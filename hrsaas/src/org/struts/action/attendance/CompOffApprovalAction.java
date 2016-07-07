package org.struts.action.attendance;

import org.paradyne.bean.attendance.CompOffApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.attendance.CompOffApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim date 11-08-2008
 */

public class CompOffApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CompOffApprovalAction.class);
	CompOffApproval compApp;

	/**
	 * @return a string after setting the records based on user choice..!
	 */
	public String callStatus() {
		CompOffApprovalModel model = new CompOffApprovalModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		model.callStatus(compApp, status);
		if(status.equals("P"))
			compApp.setPen("true");
		else if(status.equals("A"))
			compApp.setApp("true");
		else if(status.equals("R"))
			compApp.setRej("true");
		if(!status.equals("P")) {
			compApp.setApprflag("true");

		}
		model.terminate();
		return "success";
	}

	/**
	 * @return A string after setting Approval List
	 */
	public String callView() {
		String cmpId = request.getParameter("compViewNo");
		CompOffApprovalModel model = new CompOffApprovalModel();
		model.initiate(context, session);
		model.setApplication(compApp, cmpId);
		model.callDtl(compApp, cmpId);
		model.setApprover(compApp);
		model.terminate();

		return "viewCompOff";
	}

	public CompOffApproval getCompApp() {
		return compApp;
	}

	public Object getModel() {
		return compApp;
	}

	public void prepare_local() throws Exception {
		compApp = new CompOffApproval();
		compApp.setMenuCode(564);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		// setting employee details on load
		CompOffApprovalModel model = new CompOffApprovalModel();
		model.initiate(context, session);
		model.callStatus(compApp, "P");
		model.terminate();
	}

	/**
	 * @return String after saving the compoff details
	 */
	public String save() {
		int j = 0;

		String result = "";
		String[] compId = request.getParameterValues("compId");
		String[] compEmpId = request.getParameterValues("compEmpId");
		String[] status = request.getParameterValues("checkStatus");
		String[] level = request.getParameterValues("level");
		String[] comments = request.getParameterValues("remark");

		CompOffApprovalModel model = new CompOffApprovalModel();
		model.initiate(context, session);

		for(int i = 0; i < status.length; i++) {
			Object[][] empFlow = null;
			if(!(status[i].equals("P"))) {
				model.forward(compApp, status[i], compId[i], comments[i]);
				if(status[i].equals("A")) {
					empFlow = generateEmpFlow(compEmpId[i], "Leave", Integer.parseInt(level[i]) + 1);
					result = model.changeApplStatus(compApp, empFlow, compId[i], compEmpId[i]);
					j = 1;
				}
				
				//------------------------Process Manager Alert------------------------start
				if(result != "notSaved") {
					String applnID = String.valueOf(compId[i]);
					String module = "Compensatory Off";
					
					String applicant = "", oldApprover = "", newApprover = "";
					try {
						applicant = String.valueOf(compEmpId[i]);
						oldApprover = compApp.getUserEmpId();
						newApprover = String.valueOf(empFlow[0][0]);
					} catch(Exception e) {
						logger.error(e);
					}		
					String alertLevel = String.valueOf(Integer.parseInt(level[i]) + 1);					
					sendApprovalAlert(applnID, module, applicant, oldApprover, newApprover, alertLevel);					
				}
				//------------------------Process Manager Alert------------------------end
			}
		}
		if(result == "notComOff") {
			addActionMessage("CompOff is not configured for the attendence ! \n" + " Record Saved Successfully ");
		} else if(result == "Saved") {
			addActionMessage(getMessage("save"));
		} else if(result == "notSaved") {
			addActionMessage(getMessage("save.error"));
		}

		model.callStatus(compApp, compApp.getStatus());
		model.terminate();
		return "success";
	}
	
	public void sendApprovalAlert(String applnID, String module, String applicant, String oldApprover, String newApprover, String alertLevel) {
		try {
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
				
				template.setEmailTemplate("COMP OFF APPL-APPROVER TO APPROVER");
				
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
				
				template1.setEmailTemplate("COMP OFF APPL-APPROVER1 TO APPLICANT");
				
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
				
				template.setEmailTemplate("COMP OFF APPL-APPROVER2 TO APPLICANT");
				
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

	public void setCompApp(CompOffApproval compApp) {
		this.compApp = compApp;
	}
}