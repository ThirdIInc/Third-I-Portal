package org.paradyne.model.common;

import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.common.TaskReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * 
 * @author vishwambhard
 * modified by Priyanka. Kumbhar
 */
public class TaskReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TaskReportModel.class);

	/**
	 * THIS METHOD IS USED FOR GETTING TASK TYPE
	 * 
	 * @param taskReport
	 */
	public void getTaskTypeList(TaskReport taskReport) {
		// TODO Auto-generated method stub

		TreeMap map = new TreeMap();
		String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE ORDER BY TYPE_CODE ";

		Object[][] data = getSqlModel().getSingleResult(sql);

		if (data.length > 0) {
			for (int j = 0; j < data.length; j++) {

				map.put(checkNull(String.valueOf(data[j][0])), checkNull(String
						.valueOf(data[j][1])));

			}// end of for
		}// end of if
		taskReport.setTmap(map);
	}// end of getTaskTypeList

	/**
	 * THIS METHOD IS USED FOR GETTING PROJECT TYPE
	 * 
	 * @param taskReport
	 */
	public void getTaskProjectList(TaskReport taskReport) {
		// TODO Auto-generated method stub

		TreeMap map = new TreeMap();
		String sql = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT ORDER BY PROJECT_CODE ";

		Object[][] data = getSqlModel().getSingleResult(sql);

		if (data.length > 0) {
			for (int j = 0; j < data.length; j++) {

				map.put(checkNull(String.valueOf(data[j][0])), checkNull(String
						.valueOf(data[j][1])));

			}// end of for
		}// end of if
		taskReport.setTmap1(map);
	}// end of getTaskProjectList

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param taskReport
	 * @param response
	 */
	public void getReport(TaskReport taskReport, HttpServletResponse response) {
		try {
			// TODO Auto-generated method stub
			String reportName = "Task Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					taskReport.getReportType(), reportName, "A4");
			// rg.addText("Leave Application Mis Report", 0, 0, 0);
			rg.addTextBold("Task Report", 0, 1, 0);
			//rg.addTextBold("\n", 0, 1, 0);

			String taskTypeQuery = "SELECT TYPE_NAME FROM HRMS_TASK_TYPE WHERE TYPE_CODE="
					+ taskReport.getTaskType();

			Object[][] taskTypeObj = getSqlModel().getSingleResult(
					taskTypeQuery);

			if (!(taskReport.getTaskType().equals(""))) {
				rg.addTextBold("Task Type : "
						+ String.valueOf(taskTypeObj[0][0]), 0, 0, 0);
			}// end of if

			String taskProjectQuery = "SELECT PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE PROJECT_CODE="
					+ taskReport.getTaskProject();

			Object[][] taskProjectObj = getSqlModel().getSingleResult(
					taskProjectQuery);

			if (!(taskReport.getTaskProject().equals(""))) {
				rg.addTextBold("Task Project : "
						+ String.valueOf(taskProjectObj[0][0]), 0, 0, 0);
			}// end of if

			if (!(taskReport.getAppFromDate().equals(""))) {
				rg.addTextBold("From Date : " + taskReport.getAppFromDate(), 0,
						0, 0);
			}// end of if
			if (!(taskReport.getAppToDate().equals(""))) {
				rg.addTextBold("To Date : " + taskReport.getAppToDate(), 0, 0,
						0);
			}// end of if
			if (!(taskReport.getEmpName().equals(""))) {
				rg
						.addTextBold("Employee : " + taskReport.getEmpName(),
								0, 0, 0);
			}// end of if

			String sql = " SELECT HRMS_TASK_PROJECT.PROJECT_NAME,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),"
					+ "  HRMS_TASK_TYPE.TYPE_NAME,TO_CHAR(TASK_START_DATE,'DD-MM-YYYY'),TO_CHAR(TASK_END_DATE,'DD-MM-YYYY') "
					+ " ,NVL(TASK_DESC,''),DECODE(HRMS_TASK_HDR.TASK_STATUS,'O','Open','Close') "
					+ " FROM HRMS_TASK_HDR "
					+ " INNER JOIN  HRMS_TASK_TYPE ON(HRMS_TASK_TYPE.TYPE_CODE=HRMS_TASK_HDR.TASK_TYPE)"
					+ "INNER JOIN HRMS_TASK_PROJECT ON(HRMS_TASK_PROJECT.PROJECT_CODE=HRMS_TASK_HDR.TASK_PROJECT) "
					+ "INNER JOIN HRMS_TASK_DTL ON (HRMS_TASK_DTL.TASK_ID=HRMS_TASK_HDR.TASK_ID)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_DTL.TASK_ASSIGN_TO) "
					+ " WHERE 1=1 ";

			if (taskReport.getTaskType() != null
					&& taskReport.getTaskType().length() > 0) {
				sql += " AND HRMS_TASK_HDR.TASK_TYPE="
						+ taskReport.getTaskType() + "  ";
			}// end of if
			if (taskReport.getTaskProject() != null
					&& taskReport.getTaskProject().length() > 0) {
				sql += " AND HRMS_TASK_HDR.TASK_PROJECT="
						+ taskReport.getTaskProject() + "  ";
			}// end of if
			if (taskReport.getAppFromDate() != null
					&& taskReport.getAppFromDate().length() > 0) {
				sql += "AND TASK_START_DATE >=TO_DATE('"
				        + taskReport.getAppFromDate() + "','DD-MM-YYYY') "; 
			}	
			if (taskReport.getAppToDate() != null
					&& taskReport.getAppToDate().length() > 0)
			{	
				 sql +=	" AND TASK_END_DATE <=TO_DATE('"
						+ taskReport.getAppToDate() + "','DD-MM-YYYY') ";
						
			}// end of if
			if (taskReport.getEmpCode() != null
					&& taskReport.getEmpCode().length() > 0) {
				sql += "  AND HRMS_TASK_DTL.TASK_ASSIGN_TO ="
						+ taskReport.getEmpCode();

			}// end of if

			logger.info("value of sql query----------------------------" + sql);

			Object taskData[][] = getSqlModel().getSingleResult(sql);
			Object[][] finalLeaveData = new Object[taskData.length][8];
			int s = 1;
			for (int i = 0; i < taskData.length; i++) {
				finalLeaveData[i][0] = s++; // sr no
				finalLeaveData[i][1] = taskData[i][0];// task project
				finalLeaveData[i][2] = taskData[i][1];// employee
				finalLeaveData[i][3] = taskData[i][2];// task type
				finalLeaveData[i][4] = taskData[i][3];// from date
				finalLeaveData[i][5] = taskData[i][4];// to date
				/*try {
					finalLeaveData[i][6] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(taskData[i][5])));// work in  hrs.
				} catch (Exception e) {
					// TODO: handle exception
					finalLeaveData[i][6]="0";
				}*/
				finalLeaveData[i][6] = taskData[i][5];
				finalLeaveData[i][7] = taskData[i][6];// to date
																		

			}// end of for
			if (finalLeaveData != null && finalLeaveData.length > 0) {
				String[] colNames = { "Sr. No.", "Task Project",
						"Employee Name", "Task Type", "From Date", "To Date",
						"Description","Status" };
				int[] cellWidth = { 20,  50,  40,  30,  40,  40,  20,  15};
				int[] alignment = { 0, 0, 0, 0, 0, 0, 0,0};
                rg.addFormatedText("\n", 0, 0, 1,0);
				rg.tableBody(colNames, finalLeaveData, cellWidth, alignment);

			}// end of if
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// end of else
			rg.createReport(response);
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getReport------------------------" + e);
			e.printStackTrace();
		}// end of catch

	}// end of getReport
}// end of class
