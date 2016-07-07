/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.leave.LeaveApprovalModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author sunil
 *
 */
public class LeaveExtensionAction extends ParaActionSupport {

	LeaveApplication leave =null ;
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

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leave = new LeaveApplication();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	 public Object getModel() {

		return leave;
	}
	
	public String selectRecord() throws Exception {
		try{
			LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context,session);
			model.generateListForCancel(leave);
			model.terminate();
			}catch (Exception e) {
				
			}
		return "success";
	}
	
	public String calculate() {
		String startDate = leave.getLeaveFromDtl();
		String endDate = leave.getLeaveToDtl();

		LeaveApplicationModel model = new LeaveApplicationModel();

		model.initiate(context,session);
		int date_diff = model.calculate(startDate, endDate,leave);
		String date = String.valueOf(date_diff);
		leave.setLeaveTotalDays(date);

		String openbalance = leave.getLevOpeningBalance();

		logger.info("getLevOpeningBalance:" + openbalance);
		String open = String.valueOf(Integer.parseInt(openbalance)
				- Integer.parseInt(date));
		
		if(leave.getZeroBalance().equals("Y"))
			leave.setLevClosingBalance(openbalance);
		else
			leave.setLevClosingBalance(open);
		
		model.terminate();
		// leaveApplication.setLevOpeningBalance(open);
	
		return "successView";
	}
	
	/**
	 * METHOD FOR SELECT RECORD BY APPLICANT
	 * 
	 * @return
	 */
	public String getLeaveAllDetails() throws Exception {
		LeaveApplicationModel model = new LeaveApplicationModel();
		String result = "false";
		// logger.info("2 LEAVE APPLUICATION MODEL");
		model.initiate(context,session);

		logger.info(" USER LOGIN CODE IN lEAVE "
				+ leave.getUserLoginCode());

		if (model.getLeaveApplication(leave)) {
			logger.info("Leave Application 1");
		} else {
			addActionMessage("Error in retieving leave details");
		}
		// logger.info("Code--------------------------------------------------");
		model.getLeaveDtlHistory(leave);
		// model.getTableHistoryDetails(leaveApplication);
		model.terminate();
		return "successView";

	}
	public String addLdtl() throws Exception {
		LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context,session);
		String result = model.addLeaveByAdmin(leave);
		model.getLeaveDtlHistory(leave);
		model.terminate();
		addActionMessage(result);

		return "successView";
	}
	public String removeLeave() throws Exception {
		logger.info("editLdtl");
		LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context,session);
		if(!(leave.getStatus().trim().equals("Recommended")||leave.getStatus().trim().equals("Approved") || leave.getStatus().trim().equals("Rejected") )){
			boolean result =model.removeLeaves(leave);
			if(result){
				addActionMessage("Leave has removed sucessfully");
			}
		}else{
			addActionMessage("This application has\t "+leave.getStatus()+" \tyou can not remove leave");
		}

		model.getLeaveDtlHistory(leave);
		model.terminate();
		
		return "successView";
	}
	public String editLdtl() throws Exception {
		logger.info("editLdtl");
		LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context,session);
		model.getLeaveEditDtl(leave);
		model.getLeaveDtlHistory(leave);
		model.terminate();
		return "successView";
	}
	public String f9EmployeeCode() throws Exception {

		/**
		 * 
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES * String query = " SELECT "+ "
		 * HRMS_EMP_OFFC.EMP_ID, "+ " HRMS_EMP_OFFC.EMP_TITLE||'
		 * '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'
		 * '||HRMS_EMP_OFFC.EMP_LNAME "+ " FROM HRMS_EMP_OFFC ";
		 * 
		 */

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
			+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
			+ "	HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,EMP_TYPE "
			+ "	FROM HRMS_EMP_OFFC "
			+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
			+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
			+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE   ";
		query += getprofileQuery(leave);
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		/*
		 * + " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID =
		 * HRMS_EMP_OFFC.EMP_CENTER" + " WHERE HRMS_EMP_OFFC.EMP_CENTER =
		 * (SELECT HRMS_EMP_OFFC.EMP_CENTER FROM HRMS_EMP_OFFC " + " WHERE
		 * HRMS_EMP_OFFC.EMP_ID ='"+leaveApplication.getUserEmpId()+"') " + "
		 * ORDER BY HRMS_EMP_OFFC.EMP_ID";
		 */
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Token No.", "Employee Name","Center Name", "Rank Name" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "12", "35", "18", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leave.empCode",
				"leave.empName","leave.center" ,"leave.department","leave.empCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3,4 };

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
		String submitToMethod = "LeaveExtension_selectRecord.action"; // LeaveApplication_getLeaveDetails.action

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9ltypeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,"
			+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0) "
		//	+ "	HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE"
			+ "	FROM HRMS_LEAVE_BALANCE        "
			+ "	INNER JOIN  HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_BALANCE.LEAVE_CODE )"
			+" INNER JOIN HRMS_LEAVE_POLICY ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY.LEAVE_CODE"
			+" AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID ='"
			+ String.valueOf(leave.getEmpCode())
			+ "' ))"
	
			+ "	WHERE HRMS_LEAVE_BALANCE.EMP_ID = '"
			+ String.valueOf(leave.getEmpCode())
			+ "' AND HRMS_LEAVE_POLICY.LEAVE_APPLICABLE ='Y' ORDER BY HRMS_LEAVE.LEAVE_ID";	
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Leave Code", "Leave Type","Closing Balance" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35", "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leave.levCode",
				"leave.levType", "levOpeningBalance"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2};

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
