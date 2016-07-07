package org.paradyne.model.payroll.ot;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ot.ShiftRoster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * Created on 22th Feb 2012.
 * @author aa1385
 */
public class ShiftRosterModel extends ModelBase {
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ShiftRosterModel.class);
	/**
	 * Method : checkForNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		} // end of else
	}
	
	/**This is used to add AssignShift.
	 * @param bean : ShiftRoster
	 * @param request
	 * @return :result
	 */
	public boolean addAssignShift(final ShiftRoster bean, final HttpServletRequest request) {
		boolean result = false;
		try {
			Object[][] addObj = new Object[1][3];
			Object[][] delObj = new Object[1][2];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getEmployeeID();
			addObj[0][1] = bean.getShiftID();
			addObj[0][2] = bean.getFromShiftDate();
			delObj[0][0] = bean.getEmployeeID();
			delObj[0][1] = bean.getFromShiftDate();
			String fromDate = bean.getFromShiftDate();
			String toDate = bean.getToShiftDate();
			if (fromDate == toDate) {
				String deleteQuery = "DELETE FROM HRMS_SHIFT_ROSTER WHERE EMP_ID = ? AND TO_CHAR(SHIFT_ROSTER_DATE,'DD-MM-YYYY') = ?";
				result = getSqlModel().singleExecute(deleteQuery, delObj);
				String insertQuery = "INSERT INTO HRMS_SHIFT_ROSTER"
					+ "(SHIFT_ROSTER_ID, EMP_ID, SHIFT_CODE, SHIFT_ROSTER_DATE)"
					+ " VALUES((SELECT NVL(MAX(SHIFT_ROSTER_ID),0)+1 FROM HRMS_SHIFT_ROSTER),?,?,TO_DATE(?,'DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(insertQuery, addObj);
			} else {
				String date = fromDate;		
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date dt = df.parse(date);
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(dt);
				int DAY_OF_MONTH = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				String[]strFrom = date.split("-");
				String[]strTo = toDate.split("-");
				String fromMonth = strFrom[0];
				String toMonth = strTo[0];
				String calDate = "";
				if (strFrom[1].equals(strTo[1])) {
					int count = Integer.parseInt(strTo[0]) - Integer.parseInt(strFrom[0]) + 1;
					for (int i = 0; i < count; i++) {
						calDate += (Integer.parseInt(strFrom[0]) + i + "-" + strFrom[1] + "-" + strFrom[2] + ", ");
					}
					calDate = calDate.substring(0, calDate.length() - 1);
				} else {
					int countFrom = DAY_OF_MONTH-Integer.parseInt(strFrom[0]) + 1;
					int count = DAY_OF_MONTH-Integer.parseInt(strFrom[0]) + 1;
					count += Integer.parseInt(strTo[0]);
					int cnt = 0;
					for (int i = 0; i < count; i++) {
						if (i < countFrom) {
						calDate += (((Integer.parseInt(strFrom[0]) + i) < 10 ? "0" + (Integer.parseInt(strFrom[0]) + i) : (Integer.parseInt(strFrom[0]) + i)) + "-" + strFrom[1] + "-" + strFrom[2] + ",");
						} else {
							//calDate+=((cnt+1)<10?"0"+(cnt+1):(cnt+1)+"-"+(strTo[1])+"-"+(strTo[2])+",");
							calDate += (((cnt + 1) < 10 ? "0" + (cnt + 1) : (cnt + 1)) + "-" + strTo[1] + "-" + strTo[2] + ", ");
							/*if((cnt+1)<10){
								calDate +="0"+(cnt+1)+"-"+(strTo[1])+"-"+(strTo[2])+",";
							}else{
								calDate += (cnt+1)+"-"+(strTo[1])+"-"+(strTo[2])+",";
							}*/
							cnt++;
						}
					}
					calDate = calDate.substring(0, calDate.length() - 1);
				}
				String[] strarr = calDate.split(",");
				Object[][] addDateObj = new Object[strarr.length][3];
				Object[][] delObj1 = new Object[strarr.length][2];
				for (int i = 0; i < strarr.length; i++) {
					//addObj[0][0] = bean.getHiddenBankId();
					addDateObj[i][0] = bean.getEmployeeID();
					addDateObj[i][1] = bean.getShiftID();
					addDateObj[i][2] = strarr[i];
					delObj1[i][0] = bean.getEmployeeID();
					delObj1[i][1] = strarr[i];
				}
				String deleteQuery = "DELETE FROM HRMS_SHIFT_ROSTER WHERE EMP_ID = ? AND TO_CHAR(SHIFT_ROSTER_DATE,'DD-MM-YYYY') = ? ";
				result = getSqlModel().singleExecute(deleteQuery, delObj1);
				String insertQuery = "INSERT INTO HRMS_SHIFT_ROSTER"
					+ "(SHIFT_ROSTER_ID, EMP_ID, SHIFT_CODE, SHIFT_ROSTER_DATE)"
					+ " VALUES((SELECT NVL(MAX(SHIFT_ROSTER_ID),0)+1 FROM HRMS_SHIFT_ROSTER),?,?,TO_DATE(?,'DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(insertQuery, addDateObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This is used to show ShiftAssignList.
	 * @param bean : ShiftRoster
	 * @param request : request
	 * @param fromDate : fromDate
	 */
	public void showShiftAssignList(final ShiftRoster bean, final HttpServletRequest request, final String fromDate) {
		try {
			String dataQuery = "SELECT EMP_ID,TO_CHAR(SHIFT_ROSTER_DATE,'DD-MM-YYYY'),SHIFT_CODE,TO_CHAR(SHIFT_NAME) "
				 + "FROM HRMS_SHIFT_ROSTER INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_ROSTER.SHIFT_CODE)";
			Object[][]obj = getSqlModel().getSingleResult(dataQuery);
			HashMap<String, String>map = new HashMap<String, String>();
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					map.put(String.valueOf(obj[i][0]) + "#" + String.valueOf(obj[i][1]), String.valueOf(obj[i][2]) + "#" + String.valueOf(obj[i][3]));
				}
			}
			int count = 7;
			String Query = " SELECT distinct HRMS_SHIFT_ROSTER.EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME ,EMP_SHIFT,TO_CHAR(SHIFT_NAME)  "
					+ " FROM HRMS_SHIFT_ROSTER"
					+ " INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SHIFT_ROSTER.EMP_ID)"
					+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC .EMP_SHIFT)  "
					+ "  ";
			if (!bean.getManagerID().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =" + bean.getManagerID() + " ";
			} else {
				Query += "AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =" + bean.getUserEmpId() + " ";
			}
		//	Query += "	ORDER BY SHIFT_ROSTER_ID DESC ";

			Object[][] data = getSqlModel().getSingleResult(Query);
				String dateQuery = "  SELECT TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY') + (2-TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY'),'D')),'DD-MM-YYYY') startdayofweek, "
								+ "  TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY') + (8-TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY'),'D')),'DD-MM-YYYY')"
								+ "   endofweek FROM dual";
				Object[][]dateObj = getSqlModel().getSingleResult(dateQuery);
				if (dateObj != null&& dateObj.length > 0) {
					String frmDate = "";
					String toDate = "";
					bean.setFromDate(String.valueOf(dateObj[0][0]));
					bean.setToDate(String.valueOf(dateObj[0][1]));
					frmDate = String.valueOf(dateObj[0][0]);
					toDate = String.valueOf(dateObj[0][1]);
					String date = frmDate;
					java.text.DateFormat df = new java.text.SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date dt = df.parse(date);
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.setTime(dt);
					int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
					if (data != null && data.length > 0) {
						bean.setModeLength("true");
						bean.setTotalRecords(String.valueOf(data.length));
						String[] pageIndex = Utility.doPaging(bean.getMyPage(),
								data.length, 20);
						if (pageIndex == null) {
							pageIndex[0] = "0";
							pageIndex[1] = "20";
							pageIndex[2] = "1";
							pageIndex[3] = "1";
							pageIndex[4] = "";
						}
						request.setAttribute("totalPage", Integer.parseInt(String
								.valueOf(pageIndex[2])));
						request.setAttribute("pageNo", Integer.parseInt(String
								.valueOf(pageIndex[3])));
						if (pageIndex[4].equals("1")) {
							bean.setMyPage("1");
						}
						ArrayList<Object> list = new ArrayList<Object>();
						for (int i = Integer.parseInt(pageIndex[0]); i < Integer
								.parseInt(pageIndex[1]); i++) {
							ShiftRoster bean1 = new ShiftRoster();
							/*bean1.setIttShiftRosterID(checkNull(String
									.valueOf(data[i][0])));*/
							bean1.setIttEmployeeID(checkNull(String.valueOf(data[i][0])));
							bean1.setIttEmployeeName(checkNull(String.valueOf(data[i][2])));
							bean1.setIttEmployeeToken(checkNull(String.valueOf(data[i][1])));
							ArrayList<Object> dataList = new ArrayList<Object>();
							int cnt = 0;
							for (int j = 0; j < count; j++) {
								ShiftRoster bean2 = new ShiftRoster();
								String[]str = frmDate.split("-");
								int nextDay = (Integer.parseInt(str[0]) + (j));
								int oiginalNextDay = (Integer.parseInt(str[0]) + (j));
								int nextMonth = Integer.parseInt(str[1]);
								if(oiginalNextDay > day_of_month){
									nextDay = 1 + cnt;
									nextMonth = Integer.parseInt(str[1]) + 1;
									cnt++;
								}
								String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
								String noOfStaff = map.get(String.valueOf(data[i][0]) + "#" + newdate);
									if (noOfStaff != null) {
										String[]strValue = noOfStaff.split("#");
									bean2.setIttShiftName(strValue[1]);
									bean2.setIttShiftID(strValue[0]);
									} else {
										bean2.setIttShiftName(String.valueOf(data[i][4]));
										bean2.setIttShiftID(String.valueOf(data[i][3]));
									}
								///bean2.setIttShiftDate(String.valueOf(newdate));
								dataList.add(bean2);
							}
							bean1.setIttDateList(dataList);
							list.add(bean1);
						} // end of loop
						bean.setIteratorlist(list);
					}
					bean.setTotalRecords(String.valueOf(data.length));
					ArrayList<Object> dateList = new ArrayList<Object>();
					bean.setDateList(null);
					String[]dayName = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
					int cnt = 0;
					for (int i = 0; i < count; i++) {
						ShiftRoster bean2 = new ShiftRoster();
						String[]str = frmDate.split("-");
						int nextDay = (Integer.parseInt(str[0]) + (i));
						int oiginalNextDay = (Integer.parseInt(str[0]) + (i));
						int nextMonth = Integer.parseInt(str[1]);
						if (oiginalNextDay > day_of_month) {
							nextDay = 1 + cnt;
							nextMonth = Integer.parseInt(str[1]) + 1;
							cnt++;
						}
						String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
						bean2.setDayName(newdate);
						dateList.add(bean2);
					}
					bean.setDateList(dateList);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to show Shift AssignList Previous.
	 * @param bean : ShiftRoster
	 * @param request
	 * @param fromDate : fromDate
	 */
	public void showShiftAssignListPrev(final ShiftRoster bean, final HttpServletRequest request, final String fromDate) {
		try {
			String dataQuery = "SELECT EMP_ID,TO_CHAR(SHIFT_ROSTER_DATE,'DD-MM-YYYY'),SHIFT_CODE,TO_CHAR(SHIFT_NAME) "
				 + "FROM HRMS_SHIFT_ROSTER INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_ROSTER.SHIFT_CODE)";
			Object[][]obj = getSqlModel().getSingleResult(dataQuery);
			HashMap<String, String>map = new HashMap<String, String>();
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					map.put(String.valueOf(obj[i][0]) + "#" + String.valueOf(obj[i][1]), String.valueOf(obj[i][2]) + "#" + String.valueOf(obj[i][3]));
				}
		}
	int count = 7;
	String Query = " SELECT distinct HRMS_SHIFT_ROSTER.EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME ,EMP_SHIFT,TO_CHAR(SHIFT_NAME) "
			+ " FROM HRMS_SHIFT_ROSTER"
			+ " INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SHIFT_ROSTER.EMP_ID)"
			+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC .EMP_SHIFT)  "
			+ "  ";
		if (!bean.getManagerID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =" + bean.getManagerID() + " ";
		} else {
			Query += "AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =" + bean.getUserEmpId() + " ";
		}
//	Query += "	ORDER BY SHIFT_ROSTER_ID DESC ";
	Object[][] data = getSqlModel().getSingleResult(Query);
		String dateQuery="  SELECT TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY')+ (TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY'),'D')-9),'DD-MM-YYYY') startdayofweek, " 
						+ "  TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY')+ (TO_CHAR(TO_DATE('" + fromDate + "','DD-MM-YYYY'),'D')-3),'DD-MM-YYYY')"
						+ "   endofweek FROM dual";
		Object[][]dateObj=getSqlModel().getSingleResult(dateQuery);
		if (dateObj != null && dateObj.length > 0) {
			String frmDate = "";
			String toDate = "";
			bean.setFromDate(String.valueOf(dateObj[0][0]));
			bean.setToDate(String.valueOf(dateObj[0][1]));
			frmDate = String.valueOf(dateObj[0][0]);
			toDate = String.valueOf(dateObj[0][1]);
			String date = frmDate;
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			java.util.Date dt = df.parse(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dt);
			int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			if (data != null && data.length > 0) {
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						data.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1")) {
					bean.setMyPage("1");
				}
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ShiftRoster bean1 = new ShiftRoster();
					/*bean1.setIttShiftRosterID(checkNull(String
							.valueOf(data[i][0])));*/
					bean1.setIttEmployeeID(checkNull(String.valueOf(data[i][0])));
					bean1.setIttEmployeeName(checkNull(String.valueOf(data[i][2])));
					bean1.setIttEmployeeToken(checkNull(String.valueOf(data[i][1])));
					//bean1.setIttShiftID(checkNull(String.valueOf(data[i][3])));
					//bean1.setIttShiftName(checkNull(String.valueOf(data[i][4])));
					/*bean1
							.setIttFromShiftDate(checkNull(String
									.valueOf(data[i][5])));*/
					
					ArrayList<Object> dataList = new ArrayList<Object>();
					int cnt = 0;
					for (int j = 0; j < count; j++) {
						ShiftRoster bean2 = new ShiftRoster();
						String[]str = frmDate.split("-");
						int nextDay = (Integer.parseInt(str[0]) + (j));
						int oiginalNextDay = (Integer.parseInt(str[0]) + (j));
						int nextMonth = Integer.parseInt(str[1]);
						if (oiginalNextDay > day_of_month) {
							nextDay = 1 + cnt;
							nextMonth = Integer.parseInt(str[1]) + 1;
							cnt++;
						}
						String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
						String noOfStaff = map.get(String.valueOf(data[i][0]) + "#" + newdate);
							if (noOfStaff != null) {
								String[]strValue = noOfStaff.split("#");
								bean2.setIttShiftName(strValue[1]);
								bean2.setIttShiftID(strValue[0]);
							} else {
								bean2.setIttShiftName(String.valueOf(data[i][4]));
								bean2.setIttShiftID(String.valueOf(data[i][3]));
							}
						///bean2.setIttShiftDate(String.valueOf(newdate));
						dataList.add(bean2);
					}
					bean1.setIttDateList(dataList);
					list.add(bean1);
				} // end of loop
				bean.setIteratorlist(list);
			}
			ArrayList<Object> dateList = new ArrayList<Object>();
			bean.setDateList(null);
			String[]dayName = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
			int cnt = 0;
			for (int i = 0; i < count; i++) {
				ShiftRoster bean2 = new ShiftRoster();
				String[]str = frmDate.split("-");
				int nextDay = (Integer.parseInt(str[0]) + (i));
				int oiginalNextDay = (Integer.parseInt(str[0]) + (i));
				int nextMonth = Integer.parseInt(str[1]);
				if (oiginalNextDay > day_of_month) {
					nextDay = 1 + cnt;
					nextMonth = Integer.parseInt(str[1]) + 1;
					cnt++;
				}
				String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
				bean2.setDayName(newdate);
				dateList.add(bean2);
			}
			bean.setDateList(dateList);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to save ShiftAssign List.
	 * @param bean : ShiftRoster
	 * @param request
	 * @return : result
	 */
	public boolean saveShiftAssignList(final ShiftRoster bean, final HttpServletRequest request) {
		boolean result = false;
		try {
				Object[][] taxSlabForMenIDObj = getSqlModel().getSingleResult(
						"SELECT NVL(MAX(HRMS_SHIFT_ROSTER.SHIFT_ROSTER_ID),0)+1 FROM HRMS_SHIFT_ROSTER");
				// Records save for dynamic rows : MEN Start
			//	String[] ittShiftRosterID = request.getParameterValues("ittShiftRosterID");
				String[] ittEmployeeID = request.getParameterValues("ittEmployeeID");
				int count = 7;
				String[] ittShiftID = request.getParameterValues("ittShiftID");
				String[] ittShiftDate = request.getParameterValues("dayName");
				if (ittEmployeeID != null && ittEmployeeID.length > 0) {
				Object[][]obj = new Object[ittEmployeeID.length * count][4];
				Object[][]delObj = new Object[ittEmployeeID.length * count][2];
				int cnt = 0;
				for (int i = 0; i < ittEmployeeID.length; i++) {
					String[] empShiftValue = request.getParameterValues("ittShiftID" + i);
					for (int j = 0; j < count; j++) {
						obj[cnt][0] = (Integer.parseInt(String.valueOf(taxSlabForMenIDObj[0][0])) + cnt + 1);
						obj[cnt][1] = ittEmployeeID[i];	
						delObj[cnt][0] = ittEmployeeID[i];
						obj[cnt][3] = ittShiftDate[j];
						delObj[cnt][1] = ittShiftDate[j];
						if (empShiftValue != null && empShiftValue.length > 0) {
						obj[cnt][2] = empShiftValue[j];
						} else {
							obj[cnt][2] = "0";
						}
						cnt++;
					}
				}
				String deleteQuery = "DELETE FROM HRMS_SHIFT_ROSTER WHERE EMP_ID = ? AND TO_CHAR(SHIFT_ROSTER_DATE,'DD-MM-YYYY') = ?";
				result = getSqlModel().singleExecute(deleteQuery, delObj);
				String insertQuery = "INSERT INTO HRMS_SHIFT_ROSTER"
					+ "(SHIFT_ROSTER_ID, EMP_ID, SHIFT_CODE, SHIFT_ROSTER_DATE)"
					+ " VALUES(?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(insertQuery, obj);
				String delete = "DELETE FROM HRMS_SHIFT_ROSTER WHERE SHIFT_CODE = 0 ";
				result = getSqlModel().singleExecute(delete);
				}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**This is used to get ManagerDetails.
	 * @param empId
	 * @param bean : ShiftRoster
	 */
	public void getManagerDetails(final String empId, final ShiftRoster bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_EMP_OFFC.EMP_ID "
			//	+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_ID"
				+ "	FROM HRMS_EMP_OFFC " //+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
			//	+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
			//	+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";
			Object[][] data = getSqlModel().getSingleResult(query, beanObj);
			bean.setManagerToken(String.valueOf(data[0][0])); //Manager token
			bean.setManagerName(checkNull(String.valueOf(data[0][1]))); // Manager name
			bean.setManagerID(checkNull(String.valueOf(data[0][2]))); // Manager Id
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to get Report.
	 * @param bean : ShiftRoster
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(ShiftRoster bean, HttpServletResponse response,
			HttpServletRequest request, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getRptType();
			rds.setReportType(type);
			String fileName = "Shift Roster Report " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath+fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Shift Roster Report ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(10);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath , session, context, request);
				request.setAttribute("reportPath", reportPath+fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "ShiftRoster_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to Report.
	 * @param rg
	 * @param bean : ShiftRoster
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, ShiftRoster bean) {
		try {
		String fromDate = bean.getFromDate();
			String[] header = null;
			header = new String[9];
			header[0] = "Employee Id";
			header[1] = "Employee Name";
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 30;
					bcellwrap[i] = true;
				} else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i] = true;
				}  else {
					bcellAlign[i] = 0;
					bcellWidth[i] = 30;
					bcellwrap[i] = true;
				}
			}
			final String todate = bean.getToDate(); // To Date
			/* Setting filter details */
			String filters = "Shift Period: " + fromDate + " to " + todate;
			if (!bean.getManagerName().equals("")) {
				filters += "\n\nManager Name: " + bean.getManagerName();
			}
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] {{filters}});
			filterData.setCellAlignment(new int[] {0});
			//filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] {100});
			filterData.setCellColSpan(new int[]{16});
			filterData.setCellNoWrap(new boolean[]{false});
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			Object[][] summaryData = new Object[1][1];
			summaryData[0][0] = "Shift Details:  ";
			int[] cellWidthDateHeader = {100};
			int[] cellAlignDateHeader = {0};
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(summaryData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontStyle(1);
			tableheadingDateData.setCellColSpan(new int[]{16});
			tableheadingDateData.setBorderDetail(0);
			rg.addTableToDoc(tableheadingDateData);

			String dataQuery = "SELECT HRMS_SHIFT_ROSTER.EMP_ID,TO_CHAR(HRMS_SHIFT_ROSTER.SHIFT_ROSTER_DATE,'DD-MM-YYYY'),HRMS_SHIFT_ROSTER.SHIFT_CODE,TO_CHAR(HRMS_SHIFT.SHIFT_NAME) "
					+ "FROM HRMS_SHIFT_ROSTER INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_ROSTER.SHIFT_CODE)";
			Object[][]obj = getSqlModel().getSingleResult(dataQuery);
			HashMap<String, String>map = new HashMap<String, String>();
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					map.put(String.valueOf(obj[i][0]) + "#" + String.valueOf(obj[i][1]), String.valueOf(obj[i][2]) + "#" + String.valueOf(obj[i][3]));
				}
			}
			String Query = " SELECT distinct HRMS_SHIFT_ROSTER.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_SHIFT,TO_CHAR(HRMS_SHIFT.SHIFT_NAME)  "
					+ " FROM HRMS_SHIFT_ROSTER"
					+ " INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SHIFT_ROSTER.EMP_ID)"
					+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC .EMP_SHIFT)  "
					+ "  ";
			if (!bean.getManagerID().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =" + bean.getManagerID() + " ";
			} else {
				Query += "AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getUserEmpId() + " ";
			}
		//	Query += "	ORDER BY SHIFT_ROSTER_ID DESC ";
			Object[][] data = getSqlModel().getSingleResult(Query);
				String dateQuery = "  SELECT TO_CHAR(TO_DATE('"+fromDate+"','DD-MM-YYYY')+ (2-TO_CHAR(TO_DATE('"+fromDate+"','DD-MM-YYYY'),'D')),'DD-MM-YYYY') startdayofweek, " 
								+ "  TO_CHAR(TO_DATE('"+fromDate+"','DD-MM-YYYY')+ (8-TO_CHAR(TO_DATE('"+fromDate+"','DD-MM-YYYY'),'D')),'DD-MM-YYYY')"
								+ "   endofweek FROM dual";
				Object[][]dateObj = getSqlModel().getSingleResult(dateQuery);
				Object[][]empObj=new Object[data.length][9];
				if (dateObj != null && dateObj.length > 0) {
					String frmDate = "";
					String toDate = "";
					bean.setFromDate(String.valueOf(dateObj[0][0]));
					bean.setToDate(String.valueOf(dateObj[0][1]));
					frmDate = String.valueOf(dateObj[0][0]);
					toDate = String.valueOf(dateObj[0][1]);
					String date = frmDate;
					java.text.DateFormat df = new java.text.SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date dt = df.parse(date);
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.setTime(dt);
					int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
					if (data != null && data.length > 0) {
						ArrayList<Object> list = new ArrayList<Object>();
						for (int i = 0; i < data.length; i++) {
							ShiftRoster bean1 = new ShiftRoster();
							/*bean1.setIttShiftRosterID(checkNull(String
									.valueOf(data[i][0])));*/
							bean1.setIttEmployeeID(checkNull(String.valueOf(data[i][0])));
							bean1.setIttEmployeeName(checkNull(String.valueOf(data[i][2])));
							bean1.setIttEmployeeToken(checkNull(String.valueOf(data[i][1])));
							empObj[i][0] = String.valueOf(data[i][1]);
							empObj[i][1] = String.valueOf(data[i][2]);
							int count = 7;
							ArrayList<Object> dataList = new ArrayList<Object>();
							int cnt = 0;
							for (int j = 0; j < count; j++) {
								ShiftRoster bean2 = new ShiftRoster();
								String[]str = frmDate.split("-");
								int nextDay = (Integer.parseInt(str[0]) + (j));
								int oiginalNextDay = (Integer.parseInt(str[0]) + (j));
								int nextMonth = Integer.parseInt(str[1]);
								if (oiginalNextDay > day_of_month) {
									nextDay = 1 + cnt;
									nextMonth = Integer.parseInt(str[1]) + 1;
									cnt++;
								}
								String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
								header[j+2] = newdate;
								String noOfStaff = map.get(String.valueOf(data[i][0]) + "#" + newdate);
									if (noOfStaff !=null) {
										String[]strValue = noOfStaff.split("#");
										bean2.setIttShiftName(strValue[1]);
										empObj[i][j+2] = strValue[1];
									}else{
										empObj[i][j+2] = String.valueOf(data[i][4]);
									}
								dataList.add(bean2);
							}
							bean1.setIttDateList(dataList);
							list.add(bean1);
						}// end of loop
						bean.setIteratorlist(list);
						
						TableDataSet tdstableDebit = new TableDataSet();
						tdstableDebit.setHeader(header);
						tdstableDebit.setHeaderBorderDetail(3);
						tdstableDebit.setData(empObj);
						tdstableDebit.setCellAlignment(bcellAlign);
						tdstableDebit.setCellWidth(bcellWidth);
						tdstableDebit.setBorderDetail(3);
						tdstableDebit.setHeaderTable(true);
						rg.addTableToDoc(tdstableDebit);
						
						Object totalNumberOfEmp[][] = new Object[1][1];
						totalNumberOfEmp[0][0] = "\n\nTotal No of Employees : "+String.valueOf(data.length);
						
						TableDataSet totalCountDataSet = new TableDataSet();
						totalCountDataSet.setData(totalNumberOfEmp);
						totalCountDataSet.setCellWidth(new int[]{100});
						totalCountDataSet.setCellAlignment(new int[]{0});
						//totalCountDataSet.setBodyFontStyle(1);
						totalCountDataSet.setBorderDetail(0);
						rg.addTableToDoc(totalCountDataSet);
					} else {
						TableDataSet noData = new TableDataSet();
						Object[][] noDataObj = new Object[1][1];
						noDataObj[0][0] = "No records available";
						noData.setData(noDataObj);
						noData.setCellAlignment(new int[] { 1 });
						noData.setCellWidth(new int[] { 100 });
						noData.setBorder(false);
						rg.addTableToDoc(noData);
					}
					/*ArrayList<Object> dateList = new ArrayList<Object>();
					bean.setDateList(null);
					String[]DAYNAME={"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
					int cnt=0;
					for (int i = 0; i < COUNT; i++) {
						ShiftRoster bean2 = new ShiftRoster();
						String[]str=frmDate.split("-");
						int nextDay=(Integer.parseInt(str[0])+(i));
						int oiginalNextDay=(Integer.parseInt(str[0])+(i));
						int nextMonth=Integer.parseInt(str[1]);
						if(oiginalNextDay>DAY_OF_MONTH){
							nextDay=1+cnt;
							nextMonth=Integer.parseInt(str[1])+1;
							cnt++;
						}
						String newdate=(nextDay<10?"0"+nextDay:nextDay)+"-"+(nextMonth<10?"0"+nextMonth:nextMonth)+"-"+str[2];
						bean2.setDayName(newdate);
						dateList.add(bean2);
					}
					bean.setDateList(dateList);*/
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
		}
}
