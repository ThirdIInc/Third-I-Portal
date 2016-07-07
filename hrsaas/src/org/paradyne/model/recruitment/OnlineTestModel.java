package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.OnlineTest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author varunk
 *
 */
public class OnlineTestModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(OnlineTestModel.class);
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  else {
			return result;
		} 
	}// end of checkNull
	
	 
	/**
	 * THIS METHOD IS TO GET THE QUESTION CODES WHICH WERE ADDED IN THE TEST TEMPLATE...
	 * @param onlineTest
	 * @return
	 */
	private Object[][] getTestTemplateAddQues(OnlineTest onlineTest) {
		Object[][]quesCodes = null;
		Object[][]codes =null;
		String str = "";
			try {
				String query = "SELECT TEMPLATE_QUES_CODE "
						+ " FROM HRMS_REC_TESTTEMP_QUES WHERE TEMPLATE_CODE = "
						+ onlineTest.getTestTemplateCode() + "";
				quesCodes = getSqlModel().getSingleResult(query);
				if (quesCodes != null && quesCodes.length > 0) {
					str = String.valueOf(quesCodes[0][0]);
					String str1[] = str.split(",");
					codes = new Object[str1.length][1];
					for (int i = 0; i < codes.length; i++) {
						codes[i][0] = str1[i];
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return codes;
	} //END OF getTestTemplateAddQues MEHTOD

	/**
	 * THIS METHOD IS CALLED TO RETRIEVE THE TOTAL NO OF QUES CODES. ALSO TEMPLATE DTL OBJECT IS PASSED
	 * IN THE METHOD
	 * @param onlineTest
	 * @param templateDtl
	 * @return
	 */
	private Object[] getQuesCodes(OnlineTest onlineTest,Object[][] templateDtl) {
		
		Object[][]quesCodeData = null;
		Object[]totalQuesCode =null;
		try {
			totalQuesCode = new Object[Integer.parseInt(onlineTest
					.getTempTotalQues())];
			int count = 0;
			for (int i = 0; i < templateDtl.length; i++) {//this loop for no of subjects loop (1)

				/**
				 * this query to retrieve the no of question for the particular question code
				 */

				String query = "SELECT QUES_CODE,QUES_NAME FROM HRMS_REC_QUESBANK "
						+ " WHERE  QUES_STATUS = 'A' "
						+ " AND QUES_LEVEL = '"
						+ templateDtl[i][3] + "' ";
				if (!String.valueOf(templateDtl[i][2]).equals("null")) {
					query = query + " AND QUES_CAT_CODE=" + templateDtl[i][2];
				} 
				if (!String.valueOf(templateDtl[i][1]).equals("null")) {
					query = query + " AND QUES_SUB_CODE=" + templateDtl[i][1];
				} 
				if (onlineTest.getTestType().equals("B")) { //checked for both whether subjective or objective
					query = query + " AND QUES_TYPE IN ('S','O')";
				} 
				else { 
					query = query + " AND QUES_TYPE ='"
							+ onlineTest.getTestType() + "'";
				}
				query = query + " ORDER BY dbms_random.value";

				quesCodeData = getSqlModel().getSingleResult(query);

				if (quesCodeData != null && quesCodeData.length > 0) {
					/**
					 * this loop for copying the question codes for particular subject into a object
					 * Integer.parseInt(String.valueOf(templateDtl[i][4])==CONTAINS HOW MUCH QUESTIONS SHOULD BE
					 * THERE IN THE TEST FOR THE PARTICULAR SUBJECT...
					 */
					for (int j = 0; j < Integer.parseInt(String
							.valueOf(templateDtl[i][4])); j++) {

						totalQuesCode[count++] = quesCodeData[j][0];
					} 
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalQuesCode;
	}//end of method

	/**
	 * this method is called to get the data from the HRMS_REC_TESTTEMPDTL to retrieve the 
	 * subject,category code
	 * @param onlineTest
	 * @return
	 */
	private Object[][] getTemplateDtl(OnlineTest onlineTest) {
		Object[][]templateDtl = null;
		try {
			String query = "SELECT TEMPLATE_DTLCODE,TEMPLATE_SUBCODE,TEMPLATE_CATCODE,TEMPLATE_COM_LEVEL,TEMPLATE_NOOFQUES "
					+ " FROM HRMS_REC_TESTTEMPDTL WHERE TEMPLATE_CODE = "
					+ onlineTest.getTestTemplateCode() + "";
			templateDtl = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateDtl;
	}

	/**
	 * THIS METHOD IS CALLED WHEN THE TEST BEGINS...
	 * @param onlineTest
	 * @param request
	 */
	public void startTest(OnlineTest onlineTest, HttpServletRequest request) {
		
		try {
			String randomQuesCodes[] = request.getParameterValues("randomQuesCodes");
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			String subjAnsForPrevious = "";
			String answerUploadedDoc = "";
			subjAnsForPrevious = onlineTest.getSubjectAns();
			answerUploadedDoc = onlineTest.getAnswerUploadedDoc();
			int score = 0;
			Object[][]scoreDtl = new Object[1][2];
			String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
			String answerCorrect = request.getParameter("finalAnswer");//this is answer whether correct or not
			int sequence = 1;
			/**
			 * this if condition is checked on start...initially sequence is set 1
			 */
			if (request.getParameter("sequenceCode") == null
					|| request.getParameter("sequenceCode").equals("")
					|| request.getParameter("sequenceCode").equals("null")) {
				sequence = 1;
				onlineTest.setSequenceCode(String.valueOf(sequence));
			} 
			/**
			 * here in else sequence is added by 1 
			 */
			else {
				onlineTest.setPreviousButton("true");
				sequence = Integer.parseInt(request
						.getParameter("sequenceCode")) + 1;
				//onlineTest.setSubjectAns("");
				onlineTest.setSequenceCode(String.valueOf(sequence));
			} //end of else
			/**
			 * this to check if the data for the sequence is present or not. Here sequence code , candidate code
			 * and requisition code is passed....if it is present than no Insert query is fired...
			 * Only Update and the following data is retrieved.... 
			 */
			Object[][] sequenceData = null;
			int candidateCode = Integer.parseInt(onlineTest.getCandidateUserEmpId());  
			int requisitionCode = Integer.parseInt(onlineTest.getReqCode());  
			String templateCode = onlineTest.getTemplateCode();
			sequenceData = getQuesFromSequence(sequence, candidateCode, requisitionCode, templateCode);
			/**
			 * here is sequenceData is not null dat means the Next button is pressed after previous is 
			 * clicked first
			 */
			if (sequenceData != null && sequenceData.length > 0) {
				onlineTest.setQuestionName(String.valueOf(sequenceData[0][1]));//QUESTION NAME
				onlineTest.setQuestionLevel(String.valueOf(sequenceData[0][4]));//QUESTION COMPLEXITY LEVEL..
				onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
				onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
				onlineTest.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
				if (String.valueOf(sequenceData[0][2]).equals("S")) {
					if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
						onlineTest.setDocumentNotAttachedFlag(true);
						onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(sequenceData[0][7])));
					}
					onlineTest.setAnswerUploadedDoc(checkNull(String.valueOf(sequenceData[0][8])));
				}
				 
					getQuesOption(sequenceData, onlineTest,request);
				if (onlineTest.getLastQuestionSequence().equals("" + sequence)) {
					onlineTest.setSubmitButtonFlag("true");//end of test
					onlineTest.setNextButtonFlag("false"); //no more questions for the test
				}
				
				String query = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), " 
					+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(HRMS_REC_ONLINETEST.ANSWER_UPLOADED_DOC,''), " 
					+ " HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
					+ " FROM HRMS_REC_ONLINETEST "
					+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = HRMS_REC_ONLINETEST.ONLINE_QUES_CODE) "
					+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
					+ " INNER JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
					+ " WHERE ONLINE_SEQS = " + (sequence - 1) 
					+ " AND ONLINE_CAND_CODE = " + onlineTest.getCandidateUserEmpId() 
					+ " AND ONLINE_REQS_CODE = " + requisitionCode 
					+ " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = "+templateCode;
				sequenceData = getSqlModel().getSingleResult(query);
				
				if(sequenceData != null && sequenceData.length > 0) {
					onlineTest.setQuestionLevel(String.valueOf(sequenceData[0][4]));//QUESTION COMPLEXITY LEVEL..
					onlineTest.setSubjectAns(checkNull(String.valueOf(sequenceData[0][5])));
					onlineTest.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
					if (String.valueOf(sequenceData[0][2]).equals("S")) {
						if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
							onlineTest.setDocumentNotAttachedFlag(true);
							onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(sequenceData[0][7])));
						}
						onlineTest.setAnswerUploadedDoc(checkNull(String.valueOf(sequenceData[0][8])));
					}
					onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
					onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
					
				}
				/**
				 * THIS IS TO CHECK WHETHER ANSWER IS GIVEN OR NOT...IF YES WHETHER THE ANSWER IS COORECT OR NOT.
				 * IF THE ANSWER IS WRONG THEN "MARKS FOR NO ANSWER" IS SET..IF "answerCorrect" IS BLANK THEN
				 * "MARKS FOR NO ANSWER" IS SET IN "SCORE"
				 */
				if(onlineTest.getMarksForCorrect().equals("N")){
				/**
				 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE FOR EVERY RIGHT ANSWER
				 */
				
					/**
					 * THIS IS FOR QUESTIONS HAVE EQUAL WEIGHTAGE....
					 */
					scoreDtl = getScoreEqWtSubmit(onlineTest,answerCode,answerCorrect);
				} else{
					/**
					 *THIS IS FOR QUESTION MARKS BASED UPON THEIR COMPLEXITY LEVEL..
					 */
					scoreDtl = getScoreForCorrectSubmit(onlineTest,answerCode,answerCorrect,sequence);
				} 
					
					String updatePreviousQues = "UPDATE HRMS_REC_ONLINETEST SET ONLINE_ANSWER='"+answerCode+"', ONLINE_CORRECT='" + scoreDtl[0][1] + "', " +
												" ONLINE_SCORE=" + scoreDtl[0][0]+ ", " +
												" ONLINE_SUBJ_ANSWER = '"+subjAnsForPrevious+"', " +
												" HRMS_REC_ONLINETEST.ANSWER_UPLOADED_DOC = '" + answerUploadedDoc + "'" + 
												" WHERE ONLINE_CAND_CODE = "+onlineTest.getCandidateUserEmpId()+
												" AND ONLINE_REQS_CODE = "+requisitionCode+
												" AND ONLINE_SEQS = "+(sequence-1)+
												" AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = "+templateCode;
					boolean update = getSqlModel().singleExecute(updatePreviousQues);
					
					String query1 = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), " 
						+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(HRMS_REC_ONLINETEST.ANSWER_UPLOADED_DOC,''), " 
						+" HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
						+ " FROM HRMS_REC_ONLINETEST "
						+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = HRMS_REC_ONLINETEST.ONLINE_QUES_CODE) "
						+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
						+ " INNER JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
						+ " WHERE ONLINE_SEQS = " + (sequence) 
						+ " AND ONLINE_CAND_CODE = " + onlineTest.getCandidateUserEmpId() 
						+ " AND ONLINE_REQS_CODE = " + requisitionCode 
						+ " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = " + templateCode;
					sequenceData = getSqlModel().getSingleResult(query1);
					
					if (sequenceData != null && sequenceData.length > 0) {
						onlineTest.setQuestionLevel(String.valueOf(sequenceData[0][4]));//QUESTION COMPLEXITY LEVEL..
						onlineTest.setSubjectAns(checkNull(String.valueOf(sequenceData[0][5])));
						onlineTest.setSubjAnswerLimit(String.valueOf(sequenceData[0][6]));
						if (String.valueOf(sequenceData[0][2]).equals("S")) {
							if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
								onlineTest.setDocumentNotAttachedFlag(true);
								onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(sequenceData[0][7])));
							}
							onlineTest.setAnswerUploadedDoc(checkNull(String.valueOf(sequenceData[0][8])));
						}
						
						onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
						onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
						
					}
				/**
				 * HERE THE ARRAY LIST IS SET AGAIN WHEN THE NEXT BUTTON IS CLICKED FOR THE RECORD WHICH HAS
				 * APPEARED BEFORE....
				 */
				if (randomQuesCodes != null && randomQuesCodes.length > 0) {
					setRandomCodeList(onlineTest, request);
				} 
			}  
			/**
			 * this else is called when Next button is pressed for the fresh new Question..
			 */
			else {
				subjAnsForPrevious = onlineTest.getSubjectAns();
				onlineTest.setSubjectAns("");
				onlineTest.setAnswerUploadedDoc("");
				/**
				 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE FOR EVERY RIGHT ANSWER
				 * HERE SCORE IS CALCULATED IN THE getScoreDtlEqWtage METHOD...
				 * HERE scoreDtl[0][0]==SCORE AND scoreDtl[0][1]==ANSWER CORRECT YES OR NO
				 */
				if(onlineTest.getMarksForCorrect().equals("N")){
					/**
					 * THIS IS CALLED WHEN EVERY QUESTION HAS EQUAL WEIGHTAGE 
					 */
					scoreDtl = getScoreDtlEqWtage(onlineTest,answerCode,answerCorrect,sequence);
				}
				else{
					/**
					 * THIS IS CALLED WHEN THE QUESTION NAMRKS DEPENDS UPON THE COMPLEXITY LEVEL..
					 */
					scoreDtl = getScoreForCorrect(onlineTest,answerCode,answerCorrect,sequence);
				}
				
				/**
				 * THIS IS TO CHECK WHETHER ANSWER IS GIVEN OR NOT...IF YES WHETHER THE ANSWER IS COORECT OR NOT.
				 * IF THE ANSWER IS WRONG THEN "MARKS FOR NO ANSWER" IS SET..IF "answerCorrect" IS BLANK THEN
				 * "MARKS FOR NO ANSWER" IS SET IN "SCORE"
				 */
				
				if(sequence > 1){
					String updatePreviousQues = "UPDATE HRMS_REC_ONLINETEST SET ONLINE_ANSWER='"+answerCode+"',ONLINE_CORRECT='"+scoreDtl[0][1]+"', " +
												" ONLINE_SCORE="+scoreDtl[0][0]+",ONLINE_SUBJ_ANSWER = '"+subjAnsForPrevious+"', " +
												" HRMS_REC_ONLINETEST.ANSWER_UPLOADED_DOC = '" + answerUploadedDoc + "' " + 	
												" WHERE ONLINE_CAND_CODE = "+onlineTest.getCandidateUserEmpId()+
												" AND ONLINE_REQS_CODE = "+requisitionCode+
												" AND ONLINE_SEQS = "+(sequence - 1)+
												" AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = " + templateCode;
					boolean update = getSqlModel().singleExecute(updatePreviousQues);
				}
				
					getFreshNext(onlineTest, request, sequence, templateCode);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end of method

	/**
	 * THIS METHOD called when Next button is pressed for the fresh new Question..
	 * @param onlineTest
	 * @param request
	 * @param sequence
	 * @param templateCode 
	 */
	private void getFreshNext(OnlineTest onlineTest, HttpServletRequest request, int sequence, String templateCode) {
		try {
			String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
			String randomQuesCodes[] = request.getParameterValues("randomQuesCodes");
			String questionCode = "";
			Object[][] question = null;
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			/**
			 * 	THIS IF IS CHECKED IF THE ARRAY OF randomQuesCodes IS NOT NULL
			 */
			if (randomQuesCodes != null && randomQuesCodes.length > 0) {
				questionCode = randomQuesCodes[0];
					/**
					 * TO RETRIEVE THE QUESTION DETAIL BY PASSING THE randomQuesCode
					 */
					question = getQuestion(randomQuesCodes);
				if (randomQuesCodes.length > 1) {// this if is check if the questions present is more than 1
						getQuesOptionForFreshNext(onlineTest, question, randomQuesCodes, sequence, questionCode, request, templateCode);
				} else {  
					onlineTest.setSubmitButtonFlag("true");//end of test
					onlineTest.setNextButtonFlag("false"); //no more questions for the test
					onlineTest.setLastQuestionSequence(String.valueOf(sequence));//this is used in startTest method
					getlastQuestion(onlineTest, question, randomQuesCodes, sequence, questionCode, request, templateCode);
				}  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end of method getFreshNext

	/**
	 * THIS METHOD IS CALLED WHEN THE LAST QUESTION OF THE TEST COMES....
	 * @param onlineTest
	 * @param question
	 * @param randomQuesCodes
	 * @param sequence
	 * @param questionCode 
	 * @param request 
	 * @param templateCode 
	 */
	private void getlastQuestion(OnlineTest onlineTest,Object[][] question,String[] randomQuesCodes, 
			int sequence, String questionCode, HttpServletRequest request, String templateCode) {
		try {
			String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
			String answerCorrect = request.getParameter("finalAnswer");//this is answer whether correct or not
			int score = 0;
			Object[][] scoreDtl = new Object[1][2];
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			if (question != null && question.length > 0) { //if 1
				onlineTest.setQuestionName(String.valueOf(question[0][0]));//question name
				onlineTest.setQuestionLevel(String.valueOf(question[0][4]));//question complexity level
				onlineTest.setSubject(checkNull(String.valueOf(question[0][7])));
				onlineTest.setCategory(checkNull(String.valueOf(question[0][8])));
				Object[][] questionOption = null;
				if (String.valueOf(question[0][1]).equals("O")) {
					/**
					 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
					 */
					questionOption = getquestionOption(questionCode);
					if (questionOption != null && questionOption.length > 0) {
						String[] checkbox = new String[questionOption.length];
						for (int i = 0; i < questionOption.length; i++) {
							checkbox[i] = "false";
							OnlineTest bean = new OnlineTest();
							bean.setOptionCode(String
									.valueOf(questionOption[i][0]));
							bean.setOptionName(String
									.valueOf(questionOption[i][1]));
							bean.setOptionAnswer(String
									.valueOf(questionOption[i][2]));
							optionTable.add(bean);
						} //end of loop
						onlineTest.setOptionList(optionTable);
						request.setAttribute("check", checkbox);
					} //end of if
				} //end of objective if
				else {
					onlineTest.setOptionFlag("false");
				} //end of else

				/**
				 * this insert query is fired on continue and next button...but is in case previous button is clicked
				 * and after that Next is clicked than this query is not fired....
				 */
					String insOnline = "INSERT INTO HRMS_REC_ONLINETEST (ONLINE_CAND_CODE,ONLINE_REQS_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS,ONLINE_TEMPLATE_ID) "
							+ " VALUES("
							+ onlineTest.getCandidateUserEmpId()
							+ ","
							+ onlineTest.getReqCode()
							+ ","
							+ randomQuesCodes[0] + "," + sequence + "," + templateCode +  ")";
					boolean result = getSqlModel().singleExecute(insOnline);
			} 
			onlineTest.setOptionList(optionTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end of method getlastQuestion

	/**
	 * THIS METHOD IS CALLED WHEN THE NEXT BUTTON IS CLICKED...BUT ONLY WHEN THE 
	 * randomQuesCodes LENGTH IS GREATER THAN 1...
	 * @param onlineTest
	 * @param question
	 * @param randomQuesCodes
	 * @param sequence
	 * @param questionCode 
	 * @param request 
	 * @param templateCode 
	 */
	private void getQuesOptionForFreshNext(OnlineTest onlineTest,Object[][] question, String[] randomQuesCodes, 
			int sequence, String questionCode, HttpServletRequest request, String templateCode){
		try {
			ArrayList<Object> optionTable = new ArrayList<Object>();
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			int score = 0;
			Object[][] scoreDtl = new OnlineTestModel[1][2];
			String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
			String answerCorrect = request.getParameter("finalAnswer");//this is answer whether correct or not
			if (question != null && question.length > 0) { //if 1
				onlineTest.setQuestionName(String.valueOf(question[0][0]));//question name
				onlineTest.setQuestionLevel(String.valueOf(question[0][4]));//question level...
				onlineTest.setSubject(checkNull(String.valueOf(question[0][7])));
				onlineTest.setCategory(checkNull(String.valueOf(question[0][8])));
				
				Object[][] questionOption = null;

				if (String.valueOf(question[0][1]).equals("O")) {
					onlineTest.setOptionFlag("true");
					/**
					 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
					 */
					questionOption = getquestionOption(questionCode);
					String[] checkbox = new String[questionOption.length];
					if (questionOption != null && questionOption.length > 0) {
						for (int i = 0; i < questionOption.length; i++) {
							checkbox[i] = "false";
							OnlineTest bean = new OnlineTest();
							bean.setOptionCode(String.valueOf(questionOption[i][0]));
							bean.setOptionName(String.valueOf(questionOption[i][1]));
							bean.setOptionAnswer(String.valueOf(questionOption[i][2]));
							optionTable.add(bean);
						} //end of loop
						onlineTest.setOptionList(optionTable);
						request.setAttribute("check", checkbox);
					} 
				} else {
					onlineTest.setOptionFlag("false");
					onlineTest.setSubjAnswerLimit(String.valueOf(question[0][5]));
					if (!checkNull(String.valueOf(question[0][6])).equals("")) {
						onlineTest.setDocumentNotAttachedFlag(true);
						onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(question[0][6])));
					}
				}  

				/**
				 * this insert query is fired on continue and next button...but is in case previous button is clicked
				 * and after that Next is clicked than this query is not fired....
				 */
					String insOnline = "INSERT INTO HRMS_REC_ONLINETEST (ONLINE_CAND_CODE,ONLINE_REQS_CODE,ONLINE_QUES_CODE, "
							+ " ONLINE_SEQS, ONLINE_TEMPLATE_ID) "
							+ " VALUES("
							+ onlineTest.getCandidateUserEmpId()
							+ ","
							+ onlineTest.getReqCode()
							+ ","
							+ randomQuesCodes[0] + "," + sequence + "," + templateCode + ")";
					boolean result = getSqlModel().singleExecute(insOnline);
				/**
				 * Here in this for loop i=1 bcoz to remove the 0th index value frm the array list... 
				 */
				for (int i = 1; i < randomQuesCodes.length; i++) {
					OnlineTest bean = new OnlineTest();
					bean.setRandomQuesCodes(randomQuesCodes[i]);
					randomCodesList.add(bean);
				}  
				onlineTest.setRandomQueList(randomCodesList);
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //END OF METHOD getQuesOptionForFreshNext
	
	/**
	 * THIS METHOD TO GET HE SCORE IF MARKS FOR CORRECT ANSWER OPTION IS TICK.....
	 * @param onlineTest
	 * @param answerCode
	 * @param answerCorrect
	 * @param sequence
	 * @return
	 */
	private Object[][] getScoreForCorrect(OnlineTest onlineTest,String answerCode, 
			String answerCorrect, int sequence) {
		int score = 0;
		Object[][]scoreDtl = new Object[1][2];
		if(sequence > 1){ //if 2
			if(answerCode ==null ||answerCode.equals("")){
				score = Integer.parseInt(onlineTest.getMarksNoAns());
				answerCorrect = "";
			} else {
				if(!(answerCorrect ==null||answerCorrect.equals(""))){ //if 1
					if(answerCorrect=="N"||answerCorrect.equals("N")){
						/*Begin Gauri*/
						//score = Integer.parseInt(onlineTest.getMarksWrongAns());
						if(onlineTest.getQuestionLevel().equals("E")){
							score = Integer.parseInt(onlineTest.getWrongmarksEasy());
						} else if(onlineTest.getQuestionLevel().equals("M")){
							score = Integer.parseInt(onlineTest.getWrongmarksMedium());
						}  else if(onlineTest.getQuestionLevel().equals("H")){
							score = Integer.parseInt(onlineTest.getWrongmarksHard());
						}
					}  
					/**
					 * THIS ELSE IF FOR IF ANSWER IS GIVEN...THEN HERE IT WILL CHECK THE COMPLEXITY LEVEL
					 * IF THE LEVEL IS "HARD" THEN FOR THE RESPECTIVE QUESTION HARD MARKS WILL BE GIVEN
					 * SAME CASES IN MEDIUM AND EASY TYPE LEVELS....
					 */
					else{
						if(onlineTest.getQuestionLevel().equals("E")){
							score = Integer.parseInt(onlineTest.getMarksEasy());
						} else if(onlineTest.getQuestionLevel().equals("M")){
							score = Integer.parseInt(onlineTest.getMarksMedium());
						} else if(onlineTest.getQuestionLevel().equals("H")){
							score = Integer.parseInt(onlineTest.getMarksHard());
						} 
					} 
				} 
			} 
		}  
		
		scoreDtl[0][0] = score;
		scoreDtl[0][1] = answerCorrect;
		
		return scoreDtl;
	} //end of getScoreForCorrect method..
	
	private Object[][]getScoreForCorrectSubmit(OnlineTest onlineTest,String answerCode, 
			String answerCorrect, int sequence) {
		int score = 0;
		Object[][]scoreDtl = new Object[1][2];
		try {
			if (answerCode == null || answerCode.equals("")) {
				score = Integer.parseInt(onlineTest.getMarksNoAns());
				answerCorrect = "";
			} else {
				if (!(answerCorrect == null || answerCorrect.equals(""))) { //if 1
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
					 * THIS ELSE IF FOR IF ANSWER IS GIVEN...THEN HERE IT WILL CHECK THE COMPLEXITY LEVEL
					 * IF THE LEVEL IS "HARD" THEN FOR THE RESPECTIVE QUESTION HARD MARKS WILL BE GIVEN
					 * SAME CASES IN MEDIUM AND EASY TYPE LEVELS....
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
	 * THIS METHOD TO GET THE SCORE DETAILS WHICH WILL BE USED IN THE UPDATE QUERY...IT RETURNS THE 
	 * "SCORE" AND "ANSWER CORRECT"= "Y" OR "N" OR " "...
	 * @param onlineTest
	 * @param answerCode
	 * @param answerCorrect
	 * @param sequence 
	 * @return
	 */
	private Object[][] getScoreDtlEqWtage(OnlineTest onlineTest, String answerCode,
			String answerCorrect, int sequence) {
		Object[][]scoreDtl = new Object[1][2];
		try {
			int score = 0;
			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE FOR EVERY RIGHT ANSWER
			 */
			if (onlineTest.getMarksForCorrect().equals("N")) {
				score = Integer.parseInt(onlineTest.getEqualMarksForAll());
			}
			if (sequence > 1) {
				if (answerCode == null || answerCode.equals("")) {
					score = Integer.parseInt(onlineTest.getMarksNoAns());
				}

				if (answerCode == null || answerCode.equals("")) {
					score = Integer.parseInt(onlineTest.getMarksNoAns());
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

	/**
	 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
	 * @param randomQuesCodes
	 * @return
	 */
	private Object[][] getquestionOption(String randomQuesCodes) {
		Object[][]questionOption = null;
		try {
			String sqlQuery = "SELECT OPTION_CODE,OPTION_DESC,OPTION_ANS_FLAG "
					+ " FROM HRMS_REC_QUESOPTION WHERE QUES_CODE = "
					+ randomQuesCodes+ "  ";
			questionOption = getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionOption;
	} //END OF METHOD

	/**
	 * TO RETRIEVE THE QUESTION DETAIL BY PASSING THE QUESTION CODES
	 * @param randomQuesCodes
	 * @return
	 */
	private Object[][] getQuestion(String[] randomQuesCodes) {
		Object[][]question = null;
		try {
			String query = "SELECT REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,QUES_MARK,QUES_LIMIT,QUES_LEVEL,NVL(QUES_LIMIT,0),NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME  "
						+" FROM HRMS_REC_QUESBANK "  
						+" INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE) "  
						+" INNER JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "	
						+" WHERE QUES_CODE = "+ randomQuesCodes[0] +"";
			question = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	} //END OF METHOD

	/**
	 * THIS METHOD IS CALLED WHEN THE PREVIOUS BUTTON IS CLICKED..
	 * @param onlineTest
	 * @param request
	 */
	public void getPrevious(OnlineTest onlineTest, HttpServletRequest request) {
		try {
			ArrayList<Object> optionTable = new ArrayList<Object>();
			/**
			 * HERE SEQUENCE IS MINUS TO GET THE PREVIOUS TEST QUESTION TO UPDATE OR TO PREVIEW...
			 */
			int sequence = Integer.parseInt(onlineTest.getSequenceCode()) - 1;
			onlineTest.setSequenceCode(String.valueOf(sequence));
			Object[][] sequenceData = null;
			String templateCode = onlineTest.getTemplateCode();
			int candidateCode = Integer.parseInt(onlineTest.getCandidateUserEmpId()); // IT IS HARDCODED NOW....
			int requisitionCode = Integer.parseInt(onlineTest.getReqCode()); // IT IS ALSO HARDOCED NOW....
				sequenceData = getQuesFromSequence(sequence, candidateCode, requisitionCode, templateCode);
			if (sequenceData != null && sequenceData.length > 0) {
				onlineTest.setQuestionName(String.valueOf(sequenceData[0][1]));//QUESTION NAME
				onlineTest.setQuestionLevel(String.valueOf(sequenceData[0][4]));//QUESTION COMPLEXITY LEVEL
				onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
				onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
				onlineTest.setSubjAnswerLimit(checkNull(String.valueOf(sequenceData[0][6])));
				
				if (String.valueOf(sequenceData[0][2]).equals("S")) {
					if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
						onlineTest.setDocumentNotAttachedFlag(true);
						onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(sequenceData[0][7])));
					}
					onlineTest.setAnswerUploadedDoc(checkNull(String.valueOf(sequenceData[0][8])));
				}
					getQuesOption(sequenceData, onlineTest, request);
			} 
			String randomQuesCodes[] = request.getParameterValues("randomQuesCodes");
			/**
			 * HERE THE ARRAY LIST IS SET AGAIN WHEN THE PREVIOUS BUTTON IS CLICKED....
			 */
			if (randomQuesCodes != null && randomQuesCodes.length > 0) {
				setRandomCodeList(onlineTest, request);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

	/**
	 * this method is called to retrieve the QUESTION OPTION. HERE DATA IS RETRIEVED BY PASSING THE
	 * QUESTION CODE FROM THE HRMS_REC_ONLINETEST TABLE...
	 * @param sequenceData
	 * @param onlineTest
	 * @param request 
	 */
	private void getQuesOption(Object[][] sequenceData, OnlineTest onlineTest, HttpServletRequest request) {
		try {
			Object[][] questionOption = null;
			ArrayList<Object> optionTable = new ArrayList<Object>();
			/**
			 * THIS IF CODITION IS CHECKED WHETHER THE QUESTION TYPE IS OBJECTIVE...
			 */
			if (String.valueOf(sequenceData[0][2]).equals("O")) {
				/**
				 * THIS METHOD TO RETRIEVE THE QUESTION OPTION
				 */
				onlineTest.setOptionFlag("true");
				questionOption = getquestionOption(String
						.valueOf(sequenceData[0][0]));
				String[] checkbox = new String[questionOption.length];
				if (questionOption != null && questionOption.length > 0) {

					for (int i = 0; i < questionOption.length; i++) {
						OnlineTest bean = new OnlineTest();
						String[] answerCode = String
								.valueOf(sequenceData[0][3]).split(",");
						checkbox[i] = "false";
						for (int j = 0; j < answerCode.length; j++) {
							if (String.valueOf(questionOption[i][0]).equals(
									answerCode[j])) {
								checkbox[i] = "true";
							}
						}
						bean.setOptionCode(String.valueOf(questionOption[i][0]));
						bean.setOptionName(String.valueOf(questionOption[i][1]));
						bean.setOptionAnswer(String.valueOf(questionOption[i][2]));
						optionTable.add(bean);
					}
					onlineTest.setOptionList(optionTable);
					request.setAttribute("check", checkbox);
				}
			} else {
				onlineTest.setSubjectAns(checkNull(String.valueOf(sequenceData[0][5])));
				onlineTest.setSubjAnswerLimit(checkNull(String.valueOf(sequenceData[0][6])));
				if (!checkNull(String.valueOf(sequenceData[0][7])).equals("")) {
					onlineTest.setDocumentNotAttachedFlag(true);
					onlineTest.setQuestionUploadedDoc(checkNull(String.valueOf(sequenceData[0][7])));
				}
				onlineTest.setAnswerUploadedDoc(checkNull(String.valueOf(sequenceData[0][8])));
				onlineTest.setSubject(checkNull(String.valueOf(sequenceData[0][9])));
				onlineTest.setCategory(checkNull(String.valueOf(sequenceData[0][10])));
				onlineTest.setOptionFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}  

	/**
	 * THIS METHOD IS CALLED TO RETRIEVE THE DATA ON PREVIOUS AND NEXT BUTTONS...
	 * HERE SEQUENCE CODE, CANDIDATE AND REQUISITION CODE ARE PASSED
	 * @param sequence
	 * @param candidateCode
	 * @param requisitionCode
	 * @param templateCode 
	 * @return
	 */
	private Object[][] getQuesFromSequence(int sequence, int candidateCode, int requisitionCode, String templateCode) {
		Object[][]sequenceData = null;
		try {
			String query = "SELECT ONLINE_QUES_CODE,REPLACE(QUES_NAME,'\\n','<br>'),QUES_TYPE,ONLINE_ANSWER,QUES_LEVEL,ONLINE_SUBJ_ANSWER,NVL(QUES_LIMIT,0), " 
				+ " NVL(HRMS_REC_QUESBANK.QUES_UPLOADED_DOC,''), NVL(HRMS_REC_ONLINETEST.ANSWER_UPLOADED_DOC,''), " 
				+" HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME "
				+ " FROM HRMS_REC_ONLINETEST "
				+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = HRMS_REC_ONLINETEST.ONLINE_QUES_CODE) "
				+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
				+ " INNER JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
				+" WHERE HRMS_REC_ONLINETEST.ONLINE_SEQS = " + sequence + 
				" AND HRMS_REC_ONLINETEST.ONLINE_CAND_CODE = " + candidateCode + 
				" AND HRMS_REC_ONLINETEST.ONLINE_REQS_CODE = "+requisitionCode+
				" AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID ="+ templateCode;
			sequenceData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequenceData;
	} //END OF METHOD

	private void setRandomCodeList(OnlineTest onlineTest,HttpServletRequest request) {
		try {
			ArrayList<Object> randomCodesList = new ArrayList<Object>();
			String randomQuesCodes[] = request
					.getParameterValues("randomQuesCodes");
			for (int i = 0; i < randomQuesCodes.length; i++) {
				OnlineTest bean = new OnlineTest();
				bean.setRandomQuesCodes(randomQuesCodes[i]);
				randomCodesList.add(bean);
			}
			onlineTest.setRandomQueList(randomCodesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * THIS METHOD IS CALLED WHEN THE SUBNIT BUTTON IS CLICKED...
	 * @param onlineTest
	 * @param request
	 */
	public void submit(OnlineTest onlineTest, HttpServletRequest request) {
		try {
			String result = "";
			int score = 0;
			Object[][] scoreDtl = new Object[1][2];
			String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
			String answerCorrect = request.getParameter("finalAnswer");//this is answer whether correct or not
			int sequence = Integer.parseInt(onlineTest.getSequenceCode());
			String updatePreviousQues = "";
			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE FOR EVERY RIGHT ANSWER
			 */
			if (onlineTest.getMarksForCorrect().equals("N")) {
				/**
				 * THIS IS FOR QUESTIONS HAVE EQUAL WEIGHTAGE....
				 */
				scoreDtl = getScoreEqWtSubmit(onlineTest, answerCode,
						answerCorrect);
			} else {
				/**
				 *THIS IS FOR QUESTION MARKS BASED UPON THEIR COMPLEXITY LEVEL..
				 */
				scoreDtl = getScoreForCorrectSubmit(onlineTest, answerCode,
						answerCorrect, sequence);
			}
			if (sequence == 1) {
				updatePreviousQues = "UPDATE HRMS_REC_ONLINETEST SET ONLINE_ANSWER='" + answerCode + "', " +
									 " ONLINE_CORRECT='" + scoreDtl[0][1] + "',ONLINE_SCORE=" + scoreDtl[0][0] + ", " +
									 " ONLINE_SUBJ_ANSWER = '" + onlineTest.getSubjectAns()	+ "', " +
									 " ANSWER_UPLOADED_DOC = '" + onlineTest.getAnswerUploadedDoc()	+ "' " +
									 " WHERE ONLINE_CAND_CODE = " + onlineTest.getCandidateUserEmpId() + 
									 " AND ONLINE_REQS_CODE = "	+ onlineTest.getReqCode() + 
									 " AND ONLINE_SEQS = " + (sequence) + 
									 " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = "+ onlineTest.getTemplateCode();
			} else {
				String sequenceCodeForUpdate = "";
				if (onlineTest.getLastQuestionSequence().equals("") || onlineTest.getLastQuestionSequence().equals("null")) {
					sequenceCodeForUpdate = onlineTest.getSequenceCode();
				} else {
					sequenceCodeForUpdate = onlineTest.getLastQuestionSequence();
				}
				updatePreviousQues = "UPDATE HRMS_REC_ONLINETEST SET ONLINE_ANSWER='" + answerCode + "', " +
									" ONLINE_CORRECT='"	+ scoreDtl[0][1] + "',ONLINE_SCORE=" + scoreDtl[0][0] + ", " +
									" ONLINE_SUBJ_ANSWER = '" + onlineTest.getSubjectAns() + "', " +
									" ANSWER_UPLOADED_DOC = '" + onlineTest.getAnswerUploadedDoc()	+ "' " +
									" WHERE ONLINE_CAND_CODE = " + onlineTest.getCandidateUserEmpId() + 
									" AND ONLINE_REQS_CODE = " + onlineTest.getReqCode() + 
									" AND ONLINE_SEQS = " + sequenceCodeForUpdate + 
									" AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = "+ onlineTest.getTemplateCode();
			}
			boolean update = getSqlModel().singleExecute(updatePreviousQues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end of submit method

	/**
	 * THIS METHOD IS CALLED FOR UPDATING RECORD...AND ON SUBMIT AND IN 
	 * START TEST WHEN ON PREVIOUS AND NEXT BUTTONS ARE CLICKED... 
	 * @param onlineTest
	 * @param answerCode
	 * @param answerCorrect
	 * @return
	 */
	private Object[][] getScoreEqWtSubmit(OnlineTest onlineTest,
			String answerCode, String answerCorrect) {
		int score=0;
		Object[][] scoreDtl = new Object[1][2];
		try {
			/**
			 * THIS IF CONDITION IS FOR EQUAL WEIGHTAGE MARKS.... I.E SAME SCORE FOR EVERY RIGHT ANSWER
			 */
			if (onlineTest.getMarksForCorrect().equals("N")) {
				score = Integer.parseInt(onlineTest.getEqualMarksForAll());
			}
			if (answerCode == null || answerCode.equals("")) {
				score = Integer.parseInt(onlineTest.getMarksNoAns());
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
	} //end of getScoreEqWtSubmit method 

	/**
	 *THIS METHOD IS CALLED ONLY IF ONLINE SCORE TO BE SHOWN IS "YES"...HERE RESULT,PASSING MARKS,
	 *MARKS OBTAINED,PERCENTAGE ARE DISPLAYED...
	 * @param onlineTest
	 * @param request
	 */
	public void getTestResult(OnlineTest onlineTest, HttpServletRequest request) {
		try {
			int marksObtained = 0;
			int totalMarks = 0;
				Object[][] result = null;

				int percentage = 0;
				int correctMarks = 0, negativeMarks = 0, blankMarks = 0;
					String query = "SELECT NVL(HRMS_REC_ONLINETEST.ONLINE_SCORE,0), HRMS_REC_ONLINETEST.ONLINE_CORRECT "
							+ " FROM HRMS_REC_ONLINETEST "
							+ " WHERE HRMS_REC_ONLINETEST.ONLINE_CAND_CODE  =" + onlineTest.getCandidateUserEmpId()
							+ " AND HRMS_REC_ONLINETEST.ONLINE_REQS_CODE = " + onlineTest.getReqCode() 
							+ " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = "+ onlineTest.getTemplateCode();
					result = getSqlModel().getSingleResult(query);
				if (result != null) { //if 1
						for (int i = 0; i < result.length; i++) {
							if (result[i][1] != null
									&& result[i][1].equals("Y")) {
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

					if (marksObtained >= Integer.parseInt(onlineTest
							.getPassingMarks())) {
						onlineTest.setResult("Pass");
						onlineTest.setMarksObtained(String
								.valueOf(marksObtained));
						totalMarks = Integer.parseInt(onlineTest
								.getTempTotalMarks());
						percentage = ((marksObtained * 100) / totalMarks);
						onlineTest.setPercentage(String.valueOf(percentage)
								+ "%");
					} else {
						if (marksObtained < 0) {
							onlineTest.setMarksObtained("0");
						} 
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
			 * here if the test type is "Objective" then only the records will get saved
			 * else if it is "Subjective" or "Both" the details will not be saved. 
			 */
			if (onlineTest.getTestType().equals("O")) {
				System.out.println("onlineTest.getTestType() ############## >>>>>>"+onlineTest.getTestType());
				Object[][] schTestDtlCode = null;
					String dtlCodeQuery = "SELECT TEST_DTL_CODE FROM HRMS_REC_SCHTEST_DTL "
							+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + onlineTest.getReqCode()
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTest.getCandidateUserEmpId()  
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CODE = " + onlineTest.getOnlineTestCode();
					schTestDtlCode = getSqlModel().getSingleResult(dtlCodeQuery);

					String insertTestResult = "INSERT INTO HRMS_REC_TESTRESULT (TEST_RESULT_CODE,TEST_REQS_CODE,TEST_CAND_CODE,TEST_TOTAL_MARKS, "
							+ " TEST_OBT_MARKS,TEST_RESULT,TEST_TYPE,TEST_DTL_CODE, TEST_OBJECTIVE_MARKS) "
							+ " VALUES((SELECT NVL(MAX(TEST_RESULT_CODE)+1,1) FROM HRMS_REC_TESTRESULT),"
							+ onlineTest.getReqCode() + ","
							+ onlineTest.getCandidateUserEmpId() + ","
							+ totalMarks + ", " + " "
							+ marksObtained + ",'" + finalResult + "','O'," + schTestDtlCode[0][0] + ","+marksObtained+")";
					getSqlModel().singleExecute(insertTestResult);
					//BOTH/SUBJECTIVE >>> Marks obtainded & final result >> NO
					 
					String updateTestSch = "UPDATE HRMS_REC_SCHTEST_DTL SET HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y' "
							+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + onlineTest.getReqCode()
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTest.getCandidateUserEmpId()  
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CODE = " + onlineTest.getOnlineTestCode();
					getSqlModel().singleExecute(updateTestSch);
			}  else {
					Object[][] schTestDtlCode = null;
						String dtlCodeQuery = "SELECT HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE FROM HRMS_REC_SCHTEST_DTL "
											+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + onlineTest.getReqCode()
											+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTest.getCandidateUserEmpId()
											+ " AND HRMS_REC_SCHTEST_DTL.TEST_CODE = " + onlineTest.getOnlineTestCode();
						schTestDtlCode = getSqlModel().getSingleResult(dtlCodeQuery);

						String insertTestResult = "INSERT INTO HRMS_REC_TESTRESULT (TEST_RESULT_CODE,TEST_REQS_CODE,TEST_CAND_CODE,TEST_TOTAL_MARKS, "
								+ " TEST_OBT_MARKS,TEST_RESULT,TEST_TYPE,TEST_DTL_CODE, TEST_OBJECTIVE_MARKS) "
								+ " VALUES((SELECT NVL(MAX(TEST_RESULT_CODE)+1,1) FROM HRMS_REC_TESTRESULT),"
								+ onlineTest.getReqCode() + "," + onlineTest.getCandidateUserEmpId() + ","
								+ totalMarks + ", " + " "
								+ 0 + ",'" + "" + "','"+onlineTest.getTestType()+"'," + schTestDtlCode[0][0] + ","+marksObtained+")";
						getSqlModel().singleExecute(insertTestResult);
						//BOTH/SUBJECTIVE >>> Marks obtainded & final result >> NO
						 
						String updateTestSch = "UPDATE HRMS_REC_SCHTEST_DTL SET HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y' "
								+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + onlineTest.getReqCode()
								+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTest.getCandidateUserEmpId() 
								+ " AND HRMS_REC_SCHTEST_DTL.TEST_CODE = " + onlineTest.getOnlineTestCode();
						getSqlModel().singleExecute(updateTestSch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end of getTestResult method 
	
	public void createMenu(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String query = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,MENU_PLACEINMENU,MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE )" 
						/*"AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"*/
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE =58"
				+ " AND MENU_PARENT_CODE=0"+" AND MENU_ISRELEASED='Y'"
				+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";

		String[][] strObj=null;
		Object[][] obj =  getSqlModel().getSingleResult(query);
		if(obj!=null){
		strObj=new String[obj.length][5];
		
		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);
			strObj[i][2] = String.valueOf(obj[i][2]);
			strObj[i][3] = String.valueOf(obj[i][3]);
			strObj[i][4] = String.valueOf(obj[i][4]);
							
		}
		}
		request.setAttribute("menuList", strObj);
	}
	
	
	public static int convertToMinutes(String result) {
		int min = 0;
		try {
			if (result == null || result.equals("null") || result.equals("")) {
				min = 0;
			} else {
				if (result.contains(":")) {
					String[] chk = result.split(":");
					min = Integer.parseInt(chk[0].trim()) * 60;
					min = min + Integer.parseInt(chk[1].trim());
				} else {
					min = Integer.parseInt(result.trim()) * 60;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return min;
	}

	/**
	 * @param request
	 * @param onlineTestBean 
	 */
	public void getScheduledTestList(HttpServletRequest request, OnlineTest onlineTestBean) {
		try {
			String scheduledTestQuery = "SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_REC_REQS_HDR.REQS_CODE, HRMS_REC_TEST_TEMPLATE.TEMPLATE_TEST_NAME, " + 
										" HRMS_REC_TEST_TEMPLATE.TEMPLATE_CODE, TO_CHAR(HRMS_REC_SCHTEST_DTL.TEST_DATE,'DD-MM-YYYY'), HRMS_REC_SCHTEST_DTL.TEST_TIME, HRMS_REC_SCHTEST_DTL.TEST_CODE " +
										" FROM HRMS_REC_SCHTEST_DTL " +
										" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) " +
										" INNER JOIN HRMS_REC_SCHTEST ON (HRMS_REC_SCHTEST.TEST_CODE = HRMS_REC_SCHTEST_DTL.TEST_CODE) " +
										" INNER JOIN HRMS_REC_TEST_TEMPLATE ON (HRMS_REC_TEST_TEMPLATE.TEMPLATE_CODE = HRMS_REC_SCHTEST.TEST_TEMPLATE) " + 
										" WHERE HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTestBean.getCandidateUserEmpId() + 
										" AND HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'N'";
			Object[][] scheduledTestObj = getSqlModel().getSingleResult(scheduledTestQuery);
			if(scheduledTestObj != null && scheduledTestObj.length > 0) {
				ArrayList<OnlineTest> innerList = new ArrayList<OnlineTest>();
				for (int i = 0; i < scheduledTestObj.length; i++) {
					OnlineTest innerBean = new OnlineTest();
					innerBean.setRequisitionTitleItr(checkNull(String.valueOf(scheduledTestObj[i][0])));
					innerBean.setRequisitionCodeItr(checkNull(String.valueOf(scheduledTestObj[i][1])));
					innerBean.setTestTemplateNameItr(checkNull(String.valueOf(scheduledTestObj[i][2])));
					innerBean.setTestTemplateCodeItr(checkNull(String.valueOf(scheduledTestObj[i][3])));
					innerBean.setTestDateItr(checkNull(String.valueOf(scheduledTestObj[i][4])));
					innerBean.setTestTimeItr(checkNull(String.valueOf(scheduledTestObj[i][5])));
					innerBean.setOnlineTestCodeItr(checkNull(String.valueOf(scheduledTestObj[i][6])));
					innerList.add(innerBean);
				}
				onlineTestBean.setScheduledTestList(innerList);
			} else {
				onlineTestBean.setScheduledDataFlag(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param request
	 * @param onlineTest
	 * @param testTime 
	 * @param testDate 
	 * @param testTemplate 
	 */
	public String getTestTemplateDetails(HttpServletRequest request, OnlineTest onlineTest, String testTime, String testDate, String testTemplate) {
		String result = "";
		try {
			String bufferQuery = "SELECT NVL(CONF_TEST_TIME,0) FROM HRMS_REC_CONF";
			Object[][] recConfigurationTime = getSqlModel().getSingleResult(bufferQuery);
		 
			String systemTimeQuery = "SELECT TO_CHAR(SYSDATE,'HH24:MI'), TO_CHAR(SYSDATE,'dd-mm-yyyy') FROM DUAL";
			Object[][] systemTimeObj = getSqlModel().getSingleResult(systemTimeQuery);
			int systemMinutes = 0;
			int testMinutes = 0;
			int testTimeFromRecruitmentSetting = 0;
			testMinutes = convertToMinutes(String.valueOf(testTime).trim());
			testTimeFromRecruitmentSetting = convertToMinutes(String.valueOf(recConfigurationTime[0][0]));
			
			if(systemTimeObj != null && systemTimeObj.length > 0) {
				systemMinutes = convertToMinutes(String.valueOf(systemTimeObj[0][0]));
			}
			 
			String testDateOriginal = String.valueOf(testDate).trim();
			String systemDateOriginal = String.valueOf(systemTimeObj[0][1]).trim();
			
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			//Date testDateConverted = sdf.parse(testDateOriginal);
			//Date systemDateConverted = sdf.parse(systemDateOriginal);
			
			
			Date testDateConverted = Utility.getDate(testDateOriginal);
			Date systemDateConverted = Utility.getDate(systemDateOriginal);
			 
			if (testDateConverted.before(systemDateConverted)) {
				result = "testDateExpire";
				return result;
			} else {
				if (systemMinutes < testMinutes) {
					result = "waitTillScheduledDateTime";
					return result;
				}

				if (!(String.valueOf(testDateConverted).equals(String.valueOf(systemDateConverted)))) {
					result = "waitTillScheduledDateTime";
					return result;
				}

				if ((systemMinutes - testMinutes) > testTimeFromRecruitmentSetting) {
					result = "testTimeExpire";
					return result;
				}
			}
			
			

			Object[][] templateHdr = null;
			ArrayList<Object> questionList = new ArrayList<Object>();
			/**
			 * THIS QUERY IS TO RETRIEVE DATA FROM THE TEMPLATE HDT TABLE....
			 */
				 
				String queryHdr = "SELECT TEMPLATE_TEST_NAME,TO_CHAR(TEMPLATE_DURATION, 'HH24:MI:SS'),TEMPLATE_TOTAL_MARKS,TEMPLATE_TOTAL_QUES, "
						+ " TEMPLATE_INSTRUCTION,TEMPLATE_TEST_TYPE,TEMPLATE_EQUAL_WEGHT,TEMPLATE_MARKS_WRONG,"
						+ " TEMPLATE_MARKS_NOANS,TEMPLATE_ONLINE_SCORE,TEMPLATE_PASSING_MARKS,TEMPLATE_MARKS_CORERCT,"
						+ "  NVL(TEMPLATE_MARKS_HARD,0),NVL(TEMPLATE_MARKS_MEDIUM,0), "
						+ " NVL(TEMPLATE_MARKS_EASY,0),NVL(TEMPLATE_WRONG_HARD,0),NVL(TEMPLATE_WRONG_MED,0),NVL(TEMPLATE_WRONG_EASY,0),TEMPLATE_CODE,TEMPLATE_EQUAL_MARKS  "
						+ " FROM HRMS_REC_TEST_TEMPLATE WHERE TEMPLATE_TEST_NAME = '" + testTemplate + "' ";
				templateHdr = getSqlModel().getSingleResult(queryHdr);
			
			if (templateHdr != null && templateHdr.length > 0) {
				onlineTest.setTempName(checkNull(String.valueOf(templateHdr[0][0])));//testname
				onlineTest.setTempDuration(checkNull(String.valueOf(templateHdr[0][1])));//test duration
				onlineTest.setTempTotalMarks(checkNull(String.valueOf(templateHdr[0][2])));//total marks
				onlineTest.setTempTotalQues(checkNull(String.valueOf(templateHdr[0][3])));//total questions
				onlineTest.setTempInstruction(checkNull(String.valueOf(templateHdr[0][4])));//test instruction
				onlineTest.setTestType(String.valueOf(templateHdr[0][5]));//test type  is hidden(whether Objective or subjective)
				onlineTest.setEqualweightage(String.valueOf(templateHdr[0][6]));//equalWeightAge
				onlineTest.setMarksWrongAns(String.valueOf(templateHdr[0][7]));//marks for wrong answer
				onlineTest.setMarksNoAns(String.valueOf(templateHdr[0][8]));//marks for no answer
				onlineTest.setOnlineScore(String.valueOf(templateHdr[0][9]));//TEMPLATE_ONLINE_SCORE
				onlineTest.setPassingMarks(String.valueOf(templateHdr[0][10]));//TEMPLATE_PASSING_MARKS
				onlineTest.setMarksForCorrect(String.valueOf(templateHdr[0][11]));//Marks for Correct
				onlineTest.setMarksHard(String.valueOf(templateHdr[0][12]));//Marks for Hard
				onlineTest.setMarksMedium(String.valueOf(templateHdr[0][13]));//Marks for Medium
				onlineTest.setMarksEasy(String.valueOf(templateHdr[0][14]));//Marks for Easy
				onlineTest.setEqualMarksForAll(String.valueOf(templateHdr[0][19]));//equal marks for all
				onlineTest.setWrongmarksHard(String.valueOf(templateHdr[0][15]));//Marks for Hard
				onlineTest.setWrongmarksMedium(String.valueOf(templateHdr[0][16]));//Marks for Medium
				onlineTest.setWrongmarksEasy(String.valueOf(templateHdr[0][17]));//Marks for Easy
				onlineTest.setTestTemplateCode(String.valueOf(templateHdr[0][18]));//Template Code

				Object[][] templateDtl = null;
				Object[][] tempAddQues = null;
				tempAddQues = getTestTemplateAddQues(onlineTest);
				if (tempAddQues != null && tempAddQues.length > 0) {
					Random rgen = new Random(); // Random number generator
					String[] randomQueCodes = new String[tempAddQues.length];
					for (int i = 0; i < randomQueCodes.length; i++) {
						randomQueCodes[i] = "" + tempAddQues[i][0];
					} 
					for (int i = 0; i < randomQueCodes.length; i++) {
						int randomPosition = rgen.nextInt(randomQueCodes.length);
						String temp = randomQueCodes[i];
						randomQueCodes[i] = randomQueCodes[randomPosition];
						randomQueCodes[randomPosition] = temp;
					}  

					/**
					 * this for loop is run to set the hidden iterator list...
					 */
					for (int i = 0; i < tempAddQues.length; i++) {
						OnlineTest bean = new OnlineTest();
						bean.setRandomQuesCodes(randomQueCodes[i]);
						questionList.add(bean);
					} 
					onlineTest.setRandomQueList(questionList);
				} else {
						/**
						 * to retirieve the TESTTEMPDTL VALUES LIKE SUBJECT CODE,CATEGORY CODE,LEVEL...
						 */
						templateDtl = getTemplateDtl(onlineTest);
					if (templateDtl != null && templateDtl.length > 0) {  
						Object[] totalQuesCodes = null;
						/**
						 * THIS OBJECT CONTAINS THE TOTAL NO OF QUESTION CODES TO BE APPEARED IN TEST
						 */
						totalQuesCodes = getQuesCodes(onlineTest, templateDtl);
						if (totalQuesCodes == null) {
							result = "totalNoOfQuesNotDefined";
							return result;
						}
						Random rgen = new Random(); // Random number generator
						String[] randomQueCodes = new String[totalQuesCodes.length];
						//--- Initialize the array to the ints 0-51
						for (int i = 0; i < randomQueCodes.length; i++) {
							randomQueCodes[i] = "" + totalQuesCodes[i];
						}  
						//--- Shuffle by exchanging each element randomly
						for (int i = 0; i < randomQueCodes.length; i++) {
							int randomPosition = rgen.nextInt(randomQueCodes.length);
							String temp = randomQueCodes[i];
							randomQueCodes[i] = randomQueCodes[randomPosition];
							randomQueCodes[randomPosition] = temp;
						}  

						/**
						 * this for loop is run to set the hidden iterator list...
						 */
						for (int i = 0; i < totalQuesCodes.length; i++) {
							OnlineTest bean = new OnlineTest();
							bean.setRandomQuesCodes(randomQueCodes[i]);
							questionList.add(bean);
						}  
						onlineTest.setRandomQueList(questionList);
					} 
				} 
			}  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean submittedTestStatus(OnlineTest onlineTest) {
		boolean status = false;
		try {
			Object[][] testStausObj = getSqlModel().getSingleResult(
							"SELECT HRMS_REC_SCHTEST_DTL.TEST_STATUS FROM HRMS_REC_SCHTEST_DTL "
							+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + onlineTest.getReqCode()
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + onlineTest.getCandidateUserEmpId()
							+ " AND HRMS_REC_SCHTEST_DTL.TEST_CODE = " + onlineTest.getOnlineTestCode());
			if (testStausObj != null && testStausObj.length > 0) {
				if(String.valueOf(testStausObj[0][0]).equals("Y")) {
					status = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
