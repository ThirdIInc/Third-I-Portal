/* Bhushan Dasare Mar 1, 2008 */

package org.paradyne.model.attendance;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.attendance.DailyAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * To define a business for viewing and modifying the daily attendance.
 */

public class DailyAttendanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DailyAttendanceModel.class);

	/**
	 * Bulk Status update
	 * 
	 * @param bean
	 * @return
	 */
	public String bulkStatusUpdate(DailyAttendance bean) {
		String fromDate = bean.getFromDate();
		String toDate = bean.getToDate();
		String bulkStatus1 = bean.getBulkStatus1();
		String bulkStatus2 = bean.getBulkStatus2();
		String message = "";
		
		Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
		Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
		
		while(fromDateCalendar.before(toDateCalendar) || fromDateCalendar.equals(toDateCalendar)) {
			int dayOfMonth = fromDateCalendar.get(Calendar.DAY_OF_MONTH);
			int month = fromDateCalendar.get(Calendar.MONTH) + 1;
			int year = fromDateCalendar.get(Calendar.YEAR);
			String date = dayOfMonth + "-" + month + "-" + year;

			String dailyAttendanceSql = " SELECT EMP_ID FROM HRMS_DAILY_ATTENDANCE_" + year + 
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " + 
			" WHERE ATT_DATE = TO_DATE('" + date + "', 'DD-MM-YYYY') ";
			dailyAttendanceSql = setFilters(bean, dailyAttendanceSql);
			Object[][] dailyAttendanceObj = getSqlModel().getSingleResult(dailyAttendanceSql);
			
			if(dailyAttendanceObj != null && dailyAttendanceObj.length > 0) {
				String empIds = "";

				for (int i = 0; i < dailyAttendanceObj.length; i++) {
					empIds += "'" + String.valueOf(dailyAttendanceObj[i][0]) + "',";
				}
				
				empIds = empIds.substring(0, (empIds.length() - 1));
				
				String bulkUpdateQuery = " UPDATE HRMS_DAILY_ATTENDANCE_" + year + 
				" SET ATT_STATUS_ONE = '" + bulkStatus1 + "', ATT_STATUS_TWO = '" + bulkStatus2 + "' " +
				" WHERE ATT_DATE = TO_DATE('" + date + "', 'DD-MM-YYYY') " + Utility.getConcatenatedIds("ATT_EMP_ID", empIds);
				boolean queryResult = getSqlModel().singleExecute(bulkUpdateQuery);
				
				if(queryResult) {
					message = "Record updated successfully!";
				}
			}
			
			fromDateCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return message;
	}

	/**
	 * Calculate Weekly Off for given date
	 * 
	 * @param calDate
	 * @param woDaysForWeek1
	 * @param woDaysForWeek2
	 * @param woDaysForWeek3
	 * @param woDaysForWeek4
	 * @param woDaysForWeek5
	 * @param woDaysForWeek6
	 * @return
	 */
	private String calWeeklyOff(Calendar calDate, String woDaysForWeek1, String woDaysForWeek2, String woDaysForWeek3, String woDaysForWeek4, 
		String woDaysForWeek5, String woDaysForWeek6) {
		
		String status1 = "AB";
		
		String[] weeklyOffDays = null;
		int weekOfMonth = calDate.get(Calendar.WEEK_OF_MONTH);
		
		switch(weekOfMonth) {
			case 1 :
				if(woDaysForWeek1.length() > 0) {
					if(woDaysForWeek1.length() > 1) {
						weeklyOffDays = woDaysForWeek1.split(",");
					} else if(!(woDaysForWeek1.equals("null") || woDaysForWeek1.equals("") || woDaysForWeek1 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek1;
					}
				}
				break;
			case 2 :
				if(woDaysForWeek2.length() > 0) {
					if(woDaysForWeek2.length() > 1) {
						weeklyOffDays = woDaysForWeek2.split(",");
					} else if(!(woDaysForWeek2.equals("null") || woDaysForWeek2.equals("") || woDaysForWeek2 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek2;
					}
				}
				break;
			case 3 :
				if(woDaysForWeek3.length() > 0) {
					if(woDaysForWeek3.length() > 1) {
						weeklyOffDays = woDaysForWeek3.split(",");
					} else if(!(woDaysForWeek3.equals("null") || woDaysForWeek3.equals("") || woDaysForWeek3 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek3;
					}
				}
				break;
			case 4 :
				if(woDaysForWeek4.length() > 0) {
					if(woDaysForWeek4.length() > 1) {
						weeklyOffDays = woDaysForWeek4.split(",");
					} else if(!(woDaysForWeek4.equals("null") || woDaysForWeek4.equals("") || woDaysForWeek4 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek4;
					}
				}
				break;
			case 5 :
				if(woDaysForWeek5.length() > 0) {
					if(woDaysForWeek5.length() > 1) {
						weeklyOffDays = woDaysForWeek5.split(",");
					} else if(!(woDaysForWeek5.equals("null") || woDaysForWeek5.equals("") || woDaysForWeek5 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek5;
					}
				}
				break;
			case 6 :
				if(woDaysForWeek6.length() > 0) {
					if(woDaysForWeek6.length() > 1) {
						weeklyOffDays = woDaysForWeek6.split(",");
					} else if(!(woDaysForWeek6.equals("null") || woDaysForWeek6.equals("") || woDaysForWeek6 == null)) {
						weeklyOffDays = new String[1];
						weeklyOffDays[0] = woDaysForWeek6;
					}
				}
				break;
			default : break;
		}
		
		if(weeklyOffDays != null && weeklyOffDays.length > 0) {
			for(int i = 0; i < weeklyOffDays.length; i++) {
				if(!(weeklyOffDays[i].equals("null") || weeklyOffDays[i].equals("") || weeklyOffDays[i] == null)) {
					int weekDay = Integer.parseInt(weeklyOffDays[i]);
					int dayOfWeek = calDate.get(Calendar.DAY_OF_WEEK);
					
					if(weekDay == dayOfWeek) {
						status1 = "WO";
						break;
					}
				}
			}
		}
		
		return status1;
	}

	/**
	 * This method is used to do pagination
	 * 
	 * @param bean
	 * @param empLength
	 * @param attnObj
	 * @param request
	 */
	public void doPaging(DailyAttendance bean, int empLength, Object[][] attnObj, HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of record to show on a current page toTotRec -: Last No. of
			 * record to show on a current page pageNo -: Current page to show totalPage -: Total No. of Pages
			 */

			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_ATTENDANCE_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));

			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int totalPage = 0;

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0, java.math.BigDecimal.ROUND_UP).toString());

			if(String.valueOf(bean.getHdPage()).equals("null") || String.valueOf(bean.getHdPage()).equals(null) || 
				String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if(pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}

				if(toTotRec > empLength) {
					toTotRec = empLength;
				}
			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		} catch(Exception e) {
			logger.error("Exception in doPaging:" + e);
		}
	}
	
	
	private Object[][] getAttendanceSettingObj() {
		Object[][] getAttendanceSettingObj = null;
		try {
			String query = " SELECT CONF_SHOW_EXTRAWORK FROM HRMS_ATTENDANCE_CONF ";
			getAttendanceSettingObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAttendanceSettingObj;
	}
	
	/**
	 * Gets the daily attendance data
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public Object[][] getAttendance(DailyAttendance bean) {
		Object[][] dailyAttn = null;
		try {
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();

			/**
			 * Calculate number of years between from year and to year
			 */
			String fromYear = fromDate.substring(6, 10);
			String toYear = toDate.substring(6, 10);
			int diffYear = Integer.parseInt(toYear) - Integer.parseInt(fromYear);

			int fYear = Integer.parseInt(fromYear);
			int tYear = Integer.parseInt(toYear);

			String showQuery = " SELECT * FROM ( ";
			for(int i = 0; i <= diffYear; i++) {
				showQuery += " SELECT DISTINCT EMP_ID, EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATT_DATE, " +
				" ATT_SHIFT_ID, SHIFT_NAME, NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI:SS'), '00:00:00') ATT_LOGIN, " +
				" NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI:SS'), '00:00:00') ATT_LOGOUT, " +
				" NVL(TO_CHAR(ATT_WORKING_HRS, 'HH24:MI:SS'), '00:00:00') ATT_WORKING_HRS, " +
				" NVL(TO_CHAR(ATT_EXTRAHRS, 'HH24:MI:SS'), '00:00:00') ATT_EXTRAHRS,"+
				" NVL(TO_CHAR(ATT_LATE_HRS, 'HH24:MI:SS'), '00:00:00') ATT_LATE_HRS, " +
				" NVL(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI:SS'), '00:00:00') ATT_EARLY_HRS, ATT_STATUS_ONE, ATT_STATUS_TWO " +
				" FROM HRMS_DAILY_ATTENDANCE_" + fYear + 
				" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + fYear + ".ATT_EMP_ID) " + 
				" INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_" + fYear + ".ATT_SHIFT_ID) " + 
				" WHERE 1 = 1 ";
				showQuery = setFilters(bean, showQuery);
				
				if(fYear != tYear) {
					showQuery += " UNION ALL ";
					fYear++;
				} else {
					break;
				}
			}
			
			showQuery += " ) WHERE TO_DATE(ATT_DATE, 'DD-MM-YYYY') BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " + 
			" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + 
			" ORDER BY UPPER(EMP_NAME), EMP_TOKEN, TO_DATE(ATT_DATE, 'DD-MM-YYYY') ";
			dailyAttn = getSqlModel().getSingleResult(showQuery);
		} catch(Exception e) {
			logger.error("Exception in getAttendance:" + e);
		}
		return dailyAttn;
	}
	
	/**
	 * Set filters when page loads
	 * 
	 * @param bean
	 */
	public void getFilters(DailyAttendance bean) {
		try {
			String payFiltersSql = " SELECT DECODE(CONF_BRN_FLAG, 'Y', 'true','N','false') BRN_FLAG, " +
			" DECODE(CONF_DEPT_FLAG, 'Y', 'true', 'N', 'false') DEPT_FLAG, DECODE(CONF_PAYBILL_FLAG, 'Y', 'true', 'N', 'false') PAYBILL_FLAG, " +
			" DECODE(CONF_EMPTYPE_FLAG, 'Y', 'true', 'N', 'false') EMPTYPE_FLAG, DECODE(CONF_DIVISION_FLAG, 'Y', 'true', 'N', 'false') DIVISION_FLAG " +
			" FROM HRMS_SALARY_CONF ";
			Object[][] payFiltersObj = getSqlModel().getSingleResult(payFiltersSql);
			
			bean.setBrnFlag(String.valueOf(payFiltersObj[0][0]));
			bean.setDeptFlag(String.valueOf(payFiltersObj[0][1]));
			bean.setPayBillFlag(String.valueOf(payFiltersObj[0][2]));
			bean.setTypeFlag(String.valueOf(payFiltersObj[0][3]));
			bean.setDivFlag(String.valueOf(payFiltersObj[0][4]));
		} catch(Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
	}

	/**
	 * Insert Attendance of the employees whose attendance not exists for a given date
	 * 
	 * @param bean
	 */
	private void insertNotExistingAttendance(DailyAttendance bean) {
		try {
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			
			String[] listOfFilters = new String[6];
			listOfFilters[0] = bean.getBrnCode();
			listOfFilters[1] = bean.getDeptCode();
			listOfFilters[2] = bean.getPayBillNo();
			listOfFilters[3] = bean.getTypeCode();
			listOfFilters[4] = bean.getDivCode();
			listOfFilters[5] = bean.getEFilNo();
			
			Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
			Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
			
			while(fromDateCalendar.before(toDateCalendar) || fromDateCalendar.equals(toDateCalendar)) {
				int dayOfMonth = fromDateCalendar.get(Calendar.DAY_OF_MONTH);
				int month = fromDateCalendar.get(Calendar.MONTH) + 1;
				int year = fromDateCalendar.get(Calendar.YEAR);
				String date = dayOfMonth + "-" + month + "-" + year;
				
				String attendanceNotExistsSql = " SELECT '" + date + "' AS ATTDN_DATE, EMP_ID, EMP_SHIFT, NVL(SHIFT_WEEK_1, '') AS SHIFT_WEEK_1, " +
				" NVL(SHIFT_WEEK_2, '') AS SHIFT_WEEK_2, NVL(SHIFT_WEEK_3, '') AS SHIFT_WEEK_3, NVL(SHIFT_WEEK_4, '') AS SHIFT_WEEK_4, " +
				" NVL(SHIFT_WEEK_5, '') AS SHIFT_WEEK_5, NVL(SHIFT_WEEK_6, '') AS SHIFT_WEEK_6, " +
				" CASE WHEN (SELECT CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF) = 'Y' THEN " +
				" (CASE WHEN (SELECT HOLI_DATE FROM HRMS_HOLIDAY_BRANCH WHERE HOLI_DATE = TO_DATE('" + date + "', 'DD-MM-YYYY') " +
				" AND CENTER_ID = EMP_CENTER) IS NULL THEN 'N' ELSE 'Y' END) " +
				" ELSE (CASE WHEN (SELECT HOLI_DATE FROM HRMS_HOLIDAY WHERE HOLI_DATE = TO_DATE('" + date + "', 'DD-MM-YYYY')) IS NULL THEN 'N' " +
				" ELSE 'Y' END) END AS IS_HOLIDAY, EMP_DIV, EMP_CENTER " +
				" FROM HRMS_EMP_OFFC " +
				" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
				" WHERE EMP_STATUS = 'S' AND EMP_ID NOT IN (SELECT ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_" + year +
				" WHERE ATT_DATE = TO_DATE('" + date + "', 'DD-MM-YYYY')) AND EMP_REGULAR_DATE <= TO_DATE('" + date + "', 'DD-MM-YYYY') ";
				attendanceNotExistsSql = setEmployeeOffciceFiletrs(listOfFilters, attendanceNotExistsSql); // set the filters
				attendanceNotExistsSql += " ORDER BY EMP_ID ";
				Object[][] attendanceNotExistsObj = getSqlModel().getSingleResult(attendanceNotExistsSql);
				
				if(attendanceNotExistsObj != null && attendanceNotExistsObj.length > 0) {
					Object[][] insertAttendance = new Object[attendanceNotExistsObj.length][6];

					for(int i = 0; i < attendanceNotExistsObj.length; i++) {
						insertAttendance[i][0] = String.valueOf(attendanceNotExistsObj[i][0]); // ATT_DATE
						insertAttendance[i][1] = String.valueOf(attendanceNotExistsObj[i][1]); // ATT_EMP_ID
						insertAttendance[i][2] = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][2])); // ATT_SHIFT_ID
						
						// Calculate Status One - either Weekly Off or Holiday
						String woDaysForWeek1 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][3])); // SHIFT_WEEK_1
						String woDaysForWeek2 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][4])); // SHIFT_WEEK_2
						String woDaysForWeek3 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][5])); // SHIFT_WEEK_3
						String woDaysForWeek4 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][6])); // SHIFT_WEEK_4
						String woDaysForWeek5 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][7])); // SHIFT_WEEK_5
						String woDaysForWeek6 = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][8])); // SHIFT_WEEK_6
						
						String status1 = "AB";
						if(String.valueOf(attendanceNotExistsObj[i][9]).equalsIgnoreCase("Y")) {
							status1 = "HO";
						} else {
							status1 = calWeeklyOff(fromDateCalendar, woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, 
								woDaysForWeek6);
						}

						insertAttendance[i][3] = status1;
						insertAttendance[i][4] = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][10])); // ATT_DIVISION
						insertAttendance[i][5] = Utility.checkNull(String.valueOf(attendanceNotExistsObj[i][11])); // ATT_BRANCH
					}
					
					String insertQuerySql = " INSERT INTO HRMS_DAILY_ATTENDANCE_" + year + " (ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, " +
					" ATT_LOGIN, ATT_LOGOUT, ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_STATUS_ONE, " +
					" ATT_STATUS_TWO, ATT_DIVISION, ATT_BRANCH) " +
					" VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'), " +
					" TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'), TO_DATE('00:00:00', 'HH24:MI:SS'), " +
					" TO_DATE('00:00:00', 'HH24:MI:SS'), ?, 'AB', ?, ?) ";
					getSqlModel().singleExecute(insertQuerySql, insertAttendance);
				}

				fromDateCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			
		} catch(Exception e) {
			logger.error("Exception in insertNotExistingAttendance:" + e);
		}
	}
	
	/**
	 * Set the selected filters in WHERE condition in Sql query while getting list of employees
	 * 
	 * @param listOfFilters: Contains list of filters 
	 * @param sqlQuery: Sql query need to be concatenated by filters in WHERE condition
	 * @return String as concatenated Sql query
	 */
	private String setEmployeeOffciceFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND EMP_DEPT = " + listOfFilters[0];
			}
			
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
			
			// if division is selected
			if(!listOfFilters[5].equals("")) {
				sqlQuery += " AND EMP_ID = " + listOfFilters[5];
			}
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setEmployeeOffciceFiletrs:" + e);
			return "";
		}
	}
	
	/**
	 * Set filters to records which are coming from Attendance
	 * 
	 * @param bean
	 * @param sqlQuery
	 * @return
	 */
	public String setFilters(DailyAttendance bean, String sqlQuery) {
		try {
			String divCode = bean.getDivCode();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String eFilNo = bean.getEFilNo();
			String srchStatus1 = bean.getSrchStatus1();
			String srchStatus2 = bean.getSrchStatus2();

			if(!divCode.equals("")) {
				sqlQuery += " AND EMP_DIV = " + divCode;
			}

			if(!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER = " + brnCode;
			}

			if(!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT = " + deptCode;
			}

			if(!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE = " + typeCode;
			}

			if(!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
			}

			if(!eFilNo.equals("")) {
				sqlQuery += " AND EMP_ID = " + eFilNo;
			}

			if(!srchStatus1.equals("AL")) {
				sqlQuery += " AND ATT_STATUS_ONE = '" + srchStatus1 + "' ";
			}

			if(!srchStatus2.equals("AL")) {
				sqlQuery += " AND ATT_STATUS_TWO = '" + srchStatus2 + "' ";
			}

			return sqlQuery;
		} catch(Exception e) {
			logger.error("Exception in setFilters:" + e);
			return "";
		}
	}

	/**
	 * Show saved attendance
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public String showAttendance(DailyAttendance bean, HttpServletRequest request) {
		String res = "";
		Object[][] attdnData = null;
		try {
			/**
			 * Insert Attendance of the employees whose attendance not exists for a given date
			 */
			//insertNotExistingAttendance(bean);

			/**
			 * Search Attendance
			 */
			Object[][] dailyAttn = getAttendance(bean); // Get saved daily attendance records
			Object[][] getAttendanceSettingObj = getAttendanceSettingObj();
			if(String.valueOf(getAttendanceSettingObj[0][0]).equals("Y")){
				bean.setShowExtraHoursFlag(true);
			}else{
				bean.setShowExtraHoursFlag(false);
			}
			doPaging(bean, dailyAttn.length, dailyAttn, request);
			int fromTotRec = Integer.parseInt(bean.getFromTotRec());
			int toTotRec = Integer.parseInt(bean.getToTotRec());
			
			if(dailyAttn != null && dailyAttn.length > 0) {
					attdnData = new Object[toTotRec - fromTotRec][14];
					int cnt = 0;
					for(int i = fromTotRec; i < toTotRec; i++) {
						attdnData[cnt][0] = String.valueOf(dailyAttn[i][0]); // EMP_TOKEN
						attdnData[cnt][1] = String.valueOf(dailyAttn[i][1]); // EMP_ID
						attdnData[cnt][2] = String.valueOf(dailyAttn[i][2]); // EMP_NAME
						attdnData[cnt][3] = String.valueOf(dailyAttn[i][3]); // ATT_DATE
						attdnData[cnt][4] = String.valueOf(dailyAttn[i][4]); // ATT_SHIFT_ID
						attdnData[cnt][5] = String.valueOf(dailyAttn[i][5]); // SHIFT_NAME
						attdnData[cnt][6] = String.valueOf(dailyAttn[i][6]); // ATT_LOGIN
						attdnData[cnt][7] = String.valueOf(dailyAttn[i][7]); // ATT_LOGOUT
						attdnData[cnt][8] = String.valueOf(dailyAttn[i][8]); // ATT_WORKING_HRS
						attdnData[cnt][9] = String.valueOf(dailyAttn[i][9]); // ATT_EXTRAHRS
						attdnData[cnt][10] = String.valueOf(dailyAttn[i][10]); // ATT_LATE_HRS
						attdnData[cnt][11] = String.valueOf(dailyAttn[i][11]); // ATT_EARLY_HRS
						attdnData[cnt][12] = String.valueOf(dailyAttn[i][12]); // ATT_STATUS_ONE
						attdnData[cnt][13] = String.valueOf(dailyAttn[i][13]); // ATT_STATUS_TWO
						cnt++;
					}
				bean.setFlag(true);
				request.setAttribute("totalRecords", dailyAttn.length);
			} else {
				res = "Attendance Not Available!";
				bean.setFlag(false);
			}
		} catch(Exception e) {
			logger.error("Exception in showAttendance:" + e);
			res = "Attendance Not Available!";
			bean.setFlag(false);
		}
		request.setAttribute("attdnData", attdnData);
		return res;
	}

	/**
	 * Update existing attendance
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public String update(DailyAttendance bean, HttpServletRequest request) {
		try {
			boolean res = false;

			Object[] eInTime = request.getParameterValues("eInTime");
			Object[] eOutTime = request.getParameterValues("eOutTime");
			Object[] eWHrs = request.getParameterValues("eWHrs");
			Object[] eExHrs = request.getParameterValues("eExHrs");
			Object[] eLtHrs = request.getParameterValues("eLtHrs");
			Object[] eErHrs = request.getParameterValues("eErHrs");
			Object[] eStatus1 = request.getParameterValues("eStatus1");
			Object[] eStatus2 = request.getParameterValues("eStatus2");
			Object[] eDate = request.getParameterValues("eDate");
			Object[] eId = request.getParameterValues("eId");

			for(int i = 0; i < eId.length; i++) {
				int year = Integer.parseInt(String.valueOf(eDate[i]).substring(6, 10));
				String insQuery = " UPDATE HRMS_DAILY_ATTENDANCE_" + year + 
				" SET ATT_LOGIN = TO_DATE(?, 'HH24:MI:SS'), ATT_LOGOUT = TO_DATE(?, 'HH24:MI:SS'), ATT_WORKING_HRS = TO_DATE(?, 'HH24:MI:SS'), " +
				" ATT_EXTRAHRS = TO_DATE(?, 'HH24:MI:SS'), ATT_LATE_HRS = TO_DATE(?, 'HH24:MI:SS'), ATT_EARLY_HRS = TO_DATE(?, 'HH24:MI:SS'), " +
				" ATT_STATUS_ONE = ?, ATT_STATUS_TWO = ? WHERE ATT_DATE = TO_DATE(?, 'DD-MM-YYYY') AND ATT_EMP_ID = ? ";

				Object[][] insData = new Object[1][10];
				insData[0][0] = String.valueOf(eInTime[i]);
				insData[0][1] = String.valueOf(eOutTime[i]);
				insData[0][2] = String.valueOf(eWHrs[i]);
				insData[0][3] = String.valueOf(eExHrs[i]);
				insData[0][4] = String.valueOf(eLtHrs[i]);
				insData[0][5] = String.valueOf(eErHrs[i]);
				insData[0][6] = String.valueOf(eStatus1[i]);
				insData[0][7] = String.valueOf(eStatus2[i]);
				insData[0][8] = String.valueOf(eDate[i]);
				insData[0][9] = String.valueOf(eId[i]);
				res = getSqlModel().singleExecute(insQuery, insData);
			}

			if(res) {
				showAttendance(bean, request); // Show updated attendance
				return "Record updated successfully!";
			} else {
				showAttendance(bean, request); // Show updated attendance
				return "Record can't be updated!";
			}
		} catch(Exception e) {
			logger.error("Exception in update:" + e);
			return "Record can't be updated!";
		}
	}
}