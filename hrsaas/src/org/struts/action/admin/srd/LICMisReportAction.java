package org.struts.action.admin.srd;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Asset.AssestApplicationMis;
import org.paradyne.bean.admin.srd.LICMisReportMaster;
import org.paradyne.model.admin.srd.LicMisReportModel;
import org.paradyne.model.admin.srd.VisaMISReportModel;
import org.paradyne.model.payroll.CreditModel;


public class LICMisReportAction extends ParaActionSupport {

	LICMisReportMaster bean = null;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new LICMisReportMaster();
		this.bean.setMenuCode(2058);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public LICMisReportMaster getBean() {
		return bean;
	}

	public void setBean(LICMisReportMaster bean) {
		this.bean = bean;
	}
	/*
	public String report() {
		System.out.println("in report LIC report method---------------");
		LicMisReportModel model = new LicMisReportModel();
		model.initiate(context, session);
		String result = model.getLICMISReport(response,bean);
		model.terminate();
		return null;

	}// end of report*/
	
	public final String getReport() throws Exception {
		LicMisReportModel model = new LicMisReportModel();
		model.initiate(context, session);
	String reportPath = "";
		model.getLICReport(bean,request, response,reportPath);
		model.terminate();
		return null;
	}
	

	
	public final String mailReport() {
		try {
			final LicMisReportModel model = new LicMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.getLICReport(bean,request, response,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}



	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID FROM HRMS_EMP_OFFC  ";
				

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Employee Id", getMessage("employee") };

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

		String[] fieldNames = { "token", "empName","empid" };

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
		String submitToMethod = "LicMisReport_chk.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9div() throws Exception {
		
		
		
		
		String query = " SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION  "

		+ " ORDER BY DIV_ID";
				
		String header = getMessage("divisionName");
		
		String[] headerWidth = { "15", "35" };

		

		String[] fieldNames = { "divCode", "divsionName" };
		String textAreaId = "paraFrm_divisionName";
		String hiddenFieldId = "paraFrm_divCode";
		
		int[] columnIndex = { 0, 1 };

		
		String submitFlag = "false";

		
		String submitToMethod = "LicMisReport_chk1.action";
		
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
		
	}// end of f9div
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT  WHERE IS_ACTIVE='Y' ORDER BY DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Department Id", getMessage("department") };

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

		String[] fieldNames = { "deptCode", "deptName" };

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
	}// end of f9dept
	

	
	public String f9center() throws Exception {

		String query = " SELECT DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER  ORDER BY "
				+ "	UPPER (CENTER_NAME) ";

		String header = getMessage("branch");
		String textAreaId = "paraFrm_centerName";

		String hiddenFieldId = "paraFrm_centerId";

		String submitFlag = "false";
		String submitToMethod = "LicMisReport_chk1.action";

		

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";

	}
	
	
	public String f9payBill() throws Exception {
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL";
		String header = getMessage("payBillName");
		String textAreaId = "paraFrm_payBillName";
		String hiddenFieldId = "paraFrm_payBillId";
		//bean.setPayName(bean.getPayBillName());
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
		return "f9multiSelect";
	}// end of f9desg
	
	public String clear() {
		bean.setDeptCode("");
		bean.setDeptName("");
		bean.setDesgCode("");
		bean.setDesgName("");
		bean.setCenterId("");
		bean.setCenterName("");
		bean.setReportType("");
		bean.setDivCode("");
		bean.setDivisionName("");
		bean.setEmpName("");
		bean.setToken("");
		bean.setPayBillId("");
		bean.setPayBillName("");
		return SUCCESS;
	}

	public String chk() throws Exception {

		bean.setDeptCode("");
		bean.setDeptName("");
		bean.setDesgCode("");
		bean.setDesgName("");
		bean.setCenterId("");
		bean.setCenterName("");
		bean.setReportType("");
		bean.setDivCode("");
		bean.setDivisionName("");
		//bean.setEmpName("");
	//	bean.setToken("");
		bean.setPayBillId("");
		bean.setPayBillName("");

		return SUCCESS;
	}// end of chk
	
	
	public String chk1() throws Exception {
		
		bean.setEmpName("");
		bean.setEmpid("");
		bean.setToken("");
		
		
		
		
		return SUCCESS;
	}// end of chk
	
	
	

}

