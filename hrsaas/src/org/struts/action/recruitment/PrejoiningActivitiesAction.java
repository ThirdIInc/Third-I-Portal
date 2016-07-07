package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.prejoiningActivities;
import org.paradyne.model.recruitment.PrejoiningActivitiesModel;
import org.struts.lib.ParaActionSupport;

public class PrejoiningActivitiesAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PrejoiningActivitiesAction.class);
	prejoiningActivities prebean;

	public void prepare_local() throws Exception {
		prebean = new prejoiningActivities();
		prebean.setMenuCode(800);
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

	public String f9search() {
		try {
			String query = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME AS NAME, "
					+ " HRMS_REC_REQS_HDR.REQS_NAME,DECODE(PREJOIN_STATUS,'O','OPEN','C','CLOSE'),PREJOIN_CODE ,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) AS EMPNAME, "
					+ " PREJOIN_REQS_CODE,PREJOIN_CAND_CODE, PREJOIN_REPORTERCODE, TO_CHAR(PREJOIN_OFFERDATE,'DD-MM-YYYY'),TO_CHAR(PREJOIN_JOINDATE,'DD-MM-YYYY'), PREJOIN_CHECK_LIST "
					+ " FROM HRMS_REC_PREJOIN "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PREJOIN.PREJOIN_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PREJOIN.PREJOIN_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_REPORTERCODE) ORDER BY PREJOIN_REPORTERCODE ";

			String[] headers = { getMessage("cand.name"),
					getMessage("reqs.code"), "Status" };
			String[] headerWidth = { "40", "40", "20" };  
			String[] fieldNames = { "candidateName", "reqName", "Appstatus",
					"prejoinCode", "reportingName", "reqCode", "candidateCode",
					"reportingTo", "offerDate", "joinDate", "checkListType" };
			 
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			String submitFlag = "true";
			String submitToMethod = "PrejoiningActivities_f9searchdtl.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			return "";
		}  
	}

	public String f9searchdtl() {
		try {
			getNavigationPanel(3);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			if (String.valueOf(prebean.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(prebean
						.getCheckListType()));
			}
			model.f9searchdtl(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PageView";
	}
	 
	public String f9Candidate() {
		try {
			String query = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
					+ " REQS_NAME,RANK_NAME,HRMS_DIVISION.DIV_NAME,"
					+ " HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME,'OPEN',"
					+ " OFFER_REQS_CODE,OFFER_CAND_CODE,TO_CHAR(OFFER_DATE,'DD-MM-YYYY'), "
					+ "(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) AS EMPNAME,TO_CHAR(OFFER_EXP_JOINDATE,'DD-MM-YYYY'),OFFER_REPORT_TO "
					+ " FROM HRMS_REC_OFFER "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_OFFER.OFFER_REPORT_TO)"
					+ " WHERE HRMS_REC_OFFER.OFFER_STATUS='OA' AND HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (SELECT PREJOIN_CAND_CODE FROM HRMS_REC_PREJOIN WHERE PREJOIN_STATUS ='C') ";
	        
			if (prebean.isGeneralFlag()) {
				query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + prebean.getUserEmpId();
			} else {			
				if (prebean.getUserProfileDivision()!=null && prebean.getUserProfileDivision().length() > 0) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DIVISION IN ("
							+ prebean.getUserProfileDivision() + ")";
				} else {
					//query += " WHERE 1=1 ";
				}
			}
			 
			String[] headers = { getMessage("cand.name"),
					getMessage("reqs.code"), getMessage("position"),
					getMessage("division"), getMessage("branch"),
					getMessage("department") };
			String[] headerWidth = { "20", "20", "20", "20", "20", "20" };
			String[] fieldNames = { "candidateName", "reqName", "position",
					"division", "branch", "department", "Appstatus", "reqCode",
					"candidateCode", "offerDate", "reportingName", "joinDate",
					"reportingTo" };
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			 e.printStackTrace();
		}  
		return "f9page";
	}

	public String showCheckList() {
		try {
			getNavigationPanel(2);
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			if (String.valueOf(prebean.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(prebean
						.getCheckListType()));
			}
			model.showCheckList(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success1";
	}

	public String f9ReportingTo() {
		String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name, EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS='S'"
				+ " ORDER BY upper(name)";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerwidth = { "40", "60" };
		String[] fieldNames = { "reportingTo", "reportingName", "reportingTo" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Responsible() {
		String query = "SELECT  EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),EMP_ID as name "
				+ "FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS='S'"
				+ " ORDER BY upper(name)";
		String[] headers = { "Employee ID", "Employee Name" };
		String[] headerwidth = { "40", "60" };
		String[] fieldNames = { "ResponsiblePersonCode" + prebean.getRowId(),
				"ResponsiblePersonName" + prebean.getRowId(),
				"ResponsiblePersonCode" + prebean.getRowId() };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String save() {
		getNavigationPanel(2);
		prebean.setButtonpanelcode("2");
		try {
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			prebean.setChecklistTable(true);
			boolean res = false;
			Object[] ckcode = request.getParameterValues("prejoinListCode");
			Object[] targetdate = request
					.getParameterValues("prejoinTargetDate");
			Object[] actRequired = request
					.getParameterValues("activityRequired");
			Object[] status = request.getParameterValues("activityStatus");
			Object[] resperson;
			if (ckcode.length > 0){
				resperson = new Object[ckcode.length];
			} else{
				resperson = new Object[0];
			}

			for (int i = 0; i < ckcode.length; i++) {
				resperson[i] = request
						.getParameter("ResponsiblePersonCode" + i);
				if (String.valueOf(resperson[i]).equals("")) {
					resperson[i] = " ";
				}
			}
			if (prebean.getPrejoinCode().equals("")) {
				res = model.save(prebean, ckcode, targetdate, resperson,
						actRequired, status);

				if (res) {
					getNavigationPanel(3);
					addActionMessage("Record saved successfully.");

				} else {
					getNavigationPanel(2);
					addActionMessage("Record can not saved.");

				}
			} else {
				res = model.update(prebean, ckcode, targetdate, resperson,
						actRequired, status);

				if (res) {
					getNavigationPanel(3);
					addActionMessage("Record updated successfully.");

				} else {
					getNavigationPanel(2);
					addActionMessage("Record can not updated.");

				}
			}
			model.f9searchdtl(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PageView"; // "success1"; //
	}

	public String callstatus() { // retrieving application details
		getNavigationPanel(1);
		PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
		model.initiate(context, session);
		try {
			String status = request.getParameter("bgStatus");
			if (status.equals("C")) {
				prebean.setBgStatus("C");
				status = "C";
			} else {
				prebean.setBgStatus("P");
				status = "P";
			}

			if (prebean.getSearchFlag().equals("true")){
				prebean.setSearchFlag("true");
			} else{
				prebean.setSearchFlag("false");
			}
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, status, candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
			prebean.setChkSerch("true");
			request.setAttribute("stat", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "success";
	}

	public String input() throws Exception {
		try {
			getNavigationPanel(1);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, "P", candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
			prebean.setBgStatus("P");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String addNew() throws Exception {
		try {
			getNavigationPanel(2);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success1";

	}

	public String delete() throws Exception {
		try {
			getNavigationPanel(2);
			prebean.setButtonpanelcode("3");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			boolean result = false;
			result = model.deleteprecheck(prebean);
			if (result) {
				addActionMessage("Record deleted successfully !");
			} else {
				addActionMessage("Record cannot be deleted as \n it is associated with some other record");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success1";
	}

	public String edit() throws Exception {
		try {
			getNavigationPanel(2);
			prebean.setButtonpanelcode("3");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			model.preCheckRecord(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success1";
	}

	public String cancelThrd() throws Exception {
		try {
			getNavigationPanel(3);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			model.preCheckRecord(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PageView";
	}

	public String cancelSec() throws Exception {
		try {
			getNavigationPanel(2);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			model.preCheckRecord(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success1";
	}

	public String cancelFirst() throws Exception {
		try {
			getNavigationPanel(1);
			reset();
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, "P", candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String cancelFrth() throws Exception {
		try {
			getNavigationPanel(1);
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			reset();
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, "P", candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
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
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, status, candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String prejoining() { 
		getNavigationPanel(1);
		PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
		model.initiate(context, session);
		try {
			String status = request.getParameter("bgStatus");
			if (status.equals("P")) {
				prebean.setBgStatus("P");
				status = "P";
			} else {
				prebean.setBgStatus("C");
				status = "C";

			}

			if (prebean.getSearchFlag().equals("true")){
				prebean.setSearchFlag("true");
			} else{
				prebean.setSearchFlag("false");
			}
			prebean.setMyPage("1");
			prebean.setApplyFilterFlag("true");
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, status, candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
			prebean.setChkSerch("true");
			request.setAttribute("stat", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "success";
	}

	public String callforEdit() {
		try {
			getNavigationPanel(3);
			prebean.setButtonpanelcode("1");
			PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
			model.initiate(context, session);
			if (!prebean.getHiddencode().equals(""))
				model.selectedrecord(prebean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PageView";
	}

	public void reset() {
		PrejoiningActivitiesModel model = new PrejoiningActivitiesModel();
		model.initiate(context, session);
		prebean.setOfferDate("");
		prebean.setJoinDate("");
		prebean.setReportingTo("");
		prebean.setReportingName("");
		prebean.setCheckListType("");
		prebean.setPrejoinListCode("");
		prebean.setPrejoinListName("");
		prebean.setPrejoinTargetDate("");
		prebean.setResponsiblePersonName("");
		prebean.setResponsiblePersonCode("");
		prebean.setLstatus("");
		prebean.setActivityRequired("");
		prebean.setActivityStatus("");
		prebean.setDupactivityRequired("");
		prebean.setDupactivityStatus("");
		prebean.setLofferDate("");
		prebean.setLjoinDate("");
		prebean.setLreportTo("");
		prebean.setLreporterName("");
		prebean.setLtargetDate("");
		prebean.setLchecklistdtlcode("");
		prebean.setLcheckListname("");
		prebean.setLactivityRequired("");
		prebean.setRespCode("");
		prebean.setRespName("");
		prebean.setAppstatus("");
		prebean.setPrejoinCode("");
		prebean.setCandidateName("");
		prebean.setCandidateCode("");
		prebean.setReqCode("");
		prebean.setReqName("");
		prebean.setDivision("");
		prebean.setBranch("");
		prebean.setDepartment("");
		prebean.setPosition("");
		prebean.setCheckListType("");
		prebean.setDupcheckListType("");
		prebean.setCheckListCode("");
		prebean.setLcandidate("");
		prebean.setLcancode("");
		prebean.setLoffercode("");
		prebean.setLreqcode("");
		prebean.setLreqName("");
		prebean.setLposition("");
		prebean.setLofferstatus("");
		prebean.setBgStatus("");
		prebean.setConduct("");
		model.terminate();
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
			prebean.setOfferDate("");
			prebean.setOfferFDate("");
			prebean.setOfferTDate("");
			prebean.setJoinFDate("");
			prebean.setJoinTDate("");
			prebean.setChkSerch("");
			String candidate1 = getMessage("cand.name");
			String position1 = getMessage("position");
			String offfDate = getMessage("offfDate");
			String offtDate = getMessage("offtDate");
			String joinfDate = getMessage("joinfDate");
			String jointDate = getMessage("jointDate");
			model.setprejoinchececkList(prebean, "P", candidate1, position1,
					offfDate, offtDate, joinfDate, jointDate, request);
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
