/**
 * 
 */
package org.struts.action.TravelManagement.Master;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelAuthorities;
import org.paradyne.model.TravelManagement.Master.TravelAuthoritiesModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 *
 */
public class TravelAuthoritiesAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	TravelAuthorities travel;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		 travel=new TravelAuthorities();
		 travel.setMenuCode(790);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return travel;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		TravelAuthoritiesAction.logger = logger;
	}

	public TravelAuthorities getTravel() {
		return travel;
	}

	public void setTravel(TravelAuthorities travel) {
		this.travel = travel;
	}
	
	/** called on load to set the list* */
	public void prepare_withLoginProfileDetails() throws Exception {
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session); 
		//model.reqData(travel, request);
		model.terminate();
	}
	
	public String input() throws Exception{
		//Default Method with default modeCode(1)		
		getNavigationPanel(1);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);	
		model.getMsg(travel);
		travel.setOnLoadFlag(true);
		callPage();
		model.terminate();
		return "success";			
		
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		callPage();
		reset();
		travel.setLoadFlag(true);
		travel.setFlag(true);	
		travel.setImgflag(true);
		travel.setTravelAuth("");
		model.terminate();
		return "success";
		
	}
	
	/**
	 * following function is called when add new record is clicked in the jsp page
	 * @return
	 */
	public String save() throws Exception{
		//Default Method with Edit modeCode(3)
		getNavigationPanel(3);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		String[] schEmpId = request.getParameterValues("itEmpId");
		String[] aprEmpId = request.getParameterValues("itAprId");
		String[] accEmpId = request.getParameterValues("itAccId");
		if(schEmpId != null){
			for (int i = 0; i < schEmpId.length; i++) {
				System.out.println("schEmpId[i]====xxx====action==== "+schEmpId[i]);
			}
		}
		
	
		if(travel.getTravelAuth().equals("")){
			logger.info("***********==Inside Save=========");
			boolean result=model.addTravelAuth(travel,schEmpId,aprEmpId,accEmpId);
			if(result){
			addActionMessage(getText("Record saved Successfully!"));
			reset();
			model.getTravelAuth(travel);
			model.getAllList(travel, request);
			callPage();
			
			}else{
			addActionMessage(getMessage("save"));
			}
		}
		else
		{
			logger.info("***********==Inside Update=========");
			boolean result=model.update(travel,schEmpId,aprEmpId,accEmpId);
			logger.info("==========update result========="+result);
			if(result){
				addActionMessage(getMessage("update"));
				reset();
				model.getTravelAuth(travel);
				model.getAllList(travel, request);
				callPage();
			}else{
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				travel.setLoadFlag(true);
				travel.setFlag(true);
			}
		}
		model.getMsg(travel);
		model.reqData(travel,request);			
	
		travel.setLoadFlag(false);
		travel.setAddFlag(true);
		travel.setSaveFlag(true);		
		model.getMsg(travel);
		
		model.terminate();
		
		return "success";
	}
	
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		logger.info("inside Edit=======");
		getNavigationPanel(4);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		model.getAllList(travel, request);
		model.getTravelAuthEdit(travel);
		
		callPage();
		model.getMsg(travel);
		travel.setEditFlag(true);
		travel.setModFlag(true);
		travel.setAddFlag(false);
		travel.setOnLoadFlag(false);
		travel.setImgflag(true);
		model.terminate();
		return "success";
		
		
	}
	
	public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
			model.initiate(context, session);
			
			callPage();
			model.getTravelAuth(travel);
			model.getMsg(travel);
			travel.setSaveFlag(true);
			travel.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
		public String cancelSec() throws Exception{
			getNavigationPanel(1);
			TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
			model.initiate(context, session);
			callPage();
			travel.setSaveFlag(true);
			travel.setOnLoadFlag(true);
			model.getMsg(travel);
			reset();
			
			model.terminate();
			return "success";
			
		}
		
		public String cancelFirst() throws Exception{
			getNavigationPanel(1);
			callPage();
			travel.setOnLoadFlag(true);
			reset();
			
			return "success";
		}
		
		public String cancelFrth() throws Exception{
			logger.info("Inside Cancel Fourth");
			getNavigationPanel(1);
			TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
			model.initiate(context, session);
			callPage();
			reset();
			model.getMsg(travel);
			travel.setOnLoadFlag(true);
			model.terminate();
			return "success";
			
		}
		
	
		/**
		 * following function is called when delete button is clicked in the jsp page
		 * @return
		 */
		public String delete() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
			model.initiate(context, session);
					
			boolean result=model.deleteTravelAuth(travel);
			
			logger.info("result in Delete---->"+result);
			
			if(result){
				addActionMessage(getMessage("delete"));
				reset();
				callPage();
			}else{
				addActionMessage(getMessage("del.error"));
				callPage();
			}
			travel.setOnLoadFlag(true);
			model.getMsg(travel);
			model.terminate();
			
			return "success";
		}
		
	
	
	public String callPage1() throws Exception {
		getNavigationPanel(1);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		travel.setPageFlag(true);
		//bean.setOnLoadFlag("true");
		model.reqData(travel,request);
		model.terminate();
		
		
		return "success";
	}
	public String callPage2() throws Exception {
		getNavigationPanel(1);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		model.reqData(travel,request);
		travel.setPageFlag(true);
		model.terminate();
		
		
		return "success";
	}
	
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		model.reqData(travel,request);
		model.terminate();
		
		
		return "success";
	}
	
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(2);			
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		
		
		
		model.getTravelAuthOnDoubleClick(travel);
	
		model.getTravelSch(travel);
		model.getTravelApr(travel);
		model.getTravelAcc(travel);	
		//model.getAllList(travel, request);
		
		
		model.reqData(travel,request);
		model.getMsg(travel);
		travel.setDblFlag(true);
		travel.setModFlag(false);
		travel.setImgflag(true);
		//bean.setFlag("true");
		model.terminate();
		
		return "success";
		
	
	}
	
	public String delete1() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		boolean result;
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		callPage2();
		String[] code=request.getParameterValues("hdeleteCode");
		result=model.delChkdRec(travel,code);
		if(result){
			prepare_withLoginProfileDetails();
			model.reqData(travel,request);
			reset();
			addActionMessage(getMessage("delete"));
		}
		else
			addActionMessage(getMessage("multiple.del.error"));
		model.getMsg(travel);
		return "success";
	}
	
	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		
		
		String query = "SELECT HRMS_CENTER.CENTER_NAME,"
			+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
			+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
			+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
			+ " CASE WHEN MANG_AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,MANG_AUTH_ID,"
			+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
			+ " FROM HRMS_TMS_MANG_AUTH"
			+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
			+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
			+ " WHERE EMP_STATUS ='S'  ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("travel.branch"),getMessage("travel.schNm"),getMessage("travel.aprNm"),getMessage("travel.accNm"),getMessage("travel.sts")};

		String[] headerWidth = {  "20", "20","20","20","15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchName","schName","approverName","accName","status","travelAuth","branchId","hidSchCode","hidApprover","hidAccName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

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
		String submitToMethod = "TravelAuthorities_details.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
		
	}
	
	public String details() {
		getNavigationPanel(3);
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		//model.getTravelAuth(travel);
		model.getDesc(travel);
		model.getTravelSch(travel);
		model.getTravelApr(travel);
		model.getTravelAcc(travel);
		model.getMsg(travel);
		travel.setSaveFlag(true);
		//travel.setImgflag(true);
		model.reqData(travel,request);
		model.terminate();
		return "success";
	}
	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String search() {
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String f9Branch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query="SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER	 ORDER BY  CENTER_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("branch.code") ,getMessage("travel.branch")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchId","branchName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

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
	
	public String f9Employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'  ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
			 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("approver.code"),getMessage("travel.aprNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hidAppToken","approverName","hidApprover"};

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
	
	public String f9EmployeeApp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		
		
		String[] eId = request.getParameterValues("itAprId");
		String str="0";
		if(eId!=null)
		{
			for (int i = 0; i < eId.length; i++) {
				str+=","+eId[i];
			}	
		}
		
		
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("+str+") ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("alternate.approver.code"),getMessage("travel.altAprNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hidAltAppToken","altApprover","altAppCode"};

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
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
		
	}
	
	public String f9EmployeeAcc() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'  ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("accountant.code") ,getMessage("travel.accNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hidAccToken","accName","hidAccName"};

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
	
	public String f9EmployeeAltAcc() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String[] eId = request.getParameterValues("itAccId");
		String str="0";
		if(eId!=null)
		{
			for (int i = 0; i < eId.length; i++) {
				str+=","+eId[i];
			}	
		}
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("+str+") ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("alternate.accountant.code"),getMessage("travel.altAccNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"hidAltAccToken","altAccName","altAccCode" };

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

	public String f9EmployeeSch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'  ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("schedular.code"),getMessage("travel.schNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hidSchToken","schName","hidSchCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2};

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
	public String f9EmployeeAltSch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String[] eId = request.getParameterValues("itEmpId");
		String str="0";
		if(eId!=null)
		{
			for (int i = 0; i < eId.length; i++) {
				str+=","+eId[i];
			}	
		}
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,EMP_ID"
			  +" FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("+str+")  ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("alternate.scheduler.code") ,getMessage("travel.altSchNm")};

		String[] headerWidth = {  "20", "15"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hidAltSchToken","altSchName","altSchCode"};

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
		String submitToMethod = "TravelAuthorities_test.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
		
	}
	
	public String test(){
		getNavigationPanel(1);
		logger.info("------------test-----------"+travel.getAltSchCode());
		return SUCCESS;
	}
	
	public String reset() throws Exception{
		travel.setSelectAllAcc("");
		travel.setAccEmpName("");
		travel.setAccEmpId("");
		travel.setHidAltAccName("");
		travel.setAltAccName("");
		travel.setHidAccName("");
		travel.setAccName("");
		travel.setSelectAllAppr("");
		travel.setAppEmpName("");
		travel.setAppEmpId("");
		travel.setHidAltApprover("");
		travel.setAltApprover("");
		travel.setHidApprover("");
		travel.setApproverName("");
		travel.setSelectAllSchr("");
		travel.setSchEmpName("");
		travel.setSchEmpId("");
		travel.setHidAltSchCode("");
		travel.setAltSchName("");
		travel.setSchName("");
		travel.setHidSchCode("");
		travel.setBranchId("");
		travel.setDescription("");
		travel.setStatus("");
		travel.setBranchName("");
		travel.setHidappFlag("");
		
	
		return SUCCESS;
	}
	
	public String addSch()throws Exception{
		
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
		logger.info("Value Emp id .................... "+travel.getAltSchCode());
		getNavigationPanel(2);
		String[] empId = request.getParameterValues("itEmpId");
		String[] empName = request.getParameterValues("itEmpName");
		String[] empToken = request.getParameterValues("empToken");
		String[] empCheck = request.getParameterValues("empCheck");
		model.getAllList(travel, request);
		model.addSchedular(travel,empId,empToken,empName,empCheck);
		model.reqData(travel, request); 
		model.getMsg(travel);
		travel.setAltSchName("");
		travel.setAltSchCode("");
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setImgflag(true);
		model.terminate();
		return SUCCESS;

	}
public String addApr()throws Exception{
		
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context, session);
	
		getNavigationPanel(2);
		
		String[] empId = request.getParameterValues("itAprId");
		String[] empName = request.getParameterValues("itAprName");
		String[] empToken = request.getParameterValues("aprToken");
		String[] empCheck = request.getParameterValues("aprCheck");
		model.getAllList(travel, request);
		model.addApprover(travel,empId,empToken,empName,empCheck);
		model.reqData(travel, request); 
		model.getMsg(travel);
		travel.setAltApprover("");
		travel.setAltAppCode("");
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setImgflag(true);
		model.terminate();
		return SUCCESS;

	}


