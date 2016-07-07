/**
 * 
 */
package org.struts.action.leave;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.leave.LeaveApplicationMis;
import org.paradyne.model.leave.LeaveApplicationMisModel;

/**
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class LeaveApplicationMisAction extends ParaActionSupport {

	/**
	 * 
	 */
	LeaveApplicationMis leaveAppMis;

	public LeaveApplicationMisAction() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	@Override
	public void prepare_local() throws Exception {
		leaveAppMis = new LeaveApplicationMis();
		leaveAppMis.setMenuCode(636);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return leaveAppMis;
	}

	public LeaveApplicationMis getLeaveAppMis() {
		return leaveAppMis;
	}

	public void setLeaveAppMis(LeaveApplicationMis leaveAppMis) {
		this.leaveAppMis = leaveAppMis;
	}

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 */
	public String clear() {
		
		if(! leaveAppMis.isGeneralFlag())
		{
			leaveAppMis.setEmpId("");
			leaveAppMis.setEmpName("");	
			leaveAppMis.setDivCode("");
			leaveAppMis.setDivsion("");
		}
		
		leaveAppMis.setRank("");
		leaveAppMis.setCenterNo("");
		leaveAppMis.setCenterName("");
		leaveAppMis.setCenter("");
		leaveAppMis.setFrmDate("");
		leaveAppMis.setToDate("");
		//leaveAppMis.setAppFromDate("");
		//leaveAppMis.setAppToDate("");
		leaveAppMis.setToken("");
		leaveAppMis.setStatus("");
		leaveAppMis.setDeptName("");
		leaveAppMis.setDesgName("");
		leaveAppMis.setDeptCode("");
		leaveAppMis.setDesgCode("");
		leaveAppMis.setReportType("");
		leaveAppMis.setEmpStatus("");
		return SUCCESS;
	}// end of clear

	public void prepare_withLoginProfileDetails() throws Exception {

		if (leaveAppMis.isGeneralFlag()) {
			LeaveApplicationMisModel model = new LeaveApplicationMisModel();
			model.initiate(context, session);
			// logger.info("leaveBal.getUserEmpId():"+leaveAppMis.getUserEmpId());
		   model.getEmployeeDetails(leaveAppMis.getUserEmpId(),leaveAppMis);
			model.terminate();
		}// end of if
	}// end of prepare_withLoginProfileDetails

	
	public String input() throws Exception {
		if (leaveAppMis.isGeneralFlag()) {
			LeaveApplicationMisModel model = new LeaveApplicationMisModel();
			model.initiate(context, session);
		    model.getEmployeeDetails(leaveAppMis.getUserEmpId(),leaveAppMis);
			model.terminate();
		}// end of if
		return SUCCESS;
	}
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		LeaveApplicationMisModel model = new LeaveApplicationMisModel();
		model.initiate(context, session);
		String result = model.getReport(leaveAppMis, response);
		model.getEmployeeDetails(leaveAppMis.getUserEmpId(), leaveAppMis);
		model.terminate();
		clear();
		return null;

	}// end of report

	/*Generate a Report using IreportV2 Library
	 * */
	public String leaveMisReport(){
	
	LeaveApplicationMisModel model = new LeaveApplicationMisModel();
	model.initiate(context, session);
	String reportPath="";
	model.getLeaveMisReport(leaveAppMis, request, response, reportPath);
	model.terminate();
	return null;
	}
	
