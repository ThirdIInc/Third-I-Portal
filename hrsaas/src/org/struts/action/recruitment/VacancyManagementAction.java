package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.VacancyManagement;
import org.paradyne.model.recruitment.VacancyManagementModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class VacancyManagementAction extends ParaActionSupport {

	VacancyManagement vacancyMgmt = null;
	
	public VacancyManagement getVacancyMgmt() {
		return vacancyMgmt;
	}

	public void setVacancyMgmt(VacancyManagement vacancyMgmt) {
		this.vacancyMgmt = vacancyMgmt;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		vacancyMgmt = new VacancyManagement();
		vacancyMgmt.setMenuCode(745);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return vacancyMgmt;
	}
	
	/**
	 * this method is to retrieve all open requisitions data on load.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		/**
		 * this getRecord method retrieves all Open Req and Approved data on load.
		 */
		//model.getRecord(vacancyMgmt,"O","A","O",request);
		model.terminate();
	}
	/**
	 * added the following method on date:17-03-2009 by Pradeep
	 * Following function is called when the page is loaded.
	 */
	public String input(){
		
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		/**
		 * this getRecord method retrieves all Open Req and Approved data on load.
		 */
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		
		// added  this condition for back button
		String vac=request.getParameter("status");
		String stat;
		if(vac==null || vac.equals("null") || vac.equals("")){
			vac="O";
			stat="Open Vacancies";
			
		}
			if(vac.equals("P")){
				stat="Published Vacancies";
				//if(request.getParameter("flag").equals("null") || request.getParameter("flag").equals("") || request.getParameter("flag")==null){
				//vacancyMgmt.setPublishButtonFlag(request.getParameter("flag"));
				//}
			}else if(vac.equals("C")){
				stat="Closed Vacancies";
			}else{
				stat="Open Vacancies";
			}
			
			request.setAttribute("stat", stat);
			vacancyMgmt.setVacancy(vac);
			if(vac.equals("C")){
				model.getRecord(vacancyMgmt,"C","A",vac,msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
				vacancyMgmt.setCloseVacancyFlag("true");
			}else{
				model.getRecord(vacancyMgmt,"O","A",vac,msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
			}
			// end new added  condition for back button
		
		//model.getRecord(vacancyMgmt,"O","A","O",msg,msg1,msg2,msg3,msg4,msg5,msg6,request);//  this is Old condition
		model.terminate();
		return "success";
	}
	
	/**
	 * this method is called when Back button from vacancy published is clicked
	 * @return
	 * @throws Exception
	 */
	public String backPublish() throws Exception{
		
		
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		String vac=request.getParameter("status");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		/**
		 * added on date-17-03-2009 by Pradeep
		 */
		String stat;
	if(vac==null || vac.equals("null") || vac.equals("")){
		logger.info("if vac is null===="+vac);
		vac="O";
		stat="Open Vacancies";
		
	}
		if(vac.equals("P")){
			stat="Published Vacancies";
			if(request.getParameter("flag").equals("null") || request.getParameter("flag").equals("") || request.getParameter("flag")==null){
			vacancyMgmt.setPublishButtonFlag(request.getParameter("flag"));
			}
		}else if(vac.equals("C")){
			stat="Closed Vacancies";
			vacancyMgmt.setCloseVacancyFlag("true");
		}else{
			stat="Open Vacancies";
		}
		
		request.setAttribute("stat", stat);
		vacancyMgmt.setVacancy(vac);
		if(vac.equals("C")){
			vacancyMgmt.setPublishButtonFlag("false");
			vacancyMgmt.setCloseVacancyFlag("true");
			model.getRecord(vacancyMgmt,"C","A",vac,msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		}else{
			vacancyMgmt.setPublishButtonFlag("false");
			model.getRecord(vacancyMgmt,"O","A",vac,msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		}
		model.terminate();
		return SUCCESS;
		/*VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		String vac=request.getParameter("status");
		*//**
		 * this getRecord method retrieves all Open Req and Approved data on load.
		 *//*
		model.getRecord(vacancyMgmt,"O","A","O",request);
		model.terminate();
		return SUCCESS;*/
	}
	
	/**
	 * following function is called when the view Requisition button is clicked and the back button in Manpower Requisition form is clicked.
	 * @return
	 * @throws Exception
	 * Added by Pradeep on Date:24-03-2009
	 */
	public String callFunction()throws Exception{
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		String vac=request.getParameter("status");
		/**
		 * added on date-17-03-2009 by Pradeep
		 */
		String stat;
		if(vac.equals("P")){
			stat="Published Vacancies";
		}else if(vac.equals("C")){
			stat="Closed Vacancies";
		}else{
			stat="Open Vacancies";
		}
	
		request.setAttribute("stat", stat);
		vacancyMgmt.setVacancy(vac);
		if(vac.equals("C")){
			model.getRecord(vacancyMgmt,"C","A","O",msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		}else{
			model.getRecord(vacancyMgmt,"O","A",vac,msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		}
		model.terminate();
		return SUCCESS;
	}
	
	
	/**
	 * this method is called on the search button where only Open requisition or closed are searched.
	 * @return
	 * @throws Exception
	 */
	public String search()throws Exception{
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		String vac=vacancyMgmt.getVacancy();
		vacancyMgmt.setMyPage("1");
		/**
		 * added on date-17-03-2009 by Pradeep
		 */
		String stat;
		if(vac.equals("P")){
			stat="Published Vacancies";
		}else if(vac.equals("C")){
			stat="Closed Vacancies";
			
		}else{
			stat="Open Vacancies";
			
		}
		logger.info("status is"+vac);
		vacancyMgmt.setApplyFilterFlag("true");
		vacancyMgmt.setEnableFilterValue(true);
		request.setAttribute("stat", stat);
		vacancyMgmt.setSearchFlag("true");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		model.getRecord(vacancyMgmt,"O","A",vacancyMgmt.getVacancy(),msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		vacancyMgmt.setChkSerch("true");
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * this method clears all the search fields and get's Open requisition records.
	 * @return
	 * @throws Exception
	 */
	public String clear()throws Exception{
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		vacancyMgmt.setDivId("");
		vacancyMgmt.setDivName("");
		vacancyMgmt.setBranchId("");
		vacancyMgmt.setBranchName("");
		vacancyMgmt.setDeptId("");
		vacancyMgmt.setDeptName("");
		vacancyMgmt.setVacancy("O");
		vacancyMgmt.setFDate("");
		vacancyMgmt.setTDate("");
		vacancyMgmt.setPositionId("");
		vacancyMgmt.setPositionName("");
		vacancyMgmt.setHrManagerId("");
		vacancyMgmt.setManagerName("");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		model.getRecord(vacancyMgmt,"O","A","O",msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		model.terminate();
		return SUCCESS;
	}
	
	/* This method is used to clear the  the Applyid filter for Open,Public and Closed Requistion
	 * @return
	 * @throws Exception
	 * */
	public String clearFilter()throws Exception{
		VacancyManagementModel model = new VacancyManagementModel();
		model.initiate(context, session);
		vacancyMgmt.setDivId("");
		vacancyMgmt.setDivName("");
		vacancyMgmt.setBranchId("");
		vacancyMgmt.setBranchName("");
		vacancyMgmt.setDeptId("");
		vacancyMgmt.setDeptName("");
		vacancyMgmt.setVacancy("O");
		vacancyMgmt.setFDate("");
		vacancyMgmt.setTDate("");
		vacancyMgmt.setPositionId("");
		vacancyMgmt.setPositionName("");
		vacancyMgmt.setHrManagerId("");
		vacancyMgmt.setManagerName("");
		
		vacancyMgmt.setChkSerch("");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("Req.fDate");
		String msg6=getMessage("Req.tDate");
		
		
		model.getRecord(vacancyMgmt,"O","A","O",msg,msg1,msg2,msg3,msg4,msg5,msg6,request);
		vacancyMgmt.setShowFilterValue(true);
				
		model.terminate();
		return SUCCESS;
	}
	/**
	 * THIS METHOD IS USED FOR DISPLAYING RECORDS FOR published requisition
	 * 
	 * @return String
	 */
	public String callPublishStatus()throws Exception{
		try {
			VacancyManagementModel model = new VacancyManagementModel();
			model.initiate(context, session);
			String pubStatus = "";
			String stat = "";
			//	vacancyMgmt.setMyPage("1");
			vacancyMgmt.setApplyFilterFlag("true");
			String msg = getMessage("division");
			String msg1 = getMessage("branch");
			String msg2 = getMessage("department");
			String msg3 = getMessage("hiring.mgr");
			String msg4 = getMessage("position");
			String msg5 = getMessage("Req.fDate");
			String msg6 = getMessage("Req.tDate");
			pubStatus = request.getParameter("status");
			pubStatus = String.valueOf(pubStatus.charAt(0));
			System.out.println("pubStatus >>>>>"+pubStatus);
			if (pubStatus.equals("P")) {
				vacancyMgmt.setRePublishFlag("false");
				vacancyMgmt.setCloseVacancyFlag("false");
				stat = "Published Vacancies";
			} else if (pubStatus.equals("C")) {
				vacancyMgmt.setRePublishFlag("true");
				vacancyMgmt.setCloseVacancyFlag("true");
				stat = "Closed Vacancies";
			} 
			request.setAttribute("stat", stat);
			model.getPublishedRecord(vacancyMgmt, pubStatus, "A", "O", msg,
					msg1, msg2, msg3, msg4, msg5, msg6, request);
			vacancyMgmt.setShowFilterValue(true);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * THIS METHOD IS USED FOR DISPLAYING RECORDS FOR Open,published and closed requisition
	 * 
	 * @return String
	 */
	public String callstatus() {
		try {
			VacancyManagementModel model = new VacancyManagementModel();
			model.initiate(context, session);
			String status = "";
			String stat = "";
			String msg = getMessage("division");
			String msg1 = getMessage("branch");
			String msg2 = getMessage("department");
			String msg3 = getMessage("hiring.mgr");
			String msg4 = getMessage("position");
			String msg5 = getMessage("Req.fDate");
			String msg6 = getMessage("Req.tDate");
				status = request.getParameter("status");
				status = String.valueOf(status.charAt(0));
			if (status.equals("O")) {
				vacancyMgmt.setCloseVacancyFlag("false");
				stat = "Open Vacancies";
			} else if (status.equals("C")) {
				stat = "Closed Vacancies";
				vacancyMgmt.setCloseVacancyFlag("true");
			} 
			request.setAttribute("stat", stat);
			//model.collect(levApp, status);
			model.getRecord(vacancyMgmt, status, "A", "O", msg, msg1, msg2,
					msg3, msg4, msg5, msg6, request);
			vacancyMgmt.setShowFilterValue(true);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}// end of callstatus
	
	/**
	 * this method is to display all the division
	 * @return
	 * @throws Exception
	 */
	public String f9Division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
		
		if(vacancyMgmt.getUserProfileDivision() !=null && vacancyMgmt.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+vacancyMgmt.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER(DIV_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("Division.Name")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"divName","divId"};

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
	
	/**
	 * this method is to display all the branch
	 * @return
	 * @throws Exception
	 */
	public String f9Branch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("Branch.Name")};

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

		String[] fieldNames = { "branchName","branchId" };

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
	 * this method is to display all the Department
	 * @return
	 * @throws Exception
	 */
	public String f9Department() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("Department.Name")};

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

		String[] fieldNames = { "deptName","deptId" };

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
	 * this method is to display all the Position(Rank)
	 * @return
	 * @throws Exception
	 */
	
	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("Position")};

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
	
	
	
	/**
	 * this method is to display all the Hiring Manager name .
	 * @return
	 * @throws Exception
	 */
	
	public String f9HrManager() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S' ORDER BY EMP_ID ";

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
	
	public String updateRequisitionStatus() {
		try {
			VacancyManagementModel model = new VacancyManagementModel();
			String requisitionCode = request.getParameter("requisitionCode");
			String noOfVacancies = request.getParameter("noOfVacancies");
			model.initiate(context, session);
			boolean result = model.updateRequisitionStatus(vacancyMgmt, request,requisitionCode,noOfVacancies);
			if(result) {
				addActionMessage("Vacancies closed successfully.");
			}else{
				addActionMessage("Unable to close vacancies.");
			}
			model.terminate();
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*public String report() throws Exception {
		try {
			System.out.println(" in Vacancy Report Action");
			
			
			VacancyManagementModel model = new VacancyManagementModel();
			model.initiate(context, session);
		//	vacancyMgmt.setBackFlag("");
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("hiring.mgr"), getMessage("position"),
					getMessage("reqs.code"), getMessage("applied.by"),
					getMessage("reqs.date"),
					getMessage("noofvacan"), getMessage("required.date") }; 
			model.getReport(vacancyMgmt, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}*/
	
}
