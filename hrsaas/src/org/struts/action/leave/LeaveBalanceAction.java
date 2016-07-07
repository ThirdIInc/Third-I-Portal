package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveBalance;
import org.paradyne.model.leave.LeaveBalanceModel;

import com.lowagie.text.Image;

/**
 * @author radha
 * 
 */
/**@modified by
 * @author Prajakta.Bhandare
 * @date 26 Dec 2012
 */

public class LeaveBalanceAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveBalanceAction.class);
	LeaveBalance leaveBal;
	/**
	 * @return the leaveBal
	 */
	public LeaveBalance getLeaveBal() {
		return leaveBal;
	}
 /**
 * @param leaveBal the leaveBal to set
 */
void setLeaveBal(LeaveBalance leaveBal) {
		this.leaveBal = leaveBal;
	}

	public Object getModel() {

		return leaveBal;
	}
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		leaveBal = new LeaveBalance();
		leaveBal.setMenuCode(64);
		try {

			LeaveBalanceModel model = new LeaveBalanceModel();
			model.initiate(context, session);
			//model.getBlankLeaveRecord(leaveBal);
			model.terminate();
		} // end of try
		catch (Exception e) {
			logger.error("Exception in prepare_local------------------" + e);
		}// end of catch
	}

	/** method toget leave
	 * @return String
	 * @throws Exception
	 */
	public String getLeave() throws Exception {

		return "success";
	}

	/**
	 * THIS METHOD IS USED FOR DISPLAYING LEAVE BALANCE RECORD
	 * @return String
	 * @throws Exception
	 */
	public String LeaveBalanceRecord() throws Exception {

		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		leaveBal = model.getLeaveRecord(leaveBal);
		getProfileImage();
		model.terminate();
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		leaveBal.setEditFlag(false);
		if (leaveBal.isGeneralFlag()) {
			leaveBal.setIsNotGeneralUser("false");
			leaveBal.setEmpId(leaveBal.getUserEmpId());
		} //end of if
		else {
			leaveBal.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if(str==null || str.equals("")){
				str=leaveBal.getUserEmpId();
			}
			leaveBal.setEmpId(str);
		}//end of else
		LeaveBalanceRecord();
		getProfileImage();
		model.terminate();
	}

	/**
	 * THIS METHOD IS USED FOR SAVING LEAVE BALANCE OF AN EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		String[] leaveCode = request.getParameterValues("leaveCode");// leave
		// code
		String[] opBal = request.getParameterValues("opBal");// opening
		// balance
		String[] clBal = request.getParameterValues("clBal");// closing
		// balance
		String[] onholdBal = request.getParameterValues("onholdBal");// on
		// hold
		// balance
		String[] opBalHrs = request.getParameterValues("opBalHrs");// opening
		// balance
		String[] clBalHrs = request.getParameterValues("clBalHrs");// closing
		// balance
		String[] onholdBalHrs = request.getParameterValues("onholdBalHrs");// on
		// hold
		// balance
		// pending
		// for
		// approval
		if(leaveCode!=null && leaveCode.length>0){
		
		boolean result = model.addLeave(leaveBal, leaveCode, opBal, clBal,
				onholdBal,opBalHrs,clBalHrs,onholdBalHrs,request);
		if (result) {
			addActionMessage(getText("addMessage", ""));
			

		}// end of if
		else {
			addActionMessage("Leaves can not be added");
		}// end of else
		
		}else addActionMessage("Leave policy has not been defined for the employee.");
		leaveBal.setEditFlag(false);
		model.getLeaveRecord(leaveBal);
		getProfileImage();
		model.terminate();
		return "success";
	}// end of save

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		leaveBal.setEmpToken("");
		leaveBal.setEmpName("");
		leaveBal.setRankName("");
		leaveBal.setCenterName("");
		leaveBal.setEmpId("");
		leaveBal.setLeaveCode("");
		leaveBal.setLeaveName("");
		leaveBal.setOpBal("");
		leaveBal.setClBal("");
		leaveBal.setOpBalHrs("");
		leaveBal.setClBalHrs("");
		leaveBal.setHourFlag(false);
		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		getProfileImage();
		model.terminate();
		return SUCCESS;

	}

	/** METHOD TO EDIT LEAVE BALANCE RECORD
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
	LeaveBalanceModel model = new LeaveBalanceModel();
	model.initiate(context, session);
	leaveBal.setEditFlag(true);
	LeaveBalanceRecord();
	getProfileImage();
	model.terminate();
	return SUCCESS;
	}
	
	/** METHOD TO CANCEL LEAVE BALANCE RECORD
	 * @return String
	 * @throws Exception
	 */
	public String cancel()throws Exception{
		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		leaveBal.setEditFlag(false);
		LeaveBalanceRecord();
		getProfileImage();
		model.terminate();
		return SUCCESS;	
	}
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String report() throws Exception {

		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		model.getReport(leaveBal);
		getProfileImage();
		model.terminate();
		return "report";

	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";

		query += getprofileQuery(leaveBal);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName",
				"centerName", "rankName", "empId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeaveBalance_LeaveBalanceRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * METHOD TO GET PROFILE IMAGE AND NAME OF EMPLOYEE
	 */
	public void getProfileImage(){
		LeaveBalanceModel model = new LeaveBalanceModel();
		model.initiate(context, session);
		 model.getImage(leaveBal);
		 String photo = leaveBal.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		//leaveBal.setFlag("false");
		String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
		
	}

}
