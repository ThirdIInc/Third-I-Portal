package org.struts.action.egov.attendance;

import java.util.Calendar;

import org.paradyne.bean.eGov.attendance.UploadMonthlyAttendanceEGov;
import org.paradyne.lib.BeanBase;
import org.paradyne.model.eGov.attendance.UploadMonthlyAttendanceModelEGov;
import org.struts.lib.ParaActionSupport;

public class UploadMonthlyAttendanceActionEGov extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadMonthlyAttendanceActionEGov.class);
	UploadMonthlyAttendanceEGov bean;
	public void prepare_local() throws Exception {
		bean=new UploadMonthlyAttendanceEGov();
		bean.setMenuCode(2103);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public UploadMonthlyAttendanceEGov getBean() {
		return bean;
	}

	public void setBean(UploadMonthlyAttendanceEGov bean) {
		this.bean = bean;
	}
	
	public String downLoadFile() {
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
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
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
		model.initiate(context, session);
		model.showStatistics(bean);
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

			String[] fieldNames = {"branchName", "branchId"};

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
				" SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
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
				" SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMP_NAME ,EMP_ID" + " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) ";
					 

			if(typeCode != null && !typeCode.equals("")) {
				query += " AND EMP_TYPE = " + typeCode;
			} // end of if statement
			if(payBillNo != null && !payBillNo.equals("")) {
				query += " AND EMP_PAYBILL = " + payBillNo;
			} // end of if statement
			if(brnCode != null && !brnCode.equals("")) {
				query += " AND EMP_CENTER = " + brnCode;
			} // end of if statement
			if(deptCode != null && !deptCode.equals("")) {
				query += " AND EMP_DEPT = " + deptCode;
			} // end of if statement
			if(divCode != null && !divCode.equals("")) {
				query += " AND EMP_DIV = " + divCode;
			} // end of if statement
			
			
			query += getprofileQuery(bean);
			 query +=" AND EMP_STATUS='S'";
			 
			 query += "  AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-" + month + "-" + year + "','DD-MM-YYYY'))";
			 
			query += "	ORDER BY UPPER(CENTER_NAME),UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),UPPER(EMP_TOKEN) ";
			
		 

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
			UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
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
		prepare_withLoginProfileDetails();

		return "success";
	}
	

	public String uploadMonSheet() {
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
		model.initiate(context, session);
		Object[][] checkWorkFlow = model.callCheckWorkFlow();
		if(checkWorkFlow != null && checkWorkFlow.length > 0) {
			if(String.valueOf(checkWorkFlow[0][4]).equals("Y")) {
				String resultVal = model.uploadMonthlySheet(bean,String.valueOf(checkWorkFlow[0][5]));
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
		

		return "success";
	}
	/**
	 * Generates a report
	 */
	public void report() {
		try {
			UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
			model.initiate(context, session);
			model.report(bean, response);
			model.terminate();			
		} catch(Exception e) {
			e.printStackTrace();
		} // end of try-catch block
	}
		
	
	/**
	 * Popup window contains list of all branches
	 * 
	 * @return String f9page
	 */
	public String f9ViewEmpStatistics() {
		try {
			String query = "  ";
			String attMonth=bean.getMonth();
			String attYear=bean.getYear();
			String center=bean.getHBranchCode();
			if(bean.getHEmpType().equals("U")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+"  AND EMP_DIV = "+bean.getDivisionId()+" AND EMP_CENTER="+bean.getHBranchCode();
			}
			if(bean.getHEmpType().equals("L")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+"	FROM HRMS_LEAVE_DTLHISTORY		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE LEAVE_DTL_STATUS='P' AND LEAVE_MONTH="+attMonth+"	 AND LEAVE_YEAR="+attYear+"	  AND EMP_DIV = "+bean.getDivisionId()+" AND EMP_CENTER="+bean.getHBranchCode();
			}
			if(bean.getHEmpType().equals("H")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND ATT_EMP_STATUS='O'  AND EMP_DIV = "+bean.getDivisionId()+" AND EMP_CENTER="+bean.getHBranchCode();
			}
			String[] headers = {"Employee Id","Employee Name"};

			String[] headerWidth = {"20","80"};

			String[] fieldNames = {"branchName", "branchId"};

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
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" ORDER BY EMP_TOKEN";
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
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(ATT_EMP_STATUS='O') ORDER BY EMP_TOKEN";
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
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID	FROM HRMS_UPLOAD_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(ATT_EMP_STATUS='' OR ATT_EMP_STATUS IS NULL) ORDER BY EMP_TOKEN";
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
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
		model.initiate(context, session);
		boolean result=model.removeEmployee(bean);
		if(result){
			bean.setRemoveEmpCode("");
			bean.setRemoveEmpName("");
			bean.setRemoveEmpToken("");
			addActionMessage("Record deleted successfully");
			return showStatistics();
		}
		else{
			addActionMessage("Error occured while deleting record's");
		}
		return "success";
	}	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 */
	public String addOnHold() {
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
		model.initiate(context, session);
		String empCode = bean.getOnHoldAddEmpCode();	
		boolean result=model.addOnHold(bean,"O",empCode);
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
		return "success";
	}	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 */
	public String clearOnHold() {
		UploadMonthlyAttendanceModelEGov model = new UploadMonthlyAttendanceModelEGov();
		model.initiate(context, session);
		String empCode = bean.getOnHoldEmpCode();	
		boolean result=model.addOnHold(bean,"",empCode);
		if(result){
			bean.setOnHoldEmpCode("");
			bean.setOnHoldEmpName("");
			bean.setOnHoldEmpToken("");
			addActionMessage("Employee cleared from onhold");
			return showStatistics();
		}
		else{
			addActionMessage("Error occured while adding onhold employee");
		}
		return "success";
	}	
	
}
