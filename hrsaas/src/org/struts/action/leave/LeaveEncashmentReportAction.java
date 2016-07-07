package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveEncashmentReport;
import org.paradyne.model.leave.LeaveEncashmentReportModel;
import org.struts.lib.ParaActionSupport;

public class LeaveEncashmentReportAction extends ParaActionSupport {

	LeaveEncashmentReport leaverpt;

	public void prepare_local() throws Exception {

		leaverpt = new LeaveEncashmentReport();
		leaverpt.setMenuCode(561);

	}
	
	public void prepare_withLoginProfileDetails() throws Exception
	{
			if (leaverpt.isGeneralFlag()) 
			{
		  LeaveEncashmentReportModel model = new LeaveEncashmentReportModel();
			model.initiate(context,session);
			model.getEmployeeDetails(leaverpt.getUserEmpId(),leaverpt);
			model.terminate();
		}//end of if
	}//end of prepare_withLoginProfileDetails

	public Object getModel() {
		// TODO Auto-generated method stub
		return leaverpt;
	}

	public LeaveEncashmentReport getLeaverpt() {
		return leaverpt;
	}

	public void setLeaverpt(LeaveEncashmentReport leaverpt) {
		this.leaverpt = leaverpt;
	}

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		LeaveEncashmentReportModel model = new LeaveEncashmentReportModel();
		model.initiate(context, session);
		model.getReport(leaverpt,request, response, "");
		model.terminate();
		return null;

	}// end of report

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 */
	public String reset() {
		
		if(! leaverpt.isGeneralFlag())
		{
			leaverpt.setEmpId("");
			leaverpt.setEmpName("");
			leaverpt.setDivCode("");
			leaverpt.setDivName("");
			leaverpt.setToken("");
		}
		leaverpt.setCenterID("");
		leaverpt.setCenterName("");
		leaverpt.setDeptID("");
		leaverpt.setDeptName("");
		leaverpt.setDesgID("");
		leaverpt.setDesgName("");
		leaverpt.setFrmDate("");
		leaverpt.setToDate("");
		leaverpt.setStatus("");
		leaverpt.setTypeCode("");
		leaverpt.setEmpType("");
		leaverpt.setReportType("");
		leaverpt.setAllCheck("");
		leaverpt.setInclSalCheck("");
		leaverpt.setNotInclSalCheck("");
		leaverpt.setCostcentername("");
		leaverpt.setCostcenterid("");
		leaverpt.setPayBillName("");
		leaverpt.setPayBillNo("");
		leaverpt.setCadreName("");
		leaverpt.setCadreCode("");
		leaverpt.setLeaveTypeCode("");
		leaverpt.setLeaveTypeName("");
		leaverpt.setBankCheck("");
		leaverpt.setAccNoCheck("");
		leaverpt.setDivCheck("");
		leaverpt.setBranchCheck("");
		leaverpt.setGradeCheck("");
		leaverpt.setCostCenterCheck("");
		
		return SUCCESS;
	}// end of reset

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chk() throws Exception {

		leaverpt.setCenterID("");
		leaverpt.setCenterName("");
		leaverpt.setDeptID("");
		leaverpt.setDeptName("");
		leaverpt.setDesgID("");
		leaverpt.setDesgName("");
		leaverpt.setFrmDate("");
		leaverpt.setToDate("");
		leaverpt.setStatus("");
		leaverpt.setEmpType("");
		leaverpt.setTypeCode("");

		return SUCCESS;
	}// end of chk

	/**
	 * THIS METHOD IS USED FOR SELECTING DIVISION
	 * 
	 * @return String
	 * @throws Exception
	 */
	
	public String f9div() {
		try {
			String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION";

			if (leaverpt.getUserProfileDivision() != null && leaverpt.getUserProfileDivision().length() > 0) {
				query+= " WHERE DIV_ID IN ("+leaverpt.getUserProfileDivision() +")";
			}
				query+= " ORDER BY  DIV_ID ";
			
			String header = getMessage("division");
			
			String textAreaId ="paraFrm_divName";
			String hiddenFieldId ="paraFrm_divCode";
			
			String submitFlag = "false";
			String submitToMethod = "LeaveEncashmentReport_chk1.action";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9type() throws Exception {

		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
		String query = " SELECT    HRMS_EMP_TYPE.TYPE_ID,HRMS_EMP_TYPE.TYPE_NAME  FROM HRMS_EMP_TYPE ";
		
		String header = getMessage("employee.type");
		
		String  hiddenFieldId="paraFrm_typeCode";
		String textAreaId ="paraFrm_empType";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		
		return "f9multiSelect";
	}// end of f9type

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID"
				+ " FROM HRMS_EMP_OFFC ";
				
		query += getprofileQuery(leaverpt);
		
		query +=  "  AND HRMS_EMP_OFFC.EMP_STATUS='S' AND EMP_DIV IN ("+ leaverpt.getDivCode();

		query += ")	ORDER BY EMP_ID ASC ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

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

		String[] fieldNames = { "token", "empName", "empId" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeaveEncashmentReport_chk.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT HRMS_CENTER.CENTER_ID,HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER ";
		
		String header = getMessage("branch");
		
		String  hiddenFieldId="paraFrm_centerId";
		String textAreaId ="paraFrm_centerName";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		
		return "f9multiSelect";

		
	}// end of f9action

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE
		 * 
		 * DESIRED OUTPUT
		 */
		String query = "select HRMS_DEPT.DEPT_ID,HRMS_DEPT.DEPT_NAME from  HRMS_DEPT ";
		
		String header = getMessage("department");
		
		String  hiddenFieldId="paraFrm_deptID";
		String textAreaId ="paraFrm_deptName";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		
		return "f9multiSelect";

		
	}// end of f9deptaction

	/**
	 * THIS METHOD IS USED OFR SELECTING DESIGNATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desigaction() throws Exception {

		String query = " SELECT HRMS_RANK.RANK_ID,HRMS_RANK.RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID";
		
		String header = getMessage("designation");
		
		String  hiddenFieldId="paraFrm_desgID";
		String textAreaId ="paraFrm_desgName";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		
		return "f9multiSelect";

		
	}// end of f9desigaction

	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String query = " SELECT 'Pdf' FROM DUAL";
			String[][] type = new String[][]{{"PDF"},{"Xls"},{"Doc"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "LeaveEncashmentReport_mailReport.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String mailReport(){
		try {
			LeaveEncashmentReportModel model = new LeaveEncashmentReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getReport(leaverpt, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String f9Costcenter()throws Exception{
		
		try {
			String query = " SELECT HRMS_COST_CENTER.COST_CENTER_ID,HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			
			String header = getMessage("costcenter");
			String textAreaId = "paraFrm_costcentername";
			String hiddenFieldId = "paraFrm_costcenterid";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9payBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String header = getMessage("pay.bill");
			String textAreaId ="paraFrm_payBillName";
			String hiddenFieldId ="paraFrm_payBillNo";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9grade() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";

		String header = getMessage("grade");
		String textAreaId ="paraFrm_cadreName";
		String hiddenFieldId ="paraFrm_cadreCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9leaveType() throws Exception {
		String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME"
				+ "	FROM HRMS_LEAVE  "
				+ " ORDER BY HRMS_LEAVE.LEAVE_ID";
		String header = getMessage("levtype");
		String hiddenFieldId ="paraFrm_leaveTypeCode";
		String textAreaId = "paraFrm_leaveTypeName";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	

}// end of class
