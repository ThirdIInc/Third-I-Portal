/* Bhushan Dasare Feb 25, 2010 */

package org.paradyne.model.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.paradyne.bean.attendance.AutoUploadAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.Utility;

public class AutoUploadAttendanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AutoUploadAttendanceModel.class);

	public boolean delete(String autoUploadID) {
		boolean result = false;
		try {
			String query = " DELETE FROM HRMS_ATT_AUTO_UPLOAD_DTL WHERE AUTO_UPLOAD_ID IN(" + autoUploadID + ") ";			
			result = getSqlModel().singleExecute(query);
			
			if(result) {
				String dtlQuery = " DELETE FROM HRMS_ATTENDANCE_AUTO_UPLOAD WHERE AUTO_UPLOAD_ID IN (" + autoUploadID + ") ";
				result = getSqlModel().singleExecute(dtlQuery);
			}
		} catch(Exception e) {
			logger.error("Exception in delete:" + e);
		}		
		return result;
	}

	public void edit(AutoUploadAttendance bean, String autoUploadID, HashMap<String, String> jobDetails) {
		try {
			String query = " SELECT NVL(AUTO_UPLOAD_DRIVER, ' ') AS AUTO_UPLOAD_DRIVER, NVL(AUTO_UPLOAD_SERVER, ' ') AS AUTO_UPLOAD_SERVER, " +
			" NVL(AUTO_UPLOAD_USER_NAME, ' ') AS AUTO_UPLOAD_USER_NAME, NVL(AUTO_UPLOAD_PASSWORD, ' ') AS AUTO_UPLOAD_PASSWORD, " +
			" NVL(AUTO_UPLOAD_DATABASE, ' ') AS AUTO_UPLOAD_DATABASE, AUTO_UPLOAD_PORT, NVL(AUTO_UPLOAD_TABLE, ' ') AS AUTO_UPLOAD_TABLE, " +
			" AUTO_UPLOAD_ID FROM HRMS_ATTENDANCE_AUTO_UPLOAD WHERE AUTO_UPLOAD_ID = " + autoUploadID;
			Object[][] data = getSqlModel().getSingleResult(query);
			
			if(data != null && data.length > 0) {
				bean.setDriver(String.valueOf(data[0][0]));
				bean.setServer(String.valueOf(data[0][1]));
				bean.setUserName(String.valueOf(data[0][2]));
				bean.setPassword(PPEncrypter.decrypt(String.valueOf(data[0][3])));
				bean.setDatabase(String.valueOf(data[0][4]));
				bean.setPortNo(String.valueOf(data[0][5]));
				bean.setTableName(String.valueOf(data[0][6]));
				bean.setAutoUploadID(String.valueOf(data[0][7]));
				
				/**
				 * SET DTL VALUE
				 */
				String dtlQuery = " SELECT AUTO_UPLOAD_ID, AUTO_UPLOAD_DTL_FIELDTYPE, " +
				" DECODE(AUTO_UPLOAD_DTL_FLAG, 'Y', 'true', 'false') AS AUTO_UPLOAD_DTL_FLAG, AUTO_UPLOAD_DTL_FIELD, AUTO_UPLOAD_DTL_ID " +
				" FROM HRMS_ATT_AUTO_UPLOAD_DTL WHERE AUTO_UPLOAD_ID = " + autoUploadID + " ORDER BY AUTO_UPLOAD_DTL_ID";
				Object[][] dtlData = getSqlModel().getSingleResult(dtlQuery);
				
				if(dtlData != null && dtlData.length > 0) {
					bean.setRadioFlag("");
					bean.setEmpFlag(String.valueOf(dtlData[0][2]));
					bean.setEmpField(Utility.checkNull(String.valueOf(dtlData[0][3])));
					bean.setDateFlag(Utility.checkNull(String.valueOf(dtlData[1][2])));
					bean.setDateField(Utility.checkNull(String.valueOf(dtlData[1][3])));
					bean.setWrkHoursFlag(Utility.checkNull(String.valueOf(dtlData[2][2])));
					bean.setWrkHoursField(Utility.checkNull(String.valueOf(dtlData[2][3])));
					bean.setShiftFlag(Utility.checkNull(String.valueOf(dtlData[3][2])));
					bean.setShiftField(Utility.checkNull(String.valueOf(dtlData[3][3])));
					bean.setInTimeFlag(Utility.checkNull(String.valueOf(dtlData[4][2])));
					bean.setInTimeField(Utility.checkNull(String.valueOf(dtlData[4][3])));
					bean.setOutTimeFlag(Utility.checkNull(String.valueOf(dtlData[5][2])));
					bean.setOutTimeField(Utility.checkNull(String.valueOf(dtlData[5][3])));
					bean.setTimeFlag(Utility.checkNull(String.valueOf(dtlData[6][2])));
					bean.setTimeField(Utility.checkNull(String.valueOf(dtlData[6][3])));
					bean.setIoFlag(Utility.checkNull(String.valueOf(dtlData[7][2])));
					bean.setIoField(Utility.checkNull(String.valueOf(dtlData[7][3])));

					if(bean.getTimeFlag().equals("true") || bean.getIoFlag().equals("true")) {
						bean.setRadioFlag("oneTimeFlag");
					}
				}
				
				if(jobDetails.size() > 0) {
					bean.setJobDuration(jobDetails.get("DURATION"));
					bean.setJobDayOfWeek(jobDetails.get("DAY_OF_WEEK"));
					bean.setJobDayOfMonth(jobDetails.get("DAY_OF_MONTH"));
					bean.setJobStartTime(jobDetails.get("START_TIME"));
				}				
			}
		} catch(Exception e) {
			logger.info("Exception in edit:" + e);
		}
	}

	public String getAutoUploadID() {
		String autoUploadID = "";
		try {
			String uploadIDSql = " SELECT NVL(MAX(AUTO_UPLOAD_ID), 0) + 1 FROM HRMS_ATTENDANCE_AUTO_UPLOAD ";
			Object[][] uploadIDObj = getSqlModel().getSingleResult(uploadIDSql);
			autoUploadID = String.valueOf(uploadIDObj[0][0]);
		} catch(Exception e) {
			logger.error("Exception in getAutoUploadID:" + e);
		}
		return autoUploadID;
	}

	public Object[][] getAutoUploadList() {
		Object[][] objAutoUploadList = null;
		try {
			String autoUploadSql = " SELECT AUTO_UPLOAD_ID, AUTO_UPLOAD_DRIVER, AUTO_UPLOAD_SERVER, AUTO_UPLOAD_USER_NAME, AUTO_UPLOAD_DATABASE " +
			" FROM HRMS_ATTENDANCE_AUTO_UPLOAD ";
			objAutoUploadList = getSqlModel().getSingleResult(autoUploadSql);
		} catch(Exception e) {
			logger.error("Exception in getAutoUploadList:" + e);
		}
		return objAutoUploadList;
	}
	
	public boolean isConfigurationExists(String server, String autoUploadID) {
		boolean result = false;
		try {
			String sql = "";
			Object[][] obj = null;
			
			if(autoUploadID.equals("")) {
				sql = " SELECT * FROM HRMS_ATTENDANCE_AUTO_UPLOAD WHERE UPPER(AUTO_UPLOAD_SERVER) = UPPER('" + server + "') ";
				obj = getSqlModel().getSingleResult(sql);
			} else {
				sql = " SELECT AUTO_UPLOAD_SERVER FROM HRMS_ATTENDANCE_AUTO_UPLOAD WHERE AUTO_UPLOAD_ID = " + autoUploadID;
				String serverName = String.valueOf((getSqlModel().getSingleResult(sql))[0][0]);
				
				if(!serverName.equals(server)) {
					sql = " SELECT * FROM HRMS_ATTENDANCE_AUTO_UPLOAD WHERE UPPER(AUTO_UPLOAD_SERVER) = UPPER('" + server + "') ";
					obj = getSqlModel().getSingleResult(sql);
				}
			}

			if(obj != null && obj.length > 0) {
				result = true;
			}
		} catch(Exception e) {
			logger.error("Exception in isConfigurationExists:" + e);
		}
		return result;
	}

	public boolean save(String autoUploadID, String driver, String server, String userName, String password, String database, String port, 
		String tableName) {
		boolean result = false;
		try {
			Object[][] param = new Object[1][8];
			param[0][0] = autoUploadID;
			param[0][1] = driver;
			param[0][2] = server;
			param[0][3] = userName;
			param[0][4] = PPEncrypter.encrypt(password);
			param[0][5] = database;
			param[0][6] = port;
			param[0][7] = tableName;
			
			String insertSql = " INSERT INTO HRMS_ATTENDANCE_AUTO_UPLOAD (AUTO_UPLOAD_ID, AUTO_UPLOAD_DRIVER, AUTO_UPLOAD_SERVER, " +
			" AUTO_UPLOAD_USER_NAME, AUTO_UPLOAD_PASSWORD, AUTO_UPLOAD_DATABASE, AUTO_UPLOAD_PORT, AUTO_UPLOAD_TABLE) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			result = getSqlModel().singleExecute(insertSql, param);
		} catch(Exception e) {
			logger.error("Exception in save:" + e);
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveDtl(AutoUploadAttendance bean, String autoUploadID) {
		boolean result = false;

		try {
			String dtlQuery = " DELETE FROM HRMS_ATT_AUTO_UPLOAD_DTL WHERE AUTO_UPLOAD_ID = " + autoUploadID;
			result = getSqlModel().singleExecute(dtlQuery);
			
			if(result) {
				Object[][] data = new Object[8][4];
				data[0][0] = "Employee Number";
				data[0][1] = bean.getEmpFlag().equals("true") ? "Y" : "N";
				data[0][2] = bean.getEmpField();
				data[0][3] = "1";

				data[1][0] = "Date";
				data[1][1] = bean.getDateFlag().equals("true") ? "Y" : "N";
				data[1][2] = bean.getDateField();
				data[1][3] = "2";

				data[2][0] = "Work Hours";
				data[2][1] = bean.getWrkHoursFlag().equals("true") ? "Y" : "N";
				data[2][2] = bean.getWrkHoursField();
				data[2][3] = "3";

				data[3][0] = "Shift";
				data[3][1] = bean.getShiftFlag().equals("true") ? "Y" : "N";
				data[3][2] = bean.getShiftField();
				data[3][3] = "4";

				if(bean.getRadioFlag().equals("oneTimeFlag")) {
					data[4][0] = "In Time";
					data[4][1] = "N";
					data[4][2] = "";
					data[4][3] = "5";

					data[5][0] = "Out Time";
					data[5][1] = "N";
					data[5][2] = "";
					data[5][3] = "6";

					data[6][0] = "Time";
					data[6][1] = bean.getTimeFlag().equals("true") ? "Y" : "N";
					data[6][2] = bean.getTimeField();
					data[6][3] = "7";

					data[7][0] = "I/O Flag";
					data[7][1] = bean.getIoFlag().equals("true") ? "Y" : "N";
					data[7][2] = bean.getIoField();
					data[7][3] = "8";
				} else {
					data[4][0] = "In Time";
					data[4][1] = bean.getInTimeFlag().equals("true") ? "Y" : "N";
					data[4][2] = bean.getInTimeField();
					data[4][3] = "5";

					data[5][0] = "Out Time";
					data[5][1] = bean.getOutTimeFlag().equals("true") ? "Y" : "N";
					data[5][2] = bean.getOutTimeField();
					data[5][3] = "6";

					data[6][0] = "Time";
					data[6][1] = "N";
					data[6][2] = "";
					data[6][3] = "7";

					data[7][0] = "I/O Flag";
					data[7][1] = "N";
					data[7][2] = "";
					data[7][3] = "8";
				}
				
				String query = " INSERT INTO HRMS_ATT_AUTO_UPLOAD_DTL(AUTO_UPLOAD_ID, AUTO_UPLOAD_DTL_FIELDTYPE, AUTO_UPLOAD_DTL_FLAG, " +
				" AUTO_UPLOAD_DTL_FIELD, AUTO_UPLOAD_DTL_ID) VALUES(" + autoUploadID + ", ?, ?, ?, ?) ";
				result = getSqlModel().singleExecute(query, data);
			}
		} catch(Exception e) {
			logger.info("Exception in saveDtl:" + e);
			e.printStackTrace();
		}
		return result;
	}

	public String testConnection(String driver, String server, String userName, String password, String database, String portNo, String tableName, 
		String fldEmpToken, String fldInTime, String fldOutTime, String fldOneTime, String fldIOFlag, String fldWorkHrs, String fldShift, 
		String fldAttendanceDate) {
		String message = "Connection Failed.";
		try {
			Connection conn = null;

			try {
				String driverClass = "", url = "";
				String sqlQuery = "SELECT ";
				
				/**
				 * Connect to Oracle
				 */
				if(driver.equals("ORACLE")) {
					if(!fldEmpToken.equals("")) { sqlQuery += fldEmpToken + ", "; }
					if(!fldInTime.equals("")) { sqlQuery += "TO_CHAR(" + fldInTime + ", 'HH24:MI:SS') AS " + fldInTime + ", "; }
					if(!fldOutTime.equals("")) {sqlQuery += "TO_CHAR(" + fldOutTime + ", 'HH24:MI:SS') AS " + fldOutTime + ", "; }
					if(!fldOneTime.equals("")) { sqlQuery += "TO_CHAR(" + fldOneTime + ", 'HH24:MI:SS') AS " + fldOneTime + ", "; }
					if(!fldIOFlag.equals("")) {sqlQuery += fldIOFlag + ", "; }
					if(!fldWorkHrs.equals("")) {sqlQuery += "TO_CHAR(" + fldWorkHrs + ", 'HH24:MI:SS') AS " + fldWorkHrs + ", "; }
					if(!fldShift.equals("")) {sqlQuery += fldShift + ", "; }
					if(!fldAttendanceDate.equals("")) { sqlQuery += "TO_CHAR(" + fldAttendanceDate + ", 'DD-MM-YYYY') AS " + fldAttendanceDate; }
					
					sqlQuery += " FROM " + tableName;
					
					driverClass = "oracle.jdbc.OracleDriver";
					url = "jdbc:oracle:thin:@" + server + ":" + portNo + ":" + database;
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url, userName, password);
						logger.info("Oracle Connected.............");
					} catch(Exception e) {
						logger.error("Exception in creating connection object for Oracle:" + e);
					}
				}
				
				/**
				 * Connect to MS SQL Server
				 */
				else if(driver.equals("MSSQL")) {
					if(!fldEmpToken.equals("")) { sqlQuery += fldEmpToken + ", "; }					
					if(!fldInTime.equals("")) { sqlQuery += "CONVERT(VARCHAR(8), " + fldInTime + ", 108) AS " + fldInTime + ", "; }
					if(!fldOutTime.equals("")) {sqlQuery += "CONVERT(VARCHAR(8), " + fldOutTime + ", 108) AS " + fldOutTime + ", "; }
					if(!fldOneTime.equals("")) { sqlQuery += "CONVERT(VARCHAR(8), " + fldOneTime + ", 108) AS " + fldOneTime + ", "; }
					if(!fldIOFlag.equals("")) {sqlQuery += fldIOFlag + ", "; }
					if(!fldWorkHrs.equals("")) {sqlQuery += "CONVERT(VARCHAR(8), " + fldWorkHrs + ", 108) AS " + fldWorkHrs + ", "; }
					if(!fldShift.equals("")) {sqlQuery += fldShift + ", "; }
					if(!fldAttendanceDate.equals("")) { sqlQuery += "CONVERT(VARCHAR(10), " + fldAttendanceDate + ", 105) AS " + fldAttendanceDate; }
					
					sqlQuery += " FROM " + tableName;
					
					driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					url = "jdbc:sqlserver://" + server + ";databaseName=" + database + ";user=" + userName + ";password=" + password + ";";
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						logger.info("SQL Server Connected.............");
					} catch(Exception e) {
						logger.error("Exception in creating connection for MS SQL:" + e);
					}					
				}
				
				/**
				 * Connect to My SQL
				 */
				else if(driver.equals("MYSQL")) {
					if(!fldEmpToken.equals("")) { sqlQuery += fldEmpToken + ", "; }					
					if(!fldInTime.equals("")) { sqlQuery += "TIME_FORMAT(" + fldInTime + ", '%H:%i:%S') AS " + fldInTime + ", "; }
					if(!fldOutTime.equals("")) {sqlQuery += "TIME_FORMAT(" + fldOutTime + ", '%H:%i:%S') AS " + fldOutTime + ", "; }
					if(!fldOneTime.equals("")) { sqlQuery += "TIME_FORMAT(" + fldOneTime + ", '%H:%i:%S') AS " + fldOneTime + ", "; }
					if(!fldIOFlag.equals("")) {sqlQuery += fldIOFlag + ", "; }
					if(!fldWorkHrs.equals("")) {sqlQuery += "TIME_FORMAT(" + fldWorkHrs + ", '%H:%i:%S') AS " + fldWorkHrs + ", "; }
					if(!fldShift.equals("")) {sqlQuery += fldShift + ", "; }
					if(!fldAttendanceDate.equals("")) { sqlQuery += "DATE_FORMAT(" + fldAttendanceDate + ", '%d-%m-%Y') AS " + fldAttendanceDate; }
					
					sqlQuery += " FROM " + tableName;
					
					driverClass = "com.mysql.jdbc.Driver";
					url = "jdbc:mysql://" + server + ":" + portNo + "/" + database + "?user=" + userName + "&password=" + password;
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						logger.info("My SQL Connected.............");
					} catch(Exception e) {
						logger.error("Exception in creating connection for My SQL:" + e);
					}
				}

				PreparedStatement pst = null;
				ResultSet rs = null;

				try {
					pst = conn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					pst.executeQuery();
					message = "Connection Successfull.";
				} catch(Exception e) {
					logger.error("Exception in executing sql query:" + e);
					try {
						rs.close();
					} catch(Exception e1) {
						logger.error("Exception in closing resultset:" + e1);
					}

					try {
						pst.clearParameters();
						pst.close();
					} catch(Exception e2) {
						logger.error("Exception in closing prepareStatement:" + e2);
					}
				}
			} catch(Exception e) {
				logger.error("Exception in creating connection:" + e);
				
				if(conn != null) {
					conn.close();
				}
			} finally {
				if(conn != null) {
					conn.close();
				}
			}
		} catch(Exception e) {
			logger.error("Exception in testConnection:" + e);
		}
		return message;
	}

	public boolean update(String driver, String server, String userName, String password, String database, String port, String tableName, 
		String autoUploadID) {
		boolean result = true;
		try {
			Object[][] param = new Object[1][8];
			param[0][0] = driver;
			param[0][1] = server;
			param[0][2] = userName;
			param[0][3] = PPEncrypter.encrypt(password);
			param[0][4] = database;
			param[0][5] = port;
			param[0][6] = tableName;
			param[0][7] = autoUploadID;
			
			String insertSql = " UPDATE HRMS_ATTENDANCE_AUTO_UPLOAD SET AUTO_UPLOAD_DRIVER = ?, AUTO_UPLOAD_SERVER = ?, AUTO_UPLOAD_USER_NAME = ?, " +
			" AUTO_UPLOAD_PASSWORD = ?, AUTO_UPLOAD_DATABASE = ?, AUTO_UPLOAD_PORT = ?, AUTO_UPLOAD_TABLE = ? WHERE AUTO_UPLOAD_ID = ? ";
			result = getSqlModel().singleExecute(insertSql, param);
		} catch(Exception e) {
			logger.error("Exception in update:" + e);
		}
		return result;
	}
}