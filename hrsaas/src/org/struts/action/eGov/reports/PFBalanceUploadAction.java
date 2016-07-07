package org.struts.action.egov.reports;

import org.paradyne.bean.eGov.reports.PFBalanceUploadBean;
import org.paradyne.model.eGov.reports.PFBalanceUploadModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class PFBalanceUploadAction extends ParaActionSupport {

	/**
	 * Bean Instance created.
	 */
	private PFBalanceUploadBean pfbalance;
	
	/** (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		this.pfbalance = new PFBalanceUploadBean();
		this.pfbalance.setMenuCode(2101);

	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return Object.
	 */

	public Object getModel() {
		return this.pfbalance;
	}

	/**
	 * @return the pfbalance
	 */
	public PFBalanceUploadBean getPfbalance() {
		return this.pfbalance;
	}

	/**
	 * @param pfbalance the pfbalance to set
	 */
	public void setPfbalance(final PFBalanceUploadBean pfbalance) {
		this.pfbalance = pfbalance;
	}

	/**
	 * purpose - method is called when Download Excel Sheet button is pressed.
	 * @return String
	 */
	public String uploadReport(){
		PFBalanceUploadModel model = new PFBalanceUploadModel();
		model.initiate(context, session);
		model.generateReport(pfbalance,response);
		model.terminate();
		return null;
	}
	
	
	
	
	/**
	 * purpose - This method Sets the Division.  
	 * @return String.
	 * @throws Exception no action map or no such method exception.
	 */
	public String f9division() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		final String query = " SELECT DISTINCT DIV_ID,TO_CHAR(DIV_NAME) FROM  HRMS_DIVISION ";	  			 
			/*if(licreportBean.getUserProfileDivision() !=null && licreportBean.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+licreportBean.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";*/
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		final String[] headers = {"Division Id", this.getMessage("division.name")};
		
		final String[] headerWidth = {"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */	
		
		final String[] fieldNames = {"divId", "divisionName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 */
		 
		final int[] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		
		final String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		*/
		final String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 */ 
		
		
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * purpose - This method Sets the Pay Bill. 
	 * @return String.
	 * @throws Exception action not map or no such method exception.
	 */
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		final String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL order by PAYBILL_ID asc";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {"Pay Bill Id", this.getMessage("paybill.name")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"30", "70"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"paybillId", "payBill"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
	
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	
	
	
}