//To generate Mail Report
	
	public final String mailReport()
	{
		try
		{
		  final LeaveApplicationMisModel model = new  LeaveApplicationMisModel();
		  model.initiate(context, session);
		  String poolName= String.valueOf(session.getAttribute("session_pool"));
		  if(!(poolName.equals("")|| poolName==null))
		  {
	 		 poolName="/"+poolName;
	      }
		  String reportPath= getText("data_path") + "/Report/Master" + poolName + "/";
		  model.getLeaveMisReport(leaveAppMis, request, response, reportPath);
		  model.terminate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "mailReport";
	}	
	/**
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  CENTER_NAME,RANK_NAME,EMP_ID"
				+ " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";		
		if(!leaveAppMis.getDivCode().equals("") && leaveAppMis.getDivCode().length() >0){
			query += getprofileQuery(leaveAppMis);
			query +="  AND  EMP_DIV IN("+leaveAppMis.getDivCode()+ ")";
		}
		query += "	ORDER BY EMP_ID ASC ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"),getMessage("employee")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leaveAppMis.token", "leaveAppMis.empName",
				"leaveAppMis.center", "leaveAppMis.rank", "leaveAppMis.empId" };

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
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chk() throws Exception {
		// leaveAppMis.setEmpId("");
		// leaveAppMis.setEmpName("");
		// leaveAppMis.setRank("");
		leaveAppMis.setCenterNo("");
		leaveAppMis.setCenterName("");
		// leaveAppMis.setCenter("");
		// leaveAppMis.setFromDate("");
		// leaveAppMis.setToDate("");
		// leaveAppMis.setAppFromDate("");
		// leaveAppMis.setAppToDate("");
		// leaveAppMis.setToken("");
		// leaveAppMis.setStatus("");
		leaveAppMis.setDeptName("");
		leaveAppMis.setDesgName("");
		leaveAppMis.setDeptCode("");
		leaveAppMis.setDesgCode("");

		return SUCCESS;
	}// end of chk

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chk1() throws Exception {
		leaveAppMis.setEmpId("");
		leaveAppMis.setEmpName("");
		leaveAppMis.setRank("");
		leaveAppMis.setCenter("");
		leaveAppMis.setToken("");
		return SUCCESS;
	}// end of chk1

	
	public String f9MultiDiv() {
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (this.leaveAppMis.getUserProfileDivision()!= null
					&& this.leaveAppMis.getUserProfileDivision().length() > 0){
				query += " WHERE DIV_ID IN (" + this.leaveAppMis.getUserProfileDivision()
						+ ") AND IS_ACTIVE= 'Y'" ;
			}else{
			query+= " WHERE IS_ACTIVE= 'Y' ";
			}
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_divsion";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger.error("Error in LeaveApplicationMisAction.f9MultiDiv() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	public String f9MultiDept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT " + " WHERE " + " IS_ACTIVE = 'Y' " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("department");
			String textAreaId = "paraFrm_leaveAppMis_deptName";

			String hiddenFieldId = "paraFrm_leaveAppMis_deptCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiDept() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	public String f9Brch() {
		try {
			String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
					+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER "
					+" WHERE IS_ACTIVE = 'Y' "
					+ " ORDER BY " + "		UPPER (CENTER_NAME) ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_leaveAppMis_centerName";

			String hiddenFieldId = "paraFrm_leaveAppMis_centerNo";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	
	public String f9MultiRank() {
		try {
			String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
					+ "	FROM " + " 	HRMS_RANK  " +" WHERE IS_ACTIVE='Y' "+ " ORDER BY "
					+ "		UPPER (RANK_NAME) ";

			String header = getMessage("designation");
			String textAreaId = "paraFrm_leaveAppMis_desgName";

			String hiddenFieldId = "paraFrm_leaveAppMis_desgCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING DEVISION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME FROM HRMS_DIVISION  ";

		if(leaveAppMis.getUserProfileDivision() !=null && leaveAppMis.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+leaveAppMis.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = {  getMessage("division.code"),getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divsion" };

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
		String submitToMethod = "LeaveApplicationMis_chk1.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = {  getMessage("department.code"),getMessage("department")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leaveAppMis.deptCode", "leaveAppMis.deptName" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeaveApplicationMis_chk1.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9dept

	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGNATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("designation.code"),getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leaveAppMis.desgCode", "leaveAppMis.desgName" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeaveApplicationMis_chk1.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9desg

	/**
	 * THIS METHOD IS USED OFR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("branch.code"),getMessage("branch") };

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "40", "60" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = { "leaveAppMis.centerNo",
				"leaveAppMis.centerName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "true";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "LeaveApplicationMis_chk1.action";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9center

}// end of class
