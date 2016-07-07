package org.struts.action.TravelManagement.TravelProcess;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlMangAuthorities;
import org.paradyne.model.TravelManagement.TravelProcess.TmsTrvlMangAuthoritiesModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 * 
 */
public class TmsTrvlMangAuthoritiesAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	TmsTrvlMangAuthorities trvlAuth;

	public void prepare_local() throws Exception {
		trvlAuth = new TmsTrvlMangAuthorities();
		trvlAuth.setMenuCode(790);

	}

	public void prepare_withLoginProfileDetails() {
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getMsg(trvlAuth);
		// model.getEmpId(trvlAuth);
		model.terminate();
	}

	public Object getModel() {
		return trvlAuth;
	}

	public TmsTrvlMangAuthorities getTrvlAuth() {
		return trvlAuth;
	}

	public void setTrvlAuth(TmsTrvlMangAuthorities trvlAuth) {
		this.trvlAuth = trvlAuth;
	}

	/**
	 * To display saved record on load
	 */
	public String input() throws Exception {
		// Default Method with default modeCode(1)
		getNavigationPanel(1);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getMsg(trvlAuth);
		model.getRecords(trvlAuth, request);
		model.terminate();
		trvlAuth.setOnLoadFlag(true);
		trvlAuth.setPanelFlag("1");
		trvlAuth.setRetrnFlag("view");
		return "view";
	}

	/**
	 * To add new record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addNew() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.checkData(trvlAuth);
		trvlAuth.setCancelFlg("false");
		trvlAuth.setOnLoadFlag(true);
		trvlAuth.setPanelFlag("2");
		trvlAuth.setRetrnFlag("success");
		trvlAuth.setMangAuthCode("");
		reset();
		model.terminate();

		return "success";

	}

	/**
	 * To save record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		String str = "success";
		try {
			getNavigationPanel(2);
			String overWrite = request.getParameter("overWrite");
			boolean flag = false;
			str = "";
			trvlAuth.setPanelFlag("3");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			model.getBranchDtl(trvlAuth);
			if (trvlAuth.getMangAuthCode().equals("")
					|| trvlAuth.getTestBranchCode().equals("")) {
				flag = model.saveAuth(trvlAuth, request, overWrite, "save");
				if (flag) {
					reset();
					model.getMangAuthDtl(trvlAuth);
					model.getDesc(trvlAuth);
					model.viewSubSchdlr(trvlAuth);
					model.getSavedSubSchdlr(trvlAuth);
					model.getSavedAccountant(trvlAuth);
					addActionMessage(getMessage("save"));
					trvlAuth.setRetrnFlag("view");
					str = "success";
				} // end of inner if
				else {
					addActionMessage(getMessage("duplicate"));
					trvlAuth.setCancelFlg("false");
					getNavigationPanel(2);
					trvlAuth.setPanelFlag("2");
					trvlAuth.setRetrnFlag("success");
					str = "success";
				}// end of inner else
			} // end of outer if
			else if (!trvlAuth.getMangAuthCode().equals("")
					&& !trvlAuth.getTestBranchCode().equals("")) {
				flag = model.updateAuth(trvlAuth, request, overWrite, "update");
				if (flag) {
					model.getMangAuthDtl(trvlAuth);
					model.getDescPrevious(trvlAuth);
					model.viewSubSchdlr(trvlAuth);
					model.getSavedSubSchdlr(trvlAuth);
					model.getSavedAccountant(trvlAuth);
					addActionMessage(getMessage("update"));
					trvlAuth.setCancelFlg("false");
					getNavigationPanel(2);
					trvlAuth.setPanelFlag("2");
					trvlAuth.setRetrnFlag("success");

					str = "success";
				} // end of inner if
				else {
					addActionMessage(getMessage("duplicate"));
					trvlAuth.setCancelFlg("false");
					getNavigationPanel(2);
					trvlAuth.setPanelFlag("2");
					trvlAuth.setRetrnFlag("success");
					str = "success";
				}// end of inner else
			}// end of outer else if
			trvlAuth.setMyHidden("");
			trvlAuth.setSubTabLength("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	/**
	 * To clear all the fields of the form
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		trvlAuth.setAppFlag("");
		trvlAuth.setHidAppFlag("");
		trvlAuth.setBranchCode("");
		trvlAuth.setBranch("");
		trvlAuth.setMainSchdlr("");
		trvlAuth.setMainSchdlrCode("");
		trvlAuth.setAltMainSchdlr("");
		trvlAuth.setAltMainSchdlrCode("");
		trvlAuth.setAltMainSchdlrToken("");
		trvlAuth.setSchdlrApprCode("");
		trvlAuth.setSchdlrAppr("");
		trvlAuth.setAccntCode("");
		trvlAuth.setAccnt("");
		trvlAuth.setMainSchdlrToken("");
		trvlAuth.setAccntToken("");
		trvlAuth.setDescription("");
		trvlAuth.setEmployeeCode("");
		trvlAuth.setEmployee("");
		trvlAuth.setEmployeeToken("");
		trvlAuth.setStatus("");
		trvlAuth.setDescCnt("");
		trvlAuth.setMyHidden("");
		trvlAuth.setHiddencode("");
		trvlAuth.setHdeleteCode("");
		trvlAuth.setHdeleteSub("");
		trvlAuth.setHdeleteAuth("");
		trvlAuth.setConfChk("");
		// ADDED BY REEBA BEGINS
		trvlAuth.setAlterAccountant("");
		trvlAuth.setAlterAccountantCode("");
		trvlAuth.setAlterAccountantToken("");
		trvlAuth.setEscalationEmployee("");
		trvlAuth.setEscalationEmployeeCode("");
		trvlAuth.setEscalationEmployeeToken("");
		trvlAuth.setAccountantEmailID("");
		trvlAuth.setHiddenclaimwrkflowflag("N");
		trvlAuth.setHiddenackwrkflowflag("N");
		// ADDED BY REEBA ENDS
		return SUCCESS;
	}

	/**
	 * To delete a single record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteAuth() throws Exception {
		/* Default Method with save modeCode(2) */

		getNavigationPanel(1);
		boolean result;
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		String[] code = request.getParameterValues("hdeleteAuth");
		result = model.delChkdRec(trvlAuth, code);
		if (result) {
			prepare_withLoginProfileDetails();
			model.getRecords(trvlAuth, request);
			addActionMessage(getMessage("delete"));
			trvlAuth.setMangAuthCode("");
			reset();
		} // end of if
		else
			addActionMessage(getMessage("multiple.del.error"));
		trvlAuth.setOnLoadFlag(true);
		model.getMsg(trvlAuth);
		return "view";
	}// end of else

	/**
	 * To call child window by clicking Add Sub scheduler button to define sub
	 * schedulers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addSub() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(6);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);

		String myHidden = trvlAuth.getMyHidden();
		if (!myHidden.equals("")) {
			model.getSubList(trvlAuth);
		}// end of if
		else {
			model.getSavedSubSchdlr(trvlAuth);

		}// end of else

		model.terminate();
		return "add";

	}

	/**
	 * To call child window by clicking Edit subScheduler button to edit
	 * subscheduler list.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editSub() throws Exception {
		getNavigationPanel(6);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		if (!trvlAuth.getMyHidden().equals("")) {
			model.getSubList(trvlAuth);
		}// end of if
		else {
			model.getSavedSubSchdlr(trvlAuth);
		}// end of else
		model.terminate();
		return "add";
	}

	/**
	 * To add sub Schedulers to SubScheduler list.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addSubSchdlr() throws Exception {
		try {
			/* Default Method with save modeCode(2) */
			// getNavigationPanel(6);
			getNavigationPanel(2);

			logger.info("Inside addSubSchdlr");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			model.setItterotorAccountantData(trvlAuth, request);
			model.addSchdlr(trvlAuth, request);
			model.terminate();
			trvlAuth.setEmployeeCode("");
			trvlAuth.setEmployee("");
			trvlAuth.setEmployeeToken("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "add";
		return "success";
	}

	/**
	 * To add sub Schedulers to SubScheduler list.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addSubAccountant() throws Exception {
		try {
			/* Default Method with save modeCode(2) */
			// getNavigationPanel(6);
			getNavigationPanel(2);
			setItterotorData();
			logger.info("Inside addSubSchdlr");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			model.addAccountant(trvlAuth, request);
			model.terminate();
			trvlAuth.setAccountantemployee("");
			trvlAuth.setAccountantemployeeCode("");
			trvlAuth.setAccountantemployeeToken("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "add";
		return "success";
	}

	public void setItterotorData() {
		try {
			getNavigationPanel(2);
			logger.info("Inside addSubSchdlr");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			model.setItterotorSubShedulerData(trvlAuth, request);
			model.setItterotorAccountantData(trvlAuth, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * To delete sub schedulers from sub scheduler list.
	 */
	public String deleteSub() throws Exception {
		try {
			/* Default Method with save modeCode(2) */
			getNavigationPanel(2);
			setItterotorData();
			boolean result = false;
			String code[] = request.getParameterValues("hdeleteSub");
			String[] itEmployeeCode = request
					.getParameterValues("itEmployeeCode");
			String[] itEmployee = request.getParameterValues("itEmployee");
			String[] itEmployeeToken = request
					.getParameterValues("itEmployeeToken");
			String[] hidDeleteEmpId = request
					.getParameterValues("hidDeleteEmpId");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			result = model.callDelSub(trvlAuth, code, itEmployeeCode,
					itEmployee, itEmployeeToken);
			if (result) {
				addActionMessage(getMessage("delete"));
			}// end of if
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return "add";

		return "success";
	}

	/**
	 * To delete Sub Accountant
	 */

	public String deleteAccountant() throws Exception {
		try {
			/* Default Method with save modeCode(2) */
			getNavigationPanel(2);
			setItterotorData();
			boolean result = false;
			String code[] = request.getParameterValues("hdeleteAcc");

			String[] itEmployeeCode = request
					.getParameterValues("itEmployeeCodeAcc");

			String[] itEmployee = request.getParameterValues("itEmployeeAcc");

			String[] itEmployeeToken = request
					.getParameterValues("itEmployeeTokenAcc");

			String[] acknoledge = request
					.getParameterValues("hiddenitAcknoledge");

			String[] advance = request.getParameterValues("hiddenitAdvance");

			String[] claim = request.getParameterValues("hiddenittClaim");

			String[] hidDeleteEmpId = request
					.getParameterValues("hidDeleteEmpIdAcc");

			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			result = model.callDelAccountant(trvlAuth, code, itEmployeeCode,
					itEmployee, itEmployeeToken, acknoledge, advance, claim);
			if (result) {
				addActionMessage(getMessage("delete"));
			}// end of if
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return "add";

		return "success";
	}

	/**
	 * To return to first page
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelFirst_NOTINUSE() throws Exception {
		String str;
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		if (trvlAuth.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getMangAuthDtl(trvlAuth);
			model.viewSubSchdlr(trvlAuth);
			trvlAuth.setOnLoadFlag(false);
			trvlAuth.setPanelFlag("3");
			trvlAuth.setRetrnFlag("view");
			str = "view";
		}// end of if
		else {
			getNavigationPanel(1);
			model.getRecords(trvlAuth, request);
			trvlAuth.setMangAuthCode("");
			reset();
			trvlAuth.setOnLoadFlag(true);
			trvlAuth.setPanelFlag("1");
			trvlAuth.setRetrnFlag("view");

			str = "view";
		}// end of else
		model.terminate();
		return str;
	}

	public String cancelFirst() throws Exception {
		String str;
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		getNavigationPanel(1);
		model.getRecords(trvlAuth, request);
		trvlAuth.setMangAuthCode("");
		reset();
		trvlAuth.setOnLoadFlag(true);
		trvlAuth.setPanelFlag("1");
		trvlAuth.setRetrnFlag("view");

		str = "view";

		model.terminate();
		return str;
	}

	/**
	 * To return to edit mode
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getRecords(trvlAuth, request);
		trvlAuth.setMangAuthCode("");
		reset();
		model.terminate();
		trvlAuth.setPanelFlag("1");
		trvlAuth.setRetrnFlag("view");
		trvlAuth.setOnLoadFlag(true);
		return "view";
	}

	/**
	 * To set records by double clicking
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			getNavigationPanel(2);
			trvlAuth.setCancelFlg("false");
			TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
			model.initiate(context, session);
			model.getMangAuthDtl(trvlAuth);
			model.getDescPrevious(trvlAuth);
			model.viewSubSchdlr(trvlAuth);

			model.getSavedSubSchdlr(trvlAuth);
			model.getSavedAccountant(trvlAuth);

			model.terminate();
			trvlAuth.setPanelFlag("2");
			trvlAuth.setRetrnFlag("success");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";

	}

	/**
	 * To delete single record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(1);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		boolean result = model.deleteAuth(trvlAuth);

		if (result) {
			addActionMessage(getMessage("delete"));
			// trvlAuth.setOnLoadFlag(false);

		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else

		model.getRecords(trvlAuth, request);
		trvlAuth.setMangAuthCode("");
		reset();
		model.terminate();
		trvlAuth.setPanelFlag("1");
		trvlAuth.setRetrnFlag("view");
		trvlAuth.setOnLoadFlag(true);
		return "view";
	}

	/**
	 * To set records in the respective fields after clicking Edit button
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getHidData(trvlAuth);
		model.getMangAuthDtlEdit(trvlAuth);
		model.getSavedSubSchdlr(trvlAuth);
		System.out.println("trvlAuth.getAppFlag()================"
				+ trvlAuth.getAppFlag());
		if (trvlAuth.getAppFlag().equals("checked")) {
			trvlAuth.setHidAppFlag("Y");
		} // end of if
		else {
			trvlAuth.setHidAppFlag("N");
		}// end of else
		model.getDesc(trvlAuth);
		model.checkData(trvlAuth);
		trvlAuth.setCancelFlg("true");
		trvlAuth.setEditFlag(true);
		model.terminate();
		trvlAuth.setPanelFlag("2");
		trvlAuth.setRetrnFlag("success");
		return "success";

	}

	/*
	 * To display saved records in another window
	 */
	public String f9Action() throws Exception {
		try {
			String query = "SELECT HRMS_CENTER.CENTER_NAME,"
					+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes'"
					+ " ELSE 'No' END,NVL(AUTH_ALL_BRNCH,''),"
					+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"
					+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME,"
					+ " e4.EMP_FNAME || ' ' || e4.EMP_MNAME || ' ' || e4.EMP_LNAME,"
					+ " CASE WHEN AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,AUTH_BRNCH_ID,"
					+ " AUTH_MAIN_SCHL_ID,AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT,AUTH_ALT_MAIN_SCHL_ID,AUTH_ID,CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes' ELSE 'No' END,AUTH_STATUS"
					// ADDED BY REEBA
					+ " , AUTH_ALT_ACCNT_ID, e5.EMP_FNAME || ' ' || e5.EMP_MNAME || ' ' || e5.EMP_LNAME"
					+ " , AUTH_POLICY_VIOLN_ESCLN_ID, e6.EMP_FNAME || ' ' || e6.EMP_MNAME || ' ' || e6.EMP_LNAME, AUTH_ACCOUNTANT_EMAIL_ID,CLM_ACK_WORKFLOW_ENABLE,CLM_ADMIN_WORKFLOW_ENABLE"

					+ " FROM TMS_MANG_AUTH_HDR"
					+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_SCH_APPROVAL)"
					+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
					+ " LEFT JOIN HRMS_EMP_OFFC e4 ON(e4.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_MAIN_SCHL_ID)"
					// ADDED BY REEBA
					+ " LEFT JOIN HRMS_EMP_OFFC e5 ON(e5.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_ACCNT_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC e6 ON(e6.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_POLICY_VIOLN_ESCLN_ID)"

					+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME";

			String[] headers = { "Branch Name", "Main Scheduler Name",
					"Applied to all branch" };

			String[] headerWidth = { "20", "15", "15" };

			String[] fieldNames = { "branch", "mainSchdlr", "hiddenAppFlag",
					"hidAppFlag", "schdlrAppr", "accnt", "altMainSchdlr",
					"status", "branchCode", "mainSchdlrCode", "schdlrApprCode",
					"accntCode", "altMainSchdlrCode", "mangAuthCode",
					"hidChkFlg",
					"hidStatus",
					// ADDED BY REEBA
					"alterAccountantCode", "alterAccountant",
					"escalationEmployeeCode", "escalationEmployee",
					"accountantEmailID","hiddenclaimwrkflowflag","hiddenackwrkflowflag" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			// ADDED BY REEBA
					14, 15, 16, 17, 18, 19, 20,21,22 };

			String submitFlag = "true";

			String submitToMethod = "TrvlMangAuthorities_details.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * To view subScheduler
	 * 
	 * @return String
	 */
	public String details() {
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getDescPrevious(trvlAuth);
		model.viewSubSchdlr(trvlAuth);
		model.getSavedSubSchdlr(trvlAuth);
		model.getSavedAccountant(trvlAuth);
		model.terminate();
		trvlAuth.setPanelFlag("3");
		trvlAuth.setRetrnFlag("view");
		return SUCCESS;
	}

	/**
	 * for paging
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String callPageView() throws Exception {
		getNavigationPanel(1);
		logger.info("trvlAuth.getSelectname()----------"
				+ trvlAuth.getSelectname());
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		trvlAuth.setOnLoadFlag(true);
		getNavigationPanel(Integer.parseInt(trvlAuth.getPanelFlag()));
		model.getRecords(trvlAuth, request);
		model.terminate();
		return trvlAuth.getRetrnFlag();

	}

	/**
	 * to show exisiting branch
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String showBranch() throws Exception {
		getNavigationPanel(1);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.showBranch(trvlAuth);
		model.getMsg(trvlAuth);
		model.getRecords(trvlAuth, request);
		trvlAuth.setOnLoadFlag(true);
		trvlAuth.setPanelFlag("1");
		trvlAuth.setRetrnFlag("view");
		model.terminate();
		return "show";
	}

	public String f9Branch() throws Exception {

		String[] itBranchCode = (String[]) request
				.getParameterValues("itBranchCode");
		String str = "0";
		if (!trvlAuth.getItBranchCode().equals("null")
				&& !trvlAuth.getItBranchCode().equals("")) {
			for (int i = 0; i < itBranchCode.length; i++) {
				str += "," + itBranchCode[i];
			}// end of for loop
		}// end of if

		String query = "SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER"
		// + " WHERE CENTER_ID NOT IN(SELECT NVL(AUTH_BRNCH_ID,0) FROM
		// TMS_MANG_AUTH_HDR)"
				+ " ORDER BY  CENTER_ID";

		String[] headers = { "Branch Name" };

		String[] headerWidth = { "15" };

		String[] fieldNames = { "branch", "branchCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "TrvlMangAuthorities_getAllDetail.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * method for getting record(s) related to specific branch
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getAllDetail() throws Exception {
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);
		model.getAllDetail(trvlAuth);
		model.terminate();
		return "success";
	}

	/*
	 * To display employee name to select main scheduler
	 */
	public String f9MainSchdlr() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String str1 = "0", str2 = "0", str3 = "0", str = "0";
		/*
		 * if (!trvlAuth.getHiddenEmpId().equals("null") &&
		 * !trvlAuth.getHiddenEmpId().equals("")) { str =
		 * trvlAuth.getHiddenEmpId(); }
		 */

		if (trvlAuth.getMangAuthCode().equals("")) {
			if (!trvlAuth.getMyHiddenEmpCode().equals("null")
					&& !trvlAuth.getMyHiddenEmpCode().equals("")) {
				str = trvlAuth.getMyHiddenEmpCode();
			}// end of inner if
		}// end of outer if
		else {
			if (!trvlAuth.getHiddenEmpId().equals("null")
					&& !trvlAuth.getHiddenEmpId().equals("")) {
				str = trvlAuth.getHiddenEmpId();
			}// end of if
		}// end of else

		if (!trvlAuth.getAltMainSchdlrCode().equals("null")
				&& !trvlAuth.getAltMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getAltMainSchdlrCode();
		}// end of if
		/*
		 * if (!trvlAuth.getSchdlrApprCode().equals("null") &&
		 * !trvlAuth.getSchdlrApprCode().equals("")) { str2 =
		 * trvlAuth.getSchdlrApprCode(); } if
		 * (!trvlAuth.getAccntCode().equals("null") &&
		 * !trvlAuth.getAccntCode().equals("")) { str3 =
		 * trvlAuth.getAccntCode(); }
		 */
		logger.info("---------str-------" + str);
		logger.info("---------str1-------" + str1);

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("
				+ str1
				+ ","
				+ str + ") "
				// + "," + str3 + "," + str2 + ","+trvlAuth.getHidTabEmpId()+")
				// "
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "mainSchdlrToken", "mainSchdlr",
				"mainSchdlrCode" };

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

	}

	/*
	 * To display employee name to select alternate main scheduler
	 */
	public String f9AltMainSchdlr() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String str1 = "0", str2 = "0", str3 = "0", str = "0";
		/*
		 * if (!trvlAuth.getHiddenEmpId().equals("null") &&
		 * !trvlAuth.getHiddenEmpId().equals("")) { str =
		 * trvlAuth.getHiddenEmpId(); }
		 */
		if (trvlAuth.getMangAuthCode().equals("")) {
			if (!trvlAuth.getMyHiddenEmpCode().equals("null")
					&& !trvlAuth.getMyHiddenEmpCode().equals("")) {
				str = trvlAuth.getMyHiddenEmpCode();
			}// end of inner if

		}// end of outer if
		else {
			if (!trvlAuth.getHiddenEmpId().equals("null")
					&& !trvlAuth.getHiddenEmpId().equals("")) {
				str = trvlAuth.getHiddenEmpId();
			}// end of inner if
		}// end of else

		if (!trvlAuth.getMainSchdlrCode().equals("null")
				&& !trvlAuth.getMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getMainSchdlrCode();
		}// end of if
		/*
		 * if (!trvlAuth.getSchdlrApprCode().equals("null") &&
		 * !trvlAuth.getSchdlrApprCode().equals("")) { str2 =
		 * trvlAuth.getSchdlrApprCode(); } if
		 * (!trvlAuth.getAccntCode().equals("null") &&
		 * !trvlAuth.getAccntCode().equals("")) { str3 =
		 * trvlAuth.getAccntCode(); }
		 */

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("
				+ str1
				+ ","
				+ str + ") "
				// + "," + str3 + "," + str2 + ","+trvlAuth.getHidTabEmpId()+")
				// "
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "altMainSchdlrToken", "altMainSchdlr",
				"altMainSchdlrCode" };

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

	}

	/*
	 * To display employee name to select approver.
	 */
	public String f9SchdlrAppr() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String str1 = "0", str2 = "0", str3 = "0", str = "0";
		if (!trvlAuth.getHiddenEmpId().equals("null")
				&& !trvlAuth.getHiddenEmpId().equals("")) {
			str = trvlAuth.getHiddenEmpId();
		}// end of if

		if (!trvlAuth.getMainSchdlrCode().equals("null")
				&& !trvlAuth.getMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getAltMainSchdlrCode().equals("null")
				&& !trvlAuth.getAltMainSchdlrCode().equals("")) {
			str2 = trvlAuth.getAltMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getAccntCode().equals("null")
				&& !trvlAuth.getAccntCode().equals("")) {
			str3 = trvlAuth.getAccntCode();
		}// end of if

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' "
				// + " AND EMP_ID NOT IN("
				// + str1
				// + ","
				// + str2 + "," + str3 + "," + str +
				// ","+trvlAuth.getHidTabEmpId()+")"
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "schdlrApprToken", "schdlrAppr",
				"schdlrApprCode" };

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

	}

	/*
	 * To display employee name to select Accountant
	 */
	public String f9Accnt() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String[] schdlrId = request.getParameterValues("itSchdlrCode");
		String[] schdlrApprId = request.getParameterValues("itSchdlrApprCode");
		String[] sltSchdlrId = request.getParameterValues("itAltSchdlrCode");
		/*
		 * String str="0"; if(schdlrId!=null) {
		 * logger.info("---------str-----inside--------"+str); for (int i = 0; i <
		 * schdlrId.length; i++) { str+=","+schdlrId[i]; } }
		 */
		String str1 = "0", str2 = "0", str3 = "0", str = "0";
		if (!trvlAuth.getHiddenEmpId().equals("null")
				&& !trvlAuth.getHiddenEmpId().equals("")) {
			str = trvlAuth.getHiddenEmpId();
		}// end of if

		if (!trvlAuth.getMainSchdlrCode().equals("null")
				&& !trvlAuth.getMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getAltMainSchdlrCode().equals("null")
				&& !trvlAuth.getAltMainSchdlrCode().equals("")) {
			str2 = trvlAuth.getAltMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getSchdlrApprCode().equals("null")
				&& !trvlAuth.getSchdlrApprCode().equals("")) {
			str3 = trvlAuth.getSchdlrApprCode();
		}// end of if

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' "
				// + " AND EMP_ID NOT IN("
				// + str1
				// + ","
				// + str2 + "," + str3 + "," + str +
				// ","+trvlAuth.getHidTabEmpId()+") "
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "accntToken", "accnt", "accntCode" };

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

	}

	/*
	 * To display employee name to select sub scheduler
	 */
	public String f9Accountantemployee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String str1 = "0", str2 = "0", str3 = "0", str4 = "0", str = "0";
		String[] itEmployeeCode = (String[]) request
				.getParameterValues("itEmployeeCodeAcc");

		String[] employeeCodeSubScheduler = (String[]) request
				.getParameterValues("itEmployeeCode");

		if (itEmployeeCode != null) {
			for (int i = 0; i < itEmployeeCode.length; i++) {
				str += "," + itEmployeeCode[i];
			}// end of loop
		}// end of if

		if (!trvlAuth.getMainSchdlrCode().equals("null")
				&& !trvlAuth.getMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getAltMainSchdlrCode().equals("null")
				&& !trvlAuth.getAltMainSchdlrCode().equals("")) {
			str2 = trvlAuth.getAltMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getSchdlrApprCode().equals("null")
				&& !trvlAuth.getSchdlrApprCode().equals("")) {
			str3 = trvlAuth.getSchdlrApprCode();
		}// end of if
		if (!trvlAuth.getAccntCode().equals("null")
				&& !trvlAuth.getAccntCode().equals("")) {
			str4 = trvlAuth.getAccntCode();
		}// end of if

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("
				+ str
				+ ","
				+ str1 + "," + str2
				// + "," + str3 + "," + str4 + "," +trvlAuth.getHidTabEmpId()
				+ ") "
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ "ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "accountantemployeeToken",
				"accountantemployee", "accountantemployeeCode" };

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

	}

	/*
	 * To display employee name to select sub scheduler
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' ||
		 * EMP_LNAME ,EMP_ID" +" FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS ='S'
		 * ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";
		 */
		String str1 = "0", str2 = "0", str3 = "0", str4 = "0", str = "0";
		String[] itEmployeeCode = (String[]) request
				.getParameterValues("itEmployeeCode");

		String[] itEmployeeCodeAccountant = (String[]) request
				.getParameterValues("itEmployeeCodeAcc");

		if (itEmployeeCode != null) {
			for (int i = 0; i < itEmployeeCode.length; i++) {
				str += "," + itEmployeeCode[i];
			}// end of loop
		}// end of if

		if (!trvlAuth.getMainSchdlrCode().equals("null")
				&& !trvlAuth.getMainSchdlrCode().equals("")) {
			str1 = trvlAuth.getMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getAltMainSchdlrCode().equals("null")
				&& !trvlAuth.getAltMainSchdlrCode().equals("")) {
			str2 = trvlAuth.getAltMainSchdlrCode();
		}// end of if
		if (!trvlAuth.getSchdlrApprCode().equals("null")
				&& !trvlAuth.getSchdlrApprCode().equals("")) {
			str3 = trvlAuth.getSchdlrApprCode();
		}// end of if
		if (!trvlAuth.getAccntCode().equals("null")
				&& !trvlAuth.getAccntCode().equals("")) {
			str4 = trvlAuth.getAccntCode();
		}// end of if

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("
				+ str
				+ ","
				+ str1 + "," + str2
				// + "," + str3 + "," + str4 + "," +trvlAuth.getHidTabEmpId()
				+ ") "
				// + " AND EMP_CENTER="+trvlAuth.getBranchCode()
				+ "ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "employee", "employeeCode" };

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

	}

	/**
	 * @author REEBA JOSEPH To display employee name to select Alternate
	 *         Accountant
	 * @return
	 * @throws Exception
	 */
	public String f9AlterAccountant() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' "
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "alterAccountantToken", "alterAccountant",
				"alterAccountantCode" };

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

	}// end of method f9AlterAccountant

	/**
	 * @author REEBA JOSEPH To display employee name to select Escalation
	 *         Employee
	 * @return
	 * @throws Exception
	 */
	public String f9EscalationEmp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT NVL(EMP_TOKEN,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ,NVL(EMP_ID,0)"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " WHERE EMP_STATUS ='S' "
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME 	";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.code"),
				getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "escalationEmployeeToken",
				"escalationEmployee", "escalationEmployeeCode" };

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

	}// end of method f9EscalationEmp

	/**
	 * @author REEBA JOSEPH
	 * @return String
	 */
	public String handoverToAlternateScheduler() {
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);

		model.updateMainScheduler(trvlAuth);

		model.getHidData(trvlAuth);
		model.getMangAuthDtlEdit(trvlAuth);
		/*
		 * model.getSavedSubSchdlr(trvlAuth);
		 * model.getSavedAccountant(trvlAuth);
		 */

		setItterotorData();

		if (trvlAuth.getAppFlag().equals("checked")) {
			trvlAuth.setHidAppFlag("Y");
		} // end of if
		else {
			trvlAuth.setHidAppFlag("N");
		}// end of else
		model.getDesc(trvlAuth);
		model.checkData(trvlAuth);
		trvlAuth.setCancelFlg("true");
		trvlAuth.setEditFlag(true);
		model.terminate();
		trvlAuth.setPanelFlag("2");
		trvlAuth.setRetrnFlag("success");
		return SUCCESS;
	}// end of method handoverToAlternateScheduler

	/**
	 * @author REEBA JOSEPH
	 * @return String
	 */
	public String handoverToAlternateAccountant() {
		getNavigationPanel(2);
		TmsTrvlMangAuthoritiesModel model = new TmsTrvlMangAuthoritiesModel();
		model.initiate(context, session);

		model.updateAccountant(trvlAuth);

		model.getHidData(trvlAuth);
		model.getMangAuthDtlEdit(trvlAuth);
		/*
		 * model.getSavedSubSchdlr(trvlAuth);
		 * model.getSavedAccountant(trvlAuth);
		 */

		setItterotorData();
		if (trvlAuth.getAppFlag().equals("checked")) {
			trvlAuth.setHidAppFlag("Y");
		} // end of if
		else {
			trvlAuth.setHidAppFlag("N");
		}// end of else
		model.getDesc(trvlAuth);
		model.checkData(trvlAuth);
		trvlAuth.setCancelFlg("true");
		trvlAuth.setEditFlag(true);
		model.terminate();
		trvlAuth.setPanelFlag("2");
		trvlAuth.setRetrnFlag("success");
		return SUCCESS;
	}// end of method handoverToAlternateAccountant

}
