package org.struts.action.TravelManagement.TravelProcess;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlApproval;
import org.paradyne.model.TravelManagement.TravelProcess.TmsTrvlApprovalModel;
import org.paradyne.model.TravelManagement.TravelProcess.TravelProcessModel;
import org.struts.lib.ParaActionSupport;

public class TmsTrvlApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlApprovalAction.class);
	TmsTrvlApproval trvlApprvl;

	public void prepare_local() throws Exception {
		trvlApprvl = new TmsTrvlApproval();
		trvlApprvl.setMenuCode(914);

	}

	public Object getModel() {
		return trvlApprvl;
	}

	/**
	 * Method to display the Travel Applications while loading the page.
	 * 
	 * @return string
	 */
	public String input() {

		logger.info("############# IN INPUT METHOD #################");
		try {
			TmsTrvlApprovalModel model = new TmsTrvlApprovalModel();
			model.initiate(context, session);
			String userId = trvlApprvl.getUserEmpId();
			model.getAllApplications(trvlApprvl, request, userId,
					"under_process");

			model.terminate();
		} catch (Exception e) {
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String back() throws Exception {
		try {
			//getNavigationPanel(1);
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getAllApplications() throws Exception {
		logger
				.info("############# IN GET ALL APPLICATION METHOD #################");
		try {
			TmsTrvlApprovalModel model = new TmsTrvlApprovalModel();
			model.initiate(context, session);
			String status = request.getParameter("status");
			System.out.println("status    " + status);
			String userId = trvlApprvl.getUserEmpId();
			model.getAllApplications(trvlApprvl, request, userId, status);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(1);
		return SUCCESS;

	}

	public String showApprovalDetails() {
		try {
			
			String source = request.getParameter("src");

			//String source =(String) request.getAttribute("src");

			System.out.println("source--------------" + source);
			trvlApprvl.setSource(source);
			
			if(trvlApprvl.getListType().equals(""))
			{
				trvlApprvl.setListType("under_process");
			}
			
			TmsTrvlApprovalModel model = new TmsTrvlApprovalModel();
			model.initiate(context, session);
			String appId = request.getParameter("applicationId");
			trvlApprvl.setHiddenApplicationCode(appId);
			setApproximateCostOfTour(appId);
			String statusQuery = " SELECT APPL_STATUS ";
			
			if(trvlApprvl.getListType().equals("processed"))
			{
				statusQuery += " ,DECODE(APPL_STATUS,'P','Pending for manager approval')";
			}
			else{
				statusQuery += " ,DECODE(APPL_STATUS,'P','Pending')";
			}
			statusQuery +=" FROM TMS_APPLICATION WHERE APPL_ID="+ trvlApprvl.getHiddenApplicationCode();

			Object statusObj[][] = model.getSqlModel().getSingleResult(
					statusQuery);
			String applstatus = "";// request.getParameter("applstatus")

			if (statusObj != null && statusObj.length > 0) {

				if (String.valueOf(statusObj[0][0]).equals("P")) {
					trvlApprvl.setAppRejButtonFlag(false);
					trvlApprvl.setViewDtlFlag(false);
					trvlApprvl.setAppRejFlag("true");
					trvlApprvl.setApproverCommentsFlag("true");
					applstatus = String.valueOf(statusObj[0][1]);

				}
				if (String.valueOf(statusObj[0][0]).equals("A")
						|| String.valueOf(statusObj[0][0]).equals("R")
						|| String.valueOf(statusObj[0][0]).equals("C")
						|| String.valueOf(statusObj[0][0]).equals("F")) {

					trvlApprvl.setAppRejButtonFlag(true);
					trvlApprvl.setViewDtlFlag(true);
					trvlApprvl.setAppRejFlag("true");
					trvlApprvl.setApproverCommentsFlag("false");
					if (String.valueOf(statusObj[0][0]).equals("F"))
						trvlApprvl.setShowRevokeStatus("true");
				}
			}

			System.out.println("trvlApprvl.getListType()-------"+trvlApprvl.getListType());

		
			
			model.getApplicationDetailsByApplicationId(trvlApprvl, request);

			boolean result = model.setApproverComments(trvlApprvl);

			if (result) {
				trvlApprvl.setApproverListFlag(true);
			}
			request.setAttribute("journeyRadioStatus", trvlApprvl
					.getJourneyRadio());
			request.setAttribute("accomodationRadioStatus", trvlApprvl
					.getAccomodationRadio());
			request.setAttribute("localConvRadioStatus", trvlApprvl
					.getLocalConvRadio());
			
	/*This status overrides the flag & button panel when 1approver approves & is sent for 2nd approver */
			
			if(trvlApprvl.getListType().equals("processed"))
			{
				if (applstatus.equals("Pending for manager approval")) {
					trvlApprvl.setAppRejButtonFlag(true);
					trvlApprvl.setViewDtlFlag(true);
					trvlApprvl.setAppRejFlag("true");
					trvlApprvl.setApproverCommentsFlag("false");
				} 

			}
			System.out.println("applstatus   "+applstatus);
			if(trvlApprvl.getListType().equals("under_process"))
			{
				if (applstatus.equals("Pending")) {
					
					System.out.println("applstatus iffffff  "+applstatus); 
					trvlApprvl.setAppRejButtonFlag(false);
					trvlApprvl.setViewDtlFlag(false);
					trvlApprvl.setAppRejFlag("false");
					trvlApprvl.setApproverCommentsFlag("true");
				} 

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		trvlApprvl.setPolicyViolationMsg(trvlApprvl.getPolicyViolationMsg());
		trvlApprvl.setViolationFlag(trvlApprvl.getViolationStatus());
		return "trvlApprView";
	}

	public String approveRejSendBackTravelApp() {
		try {
			setLevelForApplication();

			TmsTrvlApprovalModel travelApprovalmodel = new TmsTrvlApprovalModel();
			travelApprovalmodel.initiate(context, session);

			String appStatus = travelApprovalmodel.approveRejectSendBackLevApp(
					request, trvlApprvl.getInitId(), trvlApprvl
							.getHiddenApplicationCode(), trvlApprvl
							.getCheckApproveRejectStatus(), trvlApprvl
							.getApproverComments(), trvlApprvl.getUserEmpId(),
					trvlApprvl.getLevel());
			if (appStatus.equals("rejected")) {
				addActionMessage("Application rejected.");
			} else if (appStatus.equals("sendback")) {
				addActionMessage("Application sent back.");
			} else {
				logger.info("######### INITIATOR ID #############"
						+ trvlApprvl.getInitId());
				//sendApprovalMail(trvlApp.getHiddenApplicationCode());
				addActionMessage("Application approved.");
			}
			//saveApproverViolationComments(trvlApp.getHiddenApplicationCode());//save approver violation comments
			travelApprovalmodel.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (trvlApprvl.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlApprvl.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (trvlApprvl.getSource().equals("mytimecard")) {
			return "mytimeCard";
		}  else {
			return input();
		} 
		
		 
	}

	public void setLevelForApplication() {
		try {
			TmsTrvlApprovalModel model = new TmsTrvlApprovalModel();
			model.initiate(context, session);
			String query = " SELECT APPL_LEVEL FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApprvl.getHiddenApplicationCode();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				trvlApprvl.setLevel(String.valueOf(levelObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setApproximateCostOfTour(String travelapplicationID) {
		double cost = 0.0d;
		try {
			TravelProcessModel trvlprcmodel = new TravelProcessModel();
			trvlprcmodel.initiate(context, session);
			cost = trvlprcmodel.getApproximateBudget(travelapplicationID);
			trvlApprvl.setApproximateCostOfTour("" + cost);
			trvlprcmodel.terminate();
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
