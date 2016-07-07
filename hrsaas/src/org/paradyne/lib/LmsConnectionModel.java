package org.paradyne.lib;

import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * @author Sunil
 * @date 29 Nov 2007
 * This class creates a pool of database connection
**/

public class LmsConnectionModel {
	static Logger logger = Logger.getLogger(ConnectionModel.class);
	org.paradyne.lib.DbConnectionBroker pooldb = null;
	String poolName = "NO_EXISTANCE";
	int MAX_CONNECTION_TRY = 10;
	int CON_TRY = 0;
	
	/**
	 * Define database connection related parameters
	**/
	private String connectionDriver; 
	private String connectionUrl;
	private String dbUserName;
	private String dbPassword;	
	/**
	 * Creates new pool
	 * @param context
	 * @param session
	**/
	public  void createPool(javax.servlet.ServletContext context,HttpSession session) {
		org.paradyne.lib.DbConnectionBroker pool = null;
		try{
			poolName = "lms"; //(String)session.getAttribute("lms_pool");//Get pool name from session
			setConnectionInfo(poolName);//Sets the connection parameters
			pool = new org.paradyne.lib.DbConnectionBroker(getConnectionDriver(),getConnectionUrl(),
					getDbUserName(),getDbPassword(), 1, 25, "c:\\logs.log", 7.0);
			context.setAttribute(poolName,pool);//Sets the pool name at context level
		} catch (Exception e) {
			logger.error("Connection pool failed"+e);
		} //end of try-catch block
	}

	/**
	 * Destroys a pool
	**/
	public void destroyPool() {
		pooldb.destroy();
	}

	/**
	 * Frees a Connection
	**/
	/**
	 * @param conn
	**/
	public void freeConnection(java.sql.Connection conn) {
		try{
			pooldb.freeConnection(conn);			
		}catch(Exception e) {
			logger.error("Error while freeing connection"+e);
		} //end of try-catch block
	}

	/**
	 * Gets a Connection
	 * @param context
	 * @param session
	 * @return object of Connection
	**/
	public java.sql.Connection getConnection(javax.servlet.ServletContext context,HttpSession session) {
		pooldb = getPoolObject(context,session);//Get a pool of connections
		java.sql.Connection conn = pooldb.getConnection();//Get connection from pool
		try{
			if(conn == null || conn.isClosed()) {
				createPool(context,session);//Creates a new pool of connections
				getConnection(context,session);//Get connection from pool
			}
		}catch(Exception e){
			logger.error(e);
		} //end of try-catch block
		return conn;
	}

	
	/**
	 * @return String as connection driver class
	**/
	public String getConnectionDriver() {
		return connectionDriver;
	}

	/**
	 * @return String as connection URL
	**/
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @return String as password
	**/
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @return String as user name
	**/
	public String getDbUserName() {
		return dbUserName;
	}

	/**
	 * Gets a pool from context
	 * @param context
	 * @param session
	 * @return object of DbConnectionBroker
	**/
	public synchronized org.paradyne.lib.DbConnectionBroker getPoolObject(javax.servlet.ServletContext context,HttpSession session) {
		poolName = "lms"; //(String)session.getAttribute("session_pool");//Get pool name from session
		pooldb = (org.paradyne.lib.DbConnectionBroker)context.getAttribute(poolName);//Get pool object from context
		if(pooldb == null) {//If pool of connections is not existed
			CON_TRY++;//Increase no. of connections
			if(CON_TRY <= MAX_CONNECTION_TRY) {//If no. of connections reached till maximum
				createPool(context,session);//Create pool
				getPoolObject(context,session);//Get pool object
			} else {
				return null;
			}
		}
		return pooldb;
	}


	/**
	 * @param connectionDriver
	**/
	public void setConnectionDriver(String connectionDriver) {
		this.connectionDriver = connectionDriver;
	}
	
	/**
	 * Gets the parameters required to make a database connection 
	 * @param poolName
	**/
	public void setConnectionInfo(String poolName){
		ResourceBundle boundle = ResourceBundle.getBundle(this.getClass().getName());//Get resource bundle object
		
		String driver = "";
		String url = "";
		String username = "";
		String password ="";
		
		/**
		 * Decrypt the parameters defined in properties file
		**/
		try {
			driver = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle.getString(poolName+".driver"));
			url = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle.getString(poolName+".url"));
			username = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle.getString(poolName+".username"));
			password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle.getString(poolName+".password"));			
		} catch (Exception e) {
			logger.error("Connection url does not exist"+e);
		} //end of try-catch block
		
		/**
		 * Sets the decrypted parameters
		**/
		setConnectionDriver(driver);
		setConnectionUrl(url);
		setDbUserName(username);
		setDbPassword(password);
		
	}
	
	/**
	 * @param connectionUrl
	**/
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	/**
	 * @param dbPassword
	**/
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * @param dbUserName
	**/
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}




}