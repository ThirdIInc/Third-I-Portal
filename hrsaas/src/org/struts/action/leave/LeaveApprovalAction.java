package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.leave.LeaveApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class LeaveApprovalAction extends ParaActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** logger. */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/** levApp. */
	private LeaveApproval levApp;

	/**
	 * Method : prepare_local. Purpose : This method is used to set menuCode
	 * 
	 * @throws Exception :
	 *             Exception
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.levApp = new LeaveApproval();
		this.levApp.setMenuCode(63); // setting menu code

	}
	/**
	 * Method : getModel. Purpose : This method is used to return
	 * this.leaveApplication
	 * 
	 * @return Object : this.levApp
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.levApp;
	}
	/**
	 * Method : getlevApp. Purpose : This method is used to get
	 * this.levApp
	 * 
	 * @return this.levApp
	 */
	public   LeaveApproval getlevApp() {
		return this.levApp;
	}

	/**
	 * Method : setlevApp. Purpose : This method is used to set
	 * this.setlevApp
	 * 
	 * @param this.setlevApp :
	 *            this.setlevApp
	 */
	public void setlevApp(LeaveApproval levApp) {
		this.levApp = levApp;
	}
/**
 * This method is used for getting leave applications for approval
 * @return String 
 */
	public String callstatus() {

		try {
			final LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(this.levApp, request, this.levApp
					.getUserEmpId());
			this.levApp.setListType("pending");
			this.levApp.setSearchStatus("P");
			model.terminate();
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to call onload.
	 * */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			final LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context, session);
			//model.collect(this.levApp, "P", request); // display list of pending records
			//this.levApp.setPen("true");
			model.terminate();
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}
/**
 * This method is used for getting list of approved applications
 * @return String
 * @throws Exception
 */
	public String getApprovedList() throws Exception {

		try {
			final LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context, session);
			model.getApprovedList(this.levApp, request, this.levApp.getUserEmpId());
			this.levApp.setListType("approved");
			this.levApp.setSearchStatus("A");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return SUCCESS;
	}
/**
 * This method is used for getting list of rejected leave applications
 * @return String
 * @throws Exception
 */
	public String getRejectedList() throws Exception {

		try {
			final LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context, session);
			model.getRejectedList(this.levApp, request, this.levApp.getUserEmpId());
			this.levApp.setListType("rejected");
			this.levApp.setSearchStatus("R");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}
/**
 * This method is used for searching leave application according to filter.
 * @return
 */
	public String search() {
		try {
			final LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context, session);
			if (this.levApp.getSearchStatus().equals("A")) {
				getApprovedList();
			} else if (this.levApp.getSearchStatus().equals("R")) {
				getRejectedList();
			} else {
				this.levApp.setListType("pending");
				model.getAllTypeOfApplications(this.levApp, request, this.levApp
						.getUserEmpId());
			}
			model.terminate();
		} catch (final Exception e) {
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
		final LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context, session);
		  String status = "";
		  String stat = "";
		try {
			status = request.getParameter("status");
			status = String.valueOf(status.charAt(0));
		}// end of try
		catch (final Exception e) {
			// TODO: handle exception
			logger.error("Exception in callstatus-------" + e);
		}// end of catch
		if (status == null || status.equals("")) {
			status = "P";
		}// end of if
		else if (status.equals("F")) {
			status = "P";
		}
		if (status.equals("P")) 
		{
			this.levApp.setPen("true");
			stat = "Pending List";
		}
		else if (status.equals("A")) {
			this.levApp.setApp("true");
			stat = "Approved List";

		}// end of else if
		else if (status.equals("R")) {
			this.levApp.setRej("true");
			stat = "Rejected List";
		}// end of else if
		else if (status.equals("H")) {
			this.levApp.setHol("true");
			stat = "On-HOld List";
		}// end of else if

		if (!(status.equals("P"))) {
			this.levApp.setApprflag("true");
		}// end of if
		request.setAttribute("stat", stat);
		model.collect(this.levApp, status, request);
		model.terminate();
		return "success";

	}// end of callstatus

	public String callPage1() throws Exception {
		final LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context, session);
		//callstatus();
		model.terminate();

		return SUCCESS;
	}

	public String callPage2() throws Exception {
		final LeaveApprovalModel model = new LeaveApprovalModel();
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
	public String saveStatus() {
		  String applStatus = "";
		boolean result = false;
		final LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context, session);
		final String[] leaveAppNo = request.getParameterValues("leaveAppNo"); // application
		// code
		final String[] empCode = request.getParameterValues("empCode"); // employee
		// id
		final String[] status = request.getParameterValues("checkStatus"); // status
		final String[] level = request.getParameterValues("level"); // level
		final String[] remarks = request.getParameterValues("statusRemark");// remark
		final String[] date = request.getParameterValues("date");//date
		final String approverId = this.levApp.getUserEmpId();
		final String isAdminApprovalClick= new LeaveApplication().getIsAdminApprovalClick();
		if (status != null && status.length > 0) {
			for (int i = 0; i < leaveAppNo.length; i++) {
				if (!(status[i].equals("P"))) {
					applStatus = model.approveReject(request, empCode[i],
							leaveAppNo[i], status[i], remarks[i], approverId,
							level[i],isAdminApprovalClick);
					result = true;
				}// end of if
			}// end of for loop
		}// end of if
		if (result) {
			addActionMessage(getMessage("save"));
		}// end of if
		model.collect(this.levApp, "P", request);
		model.terminate();
		return "success";
	}// end of saveStatus

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

		final String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = { "searchEmpToken", "searchEmpName",
				"searchEmpCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";

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
		this.levApp.setApp("");
		this.levApp.setApprflag("");
		this.levApp.setCheckStatus("");
		this.levApp.setDate("");
		this.levApp.setEmpCode("");
		this.levApp.setEmpName("");
		this.levApp.setHol("");
		this.levApp.setLevel("");
		this.levApp.setPen("");
		this.levApp.setRej("");
		this.levApp.setStatus("");
		this.levApp.setLeaveAppNo("");
		return SUCCESS;
	}// end of reset

}// end of class
