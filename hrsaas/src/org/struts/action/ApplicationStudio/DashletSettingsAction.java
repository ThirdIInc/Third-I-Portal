/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.DashletSettings;
import org.paradyne.model.ApplicationStudio.DashletSettingsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph&Pankaj_Jain
 *
 */
public class DashletSettingsAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	DashletSettings dashletSettings = null;
	String poolDir = "";
	String fileName = "";
	
	/**
	 * @return the dashletSettings
	 */
	public DashletSettings getDashletSettings() {
		return dashletSettings;
	}

	/**
	 * @param dashletSettings the dashletSettings to set
	 */
	public void setDashletSettings(DashletSettings dashletSettings) {
		this.dashletSettings = dashletSettings;
	}

	public void prepare_local() throws Exception {
		dashletSettings = new DashletSettings();
		dashletSettings.setMenuCode(778);
		poolDir = String.valueOf(session.getAttribute("session_pool"));

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return dashletSettings;
	}
	
	public String input() throws Exception {
		try{
		getNavigationPanel(1);
		}catch(Exception e){
			e.printStackTrace();
		}
		dashletSettings.setOnLoadFlag(true);
		callPage();
		return INPUT;
	}
	
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		DashletSettingsModel model = new DashletSettingsModel();	
		model.initiate(context, session);
		model.reqData(dashletSettings,request);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * Method gets call when user clicks page no
	 * @return
	 * @throws Exception
	 */
	public String callPage1() throws Exception {
		getNavigationPanel(1);
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		dashletSettings.setPageFlag(true);
		model.reqData(dashletSettings,request);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * Method gets call when user selects the page no 
	 * from dropdown list
	 * @return
	 * @throws Exception
	 */
	public String callPage2() throws Exception {
		getNavigationPanel(1);
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		model.reqData(dashletSettings,request);
		dashletSettings.setPageFlag(true);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * To add the new record to list
	 * fields gets displayed in editable mode
	 * and the new record can be added
	 * @return
	 * @throws Exception
	 */
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		DashletSettingsModel model = new DashletSettingsModel();		
		model.initiate(context,session);
		callPage();
		reset();
		dashletSettings.setLoadFlag(true);
		dashletSettings.setFlag(true);		
		model.terminate();
		return SUCCESS;
		
	}
	
	/**
	 * to reset all the fields and bean variables
	 * default display mode is displayed
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception{
		dashletSettings.setDashletId("");
		dashletSettings.setName("");
		dashletSettings.setLink("");
		dashletSettings.setPosition("");
		dashletSettings.setType("");
		return SUCCESS;
	}
	
	/**
	 * returns to the previous mode
	 * i.e. return to the list 
	 * @return
	 * @throws Exception
	 */
	public String cancelFirst() throws Exception{
		getNavigationPanel(1);
		callPage();
		dashletSettings.setOnLoadFlag(true);
		reset();
		return SUCCESS;
	}

	/**
	 * cancel after record is saved or searched from
	 * search window 
	 * @return
	 * @throws Exception
	 */
	public String cancelSec() throws Exception{
		getNavigationPanel(1);
		DashletSettingsModel model = new DashletSettingsModel();	
		model.initiate(context, session);
		callPage();
		dashletSettings.setOnLoadFlag(true);
		dashletSettings.setSaveFlag(true);
		model.terminate();
		return SUCCESS;
		
	}
	
	/**
	 * when new record is set to be in editable mode
	 * and reverts back to non-editable mode
	 * @return
	 * @throws Exception
	 */
	public String cancelThrd() throws Exception{
		getNavigationPanel(3);
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		
		callPage();
		model.getDashletSettings(dashletSettings);
		dashletSettings.setSaveFlag(true);
		dashletSettings.setModFlag(false);
		model.terminate();
		return SUCCESS;
			
	}
	
	/**
	 * return to previous mode
	 * @return
	 * @throws Exception
	 */
	public String cancelFrth() throws Exception{
		getNavigationPanel(1);
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		callPage();
		reset();
		dashletSettings.setOnLoadFlag(true);
		model.terminate();
		return "success";
		
	}
	
	/**
	 * to save the newly added record
	 * or to modify an existing record
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		//Default Method with Edit modeCode(3)
		getNavigationPanel(3);
		
		fileName = getText("data_path") + "\\dashlet\\default\\" 
		+ "dashletSettings.xml";
		
		DashletSettingsModel model = new DashletSettingsModel();	
		model.initiate(context, session);
		
		// if new record
		if(dashletSettings.getDashletId().equals("")){
			boolean result=model.addDashletSettings(dashletSettings,fileName);
			if(result){
				addActionMessage(getMessage("save"));
				callPage();
			}else{
				addActionMessage(getMessage("duplicate"));
			}
		}
		else // if record to be modified
		{
			boolean result=model.modDashletSettings(dashletSettings,fileName);
			if(result){
				addActionMessage(getMessage("save"));
				callPage();
			}else{
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				dashletSettings.setLoadFlag(true);
				dashletSettings.setFlag(true);
			}
		}
		model.reqData(dashletSettings,request);			
		dashletSettings.setLoadFlag(false);
		dashletSettings.setAddFlag(true);
		dashletSettings.setSaveFlag(true);		
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * method gets call to display the dashlet list
	 * @return
	 */
	public String details() {
		getNavigationPanel(3);
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		dashletSettings.setDashletId(dashletSettings.getDashletId());
		model.getDashletSettings(dashletSettings);
		dashletSettings.setSaveFlag(true);
		model.reqData(dashletSettings,request);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * when record is double clicked or particular
	 * record is selected from search window
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(4);
		DashletSettingsModel model = new DashletSettingsModel();	
		model.initiate(context, session);
		model.getDashletSettings(dashletSettings);
		callPage();
		model.terminate();
		dashletSettings.setEditFlag(true);
		dashletSettings.setModFlag(true);
		dashletSettings.setAddFlag(false);
		//dashletSettings.setOnLoadFlag(false);
		
		return SUCCESS;
	}
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		
		fileName = getText("data_path")+"\\dashlet\\default\\dashletSettings.xml";
		
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context, session);
		boolean result=model.deleteDashletSettings(dashletSettings,fileName);
		if(result){
			addActionMessage(getMessage("delete"));
			reset();
		}else{
			addActionMessage("Record can't be deleted \n as it is associated with some other record");
		}
		callPage();
		dashletSettings.setOnLoadFlag(true);
		model.terminate();
		
		return SUCCESS;
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		System.out.println("hidden code============="+dashletSettings.getHiddencode());
		getNavigationPanel(2);			
		DashletSettingsModel model = new DashletSettingsModel();	
		model.initiate(context,session);
		model.getSettingsOnDoubleClick(dashletSettings);
		//model.getDashletSetting(dashletSettings);
		model.reqData(dashletSettings,request);
		dashletSettings.setDblFlag(true);
		dashletSettings.setModFlag(false);
		model.terminate();
		return "success";
	}
	
	/**
	 * when multiple records are selected to delete
	 * @return
	 * @throws Exception
	 */
	public String delete1() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		
		fileName = getText("data_path")+"\\dashlet\\default\\dashletSettings.xml";
		
		boolean result;
		DashletSettingsModel model = new DashletSettingsModel();
		model.initiate(context,session);
		callPage2();
		String[] code=request.getParameterValues("hdeleteCode");
		result=model.delChkdRec(dashletSettings,code,fileName);
		if(result){
			input();
			reset();
			addActionMessage(getMessage("delete"));
			dashletSettings.setPageFlag(false);
		}
		else
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		return "success";
	}
	
	
	/**
	 * when search button is clicked 
	 * it displays all the records and one record can be selected
	 * @return
	 * @throws Exception
	 */
	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		
		String query="SELECT  DASHLET_NAME,DASHLET_LINK,"
			+ " DECODE(DASHLET_POSITION,'F','Fixed','M','Movable'), "
			+ " DECODE(DASHLET_TYPE,'L','List','G','Graph','O','Others'), "
			+ " DASHLET_CODE FROM HRMS_DASHLET_SETTINGS"
			+ " ORDER BY  DASHLET_CODE";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("dashlet.name"), getMessage("dashlet.link")};

		String[] headerWidth = { "40", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "name", "link","position","type", "dashletId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DashletSettings_details.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
