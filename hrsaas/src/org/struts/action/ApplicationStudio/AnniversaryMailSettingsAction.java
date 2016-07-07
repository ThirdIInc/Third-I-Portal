/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AnniversaryMailSettings;
import org.paradyne.model.ApplicationStudio.AnniversaryMailSettingsModel;
import org.struts.action.ApplicationStudio.jobScheduling.JobScheduler;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * 
 */
public class AnniversaryMailSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	AnniversaryMailSettings anniversaryMailSettings = null;

	

	/**
	 * @return the anniversaryMailSettings
	 */
	public AnniversaryMailSettings getAnniversaryMailSettings() {
		return anniversaryMailSettings;
	}

	/**
	 * @param anniversaryMailSettings the anniversaryMailSettings to set
	 */
	public void setAnniversaryMailSettings(
			AnniversaryMailSettings anniversaryMailSettings) {
		this.anniversaryMailSettings = anniversaryMailSettings;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		anniversaryMailSettings = new AnniversaryMailSettings();
		anniversaryMailSettings.setMenuCode(833);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return anniversaryMailSettings;
	}

	/**
	 * call on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		displaySettings();
	}

	/**
	 * Set values on load
	 * @return String
	 */
	private String displaySettings() {
		// TODO Auto-generated method stub
		AnniversaryMailSettingsModel model = new AnniversaryMailSettingsModel();
		model.initiate(context, session);
		model.displaySettings(anniversaryMailSettings);
		model.terminate();
		return SUCCESS;
	}//end of displaySettings method

	/**
	 * Save mail settings
	 * @return String
	 */
	public String save() {
		AnniversaryMailSettingsModel model = new AnniversaryMailSettingsModel();
		model.initiate(context, session);
		// save
		boolean flag = model.saveSettings(anniversaryMailSettings);
		if (flag) {
			addActionMessage(getMessage("save"));
			String jobStartTime = request.getParameter("mailSentOnTiming");
			addUpdateJob("UPDATE", "Anniversary", jobStartTime, "Daily", "", "");
		} else {
			addActionMessage(getMessage("save.error"));
		}
		// setting after save
		model.displaySettings(anniversaryMailSettings);
		model.terminate();
		return SUCCESS;
	}//end of save method
	
	
	private String addUpdateJob(String typeOfTransaction, String autoUploadName,
			String jobStartTime, String jobDuration, String jobDayOfWeek,
			String jobDayOfMonth) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AlertMessage_"+autoUploadName;
			final String jobGroup = "AlertMessage";
			final String jobClass = "org.paradyne.model.ApplicationStudio.AutoAnniversaryModel";
			boolean result = false;
			/**
			 * Update job
			 */
			 if (typeOfTransaction.equalsIgnoreCase("UPDATE")) {
				result = JobScheduler.deleteJob(jobClient, jobName, jobGroup);
				result = JobScheduler.addJob(jobClient, jobName, jobGroup,
						jobClass, jobStartTime, jobDuration, jobDayOfWeek,
						jobDayOfMonth);
				boolean isJobScheduled = JobScheduler.startJobScheduling(jobClient, jobName, jobGroup);
				if (result) {
					message = "SAVED";
				}
			}
		} catch (Exception e) {
			logger.error("Exception in editJobInScheduler:" + e);
			e.printStackTrace();
		}
		return message;
	}
	/**
	 * Search saved template from anniversary template table.
	 * @return String (Search window)
	 * @throws Exception
	 */
	public String f9mailTemplate() throws Exception {

		String query = " SELECT TEMPLATE_ID, NVL(TEMPLATE_NAME,'') FROM HRMS_ANNIVERSARY_TEMPLATE ORDER BY TEMPLATE_NAME";
		String[] headers = { getMessage("template.code"),
				getMessage("template") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "tempCode", "tempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of f9mailTemplate method

}//end of class
