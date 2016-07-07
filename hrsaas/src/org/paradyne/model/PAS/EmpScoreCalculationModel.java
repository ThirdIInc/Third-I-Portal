/*
 * Author Anantha lakshmi
 */
package org.paradyne.model.PAS;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.EmpScoreCalculation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.Utility;
import org.paradyne.model.Promotion.PromotionModel;


public class EmpScoreCalculationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmpScoreCalculationModel.class);
	HashMap incrDataMap = new HashMap();

	public void getIncrSlab() {
		Object[][] incrSlabData = null;
		try {
			String query = "SELECT INCR_GRADE, INCR_RATING_FROM, INCR_RATING_TO, INCR_PERCENTAGE FROM PAS_INCR_SLAB ";
			incrSlabData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getIncrSlab query", e);
		} // end of catch
		incrDataMap = objectToHashMap(incrSlabData);
	} // end of getServiceDaysData method

	public HashMap objectToHashMap(Object[][] incrSlabData) {
		HashMap dataMap = new HashMap();
		if (incrSlabData == null) {

		} // end of if
		else if (incrSlabData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < incrSlabData.length; i++) {
					String incrGradeId = "";
					incrGradeId = String.valueOf(incrSlabData[i][0]);
					for (int j = 0; j < incrSlabData.length; j++) {
						if (String.valueOf(incrSlabData[j][0]).equals(
								incrGradeId)) {
							v.add(incrSlabData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] data = new Object[v.size()][3];
					for (int k = 0; k < data.length; k++) {
						data[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(incrGradeId, data);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertIncrObjectToHashMap", e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of objectToHashMap method

	public void getPendingList(EmpScoreCalculation ctcBean,
			HttpServletRequest request, boolean isSearchFlag) {
		try {
			String pendingListQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,NVL(HRMS_CADRE.CADRE_NAME,' '),"
					+ " NVL(MOD_SCORE,0),NVL(EMP_OLD_CTC,0),NVL(EMP_SYS_PERC_INCR,0), NVL(EMP_NEW_CTC,0),NVL(CRITICAL_PERC_INCR,0),NVL(PROM_GROSSAMT,NVL(EMP_MOD_CTC,0)),EMP_PROM_CODE,"
					+"  DECODE(PUBLISH_LETTER_FLAG,'Y','Published', CASE WHEN LOCK_FLAG='Y' and EMP_MAIL_SENT='Y' THEN 'Published' WHEN LOCK_FLAG='Y' AND (EMP_MAIL_SENT!='Y' OR EMP_MAIL_SENT is null)  THEN 'Finalized' "
					+" WHEN LOCK_FLAG='N' THEN 'In Process' ELSE 'Pending' END) AS STATUS,NVL(NEW_CADRE.CADRE_NAME,' '),PRO_GRADE,HRMS_EMP_OFFC.EMP_CADRE AS OLD_GRADE  "
					+" ,DECODE(PROM_PROMFLAG,'I','false','P','true','false'),PROM_FORMULA,HRMS_FORMULABUILDER_HDR.FORMULA_NAME,TEMPLATE_CODE,TEMPLATE_NAME,NVL(ADD_EMAIL,'NOTAVLB') , "
					+" EMP_FORMULA,PANEL.FORMULA_NAME,DECODE(EMP_IS_PROMOTED,'N','false','Y','true','false'),EMP_REVISED_GRADE,PANEL_GARDE.CADRE_NAME," +
					" CASE WHEN FINAL_SCORE IS NULL THEN 'false' WHEN FINAL_SCORE=MOD_SCORE THEN 'false' ELSE 'true' END AS RATING_CHANGED  FROM HRMS_EMP_OFFC"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
					+ " INNER JOIN PAS_APPR_MGMNT_PANEL_REVIEW ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID)"
					+" INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID "
					+ " INNER JOIN PAS_APPR_APPRAISER  ON PAS_APPR_APPRAISER.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID AND PAS_APPR_APPRAISER.APPR_ID="
					+ ctcBean.getAppraisalId()
					+" LEFT JOIN HRMS_PROMOTION ON (PROM_CODE=EMP_PROM_CODE) "
					+" LEFT JOIN HRMS_CADRE NEW_CADRE ON(NEW_CADRE.CADRE_ID = PRO_GRADE)"
					+" LEFT JOIN HRMS_FORMULABUILDER_HDR ON(PROM_FORMULA=HRMS_FORMULABUILDER_HDR.FORMULA_ID)"
					+" LEFT JOIN HRMS_LETTERTEMPLATE_HDR ON (TEMPLATE_ID=TEMPLATE_CODE)"
					+" LEFT JOIN HRMS_FORMULABUILDER_HDR PANEL ON(EMP_FORMULA=PANEL.FORMULA_ID)"
					+" LEFT JOIN HRMS_CADRE PANEL_GARDE ON(PANEL_GARDE.CADRE_ID = EMP_REVISED_GRADE)"
					+" LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
					+ " INNER JOIN  PAS_APPR_APPRAISER_GRP_DTL on (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID and PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=APPR_APPRAISEE_ID)"
					+ " WHERE 1=1  ";
			if (isSearchFlag) {
				if (!ctcBean.getGroupHeadId().equals("")) {
					pendingListQuery += " AND APPR_GROUP_HEAD_ID ="
							+ ctcBean.getGroupHeadId();
				}
				if (!ctcBean.getManagerId().equals("")) {
					pendingListQuery += " AND PAS_APPR_MGMNT_PANEL_REVIEW.MANAGER_ID ="
							+ ctcBean.getManagerId();
				}
				if (!ctcBean.getDepartmentId().equals("")) {
					pendingListQuery += " AND DEPT_ID ="
							+ ctcBean.getDepartmentId();
				}
				if (!ctcBean.getBranchId().equals("")) {
					pendingListQuery += " AND EMP_CENTER ="
							+ ctcBean.getBranchId();
				}
				if (!ctcBean.getEmpId().equals("")) {
					pendingListQuery += " AND EMP_ID =" + ctcBean.getEmpId();
				} // employee filter
				
				if(!ctcBean.getStatusFilter().equals("A")){
					if(ctcBean.getStatusFilter().equals("P")){
						pendingListQuery += " AND EMP_PROM_CODE IS NULL ";
					}else if(ctcBean.getStatusFilter().equals("I")){
						pendingListQuery += " AND LOCK_FLAG='N' ";
					}else if(ctcBean.getStatusFilter().equals("F")){
						pendingListQuery += " AND LOCK_FLAG='Y' AND NVL(EMP_MAIL_SENT,'N')='N' ";
					}else if(ctcBean.getStatusFilter().equals("M")){
						pendingListQuery += " AND LOCK_FLAG='Y' AND NVL(EMP_MAIL_SENT,'N')='Y' ";
					}else if(ctcBean.getStatusFilter().equals("B")){
						pendingListQuery += " AND PUBLISH_LETTER_FLAG='Y' ";
					}
				}
				ctcBean.setPublishFlag("true");
			}
			Object pendingData[][] = getSqlModel().getSingleResult(
					pendingListQuery);

			String[] pageIndexPending = Utility.doPaging(ctcBean.getMyPage(),
					pendingData.length, 20);
			if (pageIndexPending == null) {
				pageIndexPending[0] = "0";
				pageIndexPending[1] = "20";
				pageIndexPending[2] = "1";
				pageIndexPending[3] = "1";
				pageIndexPending[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexPending[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexPending[3])));
			if (pageIndexPending[4].equals("1"))
				ctcBean.setMyPage("1");

			if (pendingData != null && pendingData.length > 0) {

				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
						.parseInt(pageIndexPending[1]); i++) {
					EmpScoreCalculation bean1 = new EmpScoreCalculation();
					bean1.setEmployeeIdItt(String.valueOf(pendingData[i][0]));
					// bean1.setEmployeeTokenItt(String.valueOf(pendingData[i][1]));
					if (Utility.checkNull(String.valueOf(pendingData[i][1]))
							.equals("")) {
						bean1.setEmployeeTokenItt("");
					} else {
						bean1.setEmployeeTokenItt(String
								.valueOf(pendingData[i][1]));
					}
					bean1.setEmployeeNameItt(String.valueOf(pendingData[i][2]));

					bean1.setEmpGradeItt(String.valueOf(pendingData[i][3]));
					bean1.setEmpGradeIdItt(String.valueOf(pendingData[i][14]));

					bean1.setEmployeeScoreItt(String.valueOf(pendingData[i][4]));

					if (Utility.checkNull(String.valueOf(pendingData[i][5]))
							.equals("")) {
						bean1.setOldCtcItt("0");
					} else {
						bean1.setOldCtcItt(String.valueOf(pendingData[i][5]));
					}

					bean1.setPercentIncrementItt(String
							.valueOf(pendingData[i][6]));

					bean1.setNewCtcItt(String.valueOf(pendingData[i][7]));

					bean1.setCriticalIncrementItt(String
							.valueOf(pendingData[i][8]));

					bean1.setModCtcItt(String.valueOf(pendingData[i][9]));
					//bean1.setFormulaMap(setFormulaDetails());
					bean1.setPromCodeItt(Utility.checkNull(String.valueOf(pendingData[i][10])));
					bean1.setStatus(String.valueOf(pendingData[i][11]));
					
					if(String.valueOf(pendingData[i][11]).equals("Pending"))
						bean1.setActionName("Process");
					else if(!String.valueOf(pendingData[i][11]).equals("In Process"))
						bean1.setActionName("Send Mail");
					else 
						bean1.setActionName("In Process");
					
					if(!(String.valueOf(pendingData[i][13]).equals("")||String.valueOf(pendingData[i][13]).equals("null")|| String.valueOf(pendingData[i][13]).equals(null))){
						bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][12]));
						bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][13]));
						
					}else{
						bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][3]));
						bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][14]));
					}
					
					bean1.setTemplateCode(Utility.checkNull(String.valueOf(pendingData[i][18])));
					bean1.setTemplateName(Utility.checkNull(String.valueOf(pendingData[i][19])));
					bean1.setEmpMailId(Utility.checkNull(String.valueOf(pendingData[i][20])));
					if(bean1.getPromCodeItt().equals("")){
						bean1.setPromotionFlag(Utility.checkNull(String.valueOf(pendingData[i][23])));
						bean1.setFormulaCode(Utility.checkNull(String.valueOf(pendingData[i][21])));
						bean1.setFormulaName(Utility.checkNull(String.valueOf(pendingData[i][22])));
						if(!(String.valueOf(pendingData[i][24]).equals("")||String.valueOf(pendingData[i][24]).equals("null")|| String.valueOf(pendingData[i][24]).equals(null))){
							bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][25]));
							bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][24]));
							
						}else{
							bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][3]));
							bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][14]));
						}
					}else{
						bean1.setPromotionFlag(Utility.checkNull(String.valueOf(pendingData[i][15])));
						bean1.setFormulaCode(Utility.checkNull(String.valueOf(pendingData[i][16])));
						bean1.setFormulaName(Utility.checkNull(String.valueOf(pendingData[i][17])));
						bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][12]));
						bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][13]));
					}
					//logger.info("rating changed flag="+String.valueOf(pendingData[i][26]));
					bean1.setRatingChangedFlag(String.valueOf(pendingData[i][26]));
					pendingList.add(bean1);
				}
				
				ctcBean.setPendingScoreList(pendingList);
				ctcBean.setPendingLength("true");
			}else{
				ctcBean.setPendingLength("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String getIncrementPercentage(String empGrade, String empScores,String prorataPercentage) {
		String percentageHike = "0";
		try {
			
			Object[][] slabGradeData = (Object[][]) incrDataMap.get(String.valueOf(empGrade));
			if (slabGradeData != null && slabGradeData.length > 0) {
				for (int j = 0; j < slabGradeData.length; j++) {
					double empScore = Double.parseDouble(empScores);
					double startIndex = Double.parseDouble(String
							.valueOf(slabGradeData[j][1]));
					double endIndex = Double.parseDouble(String
							.valueOf(slabGradeData[j][2]));
					if (empScore >= startIndex && empScore <= endIndex) {
						percentageHike = String.valueOf(slabGradeData[j][3]);
						System.out.println("------------------------------"+Double.parseDouble(percentageHike));
						percentageHike=Utility.twoDecimals(((Double.parseDouble(percentageHike)*Double.parseDouble(prorataPercentage))/100)); 
						break;
					} // end of if
					else {
						percentageHike = "0";
					}
				} // end of loop
			} // end of if
			else {
				percentageHike = "0";
			} // end of else
		} catch (Exception e) {
			logger.info("exception in getIncrementPercentage medthod====" + e);
		} // end of catch
		return percentageHike;
	} // end of getIncrementPercentage method
	

	public boolean updateModerateCtc(EmpScoreCalculation bean, String[] empId,
			String[] criticalIncrement, String[] moderatedCtc,
			String[] percent, String[] newCtc,String [] oldCtc,String []templateCode, String[] isPromoted, String[] formulaCode, String[] revisedGrade,String[] finalScore) {
		boolean result = false;
		Object[][] obj = new Object[empId.length][12];
		for (int i = 0; i < obj.length; i++) {
			obj[i][0] = percent[i];
			obj[i][1] = criticalIncrement[i];
			obj[i][2] = moderatedCtc[i];
			obj[i][3] = newCtc[i];
			obj[i][4] =	oldCtc[i];
			obj[i][5] = templateCode[i];
			if(isPromoted[i].equals("true"))
			obj[i][6] = "Y";
			else{
				obj[i][6] = "N";
			}
			obj[i][7] = revisedGrade[i];
			obj[i][8] = formulaCode[i];
			obj[i][9] = finalScore[i];
			obj[i][10] = bean.getAppraisalId();
			obj[i][11] = empId[i];
			
		}
		for (int i = 0; i < obj.length; i++) {
			for (int j = 0; j < obj[0].length; j++) {
				//logger.info("obj=="+obj[i][j]);
			}
		}
		String updateQuery = "UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET EMP_SYS_PERC_INCR=?, CRITICAL_PERC_INCR=?, EMP_MOD_CTC=?,EMP_NEW_CTC=?,EMP_OLD_CTC=?,"
			+" TEMPLATE_CODE=? ,EMP_IS_PROMOTED=?, EMP_REVISED_GRADE=?, EMP_FORMULA=?,FINAL_SCORE=? WHERE APPR_ID = ? AND EMP_ID=?";
		result = getSqlModel().singleExecute(updateQuery, obj);
		
		return result;
	}

	public void genReport(EmpScoreCalculation bean, HttpServletResponse response) {

		String grpQuery = " SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(CADRE_NAME,' '),"
			+ " NVL(MOD_SCORE,0),NVL(EMP_OLD_CTC,0),NVL(EMP_SYS_PERC_INCR,0), NVL(EMP_NEW_CTC,0),NVL(CRITICAL_PERC_INCR,0),NVL(EMP_MOD_CTC,0)  FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
			+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
			+ " INNER JOIN PAS_APPR_MGMNT_PANEL_REVIEW ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID)"
		/*if (!bean.getGroupHeadId().equals("")) {
			grpQuery += " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID ";
		}*/
				+" INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID "
				+ " INNER JOIN PAS_APPR_APPRAISER  ON PAS_APPR_APPRAISER.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID AND PAS_APPR_APPRAISER.APPR_ID="
				+ bean.getAppraisalId()
				+ " INNER JOIN  PAS_APPR_APPRAISER_GRP_DTL on (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID and PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=APPR_APPRAISEE_ID)"
				+ " WHERE 1=1  ";
		if (!(bean.getBranchId().equals("")) && !(bean.getBranchId() == null)
				&& !bean.getBranchId().equals("null")) {
			grpQuery += " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId()
					+ " ";
		}
		if (!(bean.getDepartmentId().equals(""))
				&& !(bean.getDepartmentId() == null)
				&& !bean.getDepartmentId().equals("null")) {
			grpQuery += " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDepartmentId()
					+ " ";
		}
		if (!(bean.getManagerId().equals("")) && !(bean.getManagerId() == null)
				&& !bean.getManagerId().equals("null")) {
			grpQuery += " AND PAS_APPR_MGMNT_PANEL_REVIEW.MANAGER_ID=" + bean.getManagerId() + " ";
		}
		if (!(bean.getGroupHeadId().equals(""))
				&& !(bean.getGroupHeadId() == null)
				&& !bean.getGroupHeadId().equals("null")) {
			grpQuery += " AND APPR_GROUP_HEAD_ID=" + bean.getGroupHeadId()
					+ " ";
		}
		if (!bean.getEmpId().equals("")
			&& !(bean.getEmpId() == null)
			&& !bean.getEmpId().equals("null")){
			grpQuery += " AND EMP_ID =" + bean.getEmpId();
		} // employee filter
		//grpQuery += " ";
		Object[][] objData = getSqlModel().getSingleResult(grpQuery);
				
		String reportName = "Employee Score Calculation Report";
		String reportType = "Xls";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName, "A4");
		rg.addTextBold("Employee Score Calculation Report", 0, 1, 0);
		if (!(objData == null || objData.length == 0)) {
			int[] attCellWidth = { 40, 40, 20, 20, 20, 20, 20, 20, 20 };
			int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			String[] attCol = { "Appraisal Id", "Appraisal Name", "Grade",
					"Score", "Old CTC", "% Increment", "New CTC",
					"Critical % Increment", "Moderated CTC" };
			rg.setFName("Employee Score Calculation Report");
			rg.addFormatedText("\n", 0, 0, 1, 0);
			if (reportType.equals("Xls")) {
				rg.tableBody(attCol, objData, attCellWidth, attAlign);
				rg.addText("\n\n", 0, 0, 0);
			}
		} else {
			rg.addTextBold("No records to display ", 0, 0, 1, 0);
		}
		rg.createReport(response);
	}

	public void calculateCtc(EmpScoreCalculation ctcBean,
			HttpServletRequest request, boolean isSearchFlag) {

		try {
			String pendingListQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, NVL(CTC,0),"
				+ " NVL(HRMS_CADRE.CADRE_NAME,' '), HRMS_CADRE.CADRE_ID, NVL(MOD_SCORE,0), "
				+ " CASE WHEN EMP_REGULAR_DATE<=to_date('30-06-2010','dd-mm-yyyy') THEN 100 "
				+ " WHEN  EMP_REGULAR_DATE>=to_date('01-07-2010','dd-mm-yyyy') " 
				+ " AND EMP_REGULAR_DATE<=to_date('30-09-2010','dd-mm-yyyy') "
				+ " THEN 75 "
				+ " WHEN  EMP_REGULAR_DATE>=to_date('01-10-2010','dd-mm-yyyy')" 
				+ " AND EMP_REGULAR_DATE<=to_date('31-12-2010','dd-mm-yyyy')"
				+ " THEN 50 "
				+ " else 0 END,"
				+" CASE WHEN LOCK_FLAG='Y' and EMP_MAIL_SENT='Y' THEN 'Published' WHEN LOCK_FLAG='Y' AND (EMP_MAIL_SENT!='Y' OR EMP_MAIL_SENT is null)  THEN 'Finalized' "
				+" WHEN LOCK_FLAG='N' THEN 'In Process' ELSE 'Pending' END AS STATUS, "
				+" DECODE(PROM_PROMFLAG,'I','false','P','true','false'),PROM_FORMULA,HRMS_FORMULABUILDER_HDR.FORMULA_NAME,TEMPLATE_CODE,TEMPLATE_NAME,NVL(ADD_EMAIL,'NOTAVLB'),EMP_PROM_CODE,"
				+"  EMP_FORMULA,PANEL.FORMULA_NAME,DECODE(EMP_IS_PROMOTED,'N','false','Y','true','false'),EMP_REVISED_GRADE,PANEL_GARDE.CADRE_NAME,NVL(CRITICAL_PERC_INCR,0)"
				+ " FROM HRMS_EMP_OFFC" 
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
				+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				+ " INNER JOIN PAS_APPR_MGMNT_PANEL_REVIEW ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID)"
				+" LEFT JOIN HRMS_PROMOTION ON (PROM_CODE=EMP_PROM_CODE) "
				+" LEFT JOIN HRMS_CADRE NEW_CADRE ON(NEW_CADRE.CADRE_ID = PRO_GRADE)"
				+" LEFT JOIN HRMS_FORMULABUILDER_HDR ON(PROM_FORMULA=HRMS_FORMULABUILDER_HDR.FORMULA_ID)"
				+" LEFT JOIN HRMS_LETTERTEMPLATE_HDR ON (TEMPLATE_ID=TEMPLATE_CODE)"
				+" LEFT JOIN HRMS_FORMULABUILDER_HDR PANEL ON(EMP_FORMULA=PANEL.FORMULA_ID)"
				+" LEFT JOIN HRMS_CADRE PANEL_GARDE ON(PANEL_GARDE.CADRE_ID = EMP_REVISED_GRADE)"
				+" LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
		
			/*if (!ctcBean.getGroupHeadId().equals("")) {
				pendingListQuery += " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID ";
			}*/
				+" INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID "
					+" INNER JOIN PAS_APPR_APPRAISER  ON PAS_APPR_APPRAISER.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID AND PAS_APPR_APPRAISER.APPR_ID="
					+ ctcBean.getAppraisalId()
					+ " INNER JOIN  PAS_APPR_APPRAISER_GRP_DTL on (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID and PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=APPR_APPRAISEE_ID)"
					+ " WHERE 1=1  ";
			if (isSearchFlag) {
				if (!ctcBean.getGroupHeadId().equals("")) {
					pendingListQuery += " AND APPR_GROUP_HEAD_ID ="
							+ ctcBean.getGroupHeadId();
				}
				if (!ctcBean.getManagerId().equals("")) {
					pendingListQuery += " AND PAS_APPR_MGMNT_PANEL_REVIEW.MANAGER_ID ="
							+ ctcBean.getManagerId();
				}
				if (!ctcBean.getDepartmentId().equals("")) {
					pendingListQuery += " AND DEPT_ID ="
							+ ctcBean.getDepartmentId();
				}
				if (!ctcBean.getBranchId().equals("")) {
					pendingListQuery += " AND EMP_CENTER ="
							+ ctcBean.getBranchId();
				}
				if (!ctcBean.getEmpId().equals("")) {
					pendingListQuery += " AND EMP_ID =" + ctcBean.getEmpId();
				} // employee filter
				
				if(!ctcBean.getStatusFilter().equals("A")){
					if(ctcBean.getStatusFilter().equals("P")){
						pendingListQuery += " AND EMP_PROM_CODE IS NULL ";
					}else if(ctcBean.getStatusFilter().equals("I")){
						pendingListQuery += " AND LOCK_FLAG='N' ";
					}else if(ctcBean.getStatusFilter().equals("F")){
						pendingListQuery += " AND LOCK_FLAG='Y' AND NVL(EMP_MAIL_SENT,'N')='N' ";
					}else if(ctcBean.getStatusFilter().equals("M")){
						pendingListQuery += " AND LOCK_FLAG='Y' AND NVL(EMP_MAIL_SENT,'N')='Y' ";
					}
				}
			}
			Object pendingData[][] = getSqlModel().getSingleResult(pendingListQuery);

			String[] pageIndexPending = Utility.doPaging(ctcBean.getMyPage(),
					pendingData.length, 20);
			if (pageIndexPending == null) {
				pageIndexPending[0] = "0";
				pageIndexPending[1] = "20";
				pageIndexPending[2] = "1";
				pageIndexPending[3] = "1";
				pageIndexPending[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexPending[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexPending[3])));
			if (pageIndexPending[4].equals("1"))
				ctcBean.setMyPage("1");

			if (pendingData != null && pendingData.length > 0) {

				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
						.parseInt(pageIndexPending[1]); i++) {
					EmpScoreCalculation bean1 = new EmpScoreCalculation();

					bean1.setEmployeeIdItt(String.valueOf(pendingData[i][0]));//EmpID
					bean1.setEmployeeTokenItt(Utility.checkNull(String.valueOf(pendingData[i][1])));//EmpToken
					bean1.setEmployeeNameItt(String.valueOf(pendingData[i][2]));//EmpNAME
					bean1.setOldCtcItt(Utility.checkNull(String.valueOf(pendingData[i][3])));//OLDCTC
					bean1.setEmpGradeItt(String.valueOf(pendingData[i][4]));//GRADE NAME
					bean1.setEmpGradeIdItt(String.valueOf(pendingData[i][5]));//GRADE ID
					bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][4]));//GRADE NAME
					bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][5]));//GRADE ID
					bean1.setPercentIncrementItt(getIncrementPercentage(String
							.valueOf(pendingData[i][5]), String.valueOf(pendingData[i][6]), 
							String.valueOf(pendingData[i][7])));//
					double amtIncreased = (Double.parseDouble(bean1
							.getPercentIncrementItt()) / 100)
							* Double.parseDouble(bean1.getOldCtcItt());
					
					bean1.setNewCtcItt(String.valueOf(Math.round(amtIncreased
							+ Double.parseDouble(bean1.getOldCtcItt()))));//NEW CTC
					
					bean1.setCriticalIncrementItt(String.valueOf(pendingData[i][21]));	//CRITICAL
					if (bean1.getCriticalIncrementItt().equals("0")) {
						bean1.setModCtcItt(bean1.getNewCtcItt());
					} else {
						double modCtcIncreased = (Double.parseDouble(bean1
								.getCriticalIncrementItt()) / 100)
								* Double.parseDouble(bean1.getOldCtcItt());	
						//logger.info("modCtcIncreased=="+modCtcIncreased);
						bean1.setModCtcItt(String.valueOf(Math.round(modCtcIncreased+ Double.parseDouble(bean1.getNewCtcItt()))));//MOD CTC
						//logger.info("setModCtcItt=="+bean1.getModCtcItt());
					}
					bean1.setEmployeeScoreItt(String.valueOf(pendingData[i][6]));//MOD SCORE
					bean1.setEmpGradeItt(Utility.checkNull(String.valueOf(pendingData[i][4])));//CADRE ID
					
					if(String.valueOf(pendingData[i][8]).equals("Pending"))
						bean1.setActionName("Process");
					else if(!String.valueOf(pendingData[i][8]).equals("In Process"))
						bean1.setActionName("Send Mail");
					else 
						bean1.setActionName("In Process");
					
					
					bean1.setStatus(String.valueOf(pendingData[i][8]));
					
					bean1.setPromotionFlag(Utility.checkNull(String.valueOf(pendingData[i][9])));
					bean1.setFormulaCode(Utility.checkNull(String.valueOf(pendingData[i][10])));
					bean1.setFormulaName(Utility.checkNull(String.valueOf(pendingData[i][11])));
					bean1.setTemplateCode(Utility.checkNull(String.valueOf(pendingData[i][12])));
					bean1.setTemplateName(Utility.checkNull(String.valueOf(pendingData[i][13])));
					bean1.setEmpMailId(Utility.checkNull(String.valueOf(pendingData[i][14])));
					bean1.setPromCodeItt(Utility.checkNull(String.valueOf(pendingData[i][15])));
					if(bean1.getPromCodeItt().equals("")){
						bean1.setPromotionFlag(Utility.checkNull(String.valueOf(pendingData[i][18])));
						bean1.setFormulaCode(Utility.checkNull(String.valueOf(pendingData[i][16])));
						bean1.setFormulaName(Utility.checkNull(String.valueOf(pendingData[i][17])));
						if(!(String.valueOf(pendingData[i][19]).equals("")||String.valueOf(pendingData[i][19]).equals("null")|| String.valueOf(pendingData[i][19]).equals(null))){
							bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][20]));
							bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][19]));
							
						}else{
							bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][4]));
							bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][5]));
						}
					}else{
						bean1.setPromotionFlag(Utility.checkNull(String.valueOf(pendingData[i][9])));
						bean1.setFormulaCode(Utility.checkNull(String.valueOf(pendingData[i][10])));
						bean1.setFormulaName(Utility.checkNull(String.valueOf(pendingData[i][11])));
						bean1.setEmpNewGradeItt(String.valueOf(pendingData[i][4]));
						bean1.setEmpNewGradeIdItt(String.valueOf(pendingData[i][5]));
					}
					pendingList.add(bean1);
				}
				ctcBean.setPendingScoreList(pendingList);
				ctcBean.setPendingLength("true");
			}else{
				ctcBean.setPendingLength("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LinkedHashMap<String, String> setFormulaDetails1() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String phaseQuery = " SELECT FORMULA_ID, FORMULA_NAME FROM HRMS_FORMULABUILDER_HDR " 
			+ " ORDER BY FORMULA_ID ";
		Object[][] formulaObj = getSqlModel().getSingleResult(phaseQuery);
		if(formulaObj != null && formulaObj.length > 0){
			for (int i = 0; i < formulaObj.length; i++) {
				map.put(String.valueOf(formulaObj[i][0]), String.valueOf(formulaObj[i][1]));
			}
		}
		return map;
	}

	public String processPromotion(String[] paramArray,
			EmpScoreCalculation ctcCalBean) {

		// TODO Auto-generated method stub
		try {

			String promoCodeQuery = " SELECT NVL(MAX(PROM_CODE),0)+1 FROM HRMS_PROMOTION";

			Object[][] promoCodeObj = getSqlModel().getSingleResult(
					promoCodeQuery);

			String effDateQue = "SELECT TO_CHAR(EFFECT_DATE,'DD-MM-YYYY') FROM HRMS_PROMOTION WHERE EMP_CODE ="
					+ paramArray[0];

			Object[][] effdata = getSqlModel().getSingleResult(effDateQue);

			String effMsg = "";
			String res = "";
			if (effdata.length > 0) {
				for (int i = 0; i < effdata.length; i++) {
					int days = Integer.parseInt(String.valueOf(effdata[i][0])
							.substring(0, 2));
					int month = Integer.parseInt(String.valueOf(effdata[i][0])
							.substring(3, 5));
					int year = Integer.parseInt(String.valueOf(effdata[i][0])
							.substring(6, 10));
					//logger.info("=====promotion.getEffDate()============"
							//+ effdata[i][0]);
					int effDays = Integer.parseInt(paramArray[6]
							.substring(0, 2));
					int effMonth = Integer.parseInt(paramArray[6].substring(3,
							5));
					int effYear = Integer.parseInt(paramArray[6].substring(6,
							10));
					/*logger.info(" =====ifffffffff===========" + days + "==="
							+ effDays);
					logger.info(" =====ifffffffff===========" + month + "==="
							+ effMonth);
					logger.info(" =====ifffffffff===========" + year + "==="
							+ effYear);*/
					if ((days == effDays) && (month == effMonth)
							&& (year == effYear)) {
						//logger.info("=-----in ifffffffff-------");

						return "sameEff";

					}

				}

			}
			//logger.info("=------------" + effMsg);

			Object[][] saveObj = new Object[1][32];

			String empQuery = "SELECT EMP_ID,EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_CADRE,EMP_DIV,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),EMP_REPORTING_OFFICER,NVL(EMP_ROLE,'') "
					+ " FROM HRMS_EMP_OFFC WHERE EMP_ID=" + paramArray[0];

			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			if (empObj != null && empObj.length > 0) {
				saveObj[0][0] = promoCodeObj[0][0];

				saveObj[0][1] = Utility.checkNull(String.valueOf(empObj[0][0]));

				saveObj[0][2] = Utility.checkNull(String.valueOf(empObj[0][1])); // branch

				saveObj[0][3] = Utility.checkNull(String.valueOf(empObj[0][2])); // dept

				saveObj[0][4] = Utility.checkNull(String.valueOf(empObj[0][3])); // desg

				saveObj[0][5] = Utility.checkNull(String.valueOf(empObj[0][4])); // grade

				saveObj[0][6] = Utility.checkNull(String.valueOf(empObj[0][5])); // div

				saveObj[0][7] = Utility.checkNull(String.valueOf(empObj[0][6])); // join
																					// date

				saveObj[0][8] = Utility.checkNull(String.valueOf(empObj[0][7])); // reporting

				saveObj[0][9] = paramArray[5];

				saveObj[0][10] = paramArray[6];

				saveObj[0][11] = Utility
						.checkNull(String.valueOf(empObj[0][1])); // prop
																	// branch

				saveObj[0][12] = Utility
						.checkNull(String.valueOf(empObj[0][2])); // prop dept

				saveObj[0][13] = Utility
						.checkNull(String.valueOf(empObj[0][3])); // prop desg

				saveObj[0][14] = paramArray[8]; // prop grade

				saveObj[0][15] = Utility
						.checkNull(String.valueOf(empObj[0][5])); // prop div

				saveObj[0][16] = Utility
						.checkNull(String.valueOf(empObj[0][7])); // prop
																	// reporting

				saveObj[0][17] = "";

				saveObj[0][18] = "";

				saveObj[0][19] = "";

				saveObj[0][20] = "";

				saveObj[0][21] = paramArray[3]; // emp rating

				saveObj[0][22] = ""; // old ctc

				saveObj[0][23] = ""; // new CTC

				saveObj[0][24] = paramArray[1]; // formula ID

				saveObj[0][25] = paramArray[4]; // new CTC

				saveObj[0][26] = "F";
				saveObj[0][27] = "0";
				if (paramArray[7].equals("true"))
					saveObj[0][28] = "P";
				else {
					saveObj[0][28] = "I";
				}

				String insertPromoQuery = "INSERT INTO HRMS_PROMOTION(PROM_CODE,EMP_CODE,BRANCH_CODE,DEPT_CODE,DESG_CODE,GRADE_CODE,DIV_CODE,"
						+ "DATE_JOINING,REPORTING_TO,PROM_DATE,EFFECT_DATE,"
						+ " PRO_BRANCH,"
						+ " PRO_DEPT,PRO_DESG,PRO_GRADE,PRO_DIV,PRO_REPORT_TO,PROPOSED_BY,APPROVED_BY,STRENGTH,WEAKNESS,RATING,TOTAL_OLD_AMOUNT,TOTAL_NEW_AMOUNT,LOCK_FLAG,PROM_FORMULA,PROM_GROSSAMT,PROCESS_FLAG,PROM_SALGRADE,PROM_PROMFLAG,OLD_CTC,PRO_ROLE,CURRENT_ROLE)"
						+ " VALUES(?,?,?,?,?,?,?,"
						+ "to_date(?,'DD-MM-YYYY'),?,to_date(?,'DD-MM-YYYY'),to_date(?,'DD-MM-YYYY'),"
						+ " ?,?,?,?,?,?,?,?,?,?,?,?,?,'N',?,?,?,?,?,?,?,?)";
				saveObj[0][29] = paramArray[2]; // old ctc

				saveObj[0][30] = Utility
						.checkNull(String.valueOf(empObj[0][8])); // CURRENT
																	// ROLE;

				saveObj[0][31] = Utility
						.checkNull(String.valueOf(empObj[0][8])); // PROP ROLE

				boolean flag = getSqlModel().singleExecute(insertPromoQuery,
						saveObj);

				// effMsg = String.valueOf(flag);
				if (flag) {

					String promoCode = (String.valueOf(promoCodeObj[0][0]));
					if (flag
							&& savePromotionSalary(ctcCalBean, promoCode,
									paramArray)) {
						getSqlModel().singleExecute(
								"UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET EMP_PROM_CODE="
										+ promoCode + " WHERE APPR_ID="
										+ ctcCalBean.getAppraisalId()
										+ " AND EMP_ID=" + paramArray[0]);
					}
					return "saved";
				} else {
					return "notSaved";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	return "";
		
	}

	public boolean savePromotionSalary(EmpScoreCalculation ctcCalBean,
			String promoCode,String []paramArray) {
		boolean result=false;
		String salquery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, CREDIT_NAME, NVL(CREDIT_AMT,0) CURRSAL, NVL(CREDIT_AMT,0) NEWSAL,NVL(CREDIT_PERIODICITY,' ')"
			+ " FROM HRMS_EMP_CREDIT"
			+ " RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE"
			+ " and HRMS_EMP_CREDIT.EMP_ID ="
			+ paramArray[0]
			+ ")"
			+ " ORDER BY CREDIT_CODE";

		Object[][] salData = getSqlModel().getSingleResult(salquery);

		try {

			Object[][] tableData = new Object[salData.length][5];
			for (int i1 = 0; i1 < tableData.length; i1++) {


				tableData[i1][0] = String.valueOf(salData[i1][0]);
				tableData[i1][1] = String.valueOf(salData[i1][1]);
				tableData[i1][2] = Utility.twoDecimals(String.valueOf(salData[i1][2]));
				tableData[i1][3] = Utility.twoDecimals(String.valueOf(salData[i1][3]));

				tableData[i1][4] = String.valueOf(salData[i1][4]);
			}

			// Query for retrieving formula information...

			String frmQue = " SELECT SAL_CODE, SAL_TYPE,SAL_FORMULA FROM HRMS_FORMULABUILDER_HDR "
					+ " INNER JOIN HRMS_FORMULABUILDER_DTL ON(HRMS_FORMULABUILDER_DTL.FORMULA_ID = HRMS_FORMULABUILDER_HDR.FORMULA_ID) "
					+ " WHERE HRMS_FORMULABUILDER_HDR.FORMULA_ID = "
					+ paramArray[1] + " ORDER BY SAL_CODE";

			Object[][] frmObj = getSqlModel().getSingleResult(frmQue);

			
				double CTC = Double.parseDouble(String.valueOf(paramArray[4]));	

				for (int j1 = 0; j1 < tableData.length; j1++) {
					tableData[j1][3] = "0.00";
				}

				ArrayList diffData = new ArrayList();
				ArrayList formData = new ArrayList();

				for (int j = 0; j < tableData.length; j++) {

					/*
					 * Loop for checking length of salary codes This loop
					 * calculates new salary amount according to salary code .
					 */

					for (int i = 0; i < frmObj.length; i++) {

						/*
						 * Loop for checking formula type according to formula
						 * code.
						 */

						String exec = "";
						if (String.valueOf(salData[j][0]).equals(String.valueOf(frmObj[i][0]))) {

							/*
							 * If loop for checking equal salary code It
							 * calculates new amount if salary code is equal to
							 * salary code of formula
							 */
							String sal_type = String.valueOf(frmObj[i][1]);
							String sal_formula = String.valueOf(frmObj[i][2]);

							if (sal_type.trim().equals("Fi")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is fixed
								 */

								tableData[j][3] = sal_formula;
							} else if (sal_type.trim().equals("Fr")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is formula
								 */

								//logger.info("In formula");
								/// formData.add(String.valueOf(tableData[j][0]));

								try {
									PromotionModel model = new PromotionModel();
									model.initiate(context, session);
									exec = model.executeFormula(sal_formula, CTC,
											tableData, formData);
								//	logger.info("exec===" + exec);
									model.terminate();
								} catch (Exception e) {
									e.printStackTrace();
								}
								tableData[j][3] = Utility.twoDecimals(Math
										.round(Double.parseDouble(String
												.valueOf(exec))));
								//logger.info("tableData[j][3]==="+tableData[j][3]);
							} else if (sal_type.trim().equals("Df")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is difference
								 */
								//logger.info("in diff "+String.valueOf(tableData[j][0]));
								diffData.add(String.valueOf(tableData[j][0]));
								
								tableData[j][3] = "0.00";
							} // end of else if
						} // end of if loop
					} // end of i loop
				} // end of j loop

				
				double sum = 0.00;

			

				for (int k = 0; k < tableData.length; k++) {

					/*
					 * calculates difference according to periodicity
					 */

					try {

						//logger.info("pppppppppppp======="
								//+ String.valueOf(tableData[k][4]));
						if (String.valueOf(tableData[k][4]).equals("A")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])));
						} else if (String.valueOf(tableData[k][4]).equals(
								"Q")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 4);
						} else if (String.valueOf(tableData[k][4]).equals(
								"H")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 2);
						} else if (String.valueOf(tableData[k][4]).equals(
								"M")) {
							/*/logger.info("String.valueOf(tableData[k][3]====)"
									+ String.valueOf(tableData[k][3]));*/
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 12);
						}
						/*logger.info("SalCode==" + tableData[k][0]);
						logger.info("SalAmt==" + tableData[k][3]);
						logger.info("sum==========" + sum);*/
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				double diffCal = 0.0;
				double cal = 0.00;

				// diffCal = CTC -
				if (CTC >= sum) {
					cal = ((CTC - sum) / 12);
					//logger.info("cal======" + cal);
				}
				if (CTC <= sum) {
					cal = ((sum - CTC) / 12);
					//logger.info("cal======" + cal);
				}
				for (int j = 0; j < tableData.length; j++) {

					for (int i = 0; i < diffData.size(); i++) {
						if (String.valueOf(tableData[j][0]).equals(
								diffData.get(i))) {

							/*
							 * logger.info("cnt==============" + i);
							 * tableData[j][3] =
							 * Utility.twoDecimals(Math.round(cal));
							 */

						/*	logger.info("Difference sal Type =========="
									+ String.valueOf(tableData[j][2]));
							logger.info("Before Multiply cal ==========" + cal);*/
							// check the salary type for difference

							if (String.valueOf(tableData[j][4]).equals(
									"A")) {
								tableData[j][3] = (cal * 12);
							}
							if (String.valueOf(tableData[j][4]).equals(
									"H")) {
								tableData[j][3] = cal * 6;

							}
							if (String.valueOf(tableData[j][4]).equals(
									"Q")) {
								tableData[j][3] = cal * 4;
							}

							if (String.valueOf(tableData[j][4]).equals(
									"M")) {
								tableData[j][3] = cal;
							}
							//logger.info("After Multiply cal =========="
							//		+ String.valueOf(tableData[j][3]));

						} // end of if loop
					} // end of i loop
				} // end of j loop
				
			if(getSqlModel().singleExecute("DELETE FROM HRMS_PROMOTION_SALARY WHERE PROM_CODE="+promoCode)){
			Object [][]promSalaryObj=new Object[tableData.length][4];
			String saveSal = "INSERT INTO HRMS_PROMOTION_SALARY(PROM_CODE,SAL_CODE,OLD_AMT,NEW_AMT) "
				+ "VALUES(?,?,?,?)";
			for (int i = 0; i < tableData.length; i++) {

				/* loop for setting new calculated values using formula */

				promSalaryObj[i][0] =promoCode;
				promSalaryObj[i][1] =String.valueOf(tableData[i][0]);
				promSalaryObj[i][2] =String.valueOf(tableData[i][2]);
				promSalaryObj[i][3] =String.valueOf(tableData[i][3]);
			} // end of i loop
			result = getSqlModel().singleExecute(saveSal,promSalaryObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return result;
	}
	
	public String getTemplate(HttpServletRequest request,HttpServletResponse response,String tempCode,String promCode,String empId,String empName){
		String signAuthCode = "1";
		String finaldata = "";
		String secSignAuthCode = "2";
		try {
			Template template = new Template(tempCode);
			template.initiate(context, session);
			template.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, promCode);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				TemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				TemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}*/

			String comlpeteTemplate = template.execute(request, response,
					"APPRAISAL_2010-11",true,empId,empName);
			////logger.info("comleteTemplate....."+comlpeteTemplate);

			try {
				comlpeteTemplate=comlpeteTemplate.replaceAll("&nbsp;", "");
				comlpeteTemplate=comlpeteTemplate.replaceAll("& ", "&amp; ");
				finaldata = "<html>" + comlpeteTemplate + "</html>";
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finaldata;
	
		
	}

	public boolean updateMailStatus(EmpScoreCalculation ctcCalBean,String empId) {
		return getSqlModel().singleExecute("UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET EMP_MAIL_SENT='Y',PUBLISH_LETTER_FLAG='Y'  WHERE APPR_ID="+ctcCalBean.getAppraisalId()+" AND EMP_ID="+empId);
	}

	public String reProcessPromotion(String[] paramArray,
			EmpScoreCalculation ctcCalBean) {
		String checkLockFlag="SELECT LOCK_FLAG FROM HRMS_PROMOTION WHERE PROM_CODE="+paramArray[9];
		Object [][]checkLockObj=getSqlModel().getSingleResult(checkLockFlag);
		if(checkLockObj!=null && checkLockObj.length>0){
			if(String.valueOf(checkLockObj[0][0]).equals("Y")){
				return "locked";
			}
		}
		// TODO Auto-generated method stub
		try {

			Object[][] updateObj = new Object[1][9];

			updateObj[0][0] = paramArray[5];		
			updateObj[0][1] = paramArray[6];
			updateObj[0][2] = paramArray[8];
			updateObj[0][3] = paramArray[3];
			updateObj[0][4] = paramArray[1];
			updateObj[0][5] = paramArray[4];
			updateObj[0][6] = "N";
			if (paramArray[7].equals("true"))
				updateObj[0][6] = "Y";
			updateObj[0][7] = paramArray[2];
			updateObj[0][8] = paramArray[9];

			String updatePromoQuery = "UPDATE HRMS_PROMOTION SET PROM_DATE=to_date(?,'DD-MM-YYYY'),EFFECT_DATE=to_date(?,'DD-MM-YYYY'),"
					+ " PRO_GRADE=?,RATING=?,LOCK_FLAG='N',PROM_FORMULA=?,PROM_GROSSAMT=?,PROM_PROMFLAG=?,OLD_CTC=? WHERE PROM_CODE=?";

			boolean flag = getSqlModel().singleExecute(updatePromoQuery,
					updateObj);

			// effMsg = String.valueOf(flag);
			if (flag) {

				String promoCode = paramArray[9];
				if (flag
						&& savePromotionSalary(ctcCalBean, promoCode,
								paramArray)) {
					getSqlModel().singleExecute(
							"UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET EMP_PROM_CODE="
									+ promoCode + " WHERE APPR_ID="
									+ ctcCalBean.getAppraisalId()
									+ " AND EMP_ID=" + paramArray[0]);
				}
				return "saved";
			} else {
				return "notSaved";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	return "";
		
	}
	
	public boolean publishAppraisals(EmpScoreCalculation ctcBean) {
		boolean result = false;
		
		try {
			String pendingListQuery = " UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET PUBLISH_LETTER_FLAG='Y' WHERE PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID IN( SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
				+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				+ " INNER JOIN PAS_APPR_MGMNT_PANEL_REVIEW ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID)"
				+" INNER JOIN PAS_APPR_APPRAISER_GRP_HDR  ON PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID "
				+ " INNER JOIN PAS_APPR_APPRAISER  ON PAS_APPR_APPRAISER.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID AND PAS_APPR_APPRAISER.APPR_ID="
				+ ctcBean.getAppraisalId()
				+" LEFT JOIN HRMS_PROMOTION ON (PROM_CODE=EMP_PROM_CODE) "
				+" LEFT JOIN HRMS_CADRE NEW_CADRE ON(NEW_CADRE.CADRE_ID = PRO_GRADE)"
				+" LEFT JOIN HRMS_FORMULABUILDER_HDR ON(PROM_FORMULA=HRMS_FORMULABUILDER_HDR.FORMULA_ID)"
				+" LEFT JOIN HRMS_LETTERTEMPLATE_HDR ON (TEMPLATE_ID=TEMPLATE_CODE)"
				+" LEFT JOIN HRMS_FORMULABUILDER_HDR PANEL ON(EMP_FORMULA=PANEL.FORMULA_ID)"
				+" LEFT JOIN HRMS_CADRE PANEL_GARDE ON(PANEL_GARDE.CADRE_ID = EMP_REVISED_GRADE)"
				+" LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
				+ " INNER JOIN  PAS_APPR_APPRAISER_GRP_DTL on (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID and PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=APPR_APPRAISEE_ID)"
				+ " WHERE 1=1  ";

		if (!ctcBean.getGroupHeadId().equals("")) {
			pendingListQuery += " AND APPR_GROUP_HEAD_ID ="
					+ ctcBean.getGroupHeadId();
		}
		if (!ctcBean.getManagerId().equals("")) {
			pendingListQuery += " AND PAS_APPR_MGMNT_PANEL_REVIEW.MANAGER_ID ="
					+ ctcBean.getManagerId();
		}
		if (!ctcBean.getDepartmentId().equals("")) {
			pendingListQuery += " AND DEPT_ID ="
					+ ctcBean.getDepartmentId();
		}
		if (!ctcBean.getBranchId().equals("")) {
			pendingListQuery += " AND EMP_CENTER ="
					+ ctcBean.getBranchId();
		}
		if (!ctcBean.getEmpId().equals("")) {
			pendingListQuery += " AND EMP_ID =" + ctcBean.getEmpId();
		} // employee filter
		
		pendingListQuery += " AND LOCK_FLAG='Y' ";
		
		pendingListQuery +=") AND PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID="
			+ ctcBean.getAppraisalId();
		
		result = getSqlModel().singleExecute(pendingListQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
