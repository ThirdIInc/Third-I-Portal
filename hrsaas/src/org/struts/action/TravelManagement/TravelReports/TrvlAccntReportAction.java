package org.struts.action.TravelManagement.TravelReports;

import org.struts.lib.ParaActionSupport;
import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAccntReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAccntReportModel;
import org.paradyne.model.extraWorkBenefits.AttReglMisReportModel;

/**
 * @author ayyappa
 * @date 13-04-2010
 */
public class TrvlAccntReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TrvlAccntReportAction.class);
	
	TrvlAccntReport accntRepBean;

	public TrvlAccntReport getAccntRepBean() {
		return accntRepBean;
	}

	public void setAccntRepBean(TrvlAccntReport accntRepBean) {
		this.accntRepBean = accntRepBean;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return accntRepBean;
	}
	
	public void prepare_local() throws Exception {
		accntRepBean = new TrvlAccntReport();
		accntRepBean.setMenuCode(981);
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlAccntReportModel model = new TrvlAccntReportModel();
		model.initiate(context, session);
		LinkedMap typeMap = model.getTravelTypes();
		accntRepBean.setTrvlTypeMap(typeMap);
		LinkedMap purposeMap = model.getTravelPurposes();
		accntRepBean.setTrvlPurposeMap(purposeMap);
		model.terminate();
	}
	
	/**
	 * Search grade details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9grade() throws Exception {

		String query = " SELECT NVL(CADRE_NAME,' '),CADRE_ID FROM HRMS_CADRE "
					+"  ORDER BY  CADRE_ID ";
		String[] headers = { getMessage("tms.grade") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "accntRepBean.gradeName", "accntRepBean.gradeId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	/**
	 * Search branch details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {

		String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER "
					+ " ORDER BY  CENTER_ID ";
		String[] headers = { getMessage("tms.branch") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "accntRepBean.branchName", "accntRepBean.branchId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	/**
	 * Search department details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9department() throws Exception {

		String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  "
				+ " ORDER BY  DEPT_ID ";
		String[] headers = { getMessage("tms.dept") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "accntRepBean.deptName", "accntRepBean.deptId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

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
		TrvlAccntReportModel model = new TrvlAccntReportModel();
		model.initiate(context, session);
		logger.info("Report id........." + accntRepBean.getReportId());
		model.deleteSavedReport(accntRepBean);
		boolean result = model.saveReportCriteria(accntRepBean);
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
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE = 'TravelAccountant'  ORDER BY  REPORT_ID ";

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

		String[] fieldNames = { "settingName", "accntRepBean.reportTitle",
				"accntRepBean.reportId" };

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
		String submitToMethod = "TravelAccntReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	public String setDetails() throws Exception {
		TrvlAccntReportModel model = new TrvlAccntReportModel();
		reset();
		logger.info("report id -- "+accntRepBean.getReportId());
		model.initiate(context, session);
		model.setDetailOnScreen(accntRepBean);
		model.terminate();
		if(accntRepBean.getSortByOrder().trim().equals("D"))
			accntRepBean.setSortByDsc("checked");
		if(accntRepBean.getSortByOrder().trim().equals("A"))
			accntRepBean.setSortByAsc("checked");
		if(accntRepBean.getThenByOrder1().trim().equals("D"))
			accntRepBean.setThenByOrder1Dsc("checked");
		if(accntRepBean.getThenByOrder1().trim().equals("A"))
			accntRepBean.setThenByOrder1Asc("checked");
		if(accntRepBean.getThenByOrder2().trim().equals("D"))
			accntRepBean.setThenByOrder2Dsc("checked");
		if(accntRepBean.getThenByOrder2().trim().equals("A"))
			accntRepBean.setThenByOrder2Asc("checked");
		return SUCCESS;
	}
	
	public String reset() throws Exception {
		accntRepBean.setBackFlag("");
		accntRepBean.setSavedReport("");
		
		accntRepBean.setStatus("");
		accntRepBean.setTypeExp("");
		accntRepBean.setGradeId("");
		accntRepBean.setGradeName("");
		accntRepBean.setBranchId("");
		accntRepBean.setBranchName("");
		accntRepBean.setDeptId("");
		accntRepBean.setDeptName("");
		accntRepBean.setAdvAmtSelect("");
		accntRepBean.setAdvAmtFrom("");
		accntRepBean.setAdvAmtTo("");
		accntRepBean.setClaimsetSelect("");
		accntRepBean.setClaimsetFrom("");
		accntRepBean.setClaimsetTo("");
		accntRepBean.setFromDateSelect("");
		accntRepBean.setFromDateFromDate("");
		accntRepBean.setFromDateToDate("");
		accntRepBean.setToDateSelect("");
		accntRepBean.setToDateFromDate("");
		accntRepBean.setToDateToDate("");
		accntRepBean.setAppliFor("");
		accntRepBean.setTravelCheck("");
		accntRepBean.setTrvlRadio("");
		accntRepBean.setHidTravelSelf("");
		accntRepBean.setHidTravelComp("checked");
		accntRepBean.setAccomCheck("");
		accntRepBean.setAccomRadio("");
		accntRepBean.setHidAccomSelf("");
		accntRepBean.setHidAccomComp("checked");
		accntRepBean.setLocalCheck("");
		accntRepBean.setLocalRadio("");
		accntRepBean.setHidLocalSelf("");
		accntRepBean.setHidLocalComp("checked");
		
		accntRepBean.setAppliNameFlag("");
		accntRepBean.setGradeFlag("");
		accntRepBean.setBranchFlag("");
		//accntRepBean.setTravelIdFlag("");
		accntRepBean.setEmpIdFlag("");
		accntRepBean.setStartDateFlag("");
		accntRepBean.setEndDateFlag("");
		accntRepBean.setStatusFlag("");
		accntRepBean.setPurposeFlag("");
		accntRepBean.setTypeFlag("");
		accntRepBean.setRequestFlag("");
		accntRepBean.setAdvAmtFlag("");
		
		accntRepBean.setSortBy("");
		accntRepBean.setSortByAsc("checked");
		accntRepBean.setSortByDsc("");
		accntRepBean.setSortByOrder("");
		accntRepBean.setHiddenSortBy("");
		accntRepBean.setThenBy1("");
		accntRepBean.setThenByOrder1Asc("checked");
		accntRepBean.setThenByOrder1Dsc("");
		accntRepBean.setThenByOrder1("");
		accntRepBean.setHiddenThenBy1("");
		accntRepBean.setThenBy2("");
		accntRepBean.setThenByOrder2Asc("checked");
		accntRepBean.setThenByOrder2Dsc("");
		accntRepBean.setThenByOrder2("");
		accntRepBean.setHiddenThenBy2("");
		
		accntRepBean.setColumnOrdering("");
		accntRepBean.setHiddenColumns("");
		
		accntRepBean.setTrvlType("");
		accntRepBean.setTrvlPurpose("");
		accntRepBean.setStartDateSelect("");
		accntRepBean.setStartFromDate("");
		accntRepBean.setStartToDate("");
		accntRepBean.setEndDateSelect("");
		accntRepBean.setEndFromDate("");
		accntRepBean.setEndToDate("");
		
		accntRepBean.setHidReportView("checked");
		accntRepBean.setHidReportRadio("");
		accntRepBean.setReportType("");
		
		//accntRepBean.setSettingName("");
		accntRepBean.setReqStatus("");
		
		return SUCCESS;
	}
	
	/**
	 * Display the report on screen
	 * @return String
	 */
	public String viewOnScreen() {
		accntRepBean.setBackFlag("");
		logger.info("view on screen");
		try{
			TrvlAccntReportModel model = new TrvlAccntReportModel();
			model.initiate(context, session);
			//PASSING LABEL NAMES
			String labelNames[] = {getMessage("tms.applicantname"), getMessage("tms.grade"),
					getMessage("tms.branch"), getMessage("tms.empid"),
					getMessage("tms.startdate"), getMessage("tms.enddate"),
					getMessage("tms.status"), getMessage("tms.purpose"),
					getMessage("tms.traveltype"), getMessage("tms.advanceamount")};
			//CALL TO MODEL
			model.callViewScreen(accntRepBean, request, labelNames);
			model.terminate();
		}catch(Exception e){
			logger.error("Exception in viewOnScreen -- "+e);
		}
		return "viewOnScreen";
	}
	
	
	/**
	 * Called on Back button in view screen
	 * @return String
	 * returns to the Travel Accountant filter page
	 */
	public String callBack() {

		accntRepBean.setBackFlag("true");
		logger.info("getSortByOrder..."+accntRepBean.getSortByOrder());
		if(accntRepBean.getSortByOrder().trim().equals("D"))
			accntRepBean.setSortByDsc("checked");
		if(accntRepBean.getSortByOrder().trim().equals("A"))
			accntRepBean.setSortByAsc("checked");
		if(accntRepBean.getThenByOrder1().trim().equals("D"))
			accntRepBean.setThenByOrder1Dsc("checked");
		if(accntRepBean.getThenByOrder1().trim().equals("A"))
			accntRepBean.setThenByOrder1Asc("checked");
		if(accntRepBean.getThenByOrder2().trim().equals("D"))
			accntRepBean.setThenByOrder2Dsc("checked");
		if(accntRepBean.getThenByOrder2().trim().equals("A"))
			accntRepBean.setThenByOrder2Asc("checked");
		
		accntRepBean.setHiddenSortBy(accntRepBean.getSortBy());
		accntRepBean.setHiddenThenBy1(accntRepBean.getThenBy1());
		accntRepBean.setHiddenThenBy2(accntRepBean.getThenBy2());
		return SUCCESS;
	}	
	
	/**
	 * Generate report in PDF/XLS or DOC
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		try {
			TrvlAccntReportModel model = new TrvlAccntReportModel();
			model.initiate(context, session);
			accntRepBean.setBackFlag("");
			String labelNames[] = {getMessage("tms.applicantname"), getMessage("tms.grade"),
					getMessage("tms.branch"), getMessage("tms.empid"),
					getMessage("tms.startdate"), getMessage("tms.enddate"),
					getMessage("tms.status"), getMessage("tms.purpose"),
					getMessage("tms.traveltype"), getMessage("tms.advanceamount")};
			model.getReport(accntRepBean, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}
	
}
