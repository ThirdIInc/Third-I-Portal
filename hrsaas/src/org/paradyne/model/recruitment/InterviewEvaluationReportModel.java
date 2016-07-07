package org.paradyne.model.recruitment;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.InterviewEvaluationBean;
import org.paradyne.lib.*;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class InterviewEvaluationReportModel extends ModelBase {
	static Logger logger = Logger.getLogger(InterviewEvaluationReportModel.class);

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, InterviewEvaluationBean interviewEvalBean) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";
			if (interviewEvalBean.getReportType().equals("Pdf")) {
				reportType = "Pdf";
			}
			if (interviewEvalBean.getReportType().equals("Xls")) {
				reportType = "Xls";
			}
			
			if (interviewEvalBean.getReportType().equals("Doc")) {
				reportType = "Txt";
			}
			rds.setReportType(reportType);
			rds.setPageOrientation("landscape");
			rds.setFileName("Interview Evaluation Analysis Report");
			rds.setReportName("Interview Evaluation Analysis Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			TableDataSet titleName = new TableDataSet();
			titleName.setData(new Object[][]{{"Interview Evaluation Analysis Report"}});
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			
			String query = " SELECT REQS_NAME,RANK_NAME,  EMP_FNAME||' '||EMP_LNAME AS INTNAME,CAND_FIRST_NAME||' '||CAND_LAST_NAME AS CANDNAME,  DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','OnHold'),  HRMS_REC_ROUND_TYPE.ROUND_TYPE, "
					+ " C.EMP_FNAME||' '||C.EMP_MNAME||' '||C.EMP_LNAME AS RECRUITERNAME , EVAL_SCORE, "
					+ " EVAL_PERCENTAGE, TO_CHAR(HRMS_REC_CANDEVAL.EVAL_CURRINT_DATE,'DD-MM-YYYY'), HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, EVAL_RATE_SCORE"
					+ " "
					+ " FROM HRMS_REC_CANDEVAL_DTL  "
					+ " INNER JOIN HRMS_REC_CANDEVAL ON (HRMS_REC_CANDEVAL.EVAL_CODE = HRMS_REC_CANDEVAL_DTL.EVAL_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CANDEVAL.EVAL_REQS_CODE)"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  "
					+ " INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_DTL_CODE = HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)  "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE)"
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)  "
					+ " INNER JOIN  HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC C ON(C.EMP_ID=HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID )  "
					+ " LEFT JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_CANDEVAL.EVAL_ROUND_TYPE)"
					+ " inner join HRMS_REC_INTERVIEW_ASSESSMENT on (HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE = HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE)"
					+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
			if (!interviewEvalBean.getReqCode().equals("")) {
				query += "AND HRMS_REC_REQS_HDR.REQS_CODE = " + interviewEvalBean.getReqCode();
			}
			
			if (!interviewEvalBean.getInterviewerId().equals("")) {
				query += " AND HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID = " + interviewEvalBean.getInterviewerId();
			}
			
			if (!interviewEvalBean.getCandidateCode().equals("")) {
				query += "AND HRMS_REC_SCHINT_DTL.INT_CAND_CODE =" + interviewEvalBean.getCandidateCode();
			}
			
			if (!interviewEvalBean.getPositionId().equals("")) {
				query += "AND HRMS_REC_REQS_HDR.REQS_POSITION = " + interviewEvalBean.getPositionId();
			}
			
			if (!interviewEvalBean.getFrmDate().equals("") && !interviewEvalBean.getToDate().equals("")) {
				query += "AND HRMS_REC_REQS_HDR.REQS_DATE BETWEEN TO_DATE('" + interviewEvalBean.getFrmDate().trim() + "', 'DD-MM-YYYY') AND TO_DATE('" + interviewEvalBean.getToDate() + "', 'DD-MM-YYYY')" ;
			}

			if (!interviewEvalBean.getInterviewDate().equals("")) {
				query += "AND HRMS_REC_CANDEVAL.EVAL_CURRINT_DATE = TO_DATE('" + interviewEvalBean.getInterviewDate().trim() + "', 'DD-MM-YYYY')" ;
			}
			
			query += " ORDER BY HRMS_REC_CANDEVAL_DTL.EVAL_CODE,EVAL_RATE_CODE ASC  ";

			Object[][] interviewObj = getSqlModel().getSingleResult(query);
			Set<String> interviewSet = new HashSet<String>();
			if (interviewObj != null && interviewObj.length > 0) {
				for (int i = 0; i < interviewObj.length; i++) {
					interviewSet.add(String.valueOf(interviewObj[i][0]));
				}
				interviewObj = Utility.transverse(interviewObj, new int[] { 0, 1,
						2, 3, 4, 5, 6, 7, 8, 9}, 10, 11, "0", true, new String[] {
						"Requisition Title", "Position", "Interviewer",
						"Candidate", "Status", "Round Of Interview",
						"Recruiter", "Score", "Percentage", "Interview Date" });
				int cellAlignmentCount = interviewSet.size() + 2;
				int[] cellAlignArray = new int[cellAlignmentCount];
				int[] cellWidthArray = new int[cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if (i == 0) {
						cellWidthArray[i] = 5;
					} else {
						cellWidthArray[i] = 5;
					}
				}
				int[] rowSumArray = new int[interviewSet.size() + 1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				TableDataSet interviewDataTable = new TableDataSet();
				interviewDataTable.setData(interviewObj);
				interviewDataTable.setCellAlignment(cellAlignArray);
				interviewDataTable.setCellWidth(cellWidthArray);
				interviewDataTable.setBorder(true);
				interviewDataTable.setHeaderTable(true);
				rg.addTableToDoc(interviewDataTable);
			} else {
				TableDataSet interviewDataTable = new TableDataSet();
				interviewDataTable.setData(new Object[][] { { "No data to display" } });
				interviewDataTable.setCellAlignment(new int[] { 0 });
				interviewDataTable.setCellWidth(new int[] { 100 });
				interviewDataTable.setBorder(true);
				interviewDataTable.setHeaderTable(true);
				rg.addTableToDoc(interviewDataTable);
			}
			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}