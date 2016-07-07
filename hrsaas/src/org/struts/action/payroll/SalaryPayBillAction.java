package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalaryPayBill;
import org.paradyne.model.payroll.SalPageWiseModel;
import org.struts.lib.ParaActionSupport;


public class SalaryPayBillAction extends ParaActionSupport{
	SalaryPayBill salPayBill;

	public Object getModel() {
		// TODO Auto-generated method stub
		return salPayBill;
	}
	
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		salPayBill.setRadioChk("department");
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalaryPayBillAction.class);
	public void prepare_local() throws Exception {
		salPayBill=new SalaryPayBill();
		salPayBill.setMenuCode(258);
		
	}
	public SalaryPayBill getsalPayBill() {
		return salPayBill;
	}
	public void setsalPayBill(SalaryPayBill salPayBill) {
		this.salPayBill = salPayBill;
	}
	
	
	public String getpageWiseSal()throws Exception
	{
		
		SalPageWiseModel model = new SalPageWiseModel(); 
		model.initiate(context, session);
		if(salPayBill.getPaybillFor().equals("AR")){
			model.genArrearsReport(salPayBill,response,salPayBill.getRowNumber(),salPayBill.getColumnNumber());
		}else{
			model.genReport(salPayBill,response,salPayBill.getRowNumber(),salPayBill.getColumnNumber());
		}
		model.terminate();
		return null;
	}
		
	/*
	 * Following function is called in jsp page to show the division code and name
	 */
	public String f9div() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT TO_CHAR(DIV_NAME),DIV_ID FROM  HRMS_DIVISION "	;	  			 
		       	     
			if(salPayBill.getUserProfileDivision() !=null && salPayBill.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+salPayBill.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		String[] headers={getMessage("division")};
		
		String[] headerWidth={"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */	
		
		String[] fieldNames={"divName","divCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 */
		 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		*/
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 */ 
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
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
