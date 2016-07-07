package org.struts.action.admin.srd;
import org.paradyne.bean.admin.srd.InitialAppointment;
import org.paradyne.model.admin.srd.InitialAppointmentModel;
import org.paradyne.model.admin.srd.SmallFamilyModel;
/* Pradeep Kumar Sahoo
 */
public class InitialAppointmentAction extends org.struts.lib.ParaActionSupport {
	
	
	InitialAppointment initialAppt;
	/**
	 * @return the initialAppt
	 */
	public InitialAppointment getInitialAppt() {
		return initialAppt;
	}

	/**
	 * @param initialAppt the initialAppt to set
	 */
	public void setInitialAppt(InitialAppointment initialAppt) {
		this.initialAppt = initialAppt;
	}

	

	public Object getModel(){
		
		return initialAppt;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		initialAppt = new InitialAppointment();
		initialAppt.setMenuCode(115);
	}
	
	public String AppointmentDetailsRecord() {
	logger.info(" OfficialDetailRecord 1");
	InitialAppointmentModel model =new InitialAppointmentModel();
		logger.info(" OfficialDetailRecord 2");
		model.initiate(context,session);
		logger.info(" OfficialDetailRecord 3");
		initialAppt=model.getRecord(initialAppt);
		logger.info(" OfficialDetailRecord 4");
		model.terminate();
		return "success";
	}
	
	public String PayDetailRecord() {
		logger.info(" OfficialDetailRecord 1");
		InitialAppointmentModel model =new InitialAppointmentModel();
			logger.info(" OfficialDetailRecord 2");
			model.initiate(context,session);
			logger.info(" OfficialDetailRecord 3");
			initialAppt=model.getPayScale(initialAppt);
			logger.info(" OfficialDetailRecord 4");
			model.terminate();
			return "success";
		}
	
	
	
	public String DesgDetailRecord() {
		logger.info(" OfficialDetailRecord 1");
		InitialAppointmentModel model =new InitialAppointmentModel();
			logger.info(" OfficialDetailRecord 2");
			model.initiate(context,session);
			logger.info(" OfficialDetailRecord 3");
			initialAppt=model.getDesg(initialAppt);
			logger.info(" OfficialDetailRecord 4");
			model.terminate();
			return "success";
		}
	
	/*public String save() throws Exception
	{
		logger.info("in delete");
		InitialAppointmentModel model=new InitialAppointmentModel();
		
		model.initiate(context,session);
		boolean result;
		
		logger.info("Before deleteHoliday");
		
		result = model.addAppt(initialAppt);
		logger.info("After deleteholiday");
		
			
		if(result){
			addActionMessage(getText("Record saved Successfully"));
	
		} else {
			addActionMessage("This record is already Saved.");
		}	
		
					
		
		model.terminate();
		return "success";
	}*/
	
	
	/*public String update() throws Exception
	{
		logger.info("in delete");
		InitialAppointmentModel model=new InitialAppointmentModel();
		
		model.initiate(context,session);
		boolean result=false;
		boolean res;
		res=model.checkID(initialAppt);
		
		logger.info("Before deleteSmallFamily");
		if(res) {
			result = model.modAppt(initialAppt);  
			
			
		} else {
			
			 addActionMessage("Please save the Record before Updating.");
		}
		
		
		if(result)
		{
			addActionMessage(getText("Record updated Successfully"));
		   //  hm.setDesc("");
		   //  hm.setHoliDate("");
			
		} 

		model.terminate();
		return "success";
	}*/
	
	
	
	
	
