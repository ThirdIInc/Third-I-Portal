package org.struts.action.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlMainSchdlrReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlMainSchdlrReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlMainSchdlrReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlMainSchdlrReportAction.class);
	TrvlMainSchdlrReport mainSchdlr;

	public void prepare_local() throws Exception {
		mainSchdlr = new TrvlMainSchdlrReport();
		mainSchdlr.setMenuCode(957);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
		model.initiate(context, session);
		LinkedMap hMap = model.getTravelTypes();
		mainSchdlr.setTrvlTypeMap(hMap);
		model.terminate();
	}

	public Object getModel() {
		return mainSchdlr;
	}

	/**
	 * @return the mainSchdlr
	 */
	public TrvlMainSchdlrReport getMainSchdlr() {
		return mainSchdlr;
	}

	/**
	 * @param mainSchdlr the mainSchdlr to set
	 */
	public void setMainSchdlr(TrvlMainSchdlrReport mainSchdlr) {
		this.mainSchdlr = mainSchdlr;
	}

	/**
	 * Search employee details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9ApprvrAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,"
				+ " EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		
		query += getprofileQuery(mainSchdlr);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("tms.approver") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "apprvrName", "apprvrToken", "apprvrCode" };

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
	}

	/**
	 * Search employee details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9SubSchdlrAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,"
				+ " EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		
		query += getprofileQuery(mainSchdlr);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("tms.subSchdlr") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "subSchdlrName", "subSchdlrToken",
				"subSchdlrCode" };

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
	}

	/**
	 * Search cadre details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9cadretaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT NVL(CADRE_NAME,' '), CADRE_ID FROM  HRMS_CADRE  "
				+ " ORDER BY  CADRE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("grade") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "gradeName", "gradeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
	}

	/**
	 * Search branch details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9centeraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER "
				/*
				 * + " ,DEPT_NAME,DIV_NAME LEFT JOIN HRMS_DEPT
				 * ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)" + " LEFT
				 * JOIN HRMS_DIVISION
				 * ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE) "
				 */
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchName", "brnchCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
	}

	/**
	 * Search department details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName", "deptCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReport() throws Exception {
		TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
		model.initiate(context, session);
		model.deleteSavedReport(mainSchdlr);
		boolean result = model.saveReportCriteria(mainSchdlr);
		if (result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("duplicate"));
		model.terminate();
		return SUCCESS;
	}

	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '),
		 * REPORT_ID FROM HRMS_MISREPORT_HDR " + " ORDER BY REPORT_ID ";
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE = 'TravelMainScheduler'  ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),
				getMessage("report.title") };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName", "reportTitle", "reportId" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TravelMainSchdlrReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Generate report in PDF/XLS or DOC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		try {
			TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
			model.initiate(context, session);
			mainSchdlr.setBackFlag("");
			String[] labelNames = { getMessage("tms.empid"),
					getMessage("tms.travelType"), getMessage("branch"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.subSchdlr"), getMessage("grade"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.applcntName"),
					getMessage("tms.trvlPurpose"),
					getMessage("tms.trvlAssigntDate"),
					getMessage("tms.approver"), getMessage("tms.trvlStatus"),
					getMessage("tms.trvlBookingDate"),
					getMessage("tms.trvlInitrName") };
			model.getReport(mainSchdlr, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}

	public String reset() throws Exception {
		try {
			
			mainSchdlr.setApplDateSelect("");
			mainSchdlr.setApplDateFrm("");
			mainSchdlr.setApplDateTo("");
			mainSchdlr.setApplStatus("");
			mainSchdlr.setApprvrToken("");
			mainSchdlr.setApprvrCode("");
			mainSchdlr.setApprvrName("");
			mainSchdlr.setSubSchdlrToken("");
			mainSchdlr.setSubSchdlrCode("");
			mainSchdlr.setSubSchdlrName("");
			mainSchdlr.setApplFor("");
			mainSchdlr.setTravelCheckVal("");
			mainSchdlr.setTravelCheck("");
			mainSchdlr.setHidTravelSelf("");
			mainSchdlr.setTrvlSelf("");
			mainSchdlr.setHidTravelComp("");
			mainSchdlr.setAccomCheck("");
			mainSchdlr.setTrvlSelf("");
			mainSchdlr.setTrvlComp("");
			mainSchdlr.setAccomCheckVal("");
			mainSchdlr.setHidAccomSelf("");
			mainSchdlr.setAccomSelf("");
			mainSchdlr.setHidAccomComp("");
			mainSchdlr.setLocalCheckVal("");
			mainSchdlr.setLocalCheck("");
			mainSchdlr.setHidLocalSelf("");
			mainSchdlr.setLocalSelf("");
			mainSchdlr.setHidLocalComp("");
			mainSchdlr.setLocalComp("");

			// column definitions

			mainSchdlr.setEmpIdFlag("");
			mainSchdlr.setTravelIdFlag("");
			mainSchdlr.setTrvlTypeFlag("");
			mainSchdlr.setBranchFlag("");
			mainSchdlr.setTravelStrtDateFlag("");
			mainSchdlr.setSubSchdlrFlag("");
			mainSchdlr.setGradeFlag("");
			mainSchdlr.setTravelEndDateFlag("");
			mainSchdlr.setApplcntNameFlag("");
			mainSchdlr.setTrvlPurposeFlag("");
			mainSchdlr.setTravelAssignDateFlag("");
			mainSchdlr.setApprvrFlag("");
			mainSchdlr.setTrvlStatusFlag("");
			mainSchdlr.setTrvlBkngDateFlag("");
			mainSchdlr.setTrvlInitrNameFlag("");

			// advance filters

			mainSchdlr.setAssignDateSelect("");
			mainSchdlr.setTADfromDate("");
			mainSchdlr.setTADToDate("");
			mainSchdlr.setTrvlStrtDateSelect("");
			mainSchdlr.setTSDfromDate("");
			mainSchdlr.setTSDToDate("");
			mainSchdlr.setTrvlBookingDateSelect("");
			mainSchdlr.setTBDfromDate("");
			mainSchdlr.setTBDToDate("");
			mainSchdlr.setSchCycleSelect("");
			mainSchdlr.setSchCycleFrom("");
			mainSchdlr.setSchCycleTo("");
			mainSchdlr.setTrvlType("");
			mainSchdlr.setGradeId("");
			mainSchdlr.setGradeName("");
			mainSchdlr.setBrnchCode("");
			mainSchdlr.setBranchName("");
			mainSchdlr.setDeptCode("");
			mainSchdlr.setDeptName("");

			mainSchdlr.setSortBy("");
			mainSchdlr.setSortByAsc("checked");
			mainSchdlr.setSortByDsc("");
			mainSchdlr.setSortByOrder("");
			mainSchdlr.setThenBy1("");
			mainSchdlr.setThenByOrder1Asc("checked");
			mainSchdlr.setThenByOrder1Dsc("");
			mainSchdlr.setThenByOrder1("");
			mainSchdlr.setThenBy2("");
			mainSchdlr.setThenByOrder2Asc("checked");
			mainSchdlr.setThenByOrder2("");
			mainSchdlr.setThenByOrder2Dsc("");
			mainSchdlr.setHiddenSortBy("");
			mainSchdlr.setHiddenThenBy1("");
			mainSchdlr.setHiddenThenBy2("");

			mainSchdlr.setHiddenColumns("");
			mainSchdlr.setHidReportView("checked");
			mainSchdlr.setHidReportRadio("");
			mainSchdlr.setReportType("");

			mainSchdlr.setMyPage("");
			mainSchdlr.setShow("");

			mainSchdlr.setSettingName("");
			mainSchdlr.setReportTitle("");
			mainSchdlr.setReportId("");
			mainSchdlr.setBackFlag("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String setDetails() throws Exception {
		TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
		model.initiate(context, session);
		model.setDetailOnScreen(mainSchdlr);
		model.terminate();
		if (mainSchdlr.getSortByOrder().trim().equals("D"))
			mainSchdlr.setSortByDsc("checked");
		if (mainSchdlr.getSortByOrder().trim().equals("A"))
			mainSchdlr.setSortByAsc("checked");
		if (mainSchdlr.getThenByOrder1().trim().equals("D"))
			mainSchdlr.setThenByOrder1Dsc("checked");
		if (mainSchdlr.getThenByOrder1().trim().equals("A"))
			mainSchdlr.setThenByOrder1Asc("checked");
		if (mainSchdlr.getThenByOrder2().trim().equals("D"))
			mainSchdlr.setThenByOrder2Dsc("checked");
		if (mainSchdlr.getThenByOrder2().trim().equals("A"))
			mainSchdlr.setThenByOrder2Asc("checked");
		return SUCCESS;
	}

	/**
	 * Display the report on screen
	 * 
	 * @return String
	 */
	public String viewOnScreen() {
		mainSchdlr.setBackFlag("");
		try {
			TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
			model.initiate(context, session);
			// PASSING LABEL NAMES
			String[] labelNames = { getMessage("tms.empid"),
					getMessage("tms.travelType"), getMessage("branch"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.subSchdlr"), getMessage("grade"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.applcntName"),
					getMessage("tms.trvlPurpose"),
					getMessage("tms.trvlAssigntDate"),
					getMessage("tms.approver"), getMessage("tms.trvlStatus"),
					getMessage("tms.trvlBookingDate"),
					getMessage("tms.trvlInitrName") };
			// CALL TO MODEL
			model.callViewScreen(mainSchdlr, request, labelNames);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return "viewOnScreen";
	}

	/**
	 * Called on Back button in view screen
	 * 
	 * @return String returns to the mis filter page
	 */
	public String callBack() {
		TrvlMainSchdlrReportModel model = new TrvlMainSchdlrReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		model.terminate();
		mainSchdlr.setBackFlag("true");
		logger.info("getSortByOrder..." + mainSchdlr.getSortByOrder());
		if (mainSchdlr.getSortByOrder().trim().equals("D"))
			mainSchdlr.setSortByDsc("checked");
		if (mainSchdlr.getSortByOrder().trim().equals("A"))
			mainSchdlr.setSortByAsc("checked");
		if (mainSchdlr.getThenByOrder1().trim().equals("D"))
			mainSchdlr.setThenByOrder1Dsc("checked");
		if (mainSchdlr.getThenByOrder1().trim().equals("A"))
			mainSchdlr.setThenByOrder1Asc("checked");
		if (mainSchdlr.getThenByOrder2().trim().equals("D"))
			mainSchdlr.setThenByOrder2Dsc("checked");
		if (mainSchdlr.getThenByOrder2().trim().equals("A"))
			mainSchdlr.setThenByOrder2Asc("checked");

		mainSchdlr.setHiddenSortBy(mainSchdlr.getSortBy());
		mainSchdlr.setHiddenThenBy1(mainSchdlr.getThenBy1());
		mainSchdlr.setHiddenThenBy2(mainSchdlr.getThenBy2());
		return SUCCESS;
	}

}
