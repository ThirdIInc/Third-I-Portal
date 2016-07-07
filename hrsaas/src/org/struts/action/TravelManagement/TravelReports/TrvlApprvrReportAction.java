package org.struts.action.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlApprvrReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlApprvrReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlApprvrReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlApprvrReportAction.class);
	TrvlApprvrReport apprvrRepBean;

	/**
	 * @return the apprvrRepBean
	 */
	public TrvlApprvrReport getApprvrRepBean() {
		return apprvrRepBean;
	}

	/**
	 * @param apprvrRepBean
	 *            the apprvrRepBean to set
	 */
	public void setApprvrRepBean(TrvlApprvrReport apprvrRepBean) {
		this.apprvrRepBean = apprvrRepBean;
	}

	public void prepare_local() throws Exception {
		apprvrRepBean = new TrvlApprvrReport();
		apprvrRepBean.setMenuCode(955);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlApprvrReportModel model = new TrvlApprvrReportModel();
		model.initiate(context, session);
		LinkedMap hMap = model.getTravelTypes();
		apprvrRepBean.setTrvlTypeMap(hMap);
		model.terminate();
	}

	public Object getModel() {
		return apprvrRepBean;
	}

	public TrvlApprvrReport getApprvrBean() {
		return apprvrRepBean;
	}

	public void setApprvrBean(TrvlApprvrReport apprvrBean) {
		this.apprvrRepBean = apprvrBean;
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
		
		query += getprofileQuery(apprvrRepBean);
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
		TrvlApprvrReportModel model = new TrvlApprvrReportModel();
		model.initiate(context, session);
		model.deleteSavedReport(apprvrRepBean);
		boolean result = model.saveReportCriteria(apprvrRepBean);
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
				+ " WHERE REPORT_TYPE = 'TravelApprover'  ORDER BY  REPORT_ID ";

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
		String submitToMethod = "TravelApprvrReport_setDetails.action";

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
			TrvlApprvrReportModel model = new TrvlApprvrReportModel();
			model.initiate(context, session);
			apprvrRepBean.setBackFlag("");
			String[] labelNames = { getMessage("employee.id"),
					getMessage("tms.travelType"), getMessage("tms.advanceAmt"),
					getMessage("tms.empName"), getMessage("tms.applDate"),
					getMessage("tms.apprvdAmt"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.trvlPurpose") /* ,getMessage("tms.polViolated") */
			};
			model.getReport(apprvrRepBean, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}

	public String reset() throws Exception {
		try {

			logger.info("Hai reset");
			apprvrRepBean.setEmpToken("");
			apprvrRepBean.setEmpCode("");
			apprvrRepBean.setEmpName("");
			apprvrRepBean.setApplStatus("");
			apprvrRepBean.setApprvrAmtSelect("");
			apprvrRepBean.setApprvrAmtFrom("");
			apprvrRepBean.setApprvrAmtTo("");
			apprvrRepBean.setApprvdDateSelect("");
			apprvrRepBean.setApprvdDateFrm("");
			apprvrRepBean.setApprvdDateTo("");
			apprvrRepBean.setApplFor("");

			apprvrRepBean.setTravelCheckVal("");
			apprvrRepBean.setTravelCheck("");
			apprvrRepBean.setHidTravelSelf("");
			apprvrRepBean.setTrvlSelf("");
			apprvrRepBean.setHidTravelComp("");
			apprvrRepBean.setAccomCheck("");
			apprvrRepBean.setTrvlSelf("");
			apprvrRepBean.setTrvlComp("");
			apprvrRepBean.setAccomCheckVal("");
			apprvrRepBean.setHidAccomSelf("");
			apprvrRepBean.setAccomSelf("");
			apprvrRepBean.setHidAccomComp("");
			apprvrRepBean.setLocalCheckVal("");
			apprvrRepBean.setLocalCheck("");
			apprvrRepBean.setHidLocalSelf("");
			apprvrRepBean.setLocalSelf("");
			apprvrRepBean.setHidLocalComp("");
			apprvrRepBean.setLocalComp("");
			// column definitions

			apprvrRepBean.setEmpIdFlag("");
			apprvrRepBean.setTrvlTypeFlag("");
			apprvrRepBean.setAdvAmtFlag("");
			apprvrRepBean.setEmpNameFlag("");
			apprvrRepBean.setApplDateFlag("");
			apprvrRepBean.setApprvdAmtFlag("");
			apprvrRepBean.setTravelIdFlag("");
			apprvrRepBean.setTravelStrtDateFlag("");
			apprvrRepBean.setPolViolatedFlag("");
			apprvrRepBean.setTrvlPurposeFlag("");
			apprvrRepBean.setTravelEndDateFlag("");

			// advance filters

			apprvrRepBean.setApplDateSelect("");
			apprvrRepBean.setDOAfromDate("");
			apprvrRepBean.setDOAToDate("");
			apprvrRepBean.setSchTrvlDateSelect("");
			apprvrRepBean.setSTDfromDate("");
			apprvrRepBean.setSTDToDate("");
			apprvrRepBean.setTrvlStrtDateSelect("");
			apprvrRepBean.setTSDfromDate("");
			apprvrRepBean.setTSDToDate("");
			apprvrRepBean.setTrvlEndDateSelect("");
			apprvrRepBean.setTEDfromDate("");
			apprvrRepBean.setTEDToDate("");
			apprvrRepBean.setSchCycleSelect("");
			apprvrRepBean.setSchCycleFrom("");
			apprvrRepBean.setSchCycleTo("");
			apprvrRepBean.setTrvlType("");
			apprvrRepBean.setGradeId("");
			apprvrRepBean.setGradeName("");
			apprvrRepBean.setBrnchCode("");
			apprvrRepBean.setBranchName("");
			apprvrRepBean.setDeptCode("");
			apprvrRepBean.setDeptName("");

			apprvrRepBean.setSortBy("");
			apprvrRepBean.setSortByAsc("checked");
			apprvrRepBean.setSortByDsc("");
			apprvrRepBean.setSortByOrder("");
			apprvrRepBean.setThenBy1("");
			apprvrRepBean.setThenByOrder1Asc("checked");
			apprvrRepBean.setThenByOrder1Dsc("");
			apprvrRepBean.setThenByOrder1("");
			apprvrRepBean.setThenBy2("");
			apprvrRepBean.setThenByOrder2Asc("checked");
			apprvrRepBean.setThenByOrder2("");
			apprvrRepBean.setThenByOrder2Dsc("");
			apprvrRepBean.setHiddenSortBy("");
			apprvrRepBean.setHiddenThenBy1("");
			apprvrRepBean.setHiddenThenBy2("");

			apprvrRepBean.setHiddenColumns("");
			apprvrRepBean.setHidReportView("checked");
			apprvrRepBean.setHidReportRadio("");
			apprvrRepBean.setReportType("");

			apprvrRepBean.setMyPage("");
			apprvrRepBean.setShow("");

			apprvrRepBean.setSettingName("");
			apprvrRepBean.setReportTitle("");
			apprvrRepBean.setReportId("");
			apprvrRepBean.setBackFlag("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String setDetails() throws Exception {
		TrvlApprvrReportModel model = new TrvlApprvrReportModel();
		model.initiate(context, session);
		model.setDetailOnScreen(apprvrRepBean);
		model.terminate();
		if (apprvrRepBean.getSortByOrder().trim().equals("D"))
			apprvrRepBean.setSortByDsc("checked");
		if (apprvrRepBean.getSortByOrder().trim().equals("A"))
			apprvrRepBean.setSortByAsc("checked");
		if (apprvrRepBean.getThenByOrder1().trim().equals("D"))
			apprvrRepBean.setThenByOrder1Dsc("checked");
		if (apprvrRepBean.getThenByOrder1().trim().equals("A"))
			apprvrRepBean.setThenByOrder1Asc("checked");
		if (apprvrRepBean.getThenByOrder2().trim().equals("D"))
			apprvrRepBean.setThenByOrder2Dsc("checked");
		if (apprvrRepBean.getThenByOrder2().trim().equals("A"))
			apprvrRepBean.setThenByOrder2Asc("checked");
		return SUCCESS;
	}

	/**
	 * Display the report on screen
	 * 
	 * @return String
	 */
	public String viewOnScreen() {
		apprvrRepBean.setBackFlag("");
		try {
			TrvlApprvrReportModel model = new TrvlApprvrReportModel();
			model.initiate(context, session);
			// PASSING LABEL NAMES
			String[] labelNames = { getMessage("employee.id"),
					getMessage("tms.travelType"), getMessage("tms.advanceAmt"),
					getMessage("tms.empName"), getMessage("tms.applDate"),
					getMessage("tms.apprvdAmt"),
					getMessage("tms.trvlStrtDate"),
					getMessage("tms.trvlEndDate"),
					getMessage("tms.trvlPurpose") };/* ,getMessage("tms.polViolated") */
			// CALL TO MODEL
			model.callViewScreen(apprvrRepBean, request, labelNames);
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
		TrvlApprvrReportModel model = new TrvlApprvrReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		model.terminate();
		apprvrRepBean.setBackFlag("true");
		logger.info("getSortByOrder..." + apprvrRepBean.getSortByOrder());
		if (apprvrRepBean.getSortByOrder().trim().equals("D"))
			apprvrRepBean.setSortByDsc("checked");
		if (apprvrRepBean.getSortByOrder().trim().equals("A"))
			apprvrRepBean.setSortByAsc("checked");
		if (apprvrRepBean.getThenByOrder1().trim().equals("D"))
			apprvrRepBean.setThenByOrder1Dsc("checked");
		if (apprvrRepBean.getThenByOrder1().trim().equals("A"))
			apprvrRepBean.setThenByOrder1Asc("checked");
		if (apprvrRepBean.getThenByOrder2().trim().equals("D"))
			apprvrRepBean.setThenByOrder2Dsc("checked");
		if (apprvrRepBean.getThenByOrder2().trim().equals("A"))
			apprvrRepBean.setThenByOrder2Asc("checked");

		apprvrRepBean.setHiddenSortBy(apprvrRepBean.getSortBy());
		apprvrRepBean.setHiddenThenBy1(apprvrRepBean.getThenBy1());
		apprvrRepBean.setHiddenThenBy2(apprvrRepBean.getThenBy2());
		return SUCCESS;
	}

}
