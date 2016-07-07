/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveHistoryMis;
import org.paradyne.model.leave.LeaveHistoryMisModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author diptip
 *
 */
public class LeaveHistoryMisAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	LeaveHistoryMis leaveHisMis;
	public LeaveHistoryMisAction() {
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	@Override
	public void prepare_local() throws Exception {
		 leaveHisMis=new LeaveHistoryMis();
		 leaveHisMis.setMenuCode(638);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return leaveHisMis;
	}

	public LeaveHistoryMis getLeaveHisMis() {
		return leaveHisMis;
	}

	public void setLeaveHisMis(LeaveHistoryMis leaveHisMis) {
		this.leaveHisMis = leaveHisMis;
	}
	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * @return String
	 */
	public String clear()
	{
		if (! leaveHisMis.isGeneralFlag()) 
		{
			leaveHisMis.setEmpId("");
			leaveHisMis.setEmpName("");
			leaveHisMis.setDivCode("");
			leaveHisMis.setDivsion("");
		}		
		leaveHisMis.setRank("");
		leaveHisMis.setCenterNo("");
		leaveHisMis.setCenterName("");
		leaveHisMis.setCenter("");	
		leaveHisMis.setFromDate("");
		leaveHisMis.setToDate("");		
		leaveHisMis.setToken("");
		leaveHisMis.setStatus("");
		leaveHisMis.setDeptName("");
		leaveHisMis.setDesgName("");
		leaveHisMis.setDeptCode("");
		leaveHisMis.setDesgCode("");
		leaveHisMis.setTypeCode("");
		leaveHisMis.setLevCode("");
		leaveHisMis.setLevType("");
		leaveHisMis.setEmpType("");
		leaveHisMis.setReportType("");		
		return SUCCESS;
	}//end of clear
	
	public void prepare_withLoginProfileDetails() throws Exception
	{
			if (leaveHisMis.isGeneralFlag()) 
			{
			LeaveHistoryMisModel model = new LeaveHistoryMisModel();
			model.initiate(context,session);
			model.getEmployeeDetails(leaveHisMis.getUserEmpId(),leaveHisMis);
			model.terminate();
		}//end of if
	}//end of prepare_withLoginProfileDetails
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * @return String
	 */
	public String report()
	{
		LeaveHistoryMisModel model=new LeaveHistoryMisModel();
		model.initiate(context,session);
		
		String reportPath="";
		String result = model.getReport(leaveHisMis,request, response, reportPath);
		model.getEmployeeDetails(leaveHisMis.getUserEmpId(),leaveHisMis);
		model.terminate();
		clear();
		return null;
		
	}//end of report
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
					  +"  CENTER_NAME,RANK_NAME,EMP_ID"
					  +" FROM HRMS_EMP_OFFC"
					  +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					  +" LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";
		if(!leaveHisMis.getDivCode().equals("") && leaveHisMis.getDivCode().length()>0){
			query += getprofileQuery(leaveHisMis);
			query += "  AND  EMP_DIV IN ("+leaveHisMis.getDivCode()+")";
		}
		query += "	ORDER BY EMP_ID ASC ";
		String[] headers ={ getMessage("employee.id"),getMessage("employee")};
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = {"leaveHisMis.token","leaveHisMis.empName","leaveHisMis.center","leaveHisMis.rank","leaveHisMis.empId"};
		int[] columnIndex = {0,1,2,3,4};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * THIS METHOD IS USED FOR RESETTING FIELDS WHEN EMPLOYEE NAME GET SELECTED
	 * @return String
	 * @throws Exception
	 */
	public String chk() throws Exception{		
		leaveHisMis.setCenterNo("");
		leaveHisMis.setCenterName("");
		leaveHisMis.setEmpType("");
		leaveHisMis.setTypeCode("");	
		leaveHisMis.setLevCode("");
		leaveHisMis.setLevType("");
		leaveHisMis.setStatus("");
		leaveHisMis.setEmpStatus("");		
		leaveHisMis.setDeptName("");
		leaveHisMis.setDesgName("");
		leaveHisMis.setDeptCode("");
		leaveHisMis.setDesgCode("");
		return SUCCESS;
	}//end of chk
	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * @return String
	 * @throws Exception
	 */
	public String chk1() throws Exception{
		leaveHisMis.setEmpId("");
		leaveHisMis.setEmpName("");
		leaveHisMis.setRank("");
		leaveHisMis.setCenter("");	
		leaveHisMis.setToken("");
		return SUCCESS;
	}//end of chk1
	
	/**
	 * THIS METHOD IS USED FOR SELECTING DEVISION
	 * @return String
	 * @throws Exception
	 */
	
	public String f9div() throws Exception {
		/*String query = " SELECT  DIV_ID,DIV_NAME"
						+ "	FROM HRMS_DIVISION  ";
						
						if(leaveHisMis.getUserProfileDivision() !=null && leaveHisMis.getUserProfileDivision().length()>0)
							query+= " WHERE DIV_ID IN ("+leaveHisMis.getUserProfileDivision() +")"; 
		String[] headers = {  getMessage("division.code"),getMessage("division") };
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "divCode","divsion" };
		int[] columnIndex = { 0, 1};
		String submitFlag = "false";
		String submitToMethod = "LeaveHistoryMis_chk1.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
		*/
		try {
			String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
			query +="   WHERE HRMS_DIVISION.IS_ACTIVE='Y' ";
			if (leaveHisMis.getUserProfileDivision() != null
					&& leaveHisMis.getUserProfileDivision().length() > 0)
				
				query += " AND DIV_ID IN (" + leaveHisMis.getUserProfileDivision()+")";
			
			query +=" ORDER BY HRMS_DIVISION.DIV_ID";
			

			String textAreaId = "paraFrm_leaveHisMis_divsion";
			String hiddenFieldId = "paraFrm_leaveHisMis_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, getMessage("Division"), textAreaId,
					hiddenFieldId, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		/*String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";
		String[] headers =  {  getMessage("department.code"),getMessage("department")};
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "leaveHisMis.deptCode",
				"leaveHisMis.deptName" };
		int[] columnIndex = { 0, 1};
		String submitFlag = "true";
		String submitToMethod = "LeaveHistoryMis_chk1.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT WHERE HRMS_DEPT.IS_ACTIVE='Y' ORDER BY HRMS_DEPT.DEPT_ID";

			String header = getMessage("dept.name");

			String textAreaId = "paraFrm_leaveHisMis_deptName";
			String hiddenFieldId = "paraFrm_leaveHisMis_deptCode";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGANTION
	 * @return String
	 * @throws Exception
	 */
	public String f9desg() throws Exception {
		/*
		String query = " SELECT  RANK_ID,RANK_NAME"
						+ "	FROM HRMS_RANK  ";
		String[] headers ={ getMessage("designation.code"),getMessage("designation") };
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "leaveHisMis.desgCode",
				"leaveHisMis.desgName" };
		int[] columnIndex = { 0, 1};
		String submitFlag = "true";
		String submitToMethod = "LeaveHistoryMis_chk1.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		try {
			String query = " SELECT DISTINCT  HRMS_RANK.RANK_ID, NVL(HRMS_RANK.RANK_NAME,' ') FROM HRMS_RANK WHERE HRMS_RANK.IS_ACTIVE='Y' ORDER BY HRMS_RANK.RANK_ID";

			String header = getMessage("dept.name");

			String textAreaId = "paraFrm_leaveHisMis_desgName";
			String hiddenFieldId = "paraFrm_leaveHisMis_desgCode";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {
	 
		/*String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";
		String[] headers =  { getMessage("branch.code"),getMessage("branch") };
		String[] headerWidth = { "40", "60" };
		String[] fieldNames = { "leaveHisMis.centerNo","leaveHisMis.centerName" };
		int[] columnIndex = { 0,1 };
		String submitFlag = "true";
		String submitToMethod = "LeaveHistoryMis_chk1.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE HRMS_CENTER.IS_ACTIVE='Y'";
			query += " ORDER BY HRMS_CENTER.CENTER_ID";

			String header = getMessage("branch.name");

			String textAreaId = "paraFrm_leaveHisMis_centerName";
			String hiddenFieldId = "paraFrm_leaveHisMis_centerNo";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * @return String
	 * @throws Exception
	 */
	public String f9type() throws Exception {
		/*
		String query = " SELECT    TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ";
		String[] headers = { getMessage("typeid"),getMessage("employee.type")};
		String[] headerWidth = { "10","10" };
		String[] fieldNames = { "leaveHisMis.typeCode","leaveHisMis.empType"};
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeaveHistoryMis_chk1.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";*/
			try {
				String query = " SELECT  TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y' ORDER BY HRMS_EMP_TYPE.TYPE_ID ";
				String header = "Employee Type";
				String textAreaId = "paraFrm_leaveHisMis_empType";
				String hiddenFieldId = "paraFrm_leaveHisMis_typeCode";
				String submitFlag = "false";
				String submitToMethod = "";
				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
						submitFlag, submitToMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "f9multiSelect";
	}
	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE TYPE
	 * @return String
	 * @throws Exception
	 */
	public String f9ltypeaction() throws Exception {
		/*String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME"
						+ "	FROM HRMS_LEAVE  "
						
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID";
		String[] headers = { getMessage("leaveCode"),getMessage("levtype") };
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "leaveHisMis.levCode",
				"leaveHisMis.levType" };
		int[] columnIndex = { 0, 1};
		String submitFlag = "true";
		String submitToMethod = "LeaveHistoryMis_chk1.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";*/
		try {
			String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME"
				+ "	FROM HRMS_LEAVE WHERE HRMS_LEAVE.IS_ACTIVE='Y' "	
				+ " ORDER BY HRMS_LEAVE.LEAVE_ID";
			String header = "Leave Name";
			String textAreaId = "paraFrm_leaveHisMis_levType";
			String hiddenFieldId = "paraFrm_leaveHisMis_levCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String leaveHistoryReport() throws Exception {
		System.out.println("in Rep");
		final LeaveHistoryMisModel model = new LeaveHistoryMisModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getLeaveHistoryMisReport(leaveHisMis, request, response, reportPath);
		model.terminate();
		System.out.println("in Repppppp");
		return null;
	}

	public final String mailReport() {
		try {
			final LeaveHistoryMisModel model = new LeaveHistoryMisModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.getLeaveHistoryMisReport(leaveHisMis, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}


}//end of class
