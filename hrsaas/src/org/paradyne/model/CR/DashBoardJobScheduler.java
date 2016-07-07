package org.paradyne.model.CR;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.struts.action.ApplicationStudio.jobScheduling.JobLogger;
import org.struts.action.ApplicationStudio.jobScheduling.ListenerForJob;

import com.opensymphony.xwork2.ActionSupport;

public class DashBoardJobScheduler {
	private static Scheduler scheduler = null;
	private final static String JOB_LIST_FILE = getDataPath() + "\\JobSchedules\\JobList.properties";
	
	public static boolean addJob(String jobClient, String jobName, String jobGroup, String jobClass, String jobStartTime, String jobDuration, 
		String jobDayOfWeek, String jobDayOfMonth) {
		boolean isJobAdded = false;
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);
			
			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList(jobClient, jobProperties);
			
			String job = "";
			
			/**
			 * Create an JOB ID
			 */
			if(jobList.size() > 0) {
				Collections.sort(jobList);
				
				int lastJobNo = jobList.get(jobList.size() - 1);
				job = "JOB-" + (lastJobNo + 1);
			} else {
				job = "JOB-1";
			}
			
			/**
			 * Add job properties into Job List
			 */
			jobProperties.setProperty(job, jobClient);
			jobProperties.setProperty(job + ".NAME", jobName);
			jobProperties.setProperty(job + ".GROUP", jobGroup);
			jobProperties.setProperty(job + ".CLASS", jobClass);
			jobProperties.setProperty(job + ".DURATION", jobDuration);
			jobProperties.setProperty(job + ".DAY_OF_WEEK", jobDayOfWeek);
			jobProperties.setProperty(job + ".DAY_OF_MONTH", jobDayOfMonth);
			jobProperties.setProperty(job + ".START_TIME", jobStartTime);
			jobProperties.setProperty(job + ".ACTIVE", "No");
			
			jobProperties.store(new FileOutputStream(JOB_LIST_FILE), null);
			
