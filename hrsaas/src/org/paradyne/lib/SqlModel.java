package org.paradyne.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import javax.servlet.http.*;
import javax.servlet.*;
import oracle.jdbc.driver.OraclePreparedStatement;

/**
 * @author Sunil
 * @date 29 Nov 2007
 **/

/**
 * For all sorts of sql transactions
 */

public class SqlModel {
	public static int ADD = 1;
	public static int MOD = 2;
	public static int DEL = 3;
	static Logger logger = Logger.getLogger(SqlModel.class);
	private int errorCode = 0;
	boolean resultFlag = false;
	private int updateRows = 0;

	boolean oracleflag = false;
	private java.sql.Connection dbConn = null;
	org.paradyne.lib.ConnectionModel connModel = null;
	HttpSession session = null;
	ServletContext context = null;
	int cnt = 0;
	
	public SqlModel() {
		
	}

	/**
	 * @param dbConn
	 */
	public SqlModel(java.sql.Connection dbConn) {
		this.dbConn = dbConn;
	}
	
	/**
	 * 
	 * @param context
	 */
	public SqlModel(ServletContext context) {	
		this.context=context;
		this.connModel = new org.paradyne.lib.ConnectionModel();			
	}

	/**
	 *	For custom connections to other databases 
	 * 
	 * @param context
	 * @param session
	 */
	public SqlModel(ServletContext context, HttpSession session, String ConnName) {
		this.context = context;
		this.session = session;
		this.connModel = new org.paradyne.lib.ConnectionModel();
		this.connModel.setOtherDBconnection(ConnName); 
	}
	
	
	/**
	 * @param context
	 * @param session
	 */
	public SqlModel(ServletContext context, HttpSession session) {
		this.context = context;
		this.session = session;
		this.connModel = new org.paradyne.lib.ConnectionModel();
	}

