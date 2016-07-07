package org.struts.action.payroll;
import org.paradyne.bean.payroll.EmpSalaryReg;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.payroll.EmpSalRegModel;

/*
 * author:Pradeep
 * Date:14-05-2008
 */
public class EmpSalRegAction extends ParaActionSupport {
	
	EmpSalaryReg esr;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		esr = new EmpSalaryReg();
		esr.setMenuCode(646);
	
	}
	public EmpSalaryReg getEsr() {
		return esr;
	}
	public void setEsr(EmpSalaryReg esr) {
		this.esr = esr;
	}
	
	 public Object getModel() {
			// TODO Auto-generated method stub
			return esr;
			
		}
	 /**
	  *Following function is called when a general user makes login 
	  */
	 
	 public void prepare_withLoginProfileDetails() throws Exception {
			
		 EmpSalRegModel model = new EmpSalRegModel();
			model.initiate(context,session);
			
			model.getEmployeeDetails(esr.getUserEmpId(),esr);
			model.terminate();
		
	}
	 /**
	  * Following function is called to generate the report in action class 
	  * @return
	  * @throws Exception
	  */
	 public final void report() {
		try {
			EmpSalRegModel model = new EmpSalRegModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(esr, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 public final String mailReport(){
		try {
			EmpSalRegModel model = new EmpSalRegModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/PayrollReport" + poolName + "/";
			model.generateReport(esr, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	 
	 /**
	  * Following function is called to reset the field in jsp page 
	  * @return
	  */
	 public String reset(){
		 EmpSalRegModel model = new EmpSalRegModel();
		 model.initiate(context,session);
		 if(!esr.isGeneralFlag()){
			 esr.setEmpId("");
			esr.setEmpToken("");
			esr.setEmpName("");
		 }
		
		esr.setFromMonth("");
		esr.setFromYear("");
		esr.setToMonth("");
		esr.setToYear("");
		esr.setReportType("");
		
		model.terminate();
		 return "success";
		 		 	 
	 }
	 
	 public String f9emp() throws Exception {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,NVL(CENTER_NAME,' '),NVL(TYPE_NAME,''),HRMS_EMP_OFFC.EMP_ID,EMP_STATUS, "
				+ "	DIV_NAME, CADRE_NAME FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+"  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)";
			
			query += getprofileQuery(esr);
			
			query +="	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = {getMessage("employee.id"),getMessage("employee"),getMessage("branch"),getMessage("employee.type") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35","25","25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken",
				"empName","brnName","empType","empId","empStatus","empDiv","empGrade" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

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
