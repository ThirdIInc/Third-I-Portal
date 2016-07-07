/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.io.File;
import org.paradyne.bean.ApplicationStudio.DashletProfileSettings;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.DashletProfileSettingsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class DashletProfileSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	DashletProfileSettings profileSettings = null;
	String poolName = "";
	String fileName = "";
	
	/**
	 * @return the profileSettings
	 */
	public DashletProfileSettings getProfileSettings() {
		return profileSettings;
	}

	/**
	 * @param profileSettings the profileSettings to set
	 */
	public void setProfileSettings(DashletProfileSettings profileSettings) {
		this.profileSettings = profileSettings;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		profileSettings = new DashletProfileSettings();
		profileSettings.setMenuCode(784);
		poolName = String.valueOf(session.getAttribute("session_pool"));
		
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return profileSettings;
	}
	
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		try{
			getNavigationPanel(1);
			reset();
			}catch(Exception e){
				e.printStackTrace();
			}
			profileSettings.setOnLoadFlag(true);
			return INPUT;
	}
	
	public String getDashletList() throws Exception {
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}// end of if

		// READING FROM XML
		String path = getText("data_path") + "\\dashlet\\default\\dashletSettings.xml";
		DashletProfileSettingsModel model = new DashletProfileSettingsModel();
		model.initiate(context, session);
		File file = new File(path);
		if (file.exists()) {
			logger.info("File exists");
			Object[][] result = model.processDashletList(
					new XMLReaderWriter().parse(new File(path)));
			request.setAttribute("searchObj", result);
		} else {
			logger.info("File does not exist");
			request.setAttribute("message", "File not found");
		}
		return SUCCESS;
	}
	
	public String save() throws Exception{
		//Default Method with Edit modeCode(3)
		getNavigationPanel(1);
		logger.info("Profile id ------------------"+profileSettings.getProfileID());
		
		String [] dashcode = request.getParameterValues("dashcode");
		String [] chkDash = request.getParameterValues("confChk");
		String [] dashName = request.getParameterValues("dashName");
		Object[][] dashObj = new Object[dashcode.length][3];
		
		for (int i = 0; i < dashObj.length; i++) {
			dashObj[i][0] = dashcode[i];
			dashObj[i][1] = dashName[i];
			dashObj[i][2] = "N";
		}
		if(chkDash != null && chkDash != null)
		for (int i = 0; i < chkDash.length; i++) 
			dashObj[Integer.parseInt(chkDash[i])][2] = "Y";
		
		fileName = getText("data_path")+"\\dashlet\\"+poolName+"\\profile\\"
		+profileSettings.getProfileID()+"\\profileDashletList.xml";
		DashletProfileSettingsModel model = new DashletProfileSettingsModel();
		model.initiate(context, session);
		model.saveDashletProSettings(profileSettings, fileName,dashObj);
		model.terminate();
		setProfileSettings();
		addActionMessage(getMessage("save"));
		return SUCCESS;
	}
	
	public String setProfileSettings()  throws Exception {
		getNavigationPanel(2);
		Object[][] result = null;
		Object[][] result1 = null;
		String [][] finalObj = null;
		String path = getText("data_path")+"\\dashlet\\" 
		+ "default\\dashletSettings.xml";
		
		DashletProfileSettingsModel model = new DashletProfileSettingsModel();
		model.initiate(context, session);
		
		File file = new File(path);
		if (file.exists()) {
			try {
				logger.info("----------------------------Result Path-- "+path);
				result = model.processDashletList(
						new XMLReaderWriter().parse(new File(path)));
				path = getText("data_path")+"\\dashlet\\" 
				+ session.getAttribute("session_pool")+"\\profile\\"+profileSettings.getProfileID()
				+"\\profileDashletList.xml";
				file = new File(path);
				if(!file.exists())
				{
					path = getText("data_path")+"\\dashlet\\" 
					+ "default\\profile\\"+profileSettings.getProfileID()
					+"\\profileDashletList.xml";
					file = new File(path);
					if(!file.exists())
					{
						path = getText("data_path")+"\\dashlet\\" 
						+session.getAttribute("session_pool")+"\\profile\\2"
						+"\\profileDashletList.xml";
						file = new File(path);
						if(!file.exists())
						{
							path = getText("data_path")+"\\dashlet\\" 
							+ "default\\profile\\2\\"
							+"\\profileDashletList.xml";
							file = new File(path);
						}
					}
				}
				logger.info("----------------------------Result1 Path-- "+path);
				result1 = model.processDashletList(
						new XMLReaderWriter().parse(new File(path)));
				int j=0;
				finalObj = new String[result.length][3];
				
				logger.info("Result Length : "+result.length);
				logger.info("Result1 Length : "+result1.length);
				
				// result1 is for profileDashletList file
				// result is for dashletSettings file 
				
				if(result1.length > result.length)
				{
					for (int i = 0; i < result1.length; i++) {
						finalObj[i][0] = ""+result1[i][0];
						finalObj[i][1] = ""+result1[i][1];
						finalObj[i][2] = "N";
						for ( j = 0; j < result.length; j++) 
							if(String.valueOf(result1[i][0]).equals(String.valueOf(result[j][0])))
								finalObj[i][2] = "Y";
					}
				}
				else
				{
					finalObj = new String[result.length][result[0].length];
					for (int i = 0; i < result.length; i++) {
						finalObj[i][0] = ""+result[i][0];
						finalObj[i][1] = ""+result[i][1];
						finalObj[i][2] = "N";
					for ( j = 0; j < result1.length; j++) 
						if(String.valueOf(result1[j][0]).equals(String.valueOf(result[i][0])))
							finalObj[i][2] = "Y";
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		request.setAttribute("listObj", finalObj);
		return SUCCESS;
	}
	
	private String reset() {
		// TODO Auto-generated method stub
		profileSettings.setProfileID("");
		profileSettings.setProfileName("");
		return SUCCESS;
	}
	
	/**
	 * Search Profile
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9profile() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */

		String query = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_HDR ORDER BY PROFILE_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("profile") };

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

		String[] fieldNames = { "profileName", "profileID" };

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
	
	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */

		String query = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_HDR ORDER BY PROFILE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("profile") };

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

		String[] fieldNames = { "profileName", "profileID" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DashletProfileSettings_setProfileSettings.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

}
