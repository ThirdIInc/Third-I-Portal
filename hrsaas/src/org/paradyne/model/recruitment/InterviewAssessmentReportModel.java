package org.paradyne.model.recruitment;

import java.awt.Color;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.InterviewAssessmentReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import com.lowagie.text.Font;
 

public class InterviewAssessmentReportModel extends ModelBase {

	public void getnerateReport(InterviewAssessmentReport reportBean,
			HttpServletResponse response) {
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType("Txt");
		rds.setFileName("Interview  Assessment  Form__"
				+ reportBean.getCandName());

		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		Object[][] nameObj = null;
		nameObj = new Object[1][4];
		nameObj[0][0] = "";
		nameObj[0][1] = "\n\nINTERVIEW ASSESSMENT FORM";
		nameObj[0][2] = "";
		  
		try {
			String logoQuery = "select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
			Object logoObj[][] = getSqlModel().getSingleResult(logoQuery);
			if (logoObj != null && logoObj.length > 0) {
				String filename = "";
				if (!String.valueOf(logoObj[0][1]).equals("")) {
					String clientUser = (String) session
							.getAttribute("session_pool");
					filename = String.valueOf(logoObj[0][1]);
					String filePath = context.getRealPath("/")
							+ "pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ filename;
					nameObj[0][3] = rg.getImage(filePath);
					 

				} else
					nameObj[0][3] = "";

			} else {
				nameObj[0][3] = "";
			}
			// Image
			// im=Image.getInstance("C:\\hrwork\\dataFiles\\sal_logo.jpg");

			// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
		} catch (Exception eee) {
			 
			eee.printStackTrace();
			nameObj[0][3] = " ";
			// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
		}

		try {
			TableDataSet tdsName = new TableDataSet();
			tdsName.setData(nameObj);
			tdsName.setCellAlignment(new int[] { 0, 0, 0, 0 });
			tdsName.setCellWidth(new int[] { 20, 55, 15, 10 });
			// tdsName.setBorder(true);
			tdsName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(
					0, 0, 0));
			// rg.addTableToDoc(tdsName);
			rg.createHeader(tdsName);
		} catch (Exception e) {
			System.out.println("error in image " + e);
			// e.printStackTrace();
		}

		String intDtlQry = " SELECT INT_DTL_CODE,'Interview Round :- '||''||INT_ROUND_TYPE,CAND_FIRST_NAME || ' ' || CAND_MID_NAME || ' ' || CAND_LAST_NAME from HRMS_REC_SCHINT_DTL "
				+ " INNER JOIN HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) "
				+ " WHERE INT_CAND_CODE = "
				+ reportBean.getCandCode()
				+ " AND INT_REQS_CODE="
				+ reportBean.getReqCode()
				+ " AND INT_CONDUCT_STATUS = 'Y' ";
		Object intDtlObj[][] = getSqlModel().getSingleResult(intDtlQry);
		String evalDtlQry = "";
		Object evalDtlObj[][] = null;
		Vector vect = new Vector();
		if (intDtlObj != null && intDtlObj.length > 0) {
			for (int i = 0; i < intDtlObj.length; i++) {
				evalDtlQry = " SELECT NVL(EVAL_RATE_SCORE,0),DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','O','OnHold'),"
						+ " NVL(EVAL_RATE_COMMENTS,' '),NVL(REC_ASSESS_PARAM,' '),NVL(REC_ASSESS_DESC,' ') FROM HRMS_REC_CANDEVAL_DTL "
						+ " INNER JOIN HRMS_REC_CANDEVAL ON (HRMS_REC_CANDEVAL.EVAL_CODE = HRMS_REC_CANDEVAL_DTL.EVAL_CODE) "
						+ " INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_DTL_CODE = HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE) "
						+ "  INNER JOIN HRMS_REC_INTERVIEW_ASSESSMENT ON (HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE= HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE) "
						+ " WHERE INT_CONDUCT_STATUS = 'Y'  AND EVAL_INT_DTL_CODE = "
						+ intDtlObj[i][0];
				evalDtlObj = getSqlModel().getSingleResult(evalDtlQry);

				vect.add(evalDtlObj);

			}
		}

