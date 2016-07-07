package org.struts.action.payroll;

import java.io.PrintWriter;
import java.util.ArrayList;


import org.paradyne.bean.payroll.NonIndustrialSalary;
import org.paradyne.model.payroll.CashProcessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Venkatesh
 */
public class CashProcessAction extends ParaActionSupport {	 

	private NonIndustrialSalary nonIndustrialSalary;
	
	 public Object getModel() {
		return this.nonIndustrialSalary;
	}

	
	public NonIndustrialSalary getNonIndustrialSalary() {
		return nonIndustrialSalary;
	}

	public void setNonIndustrialSalary(NonIndustrialSalary nonIndustrialSalary) {
		this.nonIndustrialSalary = nonIndustrialSalary;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		nonIndustrialSalary=new NonIndustrialSalary();	 
		nonIndustrialSalary.setMenuCode(558);	
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		CashProcessModel model = new CashProcessModel();
		model.initiate(context, session);
		model.getFilters(nonIndustrialSalary);
		model.terminate();
	}
	
	/**
	 * THIS METHOD RESETS THE CONTENTS ON JSP
	 */
	public String reset(){
		
		nonIndustrialSalary.setCashCode("");
		nonIndustrialSalary.setFrmDate("");
		nonIndustrialSalary.setToDate("");
		nonIndustrialSalary.setFrmDate("");
		nonIndustrialSalary.setDivisionCode("");
		nonIndustrialSalary.setDivisionName("");
		nonIndustrialSalary.setDeptCode("");
		nonIndustrialSalary.setDeptName("");
		nonIndustrialSalary.setBranchCode("");
		nonIndustrialSalary.setBranchName("");
		nonIndustrialSalary.setTypeName("");
		nonIndustrialSalary.setTypeCode("");
		nonIndustrialSalary.setPayBillNo("");
		nonIndustrialSalary.setPayBillName("");
		nonIndustrialSalary.setStatus("");
		return "success";
		
	}
		
	/* 					PROCESS
	 * FOLLOWING METHOD IS FOR GETTING THE CURRENT  credit OF EMPLOYEE 
	 * FROM HRMS_EMP_CREDITS AND HRMS_EMP_DEBIITS   
	 */
	public String getCashProcess() throws Exception {
		
			CashProcessModel model = new CashProcessModel();
			model.initiate(context,session);
			
			String cashStatus ="D";// model.checkCashStatus(nonIndustrialSalary);
			
			//IF CASH PROCESS ALREADY DONE FOR THE DATE RANGE ('S'-Save,'F'-Finalised)
			if(cashStatus.equals("S")||cashStatus.equals("F")){				
				
				addActionMessage("Cash process already done.");
				
			}else{
				
				    long daysDiff=model.dateDifferenceInDays(nonIndustrialSalary.getFrmDate(),nonIndustrialSalary.getToDate());
				    
					Object rows[][] = model.getSalary(nonIndustrialSalary,daysDiff,request);
					
					if(rows==null){
						
						addActionMessage("Salary has not been processed");
					}else{
						
						request.setAttribute("rows", rows);
						
					}
			}	
			model.terminate();
			
		return "success";
	}
	
	/**					SAVE
	 * 	THIS METHOD SAVES THE CASH PROCESS FOR  A PERIOD
	 * @return
	 * @throws Exception
	 */
	public String saveCashProcess() throws Exception {
		
			
			CashProcessModel model = new CashProcessModel();
			model.initiate(context,session);			
			Object c[][]=model.getCreditHeader();
		/*	////////////////////////////////////////////////////////////		
			ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();
			

			for (int i = 0; i < c.length; i++) {
				
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
				 * LOOP IS USED
				 * 
				 
				NonIndustrialSalary creditName = new NonIndustrialSalary();
				creditName.setCreditCode(String.valueOf(c[i][0]));
				creditName.setCreditName(String.valueOf(c[i][1]));
				creditNames.add(creditName);
			}

			nonIndustrialSalary.setCreditHeader(creditNames);
			/////////////////////////////////////////////////////////////////	
*/			
			String[] emp_id=request.getParameterValues("emp_id");//EMP ID
									
			Object [][] rows=new Object[emp_id.length][c.length]; 
						 			
			for(int i=0;i<emp_id.length;i++){
				
				// FOR GETTING CREDIT AND DEBIT FROM JSP				
				rows[i]=request.getParameterValues(String.valueOf(i));
				
			}
			
			String paybill = nonIndustrialSalary.getPayBillNo();
			String empType = nonIndustrialSalary.getTypeCode();
						
			boolean insert=false;
			boolean update=false;
			
			if(!nonIndustrialSalary.getStatus().equals("Finalised")){//IF PROCESS STATUS IS TO BE FINALISED
				
			
					if(nonIndustrialSalary.getCashCode().equals("")){//SAVE
						
						insert=model.save( rows,c,emp_id,nonIndustrialSalary);
						
						if(insert){	
							addActionMessage("Record saved successfully");
						}else{
							addActionMessage("Record cannot be saved");
						}
						
					}else{//UPDATE
						
						update=model.update( rows,c,emp_id,nonIndustrialSalary);
						
						if(update){	
							addActionMessage("Record updated successfully");
						}else{
							addActionMessage("Record cannot be updated");
						}
						
					}
					
			}else{//PROCESS IS ALREADY FINALISED
				
				addActionMessage("Process cant be saved as it has been finalised. ");
				
			}			
			
			model.terminate();
		//request.setAttribute("", arg1)
		reset();
		return "success";
	}
	
