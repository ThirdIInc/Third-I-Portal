package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.paradyne.bean.D1.HardwareSoftwareRequestApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.HardwareSoftwareRequestApproverModel;
import org.struts.lib.ParaActionSupport;

public class HardwareSoftwareRequestApproverAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HardwareSoftwareRequestApproverAction.class);

	HardwareSoftwareRequestApprover hrdswApprBean;

	public void prepare_local() throws Exception {
		hrdswApprBean = new HardwareSoftwareRequestApprover();
		hrdswApprBean.setMenuCode(2001);
	}

	public Object getModel() {
		return hrdswApprBean;
	}

	public HardwareSoftwareRequestApprover getHrdswApprBean() {
		return hrdswApprBean;
	}

	public void setHrdswApprBean(HardwareSoftwareRequestApprover hrdswApprBean) {
		this.hrdswApprBean = hrdswApprBean;
	}

	public String input() {
		try {
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			String userId = hrdswApprBean.getUserEmpId();
			String applCode = hrdswApprBean.getHwSwHiddenID();
			boolean corporateProcurementHSApprover = model.iscorporateProcurementHSApprover(userId);
			if (corporateProcurementHSApprover) {
				model.getPendingListForCorpProHS(hrdswApprBean, request, userId);
				hrdswApprBean.setPendingSearchMgrListFlag(false);
			} else {
				model.getPendingList(hrdswApprBean, request, userId);
				hrdswApprBean.setPendingSearchMgrListFlag(true);
			}
			hrdswApprBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String getApprovedList() throws Exception {
		try {
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			String userId = hrdswApprBean.getUserEmpId();
			String applicationId = hrdswApprBean.getHwSwHiddenID();
			boolean corporateProcurementHSApprover = model.iscorporateProcurementHSApprover(userId);
			if (corporateProcurementHSApprover) {
				model.getApprovedListGroup(hrdswApprBean, request, userId, applicationId);
				hrdswApprBean.setPendingSearchMgrListFlag(false);
			} else {
				model.getApprovedList(hrdswApprBean, request, userId, applicationId);
				hrdswApprBean.setPendingSearchMgrListFlag(true);
			}
			hrdswApprBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String getRejectedList() throws Exception {
		try {
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			String userId = hrdswApprBean.getUserEmpId();
			boolean corporateProcurementHSApprover = model
					.iscorporateProcurementHSApprover(userId);
			if (corporateProcurementHSApprover) {
				model.getRejectedList(hrdswApprBean, request);
				hrdswApprBean.setPendingSearchMgrListFlag(false);
			} else {
				model.getRejectedList(hrdswApprBean, request, userId);
				hrdswApprBean.setPendingSearchMgrListFlag(true);
			}
			hrdswApprBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String approveApplication() {
		boolean corporateProcurementHSAvailable = false;
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);
		String applicationId = hrdswApprBean.getHwSwID();
		String approverComments = hrdswApprBean.getApproverComments();
		String userId = hrdswApprBean.getUserEmpId();
		String requesterCode = hrdswApprBean.getRequesterID();
		String status = hrdswApprBean.getStatus();
		String poNumber = hrdswApprBean.getPpoNumber();
		String poAttachment = hrdswApprBean.getPpoAttachement();
		String path = hrdswApprBean.getDataPath();
		String fileanme = hrdswApprBean.getUploadFileName();
		try {
			int level = Integer.parseInt(hrdswApprBean.getLevel());
			corporateProcurementHSAvailable = model
					.isCorporateProcurementHSAvailable();

			if (corporateProcurementHSAvailable) {
				//boolean isApprover = model.isApprover(applicationId, userId);
				status = model.approve(applicationId, approverComments, userId,
						status, level, poNumber, poAttachment);
				hrdswApprBean.setStatus(status);
				hrdswApprBean.setApproverComments("");

				addActionMessage("Application approved successfully.");

				if (hrdswApprBean.getStatus().equals("F")) {
					sendApprovalMail(applicationId, userId, requesterCode, status, path, fileanme);
				} else {
					String ppoAttachement = hrdswApprBean.getPpoAttachement();
					sendApprovalMailToApplicantFinal(applicationId, userId, requesterCode, status, path, fileanme, ppoAttachement);
					//sendITApproverToRequester(applicationId, userId, requesterCode, status, path, fileanme, ppoAttachement);
				}
				model.terminate();
			} else {
				addActionMessage("Corporate Procurement Group HS authortity person is not available. \n Please Contact to HR department.");
			}

		} catch (Exception e) {
			logger.error("Exception in approve Function : " + e);
			e.printStackTrace();
		}

		if (corporateProcurementHSAvailable) {
			input();
			return INPUT;
		} else {
			model.viewApplicationFunction(hrdswApprBean, applicationId);
			getNavigationPanel(1);
			hrdswApprBean.setEnableAll("Y");
			return SUCCESS;
		}
	}

	private void populateApproverComments(Object[][] apprCommentListObj) {
		ArrayList approverCommentList = new ArrayList<Object>(
				apprCommentListObj.length);

		for (int i = 0; i < apprCommentListObj.length; i++) {
			HardwareSoftwareRequestApprover innerBean = new HardwareSoftwareRequestApprover();
			innerBean.setApprName(checkNull(String
					.valueOf(apprCommentListObj[i][0])));
			innerBean.setApprComments(checkNull(String
					.valueOf(apprCommentListObj[i][1])));
			innerBean.setApprDate(checkNull(String
					.valueOf(apprCommentListObj[i][2])));
			innerBean.setApprStatus(checkNull(String
					.valueOf(apprCommentListObj[i][3])));
			approverCommentList.add(innerBean);
		}
		hrdswApprBean.setApproverCommentList(approverCommentList);
	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String rejectApplication() {
		try {
			String applicationId = hrdswApprBean.getHwSwID();
			String approverComments = hrdswApprBean.getApproverComments();
			String userId = hrdswApprBean.getUserEmpId();
			String requesterCode = hrdswApprBean.getRequesterID();

			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			model.reject(hrdswApprBean, applicationId, approverComments, userId);
			String status = hrdswApprBean.getStatus();
			model.terminate();
			addActionMessage("Application rejected successfully.");
			String path = hrdswApprBean.getDataPath();
			String fileanme = hrdswApprBean.getUploadFileName();
			if (model.isApprover(applicationId, userId)) {
				try {

					sendRejectSentBackApproverToApplicantMail(applicationId,
							userId, requesterCode, status, path, fileanme);
				} catch (Exception e) {
					logger.error("Exception in approve Function : " + e);
				}
			} else {
				try {

					String ppoAttachement = hrdswApprBean.getPpoAttachement();

					sendApprovalMailToApplicantFinal(applicationId, userId,
							requesterCode, status, path, fileanme,
							ppoAttachement);
					sendITApproverToRequester(applicationId, userId,
							requesterCode, status, path, fileanme,
							ppoAttachement);
				} catch (Exception e) {
					logger.error("Exception in approve Function : " + e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String sendBackApplication() {
		try {
			String applicationId = hrdswApprBean.getHwSwID();
			String approverComments = hrdswApprBean.getApproverComments();
			String userId = hrdswApprBean.getUserEmpId();
			String requesterCode = hrdswApprBean.getRequesterID();

			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			String nextApprover = userId;

			Object[][] empFlow = generateEmpFlow(requesterCode, "D1", 1);
			if (empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}

			String status = model.sendBack(applicationId, approverComments,
					userId, nextApprover);

			hrdswApprBean.setStatus(status);
			hrdswApprBean.setApproverComments("");
			model.terminate();

			addActionMessage("Application send back successfully.");
			String path = hrdswApprBean.getDataPath();
			String fileanme = hrdswApprBean.getUploadFileName();
			if (model.isApprover(applicationId, userId)) {
				sendRejectSentBackApproverToApplicantMail(applicationId, userId, requesterCode, status, path, fileanme);
			} else {
					String ppoAttachement = hrdswApprBean.getPpoAttachement();
					sendApprovalMailToApplicantFinal(applicationId, userId, requesterCode, status, path, fileanme, ppoAttachement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String viewApplicationFunction() {
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName
				+ "/HardwareSoftware/";

		hrdswApprBean.setDataPath(dataPath);

		String typeOfList = hrdswApprBean.getListType();

		String status = hrdswApprBean.getStatus();

		hrdswApprBean.setListTypeDetailPage(typeOfList);
		String hiddenStatus = request.getParameter("hiddenStatus");
		String applCode = request.getParameter("applCode");
		try {
			String applicationID = request.getParameter("applicationID");
			/*
			 * FOR SUPER USER
			 */
			if (applCode != null && applCode.length() > 0) {
				applicationID = applCode;
			}
			hrdswApprBean.setFwdempCode("");
			model.viewApplicationFunction(hrdswApprBean, applicationID);
			Object[][] apprCommentListObj = model
					.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.terminate();

			if (hiddenStatus.equals("_F")) {
				hrdswApprBean.setEnableAll("Y");
				getNavigationPanel(1);
				hrdswApprBean.setPpoFlag("true");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("true");
			}

			if (hiddenStatus.equals("_P")) {
				hrdswApprBean.setEnableAll("Y");
				getNavigationPanel(1);
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setFileFlag("true");
			}
			if (hrdswApprBean.getListType().equals("approved")
					|| hrdswApprBean.getListType().equals("rejected")) {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setApproverCommentsFlag(false);
				// hrdswApprBean.setPpoFlag(true);
				hrdswApprBean.setFileFlag("true");
			}

			// Added by Nilesh
			String userId = hrdswApprBean.getUserEmpId();
			String query = " SELECT HWSW_APPOVER_ID FROM  HRMS_D1_HARDWARE_SOFTWARE_REQ where HWSW_REQ_ID ="
					+ applicationID;
			Object[][] code = model.getSqlModel().getSingleResult(query);
			String codeValue = String.valueOf(code[0][0]);

			/*		if (hiddenStatus.equals("F")) {
						String queryGroup = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_EMP_ID LIKE ("
								+ userId + ")";
						Object[][] data = model.getSqlModel().getSingleResult(
								queryGroup);

						if (data != null && data.length > 0) {
							getNavigationPanel(5);
							hrdswApprBean.setApproverCommentsFlag(true);
							hrdswApprBean.setF9Flag("true");
							hrdswApprBean.setFileFlag("false");
						}

					}*/

			String groupQuery = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'C' AND APP_SETTINGS_EMP_ID is not null order by APP_SETTINGS_ID";
			Object[][] data = model.getSqlModel().getSingleResult(groupQuery);

			/*if (String.valueOf(data[0][0]).equals(userId)
					&& hiddenStatus.equals("F")) {
				hrdswApprBean.setPpoAttachement("");
				getNavigationPanel(5);
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("true");
				hrdswApprBean.setAttachedFlag("true");
			} else { 
			
			if (hiddenStatus.equals("F")) {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("false");

			}*/ 
			if (hiddenStatus.equals("F")) {
				hrdswApprBean.setPpoAttachement("");
				getNavigationPanel(5);
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("true");
				hrdswApprBean.setAttachedFlag("true");

			} else if (hiddenStatus.equals("H") && (codeValue.equals(userId))) {
				getNavigationPanel(1);
				//hrdswApprBean.setCheckFlag("true");
				hrdswApprBean.setAttachedFlag("true");
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setFileFlag("false");
			} else if (hiddenStatus.equals("H") && (!codeValue.equals(userId))) {
				//hrdswApprBean.setCheckFlag("true");
				hrdswApprBean.setAttachedFlag("true");
				getNavigationPanel(2);
				hrdswApprBean.setEnableAll("N");
			} else if (hiddenStatus.equals("P")) {
				getNavigationPanel(1);
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setFileFlag("false");
			} else {
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("false");
				getNavigationPanel(2);
				hrdswApprBean.setEnableAll("N");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// FOR SUPER USER
		if (applCode != null && applCode.length() > 0) {
			if (hrdswApprBean.getStatus().equals("_D")
					|| hrdswApprBean.getStatus().equals("_A")
					|| hrdswApprBean.getStatus().equals("_B")
					|| hrdswApprBean.getStatus().equals("_R")) {
				hrdswApprBean.setApproverCommentsFlag(false);
				getNavigationPanel(4);
				hrdswApprBean.setEnableAll("N");
			} else {
				hrdswApprBean.setApproverCommentsFlag(true);
				getNavigationPanel(3);
				hrdswApprBean.setEnableAll("N");
			}
		}
		// hrdswApprBean.setEnableAll("Y");
		return SUCCESS;
	}
	//Added by Nilesh on 12th Dec 2011.

	public String viewApprovedApplication() {
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName + "/HardwareSoftware/";

		hrdswApprBean.setDataPath(dataPath);
		String typeOfList = hrdswApprBean.getListType();
		hrdswApprBean.setListTypeDetailPage(typeOfList);
		String hiddenStatus = request.getParameter("hiddenStatus");
		String applCode = request.getParameter("applCode");
		try {
			String applicationID = request.getParameter("applicationID");
			/*
			 * FOR SUPER USER
			 */
			if (applCode != null && applCode.length() > 0) {
				applicationID = applCode;
			}

			model.viewApplicationFunction(hrdswApprBean, applicationID);
			Object[][] apprCommentListObj = model
					.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.terminate();
			
			if (hiddenStatus.equals("_F")) {
				hrdswApprBean.setEnableAll("Y");
				getNavigationPanel(1);
				hrdswApprBean.setPpoFlag("true");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("true");
			}

			if (hiddenStatus.equals("_P")) {
				hrdswApprBean.setEnableAll("Y");
				getNavigationPanel(1);
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setFileFlag("true");
			}
			if (hrdswApprBean.getListType().equals("approved")
					|| hrdswApprBean.getListType().equals("rejected")) {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setApproverCommentsFlag(false);
				// hrdswApprBean.setPpoFlag(true);
				hrdswApprBean.setFileFlag("true");
			}

			// Added by Nilesh
			String userId = hrdswApprBean.getUserEmpId();
			String query = " SELECT HWSW_APPOVER_ID FROM  HRMS_D1_HARDWARE_SOFTWARE_REQ where HWSW_REQ_ID ="
					+ applicationID;
			Object[][] code = model.getSqlModel().getSingleResult(query);
			String codeValue = String.valueOf(code[0][0]);
			String groupQuery = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'C' AND APP_SETTINGS_EMP_ID is not null order by APP_SETTINGS_ID";
			Object[][] data = model.getSqlModel().getSingleResult(groupQuery);
			if (String.valueOf(data[0][0]).equals(userId) && hiddenStatus.equals("F")) {
				hrdswApprBean.setPpoAttachement("");
				getNavigationPanel(2);
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setAttachedFlag("false");
			} else if (hiddenStatus.equals("F")) {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("false");
			} else if (hiddenStatus.equals("H") && (codeValue.equals(userId))) {
				getNavigationPanel(1);
				hrdswApprBean.setCheckFlag("true");
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setFileFlag("false");
			} else if (hiddenStatus.equals("H") && (!codeValue.equals(userId))) {
				hrdswApprBean.setCheckFlag("true");
				getNavigationPanel(2);
				hrdswApprBean.setEnableAll("N");
			} else if (hiddenStatus.equals("P")) {
				getNavigationPanel(1);
				hrdswApprBean.setApproverCommentsFlag(true);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setFileFlag("false");
			} else {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setPpoFlag("");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setFileFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// FOR SUPER USER
		if (applCode != null && applCode.length() > 0) {
			if (hrdswApprBean.getStatus().equals("_D")
					|| hrdswApprBean.getStatus().equals("_A")
					|| hrdswApprBean.getStatus().equals("_B")
					|| hrdswApprBean.getStatus().equals("_R")) {
				hrdswApprBean.setApproverCommentsFlag(false);
				getNavigationPanel(4);
				hrdswApprBean.setEnableAll("N");
			} else {
				hrdswApprBean.setApproverCommentsFlag(true);
				getNavigationPanel(3);
				hrdswApprBean.setEnableAll("N");
			}
		}
		// hrdswApprBean.setEnableAll("Y");
		boolean corporateProcurementHSApprover = model.iscorporateProcurementHSApprover(hrdswApprBean.getUserEmpId());
		if (corporateProcurementHSApprover) {
			hrdswApprBean.setCheckFlag("true");
		} else if(hiddenStatus.equals("A")){
			hrdswApprBean.setCheckFlag("true");
		}
		
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String backToList() throws Exception {
		String listType = hrdswApprBean.getListType();
		hrdswApprBean.setHwSwID("");
		if(listType.equals("pending")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else {
			getRejectedList();
		}
		 
		return INPUT;
	}

	private void sendApprovalMail(String applicationId, String userId,
			String requesterCode, String status, String path, String fileanme) {
		try {
			// MAIL FROM APPROVER TO APPLICANT BEGINS
			try {
				HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
				model.initiate(context, session);

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
				templateQuery1.setParameter(1, userId);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, hrdswApprBean.getCompletedByID());
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, userId);
				templateQuery3.setParameter(2, applicationId);
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationId);
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationId);

				String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
						+ applicationId;
				Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

				String[] hwData = null;
				String hw = "";
				if (data != null && data.length > 0) {
					// hw =String.valueOf(data[0][0]);
					hwData = String.valueOf(data[0][0]).split(",");

					for (int i = 0; i < hwData.length; i++) {
						if (hwData[i].equals("1")) {
							hw += "" + (i + 1)
									+ ")Desktop Standard User Build A\n";
						} else if (hwData[i].equals("2")) {
							hw += "" + (i + 1)
									+ ")Desktop Standard User Build B\n";
						} else if (hwData[i].equals("3")) {
							hw += "" + (i + 1)
									+ ")Laptop Standard User Build A\n";
						} else if (hwData[i].equals("4")) {
							hw += "" + (i + 1)
									+ ")Laptop Standard User Build B\n";
						} else if (hwData[i].equals("5")) {
							hw += "" + (i + 1) + ")Other\n";
						}
					}
				}

				EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
				templateQueryApp6.setParameter(1, hw);
				templateQueryApp6.setParameter(2, applicationId);

				String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
						+ applicationId;
				Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

				String[] softwareData = null;
				String sw = "";
				String spSw = "";

				if (swData != null && swData.length > 0) {
					softwareData = String.valueOf(swData[0][0]).split(",");
					for (int i = 0; i < softwareData.length; i++) {
						if (softwareData[i].equals("1")) {
							sw += "" + (i + 1) + ")MS Office\n";
						} else if (softwareData[i].equals("2")) {
							sw += "" + (i + 1) + ")MS Project\n";
						} else if (softwareData[i].equals("3")) {
							sw += "" + (i + 1) + ")MS Visio\n";
						} else if (softwareData[i].equals("4")) {
							sw += "" + (i + 1) + ")Open Office\n";
						}
					}

					String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
							+ swData[0][1] + ")";
					Object spSwData[][] = model.getSqlModel().getSingleResult(
							spSwQuery);

					for (int i = 0; i < spSwData.length; i++) {
						spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
					}
				}

				EmailTemplateQuery templateQueryApp7 = template.getTemplateQuery(7);
				templateQueryApp7.setParameter(1, sw);
				templateQueryApp7.setParameter(2, spSw);
				templateQueryApp7.setParameter(3, applicationId);

				template.configMailAlert();

				if (!fileanme.trim().equals("")) {
					String[] attachment = new String[1];
					attachment[0] = path + fileanme;
					template.sendApplMailWithAttachment(attachment);
				} else {
					template.sendApplicationMail();
				}
				template.clearParameters();
				template.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// MAIL FROM APPROVER TO APPLICANT ENDS
			
			
			//MAIL FROM MANAGER TO FIRST GROUP MEMBER -- BEGINS
			try {
				HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
				model.initiate(context, session);
				final String firstGroupMemberIDQuery = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE ='C' AND APP_SETTINGS_EMP_ID IS NOT NULL ORDER BY APP_SETTINGS_ID ";
				final Object[][] firstGroupMemberID = model.getSqlModel().getSingleResult(firstGroupMemberIDQuery);
				if (firstGroupMemberID != null && firstGroupMemberID.length >0 ) {
					EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST GROUP TO FORWARDED EMPLOYEE");
					templateApp.getTemplateQueries();
					EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
					templateQueryApp1.setParameter(1, userId);
					EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
					templateQueryApp2.setParameter(1, String.valueOf(firstGroupMemberID[0][0]));
					EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applicationId);
					EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, applicationId);

					EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, userId);

					EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, applicationId);

					String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID=" + applicationId;
					Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

					String[] hwData = null;
					String hw = "";
					if (data != null && data.length > 0) {
						// hw =String.valueOf(data[0][0]);
						hwData = String.valueOf(data[0][0]).split(",");

						for (int i = 0; i < hwData.length; i++) {
							if (hwData[i].equals("1")) {
								hw += "" + (i + 1)
										+ ")Desktop Standard User Build A\n";
							} else if (hwData[i].equals("2")) {
								hw += "" + (i + 1)
										+ ")Desktop Standard User Build B\n";
							} else if (hwData[i].equals("3")) {
								hw += "" + (i + 1)
										+ ")Laptop Standard User Build A\n";
							} else if (hwData[i].equals("4")) {
								hw += "" + (i + 1)
										+ ")Laptop Standard User Build B\n";
							} else if (hwData[i].equals("5")) {
								hw += "" + (i + 1) + ")Other\n";
							}
						}
					}

					EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
					templateQueryApp7.setParameter(1, hw);
					templateQueryApp7.setParameter(2, applicationId);

					String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID=" + applicationId;
					Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

					String[] softwareData = null;
					String sw = "";
					String spSw = "";

					if (swData != null && swData.length > 0) {
						softwareData = String.valueOf(swData[0][0]).split(",");
						for (int i = 0; i < softwareData.length; i++) {
							if (softwareData[i].equals("1")) {
								sw += "" + (i + 1) + ")MS Office\n";
							} else if (softwareData[i].equals("2")) {
								sw += "" + (i + 1) + ")MS Project\n";
							} else if (softwareData[i].equals("3")) {
								sw += "" + (i + 1) + ")MS Visio\n";
							} else if (softwareData[i].equals("4")) {
								sw += "" + (i + 1) + ")Open Office\n";
							}
						}

						String spSwQuery = "SELECT SPECIAL_SOFTWARE_NAME FROM HRMS_D1_SPECIAL_SW_REQ WHERE SPECIAL_SOFTWARE_ID IN ("
								+ swData[0][1] + ")";
						Object spSwData[][] = model.getSqlModel().getSingleResult(spSwQuery);

						for (int i = 0; i < spSwData.length; i++) {
							spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
						}
					}

					EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
					templateQueryApp8.setParameter(1, sw);
					templateQueryApp8.setParameter(2, spSw);
					templateQueryApp8.setParameter(3, applicationId);

					EmailTemplateQuery templateQueryApp9 = templateApp.getTemplateQuery(9);
					templateQueryApp9.setParameter(1, applicationId);

					templateApp.configMailAlert();
					if (!fileanme.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + fileanme;
						templateApp.sendApplMailWithAttachment(attachment);
					} else {
						templateApp.sendApplicationMail();
					}
					templateApp.clearParameters();
					templateApp.terminate();
				}

			} catch(final Exception e) {
				e.printStackTrace();
			}
			//MAIL FROM MANAGER TO FIRST GROUP MEMBER -- ENDS
			
			
			
			
			
			// MAIL FROM APPROVER TO MANAGER BEGINS
			/*try {
				HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
				model.initiate(context, session);

				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp
						.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp
						.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);

				EmailTemplateQuery templateQueryApp2 = templateApp
						.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, "0");

				EmailTemplateQuery templateQueryApp3 = templateApp
						.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, applicationId);

				EmailTemplateQuery templateQueryApp4 = templateApp
						.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, applicationId);

				EmailTemplateQuery templateQueryApp5 = templateApp
						.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, applicationId);

				String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
						+ applicationId;
				Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

				String[] hwData = null;
				String hw = "";
				if (data != null && data.length > 0) {
					// hw =String.valueOf(data[0][0]);
					hwData = String.valueOf(data[0][0]).split(",");

					for (int i = 0; i < hwData.length; i++) {
						if (hwData[i].equals("1")) {
							hw += "" + (i + 1)
									+ ")Desktop Standard User Build A\n";
						} else if (hwData[i].equals("2")) {
							hw += "" + (i + 1)
									+ ")Desktop Standard User Build B\n";
						} else if (hwData[i].equals("3")) {
							hw += "" + (i + 1)
									+ ")Laptop Standard User Build A\n";
						} else if (hwData[i].equals("4")) {
							hw += "" + (i + 1)
									+ ")Laptop Standard User Build B\n";
						} else if (hwData[i].equals("5")) {
							hw += "" + (i + 1) + ")Other\n";
						}
					}
				}

				EmailTemplateQuery templateQueryApp6 = templateApp
						.getTemplateQuery(6);
				templateQueryApp6.setParameter(1, hw);
				templateQueryApp6.setParameter(2, applicationId);

				String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
						+ applicationId;
				Object swData[][] = model.getSqlModel()
						.getSingleResult(swQuery);

				String[] softwareData = null;
				String sw = "";
				String spSw = "";

				if (swData != null && swData.length > 0) {

					softwareData = String.valueOf(swData[0][0]).split(",");

					for (int i = 0; i < softwareData.length; i++) {
						if (softwareData[i].equals("1")) {
							sw += "" + (i + 1) + ")MS Office\n";
						} else if (softwareData[i].equals("2")) {
							sw += "" + (i + 1) + ")MS Project\n";
						} else if (softwareData[i].equals("3")) {
							sw += "" + (i + 1) + ")MS Visio\n";
						} else if (softwareData[i].equals("4")) {
							sw += "" + (i + 1) + ")Open Office\n";
						}
					}

					String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
							+ swData[0][1] + ")";
					Object spSwData[][] = model.getSqlModel().getSingleResult(
							spSwQuery);

					for (int i = 0; i < spSwData.length; i++) {
						spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
					}
				}

				EmailTemplateQuery templateQueryApp7 = templateApp
						.getTemplateQuery(7);
				templateQueryApp7.setParameter(1, sw);
				templateQueryApp7.setParameter(2, spSw);
				templateQueryApp7.setParameter(3, applicationId);

				templateApp.configMailAlert();

				
				 * String[] empData = null;
				 * 
				 * if(data != null && data.length > 1) { empData = new
				 * String[data.length]; for(int i = 1; i < empData.length; i++) {
				 * empData[i] = String.valueOf(data[i][0]); }
				 * templateApp.configMailAlert();
				 * templateApp.sendApplicationMailToKeepInfo(empData); }else{
				 * templateApp.configMailAlert(); }
				 

				String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
						+ " WHERE APP_SETTINGS_TYPE = 'C' AND APP_EMAIL_ID IS NOT NULL";
				Object setdata[][] = model.getSqlModel().getSingleResult(query);
				if (setdata != null && setdata.length > 0) {

					String[] attachment = new String[1];
					attachment[0] = path + fileanme;
					if (!fileanme.trim().equals("")) {
						templateApp.sendApplicationMailToGroups(setdata,
								attachment);
					} else {
						templateApp.sendApplicationMailToGroups(setdata);
					}

				}

				// templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();
				model.terminate();

			} catch (Exception e) {
				logger.error("Exception occured : " + e);
			}*/
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void sendApprovalMailToApplicantFinal(String applicationId,
			String userId, String requesterCode, String status, String path,
			String fileanme, String ppoAttachement) {
		try {

			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			// MAIL FROM MANAGER TO APPLICANT

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST MANAGER TO APPLICANT");
			templateApp.getTemplateQueries();

			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
			templateQueryApp1.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, hrdswApprBean.getCompletedByID());

			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);

			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");
				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}

			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, hw);
			templateQueryApp7.setParameter(2, applicationId);

			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

			String[] softwareData = null;
			String sw = "";
			String spSw = "";
			if (swData != null && swData.length > 0) {
				softwareData = String.valueOf(swData[0][0]).split(",");
				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);
				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}

			EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, sw);
			templateQueryApp8.setParameter(2, spSw);
			templateQueryApp8.setParameter(3, applicationId);

			templateApp.configMailAlert();

			/*
			 * String[] keepinformEmp = null; if(data != null && data.length >
			 * 0) { keepinformEmp = new String[data.length +1]; for(int i = 0; i <
			 * data.length; i++) { keepinformEmp[i] =
			 * String.valueOf(data[i][0]); } }
			 */

			String apprQuery = " SELECT DISTINCT HWSW_APPROVER FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPLICATION_ID = " + applicationId +
							   " AND HWSW_APPROVER NOT IN (" + hrdswApprBean.getUserEmpId() + ")";
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			if (apprDataObj != null && apprDataObj.length > 0) {
				for (int i = 0; i < apprDataObj.length; i++) {
					if (!fileanme.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + fileanme;
						templateApp.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) },
								attachment);
					} else if (!ppoAttachement.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + ppoAttachement;
						templateApp.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) },
								attachment);
					} else {
						templateApp.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) }, null);
					}
				}
			} 

			/*
			 * String[] keepinformEmp = null; keepinformEmp[apprDataObj.length] =
			 * String.valueOf(apprDataObj[0][0]);
			 * templateApp.sendApplicationMailToKeepInfo(keepinformEmp);
			 */

			/*String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'C' AND APP_EMAIL_ID IS NOT NULL";
			Object setdata[][] = model.getSqlModel().getSingleResult(query);
			if (setdata != null && setdata.length > 0) {
				if (!fileanme.trim().equals("")) {
					String[] attachment = new String[1];
					attachment[0] = path + fileanme;
					templateApp
							.sendApplicationMailToGroups(setdata, attachment);
				} else if (!ppoAttachement.trim().equals("")) {
					String[] attachment = new String[1];
					attachment[0] = path + ppoAttachement;
					templateApp
							.sendApplicationMailToGroups(setdata, attachment);
				} else {
					templateApp.sendApplicationMailToGroups(setdata);
				}
			}*/

			// If requester and initiator are different then only BEGINS
			String requesterNum = hrdswApprBean.getRequesterID();
			String initiatorNum = hrdswApprBean.getCompletedByID();
			if (!requesterNum.equals(initiatorNum)) {
				if (status.equals("A")) {
					if (!fileanme.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + fileanme;
						templateApp.sendApplMailWithAttachmentToKeepInf(new String[] { hrdswApprBean.getRequesterID() }, attachment);
					} else if (!ppoAttachement.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + ppoAttachement;
						templateApp.sendApplMailWithAttachmentToKeepInf(new String[] { hrdswApprBean.getRequesterID() }, attachment);
					} else {
						templateApp.sendApplMailWithAttachmentToKeepInf(new String[] { hrdswApprBean.getRequesterID() }, null);
					}
				}
			}
			// If requester and initiator are different then only ENDS
			
			
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else if (!ppoAttachement.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + ppoAttachement;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendRejectSentBackApproverToApplicantMail(
			String applicationId, String userId, String requesterCode,
			String status, String path, String fileanme) {
		// MAIL FROM APPROVER TO APPLICANT
		try {
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST REJECT/SENTBACK FROM APPROVER TO APPLICANT");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, hrdswApprBean.getCompletedByID());
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");

				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}

			EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, hw);
			templateQueryApp6.setParameter(2, applicationId);

			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

			String[] softwareData = null;
			String sw = "";
			String spSw = "";

			if (swData != null && swData.length > 0) {

				softwareData = String.valueOf(swData[0][0]).split(",");

				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);

				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}

			EmailTemplateQuery templateQueryApp7 = template.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, sw);
			templateQueryApp7.setParameter(2, spSw);
			templateQueryApp7.setParameter(3, applicationId);

			template.configMailAlert();
			
			String ppoAttachement = hrdswApprBean.getPpoAttachement();
			String apprQuery = " SELECT DISTINCT HWSW_APPROVER FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPLICATION_ID = " + applicationId +
			   				   " AND HWSW_APPROVER NOT IN (" + hrdswApprBean.getUserEmpId() + ")";
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			if (apprDataObj != null && apprDataObj.length > 0) {
				for (int i = 0; i < apprDataObj.length; i++) {
					if (!fileanme.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + fileanme;
						template.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) },
								attachment);
					} else if (!ppoAttachement.trim().equals("")) {
						String[] attachment = new String[1];
						attachment[0] = path + ppoAttachement;
						template.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) },
								attachment);
					} else {
						template.sendApplMailWithAttachmentToKeepInf(
								new String[] { String.valueOf(apprDataObj[i][0]) }, null);
					}
				}
			} 
			
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				template.sendApplMailWithAttachment(attachment);
			} else if (!ppoAttachement.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + ppoAttachement;
				template.sendApplMailWithAttachment(attachment);
			} else {
				template.sendApplicationMail();
			}
			 
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendITApproverToRequester(String applicationId, String userId,
			String requesterCode, String status, String path, String fileanme,
			String ppoAttachement) {
		try {
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			// MAIL FROM MANAGER TO APPLICANT
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST MANAGER TO APPLICANT");
			templateApp.getTemplateQueries();

			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, hrdswApprBean.getRequesterID());

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);

			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");

				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}

			EmailTemplateQuery templateQueryApp7 = templateApp
					.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, hw);
			templateQueryApp7.setParameter(2, applicationId);

			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

			String[] softwareData = null;
			String sw = "";
			String spSw = "";

			if (swData != null && swData.length > 0) {

				softwareData = String.valueOf(swData[0][0]).split(",");

				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);

				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}

			EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, sw);
			templateQueryApp8.setParameter(2, spSw);
			templateQueryApp8.setParameter(3, applicationId);

			templateApp.configMailAlert();
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else if (!ppoAttachement.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + ppoAttachement;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			/*
			 * MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
			 * response);
			 */

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}

				response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
			// return null;

		} catch (Exception e) {
			// logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			/*
			 * MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
			 * response);
			 */

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("docx")) {
					mimeType = "msexcel";
				} // end of else
				// end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
				} // end of if
				else {
					response.setHeader("Content-type", "application/" + mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename= \"" + fileName + "\"");
				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while
			} catch (FileNotFoundException e) {
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
		} catch (Exception e) {
			// logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	/**
	 * This Function is used For Search List.
	 * 
	 * @return f9action
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		// String status = request.getParameter("status");
		String query = "";
		// String Status = hrdswApprBean.getHwSwHiddenStatus();
		String userId = hrdswApprBean.getUserEmpId();

		/*if (hrdswApprBean.getHwswSearchId().equals("P")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME||' '|| EMP_LNAME ,TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_REQ_ID,HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'P' AND HWSW_APPOVER_ID = "
					+ hrdswApprBean.getUserEmpId()
					+ " ORDER BY HWSW_REQ_ID DESC ";
		}

		if (hrdswApprBean.getHwSwHiddenStatus().equals("_F")) {
			query = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE (HWSW_STATUS = 'P' AND HWSW_APPOVER_ID = "
					+ userId
					+ " ) "
					+ " OR (HWSW_STATUS = 'F')"
					+ " ORDER BY HWSW_REQ_ID DESC ";

		}
		if (hrdswApprBean.getHwswSearchId().equals("F")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, "
					+ "TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'),HWSW_REQ_ID,  HWSW_STATUS "
					+ "FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  "
					+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ "WHERE HWSW_STATUS IN ('F','H') ORDER BY HWSW_REQ_ID DESC";
		}
		if (hrdswApprBean.getHwswSearchId().equals("A")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, "
					+ "TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'),HWSW_REQ_ID,  HWSW_STATUS "
					+ "FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  "
					+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ "WHERE HWSW_STATUS IN ('F', 'A') ORDER BY HWSW_REQ_ID DESC";
		}

		if (hrdswApprBean.getHwswSearchId().equals("R")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME||' '|| EMP_LNAME ,TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_REQ_ID,  HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_APPOVER_ID = "
					+ hrdswApprBean.getUserEmpId()
					+ " ORDER BY HWSW_REQ_ID DESC ";
		}

		if (hrdswApprBean.getHwSwHiddenStatus().equals("_Z")
				|| hrdswApprBean.getHwSwHiddenStatus().equals("_R")
				|| hrdswApprBean.getHwswSearchId().equals("_R")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, "
					+ "TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'),HWSW_REQ_ID,  HWSW_STATUS "
					+ "FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  "
					+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ "WHERE HWSW_STATUS IN ('R', 'Z') ORDER BY HWSW_REQ_ID DESC";

		}
		if (hrdswApprBean.getHwSwHiddenStatus().equals("_A")
				|| hrdswApprBean.getHwswSearchId().equals("_A")) {
			query = " SELECT  HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, "
					+ "TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'),HWSW_REQ_ID,  HWSW_STATUS "
					+ "FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  "
					+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ "WHERE HWSW_STATUS IN ('A', 'X') ORDER BY HWSW_REQ_ID DESC";

		}*/
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);
		boolean corporateProcurementHSApprover = model.iscorporateProcurementHSApprover(userId);
		String selQuery = "";
		String code = "0";
		
		//IF LIST-TYPE IS "pending" -- BEGINS
		if (hrdswApprBean.getListType().equals("pending")) {
			if (corporateProcurementHSApprover) {
				selQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
								+ " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
								+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
								+ " WHERE HWSW_STATUS IN ('P','F','H') AND HWSW_APPOVER_ID =" + userId;
						
			} else {
				selQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						 + " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						 + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						 + " WHERE HWSW_STATUS IN ('P') AND HWSW_APPOVER_ID = " + userId + " ORDER BY HWSW_REQ_ID DESC ";
			}
		}
		//IF LIST-TYPE IS "pending" -- ENDS
		
		
		
		//IF LIST-TYPE IS "approved" -- BEGINS
		if (hrdswApprBean.getListType().equals("approved")) {
			if (corporateProcurementHSApprover) {
				String pathQuery = "SELECT DISTINCT HWSW_APPLICATION_ID FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPROVER ="	+ userId;
				Object[][] pathData = model.getSqlModel().getSingleResult(pathQuery);
				if (pathData != null && pathData.length > 0) {
					for (int i = 0; i < pathData.length; i++) {
						code += "," + String.valueOf(pathData[i][0]);
					}
				}

				String selManQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN ('F','H') AND HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID in("
					+ code + ")  ORDER BY HWSW_REQ_ID DESC";

				selQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN('A')  UNION   ";

				selQuery += selManQuery;

			} else {
				String pathQuery = "SELECT DISTINCT HWSW_APPLICATION_ID FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPROVER ="	+ userId;
				Object[][] pathData = model.getSqlModel().getSingleResult(pathQuery);
				if (pathData != null && pathData.length > 0) {
					for (int i = 0; i < pathData.length; i++) {
						code += "," + String.valueOf(pathData[i][0]);
					}
				}

				selQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN ('F', 'A','H') AND HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID in("
					+ code + ")ORDER BY HWSW_REQ_ID DESC";
			}
			model.terminate();
		}
		//IF LIST-TYPE IS "approved" -- ENDS
		
		//IF LIST-TYPE IS "rejected" -- BEGINS
		if (hrdswApprBean.getListType().equals("rejected")) {
			if (corporateProcurementHSApprover) {
				selQuery = " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						 + " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						 + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						 + " WHERE HWSW_STATUS IN('R','Z') ORDER BY HWSW_REQ_ID DESC";
			} else {
				selQuery =  " SELECT HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						  + " HWSW_REQ_ID, HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						  + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						  + " WHERE HWSW_STATUS = 'R' AND HWSW_APPOVER_ID = " + userId + " ORDER BY HWSW_REQ_ID DESC";
			}
		}
		//IF LIST-TYPE IS "rejected" -- ENDS

		String[] headers = { getMessage("trackingNumIterator"),
				getMessage("empName"), getMessage("applicationDateIterator") };

		String[] headerWidth = { "30", "40", "30" };

		String[] fieldNames = { "trackingNumIterator", "requesterNameIterator",
				"applicationDateIterator", "hwSwHiddenSearchId" };

		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "true";

		String submitToMethod = "HardwareSoftwareRequestApprover_viewApplicationFunctionSearch.action";

		setF9Window(selQuery, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * This function is used When Search Button is Clicked.
	 * 
	 * @return SUCCESS
	 */
	public String viewApplicationFunctionSearch() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName + "/HardwareSoftware/";

		hrdswApprBean.setDataPath(dataPath);
		String typeOfList = hrdswApprBean.getListType();
		hrdswApprBean.setListTypeDetailPage(typeOfList);
		String applCode = request.getParameter("applCode");
		try {
			String applicationID = hrdswApprBean.getHwSwHiddenSearchId();
			HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
			model.initiate(context, session);
			hrdswApprBean.setF9Flag("true");
			hrdswApprBean.setAttachedFlag("true");
			/*
			 * FOR SUPER USER
			 */
			if (applCode != null && applCode.length() > 0) {
				applicationID = applCode;
			}

			model.viewApplicationFunction(hrdswApprBean, applicationID);
			Object[][] apprCommentListObj = model
					.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.terminate();

			String query = " SELECT HWSW_APPOVER_ID,HWSW_STATUS FROM  HRMS_D1_HARDWARE_SOFTWARE_REQ where HWSW_REQ_ID ="
					+ applicationID;
			Object[][] code = model.getSqlModel().getSingleResult(query);
			String codeValue = String.valueOf(code[0][0]);
			String hiddenStatus = String.valueOf(code[0][1]);
			String userId = hrdswApprBean.getUserEmpId();
			if (hrdswApprBean.getListType().equals("approved")
					|| hrdswApprBean.getListType().equals("rejected")) {
				hrdswApprBean.setEnableAll("N");
				getNavigationPanel(2);
				hrdswApprBean.setPpoFlag("false");
				hrdswApprBean.setApproverCommentsFlag(false);
				hrdswApprBean.setF9Flag("false");
				hrdswApprBean.setFileFlag("false");
				hrdswApprBean.setAttachedFlag("false");
				// hrdswApprBean.setPpoFlag(true);
			} else {
				
				if (hiddenStatus.equals("F")) {
					hrdswApprBean.setPpoAttachement("");
					getNavigationPanel(5);
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("true");
					hrdswApprBean.setAttachedFlag("true");

				} else if (hiddenStatus.equals("H") && (codeValue.equals(userId))) {
					getNavigationPanel(1);
					hrdswApprBean.setCheckFlag("true");
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("false");
					hrdswApprBean.setFileFlag("false");
					hrdswApprBean.setAttachedFlag("false");
				} else if (hiddenStatus.equals("H") && (!codeValue.equals(userId))) {
					hrdswApprBean.setCheckFlag("true");
					getNavigationPanel(2);
					hrdswApprBean.setEnableAll("N");
				} else if (hiddenStatus.equals("P")) {
					getNavigationPanel(1);
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("false");
					hrdswApprBean.setFileFlag("false");
					hrdswApprBean.setAttachedFlag("false");
					hrdswApprBean.setCheckFlag("false");
				} else {
					hrdswApprBean.setPpoFlag("");
					hrdswApprBean.setApproverCommentsFlag(false);
					hrdswApprBean.setFileFlag("false");
					getNavigationPanel(2);
					hrdswApprBean.setEnableAll("N");
				}
				
				/*if (hiddenStatus.equals("P")) {
					hrdswApprBean.setEnableAll("Y");
					getNavigationPanel(1);
					hrdswApprBean.setPpoFlag("false");
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("false");
					hrdswApprBean.setFileFlag("false");
					hrdswApprBean.setAttachedFlag("false");
				}
				
				if (hiddenStatus.equals("H") && (codeValue.equals(userId))) {
					getNavigationPanel(1);
					hrdswApprBean.setCheckFlag("true");
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("false");
					hrdswApprBean.setFileFlag("false");
				}

				if (hiddenStatus.equals("F")) {
					hrdswApprBean.setEnableAll("Y");
					getNavigationPanel(1);
					hrdswApprBean.setPpoFlag("true");
					hrdswApprBean.setApproverCommentsFlag(true);
					hrdswApprBean.setF9Flag("true");
				}*/
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// FOR SUPER USER
		if (applCode != null && applCode.length() > 0) {
			if (hrdswApprBean.getStatus().equals("_D")
					|| hrdswApprBean.getStatus().equals("_A")
					|| hrdswApprBean.getStatus().equals("_B")
					|| hrdswApprBean.getStatus().equals("_R")) {
				hrdswApprBean.setApproverCommentsFlag(false);
				getNavigationPanel(4);
				hrdswApprBean.setEnableAll("N");
			} else {
				hrdswApprBean.setApproverCommentsFlag(true);
				getNavigationPanel(3);
				hrdswApprBean.setEnableAll("N");
			}
		}
		// hrdswApprBean.setEnableAll("Y");

		return SUCCESS;
	}

	// Addded By Nilesh D on 14th Nov 2011
	/**
	 * Method : forwardToEmp. Purpose : Search particular employee.
	 * 
	 * @return String.
	 */
	public String f9forwardToEmp() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC "
				+ " INNER JOIN  HRMS_D1_APPROVAL_SETTINGS ON (HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID = HRMS_EMP_OFFC.EMP_ID)    ";
		if (this.hrdswApprBean.getUserProfileDivision() != null
				&& this.hrdswApprBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ this.hrdswApprBean.getUserProfileDivision() + " )";
		} else {
			query += " WHERE 1=1 AND EMP_STATUS='S'";
		}

		query += " AND APP_SETTINGS_TYPE = 'C' AND APP_SETTINGS_EMP_ID IS NOT NULL 	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = { "Employee Id", "Employee Name" };

		final String[] headerWidth = { "30", "70" };

		final String[] fieldNames = { "forwardEmpToken", "forwardEmpName",
				"fwdempCode" };

		final int[] columnIndex = { 0, 1, 2 };

		final String submitFlag = "false";

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String forward() {
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);
		String fwdCode = this.hrdswApprBean.getFwdempCode();
		String userId = hrdswApprBean.getUserEmpId();
		String applicationId = hrdswApprBean.getHwSwID();
		String poAttachment = hrdswApprBean.getPpoAttachement();
		String ppoName = hrdswApprBean.getPpoNumber().trim();
		model.updateApprover(hrdswApprBean, userId, applicationId, fwdCode, poAttachment, ppoName);
		addActionMessage("Application forwarded successfully");

		String requesterCode = hrdswApprBean.getRequesterID();
		String path = hrdswApprBean.getDataPath();
		String fileanme = hrdswApprBean.getUploadFileName();

		String status = "H";
		sendForwardedMail(applicationId, userId, requesterCode, status, path,
				fileanme, fwdCode);
		input();
		return INPUT;
	}

	private void sendForwardedMail(String applicationId, String userId,
			String requesterCode, String status, String path, String fileanme,
			String fwdCode) {
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		model.initiate(context, session);
		Object[][] empFlow = null;
		empFlow = generateEmpFlow(hrdswApprBean.getCompletedByID(), "D1", 1);
		String empCode = String.valueOf(empFlow[0][0]);
		try {
			//Mail from Group Employee to Forwarded Employee
			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID ,APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS  WHERE APP_SETTINGS_TYPE ='C' AND APP_EMAIL_ID IS NOT NULL ";
			final Object[][] empdata = model.getSqlModel().getSingleResult(
					query);

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST GROUP TO FORWARDED EMPLOYEE");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, fwdCode);
			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);

			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);

			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");

				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}

			EmailTemplateQuery templateQueryApp7 = templateApp
					.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, hw);
			templateQueryApp7.setParameter(2, applicationId);

			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);

			String[] softwareData = null;
			String sw = "";
			String spSw = "";

			if (swData != null && swData.length > 0) {

				softwareData = String.valueOf(swData[0][0]).split(",");

				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "SELECT SPECIAL_SOFTWARE_NAME FROM HRMS_D1_SPECIAL_SW_REQ WHERE SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);

				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}

			EmailTemplateQuery templateQueryApp8 = templateApp
					.getTemplateQuery(8);

			EmailTemplateQuery templateQueryApp9 = templateApp
					.getTemplateQuery(9);
			templateQueryApp9.setParameter(1, applicationId);

			templateQueryApp8.setParameter(1, sw);
			templateQueryApp8.setParameter(2, spSw);
			templateQueryApp8.setParameter(3, applicationId);

			templateApp.configMailAlert();
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Mail from Group to Applicant
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST GROUP TO APPLICANT");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, hrdswApprBean.getCompletedByID());
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);
			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");

				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}
			EmailTemplateQuery templateQueryApp5 = template.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, hw);
			templateQueryApp5.setParameter(2, applicationId);
			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);
			String[] softwareData = null;
			String sw = "";
			String spSw = "";
			if (swData != null && swData.length > 0) {

				softwareData = String.valueOf(swData[0][0]).split(",");

				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);

				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}
			EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, sw);
			templateQueryApp6.setParameter(2, spSw);
			templateQueryApp6.setParameter(3, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			template.configMailAlert();
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				template.sendApplMailWithAttachment(attachment);
			} else {
				template.sendApplicationMail();
			}
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Mail From Group to Manager

		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST GROUP TO MANAGER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, empCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			String hwQuery = "SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);
			String[] hwData = null;
			String hw = "";
			if (data != null && data.length > 0) {
				// hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");

				for (int i = 0; i < hwData.length; i++) {
					if (hwData[i].equals("1")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build A\n";
					} else if (hwData[i].equals("2")) {
						hw += "" + (i + 1) + ")Desktop Standard User Build B\n";
					} else if (hwData[i].equals("3")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build A\n";
					} else if (hwData[i].equals("4")) {
						hw += "" + (i + 1) + ")Laptop Standard User Build B\n";
					} else if (hwData[i].equals("5")) {
						hw += "" + (i + 1) + ")Other\n";
					}
				}
			}
			EmailTemplateQuery templateQueryApp5 = template.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, hw);
			templateQueryApp5.setParameter(2, applicationId);
			String swQuery = "SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID="
					+ applicationId;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);
			String[] softwareData = null;
			String sw = "";
			String spSw = "";
			if (swData != null && swData.length > 0) {

				softwareData = String.valueOf(swData[0][0]).split(",");

				for (int i = 0; i < softwareData.length; i++) {
					if (softwareData[i].equals("1")) {
						sw += "" + (i + 1) + ")MS Office\n";
					} else if (softwareData[i].equals("2")) {
						sw += "" + (i + 1) + ")MS Project\n";
					} else if (softwareData[i].equals("3")) {
						sw += "" + (i + 1) + ")MS Visio\n";
					} else if (softwareData[i].equals("4")) {
						sw += "" + (i + 1) + ")Open Office\n";
					}
				}

				String spSwQuery = "select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("
						+ swData[0][1] + ")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(
						spSwQuery);

				for (int i = 0; i < spSwData.length; i++) {
					spSw += "" + (i + 1) + ")" + spSwData[i][0] + "\n";
				}
			}
			EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, sw);
			templateQueryApp6.setParameter(2, spSw);
			templateQueryApp6.setParameter(3, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationId);

			template.configMailAlert();
			if (!fileanme.trim().equals("")) {
				String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				template.sendApplMailWithAttachment(attachment);
			} else {
				template.sendApplicationMail();
			}
			template.clearParameters();
			template.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
