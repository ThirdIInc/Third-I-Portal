/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.FormA1CumReturn;
import org.paradyne.model.payroll.FormA1CumReturnModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * @modified ganesh 26 March 2012
 */
public class FormA1CumReturnAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FormA1CumReturnAction.class);
	FormA1CumReturn formA1CumReturn = null;
	/**
	 * @return the formA1CumReturn
	 */
	public FormA1CumReturn getFormA1CumReturn() {
		return formA1CumReturn;
	}

	/**
	 * @param formA1CumReturn the formA1CumReturn to set
	 */
	public void setFormA1CumReturn(FormA1CumReturn formA1CumReturn) {
		this.formA1CumReturn = formA1CumReturn;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		formA1CumReturn = new FormA1CumReturn();
		formA1CumReturn.setMenuCode(1056);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return formA1CumReturn;
	}
	
	
	public String reset() throws Exception {
		formA1CumReturn.setFromYear("");
		formA1CumReturn.setToYear("");
		formA1CumReturn.setDivisionId("");
		formA1CumReturn.setDivisionName("");
		return SUCCESS;
	}
	
	public String f9division() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT TO_CHAR(DIV_NAME), DIV_ID FROM  HRMS_DIVISION "	;	  			 
			if(formA1CumReturn.getUserProfileDivision() !=null && formA1CumReturn.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+formA1CumReturn.getUserProfileDivision() +")"; 
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
		
		String[] fieldNames={"divisionName", "divisionId"};
		
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
	
	public String getReport() {
		final FormA1CumReturnModel model = new FormA1CumReturnModel();
		model.initiate(context, session);
		
		String reportPath = "";
		
		model.getReport(formA1CumReturn, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			final FormA1CumReturnModel model = new FormA1CumReturnModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(formA1CumReturn, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
	

}
