/**
 * 
 */
package org.paradyne.model.exam;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.exam.CandidateScheduleReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author diptip
 *
 */
public class CandidateScheduleReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * 
	 */
	public void  getReport(CandidateScheduleReport candrpt,HttpServletResponse response)
	{
		logger.info("report..!");
		logger.info("paperCode.!"+candrpt.getPaperCode());
		logger.info("fromDate"+candrpt.getFromDate()+"todate"+candrpt.getToDate());
		try {
			String reportName = "Candidates Schedule Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", reportName);
			String query = " SELECT CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME,TO_char(SCHEDULE_DATE,'dd-mm-yyyy'),SCHEDULE_TIME,PAPER_NAME"
					+ " FROM HRMS_TEST_SCHEDULE "
					+ "LEFT JOIN HRMS_CANDIDATE_HDR ON(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE)"
					+" LEFT JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_TEST_SCHEDULE.SCHEDULE_PAPER)";
			
			if (!candrpt.getFromDate().equals("")) {
				query += " WHERE  SCHEDULE_DATE between to_date('"
						+ candrpt.getFromDate()
						+ "', 'DD-MM-YYYY') AND to_date('"
						+ candrpt.getToDate() + "', 'DD-MM-YYYY')  ";
			}
			if (!candrpt.getPaperCode().equals("")) {
				query += " AND PAPER_CODE=" + candrpt.getPaperCode();
				rg.addFormatedText("Paper: " + candrpt.getPaperName(), 1, 0, 1, 0);
			}
			
			query += " ORDER BY HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE";
			Object[][] Data = getSqlModel().getSingleResult(query);
			if (Data != null && Data.length > 0) {
				logger.info("query............."+ query);
				logger.info("data..............."
						+ String.valueOf(Data[0][0]));

				String abc[] = { "Candidate Name", "Schedule Date",
						"Schedule Time", "Schedule Paper" };
				int cellwidth[] = {20, 12,12,20 };

				int alignment[] = { 0, 1, 1, 0 };
				//rg.addText("Candidates Schedule Report",1,1,0);
				rg.addFormatedText("Candidates Schedule Report", 1,0,1,0);
				rg.addText("\n",0,0,0);
				rg.tableBody(abc, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
