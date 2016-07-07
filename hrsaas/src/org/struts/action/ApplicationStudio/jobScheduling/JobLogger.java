/* Bhushan Dasare Apr 6, 2010 */

package org.struts.action.ApplicationStudio.jobScheduling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class JobLogger {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JobLogger.class);
	
	public static void error(String jobClient, String textToWrite) {
		try {
			//writeLogs(jobClient, textToWrite, "ERROR");
		} catch(Exception e) {
			logger.error("Exception in error:" + e);
		}
	}
	
	public static void info(String jobClient, String textToWrite) {
		try {
			//writeLogs(jobClient, textToWrite, "INFO");
		} catch(Exception e) {
			logger.error("Exception in info:" + e);
		}
	}
	
	public static void printStackTrace(String jobClient, Exception excp) {
		try {
			StringWriter stringWriter = new StringWriter();
			excp.printStackTrace(new PrintWriter(stringWriter));
			String stackTrace = stringWriter.toString();
			
			error(jobClient, stackTrace);
		} catch(Exception e) {
			logger.error("Exception in printStackTrace:" + e);
			e.printStackTrace();
		}
	}

	private static void writeLogs(String jobClient, String textToWrite, String logType) {
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			if(jobClient == null || jobClient.equals(null) || jobClient.equals("") || jobClient.equals("null")) {
				jobClient = "Default";
			}
			
			String folderPath = JobScheduler.getDataPath() + "\\JobSchedules\\" + jobClient + "\\logs\\";
			file = new File(folderPath + "jobSchedules_" + currentDate + ".log");
			
			if(!file.exists()) {
				new File(folderPath).mkdirs();
			}
			
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
			
			bufferedWriter.write(currentTime + " " + logType + " :" + textToWrite);
			bufferedWriter.newLine();
		} catch(Exception e) {
			logger.error("Exception in writeLogs:" + e);
		} finally {
			try {
				file = null;
				
				if(bufferedWriter != null) {
					bufferedWriter.close();
					bufferedWriter = null;
				}
				
				if(fileWriter != null) {
					fileWriter.close();
					fileWriter = null;
				}
			} catch(Exception e) {
				logger.error("Exception in closing file objects:" + e);
			}
		}
	}
}