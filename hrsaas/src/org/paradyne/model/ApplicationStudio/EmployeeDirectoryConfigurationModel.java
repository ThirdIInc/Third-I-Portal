package org.paradyne.model.ApplicationStudio;

import org.paradyne.lib.ModelBase;
import org.paradyne.bean.ApplicationStudio.EmployeeDirectoryConfiguration;

public class EmployeeDirectoryConfigurationModel extends ModelBase {
	
	public boolean save(EmployeeDirectoryConfiguration bean)
	{
		boolean result=false;
		result = getSqlModel().singleExecute("DELETE FROM HRMS_EMP_DIRETORY_CONF ");
		if(result)
		{
			String insertValQuery="";
			if(bean.getBirthdayFlag().equals("true"))
				insertValQuery+="'Y'";
			else
				insertValQuery+="'N'";
			if(bean.getBloodgroupFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getDateofjoiningFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getDepartmentFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getRoleanddesignationFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getManagerInfoFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getLocatoinFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getEmailFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getExtensionFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getMobilenoFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getPhonenoFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			if(bean.getPhotoFlag().equals("true"))
				insertValQuery+=",'Y'";
			else
				insertValQuery+=",'N'";
			
			
			String insertQuery="INSERT INTO HRMS_EMP_DIRETORY_CONF (HRMS_EMP_DIRETORY_CONF.EMP_BDAY, HRMS_EMP_DIRETORY_CONF.EMP_BLOODGROUP, HRMS_EMP_DIRETORY_CONF.EMP_JOINING_DATE, HRMS_EMP_DIRETORY_CONF.EMP_DEPTARTMENT, HRMS_EMP_DIRETORY_CONF.EMP_ROLE_DESG, HRMS_EMP_DIRETORY_CONF.EMP_MANAGER_INFO, HRMS_EMP_DIRETORY_CONF.EMP_BRANCH, HRMS_EMP_DIRETORY_CONF.EMP_EMAILID, HRMS_EMP_DIRETORY_CONF.EMP_EXTENSION, HRMS_EMP_DIRETORY_CONF.EMP_MOBILENO, HRMS_EMP_DIRETORY_CONF.EMP_PHONENO,HRMS_EMP_DIRETORY_CONF.EMP_PHOTO) VALUES ("+insertValQuery+")";
			result = this.getSqlModel().singleExecute(insertQuery);
		}
		return result;
	}
	
	public void getRecord(EmployeeDirectoryConfiguration bean)
	{
		String query="SELECT HRMS_EMP_DIRETORY_CONF.EMP_BDAY, HRMS_EMP_DIRETORY_CONF.EMP_BLOODGROUP, HRMS_EMP_DIRETORY_CONF.EMP_JOINING_DATE, HRMS_EMP_DIRETORY_CONF.EMP_DEPTARTMENT, HRMS_EMP_DIRETORY_CONF.EMP_ROLE_DESG, HRMS_EMP_DIRETORY_CONF.EMP_MANAGER_INFO, HRMS_EMP_DIRETORY_CONF.EMP_BRANCH, HRMS_EMP_DIRETORY_CONF.EMP_EMAILID, HRMS_EMP_DIRETORY_CONF.EMP_EXTENSION, HRMS_EMP_DIRETORY_CONF.EMP_MOBILENO, HRMS_EMP_DIRETORY_CONF.EMP_PHONENO,HRMS_EMP_DIRETORY_CONF.EMP_PHOTO FROM HRMS_EMP_DIRETORY_CONF";
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
			if(checkNull(String.valueOf(data[0][0])).equals("Y"))
			{
				bean.setBirthdayFlag("true");
			}
			if(checkNull(String.valueOf(data[0][1])).equals("Y"))
			{
				bean.setBloodgroupFlag("true");
			}
			if(checkNull(String.valueOf(data[0][2])).equals("Y"))
			{
				bean.setDateofjoiningFlag("true");
			}
			if(checkNull(String.valueOf(data[0][3])).equals("Y"))
			{
				bean.setDepartmentFlag("true");
			}
			if(checkNull(String.valueOf(data[0][4])).equals("Y"))
			{
				bean.setRoleanddesignationFlag("true");
			}
			if(checkNull(String.valueOf(data[0][5])).equals("Y"))
			{
				bean.setManagerInfoFlag("true");
			}
			if(checkNull(String.valueOf(data[0][6])).equals("Y"))
			{
				bean.setLocatoinFlag("true");
			}
			if(checkNull(String.valueOf(data[0][7])).equals("Y"))
			{
				bean.setEmailFlag("true");
			}
			if(checkNull(String.valueOf(data[0][8])).equals("Y"))
			{
				bean.setExtensionFlag("true");
			}
			if(checkNull(String.valueOf(data[0][9])).equals("Y"))
			{
				bean.setMobilenoFlag("true");
			}
			if(checkNull(String.valueOf(data[0][10])).equals("Y"))
			{
				bean.setPhonenoFlag("true");
			}
			if(checkNull(String.valueOf(data[0][11])).equals("Y"))
			{
				bean.setPhotoFlag("true");
			}
			
		}
	}
	
	/**
	 * To set blank for null value of any field.
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

}
