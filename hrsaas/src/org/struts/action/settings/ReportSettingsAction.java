package org.struts.action.settings;

import org.paradyne.bean.settings.ReportSettings;
import org.paradyne.model.settings.ReportSettingsModel;
import org.struts.lib.ParaActionSupport;

public class ReportSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportSettingsAction.class); 
	
	ReportSettings bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new ReportSettings();
		bean.setMenuCode(2266);

	}
	
	public Object getModel() {
		return bean;
	}
	
	public String input() throws Exception {
		ReportSettingsModel model=new ReportSettingsModel();
		model.initiate(context,session);
		model.getData(bean);
		bean.setEnableAll("Y");
		getNavigationPanel(1);
		return INPUT;
	}

	public String f9Divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		System.out.println("m in 3rd f9 block>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		
		
		String query = " SELECT  DIV_NAME, DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
		
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "divname", "divCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportSettings_setData.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		bean.setMasterMenuCode(42);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	//HRMS_REPORT_CONF
	public String setData()throws Exception{
		try {
			ReportSettingsModel model = new ReportSettingsModel();
			model.initiate(context, session);
			model.getData(bean);
			} catch (Exception e) {
			e.printStackTrace();
		}
			return input();
	}
	
	public String saveData()throws Exception{
		try {
			ReportSettingsModel model = new ReportSettingsModel();
			model.initiate(context, session);
			boolean result = model.save(bean);
			
			if (result) {
				addActionMessage("Record saved successfully.");
			}// end of if
			else {
				addActionMessage("Record can't be saved.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return input();
	}
	
	public String reset()throws Exception{
		ReportSettingsModel model = new ReportSettingsModel();
		model.initiate(context, session);
		model.reset(bean);
		return input();
	}
}
