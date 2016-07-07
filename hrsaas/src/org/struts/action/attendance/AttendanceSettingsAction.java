/* Tarun Chaturvedi 23-06-2008 */

package org.struts.action.attendance;

import org.paradyne.bean.attendance.AttendanceSettings;
import org.paradyne.model.attendance.AttendanceSettingsModel;
import org.struts.lib.ParaActionSupport;

/**
 * AttendanceSettingsAction class to define settings for file uploading
 */
public class AttendanceSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AttendanceSettingsAction.class);
	AttendanceSettings bean;

	/**
	 * method name : f9BranchAction purpose : to select branch name return type : String parameter : none
	 */
	public String f9BranchAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("branch")};

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"branchName", "branchCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * method name : f9CompOffAction purpose : to select leave type for comp off adjustment return type : String parameter : none
	 */
	public String f9CompOffAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT CAST(' ' AS NVARCHAR2(10)) LEAVE_NAME, 0 LEAVE_ID FROM DUAL " +
		" UNION " +
		" SELECT LEAVE_NAME, LEAVE_ID FROM HRMS_LEAVE " +
		" INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE_POLICY_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID) " +
		" WHERE LEAVE_PAID_UNPAID = 'P' ORDER BY LEAVE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("compName")};

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"compOffName", "compOffCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * method name : f9DivisionAction purpose : to select division name return type : String parameter : none
	 */
	public String f9DivisionAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
		if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
		}
		query += " ORDER BY UPPER(DIV_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division")};

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"divName", "divCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public AttendanceSettings getAttendanceSettings() {
		return bean;
	}

	public Object getModel() {
		return bean;
	}

	/**
	 * method name : leaveCombination purpose : to display all the available leave types in new window return type : String
	 * parameter : none
	 */
	public String leaveCombination() {
		bean.setLeaveCodeHidNext(bean.getLeaveCodeHid());
		bean.setLeaveAbbrHidNext(bean.getLeaveAbbrHid());

		AttendanceSettingsModel model = new AttendanceSettingsModel();
		model.initiate(context, session);

		model.leaveCombination(bean);

		model.terminate();

		return "leaveType";
	}

	@Override
	public void prepare_local() throws Exception {
		bean = new AttendanceSettings();
		bean.setMenuCode(612);
		// TODO Auto-generated method stub
	}

	/**
	 * method name : prepare_withLoginProfileDetails purpose : to retrieve the details for daily work flow return type : void
	 * parameter : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		AttendanceSettingsModel model = new AttendanceSettingsModel();
		model.initiate(context, session);
		model.getDailyAttendanceDetail(bean);
		model.terminate();
	}

	/**
	 * method name : reset purpose : to reset all the form fields to blank values return type : String parameter : none
	 */
	public String reset() {
		bean.setAttSettingsCode("");
		bean.setReportType("");
		bean.setBranchCode("");
		bean.setBranchName("");
		bean.setDivCode("");
		bean.setDivName("");

		resetFields();
		return "success";
	}

	/**
	 * method name : resetAttendanceSettings purpose : to reset daily work flow form fields to blank values return type : String
	 * parameter : none
	 */
	public String resetAttendanceSettings() {
		bean.setLeaveApplicationFlag(false);
		bean.setBranchHolidayFlag(false);
		bean.setAttendanceFlag(false);
		bean.setStartDate("");
		bean.setSalaryFlag("");
		bean.setMonthlyAttendanceFlag(false);
		bean.setMonthlyPaidleaveCode("");
		bean.setMonthlyPaidleave("");
		bean.setLeaveCodeHid("");
		bean.setLeaveAbbrHid("");
		bean.setRecordPerPage("");
		bean.setLeaveMngtStartMonth("");
		bean.setAlertLeave("");
		bean.setCompOffCode("");
		bean.setCompOffName("");
		bean.setCompoffdays("0");
		return "success";
	}

	/**
	 * method name : resetFields purpose : to reset fields on changing either division name or branch name return type : void
	 * parameter : none
	 */
	public void resetFields() {
		bean.setTimeFormat("");
		bean.setSheetNum("");

		bean.setDateFormat("");
		bean.setDdFormat("");
		bean.setDdSeparator("");
		bean.setMmFormat("");
		bean.setMmSeparator("");
		bean.setYyFormat("");

		bean.setEmpFlag(false);
		bean.setEmpCharFrom("");
		bean.setEmpCharTo("");
		bean.setEmpColumn("");
		bean.setEmpColumnNoCsv("");

		bean.setDateCharFrom("");
		bean.setDateCharTo("");
		bean.setDateColumn("");
		bean.setDateColumnNoCsv("");
		bean.setDateFlag(false);

		bean.setInOutFlag(false);
		bean.setInTimeCharFrom("");
		bean.setInTimeCharTo("");
		bean.setInTimeColumn("");
		bean.setInTimeColumnNoCsv("");
		bean.setInTimeFlag(false);

		bean.setOutTimeCharFrom("");
		bean.setOutTimeCharTo("");
		bean.setOutTimeColumn("");
		bean.setOutTimeColumnNoCsv("");
		bean.setOutTimeFlag(false);

		bean.setOneTimeFlag(false);
		bean.setTimeCharFrom("");
		bean.setTimeCharTo("");
		bean.setTimeColumn("");
		bean.setTimeColumnNoCsv("");
		bean.setTimeFlag(false);
		bean.setTimeFlagType("");

		bean.setCheckCharFrom("");
		bean.setCheckCharTo("");
		bean.setCheckColumn("");
		bean.setCheckColumnNoCsv("");
		bean.setCheckFlag(false);

		bean.setWorkHrsCharFrom("");
		bean.setWorkHrsCharTo("");
		bean.setWorkHrsColumn("");
		bean.setWorkHrsColumnNoCsv("");
		bean.setWorkHrsFlag(false);

		bean.setShiftCharFrom("");
		bean.setShiftCharTo("");
		bean.setShiftColumn("");
		bean.setShiftColumnNoCsv("");
		bean.setShiftFlag(false);
		
		bean.setLateRegularizationCheckBox("");
		bean.setHalfDayRegularizationCheckBox("");
		bean.setAbsentRegularizationCheckBox("");
		bean.setRegularTimeRegularizationCheckBox("");
		
	}

	/**
	 * method name : saveAttendanceSetting purpose : to update the daily attendance work flow settings return type : String
	 * parameter : none
	 */
	public String saveAttendanceSetting() {
		boolean result = false;
		
		AttendanceSettingsModel model = new AttendanceSettingsModel();
		model.initiate(context, session);

		result = model.updateAttendanceSettings(bean);

		if(result) {
			addActionMessage(getMessage("update"));
			//resetAttendanceSettings(); 
		} else {
			addActionMessage(getMessage("update.error"));
			//resetAttendanceSettings();
		}

		model.terminate();
		return "success";
	}

	/**
	 * method name : saveDailyAttendanceDetails purpose : to save and update the daily attendance settings return type : String
	 * parameter : none
	 */
	public String saveDailyAttendanceDetails() {
		boolean result = false;
		
		AttendanceSettingsModel model = new AttendanceSettingsModel();
		model.initiate(context, session);

		boolean isExist = model.isDataExist(bean);

		if(!isExist) {
			result = model.saveDailyAttendanceDetails(bean);

			if(result) {
				addActionMessage(getMessage("save"));
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} else {
			result = model.updateDailyAttendanceDetails(bean);

			if(result) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}

		model.terminate();
		return "success";
	}

	public void setAttendanceSettings(AttendanceSettings bean) {
		this.bean = bean;
	}

	/**
	 * method name : setLeavePriority purpose :to set the leave priorities return type : String parameter : none
	 */
	public String setLeavePriority() {
		/**
		 * getting all the form fields values which are necessary to set the leave priority
		 */
		String srNo = bean.getSrNo();
		String buttonType = request.getParameter("type");
		String[] leaveCode = request.getParameterValues("leaveCode");
		String[] leaveName = request.getParameterValues("leaveName");
		String[] leaveAbbr = request.getParameterValues("leaveAbbr");

		AttendanceSettingsModel model = new AttendanceSettingsModel(); // creating an instance of AttendanceSettingsModel class
		model.initiate(context, session); // initialize AttendanceSettingsModel class

		/**
		 * call setLeavePriority(bean, srNo, buttonType, leaveCode, leaveName, leaveAbbr) of AttendanceSettingsModel
		 * class to set the leave priorities
		 */
		model.setLeavePriority(bean, srNo, buttonType, leaveCode, leaveName, leaveAbbr);

		model.terminate(); // terminate the AttendanceSettingsModel class
		return "leaveType";
	}

	/**
	 * method name : showRecords purpose : to display the attendance settings and daily work flow settings return type : String
	 * parameter : none
	 */
	public String showRecords() {
		resetFields();
		AttendanceSettingsModel model = new AttendanceSettingsModel();
		model.initiate(context, session);
		model.showRecords(bean);
		model.terminate();
		return "success";
	}
}