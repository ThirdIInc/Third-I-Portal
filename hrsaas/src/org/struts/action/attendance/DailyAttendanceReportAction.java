/**
 * Bhushan Apr 1, 2008
 */

package org.struts.action.attendance;

import org.paradyne.bean.attendance.DailyAttendanceReport;
import org.paradyne.model.attendance.DailyAttendanceReportModel;
import org.paradyne.model.attendance.MonthAttendanceProcessModel;
import org.struts.lib.ParaActionSupport;

/*
 * To generate a report of daily attendance
 */

public class DailyAttendanceReportAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MonthAttendanceProcessModel.class);
	DailyAttendanceReport bean;

	/**
	 * Get the DailyAttendanceReport bean object
	 * 
	 * @return DailyAttendanceReport bean object
	 */
	public DailyAttendanceReport getDailyAttendanceReport() {
		return bean;
	}

	/**
	 * Overriding super class method
	 * 
	 * @return java bean object
	 */
	public Object getModel() {
		return bean;
	}

	/**
	 * Overriding super class method
	 */
	public void prepare_local() throws Exception {
		bean = new DailyAttendanceReport();
		bean.setMenuCode(553);
	}

	/**
	 * Overriding super class method Set the filters when page loads
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			DailyAttendanceReportModel model = new DailyAttendanceReportModel();
			model.initiate(context, session);
			if (bean.isGeneralFlag()) {
				model.setEmployee(bean); // If login user is general, then sets his name when page loads
			}
			model.terminate();
		} catch (Exception e) {
			logger
					.error("Exception in prepare_withLoginProfileDetails in action: "+ e);
		}
	}

	public String mailReport() {
		try {
			DailyAttendanceReportModel model = new DailyAttendanceReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Payroll"
					+ poolName + "/";
			model.dailyAttendanceReport(bean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	/**
	 * Generates a report
	 */
	public void report() {
		try {
			DailyAttendanceReportModel model = new DailyAttendanceReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.dailyAttendanceReport(bean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in report in action: " + e);
		}
	}

	/**
	 * Resets the fields
	 * 
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			bean.setFromDate("");
			bean.setToDate("");
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setPayBillName("");
			bean.setPayBillNo("");
			bean.setReportType("");
			bean.setTypeCode("");
			bean.setTypeName("");
			bean.setEmpName("");
			bean.setEmpCode("");
			bean.setEmpToken("");
			bean.setSrchStatus1("");
			bean.setSrchStatus2("");

			prepare_withLoginProfileDetails(); // Sets the filters when page
			// loads
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in reset in action: " + e);
			return "";
		}
	}

	/**
	 * Sets the DailyAttendanceReport bean object
	 */
	public void setDailyAttendanceReport(DailyAttendanceReport bean) {
		this.bean = bean;
	}

	/**
	 * Popup window contains list of all the employees
	 * 
	 * @return String f9page
	 */
	public String f9Emp() {
		try {
			String query = " SELECT DISTINCT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ENAME, EMP_ID "
					+ " FROM HRMS_EMP_OFFC WHERE  EMP_DIV IN ("+bean.getDivCode()+")  ";
			
			query += "	ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN  ";
		
			String[] headers = { getMessage("employee.id"),getMessage("employee") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empToken", "empName", "empCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Emp in action: " + e);
			return null;
		}
	}

	public String f9EmpType() {
		String query = "SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE "
					+" WHERE IS_ACTIVE='Y' ORDER BY UPPER(TYPE_NAME)  ";

		String header = getMessage("employee.type");
		String textAreaId = "paraFrm_typeName";
		String hiddenFieldId = "paraFrm_typeCode";
		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	/**
	 * Popup window contains list of all paybill group
	 * @return String f9page
	 */
	public String f9PayBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL ";
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";

			String header = getMessage("pay.bill");
			String textAreaId = "paraFrm_payBillName";
			String hiddenFieldId = "paraFrm_payBillNo";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Method : f9Branch(). 
	 * Purpose : Branch F9 search.
	 * @return String.
	 */
	public String f9Branch() {
		String query = "SELECT CENTER_ID, NVL(CENTER_NAME,' ') FROM HRMS_CENTER "
				+ "WHERE IS_ACTIVE='Y' ORDER BY UPPER(CENTER_NAME)  ";
		String header = getMessage("branch");
		String textAreaId = "paraFrm_brnName";
		String hiddenFieldId = "paraFrm_brnCode";
		String submitFlag = "";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	/**
	 * Method : f9Div().
	 * Purpose : Division F9 search.
	 * @return String.
	 */
	public String f9Div() {
		try {
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision()
						+ ")";
			}else{
				query += " WHERE 1=1";
			}
			query += " AND IS_ACTIVE='Y' ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_divName";
			String hiddenFieldId = "paraFrm_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Method : f9Dept(). 
	 * Purpose : Department F9 search.
	 * @return String.
	 */
	public String f9Dept() {

		String query = "SELECT DEPT_ID, NVL(DEPT_NAME,' ')  FROM HRMS_DEPT "
				+ " WHERE IS_ACTIVE='Y' ORDER BY UPPER(DEPT_NAME) ";

		String header = getMessage("department");
		String textAreaId = "paraFrm_deptName";
		String hiddenFieldId = "paraFrm_deptCode";
		String submitFlag = "";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
}