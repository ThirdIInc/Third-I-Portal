package org.struts.action.attendance.monthlyAttendance;

import java.util.Calendar;

import org.paradyne.bean.attendance.monthlyAttendance.UploadMonthlyAttnStatistics;
import org.paradyne.lib.BeanBase;
import org.paradyne.model.attendance.monthlyAttendance.UploadMonthlyAttnStatisticsModel;
import org.struts.lib.ParaActionSupport;

public class UploadMonthlyAttnStatisticsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadMonthlyAttnStatisticsAction.class);
	UploadMonthlyAttnStatistics bean;
	public void prepare_local() throws Exception {
		bean=new UploadMonthlyAttnStatistics();
		bean.setMenuCode(2237);
	}

	public UploadMonthlyAttnStatistics getBean() {
		return bean;
	}

	public void setBean(UploadMonthlyAttnStatistics bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String downLoadFile() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		// model.downloadFile(bean, response);
		Object[][] checkWorkFlow = model.callCheckWorkFlow();
		if(checkWorkFlow != null && checkWorkFlow.length > 0) {
			if(String.valueOf(checkWorkFlow[0][4]).equals("Y")) {
				model.downloadCalculateFile(bean, response);
				model.terminate();
				return null;
			} else {
				addActionMessage("Please enable Upload Monthly Attendance Workflow.");
				return "success";
			}
		} else {
			addActionMessage("Please specify the settings.");
			return "success";
		}

	}
	
	public String showStatistics() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		if((bean.getPayBillId().equals("")&&bean.getEmployeeTypeId().equals("")&&bean.getEmpSerachId().equals(""))){
			model.showStatistics(bean);		
		}
		
		return "success";
	}

	/**
	 * Popup window contains list of all branches
	 * 
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchNameattn", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}

	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentName", "departmentId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Department:" + e);
			return "";
		}
	}

	/**
	 * Popup window contains list of all division
	 * 
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY DIV_ID ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Division:" + e);
			return "";
		}
	} // end of f9Div

	/**
	 * Popup window contains list of all employee types
	 * 
	 * @return String f9page
	 */
	public String f9EmployeeType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"employeeTypeName", "employeeTypeId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9EmployeeType:" + e);
			return null;
		}
	}

	/**
	 * Popup window contains list of all paybill group
	 * 
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query =
				" SELECT DISTINCT HRMS_PAYBILL.PAYBILL_GROUP, HRMS_PAYBILL.PAYBILL_ID FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_PAYBILL = HRMS_PAYBILL.PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9PayBill:" + e);
			return null;
		}
	}

	/**
	 * Popup window contains list of all departments
	 * 
	 * @return String f9page
	 */

	public String f9SeachEmployee() {
		try {

			String typeCode = bean.getEmployeeTypeId();
			String payBillNo = bean.getPayBillId();
			String brnCode = bean.getBranchId();
			String deptCode = bean.getDepartmentId();
			String divCode = bean.getDivisionId();

			String month = bean.getMonth();
			String year = bean.getYear();

			String query =
				" SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME ,HRMS_EMP_OFFC.EMP_ID" + " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) ";
					 

			if(typeCode != null && !typeCode.equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = " + typeCode;
			} // end of if statement
			if(payBillNo != null && !payBillNo.equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + payBillNo;
			} // end of if statement
			if(brnCode != null && !brnCode.equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + brnCode;
			} // end of if statement
			if(deptCode != null && !deptCode.equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT = " + deptCode;
			} // end of if statement
			if(divCode != null && !divCode.equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV = " + divCode;
			} // end of if statement
			
			
			query += getprofileQuery(bean);
			 query +=" AND HRMS_EMP_OFFC.EMP_STATUS='S'";
			 
			 query += "  AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-" + month + "-" + year + "','DD-MM-YYYY'))";
			 
			query += "	ORDER BY UPPER(HRMS_CENTER.CENTER_NAME),UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),UPPER(HRMS_EMP_OFFC.EMP_TOKEN) ";
			
		 

			String[] headers = {getMessage("emp.id"), getMessage("emp.Name")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken", "employeeName", "empSerachId"};

			int[] columnIndex = {0, 1, 2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Get records of the employees who are under same paybill group
	 */
	/**
	 * @param bean
	 * @return String as part of query that returns records of the employees who are under same paybill group
	 */
	public String getprofilePaybillQuery(BeanBase bean) {
		String query = "";
		if(bean.isGeneralFlag()) { // Logged in employee is general
			return query;
		} // end of if statement
		else {
			if(bean.getUserProfilePaybill() != null && bean.getUserProfilePaybill().length() > 0) {
				query += "WHERE HRMS_EMP_OFFC.EMP_PAYBILL IN (" + bean.getUserProfilePaybill() + ")";
			} // end of if statement
		} // end of else statement
		return query;
	}
	
	/**
	 * Overriding super class method Set the filters when page loads
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);

			// Get the filters from back end
			Object[][] attendanceFilters = model.getFilters();

			// Set the filters on a page via been
			bean.setBranchFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][0])));
			bean.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][1])));
			bean.setPayBillFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][2])));
			bean.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][3])));
			bean.setDivisionFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][4])));

			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));

			model.terminate();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}

	public String reset() {
		bean.setUploadFileName("");
		bean.setMonth("");

		// Set the current year
		Calendar c = Calendar.getInstance();
		bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
		bean.setDivisionFlag(false);
		bean.setDivisionId("");
		bean.setDivisionName("");
		bean.setBranchFlag(false);
		bean.setBranchId("");
		bean.setBranchName("");
		bean.setBranchNameattn("");
		bean.setDepartmentFlag(false);
		bean.setDepartmentId("");
		bean.setDepartmentName("");
		bean.setPayBillFlag(false);
		bean.setPayBillId("");
		bean.setPayBilName("");
		bean.setEmployeeTypeFlag(false);
		bean.setEmployeeTypeId("");
		bean.setEmployeeTypeName("");
		// bean.setWrongEmptoken("");
		bean.setEmpSerachId("");
		bean.setEmployeeName("");
		bean.setRemoveEmpCode("");
		bean.setRemoveEmpName("");
		bean.setRemoveEmpToken("");
		prepare_withLoginProfileDetails();

		return "success";
	}
	

	public String uploadMonSheet() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		Object[][] checkWorkFlow = model.callCheckWorkFlow();
		if(checkWorkFlow != null && checkWorkFlow.length > 0) {
			if(String.valueOf(checkWorkFlow[0][4]).equals("Y")) {
				//String resultVal = model.uploadMonthlySheet(bean,String.valueOf(checkWorkFlow[0][5]));
				String resultVal = model.uploadMonthlySheet(bean,"N");
				model.terminate();
				String[] result = resultVal.split(",");

				if(result[0].equals("notValidFormat")) {
					addActionMessage("Please select the Xls file.");
				}

				if(result[0].equals("noDataInfile")) {
					addActionMessage("Either there is no data in file to read or data is not in proper format!");
				}
				if(result[0].equals("success")) {
					addActionMessage("File uploaded successfully");
					bean.setUploadFileName("");
				}
				if(result[0].equals("successWithError")) {
					String empToken = "";
					for(int i = 1; i < result.length; i++) {
						if(i < result.length - 1) {
							empToken += result[i] + ",";
						} else {
							empToken += result[i];
						}
						if(i % 20 == 0) {
							empToken += "<br>";
						}
					}
					request.setAttribute("wrongEmptoken", "Please correct the records of following employees <br>" + empToken);
					addActionMessage("File Uploaded Successfully.");
				} else {
					request.setAttribute("wrongEmptoken", "");
				}

				if(result[0].equals("notSuccess")) {
					addActionMessage("File cannot be uploaded.");
				}
			} else {
				addActionMessage("Please enable Upload Monthly Attendance Workflow.");
			}
		} else {
			addActionMessage("Please specify setting Attendance Settings.");
		}
		
		model.showStatistics(bean);
		return "success";
	}
	/**
	 * Generates a report
	 */
	public void report() {
		try {
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			model.report(bean, response);
			model.terminate();			
		} catch(Exception e) {
			e.printStackTrace();
		} // end of try-catch block
	}
		
	
	/**
	 * Popup window contains list of all branches	 * 
	 * @return String f9page
	 */
	public String f9ViewEmpStatistics() {
		try {
			String query = "  ";
			String attMonth=bean.getMonth();
			String attYear=bean.getYear();
			String center=bean.getHBranchCode();
			String salaryStartFlag="";
			String fromDay="";
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			Object[][]flagObj=model.callCheckWorkFlow();
			if(flagObj!=null && flagObj.length>0){
			salaryStartFlag =String.valueOf(flagObj[0][2]);
			fromDay=String.valueOf(flagObj[0][3]);
			}
			if(bean.getHEmpType().equals("T")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_EMP_OFFC	"
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S'  AND EMP_DIV = "+bean.getDivisionId()+" ";
				
				if (salaryStartFlag.equals("P")) {
					String date = "";
					String month = "";
					String Year = "";
					month = bean.getMonth();
					Year = bean.getYear();
					date = fromDay + "-" + month + "-" + Year;
					query += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
							+ "','DD-MM-YYYY')";
				} else {
					query += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
							+ bean.getMonth() + "-" + bean.getYear()
							+ "', 'DD-MM-YYYY'))";
				}
				
						
				
			}
			
			if(bean.getHEmpType().equals("U")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+"  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
			if(bean.getHEmpType().equals("L")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_LEAVE_DTLHISTORY		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE LEAVE_DTL_STATUS='P' AND LEAVE_MONTH="+attMonth+"	 AND LEAVE_YEAR="+attYear+"	  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
			if(bean.getHEmpType().equals("H")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND ATT_EMP_STATUS='O'  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
		
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			
			if(center.equals("HH")||center.equals("")){
				
			}else{
				query+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
			}
			if(!bean.getEmpSerachId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_ID="+bean.getEmpSerachId()+" ";
			}
			
			query+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			String[] headers = {"Employee Id","Employee Name","Branch"};

			String[] headerWidth = {"20","40","20"};

			String[] fieldNames = {"statisticsName", "statisticsCode", "statisticsCenter"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}
	
	/**
	 * Popup window contains list of all branches
	 * 
	 * @return String f9page
	 */
	public String f9ResignEmpStatistics() {
		try {
			String salaryStartFlag="";
			String fromDay="";
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			Object[][]flagObj=model.callCheckWorkFlow();
			if(flagObj!=null && flagObj.length>0){
			salaryStartFlag =String.valueOf(flagObj[0][2]);
			fromDay=String.valueOf(flagObj[0][3]);
			}
			
			String query = "  ";
			String attMonth=bean.getMonth();
			String attYear=bean.getYear();
			String center=bean.getHBranchCode();
			
						 query="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME	"	
							+"	,TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY')	 FROM HRMS_RESIGN	"
							+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC. EMP_ID) "	
							+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)"	
							+"	WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' AND RESIGN_APPL_STATUS IN ('A','Y') ";
			
			String toDate = "";
			String fromDate = "";
			String month= bean.getMonth();
			String year=bean.getYear();
			int endDate = Integer.parseInt(fromDay);
			endDate -= 1;
			if (salaryStartFlag.equals("P")) {
			if (month.equals("1")) {
				String DateYear = String.valueOf(Integer.parseInt(year) - 1);
				fromDate = fromDay + "-12-" + DateYear;
			} else {
				String DateMonth = String.valueOf(Integer.parseInt(month) - 1);
				fromDate = fromDay + "-" + DateMonth + "-" + year;
			}
			toDate = endDate + "-" + month + "-" + year;
			query+="AND (RESIGN_ACCEPT_DATE BETWEEN TO_DATE('"+fromDate+"', 'DD-MM-YYYY')  AND TO_DATE('"+toDate+"', 'DD-MM-YYYY') "
						+"  OR(RESIGN_SEPR_DATE BETWEEN TO_DATE('"+fromDate+"', 'DD-MM-YYYY')  AND TO_DATE('"+toDate+"', 'DD-MM-YYYY')) "
						+"	)";
			
			}else{				
			
			//RESIGNED EMPLOYEE
				query+="AND (RESIGN_ACCEPT_DATE >= TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')  AND RESIGN_ACCEPT_DATE <= LAST_DAY(TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')) "
			+"  OR(RESIGN_SEPR_DATE >= TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')  AND RESIGN_SEPR_DATE <= LAST_DAY( TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY'))) "
			+"	)";
			}			
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			if(center.equals("HH")||center.equals("")){
				
			}else{
				query+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
			}
			if(!bean.getEmpSerachId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_ID="+bean.getEmpSerachId()+" ";
			}
			query+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			String[] headers = {"Employee Id","Employee Name","Branch","Resign Accept Date","Resign Separation Date"};

			String[] headerWidth = {"10","30","20","20","20"};

			String[] fieldNames = {"statisticsName", "statisticsCode", "statisticsCenter","resignAcceptDate","resignSeparationDate"};

			int[] columnIndex = {0, 1,2,3,4};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}
	
	
	
	/**
	 * Popup window contains list of all departments
	 * 
	 * @return String f9page
	 */

	public String f9removeEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String salaryStartFlag="";
			String fromDay="";
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			Object[][]flagObj=model.callCheckWorkFlow();
			if(flagObj!=null && flagObj.length>0){
			salaryStartFlag =String.valueOf(flagObj[0][2]);
			fromDay=String.valueOf(flagObj[0][3]);
			}
			
			
			
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" ";
			/*if (salaryStartFlag.equals("P")) {
				String date = "";
				String Year = "";
				month = bean.getMonth();
				Year = bean.getYear();
				date = fromDay + "-" + month + "-" + Year;
				query += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
						+ "','DD-MM-YYYY')";
			} else {
				query += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
						+ bean.getMonth() + "-" + bean.getYear()
						+ "', 'DD-MM-YYYY'))";
			}*/
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			if(!bean.getEmpSerachId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_ID="+bean.getEmpSerachId()+" ";
			}
			query +=" ORDER BY EMP_TOKEN";
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"removeEmpToken", "removeEmpName", "onholdBranchName","removeEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * Popup window contains list of all departments
	 * 
	 * @return String f9page
	 */

	public String f9clearOnHoldEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(ATT_EMP_STATUS='O')";
			
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			if(!bean.getEmpSerachId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_ID="+bean.getEmpSerachId()+" ";
			}
			query +="  ORDER BY EMP_TOKEN ";
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"onHoldEmpToken", "onHoldEmpName","onholdBranchName", "onHoldEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	public String f9addOnHoldEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(ATT_EMP_STATUS='' OR ATT_EMP_STATUS IS NULL) ";
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			if(!bean.getEmpSerachId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_ID="+bean.getEmpSerachId()+" ";
			}
			query +="  ORDER BY EMP_TOKEN ";
			
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"onHoldAddEmpToken", "onHoldAddEmpName","onholdBranchName", "onHoldAddEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 */
	public String removeEmployee() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		/**
		 * IS SALARY LOCKED
		 */
		String month = bean.getMonth();
		String year = bean.getYear();
		String branchId = bean.getBranchId();
		String departmentId = bean.getDepartmentId();
		String payBillId = bean.getPayBillId();
		String employeeTypeId = bean.getEmployeeTypeId();
		String divisionId = bean.getDivisionId();
		
		// Set list of filters
		String[] listOfFilters = new String[5];
		listOfFilters[0] = branchId;
		listOfFilters[1] = departmentId;
		listOfFilters[2] = payBillId;
		listOfFilters[3] = employeeTypeId;
		listOfFilters[4] = divisionId;
		boolean result=false;
		String flag=model.isSalaryLock(bean,listOfFilters);
		if((flag.equals(""))){
		 result=model.removeEmployee(bean);
		}
		if(!(flag.equals(""))){
			addActionMessage(flag);
		}
		else if(result){
			bean.setRemoveEmpCode("");
			bean.setRemoveEmpName("");
			bean.setRemoveEmpToken("");
			addActionMessage("Employee deleted successfully");
			return showStatistics();
		}
		else{
			addActionMessage("Error occured while deleting record's");
		}
		return  showStatistics();
	}	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 */
	public String addOnHold() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		String empCode = bean.getOnHoldAddEmpCode();	
		String month = bean.getMonth();
		String year = bean.getYear();
		String flag=model.isSalaryLockEmp(month,year,empCode,"add onhold");
		if(flag.equals("")){
			boolean result=model.addOnHold(bean,"O",empCode,"Y");
			if(result){
				bean.setOnHoldAddEmpCode("");
				bean.setOnHoldAddEmpName("");
				bean.setOnHoldAddEmpToken("");
				addActionMessage("Employee added onhold successfully");
				return showStatistics();
			}
			else{
				addActionMessage("Error occured while adding onhold employee");
			}
		}
		else{
			addActionMessage(flag);
			return showStatistics();
		}
		return "success";
	}	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 */
	public String clearOnHold() {
		UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
		model.initiate(context, session);
		String empCode = bean.getOnHoldEmpCode();	
		String month = bean.getMonth();
		String year = bean.getYear();
		String flag=model.isSalaryLockEmp(month,year,empCode,"clear onhold");
		if(flag.equals("")){
		boolean result=model.addOnHold(bean,"",empCode,"N");
		if(result){
			bean.setOnHoldEmpCode("");
			bean.setOnHoldEmpName("");
			bean.setOnHoldEmpToken("");
			addActionMessage("Employee cleared onhold successfully");
			return showStatistics();
		}
		else{
			addActionMessage("Error occured while adding onhold employee");
		}
		}
		else{
			addActionMessage(flag);
			return showStatistics();
		}
		return "success";
	}	
	

}
