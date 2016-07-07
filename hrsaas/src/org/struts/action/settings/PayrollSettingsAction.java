/**
 * 
 */
package org.struts.action.settings;

import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.model.settings.SuperSettingsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 * 
 */
public class PayrollSettingsAction extends ParaActionSupport {
	SuperSettings bean;

	public void prepare_local() throws Exception {
		bean = new SuperSettings();
		bean.setMenuCode(664);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * Called on load Set Payroll Settings fields on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		showAppSetting();
	}

	/**
	 * Saving Records under Payroll settings
	 * 
	 * @return String
	 */
	public String saveApplicationSetting() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// save
		boolean flag = model.saveAppSetting(bean);
		if (flag)
			addActionMessage("Settings Saved Successfully");
		else
			addActionMessage("Settings Cant be Saved.");
		// setting after save
		model.showAppSetting(bean);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * From on load
	 * 
	 * @return String
	 */
	public String showAppSetting() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		model.showAppSetting(bean);
		model.terminate();
		return SUCCESS;
	}
	
	public String f9RecDebit(){
		
			String query = " SELECT DEBIT_NAME, DEBIT_CODE FROM HRMS_DEBIT_HEAD ORDER BY UPPER(DEBIT_NAME) ";

			String[] headers = {getMessage("debit.name")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"recDebitName", "recDebitCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
	
	}
}
