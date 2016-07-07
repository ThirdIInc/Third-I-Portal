 package org.struts.action.admin.transfer;
import org.paradyne.bean.admin.transfer.TransferApproval;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.admin.transfer.TransferApprovalModel;
import org.paradyne.model.admin.transfer.TransferModel;


/*
 * @author pradeep Kumar
 *Date:28-06-2008
 */

public class TransferApprovalAction extends org.struts.lib.ParaActionSupport{
	
	
	TransferApproval trnApp;

	public Object getModel(){
		
		return trnApp;
	}
	
	
	
	/**
	 * @return the trnApp
	 */
	public TransferApproval getTrnApp() {
		return trnApp;
	}

	/**
	 * @param trnApp the trnApp to set
	 */
	public void setTrnApp(TransferApproval trnApp) {
		this.trnApp = trnApp;
		
	}
	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		trnApp = new TransferApproval();
		trnApp.setMenuCode(67);
		
	}	
	
	public String approve()throws Exception {
	try{		
		TransferApprovalModel model = new TransferApprovalModel();
		model.initiate(context,session);
		//model.getTrnRecord(trnApp,request);
		model.terminate();
	}catch(Exception e){
		
		logger.error(e.getMessage());
		}
		return"success";
	}
	/**
	 * following function is called when Approved List,Rejected List or Pending lList is clicked
	 * @return
	 * @throws Exception
	 */
	public String chkeckdata()throws Exception {
		try{
		TransferApprovalModel model = new TransferApprovalModel();
		model.initiate(context, session);
		String status=request.getParameter("status");   //empReqAppr.getStatus();
		if(!(status.equals(""))){
		status=String.valueOf(status.charAt(0));
		if(status.equals("F"))
		status="P";
			
		if(status.equals("P"))
			trnApp.setPen("true");
		else if(status.equals("A"))
			trnApp.setApp("true");
		else if(status.equals("R"))
			trnApp.setRej("true");
		else if(status.equals("H"))
			trnApp.setHol("true");
		
		if(!(status.equals("P")||status.equals("H"))){
			trnApp.setApprflag("true");
		}
		model.getTrnRecord(trnApp,request,status);
		model.terminate();
		}
		
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * Following function is called when the general user makes login.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
	
		
			TransferApprovalModel model = new TransferApprovalModel();
			model.initiate(context,session);
			model.getLoginTrnRecord(trnApp,request,"P");
			model.terminate();
		
	}
	
	/**
	 * following function is called when the save button is clicked in transfer approval form.
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		try{
		String reqCode[]=request.getParameterValues("trfId");
		String reqStatus[]=request.getParameterValues("statusList");
		String level[]=request.getParameterValues("level");
		String empCode[]=request.getParameterValues("empId");
		String remk[]=request.getParameterValues("remark");
		TransferApprovalModel model = new TransferApprovalModel();
		model.initiate(context, session);
		boolean result=false;
		 int j=0;
		
		 
		for (int i=0;i<reqCode.length;i++){
		
			if(!(String.valueOf(reqStatus[i]).equals("P"))){
				model.forward(trnApp,reqStatus[i],reqCode[i],remk[i]);
				if(reqStatus[i].equals("A")){
				Object[][]empFlow=generateEmpFlow(empCode[i], "Tran", Integer.parseInt(level[i])+1);
				result=model.changeApplStatus(trnApp,empFlow,reqCode[i]);
				j=1;
				//for process manager alert
				String applnID = String.valueOf(reqCode[i]);
				String module = "Transfer";
			  String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = String.valueOf(empCode[i]);
					oldApprover = trnApp.getUserEmpId();
					newApprover = String.valueOf(empFlow[0][0]);
				} catch(Exception e) {
					logger.error(e);
				}
				String alertLevel = String.valueOf(Integer.parseInt(level[i]) + 1);					
				sendApprovalAlert(applnID, module, applicant, oldApprover, newApprover, alertLevel);
				
				
				
				
					}
				if(trnApp.getStatus().equals("H")&& (reqStatus[i].equals("R")||reqStatus[i].equals("A")))
				{
				j=1;
				}
				else if(!trnApp.getStatus().equals("H"))
					j=1;
		}
		
		}	
			if(result || j==1){
			  addActionMessage(getMessage("save"));
			  
			  
			  
			  
		  }
		model.getTrnRecord(trnApp, request, "P");
		model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	
	}
	/**
	 * following function is called when reset button is clicked in transfer approval form.
	 * @return
	 */
		public String reset(){
			trnApp.setApp("");
			trnApp.setAppDt("");
			trnApp.setApprflag("");
			trnApp.setEmpId("");
			trnApp.setEmpName("");
			trnApp.setEmpToken("");
			trnApp.setHol("");
			trnApp.setTrfId("");
			trnApp.setStatusList("");
			trnApp.setStatus("");
			trnApp.setRej("");
			trnApp.setPen("");
			trnApp.setLevel("");
			
			return SUCCESS;
		}
		
		
		
		//-----procees manage alert----------------
		public void sendApprovalAlert(String applnID, String module, String applicant, String oldApprover, String newApprover, String alertLevel) {
			try {
				
				
				logger.info("applnID is----"+applnID);
				logger.info("applicant is----"+applicant);
				ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				
				processAlerts.removeProcessAlert(applnID, module);
				
				String empID = "", msgType = "";
				
				if(!newApprover.equals("")) {
					//send alert from oldApprover to newApprover
					empID = newApprover;
					msgType = "A";
											
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					
					template.setEmailTemplate("TRANSFER APPL-APPROVER TO APPROVER");
					
					template.getTemplateQueries();
					
					EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);
					
					EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
					templateQuery2.setParameter(1, newApprover);
					
					EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);
					
					EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);
					
					template.configMailAlert();
					try {
						template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
						template.sendApplicationMail();
					} catch(Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
					
					
					//send alert from oldApprover to applicant
					empID = applicant;
					msgType = "I";
																	
					EmailTemplateBody template1 = new EmailTemplateBody();
					template1.initiate(context, session);
					
					template1.setEmailTemplate("TRANSFER APPL-APPROVER1 TO APPLICANT");
					
					template1.getTemplateQueries();
					
					EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); //FROM EMAIL
					templateQuery11.setParameter(1, oldApprover);
					
					EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); //TO EMAIL
					templateQuery12.setParameter(1, applicant);
					
					EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
					templateQuery13.setParameter(1, applnID);
					
					EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
					templateQuery14.setParameter(1, oldApprover);
					
					template1.configMailAlert();
					try {
						template1.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
						template1.sendApplicationMail();
					} catch(Exception e) {
						logger.error(e);
					}
					template1.clearParameters();
					template1.terminate();
				} else {
					//send alert from oldApprover to applicant
					empID = applicant;  
					msgType = "I";
											
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					
					template.setEmailTemplate("SUGGESTION APPL-APPROVER2 TO APPLICANT");
					
					template.getTemplateQueries();
					
					EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);
					
					EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
					templateQuery2.setParameter(1, applicant);
					
					EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);
					
					template.configMailAlert();
					try {
						template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
						template.sendApplicationMail();
					} catch(Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				}
			} catch(Exception e) {
				logger.error(e);
			}
		}


		
		
		
}