	/**				FINALISE
	 * THIS METHOD FINALISES THE CASH PROCESS AND ADDs BALANCE TO THE HRMS_CASH_BALANCE
	 * table
	 * @return
	 * @throws Exception
	 */
	public String finalBalance() throws Exception {
			
				CashProcessModel model = new CashProcessModel();
				model.initiate(context,session);
		
				Object c[][]=model.getCreditHeader();
				String[] emp_id=request.getParameterValues("emp_id");							
				Object [][] rows=new Object[emp_id.length][c.length]; 
								
				for(int i=0;i<emp_id.length;i++){					
					
					rows[i]=request.getParameterValues(String.valueOf(i));
					
				}
				
		
				if(!nonIndustrialSalary.getStatus().equals("Finalised")){//PROCESS NOT FINALISED
					
					boolean record= model.finalCash( rows,c,emp_id,nonIndustrialSalary);
					record=model.updateCashProcessStatus(nonIndustrialSalary);
					
					if(record){//IF STATUS UPADATED TO 'F'
						
						addActionMessage("Process finalised successfully.");
						
					}
					
				}else{//PROCESS FINALISED
					
					addActionMessage("Process already finalised.");
					
				}	
			
			model.terminate();
		reset();
		return "success";
	}

	
/**
 * THIS METHOD DISPLAYS THE EXISTING CASH PROCESS DETAILS FROM THE HRMS_CASH_HDR TABLE
 * @return
 */
public String getCashProcessDetails(){
	
	CashProcessModel model = new CashProcessModel();
	model.initiate(context, session); 
	model.setProcessFilters(nonIndustrialSalary);
	Object data[][]=model.getCashProcessDetails(nonIndustrialSalary,request);
	request.setAttribute("rows", data);
	model.terminate();		
	logger.info("TYPE CODE-"+nonIndustrialSalary.getPayBillName()+"-");
	
	if(nonIndustrialSalary.getDivisionName().trim().equals("")){
		nonIndustrialSalary.setDivisionFlag("false");
	}else{
		nonIndustrialSalary.setDivisionFlag("true");
	}if(nonIndustrialSalary.getDeptName().trim().equals("")){
		nonIndustrialSalary.setDepartmentFlag("false");
	}else{
		nonIndustrialSalary.setDepartmentFlag("true");
	}if(nonIndustrialSalary.getBranchName().trim().equals("")){
		nonIndustrialSalary.setBranchFlag("false");
	}else{
		nonIndustrialSalary.setBranchFlag("true");
	}if(nonIndustrialSalary.getTypeName().trim().equals("")){
		nonIndustrialSalary.setEmptypeFlag("false");
	}else{
		nonIndustrialSalary.setEmptypeFlag("true");
	}if(nonIndustrialSalary.getPayBillName().trim().equals("")){
		nonIndustrialSalary.setPaybillFlag("false");
	}else{
		nonIndustrialSalary.setPaybillFlag("true");
	}
	
	return "success";
}
	
public String f9cashProcess()throws Exception{
	
	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT ALONG WITH PROFILES
	 */
	String query = " SELECT TO_CHAR(CASH_FROMDATE,'DD-MM-YYYY'), TO_CHAR(CASH_TODATE,'DD-MM-YYYY'),NVL(DIV_NAME,' '), "
				  +" NVL(CENTER_NAME,' '), NVL(DEPT_NAME,' '), NVL(TYPE_NAME,' '), NVL(PAYBILL_GROUP,' '), DECODE(CASH_STATUS,'S','Pending','F','Finalized'),CASH_CODE"
				  +" ,CASH_DIVISION, CASH_BRANCH, CASH_DEPT, CASH_EMPTYPE, CASH_PAYBILL FROM HRMS_CASH_HDR"
			      +" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_CASH_HDR.CASH_DIVISION)"
			      +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_CASH_HDR.CASH_BRANCH)"
			      +" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CASH_HDR.CASH_DEPT)"
			      +" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_CASH_HDR.CASH_EMPTYPE)"
			      +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_CASH_HDR.CASH_PAYBILL)";
	
	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = {getMessage("frmdate"),getMessage("todate"),getMessage("division"),getMessage("branch"),getMessage("department"),getMessage("employee.type"),getMessage("pay.bill"),getMessage("status") };
	
	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "10","10","10","10","10","10","10","10" };
	
	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */
	
	String[] fieldNames = { 
							"nonIndustrialSalary.frmDate","nonIndustrialSalary.toDate",
							"nonIndustrialSalary.divisionName","nonIndustrialSalary.branchName",
							"nonIndustrialSalary.deptName","nonIndustrialSalary.typeName",
							"nonIndustrialSalary.payBillName","status","nonIndustrialSalary.cashCode",
							"nonIndustrialSalary.divisionCode","nonIndustrialSalary.branchCode",
							"nonIndustrialSalary.deptCode","nonIndustrialSalary.typeCode",
							"nonIndustrialSalary.payBillNo"
			 				
						  };
	
	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
	 * 
	 * NOTE: COLUMN NUMBERS STARTS WITH 0
	 * 
	 */
	int[] columnIndex = { 0, 1,2,3,4,5,6,7,8,9,10,11,12 };
	
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
	String submitToMethod = "CashProcess_getCashProcessDetails.action";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */
	logger.info("1");
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	logger.info("4");
	return "f9page";
	
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
		String[] headers = { getMessage(("employee.type"))};

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

		String[] fieldNames = { "nonIndustrialSalary.typeName",
				"nonIndustrialSalary.typeCode" };

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
	
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
						+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
		
		query +=getprofilePaybillQuery(nonIndustrialSalary);
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("pay.bill"),getMessage("billno") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80","20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "nonIndustrialSalary.payBillName","nonIndustrialSalary.payBillNo"};

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
	 
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalary.branchName", "nonIndustrialSalary.branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalary.deptName", "nonIndustrialSalary.deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			 
			if(nonIndustrialSalary.getUserProfileDivision() !=null && nonIndustrialSalary.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+nonIndustrialSalary.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME)  ";
				
		 
			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalary.divisionName", "nonIndustrialSalary.divisionCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/*public String f9ShowRecord()
	{
		try
		{   
			String query = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', " +
			" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " +
			" LEDGER_YEAR, TYPE_NAME, PAYBILL_GROUP, DEPT_NAME, CENTER_NAME, DIV_NAME, LEDGER_STATUS, LEDGER_CODE,LEDGER_MONTH " +
			" FROM HRMS_SALARY_LEDGER " +
			" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) " +
			" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) " +
			" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) " +
			" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " +
			" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) " +
			" WHERE LEDGER_STATUS='SAL_START' OR LEDGER_STATUS='SAL_FINAL'" +
			" ORDER BY ATTN_MONTH DESC, LEDGER_YEAR DESC ";

			String[] headers = {"MONTH", "YEAR", "EMPLOYEE TYPE", "PAYBILL", "DEPARTMENT", "BRANCH", "DIVISION", "STATUS"};

			String[] headerWidth = {"12", "12", "12", "12", "12", "12", "12", "12"};

			String[] fieldNames = {"nonIndustrialSalary.month", "nonIndustrialSalary.year", "nonIndustrialSalary.typeName", "nonIndustrialSalary.payBillName",
									"nonIndustrialSalary.deptName", "nonIndustrialSalary.branchName", "nonIndustrialSalary.divisionName", "status","attCode",
									"nonIndustrialSalary.month"};

			int[] columnIndex = {0, 1 ,2, 3, 4 ,5 ,6 ,7,8,9};

			String submitFlag = "true";

			String submitToMethod = "NonIndustrialSalary_filters.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}*/
	
/*	public String filters()
	{
		try
		{
			CashProcessModel model = new CashProcessModel();
			model.initiate(context, session);
			model.filters(nonIndustrialSalary);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
*/
	
}
