package org.struts.action.recruitment;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Recruitment.ReqsnMisReport;
import org.paradyne.model.recruitment.ManpowerRequisitionAnalysisModel;
import org.paradyne.model.recruitment.ReqsnMisReportModel;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:07-04-2009
 *
 */
public class ReqsnMisReportAction extends ParaActionSupport  {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ReqsnMisReportAction.class);
	ReqsnMisReport bean;
	public void prepare_local() throws Exception {
		bean = new ReqsnMisReport();
		bean.setMenuCode(835);
		
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		ReqsnMisReportAction.logger = logger;
	}

	public ReqsnMisReport getBean() {
		return bean;
	}

	public void setBean(ReqsnMisReport bean) {
		this.bean = bean;
	}
	public void prepare_withLoginProfileDetails(){
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context, session);
		model.callSavedLIst(bean);
		model.terminate();
}
	public String input(){
		if(!(bean.getMraRepCode().equals("") || bean.getMraRepCode().equals("null"))){
		      getFilterDetails();
		  	ReqsnMisReportModel model=new ReqsnMisReportModel();
			model.initiate(context, session);
			model.callSavedLIst(bean);
			Object[][] orderObj=model.chkUser(bean);
			bean.setSearchSetting(String.valueOf(orderObj[0][1]));
			model.terminate();
		}
		return "success";	
	}
	
	
	public String getFilterDetails(){
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context,session);
		model.getSettingDetails(bean);
	
		model.terminate();
		return "success";
	}
	
	/**
	 * following function is called to generate the report 
	 * @return
	 */
	public String report(){
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context,session);
		String type=request.getParameter("rep");
		if(bean.getExportAllData().equals("Y")){
			model.getAllRequisitions(type, bean, request,response);
		}else{
		    model.getReport(type,bean,request,response);
		}    
		model.terminate();
		return null;
	}
	
	public String saveSettings(){
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context,session);
		if(bean.getMraRepCode().equals("") || bean.getMraRepCode().equals("null")){
			Object[][] obj=model.chkUser(bean);
			if(obj!=null && obj.length>0){
				addActionMessage("Setting name already exists.");
			}else{
				boolean result=model.saveFilters(bean);
				if(result){
					model.callSavedLIst(bean);
					Object[][] orderObj=model.chkUser(bean);
					bean.setSearchSetting(String.valueOf(orderObj[0][1]));
					getFilterDetails();
					addActionMessage("Record saved successfully.");
				}
			}
			
		}else{
			String str=(String)bean.getHashMap().get(bean.getSearchSetting());
		    if(str.equalsIgnoreCase(bean.getSettingName())){
		    	model.updateSettings(bean);
		    	model.callSavedLIst(bean);
		    	Object[][] orderObj=model.chkUser(bean);
				bean.setSearchSetting(String.valueOf(orderObj[0][1]));
		       	addActionMessage("Record updated successfully.");
		    	
		    }else{
		    	Object[][] obj=model.chkUser(bean);
				if(obj!=null && obj.length>0){
					Object[][] orderObj=model.chkUser(bean);
					bean.setSearchSetting(String.valueOf(orderObj[0][1]));
					addActionMessage("Setting name already exists.");
					
				}else{
				
				boolean result=model.saveFilters(bean);
			
				if(result){
					model.callSavedLIst(bean);
					Object[][] orderObj=model.chkUser(bean);
					bean.setSearchSetting(String.valueOf(orderObj[0][1]));
					addActionMessage("Record saved successfully.");
					
				   }
			
				}
				
		     }
		    getFilterDetails();
		}
		model.terminate();
		return "success";
	}
	
	/**
	 * following function is called to display the requisition details page wise when View on Screen
	 * radio button is clicked and Show report button is clicked.
	 * @return
	 */
	public String callJspView(){
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context,session);
		model.callJspView(bean,request,response);
		model.terminate();
		return "viewReport";
	}
	/**
	 * following function is called to display the requisition details when select requisition or edit requisition 
	 * button is clicked.
	 * @return
	 */
	public String displayReq(){ 
		ReqsnMisReportModel model=new ReqsnMisReportModel();
		model.initiate(context, session);
		model.displayReq(bean);
		model.terminate();
		return "viewReqAnalysis";
	}
	
	public String reset(){
		/**
		 * private String editReqFlag="false";
		 * private String noDataReq="false";
		 * private String radioFlag1="false";
		 * private String radioFlag2="false";
		 * private String radioFlag3="false";
	 */
		bean.setDivCode("");
		bean.setDivName("");
		bean.setBrnCode("");
		bean.setBrnName("");
		bean.setDeptCode("");
		bean.setDeptName("");
		bean.setPosCode("");
		bean.setPosName("");
		bean.setDateFilter("1");
		bean.setFrmDate("");
		bean.setToDate("");
		bean.setSearchSetting("");
		bean.setSettingName("");
		bean.setReportType("1");
		bean.setFirstSort("1");
		bean.setHidFirstAsc("A");
		bean.setHidSecAsc("A");
		bean.setHidThirdAsc("A");
		bean.setSecondSort("1");
		bean.setThirdSort("1");
		bean.setAdvVacOpt("1");
		bean.setAdvVacVal("");
		bean.setHiringMgr("");
		bean.setHiringMgrId("");
		bean.setReqsStatus("");
		bean.setSelectedReq("");
		bean.setSelectedReqName("");
		bean.setItReqCode("");
		bean.setBackFlag("");
		bean.setExportAllData("Y");
		bean.setItReqCode1("");
		bean.setItReqName("");
		bean.setItReqName1("");
		bean.setItReqDate("");
		bean.setItReqDate1("");
		bean.setViewOnScrn("false");
		bean.setReportFlag("false");
		bean.setDateFlag("false");
		bean.setScrnFlg("false");
		bean.setRadioFlag("false");
		bean.setFirstRadio("false");
		bean.setSecondRadio("false");
		bean.setThirdRadio("false");
		bean.setEditReqFlag("false");
		bean.setNoDataReq("false");
		bean.setRadioFlag1("false");
		bean.setRadioFlag2("false");
		bean.setRadioFlag3("false");
		return SUCCESS;
	}
	
	public String f9Div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName","divCode"};

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
	
	public String f9Brn() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("branch")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "brnName","brnCode"};

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
	
	public String f9Dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("department")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName","deptCode"};

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
	
	public String f9Pos() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK WHERE RANK_STATUS='A'";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("position")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "posName","posCode"};

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
	
	
	public String f9Hiring() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {"Employee Code",getMessage("hiringhead")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "35","25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken","hiringMgr","hiringMgrId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2};

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
