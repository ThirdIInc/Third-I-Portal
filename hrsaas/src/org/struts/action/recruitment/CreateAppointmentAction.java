package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.CreateAppointment;
import org.paradyne.model.recruitment.CreateAppointmentModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0540
 * 
 */
public class CreateAppointmentAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CreateAppointmentAction.class);

	CreateAppointment createAppointment;

	public void prepare_local() throws Exception {
		createAppointment = new CreateAppointment();
		createAppointment.setMenuCode(889);
	}

	public Object getModel() {
		return createAppointment;
	}

	/**
	 * @return the createOffer
	 */
	public CreateAppointment getCreateOffer() {
		return createAppointment;
	}

	/**
	 * @param createOffer
	 *            the createOffer to set
	 */
	public void setCreateOffer(CreateAppointment createOffer) {
		this.createAppointment = createOffer;
	}

	/**
	 * @method input
	 * @purpose to display pending offer and appointment list
	 * @return String
	 */
	public String input() {
		try {
			String appointmentStatus = createAppointment.getAppointmentStatus();
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			if (appointmentStatus.equals("")
					|| appointmentStatus.equals("null")
					|| appointmentStatus.equals(null)) {
				createAppointment.setAppointmentStatus("D");
				createAppointment.setAppointmentDueFlag(true);
			} else {
				createAppointment.setAppointmentStatus(appointmentStatus);
				setAppointmentStatus();
			}
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);
			model.showAppointmentList(createAppointment, request, msg, msg1,
					msg2, msg3, msg4, msg5, msg6, msg7);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method dupinput
	 * @purpose to display pending offer and appointment list when clicking
	 *          cancel button in view offer and view appointment
	 * @return String
	 */
	public String dupinput() {
		createAppointment.setAppointmentStatus("D");
		createAppointment.setAppointmentDueFlag(true);
		String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
		CreateAppointmentModel model = new CreateAppointmentModel();
		model.initiate(context, session);
		model.showAppointmentList(createAppointment, request, msg, msg1, msg2,
				msg3, msg4, msg5, msg6, msg7);
		model.terminate();
		return "success";
	}

	/**
	 * @method viewCV
	 * @purpose to view the resume of the candidate
	 * @return String
	 */
	public void viewCV() throws Exception {
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
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/resume/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
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
	}

	/**
	 * @method showAppointmentList
	 * @purpose to display the offer details in tabular format as per the
	 *          selected status
	 * @return type : String
	 */
	public String showAppointmentList() throws Exception {
		logger.info("inside showAppointmentList");
		String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
		String status = request.getParameter("status"); // getting
		// statusAppointment
		// which passed as a
		// request parameter
		// from form

		createAppointment.setAppointmentStatus(status);
		// createOffer.setOfferStatus("D");
		// createOffer.setOfferDueFlag("true");

		setAppointmentStatus();

		CreateAppointmentModel model = new CreateAppointmentModel();
		model.initiate(context, session);

		/**
		 * call showOfferList(createOffer) method of CreateOfferModel class to
		 * retrieve the offer list from HRMS_REC_OFFER table
		 */
		model.showAppointmentList(createAppointment, request, msg, msg1, msg2,
				msg3, msg4, msg5, msg6, msg7);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * @method setAppointmentStatus
	 * @purpose to set the appointment status as header
	 * @return String
	 */
	public String setAppointmentStatus() {
		logger.info("inside showAppointmentList");

		String appointmentListStatus = "";
		String status = createAppointment.getAppointmentStatus();

		if (status.equals("D") || status.equals(" ")) { // if status is D
			appointmentListStatus = "Appointment Due List"; // set header status
			// as Appointment
			// Due List

			createAppointment.setAppointmentDueFlag(true);
		} // if statement ends
		else if (status.equals("P")) { // if status is P
			appointmentListStatus = "Pending List"; // set header status as
			// Pending List

			createAppointment.setAppointmentApprPendingFlag(true);
		} // else if statement ends
		else if (status.equals("A")) { // if status is A
			appointmentListStatus = "Approved List"; // set header status as
			// Approved List

			createAppointment.setAppointmentApprFlag(true);
		} // else if statement ends
		else if (status.equals("R")) { // if status is R
			appointmentListStatus = "Rejected List"; // set header status as
			// Rejected List

			createAppointment.setAppointmentApprRejFlag(true);
		} // else if statement ends
		else if (status.equals("I")) { // if status is I
			appointmentListStatus = "Appointment Issued List"; // set header
			// status as
			// Appointment
			// Issued List

			createAppointment.setAppointmentIssueFlag(true);
		} // else if statement ends
		else if (status.equals("OA")) { // if status is OA
			appointmentListStatus = "Appointment Accepted List"; // set
			// header
			// status as
			// Appointment
			// Accepted
			// List

			createAppointment.setAppointmentAccFlag(true);
		} // else if statement ends
		else if (status.equals("S")) { // if status is OR
			appointmentListStatus = "Appointment Rejected List"; // set
			// header
			// status as
			// Appointment
			// Rejected
			// List

			createAppointment.setAppointmentRejFlag(true);
		} // else if statement ends
		else if (status.equals("C")) { // if status is C
			appointmentListStatus = "Appointment Cancelled List"; // set
			// header
			// status as
			// Appointment
			// Canceled
			// List

			createAppointment.setAppointmentCancelFlag(true);
		} // else if statement ends

		request.setAttribute("appointmentListStatus", appointmentListStatus); // set
		// header
		// status
		// as a
		// request
		// attribute
		return "success";
	}

	/**
	 * @method toAppointmentDetails
	 * @purpose to navigate to the Appointment Details form
	 * @return String
	 */
	public String toAppointmentDetails() {
		try {
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);
			String status = createAppointment.getAppointmentStatus();
			if (status.equals("D")) {
				request.setAttribute("status", "Y");
			} else if (status.equals("I")  || status.equals("C") || status.equals("S")) {
				request.setAttribute("status", "N");
			}

			model.getKeepInformedSavedRecord(createAppointment);
			model.terminate();
			// For adding data path BEGINS
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName
					+ "/Recruitment/";
			System.out.println("data path : " + fileDataPath);
			createAppointment.setDataPath(fileDataPath);
			// For adding data path ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "appointmentDetails";
	}

	/**
	 * @method cancelAppointment
	 * @purpose to cancel appointment
	 * @return String
	 */
	public String cancelAppointment() {
		try {
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);
			boolean result = model.cancelAppointment(createAppointment);
			model.terminate();
			if (result) {
				addActionMessage("Appointment cancelled successfully");
			} else {
				addActionMessage("Appointment can not be cancelled");
			}
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Created by Guru on 22/06/2009
	 * 
	 */

	public String getAppointmentDetailsOnSearch() {
		try {
			getNavigationPanel(1);
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			createAppointment.setMyPageApp("");
			createAppointment.setShow("");
			createAppointment.setSearchFlag(true);
			createAppointment.setChkSerch("true");
			String appointmentListStatus = "";
			if (createAppointment.getAppointmentStatus().equals("D")
					|| createAppointment.getAppointmentStatus().equals(" ")) {
				createAppointment.setAppointmentDueFlag(true);
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				appointmentListStatus = "Appointment Due List";
			} else if (createAppointment.getAppointmentStatus().equals("P")) {
				appointmentListStatus = "Pending List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprPendingFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("A")) {
				appointmentListStatus = "Approved List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("R")) {
				appointmentListStatus = "Rejected List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("I")) {
				appointmentListStatus = "Appointment Issued List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				createAppointment.setAppointmentIssueFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("OA")) {
				appointmentListStatus = "Appointment Accepted List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.accFrdate");
				msg7 = getMessage("app.accTodate");
				createAppointment.setAppointmentAccFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("S")) {
				appointmentListStatus = "Appointment Rejected List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.RejFrdate");
				msg7 = getMessage("app.RejTodate");
				createAppointment.setAppointmentRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("S")) {
				appointmentListStatus = "Appointment Rejected List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.RejFrdate");
				msg7 = getMessage("app.RejTodate");
				createAppointment.setAppointmentRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("C")) {
				appointmentListStatus = "Appointment Cancelled List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.canFrdate");
				msg7 = getMessage("app.canTodate");
				createAppointment.setAppointmentCancelFlag(true);
			}
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);
			model.showAppointmentList(createAppointment, request, msg, msg1,
					msg2, msg3, msg4, msg5, msg6, msg7);
			request
					.setAttribute("appointmentListStatus",
							appointmentListStatus);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when Clear button is clicked.
	 * 
	 * @return
	 */
	public String reset() {
		try {
			String appointmentListStatus = "";
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			createAppointment.setAppointmentApprPendingFlag(false);
			createAppointment.setAppointmentApprFlag(false);
			createAppointment.setAppointmentApprRejFlag(false);
			createAppointment.setAppointmentIssueFlag(false);
			createAppointment.setAppointmentAccFlag(false);
			createAppointment.setAppointmentRejFlag(false);
			createAppointment.setAppointmentCancelFlag(false);
			createAppointment.setChkSerch("");
			createAppointment.setReqCodeFlag(false);
			createAppointment.setCandFlag(false);
			createAppointment.setClearFlag(false);
			createAppointment.setPositionFlag(false);
			createAppointment.setHiringFlag(false);
			createAppointment.setDueDaysFlag(false);
			createAppointment.setSearchRequisitionCode("");
			createAppointment.setSearchRequisitionCode("");
			createAppointment.setSearchHidRequisitionCode("");
			createAppointment.setSearchPosition("");
			createAppointment.setSearchPositionId("");
			createAppointment.setSearchCandCode("");
			createAppointment.setSearchCandName("");
			createAppointment.setSearchDueSinceDays("");
			createAppointment.setSearchHiringMgr("");
			createAppointment.setSearchHiringMgrId("");
			createAppointment.setAppToDate("");
			createAppointment.setAppFrmDate("");
			createAppointment.setAppAccFrmDate("");
			createAppointment.setAppAccToDate("");
			createAppointment.setAppRejFrmDate("");
			createAppointment.setAppRejToDate("");
			createAppointment.setAppCanFrmDate("");
			createAppointment.setAppCanToDate("");
			createAppointment.setAppFromFlag(false);
			createAppointment.setAppToFlag(false);
			createAppointment.setAccFromFlag(false);
			createAppointment.setAccToFlag(false);
			createAppointment.setRejFromFlag(false);
			createAppointment.setRejToFlag(false);
			createAppointment.setCanFromFlag(false);
			createAppointment.setCanToFlag(false);
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);

			if (createAppointment.getAppointmentStatus().equals("D")
					|| createAppointment.getOfferStatus().equals(" ")) {
				createAppointment.setAppointmentDueFlag(true);
				appointmentListStatus = "Appointment Due List";
			} else if (createAppointment.getAppointmentStatus().equals("P")) {
				appointmentListStatus = "Pending List";
				createAppointment.setAppointmentApprPendingFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("A")) {
				appointmentListStatus = "Approved List";
				createAppointment.setAppointmentApprFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("R")) {
				appointmentListStatus = "Rejected List";
				createAppointment.setAppointmentApprRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("I")) {
				appointmentListStatus = "Appointment Issued List";
				createAppointment.setAppointmentIssueFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("OA")) {
				appointmentListStatus = "Appointment Accepted List";
				createAppointment.setAppointmentAccFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("S")) {
				appointmentListStatus = "Appointment Rejected List";
				createAppointment.setAppointmentRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("C")) {
				appointmentListStatus = "Appointment Cancelled List";
				createAppointment.setAppointmentCancelFlag(true);
			}
			model.showAppointmentList(createAppointment, request, msg, msg1,
					msg2, msg3, msg4, msg5, msg6, msg7);
			request
					.setAttribute("appointmentListStatus",
							appointmentListStatus);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String f9Reqs() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME, TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY'), HRMS_REC_REQS_HDR.REQS_CODE "
				+ "FROM HRMS_REC_REQS_HDR INNER JOIN HRMS_REC_REQS_VACDTL ON "
				+ "(HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_REQS_VACDTL.REQS_CODE) "
				+ "WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') "
				+ "ORDER BY HRMS_REC_REQS_HDR.REQS_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("reqs.code"),
				getMessage("required.date") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "searchRequisitionCode",
				"searchHidRequiredbyDate", "searchHidRequisitionCode" };

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

	public String f9Candidate() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_CAND_DATABANK.CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY UPPER(HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("cand.name") };

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

		String[] fieldNames = { "searchCandName", "searchCandCode" };

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

	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT HRMS_RANK.RANK_NAME, HRMS_RANK.RANK_ID FROM HRMS_RANK ORDER BY UPPER(HRMS_RANK.RANK_NAME)";

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

		String[] fieldNames = { "searchPosition", "searchPositionId" };

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

	public String f9Hiring() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S' ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("hiring.mgr") };

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

		String[] fieldNames = { "searchHiringMgr", "searchHiringMgrId" };

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

	public String showPageList() throws Exception {
		try {
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			String status = request.getParameter("status"); // getting statusAppointment
			// which passed as a request parameter from form
			createAppointment.setAppointmentStatus(status);
			// setAppointmentStatus();
			String appointmentListStatus = "";
			if (createAppointment.getAppointmentStatus().equals("D")
					|| createAppointment.getAppointmentStatus().equals(" ")) {
				createAppointment.setAppointmentDueFlag(true);
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				appointmentListStatus = "Appointment Due List";
			} else if (createAppointment.getAppointmentStatus().equals("P")) {
				appointmentListStatus = "Pending List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprPendingFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("A")) {
				appointmentListStatus = "Approved List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("R")) {
				appointmentListStatus = "Rejected List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				createAppointment.setAppointmentApprRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("I")) {
				appointmentListStatus = "Appointment Issued List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				createAppointment.setAppointmentIssueFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("OA")) {
				appointmentListStatus = "Appointment Accepted List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.accFrdate");
				msg7 = getMessage("app.accTodate");
				createAppointment.setAppointmentAccFlag(true);
			}  else if (createAppointment.getAppointmentStatus().equals("S")) {
				appointmentListStatus = "Appointment Rejected List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.RejFrdate");
				msg7 = getMessage("app.RejTodate");
				createAppointment.setAppointmentRejFlag(true);
			} else if (createAppointment.getAppointmentStatus().equals("C")) {
				appointmentListStatus = "Appointment Cancelled List";
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("app.frdate");
				msg5 = getMessage("app.todate");
				msg6 = getMessage("app.canFrdate");
				msg7 = getMessage("app.canTodate");
				createAppointment.setAppointmentCancelFlag(true);
			}
			CreateAppointmentModel model = new CreateAppointmentModel();
			model.initiate(context, session);
			/**
			 * call showOfferList(createOffer) method of CreateOfferModel class
			 * to retrieve the offer list from HRMS_REC_OFFER table
			 */
			model.showAppointmentList(createAppointment, request, msg, msg1,
					msg2, msg3, msg4, msg5, msg6, msg7);
			request
					.setAttribute("appointmentListStatus",
							appointmentListStatus);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
