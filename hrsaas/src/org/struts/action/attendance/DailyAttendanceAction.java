/* Bhushan Dasare Feb 29, 2008*/

package org.struts.action.attendance;

import org.paradyne.bean.attendance.DailyAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.attendance.DailyAttendanceModel;
import org.struts.lib.ParaActionSupport;

/**
 * To view and modify the daily attendance.
 */

public class DailyAttendanceAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	DailyAttendance bean;

	/**
	 * Bulk updates the Daily Attendance with Status 1 and Status 2
	 */
	/**
	 * @return String SUCCESS
	 */
	public String bulkStatusUpdate() {
		try {
			DailyAttendanceModel model = new DailyAttendanceModel();
			model.initiate(context, session);
			String message = "";
			message = model.bulkStatusUpdate(bean);
			
			if(!message.equals("")) {
				addActionMessage(message);
			} else {
				addActionMessage("Record can't be updated!");
			}
			
			showAttendance();
			
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in bulkStatusUpdate:" + e);
			return null;
		}
	}

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
			logger.error("Exception in f9Branch:" + e);
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
			logger.error("Exception in f9Dept:" + e);
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
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Div:" + e);
			return "";
		} // end of try-catch block
	}

	/**
	 * Popup window contains list of all the employees
	 */
	/**
	 * @return String f9page
	 */
	public String f9Emp() {
		try {
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();

			String query =
				" SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, EMP_ID, EMP_DIV, DIV_NAME " 
				+ " FROM HRMS_EMP_OFFC  " 
				+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
				+" WHERE 1=1 ";
			
			if(!divCode.equals("")) {
				query += " AND EMP_DIV = " + divCode;
			} // end of if statement
			if(!brnCode.equals("")) {
				query += " AND EMP_CENTER = " + brnCode;
			} // end of if statement
			if(!deptCode.equals("")) {
				query += " AND EMP_DEPT = " + deptCode;
			} // end of if statement
			if(!typeCode.equals("")) {
				query += " AND EMP_TYPE = " + typeCode;
			} // end of if statement
			if(!payBillNo.equals("")) {
				query += " AND EMP_PAYBILL = " + payBillNo;
			} // end of if statement
			
			
			 query +=" AND EMP_STATUS='S'";
			query += "	ORDER BY UPPER(ENAME), EMP_TOKEN ";
		 
			String[] headers = {getMessage("employee.id"), getMessage("employee")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"eFilToken", "eFilName", "eFilNo" , "divCode", "divName"};

			int[] columnIndex = {0, 1, 2, 3, 4};

			String submitFlag = "false";

			String submitToMethod = " ";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch(Exception e) {
			logger.error("Exception in f9Emp:" + e);

		} // end of try-catch block
		return "f9page";
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
			logger.error("Exception in f9EmpType:" + e);
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
			String query =
				" SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String[] headers = {"PAY BILL NAME", getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillNo"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9PayBill:" + e);
			return null;
		} // end of try-catch block
	}

	/**
	 * Gets the DailyAttendance bean object
	 */
	/**
	 * @return DailyAttendance bean object
	 */
	public DailyAttendance getDailyAttendance() {
		return bean;
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
	 * Overriding super class method
	 */
	public void prepare_local() throws Exception {
		bean = new DailyAttendance();
		bean.setMenuCode(57);
	}

	/**
	 * Overriding super class method Set the filters when page loads
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			DailyAttendanceModel model = new DailyAttendanceModel();
			model.initiate(context, session);
			model.getFilters(bean);// Set filters when page loads
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails:" + e);
		} // end of try-catch block
	}

	/**
	 * Resets the fields
	 */
	/**
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			bean.setPayBillNo("");
			bean.setPayBillName("");
			bean.setTypeCode("");
			bean.setTypeName("");
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setEFilNo("");
			bean.setEFilToken("");
			bean.setEFilName("");
			bean.setFromDate("");
			bean.setToDate("");
			bean.setAttdnList(null);
			bean.setFlag(false);
			bean.setSrchStatus1("AL");
			bean.setSrchStatus2("AL");
			prepare_withLoginProfileDetails();// Sets the filters when page loads
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in reset:" + e);
			return null;
		} // end of try-catch block
	}

	/**
	 * Sets the DailyAttendance bean object
	 */
	public void setDailyAttendance(DailyAttendance bean) {
		this.bean = bean;
	}

	/**
	 * Show saved attendance
	 */
	/**
	 * @return String SUCCESS
	 */
	public String showAttendance() {
		try {
			DailyAttendanceModel model = new DailyAttendanceModel();
			model.initiate(context, session);

			String res = model.showAttendance(bean, request);// Shows saved attendance records
			if(!res.equals("")) {
				addActionMessage(res);
			}

			model.terminate();
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in showAttendance:" + e);
			return null;
		} // end of try-catch block
	}

	/**
	 * Updates the Daily Attendance
	 */
	/**
	 * @return String SUCCESS
	 */
	public String update() {
		try {
			DailyAttendanceModel model = new DailyAttendanceModel();
			model.initiate(context, session);
			String message = "";
			message = model.update(bean, request);// Updates the attendance records
			if(!message.equals("")) {
				addActionMessage(message);
			} // end of if statement
			model.terminate();
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in update:" + e);
			return null;
		} // end of try-catch block
	}
}