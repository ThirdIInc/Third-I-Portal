package org.struts.action.payroll;

import org.paradyne.bean.payroll.LWFReport;
import org.paradyne.model.payroll.LwfReportModel;
import org.struts.lib.ParaActionSupport;

public class LWFReportAction extends ParaActionSupport {

	LWFReport lwf;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		lwf = new LWFReport();
		lwf.setMenuCode(1095);
	}

	public Object getModel() {
		return this.lwf;
	}

	public String input() {
		return INPUT;
	}

	/**
	 * Reset the fields of the form
	 * 
	 * @return String
	 */
	public String reset() {
		lwf.setBrnCode("");
		lwf.setTypeCode("");
		lwf.setTypeName("");
		lwf.setMonth("");
		lwf.setYear("");
		lwf.setPayBillNo("");
		lwf.setPayBillName("");
		lwf.setDivisionAbbrevation("");
		lwf.setDivCode("");
		lwf.setDivName("");
		lwf.setBrnCode("");
		lwf.setBrnName("");
		lwf.setDeptCode("");
		lwf.setDeptName("");
		lwf.setOnHold("");
		lwf.setReportType("");
		return SUCCESS;

	}

	/*
	 * Following function is called to generate the LWF report.
	 */
	public String lwfReport() {

		LwfReportModel model = new LwfReportModel();
		model.initiate(context, session);
		try {
			String reportPath = "";
			model.generateLWFReport(lwf, request, response, reportPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return null;
	}

	public final String lwfmailReport() {
		try {
			LwfReportModel model = new LwfReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Payroll"
					+ poolName + "/";
			model.generateLWFReport(lwf, request, response, reportPath);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	public final String f9lwfreportType() {
		try {
			String[][] type = new String[][] { { "PDF" }, { "xls" }, { "Html" } };
			String[] headers = { getMessage("report.type") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "reportType" };
			int[] columnIndex = { 0 };
			String submitFlag = "true";
			String submitToMethod = "LWFReport_lwfmailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/*
	 * Following function is called in the jsp page to show the employee type and id
	 */

	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   HRMS_EMP_TYPE.TYPE_NAME , HRMS_EMP_TYPE.TYPE_ID FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("type.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "typeName", "typeCode" };

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
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/*
	 * Following function is called to show the pay bill id and name in the jsp
	 */

	public String f9payBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL ";
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";

			String[] headers = { "Paybill Code", getMessage("pay.bill") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "payBillNo", "payBillName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/*
	 * Following function is called in the jsp page to show the department id and name
	 */
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT HRMS_DEPT.DEPT_ID,NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

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

		logger.info("4");
		return "f9page";
	}

	/*
	 * Following function is called to generate the branch id and branch name in the jsp page
	 */
	public String f9brn() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID,NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY HRMS_CENTER.CENTER_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch.code"), getMessage("branch") };

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

		String[] fieldNames = { "brnCode", "brnName" };

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

		logger.info("4");
		return "f9page";
	}

	/*
	 * Following function is called to show the division name and division id in the jsp 
	 */
	public String f9div() throws Exception {
		String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' '),HRMS_DIVISION.DIV_ADDRESS1 ||' '||HRMS_DIVISION.DIV_ADDRESS2 ||' '||DIV_ADDRESS3 AS Address,DIV_ESI_CODE, NVL(DIV_ABBR,' ') FROM HRMS_DIVISION";

		if (lwf.getUserProfileDivision() != null
				&& lwf.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + lwf.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		String header = getMessage("division");
		
		String textAreaId ="paraFrm_divName";
		String hiddenFieldId ="paraFrm_divCode";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		logger.info("4");
		return "f9multiSelect";
	}

}
