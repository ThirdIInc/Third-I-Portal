/**
 * 
 */
package org.struts.action.PAS;

import java.util.HashMap;

import org.paradyne.bean.PAS.AppraisalCalendar;
import org.paradyne.model.PAS.AppraisalCalendarModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author aa0554
 * modified @Date 30/07/2012
 *
 */
public class AppraisalCalendarAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalCalendarAction.class);
	AppraisalCalendar apprCalendar ;
	public void prepare_local() throws Exception {
		apprCalendar = new AppraisalCalendar();
		apprCalendar.setMenuCode(748);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return apprCalendar;
	}

	public AppraisalCalendar getApprCalendar() {
		return apprCalendar;
	}

	public void setApprCalendar(AppraisalCalendar apprCalendar) {
		this.apprCalendar = apprCalendar;
	}
	public String addNew(){
		if(apprCalendar.getAddNew().equals("false")){
			reset();
		}
		apprCalendar.setAppraisalId("");
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		
		String queryDiv = "SELECT DIV_ID,DIV_NAME FROM  HRMS_DIVISION ";
		
		if(apprCalendar.getUserProfileDivision() !=null && apprCalendar.getUserProfileDivision().length()>0)
			queryDiv+= " WHERE DIV_ID IN ("+apprCalendar.getUserProfileDivision() +")"; 
		queryDiv+= " ORDER BY  DIV_ID ";
		
		Object[][] iteratorDiv = model.getSqlModel().getSingleResult(queryDiv);
		HashMap mpDiv = new HashMap();
		for (int i = 0; i < iteratorDiv.length; i++) {
			mpDiv.put(String.valueOf(iteratorDiv[i][0]), String
					.valueOf(iteratorDiv[i][1]));

		}
		mpDiv = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDiv, null, true);
		apprCalendar.setHashmapDiv(mpDiv);
		
		String querType = "SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";
		
		Object[][] iteratorType = model.getSqlModel().getSingleResult(querType);
		HashMap mpType = new HashMap();
		for (int i = 0; i < iteratorType.length; i++) {
			mpType.put(String.valueOf(iteratorType[i][0]), String
					.valueOf(iteratorType[i][1]));

		}
		mpType = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpType, null, true);
		apprCalendar.setHashmapType(mpType);
		
		String queryGrade = "SELECT CADRE_ID,CADRE_NAME FROM  HRMS_CADRE ORDER BY CADRE_ID ";
		
		Object[][] iteratorGrade = model.getSqlModel().getSingleResult(queryGrade);
		HashMap mpGrade = new HashMap();
		for (int i = 0; i < iteratorGrade.length; i++) {
			mpGrade.put(String.valueOf(iteratorGrade[i][0]), String
					.valueOf(iteratorGrade[i][1]));

		}
		mpGrade = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpGrade, null, true);
		apprCalendar.setHashmapGrade(mpGrade);
		
		String queryDept = "SELECT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT ORDER BY DEPT_ID ";
		
		Object[][] iteratorDept = model.getSqlModel().getSingleResult(queryDept);
		HashMap mpDept = new HashMap();
		for (int i = 0; i < iteratorDept.length; i++) {
			mpDept.put(String.valueOf(iteratorDept[i][0]), String
					.valueOf(iteratorDept[i][1]));

		}
		mpDept = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDept, null, true);
		apprCalendar.setHashmapDept(mpDept);
		getNavigationPanel(2);
		apprCalendar.setEdit("true");
		return "editAppraisalCalendar";
	}
	public String edit(){
		getNavigationPanel(2);
		apprCalendar.setEdit("true");
		//setCommonDetails();
		return "editAppraisalCalendar";
	}
	
	@Override
	public String input() throws Exception {
		String menucode =request.getParameter("menuCode");
		if(menucode!=null && !menucode.equals("")){
			apprCalendar.setMenuCode(Integer.parseInt(menucode));
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		model.setCalendarList(apprCalendar,request);
		model.terminate();
	}
	/**
	 * following function is called when the paging is clicked .
	 * @return result
	 */
	public String callPage() throws Exception {
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		model.setCalendarList(apprCalendar,request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	/**
	 * following function is called when the save is clicked .
	 * @return result
	 */
	public String save(){
		boolean result=false;
		String returnType ="";
		/////apprCalendar.setHideJoinDateCondition(apprCalendar.getJoinDateCondition());
		///apprCalendar.setJoinDateCheck(apprCalendar.getHideJoinDateCheck());
		///apprCalendar.setEmpTypeCheck(apprCalendar.getHideEmpTypeCheck());
		///apprCalendar.setEmpGradeCheck(apprCalendar.getHideEmpGradeCheck());
		///apprCalendar.setEmpDeptCheck(apprCalendar.getHideEmpDeptCheck());
		////apprCalendar.setEmpDivCheck(apprCalendar.getHideEmpDivCheck());		
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		logger.info("AppraisalId======"+apprCalendar.getAppraisalId());
		if(apprCalendar.getAppraisalId().equals("")){
			result=model.save(apprCalendar,request);
			if(result){
				addActionMessage(getMessage("save"));
				///setCommonDetails();
				apprCalendar.setEnableAll("N");
				returnType ="editAppraisalCalendar";
			}
			else{
				addActionMessage("Duplicate record found.");
				returnType ="editAppraisalCalendar";
				addNew();
			}
		}else{
			result=model.update(apprCalendar,request);
			if(result){
				addActionMessage(getMessage("update"));
				///setCommonDetails();
				returnType ="editAppraisalCalendar";
				apprCalendar.setEnableAll("N");
			}else{
				addActionMessage(getMessage("update.error"));
				returnType ="editAppraisalCalendar";
			}
		}
		//model.saveEligibleEmployee(apprCalendar,request);
		//apprCalendar.setEdit("false");
		///apprCalendar.setView("true");
		///apprCalendar.setOnloadMode("false");
		model.terminate();
		getNavigationPanel(3);
		return returnType;
	}
	/**
	 * following function is called when the f9Search is clicked .
	 * @return result
	 */
	public String f9Search(){
			String query="SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),"
							+" TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,APPR_CAL_REPEAT_FREQ,APPR_CAL_AUTO_FLAG, "
							+" APPR_CAL_CRT_ETYP_FLAG,APPR_CAL_CRT_DOJ_FLAG,APPR_CAL_CRT_GRD_FLAG,APPR_CAL_CRT_DEP_FLAG,APPR_CAL_IS_STARTED,APPR_CAL_CRT_DIV_FLAG FROM PAS_APPR_CALENDAR ORDER BY APPR_ID DESC" ;
			String[] headers={getMessage("appraisal.code"), getMessage("valid.till"), getMessage("start.date"), getMessage("end.date")};
			String[] headerWidth={"40", "20", "20", "20"};
			String[] fieldNames={"appraisalCode","validTill","startDate","endDate","appraisalId","repeatFreq","hideAutoStart","hideEmpTypeCheck",
										"hideJoinDateCheck","hideEmpGradeCheck","hideEmpDeptCheck","isStarted","hideEmpDivCheck"};
			int[] columnIndex={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12};
			String submitFlag = "true";
			String submitToMethod="AppraisalCalendar_setCalendarDetails.action";
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			return "f9page";
	}
	/**
	 * following function is to callForEdit.
	 * @return result
	 */
	public String callForEdit(){
		///apprCalendar.setAppraisalId(apprCalendar.getParaId());
		AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		String requestID = request.getParameter("apprId");
		model.setCalendarHeaderDetails(apprCalendar,requestID);
		apprCalendar.setEdit("true");
		setCommonDetails(requestID);
		getNavigationPanel(2);
		model.terminate();
		return "editAppraisalCalendar";
	}
	/**
	 * following function is to mapGoalCompt.
	 * @return result
	 */
	public String mapGoalCompt()
	{
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		String requestID = request.getParameter("apprId");
		model.retriveMapGoalCompt(apprCalendar,request,requestID);
		model.terminate();
		return "mapGoalComp";
	}
	/**
	 * following function is to addItem.
	 * @return result
	 */
	public String addItem() throws Exception {
		String[] srNo = request.getParameterValues("srNo");
		String[] goalName = request.getParameterValues("ittrGoalName");
		String[] goalFromDate = request.getParameterValues("ittrGoalFromDate");
		String[] goalToDate = request.getParameterValues("ittrGoalToDate");
		String[] goalWeightage = request.getParameterValues("ittrGoalWeightage");
		String[] goalId = request.getParameterValues("ittrGoalId");
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		//if (goalInitialization.getHiddenEdit().equals("")) {
			model.addItem(apprCalendar, srNo, goalName,goalFromDate,goalToDate,goalWeightage,goalId, 1);
		//}
		model.terminate();
		apprCalendar.setGoalName("");
		apprCalendar.setGoalId("");
		apprCalendar.setGoalWeightage("");
		//apprCalendar.setHiddenEdit("");
		apprCalendar.setParaId("");
		//goalInitialization.setCategoryWeightage("");
		//getNavigationPanel(2);
		//model.getDateRow(goalInitialization, request);
		return "mapGoalComp";
	}
	/**
	 * following function is to remove GoalsWeightage.
	 * @return result
	 */
	public String removeGoalsWeightage() throws Exception {
		String[] srNo = request.getParameterValues("srNo");
		String[] goalName = request.getParameterValues("ittrGoalName");
		String[] goalFromDate = request.getParameterValues("ittrGoalFromDate");
		String[] goalToDate = request.getParameterValues("ittrGoalToDate");
		String[] goalWeightage = request.getParameterValues("ittrGoalWeightage");
		String[] goalId = request.getParameterValues("ittrGoalId");
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		//if (goalInitialization.getHiddenEdit().equals("")) {
		model.removeGoals(apprCalendar, goalName, srNo,goalFromDate,goalToDate,goalWeightage,goalId ,request);
		//}
		model.terminate();
		apprCalendar.setGoalName("");
		apprCalendar.setGoalId("");
		apprCalendar.setGoalWeightage("");
		//apprCalendar.setHiddenEdit("");
		apprCalendar.setParaId("");
		//goalInitialization.setCategoryWeightage("");
		//getNavigationPanel(2);
		//model.getDateRow(goalInitialization, request);
		return "mapGoalComp";
	}

	/**
	 * following function is to Reset.
	 * @return result
	 */
	public String reset(){
		apprCalendar.setAddNew("false");
		apprCalendar.setAppraisalCode("");
		apprCalendar.setAppraisalCodeAppraisers("");
		apprCalendar.setAppraisalCodeMapping("");
		apprCalendar.setAppraisalCodeParameters("");
		apprCalendar.setAppraisalCodePhase("");
		apprCalendar.setAppraisalCodeRating("");
		apprCalendar.setAppraisalCodeTemplate("");
		apprCalendar.setAppraisalId("");
		apprCalendar.setAppraisalIdAppraisers("");
		apprCalendar.setAppraisalIdMapping("");
		apprCalendar.setAutoStart("");
		apprCalendar.setAvailDept("");
		apprCalendar.setAvailDiv("");
		apprCalendar.setAvailGrade("");
		apprCalendar.setAvailType("");
		apprCalendar.setComments("");
		apprCalendar.setEdit("false");
		apprCalendar.setEmpDeptCheck("");
		apprCalendar.setEmpDivCheck("");
		apprCalendar.setEmpGradeCheck("");
		apprCalendar.setEndDate("");
		apprCalendar.setHideAutoStart("");
		apprCalendar.setHideJoinDateCondition("");
		apprCalendar.setHideJoinDateCheck("");
		apprCalendar.setHideImportContentConfig("");
		apprCalendar.setHideImportConfig("");
		apprCalendar.setHideEmpTypeCheck("");
		apprCalendar.setHideEmpDivCheck("");
		apprCalendar.setHideEmpGradeCheck("");
		apprCalendar.setHideEmpDeptCheck("");
		apprCalendar.setImportAppraisalCode("");
		apprCalendar.setImportAppraisalID("");
		apprCalendar.setImportConfig("");
		apprCalendar.setImportContentConfig("");
		apprCalendar.setJoinDate("");
		apprCalendar.setJoinDateCheck("");
		apprCalendar.setJoinDateCondition("");
		apprCalendar.setJoinFromDate("");
		apprCalendar.setJoinToDate("");
		apprCalendar.setParaId("");
		apprCalendar.setRepeatFreq("");
		apprCalendar.setStartDate("");
		apprCalendar.setValidTill("");
		apprCalendar.setIsStarted("");
		apprCalendar.setView("false");
		
		return "input";
	}
	/**
	 * following function is to cancel.
	 * @return result
	 */
	public String cancel(){
		String returnPage ="";
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		int modeId=1;
		logger.info("getedit in cancel=="+apprCalendar.getEdit());
		/*if(apprCalendar.getEdit().equals("true")){
			model.setCalendarHeaderDetails(apprCalendar);
			setCommonDetails();
			
			apprCalendar.setOnloadMode("false");
			apprCalendar.setEdit("false");
			modeId =3;
			returnPage ="input";
		}
		else*/ {
			model.setCalendarList(apprCalendar,request);
			reset();
			apprCalendar.setOnloadMode("true");
			apprCalendar.setEdit("false");
			returnPage ="input";
		}
		getNavigationPanel(modeId);
		model.terminate();
		return returnPage ;
	}
	/**
	 * following function is to setCalendarDetails.
	 * @return result
	 */
	public String setCalendarDetails(){
		//apprCalendar.setAppraisalId(apprCalendar.getParaId());
		AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		String requestID = apprCalendar.getAppraisalId();
		model.setCalendarHeaderDetails(apprCalendar,requestID);
		apprCalendar.setEdit("true");
		//setCommonDetails(requestID);
		getNavigationPanel(2);
		model.terminate();
		return "editAppraisalCalendar";
		/*getNavigationPanel(3);
		apprCalendar.setView("true");
		apprCalendar.setOnloadMode("false");
		setCommonDetails();
		return SUCCESS;*/
	}
	/**
	 * following function is to setCommonDetails.
	 * @return result
	 */
	public void setCommonDetails(String requestID){
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		apprCalendar.setJoinDateCheck(apprCalendar.getHideJoinDateCheck());
		apprCalendar.setEmpTypeCheck(apprCalendar.getHideEmpTypeCheck());
		apprCalendar.setEmpGradeCheck(apprCalendar.getHideEmpGradeCheck());
		apprCalendar.setEmpDeptCheck(apprCalendar.getHideEmpDeptCheck());
		apprCalendar.setEmpDivCheck(apprCalendar.getHideEmpDivCheck());
		model.setEmpTypeList(apprCalendar,requestID);
		model.setEmpDivList(apprCalendar,requestID);
		model.setEmpDeptList(apprCalendar,requestID);
		model.setEmpGradeList(apprCalendar,requestID);
		model.setJoinDateDetails(apprCalendar,requestID);
		model.terminate();
	}
	/**
	 * following function is called when the saveMapGoalComp is clicked .
	 * @return result
	 */
	public String saveMapGoalComp()
	{
		try {
			AppraisalCalendarModel model = new AppraisalCalendarModel();
			model.initiate(context, session);
			boolean result=model.saveMapGoalComp(apprCalendar,request);
			if(result)
			{
				addActionMessage(getMessage("save"));
				String requestID = apprCalendar.getAppraisalId();
				model.retriveMapGoalCompt(apprCalendar,request,requestID);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mapGoalComp";
	}
	/**
	 * following function is called when the f9action Goal is clicked .
	 * @return result
	 */
	public String f9actionGoal(){
		String query="SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY') , TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),GOAL_ID FROM HRMS_GOAL_CONFIG ORDER BY GOAL_ID" ;
		String[] headers={"Goal Name" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"goalName","hidengoalStartDate","hidengoalEndDate","goalId"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action Comp is clicked .
	 * @return result
	 */
	public String f9actionComp(){
		String query="SELECT COMP_NAME,COMP_ID FROM HRMS_COMP_CONFIG ORDER BY COMP_ID" ;
		String[] headers={"Competency Name" };
		String[] headerWidth={"100"};
		String[] fieldNames={"compName","compId"};
		int[] columnIndex={0,1};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportApprCode is clicked .
	 * @return result
	 */
	public String f9actionImportApprCode(){
			String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
			String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
			String[] headerWidth={"50","25","25"};
			String[] fieldNames={"importAppraisalCode","importStartDate","importEndDate","importAppraisalID"};
			int[] columnIndex={0,1,2,3};
			String submitFlag = "false";
			String submitToMethod="";
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			return "f9page";
	}
	/**
	 * following function is called when the f9action ImportPhases is clicked .
	 * @return result
	 */
	public String f9actionImportPhases(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodePhase","importStartDate","importEndDate","appraisalIdPhase"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportRating is clicked .
	 * @return result
	 */
	public String f9actionImportRating(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodeRating","importStartDate","importEndDate","appraisalIdRating"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportParameters is clicked .
	 * @return result
	 */
	public String f9actionImportParameters(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodeParameters","importStartDate","importEndDate","appraisalIdParameters"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportAppraisers is clicked .
	 * @return result
	 */
	public String f9actionImportAppraisers(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodeAppraisers","importStartDate","importEndDate","appraisalIdAppraisers"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportTemplate is clicked .
	 * @return result
	 */
	public String f9actionImportTemplate(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodeTemplate","importStartDate","importEndDate","appraisalIdTemplate"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action ImportMapping is clicked .
	 * @return result
	 */
	public String f9actionImportMapping(){
		String query="SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID" ;
		String[] headers={"Appraisal Code" ,"Start Date", "End Date" };
		String[] headerWidth={"50","25","25"};
		String[] fieldNames={"appraisalCodeMapping","importStartDate","importEndDate","appraisalIdMapping"};
		int[] columnIndex={0,1,2,3};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the f9action EmployeeAdd is clicked .
	 * @return result
	 */
	public String f9actionEmployeeAdd(){
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		String empQuery="SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
			+" WHERE APPR_EMP_STATUS='A' AND APPR_ID="+apprCalendar.getAppraisalId() 
			+" AND APPR_EMP_ID NOT IN(SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID = "+apprCalendar.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='R') "
			+ " UNION"
			+" SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP_TEMP "
			+" WHERE APPR_EMP_TEMP_STATUS='A' AND APPR_ID="+apprCalendar.getAppraisalId() 
			+" AND APPR_EMP_ID NOT IN(SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID = "+apprCalendar.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='R') ";
		Object empObject[][] =model.getSqlModel().getSingleResult(empQuery);
		String empCode = "";
		try {
			if (empObject != null) {
				for (int i = 0; i < empObject.length - 1; i++)
					empCode += empObject[i][0] + ",";
				empCode += empObject[empObject.length - 1][0];
			} else
				empCode = "0";
		} catch (Exception e) {
			empCode = "0";
		}
			String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
					+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TYPE_NAME, CADRE_NAME,DEPT_NAME FROM HRMS_EMP_OFFC " 
					+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
					+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+" WHERE EMP_STATUS='S' AND EMP_ID NOT IN("+empCode+")  ORDER BY EMP_ID";
			String[] headers = { "Employee ID", getMessage("employee") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empIdAdd", "empNameAdd", "empCodeAdd","empJoinDateAdd","empTypeAdd","empGradeAdd","empDeptAdd" };
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			model.terminate();
			return "f9page";
	}
	/**
	 * following function is called when the f9action EmployeeSearch is clicked .
	 * @return result
	 */
	public String f9actionEmployeeSearch(){
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_STATUS='S' ORDER BY EMP_ID";
		String[] headers = { "Employee ID", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empIdSearch", "empNameSearch", "empCodeSearch" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called when the delete button is clicked .
	 * @return result
	 */
	public String delete(){
		boolean result= false;
		AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		result = model.delete(apprCalendar);
		if(result){
			addActionMessage(getMessage("delete"));
			getNavigationPanel(1);
			reset();
			model.setCalendarList(apprCalendar,request);
		}else{
			addActionMessage(getMessage("del.error"));
			callBackToCalendar();
		}
		model.terminate();
		return SUCCESS;
	}
	/**
	 * following function is called when the view EligibleEmp button is clicked .
	 * @return result
	 */
	public String viewEligibleEmp(){
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		model.viewEligibleEmp(apprCalendar,request);
		model.terminate();
		return "eligibleEmp";
	}
	/**
	 * following function is called when the view EligibleEmp1 button is clicked .
	 * @return result
	 */
	public String viewEligibleEmp1(){
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		model.viewEligibleEmp1(apprCalendar,request);
		model.terminate();
		return "eligibleEmp";
	}
	/**
	 * following function is called when the add EligibleEmp button is clicked .
	 * @return result
	 */
	public String addEligibleEmp(){
		boolean result = false;
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		result = model.addEligibleEmp(apprCalendar,request);
		model.terminate();
		apprCalendar.setSaveEligibleFlag("false");
		apprCalendar.setEmpCodeAdd("");
		apprCalendar.setEmpNameAdd("");
		apprCalendar.setEmpIdAdd("");
		apprCalendar.setEmpDeptAdd("");
		apprCalendar.setEmpGradeAdd("");
		apprCalendar.setEmpJoinDateAdd("");
		apprCalendar.setEmpTypeAdd("");
		
		return "eligibleEmp";
	}
	/**
	 * following function is called when the remove MultipleEmployee button is clicked .
	 * @return result
	 */
	public String removeMultipleEmployee(){
		boolean result = false;
		
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		result = model.removeEligibleEmp(apprCalendar,request);
		apprCalendar.setSaveEligibleFlag("false");
		viewEligibleEmp();
		model.terminate();
		return "eligibleEmp";
	}
	/**
	 * following function is called when the delete MultipleCalendar button is clicked .
	 * @return result
	 */
	public String deleteMultipleCalendar(){
		boolean result = false;
		
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		result = model.deleteMultipleCalendar(apprCalendar,request);
		if(result){
		addActionMessage(getMessage("delete"));
		}else{
			addActionMessage(getMessage("multiple.del.error"));
		}
		getNavigationPanel(1);
		model.setCalendarList(apprCalendar,request);
		model.terminate();
		return SUCCESS;
	}
	/**
	 * following function is called when the save UpdatedEmployee button is clicked .
	 * @return result
	 */
	public String saveUpdatedEmployee(){
		boolean result = false;
		AppraisalCalendarModel model = new AppraisalCalendarModel();
		model.initiate(context, session);
		result = model.saveUpdatedEligibleEmployee(apprCalendar);	
		viewEligibleEmp();
		logger.info("result in action"+result);
		if(result){
			apprCalendar.setSaveEligibleFlag("true");
			addActionMessage("Employee List updated successfully");
		}else{
			apprCalendar.setSaveEligibleFlag("false");
			addActionMessage(getMessage("update.error"));
		}
		model.terminate();
		return "eligibleEmp";
	}
	/**
	 * following function is called when the callBackToCalendar button is clicked .
	 * @return result
	 */
	public String callBackToCalendar(){
		AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		String requestID = request.getParameter("apprId");
		model.setCalendarHeaderDetails(apprCalendar,requestID);
		apprCalendar.setOnloadMode("false");
		setCalendarDetails();
		model.getSqlModel().singleExecute("DELETE FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID ="+apprCalendar.getAppraisalId());
		getNavigationPanel(3);
		
		model.getEligibilityCriteria(apprCalendar, requestID, request);
		setCommonDetails(requestID);
		
		model.terminate();
		return "eligibilityCriteriaSave";
	}
	/**
	 * following function is called when the importAppraisal button is clicked .
	 * @return
	 */
	public String importAppraisal ()  {
		try {
				String requestID = request.getParameter("calCode");
				final AppraisalCalendarModel model= new AppraisalCalendarModel();
				model.initiate(context, session);
				model.getImportAppraisal(apprCalendar, requestID, request);
				///setCommonDetails(requestID);
				
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "importAppraisal";
	}
	/**
	 * following function is called when the save ImportApprDetails button is clicked .
	 * @return result
	 */	
	public String saveImportApprDetails(){
		boolean result=false;
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		result=model.saveImportApprDetails(apprCalendar,request);
		addActionMessage("Import Details save successfully");
		model.getImportAppraisal(apprCalendar, apprCalendar.getAppraisalId(), request);
		String requestID = request.getParameter("appraisalId");
		model.setCalendarHeaderDetails(apprCalendar,requestID);
		apprCalendar.setEdit("true");
		setCommonDetails(requestID);
		model.terminate();
		getNavigationPanel(2);
		return "editAppraisalCalendar";
	}
	
	/**
	 * following function is called when the eligibilityCriteria button is clicked .
	 * @return
	 */
	public String eligibilityCriteria(){
		try {
				String requestID = request.getParameter("calCode");
				final AppraisalCalendarModel model= new AppraisalCalendarModel();
				model.initiate(context, session);
				model.getEligibilityCriteria(apprCalendar, requestID, request);
				setCommonDetails(requestID);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "eligibilityCriteria";
	}
	/**
	 * following function is called when the save EligibilityCriteria button is clicked .
	 * @return result
	 */
	public String saveEligibilityCriteria()
	{
		boolean result=false;
		AppraisalCalendarModel model=new AppraisalCalendarModel();
		model.initiate(context, session);
		String requestID = apprCalendar.getAppraisalId();
		result=model.saveCriteria(apprCalendar,request);
		if(result){
			addActionMessage("Apprisal Eligibility Criteria save successfully");
		}else
		{
			addActionMessage("Error in Apprisal Eligibility Criteria");
		}
		apprCalendar.setEdit("false");
		apprCalendar.setView("true");
		apprCalendar.setOnloadMode("false");
		model.setCalendarHeaderDetails(apprCalendar,requestID);
		apprCalendar.setEdit("true");
		setCommonDetails(requestID);
		model.terminate();
		getNavigationPanel(2);
		return "editAppraisalCalendar";
	}
	
	
	/**
	 * following function is called when the goalCompetencyMap button is clicked .
	 * @return
	 */
	public String goalCompetencyMap ()  {
		try {
				String requestID = request.getParameter("calCode");
				final AppraisalCalendarModel model= new AppraisalCalendarModel();
				model.initiate(context, session);
				model.getGoalCompetencyMap(apprCalendar, requestID, request);
				model.retriveMapGoalCompt(apprCalendar,request,requestID);
				////setCommonDetails(requestID);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "goalCompetencyMap";
	}
	
	/**
	 * @return
	 */
	public String callForPhaseConfig()throws Exception{
		System.out.println("Inside edit()....");
		String requestID = request.getParameter("appraisalIdList");
		final AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		model.getPhaseConfigDetails(apprCalendar,request,requestID);//get phase details in view mode
		model.terminate();	
		
		reset();
		apprCalendar.setNewFlag("true");
		apprCalendar.setEditMode("true");
		apprCalendar.setMode("update");
		apprCalendar.setRemovePhases("");
		getNavigationPanel(2);
		return "phaseConfig";
	}
	
/*public String savePhaseConfig()throws Exception{
		
	final AppraisalCalendarModel model= new AppraisalCalendarModel();
		boolean insert = false;
		boolean update = false;
		
		String phaseId[]=request.getParameterValues("phaseId");
		if(phaseId!=null && phaseId.length>0){
			if(){
			}
		}
		
		model.initiate(context, session);
		
		if(!apprCalendar.getMode().equals("update")){
			System.out.println("ganesh-------------IF-------");
			insert = model.savePhaseConfig(apprCalendar,request);
		}else{
			System.out.println("ganesh-------------ELSE-------");
			update = model.updatePhaseConfig(apprCalendar,request);
		}
		
		String requestID = request.getParameter("appraisalIdList");
		model.getPhaseConfigDetails(apprCalendar,request,requestID);//get phase details in view mode
		model.terminate();
		
		//reset();
		apprCalendar.setMode("");
		apprCalendar.setViewMode("true");
		apprCalendar.setEditMode("false");
		
		if(insert){
			//addActionMessage("Record saved successfully.");		
			addActionMessage(getMessage("save"));
		}else if(update){
			addActionMessage(getMessage("update"));
			//addActionMessage("Record updated successfully.");				
		}else{
			addActionMessage(getMessage("save.error"));
			//addActionMessage("Cannot save record.");		
		}
		
		getNavigationPanel(3); 
				
		
		return "phaseConfig";
		
	}*/

	/*public String callForAppraisalSchedule() throws Exception {
		String requestID = request.getParameter("appraisalIdList");
		final AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		System.out.println("action calforedit");
		model.callForAppraisalSchedule(apprCalendar,request,requestID);
		model.terminate();
		getNavigationPanel(2);
		apprCalendar.setMode("update");
		return "apprSchedule";
	}*/
	
	/*public String callForRatingScaleDef() throws Exception {
		
		String requestID = request.getParameter("appraisalIdList");
		
		String sAppId = "";
		
		if((null != apprCalendar.getHiddencode()) && !((apprCalendar.getHiddencode().equals(""))))
		{
			sAppId = apprCalendar.getHiddencode();
		}
		apprCalendar.setSMode("update");
		final AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		System.out.println("action calforedit");
		model.callForRatingScaleDef(apprCalendar,request,requestID);
		model.terminate();
		getNavigationPanel(2);
		return "ratingDefn";
	}*/
	
	
	
	public String callForAppraiserConfig() throws Exception {
		
		String requestID = request.getParameter("appraisalIdList");
		
		String sAppId = "";
		
		if((null != apprCalendar.getHiddencode()) && !((apprCalendar.getHiddencode().equals(""))))
		{
			sAppId = apprCalendar.getHiddencode();
		}
		apprCalendar.setSMode("update");
		final AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		System.out.println("action calforedit");
		model.callForRatingScaleDef(apprCalendar,request,requestID);
		model.terminate();
		getNavigationPanel(2);
		return "apprConfigView";
	}
	
public String callForApprTemplateDefination() throws Exception {
		
		String requestID = request.getParameter("appraisalIdList");
		
		String sAppId = "";
		
		if((null != apprCalendar.getHiddencode()) && !((apprCalendar.getHiddencode().equals(""))))
		{
			sAppId = apprCalendar.getHiddencode();
		}
		apprCalendar.setSMode("update");
		final AppraisalCalendarModel model= new AppraisalCalendarModel();
		model.initiate(context, session);
		System.out.println("action calforedit");
		model.callForRatingScaleDef(apprCalendar,request,requestID);
		model.terminate();
		getNavigationPanel(2);
		return "apprFormDesign";
	}
}

