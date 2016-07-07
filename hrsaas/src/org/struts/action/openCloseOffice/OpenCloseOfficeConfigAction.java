/**
 * 
 */
package org.struts.action.openCloseOffice;

import org.paradyne.bean.openCloseOffice.OpenCloseOfficeConfig;
import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.model.openCloseOffice.OpenCloseOfficeConfigModel;
import org.paradyne.model.settings.SuperSettingsModel;
import org.struts.action.common.LoginAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseOfficeConfigAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LoginAction.class);
	
	OpenCloseOfficeConfig openCloseConfig;
	
	public OpenCloseOfficeConfig getOpenCloseConfig() {
		return openCloseConfig;
	}

	public void setOpenCloseConfig(OpenCloseOfficeConfig openCloseConfig) {
		this.openCloseConfig = openCloseConfig;
	}

	public void prepare_local() throws Exception {
		openCloseConfig = new OpenCloseOfficeConfig();
		openCloseConfig.setMenuCode(1060);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return openCloseConfig;
	}
	
	/**
	 * Saving and updating  HrWorK Communication Settings
	 * 
	 * @return String
	 */

	public String add() {
		OpenCloseOfficeConfigModel model = new OpenCloseOfficeConfigModel();
		model.initiate(context, session);
		
		int value = model.add(openCloseConfig);
		reset();
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		model.terminate();
		return "success";
	}
	
	public String reset() {
		openCloseConfig.setEmpCode("");
		openCloseConfig.setEname("");
		openCloseConfig.setEmpToken("");
		return SUCCESS;
	}
	
	public String loadList(){
		OpenCloseOfficeConfigModel model = new OpenCloseOfficeConfigModel();
		model.initiate(context, session);
		
		model.loadList(openCloseConfig);
		
		model.terminate();
		return SUCCESS;
	} //end of loadList method
	
	public String delete() {
		OpenCloseOfficeConfigModel model = new OpenCloseOfficeConfigModel();
		model.initiate(context, session);
	
		boolean result = model.delete(openCloseConfig);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			reset();
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		model.terminate();
		return "success";
	} //end of delete method
	
	public String resetButton(){
		openCloseConfig.setEmpCode("");
		openCloseConfig.setEname("");
		openCloseConfig.setEmpToken("");
		openCloseConfig.setBranchId("");
		openCloseConfig.setBranchName("");
		openCloseConfig.setListNotnull(false);
		return SUCCESS;
	}

	
	
	
	
	public String f9branch() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER "
						+"ORDER BY CENTER_ID ASC ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "branchId", "branchName"};

		int[] columnIndex = { 0, 1};

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "true";

		String submitToMethod = "OpenCloseOfficeConfig_loadList.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	public String f9Employee() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC "
					  +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)" +
					   " WHERE HRMS_CENTER.CENTER_ID = "+openCloseConfig.getBranchId()+"" +
					   " AND HRMS_EMP_OFFC.EMP_ID NOT IN (SELECT HRMS_OPEN_CLOSE_CONFIG.EMP_ID FROM HRMS_OPEN_CLOSE_CONFIG "
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OPEN_CLOSE_CONFIG.EMP_ID) "
					+" WHERE BRANCH_ID = "+openCloseConfig.getBranchId()+")" +
					   " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ASC";

		String[] headers = { getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = { "20","80"};

		String[] fieldNames = { "empToken","ename","empCode"};

		int[] columnIndex = { 0, 1,2};

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


	
	

}
