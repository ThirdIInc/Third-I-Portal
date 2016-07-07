package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalarySummary;
import org.paradyne.model.payroll.SalarySummaryModel;
import org.struts.lib.ParaActionSupport;


public class SalarySummaryAction extends ParaActionSupport {

	SalarySummary salSummary;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalarySummaryAction.class); 
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		salSummary = new SalarySummary();
		salSummary.setMenuCode(333);

	}

	public SalarySummary getSalSummary() {
		return salSummary;
	}


	public void setSalSummary(SalarySummary salSummary) {
		this.salSummary = salSummary;
	}



	public Object getModel() {
		// TODO Auto-generated method stub
		return salSummary;
	}
	public String getReport(){
		
		logger.info("branch flag=="+salSummary.getBranchFlag());
		logger.info("DeptFlag=="+salSummary.getDeptFlag());
		SalarySummaryModel model = new SalarySummaryModel(); 
		model.initiate(context, session);
		model.getReport(salSummary,response);
		model.terminate();
		return null;
	}
	public String getBranchWiseSal()throws Exception
	{
		
		SalarySummaryModel model = new SalarySummaryModel(); 
		model.initiate(context, session);
		//model.generateReport(salSummary,response);
		//model.genReport(brnWiseSal,response,brnWiseSal.getRowNumber(),brnWiseSal.getColumnNumber());
		model.terminate();
		return null;
	}
	public String f9Dept(){
		String query ="SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER (DEPT_NAME)";
		/*
		 * SET THE HEADER NAME
		 * 
		 */
		String header =getMessage("department");
		/*
		 * textField id where to display the selected fields
		 * 
		 */
		String textAreaId ="paraFrm_deptName";
				
		/*
		 * hidden Field ID (paraFrm_deptCode in this case )
		 * 
		 */
		String hiddenFieldId ="paraFrm_deptCode";
		
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag ="";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	/*
	 * Following function is called in jsp page to show the division code and name
	 
	
	/*
	public String f9type() throws Exception {
		
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE order by upper(type_name)";

		
		String[] headers = { getMessage("type.name")  };

		
		String[] headerWidth = { "100" };

		

		String[] fieldNames = { "typeName","typeCode" };

		
		int[] columnIndex = { 0, 1 };

		
		String submitFlag = "false";

		
		String submitToMethod = "";

		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	*/
	public String f9Desg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		/*
		 * SET THE HEADER NAME
		 * 
		 */
		String header =getMessage("designation");
		/*
		 * textField id where to display the selected fields
		 * 
		 */
		String textAreaId ="paraFrm_desgName";
		
		/*
		 * hidden Field ID (paraFrm_desgCode in this case )
		 * 
		 */
		
		String hiddenFieldId ="paraFrm_desgCode";
		
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag ="false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	/*public String f9desg() throws Exception {
		
		String query = " SELECT RANK_NAME , RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";

		String[] headers = { getMessage("designation")  };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "desgName","desgCode" };

		
		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}*/
	
	/*
	 * Following function is called in the jsp page to show the department id and name
	 */
	/*public String f9dept() throws Exception {
		
		String query = " SELECT DISTINCT NVL(DEPT_NAME,' '),DEPT_ID FROM HRMS_DEPT ORDER BY upper(NVL(DEPT_NAME,' '))";
		
		String[] headers = {getMessage("department") };

		String[] headerWidth = { "20","80" };
		String[] fieldNames = { "deptName","deptCode"};

		int[] columnIndex = { 0 ,1};

		String submitFlag = "false";

		String submitToMethod = "";

		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	*/
	/*
	 * Following function is called to generate the branch id and branch name in the jsp page
	 */
	
	public String f9Branch(){
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT CENTER_ID, NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY Upper(NVL(CENTER_NAME,' '))";
		
		/*
		 * SET THE HEADER NAME
		 * 
		 */
		String header =getMessage("branch");
		/*
		 * textField id where to display the selected fields
		 * 
		 */
		
		String textAreaId ="paraFrm_brnName";
		
		/*
		 * hidden Field ID (paraFrm_brnCode in this case )
		 * 
		 */
		String hiddenFieldId ="paraFrm_brnCode";
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag ="false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	/*public String f9brn() throws Exception {
		
		String query = " SELECT DISTINCT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER ORDER BY Upper(NVL(CENTER_NAME,' '))";
						
		
		String[] headers = {getMessage("branch") };

		String[] headerWidth = { "80" };


		String[] fieldNames = {"brnName", "brnCode"};

		int[] columnIndex = { 0 ,1};

		
		String submitFlag = "false";

		
		String submitToMethod = "";

		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}*/
	/*
	 * Following function is called to show the division name and division id in the jsp 
	 */
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT NVL(DIV_NAME,' '),div_id FROM HRMS_DIVISION ";
			
			if(salSummary.getUserProfileDivision() !=null && salSummary.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+salSummary.getUserProfileDivision() +")"; 
				query+= "  ORDER BY Upper(NVL(DIV_NAME,' '))";
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName","divCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1};

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
	public String f9paybill(){
		try {
			String query = " SELECT DISTINCT NVL(HRMS_PAYBILL.PAYBILL_GROUP,' '),HRMS_PAYBILL.PAYBILL_ID FROM HRMS_PAYBILL "
				+ " ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String[] headers = { getMessage("pay.bill")};
			String[] headerWidth = { "100" };
			String[] fieldNames = {  "paybillName","paybillId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
}
