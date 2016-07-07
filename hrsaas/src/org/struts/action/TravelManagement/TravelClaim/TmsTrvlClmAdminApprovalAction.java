package org.struts.action.TravelManagement.TravelClaim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmAdminApproval;
import org.paradyne.lib.Utility;
import org.paradyne.lib.sms.SMSUtil;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.TmsTrvlClmAdminApprovalModel;
import org.paradyne.model.TravelManagement.TravelProcess.TravelProcessModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class TmsTrvlClmAdminApprovalAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlClmAdminApprovalAction.class);
	TmsTrvlClmAdminApproval trvlClmAdminApprvl;

	public void prepare_local() throws Exception {
		trvlClmAdminApprvl = new TmsTrvlClmAdminApproval();
		trvlClmAdminApprvl.setMenuCode(1091);

	}

	public Object getModel() {
		return trvlClmAdminApprvl;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
		model.initiate(context, session);
		model.callStatus(trvlClmAdminApprvl, "P", request);
		trvlClmAdminApprvl.setStatus("P");
		trvlClmAdminApprvl.setNavStatus("P");

		model.terminate();
	}

	public String callStatus() {
		TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
		model.initiate(context, session);

		String status = request.getParameter("status");

		if (status.equals("F")) {
			status = "P";
		}

		trvlClmAdminApprvl.setNavStatus(status);
		model.callStatus(trvlClmAdminApprvl, status, request);
		trvlClmAdminApprvl.setStatus(status);

		model.terminate();
		return "success";
	}

	public String input() {
		TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
		model.initiate(context, session);
		model.callStatus(trvlClmAdminApprvl, "P", request);
		trvlClmAdminApprvl.setStatus("P");
		trvlClmAdminApprvl.setNavStatus("P");

		model.terminate();
		try {
			String clmAppFlag = request.getParameter("clmAppFlag");
			String clmApplStatus = request.getParameter("clmStatus");
			logger.info("clmAppFlag====" + clmAppFlag);
			logger.info("clmApplStatus====" + clmApplStatus);
			String claimId = request.getParameter("claimId");
			logger.info("claimId 1==" + claimId);
			if (clmAppFlag == null && clmApplStatus == null) {

			} else {
				if (clmAppFlag != null && !clmAppFlag.equals("null")
						&& !clmAppFlag.equals("")) {
					trvlClmAdminApprvl.setClmAppFlag("Y");
					trvlClmAdminApprvl.setNavStatus("");
				} else
					trvlClmAdminApprvl.setClmAppFlag("");

				if (clmApplStatus != null && !clmApplStatus.equals("null")
						&& !clmApplStatus.equals("")) {
					trvlClmAdminApprvl.setClmApplStatus("CL");
					trvlClmAdminApprvl.setNavStatus("");
				} else
					trvlClmAdminApprvl.setClmApplStatus("");
				callView();
				return "trvlClmApprView";
			}
		} catch (Exception e) {
			logger.info("clmApplStatus====null");
		}
		return "input";
	}

	public String callView() {
		
		String source = request.getParameter("src");

		//String source =(String) request.getAttribute("src");

		System.out.println("source--------------" + source);
		trvlClmAdminApprvl.setSource(source);
		
		TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
		//String claimId = request.getParameter("claimId");

		String expAppId = request.getParameter("expAppId");
		trvlClmAdminApprvl.setTmsClmAppId(expAppId);
		model.initiate(context, session);
		model.view(trvlClmAdminApprvl, request, expAppId);
		trvlClmAdminApprvl.setApproverListFlag("true");
		trvlClmAdminApprvl.setApproverCommentsFlag("true");

		String query = "  SELECT CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_ADMIN_STATUS END,  TMS_APPLICATION.APPL_ID  FROM  TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " WHERE EXP_APPID=" + expAppId;

		Object data[][] = model.getSqlModel().getSingleResult(query);
		String travelAppId = "";
		setBudgetExpenditure(expAppId);
		if (data != null && data.length > 0) {
			if (String.valueOf(data[0][0]).equals("A")
					|| String.valueOf(data[0][0]).equals("R")
					|| String.valueOf(data[0][0]).equals("F")) {
				trvlClmAdminApprvl.setApproverCommentsFlag("false");

				if (String.valueOf(data[0][0]).equals("F")) {
					trvlClmAdminApprvl.setNavStatus("F");
					trvlClmAdminApprvl.setRevokeFlag(true);
				}
			}
			travelAppId= String.valueOf(data[0][1]);
		}
		model.getRatingParameters(trvlClmAdminApprvl, expAppId, travelAppId);
		String statusQUery = " SELECT APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="
				+ trvlClmAdminApprvl.getTmsTrvlId();

		Object statusObj[][] = model.getSqlModel().getSingleResult(statusQUery);

		if (statusObj != null && statusObj.length > 0) {
			if (String.valueOf(statusObj[0][0]).equals("C")) {
				trvlClmAdminApprvl.setShowBookingDetailsFlag("true");
			}
		}
		model.terminate();
		//logger.info("claimId end==" + claimId);
		return "trvlClmApprView";

	}

	public String doFun() {
		try {
			TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
			model.initiate(context, session);
			logger.info("==============doFun====");
			String status = trvlClmAdminApprvl.getCheckStatus().trim();
			String msgFlag = trvlClmAdminApprvl.getStatusSave();
			boolean result = model.saveApplication(trvlClmAdminApprvl
					.getTmsClmAppId(), status, trvlClmAdminApprvl
					.getTmsApprvrLevel(), trvlClmAdminApprvl.getClmApprCmts(),
					trvlClmAdminApprvl.getTrvlEmpId(), trvlClmAdminApprvl
							.getUserEmpId(), request);
			
			logger.info("######### EMPLOYEE ID ##############"+trvlClmAdminApprvl.getTrvlEmpId());
			
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel Claim";
			processAlerts.removeProcessAlert(String.valueOf(trvlClmAdminApprvl.getTmsClmAppId()),
					module);

			
			if (result) {
				if (msgFlag.equals("A")) {
					/**
					 * SMS Util...
					 * 
					 */
					try {
						System.out.println("Travel SMS Util");
						Object[][] mobileNoObj = model.getSqlModel()
								.getSingleResult(
										"SELECT APPL_EMP_CONTACT FROM TMS_APP_EMPDTL WHERE APPL_ID="
												+ trvlClmAdminApprvl
														.getTrvlAppId());
						Object[][] travelIdObj = model.getSqlModel()
								.getSingleResult(
										"SELECT EXP_TRVL_ID FROM  TMS_CLAIM_APPL WHERE EXP_APPID="
												+ trvlClmAdminApprvl
														.getTmsClmAppId());

						String message = "Your Travel Claim"
								+ String.valueOf(travelIdObj[0][0])
								+ "  is approved by Admin.Kindly forward hardcopies to Account department.";
						SMSUtil su = new SMSUtil();
						su.init(model.getSqlModel());
						if (mobileNoObj != null
								&& String.valueOf(mobileNoObj[0][0]) != null
								&& !String.valueOf(
										String.valueOf(mobileNoObj[0][0]))
										.equals("")) {
							su.send(String.valueOf(String
									.valueOf(mobileNoObj[0][0])), message);
						}
						su.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					String accountantId = "";
					String accountantQuery = "  SELECT AUTH_ACCOUNTENT  FROM TMS_MANG_AUTH_HDR  WHERE AUTH_MAIN_SCHL_ID="
							+ trvlClmAdminApprvl.getUserEmpId();

					Object[][] accountantObj = model.getSqlModel()
							.getSingleResult(accountantQuery);

					if (accountantObj != null && accountantObj.length > 0) {
						accountantId = String.valueOf(accountantObj[0][0]);
						logger.info("########## accountantId ################"+accountantId);
					}

					/** ********************************* */

					/**
					 * * Create instance of EmailTemplateBody
					 */

					EmailTemplateBody template = new EmailTemplateBody();

					// Initiate template
					template.initiate(context, session);

					// Set respective template name
					template
							.setEmailTemplate("Travel Claim Application Admin approved/rejected/sent back");

					// call compulsory for set the queries of template
					template.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQuery1.setParameter(1, trvlClmAdminApprvl.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);// To EMAIL
						templateQuery2.setParameter(1, trvlClmAdminApprvl.getTrvlEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, trvlClmAdminApprvl
								.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery5 = template
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery6 = template
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery7 = template
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery8 = template
								.getTemplateQuery(8);
						templateQuery8.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdm9 = template
								.getTemplateQuery(9);
						templateQueryAdm9.setParameter(1, trvlClmAdminApprvl.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdm10 = template
								.getTemplateQuery(10);
						templateQueryAdm10.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*try {
						EmailTemplateQuery templateQueryAdm11 = template
						.getTemplateQuery(11);
						templateQueryAdm11.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
						System.out.println("trvlClmAdminApprvl.getTmsClmAppId ====> "+trvlClmAdminApprvl.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}*/

					// Add approval link -pass parameters to the link

			/*		String[] link_param = new String[3];
					String[] link_label = new String[3];
					// String applicationType = "TYD";
					String applicationAppType = "TravelClaim";
					link_param[0] = applicationAppType + "#"
							+ trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "A"
							+ "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_param[1] = applicationAppType + "#"
							+ trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "R"
							+ "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_param[2] = applicationAppType + "#"
							+ trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "B"
							+ "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";*/

					// configure the actual contents of the template
					template.configMailAlert();
				//	template.addOnlineLink(request, link_param, link_label);
					
						
					String actualStataus="Approved";
					String  linkParam ="applnStatus=R&status=process&claimApplnCode="+trvlClmAdminApprvl.getTmsClmAppId() ;
					String  alertlink ="/TMS/TravelClaim_trvlClaimDraftDtl.action";
					
				 
					template.sendProcessManagerAlert(trvlClmAdminApprvl.getUserEmpId(),
							"Travel Claim", "I",  trvlClmAdminApprvl.getTmsClmAppId(), "1",
							linkParam, alertlink, actualStataus,  trvlClmAdminApprvl.getTmsClmAppId(),
							"0", "", trvlClmAdminApprvl.getTrvlEmpId(),	trvlClmAdminApprvl.getUserEmpId());
			 
					// call for sending the email
					template.sendApplicationMail();

					// clear the template
					template.clearParameters();

					// terminate template
					template.terminate();

					addActionMessage("Application approved successfully");

					/** ******************************* */

					/** ********************************* */

					/**
					 * * 2.  Travel Claim Application Admin to Accountant
					 */

					/*String adminId = "";
					String adminQuery = "  SELECT AUTH_ACCOUNTENT AS AUTH_ACCOUNTENT, NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') AS ADMIN_NAME ,ADMIN.RANK_NAME AS ADMIN_RANK , "
										+ "	NVL(C2.EMP_FNAME||' '||C2.EMP_LNAME,' ') AS ACCOUNTANT_NAME , ACCOUNTENT.RANK_NAME AS ACCOUNTENT_RANK "
										+ "		FROM TMS_MANG_AUTH_HDR "
										+ "		INNER JOIN HRMS_EMP_OFFC C1 ON(C1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID) "
										+ "		INNER JOIN HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT) "
										+ "		LEFT JOIN HRMS_RANK ADMIN ON(ADMIN.RANK_ID=C1.EMP_RANK) LEFT JOIN HRMS_RANK ACCOUNTENT ON(ACCOUNTENT.RANK_ID=C2.EMP_RANK)" 
										+ "		WHERE AUTH_MAIN_SCHL_ID = "+trvlClmAdminApprvl.getUserEmpId()+" AND AUTH_STATUS='A'";
					
					Object[][] adminObj = model.getSqlModel().getSingleResult(
							adminQuery);

					if (adminObj != null && adminObj.length > 0) {
						adminId = String.valueOf(adminObj[0][0]);
					}*/

					//ADDED BY REEBA FOR ACCOUNTANT EMAIL
					String authId = "";
					String acknowledgementId = "";
					String accountCode="";
					String adminQuery = "  SELECT  AUTH_ID ,AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT FROM TMS_MANG_AUTH_HDR "
							+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
							+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
							+ trvlClmAdminApprvl.getTrvlEmpId() + ")";//branch Id
					Object[][] adminObj = model.getSqlModel().getSingleResult(
							adminQuery);
					logger.info("getTrvlEmpId======"
							+ trvlClmAdminApprvl.getTrvlEmpId());
					if (adminObj != null && adminObj.length > 0) {
						authId = String.valueOf(adminObj[0][0]);
						acknowledgementId=String.valueOf(adminObj[0][1]);
						accountCode=String.valueOf(adminObj[0][2]);
					}
					adminQuery = "  SELECT  AUTH_ID, AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
							+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";//branch Id
					adminObj = model.getSqlModel().getSingleResult(adminQuery);
					if (adminObj != null && adminObj.length > 0) {
						authId = String.valueOf(adminObj[0][0]);
						acknowledgementId=String.valueOf(adminObj[0][1]);
						accountCode=String.valueOf(adminObj[0][2]);
					}
					logger.info("Authorisation id======" + authId);
					//ADDED BY REEBA ENDS

					EmailTemplateBody templateAdmin = new EmailTemplateBody();

					// Initiate template
					templateAdmin.initiate(context, session);

					// Set respective template name
					templateAdmin
							.setEmailTemplate("Travel Claim Application Admin to Accountant");

					// call compulsory for set the queries of template
					templateAdmin.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQueryAdmApp1 = templateAdmin
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQueryAdmApp1.setParameter(1, trvlClmAdminApprvl.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {

						EmailTemplateQuery templateQueryAdmApp2 = templateAdmin
								.getTemplateQuery(2);// To EMAIL
						templateQueryAdmApp2.setParameter(1, authId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAdmApp3 = templateAdmin
								.getTemplateQuery(3);
						templateQueryAdmApp3.setParameter(1, trvlClmAdminApprvl
								.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAdmApp4 = templateAdmin
								.getTemplateQuery(4);
						templateQueryAdmApp4.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdmApp5 = templateAdmin
								.getTemplateQuery(5);
						templateQueryAdmApp5.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdmApp6 = templateAdmin
								.getTemplateQuery(6);
						templateQueryAdmApp6.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdmApp7 = templateAdmin
								.getTemplateQuery(7);
						templateQueryAdmApp7.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdmApp8 = templateAdmin
								.getTemplateQuery(8);
						templateQueryAdmApp8.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdmApp9 = templateAdmin
								.getTemplateQuery(9);
						templateQueryAdmApp9.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAdmApp10 = templateAdmin
								.getTemplateQuery(10);
						templateQueryAdmApp10.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					/*
					// Add approval link -pass parameters to the link

					String[] link_param_adm = new String[3];
					String[] link_label_adm = new String[3];
					// String applicationType = "TYD";
					String applicationAppAdminType = "TravelClaimAdmin";
					link_param_adm[0] = applicationAppAdminType + "#" + trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "A" + "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_param_adm[1] = applicationAppAdminType + "#" + trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "R" + "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_param_adm[2] = applicationAppAdminType + "#" + trvlClmAdminApprvl.getUserEmpId() + "#"
							+ trvlClmAdminApprvl.getTrvlAppCode() + "#" + "B" + "#" + "..." + "#"
							+ trvlClmAdminApprvl.getTmsClmAppId() + "#" + "1";
					link_label_adm[0] = "Approve";
					link_label_adm[1] = "Reject";
					link_label_adm[2] = "Send Back";*/

					// configure the actual contents of the template
					templateAdmin.configMailAlert();
					//templateAdmin.addOnlineLink(request, link_param_adm, link_label_adm);
					
					String acknowledgeWorkFlowRequires =model.skipAcknoledgementWorkFlow(trvlClmAdminApprvl.getTrvlEmpId());
					System.out.println("acknowledgeWorkFlowRequires   "+acknowledgeWorkFlowRequires);
					
					if(acknowledgeWorkFlowRequires.equals("Y"))
					{
						try {
							String claimApplicationId = "";
							String travelApplicationId = "";
							String travelApplicationCode = "";
							String applicantId = "";
							String applicantName = "";
							String parameterQuery = " SELECT EXP_APPID,EXP_TRVL_APPID,EXP_TRVL_APPCODE,EXP_APP_EMPID, "
									+ " HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_MNAME||' '|| EMP_LNAME FROM TMS_CLAIM_APPL "
									+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_CLAIM_APPL.EXP_APP_EMPID= HRMS_EMP_OFFC.EMP_ID)"
									+ " WHERE EXP_APPID="
									+ trvlClmAdminApprvl.getTmsClmAppId();
							Object[][] parameterObj = model.getSqlModel()
									.getSingleResult(parameterQuery);
							if (parameterObj != null && parameterObj.length > 0) {
								claimApplicationId = String
										.valueOf(parameterObj[0][0]);
								travelApplicationId = String
										.valueOf(parameterObj[0][1]);
								travelApplicationCode = String
										.valueOf(parameterObj[0][2]);
								applicantId = String.valueOf(parameterObj[0][3]);
								applicantName = String.valueOf(parameterObj[0][4]);
							}
						
							
							String message =  "	Travel Claim Application of "+applicantName +" is forwarded for your Acknowledgement";
							alertlink = "/TMS/TravelExpDisbrsmnt_callViewAck.action"; 
							linkParam = "tmsClmAppId=" + claimApplicationId
									+ "&trvlAppId=" + travelApplicationId
									+ "&trvlAppCode=" + travelApplicationCode+"&cailmAppId="+claimApplicationId;
									
							templateAdmin.sendProcessManagerAlertWithdraw(acknowledgementId,
									"Travel Claim", "A", trvlClmAdminApprvl.getTmsClmAppId(), "1", linkParam,
									alertlink, message, actualStataus, "", trvlClmAdminApprvl.getUserEmpId(), "", "");
							
					 	
							
							/*templateAdmin.sendProcessManagerAlert("",
									"Travel Claim", "A", trvlClmAdminApprvl
											.getTmsClmAppId(), "1", linkParam,
									alertlink, actualStataus, "", "0", "",
									acknowledgementId, trvlClmAdminApprvl
											.getUserEmpId());*/
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						sendProcessMangerAlertToAccountant(trvlClmAdminApprvl.getTmsClmAppId(), accountCode, trvlClmAdminApprvl.getTrvlEmpId(), trvlClmAdminApprvl.getUserEmpId());
					}
			
					try {
						String[] link_parameter = new String[1];
						String[] link_labels = new String[1];
						String applicationType1 = "Travel";
						link_parameter[0] = applicationType1 + "#" + authId
								+ "#applicationDtls#";
						String link = "/TMS/AdvClmDisbursement_input.action";
						//link= PPEncrypter.encrypt(link);
						System.out.println("applicationDtls  ..." + link);
						link_parameter[0] += link;
						link_labels[0] = "Claim Disbursement";
						templateAdmin.addOnlineLink(request, link_parameter,
								link_labels);
					} catch (Exception e) {
						// TODO: handle exception
					}

					// call for sending the email
					templateAdmin.sendApplicationMail();

					// clear the template
					templateAdmin.clearParameters();

					// terminate template
					templateAdmin.terminate();

					/** ******************************* */

				} else if (msgFlag.equals("R")) {
					String accountantId = "";
					String accountantQuery = "  SELECT AUTH_ACCOUNTENT  FROM TMS_MANG_AUTH_HDR  WHERE AUTH_MAIN_SCHL_ID="
							+ trvlClmAdminApprvl.getUserEmpId();

					Object[][] accountantObj = model.getSqlModel()
							.getSingleResult(accountantQuery);

					if (accountantObj != null && accountantObj.length > 0) {
						accountantId = String.valueOf(accountantObj[0][0]);
					}

					/** ********************************* */

					/**
					 * * Create instance of EmailTemplateBody
					 */

					EmailTemplateBody template = new EmailTemplateBody();

					// Initiate template
					template.initiate(context, session);

					// Set respective template name
					template
							.setEmailTemplate("Travel Claim Application Admin approved/rejected/sent back");

					// call compulsory for set the queries of template
					template.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQuery1.setParameter(1, trvlClmAdminApprvl
								.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);// To EMAIL
						templateQuery2.setParameter(1, trvlClmAdminApprvl.getTrvlEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, trvlClmAdminApprvl
								.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery5 = template
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery6 = template
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery7 = template
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery8 = template
								.getTemplateQuery(8);
						templateQuery8.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdm9 = template
								.getTemplateQuery(9);
						templateQueryAdm9.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						EmailTemplateQuery templateQueryAdm10 = template
								.getTemplateQuery(10);
						templateQueryAdm10.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*try {
						EmailTemplateQuery templateQueryAdm11 = template
						.getTemplateQuery(11);
						templateQueryAdm11.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}*/

					// configure the actual contents of the template
					template.configMailAlert();
					
					
					String actualStataus="Rejected";
					String  linkParam ="applnStatus=R&status=process&claimApplnCode="+trvlClmAdminApprvl.getTmsClmAppId() ;
					String  alertlink ="/TMS/TravelClaim_trvlClaimDraftDtl.action";
					
				 
				 
					template.sendProcessManagerAlert(trvlClmAdminApprvl.getUserEmpId(),
							"Travel Claim", "I",  trvlClmAdminApprvl.getTmsClmAppId(), "1",
							linkParam, alertlink, actualStataus,  trvlClmAdminApprvl.getTmsClmAppId(),
							"0", "", trvlClmAdminApprvl.getTrvlEmpId(),	trvlClmAdminApprvl.getUserEmpId());

					// call for sending the email
					template.sendApplicationMail();

					// clear the template
					template.clearParameters();

					// terminate template
					template.terminate();

					/** ******************************* */

					addActionMessage("Application Rejected successfully");
				} else if (msgFlag.equals("B")) {
					String accountantId = "";
					String accountantQuery = "  SELECT AUTH_ACCOUNTENT  FROM TMS_MANG_AUTH_HDR  WHERE AUTH_MAIN_SCHL_ID="
							+ trvlClmAdminApprvl.getUserEmpId();

					Object[][] accountantObj = model.getSqlModel()
							.getSingleResult(accountantQuery);

					if (accountantObj != null && accountantObj.length > 0) {
						accountantId = String.valueOf(accountantObj[0][0]);
					}

					/** ********************************* */

					/**
					 * * Create instance of EmailTemplateBody
					 */

					EmailTemplateBody template = new EmailTemplateBody();

					// Initiate template
					template.initiate(context, session);

					// Set respective template name
					template
							.setEmailTemplate("Travel Claim Application Admin approved/rejected/sent back");

					// call compulsory for set the queries of template
					template.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQuery1.setParameter(1, trvlClmAdminApprvl.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);// To EMAIL
						templateQuery2.setParameter(1, trvlClmAdminApprvl.getTrvlEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, trvlClmAdminApprvl
								.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery5 = template
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery6 = template
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery7 = template
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQuery8 = template
								.getTemplateQuery(8);
						templateQuery8.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						EmailTemplateQuery templateQueryAdm9 = template
								.getTemplateQuery(9);
						templateQueryAdm9.setParameter(1, trvlClmAdminApprvl
								.getTmsClmAppId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					// configure the actual contents of the template
					template.configMailAlert();
			 		
					try {
						String actualStataus = "SentBack";
						String alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";
						String linkParam = "applnId="
								+ trvlClmAdminApprvl.getTmsClmAppId()
								+ "&applnCode="
								+ trvlClmAdminApprvl.getTmsClmAppId()
								+ "&applnStatus=B&applnEmpId="
								+ trvlClmAdminApprvl.getTrvlEmpId()
								+ "&claimApplnCode="
								+ trvlClmAdminApprvl.getTmsClmAppId()
								+ "&status=underprocess";
						template.sendProcessManagerAlert("", "Travel Claim",
								"A", trvlClmAdminApprvl.getTmsClmAppId(), "1",
								linkParam, alertlink, actualStataus,
								trvlClmAdminApprvl.getTrvlEmpId(), "", "",
								trvlClmAdminApprvl.getTrvlEmpId(),
								trvlClmAdminApprvl.getUserEmpId());
						alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";
						linkParam = "applnStatus="
								+ trvlClmAdminApprvl.getTmsClmAppId()
								+ "&claimApplnCode="
								+ trvlClmAdminApprvl.getTmsClmAppId()
								+ "&status=process";
						template.sendProcessManagerAlert(trvlClmAdminApprvl
								.getUserEmpId(), "Travel Claim", "I",
								trvlClmAdminApprvl.getTmsClmAppId(), "1",
								linkParam, alertlink, actualStataus,
								trvlClmAdminApprvl.getTrvlEmpId(), "0", "", "",
								trvlClmAdminApprvl.getUserEmpId());
					} catch (Exception e) {
						// TODO: handle exception
					}
					// call for sending the email
					template.sendApplicationMail();

					// clear the template
					template.clearParameters();

					// terminate template
					template.terminate();

					/** ******************************* */
					addActionMessage("Application sent back successfully");
				}
			} else {
				addActionMessage("Application can't be saved");

			}
			model.callStatus(trvlClmAdminApprvl, "P", request);
			model.terminate();
			if (msgFlag.equals("A") || msgFlag.equals("R")
					|| msgFlag.equals("B")) {
				trvlClmAdminApprvl.setPen("true");
				trvlClmAdminApprvl.setApprvd("false");
				trvlClmAdminApprvl.setRetrned("false");
			}
			// imp ....set the status here....
			trvlClmAdminApprvl.setStatusSave("P");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (trvlClmAdminApprvl.getSource().equals("mymessages")) {
			return "mymessages";
		}  else {
			return "success";
		}
		

	}

	public String back() {
		TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
		model.initiate(context, session);
		String expAppId = request.getParameter("expAppId");
		model.view(trvlClmAdminApprvl, request, expAppId);
		model.terminate();
		return "trvlClmApprView";

	}

	public void viewAttachedProof() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");

			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/upload/" + poolName
					+ "/Travel/" + fileName;

			oStream = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}

		// return null;
	}
	
	/*
	 * function to show budget expenditure 30-Dec-2010
	 */
	public void setBudgetExpenditure(String expAppId) {
		double cost = 0.0d;
		try {
			TravelProcessModel trvlprcmodel = new TravelProcessModel();
			trvlprcmodel.initiate(context, session);
			cost = trvlprcmodel.getApproximateBudget(expAppId);
			trvlClmAdminApprvl.setBudgetExpenditure(Utility.twoDecimals(cost));
			//System.out.println("trvlClmAdminApprvl.setBudgetExpenditure = "+cost);
			trvlprcmodel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public String viewSplittedExpenditure(){
		String[] splittedAmt = new String[3];
		try {
			String claimId = request.getParameter("tmsClmAppId");
			System.out.println("######### claimId ############ : " + claimId);
			TravelProcessModel processModel = new TravelProcessModel();
			processModel.initiate(context, session);
			splittedAmt = processModel.getSplitedActualExpenditure(claimId);
			trvlClmAdminApprvl.setLodgeExpenditureAmount(String.valueOf(splittedAmt[0]));
			trvlClmAdminApprvl.setTravelExpenditureAmount(String.valueOf(splittedAmt[1]));
			trvlClmAdminApprvl.setClaimExpenditureAmount(String.valueOf(splittedAmt[2]));
			processModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trvlClmAdmExpenditure";
	}
	
	
	
	
	public void sendProcessMangerAlertToAccountant(String applCode,String accountCode,String empCode,String approver)
	{
		System.out.println("applCode  "+applCode);
		System.out.println("accountCode  "+accountCode);
		System.out.println("empCode  "+empCode);
		System.out.println("approver  "+approver);
		
		try {
			TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
			model.initiate(context, session);
			String approverNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + approver;
			Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverNameQuery);
			String empNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + empCode;
			Object[][] empCodeObj = model.getSqlModel().getSingleResult(
					empNameQuery);
			Properties alertProp;
			FileInputStream alertFin;
			ResourceBundle bundle = ResourceBundle.getBundle("globalMessages");
			alertFin = new FileInputStream(bundle.getString("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String claimApplicationId = "";
			String claimApplicationStatus = "";
			String applicationId = "";
			String applicationCode = "";
			String parameterQuery = " SELECT 	EXP_APPID,CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_STATUS END NEW_STATUS "
					+ "  ,EXP_TRVL_APPID	,EXP_TRVL_APPCODE "
					+ " FROM TMS_CLAIM_APPL  "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
					+ " WHERE EXP_APPID=" + applCode;
			Object[][] parameterQueryObj = model.getSqlModel().getSingleResult(
					parameterQuery);
			if (parameterQueryObj != null && parameterQueryObj.length > 0) {
				claimApplicationId = String.valueOf(parameterQueryObj[0][0]);
				claimApplicationStatus = String
						.valueOf(parameterQueryObj[0][1]);
				applicationId = String.valueOf(parameterQueryObj[0][2]);
				applicationCode = String.valueOf(parameterQueryObj[0][3]);

			}
			String applicantID =empCode;
			
			String employeeName = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][0])
					: "";
			String approverName = (approverObj != null && approverObj.length > 0) ? String
					.valueOf(approverObj[0][0])
					: "";
			String message = alertProp.getProperty("disburseAlertMessgae");
			message = message.replace("<#EMPLOYEE#>", employeeName);
			message = message.replace("<#APPROVER_NAME#>", approverName);
			String alertlink = "/TMS/TravelExpDisbrsmnt_callView.action";
			String linkParam = "tmsClmAppId=" + claimApplicationId
					+ "&tmsClmStatus=" + claimApplicationStatus + "&trvlAppId="
					+ applicationId + "&trvlAppCode=" + applicationCode
					+ "&disStatus=";
			template.sendProcessManagerAlertWithdraw(accountCode,
					"Travel Claim", "A", claimApplicationId, "1", linkParam,
					alertlink, message, "Approved", applicantID, approver, "",
					"");
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID,CENTER_NAME,RANK_NAME"
				+ " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";

		query += "	ORDER BY EMP_ID ASC ";
		
				

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "searchemptoken", "searchempName","searchempId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action
}
