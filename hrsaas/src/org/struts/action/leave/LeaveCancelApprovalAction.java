package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveCancelApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.leave.LeaveCancelApprovalModel;
import org.struts.lib.ParaActionSupport;

public class LeaveCancelApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LeaveCancelApprovalAction.class);

	LeaveCancelApproval leaveCancel;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leaveCancel = new LeaveCancelApproval();
		leaveCancel.setMenuCode(694);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return leaveCancel;
	}

	public LeaveCancelApproval getLeaveCancel() {
		return leaveCancel;
	}

	public void setLeaveCancel(LeaveCancelApproval leaveCancel) {
		this.leaveCancel = leaveCancel;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		LeaveCancelApprovalModel model = new LeaveCancelApprovalModel();
		model.initiate(context, session);
		model.generateListForCancel(leaveCancel, request);
		model.terminate();
	}

	public String callPage1() throws Exception {
		LeaveCancelApprovalModel model = new LeaveCancelApprovalModel();
		model.initiate(context, session);
		model.generateListForCancel(leaveCancel, request);
		model.terminate();
		return SUCCESS;
	}

	public String callPage2() throws Exception {
		LeaveCancelApprovalModel model = new LeaveCancelApprovalModel();
		model.initiate(context, session);
		model.generateListForCancel(leaveCancel, request);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR LEAVE APPLICATION CANCELLATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelForm() throws Exception {
		LeaveCancelApprovalModel model = new LeaveCancelApprovalModel();
		model.initiate(context, session);

		String leaveAppCode[] = request.getParameterValues("hdeleteCode");
		String[] date = request.getParameterValues("applicationDate");// date
		String[] empIds = request.getParameterValues("empCode");// employee
		String[] confChk = request.getParameterValues("confChk");// employee

		Object[][] empFlow = null;
		String[] repOff = new String[leaveAppCode.length];
		for(int i = 0; i < leaveAppCode.length; i++) {
			empFlow = generateEmpFlow(empIds[i], "Leave", 1);
			if(empFlow != null && empFlow.length > 0) {
				repOff[i] = String.valueOf(empFlow[0][0]);
			}// end of if
			else {
				repOff[i] = "";
			}// end of else
		}// end of for

		if(leaveAppCode != null) {
			boolean result = model.cancelApplication(leaveAppCode, leaveCancel, repOff, empIds);
			if(result) {
				// --------Email Templates + Process Manager Alert--------------start
				String module = "Leave Cancellation";
				String applicant = "", oldApprover = "", newApprover = "";
				String applStatus = "approved";

				for(int i = 0; i < leaveAppCode.length; i++) {

					try {
						String apprQuery = "  SELECT APPROVED_BY, LEAVE_LEVEL,LEAVE_APPL_CODE FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE= " + leaveAppCode[i];
						Object[][] apprObj = model.getSqlModel().getSingleResult(apprQuery);
						if(String.valueOf(leaveAppCode[i]).equals(String.valueOf(apprObj[0][2]))) {
							ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
							processAlerts.initiate(context, session);
							processAlerts.removeProcessAlert(String.valueOf(leaveAppCode[i]), module);
							try {
								newApprover = String.valueOf(apprObj[0][0]);
							} catch(Exception e) {
								logger.error(e);
							}
							String applnID = String.valueOf(leaveAppCode[i]);
							String applnDate = String.valueOf(date[i]);
							String alertLevel = String.valueOf(apprObj[0][1]);

							String empID = "", msgType = "";
							applicant = empIds[i];
							oldApprover = leaveCancel.getUserEmpId();
							
							// send alert from oldApprover to applicant
							empID = applicant;
							msgType = "I";

							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);

							template.setEmailTemplate("LEAVE CNCL-APPROVER TO APPLICANT");

							template.getTemplateQueries();

							EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
							templateQuery1.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
							templateQuery2.setParameter(1, applicant);

							EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
							templateQuery3.setParameter(1, applnDate);
							templateQuery3.setParameter(2, applStatus);
							templateQuery3.setParameter(3, applicant);

							EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
							templateQuery4.setParameter(1, oldApprover);

							template.configMailAlert();
							try {
								template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
							} catch(Exception e) {
								logger.error(e);
							}
							template.sendApplicationMail();
							template.clearParameters();
							template.terminate();
						}
					} catch(Exception e) {
						logger.error(e);
					}
					// --------Email Templates + Process Manager Alert--------------end
				}// end of for loop
				addActionMessage("Leave cancellation approved successfully!");
			} else {
				addActionMessage("Leave cancellation can not be approved!");
			}// end of else
		}// end of if
		model.generateListForCancel(leaveCancel, request);
		model.terminate();
		return "success";
	} // end of cancelForm
}// end of class
