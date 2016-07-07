package org.struts.action.common;

import org.paradyne.bean.common.TaskReport;
import org.paradyne.model.common.TaskReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author vishwambhard
 * 
 */
public class TaskReportAction extends ParaActionSupport {

	TaskReport taskReport;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		taskReport = new TaskReport();
		taskReport.setMenuCode(675);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return taskReport;
	}

	public TaskReport getTaskReport() {
		return taskReport;
	}

	public void setTaskReport(TaskReport taskReport) {
		this.taskReport = taskReport;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TaskReportModel model = new TaskReportModel();
		model.initiate(context, session);

		getRecord();
		model.terminate();

	}

	/**
	 * THIS METHOD IS USED FOR GETTING TASK TYPE AND TASK PROJECT LIST
	 * 
	 * @return String
	 */
	public String getRecord() {
		TaskReportModel model = new TaskReportModel();
		model.initiate(context, session);
		model.getTaskTypeList(taskReport);// task type list
		model.getTaskProjectList(taskReport);// task project list
		model.terminate();
		return SUCCESS;
	}// end of getRecord

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		TaskReportModel model = new TaskReportModel();
		model.initiate(context, session);
		model.getReport(taskReport, response);
		model.terminate();
		return null;

	}// end of report

	/**
	 * THIS METHOD IS USED FOR RESET
	 * 
	 * @return String
	 */
	public String clear() {
		taskReport.setEmpName("");
		taskReport.setAppFromDate("");
		taskReport.setAppToDate("");
		taskReport.setTaskType("");
		taskReport.setTaskProject("");
		taskReport.setEmpCode("");
		taskReport.setReportType("");
		return SUCCESS;
	}// end of clear

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID,"
				+ "  CENTER_NAME,RANK_NAME"
				+ " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ "  WHERE  HRMS_EMP_OFFC.EMP_STATUS='S' "
				+ " ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"),getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "taskReport.empToken", "taskReport.empName",
				"taskReport.empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

}// end of class
