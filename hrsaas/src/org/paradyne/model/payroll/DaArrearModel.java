package org.paradyne.model.payroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.paradyne.bean.payroll.DaArrear;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author Lakkichand
 * 
 *
 */
public class DaArrearModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	public String view(DaArrear daArrear) {
		String availEffDate = "false";
		try {
			String dadate = daArrear.getDaDate();
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			String month = String.valueOf((Integer.parseInt(sysDate.substring(
					2, 4)) - 1));

			daArrear.setSysDate(sysDate);

			Object[] selObj = new Object[3];
			selObj[0] = daArrear.getPayBillNo();
			selObj[1] = daArrear.getTypeCode();
			selObj[2] = daArrear.getDaDate();
			/*
			 * String daSelect = "SELECT DA_CODE,
			 * TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') , DA_RATE FROM
			 * HRMS_DA_PARAMETER WHERE DA_EFFECTIVE_DATE=TO_DATE('" + dadate +
			 * "','DD-MM-YYYY') "; Object da[][] =
			 * getSqlModel().getSingleResult(daSelect);
			 * 
			 * daArrear.setDaCode(String.valueOf(da[0][0]));
			 * daArrear.setEffectiveDate(String.valueOf(da[0][1]));
			 * daArrear.setDaRate(String.valueOf(da[0][2])); availEffDate =
			 * "true";
			 */
			try {
				String daArrearSelect = "select  HRMS_DA_ARREAR_DTL.EMP_ID ,HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME, "
						+ " DA_MONTH,DA_YEAR,DA_DRAWN,DA_DUE,DA_DIFF FROM HRMS_DA_ARREAR_HDR "
						+ " INNER JOIN HRMS_DA_ARREAR_DTL ON (HRMS_DA_ARREAR_DTL.DA_CODE=HRMS_DA_ARREAR_HDR.DA_CODE) "
						+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_DA_ARREAR_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ " WHERE DA_PAYBILL_ID=? AND DA_EMP_TYPE=? AND TO_CHAR(DA_EFFECT_DATE,'DD-MM-YYYY')=? "
						+ " ORDER BY HRMS_DA_ARREAR_DTL.EMP_ID";

				Object daArrearData[][] = getSqlModel().getSingleResult(
						daArrearSelect, selObj);
				ArrayList<DaArrear> daList = new ArrayList<DaArrear>();
				for (int i = 0; i < daArrearData.length; i++) {
					DaArrear bean = new DaArrear();
					bean.setEmpId(String.valueOf(daArrearData[i][0]));
					bean.setEmpToken(String.valueOf(daArrearData[i][1]));
					bean.setEmpName(String.valueOf(daArrearData[i][2]));
					bean.setDaMonth(String.valueOf(daArrearData[i][3]));
					bean.setDaMonthName(Utility.month(Integer.parseInt(String
							.valueOf(daArrearData[i][3]))));
					bean.setDaYear(String.valueOf(daArrearData[i][4]));
					bean.setPaidDa(String.valueOf(daArrearData[i][5]));
					bean.setDueDa(String.valueOf(daArrearData[i][6]));
					bean.setDiff(String.valueOf(daArrearData[i][7]));

					daList.add(bean);
				}
				daArrear.setDaList(daList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			availEffDate = "false";
		}
		return availEffDate;
	}

	public String calculate(DaArrear daArrear) {
		String availEffDate = "false";
		try {
			String dadate = daArrear.getDaDate();
			String month = String.valueOf((dadate.substring(3, 5)));
			String year = String.valueOf(dadate.substring(6, 10));
			logger.info("Month::::::;;;" + month);
			logger.info("year::::::;;;" + year);
			Object[] selObj = new Object[4];
			selObj[0] = month;
			selObj[1] = year;
			selObj[2] = daArrear.getPayBillNo();
			selObj[3] = daArrear.getTypeCode();
			String months_leadger = " SELECT DISTINCT SAL_MONTH ,SAL_YEAR,SAL_PAYBILL,SAL_EMP_TYPE FROM HRMS_SAL_LEDGER "
					+ " WHERE SAL_LOCK='L' AND SAL_MONTH>=? AND SAL_MONTH<=(SELECT TO_CHAR(SYSDATE,'MM') FROM DUAL ) "
					+ " AND SAL_YEAR>=? AND SAL_MONTH<=(SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL ) AND SAL_PAYBILL=? "
					+ " AND SAL_EMP_TYPE=? ORDER BY SAL_YEAR,SAL_MONTH";

			Object months_year[][] = getSqlModel().getSingleResult(
					months_leadger, selObj);
			if (months_year.length > 0) {
				availEffDate = "true";
			}
			Object[] emp_Obj = new Object[2];
			emp_Obj[0] = daArrear.getPayBillNo();
			emp_Obj[1] = daArrear.getTypeCode();
			logger.info("typeCode=======" + daArrear.getTypeCode());

			String empQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID ,HRMS_EMP_OFFC.EMP_TOKEN , "
					+ " NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| "
					+ " ' '||HRMS_EMP_OFFC.EMP_LNAME,' '), NVL(CREDIT_AMT,0) "
					+ " FROM HRMS_EMP_CREDIT  "
					+ " RIGHT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_CREDIT.EMP_ID  AND CREDIT_CODE=3 "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE EMP_PAYBILL ="
					+ daArrear.getPayBillNo()
					+ " AND EMP_TYPE="
					+ daArrear.getTypeCode()
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			Object emp_ids[][] = getSqlModel().getSingleResult(empQuery);

			Object[][] final_Object = new Object[emp_ids.length
					* months_year.length][9];
			int month_count = 0;

			for (int i = 0; i < months_year.length; i++) {

				String innerQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID ,SAL_CREDIT_CODE	,NVL(SAL_AMOUNT,0) "
						+ " FROM HRMS_SAL_CREDITS_"
						+ String.valueOf(months_year[i][1])
						+ " right JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"
						+ String.valueOf(months_year[i][1])
						+ ".EMP_ID AND SAL_CREDIT_CODE=3 AND SAL_MONTH="
						+ String.valueOf(months_year[i][0])
						+ " ) "
						+ " WHERE   "
						+ " EMP_PAYBILL="
						+ daArrear.getPayBillNo()
						+ " AND  EMP_TYPE="
						+ daArrear.getTypeCode()
						+ " ORDER BY HRMS_EMP_OFFC.EMP_ID";
				Object sal_Amt[][] = getSqlModel().getSingleResult(innerQuery);
				int sal_count = 0;
				for (int j = 0; j < emp_ids.length; j++) {
					if (j == 0) {
						sal_count = 0;
					} else {
						sal_count = sal_count + months_year.length;
					}
					final_Object[month_count + sal_count][0] = emp_ids[j][0];// emp_id
					final_Object[month_count + sal_count][1] = emp_ids[j][1];// emp_token
					final_Object[month_count + sal_count][2] = emp_ids[j][2];// emp_name
					final_Object[month_count + sal_count][3] = months_year[i][0];
					final_Object[month_count + sal_count][4] = Utility
							.month(Integer.parseInt(String
									.valueOf(months_year[i][0])));// month
					final_Object[month_count + sal_count][5] = String
							.valueOf(months_year[i][1]);// year
					final_Object[month_count + sal_count][6] = sal_Amt[j][2];// withdraw_amt
					final_Object[month_count + sal_count][7] = emp_ids[j][3];// due_amt
					final_Object[month_count + sal_count][8] = Double
							.parseDouble(String.valueOf(emp_ids[j][3]))
							- Double.parseDouble(String.valueOf(sal_Amt[j][2]));// diff

				}
				month_count++;
			}

			ArrayList<DaArrear> daList = new ArrayList<DaArrear>();
			for (int i = 0; i < final_Object.length; i++) {

				DaArrear bean = new DaArrear();
				bean.setEmpId(String.valueOf(final_Object[i][0]));
				bean.setEmpToken(String.valueOf(final_Object[i][1]));
				bean.setEmpName(String.valueOf(final_Object[i][2]));
				bean.setDaMonth(String.valueOf(final_Object[i][3]));
				bean.setDaMonthName(String.valueOf(final_Object[i][4]));
				bean.setDaYear(String.valueOf(final_Object[i][5]));
				bean.setPaidDa(String.valueOf(final_Object[i][6]));
				bean.setDueDa(String.valueOf(final_Object[i][7]));
				bean.setDiff(String.valueOf(final_Object[i][8]));

				daList.add(bean);
				/*
				 * logger.info("RRRRRRRRRRRRRR"+final_Object[i][0]);
				 * logger.info("RRRRRRRRRRRRRR_____________"+final_Object[i][5]);
				 * logger.info("RRRRRRRRRRRRRR___________"+final_Object[i][6]);
				 * logger.info("RRRRRRRRRRRRRR___________"+final_Object[i][7]);
				 */
			}
			daArrear.setDaList(daList);

		} catch (Exception e) {
			availEffDate = "false";
			e.printStackTrace();
		}
		return availEffDate;
	}

	public String save(String[] emp_id, String[] daMonth, String[] daYear,
			String[] paidDa, String[] dueDa, String[] dadiff, DaArrear daArrear) {

		String isLocked = "no";
		boolean result1 = false;
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		String sysDate = formater.format(date);
		daArrear.setSysDate(sysDate);
		Object[] selObj = new Object[3];
		selObj[0] = daArrear.getPayBillNo();
		selObj[1] = daArrear.getTypeCode();
		selObj[2] = daArrear.getDaDate();
		String selQuery = " SELECT  DA_CODE,DA_ISLOCKED FROM HRMS_DA_ARREAR_HDR WHERE DA_PAYBILL_ID=? AND  DA_EMP_TYPE=? "
				+ " AND TO_CHAR(DA_EFFECT_DATE,'DD-MM-YYYY')=? ";

		Object daCode[][] = getSqlModel().getSingleResult(selQuery, selObj);
		String delHdrQuery = " DELETE FROM HRMS_DA_ARREAR_HDR WHERE  DA_CODE=?";
		String delDtlQuery = " DELETE FROM HRMS_DA_ARREAR_DTL WHERE  DA_CODE=?";
		if (daCode.length > 0) {
			if (String.valueOf(daCode[0][1]).equals("N")) {

				Object[][] delObj = new Object[1][1];
				delObj[0][0] = daCode[0][0];
				boolean result = getSqlModel().singleExecute(delDtlQuery,
						delObj);

				if (result) {
					getSqlModel().singleExecute(delHdrQuery, delObj);
				}
				result1 = insertDetails(emp_id, daMonth, daYear, paidDa, dueDa,
						dadiff, daArrear);

			} else {
				isLocked = "yes";
			}

		} else {
			result1 = insertDetails(emp_id, daMonth, daYear, paidDa, dueDa,
					dadiff, daArrear);
		}

		return isLocked;

	}

	public boolean insertDetails(String[] emp_id, String[] daMonth,
			String[] daYear, String[] paidDa, String[] dueDa, String[] dadiff,
			DaArrear daArrear) {
		boolean result1 = false;
		// OBJECT TO INSERT INTO HDR TABLE
		Object[][] addObj = new Object[1][5];

		addObj[0][0] = daArrear.getDaDate();
		addObj[0][1] = daArrear.getSysDate();
		addObj[0][2] = daArrear.getDaRate();
		addObj[0][3] = daArrear.getPayBillNo();
		addObj[0][4] = daArrear.getTypeCode();

		// OBJECT TO INSERT INTO DTL TABLE
		Object[][] insertData = new Object[emp_id.length][6];
		for (int i = 0; i < emp_id.length; i++) {
			insertData[i][0] = emp_id[i];
			insertData[i][1] = daMonth[i];
			insertData[i][2] = daYear[i];
			insertData[i][3] = paidDa[i];
			insertData[i][4] = dueDa[i];
			insertData[i][5] = dadiff[i];

		}
		String sqlInsertHdr = "INSERT INTO  HRMS_DA_ARREAR_HDR (DA_CODE,DA_EFFECT_DATE,DA_PROCESS_DATE,DA_RATE,DA_PAYBILL_ID,DA_EMP_TYPE) "
				+ " VALUES((SELECT NVL(MAX(DA_CODE),0)+1 FROM HRMS_DA_ARREAR_HDR),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?) ";

		String sqlInsertDtl = " INSERT INTO HRMS_DA_ARREAR_DTL(DA_CODE,EMP_ID,DA_MONTH,DA_YEAR,DA_DRAWN	,DA_DUE,DA_DIFF	) "
				+ " VALUES((SELECT MAX(DA_CODE) FROM HRMS_DA_ARREAR_HDR),?,?,?,?,?,?)";

		boolean result2 = getSqlModel().singleExecute(sqlInsertHdr, addObj);

		if (result2) {
			result1 = getSqlModel().singleExecute(sqlInsertDtl, insertData);
		}
		return result1;
	}

	public boolean lockStatus(DaArrear daArrear) {
		Object[][] updateObj = new Object[1][3];
		updateObj[0][0] = daArrear.getPayBillNo();
		updateObj[0][1] = daArrear.getTypeCode();
		updateObj[0][2] = daArrear.getDaDate();
		String updateFlag = " UPDATE HRMS_DA_ARREAR_HDR SET DA_ISLOCKED='Y' WHERE DA_PAYBILL_ID=? AND  DA_EMP_TYPE=? "
				+ " AND TO_CHAR(DA_EFFECT_DATE,'DD-MM-YYYY')=?";

		boolean result = getSqlModel().singleExecute(updateFlag, updateObj);

		return result;
	}

}
