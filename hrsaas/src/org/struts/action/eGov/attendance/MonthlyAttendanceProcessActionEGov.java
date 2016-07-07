/**
 * 
 */
package org.struts.action.egov.attendance;

import java.util.Calendar;

import org.paradyne.bean.eGov.attendance.MonthlyAttendanceProcessEGov;
import org.paradyne.model.eGov.attendance.MonthlyAttendanceProcessModelEGov;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0476
 *
 */
public class MonthlyAttendanceProcessActionEGov extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthlyAttendanceProcessActionEGov.class);
	MonthlyAttendanceProcessEGov bean;
	
	public void prepare_local() throws Exception {
		bean =new MonthlyAttendanceProcessEGov();
		bean.setMenuCode(2104);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public MonthlyAttendanceProcessEGov getBean() {
		return bean;
	}

	public void setBean(MonthlyAttendanceProcessEGov bean) {
		this.bean = bean;
	}

	public String lockAttendance()throws Exception{
		MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
		model.initiate(context, session);
		// Get the filters from a page
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
		String result=model.lockAttendance(month, year, divisionId, listOfFilters);
		if(!result.equals("")){
			addActionMessage(result);
		}
		else{
			addActionMessage("Attendance Locked Successfully");
		}
		return SUCCESS;
	}
	
	public String unLockAttendance()throws Exception{
		MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
		model.initiate(context, session);
		// Get the filters from a page
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
		String result=model.unLockAttendance(month, year, divisionId, listOfFilters);
		if(!result.equals("")){
			addActionMessage(result);
		}
		else{
			addActionMessage("Attendance Locked Successfully");
		}
		return SUCCESS;
	}
	
	
	/**
	 * Processes the attendance
	 * @return SUCCESS
	 */
	public String attendanceProcess() {
		try {
			MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
			model.initiate(context, session);
			
			// Get the filters from a page
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
			
			// Process the attendance
			String message = model.attendanceProcess(month, year, divisionId, listOfFilters);
			
			// message whether attendance is processed properly or not
			addActionMessage(message);
			
			// reset the fields
			//reset();
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in attendanceProcess in action: " + e);
		}
		return SUCCESS;
	}

	/**
	 * Opens a popup window containing a list of all branches
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
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}

	/**
	 * Opens a popup window containing a list of all departments
	 * @return String f9page
	 */
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
		} catch (Exception e) {
			logger.error("Exception in f9Department in action:" + e);
			return "";
		}
	}

	/**
	 * Opens a popup window containing a list of all division
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
		} catch (Exception e) {
			logger.error("Exception in f9Division in action:" + e);
			return "";
		}
	} //end of f9Div

	/**
	 * Opens a popup window containing a list of all employee types
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
		} catch (Exception e) {
			logger.error("Exception in f9EmployeeType in action:" + e);
			return null;
		}
	}
	
	/**
	 * Opens a popup window containing a list of all paybill group
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " +
			" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9PayBill in action:" + e);
			return null;
		}
	}
	/* 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	/**
	 * Call when page loads at first time. Set the filters which are defined in Payroll Setting in Configuration module
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
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
			logger.error("Exception in prepare_withLoginProfileDetails in action: " + e);
		}
	}

	/**
	 * Reset the fields, set year as current year
	 * @return SUCCESS
	 */
	public String reset() {
		try {
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
			
			MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
			model.initiate(context, session);
			model.resetToNull(); // reset global variable to null
			model.terminate();
			
			// Set the filters
			prepare_withLoginProfileDetails();
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in reset in action: " + e);
			return "";
		}
	}
	
	/**
	 * Generates a report
	 */
	public void report() {
		try {
			MonthlyAttendanceProcessModelEGov model = new MonthlyAttendanceProcessModelEGov();
			model.initiate(context, session);
			//String type = bean.getAttdnType();
			// Get the filters from a page
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
				model.getMonthAttendanceReport(bean, response,listOfFilters);// Generates a report for a given month and year
			
			/*if(type.equals("A")) {
				model.annualReport(bean, response);// Generates a report for 12 months starting from a given month and year
			} // end of else statement
*/
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in report in action" + e);
		} // end of try-catch block
	}
	
	

}
