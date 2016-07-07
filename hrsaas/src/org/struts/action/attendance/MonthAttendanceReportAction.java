package org.struts.action.attendance;

import java.util.Calendar;
import org.paradyne.bean.attendance.MonthAttendanceReport;
import org.paradyne.model.attendance.MonthAttendanceReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Bhushan
 * @date Jul 11, 2008
 */

/**
 * To generate a report of both monthly as well as annual attendance
 */

public class MonthAttendanceReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthAttendanceReportAction.class);

	MonthAttendanceReport bean;

	/**
	 * Popup window contains list of all branches
	 */
	/**
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"brnName", "brnCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch in action" + e);
			return "";
		} // end of try-catch block
	}

	/**
	 * Popup window contains list of all departments
	 */
	/**
	 * @return String f9page
	 */
	public String f9Dept() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Dept in action" + e);
			return "";
		} // end of try-catch block
	}

	/**
	 * Popup window contains list of all division
	 */
	/**
	 * @return String f9page
	 */
	public String f9Div() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION  ";
			
			if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
				

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Div in action" + e);
			return "";
		} // end of try-catch block
	}

	/**
	 * Popup window contains list of all employee types
	 */
	/**
	 * @return String f9page
	 */
	public String f9EmpType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"typeName", "typeCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9EmpType in action" + e);
			return null;
		} // end of try-catch block
	}

	/**
	 * Popup window contains list of all paybill group
	 */
	/**
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillNo"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9PayBill in action" + e);
			return null;
		} // end of try-catch block
	}

	/**
	 * Overriding super class method
	 */
	/**
	 * @return java bean object
	 */
	public Object getModel() {
		return bean;
	}

	/**
	 * Gets the MonthAttendanceReport bean object
	 */
	/**
	 * @return MonthAttendanceReport bean object
	 */
	public MonthAttendanceReport getMonthAttendanceReport() {
		return bean;
	}

	/**
	 * Overriding super class method
	 */
	public void prepare_local() throws Exception {
		bean = new MonthAttendanceReport();
		bean.setMenuCode(555);
	}

	/* 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	/**
	 * Call when page loads at first time. Set the current year
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails in action: " + e);
		}
	}

	/**
	 * Generates a report
	 */
	/*public void report() {
		try {
			MonthAttendanceReportModel model = new MonthAttendanceReportModel();
			model.initiate(context, session);
			String type = bean.getAttdnType();

			if(type.equals("M")) {
				model.getMonthAttendanceReport(bean, response);// Generates a report for a given month and year
			}
			if(type.equals("A")) {
				model.annualReport(bean, response);// Generates a report for 12 months starting from a given month and year
			} // end of else statement

			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in report in action" + e);
		} // end of try-catch block
	}*/
	
	/**
	 * @return null
	 */
	public String getReport() {
		final MonthAttendanceReportModel model = new MonthAttendanceReportModel();
		model.initiate(context, session);
		
		String reportPath = "";
		
		model.getReport(bean, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			final MonthAttendanceReportModel model = new MonthAttendanceReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(bean, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
	
	
	
	
	
	

	/**
	 * Resets the fields
	 */
	/**
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			bean.setBrnFlag("");
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setDeptCode("");
			bean.setDeptFlag("");
			bean.setDeptName("");
			bean.setDivCode("");
			bean.setDivFlag("");
			bean.setDivName("");
			bean.setMonth("");
			bean.setPayBillFlag("");
			bean.setPayBillName("");
			bean.setPayBillNo("");
			bean.setAttdnType("");
			bean.setReportType("");
			bean.setTypeCode("");
			bean.setTypeFlag("");
			bean.setTypeName("");
			bean.setYear("");
			prepare_withLoginProfileDetails();
			return SUCCESS;
		} catch(Exception e) {
			logger.info("Exception in reset in action:" + e);
			return "";
		} // end of try-catch block
	}

	/**
	 * Sets the MonthAttendanceReport bean object
	 */
	public void setMonthAttendanceReport(MonthAttendanceReport bean) {
		this.bean = bean;
	}
}