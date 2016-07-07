package org.paradyne.model.payroll.incometax;

import java.util.ArrayList;
import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Varun
 * Date: 05/03/2008
 */
public class TdsCalculationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	
	/**
	 * this method is used to get the values of the filters used.
	 * @param tdsCal
	 */

	public void getFilters(TdsCalculation tdsCal) {
		try {
			String query = "SELECT DECODE(CONF_BRN_FLAG,'Y','true','N','false'),DECODE(CONF_DEPT_FLAG,'Y','true','N','false'),DECODE(CONF_PAYBILL_FLAG,'Y','true','N','false')"
					+ ",DECODE(CONF_EMPTYPE_FLAG,'Y','true','N','false'),DECODE(CONF_DIVISION_FLAG,'Y','true','N','false') FROM HRMS_SALARY_CONF";
			Object[][] result = getSqlModel().getSingleResult(query);
			tdsCal.setBranchFlag(String.valueOf(result[0][0]));
			tdsCal.setDepartmentFlag(String.valueOf(result[0][1]));
			tdsCal.setPaybillFlag(String.valueOf(result[0][2]));
			tdsCal.setEmptypeFlag(String.valueOf(result[0][3]));
			tdsCal.setDivisionFlag(String.valueOf(result[0][4]));

		} catch (Exception e) {
			logger.info("Exception in getFilters method");
		}
	}//end of getFilters method
	
	
	/**
	 * This method is used to get the slabs depending upon the year and the slab type.
	 * @param frmYear--Entered from year
	 * @param toYear--Entered to year
	 * @param genderType--either Male, female
	 * @return taxSlab-- slab object is returned.
	 */

	public Object[][] getTaxSlab(String frmYear, String toYear,String genderType) {
		Object[][] taxSlab = null;
		try {
			String queryMen = "select TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where "
					+ " TAX_FROM_YEAR="
					+ frmYear
					+ " and TAX_to_YEAR="
					+ toYear
					+ " and TAX_EMP_TYPE='"
					+ genderType
					+ "' order by tax_percent";
			taxSlab = getSqlModel().getSingleResult(queryMen);
		} catch (Exception e) {
			logger.error("exception in getTaxSlab",e);
		} //end of catch
		return taxSlab;
	}//end of getTaxSlab method

	/**
	 * This method is used to get the values of the TDS parameters
	 * @return taxParameters
	 */
	public Object[][] getTaxParameters(TdsCalculation tdsCal) {
		Object[][] taxParameters = null;
		try {
			String parameters = "SELECT TDS_DEBIT_CODE,TDS_SURCHARGE,TDS_EDU_CESS,nvl(TDS_SRCITIZEN_AGE,65),TDS_SURCHARGE_LIMIT_AMT, "
					+ " TDS_HRA_CREDITCODE,TDS_HRAEXEMPT_PAIDCOND1,TDS_HRAEXEMPT_PAIDCOND2,TDS_HRAEXEMPT_PAIDCOND3,TDS_HRAEXEMPT_UNPAIDCOND1"
					+ " ,TDS_HRAEXEMPT_UNPAIDCOND2,TDS_HRAEXEMPT_UNPAIDCOND3,TDS_HRAEXEMPT_INVCODE,TDS_REBATEMAX_AMOUNT,TDS_EMPPF_INVCODE,TDS_HRAEXEMPT_COND1MET, TDS_REBATE_ENABLE, TDS_REBATE_AMOUNT, TDS_REBATE_INCOME_LIMIT  FROM HRMS_TAX_PARAMETER "
					//+ " WHERE TDS_CODE =(SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER) ";
					+ " WHERE TDS_FINANCIALYEAR_FROM= "+tdsCal.getFromYear();
			taxParameters = getSqlModel().getSingleResult(parameters);
		} catch (Exception e) {
			logger.error("exception in getTaxParameters",e);
		} //end of catch
		return taxParameters;
	}//end of getTaxParamters
	
	/**
	 * This method is called for getting the data of all the investment depending upon the chapter.
	 * @param empId
	 * @param frmYear
	 * @param toYear
	 * @return invChapType
	 */

	public Object[][] getTotalInvestment(String empId, String frmYear,
			String toYear) {
		/*String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,nvl(INV_LIMIT1,0),NVL(INV_VALID_AMOUNT,0),INV_CHAPTER,INV_OTHER_FLAG, "
				+ " DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive')  FROM HRMS_TAX_INVESTMENT "
				+ " inner join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="
				+ empId
				+ " "
				+ " and INV_FINYEAR_FROM="
				+ frmYear
				+ " and INV_FINYEAR_TO="
				+ toYear
				+ " ) and HRMS_TAX_INVESTMENT.INV_CODE NOT IN (select DEBIT_EXEMPT_INV_CODE from hrms_debit_head where DEBIT_EXEMPT_INV_CODE is not null)  "
				+ "  order by HRMS_TAX_INVESTMENT.INV_CODE";*/
		String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,nvl(INV_LIMIT1,0), "
			+ " NVL(CASE  "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ") "
			+ " THEN NVL(INV_VERIFIED_AMOUNT,INV_VALID_AMOUNT) "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ")  "
			+ " THEN CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END   "
			+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN NVL(INV_VALID_AMOUNT,0)" +
			  " END,0) AS INVESTMENT_AMOUNT  "
			+ " ,INV_CHAPTER,INV_OTHER_FLAG "
			+ " ,DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive') "
			+ " FROM HRMS_TAX_INVESTMENT    "
			+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
			+ " AND HRMS_EMP_INVESTMENT.EMP_ID = "+empId+" "
			+ "  AND INV_FINYEAR_FROM="
			+ frmYear
			+ "  "
			+ " AND INV_FINYEAR_TO="
			+ toYear
			+ ") " +
			 "  AND HRMS_TAX_INVESTMENT.INV_CODE NOT IN(SELECT DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD "
				+" WHERE DEBIT_EXEMPT_INV_CODE IS NOT NULL)ORDER BY HRMS_EMP_INVESTMENT.EMP_ID";
		
		
		
		Object[][] invChapType = getSqlModel().getSingleResult(query);
		return invChapType;
	}//end of getTotalInvestment method.
	
	public Object[][] getTotalInvestment1(String empId, String frmYear,
			String toYear) {
		Object[][] invChapType = null;
		try {
			/*String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,nvl(INV_LIMIT1,0),NVL(INV_VALID_AMOUNT,0),INV_CHAPTER,INV_OTHER_FLAG, "
					+ " DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive')  FROM HRMS_TAX_INVESTMENT "
					+ " left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="
					+ empId
					+ " "
					+ " and INV_FINYEAR_FROM="
					+ frmYear
					+ " and INV_FINYEAR_TO="
					+ toYear
					+ " ) "
					+ "  order by HRMS_TAX_INVESTMENT.INV_CODE";*/
			
			/*String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME||'-('||INV_SECTION||')',nvl(INV_LIMIT1,0), "
				+ " NVL(CASE  " +
				  " WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0)"
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") "
				+ " THEN NVL(INV_VERIFIED_AMOUNT,INV_VALID_AMOUNT) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ")  "
				+ " THEN NVL(INV_VALID_AMOUNT,0) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN NVL(INV_VALID_AMOUNT,0)" +
				 "  END,0) AS INVESTMENT_AMOUNT  "
				+ " ,INV_CHAPTER,INV_OTHER_FLAG "
				+ " ,DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive'),NVL(INV_IS_INCLUDE_IN_80C_LIMIT,'N') "
				+ " FROM HRMS_TAX_INVESTMENT    "
				+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
				+ " AND HRMS_EMP_INVESTMENT.EMP_ID = "+empId+" "
				+ "  AND INV_FINYEAR_FROM="
				+ frmYear
				+ "  "
				+ " AND INV_FINYEAR_TO="
				+ toYear
				+ ")  ";*/
			
			
			String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME||'-('||INV_SECTION||')',NVL(INV_LIMIT1,0), "
				+" NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") AND INV_IS_VERIFIED='Y'"
				+ " THEN LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))   "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ")  "
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL"
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				//THEN NVL(INV_VALID_AMOUNT,0)" +
				+ " END,0) AS INVESTMENT_AMOUNT  "
				+ " ,INV_CHAPTER,INV_OTHER_FLAG "
				+ " ,DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive'),NVL(INV_IS_INCLUDE_IN_80C_LIMIT,'N') "
				+ " FROM HRMS_TAX_INVESTMENT    "
				+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
				+ " AND HRMS_EMP_INVESTMENT.EMP_ID ="
				+ empId
				+ "  AND INV_FINYEAR_FROM="
				+ frmYear
				+ "  "
				+ " AND INV_FINYEAR_TO="
				+ toYear
				+ ")  "
				//+ " AND HRMS_TAX_INVESTMENT.INV_CODE NOT IN(SELECT DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD "
				//+ " WHERE DEBIT_EXEMPT_INV_CODE IS NOT NULL)"
				+" ORDER BY HRMS_EMP_INVESTMENT.EMP_ID";
			invChapType = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getTotalInvestment1",e);
		} //end of catch
		return invChapType;
	}//end of getTotalInvestment method.

	/**
	 * 
	 * @param totalInvest
	 * @param type-- it contaions the type of Inv Chapter
	 * @return invObject
	 */
	private Object[][] getInvestments(Object[][] totalInvest, String type,String includeInLimit) {

		// type===>> it contains the type of Investment Chapter
		int cnt = 0;

		try {
			// this loop is run to increment the cnt
			for (int i = 0; i < totalInvest.length; i++) {
				// this if condition is checked for the type...for e:g if in Object
				// totalInvest, "EXEMPT" is 5 times
				// than the cnt is incremented 5 times.
				if (String.valueOf(totalInvest[i][4]).equals(type) && String.valueOf(totalInvest[i][7]).equals(includeInLimit)) {
					cnt++;
				}//end of if
			}//end of for looop
		} catch (Exception e) {
			logger.error("exception in totalInvest loop  in getInvestments mehtod",e);
		} //end of catch
		// this invObject is created based on the cnt which is incremented above
		Object invObject[][] = new Object[cnt][7];
		cnt = 0;

		try {
			// this loop is run to set all the values like Inv Code, Inv Name,Inv
			// Limt,Inv Amount for that particular Chapter Type
			for (int i = 0; i < totalInvest.length; i++) {
				// /in this if condition all the values of that particular chapter
				// is set in the Object invObject
				if (String.valueOf(totalInvest[i][4]).equals(type)&& String.valueOf(totalInvest[i][7]).equals(includeInLimit)) {
					invObject[cnt][0] = String.valueOf(totalInvest[i][0]);
					invObject[cnt][1] = String.valueOf(totalInvest[i][1]);
					invObject[cnt][2] = String.valueOf(totalInvest[i][2]);
					invObject[cnt][3] = String.valueOf(totalInvest[i][3]);
					invObject[cnt][4] = String.valueOf(totalInvest[i][4]);
					invObject[cnt][5] = String.valueOf(totalInvest[i][5]);
					invObject[cnt][6] = String.valueOf(totalInvest[i][6]);
					cnt++;
				}//end of if

			}//end of for loop
		} catch (Exception e) {
			logger.error("exception in totalInvest loop  in getInvestments mehtod",e);
		} //end of catch
		return invObject;
	}//end of getInvestments method
	
	/**
	 * This method is used to get the sum total amount of the debits
	 * @param debittotalInv-- total of all the debits
	 * @param chapType-- type of chapter
	 * @return debitAmt
	 */

	private double getDebitInvestments(Object[][] debittotalInv, String chapType) {

		double debitAmt = 0.0;

		for (int i = 0; i < debittotalInv.length; i++) {
			// in this if condition it checks whether the debits belongs to that
			// particular Chapter.
			if (String.valueOf(debittotalInv[i][1]).equals(chapType)) {
				debitAmt += Double.parseDouble(String
						.valueOf(debittotalInv[i][0]));
			}//end of if
		}//end of for loop
		return debitAmt;
	}//end of getDebitInvestments method

	/**
	 * this method is used by Tds Calculator.......
	 * @param invChapter
	 * @param taxParameters
	 * @return totalChapAmount--sum total of all the investments in that chapter
	 */
	
	public double getInvAmt(Object[][] invChapter, Object[][] taxParameters) {
		double totalChapAmount = 0.0;
		/**
		 * this loop is run to calculate total Investment Amount for that
		 *  particular Chapter.
		 	invChapter[i][3]==>> contains Investment_valid_Amount from
		 	HRMS_TAX_INVESTMENT
		 	Here taxParameters[0][12] contains HRA Inv Code and taxParameters[0][14] contains Emp Pf code
		 	which is define in Tds Parameter.
		 */
		try{	
			for (int i = 0; i < invChapter.length; i++) {
	
				if (!((String.valueOf(taxParameters[0][12]).trim().equals(""
						+ String.valueOf(invChapter[i][0]) + "")) || (String
						.valueOf(taxParameters[0][14]).trim().equals(""
						+ String.valueOf(invChapter[i][0]) + "")))) {
						if(String.valueOf(invChapter[i][5]).equals("D")){
							totalChapAmount -= Math.round(Double.parseDouble(String
									.valueOf(invChapter[i][3])));
						}
						else{
							totalChapAmount += Math.round(Double.parseDouble(String
									.valueOf(invChapter[i][3])));
						}
					
				}//end of if
			}//end of for loop
		} catch (Exception e) {
			logger.error("exception in invChapter loop",e);
		} //end of catch
		return totalChapAmount;
	}// end of getInvAmt method

	/**
	 * this method is used by Tds Calculation form....
	 * @param invChapter
	 * @return totalChapAmount
	 */
	public double getInvAmt(Object[][] invChapter) {
		double totalChapAmount = 0.0;
		// this loop is run to calculate total Investment Amount for that
		// particular Chapter.
		// invChapter[i][3]==>> contains Investment_valid_Amount from
		// HRMS_TAX_INVESTMENT
		try{
			for (int i = 0; i < invChapter.length; i++) {
				if(String.valueOf(invChapter[i][5]).equals("D")){
					totalChapAmount -= Math.round(Double.parseDouble(String
							.valueOf(invChapter[i][3])));
				}
				else{
					totalChapAmount += Math.round(Double.parseDouble(String
							.valueOf(invChapter[i][3])));
				}
				
			}//end of for loop
		} catch (Exception e) {
			logger.error("exception in invChapter loop",e);
		} //end of catch
		return Math.round(totalChapAmount);
	}//end of getInvAmt method

	/**
	 *  this method is used to set the list of the slabs
	 * @param seniorSlab
	 * @param tdsCal
	 * @return null
	 */
	public String taxSlab(Object[][] seniorSlab, TdsCalculation tdsCal) {
		ArrayList<Object> viewList = new ArrayList<Object>();
		logger.info("7");
		try{
			for (int i = 0; i < seniorSlab.length; i++) {
				TdsCalculation getList = new TdsCalculation();
				getList.setSlabFrmAmt(String.valueOf(seniorSlab[i][0]));
				getList.setSlabToAmt(String.valueOf(seniorSlab[i][1]));
				getList.setSlabTaxRate(String.valueOf(seniorSlab[i][2]));
				getList.setSlabAmt("0");
				getList.setSlabTax("0");
				viewList.add(getList);
			} //end of loop
		} catch (Exception e) {
			logger.error("exception in invChapter loop",e);
		} //end of catch
		tdsCal.setTdslabList(viewList);
		return null;
	}//end of taxSlab method

	/*
	 * public String[][] process(TdsCalculation tdsCal){
	 * 
	 * String frmYear = tdsCal.getFromYear(); String toYear =
	 * tdsCal.getToYear(); String genderType = tdsCal.getEmpGender(); Object[][]
	 * taxSlab=null; String empAge1 = tdsCal.getEmpAge(); int empAge =0;
	 * 
	 * Object[][] taxParameters = getTaxParameters();
	 * 
	 * if(empAge1.equals(null)|| empAge1.equals("null") || empAge1.equals("")){
	 * empAge=0; } else{
	 * logger.info("tdsCal.getEmpAge()=============="+tdsCal.getEmpAge());
	 * empAge = Integer.parseInt(String.valueOf(tdsCal.getEmpAge())); }
	 * 
	 * if(taxParameters==null){ taxParameters =new Object[1][1]; } if(empAge >
	 * Integer.parseInt(String.valueOf(taxParameters[0][3]))){
	 * taxSlab=getTaxSlab(frmYear,toYear,"S"); taxSlab(taxSlab,tdsCal); } else{
	 * if(genderType.equals("M")){ taxSlab=getTaxSlab(frmYear,toYear,"M");
	 * taxSlab(taxSlab,tdsCal); } else if(genderType.equals("F") ||
	 * genderType.equals("O")){ taxSlab=getTaxSlab(frmYear,toYear,"F");
	 * taxSlab(taxSlab,tdsCal); } }
	 * 
	 * 
	 * double
	 * taxableIncome=Double.parseDouble(String.valueOf(tdsCal.getNetTaxableIncome()));
	 * 
	 * if(taxableIncome > 0){
	 * tdsCal.setNetTaxableIncome(String.valueOf(taxableIncome)); } else{
	 * taxableIncome=0; tdsCal.setNetTaxableIncome(String.valueOf(0)); }
	 * 
	 * double totalTax = getTaxAmount(taxableIncome, taxSlab);
	 * tdsCal.setTaxOnIncome(String.valueOf(Math.round(totalTax)));
	 * 
	 * String[][] slabAmt = setSlab(taxableIncome,taxSlab,tdsCal); String[][]
	 * result = new String[slabAmt.length+3][2]; for (int i = 0; i <
	 * slabAmt.length; i++) { result[i][0] = slabAmt[i][0]; result[i][1] =
	 * slabAmt[i][1]; } double educess = geteducess(totalTax,taxParameters);
	 * tdsCal.setEduCess(String.valueOf(Math.round(educess)));
	 * 
	 * double
	 * surcharge=getsurcharge(taxableIncome,(totalTax+educess),taxParameters);
	 * tdsCal.setSurCharge(String.valueOf(Math.round(surcharge)));
	 * 
	 * double netTax=0.0; netTax = totalTax + educess + surcharge;
	 * tdsCal.setNetTax(String.valueOf(Math.round(netTax)));
	 * 
	 * double taxPerMonth =0.0; taxPerMonth = (netTax -
	 * Double.parseDouble(tdsCal.getTaxPaid()))/Double.parseDouble(tdsCal.getRemainMonths());
	 * 
	 * if(taxPerMonth <0){ taxPerMonth=0; tdsCal.setTaxPerMonth("0"); } else{
	 * tdsCal.setTaxPerMonth(String.valueOf(Math.round(taxPerMonth))); }
	 * 
	 * result[slabAmt.length][0] =
	 * String.valueOf(Utility.twoDecimals(totalTax)); result[slabAmt.length][1] =
	 * String.valueOf(Utility.twoDecimals(educess)); result[slabAmt.length+1][0] =
	 * String.valueOf(Utility.twoDecimals(surcharge));
	 * result[slabAmt.length+1][1] =
	 * String.valueOf(Utility.twoDecimals(netTax)); result[slabAmt.length+2][0] =
	 * String.valueOf(Utility.twoDecimals(taxPerMonth));
	 * result[slabAmt.length+2][1] = String.valueOf(0);
	 * 
	 * return result; }
	 */

	/**
	 * this method is used to get the debit amount from the emp debit table and the tax investment data
	 * return invDebAdd
	 */
	private Object[][] getDebInvAddChap(String empID) {
		Object[][] invDebAdd=null;
		try {
			String query = "SELECT 0,INV_CHAPTER,INV_CODE,INV_NAME,HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_EMP_DEBIT "
					+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE= HRMS_EMP_DEBIT.DEBIT_CODE  "
					+ " INNER JOIN HRMS_TAX_INVESTMENT ON HRMS_TAX_INVESTMENT.INV_CODE= HRMS_DEBIT_HEAD.DEBIT_EXEMPT_INV_CODE "
					+ " WHERE HRMS_EMP_DEBIT.DEBIT_CODE IN "
					+ " (SELECT DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_ISEXEMPT='Y') AND EMP_ID="
					+ empID
					+ " "
					+ " AND INV_CHAPTER IN ('EXEMPT','OTHER','VI','VI-A') ORDER BY INV_CHAPTER ";
			invDebAdd = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getDebInvAddChap "+e);
		}

		return invDebAdd;
	}//end of getDebInvAddChap method
	
	/**
	 * This method is used to set the list of Exempt chapter
	 * @param exempt
	 * @param tdsCal
	 * @param exemptAmt
	 * @param debitexempt
	 * @param debitInvAdd
	 * @param hraFinalAmt
	 * @param taxParaHraInvCode
	 */

	public void exempt(Object[][] exempt, TdsCalculation tdsCal,
			double exemptAmt,
			double hraFinalAmt, String taxParaHraInvCode) {
		double amt = 0.0;
		double abc, totAmt = 0.0;
		//logger.info("taxParaHraInvCode========in exempt====="+taxParaHraInvCode);
		ArrayList<Object> viewExemtList = new ArrayList<Object>();
		try{
			for (int i = 0; i < exempt.length; i++) { //loop a
				//logger.info("exempt[i][0]   :"+exempt[i][0]);
				//logger.info("exempt[i][0]   :"+exempt[i][1]);
				//logger.info("exempt[i][0]   :"+exempt[i][2]);
				//logger.info("exempt[i][0]   :"+exempt[i][3]);
				//logger.info("hraFinalAmt   :"+hraFinalAmt);
				TdsCalculation getList = new TdsCalculation();
				getList.setInvExemptId(String.valueOf(exempt[i][0]));
				getList.setInvExemptName(String.valueOf(exempt[i][1]));
				Object investmentLimit = exempt[i][2];
				if (investmentLimit == null)
					getList.setInvExemptLimit("0");
				else
					getList.setInvExemptLimit(String.valueOf(exempt[i][2]));
	
				Object investmentAmt = exempt[i][2];
				if (investmentAmt == null)
					getList.setInvExemptAmt("0");
				else
					// logger.info("exempt[i][3]========in exempt====="+exempt[i][3]);
					getList.setInvExemptAmt(String.valueOf(Math.round(Double.parseDouble(String
							.valueOf(exempt[i][3])))));
				 //logger.info("getList.getInvExemptAmt=======in exempt====="+getList.getInvExemptAmt());
				/*for (int j = 0; j < debitInvAdd.length; j++) {//loop x
					//logger.info("in for");
					if (String.valueOf(exempt[i][0]).trim().equals(
							String.valueOf(debitInvAdd[j][2]).trim())) {
						amt = Double.parseDouble(String.valueOf(debitInvAdd[j][0]));
						abc = Double.parseDouble(String.valueOf(getList
								.getInvExemptAmt()));
						totAmt = amt + abc;
						getList.setInvExemptAmt(String.valueOf(Math.round(Double.parseDouble(String
								.valueOf(totAmt)))));
					}//end of nested if
				}//end of nested for loop x
	*/			
				/** This if statement commented on 12/11/08 because for this in Tds Calculator the Hra amt is not
				 * displayed in the list.
				 * 
				 */
				/*if (String.valueOf(exempt[i][0]).trim().equals(
						taxParaHraInvCode.trim())) {
					//logger.info("exempt[i][0]========in exempt====="+exempt[i][0]);
					totAmt += hraFinalAmt;
					getList.setInvExemptAmt(Utility.twoDecimals(totAmt));
				}//end of nested if
				 */			
				viewExemtList.add(getList);
			}//end of for loop "a"
		} catch (Exception e) {
			logger.error("exception in exempt loop",e);
		} //end of catch
		tdsCal.setTotalExemptInvAmt(String.valueOf(Math.round(exemptAmt 
				+ hraFinalAmt)));
		tdsCal.setEmpExemptInvList(viewExemtList);
	}//end of exempt method

	/**
	 * this method is used to calculate the Other chapter investment
	 * @param other
	 * @param tdsCal
	 * @param otherAmt
	 * @param debitOther
	 * @param debitInvAdd
	 */
	public void other(Object[][] other, TdsCalculation tdsCal, double otherAmt) {
		double amt = 0.0;
		double abc, totAmt = 0.0;
		ArrayList<Object> viewOtherList = new ArrayList<Object>();
		try{
			for (int i = 0; i < other.length; i++) {//loop a
	
				TdsCalculation getOtherList = new TdsCalculation();
				getOtherList.setInvOtherId(String.valueOf(other[i][0]));
				getOtherList.setInvOtherName(String.valueOf(other[i][1]));
	
				Object investmentLimit = other[i][2];
				if (investmentLimit == null)
					getOtherList.setInvOtherLimit("0");
				else
					getOtherList.setInvOtherLimit(String.valueOf(other[i][2]));
	
				Object investmentAmt = other[i][2];
				if (investmentAmt == null)
					getOtherList.setInvOtherAmt("0");
				else
					getOtherList.setInvOtherAmt(String.valueOf(Math.round(Double.parseDouble(String
							.valueOf(other[i][3])))));
				
				getOtherList.setOtherFlag(String.valueOf(other[i][5]));
				getOtherList.setOtherDecodeFlag(String.valueOf(other[i][6]));
	
				/*for (int j = 0; j < debitInvAdd.length; j++) {//loop x
					if (String.valueOf(other[i][0]).equals(
							"" + String.valueOf(debitInvAdd[j][2]) + "")) {
						amt = Double.parseDouble(String.valueOf(debitInvAdd[j][0]));
						abc = Double.parseDouble(String.valueOf(getOtherList
								.getInvOtherAmt()));
						totAmt = amt + abc;
						getOtherList.setInvOtherAmt(String.valueOf(Math.round(Double.parseDouble(String
								.valueOf(totAmt)))));
					}//end of nested if
				}//end of nested for loop x
	*/
				viewOtherList.add(getOtherList);
			}//end of loop a
		} catch (Exception e) {
			logger.error("exception in other loop",e);
		} //end of catch
		tdsCal.setTotalOtherInvAmt(String.valueOf(Math.round(otherAmt)));
		tdsCal.setEmpOtherInvList(viewOtherList);
	}// end of other method

	/**
	 * for Para chapter
	 * @param para
	 * @param tdsCal
	 * @param paraAmt
	 * @param debitInvAdd
	 */
	public void para(Object[][] para, TdsCalculation tdsCal, double paraAmt,
			Object[][] debitInvAdd) {
		double amt = 0.0;
		double abc, totAmt = 0.0;
		ArrayList<Object> viewParaList = new ArrayList<Object>();
		try{
			for (int i = 0; i < para.length; i++) {//loop a
				TdsCalculation getParaList = new TdsCalculation();
				getParaList.setInvParaId(String.valueOf(para[i][0]));
				getParaList.setInvParaName(String.valueOf(para[i][1]));
	
				Object investmentLimit = para[i][2];
				if (investmentLimit == null)
					getParaList.setInvParaLimit("0");
				else
					getParaList.setInvParaLimit(String.valueOf(para[i][2]));
	
				Object investmentAmt = para[i][2];
				if (investmentAmt == null)
					getParaList.setInvParaAmt("0");
				else
					getParaList.setInvParaAmt(String.valueOf(Math.round(Double.parseDouble(String
							.valueOf(para[i][3])))));
	
				for (int j = 0; j < debitInvAdd.length; j++) {//loop x
					if (String.valueOf(para[i][0]).equals(
							"" + String.valueOf(debitInvAdd[j][2]) + "")) {
						amt = Double.parseDouble(String.valueOf(debitInvAdd[j][0]));
						abc = Double.parseDouble(String.valueOf(getParaList
								.getInvParaAmt()));
						totAmt = amt + abc;
						getParaList.setInvParaAmt(String.valueOf(Math.round(Double.parseDouble(String
								.valueOf(totAmt)))));
					}//end of nested if
				}//end of nested for loop x
				viewParaList.add(getParaList);
			}//end of for loop a
		} catch (Exception e) {
			logger.error("exception in para loop",e);
		} //end of catch
		tdsCal.setTotalParaInvAmt(String.valueOf(Math.round(paraAmt)));
		tdsCal.setEmpParaInvList(viewParaList);
	}//end of para method

	/**
	 * for rebate chapter
	 * @param rebate
	 * @param tdsCal
	 * @param rebateAmt
	 * @param debitRebate
	 * @param debitInvAdd
	 * @param maxRebateAmt
	 */
	public void rebate(Object[][] rebate, TdsCalculation tdsCal,
			double rebateAmt,String maxRebateAmt,double prePFAmt) {
		double amt = 0.0;
		double abc, totAmt = 0.0;
		ArrayList<Object> viewRebateList = new ArrayList<Object>();
		try{
			for (int i = 0; i < rebate.length; i++) { // loop a
				TdsCalculation getRebateList = new TdsCalculation();
				getRebateList.setInvRebateId(String.valueOf(rebate[i][0]));
				getRebateList.setInvRebateName(String.valueOf(rebate[i][1]));
	
				Object investmentLimit = rebate[i][2];
				if (investmentLimit == null)
					getRebateList.setInvRebateLimit("0");
				else
					getRebateList.setInvRebateLimit(String.valueOf(rebate[i][2]));
	
				Object investmentAmt = rebate[i][2];
				if (investmentAmt == null)
					getRebateList.setInvRebateAmt("0");
				else
					getRebateList.setInvRebateAmt(String.valueOf(Math.round(Double.parseDouble(String
							.valueOf(rebate[i][3])))));
	
				/*for (int j = 0; j < debitInvAdd.length; j++) {//loop x
	
					if (String.valueOf(rebate[i][0]).equals(
							"" + String.valueOf(debitInvAdd[j][2]) + "")) {
						amt = Double.parseDouble(String.valueOf(debitInvAdd[j][0]));
						abc = Double.parseDouble(String.valueOf(getRebateList
								.getInvRebateAmt()));
						totAmt = amt + abc;
						getRebateList.setInvRebateAmt(String.valueOf(Math.round(Double.parseDouble(String
								.valueOf(totAmt)))));
					}// end of nested if
				}//end of loop x
	*/
				viewRebateList.add(getRebateList);
			} //end of loop a
		} catch (Exception e) {
			logger.error("exception in rebate loop",e);
		} //end of catch

		if (!(maxRebateAmt == null || maxRebateAmt.equals("")
				|| maxRebateAmt.equals("null") || maxRebateAmt.equals(null))) {// a
			tdsCal.setRebateLimit(maxRebateAmt);
			if (Double.parseDouble(maxRebateAmt) > (rebateAmt+prePFAmt)) {
				tdsCal.setTotalRebateInvAmt(String.valueOf(Math.round(rebateAmt)));
			} //end of nested if
			else {
				tdsCal.setTotalRebateInvAmt(String.valueOf(Math.round(Double.parseDouble(maxRebateAmt))));
			} //end of nested else 
		} //end of "a" if 
		else {
			tdsCal
					.setTotalRebateInvAmt(String.valueOf(Math.round(rebateAmt)));
			tdsCal.setRebateLimit("No Limit");
		}//end of else

		tdsCal.setEmpRebateInvList(viewRebateList);
	}//end of rebate statement

	/**
	 * 
	 * @param via
	 * @param tdsCal
	 * @param viaAmt
	 * @param debitVia
	 * @param debitInvAdd
	 */
	public void via(Object[][] via, TdsCalculation tdsCal, double viaAmt) {
		double amt = 0.0;
		double abc, totAmt = 0.0;
		ArrayList<Object> viewVIAList = new ArrayList<Object>();
		try{
			for (int i = 0; i < via.length; i++) { //loop a
				TdsCalculation getVIAList = new TdsCalculation();
				getVIAList.setInvVIAId(String.valueOf(via[i][0]));
				getVIAList.setInvVIAName(String.valueOf(via[i][1]));
	
				Object investmentLimit = via[i][2];
				if (investmentLimit == null)
					getVIAList.setInvVIALimit("0");
				else
					getVIAList.setInvVIALimit(String.valueOf(via[i][2]));
	
				Object investmentAmt = via[i][2];
				if (investmentAmt == null)
					getVIAList.setInvVIAAmt("0");
				else
					getVIAList.setInvVIAAmt(String.valueOf(Math.round(Double.parseDouble(String
							.valueOf(via[i][3])))));
				//logger.info("via[i][3]    :"+via[i][3]);
				/*for (int j = 0; j < debitInvAdd.length; j++) {
					//logger.info("via[i][0]    :"+via[i][0]);
					//logger.info("debitInvAdd[j][2    :"+debitInvAdd[j][2]);
					if (String.valueOf(via[i][0]).trim().equals(
							String.valueOf(debitInvAdd[j][2]).trim())) {
					//	logger.info("debitInvAdd[j][0]   :"+debitInvAdd[j][0]);
						amt = Double.parseDouble(String.valueOf(debitInvAdd[j][0]));
						abc = Double.parseDouble(String.valueOf(getVIAList
								.getInvVIAAmt()));
						totAmt = amt + abc;
						getVIAList.setInvVIAAmt(String.valueOf(Math.round(Double.parseDouble(String
								.valueOf(totAmt)))));
					}//end of nested if
				}//end of for loop
	*/
				viewVIAList.add(getVIAList);
			}////end of loop "a"
		} catch (Exception e) {
			logger.error("exception in rebate loop",e);
		} //end of catch
		
		tdsCal.setTotalVIAInvAmt(String.valueOf(Math.round(viaAmt)));
		tdsCal.setEmpVIAInvList(viewVIAList);
	}//end of VIA method

	/**
	 * this method is called to view the calculator details
	 * @param tdsCal
	 * @param request
	 * @return taxValidation
	 */
	public Object[][] viewCalculator(TdsCalculation tdsCal,
			HttpServletRequest request) {
		String empId = "";
		String frmYear = "";
		String toYear = "";
		String genderType = "";
		String empAge1 = "";
		String token = "";
		String name = "";
		String center = "";
		String rank = "";
		String centerId = "";
		String joinMonth = "", joinMonCheck = "", empJoinYearCheck = "";
		double rentAmt = 0, hraFinalAmt = 0, ptaxMonTotAmt = 0,totalIncome = 0,totalTax=0;
		// this if Conditions are check if the viewCalculator method is called
		// from the Show Details in the Tds Calculator form
		if (tdsCal.getFromYear().equals("")
				|| tdsCal.getFromYear().equals("null")) {
			frmYear = request.getParameter("frYr");
			tdsCal.setFromYear(frmYear);
		}//end of if
		if (tdsCal.getToYear().equals("") || tdsCal.getToYear().equals("null")) {
			toYear = request.getParameter("toYr");
			tdsCal.setToYear(toYear);
		}//end of if
		if (tdsCal.getEmpID().equals("") || tdsCal.getEmpID().equals("null")) {
			empId = request.getParameter("bbb");
			tdsCal.setEmpID(empId);
			tdsCal.setIsShowButton("true");
			frmYear = request.getParameter("frYr");
			tdsCal.setFromYear(frmYear);
			toYear = request.getParameter("toYr");
			tdsCal.setToYear(toYear);
		}//end of if
		if (tdsCal.getEmpGender().equals("")
				|| tdsCal.getEmpGender().equals("null")) {
			genderType = request.getParameter("gender");
			tdsCal.setEmpGender(genderType);
		}//end of if
		if (tdsCal.getEmpAge().equals("") || tdsCal.getEmpAge().equals("null")) {
			empAge1 = request.getParameter("age");
			tdsCal.setEmpAge(empAge1);
		}//end of if
		if (tdsCal.getEmpToken().equals("")
				|| tdsCal.getEmpToken().equals("null")) {
			token = request.getParameter("token");
			tdsCal.setEmpToken(token);
		}//end of if
		if (tdsCal.getEmpName().equals("")
				|| tdsCal.getEmpName().equals("null")) {
			name = request.getParameter("name");
			tdsCal.setEmpName(name);
		}//end of if
		if (tdsCal.getEmpCenter().equals("")
				|| tdsCal.getEmpCenter().equals("null")) {
			center = request.getParameter("center");
			tdsCal.setEmpCenter(center);
		}//end of if
		if (tdsCal.getEmpRank().equals("")
				|| tdsCal.getEmpRank().equals("null")) {
			rank = request.getParameter("rank");
			tdsCal.setEmpRank(rank);
		}//end of if
		if (tdsCal.getCenterId().equals("")
				|| tdsCal.getCenterId().equals("null")) {
			centerId = request.getParameter("centerId");
			tdsCal.setCenterId(centerId);
		}//end of if
		if (tdsCal.getEmpJoinedDate() == null) {
			joinMonth = request.getParameter("joinedMonth");
			tdsCal.setEmpJoinedDate(joinMonth);
		}//end of if

		frmYear = tdsCal.getFromYear();
		toYear = tdsCal.getToYear();
		genderType = tdsCal.getEmpGender();
		empId = tdsCal.getEmpID();
		Object[][] taxSlab = null;
		Object remainMonData[][] = null;
		Object[][]currentMonth = null;
		empAge1 = tdsCal.getEmpAge();
		
		String query1 = "SELECT TO_CHAR(SYSDATE,'MM') FROM DUAL";
		currentMonth = getSqlModel().getSingleResult(query1);
		
		logger.info("frmYear  :"+frmYear);
		logger.info("tdsCal.getCurrentYear()  :"+tdsCal.getCurrentYear());
		Object []empPreParamObj=new Object[3];
		empPreParamObj[0]=empId;
		empPreParamObj[1]=frmYear;
		empPreParamObj[2]=toYear;
		// query to get the previous employer income details
		String empPreQuery="SELECT NVL(PRE_TAXABLE_INCOME,0),NVL(PRE_TAX_PAID_AMT,0),NVL(PRE_PF_AMT,0),NVL(PRE_PT_AMT,0) FROM  HRMS_PRE_EMPLOYER_INCOME"
					+" WHERE PRE_EMP_ID =? AND PRE_FROM_YEAR=? AND PRE_TO_YEAR=?";
		Object [][]empPreDataObj=getSqlModel().getSingleResult(empPreQuery,empPreParamObj);
		double preNetTaxableIncomeAmt=0;
		double preTaxPaidAmt=0;
		double prePTaxAmt=0;
		double prePFAmt=0;
		if(empPreDataObj!=null && empPreDataObj.length>0){
			preNetTaxableIncomeAmt= Double.parseDouble(String.valueOf(empPreDataObj[0][0]));
			preTaxPaidAmt=Double.parseDouble(String.valueOf(empPreDataObj[0][1]));
			prePTaxAmt=Double.parseDouble(String.valueOf(empPreDataObj[0][3]));
			prePFAmt=Double.parseDouble(String.valueOf(empPreDataObj[0][2]));
			tdsCal.setPrevEmployerFlag("true"); // flag to display the previous employer details block
		}else{
			tdsCal.setPrevEmployerFlag("false"); // flag to hide the previous employer details block
		}
		tdsCal.setPreNetTaxableIncomeAmt(String.valueOf(Math.round(preNetTaxableIncomeAmt)));
		tdsCal.setPreTaxPaidAmt(String.valueOf(Math.round(preTaxPaidAmt)));
		tdsCal.setPrePTaxAmt(String.valueOf(Math.round(prePTaxAmt)));
		tdsCal.setPrePFAmt(String.valueOf(Math.round(prePFAmt)));
		try {
			String query = "SELECT TDS_REMAIN_MONTH FROM HRMS_TDS "
					+ " WHERE TDS_EMP_ID=" + tdsCal.getEmpID()
					+ " AND TDS_FROM_YEAR=" + frmYear + " AND TDS_TO_YEAR="
					+ toYear + "";
			remainMonData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in remainMonData",e);
		} //end of catch
		if (remainMonData == null) {
			tdsCal.setRemainMonths("1");
		}//end of if
		else if (remainMonData.length == 0) {
			tdsCal.setRemainMonths("1");
		}//end of else if
		else{
			
			if(String.valueOf(remainMonData[0][0]).equals("0")){
				logger.info("if remain month is 0========>");
				tdsCal.setRemainMonths("1");
			} //end of if
			
			int forPreviousYear = 0;
			/**
			 * This condition is check for previous year...if the current month is between Jan to March
			 * then the Current Year is minus by 1....
			 */
			if(String.valueOf(currentMonth[0][0]).equals(01) || String.valueOf(currentMonth[0][0]).equals(02)
					|| String.valueOf(currentMonth[0][0]).equals(03)){
				forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear()) - 1;
			} //end of if
			else{
				forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear());
			} //end of else
			
			logger.info("forPreviousYearin viewCalculator   "+forPreviousYear);
			
			if(Integer.parseInt(frmYear) < forPreviousYear){
				tdsCal.setRemainMonths("1");
			} //end of if
			else if(Integer.parseInt(frmYear) > Integer.parseInt(tdsCal.getCurrentYear())
					&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
				tdsCal.setRemainMonths("12");
			} //end of else if
			else{
				if(String.valueOf(remainMonData[0][0]).equals("0")){
					tdsCal.setRemainMonths("1");
				} //end of if
				else{
					tdsCal.setRemainMonths(String.valueOf(remainMonData[0][0]));
				} //end of else
				
			} //end of else
		} //end of else
		
		int empAge = 0;
		if (empAge1 == null) {
			empAge = 0;
		}//end of if
		else if (empAge1.equals("null") || empAge1.equals("")) {
			empAge = 0;
		}//end of else if 
		else {
			empAge = Integer.parseInt(String.valueOf(tdsCal.getEmpAge()));
			logger.info("2");
		}//end of else
		double totInvestmentAmt = 0.0;

		// this method is to retrieve all the data from the HRMS_TAX_PARAMETER
		Object[][] taxParameters = null;
		try {
			taxParameters = getTaxParameters(tdsCal);
		} catch (Exception e) {
			logger.error("exception in taxParameters object",e);
		} //end of catch
		
		ArrayList<Object> viewGrossList = new ArrayList<Object>();
		ArrayList<Object> viewPreqList = new ArrayList<Object>();

		//Object debitInvAdd[][] = getDebInvAddChap(tdsCal.getEmpID());

		// this Object totalInvest contains all the investments of that
		// particular Employee for that financial Year
		// FROM HRMS_TAX_INVESTMENT table.
		Object totalInvest[][] = null;
		try {
			totalInvest = getTotalInvestment1(tdsCal.getEmpID(), frmYear,toYear);
		} catch (Exception e) {
			logger.error("exception in totalInvest object",e);
		} //end of catch
		Object taxValidation[][] = new Object[1][1];

		if (taxParameters == null) {
			taxParameters = new Object[1][1];
		}//end of if

		if (!(taxParameters.length > 0)) {
			taxValidation[0][0] = "1";
			return taxValidation;
		}//end of if
		// to get slabs from the HRMS_TAX_SLAB for Seniors
		if (empAge >= Integer.parseInt(String.valueOf(taxParameters[0][3]))) {
			taxSlab = getTaxSlab(frmYear, toYear, "S");
			if (!(taxSlab.length > 0)) {
				taxValidation[0][0] = "2";
				return taxValidation;
			}//end of nested if
			try {
				taxSlab(taxSlab, tdsCal);
			} catch (Exception e) {
				logger.error("exception in taxsalb object",e);
			} //end of catch
		} //end of if
		else {
			// to get slabs from the HRMS_TAX_SLAB for men
			if (genderType.equals("M")) {
				taxSlab = getTaxSlab(frmYear, toYear, "M");
				if (!(taxSlab.length > 0)) {
					taxValidation[0][0] = "2";
					return taxValidation;
				}//end of nested if
				try {
					taxSlab(taxSlab, tdsCal);
				} catch (Exception e) {
					logger.error("exception in taxsalb object",e);
				} //end of catch
			}//end of if
			// to get slabs from the HRMS_TAX_SLAB for women
			else if (genderType.equals("F") || genderType.equals("O")) {
				taxSlab = getTaxSlab(frmYear, toYear, "F");
				if (!(taxSlab.length > 0)) {
					taxValidation[0][0] = "2";
					return taxValidation;
				}//end of nested if
				try {
					taxSlab(taxSlab, tdsCal);
				} catch (Exception e) {
					logger.error("exception in taxsalb object",e);
				} //end of catch
			}//end of else if
		}//end of else

		tdsCal.setTaxParaSur(String.valueOf(taxParameters[0][1]));
		tdsCal.setTaxParaEdu(String.valueOf(taxParameters[0][2]));
		tdsCal.setTaxParaSurLimit(String.valueOf(taxParameters[0][4]));
		// get the taxable salary details
		Object[][] income = null;
		try {
			String creditQuery = "SELECT TDS_CREDIT_HEAD,TDS_CREDIT_AMOUNT,HRMS_CREDIT_HEAD.CREDIT_NAME,TDS_YEAR_FROM, "
					+ " TDS_YEAR_TO FROM HRMS_TDS_SALARY "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_TDS_SALARY.TDS_CREDIT_HEAD ) "
					+ " WHERE TDS_EMP_ID="
					+ tdsCal.getEmpID()
					+ " AND TDS_YEAR_FROM="
					+ frmYear
					+ " AND TDS_YEAR_TO="
					+ toYear + " ORDER BY TDS_CREDIT_HEAD";
			income = getSqlModel().getSingleResult(creditQuery);
		} catch (Exception e) {
			logger.error("exception in income object",e);
		} //end of catch
		
		try {
			if (income == null) {

			} //end of if
			else if (income.length == 0) {

			} //end of else if
			else{
				for (int i = 0; i < income.length; i++) {

					TdsCalculation bean = new TdsCalculation();

					bean.setCreditId(String.valueOf(income[i][0]));
					bean.setCreditName(String.valueOf(income[i][2]));
					bean.setCreditAmt(String.valueOf(Math.round(Double
							.parseDouble(String.valueOf(income[i][1])))));
					viewGrossList.add(bean);
				}//end of for loop
			} //end of else
			
		} catch (Exception e) {
			logger.error("exception in income loop",e);
		} //end of catch
		tdsCal.setEmpGrossSalList(viewGrossList);
		
		Object[][] prequisite = null;
		// get the taxable perqs details
		try {
			String preqQuery = "SELECT TDS_PERQ_HEAD,TDS_PERQ_AMOUNT,HRMS_PERQUISITE_HEAD.PERQ_NAME,TDS_YEAR_FROM, "
					+ " TDS_YEAR_TO FROM HRMS_TDS_PERQ "
					+ " LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_TDS_PERQ.TDS_PERQ_HEAD) "
					+ " WHERE TDS_EMP_ID="
					+ tdsCal.getEmpID()
					+ " "
					+ " AND TDS_YEAR_FROM="
					+ frmYear
					+ " "
					+ " AND TDS_YEAR_TO="
					+ toYear
					+ " ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE 	";
			prequisite = getSqlModel().getSingleResult(preqQuery);
		} catch (Exception e) {
			logger.error("exception in preq query  "+e);
		} //end of catch
		
		double totPerqAmt = 0.0;
		
		try {
			if (prequisite == null) {

			} //end of if
			else if (prequisite.length == 0) {

			} //end of else if
			else{
				for (int i = 0; i < prequisite.length; i++) {

					TdsCalculation bean = new TdsCalculation();

					bean.setPerqId(String.valueOf(prequisite[i][0]));
					bean.setPerqName(String.valueOf(prequisite[i][2]));
					bean.setPerqAmt(String.valueOf(Math.round(Double
							.parseDouble(String.valueOf(prequisite[i][1])))));
					viewPreqList.add(bean);
					totPerqAmt += Double.parseDouble(String
							.valueOf(prequisite[i][1]));
				}//end of for loop
			} //end of else
		} catch (Exception e) {
			logger.error("exception in prequisite loop",e);
		} //end of catch
		
		tdsCal.setEmpPerqList(viewPreqList);
		tdsCal.setTotalPerqAmt(String.valueOf(Math.round(totPerqAmt)));
		
		try {
			// ///to calculate total sum of income
			totalIncome = getSumIncome(income, tdsCal);
		} catch (Exception e) {
			logger.error("exception in totalIncome(getSumIncome)",e);
		} //end of catch
		
		tdsCal.setTotalCreditAmt(String.valueOf(Math.round(totalIncome)));
		// //methods to calculate and get list of all the Investment
		// Chapters....
		Object exempt[][] = null;
		try {
			exempt = getInvestments(totalInvest, "EXEMPT","N");
		} catch (Exception e) {
			logger.error("exception in exempt object",e);
		}
																
		double exemptAmt = getInvAmt(exempt);// total exempt inv amt
		//double debitexempt = getDebitInvestments(debitInvAdd, "EXEMPT");
		// HRA amount is added in the Exempt Chapter
		exempt(exempt, tdsCal, exemptAmt,
				hraFinalAmt, String.valueOf(taxParameters[0][12]));
	
		Object other[][] = null;
		try {
			other = getInvestments(totalInvest, "OTHER","N");
		} catch (Exception e) {
			logger.error("exception in other object",e);
		} //end of catch
		double otherAmt = getInvAmt(other);
		//double debitOther = getDebitInvestments(debitInvAdd, "OTHER");
		other(other, tdsCal, otherAmt);
		
		Object via[][] = null;
		try {
			via = getInvestments(totalInvest, "VI-A","N");
			//via = Utility.joinArrays(via, getInvestments(totalInvest, "VI","N"), 0, 0);
		} catch (Exception e) {
			logger.error("exception in via object",e);
		} //end of catch
		
		try {
			if (via != null && via.length > 0) {
				via = Utility.joinArrays(via, getInvestments(totalInvest, "VI","N"),1, 0);
			} else {
				via = getInvestments(totalInvest, "VI","N");
			}
		} catch (Exception e) {
			logger.error("exception in getInvestments jopin array"+e);
		}
		double viaAmt = getInvAmt(via);
		//double debitVia = getDebitInvestments(debitInvAdd, "VI-A");
		via(via, tdsCal, viaAmt);
		
		Object rebate[][] = null;
		try {
			rebate = getInvestments(totalInvest, "VI","Y");
		} catch (Exception e) {
			logger.error("exception in rebate object",e);
		} //end of catch
		double rebateAmt = getInvAmt(rebate);
		//double debitRebate = getDebitInvestments(debitInvAdd, "VIII");
		rebate(rebate, tdsCal, rebateAmt, String.valueOf(taxParameters[0][13]),prePFAmt); 

		if (!(taxParameters[0][13] == null || taxParameters[0][13].equals("")
				|| taxParameters[0][13].equals("null") || taxParameters[0][13]
				.equals(null))) {
			if (Double.parseDouble(String.valueOf(taxParameters[0][13])) > (rebateAmt)) {
				totInvestmentAmt = exemptAmt + viaAmt + rebateAmt;
			}//end of nested if
			else {
				totInvestmentAmt = exemptAmt+ viaAmt+ Double.parseDouble(String
								.valueOf(taxParameters[0][13]));
			}//end of else
		}//end of if
		else {
			totInvestmentAmt = exemptAmt  + viaAmt + rebateAmt;
					
		}//end of else

		tdsCal.setTotalNetInvAmt(String.valueOf(Math.round(totInvestmentAmt)));

		double ptax = 0;
		Object[][] ptaxAmt = null;
		// set the other details like PTAX,taxPaid,leaveEncashment
		try {
			String ptaxQuery = "SELECT TDS_PROF_TAX,TDS_TAX_PAID,NVL(TDS_LEAVE_ENCASH_AMT,0),NVL(TDS_ENCASH_AMT_TO_DEDUCT,0) FROM HRMS_TDS "
					+ " WHERE TDS_EMP_ID="
					+ tdsCal.getEmpID()
					+ " AND TDS_FROM_YEAR="
					+ frmYear
					+ " AND TDS_TO_YEAR="
					+ toYear + "";
			ptaxAmt = getSqlModel().getSingleResult(ptaxQuery);
		} catch (Exception e) {
			logger.error("exception in ptaxAmt object",e);
		} //end of catch
		if (ptaxAmt == null) {
			tdsCal.setTaxPaid(String.valueOf(Math.round(preTaxPaidAmt)));
		}//end of if
		else if (ptaxAmt.length == 0) {
			tdsCal.setTaxPaid(String.valueOf(preTaxPaidAmt));
		}//end of else if
		else {
			if (String.valueOf(ptaxAmt[0][1]).trim().equals("")
					|| String.valueOf(ptaxAmt[0][1]).trim().equals("null")) {
				tdsCal.setTaxPaid(String.valueOf(preTaxPaidAmt));
			}//end of nested if
			else {
				tdsCal.setTaxPaid(String.valueOf(Math.round(Double.parseDouble(String.valueOf(ptaxAmt[0][1]))+preTaxPaidAmt)));
			}//end of else

			if (String.valueOf(ptaxAmt[0][0]).trim().equals("")
					|| String.valueOf(ptaxAmt[0][0]).trim().equals("null")) {
				ptax = 0;
			}//end of nested if
			else {
				ptax = Double.parseDouble(String.valueOf(ptaxAmt[0][0]));
			}//end of nested else
		}//end of else
		double leaveAddedIncome=0.0;
		if(ptaxAmt != null && ptaxAmt.length > 0)
		{
			tdsCal.setLeaveEncashAmt(String.valueOf(ptaxAmt[0][2]));
			leaveAddedIncome = Math.round(Double.parseDouble(String.valueOf(ptaxAmt[0][3])));
			tdsCal.setLeaveEncashAddedIncome(String.valueOf(Math.round(leaveAddedIncome)));
		}
		else
		{
			tdsCal.setLeaveEncashAmt("0");
			tdsCal.setLeaveEncashAddedIncome("0");
		}
		tdsCal.setPTaxAmt(String.valueOf(Math.round(ptax)));
		double InvAmt = totInvestmentAmt + ptax;
		tdsCal.setTotalNetInvAmt(String.valueOf(Math.round(InvAmt)));

		tdsCal.setTotalGrossAmt(String.valueOf(Math.round(totalIncome + totPerqAmt + otherAmt + leaveAddedIncome)));
		double finalIncome =0;
		finalIncome = Math.round(totalIncome + totPerqAmt + otherAmt + leaveAddedIncome);
		logger.info("finalIncome   :"+finalIncome );
		logger.info("InvAmt   :"+InvAmt );
		double taxableIncome = finalIncome - InvAmt+(preNetTaxableIncomeAmt-(prePFAmt+prePTaxAmt));
		
		if (taxableIncome > 0) {
			logger.info("taxableIncome   :"+taxableIncome);
			/**
			 * this method is for next round of 10 for e.g- 61 it will be 70
			 */
			double z = taxableIncome;
			double mod=taxableIncome%10;
			if(mod >0)
			{
				z=(z-mod)+10;
			}
			
			if(z<0)
			{
				logger.info("in ifc z   :");
				tdsCal.setNetTaxableIncome("0");
			}
			else
				logger.info("z in else   :"+z);
			tdsCal.setNetTaxableIncome(String.valueOf(Math.round(z)));
			taxableIncome = z;
			//tdsCal.setNetTaxableIncome(String.valueOf(taxableIncome));
		}////end of nested if 
		else {
			taxableIncome = 0;
			tdsCal.setNetTaxableIncome(String.valueOf(0));
		}//end of nested else
		
		/*
		 * 	Code Block to implement the section 87A tax rebate condition
		 *  if Taxable income is less than 5,00,000, income tax rebate of 2000 is given.
		 */
		try {
			
			totalTax = getTaxAmount(taxableIncome, taxSlab);
			
			tdsCal.setTaxExemptionIncomeLimit(String.valueOf(taxParameters[0][18]));
			tdsCal.setMaxTaxExemption(String.valueOf(taxParameters[0][17]));
			
			tdsCal.setTaxExemptionSection87("0");
			 
			if (String.valueOf(taxParameters[0][16]).equals("Y")) {   // if tax exemption as per seciton 87 is enabled
				if(taxableIncome <= Double.parseDouble(String.valueOf(taxParameters[0][18]))) {	// if income is less than 5,00,000						
					if(totalTax <= Double.parseDouble(String.valueOf(taxParameters[0][17]))) { // if tax is less than 2000
						tdsCal.setTaxExemptionSection87(""+totalTax);
						totalTax=0;						
					} else {  // if tax is grater than 2000
						tdsCal.setTaxExemptionSection87(String.valueOf(taxParameters[0][17]));
						totalTax = totalTax-Double.parseDouble(String.valueOf(taxParameters[0][17]));
					}				
				}
			}
			
		} catch (Exception e) {
				logger.error("exception in totalTax in getTaxAmount",e);
		} //end of catch
		
		
		tdsCal.setTaxOnIncome(String.valueOf(Math.round(totalTax)));
		// calculate the tax amount according to the applicable slab to taxable income
		setSlab(taxableIncome, taxSlab, tdsCal);

		double surcharge = getsurcharge(taxableIncome, (totalTax),
				taxParameters);
		tdsCal.setSurCharge(String.valueOf(Math.round(surcharge)));
		
		double educess = geteducess(totalTax + surcharge, taxParameters);
		tdsCal.setEduCess(String.valueOf(Math.round(educess)));

		double netTax = 0.0;
		netTax = totalTax + educess + surcharge;
		tdsCal.setNetTax(String.valueOf(Math.round(netTax)));

		// this tdsCal.getTaxPaid()==>> is set in the getIncome method
		double taxPerMonth = 0.0;
		logger.info("tdsCal.getRemainMonths()   :"+tdsCal.getRemainMonths());
		// tax per month is caclualted
		try {
			taxPerMonth = (netTax - Double.parseDouble(tdsCal.getTaxPaid()))
					/ Double.parseDouble(tdsCal.getRemainMonths());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(taxPerMonth < 0)
			taxPerMonth = 0;
		tdsCal.setTaxPerMonth(String.valueOf(Math.round(taxPerMonth)));
		
		
		int forPreviousYear = 0;
		/**
		 * This condition is check for previous year...if the current month is between Jan to March
		 * then the Current Year is minus by 1....
		 */
		if(String.valueOf(currentMonth[0][0]).equals(01) || String.valueOf(currentMonth[0][0]).equals(02)
				|| String.valueOf(currentMonth[0][0]).equals(03)){
			forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear()) - 1;
		} //end of if
		else{
			forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear());
		} //end of else
		
		logger.info("forPreviousYearin viewCalculator   "+forPreviousYear);
		
		if(Integer.parseInt(frmYear) < forPreviousYear){
			tdsCal.setRemainMonths("No Remain Months");
		} //end of if

		return taxValidation;
	} // end of viewCalculator method

	/**
	 * this method is used to calculate the surcharge value
	 * @param taxableIncome
	 * @param totalTax
	 * @param taxParameters
	 * @return surCharge
	 */
	private double getsurcharge(double taxableIncome, double totalTax,
			Object[][] taxParameters) {
		// taxableIncome===>> contains value after totalGross - totalInvestment
		// totalTax==>>contains value after the TaxSlabs are applied..
		double surCharge = 0.0;
		try {
			// if taxableIncome is greater than TDS_SURCHARGE_LIMIT_AMT
			if (!(taxParameters[0][4] == null)) {
				if (taxableIncome > Double.parseDouble(String
						.valueOf(taxParameters[0][4]))) {
					if (!(taxParameters[0][1] == null)) {
						surCharge = Math.round((totalTax * Double
								.parseDouble(String
										.valueOf(taxParameters[0][1]))) / 100);
					}//end of nested if
					else {
						surCharge = 0.0;
					}//end of nested else
				}//end of nested if
			}//end of if
			else {
				surCharge = 0.0;
			}//end of else
		} catch (Exception e) {
			logger.error("error in getsurcharge "+e);
		} //end of catch
		return surCharge;
	}//end of getsurcharge

	/**
	 * this method is used to calculate the education cess value.
	 * @param totalTax
	 * @param taxParameters
	 * @return eduCessValue
	 */
	private double geteducess(double totalTax, Object[][] taxParameters) {
		double eduCessValue = 0.0;
		logger.info("in educess   :");
		try {
			if (totalTax > 0) {
				// taxParameters[0][2]==>>TDS_EDU_CESS
				logger.info("taxParameters[0][1]  :" + taxParameters[0][1]);
				if (!(taxParameters[0][1] == null)) {
					logger.info("taxParameters[0][2]  :" + taxParameters[0][2]);
					logger.info("totalTax  :" + totalTax);
					eduCessValue = Math
							.round((totalTax * Double.parseDouble(String
									.valueOf(taxParameters[0][2]))) / 100);
				}//end of nested if
				else {
					eduCessValue = 0.0;
				}//end of nested else
			}//end of if
			else {
				eduCessValue = 0.0;
			}//end of else
		} catch (Exception e) {
			logger.error("error in geteducess "+e);
		}
		return eduCessValue;
	}//end of geteducess method

	/**
	 * this method is used to set the values of slab in the list
	 * @param total
	 * @param taxData
	 * @param tdsCal
	 * @return
	 */
	private String[][] setSlab(double total, Object[][] taxData,
			TdsCalculation tdsCal) {
		double totalTaxOnIncome = 0.0;
		double totalTaxOnIncomeElse = 0.0;
		double taxOnIncomeElse = 0.0;
		double diff = 0;
		double taxAmt = 0;
		String xyz = String.valueOf(total);
		double remain = total;
		String[][] resultSalb = null;
		ArrayList<Object> viewList = new ArrayList<Object>();
		try {
			if(taxData == null){
				
			} //end of if
			else if(taxData.length == 0){
				
			} //end of else if
			else{
				resultSalb = new String[taxData.length][2];

				// total==>>totalGrossAmt - totalInvestmentAmt
				for (int i = 0; i < taxData.length; i++) {
					TdsCalculator getList = new TdsCalculator();
					// it checks if total is greater than TAX_TO_AMT FROM
					// HRMS_TAX_SLAB
					if (total >= Double.parseDouble(String.valueOf(taxData[i][1]))) {
						getList.setSlabFrmAmt(String.valueOf(taxData[i][0]));
						getList.setSlabToAmt(String.valueOf(taxData[i][1]));
						getList.setSlabTaxRate(String.valueOf(taxData[i][2]));

						// THIS conditions are check to make the value round for e:g
						// if tax_from_amt is 10001 so if we take 10%
						// of dat so it comes some 999 so to make it round we are
						// adding it 1
						if (i == 0) {
							diff = Math.abs(((Double.parseDouble(String
									.valueOf(taxData[i][1])) - Integer
									.parseInt(String.valueOf(taxData[i][0])))));
							// in diff==>> TAX_TO_AMT - TAX_FROM_AMT
						} //end of nested if
						else {
							diff = Math.abs(((Double.parseDouble(String
									.valueOf(taxData[i][1])) - Integer
									.parseInt(String.valueOf(taxData[i][0]))) + 1));
						} //end of nested else
						getList.setSlabAmt(String.valueOf(Math.round(diff)));
						resultSalb[i][0] = String.valueOf(diff);
						remain = remain - diff;

						// remain===>>TOTAL
						// taxData[i][2]==> TAX_PERCENT
						taxAmt = ((diff * (Double.parseDouble(String
								.valueOf(taxData[i][2])))) / 100);
						diff = 0;
						getList.setSlabTax(String.valueOf(Math.round(taxAmt)));
						totalTaxOnIncome += Double.parseDouble(String.valueOf(Math
								.round(taxAmt)));
						resultSalb[i][1] = String.valueOf(Math.round(taxAmt));
						viewList.add(getList);
					} //end of if
					// this else is for the last Slab row.
					else {
						// /in this the TAX_PERCENTAGE is applied to all the
						// remaining amount
						getList.setSlabFrmAmt(String.valueOf(taxData[i][0]));
						getList.setSlabToAmt(String.valueOf(taxData[i][1]));
						getList.setSlabTaxRate(String.valueOf(taxData[i][2]));
						getList.setSlabAmt(String.valueOf(Math.round(remain)));
						taxAmt = (remain * (Double.parseDouble(String
								.valueOf(taxData[i][2])))) / 100;
						getList.setSlabTax(String.valueOf(Math.round(taxAmt)));
						resultSalb[i][0] = String.valueOf(remain);
						totalTaxOnIncomeElse += Double.parseDouble(String
								.valueOf(Math.round(taxAmt)));
						resultSalb[i][1] = String.valueOf(Math.round(taxAmt));
						viewList.add(getList);
						remain = 0;

					} //end of else
				} //end of for loop
			} //end of else
			
			taxOnIncomeElse = totalTaxOnIncome + totalTaxOnIncomeElse;
			tdsCal.setTdslabList(viewList);
		} catch (NumberFormatException e) {
			logger.info("Exception in setSlab method");
		} //end of catch
		return resultSalb;
	}// end of setSlab method

	/**
	 * this is used to calculate the total annual gross amount
	 * @param income
	 * @param tdsCal
	 * @return totalGrossAmt
	 */
	private double getSumIncome(Object[][] income, TdsCalculation tdsCal) {
		double totalGrossAmt = 0.0;
		try {
			// this loop is run to calculate total Gross Salary by adding the values
			// in Object income[i][2]
			for (int i = 0; i < income.length; i++) {
				totalGrossAmt += Double.parseDouble(String
						.valueOf(income[i][1]));
			}//end of for loop
		} catch (Exception e) {
			logger.error("error in getSumIncome "+e);
		} //end of catch
		return totalGrossAmt;
	}//end of getSumIncome method

	/**
	 * this method is used to get the ledger code for month April to december
	 * @param frmMonth---4
	 * @param toMonth---12
	 * @param year
	 * @param tdsCal
	 * @return processData--returns ledger code
	 */
	public Object[][] retProcessData(int frmMonth, int toMonth, String year,
			TdsCalculation tdsCal) {

		Object[][] processData=null;
		try {
			String process = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
					+ "WHERE LEDGER_YEAR="
					+ year
					+ " AND LEDGER_STATUS IN('SAL_FINAL','SAL_START') AND LEDGER_MONTH>="
					+ frmMonth + " " + "AND LEDGER_MONTH<=" + toMonth
					+ " AND LEDGER_DIVISION=" + tdsCal.getDivisionCode();
			/*if (tdsCal.getBranchFlag().equals("true")) {
				process = process + " AND LEDGER_BRN=" + tdsCal.getBranchCode();
			}//end of if
			if (tdsCal.getEmptypeFlag().equals("true")) {
				process = process + " AND LEDGER_EMPTYPE="
						+ tdsCal.getTypeCode();
			}//end of if
			if (tdsCal.getPaybillFlag().equals("true")) {
				process = process + " AND LEDGER_PAYBILL="
						+ tdsCal.getPayBillNo();
			}//end of if
			if (tdsCal.getDepartmentFlag().equals("true")) {
				process = process + " AND LEDGER_DEPT=" + tdsCal.getDeptCode();
			}*/
			processData = getSqlModel().getSingleResult(process);
		} catch (Exception e) {
			logger.error("error in retProcessData "+e);
		} //end of catch
		return processData;
	}// end of retProcessData method

	/*
	 * private Object[][] getLoctaion(String branchCode) { String query =
	 * "SELECT LOCATION_PARENT_CODE,LOCATION_CODE FROM HRMS_CENTER " +" LEFT
	 * JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =
	 * HRMS_CENTER.CENTER_LOCATION) " +" WHERE CENTER_ID ="+branchCode+"";
	 * Object[][] location = getSqlModel().getSingleResult(query); return
	 * location; }
	 */

	/**
	 * to get the ptax data
	 */
	private Object[][] getPtaxData() {
		Object[][] profTaxData=null;
		try {
			String query = "SELECT HRMS_PTAX_HDR.PTAX_CODE,PTAX_LOCATION,PTAX_FROMAMT,PTAX_UPTOAMT, "
					+ " PTAX_VARMTH,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_DEBIT_CODE FROM HRMS_PTAX_HDR  "
					+ " INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE)";
			profTaxData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getPtaxData "+e);
		} //end of catch
		return profTaxData;
	}//end of getPtaxData method

	/**
	 * to get the professional tax amount by passing the slabs and gross salary
	 * @param location
	 * @param taxData
	 * @param grossSal
	 * @param tdsCal 
	 * @param joinedMonth 
	 * @param empJoinMth 
	 * @return Integer.parseInt(String.valueOf(taxData[i][5]))--- ptax amount
	 */
	private int getEmpPtax(String location, Object[][] taxData, double grossSal, TdsCalculation tdsCal, String joinedMonth, String empJoinMth) {
		try {
			// location==>>it contains location parent code.
			for (int i = 0; i < taxData.length; i++) {
				// in this if condition it checks whether the location parent code
				// is equal to PTAX_LOCATION
				if (String.valueOf(location).trim().equals(
						String.valueOf(taxData[i][1]).trim())) {
					// logger.info("taxData[i][1]=========================="+taxData[i][1]);
					// in this it checks whether the grossSal is in between
					// PTAX_FROMAMT to PTAX_UPTOAMT
					// if it is in between than the PTAX_FIXEDAMT value is taken
					// from the HRMS_PTAX_HDR and DTL table
					logger.info("grossSal=========================="+ grossSal);
					//logger.info("taxData[i][4]=========================="+taxData[i][4]);

					if (!(String.valueOf(taxData[i][4]).trim().equals("") || String
							.valueOf(taxData[i][4]).trim().equals("null")
							|| String.valueOf(taxData[i][4]).trim().equals("0"))){// if 'a'
						if (empJoinMth.trim().equals("1")) {
							empJoinMth = "13";
						} //end of if 
						else if (empJoinMth.trim().equals("2")) {
							empJoinMth = "14";
						} //end of else if 
						else if (empJoinMth.trim().equals("3")) {
							empJoinMth = "15";
						} //end of else if

						if (String.valueOf(taxData[i][4]).equals("1")) {
							taxData[i][4] = "13";
						} //end of if 
						else if (String.valueOf(taxData[i][4]).equals("2")) {
							taxData[i][4] = "14";
						} //end of else if 
						else if (String.valueOf(taxData[i][4]).equals("3")) {
							taxData[i][4] = "15";
						} //end of else if

						if (empJoinMth.trim().equals("")
								|| empJoinMth.trim().equals("null")) {
							if (grossSal >= Double.parseDouble(String
									.valueOf(taxData[i][2]))
									&& grossSal <= Double.parseDouble(String
											.valueOf(taxData[i][3]))) {
								// logger.info("taxData[i][5]=========================="+taxData[i][5]);
								tdsCal.setPtaxVarAmt(String
										.valueOf(taxData[i][6]));
								tdsCal.setPtaxVarMth(String
										.valueOf(taxData[i][4]));
								//logger.info("tdsCal.getPtaxVarMth  in if  :"+tdsCal.getPtaxVarMth());
							}//end of netsed if
						} else if (Double.parseDouble(String
								.valueOf(taxData[i][4])) >= Double
								.parseDouble(empJoinMth)) {
							if (grossSal >= Double.parseDouble(String
									.valueOf(taxData[i][2]))
									&& grossSal <= Double.parseDouble(String
											.valueOf(taxData[i][3]))) {
								// logger.info("taxData[i][5]=========================="+taxData[i][5]);
								tdsCal.setPtaxVarAmt(String
										.valueOf(taxData[i][6]));
								tdsCal.setPtaxVarMth(String
										.valueOf(taxData[i][4]));
								//logger.info("tdsCal.getPtaxVarMth else if    :"+tdsCal.getPtaxVarMth());
							}//end of netsed if
						} //end of else if
						else {
							tdsCal.setPtaxVarAmt("");
						} //end of else

					} //end of if 'a'

					if (grossSal >= Double.parseDouble(String
							.valueOf(taxData[i][2]))
							&& grossSal <= Double.parseDouble(String
									.valueOf(taxData[i][3]))) {
						logger.info("taxData[i][5]=========================="+ taxData[i][5]);
						return Integer.parseInt(String.valueOf(taxData[i][5]));

					}//end of netsed if

				}//end of if
			}//end of for loop
		} catch (Exception e) {
			logger.error("error in getEmpPtax "+e);
		} //end of catch
		return 0;
	}//end of getEmpPtax method

	/**
	 * this method is called to return the pTaxDebitCode.
	 * @param location---contains the location code.
	 * @param taxData---odject which contains the pTaxData
	 * @return
	 */
	private String getEmpPtaxDebCode(String location, Object[][] taxData) {

		try {
			for (int i = 0; i < taxData.length; i++) { //start of pTaxData loop
				//here in this if it checks the location code with the PTAX_LOCATION
				if (String.valueOf(location).trim().equals(
						String.valueOf(taxData[i][1]).trim())) {
					logger.info("----------------------------- ptax debit code"+String.valueOf(taxData[i][7]));
					return String.valueOf(taxData[i][7]);//it returns the PTAX_DEBIT_CODE

				} //end of if
			} //end of for
		} catch (Exception e) {
			logger.error("error in getEmpPtaxDebCode "+e);
		} //end of catch
		return String.valueOf("3");
	} // end of getEmpPtaxDebCode method
	
	
	/**this changes started on 15/11/08 to separate the setConfCredit
	 * 
	 */
	private Object[][] setConfCredit(String empID) {
		Object[][] creditTotalData=null;
		try {
			String queryCredit = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE,NVL(CREDIT_AMT,0),CREDIT_NAME,CREDIT_PERIODICITY "
					+ "FROM HRMS_EMP_CREDIT "
					+ "LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
					+ "WHERE EMP_ID = "
					+ empID
					+ " and CREDIT_TAXABLE_FLAG='Y' AND CREDIT_PAYFLAG = 'Y'";
			creditTotalData = getSqlModel().getSingleResult(queryCredit);
		} catch (Exception e) {
			logger.error("error in setConfCredit "+e);
		} //end of catch
		return creditTotalData;
	} //end of method
	
	/**
	 * this method is used to get the salary data for the months April to December.
	 * @param empID
	 * @param ledgerCode
	 * @param year
	 * @return salData
	 */
	private Object[][] getSalCredit(String empID, String ledgerCode, String year) {
		
		Object[][] salData=null;
		try {
			String query = " select SAL_CREDIT_CODE,sum(nvl(SAL_AMOUNT,0))  from hrms_sal_credits_"
					+ year
					+ " "
					+ "inner join hrms_credit_head on hrms_credit_head.credit_code = hrms_sal_credits_"
					+ year
					+ ".sal_credit_code "
					+ "where SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ") and emp_id="
					+ empID
					+ " and  CREDIT_TAXABLE_FLAG='Y' "
					+ "group by sal_credit_code";
			salData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getSalCredit "+e);
		} //end of catch
		return salData;
	} //end of method
	/**
	 * this method is used to get the salary debit data for the months April to December.
	 * @param empID
	 * @param ledgerCode
	 * @param year
	 * @return salData
	 */
	private Object[][] getSalDebit(String empID, String ledgerCode,String year, String debitTaxPaid) {

		Object[][] debData=null;
		try {
			//logger.info("debitTaxPaid---------------------------  :"+debitTaxPaid);
			String debQuery = "  SELECT SAL_DEBIT_CODE,SUM(NVL(SAL_AMOUNT,0))  FROM HRMS_SAL_DEBITS_"
					+ year
					+ " "
					+ "INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"
					+ year
					+ ".SAL_DEBIT_CODE) "
					+ "WHERE SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ") and emp_id="
					+ empID
					+ " and sal_debit_code In("+debitTaxPaid+") "
					+ "group by SAL_DEBIT_CODE ";
			debData = getSqlModel().getSingleResult(debQuery);
		} catch (Exception e) {
			logger.error("error in getSalDebit "+e);
		} //end of catch
		return debData;
	} //end of method
	
	/**
	 * this method is used to get the salary processed ptax and Income Tax amount
	 * @param salDebitAprToDec
	 * @param debPtaxCode
	 * @param tdsCode
	 * @param tdsCal
	 * @return returnObj
	 */
	private double[] getTaxPaid(Object[][] salDebitAprToDec, String debPtaxCode,
			String tdsCode, TdsCalculation tdsCal) {
		double debMonthAmt=0.0,taxPaid=0.0;
		double returnObj[] = new double[2];	
		try {
			if(salDebitAprToDec.length ==2){//if both codes debPtax code and Income Tax code are present
				//logger.info("in salDebitAprToDec.length ==2     ---:"+salDebitAprToDec[0][0]);
				//logger.info("in salDebitAprToDec.length ==2 debPtaxCode     ---:"+debPtaxCode);
				if (String.valueOf(salDebitAprToDec[0][0]).trim().equals(//here debPtax amount is retrieved
						"" + debPtaxCode + "")) {
					debMonthAmt = Double.parseDouble(String
							.valueOf(salDebitAprToDec[0][1]));
					//logger.info("in salDebitAprToDec.length ==2 debMonthAmt     ---:"+debMonthAmt);
				}//end of nested if
				else {//else it contains the Income Tax amount
					debMonthAmt = Double.parseDouble(String
							.valueOf(salDebitAprToDec[1][1]));
					//logger.info("in else salDebitAprToDec.length ==2 debMonthAmt     ---:"+debMonthAmt);
				}//end of nested else
				if (String.valueOf(salDebitAprToDec[0][0]).trim().equals(
						String.valueOf(tdsCode))){//if it contains tds code
								
					taxPaid =Double.parseDouble(String.valueOf(salDebitAprToDec[0][1]));
				}//end of nested if
				else {//else it contains ptax amount
					taxPaid = Double.parseDouble(String.valueOf(salDebitAprToDec[1][1]));
				}//end of else
			}//end of nested else
			else if(salDebitAprToDec.length ==1){//else if only either of only 1 code is present
				//logger.info("in salDebitAprToDec.length ==1     ---:"+salDebitAprToDec[0][0]);
				//logger.info("in salDebitAprToDec.length ==2 debPtaxCode     ---:"+debPtaxCode);
				if (String.valueOf(salDebitAprToDec[0][0]).trim().equals(
						"" + debPtaxCode + "")) {//is ptax code
					debMonthAmt = Double.parseDouble(String
							.valueOf(salDebitAprToDec[0][1]));
					//logger.info("in salDebitAprToDec.length ==1 debMonthAmt     ---:"+debMonthAmt);
				}//end of nested if
				else if (String.valueOf(salDebitAprToDec[0][0]).trim().equals(
						String.valueOf(tdsCode))) {//else if tds code
					taxPaid = Double.parseDouble(String.valueOf(salDebitAprToDec[0][1]));
				}//end of nested if
			}//end of else if
		} catch (Exception e) {
			logger.error(" in getTaxPaid",e);
		} //end of catch
		returnObj[0] =  debMonthAmt;
		returnObj[1] = taxPaid;
		return returnObj;
	}//end of getTaxPaid method
	
	/**
	 * This method is used to check whether the salary for currentMonth is processed or not
	 * It returns check---which contains the value of remaining months.
	 * @param currentMonth
	 * @param tdsCal
	 * @param year
	 * @return check
	 */
	private int getCurrentSalMonth(int currentMonth, TdsCalculation tdsCal, String year) {
		int check=0;
		Object[][] processData = null;
		try {
			String process = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR="
					+ year
					+ " "
					+ " AND LEDGER_STATUS IN('SAL_FINAL','SAL_START') "
					+ " AND LEDGER_MONTH="
					+ currentMonth
					+ " AND LEDGER_DIVISION=" + tdsCal.getDivisionCode();
			/*if (tdsCal.getBranchFlag().equals("true")) {
				process = process + " AND LEDGER_BRN=" + tdsCal.getBranchCode();
			}//end of if
			if (tdsCal.getEmptypeFlag().equals("true")) {
				process = process + " AND LEDGER_EMPTYPE=" + tdsCal.getTypeCode();
			}//end of if
			if (tdsCal.getPaybillFlag().equals("true")) {
				process = process + " AND LEDGER_PAYBILL=" + tdsCal.getPayBillNo();
			}//end of if
			if (tdsCal.getDepartmentFlag().equals("true")) {
				process = process + " AND LEDGER_DEPT=" + tdsCal.getDeptCode();
			}*/
			/**
			 * this processData contains the ledgerCode of the salary processed
			 */
			processData = getSqlModel().getSingleResult(process);
		} catch (Exception e) {
			logger.error("exception in processData in  getCurrentSalMonth mehtod",e);
		} //end of catch
			try {
				if (processData == null) {//this if is for if the salary for currentMonth is not processed
					/**
					 * this CheckCurrentProcess is set to blank if processData is null. this value is get in
					 * the pTax calculation.
					 */
					tdsCal.setCheckCurrentProcess("");
				}//end of if
				else if (processData.length == 0) {//this else if is for if the salary for currentMonth is not processed
					tdsCal.setCheckCurrentProcess("");
					/**
					 * if the currentMonth is in between April to December and if suppose currentMonth=11(Nov)
					 */
					if (currentMonth >= 4 && currentMonth <= 12) {
						check = currentMonth - 4;//then here value of check=7 i.e--bcoz from April to October.
					} //end of if
					else {
						/**
						 * if currentMonth=1. then the value of currentMonth=9 i.e--bcoz from April to December
						 */
						check = 8 + currentMonth;
					} //end of else
				}//end of else if
				else {//this else is for if the salary for currentMonth is processed.
					tdsCal.setCheckCurrentProcess("1");
					/**
					 * if the currentMonth is in between April to December and if suppose currentMonth=11(Nov)
					 */
					if (currentMonth >= 4 && currentMonth <= 12) {
						check = currentMonth - 4 + 1;//then the value of check=8 i.e---bcoz from April to November
					} //end of if 
					else {
						check = 8 + currentMonth + 1;//if currentMonth=1 then the value of check=10 from April to January
					} //end of else
				} //end of else
			} catch (Exception e) {
				logger.error("error in getCurrentSalMonth "+e);
			} //end of catch
	return check;
	
	} //end of method getCurrentSalMonth
	
	/**
	 * this method is used to check whether the previous month salary is processed or not
	 * @param currentMonth
	 * @param tdsCal
	 * @param year
	 * @return
	 */
	private int getPreviousSalMonth(int currentMonth, TdsCalculation tdsCal, String year) {
		int check=0;
		Object[][] processData = null;
		try{
				String process = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR="+year+" " 
									+" AND LEDGER_STATUS IN('SAL_FINAL','SAL_START') " 
									+" AND LEDGER_MONTH="+currentMonth+" AND LEDGER_DIVISION="+tdsCal.getDivisionCode();
		
			if (tdsCal.getBranchFlag().equals("true")) {
				process = process + " AND LEDGER_BRN=" + tdsCal.getBranchCode();
			}//end of if
			if (tdsCal.getEmptypeFlag().equals("true")) {
				process = process + " AND LEDGER_EMPTYPE=" + tdsCal.getTypeCode();
			}//end of if
			if (tdsCal.getPaybillFlag().equals("true")) {
				process = process + " AND LEDGER_PAYBILL=" + tdsCal.getPayBillNo();
			}//end of if
			if (tdsCal.getDepartmentFlag().equals("true")) {
				process = process + " AND LEDGER_DEPT=" + tdsCal.getDeptCode();
			} //end of if
			processData = getSqlModel().getSingleResult(process);
		} catch (Exception e) {
			logger.error("error in processData in getPreviousSalMonth "+e);
		} //end of catch
			try {
				if (processData == null) {
					check=1;
				}//end of if
				else if (processData.length == 0) {
					check=1;
				}//end of else if
				else {
					check=0;//if salary is processed then the value of check is returned 0
				}
			} catch (Exception e) {
				logger.error("error in getPreviousSalMonth "+e);
			} //end of catch
	return check;
	
	} //end of method


	/**
	 * this method is used to calculate all the credits of the employee anually, it is
	 * calulated depending upon the processed salasry.
	 * @param frmYear
	 * @param toYear
	 * @param tdsCal
	 * @param empID
	 * @param location
	 * @param taxData
	 * @param processData--ledger code for month april to dec
	 * @param processData1----ledger code for month jan to march
	 * @param taxParameters
	 * @param joinedMonth
	 * @param currentMonth 
	 * @param monthCnt 
	 * @param empJoinedMonth 
	 * @param joinedYear 
	 * @param forPreviousYear 
	 * @param resignedMonth 
	 * @return creditDataFinal--this object contains the list of credit values,names
	 */
	private Object[][] getIncomeChanged(String frmYear, String toYear,
			TdsCalculation tdsCal, String empID, String location,
			Object[][] taxData, Object[][] processData,
			Object[][] processData1, Object[][] taxParameters,
			String joinedMonth, int currentMonth, int monthCnt, int joinedYear, int empJoinedMonth, int forPreviousYear, double resignedMonth) {
		//logger.info("processData   :"+processData.length);
		//logger.info("processData1   :"+processData1.length);
		logger.info("taxParameters[0][6] HRA Metro formula  --"+taxParameters[0][6]);
		logger.info("currentMonth   :"+currentMonth);
		logger.info("joinedYear   :"+joinedYear);
		logger.info("empJoinedMonth   :"+empJoinedMonth);
		logger.info("joinedMonth   :"+joinedMonth);
		logger.info("monthCnt   :"+monthCnt);
		logger.info("resignedMonth   :"+resignedMonth);
		int salPreProcessCount=0,piCount=0;
		//double processDebAmt1 = 0.0, debMonthAmt = 0, taxPaid = 0;
		double hraAmount = 0,hraAmt2=0,hraAmt1=0,resultAmtHra=0,ptaxMonTotAmt = 0, 
		totHraAmt = 0,totHraAmt1 = 0,totHraAmt2=0, hraAmountUnProc = 0,
		hraAmountUnProc1=0,hraAmountUnProc2=0, resultAmt = 0,resultAmt1=0;
		double[] returnObj=new double[2],returnObj1=new double[2];
		tdsCal.setHraPaidAmtCond("");
		String debPtaxCode = "",ledgerCode="",ledgerCode1="",debitTaxPaid="",debitTaxPaid1="";
		Object salCreditAprToDec[][]=null,salCreditJanToMarch[][]=null,salDebitAprToDec[][]=null,salDebitJanToMar[][]=null;
		/**this changes started on 15/11/08 to seperate the setConfCredit
		 * 
		 */
		
		/**
		 * this if and else if conditions are to check if the calculation is for the Previous/Current/Further years
		 */
		if(empJoinedMonth == currentMonth && String.valueOf(joinedYear).equals(String.valueOf(frmYear))){
			logger.info("same join year   :");
		}
		else{
			logger.info("forPreviousYear getIncome method   "+forPreviousYear);
			
			//this if is for previous year 
			if(Integer.parseInt(frmYear) < forPreviousYear){
				logger.info("in previous year getIncome method");
				if(currentMonth==4){
					try {
						/**
						 * this count contains the value 0 or 1. if the previous month salary is processed then the
						 * value of count is 0
						 */
						salPreProcessCount = getPreviousSalMonth(currentMonth - 1, tdsCal, frmYear);
					} catch (Exception e) {
						logger.error("exception in salPreProcessCount",e);
					} //end of catch
				} //end of if
			} //end of if
			//this else if is for future year
			else if(Integer.parseInt(frmYear) > Integer.parseInt(tdsCal.getCurrentYear())
					&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
				logger.info("in future year getIncome method");
			} //end of else if
			else{// this else is for current year calculation
				logger.info("in current year getIncome method");
				//if currentMonth between April to Dec
				if(currentMonth>=4 && currentMonth<=12){
					try {
						salPreProcessCount = getPreviousSalMonth(currentMonth - 1, tdsCal, frmYear);
					} catch (Exception e) {
						logger.error("exception in salPreProcessCount",e);
					} //end of catch
				} //end of if
				//if currentMonth between Jan To March
				else{
					
					if(String.valueOf(currentMonth).equals("1")){//this if checks if the currentMonth is Jan
						try {
							//than salPreProcessCount is checked for previous month i.e is December
							salPreProcessCount = getPreviousSalMonth(12,tdsCal, frmYear);
						} catch (Exception e) {
							logger.error("exception in salPreProcessCount for previousSalmonth",e);
						} //end of catch
					}//end of if
					else{//this else is check for if the currentMonth is Feb or March
						try {
							salPreProcessCount = getPreviousSalMonth(
									currentMonth - 1, tdsCal, toYear);
						} catch (Exception e) {
							logger.error("exception in salPreProcessCount for previousSalmonth",e);
						} //end of catch
					}//end of else
				}//end of else
				logger.info("salPreProcessCount   in get income method"+salPreProcessCount);
				/**
				 * this monthCnt(which can also be called as "Remaining Months") is the Final value which will be 
				 * multiplied by the credits from the Configuration
				 */
				monthCnt = monthCnt + salPreProcessCount;
			}//end of nested else
		}//end of else
		
		if(resignedMonth > 0){
			monthCnt = 0;
		}
		
		logger.info("monthCnt in getIncome   1 :"+monthCnt);
		
		/**
		 * this to check for adding settlement amount, it is added only when the employee have Resigned.
		 * So initially we have to check whether the employee have resigned or not...
		 */
		/*Object[][]resignData = null;
		Object[][]settleCredit = null;
		resignData = getResignData(empID);
		if(resignData == null){
			
		} //end of if
		else if(resignData.length == 0){
			
		} //end of else if
		else{
			*//**
			 * this if condition is checked if the employee has withdrwan his settlement amount.....
			 * here monthCnt id set 0....bcoz for this employee "to be paid" salary from configuration 
			 * will not come....
			 *//*
			if(String.valueOf(resignData[0][1]).equals("Y")){
				monthCnt = 0;
				logger.info("if settlement withdrawn-----");
			} //end of if
			else{
				monthCnt = 0;
				settleCredit = getSettleCredit(resignData);
			} //end of else
		} //end of else
*/		
			/**
			 * this creditTotal object consists of all the credits from emp_creedit which are taxable and
			 * their periodicity "Monthly"
			 */
			Object creditTotal[][] = null;
			
			try {
				creditTotal = setConfCredit(empID);
			} catch (Exception e) {
				logger.error("exception in creditTotal object",e);
			} //end of catch
			
			if(creditTotal == null){//if object is null
				
			} //end of if
			else if(creditTotal.length == 0){//else if credit object is blank
				
			} //end of else if
			else{//else if it contains data
				try {
					/**
					 * this creditTotal loop is started here to get the total of credit amount for PTax before
					 * Arrears,allowance, salary process amount are added in the Conf credit amount.
					 */
					for (int i = 0; i < creditTotal.length; i++) {//start of creditTotal for loop
						if (String.valueOf(creditTotal[i][3]).equals("M")) {//in this if it checks 
							//the credits which are monthly here ptaxMonTotAmt contains the sum of all the credit amount.
							ptaxMonTotAmt = ptaxMonTotAmt+ Double.parseDouble(String.valueOf(creditTotal[i][1]));
						} //end of if
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in ptaxMonTotAmt",e);
				} //end of catch
			} //end of else
			
			logger.info("ptaxMonTotAmt================"+ptaxMonTotAmt);
			tdsCal.setPtaxMonthTotGross(String.valueOf(ptaxMonTotAmt));//the sum of all the conf credit amt is set
			
			debPtaxCode = getEmpPtaxDebCode(location, taxData);
			tdsCal.setDebPtaxCode(debPtaxCode);///to be used in get record method for ptax.
			//processMonthCnt = processData.length + processData1.length;
			logger.info("debPtaxCode================"+debPtaxCode);
			
			/**
			 * methods for calculating Arrears
			 */
				///to get frmYear arrears from April To December
				Object arrearsAprToDec[][] = null;
				
				try {
					arrearsAprToDec = getArrearsAmt(4, 12, empID, frmYear);
				} catch (Exception e) {
					logger.error("exception in arrearsAprToDec object",e);
				} //end of catch
				
				//to get toMonth arrears from Jan To March
				Object arrearsJanToMar[][] = null;
				
				try {
					arrearsJanToMar = getArrearsAmt(1, 3, empID, toYear);
				} catch (Exception e) {
					logger.error("exception in arrearsJanToMar object",e);
				} //end of catch
				
				/**
				 * to get previous year arrears which is received in next year
				 */
				//to get preMonthArrears which means the arrears processed for frmYear but are received in toYear
				Object preMonthArrears[][] = null;
				try {
					preMonthArrears = getPrevArrears(4, 12, empID, frmYear,toYear);
				} catch (Exception e) {
					logger.error("exception in preMonthArrears object",e);
				} //end of catch
				if(creditTotal == null){//if credit obj is null
					
				}//end of if
				else if(creditTotal.length == 0){
					
				}//end of else if
				else{
					try {
						/**
						 * this for loop is for arrears for financial year frm...dat is Reflecting year is  FrmYear
						 */
						for (int j = 0; j < creditTotal.length; j++) {
							/**
							 * here this config credit is multiplied by remaining months
							 */
							if (String.valueOf(creditTotal[j][3]).equals("M")) {//in this if it checks 
								creditTotal[j][1] = Double.parseDouble(String
										.valueOf(creditTotal[j][1]))
										* monthCnt;
								//logger.info("creditTotal[j][0]===>"+creditTotal[j][0]);
								//logger.info("creditTotal[j][1]===>"+creditTotal[j][1]);
							} //end of if
							

							if (arrearsAprToDec == null) {

							}//end of if
							else if (arrearsAprToDec.length == 0) {

							}//end of else
							else {
								//this loop is to add the arrears of April to December in the creditTotal amount
								for (int i = 0; i < arrearsAprToDec.length; i++) {
									//this if condition is to check the creditTotal code should be equal to arrear credit code
									//is it is equal than that arrear amount is added in the respective creditTotal amount
									if (String.valueOf(creditTotal[j][0]).equals(String.valueOf(arrearsAprToDec[i][0]))) {
										creditTotal[j][1] = Double.parseDouble(String.valueOf(creditTotal[j][1]))
												+ Double.parseDouble(String.valueOf(arrearsAprToDec[i][1]));
										//logger.info("creditTotal[j][0]=after arrears==>"+creditTotal[j][0]);
										//logger.info("creditTotal[j][1]=after arrears==>"+creditTotal[j][1]);
									} //end of if
								} //end of loop
							} //end of else
							
							/**
							 * here settlement amount is added in the credits.....
							 */
							/*if(settleCredit == null){
								
							} //end of else
							else if(settleCredit.length == 0){
								
							} //end of else
							else{
								//this loop is to add the settlement credit in the creditTotal amount
								for (int i = 0; i < settleCredit.length; i++) {
									if (String.valueOf(creditTotal[j][0]).equals(String.valueOf(settleCredit[i][0]))) {
										creditTotal[j][1] = Double.parseDouble(String.valueOf(creditTotal[j][1]))
												+ Double.parseDouble(String.valueOf(settleCredit[i][1]));
									} //end of if 
								} //end of loop
							} //end of else for Settlement*/	
					} //end of creditTotal loop
					
					/**
					 * this for loop is for arrears for financial year to...dat is Reflecting year is ToYear
					 */
					for (int j = 0; j < creditTotal.length; j++) {
						if(arrearsJanToMar == null){
							
						}//end of if
						else if(arrearsJanToMar.length == 0){
							
						}//end of else
						else{
							//this loop is to add the arrears of Jan to March in the creditTotal amount
							for (int i = 0; i < arrearsJanToMar.length; i++) {
								//this if condition is to check the creditTotal code should be equal to arrear credit code
								//is it is equal than that arrear amount is added in the respective creditTotal amount
								if(String.valueOf(creditTotal[j][0]).equals(String.valueOf(arrearsJanToMar[i][0]))){
									creditTotal[j][1] = Double.parseDouble(String.valueOf(creditTotal[j][1])) + 
														Double.parseDouble(String.valueOf(arrearsJanToMar[i][1]));
									//logger.info("creditTotal[j][0]=after arrears=jan to marc=>"+creditTotal[j][0]);
									//logger.info("creditTotal[j][1]=after arrears=jan to march=>"+creditTotal[j][1]);
								}//end of if
							}//end of loop
						}//end of else
					}//end of creditTotal loop
					
					/**
					 * this for loop is for arrears for financial year to...dat is Reflecting year is FrmYear
					 * 
					 */
					for (int j = 0; j < creditTotal.length; j++) {
						if(preMonthArrears == null){
							
						}//end of if
						else if(preMonthArrears.length == 0){
							
						}//end of else
						else{
							//this loop is for arrears Apr To Dec but which are processed in Jan To march
							for (int i = 0; i < preMonthArrears.length; i++) {
								//this if condition is to check the creditTotal code should be equal to arrear credit code
								//is it is equal than that arrear amount is added in the respective creditTotal amount
								if(String.valueOf(creditTotal[j][0]).equals(String.valueOf(preMonthArrears[i][0]))){
									creditTotal[j][1] = Double.parseDouble(String.valueOf(creditTotal[j][1])) + 
														Double.parseDouble(String.valueOf(preMonthArrears[i][1]));
									//logger.info("creditTotal[j][0]=after pre month arrears=jan to marc=>"+creditTotal[j][0]);
									//logger.info("creditTotal[j][1]=after pre month arrears=jan to march=>"+creditTotal[j][1]);
								}//end of if
							}//end of loop
						}//end of else
					}//end of creditTotal loop
					} catch (Exception e) {
						logger.error("exception in creditTotal loop",e);
					} //end of catch
				} //end of creditToal else
				
				/**
				 * this credit object also includes Arrears amount.
				 */
				
				/**
				 * process for PI calculation 
				 */
					Object finalCredit[][]=null;
					Object piData[][] = null;
					
					try {
						piData = getperiodAllData(empID, frmYear, toYear);//this is Periodic Allowance obj
					} catch (Exception e) {
						logger.error("exception in piData object",e);
					} //end of catch
					if(piData == null){

					}//end of if
					else if(piData.length == 0){

					}//end of else if
					else{
						try {
							//this loop of periodic allowance is run to get the total count of allowances which have 
							//periodicity quarterly/half yearly/annually
							for (int i = 0; i < piData.length; i++) {
								//this condition is to check the allowance whose periodicity is not "Monthly"
								if (String.valueOf(piData[i][4]).equals("Q")
										|| String.valueOf(piData[i][4]).equals("A")
										|| String.valueOf(piData[i][4]).equals("H")) {
									piCount++;//this piCount contains the count of allowances whose periodicity is not "Monthly"
								}//end of if
							}//end of loop
						} catch (Exception e) {
							logger.error("exception in piData loop",e);
						} //end of catch
					}//end of else
					if(creditTotal == null){

					}//end of if
					else if(creditTotal.length == 0){

					}//end of else
					else{
						int addcount=0;
						try {
							//logger.info("piCount===============  :"+piCount);
							/**
							 * this finalCredit object now contains the length of creditTotal object which has only
							 * credits whose periodicity is "Monthly" & this piCount contains the number of credits whose
							 * periodicity is quarterly/half yearly/annually so both are added to get the final
							 * length of Object
							 */
							finalCredit = new Object[piCount+ Integer.parseInt(String.valueOf(creditTotal.length))][4];
						} catch (Exception e) {
							logger.error("exception in finalCredit object",e);
						} //end of catch
							//logger.info("finalCredit[][]===============  :"+finalCredit.length);
						try{
							for (int i = 0; i < creditTotal.length; i++) {//start of creditTotal loop
								
								/**
								 * this for adding pi amount for Monthly credits in credit total
								 */
								if(piData == null){
									
								}//end of if
								else if(piData.length == 0){
									
								}//end of else if
								else{
									/**
									 * this piData loop is started to add the periodic allowance of "Monthly" into
									 * the creditTotal amount....as uptil now the creditTotal obj contains only the
									 * credits whose periodicity is "Monthly"
									 */
									try{	
										for (int k = 0; k < piData.length; k++) {
											//this condition is to check the creditTotal code is equal to the piData 
											//credit code.
											if(String.valueOf(creditTotal[i][3]).equals(String.valueOf(piData[k][4])) 
													&& String.valueOf(creditTotal[i][0]).equals(String.valueOf(piData[k][1]))){
												//piData amount is added in the creditTotal obj.
												creditTotal[i][1] = Double.parseDouble(String.valueOf(creditTotal[i][1])) + 
												Double.parseDouble(String.valueOf(piData[k][2]));
												//logger.info("creditTotal[j][0]=after pi data=====>"+creditTotal[i][0]);
												//logger.info("creditTotal[j][1]=after pi data======>"+creditTotal[i][1]);
											}//end of if
										}//end of piData loop
									} catch (Exception e) {
										logger.error("exception in piData loop",e);
									} //end of catch	
								}//end of else
								
								//in this the creditTotal obj is copied into the finalCredit which now contains only credits
								//whose periodicity is "Monthly". It has conf (creditAmount * remainMonths)+arrearAmt+piAmt
								finalCredit[i][0] = creditTotal[i][0];
								finalCredit[i][1] = creditTotal[i][1];
								finalCredit[i][2] = creditTotal[i][2];
								finalCredit[i][3] = creditTotal[i][3];
								addcount++;//this count contains the finalCredit length count.
							} //end of loop
						} catch (Exception e) {
							logger.error("exception in for adding pi amount for Monthly credits in credit total",e);
						} //end of catch
							if(piData == null){
								
							}//end of if
							else if(piData.length == 0){
								
							}//end of else if
							else{
								//now this piData loop is run to add the credits whose periodicity is quarterly/half yearly/annually
								try{
									for (int i = 0; i < piData.length; i++) {
										/**
										 * this if is check to add only those credits into the final obj
										 * this addCount is used so that above where Monthly credits are added so after dat below in dat
										 * object remaining credits can be added
										 */
										 if(String.valueOf(piData[i][4]).equals("Q") || String.valueOf(piData[i][4]).equals("A")
												 || String.valueOf(piData[i][4]).equals("H")){
											 	finalCredit[addcount][0] = piData[i][1];
												finalCredit[addcount][1] = piData[i][2];
												finalCredit[addcount][2] = piData[i][3];
												finalCredit[addcount][3] = piData[i][4];
												addcount++;	
										 }//end of if
									}//end of piData loop
								} catch (Exception e) {
									logger.error("exception in piData loop is run to add the credits whose periodicity is quarterly/half yearly/annually",e);
								} //end of catch
							}//end of else
					}//end of else
			/**
			 * this for loop is for salary processed in April To December
			 */
			if (processData == null) {

			}//end of if
			else if (processData.length == 0) {

			}//end of else if 
			else{
				try {
					for (int i = 0; i < processData.length; i++) {
						ledgerCode += String.valueOf(processData[i][0]) + ",";//this ledger code contains the code for Apr To Dec
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in processData loop",e);
				} //end of catch
				//all the ledgerCodes for salary process from April to December are substring together.
				ledgerCode = ledgerCode.substring(0,ledgerCode.length()-1);	
				
				try {
					//this salCredit object is created for Apr To Dec salary process
					salCreditAprToDec = getSalCredit(empID, ledgerCode, frmYear);
				} catch (Exception e) {
					logger.error("exception in salCreditAprToDec salCredit object " +
							"is created for Apr To Dec salary process",e);
				} //end of catch
				if (salCreditAprToDec == null) {

				}//end of nested if
				else if (salCreditAprToDec.length == 0) {

				}//end of else if
				else{//this else to calculate the HRA amount for salary processed months for Apr to Dec.
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..((BA + DA)/100)*10)
						resultAmt = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditAprToDec, String
										.valueOf(taxParameters[0][8]), context,	session));
					} catch (Exception e) {
						logger.error("exception in resultAmt by passing the formula..((BA + DA)/100)*10)",e);
					} //end of catch
					
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..((BA + DA)/100)*40)
						logger.info("taxParameters[0][6] HRA Metro formula  --"+taxParameters[0][6]);
						resultAmt1 = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditAprToDec, String
										.valueOf(taxParameters[0][6]), context,session));
					} catch (Exception e) {
						logger.error("exception in resultAmt1 by passing the formula..((BA + DA)/100)*40)",e);
					} //end of catch
					
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..HRA
						resultAmtHra = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditAprToDec, String
										.valueOf(taxParameters[0][7]), context,session));
					} catch (Exception e) {
						logger.error("exception in resultAmtHra by passing the formula..HRA",e);
					} //end of catch
					if(resultAmt>0)
					hraAmount = resultAmt;///for ((BA + DA)/100)*10)
					if(resultAmt1>0)
					hraAmt1 = resultAmt1;//for ((BA + DA)/100)*40)
					if(resultAmtHra>0)
					hraAmt2 = resultAmtHra;//for HRA
				}///end of HRA else
				//this condition is checked if either of the value debPtaxCode or Income Tax code should not be blank
				if(!(debPtaxCode.equals(""))&& !(String.valueOf(taxParameters[0][0]).equals(""))){
					debitTaxPaid=debPtaxCode+","+String.valueOf(taxParameters[0][0]);//contains both codes.
				}//end of if
				else if(!(debPtaxCode.equals(""))){//else if debPtax code is not blank
					debitTaxPaid=debPtaxCode;
				}//end of else if
				else if(!(String.valueOf(taxParameters[0][0]).equals(""))){//else if Income Tax code is not blank
					debitTaxPaid=String.valueOf(taxParameters[0][0]);
				}//end of else
				if(!(debitTaxPaid.equals(""))){
					//this salDebits object is created for Apr To Dec salary process
					salDebitAprToDec = getSalDebit(empID,ledgerCode,frmYear,debitTaxPaid);
					
				}//end of if
				
				if (salDebitAprToDec != null) {
					if (salDebitAprToDec.length > 0) {
					try {
						//this returnObj contains the value of processed PTax and Income Tax amount for Apr To Dec
						returnObj = getTaxPaid(salDebitAprToDec, debPtaxCode,
								String.valueOf(taxParameters[0][0]), tdsCal);
					} catch (Exception e) {
						logger.error("exception in returnObj contains the value of processed PTax and Income Tax amount for Apr To Dec",e);
					} //end of catch  
					}//end of if
				}//end of if
			}//end of processData else
				
			/**
			 * this for loop is for salary processed in Jan To March
			 */
			if (processData1 == null) {

			}//end of if
			else if (processData1.length == 0) {

			}//end of else if 
			else{
				try {
					for (int i = 0; i < processData1.length; i++) {
						ledgerCode1 += String.valueOf(processData1[i][0]) + ",";//this ledger code contains the code for Jan To March
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in processData1 loop",e);
				} //end of catch
				ledgerCode1 = ledgerCode1.substring(0,ledgerCode1.length()-1);	
				
				try {
					//this salCredit object is created for Jan To March
					salCreditJanToMarch = getSalCredit(empID, ledgerCode1,toYear);
				} catch (Exception e) {
					logger.error("exception in salCreditJanToMarch",e);
				} //end of catch
				if (salCreditJanToMarch == null) {

				}//end of nested if
				else if (salCreditJanToMarch.length == 0) {

				}//end of else if
				else{//this else to calculate the HRA amount for salary processed months for Jan To March.
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..((BA + DA)/100)*10)
						resultAmt = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditJanToMarch, String
										.valueOf(taxParameters[0][8]), context,
										session));
					} catch (Exception e) {
						logger.error("exception in resultAmt by passing the formula..((BA + DA)/100)*10)",e);
					} //end of catch
					
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..((BA + DA)/100)*40)
						logger.info("taxParameters[0][6] HRA Metro formula  --"+taxParameters[0][6]);
						resultAmt1 = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditJanToMarch, String
										.valueOf(taxParameters[0][6]), context,
										session));
					} catch (Exception e) {
						logger.error("exception in resultAmt1 by passing the formula..((BA + DA)/100)*40)",e);
					} //end of catch
					
					try {
						// this method is to calculate hraAmt for process months by
						// passing the formula..HRA
						resultAmtHra = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(salCreditJanToMarch, String
										.valueOf(taxParameters[0][7]), context,
										session));
					} catch (Exception e) {
						logger.error("exception in resultAmtHra by passing the formula..HRA",e);
					} //end of catch
					if(resultAmt>0)
					hraAmount += resultAmt;///for ((BA + DA)/100)*10)
					if(resultAmt1>0)
					hraAmt1 += resultAmt1;//for ((BA + DA)/100)*40)
					if(resultAmtHra>0)
					hraAmt2 += resultAmtHra;//for HRA
				} //end of HRA else
				//this condition is checked if either of the value debPtaxCode or Income Tax code should not be blank
				if(!(debPtaxCode.equals(""))&& !(String.valueOf(taxParameters[0][0]).equals(""))){
					debitTaxPaid1=debPtaxCode+","+String.valueOf(taxParameters[0][0]);
				} //end of if
				else if(!(debPtaxCode.equals(""))){//else if debPtax code is not blank
					debitTaxPaid1=debPtaxCode;
				} //end of else if
				else if(!(String.valueOf(taxParameters[0][0]).equals(""))){//else if Income Tax code is not blank
					debitTaxPaid1=String.valueOf(taxParameters[0][0]);
				} //end of else if
				if(!(debitTaxPaid1.equals(""))){
					try {
						//this salDebits object is created for Jan to March salary process
						salDebitJanToMar = getSalDebit(empID, ledgerCode1,toYear, debitTaxPaid1);
					} catch (Exception e) {
						logger.error("exception in salDebitJanToMar",e);
					} //end of catch
				} //end of if
				if (salDebitJanToMar != null) {
					if (salDebitJanToMar.length > 0) {
						try {
							//this returnObj1 contains the value of processed PTax and Income Tax amount for Jan To Mar
							returnObj1 = getTaxPaid(salDebitJanToMar,debPtaxCode, String.valueOf(taxParameters[0][0]),tdsCal);
						} catch (Exception e) {
							logger.error("exception in returnObj1 contains the value of processed PTax and Income Tax amount for Jan To Mar",e);
						} //end of catch  
					} //end of if
				} //end of if
			} //end of processData1 else
			//this MonthTotAmt contains the total of PTax amount for all the salary processed month
			tdsCal.setMonthTotAmt(String.valueOf(returnObj[0]+returnObj1[0]));
			tdsCal.setTaxPaid(String.valueOf(returnObj[1]+returnObj1[1]));//this contains the total for Income Tax for all the salary processed months		
			tdsCal.setMonthCount(String.valueOf(monthCnt));//contains the remaining months count
			
			//uptill now in the finalCredit obj only the unprocessed months credits are there
			if (finalCredit == null) {

			}//end of nested if
			else if (finalCredit.length == 0) {

			}//end of else if
			else{//this else is to get the HRA amount for unprocessed months
				try {
					hraAmountUnProc = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(finalCredit, String
									.valueOf(taxParameters[0][8]), context,	session));//(BAD+DA)*10%
				} catch (Exception e) {
					logger.error("exception in hraAmountUnProc in get income",e);
				} //end of catch
				
				try {
					hraAmountUnProc1 = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(finalCredit, String
									.valueOf(taxParameters[0][6]), context,session));//(BAD+DA)*.40
				} catch (Exception e) {
					logger.error("exception in hraAmountUnProc1 in get income",e);
				} //end of catch
				
				try {
					hraAmountUnProc2 = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(finalCredit, String
									.valueOf(taxParameters[0][7]), context,session));//hra
				} catch (Exception e) {
					logger.error("exception in hraAmountUnProc2 in get income",e);
				} //end of catch
			}
			//logger.info("monthCnt    :"+monthCnt);
			//here the total of processed and unprocessed months are added for (BAD+DA)*10% formula
			totHraAmt = hraAmount + hraAmountUnProc;
			totHraAmt1 = hraAmt1 + hraAmountUnProc1;
			totHraAmt2 = hraAmt2 + hraAmountUnProc2;
			tdsCal.setHraPaidAmtCond(String.valueOf(totHraAmt));//this HRA paid condition for (BA+DA)*0.10
			tdsCal.setHraPaidAmtCond1(String.valueOf(totHraAmt1));//this HRA paid condition for HRA
			tdsCal.setHraPaidAmtCond2(String.valueOf(totHraAmt2));//this HRA paid condition for (BA+DA)*0.40
			/**
			 * if the monthCnt is 12 that means the Tds calculation is for Future Financial Year so the
			 * monthCnt value is 12, bcoz only the conf credit amount is multiplied by 12. But this is done above
			 * at the start of the getIncome method so this if is kept blank
			 */ 
			if(monthCnt==12){
				logger.info("in monthCnt==12    : ");
				for (int i = 0; i < finalCredit.length; i++) {
					/**
					 * this have been multiplied above
					 */
					/*if (String.valueOf(finalCredit[i][3]).equals("M")) {
						finalCredit[i][1] = Double.parseDouble(String
								.valueOf(finalCredit[i][1])) * 12;
					}//end of nested else
*/				   if (String.valueOf(finalCredit[i][3]).equals("Q")) {
					   finalCredit[i][1] = Double.parseDouble(String
								.valueOf(finalCredit[i][1])) * 4;
					}//end of nested else
					else if (String.valueOf(finalCredit[i][3]).equals("H")) {
						finalCredit[i][1] = Double.parseDouble(String
								.valueOf(finalCredit[i][1])) * 2;
					}//end of nested else if
					else {
						finalCredit[i][1] = finalCredit[i][1];
					}//end of nested else
				}
			} //end of if
			/**
			 * this else is for previous and current financial year calculation. For previous year calculation
			 * monthCnt value is "0"
			 */
			else{
				if (finalCredit == null) {

				}//end of nested if
				else if (finalCredit.length == 0) {

				}//end of else if
				else{
				//logger.info("in monthCnt else    : "+monthCnt);
				try{	
					for (int i = 0; i < finalCredit.length; i++) {
							if(String.valueOf(finalCredit[i][3]).equals("M")){ //only for the "Monthly credits"
								if (salCreditAprToDec == null) {//if the obj is null den the same value of obj is copied
										//logger.info("in finalCredit[i][1] if M   : "+finalCredit[i][1]);
										finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) ;
																
								}//end of nested if
								else if(salCreditAprToDec.length==0){
										//logger.info("in finalCredit[i][1]    : "+finalCredit[i][1]);
										finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) ;
								}//end of else if
								else{
									//logger.info("in salCreditAprToDec else M   : "+salCreditAprToDec.length);
									try{
										for (int j = 0; j < salCreditAprToDec.length; j++) { //loop for sal process Apr to Dec
											if(String.valueOf(salCreditAprToDec[j][0]).equals(String.valueOf(finalCredit[i][0]))){
												//logger.info("in finalCredit[i][1]    : "+finalCredit[i][1]);
												//logger.info("in salCreditAprToDec[j][0]    : "+salCreditAprToDec[j][0]);
												/**
												 * here in this finalCredit salary process amount is added in the respective credit
												 * codes which are compared in the if condition just above
												 */
												finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) + 
																	Double.parseDouble(String.valueOf(salCreditAprToDec[j][1]));
												//logger.info("finalCredit[i][0] ==apr to dec====>"+finalCredit[i][0] );
												//logger.info("finalCredit[i][1] ==apr to dec=====>"+finalCredit[i][1] );
											}//end of if
										} //end of loop
									} catch (Exception e) {
										logger.error("exception in salCreditAprToDec in finalCredit loop",e);
									} //end of catch
								} //end of else
								
								/**
								 * this if and else if conditions are to check if the calculation is for the Previous/Current/Further years
								 */
								if(Integer.parseInt(frmYear) < forPreviousYear){ //this if for previous year
									if (salCreditJanToMarch == null) {
									}//end of nested if
									else if(salCreditJanToMarch.length ==0){
										
									}//end of else if
									else{
										try{
											for (int j = 0; j < salCreditJanToMarch.length; j++) { //loop for sal process Jan To March
												if(String.valueOf(salCreditJanToMarch[j][0]).equals(String.valueOf(finalCredit[i][0]))){
													/**
													 * Now here this finalCredit obj also contains the conf credit amt,arrear amt,pi amt and
													 * salary process amount for April to December
													 * here in this finalCredit salary process amount is added in the respective credit
													 * codes which are compared in the if condition just above
													 */
													finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) + 
																		Double.parseDouble(String.valueOf(salCreditJanToMarch[j][1]));
													//logger.info("finalCredit[i][0] ==jan to mar====>"+finalCredit[i][0] );
													//logger.info("finalCredit[i][1] ==jan to mar=====>"+finalCredit[i][1] );
												} //end of if
											} //end of loop
										} catch (Exception e) {
											logger.error("exception in salCreditJanToMarch in finalCredit loop",e);
										} //end of catch
									} //end of else
								} //end of previous year if
								else if(Integer.parseInt(frmYear) > Integer.parseInt((tdsCal.getCurrentYear()))
										&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
									//this else if condition for future year calculation
								} //end of future year else if
								else{//now this else is for Current year Tds calculation
									if(!(currentMonth>=4 && currentMonth<=12)){ //currentMonth is between Jan To March
										if (salCreditJanToMarch == null) {
											finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) ;
											}//end of nested if
										else if(salCreditJanToMarch.length ==0){
											finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) ;
										}
										else{ //if salary is processed for jan to march
												try{
														for (int j = 0; j < salCreditJanToMarch.length; j++) {
															if(String.valueOf(salCreditJanToMarch[j][0]).equals(String.valueOf(finalCredit[i][0]))){
																/**
																 * here in this finalCredit salary process amount is added in the respective credit
																 * codes which are compared in the if condition just above
																 */
																finalCredit[i][1] = Double.parseDouble(String.valueOf(finalCredit[i][1])) + 
																					Double.parseDouble(String.valueOf(salCreditJanToMarch[j][1]));
																//logger.info("finalCredit[i][0] ==jan to mar====>"+finalCredit[i][0] );
																//logger.info("finalCredit[i][1] ==jan to mar=====>"+finalCredit[i][1] );
															} //end of if
														} //end of jan to march loop
													} catch (Exception e) {
														logger.error("exception in salCreditJanToMarch loop if salary is processed for jan to march",e);
													} //end of catch	
												} //end of else
										} //end of currentMonth if
								} //end of current year else
							} //end of "Montlhy" credits if
							else if (String.valueOf(finalCredit[i][3]).equals("Q")) {
								double forCalculation = 0;
								forCalculation = (Double.parseDouble(String
										.valueOf(finalCredit[i][1])) * 4)/12;
								forCalculation = forCalculation * (12 - Double.parseDouble(joinedMonth));
								logger.info("forCalculation===>"+forCalculation);
								finalCredit[i][1] = forCalculation;
							}//end of nested else
							else if (String.valueOf(finalCredit[i][3]).equals("H")) {
								double forCalculation = 0;
								forCalculation = (Double.parseDouble(String
										.valueOf(finalCredit[i][1])) * 2)/12;
								forCalculation = forCalculation * (12 - Double.parseDouble(joinedMonth));
								finalCredit[i][1] = forCalculation;
							}//end of nested else if
							else {
								double forCalculation = 0;
								forCalculation = Double.parseDouble(String.valueOf(finalCredit[i][1]))/12;
								forCalculation = forCalculation * (12 - Double.parseDouble(joinedMonth));
								finalCredit[i][1] = forCalculation;
							}//end of nested else
					} //end of finalCredit for loop
				} catch (Exception e) {
					logger.error("exception in finalCredit for loop",e);
				} //end of catch	
				} //end of finalCredit else
			} //end of else for previous and current year tds calculation
		return finalCredit ;
	} //end of getIncomeChanged method
	
	/**
	 * this method retrieves all the settlement credit amount of the employee who has resigned...
	 * @param resignData
	 * @return
	 */
	private Object[][] getSettleCredit(Object[][] resignData) {
		Object[][]setCreditData = null;
		
		try {
			String query = "SELECT SETTL_CREDIT_CODE,SETTL_AMT FROM HRMS_SETTL_CREDITS WHERE SETTL_CODE = "
					+ resignData[0][2] + "";
			setCreditData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in setCreditData",e);
		} //end of catch
		return setCreditData;
	} //end of method


	/**
	 * thid method returns Resign Code and whether Resign Withdrawn Yes/No...
	 * @param empID
	 * @return
	 */
	private Object[][] getResignData(String empID) {
		Object[][]resignData = null;
		try {
			String query = "SELECT RESIGN_CODE,RESIGN_WITHDRAWN,SETTL_CODE FROM HRMS_RESIGN "
						+" LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_RESGNO = HRMS_RESIGN.RESIGN_CODE) "
						+" WHERE RESIGN_EMP = "+empID+""; 
			resignData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in resignData query",e);
		} //end of catch
		return resignData;
	} //end of method


	/**
	 * this mehtod is to get the periodic allowance data.
	 * @param empID
	 * @param frmYear
	 * @param toYear
	 * @return data
	 */
	private Object[][] getperiodAllData(String empID, String frmYear,String toYear) {
		
		Object[][] data=null;
		try{
		String query = "SELECT HRMS_ALLOWANCE_HDR.ALLW_ID,ALLW_CREDIT_HEAD,ALLW_AMOUNT_FINAL,CREDIT_NAME,CREDIT_PERIODICITY "
		+" FROM HRMS_ALLOWANCE_HDR "
		+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) "
		+" WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID="+empID+"  "
		+" AND ALLW_PROCESS_DATE>=TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') AND ALLW_PROCESS_DATE<=TO_DATE('31-03-"+toYear+"','DD-MM-YYYY')";
		
		data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("in getperiodAllData "+e);
		}
		return data;
	}


	private Object[][] getArrearsAmt(int frmMonth, int toMonth, String empID, String year) {
		
		Object[][] arrearsData=null;
		
		try{
			String query = "SELECT ARREARS_CREDIT_CODE,SUM(NVL(ARREARS_AMT,0)) FROM HRMS_ARREARS_CREDIT_"+year+" "
			+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE " 
			+" WHERE ARREARS_EMP_ID="+empID+" AND ARREARS_MONTH>="+frmMonth+" AND ARREARS_MONTH<="+toMonth+" AND ARREARS_YEAR="+year+" AND CREDIT_TAXABLE_FLAG='Y' "
			+" GROUP BY ARREARS_CREDIT_CODE";
			
			arrearsData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("in getArrearsAmt "+e);
		}
	return arrearsData;
	}
	
	private Object[][] getPrevArrears(int frmMonth, int toMonth, String empID, String frmYear,String toYear) {
		Object[][] arrearsData=null;
		try{
			String query = "SELECT ARREARS_CREDIT_CODE,SUM(NVL(ARREARS_AMT,0)) FROM HRMS_ARREARS_CREDIT_"+toYear+" "
			+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_CREDIT_CODE " 
			+" WHERE ARREARS_EMP_ID="+empID+" AND ARREARS_MONTH>="+frmMonth+" AND ARREARS_MONTH<="+toMonth+" AND ARREARS_YEAR="+frmYear+" AND CREDIT_TAXABLE_FLAG='Y' "
			+" GROUP BY ARREARS_CREDIT_CODE";
			
			arrearsData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("in getPrevArrears "+e);
		}
	return arrearsData;
	}
	
	
		private Object[][] getArrearsDebitAmt(int frmMonth, int toMonth, String empId,String year, String debPtaxCode) {
			Object[][]arrearsData=null;
			
			try{
				String query = "SELECT ARREARS_DEBIT_CODE,SUM(NVL(ARREARS_AMT,0)) FROM HRMS_ARREARS_DEBIT_"+year+" "
					+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE) "
					+" WHERE ARREARS_EMP_ID="+empId+" "
					+" AND ARREARS_MONTH>="+frmMonth+" AND ARREARS_MONTH<="+toMonth+" AND ARREARS_YEAR="+year+" AND ARREARS_DEBIT_CODE="+debPtaxCode+" AND DEBIT_ISEXEMPT='Y' " 
					+" GROUP BY ARREARS_DEBIT_CODE";
				
				arrearsData = getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error("in getArrearsAmt "+e);
			}
			
		return arrearsData;
	}
		
		private Object[][] getPrevArrearsDebit(int frmMonth, int toMonth, String empId,String frmYear, String toYear, String debPtaxCode) {
			Object[][]arrearsData=null;
			
			try{
				String query = "SELECT ARREARS_DEBIT_CODE,SUM(NVL(ARREARS_AMT,0)) FROM HRMS_ARREARS_DEBIT_"+toYear+" "
					+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_DEBIT_CODE) "
					+" WHERE ARREARS_EMP_ID="+empId+" "
					+" AND ARREARS_MONTH>="+frmMonth+" AND ARREARS_MONTH<="+toMonth+" AND ARREARS_YEAR="+frmYear+" AND ARREARS_DEBIT_CODE="+debPtaxCode+" AND DEBIT_ISEXEMPT='Y' " 
					+" GROUP BY ARREARS_DEBIT_CODE";
				
				arrearsData = getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error("in getArrearsAmt "+e);
			}
			
			return arrearsData;
		}

	/**
	 * this method is used to calculate the tax by passing the amount in the slabs 
	 * and calulating it.
	 * @param total
	 * @param taxData
	 * @return taxOnIncomeElse
	 */
	public double getTaxAmount(Double total, Object[][] taxData) {
		try {

			double totalTaxOnIncome = 0.0;
			double totalTaxOnIncomeElse = 0.0;
			double taxOnIncomeElse = 0.0;
			double diff = 0;
			double taxAmt = 0;
			String xyz = String.valueOf(total);
			double remain = total;
			try {
				if(taxData == null){
					
				} //end of if
				else if(taxData.length == 0){
					
				} //end of else if
				else{
					for (int i = 0; i < taxData.length; i++) {
						// total==>> totalGrossAmt - totalInvestmentAmount
						// it checks if total is greater than TAX_TO_AMT FROM
						// HRMS_TAX_SLAB
						if (total >= Double.parseDouble(String.valueOf(taxData[i][1]))) {
							// THIS conditions are check to make the value round for e:g
							// if tax_from_amt is 10001 so if we take 10%
							// of dat so it comes some 999 so to make it round we are
							// adding it 1
							if (i == 0) {
								diff = Math.abs(((Double.parseDouble(String
										.valueOf(taxData[i][1])) - Double.parseDouble(String.valueOf(taxData[i][0])))));
								// in diff==>> TAX_TO_AMT - TAX_FROM_AMT
							}//end of nested if
							else {
								diff = Math
										.abs(((Double.parseDouble(String
												.valueOf(taxData[i][1])) - Double.parseDouble(String
														.valueOf(taxData[i][0]))) + 1));
							}//end of nested else
							// remain===>>TOTAL
							remain = remain - diff;
							// taxData[i][2]==> TAX_PERCENT
							taxAmt = ((diff * (Double.parseDouble(String
									.valueOf(taxData[i][2])))) / 100);
							diff = 0;
							totalTaxOnIncome += Double.parseDouble(String
									.valueOf(taxAmt));
						}////end of if
						// this else is for the last Slab row.
						else {
							// /in this the TAX_PERCENTAGE is applied to all the
							// remaining amount
							taxAmt = (remain * (Double.parseDouble(String
									.valueOf(taxData[i][2])))) / 100;

							totalTaxOnIncomeElse += Double.parseDouble(String
									.valueOf(taxAmt));
							break;
						}//end of else
					} //end of loop
				} //end of else
				
			} catch (Exception e) {
				logger.error("exception in taxData loop in getTaxAmount method",e);
			} //end of catch
			taxOnIncomeElse = totalTaxOnIncome + totalTaxOnIncomeElse;
			return taxOnIncomeElse;
		} catch (Exception e) {
			logger.info("Exception in getTaxAmount method");
			return 0;
		} //end of catch

	}//end of getTaxAmount method

	/**
	 * this method is used to retrieve the values of the house rent data monthwise.
	 * @param empID
	 * @param frmYear
	 * @param toYear
	 * @return rentMontData
	 */
	private Object[][] getRentMontwiseData(String empID, String frmYear,
			String toYear) {
		Object[][] rentMontData=null;
		try {
			String query = "SELECT RENT_MONTH,RENT_AMOUNT,HRMS_HOUSERENT_HDR.RENT_CODE "
					+ " FROM HRMS_HOUSERENT_HDR "
					+ " INNER JOIN HRMS_HOUSERENT_DTL ON(HRMS_HOUSERENT_DTL.RENT_CODE = HRMS_HOUSERENT_HDR.RENT_CODE) "
					+ " WHERE RENT_EMPID="
					+ empID
					+ " AND RENT_FROMYEAR="
					+ frmYear
					+ " AND RENT_TOYEAR="
					+ toYear
					+ ""
					+ "ORDER BY RENT_MONTH";
			rentMontData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getRentMontwiseData "+e);
		} //end of catch
		return rentMontData;
	}//end of getRentMontwiseData method

	/**
	 * This method is used to retrieve the data of house rent
	 * @param toYear 
	 * @param frmYear 
	 * @return rentData
	 */
	private Object[][] getRentData(String frmYear, String toYear) {

		Object[][] rentData=null;
		try {
			String query = "SELECT RENT_EMPID,RENT_FROMYEAR,RENT_TOYEAR,RENT_AMOUNT,HRMS_HOUSERENT_HDR.RENT_CODE FROM HRMS_HOUSERENT_HDR "
					+ "INNER JOIN HRMS_HOUSERENT_DTL ON (HRMS_HOUSERENT_DTL.RENT_CODE = HRMS_HOUSERENT_HDR.RENT_CODE)"
					+ "  WHERE RENT_FROMYEAR="
					+ frmYear
					+ " AND RENT_TOYEAR="
					+ toYear + "";
			rentData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getRentData "+e);
		} //end of catch
		return rentData;
	}//end of getRentData method

	/**
	 * this method is used to calculate the annual rent paid by the employee
	 * @param houseRentData
	 * @param empID
	 * @param frmYear
	 * @param toYear
	 * @return rentAmt--returns the value of rent amount
	 */
	private double calAnnualRentPaid(Object[][] houseRentData, String empID,
			String frmYear, String toYear) {
		double rentAmt = 0;
		try {
			//logger.info("in annual rent paid");
			// this loop is run to check the particular employee in that particular
			// Financial Year how much rent Amount
			// he has paid.
			for (int j = 0; j < houseRentData.length; j++) {
				// houseRentData[j][3]===> it consists Rent Amount
				// if empId, frmYear and toYear is equal then the total Rent Amount
				// is added for e:g frm April 2008 to March 2009
				//logger.info("houseRentData[j][0]   :"+houseRentData[j][0]);
				//logger.info("empID :"+empID);
				//logger.info("houseRentData[j][1]   :"+houseRentData[j][1]);
				//logger.info("houseRentData[j][2]   :"+houseRentData[j][2]);
				if (String.valueOf(houseRentData[j][0]).equals("" + empID + "")
						&& String.valueOf(houseRentData[j][1]).equals(
								"" + frmYear + "")
						&& String.valueOf(houseRentData[j][2]).equals(
								"" + toYear + "")) {
					//logger.info("in annual if houseRentData[j][3]"+houseRentData[j][3]);
					rentAmt = rentAmt
							+ Double.parseDouble(String
									.valueOf(houseRentData[j][3]));
					//logger.info("in annual if rentAmt"+rentAmt);
				}//end of if
			}//end of for loop
		} catch (Exception e) {
			logger.error("error in calAnnualRentPaid "+e);
		}
		// rentAmt ==> the total rent paid in that financial Year
		//logger.info("rentAmt final"+rentAmt);
		return rentAmt;
	}//end of calAnnualRentPaid method

	/**
	 * this method id used to caclulate the rent amount by using the formula.
	 * @param empID
	 * @param formula--contains value from HRMS_TAX_PARAMETER table
	 * @return result
	 */
	private double getCondition1(String empID, String formula) {
		double result = 0;
		try {
			// formula===>> contains the formula from the HRMS_TAX_PARAMETERS...
			result = Utility.expressionEvaluate(new Utility().generateFormula(
					empID, formula, context, session));
		} catch (Exception e) {
			logger.error("error in getCondition1 "+e);
		}
		return result;
	}// end of getCondition1 method

	/**
	 * this method is used to calculate the value of in which month the employee has joined
	 * @param empJoinDate
	 * @param frmYear
	 * @param toYear
	 * @param tdsCal
	 * @return joinedMonth
	 */
	private String getEmpJoinMonth(String empJoinDate, String frmYear,
			String toYear, TdsCalculation tdsCal) {

		// empJoinDate====> in tis the value is taken from emp_regular_date from
		// the hrms_emp_offc table.
		String joinYear = empJoinDate;
		String empJoinYear = joinYear.substring(6, 10);
		String empFrmJoinMonth;
		String joinedMonth;
		double frmMonthToMinus = 9, toMonthToMinus = 3, empJoinedMonth = 0, value = 0, frmMonthToMinus1 = 12;
		int c = 5, value1 = 0;

		tdsCal.setEmpJoinYear(empJoinYear);
		if (joinYear.substring(3, 4).equals("0")) {
			empFrmJoinMonth = joinYear.substring(4, 5);
			tdsCal.setEmpJoinMon(empFrmJoinMonth);
		}//end of if
		else {
			empFrmJoinMonth = joinYear.substring(3, 5);
			tdsCal.setEmpJoinMon(empFrmJoinMonth);
		}//end of else

		// this condition is checked if the empJon year is the same as Financial
		// From Year and its between April to December
		if (frmYear.equals(empJoinYear)
				&& Double.parseDouble(empFrmJoinMonth) > 3) {
			joinedMonth = empFrmJoinMonth;
			tdsCal.setJoinedMonth(joinedMonth);
			empJoinedMonth = Double.parseDouble(joinedMonth);

			// this condition is checked if joined month is in between April to
			// September
			if (frmMonthToMinus >= Double.parseDouble(joinedMonth)) {
				value = frmMonthToMinus - empJoinedMonth;
				/**
				 * value==> we get the value of month..for e.g if he has joined
				 *	 in September, than from April to September
				 *	 it is 6 months. So that 6 months no TDS is calculated.
				 *	 in this loop total conditions from April to September is 6
				 *	 times
				 */
				for (int i = 0; i < 6; i++) {
					if (i == value) {
						value1 = c;
					}//end of nested if
					else {
						c--;
					}//end of nested else
				}//end of for loop
			}//end of nested if

			// this condition is checked if joined month is in between October
			// to December
			else {
				if (frmMonthToMinus1 >= Double.parseDouble(joinedMonth)) {
					value = frmMonthToMinus1 - empJoinedMonth;
					for (int i = 0; i < 3; i++) {
						if (i == value) {
							value1 = c + 3;
						}//end of nested if
						else {
							c--;
						}//end of nested else
					}//end of nested for loop
				}//end of nested
			}//end of else
			joinedMonth = String.valueOf(value1);
		}//end of if

		// this condition is checked if the empJoin year is the same as
		// Financial To Year and its between Janvary to March
		else if (toYear.equals(empJoinYear)
				&& Double.parseDouble(empFrmJoinMonth) <= 3) {
			joinedMonth = empFrmJoinMonth;
			tdsCal.setJoinedMonth(joinedMonth);
			empJoinedMonth = Double.parseDouble(joinedMonth);
			if (toMonthToMinus >= Double.parseDouble(joinedMonth)) {
				value = toMonthToMinus - empJoinedMonth;
				for (int i = 0; i < 3; i++) {
					if (i == value) {
						value1 = c + 6;
					}//end of nested if
					else {
						c--;
					}//end of nested else
				}//end of nested for loop
			}//end of nested if
			joinedMonth = String.valueOf(value1);
		} //end of else if
		else {
			joinedMonth = "0";
		}//end of else
		return joinedMonth;
	}//end of getEmpJoinMonth method

