package org.struts.action.TravelManagement.TravelClaim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmApproval;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.TmsTrvlClmApprovalModel;
import org.paradyne.model.TravelManagement.TravelProcess.TravelProcessModel;
import org.struts.lib.ParaActionSupport;

public class TmsTrvlClmApprovalAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlClmApprovalAction.class);
	TmsTrvlClmApproval trvlClmApprvl;

	public void prepare_local() throws Exception {
		trvlClmApprvl = new TmsTrvlClmApproval();
		trvlClmApprvl.setMenuCode(975);
	}

	public Object getModel() {
		return trvlClmApprvl;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		model.initiate(context, session);
		model.callStatus(trvlClmApprvl, "P", request);
		trvlClmApprvl.setStatus("P");
		trvlClmApprvl.setNavStatus("P");
		model.terminate();
	}

	public String callStatus() {
		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		if (status.equals("F")) {
			status = "P";
		}
		trvlClmApprvl.setNavStatus(status);
		model.callStatus(trvlClmApprvl, status, request);
		trvlClmApprvl.setStatus(status);
		model.terminate();
		return "success";
	}

	public String input() {
		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		model.initiate(context, session);
		model.callStatus(trvlClmApprvl, "P", request);
		trvlClmApprvl.setStatus("P");
		trvlClmApprvl.setNavStatus("P");
		model.terminate();
		try {
			String clmAppFlag = request.getParameter("clmAppFlag");
			String clmApplStatus = request.getParameter("clmStatus");
			String expAppId = request.getParameter("expAppId");
			if (clmAppFlag == null && clmApplStatus == null) {

			} else {
				if (clmAppFlag != null && !clmAppFlag.equals("null")
						&& !clmAppFlag.equals("")) {
					trvlClmApprvl.setClmAppFlag("Y");
					trvlClmApprvl.setNavStatus("");
				} else
					trvlClmApprvl.setClmAppFlag("");
				if (clmApplStatus != null && !clmApplStatus.equals("null")
						&& !clmApplStatus.equals("")) {
					trvlClmApprvl.setClmApplStatus("CL");
					trvlClmApprvl.setNavStatus("");
				} else
					trvlClmApprvl.setClmApplStatus("");
				callView();
				return "trvlClmApprView";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public String callView() {

		String source = request.getParameter("src");
		//String source =(String) request.getAttribute("src");
		trvlClmApprvl.setSource(source);

		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		// String claimId = request.getParameter("claimId");
		model.initiate(context, session);
		String expAppId = request.getParameter("expAppId");
		trvlClmApprvl.setHiddenApplicationCode(expAppId);
		trvlClmApprvl.setTmsClmAppId(expAppId);
		trvlClmApprvl.setApproverCommentsFlag("true");
		boolean result = model.setApproverComments(trvlClmApprvl);
		if (result) {
			trvlClmApprvl.setApproverListFlag("true");
		}
		setBudgetExpenditure(expAppId);
		setLevelForApplication();
		String query = " SELECT CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_STATUS END, TMS_APPLICATION.APPL_ID "
				+ " FROM  TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " WHERE EXP_APPID="
				+ trvlClmApprvl.getHiddenApplicationCode();
		Object data[][] = model.getSqlModel().getSingleResult(query);

		String travelAppId = "";
		if (data != null && data.length > 0) {
			if (String.valueOf(data[0][0]).equals("A")
					|| String.valueOf(data[0][0]).equals("R")
					|| String.valueOf(data[0][0]).equals("F")) {
				trvlClmApprvl.setApproverCommentsFlag("false");
				if (String.valueOf(data[0][0]).equals("F")) {
					trvlClmApprvl.setRevokeFlag(true);
					trvlClmApprvl.setNavStatus("F");
				}

			} else {
				trvlClmApprvl.setApproverCommentsFlag("true");
			}
			travelAppId = String.valueOf(data[0][1]);
		}

		model.view(trvlClmApprvl, request, expAppId);
		model.getRatingParameters(trvlClmApprvl, expAppId, travelAppId);
		model.getTourReportDetails(trvlClmApprvl, expAppId);
		model.terminate();
		// logger.info("claimId end==" + claimId);
		return "trvlClmApprView";

	}

	public void setLevelForApplication() {
		try {
			TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
			model.initiate(context, session);
			String query = " SELECT APPL_LEVEL FROM TMS_CLAIM_APPL WHERE EXP_APPID="
					+ trvlClmApprvl.getHiddenApplicationCode();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				trvlClmApprvl.setLevel(String.valueOf(levelObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			trvlClmApprvl.setBudgetExpenditure(Utility.twoDecimals(cost));
			trvlprcmodel.terminate();
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

	public String approveRejectSendBackTravelClaimApp() {
		try {
			setLevelForApplication();

			TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
			model.initiate(context, session);
			// trvlClmApprvl.getEmpCode()

			String appStatus = model.approveRejectSendBackTravelClaimApp(
					request, trvlClmApprvl.getTrvlEmpId(), trvlClmApprvl
							.getTmsClmAppId(), trvlClmApprvl.getCheckStatus(),
					trvlClmApprvl.getClmApprCmts(), trvlClmApprvl
							.getUserEmpId(), trvlClmApprvl.getLevel(),
					trvlClmApprvl.getTmsTrvlId());
			if (appStatus.equals("rejected")) {
				addActionMessage("Application rejected");
			} else if (appStatus.equals("sendback")) {
				addActionMessage("Application sent back ");
			} else {

				addActionMessage("Application approved successfully");
			}

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (trvlClmApprvl.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlClmApprvl.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return "claimApprovalJsp";
		}

	}

	public String doFun() {

		try {
			TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
			model.initiate(context, session);
			String msgFlag = trvlClmApprvl.getStatusSave();
			String status = trvlClmApprvl.getCheckStatus().trim();
			boolean result = model.saveApplication(trvlClmApprvl
					.getTmsClmAppId(), status, trvlClmApprvl
					.getTmsApprvrLevel(), trvlClmApprvl.getClmApprCmts(),
					trvlClmApprvl.getTrvlEmpId(), trvlClmApprvl.getUserEmpId(),
					request);
			if (result) {

				if (msgFlag.equals("A")) {
					addActionMessage("Application approved successfully");
				} else if (msgFlag.equals("R")) {
					addActionMessage("Application Rejected successfully");
				} else if (msgFlag.equals("B")) {
					addActionMessage("Application sent back successfully");
				}

				//mail code removed by vishu

			} else {
				addActionMessage("Application can't be saved");

			}
			model.callStatus(trvlClmApprvl, "P", request);
			model.terminate();
			if (msgFlag.equals("A") || msgFlag.equals("R")
					|| msgFlag.equals("B")) {
				trvlClmApprvl.setPen("true");
				trvlClmApprvl.setApprvd("false");
				trvlClmApprvl.setRetrned("false");
			}
			// imp ....set the status here....
			trvlClmApprvl.setStatusSave("P");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (trvlClmApprvl.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "success";

		}

	}

	public void sendmail() {

		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		model.initiate(context, session);
		// * * 1. Travel Claim Application approved/rejected/sent back

		EmailTemplateBody template = new EmailTemplateBody();

		// Initiate template
		template.initiate(context, session);

		// Set respective template name
		template
				.setEmailTemplate("Travel Claim Application approved/rejected/sent back");

		// call compulsory for set the queries of template
		template.getTemplateQueries();

		try {
			// get the query as per number
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQuery1.setParameter(1, trvlClmApprvl.getUserEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);// To EMAIL
			templateQuery2.setParameter(1, trvlClmApprvl.getTrvlEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, trvlClmApprvl.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, trvlClmApprvl.getTmsClmAppId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
			templateQuery10.setParameter(1, trvlClmApprvl.getTmsClmAppId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Add approval link -pass parameters to the link

		String[] link_param = new String[3];
		String[] link_label = new String[3];
		// String applicationType = "TYD";
		String applicationType = "TravelClaim";
		link_param[0] = applicationType + "#" + trvlClmApprvl.getUserEmpId()
				+ "#" + trvlClmApprvl.getTrvlAppCode() + "#" + "A" + "#"
				+ "..." + "#" + trvlClmApprvl.getTmsClmAppId() + "#" + "1";
		link_param[1] = applicationType + "#" + trvlClmApprvl.getUserEmpId()
				+ "#" + trvlClmApprvl.getTrvlAppCode() + "#" + "R" + "#"
				+ "..." + "#" + trvlClmApprvl.getTmsClmAppId() + "#" + "1";
		link_param[2] = applicationType + "#" + trvlClmApprvl.getUserEmpId()
				+ "#" + trvlClmApprvl.getTrvlAppCode() + "#" + "B" + "#"
				+ "..." + "#" + trvlClmApprvl.getTmsClmAppId() + "#" + "1";
		link_label[0] = "Approve";
		link_label[1] = "Reject";
		link_label[2] = "Send Back";

		// configure the actual contents of the template
		template.configMailAlert();
		template.addOnlineLink(request, link_param, link_label);

		// configure the actual contents of the template
		template.configMailAlert();

		// call for sending the email
		template.sendApplicationMail();

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

		// 2. Travel Claim Application Approval to Admin

		String branchQuery = "SELECT RANK.RANK_ID AS ADMIN_RANK_ID,C2.EMP_ID AS ADMIN_ID , AUTH_BRNCH_ID AS AUTH_BRNCH_ID , NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') AS ADMIN_NAME ,"
				+ "	ADMIN.RANK_NAME AS ADMIN_RANK "
				+ "	FROM TMS_MANG_AUTH_HDR "
				+ "	INNER JOIN HRMS_EMP_OFFC C1 ON(C1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID) "
				+ "	LEFT JOIN HRMS_RANK ADMIN ON(ADMIN.RANK_ID=C1.EMP_RANK) "
				+ "	LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID=C1.EMP_RANK) "
				+ "	INNER JOIN HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID) "
				+ "	WHERE AUTH_STATUS='A'";

		String adminId = "";
		String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID  FROM TMS_MANG_AUTH_HDR "
				+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
				+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
				+ trvlClmApprvl.getEmpCode() + ")";// branch Id
		Object[][] adminObj = model.getSqlModel().getSingleResult(adminQuery);

		if (adminObj != null && adminObj.length > 0) {
			adminId = "(" + String.valueOf(adminObj[0][0]) + ")";
		}
		adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID  FROM TMS_MANG_AUTH_HDR "
				+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
		// Id
		adminObj = model.getSqlModel().getSingleResult(adminQuery);
		if (adminObj != null && adminObj.length > 0) {
			adminId = String.valueOf(adminObj[0][0]);
		}

		EmailTemplateBody templateAppAdmin = new EmailTemplateBody();

		// Initiate template
		templateAppAdmin.initiate(context, session);

		// Set respective template name
		templateAppAdmin
				.setEmailTemplate("Travel Claim Application  Approval to Admin");

		// call compulsory for set the queries of template
		templateAppAdmin.getTemplateQueries();

		try {
			// get the query as per number
			EmailTemplateQuery templateQueryAppAdmin1 = templateAppAdmin
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQueryAppAdmin1
					.setParameter(1, trvlClmApprvl.getUserEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryAppAdmin2 = templateAppAdmin
					.getTemplateQuery(2);// To EMAIL
			templateQueryAppAdmin2.setParameter(1, adminId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EmailTemplateQuery templateQueryAppAdmin3 = templateAppAdmin
					.getTemplateQuery(3);
			templateQueryAppAdmin3.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EmailTemplateQuery templateQueryAppAdmin4 = templateAppAdmin
					.getTemplateQuery(4);
			templateQueryAppAdmin4.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQueryAppAdmin5 = templateAppAdmin
					.getTemplateQuery(5);
			templateQueryAppAdmin5.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQueryAppAdmin6 = templateAppAdmin
					.getTemplateQuery(6);
			templateQueryAppAdmin6.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQueryAppAdmin7 = templateAppAdmin
					.getTemplateQuery(7);
			templateQueryAppAdmin7.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQueryAppAdmin8 = templateAppAdmin
					.getTemplateQuery(8);
			templateQueryAppAdmin8.setParameter(1, adminId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EmailTemplateQuery templateQueryAppAdmin9 = templateAppAdmin
					.getTemplateQuery(9);
			templateQueryAppAdmin9.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailTemplateQuery templateQueryAppAdmin10 = template
					.getTemplateQuery(10);
			templateQueryAppAdmin10.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			EmailTemplateQuery templateQueryAppAdmin11 = template
			.getTemplateQuery(11);
			templateQueryAppAdmin11.setParameter(1, trvlClmApprvl
					.getTmsClmAppId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Add approval link -pass parameters to the link

		String[] link_param_app_adm = new String[3];
		String[] link_label_app_adm = new String[3];
		// String applicationType = "TYD";
		String applicationAppAdminType = "TravelClaimAdmin";

		link_param_app_adm[0] = applicationAppAdminType + "#"
				+ trvlClmApprvl.getUserEmpId() + "#"
				+ trvlClmApprvl.getTmsClmAppId() + "#" + "A" + "#" + "..."
				+ "#" + trvlClmApprvl.getTmsTrvlId().trim() + "#" + "1";
		link_param_app_adm[1] = applicationAppAdminType + "#"
				+ trvlClmApprvl.getUserEmpId() + "#"
				+ trvlClmApprvl.getTmsClmAppId() + "#" + "R" + "#" + "..."
				+ "#" + trvlClmApprvl.getTmsTrvlId().trim() + "#" + "1";
		link_param_app_adm[2] = applicationAppAdminType + "#"
				+ trvlClmApprvl.getUserEmpId() + "#"
				+ trvlClmApprvl.getTmsClmAppId() + "#" + "B" + "#" + "..."
				+ "#" + trvlClmApprvl.getTmsTrvlId().trim() + "#" + "1";

		link_label_app_adm[0] = "Approve";
		link_label_app_adm[1] = "Reject";
		link_label_app_adm[2] = "Send Back";

		// configure the actual contents of the template
		templateAppAdmin.configMailAlert();
		templateAppAdmin.addOnlineLink(request, link_param_app_adm,
				link_label_app_adm);

		// configure the actual contents of the template
		// templateAppAdmin.configMailAlert();

		// call for sending the email
		templateAppAdmin.sendApplicationMail();

		// call for sending the email with attachment
		// template.sendApplMailWithAttachment(String[] attachFile)

		// callto set in the list of PMA
		// template.sendProcessManagerAlert(String empID, String module,
		// String msgType, String applnID, String alertLevel);

		// call for sending the email in CC
		// template.sendApplicationMailToKeepInfo(String[] empId);

		// clear the template
		templateAppAdmin.clearParameters();

		// terminate template
		templateAppAdmin.terminate();
	}

	/*
	 * public String back() { TmsTrvlClmApprovalModel model = new
	 * TmsTrvlClmApprovalModel(); model.initiate(context, session); String
	 * travelAppId = request.getParameter("travelAppId"); String travelAppCode =
	 * request.getParameter("travelAppCode"); addActionMessage("travelAppId
	 * back: "+travelAppId); addActionMessage("travelAppCode back:
	 * "+travelAppCode); model.view(trvlClmApprvl, request,travelAppId,
	 * travelAppCode); model.terminate(); return "trvlClmApprView"; }
	 */

	public TmsTrvlClmApproval getTrvlClmApprvl() {
		return trvlClmApprvl;
	}

	public void setTrvlClmApprvl(TmsTrvlClmApproval trvlClmApprvl) {
		this.trvlClmApprvl = trvlClmApprvl;
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
			e.printStackTrace();
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
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

	public String viewSplittedExpenditure() {
		String[] splittedAmt = new String[3];
		try {
			String claimId = request.getParameter("tmsClmAppId");
			TravelProcessModel processModel = new TravelProcessModel();
			processModel.initiate(context, session);
			splittedAmt = processModel.getSplitedActualExpenditure(claimId);
			trvlClmApprvl.setLodgeExpenditureAmount(String
					.valueOf(splittedAmt[0]));
			trvlClmApprvl.setTravelExpenditureAmount(String
					.valueOf(splittedAmt[1]));
			trvlClmApprvl.setClaimExpenditureAmount(String
					.valueOf(splittedAmt[2]));
			processModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "splittedExpenditure";
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

}
