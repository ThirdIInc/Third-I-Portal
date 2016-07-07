package org.struts.action.PAS.GoalSetting;

import org.paradyne.bean.PAS.GoalSetting.GoalInitialization;
import org.paradyne.model.PAS.GoalSetting.GoalInitializationModel;
import org.paradyne.model.admin.master.QualificationModel;
//import org.paradyne.model.appraisal.EmpAppraisalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1383
 * 
 */
public class GoalInitializationAction extends ParaActionSupport {

	GoalInitialization goalInitialization;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(GoalInitializationAction.class);

	public void prepare_local() throws Exception {

		goalInitialization = new GoalInitialization();
		goalInitialization.setMenuCode(1071);

	}

	public Object getModel() {
		return goalInitialization;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input() {
		try {
			GoalInitializationModel model = new GoalInitializationModel();
			model.initiate(context, session);
			model.data(goalInitialization, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		goalInitialization.setEligibleEmployeeReportFlag(true);
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/**
	 * Use for Add new Goal
	 * @return
	 */
	public String addNew() {
		try {
			getNavigationPanel(2);
			System.out.println("In addNew Method >>>>>>>>>>>>>>>>>>>>>>>>");
			
			return "addnew";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * Use for publish goal.
	 * @return
	 */
	public String publishGoal() {

		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		try {
			save();
			model.updatePublishStatus(goalInitialization.getGoalId(), "2");
			goalInitialization.setGoalPublishStatus("2");

		} catch (Exception e) {
			// TODO: handle exception
		}
		if(goalInitialization.getDupData()=="Y"){
			getNavigationPanel(2);
		}else{
			getNavigationPanel(4);
			goalInitialization.setEnableAll("N");
		}
		goalInitialization.setDupData("");
		
		return "addnew";
	}

	/**
	 * Use for edit existing goal.
	 * @return
	 */
	public String callforedit() {
		String autoGoalCode = request.getParameter("autoCode");
		String status = request.getParameter("status");
		System.out.println("status&&&&&&&"+status);
		try {
			System.out.println("-------------EDIT--------------");
			GoalInitializationModel model = new GoalInitializationModel();
			model.initiate(context, session);
			System.out.println("-------------status--------------"+status);
			model.setData(autoGoalCode, goalInitialization);
			model.showReviewDates(goalInitialization);
			model.categoryData(goalInitialization);
			model.goalRatingDetails(goalInitialization);
			goalInitialization.setDeleteKey("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//goalInitialization.setEnableAll("N");
		if (status.equals("Draft") || status.equals("1")) {
			getNavigationPanel(3);
			goalInitialization.setEnableAll("N");
		} else {
			goalInitialization.setCalcRatingFlag(true);
			//goalInitialization.setEditFlag(true);
			getNavigationPanel(4);
			goalInitialization.setEnableAll("N");
		}
		goalInitialization.setEligibleEmployeeReportFlag(true);
		request.setAttribute("stat", status);
		return "addnew";
	}
	
	public String callBack()
	{
		getNavigationPanel(3);
		return "addnew";
	}

	/**
	 * Use for save & update goal.
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		GoalInitializationModel model = new GoalInitializationModel();
		String result = "";
		String[] category = request.getParameterValues("category");
		String[] categoryWeightage = request.getParameterValues("catWeightage");
		
		//String multipleManagerRatingRadio=request.getParameter("multipleManagerRatingRadio");
		model.initiate(context, session);
		if (String.valueOf(goalInitialization.getGoalId()).equals("")) {
			logger.info("into add method");
			result = model.addGoal(goalInitialization, category,categoryWeightage, request);
			if (result == "Saved") {
				addActionMessage("Record saved successfully.");
				getNavigationPanel(3);
				model.setData(goalInitialization.getGoalId(),goalInitialization);
				goalInitialization.setEnableAll("N");
			} else if(result=="Duplicate"){
				addActionMessage("Duplicate Record found.");
				//model.categoryData(goalInitialization);
				goalInitialization.setDupData("Y");
				getNavigationPanel(2);
			}else {
				addActionMessage("Goal can not be added.");
				getNavigationPanel(2);
			}

		} else {
			boolean result1;
			result = model.updateGoal(goalInitialization, category,categoryWeightage, request);
			String status= goalInitialization.getGoalPublishStatus();
			if (result == "modified") {
				//model.categoryData(goalInitialization);
				
				model.goalRatingDetails(goalInitialization);
				
				if(status.equals("1")){
					
				getNavigationPanel(3);
				addActionMessage("Record updated successfully");
				
				}else if(status.equals("2")){
					
					getNavigationPanel(6);
					addActionMessage("Record published successfully");
					
				}
				goalInitialization.setEnableAll("N");
			}else if(result=="Duplicate"){
				addActionMessage("Duplicate Record found.");
				goalInitialization.setDupData("Y");
				getNavigationPanel(2);
			} else {
				addActionMessage("Goal can not be updated.");
				getNavigationPanel(2);
			}

		}
		model.categoryData(goalInitialization);
		model.getDateRow(goalInitialization, request);
		model.terminate();
		return "addnew";
	}

	/**
	 * Use for reset values of goal initialization form.
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		goalInitialization.setGoalName("");
		goalInitialization.setEmpIdTxt("");
		goalInitialization.setGoalfromDate("");
		goalInitialization.setGoaltoDate("");
		goalInitialization.setAppraisalCode("");
		goalInitialization.setAppraisalName("");
		goalInitialization.setReportingType("");
		goalInitialization.setGoalWeightage("");
		goalInitialization.setAppWeightage("");
		goalInitialization.setSelfAsstWeightage("");
		goalInitialization.setManagerAsstWeightage("");
		goalInitialization.setRatingrangeupto("");
		goalInitialization.setEscalationMailId("");
		goalInitialization.setTotalCatWeightage("");
		goalInitialization.setTotalreviewWeight("");
		goalInitialization.setRatingrangedescrp("");
		
		getNavigationPanel(2);
		return "addnew";
	}

	/**
	 * Use for delete goal.
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		String autoGoalCode = request.getParameter("autoCode");
		System.out.println("=========AUTO GOAL CODE======="+autoGoalCode);
		if (model.deleteGoal(goalInitialization.getGoalId())) {
			getNavigationPanel(1);
			addActionMessage("Goal deleted successfully");
			model.data(goalInitialization, request);
		} else {
			model.setData(autoGoalCode, goalInitialization);
			model.showReviewDates(goalInitialization);
			model.categoryData(goalInitialization);
			model.goalRatingDetails(goalInitialization);
			addActionMessage("Goal can not be deleted");
			
			getNavigationPanel(3);
		}
		
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Use for cancel goal initialization application .
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		goalInitialization.setGoalName("");
		goalInitialization.setGoalfromDate("");
		goalInitialization.setGoaltoDate("");
		goalInitialization.setGoalId("");
		getNavigationPanel(1);
		input();
		return SUCCESS;
	}

	/**
	 * Use for back page.
	 * @return
	 * @throws Exception
	 */
	public String back() throws Exception {
		goalInitialization.setGoalName("");
		goalInitialization.setGoalfromDate("");
		goalInitialization.setGoaltoDate("");
		goalInitialization.setGoalId("");
		getNavigationPanel(1);
		input();
		return SUCCESS;
	}

	/**
	 * Use for edit goal initialization record.
	 * @return String.
	 */
	public String edit() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		goalInitialization.setDeleteKey("true");
		model.setData(goalInitialization.getGoalId(), goalInitialization);
		goalInitialization.setGoalPublishStatus("1");
		String status= goalInitialization.getGoalPublishStatus();
		System.out.println("Action-------Status"+status);
		//model.getDateRow(goalInitialization, request);
		model.showReviewDates(goalInitialization);
		model.categoryData(goalInitialization);
		model.goalRatingDetails(goalInitialization);
		getNavigationPanel(2);
		request.setAttribute("stat", status);
		model.terminate();
		return "addnew";
	}
	
	
	
	public String editUnlock() {
		
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		goalInitialization.setDeleteKey("true");
		model.setData(goalInitialization.getGoalId(), goalInitialization);
		goalInitialization.setGoalPublishStatus("1");
		model.showReviewDates(goalInitialization);
		model.categoryData(goalInitialization);
		model.goalRatingDetails(goalInitialization);
		model.unLock(goalInitialization.getGoalId());
		goalInitialization.setGoalPublishStatus("1");
		getNavigationPanel(2);
		model.terminate();
		return "addnew";
	}

	/**
	 * Use for set search goal record.
	 * @return String.
	 */
	public String setSearchedRecord() {
		try {

			GoalInitializationModel model = new GoalInitializationModel();
			model.initiate(context, session);
			model.setData(goalInitialization.getGoalId(), goalInitialization);
			model.showReviewDates(goalInitialization);
			model.categoryData(goalInitialization);
			// model.goalRatingDetails(goalInitialization);
			logger.info("bean.getStastus=="
					+ goalInitialization.getGoalPublishStatus());
			if (goalInitialization.getGoalPublishStatus().equals("1")) {
				getNavigationPanel(3);
			} else {
				goalInitialization.setCalcRatingFlag(true);
				getNavigationPanel(4);
			}

			goalInitialization.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "addnew";
	}

	/**
	 * List of all existing goal.
	 * @return String.
	 * @throws Exception
	 */
	public String f9action() throws Exception {

		String query = " SELECT GOAL_NAME, TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),DECODE(GOAL_PUBLISH_STATUS,'1','Draft','2','Published'),GOAL_ID FROM HRMS_GOAL_CONFIG";

		String[] headers = { getMessage("goalPeriod"),
				getMessage("goalFromDate"), getMessage("goalToDate"),
				getMessage("goalStatus") };

		String[] headerWidth = { "30", "30", "30", "15" };

		String[] fieldNames = { "goalName", "goalfromDate", "goaltoDate",
				"goalPublishStatus", "goalId" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlag = "true";

		String submitToMethod = "GoalInitialization_setSearchedRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Add new review date & review weightage in to list.
	 * @return String.
	 */
	public String addDateRow() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		model.addDateRow(goalInitialization, request);
		
		String[] srNo = request.getParameterValues("srNo");
		String[] category = request.getParameterValues("category");
		String[] weightage = request.getParameterValues("reviewWeightage");
		
		String[] categoryWeightage = request.getParameterValues("catWeightage");
	

		/*if (category != null) {
			model.getDuplicate(goalInitialization, srNo, category, 1);
		}*/
		if (category!=null) {
			model.addItem(goalInitialization, srNo, category,categoryWeightage, 0);
		}
		model.terminate();
		
		getNavigationPanel(2);
		model.terminate();
		return "addnew";
	}

	/**
	 * Use for show review date list.
	 * @return String.
	 */
	public String showReviewDates() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		model.showReviewDates(goalInitialization);
		getNavigationPanel(2);
		model.terminate();
		return "addnew";
	}

	/**
	 * Use for remove review date record from review date list.
	 * @return String.
	 */
	public String removeDate() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		model.removeDate(goalInitialization, request);
		goalInitialization.setParaId("");
		String[] srNo = request.getParameterValues("srNo");
		String[] category = request.getParameterValues("category");
		String[] categoryWeightage = request.getParameterValues("catWeightage");

		/*if (category != null) {
			model.getDuplicate(goalInitialization, srNo, category, 1);
		}*/
		if (category!=null) {
			model.addItem(goalInitialization, srNo, category,categoryWeightage, 0);
		}
		
		
		getNavigationPanel(2);
		model.terminate();
		return "addnew";

	}

	/**
	 * Use for delete multiple goal record.
	 * @return String.
	 */
	public String multipleDelete() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		String goalIdForDelete = goalInitialization.getParaId();
		logger.info("goalIdForDelete====" + goalIdForDelete);
		if (goalIdForDelete.lastIndexOf(",") == goalIdForDelete.length() - 1) {
			goalIdForDelete = goalIdForDelete.substring(0, goalIdForDelete
					.length() - 1);
		}
		logger.info("goalIdForDelete after====" + goalIdForDelete);
		if (model.deleteGoal(goalIdForDelete)) {

			addActionMessage("Goals deleted successfully");
		} else {
			addActionMessage("Goals can not be deleted");
		}
		getNavigationPanel(1);
		model.data(goalInitialization, request);
		model.terminate();

		return SUCCESS;
	}

	/**
	 * Use for apprisal calaendar list.
	 * @return String.
	 * @throws Exception
	 */
	public String f9AppCal() throws Exception {

		String query = " SELECT APPR_CAL_CODE,APPR_ID FROM PAS_APPR_CALENDAR	 ORDER BY APPR_ID";

		String[] headers = { "Appraisal Code" };

		String[] headerWidth = { "50" };

		String[] fieldNames = { "appraisalName", "appraisalCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		logger.info("4");
		return "f9page";
	}

	/**
	 * Use for add category into category list.
	 * @return String.
	 * @throws Exception
	 */
	public String addItem() throws Exception {
		String[] srNo = request.getParameterValues("srNo");
		String[] category = request.getParameterValues("category");
		String[] categoryWeightage = request.getParameterValues("catWeightage");
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);

		if (category != null) {
			model.getDuplicate(goalInitialization, srNo, category,categoryWeightage, 1);
		}
		if (goalInitialization.getHiddenEdit().equals("")) {
			model.addItem(goalInitialization, srNo, category,categoryWeightage, 1);
		}
		model.terminate();
		goalInitialization.setCategory1("");
		goalInitialization.setSubcode("");
		goalInitialization.setHiddenEdit("");
		goalInitialization.setParaId("");
		goalInitialization.setCategoryWeightage("");
		getNavigationPanel(2);
		model.getDateRow(goalInitialization, request);
		return "addnew";
	}

	/*
	 * public String deleteCategoryData() throws Exception {
	 * 
	 * String code[] = request.getParameterValues("paraId"); String[] category =
	 * request.getParameterValues("category"); GoalInitializationModel model =
	 * new GoalInitializationModel(); model.initiate(context, session);
	 * model.delCatagoryData(goalInitialization, code, category);
	 * model.getDateRow(goalInitialization, request); getNavigationPanel(2);
	 * model.terminate(); goalInitialization.setHiddenEdit(""); return "addnew";
	 *  }
	 */
	/**
	 * Use for remove goal category from category list.
	 * @return String.
	 */
	public String removeGoalsCategories() {
		String[] srNo = request.getParameterValues("srNo");
		String[] category = request.getParameterValues("category");
		String[] categoryWeightage = request.getParameterValues("catWeightage");
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		model.removeGoals(goalInitialization, category, srNo,categoryWeightage, request);
		goalInitialization.setCategory1("");
		goalInitialization.setSrNo("");
		goalInitialization.setSubcode("");
		goalInitialization.setHiddenEdit("");
		goalInitialization.setParaId("");
		model.getDateRow(goalInitialization, request);
		model.terminate();
		getNavigationPanel(2);
		return "addnew";
	}
	
	/**
	 * Use for calculate goal score.
	 * @return String.
	 */
	public String calculateRating(){
		try {
			GoalInitializationModel model = new GoalInitializationModel();
			String goalId = goalInitialization.getGoalId();
			model.initiate(context, session);
			model.getCalculatedRatingsByGoalId(goalInitialization, goalId);
			model.setData(goalId, goalInitialization);
			model.showReviewDates(goalInitialization);
			model.categoryData(goalInitialization);
			model.goalRatingDetails(goalInitialization);
			model.terminate();
			getNavigationPanel(4);
			goalInitialization.setCalcRatingFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addnew";
	}
	
	/**
	 * following function is used to get the Pdf Report
	 */
	public String report(){
		String goalId = goalInitialization.getGoalId();
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context,session);
		model.generateReport(goalInitialization,request,response,goalId,"");
		model.terminate();
		return null;
	}
	
	
	
	/**
	 * To Generate Report for Eligible Employees. 
	 */
	
	public String eligibleEmployeeReport(){
		String goalId = goalInitialization.getGoalId();
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context,session);
		model.generateEligibleEmployeeReport(goalInitialization,request,response,goalId,"");
		model.terminate();
		return null;
	}
	
	
	/**
	 * Use for generate eligible employee of perticular goal.
	 * @return String.
	 */
	public String empReport(){
		String goalId = goalInitialization.getGoalId();
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context,session);
		/*String[]label={"Sr.No",getMessage("emp.token"),getMessage("emp.name"),
		getMessage("dept"),getMessage("branch"),getMessage("designation")
		,getMessage("grade"),getMessage("appraisal.scroe"),getMessage("goal.scroe"),getMessage("final.scroe")};*/
		model.generateEmpReport(goalInitialization,response,request,goalId);
		model.terminate();
		return null;
	}
	
	/**
	 * USE FOR GET ELIGIBLE EMPLOYEE LIST DATA FOR GOAL.
	 * @return String.
	 */
	public String eligibleEmployee(){
		
		String goalId = goalInitialization.getGoalId();
		String status=goalInitialization.getGoalPublishStatus();
		System.out.println("status---->"+status);
		System.out.println("goalId="+goalId);
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context,session);
		//model.getEligibleEmployeeListData(goalInitialization,request);
		
		/*if(status.equals("2"))
		{
			
			goalInitialization.setDeleteKey("true");
			
		}*/
		
		goalInitialization.setGoalId(goalId);
		model.terminate();
		return "eligibleEmployee";
	}
	
	public String eligibleEmployeeList()
	{
		String goalId = goalInitialization.getGoalId();
		String status=goalInitialization.getGoalPublishStatus();
		System.out.println("status---->"+status);
		System.out.println("goalId="+goalId);
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context,session);
		model.getEligibleEmployeeListData(goalInitialization,request);
		goalInitialization.setGoalId(goalId);
		model.terminate();
		return "listOfEligibleEmployee";
	}
	
	/**
	 * Get all division list.
	 * @return String
	 */
	public String f9MultiDiv() {
		try {
			
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (this.goalInitialization.getUserProfileDivision()!= null
					&& this.goalInitialization.getUserProfileDivision().length() > 0){
				query += " WHERE DIV_ID IN (" + this.goalInitialization.getUserProfileDivision()
						+ ") AND IS_ACTIVE= 'Y'" ;
			}else{
			query+= " WHERE IS_ACTIVE= 'Y' ";
			}
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_division";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger.error("Error in LeaveApplicationMisAction.f9MultiDiv() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	/**
	 * Get all department list.
	 * @return String.
	 */
	public String f9MultiDept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT " + " WHERE " + " IS_ACTIVE = 'Y' " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("dept");
			String textAreaId = "paraFrm_deptName";

			String hiddenFieldId = "paraFrm_deptCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiDept() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Get all rank list.
	 * @return String.
	 */
	public String f9MultiRank() {
		try {
			String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
					+ "	FROM " + " 	HRMS_RANK  " +" WHERE IS_ACTIVE='Y' "+ " ORDER BY "
					+ "		UPPER (RANK_NAME) ";

			String header = getMessage("designation");
			String textAreaId = "paraFrm_desgName";

			String hiddenFieldId = "paraFrm_desgCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Get all center list.
	 * @return String.
	 */
	public String f9MultiCenter() {
		try {
			String query = " SELECT DISTINCT CENTER_ID, CENTER_NAME "+
							" FROM  	HRMS_CENTER    WHERE IS_ACTIVE='Y'  ORDER BY " +
							" UPPER (CENTER_NAME)	";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_branch";

			String hiddenFieldId = "paraFrm_branchCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Get all grade list.
	 * @return String.
	 */
	public String f9MultiGrade() {
		try {
			String query = "SELECT DISTINCT CADRE_ID, CADRE_NAME "+
					" FROM  	HRMS_CADRE   WHERE CADRE_IS_ACTIVE='Y'  ORDER BY " +
			        " UPPER (CADRE_NAME) ";	

			String header = getMessage("grade");
			String textAreaId = "paraFrm_grade";

			String hiddenFieldId = "paraFrm_gradeCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiGrade() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Get all employee type list.
	 * @return String.
	 */
	public String f9MultiEmpType() {
		try {
			String query =  " SELECT DISTINCT TYPE_ID, TYPE_NAME "+
			" FROM  	HRMS_EMP_TYPE   WHERE IS_ACTIVE='Y'  ORDER BY  "+
			" UPPER (TYPE_NAME)	";	

			String header = getMessage("empType");
			String textAreaId = "paraFrm_empType";

			String hiddenFieldId = "paraFrm_empTypeCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiEmpType() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Get all employee list for selected division.
	 * @return String.
	 * @throws Exception
	 */
	public String f9Employee() throws Exception {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ";
				
		if (goalInitialization.getUserProfileDivision() != null
				&& goalInitialization.getUserProfileDivision().length() > 0)
			query += "AND EMP_DIV IN ("
					+ goalInitialization.getUserProfileDivision() + ")";
		query+=" ORDER BY EMP_ID";
		System.out.println("query Details"+query);
		String[] headers = { getMessage("emp.token"), getMessage("emp.name") };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "empToken", "empName", "empIdTxt" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
		
	
	
	/**
	 * Use add employee into list.
	 * @return String.
	 * @throws Exception
	 */
	public String addEligibleEmp() throws Exception {
		String[] srNo = request.getParameterValues("srNo");
		String[] divsion = request.getParameterValues("divCode");
		String[] department = request.getParameterValues("deptCode");
		String[] branch = request.getParameterValues("branchCode");
		String[] designation = request.getParameterValues("desgCode");
		String[] grade = request.getParameterValues("gradeCode");
		String[] employee = request.getParameterValues("empId");
		String[] employeeType = request.getParameterValues("empTypeCode");
		String[] doj = request.getParameterValues("");
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		System.out.println("DepatId"+goalInitialization.getDeptCode());
		model.addEligibleEmp(goalInitialization,request);
		model.terminate();
		goalInitialization.setCategory1("");
		goalInitialization.setSubcode("");
		goalInitialization.setHiddenEdit("");
		goalInitialization.setParaId("");
		goalInitialization.setCategoryWeightage("");
		goalInitialization.setEmpName("");
		//resetIds();
		getNavigationPanel(2);
		model.getDateRow(goalInitialization, request);
		return "eligibleEmployee";
	}
	
	/**
	 * Use for remove employee from list.
	 * @return String.
	 * @throws Exception
	 */
	public String resetEmployee() throws Exception {
		goalInitialization.setEmpBranch("");
		goalInitialization.setEmpIdTxt("");
		goalInitialization.setEmpDepartment("");
		goalInitialization.setEmpDesignation("");
		goalInitialization.setEmpGrade("");
		goalInitialization.setEmpName("");
		goalInitialization.setEmpToken("");
		goalInitialization.setDatOfJoining("");
		eligibleEmployee();
		return "eligibleEmployee";
	}
	
	//added by Tinshuk begins
	
	/**
	 * Use for save eligible employee for goal.
	 * @return String.
	 */
	public String addDetails(){
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		
		String result=model.addDetails(goalInitialization, request);
		
		if(result.equals("inserted")){
			addActionMessage("Record inserted successfully");
		}else{
			addActionMessage("Please add atleast one record in the list");
		}
		model.terminate();
		
		return eligibleEmployee();
	}
	
	/**
	 * Use for delete multiple employee from employee list.
	 * @return String.
	 */
	public String multipleEmpDelete() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		String empIdForDelete = goalInitialization.getParaId();
		logger.info("goalIdForDelete====" + empIdForDelete);
		if (empIdForDelete.lastIndexOf(",") == empIdForDelete.length() - 1) {
			empIdForDelete = empIdForDelete.substring(0, empIdForDelete
					.length() - 1);
		}
		logger.info("goalIdForDelete after====" + empIdForDelete);
		if (model.deleteEmployee(empIdForDelete)) {

			addActionMessage("Employee deleted successfully");
		} else {
			addActionMessage("Employee can not be deleted");
		}
		getNavigationPanel(1);
		model.data(goalInitialization, request);
		model.terminate();

		return eligibleEmployee();
	}
	
	
	
	public String multipleEmpAdd()
	{
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		boolean result=model.addMultipleEmployee(request,goalInitialization);
		if(result){
			addActionMessage("Record inserted successfully");
		}else{
			addActionMessage("Please add atleast one record in the list");
		}
		//getEmployees1();
		//goalInitialization.setParaId("");
		model.terminate();
		//return "addEmloyee";
		return eligibleEmployeeList();
	}
	
	
	
	public String multipleEmpDeleteListPage() {
		GoalInitializationModel model = new GoalInitializationModel();
		model.initiate(context, session);
		String empIdForDelete = goalInitialization.getParaId();
		logger.info("goalIdForDelete====" + empIdForDelete);
		if (empIdForDelete.lastIndexOf(",") == empIdForDelete.length() - 1) {
			empIdForDelete = empIdForDelete.substring(0, empIdForDelete
					.length() - 1);
		}
		logger.info("goalIdForDelete after====" + empIdForDelete);
		if (model.deleteEmployee(empIdForDelete)) {

			addActionMessage("Employee deleted successfully");
			
		} else {
			addActionMessage("Employee can not be deleted");
		}
		//getNavigationPanel(1);
		//model.data(goalInitialization, request);
		model.getEligibleEmployeeListData(goalInitialization,request);
		model.terminate();
		return "listOfEligibleEmployee";
	}
	
	
	
	/**
	 * Use for reset all values.
	 */
	public void resetIds(){
		
		goalInitialization.setDivCode("");
		goalInitialization.setBranchCode("");
		goalInitialization.setDeptCode("");
		goalInitialization.setDesgCode("");
		goalInitialization.setGradeCode("");
		goalInitialization.setEmpIdTxt("");
		goalInitialization.setEmpTypeCode("");
		
	}
	//added by Tinshuk ends

}
