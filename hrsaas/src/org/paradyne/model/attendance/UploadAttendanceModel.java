package org.paradyne.model.attendance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.attendance.UploadAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.TimeUtility;

/**
 * @author tarunc
 * @date Jul 02, 2008
 * @description UploadAttendanceModel serves as model for upload attendance form
 */
/**
 * @Modified By AA1711
 * @Description Modified For Flexi Shift And Night Shift
 */
public class UploadAttendanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(UploadAttendanceModel.class);
	private ArrayList<String> columnNames = null;

	/**
	 * @Method Name : calculateLateAndEarlyHrs
	 * @purpose Used to calculate Status according to Login and Logout Time
	 * @param empInTime
	 * @param empOutTime
	 * @param offcStartTime
	 * @param offcEndTime
	 * @param firstHalfTime
	 * @param secondHalfTime
	 * @param nightFlag
	 * @param lateMarkTime
	 * @param earlyLeavingTime
	 * @param flexiTimeFromQuery
	 * @param shiftWorkingHoursFromQuery
	 * @param markAbsentAfterThisTime
	 * @param markFlexiLateLessThan
	 * @param markFlexiHalfDayLateLessThan
	 * @return Object[][]
	 */
	private Object[] calculateLateAndEarlyHrs(String empInTime,
			String empOutTime, String offcStartTime, String offcEndTime,
			String firstHalfTime, String secondHalfTime, String nightFlag,
			String lateMarkTime, String earlyLeavingTime,
			String flexiTimeFromQuery, String shiftWorkingHoursFromQuery,
			String markAbsentAfterThisTime, String markFlexiLateLessThan,
			String markFlexiHalfDayLateLessThan, String markFlexiAbsentBefore) {
		//System.out.println("FETCH FROM SERVER : calculateLateAndEarlyHrs");
		String status = "";
		// late hrs early hrs extra hrs status in time out time work hrs
		Object[] calculateTime = { "00:00:00", "00:00:00", "N", "N",
				"00:00:00", status, empInTime, empOutTime, "00:00:00" };

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			Date dateEmpInTime = sdf.parse(empInTime);
			Date dateEmpOutTime = sdf.parse(empOutTime);
			Date dateOffcEndTime = sdf.parse(offcEndTime);
			if (lateMarkTime.equals("00:00:00")) {
				lateMarkTime = offcStartTime;
			}
			Date lateMarkStartTime = sdf.parse(lateMarkTime);

			if (firstHalfTime.equals("00:00:00")) {
				firstHalfTime = lateMarkTime;
			}
			/** Convert Time into Date Format */
			Date dateFirstHalfTime = sdf.parse(firstHalfTime);

			if (earlyLeavingTime.equals("00:00:00")) {
				earlyLeavingTime = offcEndTime;
			}
			Date earlyLeavingEndTime = sdf.parse(earlyLeavingTime);

			if (secondHalfTime.equals("00:00:00")) {
				secondHalfTime = earlyLeavingTime;
			}
			Date dateSecondHalfTime = sdf.parse(secondHalfTime);

			Calendar c1 = Calendar.getInstance();

			if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
					.before(dateFirstHalfTime) || dateEmpInTime
					.equals(dateFirstHalfTime)))
					&& ((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
							.equals(dateSecondHalfTime)) && dateEmpOutTime
							.before(earlyLeavingEndTime))) {
				calculateTime[3] = "Y";

				c1.setTime(dateEmpInTime);
				c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
				c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
				c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours
				if (calculateTime[0] == null)
					calculateTime[0] = "00:00:00";

				c1.setTime(earlyLeavingEndTime);
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours
				status = "DL";
			} else if (dateEmpInTime.after(dateFirstHalfTime)
					&& !empInTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if (dateEmpOutTime.before(dateSecondHalfTime)
					&& !empOutTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
					.before(dateFirstHalfTime) || dateEmpInTime
					.equals(dateFirstHalfTime)))) {
				c1.setTime(dateEmpInTime);
				c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
				c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
				c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours
				if (calculateTime[0] == null)
					calculateTime[0] = "00:00:00";
				status = "LC";
			} else if (((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
					.equals(dateSecondHalfTime)) && dateEmpOutTime
					.before(earlyLeavingEndTime))) {
				c1.setTime(earlyLeavingEndTime);
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours
				if (calculateTime[1] == null)
					calculateTime[1] = "00:00:00";
				status = "EL";
			} else if ((!empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					&& (dateEmpInTime.before(lateMarkStartTime) || dateEmpInTime
							.equals(lateMarkStartTime))
					&& (dateEmpOutTime.after(earlyLeavingEndTime) || dateEmpOutTime
							.equals(earlyLeavingEndTime))) {
				status = "IN";
			} else if ((empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					|| (!empInTime.equals("00:00:00") && empOutTime
							.equals("00:00:00"))) {
				status = "HD";
			} else {
				status = "AB";
			}

			if (empOutTime.equals("00:00:00") || offcEndTime.equals("00:00:00")) {
				calculateTime[4] = "00:00:00"; // extra hours
				if (calculateTime[4] == null)
					calculateTime[4] = "00:00:00";
			} else {
				// CALCULATION FOR EXTRA HOURS -- FETCH FROM SERVER
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateEmpOutTime);
				cal.add(Calendar.HOUR, -dateEmpInTime.getHours());
				cal.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				cal.add(Calendar.SECOND, -dateEmpInTime.getSeconds());

				Date actualWorkHrs = cal.getTime();
				String convertedShiftWorkingHours = getHH_MM_SS(Integer
						.parseInt(shiftWorkingHoursFromQuery));
				Date shiftWorkingHrs = sdf.parse(convertedShiftWorkingHours);
				if (actualWorkHrs.after(shiftWorkingHrs)) {
					Calendar extraHours = Calendar.getInstance();
					extraHours.setTime(actualWorkHrs);
					extraHours.add(Calendar.HOUR, -shiftWorkingHrs.getHours());
					extraHours.add(Calendar.MINUTE, -shiftWorkingHrs
							.getMinutes());
					extraHours.add(Calendar.SECOND, -shiftWorkingHrs
							.getSeconds());
					calculateTime[4] = sdf.format(extraHours.getTime());
					if (calculateTime[4] == null)
						calculateTime[4] = "00:00:00";
				}
			}
			/** If Out Time is Less Than In Time
			 * If Employee Stretched till Next date In Regular Shift*/
			if(dateEmpOutTime.before(dateEmpInTime)){
				 if (dateEmpInTime.after(dateFirstHalfTime)
						&& !empInTime.equals("00:00:00")) {
					calculateTime[2] = "Y";
					status = "HD";
				 }
				 else if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
						.before(dateFirstHalfTime) || dateEmpInTime
						.equals(dateFirstHalfTime)))) {
					c1.setTime(dateEmpInTime);
					c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
					c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
					c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

					calculateTime[0] = sdf.format(c1.getTime()); // late hours
					if (calculateTime[0] == null)
						calculateTime[0] = "00:00:00";
					status = "LC";
				}
			}

			/*
			 * else if (dateEmpOutTime.after(dateOffcEndTime)) {
			 * //System.out.println("FETCH ELSE : empOutTime : "+empOutTime);
			 * //System.out.println("FETCH ELSE : offcEndTime : "+offcEndTime);
			 * c1.setTime(dateEmpOutTime); c1.add(Calendar.HOUR,
			 * -dateOffcEndTime.getHours()); c1.add(Calendar.MINUTE,
			 * -dateOffcEndTime.getMinutes()); c1.add(Calendar.SECOND,
			 * -dateOffcEndTime.getSeconds());
			 * 
			 * calculateTime[4] = sdf.format(c1.getTime()); // extra hours }
			 */
			if (flexiTimeFromQuery.equals("N")
					&& !markAbsentAfterThisTime.equals("00:00:00")) {
				int employeeInTime = TimeUtility.getMinute(empInTime);
				int markAbsent = TimeUtility.getMinute(markAbsentAfterThisTime);
				if (employeeInTime > markAbsent) {
					status = "AB";
				}
			}
			/** Updated for If Shift is Flexi Shift */
			if (flexiTimeFromQuery.equals("Y")) {
				Calendar flex = Calendar.getInstance();
				flex.setTime(dateEmpOutTime);
				flex.add(Calendar.HOUR, -dateEmpInTime.getHours());
				flex.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				flex.add(Calendar.SECOND, -dateEmpInTime.getSeconds());				
				int employeeWorkHours = TimeUtility.getMinute(String
						.valueOf(sdf.format(flex.getTime())));
				int shiftWorkingHours = Integer
						.parseInt(shiftWorkingHoursFromQuery);
				int flexiLateHours = TimeUtility
						.getMinute(markFlexiLateLessThan);
				int flexiHalfDayHours = TimeUtility
						.getMinute(markFlexiHalfDayLateLessThan);
				int flexiAbsentDayHours= TimeUtility.getMinute(markFlexiAbsentBefore);
				if (employeeWorkHours == 0) {
					status = "AB";
				} else if ((empInTime.equals("00:00:00") && !empOutTime
						.equals("00:00:00"))
						|| (!empInTime.equals("00:00:00") && empOutTime
								.equals("00:00:00"))) {
					status = "HD";
				} else if (employeeWorkHours < flexiAbsentDayHours){
					status = "AB";
				} else if (employeeWorkHours >= flexiLateHours) {
					status = "IN";
				} else if (employeeWorkHours < flexiHalfDayHours) {
					status = "HD";
				} else if ((employeeWorkHours < flexiLateHours)
						& (flexiLateHours > flexiHalfDayHours)) {
					status = "LC";
				} 		
			}
			/** Update For Shift If Shift is night Shift */
			if (nightFlag.equals("Y")) {
				int inTime = Integer.parseInt((empInTime.substring(0, 5))
						.replaceAll(":", ""));
				int outTime = Integer.parseInt((empOutTime.substring(0, 5))
						.replaceAll(":", ""));
				int startTime = Integer
						.parseInt((offcStartTime.substring(0, 5)).replaceAll(
								":", ""));
				int endTime = Integer.parseInt((offcEndTime.substring(0, 5))
						.replaceAll(":", ""));
				int fstHalfTime = Integer.parseInt((firstHalfTime.substring(0,
						5)).replaceAll(":", ""));
				int scdHalfTime = Integer.parseInt((secondHalfTime.substring(0,
						5)).replaceAll(":", ""));

				int nightlateMarkTime = Integer.parseInt((lateMarkTime
						.substring(0, 5)).replaceAll(":", ""));
				int nightearlyLeavingEndTime = Integer
						.parseInt((earlyLeavingTime.substring(0, 5))
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
					status = "DL";
				} else if (inTime > fstHalfTime) {
					status = "HD";
				} else if (outTime < scdHalfTime) {
					status = "HD";
				} else if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
					status = "LC";
				} else if (((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
					status = "EL";
				} else if (inTime > 0
						&& outTime > 0
						&& (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
						&& (outTime == nightearlyLeavingEndTime)
						|| outTime > nightearlyLeavingEndTime) {
					status = "IN";
				} else {
					status = "AB";
				}
			}
			calculateTime[5] = status;
		} catch (Exception e) {
			logger.error("Exception in calculateLateAndEarlyHrs:" + e);
		}
		return calculateTime;
	}

	/**
	 * @method calculateLateAndEarlyHrs
	 * @purpose to calculate half day, late coming, early leaving, dual
	 *          late,absent etc
	 * @param bean
	 * @param empInTime
	 * @param empOutTime
	 * @param offcStartTime
	 * @param offcEndTime
	 * @param firstHalfTime
	 * @param secondHalfTime
	 * @param string2
	 * @param string
	 * @return Object[]
	 */

	public Object[] calculateLateAndEarlyHrs(UploadAttendance bean,
			String empInTime, String empOutTime, String offcStartTime,
			String offcEndTime, String firstHalfTime, String secondHalfTime,
			String nightFlag, String lateMarkTime, String earlyLeavingTime,
			String flexiTimeFromQuery, String shiftWorkingHoursFromQuery,
			String markAbsentAfterThisTime, String markFlexiLateLessThan,
			String markFlexiHalfDayLateLessThan) {
		String status = "";
		//System.out.println("UPLOAD ATTENDANCE : calculateLateAndEarlyHrs");
		// late hrs early hrs extra hrs status in time out time work hrs
		Object[] calculateTime = { "00:00:00", "00:00:00", "N", "N",
				"00:00:00", status, empInTime, empOutTime, "00:00:00" };
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			Date dateEmpInTime = sdf.parse(empInTime); // convert in time
			// employee in to date
			// format
			Date dateEmpOutTime = sdf.parse(empOutTime); // convert in time
			// employee out to
			// date format
			Date dateOffcStartTime = sdf.parse(offcStartTime); // convert shift
			// in time in to
			// date format
			Date dateOffcEndTime = sdf.parse(offcEndTime); // convert shift out
			// time in to date
			// format

			if (lateMarkTime.equals("00:00:00")) {
				lateMarkTime = offcStartTime;
			}
			Date lateMarkStartTime = sdf.parse(lateMarkTime);

			if (firstHalfTime.equals("00:00:00")) {
				firstHalfTime = lateMarkTime;
			}
			Date dateFirstHalfTime = sdf.parse(firstHalfTime); // convert fist
			// half day time
			// in to date
			// format

			if (earlyLeavingTime.equals("00:00:00")) {
				earlyLeavingTime = offcEndTime;
			}
			Date earlyLeavingEndTime = sdf.parse(earlyLeavingTime);

			if (secondHalfTime.equals("00:00:00")) {
				secondHalfTime = earlyLeavingTime;
			}
			Date dateSecondHalfTime = sdf.parse(secondHalfTime);
			// convert second H.D. time in to date format

			/**
			 * set in time and out time of the employee as per the out door
			 * start time and end time
			 */
			if (bean.isOutDoorFlag()) {
				Date dateOutDoorStartTime = sdf.parse(bean
						.getOutDoorStartTime()); // convert out door start
				// time in to date format
				Date dateOutDoorEndTime = sdf.parse(bean.getOutDoorEndTime()); // convert
				// out-door
				// end
				// time
				// in
				// to
				// date
				// format
				/**
				 * if employee in time is 00:00:00 or after out door visit start
				 * time
				 */
				if (empInTime.equals("00:00:00")
						|| dateEmpInTime.after(dateOutDoorStartTime)) {
					dateEmpInTime = dateOutDoorStartTime;
					empInTime = bean.getOutDoorStartTime();

					calculateTime[6] = empInTime; // in time
				}

				/**
				 * if employee out time is 00:00:00 or before out door visit
				 * start time
				 */
				if (empOutTime.equals("00:00:00")
						|| dateEmpOutTime.before(dateOutDoorEndTime)) {
					dateEmpOutTime = dateOutDoorEndTime;
					empOutTime = bean.getOutDoorEndTime();

					calculateTime[7] = empOutTime; // out time
				}

				Calendar cal = Calendar.getInstance();
				cal.setTime(dateEmpOutTime);
				cal.add(Calendar.HOUR, -dateEmpInTime.getHours());
				cal.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				cal.add(Calendar.SECOND, -dateEmpInTime.getSeconds());
				calculateTime[8] = sdf.format(cal.getTime()); // work hours
			}

			Calendar c1 = Calendar.getInstance();
			if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
					.before(dateFirstHalfTime) || dateEmpInTime
					.equals(dateFirstHalfTime)))
					&& ((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
							.equals(dateSecondHalfTime)) && dateEmpOutTime
							.before(earlyLeavingEndTime))) {
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
			} else if (dateEmpInTime.after(dateFirstHalfTime)
					&& !empInTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if (dateEmpOutTime.before(dateSecondHalfTime)
					&& !empOutTime.equals("00:00:00")) {
				calculateTime[2] = "Y";
				status = "HD";
			} else if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
					.before(dateFirstHalfTime) || dateEmpInTime
					.equals(dateFirstHalfTime)))) {
				c1.setTime(dateEmpInTime);
				c1.add(Calendar.HOUR, -lateMarkStartTime.getHours());
				c1.add(Calendar.MINUTE, -lateMarkStartTime.getMinutes());
				c1.add(Calendar.SECOND, -lateMarkStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours

				status = "LC";
			} else if (((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
					.equals(dateSecondHalfTime)) && dateEmpOutTime
					.before(earlyLeavingEndTime))) {
				c1.setTime(earlyLeavingEndTime);
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours

				status = "EL";
			} else if ((!empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					&& (dateEmpInTime.before(lateMarkStartTime) || dateEmpInTime
							.equals(lateMarkStartTime))
					&& (dateEmpOutTime.after(earlyLeavingEndTime) || dateEmpOutTime
							.equals(earlyLeavingEndTime))) {
				status = "IN";
			} else if ((empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					|| (!empInTime.equals("00:00:00") && empOutTime
							.equals("00:00:00"))) {
				status = "HD";
				//System.out.println("3rd H.D.");
			} else {
				status = "AB";
			}

			if (empOutTime.equals("00:00:00") || offcEndTime.equals("00:00:00")) {
				calculateTime[4] = "00:00:00"; // extra hours
			} else {
				// CALCULATION FOR EXTRA HOURS -- UPLOAD ATTENDANCE
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateEmpOutTime);
				cal.add(Calendar.HOUR, -dateEmpInTime.getHours());
				cal.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				cal.add(Calendar.SECOND, -dateEmpInTime.getSeconds());

				Date actualWorkHrs = cal.getTime();
				String convertedShiftWorkingHours = getHH_MM_SS(Integer
						.parseInt(shiftWorkingHoursFromQuery));
				Date shiftWorkingHrs = sdf.parse(convertedShiftWorkingHours);
				if (actualWorkHrs.after(shiftWorkingHrs)) {
					Calendar extraHours = Calendar.getInstance();
					extraHours.setTime(actualWorkHrs);
					extraHours.add(Calendar.HOUR, -shiftWorkingHrs.getHours());
					extraHours.add(Calendar.MINUTE, -shiftWorkingHrs
							.getMinutes());
					extraHours.add(Calendar.SECOND, -shiftWorkingHrs
							.getSeconds());
					calculateTime[4] = sdf.format(extraHours.getTime());
					/*
					 * System.out.println("############# UPLOAD ELSE :
					 * empOutTime : "+empOutTime);
					 * System.out.println("############# UPLOAD ELSE :
					 * offcEndTime : "+offcEndTime); c1.setTime(dateEmpOutTime);
					 * c1.add(Calendar.HOUR, -dateOffcEndTime.getHours());
					 * c1.add(Calendar.MINUTE, -dateOffcEndTime.getMinutes());
					 * c1.add(Calendar.SECOND, -dateOffcEndTime.getSeconds());
					 * calculateTime[4] = sdf.format(c1.getTime()); // extra
					 * hours System.out.println("############# CALULATED EXTRA
					 * HOURS >>"+String.valueOf(calculateTime[4]));
					 */
				}
			}
			if (flexiTimeFromQuery.equals("N")
					&& !markAbsentAfterThisTime.equals("00:00:00")) {
				if (!empInTime.equals("00.00.00")
						&& !markAbsentAfterThisTime.equals("00:00:00")) {
					// System.out.println("UPLOAD : MARK ABSENT");
					int employeeInTime = TimeUtility.getMinute(empInTime);
					int markAbsent = TimeUtility
							.getMinute(markAbsentAfterThisTime);
					if (employeeInTime > markAbsent) {
						status = "AB";
					}
				}
			}
			if (flexiTimeFromQuery.equals("Y")) {
				Calendar flex = Calendar.getInstance();
				flex.setTime(dateEmpOutTime);
				flex.add(Calendar.HOUR, -dateEmpInTime.getHours());
				flex.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				flex.add(Calendar.SECOND, -dateEmpInTime.getSeconds());
				int employeeWorkHours = TimeUtility.getMinute(String
						.valueOf(sdf.format(flex.getTime())));
				int shiftWorkingHours = Integer
						.parseInt(shiftWorkingHoursFromQuery);
				int flexiLateHours = TimeUtility
						.getMinute(markFlexiLateLessThan);
				int flexiHalfDayHours = TimeUtility
						.getMinute(markFlexiHalfDayLateLessThan);

				if (employeeWorkHours == 0) {
					status = "AB";
				} else if (employeeWorkHours >= flexiLateHours) {
					status = "IN";
				} else if (employeeWorkHours < flexiHalfDayHours) {
					status = "HD";
				} else if ((employeeWorkHours < flexiLateHours)
						& (flexiLateHours > flexiHalfDayHours)) {
					status = "LC";
				}
				if (!empInTime.equals("00:00:00")
						&& empOutTime.equals("00:00:00")) {
					status = "HD";
				}
			}
			/* Updated for If Shift is Night Shift */
			if (nightFlag.equals("Y")) {
				int inTime = Integer.parseInt((empInTime.substring(0, 5))
						.replaceAll(":", ""));
				int outTime = Integer.parseInt((empOutTime.substring(0, 5))
						.replaceAll(":", ""));
				int startTime = Integer
						.parseInt((offcStartTime.substring(0, 5)).replaceAll(
								":", ""));
				int endTime = Integer.parseInt((offcEndTime.substring(0, 5))
						.replaceAll(":", ""));
				int fstHalfTime = Integer.parseInt((firstHalfTime.substring(0,
						5)).replaceAll(":", ""));
				int scdHalfTime = Integer.parseInt((secondHalfTime.substring(0,
						5)).replaceAll(":", ""));

				int nightlateMarkTime = Integer.parseInt((lateMarkTime
						.substring(0, 5)).replaceAll(":", ""));
				int nightearlyLeavingEndTime = Integer
						.parseInt((earlyLeavingTime.substring(0, 5))
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
					status = "DL";
				} else if (inTime > fstHalfTime) {
					status = "HD";
				} else if (outTime < scdHalfTime) {
					status = "HD";
				} else if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
					status = "LC";
				} else if (((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
					status = "EL";
				} else if (inTime > 0
						&& outTime > 0
						&& (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
						&& (outTime == nightearlyLeavingEndTime)
						|| outTime > nightearlyLeavingEndTime) {
					status = "IN";
				} else {
					status = "AB";
				}
			}
			calculateTime[5] = status;
		} catch (Exception e) {
			logger
					.error("UploadAttendance bean,tring empInTimecalculateLateAndEarlyHrs : "
							+ e);
			e.printStackTrace();
		}
		return calculateTime;
	}

	/**
	 * @Method Name: getHH_MM_SS()
	 * @purpose : Convert into Time Format
	 * @param minuts
	 * @return String
	 */
	private String getHH_MM_SS(int minuts) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			hh = minuts / 60;
			mm = (minuts % 60);

			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (hrs + ":" + minute + ":00");
	}

	/**
	 * @method calculateNumberOfDays
	 * @purpose to calculate no of days between from date and to date
	 * @param fromDate
	 * @param toDate
	 * @return int
	 */
	public int calculateNumberOfDays(String fromDate, String toDate) {
		int daysBetween = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			Date fromDateParse = sdf.parse(fromDate);
			Date toDateParse = sdf.parse(toDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);

			Calendar calTo = Calendar.getInstance();
			calTo.setTime(toDateParse);

			while (cal.before(calTo) || cal.equals(calTo)) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				daysBetween++;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return daysBetween;
	}

	/**
	 * @param workHr
	 * @return String
	 */
	public String calWorkHr(String workHr) {

		int hours = Integer.parseInt(workHr.substring(0, 2));
		int min = Integer.parseInt(workHr.substring(2, 4));
		if (min > 59) {
			int addHr = 0;
			addHr = min / 60;
			hours += addHr;
			min = min % 60;
		}
		String totalHr = hours + ":" + min;
		return totalHr;
	}

	/**
	 * @method checkDateFormat
	 * @purpose to check whether date is in proper format or not
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	public String checkDateFormat(String date, String dateFormat) {
		try {
			Date dtTmp = new SimpleDateFormat(dateFormat).parse(date.trim());

			String strOutDt = new SimpleDateFormat("dd-MM-yyyy").format(dtTmp);

			return strOutDt;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}

	}

	/**
	 * @method checkNull
	 * @purpose to check whether the selected data is null or not
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @method checkTimeFormat
	 * @purpose to check whether time is in proper format or not
	 * @param bean
	 * @param getHdrData
	 * @param time
	 * @return String
	 */
	public String checkTimeFormat(UploadAttendance bean, Object[][] getHdrData,
			String time) {
		String timePattern = "";
		String validTime = "";

		String timeFormat = String.valueOf(getHdrData[0][4]);
		if (timeFormat.equals("HH:MM:SS")) {
			timePattern = "[0-9]{1,2}[:]?[0-9]{1,2}[:]?[0-9]{1,2}";
			/*
			 * if(time.equals("")) { time = "00:00:00"; }
			 */
		} else if (timeFormat.equals("HH:MM")) {
			timePattern = "[0-9]{1,2}[:]?[0-9]{1,2}";
			/*
			 * if(time.equals("")) { time = "00:00"; }
			 */
		} else if (timeFormat.equals("HHMM")) {
			timePattern = "[0-9]{2,3,4}";
			/*
			 * if(time.equals("")) { time = "0000"; }
			 */
		} else if (timeFormat.equals("HH-MM-SS")) {
			timePattern = "[0-9]{1,2}[-]?[0-9]{1,2}[-]?[0-9]{1,2}";
			/*
			 * if(time.equals("")) { time = "00-00-00"; }
			 */
		} else if (timeFormat.equals("HH-MM")) {
			timePattern = "[0-9]{1,2}[-]?[0-9]{1,2}}";
			/*
			 * if(time.equals("")) { time = "00-00"; }
			 */
		} else if (timeFormat.equals("HH.MM.SS")) {
			timePattern = "[0-9]{1,2}[.]?[0-9]{1,2}[.]?[0-9]{1,2}";
			/*
			 * if(time.equals("")) { time = "00.00.00"; }
			 */
		} else if (timeFormat.equals("HH.MM")) {
			timePattern = "[0-9]{1,2}[.]?[0-9]{1,2}";
			/*
			 * if(time.equals("")) { time = "00.00"; }
			 */
		}

		Pattern pattern = Pattern.compile(timePattern);
		Matcher matcher = pattern.matcher(time.trim());

		if (matcher.matches()) {
			validTime = time;
		} else {
			validTime = "";
		}
		return validTime;
	}

	/**
	 * @method compareDates
	 * @purpose to check the whether the date retrieved from file falls between
	 *          from date and to date
	 * @param bean
	 * @param fileDate
	 * @return boolean
	 */
	public boolean compareDates(UploadAttendance bean, String fileDate) {
		String myFormatString = "dd-MM-yyyy"; // for example
		SimpleDateFormat df = new SimpleDateFormat(myFormatString);

		String fromDate = bean.getFromDate();
		String toDate = bean.getToDate();

		try {
			Date startDate = df.parse(fromDate);
			Date endDate = df.parse(toDate);
			Date betweenDate = df.parse(fileDate);

			if (betweenDate.before(startDate) || betweenDate.after(endDate)) {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}

	/**
	 * @Method Name : copyRetrievedAttendance()
	 * @purpose : Copy data into another object
	 * @param retrievedAttendance
	 * @param fldEmpToken
	 * @param fldAttendanceDate
	 * @param fldInTime
	 * @param fldOutTime
	 * @param fldOneTime
	 * @param fldIOFlag
	 * @param fldWorkHrs
	 * @param fldShift
	 * @return Object[][]
	 */
	private Object[][] copyRetrievedAttendance(Object[][] retrievedAttendance,
			String fldEmpToken, String fldAttendanceDate, String fldInTime,
			String fldOutTime, String fldOneTime, String fldIOFlag,
			String fldWorkHrs, String fldShift) {
		Object[][] attendanceData = null;
		try {
			if (retrievedAttendance != null && retrievedAttendance.length > 0) {
				attendanceData = new Object[retrievedAttendance.length][8];

				for (int i = 0; i < retrievedAttendance.length; i++) {
					/**
					 * Copy Employee Token
					 */
					boolean isEmpTokenExists = false;

					if (!fldEmpToken.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldEmpToken.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][0] = retrievedAttendance[i][j];
								isEmpTokenExists = true;
								break;
							}
						}
					}

					if (!isEmpTokenExists) {
						attendanceData[i][0] = "";
					}

					/**
					 * Copy Attendance Date
					 */
					boolean isAttnDateExists = false;

					if (!fldAttendanceDate.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldAttendanceDate.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][1] = retrievedAttendance[i][j];
								isAttnDateExists = true;
								break;
							}
						}
					}

					if (!isAttnDateExists) {
						attendanceData[i][1] = "";
					}

					/**
					 * Copy In Time
					 */
					boolean isInTimeExists = false;

					if (!fldInTime.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldInTime.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][2] = retrievedAttendance[i][j];
								isInTimeExists = true;
								break;
							}
						}
					}

					if (!isInTimeExists) {
						attendanceData[i][2] = "";
					}

					/**
					 * Copy Out Time
					 */
					boolean isOutTimeExists = false;

					if (!fldOutTime.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldOutTime.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][3] = retrievedAttendance[i][j];
								isOutTimeExists = true;
								break;
							}
						}
					}

					if (!isOutTimeExists) {
						attendanceData[i][3] = "";
					}

					/**
					 * Copy One Time
					 */
					boolean isOneTimeExists = false;

					if (!fldOneTime.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldOneTime.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][4] = retrievedAttendance[i][j];
								isOneTimeExists = true;
								break;
							}
						}
					}

					if (!isOneTimeExists) {
						attendanceData[i][4] = "";
					}

					/**
					 * Copy IO Flag
					 */
					boolean isIOFlagExists = false;

					if (!fldIOFlag.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldIOFlag.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][5] = retrievedAttendance[i][j];
								isIOFlagExists = true;
								break;
							}
						}
					}

					if (!isIOFlagExists) {
						attendanceData[i][5] = "";
					}

					/**
					 * Copy Work Hrs
					 */
					boolean isWorkHrsExists = false;

					if (!fldWorkHrs.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldWorkHrs.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][6] = retrievedAttendance[i][j];
								isWorkHrsExists = true;
								break;
							}
						}
					}

					if (!isWorkHrsExists) {
						attendanceData[i][6] = "";
					}

					/**
					 * Copy Shift
					 */
					boolean isShiftExists = false;

					if (!fldShift.equals("")) {
						for (int j = 0; j < columnNames.size(); j++) {
							if (fldShift.trim().equalsIgnoreCase(
									columnNames.get(j).trim())) {
								attendanceData[i][7] = retrievedAttendance[i][j];
								isShiftExists = true;
								break;
							}
						}
					}

					if (!isShiftExists) {
						attendanceData[i][7] = "";
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in copyRetrievedAttendance:" + e);
		}
		return attendanceData;
	}

	/**
	 * @Method Name : getAttendanceData()
	 * @param day
	 * @param empShiftData
	 * @param fromDate
	 * @param fldShift
	 * @param fldOneTime
	 * @param fldWorkHrs
	 * @param uploadFrom
	 * @param division
	 * @param branch
	 * @return
	 */
	private Object[][] getAttendanceData(int day, Object[][] empShiftData,
			String fromDate, String fldShift, String fldOneTime,
			String fldWorkHrs, String uploadFrom, String division, String branch) {

		return getAttendanceData(day, empShiftData, fromDate, fldShift,
				fldOneTime, fldWorkHrs, uploadFrom, division, branch, null);
	}

	/**
	 * @param day :
	 *            Specifies difference between from and to date
	 * @param empShiftData :
	 *            Specifies shift detail
	 * @param fromDate :
	 *            Specifies from date
	 * @param fldShift :
	 *            Specifies to date
	 * @param fldOneTime :
	 *            Specifies oneTime flag whether it is available or not
	 * @param fldWorkHrs :
	 *            Specifies workHrs flag whether it is available or not
	 * @param uploadFrom :
	 *            Specifies source of data from where record has been uploaded
	 * @param division :
	 *            Specifies division code
	 * @param branch :
	 *            Specifies branch code
	 * @param conn :
	 *            Specifies connection object
	 * @return Object
	 */
	private Object[][] getAttendanceData(int day, Object[][] empShiftData,
			String fromDate, String fldShift, String fldOneTime,
			String fldWorkHrs, String uploadFrom, String division,
			String branch, Connection conn) {
		String date = incrementDate(fromDate, day);
		boolean flag = false;
		// select attendance data from HRMS_ATTENDANCE_TEMP along with shift
		// details
		String query = " SELECT DISTINCT TEMP_ATT_EMP_CODE AS EMP_TOKEN, EMP_ID, TO_CHAR(TEMP_ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, ";
		if (!fldShift.equals("")) {
			query += " HRMS_SHIFT.SHIFT_ID, NVL(TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF ";
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
		query += " , EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, NVL(HRMS_SHIFT.SFT_NIGHT_FLAG, 'N') AS SFT_NIGHT_FLAG, ";

		if (!fldShift.equals("")) {
			query += " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING_DTL, "
					+ " HRMS_SHIFT.SFT_FLEXITIME_ALLOWED, NVL(TO_NUMBER(SUBSTR(TO_CHAR(HRMS_SHIFT.SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 + "
					+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(HRMS_SHIFT.SFT_WORK_HRS,'HH24:MI'),4,5)),0), "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_MARK_ABSENT_AFTER, 'HH24:MI:SS'), '00:00:00') AS MARK_ABSENT, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_LATE_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_LATE_MARK,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_HALFDAY_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_HALFDAY_MARK ,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_ABSENT_MARK";
		} else {
			query += " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_START_TIME, 'HH24:MI:SS'), '00:00:00') AS START_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_END_TIME, 'HH24:MI:SS'), '00:00:00') AS END_TIME_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS FIRST_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS SECOND_HALF_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK_DTL, "
					+ " NVL(TO_CHAR(HRMS_SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING_DTL, "
					+ " HRMS_SHIFT.SFT_FLEXITIME_ALLOWED, NVL(TO_NUMBER(SUBSTR(TO_CHAR(HRMS_SHIFT.SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 + "
					+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(HRMS_SHIFT.SFT_WORK_HRS,'HH24:MI'),4,5)),0), "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_MARK_ABSENT_AFTER, 'HH24:MI:SS'), '00:00:00') AS MARK_ABSENT, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_LATE_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_LATE_MARK,"
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_HALFDAY_MARK, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_HALFDAY_MARK, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI:SS'), '00:00:00') AS SFT_FLEXI_ABSENT_MARK";
		}
		query += " FROM HRMS_ATTENDANCE_TEMP "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_TOKEN = HRMS_ATTENDANCE_TEMP.TEMP_ATT_EMP_CODE) "
				+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) "
				// + " LEFT JOIN HRMS_SHIFT SHIFT ON(SHIFT.SHIFT_NAME =
				// HRMS_ATTENDANCE_TEMP.TEMP_ATT_SHIFT ) "
				+ " LEFT JOIN HRMS_SHIFT_DTL ON (HRMS_SHIFT_DTL.SHIFT_ID = HRMS_SHIFT.SHIFT_ID "
				+ " AND HRMS_SHIFT_DTL.SFT_DTL_EXCEPTIONAL_DAY = TO_NUMBER(DECODE(TRIM(TO_CHAR(TEMP_ATT_DATE, 'DAY')), 'SUNDAY', 1, 'MONDAY', 2, "
				+ " 'TUESDAY', 3, 'WEDNESDAY', 4, 'THURSDAY', 5, 'FRIDAY', 6, 'SATURDAY', 7))) "
				/*
				 * + " LEFT JOIN HRMS_SHIFT_DTL SHIFT_DTL ON (SHIFT_DTL.SHIFT_ID =
				 * SHIFT.SHIFT_ID " + " AND SHIFT_DTL.SFT_DTL_EXCEPTIONAL_DAY =
				 * TO_NUMBER(DECODE(TRIM(TO_CHAR(TEMP_ATT_DATE, 'DAY')),
				 * 'SUNDAY', 1, 'MONDAY', 2, 'TUESDAY', 3, " + " 'WEDNESDAY', 4,
				 * 'THURSDAY', 5, 'FRIDAY', 6, 'SATURDAY', 7))) "
				 */
				+ " WHERE TEMP_ATT_DATE = TO_DATE('" + date
				+ "', 'DD-MM-YYYY') ";

		/* check whether uploaded data is from Attendance Sheet or not */
		if (uploadFrom.equals("uploadSheet")) {
			query += " AND TEMP_ATT_DIVISION = " + division
					+ " AND TEMP_ATT_BRANCH = " + branch;
		}
		/*
		 * query+= " GROUP BY TEMP_ATT_EMP_CODE, SFT_NIGHT_FLAG, EMP_ID,
		 * TEMP_ATT_DATE, EMP_SHIFT, HRMS_SHIFT.SFT_START_TIME,
		 * TEMP_ATT_IN_TIME, " + " TEMP_ATT_OUT_TIME, HRMS_SHIFT.SFT_END_TIME,
		 * HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME,
		 * TEMP_ATT_WORK_HRS, " + " SHIFT.SHIFT_ID, SHIFT.SFT_START_TIME,
		 * SHIFT.SFT_END_TIME, SHIFT.SFT_IN_HD_AFTER_TIME,
		 * SHIFT.SFT_OUT_HD_BEFORE_TIME, TEMP_ATT_SHIFT, " + " EMP_FNAME || ' ' ||
		 * EMP_MNAME || ' ' || EMP_LNAME "; if (!fldShift.equals("")) { query += "
		 * ,SHIFT.SFT_IN_LM_AFTER_TIME, SHIFT.SFT_OUT_EL_BEFORE_TIME,
		 * TEMP_ATT_SHIFT, SHIFT.SFT_FLEXITIME_ALLOWED, SHIFT.SFT_WORK_HRS, " +"
		 * SHIFT.SFT_MARK_ABSENT_AFTER, SHIFT.SFT_FLEXI_LATE_MARK,
		 * SHIFT.SFT_FLEXI_HALFDAY_MARK "; } else { query += "
		 * ,HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME ,
		 * SHIFT_NAME, HRMS_SHIFT.SFT_FLEXITIME_ALLOWED,
		 * HRMS_SHIFT.SFT_WORK_HRS, " +" HRMS_SHIFT.SFT_MARK_ABSENT_AFTER,
		 * HRMS_SHIFT.SFT_FLEXI_LATE_MARK, HRMS_SHIFT.SFT_FLEXI_HALFDAY_MARK "; }
		 * query += " , HRMS_SHIFT_DTL.SFT_DTL_START_TIME,
		 * HRMS_SHIFT_DTL.SFT_DTL_END_TIME,
		 * HRMS_SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME, " + "
		 * HRMS_SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME,
		 * HRMS_SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME,
		 * HRMS_SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME, " + "
		 * SHIFT_DTL.SFT_DTL_START_TIME, SHIFT_DTL.SFT_DTL_END_TIME,
		 * SHIFT_DTL.SFT_DTL_IN_HD_AFTER_TIME,
		 * SHIFT_DTL.SFT_DTL_OUT_HD_BEFORE_TIME, " + "
		 * SHIFT_DTL.SFT_DTL_IN_LM_AFTER_TIME,
		 * SHIFT_DTL.SFT_DTL_OUT_EL_BEFORE_TIME "
		 */;

		query += " ORDER BY TEMP_ATT_EMP_CODE";
		Object[][] getAttendanceData = null;// getSqlModel().getSingleResult(query);

		/*
		 * check whether function is called by scheduler or not if true then
		 * pass connection object
		 */
		if (uploadFrom.equals("autoSchedule")) {
			getAttendanceData = SqlModel.getSingleResult(query, conn);
		} else {
			getAttendanceData = getSqlModel().getSingleResult(query);
		}

		Object[][] newData = null;

		if (getAttendanceData != null && getAttendanceData.length != 0) {
			// match employee code retrieved from HRMS_ATT_TEMP table with
			// employee id from HRMS_EMP_OFFC
			if (empShiftData != null && empShiftData.length != 0) {
				newData = new Object[empShiftData.length][22];

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
							// adte
							newData[j][3] = getAttendanceData[k][3]; // shift
							// id
							if (isShiftExceptionExists) {
								newData[j][4] = shiftDtlStartTime; // shift
								// Exception
								// Start
								// Time

								newData[j][5] = shiftDtlEndTime; // shift
								// exception
								// End Time
								newData[j][6] = getAttendanceData[k][18]; // shift
								// Exception
								// 1st
								// HD
								newData[j][7] = getAttendanceData[k][19]; // shift
								// Exception
								// 2nd
								// HD
							} else {
								newData[j][4] = getAttendanceData[k][4]; // shift
								// start
								// time
								newData[j][5] = getAttendanceData[k][5]; // shift
								// end
								// Time
								newData[j][6] = getAttendanceData[k][6]; // shift
								// 1st
								// HD
								newData[j][7] = getAttendanceData[k][7]; // shift
								// 2nd
								// HD
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
							if (String.valueOf(getAttendanceData[k][9]).equals(
									"00:00:00")) {
								newData[j][10] = "00:00:00"; // work hours :
								// if Emp Out
								// time is 0
							}
							if (!fldShift.equals("")) {
								newData[j][11] = getAttendanceData[k][11]; // shift
								// name
								newData[j][12] = getAttendanceData[k][12]; // employee
								// name
							}
							newData[j][13] = getAttendanceData[k][13]; // Night
							// flag
							// flag
							if (isShiftExceptionExists) {
								newData[j][14] = getAttendanceData[k][20]; // shift
								// exception
								// LC
								newData[j][15] = getAttendanceData[k][21]; // shift
								// exception
								// EL
							} else {
								newData[j][14] = getAttendanceData[k][14]; // shift
								// late
								// Coming
								newData[j][15] = getAttendanceData[k][15]; // shift
								// early
								// leaving
							}
							newData[j][16] = getAttendanceData[k][22]; // shift
							// flexi
							// time
							newData[j][17] = getAttendanceData[k][23]; // shift
							// working
							// hours
							// working-hours
							newData[j][18] = getAttendanceData[k][24]; // Absent
							// after
							// this
							// time
							newData[j][19] = getAttendanceData[k][25]; // Flexi
							// mark
							// late
							newData[j][20] = getAttendanceData[k][26]; // Flexi
							// mark
							// HD
							newData[j][21]= getAttendanceData[k][27];//Flexi mark Absent
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
						newData[j][6] = empShiftData[j][5]; // shift first HD
						newData[j][7] = empShiftData[j][6]; // shift second HD
						newData[j][8] = "00:00:00"; // in time
						newData[j][9] = "00:00:00"; // out time
						newData[j][10] = "00:00:00"; // work hours
						newData[j][11] = empShiftData[j][7]; // shift name
						newData[j][12] = empShiftData[j][8]; // employee name
						newData[j][13] = empShiftData[j][11]; // Night flag
						newData[j][14] = empShiftData[j][9]; // late start
						// time
						newData[j][15] = empShiftData[j][10]; // EL Late Time
						newData[j][16] = "N"; // Is Flexi
						newData[j][17] = "00:00:00"; // Working hours
						newData[j][18] = "00:00:00"; // Absent after this
						// time
					}
				}
			}
		}
		return newData;
	}

	/**
	 * @Method Name : getCalendarDate()
	 * @param fromDate
	 * @param daysBetween
	 * @return Object []
	 */
	public Object[] getCalendarDate(String fromDate, int daysBetween) {
		String[] fromDateStr = fromDate.split("-");
		int year = Integer.parseInt(fromDateStr[2]) - 1900;
		int month = Integer.parseInt(fromDateStr[1]) - 1;
		int day = Integer.parseInt(fromDateStr[0]) - 1;

		Calendar cal = Calendar.getInstance();
		Date d = new Date(year, month, day);
		cal.setTime(d);

		Object[] newDates = new Object[daysBetween];
		for (int i = 0; i < daysBetween; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);

			String newDay = Integer.parseInt(String.valueOf(cal
					.get(Calendar.DATE))) < 10 ? "0"
					+ String.valueOf(cal.get(Calendar.DATE)) : String
					.valueOf(cal.get(Calendar.DATE));
			String newMonth = Integer.parseInt(String.valueOf(cal
					.get(Calendar.MONTH))) < 10 ? "0"
					+ String.valueOf(cal.get(Calendar.MONTH)) : String
					.valueOf(cal.get(Calendar.MONTH));

			int intAttMonth = Integer.parseInt(newMonth) + 1;
			String strAttMonth = intAttMonth < 10 ? "0"
					+ String.valueOf(intAttMonth) : String.valueOf(intAttMonth);
			newDates[i] = newDay + "-" + strAttMonth + "-"
					+ cal.get(Calendar.YEAR);
		}
		return newDates;
	}

	/**
	 * @method getConfigurationData
	 * @purpose to retrieve the configuration data from the
	 *          HRMS_ATTENDANCESETTINGS_DTL table
	 * @param bean
	 * @param hdrData
	 * @return Object array
	 */
	public Object[][] getConfigurationData(UploadAttendance bean,
			Object[][] hdrData) {
		Object[] hdrCode = new Object[1];
		hdrCode[0] = hdrData[0][0]; // hdr code

		Object[][] getDtlData = getSqlModel().getSingleResult(getQuery(2),
				hdrCode);
		return getDtlData;
	}

	/**@method Name: getListOfServers()
	 * @purpose: Retrieve Server details
	 * @return object[][]
	 */
	public Object[][] getListOfServers() {
		Object[][] listOfDrivers = null;
		try {
			listOfDrivers = getSqlModel().getSingleResult(getQuery(15));
		} catch (Exception e) {
			logger.error("Exception in getListOfDrivers:" + e);
		}
		return listOfDrivers;
	}

	/**
	 * @method getNumberOfLines
	 * @purpose to get the total number of lines in a txt and csv file
	 * @param filePath
	 * @return int
	 */
	public int getNumberOfLines(String filePath) {
		int i = 0;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();

			while (line != null) {
				line = br.readLine();
				i++;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return i;
	}

	/**
	 * @Method Name: getShiftWorkHrs()
	 * @purpose : Used to Calculate Working Hours
	 * @param inTimeStr
	 * @param outTimeStr
	 * @return String
	 */
	public String getShiftWorkHrs(String inTimeStr, String outTimeStr) {
		int inTimehr = Integer.parseInt(inTimeStr.replaceAll(":", "")
				.substring(0, 2));
		int outTimehr = Integer.parseInt(outTimeStr.replaceAll(":", "")
				.substring(0, 2));

		if (outTimehr < inTimehr) {
			outTimehr += 24;
		}

		int inTimemin = Integer.parseInt(inTimeStr.replaceAll(":", "")
				.substring(2, 4));
		int outTimemin = Integer.parseInt(outTimeStr.replaceAll(":", "")
				.substring(2, 4));

		if (outTimemin == 0 && inTimemin > 0) {
			outTimemin = 60;
			inTimehr = inTimehr + 1;
		}
		if (inTimemin > outTimemin) {
			outTimemin = outTimemin + 60;
			inTimehr = inTimehr + 1;
		}

		String hrStr = "";
		String minStr = "";
		int hour = outTimehr - inTimehr;
		int min = outTimemin - inTimemin;

		if (min <= 9) {
			minStr = "0" + min;
		} else {
			minStr = "" + min;
		}

		if (hour <= 9) {
			hrStr = "0" + hour;
		} else {
			hrStr = "" + hour;
		}

		String workHr = hrStr + "" + minStr;

		String[] splitHrMin = calWorkHr(workHr).split(":");
		String actHour = splitHrMin[0];
		String actMin = splitHrMin[1];

		if (Integer.parseInt(splitHrMin[1]) <= 9) {
			actMin = "0" + splitHrMin[1];
		}

		String actWorkhr = actHour + ":" + actMin;
		return actWorkhr;
	}

	/**
	 * @Method Name :incrementDate()
	 * @purpose to increase the from date by one
	 * @param fromDate
	 * @param i
	 * @return String
	 */
	public String incrementDate(String fromDate, int i) {
		String date = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDateParse = sdf.parse(fromDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);
			cal.add(Calendar.DAY_OF_MONTH, i);

			date = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
		} catch (Exception e) {
			logger.error(e);
		}
		return date;
	}

	/**
	 * @Method Name: insertData()
	 * @purpose : Insert data into Temporary table in case of Auto Upload
	 * @param attendanceData
	 * @param fromDate
	 * @param toDate
	 * @return String
	 */
	public String insertData(Object[][] attendanceData, String fromDate,
			String toDate) {
		Object[][] attendanceDetail = new Object[1][3];
		String result = "";
		String inTime = "", outTime = "", attDate = "", empToken = "";
		int count = 0;
		boolean queryResult = false;

		int daysBetween = calculateNumberOfDays(fromDate, toDate);

		for (int i = 0; i < attendanceData.length; i++) {
			if (attendanceData[i][0] != null
					&& !attendanceData[i][0].equals("")) {
				count++;
			}
		}

		Object[][] insertData = new Object[count][8];
		int j = 0;
		String concatDelEmpIds = "";
		for (int i = 0; i < attendanceData.length; i++) {
			if (attendanceData[i][1] != null
					&& !attendanceData[i][1].equals("")) {
				empToken = String.valueOf(attendanceData[i][0]);
				attDate = String.valueOf(attendanceData[i][1]);
				inTime = String.valueOf(attendanceData[i][2]);
				outTime = String.valueOf(attendanceData[i][3]);
				/** It returns In And Out Time With Attendance Date */
				attendanceDetail = dateWithTime(empToken, attDate, inTime,
						outTime);

				concatDelEmpIds += "'"
						+ checkNull(String.valueOf(attendanceData[i][0]).trim())
						+ "',";

				insertData[j][0] = checkNull(String.valueOf(
						attendanceData[i][0]).trim()); // employee token
				insertData[j][1] = checkNull(String.valueOf(
						attendanceData[i][1]).trim()); // attendance date
				insertData[j][2] = checkNull(String.valueOf(
						attendanceDetail[0][1]).trim()); // in time
				insertData[j][3] = checkNull(String.valueOf(
						attendanceDetail[0][2]).trim()); // out time
				insertData[j][4] = checkNull(String.valueOf(
						attendanceData[i][4]).trim()); // one time
				insertData[j][5] = checkNull(String.valueOf(
						attendanceData[i][5]).trim()); // io flag
				insertData[j][6] = checkNull(String.valueOf(
						attendanceData[i][6]).trim()); // work hours
				insertData[j][7] = checkNull(String.valueOf(
						attendanceData[i][7]).trim()); // shift
				j++;
			}
		}
		concatDelEmpIds = concatDelEmpIds.substring(0,
				concatDelEmpIds.length() - 1);

		String deleteQuery = " DELETE FROM HRMS_ATTENDANCE_TEMP "
				+ " WHERE TEMP_ATT_DATE BETWEEN TO_DATE('"
				+ fromDate
				+ "', 'DD-MM-YYYY') "
				+ " AND TO_DATE('"
				+ toDate
				+ "', 'DD-MM-YYYY') "
				+ Utility.getConcatenatedIds("TEMP_ATT_EMP_CODE",
						concatDelEmpIds);
		queryResult = getSqlModel().singleExecute(deleteQuery);

		ArrayList<String> empIds = new ArrayList<String>();
		ArrayList<String> shifts = new ArrayList<String>();

		for (int i = 0; i < insertData.length; i++) {
			if (i > 0) {
				if (!String.valueOf(insertData[i - 1][0]).equals(
						String.valueOf(insertData[i][0]))) {
					empIds.add(String.valueOf(insertData[i][0]));
					shifts.add(String.valueOf(insertData[i][7]));
				}
			} else {
				empIds.add(String.valueOf(insertData[i][0]));
				shifts.add(String.valueOf(insertData[i][7]));
			}
		}

		// SHIFT OR THOSE RECORD WHOSE DATA IS NOT AVIALBALE IN SHEET
		String shiftSql = " SELECT EMP_TOKEN, NVL(SHIFT_NAME, '') AS SHIFT_NAME FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_SHIFT ON (HRMS_EMP_OFFC.EMP_SHIFT = SHIFT_ID) "
				+ " WHERE 1 = 1 "
				+ Utility.getConcatenatedIds("EMP_TOKEN", concatDelEmpIds);
		Object[][] shiftData = getSqlModel().getSingleResult(shiftSql);

		HashMap<String, String> shiftMap = new HashMap<String, String>();
		if (shiftData != null && shiftData.length > 0) {
			for (int i = 0; i < shiftData.length; i++) {
				shiftMap.put(String.valueOf(shiftData[i][0]), String
						.valueOf(shiftData[i][1]));
			}
		}
		Object[] newDates = getCalendarDate(fromDate, daysBetween);
		Object[][] newAttendanceData = null;
		try {
			newAttendanceData = new Object[newDates.length * empIds.size()][8];
			int cnt = 0;

			for (int i = 0; i < empIds.size(); i++) {
				for (int k = 0; k < newDates.length; k++) {
					for (int l = 0; l < insertData.length; l++) {
						String empId = String.valueOf(insertData[l][0]);
						String date = String.valueOf(insertData[l][1]);

						if (empId.equals(empIds.get(i))
								&& date.equals(String.valueOf(newDates[k]))) {
							newAttendanceData[cnt][0] = empId; // employee code
							newAttendanceData[cnt][1] = date; // attendance
							// date
							newAttendanceData[cnt][2] = insertData[l][2]; // in
							// time
							newAttendanceData[cnt][3] = insertData[l][3]; // out
							// time
							newAttendanceData[cnt][4] = insertData[l][4]; // work
							// hours
							newAttendanceData[cnt][5] = insertData[l][5]; // one
							// time
							newAttendanceData[cnt][6] = insertData[l][6]; // io
							// flag
							newAttendanceData[cnt][7] = insertData[l][7]; // shift
							break;
						} else {
							newAttendanceData[cnt][0] = empIds.get(i); // employee
							// code
							newAttendanceData[cnt][1] = newDates[k]; // attendance
							// date
							newAttendanceData[cnt][2] = null; // in time
							newAttendanceData[cnt][3] = null; // out time
							newAttendanceData[cnt][4] = null; // work hours
							newAttendanceData[cnt][5] = null; // one time
							newAttendanceData[cnt][6] = null; // io flag
							newAttendanceData[cnt][7] = shiftMap.get(String
									.valueOf(newAttendanceData[cnt][0])); // shift
						}
					}
					cnt++;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in insertData:" + e);
		}

		if (newAttendanceData != null && newAttendanceData.length > 0) {
			try {
				String insertAttendnaceTemp = " INSERT INTO HRMS_ATTENDANCE_TEMP (TEMP_ATT_EMP_CODE, TEMP_ATT_DATE, TEMP_ATT_IN_TIME, "
						+ " TEMP_ATT_OUT_TIME, TEMP_ATT_ONE_TIME, TEMP_ATT_IO_FLAG, TEMP_ATT_WORK_HRS, TEMP_ATT_SHIFT) "
						+ " VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, "
						+ " TO_DATE(?, 'HH24:MI:SS'), ?) ";
				queryResult = getSqlModel().singleExecute(insertAttendnaceTemp,
						newAttendanceData);
			} catch (Exception e) {
				logger.error("Exception in insertData:" + e);
			}
		}

		if (queryResult) {
			result = "uploaded";
		}
		return result;
	}

	/**
	 * @Method Name: dateWithTime()
	 * @purpose : Shows In Time and Out Time With particular Attendance Date
	 * @param empTokenStr
	 * @param attDate
	 * @param attInTime
	 * @param attOutTime
	 * @return Object
	 */
	public Object[][] dateWithTime(String empTokenStr, String attDate,
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
			if (attDate != null) {//Check for is attendance date null
				String empToken = empTokenStr;
				/**This Object returns Shift Information from HRMS_SHIFT and HRMS_EMP_OFFC*/
				Object[][] shiftDetails = getShiftDetails(empToken);
				if (shiftDetails != null && shiftDetails.length > 0) {//Check for Shift Object
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
					}
					int startTime = Integer.parseInt(uploadIN);
					int endTime = Integer.parseInt(uploadOut);
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
					}//end else if  
					else if (shiftDetails[0][4].equals("Y")
							&& endTime > startTime
							&& endTime > finalShiftEndTime) {
						inDateTime = attDate;
						inDateTime = inDateTime + " " + attInTime;
						attInTime = inDateTime;
						outDateTime = attDate;
						outDateTime = outDateTime + " " + attOutTime;
						attOutTime = outDateTime;
					}//end else if  
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
				}//end if
				else {
					inDateTime = attDate;
					inDateTime = inDateTime + " " + attInTime;
					attInTime = inDateTime;
					outDateTime = attDate;
					outDateTime = outDateTime + " " + attOutTime;
					attOutTime = outDateTime;
				}
			} //end if

			attDetails[0][0] = attDate;
			attDetails[0][1] = attInTime;
			attDetails[0][2] = attOutTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attDetails;
	}

	/**
	 * @Method Name : getShiftDetails()
	 * @purpose : Returns Shift detail of Particular Employee
	 * @param empToken
	 * @return Object[][]
	 */
	public Object[][] getShiftDetails(String empToken) {

		String shiftQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_SHIFT, TO_CHAR(HRMS_SHIFT.SFT_START_TIME,'HH24:MI:SS'),"
				+ " TO_CHAR(HRMS_SHIFT.SFT_END_TIME,'HH24:MI:SS'), NVL(HRMS_SHIFT.SFT_NIGHT_FLAG,'N'),"
				+ " NVL(HRMS_SHIFT.SFT_FLEXITIME_ALLOWED,'N')"
				+ " FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_SHIFT ON(HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID)"
				+ " WHERE EMP_TOKEN='" + empToken + "'";
		Object[][] shiftDetail = getSqlModel().getSingleResult(shiftQuery);
		return shiftDetail;
	}

	/**
	 * @Method Name :insertData ()
	 * @purpose to insert the file data in HRMS_ATTENDANCE_TEMP table In case of
	 *          Upload manually
	 * @param bean
	 * @param attendanceData
	 * @return String
	 */
	public String insertData(UploadAttendance bean, Object[][] attendanceData,
			String fromDate, String toDate, String fileExtension,
			String DateFormat, String timeFormat) {

		Object[][] attendanceDetail = new Object[1][3];

		String result = "";
		boolean queryResult = false;
		String branchCode = bean.getBranchCode();
		String divCode = bean.getDivCode();
		String empIdString = "";

		/*
		 * delete data from HRMS_ATTENDANCE_TEMP table for the specified
		 * 
		 */
		Object[][] deleteObj = new Object[attendanceData.length][2];
		for (int i = 0; i < attendanceData.length; i++) {
			deleteObj[i][0] = attendanceData[i][0]; // emp Code
			deleteObj[i][1] = attendanceData[i][2]; // attendance date
			empIdString = empIdString + "'"
					+ String.valueOf(attendanceData[i][0]) + "',";
		}
		empIdString = empIdString.substring(0, (empIdString.length() - 1));

		String deleteQuery = " DELETE FROM HRMS_ATTENDANCE_TEMP "
				+ " WHERE TEMP_ATT_EMP_CODE = ? "
				+ " AND TEMP_ATT_DATE = TO_DATE(?, '" + DateFormat + "') ";
		queryResult = getSqlModel().singleExecute(deleteQuery, deleteObj);
		deleteObj = null;

		/*
		 * Get shift information from official table and create a emp-shift map
		 */
		String shiftSql = " SELECT EMP_TOKEN, EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_TOKEN IN ("
				+ empIdString + ")";
		Object[][] shiftData = getSqlModel().getSingleResult(shiftSql);

		HashMap<String, String> empShiftMap = new HashMap<String, String>();
		if (shiftData != null && shiftData.length > 0) {
			for (int i = 0; i < shiftData.length; i++) {
				empShiftMap.put(String.valueOf(shiftData[i][0]), String
						.valueOf(shiftData[i][1]));
			}
		}

		/*
		 * Create object to insert data
		 * 
		 */
		String inTime = "", outTime = "", attDate = "", empToken = "";
		Object[][] insertData = new Object[attendanceData.length][10];
		for (int i = 0; i < attendanceData.length; i++) {

			empToken = String.valueOf(attendanceData[i][0]);
			attDate = String.valueOf(attendanceData[i][2]);
			inTime = String.valueOf(attendanceData[i][3]);
			outTime = String.valueOf(attendanceData[i][4]);
			/**
			 * Attendance In and out times are attached with Dates based on
			 * Night shift and General shift
			 */
			attendanceDetail = dateWithTime(empToken, attDate, inTime, outTime);

			insertData[i][0] = checkNull(String.valueOf(attendanceData[i][0])
					.trim()); // employee code
			insertData[i][1] = checkNull(String.valueOf(attendanceData[i][2])
					.trim()); // attendance date
			insertData[i][2] = checkNull(String.valueOf(attendanceDetail[0][1])
					.trim()); // in time
			insertData[i][3] = checkNull(String.valueOf(attendanceDetail[0][2])
					.trim()); // out time
			insertData[i][4] = checkNull(String.valueOf(attendanceData[i][5])
					.trim()); // work hours
			insertData[i][5] = checkNull(String.valueOf(attendanceData[i][6])
					.trim()); // one time
			insertData[i][6] = checkNull(String.valueOf(attendanceData[i][7])
					.trim()); // io flag
			insertData[i][7] = checkNull(String.valueOf(attendanceData[i][8])
					.trim()); // shift
			if (String.valueOf(insertData[i][7]).length() == 0) {
				insertData[i][7] = empShiftMap.get(insertData[i][0]);
			}
			insertData[i][8] = branchCode; // branch code
			insertData[i][9] = divCode; // division code
		}

		/*
		 * Insert the data
		 */		
		String dateTimeFormat=DateFormat+" "+timeFormat;
		
		insertData=org.paradyne.lib.Utility.removeNullRows(insertData, 0);
		try {
			insertData = org.paradyne.lib.Utility.removeNullRows(insertData, 0);
			queryResult = getSqlModel().singleExecute(getQuery(4), insertData);
		} catch (Exception e) {
			logger.error(e);
		}

		if (queryResult) {
			result = "uploaded";
		}
		return result;
	}

	/**
	 * @Method Name : isConfigurationExist()
	 * @purpose to check whether the configuration is already available in
	 *          HRMS_ATTENDANCESETTINGS_HDR and HRMS_ATTENDANCESETTINGS_HDR for
	 *          the uploaded file for selected branch and division
	 * @param bean
	 * @param fileExtension
	 * @return object array
	 */
	public Object[][] isConfigurationExist(UploadAttendance bean,
			String fileExtension) {
		Object[] hdrQueryData = new Object[3];
		String fileType = "";

		if (fileExtension.equalsIgnoreCase("xls")) {
			fileType = "x";
		} else if (fileExtension.equalsIgnoreCase("txt")) {
			fileType = "t";
		} else if (fileExtension.equalsIgnoreCase("csv")) {
			fileType = "c";
		} else {
			return new Object[][] { { "wrong extension" } };
		}

		hdrQueryData[0] = bean.getBranchCode(); // branch code
		hdrQueryData[1] = bean.getDivCode(); // division code
		hdrQueryData[2] = fileType; // file type

		Object[][] getHdrData = getSqlModel().getSingleResult(getQuery(1),
				hdrQueryData);
		return getHdrData;
	}

	/**
	 * @Method Name : isWeeklyOff()
	 * @purpose to check whether the particular date is weekly off for that
	 *          employee or not
	 * @param currentDay
	 * @param getWeeklyOffData
	 * @return int
	 */
	public int isWeeklyOff(Calendar currentDay, Object getWeeklyOffData[][]) {
		int countDays = 0;
		int DAY_OF_WEEK = currentDay.get(java.util.Calendar.WEEK_OF_MONTH); // Day
		// of
		// week of specified date
		String[] weekDays = null;
		for (int i = 0; i < getWeeklyOffData[0].length; i++) {
			if (i + 1 == DAY_OF_WEEK) {
				weekDays = String.valueOf(getWeeklyOffData[0][i]).split(",");
			}
		}
		for (String string : weekDays) {
			int i = 0;
			try {
				i = Integer.parseInt(string.trim());
			} catch (Exception e) {
				logger.error(e);
				i = 0;
			}
			switch (i) {
			case 1:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 1) {
					countDays++;
				}
				break;
			case 2:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 2) {
					countDays++;
				}
				break;
			case 3:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 3) {
					countDays++;
				}
				break;
			case 4:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 4) {
					countDays++;
				}
				break;
			case 5:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 5) {
					countDays++;
				}
				break;
			case 6:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 6) {
					countDays++;
				}
				break;
			case 7:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 7) {
					countDays++;
				}
				break;
			default:
				break;
			} // end of switch
		} // end of for loop
		return countDays;
	}

	/**
	 * @Method Name : processAttendance()
	 * @purpose : call Another processAttendance method
	 * @param attendanceData
	 * @param fromDate
	 * @param toDate
	 * @param fldShift
	 * @param fldOneTime
	 * @param fldWorkHrs
	 * @param uploadFrom
	 * @param division
	 * @param branch
	 * @param extension
	 * @return String
	 */
	public String processAttendance(Object[][] attendanceData, String fromDate,
			String toDate, String fldShift, String fldOneTime,
			String fldWorkHrs, String uploadFrom, String division, String branch) {
		String str = processAttendance(attendanceData, fromDate, toDate,
				fldShift, fldOneTime, fldWorkHrs, uploadFrom, division, branch,
				null);
		return str;
	}

	/* Overriding processAttendance() Function */
	/**
	 * @param attendanceData :
	 *            Specifies the Attendance data of employee
	 * @purpose to actual process the attendance i.e to calculate half day, late
	 *          coming, early leaving, dual late, absents, holidays, weekly offs
	 *          etc for each employee and then insert the data in
	 *          HRMS_DAILY_ATTENDANCE_YEAR table
	 * @param fromDate :
	 *            Specifies attendance from date
	 * @param toDate :
	 *            Specifies attendance to date
	 * @param fldShift :
	 *            Specifies shift value
	 * @param fldOneTime :
	 *            Specifies OneTime flag
	 * @param fldWorkHrs :
	 *            Specifies WorkHrs flag
	 * @param uploadFrom :
	 *            Specifies source of data from where record has been uploaded
	 * @param division :
	 *            Specifies division code
	 * @param branch :
	 *            Specifies branch code
	 * @param conn :
	 *            Specifies connection object, used when called by scheduler
	 * @return String as query successfully executes or not
	 */
	public String processAttendance(Object[][] attendanceData, String fromDate,
			String toDate, String fldShift, String fldOneTime,
			String fldWorkHrs, String uploadFrom, String division,
			String branch, Connection conn) {
		//System.out.println("FETCH FROM SERVER : processAttendance ");
		String message = "";
		Object[][] getAttendanceData = null;
		boolean queryResult = false;

		String attDate = "";
		String year = fromDate.substring(6);

		/**
		 * Check the value of branch wise holiday flag. If it is true then
		 * holiday will be calculated branch wise other wise all holiday will be
		 * applied to that branch.
		 */
		String query = " SELECT CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF ";
		Object[][] branchFlag;

		/*
		 * check whether function is called by scheduler or not if true then
		 * pass connection object
		 */
		if (uploadFrom.equals("autoSchedule")) {
			branchFlag = SqlModel.getSingleResult(query, conn);
		} else {
			branchFlag = getSqlModel().getSingleResult(query);
		}
		try {
			// call calculateNumberOfDays method to calculate the number of days
			// between from date and to date
			int daysBetween = calculateNumberOfDays(fromDate, toDate);

			String tempEmpID = "";
			String tToken = "";

			for (int i = 0; i < attendanceData.length; i++) {
				if (!String.valueOf(attendanceData[i][0]).equals("null")
						&& !tToken.equals(String.valueOf(attendanceData[i][0]))) {
					tempEmpID += "'" + String.valueOf(attendanceData[i][0])
							+ "',";

					tToken = String.valueOf(attendanceData[i][0]);
				}
			}

			tempEmpID = tempEmpID.substring(0, (tempEmpID.length() - 1));

			String empCodeQuery = " SELECT DISTINCT TEMP_ATT_EMP_CODE, EMP_ID, HRMS_EMP_OFFC.EMP_SHIFT, "
					+ " NVL(TO_CHAR(SFT_START_TIME, 'HH24:MI:SS'),'00:00:00') AS START_TIME, NVL(TO_CHAR(SFT_END_TIME, 'HH24:MI:SS'),'00:00:00') AS END_TIME, "
					+ " NVL(TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'),'00:00:00') AS FIRST_HALF, NVL(TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00') AS SECOND_HALF, "
					+ " HRMS_SHIFT.SHIFT_NAME, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'), '00:00:00') AS LATE_MARK, "
					+ " NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'), '00:00:00') AS EARLY_LEAVING, SFT_NIGHT_FLAG "
					+ " FROM HRMS_ATTENDANCE_TEMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_TOKEN = HRMS_ATTENDANCE_TEMP.TEMP_ATT_EMP_CODE) "
					+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) "
					+ " WHERE 1 = 1 "
					+ Utility
							.getConcatenatedIds("TEMP_ATT_EMP_CODE", tempEmpID);

			// get employee ids separated by comma

			Object[][] empShiftData = null;

			/*
			 * check whether function is called by scheduler or not if true then
			 * pass connection object
			 */
			if (uploadFrom.equals("autoSchedule")) {
				empShiftData = SqlModel.getSingleResult(empCodeQuery, conn);
			} else {
				empShiftData = getSqlModel().getSingleResult(empCodeQuery);
			}

			for (int day = 0; day < daysBetween; day++) {
				String concatEmpIds = "";
				/**
				 * Call getAttendanceData method to retrieve attendance data
				 * from HRMS_ATT_TEMP table for a particular date and division
				 * and branch
				 */

				/*
				 * check whether function is called by scheduler or not if true
				 * then pass connection object
				 */
				if (uploadFrom.equals("autoSchedule")) {
					getAttendanceData = getAttendanceData(day, empShiftData,
							fromDate, fldShift, fldOneTime, fldWorkHrs,
							uploadFrom, division, branch, conn);
				} else {
					getAttendanceData = getAttendanceData(day, empShiftData,
							fromDate, fldShift, fldOneTime, fldWorkHrs,
							uploadFrom, division, branch);
				}

				if (getAttendanceData != null && getAttendanceData.length != 0) {
					Object[][] insertAttendanceData = new Object[getAttendanceData.length][11];
					for (int i = 0; i < getAttendanceData.length; i++) {
						Object[] shift_id = { checkNull(String.valueOf(
								getAttendanceData[i][3]).trim()) }; // shift id

						int count = 0;

						Object[][] holiDay = null;
						Object[] calculateTime = null;
						String status = "";

						/**
						 * If branch flag is true
						 */
						String holidaySql = "";

						if (branchFlag[0][0].equals("Y")) {
							// retrieve holiday details for that branch from
							// HRMS_HOLIDAY_BRANCH table for given date
							Object[] branchHolidayDate = new Object[2];
							
							branchHolidayDate[0] = checkNull(String.valueOf(
									getAttendanceData[i][1]).trim()); // employee
							// code
							branchHolidayDate[1] = checkNull(
									String.valueOf(getAttendanceData[i][2]))
									.trim(); // holiday date
							/*holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END "
									+ " FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = ?) AND "
									+ " HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') AND IS_ACTIVE='Y'";
							 holiDay =
							 getSqlModel().getSingleResult(holidaySql,branchHolidayDate);*/
							holidaySql=" SELECT HDATE  FROM ( "
									 + " SELECT HOLI_DATE AS HDATE FROM HRMS_HOLIDAY WHERE HOLI_TYPE='N' AND IS_ACTIVE='Y'"
									 + " UNION ALL " 
									 + " SELECT HOLI_DATE AS HDATE FROM HRMS_HOLIDAY_BRANCH WHERE"
									 + " CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+branchHolidayDate[0]+"))" 
									 + " WHERE HDATE=TO_DATE('"+branchHolidayDate[1]+"', 'DD-MM-YYYY')";
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */							
						} else {
							// retrieve holiday details from HRMS_HOLIDAY table
							Object[] holidayDate = new Object[1];
							holidayDate[0] = checkNull(String.valueOf(
									getAttendanceData[i][2]).trim()); // holiday
							// date
							

							holidaySql = " SELECT  HOLI_DATE FROM HRMS_HOLIDAY "
									+ " WHERE HOLI_DATE = TO_DATE('"+holidayDate[0]+"', 'DD-MM-YYYY')AND IS_ACTIVE='Y' ";
							// holiDay =
							// getSqlModel().getSingleResult(holidaySql,holidayDate);
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */							
						}
						if (uploadFrom.equals("autoSchedule")) {
							holiDay = getSqlModel().getSingleResult(
									holidaySql, conn);

						} else {
							holiDay = getSqlModel().getSingleResult(
									holidaySql);
						}
						if (holiDay != null && holiDay.length != 0) {
							status = "HO";
						} else {
							// retrieve the weekly off details from HRMS_SHIFT
							// table for given date
							String weekOffSql = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 "
									+ " FROM HRMS_SHIFT WHERE SHIFT_ID = ? ";
							Object[][] getWeeklyOffData = null;
							// getSqlModel().getSingleResult(weekOffSql,
							// shift_id);
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */
							if (uploadFrom.equals("autoSchedule")) {
								getWeeklyOffData = getSqlModel()
										.getSingleResult(weekOffSql, shift_id,
												conn);

							} else {
								getWeeklyOffData = getSqlModel()
										.getSingleResult(weekOffSql, shift_id);
							}

							// convert date in to calendar date
							Calendar currentDay = new Utility()
									.getCalanderDate(checkNull(String
											.valueOf(getAttendanceData[i][2])));

							try {
								// call isWeeklyOff method to check whether
								// given date is weekly off or not
								count = isWeeklyOff(currentDay,
										getWeeklyOffData);
							} catch (Exception e) {
								if (!fldShift.equals("")
										&& !String.valueOf(
												getAttendanceData[i][11])
												.equals(null)
										&& !String.valueOf(
												getAttendanceData[i][11])
												.equals("")
										&& !String.valueOf(
												getAttendanceData[i][11])
												.equals("null")) {
									return "shiftNotDefined"; // shift
									// configuration
									// not present
								} else if (!fldShift.equals("")) {
									// retrieve shift details for an employee
									// from HRMS_EMP_OFFC and HRMS_SHIFT tables
									String selectQury = " SELECT EMP_SHIFT, SHIFT_NAME, "
											+ " TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS') AS START_TIME, "
											+ " TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS') AS END_TIME, "
											+ " TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS') AS FIRST_HALF, "
											+ " TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS') AS SECOND_HALF FROM HRMS_EMP_OFFC "
											+ " INNER JOIN HRMS_SHIFT ON (EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) "
											+ " WHERE EMP_ID = "
											+ String
													.valueOf(getAttendanceData[i][1]);

									Object[][] getShiftDetails = null;
									// getSqlModel().getSingleResult(selectQury);
									/*
									 * check whether function is called by
									 * scheduler or not if true then pass
									 * connection object
									 */
									if (uploadFrom.equals("autoSchedule")) {
										getShiftDetails = SqlModel
												.getSingleResult(selectQury,
														conn);

									} else {
										getShiftDetails = getSqlModel()
												.getSingleResult(selectQury);
									}

									if (getShiftDetails != null
											&& getShiftDetails.length != 0) {
										getAttendanceData[i][3] = checkNull(String
												.valueOf(getShiftDetails[0][0])
												.trim()); // shift code
										getAttendanceData[i][11] = checkNull(String
												.valueOf(getShiftDetails[0][1])
												.trim());// shift name
										getAttendanceData[i][4] = checkNull(String
												.valueOf(getShiftDetails[0][2])
												.trim()); // shift start time
										getAttendanceData[i][5] = checkNull(String
												.valueOf(getShiftDetails[0][3])
												.trim()); // shift end time
										getAttendanceData[i][6] = checkNull(String
												.valueOf(getShiftDetails[0][4])
												.trim()); // shift first half
										// day
										getAttendanceData[i][7] = checkNull(String
												.valueOf(getShiftDetails[0][5])
												.trim()); // shift second half
										// day

										Object[] shiftId = { checkNull(String
												.valueOf(getShiftDetails[0][0])
												.trim()) }; // shift id
										try {
											// retrieve weekly off details for
											// above selected shift code
											Object[][] getWeeklyData = getSqlModel()
													.getSingleResult(
															weekOffSql, shiftId);

											// call isWeeklyOff method to check
											// whether given date is weekly off
											// or not
											count = isWeeklyOff(currentDay,
													getWeeklyData);
										} catch (Exception ex) {
											logger
													.error("Exception in processAttendance:"
															+ e);
										}
									}
								}
							}

							// if isWeeklyOff method return 1, given date is a
							// week off
							if (count == 1) {
								status = "WO"; // set status as weekly off
							}
						}
						/**
						 * call calculateLateAndEarlyHrs method to calculate
						 * late hours, early hours, extra hours etc as per the
						 * shift details and the values retrieved from the file
						 */
						String empInTime = checkNull(String
								.valueOf(getAttendanceData[i][8]));
						String empOutTime = checkNull(String
								.valueOf(getAttendanceData[i][9]));
						String offcStartTime = checkNull(String
								.valueOf(getAttendanceData[i][4]));
						String offcEndTime = checkNull(String
								.valueOf(getAttendanceData[i][5]));
						String firstHalfTime = checkNull(String
								.valueOf(getAttendanceData[i][6]));
						String secondHalfTime = checkNull(String
								.valueOf(getAttendanceData[i][7]));
						String nightFlag = checkNull(String
								.valueOf(getAttendanceData[i][13]));
						String lateMarkTime = checkNull(String
								.valueOf(getAttendanceData[i][14]));
						String earlyLeavingTime = checkNull(String
								.valueOf(getAttendanceData[i][15]));
						String flexiTimeFromQuery = checkNull(String
								.valueOf(getAttendanceData[i][16]));
						String workingHoursFromQuery = checkNull(String
								.valueOf(getAttendanceData[i][17]));
						String markAbsentAfterThisTime = checkNull(String
								.valueOf(getAttendanceData[i][18]));
						String markFlexiLateLessThan = checkNull(String
								.valueOf(getAttendanceData[i][19]));
						String markHalfDayLateLessThan = checkNull(String
								.valueOf(getAttendanceData[i][20]));
						String markFlexiAbsentBefore= checkNull(String.valueOf(getAttendanceData[i][21]));

						/**
						 * Calculate Extra, Early, Late hours and mark absent 
						 * for specific record 
						 * and define Status accordingly
						 * */
						calculateTime = calculateLateAndEarlyHrs(empInTime,
								empOutTime, offcStartTime, offcEndTime,
								firstHalfTime, secondHalfTime, nightFlag,
								lateMarkTime, earlyLeavingTime,
								flexiTimeFromQuery, workingHoursFromQuery,
								markAbsentAfterThisTime, markFlexiLateLessThan,
								markHalfDayLateLessThan,markFlexiAbsentBefore);

						attDate = checkNull(String.valueOf(
								getAttendanceData[i][2]).trim());

						Object[][] attendanceDetail = new Object[1][3];
						String empTokenStr = "", attInTime = "", attOutTime = "", attendanceDate = "";
						empTokenStr = String.valueOf(getAttendanceData[i][0]);
						attendanceDate = String
								.valueOf(getAttendanceData[i][2]);
						/**It retrieves data from HRMS_ATTENDANCE_TEMP 
						 * of particular Employee and Date 
						 */
						Object[] paramObj = new Object[2];
						paramObj[0] = String.valueOf(getAttendanceData[i][2]);
						paramObj[1] = String.valueOf(getAttendanceData[i][0]);
						String queryTemp = "SELECT TO_CHAR(TEMP_ATT_IN_TIME,'DD-MM-YYYY HH24:MI:SS'), "
								+ " TO_CHAR(TEMP_ATT_OUT_TIME,'DD-MM-YYYY HH24:MI:SS')"
								+ " FROM HRMS_ATTENDANCE_TEMP WHERE TEMP_ATT_DATE= TO_DATE('"+paramObj[0]+"', 'DD-MM-YYYY')"
								+ " AND  TEMP_ATT_EMP_CODE='"+paramObj[1]+"'";
				
						/**
						 * check whether function is called by scheduler or not if true then
						 * pass connection object
						 */
						if (uploadFrom.equals("autoSchedule")) {
							attendanceDetail = SqlModel.getSingleResult(
									queryTemp,conn);

						} else {
							attendanceDetail = getSqlModel()
							.getSingleResult(queryTemp);
						}

						try {
							insertAttendanceData[i][0] = checkNull(String
									.valueOf(getAttendanceData[i][2]).trim()); // ATT_DATE
							insertAttendanceData[i][1] = checkNull(String
									.valueOf(getAttendanceData[i][1]).trim()); // ATT_EMP_ID
							insertAttendanceData[i][2] = checkNull(String
									.valueOf(getAttendanceData[i][3]).trim()); // ATT_SHIFT_ID
							insertAttendanceData[i][3] = checkNull(String
									.valueOf(attendanceDetail[0][0]).trim()); // ATT_LOGIN
							insertAttendanceData[i][4] = checkNull(String
									.valueOf(attendanceDetail[0][1]).trim()); // ATT_LOGOUT
							insertAttendanceData[i][5] = checkNull(String
									.valueOf(getAttendanceData[i][10]).trim()); // ATT_WORKING_HRS
							insertAttendanceData[i][6] = String.valueOf(
									calculateTime[4]).trim(); // ATT_EXTRAHRS
							insertAttendanceData[i][7] = checkNull(String
									.valueOf(calculateTime[0]).trim()); // ATT_LATE_HRS
							insertAttendanceData[i][8] = checkNull(String
									.valueOf(calculateTime[1]).trim()); // ATT_EARLY_HRS
						} catch (Exception e) {
							logger.info("Error while generating insert data : "
									+ e);
						}
						if (String.valueOf(getAttendanceData[i][8]).equals(
								"00:00:00")
								&& String.valueOf(getAttendanceData[i][9])
										.equals("00:00:00")
								&& !status.equals("HO") && !status.equals("WO")) {
							status = "AB";
						} else if (!status.equals("HO") && !status.equals("WO")
								&& !status.equals("AB")) {
							status = "PR";
						}

						insertAttendanceData[i][9] = status; // status one
						String statusTwo = "";
						if (status.equals("AB")) {
							statusTwo = "AB";
						} else {
							statusTwo = checkNull(String.valueOf(
									calculateTime[5]).trim());
						}
						insertAttendanceData[i][10] = statusTwo; // status two
						concatEmpIds = concatEmpIds
								+ String.valueOf(insertAttendanceData[i][1])
								+ ",";
					}
					concatEmpIds = concatEmpIds.substring(0, concatEmpIds
							.length() - 1);

					String fromYear = fromDate.substring(6);
					String toYear = toDate.substring(6);

					if (fromYear.equals(toYear)) {
						String selectQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ year
								+ " WHERE 1 = 1 "
								+ Utility.getConcatenatedIds("ATT_EMP_ID",
										concatEmpIds)
								+ " AND "
								+ " ATT_DATE = TO_DATE('"
								+ attDate
								+ "', 'DD-MM-YYYY') ";

						Object[][] attExistEmp = null;
						// getSqlModel().getSingleResult(selectQuery);
						/**
						 * check whether function is called by scheduler or not
						 * if true then pass connection object
						 */
						if (uploadFrom.equals("autoSchedule")) {
							attExistEmp = SqlModel.getSingleResult(selectQuery,
									conn);

						} else {
							attExistEmp = getSqlModel().getSingleResult(
									selectQuery);
						}

						Object[][] updateAttData = null;

						if (attExistEmp != null && attExistEmp.length > 0) {
							updateAttData = new Object[attExistEmp.length][11];

							for (int m = 0; m < attExistEmp.length; m++) {
								for (int n = 0; n < insertAttendanceData.length; n++) {
									if (String
											.valueOf(attExistEmp[m][0])
											.equals(
													String
															.valueOf(insertAttendanceData[n][1]))
											&& String
													.valueOf(attExistEmp[m][1])
													.equals(
															String
																	.valueOf(insertAttendanceData[n][0]))) {
										updateAttData[m][0] = String
												.valueOf(insertAttendanceData[n][2]); // ATT_SHIFT_ID
										updateAttData[m][1] = String
												.valueOf(insertAttendanceData[n][3]); // ATT_LOGIN
										updateAttData[m][2] = String
												.valueOf(insertAttendanceData[n][4]); // ATT_LOGOUT
										updateAttData[m][3] = String
												.valueOf(insertAttendanceData[n][5]); // ATT_WORKING_HRS
										updateAttData[m][4] = String
												.valueOf(insertAttendanceData[n][6]); // ATT_EXTRAHRS
										updateAttData[m][5] = String
												.valueOf(insertAttendanceData[n][7]); // ATT_LATE_HRS
										updateAttData[m][6] = String
												.valueOf(insertAttendanceData[n][8]); // ATT_EARLY_HRS
										updateAttData[m][7] = String
												.valueOf(insertAttendanceData[n][9]); // ATT_STATUS_ONE
										updateAttData[m][8] = String
												.valueOf(insertAttendanceData[n][10]); // ATT_STATUS_TWO
										updateAttData[m][9] = String
												.valueOf(insertAttendanceData[n][0]); // ATT_DATE
										updateAttData[m][10] = String
												.valueOf(insertAttendanceData[n][1]); // ATT_EMP_ID

										break;
									}
								}
							}
							String updateQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ year
									+ " SET ATT_SHIFT_ID = ?, "
									+ " ATT_LOGIN = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), "
									+ " ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS') , "
									+ " ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ? , "
									+ " ATT_STATUS_TWO = ? WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";

							/**
							 * to remove null rows
							 * 
							 */
							for (int k = 0; k < updateAttData.length; k++) {
								if (updateAttData[k][1] == null
										|| String.valueOf(updateAttData[k][1])
												.equals("null"))
									updateAttData[k][1] = String
											.valueOf(updateAttData[k][9])
											+ " 00:00:00";
								if (updateAttData[k][2] == null
										|| String.valueOf(updateAttData[k][2])
												.equals("null"))
									updateAttData[k][2] = String
											.valueOf(updateAttData[k][9])
											+ " 00:00:00";
								if (updateAttData[k][3] == null
										|| String.valueOf(updateAttData[k][3])
												.equals("null"))
									updateAttData[k][3] = "00:00:00";
								if (updateAttData[k][4] == null
										|| String.valueOf(updateAttData[k][4])
												.equals("null"))
									updateAttData[k][4] = "00:00:00";
								if (updateAttData[k][5] == null
										|| String.valueOf(updateAttData[k][5])
												.equals("null"))
									updateAttData[k][5] = "00:00:00";
								if (updateAttData[k][6] == null
										|| String.valueOf(updateAttData[k][6])
												.equals("null"))
									updateAttData[k][6] = "00:00:00";
							}							
							for (int k = 0; k < updateAttData.length; k++) {								
								for (int p = 0; p < updateAttData[0].length; p++) {								
								}
							}							
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */
							try {
								if (uploadFrom.equals("autoSchedule")) {
									queryResult = getSqlModel().singleExecute(
											updateQuerySql, updateAttData, conn);

								} else {
									queryResult = getSqlModel().singleExecute(
											updateQuerySql, updateAttData);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}						
						Object[][] insertAttData = null;
						int insertDataLength = 0;

						if (attExistEmp != null && attExistEmp.length > 0) {
							insertDataLength = insertAttendanceData.length
									- attExistEmp.length;
						} else if (insertAttendanceData != null
								&& insertAttendanceData.length > 0) {
							insertDataLength = insertAttendanceData.length;
						}

						if (insertDataLength > 0) {
							insertAttData = new Object[insertDataLength][11];
							boolean flag = false;
							int cnt = 0;

							for (int p = 0; p < insertAttendanceData.length; p++) {
								for (int q = 0; q < attExistEmp.length; q++) {
									if (String
											.valueOf(attExistEmp[q][0])
											.equals(
													String
															.valueOf(insertAttendanceData[p][1]))
											&& String
													.valueOf(attExistEmp[q][1])
													.equals(
															String
																	.valueOf(insertAttendanceData[p][0]))) {
										flag = true;
									}
								}

								if (!flag) {
									
									try {
										insertAttData[cnt][0] = String
												.valueOf(insertAttendanceData[p][0]); // ATT_DATE
										insertAttData[cnt][1] = String
												.valueOf(insertAttendanceData[p][1]); // ATT_EMP_ID
										insertAttData[cnt][2] = String
												.valueOf(insertAttendanceData[p][2]); // ATT_SHIFT_ID
										insertAttData[cnt][3] = String
												.valueOf(insertAttendanceData[p][3]); // ATT_LOGIN
										insertAttData[cnt][4] = String
												.valueOf(insertAttendanceData[p][4]); // ATT_LOGOUT
										insertAttData[cnt][5] = String
												.valueOf(insertAttendanceData[p][5]); // ATT_WORKING_HRS
										insertAttData[cnt][6] = String
												.valueOf(insertAttendanceData[p][6]); // ATT_EXTRAHRS
										insertAttData[cnt][7] = String
												.valueOf(insertAttendanceData[p][7]); // ATT_LATE_HRS
										insertAttData[cnt][8] = String
												.valueOf(insertAttendanceData[p][8]); // ATT_EARLY_HRS
										insertAttData[cnt][9] = String
												.valueOf(insertAttendanceData[p][9]); // ATT_STATUS_ONE
										insertAttData[cnt][10] = String
												.valueOf(insertAttendanceData[p][10]); // ATT_STATUS_TWO
									} catch (Exception e) {
										e.printStackTrace();
									}									
									cnt++;
								}
								flag = false;
							}						
							// insert attendance data with all calculations in
							// HRMS_DAILY_ATTENDANCE_YEAR table
							String insertQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ year
									+ " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, "
									+ " ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) "
									+ " VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), "
									+ " TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?)";
							/**
							 * to remove null rows
							 * 
							 */
							for (int k = 0; k < insertAttData.length; k++) {
								if (insertAttData[k][3] == null
										|| String.valueOf(insertAttData[k][3])
												.equals("null"))
									insertAttData[k][3] = String
											.valueOf(insertAttData[k][0])
											+ " 00:00:00";
								if (insertAttData[k][4] == null
										|| String.valueOf(insertAttData[k][4])
												.equals("null"))
									insertAttData[k][4] = String
											.valueOf(insertAttData[k][0])
											+ " 00:00:00";
								if (insertAttData[k][5] == null
										|| String.valueOf(insertAttData[k][5])
												.equals("null"))
									insertAttData[k][5] = "00:00:00";
								if (insertAttData[k][6] == null
										|| String.valueOf(insertAttData[k][6])
												.equals("null"))
									insertAttData[k][6] = "00:00:00";
								if (insertAttData[k][7] == null
										|| String.valueOf(insertAttData[k][7])
												.equals("null"))
									insertAttData[k][7] = "00:00:00";
								if (insertAttData[k][8] == null
										|| String.valueOf(insertAttData[k][8])
												.equals("null"))
									insertAttData[k][8] = "00:00:00";
							}																				
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */
							try {
								if (uploadFrom.equals("autoSchedule")) {
									queryResult = getSqlModel().singleExecute(
											insertQuerySql, insertAttData, conn);

								} else {
									queryResult = getSqlModel().singleExecute(
											insertQuerySql, insertAttData);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}							
						}
					} else {
						int count = 0;
						for (int k = 0; k < insertAttendanceData.length; k++) {
							if (fromYear.equals(String.valueOf(
									insertAttendanceData[k][0]).substring(6))) {
								count++;
							}
						}
						Object[][] fromObject = null, toBoject = null;

						if (count != 0) {
							fromObject = new Object[count][11];
						}
						if (insertAttendanceData.length - count != 0) {
							toBoject = new Object[insertAttendanceData.length
									- count][11];
						}
						int fromCount = 0, toCount = 0;

						for (int m = 0; m < insertAttendanceData.length; m++) {
							if (fromYear.equals(String.valueOf(
									insertAttendanceData[m][0]).substring(6))) {
								if (count != 0) {
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
								if (insertAttendanceData.length - count != 0) {
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
						
						// ============== for From Year
						// ================================================

						Object[][] attExistEmpFrom = null;

						if (count != 0) {
							String selectFromQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
									+ fromYear
									+ " WHERE 1 = 1 "
									+ Utility.getConcatenatedIds("ATT_EMP_ID",
											concatEmpIds)
									+ " AND ATT_DATE = TO_DATE('"
									+ attDate
									+ "','DD-MM-YYYY')";
							// attExistEmpFrom =
							// getSqlModel().getSingleResult(selectFromQuery);
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */
							if (uploadFrom.equals("autoSchedule")) {
								attExistEmpFrom = SqlModel.getSingleResult(
										selectFromQuery, conn);
							} else {
								attExistEmpFrom = getSqlModel()
										.getSingleResult(selectFromQuery);
							}
						}						
						Object[][] updateAttDataFrom = null;
						if (attExistEmpFrom != null
								&& attExistEmpFrom.length > 0) {
							updateAttDataFrom = new Object[attExistEmpFrom.length][11];

							for (int m = 0; m < attExistEmpFrom.length; m++) {
								for (int n = 0; n < fromObject.length; n++) {
									if (String
											.valueOf(attExistEmpFrom[m][0])
											.equals(
													String
															.valueOf(fromObject[n][1]))
											&& String
													.valueOf(
															attExistEmpFrom[m][1])
													.equals(
															String
																	.valueOf(fromObject[n][0]))) {
										updateAttDataFrom[m][0] = String
												.valueOf(fromObject[n][2]); // ATT_SHIFT_ID
										updateAttDataFrom[m][1] = String
												.valueOf(fromObject[n][3]); // ATT_LOGIN
										updateAttDataFrom[m][2] = String
												.valueOf(fromObject[n][4]); // ATT_LOGOUT
										updateAttDataFrom[m][3] = String
												.valueOf(fromObject[n][5]); // ATT_WORKING_HRS
										updateAttDataFrom[m][4] = String
												.valueOf(fromObject[n][6]); // ATT_EXTRAHRS
										updateAttDataFrom[m][5] = String
												.valueOf(fromObject[n][7]); // ATT_LATE_HRS
										updateAttDataFrom[m][6] = String
												.valueOf(fromObject[n][8]); // ATT_EARLY_HRS
										updateAttDataFrom[m][7] = String
												.valueOf(fromObject[n][9]); // ATT_STATUS_ONE
										updateAttDataFrom[m][8] = String
												.valueOf(fromObject[n][10]); // ATT_STATUS_TWO
										updateAttDataFrom[m][9] = String
												.valueOf(fromObject[n][0]); // ATT_DATE
										updateAttDataFrom[m][10] = String
												.valueOf(fromObject[n][1]); // ATT_EMP_ID
										break;
									}
								}
							}							
							
							if (count != 0) {
								String updateQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_"
										+ year
										+ " SET ATT_SHIFT_ID = ?, "
										+ " ATT_LOGIN = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), "
										+ " ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), "
										+ " ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ?, ATT_STATUS_TWO = ? "
										+ " WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";

								/**
								 * to remove null rows
								 * 
								 */
								for (int k = 0; k < updateAttDataFrom.length; k++) {
									if (updateAttDataFrom[k][1] == null
											|| String.valueOf(
													updateAttDataFrom[k][1])
													.equals("null"))
										updateAttDataFrom[k][1] = String
												.valueOf(updateAttDataFrom[k][9])
												+ " 00:00:00";
									if (updateAttDataFrom[k][2] == null
											|| String.valueOf(
													updateAttDataFrom[k][2])
													.equals("null"))
										updateAttDataFrom[k][2] = String
												.valueOf(updateAttDataFrom[k][9])
												+ " 00:00:00";
									if (updateAttDataFrom[k][3] == null
											|| String.valueOf(
													updateAttDataFrom[k][3])
													.equals("null"))
										updateAttDataFrom[k][3] = "00:00:00";
									if (updateAttDataFrom[k][4] == null
											|| String.valueOf(
													updateAttDataFrom[k][4])
													.equals("null"))
										updateAttDataFrom[k][4] = "00:00:00";
									if (updateAttDataFrom[k][5] == null
											|| String.valueOf(
													updateAttDataFrom[k][5])
													.equals("null"))
										updateAttDataFrom[k][5] = "00:00:00";
									if (updateAttDataFrom[k][6] == null
											|| String.valueOf(
													updateAttDataFrom[k][6])
													.equals("null"))
										updateAttDataFrom[k][6] = "00:00:00";
								}															
								/*
								 * check whether function is called by scheduler
								 * or not if true then pass connection object
								 */
								try {
									if (uploadFrom.equals("autoSchedule")) {
										queryResult = getSqlModel().singleExecute(
												updateQuerySql, updateAttDataFrom,
												conn);
									} else {
										queryResult = getSqlModel().singleExecute(
												updateQuerySql, updateAttDataFrom);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}															
							}
						}
						Object[][] insertAttDataFrom = null;
						int insertDataFromLength = 0;

						if (attExistEmpFrom != null
								&& attExistEmpFrom.length > 0) {
							insertDataFromLength = fromObject.length
									- attExistEmpFrom.length;
						} else if (fromObject != null && fromObject.length > 0) {
							insertDataFromLength = fromObject.length;
						}

						if (insertDataFromLength > 0) {
							insertAttDataFrom = new Object[insertDataFromLength][11];
							boolean flag = false;
							int cnt = 0;
							for (int p = 0; p < fromObject.length; p++) {
								for (int q = 0; q < attExistEmpFrom.length; q++) {
									if (String
											.valueOf(attExistEmpFrom[q][0])
											.equals(
													String
															.valueOf(fromObject[p][1]))
											&& String
													.valueOf(
															attExistEmpFrom[q][1])
													.equals(
															String
																	.valueOf(fromObject[p][0]))) {
										flag = true;
									}
								}
								if (!flag) {
									insertAttDataFrom[cnt][0] = String
											.valueOf(fromObject[p][0]); // ATT_DATE
									insertAttDataFrom[cnt][1] = String
											.valueOf(fromObject[p][1]); // ATT_EMP_ID
									insertAttDataFrom[cnt][2] = String
											.valueOf(fromObject[p][2]); // ATT_SHIFT_ID
									insertAttDataFrom[cnt][3] = String
											.valueOf(fromObject[p][3]); // ATT_LOGIN
									insertAttDataFrom[cnt][4] = String
											.valueOf(fromObject[p][4]); // ATT_LOGOUT
									insertAttDataFrom[cnt][5] = String
											.valueOf(fromObject[p][5]); // ATT_WORKING_HRS
									insertAttDataFrom[cnt][6] = String
											.valueOf(fromObject[p][6]); // ATT_EXTRAHRS
									insertAttDataFrom[cnt][7] = String
											.valueOf(fromObject[p][7]); // ATT_LATE_HRS
									insertAttDataFrom[cnt][8] = String
											.valueOf(fromObject[p][8]); // ATT_EARLY_HRS
									insertAttDataFrom[cnt][9] = String
											.valueOf(fromObject[p][9]); // ATT_STATUS_ONE
									insertAttDataFrom[cnt][10] = String
											.valueOf(fromObject[p][10]); // ATT_STATUS_TWO

									cnt++;
								}
								flag = false;
							}
							// insert attendance data with all calculations in
							// HRMS_DAILY_ATTENDANCE_YEAR table
							String insertFromQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ fromYear
									+ " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, "
									+ " ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) "
									+ " VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), "
									+ " TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?) ";

							if (count != 0) {
								// queryResult =
								// getSqlModel().singleExecute(insertFromQuerySql,
								// insertAttDataFrom);
								/**
								 * to remove null rows
								 * 
								 */
								for (int k = 0; k < insertAttDataFrom.length; k++) {
									if (insertAttDataFrom[k][3] == null
											|| String.valueOf(
													insertAttDataFrom[k][3])
													.equals("null"))
										insertAttDataFrom[k][3] = String
												.valueOf(insertAttDataFrom[k][0])
												+ " 00:00:00";
									if (insertAttDataFrom[k][4] == null
											|| String.valueOf(
													insertAttDataFrom[k][4])
													.equals("null"))
										insertAttDataFrom[k][4] = String
												.valueOf(insertAttDataFrom[k][0])
												+ " 00:00:00";
									if (insertAttDataFrom[k][5] == null
											|| String.valueOf(
													insertAttDataFrom[k][5])
													.equals("null"))
										insertAttDataFrom[k][5] = "00:00:00";
									if (insertAttDataFrom[k][6] == null
											|| String.valueOf(
													insertAttDataFrom[k][6])
													.equals("null"))
										insertAttDataFrom[k][6] = "00:00:00";
									if (insertAttDataFrom[k][7] == null
											|| String.valueOf(
													insertAttDataFrom[k][7])
													.equals("null"))
										insertAttDataFrom[k][7] = "00:00:00";
									if (insertAttDataFrom[k][8] == null
											|| String.valueOf(
													insertAttDataFrom[k][8])
													.equals("null"))
										insertAttDataFrom[k][8] = "00:00:00";
								}								
								/*
								 * check whether function is called by scheduler
								 * or not if true then pass connection object
								 */
								try {
									if (uploadFrom.equals("autoSchedule")) {
										queryResult = getSqlModel().singleExecute(
												insertFromQuerySql,
												insertAttDataFrom, conn);

									} else {
										queryResult = getSqlModel().singleExecute(
												insertFromQuerySql,
												insertAttDataFrom);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}					

						String selectToQuery = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ toYear
								+ " WHERE 1 = 1 "
								+ Utility.getConcatenatedIds("ATT_EMP_ID",
										concatEmpIds)
								+ " AND ATT_DATE = TO_DATE('"
								+ attDate
								+ "', 'DD-MM-YYYY')";

						Object[][] attExistEmpTo = null;
						if (insertAttendanceData.length - count != 0) {
							// attExistEmpTo =
							// getSqlModel().getSingleResult(selectToQuery);
							/*
							 * check whether function is called by scheduler or
							 * not if true then pass connection object
							 */
							if (uploadFrom.equals("autoSchedule")) {
								attExistEmpTo = SqlModel.getSingleResult(
										selectToQuery, conn);
							} else {
								attExistEmpTo = getSqlModel().getSingleResult(
										selectToQuery);
							}
						}

						Object[][] updateAttDataTo = null;
						if (attExistEmpTo != null && attExistEmpTo.length > 0) {
							updateAttDataTo = new Object[attExistEmpTo.length][11];
							for (int m = 0; m < attExistEmpTo.length; m++) {
								for (int n = 0; n < toBoject.length; n++) {
									if (String
											.valueOf(attExistEmpTo[m][0])
											.equals(
													String
															.valueOf(toBoject[n][1]))
											&& String
													.valueOf(
															attExistEmpTo[m][1])
													.equals(
															String
																	.valueOf(toBoject[n][0]))) {
										updateAttDataTo[m][0] = String
												.valueOf(toBoject[n][2]); // ATT_SHIFT_ID
										updateAttDataTo[m][1] = String
												.valueOf(toBoject[n][3]); // ATT_LOGIN
										updateAttDataTo[m][2] = String
												.valueOf(toBoject[n][4]); // ATT_LOGOUT
										updateAttDataTo[m][3] = String
												.valueOf(toBoject[n][5]); // ATT_WORKING_HRS
										updateAttDataTo[m][4] = String
												.valueOf(toBoject[n][6]); // ATT_EXTRAHRS
										updateAttDataTo[m][5] = String
												.valueOf(toBoject[n][7]); // ATT_LATE_HRS
										updateAttDataTo[m][6] = String
												.valueOf(toBoject[n][8]); // ATT_EARLY_HRS
										updateAttDataTo[m][7] = String
												.valueOf(toBoject[n][9]); // ATT_STATUS_ONE
										updateAttDataTo[m][8] = String
												.valueOf(toBoject[n][10]); // ATT_STATUS_TWO
										updateAttDataTo[m][9] = String
												.valueOf(toBoject[n][0]); // ATT_DATE
										updateAttDataTo[m][10] = String
												.valueOf(toBoject[n][1]); // ATT_EMP_ID

										break;
									}
								}
							}

							String updateToQuerySql = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ toYear
									+ " SET ATT_SHIFT_ID = ?, "
									+ " ATT_LOGIN =  TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), "
									+ " ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), "
									+ " ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_STATUS_ONE = ?, "
									+ " ATT_STATUS_TWO = ? WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";

							if (insertAttendanceData.length - count != 0) {
								/**
								 * to remove null rows
								 * 
								 */
								for (int k = 0; k < updateAttDataTo.length; k++) {
									if (updateAttDataTo[k][1] == null
											|| String.valueOf(
													updateAttDataTo[k][1])
													.equals("null"))
										updateAttDataTo[k][1] = String
												.valueOf(updateAttDataTo[k][9])
												+ " 00:00:00";
									if (updateAttDataTo[k][2] == null
											|| String.valueOf(
													updateAttDataTo[k][2])
													.equals("null"))
										updateAttDataTo[k][2] = String
												.valueOf(updateAttDataTo[k][9])
												+ " 00:00:00";
									if (updateAttDataTo[k][3] == null
											|| String.valueOf(
													updateAttDataTo[k][3])
													.equals("null"))
										updateAttDataTo[k][3] = "00:00:00";
									if (updateAttDataTo[k][4] == null
											|| String.valueOf(
													updateAttDataTo[k][4])
													.equals("null"))
										updateAttDataTo[k][4] = "00:00:00";
									if (updateAttDataTo[k][5] == null
											|| String.valueOf(
													updateAttDataTo[k][5])
													.equals("null"))
										updateAttDataTo[k][5] = "00:00:00";
									if (updateAttDataTo[k][6] == null
											|| String.valueOf(
													updateAttDataTo[k][6])
													.equals("null"))
										updateAttDataTo[k][6] = "00:00:00";
								}								
								/*
								 * check whether function is called by scheduler
								 * or not if true then pass connection object
								 */
								try {
									if (uploadFrom.equals("autoSchedule")) {
										queryResult = getSqlModel().singleExecute(
												updateToQuerySql, updateAttDataTo,
												conn);
									} else {
										queryResult = getSqlModel().singleExecute(
												updateToQuerySql, updateAttDataTo);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						Object[][] insertAttDataTo = null;
						int insertDataToLength = 0;

						if (attExistEmpTo != null && attExistEmpTo.length > 0) {
							insertDataToLength = toBoject.length
									- attExistEmpTo.length;
						} else if (toBoject != null && toBoject.length > 0) {
							insertDataToLength = toBoject.length;
						}
						if (insertDataToLength > 0) {
							insertAttDataTo = new Object[insertDataToLength][11];
							boolean flag = false;
							int cnt = 0;
							for (int p = 0; p < toBoject.length; p++) {
								for (int q = 0; q < attExistEmpTo.length; q++) {
									if (String
											.valueOf(attExistEmpTo[q][0])
											.equals(
													String
															.valueOf(toBoject[p][1]))
											&& String
													.valueOf(
															attExistEmpTo[q][1])
													.equals(
															String
																	.valueOf(toBoject[p][0]))) {
										flag = true;
									}
								}
								if (!flag) {
									insertAttDataTo[cnt][0] = String
											.valueOf(toBoject[p][0]); // ATT_DATE
									insertAttDataTo[cnt][1] = String
											.valueOf(toBoject[p][1]); // ATT_EMP_ID
									insertAttDataTo[cnt][2] = String
											.valueOf(toBoject[p][2]); // ATT_SHIFT_ID
									insertAttDataTo[cnt][3] = String
											.valueOf(toBoject[p][3]); // ATT_LOGIN
									insertAttDataTo[cnt][4] = String
											.valueOf(toBoject[p][4]); // ATT_LOGOUT
									insertAttDataTo[cnt][5] = String
											.valueOf(toBoject[p][5]); // ATT_WORKING_HRS
									insertAttDataTo[cnt][6] = String
											.valueOf(toBoject[p][6]); // ATT_EXTRAHRS
									insertAttDataTo[cnt][7] = String
											.valueOf(toBoject[p][7]); // ATT_LATE_HRS
									insertAttDataTo[cnt][8] = String
											.valueOf(toBoject[p][8]); // ATT_EARLY_HRS
									insertAttDataTo[cnt][9] = String
											.valueOf(toBoject[p][9]); // ATT_STATUS_ONE
									insertAttDataTo[cnt][10] = String
											.valueOf(toBoject[p][10]); // ATT_STATUS_TWO
									cnt++;
								}
								flag = false;
							}
							// insert attendance data with all calculations in
							// HRMS_DAILY_ATTENDANCE_YEAR table
							String insertFromQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ toYear
									+ " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, "
									+ " ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO) "
									+ " VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), "
									+ " TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?) ";

							if (insertAttendanceData.length - count != 0) {
								// queryResult =
								// getSqlModel().singleExecute(insertFromQuerySql,
								// insertAttDataTo);
								/*
								 * check whether function is called by scheduler
								 * or not if true then pass connection object
								 */
								/**
								 * to remove null rows
								 * 
								 */
								for (int k = 0; k < insertAttDataTo.length; k++) {
									if (insertAttDataTo[k][3] == null
											|| String.valueOf(
													insertAttDataTo[k][3])
													.equals("null"))
										insertAttDataTo[k][3] = String
												.valueOf(insertAttDataTo[k][0])
												+ " 00:00:00";
									if (insertAttDataTo[k][4] == null
											|| String.valueOf(
													insertAttDataTo[k][4])
													.equals("null"))
										insertAttDataTo[k][4] = String
												.valueOf(insertAttDataTo[k][0])
												+ " 00:00:00";
									if (insertAttDataTo[k][5] == null
											|| String.valueOf(
													insertAttDataTo[k][5])
													.equals("null"))
										insertAttDataTo[k][5] = "00:00:00";
									if (insertAttDataTo[k][6] == null
											|| String.valueOf(
													insertAttDataTo[k][6])
													.equals("null"))
										insertAttDataTo[k][6] = "00:00:00";
									if (insertAttDataTo[k][7] == null
											|| String.valueOf(
													insertAttDataTo[k][7])
													.equals("null"))
										insertAttDataTo[k][7] = "00:00:00";
									if (insertAttDataTo[k][8] == null
											|| String.valueOf(
													insertAttDataTo[k][8])
													.equals("null"))
										insertAttDataTo[k][8] = "00:00:00";
								}								
								try {
									if (uploadFrom.equals("autoSchedule")) {
										queryResult = getSqlModel().singleExecute(
												insertFromQuerySql,
												insertAttDataTo, conn);

									} else {
										queryResult = getSqlModel()
												.singleExecute(insertFromQuerySql,
														insertAttDataTo);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					if (queryResult) {
						message = "inserted"; // data inserted successfully
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in processAttendance:" + e);
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * @Method Name :readCsvFile()
	 * @purpose to read the data from the csv file
	 * @param bean
	 * @param filePath
	 * @param getHdrData
	 * @param getDtlData
	 * @return Object array
	 */
	public Object[][] readCsvFile(UploadAttendance bean, String filePath,
			Object[][] getHdrData, Object[][] getDtlData) {
		Object[][] attendanceData = null;
		int j = 0;
		int no_match = 0;
		String validDate = "";
		String validTime = "";

		int empCodeColumn = returnFieldValue(getDtlData[0][4]); // column index
		// for employee code
		int dateColumn = returnFieldValue(getDtlData[1][4]); // column index
		// for attendance date
		int inTimeColumn = returnFieldValue(getDtlData[2][4]); // column index
		// for in time
		int outTimeColumn = returnFieldValue(getDtlData[3][4]); // column index
		// for out time
		int timeColumn = returnFieldValue(getDtlData[4][4]); // column index
		// for one time
		int ioFlagColumn = returnFieldValue(getDtlData[5][4]); // column index
		// for io flag
		int workHrsColumn = returnFieldValue(getDtlData[6][4]); // column index
		// for work hours
		int shiftColumn = returnFieldValue(getDtlData[7][4]); // column index
		// for shift

		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			char s = '"';
			String line = br.readLine().replaceAll(String.valueOf(s), ""); // read
			// first
			// line from the file
			/*
			 * call getNumberOfLines method to find the number of lines
			 */
			int numberOfLines = getNumberOfLines(filePath);

			attendanceData = new Object[numberOfLines + 1][9];

			int i = 1;
			String employeeCode = "";

			while (line != null) {
				if (line.length() != 0 && !line.trim().equals("")) {
					String actualWorkHrs = "";
					String actualDate = "";
					String[] values = line.split(",");
					String empCode = ""; // employee code
					String empName = ""; // employee name
					String date = ""; // attendance date
					String inTime = ""; // in time
					String outTime = ""; // out time
					String oneTime = ""; // one time
					String ioFlag = ""; // io flag
					String workHrs = ""; // work hours
					String shift = ""; // shift

					if (dateColumn != -1) {
						String dateFromFile = "";
						try {
							dateFromFile = values[dateColumn - 1]; // retrieve
							// attendance
							// date
							/*
							 * call checkDateFormat method to check the date
							 * retrieved from file is in proper format or not
							 */
							actualDate = checkDateFormat(dateFromFile, String
									.valueOf(getHdrData[0][3]));
						} catch (Exception e) {
						}

						String emp_Code = "";

						if (!actualDate.equals("")) {
							if (empCodeColumn != -1) {
								emp_Code = values[empCodeColumn - 1]; // retrieve
								// employee
								// code
							}
							if (!emp_Code.trim().equals("")) {
								employeeCode = emp_Code; // if employee code
								// retrieved from
								// file is null or
								// blank, set the previous employee code
							}
						}
					}

					if (!actualDate.equals("")) {
						if (inTimeColumn != -1
								&& !bean.getOneTimeFlag().equals("true")) {
							String inTimeFromFile = "";
							try {
								inTimeFromFile = values[inTimeColumn - 1]; // retrieve
								// in
								// time
								/*
								 * call checkTimeFormat method to check that in
								 * time retrieved from file is in proper format
								 * or not
								 */
								actualWorkHrs = checkTimeFormat(bean,
										getHdrData, inTimeFromFile);
							} catch (Exception e) {
							}
						}

						if (actualWorkHrs.equals("") && outTimeColumn != -1
								&& !bean.getOneTimeFlag().equals("true")) {
							String outTimeFromFile = "";
							try {
								outTimeFromFile = values[outTimeColumn - 1]; // retrieve
								// out
								// time
								/*
								 * call checkTimeFormat method to check that out
								 * time retrieved from file is in proper format
								 * or not
								 */
								actualWorkHrs = checkTimeFormat(bean,
										getHdrData, outTimeFromFile);
							} catch (Exception e) {
							}
						}

						if (timeColumn != -1
								&& bean.getOneTimeFlag().equals("true")) {
							String oneTimeFromFile = "";
							try {
								oneTimeFromFile = values[timeColumn - 1]; // retrieve
								// one
								// time

								/*
								 * call checkTimeFormat method to check that one
								 * time retrieved from file is in proper format
								 * or not
								 */
								actualWorkHrs = checkTimeFormat(bean,
										getHdrData, oneTimeFromFile);
							} catch (Exception e) {
							}
						}

						if (actualWorkHrs.equals("") && workHrsColumn != -1) {
							String workHrsFromFile = "";
							try {
								workHrsFromFile = values[workHrsColumn - 1]; // retrieve
								// work
								// hours
								/*
								 * call checkTimeFormat method to check that
								 * work hours retrieved from file is in proper
								 * format or not
								 */
								actualWorkHrs = checkTimeFormat(bean,
										getHdrData, workHrsFromFile);
							} catch (Exception e) {
							}
						}
					}
					if (!actualDate.equals("")) {
						validDate = "valid";
					}
					if (!actualWorkHrs.equals("")) {
						validTime = "valid";
					}
					if (!actualWorkHrs.equals("") && !actualDate.equals("")) {
						/*
						 * call compareDates method to check the whether the
						 * date retrieved from file falls between from date and
						 * to date specified in the form
						 */
						boolean result = compareDates(bean, actualDate);

						if (result) {
							no_match++;

							if (empCodeColumn != -1) {
								empCode = employeeCode; // employee code
							}
							if (inTimeColumn != -1) {
								try {
									inTime = values[inTimeColumn - 1]; // in
									// time
									// from
									// file
								} catch (Exception e) {
								}
							}

							if (outTimeColumn != -1) {
								try {
									outTime = values[outTimeColumn - 1]; // out
									// time
									// from
									// file
								} catch (Exception e) {
								}
							}

							if (timeColumn != -1) {
								try {
									oneTime = values[timeColumn - 1]; // one
									// time
									// from
									// file
								} catch (Exception e) {
								}
							}

							if (ioFlagColumn != -1) {
								try {
									ioFlag = values[ioFlagColumn - 1]; // io
									// flag
									// from
									// file
								} catch (Exception e) {
								}
							}

							if (workHrsColumn != -1) {
								try {
									workHrs = values[workHrsColumn - 1]; // work
									// hours
									// from
									// file
								} catch (Exception e) {
								}
							}

							if (shiftColumn != -1) {
								try {
									shift = values[shiftColumn - 1]; // shift
									// from
									// file
								} catch (Exception e) {
								}
							}

							date = actualDate; // attendance date

							/*
							 * store the values of all the file parameters in an
							 * object array
							 */
							attendanceData[i][0] = empCode; // employee code
							attendanceData[i][1] = empName; // employee name
							attendanceData[i][2] = date; // attendance date
							attendanceData[i][3] = inTime; // in time
							attendanceData[i][4] = outTime; // out time
							attendanceData[i][5] = workHrs; // work hours
							attendanceData[i][6] = oneTime; // one time
							attendanceData[i][7] = ioFlag; // io flag
							attendanceData[i][8] = shift; // shift

							j++;
						}
					}
				}

				try {
					/* Read next line from File */
					line = br.readLine().replaceAll(String.valueOf(s), "");
					i++;
				} catch (Exception e) {
					line = null;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error(e);
			Object[][] blankData = { { "noMatch" } }; // settings not defined
			// properly
			return blankData;
		} catch (Exception e) {
			logger.error(e);
		}

		if ((validDate.equals("valid") && validTime.equals("valid")) && j == 0
				&& no_match == 0) {
			/* no data between from date and to date */
			Object[][] blankData = { { "noDataBetweenDates" } };
			return blankData;
		} else if ((validDate.equals("valid") || validTime.equals("valid"))
				&& j == 0) {
			Object[][] blankData = { { "noMatch" } }; // settings not defined
			// properly
			return blankData;
		} else if (j == 0) {
			Object[][] blankData = { { "blank" } }; // no data in the file
			return blankData;
		}

		return attendanceData;
	}

	/**
	 * @Method Name : readFileData()
	 * @purpose to read the uploaded file data and then insert these data to the
	 *          related table
	 * @param bean
	 * @return String
	 */
	public String readFileData(UploadAttendance bean) {
		Object[][] attendanceData = null;
		String result = "";

		String fileName = bean.getUploadFileName();

		int fileLength = fileName.length();

		// String fileExtension = fileName.substring(fileLength - 3,
		// fileLength);
		String fileExtension = fileName.substring(
				(fileName.lastIndexOf(".") + 1), fileLength);
		try {
			String filePath = context.getRealPath("/") + "pages/images/"
					+ session.getAttribute("session_pool") + "/attendance/"
					+ bean.getUploadFileName();
			// executing query to check whether configuration already exists or
			// not
			Object[][] getHdrData = isConfigurationExist(bean, fileExtension);
			if (getHdrData != null && getHdrData.length != 0) {
				if (getHdrData[0][0].equals("wrong extension")) {
					return "wrong extension";
				}

				// if configuration exists then retrieve the configuration data
				Object[][] getDtlData = getConfigurationData(bean, getHdrData);

				if (getDtlData != null && getDtlData.length != 0) {
					// set the configuration data to respective flags
					bean.setEmpIdFlag(String.valueOf(getDtlData[0][1])); // empIdFlag
					bean.setDateFlag(String.valueOf(getDtlData[1][1])); // dateFlag
					bean.setInTimeFlag(String.valueOf(getDtlData[2][1])); // inTimeFlag
					bean.setOutTimeFlag(String.valueOf(getDtlData[3][1])); // outTimeFlag
					bean.setOneTimeFlag(String.valueOf(getDtlData[4][1])); // oneTimeFlag
					bean.setIoFlag(String.valueOf(getDtlData[5][1])); // ioFlag
					bean.setWorkHrsFlag(String.valueOf(getDtlData[6][1])); // workHrsFlag
					bean.setShiftFlag(String.valueOf(getDtlData[7][1])); // shiftFlag

					/**
					 * Call to specific readXXXFile method according to file
					 * extension which returns a two dimensional array of file
					 * data.
					 */
					// for XLS file
					if (fileExtension.equals("xls")
							|| fileExtension.equals("xlsx")) {
						attendanceData = readXlsFile(bean, filePath,
								getHdrData, getDtlData);
					}

					// for TXT file
					else if (fileExtension.equals("txt")) {
						attendanceData = readTxtFile(bean, filePath,
								getHdrData, getDtlData);
					}

					// for CSV file
					else if (fileExtension.equals("csv")) {
						attendanceData = readCsvFile(bean, filePath,
								getHdrData, getDtlData);
					}

					/*
					 * Delete the file after reading
					 * 
					 */

					/**
					 * further attendance data processing
					 * 
					 */
					if (attendanceData != null && attendanceData.length != 0) {
						if (attendanceData[0][0] != null
								&& attendanceData[0][0].equals("blank")) {
							bean.setNoDataFlag("false");
							result = "blank"; // no data in the file
						} else if (attendanceData[0][0] != null
								&& attendanceData[0][0].equals("noMatch")) {
							bean.setNoDataFlag("false");
							result = "error"; // settings not defined properly
						} else if (attendanceData[0][0] != null
								&& attendanceData[0][0]
										.equals("noDataBetweenDates")) {
							bean.setNoDataFlag("false");
							result = "noDataBetweenDates";
						} else if (attendanceData[0][0] != null
								&& attendanceData[0][0]
										.equals("shiftNotDefined")) {
							bean.setNoDataFlag("false");
							result = "shiftNotDefined";
						} else if (attendanceData[0][0] != null
								&& attendanceData[0][0]
										.equals("shiftNotPresent")) {
							bean.setNoDataFlag("false");
							result = "shiftNotPresent";
						} else {							
							/**
							 * Insert data into Temporary tables
							 * 
							 */
							result = insertData(bean, attendanceData, bean
									.getFromDate(), bean.getToDate(),
									fileExtension, String
											.valueOf(getHdrData[0][3]), String
											.valueOf(getHdrData[0][4]));

							if (result.equals("uploaded")) {
								// result = processAttendance(bean,
								// attendanceData);
								String fromDate = bean.getFromDate();
								String toDate = bean.getToDate();

								String fldShift = "";
								String fldWorkHrs = "";
								String fldOneTime = "";

								if (bean.getShiftFlag().equals("true")) {
									fldShift = "true";
								}

								if (bean.getOneTimeFlag().equals("true")) {
									fldOneTime = "true";
								}

								if (bean.getWorkHrsFlag().equals("true")) {
									fldWorkHrs = "true";
								}

								String div = bean.getDivCode();
								String branch = bean.getBranchCode();

								/* calling processAttendance() */
								result = processAttendance(attendanceData,
										fromDate, toDate, fldShift, fldOneTime,
										fldWorkHrs, "uploadSheet", div, branch);
							}
						}
					} else {
						bean.setNoDataFlag("false");
						result = "error"; // settings not defined properly
					}
				} else {
					result = "not defined"; // settings are not defined for
					// given branch, division and file
					// type
				}
			} else {
				result = "not defined"; // settings are not defined for given
				// branch, division and file type
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * @Method Name readTxtFile()
	 * @purpose to read the data from the txt file
	 * @param bean
	 * @param filePath
	 * @param getHdrData
	 * @param getDtlData
	 * @return Object array
	 */
	public Object[][] readTxtFile(UploadAttendance bean, String filePath,
			Object[][] getHdrData, Object[][] getDtlData) {
		Object[][] attendanceData = null;
		int j = 0;
		int no_match = 0;
		String validDate = "";
		String validTime = "";

		int empCodeCharFrom = returnFieldValue(getDtlData[0][2]); // starting
		// index for
		// employee
		// code
		int empCodeCharTO = returnFieldValue(getDtlData[0][3]); // last index
		// for employee
		// code

		int dateCharFrom = returnFieldValue(getDtlData[1][2]); // starting
		// index for
		// attendance
		// date
		int dateCharTo = returnFieldValue(getDtlData[1][3]); // last index
		// for
		// attendance
		// date

		int inTimeCharFrom = returnFieldValue(getDtlData[2][2]); // starting
		// index for
		// in time
		int inTimeCharTo = returnFieldValue(getDtlData[2][3]); // last index
		// for in time

		int outTimeCharFrom = returnFieldValue(getDtlData[3][2]); // starting
		// index for
		// out time
		int outTimeCharTo = returnFieldValue(getDtlData[3][3]); // last index
		// for out time

		int oneTimeCharFrom = returnFieldValue(getDtlData[4][2]); // starting
		// index for
		// one time
		int oneTimeCharTo = returnFieldValue(getDtlData[4][3]); // last index
		// for one time

		int ioFlagCharFrom = returnFieldValue(getDtlData[5][2]); // starting
		// index for
		// io flag
		int ioFlagCharTo = returnFieldValue(getDtlData[5][3]); // last index
		// for io flag

		int workHrsCharFrom = returnFieldValue(getDtlData[6][2]); // starting
		// index for
		// work
		// hours
		int workHrsCharTo = returnFieldValue(getDtlData[6][3]); // last index
		// for work
		// hours

		int shiftCharFrom = returnFieldValue(getDtlData[7][2]); // starting
		// index for
		// shift
		int shiftCharTo = returnFieldValue(getDtlData[7][3]); // last index
		// for shift

		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine(); // read first line from the file

			// call getNumberOfLines method to find the number of lines present
			// in the file
			int numberOfLines = getNumberOfLines(filePath);

			int i = 1;
			String employeeCode = "";
			attendanceData = new Object[numberOfLines + 1][9];

			while (line != null) {
				if (line.length() != 0 && !line.trim().equals("")) {
					String actualWorkHrs = "";
					String actualDate = "";

					String empCode = ""; // employee code
					String empName = ""; // employee name
					String date = ""; // attendance date
					String inTime = ""; // in time
					String outTime = ""; // out time
					String oneTime = ""; // one time
					String ioFlag = ""; // io flag
					String workHrs = ""; // work hours
					String shift = ""; // shift

					try {
						if (dateCharFrom != -1 && dateCharTo != -1) {
							String emp_Code = "";

							String dateFromFile = line.substring(
									dateCharFrom - 1, dateCharTo).trim(); // retrieve
							// attendance
							// date

							/*
							 * call checkDateFormat method to check that
							 * attendance date retrieved from the file is in
							 * proper format or not
							 */
							actualDate = checkDateFormat(dateFromFile, String
									.valueOf(getHdrData[0][3]));

							if (empCodeCharFrom != -1 && empCodeCharTO != -1) {
								emp_Code = line.substring(empCodeCharFrom - 1,
										empCodeCharTO).trim(); // retrieve
								// employee code
							}

							if (!emp_Code.trim().equals("")) {
								employeeCode = emp_Code; // if employee code
								// retrieved form
								// the file is blank
								// or null, then set
								// the
								// previous employee code
							}
						}
					} catch (StringIndexOutOfBoundsException e) {
						logger.error(e);
					}

					if (inTimeCharFrom != -1 && inTimeCharTo != -1
							&& !bean.getOneTimeFlag().equals("true")) {
						try {
							String inTimeFromFile = line.substring(
									inTimeCharFrom - 1, inTimeCharTo).trim(); // retrieve
							// in
							// time

							/*
							 * call checkTimeFormat method to check that in time
							 * retrieved from the file is in proper format or
							 * not which is defined in Attendance Settings form
							 */
							actualWorkHrs = checkTimeFormat(bean, getHdrData,
									inTimeFromFile);
						} catch (StringIndexOutOfBoundsException e) {
							logger.error(e);
						}
					}

					if (actualWorkHrs.equals("") && outTimeCharFrom != -1
							&& outTimeCharTo != -1
							&& !bean.getOneTimeFlag().equals("true")) {
						try {
							String outTimeFromFile = line.substring(
									outTimeCharFrom - 1, outTimeCharTo).trim(); // retrieve
							// out
							// time

							/*
							 * call checkTimeFormat method to check that out
							 * time retrieved from the file is in proper format
							 * or not which is defined in Attendance Settings
							 * form
							 */
							actualWorkHrs = checkTimeFormat(bean, getHdrData,
									outTimeFromFile);
						} catch (StringIndexOutOfBoundsException e) {
							logger.error(e);
						}
					}

					if (oneTimeCharFrom != -1 && oneTimeCharTo != -1
							&& bean.getOneTimeFlag().equals("true")) {
						try {
							String oneTimeFromFile = line.substring(
									oneTimeCharFrom - 1, oneTimeCharTo).trim(); // retrieve
							// one
							// time

							/*
							 * call checkTimeFormat method to check that one
							 * time retrieved from the file is in proper format
							 * or not which is defined in Attendance Settings
							 * form
							 */
							actualWorkHrs = checkTimeFormat(bean, getHdrData,
									oneTimeFromFile);
						} catch (StringIndexOutOfBoundsException e) {
							logger.error(e);
						}
					}

					if (actualWorkHrs.equals("") && workHrsCharFrom != -1
							&& workHrsCharTo != -1) {
						try {
							String workHrsFromFile = line.substring(
									workHrsCharFrom - 1, workHrsCharTo).trim(); // retrieve
							// work
							// hours

							/*
							 * call checkTimeFormat method to check that work
							 * hours retrieved from the file is in proper format
							 * or not which is defined in Attendance Settings
							 * form
							 */
							actualWorkHrs = checkTimeFormat(bean, getHdrData,
									workHrsFromFile);
						} catch (StringIndexOutOfBoundsException e) {
							logger.error(e);
						}
					}

					if (!actualDate.equals("")) {
						validDate = "valid";
					}

					if (!actualWorkHrs.equals("")) {
						validTime = "valid";
					}

					if (!actualWorkHrs.equals("") && !actualDate.equals("")) { // if
						// attendance
						// date
						// and
						// time
						// are
						// in
						// proper
						// format

						/*
						 * call compareDates method to check the whether the
						 * date retrieved from file falls between from date and
						 * to date specified in the form
						 */
						boolean result = compareDates(bean, actualDate);

						if (result) {
							no_match++;

							try {
								empCode = employeeCode;
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							try {
								if (inTimeCharFrom != -1 && inTimeCharTo != -1) {
									inTime = line.substring(inTimeCharFrom - 1,
											inTimeCharTo).trim(); // retrieve
									// in time
									// from file
								}
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							try {
								if (outTimeCharFrom != -1
										&& outTimeCharTo != -1) {
									outTime = line.substring(
											outTimeCharFrom - 1, outTimeCharTo)
											.trim(); // retrieve out time
									// from file
								}
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							try {
								if (oneTimeCharFrom != -1
										&& oneTimeCharTo != -1) {
									oneTime = line.substring(
											oneTimeCharFrom - 1, oneTimeCharTo)
											.trim(); // retrieve one time
									// from file
								}
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							try {
								if (ioFlagCharFrom != -1 && ioFlagCharTo != -1) {
									ioFlag = line.substring(ioFlagCharFrom - 1,
											ioFlagCharTo).trim(); // retrieve
									// io flag
									// from file
								}
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							try {
								if (shiftCharFrom != -1 && shiftCharTo != -1) {
									shift = line.substring(shiftCharFrom - 1,
											shiftCharTo).trim(); // retrieve
									// shift
									// from file
								}
							} catch (StringIndexOutOfBoundsException e) {
								logger.error(e);
							}

							if (workHrsCharFrom != -1 && workHrsCharTo != -1) {
								try {
									workHrs = line.substring(
											workHrsCharFrom - 1, workHrsCharTo)
											.trim(); // retrieve work hours
									// from file
								} catch (StringIndexOutOfBoundsException e) {
									logger.error(e);
								}
							}

							date = actualDate;

							/*
							 * store the values of all file parameters in an
							 * object array
							 */
							attendanceData[i][0] = empCode; // employee code
							attendanceData[i][1] = empName; // employee name
							attendanceData[i][2] = date; // attendance date
							attendanceData[i][3] = inTime; // in time
							attendanceData[i][4] = outTime; // out time
							attendanceData[i][5] = workHrs; // work hours
							attendanceData[i][6] = oneTime; // one time
							attendanceData[i][7] = ioFlag; // io flag
							attendanceData[i][8] = shift; // shift

							j++;
						}
					}
				}

				line = br.readLine(); // read lines one by one from file
				i++;

			}
		} catch (Exception e) {
			logger.error(e);
		}

		if ((validDate.equals("valid") && validTime.equals("valid")) && j == 0
				&& no_match == 0) {
			Object[][] blankData = { { "noDataBetweenDates" } }; // no data
			// between
			// specified
			// from date
			// and to
			// date
			return blankData;
		} // end of if

		else if ((validDate.equals("valid") || validTime.equals("valid"))
				&& j == 0) {
			Object[][] blankData = { { "noMatch" } }; // settings are not
			// defined properly
			return blankData;
		} // end of else if

		else if (j == 0) {
			Object[][] blankData = { { "blank" } }; // no data in the file
			return blankData;
		} // end of else if

		return attendanceData;
	}

	/**
	 * @method readXlsFile
	 * @purpose to read the data from the xls file
	 * @param bean
	 * @param filePath
	 * @param getHdrData
	 * @param getDtlData
	 * @return Object array
	 */
	public Object[][] readXlsFile(UploadAttendance bean, String filePath,
			Object[][] getHdrData, Object[][] getDtlData) {
		Object[][] attendanceData = null;
		int j = 0;
		int no_match = 0;
		String readData = "";
		String validTime = "";

		try {
			InputStream myFile = new FileInputStream(filePath);
			HSSFWorkbook workBook = new HSSFWorkbook(myFile);
			HSSFSheet sheet = null;

			int sheetNumber = 1; // sheet number

			if (!String.valueOf(getHdrData[0][2]).equals("")) {
				sheetNumber = Integer
						.parseInt(String.valueOf(getHdrData[0][2])); // parse
				// string
				// value
				// of
				// sheet
				// number
				// in to
				// Integer
				// value
			}

			try {
				sheet = workBook.getSheetAt(sheetNumber - 1); // retrieve the
				// sheet from
				// the xls file
			} catch (Exception e) {
				logger.error(e);
				Object[][] blankData = { { "noMatch" } };
				return blankData;
			}

			int empCode = returnFieldValue(getDtlData[0][4]); // employee code
			int date = returnFieldValue(getDtlData[1][4]); // attendance date
			int inTime = returnFieldValue(getDtlData[2][4]); // employee in
			// time
			int outTime = returnFieldValue(getDtlData[3][4]); // employee out
			// time
			int time = returnFieldValue(getDtlData[4][4]); // one time
			int ioFlag = returnFieldValue(getDtlData[5][4]); // io flag i.e.
			// in or out
			int workHrs = returnFieldValue(getDtlData[6][4]); // work hours
			int shift = returnFieldValue(getDtlData[7][4]); // shift

			int count = 0;
			int endOfFile = 0;
			String employeeCode = "";
			attendanceData = new Object[Integer.parseInt(String.valueOf(sheet
					.getLastRowNum())) + 1][9];
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell employeeNumValue = null;
				HSSFCell employeeNameValue = null;
				HSSFCell attDateValue = null;
				HSSFCell entryTimeValue = null;
				HSSFCell outGoingTimeValue = null;
				HSSFCell workingHrsValue = null;
				HSSFCell timeValue = null;
				HSSFCell ioFlagValue = null;
				HSSFCell shiftValue = null;

				if (row != null) {
					if (empCode != -1) {
						employeeNumValue = row.getCell((short) (empCode - 1)); // define
						// column
						// for
						// employee
						// Code
					}

					if (date != -1) {
						attDateValue = row.getCell((short) (date - 1)); // define
						// column
						// for
						// attendance
						// date
					}

					if (inTime != -1) {
						entryTimeValue = row.getCell((short) (inTime - 1)); // define
						// column
						// for
						// in
						// time
					}

					if (outTime != -1) {
						outGoingTimeValue = row.getCell((short) (outTime - 1)); // define
						// column
						// for
						// out
						// time
					}

					if (workHrs != -1) {
						workingHrsValue = row.getCell((short) (workHrs - 1)); // define
						// column
						// for
						// work
						// hours
					}

					if (time != -1) {
						timeValue = row.getCell((short) (time - 1)); // define
						// column
						// for
						// one
						// time
					}

					if (ioFlag != -1) {
						ioFlagValue = row.getCell((short) (ioFlag - 1)); // define
						// column
						// for
						// io
						// flag
					}

					if (shift != -1) {
						shiftValue = row.getCell((short) (shift - 1)); // define
						// column
						// for
						// shift
					}
					if (endOfFile > 25) { // if there are more than 25
						// consecutive blank rows in the
						// file, break the execution
						break;
					} else {
						String currDate = "";
						Object[] readTime = new Object[1];

						// calculation for date
						if (attDateValue != null) {
							try {

								if (attDateValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									String stringDate = attDateValue
											.getStringCellValue();// retrieve
																	// the
																	// attendance
																	// date from
																	// file
									String dateFormat = String
											.valueOf(getHdrData[0][3]);
									SimpleDateFormat currentFormat = new SimpleDateFormat(
											dateFormat);
									Date d = currentFormat.parse(stringDate);

									SimpleDateFormat newFormat = new SimpleDateFormat(
											"dd-MM-yyyy");
									if (d != null) {
										currDate = newFormat.format(d);
									}
								} else {
									Date d = attDateValue.getDateCellValue(); // retrieve
																				// the
																				// attendance
																				// date
																				// from
																				// file
									SimpleDateFormat sdf = new SimpleDateFormat(
											"dd-MM-yyyy");
									if (d != null) {
										currDate = sdf.format(d);
									}			
								}
							} catch (Exception e) {
								logger.error(e);
							}
						}
						// calculation for in time
						if (entryTimeValue != null
								&& !bean.getOneTimeFlag().equals("true")) {
							if (entryTimeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								readTime[0] = entryTimeValue
										.getNumericCellValue(); // retrieve in
								// time value as
								// numeric
							} else if (entryTimeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								readTime[0] = entryTimeValue
										.getStringCellValue(); // retrieve in
								// time value as
								// string
							}							
							try {
								/*
								 * call checkTimeFormat method to check that in
								 * time retrieved from the file is in proper
								 * format or not which is defined in Attendance
								 * Settings form
								 */
								validTime = checkTimeFormat(bean, getHdrData,
										String.valueOf(readTime[0]));
							} catch (Exception e) {
								logger.error(e);
							}
						}

						// calculation for out time
						if (validTime.equals("") && outGoingTimeValue != null
								&& !bean.getOneTimeFlag().equals("true")) {
							if (outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								readTime[0] = outGoingTimeValue
										.getNumericCellValue(); // retrieve out
								// time value as
								// numeric
							} else if (outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								readTime[0] = outGoingTimeValue
										.getStringCellValue(); // retrieve out
								// time value as
								// string
							}							
							try {
								/*
								 * call checkTimeFormat method to check that out
								 * time retrieved from the file is in proper
								 * format or not which is defined in Attendance
								 * Settings form
								 */
								validTime = checkTimeFormat(bean, getHdrData,
										String.valueOf(readTime[0]));
							} catch (Exception e) {
								logger.error(e);
							}
						}

						// calculation for one time
						if (timeValue != null
								&& bean.getOneTimeFlag().equals("true")) {
							if (timeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								readTime[0] = timeValue.getNumericCellValue(); // retrieve
								// one
								// time
								// value
								// as
								// numeric
							} else if (timeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								readTime[0] = timeValue.getStringCellValue(); // retrieve
								// one
								// time
								// value
								// as
								// string
							}

							try {
								/*
								 * call checkTimeFormat method to check that one
								 * time retrieved from the file is in proper
								 * format or not which is defined in Attendance
								 * Settings form
								 */
								validTime = checkTimeFormat(bean, getHdrData,
										String.valueOf(readTime[0]));
							} catch (Exception e) {
								logger.error(e);
							}
						}

						// calculation for working hrs
						if (validTime.equals("") && workingHrsValue != null) {
							try {
								if (workingHrsValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									readTime[0] = workingHrsValue
											.getNumericCellValue(); // retrieve
									// work
									// hours
									// value as
									// numeric
								} else if (workingHrsValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									readTime[0] = workingHrsValue
											.getStringCellValue(); // retrieve
									// work
									// hours
									// value as
									// string
								}

								/*
								 * call checkTimeFormat method to check that
								 * work hours retrieved from the file is in
								 * proper format or not which is defined in
								 * Attendance Settings form
								 */
								validTime = checkTimeFormat(bean, getHdrData,
										String.valueOf(readTime[0]));
							} catch (Exception e) {
								logger.error(e);
							}
						}

						Pattern pattern = Pattern
								.compile("[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}");
						Matcher matcher = pattern.matcher(currDate); // create
						// a
						// pattern
						// for
						// the
						// attendance
						// date

						if (matcher.matches()) { // if pattern for date
							// matches
							readData = "readData";
							Object emp_code = "";

							if (employeeNumValue != null) {
								if (employeeNumValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									emp_code = (int) employeeNumValue
											.getNumericCellValue(); // retrieve
									// employee
									// code as
									// numeric
								} else if (employeeNumValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									emp_code = employeeNumValue
											.getStringCellValue(); // retrieve
									// employee
									// code as
									// string
								}
							}

							if (!String.valueOf(emp_code).trim().equals("")) {
								// if employee code retrieved from file is null
								// or blank then set the previous value of
								// employee code
								employeeCode = String.valueOf(emp_code);
							}
						} else {
							readData = "";
						}
						// if(readData.equals("readData") &&
						// !validTime.equals("")){
						if (readData.equals("readData")) {
							/*
							 * call compareDates method to check the whether the
							 * date retrieved from file falls between from date
							 * and to date specified in the form
							 */
							boolean result = compareDates(bean, currDate);
							if (result) {
								no_match++;

								attendanceData[count][0] = employeeCode;

								if (employeeNameValue != null) {
									if (employeeNameValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][1] = employeeNameValue
												.getNumericCellValue(); // retrieve
										// employee
										// name
										// as
										// numeric
									} else if (employeeNameValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][1] = employeeNameValue
												.getStringCellValue(); // retrieve
										// employee
										// name
										// as
										// string
									}
								}

								if (currDate != null) {
									attendanceData[count][2] = currDate; // copy
									// attendance
									// date
									// in
									// to
									// the
									// object
									// array
								}

								if (entryTimeValue != null) {
									if (entryTimeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][3] = entryTimeValue
												.getNumericCellValue(); // retrieve
										// in
										// time
										// as
										// numeric
									} else if (entryTimeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][3] = entryTimeValue
												.getStringCellValue(); // retrieve
										// in
										// time
										// as
										// string
									}
								}

								if (outGoingTimeValue != null) {
									if (outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][4] = outGoingTimeValue
												.getNumericCellValue(); // retrieve
										// out
										// time
										// as
										// numeric
									} else if (outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][4] = outGoingTimeValue
												.getStringCellValue(); // retrieve
										// out
										// time
										// as
										// string
									}
								}

								if (workingHrsValue != null) {
									if (workingHrsValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][5] = workingHrsValue
												.getNumericCellValue(); // retrieve
										// work
										// hours
										// as
										// numeric
									} else if (workingHrsValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][5] = workingHrsValue
												.getStringCellValue(); // retrieve
										// work
										// hours
										// as
										// string
									}
								}

								if (timeValue != null) {
									if (timeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][6] = timeValue
												.getNumericCellValue(); // retrieve
										// one
										// time
										// as
										// numeric
									} else if (timeValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][6] = timeValue
												.getStringCellValue(); // retrieve
										// one
										// time
										// as
										// string
									}
								}

								if (ioFlagValue != null) {
									if (ioFlagValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][7] = ioFlagValue
												.getNumericCellValue(); // retrieve
										// io
										// flag
										// as
										// numeric
									} else if (ioFlagValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][7] = ioFlagValue
												.getStringCellValue(); // retrieve
										// io
										// flag
										// as
										// string
									}
								}

								if (shiftValue != null) {
									if (shiftValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
										attendanceData[count][8] = shiftValue
												.getNumericCellValue(); // retrieve
										// shift
										// as
										// numeric
									} else if (shiftValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										attendanceData[count][8] = shiftValue
												.getStringCellValue(); // retrieve
										// shift
										// as
										// string
									}
								}

								if (attendanceData[count][0] == null
										&& attendanceData[count][1] == null
										&& attendanceData[count][2] == null
										&& attendanceData[count][3] == null
										&& attendanceData[count][4] == null
										&& attendanceData[count][5] == null) {
									endOfFile++; // if blank row found in
									// file, increase the
									// endOfFile variable by one
								} else {
									j++;
								}
								count++;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

		if ((readData.equals("readData") && !validTime.equals("")) && j == 0
				&& no_match == 0) {
			Object[][] blankData = { { "noDataBetweenDates" } }; // no data
			// between
			// the
			// specified
			// dates
			return blankData;
		} else if ((readData.equals("readData") || !validTime.equals(""))
				&& j == 0) {
			Object[][] blankData = { { "noMatch" } }; // settings not defined
			// properly
			return blankData;
		} else if (j == 0) {
			Object[][] blankData = { { "blank" } }; // no data in the file
			return blankData;
		}
		// return org.paradyne.lib.Utility.removeNullRows(attendanceData);
		return attendanceData;
	}

	/**@Method Name : retrieveAttendance()
	 * @purpose : 
	 * @param driver
	 * @param server
	 * @param userName
	 * @param password
	 * @param database
	 * @param port
	 * @param tableName
	 * @param fromDateAuto
	 * @param toDateAuto
	 * @param fldEmpToken
	 * @param fldAttendanceDate
	 * @param fldInTime
	 * @param fldOutTime
	 * @param fldOneTime
	 * @param fldIOFlag
	 * @param fldWorkHrs
	 * @param fldShift
	 * @return Object[][]
	 */
	private Object[][] retrieveAttendance(String driver, String server,
			String userName, String password, String database, String port,
			String tableName, String fromDateAuto, String toDateAuto,
			String fldEmpToken, String fldAttendanceDate, String fldInTime,
			String fldOutTime, String fldOneTime, String fldIOFlag,
			String fldWorkHrs, String fldShift) {
		Object[][] retrievedAttendance = null;
		try {
			Connection conn = null;

			try {
				String driverClass = "", url = "";
				String sqlQuery = "SELECT ";

				if (driver.equals("ORACLE")) {
					if (!fldEmpToken.equals("")) {
						sqlQuery += fldEmpToken + ", ";
					}
					if (!fldInTime.equals("")) {
						sqlQuery += "TO_CHAR(" + fldInTime
								+ ", 'HH24:MI:SS') AS " + fldInTime + ", ";
					}
					if (!fldOutTime.equals("")) {
						sqlQuery += "TO_CHAR(" + fldOutTime
								+ ", 'HH24:MI:SS') AS " + fldOutTime + ", ";
					}
					if (!fldOneTime.equals("")) {
						sqlQuery += "TO_CHAR(" + fldOneTime
								+ ", 'HH24:MI:SS') AS " + fldOneTime + ", ";
					}
					if (!fldIOFlag.equals("")) {
						sqlQuery += fldIOFlag + ", ";
					}
					if (!fldWorkHrs.equals("")) {
						sqlQuery += "TO_CHAR(" + fldWorkHrs
								+ ", 'HH24:MI:SS') AS " + fldWorkHrs + ", ";
					}
					if (!fldShift.equals("")) {
						sqlQuery += fldShift + ", ";
					}
					if (!fldAttendanceDate.equals("")) {
						sqlQuery += "TO_CHAR(" + fldAttendanceDate
								+ ", 'DD-MM-YYYY') AS " + fldAttendanceDate;
					}

					sqlQuery += " FROM " + tableName + " WHERE "
							+ fldAttendanceDate + " BETWEEN TO_DATE('"
							+ fromDateAuto + "', 'DD-MM-YYYY')"
							+ " AND TO_DATE('" + toDateAuto
							+ "', 'DD-MM-YYYY')";

					driverClass = "oracle.jdbc.OracleDriver";
					url = "jdbc:oracle:thin:@" + server + ":" + port + ":"
							+ database;

					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url,
								userName, password);
						logger.info("Oracle Connected.............");
					} catch (Exception e) {
						logger
								.error("Exception in creating connection object for Oracle:"
										+ e);
					}
				} else if (driver.equals("MSSQL")) {
					if (!fldEmpToken.equals("")) {
						sqlQuery += fldEmpToken + ", ";
					}
					if (!fldInTime.equals("")) {
						sqlQuery += "CONVERT(VARCHAR(8), " + fldInTime
								+ ", 108) AS " + fldInTime + ", ";
					}
					if (!fldOutTime.equals("")) {
						sqlQuery += "CONVERT(VARCHAR(8), " + fldOutTime
								+ ", 108) AS " + fldOutTime + ", ";
					}
					if (!fldOneTime.equals("")) {
						sqlQuery += "CONVERT(VARCHAR(8), " + fldOneTime
								+ ", 108) AS " + fldOneTime + ", ";
					}
					if (!fldIOFlag.equals("")) {
						sqlQuery += fldIOFlag + ", ";
					}
					if (!fldWorkHrs.equals("")) {
						sqlQuery += "CONVERT(VARCHAR(8), " + fldWorkHrs
								+ ", 108) AS " + fldWorkHrs + ", ";
					}
					if (!fldShift.equals("")) {
						sqlQuery += fldShift + ", ";
					}
					if (!fldAttendanceDate.equals("")) {
						sqlQuery += "CONVERT(VARCHAR(10), " + fldAttendanceDate
								+ ", 105) AS " + fldAttendanceDate;
					}

					sqlQuery += " FROM " + tableName + " WHERE "
							+ fldAttendanceDate
							+ " BETWEEN CONVERT(DATETIME, '" + fromDateAuto
							+ "', 105)" + " AND CONVERT(DATETIME, '"
							+ toDateAuto + "', 105)";

					driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					url = "jdbc:sqlserver://" + server + ";databaseName="
							+ database + ";user=" + userName + ";password="
							+ password + ";";

					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						logger.info("SQL Server Connected.............");
					} catch (Exception e) {
						logger
								.error("Exception in creating connection object for MS SQL :"
										+ e);
					}
				} else if (driver.equals("MYSQL")) {
					if (!fldEmpToken.equals("")) {
						sqlQuery += fldEmpToken + ", ";
					}
					if (!fldInTime.equals("")) {
						sqlQuery += "TIME_FORMAT(" + fldInTime
								+ ", '%H-%i-%S') AS " + fldInTime + ", ";
					}
					if (!fldOutTime.equals("")) {
						sqlQuery += "TIME_FORMAT(" + fldOutTime
								+ ", '%H-%i-%S') AS " + fldOutTime + ", ";
					}
					if (!fldOneTime.equals("")) {
						sqlQuery += "TIME_FORMAT(" + fldOneTime
								+ ", '%H-%i-%S') AS " + fldOneTime + ", ";
					}
					if (!fldIOFlag.equals("")) {
						sqlQuery += fldIOFlag + ", ";
					}
					if (!fldWorkHrs.equals("")) {
						sqlQuery += "TIME_FORMAT(" + fldWorkHrs
								+ ", '%H-%i-%S') AS " + fldWorkHrs + ", ";
					}
					if (!fldShift.equals("")) {
						sqlQuery += fldShift + ", ";
					}
					if (!fldAttendanceDate.equals("")) {
						sqlQuery += "DATE_FORMAT(" + fldAttendanceDate
								+ ", '%d-%m-%Y') AS " + fldAttendanceDate;
					}

					sqlQuery += " FROM " + tableName + " WHERE "
							+ fldAttendanceDate + " BETWEEN STR_TO_DATE('"
							+ fromDateAuto + "', '%d-%m-%Y') "
							+ " AND STR_TO_DATE('" + toDateAuto
							+ "', '%d-%m-%Y')";

					driverClass = "com.mysql.jdbc.Driver";
					url = "jdbc:mysql://" + server + ":" + port + "/"
							+ database + "?user=" + userName + "&password="
							+ password;

					try {
						Class.forName(driverClass);
						conn = java.sql.DriverManager.getConnection(url);
						logger.info("My SQL Connected.............");
					} catch (Exception e) {
						logger
								.error("Exception in creating connection object for My SQL:"
										+ e);
					}
				}

				PreparedStatement pst = null;
				ResultSet rs = null;

				try {
					logger.info("query on remote server:" + sqlQuery);
					pst = conn.prepareStatement(sqlQuery,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = pst.executeQuery();

					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();

					rs.last();
					retrievedAttendance = new Object[rs.getRow()][numberOfColumns];
					rs.beforeFirst();

					columnNames = new ArrayList<String>(numberOfColumns);

					for (int colNo = 0; colNo < numberOfColumns; colNo++) {
						columnNames.add(rsmd.getColumnName(colNo + 1));
					}

					int rowNumber = 0;

					while (rs.next()) {
						for (int i = 0; i < numberOfColumns; i++) {
							retrievedAttendance[rowNumber][i] = rs
									.getObject(i + 1);
						}
						rowNumber++;
					}
				} catch (Exception e) {
					try {
						rs.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						pst.clearParameters();
						pst.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			} catch (Exception e) {
				if (conn != null) {
					conn.close();
				}
			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception e) {
			logger.error("Connection Not Esablished..." + e);
		}

		return retrievedAttendance;
	}

	/**
	 * @method returnFieldValue
	 * @purpose to check the configuration values whether they are null or not
	 * @param fieldValue
	 * @return int
	 */
	public int returnFieldValue(Object fieldValue) {
		String stringValue = fieldValue.toString().trim();
		int value = -1;

		if (stringValue.equals("") || stringValue.equals(null)) {
		} else {
			value = Integer.parseInt(String.valueOf(fieldValue));
		}
		return value;
	}

	/**Method Name: uploadDataAutomatically()
	 * @purpose : Upload  Attendance data through Server  
	 * @param fromDateAuto
	 * @param toDateAuto
	 * @param autoUploadId
	 * @return String
	 */
	public String uploadDataAutomatically(String fromDateAuto,
			String toDateAuto, String autoUploadId) {
		String message = "Attendance cannot be uploaded";
		try {
			Object[][] autoUploadSettings = getSqlModel().getSingleResult(
					getQuery(16), new Object[] { autoUploadId });

			if (autoUploadSettings != null && autoUploadSettings.length > 0) {
				Object[][] autoUploadDtlSettings = getSqlModel()
						.getSingleResult(getQuery(17),
								new Object[] { autoUploadId });

				if (autoUploadDtlSettings != null
						&& autoUploadDtlSettings.length > 0) {
					String driver = String.valueOf(autoUploadSettings[0][0]);
					String server = String.valueOf(autoUploadSettings[0][1]);
					String userName = String.valueOf(autoUploadSettings[0][2]);
					String password = PPEncrypter.decrypt(String
							.valueOf(autoUploadSettings[0][3]));
					String database = String.valueOf(autoUploadSettings[0][4]);
					String port = String.valueOf(autoUploadSettings[0][5]);
					String tableName = String.valueOf(autoUploadSettings[0][6]);

					String fldEmpToken = "", fldAttendanceDate = "", fldInTime = "", fldOutTime = "", fldOneTime = "", fldIOFlag = "";
					String fldWorkHrs = "", fldShift = "";

					for (int j = 0; j < autoUploadDtlSettings.length; j++) {
						String fieldType = String.valueOf(
								autoUploadDtlSettings[j][0])
								.replaceAll(" ", "");
						boolean flag = Boolean.parseBoolean(String
								.valueOf(autoUploadDtlSettings[j][1]));

						if (flag) {
							String field = String
									.valueOf(autoUploadDtlSettings[j][2]);

							if (fieldType.equals("EmployeeNumber")) {
								fldEmpToken = field;

							} else if (fieldType.equals("Date")) {
								fldAttendanceDate = field;

							} else if (fieldType.equals("InTime")) {
								fldInTime = field;

							} else if (fieldType.equals("OutTime")) {
								fldOutTime = field;

							} else if (fieldType.equals("Time")) {
								fldOneTime = field;

							} else if (fieldType.equals("I/OFlag")) {
								fldIOFlag = field;

							} else if (fieldType.equals("WorkHours")) {
								fldWorkHrs = field;
							} else if (fieldType.equals("Shift")) {
								fldShift = field;

							}
						}
					}

					Object[][] retrievedAttendance = retrieveAttendance(driver,
							server, userName, password, database, port,
							tableName, fromDateAuto, toDateAuto, fldEmpToken,
							fldAttendanceDate, fldInTime, fldOutTime,
							fldOneTime, fldIOFlag, fldWorkHrs, fldShift);

					if (retrievedAttendance != null
							&& retrievedAttendance.length > 0) {
						Object[][] attendanceData = copyRetrievedAttendance(
								retrievedAttendance, fldEmpToken,
								fldAttendanceDate, fldInTime, fldOutTime,
								fldOneTime, fldIOFlag, fldWorkHrs, fldShift);

						if (attendanceData != null && attendanceData.length > 0) {
							String result = insertData(attendanceData,
									fromDateAuto, toDateAuto);
							if (result == "uploaded") {
								result = processAttendance(attendanceData,
										fromDateAuto, toDateAuto, fldShift,
										fldOneTime, fldWorkHrs, "autoUpload",
										"", "");

								// result =
								// processAttendance(bean,attendanceData);

								if (result.equals("inserted")) {
									message = "Attendance uploaded successfully.";
								}
							}
						}
					} else {
						message = "Attendance not exists.";
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadDataAutomatically:" + e);
			e.printStackTrace();
		}
		return message;
	}
}