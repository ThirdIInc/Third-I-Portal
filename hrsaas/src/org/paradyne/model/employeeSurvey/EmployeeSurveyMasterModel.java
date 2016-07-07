/**
 * 
 */
package org.paradyne.model.employeeSurvey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyMaster;
import org.paradyne.lib.ModelBase;

/**
 * @author Reeba_Joseph
 *
 */
public class EmployeeSurveyMasterModel extends ModelBase {

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(EmployeeSurveyMaster surveyMaster) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_EMPSURVEY_MASTER WHERE UPPER(SURVEY_NAME) LIKE '"
				+ surveyMaster.getSurveyName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(EmployeeSurveyMaster surveyMaster) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_EMPSURVEY_MASTER WHERE UPPER(SURVEY_NAME) LIKE '"
				+ surveyMaster.getSurveyName().trim().toUpperCase()
				+ "' AND SURVEY_CODE NOT IN(" + surveyMaster.getSurveyCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/**
	 * 
	 * @param surveyMaster
	 * @return boolean
	 */
	public boolean saveSurvey(EmployeeSurveyMaster surveyMaster, String[] section) {
		if (!checkDuplicate(surveyMaster)) {
			String query = "SELECT NVL(MAX(SURVEY_CODE),0)+1 FROM  HRMS_EMPSURVEY_MASTER";
			Object[][] code = getSqlModel().getSingleResult(query);

			Object[][] saveObj = new Object[1][4];
			saveObj[0][0] = code[0][0];
			saveObj[0][1] = surveyMaster.getSurveyName().trim();
			saveObj[0][2] = surveyMaster.getFrmDate().trim();
			saveObj[0][3] = surveyMaster.getToDate().trim();

			String insertQuery = " INSERT INTO HRMS_EMPSURVEY_MASTER (SURVEY_CODE, SURVEY_NAME, SURVEY_START_DATE, SURVEY_END_DATE) VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY'))";
			boolean result =  getSqlModel().singleExecute(insertQuery, saveObj);
			if (result) {
				// surveyQuestionnaire.setQuestionView("true");
				String maxQuery = "SELECT MAX(SURVEY_CODE) FROM HRMS_EMPSURVEY_MASTER";
				Object[][] data = getSqlModel().getSingleResult(maxQuery);
				if (data != null && data.length > 0) {
					surveyMaster.setSurveyCode(String.valueOf(data[0][0]));
					if (section != null) {
						for (int i = 0; i < section.length; i++) {
							String sectionQuery = "INSERT INTO HRMS_EMPSURVEY_MASTERDTL(SECTION_CODE,SURVEY_CODE,SECTION_NAME) "
									+ " VALUES ((SELECT NVL(MAX(SECTION_CODE),0)+1 FROM HRMS_EMPSURVEY_MASTERDTL),"
									+ data[0][0] + ",'" + section[i] + "') ";
							result = getSqlModel().singleExecute(sectionQuery);
						}
					}
				}
			}
			return result;

		}// end of if
		else {
			return false;
		}// end of else
		
	}
	
	public boolean updateSurvey(EmployeeSurveyMaster surveyMaster, String[] section) {
		if (!checkDuplicateMod(surveyMaster)) {

			Object[][] updateObj = new Object[1][4];
			updateObj[0][0] = surveyMaster.getSurveyName().trim();
			updateObj[0][1] = surveyMaster.getFrmDate().trim();
			updateObj[0][2] = surveyMaster.getToDate().trim();
			updateObj[0][3] = surveyMaster.getSurveyCode();

			String updateQuery = " UPDATE HRMS_EMPSURVEY_MASTER SET SURVEY_NAME=?, SURVEY_START_DATE=TO_DATE(?, 'DD-MM-YYYY'), " +
					" SURVEY_END_DATE=TO_DATE(?, 'DD-MM-YYYY') WHERE SURVEY_CODE=?  ";
			boolean result =  getSqlModel().singleExecute(updateQuery, updateObj);
			if (result) {
				Object[][] data = new Object[1][1];
				data[0][0] = surveyMaster.getSurveyCode();
				String queryOption = "DELETE FROM HRMS_EMPSURVEY_MASTERDTL WHERE SURVEY_CODE=?";
				getSqlModel().singleExecute(queryOption, data);
				surveyMaster.setSurveyCode(String.valueOf(data[0][0]));

				if (section != null) {
					for (int i = 0; i < section.length; i++) {
						String optionQuery = "INSERT INTO HRMS_EMPSURVEY_MASTERDTL(SECTION_CODE,SURVEY_CODE,SECTION_NAME) "
								+ " VALUES ((SELECT NVL(MAX(SECTION_CODE),0)+1 FROM HRMS_EMPSURVEY_MASTERDTL),"
								+ data[0][0] + ",'" + section[i] + "') ";
						result = getSqlModel().singleExecute(optionQuery);
					}
				}
			}
			return result;
		}// end of if
		else {
			return false;
		}// end of else
		
	}

	public boolean deleteSurvey(EmployeeSurveyMaster surveyMaster) {
		Object deleteObj[][] = new Object[1][1];
		deleteObj[0][0] = surveyMaster.getSurveyCode();
		String queryOption = "DELETE FROM HRMS_EMPSURVEY_MASTERDTL WHERE SURVEY_CODE=?";
		boolean flag = getSqlModel().singleExecute(queryOption, deleteObj);
		boolean result = false;
		if (flag) {
			String delQuery = " DELETE FROM HRMS_EMPSURVEY_MASTER WHERE SURVEY_CODE = ?  ";
			result = getSqlModel().singleExecute(delQuery, deleteObj);
		}
		return result;
	}

