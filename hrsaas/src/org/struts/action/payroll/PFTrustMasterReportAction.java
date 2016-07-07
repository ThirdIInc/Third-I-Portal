package org.struts.action.payroll;

import org.paradyne.bean.payroll.PFTrustMasterReport;
import org.paradyne.model.payroll.PFTrustMasterReportModel;
import org.struts.lib.ParaActionSupport;

public class PFTrustMasterReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PFTrustMasterReportAction.class);
	
	PFTrustMasterReport pftrustBean=null;
	
	public void prepare_local() throws Exception {
		pftrustBean = new PFTrustMasterReport();
		pftrustBean.setMenuCode(2226);
	}

	public Object getModel() {
		return pftrustBean;
	}
	
	public String input(){
		return INPUT;
	}
	
	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION  ";
		
		
		if(pftrustBean.getUserProfileDivision() !=null && pftrustBean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+pftrustBean.getUserProfileDivision() +")"; 
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

		String[] fieldNames = { "sDivId", "sDivName" };

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
	}// end of f9div
	
	public String f9branch() {
		try {
			String query = 	" SELECT DISTINCT CENTER_NAME, CENTER_ID "
							+ " FROM "
							+ "		HRMS_SALARY_MISC A "
							+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
							+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "
							+ "   	INNER JOIN HRMS_CENTER D ON (D.CENTER_ID = C.EMP_CENTER) "
							+ " ORDER BY " 
							+ "		CENTER_NAME ";

			String[] headers = { getMessage("branch") };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "sBrchName", "sBrchId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	public String f9department() {
		try {
			String query = 	" SELECT "
							+ "		DISTINCT DEPT_NAME,"
							+ "		DEPT_ID "
							+ "	FROM "
							+ "		HRMS_SALARY_MISC A "
							+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
							+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "
							+ " 	INNER JOIN HRMS_DEPT D ON (D.DEPT_ID = C.EMP_DEPT) "
							+ " ORDER BY " 
							+ "		DEPT_NAME ";

			String[] headers = { getMessage("department") };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "sDeptName", "sDeptId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	
	public void report(){
		try {
			PFTrustMasterReportModel model = new PFTrustMasterReportModel();
			model.initiate(context, session);
			model.getReport(pftrustBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String reset(){
		pftrustBean.setSDivId("");
		pftrustBean.setSDivName("");
		pftrustBean.setToMonth("");
		pftrustBean.setToYear("");
		pftrustBean.setSBrchId("");
		pftrustBean.setSBrchName("");
		pftrustBean.setSDeptId("");
		pftrustBean.setSDeptName("");
		return INPUT;
	}
}
