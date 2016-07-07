package org.struts.action.voucher;

import org.paradyne.bean.voucher.VoucherApprovalMaster;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.voucher.VoucherApprovalModel;
import org.struts.lib.ParaActionSupport;

public class VoucherApprovalMasterAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VoucherApprovalMasterAction.class);
	VoucherApprovalMaster vouchApprov;

	public void setVouchApprov(VoucherApprovalMaster vouchApprov) {
		this.vouchApprov = vouchApprov;
	}

	public VoucherApprovalMaster getVouchApprov() {
		return vouchApprov;
	}

	public Object getModel() {
		return vouchApprov;
	}

	public void prepare_local() throws Exception {
		vouchApprov = new VoucherApprovalMaster();
		vouchApprov.setMenuCode(373);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		VoucherApprovalModel model = new VoucherApprovalModel();
		model.initiate(context, session);
		model.collect(vouchApprov, "P", request);
		vouchApprov.setPen("true");
		vouchApprov.setStatus("P");
		model.terminate();
	}
	
	public String input() {
		VoucherApprovalModel model = new VoucherApprovalModel();
		model.initiate(context, session);
		model.collect(vouchApprov, "P", request);
		vouchApprov.setPen("true");
		vouchApprov.setStatus("P");
		model.terminate();
		return "success";
	}

	public String callstatus() {		
		try {
			VoucherApprovalModel model = new VoucherApprovalModel();
			model.initiate(context, session);
			String s = vouchApprov.getStatus();
			String stat = "";
			//vouchApprov.setStatus(s);
			String status = request.getParameter("status"); // empReqAppr.getStatus();
			vouchApprov.setStatus(status);
			if (status.equals("F")) {
				status = "P";
			}
			if (status.equals("P")) {
				vouchApprov.setPen("true");
				stat = "Pending List";
			} else if (status.equals("A")) {
				vouchApprov.setApp("true");
				stat = "Approved List";
			} else if (status.equals("R")) {
				vouchApprov.setRej("true");
				stat = "Rejected List";
			} else if (status.equals("H")) {
				vouchApprov.setHol("true");
				stat = "On-HOld List";
			}
			if (!status.equals("P")) {
				vouchApprov.setApprflag("true");
			}
			request.setAttribute("stat", stat);
			model.collect(vouchApprov, status, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String callstatusFromAppl() {
		VoucherApprovalModel model = new VoucherApprovalModel();
		model.initiate(context, session);
		String stat = request.getParameter("hiddenStatus");
		if (stat.equals("F")) {
			stat = "P";
		}
		if (!(stat.equals("P"))) {
			vouchApprov.setApprflag("true");
		}
		if (stat.equals("P")) {
			vouchApprov.setPen("true");
		} else if (stat.equals("A")) {
			vouchApprov.setApp("true");
		} else if (stat.equals("R")) {
			vouchApprov.setRej("true");
		} else if (stat.equals("H")) {
			vouchApprov.setHol("true");
		}
		vouchApprov.setStatus(stat);
		model.collect(vouchApprov, stat, request);
		model.terminate();
		return "success";
	}

	public String reset() {
		vouchApprov.setApp("");
		vouchApprov.setApprflag("");
		vouchApprov.setCheckStatus("");
		vouchApprov.setDate("");
		vouchApprov.setEmpCode("");
		vouchApprov.setEmpName("");
		vouchApprov.setHol("");
		vouchApprov.setLevel("");
		vouchApprov.setListLength("0");
		vouchApprov.setPen("");
		vouchApprov.setRej("");
		vouchApprov.setStatus("");
		vouchApprov.setTotalAmt("");
		vouchApprov.setVoucherNo("");
		return SUCCESS;
	}
	
	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String applnDate, String alertLevel, String applStatus,
			String[] keepInfo) {
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID, module);
			String empID = "", msgType = "";

			empID = applicant;
			msgType = "I";
			System.out.println("===APPLICANT===" + applicant);
			System.out.println("===APPL Id===" + applnID);
			System.out.println("===APPROVER===" + newApprover);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template.setEmailTemplate("CASH VOUCHER -APPROVER REJECT");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			//templateQuery1.setParameter(1, oldApprover);
			templateQuery1.setParameter(1, newApprover);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, applicant);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			//templateQuery4.setParameter(1, oldApprover);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			template.configMailAlert();
			try {
				template.sendProcessManagerAlert(empID, module, msgType,
						applnID, alertLevel);
				if (applStatus.equals("approved")) {
					if (keepInfo != null) {
						template.sendApplicationMailToKeepInfo(keepInfo);
					}
				}
				template.sendApplicationMail();
			} catch (Exception e) {
				logger.error(e);
			}
			template.clearParameters();
			template.terminate();

			if (!newApprover.equals("")) {
				//send alert from oldApprover to newApprover

				System.out.println("========NEW APPROVER=======NOT==========");
				empID = newApprover;
				msgType = "A";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1
						.setEmailTemplate("CASH VOUCHER -APPROVER1 TO APPROVER2");

				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery111 = template1
						.getTemplateQuery(1); //FROM EMAIL
				templateQuery111.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery22 = template1
						.getTemplateQuery(2); //TO EMAIL
				templateQuery22.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery33 = template1
						.getTemplateQuery(3);
				templateQuery33.setParameter(1, applnID); // application details

				EmailTemplateQuery templateQuery44 = template1
						.getTemplateQuery(4);
				templateQuery44.setParameter(1, oldApprover); // current approver details

				EmailTemplateQuery templateQuery55 = template1
						.getTemplateQuery(5);
				templateQuery55.setParameter(1, newApprover); // next approver details

				template1.configMailAlert();
				try {
					template1.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					if (keepInfo != null) {
						template1.sendApplicationMailToKeepInfo(keepInfo);
					}
					template1.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template1.clearParameters();
				template1.terminate();

				//send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template2 = new EmailTemplateBody();
				template2.initiate(context, session);

				template2
						.setEmailTemplate("CASH VOUCHER - APPROVER TO APPLICANT");

				template2.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template2
						.getTemplateQuery(1); //FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery12 = template2
						.getTemplateQuery(2); //TO EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template2
						.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template2
						.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery15 = template2
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, newApprover);

				template2.configMailAlert();

				try {
					template2.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					if (keepInfo != null) {
						for (int i = 0; i < keepInfo.length; i++) {
							System.out
									.println("===========IN KEEP INFO LOOP============="
											+ keepInfo[i]);
						}

						template2.sendApplicationMailToKeepInfo(keepInfo);
					}
					template2.sendApplicationMail();
				} catch (Exception e) {
					e.printStackTrace();
				}
				template2.clearParameters();
				template2.terminate();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	//Anantha lakshmi start
	public String back() {
		try {
			VoucherApprovalModel model = new VoucherApprovalModel();
			vouchApprov.setEnableAll("Y");
			model.initiate(context, session);
			String status = vouchApprov.getHiddenStatus();
			if (status.equals("P"))
				model.collect(vouchApprov, "P", request);
			if (status.equals("A"))
				model.collect(vouchApprov, "A", request);
			if (status.equals("R"))
				model.collect(vouchApprov, "R", request);

			if (status.equals("P"))
				status = "Pending List";
			if (status.equals("A"))
				status = "Approved List";
			if (status.equals("R"))
				status = "Rejected List";

			request.setAttribute("stat", status);
			vouchApprov.setPen("true");
			model.terminate();
			//input();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	//Anantha lakshmi end
}