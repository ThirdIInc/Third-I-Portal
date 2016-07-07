package org.struts.action.attendance;

import org.paradyne.bean.attendance.OutdoorApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.attendance.OutdoorApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ritac & saipavan v 04-06-2008
 */

public class OutdoorApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OutdoorApprovalAction.class);
	OutdoorApproval approval;

	public void prepare_local() throws Exception {
		approval = new OutdoorApproval();
		approval.setMenuCode(404);
	}

	public Object getModel() {
		return approval;
	}

	public OutdoorApproval getApproval() {
		return approval;
	}

	public void setApproval(OutdoorApproval approval) {
		this.approval = approval;
	}

	public String input() {
		try {
			OutdoorApprovalModel model = new OutdoorApprovalModel();
			model.initiate(context, session);

			model.add(approval, "P");
			approval.setApprflag("false");
			request.setAttribute("stat", "Pending List");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return INPUT;
	}

	public String callstatusFromAppl() { // retrieving application details
		OutdoorApprovalModel model = new OutdoorApprovalModel();
		model.initiate(context, session);

		String status = request.getParameter("hiddenStatus");
		if (status.equals("F")) {
			approval.setOutAppStatus("P");
			status = "P";
		} else {
			approval.setOutAppStatus(status);
		}

		model.collect(approval, status);

		if (approval.getOutAppStatus().equals("P")
				|| approval.getOutAppStatus().equals(" ")) {
			approval.setApprflag("true");
		} else if (approval.getOutAppStatus().equals("A")
				|| approval.getOutAppStatus().equals("R")) {
			approval.setApprflag("false");
		}
		model.terminate();
		return SUCCESS;
	}

	public String ckeckdata() throws Exception {
		// retrieving data based upon user choice (pending or approved or
		// reject)
		OutdoorApprovalModel model = new OutdoorApprovalModel();
		model.initiate(context, session);

		String status = request.getParameter("status");
		approval.setOutAppStatus(status);

		String stat = "";
		String statusToAppend = "";
		if (status.equals("P") || status.equals(" ")) {
			stat = "Pending List";
			approval.setApprflag("false");
			statusToAppend = "P";
		} else if (status.equals("A")) {
			stat = "Approved List";
			approval.setApprflag("true");
			statusToAppend = "A";
		} else if (status.equals("R")) {
			stat = "Rejected List";
			approval.setApprflag("true");
			statusToAppend = "R";
		} else if (status.equals("H")) {
			stat = "On Hold List";
			approval.setApprflag("true");
		}
		request.setAttribute("stat", stat);
		model.add(approval, statusToAppend); // setting application details.
		model.terminate();
		return SUCCESS;
	}

	public String save() throws Exception { // saving outdoor approval
		// application details.
		boolean result = false;
		int j = 0;

		String outvCode[] = request.getParameterValues("outVisitcode");
		String outLoc[] = request.getParameterValues("outLoc");
		String purpose[] = request.getParameterValues("purpose");
		String checkStatus[] = request.getParameterValues("checkStatus");
		String empCode[] = request.getParameterValues("ecode");
		String level[] = request.getParameterValues("level");

		OutdoorApprovalModel model = new OutdoorApprovalModel();
		model.initiate(context, session);

		for (int i = 0; i < checkStatus.length; i++) {
			Object[][] empFlow = null;
			if (!(checkStatus[i].equals("P"))) { // checking status is
				// pending...!
				result = model.forward(approval, checkStatus[i], outvCode[i],
						purpose[i], outLoc[i]);
				if (checkStatus[i].equals("A")) {
					empFlow = generateEmpFlow(empCode[i], "Outdoor", Integer
							.parseInt(level[i]) + 1);
					result = model.changeApplStatus(approval, empFlow, String
							.valueOf(outvCode[i]), String.valueOf(empCode[i]));

				}
				j = 1;
				// ------------------------Process Manager
				// Alert------------------------start
				String applnID = String.valueOf(outvCode[i]);
				String module = "Outdoor Visit";

				String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = String.valueOf(empCode[i]);
					oldApprover = approval.getUserEmpId();
					newApprover = String.valueOf(empFlow[0][0]);
				} catch (Exception e) {
					logger.error(e);
				}
				String alertLevel = String
						.valueOf(Integer.parseInt(level[i]) + 1);
				sendApprovalAlert(applnID, module, applicant, oldApprover,
						newApprover, alertLevel);
				// ------------------------Process Manager
				// Alert------------------------end

			}// end of if statement
		}// end of for loop
		if (result && j == 1) {
			addActionMessage(getMessage("save"));
		} else {
			addActionMessage(getMessage("save.error"));
		}
		model.add(approval, "P");
		model.terminate();
		return SUCCESS;
	}

	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel) {
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(applnID, module);

			String empID = "", msgType = "";

			if (!newApprover.equals("")) {
				// send alert from oldApprover to newApprover
				empID = newApprover;
				msgType = "A";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("OUTDOOR APPL-APPROVER TO APPROVER");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);

				template.configMailAlert();
				try {
					template.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1
						.setEmailTemplate("OUTDOOR APPL-APPROVER1 TO APPLICANT");

				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery12 = template1
						.getTemplateQuery(2); // TO EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template1
						.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template1
						.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);

				template1.configMailAlert();
				try {
					template1.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template1.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template1.clearParameters();
				template1.terminate();
			} else {
				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template
						.setEmailTemplate("OUTDOOR APPL-APPROVER2 TO APPLICANT");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				template.configMailAlert();
				OutdoorApprovalModel model = new OutdoorApprovalModel();
				model.initiate(context, session);
				String keepInfoQuery = "SELECT NVL(APPL_KEEP_INFO,' ') FROM  HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE="
						+ applnID;
				Object[][] keepInfoData = model.getSqlModel().getSingleResult(
						keepInfoQuery);
				if (keepInfoData != null && keepInfoData.length > 0) {
					String keepInfo = String.valueOf(keepInfoData[0][0]);
					try {
						template.sendApplicationMailToKeepInfo(keepInfo);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

				try {
					template.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String backToApprovalList() {
		input();
		return INPUT;
	}

	public String approveRejSendBackApplication() {
		try {
			OutdoorApprovalModel model = new OutdoorApprovalModel();
			model.initiate(context, session);
			String appStatus = model.approveRejectSendBackLevApp(request,
					approval.getEcode(), approval.getOutvCode(), approval
							.getCheckApproveRejectStatus(), approval
							.getApproverComments(), approval.getUserEmpId(),
					approval.getApplicationLevel());
			System.out.println("############### appStatus : ######### "
					+ appStatus);
			if (appStatus.equals("rejected")) {
				addActionMessage("Application rejected successfully");
			} else if (appStatus.equals("sendback")) {
				addActionMessage("Application sent back successfully");
			} else {
				addActionMessage("Application approved successfully");
			}
			model.terminate();

			/*if(appStatus.equals("forwarded")){
				Object[][] empFlow = generateEmpFlow(approval.getUserEmpId(), "Outdoor", 1);
				if(empFlow!=null && empFlow.length>0){
					sendMailToNextApprover(empFlow);
					sendMailToApplicant();
				}
			}else{
				sendMailToApplicant();
			}
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	

}