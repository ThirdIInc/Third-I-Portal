package org.struts.action.payroll;
import org.paradyne.bean.payroll.TDSTaxChallan;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.payroll.TDSTaxChallanModel;

public class TDSTaxChallanAction extends ParaActionSupport {
	
	TDSTaxChallan tdsTaxChallan;

	public TDSTaxChallan getForm5() {
		return tdsTaxChallan;
	}

	public void setForm5(TDSTaxChallan tdsTaxChallan) {
		this.tdsTaxChallan = tdsTaxChallan;
	}
	
	public Object getModel() {

			return tdsTaxChallan;
	}
	 
	 
	 static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		 tdsTaxChallan = new TDSTaxChallan();
		 tdsTaxChallan.setMenuCode(2067);
			
		}
	 public String report()
		{
		 TDSTaxChallanModel model=new TDSTaxChallanModel();
		model.initiate(context, session);
		model.generateReport(tdsTaxChallan, response);
		model.terminate();
		return null;
		}
		
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

			String[] fieldNames = { "typeName",
					"typeId" };

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
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT ALONG WITH PROFILES
			 */
			String query = " SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
							+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
			
			query +=getprofilePaybillQuery(tdsTaxChallan);
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("billno") ,getMessage("pay.bill")};

			/**
			 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
			 */
			String[] headerWidth = { "20","80" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = { "payId","payName"};

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
			
			logger.info("4");
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
			String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
							
			
			
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("department.code"),getMessage("department") };

			/**
			 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
			 */
			String[] headerWidth = { "20","80" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = { "deptId","deptName"};

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
			String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
							
			
			
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("branch.code"),getMessage("branch") };

			/**
			 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
			 */
			String[] headerWidth = { "20","80" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = { "brnId","brnName"};

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
			
			logger.info("4");
			return "f9page";
		}
		/*
		 * Following function is called to show the division name and division id in the jsp 
		 */
		public String f9div() throws Exception {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT ALONG WITH PROFILES
			 */
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
			
			if(tdsTaxChallan.getUserProfileDivision() !=null && tdsTaxChallan.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+tdsTaxChallan.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
		 	
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("division.code"),getMessage("division") };

			/**
			 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
			 */
			String[] headerWidth = { "20","80" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = { "divId","divName"};

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
			
			logger.info("4");
			return "f9page";
		}
	 
	 
}
