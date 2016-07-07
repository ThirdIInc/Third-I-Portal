package org.struts.action.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAdminReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAdminReportModel;
import org.struts.lib.ParaActionSupport;
/**
 * @author ayyappa
 * @date 20-04-2010
 */
public class TrvlAdminReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlAdminReportAction.class);

	TrvlAdminReport adminRepBean;

	public void prepare_local() throws Exception {
		adminRepBean = new TrvlAdminReport();
		adminRepBean.setMenuCode(956);

	}

	public Object getModel() {
		return adminRepBean;
	}

	/**
	 * @return the adminRepBean
	 */
	public TrvlAdminReport getAdminRepBean() {
		return adminRepBean;
	}

	/**
	 * @param adminRepBean
	 *            the adminRepBean to set
	 */
	public void setAdminRepBean(TrvlAdminReport adminRepBean) {
		this.adminRepBean = adminRepBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlAdminReportModel model = new TrvlAdminReportModel();
		model.initiate(context, session);
		LinkedMap fMap = model.getFinancialYears();
		adminRepBean.setFinanYearMap(fMap);
		LinkedMap typeMap = model.getTravelTypes();
		adminRepBean.setTrvlTypeMap(typeMap);
		LinkedMap purposeMap = model.getTravelPurposes();
		adminRepBean.setTrvlPurposeMap(purposeMap);
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

		String[] fieldNames = { "adminRepBean.gradeName", "adminRepBean.gradeId" };
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

		String[] fieldNames = { "adminRepBean.branchName", "adminRepBean.branchId" };
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

		String[] fieldNames = { "adminRepBean.deptName", "adminRepBean.deptId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";
		 	 
		query += getprofileQuery(adminRepBean);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "25", "75" };

		String[] fieldNames = { "adminRepBean.empToken", "adminRepBean.empName","adminRepBean.empId" };
		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReport() throws Exception {
		TrvlAdminReportModel model = new TrvlAdminReportModel();
		model.initiate(context, session);
		logger.info("Report id........." + adminRepBean.getReportId());
		model.deleteSavedReport(adminRepBean);
		boolean result = model.saveReportCriteria(adminRepBean);
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
				+ " WHERE REPORT_TYPE = 'TravelAdmin'  ORDER BY  REPORT_ID ";

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

		String[] fieldNames = { "settingName", "adminRepBean.reportTitle",
				"adminRepBean.reportId" };

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
		String submitToMethod = "TravelAdminReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	public String setDetails() throws Exception {
		TrvlAdminReportModel model = new TrvlAdminReportModel();
		reset();
		logger.info("report id -- "+adminRepBean.getReportId());
		model.initiate(context, session);
		model.setDetailOnScreen(adminRepBean);
		model.terminate();
		if(adminRepBean.getSortByOrder().trim().equals("D"))
			adminRepBean.setSortByDsc("checked");
		if(adminRepBean.getSortByOrder().trim().equals("A"))
			adminRepBean.setSortByAsc("checked");
		if(adminRepBean.getThenByOrder1().trim().equals("D"))
			adminRepBean.setThenByOrder1Dsc("checked");
		if(adminRepBean.getThenByOrder1().trim().equals("A"))
			adminRepBean.setThenByOrder1Asc("checked");
		if(adminRepBean.getThenByOrder2().trim().equals("D"))
			adminRepBean.setThenByOrder2Dsc("checked");
		if(adminRepBean.getThenByOrder2().trim().equals("A"))
			adminRepBean.setThenByOrder2Asc("checked");
		return SUCCESS;
	}
	
	public String reset() throws Exception {
		adminRepBean.setBackFlag("");
		adminRepBean.setSavedReport("");
		
		adminRepBean.setFinanYear("");
		adminRepBean.setGradeId("");
		adminRepBean.setGradeName("");
		adminRepBean.setTrvlType("");
		adminRepBean.setAppliFor("");
		adminRepBean.setTravelCheck("");
		adminRepBean.setTrvlRadio("");
		adminRepBean.setHidTravelSelf("");
		adminRepBean.setHidTravelComp("checked");
		adminRepBean.setAccomCheck("");
		adminRepBean.setAccomRadio("");
		adminRepBean.setHidAccomSelf("");
		adminRepBean.setHidAccomComp("checked");
		adminRepBean.setLocalCheck("");
		adminRepBean.setLocalRadio("");
		adminRepBean.setHidLocalSelf("");
		adminRepBean.setHidLocalComp("checked");
		
		adminRepBean.setEmpIdFlag("");
		adminRepBean.setAppliNameFlag("");
		adminRepBean.setGradeFlag("");
		adminRepBean.setAppliDateFlag("");
		adminRepBean.setTravelIdFlag("");
		adminRepBean.setStartDateFlag("");
		adminRepBean.setEndDateFlag("");
		adminRepBean.setPurposeFlag("");
		adminRepBean.setTypeFlag("");
		adminRepBean.setApprvdAmtFlag("");
		//adminRepBean.setAdvanceAmtFlag("");
		adminRepBean.setTravelCostFlag("");
		adminRepBean.setAccomCostFlag("");
		adminRepBean.setLocalCostFlag("");
		
		adminRepBean.setSortBy("");
		adminRepBean.setSortByAsc("checked");
		adminRepBean.setSortByDsc("");
		adminRepBean.setSortByOrder("");
		adminRepBean.setHiddenSortBy("");
		adminRepBean.setThenBy1("");
		adminRepBean.setThenByOrder1Asc("checked");
		adminRepBean.setThenByOrder1Dsc("");
		adminRepBean.setThenByOrder1("");
		adminRepBean.setHiddenThenBy1("");
		adminRepBean.setThenBy2("");
		adminRepBean.setThenByOrder2Asc("checked");
		adminRepBean.setThenByOrder2Dsc("");
		adminRepBean.setThenByOrder2("");
		adminRepBean.setHiddenThenBy2("");
		
		adminRepBean.setColumnOrdering("");
		adminRepBean.setHiddenColumns("");
		
		adminRepBean.setTrvlPurpose("");
		adminRepBean.setBranchId("");
		adminRepBean.setBranchName("");
		adminRepBean.setDeptId("");
		adminRepBean.setDeptName("");
		adminRepBean.setEmpId("");
		adminRepBean.setEmpName("");
		adminRepBean.setEmpToken("");
		
		adminRepBean.setHidReportView("checked");
		adminRepBean.setHidReportRadio("");
		adminRepBean.setReportType("");
		
		adminRepBean.setReqStatus("");		
		
		return SUCCESS;
	}
	
	/**
	 * Display the report on screen
	 * @return String
	 */
	public String viewOnScreen() {
		adminRepBean.setBackFlag("");
		logger.info("view on screen");
		try{
			TrvlAdminReportModel model = new TrvlAdminReportModel();
			model.initiate(context, session);
			//PASSING LABEL NAMES
			String labelNames[] = {getMessage("tms.empid"),getMessage("tms.employeename"),
					 getMessage("tms.grade"),getMessage("tms.applicationdate"), 
					getMessage("tms.startdate"), getMessage("tms.enddate"),
					getMessage("tms.purpose"), getMessage("tms.traveltype"),
					getMessage("tms.approvedamt"),getMessage("tms.travelcost"),
					getMessage("tms.accomcost"),getMessage("tms.localconcost")};
			//CALL TO MODEL
			model.callViewScreen(adminRepBean, request, labelNames);
			model.terminate();
		}catch(Exception e){
			logger.error("Exception in viewOnScreen -- "+e);
		}
		return "viewOnScreen";
	}
	
	/**
	 * Generate report in PDF/XLS or DOC
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		try {
			TrvlAdminReportModel model = new TrvlAdminReportModel();
			model.initiate(context, session);
			adminRepBean.setBackFlag("");
			String labelNames[] = {getMessage("tms.empid"),getMessage("tms.employeename"),
					 getMessage("tms.grade"),getMessage("tms.applicationdate"),
					getMessage("tms.startdate"), getMessage("tms.enddate"),
					getMessage("tms.purpose"), getMessage("tms.traveltype"),
					getMessage("tms.approvedamt"),getMessage("tms.travelcost"),
					getMessage("tms.accomcost"),getMessage("tms.localconcost")};
			model.getReport(adminRepBean, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}

	/**
	 * Called on Back button in view screen
	 * @return String
	 * returns to the Travel Accountant filter page
	 */
	public String callBack() {

		adminRepBean.setBackFlag("true");
		logger.info("getSortByOrder..."+adminRepBean.getSortByOrder());
		if(adminRepBean.getSortByOrder().trim().equals("D"))
			adminRepBean.setSortByDsc("checked");
		if(adminRepBean.getSortByOrder().trim().equals("A"))
			adminRepBean.setSortByAsc("checked");
		if(adminRepBean.getThenByOrder1().trim().equals("D"))
			adminRepBean.setThenByOrder1Dsc("checked");
		if(adminRepBean.getThenByOrder1().trim().equals("A"))
			adminRepBean.setThenByOrder1Asc("checked");
		if(adminRepBean.getThenByOrder2().trim().equals("D"))
			adminRepBean.setThenByOrder2Dsc("checked");
		if(adminRepBean.getThenByOrder2().trim().equals("A"))
			adminRepBean.setThenByOrder2Asc("checked");
		
		adminRepBean.setHiddenSortBy(adminRepBean.getSortBy());
		adminRepBean.setHiddenThenBy1(adminRepBean.getThenBy1());
		adminRepBean.setHiddenThenBy2(adminRepBean.getThenBy2());
		return SUCCESS;
	}	
	
}
