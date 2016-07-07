/**
 * 
 */
package org.struts.action.employeeSurvey;


import org.paradyne.bean.employeeSurvey.EmployeeSurveySummary;

import org.paradyne.model.employeeSurvey.EmployeeSurveySummaryModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0431 Lakkichand_Golegaonkar
 * @Date 20 Oct 2010
 *
 */
public class EmployeeSurveySummaryAction extends ParaActionSupport {

	
	EmployeeSurveySummary empSurveySummary=null;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		empSurveySummary=new EmployeeSurveySummary();
		empSurveySummary.setMenuCode(1087);
		
	}

	public Object getModel() {
		
		return empSurveySummary;
	}
	

	
	
	
	public String report()
	{
		try {
			EmployeeSurveySummaryModel model = new EmployeeSurveySummaryModel();
			model.initiate(context, session);
			model.generateReport(response, empSurveySummary);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null ;
	}
	
	public String reset()
	{
		try {
			empSurveySummary.setSurveyCode("");
			empSurveySummary.setSurveyName("");
			empSurveySummary.setSectionCode("");
			empSurveySummary.setSectionName("");
		
			empSurveySummary.setReportType("0");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS ;
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * @return String
	 * @throws Exception
	 */
	public String f9section() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT SECTION_CODE,NVL(SECTION_NAME,' ')FROM "
						+" HRMS_EMPSURVEY_MASTERDTL WHERE SURVEY_CODE="+empSurveySummary.getSurveyCode()+" "
						+" ORDER BY SECTION_CODE ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { "Section Code", "Section Name" };

		//DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//
		
		
		String[] fieldNames = { "sectionCode", "sectionName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		//NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "false";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9center	
	
	
	
	
	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * @return String
	 * @throws Exception
	 */
	public String f9survey() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT SURVEY_CODE,NVL(SURVEY_NAME,'') FROM HRMS_EMPSURVEY_MASTER ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { "Survey Code", "Survey Name" };

		//DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//
		

		String[] fieldNames = { "surveyCode", "surveyName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		//NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "false";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9center	
	
	


	public EmployeeSurveySummary getEmpSurveySummary() {
		return empSurveySummary;
	}

	public void setEmpSurveySummary(EmployeeSurveySummary empSurveySummary) {
		this.empSurveySummary = empSurveySummary;
	}

	public String f9division() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_NAME,DIV_ID FROM  HRMS_DIVISION   ORDER BY  DIV_ID ";

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
		String[] fieldNames = { "divisionName", "divisionId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9branch() throws Exception {
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

		String[] fieldNames = { "branchName", "branchId" };

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

	public String f9department() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "departmentName", "departmentId" };

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


	

}
