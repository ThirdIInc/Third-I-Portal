package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlApplicantReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlApplicantReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlApplicantReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlApplicantReportAction.class);
	TrvlApplicantReport applRpt;

	public void prepare_local() throws Exception {
		applRpt = new TrvlApplicantReport();
		applRpt.setMenuCode(954);
	}

	public Object getModel() {
		return applRpt;
	}

	public TrvlApplicantReport getApplRpt() {
		return applRpt;
	}

	public void setApplRpt(TrvlApplicantReport applRpt) {
		this.applRpt = applRpt;
	}

	public String f9TrvlPurpose() throws Exception {

		String query = "SELECT  PURPOSE_NAME,PURPOSE_ID FROM HRMS_TMS_PURPOSE "
				+ " WHERE PURPOSE_STATUS='A' ORDER BY PURPOSE_NAME";

		String[] headers = { "Purpose Name" };

		String[] headerWidth = { "20" };

		String[] fieldNames = { "trvlPurpose", "trvlPurposeId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String saveReport() throws Exception {
		TrvlApplicantReportModel model = new TrvlApplicantReportModel();
		model.initiate(context, session);
		logger.info("Report id........." + applRpt.getReportId());
		model.deleteSavedReport(applRpt);
		boolean result = model.saveReportCriteria(applRpt);
		if (result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("duplicate"));
		model.terminate();
		return SUCCESS;
	}

	public String reset() throws Exception {

		applRpt.setBackFlag("");
		applRpt.setSavedReport("");
		applRpt.setReportId("");
		applRpt.setReportTitle("");
		applRpt.setApplDtSelect("");
		applRpt.setApplicationDate("");
		applRpt.setApplForSelect("");
		applRpt.setStsSelect("");
		applRpt.setTravelId("");
		applRpt.setTrvlPurpose("");
		applRpt.setTrvlPurposeId("");
		applRpt.setArrangeSelect("");
		applRpt.setTrvlId("");
		applRpt.setEmpId("");
		applRpt.setEmpName("");
		applRpt.setApplDate("");
		applRpt.setBranch("");
		applRpt.setTravelStartDate("");
		applRpt.setGrade("");
		applRpt.setTravelEndDate("");
		applRpt.setTravelType("");
		applRpt.setTravelPurpose("");
		applRpt.setTrvlAdvAmt("");
		applRpt.setApprover("");
		applRpt.setInitName("");
		applRpt.setSortByLabel("");
		applRpt.setSortBy("");
		applRpt.setSortByAsc("checked");
		applRpt.setSortByDsc("");
		applRpt.setSortByOrder("");
		applRpt.setThenBy1("");
		applRpt.setThenByLabel("");
		applRpt.setThenByOrder1Asc("checked");
		applRpt.setThenByOrder1Dsc("");
		applRpt.setThenByOrder1("");
		applRpt.setThenBy2("");
		applRpt.setThenByOrder2Asc("checked");
		applRpt.setThenByOrder2Dsc("");
		applRpt.setThenByOrder2("");
		applRpt.setColumnOrdering("");
		applRpt.setHiddenColumns("");
		applRpt.setHiddenSortBy("");
		applRpt.setHiddenThenBy1("");
		applRpt.setHiddenThenBy2("");
		applRpt.setSelectAdvanceFilter("");
		applRpt.setCostwiseSelect("");
		applRpt.setCostwise("");
		applRpt.setStartDate("");
		applRpt.setEndDate("");
		applRpt.setCyclewiseSelect("");
		applRpt.setCyclewise("");
		applRpt.setDurationwiselSelect("");
		applRpt.setDurationwise("");
		applRpt.setTrvlFlag("");
		applRpt.setAccoFlag("");
		applRpt.setConvFlag("");
		applRpt.setHidReportRadio("");
		applRpt.setReportView("");
		applRpt.setReportType("");
		applRpt.setSettingName("");
		applRpt.setReqStatus("");
		applRpt.setMyPage("");
		applRpt.setShow("");
		applRpt.setDataLength("");
		applRpt.setExportAll("");
		
		applRpt.setStartDtSelect("");
		applRpt.setEndDtSelect("");
		

		// added by krishna

		applRpt.setTravelCheckVal("");
		applRpt.setTravelCheck("");
		applRpt.setHidTravelSelf("");
		applRpt.setTrvlSelf("");
		applRpt.setHidTravelComp("");
		applRpt.setAccomCheck("");
		applRpt.setTrvlSelf("");
		applRpt.setTrvlComp("");
		applRpt.setAccomCheckVal("");
		applRpt.setHidAccomSelf("");
		applRpt.setAccomSelf("");
		applRpt.setHidAccomComp("");
		applRpt.setLocalCheckVal("");
		applRpt.setLocalCheck("");
		applRpt.setHidLocalSelf("");
		applRpt.setLocalSelf("");
		applRpt.setHidLocalComp("");
		applRpt.setLocalComp("");

		return SUCCESS;
	}

	public String viewOnScreen() {
		TrvlApplicantReportModel model = new TrvlApplicantReportModel();
		model.initiate(context, session);
		String[] labelNames = { getMessage("emp.id"), getMessage("emp.name"),
				getMessage("appl.date"), getMessage("branch"),
				getMessage("trvl.startdate"), getMessage("grade"),
				getMessage("trvl.enddate"), getMessage("trvl.type"),
				getMessage("trvl.purpose"), getMessage("trvl.advAmt"),
				getMessage("approver"), getMessage("init.name") };
		model.callViewScreen(applRpt, request, labelNames);
		model.terminate();
		return "viewOnScreen";
	}

	public String report() throws Exception {
		TrvlApplicantReportModel model = new TrvlApplicantReportModel();
		model.initiate(context, session);
		applRpt.setBackFlag("");
		String[] labelNames = { getMessage("emp.id"), getMessage("emp.name"),
				getMessage("appl.date"), getMessage("branch"),
				getMessage("trvl.startdate"), getMessage("grade"),
				getMessage("trvl.enddate"), getMessage("trvl.type"),
				getMessage("trvl.purpose"), getMessage("trvl.advAmt"),
				getMessage("approver"), getMessage("init.name") };
		model.getReport(applRpt, response, labelNames, request);
		model.terminate();
		return null;
	}

	/**
	 * Called on Back button in view screen
	 * 
	 * @return String returns to the mis filter page
	 */
	public String callBack() {
		TrvlApplicantReportModel model = new TrvlApplicantReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		model.terminate();
		applRpt.setBackFlag("true");
		if (applRpt.getSortByOrder().equals("D"))
			applRpt.setSortByDsc("checked");
		if (applRpt.getThenByOrder1().equals("D"))
			applRpt.setThenByOrder1Dsc("checked");
		if (applRpt.getThenByOrder2().equals("D"))
			applRpt.setThenByOrder2Dsc("checked");
		return SUCCESS;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE = 'TravelApplicant'  ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.setting"),
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
		String submitToMethod = "TrvlApplicantReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String setDetails() throws Exception {
		TrvlApplicantReportModel model = new TrvlApplicantReportModel();
		model.initiate(context, session);
		model.setDetailOnScreen(applRpt);
		model.terminate();
		if (applRpt.getSortByOrder().trim().equals("D"))
			applRpt.setSortByDsc("checked");
		if (applRpt.getSortByOrder().trim().equals("A"))
			applRpt.setSortByAsc("checked");
		if (applRpt.getThenByOrder1().trim().equals("D"))
			applRpt.setThenByOrder1Dsc("checked");
		if (applRpt.getThenByOrder1().trim().equals("A"))
			applRpt.setThenByOrder1Asc("checked");
		if (applRpt.getThenByOrder2().trim().equals("D"))
			applRpt.setThenByOrder2Dsc("checked");
		if (applRpt.getThenByOrder2().trim().equals("A"))
			applRpt.setThenByOrder2Asc("checked");

		return SUCCESS;
	}

}
