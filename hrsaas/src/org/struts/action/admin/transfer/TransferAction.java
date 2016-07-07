package org.struts.action.admin.transfer;

import org.paradyne.bean.admin.transfer.TransferApplication;

/**
 * author:Pradeep Kumar
 * Date:20-06-2008
 */

import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.admin.transfer.TransferModel;
import org.paradyne.model.leave.LeaveApplicationModel;


public class TransferAction extends org.struts.lib.ParaActionSupport{
	/**
	 * 
	 * author:Pradeep Kumar
	 */
	TransferApplication trfApp;

	/**
	 * @return the trfApp
	 */
	public TransferApplication getTrfApp() {
		return trfApp;
	}

	/**
	 * @param trfApp the trfApp to set
	 */
	public void setTrfApp(TransferApplication trfApp) {
		this.trfApp = trfApp;
	}
	
	public Object getModel(){
		
		return trfApp;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		trfApp = new TransferApplication();
		trfApp.setMenuCode(66);
	}
	/*
	 * Following function is called in f9empaction function when employee pop up window button is clicked.
	 */
	public String EmployeeRecord() {
	
		   TransferModel model =new TransferModel();
			
			model.initiate(context,session);
			
			model.getEmpRecord(trfApp);
			trfApp.setTrfId("");
			trfApp.setReason("");
			trfApp.setForward("");
			trfApp.setPropName("");
			trfApp.setRelName("");
			trfApp.setRelReq("");
			trfApp.setRelDt("");
			trfApp.setAprName("");
			trfApp.setStatus("");
		
		
			model.terminate();
			return "success";
		}
	/*
	 * Following function is called in f9trfaction function when search button is called .
	 */
	public String TransferDetailsRecord() {
		
		TransferModel model =new TransferModel();
			
			model.initiate(context,session);
			
			model.getTrfRec(trfApp);
			trfApp.setCentFlag("false");
			trfApp.setDivFlag("false");
			trfApp.setDeptFlag("false");
			trfApp.setRelFlag("false");
			trfApp.setDateFlag("false");
			
			model.terminate();
			return "success";
		}
	
	/*
	 * Following function is called when save button is called.
	 */
	public String save()throws Exception {
		
	
		TransferModel model = new TransferModel();
		
		model.initiate(context,session);
		
		boolean result ;
		Object [][]empflow=generateEmpFlow(trfApp.getEmpId(),"Tran", 1);
		if(empflow==null){
			addActionMessage("Reporting Structure Not Defined for the Employee\n"+trfApp.getEmpName());
			addActionMessage("Please Contact HR Manager !");
			
		}else{
		
		if(trfApp.getTrfId().equals("")){
			result = model.addTrf(trfApp,empflow);
			if(result) {
				addActionMessage(getMessage("save"));
				//process manager for transfer....//
				
				
				
				String sql = " SELECT MAX(TRANSFER_CODE) FROM HRMS_TRANSFER ";
				Object[][] obj = model.getSqlModel().getSingleResult(sql);
				String transferCode = String.valueOf(obj[0][0]);
				trfApp.setTrfId(transferCode);
				logger.info("SUGGESTION_CODE==="+trfApp.getTrfId());
				//pma+email temp
				try {
					String applnID = trfApp.getTrfId();
					String module = "Transfer";
					String applicant = trfApp.getEmpId();
					String approver = String.valueOf(empflow[0][0]);
					sendApplicationAlert(applnID, module, applicant, approver);
				} catch(Exception e) {
					logger.error(e);
				}
			
				
				
				
				
				
				
				
				
				trfApp.setStatus("Pending");
				reset();
		}
		 else {
			addActionMessage(getMessage("save.error"));
		}
		}else{
			
			 result = model.modTrf(trfApp,empflow);
		
			 if(result) {
					addActionMessage(getMessage("update"));
					
					//proces  manager alert  for Transfer in update
					
					
					String sql = " SELECT MAX(TRANSFER_CODE) FROM HRMS_TRANSFER ";
					Object[][] obj = model.getSqlModel().getSingleResult(sql);
					String transferCode = String.valueOf(obj[0][0]);
					trfApp.setTrfId(transferCode);
					logger.info("TRANSFER_CODE==="+trfApp.getTrfId());
					
					try {
						String applnID = trfApp.getTrfId();
						String module = "Transfer";
						String applicant = trfApp.getEmpId();
						String approver = String.valueOf(empflow[0][0]);
						sendApplicationAlert(applnID, module, applicant, approver);
					} catch(Exception e) {
						logger.error(e);
					}
					
					trfApp.setStatus("Pending");
					reset();
				} else {
					addActionMessage("Reporting Structure Not Defined for the Employee\n"+trfApp.getEmpName());
					addActionMessage("Please Contact HR Manager !");
				}
		}
	
		model.terminate();
		
		
	
		}
		
		return "success";
	}


	
	
	
	
	//process manager  alert for transfer. application ............
	
