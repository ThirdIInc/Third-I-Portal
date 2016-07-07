package org.paradyne.model.payroll.salary;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.SalaryStatementBank;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class SalaryStatementBankModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);	
	
	/**
	 * The following function displays bankwise list with no of employees, amount, cheque no & cheque date
	 * @param salStat
	 * @param earningCode
	 * @param year
	 */
	NumberFormat formatter = new DecimalFormat("#0.00");
	public final void getBankwiseRecordsByEarningCode(final SalaryStatementBank salStat, final String earningCode, final String year, final String month) {
		try {
			String query = "";
			String arrearsQuery ="";
			Object[][] bankwiseData=null;
			Object[][] arrearsData=null;
			
			/**
			 * FOR OT
			 */
			if(salStat.getEarningType().equals("O")){
				query = "SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_OT_DTL.EMP_ID),SUM(HRMS_OT_DTL.NET_OT_AMT),TO_CHAR(PARENT_BANK.BANK_ID), NVL(PARENT_BANK.COVERING_LETTER_TEMPLATE,0)"
				+ " FROM HRMS_OT_DTL "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_OT_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID )" 
				+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE )" 
				+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
				+ " WHERE NET_OT_AMT  >0 AND SAL_PAY_MODE='T' AND OT_ID="+earningCode
				+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID, PARENT_BANK.COVERING_LETTER_TEMPLATE "
				+ " UNION ALL "
				+ " SELECT 'Cheque',COUNT(HRMS_OT_DTL.EMP_ID),SUM(HRMS_OT_DTL.NET_OT_AMT),'CH', 0"
				+ " FROM HRMS_OT_DTL "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_OT_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+ " WHERE NET_OT_AMT  >0  AND OT_ID="+earningCode+" AND SAL_PAY_MODE='H' HAVING COUNT(HRMS_OT_DTL.EMP_ID) >0"
				+ " UNION ALL "
				+ " SELECT 'Cash Payment',COUNT(HRMS_OT_DTL.EMP_ID),SUM(HRMS_OT_DTL.NET_OT_AMT),'CS',0"
				+ " FROM HRMS_OT_DTL "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_OT_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID )"
				+ " WHERE NET_OT_AMT >0 AND OT_ID="+earningCode+"  AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' or SAL_PAY_MODE is null or SAL_MICR_REGULAR is null ) HAVING COUNT(HRMS_OT_DTL.EMP_ID) > 0";
			
			bankwiseData = getSqlModel().getSingleResult(query);
			}
			/**
			 * FOR LEAVE ENCASHMENT
			 */
			else if(salStat.getEarningType().equals("L")){
				query = "SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID),SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT),TO_CHAR(PARENT_BANK.BANK_ID), NVL(PARENT_BANK.COVERING_LETTER_TEMPLATE,0)"
				+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID )" 
				+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE )" 
				+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
				+ " WHERE ENCASHMENT_ENCASH_AMOUNT  >0 AND SAL_PAY_MODE='T' AND ENCASHMENT_PROCESS_CODE="+earningCode
				+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID, PARENT_BANK.COVERING_LETTER_TEMPLATE "
				+ " UNION ALL "
				+ " SELECT 'Cheque',COUNT(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID),SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT),'CH', 0"
				+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+ " WHERE ENCASHMENT_ENCASH_AMOUNT  >0  AND ENCASHMENT_PROCESS_CODE="+earningCode+" AND SAL_PAY_MODE='H' HAVING COUNT(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) >0"
				+ " UNION ALL "
				+ " SELECT 'Cash Payment',COUNT(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID),SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT),'CS', 0"
				+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID=HRMS_SALARY_MISC.EMP_ID )"
				+ " WHERE ENCASHMENT_ENCASH_AMOUNT >0 AND ENCASHMENT_PROCESS_CODE="+earningCode+" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' or SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) HAVING COUNT(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) > 0";
			
			bankwiseData = getSqlModel().getSingleResult(query);
			}
			else if(salStat.getEarningType().equals("B")){
				query = "SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_BONUS_EMP.EMP_ID),SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),TO_CHAR(PARENT_BANK.BANK_ID), NVL(PARENT_BANK.COVERING_LETTER_TEMPLATE,0)"
				+ " FROM HRMS_BONUS_EMP "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_BONUS_EMP.EMP_ID=HRMS_SALARY_MISC.EMP_ID )" 
				+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE )" 
				+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
				+ " WHERE EMP_BONUS_TOTAL  >0 AND SAL_PAY_MODE='T' AND BONUS_CODE="+earningCode
				+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID, PARENT_BANK.COVERING_LETTER_TEMPLATE "
				+ " UNION ALL "
				+ " SELECT 'Cheque',COUNT(HRMS_BONUS_EMP.EMP_ID),SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),'CH',0"
				+ " FROM HRMS_BONUS_EMP "
				+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_BONUS_EMP.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+ " WHERE EMP_BONUS_TOTAL  >0  AND BONUS_CODE="+earningCode+" AND SAL_PAY_MODE='H' HAVING COUNT(HRMS_BONUS_EMP.EMP_ID) >0"
				+ " UNION ALL "
				+ " SELECT 'Cash Payment',COUNT(HRMS_BONUS_EMP.EMP_ID),SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),'CS',0"
				+ " FROM HRMS_BONUS_EMP "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_BONUS_EMP.EMP_ID=HRMS_SALARY_MISC.EMP_ID )"
				+ " WHERE EMP_BONUS_TOTAL >0 AND BONUS_CODE="+earningCode+" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' or SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) HAVING COUNT(HRMS_BONUS_EMP.EMP_ID) > 0";
			
			bankwiseData = getSqlModel().getSingleResult(query);
			}			
			else if(salStat.getEarningType().equals("S")){
								
				query = "SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_SALARY_"+year+".EMP_ID),SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),TO_CHAR(PARENT_BANK.BANK_ID), NVL(PARENT_BANK.COVERING_LETTER_TEMPLATE,0)"
					+ " FROM HRMS_SALARY_"+year
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID)" 
					+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE ) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
					+ " WHERE SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_LEDGER_CODE="+earningCode+" AND SAL_PAY_MODE='T'"
					+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID , PARENT_BANK.COVERING_LETTER_TEMPLATE"
					+ " UNION ALL "
					+ " SELECT 'Cheque',COUNT(HRMS_SALARY_MISC.EMP_ID),SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),'CH',0"
					+ " FROM HRMS_SALARY_"+year
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID )"
					+ " WHERE SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_LEDGER_CODE="+earningCode+" AND SAL_PAY_MODE='H' HAVING COUNT(HRMS_SALARY_"+year+".EMP_ID)>0"
					+ " UNION ALL"
					+ " SELECT 'Cash Payment',COUNT(HRMS_SALARY_"+year+".EMP_ID),SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),'CS', 0"
					+ " FROM HRMS_SALARY_"+year
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID  )"
					+ " WHERE SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_LEDGER_CODE="+earningCode+" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' or SAL_PAY_MODE is null or SAL_MICR_REGULAR is null ) HAVING COUNT(HRMS_SALARY_"+year+".EMP_ID)>0"
					+ " UNION ALL"
					+ " SELECT 'On-Hold',COUNT(HRMS_SALARY_"+year+".EMP_ID),SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),'OH',0"
					+ " FROM HRMS_SALARY_"+year
					//+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID  )"
					+ " WHERE SAL_NET_SALARY >0 AND SAL_ONHOLD = 'Y' AND SAL_LEDGER_CODE="+earningCode+" HAVING COUNT(HRMS_SALARY_"+year+".EMP_ID)>0";
				bankwiseData = getSqlModel().getSingleResult(query);
				
				arrearsQuery = " SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),TO_CHAR(PARENT_BANK.BANK_ID) FROM HRMS_ARREARS_"+year
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID)" 
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE ) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
					+ " WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAID_MONTH="+month+" AND ARREARS_PAID_YEAR="+year+" AND SAL_PAY_MODE='T' AND HRMS_ARREARS_"+year+".EMP_ID IN (SELECT EMP_ID FROM HRMS_SALARY_"+year+" WHERE SAL_LEDGER_CODE="+earningCode+")" 
					+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID " 
					+ " UNION ALL "  
					+ " SELECT 'Cheque',COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),'CH' FROM HRMS_ARREARS_"+year 
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID )" 
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					+ " WHERE ARREARS_PAY_IN_SAL='Y' AND SAL_PAY_MODE='H' AND ARREARS_PAID_MONTH="+month+" AND ARREARS_PAID_YEAR="+year+" AND HRMS_ARREARS_"+year+".EMP_ID IN (SELECT EMP_ID FROM HRMS_SALARY_"+year+" WHERE SAL_LEDGER_CODE="+earningCode+")"
					+ " HAVING COUNT(HRMS_ARREARS_"+year+".EMP_ID)>0 " 
					+ " UNION ALL "
					+ " SELECT 'Cash Payment',COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),'CS'" 
					+ " FROM HRMS_ARREARS_"+year+""
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID)" 
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					+ " WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAID_MONTH="+month+" AND ARREARS_PAID_YEAR="+year+" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL )" 
					+ " AND HRMS_ARREARS_"+year+".EMP_ID IN (SELECT EMP_ID FROM HRMS_SALARY_"+year+" WHERE SAL_LEDGER_CODE="+earningCode+")"
					+ " HAVING COUNT(HRMS_ARREARS_"+year+".EMP_ID)>0";
					//+ " UNION ALL "
					//+ " SELECT 'On-Hold',COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),'OH'" 
					//+ " FROM HRMS_ARREARS_"+year+""
					//+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					//+ " WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAID_MONTH="+month+" AND ARREARS_PAID_YEAR="+year+" " 
					//+ " AND HRMS_ARREARS_"+year+".EMP_ID IN (SELECT EMP_ID FROM HRMS_SALARY_"+year+" WHERE SAL_LEDGER_CODE="+earningCode+")"
					//+ " HAVING COUNT(HRMS_ARREARS_"+year+".EMP_ID)>0";
				
				arrearsData = getSqlModel().getSingleResult(arrearsQuery);
				
				/*Adding amount of same type*/
				
				if(arrearsData!=null && arrearsData.length >0){
					for (int i = 0; i < arrearsData.length; i++) {
						for (int j = 0; j < bankwiseData.length; j++) {
							if(String.valueOf(arrearsData[i][0]).equals(String.valueOf(bankwiseData[j][0]))){
								bankwiseData[j][2] = Double.parseDouble(String.valueOf(arrearsData[i][2]))+Double.parseDouble(String.valueOf(bankwiseData[j][2]));
							}
						}
					}
				}
				
			}else{
				
				query = "SELECT TO_CHAR(PARENT_BANK.BANK_NAME),COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),TO_CHAR(PARENT_BANK.BANK_ID), NVL(PARENT_BANK.COVERING_LETTER_TEMPLATE,0)"
					+ " FROM HRMS_ARREARS_"+year
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID )" 
					+ " INNER JOIN HRMS_BANK MAIN_BANK ON (SAL_MICR_REGULAR=MAIN_BANK.BANK_MICR_CODE )" 
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (MAIN_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)" 
					+ " WHERE SAL_PAY_MODE='T' AND ARREARS_CODE="+earningCode
					+ " GROUP BY BANK_NAME,PARENT_BANK.BANK_ID, PARENT_BANK.COVERING_LETTER_TEMPLATE "
					+ " UNION ALL "
					+ " SELECT 'Cheque',COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),'CH',0"
					+ " FROM HRMS_ARREARS_"+year 
					+ " INNER JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
					+ " WHERE ARREARS_CODE="+earningCode+" AND SAL_PAY_MODE='H' HAVING COUNT(HRMS_ARREARS_"+year+".EMP_ID) >0"
					+ " UNION ALL "
					+ " SELECT 'Cash Payment',COUNT(HRMS_ARREARS_"+year+".EMP_ID),SUM(HRMS_ARREARS_"+year+".ARREARS_NET_AMT),'CS',0"
					+ " FROM HRMS_ARREARS_"+year
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_ARREARS_"+year+".EMP_ID=HRMS_SALARY_MISC.EMP_ID )"
					+ " WHERE ARREARS_CODE="+earningCode+" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' or SAL_PAY_MODE is null or SAL_MICR_REGULAR is null ) HAVING COUNT(HRMS_ARREARS_"+year+".EMP_ID) > 0";
				
				bankwiseData = getSqlModel().getSingleResult(query);
			}
			
			if(bankwiseData!=null && bankwiseData.length>0){
				ArrayList list = new ArrayList();
				for (int i = 0; i < bankwiseData.length; i++) {
					SalaryStatementBank bean = new SalaryStatementBank();
					bean.setBankNameItt(String.valueOf(bankwiseData[i][0]));
					bean.setTotalEmpItt(String.valueOf(bankwiseData[i][1]));
					bean.setTotalEmpAmountItt(formatter.format(Double.parseDouble(String.valueOf(bankwiseData[i][2]))));
					bean.setBankCodeItt(String.valueOf(bankwiseData[i][3]));
					bean.setBankTemplateCodeItt(String.valueOf(bankwiseData[i][4]));
					logger.info("############## TEMPLATE CODE "+String.valueOf(bankwiseData[i][4]));
					
					list.add(bean);
				}
				salStat.setBankwiseList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function generates the report for the selected record & inserts the data into the respective table
	 * @param bean
	 * @param response
	 * @param earningCode
	 * @param bankCode
	 * @param chequeDt 
	 * @param chequeNum 
	 * @param totalAmount 
	 * @param totalRecords 
	 */
	public final String getBankStatement(final SalaryStatementBank bean, final HttpServletResponse response, final String earningCode, final String bankCode, final String totalRecords, final String totalAmount, final String chequeNum, final String chequeDt) {
		String message="Exception";
		try{
			
			Object[][] data = null;
			Object[][] finalObject = null;
			logger.info("bean.getMonth()="+bean.getHiddenMonth());
			if(bean.getEarningType().equals("O")){
				/**
				 * BANK STATEMENT FOR OT
				 */
				String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),'C',NVL(HRMS_OT_DTL.NET_OT_AMT,0), HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(HRMS_OT_DTL.NET_OT_AMT,0)||','||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_OT_DTL.EMP_ID "
					+ " FROM HRMS_OT_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND OT_ID="+earningCode+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE HRMS_OT_DTL.NET_OT_AMT >0  AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				finalObject = getSqlModel().getSingleResult(query);	
			}else if(bean.getEarningType().equals("L")){
				/**
				 * BANK STATEMENT FOR LEAVE ENCASHMENT
				 */
				String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),'C',NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT,0), HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT,0)||','||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID "
					+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ENCASHMENT_PROCESS_CODE="+earningCode+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT >0  AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				finalObject = getSqlModel().getSingleResult(query);	
			}
			else if(bean.getEarningType().equals("B")){
				/**
				 * BANK STATEMENT FOR BONUS
				 */
				String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),'C',NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0), HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0)||','||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_BONUS_EMP.EMP_ID "
					+ " FROM HRMS_BONUS_EMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_BONUS_EMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND BONUS_CODE="+earningCode+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE HRMS_BONUS_EMP.EMP_BONUS_TOTAL >0  AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
											
				finalObject = getSqlModel().getSingleResult(query);	
				//finalObject = Utility.removeColumns(finalObject,new int[] { 7 });
			}			
			else if(bean.getEarningType().equals("S")){
				String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),'C',NVL(HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY,0), HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY,0)||','||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID "
					+ " FROM HRMS_SALARY_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+earningCode+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				String innerQuery ="SELECT  HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID FROM HRMS_SALARY_"+bean.getEarningYear()
					//+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+earningCode+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID AND SAL_LEDGER_CODE="+earningCode+") "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode;
					//+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			
				data = getSqlModel().getSingleResult(query);
			
			
			String arrearsQuery = "SELECT  '', '','C',NVL(SUM(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT),0), '', '', '',"
					+ " HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID "
					+ " FROM HRMS_ARREARS_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " inner join HRMS_ARREARS_LEDGER on (HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+bean.getHiddenMonth()+" AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+bean.getEarningYear()+")"
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE ARREARS_PAY_IN_SAL='Y' AND SAL_PAY_MODE='T' AND PARENT_BANK.BANK_ID="+bankCode
					+" AND HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID IN ("+innerQuery+") GROUP BY HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID " ;
					//+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				//+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+bean.getHiddenMonth()+" AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+bean.getEarningYear()+" AND HRMS_ARREARS_LEDGER.ARREARS_TYPE IN ('M','P') AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL = 'Y' ";
			
			Object[][] arrearsObject = getSqlModel().getSingleResult(
						arrearsQuery);

				if (data != null && data.length > 0) {
					finalObject = Utility.consolidateArrearsObject(data,arrearsObject, 7, new int[] { 3 }, 8);
				}
					finalObject = Utility.removeColumns(finalObject,new int[] { 7 });
			}else{

				String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),'C',NVL(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT,0), HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT,0)||','||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID "
					+ " FROM HRMS_ARREARS_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
					+ " WHERE ARREARS_CODE="+earningCode+" AND PARENT_BANK.BANK_ID="+bankCode
					+ " AND HRMS_SALARY_MISC.SAL_PAY_MODE ='T'  ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				data = getSqlModel().getSingleResult(query);
				finalObject =data;
			}
			if(finalObject!=null && finalObject.length >0){
				
				message =generateDynamicTemplate(bean, response, earningCode, bankCode,finalObject);
				
				insertBankStatement(bean, bankCode, totalRecords, totalAmount, chequeNum, chequeDt);
				
			}else{
				message = "Records not available";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * This function generates report for the payment modes Cash & Cheque
	 * @param bean
	 * @param response
	 * @param earningCode
	 */
	
	public final void getBankStatementForCashOrCheque(final SalaryStatementBank bean, final HttpServletResponse response, final String earningCode,final String payMode) {
		String reportType = "Xls";
		String reportName = "Bank Statement";
		try{
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
			rg.setFName("Bank Statement");
			
			Object[][] data = null;
			Object[][] finalObject = null;
			
			if(bean.getEarningType().equals("O")){
				/**
				 * BANK STATEMENT FOR LEAVE ENCASHMENT
				 */
						String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_OT_DTL.NET_OT_AMT,0), HRMS_OT_DTL.EMP_ID"
							+ " FROM HRMS_OT_DTL "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
							+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
							+ " WHERE HRMS_OT_DTL.NET_OT_AMT >0  AND OT_ID="+earningCode;
							if(payMode.equals("CS")){
								query +=" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
							}else{
								query +=" AND SAL_PAY_MODE='H'";
							}
							query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
							finalObject = getSqlModel().getSingleResult(query);					
			}
			else if(bean.getEarningType().equals("L")){
				/**
				 * BANK STATEMENT FOR LEAVE ENCASHMENT
				 */
						String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT,0), HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID"
							+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
							+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
							+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT >0  AND ENCASHMENT_PROCESS_CODE="+earningCode;
							if(payMode.equals("CS")){
								query +=" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
							}else{
								query +=" AND SAL_PAY_MODE='H'";
							}
							query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
							finalObject = getSqlModel().getSingleResult(query);					
			}			
			else if(bean.getEarningType().equals("B")){
			/**
			 * BANK STATEMENT FOR BONUS
			 */
					String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0), HRMS_BONUS_EMP.EMP_ID"
						+ " FROM HRMS_BONUS_EMP "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_BONUS_EMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
						+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
						+ " WHERE HRMS_BONUS_EMP.EMP_BONUS_TOTAL >0  AND BONUS_CODE="+earningCode;
						if(payMode.equals("CS")){
							query +=" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
						}else{
							query +=" AND SAL_PAY_MODE='H'";
						}
						query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
						finalObject = getSqlModel().getSingleResult(query);					
		}
			
		else if(bean.getEarningType().equals("S")){
				
				if(payMode.equals("OH")){			// for onhold employees
					String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY,0), HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID"
					+ " FROM HRMS_SALARY_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
					//+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'Y' AND SAL_LEDGER_CODE="+earningCode;
				
					
					query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				data = getSqlModel().getSingleResult(query);
				String innerQuery ="SELECT HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID FROM HRMS_SALARY_"+bean.getEarningYear()
				//+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID)"
				+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'Y' AND SAL_LEDGER_CODE="+earningCode;
			
			
			String arrearsQuery = "SELECT '', '', NVL(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT,0), HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID"
				+ " FROM HRMS_ARREARS_"+bean.getEarningYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " INNER JOIN HRMS_ARREARS_LEDGER  ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_CODE)" 
				+ " WHERE ARREARS_PAY_IN_SAL='Y' AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+bean.getHiddenMonth()+" AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+bean.getEarningYear()+" AND HRMS_ARREARS_LEDGER.ARREARS_TYPE IN ('M','P') AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL = 'Y' "
				+" AND HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID IN ("+innerQuery+")";
			
			Object[][] arrearsObject = getSqlModel().getSingleResult(arrearsQuery);
			
			
				if (data != null && data.length > 0) {
					finalObject = Utility.consolidateArrearsObject(data,arrearsObject, 3, new int[] { 2 }, 4);
					finalObject = Utility.removeColumns(finalObject,new int[] { 3 });
				}
				
				}else{		// for cash or cheque employees 
					
					String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY,0), HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID"
					+ " FROM HRMS_SALARY_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_LEDGER_CODE="+earningCode;
				
					if(payMode.equals("CS")){
						query +=" AND SAL_PAY_MODE!='H'  AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
					}else{
						query +=" AND SAL_PAY_MODE='H'";
					}
					query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				data = getSqlModel().getSingleResult(query);
				String innerQuery ="SELECT HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID FROM HRMS_SALARY_"+bean.getEarningYear()
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID)"
				+ " WHERE HRMS_SALARY_"+bean.getEarningYear()+".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND SAL_LEDGER_CODE="+earningCode;
			
				if(payMode.equals("CS")){
					innerQuery +=" AND SAL_PAY_MODE!='H'   AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
				}else{
					innerQuery +=" AND SAL_PAY_MODE='H'";
				}
			
			String arrearsQuery = "SELECT '', '', NVL(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT,0), HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID"
				+ " FROM HRMS_ARREARS_"+bean.getEarningYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " INNER JOIN HRMS_ARREARS_LEDGER  ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_CODE)" 
				+ " WHERE ARREARS_PAY_IN_SAL='Y' AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+bean.getHiddenMonth()+" AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+bean.getEarningYear()+" AND HRMS_ARREARS_LEDGER.ARREARS_TYPE IN ('M','P') AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL = 'Y' "
				+" AND HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID IN ("+innerQuery+")";
			
			Object[][] arrearsObject = getSqlModel().getSingleResult(arrearsQuery);
			
			
				if (data != null && data.length > 0) {
					finalObject = Utility.consolidateArrearsObject(data,arrearsObject, 3, new int[] { 2 }, 4);
					finalObject = Utility.removeColumns(finalObject,new int[] { 3 });
				}
				}
				
			}else {
				String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' "
					+ " '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_ARREARS_"+bean.getEarningYear()+".ARREARS_NET_AMT,0)"
					+ " FROM HRMS_ARREARS_"+bean.getEarningYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_ARREARS_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE ARREARS_CODE="+earningCode;
					
					if(payMode.equals("CS")){
						query +=" AND SAL_PAY_MODE!='H'   AND (SAL_PAY_MODE='C' OR SAL_PAY_MODE IS NULL OR SAL_MICR_REGULAR IS NULL ) ";
					}else{
						query +=" AND SAL_PAY_MODE='H'";
					}					
				query +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				data = getSqlModel().getSingleResult(query);
				finalObject =data;
			}
			if(finalObject!=null && finalObject.length >0){
				String month="";
				 try {
					month = Utility.month(Integer.parseInt(bean
							.getHiddenMonth()));
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] colName={"Employee Code", "Employee Name", "Amount"};	
				int[] align={0, 0, 1};
				int[] cellWidth={20, 20, 30};
				Object [][] title = new Object[1][3];
				title [0][0] = "";
				title [0][1] = "";
				title [0][2] = "Bank Statement for the Division "+bean.getDivisionName()+" ,"+month+" - "+bean.getEarningYear();  

				int [] cols1 = {20,20,40};
				int [] align1 = {0,0,1};
				/*
				 * Following table shows the heading of the bank statement report
				 */
				rg.tableBodyNoCellBorder(title,cols1,align1,1); 
				rg.addText("\n",0,0,0);
				/*
				 * Following table shows the bank statement report  details in xls format
				 */
				rg.xlsTableBody(colName, finalObject, cellWidth, align);
				
			}else{
				rg.addText("Records are not available. ", 0,0,10);
				rg.createReport(response);
			}
			rg.createReport(response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function inserts the data in HRMS_BANK_STATEMENT table when bank statement is generated
	 * 
	 * @param bean
	 * @param bankCode
	 * @param totalRecords
	 * @param totalAmount
	 * @param chequeNum
	 * @param chequeDt
	 */
	
	public final void insertBankStatement(final SalaryStatementBank bean, final String bankCode, final String totalRecords, final String totalAmount, final String chequeNum, final String chequeDt){
		try {
			
			String deleteQuery="DELETE FROM HRMS_BANK_STATEMENT WHERE PAYMENT_TYPE=? AND PAYMENT_CODE=? AND PAYMENT_BANK_ID=?";
			Object [][] insertObject = new Object[1][10];
			Object [][] deleteObject = new Object[1][3];
			
			deleteObject[0][0] = bean.getEarningType();
			deleteObject[0][1] = bean.getEarningCode();
			deleteObject[0][2] = bankCode;
			
			
			insertObject[0][0] = bean.getHiddenMonth();
			insertObject[0][1] = bean.getEarningYear();
			insertObject[0][2] = bean.getEarningType();
			insertObject[0][3] = bean.getEarningCode();
			insertObject[0][4] = totalRecords;
			insertObject[0][5] = totalAmount;
			insertObject[0][6] = chequeNum;
			insertObject[0][7] = chequeDt;
			insertObject[0][8] = bankCode;
			insertObject[0][9] = bean.getUserEmpId();
			
			String insertQuery = "INSERT INTO HRMS_BANK_STATEMENT(PAYMENT_ID, PAYMENT_MONTH, PAYMENT_YEAR, PAYMENT_TYPE, PAYMENT_CODE, PAYMENT_COUNT, PAYEMNT_AMOUNT,"
				+" CHEQUE_NO, CHEQUE_DATE, PAYMENT_BANK_ID,PAYEMNT_GENERATED_BY,PAYEMNT_SYSDATE)"
					+ " VALUES((SELECT NVL(MAX(PAYMENT_ID),0)+1 FROM HRMS_BANK_STATEMENT), ?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))";
			if(getSqlModel().singleExecute(deleteQuery, deleteObject)){
				getSqlModel().singleExecute(insertQuery, insertObject);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
/** This method generates the dynamic template.
 * @param bean
 * @param response
 * @param earningCode
 * @param bankCode
 * @param data
 * @return
 */
public final String generateDynamicTemplate(final SalaryStatementBank bean, final HttpServletResponse response, final String earningCode,final String bankCode,final Object [][]data ){
		
		String reportType = "Xls";
		String reportName = "Bank Statement";
		String divisionQuery = " SELECT NVL(DIV_ADDRESS1,''),NVL(DIV_ADDRESS2,''),NVL(DIV_ADDRESS3,''), DIV_ACCOUNT_NUMBER FROM HRMS_DIVISION WHERE DIV_ID = " + bean.getDivisionCode() + " ";
		Object[][] division = this.getSqlModel().getSingleResult(divisionQuery);
		String divAddress="";
		if(division!=null && division.length>0){
		 divAddress=String.valueOf(division[0][0])+"\n"+String.valueOf(division[0][1])+"\n"+String.valueOf(division[0][2]);
		}
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
		rg.setFName(reportName);
		String reportTitle="";
		String accFormat="";
		/*****************************
		  	STATEMENT_IFSC_CODE	--	0
			STATEMENT_CR_DR	--		1
			STATEMENT_ACCOUNT --	2
			STATEMENT_AMOUNT--		3
			STATEMENT_EMP_CODE--	4
			STATEMENT_NAME--		5
			STATEMENT_NARRATION--	6
			STATEMENT_SOL_ID--		7
			STATEMENT_TRANS_DESC--	8
			STATEMENT_TRANS_PART--	9
			STATEMENT_TITLE--		10
			STATEMENT_DR_ACCOUNT --	11

		 ******************************/
		
		ArrayList<String> columnNames=new ArrayList<String>();
		Object [][]fileObj =getSqlModel().getSingleResult("SELECT 'IFSC Code#'||STATEMENT_IFSC_CODE, 'Credit#'||STATEMENT_CR_DR, 'Account#'||STATEMENT_ACCOUNT, 'Amount#'||STATEMENT_AMOUNT, 'Employee Code#'||STATEMENT_EMP_CODE, "
				+" 'Narration#'||STATEMENT_NAME, 'Account,Credit,Amount,Narration#'||STATEMENT_NARRATION,'DR Account#'||STATEMENT_DR_ACCOUNT, 'SOL ID#'||STATEMENT_SOL_ID, " +
				" 'TRAN DESC#'||STATEMENT_TRANS_DESC, 'TRAN PART#'||STATEMENT_TRANS_PART, 'Currency Code#'||STATEMENT_CURRENCY, STATEMENT_TITLE, NVL(STATEMENT_ACC_FORMAT,'')  FROM HRMS_BANK WHERE BANK_ID="+bankCode);
		for (int i = 0; i < data.length; i++) {
			data[i][6] = String.valueOf(data[i][1])+","+String.valueOf(data[i][2])+","+String.valueOf(data[i][3])+","+String.valueOf(data[i][5]);
			
		}
		if(fileObj !=null && fileObj.length >0){
			reportTitle = String.valueOf(fileObj[0][12]);
			accFormat = String.valueOf(fileObj[0][13]);
			for (int i = 0; i < fileObj[0].length-2; i++) {
				if(String.valueOf(fileObj[0][i]).split("#").length >1){
					columnNames.add(String.valueOf(fileObj[0][i]));
				}
			}
		}
		if(columnNames.size()==0){
			
			return "Statement template not defined in the Bank master";
		}
		else{
		try {
		
		/*String query ="SELECT  NVL(HRMS_BANK.BANK_IFSC_CODE,' '), NVL(SAL_ACCNO_REGULAR,' '),'C',NVL(SAL_NET_SALARY,0), EMP_TOKEN, EMP_FNAME||' "
			+ " '||EMP_MNAME||' '||EMP_LNAME,"
			+ " NVL(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID "
			+ " FROM HRMS_SALARY_"+bean.getEarningYear()
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+earningCode+")"
			+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
			+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
			+ " WHERE SAL_NET_SALARY >0 AND PARENT_BANK.BANK_ID="+bankCode
			+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
		
		Object [][]data = getSqlModel().getSingleResult(query);*/
		
		String month=Utility.month(Integer.parseInt(bean.getHiddenMonth()));

		String []colName =new String[columnNames.size()];
		int[] align=new int[columnNames.size()];
		int[] cellWidth=new int[columnNames.size()];
		String [] format=new String[columnNames.size()];
		for (int i = 0; i < format.length; i++) {
			format[i]="";
		}
		
		Object [][]finalObj =new Object[data.length][columnNames.size()];
		
		for (int i = 0; i < columnNames.size(); i++) {
			int sourceIndexNo=-1;
			int destIndexNo=-1;
			
			String []colDetails=columnNames.get(i).split("#");
			destIndexNo =Integer.parseInt(colDetails[1])-1;
			colName[destIndexNo]=colDetails[0];
			if(colDetails[0].equals("IFSC Code")){
				sourceIndexNo =0;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=20;
			} else if(colDetails[0].equals("Account")){
				sourceIndexNo =1;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=20;
				format[destIndexNo]=accFormat;
			}
			else if(colDetails[0].equals("Credit")){
				sourceIndexNo =2;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=20;
			}
			else if(colDetails[0].equals("Amount")){
				sourceIndexNo =3;
				align[destIndexNo]=2;
				cellWidth[destIndexNo]=20;
			}
			else if(colDetails[0].equals("Employee Code")){
				sourceIndexNo =4;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=20;
			}
			else if(colDetails[0].equals("Narration")){
				sourceIndexNo =5;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
			}
			else if(colDetails[0].equals("Account,Credit,Amount,Narration")){
				sourceIndexNo =6;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
			}else if(colDetails[0].equals("DR Account")){
				sourceIndexNo =-1;
				format[destIndexNo]=accFormat;
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
				for (int j = 0; j < finalObj.length; j++) {
					finalObj[j][destIndexNo]= String .valueOf(division[0][3]); 
				}
			}
			else if(colDetails[0].equals("Currency Code")){
				sourceIndexNo =-1;
				for (int j = 0; j < finalObj.length; j++) {
					finalObj[j][destIndexNo]= "INR"; 
				}
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
			}
			else if(colDetails[0].equals("TRAN DESC")){
				sourceIndexNo =-1;
				for (int j = 0; j < finalObj.length; j++) {
					finalObj[j][destIndexNo]= ""+month+" "+bean.getEarningYear(); 
				}
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
			}
			else if(colDetails[0].equals("TRAN PART")){
				sourceIndexNo =-1;
				for (int j = 0; j < finalObj.length; j++) {
					finalObj[j][destIndexNo]= "Salary"; 
				}
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
			}
			//=INT(IF(LEN(A3)<9,0,IF(LEN(A3)>12,LEFT(A3,4),LEFT(A3,LEN(A3) - 8))))
			else if(colDetails[0].equals("SOL ID")){
				sourceIndexNo =-1;
				for (int j = 0; j < finalObj.length; j++) {
					String sol_id="";
					if(String.valueOf(data[j][1]).length()<9){
						sol_id ="0000";
					}else if(String.valueOf(data[j][1]).length()>12){
						sol_id =String.valueOf(data[j][1]).substring(0,4);
					}else{
						sol_id =String.valueOf(data[j][1]).substring(0,String.valueOf(data[j][1]).length()-8);
					}
					/*if(sol_id.length()<4){
						int length=sol_id.length();
						for (int k = 0; k < 4-length; k++) {
							sol_id = "0"+sol_id;
						}
					}*/
					finalObj[j][destIndexNo]= sol_id; 
					
				}
				align[destIndexNo]=0;
				cellWidth[destIndexNo]=30;
				format[destIndexNo]="0000";
			}
			
			if(sourceIndexNo >=0) {
				for (int j = 0; j < finalObj.length; j++) {
					finalObj[j][destIndexNo]= data[j][sourceIndexNo]; 
				}
			}
			
		}
			
			
			reportTitle = reportTitle.replaceAll("<#DIVISION#>", bean.getDivisionName());
			reportTitle = reportTitle.replaceAll("<#MONTH#>", month);
			reportTitle = reportTitle.replaceAll("<#YEAR#>", bean.getEarningYear());
			reportTitle = reportTitle.replaceAll("<#ADDRESS#>", divAddress);
			//String colName[]={"IFSC Code","Account","Credit","Amount","Employee Code","Narration","Account,Credit,Amount,Narration"};	
			Object [][] title = new Object[1][3];
			title [0][0] = "";
			title [0][1] = "";
			//title [0][2] = "Bank Statement for the Division "+bean.getDivName()+" ,"+month+" - "+bean.getEarningYear();  
			title [0][2] = reportTitle;  

			int [] cols1 = {20,20,40};
			int [] align1 = {0,0,1};
			/*
			 * Following table shows the heading of the bank statement report
			 */
			rg.tableBodyNoCellBorder(title,cols1,align1,1); 
			rg.addText("\n",0,0,0);
			/*
			 * Following table shows the bank statement report  details in xls format
			 */
			rg.xlsTableBodyForStatement(colName, finalObj, cellWidth, align,format);
			rg.createReport(response);
			return "SUCCESS";
		
		}catch (Exception e) {
			e.printStackTrace();
			return "Exception";
		}
		}
	
	}

/*public String generateDynamicTemplateOld(SalaryStatementBank bean, HttpServletResponse response, String earningCode, String dataPath,String client,String bankCode ){
	
	String reportType = "Xls";
	String reportName = "Bank Statement";
	String downloadFileName = "";
		
	org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
	rg.setFName(reportName);
	
	Object [][]fileObj =getSqlModel().getSingleResult("SELECT NVL(SAL_STATEMENT_TEMPLATE,'') FROM HRMS_BANK WHERE BANK_ID="+bankCode);
	if(fileObj !=null && fileObj.length >0){
		downloadFileName = String.valueOf(fileObj[0][0]);
	}
	if(downloadFileName.equals("") || downloadFileName.equals(null)){
		return "Statement template not defined in the master";
	}else{
	String downloadFilePath = dataPath + "/DataMigration/Templates/" + downloadFileName;
	logger.info("downloadFilePath=="+downloadFilePath);
	try {
	FileInputStream uploadXls= new FileInputStream(new File(
				downloadFilePath));
	
	HSSFWorkbook workbook = new HSSFWorkbook(uploadXls);
	
	HSSFSheet sheet = workbook.getSheetAt(0);
	
	 * Code for report title
	 
	HSSFRow rowForTitle = sheet.getRow(0);
	int titleNo = rowForTitle.getLastCellNum();
	ArrayList<String> titleList= new ArrayList<String>();
	String reportTitle="";
	for (int i = 0; i < titleNo; i++) {
		HSSFCell cellForTitle = rowForTitle.getCell((short) i);

		if (cellForTitle != null) {
			String cellValueTitle = cellForTitle
					.getStringCellValue();

			logger.info("cellValueTitle=="+cellValueTitle);
			reportTitle = cellValueTitle;
			break;
		}
	}
	
	
	 * Code to get the columns
	 
	
	HSSFRow rowForColNames = sheet.getRow(3);
	int totalCols = rowForColNames.getLastCellNum();
	ArrayList<String> columnNames = new ArrayList<String>();

	for (int i = 0; i < totalCols; i++) {
		HSSFCell cellForColName = rowForColNames.getCell((short) i);

		if (cellForColName != null) {
			String cellValueForColName = cellForColName
					.getStringCellValue();

			logger.info("col Name=="+cellValueForColName);
				columnNames.add(cellValueForColName);
		
		}
	}
	
	
	 * Code for report title
	
	String query ="SELECT  NVL(BANK_IFSC_CODE,' '), NVL(SAL_ACCNO_REGULAR,' '),'C',NVL(SAL_NET_SALARY,0), EMP_TOKEN, EMP_FNAME||' "
		+ " '||EMP_MNAME||' '||EMP_LNAME,"
		+ " NVL(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID "
		+ " FROM HRMS_SALARY_"+bean.getEarningYear()
		+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getEarningYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+earningCode+")"
		+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
		+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
		+ " INNER JOIN HRMS_BANK PARENT_BANK ON (HRMS_BANK.BANK_PARENT_ID=PARENT_BANK.BANK_ID)"
		+ " WHERE SAL_NET_SALARY >0 AND PARENT_BANK.BANK_ID="+bankCode
		+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
	
	Object [][]data = getSqlModel().getSingleResult(query);
	
	String month=Utility.month(Integer.parseInt(bean.getMonth()));

	String []colName =new String[columnNames.size()];
	int[] align=new int[columnNames.size()];
	int[] cellWidth=new int[columnNames.size()];
	int[] format=new int[columnNames.size()];
	for (int i = 0; i < format.length; i++) {
		format[i]=0;
	}
	
	Object [][]finalObj =new Object[data.length][columnNames.size()];
	
	for (int i = 0; i < columnNames.size(); i++) {
		int indexNo=-1;
		colName[i]=columnNames.get(i);
		if(columnNames.get(i).equals("IFSC Code")){
			indexNo =0;
			align[i]=0;
			cellWidth[i]=20;
		} else if(columnNames.get(i).equals("Account")){
			indexNo =1;
			align[i]=0;
			cellWidth[i]=20;
			format[i]=1;
		}
		else if(columnNames.get(i).equals("Credit")){
			indexNo =2;
			align[i]=0;
			cellWidth[i]=20;
		}
		else if(columnNames.get(i).equals("Amount")){
			indexNo =3;
			align[i]=2;
			cellWidth[i]=20;
		}
		else if(columnNames.get(i).equals("Employee Code")){
			indexNo =4;
			align[i]=0;
			cellWidth[i]=20;
		}
		else if(columnNames.get(i).equals("Narration")){
			indexNo =5;
			align[i]=0;
			cellWidth[i]=30;
		}
		else if(columnNames.get(i).equals("Account,Credit,Amount,Narration")){
			indexNo =6;
			align[i]=0;
			cellWidth[i]=30;
		}
		else if(columnNames.get(i).equals("Currency Code")){
			indexNo =-1;
			for (int j = 0; j < finalObj.length; j++) {
				finalObj[j][i]= "INR"; 
			}
			align[i]=0;
			cellWidth[i]=30;
		}
		else if(columnNames.get(i).equals("TRAN_DESC")){
			indexNo =-1;
			for (int j = 0; j < finalObj.length; j++) {
				finalObj[j][i]= ""+month+" "+bean.getEarningYear(); 
			}
			align[i]=0;
			cellWidth[i]=30;
		}
		else if(columnNames.get(i).equals("TRAN_PART")){
			indexNo =-1;
			for (int j = 0; j < finalObj.length; j++) {
				finalObj[j][i]= "Salary"; 
			}
			align[i]=0;
			cellWidth[i]=30;
		}
		//=INT(IF(LEN(A3)<9,0,IF(LEN(A3)>12,LEFT(A3,4),LEFT(A3,LEN(A3) - 8))))
		else if(columnNames.get(i).equals("SOL_ID")){
			indexNo =-1;
			
			for (int j = 0; j < finalObj.length; j++) {
				String sol_id="";
				if(String.valueOf(data[j][1]).length()<9){
					sol_id ="0000";
				}else if(String.valueOf(data[j][1]).length()>12){
					sol_id =String.valueOf(data[j][1]).substring(0,4);
				}else{
					sol_id =String.valueOf(data[j][1]).substring(0,String.valueOf(data[j][1]).length()-8);
				}
				finalObj[j][i]= sol_id; 
			}
			align[i]=0;
			cellWidth[i]=30;
			format[i]=2;
		}
		if(indexNo >=0)
		for (int j = 0; j < finalObj.length; j++) {
			finalObj[j][i]= data[j][indexNo]; 
		}
		
	}
		
		
		reportTitle = reportTitle.replaceAll("<#DIVISION#>", bean.getDivisionName());
		reportTitle = reportTitle.replaceAll("<#MONTH#>", month);
		reportTitle = reportTitle.replaceAll("<#YEAR#>", bean.getEarningYear());
		//String colName[]={"IFSC Code","Account","Credit","Amount","Employee Code","Narration","Account,Credit,Amount,Narration"};	
		Object [][] title = new Object[1][3];
		title [0][0] = "";
		title [0][1] = "";
		//title [0][2] = "Bank Statement for the Division "+bean.getDivName()+" ,"+month+" - "+bean.getEarningYear();  
		title [0][2] = reportTitle;  

		int [] cols1 = {20,20,40};
		int [] align1 = {0,0,1};
		
		 * Following table shows the heading of the bank statement report
		 
		rg.tableBodyNoCellBorder(title,cols1,align1,1); 
		rg.addText("\n",0,0,0);
		
		 * Following table shows the bank statement report  details in xls format
		 
		rg.xlsTableBodyForStatement(colName, finalObj, cellWidth, align,format);
		rg.createReport(response);
		return "SUCCESS";
	
	}catch (Exception e) {
		e.printStackTrace();
		return "Exception";
	}
}
}*/


}
