package org.struts.action.attendance;

import org.paradyne.bean.attendance.OutdoorVisit;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.attendance.OutdoorVisitModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author saipavan 29-05-2008
 */

/**
 * class for outdoor visit application actionfile.
 */

public class OutdoorVisitAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OutdoorVisitAction.class);
	OutdoorVisit outdoor = null;

	public void prepare_local() throws Exception {
		outdoor = new OutdoorVisit();
		outdoor.setMenuCode(403);
	}

	public Object getModel() {
		return outdoor;
	}

	public OutdoorVisit getOutdoor() {
		return outdoor;
	}

	public void setOutdoor(OutdoorVisit outdoor) {
		this.outdoor = outdoor;
	}

	/**
	 * @return string
	 */
	public String delete() { // deleting outdoor visit application.
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		boolean result = model.delete(outdoor);
		if (result) {
			addActionMessage(getMessage("delete"));

			// ------------------------Process Manager
			// Alert------------------------start
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			String applnID = outdoor.getOutvCode();
			String module = "Outdoor Visit";
			processAlerts.removeProcessAlert(applnID, module);
			// ------------------------Process Manager
			// Alert------------------------end
			reset();
		} else {
			addActionMessage(getMessage("del.error"));
		}

		model.terminate();
		return SUCCESS;
	}

	/**
	 * @return string
	 */
	public String f9empaction() {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID "
				+ " FROM HRMS_EMP_OFFC  ";

		query += getprofileQuery(outdoor);

		query += " AND EMP_STATUS = 'S'";

		query += "	ORDER BY UPPER(EMP_NAME) ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerwidth = { "40", "60" };

		String[] fieldNames = { "outdoor.eToken", "outdoor.empName", "ecode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlage = "true";

		String submitToMethod = "OutdoorVisit_setApproverData.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String setApproverData() {
		setApplicationApproverData("0");
		if (outdoor.getOutvCode().equals("") || outdoor.getOutvCode() == null) {
			getNavigationPanel(1);
			outdoor.setApplnStatus("");
		} else {
			getNavigationPanel(2);
			outdoor.setApplnStatus("Dr");
		}
		outdoor.setEnableAll("Y");
		return SUCCESS;
	}

	public String f9OutVisitaction() {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'),"
				+ " DECODE(OUTDOORVISIT_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected'), OUTDOORVISIT_FRTIME, OUTDOORVISIT_TOTIME,"
				+ " OUTDOORVISIT_LOCATION , EMP_ID, OUTDOORVISIT_APPR, OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
				+ " FROM HRMS_OUTDOORVISIT "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) ";
		if (outdoor.isGeneralFlag()) {
			query += " WHERE HRMS_EMP_OFFC.EMP_ID = " + outdoor.getUserEmpId()
					+ " ORDER BY OUTDOORVISIT_DATE DESC, UPPER(NAME) ";
		} else {
			query += " ORDER BY OUTDOORVISIT_DATE DESC,UPPER(NAME) ";
		}

		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("app.date"), getMessage("status") };

		String[] headerwidth = { "10", "40", "15", "15" };

		String[] fieldNames = { "outdoor.eToken", "outdoor.empName",
				"outdoor.outDate", "hiddenStatus", "outdoor.frTime",
				"outdoor.toTime", "outdoor.outLoc", "outdoor.ecode",
				"outdoor.empAppr", "outvCode", "outdoor.status" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		String submitFlage = "true";

		String submitToMethod = "OutdoorVisit_outdoorAppdtl.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String outdoorAppdtl() {

		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		model.applicationDtl(outdoor);
		model.setKeepInform(outdoor);
		model.terminate();

		return SUCCESS;
	}

	/**
	 * @return string
	 */
	public String report() {
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		model.getReport(request, response, outdoor);
		model.terminate();
		return null;
	}

	/**
	 * @return string
	 */
	public String reset() { // clearing all text fields including hidden
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		outdoor.setOutvCode("");
		outdoor.setOutDate("");
		outdoor.setOutLoc("");
		outdoor.setToTime("");
		outdoor.setFrTime("");
		outdoor.setPurpose("");
		outdoor.setInformName("");

		if (!outdoor.isGeneralFlag()) {
			outdoor.setEmpName("");
			outdoor.setEcode("");
			outdoor.setEToken("");
		}
		model.getCurrenUserInformation(outdoor);
		Object[][] empFlow = generateEmpFlow(outdoor.getEcode(), "Outdoor", 1);
		model.setApplicationApproverData(outdoor, empFlow);

		if (outdoor.getOutvCode().equals("") || outdoor.getOutvCode() == null) {
			getNavigationPanel(1);
			outdoor.setApplnStatus("");
		} else {
			getNavigationPanel(2);
			outdoor.setApplnStatus("Dr");
		}

		model.terminate();
		return SUCCESS;
	}

	/**
	 * @return string after retrieve approver list
	 */
	public String showDetailsForApproval() { // Retrieving approver Details
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		String outCode = request.getParameter("outdrCode");
		model.setApplication(outdoor, outCode); // method for setting
		// application details.
		model.setApproverComments(outdoor); // retrieving approver details and
		// setting.
		model.setKeepInform(outdoor, outCode);
		setApplicationApproverData("0");

		if (outdoor.getStatus().equals("P")) {
			// outdoor.setApproverFlag(true);
			outdoor.setApproverCommentsFlag(true);
			outdoor.setApplnStatus("Pn");
			getNavigationPanel(5);
		} else if (outdoor.getStatus().equals("A")) {
			outdoor.setApplnStatus("Ap");
			getNavigationPanel(6);
		} else if (outdoor.getStatus().equals("R")) {
			outdoor.setApplnStatus("Rj");
			getNavigationPanel(6);
		} else if (outdoor.getStatus().equals("F")) {
			outdoor.setApplnStatus("Fr");
			outdoor.setApproverCommentsFlag(true);
			getNavigationPanel(5);
		}
		model.setLevel(outdoor);
		outdoor.setIsApprove("true");
		outdoor.setEnableAll("N");
		model.terminate();
		return SUCCESS;
	}

	public String checkForBlockApplication() {
		String isApplicationBlock = "N";
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);

			String outdoorBlockSettingQuery = " SELECT NVL(OUTDOOR_BLOCK_AFTER_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			Object[][] outdoorBlockSettingObj = model.getSqlModel()
					.getSingleResult(outdoorBlockSettingQuery);

			if (outdoorBlockSettingObj != null
					&& outdoorBlockSettingObj.length > 0) {

				if (!String.valueOf(outdoorBlockSettingObj[0][0]).equals("0")) {
					String sqlQuery = " SELECT 'Y' FROM DUAL "
							+ " WHERE "
							+ "	TO_DATE('"
							+ outdoor.getOutDate()
							+ "','DD-MM-YYYY')+("
							+ String.valueOf(outdoorBlockSettingObj[0][0])
							+ ") >=to_date(to_char(SYSDATE,'DD-MM-YYYY'),'dd-mm-yyyy') ";
					Object[][] dataObj = model.getSqlModel().getSingleResult(
							sqlQuery);

					System.out.println("dataObj.length  " + dataObj.length);
					if (dataObj != null && dataObj.length == 0) {
						isApplicationBlock = "Y";
					}
				}

			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isApplicationBlock;
	}

	public String draftFunction() {
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		boolean result;
		Object[][] empFlow = generateEmpFlow(outdoor.getEcode(), "Outdoor", 1);

		String str = checkForBlockApplication();
		if (str.equals("N")) {
			if (outdoor.getOutvCode().equals("")
					|| outdoor.getOutvCode() == null) {
				result = model.saveRecords(outdoor, empFlow, request);
				if (result) {
					addActionMessage("Application submitted successfully.");
				} else {
					addActionMessage("Error occured");
				}
			} else {
				result = model.updateRecords(outdoor, empFlow, request);
				if (result) {
					addActionMessage("Application submitted successfully.");
				} else {
					addActionMessage("Error occured");
				}
			}
		} else {
			addActionMessage("Outdoor application has been blocked.Please contact administrator.");
		}

		input();
		return INPUT;
	}

	public String sendForApproval() {
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		Object[][] empFlow = generateEmpFlow(outdoor.getEcode(), "Outdoor", 1);

		/*
		 * boolean checkDup = model.checkDuplicateApp(outdoor); if(checkDup){
		 * addActionMessage("You are already applied otdoor visit for this
		 * date."); if(outdoor.getOutvCode().equals("")){ getNavigationPanel(1);
		 * }else{ getNavigationPanel(2); } setApplicationApproverData("0");
		 * outdoor.setEnableAll("Y"); return SUCCESS; }else
		 */

		if (empFlow == null || empFlow.length == 0) {
			addActionMessage("Reporting Structure Not Defined for the Employee\n"
					+ outdoor.getEmpName());
			addActionMessage("Please contact your HR Manager");
			if (outdoor.getOutvCode().equals("")) {
				getNavigationPanel(1);
			} else {
				getNavigationPanel(2);
			}
			setApplicationApproverData("0");
			outdoor.setEnableAll("Y");
			return SUCCESS;
		} else {

			String str = checkForBlockApplication();
			if (str.equals("N")) {
				boolean result = model.sendForApprovalFunction(outdoor,
						empFlow, request);
				if (result) {
					addActionMessage("Application Successfully Send For Approval. ");
					sendMailtoApprover(empFlow);
					sendSystemGeneratedMailToApplicant();
				} else {
					addActionMessage("Error occured while sending application.");
				}
				model.terminate();

			} else {
				addActionMessage("Outdoor application has been blocked.Please contact administrator.");
			}

			input();
			return INPUT;
		}
	}

	private void sendSystemGeneratedMailToApplicant() {
		try {
			String applicantID = outdoor.getUserEmpId();
			String applicationCode = outdoor.getOutvCode();

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("OUT DOOR VISIT MAIL FROM SYSTEM TO APPLICANT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			// templateQueryApp1.setParameter(1, applicantID);

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, applicantID);

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationCode);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationCode);

			templateApp.configMailAlert();
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void sendMailtoApprover(Object[][] empFlow) {
		try {
			String applicantID = outdoor.getUserEmpId();
			String approverID = String.valueOf(empFlow[0][0]);
			String applicationCode = outdoor.getOutvCode();
			String level = "1";

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("OUT DOOR VISIT MAIL FROM APPLICANT TO APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, applicantID);

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, approverID);

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationCode);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationCode);

			templateApp.configMailAlert();

			String[] link_parameter = new String[3];
			String[] link_labels = new String[3];
			String applicationType = "OutDoorOnline";
			link_parameter[0] = applicationType + "#" + applicantID + "#"
					+ applicationCode + "#" + "A" + "#" + "..." + "#"
					+ approverID + "#" + level;
			link_parameter[1] = applicationType + "#" + applicantID + "#"
					+ applicationCode + "#" + "R" + "#" + "..." + "#"
					+ approverID + "#" + level;
			link_parameter[2] = applicationType + "#" + applicantID + "#"
					+ applicationCode + "#" + "B" + "#" + "..." + "#"
					+ approverID + "#" + level;
			link_labels[0] = "Approve";
			link_labels[1] = "Reject";
			link_labels[2] = "Send Back";

			/*
			 * String[] link_parameter = new String[1]; String[] link_labels =
			 * new String[1]; String applicationType1 = "OutDoorOnline";
			 * link_parameter[0] = applicationType1 + "#" +
			 * String.valueOf(empFlow[0][0]) + "#applicationDtls#";
			 * 
			 * String link =
			 * "/attendance/OutdoorVisit_showDetailsForApproval.action?outdrCode="+
			 * outdoor.getOutvCode().trim(); // link= PPEncrypter.encrypt(link);
			 * link_parameter[0] += link; link_labels[0] =
			 * "Approve/Reject/SentBack";
			 */

			String[] keepInformToEmpID = request
					.getParameterValues("keepInformCode");
			if (keepInformToEmpID != null && keepInformToEmpID.length > 0) {
				/*
				 * for (int i = 0; i < keepInformToEmpID.length; i++) {
				 * System.out.println("Keep Inform : "+keepInformToEmpID[i]); }
				 */
				templateApp.sendApplicationMailToKeepInfo(keepInformToEmpID);
			}

			String module = "OutdoorVisit";
			String msgType = "A";
			templateApp.sendProcessManagerAlert(String.valueOf(empFlow[0][0]),
					module, msgType, outdoor.getOutvCode(), level);

			templateApp.addOnlineLink(request, link_parameter, link_labels);
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * CODED BY SHASHIKANT
	 */
	public String f9informTo() throws Exception {

		String[] empCode = request.getParameterValues("keepInformCode");
		String code = "";
		if (empCode != null && empCode.length > 0) {
			for (int i = 0; i < empCode.length; i++) {
				code += String.valueOf(empCode[i]) + ",";
			}
			code = code.substring(0, code.length() - 1);
		}

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC  ";

		query += " WHERE EMP_STATUS='S'";
		if (outdoor.getUserProfileDivision() != null
				&& outdoor.getUserProfileDivision().length() > 0)
			query += "AND EMP_DIV IN (" + outdoor.getUserProfileDivision()
					+ ")";
		if (!code.equals("")) {
			query += " AND EMP_ID NOT IN(" + code + ")";
		}
		query += "ORDER BY EMP_ID ";
		String[] headers = { "Token", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "informToken", "informName", "informCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String addKeepInfo() throws Exception {
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		model.addInformList(outdoor, keepInformCode, keepInform, "add");
		model.terminate();
		outdoor.setInformCode("");
		outdoor.setInformName("");
		outdoor.setInformToken("");
		setApplicationApproverData("add");

		if (outdoor.getOutvCode().equals("") || outdoor.getOutvCode() == null) {
			getNavigationPanel(1);
			outdoor.setApplnStatus("");
		} else {
			getNavigationPanel(2);
			outdoor.setApplnStatus("Dr");
		}
		outdoor.setEnableAll("Y");
		return SUCCESS;

	}

	public String removeKeepInfo() throws Exception {
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		model.addInformList(outdoor, keepInformCode, keepInform, "remove");
		model.terminate();
		outdoor.setInformCode("");
		outdoor.setInformName("");
		outdoor.setInformToken("");
		setApplicationApproverData("remove");

		if (outdoor.getOutvCode().equals("") || outdoor.getOutvCode() == null) {
			getNavigationPanel(1);
			outdoor.setApplnStatus("");
		} else {
			getNavigationPanel(2);
			outdoor.setApplnStatus("Dr");
		}
		outdoor.setEnableAll("Y");
		return SUCCESS;

	}

	public String input() {
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			model.getPendingList(outdoor, request);
			model.terminate();
			outdoor.setListType("pending");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String addNew() {
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		//Added for My time card by janisha on 6 jan 2012
		String source = request.getParameter("src");
		String timeCardDate = request.getParameter("timeCardDate");
		System.out.println("timeCardDate  "+timeCardDate);
		if (timeCardDate != null) {
			outdoor.setOutDate(timeCardDate);
		}
		outdoor.setSource(source);
		System.out.println("source  " + source);
		//End Added for My time card by janisha on 6 jan 2012
		outdoor.setOutvCode("");
		model.getCurrenUserInformation(outdoor);
		setApplicationApproverData("0");
		model.terminate();
		getNavigationPanel(1);
		outdoor.setEnableAll("Y");
		return SUCCESS;
	}

	public String viewApplicationFunction() {
		String hiddenVisitID = request.getParameter("hiddenVisitID");
		String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			model.viewApplicationFunction(outdoor, hiddenVisitID);
			model.setKeepInform(outdoor);
			model.setApproverComments(outdoor);
			setApplicationApproverData("0");
			model.setLevel(outdoor);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (hiddenStatus.equals("D")) {
			getNavigationPanel(2);
			outdoor.setEnableAll("Y");
			outdoor.setApplnStatus("Dr");
		} else if (hiddenStatus.equals("P")) {
			getNavigationPanel(3);
			outdoor.setEnableAll("N");
			outdoor.setApplnStatus("Pn");
		} else if (hiddenStatus.equals("B")) {
			getNavigationPanel(2);
			outdoor.setEnableAll("Y");
			outdoor.setApplnStatus("Bk");
		} else if (hiddenStatus.equals("A")) {
			getNavigationPanel(3);
			outdoor.setEnableAll("N");
			outdoor.setApplnStatus("Ap");
		} else if (hiddenStatus.equals("R")) {
			getNavigationPanel(3);
			outdoor.setEnableAll("N");
			outdoor.setApplnStatus("Rj");
		}

		return SUCCESS;
	}

	public String getApprovedList() throws Exception {
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			String userId = outdoor.getUserEmpId();
			model.getApprovedList(outdoor, request, userId);
			outdoor.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		getNavigationPanel(3);
		return INPUT;
	}

	public String getRejectedList() throws Exception {
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			String userId = outdoor.getUserEmpId();
			model.getRejectedList(outdoor, request, userId);
			outdoor.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return INPUT;
	}

	public String backFunction() {
		input();
		return INPUT;
	}

	public String deleteFunction() {
		OutdoorVisitModel model = new OutdoorVisitModel();
		model.initiate(context, session);
		boolean result = model.deleteApplication(outdoor);
		if (result) {
			addActionMessage("Record deleted successfully.");
		}
		model.terminate();
		input();
		return INPUT;
	}

	public String setApplicationApproverData(String str) {
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(outdoor.getEcode(), "Outdoor",
					1);
			model.setApplicationApproverData(outdoor, empFlow);

			String keepInformCode[] = request
					.getParameterValues("keepInformCode");
			String keepInform[] = request.getParameterValues("keepInform");

			if (str.equals("0") && keepInformCode != null
					&& keepInformCode.length > 0) {
				model.addInformList(outdoor, keepInformCode, keepInform, str);
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApplicationApproverData : " + e);
			e.printStackTrace();
		}

		if (outdoor.getOutvCode().equals("") || outdoor.getOutvCode() == null) {
			getNavigationPanel(1);
		} else {
			getNavigationPanel(2);
		}
		outdoor.setEnableAll("Y");
		return SUCCESS;
	}

	public String setAttendanceData() {
		try {
			OutdoorVisitModel model = new OutdoorVisitModel();
			model.initiate(context, session);
			String outDoorVisitDate = request.getParameter("outDoorVisitDate");
			model.setAttendanceData(outdoor, outDoorVisitDate); // For showing
			// Attendance
			// Record
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showAttendanceRecord";
	}

}