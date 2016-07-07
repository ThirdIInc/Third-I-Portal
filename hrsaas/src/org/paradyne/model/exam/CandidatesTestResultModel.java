/**
 * 
 */
package org.paradyne.model.exam;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.exam.CandidatesTestResult;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author diptip
 *
 */
public class CandidatesTestResultModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * 
	 */
	public CandidatesTestResultModel() {
		// TODO Auto-generated constructor stub
	}
public void testData(CandidatesTestResult ctres, HttpServletRequest request) {
				
	String query = "SELECT CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME,to_char(TEST_DATE,'dd-mm-yyyy'),"+
	 "TEST_TIME_START, TEST_SCORE_TOTAL, TEST_RESULT,HRMS_PAPER_HDR.PAPER_NAME FROM HRMS_ONLINETEST_HDR "+
	 "LEFT JOIN HRMS_CANDIDATE_HDR on(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_ONLINETEST_HDR.CANDIDATE_CODE ) "+
	"LEFT JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_ONLINETEST_HDR.TEST_PAPER_CODE )";
		if (!ctres.getFromDate().equals("")) {
	query += " WHERE  TEST_DATE between to_date('"
			+ ctres.getFromDate()
			+ "', 'DD-MM-YYYY') AND to_date('" + ctres.getToDate()
			+ "', 'DD-MM-YYYY')  ";
	}
	if (!ctres.getPaperCode().equals("")) {
	query += " AND PAPER_CODE=" + ctres.getPaperCode();
	}
	if (!ctres.getLcutoff().equals("")) {
	query += " AND TEST_SCORE_TOTAL between "+ctres.getLcutoff()+" AND "+ctres.getUcutoff();
	}
	
	query += " ORDER BY HRMS_ONLINETEST_HDR.CANDIDATE_CODE";
	Object[][] Data = getSqlModel().getSingleResult(query);
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> list1=new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			CandidatesTestResult bean1=new CandidatesTestResult();
			bean1.setCandidateName(String.valueOf(data[i][0]));
			bean1.setTestDate(String.valueOf(data[i][1]));
			bean1.setTestTime(String.valueOf(data[i][2]));
			bean1.setTotScore(String.valueOf(data[i][3]));
			bean1.setResult(String.valueOf(data[i][4]));
			
			list1.add(bean1);
		}
		ctres.setList(list1);
		
	}

	public void getReport(CandidatesTestResult ctres,HttpServletResponse response) {
		try {
			// TODO Auto-generated method stub
			String reportName = "Candidates Test Result";
			logger.info(">>>>>>>>>");
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", reportName);
			String query = "SELECT CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME,to_char(TEST_DATE,'dd-mm-yyyy'),"+
					 "TEST_TIME_START, TEST_SCORE_TOTAL, TEST_RESULT,HRMS_PAPER_HDR.PAPER_NAME FROM HRMS_ONLINETEST_HDR "+
					 "LEFT JOIN HRMS_CANDIDATE_HDR on(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_ONLINETEST_HDR.CANDIDATE_CODE ) "+
					"LEFT JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_ONLINETEST_HDR.TEST_PAPER_CODE )";
			if (!ctres.getFromDate().equals("")) {
				query += " WHERE  TEST_DATE between to_date('"
						+ ctres.getFromDate()
						+ "', 'DD-MM-YYYY') AND to_date('" + ctres.getToDate()
						+ "', 'DD-MM-YYYY')  ";
			}
			if (!ctres.getPaperCode().equals("")) {
				query += " AND PAPER_CODE=" + ctres.getPaperCode();
			}
			if (!ctres.getLcutoff().equals("")) {
				query += " AND TEST_SCORE_TOTAL between "+ctres.getLcutoff()+" AND "+ctres.getUcutoff();
			}
				rg.addFormatedText("Paper: " + ctres.getPaperName(), 1, 0, 1, 0);
				query += " ORDER BY HRMS_ONLINETEST_HDR.CANDIDATE_CODE";
				Object[][] Data = getSqlModel().getSingleResult(query);
				if (Data != null && Data.length > 0) {
					logger.info("query............." + query);
					logger.info("data..............."
							+ String.valueOf(Data[0][0]));

					String abc[] = { "Candidate Name", "Test Date",
							"Test Time", "Total Score", "Result" };
					int cellwidth[] = { 20, 12, 12, 12, 20 };

					int alignment[] = { 0, 1, 1, 1, 0 };
					//rg.addText("Candidates Schedule Report",1,1,0);
					rg.addFormatedText("Candidates Test Result Report", 1, 0, 1, 0);
					rg.addText("\n", 0, 0, 0);
					rg.tableBody(abc, Data, cellwidth, alignment);
				} else {
					rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
				}
				rg.createReport(response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	
		
	}

	

}
