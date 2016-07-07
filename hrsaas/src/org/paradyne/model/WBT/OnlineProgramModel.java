/**
 * 
 */
package org.paradyne.model.WBT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.WBT.OnlineProgram;
import org.paradyne.lib.ModelBase;




/**
 * @author AA0491
 * 
 */
public class OnlineProgramModel extends ModelBase {

	/**
	 * 
	 */
	public OnlineProgramModel() {
		// TODO Auto-generated constructor stub
	}

	public void getListDetails(OnlineProgram bean) {
		try {
			// TODO Auto-generated method stub
			String query = " SELECT PROGRAM_NAME ,APPLICATION_CODE,PROGRAM_NAME,PROGRAM_ID "
					+ "  ,TO_CHAR(PROGRAM_FRM_DATE,'DD-MM-YYYY'),PROGRAM_TIME,PROGRAM_CODE "
					+ " FROM WBT_SCHEDULE_PROGRAM "
					+ " INNER JOIN WBT_PROGRAM_HDR ON(WBT_PROGRAM_HDR.PROGRAM_ID =WBT_SCHEDULE_PROGRAM.PROGRAM_CODE) "
					+ " WHERE PROGRAM_USER_CODE=1 AND PROGRAM_USER_TYPE='T' "
					+ " AND PROGRAM_STATUS='N'  ";
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					OnlineProgram innerBean = new OnlineProgram();
					innerBean.setProgramName(checkNull(String
							.valueOf(data[i][0])));
					innerBean.setApplicationCode(checkNull(String
							.valueOf(data[i][1])));
					innerBean.setProgramTemplateName(checkNull(String
							.valueOf(data[i][2])));
					innerBean.setProgramTemplateCode(checkNull(String
							.valueOf(data[i][3])));
					innerBean.setProgramDateItr(checkNull(String
							.valueOf(data[i][4])));
					innerBean.setProgramTimeItr(checkNull(String
							.valueOf(data[i][5])));
					innerBean.setOnlineProgramCodeItr(checkNull(String
							.valueOf(data[i][6])));
					list.add(innerBean);
				}
			}
			bean.setProgramList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void startProgram(OnlineProgram bean, HttpServletRequest request,
			String programCode) {
		try {

			/*
			 * String query = " SELECT
			 * PROGRAM_NAME,PROGRAM_CONTENT,WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE,SUBJECT_NAME
			 * ,case when ONLINE_TEST_STATUS='Y' Then 'Completed' else
			 * 'Incompleted' end status " + " ,case when ONLINE_MARK_READ='Y'
			 * then 'NA' else to_CHAR(TEST_OBT_MARKS) end " + ",case when
			 * TEST_RESULT='P' then 'Pass' when TEST_RESULT='F' then 'Fail' when
			 * TEST_RESULT='N' then 'NA' else '' end result " + "
			 * ,IS_PROGRAM_ORDER FROM WBT_PROGRAM_DTL " + " INNER JOIN
			 * WBT_PROGRAM_HDR ON(WBT_PROGRAM_HDR.PROGRAM_ID
			 * =WBT_PROGRAM_DTL.PROGRAM_ID)" + " INNER JOIN HRMS_REC_SUBJECT ON(
			 * HRMS_REC_SUBJECT.SUBJECT_CODE
			 * =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE)" + " left JOIN
			 * WBT_ONLINETEST_RESULT ON(WBT_ONLINETEST_RESULT.ONLINE_MODULE_CODE =
			 * WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE AND ONLINE_SECTION_CODE is
			 * null and WBT_ONLINETEST_RESULT.ONLINE_PROGRAM_ID=" + programCode + ") " + "
			 * WHERE WBT_PROGRAM_DTL.PROGRAM_ID=" + programCode + " AND
			 * SUBJECT_STATUS='A' ORDER BY PROGRAM_ORDER ";
			 */

			String query = " SELECT   PROGRAM_NAME,PROGRAM_CONTENT "
					+ " FROM  WBT_PROGRAM_DTL   "
					+ " INNER JOIN  WBT_PROGRAM_HDR ON(WBT_PROGRAM_HDR.PROGRAM_ID =WBT_PROGRAM_DTL.PROGRAM_ID)"
					+ "WHERE   WBT_PROGRAM_DTL.PROGRAM_ID=" + programCode
					+ " ORDER BY PROGRAM_ORDER ";

			Object[][] queryDataObj = getSqlModel().getSingleResult(query);

			if (queryDataObj != null && queryDataObj.length > 0) {
				bean.setProgramCode(programCode);
				bean.setProgramName(checkNull(String
						.valueOf(queryDataObj[0][0])));
				request.setAttribute("programInstructions", checkNull(String
						.valueOf(queryDataObj[0][1])));
				// request.setAttribute("moduleList", queryDataObj);

			}
			/*
			 * String modulQuery = " SELECT
			 * PROGRAM_MODULE_CODE,SUBJECT_NAME,IS_PROGRAM_ORDER,NVL(MODULE_NO_OF_ATTEMPT,0) " + "
			 * FROM WBT_PROGRAM_DTL " + " INNER JOIN HRMS_REC_SUBJECT ON(
			 * HRMS_REC_SUBJECT.SUBJECT_CODE
			 * =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) " + " WHERE
			 * WBT_PROGRAM_DTL.PROGRAM_ID=" + programCode + " AND
			 * SUBJECT_STATUS='A' ORDER BY PROGRAM_ORDER ";
			 * 
			 * Object moduleNameObj[][] = getSqlModel()
			 * .getSingleResult(modulQuery);
			 */
			getMarksAndResult(programCode, bean);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void callModule(OnlineProgram bean, HttpServletRequest request,
			String moduleCode, String programCode, String sectionCode,
			String from, String isSubmit) {

		try {

			if (sectionCode == null) {
				bean.setShowModuleDtlFlag("true");
			}

			System.out.println("from  ----------------  " + from);

			bean.setSource(from);

			String query = "  SELECT PROGRAM_MODULE_CODE,  SUBJECT_NAME,SUBJECT_INSTRUCTION ,PROGRAM_NAME "
					+ " FROM WBT_PROGRAM_DTL "
					+ " INNER JOIN  HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) "
					+ " INNER JOIN  WBT_PROGRAM_HDR ON( WBT_PROGRAM_HDR.PROGRAM_ID =WBT_PROGRAM_DTL.PROGRAM_ID) "
					+ " WHERE WBT_PROGRAM_DTL.PROGRAM_ID="
					+ programCode
					+ " AND  PROGRAM_MODULE_CODE="
					+ moduleCode
					+ "  order by PROGRAM_ORDER ";

			Object[][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				bean.setShowModuleDtlFlag("true");
				bean.setSectionModuleCode(checkNull(String
						.valueOf(dataObj[0][0])));
				bean.setSectionModuleName(checkNull(String
						.valueOf(dataObj[0][1])));
				bean.setSectionProgramCode(programCode);
				bean.setSectionProgramName(checkNull(String
						.valueOf(dataObj[0][3])));

				request.setAttribute("moduleInstruction", checkNull(String
						.valueOf(dataObj[0][2])));
			}

			String isProgramContentQuery = "   SELECT SUBJECT_CONTENT "
					+ "  FROM WBT_PROGRAM_DTL "
					+ "   INNER JOIN  HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) "
					+ " WHERE PROGRAM_ID=" + programCode
					+ " AND  PROGRAM_MODULE_CODE=" + moduleCode
					+ " AND IS_PROGRAM_CONTENT='Y' ";

			Object[][] programContentQueryObj = getSqlModel().getSingleResult(
					isProgramContentQuery);

			if (programContentQueryObj != null
					&& programContentQueryObj.length > 0) {
				request.setAttribute("moduleContent", checkNull(String
						.valueOf(programContentQueryObj[0][0])));

			}
			
			
			isProgramContentQuery = "   SELECT IS_PROGRAM_CONTENT "
					+ " FROM WBT_PROGRAM_DTL "
					+ " INNER JOIN  HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) "
					+ " WHERE PROGRAM_ID=" + programCode
					+ " AND  PROGRAM_MODULE_CODE=" + moduleCode;

			programContentQueryObj = getSqlModel().getSingleResult(isProgramContentQuery);
		
			if (programContentQueryObj != null && programContentQueryObj.length > 0) {
				if(String.valueOf(programContentQueryObj[0][0]).equalsIgnoreCase("Y")){
					String contentListQuery = " SELECT HRMS_WBT_CONTENT.CONTENT_ID," +
							" NVL(HRMS_WBT_CONTENT.CONTENT_TITLE,' '), NVL(HRMS_WBT_CONTENT.CONTENT_URL, ' ') " +
							" FROM HRMS_WBT_CONTENT" +
							" WHERE HRMS_WBT_CONTENT.CONTENT_SEC_TYPE like 'subject'" +
							" AND HRMS_WBT_CONTENT.CONTENT_SEC_ID = '" + moduleCode +"' ORDER BY HRMS_WBT_CONTENT.CONTENT_ORDER DESC "; 
					Object[][] contentListQueryObj = getSqlModel().getSingleResult(contentListQuery);
					
					if (contentListQueryObj != null && contentListQueryObj.length > 0) {
						LinkedHashMap  contentList = new LinkedHashMap ();
						for (int i = 0; i < contentListQueryObj.length; i++) {
							contentList.put(String.valueOf(contentListQueryObj[i][0])+"|"+String.valueOf(contentListQueryObj[i][2]),
										String.valueOf(contentListQueryObj[i][1]));
						}
						System.out.println(contentList);
						bean.setContentList(contentList);
					}
				}

			}
			
			
			/*
			 * String sectionQuery = " SELECT SECTION_ID, NVL(CAT_NAME,'')" + "
			 * FROM WBT_PROGRAM_SECTION " + " INNER JOIN HRMS_REC_CATEGORY
			 * ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID ) " + "
			 * WHERE PROGRAM_ID=" + programCode + " and MODULE_ID=" +
			 * moduleCode;
			 */

			/*
			 * String sectionQuery = " SELECT WBT_PROGRAM_SECTION.SECTION_ID,
			 * NVL(CAT_NAME,'') " + " ,case when ONLINE_TEST_STATUS='Y' Then
			 * 'Completed' else 'Incompleted' end status " + " ,case when
			 * ONLINE_MARK_READ='Y' then 'NA' else to_CHAR(TEST_OBT_MARKS) end
			 * ,case when TEST_RESULT='P' then 'Pass' when TEST_RESULT='F' then
			 * 'Fail' when TEST_RESULT='N' then 'NA' else '' end result " + "
			 * FROM WBT_PROGRAM_SECTION " + " INNER JOIN HRMS_REC_CATEGORY
			 * ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID ) " + "
			 * left JOIN WBT_ONLINETEST_RESULT
			 * ON(WBT_ONLINETEST_RESULT.ONLINE_SECTION_CODE =
			 * WBT_PROGRAM_SECTION.SECTION_ID and ONLINE_PROGRAM_ID=" +
			 * programCode + ")" + " WHERE PROGRAM_ID=" + programCode + " and
			 * MODULE_ID=" + moduleCode + " ORDER BY SECTION_ORDER "; // +" and
			 * ONLINE_SECTION_CODE is null ";
			 * 
			 * Object sectionDataObj[][] = getSqlModel().getSingleResult(
			 * sectionQuery);
			 */
			Object[][] sectionDataObj = getMarksAndResultForSection(
					programCode, moduleCode, bean);

			ArrayList<Object> sectionList = null;
			if (sectionDataObj != null && sectionDataObj.length > 0) {
				
				sectionList = new ArrayList<Object>();
				for (int i = 0; i < sectionDataObj.length; i++) {
					OnlineProgram innerbean = new OnlineProgram();
					innerbean.setSectionCodeItt(checkNull(String
							.valueOf(sectionDataObj[i][0])));
					innerbean.setSectionNameItt(checkNull(String
							.valueOf(sectionDataObj[i][1])));
					innerbean.setSectionAttemptRemaining(checkNull(String
							.valueOf(sectionDataObj[i][2])));
					innerbean.setSectionCompletionStatusItt(checkNull(String
							.valueOf(sectionDataObj[i][3])));
					innerbean.setSectionMarksItt(checkNull(String
							.valueOf(sectionDataObj[i][4])));
					innerbean.setSectionResultItt(checkNull(String
							.valueOf(sectionDataObj[i][5])));
					sectionList.add(innerbean);
				}
			}
			bean.setSectionList(sectionList);
			/*
			 * if (sectionDataObj != null && sectionDataObj.length > 0) {
			 * request.setAttribute("sectionData", sectionDataObj); }
			 */

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {

				String sectionNameQuery = "    SELECT SECTION_ID, NVL(CAT_NAME,'') "
						+ " FROM  WBT_PROGRAM_SECTION "
						+ " INNER JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID )"
						+ " WHERE PROGRAM_ID="
						+ programCode
						+ " and  MODULE_ID="
						+ moduleCode
						+ "  and SECTION_ID="
						+ sectionCode;

				Object[][] sectionNameObj = getSqlModel().getSingleResult(
						sectionNameQuery);

				if (sectionNameObj != null && sectionNameObj.length > 0) {
					bean.setSectionName(checkNull(String
							.valueOf(sectionNameObj[0][1])));
				}

				System.out.println("sectionCode====================  "
						+ sectionCode);

				bean.setShowModuleDtlFlag("false");

				bean.setSectionCode(sectionCode);

				String isSectionContentQuery = "   SELECT CAT_CONTENT  FROM WBT_PROGRAM_SECTION  INNER JOIN HRMS_REC_CATEGORY  ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID )"
						+ " WHERE  PROGRAM_ID= "
						+ programCode
						+ " AND MODULE_ID= "
						+ moduleCode
						+ " "
						+ " AND SECTION_ID="
						+ sectionCode
						+ " AND  IS_SECTION_CONTENT='Y'";

				Object sectionContentObj[][] = getSqlModel().getSingleResult(
						isSectionContentQuery);

				if (sectionContentObj != null && sectionContentObj.length > 0) {
					request.setAttribute("sectionContent", checkNull(String
							.valueOf(sectionContentObj[0][0])));
				}
				
				
				
				isSectionContentQuery = "   SELECT IS_SECTION_CONTENT "
						+ " FROM WBT_PROGRAM_SECTION "
						+ " INNER JOIN HRMS_REC_CATEGORY  ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID )"
						+ " WHERE  PROGRAM_ID= " + programCode
						+ " AND MODULE_ID= " + moduleCode
						+ " AND SECTION_ID=" + sectionCode;
	
				Object isSectionContentObj[][] = getSqlModel().getSingleResult(isSectionContentQuery);
	
				if (isSectionContentObj != null && isSectionContentObj.length > 0) {
					if(String.valueOf(isSectionContentObj[0][0]).equalsIgnoreCase("Y")){
						String contentListQuery = " SELECT HRMS_WBT_CONTENT.CONTENT_ID," +
								" NVL(HRMS_WBT_CONTENT.CONTENT_TITLE,' '), NVL(HRMS_WBT_CONTENT.CONTENT_URL,' ')  " +
								" FROM HRMS_WBT_CONTENT" +
								" WHERE HRMS_WBT_CONTENT.CONTENT_SEC_TYPE like 'category'" +
								" AND HRMS_WBT_CONTENT.CONTENT_SEC_ID = '" + sectionCode +"' ORDER BY HRMS_WBT_CONTENT.CONTENT_ORDER DESC "; 
						Object[][] contentListQueryObj = getSqlModel().getSingleResult(contentListQuery);
				
						if (contentListQueryObj != null && contentListQueryObj.length > 0) {
							LinkedHashMap  contentList = new LinkedHashMap ();
							for (int i = 0; i < contentListQueryObj.length; i++) {
								contentList.put(String.valueOf(contentListQueryObj[i][0])+"|"+String.valueOf(contentListQueryObj[i][2]),
										String.valueOf(contentListQueryObj[i][1]));
							}	
							System.out.println(contentList);
							bean.setContentList(contentList);
						} else {
							bean.setContentList(null);
						}
					} else {
						bean.setContentList(null);
					}
				} else {
					bean.setContentList(null);
				}
				
				

			}

			/*
			 * Object[][] tempAddQues = null; tempAddQues =
			 * getTestTemplateAddQues(bean, programCode, moduleCode,
			 * sectionCode,isSubmit); ArrayList<Object> questionList = new
			 * ArrayList<Object>();
			 * 
			 * String[] randomQueCodes = new String[tempAddQues.length]; for
			 * (int i = 0; i < randomQueCodes.length; i++) { randomQueCodes[i] =
			 * String.valueOf(tempAddQues[i][0]); }
			 * 
			 * for (int i = 0; i < tempAddQues.length; i++) { OnlineProgram
			 * innerbean = new OnlineProgram();
			 * innerbean.setRandomQuesCodes(randomQueCodes[i]);
			 * questionList.add(innerbean); }
			 * bean.setRandomQueList(questionList);
			 */
			ArrayList<Object> questionList = new ArrayList<Object>();
			Object[][] templateDtl = null;
			Object[][] tempAddQues = null;
			tempAddQues = getTestTemplateAddQues(bean, programCode, moduleCode,
					sectionCode, isSubmit);

			if (tempAddQues != null && tempAddQues.length > 0) {
				if (bean.getIsRandomQues().equals("N")) {
					Random rgen = new Random(); // Random number generator
					String[] randomQueCodes = new String[tempAddQues.length];
					for (int i = 0; i < randomQueCodes.length; i++) {
						randomQueCodes[i] = "" + tempAddQues[i][0];
					}
					for (int i = 0; i < randomQueCodes.length; i++) {
						int randomPosition = rgen
								.nextInt(randomQueCodes.length);
						String temp = randomQueCodes[i];
						randomQueCodes[i] = randomQueCodes[randomPosition];
						randomQueCodes[randomPosition] = temp;
					}

					/**
					 * this for loop is run to set the hidden iterator list...
					 */
					for (int i = 0; i < tempAddQues.length; i++) {
						OnlineProgram innerbean = new OnlineProgram();
						innerbean.setRandomQuesCodes(randomQueCodes[i]);
						questionList.add(innerbean);
					}
					bean.setRandomQueList(questionList);
				}

				else {
					/**
					 * to retirieve the TESTTEMPDTL VALUES LIKE SUBJECT
					 * CODE,CATEGORY CODE,LEVEL...
					 */

					Object[] totalQuesCodes = null;
					/**
					 * THIS OBJECT CONTAINS THE TOTAL NO OF QUESTION CODES TO BE
					 * APPEARED IN TEST
					 */
					totalQuesCodes = getQuesCodes(bean, programCode,
							moduleCode, sectionCode);

					/*
					 * if (totalQuesCodes == null) { result =
					 * "totalNoOfQuesNotDefined"; return result; }
					 */
					Random rgen = new Random(); // Random number generator
					String[] randomQueCodes = new String[totalQuesCodes.length];
					// --- Initialize the array to the ints 0-51
					for (int i = 0; i < randomQueCodes.length; i++) {
						randomQueCodes[i] = "" + totalQuesCodes[i];
					}
					// --- Shuffle by exchanging each element randomly
					for (int i = 0; i < randomQueCodes.length; i++) {
						int randomPosition = rgen
								.nextInt(randomQueCodes.length);
						String temp = randomQueCodes[i];
						randomQueCodes[i] = randomQueCodes[randomPosition];
						randomQueCodes[randomPosition] = temp;
					}

					/**
					 * this for loop is run to set the hidden iterator list...
					 */
					System.out
							.println("totalQuesCodes.length=================="
									+ totalQuesCodes.length);

					for (int i = 0; i < totalQuesCodes.length; i++) {
						OnlineProgram innerbean = new OnlineProgram();
						innerbean.setRandomQuesCodes(randomQueCodes[i]);
						questionList.add(innerbean);
					}
					bean.setRandomQueList(questionList);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void startTest(OnlineProgram bean, HttpServletRequest request,
			String moduleCode, String programCode, String sectionCode,
			String from) {

		try {

			bean.setSource(from);
			String randomQuesCodes[] = request
					.getParameterValues("randomQuesCodes");
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			String subjAnsForPrevious = "";
			String answerUploadedDoc = "";

			subjAnsForPrevious = bean.getSubjectAns();
			answerUploadedDoc = bean.getAnswerUploadedDoc();

			Object[][] scoreDtl = new Object[1][2];

			int sequence = 1;

			String sequenceQuery = " SELECT NVL(MAX(ONLINE_SEQS),0)+1 FROM WBT_ONLINETEST "
					+ " where WBT_ONLINETEST.ONLINE_PROGRAM_ID = "
					+ programCode
					+ " "
					+ " and ONLINE_MODULE_CODE="
					+ moduleCode;

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				sequenceQuery += " and  ONLINE_SECTION_CODE =" + sectionCode;
			} else {
				sequenceQuery += " and ONLINE_SECTION_CODE is null";
			}
			sequenceQuery += " and ONLINE_USER_CODE=" + (String)session.getAttribute("userCode") +" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
			+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_ATTEMPT=" + bean.getAttempt();

			Object[][] seqDataObj = getSqlModel()
					.getSingleResult(sequenceQuery);

			if (seqDataObj != null && seqDataObj.length > 0) {
				sequence = Integer.parseInt(String.valueOf(seqDataObj[0][0]));
			}

			String answerCode = request.getParameter("optionCode");// this is
			// answers
			// given by
			// the
			// candidate
			String answerCorrect = request.getParameter("finalAnswer");// this
			// is
			// answer
			// whether
			// correct
			// or
			// not

			/**
			 * this if condition is checked on start...initially sequence is set
			 * 1
			 */
			if (request.getParameter("sequenceCode") == null
					|| request.getParameter("sequenceCode").equals("")
					|| request.getParameter("sequenceCode").equals("null")) {
				sequence = Integer.parseInt(String.valueOf(seqDataObj[0][0]));
				bean.setSequenceCode(String.valueOf(sequence));
			}

			/**
			 * here in else sequence is added by 1
			 */
			else {
				bean.setPreviousButton("true");
				sequence = Integer.parseInt(request
						.getParameter("sequenceCode")) + 1;
				// onlineTest.setSubjectAns("");
				bean.setSequenceCode(String.valueOf(sequence));
			}

			/**
			 * this to check if the data for the sequence is present or not.
			 * Here sequence code , candidate code and requisition code is
			 * passed....if it is present than no Insert query is fired... Only
			 * Update and the following data is retrieved....
			 */
			Object[][] sequenceData = null;
			int userCode = Integer.parseInt(bean.getUserCode());
			int applicationCode = Integer.parseInt(bean.getApplicationCode()
					.equals("") ? "0" : bean.getApplicationCode());
			String userType = bean.getUserType();

			sequenceData = getQuesFromSequence(sequence, userCode,
					applicationCode, programCode, userType, moduleCode,
					sectionCode, bean);

			/**
			 * here is sequenceData is not null dat means the Next button is
			 * pressed after previous is clicked first
			 */

			/*
			 * System.out
			 * .println("sequenceData.length=======================================" +
			 * sequenceData.length);
			 */

			if (sequenceData != null && sequenceData.length > 0) {
				bean.setQuestionName(String.valueOf(sequenceData[0][1]));// QUESTION
				// NAME
				bean.setQuestionLevel(String.valueOf(sequenceData[0][4]));// QUESTION
				// COMPLEXITY
				// LEVEL..

				bean.setTestModuleCode(checkNull(moduleCode));
				bean.setTestModuleName(checkNull(String
						.valueOf(sequenceData[0][9])));
				bean.setTestProgramCode(checkNull(programCode));

				setProgramName(programCode, bean);

				bean.setTestSectionName(checkNull(String
						.valueOf(sequenceData[0][10])));
				bean.setTestSectionCode(checkNull(String
						.valueOf(sequenceData[0][11])));
				/*
				 * bean.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
				 * 
				 * bean.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
				 * 
				 * bean.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
				 */

				if (String.valueOf(sequenceData[0][2]).equals("S")) {
					if (!checkNull(String.valueOf(sequenceData[0][7])).equals(
							"")) {
						bean.setDocumentNotAttachedFlag(true);
						bean.setQuestionUploadedDoc(checkNull(String
								.valueOf(sequenceData[0][7])));
					}
					bean.setAnswerUploadedDoc(checkNull(String
							.valueOf(sequenceData[0][8])));
				} else {
					bean.setDocumentNotAttachedFlag(false);
				}
				getQuesOption(sequenceData, bean, request);
				if (bean.getLastQuestionSequence().equals("" + sequence)) {
					bean.setSubmitButtonFlag("true");// end of test
					bean.setNextButtonFlag("false"); // no more questions for
					// the test
				}

				String query = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), "
						+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(WBT_ONLINETEST.ANSWER_UPLOADED_DOC,''), "
						+ " HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
						+ " FROM WBT_ONLINETEST "
						+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = WBT_ONLINETEST.ONLINE_QUES_CODE) "
						+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
						+ " LEFT JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
						+ " WHERE ONLINE_SEQS = "
						+ (sequence - 1)
						+ " AND ONLINE_USER_CODE  = " + userCode
				+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
				+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";
				if (!String.valueOf(applicationCode).equals("0")
						&& !String.valueOf(applicationCode).equals("")) {
					query += "AND ONLINE_APPLICATION_CODE = " + applicationCode;
				}
				query += " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID  = "
						+ programCode;

				query += " AND ONLINE_ATTEMPT=" + bean.getAttempt();

				sequenceData = getSqlModel().getSingleResult(query);

				if (sequenceData != null && sequenceData.length > 0) {
					bean.setQuestionLevel(String.valueOf(sequenceData[0][4]));// QUESTION
					// COMPLEXITY
					// LEVEL..
					bean.setSubjectAns(checkNull(String
							.valueOf(sequenceData[0][5])));
					bean.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
					if (String.valueOf(sequenceData[0][2]).equals("S")) {
						if (!checkNull(String.valueOf(sequenceData[0][7]))
								.equals("")) {
							bean.setDocumentNotAttachedFlag(true);
							bean.setQuestionUploadedDoc(checkNull(String
									.valueOf(sequenceData[0][7])));
						}
						bean.setAnswerUploadedDoc(checkNull(String
								.valueOf(sequenceData[0][8])));
					} else {
						bean.setDocumentNotAttachedFlag(false);
					}
					/*
					 * bean.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
					 * bean.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
					 */

				}
				/**
				 * THIS IS TO CHECK WHETHER ANSWER IS GIVEN OR NOT...IF YES
				 * WHETHER THE ANSWER IS COORECT OR NOT. IF THE ANSWER IS WRONG
				 * THEN "MARKS FOR NO ANSWER" IS SET..IF "answerCorrect" IS
				 * BLANK THEN "MARKS FOR NO ANSWER" IS SET IN "SCORE"
				 */
				System.out
						.println("equal weightage 111111111111111111111111--------------"
								+ bean.getEqualweightage());
				/**
				 * THIS IS TO CHECK WHETHER ANSWER IS GIVEN OR NOT...IF YES
				 * WHETHER THE ANSWER IS COORECT OR NOT. IF THE ANSWER IS WRONG
				 * THEN "MARKS FOR NO ANSWER" IS SET..IF "answerCorrect" IS
				 * BLANK THEN "MARKS FOR NO ANSWER" IS SET IN "SCORE"
				 */
				if (bean.getEqualweightage().equals("Y")) {
					/**
					 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E
					 * SAME SCORE FOR EVERY RIGHT ANSWER
					 */

					/**
					 * THIS IS FOR QUESTIONS HAVE EQUAL WEIGHTAGE....
					 */
					scoreDtl = getScoreEqWtSubmit(bean, answerCode,
							answerCorrect);
				} else {
					/**
					 * THIS IS FOR QUESTION MARKS BASED UPON THEIR COMPLEXITY
					 * LEVEL..
					 */
					scoreDtl = getScoreForCorrectSubmit(bean, answerCode,
							answerCorrect, sequence);
				}
				String updatePreviousQues = "UPDATE WBT_ONLINETEST SET ONLINE_ANSWER='"
						+ answerCode
						+ "', ONLINE_CORRECT='"
						+ scoreDtl[0][1]
						+ "', "
						+ " ONLINE_SCORE="
						+ scoreDtl[0][0]
						+ ", "
						+ " ONLINE_SUBJ_ANSWER = '"
						+ subjAnsForPrevious
						+ "', "
						+ " WBT_ONLINETEST.ANSWER_UPLOADED_DOC = '"
						+ answerUploadedDoc
						+ "'"
						+ " WHERE ONLINE_USER_CODE  = "
						+ userCode
						+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
						+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')" 
						+ " AND ONLINE_ATTEMPT=" + bean.getAttempt();

				if (!String.valueOf(applicationCode).equals("0")
						&& !String.valueOf(applicationCode).equals("")) {
					updatePreviousQues += "AND ONLINE_APPLICATION_CODE = "
							+ applicationCode;
				}
				updatePreviousQues += " AND ONLINE_SEQS = " + (sequence - 1)
						+ " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID = "
						+ programCode + " " + " AND ONLINE_MODULE_CODE ="
						+ moduleCode;
				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					updatePreviousQues += " AND  ONLINE_SECTION_CODE="
							+ sectionCode;
				}

				updatePreviousQues += " AND ONLINE_ATTEMPT="
						+ bean.getAttempt();

				System.out
						.println("after update Query----------------------------------"
								+ updatePreviousQues);

				boolean update = getSqlModel()
						.singleExecute(updatePreviousQues);

				System.out
						.println("after update update--------update-----------------------"
								+ update);

				String query1 = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), "
						+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(WBT_ONLINETEST.ANSWER_UPLOADED_DOC,''), "
						+ " HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
						+ " FROM WBT_ONLINETEST "
						+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = WBT_ONLINETEST.ONLINE_QUES_CODE) "
						+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
						+ " LEFT JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
						+ " WHERE ONLINE_SEQS = "
						+ (sequence)
						+ " AND ONLINE_USER_CODE = " + userCode
				+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
				+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";
				if (!String.valueOf(applicationCode).equals("0")
						&& !String.valueOf(applicationCode).equals("")) {
					query1 += "AND ONLINE_APPLICATION_CODE = "
							+ applicationCode;
				}
				query1 += " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID = "
						+ programCode;

				query1 += "AND ONLINE_ATTEMPT=" + bean.getAttempt();

				sequenceData = getSqlModel().getSingleResult(query1);

				if (sequenceData != null && sequenceData.length > 0) {
					bean.setQuestionLevel(String.valueOf(sequenceData[0][4]));// QUESTION
					// COMPLEXITY
					// LEVEL..
					bean.setSubjectAns(checkNull(String
							.valueOf(sequenceData[0][5])));
					bean.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
					if (String.valueOf(sequenceData[0][2]).equals("S")) {
						if (!checkNull(String.valueOf(sequenceData[0][7]))
								.equals("")) {
							bean.setDocumentNotAttachedFlag(true);
							bean.setQuestionUploadedDoc(checkNull(String
									.valueOf(sequenceData[0][7])));
						}
						bean.setAnswerUploadedDoc(checkNull(String
								.valueOf(sequenceData[0][8])));
					} else {
						bean.setDocumentNotAttachedFlag(false);
					}
					/*
					 * onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
					 * onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
					 */

				}
				/**
				 * HERE THE ARRAY LIST IS SET AGAIN WHEN THE NEXT BUTTON IS
				 * CLICKED FOR THE RECORD WHICH HAS APPEARED BEFORE....
				 */
				if (randomQuesCodes != null && randomQuesCodes.length > 0) {
					setRandomCodeList(bean, request);
				}
			}

			/**
			 * this else is called when Next button is pressed for the fresh new
			 * Question..
			 */
			else {
				subjAnsForPrevious = bean.getSubjectAns();
				bean.setSubjectAns("");
				bean.setAnswerUploadedDoc("");

				/**
				 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME
				 * SCORE FOR EVERY RIGHT ANSWER HERE SCORE IS CALCULATED IN THE
				 * getScoreDtlEqWtage METHOD... HERE scoreDtl[0][0]==SCORE AND
				 * scoreDtl[0][1]==ANSWER CORRECT YES OR NO
				 */
				if (bean.getEqualweightage().equals("Y")) {
					/**
					 * THIS IS CALLED WHEN EVERY QUESTION HAS EQUAL WEIGHTAGE
					 */
					scoreDtl = getScoreDtlEqWtage(bean, answerCode,
							answerCorrect, sequence);
				} else {
					/**
					 * THIS IS CALLED WHEN THE QUESTION NAMRKS DEPENDS UPON THE
					 * COMPLEXITY LEVEL..
					 */
					scoreDtl = getScoreForCorrect(bean, answerCode,
							answerCorrect, sequence);
				}

				/**
				 * THIS IS TO CHECK WHETHER ANSWER IS GIVEN OR NOT...IF YES
				 * WHETHER THE ANSWER IS COORECT OR NOT. IF THE ANSWER IS WRONG
				 * THEN "MARKS FOR NO ANSWER" IS SET..IF "answerCorrect" IS
				 * BLANK THEN "MARKS FOR NO ANSWER" IS SET IN "SCORE"
				 */
				// "+scoreDtl[0][0]+"
				if (sequence > 1) {

					String updatePreviousQues = "UPDATE WBT_ONLINETEST SET ONLINE_ANSWER='"
							+ answerCode
							+ "',ONLINE_CORRECT='"
							+ scoreDtl[0][1]
							+ "', "
							+ " ONLINE_SCORE="
							+ scoreDtl[0][0]
							+ ",ONLINE_SUBJ_ANSWER = '"
							+ subjAnsForPrevious
							+ "', "
							+ " WBT_ONLINETEST.ANSWER_UPLOADED_DOC = '"
							+ answerUploadedDoc
							+ "' "
							+ " WHERE ONLINE_USER_CODE = " + userCode
							+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";
					if (!String.valueOf(applicationCode).equals("0")
							&& !String.valueOf(applicationCode).equals("")) {
						updatePreviousQues += "AND ONLINE_APPLICATION_CODE = "
								+ applicationCode;
					}
					updatePreviousQues += " AND ONLINE_SEQS = "
							+ (sequence - 1)
							+ " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID = "
							+ programCode
							+ " AND WBT_ONLINETEST.ONLINE_USER_TYPE = '"
							+ (String)session.getAttribute("userType") + "' " + " AND ONLINE_MODULE_CODE="
							+ moduleCode;

					if (sectionCode != null && !sectionCode.equals("null")
							&& !sectionCode.equals("")) {
						updatePreviousQues += " AND ONLINE_SECTION_CODE="
								+ sectionCode;
					} else {
						updatePreviousQues += " AND ONLINE_SECTION_CODE is null ";
					}

					updatePreviousQues += "AND ONLINE_ATTEMPT="
							+ bean.getAttempt();

					boolean update = getSqlModel().singleExecute(
							updatePreviousQues);
				}

				getFreshNext(bean, request, sequence, programCode, moduleCode,
						sectionCode, userCode, applicationCode, userType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	private Object[][] getNegativeMarksSubmit(OnlineProgram onlineTest,
			String answerCode, String answerCorrect, int sequence) {
		int score = 0;
		Object[][] scoreDtl = new Object[1][2];
		if (sequence > 1) { // if 2
			if (answerCode == null || answerCode.equals("")) {
				score = 0;
				answerCorrect = "";
			} else {
				if (!(answerCorrect == null || answerCorrect.equals(""))) { // if 1
					if (answerCorrect == "Y" || answerCorrect.equals("Y")) {
						/* Begin Gauri */
						// score =
						// Integer.parseInt(onlineTest.getMarksWrongAns());
						if (onlineTest.getQuestionLevel().equals("E")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksEasy());
						} else if (onlineTest.getQuestionLevel().equals("M")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksMedium());
						} else if (onlineTest.getQuestionLevel().equals("H")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksHard());
						}
					}
					/**
					 * THIS ELSE IF FOR IF ANSWER IS GIVEN...THEN HERE IT WILL
					 * CHECK THE COMPLEXITY LEVEL IF THE LEVEL IS "HARD" THEN
					 * FOR THE RESPECTIVE QUESTION HARD MARKS WILL BE GIVEN SAME
					 * CASES IN MEDIUM AND EASY TYPE LEVELS....
					 */
					/*
					 * else{ if(onlineTest.getQuestionLevel().equals("E")){
					 * score = Integer.parseInt(onlineTest.getMarksEasy()); }
					 * else if(onlineTest.getQuestionLevel().equals("M")){ score =
					 * Integer.parseInt(onlineTest.getMarksMedium()); } else
					 * if(onlineTest.getQuestionLevel().equals("H")){ score =
					 * Integer.parseInt(onlineTest.getMarksHard()); } }
					 */
				}
			}
		}

		scoreDtl[0][0] = score;
		scoreDtl[0][1] = answerCorrect;

		return scoreDtl;
	}

	private Object[][] getScoreForCorrect(OnlineProgram onlineTest,
			String answerCode, String answerCorrect, int sequence) {
		int score = 0;
		Object[][] scoreDtl = new Object[1][2];
		if (sequence > 1) { // if 2
			if (answerCode == null || answerCode.equals("")) {
				score = 0;
				answerCorrect = "";
			} else {
				if (!(answerCorrect == null || answerCorrect.equals(""))) { // if 1
					if (answerCorrect == "Y" || answerCorrect.equals("Y")) {
						/* Begin Gauri */
						// score =
						// Integer.parseInt(onlineTest.getMarksWrongAns());
						if (onlineTest.getQuestionLevel().equals("E")) {
							score = Integer.parseInt(onlineTest.getMarksEasy());
						} else if (onlineTest.getQuestionLevel().equals("M")) {
							score = Integer.parseInt(onlineTest
									.getMarksMedium());
						} else if (onlineTest.getQuestionLevel().equals("H")) {
							score = Integer.parseInt(onlineTest.getMarksHard());
						}
					}
					/**
					 * THIS ELSE IF FOR IF ANSWER IS GIVEN...THEN HERE IT WILL
					 * CHECK THE COMPLEXITY LEVEL IF THE LEVEL IS "HARD" THEN
					 * FOR THE RESPECTIVE QUESTION HARD MARKS WILL BE GIVEN SAME
					 * CASES IN MEDIUM AND EASY TYPE LEVELS....
					 */
					/*
					 * else{ if(onlineTest.getQuestionLevel().equals("E")){
					 * score = Integer.parseInt(onlineTest.getMarksEasy()); }
					 * else if(onlineTest.getQuestionLevel().equals("M")){ score =
					 * Integer.parseInt(onlineTest.getMarksMedium()); } else
					 * if(onlineTest.getQuestionLevel().equals("H")){ score =
					 * Integer.parseInt(onlineTest.getMarksHard()); } }
					 */
				}
			}
		}

		scoreDtl[0][0] = score;
		scoreDtl[0][1] = answerCorrect;

		return scoreDtl;
	}

	private void setRandomCodeList(OnlineProgram bean,
			HttpServletRequest request) {
		try {
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			String randomQuesCodes[] = request
					.getParameterValues("randomQuesCodes");
			for (int i = 0; i < randomQuesCodes.length; i++) {
				OnlineProgram innerbean = new OnlineProgram();
				innerbean.setRandomQuesCodes(randomQuesCodes[i]);
				randomCodesList.add(innerbean);
			}
			bean.setRandomQueList(randomCodesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object[][] getQuesFromSequence(int sequence, int userCode,
			int applicationCode, String programCode, String userType,
			String moduleCode, String sequenceCode, OnlineProgram bean) {
		Object[][] sequenceData = null;
		try {
			String query = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), "
					+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(WBT_ONLINETEST.ANSWER_UPLOADED_DOC,''), "
					+ " HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME,CAT_CODE "
					+ " FROM WBT_ONLINETEST "
					+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = WBT_ONLINETEST.ONLINE_QUES_CODE) "
					+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
					+ " LEFT JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
					+ " WHERE WBT_ONLINETEST.ONLINE_SEQS = "
					+ sequence
					+ " AND WBT_ONLINETEST.ONLINE_USER_CODE = " + (String)session.getAttribute("userCode")
			+" AND WBT_ONLINETEST.ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
			+"'  AND WBT_ONLINETEST.ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";

			if (!String.valueOf(applicationCode).equals("0")
					&& !String.valueOf(applicationCode).equals("")) {
				query += "AND ONLINE_APPLICATION_CODE = " + applicationCode;
			}
			query += " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID  =" + programCode
					+ " " + " AND WBT_ONLINETEST.ONLINE_USER_TYPE='" + (String)session.getAttribute("userType")
					+ "' " + " AND ONLINE_MODULE_CODE=" + moduleCode + " ";

			if (sequenceCode != null && !sequenceCode.equals("null")
					&& !sequenceCode.equals("")) {
				query += " AND ONLINE_SECTION_CODE=" + sequenceCode;
			} else {
				query += " AND ONLINE_SECTION_CODE is null ";
			}

			query += "AND ONLINE_ATTEMPT=" + bean.getAttempt();

			sequenceData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequenceData;
	} // END OF METHOD

	private void getFreshNext(OnlineProgram bean, HttpServletRequest request,
			int sequence, String programCode, String moduleCode,
			String sectionCode, int userCode, int applicationCode,
			String userType) {
		// TODO Auto-generated method stub
		String questionCode = "";
		Object[][] question = null;

		String randomQuesCodes[] = request
				.getParameterValues("randomQuesCodes");
		if (randomQuesCodes != null && randomQuesCodes.length > 0) {
			questionCode = randomQuesCodes[0];
			question = getQuestion(moduleCode, randomQuesCodes, sectionCode);

			if (randomQuesCodes.length > 1) {// this if is check if the
				// questions present is more
				// than 1
				getQuesOptionForFreshNext(bean, question, randomQuesCodes,
						sequence, questionCode, request, programCode,
						moduleCode, sectionCode, userCode, applicationCode,
						userType);

			} else {
				bean.setSubmitButtonFlag("true");// end of test
				bean.setNextButtonFlag("false"); // no more questions for the
				// test
				bean.setLastQuestionSequence(String.valueOf(sequence));// this
				// is
				// used
				// in
				// startTest
				// method
				getlastQuestion(bean, question, randomQuesCodes, sequence,
						questionCode, request, programCode, userCode,
						applicationCode, userType, moduleCode, sectionCode);
			}
		}

	}

	private void getQuesOptionForFreshNext(OnlineProgram bean,
			Object[][] question, String[] randomQuesCodes, int sequence,
			String questionCode, HttpServletRequest request,
			String programCode, String moduleCode, String sectionCode,
			int userCode, int applicationCode, String userType) {
		try {
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();

			if (question != null && question.length > 0) { // if 1
				bean.setQuestionName(String.valueOf(question[0][0]));// question
				// name
				bean.setQuestionLevel(String.valueOf(question[0][4]));// question
				// level...
				bean.setTestModuleCode(checkNull(String
						.valueOf(question[0][10])));
				bean
						.setTestModuleName(checkNull(String
								.valueOf(question[0][7])));
				bean.setTestProgramCode(checkNull(programCode));

				setProgramName(programCode, bean);

				bean.setTestSectionName(checkNull(String
						.valueOf(question[0][8])));
				bean.setTestSectionCode(checkNull(String
						.valueOf(question[0][11])));

				Object[][] questionOption = null;

				System.out
						.println("String.valueOf(question[0][1])----------------"
								+ String.valueOf(question[0][1]));

				if (String.valueOf(question[0][1]).equals("O")) {
					bean.setOptionFlag("true");

					bean.setDocumentNotAttachedFlag(false);

					questionOption = getquestionOption(questionCode);
					String[] checkbox = new String[questionOption.length];
					if (questionOption != null && questionOption.length > 0) {
						for (int i = 0; i < questionOption.length; i++) {
							checkbox[i] = "false";
							OnlineProgram innerbean = new OnlineProgram();
							innerbean.setOptionCode(String
									.valueOf(questionOption[i][0]));
							innerbean.setOptionName(String
									.valueOf(questionOption[i][1]));
							innerbean.setOptionAnswer(String
									.valueOf(questionOption[i][2]));
							optionTable.add(innerbean);
						} // end of loop
						bean.setOptionList(optionTable);
						request.setAttribute("check", checkbox);
					}
				} else {
					bean.setOptionFlag("false");

					System.out
							.println("String.valueOf(question[0][5])------------------------------"
									+ String.valueOf(question[0][5]));

					bean.setSubjAnswerLimit(String.valueOf(question[0][5]));

					System.out
							.println("String.valueOf(question[0][6])------------------------------"
									+ String.valueOf(question[0][6]));
					if (!checkNull(String.valueOf(question[0][6])).equals("")) {
						bean.setDocumentNotAttachedFlag(true);
						bean.setQuestionUploadedDoc(checkNull(String
								.valueOf(question[0][6])));
					}
				}

				/**
				 * this insert query is fired on continue and next button...but
				 * is in case previous button is clicked and after that Next is
				 * clicked than this query is not fired....
				 */

				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					String insOnline = "INSERT INTO WBT_ONLINETEST (ONLINE_USER_CODE ,ONLINE_APPLICATION_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE ,ONLINE_MODULE_CODE, ONLINE_SECTION_CODE,ONLINE_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME) "
							+ " VALUES("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ randomQuesCodes[0]
							+ ","
							+ sequence
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ "," + sectionCode + "," + bean.getAttempt() + ",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"+")";

					boolean result = getSqlModel().singleExecute(insOnline);
				} else {
					String insOnline = "INSERT INTO WBT_ONLINETEST (ONLINE_USER_CODE ,ONLINE_APPLICATION_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE ,ONLINE_MODULE_CODE,ONLINE_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME) "
							+ " VALUES("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ randomQuesCodes[0]
							+ ","
							+ sequence
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ "," + bean.getAttempt() + ",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"+")";

					boolean result = getSqlModel().singleExecute(insOnline);

				}

				/**
				 * Here in this for loop i=1 bcoz to remove the 0th index value
				 * frm the array list...
				 */
				for (int i = 1; i < randomQuesCodes.length; i++) {
					OnlineProgram innerbean = new OnlineProgram();
					innerbean.setRandomQuesCodes(randomQuesCodes[i]);
					randomCodesList.add(innerbean);
				}
				bean.setRandomQueList(randomCodesList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getlastQuestion(OnlineProgram bean, Object[][] question,
			String[] randomQuesCodes, int sequence, String questionCode,
			HttpServletRequest request, String programCode, int userCode,
			int applicationCode, String userType, String moduleCode,
			String sectionCode) {
		try {
			String answerCode = request.getParameter("optionCode");// this is
			// answers
			// given by
			// the
			// candidate
			String answerCorrect = request.getParameter("finalAnswer");// this
			// is
			// answer
			// whether
			// correct
			// or
			// not
			int score = 0;
			Object[][] scoreDtl = new Object[1][2];
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			if (question != null && question.length > 0) { // if 1
				bean.setQuestionName(String.valueOf(question[0][0]));// question
				// name
				bean.setQuestionLevel(String.valueOf(question[0][4]));// question
				// complexity
				// level
				bean.setTestModuleCode(checkNull(String
						.valueOf(question[0][10])));
				bean
						.setTestModuleName(checkNull(String
								.valueOf(question[0][7])));
				bean.setTestProgramCode(checkNull(programCode));

				setProgramName(programCode, bean);

				bean.setTestSectionName(checkNull(String
						.valueOf(question[0][8])));
				bean.setTestSectionCode(checkNull(String
						.valueOf(question[0][11])));

				Object[][] questionOption = null;
				if (String.valueOf(question[0][1]).equals("O")) {
					/**
					 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
					 */
					bean.setOptionFlag("true");

					bean.setDocumentNotAttachedFlag(false);

					questionOption = getquestionOption(questionCode);
					if (questionOption != null && questionOption.length > 0) {
						String[] checkbox = new String[questionOption.length];
						for (int i = 0; i < questionOption.length; i++) {
							checkbox[i] = "false";
							OnlineProgram innerbean = new OnlineProgram();
							innerbean.setOptionCode(String
									.valueOf(questionOption[i][0]));
							innerbean.setOptionName(String
									.valueOf(questionOption[i][1]));
							innerbean.setOptionAnswer(String
									.valueOf(questionOption[i][2]));
							optionTable.add(innerbean);
						} // end of loop
						bean.setOptionList(optionTable);
						request.setAttribute("check", checkbox);
					} // end of if
				} // end of objective if
				else {
					bean.setOptionFlag("false");

					bean.setSubjAnswerLimit(String.valueOf(question[0][5]));

					System.out
							.println("String.valueOf(question[0][6])------------------------------"
									+ String.valueOf(question[0][6]));
					if (!checkNull(String.valueOf(question[0][6])).equals("")) {
						bean.setDocumentNotAttachedFlag(true);
						bean.setQuestionUploadedDoc(checkNull(String
								.valueOf(question[0][6])));
					}

				} // end of else

				/**
				 * this insert query is fired on continue and next button...but
				 * is in case previous button is clicked and after that Next is
				 * clicked than this query is not fired....
				 */

				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					String insOnline = "INSERT INTO WBT_ONLINETEST (ONLINE_USER_CODE ,ONLINE_APPLICATION_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE ,ONLINE_MODULE_CODE, ONLINE_SECTION_CODE ,ONLINE_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME) "
							+ " VALUES("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ randomQuesCodes[0]
							+ ","
							+ sequence
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ "," + sectionCode + "," + bean.getAttempt() +  ",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"+")";
					boolean result = getSqlModel().singleExecute(insOnline);
				} else {
					String insOnline = "INSERT INTO WBT_ONLINETEST (ONLINE_USER_CODE ,ONLINE_APPLICATION_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE ,ONLINE_MODULE_CODE ,ONLINE_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME ) "
							+ " VALUES("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ randomQuesCodes[0]
							+ ","
							+ sequence
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ "," + bean.getAttempt() + ",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"+ ")";
					boolean result = getSqlModel().singleExecute(insOnline);
				}

			}
			bean.setOptionList(optionTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method getlastQuestion

	public Object[][] getQuestion(String moduleCode, String[] randomQuesCodes,
			String sectionCode) {
		Object[][] questionObj = null;
		String questionQuery = " SELECT REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,QUES_MARK,QUES_LIMIT,QUES_LEVEL,NVL(QUES_LIMIT,0),NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
				+ " , QUES_CODE,SUBJECT_CODE ,cat_code FROM HRMS_REC_QUESBANK  "
				+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE) "
				+ " LEFT JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE)	 "
				+ " where QUES_SUB_CODE=" + moduleCode + " ";

		if (sectionCode != null && !sectionCode.equals("null")
				&& !sectionCode.equals("")) {
			questionQuery += " AND QUES_CAT_CODE =" + sectionCode;
		}

		questionQuery += " and QUES_STATUS='A'  ";

		questionQuery += " and QUES_CODE=" + randomQuesCodes[0] + " ";

		questionObj = getSqlModel().getSingleResult(questionQuery);

		return questionObj;
	}

	private Object[][] getquestionOption(String queCode) {
		Object[][] questionOption = null;
		try {
			String sqlQuery = "SELECT OPTION_CODE,OPTION_DESC,OPTION_ANS_FLAG "
					+ " FROM HRMS_REC_QUESOPTION WHERE QUES_CODE = " + queCode
					+ "  ";
			questionOption = getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionOption;
	}

	public Object[][] getTestTemplateAddQues(OnlineProgram onlineTest,
			String programCode, String moduleCode, String sectionCode,
			String isSubmit) {
		Object[][] quesCodes = null;
		Object[][] codes = null;
		try {

			onlineTest.setNoQuesAvailable("1");

			String query = " SELECT PROGRAM_QUES_ID, PROGRAM_ID, MODULE_ID, SECTION_ID, IS_QUES_RANDOM, PROGRAM_NO_QUES, MARK_EACH_QUES, PROGRAM_TOTAL_MARKS, PROGRAM_PASS_MARKS, IS_EQUAL_MARK_QUES, MARKS_EASY_QUES, MARKS_MEDIUM_QUES, MARKS_HARD_QUES, IS_NEGATIVE_MARK_QUES, NEGATIVE_MARK_EASY_QUES, NEGATIVE_MEDIUM_QUES, NEGATIVE_MARK_HARD_QUES "
					+ " FROM WBT_PROGRAM_QUES_HDR "
					+ " WHERE PROGRAM_ID="
					+ programCode + " AND MODULE_ID=" + moduleCode;

			if (isSubmit.equals("Y")) {
				query += " AND SECTION_ID is null ";
			} else {
				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					query += " AND SECTION_ID=" + sectionCode;
				} else {
					query += " AND SECTION_ID is null ";
				}
			}

			Object hdrQueryObj[][] = getSqlModel().getSingleResult(query);

			if (hdrQueryObj != null && hdrQueryObj.length > 0) {
				/*
				 * String dtlQuery = " select QUES_CODE, QUES_TYPE, QUES_LEVEL,
				 * QUES_MARKS from WBT_PROGRAM_QUES_DTL " + " where
				 * PROGRAM_QUES_ID=" + String.valueOf(hdrQueryObj[0][0]) + "
				 * order by QUES_ORDER ";
				 */
				String dtlQuery = " SELECT WBT_PROGRAM_QUES_DTL.QUES_CODE, WBT_PROGRAM_QUES_DTL.QUES_TYPE, WBT_PROGRAM_QUES_DTL .QUES_LEVEL, WBT_PROGRAM_QUES_DTL .QUES_MARKS "
						+ "	,NVL(WBT_PROGRAM_QUES_DTL.QUES_ORDER, 0) FROM WBT_PROGRAM_QUES_DTL "
						+ "	INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
						+ " WHERE PROGRAM_QUES_ID="
						+ String.valueOf(hdrQueryObj[0][0])
						+ " ORDER BY WBT_PROGRAM_QUES_DTL.QUES_ORDER  desc";
				quesCodes = getSqlModel().getSingleResult(dtlQuery);
				/*
				 * if (quesCodes != null && quesCodes.length > 0) { codes = new
				 * Object[quesCodes.length][1]; for (int i = 0; i <
				 * codes.length; i++) { codes[i][0] = quesCodes[i][0]; } }
				 */
				if (quesCodes != null && quesCodes.length == 0) {
					onlineTest.setNoQuesAvailable("0");
				}

			} else {
				onlineTest.setNoQuesAvailable("0");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return quesCodes;
	}

	/**
	 * this method is called to retrieve the QUESTION OPTION. HERE DATA IS
	 * RETRIEVED BY PASSING THE QUESTION CODE FROM THE WBT_ONLINETEST TABLE...
	 * 
	 * @param sequenceData
	 * @param onlineTest
	 * @param request
	 */
	private void getQuesOption(Object[][] sequenceData, OnlineProgram bean,
			HttpServletRequest request) {
		try {
			Object[][] questionOption = null;
			ArrayList<Object> optionTable = new ArrayList<Object>();
			/**
			 * THIS IF CODITION IS CHECKED WHETHER THE QUESTION TYPE IS
			 * OBJECTIVE...
			 */
			if (String.valueOf(sequenceData[0][2]).equals("O")) {
				/**
				 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
				 */
				bean.setOptionFlag("true");

				bean.setDocumentNotAttachedFlag(false);

				questionOption = getquestionOption(String
						.valueOf(sequenceData[0][0]));
				String[] checkbox = new String[questionOption.length];
				if (questionOption != null && questionOption.length > 0) {

					for (int i = 0; i < questionOption.length; i++) {
						OnlineProgram innerbean = new OnlineProgram();
						String[] answerCode = String
								.valueOf(sequenceData[0][3]).split(",");
						checkbox[i] = "false";
						for (int j = 0; j < answerCode.length; j++) {
							if (String.valueOf(questionOption[i][0]).equals(
									answerCode[j])) {
								checkbox[i] = "true";
							}
						}
						innerbean.setOptionCode(String
								.valueOf(questionOption[i][0]));
						innerbean.setOptionName(String
								.valueOf(questionOption[i][1]));
						innerbean.setOptionAnswer(String
								.valueOf(questionOption[i][2]));
						optionTable.add(innerbean);
					}
					bean.setOptionList(optionTable);
					request.setAttribute("check", checkbox);
				}
			} else {
				bean
						.setSubjectAns(checkNull(String
								.valueOf(sequenceData[0][5])));
				bean.setSubjAnswerLimit(checkNull(String
						.valueOf(sequenceData[0][6])));
				if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
					bean.setDocumentNotAttachedFlag(true);
					bean.setQuestionUploadedDoc(checkNull(String
							.valueOf(sequenceData[0][7])));
				}
				bean.setAnswerUploadedDoc(checkNull(String
						.valueOf(sequenceData[0][8])));
				/*
				 * bean.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
				 * bean.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
				 */
				bean.setOptionFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPrevious(OnlineProgram bean, HttpServletRequest request,
			String moduleCode, String programCode, String sectionCode) {

		try {
			System.out
					.println("in previous model method logic--------------------");
			ArrayList<Object> optionTable = new ArrayList<Object>();
			/**
			 * HERE SEQUENCE IS MINUS TO GET THE PREVIOUS TEST QUESTION TO
			 * UPDATE OR TO PREVIEW...
			 */
			int sequence = Integer.parseInt(bean.getSequenceCode()) - 1;
			bean.setSequenceCode(String.valueOf(sequence));
			Object[][] sequenceData = null;
			// String programCode = "1";//bean.getTemplateCode();
			int userCode = Integer.parseInt(bean.getUserCode());// Integer.parseInt(bean.getCandidateUserEmpId());
			// // IT IS HARDCODED NOW....
			// int applicationCode =
			// Integer.parseInt(bean.getApplicationCode());//
			// ;Integer.parseInt(bean.getReqCode()); //
			int applicationCode = Integer.parseInt(bean.getApplicationCode()
					.equals("") ? "0" : bean.getApplicationCode());

			// IT IS ALSO HARDOCED NOW....

			String userType = bean.getUserType();

			sequenceData = getQuesFromSequence(sequence, userCode,
					applicationCode, programCode, userType, moduleCode,
					sectionCode, bean);

			System.out.println("sequenceData.length  Previous----------"
					+ sequenceData.length);

			if (sequenceData != null && sequenceData.length > 0) {
				bean.setQuestionName(String.valueOf(sequenceData[0][1]));// QUESTION
				// NAME
				bean.setQuestionLevel(String.valueOf(sequenceData[0][4]));// QUESTION
				// COMPLEXITY
				// LEVEL
				/*
				 * bean.setSubject(checkNull(String
				 * .valueOf(sequenceData[0][9])));
				 * bean.setCategory(checkNull(String
				 * .valueOf(sequenceData[0][10])));
				 */

				bean.setTestModuleCode(moduleCode);
				bean.setTestModuleName(checkNull(String
						.valueOf(sequenceData[0][9])));

				bean.setTestProgramCode(checkNull(programCode));

				setProgramName(programCode, bean);

				bean.setSubjAnswerLimit(checkNull(String
						.valueOf(sequenceData[0][6])));

				bean.setTestSectionName(checkNull(String
						.valueOf(sequenceData[0][10])));
				bean.setTestSectionCode(checkNull(String
						.valueOf(sequenceData[0][11])));

				if (String.valueOf(sequenceData[0][2]).equals("S")) {
					if (!checkNull(String.valueOf(sequenceData[0][7])).equals(
							"")) {
						bean.setDocumentNotAttachedFlag(true);
						bean.setQuestionUploadedDoc(checkNull(String
								.valueOf(sequenceData[0][7])));
					}
					bean.setAnswerUploadedDoc(checkNull(String
							.valueOf(sequenceData[0][8])));
				} else {
					bean.setDocumentNotAttachedFlag(false);
				}
				getQuesOption(sequenceData, bean, request);
			}
			String randomQuesCodes[] = request
					.getParameterValues("randomQuesCodes");
			/**
			 * HERE THE ARRAY LIST IS SET AGAIN WHEN THE PREVIOUS BUTTON IS
			 * CLICKED....
			 */
			if (randomQuesCodes != null && randomQuesCodes.length > 0) {
				setRandomCodeList(bean, request);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void submit(OnlineProgram bean, HttpServletRequest request,
			String moduleCode, String programCode, String applicationCode,
			String userCode, String userType, String sectionCode) {

		try {
			System.out
					.println("sectionCode in sumbmit model--------------------------"
							+ sectionCode);

			String result = "";
			int score = 0;
			Object[][] scoreDtl = new Object[1][2];
			String answerCode = request.getParameter("optionCode");// this is
			// answers
			// given by
			// the
			// candidate
			String answerCorrect = request.getParameter("finalAnswer");// this
			// is
			// answer
			// whether
			// correct
			// or
			// not
			int sequence = Integer.parseInt(bean.getSequenceCode());
			String updatePreviousQues = "";

			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE
			 * FOR EVERY RIGHT ANSWER
			 */
			if (bean.getEqualweightage().equals("Y")) {
				/**
				 * THIS IS FOR QUESTIONS HAVE EQUAL WEIGHTAGE....
				 */
				scoreDtl = getScoreEqWtSubmit(bean, answerCode, answerCorrect);
			} else {
				/**
				 * THIS IS FOR QUESTION MARKS BASED UPON THEIR COMPLEXITY
				 * LEVEL..
				 */
				scoreDtl = getScoreForCorrectSubmit(bean, answerCode,
						answerCorrect, sequence);
			}

			if (sequence == 1) {
				updatePreviousQues = "UPDATE WBT_ONLINETEST SET ONLINE_ANSWER='"
						+ answerCode
						+ "', "
						+ " ONLINE_CORRECT='"
						+ scoreDtl[0][1]
						+ "',ONLINE_SCORE="
						+ scoreDtl[0][0]
						+ ", "
						+ " ONLINE_SUBJ_ANSWER = '"
						+ bean.getSubjectAns()
						+ "', "
						+ " ANSWER_UPLOADED_DOC = '"
						+ bean.getAnswerUploadedDoc()
						+ "' "
						+ " WHERE ONLINE_USER_CODE = " + (String)session.getAttribute("userCode")
						+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
						+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";
				if (!String.valueOf(applicationCode).equals("0")
						&& !String.valueOf(applicationCode).equals("")) {
					updatePreviousQues += "AND ONLINE_APPLICATION_CODE = "
							+ applicationCode;
				}
				updatePreviousQues += " AND ONLINE_SEQS = " + (sequence)
						+ " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID= "
						+ programCode
						+ " AND WBT_ONLINETEST.ONLINE_USER_TYPE = '" + (String)session.getAttribute("userType")
						+ "'" + " AND ONLINE_MODULE_CODE=" + moduleCode;

				updatePreviousQues += "AND ONLINE_ATTEMPT=" + bean.getAttempt();

				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					updatePreviousQues += " AND  ONLINE_SECTION_CODE="
							+ sectionCode;
				}

			} else {
				String sequenceCodeForUpdate = "";
				if (bean.getLastQuestionSequence().equals("")
						|| bean.getLastQuestionSequence().equals("null")) {
					sequenceCodeForUpdate = bean.getSequenceCode();
				} else {
					sequenceCodeForUpdate = bean.getLastQuestionSequence();
				}
				updatePreviousQues = "UPDATE WBT_ONLINETEST SET ONLINE_ANSWER='"
						+ answerCode
						+ "', "
						+ " ONLINE_CORRECT='"
						+ scoreDtl[0][1]
						+ "',ONLINE_SCORE="
						+ scoreDtl[0][0]
						+ ", "
						+ " ONLINE_SUBJ_ANSWER = '"
						+ bean.getSubjectAns()
						+ "', "
						+ " ANSWER_UPLOADED_DOC = '"
						+ bean.getAnswerUploadedDoc()
						+ "' "
						+ " WHERE ONLINE_USER_CODE = " + (String)session.getAttribute("userCode")
						+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
						+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";

				if (!String.valueOf(applicationCode).equals("0")
						&& !String.valueOf(applicationCode).equals("")) {
					updatePreviousQues += "AND ONLINE_APPLICATION_CODE = "
							+ applicationCode;
				}
				updatePreviousQues += " AND ONLINE_SEQS = "
						+ sequenceCodeForUpdate
						+ " AND WBT_ONLINETEST.ONLINE_PROGRAM_ID = "
						+ programCode
						+ " AND WBT_ONLINETEST.ONLINE_USER_TYPE = '" + (String)session.getAttribute("userType")
						+ "'" + " AND ONLINE_MODULE_CODE=" + moduleCode;

				if (sectionCode != null && !sectionCode.equals("null")
						&& !sectionCode.equals("")) {
					updatePreviousQues += " AND  ONLINE_SECTION_CODE="
							+ sectionCode;
				}

				updatePreviousQues += " AND ONLINE_ATTEMPT="
						+ bean.getAttempt();
			}
			boolean update = getSqlModel().singleExecute(updatePreviousQues);

			// updateTestResult(userCode, applicationCode, programCode,
			// userType, moduleCode, sectionCode, "Y");

			// getTestResult(bean, request, applicationCode, moduleCode,
			// sectionCode, programCode, userCode, userType, "Y");
			// checkModuleTestCompleted(userCode, applicationCode, programCode,
			// userType, moduleCode, sectionCode,bean);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String checkTestCompleted(String userCode, String applicationCode,
			String programCode, String userType, String moduleCode,
			String sectionCode, OnlineProgram bean) {
		String moduleCompleted = "N";
		try {
			String query = "  SELECT case when TEST_RESULT='P' then 'Completed' else 'Incompleted' end as status FROM WBT_ONLINETEST_RESULT "
					+ " WHERE ONLINE_USER_CODE =" + (String)session.getAttribute("userCode")
			+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
			+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";

			if (!String.valueOf(applicationCode).equals("0")
					&& !String.valueOf(applicationCode).equals("")) {
				query += "AND ONLINE_APPLICATION_CODE = " + applicationCode;
			}
			query += " AND ONLINE_USER_TYPE='" + (String)session.getAttribute("userType") + "'"
					+ "  AND ONLINE_MODULE_CODE=" + moduleCode;

			query += " AND ONLINE_PROGRAM_ID=" + programCode;

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " and ONLINE_SECTION_CODE is null ";
			}
			query += " And TEST_RESULT='P'";

			Object[][] statusObj = getSqlModel().getSingleResult(query);
			if (statusObj != null && statusObj.length > 0) {
				moduleCompleted = "Y";

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return moduleCompleted;

	}

	/*public void updateTestResult(String userCode, String applicationCode,
			String programCode, String userType, String moduleCode,
			String sectionCode, String testStatus, String attempt) {

		if (sectionCode != null && !sectionCode.equals("null")
				&& !sectionCode.equals("")) {
			String query = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE, ONLINE_SECTION_CODE, ONLINE_TEST_STATUS,WBT_ONLINETEST_RESULT)"
					+ "VALUES ("
					+ userCode
					+ ","
					+ applicationCode
					+ ","
					+ programCode
					+ ",'"
					+ userType
					+ "',"
					+ moduleCode
					+ ","
					+ sectionCode + ",'" + testStatus + "'," + attempt + ")";

			getSqlModel().singleExecute(query);
		} else {

			String query = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE,ONLINE_TEST_STATUS,ONLINE_TEST_ATTEMPT)"
					+ "VALUES ("
					+ userCode
					+ ","
					+ applicationCode
					+ ","
					+ programCode
					+ ",'"
					+ userType
					+ "',"
					+ moduleCode
					+ ",'"
					+ testStatus + "'," + attempt + ")";

			getSqlModel().singleExecute(query);
		}

	}*/

	public String getTestTemplateDetails(HttpServletRequest request,
			OnlineProgram bean, String moduleCode, String programCode,
			String sectionCode) {
		String result = "";
		try {

			Object[][] templateHdr = null;
			ArrayList<Object> questionList = new ArrayList<Object>();
			/**
			 * THIS QUERY IS TO RETRIEVE DATA FROM THE TEMPLATE HDT TABLE....
			 */

			String queryHdr = " SELECT NVL(PROGRAM_TOTAL_MARKS,0) ,NVL(PROGRAM_NO_QUES,0),IS_EQUAL_MARK_QUES ,NVL(PROGRAM_PASS_MARKS,0) "
					+ " ,NVL(MARKS_EASY_QUES,0),NVL( MARKS_MEDIUM_QUES,0),NVL(MARKS_HARD_QUES,0) "
					+ "	,NVL(NEGATIVE_MARK_EASY_QUES,0),NVL( NEGATIVE_MEDIUM_QUES,0),NVL( NEGATIVE_MARK_HARD_QUES,0) "
					+ "	,NVL(MARK_EACH_QUES,0),IS_EQUAL_MARK_QUES_TYPE ,IS_QUES_RANDOM FROM WBT_PROGRAM_QUES_HDR "
					+ " WHERE "
					+ " PROGRAM_ID="
					+ programCode
					+ " AND MODULE_ID=" + moduleCode;

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				queryHdr += " AND SECTION_ID=" + sectionCode;
			} else {
				queryHdr += "AND SECTION_ID is null";
			}

			templateHdr = getSqlModel().getSingleResult(queryHdr);

			if (templateHdr != null && templateHdr.length > 0) {
				bean.setTempTotalMarks(checkNull(String
						.valueOf(templateHdr[0][0])));// total marks
				bean.setTempTotalQues(checkNull(String
						.valueOf(templateHdr[0][1])));// total questions
				// bean.setTestType(String.valueOf(templateHdr[0][5]));//test
				// type is hidden(whether Objective or subjective)
				bean.setEqualweightage(String.valueOf(templateHdr[0][2]));// equalWeightAge
				// bean.setMarksWrongAns(String.valueOf(templateHdr[0][7]));//marks
				// for wrong answer
				// bean.setMarksNoAns(String.valueOf(templateHdr[0][8]));//marks
				// for no answer
				// bean.setOnlineScore(String.valueOf(templateHdr[0][9]));//TEMPLATE_ONLINE_SCORE
				bean.setPassingMarks(String.valueOf(templateHdr[0][3]));// TEMPLATE_PASSING_MARKS
				// bean.setMarksForCorrect(String.valueOf(templateHdr[0][11]));//Marks
				// for Correct

				bean.setMarksEasy(String.valueOf(templateHdr[0][4]));// Marks
				// for
				// Easy

				bean.setMarksMedium(String.valueOf(templateHdr[0][5]));// Marks
				// for
				// Medium

				bean.setMarksHard(String.valueOf(templateHdr[0][6]));// Marks
				// for
				// Hard
				bean.setWrongmarksEasy(String.valueOf(templateHdr[0][7]));// Marks
				// for
				// Easy

				bean.setWrongmarksMedium(String.valueOf(templateHdr[0][8]));// Marks
				// for
				// Medium

				bean.setWrongmarksHard(String.valueOf(templateHdr[0][9]));// Marks
				// for
				// Hard

				bean.setEqualMarksForAll(String.valueOf(templateHdr[0][10]));// equal
				// marks

				bean.setEqualMarkForQueTypeCheck(String
						.valueOf(templateHdr[0][11]));

				bean.setIsRandomQues(String.valueOf(templateHdr[0][12]));

				setProgramName(programCode, bean);

				Object[][] templateDtl = null;
				Object[][] tempAddQues = null;
				tempAddQues = getTestTemplateAddQues(bean, programCode,
						moduleCode, sectionCode, "N");

				System.out.println("bean.getIsRandomQues()                 "
						+ bean.getIsRandomQues());
				if (tempAddQues != null && tempAddQues.length > 0) {
					if (bean.getIsRandomQues().equals("N")) {
						Random rgen = new Random(); // Random number generator
						String[] randomQueCodes = new String[tempAddQues.length];
						for (int i = 0; i < randomQueCodes.length; i++) {
							randomQueCodes[i] = "" + tempAddQues[i][0];
						}
						for (int i = 0; i < randomQueCodes.length; i++) {
							int randomPosition = rgen
									.nextInt(randomQueCodes.length);
							String temp = randomQueCodes[i];
							randomQueCodes[i] = randomQueCodes[randomPosition];
							randomQueCodes[randomPosition] = temp;
						}

						/**
						 * this for loop is run to set the hidden iterator
						 * list...
						 */
						for (int i = 0; i < tempAddQues.length; i++) {
							OnlineProgram innerbean = new OnlineProgram();
							innerbean.setRandomQuesCodes(randomQueCodes[i]);
							questionList.add(innerbean);
						}
						bean.setRandomQueList(questionList);
					}

					else {
						/**
						 * to retirieve the TESTTEMPDTL VALUES LIKE SUBJECT
						 * CODE,CATEGORY CODE,LEVEL...
						 */
						// templateDtl = getTemplateDtl(onlineTest);
						// if (templateDtl != null && templateDtl.length > 0) {
						Object[] totalQuesCodes = null;
						/**
						 * THIS OBJECT CONTAINS THE TOTAL NO OF QUESTION CODES
						 * TO BE APPEARED IN TEST
						 */

						totalQuesCodes = getQuesCodes(bean, programCode,
								moduleCode, sectionCode);
						if (totalQuesCodes == null) {
							result = "totalNoOfQuesNotDefined";
							return result;
						}
						Random rgen = new Random(); // Random number generator
						String[] randomQueCodes = new String[totalQuesCodes.length];
						// --- Initialize the array to the ints 0-51
						for (int i = 0; i < randomQueCodes.length; i++) {
							randomQueCodes[i] = String
									.valueOf(totalQuesCodes[i]);
						}
						// --- Shuffle by exchanging each element randomly
						for (int i = 0; i < randomQueCodes.length; i++) {
							int randomPosition = rgen
									.nextInt(randomQueCodes.length);
							String temp = randomQueCodes[i];
							randomQueCodes[i] = randomQueCodes[randomPosition];
							randomQueCodes[randomPosition] = temp;
						}

						/**
						 * this for loop is run to set the hidden iterator
						 * list...
						 */
						for (int i = 0; i < totalQuesCodes.length; i++) {
							OnlineProgram innerbean = new OnlineProgram();
							innerbean.setRandomQuesCodes(randomQueCodes[i]);
							questionList.add(innerbean);
						}
						bean.setRandomQueList(questionList);
						// }
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private Object[] getQuesCodes(OnlineProgram bean, String programCode,
			String moduleCode, String sectionCode) {
		// TODO Auto-generated method stub
		Object[][] quesCodeData = null;
		Object[] totalQuesCode = null;
		String totalQues = bean.getTempTotalQues().equals("") ? "0" : bean
				.getTempTotalQues();

		totalQuesCode = new Object[Integer.parseInt(totalQues)];

		int count = 0;
		String query = " SELECT PROGRAM_QUES_ID, PROGRAM_ID, MODULE_ID, SECTION_ID, IS_QUES_RANDOM, PROGRAM_NO_QUES, MARK_EACH_QUES, PROGRAM_TOTAL_MARKS, PROGRAM_PASS_MARKS, IS_EQUAL_MARK_QUES, MARKS_EASY_QUES, MARKS_MEDIUM_QUES, MARKS_HARD_QUES, IS_NEGATIVE_MARK_QUES, NEGATIVE_MARK_EASY_QUES, NEGATIVE_MEDIUM_QUES, NEGATIVE_MARK_HARD_QUES "
				+ " FROM WBT_PROGRAM_QUES_HDR "
				+ " WHERE PROGRAM_ID="
				+ programCode + " AND MODULE_ID=" + moduleCode;

		if (sectionCode != null && !sectionCode.equals("null")
				&& !sectionCode.equals("")) {
			query += " AND SECTION_ID=" + sectionCode;
		} else {
			query += " AND SECTION_ID is null ";
		}
		Object hdrQueryObj[][] = getSqlModel().getSingleResult(query);

		if (hdrQueryObj != null && hdrQueryObj.length > 0) {
			String dtlQuery = " select QUES_CODE, QUES_TYPE, QUES_LEVEL, QUES_MARKS from WBT_PROGRAM_QUES_DTL "
					+ " where PROGRAM_QUES_ID="
					+ String.valueOf(hdrQueryObj[0][0])
					+ " ORDER BY DBMS_RANDOM.VALUE ";

			quesCodeData = getSqlModel().getSingleResult(dtlQuery);

			if (quesCodeData != null && quesCodeData.length > 0) {

				for (int j = 0; j < Integer.parseInt(totalQues); j++) {

					totalQuesCode[count++] = quesCodeData[j][0];
				}
			}
		}

		return totalQuesCode;
	}

	/**
	 * THIS METHOD IS CALLED FOR UPDATING RECORD...AND ON SUBMIT AND IN START
	 * TEST WHEN ON PREVIOUS AND NEXT BUTTONS ARE CLICKED...
	 * 
	 * @param onlineTest
	 * @param answerCode
	 * @param answerCorrect
	 * @return
	 */
	private Object[][] getScoreEqWtSubmit(OnlineProgram onlineTest,
			String answerCode, String answerCorrect) {
		int score = 0;
		Object[][] scoreDtl = new Object[1][2];
		try {
			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE
			 * FOR EVERY RIGHT ANSWER
			 */
			if (onlineTest.getEqualweightage().equals("Y")) {
				score = Integer.parseInt(onlineTest.getEqualMarksForAll());
			}
			if (answerCode == null || answerCode.equals("")) {
				score = 0;// Integer.parseInt(onlineTest.getMarksNoAns());
				answerCorrect = "";
			} else {
				if (!(answerCorrect == null || answerCorrect.equals(""))) {
					if (answerCorrect == "N" || answerCorrect.equals("N")) {
						if (onlineTest.getQuestionLevel().equals("E")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksEasy());
						} else if (onlineTest.getQuestionLevel().equals("M")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksMedium());
						} else if (onlineTest.getQuestionLevel().equals("H")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksHard());
						}
					}
				}
			}
			scoreDtl[0][0] = score;
			scoreDtl[0][1] = answerCorrect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoreDtl;
	} // end of getScoreEqWtSubmit method

	private Object[][] getScoreForCorrectSubmit(OnlineProgram onlineTest,
			String answerCode, String answerCorrect, int sequence) {
		int score = 0;
		Object[][] scoreDtl = new Object[1][2];
		try {
			if (answerCode == null || answerCode.equals("")) {
				score = 0;// Integer.parseInt(onlineTest.getMarksNoAns());
				answerCorrect = "";
			} else {
				if (!(answerCorrect == null || answerCorrect.equals(""))) { // if 1
					if (answerCorrect == "N" || answerCorrect.equals("N")) {
						if (onlineTest.getQuestionLevel().equals("E")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksEasy());
						} else if (onlineTest.getQuestionLevel().equals("M")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksMedium());
						} else if (onlineTest.getQuestionLevel().equals("H")) {
							score = Integer.parseInt(onlineTest
									.getWrongmarksHard());
						}
					}
					/**
					 * THIS ELSE IF FOR IF ANSWER IS GIVEN...THEN HERE IT WILL
					 * CHECK THE COMPLEXITY LEVEL IF THE LEVEL IS "HARD" THEN
					 * FOR THE RESPECTIVE QUESTION HARD MARKS WILL BE GIVEN SAME
					 * CASES IN MEDIUM AND EASY TYPE LEVELS....
					 */
					else {
						if (onlineTest.getQuestionLevel().equals("E")) {
							score = Integer.parseInt(onlineTest.getMarksEasy());
						} else if (onlineTest.getQuestionLevel().equals("M")) {
							score = Integer.parseInt(onlineTest
									.getMarksMedium());
						} else if (onlineTest.getQuestionLevel().equals("H")) {
							score = Integer.parseInt(onlineTest.getMarksHard());
						}
					}
				}
			}
			scoreDtl[0][0] = score;
			scoreDtl[0][1] = answerCorrect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoreDtl;
	}

	/**
	 * THIS METHOD TO GET THE SCORE DETAILS WHICH WILL BE USED IN THE UPDATE
	 * QUERY...IT RETURNS THE "SCORE" AND "ANSWER CORRECT"= "Y" OR "N" OR " "...
	 * 
	 * @param onlineTest
	 * @param answerCode
	 * @param answerCorrect
	 * @param sequence
	 * @return
	 */
	private Object[][] getScoreDtlEqWtage(OnlineProgram onlineTest,
			String answerCode, String answerCorrect, int sequence) {
		Object[][] scoreDtl = new Object[1][2];
		try {
			int score = 0;
			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE
			 * FOR EVERY RIGHT ANSWER
			 */
			if (onlineTest.getEqualweightage().equals("Y")) {
				score = Integer.parseInt(onlineTest.getEqualMarksForAll());
			}
			if (sequence > 1) {
				if (answerCode == null || answerCode.equals("")) {
					score = 0;// Integer.parseInt(onlineTest.getMarksNoAns());
				}

				if (answerCode == null || answerCode.equals("")) {
					score = 0;// Integer.parseInt(onlineTest.getMarksNoAns());
					answerCorrect = "";
				} else {
					if (!(answerCorrect == null || answerCorrect.equals(""))) {
						if (answerCorrect == "N" || answerCorrect.equals("N")) {
							if (onlineTest.getQuestionLevel().equals("E")) {
								score = Integer.parseInt(onlineTest
										.getWrongmarksEasy());
							} else if (onlineTest.getQuestionLevel()
									.equals("M")) {
								score = Integer.parseInt(onlineTest
										.getWrongmarksMedium());
							} else if (onlineTest.getQuestionLevel()
									.equals("H")) {
								score = Integer.parseInt(onlineTest
										.getWrongmarksHard());
							}
						}
					}
				}
			}
			scoreDtl[0][0] = score;
			scoreDtl[0][1] = answerCorrect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoreDtl;
	}

	public String isModuleContainQue(String programCode, String moduleCode) {
		String moduleHasQues = "N";
		try {
			String query = " SELECT  IS_PROGRAM_QUES  FROM WBT_PROGRAM_DTL "
					+ " WHERE PROGRAM_ID=" + programCode
					+ " AND  PROGRAM_MODULE_CODE =" + moduleCode;
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				moduleHasQues = String.valueOf(dataObj[0][0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return moduleHasQues;
	}

	public String isSectionContainQue(String programCodeStr,
			String moduleCodeStr, String sectionCodeStr) {
		String sectionHasQUe = "N";
		try {
			String query = " SELECT IS_SECTION_QUES FROM WBT_PROGRAM_SECTION "
					+ " WHERE MODULE_ID= " + moduleCodeStr
					+ " AND PROGRAM_ID= " + programCodeStr + " AND SECTION_ID="
					+ sectionCodeStr;
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				sectionHasQUe = String.valueOf(dataObj[0][0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sectionHasQUe;
	}

	public void setProgramName(String programCode, OnlineProgram bean) {
		try {
			String programNameQuery = " select PROGRAM_NAME from  WBT_PROGRAM_HDR where PROGRAM_ID="
					+ programCode;
			Object dataObj[][] = getSqlModel()
					.getSingleResult(programNameQuery);
			if (dataObj != null && dataObj.length > 0) {
				bean
						.setTestProgramName(checkNull(String
								.valueOf(dataObj[0][0])));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * THIS METHOD IS CALLED ONLY IF ONLINE SCORE TO BE SHOWN IS "YES"...HERE
	 * RESULT,PASSING MARKS, MARKS OBTAINED,PERCENTAGE ARE DISPLAYED...
	 * 
	 * @param onlineTest
	 * @param request
	 */
	public void getTestResult(OnlineProgram onlineTest,
			HttpServletRequest request, String applicationCode,
			String moduleCode, String sectionCode, String programCode,
			String userCode, String userType, String testStatus, String attempt) {
		try {
			int marksObtained = 0;
			int totalMarks = 0;
			Object[][] result = null;

			int percentage = 0;
			int correctMarks = 0, negativeMarks = 0, blankMarks = 0;
			String query = "  select NVL(ONLINE_SCORE,0), ONLINE_CORRECT "
					+ " from WBT_ONLINETEST  " + " where 1=1 ";

			if (!String.valueOf(applicationCode).equals("0")
					&& !String.valueOf(applicationCode).equals("")) {
				query += "AND ONLINE_APPLICATION_CODE = " + applicationCode;
			}
			query += "  and  ONLINE_MODULE_CODE=" + moduleCode
					+ "  and  ONLINE_PROGRAM_ID=" + programCode
					+ " and  ONLINE_USER_CODE=" + (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
					+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " and ONLINE_USER_TYPE='" + (String)session.getAttribute("userType") + "'  ";

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " AND ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " AND ONLINE_SECTION_CODE is null ";
			}
			query += " AND ONLINE_ATTEMPT=" + onlineTest.getAttempt();

			result = getSqlModel().getSingleResult(query);
			if (result != null) { // if 1
				for (int i = 0; i < result.length; i++) {
					if (result[i][1] != null && result[i][1].equals("Y")) {
						marksObtained += Integer.parseInt(String
								.valueOf(result[i][0]));
						correctMarks += Integer.parseInt(String
								.valueOf(result[i][0]));
					} else {
						marksObtained -= Integer.parseInt(String
								.valueOf(result[i][0]));
						negativeMarks += Integer.parseInt(String
								.valueOf(result[i][0]));
					}

					if (result[i][1] == null) {
						blankMarks += Integer.parseInt(String
								.valueOf(result[i][0]));
					}
				}

				onlineTest.setCorrectMarks(String.valueOf(correctMarks));
				onlineTest.setNegativeMarks(String.valueOf(negativeMarks));
				onlineTest.setBlankAnswer(String.valueOf(blankMarks));

				System.out.println("marksObtained  ----------  "
						+ marksObtained);

				System.out.println(" onlineTest.getPassingMarks()  "
						+ onlineTest.getPassingMarks());

				System.out.println("onlineTesttTempTotalMarks() "
						+ onlineTest.getTempTotalMarks());

				System.out
						.println("Integer.parseInt(onlineTest.getPassingMarks()) "
								+ Integer
										.parseInt(onlineTest.getPassingMarks()));

				request.setAttribute("totalProgramMarks", onlineTest
						.getTempTotalMarks());
				request.setAttribute("totalMarksObtained", String
						.valueOf(marksObtained));
				request.setAttribute("passingMarks", onlineTest
						.getPassingMarks());

				if (marksObtained >= Integer.parseInt(onlineTest
						.getPassingMarks())) {
					onlineTest.setResult("Pass");
					onlineTest.setPassColor("true");
					onlineTest.setMarksObtained(String.valueOf(marksObtained));
					totalMarks = Integer.parseInt(onlineTest
							.getTempTotalMarks());
					percentage = ((marksObtained * 100) / totalMarks);

					System.out.println("percentage   " + percentage);

					onlineTest.setPercentage(String.valueOf(percentage) + "%");
				} else {
					if (marksObtained < 0) {
						onlineTest.setMarksObtained("0");
					}
					onlineTest.setMarksObtained(String.valueOf(marksObtained));
					totalMarks = Integer.parseInt(onlineTest
							.getTempTotalMarks());
					percentage = ((marksObtained * 100) / totalMarks);

					System.out.println("percentage   " + percentage);

					onlineTest.setPercentage(String.valueOf(percentage) + "%");
					onlineTest.setResult("Fail");
				}
			}
			String finalResult = "";
			if (onlineTest.getResult().equals("Pass")) {
				finalResult = "P";
			} else {
				finalResult = "F";
			}
			/**
			 * here if the test type is "Objective" then only the records will
			 * get saved else if it is "Subjective" or "Both" the details will
			 * not be saved.
			 */

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				String resultQuery = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE, ONLINE_SECTION_CODE, ONLINE_TEST_STATUS,TEST_TOTAL_MARKS, TEST_OBT_MARKS, TEST_RESULT,ONLINE_TEST_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME)"
						+ "VALUES ("
						+ (String)session.getAttribute("userCode")
						+ ","
						+ applicationCode
						+ ","
						+ programCode
						+ ",'"
						+ (String)session.getAttribute("userType")
						+ "',"
						+ moduleCode
						+ ","
						+ sectionCode
						+ ",'"
						+ testStatus
						+ "',"
						+ totalMarks
						+ ","
						+ marksObtained
						+ ",'"
						+ finalResult
						+ "',"
						+ attempt + ",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"+")";

				getSqlModel().singleExecute(resultQuery);
			} else {

				String resultQuery = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE,ONLINE_TEST_STATUS,TEST_TOTAL_MARKS, TEST_OBT_MARKS, TEST_RESULT,ONLINE_TEST_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME)"
						+ "VALUES ("
						+ (String)session.getAttribute("userCode")
						+ ","
						+ applicationCode
						+ ","
						+ programCode
						+ ",'"
						+ (String)session.getAttribute("userType")
						+ "',"
						+ moduleCode
						+ ",'"
						+ testStatus
						+ "',"
						+ totalMarks
						+ ","
						+ marksObtained
						+ ",'"
						+ finalResult
						+ "',"
						+ attempt +",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
						+ ")";

				getSqlModel().singleExecute(resultQuery);
			}

			if (onlineTest.getTestType().equals("O")) {/*
			 * System.out.println("onlineTest.getTestType()
			 * ##############
			 * >>>>>>"+onlineTest.getTestType());
			 * Object[][]
			 * schTestDtlCode =
			 * null; String
			 * dtlCodeQuery =
			 * "SELECT TEST_DTL_CODE
			 * FROM
			 * HRMS_REC_SCHTEST_DTL " + "
			 * WHERE
			 * HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " +
			 * onlineTest.getReqCode() + "
			 * AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " +
			 * onlineTest.getCandidateUserEmpId() + "
			 * AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CODE = " +
			 * onlineTest.getOnlineTestCode();
			 * schTestDtlCode =
			 * getSqlModel().getSingleResult(dtlCodeQuery);
			 * 
			 * String insert =
			 * "INSERT INTO
			 * HRMS_REC_TESTRESULT
			 * (TEST_RESULT_CODE,TEST_REQS_CODE,TEST_CAND_CODE,TEST_TOTAL_MARKS, " + "
			 * TEST_OBT_MARKS,TEST_RESULT,TEST_TYPE,TEST_DTL_CODE,
			 * TEST_OBJECTIVE_MARKS) " + "
			 * VALUES((SELECT
			 * NVL(MAX(TEST_RESULT_CODE)+1,1)
			 * FROM
			 * HRMS_REC_TESTRESULT)," +
			 * onlineTest.getReqCode() +
			 * "," +
			 * onlineTest.getCandidateUserEmpId() +
			 * "," + totalMarks + ", " + " " +
			 * marksObtained + ",'" +
			 * finalResult +
			 * "','O'," +
			 * schTestDtlCode[0][0] +
			 * ","+marksObtained+")";
			 * getSqlModel().singleExecute(insertTestResult);
			 * //BOTH/SUBJECTIVE >>>
			 * Marks obtainded &
			 * final result >> NO
			 * 
			 * String updateTestSch =
			 * "UPDATE
			 * HRMS_REC_SCHTEST_DTL
			 * SET
			 * HRMS_REC_SCHTEST_DTL.TEST_STATUS =
			 * 'Y' " + " WHERE
			 * HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " +
			 * onlineTest.getReqCode() + "
			 * AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " +
			 * onlineTest.getCandidateUserEmpId() + "
			 * AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CODE = " +
			 * onlineTest.getOnlineTestCode();
			 * getSqlModel().singleExecute(updateTestSch);
			 */
			} else {/*
			 * Object[][] schTestDtlCode = null; String dtlCodeQuery =
			 * "SELECT HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE FROM
			 * HRMS_REC_SCHTEST_DTL " + " WHERE
			 * HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " +
			 * onlineTest.getReqCode() + " AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " +
			 * onlineTest.getCandidateUserEmpId() + " AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CODE = " +
			 * onlineTest.getOnlineTestCode(); schTestDtlCode =
			 * getSqlModel().getSingleResult(dtlCodeQuery);
			 * 
			 * String insertTestResult = "INSERT INTO
			 * HRMS_REC_TESTRESULT
			 * (TEST_RESULT_CODE,TEST_REQS_CODE,TEST_CAND_CODE,TEST_TOTAL_MARKS, " + "
			 * TEST_OBT_MARKS,TEST_RESULT,TEST_TYPE,TEST_DTL_CODE,
			 * TEST_OBJECTIVE_MARKS) " + " VALUES((SELECT
			 * NVL(MAX(TEST_RESULT_CODE)+1,1) FROM
			 * HRMS_REC_TESTRESULT)," + onlineTest.getReqCode() + "," +
			 * onlineTest.getCandidateUserEmpId() + "," + totalMarks + ", " + " " +
			 * 0 + ",'" + "" + "','"+onlineTest.getTestType()+"'," +
			 * schTestDtlCode[0][0] + ","+marksObtained+")";
			 * getSqlModel().singleExecute(insertTestResult);
			 * //BOTH/SUBJECTIVE >>> Marks obtainded & final result >>
			 * NO
			 * 
			 * String updateTestSch = "UPDATE HRMS_REC_SCHTEST_DTL SET
			 * HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y' " + " WHERE
			 * HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " +
			 * onlineTest.getReqCode() + " AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " +
			 * onlineTest.getCandidateUserEmpId() + " AND
			 * HRMS_REC_SCHTEST_DTL.TEST_CODE = " +
			 * onlineTest.getOnlineTestCode();
			 * getSqlModel().singleExecute(updateTestSch);
			 */
			}
			callFinish(onlineTest, request, applicationCode, moduleCode,
					sectionCode, programCode, userCode, userType, testStatus,
					"N");
			
			
			request.setAttribute("moduleCode", moduleCode);
			request.setAttribute("sectionCode", sectionCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of getTestResult method

	public void callFinish(OnlineProgram bean, HttpServletRequest request,
			String applicationCode, String moduleCode, String sectionCode,
			String programCode, String userCode, String userType,
			String testStatus, String isFinishClick) {

		try {
			Object selectObj[][] = null;
			String query = "  SELECT  *  FROM WBT_ONLINETEST_RESULT  WHERE ONLINE_USER_CODE ="
					+ (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
					+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_USER_TYPE='"
					+ (String)session.getAttribute("userType")
					+ "'  AND ONLINE_MODULE_CODE="
					+ moduleCode
					+ " AND ONLINE_PROGRAM_ID=" + programCode + " ";

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " and ONLINE_SECTION_CODE is null ";
			}

			query += " and ONLINE_TEST_ATTEMPT=" + bean.getAttempt();

			selectObj = getSqlModel().getSingleResult(query);

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {

				if (selectObj != null && selectObj.length > 0) {
					String updateQuery = " update  WBT_ONLINETEST_RESULT set  ONLINE_MARK_READ='"
							+ isFinishClick
							+ "' "
							+ " where ONLINE_USER_CODE ="
							+ (String)session.getAttribute("userCode")
							+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
							+ " AND ONLINE_USER_TYPE='"
							+ (String)session.getAttribute("userType")
							+ "'  AND ONLINE_MODULE_CODE="
							+ moduleCode
							+ " AND ONLINE_PROGRAM_ID="
							+ programCode
							+ " and ONLINE_SECTION_CODE=" + sectionCode

							+ " and ONLINE_TEST_ATTEMPT=" + bean.getAttempt();

					getSqlModel().singleExecute(updateQuery);
				} else {
					String resultQuery = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE, ONLINE_SECTION_CODE, ONLINE_TEST_STATUS,TEST_TOTAL_MARKS, TEST_OBT_MARKS, TEST_RESULT,ONLINE_MARK_READ,ONLINE_TEST_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME)"
							+ "VALUES ("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ ","
							+ sectionCode
							+ ",'"
							+ testStatus
							+ "',0,0,'P','"
							+ isFinishClick
							+ "',"
							+ bean.getAttempt() +",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')" +")";

					getSqlModel().singleExecute(resultQuery);
				}

			} else {
				if (selectObj != null && selectObj.length > 0) {
					String updateQuery = " update  WBT_ONLINETEST_RESULT set  ONLINE_MARK_READ='"
							+ isFinishClick
							+ "' "
							+ " where ONLINE_USER_CODE ="
							+ (String)session.getAttribute("userCode")
							+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
							+ " AND ONLINE_USER_TYPE='"
							+ (String)session.getAttribute("userType")
							+ "'  AND ONLINE_MODULE_CODE="
							+ moduleCode
							+ " AND ONLINE_PROGRAM_ID="
							+ programCode
							+ " and  ONLINE_SECTION_CODE is null "
							+ " and ONLINE_TEST_ATTEMPT=" + bean.getAttempt();
					getSqlModel().singleExecute(updateQuery);
				} else {
					String resultQuery = " insert into WBT_ONLINETEST_RESULT (ONLINE_USER_CODE, ONLINE_APPLICATION_CODE, ONLINE_PROGRAM_ID, ONLINE_USER_TYPE, ONLINE_MODULE_CODE,ONLINE_TEST_STATUS,TEST_TOTAL_MARKS, TEST_OBT_MARKS, TEST_RESULT,ONLINE_MARK_READ,ONLINE_TEST_ATTEMPT,ONLINE_INSTANCE_NAME, ONLINE_USER_DATETIME)"
							+ "VALUES ("
							+ (String)session.getAttribute("userCode")
							+ ","
							+ applicationCode
							+ ","
							+ programCode
							+ ",'"
							+ (String)session.getAttribute("userType")
							+ "',"
							+ moduleCode
							+ ",'"
							+ testStatus
							+ "',0,0,'P','"
							+ isFinishClick + "'," + bean.getAttempt() +",'"+(String)session.getAttribute("instance")+"',TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')" + ")";

					getSqlModel().singleExecute(resultQuery);
				}

			}

			String finalResult = isProgramCompleted(bean, programCode);

			System.out.println("result for tech finalResult---------------"
					+ finalResult);
			System.out.println("programCode  "+programCode);
 
			if (!finalResult.equals("")) {
				try {
					
					//commented by Lakkichand
					/*if(programCode.equals("1"))
					{
						finalResult = finalResult.equals("Pass") ? "14" : "13";
						System.out.println("result for tech portal-----prog 1----------"
								+ finalResult);
					}
					if(programCode.equals("2")||programCode.equals("3"))
					{
						finalResult = finalResult.equals("Pass") ? "16" : "13";
						System.out.println("result for tech portal-----prog 2 or 3----------"
								+ finalResult);
					}*/
					String instance = (String)session.getAttribute("instance"); 
					if(instance.equals("")){
						instance = String.valueOf(session.getAttribute("instance"));
					}
					System.out.println(":" + (String)session.getAttribute("instance"));
				
					System.out.println("session.getAttribute(\"instance\"):" + session.getAttribute("instance"));
					
				
					String resultQuery=" SELECT MODULE,SECTION,ATTEMPT,RESULT FROM ( "
								+" SELECT SUBJECT_NAME MODULE ,NVL(CAT_NAME,'NA') SECTION , NVL(ONLINE_TEST_ATTEMPT,1) ATTEMPT,"
								+" DECODE(TEST_RESULT,'P','Pass','F','Fail') RESULT,"
								+" RANK() OVER (PARTITION BY ONLINE_PROGRAM_ID,SUBJECT_NAME,CAT_NAME ORDER BY ONLINE_TEST_ATTEMPT DESC) TESTSEQ "
								+" FROM WBT_ONLINETEST_RESULT  "
								+" LEFT JOIN HRMS_REC_CATEGORY ON (WBT_ONLINETEST_RESULT.ONLINE_SECTION_CODE=HRMS_REC_CATEGORY.CAT_CODE)  "
								+" INNER JOIN  HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_ONLINETEST_RESULT.ONLINE_MODULE_CODE)"  
								+" WHERE  ONLINE_USER_CODE=" + (String)session.getAttribute("userCode")
								+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")+"'"
								+"  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')" 
								+" ORDER BY CAT_NAME) WHERE TESTSEQ=1";
																
								
					Object[][]resObj=getSqlModel().getSingleResult(resultQuery);
					
					String xmlParam="<WBT Result='"+finalResult+"' Program='"+(String)session.getAttribute("programCode")+ "' EmployeeId='"+bean.getUserCode()+"' instanceName='"+instance+"'>";
					
					for (int i = 0; i < resObj.length; i++) {
						
						xmlParam+="<RECORD> "
						+"<MODULE>"+String.valueOf(resObj[i][0])+"</MODULE>"
						+"<SECTION>"+String.valueOf(resObj[i][1])+"</SECTION> "
					    +"<NO_OF_ATTEMPTS>"+String.valueOf(resObj[i][2])+"</NO_OF_ATTEMPTS> "
						+"<RESULT>"+String.valueOf(resObj[i][3])+"</RESULT>"
						+"</RECORD>";
						
						 
					}
					xmlParam+="</WBT>";
					System.out.println(xmlParam);
					TechPortalWebServiceModel model= new TechPortalWebServiceModel();
					
					String output =model.updateWBTResultToTechPortal(xmlParam);
					System.out.println("output   " + output);

					} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkMarkAsRead(String userCode, String applicationCode,
			String programCode, String userType, String moduleCode,
			String sectionCode, OnlineProgram bean) {
		String checkMarkAsRead = "N";
		try {
			String query = "  SELECT   ONLINE_MARK_READ  FROM WBT_ONLINETEST_RESULT "
					+ " WHERE ONLINE_USER_CODE =" + (String)session.getAttribute("userCode")
			+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
			+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";

			if (!String.valueOf(applicationCode).equals("0")
					&& !String.valueOf(applicationCode).equals("")) {
				query += "AND ONLINE_APPLICATION_CODE = " + applicationCode;
			}
			query += " AND ONLINE_USER_TYPE='" + (String)session.getAttribute("userType") + "'"
					+ "  AND ONLINE_MODULE_CODE=" + moduleCode;

			query += " AND ONLINE_PROGRAM_ID=" + programCode;

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " and ONLINE_SECTION_CODE is null ";
			}
			query += " AND ONLINE_TEST_ATTEMPT="
					+ getMaxAttemptCount(programCode, bean, moduleCode,
							sectionCode);
			Object[][] markAsReadstatusObj = getSqlModel().getSingleResult(
					query);
			if (markAsReadstatusObj != null && markAsReadstatusObj.length > 0) {
				checkMarkAsRead = String.valueOf(markAsReadstatusObj[0][0]);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return checkMarkAsRead;

	}

	public Object[][] getFinalResult(String programCode, OnlineProgram bean,
			String moduleCode) {
		Object[][] finalResultObj = null;
		finalResultObj = new Object[1][3];

		try {

			System.out
					.println("moduleCode in final result-------" + moduleCode);

			Object[][] moduleResultObj = checkisAllModulePass(programCode,
					bean, moduleCode);
			Object[][] sectionResultObj = checkisAllSectionPass(programCode,
					bean, moduleCode);
			int totalMarksObtained = 0;

			System.out.println("moduleResultObj.length============  "
					+ moduleResultObj.length);
			
			totalMarksObtained =getFinalMarks(bean, programCode, moduleCode, "");
			
			System.out.println("totalMarksObtained in final method---- "+totalMarksObtained);
			
			if (moduleResultObj != null && moduleResultObj.length > 0) {

				System.out.println("moduleCode result  -------"
						+ String.valueOf(moduleResultObj[0][0]));

				System.out.println("sectioncode result------"
						+ String.valueOf(sectionResultObj[0][0]));

				System.out.println("section Mark "
						+ Integer.parseInt(String
								.valueOf(sectionResultObj[0][2])));

				System.out.println("module mark  "
						+ Integer.parseInt(String
								.valueOf(moduleResultObj[0][2])));

				if (String.valueOf(moduleResultObj[0][0]).equals("Pass")
						&& String.valueOf(sectionResultObj[0][0])
								.equals("Pass")) {
					/*totalMarksObtained = Integer.parseInt(String
							.valueOf(moduleResultObj[0][2]))
							+ Integer.parseInt(String
									.valueOf(sectionResultObj[0][2]));*/
					finalResultObj[0][0] = "Pass";
					finalResultObj[0][1] = "Completed";
					finalResultObj[0][2] = totalMarksObtained;

				} else if (String.valueOf(moduleResultObj[0][0]).equals("Fail")
						&& String.valueOf(sectionResultObj[0][0])
								.equals("Pass")) {
					/*totalMarksObtained = Integer.parseInt(String
							.valueOf(moduleResultObj[0][2]))
							+ Integer.parseInt(String
									.valueOf(sectionResultObj[0][2]));*/
					finalResultObj[0][0] = "Fail";
					finalResultObj[0][1] = "Completed";
					finalResultObj[0][2] = totalMarksObtained;

				} else if (String.valueOf(moduleResultObj[0][0]).equals("Pass")
						&& String.valueOf(sectionResultObj[0][0])
								.equals("Fail")) {
					/*totalMarksObtained = Integer.parseInt(String
							.valueOf(moduleResultObj[0][2]))
							+ Integer.parseInt(String
									.valueOf(sectionResultObj[0][2]));*/
					finalResultObj[0][0] = "Fail";
					finalResultObj[0][1] = "Completed";
					finalResultObj[0][2] = totalMarksObtained;

				} else if (String.valueOf(moduleResultObj[0][0]).equals("Fail")
						&& String.valueOf(sectionResultObj[0][0])
								.equals("Fail")) {
					/*totalMarksObtained = Integer.parseInt(String
							.valueOf(moduleResultObj[0][2]))
							+ Integer.parseInt(String
									.valueOf(sectionResultObj[0][2]));*/
					finalResultObj[0][0] = "Fail";
					finalResultObj[0][1] = "Completed";
					finalResultObj[0][2] = totalMarksObtained;

				} else {
					finalResultObj[0][0] = "-";
					finalResultObj[0][1] = "-";
					finalResultObj[0][2] = "-";
				}

			} else {
				finalResultObj[0][0] = "-";
				finalResultObj[0][1] = "-";
				finalResultObj[0][2] = "-";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (finalResultObj != null) {
			for (int i = 0; i < finalResultObj.length; i++) {
				for (int j = 0; j < finalResultObj[0].length; j++) {
					System.out.println("Deepti            "
							+ finalResultObj[i][j]);
				}
			}
		}
		return finalResultObj;
	}

	public Object[][] checkisAllModulePass(String programCode,
			OnlineProgram bean, String moduleCode) {

		Object[][] finalmoduleResultObj = null;
		finalmoduleResultObj = new Object[1][3];
		try {
			int moduleCount = 0;
			String moduleQuery = "select PROGRAM_MODULE_CODE ,NVL(MODULE_NO_OF_ATTEMPT,0) from  WBT_PROGRAM_DTL "
					+ " where PROGRAM_ID="
					+ programCode
					+ " AND PROGRAM_MODULE_CODE=" + moduleCode;
			Object[][] moduleCodeObj = getSqlModel().getSingleResult(
					moduleQuery);
			if (moduleCodeObj != null && moduleCodeObj.length > 0) {

				moduleCount = moduleCodeObj.length;

				String query = " SELECT TEST_RESULT ,NVL(TEST_OBT_MARKS,0),ONLINE_TEST_ATTEMPT FROM WBT_ONLINETEST_RESULT "
						+ " WHERE ONLINE_USER_CODE="
						+ (String)session.getAttribute("userCode")
						+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
						+ " AND ONLINE_PROGRAM_ID="
						+ programCode
						+ "AND ONLINE_USER_TYPE='"
						+ (String)session.getAttribute("userType")
						+ "'"
						+ " AND ONLINE_MODULE_CODE="
						+ moduleCode
						+ " AND ONLINE_SECTION_CODE is NULL ";

				if (!String.valueOf(bean.getApplicationCode()).equals("0")
						&& !String.valueOf(bean.getApplicationCode())
								.equals("")) {
					query += "AND ONLINE_APPLICATION_CODE = "
							+ bean.getApplicationCode();
				}

				Object[][] moduleResultObj = getSqlModel().getSingleResult(
						query);

				if (moduleResultObj != null && moduleResultObj.length > 0) {
					int passCount = 0;
					int failCount = 0;
					boolean isTestOpen = false;
					int totalMarksObtained = 0;
					int remainingAttempt = 0;
					
					System.out.println("moduleResultObj.length----"+moduleResultObj.length);
					for (int i = 0; i < moduleResultObj.length; i++) {

						//totalMarksObtained=getTotalMarks(bean, programCode, moduleCode, "");
							
					System.out.println("i         :::::::  "+i);	
						System.out.println("totalMarksObtained in module "
								+ totalMarksObtained);

						remainingAttempt = Integer.parseInt(String
								.valueOf(moduleCodeObj[0][1]))
								- getRemainingModuleOrSectionAttempt(
										programCode, bean, moduleCode, "");

						System.out.println("remainingAttempt vish "
								+ remainingAttempt);
						
						System.out.println("pass count check "+String.valueOf(moduleResultObj[i][0]));
						
						if (String.valueOf(moduleResultObj[i][0]).equals("P")) {
							/*totalMarksObtained = Integer.parseInt(String
									.valueOf(moduleResultObj[i][1]));*/
							passCount++;
						} else if (String.valueOf(moduleResultObj[i][0])
								.equals("F")
								&& (remainingAttempt > 0)) {
							failCount++;
							/*	totalMarksObtained = Integer.parseInt(String
										.valueOf(moduleResultObj[i][1]));*/
							isTestOpen = true;
							//break;
						} else if (String.valueOf(moduleResultObj[i][0])
								.equals("F")
								&& (remainingAttempt == 0)) {
							failCount++;
							/*totalMarksObtained = Integer.parseInt(String
									.valueOf(moduleResultObj[i][1]));*/
							isTestOpen = false;
							//break;
						} else {
							isTestOpen = true;
							//break;
						}

					}

					System.out.println("passCount vish  " + passCount);
					System.out.println("moduleCount vish  " + moduleCount);
					System.out.println("isTestOpen vish " + isTestOpen);

					if (passCount == moduleCount) {
						finalmoduleResultObj[0][0] = "Pass";
						finalmoduleResultObj[0][1] = "Completed";
						finalmoduleResultObj[0][2] = totalMarksObtained;

					} else if (isTestOpen == false) {
						finalmoduleResultObj[0][0] = "Fail";
						finalmoduleResultObj[0][1] = "Completed";
						finalmoduleResultObj[0][2] = totalMarksObtained;
					} else if (isTestOpen == true) {
						finalmoduleResultObj[0][0] = "-";
						finalmoduleResultObj[0][1] = "Incompleted";
						finalmoduleResultObj[0][2] = totalMarksObtained;
					} else {
						finalmoduleResultObj[0][0] = "-";
						finalmoduleResultObj[0][1] = "Incompleted";
						finalmoduleResultObj[0][2] = totalMarksObtained;
					}

				} else {
					finalmoduleResultObj[0][0] = "-";
					finalmoduleResultObj[0][1] = "-";
					finalmoduleResultObj[0][2] = "0";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (finalmoduleResultObj != null) {
			for (int j = 0; j < finalmoduleResultObj.length; j++) {
				for (int j2 = 0; j2 < finalmoduleResultObj[0].length; j2++) {
					System.out.println("Omkara------  "
							+ finalmoduleResultObj[j][j2]);
				}
			}
		}
		return finalmoduleResultObj;
	}

	public int getTotalModuleMarks(OnlineProgram bean, String programCode,
			String moduleCode, String sectionCode) {
		int totalMarks = 0;
		try {
			String getMarksQuery = " select TEST_OBT_MARKS from WBT_ONLINETEST_RESULT "
					+ " WHERE  ONLINE_PROGRAM_ID="
					+ programCode
					+ " AND ONLINE_MODULE_CODE=" + moduleCode;

			if (!String.valueOf(bean.getApplicationCode()).equals("0")
					&& !String.valueOf(bean.getApplicationCode()).equals("")) {
				getMarksQuery += "AND ONLINE_APPLICATION_CODE = "
						+ bean.getApplicationCode();
			}
			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				getMarksQuery += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				getMarksQuery += " and ONLINE_SECTION_CODE is null ";
			}
			getMarksQuery += "  and ONLINE_USER_CODE=" +(String)session.getAttribute("userCode")
			+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " " + "  and ONLINE_USER_TYPE='" + (String)session.getAttribute("userType")
					+ "'";
			getMarksQuery += " AND ONLINE_TEST_ATTEMPT="
					+ getMaxAttemptCount(programCode, bean, moduleCode,
							sectionCode);

			Object markObj[][] = getSqlModel().getSingleResult(getMarksQuery);

			if (markObj != null && markObj.length > 0) {
				totalMarks = Integer.parseInt(String.valueOf(markObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			totalMarks = 0;
		}

		return totalMarks;
	}

	public int getFinalMarks(OnlineProgram bean, String programCode,
			String moduleCode, String sectionCode) {
		int totalMarks = 0;
		try {
			String moduleQuery = "select PROGRAM_MODULE_CODE ,NVL(MODULE_NO_OF_ATTEMPT,0) from  WBT_PROGRAM_DTL "
					+ " where PROGRAM_ID="
					+ programCode
					+ " AND PROGRAM_MODULE_CODE=" + moduleCode;

			Object[][] moduleCodeObj = getSqlModel().getSingleResult(
					moduleQuery);

			int totalObtainedMarks = 0;
			if (moduleCodeObj != null && moduleCodeObj.length > 0) {

				for (int i = 0; i < moduleCodeObj.length; i++) {

					String query = " SELECT TEST_RESULT ,round((NVL(TEST_OBT_MARKS,0)*100/TEST_TOTAL_MARKS)),NVL(TEST_OBT_MARKS,0) FROM WBT_ONLINETEST_RESULT "
							+ " WHERE ONLINE_USER_CODE="
							+ (String)session.getAttribute("userCode")
							+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
							+ " AND ONLINE_PROGRAM_ID="
							+ programCode
							+ "AND ONLINE_USER_TYPE='"
							+ (String)session.getAttribute("userType")
							+ "'"
							+ " AND ONLINE_MODULE_CODE="
							+ moduleCodeObj[i][0]
							+ " AND ONLINE_SECTION_CODE is NULL "
							+ " AND ONLINE_TEST_ATTEMPT="
							+ getMaxAttemptCount(programCode, bean, moduleCode,
									sectionCode);
					if (!String.valueOf(bean.getApplicationCode()).equals("0")
							&& !String.valueOf(bean.getApplicationCode())
									.equals("")) {
						query += "AND ONLINE_APPLICATION_CODE = "
								+ bean.getApplicationCode();
					}

					Object[][] moduleResultObj = getSqlModel().getSingleResult(
							query);

					if (moduleResultObj != null && moduleResultObj.length > 0) {
						for (int j = 0; j < moduleResultObj.length; j++) {
							totalObtainedMarks += Integer.parseInt(String
									.valueOf(moduleResultObj[j][1]));
						}

					}

				}

			}

			String sectionQuery = " select SECTION_ID from WBT_PROGRAM_SECTION "
					+ " where PROGRAM_ID="
					+ programCode
					+ " and MODULE_ID="
					+ moduleCode;

			Object[][] sectionCodeObj = getSqlModel().getSingleResult(
					sectionQuery);

			if (sectionCodeObj != null && sectionCodeObj.length > 0) {
				for (int i = 0; i < sectionCodeObj.length; i++) {

					String query = " SELECT TEST_RESULT ,round((NVL(TEST_OBT_MARKS,0)*100/TEST_TOTAL_MARKS)),NVL(TEST_OBT_MARKS,0) FROM WBT_ONLINETEST_RESULT "
							+ " WHERE ONLINE_USER_CODE="
							+(String)session.getAttribute("userCode")
							+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
							+ " AND ONLINE_PROGRAM_ID="
							+ programCode
							+ "AND ONLINE_USER_TYPE='"
							+ (String)session.getAttribute("userType")
							+ "'"
							+ " AND ONLINE_SECTION_CODE="
							+ sectionCodeObj[i][0]
							+ " and ONLINE_MODULE_CODE="
							+ moduleCode
							+ " AND ONLINE_TEST_ATTEMPT="
							+ getMaxAttemptCount(programCode, bean, moduleCode,
									String.valueOf(sectionCodeObj[i][0]));

					if (!String.valueOf(bean.getApplicationCode()).equals("0")
							&& !String.valueOf(bean.getApplicationCode())
									.equals("")) {
						query += "AND ONLINE_APPLICATION_CODE = "
								+ bean.getApplicationCode();
					}
					Object[][] sectionResultObj = getSqlModel()
							.getSingleResult(query);

					if (sectionResultObj != null && sectionResultObj.length > 0) {

						for (int j = 0; j < sectionResultObj.length; j++) {
							totalObtainedMarks += Integer.parseInt(String
									.valueOf(sectionResultObj[j][1]));
						}

					}
				}

			}

			totalMarks = totalObtainedMarks;
			
			System.out.println("totalMarks WBT       "+totalMarks);

		} catch (Exception e) {
			e.printStackTrace();
			totalMarks = 0;
		}

		return totalMarks;
	}

	public Object[][] checkisAllSectionPass(String programCode,
			OnlineProgram bean, String moduleCode) {

		Object[][] sectionResultObj = null;

		try {
			String moduleQuery = "select PROGRAM_MODULE_CODE ,NVL(MODULE_NO_OF_ATTEMPT,0) from  WBT_PROGRAM_DTL "
					+ " where PROGRAM_ID="
					+ programCode
					+ " AND PROGRAM_MODULE_CODE="
					+ moduleCode
					+ " ORDER BY PROGRAM_MODULE_CODE ";
			Object[][] moduleCodeObj = getSqlModel().getSingleResult(
					moduleQuery);
			if (moduleCodeObj != null && moduleCodeObj.length > 0) {
				sectionResultObj = new Object[moduleCodeObj.length][3];

				for (int i = 0; i < moduleCodeObj.length; i++) {

					Object[][] sectionDataObj = getMarksAndResultForSection(
							programCode, String.valueOf(moduleCodeObj[i][0]),
							bean);
					
					if(sectionDataObj!=null)
					{
						for (int j = 0; j < sectionDataObj.length; j++) {
							for (int j2 = 0; j2 < sectionDataObj[0].length; j2++) {
								System.out.println("Amol       "+sectionDataObj[j][j2]);
							}
						}
					}

					if (sectionDataObj != null && sectionDataObj.length > 0) {
						int passCount = 0;
						int failCount = 0;
						boolean isTestOpen = false;
						int totalMarksObtained = 0;

						for (int j = 0; j < sectionDataObj.length; j++) {

							// totalMarksObtained=getTotalMarks(bean, programCode, moduleCode,String.valueOf(sectionDataObj[j][0]));

							System.out.println("totalMarksObtained section  "
									+ totalMarksObtained);

							System.out.println("result-----"
									+ String.valueOf(sectionDataObj[j][5]));

							System.out.println("remaining Count "
									+ Integer.parseInt(String
											.valueOf(sectionDataObj[j][2])));

							System.out.println("totalMarksObtained OM "
									+ String.valueOf(sectionDataObj[j][4]));

							if (String.valueOf(sectionDataObj[j][5]).equals(
									"Pass")) {
								/*
								 * totalMarksObtained += Integer.parseInt(String
								 * .valueOf(sectionDataObj[j][4]).equals("NA")?"0":String
								 * .valueOf(sectionDataObj[j][4]));
								 */
								passCount++;
							} else if (String.valueOf(sectionDataObj[j][5])
									.equals("Fail")
									&& Integer.parseInt(String
											.valueOf(sectionDataObj[j][2])) > 0) {
								failCount++;
								/*
								 * totalMarksObtained += Integer.parseInt(String
								 * .valueOf(sectionDataObj[j][4]).equals("NA")?"0":String
								 * .valueOf(sectionDataObj[j][4]));
								 */
								isTestOpen = true;
								//break;
							} else if (String.valueOf(sectionDataObj[j][5])
									.equals("Fail")
									&& Integer.parseInt(String
											.valueOf(sectionDataObj[j][2])) == 0) {
								failCount++;
								/*
								 * totalMarksObtained += Integer.parseInt(String
								 * .valueOf(sectionDataObj[j][4]).equals("NA")?"0":String
								 * .valueOf(sectionDataObj[j][4]));
								 */
								isTestOpen = false;
								//break;
							} else {
								isTestOpen = true;
								//break;
							}

						}
						System.out.println("module Code-------"
								+ String.valueOf(moduleCodeObj[i][0]));
						System.out.println("isTestOpen " + isTestOpen);
						System.out.println("passCount " + passCount);
						System.out.println("sectionDataObj.length "
								+ sectionDataObj.length);
						System.out.println("totalMarksObtained"
								+ totalMarksObtained);

						if (passCount == sectionDataObj.length) {
							sectionResultObj[i][0] = "Pass";
							sectionResultObj[i][1] = "Completed";
							sectionResultObj[i][2] = totalMarksObtained;

						} else if (isTestOpen == false) {
							sectionResultObj[i][0] = "Fail";
							sectionResultObj[i][1] = "Completed";
							sectionResultObj[i][2] = totalMarksObtained;
						} else if (isTestOpen == true) {
							sectionResultObj[i][0] = "-";
							sectionResultObj[i][1] = "Incompleted";
							sectionResultObj[i][2] = totalMarksObtained;
						} else {
							sectionResultObj[i][0] = "-";
							sectionResultObj[i][1] = "Incompleted";
							sectionResultObj[i][2] = totalMarksObtained;
						}

					} else {
						sectionResultObj[i][0] = "Pass";
						sectionResultObj[i][1] = "Completed";
						sectionResultObj[i][2] = "0";
					}
				}
			}
			if (sectionResultObj != null) {
				for (int k = 0; k < sectionResultObj.length; k++) {
					for (int k2 = 0; k2 < sectionResultObj[0].length; k2++) {
						System.out.println("Pradnya------  "
								+ sectionResultObj[k][k2]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionResultObj;
	}

	public void getMarksAndResult(String programCode, OnlineProgram bean) {
		Object[][] dataObj = null;

		try {
			int remainingAttempts = 0;

			ArrayList<Object> list = new ArrayList<Object>();

			String modulQuery = "  SELECT   PROGRAM_MODULE_CODE,SUBJECT_NAME,IS_PROGRAM_ORDER,NVL(MODULE_NO_OF_ATTEMPT,0) "
					+ " FROM  WBT_PROGRAM_DTL  "
					+ "  INNER JOIN HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) "
					+ "  WHERE   WBT_PROGRAM_DTL.PROGRAM_ID="
					+ programCode
					+ "   AND SUBJECT_STATUS='A' ORDER BY PROGRAM_ORDER ";

			Object moduleNameObj[][] = getSqlModel()
					.getSingleResult(modulQuery);

			if (moduleNameObj != null && moduleNameObj.length > 0) {
				for (int i = 0; i < moduleNameObj.length; i++) {

					OnlineProgram inerBean = new OnlineProgram();
					inerBean.setModuleCodeItt(checkNull(String
							.valueOf(moduleNameObj[i][0])));
					inerBean.setModuleNameItt(checkNull(String
							.valueOf(moduleNameObj[i][1])));
					inerBean.setIsOrderRequired(checkNull(String
							.valueOf(moduleNameObj[i][2])));

					remainingAttempts = Integer.parseInt((String
							.valueOf(moduleNameObj[i][3])))
							- getRemainingModuleOrSectionAttempt(programCode,
									bean, inerBean.getModuleCodeItt(), "");

					Object[][] finalResultObj = getFinalResult(programCode,
							bean, String.valueOf(moduleNameObj[i][0]));

					if (finalResultObj != null) {
						for (int k = 0; k < finalResultObj.length; k++) {
							for (int j = 0; j < finalResultObj[0].length; j++) {
								System.out
										.println("finalResultObj[k][j]-----------vishu "
												+ finalResultObj[k][j]);
							}
						}
					}

					if (finalResultObj != null && finalResultObj.length > 0) {
						for (int j = 0; j < finalResultObj.length; j++) {
							inerBean.setModuleResultItt(checkNull(String
									.valueOf(finalResultObj[j][0])));

							inerBean.setCompletionStatusItt(checkNull(String
									.valueOf(finalResultObj[j][1])));

							inerBean.setModuleMarksItt(checkNull(String
									.valueOf(finalResultObj[j][2])));

							inerBean.setAttemptsRemaining(checkNull(String
									.valueOf(remainingAttempts)));
						}

					}

					list.add(inerBean);
				}
				bean.setModuleList(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getMarksAndResultForSection(String programCode,
			String moduleCode, OnlineProgram bean) {
		Object[][] dataObj = null;
		Object[][] sectionObj = null;

		try {

			ArrayList<Object> sectionList = new ArrayList<Object>();

			/*
			 * String sectionQuery = " select WBT_PROGRAM_SECTION.SECTION_ID,
			 * NVL(CAT_NAME,'')" + " FROM WBT_PROGRAM_SECTION " + " INNER JOIN
			 * HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE =
			 * WBT_PROGRAM_SECTION.SECTION_ID ) " + " WHERE PROGRAM_ID=" +
			 * programCode + " and MODULE_ID=" + moduleCode + " ORDER BY
			 * SECTION_ORDER ";
			 */

			String sectionQuery = " SELECT WBT_PROGRAM_SECTION.SECTION_ID, NVL(CAT_NAME,'') ,NVL(MODULE_NO_OF_ATTEMPT,0) "
					+ " FROM  WBT_PROGRAM_SECTION  "
					+ " INNER JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = WBT_PROGRAM_SECTION.SECTION_ID )  "
					+ " INNER JOIN  WBT_PROGRAM_DTL ON(WBT_PROGRAM_DTL.PROGRAM_ID = WBT_PROGRAM_SECTION.PROGRAM_ID AND WBT_PROGRAM_DTL.PROGRAM_ID="
					+ programCode
					+ " AND  WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE="
					+ moduleCode
					+ ") "
					+ " WHERE PROGRAM_ID="
					+ programCode
					+ " AND  MODULE_ID="
					+ moduleCode
					+ " ORDER BY SECTION_ORDER ";

			Object sectionNameObj[][] = getSqlModel().getSingleResult(
					sectionQuery);

			if (sectionNameObj != null && sectionNameObj.length > 0) {

				sectionObj = new Object[sectionNameObj.length][6];

				for (int i = 0; i < sectionNameObj.length; i++) {
					String query = " select  case when TEST_RESULT='P' Then 'Completed' else 'Incompleted' end status  ," +
							" case when ONLINE_MARK_READ='Y' then 'NA' else to_CHAR(round((NVL (test_obt_marks, 0)) * 100/ TEST_TOTAL_MARKS)) end ," +
							" case when TEST_RESULT='P' then 'Pass'  when TEST_RESULT='F' then 'Fail' when TEST_RESULT='N' then 'NA' else '' end result "
							+ " FROM  WBT_ONLINETEST_RESULT "
							+ "  WHERE ONLINE_PROGRAM_ID="
							+ programCode
							+ " and  ONLINE_MODULE_CODE="
							+ moduleCode
							+ " and ONLINE_SECTION_CODE="
							+ String.valueOf(sectionNameObj[i][0]);

					if (!bean.getApplicationCode().equals("0")
							&& !bean.getApplicationCode().equals("")) {
						query += "AND ONLINE_APPLICATION_CODE = "
								+ bean.getApplicationCode();
					}
					query += " AND ONLINE_USER_CODE =" + (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
					+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')";
					query += " AND ONLINE_USER_TYPE='" + (String)session.getAttribute("userType")
							+ "'";

					query += " order by ONLINE_TEST_ATTEMPT desc ";

					dataObj = getSqlModel().getSingleResult(query);

					sectionObj[i][0] = checkNull(String
							.valueOf(sectionNameObj[i][0]));// setSectionCodeItt

					sectionObj[i][1] = checkNull(String
							.valueOf(sectionNameObj[i][1]));// setSectionNameItt

					// OnlineProgram inerBean = new OnlineProgram();
					/*
					 * inerBean.setSectionCodeItt(checkNull(String
					 * .valueOf(sectionNameObj[i][0])));
					 * inerBean.setSectionNameItt(checkNull(String
					 * .valueOf(sectionNameObj[i][1])));
					 */

					int remainingAttempts = Integer.parseInt((String
							.valueOf(sectionNameObj[i][2])))
							- getRemainingModuleOrSectionAttempt(programCode,
									bean, moduleCode, String
											.valueOf(sectionNameObj[i][0]));

					sectionObj[i][2] = remainingAttempts;// setSectionAttemptRemaining

					/*
					 * inerBean.setSectionAttemptRemaining(checkNull(String
					 * .valueOf(remainingAttempts)));
					 */

					if (dataObj != null && dataObj.length > 0) {

						/*
						 * inerBean .setSectionCompletionStatusItt(checkNull(
						 * String.valueOf(dataObj[0][0])) .equals("") ? "-" :
						 * checkNull(String .valueOf(dataObj[0][0])));
						 */
						sectionObj[i][3] = checkNull(
								String.valueOf(dataObj[0][0])).equals("") ? "-"
								: checkNull(String.valueOf(dataObj[0][0]));// setSectionCompletionStatusItt

						sectionObj[i][4] = checkNull(
								String.valueOf(dataObj[0][1])).equals("") ? "-"
								: checkNull(String.valueOf(dataObj[0][1]));// setSectionMarksItt

						/*
						 * inerBean .setSectionMarksItt(checkNull(
						 * String.valueOf(dataObj[0][1])) .equals("") ? "-" :
						 * checkNull(String .valueOf(dataObj[0][1])));
						 */

						sectionObj[i][5] = checkNull(
								String.valueOf(dataObj[0][2])).equals("") ? "-"
								: checkNull(String.valueOf(dataObj[0][2]));// setSectionResultItt
						/*
						 * inerBean .setSectionResultItt(checkNull(
						 * String.valueOf(dataObj[0][2])) .equals("") ? "-" :
						 * checkNull(String .valueOf(dataObj[0][2])));
						 */

					} else {
						/*
						 * inerBean.setSectionCompletionStatusItt("-");
						 * inerBean.setSectionMarksItt("-");
						 * inerBean.setSectionResultItt("-");
						 */

						sectionObj[i][3] = "-";
						sectionObj[i][4] = "-";
						sectionObj[i][5] = "-";
					}
					// sectionList.add(inerBean);
				}
				// bean.setSectionList(sectionList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionObj;
	}

	public String setAttemptCount(OnlineProgram bean, String programCode,
			String sectionCode, String moduleCode) {
		String attempCount = "1";
		try {
			String query = " select NVL(MAX(ONLINE_TEST_ATTEMPT)+1,1) from WBT_ONLINETEST_RESULT "
					+ " where ONLINE_PROGRAM_ID="
					+ programCode
					+ " AND ONLINE_USER_CODE ="
					+ (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_USER_TYPE='"
					+ (String)session.getAttribute("userType")
					+ "'  AND ONLINE_MODULE_CODE=" + moduleCode;
			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " and ONLINE_SECTION_CODE is null ";
			}
			Object attemptCountNoObj[][] = getSqlModel().getSingleResult(query);
			if (attemptCountNoObj != null && attemptCountNoObj.length > 0) {
				attempCount = String.valueOf(attemptCountNoObj[0][0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return attempCount;
	}

	public String getMaxAttemptCount(String programCode, OnlineProgram bean,
			String moduleCode, String sectionCode) {
		String maxattemptCountNo = "1";

		try {

			String query = " select  MAX(ONLINE_TEST_ATTEMPT)  from WBT_ONLINETEST_RESULT"
					+ " where ONLINE_PROGRAM_ID="
					+ programCode
					+ " AND ONLINE_USER_CODE ="
					+(String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_USER_TYPE='" + (String)session.getAttribute("userType") + "' ";

			query += " AND ONLINE_MODULE_CODE=" + moduleCode;

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				query += " and ONLINE_SECTION_CODE=" + sectionCode;
			} else {
				query += " and ONLINE_SECTION_CODE is null ";
			}

			Object maxattemptCountNoObj[][] = getSqlModel().getSingleResult(
					query);
			if (maxattemptCountNoObj != null && maxattemptCountNoObj.length > 0) {
				maxattemptCountNo = String.valueOf(maxattemptCountNoObj[0][0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return maxattemptCountNo;
	}

	public int getRemainingModuleOrSectionAttempt(String programCode,
			OnlineProgram bean, String moduleCode, String sectionCode) {
		int userAttempt = 0;
		try {
			String userAttemptQuery = " select count(ONLINE_TEST_ATTEMPT) as userattempt from WBT_ONLINETEST_RESULT "
					+ " WHERE ONLINE_PROGRAM_ID="
					+ programCode
					+ " and  ONLINE_MODULE_CODE="
					+ moduleCode
					+ "   AND ONLINE_USER_CODE ="
					+ (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_USER_TYPE='" + (String)session.getAttribute("userType") + "' ";
			if (!sectionCode.equals("")) {
				userAttemptQuery += " and ONLINE_SECTION_CODE =" + sectionCode;
			} else {
				userAttemptQuery += " and ONLINE_SECTION_CODE is null";
			}

			Object[][] userAttemptObj = getSqlModel().getSingleResult(
					userAttemptQuery);
			if (userAttemptObj != null && userAttemptObj.length > 0) {
				userAttempt = Integer.parseInt(String
						.valueOf(userAttemptObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAttempt;
	}

	public String getResultTableAttemptCount(OnlineProgram bean,
			String programCode, String moduleCode, String sectionCode) {
		String totalattempt = "0";
		String query = " select distinct ONLINE_TEST_ATTEMPT from WBT_ONLINETEST_RESULT  "
				+ " WHERE ONLINE_USER_CODE="
				+ (String)session.getAttribute("userCode")
				+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
				+ " AND ONLINE_PROGRAM_ID="
				+ programCode
				+ " AND ONLINE_USER_TYPE='"
				+ (String)session.getAttribute("userType")
				+ "' AND ONLINE_MODULE_CODE=" + moduleCode;

		if (!sectionCode.equals("")) {
			query += " and ONLINE_SECTION_CODE =" + sectionCode;
		} else {
			query += " and ONLINE_SECTION_CODE is null";
		}

		Object attemptObj[][] = getSqlModel().getSingleResult(query);

		if (attemptObj != null && attemptObj.length > 0) {
			for (int i = 0; i < attemptObj.length; i++) {
				if (i == attemptObj.length) {
					totalattempt = String.valueOf(attemptObj[0][0]);
				} else {
					totalattempt += "," + String.valueOf(attemptObj[i][0]);
				}
			}
		}
		return totalattempt;
	}

	public int setRemainingAttemptCountForModelAndSection(String programCode,
			String moduleCode, String sectionCode, OnlineProgram bean) {
		int moduleAttemptCount = 0;
		int attemptCount = 0;
		int totalCount = 0;

		try {

			String moduleAttemptQuery = " SELECT    NVL(MODULE_NO_OF_ATTEMPT,0)"
					+ " FROM  WBT_PROGRAM_DTL"
					+ " WHERE   WBT_PROGRAM_DTL.PROGRAM_ID="
					+ programCode
					+ " and PROGRAM_MODULE_CODE=" + moduleCode;
			Object[][] moduleAttemptCountObj = getSqlModel().getSingleResult(
					moduleAttemptQuery);

			if (moduleAttemptCountObj != null
					&& moduleAttemptCountObj.length > 0) {
				moduleAttemptCount = Integer.parseInt(String
						.valueOf(moduleAttemptCountObj[0][0]));
			}
			String attemptedCountQuery = "  select  NVL(MAX(ONLINE_TEST_ATTEMPT),0)  from WBT_ONLINETEST_RESULT where ONLINE_PROGRAM_ID="
					+ programCode
					+ " AND ONLINE_USER_CODE ="
					+ (String)session.getAttribute("userCode")
					+" AND ONLINE_INSTANCE_NAME='"+(String)session.getAttribute("instance")
							+"'  AND ONLINE_USER_DATETIME=TO_DATE('"+(String)session.getAttribute("datetime")+"','MM/DD/YYYY HH:MI:SS AM')"
					+ " AND ONLINE_USER_TYPE='"
					+ (String)session.getAttribute("userType")
					+ "' "
					+ " and ONLINE_MODULE_CODE="
					+ moduleCode;
			if (!sectionCode.equals("")) {
				attemptedCountQuery += " and ONLINE_SECTION_CODE ="
						+ sectionCode;
			} else {
				attemptedCountQuery += " and ONLINE_SECTION_CODE is null";
			}

			Object[][] attemptCountObj = getSqlModel().getSingleResult(
					attemptedCountQuery);

			if (attemptCountObj != null && attemptCountObj.length > 0) {
				attemptCount = Integer.parseInt(String
						.valueOf(attemptCountObj[0][0]));
			}
			totalCount = moduleAttemptCount - attemptCount;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return totalCount;
	}
 

	public String isProgramCompleted(OnlineProgram bean, String programCode) {

		String result = "";
		try {
			String modulQuery = "  SELECT   PROGRAM_MODULE_CODE,SUBJECT_NAME,IS_PROGRAM_ORDER,NVL(MODULE_NO_OF_ATTEMPT,0) "
					+ " FROM  WBT_PROGRAM_DTL  "
					+ "  INNER JOIN HRMS_REC_SUBJECT ON( HRMS_REC_SUBJECT.SUBJECT_CODE =WBT_PROGRAM_DTL.PROGRAM_MODULE_CODE) "
					+ "  WHERE   WBT_PROGRAM_DTL.PROGRAM_ID="
					+ programCode
					+ "   AND SUBJECT_STATUS='A' ORDER BY PROGRAM_ORDER ";
			Object moduleNameObj[][] = getSqlModel()
					.getSingleResult(modulQuery);
			int remainingAttempts = 0;
			int passcount = 0;
			int failcount = 0;
			if (moduleNameObj != null && moduleNameObj.length > 0) {
				for (int i = 0; i < moduleNameObj.length; i++) {

					remainingAttempts = Integer.parseInt((String
							.valueOf(moduleNameObj[i][3])))
							- getRemainingModuleOrSectionAttempt(programCode,
									bean, String.valueOf(moduleNameObj[i][0]),
									"");

					/*
					 * Object[][] moduleDataObj = isAllSectionAndModulePassed(
					 * programCode, bean.getUserCode(), bean.getUserType(),
					 * bean.getApplicationCode(), bean,
					 * String.valueOf(moduleNameObj[i][0]), Integer
					 * .parseInt((String .valueOf(moduleNameObj[i][3]))),
					 * remainingAttempts);
					 */

					Object[][] moduleDataObj = getFinalResult(programCode,
							bean, String.valueOf(moduleNameObj[i][0]));

					for (int j = 0; j < moduleDataObj.length; j++) {
						System.out
								.println("String.valueOf(moduleDataObj[j][0])  "
										+ String.valueOf(moduleDataObj[j][0]));

						if (String.valueOf(moduleDataObj[j][0]).equals("Pass")) {
							passcount++;
						}
						if (String.valueOf(moduleDataObj[j][0]).equals("Fail")) {
							failcount++;
						}
					}
					int totalModuleCount = 0;

					String modulQuery1 = " select PROGRAM_MODULE_CODE from 	WBT_PROGRAM_dtl "
							+ " where PROGRAM_ID=" + programCode;
					Object totalModuleCountbj[][] = getSqlModel()
							.getSingleResult(modulQuery1);
					if (totalModuleCountbj != null
							&& totalModuleCountbj.length > 0) {
						totalModuleCount = totalModuleCountbj.length;
						System.out.println("totalModuleCount  "
								+ totalModuleCount);
					}

					System.out.println("totalModuleCount  " + totalModuleCount);

					System.out.println("passcount  " + passcount);

					System.out.println("(passcount+failcount)  "
							+ (passcount + failcount));

					if (totalModuleCount == passcount) {
						result = "Pass";
						return result;
					}
					if (totalModuleCount == (passcount + failcount)) {
						result = "Fail";
						return result;
					}

					System.out.println("result  " + result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getContent(String contentId, HttpServletRequest request) {
		
		String result="";
		String contentType="";
		String sql =" SELECT HRMS_WBT_CONTENT.CONTENT_TEXT, HRMS_WBT_CONTENT.CONTENT_TYPE," +
				" HRMS_WBT_CONTENT.CONTENT_URL " +
				" FROM HRMS_WBT_CONTENT " +
				" WHERE HRMS_WBT_CONTENT.CONTENT_ID = " + contentId;
		
		Object[][] dataObj = getSqlModel().getSingleResult(sql);
		
		if (dataObj != null	&& dataObj.length > 0) {
			contentType = String.valueOf(dataObj[0][1]);
			if(contentType.equalsIgnoreCase("text")){
				result = String.valueOf(dataObj[0][0]);				
			} else {
				result = String.valueOf(dataObj[0][2]);
			}
		}
		request.setAttribute("content", result);
		request.setAttribute("contentType", contentType);
		
		return result;
	}
}
