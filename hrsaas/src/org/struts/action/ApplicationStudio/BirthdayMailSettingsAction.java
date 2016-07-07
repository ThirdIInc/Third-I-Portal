/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.struts.action.ApplicationStudio.jobScheduling.JobScheduler;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.ApplicationStudio.BirthdayMailSettings;
import org.paradyne.model.ApplicationStudio.BirthdayMailSettingsModel;

/**
 * @author Reeba
 * 
 */
public class BirthdayMailSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	BirthdayMailSettings birthdayMailSettings = null;

	/**
	 * @return the birthdayMailSettings
	 */
	public BirthdayMailSettings getBirthdayMailSettings() {
		return birthdayMailSettings;
	}

	/**
	 * @param birthdayMailSettings
	 *            the birthdayMailSettings to set
	 */
	public void setBirthdayMailSettings(
			BirthdayMailSettings birthdayMailSettings) {
		this.birthdayMailSettings = birthdayMailSettings;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		birthdayMailSettings = new BirthdayMailSettings();
		birthdayMailSettings.setMenuCode(775);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return birthdayMailSettings;
	}

	public void prepare_withLoginProfileDetails() throws Exception {		
		displaySettings();
	}

	public String input(){
		displaySettings();
		return SUCCESS;
	}
	private String displaySettings() {
		BirthdayMailSettingsModel model = new BirthdayMailSettingsModel();
		model.initiate(context, session);
		model.displaySettings(birthdayMailSettings);
		model.terminate();
		return SUCCESS;
	}

	public String save() {
		BirthdayMailSettingsModel model = new BirthdayMailSettingsModel();
		model.initiate(context, session);
		// save
		boolean flag = model.saveSettings(birthdayMailSettings,request);
		if (flag) {
			addActionMessage(getMessage("save"));
			String jobStartTime = request.getParameter("mailSentOnTiming");
			addUpdateJob("UPDATE", "BirthDay", jobStartTime, "Daily", "", "");
		} else {
			addActionMessage(getMessage("save.error"));
		}
		// setting after save
		model.displaySettings(birthdayMailSettings);
		model.terminate();
		birthdayMailSettings.setEmpName("");
		return SUCCESS;
	}
	
	
	private String addUpdateJob(String typeOfTransaction, String autoUploadName,
			String jobStartTime, String jobDuration, String jobDayOfWeek,
			String jobDayOfMonth) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AlertMessage_"+autoUploadName;
			final String jobGroup = "AlertMessage";
			final String jobClass = "org.paradyne.model.ApplicationStudio.AutoBirthdayModel";
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

	public String f9mailTemplate() throws Exception {

		String query = " SELECT TEMPLATE_ID, NVL(TEMPLATE_NAME,'') FROM HRMS_BIRTHDAYTEMPLATE_HDR ORDER BY TEMPLATE_NAME";
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
	}

	//Updated by Anantha Lakshmi
	public String f9Emp() throws Exception {
		String employeeCode = "0";
		String[] employeeId = request.getParameterValues("empCodeIt");
		if (employeeId != null && employeeId.length > 0) {
			for (int i = 0; i < employeeId.length; i++) {
				if (i == 0) {
					employeeCode = employeeId[i];
				} else {
					employeeCode += "," + employeeId[i];
				}
			}
		}

		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";

		//query += getprofileQuery(probationConfirm);

		query += " WHERE EMP_ID NOT IN ("+employeeCode+") ORDER BY EMP_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Code", "Employee Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenNo", "empName", "empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String addEmp() {
		boolean flag = false;
		boolean upFlag = false;
		BirthdayMailSettingsModel model = new BirthdayMailSettingsModel();
		model.initiate(context, session);
		String[] empId = request.getParameterValues("empCodeIt");
		String[] employeeToken = request.getParameterValues("empTokenNoIt");
		String[] employeeName = request.getParameterValues("empNameIt");

		model.addEmp(birthdayMailSettings, empId, employeeToken, employeeName);

		birthdayMailSettings.setEmpName("");
		birthdayMailSettings.setEmpTokenNo("");
		birthdayMailSettings.setEmpCode("");
		model.terminate();
		return SUCCESS;
	}

	public String deleteEmp() {
		boolean flag = false;
		boolean upFlag = false;
		BirthdayMailSettingsModel model = new BirthdayMailSettingsModel();
		model.initiate(context, session);
		String[] empCode = request.getParameterValues("empCodeIt");
		String[] empToken = request.getParameterValues("empTokenNoIt");
		String[] empName = request.getParameterValues("empNameIt");
		String[] paraID=request.getParameterValues("paraId"); 
		String strParaID="";
		for(int i=0;i<paraID.length;i++){
			strParaID=String.valueOf(paraID[i]);
		}	
		model.deleteEmp(birthdayMailSettings, empCode, empToken, empName,strParaID);
		model.terminate();

		birthdayMailSettings.setEmpName("");
		birthdayMailSettings.setEmpTokenNo("");
		birthdayMailSettings.setEmpCode("");
		return SUCCESS;
	}
}
