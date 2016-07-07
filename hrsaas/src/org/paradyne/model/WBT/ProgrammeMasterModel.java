package org.paradyne.model.WBT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.CLOB;

import org.paradyne.bean.WBT.ProgrammeMaster;
import org.paradyne.bean.exam.ExamMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

import common.Logger;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class ProgrammeMasterModel extends ModelBase {

	/*This method is used to save Programme Records*/
	public boolean save(ProgrammeMaster programBean, String[] mod,
			String[] modCode, String[] isOrder, String[] order,
			String[] content, String[] question, String[] passMod, String[] noOfAttempts) {
		boolean queryResult = false;
		try {			
			String maxQuery = " SELECT NVL(MAX(PROGRAM_ID), 0)+1 FROM WBT_PROGRAM_HDR";
			Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
			Object insertObj[][] = new Object[1][6];
			insertObj[0][0] = maxObj[0][0];
			insertObj[0][1] = programBean.getProgrameName();
			insertObj[0][2] = programBean.getType();
			insertObj[0][3] = programBean.getDuration();
			insertObj[0][4] = programBean.getDays();
			if (programBean.getIsActive().equals("true")) {
				insertObj[0][5] = "Y"; // is active
			} else {
				insertObj[0][5] = "N";
			}
			if (insertObj != null && insertObj.length > 0) {
				queryResult = getSqlModel().singleExecute(getQuery(1),
						insertObj);
			}
			if (queryResult) {
				if (mod != null && mod.length > 0) {
					for (int i = 0; i < mod.length; i++) {
						if (content[i].equals("")) {
							content[i] = "N";
						}
						if (question[i].equals("")) {
							question[i] = "N";
						}
						if (isOrder[i].equals("")) {
							isOrder[i] = "N";
						}
						if (passMod[i].equals("")) {
							passMod[i] = "0";
						}
						if (noOfAttempts[i].equals("")) {
							noOfAttempts[i] = "1";
						}
						
						String insertQuery = "INSERT INTO WBT_PROGRAM_DTL (PROGRAM_ID, PROGRAM_MODULE_CODE, IS_PROGRAM_ORDER,"
								+ " PROGRAM_ORDER, IS_PROGRAM_CONTENT, IS_PROGRAM_QUES) VALUES ("
								+ maxObj[0][0]
								+ " , "
								+ String.valueOf(modCode[i])
								+ " , '"
								+ String.valueOf(isOrder[i])
								+ "' , "
								+ String.valueOf(order[i])
								+ " , '"
								+ String.valueOf(content[i])
								+ "' , '"
								+ String.valueOf(question[i]) + "' )";
						queryResult = getSqlModel().singleExecute(insertQuery);
					}
				}
			}
			programBean.setProgrameId(maxObj[0][0].toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult;
	}

	/*It Provides functionality to update programme and Module Records*/
	public boolean update(ProgrammeMaster programBean, String[] mod,
			String[] modCode, String[] isOrder, String[] order,
			String[] content, String[] question, String[] passMod, String[] noOfAttempts) {
		boolean result = false;
		try {
			Object[][] updateObj = new Object[1][6];
			updateObj[0][0] = programBean.getProgrameName();
			updateObj[0][1] = programBean.getType();
			updateObj[0][2] = programBean.getDuration();
			updateObj[0][3] = programBean.getDays();
			if (programBean.getIsActive().equals("true")) {
				updateObj[0][4] = "Y"; // is active
			} else {
				updateObj[0][4] = "N";
			}
			updateObj[0][5] = programBean.getProgrameId();

			result = getSqlModel().singleExecute(getQuery(5), updateObj);
			if (result) {
				String deleteQuery = "DELETE FROM WBT_PROGRAM_DTL WHERE PROGRAM_ID="
						+ programBean.getProgrameId();
				getSqlModel().singleExecute(deleteQuery);
				if (mod != null && mod.length > 0) {
					for (int i = 0; i < mod.length; i++) {
					
						String insertQuery = "INSERT INTO WBT_PROGRAM_DTL (PROGRAM_ID, PROGRAM_MODULE_CODE, IS_PROGRAM_ORDER,"
								+ " PROGRAM_ORDER, IS_PROGRAM_CONTENT, IS_PROGRAM_QUES, PROGRAM_PASS_MARKS, MODULE_NO_OF_ATTEMPT) VALUES ("
								+ programBean.getProgrameId()
								+ " , "
								+ String.valueOf(modCode[i])
								+ " , '"
								+ String.valueOf(isOrder[i])
								+ "' , "
								+ String.valueOf(order[i])
								+ " , '"
								+ String.valueOf(content[i])
								+ "' , '"
								+ String.valueOf(question[i]) + "' ,"
								+ passMod[i] 
								+ ", "
								+ noOfAttempts[i]
								+ ")";
					result = getSqlModel().singleExecute(insertQuery);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*It Provides functionality to set programme and Module Records*/
	public void getProgramList(HttpServletRequest request,
			ProgrammeMaster programBean) {
		Object[][] outData = getSqlModel().getSingleResult(getQuery(2));
		if (outData != null && outData.length > 0) {
			programBean.setModeLength("true");
			programBean.setTotalRecords(String.valueOf(outData.length));

			String[] pageIndex = Utility.doPaging(programBean.getMyPage(),
					outData.length, 20);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of
			// page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				programBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ProgrammeMaster bean = new ProgrammeMaster();
				bean.setProgrameId(checkNull(String.valueOf(outData[i][0])));
				bean.setProgrameName(checkNull(String.valueOf(outData[i][1])));
				bean.setType(checkNull(String.valueOf(outData[i][2])));
				bean.setDuration(checkNull(String.valueOf(outData[i][3])));
				bean.setIsActive(checkNull(String.valueOf(outData[i][4])));
				List.add(bean);
			}
			programBean.setProgrammeList(List);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/*This method is used to set Programme and Module Records*/
	public void getRecord(HttpServletRequest request, ProgrammeMaster bean) {
		try {
			Object[] paramObj = new Object[1];
			paramObj[0] = bean.getProgrameId();
			Object[][] progData = getSqlModel().getSingleResult(getQuery(3),
					paramObj);
			if (progData != null && progData.length > 0) {
				bean.setProgrameId(String.valueOf(progData[0][0]));
				bean.setProgrameName(String.valueOf(progData[0][1]));
				bean.setType(String.valueOf(progData[0][2]));
				bean.setDuration(String.valueOf(progData[0][3]));
				bean.setDays(String.valueOf(progData[0][4]));
				if (String.valueOf(progData[0][5]).equals("Y")) {
					bean.setIsActive("true");
				}
			}
			setModuleIterator(request, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*This method is used to set Module Records in Iterator */
	public void setModuleIterator(HttpServletRequest request,
			ProgrammeMaster bean) {
		try {
			Object[] modParamObj = new Object[1];
			modParamObj[0] = bean.getProgrameId();
			Object[][] moduleObj = getSqlModel().getSingleResult(getQuery(4),
					modParamObj);
			if (moduleObj != null && moduleObj.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = 0; i < moduleObj.length; i++) {
					ProgrammeMaster beanItt = new ProgrammeMaster();
					beanItt.setModuleCodeItt(checkNull(String
							.valueOf(moduleObj[i][0])));
					
					beanItt.setModuleItt(checkNull(String
							.valueOf(moduleObj[i][1])));
					if(beanItt.getModuleItt().length() > 50) {
						beanItt.setModuleNameAbbrItt(beanItt.getModuleItt().substring(0, 47) + "....");
					} else {
						beanItt.setModuleNameAbbrItt(beanItt.getModuleItt());
					}
					
					beanItt.setModuleOrder(checkNull(String
							.valueOf(moduleObj[i][2])));
					beanItt.setHiddenOrderCheck(checkNull(String
							.valueOf(moduleObj[i][3])));
					beanItt.setHiddenContentCheck(checkNull(String
							.valueOf(moduleObj[i][4])));
					beanItt.setHiddenQuesCheck(checkNull(String
							.valueOf(moduleObj[i][5])));
					beanItt.setPassMarksMod(checkNull(String
							.valueOf(moduleObj[i][6])));
					beanItt.setNoOfAttempts(checkNull(String
							.valueOf(moduleObj[i][7])));
					
					String query = "SELECT COUNT(CAT_CODE) FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE =" + beanItt.getModuleCodeItt();
					Object[][] sectionCountObj = getSqlModel().getSingleResult(query);
					int sectionCount = 0;
					if (sectionCountObj != null && sectionCountObj.length > 0) {
						sectionCount = Integer.parseInt(checkNull(String.valueOf(sectionCountObj[0][0])));
					} else {
						sectionCount = 0;
					}
					if(sectionCount > 0)
						beanItt.setManageSectionItt("false");
					else
						beanItt.setManageSectionItt("true");
					
					list.add(beanItt);
				}
				bean.setModuleList(list);
				bean.setModuleListLen(String.valueOf(list.size()));
				request.setAttribute("moduleLtLength", String.valueOf(list
						.size()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*It provides Functionality to add Module into Iterator*/
	public String addModuleList(HttpServletRequest request,
			ProgrammeMaster programBean, String[] srNo, String[] modName,
			String[] modCode, String[] modOrder, String[] orderReq,
			String[] contentReq, String[] enableQue, String[] passMarks) {
		boolean queryResult = false;

		if (programBean.getProgrameId().equals("")) {
			
			if(checkDuplicate(programBean)){
				return "Duplicate";
			}
			
			String maxQuery = " SELECT NVL(MAX(PROGRAM_ID), 0)+1 FROM WBT_PROGRAM_HDR";
			Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);

			programBean.setProgrameId(String.valueOf(maxObj[0][0]));

			Object insertObj[][] = new Object[1][6];
			insertObj[0][0] = maxObj[0][0];
			insertObj[0][1] = programBean.getProgrameName();
			insertObj[0][2] = programBean.getType();
			insertObj[0][3] = programBean.getDuration();
			insertObj[0][4] = programBean.getDays();
			if (programBean.getIsActive().equals("true")) {
				insertObj[0][5] = "Y"; // is active
			} else {
				insertObj[0][5] = "N";
			}
			if (insertObj != null && insertObj.length > 0) {
				queryResult = getSqlModel().singleExecute(getQuery(1),
						insertObj);
			}
		}
		int order = 1;
		/*String sqlQuery = "SELECT NVL(MAX(PROGRAM_ORDER), 0)+1 FROM WBT_PROGRAM_DTL "
				+ " where PROGRAM_ID="
				+ programBean.getProgrameId()
				+ " AND  PROGRAM_MODULE_CODE =" + programBean.getModuleCode();

		Object dataObj[][] = getSqlModel().getSingleResult(sqlQuery);

		if (dataObj != null && dataObj.length > 0) {
			order = Integer.parseInt(String.valueOf(dataObj[0][0]));
		}*/
		
		if(modCode != null){
			order= modCode.length + 1;
		}
		String modInsertQuery = " INSERT INTO  WBT_PROGRAM_DTL(PROGRAM_ID, PROGRAM_MODULE_CODE,PROGRAM_ORDER)values("
				+ programBean.getProgrameId()+ ","
				+ programBean.getModuleCode() + "," + order + ")";
		queryResult = getSqlModel().singleExecute(modInsertQuery);
		setModuleIterator(request, programBean);
		
		return "Success";
	}
	/*It is used to set Section Records on click of Section's Hyperlink */
	public void callSection(HttpServletRequest request,
			ProgrammeMaster programBean, String programID, String moduleID,
			String progName, String modName) {
		try {
			String query = "  SELECT CAT_CODE, NVL(CAT_NAME,''), CAT_ORDER FROM HRMS_REC_CATEGORY "
					+ " WHERE CAT_SUB_CODE="+ moduleID +"  AND CAT_STATUS='Y' "
					+ "ORDER BY CAT_ORDER ";
			Object[][] secObj = getSqlModel().getSingleResult(query);
			if (secObj != null && secObj.length > 0) {
				ArrayList<ProgrammeMaster> list1 = new ArrayList<ProgrammeMaster>();
				for (int i = 0; i < secObj.length; i++) {
					String secIteratorQuery = "SELECT NVL(WBT_PROGRAM_SECTION.IS_SECTION_CONTENT,'N'),"
							+ " NVL(WBT_PROGRAM_SECTION.IS_SECTION_QUES,'N'), NVL(SECTION_PASS_MARKS,0),"
							+ " NVL(WBT_PROGRAM_SECTION.SECTION_ORDER,0)"
							+ " FROM WBT_PROGRAM_SECTION"
							+ " WHERE WBT_PROGRAM_SECTION.MODULE_ID= " + moduleID
							+ " AND WBT_PROGRAM_SECTION.PROGRAM_ID= " + programID
							+ " AND WBT_PROGRAM_SECTION.SECTION_ID= " + String.valueOf(secObj[i][0]);
					
					ProgrammeMaster bean = new ProgrammeMaster();
					bean.setSectionCodeItt(String.valueOf(secObj[i][0]));
					bean.setSectionItt(String.valueOf(secObj[i][1]));
					if(bean.getSectionItt().length() > 50) {
						bean.setSectionAbbrItt(bean.getSectionItt().substring(0, 47) + "....");
					} else {
						bean.setSectionAbbrItt(bean.getSectionItt());
					}		
					
					bean.setPassMarkSecItt("0");
					bean.setHiddenSecContChk("N");
					bean.setHiddenSecQuesChk("N");
					bean.setSectionModuleCode(programBean
							.getSectionModuleCode());
					bean.setSectionOrder(String.valueOf(i + 1));
					Object[][] secIttObj = getSqlModel().getSingleResult(secIteratorQuery);	
					if (secIttObj != null && secIttObj.length > 0) {
						bean.setHiddenSecContChk(String.valueOf(secIttObj[0][0]));
						bean.setHiddenSecQuesChk(String.valueOf(secIttObj[0][1]));
						bean.setPassMarkSecItt(String.valueOf(secIttObj[0][2]));
						bean.setSectionOrder(String.valueOf(secIttObj[0][3]));
					}
					list1.add(bean);
				}
				/*It set Section details according to Section's Order*/
				/*
				List<ProgrammeMaster> list = new ArrayList<ProgrammeMaster>();
				list = list1;
				for (int i = 0; i < list1.size(); i++) {
					for (int j = i+1; j < list1.size(); j++) {
						ProgrammeMaster bean = (ProgrammeMaster) list1.get(i);
						ProgrammeMaster bean1 = (ProgrammeMaster) list1.get(j);
						int order = Integer.parseInt(bean.getSectionOrder());
						int order1 = Integer.parseInt(bean1.getSectionOrder());
						if (order > order1) {
							list.set(j, bean);
							list.set(i, bean1);
							break;
						}
					}					
				}*/
				Collections.sort(list1, sortSectionOrder);
				
				programBean.setSectionList(list1);
				programBean.setSectionListLength(String.valueOf(list1.size()));
				request.setAttribute("sectionListLen", String.valueOf(list1
						.size()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*It provides functionality to save Section Records */
	public boolean saveSection(ProgrammeMaster programBean, String[] sectCode,
			String[] mark, String[] content, String[] question,
			String[] secOrder) {
		boolean queryResult = false;
		try {
			if (sectCode != null && sectCode.length > 0) {
				for (int i = 0; i < sectCode.length; i++) {
					secOrder[i] = String.valueOf(i);
					String insertQuery = "INSERT INTO WBT_PROGRAM_SECTION (PROGRAM_ID, MODULE_ID, SECTION_ID,"
							+ " SECTION_PASS_MARKS, IS_SECTION_CONTENT, IS_SECTION_QUES, SECTION_ORDER) VALUES ("
							+ programBean.getSectionProgrameCode()
							+ " , "
							+ programBean.getSectionModuleCode()
							+ " , "
							+ sectCode[i]
							+ "  ,"
							+ mark[i]
							+ " "
							+ " , '"
							+ content[i]
							+ "' , '"
							+ question[i]
							+ "' ,"
							+ secOrder[i] + ")";
					queryResult = getSqlModel().singleExecute(insertQuery);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult;
	}

	/* This is used to Update Section Records */
	public boolean updateSection(ProgrammeMaster programBean,
			String[] sectCode, String[] mark, String[] content,
			String[] question, String[] secOrder) {
		boolean result = false;

		try {

			if (sectCode != null && sectCode.length > 0) {

				String delQuery = " DELETE FROM WBT_PROGRAM_SECTION WHERE PROGRAM_ID="
						+ programBean.getSectionProgrameCode()
						+ " AND MODULE_ID="
						+ programBean.getSectionModuleCode();
				result = getSqlModel().singleExecute(delQuery);

				
				for (int i = 0; i < sectCode.length; i++) {
					if (secOrder[i] == null || secOrder[i].equals("")
							|| secOrder[i].equals("null")) {
						secOrder[i] = String.valueOf(i + 1);
					}
					String insertQuery = "INSERT INTO WBT_PROGRAM_SECTION (PROGRAM_ID, MODULE_ID, SECTION_ID,"
							+ " SECTION_PASS_MARKS, IS_SECTION_CONTENT, IS_SECTION_QUES, SECTION_ORDER) VALUES ("
							+ programBean.getSectionProgrameCode()
							+ " , "
							+ programBean.getSectionModuleCode()
							+ " , "
							+ sectCode[i]
							+ "  , "
							+ mark[i]
							+ " "
							+ " , '"
							+ content[i]
							+ "' , '"
							+ question[i]
							+ "' ,"
							+ secOrder[i] + ")";
					result = getSqlModel().singleExecute(insertQuery);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* It provides Functionality to set Question's Record on click of 
	 * Manage(Question) link of Module and Programme */
	public void callQuestion(HttpServletRequest request,
			ProgrammeMaster programBean, String programID, String moduleID,
			String progName, String modName, String sectionCode,String sectionName){
		try {
		programBean.setQueProgrameName(progName);
		programBean.setQueProgrameCode(programID);
		programBean.setQueModuleItt(modName);
		programBean.setQueModuleCodeItt(moduleID);
		programBean.setQueSectionItt(sectionName);
		programBean.setQueSectionCodeItt(sectionCode);

		programBean.setTotalQuestion("0");
		programBean.setTotalEasyQuestion("0");
		programBean.setTotalMediumQuestion("0");
		programBean.setTotalHardQuestion("0");
		
		String query = "SELECT PROGRAM_QUES_ID, IS_QUES_RANDOM, NVL(PROGRAM_NO_QUES,0), NVL(MARK_EACH_QUES,0),"
				+ " NVL(PROGRAM_TOTAL_MARKS,0), NVL(PROGRAM_PASS_MARKS,0), IS_EQUAL_MARK_QUES, NVL(MARKS_EASY_QUES,0), "
				+ " NVL(MARKS_MEDIUM_QUES,0), NVL(MARKS_HARD_QUES,0), IS_NEGATIVE_MARK_QUES,  "
				+ " NVL(NEGATIVE_MARK_EASY_QUES,0), NVL(NEGATIVE_MEDIUM_QUES,0), NVL(NEGATIVE_MARK_HARD_QUES,0),"
				+ " IS_EQUAL_MARK_QUES_TYPE "
					+ " FROM WBT_PROGRAM_QUES_HDR  WHERE PROGRAM_ID = "
					+ programID;

		if (programBean.getQuestionFlag() != null
				&& programBean.getQuestionFlag().equals("module")) {
				query += " AND MODULE_ID = " + moduleID
						+ " AND SECTION_ID IS NULL";
		} else {
				query += " AND SECTION_ID = " + sectionCode
						+ " AND MODULE_ID= " + moduleID;
		}
		Object[][] programQueData = getSqlModel().getSingleResult(query);
		if (programQueData != null && programQueData.length > 0) {

			programBean.setProgramQuesID(String
					.valueOf(programQueData[0][0]));
			// programBean.setRandomCheck(String.valueOf(programQueData[0][1]));
			if (checkNull(String.valueOf(programQueData[0][1]))
					.equalsIgnoreCase("Y")) {
				programBean.setRandomCheck("true");
			} else {
				//programBean.setRandomCheck("false");
			}
			programBean.setShowNoQues(String.valueOf(programQueData[0][2]));
			programBean.setEqualEachMark(String
					.valueOf(programQueData[0][3]));
			programBean.setTotalMark(String.valueOf(programQueData[0][4]));
			programBean.setPassQuesMark(String
					.valueOf(programQueData[0][5]));
			// programBean.setEqualEachMark(String.valueOf(programQueData[0][6]));

			if (checkNull(String.valueOf(programQueData[0][6]))
					.equalsIgnoreCase("Y")) {
				programBean.setEqualEachMarkChk("true");
			} else {
				//programBean.setEqualEachMarkChk("false");
			}
			programBean.setEasyMarkQue(String.valueOf(programQueData[0][7]));
			programBean.setMediumMarkQue(String.valueOf(programQueData[0][8]));
			programBean.setHardMarkQue(String.valueOf(programQueData[0][9]));
			// programBean.setEqualNegaMarkType(String.valueOf(programQueData[0][10]));
			if (checkNull(String.valueOf(programQueData[0][10]))
					.equalsIgnoreCase("Y")) {
				programBean.setEqualNegaMarkType("true");
			} else {
				//programBean.setEqualNegaMarkType("false");
			}
			programBean.setEasyNegaMarkQ(String
					.valueOf(programQueData[0][11]));
			programBean.setMediumNegaMarkQ(String
					.valueOf(programQueData[0][12]));
			programBean.setHardNegaMarkQ(String
					.valueOf(programQueData[0][13]));

			if (checkNull(String.valueOf(programQueData[0][14]))
					.equalsIgnoreCase("Y")) {
				programBean.setEqualEachMarkType("true");
			} else {
				//programBean.setEqualEachMarkType("false");
			}
			
			String queryTotalQues = "SELECT COUNT(WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " FROM WBT_PROGRAM_QUES_DTL "
				+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID =" 
				+ programBean.getProgramQuesID();
			Object[][] totalQueData = getSqlModel().getSingleResult(queryTotalQues);
			if (totalQueData != null && totalQueData.length > 0) {
				programBean.setTotalQuestion(checkNull(String.valueOf(totalQueData[0][0])));
				if(programBean.getTotalQuestion().equals("0")){
					String updtPassMarkquery = "UPDATE WBT_PROGRAM_QUES_HDR SET PROGRAM_PASS_MARKS = 0, PROGRAM_TOTAL_MARKS = 0"
							+ " WHERE PROGRAM_ID = " + programID;
					getSqlModel().singleExecute(updtPassMarkquery);
					updtPassMarkquery = "UPDATE WBT_PROGRAM_DTL SET PROGRAM_PASS_MARKS = 0"
						+ " WHERE PROGRAM_ID = " + programID;
					if (programBean.getQuestionFlag() != null
							&& programBean.getQuestionFlag().equals("module")) {
						updtPassMarkquery += " AND PROGRAM_MODULE_CODE = " + moduleID;
					} else {
						updtPassMarkquery = "UPDATE WBT_PROGRAM_SECTION SET SECTION_PASS_MARKS = 0"
							+ " WHERE PROGRAM_ID = " + programID
							+ " AND MODULE_ID = " + moduleID
							+ " AND SECTION_ID = " + sectionCode;
					}
					getSqlModel().singleExecute(updtPassMarkquery);
					
					programBean.setPassQuesMark("0");
					programBean.setTotalEasyQuestion("0");
					programBean.setTotalMediumQuestion("0");
					programBean.setTotalHardQuestion("0");
				}
			} else{
				String updtPassMarkquery = "UPDATE WBT_PROGRAM_QUES_HDR SET PROGRAM_PASS_MARKS = 0, PROGRAM_TOTAL_MARKS = 0"
						+ " WHERE PROGRAM_ID = " + programID;
				getSqlModel().singleExecute(updtPassMarkquery);
				updtPassMarkquery = "UPDATE WBT_PROGRAM_DTL SET PROGRAM_PASS_MARKS = 0"
					+ " WHERE PROGRAM_ID = " + programID;
				if (programBean.getQuestionFlag() != null
						&& programBean.getQuestionFlag().equals("module")) {
					updtPassMarkquery += " AND PROGRAM_MODULE_CODE = " + moduleID;
				} else {
					updtPassMarkquery = "UPDATE WBT_PROGRAM_SECTION SET SECTION_PASS_MARKS = 0"
						+ " WHERE PROGRAM_ID = " + programID
						+ " AND MODULE_ID = " + moduleID
						+ " AND SECTION_ID = " + sectionCode;
				}
				programBean.setPassQuesMark("0");
			}
			String queryTotalLevelQues = "SELECT HRMS_REC_QUESBANK.QUES_LEVEL, COUNT(WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " FROM WBT_PROGRAM_QUES_DTL "
				+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID =" 
				+ programBean.getProgramQuesID()
				+ " GROUP BY HRMS_REC_QUESBANK.QUES_LEVEL"; 
			Object[][] totalQueLevelData = getSqlModel().getSingleResult(queryTotalLevelQues);
			if (totalQueLevelData != null && totalQueLevelData.length > 0) {
				for (int i = 0; i < totalQueLevelData.length; i++) {
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("E")){
						programBean.setTotalEasyQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("M")){
						programBean.setTotalMediumQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("H")){
						programBean.setTotalHardQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}					
				}				
			}

			String queryQues = "SELECT WBT_PROGRAM_QUES_DTL.QUES_CODE, NVL(HRMS_REC_QUESBANK.QUES_NAME,' '), "
					+ " DECODE(WBT_PROGRAM_QUES_DTL.QUES_TYPE,'O','Objective','S','Subjective'),"
					+ " DECODE(HRMS_REC_QUESBANK.QUES_LEVEL,'E','Easy','M','Medium','H','Hard'),"
					+ "  NVL(WBT_PROGRAM_QUES_DTL.QUES_MARKS, 0), NVL(WBT_PROGRAM_QUES_DTL.QUES_ORDER, 0)"
					+ " FROM WBT_PROGRAM_QUES_DTL "
					+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
					+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID = "
					+ programBean.getProgramQuesID()
					+ " ORDER BY WBT_PROGRAM_QUES_DTL.QUES_ORDER";

			Object[][] queData = getSqlModel().getSingleResult(queryQues);
			
				String[] pageIndex = Utility.doPaging(programBean.getMyPage(),
						queData.length, 5);// to display the page number
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "5";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number of
				// page
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1"))
					programBean.setMyPage("1");
				
				
				if (queData != null && queData.length > 0) {
					ArrayList<Object> list = new ArrayList<Object>();
					programBean.setTotalRecords(String.valueOf(queData.length));
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					
					//for (int i = 0; i < queData.length; i++) {
						ProgrammeMaster bean = new ProgrammeMaster();
						bean.setSrNo("" + i);
						bean.setQuesCodeItt(queData[i][0].toString());
						bean.setQuesItt(queData[i][1].toString());
						if(bean.getQuesItt().length() > 50) {
							bean.setQuesAbbrItt(bean.getQuesItt().substring(0, 47) + "....");
						} else {
							bean.setQuesAbbrItt(bean.getQuesItt());
						}
						
						bean.setQuesTypeItt(queData[i][2].toString());
						bean.setQuesLevelItt(queData[i][3].toString());
						bean.setQuesMarkItt(queData[i][4].toString());
						if (queData[i][5].toString().equals("0"))
							bean.setQuesOrderItt("" + i);
						else
							bean.setQuesOrderItt(queData[i][5].toString());

						list.add(bean);
					}
					programBean.setQuestionList(list);
					request.setAttribute("questionListLength", list.size());
				} else {
					programBean.setQuestionList(null);
					programBean.setTotalRecords("0");
				}
			} else {
				/*programBean.setQuestionList(null);
				programBean.setTotalRecords("0");
				String updtPassMarkquery = "UPDATE WBT_PROGRAM_QUES_HDR SET PROGRAM_PASS_MARKS = 0 , PROGRAM_TOTAL_MARKS = 0"
						+ " WHERE PROGRAM_ID = " + programID;
				getSqlModel().singleExecute(updtPassMarkquery);
				programBean.setPassQuesMark("0");
				programBean.setTotalEasyQuestion("0");
				programBean.setTotalMediumQuestion("0");
				programBean.setTotalHardQuestion("0");*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			programBean.setQuestionList(null);
			programBean.setTotalRecords("0");
			programBean.setPassQuesMark("0");
			programBean.setTotalEasyQuestion("0");
			programBean.setTotalMediumQuestion("0");
			programBean.setTotalHardQuestion("0");
		}
	}
	
	
	public void callQuestionPageView(HttpServletRequest request,
			ProgrammeMaster programBean, String programID, String moduleID,
			String progName, String modName, String sectionCode,
			String sectionName) {
		try {
			programBean.setQueProgrameName(progName);
			programBean.setQueProgrameCode(programID);
			programBean.setQueModuleItt(modName);
			programBean.setQueModuleCodeItt(moduleID);
			programBean.setQueSectionItt(sectionName);
			programBean.setQueSectionCodeItt(sectionCode);

			String queryTotalQues = "SELECT COUNT(WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " FROM WBT_PROGRAM_QUES_DTL "
				+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID =" 
				+ programBean.getProgramQuesID();
			Object[][] totalQueData = getSqlModel().getSingleResult(queryTotalQues);
			if (totalQueData != null && totalQueData.length > 0) {
				programBean.setTotalQuestion(checkNull(String.valueOf(totalQueData[0][0])));
			}
			String queryTotalLevelQues = "SELECT HRMS_REC_QUESBANK.QUES_LEVEL, COUNT(WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " FROM WBT_PROGRAM_QUES_DTL "
				+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
				+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID =" 
				+ programBean.getProgramQuesID()
				+ " GROUP BY HRMS_REC_QUESBANK.QUES_LEVEL"; 
			Object[][] totalQueLevelData = getSqlModel().getSingleResult(queryTotalLevelQues);
			if (totalQueLevelData != null && totalQueLevelData.length > 0) {
				for (int i = 0; i < totalQueLevelData.length; i++) {
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("E")){
						programBean.setTotalEasyQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("M")){
						programBean.setTotalMediumQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}
					if(checkNull(String.valueOf(totalQueLevelData[i][0])).equals("H")){
						programBean.setTotalHardQuestion(checkNull(String.valueOf(totalQueLevelData[i][1])));
					}					
				}				
			}
			
			String queryQues = "SELECT WBT_PROGRAM_QUES_DTL.QUES_CODE, NVL(HRMS_REC_QUESBANK.QUES_NAME,' '), "
					+ " DECODE(WBT_PROGRAM_QUES_DTL.QUES_TYPE,'O','Objective','S','Subjective'),"
					+ " DECODE(HRMS_REC_QUESBANK.QUES_LEVEL,'E','Easy','M','Medium','H','Hard'),"
					+ "  NVL(WBT_PROGRAM_QUES_DTL.QUES_MARKS, 0), NVL(WBT_PROGRAM_QUES_DTL.QUES_ORDER, 0)"
					+ " FROM WBT_PROGRAM_QUES_DTL "
					+ " INNER JOIN  HRMS_REC_QUESBANK ON (HRMS_REC_QUESBANK.QUES_CODE = WBT_PROGRAM_QUES_DTL.QUES_CODE)"
					+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID = "
					+ programBean.getProgramQuesID()
					+ " ORDER BY WBT_PROGRAM_QUES_DTL.QUES_ORDER";

			Object[][] queData = getSqlModel().getSingleResult(queryQues);

			String[] pageIndex = Utility.doPaging(programBean.getMyPage(),
					queData.length, 5);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "5";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of
			// page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				programBean.setMyPage("1");

			if (queData != null && queData.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();
				programBean.setTotalRecords(String.valueOf(queData.length));
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					// for (int i = 0; i < queData.length; i++) {
					ProgrammeMaster bean = new ProgrammeMaster();
					bean.setSrNo("" + i);
					bean.setQuesCodeItt(queData[i][0].toString());
					bean.setQuesItt(queData[i][1].toString());
					if (bean.getQuesItt().length() > 50) {
						bean.setQuesAbbrItt(bean.getQuesItt().substring(0, 47)
								+ "....");
					} else {
						bean.setQuesAbbrItt(bean.getQuesItt());
					}

					bean.setQuesTypeItt(queData[i][2].toString());
					bean.setQuesLevelItt(queData[i][3].toString());
					bean.setQuesMarkItt(queData[i][4].toString());
					if (queData[i][5].toString().equals("0"))
						bean.setQuesOrderItt("" + i);
					else
						bean.setQuesOrderItt(queData[i][5].toString());

					list.add(bean);
				}
				programBean.setQuestionList(list);
				request.setAttribute("questionListLength", list.size());
			} else {
				programBean.setQuestionList(null);
				programBean.setTotalRecords("0");
			}

		} catch (Exception e) {
			e.printStackTrace();
			programBean.setQuestionList(null);
			programBean.setTotalRecords("0");
		}
	}
	
	

	/* It is used to Delete Question Record */
	public boolean deleteQuestion(HttpServletRequest request, ProgrammeMaster programBean,
			String[] quesCode, String[] quesName, String[] quesType,
			String[] quesLevel, String[] quesMark, String[] quesOrder) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < quesCode.length; i++) {
				if (!programBean.getDeleteQueCode().equals(quesCode[i])) {
					ProgrammeMaster bean = new ProgrammeMaster();
					bean.setSrNo("" + i);
					bean.setQuesCodeItt(quesCode[i]);
					bean.setQuesItt(quesName[i]);
					bean.setQuesTypeItt(quesType[i]);
					bean.setQuesLevelItt(quesLevel[i]);
					bean.setQuesMarkItt(quesMark[i]);
					bean.setOrder(quesOrder[i]);
					list.add(bean);
				}
			}
			programBean.setQuestionList(list);
			request.setAttribute("questionListLength", list.size());

			boolean result = saveQuestion(programBean, quesCode, quesName,
					quesType, quesLevel, quesMark, quesOrder);

			String deleteQuery = "DELETE FROM WBT_PROGRAM_QUES_DTL WHERE PROGRAM_QUES_ID="
					+ programBean.getProgramQuesID()
					+ " and QUES_CODE="
					+ programBean.getDeleteQueCode();
			getSqlModel().singleExecute(deleteQuery);

		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}


	/* It provides functionality to add Question into Iterator*/
	public void addQuestionList(HttpServletRequest request, ProgrammeMaster programBean, String[] srNo,
			String[] quesCode, String[] quesName, String[] quesType,
			String[] quesLevel, String[] quesMark, String[] queOrder) {
		try {

			ArrayList<Object> list = new ArrayList<Object>();
			String[] quesCode1 = null;
			String[] quesName1 = null;
			String[] quesType1 = null;
			String[] quesLevel1 = null;
			String[] quesMark1 = null;
			String[] queOrder1 = null;
		
			if(quesCode != null){
				quesCode1 = new String[quesCode.length+1];
				quesName1 = new String[quesCode.length+1];
				quesType1 = new String[quesCode.length+1];
				quesLevel1 = new String[quesCode.length+1];
				quesMark1 = new String[quesCode.length+1];
				queOrder1 = new String[quesCode.length+1];
			}
			
			String queCode = programBean.getQuesCode();
			String query = "SELECT HRMS_REC_QUESBANK.QUES_CODE, HRMS_REC_QUESBANK.QUES_NAME,"
					+ " DECODE(HRMS_REC_QUESBANK.QUES_TYPE,'O','Objective','S','Subjective'),"
					+ " DECODE(HRMS_REC_QUESBANK.QUES_LEVEL,'E','Easy','M','Medium','H','Hard')"
					+ " FROM HRMS_REC_QUESBANK"
					+ " WHERE HRMS_REC_QUESBANK.QUES_CODE IN ("
					+ queCode + ")";
			Object[][] quesData = getSqlModel().getSingleResult(query);
			if (quesData != null && quesData.length > 0) {
				if(quesCode != null){
				quesCode1 = new String[quesCode.length+quesData.length];
				quesName1 = new String[quesCode.length+quesData.length];
				quesType1 = new String[quesCode.length+quesData.length];
				quesLevel1 = new String[quesCode.length+quesData.length];
				quesMark1 = new String[quesCode.length+quesData.length];
				queOrder1 = new String[quesCode.length+quesData.length];
				} else{
					quesCode1 = new String[quesData.length];
					quesName1 = new String[quesData.length];
					quesType1 = new String[quesData.length];
					quesLevel1 = new String[quesData.length];
					quesMark1 = new String[quesData.length];
					queOrder1 = new String[quesData.length];
					
				}
				
				for (int i = 0; i < quesData.length; i++) {
					ProgrammeMaster bean = new ProgrammeMaster();
					// bean.setSrNo("" + i);
					bean.setQuesCodeItt(quesData[i][0].toString());
					bean.setQuesItt(quesData[i][0].toString());
					bean.setQuesTypeItt(quesData[i][2].toString());
					bean.setQuesLevelItt(quesData[i][3].toString());
					bean.setQuesMarkItt("0");
					list.add(bean);
					
					if(quesCode != null){
					quesCode1[quesCode.length+i] = quesData[i][0].toString();
					quesName1[quesCode.length+i] = quesData[i][0].toString();
					quesType1[quesCode.length+i] = quesData[i][2].toString();
					quesLevel1[quesCode.length+i] = quesData[i][3].toString();
					quesMark1[quesCode.length+i] = "0";
					queOrder1[quesCode.length+i] = "";
					} else{
						quesCode1[i] = quesData[i][0].toString();
						quesName1[i] = quesData[i][0].toString();
						quesType1[i] = quesData[i][2].toString();
						quesLevel1[i] = quesData[i][3].toString();
						quesMark1[i] = "0";
						queOrder1[i] = "";
					}
				}
			}
			
			if (quesCode != null) {
				for (int i = 0; i < quesCode.length; i++) {
					ProgrammeMaster beanQ = new ProgrammeMaster();
					beanQ.setQuesItt(quesName[i]);
					beanQ.setQuesCodeItt(quesCode[i]);
					beanQ.setQuesTypeItt(quesType[i]);
					beanQ.setQuesLevelItt(quesLevel[i]);
					beanQ.setQuesMarkItt(quesMark[i]);
					list.add(beanQ);
					
					if(quesCode != null){
					quesCode1[i] = quesCode[i];
					quesName1[i] = quesName[i];
					quesType1[i] = quesType[i];
					quesLevel1[i] = quesLevel[i];
					quesMark1[i] = quesMark[i];
					queOrder1[i] = queOrder[i]; 
					}
					
				}
			}
			programBean.setQuestionList(list);
			
			boolean result = saveQuestion(programBean, quesCode1, quesName1, quesType1,
					quesLevel1, quesMark1, queOrder1);
			
			request.setAttribute("questionListLength", list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* It provides functionality to save Question */
	public boolean saveQuestion(ProgrammeMaster programBean, String[] queID,
			String[] queName, String[] queType, String[] queLevel,
			String[] queMark, String[] queOrder) {
		boolean queryResult = false;
		String programQuesID = "";
		String insertQuery = "";
		try {

			Object[][] chkBooleanVal = new Object[1][4];
			if (programBean.getRandomCheck().equals("true")) {
				chkBooleanVal[0][0] = "Y";
			} else
				chkBooleanVal[0][0] = "N";
			if (programBean.getEqualEachMarkChk().equals("true")) {
				chkBooleanVal[0][1] = "Y";
			} else
				chkBooleanVal[0][1] = "N";

			if (programBean.getEqualNegaMarkType().equals("true")) {
				chkBooleanVal[0][2] = "Y";
			} else {
				chkBooleanVal[0][2] = "N";
			}
			if (programBean.getEqualEachMarkType().equals("true")) {
				chkBooleanVal[0][3] = "Y";
			} else
				chkBooleanVal[0][3] = "N";

			if (programBean.getSectionFlag().equals("false")) {
				programBean.setSectionCodeItt("0");
			}

			if (programBean.getProgramQuesID() != null
					&& !programBean.getProgramQuesID().equals("")) {
				programQuesID = programBean.getProgramQuesID();

				insertQuery = "UPDATE WBT_PROGRAM_QUES_HDR SET IS_QUES_RANDOM = ?, PROGRAM_NO_QUES = ?,"
						+ " MARK_EACH_QUES=?, PROGRAM_TOTAL_MARKS=?, PROGRAM_PASS_MARKS=?, "
						+ " IS_EQUAL_MARK_QUES=?, MARKS_EASY_QUES=?, MARKS_MEDIUM_QUES=?, MARKS_HARD_QUES=?,"
						+ " IS_NEGATIVE_MARK_QUES=?, "
						+ " NEGATIVE_MARK_EASY_QUES=?, NEGATIVE_MEDIUM_QUES=?, NEGATIVE_MARK_HARD_QUES=?,"
						+ " IS_EQUAL_MARK_QUES_TYPE=?"
						+ " WHERE PROGRAM_QUES_ID = ?";

				if (queName != null && queName.length > 0) {
					Object[][] paramObj = new Object[1][15];
					paramObj[0][0] = (String.valueOf(chkBooleanVal[0][0]));
					paramObj[0][1] = checkNull(programBean.getShowNoQues());
					paramObj[0][2] = checkNull(programBean.getEqualEachMark());
					paramObj[0][3] = checkNull(programBean.getTotalMark());
					paramObj[0][4] = checkNull(programBean.getPassQuesMark());
					paramObj[0][5] = chkBooleanVal[0][1];
					paramObj[0][6] = checkNull(programBean.getEasyMarkQue());
					paramObj[0][7] = checkNull(programBean.getMediumMarkQue());
					paramObj[0][8] = checkNull(programBean.getHardMarkQue());
					paramObj[0][9] = chkBooleanVal[0][2];
					paramObj[0][10] = checkNull(programBean.getEasyNegaMarkQ());
					paramObj[0][11] = checkNull(programBean
							.getMediumNegaMarkQ());
					paramObj[0][12] = checkNull(programBean.getHardNegaMarkQ());
					paramObj[0][13] = chkBooleanVal[0][3];
					paramObj[0][14] = programQuesID;
					queryResult = getSqlModel().singleExecute(insertQuery,
							paramObj);
				}

			} else {
				String query = "SELECT NVL(MAX(PROGRAM_QUES_ID), 0)+1 FROM WBT_PROGRAM_QUES_HDR";
				Object[][] maxQueID = getSqlModel().getSingleResult(query);
				programQuesID = String.valueOf(maxQueID[0][0]);

				insertQuery = "INSERT INTO WBT_PROGRAM_QUES_HDR (PROGRAM_QUES_ID, PROGRAM_ID, MODULE_ID, SECTION_ID, "
						+ " IS_QUES_RANDOM, PROGRAM_NO_QUES, MARK_EACH_QUES, PROGRAM_TOTAL_MARKS, PROGRAM_PASS_MARKS, "
						+ " IS_EQUAL_MARK_QUES, MARKS_EASY_QUES, MARKS_MEDIUM_QUES, MARKS_HARD_QUES, IS_NEGATIVE_MARK_QUES, "
						+ " NEGATIVE_MARK_EASY_QUES, NEGATIVE_MEDIUM_QUES, NEGATIVE_MARK_HARD_QUES, IS_EQUAL_MARK_QUES_TYPE)"
						+ " VALUES (?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

				if (queName != null && queName.length > 0) {
					Object[][] paramObj = new Object[1][18];
					paramObj[0][0] = programQuesID;
					paramObj[0][1] = checkNull(programBean.getQueProgrameCode());
					paramObj[0][2] = checkNull(programBean
							.getQueModuleCodeItt());
					paramObj[0][3] = checkNull(programBean
							.getQueSectionCodeItt());
					paramObj[0][4] = (String.valueOf(chkBooleanVal[0][0]));
					paramObj[0][5] = checkNull(programBean.getShowNoQues());
					paramObj[0][6] = checkNull(programBean.getEqualEachMark());
					paramObj[0][7] = checkNull(programBean.getTotalMark());
					paramObj[0][8] = checkNull(programBean.getPassQuesMark());
					paramObj[0][9] = chkBooleanVal[0][1];
					paramObj[0][10] = checkNull(programBean.getEasyMarkQue());
					paramObj[0][11] = checkNull(programBean.getMediumMarkQue());
					paramObj[0][12] = checkNull(programBean.getHardMarkQue());
					paramObj[0][13] = chkBooleanVal[0][2];
					paramObj[0][14] = checkNull(programBean.getEasyNegaMarkQ());
					paramObj[0][15] = checkNull(programBean
							.getMediumNegaMarkQ());
					paramObj[0][16] = checkNull(programBean.getHardNegaMarkQ());
					paramObj[0][17] = chkBooleanVal[0][3];
					queryResult = getSqlModel().singleExecute(insertQuery,
							paramObj);
				}
			}
			if (queryResult) {
				String updateQuery = "";
				if(programBean.getQuestionFlag().equalsIgnoreCase("module")) {
					updateQuery = "UPDATE WBT_PROGRAM_DTL SET PROGRAM_PASS_MARKS = " 
						+ checkNull(programBean.getPassQuesMark())
						+ " WHERE PROGRAM_ID = " + programBean.getQueProgrameCode()						
						+ " AND PROGRAM_MODULE_CODE = " + programBean.getQueModuleCodeItt();
					queryResult = getSqlModel().singleExecute(updateQuery);
				} else {
					updateQuery = "UPDATE WBT_PROGRAM_SECTION SET SECTION_PASS_MARKS = " 
						+ checkNull(programBean.getPassQuesMark())
						+ " WHERE PROGRAM_ID = " + programBean.getQueProgrameCode()
						+ " AND MODULE_ID = " + programBean.getQueModuleCodeItt()
						+ " AND SECTION_ID = " + programBean.getQueSectionCodeItt();
					queryResult = getSqlModel().singleExecute(updateQuery);
				}

				String queCodes = "";
				String queNames = "";
				String queTypes = "";
				String queLevels = "";
				String queMarks = "";
				String queOrders = "";

				

				insertQuery = "INSERT INTO WBT_PROGRAM_QUES_DTL (PROGRAM_QUES_ID, QUES_CODE,"
						+ " QUES_TYPE, QUES_LEVEL, QUES_MARKS, QUES_ORDER) VALUES(?,?,?,?,?,?)";

				for (int i = 0; i < queName.length; i++) {
					
					String deleteQuery = "DELETE FROM WBT_PROGRAM_QUES_DTL WHERE PROGRAM_QUES_ID="
						+ programQuesID
						+ " and QUES_CODE=" + checkNull(queID[i]);
					getSqlModel().singleExecute(deleteQuery);
					
					queCodes += queID[i];
					queNames += queName[i];

					if (queType[i].equals("Objective")) {
						queType[i] = "O";
					} else {
						queType[i] = "S";
					}
					queTypes += queType[i];

					if (queLevel[i].equals("Easy")) {
						queLevel[i] = "E";
					} else if (queLevel[i].equals("Medium")) {
						queLevel[i] = "M";
					} else {
						queLevel[i] = "H";
					}
					queLevels += queLevel[i];

					queMarks += queMark[i];
					queOrders += queOrder[i];

					Object[][] paramObj = new Object[1][6];
					paramObj[0][0] = programQuesID;
					paramObj[0][1] = checkNull(queID[i]);
					paramObj[0][2] = checkNull(queType[i]);
					paramObj[0][3] = checkNull(queLevel[i]);
					paramObj[0][4] = checkNull(queMark[i]);
					paramObj[0][5] = checkNull(queOrder[i]);

					queryResult = getSqlModel().singleExecute(insertQuery,
							paramObj);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	/* This is used to Open Instruction page on hyperlink of Add Instruction*/
	public void setInstruction(ProgrammeMaster bean,
			HttpServletRequest request, String from) {
		try {
			String query = " SELECT WBT_PROGRAM_HDR.PROGRAM_ID, NVL(WBT_PROGRAM_HDR.PROGRAM_NAME,' '),"
					+ " WBT_PROGRAM_HDR.PROGRAM_CONTENT FROM WBT_PROGRAM_HDR "
					+ " WHERE PROGRAM_ID = " + bean.getProgrameId();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setProgrameId(checkNull(String.valueOf(data[0][0])));
				bean.setProgrameName(checkNull(String.valueOf(data[0][1])
						.trim()));
				bean.setContent(checkNull(String.valueOf(data[0][2])));
			}
			request.setAttribute("labelName", "Instructions");
			request.setAttribute("from", from);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*Update a Instruction*/
	public boolean updateInstruction(ProgrammeMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			Object mod[][] = new Object[1][1];
			mod[0][0] = bean.getProgrameId();

			String query = " UPDATE WBT_PROGRAM_HDR SET PROGRAM_CONTENT = EMPTY_CLOB() "
					+ " WHERE PROGRAM_ID = ? ";
			result = getSqlModel().singleExecute(query, mod);
			
		if (result) {

			try {
				String sqlText = "SELECT PROGRAM_CONTENT  FROM WBT_PROGRAM_HDR WHERE  PROGRAM_ID = "
						+ bean.getProgrameId() + " FOR UPDATE ";
				SqlModel model = getSqlModel();
				Connection conn = model.connection();
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(sqlText);
				rset.next();
				CLOB xmlDoent = ((OracleResultSet) rset)
						.getCLOB("PROGRAM_CONTENT");
				xmlDoent.setString(1, bean.getContent());

				conn.commit();
				rset.close();
				stmt.close();
				model.freeConnection(conn);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(ProgrammeMaster programBean) {
		boolean result = false;
		String query = "SELECT * FROM WBT_PROGRAM_HDR WHERE UPPER(PROGRAM_NAME) LIKE '"
				+ programBean.getProgrameName().trim().toUpperCase() + "'";
		if (!programBean.getProgrameId().equals("")) {
			query = " AND PROGRAM_ID NOT IN(" + programBean.getProgrameId() + ")";
		}
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	final Comparator<ProgrammeMaster> sortSectionOrder = new Comparator<ProgrammeMaster>() {
		public int compare(ProgrammeMaster e1, ProgrammeMaster e2) {
			return e1.getSectionOrder().compareTo(e2.getSectionOrder());
		}
	};
	
}
