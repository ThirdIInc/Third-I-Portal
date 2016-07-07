package org.struts.action.payroll;
import org.paradyne.bean.payroll.Form7;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.payroll.Form7Model;
/*
 * Author:Pradeep
 * Date:17-09-2008
 */
public class Form7Action extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(Form7Action.class);
	
	Form7 form7;
	
	public Object getModel() {
			return form7;
	}
	public void prepare_local() throws Exception {
		form7 = new Form7();
		form7.setMenuCode(673);
	}
	
	/**
	 * This function is used to generate report when report button is clicked.
	 *
	 */
	public final void report() {
		try {
			Form7Model model = new Form7Model();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(form7, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final String mailReport(){
		try {
			Form7Model model = new Form7Model();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/PayrollReport" + poolName + "/";
			model.generateReport(form7, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public final String f9type() {
		try {
			String query = "SELECT HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME FROM HRMS_EMP_TYPE " 
				+ " WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y'"
				+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID";
			
			String header = getMessage("employee.type");
			String textAreaId ="paraFrm_typeName";
			String hiddenFieldId ="paraFrm_typeId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "f9multiSelect";
	}
	
	public final String f9payBill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE 1=1"; 
			if(!(form7.getUserProfilePaybill().equals(""))){
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+form7.getUserProfilePaybill()+")";
			}
			
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String header = getMessage("pay.bill");
			
			String textAreaId ="paraFrm_payName";
			String hiddenFieldId ="paraFrm_payId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
		
	public final String f9dept() {
		try {
				String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
				
				String header = getMessage("department");
				String textAreaId ="paraFrm_deptName";
				String hiddenFieldId ="paraFrm_deptId";
				String submitFlag = "false";
				String submitToMethod = "";
				
				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "f9multiSelect";
	}
		
	public final String f9brn() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE 1=1";
			if(!(form7.getUserProfileCenters().equals(""))){
				query += " AND HRMS_CENTER.CENTER_ID IN ("+form7.getUserProfileCenters()+")";
			}
			query += " ORDER BY HRMS_CENTER.CENTER_ID";
			
			String header = getMessage("branch");
			
			String textAreaId ="paraFrm_brnName";
			String hiddenFieldId ="paraFrm_brnId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
		
		/*
		 * Following function is called to show the division name and division id in the jsp 
		 */
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID, NVL(DIV_NAME,' '), NVL(DIV_ABBR,' ') FROM HRMS_DIVISION ";
		
		if(form7.getUserProfileDivision() !=null && form7.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+form7.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
	 	
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20","80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divId", "divName", "divAbbr"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1, 2};

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
		
		logger.info("4");
		return "f9page";
	}
}
