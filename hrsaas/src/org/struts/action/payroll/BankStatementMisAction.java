package org.struts.action.payroll;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.BankStatementMis;
import org.paradyne.model.payroll.BankStatementMisModel;

public class BankStatementMisAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BankStatementMisAction.class);

	BankStatementMis bankStatementMisBean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bankStatementMisBean = new BankStatementMis();
		bankStatementMisBean.setMenuCode(1051);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bankStatementMisBean;
	}

	public BankStatementMis getBankStatementMisBean() {
		return bankStatementMisBean;
	}

	public void setBankStatementMisBean(BankStatementMis bankStatementMisBean) {
		this.bankStatementMisBean = bankStatementMisBean;
	}
	
	public String callBack() {
		BankStatementMisModel model=null;
		try {
			model= new BankStatementMisModel();
			model.initiate(context, session);
			try {
				prepare_withLoginProfileDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}//end of try-catch block
			;
			bankStatementMisBean.setBackFlag("true");
			logger.info("getSortByOrder..."
					+ bankStatementMisBean.getSortByOrder());
			if (bankStatementMisBean.getSortByOrder().trim().equals("D"))
				bankStatementMisBean.setSortByDsc("checked");
			if (bankStatementMisBean.getSortByOrder().trim().equals("A"))
				bankStatementMisBean.setSortByAsc("checked");
			if (bankStatementMisBean.getThenByOrder1().trim().equals("D"))
				bankStatementMisBean.setThenByOrder1Dsc("checked");
			if (bankStatementMisBean.getThenByOrder1().trim().equals("A"))
				bankStatementMisBean.setThenByOrder1Asc("checked");
			if (bankStatementMisBean.getThenByOrder2().trim().equals("D"))
				bankStatementMisBean.setThenByOrder2Dsc("checked");
			if (bankStatementMisBean.getThenByOrder2().trim().equals("A"))
				bankStatementMisBean.setThenByOrder2Asc("checked");
			bankStatementMisBean.setHiddenSortBy(bankStatementMisBean
					.getSortBy());
			bankStatementMisBean.setHiddenThenBy1(bankStatementMisBean
					.getThenBy1());
			bankStatementMisBean.setHiddenThenBy2(bankStatementMisBean
					.getThenBy2());
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
		return SUCCESS;
	}
	public String viewOnScreen()
	{
		try {
			BankStatementMisModel model = new BankStatementMisModel();
			model.initiate(context, session);
			bankStatementMisBean.setBackFlag("");
			String[] labelNames = { getMessage("account.no"),
					getMessage("currency.code"), getMessage("sol.id"),
					getMessage("credit.debit"), getMessage("transaction.type"),
					getMessage("ifsc.code"), 
					getMessage("emp.name"), getMessage("transaction.details"),
					getMessage("customer.refno"),
					getMessage("transaction.date"), getMessage("bank"),
					getMessage("bank.brnName"),
					getMessage("beneficary.emailid"),
					getMessage("divisionAccountNo")	};
			model.callViewScreen(bankStatementMisBean, request, labelNames);
		model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "viewOnScreen";
	}
	
	public String report() {

		try {
			BankStatementMisModel model = new BankStatementMisModel();
			model.initiate(context, session);
			bankStatementMisBean.setBackFlag("");
			String[] labelNames = { getMessage("account.no"),
					getMessage("currency.code"), getMessage("sol.id"),
					getMessage("credit.debit"), getMessage("transaction.type"),
					getMessage("ifsc.code"), 
					getMessage("emp.name"), getMessage("transaction.details"),
					getMessage("customer.refno"),
					getMessage("transaction.date"), getMessage("bank"),
					getMessage("bank.brnName"),
					getMessage("beneficary.emailid"),
					getMessage("divisionAccountNo")	};
					

			model
					.getReport(bankStatementMisBean, response, labelNames,
							request);
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public String reset() {
		try {
			bankStatementMisBean.setDivCode("");
			bankStatementMisBean.setDivName("");
			bankStatementMisBean.setCenterCode("");
			bankStatementMisBean.setCenterName("");
			bankStatementMisBean.setBankCode("");
			bankStatementMisBean.setBankName("");
			bankStatementMisBean.setAccNumberFlag("");
			bankStatementMisBean.setCurrencyCodeFlag("");
			bankStatementMisBean.setSolIdFlag("");
			bankStatementMisBean.setCreditdebitFlag("");
			bankStatementMisBean.setTransactiontypeFlag("");
			bankStatementMisBean.setIfsccodeFlag("");
			//bankStatementMisBean.setEmpcodeFlag("");
			bankStatementMisBean.setEmpnameFlag("");
			bankStatementMisBean.setTransactionDtlFlag("");
			bankStatementMisBean.setCustomerrefnoFlag("");
			bankStatementMisBean.setTransactionDateFlag("");
			bankStatementMisBean.setBankFlag("");
			bankStatementMisBean.setBankbrnNameFlag("");
			bankStatementMisBean.setBeneficaryEmailidFlag("");

			bankStatementMisBean.setSortBy("");
			bankStatementMisBean.setSortByAsc("checked");
			bankStatementMisBean.setSortByDsc("");
			bankStatementMisBean.setSortByOrder("");
			bankStatementMisBean.setThenBy1("");
			bankStatementMisBean.setThenByOrder1Asc("checked");
			bankStatementMisBean.setThenByOrder1Dsc("");
			bankStatementMisBean.setThenByOrder1("");
			bankStatementMisBean.setThenBy2("");
			bankStatementMisBean.setThenByOrder2Asc("checked");
			bankStatementMisBean.setThenByOrder2("");
			bankStatementMisBean.setThenByOrder2Dsc("");
			bankStatementMisBean.setHiddenSortBy("");
			bankStatementMisBean.setHiddenThenBy1("");
			bankStatementMisBean.setHiddenThenBy2("");

			bankStatementMisBean.setHiddenColumns("");

			bankStatementMisBean.setHidReportView("checked");
			bankStatementMisBean.setHidReportRadio("");
			bankStatementMisBean.setReportType("");

			bankStatementMisBean.setMyPage("");
			bankStatementMisBean.setShow("");
			bankStatementMisBean.setBackFlag("");
			
			bankStatementMisBean.setMonth("");
			bankStatementMisBean.setYear("");

		} catch (Exception e) {

		}
		return SUCCESS;
	}

	public String saveReport() throws Exception {
		try {
			BankStatementMisModel model = new BankStatementMisModel();
			model.initiate(context, session);
			logger.info("Report id........."
					+ bankStatementMisBean.getReportId());
			model.deleteSavedReport(bankStatementMisBean);
			boolean result = model.saveReportCriteria(bankStatementMisBean);
			if (result)
				addActionMessage(getMessage("save"));
			else
				addActionMessage(getMessage("duplicate"));
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String setDetails() throws Exception {
		BankStatementMisModel model = new BankStatementMisModel();
		model.initiate(context, session);
		reset();
		model.setDetailOnScreen(bankStatementMisBean);
		model.terminate();
		if (bankStatementMisBean.getSortByOrder().trim().equals("D"))
			bankStatementMisBean.setSortByDsc("checked");
		if (bankStatementMisBean.getSortByOrder().trim().equals("A"))
			bankStatementMisBean.setSortByAsc("checked");
		if (bankStatementMisBean.getThenByOrder1().trim().equals("D"))
			bankStatementMisBean.setThenByOrder1Dsc("checked");
		if (bankStatementMisBean.getThenByOrder1().trim().equals("A"))
			bankStatementMisBean.setThenByOrder1Asc("checked");
		if (bankStatementMisBean.getThenByOrder2().trim().equals("D"))
			bankStatementMisBean.setThenByOrder2Dsc("checked");
		if (bankStatementMisBean.getThenByOrder2().trim().equals("A"))
			bankStatementMisBean.setThenByOrder2Asc("checked");

		return SUCCESS;
	}

	/**
	 * Search division details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
		if (bankStatementMisBean.getUserProfileDivision() != null
				&& bankStatementMisBean.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ bankStatementMisBean.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

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

		String[] fieldNames = { "bankStatementMisBean.divName",
				"bankStatementMisBean.divCode" };

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
	 * Search branch details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9centeraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER "
				/*
				 * + " ,DEPT_NAME,DIV_NAME LEFT JOIN HRMS_DEPT
				 * ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)" + " LEFT
				 * JOIN HRMS_DIVISION
				 * ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE) "
				 */
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bankStatementMisBean.centerName",
				"bankStatementMisBean.centerCode" };

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
	 * Search branch details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9bankaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT BANK_NAME,BANK_MICR_CODE FROM HRMS_BANK ORDER BY BANK_MICR_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("bank") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bankStatementMisBean.bankName",
				"bankStatementMisBean.bankCode" };

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

	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '),REPORT_ID,REPORT_TYPE FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE='BankStatementMis' ORDER BY  REPORT_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),
				getMessage("report.title") };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName",
				"bankStatementMisBean.reportTitle",
				"bankStatementMisBean.reportId", "reportTypeStr" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

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
		String submitToMethod = "BankStatementMis_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