	public boolean chkDuplicate(EmployeeSurveyMaster surveyMaster,
			String[] srNo, String[] section, String[] code,
			HttpServletRequest request) {
		int count = 0;

		if (section != null && section.length > 0) {
			for (int i = 0; i < section.length; i++) {

				if (surveyMaster.getSectionName().trim()
						.equalsIgnoreCase(section[i].trim())) {
					count = 1;
					break;
				} else {
					count = 0;
				}
			}
		}

		ArrayList<Object> list = new ArrayList<Object>();

		if (section != null) {
			for (int i = 0; i < section.length; i++) {
				EmployeeSurveyMaster bean = new EmployeeSurveyMaster();
				bean.setSrNo(srNo[i]);
				bean.setSectionNameItt(section[i]);
				if (!(surveyMaster.getSurveyCode().equals("") || surveyMaster
						.getSurveyCode().equals("null"))) {
					bean.setSectionCodeItt(code[i]);
				}
				list.add(bean);
			}
		}

		surveyMaster.setSectionList(list);
		if (count == 0)
			return false;
		else
			return true;

	}

	public void addOption(EmployeeSurveyMaster surveyMaster, String[] srNo,
			String[] section, String[] code, HttpServletRequest request) {
		ArrayList<Object> list = new ArrayList<Object>();
		if (section != null) {
			for (int i = 0; i < section.length; i++) {
				EmployeeSurveyMaster bean = new EmployeeSurveyMaster();
				bean.setSrNo(srNo[i]);
				bean.setSectionNameItt(section[i]);
				if (!(surveyMaster.getSurveyCode().equals("") || surveyMaster
						.getSurveyCode().equals("null"))) {
					bean.setSectionCodeItt(code[i]);
				}
				list.add(bean);
			}
		}
		EmployeeSurveyMaster qb1 = new EmployeeSurveyMaster();
		System.out
				.println("surveyQuestionnaire.getOptionTextarea()================ "
						+ surveyMaster.getSectionName());
		String sectionName = request.getParameter("sectionName");

		System.out.println("request.getParameter .getSectionName()================ "
				+ sectionName);
		qb1.setSrNo(String.valueOf(list.size() + 1));
		qb1.setSectionNameItt(sectionName);
		list.add(qb1);

		surveyMaster.setSectionList(list);

	}

	public void editOption(EmployeeSurveyMaster surveyMaster, String[] srNo,
			String[] section, String[] code, HttpServletRequest request) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			for (int i = 0; i < section.length; i++) {

				if ((i + 1 != Integer.parseInt(surveyMaster.getParaId()))) {
					EmployeeSurveyMaster bean1 = new EmployeeSurveyMaster();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setSectionNameItt(section[i]);
					if (!(surveyMaster.getSurveyCode().equals("") || surveyMaster
							.getSurveyCode().equals("null"))) {
						bean1.setSectionCodeItt(code[i]);
					}
					tableList.add(bean1);
				} // end of if
				else {

					EmployeeSurveyMaster qb1 = new EmployeeSurveyMaster();

					qb1.setSectionNameItt(surveyMaster.getSectionName());
					if (!(surveyMaster.getSectionId().equals(""))) {
						qb1.setSectionCodeItt(surveyMaster.getSectionId());
					}

					tableList.add(qb1);
				} // end of else
			} // end of for loop
			surveyMaster.setSectionList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteOption(EmployeeSurveyMaster surveyMaster, String[] code,
			String[] section, String[] delete) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String ansVar = "";
			if (section != null) {
				surveyMaster.setSectionFlag("true");
				for (int i = 0; i < section.length; i++) {
					System.out.println("val of" + delete[i]);
					EmployeeSurveyMaster bean = new EmployeeSurveyMaster();

					if ((i + 1 != Integer.parseInt(surveyMaster
							.getParaId()))) {
						ansVar += section[i] + ",";
						bean.setSectionNameItt(section[i]);
						if (!(surveyMaster.getSurveyCode().equals("") || surveyMaster
								.getSurveyCode().equals("null"))) {
							bean.setSectionCodeItt(code[i]);
						}
						list.add(bean);
					}
				}
			}
			surveyMaster.setSectionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSectionDet(EmployeeSurveyMaster surveyMaster) {
		String query = "SELECT SECTION_NAME,SECTION_CODE FROM HRMS_EMPSURVEY_MASTERDTL LEFT JOIN "
				+ " HRMS_EMPSURVEY_MASTER ON(HRMS_EMPSURVEY_MASTER.SURVEY_CODE=HRMS_EMPSURVEY_MASTERDTL.SURVEY_CODE)"
				+ " WHERE HRMS_EMPSURVEY_MASTERDTL.SURVEY_CODE="
				+ surveyMaster.getSurveyCode() + " ORDER BY SECTION_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			surveyMaster.setSectionFlag("true");
			for (int i = 0; i < data.length; i++) {
				EmployeeSurveyMaster bean = new EmployeeSurveyMaster();
				bean.setSectionNameItt(String.valueOf(data[i][0]));
				bean.setSectionCodeItt(String.valueOf(data[i][1]));
				list.add(bean);
			}
		}
		surveyMaster.setSectionList(list);
	}

}
