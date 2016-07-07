package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveAdminApproval;
import org.paradyne.bean.leave.LeaveApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.TravelManagement.TravelProcess.TravelQuickBookingModel;
import org.paradyne.model.leave.LeaveAdminApprovalModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class LeaveAdminApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	LeaveAdminApproval levApp;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		levApp = new LeaveAdminApproval();
		levApp.setMenuCode(2223); // setting menu code

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return levApp;
	}

	public LeaveAdminApproval getLevApp() {
		return levApp;
	}

	public void setLevApp(LeaveAdminApproval levApp) {
		this.levApp = levApp;
	}

	public String callstatus() {

		try {
			LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(levApp, request);
			levApp.setListType("pending");
			levApp.setSearchStatus("P");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
			model.initiate(context, session);
			//model.collect(levApp, "P", request); // display list of pending records
			//levApp.setPen("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getApprovedList() throws Exception {

		try {
			LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
			model.initiate(context, session);
			model.getApprovedList(levApp, request, levApp.getUserEmpId());
			levApp.setListType("approved");
			levApp.setSearchStatus("A");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return SUCCESS;
	}

	public String getRejectedList() throws Exception {

		try {
			LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
			model.initiate(context, session);
			model.getRejectedList(levApp, request, levApp.getUserEmpId());
			levApp.setListType("rejected");
			levApp.setSearchStatus("R");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}

	public String search() {
		try {
			LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
			model.initiate(context, session);
			if (levApp.getSearchStatus().equals("A")) {
				getApprovedList();
			} else if (levApp.getSearchStatus().equals("R")) {
				getRejectedList();
			} else {
				levApp.setListType("pending");
				model.getAllTypeOfApplications(levApp, request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR DISPLAYING RECORDS FOR PENDING,APPROVED,REJECTED
	 * LIST OF PENDING LEAVE APPLICATIONS
	 * 
	 * @return String
	 */
	public String callstatusBefore() {
		LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
		model.initiate(context, session);
		String status = "";
		String stat = "";
		try {
			status = request.getParameter("status");
			status = String.valueOf(status.charAt(0));
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in callstatus-------" + e);
		}// end of catch
		if (status == null || status.equals("")) {
			status = "P";
		}// end of if
		else if (status.equals("F")) {
			status = "P";
		}// end of else if

		if (status.equals("P")) {
			levApp.setPen("true");
			stat = "Pending List";
		}// end of if
		else if (status.equals("A")) {
			levApp.setApp("true");
			stat = "Approved List";

		}// end of else if
		else if (status.equals("R")) {
			levApp.setRej("true");
			stat = "Rejected List";
		}// end of else if
		else if (status.equals("H")) {
			levApp.setHol("true");
			stat = "On-HOld List";
		}// end of else if

		if (!(status.equals("P"))) {
			levApp.setApprflag("true");
		}// end of if
		request.setAttribute("stat", stat);
		model.collect(levApp, status, request);
		model.terminate();
		return "success";

	}// end of callstatus

	public String callPage1() throws Exception {
		LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
		model.initiate(context, session);
		//callstatus();
		model.terminate();

		return SUCCESS;
	}

	public String callPage2() throws Exception {
		LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
		model.initiate(context, session);
		//callstatus();
		model.terminate();

		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SAVE
	 * 
	 * @return String
	 */
	/*public String saveStatus() {

		String applStatus = "";
		boolean result = false;

		LeaveAdminApprovalModel model = new LeaveAdminApprovalModel();
		model.initiate(context, session);
		String[] leaveAppNo = request.getParameterValues("leaveAppNo"); // application
		// code
		String[] empCode = request.getParameterValues("empCode"); // employee
		// id
		String[] status = request.getParameterValues("checkStatus"); // status
		String[] level = request.getParameterValues("level"); // level
		String[] remarks = request.getParameterValues("statusRemark");// remark
		String[] date = request.getParameterValues("date");//date
		String approverId = levApp.getUserEmpId();
		;
		if (status != null && status.length > 0) {
			for (int i = 0; i < leaveAppNo.length; i++) {
				if (!(status[i].equals("P"))) {
					applStatus = model.approveReject(request, empCode[i],
							leaveAppNo[i], status[i], remarks[i], approverId,
							level[i]);
					result = true;

				}// end of if

			}// end of for loop
		}// end of if
		if (result) {
			addActionMessage(getMessage("save"));
		}// end of if
		model.collect(levApp, "P", request);
		model.terminate();
		return "success";
	}// end of saveStatus
*/
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
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME "
				+ "  , EMP_ID" + " FROM HRMS_EMP_OFFC ";

		query += "	ORDER BY EMP_ID ASC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "searchEmpToken", "searchEmpName",
				"searchEmpCode" };

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

	/**
	 * THIS METHOD IS USED FOR RESET FORM
	 * 
	 * @return String
	 */
	public String reset() {
		levApp.setApp("");
		levApp.setApprflag("");
		levApp.setCheckStatus("");
		levApp.setDate("");
		levApp.setEmpCode("");
		levApp.setEmpName("");
		levApp.setHol("");
		levApp.setLevel("");
		levApp.setPen("");
		levApp.setRej("");
		levApp.setStatus("");
		levApp.setLeaveAppNo("");
		return SUCCESS;
	}// end of reset
	
	public String f9branchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Branch Code", "Branch Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchCode", "branchName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String f9divisionaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION   ORDER BY  DIV_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Divison Code", "Division Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionCode", "divisionName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}// end of class
