/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.Form10PF;
import org.paradyne.model.payroll.Form10PFModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author reebaj
 * 
 */
public class Form10PFAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	Form10PF form10;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		form10 = new Form10PF();
		form10.setMenuCode(715);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return this.form10;
	}

	public Form10PF getForm10() {
		return form10;
	}

	public void setForm10(Form10PF form10) {
		this.form10 = form10;
	}

	/**
	 * To generate PF form 5 report.
	 * @return String
	 */
	public String form10Report() {
		Form10PFModel model = new Form10PFModel();
		String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
		model.initiate(context, session);
		model.getForm10Report(form10, request, response, logoPath, "");
		model.terminate();
		return null;
	}

	/**
	 * Filter for division 
	 * @return f9page (String)
	 * @throws Exception
	 */
	public String f9actionDiv() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION    ";
		
		if(form10.getUserProfileDivision() !=null && form10.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+form10.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "division" };

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
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Reset the fields of the form
	 * 
	 * @return String
	 */
	public String reset() {
		form10.setMonth("");
		form10.setYear("");
		form10.setDivision("");
		return SUCCESS;

	}

	public final String mailReport(){
		try {
			Form10PFModel model = new Form10PFModel();
			model.initiate(context, session);
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getForm10Report(form10, request, response, logoPath, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	
}
