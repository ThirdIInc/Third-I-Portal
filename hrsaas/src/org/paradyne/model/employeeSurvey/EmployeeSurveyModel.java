package org.paradyne.model.employeeSurvey;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.employeeSurvey.EmployeeSurvey;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Reeba_Joseph Aug 21, 2010
 */

public class EmployeeSurveyModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeSurveyModel.class);

	public Object[][] getEmployeeSurveys(EmployeeSurvey bean) {
		Object[][] surveyData = null;
		try {
			String surveyDataSql = " SELECT HRMS_EMPSURVEY_MASTER.SURVEY_CODE, SURVEY_NAME, TO_CHAR(SURVEY_START_DATE, 'DD-MM-YYYY'), TO_CHAR(SURVEY_END_DATE, 'DD-MM-YYYY') " 
				+ " FROM HRMS_EMPSURVEY_MASTER "
				+ " LEFT JOIN HRMS_EMPSURVEY_CONFIG ON (HRMS_EMPSURVEY_CONFIG.SURVEY_CODE = HRMS_EMPSURVEY_MASTER.SURVEY_CODE) "
				+ " WHERE SURVEY_EMP_CODE= "+bean.getUserEmpId()+ " AND SURVEY_PUBLISH='Y'"
				+ " AND TO_DATE(SYSDATE, 'DD-MM-YYYY') >= TO_DATE(SURVEY_START_DATE, 'DD-MM-YYYY') "
				+ " AND TO_DATE(SYSDATE, 'DD-MM-YYYY') <= TO_DATE(SURVEY_END_DATE, 'DD-MM-YYYY') ";
			surveyData = getSqlModel().getSingleResult(surveyDataSql);
		} catch(Exception e) {
			logger.error("Exception in getEmployeeSurveys: " + e);
		}
		return surveyData;
	}

	public void addSurveyList(EmployeeSurvey bean, HttpServletRequest request) {
		try {
			String surveyDataSql = " SELECT HRMS_EMPSURVEY_MASTER.SURVEY_CODE, SURVEY_NAME, TO_CHAR(SURVEY_START_DATE, 'DD-MM-YYYY'), TO_CHAR(SURVEY_END_DATE, 'DD-MM-YYYY') " 
				+ " FROM HRMS_EMPSURVEY_MASTER "
				+ " LEFT JOIN HRMS_EMPSURVEY_CONFIG ON (HRMS_EMPSURVEY_CONFIG.SURVEY_CODE = HRMS_EMPSURVEY_MASTER.SURVEY_CODE) "
				+ " WHERE SURVEY_EMP_CODE= "+bean.getUserEmpId()+ " AND SURVEY_PUBLISH='Y'"
				+ " AND TO_DATE(SYSDATE, 'DD-MM-YYYY') >= TO_DATE(SURVEY_START_DATE, 'DD-MM-YYYY') "
				+ " AND TO_DATE(SYSDATE, 'DD-MM-YYYY') <= TO_DATE(SURVEY_END_DATE, 'DD-MM-YYYY') ";
			Object surveyData[][] = getSqlModel().getSingleResult(surveyDataSql);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), surveyData.length, 20);
			
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));

			if(pageIndex[4].equals("1")) {
				bean.setMyPage("1");
			}

			if(surveyData != null && surveyData.length > 0) {
				List<EmployeeSurvey> surveyList = new ArrayList<EmployeeSurvey>(surveyData.length);
				
				for(int i = 0; i < surveyData.length; i++) {
					EmployeeSurvey empSurvey = new EmployeeSurvey();
					
					empSurvey.setSurveyCode(Integer.parseInt(String.valueOf(surveyData[i][0])));
					empSurvey.setSrNo(i + 1);
					empSurvey.setSurveyName(String.valueOf(surveyData[i][1]));
					empSurvey.setSurveyFromDate(String.valueOf(surveyData[i][2]));
					empSurvey.setSurveyToDate(String.valueOf(surveyData[i][3]));
					
					surveyList.add(empSurvey);
				}
				
				bean.setSurveyListLength(String.valueOf(surveyList.size()));
				bean.setSurveyList(surveyList);
				bean.setDataExists(true);
			} else {
				bean.setDataExists(false);
			}
		} catch(Exception e) {
			logger.error("Exception in addSurveyList:" + e);
		}
	}

	public void getSurveyQuestions(EmployeeSurvey bean, HttpServletRequest request) {
		try {
			String surveyQuestions = " SELECT QUES_CODE, NVL(QUES_NAME,' '), QUES_TYPE, NVL(SECTION_NAME,' '), QUES_SECTION_CODE, NVL(QUES_OPT_IS_MULTIPLE,'N') "
				+ " FROM HRMS_EMPSURVEY_QUESBANK "
				+ " LEFT JOIN HRMS_EMPSURVEY_MASTERDTL ON (HRMS_EMPSURVEY_MASTERDTL.SECTION_CODE = HRMS_EMPSURVEY_QUESBANK.QUES_SECTION_CODE) "
				+ " WHERE QUES_SURVEY_CODE= "+bean.getHiddencode()+ " ORDER BY QUES_SECTION_CODE";
			Object questionData[][] = getSqlModel().getSingleResult(surveyQuestions);

			String surveyAnswers = "SELECT QUESTION_COMMENTS, QUESTION_TYPE, QUESTION_CODE, SURVEY_FINALIZE"
				+ " FROM HRMS_EMPSURVEY_COMMENTS "
				+ " WHERE SURVEY_CODE="+bean.getHiddencode()+" AND EMP_ID="+bean.getUserEmpId()
				+ " ORDER BY SECTION_CODE";
			Object answerData[][] = getSqlModel().getSingleResult(surveyAnswers);
			
			String[] pageIndex = Utility.doPaging(bean.getMyQnPage(),
					questionData.length, 1);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "1";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalQuestions", Integer.parseInt(String
					.valueOf(pageIndex[2]))); //   to set the total number of page
			request.setAttribute("qnPageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				bean.setMyQnPage("1");
			String secValueChange = "";
			String str = "";
			if(questionData != null && questionData.length > 0) {
				List<EmployeeSurvey> questionList = new ArrayList<EmployeeSurvey>(questionData.length);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					
					EmployeeSurvey empSurvey = new EmployeeSurvey();
					empSurvey.setQnSrNo(String.valueOf(i + 1));
					
					if(secValueChange.equals("")){
						empSurvey.setShowSection(String.valueOf(questionData[i][3]));
						empSurvey.setSectionHide(true);
					} //end of if
					else{
						if(secValueChange.equals(String.valueOf(questionData[i][3]))){
							empSurvey.setSectionHide(false);
						} //end of if
						else{
							empSurvey.setShowSection(String.valueOf(questionData[i][3]));
							empSurvey.setSectionHide(true);
						} //end of else
					} //end of else
					
					empSurvey.setQuestionCode(String.valueOf(questionData[i][0]));
					empSurvey.setSurveyQuestion(String.valueOf(questionData[i][1]));
					empSurvey.setQuestionType(String.valueOf(questionData[i][2]));
					if(String.valueOf(questionData[i][2]).trim().equals("S")){
						empSurvey.setTypeFlag("SUBJ");
					}else if(String.valueOf(questionData[i][2]).trim().equals("O")){
						empSurvey.setTypeFlag("OBJ");
						getOptions(empSurvey,String.valueOf(questionData[i][0]));
					}
					empSurvey.setSectionName(String.valueOf(questionData[i][3]));
					empSurvey.setSectionCode(String.valueOf(questionData[i][4]));
					empSurvey.setOptionType(String.valueOf(questionData[i][5]));//MULTISELECT
					
					if (answerData!=null && answerData.length>0) {
						for (int j = 0; j < answerData.length; j++) {

							if (String.valueOf(questionData[i][0]).equals(
									String.valueOf(answerData[j][2]))) {

								if (String.valueOf(answerData[j][1])
										.equals("O")) {
									str = String.valueOf(answerData[j][0]);
									logger.info("str O : "+str);
									empSurvey.setOptionValues(str+",");
								} else {
									str = String.valueOf(answerData[j][0]);
									logger.info("str S : "+str);
									empSurvey.setComment(str);
								}
								empSurvey.setFinalizedStat(String.valueOf(answerData[j][3]));
								logger.info("setFinalizedStat  : "+empSurvey.getFinalizedStat());
								
							}
							if(String.valueOf(answerData[j][3]).trim().equals("Y")){
								bean.setFinalizedStatFlag(true);
								empSurvey.setFinalizedStatItrFlag(true);
							}
						}
					}
					questionList.add(empSurvey);
					secValueChange = String.valueOf(questionData[i][3]);
				}
				bean.setEmpSurveyCode(bean.getHiddencode());
				bean.setQuestionList(questionList);
				bean.setQuestionExists(true);
				bean.setQnPageNoField(String.valueOf(pageIndex[2]));
			} else {
				bean.setQuestionExists(false);
			}
			
		} catch(Exception e) {
			logger.error("Exception in getSurveyQuestions:" + e);
		}
	}
	
	public void getOptions(EmployeeSurvey bean, String questionCode){
		String optionQuery = " SELECT QUES_CODE, OPTION_CODE, OPTION_DESC FROM HRMS_EMPSURVEY_QUESOPTION "
			+ " WHERE QUES_CODE = "+questionCode+" ORDER BY OPTION_CODE";
		Object optionData[][] = getSqlModel().getSingleResult(optionQuery);
		if(optionData != null && optionData.length > 0){
			List<EmployeeSurvey> optionList = new ArrayList<EmployeeSurvey>(optionData.length);
			for (int i = 0; i < optionData.length; i++) {
				EmployeeSurvey empSurvey = new EmployeeSurvey();
				empSurvey.setOptionCode(String.valueOf(optionData[i][1]));
				empSurvey.setOptionName(String.valueOf(optionData[i][2]));
				optionList.add(empSurvey);
			}
			bean.setOptionList(optionList);
			logger.info("option length======"+optionData.length);
			bean.setOptionLength(optionData.length);
		}
	}

	public boolean saveSurveys(EmployeeSurvey bean, String[] questionCode,
			String[] optionCode, String[] sectionCode, String[] questionType,
			String[] comments, String[] optionValues, String finalizeStatus) {
		boolean result = true;
		String finalOptionValues = "";
		if(questionCode != null){
			for (int i = 0; i < questionCode.length; i++) {
				if(questionType[i].equals("O")){
					if(optionValues != null && !optionValues[i].equals("")){
						logger.info("optionValues["+i+"] :"+optionValues[i]);
						finalOptionValues = optionValues[i].substring(0, optionValues[i].length()-1);
						logger.info("finalOptionValues["+i+"] :"+finalOptionValues);
						
						//DELETE ENTRIES
						String deleteQuery = "DELETE FROM HRMS_EMPSURVEY_COMMENTS WHERE SURVEY_CODE="+bean.getEmpSurveyCode()
							+ " AND QUESTION_CODE = "+questionCode[i]+ " AND EMP_ID="+bean.getUserEmpId();
						getSqlModel().singleExecute(deleteQuery);
						
						String insertQuery = " INSERT INTO HRMS_EMPSURVEY_COMMENTS (SURVEY_CODE, SECTION_CODE, QUESTION_CODE, QUESTION_COMMENTS, QUESTION_TYPE, EMP_ID, SURVEY_FINALIZE) "
							 + " VALUES ("+bean.getEmpSurveyCode()+", "
							 +sectionCode[i]+", "
							 +questionCode[i]+", '"
							 +finalOptionValues+"', '"
							 +questionType[i]+"', "
							 +bean.getUserEmpId()+", '"
							 +finalizeStatus+"') ";
						result = getSqlModel().singleExecute(insertQuery);
					}
				}else{
					logger.info("comments["+i+"] : "+comments[i]);
					//DELETE ENTRIES
					String deleteQuery = "DELETE FROM HRMS_EMPSURVEY_COMMENTS WHERE SURVEY_CODE="+bean.getEmpSurveyCode()
						+ " AND QUESTION_CODE = "+questionCode[i]+ " AND EMP_ID="+bean.getUserEmpId();
					getSqlModel().singleExecute(deleteQuery);
					if(comments != null && !comments[i].equals("")){
						String insertQuery = " INSERT INTO HRMS_EMPSURVEY_COMMENTS (SURVEY_CODE, SECTION_CODE, QUESTION_CODE, QUESTION_COMMENTS, QUESTION_TYPE, EMP_ID, SURVEY_FINALIZE) "
							 + " VALUES ("+bean.getEmpSurveyCode()+", "
							 +sectionCode[i]+", "
							 +questionCode[i]+", '"
							 +comments[i]+"', '"
							 +questionType[i]+"', "
							 +bean.getUserEmpId()+", '"
							 +finalizeStatus+"') ";
						result = getSqlModel().singleExecute(insertQuery);
					}
				}
			}
			String updateStatus = " UPDATE HRMS_EMPSURVEY_COMMENTS SET SURVEY_FINALIZE = '"+finalizeStatus+"' WHERE SURVEY_CODE = "+bean.getEmpSurveyCode();
			getSqlModel().singleExecute(updateStatus);
		}
		return result;
	}
}