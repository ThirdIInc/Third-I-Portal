package org.struts.action.payroll;

import org.paradyne.bean.payroll.BranchWiseSalReport;
import org.paradyne.model.payroll.BranchWiseSalReportModel;
import org.struts.lib.ParaActionSupport;

public class BranchWiseSalReportAction extends ParaActionSupport {

	BranchWiseSalReport brnWiseSal;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		brnWiseSal = new BranchWiseSalReport();
		brnWiseSal.setMenuCode(891);

	}

	public BranchWiseSalReport getBrnWiseSal() {
		return brnWiseSal;
	}

	public void setBrnWiseSal(BranchWiseSalReport brnWiseSal) {
		this.brnWiseSal = brnWiseSal;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return brnWiseSal;
	}

	public String getBranchWiseSal()throws Exception
	{
		
		BranchWiseSalReportModel model = new BranchWiseSalReportModel(); 
		model.initiate(context, session);
		model.genReport(brnWiseSal,response,brnWiseSal.getRowNumber(),brnWiseSal.getColumnNumber());
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
		String query = " SELECT DISTINCT DIV_ID,TO_CHAR(DIV_NAME),NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,'  '), NVL(LOCATION_NAME,' ')||' '|| DIV_PINCODE FROM  HRMS_DIVISION "		  			 
		       	      	+" LEFT JOIN HRMS_LOCATION ON ( HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE )";
						
		       	     if(brnWiseSal.getUserProfileDivision() !=null && brnWiseSal.getUserProfileDivision().length()>0)
		     			query+= " WHERE DIV_ID IN ("+brnWiseSal.getUserProfileDivision() +")"; 
		     			query+= " ORDER BY  DIV_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		String[] headers={getMessage("division.code"),getMessage("division")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */	
		
		String[] fieldNames={"brnWiseSal.divCode","brnWiseSal.divName","brnWiseSal.divAdd","brnWiseSal.divCity"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 */
		 
		int[] columnIndex={0,1,2,3};
		
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
	
}
