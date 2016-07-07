package org.struts.action.eGov.reports;


import org.paradyne.bean.eGov.reports.PFBalanceeGovReportBean;
import org.paradyne.model.eGov.reports.PFBalanceeGovReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class PFBalanceeGovReportAction extends ParaActionSupport {

	PFBalanceeGovReportBean eGovpfbalnce;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		eGovpfbalnce = new PFBalanceeGovReportBean ();
		eGovpfbalnce.setMenuCode(1102);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		
		return eGovpfbalnce;
	}

	/**
	 * @return the eGovpfbalnce
	 */
	public PFBalanceeGovReportBean getEGovpfbalnce() {
		return eGovpfbalnce;
	}

	/**
	 * @param govpfbalnce the eGovpfbalnce to set
	 */
	public void setEGovpfbalnce(PFBalanceeGovReportBean govpfbalnce) {
		eGovpfbalnce = govpfbalnce;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String f9Deptaction() throws Exception {
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_NAME ";
		String[] headers = {getMessage("department") };

		String[] headerWidth = {"100" };


		String[] fieldNames = { "deptName","deptCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9Div() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM  HRMS_DIVISION ";

		if (eGovpfbalnce.getUserProfileDivision() != null
				&& eGovpfbalnce.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + eGovpfbalnce.getUserProfileDivision()
					+ ")";
		query += " ORDER BY  DIV_NAME ";
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };


		String[] fieldNames = { "divName","divCode" };

		int[] columnIndex = { 0, 1 };


		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9BranchAction() throws Exception {
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_NAME ";

		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };


		String[] fieldNames = {  "branchName","branchCode" };

		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL order by PAYBILL_ID asc";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Pay Bill",getMessage("pay.bill") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"30","70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "paybillId",
				"payBill" };

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
	}
	
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String getPFBalanceReport() throws Exception {
		PFBalanceeGovReportModel model = new PFBalanceeGovReportModel();
		model.initiate(context, session);
		model.generateReport(eGovpfbalnce,response);
		model.terminate();
		return null;
	}
	
	
}
