package org.paradyne.model.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.payroll.ot.OtRegister;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.paradyne.lib.ireportV2.ReportDataSet;
import com.itextpdf.text.BaseColor;


/**
 * Created on 29th Feb 2012.
 * @author aa1385
 */
public class OtRegisterModel extends ModelBase {
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OtRegisterModel.class);
	/**
	 * Method : checkForNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNull(final String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		} // end of else
	}
	/**
	 * Method : checkForNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * 
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkForNullValue(String result) {
		if (result == null || result.equals("null")) {
			return "NA";
		} else {
			return result;
		} // end of else
	}
	/**Thisis used to view OtRegDtl List.
	 * @param bean :OtRegister
	 * @param request
	 * @param fromDate
	 */
	public void viewOtRegDtlList(OtRegister bean, HttpServletRequest request,
			String fromDate) {
		try {
			//Query to set manager - reporting employee lengths start
			String mgLengthQuery = "SELECT * FROM HRMS_EMP_OFFC WHERE 1=1  ";
			mgLengthQuery += " AND EMP_STATUS='S' ";
			
			if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
				mgLengthQuery += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
			} else {
				mgLengthQuery += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " ";
			} 
			
			Object[][] mgLengthObj = getSqlModel().getSingleResult(mgLengthQuery);
			bean.setMngDtlLength(String.valueOf(mgLengthObj.length));
			//Query to set manager - reporting employee lengths end 
			
			//Query to set first and last week from given date .
			String dateQuery = "  SELECT TO_CHAR(TO_DATE('" + fromDate
			+ "','DD-MM-YYYY')+ (2-TO_CHAR(TO_DATE('" + fromDate
			+ "','DD-MM-YYYY'),'D')),'DD-MM-YYYY') startdayofweek, "
			+ "  TO_CHAR(TO_DATE('" + fromDate
			+ "','DD-MM-YYYY')+ (8-TO_CHAR(TO_DATE('" + fromDate
			+ "','DD-MM-YYYY'),'D')),'DD-MM-YYYY')"
			+ "   endofweek FROM dual";
			
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			
			String frmDate = "";
			String toDate = "";
			
			bean.setFromDate(String.valueOf(dateObj[0][0]));
			bean.setToDate(String.valueOf(dateObj[0][1]));
			frmDate = String.valueOf(dateObj[0][0]);
			toDate = String.valueOf(dateObj[0][1]);	
			
			// Query to set double ot flag from  HRMS_OT_CONF start
		/*	String query = " SELECT DOUBLE_OT_FLAG FROM HRMS_OT_CONF  WHERE DIV_CODE = " + bean.getDivisionID();
			
			Object[][] doubleOtFlagdata = getSqlModel().getSingleResult(query);
			// setter
			if(doubleOtFlagdata!=null&& doubleOtFlagdata.length>0){
				if(String.valueOf(doubleOtFlagdata[0][0]).equals("Y")){
					bean.setDoubleOTflag("Y"+String.valueOf(doubleOtFlagdata[0][0]));
				}else{
					bean.setDoubleOTflag("N"+String.valueOf(doubleOtFlagdata[0][0]));
				}
			}*/
			bean.setDoubleOTflag("YY");
			// Query to set double ot flag from  HRMS_OT_CONF end
			String doubleOTQuery = "	SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,   HRMS_SHIFT.SHIFT_NAME,TO_CHAR(HRMS_OT_REGISTER.OT_DATE, 'DD-MM-YYYY'), TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'), TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI')"
						+ "	 FROM HRMS_OT_REGISTER "
						+ "	 INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)"
						+ "	 INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)";
						if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
							doubleOTQuery += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = "+bean.getManagerID()+" ";
						} else {
							doubleOTQuery += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = "+bean.getManagerManuallID()+" ";
						}
						if (!bean.getEmployeeID().equals("")) {
							doubleOTQuery += " AND HRMS_EMP_OFFC.EMP_ID IN ("
									+ bean.getEmployeeID() + ") ";
						}
						if (!bean.getEmployeeManuallID().equals("")) {
							doubleOTQuery += " AND HRMS_EMP_OFFC.EMP_ID IN ("
									+ bean.getEmployeeManuallID() + ") ";
						}
						doubleOTQuery += "	 AND HRMS_OT_REGISTER.OT_DATE >=TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY')  "
											+"	AND HRMS_OT_REGISTER.OT_DATE <=TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') "
											+"	 ORDER BY HRMS_OT_REGISTER.EMP_ID ";
		Object[][]doubleOT = getSqlModel().getSingleResult(doubleOTQuery);
		HashMap<String, String>doubleOTmap = new HashMap<String, String>();
		if (doubleOT != null && doubleOT.length > 0) {
			for (int i = 0; i < doubleOT.length; i++) {
				doubleOTmap.put(String.valueOf(doubleOT[i][0]) + "#" + String.valueOf(doubleOT[i][3]), String.valueOf(doubleOT[i][4]) + "#" + String.valueOf(doubleOT[i][5]));
			}
		}
			String dataQuery = "SELECT HRMS_SHIFT_ROSTER.EMP_ID,TO_CHAR(HRMS_SHIFT_ROSTER.SHIFT_ROSTER_DATE,'DD-MM-YYYY'),HRMS_SHIFT_ROSTER.SHIFT_CODE,TO_CHAR(HRMS_SHIFT.SHIFT_NAME) "
				 + " ,TO_CHAR(HRMS_SHIFT.SFT_START_TIME,'HH:MM'), TO_CHAR(HRMS_SHIFT.SFT_END_TIME,'HH:MM') "
				 + "FROM HRMS_SHIFT_ROSTER INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_ROSTER.SHIFT_CODE)";
	Object[][]obj = getSqlModel().getSingleResult(dataQuery);
	HashMap<String, String>map=new HashMap<String, String>();
	if (obj != null && obj.length > 0) {
		for (int i = 0; i < obj.length; i++) {
			map.put(String.valueOf(obj[i][0]) + "#" + String.valueOf(obj[i][1]), String.valueOf(obj[i][2]) + "#" + String.valueOf(obj[i][3]) + "#" + String.valueOf(obj[i][4]) + "#" + String.valueOf(obj[i][5]));
		}
	}
	/**
	 * EMPLOYEE ACTUAL IN AND OUT TIME
	 */
	String year = fromDate.substring(6);
	String inOutQuery = "SELECT TO_CHAR(HRMS_DAILY_ATTENDANCE_" + year +".ATT_DATE,'DD-MM-YYYY'), HRMS_DAILY_ATTENDANCE_" + year +".ATT_EMP_ID,  TO_CHAR(HRMS_DAILY_ATTENDANCE_" + year +".ATT_LOGIN,'HH24:MI'), TO_CHAR(HRMS_DAILY_ATTENDANCE_" + year +".ATT_LOGOUT,'HH24:MI'),TO_CHAR(HRMS_DAILY_ATTENDANCE_" + year +".ATT_OT,'HH24:MI') FROM HRMS_DAILY_ATTENDANCE_" + year +" "
						+ "	WHERE HRMS_DAILY_ATTENDANCE_" + year +".ATT_DATE >= TO_DATE('" + bean.getFromDate() + "','DD-MM-YYYY') "
						+ "	AND HRMS_DAILY_ATTENDANCE_" + year +".ATT_DATE <= TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY')";
		Object[][]attnObj = getSqlModel().getSingleResult(inOutQuery);
		HashMap<String, String>attnMap = new HashMap<String, String>();
		if (attnObj!=null && attnObj.length > 0 ){
			for (int i = 0; i < attnObj.length; i++) {
				attnMap.put(String.valueOf(attnObj[i][1]) + "#" + String.valueOf(attnObj[i][0]), String.valueOf(attnObj[i][2]) + "#" + String.valueOf(attnObj[i][3]) + "#" + String.valueOf(attnObj[i][4]));
			}
		}
	String Query = null;
	if (bean.getHiddenCheckBoxFlag().equals("S")) {
		Query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_SHIFT,HRMS_SHIFT.SHIFT_NAME, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID , TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI'), TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI') " 
				+ "	FROM HRMS_EMP_OFFC  INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT)"  
				+ "		WHERE 1=1 ";
				if (!bean.getManagerID().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ="
							+ bean.getManagerID() + " ";
					} 
				if (!bean.getEmployeeID().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
							+ bean.getEmployeeID()+") ";
				}
					if (!bean.getEmployeeManuallID().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
							+ bean.getEmployeeManuallID()+") ";
				}
					Query+=" ORDER BY HRMS_EMP_OFFC.EMP_ID  ";
		} else {
		Query = " SELECT DISTINCT EMP_SHIFT,HRMS_SHIFT.SHIFT_NAME, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID , TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI'), TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI') "
			+ " FROM HRMS_EMP_OFFC "
			+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) "
			+ " INNER JOIN HRMS_OT_REGISTER ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID ) WHERE 1=1";
		
			if (!bean.getManagerID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ="
					+ bean.getManagerID() + " ";
			} /*else {
			Query += "AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ="
					+ bean.getUserEmpId() + " ";
			}*/
			//	Query += "	ORDER BY SHIFT_ROSTER_ID DESC ";
			if (!bean.getEmployeeID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
					+ bean.getEmployeeID() + ") ";
			}
			if (!bean.getEmployeeManuallID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
					+ bean.getEmployeeManuallID() + ") ";
			}
			Query += "	 AND OT_DATE >=TO_DATE('" + bean.getFromDate() + "','DD-MM-YYYY')  "
			+ "	AND OT_DATE <=TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY') "
			+ "	 ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		}
			Object[][] data = getSqlModel().getSingleResult(Query);
			
			if (dateObj != null && dateObj.length > 0) {
				
				
				String date = frmDate;
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date dt = df.parse(date);
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(dt);
				int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				if (data != null && data.length > 0) {
					bean.setViewOtRegisterDtlFlag(true);
					bean.setModeLength("true");
					bean.setTotalRecords(String.valueOf(data.length));
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							data.length, 2);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "2";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					request.setAttribute("totalPage", Integer.parseInt(String
							.valueOf(pageIndex[2])));
					request.setAttribute("pageNo", Integer.parseInt(String
							.valueOf(pageIndex[3])));
					if (pageIndex[4].equals("1"))
						bean.setMyPage("1");
				
					final List<OtRegister> initialList = new ArrayList<OtRegister>();
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						//ArrayList<Object> dataList = new ArrayList<Object>();
						int count = 7;
						int cnt = 0;
						for (int j = 0; j < count; j++) {
							//ShiftRoster bean2 = new ShiftRoster();
							String[]str = frmDate.split("-");
							int nextDay = (Integer.parseInt(str[0]) + (j));
							int oiginalNextDay = (Integer.parseInt(str[0]) + (j));
							int nextMonth = Integer.parseInt(str[1]);
							if (oiginalNextDay > day_of_month) {
								nextDay = 1 + cnt;
								nextMonth = Integer.parseInt(str[1]) + 1;
								cnt++;
							}
							OtRegister bean1 = new OtRegister();
							bean1.setIttShiftID(checkNull(String.valueOf(data[i][0])));
							bean1.setIttShiftName(checkNull(String.valueOf(data[i][1])));
							bean1.setIttEmployeeName(checkNull(String.valueOf(data[i][2])));
							bean1.setIttEmployeeID(checkNull(String.valueOf(data[i][3])));
							bean1.setIttShiftIn(checkForNullValue(String.valueOf(data[i][4])));
							bean1.setIttShiftOut(checkForNullValue(String.valueOf(data[i][5])));
							String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
							String noOfStaff = map.get(String.valueOf(data[i][3]) + "#" + newdate);
							String doubleOTStr = doubleOTmap.get(String.valueOf(data[i][3]) + "#" + newdate);
							if (noOfStaff != null) {
								String[]strValue = noOfStaff.split("#");
								bean1.setIttShiftID(strValue[0]);
								bean1.setIttShiftName(strValue[1]);
								bean1.setIttShiftIn(checkForNullValue(strValue[2]));
								bean1.setIttShiftOut(checkForNullValue(strValue[3]));
							}
							if (doubleOTStr != null) {
								String[]strValue = doubleOTStr.split("#");
								bean1.setIttSingleApprOt(checkNull(strValue[0]));
								bean1.setIttDoubleApprOt(checkNull(strValue[1]));
							} else {
								bean1.setIttSingleApprOt("0.00");
								bean1.setIttDoubleApprOt("0.00");
							}
							String attnData = attnMap.get(String.valueOf(data[i][3]) + "#" + newdate);
							if (attnData !=null) {
								String[]strValue = attnData.split("#");
								bean1.setIttActualIn(checkNull(strValue[0]));
								bean1.setIttActualOut(checkNull(strValue[1]));
								bean1.setIttSingleOt(checkForNullValue(strValue[2]));
							} else {
								bean1.setIttActualIn("NA");
								bean1.setIttActualOut("NA");
								bean1.setIttSingleOt("NA");
							}
							bean1.setIttDate(newdate);
							initialList.add(bean1);
						}
						//bean.setIttDateList(List);
						//List.add(bean1);
					} // end of loop
					bean.setIteratorlist(initialList);
					bean.setTotalNoEmp(String.valueOf(data.length));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**Method : generateTemplate.
	 * Purpose : This function is used to create a template for uploading OT details & to
	 *  generate report.
	 * @param bonusBean - bean
	 * @param response - response
	 * @param type - Determines whether to generate template or generate report
	 */
	public void generateTemplate(final OtRegister bean, final HttpServletResponse response, final String type, final String templateName) {
		try {
			String fileName = "";
			if ("Manual".equals(templateName)) {
				fileName = "Template for uploading OT";
			} else if ("System".equals(templateName)) {
				fileName = "Template for uploading OT";
			} else {
				fileName = "OT Report";
			}
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			if (type.equals("TEMPLATE")) {
				rg = getTemplate(rg, bean, templateName, "underProcessTemplate");
			} else if (type.equals("PROCESSEDRATINGS")) {
				rg = getTemplate(rg, bean, templateName, "processedRatingsTemplate");
			} else if (type.equals("PROCESSEDBONUS")) {
				rg = getTemplate(rg, bean, templateName, "processedBonusTemplate");
			} 
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to report.
	 * @param rg
	 * @param bean :OtRegister
	 * @param templateName
	 * @param typeOfTemplate
	 * @return
	 */
	private ReportGenerator getTemplate(final ReportGenerator rg,
			final OtRegister bean, final String templateName, final String typeOfTemplate) {
		try {
			if (bean.getDoubleOTflag().equals("YY")) {
				Object[][] header = new Object[2][6];
				header[0][0] = "Employee Token";
				header[1][0] = "*";
				header[0][1] = "Employee Name";
				header[1][1] = "*";
				header[0][2] = "Shift";
				header[1][2] = "";
				header[0][3] = "Date";
				header[1][3] = "";
				if ("Manual".equals(templateName)) {
					header[0][4] = "Approved Single OT Hours";
					header[1][4] = "*(HH:MM)";
					header[0][5] = "Approved Double OT Hours";
					header[1][5] = "*(HH:MM)";
				}			
				if ("System".equals(templateName)) {
					header[0][4] = "Approved Single OT Hours";
					header[1][4] = "*(HH:MM)";
					header[0][5] = "Approved Double OT Hours";
					header[1][5] = "*(HH:MM)";
				}			
				int[] alignment = new int[header[0].length];
				int[] cellwidth = new int[header[0].length];
				for (int i = 0; i < header[0].length; i++) {
					alignment[i] = 0;
					cellwidth[i] = 30;
				}			
				Object[][] dataObject = null;			
				if ("processedRatingsTemplate".equals(typeOfTemplate)) {
					dataObject = getProcessedRecords(bean, "selectRatings");
				} else if ("processedBonusTemplate".equals(typeOfTemplate)) {
					//dataObject = getProcessedRecords(bean, "selectBonus");
				} else {
					dataObject = getSelectedFilterWiseEmployees(bean);
				}
				Object[][] color = new Object[dataObject.length][header[0].length];
				if (color != null && color.length > 0) {
					for (int i = 0; i < color.length; i++) {
						for (int j = 0; j < color[0].length; j++) {
							color[i][0] = Utility.WHITE;
							color[i][1] = Utility.WHITE;
							color[i][2] = Utility.WHITE;
							color[i][3] = Utility.WHITE;
							color[i][4] = Utility.WHITE;
							color[i][5] = Utility.WHITE;
						}
					}
				}
				rg.tableBodyWithColor(header, dataObject, cellwidth, alignment, color);
			} else {
				Object[][] header = new Object[2][5];
				header[0][0] = "Employee Token";
				header[1][0] = "*";
				header[0][1] = "Employee Name";
				header[1][1] = "*";
				header[0][2] = "Shift";
				header[1][2] = "";
				header[0][3] = "Date";
				header[1][3] = "";
				if ("Manual".equals(templateName)) {
					header[0][4] = "Approved Single OT Hours";
					header[1][4] = "*(HH:MM)";
				}
				if ("System".equals(templateName)) {
					header[0][4] = "Approved Single OT Hours";
					header[1][4] = "*(HH:MM)";
				}
				int[] alignment = new int[header[0].length];
				int[] cellwidth = new int[header[0].length];
				for (int i = 0; i < header[0].length; i++) {
					alignment[i] = 0;
					cellwidth[i] = 30;
				}
				Object[][] dataObject = null;
				if ("processedRatingsTemplate".equals(typeOfTemplate)) {
					dataObject = getProcessedRecords(bean, "selectRatings");
				} else if ("processedBonusTemplate".equals(typeOfTemplate)) {
					//dataObject = getProcessedRecords(bean, "selectBonus");
				} else {
					dataObject = getSelectedFilterWiseEmployees(bean);
				}
				/*Object[][] color =null;		
				if(color != null && color.length > 0){*/
				Object[][] color = new Object[dataObject.length][header[0].length];
				rg.tableBodyWithColor(header, dataObject, cellwidth, alignment, color);
				/*}else{
					//rg.tableBodyWithColor(header, dataObject, cellwidth, alignment, color);
					rg.addText("No Records to Display",  0, 1, 0);
				}*/
				if (color != null && color.length > 0) {
					for (int i = 0; i < color.length; i++) {
						for (int j = 0; j < color[0].length; j++) {
							color[i][0] = Utility.WHITE;
							color[i][1] = Utility.WHITE;
							color[i][2] = Utility.WHITE;
							color[i][3] = Utility.WHITE;
							color[i][4] = Utility.WHITE;
						}
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * Method : getSelectedFilterWiseEmployees. Purpose : This method adds
	 * employees as per the filter to the excel table.
	 * @param bean - OtRegister
	 * @return Object
	 */
	public final Object[][] getSelectedFilterWiseEmployees(final OtRegister bean) {
		Object[][] finalObject = null;
		try {
			String dataQuery="SELECT HRMS_SHIFT_ROSTER.EMP_ID,TO_CHAR(HRMS_SHIFT_ROSTER.SHIFT_ROSTER_DATE,'DD-MM-YYYY'),HRMS_SHIFT_ROSTER.SHIFT_CODE,TO_CHAR(HRMS_SHIFT.SHIFT_NAME) "
				 + "FROM HRMS_SHIFT_ROSTER INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_SHIFT_ROSTER.SHIFT_CODE)";
				Object[][]obj = getSqlModel().getSingleResult(dataQuery);
				HashMap<String, String>map = new HashMap<String, String>();
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						map.put(String.valueOf(obj[i][0]) + "#" + String.valueOf(obj[i][1]), String.valueOf(obj[i][2]) + "#" + String.valueOf(obj[i][3]));
					}
				}
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),HRMS_SHIFT.SHIFT_NAME "
					+ " FROM HRMS_EMP_OFFC INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT)"
					+ " WHERE 1=1 ";
					/*if (!bean.getDivisionID().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV =" + bean.getDivisionID();
					}*/
					if (!bean.getBranchCode().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(" + bean.getBranchCode()+")";
					}
					if (!bean.getPaybillId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + bean.getPaybillId()+")";
					}
					if (!bean.getDepartmentID().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT IN(" + bean.getDepartmentID()+")";
					}
					if (!bean.getEmployeeManuallID().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_ID IN(" + bean.getEmployeeManuallID() + ")";
					}
					query += " AND HRMS_EMP_OFFC.EMP_STATUS='S'  ";
					if (!bean.getManagerManuallID().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ="
								+ bean.getManagerManuallID() + " ";
					} else {
						query += "AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ="
								+ bean.getUserEmpId() + " ";
					}
			 		/// " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + bean.getToMonth() + "-"+ bean.getToYear() + "','MM-YYYY')) " + 
					query +=  " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
				Object[][] empDataObj = getSqlModel().getSingleResult(query);
				String  fromDate = bean.getFromDate();
				String dateQuery = "  SELECT TO_CHAR(TO_DATE('" + fromDate
						+ "','DD-MM-YYYY')+ (2-TO_CHAR(TO_DATE('" + fromDate
						+ "','DD-MM-YYYY'),'D')),'DD-MM-YYYY') startdayofweek, "
						+ "  TO_CHAR(TO_DATE('" + fromDate
						+ "','DD-MM-YYYY')+ (8-TO_CHAR(TO_DATE('" + fromDate
						+ "','DD-MM-YYYY'),'D')),'DD-MM-YYYY')"
						+ "   endofweek FROM dual";
				Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
				if (dateObj!=null && dateObj.length > 0) {
					String frmDate = "";
					String toDate = "";
					bean.setFromDate(String.valueOf(dateObj[0][0]));
					bean.setToDate(String.valueOf(dateObj[0][1]));
					frmDate=String.valueOf(dateObj[0][0]);
					toDate=String.valueOf(dateObj[0][1]);
					String date = frmDate;
					java.text.DateFormat df = new java.text.SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date dt = df.parse(date);
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.setTime(dt);
					int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
					if (empDataObj != null && empDataObj.length > 0) {
						finalObject = new Object[empDataObj.length*7][6];
						int counter = 0;
						ArrayList<Object> list = new ArrayList<Object>();
						for (int i = 0; i < empDataObj.length; i++) {
							//ArrayList<Object> dataList = new ArrayList<Object>();
							int count = 7;
							int cnt = 0;
							for (int j = 0; j < count; j++) {
								//ShiftRoster bean2 = new ShiftRoster();
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
								String noOfStaff = map.get(String.valueOf(empDataObj[i][0]) + "#" + newdate);
								finalObject[counter][0] = empDataObj[i][1];//emp token 
								finalObject[counter][1] = empDataObj[i][2];//emp name
								finalObject[counter][2] = empDataObj[i][3];//SHIFT NAME
								if (noOfStaff !=null) {
									String[]strValue = noOfStaff.split("#");									
									finalObject[counter][2] = strValue[1];
								}
								finalObject[counter][3] = newdate;
								finalObject[counter][4] = "";
								finalObject[counter][5] = "";
								//bean1.setIttDate(newdate);				
								//List.add(bean1);
								counter++;
							}
							//bean.setIttDateList(List);
							//List.add(bean1);
						}// end of loop
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalObject;
	}
	/**Method : uploadBonusAllowanceData.
	 *Purpose  : This method is used to manually upload OT
	 * @param response : responsed
	 * @param request : request
	 * @param bean : OtRegister
	 * @return boolean
	 */
	public boolean uploadBonusAllowanceData(HttpServletResponse response,
			HttpServletRequest request, OtRegister bean) {
		boolean finalReturnResult = false;
		try {
			bean.setFileNameManuallyUploadedOtAllowance(bean.getFileNameForManuallyCalculatedOtAllowance());
			String filePath = bean.getDataPath() + "" + bean.getFileNameForManuallyCalculatedOtAllowance();
		///	bean.setTempFileNameManuallyUploadedOt("ManuallyUploadedOt");
			MigratePayrollExcelData.getFile(filePath);
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigratePayrollExcelData
					.isColumnsMandatory();

			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') FROM HRMS_EMP_OFFC WHERE 1 = 1 ";

			/*if(!bonusBean.getDivisionID().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DIV IN("+bonusBean.getDivisionID()+")";
			}*/
			if (!bean.getBranchCode().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_CENTER IN("+bean.getBranchCode()+")";
			}
			if (!bean.getPaybillId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL IN("+bean.getPaybillId()+")";
			} 
			if(!bean.getDepartmentID().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT IN("+bean.getDepartmentID()+")";
			}
			if(!bean.getEmployeeManuallID().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_ID IN("+bean.getEmployeeManuallID()+")";
			} 
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='S'  " +
	 		       //  " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + bean.getToMonth() + "-"+ bean.getToYear() + "','MM-YYYY')) " +
	 		         " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			Object[][] employeeMaster = getSqlModel().getSingleResult(query);
			
			
			String shiftQuery = " SELECT HRMS_SHIFT.SHIFT_ID, NVL(HRMS_SHIFT.SHIFT_NAME,' ') FROM HRMS_SHIFT WHERE 1 = 1 ";
			Object[][] shiftMaster = getSqlModel().getSingleResult(shiftQuery);

			Object[][] employeeTokenObj = null;
			Object[][] empNameObj = null;
			if (employeeMaster != null && employeeMaster.length > 0) {
				employeeTokenObj = MigratePayrollExcelData.uploadExcelData(1, employeeMaster, MigratePayrollExcelData.MASTER_TYPE, columnInformation.get(1));
				//empNameObj = MigratePayrollExcelData.uploadExcelData(2, null, MigratePayrollExcelData.STRING_TYPE, columnInformation.get(2));
			}
			Object[][] shiftIdObj = null;
			Object[][] shiftNameObj = null;
			if (shiftMaster != null && shiftMaster.length > 0) {
				shiftIdObj = MigratePayrollExcelData.uploadExcelData(3, shiftMaster, MigratePayrollExcelData.MASTER_TYPE, columnInformation.get(1));
			//	shiftNameObj = MigratePayrollExcelData.uploadExcelData(3, null, MigratePayrollExcelData.STRING_TYPE, columnInformation.get(2));
			}
			Object[][] dateOtObj = MigratePayrollExcelData.uploadExcelData(4, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(4));
			Object[][] apprSingleOtObj = MigratePayrollExcelData.uploadExcelData(5, null, MigratePayrollExcelData.HOURS_TYPE, columnInformation.get(4));
			Object[][] apprDoubleOtObj=null;
			apprDoubleOtObj = MigratePayrollExcelData.uploadExcelData(6, null, MigratePayrollExcelData.HOURS_TYPE, columnInformation.get(4));
			/* Checks if the values are in proper format */
			boolean result = true;//MigratePayrollExcelData.isFileToBeUploaded();
			if (result) {
				boolean insertUpdateHdrDataFlag = true;
					String maxBonusHdrCodeQuery = "SELECT NVL(MAX(HRMS_OT_REGISTER.OT_ID),0)+1 FROM HRMS_OT_REGISTER";
					Object[][] maxBonusHdrCodeObj = getSqlModel().getSingleResult(maxBonusHdrCodeQuery);
					if(maxBonusHdrCodeObj != null && maxBonusHdrCodeObj.length > 0){
						bean.setOtRegisterID(String.valueOf(maxBonusHdrCodeObj[0][0]));
					} 
					
				if (insertUpdateHdrDataFlag) {
					String bonusCode = bean.getOtRegisterID();
					
					boolean insertResult;
					if(employeeTokenObj != null && employeeTokenObj.length > 0 ){
						
						Object [][] finalEmpBonusObj = new Object[employeeTokenObj.length][6];
						Object [][] finalDeleteObj = new Object[employeeTokenObj.length][2];
						for (int i = 0; i < employeeTokenObj.length; i++) {
						//First delete those records which are available in HRMS_BONUS_EMP   	
							finalDeleteObj [i][0] = String.valueOf(employeeTokenObj[i][0]);
							finalDeleteObj[i][1] = "00-00-0000";
							
							finalEmpBonusObj[i][0] = bonusCode;
							finalEmpBonusObj[i][1] = String.valueOf(employeeTokenObj[i][0]);
							if (shiftIdObj != null && shiftIdObj.length > 0) {
								finalEmpBonusObj[i][2] = String.valueOf(shiftIdObj[i][0]);
								
							} else {
								finalEmpBonusObj[i][2] =" ";
							}
							
							if (dateOtObj != null && dateOtObj.length > 0) {
								finalEmpBonusObj[i][3] = String.valueOf(dateOtObj[i][0]);
								finalDeleteObj[i][1] = String.valueOf(dateOtObj[i][0]);
							} else {
								finalEmpBonusObj[i][3] =" ";
							}
							
							if (apprSingleOtObj != null && apprSingleOtObj.length > 0) {
								finalEmpBonusObj[i][4] = String.valueOf(apprSingleOtObj[i][0]);
							} else {
								finalEmpBonusObj[i][4] =" ";
							}
							if (apprDoubleOtObj != null && apprDoubleOtObj.length > 0) {
								finalEmpBonusObj[i][5] = String.valueOf(apprDoubleOtObj[i][0]);
							} else {
								finalEmpBonusObj[i][5] =" ";
							}
							
							/*for (int l = 0; l < finalEmpBonusObj.length; l++) {
								for (int j = 0; j < finalEmpBonusObj[l].length; j++) {
									logger.info("otConfigSettingDetails[" + l + "][" + j
											+ "]  " + finalEmpBonusObj[l][j]);
								}
							}*/
						}
						
						boolean deleteResult = getSqlModel().singleExecute(getQuery(1), finalDeleteObj);
						if (deleteResult) {
							insertResult = getSqlModel().singleExecute(getQuery(2), finalEmpBonusObj);
							if (insertResult) {
								bean.setStatus("Success");
								bean.setNote("Data uploaded successfully, Please verify the same.");
								finalReturnResult = true;
							} else {
								bean.setNote("Error inserting data");
							}
							/*String updateBonusTotalAmountQuery = "UPDATE HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_TOTAL_AMT = " +
							                                     "(SELECT SUM(EMP_BONUS_AMT) FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusCode + " )" + 
							                                     " WHERE HRMS_BONUS_HDR.BONUS_CODE = " + bonusCode;
							getSqlModel().singleExecute(updateBonusTotalAmountQuery);
							
							String updateBonusEmployeeCountQuery = "UPDATE HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_EMP_COUNT = " + 
																   "(SELECT COUNT(*) FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = "+ bonusCode + ") "+ 
																   " WHERE HRMS_BONUS_HDR.BONUS_CODE = " +bonusCode;
							getSqlModel().singleExecute(updateBonusEmployeeCountQuery);*/
							
						} else {
							bean.setStatus("Fail");
							bean.setNote("Error inserting data");
							finalReturnResult = false;
						}
					}
				} 
			} else {
				bean.setStatus("Fail");
				bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				finalReturnResult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			finalReturnResult = false;
		}
		bean.setDisplayNoteFlag(true);
		return finalReturnResult;
	}
	/**Method : getProcessedRatingsRecords.
	 * Purpose : This method is used to view processed Records
	 * @param bean : OtRegister
	 * @return Object
	 */
	private Object[][] getProcessedRecords(OtRegister bean, String columnToSelect) {
		Object[][] finalObject = null;
		try {
			String query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
					+ " HRMS_SHIFT.SHIFT_NAME,TO_CHAR(HRMS_OT_REGISTER.OT_DATE, 'DD-MM-YYYY'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),'00:00'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI'),'00:00') "
					+ "  FROM HRMS_OT_REGISTER "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)"
					+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)"
					+ " WHERE EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " "
					+ " AND HRMS_OT_REGISTER.OT_DATE >= TO_DATE('" + bean.getFromDate() + "','DD-MM-YYYY')  "
					+ "	AND HRMS_OT_REGISTER.OT_DATE <= TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY') "			
					+ " ORDER BY HRMS_OT_REGISTER.EMP_ID " ;
			Object[][] empDataObj = getSqlModel().getSingleResult(query);
			if (empDataObj != null && empDataObj.length > 0) {
				finalObject = new Object[empDataObj.length][6];
				if (finalObject != null && finalObject.length > 0) {
					finalObject[0][0] = "No DATA";
				}
				for (int i = 0; i < finalObject.length; i++) {
					for (int j = 0; j < finalObject[0].length; j++) {
						finalObject[i][j] = String.valueOf(empDataObj[i][j]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalObject;
	}
	/**Thisis used to get ManagerDetails.
	 * @param empId
	 * @param bean : OtRegister
	 */
	public void getManagerDetails(final String empId, final OtRegister bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_DIV "
			//	+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_ID"
				+ "	FROM HRMS_EMP_OFFC " //+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
			//	+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
			//	+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";
			Object[][] data = getSqlModel().getSingleResult(query, beanObj);
			if (data!=null && data.length > 0 ) {
					bean.setManagerID(String.valueOf(data[0][0]));//Manager token
					bean.setManagerToken(String.valueOf(data[0][1]));//Manager token
					bean.setManagerName(checkNull(String.valueOf(data[0][2])));// Manager name
					bean.setManagerManuallID(String.valueOf(data[0][0]));//Manager token
					bean.setManagerManuallToken(String.valueOf(data[0][1]));//Manager token
					bean.setManagerManuallName(checkNull(String.valueOf(data[0][2])));// Manager name
					bean.setDivisionID(String.valueOf(data[0][3]));
			}
		} catch(final Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used save OtDetailsList.
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean saveOtDetailsList(OtRegister bean, HttpServletRequest request) {
		boolean result = false;
		try {
				Object otDetailsIDObj[][] = getSqlModel().getSingleResult(
						"SELECT NVL(MAX(HRMS_OT_REGISTER.OT_ID),0)+1 FROM HRMS_OT_REGISTER");
				int otIncrementedID = Integer.parseInt(""
						+ otDetailsIDObj[0][0]);
				String[] ittShiftID = request.getParameterValues("ittShiftID");
				String[] ittEmployeeID = request.getParameterValues("ittEmployeeID");
				String[] ittDate = request.getParameterValues("ittDate");
				String[] ittSingleApprOt = request.getParameterValues("ittSingleApprOt");
				String[] ittDoubleApprOt = request.getParameterValues("ittDoubleApprOt");
				if (ittEmployeeID != null && ittEmployeeID.length > 0) {
					Object[][]delObj=new Object[ittEmployeeID.length][2];
					if (ittEmployeeID != null) {
						Object modParam[][] = new Object[ittEmployeeID.length][6];
						for (int i = 0; i < ittEmployeeID.length; i++) {
							 modParam[i][0] = otIncrementedID;
							modParam[i][1] = ittShiftID[i];
							modParam[i][2] = checkNull(String.valueOf(ittEmployeeID[i]));
							modParam[i][3] = ittDate[i];
							modParam[i][4] = ittSingleApprOt[i];
							modParam[i][5] = ittDoubleApprOt[i];
	
							delObj[i][0]=checkNull(String.valueOf(ittEmployeeID[i]));
							delObj[i][1]=ittDate[i];
						}
						String deleteQuery="DELETE FROM HRMS_OT_REGISTER WHERE HRMS_OT_REGISTER.EMP_ID = ? " +
								"AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'DD-MM-YYYY') = ?";
						result = getSqlModel().singleExecute(deleteQuery, delObj);
						
						String insertQuery = "INSERT INTO HRMS_OT_REGISTER(HRMS_OT_REGISTER.OT_ID, " +
								" HRMS_OT_REGISTER.OT_SHIFT_CODE, HRMS_OT_REGISTER.EMP_ID, " +
								" HRMS_OT_REGISTER.OT_DATE,  HRMS_OT_REGISTER.APPR_SINGLE_OT, " +
								" HRMS_OT_REGISTER.APPR_DOUBLE_OT) " +
								" VALUES (?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'))";
						result = getSqlModel().singleExecute(insertQuery, modParam);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**This is used to show report.
	 * @param bean
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(OtRegister bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "OT Register Report" + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("OT Register Report");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("A4");
			///rds.setPageSize("TABLOID");
			rds.setPageOrientation("portrait");
			rds.setTotalColumns(6);
			/*
			 * rds.setMarginBottom(25f); rds.setMarginLeft(25f);
			 * rds.setMarginRight(25f);
			 */
			rds.setShowPageNo(true);
			// rds.setMarginTop(25f);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"OtCalculations_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment */
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to show report.
	 * @param rg
	 * @param bean : OtRegister
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(final 
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			final OtRegister bean) {
		try {
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			/* Setting filter details */
			String filters = "Period: " + fromDate + " to " + toDate + "";
			if (!bean.getManagerName().equals("")) {
				filters += "\n\nManager : " + bean.getManagerName();
			}
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] {{filters}});
			filterData.setCellAlignment(new int[] {0});
			//filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] {100});
			filterData.setCellColSpan(new int[]{10});
			filterData.setCellNoWrap(new boolean[]{false});
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			String divisionCode =  bean.getDivisionID();
			/*
			 * select query to select approver comment data from
			 * hrms_loan_path
			 */
			String Query = null;
			if (bean.getHiddenCheckBoxFlag().equals("S")) {
				Query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
					 +" HRMS_SHIFT.SHIFT_NAME,TO_CHAR(HRMS_OT_REGISTER.OT_DATE, 'DD-MM-YYYY'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),'00:00'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI'),'00:00') "
					+ "  FROM HRMS_OT_REGISTER "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)"
					+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)";
					
					if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
						Query += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
					} else {
						Query += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " ";
					} 
					if (!bean.getEmployeeID().equals("")) {
						Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
								+ bean.getEmployeeID() + ") ";
					}
					if (!bean.getEmployeeManuallID().equals("")) {
						Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
								+ bean.getEmployeeManuallID() + ") ";
					}
					Query += "	 AND HRMS_OT_REGISTER.OT_DATE >=TO_DATE('" + bean.getFromDate() + "','DD-MM-YYYY')  "
						+ "	AND HRMS_OT_REGISTER.OT_DATE <=TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY') "
						+ "	 ORDER BY HRMS_OT_REGISTER.EMP_ID ";
				} else {
				  Query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
					    + " SHIFT_NAME,TO_CHAR(HRMS_OT_REGISTER.OT_DATE, 'DD-MM-YYYY'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),'00:00'), NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI'),'00:00') "
						+ "  FROM HRMS_OT_REGISTER "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)"
						+ " INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)";
	
					if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
						Query += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
					} else {
						Query += "	 WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " ";
					}
					if (!bean.getEmployeeID().equals("")) {
						Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
								+ bean.getEmployeeID() + ") ";
					}
					if (!bean.getEmployeeManuallID().equals("")) {
						Query += " AND HRMS_EMP_OFFC.EMP_ID IN ("
								+ bean.getEmployeeManuallID() + ") ";
					}
					Query+="	 AND HRMS_OT_REGISTER.OT_DATE >=TO_DATE('" + bean.getFromDate() + "','DD-MM-YYYY')  "
										+"	AND OT_DATE <=TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY') "
										+"	 ORDER BY HRMS_OT_REGISTER.EMP_ID ";
			}
								/*
			 * execute select query and set the comment data
			 */
			Object[][] comment = getSqlModel().getSingleResult(
					Query);
			String[] header = null;
			if (bean.getDoubleOTflag().equals("YY")) {
				header = new String[6];
			} else {
				header = new String[5];
			}
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
		//	header[0] = "Sr. No.";
			header[0] = "Employee ID";
			header[1] = "Employee Name";
			header[2] = "Shift";
			header[3] = "Date";
			header[4] = "Approved Single OT Hours";
			if(bean.getDoubleOTflag().equals("YY")){
				header[5] = "Approved Double OT Hours";
			}
			for (int i = 0; i < header.length; i++) {
				if (i == 4) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 30;
					bcellwrap[i] = false;
				} else {
					bcellAlign[i] = 0;
					bcellWidth[i] = 30;
					bcellwrap[i] = true;
				}
				if (bean.getDoubleOTflag().equals("YY")) {
					if (i == 5) {
						bcellAlign[i] = 2;
						bcellWidth[i] = 30;
						bcellwrap[i] = false;
					}
				}
			}
			Object[][] objTotalTabularData = new Object[comment.length][header.length];
			if (comment != null && comment.length > 0) {
				for (int j = 0; j < comment.length; j++) {
					objTotalTabularData[j][0] = comment[j][0]; // emp token
					objTotalTabularData[j][1] = comment[j][1]; // emp name
					objTotalTabularData[j][2] = checkNull(String.valueOf(comment[j][2])); // Shift
					objTotalTabularData[j][3] = checkNull(String.valueOf(comment[j][3])); // Date
					objTotalTabularData[j][4] = String.valueOf(comment[j][4]); // Approved Single OT Hours
					if (bean.getDoubleOTflag().equals("YY")) {
					    objTotalTabularData[j][5] = String.valueOf(comment[j][5]); // Approved Double OT Hours
					}
				}
			}
			if (comment != null && comment.length > 0) {
				Object[][] apprDetails = new Object[1][4]; // new-------------->
				apprDetails[0][0] = "OT Register Details :";
				apprDetails[0][1] = "";
				apprDetails[0][2] = "";
				apprDetails[0][3] = "";
				TableDataSet apprInfoTable = new TableDataSet();
				apprInfoTable.setData(apprDetails);
				apprInfoTable.setCellWidth(new int[] {10, 10, 10, 10 });
				apprInfoTable.setCellAlignment(new int[] {0, 0, 0, 0});
				apprInfoTable.setBorderDetail(0);
				apprInfoTable.setBlankRowsAbove(1);
				rg.addTableToDoc(apprInfoTable);

				TableDataSet branchDetails = new TableDataSet();
				branchDetails.setHeader(header);
				branchDetails.setHeaderBorderDetail(3);
				branchDetails.setHeaderBorderColor(new BaseColor(255, 0, 0));
				branchDetails.setData(objTotalTabularData);
				branchDetails.setCellWidth(bcellWidth);
				branchDetails.setCellAlignment(bcellAlign);
				branchDetails.setBorderDetail(3);
				rg.addTableToDoc(branchDetails);
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "OT Register Details : No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] {0});
				noData.setCellWidth(new int[] {100});
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
}
