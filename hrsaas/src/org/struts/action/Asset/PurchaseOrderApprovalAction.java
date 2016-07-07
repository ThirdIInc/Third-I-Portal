/**
 * 
 */
package org.struts.action.Asset;

import org.paradyne.bean.Asset.PurchaseOrderApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.Asset.AssetApprovalModel;
import org.paradyne.model.Asset.PurchaseApprovalModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * 
 */
public class PurchaseOrderApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	PurchaseOrderApproval purchaseApproval;

	public void prepare_local() throws Exception {
		purchaseApproval = new PurchaseOrderApproval();
		purchaseApproval.setMenuCode(656);

	}

	public Object getModel() {

		return purchaseApproval;
	}

	public PurchaseOrderApproval getPurchaseApproval() {
		return purchaseApproval;
	}

	public void setPurchaseApproval(PurchaseOrderApproval purchaseApproval) {
		this.purchaseApproval = purchaseApproval;
	}

	public String input() {
		try {
			PurchaseApprovalModel model = new PurchaseApprovalModel();
			model.initiate(context, session);
			String stat = "";
			model.getRecords(purchaseApproval, "P", request);
			stat = "Pending List";
			// ltcClaimApp.setApprflag("false");
			request.setAttribute("stat", stat);
			logger.info("inside prepare_withlogin getApprflag=="
					+ purchaseApproval.getApprflag());
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return INPUT;
	}

	public String checkData() throws Exception {
		try {
			PurchaseApprovalModel model = new PurchaseApprovalModel();
			model.initiate(context, session);
			String status = request.getParameter("status"); // empReqAppr.getStatus();
			logger
					.info("statussssssssssssssssssssssssssssssssssssssssssssssssssssssss"
							+ status);
			status = String.valueOf(status.charAt(0));
			String stat = "";
			if (status.equals("F"))
				status = "P";
			model.getRecords(purchaseApproval, status, request);
			if (status.equals("P"))
				stat = "Pending List";
			else if (status.equals("A"))
				stat = "Approved List";
			else if (status.equals("R"))
				stat = "Rejected List";
			if (!(status.equals("P"))) {
				purchaseApproval.setApprflag("true");

			}
			request.setAttribute("stat", stat);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/*public String updatesatus() {
		PurchaseApprovalModel model = new PurchaseApprovalModel();
		model.initiate(context, session);
		String msg = model.updateApprovalStatusRecord(purchaseApproval);
		if (msg.equals("not update"))
			addActionMessage("Record can't be updated.");
		else {
			addActionMessage("Record " + msg + " Successfully");
			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(purchaseApproval
					.getHiddenPurchaseCode());
			System.out.println("the applnId :----------------" + applnID);

			String module = "Purchase";

			String applicant = "", approver = "";
			try {
				applicant = String.valueOf(purchaseApproval.getEmpCode());
				System.out.println("The approver : applicant ************** : "
						+ applicant);
				approver = purchaseApproval.getUserEmpId();

			} catch (Exception e) {
				logger.error(e);
			}
			String alertLevel = "1";
			sendMailToApplicant(applnID, module, applicant, msg);
			// ------------------------Process Manager
			// Alert------------------------end

		}

		String stat = "";
		model.getRecords(purchaseApproval, "P", request);
		stat = "Pending List";
		// ltcClaimApp.setApprflag("false");
		request.setAttribute("stat", stat);
		model.terminate();

		return INPUT;
	}*/

	public String callApprove() {
		try {
			String result = "No";
			PurchaseApprovalModel approval_model = new PurchaseApprovalModel();
			approval_model.initiate(context, session);

			Object[][] empFlow = null;
			System.out.println("The status : =============== : "
					+ purchaseApproval.getHiddenStatus());
			result = approval_model.approverecord(purchaseApproval
					.getHiddenStatus(), purchaseApproval.getAppcomment(),
					purchaseApproval.getEmpCode(), purchaseApproval
							.getPurchaseCode(),
					purchaseApproval.getUserEmpId(), request, purchaseApproval
							.getApplicationLevel());

			if (result.equals("rejected")) {
				addActionMessage("Application rejected successfully");
			} else if (result.equals("sendback")) {
				addActionMessage("Application sent back successfully");
			} else {
				addActionMessage("Application approved successfully");
				empFlow = generateEmpFlow(purchaseApproval.getEmpCode(),
						"Purchase", purchaseApproval.getApplicationLevel() + 1);
			}

			String applnStatus = "";

			applnStatus = result;
			System.out.println("applnStatus=============" + applnStatus);
			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(purchaseApproval.getPurchaseCode());
			String module = "Purchase Order";

			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = String.valueOf(purchaseApproval.getEmpCode());
				oldApprover = purchaseApproval.getUserEmpId();
				if (empFlow != null)
					newApprover = String.valueOf(empFlow[0][0]);

				System.out.println("new Approver : ==========" + newApprover);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}

			String alertLevel = "";
			//String alertLevel = String.valueOf(purchaseApproval.getApplicationLevel());

			alertLevel = String
					.valueOf(purchaseApproval.getApplicationLevel());

			/*sendApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, alertLevel, applnStatus, empFlow);*/
			// ------------------------Process Manager
			// Alert------------------------end
			// sendMailToApplicant(applnID, module, applicant,
			// applnStatus,oldApprover,newApprover);

			approval_model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			return input();

		}
		return "mymessages";
		//return input();
	}

	private void sendMailToApplicant(String applnID, String module,
			String applicant, String msg) {

		try {
			String empID = applicant;
			String msgType = "I";
			String alertLevel = "1";
			EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);
			template1.setEmailTemplate("PURCHASE - APPROVER TO APPLICANT");
			template1.getTemplateQueries();
			EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery11.setParameter(1, purchaseApproval.getUserEmpId());
			EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery12.setParameter(1, applicant);
			EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
			templateQuery13.setParameter(1, applnID);
			EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
			templateQuery14.setParameter(1, purchaseApproval.getUserEmpId());
			EmailTemplateQuery templateQuery15 = template1.getTemplateQuery(5);
			templateQuery15.setParameter(1, purchaseApproval.getUserEmpId());
			EmailTemplateQuery templateQuery16 = template1.getTemplateQuery(6);
			templateQuery16.setParameter(1, applnID);
			EmailTemplateQuery templateQuery17 = template1.getTemplateQuery(7);
			templateQuery17.setParameter(1, applnID);
			EmailTemplateQuery templateQuery18 = template1.getTemplateQuery(8);
			templateQuery18.setParameter(1, applnID);
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
			// TODO Auto-generated method stub
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String save() {
		logger.info("in saveApproval method");

		boolean result = false;

		try {
			PurchaseApprovalModel model = new PurchaseApprovalModel();
			model.initiate(context, session);
			String appStatus = purchaseApproval.getStatus();
			String[] purchaseCode = request.getParameterValues("purchaseCode");
			String[] empCode = request.getParameterValues("empID");
			String[] level = request.getParameterValues("level");
			String[] status = request.getParameterValues("checkStatus");
			String[] comments = request.getParameterValues("comment");
			logger.info("appStatus---------" + appStatus);
			String applnStatus = "";
			Object[][] empFlow = null;
			for (int i = 0; i < status.length; i++) {
				logger.info("purchaseCode---------" + purchaseCode[i]);
				logger.info("empCode---------" + empCode[i]);
				logger.info("level---------" + level[i]);
				logger.info("status---------" + status[i]);

				if (!(status[i].equals("P"))) {
					if (status[i].equals("A")) {
						empFlow = generateEmpFlow(empCode[i], "Purchase",
								Integer.parseInt(level[i]) + 1);
						logger.info(" length " + purchaseCode[i] + "level===="
								+ level[i]);
						applnStatus = model.changeApplStatus(purchaseApproval,
								empFlow, String.valueOf(purchaseCode[i]),
								empCode[i], request);
						result = true;
					}
					applnStatus = model.forward(purchaseApproval, status[i],
							purchaseCode[i], empCode[i], comments[i], request);
					result = true;

					System.out
							.println("The application Status : ================== : "
									+ applnStatus);
					// ------------------------Process Manager
					// Alert------------------------start
					String applnID = String.valueOf(purchaseCode[i]);
					String module = "Purchase";

					String applicant = "", oldApprover = "", newApprover = "";
					try {
						applicant = String.valueOf(empCode[i]);
						oldApprover = purchaseApproval.getUserEmpId();
						newApprover = String.valueOf(empFlow[0][0]);
					} catch (Exception e) {
						logger.error(e);
					}
					/*String alertLevel = String.valueOf(Integer
							.parseInt(level[i]) + 1);*/
					/*sendApprovalAlert(applnID, module, applicant, oldApprover,
							newApprover, alertLevel, applnStatus);*/
					// ------------------------Process Manager
					// Alert------------------------end
				}
			}
			if (result) {
				addActionMessage(getText("addMessage", ""));
			} else {
				addActionMessage("Record status can not be changed !");
			}
			// model.saveValue (approval,reqCode,checkStatus);
			// model.getRecords(purchaseApproval, appStatus);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}

	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel, String applStatus, Object[][] empFlow) {
		try {
			/*ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);*/
			PurchaseApprovalModel approval_model = new PurchaseApprovalModel();
			approval_model.initiate(context, session);

			/*	processAlerts.removeProcessAlert(applnID, module);*/

			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID, module);

			String empID = "", msgType = "";
			String keepInfo = approval_model.getKeepInfoIdList(applnID);

			String actualStataus = "";
			
			System.out.println("applStatus-------------------------------------------------"+applStatus);

			if (applStatus.equals("approved")) {
				actualStataus = "Approved";
				applStatus="A";
			}
			if (applStatus.equals("rejected")) {
				actualStataus = "Rejected";
				applStatus="R";
			}

			if (applStatus.equals("sendback")) {
				actualStataus = "Sent back";
				applStatus="B";
			}

			System.out.println("applicant -------------------------------------------------- " + applicant);

			System.out.println("alertLevel --------------------------------------- " + alertLevel);
			
			
			 alertLevel = String.valueOf(Integer.parseInt(alertLevel));

			if (Integer.parseInt(alertLevel) == 1) {
				empFlow = generateEmpFlow(applicant, "Purchase", Integer
						.parseInt(alertLevel));
			} else {
				empFlow = generateEmpFlow(applicant, "Purchase", Integer
						.parseInt(alertLevel));
			}
			if (empFlow != null) {
				for (int i = 0; i < empFlow.length; i++) {
					for (int j = 0; j < empFlow[0].length; j++) {
						System.out.println("empFlow[i][j]-------------------"
								+ empFlow[i][j]);
					}
				}
			}

			if (!newApprover.equals("")) {
				// send alert from oldApprover to newApprover
				empID = newApprover;
				msgType = "A";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("PURCHASE - APPROVER1 TO APPROVER2");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID); // application
				// details

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover); // current
				// approver
				// details

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, newApprover); // next approver
				// details

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applnID);

				template.configMailAlert();

				String[] link_parameter = new String[3];
				String[] link_label = new String[3];
				// String applicationType = "TYD";
				String applicationType = "PurchaseOrderAppl";
				link_parameter[0] = applicationType + "#" + newApprover
						+ "#applicationDtls#";

				String link = "/asset/PurchaseOrder_callByApprover.action?hiddenPurchaseCode="
						+ applnID + "$status=P$applicationLevel=" + alertLevel;
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_label[0] = "here";
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template.addOnlineLink(request, link_parameter, link_label);

				try {
					String alertlink = "/asset/PurchaseOrder_callByApprover.action";
					String linkParam = "hiddenPurchaseCode=" + applnID
							+ "&level=" + alertLevel;

					template.sendProcessManagerAlert(newApprover, module,
							msgType, applnID, alertLevel, linkParam, alertlink,
							actualStataus, applicant, "", "", "", oldApprover);

				} catch (Exception e) {
					logger.error(e);
				}

				/*	
					
					try {
						template.sendProcessManagerAlert(empID, module, msgType,
								applnID, alertLevel);
						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
				 */
				template.clearParameters();
				template.terminate();

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1.setEmailTemplate("PURCHASE - APPROVER TO APPLICANT");

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

				EmailTemplateQuery templateQuery15 = template1
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery16 = template1
						.getTemplateQuery(6);
				templateQuery16.setParameter(1, applnID);

				EmailTemplateQuery templateQuery17 = template1
						.getTemplateQuery(7);
				templateQuery17.setParameter(1, applnID);

				EmailTemplateQuery templateQuery18 = template1
						.getTemplateQuery(8);
				templateQuery18.setParameter(1, applnID);

				template1.configMailAlert();

				try {

					String alertlink = "/asset/PurchaseOrder_callforedit.action";

					String linkParam = "applicationCode=" + applnID
							+ "&applStatus=P";

					if (keepInfo != null && keepInfo.length() > 0) {
						keepInfo = keepInfo;

					} else {
						keepInfo = "";
					}

					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					oldApprover = String.valueOf(empFlow[0][0]);

					template1.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, alertLevel, linkParam, alertlink,
							actualStataus, applicant, alternateApprover,
							keepInfo, applicant, oldApprover);

				} catch (Exception e) {
					logger.error(e);
				}

				try {
					template1.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template1.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				try {
					template1.sendApplicationMailToKeepInfo(keepInfo);
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
						.setEmailTemplate("PURCHASE -FINAL APPROVER TO APPLICANT");

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

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);

				template.configMailAlert();
				
				
				try {
					
					String alertlink = "/asset/PurchaseOrder_callforedit.action";
					String linkParam = "applicationCode=" + applnID
							+ "&applStatus=" + applStatus;
					
				 
					System.out
							.println("alertLevel-----------------------------------"
									+ alertLevel);
					
					if(alertLevel.equals("1"))
					{
						empFlow = generateEmpFlow(applicant, "Purchase", Integer
								.parseInt(alertLevel) );
					}
					else{
						empFlow = generateEmpFlow(applicant, "Purchase", Integer
								.parseInt(alertLevel) - 1);
					}
					
					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					System.out
							.println("alternateApprover---------------------------"
									+ alternateApprover);
				 
					if (keepInfo != null && keepInfo.length() > 0) {
						keepInfo = keepInfo;

					} else {
						keepInfo = "";
					}
					 

					if (applStatus.equals("B")) {
						actualStataus = "SentBack";
						template
								.sendProcessManagerAlert("", module, "A",
										applnID, alertLevel, linkParam,
										alertlink, actualStataus, applicant, "",
										"", applicant,oldApprover);

						template.sendProcessManagerAlert(oldApprover,
								module, "I", applnID, alertLevel,
								linkParam, alertlink, actualStataus, applicant,
								alternateApprover, keepInfo, "",oldApprover);

					} else {

						System.out
								.println("oldApprover------------------------------------------------"
										+ oldApprover);
						template.sendProcessManagerAlert(oldApprover,
								module, "I", applnID, alertLevel,
								linkParam, alertlink, actualStataus, applicant,
								alternateApprover, keepInfo, applicant,oldApprover);
					}

				} catch (Exception e) {
					logger.error(e);

					e.printStackTrace();
				}
			 	/*try {
					template.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}*/
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				if (applStatus.equals("approved")) {
					template
							.setEmailTemplate("PURCHASE -FINAL APPROVAL TO INWARD");

					module = "Purchase Inward";
					msgType = "A";
					template.getTemplateQueries();
					String assignerCode = getAssignerCode(applicant);
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery10.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery11.setParameter(1, assignerCode);

					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(3);
					templateQuery12.setParameter(1, applnID);

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(4);
					templateQuery8.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(5);
					templateQuery9.setParameter(1, assignerCode);

					EmailTemplateQuery templateQuery13 = template
							.getTemplateQuery(6);
					templateQuery13.setParameter(1, applnID);

					EmailTemplateQuery templateQuery14 = template
							.getTemplateQuery(7);
					templateQuery14.setParameter(1, applnID);

					template.configMailAlert();
					/*	try {
							template.sendProcessManagerAlert(assignerCode, module,
									msgType, applnID, alertLevel);
							template.sendApplicationMail();
						} catch (Exception e) {
							logger.error(e);
						}*/
					try {
						template.sendApplicationMailToKeepInfo(keepInfo);
					} catch (Exception e) {
						logger.error(e);
					}

					template.clearParameters();

				}

				template.terminate();
			}
			approval_model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String getAssignerCode(String empCode) {
		String assignerCode = "";

		PurchaseApprovalModel model = new PurchaseApprovalModel();
		model.initiate(context, session);
		assignerCode = model.getAssignerCode(empCode);
		model.terminate();

		return assignerCode;
	}
}
