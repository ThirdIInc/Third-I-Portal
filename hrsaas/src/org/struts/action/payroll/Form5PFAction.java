/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.Form5PF;
import org.paradyne.model.payroll.Form5PFModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author reebaj
 * 
 */
public class Form5PFAction extends ParaActionSupport {

	/**
	 * 
	 */
	Form5PF form5;

	public Form5PFAction() {
		// TODO Auto-generated constructor stub
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		form5 = new Form5PF();
		form5.setMenuCode(714);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.form5;
	}

	public Form5PF getForm5() {
		return form5;
	}

	public void setForm5(Form5PF form5) {
		this.form5 = form5;
	}

	/**
	 * To generate PF form 5 report
	 * @return String
	 */
	public String form5Report() {
		Form5PFModel model = new Form5PFModel();
		model.initiate(context, session);
		String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
		model.getForm5Report(form5, request, response,logoPath,"");
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
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		
		if(form5.getUserProfileDivision() !=null && form5.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+form5.getUserProfileDivision() +")"; 
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
		form5.setMonth("");
		form5.setYear("");
		form5.setDivision("");
		return SUCCESS;

	}
	
	public final String mailReport(){
		try {
			Form5PFModel model = new Form5PFModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getForm5Report(form5, request, response, logoPath, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
