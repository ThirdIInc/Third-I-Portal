/* Bhushan Dasare Feb 26, 2010 */

package org.struts.action.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.model.attendance.UploadAttendanceModel;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.struts.action.ApplicationStudio.jobScheduling.JobLogger;

public class CaptureAttendance implements Job {
	private Connection dbConn = null;
	private String client = "";
	private ArrayList<String> columnNames = null;
	
	UploadAttendanceModel upAttendance=new UploadAttendanceModel();
		
	/*private Object[] calculateLateAndEarlyHrs(String empInTime, String empOutTime, String offcStartTime, String offcEndTime, String firstHalfTime, 
		String secondHalfTime, String nightFlag, String lateMarkTime, String earlyLeavingTime) {
		String status = "";
		//                         late hrs   early hrs             extra hrs   status   in time   out time     work hrs
		Object[] calculateTime = {"00:00:00", "00:00:00", "N", "N", "00:00:00", status, empInTime, empOutTime, "00:00:00"};
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			Date dateEmpInTime = sdf.parse(empInTime); // convert in time employee in to date format
			Date dateEmpOutTime = sdf.parse(empOutTime); // convert in time employee out to date format
			Date dateOffcEndTime = sdf.parse(offcEndTime); // convert shift out time in to date format

			if(lateMarkTime.equals("00:00:00")) {
				lateMarkTime = offcStartTime;
			}
			Date lateMarkStartTime = sdf.parse(lateMarkTime);

			if(firstHalfTime.equals("00:00:00")) {
				firstHalfTime = lateMarkTime;
			}
			Date dateFirstHalfTime = sdf.parse(firstHalfTime); // convert fist half day time in to date format

			if(earlyLeavingTime.equals("00:00:00")) {
				earlyLeavingTime = offcEndTime;
			}
			Date earlyLeavingEndTime = sdf.parse(earlyLeavingTime);

			if(secondHalfTime.equals("00:00:00")) {
				secondHalfTime = earlyLeavingTime;
			}
			Date dateSecondHalfTime = sdf.parse(secondHalfTime); // convert second half day time in to date format

			Calendar c1 = Calendar.getInstance();

			if(((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime.before(dateFirstHalfTime) || dateEmpInTime.equals(dateFirstHalfTime)))
				&& ((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime.equals(dateSecondHalfTime)) 
				&& dateEmpOutTime.before(earlyLeavingEndTime))) {
				calculateTime[3] = "Y";

				c1.setTime(dateEmpInTime);
				c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
				c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
				c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours

				c1.setTime(earlyLeavingEndTime);
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours
				status = "DL";
			} else if(dateEmpInTime.after(dateFirstHalfTime) && !empInTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if(dateEmpOutTime.before(dateSecondHalfTime) && !empOutTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if(((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime.before(dateFirstHalfTime) 
				|| dateEmpInTime.equals(dateFirstHalfTime)))) {
				c1.setTime(dateEmpInTime);
				c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
				c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
				c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours

				status = "LC";
			} else if(((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime.equals(dateSecondHalfTime)) 
				&& dateEmpOutTime.before(earlyLeavingEndTime))) {
				c1.setTime(earlyLeavingEndTime);
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours

				status = "EL";
			} else if((!empInTime.equals("00:00:00") && !empOutTime.equals("00:00:00"))
				&& (dateEmpInTime.before(lateMarkStartTime) || dateEmpInTime.equals(lateMarkStartTime))
				&& (dateEmpOutTime.after(earlyLeavingEndTime) || dateEmpOutTime.equals(earlyLeavingEndTime))) {
				status = "IN";
			} else if((empInTime.equals("00:00:00") && !empOutTime.equals("00:00:00"))
				|| (!empInTime.equals("00:00:00") && empOutTime.equals("00:00:00"))) {
				status = "HD";
			} else {
				status = "AB";
			}

			if(dateEmpOutTime.after(dateOffcEndTime)) {
				c1.setTime(dateEmpOutTime);
				c1.add(Calendar.HOUR, -dateOffcEndTime.getHours());
				c1.add(Calendar.MINUTE, -dateOffcEndTime.getMinutes());
				c1.add(Calendar.SECOND, -dateOffcEndTime.getSeconds());

				calculateTime[4] = sdf.format(c1.getTime()); // extra hours
			}

			if(nightFlag.equals("Y")) {
				int inTime = Integer.parseInt((empInTime.substring(0, 5)).replaceAll(":", ""));
				int outTime = Integer.parseInt((empOutTime.substring(0, 5)).replaceAll(":", ""));
				int startTime = Integer.parseInt((offcStartTime.substring(0, 5)).replaceAll(":", ""));
				int endTime = Integer.parseInt((offcEndTime.substring(0, 5)).replaceAll(":", ""));
				int fstHalfTime = Integer.parseInt((firstHalfTime.substring(0, 5)).replaceAll(":", ""));
				int scdHalfTime = Integer.parseInt((secondHalfTime.substring(0, 5)).replaceAll(":", ""));

				int nightlateMarkTime = Integer.parseInt((lateMarkTime.substring(0, 5)).replaceAll(":", ""));
				int nightearlyLeavingEndTime = Integer.parseInt((earlyLeavingTime.substring(0, 5)).replaceAll(":", ""));

				if(inTime < 1200) {
					inTime += 2400;
				}
				if(outTime < 1200) {
					outTime += 2400;
				}
				if(startTime < 1200) {
					startTime += 2400;
				}
				if(endTime < 1200) {
					endTime += 2400;
				}
				if(fstHalfTime < 1200) {
					fstHalfTime += 2400;
				}
				if(scdHalfTime < 1200) {
					scdHalfTime += 2400;
				}

				if(nightlateMarkTime < 1200) {
					nightlateMarkTime += 2400;
				}

				if(nightearlyLeavingEndTime < 1200) {
					nightearlyLeavingEndTime += 2400;
				}

				if((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))
					&& ((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
					status = "DL";
				} else if(inTime > fstHalfTime) {
					status = "HD";
				} else if(outTime < scdHalfTime) {
					status = "HD";
				} else if((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
					status = "LC";
				} else if(((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
					status = "EL";
				} else if(inTime > 0 && outTime > 0 && (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
					&& (outTime == nightearlyLeavingEndTime) || outTime > nightearlyLeavingEndTime) {
					status = "IN";
				} else {
					status = "AB";
				}
			}
			calculateTime[5] = status;
		} catch(Exception e) {
			JobLogger.error(client, "Exception in calculateLateAndEarlyHrs-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return calculateTime;
	}*/

	private int calculateNumberOfDays(String fromDate, String toDate) {
		int daysBetween = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			Date fromDateParse = sdf.parse(fromDate);
			Date toDateParse = sdf.parse(toDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);

			Calendar calTo = Calendar.getInstance();
			calTo.setTime(toDateParse);

			while(cal.before(calTo) || cal.equals(calTo)) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				daysBetween++;
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in calculateNumberOfDays-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return daysBetween;
	}

	private String calWorkHr(String workHr) {
		int hours = Integer.parseInt(workHr.substring(0, 2));
		int min = Integer.parseInt(workHr.substring(2, 4));
		if(min > 59) {
			int addHr = 0;
			addHr = min / 60;
			hours += addHr;
			min = min % 60;
		}
		String totalHr = hours + ":" + min;
		return totalHr;
	}

