package org.struts.action.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.paradyne.bean.payroll.NonIndustrialSalary;
import org.paradyne.model.ot.OtViewModel;
import org.paradyne.model.payroll.NonIndustrialSalaryAuditModel;
 import org.struts.lib.ParaActionSupport;

public class NonIndustrialSalaryAuditAction extends ParaActionSupport {	 

	private NonIndustrialSalary nonIndustrialSalary;
	
	 public Object getModel() {
		return this.nonIndustrialSalary;
	}

	public String execute() throws Exception {		 
		return "success";
	}

	 
	 
	public NonIndustrialSalary getNonIndustrialSalary() {
		return nonIndustrialSalary;
	}

	public void setNonIndustrialSalary(NonIndustrialSalary nonIndustrialSalary) {
		this.nonIndustrialSalary = nonIndustrialSalary;
	}

	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		nonIndustrialSalary=new NonIndustrialSalary();
		nonIndustrialSalary.setMenuCode(201);	
	}

	// viewSalary
	
	
	public String viewNonIndustrialSalaries() throws Exception {
		try {
			NonIndustrialSalaryAuditModel model = new NonIndustrialSalaryAuditModel();
			model.initiate(context,session);
			logger.info("get salary called");
			Object rows[][]=model.viewSalary(nonIndustrialSalary);	
			Object d[][]=model.getDebitHeader();
			Object c[][]=model.getCreditHeader();
			request.setAttribute("debitLength", d);
			request.setAttribute("creditLength", c);
			request.setAttribute("rows", rows);
			model.terminate();
			if(!(rows != null && rows.length>0)){
				addActionMessage("There is no data to display");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	
	
	
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_ID IN (2,3,5) ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "EMPLOYEE TYPE" };

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
		String[] headers = { "PAY BILL NAME","PAY BILL NO" };

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

		String[] fieldNames = { "nonIndustrialSalary.payBillNo","nonIndustrialSalary.payBillNo"};

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
