/* Bhushan Dasare Mar 9, 2010 */

package org.struts.action.ApplicationStudio.jobScheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class ListenerForJob implements JobListener {
	/* (non-Javadoc)
	 * @see org.quartz.JobListener#getName()
	 */
	public String getName() {
		return "ListenerForJob";
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
		String client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
		String jobName = jobExecutionContext.getJobDetail().getJobDataMap().getString("NAME");
		String textToWrite = "Trigger has fired and job is about to execute for " + jobName;
		
		JobLogger.info(client, textToWrite);
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 */
	public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
		String client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
		String jobName = jobExecutionContext.getJobDetail().getJobDataMap().getString("NAME");
		String textToWrite = "Job to be executed for " + jobName;
		
		JobLogger.info(client, textToWrite);
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 */
	public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException jobExecutionException) {
		String client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
		String jobName = jobExecutionContext.getJobDetail().getJobDataMap().getString("NAME");
		String textToWrite = "Job was executed for " + jobName;
		
		JobLogger.info(client, textToWrite);
	}
}