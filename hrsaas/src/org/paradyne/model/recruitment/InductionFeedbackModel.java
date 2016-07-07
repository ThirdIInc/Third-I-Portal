package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.InductionFeedback;
import org.paradyne.lib.ModelBase;

/**
 * @author varunk
 *
 */
public class InductionFeedbackModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InductionFeedbackModel.class);

	public void getrecord(InductionFeedback indFeed, HttpServletRequest request) {
		Object[][] result = null;
		ArrayList<Object> tableList = new ArrayList<Object>();
		String query = "SELECT DISTINCT HRMS_QUES.QUES_CODE,QUES_NAME FROM HRMS_QUES "
				+ " INNER JOIN HRMS_QUESDTL ON(HRMS_QUESDTL.QUES_CODE=HRMS_QUES.QUES_CODE)   "
				+ " WHERE QUES_TYPE='I' ";
		result = getSqlModel().getSingleResult(query);
		try {
			/**
			 * this for loop for no of questions
			 */
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {
					TreeMap<String, String> map = new TreeMap<String, String>();
					InductionFeedback bean = new InductionFeedback();
					Object[][] options = null;
					String optionQuery = "SELECT DISTINCT QUESDTL_CODE ,QUESDTL_NAME  FROM HRMS_QUESDTL "
							+ " WHERE QUES_CODE="
							+ result[i][0]
							+ " ORDER BY QUESDTL_CODE";
					options = getSqlModel().getSingleResult(optionQuery);

					bean.setQuesCode(String.valueOf(result[i][0]));
					bean.setQuesName(String.valueOf(result[i][1]));

					/**
					 * this for loop for no of options...
					 */
					for (int j = 0; j < options.length; j++) {
						map.put(String.valueOf(options[j][0]), String
								.valueOf(options[j][1]));
					}
					bean.setTmap(map);
					map = null;
					tableList.add(bean);
				}
				indFeed.setQuestionList(tableList);
			}
		} catch (Exception e) {
			logger.error("exception in for loop", e);
		}
	}

	public String save(InductionFeedback indFeed, HttpServletRequest request,
			String[] quesCode, String[] rating, String[] comment) {

		String message = "";
		try {
			Object[][] maxCode = null;
			boolean result = false;
			String maxQuery = "SELECT NVL(MAX(INDUC_FEED_CODE),0)+1 FROM HRMS_REC_INDUC_FEEDHDR";
			maxCode = getSqlModel().getSingleResult(maxQuery);
			if (indFeed.getSuggestion().equals("")
					|| indFeed.getSuggestion().equals("null")
					|| indFeed.getSuggestion().equals(null)) {
				indFeed.setSuggestion("");
			}
			if (maxCode != null && maxCode.length > 0) {
				String insertHdr = "INSERT INTO HRMS_REC_INDUC_FEEDHDR (INDUC_FEED_CODE,INDUC_CODE,INDUC_FEED_EMPID,INDUC_SUGGESTION,ATTACH_FILE) "
						+ " VALUES ("
						+ maxCode[0][0]
						+ ","
						+ indFeed.getInductionCode()
						+ ","
						+ indFeed.getUserEmpId()
						+ ",'"
						+ indFeed.getSuggestion()
						+ "','"
						+ indFeed.getAttachFile() + "')";
				result = getSqlModel().singleExecute(insertHdr);

				/**
				 * this for inserting feedback detail
				 */
				if (result) {

					for (int i = 0; i < quesCode.length; i++) {
						String commentBlank = "";
						if (comment[i].equals("") || comment[i].equals("null")
								|| comment[i].equals(null)) {
							commentBlank = "";
						} else {
							commentBlank = comment[i];
						}
						String insertDtl = "INSERT INTO HRMS_REC_INDUC_FEEDDTL (INDUC_FEED_CODE,INDUC_CODE,INDUC_QUES_RATING,INDUC_QUES_COMMENT,INDUC_QUES_CODE) "
								+ " VALUES("
								+ maxCode[0][0]
								+ ","
								+ indFeed.getInductionCode()
								+ ","
								+ rating[i]
								+ ",'"
								+ commentBlank
								+ "',"
								+ quesCode[i]
								+ ")";
						boolean resultDtl = getSqlModel().singleExecute(
								insertDtl);

						String updateInduc = "UPDATE HRMS_REC_INDUC_PARTI SET INDUC_FEED_STATUS = 'Y' "
								+ " WHERE INDUC_EMPID = "
								+ indFeed.getUserEmpId()
								+ " AND INDUC_CODE = "
								+ indFeed.getInductionCode() + "";
						boolean resultParti = getSqlModel().singleExecute(
								updateInduc);
					}
					message = "1";
				}
			}
			reset(indFeed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public void reset(InductionFeedback indFeed) {
		try {
			indFeed.setInductionName("");
			indFeed.setInductionFrom("");
			indFeed.setInductionTo("");
			indFeed.setInducVenue("");
			indFeed.setInducDesc("");
			indFeed.setContactPerson("");
			indFeed.setInductionName("");
			indFeed.setInductionCode("");
			indFeed.setSuggestion("");
			indFeed.setAttachFile("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}// end of checkNull

	public void showFeedbackdetails(InductionFeedback indFeed,
			HttpServletRequest request, String empCode, String inducCode) {
		try {
			Object[][] dataHdr = null;
			Object[][] dataDtl = null;
			ArrayList<Object> tableList = new ArrayList<Object>();
			String query = "SELECT INDUC_NAME,TO_CHAR(INDUC_FROMDATE,'DD-MM-YYYY'),TO_CHAR(INDUC_TODATE,'DD-MM-YYYY'), "
					+ " INDUC_DTLCODE,HRMS_REC_INDUC_PARTI.INDUC_CODE,NVL(INDUC_DESC,' '),INDUC_VENUE,EMP_FNAME||' '||EMP_LNAME,INDUC_SUGGESTION,ATTACH_FILE "
					+ " FROM HRMS_REC_INDUC_PARTI "
					+ " LEFT JOIN HRMS_REC_INDUC_HDR ON (HRMS_REC_INDUC_HDR.INDUC_CODE = HRMS_REC_INDUC_PARTI.INDUC_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_INDUC_HDR.INDUC_CONTACT) "
					+ " LEFT JOIN HRMS_REC_INDUC_FEEDHDR ON (HRMS_REC_INDUC_FEEDHDR.INDUC_CODE = HRMS_REC_INDUC_PARTI.INDUC_CODE)"
					+ " WHERE INDUC_EMPID = "
					+ empCode
					+ " AND INDUC_FEED_STATUS = 'Y' AND HRMS_REC_INDUC_PARTI.INDUC_CODE = "
					+ inducCode 
					+ " AND HRMS_REC_INDUC_FEEDHDR.INDUC_FEED_CODE = (SELECT INDUC_FEED_CODE FROM HRMS_REC_INDUC_FEEDHDR " 
					+" WHERE INDUC_FEED_EMPID = "+ empCode +" AND INDUC_CODE = "+inducCode +")";
			dataHdr = getSqlModel().getSingleResult(query);
			if (dataHdr != null && dataHdr.length > 0) {
				indFeed.setInductionName(String.valueOf(dataHdr[0][0]));
				indFeed.setInductionFrom(String.valueOf(dataHdr[0][1]));
				indFeed.setInductionTo(String.valueOf(dataHdr[0][2]));
				indFeed.setInductionDtlCode(String.valueOf(dataHdr[0][3]));
				indFeed.setInducDesc(String.valueOf(dataHdr[0][5]));
				indFeed.setInducVenue(String.valueOf(dataHdr[0][6]));
				indFeed.setContactPerson(String.valueOf(dataHdr[0][7]));
				indFeed.setSuggestion(checkNull(String.valueOf(dataHdr[0][8])));
				indFeed.setAttachFile(checkNull(String.valueOf(dataHdr[0][9])));

				/**
				 * Previous query showing questions of all the users after
				 * clicking on View button in particular recors. now it will
				 * showing questions of selected record only . Add one more
				 * record as INDUC_SUGGESTION
				 */
				String dtlQuery = "SELECT distinct subStr(QUES_NAME,1,50),QUESDTL_NAME,INDUC_QUES_COMMENT,INDUC_SUGGESTION "
						+ " FROM HRMS_REC_INDUC_FEEDDTL "
						+ " INNER JOIN HRMS_REC_INDUC_FEEDHDR ON (HRMS_REC_INDUC_FEEDHDR.INDUC_CODE=HRMS_REC_INDUC_FEEDDTL.INDUC_CODE "
						+ " and HRMS_REC_INDUC_FEEDHDR.INDUC_FEED_CODE=HRMS_REC_INDUC_FEEDDTL.INDUC_FEED_CODE "
						+ " AND HRMS_REC_INDUC_FEEDHDR.INDUC_CODE ="
						+ inducCode
						+ " AND INDUC_FEED_EMPID="
						+ empCode
						+ ") "
						+ " INNER JOIN HRMS_QUES ON (HRMS_QUES.QUES_CODE = HRMS_REC_INDUC_FEEDDTL.INDUC_QUES_CODE) "
						+ " INNER JOIN HRMS_QUESDTL ON (HRMS_QUESDTL.QUES_CODE = HRMS_QUES.QUES_CODE "
						+ " and HRMS_REC_INDUC_FEEDDTL.INDUC_QUES_RATING = HRMS_QUESDTL.QUESDTL_CODE)";

				dataDtl = getSqlModel().getSingleResult(dtlQuery);

				if (dataDtl != null && dataDtl.length > 0) {
					for (int j = 0; j < dataDtl.length; j++) {
						InductionFeedback bean = new InductionFeedback();
						bean.setQuesName(String.valueOf(dataDtl[j][0]));
						bean.setRating(String.valueOf(dataDtl[j][1]));
						bean.setComments(checkNull(String
								.valueOf(dataDtl[j][2])));
						tableList.add(bean);
						indFeed.setSuggestion(checkNull(String
								.valueOf(dataDtl[j][3])));
					}
					indFeed.setQuestionList(tableList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