/**methods for TDS Calculation
 * @param currentMonth 
 * 
 */
	public String[][] getRecord(TdsCalculation tdsCal,HttpServletRequest request) {

		String frmYear = tdsCal.getFromYear();
		String toYear = tdsCal.getToYear();
		double totInvestmentAmt = 0.0;
		int empAge = 0,currentMonth=0;
		double totalTax = 0.0;
		double taxableIncome = 0;
		double hraAmt = 0, ptaxMonTotAmt = 0;
		double rentAmt = 0;
		int monthCnt = 12;
		int forPreviousYear = 0;
		Object[][] processData1=null,processData=null,empData = null;

		try {
			try{
				String sql = " SELECT  HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN , "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"
						+ "  HRMS_EMP_OFFC.EMP_ID,EMP_GENDER,TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(EMP_DOB,'YYYY'),HRMS_CENTER.CENTER_ID,"
						+ " HRMS_LOCATION.LOCATION_PARENT_CODE,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TYPE_PT,TYPE_PF,TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC  "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
						+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION)"
						+ " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) "
						+ " WHERE EMP_STATUS IN('S','N') AND EMP_REGULAR_DATE < TO_DATE('01-04-"
						+ toYear + "','DD-MM-YYYY') AND EMP_CENTER=" + tdsCal.getBranchCode()+" AND EMP_DIV=" + tdsCal.getDivisionCode()
						+ " AND (EMP_LEAVE_DATE > TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL)" 
						+ "order by UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";
	
				empData = getSqlModel().getSingleResult(sql);
			} catch (Exception e) {
				logger.error("exception in empData",e);
			} //end of catch
			
			String[][] total = new String[empData.length][24];
			String[][] totalCheck = new String[1][1];
			// this method is to retrieve all the data from the
			// HRMS_TAX_PARAMETER
			Object[][] taxParameters = null;
			try {
				taxParameters = getTaxParameters(tdsCal);
			} catch (Exception e) {
				logger.error("exception in taxParameters",e);
			} //end of catch
			Object[][] branchMetroFlag = null;
			try{
				String branchQuery = "SELECT CENTER_ISMETRO FROM HRMS_CENTER WHERE CENTER_ID = "+tdsCal.getBranchCode()+"";
				branchMetroFlag = getSqlModel().getSingleResult(branchQuery);
			}catch (Exception e) {
				logger.error("exception in branch metor query",e);
			} //end of catch
			
			if(branchMetroFlag == null){
				
			} //end of if
			else if(branchMetroFlag.length == 0){
				
			} //end of else if
			else{
				/**
				 * this if condition is check whether the branch selected is Metro or Non-Metro.
				 * If it is Metro i.e if its "Y" then formula is changed and the Metro formula is
				 * gets updated on the 6th position of the Object taxParmateres.....
				 */
				if(String.valueOf(branchMetroFlag[0][0]).equals("Y")){
					taxParameters[0][6] = taxParameters[0][15];
					logger.info("taxParameters[0][6] HRA condition 1 "+taxParameters[0][6]);
				} //end of if
			} //end of else
			
			/**
			 * this if condition is checked wether employees for selected criteria is present or not
			 */
			if (!(empData.length > 0)) {
				totalCheck[0][0] = "2";
				return totalCheck;
			}//end of if
			if (!(taxParameters.length > 0)) {//this if condition is checked whether the values in Tds Parameters present or not
				totalCheck[0][0] = "1";
				return totalCheck;
			}//end of if

			tdsCal.setTaxParaDebitCode(String.valueOf(taxParameters[0][0]));// //use for save..
			Object[][] seniorSlab = null;
			try {
				seniorSlab = getTaxSlab(frmYear, toYear, "S");//to get slabs from the HRMS_TAX_SLAB for Seniors
			} catch (Exception e) {
				logger.error("exception in seniorSlab",e);
			} //end of catch
			
			Object[][] menSlab = null;
			try {
				menSlab = getTaxSlab(frmYear, toYear, "M");//to get slabs from the HRMS_TAX_SLAB for Men
			} catch (Exception e) {
				logger.error("exception in menSlab",e);
			} //end of catch
			
			Object[][] womenSlab = null;
			try {
				womenSlab = getTaxSlab(frmYear, toYear, "F");//to get slabs from the HRMS_TAX_SLAB for Women and Other
			} catch (Exception e) {
				logger.error("exception in womenSlab",e);
			} //end of catch
			// this method is to retrieve all the data from the HRMS_PTAX_HD Rand DTL
			Object pTaxData[][] = null;
			try {
				pTaxData = getPtaxData();
			} catch (Exception e) {
				logger.error("exception in pTaxData",e);
			} //end of catch
			currentMonth = Integer.parseInt(tdsCal.getCurrentMonth());//to get the current month...is get from the system date.
			int salProcessCount=0;
			logger.info("tdsCal.getCurrentYear()   "+tdsCal.getCurrentYear());
			logger.info("frmYear   "+frmYear);
			logger.info("toYear   "+toYear);
			logger.info("currentMonth   "+currentMonth);
			
			/**
			 * this if and else if conditions are to check if the calculation is for the Previous/Current/Further years
			 */
			try {
				/**
				 * This condition is check for previous year...if the current month is between Jan to March
				 * then the Current Year is minus by 1....
				 */
				if(currentMonth==1 || currentMonth==2 || currentMonth==3){
					forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear()) - 1;
				} //end of if
				else{
					forPreviousYear = Integer.parseInt(tdsCal.getCurrentYear());
				} //end of else
				
				logger.info("forPreviousYear   "+forPreviousYear);
				/**
				 * in this if condition it is checked for the previous year
				 */
				if(Integer.parseInt(frmYear) < forPreviousYear){
					logger.info("in previous year condition");
					try {
						/**
						 * this processData contains the Ledger codes of the processed salary
						 * in processData----it is checked for April to December
						 * in processData1----it is checked forr Jan To March
						 */
						processData = retProcessData(4, 12, frmYear, tdsCal);
					} catch (Exception e) {
						logger.error("exception in processData",e);
					} //end of catch
					
					try {
						processData1 = retProcessData(1, 3, toYear, tdsCal);
					} catch (Exception e) {
						logger.error("exception in processData1",e);
					} //end of catch
					/**
					 * Here monthCnt is 0 as for the previous year all the 12 months salary will be 
					 * processed, so there is no need to get data from the Configuration. hence the 
					 * Configuration data is multiplied by 0.
					 */
					monthCnt=0;
				} //end of if
				/**
				 * this else if condition is checked for Tds Calculation for the future year
				 */
				else if(Integer.parseInt(frmYear) > Integer.parseInt(tdsCal.getCurrentYear())
						&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
					logger.info("in future year condition");
					/**
					 * here monthCnt is straight away 12 as for the future calculation all the data from
					 * the Configuration will be multiplied by 12 for full Financial Year.
					 */
					monthCnt=12;
				} //end of else if
				else{//this else is for Tds Calculation for Current Year
					logger.info("in current year condition");
					/**
					 * in this if it is checked wether the currentMonth lies in between April to December
					 */
					if(currentMonth>=4 && currentMonth<=12){
						// /to retrieve the Ledger code for the Processed Salary for months
						// April to December
							logger.info("in if of month check currentMonth=" +currentMonth);
							try {
								/**
								 * in this processData the Ledger Codes are retrieved from month of
								 * April to the currentMonth.... for e:g- currentMonth=November, then from
								 * April to Nov
								 */
								processData = retProcessData(4, currentMonth,
										frmYear, tdsCal);
							} catch (Exception e) {
								logger.error("exception in processData",e);
							} //end of catch
							try {
								/**
								 * this salProcessCount contains the count of processed salary months.
								 * suppose if the salary is processed till october then the count will be 
								 * April to October i.e=7
								 */
								salProcessCount = getCurrentSalMonth(
										currentMonth, tdsCal, frmYear);
							} catch (Exception e) {
								logger.error("exception in salProcessCount",e);
							} //end of catch
						} //end of if
						/**
						 * this else is bcoz if currentMonth is in between Jan To march, then all
						 * the ledger codes of salary from April To December is retrieved.
						 */
						else{
							try {
								processData = retProcessData(4, 12, frmYear,tdsCal);
							} catch (Exception e) {
								logger.error("exception in processData",e);
							} //end of catch
						} //end of else
						if(!(currentMonth>=4 && currentMonth<=12)){//if currentMonth between Jan To March 
							// /to retrieve the Ledger code for the Processed Salary for months
							// January to March]
							logger.info("in if of month check currentMonth=" +currentMonth);
							try {
								processData1 = retProcessData(1, currentMonth,toYear, tdsCal);
							} catch (Exception e) {
								logger.error("exception in processData1",e);
							} //end of catch
							
							try {
								/**
								 * suppose currentMonth=Feb and Feb salary is processed then the value of
								 * salProcessCount will be 11 from April To Feb
								 */
								salProcessCount = getCurrentSalMonth(currentMonth, tdsCal, toYear);
							} catch (Exception e) {
								logger.error("exception in salProcessCount",e);
							} //end of catch
						} //end of if
						/**
						 * here the initial value of monthCnt=12, then the monthCnt is substracted from the
						 * salProcessCount to get the remaining months which are not processed.
						 */
						monthCnt = monthCnt - salProcessCount;
				}
			} catch (Exception e) {
				logger.error("exception in getting processData, processData1",e);
			} //end of catch
			
			// this method is to retrieve all the data from the FROM
			// HRMS_HOUSERENT_HDR and DTL
			Object[][] houserentdata = getRentData(frmYear,toYear);
			Object[][] pfData=null;
			try {
				// Query to retrieve PF Data from Pf Parameter/
				String pfQuery = "SELECT PF_FORMULA,PF_PERCENTAGE FROM HRMS_PF_CONF "
						+ " WHERE PF_CODE = (SELECT MAX(PF_CODE)FROM HRMS_PF_CONF)";
				pfData = getSqlModel().getSingleResult(pfQuery);
			} catch (Exception e) {
				logger.error("exception in creating pfData"+e);
			} //end of catch
			/**
			 * Delete Queries to empty the Temporary  tables
			 */
			try {
				String delSalTemp = "DELETE FROM HRMS_TDS_SALARY_TEMP";
				String delTemp = "DELETE FROM HRMS_TDS_TEMP";
				String delPerq = "DELETE FROM HRMS_TDS_PERQ";
				getSqlModel().singleExecute(delSalTemp);
				getSqlModel().singleExecute(delTemp);
				getSqlModel().singleExecute(delPerq);
			} catch (Exception e) {
				logger.error("exception in deleting temp records"+e);
			} //end of catch
			double row1 = 0.0, pfFinalAmt = 0,perqTotalAmt=0;

			int count = 0;
			int cnt = 0;
			int countSalTemp = 0,countPerqTemp=0;
			Object[][] salaryTemp = null;
			Object[][] perquisiteTemp = null;
			
			Object[][] tdsTemp = null;
			Object[][] objSalTemp=null;
			Object[][] objPerqTemp=null;
			
			try {
				/**
				 * Method to create Object to insert Data in Salary Temp Table. Here the codes are retrieved
				 * to set the length of the saltemp Object.
				 */
				String createObjSalTemp = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
						+ " WHERE  CREDIT_TAXABLE_FLAG = 'Y'";
				objSalTemp = getSqlModel().getSingleResult(createObjSalTemp);
			} catch (Exception e) {
				logger.error("exception in creating objSalTemp "+e);
			} //end of catch
			
			try {
				/**
				 * Method to create Object to insert Data in Salary Temp Table.Here the codes are retrieved
				 * to set the length of the perquisites Object.
				 */
				String createObjPerqTemp = "SELECT HRMS_PERQUISITE_HEAD.PERQ_CODE FROM HRMS_PERQUISITE_HEAD";
				objPerqTemp = getSqlModel().getSingleResult(createObjPerqTemp);
			} catch (Exception e) {
				logger.error("exception in creating objSalTemp "+e);
			} //end of catch
			
			/**
			 * Object salaryTemp created to Batch Insert. Here the total credit codes is multiplied by the
			 * number of Employees.. bcoz for each employee the credits will be same
			 */
			
			salaryTemp = new String[objSalTemp.length * empData.length][5];
			
			perquisiteTemp = new String[objPerqTemp.length * empData.length][5];// //Object perquisiteTemp created to Batch Insert.
			
			tdsTemp = new String[empData.length][5];// /Object created to add Data in HRMS_TDS_TEMP

		try{
			for (int i = 0; i < empData.length; i++) { //here for loop is started for employee wise calculation
				Object income[][] =null;//loop "z"
				Object[][] creditPf = null;
				Object perquisite[][] =null;
				count++;

				/**
				 *this moethod is called to calculate the Employee joinedMonth....if he has joined before
				* Financial year from the joinedMonth is "0". Suppose employee is joined in the month of May
				* and in the same Financial year then the value of joinedMonth=1.
				 */
				String joinedMonth = getEmpJoinMonth(String
						.valueOf(empData[i][10]), frmYear, toYear, tdsCal);
				logger.info("joinedMonth    :"+joinedMonth);
				

				/**
				 *this moethod is called to calculate the Employee resigned month..
				 * Suppose employee is resigned in the month of May
				 * and in the same Financial year then the value of resignedMonth=11.
				 */
				double resignedMonth = 0;
				try {
					if (String.valueOf(empData[i][13]).equals("null")) {
						resignedMonth = 0;
					} //end of if
					else {
						resignedMonth = Double
								.parseDouble(getEmpResignMonth(String
										.valueOf(empData[i][13])));
					}
				} catch (Exception e) {
					logger.error("exception in resignedMonth",e);
				} //end of catch
				logger.info("resignedMonth=======:"+resignedMonth);
				double hraFinalAmt = 0;
				totalTax = 0.0;
				rentAmt = 0;
				total[i][0] = String.valueOf(empData[i][0]);//emp_id
				total[i][1] = String.valueOf(empData[i][1]);//emp_token
				total[i][2] = String.valueOf(empData[i][2]);//emp_name

				tdsCal.setCenterId(String.valueOf(empData[i][9]));///location parent code
				
				int joinedYear = Integer.parseInt(String.valueOf(empData[i][10]).substring(6,10));
				//this join month is employee joining date from hrms_emp_offc
				int empJoinedMonth = Integer.parseInt(String.valueOf(empData[i][10]).substring(3,5));

				try {
					/**
					 * Income Calculation
					 */
					income = getIncomeChanged(frmYear, toYear, tdsCal, String
							.valueOf(empData[i][0]), String
							.valueOf(empData[i][9]), pTaxData, processData,
							processData1, taxParameters, joinedMonth,
							currentMonth,monthCnt,joinedYear,empJoinedMonth,forPreviousYear,resignedMonth); // //calculation for Income
				} catch (Exception e) {
					logger.error("exception in income object",e);
				} //end of catch
				try {
					/**
					 * Method to add data in Salary Temp Table
					 */
					if (income == null) {

					} //end of if
					else if (income.length == 0) {

					} //end of else if
					else {
						logger.info("income.length in else   :" + income.length);
						creditPf = new String[income.length][2];
						try {
							for (int k = 0; k < income.length; k++) {
								salaryTemp[countSalTemp][0] = String
										.valueOf(empData[i][0]);
								salaryTemp[countSalTemp][1] = String
										.valueOf(income[k][1]);//credit Amt
								salaryTemp[countSalTemp][2] = String
										.valueOf(income[k][0]);//credit code
								salaryTemp[countSalTemp][3] = String
										.valueOf(frmYear);
								salaryTemp[countSalTemp][4] = String
										.valueOf(toYear);
								//logger.info("salaryTemp[countSalTemp][0]    :"+ salaryTemp[countSalTemp][0]);
								countSalTemp++;
								creditPf[k][0] = String.valueOf(income[k][0]);
								creditPf[k][1] = String.valueOf(income[k][1]);
								//logger.info("creditPf[k][0]    :"+ creditPf[k][0]);
								//logger.info("creditPf[k][1]    :"+ creditPf[k][1]);
								
							}//end of for loop
						} catch (Exception e) {
							logger.error("exception in income loop",e);
						} //end of catch
					} //end of else 
				} catch (Exception e) {
					logger.error("exception in creating salaryTemp object"+e);
				} //end of catch
				cnt++;
				
				try {
					/**
					 * calculation to add perquisite amount in gross income...
					 * Perq Object is created same as credit object 
					 */
					perquisite = getPerqData(String.valueOf(empData[i][0]));
				} catch (Exception e) {
					logger.error("exception in perquisite object",e);
				} //end of catch
				perqTotalAmt=0;
				
				if (perquisite == null) {

				} //end of if
				else if (perquisite.length == 0) {

				} //end of else if
				else {
					try{
						for (int j = 0; j < perquisite.length; j++) {
							perquisiteTemp[countPerqTemp][0] = String
							.valueOf(empData[i][0]);
							if(String.valueOf(perquisite[j][3]).trim().equals("M")){
								perquisiteTemp[countPerqTemp][1] = String.valueOf(Double.parseDouble(String
								.valueOf(perquisite[j][1]))*(12.0 - Double.parseDouble(joinedMonth) - resignedMonth));//perq Amt
							} //end of if
							else if(String.valueOf(perquisite[j][3]).trim().equals("H")){
								perquisiteTemp[countPerqTemp][1] = String.valueOf(((Double.parseDouble(String
										.valueOf(perquisite[j][1])))/6.0) * (12.0 - Double.parseDouble(joinedMonth)- resignedMonth));//perq Amt
							} //end of else if
							else if(String.valueOf(perquisite[j][3]).trim().equals("Q")){
								perquisiteTemp[countPerqTemp][1] = String.valueOf(((Double.parseDouble(String
										.valueOf(perquisite[j][1])))/3.0) * (12.0 - Double.parseDouble(joinedMonth)- resignedMonth));//perq Amt
							} //end of else if
							else if(String.valueOf(perquisite[j][3]).trim().equals("A")){
								perquisiteTemp[countPerqTemp][1] = String.valueOf(((Double.parseDouble(String
										.valueOf(perquisite[j][1])))/12.0) * (12.0 - Double.parseDouble(joinedMonth)- resignedMonth));//perq Amt
							} //end of else if
							perquisiteTemp[countPerqTemp][2] = String
									.valueOf(perquisite[j][0]);//perq code
							perquisiteTemp[countPerqTemp][3] = String
									.valueOf(frmYear);
							perquisiteTemp[countPerqTemp][4] = String
									.valueOf(toYear);
							perqTotalAmt += Double.parseDouble(String.valueOf(perquisiteTemp[countPerqTemp][1]));
							
							countPerqTemp++;
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in perquisite loop"+e);
					} //end of catch
				} //end of else
				

				try {
					/**
					 * method to calculate PF
					 */
					//logger.info("pfData[0][0]   :"+pfData[0][0]);
					pfFinalAmt=0;
					if(String.valueOf(empData[i][12]).equals("N")){
						pfFinalAmt=0;
					} //end of if
					else{
						if (pfData == null) {

						}//end of if
						else if (pfData.length == 0) {

						} //end of else if
						else {
							logger.info("in pf data else  :");
							if (creditPf == null) {
								
							} //end of if
							else if (creditPf.length == 0) {

							} //end of else if
							else {
								pfFinalAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(
														creditPf,String.valueOf(pfData[0][0]),context, session));
							} //end of else

							logger.info("pfFinalAmt "+empData[i][2]+"   :" + pfFinalAmt);
							logger.info("pfData[0][1]    :" + pfData[0][1]);
							pfFinalAmt = pfFinalAmt* (Double.parseDouble(String.valueOf(pfData[0][1])) / 100);
						} //end of else
					} //end of else
					
				} catch (Exception e) {
					logger.error("exception in calculating pfFinalAmt"+e);
				} //end of catch
				//logger.info("pfFinalAmt after   :"+pfFinalAmt);
				/**
				 * to calculate total sum of income
				 */
				double totalIncome=0;
				if(income == null){
					
				} //end of if
				else if(income.length == 0){
					
				} //end of else if
				else{
					try {
						totalIncome = getSumIncome(income, tdsCal);
					} catch (Exception e) {
						logger.error("exception in calculating totalIncome"+e);
					} //end of catch
				} //end of else
				logger.info("totalIncome   :"+totalIncome);
				logger.info("perqTotalAmt   :"+perqTotalAmt);
				
				Object totalInvest[][] = null;
				try {
					totalInvest = getTotalInvestment(String
							.valueOf(empData[i][0]), frmYear, toYear);
				} catch (Exception e) {
					logger.error("exception in creating totalInvest object"+e);
				} //end of catch
				
				Object debittotalInv[][]=null;
				try {
					debittotalInv = getDebInvAddChap(String
							.valueOf(empData[i][0]));
				} catch (Exception e) {
					logger.error("exception in creating debittotalInv object"+e);
				} //end of catch
				
				/**
				 * THIS METHOD CREATED ON 7/02/09 TO ADD SALARY DEBITS IN RESPECTIVE INVESTMENTS CHAP
				 */
				debittotalInv = getDebits(String.valueOf(empData[i][0]),processData,processData1,frmYear
						,toYear,debittotalInv);
				
				
				Object other[][] = getInvestments(totalInvest, "OTHER","N");
				double otherAmt = getInvAmt(other, taxParameters);
				//double debitOther = getDebitInvestments(debittotalInv, "OTHER");
				total[i][5] = String.valueOf(Math.round(otherAmt));
				double finalIncome = 0;
				finalIncome = Math.round(totalIncome + perqTotalAmt + otherAmt);
				total[i][3] = String.valueOf(Math.round(finalIncome));

				// this Object totalInvest contains all the investments of that
				// particular Employee for that financial Year
				// FROM HRMS_TAX_INVESTMENT table.
				
				
				// this Object debittotalInv contains the Debit Amount for all
				// the Investments Chapter for
				// that Particular Employee
				
				try {
					// to calculate annual rent paid
					if (houserentdata == null) {

					} //end of if
					else if (houserentdata.length == 0) {

					} //end of else if
					else {
						try {
							rentAmt = calAnnualRentPaid(houserentdata, String
									.valueOf(empData[i][0]), frmYear, toYear);
						} catch (Exception e) {
							logger.error("exception in rentAmt",e);
						} //end of catch
					} //end of else
					//logger.info("rentAmt     :"+rentAmt);
				} catch (Exception e) {
					logger.error("exception in calculating rentAmt"+e);
				} //end of catch
				
				// /to get the final Hra amount
				if(income == null){
					
				} //end of if
				else if(income.length == 0){
					
				} //end of else if
				else{
					try {
						hraFinalAmt = getHraCondAmt(income, taxParameters, String
								.valueOf(empData[i][0]), rentAmt, finalIncome,tdsCal, joinedMonth);
					} catch (Exception e) {
						logger.error("eception in hraFinalAmt",e);
					} //end of catch
				} //end of else
				logger.info("hraFinalAmt     :"+hraFinalAmt);
				if (hraFinalAmt < 0)
					hraFinalAmt = 0;

				/**
				 * In Object tdsTemp values is stored to batch Insert in HRMS_TDS_TEMP
				 */
				tdsTemp[i][0] = String.valueOf(empData[i][0]);
				tdsTemp[i][1] = frmYear;
				tdsTemp[i][2] = toYear;
				tdsTemp[i][3] = String.valueOf(hraFinalAmt);
				tdsTemp[i][4] = String.valueOf(pfFinalAmt);

				/**
				 * methods to calculate and get list of all the Investment Chapters....
				 */
				Object exempt[][]=null;
				double exemptAmt =0,debitexempt =0;
				if(totalInvest == null){
					
				} //end of if
				else if(totalInvest.length == 0){
					
				} //end of else if
				else{
					try {
						exempt = getInvestments(totalInvest, "EXEMPT","N");
						exemptAmt = getInvAmt(exempt, taxParameters);
					} catch (Exception e) {
						logger.error("exception in creating exempt"+e);
					} //end of catch
				} //end of else
				
				// in this total Exempt amount is calculated
				
				if(debittotalInv == null){
					 
				} //end of if
				else if(debittotalInv.length == 0){
					 
				} //end of else if
				else{
					try {
						debitexempt = getDebitInvestments(debittotalInv,"EXEMPT");
					} catch (Exception e) {
						logger.error("exception in calculating debitexempt"+e);
					} //end of catch
				} //end of else
				
				exemptAmt = exemptAmt + Math.round(hraFinalAmt);
				// HRA amount is added in the Exempt Chapter

				total[i][4] = String.valueOf(Math.round(exemptAmt + debitexempt));
				Object via[][] = null;
				try {
					via = getInvestments(totalInvest, "VI-A","N");
					//via = Utility.joinArrays(via, getInvestments(totalInvest, "VI","N"), 0, 0);
				} catch (Exception e) {
					logger.error("exception in via object",e);
				} //end of catch
				
				try {
					if (via != null && via.length > 0) {
						via = Utility.joinArrays(via, getInvestments(totalInvest, "VI","N"),1, 0);
					} else {
						via = getInvestments(totalInvest, "VI","N");
					}
				} catch (Exception e) {
					logger.error("exception in getInvestments jopin array"+e);
				}
				double viaAmt = getInvAmt(via, taxParameters);
				double debitVia = getDebitInvestments(debittotalInv, "VI-A");
				//logger.info("debitVia amount     :"+ Math.round(debitVia));
				//logger.info("viaAmt amount     :"+Math.round(viaAmt));
				//logger.info("pfFinalAmt amount     :"+Math.round(pfFinalAmt));
				viaAmt = Math.round(viaAmt);
				total[i][6] = String.valueOf(Math.round(viaAmt + Math.round(debitVia)));
				//logger.info("total[i][6] amount     :"+total[i][6]);
				
				Object rebate[][] = null;
				try {
					rebate = getInvestments(totalInvest, "VI","Y");
				} catch (Exception e) {
					logger.error("exception in rebate",e);
				} //end of catch
				double rebateAmt = getInvAmt(rebate, taxParameters);
				double debitRebate = getDebitInvestments(debittotalInv, "VI");
				logger.info("debitRebate   :"+debitRebate);
				/**
				 * this if is to check the Rebate Investment Limit...the Rebate amt should not Exceed
				 * Rebate Inv Limit.
				 */
				if (!(taxParameters[0][13] == null
						|| taxParameters[0][13].equals("") || taxParameters[0][13]
						.equals("null"))) {

					if (Double
							.parseDouble(String.valueOf(taxParameters[0][13])) > (rebateAmt + debitRebate + Math.round(pfFinalAmt))) {
						rebateAmt = rebateAmt + debitRebate + Math.round(pfFinalAmt);
						debitRebate = 0;
						total[i][7] = String.valueOf(Math.round(rebateAmt));
					}//end of nested if
					else {
						rebateAmt = Double.parseDouble(String.valueOf(taxParameters[0][13]));
						debitRebate = 0;
						total[i][7] = String.valueOf(Math.round(rebateAmt));
					} //end of nested else
				} //end of if
				else {
					total[i][7] = String.valueOf(Math.round(rebateAmt + debitRebate));
					debitRebate = 0;
				} //end of else


				double ptax = 0;
				int varMth = 0;
				// empData[i][9]==>>it contains Location Parent Code
				logger.info("tdsCal.getJoinedMonth()    :"+tdsCal.getJoinedMonth());
				if (String.valueOf(empData[i][9]).equals("")
						|| String.valueOf(empData[i][9]).equals("null")) {
					ptax = 0;
				}//end of if
				else
					// Ptax amount is retrieved by passing the totalGross
					ptaxMonTotAmt = Double.parseDouble(String.valueOf(tdsCal
							.getPtaxMonthTotGross()));
				/**
				 * this if is checked if for the employee type in the salary conf the Ptax is No, then
				 * no pTax is calculated
				 */
				if(String.valueOf(empData[i][11]).equals("N")){
					ptax=0;
				} //end of if
				else{//this else is for if emp_type PT is Yes
					ptax = getEmpPtax(String.valueOf(empData[i][9]), pTaxData,
							ptaxMonTotAmt,tdsCal,joinedMonth,tdsCal.getJoinedMonth());
					logger.info("value of pTax from slab  :"+ptax);
					if(!(tdsCal.getPtaxVarMth().equals("") || tdsCal.getPtaxVarMth().equals("null")))
						varMth = Integer.parseInt((tdsCal.getPtaxVarMth()));
					logger.info("varMth      :"+varMth);
				} //end of else
				// /this monthCount is set in the getIncome method, it sets the
				// Process month Count
				double month = Double.parseDouble(tdsCal.getMonthCount());
				logger.info("month===>"+month);
				// this monthTotAmt is set in getIncome method.. from sal_debits
				// in that process month.
				//this month is minus from the joined month for new Joinee Employee if their joining is in between Financial Year
				/**
				 * changed on 19 nov checked previous/current/future year.....
				 */
				
				if(Integer.parseInt(frmYear) < forPreviousYear){
					ptax = Double.parseDouble((tdsCal.getMonthTotAmt()));
					logger.info("ptax in previous year===>"+ptax);
				}// end of if for previous financial year Ptax
				else if(Integer.parseInt(frmYear) > Integer.parseInt(tdsCal.getCurrentYear())
						&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
					/**
					 * this if is checked if for the employee type in the salary conf the Ptax is No, then
					 * no pTax is calculated
					 */
					logger.info("ptax in future year===>");
					if(String.valueOf(empData[i][11]).equals("N")){
						ptax=0;
					} //end of if
					/**
					 * this else is for if emp_type PT is Yes
					 */
					else{
						if(!(tdsCal.getPtaxVarAmt().trim().equals(""))){
							month = month - 1;
							ptax = (ptax * month)+ Double.parseDouble(tdsCal.getPtaxVarAmt());
						}// end of else if for future financial year Ptax
						else{
							ptax = (ptax * month);
						} //end of else
					} //end of else
					
				} //end of else if
				else{//pTax for current year
					/**
					 * this if is checked if for the employee type in the salary conf the Ptax is No, then
					 * no pTax is calculated
					 */
					//logger.info("in ptax for current year");
					
					if(month > 0){
						
					
					logger.info("ptax in current year==>");
					if(String.valueOf(empData[i][11]).equals("N")){
						logger.info("in if empData[i][11]zone config  :"+empData[i][11]);
					} //end of if
					else{ //his else is for if emp_type PT is Yes
						if(tdsCal.getPtaxVarMth().equals("1")){
							tdsCal.setPtaxVarMth("13");//for jan
						} //end of if
						else if(tdsCal.getPtaxVarMth().equals("2")){
							tdsCal.setPtaxVarMth("14");//for feb
						} //end of else if
						else if(tdsCal.getPtaxVarMth().equals("3")){
							tdsCal.setPtaxVarMth("15");//for march
						} //end of else if
						if((tdsCal.getPtaxVarAmt().trim().equals(""))){
							ptax = (ptax * month);
							logger.info("in if var amt is zero  :"+ptax);
						} //end of if
						
						else if(!(tdsCal.getPtaxVarMth().equals("") || tdsCal.getPtaxVarMth().equals("null"))){
								if(currentMonth<=Integer.parseInt(tdsCal.getPtaxVarMth())){
								logger.info("1  :"+tdsCal.getPtaxVarMth());
								if(!(tdsCal.getCheckCurrentProcess().equals(""))){ 
									logger.info("2  :"+tdsCal.getCheckCurrentProcess());
									month = month - 1;
									ptax = (ptax * month) + Double.parseDouble(tdsCal.getPtaxVarAmt());
									logger.info("3  :"+ptax);
							} //end of if
							else{
								if(varMth==13){
									varMth=1;
								} //end of if
								else if(varMth==14){
									varMth=2;
								} //end of else if
								else if(varMth==15){
									varMth=3;
								} //end of if
								if(currentMonth==varMth){
									logger.info("4  :"+month);
									month = month - 1;
									logger.info("5  :"+tdsCal.getPtaxVarAmt());
									ptax = (ptax * month)+ Double.parseDouble(tdsCal.getPtaxVarAmt());
									logger.info("6  :"+ptax);
								} //end of if
								else{
									logger.info("7  :"+month);
									month = month - 1;
									ptax = (ptax * month)+ Double.parseDouble(tdsCal.getPtaxVarAmt());
									logger.info("8   :"+ptax);
								} //end of else
								
							} //end of else
						} //end of if
						} //end of else if
						else{
							ptax = (ptax * month);
							logger.info("in else 12222  :"+ptax);
						} //end of else
					} //end of else
					logger.info("tdsCal.getMonthTotAmt() to be add in ptax :"+tdsCal.getMonthTotAmt());
					ptax = ptax	+ Double.parseDouble((tdsCal.getMonthTotAmt()));
					logger.info("final  ptax :"+ptax);
					} //end of if
					else{
						ptax = Double.parseDouble((tdsCal.getMonthTotAmt()));
					} //end of else
				} //end of else
				
				/**
				 * Objects for getting Ptax Arrears
				 */
				Object arrearsAprToDecDebit[][] = null;
				try {
					arrearsAprToDecDebit = getArrearsDebitAmt(4, 12, String
							.valueOf(empData[i][0]), frmYear, tdsCal.getDebPtaxCode());///to get frmYear arrears
				} catch (Exception e) {
					logger.error("exception in arrearsAprToDecDebit",e);
				} //end of catch
				
				Object arrearsJanToMarDebit[][] = null;
				try {
					arrearsJanToMarDebit = getArrearsDebitAmt(1, 3, String
							.valueOf(empData[i][0]), toYear, tdsCal.getDebPtaxCode());//to get toMonth arrears
				} catch (Exception e) {
					logger.error("exception in arrearsJanToMarDebit");
				} //end of catch
				
				/**
				 * to get previous year arrears which is received in next year
				 */
				Object preMonthArrearsDebit[][] = null;
				try {
					preMonthArrearsDebit = getPrevArrearsDebit(4, 12, String
							.valueOf(empData[i][0]), frmYear, toYear, tdsCal.getDebPtaxCode());
				} catch (Exception e) {
					logger.error("exception in preMonthArrearsDebit",e);
				} //end of catch
				double ptaxArrearAmt = 0;
				
				if(arrearsAprToDecDebit == null){
					
				} //end of if
				else if(arrearsAprToDecDebit.length == 0){
					
				} //end of else if
				else{
					try {
						for (int j = 0; j < arrearsAprToDecDebit.length; j++) {
							ptaxArrearAmt = ptaxArrearAmt+ Double.parseDouble(String.valueOf(arrearsAprToDecDebit[j][1]));
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in ptaxArrearAmt for arrearsAprToDecDebit",e);
					} //end of catch
				} //end of else
				
				if(arrearsJanToMarDebit == null){
					
				} //end of if
				else if(arrearsJanToMarDebit.length == 0){
					
				} //end of else if
				else{
					try {
						for (int j = 0; j < arrearsJanToMarDebit.length; j++) {
							ptaxArrearAmt = ptaxArrearAmt+ Double.parseDouble(String.valueOf(arrearsJanToMarDebit[j][1]));
						} //end of loop
					} catch (Exception e) {
						logger.error("eception in ptaxArrearAmt for arrearsJanToMarDebit",e);
					} //end of catch
				} //end of else
				
				if(preMonthArrearsDebit == null){
					
				} //end of if
				else if(preMonthArrearsDebit.length == 0){
					
				} //end of else if
				else{
					try {
						for (int j = 0; j < preMonthArrearsDebit.length; j++) {
							ptaxArrearAmt = ptaxArrearAmt+ Double.parseDouble(String.valueOf(preMonthArrearsDebit[j][1]));
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in ptaxArrearAmt for preMonthArrearsDebit",e);
					}  //end of catch
				} //end of else
				logger.info("---------------------------------prtax"+ptax);
				logger.info("---------------------------------ptaxArrearAmt"+ptaxArrearAmt);
				total[i][8] = String.valueOf(Math.round(ptax + ptaxArrearAmt));

				// in this the totalInvestments amt is calulated.
				totInvestmentAmt = exemptAmt + viaAmt + rebateAmt
						+ debitexempt + debitVia + debitRebate
						+ ptax+ptaxArrearAmt;
				
				
				// this condition is check if Taxable Income is less than zero
				// tha it is set to 0.
				if (finalIncome - totInvestmentAmt > 0) {
					logger.info("finalIncome   :"+finalIncome);
					logger.info("totInvestmentAmt   :"+totInvestmentAmt);
					taxableIncome = finalIncome - totInvestmentAmt;
					
					logger.info("taxableIncome   :"+taxableIncome);
					/**
					 * this method is for next round of 10 for e.g- 61 it will be 70
					 */
					double z = taxableIncome;
					double mod=taxableIncome%10;
					if(mod >0)
					{
						z=(z-mod)+10;
					} //end of if
					
					if(z<0)
					{
						logger.info("in ifc z   :");
						total[i][9]=String.valueOf("0");
					} //end of if
					else
						logger.info("z in else   :"+z);
						total[i][9]=String.valueOf(Math.round(z));
						taxableIncome = z;
					//total[i][9] = Utility.twoDecimals(String.valueOf(taxableIncome));
				} //end of if
				else {
					taxableIncome = 0;
					total[i][9] = String.valueOf(0);
				} //end of else

				total[i][10] = "0";

				String empAge1 = String.valueOf(empData[i][7]);
				if (empAge1.equals("") || empAge1.equals("null")) {
					empAge = 0;
				} //end of if
				else {
					empAge = Integer.parseInt(String.valueOf(empData[i][7]));
				} //end of else

				if (taxParameters == null) {
					taxParameters = new Object[1][1];
				} //end of if

				// /it checks by empAge..whether the employee is senior citizen
				if (empAge >= Integer.parseInt(String
						.valueOf(taxParameters[0][3]))) {
					totalTax = getTaxAmount(taxableIncome, seniorSlab);
					total[i][10] = String.valueOf(Math.round(totalTax));

				} //end of if
				else {
					if (String.valueOf(empData[i][6]).equals("M")) {
						totalTax = getTaxAmount(taxableIncome, menSlab);
						total[i][10] = String.valueOf(Math.round(totalTax));
					} //end of nested if
					else if (String.valueOf(empData[i][6]).equals("F")
							|| String.valueOf(empData[i][6]).equals("O")) {
						totalTax = getTaxAmount(taxableIncome, womenSlab);
						total[i][10] =String.valueOf(Math.round(totalTax));
					} //end of nested else if
				} //end of else

				// in this getsurcharge
				//logger.info("taxableIncome    :"+taxableIncome);
				//logger.info("Math.round(totalTax)    :"+Math.round(totalTax));
				double surcharge = getsurcharge(taxableIncome, Math
						.round(totalTax), taxParameters);
				//logger.info("surcharge    :"+surcharge);
				total[i][12] = String.valueOf(Math.round(surcharge));

				double educess = geteducess(Math.round(totalTax) + surcharge,
						taxParameters);
				total[i][11] = String.valueOf(Math.round(educess));

				double netTax = 0.0;
				netTax = Math.round(totalTax + educess + surcharge);
				total[i][13] = String.valueOf(Math.round(netTax));
				// tdsCal.getTaxPaid() is set in getIncome method.
				double taxPerMonth = 0.0;
				
				if(Integer.parseInt(frmYear) < forPreviousYear){
					tdsCal.setRemainMonths("1");
				} //end of if
				else if(Integer.parseInt(frmYear) > Integer.parseInt(tdsCal.getCurrentYear())
						&& Integer.parseInt(toYear) > Integer.parseInt(tdsCal.getCurrentYear())){
					tdsCal.setRemainMonths("12");
					logger.info("111111111111111");
					logger.info("tdsCal.getMonthCount() in else if"+tdsCal.getMonthCount());
				} //end of else if
				else{
					if(tdsCal.getMonthCount().equals("0")){
						tdsCal.setRemainMonths("1");
					}
					else{
						tdsCal.setRemainMonths(tdsCal.getMonthCount());
					}
				} //end of else
				
				double arrearsTaxPaid = 0;
				double taxPaid = 0;
				
				try {
					/**
					 * this is to calculate arreas tax paid for the financial year...
					 * (String.valueOf(empData[i][0])==empId
					 * taxParameters[0][0] = TDS Debit Code
					 */
					arrearsTaxPaid = getArrearsTaxPaid(String.valueOf(empData[i][0]), frmYear, toYear, String
							.valueOf(taxParameters[0][0]));
				} catch (Exception e) {
					logger.error("exception in arrearsTaxPaid",e);
				} //end of catch
				/**
				 * ADDED ON 2/02/2009...PI ALLOWANCE TAX AMOUNT IS ADDED IN THE TAX PAID
				 */
				Object[][]piAllowanceTaxPaid = null;
				
				try {
					piAllowanceTaxPaid = getPiAllowTaxPaid(String.valueOf(empData[i][0]), frmYear, toYear);
				} catch (Exception e) {
					logger.error("exception in piAllowanceTaxPaid",e);
				} //end of catch
				if(piAllowanceTaxPaid ==null){
					
				} //end of if
				else if(piAllowanceTaxPaid.length==0){
					
				} //end of else if
				else{
					taxPaid = Double.parseDouble(tdsCal.getTaxPaid()) + Double.parseDouble(String.valueOf(piAllowanceTaxPaid[0][0]));
					//logger.info("piAllowanceTaxPaid[0][0]   :"+piAllowanceTaxPaid[0][0]);
				} //end of else
				taxPaid = taxPaid + arrearsTaxPaid;
				logger.info("taxPaid   :"+taxPaid);
				tdsCal.setTaxPaid(String.valueOf(taxPaid));
				
				//logger.info("tdsCal.getTaxPaid()after piallowtax paid   :"+tdsCal.getTaxPaid());
				logger.info("final tdsCal.getRemainMonths()   :"+tdsCal.getRemainMonths());
				logger.info("netTax  :"+netTax);
				logger.info("tdsCal.getTaxPaid()  :"+tdsCal.getTaxPaid());
				taxPerMonth = (netTax - Double.parseDouble(tdsCal.getTaxPaid()))
						/ Double.parseDouble(tdsCal.getRemainMonths());
				logger.info("taxPerMonth   :"+taxPerMonth);
				total[i][14] = String.valueOf(Math.round(Double.parseDouble(String.valueOf(tdsCal.getTaxPaid()))));
				if (taxPerMonth < 0) {
					total[i][15] = "0";
				}//end of if
				else {
					total[i][15] = String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxPerMonth))));
				}//end of else
				
				Object[][] flagData = null;
				try{
					String flagQuery = "select TDS_SALARY_DEDUCT_FLAG from hrms_tds where tds_emp_id="
							+ String.valueOf(empData[i][0])
							+ " and TDS_FROM_YEAR="
							+ frmYear + " and tds_to_year=" + toYear;
	
					flagData = getSqlModel().getSingleResult(flagQuery);
				} catch (Exception e) {
					logger.error("exception in flagData",e);
				} //end of catch
				
				total[i][16] = String.valueOf("false");
				if (flagData == null || flagData.length == 0) {

				}//end of if
				else if (String.valueOf(flagData[0][0]).equals("Y")) {
					total[i][16] = "true";
				}//end of else if

				total[i][17] = String.valueOf(empData[i][6]);

				total[i][18] = String.valueOf(empData[i][7]);
				total[i][19] = String.valueOf(empData[i][3]);
				total[i][20] = String.valueOf(empData[i][4]);
				total[i][21] = String.valueOf(empData[i][9]);
				total[i][22] = String.valueOf(empData[i][10]);
				logger.info("tdsCal.getMonthCount()   :"+tdsCal.getMonthCount());
				total[i][23] = String.valueOf(tdsCal.getMonthCount());
			} //end of loop "z"
		} catch (Exception e) {
			logger.error("exception in empData looop"+e);
		} //end of catch
			try {
				/*for (int j = 0; j < salaryTemp.length; j++) {
						}//end of for loop
				 */
				String insertQueryForSalTemp = "INSERT INTO HRMS_TDS_SALARY_TEMP (TDS_EMP_ID,TDS_CREDIT_AMOUNT,TDS_CREDIT_HEAD, "
						+ " TDS_YEAR_FROM,TDS_YEAR_TO) VALUES(?,?,?,?,?)";
				if (salaryTemp == null) {

				} else if (salaryTemp.length == 0) {

				} else {
					try {
						getSqlModel().singleExecute(insertQueryForSalTemp,salaryTemp);
					} catch (Exception e) {
						logger.error("exception in insert",e);
					} //end of catch
				} //end of else
			} catch (Exception e) {
				logger.error("exception in salTemp insert  "+e);
			} //end of catch
			
			try {
				String insertQueryForPerqTemp = "INSERT INTO HRMS_TDS_PERQ (TDS_EMP_ID,TDS_PERQ_AMOUNT,TDS_PERQ_HEAD,TDS_YEAR_FROM,TDS_YEAR_TO) "
						+ " VALUES(?,?,?,?,?)";
				if (perquisiteTemp == null) {

				}//end of if 
				else if (perquisiteTemp.length == 0) {

				}//end of else if
				else {
					try {
						getSqlModel().singleExecute(insertQueryForPerqTemp,perquisiteTemp);
					} catch (Exception e) {
						logger.error("exception in insertQueryForPerqTemp",e);
					} //end of catch
				} //end of else
			} catch (Exception e) {
				logger.error("exception in perqTemp insert  "+e);
			} //end of catch
			
			try {
				String tdsTempQuery = "INSERT INTO HRMS_TDS_TEMP (EMP_ID,FIN_YEAR_FROM,FIN_YEAR_TO,TDS_HRA,TDS_PF) "
						+ " VALUES(?,?,?,?,?)";
				if (tdsTemp == null) {

				} //end of if 
				else if (tdsTemp.length == 0) {

				} //end of else if
				else {
					try {
						getSqlModel().singleExecute(tdsTempQuery, tdsTemp);
					} catch (Exception e) {
						logger.error("exzception in tdsTempQuery",e);
					} //end of catch
				} //end of else
			} catch (Exception e) {
				logger.error("exception in tdsTemp insert  "+e);
			} //end of catch
			request.setAttribute("index", count);
			if(Integer.parseInt(frmYear) < forPreviousYear){
				tdsCal.setRemainMonths("No Remain Months");
			}//end of if
			return total;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception in getRecord method",e);
			return new String[1][22];
		} //end of catch

	}//end of getRecord method
	
	private String getEmpResignMonth(String empResignDate) {
	logger.info("empResignDate===="+empResignDate);
	String month = "";
	String resignMonth = "";
	
	month = empResignDate.substring(3, 5);
	logger.info("month===="+month);
	
	if(month.equals("04")){
		resignMonth = "12";
	} //end of if
	else if(month.equals("05")){
		resignMonth = "11";
	} //end of else if
	else if(month.equals("06")){
		resignMonth = "10";
	} //end of else if
	else if(month.equals("07")){
		resignMonth = "9";
	} //end of else if
	else if(month.equals("08")){
		resignMonth = "8";
	} //end of else if
	else if(month.equals("09")){
		resignMonth = "7";
	} //end of else if
	else if(month.equals("10")){
		resignMonth = "6";
	} //end of else if
	else if(month.equals("11")){
		resignMonth = "5";
	} //end of else if
	else if(month.equals("12")){
		resignMonth = "4";
	} //end of else if
	else if(month.equals("01")){
		resignMonth = "3";
	} //end of else if
	else if(month.equals("02")){
		resignMonth = "2";
	} //end of else if
	else if(month.equals("03")){
		resignMonth = "1";
	} //end of else if
	return resignMonth;
} //end of getEmpResignMonth method


	private double getArrearsTaxPaid(String empId, String frmYear, String toYear,String tdsDebitCode) {
		double arrearTaxAmt = 0;
		
		Object[][]arrearAprToDec = null;
		Object[][]arrearJanToMar = null;
		
		try {
			String query = "SELECT  NVL(SUM(ARREARS_AMT),0) "
					+ " FROM  HRMS_ARREARS_DEBIT_"
					+ frmYear
					+ " "
					+ " WHERE ARREARS_MONTH >=4 AND ARREARS_MONTH <=12 AND ARREARS_DEBIT_CODE = "
					+ tdsDebitCode + " " + "  AND ARREARS_EMP_ID = " + empId
					+ "";
			arrearAprToDec = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in arrearAprToDec tax paid",e);
		}
		
		try {
			String query = "SELECT  NVL(SUM(ARREARS_AMT),0) "
					+ " FROM  HRMS_ARREARS_DEBIT_"
					+ toYear
					+ " "
					+ " WHERE ARREARS_MONTH >=1 AND ARREARS_MONTH <=3 AND ARREARS_DEBIT_CODE = "
					+ tdsDebitCode + " " + "  AND ARREARS_EMP_ID = " + empId
					+ "";
			arrearJanToMar = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in arrearJanToMar tax paid",e);
		}
		
		if(arrearAprToDec !=null){
			try {
				arrearTaxAmt = Double.parseDouble(String
						.valueOf(arrearAprToDec[0][0]));
			} catch (Exception e) {
				logger.error("exception in adding arrearAprToDec tax paid ",e);
			}
		}
		
		if(arrearJanToMar !=null){
			try {
				arrearTaxAmt = arrearTaxAmt + Double.parseDouble(String
						.valueOf(arrearJanToMar[0][0]));
			} catch (Exception e) {
				logger.error("exception in adding arrearAprToDec tax paid ",e);
			}
		}
		
		
	return arrearTaxAmt;
}


	private Object[][] getDebits(String empID, Object[][] processData,Object[][] processData1,
			String frmYear, String toYear, Object[][] debittotalInv) {
		
		String ledgerCode="",ledgerCode1="";
		String debCodes = "",invCodes="";
		Object[][] debDataAprToDec=null;
		Object[][] debDataJanToMarch=null;
		Object[][]salDebitsToAdd=null;
		if(debittotalInv==null){
			
		}
		else if(debittotalInv.length==0){
			
		}
		else{
			for (int i = 0; i < debittotalInv.length; i++) {
				debCodes+=String.valueOf(debittotalInv[i][4])+",";
			}
			debCodes = debCodes.substring(0,debCodes.length()-1);
		}
		logger.info("debCodes in getDebits :"+debCodes);
		
		/**
		 * for debits APRIL TO DECEMBER
		 */
		if (processData == null) {

		}//end of if
		else if (processData.length == 0) {

		}//end of else if 
		else{
			for (int i = 0; i < processData.length; i++) {
				ledgerCode+=String.valueOf(processData[i][0])+",";//this ledger code contains the code for Apr To Dec
			}
			//all the ledgerCodes for salary process from April to December are substring together.
			ledgerCode = ledgerCode.substring(0,ledgerCode.length()-1);
			
			
			try {
				//logger.info("debitTaxPaid---------------------------  :"+debitTaxPaid);
				String debQuery = "  SELECT SAL_DEBIT_CODE,SUM(NVL(SAL_AMOUNT,0))  FROM HRMS_SAL_DEBITS_"
						+ frmYear
						+ " "
						+ "INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"
						+ frmYear
						+ ".SAL_DEBIT_CODE) "
						+ "WHERE SAL_LEDGER_CODE IN("
						+ ledgerCode
						+ ") and emp_id="
						+ empID
						+ " and sal_debit_code In("+debCodes+") "
						+ "group by SAL_DEBIT_CODE ";
				debDataAprToDec = getSqlModel().getSingleResult(debQuery);
			} catch (Exception e) {
				logger.error("error in getSalDebit "+e);
			}
		}	
		
		/**
		 * this for loop is for salary processed in Jan To March
		 */
		if (processData1 == null) {

		}//end of if
		else if (processData1.length == 0) {

		}//end of else if 
		else{
			for (int i = 0; i < processData1.length; i++) {
				ledgerCode1 = String.valueOf(processData1[i][0])+",";//this ledger code contains the code for Jan To March
			}
			ledgerCode1 = ledgerCode1.substring(0,ledgerCode1.length()-1);	
			
			try {
				//logger.info("debitTaxPaid---------------------------  :"+debitTaxPaid);
				String debQuery = "  SELECT SAL_DEBIT_CODE,SUM(NVL(SAL_AMOUNT,0))  FROM HRMS_SAL_DEBITS_"
						+ toYear
						+ " "
						+ "INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"
						+ toYear
						+ ".SAL_DEBIT_CODE) "
						+ "WHERE SAL_LEDGER_CODE IN("
						+ ledgerCode1
						+ ") and emp_id="
						+ empID
						+ " and sal_debit_code In("+debCodes+") "
						+ "group by SAL_DEBIT_CODE ";
				debDataJanToMarch = getSqlModel().getSingleResult(debQuery);
			} catch (Exception e) {
				logger.error("error in getSalDebit "+e);
			}
			
		}
		if(debDataAprToDec==null){
			
		}
		else if(debDataAprToDec.length==0){
			
		}
		else{
			for (int i = 0; i < debDataAprToDec.length; i++) {
				for (int j = 0; j < debittotalInv.length; j++) {
					if(String.valueOf(debDataAprToDec[i][0]).equals(String.valueOf(debittotalInv[j][4]))){
						debittotalInv[j][0] = debDataAprToDec[i][1];
					}
				}
			}
		}
		
		if(debDataJanToMarch==null){
			
		}
		else if(debDataJanToMarch.length==0){
			
		}
		else{
			for (int i = 0; i < debDataJanToMarch.length; i++) {
				for (int j = 0; j < debittotalInv.length; j++) {
					if(String.valueOf(debDataJanToMarch[i][0]).equals(String.valueOf(debittotalInv[j][4]))){
						debittotalInv[j][0] = Integer.parseInt(String.valueOf(debittotalInv[j][0]))
												+  Integer.parseInt(String.valueOf(debDataJanToMarch[i][1]));
					}
				}
			}
		}
		
		if(debittotalInv==null){
			
		}
		else if(debittotalInv.length==0){
			
		}
		else{
			for (int i = 0; i < debittotalInv.length; i++) {
				invCodes+=String.valueOf(debittotalInv[i][2])+",";
			}
			invCodes = invCodes.substring(0,invCodes.length()-1);
		}
		
		/**
		 * NOW DELETE THE RECORD FROM HRMS_EMP_INVESTMENT FOR INVESTMENT CODE 
		 * WHICH IS IN THE debittotalInv OBJECT
		 */
		String deleteInvestment ="DELETE FROM HRMS_EMP_INVESTMENT WHERE INV_CODE IN("+invCodes+") AND EMP_ID="+empID+"";
		boolean result = getSqlModel().singleExecute(deleteInvestment);
		
		for (int i = 0; i < debittotalInv.length; i++) {
			
			String insertEmpInvestment = "INSERT INTO HRMS_EMP_INVESTMENT (INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_CODE,INV_VALID_AMOUNT) "
				+" VALUES((SELECT MAX(INV_ID)+1 FROM HRMS_EMP_INVESTMENT),"+empID+","+frmYear+","+toYear+","+debittotalInv[i][2]+","+debittotalInv[i][0]+")";
			boolean insert = getSqlModel().singleExecute(insertEmpInvestment);
		} 
		
	return debittotalInv;
}


	/**
	 * THIS METHOD IS CALLED TO GET THE SUM AMOUNT OF PI ALLOWANCE TAX PAID. HERE PROCESS DATE IS
	 * APPLIED AS FILTER IN THE QUERY.
	 * @param empId
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	public Object[][]getPiAllowTaxPaid(String empId, String frmYear, String toYear){
		
		Object[][]piTaxPaid = null;
		
		try {
			String query = "SELECT NVL(SUM(ALLW_TAX_AMT),0) "
					+ " FROM HRMS_ALLOWANCE_HDR  "
					+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) "
					+ " WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID=" + empId
					+ "  " + " AND ALLW_PROCESS_DATE>=TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND ALLW_PROCESS_DATE<=TO_DATE('31-03-"
					+ toYear + "','DD-MM-YYYY')";
			piTaxPaid = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception piTaxPaid query",e);
		}
		return piTaxPaid;
	}


	private Object[][] getPerqData(String empId) {
		Object[][]perqData = null;
		try {
			String query = "SELECT HRMS_EMP_PERQUISITE.PERQ_CODE,NVL(PERQ_AMT,0),PERQ_NAME,PERQ_PERIOD "
				+" FROM HRMS_EMP_PERQUISITE  "
				+" LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE) "
				+" WHERE EMP_ID = "+empId+" AND PERQ_TAXABLE_FLAG='Y'";
			perqData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in perq query  "+e);
		}
		return perqData;
}


	/**
	 * 
	 * @param income
	 * @param taxParameters
	 * @param empId
	 * @param rentAmt
	 * @param totalIncome
	 * @param tdsCal
	 * @param joinedMonth
	 * @return
	 */
	public double getHraCondAmt(Object[][] income, Object[][] taxParameters,
			String empId, double rentAmt, double totalIncome,
			TdsCalculation tdsCal, String joinedMonth) {
		int k = 0;
		double hraFinalAmt = 0;
		//logger.info("taxParameters[0][5]   :"+taxParameters[0][5]);
		//logger.info("joinedMonth   :"+joinedMonth);
		//logger.info("empId in  getHraCondAmt  ==>  :"+empId);
		for (int j = 0; j < income.length; j++) {//loop "z"
			//logger.info("String.valueOf(income[j][0]   :"+String.valueOf(income[j][0]));
			logger.info("String.valueOf(income[j][1]   :"+String.valueOf(income[j][1]));
			if ((String.valueOf(income[j][0]).trim().equals(String
					.valueOf(taxParameters[0][5])))) {// /it checks the HRA
														// credit Code
				//logger.info("in 1st if   :");
				logger.info("String.valueOf(income[j][2]   :"+String.valueOf(income[j][2]));
				if ((Double.parseDouble(String.valueOf(income[j][1]))>0)) {
					//logger.info("in 2 if   :");
					//logger.info("taxParameters[0][6]   :"+taxParameters[0][6]);// /if HRA is not zero
					double hraCond1Amt = Double.parseDouble(tdsCal.getHraPaidAmtCond1());// /(40%(ba+ da))
					logger.info("hraCond1Amt      :"+hraCond1Amt);
					//logger.info("taxParameters[0][7]   :"+taxParameters[0][7]);
					double hraCond2Amt = Double.parseDouble(tdsCal.getHraPaidAmtCond2());// /(HRA)
					logger.info("hraCond2Amt      :"+hraCond2Amt);
					/**
					 * // this hraPaidAmtCond is set in getIncmome method Rent-(10%(ba + da))
					 */
					logger.info("tdsCal.getHraPaidAmtCond()      :"+tdsCal.getHraPaidAmtCond());
					logger.info("rentAmt          :"+rentAmt);
					double hraCond3Amt = rentAmt - Double.parseDouble(tdsCal.getHraPaidAmtCond());
					logger.info("hraCond3Amt             :"+hraCond3Amt);
					/**
					 * conditions to check which amount is minimum..
					 */
					double least =hraCond1Amt; 
					if (least >= hraCond2Amt) {
						least = hraCond2Amt;
						logger.info("hraFinalAmt  1           :"+hraFinalAmt);
					}//end of nested if
					if (least >= hraCond3Amt
							) {
						least = hraCond3Amt;
						logger.info("hraFinalAmt  2           :"+hraFinalAmt);
					}//end of nested else if
					
						hraFinalAmt =least;
						logger.info("hraFinalAmt   3          :"+hraFinalAmt);

				}//end of if
				else {// //if HRA credit amount is zero...
					//logger.info("in unpaid       :");
					//logger.info("taxParameters[0][9]   :"+taxParameters[0][9]);
					double hraUnpaidCond1 = getCondition1(empId, String
							.valueOf(taxParameters[0][9]));// ///(24000)
					logger.info("hraUnpaidCond1      :"+hraUnpaidCond1);
					double hraUnpaidCond2 = getCondition1(empId, String
							.valueOf(taxParameters[0][10]));// /(25%of Income)
					//logger.info("taxParameters[0][10]      :"+taxParameters[0][10]);
					hraUnpaidCond2 = (hraUnpaidCond2/100) * totalIncome;
					logger.info("hraUnpaidCond2      :"+hraUnpaidCond2);
					double hraUnpaidCond3 = getCondition1(empId, String
							.valueOf(taxParameters[0][11]));// /(Rent-10%(Income))
					//logger.info("taxParameters[0][11]      :"+taxParameters[0][11]);
					hraUnpaidCond3 = rentAmt - ((hraUnpaidCond3/100) * totalIncome);
					logger.info("hraUnpaidCond3      :"+hraUnpaidCond3);
					double least =hraUnpaidCond1; 
					if (least >= hraUnpaidCond2) {
						least = hraUnpaidCond2;
						logger.info("hraFinalAmt  1           :"+hraFinalAmt);
					}//end of nested if
					if (least >= hraUnpaidCond3) {
						least = hraUnpaidCond3;
						logger.info("hraFinalAmt  2           :"+hraFinalAmt);
					}//end of nested else if
					
						hraFinalAmt =least;
						logger.info("hraFinalAmt   3          :"+hraFinalAmt);
				} //end of nested else
				k++;

			}//end of if

		}//end of for loop "z"
		if (k == 0) {
			hraFinalAmt = 0;
		}
		return hraFinalAmt;
	}//end of getHraCondAmt method

	/**
	 * to get record of general employee
	 * @param tdscal
	 * @return tdscal
	 */
	public TdsCalculation generalRecord(TdsCalculation tdscal) {
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"
				+ "  HRMS_EMP_OFFC.EMP_ID,EMP_GENDER,TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(EMP_DOB,'YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_CENTER.CENTER_ID,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY')    FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = "
				+ tdscal.getEmpID()
				+ " "
				+ "  ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "
				+ " HRMS_EMP_OFFC.EMP_LNAME ";
		Object[][] empData = getSqlModel().getSingleResult(sql);

		tdscal.setEmpToken(checkNull(String.valueOf(empData[0][0])));
		tdscal.setEmpName(checkNull(String.valueOf(empData[0][1])));
		tdscal.setEmpCenter(checkNull(String.valueOf(empData[0][2])));
		tdscal.setEmpRank(checkNull(String.valueOf(empData[0][3])));
		tdscal.setEmpGender(checkNull(String.valueOf(empData[0][5])));
		tdscal.setEmpAge(checkNull(String.valueOf(empData[0][6])));
		tdscal.setCenterId(checkNull(String.valueOf(empData[0][8])));
		tdscal.setEmpJoinedDate(checkNull(String.valueOf(empData[0][9])));
		return tdscal;
	}//end of generalRecord method

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}//end of checkNull 

	/**
	 * this method is used to delete the record from the hrms_tds table
	 * @param tdscal
	 * @param empId
	 * @return result
	 */
	public boolean deleteRecord(TdsCalculation tdscal, String[] empId) {

		Object[][] empCode = new Object[empId.length][1];

		for (int i = 0; i < empId.length; i++) {
			empCode[i][0] = empId[i];
		}//end of for loop
		String query1 = "delete from hrms_tds where tds_emp_id=? and TDS_FROM_YEAR="
				+ tdscal.getFromYear()
				+ " and TDS_to_YEAR="
				+ tdscal.getToYear() + "";
		boolean result = getSqlModel().singleExecute(query1, empCode);

		return result;
	}//end of deleteRecord method

	/**
	 * this method is used to save the record
	 * @param tdscal
	 * @param add
	 * @return
	 * @throws Exception
	 */
	public boolean saveRecord(TdsCalculation tdscal, Object[][] add)
			throws Exception {
		String salTempCode = "";
		boolean result =false;
		logger.info("frmYear--->>>"+tdscal.getFromYear());
		try {
			String query = "INSERT INTO HRMS_TDS(TDS_CODE,TDS_FROM_YEAR,TDS_TO_YEAR,TDS_EMP_ID,TDS_GROSS_SALARY,TDS_EXCEMPTIONS,"
					+ "TDS_REBATE,TDS_OTH_INCOME,TDS_TAXABLE_INCOME,TDS_TOTAL_TAX,TDS_EDUC_CESS,TDS_SURCHARGE,"
					+ "TDS_NET_TAX,TDS_TAXPERMONTH,TDS_SALARY_DEDUCT_FLAG,TDS_TAX_PAID,TDS_DEDUCTIONS,TDS_PROF_TAX,TDS_REMAIN_MONTH) "
					+ "VALUES((SELECT NVL(MAX(TDS_CODE),0)+1 FROM HRMS_TDS),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(query, add);
		} catch (Exception e) {
			logger.error("exception in INSERT INTO HRMS_TDS in saveRecord method",e);
		} //end of catch
		/**
		 * METHODS TO INSERT DATA IN HRMS_SAL_TEMP
		 * 
		 */
		Object[][] salTempData = null;
		try {
			String salTemp = "SELECT TDS_EMP_ID,TDS_CREDIT_HEAD,TDS_CREDIT_AMOUNT, "
					+ " TDS_YEAR_FROM,TDS_YEAR_TO FROM HRMS_TDS_SALARY_TEMP WHERE TDS_EMP_ID IS NOT NULL";
			salTempData = getSqlModel().getSingleResult(salTemp);
		} catch (Exception e) {
			logger.error("exceptio in salTempData query",e);
		} //end of catch
		
		
		Object[][] salTempDataToDelete = null;
		try {
			String salTempDel = "SELECT DISTINCT TDS_EMP_ID FROM HRMS_TDS_SALARY_TEMP WHERE TDS_EMP_ID IS NOT NULL";
			salTempDataToDelete = getSqlModel().getSingleResult(salTempDel);
		} catch (Exception e) {
			logger.error("exceptio in salTempData query",e);
		} //end of catch
		
		
		if(salTempDataToDelete == null){
			
		} //end of if
		else if(salTempDataToDelete.length == 0){
			
		} //end of else if
		else{
			
			try {
				for (int i = 0; i < salTempDataToDelete.length; i++) {
					salTempCode += String.valueOf(salTempDataToDelete[i][0]) + ",";//this ledger code contains the code for Apr To Dec
				} //end of loop
			} catch (Exception e) {
				logger.error("exception in processData loop",e);
			} //end of catch
			//all the ledgerCodes for salary process from April to December are substring together.
			salTempCode = salTempCode.substring(0,salTempCode.length()-1);	
			
			
		} //end of else
		
		String insertFinalSalTemp = "INSERT INTO HRMS_TDS_SALARY (TDS_EMP_ID,TDS_CREDIT_HEAD,TDS_CREDIT_AMOUNT, "
				+ " TDS_YEAR_FROM,TDS_YEAR_TO) VALUES(?,?,?,?,?)";

		if (salTempData.length > 0) {

			try {
				String delSalFinal = "DELETE FROM HRMS_TDS_SALARY WHERE TDS_EMP_ID IN ("
						+ salTempCode
						+ ") AND TDS_YEAR_FROM="
						+ tdscal.getFromYear()
						+ " "
						+ " AND TDS_YEAR_TO = "
						+ tdscal.getToYear() + "";
				getSqlModel().singleExecute(delSalFinal);
			} catch (Exception e) {
				logger.error("exception in delete HRMS_TDS_SALARY",e);
			} //end of catch
			try {
				getSqlModel().singleExecute(insertFinalSalTemp, salTempData);
			} catch (Exception e) {
				logger.error("exception in insertFinalSalTemp",e);
			} //end of catch
		} //end of if

		/**
		 * METHOD TO INSERT DATA IN HRMS_EMP_INVESTMENT FROM TABLE HRMS_TDS_TEMP FOR HRA
		 */
		// this method is to retrieve all the data from the HRMS_TAX_PARAMETER
		Object[][] taxParameters = null;
		try {
			taxParameters = getTaxParameters(tdscal);
		} catch (Exception e) {
			logger.error("exception in taxParameters object");
		} //end of catch
		Object[][] chkEmpInvDataHra = null;
		String chkEmpInv = "SELECT EMP_ID,INV_ID,HRMS_EMP_INVESTMENT.INV_CODE FROM HRMS_EMP_INVESTMENT "
				+ " WHERE INV_CODE= "
				+ taxParameters[0][12]
				+ " AND INV_FINYEAR_FROM="
				+ tdscal.getFromYear()
				+ " AND INV_FINYEAR_TO=" + tdscal.getToYear() + "";
		try {
			chkEmpInvDataHra = getSqlModel().getSingleResult(chkEmpInv);
		} catch (Exception e) {
			logger.error("exception in chkEmpInvDataHra",e);
		} //end of catch
		Object[][] tdsTempDataHra = null;
		String tdsTemp = "SELECT EMP_ID,FIN_YEAR_FROM,FIN_YEAR_TO,TDS_HRA,'' FROM HRMS_TDS_TEMP";
		try {
			tdsTempDataHra = getSqlModel().getSingleResult(tdsTemp);
		} catch (Exception e) {
			logger.error("exception in tdsTempDataHra",e);
		} //end of catch
		if (chkEmpInvDataHra.length > 0) {
			try {
				for (int i = 0; i < chkEmpInvDataHra.length; i++) {

					for (int j = 0; j < tdsTempDataHra.length; j++) {
						if (String.valueOf(chkEmpInvDataHra[i][0]).trim()
								.equals("" + tdsTempDataHra[j][0] + "")) {

							String delData = "DELETE FROM HRMS_EMP_INVESTMENT WHERE EMP_ID="
									+ chkEmpInvDataHra[i][0]
									+ " AND "
									+ " INV_FINYEAR_FROM="
									+ tdscal.getFromYear()
									+ " AND INV_FINYEAR_TO="
									+ tdscal.getToYear()
									+ " AND INV_CODE IN("
									+ taxParameters[0][12] + ")";
							getSqlModel().singleExecute(delData);
						} //end of if
					} //end of loop
				} //end of loop
			} catch (Exception e) {
				logger.error("exception in chkEmpInvDataHra loop",e);
			} //end of catch
		} //end of if
		Object[][] maxInvIdValue = null;
		String maxInvIdQueryHra = "SELECT NVL(MAX(INV_ID),0) FROM HRMS_EMP_INVESTMENT";
		try {
			maxInvIdValue = getSqlModel().getSingleResult(maxInvIdQueryHra);
		} catch (Exception e) {
			logger.error("exception in maxInvIdValue query",e);
		} //end of catch
		
		Object[][] empInvHra = null;
		
		if(tdsTempDataHra == null){
			
		} //end of if
		else if(tdsTempDataHra.length == 0){
			
		} //end of else if
		else{
			empInvHra = new Object[tdsTempDataHra.length][7];
			int srNoHra = 0;
			srNoHra = Integer.parseInt(String.valueOf(maxInvIdValue[0][0]));
			if (tdsTempDataHra.length > 0) {
				for (int i = 0; i < tdsTempDataHra.length; i++) {
					srNoHra++;
					empInvHra[i][0] = String.valueOf(srNoHra);// /for Inv Id
					empInvHra[i][1] = String.valueOf(tdsTempDataHra[i][0]);// //emp Id
					empInvHra[i][2] = String.valueOf(tdsTempDataHra[i][1]);// /from year
					empInvHra[i][3] = String.valueOf(tdsTempDataHra[i][2]);// /to year
					empInvHra[i][4] = String.valueOf(tdsTempDataHra[i][3]);// /valid Amount
					empInvHra[i][5] = String.valueOf(taxParameters[0][12]);// //HRA code.
					empInvHra[i][6] = String.valueOf(tdsTempDataHra[i][3]);// /INV_AMOUNT
				} //end of loop
			} //end of if
			String insertFinalTdsTempHra = "INSERT INTO HRMS_EMP_INVESTMENT(INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO, "
					+ " INV_VALID_AMOUNT,INV_CODE,INV_AMOUNT) " + " VALUES(?,?,?,?,?,?,?)";
			try {
				getSqlModel().singleExecute(insertFinalTdsTempHra, empInvHra);
			} catch (Exception e) {
				logger.error("exception in insertFinalTdsTempHra",e);
			} //end of catch
		} //end of else
		

		/**
		 * METHOD TO INSERT DATA IN HRMS_EMP_INVESTMENT FROM TABLE HRMS_TDS_TEMP FOR PF
		 * 
		 */
		Object[][] chkEmpInvDataPf = null;
		try {
			String chkEmpInvData = "SELECT EMP_ID,INV_ID,HRMS_EMP_INVESTMENT.INV_CODE FROM HRMS_EMP_INVESTMENT "
					+ " WHERE INV_CODE= "
					+ taxParameters[0][14]
					+ " AND INV_FINYEAR_FROM="
					+ tdscal.getFromYear()
					+ " AND INV_FINYEAR_TO=" + tdscal.getToYear() + "";
			chkEmpInvDataPf = getSqlModel().getSingleResult(chkEmpInvData);
		} catch (Exception e) {
			logger.error("exception in chkEmpInvDataPf query",e);
		} //end of catch
		
		Object[][] tdsTempDataPf = null;
		String tdsTempData = "SELECT EMP_ID,FIN_YEAR_FROM,FIN_YEAR_TO,TDS_PF,'' FROM HRMS_TDS_TEMP";
		try {
			tdsTempDataPf = getSqlModel().getSingleResult(tdsTempData);
		} catch (Exception e) {
			logger.error("exception in tdsTempDataPf query",e);
		} //end of catch
		if (chkEmpInvDataPf.length > 0) {

			try {
				for (int i = 0; i < chkEmpInvDataPf.length; i++) {

					for (int j = 0; j < tdsTempDataPf.length; j++) {
						if (String.valueOf(chkEmpInvDataPf[i][0]).trim()
								.equals("" + tdsTempDataPf[j][0] + "")) {

							String delData = "DELETE FROM HRMS_EMP_INVESTMENT WHERE EMP_ID="
									+ chkEmpInvDataPf[i][0]
									+ " AND "
									+ " INV_FINYEAR_FROM="
									+ tdscal.getFromYear()
									+ " AND INV_FINYEAR_TO="
									+ tdscal.getToYear()
									+ " AND INV_CODE IN("
									+ taxParameters[0][14] + ")";
							getSqlModel().singleExecute(delData);
						} //end of if
					} //end of loop
				} //end of loop
			} catch (Exception e) {
				logger.error("exception in chkEmpInvDataPf loop",e);
			} //end of catch
		} //end of if

		Object[][] maxInvIdValuePf = null;
		String maxInvIdQueryPf = "SELECT NVL(MAX(INV_ID),0) FROM HRMS_EMP_INVESTMENT";
		try {
			maxInvIdValuePf = getSqlModel().getSingleResult(maxInvIdQueryPf);
		} catch (Exception e) {
			logger.error("exception in maxInvIdValuePf query",e);
		} //end of catch
		Object[][] empInvPf = null;
		try {
			empInvPf = new Object[tdsTempDataHra.length][7];
		} catch (Exception e) {
			logger.error("exception in empInvPf object",e);
		} //end of catch
		int srNoPf = 0;
		srNoPf = Integer.parseInt(String.valueOf(maxInvIdValuePf[0][0]));
		if (tdsTempDataPf.length > 0) {
			try {
				for (int i = 0; i < tdsTempDataPf.length; i++) {
					srNoPf++;
					empInvPf[i][0] = String.valueOf(srNoPf);// /for Inv Id
					empInvPf[i][1] = String.valueOf(tdsTempDataPf[i][0]);// //empId
					empInvPf[i][2] = String.valueOf(tdsTempDataPf[i][1]);// /from year
					empInvPf[i][3] = String.valueOf(tdsTempDataPf[i][2]);// /to year
					empInvPf[i][4] = String.valueOf(tdsTempDataPf[i][3]);// /valid Amount
					empInvPf[i][5] = String.valueOf(taxParameters[0][14]);// //Pf code.
					empInvPf[i][6] = String.valueOf(tdsTempDataPf[i][3]);// /INV_AMOUNT
				}
			} catch (Exception e) {
				logger.error("exception in tdsTempDataPf loop",e);
			} //end of catch
		} //end of if

		String insertFinalTdsTempPf = "INSERT INTO HRMS_EMP_INVESTMENT(INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO, "
				+ " INV_VALID_AMOUNT,INV_CODE,INV_AMOUNT) " + " VALUES(?,?,?,?,?,?,?)";
		try {
			getSqlModel().singleExecute(insertFinalTdsTempPf, empInvPf);
		} catch (Exception e) {
			logger.error("exception in insertFinalTdsTempPf",e);
		} //end of catch
		return result;
	}//end of saveRecord method

	/**
	 * to add the employee debits
	 * @param addDebit
	 * @param tdsCal
	 * @return result
	 */
	public boolean addDebit(Object[][] addDebit, TdsCalculation tdsCal) {
		String query = "insert into hrms_emp_debit (DEBIT_CODE,EMP_ID,DEBIT_AMT) values("
				+ tdsCal.getTaxParaDebitCode() + ",?,?)";
		return getSqlModel().singleExecute(query, addDebit);
	}

	/**
	 * This method is used to delete the employee debits
	 * @param addDebit
	 * @param tdsCal
	 * @return
	 */
	public boolean delEmpDebit(Object[][] addDebit, TdsCalculation tdsCal) {
		Object[][] taxParameters = getTaxParameters(tdsCal);
		tdsCal.setTaxParaDebitCode(String.valueOf(taxParameters[0][0]));
		if (tdsCal.getTaxParaDebitCode().equals("")
				|| tdsCal.getTaxParaDebitCode().equals("null")) {
			return false;
		}
		String query = "delete from HRMS_EMP_DEBIT where emp_id=? and DEBIT_code="
				+ tdsCal.getTaxParaDebitCode() + "";
		return getSqlModel().singleExecute(query, addDebit);
	}

	/**
	 * this method is used to view the saved records
	 * @param tdsCal
	 * @param request
	 * @return
	 */
	public String[][] getRecordView(TdsCalculation tdsCal,
			HttpServletRequest request) {

		try {
			String sql = "select HRMS_TDS.TDS_EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN ,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '|| "
					+ " HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), TDS_GROSS_SALARY,TDS_EXCEMPTIONS,TDS_OTH_INCOME,TDS_DEDUCTIONS, "
					+ " TDS_REBATE,TDS_PROF_TAX,TDS_TAXABLE_INCOME,TDS_TOTAL_TAX, "
					+ " TDS_EDUC_CESS, TDS_SURCHARGE,TDS_NET_TAX,TDS_TAX_PAID,TDS_TAXPERMONTH,TDS_SALARY_DEDUCT_FLAG, "
					+ " HRMS_EMP_OFFC.EMP_GENDER, "
					+ " TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(EMP_DOB,'YYYY'),HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_ID,TO_CHAR(EMP_REGULAR_DATE ,'DD-MM-YYYY'),TDS_REMAIN_MONTH "
					+ " FROM HRMS_TDS  "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID "
					+ "  INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+ " WHERE 1=1 and TDS_FROM_YEAR="
					+ tdsCal.getFromYear()
					+ " and TDS_TO_YEAR=" + tdsCal.getToYear() + " ";
			if (!tdsCal.getBranchCode().equals("")) {
				sql = sql + " AND EMP_CENTER=" + tdsCal.getBranchCode();
			}//end of if
			if (!tdsCal.getTypeCode().equals("")) {
				sql = sql + " AND EMP_TYPE=" + tdsCal.getTypeCode();
			}//end of if
			if (!tdsCal.getDeptCode().equals("")) {
				sql = sql + " AND EMP_DEPT=" + tdsCal.getDeptCode();
			}//end of if
			if (!tdsCal.getDivisionCode().equals("")) {
				sql = sql + " AND EMP_DIV=" + tdsCal.getDivisionCode();
			}//end of if
			if (!tdsCal.getEmployeeId().equals("")) {
				sql = sql + " AND EMP_ID=" + tdsCal.getEmployeeId();
			}//end of if
			sql = sql
					+ " order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||  HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			Object[][] data = getSqlModel().getSingleResult(sql);
			String[][] total = new String[data.length][24];

			try {

				int count = 0;
				for (int i = 0; i < data.length; i++) {
					count++;

					total[i][0] = String.valueOf(data[i][0]);
					total[i][1] = String.valueOf(data[i][1]);
					total[i][2] = String.valueOf(data[i][2]);
					total[i][3] = String.valueOf(data[i][3]);
					total[i][4] = String.valueOf(data[i][4]);
					total[i][5] = String.valueOf(data[i][5]);
					total[i][6] = String.valueOf(data[i][6]);
					total[i][7] = String.valueOf(data[i][7]);
					if (String.valueOf(data[i][8]).equals("")
							|| String.valueOf(data[i][8]).equals("null")) {
						total[i][8] = "0";
					}//end of if
					else {
						total[i][8] = String.valueOf(data[i][8]);
					}//end of else
					total[i][9] = String.valueOf(data[i][9]);
					total[i][10] = String.valueOf(data[i][10]);
					total[i][11] = String.valueOf(data[i][11]);
					total[i][12] = String.valueOf(data[i][12]);
					total[i][13] = String.valueOf(data[i][13]);
					total[i][14] = String.valueOf(data[i][14]);
					total[i][15] = String.valueOf(data[i][15]);
					if (String.valueOf(data[i][16]).equals("Y")) {
						total[i][16] = String.valueOf("true");
					}//end of if
					else {
						total[i][16] = String.valueOf("false");
					}//end of else
					total[i][17] = String.valueOf(data[i][17]);
					total[i][18] = String.valueOf(data[i][18]);
					total[i][19] = String.valueOf(data[i][19]);
					total[i][20] = String.valueOf(data[i][20]);
					total[i][21] = String.valueOf(data[i][21]);
					total[i][22] = String.valueOf(data[i][22]);
					total[i][23] = String.valueOf(data[i][23]);
				}//end of for loop

				request.setAttribute("index", count);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				logger.info("Exception in getRecordView method in data.length");
			}
			return total;
		} catch (Exception e) {
			logger.info("Exception in getRecordView method");
			return null;
		}

	}//end of getRecordView method

}
