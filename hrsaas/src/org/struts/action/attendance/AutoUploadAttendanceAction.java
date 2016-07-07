/* Bhushan Dasare Feb 25, 2010 */

package org.struts.action.attendance;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.bean.attendance.AutoUploadAttendance;
import org.struts.action.ApplicationStudio.jobScheduling.JobScheduler;
import org.paradyne.model.attendance.AutoUploadAttendanceModel;
import org.struts.lib.ParaActionSupport;

public class AutoUploadAttendanceAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AutoUploadAttendanceAction.class);
	private AutoUploadAttendance bean = null;
	private final String jobGroup = "Attendance";
	private final String jobClass = "org.struts.action.attendance.CaptureAttendance";
	
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
		}
		return "showData";
	}

	private String addUpdateJob(String typeOfTransaction, String autoUploadID, String jobStartTime, String jobDuration, String jobDayOfWeek, 
		String jobDayOfMonth) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AutoUploadAttendance_" + autoUploadID;
			
			boolean result = false;
			
			/**
			 * Add job
			 */
			if(typeOfTransaction.equalsIgnoreCase("ADD")) {
				result = JobScheduler.addJob(jobClient, jobName, jobGroup, jobClass, jobStartTime, jobDuration, jobDayOfWeek, jobDayOfMonth);
				
				if(result) {
					message = "SAVED";
				}
			}			
			
			/**
			 * Update job
			 */
			else if(typeOfTransaction.equalsIgnoreCase("UPDATE")) {
				result = JobScheduler.deleteJob(jobClient, jobName, jobGroup);
				
				//if(result) {
					result = JobScheduler.addJob(jobClient, jobName, jobGroup, jobClass, jobStartTime, jobDuration, jobDayOfWeek, jobDayOfMonth);
					
					if(result) {
						message = "SAVED";
					}
				//}
			}
		} catch(Exception e) {
			logger.error("Exception in editJobInScheduler:" + e);
			e.printStackTrace();
		}
		return message;
	}
	
	public String delete() {
		try {
			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);
			
			String autoUploadID = bean.getAutoUploadID();
			String message = deleteJob(autoUploadID);
			
			if(message.equalsIgnoreCase("DELETED")) {
				boolean result = model.delete(autoUploadID);
				if(result) {
					addActionMessage(getMessage("delete"));
					bean.setEnableAll("N");
					getNavigationPanel(1);
					getAutoUploadList();
					return SUCCESS;
				} else {
					addActionMessage(getMessage("del.error"));
					return "showData";
				}				
			} else if(message.equalsIgnoreCase("ACTIVE")) {
				addActionMessage("Record cannot be deleted \nbecause the task associated with this record is running. " +
				"\nPlease stop it and try again.");
				bean.setEnableAll("N");
				getNavigationPanel(4);
				return "showData";
			} else {
				addActionMessage(getMessage("del.error"));
				return "showData";
			}
		} catch(Exception e) {
			logger.error("Exception in delete in action:" + e);
			return null;
		}
	}

	public String deleteAll() {
		try {
			bean.setEnableAll("N");
			getNavigationPanel(1);
			String confChk[] = request.getParameterValues("confChk");
			
			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);
			
			if(confChk != null && confChk.length > 0) {
				String code = "";
				String codesNotDelted = "";
				
				for(int i = 0; i < confChk.length; i++) {
					String message = deleteAllJobs(confChk[i]);
					
					if(message.equalsIgnoreCase("DELETED")) {
						code += confChk[i] + ",";
					} else {
						codesNotDelted += confChk[i];
					}
				}
				
				boolean result = false;
				
				if(!code.equals("")) {
					code = code.substring(0, code.length() - 1);
					result = model.delete(code);
				}
				
				model.terminate();
				
				if(result) {
					if(codesNotDelted.equals("")) {
						addActionMessage(getMessage("delete"));
					} else {
						addActionMessage("Records cannot be deleted \nbecause the tasks associated with these records are running. \nPlease stop them and try again.");
					}
				} else {
					if(codesNotDelted.equals("")) {
						addActionMessage(getMessage("del.error"));
					} else {
						addActionMessage("Records cannot be deleted \nbecause the tasks associated with these records are running. \nPlease stop them and try again.");
					}
				}
			} else {
				addActionMessage("Please select atleast one record");
			}
			
			getAutoUploadList();
		} catch(Exception e) {
			logger.error("Exception in deleteAll in action:" + e);
		}
		return SUCCESS;
	}
	
	private String deleteAllJobs(String autoUploadID) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AutoUploadAttendance_" + autoUploadID;
			boolean isJobActive = JobScheduler.isJobActive(jobClient, jobName, jobGroup);
			
			if(isJobActive) {
				message = "ACTIVE";
			} else {
				boolean jobDeleted = JobScheduler.deleteJob(jobClient, jobName, jobGroup);
				
				if(jobDeleted) {
					message = "DELETED";
				}
			}
		} catch(Exception e) {
			logger.error("Exception in deleteAllJobs:" + e);
		}
		return message;
	}

	private String deleteJob(String autoUploadID) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AutoUploadAttendance_" + autoUploadID;
			boolean isJobActive = JobScheduler.isJobActive(jobClient, jobName, jobGroup);

			if(isJobActive) {
				message = "ACTIVE";
			} else {
				boolean result = JobScheduler.deleteJob(jobClient, jobName, jobGroup);
				
				if(result) {
					message = "DELETED";
				}
			}
		} catch(Exception e) {
			logger.error("Exception in deleteJob:" + e);
		}
		return message;
	}

	public String edit() {
		try {
			String autoUploadID = bean.getAutoUploadID();
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "AutoUploadAttendance_" + autoUploadID;
			
			HashMap<String, String> jobDetails = JobScheduler.getJobDetails(jobClient, jobName, jobGroup);
			
			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);
			
			model.edit(bean, autoUploadID, jobDetails);
			
			bean.setEnableAll("N");
			
			boolean isJobActive = JobScheduler.isJobActive(jobClient, jobName, jobGroup);
			
			if(isJobActive) {
				bean.setJobScheduled(true);
				getNavigationPanel(4);
			} else {
				bean.setJobScheduled(false);
				getNavigationPanel(3);
			}
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in edit in action:" + e);
		}
		return "showData";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT AUTO_UPLOAD_DRIVER, AUTO_UPLOAD_SERVER, AUTO_UPLOAD_ID FROM HRMS_ATTENDANCE_AUTO_UPLOAD ORDER BY AUTO_UPLOAD_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("lbDriver"), getMessage("lbServerName")};

		String[] headerWidth = {"30", "70"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"driver", "server", "autoUploadID"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN
		 * THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF ACTION>_<METHOD
		 * TO CALL>.action
		 */
		String submitToMethod = "AutoUploadAttendance_edit.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	private void getAutoUploadList() {
		try {
			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);

			Object[][] objAutoUploadList = model.getAutoUploadList();

			if(objAutoUploadList != null && objAutoUploadList.length > 0) {
				bean.setDataExists(true);

				ArrayList<AutoUploadAttendance> autoUploadList = new ArrayList<AutoUploadAttendance>();

				for(int i = 0; i < objAutoUploadList.length; i++) {
					AutoUploadAttendance bean1 = new AutoUploadAttendance();
					bean1.setUploadId(String.valueOf(objAutoUploadList[i][0]));
					bean1.setDriver(String.valueOf(objAutoUploadList[i][1]));
					bean1.setServer(String.valueOf(objAutoUploadList[i][2]));
					bean1.setUserName(String.valueOf(objAutoUploadList[i][3]));
					bean1.setDatabase(String.valueOf(objAutoUploadList[i][4]));
					autoUploadList.add(bean1);
				}
				
				bean.setData_exit("true");
				bean.setAutoUploadList(autoUploadList);
			} else {
				bean.setData_exit("false");
				bean.setAutoUploadList(null);
			}

			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getAutoUploadList:" + e);
		}
	}

	/**
	 * @return the bean
	 */
	public AutoUploadAttendance getBean() {
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	@Override
	public String input() throws Exception {
		bean.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new AutoUploadAttendance();
		bean.setMenuCode(1015);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		getAutoUploadList();
	}

	private void reset() {
		try {
			bean.setAutoUploadID("");
			bean.setUploadId("");
			bean.setDriver("");
			bean.setServer("");
			bean.setUserName("");
			bean.setPassword("");
			bean.setDatabase("");
			bean.setPortNo("");
			bean.setTableName("");
			bean.setEmpFlag("");
			bean.setDateFlag("");
			bean.setWrkHoursFlag("");
			bean.setShiftFlag("");
			bean.setInTimeFlag("");
			bean.setOutTimeFlag("");
			bean.setTimeFlag("");
			bean.setIoFlag("");
			bean.setEmpField("");
			bean.setDateField("");
			bean.setWrkHoursField("");
			bean.setShiftField("");
			bean.setInTimeField("");
			bean.setOutTimeField("");
			bean.setTimeField("");
			bean.setIoField("");			
			bean.setRadioFlag("");
			bean.setJobDuration("");
			bean.setJobDayOfWeek("");
			bean.setJobDayOfMonth("");
			bean.setJobStartTime("");
		} catch(Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
	}
	
	public String save() {
		try {
			String driver = request.getParameter("driver");
			String server = request.getParameter("server");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String database = request.getParameter("database");
			String port = request.getParameter("portNo");
			String tableName = request.getParameter("tableName");
			String jobStartTime = request.getParameter("jobStartTime");
			String jobDuration = request.getParameter("jobDuration");
			String jobDayOfWeek = request.getParameter("jobDayOfWeek");
			String jobDayOfMonth = request.getParameter("jobDayOfMonth");

			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);
			
			String autoUploadID = bean.getAutoUploadID();
			boolean result = false;
			
			/**
			 * Save new record
			 */
			boolean configurationExists=false;
			if(autoUploadID.equals("")) {
				//boolean configurationExists = model.isConfigurationExists(server, "");
				
				if(configurationExists) {
					addActionMessage("Settings are already exists for this server");
					getNavigationPanel(2);
					bean.setEnableAll("Y");
				} else {
					autoUploadID = model.getAutoUploadID();
					bean.setAutoUploadID(autoUploadID);
					
					String message = addUpdateJob("ADD", autoUploadID, jobStartTime, jobDuration, jobDayOfWeek, jobDayOfMonth);
					
					if(message.equalsIgnoreCase("SAVED")) {
						result = model.save(autoUploadID, driver, server, userName, password, database, port, tableName);
						
						if(result) {
							result = model.saveDtl(bean, autoUploadID);
							
							if(result) {
								addActionMessage(getMessage("save"));
								bean.setEnableAll("N");
								getNavigationPanel(3);
							} else {
								addActionMessage(getMessage("save.error"));
								getNavigationPanel(2);
								bean.setEnableAll("Y");
							}
						} else {
							addActionMessage(getMessage("save.error"));
							getNavigationPanel(2);
							bean.setEnableAll("Y");
						}
					} else {
						addActionMessage(getMessage("save.error"));
						getNavigationPanel(2);
						bean.setEnableAll("Y");
					}
				}
			}
			
			/**
			 * Update existing record
			 */
			else {
				
				//boolean configurationExists = model.isConfigurationExists(server, autoUploadID);
				
				if(configurationExists) {
					addActionMessage("Settings are already exists for this server");
					getNavigationPanel(2);
					bean.setEnableAll("Y");
				} else {
					String message = addUpdateJob("UPDATE", autoUploadID, jobStartTime, jobDuration, jobDayOfWeek, jobDayOfMonth);
					
					if(message.equalsIgnoreCase("SAVED")) {
						result = model.update(driver, server, userName, password, database, port, tableName, autoUploadID);
						
						if(result) {
							result = model.saveDtl(bean, autoUploadID);
							
							if(result) {
								addActionMessage(getMessage("save"));								
								bean.setEnableAll("N");
								getNavigationPanel(3);
							} else {
								addActionMessage(getMessage("save.error"));
								getNavigationPanel(2);
								bean.setEnableAll("Y");
							}
						} else {
							addActionMessage(getMessage("save.error"));
							getNavigationPanel(2);
							bean.setEnableAll("Y");
						}
					} 
					
					/*else if(message.equalsIgnoreCase("ACTIVE")) {
						addActionMessage("Record cannot be saved \nbecause the task associated with this record is running. " +
						"\nPlease stop it and try again.");
						bean.setEnableAll("N");
						getNavigationPanel(4);
						
						String jobClient = (String) session.getAttribute("session_pool");
						String jobName = "AutoUploadAttendance_" + autoUploadID;
						
						HashMap<String, String> jobDetails = JobScheduler.getJobDetails(jobClient, jobName, jobGroup);
						
						model.edit(bean, autoUploadID, jobDetails);
					} */
					
					else {
						addActionMessage(getMessage("save.error"));
						getNavigationPanel(2);
						bean.setEnableAll("Y");
					}
				}
			}

			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in save in action:" + e);
			getNavigationPanel(2);
		}
		return "showData";
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(AutoUploadAttendance bean) {
		this.bean = bean;
	}

	public String startScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");

			String autoUploadID = request.getParameter("autoUploadID");
			String jobName = "AutoUploadAttendance_" + autoUploadID;
			
			boolean isJobScheduled = JobScheduler.startJobScheduling(jobClient, jobName, jobGroup);

			if(isJobScheduled) {
				AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
				model.initiate(context, session);
				
				HashMap<String, String> jobDetails = JobScheduler.getJobDetails(jobClient, jobName, jobGroup);
				model.edit(bean, autoUploadID, jobDetails);
				
				model.terminate();
				
				bean.setJobScheduled(true);
				
				bean.setEnableAll("N");
				getNavigationPanel(4);
				addActionMessage("Scheduler started successfully.");
			} else {
				bean.setEnableAll("N");
				getNavigationPanel(3);
				addActionMessage("Scheduler cannot be started.");
			}
		} catch(Exception e) {
			logger.error("Exception in startScheduling in action:" + e);
			addActionMessage("Scheduler cannot be started.");
		}
		return "showData";
	}

	public String stopScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");

			String autoUploadID = request.getParameter("autoUploadID");
			String jobName = "AutoUploadAttendance_" + autoUploadID;

			boolean result = JobScheduler.stopJobScheduling(jobClient, jobName, jobGroup);

			if(result) {
				AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
				model.initiate(context, session);
				
				HashMap<String, String> jobDetails = JobScheduler.getJobDetails(jobClient, jobName, jobGroup);
				model.edit(bean, autoUploadID, jobDetails);
				
				model.terminate();
				
				bean.setJobScheduled(false);
				
				bean.setEnableAll("N");
				getNavigationPanel(3);
				addActionMessage("Scheduler stopped successfully.");
			}
		} catch(Exception e) {
			logger.error("Exception in stopScheduling in action:" + e);
		}
		return "showData";
	}
	
	public String testConnection() {
		try {
			String driver = request.getParameter("driver");
			String server = request.getParameter("server");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String database = request.getParameter("database");
			String portNo = request.getParameter("portNo");
			String tableName = request.getParameter("tableName");
			String fldEmpToken = request.getParameter("empField");
			String fldInTime = request.getParameter("inTimeField");
			String fldOutTime = request.getParameter("outTimeField");
			String fldOneTime = request.getParameter("timeField");
			String fldIOFlag = request.getParameter("ioField");
			String fldWorkHrs = request.getParameter("wrkHoursField");
			String fldShift = request.getParameter("shiftField");
			String fldAttendanceDate = request.getParameter("dateField");
			
			AutoUploadAttendanceModel model = new AutoUploadAttendanceModel();
			model.initiate(context, session);
			
			String message = model.testConnection(driver, server, userName, password, database, portNo, tableName, 
				fldEmpToken, fldInTime, fldOutTime, fldOneTime, fldIOFlag, fldWorkHrs, fldShift, fldAttendanceDate);
			
			model.terminate();
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(message);
			pw.flush();
		} catch(Exception e) {
			logger.error("Exception in testConnection in action:" + e);
		}
		return null;
	}
}