public String addAcc()throws Exception{
	
	TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
	model.initiate(context, session);
	logger.info("Value Emp id .................... "+travel.getAltAccCode());
	getNavigationPanel(2);
	
	String[] empId = request.getParameterValues("itAccId");
	String[] empName = request.getParameterValues("itAccName");
	String[] empToken = request.getParameterValues("accToken");
	String[] empCheck = request.getParameterValues("accCheck");
	model.getAllList(travel, request);
	model.addAccountant(travel,empId,empToken,empName,empCheck); 
	model.reqData(travel, request);
	model.getMsg(travel);
	travel.setAltAccName("");
	travel.setAltAccCode("");
	travel.setLoadFlag(true);
	travel.setFlag(true);
	travel.setImgflag(true);
	model.terminate();
	return SUCCESS;

}
	
	public String deleteDtl()throws Exception {
		getNavigationPanel(2);
		String code[]=request.getParameterValues("hdeleteOp");
		String empToken[]=request.getParameterValues("empToken");
		String itEmpName[]=request.getParameterValues("itEmpName");
		String itEmpId[]=request.getParameterValues("itEmpId");
		boolean result= false ;
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		model.getAllList(travel, request);
		result = model.delDtl(travel,code,empToken,itEmpName,itEmpId);
		
		if(result)
		{
			addActionMessage(getMessage("delete")); 
		}
		model.getMsg(travel);
		model.terminate();		
		model.reqData(travel, request);
		travel.setHdeleteOp("");
		travel.setAltSchCode("");
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setImgflag(true);
		return SUCCESS;

	}
	
	public String deleteApr()throws Exception {
		getNavigationPanel(2);
		String code[]=request.getParameterValues("hdeleteApr");
		String empToken[]=request.getParameterValues("aprToken");
		String itEmpName[]=request.getParameterValues("itAprName");
		String itAprId[]=request.getParameterValues("itAprId");
		boolean result= false ;
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		model.getAllList(travel, request);
		result = model.delApr(travel,code,empToken,itEmpName,itAprId);
		
		if(result)
		{
			addActionMessage(getMessage("delete")); 
		}
		model.getMsg(travel);
		model.terminate();		
		model.reqData(travel, request);
		travel.setHdeleteApr("");
		travel.setAltAppCode("");
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setImgflag(true);
		return SUCCESS;

	}
	
	public String deleteAcc()throws Exception {
		getNavigationPanel(2);
		String code[]=request.getParameterValues("hdeleteAcc");
		String empToken[]=request.getParameterValues("accToken");
		String itEmpName[]=request.getParameterValues("itAccName");
		String itAccId[]=request.getParameterValues("itAccId");
		boolean result= false ;
		TravelAuthoritiesModel model = new TravelAuthoritiesModel();	
		model.initiate(context,session);
		model.getAllList(travel, request);
		result = model.delAcc(travel,code,empToken,itEmpName,itAccId);
		
		if(result)
		{
			addActionMessage(getMessage("delete")); 
		}
		model.getMsg(travel);
		model.terminate();		
		model.reqData(travel, request);		
		travel.setHdeleteAcc("");
		travel.setAltAccCode("");
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setImgflag(true);
		return SUCCESS;

	}


}
