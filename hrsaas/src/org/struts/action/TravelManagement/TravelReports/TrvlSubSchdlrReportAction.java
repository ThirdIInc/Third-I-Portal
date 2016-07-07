package org.struts.action.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlSubSchdlrReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlSubSchdlrReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlSubSchdlrReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlSubSchdlrReportAction.class);
	TrvlSubSchdlrReport subSchdlr;

	public void prepare_local() throws Exception {
		subSchdlr = new TrvlSubSchdlrReport();
		subSchdlr.setMenuCode(958);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
		model.initiate(context, session);
		LinkedMap hMap = model.getTravelTypes();
		subSchdlr.setTrvlTypeMap(hMap);
		model.terminate();
	}

	/**
	 * @return the logger
	 */
	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger
	 *            the logger to set
	 */
	public static void setLogger(org.apache.log4j.Logger logger) {
		TrvlSubSchdlrReportAction.logger = logger;
	}

	/**
	 * @return the subSchdlr
	 */
	public TrvlSubSchdlrReport getSubSchdlr() {
		return subSchdlr;
	}

	/**
	 * @param subSchdlr
	 *            the subSchdlr to set
	 */
	public void setSubSchdlr(TrvlSubSchdlrReport subSchdlr) {
		this.subSchdlr = subSchdlr;
	}

	public Object getModel() {
		return subSchdlr;
	}

	/**
	 * Search employee details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9EMPaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,"
				+ " EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		
		query += getprofileQuery(subSchdlr);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("tms.empName") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empName", "empToken", "empCode" };

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
		TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
		model.initiate(context, session);
		model.deleteSavedReport(subSchdlr);
		boolean result = model.saveReportCriteria(subSchdlr);
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
				+ " WHERE REPORT_TYPE = 'TravelSubScheduler'  ORDER BY  REPORT_ID ";

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
		String submitToMethod = "TravelSubSchdlrReport_setDetails.action";

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
			TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
			model.initiate(context, session);
			subSchdlr.setBackFlag("");
			String[] labelNames = { getMessage("tms.empid"),
					getMessage("tms.travelType"), getMessage("branch"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.mainSchdlr"), getMessage("grade"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.applcntName"),
					getMessage("tms.trvlPurpose"),
					getMessage("tms.trvlAssigntDate"),
					getMessage("tms.approver"), getMessage("tms.trvlStatus"),
					getMessage("tms.trvlBookingDate"),
					getMessage("tms.trvlInitrName") };
			model.getReport(subSchdlr, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}

	public String reset() throws Exception {
		try {

			subSchdlr.setApplStatus("");
			subSchdlr.setApprvrToken("");
			subSchdlr.setApprvrCode("");
			subSchdlr.setApprvrName("");
			subSchdlr.setMainSchdlrToken("");
			subSchdlr.setMainSchdlrCode("");
			subSchdlr.setMainSchdlrName("");
			subSchdlr.setApplFor("");
			subSchdlr.setTravelCheckVal("");
			subSchdlr.setTravelCheck("");
			subSchdlr.setHidTravelSelf("");
			subSchdlr.setTrvlSelf("");
			subSchdlr.setHidTravelComp("");
			subSchdlr.setAccomCheck("");
			subSchdlr.setTrvlSelf("");
			subSchdlr.setTrvlComp("");
			subSchdlr.setAccomCheckVal("");
			subSchdlr.setHidAccomSelf("");
			subSchdlr.setAccomSelf("");
			subSchdlr.setHidAccomComp("");
			subSchdlr.setLocalCheckVal("");
			subSchdlr.setLocalCheck("");
			subSchdlr.setHidLocalSelf("");
			subSchdlr.setLocalSelf("");
			subSchdlr.setHidLocalComp("");
			subSchdlr.setLocalComp("");

			// column definitions

			//subSchdlr.setTravelIdFlag("");
			subSchdlr.setEmpIdFlag("");
			subSchdlr.setTrvlTypeFlag("");
			subSchdlr.setBranchFlag("");
			subSchdlr.setTravelStrtDateFlag("");
			subSchdlr.setMainSchdlrFlag("");
			subSchdlr.setGradeFlag("");
			subSchdlr.setTravelEndDateFlag("");
			subSchdlr.setApplcntNameFlag("");
			subSchdlr.setTrvlPurposeFlag("");
			subSchdlr.setTravelAssignDateFlag("");
			subSchdlr.setApprvrFlag("");
			subSchdlr.setTrvlStatusFlag("");
			subSchdlr.setTrvlBkngDateFlag("");
			subSchdlr.setTrvlInitrNameFlag("");

			// advance filters

			subSchdlr.setAssignDateSelect("");
			subSchdlr.setTADfromDate("");
			subSchdlr.setTADToDate("");
			subSchdlr.setTrvlStrtDateSelect("");
			subSchdlr.setTSDfromDate("");
			subSchdlr.setTSDToDate("");
			subSchdlr.setTrvlBookingDateSelect("");
			subSchdlr.setTBDfromDate("");
			subSchdlr.setTBDToDate("");
			subSchdlr.setBookCycleSelect("");
			subSchdlr.setBookCycleFrom("");
			subSchdlr.setBookCycleTo("");
			subSchdlr.setTrvlType("");
			subSchdlr.setGradeId("");
			subSchdlr.setGradeName("");
			subSchdlr.setBrnchCode("");
			subSchdlr.setBranchName("");
			subSchdlr.setDeptCode("");
			subSchdlr.setDeptName("");

			subSchdlr.setSortBy("");
			subSchdlr.setSortByAsc("checked");
			subSchdlr.setSortByDsc("");
			subSchdlr.setSortByOrder("");
			subSchdlr.setThenBy1("");
			subSchdlr.setThenByOrder1Asc("checked");
			subSchdlr.setThenByOrder1Dsc("");
			subSchdlr.setThenByOrder1("");
			subSchdlr.setThenBy2("");
			subSchdlr.setThenByOrder2Asc("checked");
			subSchdlr.setThenByOrder2("");
			subSchdlr.setThenByOrder2Dsc("");
			subSchdlr.setHiddenSortBy("");
			subSchdlr.setHiddenThenBy1("");
			subSchdlr.setHiddenThenBy2("");

			subSchdlr.setHiddenColumns("");
			subSchdlr.setHidReportView("checked");
			subSchdlr.setHidReportRadio("");
			subSchdlr.setReportType("");

			subSchdlr.setMyPage("");
			subSchdlr.setShow("");

			subSchdlr.setSettingName("");
			subSchdlr.setReportTitle("");
			subSchdlr.setReportId("");
			subSchdlr.setBackFlag("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String setDetails() throws Exception {
		TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
		model.initiate(context, session);
		model.setDetailOnScreen(subSchdlr);
		model.terminate();
		if (subSchdlr.getSortByOrder().trim().equals("D"))
			subSchdlr.setSortByDsc("checked");
		if (subSchdlr.getSortByOrder().trim().equals("A"))
			subSchdlr.setSortByAsc("checked");
		if (subSchdlr.getThenByOrder1().trim().equals("D"))
			subSchdlr.setThenByOrder1Dsc("checked");
		if (subSchdlr.getThenByOrder1().trim().equals("A"))
			subSchdlr.setThenByOrder1Asc("checked");
		if (subSchdlr.getThenByOrder2().trim().equals("D"))
			subSchdlr.setThenByOrder2Dsc("checked");
		if (subSchdlr.getThenByOrder2().trim().equals("A"))
			subSchdlr.setThenByOrder2Asc("checked");
		return SUCCESS;
	}

	/**
	 * Display the report on screen
	 * 
	 * @return String
	 */
	public String viewOnScreen() {
		subSchdlr.setBackFlag("");
		try {
			TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
			model.initiate(context, session);
			// PASSING LABEL NAMES
			String[] labelNames = { getMessage("tms.empid"),
					getMessage("tms.travelType"), getMessage("branch"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.mainSchdlr"), getMessage("grade"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.applcntName"),
					getMessage("tms.trvlPurpose"),
					getMessage("tms.trvlAssigntDate"),
					getMessage("tms.approver"), getMessage("tms.trvlStatus"),
					getMessage("tms.trvlBookingDate"),
					getMessage("tms.trvlInitrName") };
			// CALL TO MODEL
			model.callViewScreen(subSchdlr, request, labelNames);
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
		TrvlSubSchdlrReportModel model = new TrvlSubSchdlrReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		model.terminate();
		subSchdlr.setBackFlag("true");
		logger.info("getSortByOrder..." + subSchdlr.getSortByOrder());
		if (subSchdlr.getSortByOrder().trim().equals("D"))
			subSchdlr.setSortByDsc("checked");
		if (subSchdlr.getSortByOrder().trim().equals("A"))
			subSchdlr.setSortByAsc("checked");
		if (subSchdlr.getThenByOrder1().trim().equals("D"))
			subSchdlr.setThenByOrder1Dsc("checked");
		if (subSchdlr.getThenByOrder1().trim().equals("A"))
			subSchdlr.setThenByOrder1Asc("checked");
		if (subSchdlr.getThenByOrder2().trim().equals("D"))
			subSchdlr.setThenByOrder2Dsc("checked");
		if (subSchdlr.getThenByOrder2().trim().equals("A"))
			subSchdlr.setThenByOrder2Asc("checked");

		subSchdlr.setHiddenSortBy(subSchdlr.getSortBy());
		subSchdlr.setHiddenThenBy1(subSchdlr.getThenBy1());
		subSchdlr.setHiddenThenBy2(subSchdlr.getThenBy2());
		return SUCCESS;
	}

	public String f9ApprvrAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,"
				+ " EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		
		query += getprofileQuery(subSchdlr);
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

	public String f9MainSchdlrAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,"
				+ " EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		
		query += getprofileQuery(subSchdlr);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("tms.mainSchdlr") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "mainSchdlrName", "mainSchdlrToken",
				"mainSchdlrCode" };

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

}
