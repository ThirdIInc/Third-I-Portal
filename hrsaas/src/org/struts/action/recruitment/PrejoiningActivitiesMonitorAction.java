package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.prejoiningActivities;
import org.paradyne.model.recruitment.PrejoiningActivitiesModel;
import org.struts.lib.ParaActionSupport;

public class PrejoiningActivitiesMonitorAction extends ParaActionSupport {

	prejoiningActivities prebean;

	public void prepare_local() throws Exception {
		prebean = new prejoiningActivities();
		prebean.setMenuCode(805);
	}

	public Object getModel() {
		return prebean;
	}

	public prejoiningActivities getprebean() {
		return prebean;
	}

	public void setprebean(prejoiningActivities prebean) {
		this.prebean = prebean;
	}

	public String input() throws Exception {
		try {
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			String candidate = getMessage("cand.name");
			String position = getMessage("position");
			String frmDdate = getMessage("tarfDate");
			String toDate = getMessage("tartDate");
			model.setActivityMonitor(prebean, "P", candidate, position,
					frmDdate, toDate, request);
			prebean.setBgStatus("P");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String prejoiningmonitor() { // retrieving application details
		try {
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			String status = request.getParameter("bgStatus");
			String stat;
			if (status.equals("P")) {
				prebean.setBgStatus("P");
				status = "P";
				prebean.setConduct("true");
			} else {
				prebean.setBgStatus("C");
				status = "C";
				prebean.setConduct("false");
			}
			if (prebean.getSearchFlag().equals("true")) {
				prebean.setSearchFlag("true");
			} else {
				prebean.setSearchFlag("false");
			}
			prebean.setMyPage("1");
			prebean.setApplyFilterFlag("true");
			String candidate = getMessage("cand.name");
			String position = getMessage("position");
			String frmDdate = getMessage("tarfDate");
			String toDate = getMessage("tartDate");
			model.setActivityMonitor(prebean, status, candidate, position,
					frmDdate, toDate, request);
			prebean.setChkSerch("true");
			request.setAttribute("stat", status);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String callPage() throws Exception {
		try {
			getNavigationPanel(1);
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			String status = prebean.getBgStatus();
			if (status.equals("C")) {
				prebean.setBgStatus("C");
				status = "C";
				prebean.setConduct("true");
			} else {
				prebean.setBgStatus("P");
				status = "P";
				prebean.setConduct("false");
			}
			request.setAttribute("stat", status);
			String candidate = getMessage("cand.name");
			String position = getMessage("position");
			String frmDdate = getMessage("tarfDate");
			String toDate = getMessage("tartDate");
			model.setActivityMonitor(prebean, status, candidate, position,
					frmDdate, toDate, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String monitorsave() throws Exception {
		try {
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			Object[] ckcode = request.getParameterValues("Lchecklistdtlcode");
			Object[] actRequired = request
					.getParameterValues("LactivityRequired");
			Object[] prejoincodes = request.getParameterValues("Lreqcode");
			boolean result = model.monitersave(prebean, ckcode, actRequired,
					prejoincodes);
			if (result) {
				addActionMessage("Status updated successfully.");
				prebean.setConduct("true");
			} else {
				addActionMessage("Status cannot updated.");
				prebean.setConduct("false");
			}
			String candidate = getMessage("cand.name");
			String position = getMessage("position");
			String frmDdate = getMessage("tarfDate");
			String toDate = getMessage("tartDate");
			model.setActivityMonitor(prebean, "P", candidate, position,
					frmDdate, toDate, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String clearFilter() throws Exception {
		try {
			getNavigationPanel(1);
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			prebean.setPositionId("");
			prebean.setPositionName("");
			prebean.setCandCode1("");
			prebean.setCandidateName1("");
			prebean.setTargetFDate("");
			prebean.setTargetTDate("");
			prebean.setChkSerch("");
			String candidate = getMessage("cand.name");
			String position = getMessage("position");
			String frmDdate = getMessage("tarfDate");
			String toDate = getMessage("tartDate");
			model.setActivityMonitor(prebean, "P", candidate, position,
					frmDdate, toDate, request); // getRecord method to retrieve
												// reqs details as per the
												// status
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String f9CandidateAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("cand.name") };// getMessage("candidate.fname")

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "candidateName1", "candCode1" };// ,
																// "relocate"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };// , 17};

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
	}

	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("position") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "positionName", "positionId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
	}

}
