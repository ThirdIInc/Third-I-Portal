package org.paradyne.lib;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Sunil
 * @date 29 Nov 2007
**/

/**
 * A servlet-based broker for database connections. Creates and manages a pool of database connections.
**/

public class DbConnectionBroker implements Runnable {    
	static Logger logger = Logger.getLogger(DbConnectionBroker.class);	
	private Thread runner;
    private Connection[] connPool;
    private int[] connStatus;
    private long[] connLockTime, connCreateDate;
    private String[] connID;
    private String dbDriver, dbServer, dbLogin, dbPassword, logFileString;
    private int currConnections, connLast, minConns, maxConns, maxConnMSec, maxCheckoutSeconds, debugLevel;

    //Set to false on destroy, checked by getConnection()
    private boolean available = true;
    private PrintWriter log;
    private SQLWarning currSQLWarning;
    private String pid;

    private final int DEFAULTMAXCHECKOUTSECONDS = 60;
    private final int DEFAULTDEBUGLEVEL = 2;

    /**
     * Creates a new Connection Broker
    **/
    /**
     * @param dbDriver -: Specifies JDBC driver. e.g. 'oracle.jdbc.driver.OracleDriver'
     * @param dbServer -: Specifies JDBC connect string. e.g. 'jdbc:oracle:thin:@203.92.21.109:1526:orcl'
     * @param dbLogin -: Specifies Database login name. e.g. 'Scott'
     * @param dbPassword -: Specifies Database password. e.g. 'Tiger'
     * @param minConns -: Specifies Minimum number of connections to start with.
     * @param maxConns -: Specifies Maximum number of connections in dynamic pool.
     * @param logFileString -: Specifies Absolute path name for log file. e.g. 'c:/temp/mylog.log'
     * @param maxConnTime -: Specifies Time in days between connection resets. (Reset does a basic cleanup)
     * @throws IOException
     * logAppend -: Specifies Append to logfile (optional)
     * maxCheckoutSeconds -: Specifies Max time a connection can be checked out before being recycled. 
     * 						 Zero value turns option off, default is 60 seconds.
     * debugLevel -: Specifies Level of debug messages output to the log file.  
     * 				 0 -> no messages, 1 -> Errors, 2 -> Warnings, 3 -> Information
    **/
    public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin, String dbPassword, int minConns, 
    		int maxConns, String logFileString, double maxConnTime) throws IOException {
    	setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, maxConns, logFileString, maxConnTime, false,
    			DEFAULTMAXCHECKOUTSECONDS, DEFAULTDEBUGLEVEL);
    }

    /**
     * Special constructor to handle logfile append
    **/
    public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin, String dbPassword, int minConns, 
    		int maxConns, String logFileString, double maxConnTime, boolean logAppend) throws IOException {
    	setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, maxConns, logFileString, maxConnTime, 
    			logAppend, DEFAULTMAXCHECKOUTSECONDS, DEFAULTDEBUGLEVEL);
    }

    /**
     * Special constructor to handle connection checkout expiration
    **/
    public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin, String dbPassword, int minConns, 
    		int maxConns, String logFileString, double maxConnTime, boolean logAppend, int maxCheckoutSeconds, 
    		int debugLevel) throws IOException {
    	setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, maxConns, logFileString, maxConnTime, 
    			logAppend, maxCheckoutSeconds, debugLevel);
    }

    private void setupBroker(String dbDriver, String dbServer, String dbLogin, String dbPassword, int minConns, 
    		int maxConns, String logFileString, double maxConnTime, boolean logAppend, int maxCheckoutSeconds, 
    		int debugLevel) throws IOException {
    	
        connPool = new Connection[maxConns];
        connStatus = new int[maxConns];
        connLockTime = new long[maxConns];
        connCreateDate = new long[maxConns];
        connID = new String[maxConns];
        currConnections = minConns;
        
        this.maxConns = maxConns;
        this.dbDriver = dbDriver;
        this.dbServer = dbServer;
        this.dbLogin = dbLogin;
        this.dbPassword = dbPassword;
        this.logFileString = logFileString;
        this.maxCheckoutSeconds = maxCheckoutSeconds;
        this.debugLevel = debugLevel;
        maxConnMSec = (int)(maxConnTime * 86400000.0);  //86400 sec/day
        
        if(maxConnMSec < 30000) {  // Recycle no less than 30 seconds.
            maxConnMSec = 30000;
        }

        try {
        	log = new PrintWriter(new FileOutputStream(logFileString, logAppend),true);	    
        } catch (IOException e1) {
        	//Can't open the requested file. Open the default file.
		    try {
				log = new PrintWriter(new FileOutputStream("DCB_" + System.currentTimeMillis() + ".log", logAppend),true);
		    } catch (IOException e2) {
				throw new IOException("Can't open any log file");
		    }
        }

        /**
         * Write the pid file (used to clean up dead/broken connection)
        **/
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
        Date nowc = new Date();
        pid = formatter.format(nowc);

        BufferedWriter pidout = new BufferedWriter(new FileWriter(logFileString + "pid"));
        pidout.write(pid);
        pidout.close();

        boolean connectionsSucceeded = false;
        int dbLoop = 20;

        try {
            for(int i = 1; i < dbLoop; i++) {
                try {
                    for(int j = 0; j < currConnections; j++) {
                        createConn(j);//Creates a connection by passing connection number
                    }
                    connectionsSucceeded = true;
                    break;
                } catch (SQLException e){
                	if(debugLevel > 0) {
                	}
                	try { Thread.sleep(15000); }
                	catch(InterruptedException e1) {}
                }
            }
            if(!connectionsSucceeded) { //All attempts at connecting to db exhausted
            	if(debugLevel > 0) {
            	}
                throw new IOException();
            }
        } catch (Exception e) {
        	throw new IOException();
        }

        /**
         * Fire up the background housekeeping thread
        **/
        runner = new Thread(this);
        runner.start();
    }

    /**
     * Housekeeping thread. Runs in the background with low CPU overhead. 
     * Connections are checked for warnings and closure and are periodically restarted. 
     * This thread is a catchall for corrupted connections and prevents the buildup of open cursors. 
     * (Open cursors result when the application fails to close a Statement).
     * This method acts as fault tolerance for bad connection/statement programming.
    **/
    public void run() {
        boolean forever = true;
        //String currCatalog = null;
		long maxCheckoutMillis = maxCheckoutSeconds * 10000;

        while(forever) {
        	/**
        	 * Make sure the log file is the one this instance opened. If not, clean it up!
        	**/
        	try {
        		BufferedReader in = new BufferedReader(new FileReader(logFileString + "pid"));
        		String curr_pid = in.readLine();
				if(curr_pid.equals(pid)) {
				} else {
				    log.close();

				    /**
				     * Close all connections silently - they are definitely dead.
				    **/
				    for(int i=0; i < currConnections; i++) {
						try {
						    connPool[i].close();
						} catch (SQLException e1) {
						} catch (Exception e2) {
						}
				    }
				    return;//Returning from the run() method kills the thread
				}
				in.close();
		    } catch (IOException e1) {
		    } catch (Exception e2) {}
		    
		    /**
		     * Get any Warnings on connections and print to event file
		    **/
		    for(int i=0; i < currConnections; i++) {
		    	try {
		    		currSQLWarning = connPool[i].getWarnings();
		    		if(currSQLWarning != null) {
		    			if(debugLevel > 1) {
		    			}
		    			connPool[i].clearWarnings();
		    		}
		    	} catch(SQLException e) {
		    		if(debugLevel > 1) {
		    		}
		    	} catch (Exception e2) {}
		    }

	    for(int i=0; i < currConnections; i++) { // Do for each connection
	    	long age = System.currentTimeMillis() - connCreateDate[i];

	    	try {  //Test the connection with createStatement call
	    		synchronized(connStatus) {
	    			if(connStatus[i] > 0) { // In use, catch it next time!
	    				// Check the time it's been checked out and recycle
	    				long timeInUse = System.currentTimeMillis() - connLockTime[i];
	    				if(debugLevel > 2) {
	    				}
	    				if(maxCheckoutMillis != 0) {
	    					if(timeInUse > maxCheckoutMillis) {
	    						if(debugLevel > 1) {
	    						}
	    						throw new SQLException();
	    					}
	    				}
	    				continue;
	    			}
	    			connStatus[i] = 2; //Take offline (2 indicates housekeeping lock)
	    		}
	    		if(age > maxConnMSec) {  //Force a reset at the max conn time
	    			throw new SQLException();
	    		}

	    		connStatus[i] = 0;  //Connection is O.K.
	    		if(connPool[i].isClosed()) {
	    			throw new SQLException();
	    		}
	    	} catch(SQLException e) { // Connection has a problem, restart it
	    		if(debugLevel > 1) {
	    		}
	    		try {
	    			connPool[i].close();
	    		} catch(SQLException e0) {
	    			if(debugLevel > 0) {
	    				logger.info("Error!  Can't close connection!  Might have been closed already.  Trying to recycle anyway... (" + e0 + ")");
	    			}
	    		}
	    		try {
	    			createConn(i);
	    		} catch(SQLException e1) {
	    			if(debugLevel > 0) {
	    				logger.info("Failed to create connection: " + e1);
	    			}
	    			connStatus[i] = 0; //Can't open, try again next time
	    		}
	    	} finally {
	    	}
	    }
	    try { Thread.sleep(20000); }  //Wait 20 seconds for next cycle
	    catch(InterruptedException e) {
	    	//Returning from the run method sets the internal flag referenced by Thread.isAlive() to false.
	    	//This is required because we don't use stop() to shutdown this thread.
	    	return;
	    	}
        }
    }

    /**
     * This method hands out the connections in round-robin order.
     * This prevents a faulty connection from locking up an application entirely.
     * A browser 'refresh' will get the next connection while the faulty connection is cleaned up by the 
     * housekeeping thread.
     * If the min number of threads are ever exhausted, new threads are added up the the max thread count.
     * Finally, if all threads are in use, this method waits 2 seconds and tries again, up to ten times.
     * After that, it returns a null.
    **/
    public Connection getConnection() {
        Connection conn = null;
		try{
        	if(available){
            	boolean gotOne = false;

	            for(int outerloop = 1; outerloop <= 10; outerloop++) {
	            	try {
	            		int loop = 0;
	                  	int roundRobin = 0;
	                    if(roundRobin >= currConnections) {
	                    	roundRobin = 0;
	                    }
	
	                    do {
	                         synchronized(connStatus) {                         	
	                            if((connStatus[roundRobin] < 1) && (! connPool[roundRobin].isClosed())) {
	                            	conn = connPool[roundRobin];
	                            	connStatus[roundRobin] = 1;
	                            	connLockTime[roundRobin] = System.currentTimeMillis();
	                            	connLast = roundRobin;
	                            	gotOne = true;
	                            	break;
	                            } else {
	                            	loop++;
	                                roundRobin++;
	                                if(roundRobin >= currConnections) {
	                                	roundRobin = 0;
	                                }
	                            }
	                         }
	                    }
	                    while((gotOne == false) && (loop < currConnections));
	                }
	                catch (SQLException e1) {
	                }
	
	                if(gotOne) {
	                    break;
	                } else {
	                	synchronized(this) {  //Add new connections to the pool
	                		if(currConnections < maxConns) {
	                			try {
	                                createConn(currConnections);
									currConnections++;
	                            } catch(SQLException e) {
	                            	if(debugLevel > 0) {
	                            	}
	                            }
	                        }
	                    }
	                	try { Thread.sleep(1000); } catch(InterruptedException e) {}
	                	if(debugLevel > 0) {
	                		logger.fatal("-----> Connections Exhausted!  Will wait and try again in loop " + String.valueOf(outerloop));
	                	}
	                }
	            } //End of try 10 times loop
        	} else {
        		if(debugLevel > 0) {
        			logger.fatal("Unsuccessful getConnection() request during destroy()");
        		}
        	} //End if(available)

        	if(debugLevel > 2) {
        		logger.fatal("Handing out connection " + idOfConnection(conn) + " --> " +
        				(new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss a")).format(new java.util.Date()));
        	}
		}catch(Exception e) {
			logger.info("get connection error " +e);
		}
		//logger.fatal("created connection " + idOfConnection(conn));
		return conn;
    }

    /**
     * Returns the local JDBC ID for a connection.
    **/
    public int idOfConnection(Connection conn) {
        int match;
        String tag;

        try {
            tag = conn.toString();
        }
        catch (NullPointerException e1) {
            tag = "none";
        }

        match = -1;

        for(int i = 0; i < currConnections; i++) {
            if(connID[i].equals(tag)) {
                match = i;
                break;
            }
        }
        return match;
    }

    /**
     * Frees a connection. Replaces connection back into the main pool for reuse.
    **/
    public String freeConnection(Connection conn) {
        String res = "";
        try{
	        if(conn != null) {
		        int thisconn = idOfConnection(conn);//Get local JDBC ID of a connection
		        if(thisconn >= 0) {
		            connStatus[thisconn] = 0;
		            res = "freed " + conn.toString();
		            //logger.fatal("Freed connection " + String.valueOf(thisconn) + " time to close: "+getAge(conn));
		        } else {
			    	if(debugLevel > 0) {
						logger.fatal("----> Error: Could not free connection!!!");
			    	}
		        }
		    }
		}catch(Exception e) {
			logger.fatal("----> Error: Could not free connection -- connection not forund!!!---"+idOfConnection(conn));
			logger.error(idOfConnection(conn)+"---"+e);
		}
        return res;
    }

    /**
     * Returns the age of a connection -: the time in millisec since it was handed out to an application.
    **/
    public long getAge(Connection conn) {
        int thisconn = idOfConnection(conn);//Get local JDBC ID of a connection
        return System.currentTimeMillis() - connLockTime[thisconn];
    }

    /**
     * Creates a new database connection
    **/
    private void createConn(int i) throws SQLException {
        Date now = new Date();
        try {
          	Class.forName(dbDriver);
            connPool[i] = java.sql.DriverManager.getConnection(dbServer, dbLogin , dbPassword);
            connStatus[i] = 0;
            connID[i] = connPool[i].toString();
            connLockTime[i] = 0;
            connCreateDate[i] = now.getTime();
        } catch (Exception e2) {
	    	if(debugLevel > 0) {
				logger.info("Error creating connection: " + e2);
	    	}
		}
        logger.fatal(now.toString() + "Opening connection " + String.valueOf(i) + " " + connPool[i].toString() + ":");
    }

    /**
     * Shuts down the housekeeping thread and closes all connections in the pool.
     * Call this method from the destroy() method of the servlet.
    **/
    /**
     * Multi-phase shutdown having following sequence:
     * getConnection() will refuse to return connections.
     * The housekeeping thread is shut down.
     * Up to the time of millis milliseconds after shutdown of the housekeeping thread
     * freeConnection() can still be called to return used connections.
     * After millis milliseconds after the shutdown of the housekeeping thread, all connections in the pool are closed.
     * If any connections were in use while being closed then a SQLException is thrown.
     * The log is closed.
     * Call this method from a servlet destroy() method.
    **/
    /**
     * @param millis -: The time to wait in milliseconds
     * @throws SQLException -: if connections were in use after millis
    **/
    public void destroy(int millis) throws SQLException {
    	/**
    	 * Checking for invalid negative arguments is not necessary, Thread.join() does this already in runner.join().
    	**/
        
        //Stop issuing connections
        available = false;

        //Shut down the background housekeeping thread
        runner.interrupt();

        //Wait until the housekeeping thread has died.
        try { runner.join(millis); }
        catch(InterruptedException e){}

        /**
         * The housekeeping thread could still be running (e.g. if millis is too small).
         * This case is ignored.
         * At worst, this method will throw an exception with the clear indication that the timeout was too short.
        **/
        long startTime = System.currentTimeMillis();

        // Wait for freeConnection() to return any connections that are still used at this time.
        int useCount;
        while((useCount = getUseCount()) > 0 && System.currentTimeMillis() - startTime <=  millis) {
            try { Thread.sleep(500); }
            catch(InterruptedException e) {}
        }

        //Close all connections, whether safe or not
        for(int i = 0; i < currConnections; i++) {
            try {
                connPool[i].close();
            } catch (SQLException e1) {
            	if(debugLevel > 0) {
            		logger.info("Cannot close connections on Destroy");
            	}
            }
        }

        if(useCount > 0) {
            String msg="Unsafe shutdown: Had to close "+useCount+" active DB connections after "+millis+"ms";
            logger.info(msg);            
            log.close();//Close all open files
            // Throwing Exception is essential because servlet authors are likely to have their own error logging requirements.
            throw new SQLException(msg);
        }
        log.close();//Close all open files
    }

    /**
     * Less safe shutdown.  Uses default timeout value.
     * This method simply calls the destroy()method with a millis value of 10000 (10 seconds) and ignores 
     * SQLException thrown by that method.
    **/
    public void destroy() {
        try {
            destroy(10000);
        }
        catch(SQLException e) {}
    }

    /**
     * Returns the number of connections in use.
    **/
    /**
     * This method could be reduced to return a counter that is maintained by all methods that update connStatus.
     * However, it is more efficient to do it this way because updating the counter would put an additional burden 
     * on the most frequently used methods; in comparison, this method is rarely used (although essential).
    **/
    public int getUseCount() {
        int useCount=0;
        synchronized(connStatus) {
            for(int i=0; i < currConnections; i++) {
                if(connStatus[i] > 0) { // In use
                    useCount++;
                }
            }
        }
        return useCount;
    }

    /**
     * Returns the number of connections in the dynamic pool.
    **/
    public int getSize() {
        return currConnections;
    }
}