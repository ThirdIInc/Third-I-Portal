package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.RequisitionApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.recruitment.RequisitionApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author pradeep
 * @date Jan 03, 2009
 * @description RequisitionApprovalAction serves as action for Manpower
 *              Requisition Approval form to change the status of manpower
 *              requisition applications
 */
public class RequisitionApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RequisitionApprovalAction.class);
	RequisitionApproval requisitionApproval;

	public void prepare_local() throws Exception {
		requisitionApproval = new RequisitionApproval();
		requisitionApproval.setMenuCode(465);
	}

	public Object getModel() {
		return requisitionApproval;
	}

	/**
	 * @return the requisitionApproval
	 */
	public RequisitionApproval getRequisitionApproval() {
		return requisitionApproval;
	}

	public void setRequisitionApproval(RequisitionApproval requisitionApproval) {
		this.requisitionApproval = requisitionApproval;
	}

	public String input() {
		try {
			RequisitionApprovalModel model = new RequisitionApprovalModel(); 
			model.initiate(context, session); 
			/**
			 * call showRecords(requisitionApproval, status) method of
			 * RequisitionApprovalModel class to retrieve the pending
			 * application details from HRMS_REQS_HDR table
			 */
			requisitionApproval.setHoldFlag("false");
			model.showRecords(requisitionApproval, "P", request);
			model.terminate(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	 
	public String showApplicationList() throws Exception {
		String listType = "";
		try {
			System.out.println("showApplicationList ######################");
			String status = request.getParameter("status"); 
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			if (status.equals("P") || status.equals(" ")) {
				listType = "Pending List";
				requisitionApproval.setHoldFlag("false");
				requisitionApproval.setStatusIterator("Pending");
				requisitionApproval.setApprflag("false");
			} else if (status.equals("A")) {
				listType = "Approved List";
				requisitionApproval.setStatusIterator("Approved");
				requisitionApproval.setHoldFlag("false");
				requisitionApproval.setApprflag("true");
			} else if (status.equals("R")) {
				listType = "Rejected List";
				requisitionApproval.setStatusIterator("Rejected");
				requisitionApproval.setHoldFlag("false");
				requisitionApproval.setApprflag("true");
			} else if (status.equals("H")) {
				listType = "On Hold List";
				requisitionApproval.setHoldFlag("true");
				requisitionApproval.setStatusIterator("On Hold");
				requisitionApproval.setApprflag("false");
			} else if (status.equals("S")) {
				listType = "Send Back List";
				requisitionApproval.setHoldFlag("true");
				requisitionApproval.setStatusIterator("Send Back");
				requisitionApproval.setApprflag("false");
			} else if (status.equals("Q")) {
				listType = "Quick Requisition List";
				requisitionApproval.setHoldFlag("true");
				requisitionApproval.setStatusIterator("Quick Requisition");
				requisitionApproval.setApprflag("false");
			}

			request.setAttribute("listType", listType);
			/**
			 * call showRecords(requisitionApproval, status) method of
			 * RequisitionApprovalModel class to retrieve the pending
			 * application details from HRMS_REQS_HDR table
			 */
			String requisitionCODE = request.getParameter("reqCode");
			model.showRecords(requisitionApproval, status, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @method save
	 * @purpose to change the status of the selected application or to forward
	 *          the application to the next approver
	 * @return type : String
	 */
	public String save() throws Exception {
		try {
			boolean result = false;
			int j = 0;
			String[] reqCode = request.getParameterValues("reqNo"); 
			String[] reqStatus = request.getParameterValues("reqStatus"); 
			String[] level = request.getParameterValues("level");  
			String[] empCode = request.getParameterValues("empCode"); 
			String[] comments = request.getParameterValues("comment"); 
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			Object[][] empFlow = null;
			for (int i = 0; i < reqCode.length; i++) {
				if (!(String.valueOf(reqStatus[i]).equals("P"))
						&& !(String.valueOf(reqStatus[i]).equals(""))) {  
					if (String.valueOf(reqStatus[i]).equals("A")) { 
						/**
						 * call generateEmpFlow(empCode, type, level) method of
						 * ParaActionSupport class to retrieve the next approver
						 * details if any exists
						 */
						empFlow = generateEmpFlow(empCode[i], "Recruitment",
								Integer.parseInt(level[i]) + 1);
						/**
						 * call changeApplStatus(requisitionApproval, empFlow,
						 * String.valueOf(loanCode[i])) method of
						 * RequisitionApprovalModel class to change the status
						 * of the selected application in HRMS_REQS_HDR table
						 */
						result = model.changeApplStatus(requisitionApproval,
								empFlow, reqCode[i]);
						j = 1;
					} // if statement ends

					/**
					 * call forward(requisitionApproval, status[i], reqCode[i],
					 * comments[i], empCode[i]) method of
					 * RequisitionApprovalModel class to change the status of
					 * the selected application in HRMS_REQS_HDR table and to
					 * insert an entry related to approver details in
					 * HRMS_REQS_PATH table
					 */
					result = model.forward(requisitionApproval, reqStatus[i],
							reqCode[i], comments[i], empCode[i]);
					j = 1;
					String applnID = String.valueOf(reqCode[i]);
					String module = "Manpower Requisition";
					String applicant = "", oldApprover = "", newApprover = "";
					applicant = String.valueOf(empCode[i]);
					oldApprover = requisitionApproval.getUserEmpId();
					newApprover = String.valueOf(empFlow[0][0]);
					String alertLevel = String.valueOf(Integer
							.parseInt(level[i]) + 1);
					sendApprovalAlert(applnID, module, applicant, oldApprover,
							newApprover, alertLevel);
				}
			}
			if (result && j == 1) {
				addActionMessage(getText("addMessage", ""));
			} else {
				addActionMessage("Record status can not be changed !");
			}
			/**
			 * call showApplications(requisitionApproval) method of
			 * RequisitionApprovalModel class to retrieve all application
			 * details as per the selected status
			 */
			// input();
			model.showRecords(requisitionApproval, "P", request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				template.setEmailTemplate("MANPOWER REQ-APPROVER TO APPROVER");
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);

				template.configMailAlert();
				template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1.setEmailTemplate("MANPOWER REQ-APPROVER1 TO APPLICANT");
				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); // FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); // TO EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);

				template1.configMailAlert();
				template1.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				template1.sendApplicationMail();
				template1.clearParameters();
				template1.terminate();
			} else {
				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("MANPOWER REQ-APPROVER2 TO APPLICANT");
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				template.configMailAlert();
				template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method reset
	 * @purpose to reset all the form fields to blank values
	 * @return type : String
	 */
	public String reset() throws Exception {
		try {
			String data = request.getParameter("status");
			requisitionApproval.setApprflag("");
			requisitionApproval.setAppliedFor("");
			requisitionApproval.setEmpCode("");
			requisitionApproval.setLevel("");
			requisitionApproval.setStatus("");
			requisitionApproval.setSrNo("");
			requisitionApproval.setReqStatus("");
			requisitionApproval.setReqNo("");
			requisitionApproval.setHrManager("");
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			if (data == null || data.equals("null") || data.equals("")
					|| data.equals(" ")) {
				model.showRecords(requisitionApproval, "P", request);
			} else if (data.equals("P")) {
				model.showRecords(requisitionApproval, "P", request);
			} else if (data.equals("H")) {
				String listType = "On Hold List";
				request.setAttribute("listType", listType);
				model.showRecords(requisitionApproval, "H", request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Added on date:10-09-2009 following function is called from Manpower
	 * Requisition when Approve button is clicked.
	 * 
	 * @return
	 */
	public String approve() {
		try {
			Object[][] empFlow = null;
			String comments = request.getParameter("comm");
			String reqCode = request.getParameter("reqCode");
			String status = request.getParameter("status");
			
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.removeCompetencyProcessManagerAlert(String.valueOf(reqCode));
			model.initiate(context, session);
			Object[][] data = model.getData(reqCode, requisitionApproval);
			empFlow = generateEmpFlow(String.valueOf(data[0][1]), "Recruitment", Integer.parseInt(String.valueOf(data[0][0])) + 1);
			boolean result = model.saveApprovedRec(empFlow, reqCode, comments, String.valueOf(data[0][1]), requisitionApproval);
		
			if (empFlow != null && empFlow.length > 0) {
				model.sendApproverToNextApprover(requisitionApproval, reqCode, empFlow, request, String.valueOf(data[0][1]));
			}
			model.showRecords(requisitionApproval, status, request);
			if (result) {
				model.sendMail(requisitionApproval, reqCode, "A", "true");
			} else {
				model.sendForwardeMailToApplicant(requisitionApproval, String.valueOf(empFlow[0][0]), reqCode, "A");
			}
			addActionMessage("Record saved successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (requisitionApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "success";
		}
	}

	/**
	 * Added on date:10-09-2009 following function is called from Manpower
	 * Requisition form when On Hold button is clicked.
	 * 
	 * @return
	 */
	public String onHold() {
		try {
			String comments = request.getParameter("comm");
			String reqCode = request.getParameter("reqCode");
			String status = request.getParameter("status");
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			Object[][] data = model.getData(reqCode, requisitionApproval);
			model.onHoldRecord(reqCode, comments, String.valueOf(data[0][1]),
					requisitionApproval);
			model.showRecords(requisitionApproval, status, request);
			model.sendMail(requisitionApproval, reqCode, "H", "false");
			addActionMessage("Record saved successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (requisitionApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "success";
		}
	}

	/**
	 * Added on date:10-09-2009 following function is called from Manpower
	 * Requisition form when Reject button is clicked.
	 * 
	 * @return
	 */
	public String reject() {
		try {
			String comments = request.getParameter("comm");
			String reqCode = request.getParameter("reqCode");
			String status = request.getParameter("status");
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			Object[][] data = model.getData(reqCode, requisitionApproval);
			model.rejectRecord(reqCode, comments, String.valueOf(data[0][1]), requisitionApproval);
			model.showRecords(requisitionApproval, status, request);
			model.sendMail(requisitionApproval, reqCode, "R", "false");
			addActionMessage("Record saved successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (requisitionApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "success";
		}
	}

	/**
	 * Purpose - To Send Back Application.
	 */
	public String sendBackApplication() {
		try {
			String comments = request.getParameter("comm");
			String reqCode = request.getParameter("reqCode");
			String status = request.getParameter("status");
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			Object[][] data = model.getData(reqCode, requisitionApproval);
			model.sendBackRecord(reqCode, comments, String.valueOf(data[0][1]), requisitionApproval);
			model.showRecords(requisitionApproval, status, request);
			model.sendMail(requisitionApproval, reqCode, "S", "false");
			addActionMessage("Record saved successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (requisitionApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "success";
		}
	}

}
