package org.struts.action.recruitment;
/*
 * @author saipavan voleti
 * 
 */
import org.paradyne.bean.Recruitment.ConfigureMailAlerts;
import org.paradyne.model.recruitment.ConfigureMailAlertsModel;
import org.struts.lib.ParaActionSupport;

public class CentralisedPanelSettingAction extends ParaActionSupport {

	ConfigureMailAlerts Confbean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		Confbean=new ConfigureMailAlerts();
		Confbean.setMenuCode(927);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return Confbean;
	}

	public ConfigureMailAlerts getConfbean() {
		return Confbean;
	}

	public void setConfbean(ConfigureMailAlerts confbean) {
		Confbean = confbean;
	}

	
	public String input()  {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		//model.getCentraliseSettingDtl(Confbean);
		//model.setModulenames(Confbean);
		model.terminate();
		
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		//model.getCentraliseSettingDtl(Confbean);
		//model.setModulenames(Confbean);
		model.terminate();
		
		
	}
	
	public String viewEvents()  {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		model.getCentraliseSettingDtl(Confbean);
		System.out.println("module codessssssssssss  ..."+Confbean.getModuleName());
		Confbean.setEventFlag(true);
	//	model.setModulenames(Confbean);
		//Confbean.setModuleName(Confbean.getModuleName());
		
		model.terminate();
		
		return SUCCESS;
	}
	
	
	
	
	
	public String saveCentraliseSetting() {
		System.out.println("saveCentraliseSetting");
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		boolean result=model.saveCentraliseSetting(Confbean,request);
		if(result)
		{
			addActionMessage("Centralised Panel Setting updated successfully.");
		}
		else
		{
			addActionMessage("Centralised Panel Setting cann't updated.");
		}
		model.getCentraliseSettingDtl(Confbean);
		Confbean.setModuleName(Confbean.getModuleName());
		model.terminate();
		return SUCCESS;
	}
	
	
	public String f9action() throws Exception {

		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_EMAILTEMPLATE_HDR " +
				 " where TEMPLATE_EVENT_CODE="+Confbean.getDupModulecode()+
				" ORDER BY TEMPLATE_ID";
		String[] headers = { getMessage("template.code"), getMessage("template.name") };
		String[] headerWidth = { "20", "50" };
		String[] fieldNames = { "templateCode"+Confbean.getRowId(), "templateName"+Confbean.getRowId() };
		
		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";
		
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	public String f9ModuleName() throws Exception {
		String query = " SELECT MODULE_CODE, MODULE_NAME FROM HRMS_MODULE order by MODULE_CODE ";

		String[] headers = { "Module Code","Module Name"};            
		  //"Event Name","Event Module Name" };
		String[] headerWidth = { "25", "50" };
		String[] fieldNames = { "dupModulecode","moduleName" };
		int[] columnIndex = { 0,1};
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
	
}
