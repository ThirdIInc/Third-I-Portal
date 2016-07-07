package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.StmtTaxCalculation;
import org.paradyne.model.payroll.incometax.StmtTaxCalulationModel;
import org.struts.lib.ParaActionSupport;

public class StmtTaxCalculationAction extends ParaActionSupport {

	StmtTaxCalculation stmt;
	public void prepare_local() throws Exception {
		stmt=new StmtTaxCalculation();
		stmt.setMenuCode(688);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return stmt;
	}
	
	/**
	 * following function is called when a general user makes login
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String[]split =  sysdate.split("/");
		int year = Integer.parseInt(String.valueOf(split[2]));
		stmt.setFromYear(String.valueOf(year));
		stmt.setToYear(String.valueOf(year + 1));
	}

	
	public String taxReport()
	{
		StmtTaxCalulationModel model =new StmtTaxCalulationModel();
		model.initiate(context,session);
		
		try {
			System.out.println("hello");
			model.getReport(stmt, response);
			
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();		
		return null;
	}
	
	
	
	
	public String f9employee() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_ID "
				           +" FROM HRMS_EMP_OFFC   ORDER BY EMP_ID ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("employee.id"), getMessage("employee")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"empTokenNo","empName", "empId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		 
		   
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	public StmtTaxCalculation getStmt() {
		return stmt;
	}

	public void setStmt(StmtTaxCalculation stmt) {
		this.stmt = stmt;
	}
}
