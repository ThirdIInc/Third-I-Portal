/**
 * @author saipavan v 
 * 25-08-2008
 *
 */
package org.struts.action.settings;



import org.paradyne.bean.settings.SuggestionApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.settings.SuggestionApprovalModel;

import org.struts.lib.ParaActionSupport;


public class SuggestionApprovalAction extends ParaActionSupport {

	
	SuggestionApproval approval;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	@Override
	public void prepare_local() throws Exception {
		
		approval= new SuggestionApproval();
		approval.setMenuCode(400);
	}

	
	public Object getModel() {
	
		return approval;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		SuggestionApprovalModel model = new SuggestionApprovalModel();
		model.initiate(context,session);
		
		approval.setOutAppStatus("P");
	    model.add(approval);
		approval.setApprflag("false");
		request.setAttribute("stat", "Pending List");
		model.terminate();
		
	}
	

	public String ckeckdata()throws Exception {
		SuggestionApprovalModel model=new SuggestionApprovalModel();
		model.initiate(context,session);
		
		String status = request.getParameter("status");
		logger.info("status------------"+status);
		approval.setOutAppStatus(status);
		
		String stat = "";
		
		if(status.equals("P") || status.equals(" ")){
			logger.info("pending-------------");
			stat = "Pending List";
			approval.setApprflag("false");
		}else if(status.equals("A")){
			stat = "Approved List";
			logger.info("approved-------------");
			approval.setApprflag("true");
		}else if(status.equals("R")){
			stat = "Rejected List";
			logger.info("Rejected-------------");
			approval.setApprflag("true");
		}else if(status.equals("H")){
			stat = "On Hold List";
			logger.info("on hold-------------");
			approval.setApprflag("true");
		}
		request.setAttribute("stat", stat);
		model.add(approval);
		model.terminate();
		return SUCCESS;
	}
	
	public String callstatusFromAppl(){
		SuggestionApprovalModel model = new SuggestionApprovalModel();
		model.initiate(context,session);
		String status = request.getParameter("hiddenStatus");
		logger.info("status*************************************=="+status);
		if(status.equals("F")){
			approval.setOutAppStatus("P");
			status = "P";
		}else{
			approval.setOutAppStatus(status);
		}
		
		model.collect(approval, status);
		if(approval.getOutAppStatus().equals("P") || approval.getOutAppStatus().equals(" ")){
			logger.info("pending-------------");
			approval.setApprflag("true");
		}else if(approval.getOutAppStatus().equals("A") || approval.getOutAppStatus().equals("R")){
			logger.info("approved-------------");
			approval.setApprflag("false");
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String save() throws Exception{
		boolean result 			= false;
		int j					= 0;
		
		String appStatus		= approval.getOutAppStatus();
		String suggcode[]		= request.getParameterValues("suggcode");
		String date[]			= request.getParameterValues("suggDate");
	    String comments[]		= request.getParameterValues("comments");
		String checkStatus[]	= request.getParameterValues("checkStatus");
		String suggestion[]	= request.getParameterValues("suggestion");
		String empCode[]	= request.getParameterValues("ecode");
		String level[]	= request.getParameterValues("level");
		
		SuggestionApprovalModel model=new SuggestionApprovalModel();
		model.initiate(context,session);
		logger.info("appStatus---------"+appStatus);
		
		
		//logger.info("chcheckStatus.length---------"+checkStatus[0]);
		logger.info("outvCode length=="+level.length);
		for(int i=0;i<checkStatus.length; i++){
		
			
			if(!(checkStatus[i].equals("P"))){
				result=model.forward(approval,checkStatus[i],suggcode[i],comments[i]);
				Object [][]empFlow=null; 
				if(checkStatus[i].equals("A")){
					empFlow = generateEmpFlow(empCode[i],"Sugg",Integer.parseInt(level[i])+1);
					//logger.info("empflow--------"+empFlow+"  length "+empFlow.length);
					result = model.changeApplStatus(approval, empFlow,String.valueOf(suggcode[i]),String.valueOf(empCode[i]),request);
					j=1;
				}
					//-----processManager alert--------
					//if(result && j==1){
				/*
						String applnID = String.valueOf(suggcode[i]);
						String module = "Suggestion";
					  String applicant = "", oldApprover = "", newApprover = "";
						
						
							 applicant = String.valueOf(empCode[i]);
							logger.info("applicant"+applicant);
							oldApprover = approval.getUserEmpId();
							logger.info("applicant"+oldApprover);
							newApprover = String.valueOf(empFlow[0][0]);
							logger.info("applicant"+oldApprover);
						
						
						String alertLevel = String.valueOf(Integer.parseInt(level[i]) + 1);	
						*/				
						//sendApprovalAlert(applnID, module, applicant, oldApprover, newApprover, alertLevel);
				}
				
			//	model.forward(approval, checkStatus[i], outvCode[i], outvCode[i]);
			}
		//}
		if(result && j==1){
			  addActionMessage(getText("addMessage", ""));
	
			  
			  
			  
			  
		  }
		
		
		else{
			  addActionMessage(getText("addMessage", ""));
			  //addActionMessage("Records status change..!");
		  }
		//model.saveValue (approval,reqCode,checkStatus);
		
		model.add(approval);
		model.terminate();
		return SUCCESS;
	}
	

	
	public SuggestionApproval getApproval() {
		return approval;
	}

	public void setApproval(SuggestionApproval approval) {
		this.approval = approval;
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
			/*
				template.setEmailTemplate("SUGGESTION APPL-APPROVER TO APPROVER");
				
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
				*/
				try {
					template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				//	template.sendApplicationMail();
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
				
				/*
				template1.setEmailTemplate("SUGGESTION APPL-APPROVER1 TO APPLICANT");
				
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
				*/
				try {
					template1.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					//template1.sendApplicationMail();
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
				
				/*
				template.setEmailTemplate("SUGGESTION APPL-APPROVER2 TO APPLICANT");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
				
				template.configMailAlert();
				*/
				try {
					template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				//	template.sendApplicationMail();
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
