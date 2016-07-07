package org.struts.action.payroll.incometax;

import org.paradyne.bean.payroll.incometax.PFReconciliationReport;
import org.paradyne.model.payroll.EsicForm5Model;
import org.paradyne.model.payroll.incometax.PFReconciliationReportModel;
import org.struts.lib.ParaActionSupport;

public class PFReconciliationReportAction extends ParaActionSupport {
	PFReconciliationReport pfBean;
	
	public void prepare_local() throws Exception {
		pfBean = new PFReconciliationReport();
		pfBean.setMenuCode(2068);
	}

	public Object getModel() {
		return pfBean;
	}
	
	public String input(){
		return INPUT;
	}
	
	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '),NVL(DIV_ABBR,' ') FROM HRMS_DIVISION  ";
		
		
		if(pfBean.getUserProfileDivision() !=null && pfBean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+pfBean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divId", "divName","divisionAbbrevation" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2 };

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
	}// end of f9div
	
	public void report(){
		try {
			PFReconciliationReportModel model = new PFReconciliationReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.getReport(pfBean,request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public final String f9reportType() {
			try {
				String[][] type = new String[][]{{"PDF"}};
				String[] headers = {getMessage("report.type")};
				String[] headerWidth = {"100"};
				String[] fieldNames = {"reportType"};
				int[] columnIndex = {0};
				String submitFlag = "true";
				String submitToMethod = "PFReconciliation_mailReport.action";
				setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "f9page";
		}
	 public final String mailReport(){
			try {
				PFReconciliationReportModel model = new PFReconciliationReportModel();
				model.initiate(context, session);
				String poolName = String.valueOf(session.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
				model.getReport(pfBean,request, response, reportPath);
				
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mailReport";
		}
	public String reset(){
		pfBean.setDivId("");
		pfBean.setDivName("");
		pfBean.setFromYear("");
		pfBean.setToYear("");
		return INPUT;
	}

}
