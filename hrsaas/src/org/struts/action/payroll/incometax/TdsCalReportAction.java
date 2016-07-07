package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.TdsCalReport;
import org.paradyne.model.payroll.incometax.TdsCalReportModel;
import org.struts.lib.ParaActionSupport;

public class TdsCalReportAction extends ParaActionSupport {

	TdsCalReport tdsCalReport;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		tdsCalReport = new TdsCalReport();
		tdsCalReport.setMenuCode(286);
	}

	public Object getModel() {
		return tdsCalReport;
	}

	public TdsCalReport getTdsCalReport() {
		return tdsCalReport;
	}

	public void setTdsCalReport(TdsCalReport tdsCalReport) {
		this.tdsCalReport = tdsCalReport;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TdsCalReportModel model = new TdsCalReportModel();
		model.initiate(context, session);
		model.getFilters(tdsCalReport);
		model.terminate();
		
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String split[] = sysdate.split("/");
		int year = Integer.parseInt(String.valueOf(split[2]));
		tdsCalReport.setFromYear(String.valueOf(year));
		tdsCalReport.setToYear(String.valueOf(year + 1));
		
	}

	public String report() throws Exception {
		TdsCalReportModel model = new TdsCalReportModel();
		model.initiate(context, session);
		/*
		 * logger.info("1111111111111111111111111111111-----------######################################"+tdsCalReport.getFromYear());
		 * logger.info("22222222222222222222222222221-----------
		 * "+tdsCalReport.getToYear());
		 * logger.info("23333333333333333333333333333333333333333333-----------
		 * #"+tdsCalReport.getPayBillNo());
		 */
		boolean result = model.getReport(tdsCalReport, response);
		if (result) {
			addActionMessage("Report Generated Successfully");
		} else {
			addActionMessage("No Data Available");
		}
		model.terminate();

		return null;
	}

	public String reset() throws Exception {
		logger
				.info("#######################################################in reset");
		tdsCalReport.setFromYear("");
		tdsCalReport.setToYear("");
		tdsCalReport.setPayBillNo("");
		tdsCalReport.setDeptName("");
		tdsCalReport.setDivisionName("");
		tdsCalReport.setTypeName("");
		tdsCalReport.setBranchName("");

		return SUCCESS;
	}

	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("taxf9PayBlName"),
				getMessage("taxf9PayBlNo") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "tdsCalReport.payBillNo",
				"tdsCalReport.payBillNo" };

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

	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "tdsCalReport.branchName",
					"tdsCalReport.branchCode" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String f9Dept() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = { getMessage("department") };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "tdsCalReport.deptName",
					"tdsCalReport.deptCode" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String f9Div() {
		try {
						
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			if(tdsCalReport.getUserProfileDivision() !=null && tdsCalReport.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+tdsCalReport.getUserProfileDivision() +")"; 
				query+= " ORDER BY  UPPER(DIV_NAME) ";

			String[] headers = { getMessage("division") };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "tdsCalReport.divisionName",
					"tdsCalReport.divisionCode" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT TYPE_NAME,TYPE_ID FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.type") };

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

		String[] fieldNames = { "tdsCalReport.typeName",
				"tdsCalReport.typeCode" };

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

}
