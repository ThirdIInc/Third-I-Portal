package org.struts.action.payroll.pension;

import org.paradyne.bean.payroll.pension.ReleaseOnholdPension;
import org.paradyne.model.payroll.pension.ReleaseOnholdPensionModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author REEBA_JOSEPH
 * 25 OCTOBER 2010
 *
 */

public class ReleaseOnholdPensionAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReleaseOnholdPensionAction.class);
	
	ReleaseOnholdPension onholdPension =null;

	public ReleaseOnholdPension getOnholdPension() {
		return onholdPension;
	}

	public void setOnholdPension(ReleaseOnholdPension onholdPension) {
		this.onholdPension = onholdPension;
	}

	@Override
	public void prepare_local() throws Exception {
		onholdPension = new ReleaseOnholdPension();
		onholdPension.setMenuCode(1099);

	}

	public Object getModel() {
		return onholdPension;
	}
	
	/**
	 * SEARCH WINDOW FOR DIVISION
	 * @return
	 * @throws Exception
	 */
	public String f9Div() throws Exception {
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
		if(onholdPension.getUserProfileDivision() !=null && onholdPension.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+onholdPension.getUserProfileDivision() +")"; 
		query+= " ORDER BY UPPER(DIV_NAME) ";
	 
		String[] headers = {getMessage("division")};

		String[] headerWidth = {"100"};

		String[] fieldNames = {"divName", "divCode"};

		int[] columnIndex = {0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}//END OF METHOD
	
	/**
	 * DISPLAY LIST OF PENSIONS UNDER ONHOLD
	 * @return STRING
	 */
	public String viewOnholdEmployees() {
		onholdPension.setViewEmp("true");
		ReleaseOnholdPensionModel model = new ReleaseOnholdPensionModel();
		model.initiate(context, session);
		model.viewOnholdEmployees(onholdPension, request);
		model.terminate();
		return SUCCESS;
	}//END OF METHOD VIEWONHOLDEMPLOYEES
	
	/**
	 * Reset the form fields
	 * @return String
	 */
	public String reset() {
		onholdPension.setMonth("");
		onholdPension.setYear("");
		onholdPension.setDivCode("");
		onholdPension.setDivName("");
		onholdPension.setEmpList(null);
		onholdPension.setViewEmp("false");
		return SUCCESS;
	}// END OF RESET
	
	/**
	 * SAVE RELEASED PENSIONS
	 * @return STRING
	 * @throws Exception
	 */
	public String save() throws Exception {
		ReleaseOnholdPensionModel model = new ReleaseOnholdPensionModel();
		model.initiate(context, session);
		boolean result = model.updatePension(onholdPension, request);
		if(result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("save.error"));
		model.terminate();
		reset();
		return SUCCESS;
	}//END OF METHOD SAVE

}
