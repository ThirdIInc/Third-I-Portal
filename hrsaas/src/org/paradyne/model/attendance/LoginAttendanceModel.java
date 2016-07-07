/* Bhushan Dasare Feb 1, 2010 */

package org.paradyne.model.attendance;

import java.util.Calendar;
import java.util.Date;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.leave.TimeUtility;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Modified By AA1711
 * @description Modified For Flexi Shift And Night Shift
 */
public class LoginAttendanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginAttendanceModel.class);

	/**@Method Name :calculateAttendance
	 * @purpose : Used to calculate Attendance at the DayIn  and DayOut Clicked
	 * @param empId
	 * @param type
	 */
	public void calculateAttendance(String empId, String type,boolean attForDayIn) {
		try {
			/**
			 * Get attendance settings
			 */
			String shiftID = "", nightShiftFlag = "N", flexiShiftFlag="N";
			String loginAttendanceFlagSql = " SELECT DECODE(CONF_LOGIN_ATTENDANCE_FLAG, 'Y', 'true', 'N', 'false') AS CONF_LOGIN_ATTENDANCE_FLAG, "
					+ " DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') AS CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF ";
			Object[][] loginAttendanceFlagObj = getSqlModel().getSingleResult(
					loginAttendanceFlagSql);

			boolean loginAttendanceFlag = Boolean.parseBoolean(String
					.valueOf(loginAttendanceFlagObj[0][0]));
			boolean branchWiseHolidayFlag = Boolean.parseBoolean(String
					.valueOf(loginAttendanceFlagObj[0][1]));
			if (loginAttendanceFlag) {
				/**
				 * Get current date and time
				 */
				int Millis_Day = 1000 * 60 * 60 * 24;
				Date date = new Date();
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						"dd-MM-yyyy");
				String currentDate = dateFormatter.format(date);
				String currentYear = currentDate.substring(6, 10);

				Date prevDate = new Date();
				SimpleDateFormat prevDateFormatter = new SimpleDateFormat(
						"dd-MM-yyyy");
				String previousDate = prevDateFormatter.format(prevDate
						.getTime()
						- Millis_Day);
				SimpleDateFormat timeFormatter = new SimpleDateFormat(
						"HH:mm:ss");
				String currentTime = timeFormatter.format(date);
				SimpleDateFormat timeDateFormatter = new SimpleDateFormat(
						"dd-MM-yyyy HH:mm:ss");
				String currentDateTime = timeDateFormatter.format(date);
				/**
				 * Check whether attendance is calculated or not at time of
				 * first login
				 */
				if((!attForDayIn) && type.equals("LOGOUT")){
					currentDate = previousDate;
				}
				Object[][] existedAttendance = getExistedAttendance(
						currentYear, empId, currentDate);
				boolean isAttendanceExists=false , isAppliedLeave=false;
				if (existedAttendance != null && existedAttendance.length > 0) {
					String existStatus = String.valueOf(existedAttendance[0][0]);					
					if (existStatus.equals("AB") || existStatus.equals("HL")) {
							isAppliedLeave =true;
					}
					isAttendanceExists=true;
				}					
				/**
				 * Following code for Night Shift
				 */
				String shiftQuery = "SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ empId;
				Object[][] shiftIDObj = getSqlModel().getSingleResult(
						shiftQuery);
				/**
				 * Here it checks for Night Shift Flag
				 */
				String query = "SELECT NVL(SFT_NIGHT_FLAG,'N'), NVL(SFT_FLEXITIME_ALLOWED,'N')"
							   + " FROM HRMS_SHIFT WHERE SHIFT_ID="+ shiftIDObj[0][0];
				Object[][] shiftDetailObj = getSqlModel()
						.getSingleResult(query);
				/**
				 * If Night Shift Flag is True then set these values
				 */
				if (shiftDetailObj[0][0].equals("Y")
						&& !shiftDetailObj[0][0].equals("N")) {
					shiftID = String.valueOf(shiftIDObj[0][0]);
					nightShiftFlag = String.valueOf(shiftDetailObj[0][0]);					
				}else if(shiftDetailObj[0][1].equals("Y")
						&& !shiftDetailObj[0][1].equals("N")){
					shiftID = String.valueOf(shiftIDObj[0][0]);
					flexiShiftFlag= String.valueOf(shiftDetailObj[0][1]);					
				}				
				/**
				 * If attendance is already exists, then at time of login, don't
				 * calculate attendance, but at time of logout calculate
				 * attendance with current time.
				 */
				if ((!isAttendanceExists || isAppliedLeave)&& type.equals("LOGIN")) {
					calculateLoginAttendance(empId, currentDate, currentTime,
							currentYear, branchWiseHolidayFlag,
							currentDateTime, nightShiftFlag, flexiShiftFlag,isAppliedLeave);
				}
				else if ((!attForDayIn || isAttendanceExists) && type.equals("LOGOUT")
						&& !shiftDetailObj[0][0].equals("Y") && !shiftDetailObj[0][1].equals("Y")) {
					calculateLogoutAttendance(empId, currentDate, currentTime,
							currentYear, existedAttendance,
							branchWiseHolidayFlag, currentDateTime,isAttendanceExists);
				}
				/** If Night shift flag is true then call this function for
					 * calculate Attendance
					 */
				else if ((shiftDetailObj[0][0].equals("Y") || shiftDetailObj[0][1].equals("Y"))
						&& type.equals("LOGOUT")) {
					calculateLogoutAttendance(empId, currentDate, currentTime,
							currentYear, existedAttendance,
							branchWiseHolidayFlag, shiftID, nightShiftFlag,flexiShiftFlag,
							currentDateTime, previousDate,isAttendanceExists);
				}							
			}
		} catch (Exception e) {
			logger.error("Exception in calculateAttendance:" + e);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**@Method Name : calculateLogoutAttendance
	 * @purpose : Calculate Logout Time with date in case of Night and Flexi Shift
	 * @param empId
	 * @param currentDate
	 * @param logoutTime
	 * @param currentYear
	 * @param existedAttendance
	 * @param branchWiseHolidayFlag
	 * @param shiftID
	 * @param nightShiftFlag
	 * @param flexiShiftFlag
	 * @param dateTimeLogout
	 * @param prevDate
	 */
	public void calculateLogoutAttendance(String empId, String currentDate,
			String logoutTime, String currentYear,
			Object[][] existedAttendance, boolean branchWiseHolidayFlag,
			String shiftID, String nightShiftFlag, String flexiShiftFlag,String dateTimeLogout,
			String prevDate, boolean isAttendanceExists) {
		Object[] hrsAndStatusTwo = null;
		try {			
			String attendance_date = "";
			String loginTime = "";
			/** getShiftDetail Method used to return Shift Details*/			 
			Object[][] shiftDetails = getShiftDetails(empId, currentDate);
			if (shiftDetails != null && shiftDetails.length > 0) {
				if (existedAttendance != null && existedAttendance.length > 0) {
					loginTime = String.valueOf(existedAttendance[0][1]);
				}				
				Object[][] updateAttendance = new Object[1][10];
				if (nightShiftFlag.equals("Y")) {
					/** Check here If Employee DayOut Early
					 * Before employee's Shift End Time  */
					boolean earlyResult = checkForEarly(empId);
					int shiftStartTime = Integer.parseInt(String
							.valueOf(shiftDetails[0][14]));
					int shiftEndTime = Integer.parseInt(String
							.valueOf(shiftDetails[0][15]));					
					/**
					 * If attendance record is exist for current date
					 */
					final String dateQuery = " SELECT TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, ATT_REG_STATUS_TWO AS STATUS, "
							+ " TO_CHAR(ATT_LOGIN,'HH24:MI:SS') FROM HRMS_DAILY_ATTENDANCE_"
							+ currentYear
							+ " WHERE ATT_EMP_ID = "
							+ empId
							+ " AND ATT_DATE = TO_DATE('"
							+ currentDate
							+ "', 'DD-MM-YYYY')";
					Object[][] attDateObj = getSqlModel().getSingleResult(
							dateQuery);
					/**
					 * If there is no attendance for current date retrieve Login
					 * time for calculate hours and status And logout record is
					 * updated in previous date But the logout date is Current date
					 * and time
					 */
					if ((attDateObj == null || attDateObj.length == 0)
							&& (shiftStartTime > shiftEndTime) && !earlyResult) {
						attendance_date = prevDate;
					} else if ((attDateObj != null && attDateObj.length > 0)
							&& !(String.valueOf(attDateObj[0][1]).equals(null) || String.valueOf(attDateObj[0][1]).equals("null"))) {
						/** || String.valueOf(attDateObj[0][1]).equals("HO") ||
						 * String.valueOf(attDateObj[0][1]).equals("AB")*/
						if(!(String.valueOf(attDateObj[0][1]).equals(null))){
							if(String.valueOf(attDateObj[0][1]).equals("HL")){
								attendance_date = currentDate;
							}else{
								attendance_date = prevDate;
							}
						}
					} else if ((attDateObj != null && attDateObj.length > 0)) {
						attendance_date = currentDate;
					}
					String loginAtt = "SELECT TO_CHAR(ATT_LOGIN, 'HH24:MI:SS') AS ATT_LOGIN"
							+ " FROM HRMS_DAILY_ATTENDANCE_"
							+ currentYear
							+ " WHERE ATT_EMP_ID ="
							+ empId
							+ " AND "
							+ " ATT_DATE = TO_DATE('"
							+ attendance_date
							+ "', 'DD-MM-YYYY') ";
					Object[][] loginTimeObj = getSqlModel().getSingleResult(
							loginAtt);
					if (loginTimeObj != null && loginTimeObj.length > 0) {
						loginTime = String.valueOf(loginTimeObj[0][0]);
					}
				}
				/**
				 * Checked for Flexi Shift
				 * Here if In Time time is greater than out time then
				 * Attendance Date is Previous Date. 
				 * */
				else if(flexiShiftFlag.equals("Y")){
					attendance_date = currentDate;
					/**
					 * If attendance record is exist for current date
					 */
					final String dateQuery = " SELECT TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, ATT_REG_STATUS_TWO AS STATUS, "
							+ " TO_CHAR(ATT_LOGIN,'HH24:MI:SS') FROM HRMS_DAILY_ATTENDANCE_"
							+ currentYear
							+ " WHERE ATT_EMP_ID = "
							+ empId
							+ " AND ATT_DATE = TO_DATE('"
							+ currentDate
							+ "', 'DD-MM-YYYY')";
					Object[][] attDateObj = getSqlModel().getSingleResult(
							dateQuery);
					if (attDateObj == null || attDateObj.length == 0){
						attendance_date = prevDate;
					}
					String loginAtt = "SELECT TO_CHAR(ATT_LOGIN, 'HH24:MI:SS') AS ATT_LOGIN"
						+ " FROM HRMS_DAILY_ATTENDANCE_"
						+ currentYear
						+ " WHERE ATT_EMP_ID ="
						+ empId
						+ " AND "
						+ " ATT_DATE = TO_DATE('"
						+ attendance_date
						+ "', 'DD-MM-YYYY') ";
				Object[][] loginTimeObj = getSqlModel().getSingleResult(
						loginAtt);
				if (loginTimeObj != null && loginTimeObj.length > 0) {
					loginTime = String.valueOf(loginTimeObj[0][0]);
				}
					String empInTime= loginTime.replace(":", "");
					String empOutTime= logoutTime.replace(":", "");					
					int loginIntTime = Integer.parseInt(empInTime);
					int logoutIntTime= Integer.parseInt(empOutTime);
					if(loginIntTime > logoutIntTime){
						attendance_date = prevDate;
					}
				}
				/**
				 * call calculateLateAndEarlyHrs method to calculate late hours,
				 * early hours, extra hours etc as per the shift details and the
				 * values retrieved from the file
				 */
				hrsAndStatusTwo = calculateHrsAndStatus(empId, attendance_date,
						branchWiseHolidayFlag, shiftDetails, loginTime,
						logoutTime, nightShiftFlag,flexiShiftFlag);
				updateAttendance[0][0] = String.valueOf(shiftDetails[0][2]); // ATT_SHIFT_ID
				updateAttendance[0][1] = dateTimeLogout; // ATT_LOGOUT
				updateAttendance[0][2] = hrsAndStatusTwo[0]; // ATT_LATE_HRS
				updateAttendance[0][3] = hrsAndStatusTwo[1]; // ATT_WORKING_HRS
				updateAttendance[0][4] = hrsAndStatusTwo[2]; // ATT_EXTRAHRS
				updateAttendance[0][5] = hrsAndStatusTwo[3]; // ATT_EARLY_HRS
				updateAttendance[0][6] = hrsAndStatusTwo[4]; // ATT_STATUS_ONE
				updateAttendance[0][7] = hrsAndStatusTwo[5]; // ATT_STATUS_TWO
				updateAttendance[0][8] = empId; // ATT_EMP_ID
				updateAttendance[0][9] = attendance_date; // ATT_DATE

				updateAttendance(currentYear, updateAttendance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**@Method Name: calculateLogoutAttendance()
	 * @purpose : Calculate Logout Time in Regular Shift
	 * @param empId
	 * @param currentDate
	 * @param logoutTime
	 * @param currentYear
	 * @param existedAttendance
	 * @param branchWiseHolidayFlag
	 * @param dateTimeLogout
	 */
	private void calculateLogoutAttendance(String empId, String currentDate,
			String logoutTime, String currentYear,
			Object[][] existedAttendance, boolean branchWiseHolidayFlag,
			String dateTimeLogout, boolean isAttendanceExist) {
		/*
		 * calculateLogoutAttendance(empId, currentDate, currentTime,
		 * currentYear, existedAttendance, branchWiseHolidayFlag,
		 * currentDateTime);
		 */
		try {
			if (! isAttendanceExist) {
				int Millis_Day = 1000 * 60 * 60 * 24;
				Date prevDate = new Date();
				SimpleDateFormat prevDateFormatter = new SimpleDateFormat(
						"dd-MM-yyyy");
				String previousDate = prevDateFormatter.format(prevDate
						.getTime()
						- Millis_Day);
				currentDate= previousDate;
			}			
			/**
			 * If shift details exists, then only update the attendance
			 */
			Object[][] shiftDetails = getShiftDetails(empId, currentDate);

			if (shiftDetails != null && shiftDetails.length > 0) {
				String loginTime = String.valueOf(existedAttendance[0][1]);
				/**
				 * Calculate working hrs, extra hrs, early hrs and status two
				 */
				String[] hrsAndStatusTwo = calculateHrsAndStatus(empId,
						currentDate, branchWiseHolidayFlag, shiftDetails,
						loginTime, logoutTime, "N","N");
				/**
				 * Get updated attendance
				 */
				Object[][] updateAttendance = new Object[1][10];
				updateAttendance[0][0] = String.valueOf(shiftDetails[0][2]); // ATT_SHIFT_ID
				updateAttendance[0][1] = dateTimeLogout; // ATT_LOGOUT
				updateAttendance[0][2] = hrsAndStatusTwo[0]; // ATT_LATE_HRS
				updateAttendance[0][3] = hrsAndStatusTwo[1]; // ATT_WORKING_HRS
				updateAttendance[0][4] = hrsAndStatusTwo[2]; // ATT_EXTRAHRS
				updateAttendance[0][5] = hrsAndStatusTwo[3]; // ATT_EARLY_HRS
				updateAttendance[0][6] = hrsAndStatusTwo[4]; // ATT_STATUS_ONE
				updateAttendance[0][7] = hrsAndStatusTwo[5]; // ATT_STATUS_TWO
				updateAttendance[0][8] = empId; // ATT_EMP_ID
				updateAttendance[0][9] = currentDate; // ATT_DATE
				/**
				 * Update attendance
				 */
				updateAttendance(currentYear, updateAttendance);
			}
		} catch (Exception e) {
			logger.error("Exception in calculateLogoutAttendance:" + e);
		}
	}

	/**@Method Name: updateAttendance()
	 * @purpose : Used to Update Record in HRMS_DAILY_ATTENDANCE Table
	 * @param currentYear
	 * @param updateAttendance
	 */
	private void updateAttendance(String currentYear,
			Object[][] updateAttendance) {
		try {
			String updateAttendanceSql = " UPDATE HRMS_DAILY_ATTENDANCE_"
					+ currentYear
					+ " SET ATT_SHIFT_ID = ?, "
					+ " ATT_LOGOUT = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), "
					+ " ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ?, ATT_STATUS_TWO = ? "
					+ " WHERE ATT_EMP_ID = ? AND ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') ";
			getSqlModel().singleExecute(updateAttendanceSql, updateAttendance);
		} catch (Exception e) {
			logger.error("Exception in updateAttendance:" + e);
		}
	}

	/** @Methid Name: calculateHrsAndStatus()
	 * @purpose : Used to Calculate Late, Extra, Early Leaving Hours
	 * @param empId
	 * @param currentDate
	 * @param branchWiseHolidayFlag
	 * @param shiftDetails
	 * @param loginTime
	 * @param logoutTime
	 * @param nightShiftFlag
	 * @return String[]
	 */
	private String[] calculateHrsAndStatus(String empId, String currentDate,
			boolean branchWiseHolidayFlag, Object[][] shiftDetails,
			String loginTime, String logoutTime, String nightShiftFlag, String flexiShiftFlag) {
		String[] hrsAndStatusTwo = { "00:00:00", "00:00:00", "00:00:00",
				"00:00:00", "PR", "IN" };
		String [] hrsAndStatus= new String[2];
		try {
			String statusTwo ="";
			Date loginTimeInDate = null, logoutTimeInDate = null, 
			shiftLateComingInDate = null, shiftFirstHalfDayInDate = null, 
			shiftSecondHalfDayInDate = null, shiftEarlyLeavingInDate = null, 
			shiftEndTimeInDate = null;

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			if (!(loginTime.equals("null") || loginTime.equals("")
					|| loginTime.equals(null) || loginTime == null)) {
				loginTimeInDate = sdf.parse(loginTime);
			}

			if (!(logoutTime.equals("null") || logoutTime.equals("")
					|| logoutTime.equals(null) || logoutTime == null)) {
				logoutTimeInDate = sdf.parse(logoutTime);
			}
           
			String shiftLateComing = String.valueOf(shiftDetails[0][3]);
			if (!(shiftLateComing.equals("null") || shiftLateComing.equals("")
					|| shiftLateComing.equals(null) || shiftLateComing == null)) {
				shiftLateComingInDate = sdf.parse(shiftLateComing);
			}

			String shiftFirstHalfDay = String.valueOf(shiftDetails[0][4]);
			if (!(shiftFirstHalfDay.equals("null")
					|| shiftFirstHalfDay.equals("")
					|| shiftFirstHalfDay.equals(null) || shiftFirstHalfDay == null)) {
				shiftFirstHalfDayInDate = sdf.parse(shiftFirstHalfDay);
			}

			String shiftSecondHalfDay = String.valueOf(shiftDetails[0][5]);
			if (!(shiftSecondHalfDay.equals("null")
					|| shiftSecondHalfDay.equals("")
					|| shiftSecondHalfDay.equals(null) || shiftSecondHalfDay == null)) {
				shiftSecondHalfDayInDate = sdf.parse(shiftSecondHalfDay);
			}

			String shiftEarlyLeaving = String.valueOf(shiftDetails[0][6]);
			if (!(shiftEarlyLeaving.equals("null")
					|| shiftEarlyLeaving.equals("")
					|| shiftEarlyLeaving.equals(null) || shiftEarlyLeaving == null)) {
				shiftEarlyLeavingInDate = sdf.parse(shiftEarlyLeaving);
			}

			String shiftEndTime = String.valueOf(shiftDetails[0][7]);
			if (!(shiftEndTime.equals("null") || shiftEndTime.equals("")
					|| shiftEndTime.equals(null) || shiftEndTime == null)) {
				shiftEndTimeInDate = sdf.parse(shiftEndTime);
			}
			String markAbsentAfter="00:00:00", 
			markFlexiLateLessThan="",markFlexiHDLateLessThan="",markFlexiAbsentBefore="";
			
			if (!(String.valueOf(shiftDetails[0][17]).equals("null") || String.valueOf(shiftDetails[0][17]).equals("")
					|| String.valueOf(shiftDetails[0][17]).equals(null) || String.valueOf(shiftDetails[0][17]) == null)) {
				markAbsentAfter= String.valueOf(shiftDetails[0][17]);		
		    }
			if (!(String.valueOf(shiftDetails[0][18]).equals("null") || String.valueOf(shiftDetails[0][18]).equals("")
					|| String.valueOf(shiftDetails[0][18]).equals(null) || String.valueOf(shiftDetails[0][18]) == null)) {
				markFlexiLateLessThan= String.valueOf(shiftDetails[0][18]);
		    }
			if (!(String.valueOf(shiftDetails[0][19]).equals("null") || String.valueOf(shiftDetails[0][19]).equals("")
					|| String.valueOf(shiftDetails[0][19]).equals(null) || String.valueOf(shiftDetails[0][19]) == null)) {
				markFlexiHDLateLessThan= String.valueOf(shiftDetails[0][19]);
			}
			if (!(String.valueOf(shiftDetails[0][20]).equals("null") || String.valueOf(shiftDetails[0][20]).equals("")
					|| String.valueOf(shiftDetails[0][20]).equals(null) || String.valueOf(shiftDetails[0][20]) == null)) {
				markFlexiAbsentBefore= String.valueOf(shiftDetails[0][20]);
			}
			
			/**
			 * Calculate late hours
			 */
			hrsAndStatus= calculateLateComingHrs(loginTime,
					shiftDetails, flexiShiftFlag,nightShiftFlag);
			hrsAndStatusTwo[0] = String.valueOf(hrsAndStatus[0]);

			if (loginTimeInDate != null && logoutTimeInDate != null) {
				/**
				 * Calculate working hours
				 */
				Calendar workingHrsCal = Calendar.getInstance();
				workingHrsCal.setTime(logoutTimeInDate);

				workingHrsCal.add(Calendar.HOUR, -loginTimeInDate.getHours());
				workingHrsCal.add(Calendar.MINUTE, -loginTimeInDate
						.getMinutes());
				workingHrsCal.add(Calendar.SECOND, -loginTimeInDate
						.getSeconds());

				hrsAndStatusTwo[1] = sdf.format(workingHrsCal.getTime());
				/**
				 * Calculate extra hours
				 */
				if (shiftEndTimeInDate != null
						&& logoutTimeInDate.after(shiftEndTimeInDate)) {
					Calendar extraHrsCal = Calendar.getInstance();
					extraHrsCal.setTime(logoutTimeInDate);

					extraHrsCal.add(Calendar.HOUR, -shiftEndTimeInDate
							.getHours());
					extraHrsCal.add(Calendar.MINUTE, -shiftEndTimeInDate
							.getMinutes());
					extraHrsCal.add(Calendar.SECOND, -shiftEndTimeInDate
							.getSeconds());

					hrsAndStatusTwo[2] = sdf.format(extraHrsCal.getTime());
				}			
				/**
				 * Calculate early leaving hours
				 */
				if (shiftSecondHalfDayInDate != null
						&& shiftEarlyLeavingInDate != null
						&& logoutTimeInDate.after(shiftSecondHalfDayInDate)
						&& logoutTimeInDate.before(shiftEarlyLeavingInDate)) {
					Calendar earlyLeavingHrsCal = Calendar.getInstance();
					earlyLeavingHrsCal.setTime(shiftEarlyLeavingInDate);

					earlyLeavingHrsCal.add(Calendar.HOUR, -logoutTimeInDate
							.getHours());
					earlyLeavingHrsCal.add(Calendar.MINUTE, -logoutTimeInDate
							.getMinutes());
					earlyLeavingHrsCal.add(Calendar.SECOND, -logoutTimeInDate
							.getSeconds());
					hrsAndStatusTwo[3] = sdf.format(earlyLeavingHrsCal
							.getTime());
				}
				/**
				 * Calculate status one
				 */
				hrsAndStatusTwo[4] = calculateStatusOne(empId, currentDate,
						branchWiseHolidayFlag, shiftDetails);				
				/**
				 * Calculate status two
				 */
				 if (shiftLateComingInDate != null
						&& shiftFirstHalfDayInDate != null
						&& shiftSecondHalfDayInDate != null
						&& shiftEarlyLeavingInDate != null) {
					 statusTwo = "IN";
					if ((loginTimeInDate.after(shiftLateComingInDate) && (loginTimeInDate
							.before(shiftFirstHalfDayInDate) || loginTimeInDate
							.equals(shiftFirstHalfDayInDate)))
							&& ((logoutTimeInDate
									.after(shiftSecondHalfDayInDate) || logoutTimeInDate
									.equals(shiftEarlyLeavingInDate)) && logoutTimeInDate
									.before(shiftEarlyLeavingInDate))) {
						statusTwo = "DL";
					} else if (loginTimeInDate.after(shiftFirstHalfDayInDate)
							|| logoutTimeInDate
									.before(shiftSecondHalfDayInDate)) {
						statusTwo = "HD";
					} else if (loginTimeInDate.after(shiftLateComingInDate)
							&& (loginTimeInDate.before(shiftFirstHalfDayInDate) || loginTimeInDate
									.equals(shiftFirstHalfDayInDate))) {
						statusTwo = "LC";
					} else if ((logoutTimeInDate
							.after(shiftSecondHalfDayInDate) || logoutTimeInDate
							.equals(shiftEarlyLeavingInDate))
							&& logoutTimeInDate.before(shiftEarlyLeavingInDate)) {
						statusTwo = "EL";
					}																		
				}
				 /** Check For mark Absent */
				if (flexiShiftFlag.equals("N")
							&& !(String.valueOf(shiftDetails[0][17])).equals("00:00:00")) {
						if (!loginTime.equals("00.00.00")
								&& !markAbsentAfter.equals("00:00:00")) {
							// System.out.println("UPLOAD : MARK ABSENT");
							int employeeInTime = TimeUtility.getMinute(loginTime);
							int markAbsent = TimeUtility
									.getMinute(markAbsentAfter);
							if (employeeInTime > markAbsent) {
								statusTwo = "AB";
							}
						}
				}	
				if(logoutTimeInDate.before(loginTimeInDate)){
					 if (shiftLateComingInDate != null
								&& shiftFirstHalfDayInDate != null) {
							 statusTwo = "IN";
							  if (loginTimeInDate.after(shiftFirstHalfDayInDate)) {
									statusTwo = "HD";
								} else if (loginTimeInDate.after(shiftLateComingInDate)
										&& (loginTimeInDate.before(shiftFirstHalfDayInDate) || loginTimeInDate
												.equals(shiftFirstHalfDayInDate))) {
									statusTwo = "LC";
								}
					 }
				}
				
				/** Check here Status For Night Shift
				 * and update the status according to In and 
				 * Out Time of Employee */
				if (nightShiftFlag.equals("Y")) {
					int inTime = Integer.parseInt((loginTime
							.substring(0, 5)).replaceAll(":", ""));
					int outTime = Integer.parseInt((logoutTime.substring(0,
							5)).replaceAll(":", ""));
					int startTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][16]).substring(0, 5))
							.replaceAll(":", ""));
					int endTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][7]).substring(0, 5))
							.replaceAll(":", ""));
					int fstHalfTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][4]).substring(0, 5))
							.replaceAll(":", ""));
					int scdHalfTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][5]).substring(0, 5))
							.replaceAll(":", ""));
					int nightlateMarkTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][3]).substring(0, 5))
							.replaceAll(":", ""));
					int nightearlyLeavingEndTime = Integer.parseInt((String
							.valueOf(shiftDetails[0][6]).substring(0, 5))
							.replaceAll(":", ""));
					if (inTime < 1200) {
						inTime += 2400;
					}
					if (outTime < 1200) {
						outTime += 2400;
					}
					if (startTime < 1200) {
						startTime += 2400;
					}
					if (endTime < 1200) {
						endTime += 2400;
					}
					if (fstHalfTime < 1200) {
						fstHalfTime += 2400;
					}
					if (scdHalfTime < 1200) {
						scdHalfTime += 2400;
					}
					if (nightlateMarkTime < 1200) {
						nightlateMarkTime += 2400;
					}
					if (nightearlyLeavingEndTime < 1200) {
						nightearlyLeavingEndTime += 2400;
					}
					if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))
							&& ((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
						statusTwo = "DL";
					} else if (inTime > fstHalfTime) {
						statusTwo = "HD";
					} else if (outTime < scdHalfTime) {
						statusTwo = "HD";
					} else if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
						statusTwo = "LC";
					} else if (((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
						statusTwo = "EL";
					} else if (inTime > 0
							&& outTime > 0
							&& (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
							&& (outTime == nightearlyLeavingEndTime)
							|| outTime > nightearlyLeavingEndTime) {
						statusTwo = "IN";
					} else {
						statusTwo = "AB";
					}
				}
				if(flexiShiftFlag.equals("Y")){

					Calendar flex = Calendar.getInstance();
					flex.setTime(logoutTimeInDate);
					flex.add(Calendar.HOUR, -loginTimeInDate.getHours());
					flex.add(Calendar.MINUTE, -loginTimeInDate.getMinutes());
					flex.add(Calendar.SECOND, -loginTimeInDate.getSeconds());
					int employeeWorkHours = TimeUtility.getMinute(String
							.valueOf(sdf.format(flex.getTime())));						
					int flexiLateHours = TimeUtility
							.getMinute(markFlexiLateLessThan);
					int flexiHalfDayHours = TimeUtility
							.getMinute(markFlexiHDLateLessThan);
					int flexiAbsentBeforeHours= TimeUtility.getMinute(markFlexiAbsentBefore);
					if (employeeWorkHours == 0) {
						statusTwo = "AB";
					} 
					else if(employeeWorkHours < flexiAbsentBeforeHours){
						statusTwo = "AB";
					}					
					else if (employeeWorkHours >= flexiLateHours) {
						statusTwo = "IN";
					} else if (employeeWorkHours < flexiHalfDayHours) {
						statusTwo = "HD";
					} else if ((employeeWorkHours < flexiLateHours)
							& (flexiLateHours > flexiHalfDayHours)) {
						statusTwo = "LC";		
					}
					if (!loginTime.equals("00:00:00")
							&& logoutTime.equals("00:00:00")) {
						statusTwo = "HD";
					}					
				}				
				hrsAndStatusTwo[5] = statusTwo;
			}
		} catch (Exception e) {
			logger.error("Exception in calculateHrsAndStatusTwo:" + e);
		}
		return hrsAndStatusTwo;
	}

	/**@Method Name : calculateLoginAttendance()
	 *@purpose : Used to insert record Into HRMS_DAILY_ATTENDANCE when Employee Do DayIn 
	 * @param empId
	 * @param currentDate
	 * @param loginTime
	 * @param currentYear
	 * @param branchWiseHolidayFlag
	 * @param currentDateTime
	 * @param nightShiftFlag
	 * @param isAppliedLeave 
	 */
	private void  calculateLoginAttendance(String empId, String currentDate,
			String loginTime, String currentYear,
			boolean branchWiseHolidayFlag, String currentDateTime,
			String nightShiftFlag, String flexiShiftFlag, boolean isAppliedLeave) {
		String [] hoursAndStatus = new String [2];
		try {	
			Object[][] lateResult;
			/**
			 * Get shift details
			 */
			Object[][] shiftDetails = getShiftDetails(empId, currentDate);

			if (shiftDetails != null && shiftDetails.length > 0) {
				String divisionId = String.valueOf(shiftDetails[0][0]);
				String branchId = String.valueOf(shiftDetails[0][1]);
				String shiftId = String.valueOf(shiftDetails[0][2]);				
				if(flexiShiftFlag.equals("N")){										
					int shiftStartTimeInt = Integer.parseInt(String
						.valueOf(shiftDetails[0][14]));
					int shiftEndTime = Integer.parseInt(String
						.valueOf(shiftDetails[0][15]));
					/** Check here if Employee's Shift is Night Shift and 
					 * Employee's In Time greater than Shift Start time 
					 * */
					if (nightShiftFlag.equals("Y")
						&& (shiftStartTimeInt > shiftEndTime)) {
						lateResult = checkForLate(empId, shiftDetails);
						if (lateResult[0][0].equals("true")) {
								currentDate = String.valueOf(lateResult[0][1]);
						}
					}
					hoursAndStatus=calculateLateComingHrs(loginTime,
							shiftDetails, flexiShiftFlag,nightShiftFlag);
				}//end if
				/**If Shift is Flexi Shift
				 * On Working Hours only it will display Status*/
				else{
					hoursAndStatus[0]= "00:00:00";
					hoursAndStatus[1]= "HD";
				}
				/**
				 * To Insert attendance
				 */
				Object[][] insertAttendance = new Object[1][9];
				insertAttendance[0][0] = empId; // ATT_EMP_ID
				insertAttendance[0][1] = currentDate; // ATT_DATE
				insertAttendance[0][2] = currentDateTime; // ATT_LOGIN
				insertAttendance[0][3] = divisionId; // ATT_DIVISION
				insertAttendance[0][4] = branchId; // ATT_BRANCH
				insertAttendance[0][5] = shiftId; // ATT_SHIFT_ID
				insertAttendance[0][6] = hoursAndStatus[0];// ATT_LATE_HRS
				insertAttendance[0][7] = calculateStatusOne(empId, currentDate,
						branchWiseHolidayFlag, shiftDetails); // ATT_STATUS_ONE
				insertAttendance[0][8]= String.valueOf(hoursAndStatus[1]);
				/**
				 * If Leave is already applied in that case it will execute 
				 * and Update HRMS_DAILY_ATTENDANCE_YEAR table
				 */						
				if(isAppliedLeave){					
					String updateQuery=" UPDATE HRMS_DAILY_ATTENDANCE_"
										+ currentYear
										+ " SET ATT_EMP_ID=?, ATT_DATE= TO_DATE(?, 'DD-MM-YYYY'), ATT_LOGIN= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_DIVISION=?, "
										+ " ATT_BRANCH=?, ATT_SHIFT_ID=?, ATT_LATE_HRS= TO_DATE(?,'HH24:MI:SS'), "
										+ " ATT_STATUS_ONE=?, ATT_STATUS_TWO=?, ATT_LOGOUT = TO_DATE('00:00:00','HH24:MI:SS'), ATT_WORKING_HRS = TO_DATE('00:00:00','HH24:MI:SS'),"
										+ " ATT_EXTRAHRS = TO_DATE('00:00:00','HH24:MI:SS'), ATT_EARLY_HRS=TO_DATE('00:00:00','HH24:MI:SS')"
										+ " WHERE ATT_EMP_ID = "+empId+" AND ATT_DATE = TO_DATE('"+currentDate+"', 'DD-MM-YYYY') ";
				     getSqlModel().singleExecute(updateQuery, insertAttendance);
				}//end if
				else
					insertAttendance(insertAttendance, currentYear);
				/**call this function to insert 
				 * last 15 days attendance record if not exist
				 * */
				addPreviousAttendance(empId, branchId, divisionId, shiftId,
						currentYear, shiftDetails);
			}
		} catch (Exception e) {
			logger.error("Exception in calculateLoginAttendance:" + e);
		}
	}

	/**@Method Name: addPreviousAttendance
	 * @purpose : This function is for checking and inserting attendance record of last 15
	 * days from login date.
	 * 
	 * @param empId :
	 *            employee id of employee
	 * @param branchId :
	 *            branch id of employee
	 * @param divisionId :
	 *            division id of employee
	 * @param shiftId :
	 *            Shift id of employee
	 * @param currentYear :
	 *            current year
	 * @param shiftDetails :
	 *            object which holds shift detail of employee
	 */
	private void addPreviousAttendance(String empId, String branchId,
			String divisionId, String shiftId, String currentYear,
			Object[][] shiftDetails) {
		try {
			Date dojDate = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

			Date selectDate = new Date();
			SimpleDateFormat selectFormatter = new SimpleDateFormat(
					"dd-MM-yyyy");

			String selectQuery = " SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empId;

			Object[][] dojObj = getSqlModel().getSingleResult(selectQuery);

			for (int i = 1; i <= 15; i++) {
				// Check whether attendance record is already available or not
				String checkPrev = "SELECT ATT_DATE FROM HRMS_DAILY_ATTENDANCE_"
						+ currentYear
						+ " WHERE ATT_EMP_ID="
						+ empId
						+ " AND TO_CHAR(ATT_DATE,'DD-MM-YYYY')=TO_CHAR(SYSDATE-"
						+ i + ",'DD-MM-YYYY')";

				Object[][] checkObj = getSqlModel().getSingleResult(checkPrev);
				// if there is no record for selected date then insert blank
				// attendance record for the date
				if (checkObj == null || checkObj.length == 0) {
					String selectDate1 = "SELECT TO_CHAR(SYSDATE-" + i
							+ ",'DD-MM-YYYY') FROM DUAL ";
					Object[][] selectDateObj = getSqlModel().getSingleResult(
							selectDate1);

					selectDate = selectFormatter.parse(String
							.valueOf(selectDateObj[0][0]));
					
					dojDate = dateFormatter.parse(String.valueOf(dojObj[0][0]));
					int result = dojDate.compareTo(selectDate);					
					if (result > 0) {
						break;
					}
					String selectedDate = selectDateObj[0][0].toString();

					/* Check for Weekly off */
					boolean isWeekOff = calculateWeekOff(selectedDate, shiftDetails);
					/* Check for Holiday */
					String attQuery = " SELECT DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false')"
										+ " AS CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF";
					Object[][] brHolidayObj = getSqlModel().getSingleResult(
							attQuery);
					boolean branchWiseHoliday = Boolean.parseBoolean((String
							.valueOf(brHolidayObj[0][0])));
					boolean isHoly = calculateHoliday(empId, selectedDate,
							branchWiseHoliday);

					/**
					 * this will return true if selected date is week off other
					 * wise false
					 */
					String statusOne;
					if (isWeekOff) {
						statusOne = "WO";// select status WO if its week off
					} else if (isHoly) {
						statusOne = "HO";
					} else {
						statusOne = "AB";// select status AB(absent) if its
											// not week off
					}

					/** query for inserting attendance detail */
					String insertAttendanceSql = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
							+ currentYear
							+ " (ATT_EMP_ID, ATT_DATE, ATT_LOGIN, ATT_DIVISION, "
							+ " ATT_BRANCH, ATT_SHIFT_ID, ATT_LATE_HRS, ATT_STATUS_ONE, "
							+ " ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_EARLY_HRS, ATT_STATUS_TWO) "
							+ " VALUES ("
							+ empId
							+ ", TO_DATE('"
							+ selectedDate
							+ "', 'DD-MM-YYYY'), "
							+ " TO_DATE('00:00:00', 'HH24:MI:SS'), "
							+ divisionId
							+ ","
							+ branchId
							+ ","
							+ shiftId
							+ ","
							+ " TO_DATE('00:00:00', 'HH24:MI:SS'), '"
							+ statusOne
							+ "', TO_DATE('00:00:00', 'HH24:MI:SS'), "
							+ " TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'),"
							+ " TO_DATE('00:00:00', 'HH24:MI:SS'), 'AB') ";
					getSqlModel().singleExecute(insertAttendanceSql);
				}
			}
		}catch(Exception e){
		e.printStackTrace();
	}

	}

	/**@Method Name : calculateHoliday
	 *@purpose : Used to calculate Holiday 
	 * @param empId
	 * @param currentDate
	 * @param branchWiseHolidayFlag
	 * @return boolean
	 */
	public boolean calculateHoliday(String empId, String currentDate,
			boolean branchWiseHolidayFlag) {
		boolean isHoliday = false;
		try {
			String holidaySql = "";
			/**
			 * Get branch wise holidays
			 */
			if (branchWiseHolidayFlag) {
				holidaySql =" SELECT HDATE  FROM ( "
					 		+ " SELECT HOLI_DATE AS HDATE FROM HRMS_HOLIDAY WHERE HOLI_TYPE='N' AND IS_ACTIVE='Y'"
					 		+ " UNION ALL " 
					 		+ " SELECT HOLI_DATE AS HDATE FROM HRMS_HOLIDAY_BRANCH WHERE "
					 		+ " CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+empId+"))" 
					 		+ " WHERE HDATE=TO_DATE('"+currentDate+"', 'DD-MM-YYYY')";
							/*" SELECT HOLI_DATE FROM HRMS_HOLIDAY_BRANCH "
							+ " WHERE HOLI_DATE = TO_DATE('"
							+ currentDate
							+ "', 'DD-MM-YYYY') "
							+ " AND CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
							+ empId + ") ";*/				
			}

			/**
			 * Get total holidays
			 */
			else {
				holidaySql = " SELECT HOLI_DATE FROM HRMS_HOLIDAY WHERE IS_ACTIVE='Y' AND HOLI_DATE = TO_DATE('"
						+ currentDate + "', 'DD-MM-YYYY') ";
			}
			Object[][] holidayObj = getSqlModel().getSingleResult(holidaySql);

			/**		
			 * Check whether current date is holiday or not
			 */
			if (holidayObj != null && holidayObj.length > 0) {
				isHoliday = true;
			}
		} catch (Exception e) {
			logger.error("Exception in isHolidaySet:" + e);
		}
		return isHoliday;
	}

	/**@Method Name : calculateLateComingHrs()
	 *@purpose : Used to calculate if Late Coming Hours
	 * @param loginTime
	 * @param shiftLateComing
	 * @param shiftFirstHalfDay
	 * @return String
	 */
	private String[] calculateLateComingHrs(String loginTime,
			Object[][] shiftDetails, String flexiShiftFlag, String nightShiftFlag) {
		String []calHoursAndStatus = new String[2];
		calHoursAndStatus[0]="00:00:00";
		calHoursAndStatus[1]="HD";
		try {
			
			if (shiftDetails != null && shiftDetails.length >0) {
				String shiftLateComing = String.valueOf(shiftDetails[0][3]);
				String shiftFirstHalfDay = String.valueOf(shiftDetails[0][4]);
				String markAbsentAfter = String.valueOf(shiftDetails[0][17]);
							if (!(shiftLateComing.equals("null") || shiftLateComing.equals("")
					|| shiftLateComing.equals(null) || shiftLateComing == null)) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				Date loginTimeInDate = null;
				Date shiftLateComingInDate = null;
				Date shiftFirstHalfDayInDate = null;

				if (!(loginTime.equals("null") || loginTime.equals("")
						|| loginTime.equals(null) || loginTime == null)) {
					loginTimeInDate = sdf.parse(loginTime);
				}

				if (!(shiftLateComing.equals("null")
						|| shiftLateComing.equals("")
						|| shiftLateComing.equals(null) || shiftLateComing == null)) {
					shiftLateComingInDate = sdf.parse(shiftLateComing);
				}

				if (!(shiftFirstHalfDay.equals("null")
						|| shiftFirstHalfDay.equals("")
						|| shiftFirstHalfDay.equals(null) || shiftFirstHalfDay == null)) {
					shiftFirstHalfDayInDate = sdf.parse(shiftFirstHalfDay);
				}

				/**
				 * Calculate late coming hrs
				 */
				if (loginTimeInDate != null && shiftLateComingInDate != null
						&& shiftFirstHalfDayInDate != null) {
					if (loginTimeInDate.after(shiftLateComingInDate)
							&& loginTimeInDate.before(shiftFirstHalfDayInDate)) {
						Calendar lateComingHrsCal = Calendar.getInstance();
						lateComingHrsCal.setTime(loginTimeInDate);
						lateComingHrsCal.add(Calendar.HOUR,
								-shiftLateComingInDate.getHours());
						lateComingHrsCal.add(Calendar.MINUTE,
								-shiftLateComingInDate.getMinutes());
						lateComingHrsCal.add(Calendar.SECOND,
								-shiftLateComingInDate.getSeconds());

						calHoursAndStatus[0] = sdf.format(lateComingHrsCal.getTime());
					}
				}
			}
			if (!(markAbsentAfter.equals("null") || markAbsentAfter.equals("")
					|| markAbsentAfter.equals(null) || markAbsentAfter == null)) {
				if (flexiShiftFlag.equals("N")
						&& !(markAbsentAfter).equals("00:00:00")) {
					if (!loginTime.equals("00.00.00")
							&& !markAbsentAfter.equals("00:00:00")) {
						int employeeInTime = TimeUtility.getMinute(loginTime);
						int markAbsent = TimeUtility
								.getMinute(markAbsentAfter);
						if(nightShiftFlag.equals("Y")){
							if (employeeInTime < markAbsent ){
								calHoursAndStatus[1] = "AB";
							}
						}
						else if (employeeInTime > markAbsent ){
							calHoursAndStatus[1] = "AB";
						}
					}
				}	
			}
			}
		} catch (Exception e) {
			logger.error("Exception in calculateLateComingHrs:" + e);
		}
		return calHoursAndStatus;
	}

	/**@Method Name :calculateStatusOne()
	 * @purpose : Used to check is it Holiday or Weekly Off else Present
	 * @param empId
	 * @param currentDate
	 * @param branchWiseHolidayFlag
	 * @param shiftDetails
	 * @return String
	 */
	private String calculateStatusOne(String empId, String currentDate,
			boolean branchWiseHolidayFlag, Object[][] shiftDetails) {
		String statusOne = "PR";
		try {
			/**
			 * Check - Current date is holiday
			 */
			boolean isHoliday = calculateHoliday(empId, currentDate,
					branchWiseHolidayFlag);
			if (isHoliday) {
				statusOne = "HO";
			}

			/**
			 * Check - Current date is week off
			 */
			else {
				boolean isWeekOff = calculateWeekOff(currentDate, shiftDetails);
				if (isWeekOff) {
					statusOne = "WO";
				}
			}
		} catch (Exception e) {
			logger.error("Exception in calculateStatusOne:" + e);
		}
		return statusOne;
	}

	/**@Method Name: calculateWeekOff()
	 * @purpose :Used to check whether it is Weekly Off
	 * @param currentDate
	 * @param shiftDetails
	 * @return boolean
	 */
	public boolean calculateWeekOff(String currentDate, Object[][] shiftDetails) {
		boolean isWeekOff = false;
		try {
			Calendar currentDay = Calendar.getInstance();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			currentDay.setTime(dateFormatter.parse(currentDate));

			// get day of week of specified date
			int DAY_OF_WEEK = currentDay.get(java.util.Calendar.WEEK_OF_MONTH);

			String[] weekDays = null;

			/**
			 * Get week off days
			 */
			if (DAY_OF_WEEK == 1) {
				weekDays = String.valueOf(shiftDetails[0][8]).split(",");
			} else if (DAY_OF_WEEK == 2) {
				weekDays = String.valueOf(shiftDetails[0][9]).split(",");
			} else if (DAY_OF_WEEK == 3) {
				weekDays = String.valueOf(shiftDetails[0][10]).split(",");
			} else if (DAY_OF_WEEK == 4) {
				weekDays = String.valueOf(shiftDetails[0][11]).split(",");
			} else if (DAY_OF_WEEK == 5) {
				weekDays = String.valueOf(shiftDetails[0][12]).split(",");
			} else if (DAY_OF_WEEK == 6) {
				weekDays = String.valueOf(shiftDetails[0][13]).split(",");
			}

			/**
			 * Find current date is weekly off or not
			 */
			for (int i = 0; i < weekDays.length; i++) {
				int weekDay = Integer.parseInt(weekDays[i]); // get week day

				switch (weekDay) {
				case 1:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 1) {
						isWeekOff = true;
					}
					break;
				case 2:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 2) {
						isWeekOff = true;
					}
					break;
				case 3:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 3) {
						isWeekOff = true;
					}
					break;
				case 4:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 4) {
						isWeekOff = true;
					}
					break;
				case 5:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 5) {
						isWeekOff = true;
					}
					break;
				case 6:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 6) {
						isWeekOff = true;
					}
					break;
				case 7:
					if (currentDay.get(Calendar.DAY_OF_WEEK) == 7) {
						isWeekOff = true;
					}
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in calculateWeekOff:" + e);
		}
		return isWeekOff;
	}

	/**@Method Name: getShiftDetails()
	 * @purpose :Used to return Shift Details
	 * @param empId
	 * @param currentDate
	 * @return Object[][]
	 */
	private Object[][] getShiftDetails(String empId, String currentDate) {
		Object[][] shiftDetails = null;
		try {
			/**
			 * Get shift details from exception
			 */
			String shiftExcpSql = " SELECT EMP_DIV, EMP_CENTER, HRMS_SHIFT_DTL.SHIFT_ID, "
					+ " TO_CHAR(SFT_DTL_IN_LM_AFTER_TIME, 'HH24:MI:SS') AS SFT_DTL_IN_LM_AFTER_TIME, "
					+ " TO_CHAR(SFT_DTL_IN_HD_AFTER_TIME, 'HH24:MI:SS') AS SFT_DTL_IN_HD_AFTER_TIME, "
					+ " TO_CHAR(SFT_DTL_OUT_HD_BEFORE_TIME, 'HH24:MI:SS') AS SFT_DTL_OUT_HD_BEFORE_TIME, "
					+ " TO_CHAR(SFT_DTL_OUT_EL_BEFORE_TIME, 'HH24:MI:SS') AS SFT_DTL_OUT_EL_BEFORE_TIME, "
					+ " TO_CHAR(SFT_DTL_END_TIME, 'HH24:MI:SS') AS SFT_DTL_END_TIME, SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, "
					+ " SHIFT_WEEK_6, TO_CHAR(SFT_DTL_START_TIME,'HH24MISS'), TO_CHAR(SFT_DTL_END_TIME,'HH24MISS'), "
					+ " TO_CHAR(SFT_DTL_START_TIME, 'HH24:MI:SS') AS SFT_DTL_START_TIME,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_MARK_ABSENT_AFTER, 'HH24:MI:SS'), '00:00:00') AS MARK_ABSENT,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_LATE_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_LATE_MARK,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_HALFDAY_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_HALFDAY_MARK,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_ABSENT_MARK"
					+ " FROM HRMS_SHIFT_DTL "
					+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_DTL.SHIFT_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) "
					+ " WHERE EMP_ID = "
					+ empId
					+ " AND SFT_DTL_EXCEPTIONAL_DAY = TO_NUMBER(DECODE(TRIM(TO_CHAR(TO_DATE('"
					+ currentDate
					+ "', 'DD-MM-YYYY'), 'DAY')), "
					+ " 'SUNDAY', 1, 'MONDAY', 2, 'TUESDAY', 3,  'WEDNESDAY', 4, 'THURSDAY', 5, 'FRIDAY', 6, 'SATURDAY', 7)) ";
			Object[][] shiftExcpDetails = getSqlModel().getSingleResult(
					shiftExcpSql);

			if (shiftExcpDetails != null && shiftExcpDetails.length > 0) {
				shiftDetails = shiftExcpDetails;
			}

			/**
			 * Get shift details from shift master
			 */
			else {
				String shiftDetailsSql = " SELECT EMP_DIV, EMP_CENTER, SHIFT_ID, TO_CHAR(SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS') AS SFT_IN_LM_AFTER_TIME, "
						+ " TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS') AS SFT_IN_HD_AFTER_TIME, "
						+ " TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS') AS SFT_OUT_HD_BEFORE_TIME, "
						+ " TO_CHAR(SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS') AS SFT_OUT_EL_BEFORE_TIME, TO_CHAR(SFT_END_TIME, 'HH24:MI:SS') AS SFT_END_TIME, "
						+ " SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6, "
						+ " TO_CHAR(SFT_START_TIME,'HH24MISS'), TO_CHAR(SFT_END_TIME,'HH24MISS'), "
						+ " TO_CHAR(SFT_START_TIME, 'HH24:MI:SS') AS SFT_START_TIME, "
						+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_MARK_ABSENT_AFTER, 'HH24:MI:SS'), '00:00:00') AS MARK_ABSENT,"
					 	+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_LATE_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_LATE_MARK,"
					 	+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_HALFDAY_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_HALFDAY_MARK,"
					 	+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_ABSENT_MARK"
						+ " FROM HRMS_SHIFT "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) "
						+ " WHERE EMP_ID = " + empId;
				shiftDetails = getSqlModel().getSingleResult(shiftDetailsSql);
			}
		} catch (Exception e) {
			logger.error("Exception in getShiftDetails:" + e);
		}
		return shiftDetails;
	}

	/**@Method Name: 
	 * @purpose :Used to insert Record into HRMS_DAILY_ATTENDANCE
	 * @param insertAttendance
	 * @param currentYear
	 */
	private void insertAttendance(Object[][] insertAttendance,
			String currentYear) {
		try {
			String insertAttendanceSql = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
					+ currentYear
					+ " (ATT_EMP_ID, ATT_DATE, ATT_LOGIN, ATT_DIVISION, "
					+ " ATT_BRANCH, ATT_SHIFT_ID, ATT_LATE_HRS, ATT_STATUS_ONE, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_EARLY_HRS, ATT_STATUS_TWO) "
					+ " VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ?, ?, ?, TO_DATE(?, 'HH24:MI:SS'), ?, TO_DATE('00:00:00', 'HH24:MI:SS'), "
					+ " TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'), ?) ";
			getSqlModel().singleExecute(insertAttendanceSql, insertAttendance);
		} catch (Exception e) {
			logger.error("Exception in insertAttendance:" + e);
		}
	}

	/**@Method Name: getExistedAttendance()
	 * @purpose :Used to check is it Record Exist for Particular date
	 * @param currentYear
	 * @param empId
	 * @param currentDate
	 * @return Object[][]
	 */	
	private Object[][] getExistedAttendance(String currentYear, String empId,
			String currentDate) {
		Object[][] existedAttendance = null;
		try {						
			String attendanceExistsSql= " SELECT NVL(ATT_REG_STATUS_TWO,'') AS ATT_REG_STATUS_TWO,"
					 + " TO_CHAR(ATT_LOGIN,'HH24:MI:SS')" 
					 + " FROM HRMS_DAILY_ATTENDANCE_"+ currentYear
					 + " WHERE ATT_EMP_ID ="+ empId
					 + " AND ATT_DATE = TO_DATE('"+currentDate+"', 'DD-MM-YYYY')";
			existedAttendance = getSqlModel().getSingleResult(
					attendanceExistsSql);
		} catch (Exception e) {
			logger.error("Exception in isAttendanceExistsForCurrentDate:" + e);
		}
		return existedAttendance;
	}
	
	/**@Method Name : checkForEarly
	 * @purpose : Used to Check If Employee DayOut before Shift End Time
	 * @param empID
	 * @return boolean
	 */
	private boolean checkForEarly(String empID) {
		boolean result = false;
		try {
			String loginTime;
			String loginDate;
			Date date = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = dateFormatter.format(date);
			String currentYear = currentDate.substring(6, 10);
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
			String currentTime = timeFormatter.format(date);
			DateFormat compareTime = new SimpleDateFormat("HH:mm:ss");
			Date currTime = compareTime.parse(currentTime);
			String query = "SELECT TO_CHAR(ATT_LOGIN,'DD-MM-YYYY'),TO_CHAR(ATT_LOGIN,'HH:MM:SS')"
					+ " FROM HRMS_DAILY_ATTENDANCE_"
					+ currentYear
					+ " WHERE ATT_DATE=TO_DATE('"
					+ currentDate
					+ "','DD-MM-YYYY') AND ATT_EMP_ID=" + empID;
			Object[][] timeObj = getSqlModel().getSingleResult(query);
			if (timeObj != null && timeObj.length > 0) {
				loginDate = String.valueOf(timeObj[0][0]);
				loginTime = String.valueOf(timeObj[0][1]);
				Date logTime = compareTime.parse(loginTime);
				currTime.compareTo(logTime);
				if (currentDate.equals(loginDate)
						&& currTime.compareTo(logTime) > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**@Method Name :  checkForLate()
	 * @purpose :Used to check that if Employee InTime is greater than Shift's In time
	 * @param empId
	 * @param shiftDetails
	 * @return Object [][]
	 */
	private Object[][] checkForLate(String empId, Object[][] shiftDetails) {
		Object[][] returnParamObj = new Object[1][2];
		try {		
			int shiftIntEnd =0, currentIntTime=0;
			int MilliDay = 1000 * 60 * 60 * 24;
			Date date = new Date();
			Date prevDate = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = dateFormatter.format(date);
			String currentYear = currentDate.substring(6, 10);
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
			String currentTime = timeFormatter.format(date);
			String previousDate = dateFormatter.format(prevDate.getTime()
					- MilliDay);
			if(shiftDetails!= null && shiftDetails.length >0){
				String shiftEndTime = String.valueOf(shiftDetails[0][15]);
				shiftIntEnd = Integer.parseInt(shiftEndTime);
				currentTime = currentTime.replace(":", "");
				currentIntTime = Integer.parseInt(currentTime);
			}
			returnParamObj[0][0] = "false";
			returnParamObj[0][1] = currentDate;
			String query = "SELECT  ATT_LOGIN FROM HRMS_DAILY_ATTENDANCE_"
					+ currentYear + " WHERE ATT_DATE=TO_DATE('" + previousDate
					+ "','DD-MM-YYYY') AND ATT_EMP_ID=" + empId;
			Object[][] loginTime = getSqlModel().getSingleResult(query);
			if (loginTime == null || loginTime.length == 0) {
				boolean isWeekOff = calculateWeekOff(previousDate, shiftDetails);
				String attQuery = " SELECT DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false')"
								+ " AS CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF";
				Object[][] brHolidayObj = getSqlModel().getSingleResult(
						attQuery);
				boolean branchWiseHoliday = Boolean.parseBoolean((String
						.valueOf(brHolidayObj[0][0])));
				boolean isHoly = calculateHoliday(empId, previousDate,
						branchWiseHoliday);
				if (currentIntTime < shiftIntEnd) {
					returnParamObj[0][0] = "true";
					returnParamObj[0][1] = previousDate;
				} 
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnParamObj;
	}
}