/**
 * 
 */
package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.CreateOffer;
import org.paradyne.model.recruitment.CreateOfferModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0540
 * 
 */
public class CreateOfferAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CreateOfferAction.class);

	CreateOffer createOffer = null;

	public void prepare_local() throws Exception {
		createOffer = new CreateOffer();
		createOffer.setMenuCode(779);
	}

	public Object getModel() {
		return createOffer;
	}

	/**
	 * @return the createOffer
	 */
	public CreateOffer getCreateOffer() {
		return createOffer;
	}

	/**
	 * @param createOffer
	 *            the createOffer to set
	 */
	public void setCreateOffer(CreateOffer createOffer) {
		this.createOffer = createOffer;
	}

	/**
	 * @method input
	 * @purpose to display pending offer and appointment list
	 * @return String
	 */
	public String input() {
		try {
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			String offerStatus = createOffer.getOfferStatus();
			msg = getMessage("reqs.code");
			msg1 = getMessage("cand.name");
			msg2 = getMessage("position");
			msg3 = getMessage("hiring.mgr");
			msg4 = getMessage("due");
			if (offerStatus.equals("") || offerStatus.equals("null")
					|| offerStatus.equals(null)) {
				createOffer.setOfferStatus("D");
				createOffer.setOfferDueFlag("true");
			} else {
				createOffer.setOfferStatus(offerStatus);
				setOfferStatus();
			}
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
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
		try {
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			createOffer.setOfferStatus("D");
			createOffer.setOfferDueFlag("true");
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	 * @method showOfferList
	 * @purpose to display the offer details in tabular format as per the
	 *          selected status
	 * @return type : String
	 */
	public String showOfferList() throws Exception {
		try {
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			String status = request.getParameter("status"); // getting statusOffer which passed as a request parameter
			// from form
			createOffer.setOfferStatus(status);
			setOfferStatus();
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			/**
			 * call showOfferList(createOffer) method of CreateOfferModel class to
			 * retrieve the offer list from HRMS_REC_OFFER table
			 */
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @method setOfferStatus
	 * @purpose to set the offer status as header
	 * @return String
	 */
	public String setOfferStatus() {
		String offerListStatus = "";
		try {
			String status = createOffer.getOfferStatus();
			if (status.equals("D") || status.equals(" ")) { // if status is D
				offerListStatus = "Offer Due List"; // set header status as Offer Due List
				createOffer.setOfferDueFlag("true");
			} else if (status.equals("P")) { // if status is P
				offerListStatus = "Pending List"; // set header status as Pending List
				createOffer.setOfferApprPendingFlag(true);
			} else if (status.equals("A")) { // if status is A
				offerListStatus = "Approved List"; // set header status as Approved List
				createOffer.setOfferApprFlag(true);
			} else if (status.equals("R")) { // if status is R
				offerListStatus = "Rejected List"; // set header status as Rejected List
				createOffer.setOfferApprRejFlag(true);
			} else if (status.equals("I")) { // if status is I
				offerListStatus = "Offer Issued List"; // set header status as Offer Issued List
				createOffer.setOfferIssueFlag(true);
			} else if (status.equals("OA")) { // if status is OA
				offerListStatus = "Offer Accepted List"; // set header status as Offer Accepted List
				createOffer.setOfferAccFlag(true);
			} else if (status.equals("S")) { // if status is OR
				offerListStatus = "Offer Rejected List"; // set header status as On Offer Rejected List
				createOffer.setOfferRejFlag(true);
			} else if (status.equals("C")) { // if status is C
				offerListStatus = "Offer Cancelled List"; // set header status as Offer Canceled List
				createOffer.setOfferCancelFlag(true);
			}
			request.setAttribute("offerListStatus", offerListStatus); // set header status as a request attribute
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method toOfferDetails
	 * @purpose to navigate the control to Offer Details form
	 * @return String
	 */
	public String toOfferDetails() {
		try {
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			String status = createOffer.getOfferStatus();
			if (status.equals("D")) {
				request.setAttribute("status", "Y");
			} else if (status.equals("I") || status.equals("C") || status.equals("S")) {
				request.setAttribute("status", "N");
			}
			model.getKeepInformedSavedRecord(createOffer);
			model.terminate();
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			System.out.println("data path : "+fileDataPath);
			createOffer.setDataPath(fileDataPath);
			//For adding data path ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "offerDetails";
	}

	/**
	 * @method cancelOffer
	 * @purpose to cancel the offer
	 * @return String
	 */
	public String cancelOffer() {
		try {
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			boolean result = model.cancelOffer(createOffer);
			model.terminate();
			if (result) {
				addActionMessage("Offer cancelled successfully");
			} else {
				addActionMessage("Offer can not be cancelled");
			}
			// model.showAppointmentList(createOffer,request);
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Created by Guru Prasad on 20/06/2009
	 * 
	 */

	public String getOfferDetailsOnSearch() {
		try {
			getNavigationPanel(1);
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			createOffer.setMyPageOffer("");
			createOffer.setShow("");
			createOffer.setSearchFlag(true);
			createOffer.setChkSerch("true");
			String offerListStatus = "";
			if (createOffer.getOfferStatus().equals("D")
					|| createOffer.getOfferStatus().equals(" ")) {
				createOffer.setOfferDueFlag("true");
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Offer Due List";
			} else if (createOffer.getOfferStatus().equals("P")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Pending List";
				createOffer.setOfferApprPendingFlag(true);
			} else if (createOffer.getOfferStatus().equals("A")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Approved List";
				createOffer.setOfferApprFlag(true);
			} else if (createOffer.getOfferStatus().equals("R")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Rejected List";
				createOffer.setOfferApprRejFlag(true);
			} else if (createOffer.getOfferStatus().equals("I")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				offerListStatus = "Offer Issued List";
				createOffer.setOfferIssueFlag(true);
			} else if (createOffer.getOfferStatus().equals("OA")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.accFrdate");
				msg7 = getMessage("offer.accTodate");
				offerListStatus = "Offer Accepted List";
				createOffer.setOfferAccFlag(true);
			} else if (createOffer.getOfferStatus().equals("S")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.RejFrdate");
				msg7 = getMessage("offer.RejTodate");
				offerListStatus = "Offer Rejected List";
				createOffer.setOfferRejFlag(true);
			} else if (createOffer.getOfferStatus().equals("C")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.canFrdate");
				msg7 = getMessage("offer.canTodate");
				offerListStatus = "Offer Cancelled List";
				createOffer.setOfferCancelFlag(true);
			}
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
			request.setAttribute("offerListStatus", offerListStatus);
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
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			String offerListStatus = "";
			createOffer.setOfferApprPendingFlag(false);
			createOffer.setOfferApprFlag(false);
			createOffer.setOfferApprRejFlag(false);
			createOffer.setOfferIssueFlag(false);
			createOffer.setOfferAccFlag(false);
			createOffer.setOfferRejFlag(false);
			createOffer.setOfferCancelFlag(false);
			createOffer.setSearchReqCode("");
			createOffer.setSearchRequisitionCode("");
			createOffer.setSearchHidRequisitionCode("");
			createOffer.setSearchPosition("");
			createOffer.setSearchPositionId("");
			createOffer.setSearchCandCode("");
			createOffer.setSearchCandName("");
			createOffer.setSearchDueDaysId("");
			createOffer.setSearchDueSinceDays("");
			createOffer.setSearchHiringMgr("");
			createOffer.setSearchHiringMgrId("");
			createOffer.setSearchFlag(false);
			createOffer.setOffToDate("");
			createOffer.setOffFrmDate("");
			createOffer.setOffAccFrmDate("");
			createOffer.setOffAccToDate("");
			createOffer.setOffRejFrmDate("");
			createOffer.setOffRejToDate("");
			createOffer.setOffCanFrmDate("");
			createOffer.setOffCanToDate("");
			createOffer.setOfferFromFlag(false);
			createOffer.setOfferToFlag(false);
			createOffer.setAccFromFlag(false);
			createOffer.setAccToFlag(false);
			createOffer.setRejFromFlag(false);
			createOffer.setRejToFlag(false);
			createOffer.setCanFromFlag(false);
			createOffer.setCanToFlag(false);
			createOffer.setChkSerch("");
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			if (createOffer.getOfferStatus().equals("D")) {
				createOffer.setOfferDueFlag("true");
				offerListStatus = "Offer Due List";
			} else if (createOffer.getOfferStatus().equals("OA")) {
				offerListStatus = "Offer Accepted List";
				createOffer.setOfferAccFlag(true);
			} else if (createOffer.getOfferStatus().equals("S")) {
				offerListStatus = "Offer Rejected List";
				createOffer.setOfferRejFlag(true);
			} else if (createOffer.getOfferStatus().equals("C")) {
				offerListStatus = "Offer Cancelled List";
				createOffer.setOfferCancelFlag(true);
			} else if (createOffer.getOfferStatus().equals("I")) {
				offerListStatus = "Offer Issued List";
				createOffer.setOfferIssueFlag(true);
			} else if (createOffer.getOfferStatus().equals("P")) {
				offerListStatus = "Pending List";
				createOffer.setOfferApprPendingFlag(true);
			} else if (createOffer.getOfferStatus().equals("A")) {
				offerListStatus = "Approved List";
				createOffer.setOfferApprFlag(true);
			} else if (createOffer.getOfferStatus().equals("R")) {
				offerListStatus = "Rejected List";
				createOffer.setOfferApprRejFlag(true);
			}
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
			request.setAttribute("offerListStatus", offerListStatus);
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
				+" FROM HRMS_REC_REQS_HDR "
				+" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_REQS_VACDTL.REQS_CODE) "
				+" WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') "
				+" ORDER BY TO_DATE(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY') DESC";

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
		String query = "SELECT HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_CAND_DATABANK.CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY UPPER(HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME) ";

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
		String query = "SELECT HRMS_RANK.RANK_NAME, HRMS_RANK.RANK_ID FROM HRMS_RANK WHERE HRMS_RANK.RANK_STATUS='A' ORDER BY UPPER(HRMS_RANK.RANK_NAME)";

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
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S' ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

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

	/**
	 * Filter will not apply when you click on page (or) select page from select
	 * box
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showPageList() throws Exception {
		try {
			String msg = "", msg1 = "", msg2 = "", msg3 = "", msg4 = "", msg5 = "", msg6 = "", msg7 = "";
			String status = request.getParameter("status"); // getting statusOffer which passed as a
			// request parameter from form
			createOffer.setOfferStatus(status);
			// createOffer.setAppointmentStatus("D");
			// createOffer.setAppointmentDueFlag(true);
			// setOfferStatus();
			String offerListStatus = "";
			if (createOffer.getOfferStatus().equals("D")
					|| createOffer.getOfferStatus().equals(" ")) {
				createOffer.setOfferDueFlag("true");
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Offer Due List";
			} else if (createOffer.getOfferStatus().equals("P")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Pending List";
				createOffer.setOfferApprPendingFlag(true);
			} else if (createOffer.getOfferStatus().equals("A")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Approved List";
				createOffer.setOfferApprFlag(true);
			} else if (createOffer.getOfferStatus().equals("R")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("due");
				offerListStatus = "Rejected List";
				createOffer.setOfferApprRejFlag(true);
			} else if (createOffer.getOfferStatus().equals("I")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				offerListStatus = "Offer Issued List";
				createOffer.setOfferIssueFlag(true);
			} else if (createOffer.getOfferStatus().equals("OA")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.accFrdate");
				msg7 = getMessage("offer.accTodate");
				offerListStatus = "Offer Accepted List";
				createOffer.setOfferAccFlag(true);
			} else if (createOffer.getOfferStatus().equals("S")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.RejFrdate");
				msg7 = getMessage("offer.RejTodate");
				offerListStatus = "Offer Rejected List";
				createOffer.setOfferRejFlag(true);
			} else if (createOffer.getOfferStatus().equals("C")) {
				msg = getMessage("reqs.code");
				msg1 = getMessage("cand.name");
				msg2 = getMessage("position");
				msg3 = getMessage("hiring.mgr");
				msg4 = getMessage("offer.frdate");
				msg5 = getMessage("offer.todate");
				msg6 = getMessage("offer.canFrdate");
				msg7 = getMessage("offer.canTodate");
				offerListStatus = "Offer Cancelled List";
				createOffer.setOfferCancelFlag(true);
			}
			CreateOfferModel model = new CreateOfferModel();
			model.initiate(context, session);
			/**
			 * call showOfferList(createOffer) method of CreateOfferModel class
			 * to retrieve the offer list from HRMS_REC_OFFER table
			 */
			model.showOfferList(createOffer, request, msg, msg1, msg2, msg3,
					msg4, msg5, msg6, msg7);
			request.setAttribute("offerListStatus", offerListStatus);
			//model.showOfferList(createOffer,request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