			isJobAdded = true;
			JobLogger.info(jobClient, "Job for " + jobName + " is added in Job List.");
		} catch(Exception e) {
			JobLogger.error(jobClient, "Job for " + jobName + " cannot be added in Job List.");
			JobLogger.error(jobClient, "Exception in addJob in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
			e.printStackTrace();
		}
		return isJobAdded;
	}
	
	private static void addJobDataMap(String jobClient, String jobName, String jobGroup, String jobClass, String jobDuration, String jobDayOfWeek, 
		int jobDayOfMonth, String jobStartTime, String jobActive, JobDetail jobDetail, Trigger trigger) {
		try {
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("CLIENT", jobClient);
			jobDataMap.put("NAME", jobName);
			jobDataMap.put("GROUP", jobGroup);
			jobDataMap.put("CLASS", jobClass);
			jobDataMap.put("DURATION", jobDuration);
			jobDataMap.put("DAY_OF_WEEK", jobDayOfWeek);
			jobDataMap.put("DAY_OF_MONTH", jobDayOfMonth);
			jobDataMap.put("START_TIME", jobStartTime);
			jobDataMap.put("ACTIVE", jobActive);
			
			jobDetail.setJobDataMap(jobDataMap);
			trigger.setJobDataMap(jobDataMap);
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in addJobDataMap in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
	}
	
	private static void addListeners(String jobClient, JobDetail jobDetail, Trigger trigger) {
		try {
			
			ListenerForJob jobListener = new ListenerForJob();
			jobDetail.addJobListener(jobListener.getName());
			scheduler.addJobListener(jobListener);
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in addListeners in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
	}
	
	public static boolean deleteJob(String jobClient, String jobName, String jobGroup) {
		boolean isJobDeleted = false;
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);
			
			/**
			 * Get list of JOB IDs
			 */
			Enumeration<Object> jobKeyList = jobProperties.keys();
			
			/**
			 * Delete a job from Job List
			 */
			while(jobKeyList.hasMoreElements()) {
				String jobFromFile = (String)jobKeyList.nextElement();
				
				if(!jobFromFile.contains(".")) {
					String propClient = jobProperties.getProperty(jobFromFile);
					String propJobName = jobProperties.getProperty(jobFromFile + ".NAME");
					String propJobGroup = jobProperties.getProperty(jobFromFile + ".GROUP");
					
					// find out a job according to client, name and group
					if(propClient.equalsIgnoreCase(jobClient) && propJobName.equalsIgnoreCase(jobName) && propJobGroup.equalsIgnoreCase(jobGroup)) {
						isJobDeleted = deleteJobFromScheduler(jobClient, jobName, jobGroup);
						
						if(isJobDeleted) {
							jobProperties.remove(jobFromFile);
							jobProperties.remove(jobFromFile + ".NAME");
							jobProperties.remove(jobFromFile + ".GROUP");
							jobProperties.remove(jobFromFile + ".CLASS");
							jobProperties.remove(jobFromFile + ".DURATION");
							jobProperties.remove(jobFromFile + ".DAY_OF_WEEK");
							jobProperties.remove(jobFromFile + ".DAY_OF_MONTH");
							jobProperties.remove(jobFromFile + ".START_TIME");
							jobProperties.remove(jobFromFile + ".ACTIVE");
							
							jobProperties.store(new FileOutputStream(JOB_LIST_FILE), null);
						}
						
						break;
					}
				}
			}
			
			if(isJobDeleted) {
				JobLogger.info(jobClient, "Job for " + jobName + " is deleted from Job List.");
			} else {
				JobLogger.error(jobClient, "Job for " + jobName + " cannot be deleted from Job List.");
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Job for " + jobName + " cannot be deleted from Job List.");
			JobLogger.error(jobClient, "Exception in deleteJob in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
			e.printStackTrace();
		}
		return isJobDeleted;
	}
	
	private static boolean deleteJobFromScheduler(String jobClient, String jobName, String jobGroup) {
		boolean isJobDeleted = false;
		try {
			// Job name in scheduler
			String jobFromScheduler = jobClient + "@" + jobName;
			
			/**
			 * Get job details from scheduler. If not available that means job doesn't exists in scheduler. Otherwise delete it from scheduler.
			 */
			JobDetail jobDetail = scheduler.getJobDetail(jobFromScheduler, jobGroup);
			
			if(jobDetail == null) {
				isJobDeleted = true;
			} else {
				isJobDeleted = scheduler.deleteJob(jobFromScheduler, jobGroup);
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in deleteTheJob in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return isJobDeleted;
	}
	
	public static String getDataPath() {
		String path = "";
		try {
			ActionSupport actionSupport = new ActionSupport();			
			ResourceBundle rb = ResourceBundle.getBundle("globalMessages");
			path = rb.getString("data_path");
			System.out.println("getDataPath in job schedule"+path);
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in getDataPath in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
		return path;
	}

	private static int getDayOfWeek(String weekDay) {
		int dayOfWeek = 1;
		try {
			if(weekDay.equalsIgnoreCase("Sunday")) { dayOfWeek = 1; }
			else if(weekDay.equalsIgnoreCase("Monday")) { dayOfWeek = 2; }
			else if(weekDay.equalsIgnoreCase("Tuesday")) { dayOfWeek = 3; }
			else if(weekDay.equalsIgnoreCase("Wednesday")) { dayOfWeek = 4; }
			else if(weekDay.equalsIgnoreCase("Thursday")) { dayOfWeek = 5; }
			else if(weekDay.equalsIgnoreCase("Friday")) { dayOfWeek = 6; }
			else if(weekDay.equalsIgnoreCase("Saturday")) { dayOfWeek = 7; }
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in getDayOfWeek in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
		return dayOfWeek;
	}
	
	public static HashMap<String, String> getJobDetails(String jobClient, String jobName, String jobGroup) {
		HashMap<String, String> jobDetails = new HashMap<String, String>();
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);

			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList(jobClient, jobProperties);
			
			/**
			 * Get job properties from a Job List
			 */
			if(jobList.size() > 0) {
				for(int i = 0; i < jobList.size(); i++) {
					String propClient = jobProperties.getProperty("JOB-" + jobList.get(i));
					String propJobName = jobProperties.getProperty("JOB-" + jobList.get(i) + ".NAME");
					String propJobGroup = jobProperties.getProperty("JOB-" + jobList.get(i) + ".GROUP");
					String propJobClass = jobProperties.getProperty("JOB-" + jobList.get(i) + ".CLASS");
					String propJobDuration = jobProperties.getProperty("JOB-" + jobList.get(i) + ".DURATION");
					String propJobDayOfWeek = jobProperties.getProperty("JOB-" + jobList.get(i) + ".DAY_OF_WEEK");
					String propJobDayOfMonth = jobProperties.getProperty("JOB-" + jobList.get(i) + ".DAY_OF_MONTH");
					String propJobStartTime = jobProperties.getProperty("JOB-" + jobList.get(i) + ".START_TIME");
					String propJobActive = jobProperties.getProperty("JOB-" + jobList.get(i) + ".ACTIVE");
					
					if(propClient.equalsIgnoreCase(jobClient) && propJobName.equalsIgnoreCase(jobName) && propJobGroup.equalsIgnoreCase(jobGroup)) {
						jobDetails.put("CLIENT", propClient);
						jobDetails.put("NAME", propJobName);
						jobDetails.put("GROUP", propJobGroup);
						jobDetails.put("CLASS", propJobClass);
						jobDetails.put("DURATION", propJobDuration);
						jobDetails.put("DAY_OF_WEEK", propJobDayOfWeek);
						jobDetails.put("DAY_OF_MONTH", propJobDayOfMonth);
						jobDetails.put("START_TIME", propJobStartTime);
						jobDetails.put("ACTIVE", propJobActive);
						
						break;
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in getJobDetails in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return jobDetails;
	}

	private static ArrayList<Integer> getJobList(String jobClient, Properties jobProperties) {
		ArrayList<Integer> jobList = new ArrayList<Integer>();
		try {
			/**
			 * Get list of JOB IDs
			 */
			Enumeration<Object> jobKeyList = jobProperties.keys();
			
			/**
			 * Get unique JOB Ids
			 */
			while(jobKeyList.hasMoreElements()) {
				String job = (String)jobKeyList.nextElement();
				
				if(!job.contains(".")) {
					int jobNo = Integer.parseInt(job.split("-")[1]);
					jobList.add(jobNo);
				}
			}
			
			/**
			 * Sort JOB Ids ascendingly
			 */
			if(jobList.size() > 0) {
				Collections.sort(jobList);
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in getJobList in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return jobList;
	}
	
	public static boolean isJobActive(String jobClient, String jobName, String jobGroup) {
		boolean jobActive = false;
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);

			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList(jobClient, jobProperties);
			
			/**
			 * Check whether a job is active in scheduler or not.
			 */
			if(scheduler != null && scheduler.isStarted()) {
				if(jobList.size() > 0) {
					for(int i = 0; i < jobList.size(); i++) {
						String propClient = jobProperties.getProperty("JOB-" + jobList.get(i));
						String propJobName = jobProperties.getProperty("JOB-" + jobList.get(i) + ".NAME");
						String propJobGroup = jobProperties.getProperty("JOB-" + jobList.get(i) + ".GROUP");

						// find out a job according to client, name and group
						if(propClient.equalsIgnoreCase(jobClient) && propJobName.equalsIgnoreCase(jobName) && propJobGroup.equalsIgnoreCase(jobGroup)) {
							String propJobActive = jobProperties.getProperty("JOB-" + jobList.get(i) + ".ACTIVE");

							if(propJobActive.equalsIgnoreCase("Yes")) {
								jobActive = true;
								break;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in isJobActive in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return jobActive;
	}
	
	public static boolean isSchedulerStarted() {
		boolean schedulerStarted = false;
		try {
			/**
			 * Check whether a scheduler is started or not.
			 */
			if(scheduler != null) {
				schedulerStarted = scheduler.isStarted();
			}
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in isSchedulerStarted in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
		return schedulerStarted;
	}

	public static void main(String[] args) {
		try {
			startTheScheduler();
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in main in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
	}

	private static Properties readPropertyFile(String jobClient) {
		Properties jobProperties = null;
		try {
			jobProperties = new Properties();
			FileInputStream jobFileInputStream = new FileInputStream(JOB_LIST_FILE);
			jobProperties.load(jobFileInputStream);
			jobFileInputStream.close();
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in readPropertyFile in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return jobProperties;
	}
	
	private static boolean scheduleTheJob(String jobName, Properties jobProperties, String job) {
		boolean isJobScheduled = false;
		String jobClient = "";
		try {
			/**
			 * Get job properties from Job List
			 */
			String jobGroup = "", jobClass = "", jobDuration = "", jobDayOfWeek = "", dayOfMonth = "", jobStartTime = "", jobActive = "";
			int jobDayOfMonth = 0;
			
			jobClient = jobProperties.getProperty(job);
			jobGroup = jobProperties.getProperty(job + ".GROUP");
			jobClass = jobProperties.getProperty(job + ".CLASS");
			jobDuration = jobProperties.getProperty(job + ".DURATION");
			jobDayOfWeek = jobProperties.getProperty(job + ".DAY_OF_WEEK");
			dayOfMonth = jobProperties.getProperty(job + ".DAY_OF_MONTH");
			if(!(dayOfMonth.equals("") || dayOfMonth.equals("null") || dayOfMonth.equals(null) || dayOfMonth == null)) {
				jobDayOfMonth = Integer.parseInt(dayOfMonth);
			}
			jobStartTime = jobProperties.getProperty(job + ".START_TIME");
			jobActive = jobProperties.getProperty(job + ".ACTIVE");

			/**
			 * Create a job
			 */
			String jobForScheduler = jobClient + "@" + jobName;
			JobDetail jobDetail = new JobDetail(jobForScheduler, jobGroup, Class.forName(jobClass));
			
			Trigger trigger = null;
			
			/**
			 * Schedule job Secondly
			 */
			if(jobDuration.equalsIgnoreCase("Secondly")) {
				trigger = TriggerUtils.makeSecondlyTrigger("TRIGGER_" + jobForScheduler);
			}
			
			/**
			 * Schedule job Minutely
			 */
			else if(jobDuration.equalsIgnoreCase("Minutely")) {
				trigger = TriggerUtils.makeMinutelyTrigger("TRIGGER_" + jobForScheduler);
			}
			
			/**
			 * Schedule job Hourly
			 */
			else if(jobDuration.equalsIgnoreCase("Hourly")) {
				trigger = TriggerUtils.makeHourlyTrigger("TRIGGER_" + jobForScheduler);
			}
			
			/**
			 * Schedule job Daily
			 */
			else if(jobDuration.equalsIgnoreCase("Daily")) {
				String[] startTime = jobStartTime.split(":");
				int hour = Integer.parseInt(startTime[0]);
				int minute = Integer.parseInt(startTime[1]);
				
				trigger = TriggerUtils.makeDailyTrigger("TRIGGER_" + jobForScheduler, hour, minute);
			}
			
			/**
			 * Schedule job Weekly
			 */
			else if(jobDuration.equalsIgnoreCase("Weekly")) {
				int dayOfWeek = getDayOfWeek(jobDayOfWeek);
				
				String[] startTime = jobStartTime.split(":");
				int hour = Integer.parseInt(startTime[0]);
				int minute = Integer.parseInt(startTime[1]);
				
				trigger = TriggerUtils.makeWeeklyTrigger("TRIGGER_" + jobForScheduler, dayOfWeek, hour, minute);
			}
			
			/**
			 * Schedule job Monthly
			 */
			else if(jobDuration.equalsIgnoreCase("Monthly")) {
				String[] startTime = jobStartTime.split(":");
				int hour = Integer.parseInt(startTime[0]);
				int minute = Integer.parseInt(startTime[1]);

				trigger = TriggerUtils.makeMonthlyTrigger("TRIGGER_" + jobForScheduler, jobDayOfMonth, hour, minute);
			}
			
			/**
			 * Add listeners
			 */
			addListeners(jobClient, jobDetail, trigger);
			
			/**
			 * Add job details in datamap
			 */
			addJobDataMap(jobClient, jobName, jobGroup, jobClass, jobDuration, jobDayOfWeek, jobDayOfMonth, jobStartTime, jobActive, jobDetail, trigger);
			
			/**
			 * Schedule the job
			 */
			scheduler.scheduleJob(jobDetail, trigger);
			
			JobLogger.info(jobClient, "Job scheduled for " + jobName);
			
			isJobScheduled = true;
		} catch(Exception e) {
			JobLogger.error(jobClient, "Exception in scheduleTheJob in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return isJobScheduled;
	}
	
	public static boolean startJobScheduling(String jobClient, String jobName, String jobGroup) {
		boolean isJobStarted = false;
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);

			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList(jobClient, jobProperties);
			
			/**
			 * Find out a job and schedule it
			 */
			String job = "";
			
			if(jobList.size() > 0) {
				for(int i = 0; i < jobList.size(); i++) {
					String propClient = jobProperties.getProperty("JOB-" + jobList.get(i));
					String propJobName = jobProperties.getProperty("JOB-" + jobList.get(i) + ".NAME");
					String propJobGroup = jobProperties.getProperty("JOB-" + jobList.get(i) + ".GROUP");
					
					// find out a job according to client, name and group
					if(propClient.equalsIgnoreCase(jobClient) && propJobName.equalsIgnoreCase(jobName) && propJobGroup.equalsIgnoreCase(jobGroup)) {
						job = "JOB-" + jobList.get(i);
						break;
					}
				}

				/**
				 * Schedule a job
				 */
				if(!job.equals("")) {
					if(scheduler != null && scheduler.isStarted()) {
						isJobStarted = scheduleTheJob(jobName, jobProperties, job);
						
						if(isJobStarted) {
							jobProperties.setProperty(job + ".ACTIVE", "Yes");
							jobProperties.store(new FileOutputStream(JOB_LIST_FILE), null);
						}
					} else {
						JobLogger.error(jobClient, "Scheduler not started");
					}
				}
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Job for " + jobName + " cannot be started.");
			JobLogger.error(jobClient, "Exception in startJobScheduling in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return isJobStarted;
	}

	public static void startTheScheduler() {
		try {
			/**
			 * Configure a scheduler
			 */
			if(scheduler == null) {
				SchedulerFactory schedulerFactory = new StdSchedulerFactory();
				scheduler = schedulerFactory.getScheduler();
			}

			/**
			 * Start a scheduler
			 */
			scheduler.start();

			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile("Default");

			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList("Default", jobProperties);
			
			/**
			 * Reschedule the job which are not active currently and previously they were active, when scheduler was stopped
			 */
			if(jobList.size() > 0) {
				for(int i = 0; i < jobList.size(); i++) {
					String job = "JOB-" + jobList.get(i);
					String propJobClient = jobProperties.getProperty(job);
					String propJobName = jobProperties.getProperty(job + ".NAME");
					String propActive = jobProperties.getProperty(job + ".ACTIVE");
					
					/**
					 * Reschedule a job which was active previously
					 */
					if(propActive.equalsIgnoreCase("Yes")) {
						boolean isJobScheduled = scheduleTheJob(propJobName, jobProperties, job);
						
						if(isJobScheduled) {
							JobLogger.info(propJobClient, "Job for " + propJobName + " is re-started as scheduler re-started.");
						}
					}
				}
			}
			JobLogger.info("Default", "Scheduler started successfully.");
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in startTheScheduler in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
	}

	public static boolean stopJobScheduling(String jobClient, String jobName, String jobGroup) {
		boolean isJobStopped = false;
		try {
			/**
			 * Read job properties from Job List
			 */
			Properties jobProperties = readPropertyFile(jobClient);

			/**
			 * Get list of jobs
			 */
			ArrayList<Integer> jobList = getJobList(jobClient, jobProperties);

			String job = "";

			/**
			 * Find out a job and delete it from scheduler
			 */
			if(jobList.size() > 0) {
				for(int i = 0; i < jobList.size(); i++) {
					String propClient = jobProperties.getProperty("JOB-" + jobList.get(i));
					String propJobName = jobProperties.getProperty("JOB-" + jobList.get(i) + ".NAME");
					String propJobGroup = jobProperties.getProperty("JOB-" + jobList.get(i) + ".GROUP");
					
					if(propClient.equalsIgnoreCase(jobClient) && propJobName.equalsIgnoreCase(jobName) && propJobGroup.equalsIgnoreCase(jobGroup)) {
						job = "JOB-" + jobList.get(i);
						break;
					}
				}
				
				/**
				 * Delete a job from scheduler
				 */
				if(!job.equals("")) {
					isJobStopped = deleteJobFromScheduler(jobClient, jobName, jobGroup);
					
					if(isJobStopped) {
						jobProperties.setProperty(job + ".ACTIVE", "No");
						jobProperties.store(new FileOutputStream(JOB_LIST_FILE), null);
					}
				}
			}
			
			if(isJobStopped) {
				JobLogger.info(jobClient, "Job for " + jobName + " is stopped.");
			} else {
				JobLogger.error(jobClient, "Job for " + jobName + " cannot be stopped.");
			}
		} catch(Exception e) {
			JobLogger.error(jobClient, "Job for " + jobName + " cannot be stopped.");
			JobLogger.error(jobClient, "Exception in startJobScheduling in JobScheduler-" + e);
			JobLogger.printStackTrace(jobClient, e);
		}
		return isJobStopped;
	}
	
	public static void stopTheScheduler() {
		try {
			/**
			 * Stop the scheduler
			 */
			scheduler.shutdown();
			
			JobLogger.info("Default", "Scheduler stopped successfully.");
		} catch(Exception e) {
			JobLogger.error("Default", "Exception in stopTheScheduler in JobScheduler-" + e);
			JobLogger.printStackTrace("Default", e);
		}
	}
}