	/**
	 * Returns the ServletContext object
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * Returns the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Get resultset for a single query.
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @return Object[][] as set of records by executing a query
	 */
	public Object[][] getSingleResult(String sqlQuery) {
		logger.info("sqlQuery==1================= "+sqlQuery);
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		System.out.println("...");
		System.out.println("dbConn..."+dbConn);
		try {
			/**
			 * Get & prepare the query
			 */
			if (dbConn == null) {
				dbConn = connModel.getConnection(context, session);// Get
																	// database
																	// connection
			}
			
			PST = dbConn.prepareStatement(sqlQuery,	ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			/**
			 * Executes Query
			 */
			rs = PST.executeQuery();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if (ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1,
									Integer.parseInt("" + clob.length()));
						} catch (Exception e) {
							logger.error("clob error" + e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch (Exception e) {
			logger.error("Excepiton Sql number :" + e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error("rs.close() error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return twoDimObjArr;
	}

	/**
	 * Get resultset for a single query.
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @param parameterObj -:
	 *            Specifies the parameter values for a query
	 * @return Object[][] as set of records by executing a query
	 */
	public Object[][] getSingleResult(String sqlQuery, Object[] parameterObj) {
		
		logger.info("sqlQuery==2================="+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {
			/**
			 * Get & prepare the query
			 */
			if (dbConn == null) {
				dbConn = connModel.getConnection(context, session);// Get
																	// database
																	// connection
			}
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			for (int j = 0; j < parameterObj.length; j++) {
				PST.setObject(j + 1, parameterObj[j]);
			}

			/**
			 * Executes a query
			 */
			rs = PST.executeQuery();
			PST.clearParameters();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if (ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1,
									Integer.parseInt("" + clob.length()));
						} catch (Exception e) {
							logger.error("clob error" + e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch (Exception e) {
			logger.error("DB Erroror********************" + errorCode + "--"
					+ e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error("rs.close error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return twoDimObjArr;
	}

	
public Object[][] getSingleResult(String sqlQuery, Object[] parameterObj,Connection dbConn) {
		
		logger.info("sqlQuery==2================="+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {
			/**
			 * Get & prepare the query
			 */
			if (dbConn == null) {
				//dbConn = connModel.getConnection(context, session);// Get
																	// database
																	// connection
			}
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			for (int j = 0; j < parameterObj.length; j++) {
				PST.setObject(j + 1, parameterObj[j]);
			}

			/**
			 * Executes a query
			 */
			rs = PST.executeQuery();
			PST.clearParameters();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if (ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1,
									Integer.parseInt("" + clob.length()));
						} catch (Exception e) {
							logger.error("clob error" + e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch (Exception e) {
			logger.error("DB Erroror********************" + errorCode + "--"
					+ e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error("rs.close error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			//connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return twoDimObjArr;
	}
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @return Object[][] as set of records by executing a query
	 */
	public Object[][] getSingleResultInsensitive(String sqlQuery) {
		
		logger.info("sqlQuery==3================= "+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;

		try {
			/**
			 * Get & prepare the query
			 */
			if (dbConn == null) {
				dbConn = connModel.getConnection(context, session);// Get
																	// database
																	// connection
			}

			PST = dbConn.prepareStatement(sqlQuery,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			/**
			 * Execute Query
			 */
			rs = PST.executeQuery();

			/**
			 * To prepare MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if (ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1,
									Integer.parseInt("" + clob.length()));
						} catch (Exception e) {
							logger.error("clob error" + e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch (Exception e) {
			logger.error("Excepiton Sql number :" + e);
		} finally {
			try {
				logger.info("--in finally");
				rs.close();
			} catch (Exception e) {
				logger.error(e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return twoDimObjArr;
	}

	/**
	 * Get resultset for along with column names
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @return Object[][] as set of records including column names by executing
	 *         a query
	 */
	public Object[][] getSingleResultWithCol(String sqlQuery) {
		
		
		logger.info("sqlQuery==4================="+sqlQuery);
		
		dbConn = connModel.getConnection(context, session);// Get database
															// connection
		Object[][] twoDimObjArr = null;
		String[][] colObjArr = null;
		Object[][] finalObject = null;
		try {
			/**
			 * Get & prepare the query
			 */
			java.sql.Statement PST = dbConn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			/**
			 * Executes Query
			 */
			java.sql.ResultSet rs = PST.executeQuery(sqlQuery);

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = 0;
			try {
				rs.last();

				/**
				 * Copy the column names
				 */
				try {
					numberOfColumns = rsmd.getColumnCount();
					colObjArr = new String[1][numberOfColumns];
					for (int i = 0; i < numberOfColumns; i++) {
						colObjArr[0][i] = rsmd.getColumnName(i + 1);
					}
				} catch (RuntimeException e1) {
					logger.error("-------------expin col names" + e1);
				}
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new Object[0][numberOfColumns];
			}
			rs.beforeFirst();

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
				}
				rowNumber++;
			}

			/**
			 * Copy column name object and their corresponding values into
			 * Object[][]
			 */
			try {
				finalObject = new Object[twoDimObjArr.length + 1][numberOfColumns];
				finalObject = Utility.joinArrays(colObjArr, twoDimObjArr, 1, 0);
			} catch (RuntimeException e) {
				logger.error("-----------------exp in join" + e);
			}
			rs.close();
			PST.close();
		} catch (Exception e) {
			logger.error("Excepiton in SqlModel :" + e);
		} finally {
			try {
				connModel.freeConnection(dbConn);
			} catch (Exception e) {
				logger.error("freeConnection: " + e);
			}
		} // end of try-catch-finally block
		return finalObject;
	}

	/**
	 * Get resultset for along with column names
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @param parameterObj -:
	 *            Specifies the parameter values for a query
	 * @return Object[][] as set of records including column names by executing
	 *         a query
	 */
	public Object[][] getSingleResultWithCol(String sqlQuery,
			Object[] parameterObj) {
		
	logger.info("sqlQuery==5================= "+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		String[][] colObjArr = null;
		Object[][] finalObject = null;
		try {
			dbConn = connModel.getConnection(context, session);// Get database
																// connection
			/**
			 * Get & prepare the query
			 */
			java.sql.PreparedStatement PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			for (int j = 0; j < parameterObj.length; j++) {
				PST.setObject(j + 1, parameterObj[j]);
			}

			/**
			 * Executes a query
			 */
			java.sql.ResultSet rs = PST.executeQuery();
			PST.clearParameters();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			rs.last();
			int numberOfColumns = 0;

			/**
			 * Copy the column names
			 */
			try {
				numberOfColumns = rsmd.getColumnCount();
				colObjArr = new String[1][numberOfColumns];

				for (int i = 0; i < numberOfColumns; i++) {
					colObjArr[0][i] = rsmd.getColumnName(i + 1);
				}
			} catch (RuntimeException e1) {
				logger.error("-------------expin col names" + e1);
			}

			twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
			rs.beforeFirst();

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
				}
				rowNumber++;
			}

			/**
			 * Copy column name object and their corresponding values into
			 * Object[][]
			 */
			try {
				finalObject = new Object[twoDimObjArr.length + 1][numberOfColumns];
				finalObject = Utility.joinArrays(colObjArr, twoDimObjArr, 1, 0);
			} catch (RuntimeException e) {
				logger.error("-----------------exp in join" + e);
			}
			rs.close();
			PST.close();
		} catch (Exception e) {
			logger.error("java.sql.SQLException  :" + e);
		} finally {
			try {
				connModel.freeConnection(dbConn);
			} catch (Exception e) {
				logger.error("freeConnection error: " + e);
			}
		} // end of try-catch-finally block
		return finalObject;
	}
	
	
	
	/**
	 * Returns the updated rows
	 */
	public int getUpdatedRows() {
		if (updateRows < 0) {
			return 1;
		}
		return updateRows;
	}

	/**
	 * Executes single queries query type -: add, modify, delete type only
	 * parameters -: action : ADD , MOD , DEL
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @return boolean as query successfully executes or not
	 */
	public boolean singleExecute(String sqlQuery) {
		logger.info("sqlQuery==6================="+sqlQuery);
		String msg = "";
		updateRows = 0;
		java.sql.PreparedStatement PST = null;
		try {
			dbConn = connModel.getConnection(context, session);// Get database
																// connection
			dbConn.setAutoCommit(false);

			/**
			 * Get the query
			 */
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			/**
			 * Executes a query
			 */
			int rowsEffected = PST.executeUpdate();
			updateRows = rowsEffected;
			PST.clearParameters();
			dbConn.commit();
			resultFlag = true;
		} catch (Exception ex) {
			logger.error(ex);
			/**
			 * Rollback transactions if fails.
			 */
			updateRows = 0;
			try {
				dbConn.rollback();
			} catch (Exception e) {
				logger.error("rollback error" + e);
			}
			logger.error("Error code : " + errorCode + "-" + ex);
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return resultFlag;
	}

	/**
	 * Executes single queries query type -: add, modify, delete type only
	 * parameters -: action : ADD , MOD , DEL
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @param obj -:
	 *            Specifies the parameter values for a query
	 * @return boolean as query successfully executes or not
	 */
	public boolean singleExecute(String sqlQuery, Object[][] obj) {
		
		logger.info("sqlQuery==7================= "+sqlQuery);
		String msg = "";
		updateRows = 0;
		java.sql.PreparedStatement PST = null;
		try {
			dbConn = connModel.getConnection(context, session);// Get database
																// connection
			dbConn.setAutoCommit(false);
			/**
			 * Get the query
			 */
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			if (!oracleflag) {// Check Oracle is connected or not
				/**
				 * Add the parameters
				 */
				
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter >= 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = obj[empCount];
						empCount++;
					} //end of loop
					
				for (int j = 0; j < newObject.length; j++) {
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.addBatch();
				}

				/**
				 * Executes a query
				 */
				int[] rowsEffected = PST.executeBatch();
				updateRows = rowsEffected[0];// Get number of records
												// affected
				PST.clearParameters();// Removes the parameters
				}
			} else {
				((OraclePreparedStatement) PST).setExecuteBatch(3);

				/**
				 * Add the parameters
				 */
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter > 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = newObject[empCount];
						empCount++;
					} //end of loop
				for (int j = 0; j < newObject.length; j++) { 
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.executeUpdate();
				}

				/**
				 * Executes a query
				 */
				updateRows = ((OraclePreparedStatement) PST).sendBatch();
				PST.clearParameters();
				}
			}
			dbConn.commit();
			resultFlag = true;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			/**
			 * Rollback transactions if fails.
			 */
			updateRows = 0;
			resultFlag = false;
			try {
				dbConn.rollback();
			} catch (Exception e) {
				logger.error("rollback error: " + e);
			}
			logger.error("Error code : " + ex);
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} //end of try-catch-finally block
		return resultFlag;
	}
	
	/**
	 * Executes single queries query type -: add, modify, delete type only
	 * parameters -: action : ADD , MOD , DEL
	 */
	/**
	 * @param sqlQuery -: Specifies the query to be executed on connection
	 * @param obj Specifies the parameter values for a query
	 * @param dbConn Specifies the connection object for a query
	 * @return boolean as query successfully executes or not
	 */
public boolean singleExecute(String sqlQuery, Object[][] obj,Connection dbConn) {
		
		logger.info("sqlQuery==7================= "+sqlQuery);
		String msg = "";
		updateRows = 0;
		java.sql.PreparedStatement PST = null;
		try {
			
			dbConn.setAutoCommit(false);
			/**
			 * Get the query
			 */
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			if (!oracleflag) {// Check Oracle is connected or not
				/**
				 * Add the parameters
				 */
				
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter >= 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = obj[empCount];
						empCount++;
					} //end of loop
					
				for (int j = 0; j < newObject.length; j++) {
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.addBatch();
				}

				/**
				 * Executes a query
				 */
				int[] rowsEffected = PST.executeBatch();
				updateRows = rowsEffected[0];// Get number of records
												// affected
				PST.clearParameters();// Removes the parameters
				}
			} else {
				((OraclePreparedStatement) PST).setExecuteBatch(3);

				/**
				 * Add the parameters
				 */
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter > 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = newObject[empCount];
						empCount++;
					} //end of loop
				for (int j = 0; j < newObject.length; j++) { 
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.executeUpdate();
				}

				/**
				 * Executes a query
				 */
				updateRows = ((OraclePreparedStatement) PST).sendBatch();
				PST.clearParameters();
				}
			}
			dbConn.commit();
			resultFlag = true;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			/**
			 * Rollback transactions if fails.
			 */
			updateRows = 0;
			resultFlag = false;
			try {
				dbConn.rollback();
			} catch (Exception e) {
				logger.error("rollback error: " + e);
			}
			logger.error("Error code : " + ex);
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			//connModel.freeConnection(dbConn);
		} //end of try-catch-finally block
		return resultFlag;
	}

	/**
	 * method to get the Connection Object
	 * @return Connection object
	 */
	public Connection connection() {
		return connModel.getConnection(context, session);
	}
	public void freeConnection(Connection conn){
		connModel.freeConnection(conn);
	}
	//-------------------------------New added by prakash-----------------------------
	/**
	 * @param sqlNumberArr -:
	 *            Specifies the array of actual queries
	 * @param dataVector -:
	 *            Specifies the vector of objects which are to be passed to respective queries
	 * @return boolean as query successfully executes or not
	 */
	public boolean multiExecute( Object [] sqlNumberArr , java.util.Vector dataVector) {
		String msg = "";
		java.sql.PreparedStatement PST = null;
		try{
			dbConn = connModel.getConnection(context, session);// Get database
			// connection
			dbConn.setAutoCommit(false);
			
			for(int i = 0 ; i < sqlNumberArr.length ; i++) 
			{
				/*
				 *	To get the query
				 */
				String sqlQuery =  "";
			 	
			 		sqlQuery = String.valueOf(sqlNumberArr[i]);
			 	
//				String sqlQuery = sql.getQuery(sqlNumberArr[i]);

				//System.out.println("slquery--------------------"+sqlQuery);
				 PST = dbConn.prepareStatement(sqlQuery,
						 ResultSet.TYPE_SCROLL_SENSITIVE,
						 ResultSet.CONCUR_UPDATABLE );

				/*
				 *	To add the parameters
				 */
				Object[][] obj = (Object[][]) dataVector.elementAt(i);
				//System.out.println("Query-------------------: " + sqlQuery);
				//System.out.println("Data--------------------- " + obj[0][0]);
				obj = Utility.scanData(obj);
				for(int j = 0 ; j < obj.length ; j ++) 
				{
					for(int k = 0 ; k < obj[j].length ; k ++) 
					{
						if(null==obj[j][k])
							PST.setNull(k+1,java.sql.Types.NULL);
						else
							PST.setObject(k+1,obj[j][k]);
//						System.out.println("parameter "+j+"-"+k+": "+String.valueOf(obj[j][k]));
					}
					PST.addBatch();
				}

				/*
				 *	Execuite query
				 */
				PST.executeBatch();
				PST.clearParameters();
				PST.close();
			}
			dbConn.commit();
			resultFlag = true;

			

		} 
		catch	(Exception ex) 
		{
			/*
			 *	Rollback transactions if  fails
			 */
			try {dbConn.rollback();logger.info("ROllback executed..........................");} catch(Exception e) {
				
				e.printStackTrace();}
			
			
			resultFlag = false;
			ex.printStackTrace();
			
		} 
		finally 
		{
			/*
			 *	Set set autocommit to default
			 */
			try {dbConn.setAutoCommit(true);} catch(Exception e) {}
		}
		return resultFlag;
	}
	
	public Object[][] getSingleResult(String sqlQuery,
			Object[][] parameterObj,Connection dbConn) {
		
	logger.info("sqlQuery==5================= "+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		String[][] colObjArr = null;
		Object[][] finalObject = null;
		
		java.sql.PreparedStatement PST = null;
		
		
		try {
		 
			
			PST = dbConn
			.prepareStatement(sqlQuery,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			
			/*for (int j = 0; j < parameterObj.length; j++) {
				PST.setObject(j + 1, parameterObj[j]);
			}*/
			
			
			for (int j = 0; j < parameterObj.length; j++) {
				for (int k = 0; k < parameterObj[0].length; k++) {
					PST.setObject(k + 1, parameterObj[j][k]);
				}
				PST.addBatch();
			}

			/**
			 * Executes a query
			 */
			java.sql.ResultSet rs = PST.executeQuery();
			PST.clearParameters();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			rs.last();
			int numberOfColumns = 0;

			/**
			 * Copy the column names
			 */
			try {
				numberOfColumns = rsmd.getColumnCount();
				colObjArr = new String[1][numberOfColumns];

				for (int i = 0; i < numberOfColumns; i++) {
					colObjArr[0][i] = rsmd.getColumnName(i + 1);
					
					colObjArr[0][i] = rsmd.getColumnName(i + 1);
				}
			} catch (RuntimeException e1) {
				logger.error("-------------expin col names" + e1);
			}

			twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
			rs.beforeFirst();

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
				}
				rowNumber++;
			}

			/**
			 * Copy column name object and their corresponding values into
			 * Object[][]
			 */
			try {
				finalObject = new Object[twoDimObjArr.length + 1][numberOfColumns];
				finalObject = Utility.joinArrays(colObjArr, twoDimObjArr, 1, 0);
			} catch (RuntimeException e) {
				logger.error("-----------------exp in join" + e);
			}
			rs.close();
			PST.close();
		} catch (Exception e) {
			logger.error("java.sql.SQLException  :" + e);
		} /*finally {
			try {
				connModel.freeConnection(dbConn);
			} catch (Exception e) {
				logger.error("freeConnection error: " + e);
			}
		} // end of try-catch-finally block
*/		
		finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} // end of try-catch-finally block
		return finalObject;
	}


	/**
	 * Get resultset for along with column names
	 */
	/**
	 * @param sqlQuery -:
	 *            Specifies the query to be executed
	 * @param parameterObj -:
	 *            Specifies the parameter values for a query
	 * @return Object[][] as set of records including column names by executing
	 *         a query
	 */
	public Object[][] getSingleResultWithCol(String sqlQuery,
			Object[] parameterObj,Connection dbConn) {
		
	logger.info("sqlQuery==5================= "+sqlQuery);
		
		Object[][] twoDimObjArr = null;
		String[][] colObjArr = null;
		Object[][] finalObject = null;
		
		java.sql.PreparedStatement PST = null;
		
		
		try {
		 
			
			PST = dbConn
			.prepareStatement(sqlQuery,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			for (int j = 0; j < parameterObj.length; j++) {
				PST.setObject(j + 1, parameterObj[j]);
			}

			/**
			 * Executes a query
			 */
			java.sql.ResultSet rs = PST.executeQuery();
			PST.clearParameters();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			rs.last();
			int numberOfColumns = 0;

			/**
			 * Copy the column names
			 */
			try {
				numberOfColumns = rsmd.getColumnCount();
				colObjArr = new String[1][numberOfColumns];

				for (int i = 0; i < numberOfColumns; i++) {
					colObjArr[0][i] = rsmd.getColumnName(i + 1);
					
					colObjArr[0][i] = rsmd.getColumnName(i + 1);
				}
			} catch (RuntimeException e1) {
				logger.error("-------------expin col names" + e1);
			}

			twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
			rs.beforeFirst();

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
				}
				rowNumber++;
			}

			/**
			 * Copy column name object and their corresponding values into
			 * Object[][]
			 */
			try {
				finalObject = new Object[twoDimObjArr.length + 1][numberOfColumns];
				finalObject = Utility.joinArrays(colObjArr, twoDimObjArr, 1, 0);
			} catch (RuntimeException e) {
				logger.error("-----------------exp in join" + e);
			}
			rs.close();
			PST.close();
		} catch (Exception e) {
			logger.error("java.sql.SQLException  :" + e);
		} /*finally {
			try {
				connModel.freeConnection(dbConn);
			} catch (Exception e) {
				logger.error("freeConnection error: " + e);
			}
		} // end of try-catch-finally block
*/		
		finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} // end of try-catch-finally block
		return finalObject;
	}


	public static Object[][] getSingleResult(String sqlQuery,Connection dbConn) {
		System.out.println("sqlQuery==1================= "+sqlQuery);
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {

			
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Executes Query
			 */
			rs = PST.executeQuery();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
				rs.beforeFirst();
			} catch (Exception e) {
				System.out.println(e);
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while (rs.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if (ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1,
									Integer.parseInt("" + clob.length()));
						} catch (Exception e) {
							System.out.println("clob error" + e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch (Exception e) {
			System.out.println("Excepiton Sql number :" + e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("rs.close() error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} // end of try-catch-finally block
		return twoDimObjArr;
	}
	public static boolean singleExecute(String sqlQuery,Connection dbConn) {
		System.out.println("sqlQuery==6================="+sqlQuery);
		String msg = "";
		int updateRows = 0;
		boolean resultFlag = false;
		int errorCode = 0;
		java.sql.PreparedStatement PST = null;
		try {
			
			/**
			 * Get the query
			 */
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			/**
			 * Executes a query
			 */
			int rowsEffected = PST.executeUpdate();
			updateRows = rowsEffected;
			PST.clearParameters();
			dbConn.commit();
			resultFlag = true;
		} catch (Exception ex) {
			System.out.println(ex);
			/**
			 * Rollback transactions if fails.
			 */
			updateRows = 0;
			try {
				dbConn.rollback();
			} catch (Exception e) {
				System.out.println("rollback error" + e);
			}
			System.out.println("Error code : " + errorCode + "-" + ex);
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} // end of try-catch-finally block
		return resultFlag;
	}
	/**
	 * @param sqlQuery= query string
	 * @param keyPosition = key for map
	 * @param valueObject = represents single or two dimensional object to return.
	 * @return Hash Map
	 */
	public HashMap getSingleResultMap(String sqlQuery,int keyPosition,int valueObject) {
		logger.info("sqlQuery==1================= "+sqlQuery);
		HashMap<String,Object[]> twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {

			/**
			 * Get & prepare the query
			 */
			if (dbConn == null) {
				dbConn = connModel.getConnection(context, session);// Get
																	// database
																	// connection
			}
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Executes Query
			 */
			rs = PST.executeQuery();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new HashMap<String,Object[]>();
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new HashMap<String,Object[]>();
			}
			

			/**
			 * Mapping the resultSet
			 */
				//int rowNumber = 0;
				while (rs.next()) {
					if(valueObject == 0){
						Object[]singleObject = new Object[numberOfColumns];
						for (int i = 0; i < numberOfColumns; i++) {
							int ctype = rsmd.getColumnType(i + 1);
							if (ctype == java.sql.Types.CLOB) {
								try {
									java.sql.Clob clob = rs.getClob(i + 1);
									singleObject[i] = clob.getSubString(1,
											Integer.parseInt("" + clob.length()));
								} catch (Exception e) {
									logger.error("clob error" + e);
								} //end of catch
							} //end of if 
							else {
								singleObject[i] = rs.getObject(i + 1);
							} //end of else
						} //end of loop
						//rowNumber++;
						twoDimObjArr.put(String.valueOf(rs.getObject(keyPosition + 1)),singleObject);
					} //end of if for single
					else{
						//logger.info("sql 8");
					} //end of else
				}
				if(valueObject == 2){
					rs.beforeFirst();
					int noOfRows = 0;
					while(rs.next())
						++noOfRows;
					//rs.beforeFirst();
					String code = "";
					for(int i=0; i<noOfRows; )
					{
						rs.absolute(i+1);
						code = String.valueOf(rs.getObject(keyPosition+1));
						int counter = 0;
						for(int j=i; j<noOfRows; j++){
							if(String.valueOf(rs.getObject(keyPosition+1)).equalsIgnoreCase(code))
							{
								counter++;
								rs.next();
							}
							else
								break;
						}
						Object doubleObject[][] = new Object[counter][numberOfColumns];
						rs.absolute(i+1);
						for(int z=0;z<counter;z++){
							for(int a=0;a<numberOfColumns;a++){
								doubleObject[z][a] = rs.getObject(a+1);
							}
							rs.next();
						}
						twoDimObjArr.put(code, doubleObject);
						if(counter == 0)
							i = i + 1;
						else
							i = i + counter;
					}//End of for
				}//End of if
		} catch (Exception e) {
			logger.error("Excepiton Sql number :" + e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error("rs.close() error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} // end of try-catch-finally block
		return twoDimObjArr;
	}
	
	/**
	 * @param sqlQuery= query string
	 * @param keyPosition = key for map
	 * @param valueObject = represents single or two dimensional object to return.
	 * @return Hash Map
	 */
	public static HashMap getSingleResultMap(String sqlQuery,int keyPosition,int valueObject,Connection dbConn ) {
		logger.info("sqlQuery==1================= "+sqlQuery);
		HashMap<String,Object[]> twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {

		
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			/**
			 * Executes Query
			 */
			rs = PST.executeQuery();

			/**
			 * Prepares MetaData
			 */
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			/**
			 * Prepares object for records
			 */
			try {
				rs.last();
				twoDimObjArr = new HashMap<String,Object[]>();
				rs.beforeFirst();
			} catch (Exception e) {
				logger.error(e);
				twoDimObjArr = new HashMap<String,Object[]>();
			}
			

			/**
			 * Mapping the resultSet
			 */
				//int rowNumber = 0;
				while (rs.next()) {
					if(valueObject == 0){
						Object[]singleObject = new Object[numberOfColumns];
						for (int i = 0; i < numberOfColumns; i++) {
							int ctype = rsmd.getColumnType(i + 1);
							if (ctype == java.sql.Types.CLOB) {
								try {
									java.sql.Clob clob = rs.getClob(i + 1);
									singleObject[i] = clob.getSubString(1,
											Integer.parseInt("" + clob.length()));
								} catch (Exception e) {
									logger.error("clob error" + e);
								} //end of catch
							} //end of if 
							else {
								singleObject[i] = rs.getObject(i + 1);
							} //end of else
						} //end of loop
						//rowNumber++;
						twoDimObjArr.put(String.valueOf(rs.getObject(keyPosition + 1)),singleObject);
					} //end of if for single
					else{
						//logger.info("sql 8");
					} //end of else
				}
				if(valueObject == 2){
					rs.beforeFirst();
					int noOfRows = 0;
					while(rs.next())
						++noOfRows;
					//rs.beforeFirst();
					String code = "";
					for(int i=0; i<noOfRows; )
					{
						rs.absolute(i+1);
						code = String.valueOf(rs.getObject(keyPosition+1));
						int counter = 0;
						for(int j=i; j<noOfRows; j++){
							if(String.valueOf(rs.getObject(keyPosition+1)).equalsIgnoreCase(code))
							{
								counter++;
								rs.next();
							}
							else
								break;
						}
						Object doubleObject[][] = new Object[counter][numberOfColumns];
						rs.absolute(i+1);
						for(int z=0;z<counter;z++){
							for(int a=0;a<numberOfColumns;a++){
								doubleObject[z][a] = rs.getObject(a+1);
							}
							rs.next();
						}
						twoDimObjArr.put(code, doubleObject);
						if(counter == 0)
							i = i + 1;
						else
							i = i + counter;
					}//End of for
				}//End of if
		} catch (Exception e) {
			logger.error("Excepiton Sql number :" + e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error("rs.close() error: " + e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			
		} // end of try-catch-finally block
		return twoDimObjArr;
	}
public boolean singleExecuteBulk(String sqlQuery, Object[][] obj) {
		
		logger.info("sqlQuery==7================= "+sqlQuery);
		String msg = "";
		updateRows = 0;
		java.sql.PreparedStatement PST = null;
		try {
			dbConn = connModel.getConnection(context, session);// Get database
																// connection
			dbConn.setAutoCommit(false);
			/**
			 * Get the query
			 */
			PST = dbConn
					.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			if (!oracleflag) {// Check Oracle is connected or not
				/**
				 * Add the parameters
				 */
				
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter >= 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = obj[empCount];
						empCount++;
					} //end of loop
					
				for (int j = 0; j < newObject.length; j++) {
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.addBatch();
				}

				/**
				 * Executes a query
				 */
				int[] rowsEffected = PST.executeBatch();
				updateRows = rowsEffected[0];// Get number of records
												// affected
				PST.clearParameters();// Removes the parameters
				}
			} else {
				((OraclePreparedStatement) PST).setExecuteBatch(3);

				/**
				 * Add the parameters
				 */
				int total_count = obj.length;
				int recordCounter = 15000;
				int empCount = 0;
				Object[][] newObject = null;

				for (int count = 0; total_count > 0; count++) {
					if (total_count / recordCounter > 1) {
						newObject = new Object[recordCounter][obj[0].length];
						total_count -= recordCounter;
					} //end of if 
					else {
						newObject = new Object[total_count][obj[0].length];
						total_count = 0;
					} //end of else
					for (int j = 0; j < newObject.length; j++) {
						newObject[j] = newObject[empCount];
						empCount++;
					} //end of loop
				for (int j = 0; j < newObject.length; j++) { 
					for (int k = 0; k < newObject[j].length; k++) {
						PST.setObject(k + 1, newObject[j][k]);
					}
					PST.executeUpdate();
				}

				/**
				 * Executes a query
				 */
				updateRows = ((OraclePreparedStatement) PST).sendBatch();
				PST.clearParameters();
				}
			}
			dbConn.commit();
			resultFlag = true;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			/**
			 * Rollback transactions if fails.
			 */
			updateRows = 0;
			resultFlag = false;
			try {
				dbConn.rollback();
			} catch (Exception e) {
				logger.error("rollback error: " + e);
			}
			logger.error("Error code : " + ex);
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch (Exception e) {
				logger.error(e);
			}
			connModel.freeConnection(dbConn);
		} //end of try-catch-finally block
		return resultFlag;
	}
}