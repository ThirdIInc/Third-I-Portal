/**
 * 
 */
package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.DebitRecoveryReport;
import org.paradyne.model.payroll.incometax.PFReconciliationReportModel;
import org.paradyne.model.payroll.salary.DebitRecoveryReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class DebitRecoveryReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	DebitRecoveryReport debRecRep;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		
		debRecRep = new DebitRecoveryReport();
		debRecRep.setMenuCode(556);
		
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return debRecRep;
	}

	public DebitRecoveryReport getDebRecRep() {
		return debRecRep;
	}

	public void setDebRecRep(DebitRecoveryReport debRecRep) {
		this.debRecRep = debRecRep;
	}
	/**
	 * Following function is called to generate the report when Report button is clicked in the jsp page
	 * @return
	 */
	public String report(){
		DebitRecoveryReportModel model=new DebitRecoveryReportModel();
		model.initiate(context,session);
		String reportPath = "";
		model.generateReport(debRecRep,request, response, reportPath);
		model.terminate();
		return null;
	}
	
	public String reset(){
		debRecRep.setBranchCode("");
		debRecRep.setBranchName("");
		debRecRep.setMonth("0");
		debRecRep.setDebitCode("");
		debRecRep.setDebitHead("");
		debRecRep.setOnHold("A");
		debRecRep.setReportType("0");
		return SUCCESS;
	}
	 public final String f9reportType() {
			try {
				String[][] type = new String[][]{{"PDF"}};
				String[] headers = {getMessage("report.type")};
				String[] headerWidth = {"100"};
				String[] fieldNames = {"reportType"};
				int[] columnIndex = {0};
				String submitFlag = "true";
				String submitToMethod = "DebitRecoveryReport_mailReport.action";
				setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "f9page";
		}
	
	public final String mailReport(){
		try {
			DebitRecoveryReportModel model = new DebitRecoveryReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.generateReport(debRecRep,request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/**
	 * Following function is called when debit head is selected from the pop up window
	 * @return
	 * @throws Exception
	 */
	public String f9debitHead() throws Exception {
		
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
	logger.info("Inside-->report()");
		String query = " SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		
		String[] headers = {getMessage("debit.code"),getMessage("debit.name")};

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 
		String[] headerWidth = {"30", "30"};

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		 //

		String[] fieldNames = { "debitCode","debitHead" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		  
		 
		int[] columnIndex = {0,1};

		
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
	} 
	/**
	 * Following function is called when debit head is selected from the pop up window
	 * @return
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY  CENTER_ID";
		String[] headers = {"Branch Code","Branch Name"};
	 	String[] headerWidth = {"30", "30"};
	 	String[] fieldNames = { "branchCode","branchName" };
	 	int[] columnIndex = {0,1};
		String submitFlag = "false"; 
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
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
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '),NVL(DIV_ABBR,' ') FROM HRMS_DIVISION ";
		
		if(debRecRep.getUserProfileDivision() !=null && debRecRep.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+debRecRep.getUserProfileDivision() +")"; 
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

		String[] fieldNames = { "divId","divName","divisionAbbrevation"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1,2};

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
	
	/**
	 * Method to select the Pay bill group.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9payBillaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
				+ " ORDER BY  PAYBILL_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Pay Bill No", "Pay Bill" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payBillId", "payBillName" };

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