		String candidateDtlQuery = " SELECT  EVAL_CURR_CTC,EVAL_NEGO_CTC,NVL(TO_CHAR(EVAL_EXP_JOINDATE,'DD-MM-YYYY') ,' ')  ,DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','O','OnHold') ,NVL(EVAL_STRENGTH,' '),NVL(EVAL_WEAKNESS,' '),NVL(EVAL_COMMENTS,'') "
				+ " ,CAND_EXP_YEAR||' '||'Years'||' '||CAND_EXP_MONTH ||' '||'Month' ,NVL(CAND_COMPANY,''),NVL(CAND_POSITION,''),NVL(CAND_LOCATION,' ') "
				+ "     FROM HRMS_REC_CANDEVAL "
				+ "	inner join HRMS_REC_CAND_DATABANK  on (HRMS_REC_CANDEVAL.EVAL_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
				+ "	 WHERE EVAL_CAND_CODE="
				+ reportBean.getCandCode()
				+ " AND  EVAL_REQS_CODE=" + reportBean.getReqCode();

		Object[][] candidateDataObj = getSqlModel().getSingleResult(
				candidateDtlQuery);

		String reqDtlQuery = " SELECT  NVL( RANK_NAME,''), TO_CHAR(INT_DATE,'DD-MM-YYYY')  FROM HRMS_REC_SCHINT_DTL "
				+ "    INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
				+ " WHERE INT_CAND_CODE="
				+ reportBean.getCandCode()
				+ " AND INT_REQS_CODE=" + reportBean.getReqCode();

		Object[][] reqDtlQueryObj = getSqlModel().getSingleResult(reqDtlQuery);