	public void sendApplicationAlert(String applnID, String module, String applicant, String approver) {
		try {
			String msgType = "A";
			String level = "1";
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			template.setEmailTemplate("TRANSFER  APPL-APPLICANT TO APPROVER");
								
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, approver);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);
			
			template.configMailAlert();
			template.sendProcessManagerAlert(approver, module, msgType, applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch(Exception e) {
			logger.error(e);
		}
	}

	/*Following function is called to display the current date when Transfer Application link is clicked in the menu.
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	
	public String input() throws Exception {
		
		TransferModel model =new TransferModel();
		
		model.initiate(context,session);
		model.getApplicationDate(trfApp);
		trfApp.setShowFlag("false");
		trfApp.setCentFlag("false");
		trfApp.setDivFlag("false");
		trfApp.setDeptFlag("false");
		trfApp.setRelFlag("false");
		trfApp.setDateFlag("false");
		model.initiate(context,session);	
		model.terminate();

		return "success";
		
		
	}




	/*
	 * Following function is called when delete button is clicked .
	 */
	public String delete() throws Exception {
	

			TransferModel model= new TransferModel();
			model.initiate(context,session);
			boolean result=model.deleteTrf(trfApp);
			model.terminate();
			if (result){
				addActionMessage(getMessage("delete"));
				reset();
				
			
			}
			else {
			addActionMessage(getMessage("del.error"));
			}
			return "success";
	}
	
	
	public String reset() throws Exception{
		try{	
		
			trfApp.setTrfId("");
			trfApp.setEmpId("");
			trfApp.setEmpName("");
			trfApp.setEmpDesg("");
			trfApp.setCurDept("");
			trfApp.setCurCent("");
			trfApp.setNewCent("");
			trfApp.setReason("");
			trfApp.setForward("");
			//trfApp.setAppDt("");
			trfApp.setPropId("");
			trfApp.setPropName("");
			trfApp.setRelName("");
			trfApp.setRelReq("");
			trfApp.setRelDt("");
			trfApp.setAprName("");
			trfApp.setStatus("");
			trfApp.setEmpToken("");
			trfApp.setType("");
			trfApp.setTransUnit("");
			trfApp.setRank("");
			trfApp.setCurCent("");
			trfApp.setCurCentId("");
			trfApp.setCurDivId("");
			trfApp.setCurDeptId("");
			trfApp.setCurDiv("");
			trfApp.setNewDept("");
			trfApp.setNewDeptId("");
			trfApp.setNewDiv("");
			trfApp.setNewDivId("");
			trfApp.setCentFlag("false");
			trfApp.setDivFlag("false");
			trfApp.setDeptFlag("false");
			trfApp.setRelFlag("false");
			trfApp.setDateFlag("false");
			
			TransferModel model =new TransferModel();
			
			model.initiate(context,session);
			model.getApplicationDate(trfApp);
			model.initiate(context,session);	
			model.terminate();

		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		} 
		return "success";
	}
	
