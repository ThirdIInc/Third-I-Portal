package org.struts.action.TravelManagement.TravelClaim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClaimApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.AdvCaimDisbModel;
import org.paradyne.model.TravelManagement.TravelClaim.TmsClaimApplicationModel;
import org.paradyne.model.TravelManagement.TravelProcess.TravelProcessModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 * 
 */
public class TmsClaimApplicationAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	TmsClaimApplication claimApp;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsClaimApplicationAction.class);

	public void prepare_local() throws Exception {
		claimApp = new TmsClaimApplication();
		claimApp.setMenuCode(973);
	}

	public Object getModel() {
		return claimApp;
	}

	public TmsClaimApplication getClaimApp() {
		return claimApp;
	}

	public void setClaimApp(TmsClaimApplication claimApp) {
		this.claimApp = claimApp;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);
		setApplicationDate();
		model.getBlockedApplications(claimApp, request, claimApp.getUserEmpId());
		model.getScheduledApplications(claimApp, request, claimApp.getUserEmpId());
		model.getSubmitRecords(claimApp, request, "P", claimApp.getUserEmpId());
		model.terminate();
		prepare_withLoginProfileDetails();
		claimApp.setListType("sch");
		claimApp.setApplnFor("");
		claimApp.setApplnStatus("");
		claimApp.setApplnId("");
		claimApp.setApplnCode("");
		claimApp.setTmsExpType("");
		if (!(claimApp.getClaimDueDays().equals("0"))) {
			claimApp.setClaimDueDaysFlag(true);
		}
		return SUCCESS;
	}

	/*
	 * TO SEND UNBLOCK REQUEST
	 * 
	 */
	public String sendUnblockRequest() throws Exception {
		// Update the status from A or C to Z
		// fire update query
		// send mail to higher authority(2nd Approver)
		// find higher authority Id, send mail, online approval, and set Status
		// to 'W' after approval

		try {

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);

			String adminId = "";
			String higherAuthId = "";
			String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ claimApp.getUserEmpId().trim() + ")";// branch Id
			Object[][] adminObj = model.getSqlModel().getSingleResult(
					adminQuery);

			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = "(" + String.valueOf(adminObj[0][1]) +
				// ")";
			}
			adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
			// Id
			adminObj = model.getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = String.valueOf(adminObj[0][1]);
			}
			String applicationid = request.getParameter("appId");
			String applicationCode = request.getParameter("appCode");

			boolean result = model.updateSendUnblockRequestStatus(claimApp,
					applicationid, applicationCode);

			if (result) {
				addActionMessage("Unblock request sent successfully.");
			}

			if (adminId != null && adminId.length() > 0) {

				higherAuthId = adminId;
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("Travel block claim application");
				template.getTemplateQueries();
				try {
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);// FROM
					templateQuery1.setParameter(1, claimApp.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);// To
					templateQuery2.setParameter(1, higherAuthId);

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applicationid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applicationid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, higherAuthId);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					System.out.println("Application Code : "
							+ claimApp.getApplnCode());
					templateQuery7.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String[] link_param = new String[2];
				String[] link_label = new String[2];
				// String applicationType = "TYD";
				String applicationType = "TravelClaimUnblock";

				System.out.println("Application ID-------"+claimApp.getApplnId());
				System.out.println("------------------------>"+claimApp.getAppCode());
				link_param[0] = applicationType + "#"
						+ claimApp.getUserEmpId().trim() + "#"
						+ applicationid + "#" + "A" + "#"
						+ "..." + "#" + higherAuthId + "#" + "1" + "#" +applicationCode;

				link_param[1] = applicationType + "#"
						+ claimApp.getUserEmpId().trim() + "#"
						+ applicationid + "#" + "R" + "#"
						+ "..." + "#" + higherAuthId + "#" + "1" + "#" +applicationCode;

				link_label[0] = " Unblock Request ";
				link_label[1] = " Reject Request ";

				// configure the actual contents of the template
				template.configMailAlert();
				
				
				String link = "/TMS/UnblockTravelClaim_input.action";
				
			
				template.sendProcessManagerAlert(higherAuthId,
					"Travel Claim", "A", applicationCode, "1", "", link,
					"Pending", "", "", "", "", claimApp.getUserEmpId());
				
				String linkApp = "/TMS/TravelClaim_input.action";
				template.sendProcessManagerAlert(claimApp.getUserEmpId(),
						"Travel Claim", "I", applicationCode, "1", "", linkApp,
						"Pending", "", "", "", "", claimApp.getUserEmpId());
				
				
				
				template.addOnlineLink(request, link_param, link_label);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input();

	}

	/**
	 * This method is used to create a new travel application
	 * 
	 * 
	 * @throws Exception
	 */
	public String addNew() throws Exception {

		try {
			getNavigationPanel(2);
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			// model.removeExistingApplicants(trvlApp, request);//remove
			// existing
			// apps

			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				String dataPath = getText("data_path") + "/upload" + poolName
						+ "/Travel/";
				// logger.info("data path " + dataPath);
				claimApp.setDataPath(dataPath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());

			setApplicationDate();
			logger.info("--empId--" + claimApp.getUserEmpId());
			claimApp.setAddNewFlag("true");
			claimApp.setDraftFlag("false");
			claimApp.setApplnFor("");
			claimApp.setApplnStatus("");
			claimApp.setApplnId("");
			claimApp.setApplnCode("");
			claimApp.setClaimApplnCode("");
			claimApp.setEligibleAmountFlag(false);
			claimApp.setCityGradeFlag(false);

			// model.setDropDownValueList(claimApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dtl";
	}

	public String trvlClaimDtl() throws Exception {
		String status = request.getParameter("status");
		try {
			
			System.out.println("status == status == : " + status);
			claimApp.setHiddenStatus(status);
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			/*String appId="SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getApplnId().trim();
			Object appIdObj[][] = model.getSqlModel().getSingleResult(appId);*/
			String travlAppId=claimApp.getApplnId().trim();
			
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				String dataPath = getText("data_path") + "/upload" + poolName
						+ "/Travel/";
				// logger.info("data path " + dataPath);
				claimApp.setDataPath(dataPath);

			} catch (Exception e) {
				e.printStackTrace();
			}

			claimApp.setSchFlag("true");
			model.getSchTrvlClaimDtl(claimApp, claimApp.getApplnEmpId(), claimApp.getApplnInitId());
			model.trvlClaimDraftData(claimApp, request);
			model.getDefaultPolicyCurrency(claimApp);
			claimApp.setTmsExpType("T");

			if (model.checkArrngdFlags(claimApp))
				claimApp.setTmsExpType("T");
			else
				claimApp.setTmsExpType("V");

			claimApp.setEnableAll("true");
			claimApp.setAddNewFlag("true");
			if (claimApp.getClaimApplnCode().equals("")) {
				model.getRatingParameters(claimApp, "0",travlAppId);
				model.getTourReportDetails(claimApp, "0");
			} else {
				model.getRatingParameters(claimApp, claimApp
						.getClaimApplnCode(),travlAppId);
				model.getTourReportDetails(claimApp, claimApp
						.getClaimApplnCode());
			}
			model.terminate();
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED ========> " + e);
		}

		if (status.equals("_F")) {
			getNavigationPanel(7);
			claimApp.setShowFlag(false);
		}

		if (status.equals("A") || status.equals("C")) {
			getNavigationPanel(1);
		}
		setNavigationPanel();

		return "dtl";
	}

	public String updatePolicyViolatedStatus() {
		try {
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			model.updatePolicyViolatedStatus(claimApp);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dtl";
	}

	public String trvlClaimDraftDtl() throws Exception {
		try {
			String source = request.getParameter("src");
			String travlAppId="";
			claimApp.setSource(source);
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			String checkIsProcess = request.getParameter("status");
			String appId="SELECT EXP_TRVL_APPID,EXP_TRVL_APPCODE FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getClaimApplnCode().trim();
			Object appIdObj[][] = model.getSqlModel().getSingleResult(appId);
			if(appIdObj!=null && appIdObj.length>0){
				claimApp.setApplnId(String.valueOf(appIdObj[0][0]));
				claimApp.setApplnCode(String.valueOf(appIdObj[0][1]));
				travlAppId = String.valueOf(appIdObj[0][0]);
			}
			//claimApp.setApplnCode(travlAppId);
			String statusQUery = "SELECT CASE WHEN APPL_STATUS='F' THEN '_'||APPL_STATUS ELSE APPL_STATUS END"  
								+ " FROM TMS_APPLICATION WHERE APPL_ID= "
								+ " (SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID= "+ claimApp.getClaimApplnCode().trim()+")";
			Object statusObj[][] = model.getSqlModel().getSingleResult(statusQUery);
			if (statusObj != null && statusObj.length > 0) {
				claimApp.setHiddenStatus(String.valueOf(statusObj[0][0]));
			}
			model.setApproverComments(claimApp,travlAppId);
			try {
				String poolName = String.valueOf(session.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				String dataPath = getText("data_path") + "/upload" + poolName + "/Travel/";
				claimApp.setDataPath(dataPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.trvlClaimDraftData(claimApp, request);
			if (claimApp.getClaimApplnCode().equals("")) {
				model.getRatingParameters(claimApp, "0",travlAppId);
				model.getTourReportDetails(claimApp, "0");
			} else {
				model.getRatingParameters(claimApp, claimApp.getClaimApplnCode(),travlAppId);
				model.getTourReportDetails(claimApp, claimApp.getClaimApplnCode());
			}
			if (checkIsProcess.equals("process")) {
				claimApp.setHiddenStatus("0");
			}
			model.getDefaultPolicyCurrency(claimApp);
			setNavigationPanel();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dtl";
	}

	private void setNavigationPanel() {

		TmsClaimApplicationModel model=null;
		try {
			model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			String travlAppId="";
			Object appIdObj[][] =null;
			if(!claimApp.getClaimApplnCode().trim().equals("")){
			String appId="SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getClaimApplnCode().trim();
			 appIdObj = model.getSqlModel().getSingleResult(appId);
			}else{
				travlAppId=claimApp.getApplnId();
			}
			
			if(appIdObj!=null && appIdObj.length>0){
				travlAppId=String.valueOf(appIdObj[0][0]);
			}
			if (claimApp.getHiddenStatus().equals("_F")) {
				getNavigationPanel(7);
				claimApp.setShowFlag(false);
			}
			if (claimApp.getHiddenStatus().equals("A")
					|| claimApp.getHiddenStatus().equals("C")
					|| claimApp.getHiddenStatus().equals("_W")) {
				getNavigationPanel(1);
			}
			if (claimApp.getApplnStatus().equals("N")) {
				getNavigationPanel(2);
			}
			if (claimApp.getApplnStatus().equals("P")
					|| claimApp.getApplnStatus().equals("A")) {
				getNavigationPanel(3);
				claimApp.setShowFlag(false);
			}
			if (claimApp.getApplnStatus().equals("B")) {
				getNavigationPanel(4);
				claimApp.setShowFlag(true);
				//claimApp.setDefaultCurrencyFlag("false");
				claimApp.setApproverListFlag("true");
			}
			System.out.println("claimApp.getClaimApplnCode()-------------"+claimApp.getClaimApplnCode());
			String query = " select EXP_APP_ADMIN_STATUS,EXP_APP_STATUS ,EXP_APP_EMPID from TMS_CLAIM_APPL where EXP_APPID="
					+ travlAppId;
			Object claimstatusObj[][] = model.getSqlModel().getSingleResult(
					query);
			if (claimstatusObj != null && claimstatusObj.length > 0) {
				if (String.valueOf(claimstatusObj[0][0]).equals("B")
						& claimApp.getApplnStatus().equals("A")) {
					getNavigationPanel(4);
					//	claimApp.setApproverListFlag("true");
				}
			}
			String statusQUery = " SELECT APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="
					+ claimApp.getApplnId().trim();
			Object statusObj[][] = model.getSqlModel().getSingleResult(
					statusQUery);
			if (statusObj != null && statusObj.length > 0) {
				if (String.valueOf(statusObj[0][0]).equals("C")) {
					claimApp.setShowBookingDetailsFlag("true");
				}
			}
			if (claimApp.getClaimApplnCode().equals("")) {
				model.getRatingParameters(claimApp, "0",travlAppId);
			} else {
				model.getRatingParameters(claimApp, claimApp
						.getClaimApplnCode(),travlAppId);
			}
			if (claimApp.getHiddenStatus().equals("0")) {
				getNavigationPanel(3);
				claimApp.setShowFlag(false);
			}
			if (claimApp.getApplnStatus().equals("A")
					&& claimApp.getHiddenStatus().equals("A")) {
				getNavigationPanel(4);
				//getNavigationPanel(3);//changes on 16 sep 2011 by vishwambhar
				claimApp.setShowFlag(true);
			}
			if (claimApp.getApplnStatus().equals("A")
					&& claimApp.getHiddenStatus().equals("C")) {
				getNavigationPanel(4);
				claimApp.setShowFlag(true);
			}
			System.out.println("claimApp.getApplnStatus()  "
					+ claimApp.getApplnStatus());
			System.out.println("claimApp.getHiddenStatus()  "
					+ claimApp.getHiddenStatus());
			if (claimApp.getApplnStatus().equals("P")
					&& claimApp.getHiddenStatus().equals("A")) {
				getNavigationPanel(4);
				claimApp.setShowFlag(true);
			}
			if (claimstatusObj != null && claimstatusObj.length > 0) {
				if (String.valueOf(claimstatusObj[0][0]).equals("P")
						&& claimApp.getApplnStatus().equals("A")) {
					getNavigationPanel(3);
					//claimApp.setApproverListFlag("true");
					claimApp.setShowFlag(false);
				}

				if (String.valueOf(claimstatusObj[0][0]).equals("P")
						&& String.valueOf(claimstatusObj[0][1]).equals("P")) {
					getNavigationPanel(3);
					claimApp.setShowFlag(false);
				}

				if (String.valueOf(claimstatusObj[0][1]).equals("M")) {
					getNavigationPanel(4);
					claimApp.setShowFlag(true);
				}
			}
			
			model.setApproverComments(claimApp,travlAppId);
			
		 
			if(claimApp.getApplnStatus().equals("M")&& claimApp.getHiddenStatus().equals("0"))
			{
				getNavigationPanel(3);
				//claimApp.setShowFlag(true);
			}
			
			setRatingsOnRefresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();

	}

	private void setNavigationPanel_Old() {

		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);
		String appId="SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getApplnId().trim();
		Object appIdObj[][] = model.getSqlModel().getSingleResult(appId);
		String travlAppId="";
		if(appIdObj!=null && appIdObj.length>0){
			travlAppId=String.valueOf(appIdObj[0][0]);
		}
		System.out.println("claimApp.getApplnStatus()            "
				+ claimApp.getApplnStatus());

		System.out.println("claimApp.getHiddenStatus()            "
				+ claimApp.getHiddenStatus());

		if ((claimApp.getApplnStatus().equals("") || claimApp.getApplnStatus()
				.equals("N"))
				&& (claimApp.getHiddenStatus().equals("A")
						|| claimApp.getHiddenStatus().equals("C") || claimApp
						.getHiddenStatus().equals("_W"))) {
			if (claimApp.getApplnStatus().equals("")
					|| claimApp.getHiddenStatus().equals("_W")) {
				System.out.println("1  ");
				getNavigationPanel(1);
			}
			if ((claimApp.getApplnStatus().equals(""))
					&& claimApp.getHiddenStatus().equals("A")) {
				System.out.println("2  ");
				getNavigationPanel(1);
			}
			if ((claimApp.getApplnStatus().equals("N"))
					&& claimApp.getHiddenStatus().equals("A")) {
				System.out.println("3 ");
				getNavigationPanel(3);
			}
			if (claimApp.getHiddenStatus().equals("C")) {
				System.out.println("3  ");
				getNavigationPanel(2);
			}

			if (claimApp.getApplnStatus().equals("N")
					&& claimApp.getHiddenStatus().equals("C")) {
				System.out.println("3  ");
				getNavigationPanel(4);
			}

		} else if (claimApp.getApplnStatus().equals("P")
				&& (claimApp.getHiddenStatus().equals("A") || claimApp
						.getHiddenStatus().equals("C"))) {
			System.out.println("in second else if  ");
			if (claimApp.getHiddenStatus().equals("A")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(5);
			}
			claimApp.setShowFlag(false);

		} else if (claimApp.getApplnStatus().equals("P")
				&& claimApp.getHiddenStatus().equals("_F")) {
			getNavigationPanel(6);
			claimApp.setShowFlag(false);
		}

		/*
		 * else if (claimApp.getApplnStatus().equals("N")&&
		 * claimApp.getHiddenStatus().equals("C")) { getNavigationPanel(4); }
		 * else if (claimApp.getApplnStatus().equals("P")&&
		 * claimApp.getHiddenStatus().equals("C")) {
		 * claimApp.setShowFlag(false); getNavigationPanel(5); } else
		 * if(claimApp.getApplnStatus().equals("P")) {
		 * claimApp.setShowFlag(false); }
		 * 
		 * else if (claimApp.getHiddenStatus().equals("A")) {
		 * getNavigationPanel(1);
		 *  } else if (claimApp.getHiddenStatus().equals("C")) {
		 * getNavigationPanel(2); } else if
		 * (claimApp.getApplnStatus().equals("P")&&
		 * claimApp.getHiddenStatus().equals("A")) { getNavigationPanel(6);
		 * claimApp.setShowFlag(false); } else if
		 * (claimApp.getHiddenStatus().equals("_W")) { getNavigationPanel(1); }
		 */

		if (claimApp.getClaimApplnCode().equals("")) {
			model.getRatingParameters(claimApp, "0",travlAppId);
		} else {
			model.getRatingParameters(claimApp, claimApp.getClaimApplnCode(),travlAppId);
		}
		model.terminate();
	}

	public String updateStatus() throws Exception {
		getNavigationPanel(1);
		boolean flag = false;
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);
		flag = model.updateStatus(claimApp);
		if (flag) {
			addActionMessage(getMessage("save"));
		} else {
			addActionMessage(getMessage("duplicate"));
		}
		input();
		model.terminate();
		return "success";
	}

	public String f9TrvlPurpose() throws Exception {

		String query = "SELECT  PURPOSE_NAME,PURPOSE_ID FROM HRMS_TMS_PURPOSE "
				+ " WHERE PURPOSE_STATUS='A' ORDER BY PURPOSE_NAME";

		String[] headers = { "Purpose Name" };

		String[] headerWidth = { "20" };

		String[] fieldNames = { "travelPurpose", "travelPurposeId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9TrvlType() throws Exception {

		String query = "SELECT  LOCATION_TYPE_NAME,LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
				+ " WHERE LOCATION_TYPE_STATUS='A' ORDER BY LOCATION_TYPE_NAME";

		String[] headers = { "Travel type" };

		String[] headerWidth = { "20" };

		String[] fieldNames = { "travelType", "travelTypeId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9ExpenseType() throws Exception {
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);
		String policyCode = model.getTravelPolicyCode(request
				.getParameter("gradeId"), claimApp.getTrvlStartDate());

		String query = "  SELECT EXP_CATEGORY_NAME , EXP_CATEGORY_ID FROM HRMS_TMS_EXP_CATEGORY "
				+ " INNER JOIN  TMS_POLICY_EXPENSE_DTL ON( TMS_POLICY_EXPENSE_DTL.POLICY_EXP_CAT_ID=HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID)"
				+ " WHERE EXP_CATEGORY_STATUS='A' AND POLICY_ID="
				+ policyCode
				+ " order by EXP_CATEGORY_ID";
		model.terminate();
		String[] headers = { "Expense type" };

		String[] headerWidth = { "20" };

		String[] fieldNames = { "expenseType", "expenseTypeId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "TravelClaim_setExpDtls.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";

	}

	public String addExpenseDtl_old() throws Exception {
		try {

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);

			setApplicationDate();
			System.out.println("Add expense : " + claimApp.getApplnStatus());

			String[] srNo = request.getParameterValues("srNo");
			String[] expDtlId = request.getParameterValues("expDtlId");
			String[] expItId = request.getParameterValues("expItId");
			String[] expenseDateIt = request
					.getParameterValues("expenseDateIt");
			String[] expenseTypeIt = request
					.getParameterValues("expenseTypeIt");
			String[] expenseTypeIdIt = request
					.getParameterValues("expenseTypeIdIt");
			String[] particularsIt = request
					.getParameterValues("particularsIt");
			String[] eligibleAmtIt = request
					.getParameterValues("eligibleAmtIt");
			String[] expenseAmtIt = request.getParameterValues("expenseAmtIt");
			String[] proofIt = request.getParameterValues("proofIt");
			String[] proofRequiredIt = request
					.getParameterValues("proofRequiredIt");
			String[] proofName = request.getParameterValues("proofName");
			String[] ittproofName = request.getParameterValues("ittproofName");
			String[] itteratorProofNameForSave = request
					.getParameterValues("itteratorProofNameForSave");
			String[] policyViolationText = request
					.getParameterValues("policyViolationTextIt");
			String[] currencyExpenseAmtItr = request.getParameterValues("currencyExpenseAmtItr");
			// previous values of iterator from database
			String itteratorExpenseTypeId = claimApp.getExpenseTypeIdIt();
			String itteratorExpenseType = claimApp.getExpenseTypeIt();
			String itteratorEligibleAmt = claimApp.getEligibleAmtIt();
			String itteratorexpenseAmt = claimApp.getExpenseAmtIt();
			String itteratorExpenseDate = claimApp.getExpenseDateIt();

			// Newly entered values.
			String[] expenseTypeId = request
					.getParameterValues("expenseTypeId");
			String[] expenseDate = request.getParameterValues("expenseDate");
			String[] expenseAmt = request.getParameterValues("expenseAmt");

			try {
				if (expenseTypeId != null && expenseTypeId.length > 0) {
					String[] splitExpenseType = null;
					String[] splitExpenseDate = null;
					for (int i = 0; i < expenseTypeId.length; i++) {

						if (itteratorExpenseTypeId.length() > 1) {
							splitExpenseDate = itteratorExpenseDate.split(",");
							splitExpenseType = itteratorExpenseTypeId
									.split(",");
							for (int j = 0; j < splitExpenseDate.length; j++) {
								if ((String.valueOf(splitExpenseDate[j]).trim()
										.equals(expenseDate[i]))
										&& (String.valueOf(splitExpenseType[j])
												.trim()
												.equals(expenseTypeId[i]))) {
									addActionMessage("You are not allowd to enter expense amount for "
											+ "\n"
											+ claimApp.getExpenseType()
											+ " on "
											+ splitExpenseDate[j]
											+ ".");
									setItteratorData();
									break;
								} else {
									model.addExpenseDtl(claimApp, request,
											srNo, expenseDateIt, expenseTypeIt,
											expenseTypeIdIt, particularsIt,
											expenseAmtIt, proofIt,
											proofRequiredIt, expDtlId, expItId,
											eligibleAmtIt, proofName,
											ittproofName,
											itteratorProofNameForSave,
											policyViolationText, currencyExpenseAmtItr);
								}
							}

						} else if ((itteratorExpenseDate.equals(expenseDate[i]))
								&& (itteratorExpenseTypeId
										.equals(expenseTypeId[i]))) {
							addActionMessage("You are not allowd to enter expense amount for "
									+ "\n"
									+ itteratorExpenseType
									+ " on "
									+ itteratorExpenseDate + ".");
							setItteratorData();
						} else {
							model.addExpenseDtl(claimApp, request, srNo,
									expenseDateIt, expenseTypeIt,
									expenseTypeIdIt, particularsIt,
									expenseAmtIt, proofIt, proofRequiredIt,
									expDtlId, expItId, eligibleAmtIt,
									proofName, ittproofName,
									itteratorProofNameForSave,
									policyViolationText, currencyExpenseAmtItr);
						}
					}

				} else {
					model.addExpenseDtl(claimApp, request, srNo, expenseDateIt,
							expenseTypeIt, expenseTypeIdIt, particularsIt,
							expenseAmtIt, proofIt, proofRequiredIt, expDtlId,
							expItId, eligibleAmtIt, proofName, ittproofName,
							itteratorProofNameForSave, policyViolationText, currencyExpenseAmtItr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());

			if (claimApp.getSchFlag().equals("true")) {
				model.getSchTrvlClaimDtl(claimApp, claimApp.getApplnEmpId(),
						claimApp.getApplnInitId());
			}

			setNavigationPanel();

			claimApp.setExpenseDate("");
			claimApp.setEligibleAmt("");
			claimApp.setAmountWithBill("");
			claimApp.setAmountWithoutBill("");
			claimApp.setAtActual("");
			claimApp.setCityGrade("");
			claimApp.setExpenseType("");
			claimApp.setExpenseTypeId("");
			claimApp.setExpenseAmt("");
			claimApp.setParticulars("");
			claimApp.setProofRequired("");
			claimApp.setUploadLocFileName("");
			claimApp.setAddNewFlag("true");
			claimApp.setEnableAll("true");
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dtl";
	}

	public String addExpenseDtl() throws Exception {
		try {

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			setApplicationDate();
			String[] srNo = request.getParameterValues("srNo");
			String[] expDtlId = request.getParameterValues("expDtlId");
			String[] expItId = request.getParameterValues("expItId");
			String[] expenseDateIt = request.getParameterValues("expenseDateIt");
			String[] expenseTypeIt = request.getParameterValues("expenseTypeIt");
			String[] expenseTypeIdIt = request.getParameterValues("expenseTypeIdIt");
			String[] particularsIt = request.getParameterValues("particularsIt");
			String[] eligibleAmtIt = request.getParameterValues("eligibleAmtIt");
			String[] expenseAmtIt = request.getParameterValues("expenseAmtIt");
			String[] currencyExpenseAmtItr = request.getParameterValues("currencyExpenseAmtItr");
			String[] proofIt = request.getParameterValues("proofIt");
			String[] proofRequiredIt = request.getParameterValues("proofRequiredIt");
			String[] proofName = request.getParameterValues("proofName");
			String[] ittproofName = request.getParameterValues("ittproofName");
			String[] itteratorProofNameForSave = request.getParameterValues("itteratorProofNameForSave");
			String[] policyViolationText = request.getParameterValues("policyViolationTextIt");

			// previous values of iterator from database
			String itteratorExpenseTypeId = claimApp.getExpenseTypeIdIt();
			String itteratorExpenseType = claimApp.getExpenseTypeIt();
			String itteratorEligibleAmt = claimApp.getEligibleAmtIt();
			String itteratorexpenseAmt = claimApp.getExpenseAmtIt();
			String itteratorExpenseDate = claimApp.getExpenseDateIt();

			// Newly entered values.
			String[] expenseTypeId = request.getParameterValues("expenseTypeId");
			String[] expenseDate = request.getParameterValues("expenseDate");
			String[] expenseAmt = request.getParameterValues("expenseAmt");

			try {
				if (expenseTypeId != null && expenseTypeId.length > 0) {
					String[] splitExpenseType = null;
					String[] splitExpenseDate = null;
					for (int i = 0; i < expenseTypeId.length; i++) {

						if (itteratorExpenseTypeId.length() > 1) {
							splitExpenseDate = itteratorExpenseDate.split(",");
							splitExpenseType = itteratorExpenseTypeId
									.split(",");
							for (int j = 0; j < splitExpenseDate.length; j++) {
								if ((String.valueOf(splitExpenseDate[j]).trim()
										.equals(expenseDate[i]))
										&& (String.valueOf(splitExpenseType[j])
												.trim()
												.equals(expenseTypeId[i]))) {
									addActionMessage("You are not allowd to enter expense amount for "
											+ "\n"
											+ claimApp.getExpenseType()
											+ " on "
											+ splitExpenseDate[j]
											+ ".");
									setItteratorData();
									break;
								} else {
									model.addExpenseDtl(claimApp, request,
											srNo, expenseDateIt, expenseTypeIt,
											expenseTypeIdIt, particularsIt,
											expenseAmtIt, proofIt,
											proofRequiredIt, expDtlId, expItId,
											eligibleAmtIt, proofName,
											ittproofName,
											itteratorProofNameForSave,
											policyViolationText, currencyExpenseAmtItr);
								}
							}

						} else if ((itteratorExpenseDate.equals(expenseDate[i]))
								&& (itteratorExpenseTypeId
										.equals(expenseTypeId[i]))) {
							addActionMessage("You are not allowd to enter expense amount for "
									+ "\n"
									+ itteratorExpenseType
									+ " on "
									+ itteratorExpenseDate + ".");
							setItteratorData();
						} else {
							model.addExpenseDtl(claimApp, request, srNo,
									expenseDateIt, expenseTypeIt,
									expenseTypeIdIt, particularsIt,
									expenseAmtIt, proofIt, proofRequiredIt,
									expDtlId, expItId, eligibleAmtIt,
									proofName, ittproofName,
									itteratorProofNameForSave,
									policyViolationText, currencyExpenseAmtItr);
						}
					}

				} else {
					model.addExpenseDtl(claimApp, request, srNo, expenseDateIt,
							expenseTypeIt, expenseTypeIdIt, particularsIt,
							expenseAmtIt, proofIt, proofRequiredIt, expDtlId,
							expItId, eligibleAmtIt, proofName, ittproofName,
							itteratorProofNameForSave, policyViolationText, currencyExpenseAmtItr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());

			if (claimApp.getSchFlag().equals("true")) {
				model.getSchTrvlClaimDtl(claimApp, claimApp.getApplnEmpId(),
						claimApp.getApplnInitId());
			}
			model.getDefaultPolicyCurrency(claimApp);
			claimApp.setExpenseDate("");
			claimApp.setEligibleAmt("");
			claimApp.setAmountWithBill("");
			claimApp.setAmountWithoutBill("");
			claimApp.setAtActual("");
			claimApp.setCityGrade("");
			claimApp.setExpenseType("");
			claimApp.setExpenseTypeId("");
			claimApp.setExpenseAmt("");
			claimApp.setParticulars("");
			claimApp.setProofRequired("");
			claimApp.setUploadLocFileName("");
			claimApp.setAddNewFlag("true");
			claimApp.setEnableAll("true");
			setNavigationPanel();

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dtl";
	}

	public String save() throws Exception {
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		try {
			ReportingModel reptmodel = new ReportingModel();
			reptmodel.initiate(context, session);
			Object rptObj[][] = reptmodel.generateEmpFlow(claimApp.getUserEmpId(), "TYD", 1);

			if (rptObj != null && rptObj.length > 0) {
				getNavigationPanel(1);
				boolean flag = false;
				String[] expenseDateIt = request.getParameterValues("expenseDateIt");
				String[] expenseTypeIt = request.getParameterValues("expenseTypeIt");
				String[] expenseTypeIdIt = request.getParameterValues("expenseTypeIdIt");
				String[] particularsIt = request.getParameterValues("particularsIt");
				String[] eligibleAmtIt = request.getParameterValues("eligibleAmtIt");
				String[] expenseAmtIt = request.getParameterValues("expenseAmtIt");
				String[] proofIt = request.getParameterValues("ittproofName");
				String[] proofRequiredIt = request.getParameterValues("proofRequiredIt");
				String buttonType = request.getParameter("buttonType");
				String removeData = request.getParameter("removeData");
				String itteratorProofNameForSave[] = request.getParameterValues("itteratorProofNameForSave");
				String[] policyViolationTextIt = request.getParameterValues("policyViolationTextIt");
				String[] currencyExpenseAmtItr = request.getParameterValues("currencyExpenseAmtItr");
				model.initiate(context, session);

				boolean remDatRes = model.delExpDtls(removeData);
				// boolean result = isApplicationBlocked(claimApp.getGradId(),
				// claimApp.getExpAppDateDraft(), claimApp.getTrvlEndDate());
				// Send Application Mail

				if (claimApp.getClaimApplnCode().equals("")) {
					// Save
					flag = model.saveSch(claimApp, expenseDateIt,
							expenseTypeIt, expenseTypeIdIt, particularsIt,
							eligibleAmtIt, expenseAmtIt, proofIt,
							proofRequiredIt, buttonType, rptObj,
							itteratorProofNameForSave, policyViolationTextIt, currencyExpenseAmtItr);

					if (buttonType.equals("N")){
						sendProcessManagerAlertDraft();
					}
				 
					saveRatingDetails();
					saveTourReport();
					if (flag) {
						if (buttonType.equals("P")) {
							sendApplicationMail(claimApp, String.valueOf(rptObj[0][0]));
							addActionMessage("Application sent for approval successfully.");
						} else if (buttonType.equals("N")) {
							addActionMessage(getMessage("save"));
						}
					} else {
						addActionMessage(getMessage("duplicate"));
					}

				} else {
					// update query
					flag = model.update(claimApp, expenseDateIt, expenseTypeIt,
							expenseTypeIdIt, particularsIt, eligibleAmtIt,
							expenseAmtIt, proofIt, proofRequiredIt, buttonType,
							rptObj, itteratorProofNameForSave,
							policyViolationTextIt, currencyExpenseAmtItr);
					if (buttonType.equals("N"))
					{
						sendProcessManagerAlertDraft();
					}
					saveRatingDetails();
					saveTourReport();
					if (flag) {
						if (buttonType.equals("P")) {
							sendApplicationMail(claimApp, String
									.valueOf(rptObj[0][0]));
							addActionMessage("Application sent for approval successfully.");

						} else if (buttonType.equals("N")) {
							addActionMessage(getMessage("update"));
						}
					} else {
						addActionMessage(getMessage("duplicate"));
					}

				}

				/*
				 * boolean isBlocked = false; if (rptObj != null &&
				 * rptObj.length > 0) { if (buttonType.equals("P")) { boolean
				 * result = isApplicationBlocked(claimApp.getGradId(),
				 * claimApp.getExpAppDateDraft(), claimApp .getTrvlEndDate());
				 * if (!result) { isBlocked = true; addActionMessage(" This
				 * application is blocked. \n So you need to send request for
				 * unblock the application."); } } } if (!isBlocked) {
				 * System.out.println("claimApp.getSchFlag()-----------------------------"+claimApp.getSchFlag());
				 * if (claimApp.getSchFlag().equals("true")) {
				 * 
				 * 
				 *  } } // ADD NEW APPLICATION else { if
				 * (claimApp.getClaimApplnCode().equals("")) { flag =
				 * model.save(claimApp, expenseDateIt, expenseTypeIt,
				 * expenseTypeIdIt, particularsIt, eligibleAmtIt, expenseAmtIt,
				 * proofIt, proofRequiredIt, buttonType, rptObj,
				 * itteratorProofNameForSave);
				 * 
				 * if (flag) { if (buttonType.equals("P")) {
				 * sendApplicationMail(claimApp, String .valueOf(rptObj[0][0]));
				 * addActionMessage("Application sent for approval
				 * successfully."); } else if (buttonType.equals("N") ||
				 * buttonType.equals("B")) {
				 * addActionMessage(getMessage("save")); } } else {
				 * addActionMessage(getMessage("duplicate")); } } else { flag =
				 * model.update(claimApp, expenseDateIt, expenseTypeIt,
				 * expenseTypeIdIt, particularsIt, eligibleAmtIt, expenseAmtIt,
				 * proofIt, proofRequiredIt, buttonType, rptObj,
				 * itteratorProofNameForSave);
				 * 
				 * if (flag) { if (buttonType.equals("P")) { // Send Application
				 * Mail sendApplicationMail(claimApp, String
				 * .valueOf(rptObj[0][0])); addActionMessage("Application sent
				 * for approval successfully."); } else if
				 * (buttonType.equals("N") || buttonType.equals("B")) {
				 * addActionMessage(getMessage("update")); } } else {
				 * addActionMessage(getMessage("duplicate")); } } } // if
				 * (result) {}// if not blocked ends here // else { //
				 * addActionMessage(" This application is blocked. \n So you
				 * need to // send request for unblock the application."); // }
				 */

				reptmodel.terminate();
			} else {
				addActionMessage("Reporting structure not define for employee");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		input();
		model.terminate();
		if (claimApp.getSource().equals("mymessages")) {
			return "mymessages";
		}  else {
			return SUCCESS;
		}
	}

	// krishna
	public String setExpDtls() {
		try {

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			Object appIdObj[][]=null;
			String travlAppId="";
			if(!claimApp.getClaimApplnCode().trim().equals("")){
			String appId="SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getClaimApplnCode().trim();
			 appIdObj = model.getSqlModel().getSingleResult(appId);
			}
			
			if(appIdObj!=null && appIdObj.length>0){
				travlAppId=String.valueOf(appIdObj[0][0]);
			}else{
				travlAppId=claimApp.getApplnId();
			}
			model.setEligibleAmountAccordingToPolicy(claimApp);

			String[] srNo = request.getParameterValues("srNo");
			String[] expDtlId = request.getParameterValues("expDtlId");
			String[] expItId = request.getParameterValues("expItId");
			String[] expenseDateIt = request
					.getParameterValues("expenseDateIt");
			String[] expenseTypeIt = request
					.getParameterValues("expenseTypeIt");
			String[] expenseTypeIdIt = request
					.getParameterValues("expenseTypeIdIt");
			String[] particularsIt = request
					.getParameterValues("particularsIt");
			String[] eligibleAmtIt = request
					.getParameterValues("eligibleAmtIt");
			String[] expenseAmtIt = request.getParameterValues("expenseAmtIt");
			String[] proofIt = request.getParameterValues("ittproofName");
			String[] proofRequiredIt = request
					.getParameterValues("proofRequiredIt");
			String[] ittproofName = request.getParameterValues("ittproofName");
			String[] itteratorProofNameForSave = request
					.getParameterValues("itteratorProofNameForSave");
			String[] policyViolationTextIt = request
					.getParameterValues("policyViolationTextIt");
			String[] currencyExpenseAmtItr = request
			.getParameterValues("currencyExpenseAmtItr");

			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());

			model.setPreviousExpenseDtl(claimApp, request, srNo, expenseDateIt,
					expenseTypeIt, expenseTypeIdIt, particularsIt,
					eligibleAmtIt, expenseAmtIt, proofIt, proofRequiredIt,
					expDtlId, expItId, ittproofName, itteratorProofNameForSave,
					policyViolationTextIt, currencyExpenseAmtItr);
			claimApp.setCurrencyExpenseAmt(claimApp.getCurrencyExpenseAmt());
			claimApp.setCurrencyEmployeeAdvance(claimApp.getCurrencyEmployeeAdvance());
			claimApp.setDefaultCurrencyFlag(claimApp.getDefaultCurrencyFlag());
			
			claimApp.setEnableAll("true");
			claimApp.setAddNewFlag("true");

			try {
				model.setDropDownValueList(claimApp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (claimApp.getClaimApplnCode().equals("")) {
				model.getRatingParameters(claimApp, "0",travlAppId);
			} else {
				model.getRatingParameters(claimApp, claimApp
						.getClaimApplnCode(),travlAppId);
			}

			setNavigationPanel();
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dtl";
	}

	// krishna
	public String removeExpDtls() throws Exception {
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);

		boolean removeExp = model.removeExpDtls(claimApp, request);
		// setItteratorData();
		if (removeExp) {
			addActionMessage("Record removed successfully.");
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}
		model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());

		if (claimApp.getApplnCode().equals("")) {
			setApplicationDate();
			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());
		} else {
			if (!(claimApp.getSchFlag().equals("true"))) {
				model.draftEmpDtl(claimApp);
			} else {
				model.getSchTrvlClaimDtl(claimApp, claimApp.getApplnEmpId(), claimApp.getApplnInitId());
			}
		}
		claimApp.setEnableAll("true");
		claimApp.setAddNewFlag("true");
		setNavigationPanel();
		model.terminate();
		return "dtl";
	}

	public String getApprovedList() throws Exception {
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);

		if (claimApp.isGeneralFlag()) {// GENERAL USER

			model.getApprovedList(claimApp, request, "A", claimApp
					.getUserEmpId());

		} else {// ADMIN

			model.getApprovedList(claimApp, request, "A", claimApp
					.getUserEmpId());

		}
		claimApp.setListType("approved");
		model.terminate();
		return SUCCESS;
	}

	public String getClosedList() throws Exception {
		TmsClaimApplicationModel model = new TmsClaimApplicationModel();
		model.initiate(context, session);

		if (claimApp.isGeneralFlag()) {// GENERAL USER
			model
					.getClosedList(claimApp, request, "C", claimApp
							.getUserEmpId());
		} else {// ADMIN
			model
					.getClosedList(claimApp, request, "C", claimApp
							.getUserEmpId());
		}
		claimApp.setListType("closed");
		model.terminate();
		return SUCCESS;
	}

	public String addMultipleProof() {

		try {
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName, claimApp);
			model.setProofList(srNo, proofName, claimApp);
			model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());
			claimApp.setUploadLocFileName("");
			model.setEligibleAmountAccordingToPolicy(claimApp);
			setItteratorData();
			setExpDtls();

			claimApp.setAddNewFlag("true");
			claimApp.setEnableAll("true");

			setNavigationPanel();

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
		}

		return "dtl";
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

	public String removeUploadFile() {

		try {

			String[] srNo = request.getParameterValues("proofSrNo"); // serial

			String[] proofName = request.getParameterValues("proofName");// keep

			// String[] proofFileName =
			// request.getParameterValues("proofFileName");
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			// trvlClaimDtl();
			setExpDtls();
			// model.removeUploadFile(srNo, proofName, proofFileName, claimApp);
			model.removeUploadFile(srNo, proofName, claimApp);

			claimApp.setSchFlag("true");
			claimApp.setAddNewFlag("true");
			// claimApp.setUploadFlag("true");
			setNavigationPanel();

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}

		return "dtl";
	}

	public String report() {
		try {
			getNavigationPanel(3);
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();

			model.initiate(context, session);
			model.getReport(claimApp, response);

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String f9Customer() throws Exception {

		String query = " SELECT TRAVEL_CUST_NAME,TRAVEL_CUST_ID FROM TMS_TRAVEL_CUSTOMER ORDER BY TRAVEL_CUST_ID";

		String[] headers = { "Customer" };

		String[] headerWidth = { "30" };

		String[] fieldNames = { "customerName", "customerId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9Project() throws Exception {

		String query = " SELECT PROJECT_NAME,PROJECT_ID FROM TMS_TRAVEL_PROJECT ORDER BY PROJECT_ID";

		String[] headers = { "Project" };

		String[] headerWidth = { "30" };

		String[] fieldNames = { "project", "projectId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/*
	 * public void uploadflagfunction() { try {
	 * if(claimApp.getProofRequired().equals("true")) {
	 * claimApp.setUploadFlag("true"); } else { claimApp.setUploadFlag("false"); } }
	 * catch (Exception e) {e.printStackTrace();}
	 * 
	 * //return "dtl"; }
	 */

	public String f9ResponsiblePerson() {

		String id = claimApp.getRowId();

		String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC";
		query += " WHERE 1=1 AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(EMP_FNAME) ASC ";

		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "15", "30" };
		logger.info("id>>>>" + id);

		String[] fieldNames = { "responsibleEmpTokenItt" + id,
				"responsibleEmpItt" + id, "responsibleEmpIdItt" + id };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public void setApplicationDate() {
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			claimApp.setExpAppDateDraft(sysDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setItteratorData() {

		try {
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			String appId="SELECT  EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="+claimApp.getApplnId().trim();
			Object appIdObj[][] = model.getSqlModel().getSingleResult(appId);
			String travlAppId="";
			if(appIdObj!=null && appIdObj.length>0){
				travlAppId=String.valueOf(appIdObj[0][0]);
			}
			String[] srNo = request.getParameterValues("srNo");
			String[] expDtlId = request.getParameterValues("expDtlId");
			String[] expItId = request.getParameterValues("expItId");
			String[] expenseDateIt = request
					.getParameterValues("expenseDateIt");
			String[] expenseTypeIt = request
					.getParameterValues("expenseTypeIt");
			String[] expenseTypeIdIt = request
					.getParameterValues("expenseTypeIdIt");
			String[] particularsIt = request
					.getParameterValues("particularsIt");
			String[] eligibleAmtIt = request
					.getParameterValues("eligibleAmtIt");
			String[] expenseAmtIt = request.getParameterValues("expenseAmtIt");
			String[] proofIt = request.getParameterValues("proofIt");
			String[] proofRequiredIt = request
					.getParameterValues("proofRequiredIt");
			String[] proofName = request.getParameterValues("proofName");
			String[] proofNameItt = request.getParameterValues("ittproofName");
			String[] itteratorProofNameForSave = request
					.getParameterValues("itteratorProofNameForSave");
			String[] policyViolationTextIt = request
					.getParameterValues("policyViolationTextIt");

			model.setItteratorValues(claimApp, request, srNo, expenseDateIt,
					expenseTypeIt, expenseTypeIdIt, particularsIt,
					expenseAmtIt, proofIt, proofRequiredIt, expDtlId, expItId,
					eligibleAmtIt, proofName, proofNameItt,
					itteratorProofNameForSave, policyViolationTextIt);
			if (claimApp.getClaimApplnCode().equals("")) {
				model.getRatingParameters(claimApp, "0",travlAppId);
			} else {
				model.getRatingParameters(claimApp, claimApp
						.getClaimApplnCode(),travlAppId);
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean sendApplicationMail(TmsClaimApplication claimApp,
			String approverId) {

		/** ********************************* */

		try {
			
			
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(claimApp.getClaimApplnCode(), "Travel Claim");
			processAlerts.terminate();

			/*TO CALCULATE ACTUAL EXPENDITURE*/
			TravelProcessModel processModel = new TravelProcessModel();
			processModel.initiate(context, session);
			double actualExpenditure = processModel
					.getActualExpenditure(claimApp.getClaimApplnCode());
			processModel.terminate();

			/**
			 * * Create instance of EmailTemplateBody
			 */

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);

			String itteratorProofNameForSave[] = request
					.getParameterValues("itteratorProofNameForSave");
			System.out.println("Inside email template................");
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			// Set respective template name
			template
					.setEmailTemplate("Travel Claim Application sent for approval");
			// call compulsory for set the queries of template
			template.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM
				// EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, claimApp.getUserEmpId());

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To
				// EMAIL

				templateQuery2.setParameter(1, approverId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, claimApp.getClaimApplnCode());

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, claimApp.getClaimApplnCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, claimApp.getClaimApplnCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, claimApp.getClaimApplnCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, claimApp.getClaimApplnCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, claimApp.getClaimApplnCode());

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, claimApp.getClaimApplnCode());

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, claimApp.getClaimApplnCode());

			} catch (Exception e) {
				e.printStackTrace();
			}
			// Add approval link -pass parameters to the link
			/*String[] link_param = new String[3];
			String[] link_label = new String[3];
			// String applicationType = "TYD";
			String applicationType = "TravelClaim";
			link_param[0] = applicationType + "#" + claimApp.getUserEmpId()
					+ "#" + claimApp.getClaimApplnCode() + "#" + "A" + "#"
					+ "..." + "#" + approverId + "#" + "1";
			link_param[1] = applicationType + "#" + claimApp.getUserEmpId()
					+ "#" + claimApp.getClaimApplnCode() + "#" + "R" + "#"
					+ "..." + "#" + approverId + "#" + "1";
			link_param[2] = applicationType + "#" + claimApp.getUserEmpId()
					+ "#" + claimApp.getClaimApplnCode() + "#" + "B" + "#"
					+ "..." + "#" + approverId + "#" + "1";
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";*/
			// configure the actual contents of the template
			template.configMailAlert();
			// Add approval link -pass parameters to the link
			
			
			try {
				sendProcessManagerAlertForApplSubmission(claimApp
						.getClaimApplnCode(), approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String alertlink = "/TMS/TravelClmAppvr_callView.action";
			String linkParam = "expAppId=" + claimApp.getClaimApplnCode();
					 
			try {
				template.sendProcessManagerAlert(approverId, "Travel Claim", "A",
						claimApp.getClaimApplnCode(), "1", linkParam, alertlink, "Pending",
						claimApp.getUserEmpId(), "0", "", "",
						claimApp.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			String[] link_parameter = new String[1];
			String[] link_labels = new String[1];
			String applicationType1 = "Travel";
			link_parameter[0] = applicationType1 + "#" + approverId
					+ "#applicationDtls#";

			String link = "/TMS/TravelClmAppvr_callView.action?expAppId="
					+ claimApp.getClaimApplnCode().trim();
			// link= PPEncrypter.encrypt(link);
			System.out.println("applicationDtls  ..." + link);
			link_parameter[0] += link;
			link_labels[0] = "Approve/Send Back";
			template.addOnlineLink(request, link_parameter, link_labels);

			//template.addOnlineLink(request, link_param, link_label);
			// call for sending the email
			Vector vect = null;

			String sqlQuery = " SELECT NVL(EXP_DTL_PROOF,' ') FROM TMS_EXP_DTL "
					+ " WHERE EXP_APPID="
					+ claimApp.getClaimApplnCode().trim()
					+ " AND EXP_DTL_PROOF IS NOT NULL ";

			Object dataObj[][] = model.getSqlModel().getSingleResult(sqlQuery);
			if (dataObj != null && dataObj.length > 0) {
				vect = new Vector();
				for (int i = 0; i < dataObj.length; i++) {

					String[] proofs = String.valueOf(dataObj[i][0]).split(",");
					if (proofs != null && proofs.length > 0) {
						System.out
								.println("in if proofs.length----------------"
										+ proofs.length);
						for (int j = 0; j < proofs.length; j++) {

							System.out.println("in if loop----------------"
									+ proofs[j]);
							vect.add(proofs[j]);
						}
					}
				}
			}

			/*
			 * if (itteratorProofNameForSave != null &&
			 * itteratorProofNameForSave.length > 0) { vect = new Vector(); for
			 * (int i = 0; i < itteratorProofNameForSave.length; i++) { String
			 * proofList = itteratorProofNameForSave[i];
			 * if(!proofList.equals("") && proofList.length()>0) { String[]
			 * proofs = proofList.split(","); if(proofs!=null && proofs.length
			 * >0) { for (int j = 0; j < proofs.length; j++) {
			 * vect.add(proofs[j]); } } } } }
			 */

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}

			if (vect != null && vect.size() > 0) {

				String[] path = new String[vect.size()];
				for (int i = 0; i < vect.size(); i++) {
					path[i] = getText("data_path") + "/upload/" + poolName
							+ "/Travel/" + String.valueOf(vect.get(i));
				}
				// template.sendApplMailWithAttachment(path);
				template.sendApplMailWithAttachment(path);
			} else {
				template.sendApplicationMail();
			}
			// clear the template
			template.clearParameters();
			// terminate template
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/** ******************************* */
		// addActionMessage("Record sent for approval successfully.");
		return true;

	}

	/*
	 * 
	 * public String sendUnlockedRequest() throws Exception { String highAuth =
	 * ""; TmsClaimApplicationModel model = new TmsClaimApplicationModel();
	 * model.initiate(context, session); System.out.println("Claim Application
	 * Code=========>" + claimApp.getClaimApplnCode()); String statusquery =
	 * "UPDATE TMS_CLAIM_APPL SET EXP_IS_BLOCKED='L' WHERE EXP_APPID= " +
	 * claimApp.getClaimApplnCode();
	 * model.getSqlModel().singleExecute(statusquery);
	 * 
	 * String highAuthQuery = " SELECT AUTH_POLICY_VIOLN_ESCLN_ID FROM
	 * TMS_MANG_AUTH_HDR " + " WHERE AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND
	 * AUTH_BRNCH_ID IN (" + claimApp.getBranchId() + ")";// branch // Id
	 * Object[][] higherAuthObj = model.getSqlModel().getSingleResult(
	 * highAuthQuery);
	 * 
	 * if (higherAuthObj != null && higherAuthObj.length > 0) { highAuth =
	 * String.valueOf(higherAuthObj[0][0]); } highAuthQuery = " SELECT
	 * AUTH_POLICY_VIOLN_ESCLN_ID FROM TMS_MANG_AUTH_HDR " + " WHERE
	 * AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch Id higherAuthObj =
	 * model.getSqlModel().getSingleResult(highAuthQuery); if (higherAuthObj !=
	 * null && higherAuthObj.length > 0) { highAuth =
	 * String.valueOf(higherAuthObj[0][0]); } logger.info("highAuth..." +
	 * highAuth);
	 *//** ********************************* */
	/*
	 *//**
	 * * Create instance of EmailTemplateBody
	 */
	/*
	 * String itteratorProofNameForSave[] = request
	 * .getParameterValues("itteratorProofNameForSave");
	 * 
	 * System.out.println("Inside email template................");
	 * EmailTemplateBody template = new EmailTemplateBody();
	 * 
	 * template.initiate(context, session); // Set respective template name
	 * template.setEmailTemplate("Travel Claim Application sent for approval"); //
	 * call compulsory for set the queries of template
	 * template.getTemplateQueries();
	 * 
	 * try { // get the query as per number EmailTemplateQuery templateQuery1 =
	 * template.getTemplateQuery(1);// FROM // EMAIL // set the parameter of
	 * queries templateQuery1.setParameter(1, claimApp.getUserEmpId()); } catch
	 * (Exception e) { e.printStackTrace();} try {
	 * 
	 * EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);// To //
	 * EMAIL
	 * 
	 * templateQuery2.setParameter(1, highAuth); } catch (Exception e) { e.printStackTrace();} 
	 * try { EmailTemplateQuery templateQuery3 =
	 * template.getTemplateQuery(3); templateQuery3.setParameter(1,
	 * claimApp.getClaimApplnCode()); } catch (Exception e) { e.printStackTrace();} 
	 * try { EmailTemplateQuery templateQuery4 =
	 * template.getTemplateQuery(4); templateQuery4.setParameter(1,
	 * claimApp.getClaimApplnCode()); } catch (Exception e) { e.printStackTrace();}

	 * try { EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
	 * templateQuery5.setParameter(1, claimApp.getClaimApplnCode()); } catch
	 * (Exception e) {e.printStackTrace();}
	 * 
	 * try { EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
	 * templateQuery6.setParameter(1, claimApp.getClaimApplnCode()); } catch
	 * (Exception e) {e.printStackTrace(); }
	 * 
	 * try { EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
	 * templateQuery7.setParameter(1, claimApp.getClaimApplnCode()); } catch
	 * (Exception e) { e.printStackTrace();}
	 * 
	 * try { EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
	 * templateQuery8.setParameter(1, claimApp.getClaimApplnCode()); } catch
	 * (Exception e) {e.printStackTrace();} // Add approval link -pass
	 * parameters to the link
	 * 
	 * String[] link_param = new String[3]; String[] link_label = new String[3]; //
	 * String applicationType = "TYD"; String applicationType = "TravelClaim";
	 * link_param[0] = applicationType + "#" + claimApp.getUserEmpId() + "#" +
	 * claimApp.getClaimApplnCode() + "#" + "A" + "#" + "..." + "#" + highAuth +
	 * "#" + "1"; link_param[1] = applicationType + "#" +
	 * claimApp.getUserEmpId() + "#" + claimApp.getClaimApplnCode() + "#" + "R" +
	 * "#" + "..." + "#" + highAuth + "#" + "1"; link_param[2] = applicationType +
	 * "#" + claimApp.getUserEmpId() + "#" + claimApp.getClaimApplnCode() + "#" +
	 * "B" + "#" + "..." + "#" + highAuth + "#" + "1"; link_label[0] =
	 * "Approve"; link_label[1] = "Reject"; link_label[2] = "Send Back"; //
	 * configure the actual contents of the template template.configMailAlert();
	 * template.addOnlineLink(request, link_param, link_label); // call for
	 * sending the email Vector vect = null; if (itteratorProofNameForSave !=
	 * null && itteratorProofNameForSave.length > 0) { vect = new Vector(); for
	 * (int i = 0; i < itteratorProofNameForSave.length; i++) { String proofList =
	 * itteratorProofNameForSave[i]; String[] proofs = proofList.split(","); for
	 * (int j = 0; j < proofs.length; j++) { vect.add(proofs[j]); } } } String
	 * poolName = String.valueOf(session.getAttribute("session_pool")); if
	 * (!(poolName.equals("") || poolName == null)) { poolName = "/" +
	 * poolName; }
	 * 
	 * if (vect != null && vect.size() > 0) { String[] path = new
	 * String[vect.size()]; for (int i = 0; i < vect.size(); i++) { path[i] =
	 * getText("data_path") + "/upload/" + poolName + "/Travel/" +
	 * String.valueOf(vect.get(i)); } template.sendApplMailWithAttachment(path); }
	 * else { template.sendApplicationMail(); } // call for sending the email
	 * with attachment // template.sendApplMailWithAttachment(String[]
	 * attachFile) // callto set in the list of PMA //
	 * template.sendProcessManagerAlert(String empID, String module, // String
	 * msgType, String applnID, String alertLevel); // call for sending the
	 * email in CC // template.sendApplicationMailToKeepInfo(String[] empId); //
	 * clear the template template.clearParameters(); // terminate template
	 * template.terminate();
	 *//** ******************************* */
	/*
	 * addActionMessage("Record sent for approval successfully.");
	 * 
	 * return input(); }
	 * 
	 * public boolean isApplicationBlocked(String gradeId, String appDate,
	 * String trvlEndDate) {
	 * 
	 * boolean resFlag = false; TmsClaimApplicationModel model = new
	 * TmsClaimApplicationModel(); model.initiate(context, session); String
	 * policyCode = model.getTravelPolicyCode(gradeId, appDate);
	 * System.out.println("Grade ID=======>" + gradeId);
	 * System.out.println("Application Date=======>" + appDate);
	 * System.out.println("Policy Code===============>" + policyCode);
	 * System.out.println("Travel End Date ============>" + trvlEndDate); if
	 * (!policyCode.equals("") && policyCode.length() > 0) { String policyQuery =
	 * "SELECT NVL(MAX_DAYS_SETTLE_TRAVEL_CLAIM,0) FROM TMS_TRAVEL_POLICY WHERE
	 * POLICY_ID=" + policyCode; Object[][] policyObj =
	 * model.getSqlModel().getSingleResult( policyQuery);
	 * System.out.println("Max Settlement Days=============>" +
	 * String.valueOf(policyObj[0][0])); if
	 * (String.valueOf(policyObj[0][0]).equals("0")) { resFlag = true; } else { //
	 * if((days+travel_end_date)<travel_app_date){ // resFlag=true; // } String
	 * query = " SELECT (" + String.valueOf(policyObj[0][0]) + ")+TO_DATE('" +
	 * trvlEndDate + "','DD-MM-YYYY') FROM DUAL " + " WHERE TO_DATE('" + appDate +
	 * "','DD-MM-YYYY')<=(" + String.valueOf(policyObj[0][0]) + ")+TO_DATE('" +
	 * trvlEndDate + "','DD-MM-YYYY')"; Object[][] daysObj =
	 * model.getSqlModel().getSingleResult(query); if (daysObj != null &&
	 * daysObj.length > 0) {
	 * 
	 * resFlag = true; } else { if (!claimApp.getClaimApplnCode().equals("")) {
	 * String blockedQuery = "SELECT EXP_IS_BLOCKED FROM TMS_CLAIM_APPL WHERE
	 * EXP_APPID=" + claimApp.getClaimApplnCode(); Object[][] blockedObj =
	 * model.getSqlModel() .getSingleResult(blockedQuery); if
	 * (String.valueOf(blockedObj[0][0]).equals("U")) { resFlag = true; } else
	 * resFlag = false; } else { resFlag = false; } } } }
	 * 
	 * return resFlag; }
	 */

	public String saveRatingDetails() {

		try {
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);
			String[] ratingId = request.getParameterValues("deskRatingIdItt");
			String[] ratingName = request
					.getParameterValues("deskRatingNameItt");
			String[] ratings = request.getParameterValues("deskRatingItt");
			String[] hotelRatingId = request
					.getParameterValues("hotelRatingIdItt");
			String[] hotelRatingName = request
					.getParameterValues("hotelRatingNameItt");
			String[] hotelRatings = request
					.getParameterValues("hotelRatingItt");
			String[] hotelId = request.getParameterValues("hotelIdItt");

			model.saveRatings(claimApp, request, ratingId, ratingName, ratings,
					hotelRatingId, hotelRatingName, hotelRatings, hotelId);
			/**
			 * For rating updates
			 */
			try {

				TravelProcessModel processMod = new TravelProcessModel();
				processMod.initiate(context, session);
				processMod.updateHotelRating(claimApp.getClaimApplnCode());
				processMod.terminate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setRatingsOnRefresh() {
		try {

			String[] ratingId = request.getParameterValues("deskRatingIdItt");
			String[] ratingName = request
					.getParameterValues("deskRatingNameItt");
			String[] ratings = request.getParameterValues("deskRatingItt");

			if (ratingId != null && ratingId.length > 0) {
				ArrayList<Object> deskRatingList = new ArrayList<Object>();
				for (int i = 0; i < ratingId.length; i++) {
					TmsClaimApplication deskbean = new TmsClaimApplication();
					deskbean.setDeskRatingIdItt(String.valueOf(ratingId[i]));
					deskbean.setDeskRatingNameItt(ratingName[i]);
					deskbean.setDeskRatingItt(String.valueOf(ratings[i]));

					deskRatingList.add(deskbean);
				}
				claimApp.setTravelRatingParameterList(deskRatingList);
			}

			String[] hotelId = request.getParameterValues("hotelIdItt");
			String[] hotelName = request.getParameterValues("hotelNameItt");
			String[] hotelRatingId = request
					.getParameterValues("hotelRatingIdItt");
			String[] hotelRatingName = request
					.getParameterValues("hotelRatingNameItt");
			String[] hotelRatings = request
					.getParameterValues("hotelRatingItt");

			ArrayList<Object> hotelList = new ArrayList<Object>();
			if (hotelId != null && hotelId.length > 0) {
				for (int i = 0; i < hotelId.length; i++) {

					TmsClaimApplication hotelNamebean = new TmsClaimApplication();
					hotelNamebean.setHotelIdItt(String.valueOf(hotelId[i]));
					hotelNamebean.setHotelNameItt(String.valueOf(hotelName[i]));

					ArrayList<Object> hotelRatingList = new ArrayList<Object>();

					if (hotelRatings != null && hotelRatings.length > 0) {

						for (int k = 0; k < hotelRatings.length
								/ hotelId.length; k++) {
							TmsClaimApplication hotelbean = new TmsClaimApplication();
							hotelbean.setHotelRatingIdItt(String
									.valueOf(hotelRatingId[k]));
							hotelbean.setHotelRatingNameItt(String
									.valueOf(hotelRatingName[k]));
							hotelbean.setHotelRatingItt(String
									.valueOf(hotelRatings[k]));

							hotelRatingList.add(hotelbean);
						}
						hotelNamebean
								.setHotelRatingParameterList(hotelRatingList);
					}

					hotelList.add(hotelNamebean);
				}
				claimApp.setHotelNameList(hotelList);

			}

			/* Setting tour report details on refresh*/
			String followUpComments[] = request
					.getParameterValues("followUpCommentsItt");
			String responsibleEmpIds[] = request
					.getParameterValues("responsibleEmpIdItt");
			String responsibleEmpTokens[] = request
					.getParameterValues("responsibleEmpTokenItt");
			String responsibleEmpName[] = request
					.getParameterValues("responsibleEmpItt");
			String targetDates[] = request.getParameterValues("targetDateItt");

			ArrayList<Object> refreshFollowUpList = new ArrayList<Object>();
			if (responsibleEmpIds != null && responsibleEmpIds.length > 0) {

				for (int f = 0; f < responsibleEmpIds.length; f++) {
					TmsClaimApplication refreshFollowUpbean = new TmsClaimApplication();
					refreshFollowUpbean.setResponsibleEmpTokenItt(String
							.valueOf(responsibleEmpTokens[f]));
					refreshFollowUpbean.setResponsibleEmpItt(String
							.valueOf(responsibleEmpName[f]));
					refreshFollowUpbean.setResponsibleEmpIdItt(String
							.valueOf(responsibleEmpIds[f]));
					refreshFollowUpbean.setFollowUpCommentsItt(String
							.valueOf(followUpComments[f]));
					refreshFollowUpbean.setTargetDateItt(String
							.valueOf(targetDates[f]));
					refreshFollowUpList.add(refreshFollowUpbean);
				}
				claimApp.setFollowUpActionList(refreshFollowUpList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String saveTourReport() {

		try {
			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);

			String followUpComments[] = request
					.getParameterValues("followUpCommentsItt");
			String responsibleEmpIds[] = request
					.getParameterValues("responsibleEmpIdItt");
			String responsibleEmpTokens[] = request
					.getParameterValues("responsibleEmpTokenItt");
			String responsibleEmpName[] = request
					.getParameterValues("responsibleEmpItt");
			String targetDates[] = request.getParameterValues("targetDateItt");

			model.saveTourReportDetails(claimApp, request, followUpComments,
					responsibleEmpIds, responsibleEmpTokens,
					responsibleEmpName, targetDates);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = claimApp.getBDtlEmpId();
			String module = "Travel Claim";
			String applnID = claimApp.getClaimApplnCode();
			String level = "1";
			String link = "/TMS/TravelClaim_trvlClaimDraftDtl.action";

			String linkParam = "applnId=" + applnID + "&applnCode=" + applnID
					+ "&applnStatus=N&applnEmpId=" + applicantID
					+ "&claimApplnCode=" + applnID + "&status=underprocess";

			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", claimApp
					.getEmployeeName().trim());
			message = message.replace("<#APPL_TYPE#>", "Travel Claim");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	
	public void sendProcessManagerAlertForApplSubmission(String applicationCode,
			String approverId) {
		try {
			
			/*MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applicationCode, "Travel Claim");
			processAlerts.terminate();
			*/
			
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

			String message = alertProp.getProperty("applSubmissionalertMessage");
			
		 
			message = message.replace("<#MODULE#>", "Travel Claim");
			message = message.replace("<#EMPLOYEE#>", employeeName);
			message = message.replace("<#APPROVER_NAME#>", approverName);
			
		 
			String alertlink = "";
			String linkParam = "";

			String actualStataus = "Pending";
		 
			alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";

			linkParam = "applnId=" + applicationCode + "&applnCode="
					+ applicationCode + "&applnStatus=P&applnEmpId="
					+ applicantID + "&claimApplnCode=" + applicationCode
					+ "&status=underprocess";

			template.sendProcessManagerAlertWithdraw(applicantID, "Travel Claim", "I",
					applicationCode, "1", linkParam, alertlink, message,
					actualStataus, applicantID, approverId, "", "");
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String f9Currency() {
		String query = " SELECT HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE " + 
							" HRMS_CURRENCY.CURRENCY_STATUS = 'A'";	
		String[] headers = { "Currency" };
		String[] headerWidth = {"100"};
		String[] fieldNames = { "currencyExpenseAmt"};
		int[] columnIndex = { 0};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}


}
