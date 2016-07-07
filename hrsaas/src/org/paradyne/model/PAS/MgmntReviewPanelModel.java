/**
 * 
 */
package org.paradyne.model.PAS;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.MgmntReviewPanel;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author AA0623
 * 
 */
public class MgmntReviewPanelModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MgmntReviewPanelModel.class);

	public LinkedHashMap<String, String> setPhaseDetails(MgmntReviewPanel panel) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String phaseQuery = " SELECT APPR_PHASE_ID, APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG "
				+ " WHERE APPR_IS_SELFPHASE = 'N' AND APPR_ID = "
				+ panel.getApprId() + " ORDER BY APPR_PHASE_ID ";
		Object[][] phaseObj = getSqlModel().getSingleResult(phaseQuery);
		if (phaseObj != null && phaseObj.length > 0) {
			for (int i = 0; i < phaseObj.length; i++) {
				map.put(String.valueOf(phaseObj[i][0]), String
						.valueOf(phaseObj[i][1]));
			}
		}
		return map;
	}

	public void getGroupHeadRecords(MgmntReviewPanel panel,
			HttpServletRequest request) {
		String grpHeadQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,  (HRMS_EMP_OFFC.EMP_FNAME||' '|| "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) GROUP_HEAD , "
				+ " NVL(DEPT_NAME,' '),NVL(RANK_NAME,'  '), NVL(CENTER_NAME,' '), 0 "
				+ " FROM PAS_APPR_APPRAISER_GRP_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_GROUP_HEAD_ID) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE APPR_ID = "
				+ panel.getApprId()
				+ " ORDER BY UPPER (HRMS_EMP_OFFC.EMP_FNAME||' '|| "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)";
		Object[][] grpHeadObj = getSqlModel().getSingleResult(grpHeadQuery);
		ArrayList<Object> grpHeadList = new ArrayList<Object>();
		if (grpHeadObj != null && grpHeadObj.length > 0) {
			panel.setTotalRecords(grpHeadObj.length);
			panel.setRecordsAvailable(true);

			String[] pageIndex = Utility.doPaging(panel.getMyPage(),
					grpHeadObj.length, 20);

			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));

			if (pageIndex[4].equals("1")) {
				panel.setMyPage("1");
			}

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				MgmntReviewPanel bean = new MgmntReviewPanel();
				bean.setGrpHeadId(Utility.checkNull(String
						.valueOf(grpHeadObj[i][0])));
				bean.setGrpHeadName(Utility.checkNull(String
						.valueOf(grpHeadObj[i][1])));
				bean.setGrpHeadDept(Utility.checkNull(String
						.valueOf(grpHeadObj[i][2])));
				bean.setGrpHeadDesg(Utility.checkNull(String
						.valueOf(grpHeadObj[i][3])));
				bean.setGrpHeadCenter(Utility.checkNull(String
						.valueOf(grpHeadObj[i][4])));
				bean.setGroupId(Utility.checkNull(String
						.valueOf(grpHeadObj[i][5])));

				grpHeadList.add(bean);
			}// end of for loop

			panel.setGrpHeadList(grpHeadList);
		}

	}

	public void getManagersUnderGrpHeads(MgmntReviewPanel panel,
			HttpServletRequest request, String groupHeadId) {
		/*
		 * String apprLevelQuery = " SELECT MAX (APPR_APPRAISER_LEVEL) FROM
		 * PAS_APPR_APPRAISER " + " WHERE APPR_ID = "+panel.getApprId()+" AND
		 * APPR_APPRAISER_GRP_ID = "+panel.getHidGroupId() + " AND APPR_PHASE_ID =
		 * "+panel.getPhaseCode(); Object[][] apprLevelObj =
		 * getSqlModel().getSingleResult(apprLevelQuery);
		 */

		String managerQuery = " SELECT APPR_APPRAISER_CODE, (HRMS_EMP_OFFC.EMP_FNAME||' '|| "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) MANAGER, "
				+ " NVL(DEPT_NAME,' '),NVL(RANK_NAME,'  '), NVL(CENTER_NAME,' '),APPR_APPRAISER_GRP_NAME, PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID,  NVL(DIV_NAME,' ')"
				+ " FROM PAS_APPR_APPRAISER "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE) "
				+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=DIV_ID) "
				+ " WHERE  PAS_APPR_APPRAISER.APPR_ID = "
				+ panel.getApprId()// +" AND APPR_APPRAISER_GRP_ID
									// ="+panel.getHidGroupId()
				+ " AND APPR_PHASE_ID = "
				+ panel.getPhaseCode()
				+ " AND APPR_GROUP_HEAD_ID=" + groupHeadId; // AND
															// APPR_APPRAISER_LEVEL
															// = "
		// + String.valueOf(apprLevelObj[0][0]);
		Object[][] managerObj = getSqlModel().getSingleResult(managerQuery);

		String groupHeadQuery = "SELECT (EMP_FNAME||' '||  NVL(EMP_MNAME,' ')|| ' ' || EMP_LNAME) GROUP_HEAD "
				+ " FROM HRMS_EMP_OFFC " + " WHERE EMP_ID= " + groupHeadId;
		Object[][] groupHeadObj = getSqlModel().getSingleResult(groupHeadQuery);
		if (groupHeadObj != null && groupHeadObj.length > 0) {
			panel.setGroupHeadName(String.valueOf(groupHeadObj[0][0]));
		}

		ArrayList<Object> managerList = new ArrayList<Object>();
		if (managerObj != null && managerObj.length > 0) {
			panel.setMgrRecordsAvailable(true);

			for (int i = 0; i < managerObj.length; i++) {
				MgmntReviewPanel bean = new MgmntReviewPanel();
				bean.setManagerIdList(Utility.checkNull(String
						.valueOf(managerObj[i][0])));
				bean.setManagerName(Utility.checkNull(String
						.valueOf(managerObj[i][1])));
				bean.setManagerDept(Utility.checkNull(String
						.valueOf(managerObj[i][2])));
				bean.setManagerDesg(Utility.checkNull(String
						.valueOf(managerObj[i][3])));
				bean.setManagerCenter(Utility.checkNull(String
						.valueOf(managerObj[i][4])));
				bean.setGroupName(Utility.checkNull(String
						.valueOf(managerObj[i][5])));
				bean.setGroupId(Utility.checkNull(String
						.valueOf(managerObj[i][6])));
				bean.setManagerDiv(Utility.checkNull(String
						.valueOf(managerObj[i][7])));
				managerList.add(bean);
			}// end of for loop

			panel.setManagerList(managerList);
		}
	}

	public void getEmployeeList(MgmntReviewPanel panel,
			HttpServletRequest request, String groupId) {

		String groupHeadQuery = "SELECT (EMP_FNAME||' '||  NVL(EMP_MNAME,' ')|| ' ' || EMP_LNAME) GROUP_HEAD "
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_ID= "
				+ panel.getHidGroupHeadId();

		Object[][] groupHeadObj = getSqlModel().getSingleResult(groupHeadQuery);
		if (groupHeadObj != null && groupHeadObj.length > 0) {
			panel.setGroupHeadName(String.valueOf(groupHeadObj[0][0]));
		}

		String query = " SELECT DISTINCT APPR_APPRAISEE_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||  NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME"
				+ " FROM  PAS_APPR_APPRAISER_GRP_DTL"
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=APPR_APPRAISEE_ID)"
				+ " WHERE APPR_APPRAISER_GRP_ID=" + groupId;

		Object[][] empObj = getSqlModel().getSingleResult(query);
		/*
		 * Calculate monthly KRA (70% of the average KRA rating)
		 */
		String kraRatingQuery = "";
		Object[][] kraRatingData = null;
		HashMap<String, String> kraRatingMap = new HashMap<String, String>();
		kraRatingQuery = " SELECT APPR_EMP_ID,ROUND(SUM((APPR_QUES_WEIGHTAGE/APPR_MAX_RATING)*APPR_QUES_RATING)*0.7) AS RATE"
				+ " FROM PAS_APPR_COMMENTS "
				+ " INNER JOIN PAS_APPR_QUESTION_RATING_HDR ON (PAS_APPR_QUESTION_RATING_HDR.APPR_ID=PAS_APPR_COMMENTS.APPR_ID)"
				+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID= PAS_APPR_COMMENTS.APPR_SECTION_ID)"
				+ " WHERE PAS_APPR_COMMENTS.APPR_ID="
				+ panel.getApprId()
				+ " AND APPR_PHASE_ID="
				+ panel.getPhaseCode()
				+ " AND (APPR_QUES_RATING>0 AND APPR_QUES_RATING IS NOT NULL)"
				+ " AND APPR_EVALUATOR_CODE="
				+ panel.getManagerId()
				+ " AND APPR_SECTION_TYPE='K' GROUP BY APPR_EMP_ID,APPR_MAX_RATING";
		kraRatingData = getSqlModel().getSingleResult(kraRatingQuery);
		if (kraRatingData != null && kraRatingData.length > 0) {
			for (int i = 0; i < kraRatingData.length; i++) {
				kraRatingMap.put(String.valueOf(kraRatingData[i][0]), String
						.valueOf(kraRatingData[i][1]));
			}
		} else {

			kraRatingQuery = " SELECT RATING_EMP_ID, "
					+ " CASE WHEN  ROUND((AVG(RATING)*0.70))>70 THEN 70 ELSE ROUND((AVG(RATING)*0.70)) END "
					+ " FROM HRMS_EMP_MONTHLY_RATING "
					+ " WHERE RATING > 0 "
					+ " AND (( RATING_MONTH >= 3 AND RATING_YEAR = TO_CHAR(TO_DATE('"
					+ panel.getFrmDate()
					+ "','dd-mm-YYYY'),'YYYY')) "
					+ " OR ( RATING_MONTH <= 2 AND RATING_YEAR = (TO_CHAR(TO_DATE('"
					+ panel.getFrmDate()
					+ "','dd-mm-YYYY'),'YYYY')+1) )) "
					+ " AND RATING_EMP_ID IN (SELECT DISTINCT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID="
					+ groupId + ")" + " GROUP BY RATING_EMP_ID";
			kraRatingData = getSqlModel().getSingleResult(kraRatingQuery);
			for (int i = 0; i < kraRatingData.length; i++) {
				kraRatingMap.put(String.valueOf(kraRatingData[i][0]), String
						.valueOf(kraRatingData[i][1]));
			}
		}

		/*
		 * End monthly KRA
		 */

		/*
		 * Calculate competency Score (30% of the average competency rating)
		 */
		String competencyQuery = " SELECT APPR_EMP_ID,ROUND(SUM(APPR_QUES_RATING)*30/(APPR_MAX_RATING*COUNT(APPR_QUESTION_CODE)))"
				+ " FROM PAS_APPR_COMMENTS "
				+ " INNER JOIN PAS_APPR_QUESTION_RATING_HDR ON (PAS_APPR_QUESTION_RATING_HDR.APPR_ID=PAS_APPR_COMMENTS.APPR_ID)"
				+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID= PAS_APPR_COMMENTS.APPR_SECTION_ID)"
				+ " WHERE PAS_APPR_COMMENTS.APPR_ID="
				+ panel.getApprId()
				+ " AND APPR_PHASE_ID="
				+ panel.getPhaseCode()
				+ " AND (APPR_QUES_RATING>0 AND APPR_QUES_RATING IS NOT NULL)"
				+ " AND APPR_EVALUATOR_CODE="
				+ panel.getManagerId()
				+ " AND APPR_SECTION_TYPE='C' GROUP BY APPR_EMP_ID,APPR_MAX_RATING";
		Object[][] competencyRatingData = getSqlModel().getSingleResult(
				competencyQuery);

		HashMap<String, String> competencyRatingMap = new HashMap<String, String>();
		for (int i = 0; i < competencyRatingData.length; i++) {
			competencyRatingMap.put(String.valueOf(competencyRatingData[i][0]),
					String.valueOf(competencyRatingData[i][1]));
		}
		/*
		 * End monthly KRA
		 */

		/*
		 * Calculate employee's saved score if any
		 */
		String savedScoreQuery = " SELECT EMP_ID, KRA_SCORE, COMPETENCY_SCORE, MOD_SCORE, HR_ACTIONS FROM PAS_APPR_MGMNT_PANEL_REVIEW"
				+ " WHERE APPR_ID="
				+ panel.getApprId()
				+ " AND MANAGER_ID="
				+ panel.getManagerId();
		Object[][] savedScoreData = getSqlModel().getSingleResult(
				savedScoreQuery);

		HashMap<String, Object[]> savedScoreDataMap = new HashMap<String, Object[]>();

		for (int i = 0; i < savedScoreData.length; i++) {
			savedScoreDataMap.put(String.valueOf(savedScoreData[i][0]),
					savedScoreData[i]);
		}

		if (empObj != null && empObj.length > 0) {
			ArrayList tableList = new ArrayList();
			for (int i = 0; i < empObj.length; i++) {
				MgmntReviewPanel beanList = new MgmntReviewPanel();
				beanList.setEmpId(String.valueOf(empObj[i][0]));
				beanList.setEmpName(String.valueOf(empObj[i][1]));
				Object[] empSavedScore = savedScoreDataMap.get(String
						.valueOf(empObj[i][0]));
				if (empSavedScore != null) {
					beanList.setEmpKRAScore(Utility.checkNull(String
							.valueOf(empSavedScore[1])));
					beanList.setEmpCompetencyScore(Utility.checkNull(String
							.valueOf(empSavedScore[2])));
					beanList.setEmpModScore(Utility.checkNull(String
							.valueOf(empSavedScore[3])));
					beanList.setEmpHRAction(Utility.checkNull(String
							.valueOf(empSavedScore[4])));
					beanList.setEmpTotalScore(Utility.checkNull(String
							.valueOf(Double.parseDouble(beanList
									.getEmpKRAScore())
									+ Double.parseDouble(beanList
											.getEmpCompetencyScore()))));

				} else {

					try {
						beanList.setEmpKRAScore(kraRatingMap.get(Utility
								.checkNull(String.valueOf(empObj[i][0]))));
					} catch (Exception e) {
						beanList.setEmpKRAScore("0");
					}

					try {
						beanList.setEmpCompetencyScore(competencyRatingMap
								.get(Utility.checkNull(String
										.valueOf(empObj[i][0]))));
					} catch (Exception e) {
						beanList.setEmpCompetencyScore("0");
					}
					beanList.setEmpModScore("");
					beanList.setEmpHRAction("");

					int kraScore = 0;
					int competencyScore = 0;
					if (!(String.valueOf(beanList.getEmpKRAScore())
							.equals(null)
							|| String.valueOf(beanList.getEmpKRAScore())
									.equals("null") || String.valueOf(
							beanList.getEmpKRAScore()).equals(""))) {
						kraScore = Integer.parseInt(beanList.getEmpKRAScore());
					}

					if (!(String.valueOf(beanList.getEmpCompetencyScore())
							.equals(null)
							|| String.valueOf(beanList.getEmpCompetencyScore())
									.equals("null") || String.valueOf(
							beanList.getEmpCompetencyScore()).equals(""))) {
						competencyScore = Integer.parseInt(beanList
								.getEmpCompetencyScore());
					}
					int totalScore = kraScore + competencyScore;
					beanList.setEmpTotalScore(String.valueOf(totalScore));
					beanList.setEmpKRAScore(String.valueOf(kraScore));
					beanList.setEmpCompetencyScore(String
							.valueOf(competencyScore));
					beanList.setEmpModScore(String.valueOf(totalScore));
				}
				tableList.add(beanList);
			}
			panel.setEmployeeList(tableList);
			panel.setEmpRecordsAvailable(true);
		} else {
			panel.setEmpRecordsAvailable(false);
		}
	}

	public boolean saveMgmntScore(MgmntReviewPanel panel,
			HttpServletRequest request) {
		String empIdQuery = "SELECT EMP_ID FROM PAS_APPR_MGMNT_PANEL_REVIEW WHERE APPR_ID="
				+ panel.getApprId();
		HashMap<String, String> empAvailableMap = getSqlModel()
				.getSingleResultMap(empIdQuery, 0, 2);
		logger.info("empAvailableMap===" + empAvailableMap.size());

		/*
		 * for (Iterator ite=empAvailableMap.keySet().iterator();
		 * ite.hasNext();) { String empId = (String)ite.next();
		 * logger.info("empId=="+empId); }
		 */
		String deleteQuery = "DELETE FROM PAS_APPR_MGMNT_PANEL_REVIEW WHERE APPR_ID="
				+ panel.getApprId() + " AND EMP_ID=?";
		String[] empIds = request.getParameterValues("empId");
		String[] kraScore = request.getParameterValues("empKRAScore");
		String[] empCompetencyScore = request
				.getParameterValues("empCompetencyScore");
		String[] empModScore = request.getParameterValues("empModScore");
		String[] empHRAction = request.getParameterValues("empHRAction");
		Object[][] deleteObj = new Object[empIds.length][1];
		Vector insertVect = new Vector();
		Vector updateVect = new Vector();

		boolean result = false;

		for (int i = 0; i < empIds.length; i++) {
			try {
				if (empAvailableMap.get(empIds[i]) == null
						|| String.valueOf(empAvailableMap.get(empIds[i]))
								.equals("")
						|| empAvailableMap.get(empIds[i]).equals("null")) {
					Object[] insertObj = new Object[7];
					insertObj[0] = panel.getApprId();
					insertObj[1] = panel.getManagerId();
					insertObj[2] = empIds[i];
					insertObj[3] = kraScore[i];
					insertObj[4] = empCompetencyScore[i];
					insertObj[5] = empModScore[i];
					insertObj[6] = empHRAction[i];
					insertVect.add(insertObj);
					insertObj = null;
				} else {
					Object[] updateObj = new Object[7];

					updateObj[0] = panel.getManagerId();
					updateObj[1] = kraScore[i];
					updateObj[2] = empCompetencyScore[i];
					updateObj[3] = empModScore[i];
					updateObj[4] = empHRAction[i];
					updateObj[5] = panel.getApprId();
					updateObj[6] = empIds[i];
					updateVect.add(updateObj);
					updateObj = null;
				}
			} catch (Exception e) {
				Object[] updateObj = new Object[7];

				updateObj[0] = panel.getManagerId();
				updateObj[1] = kraScore[i];
				updateObj[2] = empCompetencyScore[i];
				updateObj[3] = empModScore[i];
				updateObj[4] = empHRAction[i];
				updateObj[5] = panel.getApprId();
				updateObj[6] = empIds[i];
				updateVect.add(updateObj);
				updateObj = null;
			}
		}
		if (insertVect.size() > 0) {
			Object[][] finalInsert = new Object[insertVect.size()][7];
			String insertQuery = "INSERT INTO PAS_APPR_MGMNT_PANEL_REVIEW (APPR_ID, MANAGER_ID, EMP_ID, KRA_SCORE, COMPETENCY_SCORE, MOD_SCORE, HR_ACTIONS, REVIEW_DATE)"
					+ " VALUES (?,?,?,?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))";
			for (int i = 0; i < finalInsert.length; i++) {
				finalInsert[i] = (Object[]) insertVect.get(i);
			}
			result = getSqlModel().singleExecute(insertQuery, finalInsert);
		}
		if (updateVect.size() > 0) {
			Object[][] finalUpdate = new Object[updateVect.size()][7];
			String updateQuery = "UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET MANAGER_ID=?, KRA_SCORE=?, COMPETENCY_SCORE=?, MOD_SCORE=?, HR_ACTIONS=?, REVIEW_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "
					+ " WHERE APPR_ID=? AND EMP_ID=?";
			for (int i = 0; i < finalUpdate.length; i++) {
				finalUpdate[i] = (Object[]) updateVect.get(i);
			}
			result = getSqlModel().singleExecute(updateQuery, finalUpdate);
		}

		/*
		 * result = getSqlModel().singleExecute(deleteQuery,deleteObj);
		 * if(result){ Object [][]insertObj=new Object[empIds.length][7]; for
		 * (int j = 0; j < insertObj.length; j++) {
		 * insertObj[j][0]=panel.getApprId();
		 * insertObj[j][1]=panel.getManagerId(); insertObj[j][2]=empIds[j];
		 * insertObj[j][3]=kraScore[j]; insertObj[j][4]=empCompetencyScore[j];
		 * insertObj[j][5]=empModScore[j]; insertObj[j][6]=empHRAction[j]; }
		 * result = getSqlModel().singleExecute(insertQuery,insertObj); }
		 */
		return result;
	}

	public void getEmployeeAppraisalDetails(MgmntReviewPanel panel,
			HttpServletRequest request) {
		try {

			String empDetailsQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME SELF,  CENTER_ID, NVL(CENTER_NAME,' '), "
					+ " DEPT_ID,NVL(DEPT_NAME,' '), RANK_ID, NVL(RANK_NAME,' '),EMP_DIV, NVL(DIV_NAME,' ') "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " WHERE EMP_ID =" + panel.getHiddenEmpId();
			Object[][] empDataObj = getSqlModel().getSingleResult(
					empDetailsQuery);

			if (empDataObj != null && empDataObj.length > 0) {
				panel.setEmployeeId(String.valueOf(empDataObj[0][0]));
				panel.setEmpToken(String.valueOf(empDataObj[0][1]));
				panel.setEmployeeName(String.valueOf(empDataObj[0][2]));
				panel.setBranchId(String.valueOf(empDataObj[0][3]));
				panel.setBranchName(String.valueOf(empDataObj[0][4]));
				panel.setDeptId(String.valueOf(empDataObj[0][5]));
				panel.setDeptName(String.valueOf(empDataObj[0][6]));
				panel.setDesigId(String.valueOf(empDataObj[0][7]));
				panel.setDesigName(String.valueOf(empDataObj[0][8]));
				panel.setDivisionId(String.valueOf(empDataObj[0][9]));
				panel.setDivisionName(String.valueOf(empDataObj[0][10]));
				panel.setKraScore(panel.getHiddenKraScore());
				panel.setCompetencyScore(panel.getHiddenCompetencyScore());
			}

			String questionBankQuery = "SELECT DISTINCT APPR_QUESTION_CODE, NVL(APPR_QUES_DESC,' ') "
					+ " FROM PAS_APPR_COMMENTS "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
					+ " WHERE APPR_EMP_ID = "
					+ panel.getHiddenEmpId()
					+ " AND APPR_ID = "
					+ panel.getApprId()
					+ " AND APPR_QUES_RATING!=-1 " // AND
													// APPR_QUES_COMMENTS!='-1'"
					+ " ORDER BY APPR_QUESTION_CODE";
			Object[][] questionBankData = getSqlModel().getSingleResult(
					questionBankQuery);

			String selfCompetencyQuery = "SELECT DISTINCT APPR_QUESTION_CODE, APPR_QUES_RATING, APPR_QUES_COMMENTS "
					+ " FROM PAS_APPR_COMMENTS "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
					+ " WHERE APPR_EVALUATOR_CODE = "
					+ panel.getHiddenEmpId()
					+ " AND APPR_ID = "
					+ panel.getApprId()
					+ " AND APPR_QUES_RATING!=-1" // AND
													// APPR_QUES_COMMENTS!='-1'"
					+ " ORDER BY APPR_QUESTION_CODE";
			HashMap selfMap = getSqlModel().getSingleResultMap(
					selfCompetencyQuery, 0, 2);

			String managerCompetencyQuery = "SELECT DISTINCT APPR_QUESTION_CODE, APPR_QUES_RATING, APPR_QUES_COMMENTS "
					+ " FROM PAS_APPR_COMMENTS "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
					+ " WHERE APPR_EVALUATOR_CODE = "
					+ panel.getManagerId()
					+ " AND APPR_ID = "
					+ panel.getApprId()
					+ " AND APPR_EMP_ID="
					+ panel.getHiddenEmpId()
					+ " AND APPR_QUES_RATING!=-1 " // AND
													// APPR_QUES_COMMENTS!='-1'"
					+ " ORDER BY APPR_QUESTION_CODE";
			HashMap map = getSqlModel().getSingleResultMap(
					managerCompetencyQuery, 0, 2);

			if (questionBankData != null && questionBankData.length > 0) {
				Object[][] ratingDataForGraph = new Object[questionBankData.length][2];
				ArrayList<Object> ratingList = new ArrayList<Object>();
				for (int i = 0; i < questionBankData.length; i++) {
					MgmntReviewPanel bean1 = new MgmntReviewPanel();
					bean1.setQuestionCodeItt(String
							.valueOf(questionBankData[i][0]));
					bean1.setCompetencyItt(String
							.valueOf(questionBankData[i][1]));
					// bean1.setCompetencyRatingItt(String.valueOf(selfDataObj[i][2]));
					// bean1.setSelfJustificationItt(String.valueOf(selfDataObj[i][3]));

					try {
						Object[][] selfObj = (Object[][]) selfMap.get(String
								.valueOf(questionBankData[i][0]));
						bean1.setCompetencyRatingItt(Utility.checkNull(String
								.valueOf(selfObj[0][1])));
						bean1.setSelfJustificationItt(Utility.checkNull(String
								.valueOf(selfObj[0][2])));
						ratingDataForGraph[i][0] = Utility.checkNull(String
								.valueOf(selfObj[0][1]));
					} catch (Exception e) {
						// TODO: handle exception
					}
					bean1.setCompetencyManagerRatingItt("");
					bean1.setManagerJustificationItt("");
					try {
						Object[][] obj = (Object[][]) map.get(String
								.valueOf(questionBankData[i][0]));
						bean1.setCompetencyManagerRatingItt(Utility
								.checkNull(String.valueOf(obj[0][1])));
						bean1.setManagerJustificationItt(Utility
								.checkNull(String.valueOf(obj[0][2])));
						ratingDataForGraph[i][1] = Utility.checkNull(String
								.valueOf(obj[0][1]));
					} catch (Exception e) {
						// TODO: handle exception
					}

					/*
					 * if(managerDataObj !=null && managerDataObj.length > 0){
					 * for (int j = 0; j < managerDataObj.length; j++) {
					 * if(String.valueOf(managerDataObj[j][0]).equals(String.valueOf(selfDataObj[i][0]))){
					 * bean1.setCompetencyManagerRatingItt(String.valueOf(managerDataObj[j][1]));
					 * bean1.setManagerJustificationItt(String.valueOf(managerDataObj[j][2]));
					 * }else{ bean1.setCompetencyManagerRatingItt("");
					 * bean1.setManagerJustificationItt(""); } } }
					 */
					ratingList.add(bean1);
				}
				request.setAttribute("ratingDataForGraph", ratingDataForGraph);
				panel.setAnalysisList(ratingList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void generateReport(MgmntReviewPanel panel,
			HttpServletResponse response, String labels[]) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";

			reportType = "Xls";

			rds.setReportType(reportType);
			rds.setFileName("Management Review Panel Report");
			rds.setReportName("Management Review Panel Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = null;

			obj = new Object[1][3];
			obj[0][0] = "Management Review Panel Report";
			obj[0][1] = " for the period " + panel.getFrmDate();
			obj[0][2] = " to " + panel.getToDate();
			subtitleName.setData(obj);

			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);

			String managerQuery = " SELECT APPR_APPRAISER_CODE,(HRMS_EMP_OFFC.EMP_FNAME||' '|| NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) MANAGER, "
					+ " APPR_APPRAISER_GRP_NAME, APPR_APPRAISER_GRP_ID "
					+ " FROM PAS_APPR_APPRAISER "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE) "
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID) "
					+ " WHERE APPR_ID = "
					+ panel.getApprId()
					+ " AND APPR_PHASE_ID = "
					+ panel.getPhaseCode()
					+ " AND APPR_GROUP_HEAD_ID=" + panel.getHidGroupHeadId();

			Object[][] managerObj = getSqlModel().getSingleResult(managerQuery);

			String empQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_FNAME||' '||  NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME,"
					+ " '','','','','',APPR_APPRAISER_CODE,APPR_APPRAISEE_ID,APPR_APPRAISER_GRP_ID FROM  PAS_APPR_APPRAISER_GRP_DTL"
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=APPR_APPRAISEE_ID)"
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID)"
					+ " INNER JOIN PAS_APPR_APPRAISER ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID AND APPR_PHASE_ID="
					+ panel.getPhaseCode()
					+ ")"
					+ " WHERE APPR_GROUP_HEAD_ID="
					+ panel.getHidGroupHeadId()
					+ " ORDER BY APPR_APPRAISER_CODE,APPR_APPRAISER_GRP_ID ";

			Object[][] empObject = getSqlModel().getSingleResult(empQuery);
			HashMap empMap = new HashMap();
			String managerCode = "";
			Vector tempVect = new Vector();
			for (int i = 0; i < empObject.length; i++) {
				if (i == 0) {
					managerCode = String.valueOf(empObject[i][6]) + "#"
							+ String.valueOf(empObject[i][8]);
				}
				if (managerCode.equals(String.valueOf(empObject[i][6]) + "#"
						+ String.valueOf(empObject[i][8]))) {
					tempVect.add(empObject[i]);
					if (i == empObject.length - 1) {
						Object[][] empData = new Object[tempVect.size()][7];
						for (int k = 0; k < empData.length; k++) {
							empData[k] = (Object[]) tempVect.get(k);
						} // end of loop
						empMap.put(managerCode, empData);
						tempVect = new Vector();
					}
				} else {
					Object[][] empData = new Object[tempVect.size()][7];
					for (int k = 0; k < empData.length; k++) {
						empData[k] = (Object[]) tempVect.get(k);
					} // end of loop
					empMap.put(managerCode, empData);
					tempVect = new Vector();
					managerCode = String.valueOf(empObject[i][6]) + "#"
							+ String.valueOf(empObject[i][8]);

					if (i == empObject.length - 1) {
						empMap
								.put(managerCode,
										new Object[][] { empObject[i] });
					} else {
						tempVect.add(empObject[i]);
					}
				}

			}
