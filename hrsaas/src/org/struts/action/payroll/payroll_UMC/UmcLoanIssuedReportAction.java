package org.struts.action.payroll.payroll_UMC;

import org.paradyne.bean.payroll.payroll_UMC.UmcLoanIssuedReport;
import org.paradyne.model.loan.NewLoansIssuedReportModel;
import org.paradyne.model.payroll.payroll_UMC.UmcLoanIssuedReportModel;
import org.struts.action.PAS.AppraisalBellCurveAction;
import org.struts.lib.ParaActionSupport;

public class UmcLoanIssuedReportAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(UmcLoanIssuedReportAction.class);
	
	UmcLoanIssuedReport umcLoanReportBean = null;

	public void prepare_local() throws Exception {
		umcLoanReportBean = new UmcLoanIssuedReport();
		umcLoanReportBean.setMenuCode(930);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return umcLoanReportBean;
	}

	public UmcLoanIssuedReport getUmcLoanReportBean() {
		return umcLoanReportBean;
	}

	public void setUmcLoanReportBean(UmcLoanIssuedReport umcLoanReportBean) {
		this.umcLoanReportBean = umcLoanReportBean;
	}
	
	public String input() {
		logger.info("######## IN INPUT METHOD ##############");
		return INPUT ;
	 }
	
	public String f9divaction() {
		try {
			String query = 	" SELECT " 
						 	+ " 	DISTINCT DIV_NAME,DIV_ID  " 
						 	+ " FROM " 
						 	+ " 	HRMS_LOAN_APPLICATION A " 
						 	+ " 	INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID) "
						 	+ " 	INNER JOIN HRMS_LOAN_PROCESS C ON (A.LOAN_APPL_CODE = C.LOAN_APPL_CODE) "
						 	+ "		INNER JOIN HRMS_DIVISION D ON (D.DIV_ID = B.EMP_DIV)"
						 	+ " ORDER BY " 
						 	+ " 	DIV_NAME ";

			String[] headers = { getMessage("division") };
			
			String[] headerWidth = { "100" };

			String[] fieldNames = { "divName","divCode" };

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
	
	public String f9branch() {
		try {
			String query = 	" SELECT "
							+ " 	DISTINCT CENTER_NAME,"
							+ "		CENTER_ID "
							+ " FROM "
							+ "		HRMS_LOAN_APPLICATION A "
							+ " 	INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID) "
							+ " 	INNER JOIN HRMS_LOAN_PROCESS C ON (A.LOAN_APPL_CODE = C.LOAN_APPL_CODE) "
							+ " 	INNER JOIN HRMS_CENTER D ON (D.CENTER_ID = B.EMP_CENTER) "
							+ " ORDER BY " 
							+ "		CENTER_NAME ";

			String[] headers = { getMessage("branch") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "branchName" ,"branchCode"};

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
							+ "		HRMS_LOAN_APPLICATION A "
							+ " 	INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID) "
							+ " 	INNER JOIN HRMS_LOAN_PROCESS C ON (A.LOAN_APPL_CODE = C.LOAN_APPL_CODE) "
							+ " 	INNER JOIN HRMS_DEPT D ON (D.DEPT_ID = B.EMP_DEPT) "
							+ " ORDER BY " 
							+ "		DEPT_NAME ";

			String[] headers = { getMessage("department") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "deptName" ,"deptCode"};

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
	
	public String f9employee()throws Exception {
		String query = 	" SELECT "
						+ "		DISTINCT B.EMP_TOKEN, "
						+ "		(B.EMP_FNAME || ' ' || B.EMP_MNAME|| ' ' || B.EMP_LNAME) AS EMPLOYEE_NAME, "
						+ "		B.EMP_ID "
						+ " FROM "
						+ "		HRMS_LOAN_APPLICATION A  "
						+ "		INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID) "
						+ " 	INNER JOIN HRMS_LOAN_PROCESS C ON (A.LOAN_APPL_CODE = C.LOAN_APPL_CODE) "
						+ " ORDER BY " 
						+ "		EMPLOYEE_NAME ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"empToken","empName" ,"empCode"}; 
		
		int[] columnIndex = { 0,1 ,2};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9designation()throws Exception {
		String query = 	" SELECT "
						+ "		DISTINCT RANK_NAME, " 
						+ "		RANK_ID "
						+ " FROM "
						+ "		HRMS_LOAN_APPLICATION A  "
						+ "		INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID) "
						+ " 	INNER JOIN HRMS_RANK C ON (C.RANK_ID = B.EMP_RANK) "
						+ "		INNER JOIN HRMS_LOAN_PROCESS D ON (A.LOAN_APPL_CODE = D.LOAN_APPL_CODE) "	
						+ " ORDER BY " 
						+ "		RANK_NAME ";
		
		String[] headers = { "Designation" };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "designationName", "designationCode" }; 
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String saveReport() throws Exception {
		try {
			UmcLoanIssuedReportModel model = new UmcLoanIssuedReportModel();
			model.initiate(context, session);
			logger.info("Report id........." + umcLoanReportBean.getReportId());
			model.deleteSavedReport(umcLoanReportBean);
			boolean result = model.saveReportCriteria(umcLoanReportBean);
			if (result)
				addActionMessage(getMessage("save"));
			else
				addActionMessage(getMessage("duplicate"));
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in saveReport-------------"+e);
			}
		return SUCCESS;
	}
	
	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '),REPORT_ID,REPORT_TYPE FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE='Loan' ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),getMessage("report.title") };

		String[] headerWidth = { "50","50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName", "reportTitle","reportId","reportTypeStr" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2,3 };

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
		String submitToMethod = "LoanIssuedRpt_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String setDetails() throws Exception {
		
		UmcLoanIssuedReportModel model = new UmcLoanIssuedReportModel();
		try {
			model.initiate(context, session);
			reset();
			model.setDetailOnScreen(umcLoanReportBean);
			if (umcLoanReportBean.getSortByOrder().trim().equals("D"))
				umcLoanReportBean.setSortByDsc("checked");
			if (umcLoanReportBean.getSortByOrder().trim().equals("A"))
				umcLoanReportBean.setSortByAsc("checked");
			if (umcLoanReportBean.getThenByOrder1().trim().equals("D"))
				umcLoanReportBean.setThenByOrder1Dsc("checked");
			if (umcLoanReportBean.getThenByOrder1().trim().equals("A"))
				umcLoanReportBean.setThenByOrder1Asc("checked");
			if (umcLoanReportBean.getThenByOrder2().trim().equals("D"))
				umcLoanReportBean.setThenByOrder2Dsc("checked");
			if (umcLoanReportBean.getThenByOrder2().trim().equals("A"))
				umcLoanReportBean.setThenByOrder2Asc("checked");
		} catch (Exception e) {
			logger.error("Exception in setDetails-------------"+e);
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String reset() {
		try {
			
			umcLoanReportBean.setDivCode("");
			umcLoanReportBean.setDivName("");
			umcLoanReportBean.setDeptCode("");
			umcLoanReportBean.setDeptName("");
			umcLoanReportBean.setBranchCode("");
			umcLoanReportBean.setBranchName("");
			umcLoanReportBean.setDesignationCode("");
			umcLoanReportBean.setDesignationName("");
			umcLoanReportBean.setEmpCode("");
			umcLoanReportBean.setEmpName("");
			umcLoanReportBean.setEmpToken("");
			umcLoanReportBean.setEmpNameFlag("");
			umcLoanReportBean.setDivFlag("");
			umcLoanReportBean.setLoanAmountFlag("");
			umcLoanReportBean.setSavedReport("");
			
			umcLoanReportBean.setReportView("");
		 	
			umcLoanReportBean.setSortBy("");
			umcLoanReportBean.setSortByAsc("checked");
			umcLoanReportBean.setSortByDsc("");
			umcLoanReportBean.setSortByOrder("");
			umcLoanReportBean.setThenBy1("");
			umcLoanReportBean.setThenByOrder1Asc("checked");
			umcLoanReportBean.setThenByOrder1Dsc("");
			umcLoanReportBean.setThenByOrder1("");
			umcLoanReportBean.setThenBy2("");
			umcLoanReportBean.setThenByOrder2Asc("checked");
			umcLoanReportBean.setThenByOrder2("");
			umcLoanReportBean.setThenByOrder2Dsc("");
			umcLoanReportBean.setHiddenSortBy("");
			umcLoanReportBean.setHiddenThenBy1("");
			umcLoanReportBean.setHiddenThenBy2("");
			umcLoanReportBean.setHiddenColumns("");
			umcLoanReportBean.setSortByAsc("checked");
			umcLoanReportBean.setHidReportView("checked");
			
			umcLoanReportBean.setHidReportRadio("");
			umcLoanReportBean.setReportType("");
			//umcLoanReportBean.setSettingName("");
			
			umcLoanReportBean.setBackFlag("");
			
			umcLoanReportBean.setLoanPaymentDateFlag("");
			umcLoanReportBean.setLoanDateSelect("");
			umcLoanReportBean.setLoanFromDate("");
			umcLoanReportBean.setLoanAmountPaidChkBox("");
			umcLoanReportBean.setLoanPendingAmtChkBox("");
			
			//umcLoanReportBean.setEmpTokenFlag("");
		 
			umcLoanReportBean.setLoanToDate("");
			 
		} catch (Exception e) {
			logger.error("Exception in reset-------------"+e);
		}
		return SUCCESS;
	}
	
	public String report() throws Exception	{
		try {
			umcLoanReportBean.setBackFlag("");
			UmcLoanIssuedReportModel model = new UmcLoanIssuedReportModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("loan.amount"),getMessage("loanPendingAmt"), 
					getMessage("loan.paymentDt"), getMessage("loan.monthInstall"), getMessage("loanAmountPaid") };
			model.getReport(umcLoanReportBean, response, labelNames, request);
		
			model.terminate();
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
public String viewOnScreen() {
		
	umcLoanReportBean.setBackFlag("");
			
		try {
			UmcLoanIssuedReportModel model = new UmcLoanIssuedReportModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("loan.amount"),getMessage("loanPendingAmt"), 
					getMessage("loan.paymentDt"), getMessage("loan.monthInstall"), getMessage("loanAmountPaid") };
			model.callViewScreen(umcLoanReportBean, request, labelNames);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
			e.printStackTrace();
		}
		return "viewOnScreen";
	}

public String callBack() {

	try {
		
		UmcLoanIssuedReportModel model = new UmcLoanIssuedReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch block
		model.terminate();
		umcLoanReportBean.setBackFlag("true");
		logger.info("getSortByOrder..." + umcLoanReportBean.getSortByOrder());
		if (umcLoanReportBean.getSortByOrder().trim().equals("D"))
			umcLoanReportBean.setSortByDsc("checked");
		if (umcLoanReportBean.getSortByOrder().trim().equals("A"))
			umcLoanReportBean.setSortByAsc("checked");
		if (umcLoanReportBean.getThenByOrder1().trim().equals("D"))
			umcLoanReportBean.setThenByOrder1Dsc("checked");
		if (umcLoanReportBean.getThenByOrder1().trim().equals("A"))
			umcLoanReportBean.setThenByOrder1Asc("checked");
		if (umcLoanReportBean.getThenByOrder2().trim().equals("D"))
			umcLoanReportBean.setThenByOrder2Dsc("checked");
		if (umcLoanReportBean.getThenByOrder2().trim().equals("A"))
			umcLoanReportBean.setThenByOrder2Asc("checked");
		umcLoanReportBean.setHiddenSortBy(umcLoanReportBean.getSortBy());
		umcLoanReportBean.setHiddenThenBy1(umcLoanReportBean.getThenBy1());
		umcLoanReportBean.setHiddenThenBy2(umcLoanReportBean.getThenBy2());
	} catch (Exception e) {
		// TODO: handle exception
	}
	return SUCCESS;
}

	

}
