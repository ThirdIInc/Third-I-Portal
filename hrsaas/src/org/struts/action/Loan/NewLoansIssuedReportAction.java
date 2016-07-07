package org.struts.action.Loan;



import org.paradyne.bean.Loan.NewLoansIssuedReport;
import org.paradyne.model.extraWorkBenefits.ExtraWorkBenefitMisModel;
import org.paradyne.model.loan.NewLoansIssuedReportModel;
import org.struts.action.PAS.AppraisalBellCurveAction;
import org.struts.lib.ParaActionSupport;

public class NewLoansIssuedReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalBellCurveAction.class);
	NewLoansIssuedReport misreport = null;

	public void prepare_local() throws Exception {
		misreport = new NewLoansIssuedReport();
		misreport.setMenuCode(930);
	}

	public Object getModel() {
		return misreport;
	}

	public void setNewLoansIssedReport(NewLoansIssuedReport newLoansIssuedReport) {
		this.misreport = newLoansIssuedReport;
	}
	
	 public String input()
	 {
		 
		 try {
			 getPopulateData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT ;
	 }
	
 
	private void getPopulateData() throws Exception {
		try {
			NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
			model.initiate(context, session);
			/* Get 'Loan Type' */
			model.getLoanType(misreport, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Display the report on screen
	 * @return String
	 */
	public String viewOnScreen() {
		
		misreport.setBackFlag("");
			
		try {
			getPopulateData();
			NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("loan.amount"), getMessage("loan.type"),
					getMessage("loan.paymentDt"), getMessage("loan.monthInstall"),
					getMessage("noOfInstall") };
			model.callViewScreen(misreport, request, labelNames);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
			e.printStackTrace();
		}
		return "viewOnScreen";
	}
	
	/**
	 * Called on Back button in view screen
	 * @return String
	 * returns to the mis filter page
	 */
	public String callBack() {

		try {
			getPopulateData();
			NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
			model.initiate(context, session);
			try {
				prepare_withLoginProfileDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}//end of try-catch block
			model.terminate();
			misreport.setBackFlag("true");
			logger.info("getSortByOrder..." + misreport.getSortByOrder());
			if (misreport.getSortByOrder().trim().equals("D"))
				misreport.setSortByDsc("checked");
			if (misreport.getSortByOrder().trim().equals("A"))
				misreport.setSortByAsc("checked");
			if (misreport.getThenByOrder1().trim().equals("D"))
				misreport.setThenByOrder1Dsc("checked");
			if (misreport.getThenByOrder1().trim().equals("A"))
				misreport.setThenByOrder1Asc("checked");
			if (misreport.getThenByOrder2().trim().equals("D"))
				misreport.setThenByOrder2Dsc("checked");
			if (misreport.getThenByOrder2().trim().equals("A"))
				misreport.setThenByOrder2Asc("checked");
			misreport.setHiddenSortBy(misreport.getSortBy());
			misreport.setHiddenThenBy1(misreport.getThenBy1());
			misreport.setHiddenThenBy2(misreport.getThenBy2());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	
	public String report() throws Exception
	{
		try {
			misreport.setBackFlag("");
			NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("loan.amount"), getMessage("loan.type"),
					getMessage("loan.paymentDt"), getMessage("loan.monthInstall"),
					getMessage("noOfInstall") };
			model.getReport(misreport, response, labelNames, request);
		
			model.terminate();
			getPopulateData();
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String setDetails() throws Exception {
		
		getPopulateData();
		NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
		try {
			model.initiate(context, session);
			reset();
			model.setDetailOnScreen(misreport);
			 			if (misreport.getSortByOrder().trim().equals("D"))
				misreport.setSortByDsc("checked");
			if (misreport.getSortByOrder().trim().equals("A"))
				misreport.setSortByAsc("checked");
			if (misreport.getThenByOrder1().trim().equals("D"))
				misreport.setThenByOrder1Dsc("checked");
			if (misreport.getThenByOrder1().trim().equals("A"))
				misreport.setThenByOrder1Asc("checked");
			if (misreport.getThenByOrder2().trim().equals("D"))
				misreport.setThenByOrder2Dsc("checked");
			if (misreport.getThenByOrder2().trim().equals("A"))
				misreport.setThenByOrder2Asc("checked");
		} catch (Exception e) {
			logger.error("Exception in setDetails-------------"+e);
		}
		model.terminate();
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

		String[] fieldNames = { "settingName", "misreport.reportTitle","misreport.reportId","reportTypeStr" };

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
		String submitToMethod = "NewLoanIssuedRpt_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


	public String saveReport() throws Exception {
		try {
			NewLoansIssuedReportModel model = new NewLoansIssuedReportModel();
			model.initiate(context, session);
			logger.info("Report id........." + misreport.getReportId());
			model.deleteSavedReport(misreport);
			boolean result = model.saveReportCriteria(misreport);
			if (result)
				addActionMessage(getMessage("save"));
			else
				addActionMessage(getMessage("duplicate"));
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in saveReport-------------"+e);
			}
		getPopulateData();
		return SUCCESS;
	}
	
	public String reset() {
		try {
			
			getPopulateData();
			misreport.setDivCode("");
			misreport.setDivName("");
			misreport.setDeptCode("");
			misreport.setDeptName("");
			misreport.setBranchCode("");
			misreport.setBranchName("");
			misreport.setDesignationCode("");
			misreport.setDesignationName("");
			misreport.setEmpCode("");
			misreport.setEmpName("");
			misreport.setEmpToken("");
			misreport.setEmpNameFlag("");
			misreport.setDivFlag("");
			misreport.setLoanAmountFlag("");
			misreport.setSavedReport("");
			
			misreport.setReportView("");
		 	
			misreport.setSortBy("");
			misreport.setSortByAsc("checked");
			misreport.setSortByDsc("");
			misreport.setSortByOrder("");
			misreport.setThenBy1("");
			misreport.setThenByOrder1Asc("checked");
			misreport.setThenByOrder1Dsc("");
			misreport.setThenByOrder1("");
			misreport.setThenBy2("");
			misreport.setThenByOrder2Asc("checked");
			misreport.setThenByOrder2("");
			misreport.setThenByOrder2Dsc("");
			misreport.setHiddenSortBy("");
			misreport.setHiddenThenBy1("");
			misreport.setHiddenThenBy2("");
			misreport.setHiddenColumns("");
			misreport.setSortByAsc("checked");
			misreport.setHidReportView("checked");
			
			misreport.setHidReportRadio("");
			misreport.setReportType("");
			//misreport.setSettingName("");
			
			misreport.setBackFlag("");
		 
			
			misreport.setLoanTypeFlag("");
			misreport.setLoanPaymentDateFlag("");
			misreport.setLoanInstallmentFlag("");
			misreport.setNoOfInstallFlag("");
			misreport.setLoanDateSelect("");
			misreport.setLoanFromDate("");
			
			//misreport.setEmpTokenFlag("");
			 
		 
			misreport.setLoanToDate("");
			 
		} catch (Exception e) {
			logger.error("Exception in reset-------------"+e);
		}
		return SUCCESS;
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
	
	public String f9employee()throws Exception
	{
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
	
	public String f9designation()throws Exception
	{
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

	public NewLoansIssuedReport getMisreport() {
		return misreport;
	}

	public void setMisreport(NewLoansIssuedReport misreport) {
		this.misreport = misreport;
	}

}
