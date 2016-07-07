/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.util.HashMap;

import org.paradyne.bean.ApplicationStudio.AlertClientSetting;
import org.paradyne.model.ApplicationStudio.AlertClientSettingModel;
import org.paradyne.model.attendance.AutoUploadAttendanceModel;
import org.paradyne.model.leave.LeaveEncashmentProcessModel;
import org.struts.action.ApplicationStudio.jobScheduling.JobScheduler;
import org.struts.action.attendance.AutoUploadAttendanceAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0491
 *
 */
public class AlertClientSettingAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AlertClientSettingAction.class);
	AlertClientSetting alertClientSettingInstance = null;

	private final String jobGroup = "AlertMessage";
	private final String jobClass = "org.paradyne.model.ApplicationStudio.ProcessManagerAlertQuery";

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		alertClientSettingInstance = new AlertClientSetting();
		alertClientSettingInstance.setMenuCode(2119);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return alertClientSettingInstance;
	}

	public AlertClientSetting getAlertClientSettingInstance() {
		return alertClientSettingInstance;
	}

	public void setAlertClientSettingInstance(
			AlertClientSetting alertClientSettingInstance) {
		this.alertClientSettingInstance = alertClientSettingInstance;
	}

	public String setData() {

		try {

			alertClientSettingInstance.setJobDuration("");
			alertClientSettingInstance.setJobDayOfWeek("");
			alertClientSettingInstance.setJobDayOfMonth("");
			alertClientSettingInstance.setJobStartTime("");
			
			String autoJobID = alertClientSettingInstance.getHiddenJobCode();
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AlertMessage_" + autoJobID;

			HashMap<String, String> jobDetails = JobScheduler.getJobDetails(
					jobClient, jobName, jobGroup);

			AlertClientSettingModel model = new AlertClientSettingModel();
			model.initiate(context, session);
			model.setData(alertClientSettingInstance, jobDetails);

			if (alertClientSettingInstance.getJobQueryType().equals("F")) {
				alertClientSettingInstance.setShowFlag("true");
			}
			alertClientSettingInstance.setShowFlag("true");
			alertClientSettingInstance.setStartShedulerFlag("true");
			alertClientSettingInstance.setStopShedulerFlag("true");
			
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String deleteData() {
		try {

			AlertClientSettingModel model = new AlertClientSettingModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");

			//displayIttValues();

			model.deleteData(srNo, empToken, employeeId, empName,
					alertClientSettingInstance);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String addEmployee() {
		try {

			AlertClientSettingModel model = new AlertClientSettingModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");

			//displayIttValues();

			boolean result = model.addEmployee(srNo, empToken, employeeId,
					empName, alertClientSettingInstance);

			if (result) {
				alertClientSettingInstance.setAddFlag("true");
				alertClientSettingInstance.setShowFlag("true");
			}

			alertClientSettingInstance.setEmployeeToken("");
			alertClientSettingInstance.setEmployeeCode("");
			alertClientSettingInstance.setEmployeeName("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String reset() {
		try {

			alertClientSettingInstance.setHiddenJobCode("");
			alertClientSettingInstance.setJobDuration("");
			alertClientSettingInstance.setJobDayOfWeek("");
			alertClientSettingInstance.setJobDayOfMonth("");
			alertClientSettingInstance.setJobStartTime("");
			//alertClientSettingInstance.setEmailCheck("");
			//alertClientSettingInstance.setAlertCheck("");
			alertClientSettingInstance.setModuleName("");
			alertClientSettingInstance.setEmployeeToken("");
			alertClientSettingInstance.setEmployeeName("");
			alertClientSettingInstance.setEmployeeCode("");
			alertClientSettingInstance.setJobQueryType("");
			alertClientSettingInstance.setSubject("");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;

	}
	
	public String startScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String autoJobID = alertClientSettingInstance.getHiddenJobCode()
			.trim();
			String jobName = "AlertMessage_" + autoJobID;
			boolean isJobScheduled = JobScheduler.startJobScheduling(jobClient, jobName, jobGroup);
			if(isJobScheduled) {
				addActionMessage("Scheduler started successfully.");
			} else {
				addActionMessage("Scheduler cannot be started.");
			}
			alertClientSettingInstance.setStartShedulerFlag("false");
			alertClientSettingInstance.setStopShedulerFlag("true");
		} catch(Exception e) {
			logger.error("Exception in startScheduling in action:" + e);
			e.printStackTrace();
			addActionMessage("Scheduler cannot be started.");
		}
		setItteratorData();
		return SUCCESS;
	}
	
	
	public String stopScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String autoJobID = alertClientSettingInstance.getHiddenJobCode()
			.trim();
			String jobName = "AlertMessage_" + autoJobID;
			boolean result = JobScheduler.stopJobScheduling(jobClient, jobName, jobGroup);
			if(result) {
				addActionMessage("Scheduler stopped successfully.");
			}
			else{
				addActionMessage("Scheduler cannot be stopped.");
				
			}
			alertClientSettingInstance.setStartShedulerFlag("true");
			alertClientSettingInstance.setStopShedulerFlag("false");
			
		} catch(Exception e) {
			logger.error("Exception in stopScheduling in action:" + e);
			e.printStackTrace();
		}
		setItteratorData();
		return SUCCESS;
	}
	
	
	
	public void setItteratorData()
	{
		try {
			AlertClientSettingModel model = new AlertClientSettingModel();
			model.initiate(context, session);
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");
			boolean result = model.setItteratorData(srNo, empToken, employeeId,
					empName, alertClientSettingInstance);
			if (result) {
				alertClientSettingInstance.setAddFlag("true");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public String save() {
		try {

			AlertClientSettingModel model = new AlertClientSettingModel();
			model.initiate(context, session);

			String[] employeeId = request.getParameterValues("employeeId");

			String jobStartTime = request.getParameter("jobStartTime");
			String jobDuration = request.getParameter("jobDuration");
			String jobDayOfWeek = request.getParameter("jobDayOfWeek");
			String jobDayOfMonth = request.getParameter("jobDayOfMonth");

			String autoJobID = alertClientSettingInstance.getHiddenJobCode()
					.trim();
			
		 
			String message = addUpdateJob("UPDATE", autoJobID, jobStartTime,
					jobDuration, jobDayOfWeek, jobDayOfMonth);

			if (message.equalsIgnoreCase("SAVED")) {
				boolean result = model.saveAlert(alertClientSettingInstance,
						employeeId);
				if (result) {
					addActionMessage("Record saved successfully");
					alertClientSettingInstance.setStartShedulerFlag("true");
				} else {
					addActionMessage("Record can not be saved");
				}
			} else {
				addActionMessage("Record can not be saved");
			}
			setItteratorData();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//reset();
		return SUCCESS;

	}

	private String addUpdateJob(String typeOfTransaction, String autoUploadID,
			String jobStartTime, String jobDuration, String jobDayOfWeek,
			String jobDayOfMonth) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AlertMessage_" + autoUploadID;

			boolean result = false;

			/**
			 * Update job
			 */
			 if (typeOfTransaction.equalsIgnoreCase("UPDATE")) {
				
				System.out.println("In update Logic-----------------------------------------");
				result = JobScheduler.deleteJob(jobClient, jobName, jobGroup);
				
				System.out.println("result-------------------------------    "+result);

				// if(result) {
				result = JobScheduler.addJob(jobClient, jobName, jobGroup,
						jobClass, jobStartTime, jobDuration, jobDayOfWeek,
						jobDayOfMonth);

				if (result) {
					message = "SAVED";
				}
				 //}
			}
		} catch (Exception e) {
			logger.error("Exception in editJobInScheduler:" + e);
			e.printStackTrace();
		}
		return message;
	}

	public String f9employee() throws Exception {
		try {
			//  to remove an already selected employee from the search list
			String[] empCode = request.getParameterValues("employeeId");
			String str = "0";
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {// loop x
					str += "," + empCode[i];
				}// end loop x
			}// end if
			String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
					+ " FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
	}// end f9employee method

	public String f9searchaction() throws Exception {

		String query = " SELECT JOB_ID, JOB_MODULE_NAME,JOB_SUBJECT,JOB_QUERY_TYPE  FROM HRMS_JOB ORDER BY JOB_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Sr. No.", getMessage("modName") , getMessage("subject")};
		String[] headerWidth = { "10", "15","75" };
		String[] fieldNames = { "hiddenJobCode", "moduleName","subject", "jobQueryType" };
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2,3 };

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

		String submitToMethod = "AlertClientSetting_setData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