	public String save()throws Exception{
		InitialAppointmentModel model=new InitialAppointmentModel();
	model.initiate(context,session);
		
	boolean result=false;
		boolean result1;
	String str="";
	result1=model.checkID(initialAppt);
	if(result1){
			result=model.modAppt(initialAppt);
			
			
		}
		else{
		result=model.addAppt(initialAppt);
		}
		model.terminate();
	
	if(result){
		addActionMessage(getText("addMessage",""));
}
		else{
			addActionMessage("Initial Appointment can not be added");
		}
		return SUCCESS;
	}



public String reset() throws Exception{
	try{	
	
		initialAppt.setDceList("");
		initialAppt.setDceDate("");
		initialAppt.setEmployeeToken("");
		initialAppt.setInitCentName("");
		initialAppt.setInitRankCode("");
		initialAppt.setInitRankName("");
		initialAppt.setEmpRank("");
		initialAppt.setInitTradeName("");
		
		initialAppt.setDesgCode("");
		initialAppt.setDoctName("");
		initialAppt.setDtOfExm("");
		initialAppt.setDtOfJnDockyard("");
		initialAppt.setDtOfJnServ("");
		initialAppt.setEmpCent("");
	//	initialAppt.setEmpDept("");
		initialAppt.setEmpName("");
		initialAppt.setEmpId("");
	//	initialAppt.setEmpDept("");
		initialAppt.setHspName("");
		initialAppt.setInitialDesg("");
		initialAppt.setInitialJnDate("");
		initialAppt.setPayCode("");
		initialAppt.setPayScale("");
		initialAppt.setRemarks("");
		initialAppt.setSuperAnnDt("");
		initialAppt.setRtType("");
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	} 
	return "success";
}


public String delete() throws Exception {
	
	logger.info("Inside Delete");
	InitialAppointmentModel model=new InitialAppointmentModel();
	model.initiate(context,session);
	boolean result=model.deleteAppt(initialAppt);
	model.terminate();
	if (result){
		addActionMessage(getText("delMessage",""));
		initialAppt.setDceList("");
		//initialAppt.setDesgCode("");
		initialAppt.setDoctName("");
		initialAppt.setDtOfExm("");
		initialAppt.setDtOfJnDockyard("");
		initialAppt.setDtOfJnServ("");
		initialAppt.setEmpCent("");
		//initialAppt.setEmpDept("");
		initialAppt.setEmpName("");
		//initialAppt.setEmpId("");
		//initialAppt.setEmpDept("");
		initialAppt.setHspName("");
		initialAppt.setInitialDesg("");
		initialAppt.setInitialJnDate("");
		initialAppt.setPayCode("");
		initialAppt.setPayScale("");
		initialAppt.setRemarks("");
		initialAppt.setSuperAnnDt("");
		initialAppt.setDceDate("");
		initialAppt.setEmployeeToken("");
		initialAppt.setInitCentName("");
		initialAppt.setEmpRank("");
		initialAppt.setInitTradeName("");
		initialAppt.setRtType("");
		
		
	}
	else {
	addActionMessage("Appointment cannot be deleted");
	}
	return "success";
}

public void prepare_withLoginProfileDetails() throws Exception {
	logger.info("*****LOGIN PROFILE");
	InitialAppointmentModel model = new InitialAppointmentModel();
	model.initiate(context,session);
	if (initialAppt.isGeneralFlag()) {
		
		model.getEmployeeDetails(initialAppt.getUserEmpId(),initialAppt);
		
	}else{
		String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
		logger.info("Selected Employee record ****************"+offcEmp);
		model.getEmployeeDetails(offcEmp,initialAppt);
	}
	model.getRecord(initialAppt);
	model.terminate();
}




public String f9action() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = " SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
					+"	(HRMS_CENTER.CENTER_ID||'-'||HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,EMP_ID  FROM HRMS_EMP_OFFC "
					+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+"  LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+"  LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";
					
					query+=getprofileQuery(initialAppt);
					query+=" ORDER BY EMP_ID  ";
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Token No.", "Employee Name","Center Name","Rank Name"};
	
	String[] headerWidth={"20","30","30","30"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"employeeToken","empName","empCent","empRank","empId"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1,2,3,4};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "true";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="InitialAppointment_AppointmentDetailsRecord.action";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}


public String f9payaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT PAYSC_ID,NVL(PAYSC_NAME,'') FROM HRMS_PAYSCALE ORDER BY PAYSC_ID"; 
		
		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Payscale Code", "Payscale name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"payCode","payScale"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	//String submitToMethod="InitialAppointment_PayDetailRecord.action";
	String submitToMethod="";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}

public String f9desgaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT DESG_ID,DESG_NAME FROM HRMS_DESG ORDER BY DESG_ID ";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Designation Code", "Designation Name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"desgCode","initialDesg"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	//String submitToMethod="InitialAppointment_DesgDetailRecord.action";
	String submitToMethod="";
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}



public String f9initialTradeaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT TRADE_CODE,TRADE_NAME FROM HRMS_TRADE ORDER BY TRADE_CODE ";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Trade Code", "Trade Name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"initTradeCode","initTradeName"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}

public String f9initialCentaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Center Code", "Center Name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"initCentCode","initCentName"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}

public String f9initialRankaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT RANK_ID,RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Rank Code", "Rank Name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"initRankCode","initRankName"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}






}