/*
 
 
SELECT APPR_EMP_ID||'#'||APPR_EVALUATOR_CODE,ROUND(SUM(APPR_QUES_RATING)*70/(APPR_MAX_RATING*COUNT(APPR_QUESTION_CODE)))  
FROM PAS_APPR_COMMENTS  
INNER JOIN PAS_APPR_QUESTION_RATING_HDR ON (PAS_APPR_QUESTION_RATING_HDR.APPR_ID=PAS_APPR_COMMENTS.APPR_ID AND PAS_APPR_QUESTION_RATING_HDR.APPR_ID = 7)  
INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID= PAS_APPR_COMMENTS.APPR_SECTION_ID) 
WHERE  APPR_PHASE_ID=16 AND (APPR_QUES_RATING>0 AND APPR_QUES_RATING IS NOT NULL) 
AND APPR_SECTION_TYPE='K'
 GROUP BY APPR_EMP_ID,APPR_MAX_RATING,APPR_EVALUATOR_CODE
 
 
 */
			
			
			/*
			 * Calculate monthly KRA (70% of the average KRA rating)
			 */
			String kraRatingQuery = "";
			Object[][] kraRatingData = null;
			HashMap<String, String> kraRatingMap = new HashMap<String, String>();
			kraRatingQuery = " SELECT APPR_EMP_ID,ROUND(SUM((APPR_QUES_WEIGHTAGE/APPR_MAX_RATING)*APPR_QUES_RATING)*0.7) AS RATE "
					+ " FROM PAS_APPR_COMMENTS  "
					+ " INNER JOIN PAS_APPR_QUESTION_RATING_HDR ON (PAS_APPR_QUESTION_RATING_HDR.APPR_ID=PAS_APPR_COMMENTS.APPR_ID AND PAS_APPR_QUESTION_RATING_HDR.APPR_ID ="
					+panel.getApprId()
					+") "
					+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID= PAS_APPR_COMMENTS.APPR_SECTION_ID) "
					+ " WHERE  APPR_PHASE_ID="
					+ panel.getPhaseCode()
					+" AND (APPR_QUES_RATING>0 AND APPR_QUES_RATING IS NOT NULL) "
					+ " AND APPR_SECTION_TYPE='K' "
					+ " GROUP BY APPR_EMP_ID,APPR_MAX_RATING,APPR_EVALUATOR_CODE";
			kraRatingData = getSqlModel().getSingleResult(kraRatingQuery);
			if (kraRatingData != null && kraRatingData.length > 0) {
				for (int i = 0; i < kraRatingData.length; i++) {
					kraRatingMap.put(String.valueOf(kraRatingData[i][0]), String
							.valueOf(kraRatingData[i][1]));
				}
			} else {
			 kraRatingQuery = " SELECT RATING_EMP_ID, "
					+ " CASE WHEN  ROUND((AVG(RATING)*0.70))>70 THEN 70 ELSE ROUND((AVG(RATING)*0.70)) END "
					+ " FROM HRMS_EMP_MONTHLY_RATING "
					+ " WHERE RATING > 0 "
					+ " AND (( RATING_MONTH >= 3 AND RATING_YEAR = TO_CHAR(TO_DATE('"
					+ panel.getFrmDate()
					+ "','dd-mm-YYYY'),'YYYY')) "
					+ " OR ( RATING_MONTH <= 2 AND RATING_YEAR = (TO_CHAR(TO_DATE('"
					+ panel.getFrmDate()
					+ "','dd-mm-YYYY'),'YYYY')+1) )) "
					+ " AND RATING_EMP_ID IN (SELECT DISTINCT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID)WHERE APPR_ID="
					+ panel.getApprId() + ")" + " GROUP BY RATING_EMP_ID";
			kraRatingData = getSqlModel().getSingleResult(
					kraRatingQuery);

			
			if (kraRatingData != null && kraRatingData.length > 0) {
				for (int i = 0; i < kraRatingData.length; i++) {
					kraRatingMap.put(String.valueOf(kraRatingData[i][0]),
							String.valueOf(kraRatingData[i][1]));
				}
			}
			}
			/*
			 * End monthly KRA
			 */

			String competencyScoreQuery = "SELECT APPR_EMP_ID||'#'||APPR_EVALUATOR_CODE,ROUND(SUM(APPR_QUES_RATING)*30/(APPR_MAX_RATING*COUNT(APPR_QUESTION_CODE))) "
					+ " FROM PAS_APPR_COMMENTS "
					+ " INNER JOIN PAS_APPR_QUESTION_RATING_HDR ON (PAS_APPR_QUESTION_RATING_HDR.APPR_ID=PAS_APPR_COMMENTS.APPR_ID AND PAS_APPR_QUESTION_RATING_HDR.APPR_ID = "
					+ panel.getApprId()
					+ ") "
					+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID= PAS_APPR_COMMENTS.APPR_SECTION_ID)"
					+ " WHERE  APPR_PHASE_ID="
					+ panel.getPhaseCode()
					+ " AND (APPR_QUES_RATING>0 AND APPR_QUES_RATING IS NOT NULL) AND APPR_SECTION_TYPE='C'"
					+ " GROUP BY APPR_EMP_ID,APPR_MAX_RATING,APPR_EVALUATOR_CODE";

			Object[][] compObj = getSqlModel().getSingleResult(
					competencyScoreQuery);
			HashMap compMap = new HashMap();

			if (compObj != null && compObj.length > 0) {
				for (int i = 0; i < compObj.length; i++) {
					compMap.put(String.valueOf(compObj[i][0]), String
							.valueOf(compObj[i][1]));
				}
			}
			if (managerObj != null && managerObj.length > 0) {
				for (int i = 0; i < managerObj.length; i++) {
					Object[][] empObj = (Object[][]) empMap.get(String
							.valueOf(managerObj[i][0])
							+ "#" + String.valueOf(managerObj[i][3]));

					if (empObj != null && empObj.length > 0) {

						TableDataSet managerData = new TableDataSet();
						Object managerDataObj[][] = null;

						managerDataObj = new Object[1][5];
						managerDataObj[0][0] = "Manager Name : ";
						managerDataObj[0][1] = String.valueOf(managerObj[i][1]);
						managerDataObj[0][2] = "";
						managerDataObj[0][3] = "Group Head : ";
						managerDataObj[0][4] = panel.getGroupHeadName();
						managerData.setData(managerDataObj);

						managerData.setCellAlignment(new int[] { 1 });
						managerData.setCellWidth(new int[] { 100 });
						managerData.setBodyFontDetails(Font.HELVETICA, 10,
								Font.BOLD, new Color(0, 0, 0));
						managerData.setBorder(false);
						managerData.setHeaderTable(true);
						managerData.setBlankRowsBelow(1);

						rg.addTableToDoc(managerData);

						for (int j = 0; j < empObj.length; j++) {
							int kraRating = 0;
							int competencyScore = 0;
							try {
								kraRating = Integer.parseInt(String
										.valueOf(kraRatingMap.get(String
												.valueOf(empObj[j][7])))); // kra
																			// map
								competencyScore = Integer
										.parseInt(String
												.valueOf(compMap
														.get(String
																.valueOf(empObj[j][7])
																+ "#"
																+ String
																		.valueOf(managerObj[i][0])))); // competency
																										// map
							} catch (Exception e) {
								// TODO: handle exception
							}
							empObj[j][1] = String.valueOf(kraRating);
							empObj[j][2] = String.valueOf(competencyScore); // competency
																			// map

							empObj[j][3] = String.valueOf(kraRating
									+ competencyScore); // total score
							empObj[j][4] = "";
							empObj[j][5] = "";

						}
						TableDataSet empDetailsData = new TableDataSet();
						// String[] header = { "Employee Name", "KRA Score",
						// "Competency Score", "Total Score", "Moderated Score",
						// "HR Actions" };
						int[] alignment = { 0, 1, 1, 1, 1, 0 };
						int[] cellwidth = { 25, 10, 10, 10, 10, 35 };
						TableDataSet advancetable = new TableDataSet();
						empObj = Utility.removeColumns(empObj, new int[] { 6,
								7, 8 });
						empDetailsData.setData(empObj);
						empDetailsData.setHeader(labels);
						empDetailsData.setCellAlignment(alignment);
						empDetailsData.setCellWidth(cellwidth);
						advancetable.setHeaderBGColor(new Color(192, 192, 192));
						empDetailsData.setBorder(true);
						empDetailsData.setHeaderTable(true);
						empDetailsData.setBlankRowsBelow(1);
						rg.addTableToDoc(empDetailsData);

					}
				}
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
