package org.struts.action.payroll;
import org.paradyne.bean.payroll.*;
import org.paradyne.model.payroll.ProfessionalTaxReportModel;


public class ProfessionalTaxReportAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.action.payroll.ProfessionalTaxReportAction.class);
	ProfessionalTaxRep pt;
	
	public Object getModel(){
		return pt;
	}
	
	public void prepare_local() throws Exception {
		pt=new ProfessionalTaxRep();
		pt.setMenuCode(547);
	}
	
	public String input(){
		return INPUT;
	}
	
	/*
	 * Following function is called in the jsp page to reset the fields.
	 */
	public String reset(){
		pt.setMonth("");
		pt.setBrnName("");
		pt.setBrnId("");
		pt.setDivName("");
		pt.setDivId("");
		pt.setToMonth("");
		pt.setToYear("");
		pt.setBranchCheck("");
		pt.setStateCheck("");
		pt.setStateId("");
		pt.setStateName("");
		pt.setDojCheck("");
		pt.setSlabCheck("");
		pt.setIncomeCheck("");
		pt.setReportOption("");
		return "success";
	}
	
	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"PDF"},{"Excel"},{"Doc"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "PTax_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**
	 * Following function is called in the jsp page to generate the report
	 * @return
	 */
	public String report(){
		ProfessionalTaxReportModel model =new ProfessionalTaxReportModel();
		model.initiate(context,session);
		String reportPath = "";
		model.generateReport(pt, request, response, reportPath);
		model.terminate();		
		return null;
	}
	public final String mailReport(){
		try {
			ProfessionalTaxReportModel model =new ProfessionalTaxReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.generateReport(pt, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/*
	 * ]
	 */
	public String f9brn() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";
						
		String headers = getMessage("branch");

		String submitFlag = "false";

		String submitToMethod = "";

		String textAreaId ="paraFrm_brnName";
				
		String hiddenFieldId ="paraFrm_brnId";
		
		setMultiSelectF9(query, headers, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9state() throws Exception {
		String query = " SELECT DISTINCT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION " 
						+" INNER JOIN HRMS_CENTER ON(LOCATION_CODE=CENTER_PTAX_STATE) ORDER BY LOCATION_NAME";
						
		String headers = getMessage("state");

		String submitFlag = "false";

		String submitToMethod = "";

		String textAreaId ="paraFrm_stateName";
				
		String hiddenFieldId ="paraFrm_stateId";
		
		setMultiSelectF9(query, headers, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '),DIV_ADDRESS1 ||' '||DIV_ADDRESS2 ||' '||DIV_ADDRESS3 AS Address FROM HRMS_DIVISION ";
		
		if(pt.getUserProfileDivision() !=null && pt.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+pt.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
						
		
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division")};

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

		String[] fieldNames = { "divId","divName","divAdd"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1,2};

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
