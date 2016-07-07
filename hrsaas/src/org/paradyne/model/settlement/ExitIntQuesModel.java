package org.paradyne.model.settlement;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settlement.ExitIntQues;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import com.ibm.icu.text.SimpleDateFormat;

public class ExitIntQuesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.lib.ModelBase.class);

	/**
	 * @param String
	 *            resignCode and exit date
	 * @return boolean to add new record for exit interview and
	 * questionnaire details of resigned employee
	 * 
	 */
	public boolean saveExitCode(ExitIntQues bean, String[] quesCode,
			String[] comment, String[] rating) {
		boolean res = false;
		Object addObj[][] = new Object[1][2];

		addObj[0][0] = bean.getResignCode();
		addObj[0][1] = bean.getLeaveDate();

		res = getSqlModel().singleExecute(getQuery(1), addObj);
		if (res) {
			saveQdtl(addObj, quesCode, comment, rating);
			return true;
		}// end if
		else {
			return false;
		}// end else

	}

	/**
	 * @param String
	 *            resignCode and exit date
	 * @return boolean to update the existing record for exit interview and
	 * questionnaire details of resigned employee
	 * 
	 */
	public boolean updateDtl(ExitIntQues bean, String code, String[] quesCode,
			String[] comment, String[] rating) {

		// ExitIntQues bean = new ExitIntQues();

		try {
			boolean upRes = false;
			String dt2 = "";
			for (int i = 0; i < quesCode.length; i++) {
				if (rating[i].equals("")) {
					// UPDATE THE EXIT INTERVIEW DETAIL
					dt2 = " UPDATE HRMS_EXIT_DTL set EXIT_QUES_COMMENTS ='"
							+ comment[i] + "' WHERE EXIT_QUES_CODE="
							+ quesCode[i] + " AND RESIGN_CODE =" + code;

				} else {
					dt2 = " UPDATE HRMS_EXIT_DTL set EXIT_QUES_RATE = "
							+ rating[i] + ",EXIT_QUES_COMMENTS ='" + comment[i]
							+ "' WHERE EXIT_QUES_CODE=" + quesCode[i]
							+ " AND RESIGN_CODE =" + code;
				}

				upRes = getSqlModel().singleExecute(dt2);

			}//end for loop
			return upRes;
		} catch (Exception e) {
			logger.error("Exit Interview in update" + e);
			return false;
		}
	}

	/**
	 * @param String
	 *            exit code
	 * @return string deletes the record for exit interview of resigned employee.
	 * 
	 */
	public String deleteRecord(ExitIntQues bean) {

		Object delObj[][] = new Object[1][1];

		delObj[0][0] = bean.getExIntcode();

		boolean res = getSqlModel().singleExecute(getQuery(7), delObj);
		if (res) {
			Object[][] delexitCode = new Object[1][1];
			delexitCode[0][0] = bean.getExIntcode();
			boolean flag = getSqlModel()
					.singleExecute(getQuery(4), delexitCode);
			if (flag) {
				return "Record deleted successfully!";
			}// end if 
			else {
				return "Record can not be deleted!";
			}//end else
		}// end outer if

		return " Record can not be deleted!";

	}

	/**
	 * @param String
	 *            questionCode
	 * @return void sets the questions of exit interview type.
	 * 
	 */
	public void getDisplay(ExitIntQues bean) {
		try {
			Object[][] result = getSqlModel().getSingleResult(getQuery(2));
			ArrayList<Object> tableList = new ArrayList<Object>();

			for (int i = 0; i < result.length; i++) {
				TreeMap map = new TreeMap();
				Object qcode = result[i][0];

				/*
				 * String sql =" SELECT DISTINCT HRMS_QUES.QUES_CODE
				 * ,QUESDTL_NAME FROM HRMS_QUESDTL " + " left JOIN HRMS_QUES
				 * ON(HRMS_QUES.QUES_CODE=HRMS_QUESDTL.QUES_CODE) "+ " WHERE
				 * HRMS_QUES.QUES_CODE="+qcode;
				 */

				String sql = " SELECT DISTINCT QUESDTL_CODE ,QUESDTL_NAME  FROM HRMS_QUESDTL  WHERE QUES_CODE="
						+ qcode + " ORDER BY QUESDTL_CODE";
				Object[][] data = getSqlModel().getSingleResult(sql);

				ExitIntQues bean1 = new ExitIntQues();
				bean1.setQuestionCode(String.valueOf(result[i][0]));
				bean1
						.setQuestion(checkNull(String.valueOf(result[i][1])
								.trim()));

				for (int j = 0; j < data.length; j++) {

					map.put(String.valueOf(data[j][0]), String
							.valueOf(data[j][1]));

				}// end inner for loop

				bean1.setTmap(map);
				map = null;
				tableList.add(bean1);

			}// end outer for loop
			bean.setTableList(tableList);
			bean.setQuedetails("1");
			bean.setQuesDtlflag("true");
		} catch (Exception e) {
			logger.error("Exit interview exception" + e);
		}
	}

	/**
	 * @param String
	 *            exit code
	 * @return void sets the record for existing exit interview questionnaire of resigned employee.
	 * 
	 */
	public void getComment(ExitIntQues bean) {

		try {
			Object[] exCode = new Object[1];
			exCode[0] = bean.getExIntcode();
			// SELECT EXIT DATE 
			String dd = "SELECT TO_CHAR(EXIT_DATE,'DD-MM-YYYY') FROM HRMS_EXIT_HDR WHERE RESIGN_CODE= "
					+ exCode[0];
			Object[][] ddobj = getSqlModel().getSingleResult(dd);
			bean.setLeaveDate(String.valueOf(ddobj[0][0]));
			// SELECT EXISTING RECORD
			String selectData = "  SELECT DISTINCT QUES_NAME,NVL(HRMS_EXIT_DTL.EXIT_QUES_COMMENTS,' '), HRMS_QUES.QUES_CODE, "
					+ " HRMS_EXIT_HDR.RESIGN_CODE,HRMS_EXIT_DTL.EXIT_QUES_CODE,NVL(QUESDTL_CODE,'0')  FROM HRMS_QUES    "
					+ "  LEFT JOIN HRMS_EXIT_DTL ON(HRMS_EXIT_DTL.EXIT_QUES_CODE = HRMS_QUES.QUES_CODE) "
					+ "  LEFT JOIN HRMS_QUESDTL  ON( HRMS_QUESDTL.QUESDTL_CODE = HRMS_EXIT_DTL.EXIT_QUES_RATE) "
					+ "  LEFT JOIN HRMS_EXIT_HDR ON(HRMS_EXIT_HDR.RESIGN_CODE = HRMS_EXIT_DTL.RESIGN_CODE) "
					+ "  WHERE  HRMS_EXIT_HDR.RESIGN_CODE= "
					+ exCode[0]
					+ "  ORDER BY HRMS_QUES.QUES_CODE";

			Object[][] result = getSqlModel().getSingleResult(selectData);
			ArrayList<Object> tableList = new ArrayList<Object>();

			for (int i = 0; i < result.length; i++) {

				String sqlQuery = " SELECT DISTINCT EXIT_QUES_RATE , QUESDTL_NAME FROM HRMS_EXIT_DTL  "
						+ " LEFT JOIN HRMS_QUESDTL ON(HRMS_QUESDTL.QUESDTL_CODE=HRMS_EXIT_DTL.EXIT_QUES_RATE )  "
						+ " LEFT JOIN HRMS_EXIT_HDR ON(HRMS_EXIT_HDR.RESIGN_CODE = HRMS_EXIT_DTL.RESIGN_CODE)"
						+ " 	WHERE  RESIGN_CODE  = "
						+ result[i][3]
						+ " AND HRMS_QUESDTL.QUES_CODE ="
						+ result[i][2]
						+ " and QUESDTL_CODE=" + result[i][5];
				;
				Object[][] sqlData = getSqlModel().getSingleResult(sqlQuery);

				String query = "SELECT distinct QUESDTL_CODE,QUESDTL_NAME  FROM HRMS_QUESDTL WHERE QUES_CODE ="
						+ result[i][2] + " ORDER BY QUESDTL_CODE";
				Object[][] queryData = getSqlModel().getSingleResult(query);

				Object[][] addObj = new Object[sqlData.length
						+ queryData.length][2];
				for (int x = 0; x < sqlData.length; x++) {
					addObj[x][0] = String.valueOf(sqlData[x][0]);
					addObj[x][1] = String.valueOf(sqlData[x][1]);
				}// end x for loop
				int j = 0;
				for (int y = sqlData.length; y < sqlData.length
						+ queryData.length; y++) {
					addObj[y][0] = String.valueOf(queryData[j][0]);
					addObj[y][1] = String.valueOf(queryData[j][1]);

					j++;

				}// end y for loop

				TreeMap map = new TreeMap();
				ExitIntQues bean1 = new ExitIntQues();
				bean1.setQuestionCode(String.valueOf(result[i][4]));
				bean1
						.setQuestion(checkNull(String.valueOf(result[i][0])
								.trim()));

				// bean1.setRating(String.valueOf(result[i][2]));
				for (int k = 0; k < addObj.length; k++) {
					if (String.valueOf(addObj[k][0]).equals("")) {

						map.put("10", String.valueOf(addObj[k][1]));
					}// end if
					else {

						map.put(String.valueOf(addObj[k][0]), String
								.valueOf(addObj[k][1]));
					}//end else

				}// end inner for loop

				bean1.setTmap(map);

				if (sqlData != null && sqlData.length > 0) {
					bean1.setRating(checkNull(String.valueOf(sqlData[0][0])
							.trim()));
					// bean1.setRatingCode(String.valueOf(sqlData[0][0]));
				}// end of if..

				map = null;

					bean1.setComment(checkNull(String.valueOf(result[i][1])
								.trim()));
					logger.info("Comments======="+bean1.getComment());
				tableList.add(bean1);
				bean1.setQuedetails("1");

			} //	end of outer for loop
			bean.setFlag("true");
			bean.setTableList(tableList);
			bean.setQuedetails("1");
			bean.setQuesDtlflag("true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param String
	 *            exit code
	 * @return boolean add new exit interview questionnaire of resigned employee.
	 * 
	 */
	public boolean saveQdtl(Object[][] exCode, String[] quesCode,
			String[] comment, String[] rating) {
		try {

			for (int i = 0; i < quesCode.length; i++) {
				// ques[i][2]=comment[i];
				String Que = " SELECT max(RESIGN_CODE) FROM HRMS_EXIT_HDR";
				getSqlModel().getSingleResult(Que);
				String dt2 = "INSERT INTO HRMS_EXIT_DTL (RESIGN_CODE,EXIT_QUES_CODE,EXIT_QUES_RATE,EXIT_QUES_COMMENTS) "
						+ " VALUES("
						+ exCode[0][0]
						+ ","
						+ quesCode[i]
						+ ",'"
						+ comment[i] + "','" + rating[i] + "')";
				getSqlModel().singleExecute(dt2);
			}// end for loop

			return true;
		} catch (Exception e) {
			logger.error("Exit interview save details" + e);
			return false;
		}
	}

	/*
	 * public void getReport(ExitIntQues intques, HttpServletRequest request,
	 * HttpServletResponse response, ServletContext context, HttpSession
	 * session) {  logger.info("crystal
	 * report=============="); CrystalReport cr=new CrystalReport(); String
	 * path="org\\paradyne\\rpt\\settlement\\exitInterviewReport.rpt";
	 * logger.info("=====================");
	 * 
	 * cr.createReport(request,response, context, session, path,"");
	 *  }
	 */
	/**
	 * @param exit code
	 * @return String generates report for exit interview questionnaire of resigned employee.
	 * in PDF format
	 */
	public boolean report(ExitIntQues bean, HttpServletResponse response) {

		//String name = "Application ";
		String type = "Pdf";
		String title = "Exit Interview Question";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);

		rg = getHeader(rg, bean);
		rg.createReport(response);
		return true;

	}

	/**
	 * @param String
	 *            exit code
	 * @return Reportgenerator generates report for exit interview questionnaire of resigned employee.
	 * in PDF format
	 */
	private ReportGenerator getHeader(ReportGenerator rg, ExitIntQues bean) {
		try {

			String que = "SELECT distinct HRMS_EXIT_HDR.RESIGN_CODE,HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ "  NVL(RANK_NAME,' '),NVL(CENTER_NAME,' '),TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_EXIT_HDR.EXIT_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EXIT_HDR"
					+ " LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_EXIT_HDR.RESIGN_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+

					" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_EXIT_DTL ON(HRMS_EXIT_DTL.RESIGN_CODE=HRMS_EXIT_HDR.RESIGN_CODE)"
					+ " WHERE HRMS_EXIT_HDR.RESIGN_CODE="
					+ bean.getExIntcode()
					+ "  ORDER BY HRMS_EXIT_HDR.RESIGN_CODE";

			Object[][] result = getSqlModel().getSingleResult(que);
			Object[][] rateResult = null;
			Object[][] comm = null;

			String selectData = "  SELECT DISTINCT QUES_NAME,NVL(HRMS_EXIT_DTL.EXIT_QUES_COMMENTS,' '), HRMS_QUES.QUES_CODE, "
					+ " HRMS_EXIT_HDR.RESIGN_CODE,HRMS_EXIT_DTL.RESIGN_CODE,HRMS_EXIT_DTL.EXIT_QUES_CODE,"
					+ " QUESDTL_CODE "
					+ " FROM HRMS_QUES    "
					+ "  LEFT JOIN HRMS_EXIT_DTL ON(HRMS_EXIT_DTL.EXIT_QUES_CODE = HRMS_QUES.QUES_CODE) "
					+ "  LEFT JOIN HRMS_QUESDTL  ON( HRMS_QUESDTL.QUESDTL_CODE = HRMS_EXIT_DTL.EXIT_QUES_RATE) "
					+ "  LEFT JOIN HRMS_EXIT_HDR ON(HRMS_EXIT_HDR.RESIGN_CODE = HRMS_EXIT_DTL.RESIGN_CODE) "
					+ "  WHERE  HRMS_EXIT_HDR.RESIGN_CODE= "
					+ result[0][0]
					+ "  ORDER BY HRMS_QUES.QUES_CODE";

			comm = getSqlModel().getSingleResult(selectData);
			// ArrayList<Object> tableList = new ArrayList<Object>();
			Object[][] innerObj = new Object[comm.length][1];

			for (int i = 0; i < comm.length; i++) {

				String sqlQuery = " SELECT DISTINCT EXIT_QUES_RATE , NVL(QUESDTL_NAME,' ') FROM HRMS_EXIT_DTL  "
						+ " LEFT JOIN HRMS_QUESDTL ON(HRMS_QUESDTL.QUESDTL_CODE=HRMS_EXIT_DTL.EXIT_QUES_RATE )  "
						+ " LEFT JOIN HRMS_EXIT_HDR ON(HRMS_EXIT_HDR.RESIGN_CODE = HRMS_EXIT_DTL.RESIGN_CODE)"
						+ " 	WHERE  RESIGN_CODE  = "
						+ comm[i][3]
						+ " AND  HRMS_QUESDTL.QUES_CODE ="
						+ comm[i][2]
						+ " and QUESDTL_CODE=" + comm[i][6];

				rateResult = getSqlModel().getSingleResult(sqlQuery);

				/* Handle ratings in try catch block.
				 * */
				try {
					if (rateResult.length > 0 && rateResult != null)
						innerObj[i][0] = rateResult[0][1];
				} catch (Exception e) {
					innerObj[i][0] = "";

				}

			} // end outer for loop

			Object[][] finalObj = new Object[comm.length][3];
			for (int i = 0; i < finalObj.length; i++) {
				finalObj[i][0] = comm[i][0];
				finalObj[i][1] = innerObj[i][0];
				finalObj[i][2] = comm[i][1];
			}// end i for loop

			String header = " Exit Interview Question Report\n\n\n\n ";

			String header1 = "\n Candidate Question Details \n";

			String colNames[] = { "Question Name", "Rating", "Comment" };

			// String colNames1[]={"Rating"};
			int[] cellwidth = { 40, 20, 40 };
			// int []cellwidth1={25};
			int[] alignmnet = { 0, 0, 0 };
			// int []alignmnet1={1};

			Date toDate = new Date();
			SimpleDateFormat today = new SimpleDateFormat("dd-MM-yyyy");
			String day = today.format(toDate);

			if (result != null && result.length != 0) {
				rg.addText(day, 0, 2, 0);
				rg.addFormatedText(header, 2, 0, 1, 0);
				Object[][] resObj = new Object[4][4];

				resObj[0][0] = "Employee Name   ";
				resObj[0][1] = String.valueOf(result[0][1]);
				resObj[0][2] = "";
				resObj[0][3] = "";

				resObj[1][0] = "Branch          			    ";
				resObj[1][1] = String.valueOf(result[0][3]);
				resObj[1][2] = "Designation 		           ";
				resObj[1][3] = String.valueOf(result[0][2]);

				resObj[2][0] = "Resignation Date    	    ";
				resObj[2][1] = String.valueOf(result[0][4]);

				resObj[2][2] = "Seperation Date  ";
				resObj[2][3] = String.valueOf(result[0][5]);

				resObj[3][0] = "Joining Date    	    ";
				resObj[3][1] = String.valueOf(result[0][6]);

				resObj[3][2] = "Exit Interview Date  ";
				resObj[3][3] = String.valueOf(result[0][7]);

				rg.tableBody(resObj, new int[] { 47, 53, 47, 53 }, new int[] {
						30, 70, 30, 70 });

			}// end if 

			if (comm.length > 0 || rateResult.length > 0) {
				rg.addFormatedText(header1, 2, 0, 1, 0);

				rg.tableBody(colNames, finalObj, cellwidth, alignmnet);
				// rg.tableBody(colNames1, rateResult, cellwidth1, alignmnet1);

			}// end if

			return rg;
		} catch (Exception e) {
			logger.error("Exit interview report" + e);
			return null;
		}

	}

	/**
	 * @param String
	 *            exit code
	 * @return void set the data of resigned employee for adding new record.
	 * in PDF format
	 */
	public void getexIntApp(ExitIntQues intques) {

		try {

			Object[] exCode = new Object[1];
			exCode[0] = intques.getExIntcode();
			String query = " SELECT distinct HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ "NVL(RANK_NAME,' '),NVL(CENTER_NAME,' '),TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY'),NVL(HRMS_RESIGN.RESIGN_EMP,' '),HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EXIT_HDR.RESIGN_CODE,' '),NVL(HRMS_RESIGN.RESIGN_CODE,' ') FROM HRMS_RESIGN"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+

					" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EXIT_HDR on(HRMS_EXIT_HDR.RESIGN_CODE=HRMS_RESIGN.RESIGN_CODE)"
					+ " LEFT JOIN HRMS_EXIT_DTL on(HRMS_EXIT_HDR.RESIGN_CODE=HRMS_EXIT_DTL.RESIGN_CODE)"
					+
					// " WHERE HRMS_EMP_OFFC.EMP_ID NOT IN (SELECT
					// EXITQUESHDR_ECODE FROM HRMS_EXITQUESHDR)"+
					"  WHERE  HRMS_EXIT_HDR.RESIGN_CODE= " + exCode[0];
			// ORDER BY HRMS_QUES.QUES_CODE";

			Object[][] result = getSqlModel().getSingleResult(query);

			for (int i = 0; i < result.length; i++) {
				intques.setEmpToken(checkNull(String.valueOf(result[i][0])
						.trim()));
				intques.setEmpName(checkNull(String.valueOf(result[i][1])
						.trim()));
				intques.setDesignation(checkNull(String.valueOf(result[i][2])
						.trim()));

				intques
						.setBranch(checkNull(String.valueOf(result[i][3])
								.trim()));
				intques.setResignDate(checkNull(String.valueOf(result[i][4])
						.trim()));
				intques.setSepDate(checkNull(String.valueOf(result[i][5])
						.trim()));
				intques.setJoinDate(checkNull(String.valueOf(result[i][6])
						.trim()));
				intques.setLeaveDate(checkNull(String.valueOf(result[i][7])
						.trim()));
				intques.setResignId(checkNull(String.valueOf(result[i][8])
						.trim()));
				intques.setEmpId(String.valueOf(result[i][9]));
				intques.setExIntcode(String.valueOf(result[i][10]));
				intques.setResignCode(String.valueOf(result[i][11]));

			}// end for loop

		} catch (Exception e) {
			logger.error("Exception in getexIntApp() method  :"+e);
		}
	}

	public String checkNull(String result) {
		/*
		 * method to check the null value
		 * 
		 */
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
