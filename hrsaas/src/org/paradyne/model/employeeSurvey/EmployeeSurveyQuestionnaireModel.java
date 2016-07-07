/**
 * 
 */
package org.paradyne.model.employeeSurvey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyQuestionnaire;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Reeba_Joseph
 * 
 */
public class EmployeeSurveyQuestionnaireModel extends ModelBase {

	public void addOptionShow(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] srNo, String[] option, String[] code) {
		ArrayList<Object> list = new ArrayList<Object>();
		// String val = surveyQuestionnaire.getOption();
		if (option != null) {
			System.out.println("option=================== " + option.length);
			for (int i = 0; i < option.length; i++) {
				EmployeeSurveyQuestionnaire bean = new EmployeeSurveyQuestionnaire();
				bean.setSrNo(srNo[i]);
				bean.setOptionName(option[i]);
				if (!(surveyQuestionnaire.getQuesCode().equals("") || surveyQuestionnaire
						.getQuesCode().equals("null"))) {
					bean.setOptionCode(code[i]);
				}
				list.add(bean);
			}
			surveyQuestionnaire.setOptionList(list);
		}

	}

	public boolean chkDuplicate(
			EmployeeSurveyQuestionnaire surveyQuestionnaire, String[] srNo,
			String[] option, String[] code, HttpServletRequest request) {
		int count = 0;

		if (option != null && option.length > 0) {
			for (int i = 0; i < option.length; i++) {

				if (surveyQuestionnaire.getOptionTextarea().trim()
						.equalsIgnoreCase(option[i].trim())) {
					count = 1;
					break;
				} else {
					count = 0;
				}
			}
		}

		ArrayList<Object> list = new ArrayList<Object>();

		if (option != null) {
			for (int i = 0; i < option.length; i++) {
				EmployeeSurveyQuestionnaire bean = new EmployeeSurveyQuestionnaire();
				bean.setSrNo(srNo[i]);
				bean.setOptionName(option[i]);
				if (!(surveyQuestionnaire.getQuesCode().equals("") || surveyQuestionnaire
						.getQuesCode().equals("null"))) {
					bean.setOptionCode(code[i]);
				}
				list.add(bean);
			}
		}

		surveyQuestionnaire.setOptionList(list);
		if (count == 0)
			return false;
		else
			return true;

	}

	public void addOption(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] srNo, String[] option, String[] code,
			HttpServletRequest request) {
		ArrayList<Object> list = new ArrayList<Object>();
		// String val = surveyQuestionnaire.getOption();
		if (option != null) {
			for (int i = 0; i < option.length; i++) {
				EmployeeSurveyQuestionnaire bean = new EmployeeSurveyQuestionnaire();
				bean.setSrNo(srNo[i]);
				bean.setOptionName(option[i]);
				if (!(surveyQuestionnaire.getQuesCode().equals("") || surveyQuestionnaire
						.getQuesCode().equals("null"))) {
					bean.setOptionCode(code[i]);
				}
				list.add(bean);
			}
		}
		EmployeeSurveyQuestionnaire qb1 = new EmployeeSurveyQuestionnaire();
		System.out
				.println("surveyQuestionnaire.getOptionTextarea()================ "
						+ surveyQuestionnaire.getOptionTextarea());
		String opt = request.getParameter("optionTextarea");

		System.out.println("request.getParameter .getOption()================ "
				+ opt);
		qb1.setSrNo(String.valueOf(list.size() + 1));
		qb1.setOptionName(opt);
		list.add(qb1);

		surveyQuestionnaire.setOptionList(list);

	}

	public void editOption(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] srNo, String[] option, String[] opcode,
			HttpServletRequest request) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			for (int i = 0; i < option.length; i++) {

				if ((i + 1 != Integer.parseInt(surveyQuestionnaire.getParaId()))) {
					EmployeeSurveyQuestionnaire bean1 = new EmployeeSurveyQuestionnaire();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setOptionName(option[i]);
					if (!(surveyQuestionnaire.getQuesCode().equals("") || surveyQuestionnaire
							.getQuesCode().equals("null"))) {
						bean1.setOptionCode(opcode[i]);
					}
					tableList.add(bean1);
				} // end of if
				else {

					EmployeeSurveyQuestionnaire qb1 = new EmployeeSurveyQuestionnaire();

					qb1.setOptionName(surveyQuestionnaire.getOptionTextarea());
					if (!(surveyQuestionnaire.getOptionId().equals(""))) {
						qb1.setOptionCode(surveyQuestionnaire.getOptionId());
					}

					tableList.add(qb1);
				} // end of else
			} // end of for loop
			surveyQuestionnaire.setOptionList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteOption(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] code, String[] option, String[] delete) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String ansVar = "";
			if (option != null) {
				surveyQuestionnaire.setOptionFlag("true");
				for (int i = 0; i < option.length; i++) {
					System.out.println("val of" + delete[i]);
					EmployeeSurveyQuestionnaire bean = new EmployeeSurveyQuestionnaire();

					if ((i + 1 != Integer.parseInt(surveyQuestionnaire
							.getParaId()))) {
						ansVar += option[i] + ",";
						bean.setOptionName(option[i]);
						if (!(surveyQuestionnaire.getQuesCode().equals("") || surveyQuestionnaire
								.getQuesCode().equals("null"))) {
							bean.setOptionCode(code[i]);
						}
						list.add(bean);
					}
				}
			}
			surveyQuestionnaire.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean save(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] srNo, String[] option) {
		try {
			Object[][] dataQue = new Object[1][5];
			dataQue[0][0] = surveyQuestionnaire.getSurveyCode();// qus subject
			// code
			dataQue[0][1] = surveyQuestionnaire.getAnsOptions();// qu estion
			// type
			// Subjective(S) or
			// Objective(O)
			dataQue[0][2] = surveyQuestionnaire.getQuestion();
			dataQue[0][3] = surveyQuestionnaire.getSectionCode();
			dataQue[0][4] = surveyQuestionnaire.getMultipleSelect();
			System.out.println("....................dataQue[0][4] :"+dataQue[0][4]);
			
			if (surveyQuestionnaire.getMultipleSelect().equals("true")){
				dataQue[0][4] ="Y";
			}
			else{
				dataQue[0][4] ="N";
				
			}
			for (int i = 0; i < dataQue[0].length; i++) {
				System.out.println("dataQue[0]["+i+"] :"+dataQue[0][i]);
			}

			String insertQuery = "INSERT INTO HRMS_EMPSURVEY_QUESBANK (QUES_CODE,QUES_SURVEY_CODE,QUES_TYPE ,QUES_NAME, QUES_SECTION_CODE,QUES_OPT_IS_MULTIPLE)"
					+ " VALUES((SELECT NVL(MAX(QUES_CODE),0)+1 FROM HRMS_EMPSURVEY_QUESBANK),?,?,?,?,?) ";
			boolean result = getSqlModel().singleExecute(insertQuery, dataQue);
			if (result) {
				// surveyQuestionnaire.setQuestionView("true");
				String query = "SELECT MAX(QUES_CODE) FROM HRMS_EMPSURVEY_QUESBANK";
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					surveyQuestionnaire.setQuesCode(String.valueOf(data[0][0]));
					if (option != null) {
						for (int i = 0; i < option.length; i++) {
							String optionQuery = "INSERT INTO HRMS_EMPSURVEY_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC) "
									+ " VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM HRMS_EMPSURVEY_QUESOPTION),"
									+ data[0][0] + ",'" + option[i] + "') ";
							result = getSqlModel().singleExecute(optionQuery);
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateData(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			String[] option, String[] opcode) {
		boolean result = false;
		try {
			Object[][] dataQue = new Object[1][6];

			dataQue[0][0] = surveyQuestionnaire.getSurveyCode();// qus subject
																// code
			dataQue[0][1] = surveyQuestionnaire.getAnsOptions();// qu estion
																// type
			// Subjective(S) or
			// Objective(O)
			dataQue[0][2] = surveyQuestionnaire.getQuestion();
			dataQue[0][3] = surveyQuestionnaire.getSectionCode();
			if(surveyQuestionnaire.getMultipleSelect().equals("true"))
				dataQue[0][4] ="Y";
			else
				dataQue[0][4] ="N";
			dataQue[0][5] = surveyQuestionnaire.getQuesCode();
			
			String updateQuery = " UPDATE HRMS_EMPSURVEY_QUESBANK set QUES_SURVEY_CODE =?,QUES_TYPE=?,"
					+ " QUES_NAME=?, QUES_SECTION_CODE= ? ,QUES_OPT_IS_MULTIPLE=? where QUES_CODE=? ";
			result = getSqlModel().singleExecute(updateQuery, dataQue);

			/* new Adding */
			if (result) {
				Object[][] data = new Object[1][1];
				data[0][0] = surveyQuestionnaire.getQuesCode();
				String queryOption = "DELETE FROM HRMS_EMPSURVEY_QUESOPTION WHERE QUES_CODE=?";
				getSqlModel().singleExecute(queryOption, data);
				surveyQuestionnaire.setQuesCode(String.valueOf(data[0][0]));

				if (surveyQuestionnaire.getAnsOptions().equals("S")) {
					surveyQuestionnaire.setOptionFlag("false");
				} else {
					surveyQuestionnaire.setOptionFlag("true");
					if (option != null) {
						for (int i = 0; i < option.length; i++) {
							String optionQuery = "INSERT INTO HRMS_EMPSURVEY_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC) "
									+ " VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM HRMS_EMPSURVEY_QUESOPTION),"
									+ data[0][0] + ",'" + option[i] + "') ";
							result = getSqlModel().singleExecute(optionQuery);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to display the question details
	 * 
	 * @param surveyQuestionnaire
	 */
	public void getQsnDetails(EmployeeSurveyQuestionnaire surveyQuestionnaire) {
		String query = "SELECT QUES_CODE,NVL(QUES_NAME,' '),QUES_TYPE ,"
				+ " NVL(HRMS_EMPSURVEY_MASTER.SURVEY_NAME,' ') ,QUES_SURVEY_CODE, NVL(SECTION_NAME,' '),QUES_SECTION_CODE, NVL(QUES_OPT_IS_MULTIPLE,'N') FROM HRMS_EMPSURVEY_QUESBANK LEFT JOIN "
				+ " HRMS_EMPSURVEY_MASTER ON(HRMS_EMPSURVEY_MASTER.SURVEY_CODE=HRMS_EMPSURVEY_QUESBANK.QUES_SURVEY_CODE)"
				+ " LEFT JOIN HRMS_EMPSURVEY_MASTERDTL ON (HRMS_EMPSURVEY_MASTERDTL.SECTION_CODE = HRMS_EMPSURVEY_QUESBANK.QUES_SECTION_CODE)"
				+ " WHERE QUES_CODE=" + surveyQuestionnaire.getQuesCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			// surveyQuestionnaire.setQuestionView("true");
			surveyQuestionnaire.setQuesCode(String.valueOf(data[0][0]));
			surveyQuestionnaire.setQuestion(String.valueOf(data[0][1]));
			surveyQuestionnaire.setAnsOptions(String.valueOf(data[0][2]));
			surveyQuestionnaire.setSurveyName(String.valueOf(data[0][3]));
			surveyQuestionnaire.setSurveyCode(String.valueOf(data[0][4]));
			surveyQuestionnaire.setSectionName(String.valueOf(data[0][5]));
			surveyQuestionnaire.setSectionCode(String.valueOf(data[0][6]));
			if(String.valueOf(data[0][7]).trim().equals("Y")){
				surveyQuestionnaire.setMultipleSelect("true");
			}else
				surveyQuestionnaire.setMultipleSelect("false");
		}
	}

	/**
	 * following function is called to display the option details for the
	 * corresponding question.
	 * 
	 * @param surveyQuestionnaire
	 */
	public void getOptionDet(EmployeeSurveyQuestionnaire surveyQuestionnaire) {
		System.out.println("surveyQuestionnaire.getQuesCode() : "+surveyQuestionnaire.getQuesCode());
		String query = "SELECT OPTION_DESC,OPTION_CODE FROM HRMS_EMPSURVEY_QUESOPTION LEFT JOIN "
				+ " HRMS_EMPSURVEY_QUESBANK ON(HRMS_EMPSURVEY_QUESBANK.QUES_CODE=HRMS_EMPSURVEY_QUESOPTION.QUES_CODE)"
				+ " WHERE HRMS_EMPSURVEY_QUESOPTION.QUES_CODE="
				+ surveyQuestionnaire.getQuesCode() + " ORDER BY OPTION_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			surveyQuestionnaire.setOptionFlag("true");
			for (int i = 0; i < data.length; i++) {
				EmployeeSurveyQuestionnaire bean = new EmployeeSurveyQuestionnaire();
				bean.setOptionName(String.valueOf(data[i][0]));
				bean.setOptionCode(String.valueOf(data[i][1]));
				list.add(bean);
			}
		}
		surveyQuestionnaire.setOptionList(list);
	}

	public void questionData(EmployeeSurveyQuestionnaire surveyQuestionnaire,
			HttpServletRequest request) {
		try {

			String query = "SELECT HRMS_EMPSURVEY_MASTER.SURVEY_NAME,NVL(QUES_NAME,' '),QUES_CODE, "
					+ "QUES_TYPE, NVL(SECTION_NAME,' '),QUES_SECTION_CODE FROM HRMS_EMPSURVEY_QUESBANK  "
					+ "LEFT JOIN HRMS_EMPSURVEY_MASTER on (HRMS_EMPSURVEY_QUESBANK.QUES_SURVEY_CODE=HRMS_EMPSURVEY_MASTER.SURVEY_CODE) "
					+ " LEFT JOIN HRMS_EMPSURVEY_MASTERDTL ON (HRMS_EMPSURVEY_MASTERDTL.SECTION_CODE = HRMS_EMPSURVEY_QUESBANK.QUES_SECTION_CODE)"
					+ "ORDER BY QUES_CODE  ";
			Object[][] repData = getSqlModel().getSingleResult(query);

			if (repData.length > 0 && repData != null) {
				String[] pageIndex = Utility.doPaging(surveyQuestionnaire
						.getMyPage(), repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					surveyQuestionnaire.setMyPage("1");
				if (repData != null && repData.length > 0) {
					surveyQuestionnaire.setNoData("true");
					ArrayList<Object> list1 = new ArrayList<Object>();
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						EmployeeSurveyQuestionnaire bean1 = new EmployeeSurveyQuestionnaire();
						bean1.setQuesCodeItt(String.valueOf(repData[i][2]));
						bean1.setSurveyName(String.valueOf(repData[i][0]));
						bean1.setQuestion(String.valueOf(repData[i][1]));
						bean1.setAnsOptions(String.valueOf(repData[i][3]));
						bean1.setSectionName(String.valueOf(repData[i][4]));
						
						list1.add(bean1);

					}
					surveyQuestionnaire.setQuestionList(list1);
					surveyQuestionnaire.setTotRec(String.valueOf(Integer
							.parseInt(String.valueOf(repData.length))));
				}
			} else {
				surveyQuestionnaire.setNoData("false");
				request.setAttribute("totalPage", 1);
				request.setAttribute("PageNo", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean delete(EmployeeSurveyQuestionnaire surveyQuestionnaire) {
		//Object[][] del = new Object[1][1];
		//del[0][0] = surveyQuestionnaire.getQuesCode();
		System.out.println(".................... :"+surveyQuestionnaire.getQuesCode());
		String queryOption = "DELETE FROM HRMS_EMPSURVEY_QUESOPTION WHERE QUES_CODE="+surveyQuestionnaire.getQuesCode();
		boolean flag = getSqlModel().singleExecute(queryOption);

		boolean result = false;
		if (flag) {
			String QueryQues = " DELETE FROM HRMS_EMPSURVEY_QUESBANK WHERE QUES_CODE="+surveyQuestionnaire.getQuesCode();
			result = getSqlModel().singleExecute(QueryQues);

		}

		return result;

	}

}
