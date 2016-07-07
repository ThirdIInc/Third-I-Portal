package org.paradyne.model.attendance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.attendance.OutdoorApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.TimeUtility;

/**
 * @author ritac & saipavan v 04-06-2008
 */
public class OutdoorApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OutdoorApprovalModel.class);

	public void add(OutdoorApproval bean, String statusToAppend) { // adding
		// the
		// records
		String status = bean.getOutAppStatus();
		Object[][] obj = new Object[1][2];
		obj[0][0] = bean.getOutAppStatus();
		obj[0][1] = bean.getUserEmpId();
		String newStatusToAppend = "";
		if (statusToAppend.equals("P")) {
			newStatusToAppend = "'P','F'";
		} else if (statusToAppend.equals("A")) {
			newStatusToAppend = "'A'";
		} else if (statusToAppend.equals("R")) {
			newStatusToAppend = "'R'";
		}
		String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'),"
				+ " OUTDOORVISIT_STATUS, OUTDOORVISIT_APPR, OUTDOORVISIT_ECODE, OUTDOORVISIT_CODE, OUTDOOR_APP_LEVEL, OUTDOORVISIT_LOCATION "
				+ " FROM HRMS_OUTDOORVISIT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
				+ " WHERE OUTDOORVISIT_STATUS IN ("
				+ newStatusToAppend
				+ ") AND (OUTDOORVISIT_APPR = "
				+ obj[0][1]
				+ " OR "
				+ " OUTDOOR_ALTER_APPROVER = "
				+ obj[0][1]
				+ " ) ORDER BY OUTDOORVISIT_DATE DESC";

		String pathQuery = " SELECT OUTDOOR_PATH_PURPOSE FROM HRMS_OUTDOOR_PATH WHERE OUTDOOR_PATH_APPRCODE = "
				+ bean.getUserEmpId() + " AND OUTDOOR_PATH_OUTCODE = ?";

		try {
			Object[][] data = getSqlModel().getSingleResult(query);

			ArrayList<Object> list = new ArrayList<Object>();
			bean.setOutAppStatus(bean.getOutAppStatus());

			if (data != null && data.length != 0) {
				for (int i = 0; i < data.length; i++) {
					OutdoorApproval bean1 = new OutdoorApproval();
					bean1.setEtoken(String.valueOf(data[i][0]));
					bean1.setEname(String.valueOf(data[i][1]));
					bean1.setOutDate(String.valueOf(data[i][2]));
					bean1.setCheckStatus(String.valueOf(data[i][3]));
					bean1.setEApprCode(String.valueOf(data[i][4]));
					bean1.setEcode(String.valueOf(data[i][5]));
					bean1.setOutVisitcode(String.valueOf(data[i][6]));
					bean1.setLevel(String.valueOf(data[i][7]));
					bean1.setOutLoc(String.valueOf(data[i][8]));

					if (!status.equals("P")) {
						Object[][] data1 = getSqlModel().getSingleResult(
								pathQuery,
								new Object[] { String.valueOf((data[i][6])) });
						if (data1.length == 0
								|| String.valueOf(data1[0][0]).equals("null")) {
							bean1.setPurpose("");
						} else {
							bean1.setPurpose(checkNull(String
									.valueOf(data1[0][0])));
						}
					}
					list.add(bean1);
					bean.setNoData("false");
				} // end of for loop...!
			} else if (data.length == 0) {
				bean.setNoData("true");
			}
			bean.setList(list);
		} catch (Exception e) {
			logger.error(e);
		}// end of try catch block
	}

	public void attendanceUpdate(String outCode) {
		try {
			String outdoorQuery = " SELECT OUTDOORVISIT_ECODE, TO_CHAR(OUTDOORVISIT_DATE, 'DD-MM-YYYY'), TO_CHAR(TO_DATE(OUTDOORVISIT_FRTIME, 'HH24:MI:SS'), 'HH24:MI:SS'), "
					+ " TO_CHAR(TO_DATE(OUTDOORVISIT_TOTIME, 'HH24:MI:SS'), 'HH24:MI:SS'), TO_CHAR(OUTDOORVISIT_DATE,'YYYY') YEAR "
					+ " FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = "
					+ String.valueOf(outCode)
					+ " AND OUTDOORVISIT_STATUS = 'A'";
			Object[][] outDoorVisitDtl = getSqlModel().getSingleResult(
					outdoorQuery);

			if (outDoorVisitDtl != null && outDoorVisitDtl.length != 0) {
				String attendanceQuery = " SELECT NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI:SS'),'00:00:00'), NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI:SS'),'00:00:00'), "
						+ " TO_CHAR(ATT_WORKING_HRS, 'HH24:MI:SS'), NVL(TO_CHAR(ATT_EXTRAHRS, 'HH24:MI:SS'),'00:00:00'), "
						+ " NVL(TO_CHAR(ATT_LATE_HRS, 'HH24:MI:SS'),'00:00:00'), NVL(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI:SS'),'00:00:00'),"
						+ " ATT_STATUS_TWO, NVL(TO_CHAR(SFT_START_TIME, 'HH24:MI:SS'),'00:00:00'), NVL(TO_CHAR(SFT_END_TIME, 'HH24:MI:SS'),'00:00:00'), "
						+ " NVL(TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'), '00:00:00'), NVL(TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00'), "
						+ " ATT_STATUS_ONE , ATT_SHIFT_ID, SFT_FLEXITIME_ALLOWED, TO_CHAR(SFT_WORK_HRS, 'HH24:MI:SS'), "
						+ " TO_CHAR(SFT_FLEXI_LATE_MARK , 'HH24:MI:SS'), TO_CHAR(SFT_FLEXI_HALFDAY_MARK , 'HH24:MI:SS') "
						+ " FROM HRMS_DAILY_ATTENDANCE_"
						+ String.valueOf(outDoorVisitDtl[0][4])
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_DAILY_ATTENDANCE_"
						+ String.valueOf(outDoorVisitDtl[0][4])
						+ ".ATT_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+ " INNER JOIN HRMS_SHIFT ON (HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) "
						+ " WHERE ATT_EMP_ID = "
						+ String.valueOf(outDoorVisitDtl[0][0])
						+ " AND ATT_DATE = TO_DATE('"
						+ String.valueOf(outDoorVisitDtl[0][1])
						+ "', 'DD-MM-YYYY')";
				Object[][] attendanceData = getSqlModel().getSingleResult(
						attendanceQuery);

				if (attendanceData != null && attendanceData.length != 0) {
					Object[] calculatedData = calculateLateAndEarlyHrs(String
							.valueOf(attendanceData[0][0]), String
							.valueOf(attendanceData[0][1]), String
							.valueOf(attendanceData[0][7]), String
							.valueOf(attendanceData[0][8]), String
							.valueOf(attendanceData[0][9]), String
							.valueOf(attendanceData[0][10]), String
							.valueOf(outDoorVisitDtl[0][2]), String
							.valueOf(outDoorVisitDtl[0][3]), String
							.valueOf(attendanceData[0][13]), String
							.valueOf(attendanceData[0][14]), String
							.valueOf(attendanceData[0][15]), String
							.valueOf(attendanceData[0][16]));

					String status1 = "PR";
					// parameters for isHoliday()==> Employee-Id and Outdoor
					// visit date from Query : outdoorQuery
					// parameters for isWeeklyOff()==> shift-ID from query :
					// attendanceData and Outdoor visit date from Query :
					// outdoorQuery
					if (isHoliday(checkNull(String
							.valueOf(outDoorVisitDtl[0][0])), checkNull(String
							.valueOf(outDoorVisitDtl[0][1])))) {
						status1 = "HO";
					} else if (isWeeklyOff(checkNull(String
							.valueOf(attendanceData[0][12])), checkNull(String
							.valueOf(outDoorVisitDtl[0][1])))) {
						status1 = "WO";
					}

					String updateQuery = " UPDATE HRMS_DAILY_ATTENDANCE_"
							+ String.valueOf(outDoorVisitDtl[0][4])
							+ " SET ATT_LOGIN = TO_DATE('"
							+ String.valueOf(calculatedData[4])
							+ "', 'HH24:MI:SS'), " + " ATT_LOGOUT = TO_DATE('"
							+ String.valueOf(calculatedData[5])
							+ "', 'HH24:MI:SS'), "
							+ " ATT_WORKING_HRS = TO_DATE('"
							+ String.valueOf(calculatedData[6])
							+ "', 'HH24:MI:SS'), "
							+ " ATT_EXTRAHRS = TO_DATE('"
							+ String.valueOf(calculatedData[2])
							+ "', 'HH24:MI:SS'), "
							+ " ATT_LATE_HRS = TO_DATE('"
							+ String.valueOf(calculatedData[0])
							+ "', 'HH24:MI:SS'), "
							+ " ATT_EARLY_HRS = TO_DATE('"
							+ String.valueOf(calculatedData[1])
							+ "', 'HH24:MI:SS'), " + " ATT_STATUS_TWO = '"
							+ String.valueOf(calculatedData[3]) + "' "
							+ " , ATT_STATUS_ONE = '" + status1 + "' "
							+ " WHERE ATT_EMP_ID = "
							+ String.valueOf(outDoorVisitDtl[0][0])
							+ " AND ATT_DATE = TO_DATE('"
							+ String.valueOf(outDoorVisitDtl[0][1])
							+ "','DD-MM-YYYY')";
					boolean result = getSqlModel().singleExecute(updateQuery);
				}
			} else {
				return;
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	private boolean isHoliday(String empId, String outDoorDate) {
		String query = " SELECT CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF ";
		Object[][] branchFlag = getSqlModel().getSingleResult(query);

		Object[][] holiDay = null;
		String holidaySql = "";

		if (branchFlag != null && branchFlag.length > 0
				&& String.valueOf(branchFlag[0][0]).equals("Y")) {
			Object[] branchHolidayDate = new Object[3];
			branchHolidayDate[0] = outDoorDate; // holiday date
			branchHolidayDate[1] = empId; // employee code
			branchHolidayDate[2] = outDoorDate; // holiday date

			holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END "
					+ " FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = ?) AND "
					+ " HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY')";
			holiDay = getSqlModel().getSingleResult(holidaySql,
					branchHolidayDate);
		} else {
			// retrieve holiday details from HRMS_HOLIDAY table
			Object[] holidayDate = new Object[2];
			holidayDate[0] = outDoorDate; // holiday date
			holidayDate[1] = outDoorDate; // holiday date

			holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END FROM HRMS_HOLIDAY "
					+ " WHERE HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') ";
			holiDay = getSqlModel().getSingleResult(holidaySql, holidayDate);
		}

		if (holiDay != null && holiDay.length != 0
				&& String.valueOf(holiDay[0][0]).equalsIgnoreCase("true")) {
			return true;
		}

		return false;
	}

	private boolean isWeeklyOff(String shiftId, String outDoorDate) {

		// retrieve the weekly off details from HRMS_SHIFT
		// table for given date
		String weekOffSql = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 "
				+ " FROM HRMS_SHIFT WHERE SHIFT_ID = ? ";
		Object[][] getWeeklyOffData = getSqlModel().getSingleResult(weekOffSql,
				new Object[] { shiftId });

		// convert date in to calendar date
		Calendar currentDay = new Utility()
				.getCalanderDate(checkNull(outDoorDate));

		// call isWeeklyOff method to check whether
		// given date is weekly off or not
		int count = isWeeklyOff(currentDay, getWeeklyOffData);

		// if isWeeklyOff method return 1, given date is a weekly off
		if (count == 1) {
			return true;
		}

		return false;
	}

	/**
	 * @method isWeeklyOff
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
		// week
		// of
		// specified
		// date

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
	 * @method calculateLateAndEarlyHrs
	 * @purpose to calculate half day, late coming, early leaving, dual late,
	 *          absent etc
	 * @param bean
	 * @param empInTime
	 * @param empOutTime
	 * @param offcStartTime
	 * @param offcEndTime
	 * @param firstHalfTime
	 * @param secondHalfTime
	 * @return Object array
	 */
	public Object[] calculateLateAndEarlyHrs(String empInTime,
			String empOutTime, String offcStartTime, String offcEndTime,
			String firstHalfTime, String secondHalfTime,
			String outDoorStartTime, String outDoorEndTime,
			String flexiTimeAllowed, String shiftWorkingHours,
			String markFlexiLateLessThan, String markFlexiHalfDayLateLessThan) {
		String status = "";
		// late hrs early hrs extra hrs status in time out time work hrs
		Object[] calculateTime = { "00:00:00", "00:00:00", "00:00:00", status,
				empInTime, empOutTime, "00:00:00" };

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
			Date dateFirstHalfTime = sdf.parse(firstHalfTime); // convert fist
			// half day time
			// in to date
			// format
			Date dateSecondHalfTime = sdf.parse(secondHalfTime); // convert
			// second
			// half day
			// time in
			// to date
			// format
			Date dateOutDoorStartTime = sdf.parse(outDoorStartTime); // convert
			// out
			// door
			// start
			// time
			// in to
			// date
			// format
			Date dateOutDoorEndTime = sdf.parse(outDoorEndTime); // convert
			// out door
			// end time
			// in to
			// date
			// format

			// Added by Manish Sakpal == Calculation for In-Time BEGINS
			if (!empInTime.equals("00:00:00")
					&& dateEmpInTime.before(dateOutDoorStartTime)) {
				calculateTime[4] = empInTime;
			} else {
				dateEmpInTime = dateOutDoorStartTime;
				empInTime = outDoorStartTime;
				calculateTime[4] = empInTime;
			}
			// Added by Manish Sakpal == Calculation for In-Time ENDS

			/*
			 * if (empInTime.equals("00:00:00") ||
			 * dateEmpInTime.after(dateOutDoorStartTime)) { dateEmpInTime =
			 * dateOutDoorStartTime; empInTime = outDoorStartTime;
			 * calculateTime[4] = empInTime; // in time } // end of if statement
			 */

			// Added by Manish Sakpal == Calculation for outTime BEGINS
			if (!empOutTime.equals("00:00:00")
					&& dateEmpOutTime.after(dateOutDoorEndTime)) {
				calculateTime[5] = empOutTime;
			} else {
				dateEmpOutTime = dateOutDoorEndTime;
				empOutTime = outDoorEndTime;
				calculateTime[5] = empOutTime;
			}
			// Added by Manish Sakpal == Calculation for outTime ENDS

			/*
			 * if (empOutTime.equals("00:00:00") ||
			 * dateEmpOutTime.before(dateOutDoorEndTime)) { dateEmpOutTime =
			 * dateOutDoorEndTime; empOutTime = outDoorEndTime; calculateTime[5] =
			 * empOutTime; // out time } // end of if statement
			 */
			Calendar cal = Calendar.getInstance();
			cal.set(dateEmpOutTime.getYear(), dateEmpOutTime.getMonth(),
					dateEmpOutTime.getDate(), dateEmpOutTime.getHours(),
					dateEmpOutTime.getMinutes(), dateEmpOutTime.getSeconds());
			cal.add(Calendar.HOUR, -dateEmpInTime.getHours());
			cal.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
			cal.add(Calendar.SECOND, -dateEmpInTime.getSeconds());
			calculateTime[6] = sdf.format(cal.getTime()); // work hours

			Calendar c1 = Calendar.getInstance();

			if ((dateEmpInTime.after(dateOffcStartTime)
					&& (dateEmpInTime.before(dateFirstHalfTime)) || dateEmpInTime
					.equals(dateFirstHalfTime))
					&& ((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
							.equals(dateSecondHalfTime)) && dateEmpOutTime
							.before(dateOffcEndTime))) {
				c1.set(dateEmpInTime.getYear(), dateEmpInTime.getMonth(),
						dateEmpInTime.getDate(), dateEmpInTime.getHours(),
						dateEmpInTime.getMinutes(), dateEmpInTime.getSeconds());
				c1.add(Calendar.HOUR, -dateOffcStartTime.getHours());
				c1.add(Calendar.MINUTE, -dateOffcStartTime.getMinutes());
				c1.add(Calendar.SECOND, -dateOffcStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours

				c1.set(dateOffcEndTime.getYear(), dateOffcEndTime.getMonth(),
						dateOffcEndTime.getDate(), dateOffcEndTime.getHours(),
						dateOffcEndTime.getMinutes(), dateOffcEndTime
								.getSeconds());
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours
				status = "DL";
			} else if (dateEmpInTime.after(dateFirstHalfTime)
					&& !empInTime.equals("00:00:00")) {
				status = "HD";
			} else if (dateEmpOutTime.before(dateSecondHalfTime)
					&& !empOutTime.equals("00:00:00")) {
				status = "HD";
			} else if (dateEmpInTime.after(dateOffcStartTime)
					&& (dateEmpInTime.before(dateFirstHalfTime) || dateEmpInTime
							.equals(dateFirstHalfTime))) {
				c1.set(dateEmpInTime.getYear(), dateEmpInTime.getMonth(),
						dateEmpInTime.getDate(), dateEmpInTime.getHours(),
						dateEmpInTime.getMinutes(), dateEmpInTime.getSeconds());
				status = "LC";
				c1.add(Calendar.HOUR, -dateOffcStartTime.getHours());
				c1.add(Calendar.MINUTE, -dateOffcStartTime.getMinutes());
				c1.add(Calendar.SECOND, -dateOffcStartTime.getSeconds());

				calculateTime[0] = sdf.format(c1.getTime()); // late hours
			} else if ((dateEmpOutTime.after(dateSecondHalfTime) || dateEmpOutTime
					.equals(dateSecondHalfTime))
					&& dateEmpOutTime.before(dateOffcEndTime)) {
				status = "EL";
				c1.set(dateOffcEndTime.getYear(), dateOffcEndTime.getMonth(),
						dateOffcEndTime.getDate(), dateOffcEndTime.getHours(),
						dateOffcEndTime.getMinutes(), dateOffcEndTime
								.getSeconds());
				c1.add(Calendar.HOUR, -dateEmpOutTime.getHours());
				c1.add(Calendar.MINUTE, -dateEmpOutTime.getMinutes());
				c1.add(Calendar.SECOND, -dateEmpOutTime.getSeconds());

				calculateTime[1] = sdf.format(c1.getTime()); // early hours

			} else if ((!empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					&& (dateEmpInTime.before(dateOffcStartTime) || dateEmpInTime
							.equals(dateOffcStartTime))
					&& (dateEmpOutTime.after(dateOffcEndTime) || dateEmpOutTime
							.equals(dateOffcEndTime))) {
				status = "IN";
			} else {
				status = "AB";
			}

			if (dateEmpOutTime.after(dateOffcEndTime)) {
				c1.set(dateEmpOutTime.getYear(), dateEmpOutTime.getMonth(),
						dateEmpOutTime.getDate(), dateEmpOutTime.getHours(),
						dateEmpOutTime.getMinutes(), dateEmpOutTime
								.getSeconds());
				// status = "Extra Hours";
				c1.add(Calendar.HOUR, -dateOffcEndTime.getHours());
				c1.add(Calendar.MINUTE, -dateOffcEndTime.getMinutes());
				c1.add(Calendar.SECOND, -dateOffcEndTime.getSeconds());

				calculateTime[2] = sdf.format(c1.getTime()); // extra hours
			} // end of if

			// Added by manish sakpal begins
			// if working hours >= 8Hrs. then status2 = IN TIME
			if (flexiTimeAllowed.equals("Y")) {
				Calendar flex = Calendar.getInstance();
				flex.setTime(dateEmpOutTime);
				flex.add(Calendar.HOUR, -dateEmpInTime.getHours());
				flex.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				flex.add(Calendar.SECOND, -dateEmpInTime.getSeconds());

				int employeeWorkHours = TimeUtility.getMinute(String
						.valueOf(sdf.format(flex.getTime())));
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
			}

			if ((flexiTimeAllowed.equals("Y"))
					&& !(empInTime.equals("00:00:00") || empOutTime
							.equals("00:00:00"))) {
				Calendar flex = Calendar.getInstance();
				flex.setTime(dateEmpOutTime);
				flex.add(Calendar.HOUR, -dateEmpInTime.getHours());
				flex.add(Calendar.MINUTE, -dateEmpInTime.getMinutes());
				flex.add(Calendar.SECOND, -dateEmpInTime.getSeconds());

				int flexMins = TimeUtility.getMinute(String.valueOf(sdf
						.format(flex.getTime())));
				int workingHoursFromShift = TimeUtility
						.getMinute(shiftWorkingHours);
				// Daily_working hours less than shift working hours then HD
				if (flexMins > workingHoursFromShift) {
					status = "IN";
				}
			}
			// Added by manish sakpal ends

			// Convert a> outDoorStartTIme and offcStartTime(Shift start time)
			// into minutes
			// b> outDoorEndTime and offcEndTime(Shift end time) into minutes
			int outdoorFromTime = TimeUtility.getMinute(outDoorStartTime);
			int shiftStartTime = TimeUtility.getMinute(offcStartTime);

			int outDoorToTime = TimeUtility.getMinute(outDoorEndTime);
			int shiftEndTime = TimeUtility.getMinute(offcEndTime);
			if ((!empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					&& (shiftStartTime == outdoorFromTime)
					&& (shiftEndTime == outDoorToTime)) {
				status = "IN";
			} else if ((!empInTime.equals("00:00:00") && !empOutTime
					.equals("00:00:00"))
					&& ((outdoorFromTime < shiftStartTime) && (outDoorToTime > shiftEndTime))) {
				status = "IN";
			} else {
				status = status;
			}
			calculateTime[3] = status;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return calculateTime;
	}

	/**
	 * @param bean
	 * @param empFlow
	 * @param outCode
	 * @return boolean after changing the status of application.
	 */
	/*
	 * changing the status of application based on employee flow
	 */
	public boolean changeApplStatus(OutdoorApproval bean, Object[][] empFlow,
			String outCode, String empCode) {
		boolean result = false;
		// Object[][] to_mailIds = new Object[1][1];
		// Object[][] from_mailIds = new Object[1][1];
		if (empFlow != null && empFlow.length != 0) {
			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][0];
			updateApprover[0][1] = empFlow[0][2];
			updateApprover[0][2] = empFlow[0][3];
			updateApprover[0][3] = outCode;
			result = getSqlModel().singleExecute(getQuery(4), updateApprover);

			if (result) {
				/*
				 * try { to_mailIds[0][0] = String.valueOf(empFlow[0][0]);
				 * from_mailIds[0][0] = empCode; MailUtility mail=new
				 * MailUtility(); mail.initiate(context, session);
				 * mail.sendMail(to_mailIds,from_mailIds,"Outdoor", "", "P");
				 * mail.terminate(); } catch (Exception e) { logger.error(e); }
				 */
			}
		} else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A";
			statusChanged[0][1] = outCode;
			try {
				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);
				/*
				 * if(result) { attendanceUpdate(outCode); to_mailIds[0][0] =
				 * String.valueOf(empCode); from_mailIds[0][0] =
				 * bean.getUserEmpId(); MailUtility mail = new MailUtility();
				 * mail.initiate(context, session);
				 * mail.sendMail(to_mailIds,from_mailIds,"Outdoor", "", "A");
				 * mail.terminate(); }
				 */
			} catch (Exception e) {
				logger.error(e);
			}
		} // end of else statement
		collect(bean, bean.getOutAppStatus());
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param bean
	 * @param status
	 */
	/*
	 * Method for collecting the records.
	 */
	public void collect(OutdoorApproval bean, String status) {
		ArrayList<Object> list = new ArrayList<Object>();
		Object[][] result = null;
		try {
			Object[] emp = new Object[2];
			emp[0] = status;
			emp[1] = bean.getUserEmpId();
			bean.setOutAppStatus(status);

			String query = " SELECT OUTDOORVISIT_DATE, OUTDOORVISIT_LOCATION, OUTDOORVISIT_PURPOSE, OUTDOORVISIT_STATUS,"
					+ " OUTDOORVISIT_APPR, OUTDOORVISIT_ECODE, OUTDOORVISIT_CODE, OUTDOOR_APP_LEVEL, OUTDOORVISIT_LOCATION "
					+ " FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_STATUS = '"
					+ String.valueOf(emp[0]).trim()
					+ "' AND (OUTDOORVISIT_APPR = "
					+ emp[1]
					+ " OR OUTDOOR_ALTER_APPROVER= "
					+ emp[1]
					+ ") ORDER BY OUTDOORVISIT_DATE DESC";
			result = getSqlModel().getSingleResult(query);

			for (int i = 0; i < result.length; i++) {
				OutdoorApproval bean1 = new OutdoorApproval();
				bean1.setOutDate(String.valueOf(result[i][0]));
				bean1.setOutLoc(String.valueOf(result[i][1]));
				bean1.setPurpose(String.valueOf(result[i][2]));
				bean1.setEcode(String.valueOf(result[i][5]));
				bean1.setOutVisitcode(String.valueOf(result[i][6]));
				bean1.setCheckStatus(String.valueOf(result[i][3]));
				bean1.setLevel(String.valueOf(result[i][7]));
				bean1.setOutLoc(String.valueOf(result[i][8]));
				list.add(bean1);
			}// end of for loop
		} catch (Exception e) {
			logger.error(e);
		}// end of try catch
		bean.setList(list);
		bean.setOutAppStatus(status);
	}

	/**
	 * @param bean
	 * @param status
	 * @param reqCode
	 * @param purpose
	 * @param location
	 */
	/*
	 * forward the application to next level.
	 */
	public boolean forward(OutdoorApproval bean, String status, String reqCode,
			String purpose, String location) {
		Object[][] changeStatus = new Object[1][5];
		// Object[][] to_mailIds = new Object[1][1];
		// Object[][] from_mailIds = new Object[1][1];

		changeStatus[0][0] = reqCode;
		changeStatus[0][1] = bean.getUserEmpId();
		changeStatus[0][2] = status;
		changeStatus[0][3] = purpose;
		changeStatus[0][4] = location;
		boolean result = false;

		if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(reqCode);
			try {
				result = getSqlModel().singleExecute(getQuery(3), reject);
				/*
				 * if(result) { String empquery = "SELECT OUTDOORVISIT_ECODE
				 * FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = " +
				 * String.valueOf(reqCode); Object[][] employeeid =
				 * getSqlModel().getSingleResult(empquery); to_mailIds[0][0] =
				 * String.valueOf(employeeid[0][0]); from_mailIds[0][0] =
				 * bean.getUserEmpId(); MailUtility mail = new MailUtility();
				 * mail.initiate(context, session); mail.sendMail(to_mailIds,
				 * from_mailIds, "Outdoor", "", "R"); mail.terminate(); }
				 */
			} catch (Exception e) {
				logger.error(e);
			}
		} // end of if statement
		result = getSqlModel().singleExecute(getQuery(2), changeStatus);
		collect(bean, bean.getOutAppStatus());

		return result;
	}

	public String onlineApproveRejectSendBack(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		String res = "";
		try {
			String query = " SELECT OUTDOORVISIT_STATUS, OUTDOORVISIT_APPR FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = "
					+ applicationCode;
			Object approverIdObj[][] = getSqlModel().getSingleResult(query);
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][1]).equals(approverId)
						&& (String.valueOf(approverIdObj[0][0]).equals("P") || String
								.valueOf(approverIdObj[0][0]).equals("F"))) {

					res = approveRejectSendBackLevApp(request, empCode,
							applicationCode, status, remarks, approverId, level);

					if (res.equals("approved")) {
						result = "Outdoor application has been approved.";
					} else if (res.equals("rejected")) {
						result = "Outdoor application has been rejected.";
					} else if (res.equals("sendback")) {
						result = "Outdoor application has been sent back to applicant.";
					} else if (res.equals("forwarded")) {
						result = "Outdoor application has been forwarded.";
					} else {
						result = "Error Occured.";
					}
				} else {
					result = "Outdoor application has already been processed.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String approveRejectSendBackLevApp(HttpServletRequest request,
			String applicantID, String applicationCode, String status,
			String remarks, String currentUserID, String level) {
		String applStatus = "pending";
		String newStatus = "";
		Object[][] empFlow = generateEmpFlow(applicantID, "Outdoor", Integer.parseInt(level));;
		try {

			if (String.valueOf(status).equals("A")) {

				empFlow = generateEmpFlow(applicantID, "Outdoor", Integer
						.parseInt(level) + 1);

				applStatus = changeApplStatus(empFlow, String
						.valueOf(applicationCode), applicantID, status);
			}

			if (String.valueOf(status).equals("B")) {
				Object[][] sendBackObj = new Object[1][2];
				sendBackObj[0][0] = status; // status
				sendBackObj[0][1] = applicationCode;// application code
				String outDoorVisitSendBackQuery = " UPDATE HRMS_OUTDOORVISIT SET OUTDOORVISIT_STATUS='B', "
						+ " OUTDOORVISIT_APPR="
						+ currentUserID
						+ ",OUTDOOR_APP_LEVEL=1 "
						+ " WHERE OUTDOORVISIT_CODE="
						+ applicationCode;
				getSqlModel().singleExecute(outDoorVisitSendBackQuery);
				applStatus = "sendback";
			}

			if (String.valueOf(status).equals("R")) {
				Object[][] reject = new Object[1][2];
				reject[0][0] = String.valueOf(status); // status
				reject[0][1] = String.valueOf(applicationCode);// application
				// code
				String outDoorVisitRejectQuery = " UPDATE HRMS_OUTDOORVISIT SET OUTDOORVISIT_STATUS='R' WHERE OUTDOORVISIT_CODE="
						+ applicationCode;
				getSqlModel().singleExecute(outDoorVisitRejectQuery);
				applStatus = "rejected";
			}
			// Insert into HRMS_OUTDOOR_PATH

			Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = applicationCode; // application code
			changeStatus[0][1] = currentUserID; // user employee id
			if (applStatus.equals("forwarded")) {
				newStatus = "F"; // status
			} else if (applStatus.equals("approved")) {
				newStatus = "A"; // status
			} else if (applStatus.equals("sendback")) {
				newStatus = "B"; // status
			} else if (applStatus.equals("rejected")) {
				newStatus = "R"; // status
			}
			changeStatus[0][2] = newStatus;
			changeStatus[0][3] = remarks; // remark

			String pathQuery = "INSERT INTO HRMS_OUTDOOR_PATH (OUTDOOR_PATH_OUTCODE,OUTDOOR_PATH_APPRCODE,OUTDOOR_PATH_DATE,OUTDOOR_PATH_STATUS,OUTDOOR_PATH_PURPOSE)"
					+ " VALUES(?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?)";
			getSqlModel().singleExecute(pathQuery, changeStatus);

		} catch (Exception e) {
			logger.error(e);
		}
		/*
		 * if(applStatus.equals("forwarded")){ Object[][] empFlow =
		 * generateEmpFlow(currentUserID, "Outdoor", Integer.parseInt(level));
		 * if(empFlow!=null && empFlow.length>0){
		 * sendMailToNextApprover(request, currentUserID, empFlow,
		 * applicationCode, level); sendMailToApplicant(request, currentUserID,
		 * applicationCode, applicantID, level); } }else{ sendMailToApplicant(
		 * request, currentUserID, applicationCode, applicantID, level); }
		 */

		sendMailAlert(request, currentUserID, applicationCode, applicantID,
				level, currentUserID, newStatus, empFlow);

		return applStatus;
	}

	private String changeApplStatus(Object[][] empFlow, String applicationCode,
			String empCode, String status) {
		String applStatus = "pending";

		if (empFlow != null && empFlow.length != 0) {
			Object[][] updateApprover = new Object[1][5];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = "F"; // Status
			updateApprover[0][4] = applicationCode; // application code
			getSqlModel().singleExecute(getQuery(4), updateApprover);
			applStatus = "forwarded";
		} else {
			String query = " UPDATE HRMS_OUTDOORVISIT SET OUTDOORVISIT_STATUS=? WHERE OUTDOORVISIT_CODE = ? ";
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A"; // status
			statusChanged[0][1] = applicationCode; // application code
			getSqlModel().singleExecute(query, statusChanged);
			attendanceUpdate(applicationCode);
			applStatus = "approved";
		}
		return applStatus;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}

	private void sendMailToNextApprover(HttpServletRequest request,
			String currentUser, Object[][] empFlow, String applicationId,
			String level) {
		try {
			String nextApproverID = String.valueOf(empFlow[0][0]);

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("OUT DOOR VISIT MAIL FROM APPROVER TO NEXT APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, currentUser);

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, nextApproverID);

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, currentUser);

			EmailTemplateQuery templateQueryApp7 = templateApp
					.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, nextApproverID);

			templateApp.configMailAlert();

			String[] link_parameter = new String[3];
			String[] link_labels = new String[3];
			String applicationType = "OutDoorOnline";
			link_parameter[0] = applicationType + "#" + currentUser + "#"
					+ applicationId + "#" + "A" + "#" + "..." + "#"
					+ nextApproverID + "#" + level;
			link_parameter[1] = applicationType + "#" + currentUser + "#"
					+ applicationId + "#" + "R" + "#" + "..." + "#"
					+ nextApproverID + "#" + level;
			link_parameter[2] = applicationType + "#" + currentUser + "#"
					+ applicationId + "#" + "B" + "#" + "..." + "#"
					+ nextApproverID + "#" + level;
			link_labels[0] = "Approve";
			link_labels[1] = "Reject";
			link_labels[2] = "Send Back";

			/*
			 * String[] link_parameter = new String[1]; String[] link_labels =
			 * new String[1]; String applicationType1 = "OutDoorOnline";
			 * link_parameter[0] = applicationType1 + "#" +
			 * String.valueOf(empFlow[0][0]) + "#applicationDtls#";
			 * 
			 * String link =
			 * "/attendance/OutdoorVisit_showDetailsForApproval.action?outdrCode="+
			 * approval.getOutvCode().trim(); // link=
			 * PPEncrypter.encrypt(link); link_parameter[0] += link;
			 * link_labels[0] = "Approve/Reject/SentBack";
			 */

			String[] keepInformToEmpID = request
					.getParameterValues("keepInformCode");
			if (keepInformToEmpID != null && keepInformToEmpID.length > 0) {
				/*
				 * for (int i = 0; i < keepInformToEmpID.length; i++) {
				 * System.out.println("Keep Inform : "+keepInformToEmpID[i]); }
				 */
				templateApp.sendApplicationMailToKeepInfo(keepInformToEmpID);
			}

			String module = "OutdoorVisit";
			String msgType = "A";

			templateApp.sendProcessManagerAlert(String.valueOf(empFlow[0][0]),
					module, msgType, applicationId, level);

			templateApp.addOnlineLink(request, link_parameter, link_labels);
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void sendMailAlert(HttpServletRequest request, String currentUser,
			String applicationId, String applicantID, String level,
			String approverId, String status, Object[][] empFlow) {
		if (!String.valueOf(status).equals("P")) {
			String  newApprover = "";
			try {
				if (status.equals("B")) {
					newApprover = "";
				} else {
					newApprover = String.valueOf(empFlow[0][0]);
				}
			} catch (Exception e) {
				logger.error(e);
			}

			String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
			if (!newApprover.equals("")) {
				sendMailToApplicant(request, currentUser, applicationId,
						applicantID, alertLevel);
				sendMailToNextApprover(request, currentUser, empFlow,
						applicationId, alertLevel);
			} else {
				sendMailToApplicant(request, currentUser, applicationId,
						applicantID, alertLevel);
			}
		}
	}

	private void sendMailToApplicant(HttpServletRequest request,
			String currentUser, String applicationId, String applicantID,
			String level) {
		try {
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("OUT DOOR VISIT MAIL FROM APPROVER TO APPLICANT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, currentUser);

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, applicantID);

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, currentUser);

			templateApp.configMailAlert();

			String[] keepInformToEmpID = request
					.getParameterValues("keepInformCode");
			if (keepInformToEmpID != null && keepInformToEmpID.length > 0) {
				/*
				 * for (int i = 0; i < keepInformToEmpID.length; i++) {
				 * System.out.println("Keep Inform : "+keepInformToEmpID[i]); }
				 */
				templateApp.sendApplicationMailToKeepInfo(keepInformToEmpID);
			}

			String module = "OutdoorVisit";
			String msgType = "I";
			// String level = "1";
			templateApp.sendProcessManagerAlert(applicantID, module, msgType,
					applicationId, level);

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}