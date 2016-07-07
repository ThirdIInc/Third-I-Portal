package org.struts.action.recruitment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Recruitment.CandidateScreeningStatus;
import org.paradyne.model.recruitment.CandidateScreeningStatusModel;;

public class CandidateScreeningStatusAction extends ParaActionSupport  {
	CandidateScreeningStatus bean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CandidateScreeningStatusAction.class);
	public CandidateScreeningStatus getBean() {
		return bean;
	}

	public void setBean(CandidateScreeningStatus bean) {
		this.bean = bean;
	}
	
	public void prepare_local() throws Exception {

		bean = new CandidateScreeningStatus();
		bean.setMenuCode(765);
	}
	
	public Object getModel() {
		return bean;
	}
	
	public String checkData(){
		
		
		return "success";
	}
	/**
	 * following function is called each time the page is leaded.
	 */
	public String input() throws Exception{
		CandidateScreeningStatusModel model=new CandidateScreeningStatusModel();
		model.initiate(context, session);
		model.getCandidateRecords(bean,"O",request);
		model.terminate();
		return "success";
	}
	
	public String reset()throws Exception{
		CandidateScreeningStatusModel model=new CandidateScreeningStatusModel();
		model.initiate(context, session);
		bean.setRequisitionId("");
		bean.setRequisitionName("");
		bean.setPositionId("");
		bean.setPositionName("");
		bean.setCandidateCode("");
		bean.setCandCode1("");
		bean.setCandidateName1("");
		bean.setYear1("");
		bean.setMonth("");
		bean.setCandGender("");
		bean.setManagerName("");
		bean.setHrManagerId(""); 
		bean.setFilterFlag("");
		bean.setMyPage("1");
		bean.setAppliedFilterFlag("false");
		String reqsLabel = getMessage("reqs.code");
		String postionLabel = getMessage("position");
		String candidatNameLabel = getMessage("candidatName");
		String candExpLabel = getMessage("candExp");
		String hiringLabel = getMessage("hiring.mgr");
		String genderLabel = getMessage("gender");
		model.getRecord(bean, "O",request,reqsLabel,postionLabel,candidatNameLabel,candExpLabel,hiringLabel,genderLabel);	//getRecord method to retrieve reqs details as per the status
 
		model.terminate();
		return "success";
	}
	
	
	
	/**
	 * following function is called when any button is clicked in the form.
	 * @return
	 */
	
	public String ckeckdata(){
	
		bean.setViewFilterFlag(true);  
		bean.setFilterFlag("true");
		
		if(bean.getRequisitionId().equals("")&& bean.getPositionId().equals("")&& bean.getCandCode1().equals("") &&bean.getYear1().equals("") &&
				bean.getMonth().equals("") && bean.getHrManagerId().equals("") && bean.getCandGender().equals("") ) 
		  {
			bean.setViewFilterFlag(false);  
			bean.setFilterFlag("");
		}	else
		{  
			if(bean.getAppliedFilterFlag().equals("false"))
				 {
					 bean.setViewFilterFlag(false);  
			         bean.setFilterFlag("");  
				 } 
		}
 
		 
		String status = request.getParameter("status");		//getting status which passed as a request parameter from form
	
		if(status.equals("")){
			status="O";
		}
		bean.setStatus(status);		//set application status as it is in request parameter
		
		String stat = "";
		
		if(status.equals("O")){	//if status is P
		
			
			stat = "Open List";	//set header status as Open List
		
		
		}	//end of if statement
		else if(status.equals("R")){	//if status is R
			stat = "Rejected List";		//set header status as Rejected List
			
			
			
			
		}	//end of else if statement 
		else if(status.equals("T")){	//if status is T
			stat = "Short List For Test";		//set header status Short List for Test
			
			
		}	//end of else if statement
		else if(status.equals("I")){	//if status is A
			stat = "Short List For Interview";		//set header status as Short List For Interview
			
		
			
			
		}	//end of else if statement
		else if(status.equals("B")){
			stat="Short List For Test/Interview";//set header status Short List for Test/Interview
			
		}
		
		//set header status as a request attribute
		request.setAttribute("stat", stat);	
		CandidateScreeningStatusModel model = new 
		CandidateScreeningStatusModel();	//creating an instance of CandidateScreeningModel class
		model.initiate(context, session);				 
		
		/**
		 * call getCandidateRecords(bean) method of CandidateScreeningModel class
		 * to retrieve all application details as per the selected status
		 */
		String reqsLabel = getMessage("reqs.code");
		String postionLabel = getMessage("position");
		String candidatNameLabel = getMessage("candidatName");
		String candExpLabel = getMessage("candExp");
		String hiringLabel = getMessage("hiring.mgr");
		String genderLabel = getMessage("gender");
		model.getRecord(bean, status,request,reqsLabel,postionLabel,candidatNameLabel,candExpLabel,hiringLabel,genderLabel);	//getRecord method to retrieve reqs details as per the status
 
		
		model.terminate();	//terminate the LoanApprovalModel class
		return "success";
	}
	/**
	 * following function is called when the view cv button is clicked to see the cv
	 * @throws Exception
	 */
	public void viewCV()throws Exception{
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			fileName= request.getParameter("fileName");
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/resume/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/msword"); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			}
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch",e);
			addActionMessage("Resume not found");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			logger.info("in finally for closing");
			if(fsStream!=null){
			fsStream.close();  
			}
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			}
		}
		
		
		//return null;
	}

	public String formTest() throws Exception{
		try {
			CandidateScreeningStatusModel model = new CandidateScreeningStatusModel();
			model.initiate(context, session);
			String stat = "Short List For Test";
			request.setAttribute("stat", stat);
			model.getCandidateRecords(bean, "T", request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}
	public String formInterview() throws Exception{
		try {
			CandidateScreeningStatusModel model = new CandidateScreeningStatusModel();
			model.initiate(context, session);
			String stat = "Short List For Interview";
			request.setAttribute("stat", stat);
			model.getCandidateRecords(bean, "I", request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}
	public String search()throws Exception{
		logger.info("in search ");
		if(bean.getViewEditFlag().equals("true")){
			bean.setViewFilterFlag(false); 
		}else{
			bean.setViewFilterFlag(true); 
		}
		
		CandidateScreeningStatusModel model=new CandidateScreeningStatusModel();
		model.initiate(context, session);
		String reqsLabel = getMessage("reqs.code");
		String postionLabel = getMessage("position");
		String candidatNameLabel = getMessage("candidatName");
		String candExpLabel = getMessage("candExp");
		String hiringLabel = getMessage("hiring.mgr");
		String genderLabel = getMessage("gender");
		bean.setMyPage("1");
		
		String status = request.getParameter("status");		//getting status which passed as a request parameter from form
	 	if(status.equals("")){
			status="O";
		}  
		String stat = ""; 
		if(status.equals("O")){	//if status is P 
			stat = "Open List";	//set header status as Open List 
		}	//end of if statement
		else if(status.equals("R")){	//if status is R
			stat = "Rejected List";		//set header status as Rejected List  
		}	//end of else if statement 
		else if(status.equals("T")){	//if status is T
			stat = "Short List For Test";		//set header status Short List for Test 
		}	//end of else if statement
		else if(status.equals("I")){	//if status is A
			stat = "Short List For Interview";		//set header status as Short List For Interview 
		}	//end of else if statement
		else if(status.equals("B")){
			stat="Short List For Test/Interview";//set header status Short List for Test/Interview 
		} 
		request.setAttribute("stat", stat);	
		model.getRecord(bean, status,request,reqsLabel,postionLabel,candidatNameLabel,candExpLabel,hiringLabel,genderLabel);	//getRecord method to retrieve reqs details as per the status
		model.terminate();
		
		return SUCCESS;
	}

	
	public String callForTestInterview() throws Exception{
		try {
			CandidateScreeningStatusModel model = new CandidateScreeningStatusModel();
			model.initiate(context, session);
			String stat = "Short List For Test/Interview";
			request.setAttribute("stat", stat);
			model.getCandidateRecords(bean, "B", request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}
	
	
	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("position")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "positionName","positionId" };

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
	
	
	
	
	
	public String f9HrManager() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("hiring.mgr")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "managerName","hrManagerId" };

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
	
	public String f9Requisition() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT  REQS_NAME,HRMS_REC_REQS_HDR.REQS_CODE FROM HRMS_REC_REQS_HDR " 
		     	       +"  WHERE REQS_APPROVAL_STATUS IN('A','Q') ORDER BY REQS_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("reqs.code")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"requisitionName","requisitionId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0, 1};

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

	public String f9CandidateAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("candidatName")};//getMessage("candidate.fname")
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"30","60"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"candidateName1","candCode1"};//, "relocate"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0,1 };//, 17};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}


	
	
	

}