		if (reqDtlQueryObj != null && reqDtlQueryObj.length > 0) {

			reqDtlQueryObj = removeIllegalCharacter(reqDtlQueryObj);

			candidateDataObj = removeIllegalCharacter(candidateDataObj);

			Object obj[][] = new Object[4][4];
			obj[0][0] = "Name   :";
			obj[0][1] = reportBean.getCandName();
			obj[0][2] = "Date of Interview:";
			obj[0][3] = checkNull(String.valueOf(reqDtlQueryObj[0][1]));

			obj[1][0] = "Position Considered for  :";
			obj[1][1] = checkNull(String.valueOf(reqDtlQueryObj[0][0]));
			obj[1][2] = "Total Years of Experience:    :";
			obj[1][3] = checkNull(String.valueOf(candidateDataObj[0][7]));

			obj[2][0] = "Current Position   :";
			obj[2][1] = checkNull(String.valueOf(candidateDataObj[0][9]));
			obj[2][2] = "Total Years of Industry     :";
			obj[2][3] = checkNull(String.valueOf(candidateDataObj[0][7]));

			obj[3][0] = "Current Company :";
			obj[3][1] = checkNull(String.valueOf(candidateDataObj[0][8]));
			obj[3][2] = "";
			obj[3][3] = "";

			TableDataSet tdsEstab = new TableDataSet();
			tdsEstab.setData(obj);
			tdsEstab.setCellAlignment(new int[] { 0, 0, 0, 0 });
			tdsEstab.setCellWidth(new int[] { 25, 25, 25, 25 });
			// tdsEstab.setBlankRowsAbove(2);
			tdsEstab.setBorder(true);
			rg.addTableToDoc(tdsEstab);
			Object[][] result = null;
			for (int i = 0; i < intDtlObj.length; i++) {
				if (vect != null && vect.get(i) != null) {
					Object evalObj[][] = (Object[][]) vect.get(i);

					result = new Object[evalObj.length][4];
					for (int m = 0; m < evalObj.length; m++) {
						result[m][0] = checkNull(String.valueOf(evalObj[m][3]));
						result[m][1] = checkNull(String.valueOf(evalObj[m][4]));
						result[m][2] = checkNull(String.valueOf(evalObj[m][0]));
						result[m][3] = checkNull(String.valueOf(evalObj[m][2]));
					}
				}
				
				Object InterviewRoundObj[][] =new Object[1][1];
				
				InterviewRoundObj[0][0]=String.valueOf(intDtlObj[i][1]);
				
				TableDataSet tableDataInterviewRound = new TableDataSet();
				tableDataInterviewRound.setData(InterviewRoundObj);
				tableDataInterviewRound.setCellAlignment(new int[] { 0 });
				tableDataInterviewRound.setCellWidth(new int[] { 100 });
				// tableDataScale.setBorder(true);
				// tableData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataInterviewRound);

				Object data[][] = new Object[1][1];

				data[0][0] = " On a scale of 1-5, 1 being the least and 5 being the highest score ";

				TableDataSet tableDataScale = new TableDataSet();
				tableDataScale.setData(data);
				tableDataScale.setCellAlignment(new int[] { 0 });
				tableDataScale.setCellWidth(new int[] { 100 });
				// tableDataScale.setBorder(true);
				// tableData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataScale);

				String[] columns = { "Parameters", "Description", "Rating",
						"Remarks" };

				TableDataSet tableData = new TableDataSet();
				tableData.setData(result);
				tableData.setCellAlignment(new int[] { 0, 0, 1, 0 });
				tableData.setCellWidth(new int[] { 25, 25, 25, 25 });
				tableData.setHeader(columns);
				tableData.setBorder(true);
				// tableData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableData);
				
				
				
				Object intObj[][] = new Object[2][2];
				intObj[0][0] = "Strengths:";
				intObj[0][1] = checkNull(String.valueOf(candidateDataObj[i][4]));
				intObj[1][0] = "Weakness:";
				intObj[1][1] = checkNull(String.valueOf(candidateDataObj[i][5]));

				TableDataSet tableDataSet = new TableDataSet();
				tableDataSet.setData(intObj);
				tableDataSet.setCellAlignment(new int[] { 0, 0 });
				tableDataSet.setCellWidth(new int[] { 25, 75 });
				tableDataSet.setBorder(true);
				// tableData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataSet);
				
				
				Object intdataObj[][] = new Object[2][4];

				intdataObj[0][0] = "Current Compensation  :";
				intdataObj[0][1] = checkNull(String
						.valueOf(candidateDataObj[i][0]));
				intdataObj[0][2] = " Expected Compensation :";
				intdataObj[0][3] = checkNull(String
						.valueOf(candidateDataObj[i][1]));
				intdataObj[1][0] = "Current Location  :";
				intdataObj[1][1] = checkNull(String
						.valueOf(candidateDataObj[0][10]));
				intdataObj[1][2] = "Expected joining Date:";
				intdataObj[1][3] = checkNull(String
						.valueOf(candidateDataObj[0][2]));

				TableDataSet tableDataSet1 = new TableDataSet();
				tableDataSet1.setData(intdataObj);
				tableDataSet1.setCellAlignment(new int[] { 0, 0, 0, 0 });
				tableDataSet1.setCellWidth(new int[] { 25, 25, 25, 25 });
				tableDataSet1.setBorder(true);
				// tableDataSet.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataSet1);
				
				Object actObj[][] = new Object[1][2];
				actObj[0][0] = "Action :";
				actObj[0][1] = checkNull(String.valueOf(candidateDataObj[i][3]));

				TableDataSet tableDataSetAction = new TableDataSet();
				tableDataSetAction.setData(actObj);
				tableDataSetAction.setCellAlignment(new int[] { 0, 0 });
				tableDataSetAction.setCellWidth(new int[] { 25, 75 });
				tableDataSetAction.setBorder(true);
				// tableDataSetAction.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataSetAction);
				
				
				Object showObj[][] = new Object[3][2];
				showObj[0][0] = "Panel :";
				showObj[0][1] = "";
				showObj[1][0] = "Sign :";
				showObj[1][1] = "";
				showObj[2][0] = "Name";
				showObj[2][1] = "";

				TableDataSet tableDataSetPanel = new TableDataSet();
				tableDataSetPanel.setData(showObj);
				tableDataSetPanel.setCellAlignment(new int[] { 0, 0 });
				tableDataSetPanel.setCellWidth(new int[] { 25, 75 });
				tableDataSetPanel.setBorder(true);
				// tableDataSetAction.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataSetPanel);
				
				
				Object commentsObj[][] = new Object[1][2];
				commentsObj[0][0] = "Comments";
				commentsObj[0][1] = checkNull(String
						.valueOf(candidateDataObj[i][6]));

				TableDataSet tableDataSetComment = new TableDataSet();
				tableDataSetComment.setData(commentsObj);
				tableDataSetComment.setCellAlignment(new int[] { 0, 0 });
				tableDataSetComment.setCellWidth(new int[] { 25, 75 });
				tableDataSetComment.setBorder(true);
				// tableDataSetAction.setBlankRowsAbove(1);
				rg.addTableToDoc(tableDataSetComment);

			}
 		 
		}