	private void captureAttendanceData(String[] fromDateToDate, String autoUploadID) {
		try {
			Object[][] autoUploadSettings = getAutoUploadSettings(autoUploadID);

			if(autoUploadSettings != null && autoUploadSettings.length > 0) {
				for(int i = 0; i < autoUploadSettings.length; i++) {
					String driver = String.valueOf(autoUploadSettings[i][0]);
					String server = String.valueOf(autoUploadSettings[i][1]);
					String port = String.valueOf(autoUploadSettings[i][2]);
					String userName = String.valueOf(autoUploadSettings[i][3]);
					String password = PPEncrypter.decrypt(String.valueOf(autoUploadSettings[i][4]));
					String database = String.valueOf(autoUploadSettings[i][5]);
					String tableName = String.valueOf(autoUploadSettings[i][6]);

					Object[][] autoUploadDtlSettings = getAutoUploadDtlSettings(autoUploadID);

					if(autoUploadDtlSettings != null && autoUploadDtlSettings.length > 0) {
						String fldEmpToken = "", fldAttendanceDate = "", fldInTime = "", fldOutTime = "", fldOneTime = "", fldIOFlag = ""; 
						String fldWorkHrs = "", fldShift = "";

						for(int j = 0; j < autoUploadDtlSettings.length; j++) {
							String fieldType = String.valueOf(autoUploadDtlSettings[j][0]).replaceAll(" ", "");
							boolean flag = Boolean.parseBoolean(String.valueOf(autoUploadDtlSettings[j][1]));

							if(flag) {
								String field = String.valueOf(autoUploadDtlSettings[j][2]);

								if(fieldType.equals("EmployeeNumber")) { fldEmpToken = field; }
								else if(fieldType.equals("Date")) { fldAttendanceDate = field; }
								else if(fieldType.equals("InTime")) { fldInTime = field; }
								else if(fieldType.equals("OutTime")) { fldOutTime = field; }
								else if(fieldType.equals("Time")) { fldOneTime = field; }
								else if(fieldType.equals("I/OFlag")) { fldIOFlag = field; }
								else if(fieldType.equals("WorkHours")) { fldWorkHrs = field; }
								else if(fieldType.equals("Shift")) { fldShift = field; }
							}
						}

						String fromDate = fromDateToDate[0], toDate = fromDateToDate[1];

						Object[][] retrievedAttendance = retrieveAttendance(driver, server, userName, password, database, port, tableName, 
							fromDate, toDate, fldEmpToken, fldAttendanceDate, fldInTime, fldOutTime, fldOneTime, fldIOFlag, fldWorkHrs, fldShift);

						Object[][] attendanceData = copyRetrievedAttendance(retrievedAttendance, fldEmpToken, fldAttendanceDate, fldInTime, 
							fldOutTime, fldOneTime, fldIOFlag, fldWorkHrs, fldShift);
						
						String result = "";
						
						if(attendanceData != null && attendanceData.length > 0) {
							result = insertData(attendanceData, fromDate, toDate);
							
							if(result == "uploaded") {
								//processAttendance(attendanceData, fromDate, toDate, fldShift, fldOneTime, fldWorkHrs);
								
								/*calling UploadAttendanceModel class processAttendance function
								 * pass parameter uploadType as autoSchedule and connection object
								 *  */
								
								upAttendance.processAttendance(attendanceData, fromDate, toDate, fldShift, fldOneTime, fldWorkHrs,"autoSchedule","","",dbConn);
							}
														
						}
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in captureAttendanceData-" + e);
			JobLogger.printStackTrace(client, e);
		}
	}
	
	private String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	private Object[][] copyRetrievedAttendance(Object[][] retrievedAttendance, String fldEmpToken, String fldAttendanceDate, String fldInTime, 
		String fldOutTime, String fldOneTime, String fldIOFlag, String fldWorkHrs, String fldShift) {
		Object[][] attendanceData = null;
		try {
			if(retrievedAttendance != null && retrievedAttendance.length > 0) {
				attendanceData = new Object[retrievedAttendance.length][8];

				for(int i = 0; i < retrievedAttendance.length; i++) {
					/**
					 * Copy Employee Token
					 */
					boolean isEmpTokenExists = false;
					
					if(!fldEmpToken.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldEmpToken.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][0] = retrievedAttendance[i][j];
								isEmpTokenExists = true;
								break;
							}
						}
					}
					
					if(!isEmpTokenExists) {
						attendanceData[i][0] = "";
					}
					
					
					/**
					 * Copy Attendance Date
					 */
					boolean isAttnDateExists = false;

					if(!fldAttendanceDate.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldAttendanceDate.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][1] = retrievedAttendance[i][j];
								isAttnDateExists = true;
								break;
							}
						}
					}
					
					if(!isAttnDateExists) {
						attendanceData[i][1] = "";
					}
					
					
					/**
					 * Copy In Time
					 */
					boolean isInTimeExists = false;
					
					if(!fldInTime.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldInTime.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][2] = retrievedAttendance[i][j];
								isInTimeExists = true;
								break;
							}
						}
					}
					
					if(!isInTimeExists) {
						attendanceData[i][2] = "";
					}
					
					/**
					 * Copy Out Time
					 */
					boolean isOutTimeExists = false;
					
					if(!fldOutTime.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldOutTime.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][3] = retrievedAttendance[i][j];
								isOutTimeExists = true;
								break;
							}
						}
					}
					
					if(!isOutTimeExists) {
						attendanceData[i][3] = "";
					}
					
					/**
					 * Copy One Time
					 */
					boolean isOneTimeExists = false;
					
					if(!fldOneTime.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldOneTime.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][4] = retrievedAttendance[i][j];
								isOneTimeExists = true;
								break;
							}
						}
					}
					
					if(!isOneTimeExists) {
						attendanceData[i][4] = "";
					}
					
					/**
					 * Copy IO Flag
					 */
					boolean isIOFlagExists = false;
					
					if(!fldIOFlag.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldIOFlag.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][5] = retrievedAttendance[i][j];
								isIOFlagExists = true;
								break;
							}
						}
					}
					
					if(!isIOFlagExists) {
						attendanceData[i][5] = "";
					}
					
					/**
					 * Copy Work Hrs
					 */
					boolean isWorkHrsExists = false;
					
					if(!fldWorkHrs.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldWorkHrs.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][6] = retrievedAttendance[i][j];
								isWorkHrsExists = true;
								break;
							}
						}
					}
					
					if(!isWorkHrsExists) {
						attendanceData[i][6] = "";
					}
					
					/**
					 * Copy Shift
					 */
					boolean isShiftExists = false;
					
					if(!fldShift.equals("")) {
						for(int j = 0; j < columnNames.size(); j++) {
							if(fldShift.equalsIgnoreCase(columnNames.get(j))) {
								attendanceData[i][7] = retrievedAttendance[i][j];
								isShiftExists = true;
								break;
							}
						}
					}
					
					if(!isShiftExists) {
						attendanceData[i][7] = "";
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in copyRetrievedAttendance-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return attendanceData;
	}

	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
			System.out.println();
			String autoUploadID = jobExecutionContext.getJobDetail().getJobDataMap().getString("NAME").split("_")[1];

			if(!(client == null || client.equals("null") || client.equals(null))) {
				ResourceBundle bundle = ResourceBundle.getBundle("org.paradyne.lib.ConnectionModel");
				
				String dbDriver = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME, 
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle.getString(client + ".driver"));
				String dbUrl = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME, 
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle.getString(client + ".url"));
				String dbUsername = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME, 
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle.getString(client + ".username"));
				String dbPassword = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME, 
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle.getString(client + ".password"));
				
				try {
					if(dbDriver != null) {
						Class.forName(dbDriver);
						dbConn = java.sql.DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
						
						String[] fromDateToDate = getFromDateToDate(jobExecutionContext);
						
						captureAttendanceData(fromDateToDate, autoUploadID);
					}
				} catch(Exception e) {
					JobLogger.error(client, "Exception in execute-" + e);
					JobLogger.printStackTrace(client, e);
				} finally {
					if(dbConn != null) { dbConn.close(); }
				}
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in execute-" + e);
			JobLogger.printStackTrace(client, e);
		}
	}

	private Object[][] getAttendanceData(int day, Object[][] empShiftData, String fromDate, String fldShift, String fldOneTime, String fldWorkHrs) {
		String date = incrementDate(fromDate, day);
		boolean flag = false;

		// select attendance data from HRMS_ATTENDANCE_TEMP along with shift
		// details
		String query = " SELECT DISTINCT TEMP_ATT_EMP_CODE AS EMP_TOKEN, EMP_ID, TO_CHAR(TEMP_ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, ";
		if (!fldShift.equals("")) {
			query += " SHIFT.SHIFT_ID, NVL(TO_CHAR(SHIFT.SFT_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME, "
					+ " NVL(TO_CHAR(SHIFT.SFT_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME, "
					+ " NVL(TO_CHAR(SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF, "
					+ " NVL(TO_CHAR(SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF ";
		} else {
			query += " EMP_SHIFT, NVL(TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF ";
		}
		if (!fldOneTime.equals("")) {
			query += ", MIN(NVL(TO_CHAR(TEMP_ATT_ONE_TIME, 'HH24:MI:SS'), '00:00:00')) AS IN_TIME, "
					+ " MAX(NVL(TO_CHAR(TEMP_ATT_ONE_TIME, 'HH24:MI:SS'), '00:00:00')) AS OUT_TIME ";
		} else {
			query += ", NVL(TO_CHAR(TEMP_ATT_IN_TIME, 'HH24:MI:SS'), '00:00:00') AS IN_TIME, "
					+ " NVL(TO_CHAR(TEMP_ATT_OUT_TIME, 'HH24:MI:SS'), '00:00:00') AS OUT_TIME ";
		}
		if (!fldWorkHrs.equals("")) {
			query += ", NVL(TO_CHAR(TEMP_ATT_WORK_HRS, 'HH24:MI:SS'), '00:00:00') AS WORK_HRS ";
		} else if (!fldOneTime.equals("")) {
			query += ", NVL(TO_CHAR(TO_DATE(TRUNC(MOD((TO_DATE(TO_CHAR(MAX(TEMP_ATT_ONE_TIME), 'HH24:MI:SS'), 'HH24:MI:SS')- "
					+ " TO_DATE(TO_CHAR(MIN(TEMP_ATT_ONE_TIME), 'HH24:MI:SS'), 'HH24:MI:SS')) * 24, 24))|| ':' || "
					+ " TRUNC(MOD((TO_DATE(TO_CHAR(MAX(TEMP_ATT_ONE_TIME), 'HH24:MI:SS'), 'HH24:MI:SS') - "
					+ " TO_DATE(TO_CHAR(MIN(TEMP_ATT_ONE_TIME),'HH24:MI:SS'), 'HH24:MI:SS')) * 24 * 60, 60)) || ':' || "
					+ " TRUNC(MOD((TO_DATE(TO_CHAR(MAX(TEMP_ATT_ONE_TIME), 'HH24:MI:SS'), 'HH24:MI:SS') - "
					+ " TO_DATE(TO_CHAR(MIN(TEMP_ATT_ONE_TIME),'HH24:MI:SS'), 'HH24:MI:SS')) * 24 * 60 * 60, 60)), 'HH24:MI:SS'), 'HH24:MI:SS'), '00:00:00') "
					+ " AS WORK_HRS ";
		} else {
			query += " , 0 ";
		}
		if (!fldShift.equals("")) {
			query += ", TEMP_ATT_SHIFT ";
		} else {
			query += ", SHIFT_NAME ";
		}
		query += " , EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, NVL(SFT_NIGHT_FLAG, 'N') AS SFT_NIGHT_FLAG, ";
			
				
		
		
		if (!fldShift.equals("")) {
			query += " NVL(TO_CHAR(SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, "
			+ " NVL(TO_CHAR(SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING ";
			
			query += " , NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME_DTL, "
					+ " NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME_DTL, "
					+ " NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF_DTL, "
					+ " NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF_DTL, "
					+ " NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK_DTL, "
					+ " NVL(TO_CHAR(SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING_DTL ";
		} else {
			query += " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, "
			+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING ";
			query += " , NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING_DTL ";
		}
		query += " FROM HRMS_ATTENDANCE_TEMP "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_TOKEN = HRMS_ATTENDANCE_TEMP.TEMP_ATT_EMP_CODE) "
				+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) "
				+ " LEFT JOIN HRMS_SHIFT SHIFT ON(SHIFT.SHIFT_NAME = HRMS_ATTENDANCE_TEMP.TEMP_ATT_SHIFT ) "
				+ " LEFT JOIN HRMS_SHIFT_DTL ON (HRMS_SHIFT_DTL.SHIFT_ID = HRMS_SHIFT.SHIFT_ID "
				+ " AND HRMS_SHIFT_DTL.SFT_DTL_EXCEPTIONAL_DAY = TO_NUMBER(DECODE(TRIM(TO_CHAR(TEMP_ATT_DATE, 'DAY')), 'SUNDAY', 1, 'MONDAY', 2, "
				+ " 'TUESDAY', 3, 'WEDNESDAY', 4, 'THURSDAY', 5, 'FRIDAY', 6, 'SATURDAY', 7))) "
				+ " LEFT JOIN HRMS_SHIFT_DTL SHIFT_DTL ON (SHIFT_DTL.SHIFT_ID = SHIFT.SHIFT_ID "
				+ " AND SHIFT_DTL.SFT_DTL_EXCEPTIONAL_DAY = TO_NUMBER(DECODE(TRIM(TO_CHAR(TEMP_ATT_DATE, 'DAY')), 'SUNDAY', 1, 'MONDAY', 2, 'TUESDAY', 3, "
				+ " 'WEDNESDAY', 4, 'THURSDAY', 5, 'FRIDAY', 6, 'SATURDAY', 7))) "
				+ " WHERE TEMP_ATT_DATE = TO_DATE('"
				+ date
				+ "', 'DD-MM-YYYY') "
				+ " GROUP BY TEMP_ATT_EMP_CODE, SFT_NIGHT_FLAG, EMP_ID, TEMP_ATT_DATE, EMP_SHIFT, HRMS_SHIFT.SFT_START_TIME, TEMP_ATT_IN_TIME, "
				+ " TEMP_ATT_OUT_TIME, HRMS_SHIFT.SFT_END_TIME, HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, TEMP_ATT_WORK_HRS, "
				+ " SHIFT.SHIFT_ID, SHIFT.SFT_START_TIME, SHIFT.SFT_END_TIME, SHIFT.SFT_IN_HD_AFTER_TIME, SHIFT.SFT_OUT_HD_BEFORE_TIME, TEMP_ATT_SHIFT, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,";
		if (!fldShift.equals("")) {
			query += " SHIFT.SFT_IN_LM_AFTER_TIME, SHIFT.SFT_OUT_EL_BEFORE_TIME, TEMP_ATT_SHIFT ";
		} else {
			query += " HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME , SHIFT_NAME ";
		}
		query += " , HRMS_SHIFT_DTL.SFT_DTL_START_TIME, HRMS_SHIFT_DTL.SFT_DTL_END_TIME, HRMS_SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, "
				+ " HRMS_SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, HRMS_SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, HRMS_SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, "
				+ " SHIFT_DTL.SFT_DTL_START_TIME, SHIFT_DTL.SFT_DTL_END_TIME, SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, "
				+ " SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME ORDER BY TEMP_ATT_EMP_CODE ";
		Object[][] getAttendanceData = getSingleResult(query);

		Object[][] newData = null;

		if (getAttendanceData != null && getAttendanceData.length != 0) {
			// match employee code retrieved from HRMS_ATT_TEMP table with
			// employee id from HRMS_EMP_OFFC
			if (empShiftData != null && empShiftData.length != 0) {
				newData = new Object[empShiftData.length][16];

				for (int j = 0; j < empShiftData.length; j++) {
					for (int k = 0; k < getAttendanceData.length; k++) {
						if (String.valueOf(getAttendanceData[k][0]).equals(
								String.valueOf(empShiftData[j][0]))) {
							flag = true;

							boolean isShiftExceptionExists = false;
							String shiftDtlStartTime = String
									.valueOf(getAttendanceData[k][16]);
							String shiftDtlEndTime = String
									.valueOf(getAttendanceData[k][17]);
							if (!(shiftDtlStartTime.equals("00:00:00") || shiftDtlEndTime
									.equals("00:00:00"))) {
								isShiftExceptionExists = true;
							}

							newData[j][0] = getAttendanceData[k][0]; // employee
																		// token
							newData[j][1] = getAttendanceData[k][1]; // employee
																		// code
							newData[j][2] = getAttendanceData[k][2]; // attendance
																		// date
							newData[j][3] = getAttendanceData[k][3]; // shift
																		// id
							if (isShiftExceptionExists) {
								newData[j][4] = shiftDtlStartTime; // shift
																	// exception
																	// start
																	// time
								newData[j][5] = shiftDtlEndTime; // shift
																	// exception
																	// end time
								newData[j][6] = getAttendanceData[k][18]; // shift
																			// exception
																			// first
																			// half
																			// day
								newData[j][7] = getAttendanceData[k][19]; // shift
																			// exception
																			// second
																			// half
																			// day
							} else {
								newData[j][4] = getAttendanceData[k][4]; // shift
																			// start
																			// time
								newData[j][5] = getAttendanceData[k][5]; // shift
																			// end
																			// time
								newData[j][6] = getAttendanceData[k][6]; // shift
																			// first
																			// half
																			// day
								newData[j][7] = getAttendanceData[k][7]; // shift
																			// second
																			// half
																			// day
							}
							newData[j][8] = getAttendanceData[k][8]; // in
																		// time
							newData[j][9] = getAttendanceData[k][9]; // out
																		// time
							newData[j][10] = getAttendanceData[k][10]; // work
																		// hours
							if (!fldWorkHrs.equals("")) {
							} else if (!fldOneTime.equals("")) {
							} else {
								newData[j][10] = getShiftWorkHrs(String
										.valueOf(getAttendanceData[k][8]),
										String.valueOf(getAttendanceData[k][9]));
							}
							if (!fldShift.equals("")) {
								newData[j][11] = getAttendanceData[k][11]; // shift
																			// name
								newData[j][12] = getAttendanceData[k][12]; // employee
																			// name
							}
							newData[j][13] = getAttendanceData[k][13]; // Night
																		// flag
							if (isShiftExceptionExists) {
								newData[j][14] = getAttendanceData[k][20]; // shift
																			// dtl
																			// late
																			// coming
								newData[j][15] = getAttendanceData[k][21]; // shift
																			// dtl
																			// early
																			// leaving
							} else {
								newData[j][14] = getAttendanceData[k][14]; // shift
																			// late
																			// coming
								newData[j][15] = getAttendanceData[k][15]; // shift
																			// early
																			// leaving
							}

							break;
						} else {
							flag = false;
						}
					}

					// If employee is not available in emp office, then insert
					// default values
					if (!flag) {
						newData[j][0] = empShiftData[j][0]; // employee token
						newData[j][1] = empShiftData[j][1]; // employee code
						newData[j][2] = date; // attendance date
						newData[j][3] = empShiftData[j][2]; // shift id
						newData[j][4] = empShiftData[j][3]; // shift start time
						newData[j][5] = empShiftData[j][4]; // shift end time
						newData[j][6] = empShiftData[j][5]; // shift first half
															// day
						newData[j][7] = empShiftData[j][6]; // shift second half
															// day
						newData[j][8] = "00:00:00"; // in time
						newData[j][9] = "00:00:00"; // out time
						newData[j][10] = "00:00:00"; // work hours
						newData[j][11] = empShiftData[j][7]; // shift name
						newData[j][12] = empShiftData[j][8]; // employee name
						newData[j][13] = empShiftData[j][11]; // Night flag
						newData[j][14] = empShiftData[j][9]; // LATE MARK
																// START TIME
						newData[j][15] = empShiftData[j][10]; // EARLY LEAVING
																// END TIME
					}
				}
			}
		}
		return newData;
	}

	private Object[][] getAutoUploadDtlSettings(String autoUploadID) {
		Object[][] autoUploadDtlSettings = null;
		try {
			String autoUploadDtlSettingsSql = " SELECT AUTO_UPLOAD_DTL_FIELDTYPE, " +
			" DECODE(AUTO_UPLOAD_DTL_FLAG, 'Y', 'true', 'N', 'false') AS AUTO_UPLOAD_DTL_FLAG, AUTO_UPLOAD_DTL_FIELD " +
			" FROM HRMS_ATT_AUTO_UPLOAD_DTL WHERE AUTO_UPLOAD_ID = " + autoUploadID + " ORDER BY AUTO_UPLOAD_DTL_ID ";
			autoUploadDtlSettings = getSingleResult(autoUploadDtlSettingsSql);
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getAutoUploadDtlSettings-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return autoUploadDtlSettings;
	}

	private Object[][] getAutoUploadSettings(String autoUploadID) {
		Object[][] autoUploadSettings = null;
		try {
			String autoUploadSettingsSql = " SELECT AUTO_UPLOAD_DRIVER, AUTO_UPLOAD_SERVER, AUTO_UPLOAD_PORT, AUTO_UPLOAD_USER_NAME, " +
			" AUTO_UPLOAD_PASSWORD, AUTO_UPLOAD_DATABASE, AUTO_UPLOAD_TABLE FROM HRMS_ATTENDANCE_AUTO_UPLOAD " +
			" WHERE AUTO_UPLOAD_ID = " + autoUploadID + " ORDER BY AUTO_UPLOAD_ID ";
			autoUploadSettings = getSingleResult(autoUploadSettingsSql);
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getAutoUploadSettings-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return autoUploadSettings;
	}

	private Object[] getCalendarDate(String fromDate, int daysBetween) {
		Object[] newDates = null;
		try {
			String[] fromDateStr = fromDate.split("-");
			int year = Integer.parseInt(fromDateStr[2]) - 1900;
			int month = Integer.parseInt(fromDateStr[1]) - 1;
			int day = Integer.parseInt(fromDateStr[0]) - 1;

			Calendar cal = Calendar.getInstance();
			Date d = new Date(year, month, day);
			cal.setTime(d);

			newDates = new Object[daysBetween];
			for(int i = 0; i < daysBetween; i++) {
				cal.add(Calendar.DAY_OF_MONTH, 1);

				String newDay = Integer.parseInt(String.valueOf(cal.get(Calendar.DATE))) < 10 ? "0" + String.valueOf(cal.get(Calendar.DATE)) : 
					String.valueOf(cal.get(Calendar.DATE));
				String newMonth = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH))) < 10 ? "0" + String.valueOf(cal.get(Calendar.MONTH)) : 
					String.valueOf(cal.get(Calendar.MONTH));

				int intAttMonth = Integer.parseInt(newMonth) + 1;
				String strAttMonth = intAttMonth < 10 ? "0" + String.valueOf(intAttMonth) : String.valueOf(intAttMonth);
				newDates[i] = newDay + "-" + strAttMonth + "-" + cal.get(Calendar.YEAR);
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getCalendarDate-" + e);
			JobLogger.printStackTrace(client, e);
		}		
		return newDates;
	}

	private String[] getFromDateToDate(JobExecutionContext jobExecutionContext) {
		String[] fromDateToDate = new String[2];
		try {
			String duration = jobExecutionContext.getJobDetail().getJobDataMap().getString("DURATION");
			String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String fromDate = "", toDate = currentDate;
			
			if(duration.equalsIgnoreCase("Daily")) {
				fromDate = currentDate;
			} else if(duration.equalsIgnoreCase("Weekly")) {
				fromDate = getWeeklyFromDate();
			} else if(duration.equalsIgnoreCase("Monthly")) {
				fromDate = getMonthlyFromDate();
			}
			
			fromDateToDate[0] = fromDate;
			fromDateToDate[1] = toDate;
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getFromDateToDate-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return fromDateToDate;
	}
	
	private String getMonthlyFromDate() {
		String fromDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			
			int calDate = cal.get(Calendar.DAY_OF_MONTH);
			int calMonth = cal.get(Calendar.MONTH) + 1;
			int calYear = cal.get(Calendar.YEAR);
			
			int monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			String dayOfMonth = calDate < 10 ? "0" + calDate : String.valueOf(calDate);
			String month = calMonth < 10 ? "0" + calMonth : String.valueOf(calMonth);
			String year = calYear < 10 ? "0" + calYear : String.valueOf(calYear);
			
			fromDate = dayOfMonth + "-" + month + "-" + year;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse(fromDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -(monthDays - 1));
			
			calDate = cal.get(Calendar.DAY_OF_MONTH);
			calMonth = cal.get(Calendar.MONTH) + 1;
			calYear = cal.get(Calendar.YEAR);
			
			dayOfMonth = calDate < 10 ? "0" + calDate : String.valueOf(calDate);
			month = calMonth < 10 ? "0" + calMonth : String.valueOf(calMonth);
			year = calYear < 10 ? "0" + calYear : String.valueOf(calYear);
			
			fromDate = dayOfMonth + "-" + month + "-" + year;
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getMonthlyFromDate-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return fromDate;
	}
	
	private String getShiftWorkHrs(String inTimeStr, String outTimeStr) {
		int inTimehr = Integer.parseInt(inTimeStr.replaceAll(":", "").substring(0, 2));
		int outTimehr = Integer.parseInt(outTimeStr.replaceAll(":", "").substring(0, 2));

		if(outTimehr < inTimehr) {
			outTimehr += 24;
		}

		int inTimemin = Integer.parseInt(inTimeStr.replaceAll(":", "").substring(2, 4));
		int outTimemin = Integer.parseInt(outTimeStr.replaceAll(":", "").substring(2, 4));

		if(outTimemin == 0 && inTimemin > 0) {
			outTimemin = 60;
			inTimehr = inTimehr + 1;
		}
		if(inTimemin > outTimemin) {
			outTimemin = outTimemin + 60;
			inTimehr = inTimehr + 1;
		}

		String hrStr = "";
		String minStr = "";
		int hour = outTimehr - inTimehr;
		int min = outTimemin - inTimemin;

		if(min <= 9) {
			minStr = "0" + min;
		} else {
			minStr = "" + min;
		}

		if(hour <= 9) {
			hrStr = "0" + hour;
		} else {
			hrStr = "" + hour;
		}

		String workHr = hrStr + "" + minStr;

		String[] splitHrMin = calWorkHr(workHr).split(":");
		String actHour = splitHrMin[0];
		String actMin = splitHrMin[1];

		if(Integer.parseInt(splitHrMin[1]) <= 9) {
			actMin = "0" + splitHrMin[1];
		}

		String actWorkhr = actHour + ":" + actMin;
		return actWorkhr;
	}

	public Object[][] getSingleResult(String sqlQuery) {
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {
			JobLogger.info(client, "sqlQuery-" + sqlQuery);

			PST = dbConn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
			} catch(Exception e) {
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while(rs.next()) {
				for(int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if(ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1, Integer.parseInt("" + clob.length()));
						} catch(Exception e) {
							JobLogger.error(client, "Exception in getSingleResult-" + e);
							JobLogger.printStackTrace(client, e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getSingleResult-" + e);
			JobLogger.printStackTrace(client, e);
		} finally {
			try {
				rs.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in getSingleResult-" + e);
				JobLogger.printStackTrace(client, e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in getSingleResult-" + e);
				JobLogger.printStackTrace(client, e);
			}
		}
		return twoDimObjArr;
	}

	private Object[][] getSingleResult(String sqlQuery, Object[] parameterObj) {
		Object[][] twoDimObjArr = null;
		java.sql.PreparedStatement PST = null;
		java.sql.ResultSet rs = null;
		try {
			/**
			 * Get & prepare the query
			 */
			PST = dbConn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			/**
			 * Apply the parameters
			 */
			for(int j = 0; j < parameterObj.length; j++) {
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
			} catch(Exception e) {
				twoDimObjArr = new Object[1][numberOfColumns];
			}

			/**
			 * Mapping the resultSet
			 */
			int rowNumber = 0;
			while(rs.next()) {
				for(int i = 0; i < numberOfColumns; i++) {
					int ctype = rsmd.getColumnType(i + 1);
					if(ctype == java.sql.Types.CLOB) {
						try {
							java.sql.Clob clob = rs.getClob(i + 1);
							twoDimObjArr[rowNumber][i] = clob.getSubString(1, Integer.parseInt("" + clob.length()));
						} catch(Exception e) {
							JobLogger.error(client, "Exception in getSingleResult-" + e);
							JobLogger.printStackTrace(client, e);
						}
					} else {
						twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
					}
				}
				rowNumber++;
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getSingleResult-" + e);
			JobLogger.printStackTrace(client, e);
		} finally {
			try {
				rs.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in getSingleResult-" + e);
				JobLogger.printStackTrace(client, e);
			}
			try {
				PST.clearParameters();
				PST.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in getSingleResult-" + e);
				JobLogger.printStackTrace(client, e);
			}
		}
		return twoDimObjArr;
	}

	private String getWeeklyFromDate() {
		String fromDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			
			int calDate = cal.get(Calendar.DAY_OF_MONTH);
			int calMonth = cal.get(Calendar.MONTH) + 1;
			int calYear = cal.get(Calendar.YEAR);
			
			String dayOfMonth = calDate < 10 ? "0" + calDate : String.valueOf(calDate);
			String month = calMonth < 10 ? "0" + calMonth : String.valueOf(calMonth);
			String year = calYear < 10 ? "0" + calYear : String.valueOf(calYear);
			
			fromDate = dayOfMonth + "-" + month + "-" + year;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse(fromDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -6);
			
			calDate = cal.get(Calendar.DAY_OF_MONTH);
			calMonth = cal.get(Calendar.MONTH) + 1;
			calYear = cal.get(Calendar.YEAR);
			
			dayOfMonth = calDate < 10 ? "0" + calDate : String.valueOf(calDate);
			month = calMonth < 10 ? "0" + calMonth : String.valueOf(calMonth);
			year = calYear < 10 ? "0" + calYear : String.valueOf(calYear);
			
			fromDate = dayOfMonth + "-" + month + "-" + year;
		} catch(Exception e) {
			JobLogger.error(client, "Exception in getWeeklyFromDate-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return fromDate;
	}

	private String incrementDate(String fromDate, int day) {
		String date = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDateParse = sdf.parse(fromDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);
			cal.add(Calendar.DAY_OF_MONTH, day);

			date = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
		} catch(Exception e) {
			JobLogger.error(client, "Exception in incrementDate-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return date;
	}

	public String insertData(Object[][] attendanceData, String fromDate, String toDate) {
		String result = "";
		int count = 0;
		boolean queryResult = false;
		Object[][] attendanceDetail = new Object[1][3];
		String inTime = "", outTime = "", attDate = "", empToken = "";
		int daysBetween = calculateNumberOfDays(fromDate, toDate);

		for(int i = 0; i < attendanceData.length; i++) {
			if(attendanceData[i][2] != null && !attendanceData[i][2].equals("")) {
				count++;
			}
		}

		Object[][] insertData = new Object[count][8];
		int j = 0;
		String concatDelEmpIds = "";
		for(int i = 0; i < attendanceData.length; i++) {
			if(attendanceData[i][1] != null && !attendanceData[i][1].equals("")) {
				empToken = String.valueOf(attendanceData[i][0]);
				attDate = String.valueOf(attendanceData[i][1]);
				inTime = String.valueOf(attendanceData[i][2]);
				outTime = String.valueOf(attendanceData[i][3]);
				/** It returns In And Out Time With Attendance Date */
				attendanceDetail = dateWithTime(empToken, attDate, inTime,
						outTime);

				concatDelEmpIds += "'" + checkNull(String.valueOf(attendanceData[i][0]).trim()) + "',";

				insertData[j][0] = checkNull(String.valueOf(attendanceData[i][0]).trim()); // employee token
				insertData[j][1] = checkNull(String.valueOf(attendanceData[i][1]).trim()); // attendance date
				insertData[j][2] = checkNull(String.valueOf(attendanceDetail[0][1]).trim()); // in time
				insertData[j][3] = checkNull(String.valueOf(attendanceDetail[0][2]).trim()); // out time
				insertData[j][4] = checkNull(String.valueOf(attendanceData[i][4]).trim()); // one time
				insertData[j][5] = checkNull(String.valueOf(attendanceData[i][5]).trim()); // io flag
				insertData[j][6] = checkNull(String.valueOf(attendanceData[i][6]).trim()); // work hours
				insertData[j][7] = checkNull(String.valueOf(attendanceData[i][7]).trim()); // shift

				j++;
			}
		}
		concatDelEmpIds = concatDelEmpIds.substring(0, concatDelEmpIds.length() - 1);

		String deleteQuery = " DELETE FROM HRMS_ATTENDANCE_TEMP " + " WHERE TEMP_ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
		" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + Utility.getConcatenatedIds("TEMP_ATT_EMP_CODE", concatDelEmpIds);
		queryResult = singleExecute(deleteQuery);

		ArrayList<String> empIds = new ArrayList<String>();
		ArrayList<String> shifts = new ArrayList<String>();

		for(int i = 0; i < insertData.length; i++) {
			if(i > 0) {
				if(!String.valueOf(insertData[i - 1][0]).equals(String.valueOf(insertData[i][0]))) {
					empIds.add(String.valueOf(insertData[i][0]));
					shifts.add(String.valueOf(insertData[i][7]));
				}
			} else {
				empIds.add(String.valueOf(insertData[i][0]));
				shifts.add(String.valueOf(insertData[i][7]));
			}
		}

		// SHIFT OR THOSE RECORD WHOSE DATA IS NOT AVIALBALE IN SHEET
		String shiftSql = " SELECT EMP_TOKEN, NVL(SHIFT_NAME, '') AS SHIFT_NAME FROM HRMS_EMP_OFFC " +
		" INNER JOIN HRMS_SHIFT ON (HRMS_EMP_OFFC.EMP_SHIFT = SHIFT_ID) " + 
		" WHERE 1 = 1 " + Utility.getConcatenatedIds("EMP_TOKEN", concatDelEmpIds);
		Object[][] shiftData = getSingleResult(shiftSql);

		HashMap<String, String> shiftMap = new HashMap<String, String>();
		if(shiftData != null && shiftData.length > 0) {
			for(int i = 0; i < shiftData.length; i++) {
				shiftMap.put(String.valueOf(shiftData[i][0]), String.valueOf(shiftData[i][1]));
			}
		}

		Object[] newDates = getCalendarDate(fromDate, daysBetween);
		Object[][] newAttendanceData = null;

		try {
			newAttendanceData = new Object[newDates.length * empIds.size()][8];
			int cnt = 0;
			
			for(int i = 0; i < empIds.size(); i++) {
				for(int k = 0; k < newDates.length; k++) {
					for(int l = 0; l < insertData.length; l++) {
						String empId = String.valueOf(insertData[l][0]);
						String date = String.valueOf(insertData[l][1]);
						
						if(empId.equals(empIds.get(i)) && date.equals(String.valueOf(newDates[k]))) {
							newAttendanceData[cnt][0] = empId; // employee code
							newAttendanceData[cnt][1] = date; // attendance date
							newAttendanceData[cnt][2] = insertData[l][2]; // in time
							newAttendanceData[cnt][3] = insertData[l][3]; // out time
							newAttendanceData[cnt][4] = insertData[l][4]; // work hours
							newAttendanceData[cnt][5] = insertData[l][5]; // one time
							newAttendanceData[cnt][6] = insertData[l][6]; // io flag
							newAttendanceData[cnt][7] = insertData[l][7]; // shift

							break;
						} else {
							newAttendanceData[cnt][0] = empIds.get(i); // employee code
							newAttendanceData[cnt][1] = newDates[k]; // attendance date
							newAttendanceData[cnt][2] = null; // in time
							newAttendanceData[cnt][3] = null; // out time
							newAttendanceData[cnt][4] = null; // work hours
							newAttendanceData[cnt][5] = null; // one time
							newAttendanceData[cnt][6] = null; // io flag
							newAttendanceData[cnt][7] = shiftMap.get(String.valueOf(newAttendanceData[cnt][0])); // shift
						}
					}
					cnt++;
				}
			}			
		} catch(Exception e) {
			JobLogger.error(client, "Exception in insertData-" + e);
			JobLogger.printStackTrace(client, e);
		}

		if(newAttendanceData != null && newAttendanceData.length > 0) {
			try {
				/*String insertAttendnaceTemp = " INSERT INTO HRMS_ATTENDANCE_TEMP (TEMP_ATT_EMP_CODE, TEMP_ATT_DATE, TEMP_ATT_IN_TIME, " +
				" TEMP_ATT_OUT_TIME, TEMP_ATT_ONE_TIME, TEMP_ATT_IO_FLAG, TEMP_ATT_WORK_HRS, TEMP_ATT_SHIFT) " +
				" VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, " +
				" TO_DATE(?, 'HH24:MI:SS'), ?) ";
				queryResult = singleExecute(insertAttendnaceTemp, newAttendanceData);
			*/
				String insertAttendnaceTemp = "INSERT INTO HRMS_ATTENDANCE_TEMP (TEMP_ATT_EMP_CODE, TEMP_ATT_DATE, TEMP_ATT_IN_TIME, TEMP_ATT_OUT_TIME, " 
											+ " TEMP_ATT_WORK_HRS, TEMP_ATT_ONE_TIME, TEMP_ATT_IO_FLAG, TEMP_ATT_SHIFT) " 
											+ " VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), "
											+ " TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'),TO_DATE(?,'HH24:MI:SS'),?,?)";
				queryResult = singleExecute(insertAttendnaceTemp, newAttendanceData);
			
			} catch(Exception e) {
				JobLogger.error(client, "Exception in insertData-" + e);
				JobLogger.printStackTrace(client, e);
			}
		}
		
		if(queryResult) {
			result = "uploaded";
		}
		return result;
	}

	/**Method Name: dateWithTime()
	 * @purpose: It concat date with In and Out Time
	 * @param empTokenStr
	 * @param attDate
	 * @param attInTime
	 * @param attOutTime
	 * @return Object[][]
	 */
	private Object[][] dateWithTime(String empTokenStr, String attDate,
			String attInTime, String attOutTime) {

		Object[][] attDetails = new Object[1][3];
		try {
			int finalShiftEndTime = 0;
			String uploadIN = "", uploadINStr = "";
			String uploadOut = "", uploadOutStr = "";
			uploadIN = attInTime.replace(":", "");
			if (uploadIN.equals("") || uploadIN.equals(attInTime)) {
				uploadIN = attInTime.replace("-", "");
			} else if (uploadIN.equals("") || uploadIN.equals(attInTime)) {
				uploadIN = attInTime.replace(".", "");
			}
			if (uploadIN.length() > 4) {
				uploadINStr = uploadIN.substring(0, 4);
				uploadIN = uploadINStr;
			}
			uploadOut = attOutTime.replace(":", "");
			if (uploadOut.equals("") || uploadOut.equals(attOutTime)) {
				uploadOut = attOutTime.replace("-", "");
			} else if (uploadOut.equals("") || uploadOut.equals(attOutTime)) {
				uploadOut = attOutTime.replace(".", "");
			}
			if (uploadOut.length() > 4) {
				uploadOutStr = uploadOut.substring(0, 4);
				uploadOut = uploadOutStr;
			}
			int Millis_Day = 1000 * 60 * 60 * 24;
			Date inDate = new Date();
			DateFormat inDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date nextDate = new Date();
			DateFormat nextDateFormatter = new SimpleDateFormat("dd-MM-yyyy");

			String inDateTime;
			String outDateTime = "";
			String dateTime = "";
			/**Check for attendance is null or not*/
			if (attDate != null) {
				String empToken = empTokenStr;
				Object[][] shiftDetails =getShiftDetails(empToken);
				/**Check for Shift Detail object null or not*/
				if (shiftDetails != null && shiftDetails.length > 0) {
					/**Here if and Else conditions check for different shifts 
					 * and Emp In and Out Time 
					 * and Shift Start and End time 
					 * According to it define date   */
					if (shiftDetails[0][5].equals("N")) { 
						String shiftEndTimeStr = String
								.valueOf(shiftDetails[0][3]);
						String[] shiftEnd = shiftEndTimeStr.split(":");
						String shiftEndTm = shiftEnd[0] + shiftEnd[1];
						int shiftEndTime = Integer.parseInt(shiftEndTm);
						finalShiftEndTime = shiftEndTime + 300;
					}//end if
					int startTime = Integer.parseInt(uploadIN);
					int endTime = Integer.parseInt(uploadOut);
					/**Check is Night Shift and Shift Start And End Time*/
					if (shiftDetails[0][4].equals("N") && (endTime > startTime)) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end if
					else if (shiftDetails[0][4].equals("N")
							&& (startTime > endTime)) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						nextDate = nextDateFormatter.parse(outDateTime);
						String dateNext = nextDateFormatter.format(nextDate
								.getTime()
								+ Millis_Day);
						outDateTime = dateNext;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end else if 
					else if (shiftDetails[0][4].equals("Y")
							&& (startTime > endTime)) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						nextDate = nextDateFormatter.parse(outDateTime);
						String dateNext = nextDateFormatter.format(nextDate
								.getTime()
								+ Millis_Day);
						outDateTime = dateNext;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}// end else if 
					else if (shiftDetails[0][4].equals("Y")
							&& endTime > startTime
							&& endTime > finalShiftEndTime) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}// end else if 
					else if (shiftDetails[0][4].equals("Y")
							&& endTime > startTime
							&& endTime < finalShiftEndTime) {
						inDateTime = attDate;
						inDate = inDateFormat.parse(inDateTime);
						dateTime = inDateFormat.format(inDate.getTime()
								+ Millis_Day);
						inDateTime = dateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						nextDate = nextDateFormatter.parse(outDateTime);
						String dateNext = nextDateFormatter.format(nextDate
								.getTime()
								+ Millis_Day);
						outDateTime = dateNext;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end else if
					else if (shiftDetails[0][5].equals("Y")
							&& (startTime > endTime)) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						nextDate = nextDateFormatter.parse(outDateTime);
						String dateNext = nextDateFormatter.format(nextDate
								.getTime()
								+ Millis_Day);
						outDateTime = dateNext;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end else if 
					else if ((startTime == 0 && endTime == 0)
							|| (startTime == endTime)) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end else if
				} //end if 
				else {
					inDateTime = attDate;
					inDateTime = inDateTime + " " + attInTime;
					attInTime = inDateTime;
					outDateTime = attDate;
					outDateTime = outDateTime + " " + attOutTime;
					attOutTime = outDateTime;
				}//end else
			}
			attDetails[0][0] = attDate;
			attDetails[0][1] = attInTime;
			attDetails[0][2] = attOutTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attDetails;
	}
	/**Method Name: getShiftDetails()
	 * @purpose : Retrieve Shift Details From HRMS_SHIFT and HRMS_EMP_OFFC
	 * @param empToken
	 * @return object[][]
	 */
	public Object[][] getShiftDetails(String empToken) {
		String shiftQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_SHIFT, TO_CHAR(HRMS_SHIFT.SFT_START_TIME,'HH24:MI:SS'),"
				+ " TO_CHAR(HRMS_SHIFT.SFT_END_TIME,'HH24:MI:SS'), NVL(HRMS_SHIFT.SFT_NIGHT_FLAG,'N'),"
				+ " NVL(HRMS_SHIFT.SFT_FLEXITIME_ALLOWED,'N')"
				+ " FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_SHIFT ON(HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID)"
				+ " WHERE EMP_TOKEN='" + empToken + "'";
		Object[][] shiftDetail = getSingleResult(shiftQuery);
		return shiftDetail;

	}

	private int isWeeklyOff(Calendar currentDay, Object getWeeklyOffData[][]) {
		int countDays = 0;
		int DAY_OF_WEEK = currentDay.get(java.util.Calendar.WEEK_OF_MONTH); // Day of week of specified date

		if(getWeeklyOffData != null && getWeeklyOffData.length > 0) {
			String[] weekDays = null;
			
			for(int i = 0; i < getWeeklyOffData[0].length; i++) {
				if(i + 1 == DAY_OF_WEEK) {
					weekDays = String.valueOf(getWeeklyOffData[0][i]).split(",");
				}
			}

			if(weekDays != null && weekDays.length > 0) {
				for(String string: weekDays) {
					int i = 0;

					try {
						i = Integer.parseInt(string.trim());
					} catch(Exception e) {
						i = 0;
					}

					switch(i) {
						case 1 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 1) { countDays++; } break;
						case 2 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 2) { countDays++; } break;
						case 3 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 3) { countDays++; } break;
						case 4 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 4) { countDays++; } break;
						case 5 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 5) { countDays++; } break;
						case 6 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 6) { countDays++; } break;
						case 7 : if(currentDay.get(Calendar.DAY_OF_WEEK) == 7) { countDays++; } break;
						default : break;
					}
				}
			}
		}
		
		return countDays;
	}

	/*private String processAttendance(Object[][] attendanceData, String fromDate, String toDate, String fldShift, String fldOneTime, String fldWorkHrs) {
		String result = "";
		Object[][] getAttendanceData = null;
		boolean queryResult = false;

		String attDate = "";
		String year = fromDate.substring(6);

		*//**
		 * Check the value of branch wise holiday flag. If it is true then holiday will be calculated branch wise other wise all holiday will be
		 * applied to that branch.
		 *//*
		String query = " SELECT CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF ";
		Object[][] branchFlag = getSingleResult(query);

		try {
			// call calculateNumberOfDays method to calculate the number of days between from date and to date
			int daysBetween = calculateNumberOfDays(fromDate, toDate);

			String tempEmpID = "";
			String tToken = "";

			for(int i = 0; i < attendanceData.length; i++) {
				if(!String.valueOf(attendanceData[i][0]).equals("null") && !tToken.equals(String.valueOf(attendanceData[i][0]))) {
					tempEmpID += "'" + String.valueOf(attendanceData[i][0]) + "',";

					tToken = String.valueOf(attendanceData[i][0]);
				}
			}
			
			tempEmpID = tempEmpID.substring(0, (tempEmpID.length() - 1));

			String empCodeQuery = " SELECT DISTINCT TEMP_ATT_EMP_CODE, EMP_ID, HRMS_EMP_OFFC.EMP_SHIFT, " +
			" TO_CHAR(SFT_START_TIME, 'HH24:MI:SS') AS START_TIME, TO_CHAR(SFT_END_TIME, 'HH24:MI:SS') AS END_TIME, " +
			" TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS') AS FIRST_HALF, TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS') AS SECOND_HALF, " +
			" HRMS_SHIFT.SHIFT_NAME, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, " +
			" NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, " +
			" NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING, SFT_NIGHT_FLAG " + 
			" FROM HRMS_ATTENDANCE_TEMP " +
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_TOKEN = HRMS_ATTENDANCE_TEMP.TEMP_ATT_EMP_CODE) " +
			" INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " + 
			" WHERE TEMP_ATT_EMP_CODE IN(" + tempEmpID + ") ";
			Object[][] empShiftData = getSingleResult(empCodeQuery);

			for(int day = 0; day < daysBetween; day++) {
				String concatEmpIds = "";
				*//**
				 * Call getAttendanceData method to retrieve attendance data from HRMS_ATT_TEMP table for a particular date and division and branch
				 *//*
				//getAttendanceData = getAttendanceData(bean, j, empCode);
				getAttendanceData = getAttendanceData(day, empShiftData, fromDate, fldShift, fldOneTime, fldWorkHrs);

				if(getAttendanceData != null && getAttendanceData.length != 0) { // if data is present
					Object[][] insertAttendanceData = new Object[getAttendanceData.length][11];

					for(int i = 0; i < getAttendanceData.length; i++) {
						Object[] shift_id = {checkNull(String.valueOf(getAttendanceData[i][3]).trim())}; // shift id

						int count = 0;

						Object[][] holiDay = {{"false"}};
						Object[] calculateTime = null;
						String status = "";

						*//**
						 * If branch flag is true
						 *//*
						String holidaySql = "";
						
						if(branchFlag[0][0].equals("Y")) {
							// retrieve holiday details for that branch from HRMS_HOLIDAY_BRANCH table for given date
							Object[] branchHolidayDate = new Object[3];
							branchHolidayDate[0] = checkNull(String.valueOf(getAttendanceData[i][2]).trim()); // holiday date
							branchHolidayDate[1] = checkNull(String.valueOf(getAttendanceData[i][1]).trim()); // employee code
							branchHolidayDate[2] = checkNull(String.valueOf(getAttendanceData[i][2])).trim(); // holiday date
							
							holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END " +
							" FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = ?) AND " +
							" HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY')";
							holiDay = getSingleResult(holidaySql, branchHolidayDate);
						} else {
							// retrieve holiday details from HRMS_HOLIDAY table
							Object[] holidayDate = new Object[2];
							holidayDate[0] = checkNull(String.valueOf(getAttendanceData[i][2]).trim()); // holiday date
							holidayDate[1] = checkNull(String.valueOf(getAttendanceData[i][2]).trim()); // holiday date
							
							holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END FROM HRMS_HOLIDAY " +
							" WHERE HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') ";
							holiDay = getSingleResult(holidaySql, holidayDate);
						}

						if(holiDay != null && holiDay.length != 0 && String.valueOf(holiDay[0][0]).equalsIgnoreCase("true")) {
							status = "HO";
						} else {
							// retrieve the weekly off details from HRMS_SHIFT table for given date
							String weekOffSql = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 " +
							" FROM HRMS_SHIFT WHERE SHIFT_ID = ? ";
							Object[][] getWeeklyOffData = getSingleResult(weekOffSql, shift_id);

							// convert date in to calendar date
							Calendar currentDay = new Utility().getCalanderDate(checkNull(String.valueOf(getAttendanceData[i][2])));

							try {
								// call isWeeklyOff method to check whether given date is weekly off or not
								count = isWeeklyOff(currentDay, getWeeklyOffData);
							} catch(Exception e) {
								if(!fldShift.equals("") && !String.valueOf(getAttendanceData[i][11]).equals(null)
									&& !String.valueOf(getAttendanceData[i][11]).equals("")
									&& !String.valueOf(getAttendanceData[i][11]).equals("null")) {
									return "shiftNotDefined"; // shift configuration not present
								} else if(!fldShift.equals("")) {
									// retrieve shift details for an employee from HRMS_EMP_OFFC and HRMS_SHIFT tables
									String selectQury = " SELECT EMP_SHIFT, SHIFT_NAME, " +
									" TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS') AS START_TIME, " +
									" TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS') AS END_TIME, " +
									" TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS') AS FIRST_HALF, " +
									" TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS') AS SECOND_HALF FROM HRMS_EMP_OFFC " +
									" INNER JOIN HRMS_SHIFT ON (EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) " +
									" WHERE EMP_ID = " + String.valueOf(getAttendanceData[i][1]);

									Object[][] getShiftDetails = getSingleResult(selectQury);

									if(getShiftDetails != null && getShiftDetails.length != 0) {
										getAttendanceData[i][3] = checkNull(String.valueOf(getShiftDetails[0][0]).trim()); // shift code
										getAttendanceData[i][11] = checkNull(String.valueOf(getShiftDetails[0][1]).trim());// shift name
										getAttendanceData[i][4] = checkNull(String.valueOf(getShiftDetails[0][2]).trim()); // shift start time
										getAttendanceData[i][5] = checkNull(String.valueOf(getShiftDetails[0][3]).trim()); // shift end time
										getAttendanceData[i][6] = checkNull(String.valueOf(getShiftDetails[0][4]).trim()); // shift first half day
										getAttendanceData[i][7] = checkNull(String.valueOf(getShiftDetails[0][5]).trim()); // shift second half day

										Object[] shiftId = {checkNull(String.valueOf(getShiftDetails[0][0]).trim())}; // shift id
										try {
											// retrieve weekly off details for above selected shift code
											Object[][] getWeeklyData = getSingleResult(weekOffSql, shiftId);

											// call isWeeklyOff method to check whether given date is weekly off or not
											count = isWeeklyOff(currentDay, getWeeklyData);
										} catch(Exception ex) {
											JobLogger.error(client, "Exception in processAttendance-" + e);
											JobLogger.printStackTrace(client, e);
										}
									}
								}
							}

							// if isWeeklyOff method return 1, given date is a weekly off
							if(count == 1) {
								status = "WO"; // set status as weekly off
							}
						}

						*//**
						 * call calculateLateAndEarlyHrs method to calculate late hours, early hours, extra hours etc as per the shift details and 
						 * the values retrieved from the file
						 *//*
						String empInTime = checkNull(String.valueOf(getAttendanceData[i][8]));
						String empOutTime = checkNull(String.valueOf(getAttendanceData[i][9]));
						String offcStartTime = checkNull(String.valueOf(getAttendanceData[i][4]));
						String offcEndTime = checkNull(String.valueOf(getAttendanceData[i][5]));
						String firstHalfTime = checkNull(String.valueOf(getAttendanceData[i][6]));
						String secondHalfTime = checkNull(String.valueOf(getAttendanceData[i][7]));
						String nightFlag = checkNull(String.valueOf(getAttendanceData[i][13]));
						String lateMarkTime = checkNull(String.valueOf(getAttendanceData[i][14]));
						String earlyLeavingTime = checkNull(String.valueOf(getAttendanceData[i][15]));
						
						calculateTime = calculateLateAndEarlyHrs(empInTime, empOutTime, offcStartTime, offcEndTime, firstHalfTime, secondHalfTime, 
							nightFlag, lateMarkTime, earlyLeavingTime);

						attDate = checkNull(String.valueOf(getAttendanceData[i][2]).trim());

						insertAttendanceData[i][0] = checkNull(String.valueOf(getAttendanceData[i][2]).trim()); // ATT_DATE
						insertAttendanceData[i][1] = checkNull(String.valueOf(getAttendanceData[i][1]).trim()); // ATT_EMP_ID
						insertAttendanceData[i][2] = checkNull(String.valueOf(getAttendanceData[i][3]).trim()); // ATT_SHIFT_ID
						insertAttendanceData[i][3] = checkNull(String.valueOf(getAttendanceData[i][8]).trim()); // ATT_LOGIN
						insertAttendanceData[i][4] = checkNull(String.valueOf(getAttendanceData[i][9]).trim()); // ATT_LOGOUT
						insertAttendanceData[i][5] = checkNull(String.valueOf(getAttendanceData[i][10]).trim()); // ATT_WORKING_HRS
						insertAttendanceData[i][6] = String.valueOf(calculateTime[4]).trim(); // ATT_EXTRAHRS
						insertAttendanceData[i][7] = checkNull(String.valueOf(calculateTime[0]).trim()); // ATT_LATE_HRS
						insertAttendanceData[i][8] = checkNull(String.valueOf(calculateTime[1]).trim()); // ATT_EARLY_HRS
						
						if(String.valueOf(getAttendanceData[i][8]).equals("00:00:00") && String.valueOf(getAttendanceData[i][9]).equals("00:00:00")
							&& !status.equals("HO") && !status.equals("WO")) {
							status = "AB";
						} else if(!status.equals("HO") && !status.equals("WO") && !status.equals("AB")) {
							status = "PR";
						}
						
						insertAttendanceData[i][9] = status; // status one
						
						String statusTwo = "";
						
						if(status.equals("AB")) { statusTwo = "AB"; }
						else { statusTwo = checkNull(String.valueOf(calculateTime[5]).trim()); }
						
						insertAttendanceData[i][10] = statusTwo; // status two

						concatEmpIds = concatEmpIds + String.valueOf(insertAttendanceData[i][1]) + ",";
					}

					concatEmpIds = concatEmpIds.substring(0, concatEmpIds.length() - 1);

					String fromYear = fromDate.substring(6);
					String toYear = toDate.substring(6);

					if(fromYear.equals(toYear)) {
						String selectQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_" + year + 
						" WHERE 1 = 1 " + Utility.getConcatenatedIds("ATT_EMP_ID", concatEmpIds) + " AND " +
						" ATT_DATE = TO_DATE('" + attDate + "', 'DD-MM-YYYY') ";
						Object[][] attExistEmp = getSingleResult(selectQuery);

						Object[][] updateAttData = null;

						if(attExistEmp != null && attExistEmp.length > 0) {
							JobLogger.info(client,"Modified employee count=== "+attExistEmp.length);
							updateAttData = new Object[attExistEmp.length][11];
							
							for(int m = 0; m < attExistEmp.length; m++) {
								for(int n = 0; n < insertAttendanceData.length; n++) {
									if(String.valueOf(attExistEmp[m][0]).equals(String.valueOf(insertAttendanceData[n][1]))
										&& String.valueOf(attExistEmp[m][1]).equals(String.valueOf(insertAttendanceData[n][0]))) {
										updateAttData[m][0] = String.valueOf(insertAttendanceData[n][2]); // ATT_SHIFT_ID
										updateAttData[m][1] = String.valueOf(insertAttendanceData[n][3]); // ATT_LOGIN
										updateAttData[m][2] = String.valueOf(insertAttendanceData[n][4]); // ATT_LOGOUT
										updateAttData[m][3] = String.valueOf(insertAttendanceData[n][5]); // ATT_WORKING_HRS
										updateAttData[m][4] = String.valueOf(insertAttendanceData[n][6]); // ATT_EXTRAHRS
										updateAttData[m][5] = String.valueOf(insertAttendanceData[n][7]); // ATT_LATE_HRS
										updateAttData[m][6] = String.valueOf(insertAttendanceData[n][8]); // ATT_EARLY_HRS
										updateAttData[m][7] = String.valueOf(insertAttendanceData[n][9]); // ATT_STATUS_ONE
										updateAttData[m][8] = String.valueOf(insertAttendanceData[n][10]); // ATT_STATUS_TWO
										updateAttData[m][9] = String.valueOf(insertAttendanceData[n][0]); // ATT_DATE
										updateAttData[m][10] = String.valueOf(insertAttendanceData[n][1]); // ATT_EMP_ID
										
										break;
									}
								}
							}

							String updateQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_" + year + " SET ATT_SHIFT_ID = ?, " +
							" ATT_LOGIN = TO_DATE(?, 'HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'HH24:MI:SS'), " +
							" ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS') , " +
							" ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ? , " +
							" ATT_STATUS_TWO = ? WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";
							queryResult = singleExecute(updateQuerySql, updateAttData);
						}

						Object[][] insertAttData = null;
						int insertDataLength = 0;
						
						if(attExistEmp != null && attExistEmp.length > 0) {
							insertDataLength = insertAttendanceData.length - attExistEmp.length;
						} else if(insertAttendanceData != null && insertAttendanceData.length > 0) {
							insertDataLength = insertAttendanceData.length;
						}
						
						if(insertDataLength > 0) {
							insertAttData = new Object[insertDataLength][11];
							JobLogger.info(client,"Newly Added employee count=== "+insertDataLength);
							boolean flag = false;
							int cnt = 0;
							
							for(int p = 0; p < insertAttendanceData.length; p++) {
								for(int q = 0; q < attExistEmp.length; q++) {
									if(String.valueOf(attExistEmp[q][0]).equals(String.valueOf(insertAttendanceData[p][1]))
										&& String.valueOf(attExistEmp[q][1]).equals(String.valueOf(insertAttendanceData[p][0]))) {
										flag = true;
									}
								}
								
								if(!flag) {
									insertAttData[cnt][0] = String.valueOf(insertAttendanceData[p][0]); // ATT_DATE
									insertAttData[cnt][1] = String.valueOf(insertAttendanceData[p][1]); // ATT_EMP_ID
									insertAttData[cnt][2] = String.valueOf(insertAttendanceData[p][2]); // ATT_SHIFT_ID
									insertAttData[cnt][3] = String.valueOf(insertAttendanceData[p][3]); // ATT_LOGIN
									insertAttData[cnt][4] = String.valueOf(insertAttendanceData[p][4]); // ATT_LOGOUT
									insertAttData[cnt][5] = String.valueOf(insertAttendanceData[p][5]); // ATT_WORKING_HRS
									insertAttData[cnt][6] = String.valueOf(insertAttendanceData[p][6]); // ATT_EXTRAHRS
									insertAttData[cnt][7] = String.valueOf(insertAttendanceData[p][7]); // ATT_LATE_HRS
									insertAttData[cnt][8] = String.valueOf(insertAttendanceData[p][8]); // ATT_EARLY_HRS
									insertAttData[cnt][9] = String.valueOf(insertAttendanceData[p][9]); // ATT_STATUS_ONE
									insertAttData[cnt][10] = String.valueOf(insertAttendanceData[p][10]); // ATT_STATUS_TWO
									
									cnt++;
								}
								flag = false;
							}

							// insert attendance data with all calculations in HRMS_DAILY_ATTENDANCE_YEAR table
							String insertQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_" + year + " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, " +
							" ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) " +
							" VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), " +
							" TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?)";
							queryResult = singleExecute(insertQuerySql, insertAttData);
						}
					} else {
						int count = 0;
						for(int k = 0; k < insertAttendanceData.length; k++) {
							if(fromYear.equals(String.valueOf(insertAttendanceData[k][0]).substring(6))) {
								count++;
							}
						}
						Object[][] fromObject = null, toBoject = null;
						
						if(count != 0) {
							fromObject = new Object[count][11];
						}
						
						if(insertAttendanceData.length - count != 0) {
							toBoject = new Object[insertAttendanceData.length - count][11];
						}
						
						int fromCount = 0, toCount = 0;
						
						for(int m = 0; m < insertAttendanceData.length; m++) {
							if(fromYear.equals(String.valueOf(insertAttendanceData[m][0]).substring(6))) {
								if(count != 0) {
									fromObject[fromCount][0] = insertAttendanceData[m][0];
									fromObject[fromCount][1] = insertAttendanceData[m][1];
									fromObject[fromCount][2] = insertAttendanceData[m][2];
									fromObject[fromCount][3] = insertAttendanceData[m][3];
									fromObject[fromCount][4] = insertAttendanceData[m][4];
									fromObject[fromCount][5] = insertAttendanceData[m][5];
									fromObject[fromCount][6] = insertAttendanceData[m][6];
									fromObject[fromCount][7] = insertAttendanceData[m][7];
									fromObject[fromCount][8] = insertAttendanceData[m][8];
									fromObject[fromCount][9] = insertAttendanceData[m][9];
									fromObject[fromCount][10] = insertAttendanceData[m][10];
									fromCount++;
								}
							} else {
								if(insertAttendanceData.length - count != 0) {
									toBoject[toCount][0] = insertAttendanceData[m][0];
									toBoject[toCount][1] = insertAttendanceData[m][1];
									toBoject[toCount][2] = insertAttendanceData[m][2];
									toBoject[toCount][3] = insertAttendanceData[m][3];
									toBoject[toCount][4] = insertAttendanceData[m][4];
									toBoject[toCount][5] = insertAttendanceData[m][5];
									toBoject[toCount][6] = insertAttendanceData[m][6];
									toBoject[toCount][7] = insertAttendanceData[m][7];
									toBoject[toCount][8] = insertAttendanceData[m][8];
									toBoject[toCount][9] = insertAttendanceData[m][9];
									toBoject[toCount][10] = insertAttendanceData[m][10];
									
									toCount++;
								}
							}
						}

						// ============== for From Year ================================================

						Object[][] attExistEmpFrom = null;
						String selectFromQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_" + fromYear + 
						" WHERE 1 = 1 " + Utility.getConcatenatedIds("ATT_EMP_ID", concatEmpIds) + 
						" AND ATT_DATE = TO_DATE('" + attDate + "','DD-MM-YYYY')";
						
						if(count != 0) {
							attExistEmpFrom = getSingleResult(selectFromQuery);
						}
						
						Object[][] updateAttDataFrom = null;

						if(attExistEmpFrom != null && attExistEmpFrom.length > 0) {
							updateAttDataFrom = new Object[attExistEmpFrom.length][11];
							
							for(int m = 0; m < attExistEmpFrom.length; m++) {
								for(int n = 0; n < fromObject.length; n++) {
									if(String.valueOf(attExistEmpFrom[m][0]).equals(String.valueOf(fromObject[n][1]))
										&& String.valueOf(attExistEmpFrom[m][1]).equals(String.valueOf(fromObject[n][0]))) {
										updateAttDataFrom[m][0] = String.valueOf(fromObject[n][2]); // ATT_SHIFT_ID
										updateAttDataFrom[m][1] = String.valueOf(fromObject[n][3]); // ATT_LOGIN
										updateAttDataFrom[m][2] = String.valueOf(fromObject[n][4]); // ATT_LOGOUT
										updateAttDataFrom[m][3] = String.valueOf(fromObject[n][5]); // ATT_WORKING_HRS
										updateAttDataFrom[m][4] = String.valueOf(fromObject[n][6]); // ATT_EXTRAHRS
										updateAttDataFrom[m][5] = String.valueOf(fromObject[n][7]); // ATT_LATE_HRS
										updateAttDataFrom[m][6] = String.valueOf(fromObject[n][8]); // ATT_EARLY_HRS
										updateAttDataFrom[m][7] = String.valueOf(fromObject[n][9]); // ATT_STATUS_ONE
										updateAttDataFrom[m][8] = String.valueOf(fromObject[n][10]); // ATT_STATUS_TWO
										updateAttDataFrom[m][9] = String.valueOf(fromObject[n][0]); // ATT_DATE
										updateAttDataFrom[m][10] = String.valueOf(fromObject[n][1]); // ATT_EMP_ID
										break;
									}
								}
							}

							String updateQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_" + year + " SET ATT_SHIFT_ID = ?, " +
							" ATT_LOGIN = TO_DATE(?, 'HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'HH24:MI:SS'), ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), " +
							" ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), " +
							" ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ?, ATT_STATUS_TWO = ? " +
							" WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";
							
							if(count != 0) {
								queryResult = singleExecute(updateQuerySql, updateAttDataFrom);
							}
						}

						Object[][] insertAttDataFrom = null;
						int insertDataFromLength = 0;
						
						if(attExistEmpFrom != null && attExistEmpFrom.length > 0) {
							insertDataFromLength = fromObject.length - attExistEmpFrom.length;
						} else if(fromObject != null && fromObject.length > 0) {
							insertDataFromLength = fromObject.length;
						}

						if(insertDataFromLength > 0) {
							insertAttDataFrom = new Object[insertDataFromLength][11];
							boolean flag = false;
							int cnt = 0;
							
							for(int p = 0; p < fromObject.length; p++) {
								for(int q = 0; q < attExistEmpFrom.length; q++) {
									if(String.valueOf(attExistEmpFrom[q][0]).equals(String.valueOf(fromObject[p][1]))
										&& String.valueOf(attExistEmpFrom[q][1]).equals(String.valueOf(fromObject[p][0]))) {
										flag = true;
									}
								}
								
								if(!flag) {
									insertAttDataFrom[cnt][0] = String.valueOf(fromObject[p][0]); // ATT_DATE
									insertAttDataFrom[cnt][1] = String.valueOf(fromObject[p][1]); // ATT_EMP_ID
									insertAttDataFrom[cnt][2] = String.valueOf(fromObject[p][2]); // ATT_SHIFT_ID
									insertAttDataFrom[cnt][3] = String.valueOf(fromObject[p][3]); // ATT_LOGIN
									insertAttDataFrom[cnt][4] = String.valueOf(fromObject[p][4]); // ATT_LOGOUT
									insertAttDataFrom[cnt][5] = String.valueOf(fromObject[p][5]); // ATT_WORKING_HRS
									insertAttDataFrom[cnt][6] = String.valueOf(fromObject[p][6]); // ATT_EXTRAHRS
									insertAttDataFrom[cnt][7] = String.valueOf(fromObject[p][7]); // ATT_LATE_HRS
									insertAttDataFrom[cnt][8] = String.valueOf(fromObject[p][8]); // ATT_EARLY_HRS
									insertAttDataFrom[cnt][9] = String.valueOf(fromObject[p][9]); // ATT_STATUS_ONE
									insertAttDataFrom[cnt][10] = String.valueOf(fromObject[p][10]); // ATT_STATUS_TWO
									
									cnt++;
								}
								flag = false;
							}

							// insert attendance data with all calculations in HRMS_DAILY_ATTENDANCE_YEAR table
							String insertFromQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_" + fromYear + " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, " +
							" ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) " +
							" VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), " +
							" TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?) ";
							
							if(count != 0) {
								queryResult = singleExecute(insertFromQuerySql, insertAttDataFrom);
							}
						}

						String selectToQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_" + toYear + 
						" WHERE 1 = 1 " + Utility.getConcatenatedIds("ATT_EMP_ID", concatEmpIds) + 
						" AND ATT_DATE = TO_DATE('" + attDate + "', 'DD-MM-YYYY')";

						Object[][] attExistEmpTo = null;
						if(insertAttendanceData.length - count != 0) {
							attExistEmpTo = getSingleResult(selectToQuery);
						}

						Object[][] updateAttDataTo = null;
						if(attExistEmpTo != null && attExistEmpTo.length > 0) {
							updateAttDataTo = new Object[attExistEmpTo.length][11];
							
							for(int m = 0; m < attExistEmpTo.length; m++) {
								for(int n = 0; n < toBoject.length; n++) {
									if(String.valueOf(attExistEmpTo[m][0]).equals(String.valueOf(toBoject[n][1]))
										&& String.valueOf(attExistEmpTo[m][1]).equals(String.valueOf(toBoject[n][0]))) {
										updateAttDataTo[m][0] = String.valueOf(toBoject[n][2]); // ATT_SHIFT_ID
										updateAttDataTo[m][1] = String.valueOf(toBoject[n][3]); // ATT_LOGIN
										updateAttDataTo[m][2] = String.valueOf(toBoject[n][4]); // ATT_LOGOUT
										updateAttDataTo[m][3] = String.valueOf(toBoject[n][5]); // ATT_WORKING_HRS
										updateAttDataTo[m][4] = String.valueOf(toBoject[n][6]); // ATT_EXTRAHRS
										updateAttDataTo[m][5] = String.valueOf(toBoject[n][7]); // ATT_LATE_HRS
										updateAttDataTo[m][6] = String.valueOf(toBoject[n][8]); // ATT_EARLY_HRS
										updateAttDataTo[m][7] = String.valueOf(toBoject[n][9]); // ATT_STATUS_ONE
										updateAttDataTo[m][8] = String.valueOf(toBoject[n][10]); // ATT_STATUS_TWO
										updateAttDataTo[m][9] = String.valueOf(toBoject[n][0]); // ATT_DATE
										updateAttDataTo[m][10] = String.valueOf(toBoject[n][1]); // ATT_EMP_ID
										
										break;
									}
								}
							}

							String updateToQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_" + toYear + " SET ATT_SHIFT_ID = ?, " +
							" ATT_LOGIN =  TO_DATE(?, 'HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'HH24:MI:SS'), " +
							" ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), " +
							" ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ?, " +
							" ATT_STATUS_TWO = ? WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";
							
							if(insertAttendanceData.length - count != 0) {
								queryResult = singleExecute(updateToQuerySql, updateAttDataTo);
							}
						}

						Object[][] insertAttDataTo = null;
						int insertDataToLength = 0;
						
						if(attExistEmpTo != null && attExistEmpTo.length > 0) {
							insertDataToLength = toBoject.length - attExistEmpTo.length;
						} else if(toBoject != null && toBoject.length > 0) {
							insertDataToLength = toBoject.length;
						}
						
						if(insertDataToLength > 0) {
							insertAttDataTo = new Object[insertDataToLength][11];
							boolean flag = false;
							int cnt = 0;
							
							for(int p = 0; p < toBoject.length; p++) {
								for(int q = 0; q < attExistEmpTo.length; q++) {
									if(String.valueOf(attExistEmpTo[q][0]).equals(String.valueOf(toBoject[p][1]))
										&& String.valueOf(attExistEmpTo[q][1]).equals(String.valueOf(toBoject[p][0]))) {
										flag = true;
									}
								}
								
								if(!flag) {
									insertAttDataTo[cnt][0] = String.valueOf(toBoject[p][0]); // ATT_DATE
									insertAttDataTo[cnt][1] = String.valueOf(toBoject[p][1]); // ATT_EMP_ID
									insertAttDataTo[cnt][2] = String.valueOf(toBoject[p][2]); // ATT_SHIFT_ID
									insertAttDataTo[cnt][3] = String.valueOf(toBoject[p][3]); // ATT_LOGIN
									insertAttDataTo[cnt][4] = String.valueOf(toBoject[p][4]); // ATT_LOGOUT
									insertAttDataTo[cnt][5] = String.valueOf(toBoject[p][5]); // ATT_WORKING_HRS
									insertAttDataTo[cnt][6] = String.valueOf(toBoject[p][6]); // ATT_EXTRAHRS
									insertAttDataTo[cnt][7] = String.valueOf(toBoject[p][7]); // ATT_LATE_HRS
									insertAttDataTo[cnt][8] = String.valueOf(toBoject[p][8]); // ATT_EARLY_HRS
									insertAttDataTo[cnt][9] = String.valueOf(toBoject[p][9]); // ATT_STATUS_ONE
									insertAttDataTo[cnt][10] = String.valueOf(toBoject[p][10]); // ATT_STATUS_TWO
									
									cnt++;
								}
								flag = false;
							}

							// insert attendance data with all calculations in HRMS_DAILY_ATTENDANCE_YEAR table
							String insertFromQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_" + toYear + " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, " +
							" ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) " +
							" VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), " +
							" TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?) ";
							
							if(insertAttendanceData.length - count != 0) {
								queryResult = singleExecute(insertFromQuerySql, insertAttDataTo);
							}
						}
					}
					if(queryResult) {
						result = "inserted"; // data inserted successfully
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(client, "Exception in processAttendance-" + e);
			JobLogger.printStackTrace(client, e);
		}
		return result;
	}*/

	private Object[][] retrieveAttendance(String driver, String server, String userName, String password, String database, String port, 
		String tableName, String fromDate, String toDate, String fldEmpToken, String fldAttendanceDate, String fldInTime, String fldOutTime, 
		String fldOneTime, String fldIOFlag, String fldWorkHrs, String fldShift) {
		Object[][] retrievedAttendance = null;
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
					
					sqlQuery += " FROM " + tableName + 
					" WHERE " + fldAttendanceDate + " BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY')" +
					" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY')";
					
					driverClass = "oracle.jdbc.OracleDriver";
					url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;
					
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url, userName, password);
						JobLogger.info(client, "Oracle Connected.............");
					} catch(Exception e) {
						JobLogger.error(client, "Exception in creating connection object for Oracle:" + e);
						JobLogger.printStackTrace(client, e);
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
					
					sqlQuery += " FROM " + tableName + 
					" WHERE " + fldAttendanceDate + " BETWEEN CONVERT(DATETIME, '" + fromDate + "', 105)" +
					" AND CONVERT(DATETIME, '" + toDate + "', 105)";
					
					driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					url = "jdbc:sqlserver://" + server + ";databaseName=" + database + ";user=" + userName + ";password=" + password + ";";
					
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						JobLogger.info(client, "SQL Server Connected.............");
					} catch(Exception e) {
						JobLogger.error(client, "Exception in creating connection object for MS SQL :" + e);
						JobLogger.printStackTrace(client, e);
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
					
					sqlQuery += " FROM " + tableName + 
					" WHERE " + fldAttendanceDate + " BETWEEN STR_TO_DATE('" + fromDate + "', '%d-%m-%Y') " +
					" AND STR_TO_DATE('" + toDate + "', '%d-%m-%Y')";
					
					driverClass = "com.mysql.jdbc.Driver";
					url = "jdbc:mysql://" + server + ":" + port + "/" + database + "?user=" + userName + "&password=" + password;
					
					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						JobLogger.info(client, "My SQL Connected.............");
					} catch(Exception e) {
						JobLogger.error(client, "Exception in creating connection object for My SQL:" + e);
						JobLogger.printStackTrace(client, e);
					}
				}

				PreparedStatement pst = null;
				ResultSet rs = null;

				try {
					JobLogger.info(client, "query on remote server:" + sqlQuery);

					pst = conn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pst.executeQuery();

					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();

					rs.last();
					retrievedAttendance = new Object[rs.getRow()][numberOfColumns];
					rs.beforeFirst();
					
					columnNames = new ArrayList<String>(numberOfColumns);
					
					for(int colNo = 0; colNo < numberOfColumns; colNo++) {
						columnNames.add(rsmd.getColumnName(colNo + 1));
					}
					
					int rowNumber = 0;
					
					while(rs.next()) {
						for(int i = 0; i < numberOfColumns; i++) {
							retrievedAttendance[rowNumber][i] = rs.getObject(i + 1);
						}
						rowNumber++;
					}
				} catch(Exception e) {
					try {
						rs.close();
					} catch(Exception e1) {
						JobLogger.error(client, "" + e1);
						JobLogger.printStackTrace(client, e1);
					}

					try {
						pst.clearParameters();
						pst.close();
					} catch(Exception e2) {
						JobLogger.error(client, "" + e2);
						JobLogger.printStackTrace(client, e2);
					}
				}
			} catch(Exception e) {
				if(conn != null) {
					conn.close();
				}
			} finally {
				if(conn != null) {
					conn.close();
				}
			}
		} catch(Exception e) {
			JobLogger.error(client, "Connection Not Esablished..." + e);
			JobLogger.printStackTrace(client, e);
		}
		
		return retrievedAttendance;
	}

	private boolean singleExecute(String sqlQuery) {
		boolean resultFlag = false;
		java.sql.PreparedStatement PST = null;

		try {
			JobLogger.info(client, "sqlQuery-" + sqlQuery);

			/**
			 * Get the query
			 */
			PST = dbConn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			/**
			 * Executes a query
			 */
			PST.executeUpdate();
			PST.clearParameters();
			dbConn.commit();
			resultFlag = true;
		} catch(Exception ex) {
			/**
			 * Rollback transactions if fails.
			 */
			try {
				dbConn.rollback();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in singleExecute-" + e);
				JobLogger.printStackTrace(client, e);
			}
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in singleExecute-" + e);
				JobLogger.printStackTrace(client, e);
			}
		}
		return resultFlag;
	}

	private boolean singleExecute(String sqlQuery, Object[][] obj) {
		java.sql.PreparedStatement PST = null;
		boolean resultFlag = false;
		try {
			JobLogger.info(client, "sqlQuery-" + sqlQuery);

			/**
			 * Get the query
			 */
			PST = dbConn.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			/**
			 * Add the parameters
			 */
			for(int j = 0; j < obj.length; j++) {
				for(int k = 0; k < obj[j].length; k++) {
					PST.setObject(k + 1, obj[j][k]);
				}
				PST.addBatch();
			}

			/**
			 * Executes a query
			 */
			PST.executeBatch();
			PST.clearParameters(); // Removes the parameters
			dbConn.commit();
			resultFlag = true;
		} catch(Exception ex) {
			JobLogger.error(client, "Exception in singleExecute-" + ex);
			JobLogger.printStackTrace(client, ex);
			
			/**
			 * Rollback transactions if fails.
			 */
			resultFlag = false;
			try {
				dbConn.rollback();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in singleExecute-" + e);
				JobLogger.printStackTrace(client, e);
			}
		} finally {
			try {
				PST.clearParameters();
				PST.close();
			} catch(Exception e) {
				JobLogger.error(client, "Exception in singleExecute-" + e);
				JobLogger.printStackTrace(client, e);
			}
		}
		return resultFlag;
	}
}