	/*
	 * Following function is called when a reset button is clicked by general user. 
	 */
	public String genreset() throws Exception{
		try{	
	
			trfApp.setReason("");
			trfApp.setForward("");
	
			trfApp.setPropName("");
			trfApp.setRelName("");
			trfApp.setRelReq("");
			trfApp.setRelDt("");
			trfApp.setAprName("");
			trfApp.setStatus("");
			trfApp.setType("");
			trfApp.setTransUnit("");
			trfApp.setPropId("");
	
			
			}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		} 
		return "success";
	}
	
	/*
	 * Following function is called when Lock button is clicked.
	 */
	public String lock() throws Exception{
		
		TransferModel model = new TransferModel();
		model.initiate(context,session);
		Object[][] data=model.getLock(trfApp);
		if(String.valueOf(data[0][0]).equals("L")){
			
			addActionMessage("Transfer Details Already Locked.");
		}else {
			
			boolean result=model.updateOfficialDetails(trfApp);
			if(result){
				addActionMessage("Transfer Details has been Locked Successfully.");
			}else {
				addActionMessage("Transfer has not been Approved.");
			}
		}
		return "success";
	}
	
	/*Following function is called when a general user makes login.
	 * (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			trfApp.setSource(source);
			
			
			trfApp.setShowFlag("true");
			if (trfApp.isGeneralFlag()) {
				TransferModel model = new TransferModel();
				model.initiate(context, session);
				model.getEmployeeDetails(trfApp.getUserEmpId(), trfApp);
				trfApp.setGenLoginFlag("false");
				trfApp.setCentFlag("false");
				trfApp.setDivFlag("false");
				trfApp.setDeptFlag("false");
				trfApp.setRelFlag("false");
				trfApp.setDateFlag("false");
				//model.getLoginTransfer(trfApp.getUserEmpId(), trfApp);
				model.terminate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	/*
	 * Following function is called when view details button is clicked in Transfer Approval form.
	 */
	
	public String callByTransferApprove(){
		logger.info("request***********************************"+request.getParameter("appCode"));
		String applicationCode = request.getParameter("trfId");
		String empId= request.getParameter("empId");
		trfApp.setTrfId(applicationCode);
		trfApp.setEmpId(empId);
		TransferModel model = new TransferModel();
		model.initiate(context,session);
	//	model.showTransferApplication(trfApp,applicationCode,empid);
		model.getTrfRecord(trfApp);
		model.getTableHistoryDetails(trfApp);// code already defined
	//	model.getLeaveComment(leaveApplication);
		trfApp.setCentFlag("true");
		trfApp.setDivFlag("true");
		trfApp.setDeptFlag("true");
		trfApp.setShowFlag("true");
		trfApp.setGenEmpFlag("false");
		trfApp.setGenLoginFlag("false");
		trfApp.setRelFlag("true");
		trfApp.setDateFlag("true");
		trfApp.setIsForApprove("true");
		logger.info("value of setshowflag"+trfApp.getShowFlag());
		model.terminate();
		return "success";
	}
	
	
	
	

	/*
	 * Following function is called when report button is clicked in the jsp page.
	 */
	public void report() throws Exception {

		TransferModel model = new TransferModel();
		model.initiate(context,session);
		model.generateReportPdf(trfApp,response);
		model.terminate();
		//return "report";
	}
	
	
	
	public String f9trfaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT TRANSFER_CODE,EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY')," 
						+"  CASE WHEN TRANSFER_STATUS='P' AND TRANSFER_LEVEL!=1   THEN 'Forwarded' "
						+" WHEN TRANSFER_STATUS='P' THEN 'Pending' WHEN TRANSFER_STATUS='A' THEN 'Approved' WHEN TRANSFER_STATUS='R' THEN 'Rejected' ELSE 'Recommended' END,HRMS_TRANSFER.EMP_ID,TRANSFER_LEVEL "
						+" FROM HRMS_TRANSFER " 
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" ;
						query+=getprofileQuery(trfApp);
						query+=" ORDER BY TRANSFER_CODE  ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("transfer.code"), getMessage("employee.id"),getMessage("employee"),getMessage("application.date"),getMessage("status")};
		
		String[] headerWidth={"10", "20","35","15","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"trfId","empToken","empName","appDt","status","empId","level"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4,5,6};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="TransferApplication_TransferDetailsRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
			+" HRMS_RANK.RANK_NAME, "
			+" HRMS_DEPT.DEPT_NAME,HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME,HRMS_CENTER.CENTER_ID,DEPT_ID,DIV_ID,EMP_ID FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
			+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
			+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
			+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
			+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  ";
			query+=getprofileQuery(trfApp);
			query+= " AND EMP_STATUS IN('S','R')  ORDER BY EMP_ID " ;

		/** 
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("employee.id"),getMessage("employee"),getMessage("designation"),getMessage("department"),getMessage("division"),getMessage("branch") };
		
		String[] headerWidth={"13", "25","20","20","25","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"empToken","empName","rank","curDept","curDiv","curCent","curCentId","curDeptId","curDivId","empId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4,5,6,7,8,9};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
	
		String submitToMethod="TransferApplication_EmployeeRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	
	public String f9centaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME  FROM HRMS_CENTER ORDER BY CENTER_ID ";
			

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("new.branch.code") ,getMessage("new.branch")};
		
		String[] headerWidth={"10","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"newCentId","newCent"};
		
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
		
		trfApp.setMasterMenuCode(31);
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9deptaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME  FROM HRMS_DEPT ORDER BY DEPT_ID ";
			

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("new.department.code") ,getMessage("new.department")};
		
		String[] headerWidth={"10","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"newDeptId","newDept"};
		
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
		trfApp.setMasterMenuCode(23);
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9divaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DIV_ID,DIV_NAME  FROM HRMS_DIVISION";
		
		if(trfApp.getUserProfileDivision() !=null && trfApp.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+trfApp.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("new.division.code"),getMessage("new.division")};
		
		String[] headerWidth={"10","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"newDivId","newDiv"};
		
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
		trfApp.setMasterMenuCode(42);
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9propaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		//String query = " SELECT EMP_TOKEN,(TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  "
			//	+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID NOT IN("+trfApp.getEmpId()+") ORDER BY EMP_ID ";
			

		String query = " SELECT EMP_TOKEN,(TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  ORDER BY EMP_ID ";
			
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("token.no."),getMessage("proposed.name") };
		
		String[] headerWidth={"10","50"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"propToken","propName","propId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
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
	
	
	public String f9relaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		/*String query = " SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID NOT IN("+trfApp.getEmpId()+") ORDER BY EMP_ID ";
		*/
		
		
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE EMP_STATUS='S' ORDER BY EMP_ID ";
			


		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("employee.id"),getMessage("employee") };
		
		String[] headerWidth={"15","50"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"relToken","propName","relName","relId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3};
		
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
