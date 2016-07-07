package org.struts.action.payroll;

import org.paradyne.bean.payroll.PTaxChallanReport;
import org.paradyne.model.payroll.PTaxChallanReportModel;
import org.struts.lib.ParaActionSupport;

public class PTaxChallanReportAction extends ParaActionSupport {

	PTaxChallanReport ptaxChallanBean ;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		ptaxChallanBean =new PTaxChallanReport();
		ptaxChallanBean.setMenuCode(1061);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return ptaxChallanBean;
	}

	public PTaxChallanReport getPtaxChallanBean() {
		return ptaxChallanBean;
	}

	public void setPtaxChallanBean(PTaxChallanReport ptaxChallanBean) {
		this.ptaxChallanBean = ptaxChallanBean;
	}
	
	
	public String reset()
	{
		try {
			ptaxChallanBean.setDivCode("");
			ptaxChallanBean.setDivName("");
			ptaxChallanBean.setMonth("");
			ptaxChallanBean.setYear("");
			ptaxChallanBean.setStateCode("");
			ptaxChallanBean.setStateName("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS ;
	}
	
	
	public String report()
	{
		try {
			PTaxChallanReportModel model =new PTaxChallanReportModel(); 
			model.initiate(context, session);
			model.generateReport(response,ptaxChallanBean,request,"");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null ;
	}
	
	
	/**
	 * Search division details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID,DIV_ADDRESS1 ||' '||DIV_ADDRESS2 ||' '||DIV_ADDRESS3 AS Address FROM HRMS_DIVISION";
						
					 
	 if(ptaxChallanBean.getUserProfileDivision() !=null && ptaxChallanBean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+ptaxChallanBean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID "; 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName", "divCode" ,"divAddress"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 ,2};

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
		
		System.out.println("In method-----------------------------------------------");

		return "f9page";
	}
	
	public String f9state(){
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT LOCATION_NAME, LOCATION_CODE FROM HRMS_LOCATION";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "State"};

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "stateName", "stateCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

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

	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String[][] type = new String[][]{{"PDF"},{"Excel"},{"Doc"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true"; 
			String submitToMethod = "PTaxChallanReport_mailReport.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	public final String mailReport(){
		try {
			PTaxChallanReportModel model =new PTaxChallanReportModel(); 
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.generateReport(response, ptaxChallanBean, request, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	
}
