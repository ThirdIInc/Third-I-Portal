package org.struts.action.TravelManagement.TravelClaim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.TreeMap;
import org.paradyne.bean.TravelManagement.TravelClaim.AdvCaimDisb;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClmDisbursement;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.AdvCaimDisbModel;
import org.paradyne.model.TravelManagement.TravelClaim.TmsClmDisbursementModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class ClmAcknowledgementAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ClmAcknowledgementAction.class);
	AdvCaimDisb disb;

	TmsClmDisbursement clmDisbrmnt;

	public TmsClmDisbursement getClmDisbrmnt() {
		return clmDisbrmnt;
	}

	public void setClmDisbrmnt(TmsClmDisbursement clmDisbrmnt) {
		this.clmDisbrmnt = clmDisbrmnt;
	}

	@Override
	public void prepare_local() throws Exception {
		disb = new AdvCaimDisb();
		disb.setMenuCode(974);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return disb;
	}

	public AdvCaimDisb getDisb() {
		return disb;
	}

	public void setDisb(AdvCaimDisb disb) {
		this.disb = disb;
	}

	public String input() {
		try {
			disb.setListType("pending");
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			// CALLS LIST OF PENDING APPLICATIONS
			model.pendAdvDisbList(disb);
			model.terminate();

			//FOR CLAIM LIST ADDED BY REEBA
			TmsClmDisbursementModel claimModel = new TmsClmDisbursementModel();
			claimModel.initiate(context, session);
			claimModel.callStatusAcknowledge(disb, "A", request);
			disb.setStatus("A");
			claimModel.terminate();

		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		return INPUT;
	}

	/**
	 * @author REEBA JOSEPH
	 * @return
	 * @throws Exception
	 */
	public String acceptClaimDisbursement() throws Exception {
		try {

			Object[][] add = null;

			String cailmAppId = request.getParameter("cailmAppId");
			System.out.println("cailmAppId = " + cailmAppId);
			String travelApplId = request.getParameter("travelApplId");
			System.out.println("travelApplId = " + travelApplId);
			String travelApplCode = request.getParameter("travelApplCode");
			System.out.println("travelApplCode = " + travelApplCode);

			String disburseDate = request.getParameter("disburseDate");
			System.out.println("disburseDate = " + disburseDate);

			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);

			//	getSqlModel().singleExecute(insertQuery);

			String updateClaimDisb = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS = 'Q',EXPECTED_DISB_DATE= TO_DATE('"
					+ disburseDate
					+ "','DD-MM-YYYY') WHERE EXP_APPID="
					+ cailmAppId
					+ " AND EXP_TRVL_APPID="
					+ travelApplId
					+ " AND EXP_TRVL_APPCODE=" + travelApplCode;

			boolean result = model.getSqlModel().singleExecute(updateClaimDisb);
			if (result)
				addActionMessage("Claim accepted for disbursement");

			/*    Send Mail to Accountant              */

			//ADDED BY Ganesh FOR ACCOUNTANT EMAIL from Schedular
			String authId = "";
			String Accountant = "";
			String adminQuery = "  SELECT  AUTH_ID ,AUTH_ACCOUNTENT FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ disb.getApplicantId() + ")";//branch Id
			Object[][] adminObj = model.getSqlModel().getSingleResult(
					adminQuery);
			logger.info("getTrvlEmpId======" + disb.getTrvlAppId());
			if (adminObj != null && adminObj.length > 0) {
				authId = String.valueOf(adminObj[0][0]);
				Accountant = String.valueOf(adminObj[0][1]);
			}
			adminQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT,AUTH_SCH_APPROVAL, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";//branch Id
			adminObj = model.getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				authId = String.valueOf(adminObj[0][0]);
				Accountant = String.valueOf(adminObj[0][1]);
			}
			logger.info("Authorisation id======" + authId);
			//ADDED BY REEBA ENDS

			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel Claim";
			processAlerts.removeProcessAlert(String.valueOf(disb
					.getTmsClmAppId()), module);

			EmailTemplateBody templateAdmin = new EmailTemplateBody();

			// Initiate template
			templateAdmin.initiate(context, session);

			// Set respective template name
			templateAdmin
					.setEmailTemplate("Travel Claim Application Schedular to Accountant");

			// call compulsory for set the queries of template
			templateAdmin.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQueryAdmApp1 = templateAdmin
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQueryAdmApp1.setParameter(1, disb.getUserEmpId());
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
				templateQueryAdmApp3.setParameter(1, disb.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQueryAdmApp4 = templateAdmin
						.getTemplateQuery(4);
				templateQueryAdmApp4.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryAdmApp5 = templateAdmin
						.getTemplateQuery(5);
				templateQueryAdmApp5.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryAdmApp6 = templateAdmin
						.getTemplateQuery(6);
				templateQueryAdmApp6.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryAdmApp7 = templateAdmin
						.getTemplateQuery(7);
				templateQueryAdmApp7.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryAdmApp8 = templateAdmin
						.getTemplateQuery(8);
				templateQueryAdmApp8.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryAdmApp9 = templateAdmin
						.getTemplateQuery(9);
				templateQueryAdmApp9.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Add approval link -pass parameters to the link

			String[] link_param_adm = new String[3];
			String[] link_label_adm = new String[3];
			// String applicationType = "TYD";
			String applicationAppAdminType = "TravelClaimAdmin";
			link_param_adm[0] = applicationAppAdminType + "#"
					+ disb.getUserEmpId() + "#" + disb.getTrvlAppCode() + "#"
					+ "A" + "#" + "..." + "#" + disb.getTmsClmAppId() + "#"
					+ "1";
			link_param_adm[1] = applicationAppAdminType + "#"
					+ disb.getUserEmpId() + "#" + disb.getTrvlAppCode() + "#"
					+ "R" + "#" + "..." + "#" + disb.getTmsClmAppId() + "#"
					+ "1";
			link_param_adm[2] = applicationAppAdminType + "#"
					+ disb.getUserEmpId() + "#" + disb.getTrvlAppCode() + "#"
					+ "B" + "#" + "..." + "#" + disb.getTmsClmAppId() + "#"
					+ "1";
			link_label_adm[0] = "Approve";
			link_label_adm[1] = "Reject";
			link_label_adm[2] = "Send Back";

			// configure the actual contents of the template
			templateAdmin.configMailAlert();
			//templateAdmin.addOnlineLink(request, link_param_adm, link_label_adm);

			String actualStataus = "Acknowledge";

			String claimApplicationId = "";
			String claimApplicationStatus = "";
			String applicationId = "";
			String applicationCode = "";

			String parameterQuery = " SELECT 	EXP_APPID,CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_STATUS END NEW_STATUS "
					+ "  ,EXP_TRVL_APPID	,EXP_TRVL_APPCODE "
					+ " FROM TMS_CLAIM_APPL  "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
					+ " WHERE EXP_APPID=" + disb.getTmsClmAppId();
			Object[][] parameterQueryObj = model.getSqlModel().getSingleResult(
					parameterQuery);

			if (parameterQueryObj != null && parameterQueryObj.length > 0) {
				claimApplicationId = String.valueOf(parameterQueryObj[0][0]);
				claimApplicationStatus = String
						.valueOf(parameterQueryObj[0][1]);
				applicationId = String.valueOf(parameterQueryObj[0][2]);
				applicationCode = String.valueOf(parameterQueryObj[0][3]);

			}

			String alertlink = "/TMS/TravelExpDisbrsmnt_callView.action";
			String linkParam = "tmsClmAppId=" + claimApplicationId
					+ "&tmsClmStatus=" + claimApplicationStatus + "&trvlAppId="
					+ applicationId + "&trvlAppCode=" + applicationCode
					+ "&disStatus=";

			templateAdmin
					.sendProcessManagerAlert("", "Travel Claim", "A", disb
							.getTmsClmAppId(), "1", linkParam, alertlink,
							actualStataus, "", "0", "", Accountant, disb
									.getUserEmpId());

			System.out
					.println("disb.getUserEmpId() -----------------------    "
							+ disb.getUserEmpId());

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

			//	addActionMessage("Acknowledgement approved successfully & Send Acknowledge Copy to Accountant.");

			//ADDED BY Ganesh FOR Applicant EMAIL from Schedular

			String accountantId = "";
			String accountantQuery = "  SELECT AUTH_SCH_APPROVAL  FROM TMS_MANG_AUTH_HDR  WHERE AUTH_MAIN_SCHL_ID="
					+ disb.getUserEmpId();

			Object[][] accountantObj = model.getSqlModel().getSingleResult(
					accountantQuery);

			if (accountantObj != null && accountantObj.length > 0) {
				accountantId = String.valueOf(accountantObj[0][0]);
			}

			/**
			 * * Create instance of EmailTemplateBody
			 */

			EmailTemplateBody template = new EmailTemplateBody();

			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template
					.setEmailTemplate("Travel Claim Application Schedular to Applicant");

			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, disb.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, disb.getApplicantId());

			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, disb.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, disb.getTmsClmAppId());
			} catch (Exception e) {
				// TODO: handle exception
			}

			// configure the actual contents of the template
			///	template.configMailAlert();
			//	template.addOnlineLink(request, link_param, link_label);

			// configure the actual contents of the template
			template.configMailAlert();

			// call for sending the email
			template.sendApplicationMail();

			linkParam = "applnStatus=R&status=process&claimApplnCode="
					+ disb.getTmsClmAppId();
			alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";

			template.sendProcessManagerAlert(disb.getUserEmpId(),
					"Travel Claim", "I", disb.getTmsClmAppId(), "1", linkParam,
					alertlink, actualStataus, disb.getApplicantId(), "0", "",
					disb.getApplicantId(), disb.getUserEmpId());

			// call for sending the email with attachment
			// template.sendApplMailWithAttachment(String[] attachFile)

			// callto set in the list of PMA
			// template.sendProcessManagerAlert(String empID, String module,
			// String msgType, String applnID, String alertLevel);

			// call for sending the email in CC
			// template.sendApplicationMailToKeepInfo(String[] empId);

			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();

			//	addActionMessage("Application approved successfully & Send Acknowledge Copy to applicant.");

			model.terminate();

		} catch (Exception e) {
			logger.error("Exception in acceptClaimDisbursement method : " + e);
			e.printStackTrace();
		}//end of try-catch block

		if (disb.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			input();
			return INPUT;
		}

	}// end of method acceptClaimDisbursement

	//removing from list in pendingAdvance.jsp
	public String removeRow() {
		System.out.println("enetr to remove method-----------");
		String tempdate[] = null;
		String code[] = null;

		String empid = disb.getPendingEmpId();

		String applid = request.getParameter("pendingapplId");
		String applCode = request.getParameter("pendingapplCode");
		System.out.println("applid------in removerow-----" + applid);
		System.out.println("applCode------in removerow-----" + applCode);
		System.out.println("empid------in removerow-----" + empid);

		String date[] = request.getParameterValues("itpaymentdate");

		String paymode[] = request.getParameterValues("itpaymentmode");

		String amount[] = request.getParameterValues("itamount");
		String comment[] = request.getParameterValues("itcomment");
		String[] chkno = request.getParameterValues("chkno");
		String[] checkdate = request.getParameterValues("checkdate");
		String[] accountno = request.getParameterValues("accountno");
		String[] bankname = request.getParameterValues("bankname");
		String[] bankid = request.getParameterValues("itbankid");
		String[] rownum = request.getParameterValues("rownum");
		String guestflag = request.getParameter("hiddengustflag");

		//ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");

		for (int i = 0; i < amount.length; i++) {
			System.out.println("amount-----------" + amount[i]);
			System.out.println("chkno-----------" + chkno[i]);
			System.out.println("rownum-----------" + rownum[i]);
		}
		String hiddenchkfld[] = request.getParameterValues("hdeleteSkill");
		AdvCaimDisbModel model = new AdvCaimDisbModel();

		model.initiate(context, session);
		boolean removelist = model.removeJourneyDtls(disb, date, paymode,
				amount, hiddenchkfld, comment, chkno, checkdate, accountno,
				bankname, bankid, rownum, month, year);
		//saveFun();
		model.editpendAdvDisbList(disb, empid, applCode, applid, guestflag,
				request);

		//ADDED BY REEBA
		TreeMap payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);

		//model.callItList(disb);
		model.terminate();
		getNavigationPanel(1);
		return "pendingadvdisburse";

	}

	public String returnHome() {
		try {
			/*disb.setListType("pending");
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			// CALLS LIST OF PENDING APPLICATIONS
			model.pendAdvDisbList(disb);
			model.terminate();*/
			input();
		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		return INPUT;
	}

	//for adding the row
	public String addRow() {
		System.out.println("entry to addrow mehod-----");
		String empid = disb.getPendingEmpId();

		String applid = request.getParameter("pendingapplId");
		String applCode = request.getParameter("pendingapplCode");

		String SrNo[] = request.getParameterValues("SrNo");
		String date[] = request.getParameterValues("itpaymentdate");
		String paymode[] = request.getParameterValues("itpaymentmode");
		String amount[] = request.getParameterValues("itamount");
		String comment[] = request.getParameterValues("itcomment");
		String[] chkno = request.getParameterValues("chkno");
		String[] checkdate = request.getParameterValues("checkdate");
		String[] accountno = request.getParameterValues("accountno");
		String[] bankname = request.getParameterValues("bankname");
		String[] bankid = request.getParameterValues("itbankid");
		String[] rownum = request.getParameterValues("rownum");
		String guestflag = request.getParameter("hiddengustflag");
		//ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		if (!disb.getCheckEdit().equals("")) {
			model.modData(disb, SrNo, date, paymode, chkno, checkdate,
					accountno, bankname, amount, comment, bankid, rownum,
					month, year);
		} else {

			model.addData(disb, SrNo, date, paymode, chkno, checkdate,
					accountno, bankname, amount, comment, bankid, rownum,
					month, year);
		}

		model.editpendAdvDisbList(disb, empid, applCode, applid, guestflag,
				request);
		//ADDED BY REEBA
		TreeMap payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		disb.setPaymentDate("");
		//disb.setPaymentmode("");
		disb.setCheckNumber("");
		disb.setChkDate("");
		disb.setAccount("");
		//disb.setBank("");
		//disb.setBankid("");
		//disb.setAmount("");
		disb.setComment("");
		disb.setCheckEdit("");
		model.terminate();
		getNavigationPanel(1);

		return "pendingadvdisburse";

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

	public String sendBackAcknowledgement() {

		try {
			String claimAppId = request.getParameter("cailmAppId");
			System.out.println("cailmAppId = " + claimAppId);
			String travelApplId = request.getParameter("travelApplId");
			System.out.println("travelApplId = " + travelApplId);
			String travelApplCode = request.getParameter("travelApplCode");
			System.out.println("travelApplCode = " + travelApplCode);
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			boolean flag = model.sendBackAcknowledgementRequest(clmDisbrmnt,
					claimAppId, travelApplId, travelApplCode);
			if (flag) {
				sendProcessManagerAlertSendBack(claimAppId, disb
						.getUserEmpId());
				addActionMessage("Record send back successfully.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (disb.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return input();
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

		String[] fieldNames = { "searchemptoken", "searchempName",
				"searchempId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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

	public void sendProcessManagerAlertSendBack(String applicationCode,
			String approverId) {
		try {
			
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applicationCode, "Travel Claim");
			processAlerts.terminate();
			
			
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			String approverNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + approverId;
			Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverNameQuery);

			String empIdQuery = " SELECT EXP_APP_EMPID ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ " FROM TMS_CLAIM_APPL "
					+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ "WHERE EXP_APPID=" + applicationCode;

			Object[][] empCodeObj = model.getSqlModel().getSingleResult(
					empIdQuery);

			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String applicantID = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][0])
					: "";
			String employeeName = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][1])
					: "";
			String approverName = (approverObj != null && approverObj.length > 0) ? String
					.valueOf(approverObj[0][0])
					: "";

			String message = alertProp.getProperty("applicationAlertMessage");
			
		 
			message = message.replace("<#MODULE#>", "Travel Claim");
			message = message.replace("<#EMPLOYEE_NAME#>", employeeName);
			message = message.replace("<#STATUS#>", "Sent Back");
			message = message.replace("<#APPROVER_NAME#>", approverName);
			
		 
			String alertlink = "";
			String linkParam = "";

			String actualStataus = "SentBack";
		 
			alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";

			linkParam = "applnId=" + applicationCode + "&applnCode="
					+ applicationCode + "&applnStatus=B&applnEmpId="
					+ applicantID + "&claimApplnCode=" + applicationCode
					+ "&status=underprocess";

			template.sendProcessManagerAlertWithdraw(applicantID, "Travel Claim", "A",
					applicationCode, "1", linkParam, alertlink, message,
					actualStataus, applicantID, approverId, "", "");
			
		 
			alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";
			linkParam = "applnStatus=" + applicationCode + "&claimApplnCode="
					+ applicationCode + "&status=process";

			template.sendProcessManagerAlertWithdraw("",
					"Travel Claim", "I", applicationCode, "1", linkParam,
					alertlink, message, actualStataus,applicantID,approverId, "",
					approverId);

			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
