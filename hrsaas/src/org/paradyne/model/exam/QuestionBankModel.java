package org.paradyne.model.exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.*;
import org.paradyne.bean.exam.QuestionBank;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import javax.servlet.http.HttpServletResponse;

public class QuestionBankModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionBankModel.class);

	/**
	 * following function is called to check the duplicate answer option
	 * 
	 * @param questionBank
	 * @param option
	 * @param quesChoiceLen 
	 * @return
	 */
	public boolean chkDuplicate(QuestionBank questionBank, String[] srNo,
			String[] option, String[] flagItt, String[] code,
			HttpServletRequest request, String[] quesChoiceLen) {
		int count = 0;
		boolean result = false;
		try {
			if (option != null && option.length > 0) {
				for (int i = 0; i < option.length; i++) {
					if (questionBank.getParaId().equals("")) {
						if (questionBank.getOptionTextarea().trim().equalsIgnoreCase(quesChoiceLen[i].trim())) {
							count = 1;
							break;
						} else {
							count = 0;
						}
					}/* else if (!questionBank.getParaId().equals(srNo[i])){
						if (questionBank.getOptionTextarea().trim().equalsIgnoreCase(quesChoiceLen[i].trim())) {
							count = 1;
							break;
						} else {
							count = 0;
						}
					}*/
					else if (i != Integer.parseInt(questionBank.getParaId()) - 1)
					{
						if (questionBank.getOptionTextarea().trim().equalsIgnoreCase(quesChoiceLen[i].trim())) {
							count = 1;
							break;
						} else {
							count = 0;
						}
					}
				}
			}
			ArrayList<Object> list = new ArrayList<Object>();
			if (option != null) {
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setSrNo(srNo[i]);
					bean.setOptionName(option[i]);
					/*if(bean.getOptionName().length() > 25) {
						bean.setQuesChoiceLen(bean.getOptionName().substring(0, 20) + "....");
					} else {
						bean.setQuesChoiceLen(bean.getOptionName());
					}*/
					if(option[i].length()>25)
					 {
						bean.setQuesChoiceLen(option[i].substring(0,20));
					}
					else{
						bean.setQuesChoiceLen(option[i]);
					}
					bean.setFlagItt(flagItt[i]);
					if (!(questionBank.getQuesCode().equals("") || questionBank
							.getQuesCode().equals("null"))) {
						bean.setOptionCode(code[i]);
					}
					list.add(bean);
				}
			}
			questionBank.setOptionList(list);
			if (count == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to edit the answer options.This function is
	 * called when the edit button is clicked in the option list.
	 * 
	 * @param questionBank
	 * @param srNo
	 * @param option
	 * @param flagItt
	 * @param opcode
	 * @param request
	 */
	public void editOption(QuestionBank questionBank, String[] srNo,
			String[] option, String[] flagItt, String[] opcode,
			HttpServletRequest request) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			for (int i = 0; i < option.length; i++) {

				if ((i + 1 != Integer.parseInt(questionBank.getParaId()))) {
					QuestionBank bean1 = new QuestionBank();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setOptionName(option[i]);
					bean1.setFlagItt((flagItt[i]));
					if (!(questionBank.getQuesCode().equals("") || questionBank
							.getQuesCode().equals("null"))) {
						bean1.setOptionCode(opcode[i]);
					}
					
					if(option[i].length()>25)
					 {
						bean1.setQuesChoiceLen(option[i].substring(0,20));
					}
					else{
						bean1.setQuesChoiceLen(option[i]);
					}
					
					tableList.add(bean1);
				}  else {
					QuestionBank qb1 = new QuestionBank();
					qb1.setSrNo(String.valueOf(i + 1));
					qb1.setOptionName(questionBank.getOptionTextarea());
					if(qb1.getOptionName().length() > 25) {
						qb1.setQuesChoiceLen(qb1.getOptionName().substring(0, 20) + "....");
					} else {
						qb1.setQuesChoiceLen(qb1.getOptionName());
					}
					if (String.valueOf(questionBank.getFlag()).equals("Y")) {
						qb1.setFlagItt("Yes"); 
					} else {
						qb1.setFlagItt("No");
					}
						
					if (!(questionBank.getOptionId().equals(""))) {
						qb1.setOptionCode(questionBank.getOptionId());
					}
					tableList.add(qb1);
				}  
			}  
			questionBank.setOptionList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to insert the records into the database.
	 * 
	 * @param questionBank
	 * @param srNo
	 * @param option
	 * @param flagItt
	 * @return
	 */
	public boolean save(QuestionBank questionBank, String[] srNo,
			String[] option, String[] flagItt) {
		try {
			boolean result = false;
			Object[][] dataQue = new Object[1][10];
			dataQue[0][0] = questionBank.getSubjectCode();// qus subject code
			dataQue[0][1] = questionBank.getAnsOptions();// qu estion type Subjective(S) or Objective(O)
			dataQue[0][2] = questionBank.getLimit();
			dataQue[0][3] = questionBank.getQsnWtg();
			dataQue[0][4] = questionBank.getCompLevel();
			dataQue[0][5] = questionBank.getQuestion();
			dataQue[0][6] = questionBank.getQsnStatus();
			if (questionBank.getCategoryCode().equals("") || questionBank.getCategoryCode().equals("null")) {
				dataQue[0][7] = String.valueOf("");
			} else {
				dataQue[0][7] = questionBank.getCategoryCode();
			}
			
			dataQue[0][8] = questionBank.getAnsOptions().equals("S") ? questionBank.getUploadFileName() : ""; 
			dataQue[0][9] = questionBank.getAllowToUploadAnswer().equals("true") ? "Y" : "N";
			
			boolean duplicate = chkDuplicateQues(questionBank);
			
			if (!duplicate) {
				result = getSqlModel().singleExecute(getQuery(1), dataQue);
				if (result) {
					questionBank.setQuestionView("true");
					String query = "SELECT MAX(QUES_CODE) FROM HRMS_REC_QUESBANK";
					Object[][] data = getSqlModel().getSingleResult(query);
					if (data != null && data.length > 0) {
						questionBank.setQuesCode(String.valueOf(data[0][0]));
						if (option != null) {
							String Flg = "";
							for (int i = 0; i < option.length; i++) {
								if (flagItt[i].equalsIgnoreCase("Yes")) {
									Flg = "Y";
								} else {
									Flg = "N";
								}
								String optionQuery = "INSERT INTO HRMS_REC_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC,OPTION_ANS_FLAG) "
										+ " VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM HRMS_REC_QUESOPTION), ?"
										// + data[0][0]
										+ ",?"
										// + option[i]
										+ ",?"
										// + Flg
										+ ") ";

								Object[][] paramObj = new Object[1][3];
								paramObj[0][0] = data[0][0];
								paramObj[0][1] = option[i];
								paramObj[0][2] = Flg;

								result = getSqlModel().singleExecute(
										optionQuery, paramObj);
							}
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

	/**
	 * following function is called when any row in the list is double clicked.
	 * This function will set the corresponding values.
	 * 
	 * @param code
	 * @param questionBank
	 */
	public void getCallForEdit(String code, QuestionBank questionBank) {

		try {
			String query = "SELECT QUES_CODE,NVL(QUES_NAME,' '),QUES_TYPE ,"
					+ " QUES_LEVEL,QUES_MARK,NVL(QUES_LIMIT,''),NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' '),"
					+ " QUES_SUB_CODE,NVL(CAT_NAME,' '), QUES_STATUS, CAT_CODE, NVL(QUES_UPLOADED_DOC,''),"
					+ " NVL(QUES_UPLOAD_ANSWER_FLAG,'')"
					+ " FROM HRMS_REC_QUESBANK"
					+ " LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE=HRMS_REC_QUESBANK.QUES_SUB_CODE)"
					+ " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"
					+ " WHERE QUES_CODE=" + code;
			Object[][] data = getSqlModel().getSingleResult(query);
			questionBank.setQuesCode(checkNull(String.valueOf(data[0][0])));
			questionBank.setQuestion(checkNull(String.valueOf(data[0][1])));
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][2]).equals("O")){
					//questionBank.setAnsOptions("Objective");
					questionBank.setAnsOptions("O");
					questionBank.setOptionFlag("true");
				}
				else{
					//questionBank.setAnsOptions("Subjective");
					questionBank.setAnsOptions("S");
					questionBank.setOptionFlag("false");
				}

			} else {
				questionBank
						.setAnsOptions(checkNull(String.valueOf(data[0][2])));
			}
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][3]).equals("E")) {
					questionBank.setCompLevel("E");
				} else if (String.valueOf(data[0][3]).equals("M")) {
					questionBank.setCompLevel("M");
				} else {
					questionBank.setCompLevel("H");
				}
			} else {
				questionBank.setCompLevel(String.valueOf(data[0][3]));
			}
			// questionBank.setCompLevel(String.valueOf(data[0][3]));
			if (String.valueOf(data[0][4]).equals("null") || String.valueOf(data[0][4]).equals("")) {
				questionBank.setQsnWtg("");
			} else {
				questionBank.setQsnWtg(String.valueOf(data[0][4]));
			}
			questionBank.setLimit(checkNull(String.valueOf(data[0][5])));
			questionBank.setSubject(checkNull(String.valueOf(data[0][6])));
			questionBank.setSubjectCode(checkNull(String.valueOf(data[0][7])));
			questionBank.setCategory(checkNull(String.valueOf(data[0][8])));
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][9]).equals("A")) {
					questionBank.setQsnStatus("A");
				} else {
					questionBank.setQsnStatus("D");
				}
			} else {
				questionBank.setQsnStatus(String.valueOf(data[0][9]));
			}
			questionBank.setCategoryCode(String.valueOf(data[0][10]));
			questionBank.setUploadFileName(checkNull(String.valueOf(data[0][11])));
			if (checkNull(String.valueOf(data[0][2])).equals("S")) {
				questionBank.setViewUploadDocFlag(true);
			}
			
			if (checkNull(String.valueOf(data[0][12])).equals("Y")) {
				questionBank.setAllowToUploadAnswer("true");
			} else {
				questionBank.setAllowToUploadAnswer("false");
			}
			
			String query1 = "SELECT OPTION_DESC, CASE WHEN OPTION_ANS_FLAG='Y' THEN 'Yes' ELSE 'No' END,"
					+ " OPTION_CODE FROM HRMS_REC_QUESOPTION"
					+ " LEFT JOIN HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = HRMS_REC_QUESOPTION.QUES_CODE)"
					+ " WHERE HRMS_REC_QUESOPTION.QUES_CODE= " + code
					+ " ORDER BY OPTION_CODE";
			Object[][] data1 = getSqlModel().getSingleResult(query1);
			ArrayList<Object> list = new ArrayList<Object>();
			if (data1 != null && data1.length > 0) {
				questionBank.setOptionFlag("true");
				for (int i = 0; i < data1.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setOptionName(checkNull(String.valueOf(data1[i][0])));
					if(bean.getOptionName().length() > 25) {
						bean.setQuesChoiceLen(bean.getOptionName().substring(0, 20) + "....");
					} else {
						bean.setQuesChoiceLen(bean.getOptionName());
					}
					bean.setFlagItt(checkNull(String.valueOf(data1[i][1])));
					bean.setOptionCode(checkNull(String.valueOf(data1[i][2])));
					list.add(bean);
				}
			}
			questionBank.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the question details
	 * 
	 * @param questionBank
	 */
	public void getQsnDetails(QuestionBank questionBank) {
		try {
			String query = "SELECT QUES_CODE,NVL(QUES_NAME,' '),QUES_TYPE ,"
					+ " QUES_LEVEL,QUES_MARK,NVL(QUES_LIMIT,''),NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' ') ,QUES_SUB_CODE,NVL(CAT_NAME,' '),QUES_STATUS,CAT_CODE, NVL(QUES_UPLOADED_DOC,''), NVL(QUES_UPLOAD_ANSWER_FLAG,'') FROM HRMS_REC_QUESBANK "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE=HRMS_REC_QUESBANK.QUES_SUB_CODE)"
					+ " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"
					+ " WHERE QUES_CODE=" + questionBank.getQuesCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				questionBank.setQuestionView("true");
				questionBank.setQuesCode(String.valueOf(data[0][0]));
				questionBank.setQuestion(String.valueOf(data[0][1]));
				if (questionBank.getFlagView().equals("true")) {
					if (String.valueOf(data[0][2]).equals("O")) {
						questionBank.setAnsOptions("O");
					} else {
						questionBank.setAnsOptions("S");
						questionBank.setLimFlag("true");
					}
				} else {
					questionBank.setAnsOptions(String.valueOf(data[0][2]));
				}

				if (questionBank.getFlagView().equals("true")) {
					if (String.valueOf(data[0][3]).equals("E"))
						questionBank.setCompLevel("E");
					else if (String.valueOf(data[0][3]).equals("M"))
						questionBank.setCompLevel("M");
					else
						questionBank.setCompLevel("H");
				} else {
					questionBank.setCompLevel(String.valueOf(data[0][3]));
				}

				// questionBank.setCompLevel(String.valueOf(data[0][3]));
				questionBank.setQsnWtg(String.valueOf(data[0][4]));
				questionBank.setLimit(checkNull(String.valueOf(data[0][5])));
				questionBank.setSubject(String.valueOf(data[0][6]));
				questionBank.setSubjectCode(String.valueOf(data[0][7]));
				questionBank.setCategory(String.valueOf(data[0][8]));
				if (questionBank.getFlagView().equals("true")) {
					if (String.valueOf(data[0][9]).equals("A")) {
						questionBank.setQsnStatus("A");
					} else {
						questionBank.setQsnStatus("D");
					}
				} else {
					questionBank.setQsnStatus(String.valueOf(data[0][9]));
				}
				questionBank.setCategoryCode(String.valueOf(data[0][10]));
				questionBank.setUploadFileName(checkNull(String.valueOf(data[0][11])));
				if (String.valueOf(data[0][2]).equals("S")) {
					questionBank.setViewUploadDocFlag(true);
				}
				
				if (checkNull(String.valueOf(data[0][12])).equals("Y")) {
					questionBank.setAllowToUploadAnswer("true");
				} else {
					questionBank.setAllowToUploadAnswer("false");
				}
			}
			// questionBank.setQsnStatus(String.valueOf(data[0][9]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getQsnDetailsRec(QuestionBank questionBank) {
		try {
			String query = "SELECT QUES_CODE,NVL(QUES_NAME,' '),DECODE(QUES_TYPE,'O','Objective','S','Subjective') ,"
					+ " DECODE(QUES_LEVEL,'E','Easy','M','Medium','H','HARD'),QUES_MARK,NVL(QUES_LIMIT,''),NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' ') ,QUES_SUB_CODE,NVL(CAT_NAME,' '),DECODE(QUES_STATUS,'A','Active','D','Deactive'),CAT_CODE FROM HRMS_REC_QUESBANK LEFT JOIN "
					+ " HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE=HRMS_REC_QUESBANK.QUES_SUB_CODE)"
					+ " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"
					+ " WHERE QUES_CODE=" + questionBank.getQuesCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			questionBank.setQuesCode(String.valueOf(data[0][0]));
			questionBank.setQuestion(String.valueOf(data[0][1]));
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][2]).equals("O")) {
					questionBank.setAnsOptions("O");
				} else {
					questionBank.setAnsOptions("S");
					questionBank.setLimFlag("true");
				}

			} else {
				questionBank.setAnsOptions(String.valueOf(data[0][2]));
			}
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][3]).equals("E"))
					questionBank.setCompLevel("E");
				else if (String.valueOf(data[0][3]).equals("M"))
					questionBank.setCompLevel("M");
				else
					questionBank.setCompLevel("H");
			} else {
				questionBank.setCompLevel(String.valueOf(data[0][3]));
			}
			// questionBank.setCompLevel(String.valueOf(data[0][3]));
			questionBank.setQsnWtg(String.valueOf(data[0][4]));
			questionBank.setLimit(checkNull(String.valueOf(data[0][5])));
			questionBank.setSubject(String.valueOf(data[0][6]));
			questionBank.setSubjectCode(String.valueOf(data[0][7]));
			questionBank.setCategory(String.valueOf(data[0][8]));
			if (questionBank.getFlagView().equals("true")) {
				if (String.valueOf(data[0][9]).equals("A")) {
					questionBank.setQsnStatus("A");
				} else {
					questionBank.setQsnStatus("D");
				}
			} else {
				questionBank.setQsnStatus(String.valueOf(data[0][9]));
			}
			questionBank.setCategoryCode(String.valueOf(data[0][10]));
			// questionBank.setQsnStatus(String.valueOf(data[0][9]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the option details for the
	 * corresponding question.
	 * 
	 * @param questionBank
	 */
	public void getOptionDet(QuestionBank questionBank) {
		try {
			String query = "SELECT OPTION_DESC,CASE WHEN OPTION_ANS_FLAG='Y' THEN 'Right' ELSE 'Wrong' END,OPTION_CODE,OPTION_ANS_FLAG FROM HRMS_REC_QUESOPTION LEFT JOIN "
					+ " HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE=HRMS_REC_QUESOPTION.QUES_CODE)"
					+ " WHERE HRMS_REC_QUESOPTION.QUES_CODE="
					+ questionBank.getQuesCode() + " ORDER BY OPTION_CODE";
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (data != null && data.length > 0) {
				questionBank.setOptionFlag("true");
				for (int i = 0; i < data.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setOptionName(String.valueOf(data[i][0]));
					if(bean.getOptionName().length() > 25) {
						bean.setQuesChoiceLen(bean.getOptionName().substring(0, 20) + "....");
					} else {
						bean.setQuesChoiceLen(bean.getOptionName());
					}
					bean.setFlagItt(String.valueOf(data[i][1]));
					bean.setOptionCode(String.valueOf(data[i][2]));
					list.add(bean);
				}
			}
			questionBank.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean delete(QuestionBank questionBank) {
		boolean result = false;
		try {
			Object[][] del = new Object[1][1];
			del[0][0] = questionBank.getQuesCode();
			String queryOption = "DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE=?";
			boolean flag = getSqlModel().singleExecute(queryOption, del);
			if (flag) {
				String QueryQues = " DELETE FROM HRMS_REC_QUESBANK WHERE QUES_CODE=?";
				result = getSqlModel().singleExecute(QueryQues, del);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addOption(QuestionBank questionBank, String[] srNo,
		String[] option, String[] flagItt, String[] code,
		HttpServletRequest request) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String val = questionBank.getOption();
			if (option != null) {
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setSrNo(srNo[i]);
					bean.setOptionName(option[i]);
					/*if(bean.getOptionName().length() > 25) {
						bean.setQuesChoiceLen(bean.getOptionName().substring(0, 20) + "....");
					} else {
						bean.setQuesChoiceLen(bean.getOptionName());
					}*/
					if(option[i].length()>25)
					 {
						bean.setQuesChoiceLen(option[i].substring(0,20));
					}
					else{
						bean.setQuesChoiceLen(option[i]);
					}
					bean.setFlagItt(flagItt[i]);
					if (!(questionBank.getQuesCode().equals("") || questionBank
							.getQuesCode().equals("null"))) {
						bean.setOptionCode(code[i]);
					}
					list.add(bean);
				}
			}
			QuestionBank qb1 = new QuestionBank();
			String opt = request.getParameter("optionTextarea");
			qb1.setSrNo(String.valueOf(list.size() + 1));
			qb1.setOptionName(opt);
			if(qb1.getOptionName().length() > 25) {
				qb1.setQuesChoiceLen(qb1.getOptionName().substring(0, 20) + "....");
			} else {
				qb1.setQuesChoiceLen(qb1.getOptionName());
			}
			if (String.valueOf(questionBank.getFlag()).equals("Y")) {
				qb1.setFlagItt("Yes");
			} else {
				qb1.setFlagItt("No");
			}
			list.add(qb1);
			questionBank.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOptionShow(QuestionBank questionBank, String[] srNo,
			String[] option, String[] flagItt, String[] code) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String val = questionBank.getOption();
			if (option != null) {
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setSrNo(srNo[i]);
					bean.setOptionName(option[i]);
					bean.setFlagItt(flagItt[i]);
					if (!(questionBank.getQuesCode().equals("") || questionBank
							.getQuesCode().equals("null"))) {
						bean.setOptionCode(code[i]);
					}
					list.add(bean);
				}
				questionBank.setOptionList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateData(QuestionBank questionBank, String[] option,
			String[] flagItt, String opCode[]) {
		boolean result = false;
		try {
			Object[][] dataQue = new Object[1][11];
			dataQue[0][0] = questionBank.getSubjectCode();// qus subject code
			dataQue[0][1] = questionBank.getAnsOptions();// qu estion type Subjective(S) or Objective(O)

			if (questionBank.getLimit().equals("null")
					|| questionBank.getLimit().equals(" ")
					|| questionBank.getLimit().equals("")) {
				dataQue[0][2] = String.valueOf("");
			} else {
				dataQue[0][2] = questionBank.getLimit();
			}
			dataQue[0][3] = String.valueOf("");
			dataQue[0][4] = questionBank.getCompLevel();
			dataQue[0][5] = questionBank.getQuestion();
			dataQue[0][6] = questionBank.getQsnStatus();// qus subject code
			if (questionBank.getCategoryCode().equals("")
					|| questionBank.getCategoryCode().equals("null")) {
				dataQue[0][7] = String.valueOf("");
			} else {
				dataQue[0][7] = questionBank.getCategoryCode();
			}
			dataQue[0][8] = questionBank.getAnsOptions().equals("S") ? questionBank.getUploadFileName() : ""; 
			dataQue[0][9] = questionBank.getAllowToUploadAnswer().equals("true") ? "Y" : "N";
			dataQue[0][10] = questionBank.getQuesCode();
			
			boolean duplicate = chkDuplicateQues(questionBank);
			
			if (!duplicate) {

			// String query = " UPDATE HRMS_REC_QUESBANK set QUES_SUB_CODE
			// =?,QUES_TYPE=?,QUES_LIMIT=?,QUES_MARK=?,QUES_LEVEL=?,QUES_NAME=?
			// where QUES_CODE=? ";
			// boolean result = getSqlModel().singleExecute(query, dataQue);
			result = getSqlModel().singleExecute(getQuery(6), dataQue);

			/*
			 * 
			 * String sel="SELECT QUES_CODE FROM HRMS_REC_QUESOPTION WHERE
			 * QUES_CODE="+questionBank.getQuesCode(); Object[][] data =
			 * getSqlModel().getSingleResult(sel);
			 * logger.info("ddddd"+questionBank.getQuesCode()); Object[][]
			 * addObj=null;//new Object[option.length-data.length][3];
			 * 
			 * 
			 * 
			 * if (result) { if(questionBank.getAnsOptions().equals("S")){
			 * questionBank.setOptionFlag("false"); Object[][] del=new
			 * Object[1][1]; del[0][0]=questionBank.getQuesCode(); String
			 * queryOption="DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE=?";
			 * getSqlModel().singleExecute(queryOption,del); }else{
			 * if(option!=null && option.length>0){ Object[][] update=new
			 * Object[opCode.length][4];
			 * 
			 * for(int i=0;i<option.length;i++){ addObj=new
			 * Object[option.length-data.length][3];
			 * logger.info("opcode"+opCode[i]); String Flg="";
			 * if(opCode[i].equals("") || opCode[i].equals("null")){
			 * if(flagItt[i].equals("Right")){ Flg="Y"; }else{ Flg="N"; } String
			 * optionQuery="INSERT INTO
			 * HRMS_REC_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC,OPTION_ANS_FLAG) " +"
			 * VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM
			 * HRMS_REC_QUESOPTION),"+questionBank.getQuesCode()+",'"+option[i]+"','"+Flg+"') ";
			 * getSqlModel().singleExecute(optionQuery);
			 * 
			 * }else{ if(flagItt[i].equals("Right")){
			 * update[i][0]=String.valueOf("Y"); }else{
			 * update[i][0]=String.valueOf("N"); } update[i][1]=option[i];
			 * update[i][2]=opCode[i]; update[i][3]=questionBank.getQuesCode(); }
			 * 
			 *  } getSqlModel().singleExecute(getQuery(4),addObj);
			 * getSqlModel().singleExecute(getQuery(5),update);
			 *  } } }
			 * 
			 * 
			 * 
			 */

			/* new Adding */
			if (result) {

				Object[][] data = new Object[1][1];

				data[0][0] = questionBank.getQuesCode();

				String queryOption = "DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE=?";
				getSqlModel().singleExecute(queryOption, data);

				questionBank.setQuesCode(String.valueOf(data[0][0]));

				if (questionBank.getAnsOptions().equals("S")) {
					questionBank.setOptionFlag("false");
				} else {
					questionBank.setOptionFlag("true");

					if (option != null) {
						String Flg = "";
						for (int i = 0; i < option.length; i++) {
							if (flagItt[i].equalsIgnoreCase("Yes")) {
								Flg = "Y";
							} else {
								Flg = "N";
							}
							String optionQuery = "INSERT INTO HRMS_REC_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC,OPTION_ANS_FLAG) "
									+ " VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM HRMS_REC_QUESOPTION),?"
									//+ data[0][0]
									+ ",?"
									//+ option[i]
									+ ",?"
									//+ Flg 
									+ ") ";
							
							Object[][] paramObj = new Object[1][3];
							paramObj[0][0] = data[0][0];
							paramObj[0][1] = option[i];
							paramObj[0][2] = Flg; 
							
							result = getSqlModel().singleExecute(optionQuery, paramObj);
						}

					}
				}

			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteQuestion(QuestionBank questionBank) {
		Object[][] data = new Object[1][1];
		data[0][0] = questionBank.getHiddenCode();
		boolean flag = getSqlModel().singleExecute(getQuery(2), data);
		boolean flag1 = getSqlModel().singleExecute(getQuery(3), data);
		if (flag && flag1) {
			return flag;
		} else {
			return false;
		}
	}

	public void getOptionRec(QuestionBank questionBank, String[] flag,
			String[] optionName) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			if (optionName != null) {
				for (int i = 0; i < optionName.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setOptionName(optionName[i]);
					bean.setFlagItt(flag[i]);
					list.add(bean);
				}
			}
			questionBank.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteOption(QuestionBank questionBank, String[] code,
			String[] option, String[] flagItt, String[] del) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			String ansVar = "";
			String optionVar = "";
			if (option != null) {
				questionBank.setOptionFlag("true");
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					if ((i + 1 != Integer.parseInt(questionBank.getParaId()))) {
						ansVar += option[i] + ",";
						if (flagItt[i].equals("Right")) {
							optionVar += "Y" + ",";
						} else {
							optionVar += "N" + ",";
						}
						bean.setOptionName(option[i]);
						/*if(bean.getOptionName().length() > 25) {
							bean.setQuesChoiceLen(bean.getOptionName().substring(0, 20) + "....");
						} else {
							bean.setQuesChoiceLen(bean.getOptionName());
						}*/
						
						if(option[i].length()>25)
						 {
							bean.setQuesChoiceLen(option[i].substring(0,20));
						}
						else{
							bean.setQuesChoiceLen(option[i]);
						}
						bean.setFlagItt(flagItt[i]);
						if (!(questionBank.getQuesCode().equals("") || questionBank
								.getQuesCode().equals("null"))) {
							bean.setOptionCode(code[i]);
						}
						list.add(bean);
					}
				}
			}
			questionBank.setOptionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editForOption(QuestionBank questionBank, String[] srNo,
			String[] option, String[] flagItt) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			if (option != null) {
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setOptionName(option[i]);
					bean.setFlagItt(flagItt[i]);
					list.add(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewForQuestion(QuestionBank questionBank, String[] quesDesc) {
		try {
			String Query = "select HRMS_QUES_OPTION.QUES_CODE,OPTION_CODE,OPTION_DESC,OPTION_ANS_FLAG,QUES_LEVEL,QUES_IMAGE,QUES_MARK,SUBJECT_NAME from HRMS_QUES_OPTION"
					+ " left JOIN HRMS_QUESTION_BANK  ON(HRMS_QUESTION_BANK.QUES_CODE = HRMS_QUES_OPTION.QUES_CODE)"
					+ " left JOIN HRMS_TEST_SUBJECT  ON(HRMS_TEST_SUBJECT.SUBJECT_CODE = HRMS_QUESTION_BANK.QUES_SUB_CODE)"
					+ " WHERE HRMS_QUES_OPTION.QUES_CODE ="
					+ questionBank.getQuestionCode();
			Object[][] dataOption = getSqlModel().getSingleResult(Query);
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < dataOption.length; i++) {
				QuestionBank bean = new QuestionBank();
				bean.setOptionName(String.valueOf(dataOption[i][2]));
				if (String.valueOf(dataOption[i][3]).equals("Y")) {
					bean.setFlagItt("true");
				} else {
					bean.setFlagItt("false");
				}
				questionBank.setLevel(String.valueOf(dataOption[i][4]));
				questionBank.setUpload(checkNull(String
						.valueOf(dataOption[i][5])));
				questionBank.setMark(String.valueOf(dataOption[i][6]));
				questionBank.setSubject(String.valueOf(dataOption[i][7]));
				list.add(bean);
			}
			questionBank.setCheckFlag_cs("false");
			questionBank.setOptionList(list);
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
	}

	public void questionData(QuestionBank questionBank,
			HttpServletRequest request) {
		try {

			String query = "SELECT HRMS_REC_SUBJECT.SUBJECT_NAME,NVL(QUES_NAME,' '),QUES_MARK,QUES_CODE, "
					+ "CASE WHEN QUES_STATUS='A' THEN 'Active' ELSE 'Deactive' END,CAT_NAME,QUES_CAT_CODE,CASE WHEN QUES_TYPE='O' THEN 'Objective' ELSE 'Subjective' END FROM HRMS_REC_QUESBANK  "
					+ "left join HRMS_REC_SUBJECT on (HRMS_REC_QUESBANK.QUES_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE) "
					+ " left JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"
					
					+ "ORDER BY QUES_CODE  ";
			Object[][] repData = getSqlModel().getSingleResult(query);

			if (repData != null && repData.length > 0 ) {
				String[] pageIndex = Utility.doPaging(questionBank.getMyPage(),
						repData.length, 20);
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
					questionBank.setMyPage("1");

				if (repData != null && repData.length > 0) {
					questionBank.setNoData("true");
					ArrayList<Object> list1 = new ArrayList<Object>();

					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						QuestionBank bean1 = new QuestionBank();
						bean1.setQuesCodeItt(String.valueOf(repData[i][3]));
						bean1.setSubject(String.valueOf(repData[i][0]));
						bean1.setQuestion(String.valueOf(repData[i][1]));
						if (String.valueOf(repData[i][1]).equals("null")
								|| String.valueOf(repData[i][1]).equals("")) {
							bean1.setQsnWtg("");
						} else {
							bean1.setQsnWtg(String.valueOf(repData[i][2]));
						}
						bean1.setQsnStatus(String.valueOf(repData[i][4]));
						bean1.setCategory(checkNull(String
								.valueOf(repData[i][5])));
						bean1.setCategoryCode(checkNull(String
								.valueOf(repData[i][6])));
						bean1.setAnsOptions(String.valueOf(repData[i][7]));
						list1.add(bean1);

					}
					questionBank.setQuestionList(list1);
					questionBank.setTotRec(String.valueOf(Integer
							.parseInt(String.valueOf(repData.length))));
				}
			} else {
				questionBank.setNoData("false");
				request.setAttribute("totalPage", 1);
				request.setAttribute("PageNo", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getCategoryQuestions(QuestionBank questionBank,	HttpServletRequest request) {
		try {

			String query = "SELECT HRMS_REC_SUBJECT.SUBJECT_NAME, NVL(QUES_NAME,' '), QUES_MARK, QUES_CODE, "
					+ " CASE WHEN QUES_STATUS='A' THEN 'Yes' ELSE 'No' END,"
					+ " CAT_NAME, QUES_CAT_CODE,"
					+ " CASE WHEN QUES_TYPE='O' THEN 'Objective' ELSE 'Subjective' END,"
					+ " HRMS_REC_SUBJECT.SUBJECT_CODE"
					+ " FROM HRMS_REC_QUESBANK  "
					+ " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE) "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON (HRMS_REC_CATEGORY.CAT_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE)";
					if(!questionBank.getCategoryCode().equals("") && questionBank.getCategoryCode() != null){
						query  += " WHERE HRMS_REC_CATEGORY.CAT_CODE = " + questionBank.getCategoryCode();
					}
					query  += " ORDER BY QUES_CODE  ";
			Object[][] repData = getSqlModel().getSingleResult(query);

			if (repData != null && repData.length > 0 ) {
				String[] pageIndex = Utility.doPaging(questionBank.getMyPage(),	repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					questionBank.setMyPage("1");

				if (repData != null && repData.length > 0) {
					questionBank.setNoData("true");
					ArrayList<Object> list1 = new ArrayList<Object>();

					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						QuestionBank bean1 = new QuestionBank();
						bean1.setQuesCodeItt(String.valueOf(repData[i][3]));
						bean1.setSubject(String.valueOf(repData[i][0]));
						bean1.setQuestion(String.valueOf(repData[i][1]));
						if(bean1.getQuestion().length() > 50) {
							bean1.setQuestionAbbr(bean1.getQuestion().substring(0, 47) + "....");
						} else {
							bean1.setQuestionAbbr(bean1.getQuestion());
						}
						
						
						if (String.valueOf(repData[i][1]).equals("null")
								|| String.valueOf(repData[i][1]).equals("")) {
							bean1.setQsnWtg("");
						} else {
							bean1.setQsnWtg(String.valueOf(repData[i][2]));
						}
						bean1.setQsnStatus(String.valueOf(repData[i][4]));
						bean1.setCategory(checkNull(String.valueOf(repData[i][5])));
						bean1.setCategoryCode(checkNull(String.valueOf(repData[i][6])));
						bean1.setAnsOptions(String.valueOf(repData[i][7]));
						list1.add(bean1);

					}
					questionBank.setQuestionList(list1);
					questionBank.setTotRec(String.valueOf(Integer
							.parseInt(String.valueOf(repData.length))));
					questionBank.setCategory(checkNull(String.valueOf(repData[0][5])));
					questionBank.setSubject(String.valueOf(repData[0][0]));
					questionBank.setSubjectCode(String.valueOf(repData[0][8]));
				}
			} else {
				
				query = " SELECT HRMS_REC_SUBJECT.SUBJECT_NAME, HRMS_REC_CATEGORY.CAT_NAME, HRMS_REC_SUBJECT.SUBJECT_CODE"
					+ " FROM HRMS_REC_CATEGORY"
					+ " LEFT JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE=HRMS_REC_CATEGORY.CAT_SUB_CODE)"
					+ " WHERE HRMS_REC_CATEGORY.CAT_CODE = " + questionBank.getCategoryCode();
				Object[][] data = getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0 ) {
					questionBank.setSubject(String.valueOf(data[0][0]));
					questionBank.setCategory(checkNull(String.valueOf(data[0][1])));
					questionBank.setSubjectCode(String.valueOf(data[0][2]));
				}
				
				questionBank.setNoData("false");
				request.setAttribute("totalPage", 1);
				request.setAttribute("PageNo", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getSubjectQuestions(QuestionBank questionBank,
			HttpServletRequest request) {
		try {

			String query = " SELECT HRMS_REC_SUBJECT.SUBJECT_NAME,NVL(QUES_NAME,' '),QUES_MARK,QUES_CODE, "
					+ " CASE WHEN QUES_STATUS='A' THEN 'Yes' ELSE 'No' END,'','',"
					+ " CASE WHEN QUES_TYPE='O' THEN 'Objective' ELSE 'Subjective' END"
					+ " FROM HRMS_REC_QUESBANK "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON (HRMS_REC_QUESBANK.QUES_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE) ";
					//+ " LEFT JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"
					if(!questionBank.getSubjectCode().equals("") && questionBank.getSubjectCode() != null){
						query  += " WHERE HRMS_REC_SUBJECT.SUBJECT_CODE=" + questionBank.getSubjectCode()
									+" AND QUES_CAT_CODE IS NULL";
					}
					query  +=  " ORDER BY QUES_CODE  ";
			Object[][] repData = getSqlModel().getSingleResult(query);

			if (repData != null && repData.length > 0 ) {
				String[] pageIndex = Utility.doPaging(questionBank.getMyPage(), repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					questionBank.setMyPage("1");

				if (repData != null && repData.length > 0) {
					questionBank.setNoData("true");
					ArrayList<Object> list1 = new ArrayList<Object>();

					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						QuestionBank bean1 = new QuestionBank();
						bean1.setQuesCodeItt(String.valueOf(repData[i][3]));
						bean1.setSubject(String.valueOf(repData[i][0]));
						bean1.setQuestion(String.valueOf(repData[i][1]));
						if(bean1.getQuestion().length() > 50) {
							bean1.setQuestionAbbr(bean1.getQuestion().substring(0, 47) + "....");
						} else {
							bean1.setQuestionAbbr(bean1.getQuestion());
						}
						
						
						if (String.valueOf(repData[i][1]).equals("null")
								|| String.valueOf(repData[i][1]).equals("")) {
							bean1.setQsnWtg("");
						} else {
							bean1.setQsnWtg(String.valueOf(repData[i][2]));
						}
						bean1.setQsnStatus(String.valueOf(repData[i][4]));
						bean1.setCategory(checkNull(String.valueOf(repData[i][5])));
						bean1.setCategoryCode(checkNull(String.valueOf(repData[i][6])));
						bean1.setAnsOptions(String.valueOf(repData[i][7]));
						list1.add(bean1);

					}
					questionBank.setQuestionList(list1);
					questionBank.setTotRec(String.valueOf(Integer.parseInt(String.valueOf(repData.length))));
					questionBank.setSubject(String.valueOf(repData[0][0]));
				}
			} else {
				
				query = " SELECT HRMS_REC_SUBJECT.SUBJECT_NAME FROM HRMS_REC_SUBJECT "
					+ " WHERE HRMS_REC_SUBJECT.SUBJECT_CODE=" + questionBank.getSubjectCode();
				Object[][] data = getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0 ) {
					questionBank.setSubject(String.valueOf(data[0][0]));
				}
					
				questionBank.setNoData("false");
				request.setAttribute("totalPage", 1);
				request.setAttribute("PageNo", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public boolean deletecheckedRecords(QuestionBank bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					getSqlModel().singleExecute(getQuery(2), delete);
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return true;
		}
	}

	public boolean deleteoptions(QuestionBank bean, String[] code) {
		boolean result = false;
		try {
			if (code != null) {
				for (int i = 0; i < code.length; i++) {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						getSqlModel().singleExecute(getQuery(2), delete);
						result = getSqlModel().singleExecute(getQuery(3),
								delete);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void generateReport(QuestionBank questionBank,
			HttpServletResponse response, String[] label) {
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName = "QuestionBank Master";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", reportName);
			rg.setFName("QuestionBank Master.Pdf");
			String queryDes = "SELECT HRMS_REC_SUBJECT.SUBJECT_NAME,NVL(QUES_NAME,' '),CASE WHEN QUES_STATUS='A' THEN 'Active' "
					+ "ELSE 'Deactive' END FROM HRMS_REC_QUESBANK left join HRMS_REC_SUBJECT on "
					+ "(HRMS_REC_QUESBANK.QUES_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE) "
					+ "ORDER BY QUES_CODE";
			Object[][] data = getSqlModel().getSingleResult(queryDes);
			Object[][] Data = new Object[data.length][4];
			if (data != null && data.length > 0) {
				int j = 1;
				for (int i = 0; i < data.length; i++) {
					Data[i][0] = j;
					Data[i][1] = data[i][0];
					Data[i][2] = data[i][1];
					Data[i][3] = data[i][2];
					j++;
				}
				int cellwidth[] = { 5, 20, 20, 10 };
				int alignment[] = { 1, 0, 0, 0 };
				rg.addTextBold("QuestionBank Master", 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addTextBold("Date :" + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(label, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean chkDuplicateQues(QuestionBank questionBank) {
		boolean quesResult = false;
		String quesDupQuery = "SELECT NVL(QUES_NAME,' '), QUES_CODE"
				+ " FROM HRMS_REC_QUESBANK "
				+ " LEFT JOIN HRMS_REC_SUBJECT ON (HRMS_REC_QUESBANK.QUES_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE)"
				+ " WHERE UPPER(QUES_NAME) LIKE '"
				+ questionBank.getQuestion().trim().toUpperCase()
				+ "' AND HRMS_REC_SUBJECT.SUBJECT_CODE= "
				+ questionBank.getSubjectCode() + " AND QUES_STATUS='A'";
		
		if (questionBank.getFromFlag().equals("Subject")
				|| questionBank.getFromFlag().equals("SubjectList")) {
			quesDupQuery = quesDupQuery + " AND QUES_CAT_CODE IS NULL";
		} else {
			quesDupQuery = quesDupQuery + " AND QUES_CAT_CODE="
					+ questionBank.getCategoryCode();
		}
		if(!questionBank.getQuesCode().equals("")){
			quesDupQuery = quesDupQuery + " AND QUES_CODE NOT IN (" + questionBank.getQuesCode() + ")";
		}
		Object[][] quesDupObj = getSqlModel().getSingleResult(quesDupQuery);

		if (quesDupObj != null && quesDupObj.length > 0) {
			quesResult = true;
		}
		return quesResult;
	}
}
