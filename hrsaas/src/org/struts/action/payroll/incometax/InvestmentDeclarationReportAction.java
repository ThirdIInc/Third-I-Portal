package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.InvestmentDeclarationReport;
import org.paradyne.model.payroll.incometax.InvestmentDeclarationReportModel;
import org.struts.lib.ParaActionSupport;

public class InvestmentDeclarationReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InvestmentDeclarationReportAction.class);

	InvestmentDeclarationReport investmentBean = null;
	
	public void prepare_local() throws Exception {
		investmentBean = new InvestmentDeclarationReport();
		investmentBean.setMenuCode(2050);
	}

	public Object getModel() {
		return investmentBean;
	}
	
	public String input() throws Exception {
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysdate = formater.format(date);
			String[] split = sysdate.split("/");
			int year = Integer.parseInt(String.valueOf(split[2]));
			investmentBean.setFromYear(String.valueOf(year));
			investmentBean.setToYear(String.valueOf(year + 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT ;
	}
	
	/**
	 * This action is called to get the list of all the employee records.
	 * 
	 * @return String (f9 page)
	 * @throws Exception
	 */
/*	public String f9empaction() throws Exception {
		*//**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 *//*

		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')," 
			+ " NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CENTER.CENTER_NAME,' ') , HRMS_DIVISION.DIV_NAME, HRMS_EMP_OFFC.EMP_ID," 
			+ " EMP_CENTER, RANK_ID, HRMS_EMP_OFFC.EMP_DIV "
			+ " FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "	 
			+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
			+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
			+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) ";
		
		query += getprofileQuery(investmentBean);

		query += "	AND EMP_STATUS = 'S' ORDER BY HRMS_EMP_OFFC.EMP_ID";
		*//**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 *//*
		String[] headers = { getMessage("employee.id"),
				getMessage("employee"), getMessage("designation"),
				getMessage("branch"), getMessage("division") };
		String[] headerWidth = { "10", "30", "20", "20", "20" };

		*//**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 *//*

		String[] fieldNames = { "empTokenNo", "empName", "desigName",
				"centerName", "divisionName", "empID", "centerID", "desigID", "divisionId" };

		*//**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 *//*
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		*//**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 *//*
		String submitFlag = "false";

		*//**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 *//*
		String submitToMethod = "";

		*//**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 *//*
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9empaction method
*/	
	public String f9division() {
		try {
			String query = " SELECT NVL(DIV_NAME, ' '), DIV_ID FROM HRMS_DIVISION"
				+ " WHERE IS_ACTIVE = 'Y'";
			
			String[] headers = { getMessage("division") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "divisionName", "divisionId" };
		
			int[] columnIndex = { 0, 1};
		
			String submitFlag = "false";
		
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9designation() {
		try {
			String query = " SELECT NVL(HRMS_RANK.RANK_NAME,' '), RANK_ID FROM HRMS_RANK"
				+ " WHERE IS_ACTIVE = 'Y'";
			
			String[] headers = { getMessage("designation") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "desigName", "desigId" };
			
			int[] columnIndex = { 0, 1};
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9branch() {
		try {
			String query = " SELECT NVL(CENTER_NAME,' '), CENTER_ID FROM HRMS_CENTER"
				+ " WHERE IS_ACTIVE = 'Y'";
			
			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "centerName", "centerID" };
			
			int[] columnIndex = { 0, 1};
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String generateReport(){
		try {
			InvestmentDeclarationReportModel model = new InvestmentDeclarationReportModel();
			model.initiate(context, session);
			model.generateReport(investmentBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	public String generateHouseRentReport(){
		try {
			InvestmentDeclarationReportModel model = new InvestmentDeclarationReportModel();
			model.initiate(context, session);
			model.generateHouseRentReport(investmentBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

}
