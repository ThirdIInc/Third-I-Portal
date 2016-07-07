/**
 * 
 */
package org.paradyne.model.Promotion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.paradyne.bean.promotion.PromotionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;


/**
 * @author ritac
 * 
 */
public class PromotionModel extends ModelBase {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.lib.ModelBase.class);

	PromotionMaster promotion;

	/**
	 * METHOD FOR SAVING DATA ON PROMOTION HEADER TABLE.
	 * @param PromotionMaster
	 * @return String
	 */
	public String savePromotion(PromotionMaster promotion) {

		/*
		 * Query for checking wheather effective date is present or not for the
		 * particular employee promotion cannot done on the same effective date
		 * for the same employee
		 */
		String promoCodeQuery = " SELECT NVL(MAX(HRMS_PROMOTION.PROM_CODE),0)+1 FROM HRMS_PROMOTION";
		
		Object[] paramObj = null;
		paramObj = new Object[1];
		Object[][] promoCodeObj = getSqlModel().getSingleResult(promoCodeQuery);
		
		/*String effDateQue = "SELECT TO_CHAR(EFFECT_DATE,'DD-MM-YYYY') FROM HRMS_PROMOTION WHERE EMP_CODE ="
				+ promotion.getEmpCode();*/
		String effDateQue = "SELECT TO_CHAR (HRMS_PROMOTION.EFFECT_DATE, 'DD-MM-YYYY'),"
				+ " TO_CHAR (HRMS_PROMOTION.PROM_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_PROMOTION WHERE HRMS_PROMOTION.EMP_CODE = ?";	// + promotion.getEmpCode();
		paramObj[0] = promotion.getEmpCode();
			 
		Object[][] effdata = getSqlModel().getSingleResult(effDateQue, paramObj);

		String effMsg = "";
		String res = "";
		if (effdata != null && effdata.length > 0) {
			for (int i = 0; i < effdata.length; i++) {
				int days = Integer.parseInt(String.valueOf(effdata[i][0]).substring(0, 2));
				int month = Integer.parseInt(String.valueOf(effdata[i][0]).substring(3, 5));
				int year = Integer.parseInt(String.valueOf(effdata[i][0]).substring(6, 10));
				logger.info("=====promotion.getEffDate()============" + effdata[i][0]);
				int effDays = Integer.parseInt(String.valueOf(promotion.getEffDate()).substring(0, 2));
				int effMonth = Integer.parseInt(String.valueOf(promotion.getEffDate()).substring(3, 5));
				int effYear = Integer.parseInt(String.valueOf(promotion.getEffDate()).substring(6, 10));
				logger.info(" =====ifffffffff===========" + days + "===" + effDays);
				logger.info(" =====ifffffffff===========" + month + "===" + effMonth);
				logger.info(" =====ifffffffff===========" + year + "===" + effYear);
				if ((days == effDays) && (month == effMonth) && (year == effYear)) {
					//logger.info("=-----in ifffffffff-------");
					logger.info("=-----in ifffffffff-------same effective date");
					int pdays = Integer.parseInt(String.valueOf(effdata[i][1]).substring(0, 2));
					int pmonth = Integer.parseInt(String.valueOf(effdata[i][1]).substring(3, 5));
					int pyear = Integer.parseInt(String.valueOf(effdata[i][1]).substring(6, 10));
					logger.info("=====promotion.getEffDate()============" + effdata[i][0]);
					int promDays = Integer.parseInt(String.valueOf(promotion.getPromDate()).substring(0, 2));
					int promMonth = Integer.parseInt(String.valueOf(promotion.getPromDate()).substring(3, 5));
					int promYear = Integer.parseInt(String.valueOf(promotion.getPromDate()).substring(6, 10));
					logger.info(" =====ifffffffff===========" + days + "===" + promDays);
					logger.info(" =====ifffffffff===========" + month + "===" + promMonth);
					logger.info(" =====ifffffffff===========" + year + "===" + promYear);					
					
					if ((pdays == promDays) && (pmonth == promMonth) && (pyear == promYear)) {
						logger.info("=-----in ifffffffff-------same promotion date");
						return "sameEff";
					}
				}
			}
		}

		Object[][] saveObj = new Object[1][33];

		saveObj[0][0] = promoCodeObj[0][0];
		
		saveObj[0][1] = promotion.getEmpCode();

		saveObj[0][2] = promotion.getBranchCode();

		saveObj[0][3] = promotion.getDeptCode();

		saveObj[0][4] = promotion.getDesgCode();

		saveObj[0][5] = promotion.getGradeCode();

		saveObj[0][6] = promotion.getDivCode();

		saveObj[0][7] = promotion.getJoinDate();

		saveObj[0][8] = promotion.getRepToCode();

		saveObj[0][9] = promotion.getPromDate();

		saveObj[0][10] = promotion.getEffDate();

		saveObj[0][11] = promotion.getPrBranCode();

		saveObj[0][12] = promotion.getPrDeptCode();

		saveObj[0][13] = promotion.getPrDesgCode();

		saveObj[0][14] = promotion.getPrGrdCode();

		saveObj[0][15] = promotion.getPrDivCode();

		saveObj[0][16] = promotion.getPrRepCode();

		saveObj[0][17] = promotion.getProByCode();

		saveObj[0][18] = promotion.getApprByCode();

		saveObj[0][19] = promotion.getStrength();

		saveObj[0][20] = promotion.getWeakness();

		saveObj[0][21] = promotion.getRating();

		saveObj[0][22] = promotion.getTotOldAmt();

		saveObj[0][23] = promotion.getTotNewAmt();
		//logger.info("promotion.getFrmId()====" + promotion.getFrmId());
		if (!String.valueOf(promotion.getFrmId()).equals("")) {
			saveObj[0][24] = promotion.getFrmId();
		} else {
			saveObj[0][24] = "0";
		}

		saveObj[0][25] = promotion.getGrsAmt();
		//logger.info("flag====" + promotion.getFrmFlag());
		if (promotion.getGrdFlag().equals("true")) {

			saveObj[0][26] = "G";
		}
		if (promotion.getFrmFlag().equals("true")) {

			saveObj[0][26] = "F";
		}

		if (!String.valueOf(promotion.getSalgrdId()).equals("")) {
			saveObj[0][27] = promotion.getSalgrdId();
		} else {
			saveObj[0][27] = "0";
		}

		if (promotion.getPromFlag().equals("true")) {
			saveObj[0][28] = "P";
		} else {
			saveObj[0][28] = "I";
		}

		//UPDATED BY REEBA
				saveObj[0][29] = promotion.getOldCTC();
				
				saveObj[0][30] = promotion.getLtrTempCode();
				System.out.println("promotion.getLtrTempCode()**************" + promotion.getLtrTempCode());
				
				saveObj[0][31] = promotion.getProRole();
				
				saveObj[0][32] = promotion.getCurrentRole();
				
				boolean flag =  getSqlModel().singleExecute(getQuery(1), saveObj);
				
				//effMsg = String.valueOf(flag);
		if (flag) {
		
			promotion.setPromCode(String.valueOf(promoCodeObj[0][0]));
			return "saved";
		} else {
			return "notSaved";
		}

	}

	// METHOD FOR GETTING RECORDS BEFORE SAVING DATA INTO PROMOTION HEADER TABLE
	public void getRecord(PromotionMaster promotion) {
		// TODO Auto-generated method stub
		try {

			Object[] eCode = new Object[2];
			eCode[0] = promotion.getEmpCode();

			eCode[1] = promotion.getRepToCode();

			// Query for retrieving employee information
			//logger.info("############### EMP CODE ##############"+eCode[0]);
			//logger.info("############### EMP REP TO CODE ##############"+eCode[1]);

			Object[] paramObj = null;
			paramObj = new Object[3];
			paramObj[0] = eCode[1];
			paramObj[1] = eCode[0];
			paramObj[2] = eCode[0];
			String query = "SELECT "
					+ " (SELECT NVL(E0.EMP_FNAME||' '||E0.EMP_MNAME||' '||E0.EMP_LNAME,' ') FROM HRMS_EMP_OFFC"
					+ " LEFT JOIN HRMS_EMP_OFFC E0 ON(E0.EMP_ID = HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=E0.EMP_TITLE_CODE)"
					+ " WHERE HRMS_EMP_OFFC.EMP_REPORTING_OFFICER =?" //+ eCode[1]
					+ " AND HRMS_EMP_OFFC.EMP_ID=?" //+ eCode[0]
					+ " ),"
					+ " NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(HRMS_DEPT.DEPT_NAME,' '),"
					+ " NVL(HRMS_RANK.RANK_NAME,' '), NVL(HRMS_CADRE.CADRE_NAME,' '),"
					+ " NVL(HRMS_DIVISION.DIV_NAME,' '), NVL(HRMS_EMP_OFFC.EMP_CENTER,'0'),"
					+ " NVL(HRMS_EMP_OFFC.EMP_DEPT,'0'), NVL(HRMS_EMP_OFFC.EMP_RANK,'0'),"
					+ " NVL(HRMS_EMP_OFFC.EMP_CADRE,'0'), NVL(HRMS_EMP_OFFC.EMP_DIV,'0'),"
					+ " NVL(HRMS_EMP_OFFC.EMP_ROLE,' ') FROM HRMS_EMP_OFFC"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT )"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID=?"; //+ String.valueOf(eCode[0]);
			//logger.info("qqqqqqq==" + query);
			Object[][] data = getSqlModel().getSingleResult(query,paramObj);

			promotion.setRepToName(checkNull(String.valueOf(data[0][0]).trim()));
			promotion.setPrRepToNm(checkNull(String.valueOf(data[0][0]).trim()));
			promotion.setProBranch(checkNull(String.valueOf(data[0][1]).trim()));
			promotion.setProDept(checkNull(String.valueOf(data[0][2]).trim()));
			promotion.setProDesg(checkNull(String.valueOf(data[0][3]).trim()));
			promotion.setProGrade(checkNull(String.valueOf(data[0][4]).trim()));
			promotion.setProDiv(checkNull(String.valueOf(data[0][5]).trim()));
			promotion.setPrBranCode(checkNull(String.valueOf(data[0][6])));
			promotion.setPrDeptCode(checkNull(String.valueOf(data[0][7])));
			promotion.setPrDesgCode(checkNull(String.valueOf(data[0][8])));
			promotion.setPrGrdCode(checkNull(String.valueOf(data[0][9])));
			promotion.setPrDivCode(checkNull(String.valueOf(data[0][10])));
			promotion.setProRole(checkNull(String.valueOf(data[0][11])));

			// Query for retieving appraisal rating , strength and weaknesses

			/*
			 * String rateQue="SELECT
			 * NVL(APPR_SCORE,'0'),NVL(APPR_EMP_STRENGTH,'
			 * '),NVL(APPR_EMP_IMPROVEMENT,' ') FROM HRMS_APPRAISAL "+ " LEFT
			 * JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =
			 * HRMS_APPRAISAL.APPR_EMP_ID)"+ " WHERE APPR_EMP_ID ="+eCode[0];
			 * 
			 * Object[][] rateQueData = getSqlModel().getSingleResult(rateQue);
			 */

			Object[][] rateQueData = getAppraisalData(String.valueOf(eCode[0]));
			if (rateQueData.length > 0) {
				promotion.setRating(String.valueOf(rateQueData[0][0]));
				promotion.setStrength(String.valueOf(rateQueData[0][1]).trim());
				promotion.setWeakness(String.valueOf(rateQueData[0][2]).trim());
			}

			// Query for retrieving credit headers and credit amount from the
			// employee credit configuration

			paramObj = new Object[1];
			String salquery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME,"
					+ " NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) CURRSAL, NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) NEWSAL,"
					+ " NVL(HRMS_CREDIT_HEAD.CREDIT_PERIODICITY,' ')"
					+ " FROM HRMS_EMP_CREDIT"
					+ " RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE"
					+ " AND HRMS_EMP_CREDIT.EMP_ID =?"	//+ eCode[0]
					+ ")"
					+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			
			paramObj[0]=eCode[0];
			
			Object[][] salData = getSqlModel().getSingleResult(salquery, paramObj);

			ArrayList<Object> list = new ArrayList<Object>();

			double oldTotal = 0.00;
			double newTotal = 0.00;

			for (int i = 0; i < salData.length; i++) {
				PromotionMaster promotion1 = new PromotionMaster();

				promotion1.setSalCode(String.valueOf(salData[i][0]));
				promotion1.setSalHead(String.valueOf(salData[i][1]));
				promotion1.setCurAmt(Utility.twoDecimals(String
						.valueOf(salData[i][2])));
				promotion1.setNewAmt(Utility.twoDecimals(String
						.valueOf(salData[i][3])));

				oldTotal += Double.parseDouble(promotion1.getCurAmt());

				newTotal += Double.parseDouble(promotion1.getNewAmt());
				//logger.info("String.valueOf(salData[i][4]========"
						//+ String.valueOf(salData[i][4]));
				if (String.valueOf(salData[i][4]).equals("M"))
					promotion1.setSalPerdiocity("Monthly");
				if (String.valueOf(salData[i][4]).equals("Q"))
					promotion1.setSalPerdiocity("Quarterly");
				if (String.valueOf(salData[i][4]).equals("H"))
					promotion1.setSalPerdiocity("Half Yearly");
				if (String.valueOf(salData[i][4]).equals("A"))
					promotion1.setSalPerdiocity("Annually");

				//logger.info("total============" + String.valueOf(oldTotal));
				//logger.info("total============" + String.valueOf(newTotal));

				list.add(promotion1);
			}

			promotion.setTotOldAmt(String.valueOf(Math.round(oldTotal)));
			promotion.setTotNewAmt(String.valueOf(Math.round(newTotal)));
			// promotion.setTableLength("1");
			promotion.setSalList(list);

			// Query for retrieving old CTC
			paramObj = new Object[1];
			String ctcQuery = " SELECT NVL(CTC,0) FROM HRMS_SALARY_MISC WHERE EMP_ID = ?";	// + eCode[0];
			paramObj[0] = eCode[0];
			Object[][] ctcObject = getSqlModel().getSingleResult(ctcQuery, paramObj);
			promotion.setOldCTC(String.valueOf(ctcObject[0][0]));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public String checkNull(String result) {
		/*
		 * method to check the null value
		 * 
		 */
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// METHOD FOR DELETING DATA ON PROMOTION HEADER AND PROMOTION SALARY TABLE
	public String deletePromotion(PromotionMaster promotion) {
		try {

			Object[][] delObj = new Object[1][1];
			delObj[0][0] = promotion.getPromCode();
			boolean res = getSqlModel().singleExecute(getQuery(7), delObj);

			if (res) {

				boolean flag = getSqlModel().singleExecute(getQuery(3), delObj);
				if (flag) {
					return "Record deleted successfully!";
				} else {
					return "Record can not be deleted successfully!";
				}
			}

			return " Record can't delete";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}

	// METHOD FOR UPDATING DATA IN PROMOTION HEADER TABLE

	public boolean updatePromotion(PromotionMaster promotion, Object[] salCode,
			Object[] salHead, Object[] curAmt, Object[] newAmt, Object[] prmCode) {
		try {
			boolean updata = false;
			//logger.info("promotion.getLockFlag()===" + promotion.getLockFlag());
			if (promotion.getLockFlag().equals("N")) // If promotion is
														// locked then employee
														// cannot update
														// promotion
			{
				Object[][] upObj = new Object[1][33];

				upObj[0][0] = promotion.getEmpCode();

				upObj[0][1] = promotion.getBranchCode();

				upObj[0][2] = promotion.getDeptCode();

				upObj[0][3] = promotion.getDesgCode();

				upObj[0][4] = promotion.getGradeCode();

				upObj[0][5] = promotion.getDivCode();

				upObj[0][6] = promotion.getJoinDate();

				upObj[0][7] = promotion.getRepToCode();

				upObj[0][8] = promotion.getPromDate();

				upObj[0][9] = promotion.getEffDate();

				upObj[0][10] = promotion.getPrBranCode();

				upObj[0][11] = promotion.getPrDeptCode();

				upObj[0][12] = promotion.getPrDesgCode();

				upObj[0][13] = promotion.getPrGrdCode();

				upObj[0][14] = promotion.getPrDivCode();

				upObj[0][15] = promotion.getPrRepCode();

				upObj[0][16] = promotion.getProByCode();

				upObj[0][17] = promotion.getApprByCode();

				upObj[0][18] = promotion.getStrength();

				upObj[0][19] = promotion.getWeakness();

				//logger.info("ratirn====" + promotion.getRating());
				upObj[0][20] = promotion.getRating();

				upObj[0][21] = promotion.getTotOldAmt();
				upObj[0][22] = promotion.getTotNewAmt();
				// saveObj[0][23]=promotion.getSalgrdId();
				//logger.info("getSalgrdId====" + promotion.getSalgrdId());
				if (!promotion.getFrmId().equals("")) {
					upObj[0][23] = promotion.getFrmId();
				} else {
					upObj[0][23] = "0";
				}
				

				if (promotion.getGrdFlag().equals("true")) {
					upObj[0][24] = promotion.getSalPromAmount();
					upObj[0][25] = "G";
				}
				if (promotion.getFrmFlag().equals("true")) {
					upObj[0][24] = promotion.getGrsAmt();
					upObj[0][25] = "F";
				}
				//logger.info("getFrmId====" + promotion.getFrmId());
				if (!promotion.getSalgrdId().equals("")) {
					upObj[0][26] = promotion.getSalgrdId();
				} else {
					upObj[0][26] = "0";
				}
				if (promotion.getPromFlag().equals("true")) {
					upObj[0][27] = "P";
				} else {
					upObj[0][27] = "I";
				}

				// UPDATED BY Shashank
				upObj[0][28] = promotion.getOldCTC();
				upObj[0][29] = promotion.getLtrTempCode();

				upObj[0][30] = promotion.getProRole();
				upObj[0][31] = promotion.getCurrentRole();
				upObj[0][32] = promotion.getPromCode();

				updata = getSqlModel().singleExecute(getQuery(2), upObj);

				if (updata) {
					updatePromDtl(promotion, salCode, salHead, curAmt, newAmt,
							prmCode);
				}
			}

			return updata;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	// METHOD FOR GETTING DETAILS AFTER SAVING DATA FOR UPDATION IN PROMOTION
	// HEADER N PROMOTION SALARY TABLE
	public void getDetails(PromotionMaster promotion) {
		//logger.info(">>>>>>>>>>>>>>>>>>>>>>>>GLODYNE1111\n\n\n\n\n");
		try {

			Object[] eCode = new Object[2];
			eCode[0] = promotion.getEmpCode();
			//logger.info("eeeeeeeee===" + eCode[0]);
			eCode[1] = promotion.getRepToCode();
			//logger.info("rrrrrrrrrrrr===" + eCode[1]);

			String query1 = "SELECT DISTINCT  (SELECT DISTINCT  NVL(ER.EMP_FNAME||' '||ER.EMP_MNAME||' '||ER.EMP_LNAME,' ') FROM HRMS_PROMOTION "
					+ " LEFT JOIN HRMS_EMP_OFFC ER ON(ER.EMP_ID = HRMS_PROMOTION.REPORTING_TO)"
					+ " left JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=ER.EMP_TITLE_CODE)"
					+ " WHERE HRMS_PROMOTION.REPORTING_TO = ?"	//+ eCode[1]
					+ " AND HRMS_PROMOTION.EMP_CODE = ?"	//+ eCode[0]
					+ ") ,c1.CENTER_NAME,"
					+ " NVL(dp1.DEPT_NAME,' '),NVL(r1.RANK_NAME,' '), NVL(cd1.CADRE_NAME,' '), NVL(dv1.DIV_NAME,' '),"
					+ " NVL(E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,' ')PROREP ,"
					+ " NVL(E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,' ') PRO_BY,NVL(E3.EMP_FNAME||' '||E3.EMP_MNAME||' '||E3.EMP_LNAME,' ') APPR_BY,"
					+ " NVL(HRMS_PROMOTION.STRENGTH,' '), NVL(HRMS_PROMOTION.WEAKNESS,' '), NVL(HRMS_PROMOTION.RATING,'0'), "
					+ " NVL(HRMS_PROMOTION.PRO_REPORT_TO,'0'), NVL(HRMS_PROMOTION.PROPOSED_BY,'0'),"
					+ " NVL(HRMS_PROMOTION.APPROVED_BY,'0'), NVL(HRMS_PROMOTION.PRO_BRANCH,'0'),"
					+ " NVL(HRMS_PROMOTION.PRO_DEPT,'0'), NVL(PRO_DESG,'0'), NVL(PRO_GRADE,'0'),"
					+ " NVL(HRMS_PROMOTION.PRO_DIV,'0'), HRMS_PROMOTION.LOCK_FLAG,"
					+ " NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '), NVL(HRMS_PROMOTION.PROM_FORMULA,'0'),"
					+ " NVL(HRMS_PROMOTION.PROM_GROSSAMT,'0'), HRMS_PROMOTION.PROCESS_FLAG,"
					+ " HRMS_SALGRADE_HDR.SALGRADE_TYPE,NVL(PROM_SALGRADE,'0'), HRMS_PROMOTION.PROM_PROMFLAG,"
					+ " HRMS_PROMOTION.OLD_CTC , HRMS_LETTERTEMPLATE_HDR.TEMPLATE_NAME,"
					+ " NVL(HRMS_PROMOTION.PRO_ROLE,' ')"
					+ " FROM HRMS_PROMOTION "
					+ " LEFT JOIN HRMS_CENTER c1 ON(c1.CENTER_ID=HRMS_PROMOTION.PRO_BRANCH)"
					+ " LEFT JOIN HRMS_RANK r1 ON(r1.RANK_ID=HRMS_PROMOTION.PRO_DESG)"
					+ " LEFT JOIN HRMS_CADRE cd1 ON(cd1.CADRE_ID=HRMS_PROMOTION.PRO_GRADE)"
					+ " LEFT JOIN HRMS_DEPT dp1 ON(dp1.DEPT_ID=HRMS_PROMOTION.PRO_DEPT)"
					+ " LEFT JOIN HRMS_DIVISION  dv1 ON(dv1.DIV_ID = HRMS_PROMOTION.PRO_DIV)"
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID= HRMS_PROMOTION.PRO_REPORT_TO)"
					+ " LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=E1.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID= HRMS_PROMOTION.PROPOSED_BY)"
					+ " LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=E2.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E3 ON(E3.EMP_ID= HRMS_PROMOTION.APPROVED_BY)"
					+ " LEFT JOIN HRMS_TITLE T3 ON(T3.TITLE_CODE=E3.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID=HRMS_PROMOTION.PROM_FORMULA)"
					+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_SALGRADE_HDR.SALGRADE_CODE=HRMS_PROMOTION.PROM_SALGRADE)"
					+ " LEFT JOIN HRMS_LETTERTEMPLATE_HDR ON(HRMS_PROMOTION.PROM_TEMPLATE=HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID)"
					+ " WHERE HRMS_PROMOTION.PROM_CODE = ?";	//+ promotion.getPromCode();
			//logger.info("pr=======" + query1);
			Object[] paraObj = new Object[3];
			paraObj[0] = eCode[1];
			paraObj[1] = eCode[0];
			paraObj[2] = promotion.getPromCode();
			Object[][] data1 = getSqlModel().getSingleResult(query1, paraObj);

			promotion.setRepToName(checkNull(String.valueOf(data1[0][0]).trim()));
			promotion.setProBranch(checkNull(String.valueOf(data1[0][1]).trim()));
			promotion.setProDept(checkNull(String.valueOf(data1[0][2]).trim()));
			promotion.setProDesg(checkNull(String.valueOf(data1[0][3]).trim()));
			promotion.setProGrade(checkNull(String.valueOf(data1[0][4]).trim()));
			promotion.setProDiv(checkNull(String.valueOf(data1[0][5]).trim()));
			promotion.setPrRepToNm(checkNull(String.valueOf(data1[0][6]).trim()));
			promotion.setProByNm(checkNull(String.valueOf(data1[0][7]).trim()));
			promotion.setApprByName(checkNull(String.valueOf(data1[0][8]).trim()));
			promotion.setStrength(checkNull(String.valueOf(data1[0][9]).trim()));
			promotion.setWeakness((String.valueOf(data1[0][10]).trim()));
			promotion.setRating(checkNull(String.valueOf(data1[0][11]).trim()));
			promotion.setPrRepCode(checkNull(String.valueOf(data1[0][12])));
			promotion.setProByCode(checkNull(String.valueOf(data1[0][13])));
			promotion.setApprByCode(String.valueOf(data1[0][14]));
			promotion.setPrBranCode(String.valueOf(data1[0][15]));
			promotion.setPrDeptCode(String.valueOf(data1[0][16]));
			promotion.setPrDesgCode(String.valueOf(data1[0][17]));
			promotion.setPrGrdCode(String.valueOf(data1[0][18]));
			promotion.setPrDivCode(String.valueOf(data1[0][19]));
			promotion.setLockFlag(checkNull(String.valueOf(data1[0][20]).trim()));			
			promotion.setFrmId(checkNull(String.valueOf(data1[0][22]).trim()));		

			if (String.valueOf(data1[0][24]).equals("F")) {
				promotion.setFrmFlag("true");
				promotion.setFrmName(checkNull(String.valueOf(data1[0][21]).trim()));
				promotion.setGrsAmt(checkNull(String.valueOf(data1[0][23]).trim()));
			}
			if (String.valueOf(data1[0][24]).equals("G")) {
				promotion.setGrdFlag("true");
				promotion.setSalgrdName(checkNull(String.valueOf(data1[0][25]).trim()));
				promotion.setSalPromAmount(checkNull(String.valueOf(data1[0][23]).trim()));
			}
			promotion.setSalgrdId(checkNull(String.valueOf(data1[0][26]).trim()));
			
			//logger.info("salllllllllllllllllllllllllllllllllll" + data1[0][27]);

			if (String.valueOf(data1[0][27]).equals("P")) {
				promotion.setPromFlag("true");
			} else {
				promotion.setPromFlag("false");
			}
			// UPDATED BY REEBA
			promotion.setOldCTC(checkNull(String.valueOf(data1[0][28]).trim()));
			promotion.setLtrTemp(checkNull(String.valueOf(data1[0][29]).trim()));
			promotion.setProRole(checkNull(String.valueOf(data1[0][30]).trim()));

			String salquery = "SELECT DISTINCT HRMS_PROMOTION_SALARY.SAL_CODE,"
					+ " HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_PROMOTION_SALARY.OLD_AMT,"
					+ " HRMS_PROMOTION_SALARY.NEW_AMT, HRMS_CREDIT_HEAD.CREDIT_PERIODICITY"
					+ " FROM HRMS_PROMOTION_SALARY"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE)"
					+ " INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE)"
					+ " WHERE HRMS_PROMOTION_SALARY.PROM_CODE = ?"	//+ promotion.getPromCode()
					+ " AND HRMS_PROMOTION.EMP_CODE = ?"	//+ eCode[0]
					+ " ORDER BY HRMS_PROMOTION_SALARY.SAL_CODE";
			 
			//logger.info("############### salquery ##################" +salquery);
			paraObj = new Object[2];
			paraObj[0] = promotion.getPromCode();
			paraObj[1] = eCode[0];
			Object[][] salData = getSqlModel().getSingleResult(salquery, paraObj);

			ArrayList<Object> list = new ArrayList<Object>();

			double oldTotal = 0.00;
			double newTotal = 0.00;

			for (int i = 0; i < salData.length; i++) {
				PromotionMaster promotion1 = new PromotionMaster();

				promotion1.setSalCode(String.valueOf(salData[i][0]));
				promotion1.setSalHead(checkNull(String.valueOf(salData[i][1])
						.trim()));
				promotion1.setCurAmt(Utility.twoDecimals(String
						.valueOf(salData[i][2])));
				promotion1.setNewAmt(Utility.twoDecimals(String
						.valueOf(salData[i][3])));

				if (String.valueOf(salData[i][4]).equals("M"))
					promotion1.setSalPerdiocity(("Monthly"));
				if (String.valueOf(salData[i][4]).equals("Q"))
					promotion1.setSalPerdiocity(("Quarterly"));
				if (String.valueOf(salData[i][4]).equals("H"))
					promotion1.setSalPerdiocity(("Half Yearly"));
				if (String.valueOf(salData[i][4]).equals("A"))
					promotion1.setSalPerdiocity(("Annually"));
				oldTotal += Double.parseDouble(promotion1.getCurAmt());
				newTotal += Double.parseDouble(promotion1.getNewAmt());
				list.add(promotion1);
			}

			promotion.setTotOldAmt(Utility
					.twoDecimals(String.valueOf(oldTotal)));
			promotion.setTotNewAmt(Utility
					.twoDecimals(String.valueOf(newTotal)));
			promotion.setTableLength("0");
			promotion.setSalList(list);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// METHOD FOR UPDATING DATA INTO EMPLOEE OFFICE TABLE
	public boolean updateEmpDetail(PromotionMaster promotion) {

		boolean result = false;
		try {
			Object[][] upObj = null;
			
			String upQue = "";
			if (promotion.getPrGrdCode().equals("0")
					&& promotion.getPrGrdCode().length() > 0) {
				upObj = new Object[1][7];
				upObj[0][0] = promotion.getPrBranCode();
				upObj[0][1] = promotion.getPrDeptCode();
				upObj[0][2] = promotion.getPrDesgCode();
				upObj[0][3] = promotion.getPrDivCode();
				upObj[0][4] = promotion.getPrRepCode();
				upObj[0][5] = promotion.getProRole();
				upObj[0][6] = promotion.getEmpCode();
				upQue = "UPDATE HRMS_EMP_OFFC SET EMP_CENTER=?"
						+ ",EMP_DEPT=?"
						+ ",EMP_RANK=?"
						+ ",EMP_DIV=?"
						+ ",EMP_REPORTING_OFFICER=?"
						+ ",EMP_SAL_GRADE = "
						+ promotion.getSalgrdId()
						+", EMP_ROLE=?"
						+ " WHERE EMP_ID=?";
					 	
					 	
				 
				/*upQue = "UPDATE HRMS_EMP_OFFC SET EMP_CENTER=" + upObj[0][0]
						+ ",EMP_DEPT=" + upObj[0][1] + ",EMP_RANK="
						+ upObj[0][2] + ",EMP_DIV=" + upObj[0][4]
						+ ",EMP_REPORTING_OFFICER=" + upObj[0][5]
						+ ", EMP_SAL_GRADE = " + promotion.getSalgrdId()+", EMP_ROLE='"+upObj[0][7]+"'"
						+ " WHERE EMP_ID=" + upObj[0][6];*/
			} else {
				 upObj = new Object[1][8];
				 
				 	upObj[0][0] = promotion.getPrBranCode();
					upObj[0][1] = promotion.getPrDeptCode();
					upObj[0][2] = promotion.getPrDesgCode();
					upObj[0][3] = promotion.getPrGrdCode();
					upObj[0][4] = promotion.getPrDivCode();
					upObj[0][5] = promotion.getPrRepCode();
					upObj[0][6] = promotion.getProRole();
					upObj[0][7] = promotion.getEmpCode();
				 upQue = "UPDATE HRMS_EMP_OFFC SET EMP_CENTER=?"
					 + ",EMP_DEPT=?"
					 + ",EMP_RANK=?"
					 + ",EMP_CADRE=?"
					 + ",EMP_DIV=?"
					 + ",EMP_REPORTING_OFFICER=?"
					 + ", EMP_SAL_GRADE ="
					 +promotion.getSalgrdId()
					 +", EMP_ROLE=?"
					 + " WHERE EMP_ID=?";
					 
					 
				/*upQue = "UPDATE HRMS_EMP_OFFC SET EMP_CENTER=" + upObj[0][0]
						+ ",EMP_DEPT=" + upObj[0][1] + ",EMP_RANK="
						+ upObj[0][2] + ",EMP_CADRE=" + upObj[0][3]
						+ ",EMP_DIV=" + upObj[0][4] + ",EMP_REPORTING_OFFICER="
						+ upObj[0][5] + ", EMP_SAL_GRADE = "
						+ promotion.getSalgrdId()+", EMP_ROLE='"+upObj[0][7]+"'" + " WHERE EMP_ID= "
						+ upObj[0][6];*/
			}

			result = getSqlModel().singleExecute(upQue,upObj);

			if (result) // Once salary and employee information updates then
						// sets lock flag into HRMS_PROMOTION as true
			{
				Object[][] upObj1 = new Object[1][1];
				upObj1[0][0] = promotion.getPromCode();
				promotion.setLockFlag("Y");
				String lockflagQue = "UPDATE HRMS_PROMOTION SET LOCK_FLAG='Y' WHERE PROM_CODE= ?";
						//+ promotion.getPromCode();
				result = getSqlModel().singleExecute(lockflagQue, upObj1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// METHOD FOR GETTING SALARY DATA FROM EMP_CREDIT TABLE BEFORE SAVING ON
	// PROMOTION HEADER TABLE
	public void getSalary(PromotionMaster promotion, String ecode) {

		// //logger.info("eeeeeeeee=="+promotion.getEmpCode());
		try {

			String salquery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_NAME,' '), NVL(CREDIT_AMT,0) CURRSAL, NVL(CREDIT_AMT,0) NEWSAL,NVL(CREDIT_PERIODICITY,' ')"
					+ " FROM HRMS_EMP_CREDIT"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE"
					+ " AND HRMS_EMP_CREDIT.EMP_ID = ?" //+ ecode
					+ ")"
					+ " ORDER BY CREDIT_CODE";

			Object[] paramObj = new Object[1]; 
			paramObj[0] = ecode; 
			Object[][] salData = getSqlModel().getSingleResult(salquery, paramObj);

			ArrayList<Object> list = new ArrayList<Object>();

			for (int i = 0; i < salData.length; i++) {
				PromotionMaster promotion1 = new PromotionMaster();

				promotion1.setSalCode(String.valueOf(salData[i][0]));
				promotion1.setSalHead(checkNull(String.valueOf(salData[i][1])
						.trim()));
				promotion1.setCurAmt(Utility.twoDecimals(String
						.valueOf(salData[i][2])));
				promotion1.setNewAmt(Utility.twoDecimals(String
						.valueOf(salData[i][3])));
				promotion1.setSalPerdiocity(checkNull(String.valueOf(
						salData[i][4]).trim()));
				list.add(promotion1);
			}

			promotion.setSalList(list);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// METHOD FOR SAVING SALARY DATA ON PROMOTION SALARY TABLE
	public void saveSalary(PromotionMaster promotion, Object[] salCode,
			Object[] salHead, Object[] curAmt, Object[] newAmt) {
		try {

			if (salCode != null) {
				Object[][] salObj = new Object[salCode.length][4];
				for (int i = 0; i < salCode.length; i++) {
					// logger.info("sal length==========" + salCode.length);
					// logger.info("cur length==========" + curAmt.length);
					salObj[i][0] = String.valueOf(salCode[i]);
					salObj[i][1] = promotion.getPromCode();
					salObj[i][2] = String.valueOf(curAmt[i]);
					salObj[i][3] = String.valueOf(newAmt[i]);

				}
				/*String saveSal = "INSERT INTO HRMS_PROMOTION_SALARY(SAL_CODE,PROM_CODE,OLD_AMT,NEW_AMT) "
					+ "VALUES("
					+ salCode[i]
					+ ","
					+ promotion.getPromCode()
					+ ","

					+ curAmt[i] + "," + newAmt[i] + ") ";*/
				String saveSal = "INSERT INTO HRMS_PROMOTION_SALARY(SAL_CODE,PROM_CODE,OLD_AMT,NEW_AMT) "
					+ "VALUES(?,?,?,?) ";

				getSqlModel().singleExecute(saveSal, salObj);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// METHOD FOR UPDATING DATA ON PROMOTION SALARY TABLE ACCORDING TO PROM CODE

	public boolean updatePromDtl(PromotionMaster promotion, final Object[] salCode,
			final Object[] salHead, final Object[] curAmt, final Object[] newAmt, final Object[] prmCode) {
		String upsalQue = "";
		Object[][] paramObj = null;
		if (salCode != null) {
			paramObj = new Object[1][1];			
			String delque = " DELETE FROM HRMS_PROMOTION_SALARY WHERE PROM_CODE = ?";	// + prmCode[0];
			paramObj[0][0] = prmCode[0];
			getSqlModel().singleExecute(delque, paramObj);
			Object[][] salObj = new Object[salCode.length][4];
			Object[][] upsalObj = new Object[salCode.length][3];

			for (int i = 0; i < salCode.length; i++) { // for loop for checking
														// no. of credit codes

				salObj[i][0] = prmCode[0];
				salObj[i][1] = salCode[i];
				salObj[i][2] = curAmt[i];
				salObj[i][3] = newAmt[i];

			}

			getSqlModel().singleExecute(getQuery(8), salObj);
		}
		return true;
	}

	// Method for updating credit data into employee credit configuration table.

	
	
	/**
	 * @param promotion
	 * @param prmCode
	 * @param salCode
	 * @param prSalCode
	 * @param curAmt
	 * @param newAmt
	 * @return
	 */
	public boolean updatePromSal(final PromotionMaster promotion, final Object[] prmCode,
			final Object[] salCode, final Object[] prSalCode, final Object[] curAmt,
			final Object[] newAmt) {
		
		String insertQuery = "";
		boolean result = false;
		try {

			String upQue = "SELECT HRMS_PROMOTION_SALARY.SAL_CODE, HRMS_PROMOTION_SALARY.NEW_AMT"
					+ " FROM HRMS_PROMOTION_SALARY WHERE PROM_CODE= ?";
					//+ promotion.getPromCode();
			Object[] parameterObj = new Object[1];
			parameterObj[0] = promotion.getPromCode();
			Object[][] upObj = getSqlModel().getSingleResult(upQue, parameterObj);

			if (upObj != null && upObj.length > 0) {
				String delEmpQue = "DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID= ?";
						//+ promotion.getEmpCode();
				Object[][] paramObj = new Object[1][1];
				paramObj[0][0] = promotion.getEmpCode();
				result = getSqlModel().singleExecute(delEmpQue, paramObj);
				if (result) {
					Object obj[][] = new Object[upObj.length][3];
					for (int i = 0; i < upObj.length; i++) {
						obj[i][0] = upObj[i][0];
						obj[i][1] = upObj[i][1];
						obj[i][2] = promotion.getEmpCode();
					}
					insertQuery = "INSERT INTO HRMS_EMP_CREDIT (CREDIT_CODE,CREDIT_AMT,EMP_ID)"
							+ "  VALUES(?,?,?)";
					result = getSqlModel().singleExecute(insertQuery, obj);

				}
			}

			if (result) {
				result = updateEmpDetail(promotion);
				/**
				 * Following code calculates the tax and updates tax process
				 */
				try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					//logger.info("I m calling tax calculation method");
					Object[][] empList = new Object[1][1];
					empList[0][0] = promotion.getEmpCode();
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					int fromYear = Integer.parseInt(String.valueOf(cal
							.get(Calendar.YEAR)));
					int month = Integer.parseInt(String.valueOf(cal
							.get(Calendar.MONTH)));
					if (month <= 2) {
						fromYear--;
					}
					if (empList != null && empList.length > 0) {
						taxmodel.calculateTax(empList,
								String.valueOf(fromYear), String
										.valueOf(fromYear + 1));
					}

					/**
					 * Edited by Prashant Shinde
					 */
					double annualAmt = 0;
					String creditQuery = "SELECT  ROUND(SUM(CASE"
						+ " WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='M' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 12 "
						+ " WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='Q' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 4"
						+ " WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='H' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 2"  
						+ " WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='A' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 1"
						+ " ELSE  0 END )) FROM HRMS_CREDIT_HEAD "
						+ " LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE)"
						+ " WHERE HRMS_EMP_CREDIT.EMP_ID = ?"	//+ promotion.getEmpCode()
						+ " AND HRMS_CREDIT_HEAD.CREDIT_IS_CTC_COMPONENT='Y'";
					
					Object[] paramObj = new Object[1];
					paramObj[0] = promotion.getEmpCode();
					Object[][] creditAmtObj = getSqlModel().getSingleResult(creditQuery, paramObj);
					annualAmt = Double.parseDouble(String.valueOf(creditAmtObj[0][0]));
					System.out.println("UPDATING SALARY MISC >>>>>>>>>>>>>>>>>>>>>>>>" + annualAmt);
					
					Object[][] paraObj = new Object[1][2];
					paraObj[0][0] = annualAmt;
					paraObj[0][1] = promotion.getEmpCode();
					String updateSalaryMiscQuery = " UPDATE HRMS_SALARY_MISC SET CTC = ?" //+ annualAmt
							+ " WHERE EMP_ID = ?"; //+ promotion.getEmpCode();
					
					getSqlModel().singleExecute(updateSalaryMiscQuery, paraObj);

					taxmodel.terminate();
				} catch (Exception e) {
					//logger
							//.error("Exception in updatePromSal() of PromotionModel while calling calculateTax : "
									//+ e);
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// METHOD FOR SHOWING SALARY GRADE ON CLICKING SALARY GRADE F9 FROM SALARY
	// GRADE TABLE ,
	// SETTING CURR AMT FROM EMP_CREDIT AND NEW AMT FROM SALARY GRADE ACCORDING
	// TO
	// SALARY GRADE

	public Object[][] showGrade(final PromotionMaster promotion, final String id,
			final Object[] eCode, final HttpServletRequest request) {
		try {

			//logger.info("sal grddddddddd===" + promotion.getSalgrdId());

			String query = "SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME,"
					+ " NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0), NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0'),"
					+ " NVL(HRMS_CREDIT_HEAD.CREDIT_PERIODICITY,' ')"
					+ " FROM HRMS_CREDIT_HEAD"
					+ " LEFT JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE"
					+ " AND HRMS_SALGRADE_DTL.SALGRADE_CODE = ?" //+ promotion.getSalgrdId()
					+ " )"
					+ " LEFT JOIN HRMS_EMP_CREDIT on(HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "
					+ " AND  HRMS_EMP_CREDIT.EMP_ID = ?" //+ eCode[0]
					+ " ) "
					+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object[] paraObj = new Object[2];
			paraObj[0] = promotion.getSalgrdId();
			paraObj[1] = eCode[0];
			Object amt[][] = getSqlModel().getSingleResult(query, paraObj);
			ArrayList<PromotionMaster> atlist = new ArrayList<PromotionMaster>();
			HashMap hmp = new HashMap();

			double newTotal = 0.00;
			double oldTotal = 0.00;
			for (int i = 0; i < amt.length; i++) { // loop for setting credit
													// amounts according to
													// salary grade

				PromotionMaster promotion1 = new PromotionMaster();
				promotion1.setSalCode(String.valueOf(amt[i][0]));
				promotion1.setSalHead(String.valueOf(amt[i][1]));
				promotion1.setCurAmt(Utility.twoDecimals(String
						.valueOf(amt[i][2])));
				promotion1.setNewAmt(Utility.twoDecimals(String
						.valueOf(amt[i][3])));
				if (String.valueOf(amt[i][4]).equals("M")) {
					promotion1.setSalPerdiocity(("Monthly"));
				}
				if (String.valueOf(amt[i][4]).equals("Q")) {
					promotion1.setSalPerdiocity(("Quarterly"));
				}
				if (String.valueOf(amt[i][4]).equals("H")) {
					promotion1.setSalPerdiocity(("Half Yearly"));
				}
				if (String.valueOf(amt[i][4]).equals("A")) {
					promotion1.setSalPerdiocity(("Annually"));
				}

				// promotion1.setSalPerdiocity(String.valueOf(amt[i][4]));
				// promotion1.setChkId(String.valueOf(amt[i][0]));
				//logger.info("into mode data1" + String.valueOf(amt[i][0]));
				oldTotal += Double.parseDouble(promotion1.getCurAmt());
				newTotal += Double.parseDouble(promotion1.getNewAmt());

				atlist.add(promotion1);
			} // end of for loop
			promotion.setTotNewAmt(Utility
					.twoDecimals(String.valueOf(newTotal)));
			promotion.setTotOldAmt(Utility
					.twoDecimals(String.valueOf(oldTotal)));
			promotion.setFrmName("");
			promotion.setGrsAmt("");
			request.setAttribute("data", hmp);
			promotion.setSalList(atlist);
			return amt;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	// Method for calculating credit amount according to selected formula ...
	public void showFormula(final Object[] sCode, final Object[] sHead, final Object[] scurAmt,
			final Object[] snewAmt, final Object[] salPer, final PromotionMaster promotion) {

		try {

			Object[][] tableData = new Object[sCode.length][5];
			for (int i1 = 0; i1 < tableData.length; i1++) {

				// loop for taking original values from jsp...

				tableData[i1][0] = String.valueOf(sCode[i1]);
				tableData[i1][1] = String.valueOf(sHead[i1]);
				tableData[i1][2] = String.valueOf(scurAmt[i1]);
				tableData[i1][3] = String.valueOf(snewAmt[i1]);

				tableData[i1][4] = String.valueOf(salPer[i1]);
			}

			// Query for retrieving formula information...

			String frmQue = " SELECT HRMS_FORMULABUILDER_DTL.SAL_CODE, HRMS_FORMULABUILDER_DTL.SAL_TYPE,"
					+ " HRMS_FORMULABUILDER_DTL.SAL_FORMULA"
					+ " FROM HRMS_FORMULABUILDER_HDR "
					+ " INNER JOIN HRMS_FORMULABUILDER_DTL ON(HRMS_FORMULABUILDER_DTL.FORMULA_ID = HRMS_FORMULABUILDER_HDR.FORMULA_ID) "
					+ " WHERE HRMS_FORMULABUILDER_HDR.FORMULA_ID = ?" //+ promotion.getFrmId()
					+ " ORDER BY HRMS_FORMULABUILDER_DTL.SAL_CODE";

			Object[] paraObj = new Object[1];
			paraObj[0] = promotion.getFrmId();
			
			Object[][] frmObj = getSqlModel().getSingleResult(frmQue, paraObj);

			if (promotion.getGrsAmt().length() > 0) {
				double CTC = Double.parseDouble(promotion.getGrsAmt());

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
						if (sCode[j].equals(String.valueOf(frmObj[i][0]))) {

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
								// formData.add(String.valueOf(tableData[j][0]));

								try {
									exec = executeFormula(sal_formula, CTC,
											tableData, formData);
									//logger.info("exec===" + exec);
								} catch (Exception e) {
									e.printStackTrace();
								}
								tableData[j][3] = Utility.twoDecimals(Math
										.round(Double.parseDouble(String
												.valueOf(exec))));
								// //logger.info("tableData[j][3]==="+tableData[j][3]);
							} else if (sal_type.trim().equals("Df")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is difference
								 */

								diffData.add(String.valueOf(tableData[j][0]));
								/*
								 * double sum =
								 * Double.parseDouble(String.valueOf(tableData[j][3]))*0.12*12;
								 * //logger.info("sum========="+sum); for (int k =
								 * 0; k < tableData.length; k++) {
								 * logger.info("SalCode======="+tableData[k][0]);
								 * logger.info("Period======="+tableData[k][4]);
								 * logger.info("Amt======="+tableData[k][3]);
								 * if(String.valueOf(tableData[k][4]).equals("A")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])); }
								 * else
								 * if(String.valueOf(tableData[k][4]).equals("Q")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3]))*
								 * 4; } else
								 * if(String.valueOf(tableData[k][4]).equals("H")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])) *
								 * 2; } else
								 * if(String.valueOf(tableData[k][4]).equals("M")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])) *
								 * 12; } } String cal = "0"; if(CTC >= sum) {
								 * cal = ""+Math.round(((CTC-sum)/12)); } else {
								 * cal = ""+Math.round(((sum - CTC)/12)); }
								 */
								tableData[j][3] = "0.00";
							} // end of else if
						} // end of if loop
					} // end of i loop
				} // end of j loop

				// calDifference(diffData, tableData, CTC);
				// calculates salary amount when formula type is difference...
				double sum = 0.00;

				/*
				 * Query for cheking pf type of employee if pf type is yes then
				 * calculates pf of employee type pf is not applicable for
				 * consultant type
				 */

				/*
				 * String pfQue=" SELECT TYPE_PF FROM HRMS_EMP_TYPE" +" INNER
				 * JOIN HRMS_EMP_OFFC ON(HRMS_EMP_TYPE.TYPE_ID =
				 * HRMS_EMP_OFFC.EMP_TYPE)" +" WHERE HRMS_EMP_OFFC.EMP_ID
				 * ="+promotion.getEmpCode(); Object[][] pfObj =
				 * getSqlModel().getSingleResult(pfQue);
				 */

				/*
				 * String pfQue=" SELECT SAL_EPF_FLAG FROM HRMS_EMP_OFFC " +"
				 * INNER JOIN HRMS_SALARY_MISC
				 * ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID) " +" WHERE
				 * HRMS_EMP_OFFC.EMP_ID = "+promotion.getEmpCode(); Object[][]
				 * pfObj = getSqlModel().getSingleResult(pfQue);
				 * 
				 * if(String.valueOf(pfObj[0][0]).equals("Y")) { sum +=
				 * (Double.parseDouble(String.valueOf(tableData[0][3])) * 0.12 *
				 * 12); logger.info("pf==========" +sum); }
				 */

				for (int k = 0; k < tableData.length; k++) {

					/*
					 * calculates difference according to periodicity
					 */

					try {

						//logger.info("pppppppppppp======="
								//+ String.valueOf(tableData[k][4]));
						if (String.valueOf(tableData[k][4]).equals("Annually")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])));
						} else if (String.valueOf(tableData[k][4]).equals(
								"Quarterly")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 4);
						} else if (String.valueOf(tableData[k][4]).equals(
								"Half Yearly")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 2);
						} else if (String.valueOf(tableData[k][4]).equals(
								"Monthly")) {
							//logger.info("String.valueOf(tableData[k][3]====)"
									//+ String.valueOf(tableData[k][3]));
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][3])) * 12);
						}
						//logger.info("SalCode==" + tableData[k][0]);
						//logger.info("SalAmt==" + tableData[k][3]);
						//logger.info("sum==========" + sum);
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
							 * //logger.info("cnt==============" + i);
							 * tableData[j][3] =
							 * Utility.twoDecimals(Math.round(cal));
							 */

							//logger.info("Difference sal Type =========="
									//+ String.valueOf(tableData[j][2]));
							//logger.info("Before Multiply cal ==========" + cal);
							// check the salary type for difference

							if (String.valueOf(tableData[j][4]).equals(
									"Annually")) {
								tableData[j][3] = (cal * 12);
							}
							if (String.valueOf(tableData[j][4]).equals(
									"Half Yearly")) {
								tableData[j][3] = cal * 6;

							}
							if (String.valueOf(tableData[j][4]).equals(
									"Quarterly")) {
								tableData[j][3] = cal * 4;
							}

							if (String.valueOf(tableData[j][4]).equals(
									"Monthly")) {
								tableData[j][3] = cal;
							}
							//logger.info("After Multiply cal =========="
									//+ String.valueOf(tableData[j][3]));

						} // end of if loop
					} // end of i loop
				} // end of j loop

			} // end outer if
			ArrayList<Object> list = new ArrayList<Object>();

			for (int i = 0; i < tableData.length; i++) {

				/* loop for setting new calculated values using formula */

				PromotionMaster promotion1 = new PromotionMaster();
				promotion1.setSalCode(String.valueOf(tableData[i][0]));
				promotion1.setSalHead(String.valueOf(tableData[i][1]));
				promotion1.setCurAmt(String.valueOf(tableData[i][2]));
				promotion1.setNewAmt(String.valueOf(tableData[i][3]));
				promotion1.setSalPerdiocity(String.valueOf(tableData[i][4]));
				list.add(promotion1);
			} // end of i loop
			promotion.setSalList(list);
			promotion.setSalgrdName("");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Method for calculating amount when formula type is diffenrence but this
	// method is not in use....

	public void calDifference(final ArrayList diffData, final Object[][] tableData,
			final double CTC) {
		try {
			double sum = 0.0;

			for (int k = 0; k < tableData.length; k++) {
				if (String.valueOf(tableData[k][4]).equals("A")) {
					sum += Double.parseDouble(String.valueOf(tableData[k][3]));
				} else if (String.valueOf(tableData[k][4]).equals("Q")) {
					sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 4;
				} else if (String.valueOf(tableData[k][4]).equals("H")) {
					sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 2;
				} else if (String.valueOf(tableData[k][4]).equals("M")) {
					sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 12;
				}
			}
			String cal = "0";
			if (CTC >= sum) {
				cal = "" + Math.round(((CTC - sum) / 12));
			}
			for (int i = 0; i < diffData.size(); i++) {
				int cnt = Integer.parseInt(String.valueOf(diffData.get(i)));
				//logger.info("cnt==============" + cnt);
				tableData[cnt][3] = cal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for executing formula when formula type is formula(fr).......
	public String executeFormula(final String sal_formula, final double CTC,
			final Object[][] tableData, final ArrayList frmData) {

		try {

			String str[] = sal_formula.split("#");
			String frml = "";
			for (int i = 0; i < str.length; i++) {

				/*
				 * Loop for checking string length which is split by #
				 */

				//logger.info("Printing Str[i] " + str[i]);
				if (str[i].equals("CTC")) {
					//logger.info("Inside CTC");
					frml += "" + CTC;
				}

				else {
					/*
					 * Loop for salary codes
					 */
					String flag = "false", strValue = "";
					int cnt = 1;

					if (str[i].equals("") || str[i].equals("null")) {
						strValue = str[i];
					} else if (str[i].length() == 1) {
						strValue = str[i];
					} else if (str[i].contains("C")) {
						strValue = str[i].substring(1, str[i].length());
					} else {
						strValue = str[i];
					}

					for (int z = 0; z < tableData.length; z++) {
						//logger
								//.info("-------------------------------------------strValue="
									//	+ strValue);

						if (String.valueOf(strValue).trim().equals("" + cnt))// *12)/100
						// (1*12)/100
						{
							//logger.info("checking for count=======" + z);
							for (int j = 0; j < tableData.length; j++) {
								if (String.valueOf(tableData[j][0]).trim()
										.equals("" + cnt)) {
									try {
										frml += Utility
												.twoDecimals(Double
														.parseDouble(String
																.valueOf(tableData[j][3])));
									} catch (Exception e) {
										e.printStackTrace();
									}
									flag = "true";
								}
							}
						}
						cnt++;
					}
					if (flag.equals("false")) {
						frml += "" + strValue;	// (1000*12)/100
						//logger.info("frml======>>>" + frml);
					}

				}
			}
			return expressionEvaluate(frml);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}

	// Method for evaluating expression ...........
	public String expressionEvaluate(final String formula) {
		try {
			XJep j = new XJep();
			try {
				// parse expression
				Node node = j.parse(formula);
				Node processed = j.preprocess(node);
				Object value = j.evaluate(processed);
				// double
				// vv=Math.round(Double.parseDouble(String.valueOf(value)));
				return "" + Utility.twoDecimals(String.valueOf(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "0";
	}

	public boolean unLockSal(final PromotionMaster promotion2) {
		// TODO Auto-generated method stub
		boolean flag = false;
		promotion2.setLockFlag("N");
		String lockflagQue = "UPDATE HRMS_PROMOTION SET LOCK_FLAG='N' WHERE PROM_CODE = ?"; //+ promotion2.getPromCode();
		Object[][] paraObj = new Object[1][1];
		paraObj[0][0] = promotion2.getPromCode();
		flag = getSqlModel().singleExecute(lockflagQue,paraObj);
		return flag;

	}

	public Object[][] getAppraisalData(final String empId) {
		Object[][] apprData = new Object[1][3];
		apprData[0][0] = "0";	// score
		apprData[0][1] = "";	// strength
		apprData[0][2] = "";	// weakness
		try {

			String apprQuery = " SELECT MAX(APPR_ID) FROM PAS_APPR_SCORE WHERE EMP_ID = ?"; // + empId;
			Object[] paraObj = new Object[1];
			paraObj[0] = empId;
			Object[][] apprCode = getSqlModel().getSingleResult(apprQuery, paraObj);

			if (apprCode != null && apprCode.length > 0) {

				String phaseQuery = " SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = ?"
						//+ String.valueOf(apprCode[0][0])
						+ " and APPR_PHASE_ORDER = "
						+ " (SELECT MAX(APPR_PHASE_ORDER) FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = ?"
						//+ String.valueOf(apprCode[0][0])
						+ " AND APPR_PHASE_STATUS = 'A')";
				
				paraObj = new Object[2];
				paraObj[0] = apprCode[0][0];
				paraObj[1] = apprCode[0][0];
				Object[][] phaseCode = getSqlModel().getSingleResult(phaseQuery, paraObj);

				String appraiserQUery = " SELECT PAS_APPR_APPRAISER.APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER "
						+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID =  PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID "
						+ " INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID =  PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID "
						+ " WHERE PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = ?"
						//+ empId
						+ " AND PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = ?"
						//+ String.valueOf(apprCode[0][0])
						+ " AND PAS_APPR_APPRAISER.APPR_PHASE_ID = ?"
						//+ String.valueOf(phaseCode[0][0])
						+ " "
						+ " ORDER BY PAS_APPR_APPRAISER.APPR_APPRAISER_LEVEL  DESC ";
				
				paraObj = new Object[3];
				paraObj[0] = empId;
				paraObj[1] = apprCode[0][0];
				paraObj[2] = phaseCode[0][0];
				Object[][] appraiserCode = getSqlModel().getSingleResult(appraiserQUery, paraObj);

				String scoreQuery = " SELECT NVL(APPR_FINAL_SCORE,'0') AS RATING FROM PAS_APPR_SCORE "
						+ " WHERE EMP_ID = ?"
						//+ empId
						+ " and APPR_ID = ?"
						//+ String.valueOf(apprCode[0][0])
						+ " AND APPR_SCORE_FINALIZE = 'Y'";

				paraObj = new Object[2];
				paraObj[0] = empId;
				paraObj[1] = apprCode[0][0];
				Object[][] apprScore = getSqlModel().getSingleResult(scoreQuery, paraObj);

				String commentQuery = " SELECT NVL(e1.APPR_CAREER_COMMENT,''), NVL(e2.APPR_CAREER_COMMENT,'')"
						+ " FROM PAS_APPR_CAREER_COMMENT e1, PAS_APPR_CAREER_COMMENT e2 "
						+ " WHERE e1.APPR_QUESTION_CODE = 44 and e2.APPR_QUESTION_CODE = 45 "
						+ " AND e1.APPR_ID = ?"			//+ String.valueOf(apprCode[0][0])
						+ " AND e1.APPR_EMP_ID = ?"		//+ empId
						+ " AND e1.APPR_APPRAISER_ID = ?" 	//+ String.valueOf(appraiserCode[0][0])
						+ " AND e1.APPR_PHASE = ?"			//+ String.valueOf(phaseCode[0][0])
						+ " AND e2.APPR_ID = ?"			//+ String.valueOf(apprCode[0][0])
						+ " AND e2.APPR_EMP_ID = ?" 	//+ empId
						+ " AND e2.APPR_APPRAISER_ID = ?"	//+ String.valueOf(appraiserCode[0][0])
						+ " AND e2.APPR_PHASE = ?";		//+ String.valueOf(phaseCode[0][0]) + " ";

				paraObj = new Object[8];
				paraObj[0] = apprCode[0][0];
				paraObj[1] = empId;
				paraObj[2] = appraiserCode[0][0];
				paraObj[3] = phaseCode[0][0];
				paraObj[4] = apprCode[0][0];
				paraObj[5] = empId;
				paraObj[6] = appraiserCode[0][0];
				paraObj[7] = phaseCode[0][0];
				
				Object[][] comment = getSqlModel().getSingleResult(commentQuery, paraObj);

				if (apprScore != null && apprScore.length > 0) { 
					apprData[0][0] = String.valueOf(apprScore[0][0]);	// score
				}

				if (comment != null && comment.length > 0) {
					apprData[0][1] = checkNull(String.valueOf(comment[0][0]));	// strength
					apprData[0][2] = checkNull(String.valueOf(comment[0][1]));	// weakness
				}

			}

		} catch (Exception e) {
			//logger.info("Exception in getting the appraisal data" + e);
			e.printStackTrace();
		}
		return apprData;
	}

	public void promotionDataList(final PromotionMaster bean,
			final HttpServletRequest request) {

		String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
				+ " NVL(TO_CHAR(HRMS_PROMOTION.EFFECT_DATE,'DD-MM-YYYY'),' '), "
				+ " DECODE(HRMS_PROMOTION.LOCK_FLAG,'Y','Locked','N','Unlocked') AS IS_LOCKED, "
				+ " HRMS_PROMOTION.PROM_CODE, HRMS_PROMOTION.EFFECT_DATE "  
				+ " FROM HRMS_PROMOTION " 
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PROMOTION.EMP_CODE)"
				+ " ORDER BY IS_LOCKED DESC, HRMS_PROMOTION.EFFECT_DATE DESC, HRMS_PROMOTION.PROM_CODE DESC"; 

		Object[][] objPromotionData = getSqlModel().getSingleResult(query);

		String[] pageIndex = Utility.doPaging(bean.getMyPage(),objPromotionData.length, 20);

		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		//logger.info(" pageIndex[0] ===" + pageIndex[0]);
		//logger.info(" bean.getMyPage(1)[0] ===" + bean.getMyPage());

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		if (objPromotionData != null && objPromotionData.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				PromotionMaster bean1 = new PromotionMaster();

				bean1.setEmployeeIdItt(String.valueOf(objPromotionData[i][0]));
				bean1.setEmpNameItt(String.valueOf(objPromotionData[i][1]));
				bean1.setEffectiveDateItt(String
						.valueOf(objPromotionData[i][2]));
				bean1.setStatusItt(String.valueOf(objPromotionData[i][3]));
				bean1.setPramotionCodeItt(String.valueOf(objPromotionData[i][4]));
				//System.out.println("######## Promo code #######"+String.valueOf(objPromotionData[i][4]));
				list.add(bean1);
			}
		}
		bean.setIteratorlist(list);

	}

	public void callforedit(final PromotionMaster promotion, final String pramotionCode) {
		
		try {
			String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,"
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(TO_CHAR(HRMS_PROMOTION.EFFECT_DATE,'DD-MM-YYYY'),' '),"
					+ " DECODE(HRMS_PROMOTION.LOCK_FLAG,'Y','Locked','N','Unlocked'),"
					+ " HRMS_PROMOTION.EMP_CODE, HRMS_CENTER.CENTER_NAME,"
					+ " NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HRMS_RANK.RANK_NAME,' '),"
					+ " NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(HRMS_DIVISION.DIV_NAME,' '),"
					+ " NVL(TO_CHAR(HRMS_PROMOTION.DATE_JOINING,'DD-MM-YYYY'),' '),"
					+ " NVL(TO_CHAR(HRMS_PROMOTION.PROM_DATE,'DD-MM-YYYY'),' '),"
					+ " NVL(HRMS_PROMOTION.BRANCH_CODE,'0'), NVL(HRMS_PROMOTION.DEPT_CODE,'0'),"
					+ " NVL(HRMS_PROMOTION.DESG_CODE,'0'), NVL(HRMS_PROMOTION.GRADE_CODE,'0'),"
					+ " NVL(HRMS_PROMOTION.DIV_CODE,'0'), NVL(HRMS_PROMOTION.REPORTING_TO,'0'),"
					+ " HRMS_PROMOTION.PROM_CODE"
					+ " FROM HRMS_PROMOTION "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PROMOTION.EMP_CODE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_PROMOTION.BRANCH_CODE)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_PROMOTION.DESG_CODE)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_PROMOTION.GRADE_CODE)"
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_PROMOTION.DEPT_CODE)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_PROMOTION.DIV_CODE)"
					+ " WHERE HRMS_PROMOTION.PROM_CODE = ?"; //+ pramotionCode;
			
			Object[] paraObj = new Object[1];
			paraObj[0] = pramotionCode;
			Object[][] promotionData = getSqlModel().getSingleResult(query, paraObj);	//  to get the record and update the data in double click in the list
			promotion.setEmpToken(String.valueOf(promotionData[0][0]));
			promotion.setEmpName(String.valueOf(promotionData[0][1]));
			promotion.setEffDate(String.valueOf(promotionData[0][2]));
			promotion.setLockStatus(String.valueOf(promotionData[0][3]));
			promotion.setEmpCode(String.valueOf(promotionData[0][4]));
			promotion.setBranch(String.valueOf(promotionData[0][5]));
			promotion.setDept(String.valueOf(promotionData[0][6]));
			promotion.setDesg(String.valueOf(promotionData[0][7]));
			promotion.setGrade(String.valueOf(promotionData[0][8]));
			promotion.setDiv(String.valueOf(promotionData[0][9]));
			promotion.setJoinDate(String.valueOf(promotionData[0][10]));
			promotion.setPromDate(String.valueOf(promotionData[0][11]));
			promotion.setBranchCode(String.valueOf(promotionData[0][12]));
			promotion.setDeptCode(String.valueOf(promotionData[0][13]));
			promotion.setDesgCode(String.valueOf(promotionData[0][14]));
			promotion.setGradeCode(String.valueOf(promotionData[0][15]));
			promotion.setDivCode(String.valueOf(promotionData[0][16]));
			promotion.setRepToCode(String.valueOf(promotionData[0][17]));
			promotion.setPromCode(String.valueOf(promotionData[0][18]));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		getDetails(promotion);
			 
	}

	public void deleteSelectedRecord(final String[] promotionCode) {
		try {
			// TODO Auto-generated method stub
			
			if (promotionCode != null && promotionCode.length > 0) {
				Object[][] promCodeObj = new Object[promotionCode.length][1];
				for (int i = 0; i < promotionCode.length; i++) {
					promCodeObj[i][0] = String.valueOf(promotionCode[i]);
					
				}
				String deleteQuery = " DELETE FROM HRMS_PROMOTION WHERE PROM_CODE=?";
				String deleteSalQuery = " DELETE FROM HRMS_PROMOTION_SALARY WHERE PROM_CODE=?";
				if (getSqlModel().singleExecute(deleteSalQuery, promCodeObj)) {
					getSqlModel().singleExecute(deleteQuery, promCodeObj);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}