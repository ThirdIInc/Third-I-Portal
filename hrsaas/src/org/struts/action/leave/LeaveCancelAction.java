/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author VISHWAMBHAR DESHPANDE
 */
public class LeaveCancelAction extends ParaActionSupport {

	LeaveApplication leave;

	/**
	 * @return the leave
	 */
	public LeaveApplication getLeave() {
		return leave;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(LeaveApplication leave) {
		this.leave = leave;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LeaveCancelAction.class);

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leave = new LeaveApplication();
		leave.setMenuCode(196);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {

		return leave;
	}

	/*
	 * public void prepare_withLoginProfileDetails() throws Exception { LeaveApprovalModel model = new LeaveApprovalModel();
	 * model.initiate(context, session); model.generateListForCancel(leave); model.terminate(); }
	 */

	/**
	 * THIS METHOD IS USED FOR DISPLAYING LIST OF APPROVED LEAVE APPLICATION FOR CANCELLATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	/*
	 * public String cancel() throws Exception { LeaveApprovalModel model = new LeaveApprovalModel(); model.initiate(context,
	 * session); model.generateListForCancel(leave); model.terminate(); return "success"; }
	 */
	/**
	 * THIS METHOD IS USED FOR CANCELLATION OF LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	/*
	 * public String cancelForm() throws Exception { LeaveApprovalModel model = new LeaveApprovalModel(); model.initiate(context,
	 * session); String[] canCheck = (String[]) request.getParameterValues("leaveCode");// leave // application // code if
	 * (canCheck != null) { String[] empIds = request.getParameterValues("empId");// employee // id String[] repOff = new
	 * String[canCheck.length]; for (int i = 0; i < canCheck.length; i++) { Object[][] empFlow = generateEmpFlow(empIds[i],
	 * "Leave", 1);// retuns // approver // id if (empFlow != null && empFlow.length > 0) { repOff[i] =
	 * String.valueOf(empFlow[0][0]); }// end of if else { repOff[i] = ""; }// end of else }// end of for boolean result =
	 * model.cancelApplication(canCheck, leave, repOff); if (result) { addActionMessage("Leave Cancelled successfully!"); }// end
	 * of if else { addActionMessage("Leave can not be cancelled!"); }// end of else }// end of if else { addActionMessage("Please
	 * select Check box for cancel"); }// end of else model.generateListForCancel(leave); model.terminate(); return "success"; } //
	 * end of cancelForm
	 */
	/**
	 * THIS METHOD IS USED FOR CANCEL LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelLeaves() throws Exception {
		LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context, session);
		boolean result = model.cancelLeaves(leave);
		if(result) {
			// -----Process Manager Alert/Email templates-------start
			try {
				String approverQuery = " SELECT APPROVED_BY FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE=" + leave.getLevAppCode() + "AND LEAVE_STATUS = 'C'";
				Object[][] approverObj = model.getSqlModel().getSingleResult(approverQuery);

				String msgType = "A";
				String applicantID = leave.getUserEmpId();
				String approverID = String.valueOf(approverObj[0][0]);
				String applnDate = leave.getAppDate();
				String module = "Leave Cancellation";
				String applnID = leave.getLevAppCode();
				String level = "1";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("LEAVE CNCL-APPLICANT TO APPROVER");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, applicantID);

				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, approverID);

				// Subject + Body
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnDate);
				templateQuery3.setParameter(2, applicantID);

				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, approverID);

				template.configMailAlert();
				// create process alerts
				template.sendProcessManagerAlert(approverID, module, msgType, applnID, level);
				// send mail
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			} catch(Exception e) {
				logger.error(e);
			}
			addActionMessage("Leave Forwarded for Cancellation");
			leave.setEmpToken("");
			leave.setEmpName("");
			leave.setEmpCode("");
		}
		// -----Process Manager Alert/Email templates-------end

		else {
			addActionMessage("Leave can not Forwarded for Cancellation");
		}// end of else
		model.terminate();
		return "success";
	}// end of cancelLeaves

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	/*
	 * public String f9action() throws Exception { String query = " SELECT EMP_TOKEN, " + " HRMS_EMP_OFFC.EMP_FNAME||'
	 * '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + "
	 * TO_CHAR(CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID " + " FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_RANK ON
	 * HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " + " LEFT JOIN HRMS_" + "CENTER ON HRMS_CENTER.CENTER_ID =
	 * HRMS_EMP_OFFC.EMP_CENTER " + " ORDER BY EMP_ID "; String[] headers = { "Token No.", "Employee Name" }; String[] headerWidth = {
	 * "20", "80" }; String[] fieldNames = { "tokenNo", "empName", "branchName", "designation", "empId" }; int[] columnIndex = {
	 * 0, 1, 2, 3, 4 };
	 *//**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
	/*
	 * String submitFlag = "true"; String submitToMethod = "LeaveCancel_cancel.action";
	 *//**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
	/*
	 * setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod); return "f9page"; }// end of
	 * f9action
	 */
	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE DETAILS.
	 * 
	 * @return String
	 */
	public String getLeaveDtlHistory() {
		LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context, session);
		model.getLeaveDtlHistory(leave);
		model.terminate();
		return "success";
	}// end of getLeaveDtlHistory

	/**
	 * THIS METHOD IS USED FOR SELECTING APPROVED LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9appAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY')," 
				 
				+" DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'A','APPROVED'),HRMS_LEAVE_HDR.EMP_ID " 

				+" ,HRMS_LEAVE_HDR.LEAVE_APPL_CODE FROM HRMS_LEAVE_HDR "
				
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_LEAVE_HDR.EMP_ID)" 
				
				+" WHERE HRMS_LEAVE_HDR.EMP_ID="+leave.getUserEmpId()+" AND HRMS_LEAVE_HDR.LEAVE_STATUS='A' " 
				
				+" ORDER BY HRMS_LEAVE_HDR.LEAVE_APPL_CODE DESC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("appDate"), getMessage("stat")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"15", "40", "15", "30"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */

		String[] fieldNames = {"leave.empToken", "leave.empName", "leave.appDate", "leave.status", "leave.empCode", "leave.levAppCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2, 3, 4, 5};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeaveCancel_getLeaveDtlHistory.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}// end of f9appAction

}// end of class