		rg.process();
		rg.createReport(response);
	}

	public void getnerateReport_old(InterviewAssessmentReport reportBean,
			HttpServletResponse response) {
		try {
			// TODO Auto-generated method stub
			String title = "\n INTERVIEW ASSESSMENT FORM\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Txt", "Interview  Assessment  Form__"
							+ reportBean.getCandName());
			rg.addFormatedText(title, 6, 0, 1, 0);
			String intDtlQry = " SELECT INT_DTL_CODE,'Interview Round :- '||''||INT_ROUND_TYPE,CAND_FIRST_NAME || ' ' || CAND_MID_NAME || ' ' || CAND_LAST_NAME from HRMS_REC_SCHINT_DTL "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) "
					+ " WHERE INT_CAND_CODE = "
					+ reportBean.getCandCode()
					+ " AND INT_REQS_CODE="
					+ reportBean.getReqCode()
					+ " AND INT_CONDUCT_STATUS = 'Y' ";
			Object intDtlObj[][] = getSqlModel().getSingleResult(intDtlQry);
			String evalDtlQry = "";
			Object evalDtlObj[][] = null;

			if (intDtlObj != null && intDtlObj.length > 0) {
				for (int i = 0; i < intDtlObj.length; i++) {
					evalDtlQry = " SELECT NVL(EVAL_RATE_SCORE,0),DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','O','OnHold'),"
							+ " NVL(EVAL_RATE_COMMENTS,' '),NVL(REC_ASSESS_PARAM,' '),NVL(REC_ASSESS_DESC,' ') FROM HRMS_REC_CANDEVAL_DTL "
							+ " INNER JOIN HRMS_REC_CANDEVAL ON (HRMS_REC_CANDEVAL.EVAL_CODE = HRMS_REC_CANDEVAL_DTL.EVAL_CODE) "
							+ " INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_DTL_CODE = HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE) "
							+ "  INNER JOIN HRMS_REC_INTERVIEW_ASSESSMENT ON (HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE= HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE) "
							+ " WHERE INT_CONDUCT_STATUS = 'Y'  AND EVAL_INT_DTL_CODE = "
							+ intDtlObj[i][0];
					evalDtlObj = getSqlModel().getSingleResult(evalDtlQry);

				}
			}

			String candidateDtlQuery = " SELECT  NVL(EVAL_CURR_CTC,0),NVL(EVAL_NEGO_CTC,0),NVL(TO_CHAR(EVAL_EXP_JOINDATE,'DD-MM-YYYY') ,' ')  ,DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','O','OnHold') ,NVL(EVAL_STRENGTH,' '),NVL(EVAL_WEAKNESS,' '),NVL(EVAL_COMMENTS,'') "
					+ " ,CAND_EXP_YEAR||' '||'Years'||' '||CAND_EXP_MONTH ||'Month' ,NVL(CAND_COMPANY,''),NVL(CAND_POSITION,''),NVL(CAND_LOCATION,' ') "
					+ "     FROM HRMS_REC_CANDEVAL "
					+ "	inner join HRMS_REC_CAND_DATABANK  on (HRMS_REC_CANDEVAL.EVAL_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
					+ "	 WHERE EVAL_CAND_CODE="
					+ reportBean.getCandCode()
					+ " AND  EVAL_REQS_CODE=" + reportBean.getReqCode();

			Object[][] candidateDataObj = getSqlModel().getSingleResult(
					candidateDtlQuery);

			String reqDtlQuery = " SELECT  NVL( RANK_NAME,''), TO_CHAR(INT_DATE,'DD-MM-YYYY')  FROM HRMS_REC_SCHINT_DTL "
					+ "    INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
					+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " WHERE INT_CAND_CODE="
					+ reportBean.getCandCode()
					+ " AND INT_REQS_CODE=" + reportBean.getReqCode();

			Object[][] reqDtlQueryObj = getSqlModel().getSingleResult(
					reqDtlQuery);

			Object obj[][] = new Object[4][4];

			if (reqDtlQueryObj != null && reqDtlQueryObj.length > 0) {
				obj[0][0] = "Name   :";
				obj[0][1] = reportBean.getCandName();
				obj[0][2] = "Date of Interview:";
				obj[0][3] = checkNull(String.valueOf(reqDtlQueryObj[0][1]));
				obj[1][0] = "Position Considered for  :";
				obj[1][1] = checkNull(String.valueOf(reqDtlQueryObj[0][0]));
				obj[1][2] = "Total Years of Experience:    :";
				obj[1][3] = checkNull(String.valueOf(candidateDataObj[0][7]));
				obj[2][0] = "Current Position   :";
				obj[2][1] = checkNull(String.valueOf(candidateDataObj[0][9]));

				obj[2][2] = "Total Years of Industry     :";
				obj[2][3] = checkNull(String.valueOf(candidateDataObj[0][7]));
				obj[3][0] = "Current Company :";
				obj[3][1] = checkNull(String.valueOf(candidateDataObj[0][8]));
				rg.tableBody(obj, new int[] { 25, 25, 25, 25 }, new int[] { 0,
						0, 0, 0 });

				if (evalDtlObj != null && evalDtlObj.length > 0) {
					Object[][] result = new Object[evalDtlObj.length][4];

					for (int i = 0; i < evalDtlObj.length; i++) {
						result[i][0] = checkNull(String
								.valueOf(evalDtlObj[i][3]));
						result[i][1] = checkNull(String
								.valueOf(evalDtlObj[i][4]));
						result[i][2] = checkNull(String
								.valueOf(evalDtlObj[i][0]));
						result[i][3] = checkNull(String
								.valueOf(evalDtlObj[i][2]));
					}

					String[] header = { "Parameters", "Description", "Rating",
							"Remarks" };
					int appCell[] = { 25, 25, 25, 25 };
					int apprAlign[] = { 0, 0, 2, 0 };
					rg.tableBody(header, result, appCell, apprAlign);

				}

				if (candidateDataObj != null && candidateDataObj.length > 0) {

					Object intObj[][] = new Object[2][2];
					intObj[0][0] = "Strengths:";
					intObj[0][1] = checkNull(String
							.valueOf(candidateDataObj[0][4]));
					intObj[1][0] = "Weakness:";
					intObj[1][1] = checkNull(String
							.valueOf(candidateDataObj[0][5]));
					rg.tableBody(intObj, new int[] { 25, 75 },
							new int[] { 0, 0 });
					Object intdataObj[][] = new Object[2][4];

					intdataObj[0][0] = "Current Compensation  :";
					intdataObj[0][1] = checkNull(String
							.valueOf(candidateDataObj[0][0]));
					intdataObj[0][2] = " Expected Compensation :";
					intdataObj[0][3] = checkNull(String
							.valueOf(candidateDataObj[0][1]));
					intdataObj[1][0] = "Current Location  :";
					intdataObj[1][1] = checkNull(String
							.valueOf(candidateDataObj[0][10]));
					intdataObj[1][2] = "Expected joining Date:";
					intdataObj[1][3] = checkNull(String
							.valueOf(candidateDataObj[0][2]));
					rg.tableBody(intdataObj, new int[] { 25, 25, 25, 25 },
							new int[] { 0, 0, 0, 0 });

					Object actObj[][] = new Object[1][2];
					actObj[0][0] = "Action :";
					actObj[0][1] = checkNull(String
							.valueOf(candidateDataObj[0][3]));
					rg.tableBody(actObj, new int[] { 25, 75 },
							new int[] { 0, 0 });
				}

				Object showObj[][] = new Object[3][2];
				showObj[0][0] = "Panel :";
				showObj[0][1] = "";
				showObj[1][0] = "Sign :";
				showObj[1][1] = "";
				showObj[2][0] = "Name";
				showObj[2][1] = "";
				rg.tableBody(showObj, new int[] { 25, 75 }, new int[] { 0, 0 });
				Object commentsObj[][] = new Object[1][2];
				commentsObj[0][0] = "Comments";
				commentsObj[0][1] = checkNull(String
						.valueOf(candidateDataObj[0][6]));
				rg.tableBody(commentsObj, new int[] { 25, 75 }, new int[] { 0,
						0 });
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}

			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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

	public Object[][] removeIllegalCharacter(Object[][] result) {

		try {
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {
					for (int j = 0; j < result[0].length; j++) {
					result[i][j] = String.valueOf(result[i][j]).replaceAll(
								"&", " and ");

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
