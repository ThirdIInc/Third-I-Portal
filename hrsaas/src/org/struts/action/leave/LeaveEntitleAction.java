/**
 * 
 */
package org.struts.action.leave;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.leave.LeaveEntitle;
import org.paradyne.model.leave.LeaveEntitleModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Lakkichand_Golegaonkar
 * 
 * 
 * 
 */
public class LeaveEntitleAction extends ParaActionSupport {

	LeaveEntitle entitle;


	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		entitle = new LeaveEntitle();
		// TODO Auto-generated method stub
		entitle.setMenuCode(302);

	}

	public void prepare_withLoginProfileDetails() throws Exception {

		LeaveEntitleModel model = new LeaveEntitleModel();
		model.initiate(context, session);
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		String sysDate = formater.format(date);
		entitle.setEntitleDate(sysDate);
		model.terminate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return entitle;
	}

	/**
	 * @return the entitle
	 */
	public LeaveEntitle getEntitle() {
		return entitle;
	}

	/**
	 * @param entitle
	 *            the entitle to set
	 */
	public void setEntitle(LeaveEntitle entitle) {
		this.entitle = entitle;
	}

	/**
	 * THIS METHOD IS USED FOR LEAVE ENTITLE PROCESS
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String process() throws Exception {
		LeaveEntitleModel model = new LeaveEntitleModel();
		model.initiate(context, session);
		// String res=model.validSelection(entitle);

		String leavePolicyCode = " SELECT LEAVE_POLICY_CODE  FROM HRMS_LEAVE_POLICY_SETTING  "
				+ "WHERE EMP_DIVISION=" + entitle.getDivisionCode();

		Object leavePolicyCodeObj[][] = model.getSqlModel().getSingleResult(
				leavePolicyCode);

		if (leavePolicyCodeObj.length == 0) {
				
			addActionMessage("Please define leave policy setting for "+entitle.getDivisionName()+" division");
		}
		else
		{
		String result = model.checkProcess(entitle);
		addActionMessage(result);
		}
		model.terminate();
		return SUCCESS;
	}// end of process

	public String clear() {
		entitle.setMonth("");
		entitle.setYear("");
		entitle.setDivisionCode("");
		entitle.setDivisionName("");
		return SUCCESS;
	}

	/**
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "TYPE NAME" };

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

		String[] fieldNames = { "entitle.empTypeName", "entitle.empTypeCode" };

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
	}// end of f9type

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divisionaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
			 
		if(entitle.getUserProfileDivision() !=null && entitle.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+entitle.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionCode", "divisionName" };

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

}// end of class
