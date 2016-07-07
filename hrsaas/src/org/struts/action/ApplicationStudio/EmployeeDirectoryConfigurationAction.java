package org.struts.action.ApplicationStudio;

import org.struts.action.attendance.AttendanceSettingsAction;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.ApplicationStudio.EmployeeDirectoryConfiguration;
import org.paradyne.model.ApplicationStudio.EmployeeDirectoryConfigurationModel;


public class EmployeeDirectoryConfigurationAction extends ParaActionSupport {

	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeDirectoryConfigurationAction.class);
	EmployeeDirectoryConfiguration empdirConf;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		empdirConf=new EmployeeDirectoryConfiguration();
		empdirConf.setMenuCode(2246);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return empdirConf;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		EmployeeDirectoryConfigurationModel model = new EmployeeDirectoryConfigurationModel();
		model.initiate(context, session);
		model.getRecord(empdirConf);
		model.terminate();
	}
	
	public String save()
	{
		EmployeeDirectoryConfigurationModel model = new EmployeeDirectoryConfigurationModel();
		model.initiate(context, session);
		boolean result=model.save(empdirConf);
		if(result){
			addActionMessage(getMessage("save"));
		}
		else
		{
			addActionMessage(getMessage("save.error"));
		}
		model.terminate();
		return SUCCESS;
	}
	public String reset()
	{
		empdirConf.setBirthdayFlag("false");
		empdirConf.setBloodgroupFlag("false");
		empdirConf.setDateofjoiningFlag("false");
		empdirConf.setDepartmentFlag("false");
		empdirConf.setRoleanddesignationFlag("false");
		empdirConf.setManagerInfoFlag("false");
		empdirConf.setLocatoinFlag("false");
		empdirConf.setEmailFlag("false");
		empdirConf.setExtensionFlag("false");
		empdirConf.setMobilenoFlag("false");
		empdirConf.setPhonenoFlag("false");
		empdirConf.setPhotoFlag("false");
		return SUCCESS;
	}

}
