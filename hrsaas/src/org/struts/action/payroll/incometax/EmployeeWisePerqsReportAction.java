package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.EmployeeWisePerqsReport;
import org.paradyne.model.payroll.incometax.EmployeeWisePerqsReportModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeWisePerqsReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeWisePerqsReportAction.class);

	EmployeeWisePerqsReport bean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new EmployeeWisePerqsReport();
		bean.setMenuCode(5040);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public EmployeeWisePerqsReport getForm6a() {
		return bean;
	}

	public void setForm6a(EmployeeWisePerqsReport bean) {
		this.bean = bean;
	}

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		EmployeeWisePerqsReportModel model = new EmployeeWisePerqsReportModel();
		model.initiate(context, session);
		String reportPath = "";
		
		model.generateReport(bean, request, response, reportPath);
		model.terminate();
		return null;
	}// end of report
	
	/**
	 * THIS METHOD IS USED FOR RESETTING FIELDS
	 * 
	 * @return
	 */
	public String reset() throws Exception {
		bean.setBrnName("");
		bean.setDivName("");
		bean.setDeptName("");
		bean.setTypeName("");
		bean.setFromYear("");
		bean.setToYear("");
		bean.setTaxableFlag("");
		prepare_withLoginProfileDetails();
		return SUCCESS;
	}// end of reSET

	
	public void prepare_withLoginProfileDetails() throws Exception {
		EmployeeWisePerqsReportModel model = new EmployeeWisePerqsReportModel();
		model.initiate(context, session);
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		}
		
		double remianMonth=0;
		double month =  Double.parseDouble(split[1]);
		if(month>3 && month<=12){
			remianMonth = 15 - month;
		}//end of if
		else if(month<=3){
			remianMonth = 3 - month;
		}//end of else if
		model.terminate();
		
		bean.setFromYear(String.valueOf(year));
		bean.setToYear(String.valueOf(year + 1));
	}
	
	
	
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("employee.type") };

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

		String[] fieldNames = { "typeName", "typeId" };

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
	}// end of f9type

	/**
	 * FOLLOWING FUNCTION IS CALLED IN THE JSP PAGE TO SHOW THE DEPARTMENT ID
	 * AND NAME
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("department.code"),getMessage("department") };

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

		String[] fieldNames = { "deptId", "deptName" };

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
	}// end of f9dept

	/**
	 * FOLLOWING FUNCTION IS CALLED TO GENERATE THE BRANCH ID AND BRANCH NAME IN
	 * THE JSP PAGE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9brn() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("branch.code"),getMessage("branch") };

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

		String[] fieldNames = { "brnId", "brnName" };

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
	}// end of f9brn

	/**
	 * FOLLOWING FUNCTION IS CALLED TO SHOW THE DIVISION NAME AND DIVISION ID IN
	 * THE JSP
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION  ";
		
		
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
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

		String[] fieldNames = { "divId", "divName" };

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

	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String[][] type = new String[][]{{"PDF"},{"Excel"},{"Doc"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true"; 
			String submitToMethod = "EmployeeWisePerqsReport_mailReport.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	public final String mailReport(){
		try {
			EmployeeWisePerqsReportModel model = new EmployeeWisePerqsReportModel();
			model.initiate(context, session);
			
			
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.generateReport(bean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	
}// end of class
