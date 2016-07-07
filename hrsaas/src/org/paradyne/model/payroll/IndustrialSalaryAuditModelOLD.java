package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.paradyne.bean.payroll.IndustrialSalaryAudit;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.Report;
/**
 * @author sunil
 *
 */
public class IndustrialSalaryAuditModelOLD extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	IndustrialSalaryAudit industrialSalary = null;

	// GET EMP_ID THOSE FULLFILL SORTING CRITERIA

	public Object[][] getEmpId(IndustrialSalaryAudit industrialSalary) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 * 
			 */
			String selectSalary = " SELECT EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_PAYBILL IS NOT  NULL ";
			String where = " ";
			try {
				if (industrialSalary.getPayBillNo() != null
						&& industrialSalary.getPayBillNo().length() > 0)

					where = " AND EMP_PAYBILL="
							+ industrialSalary.getPayBillNo();
				
				where += " AND EMP_TYPE="
					+ industrialSalary.getTypeCode();

				selectSalary = selectSalary + where;
			} catch (Exception e) {
				e.printStackTrace();
			}

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}

	// GET CREDIT CODE AND CREDIT ABBR

	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {
			String selectCredit = "SELECT CREDIT_CODE,  CREDIT_ABBR FROM HRMS_CREDIT_HEAD order BY CREDIT_CODE";
			/*
			 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF CREDIT ON SCREEN
			 * 
			 */
			credit_header = getSqlModel().getSingleResult(selectCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credit_header;

	}

	// GET DEBIT CODE AND DEBIT ABBR

	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try {
			String selectDebit = "SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD order BY DEBIT_PRIORITY";
			/*
			 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF DEBIT ON SCREEN
			 * 
			 */
			debit_header = getSqlModel().getSingleResult(selectDebit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit_header;

	}

	/*
	 * 
	 * SET DEBIT HEADER AND CREDIT HEADER and get COMPLETE ROWS OF CREDIT AND
	 * DEBIT OF EMPLOYEES
	 */

	public Object[][] getSalary(IndustrialSalaryAudit industrialSalary) {

		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		Object emp_id[][] = getEmpId(industrialSalary);
		ArrayList<IndustrialSalaryAudit> creditNames = new ArrayList<IndustrialSalaryAudit>();
		ArrayList<IndustrialSalaryAudit> debitNames = new ArrayList<IndustrialSalaryAudit>();

		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 * 
			 */
			IndustrialSalaryAudit creditName = new IndustrialSalaryAudit();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);

		}

		industrialSalary.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
			 * 
			 */

			IndustrialSalaryAudit debitName = new IndustrialSalaryAudit();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);

		}

		industrialSalary.setDebitHeader(debitNames);

		/*
		 * TOTAL LENGTH OF COULUM
		 */
		int c = debit_header.length + credit_header.length;
		/*
		 * NO OF TOTAL ROWS IS R
		 */
		int r = emp_id.length;
		Object[][] rows = new Object[r][];
		
		/*
		 * THIS IS ORIGINAL CREDIT AMOUNT ON WHICH MANUPILATION HAS TO DO
		 * 
		 * 
		 */
		Object debitLength[][] = getDebitHeader();
		/*
		 * THIS GIVES DEBIT LENGTH ON WHICH WE WILL ITERATE IN FOR LOOP FOR
		 * SETING VALUE OF DEBIT
		 * 
		 */

		Object creditLength[][] = getCreditHeader();

		for (int i = 0; i < emp_id.length; i++) {
			/*
			 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
			 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
			 * TOTAL NO OF EMPLPYEE
			 */
			Object[][] row = getRow(String.valueOf(emp_id[i][0]), String
					.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
					industrialSalary.getMonth(), industrialSalary
							.getYear(),debitLength,creditLength);
			rows[i] = row[0];

		}

		return rows;

	}

	// GET CREDIT CODE AND VALUE OF PROCESSED SALARY OF EMPLOYEE
	public Object[][] getSalCredit(String emp_id, String month, String year) {
		Object[][] credit_amount = null;
		try {

			String selectCredits = "SELECT   SAL_CREDIT_CODE,SAL_AMOUNT  FROM HRMS_SAL_CREDITS_"
					+ year
					+ " WHERE EMP_ID='"
					+ emp_id
					+ "' AND SAL_MONTH='"
					+ month + "'";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {

		}
		return credit_amount;

	}

	// GET CURRENT CREDIT CODE AND VALUE OF EMPLOYEE

	public Object[][] getCredit(String emp_id) {
		Object[][] credit_amount = null;
		try {

			String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, CREDIT_AMT  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
					+ emp_id + "'  ) order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {

		}
		return credit_amount;

	}
	public Object[][] getCredit(String emp_id,String month,String year) {
		Object[][] credit_amount = null;
		Object[][] credit_sal_amount = null;
		
		try {

			String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, CREDIT_AMT  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
					+ emp_id + "'  ) order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			credit_amount = getSqlModel().getSingleResult(selectCredits,new Object[0][0]);
		} catch (Exception e) {

		}
		String selectDeductCredit = "SELECT CREDIT_CODE,NVL(CREDIT_AMT,0) FROM HRMS_SAL_DEDUCTION_"+ year 
						+" WHERE EMP_ID='"+ emp_id + "' AND MTH='" + month + "' AND DEBIT_CODE IS NULL ORDER BY CREDIT_CODE ";
		Object [][] deduct_amount = getSqlModel().getSingleResult(selectDeductCredit,new Object[0][0]);
		
		if(credit_amount !=null){
			credit_sal_amount = new Object[credit_amount.length][2];
			for (int i = 0; i < credit_amount.length; i++) {
				credit_sal_amount [i][0] =credit_amount[i][0];
				credit_sal_amount [i][1] =credit_amount[i][1];
				if(deduct_amount !=null || deduct_amount.length != 0 ){
					for (int j = 0; j < deduct_amount.length; j++) {
						if(String.valueOf(credit_sal_amount[i][0]).equals(String.valueOf(deduct_amount[j][0]))){
							credit_sal_amount [i][1] =Float.parseFloat(String.valueOf(credit_sal_amount [i][1]))
													+Float.parseFloat(String.valueOf(deduct_amount [j][1]));
						}
					}
				}
			}
			
		}
		return credit_sal_amount;

	}

	// GET DEBIT CODE AND VALUE OF PROCESSED SALARY OF EMPLOYEE
	public Object[][] getSalDebit(String emp_id, String month, String year) {
		Object[][] debit_amount = null;
		

		try {
			String selectDebits = "SELECT  SAL_DEBIT_CODE ,NVL(SAL_AMOUNT,0)   FROM HRMS_SAL_debiTS_"
					+ year
					+ " WHERE EMP_ID='"
					+ emp_id
					+ "' AND SAL_MONTH='"
					+ month + "' ORDER BY SAL_DEBIT_CODE "  ;
			Object[][] debit_sal_amount = getSqlModel().getSingleResult(selectDebits,new Object[0][0]);

			/*
			 * GET INSTALLMENT AMOUNT OF EMPLOYEE FOR SOME DEBITS THAT VALUES
			 * ARE NOT IN CURRENT DEBIT TABLE
			 */
			
			String selectinstallment = "SELECT LOAN_TYPE,NVL((INSTALL_MONTHLY_PRINCIPAL+INSTALL_MONTHLY_INTEREST),0) as loan "
											+" FROM HRMS_EMP_INSTALLMENTS WHERE  EMP_ID="+ emp_id;

		Object loanAmount[][] = getSqlModel().getSingleResult(selectinstallment);
		
		if(debit_sal_amount !=null){
			debit_amount = new Object[debit_sal_amount.length][2];
			for (int i = 0; i < debit_sal_amount.length; i++) {
				debit_amount [i][0] =debit_sal_amount[i][0];
				debit_amount [i][1] =debit_sal_amount[i][1];
				
				for (int j = 0; j < loanAmount.length; j++) {
					if(String.valueOf(debit_amount[i][0]).equals(String.valueOf(loanAmount[j][0]))){
						debit_amount [i][1] =Float.parseFloat(String.valueOf(debit_amount [i][1]))
												+Float.parseFloat(String.valueOf(loanAmount [j][1]));
					}
				}
			}
			
		}
			
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return debit_amount;

	}

	// GET CURRENT DEBIT CODE AND VALUE OF EMPLOYEE
	public Object[][] getDebit(String emp_id) {
		Object[][] debit_amount = null;

		try {
			String selectDebits = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, DEBIT_AMT  FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id + "' )  order BY HRMS_DEBIT_HEAD.DEBIT_CODE";
			debit_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return debit_amount;

	}
	public Object[][] getDebit(String emp_id,String month, String year,Object[][] credit_amount) {
		Object[][] total_debit_amount = null;

		try {
			String selectDebits = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0)  FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id + "' )  order BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY";
			Object[][] debit_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);
			
			String selectDeductDebits = "SELECT DEBIT_CODE,NVL(DEBIT_AMT,0) FROM HRMS_SAL_DEDUCTION_"+ year 
					+" WHERE EMP_ID='"+ emp_id + "' AND MTH='" + month + "' AND CREDIT_CODE IS NULL ORDER BY DEBIT_CODE ";

		
			Object[][] deduct_amount = getSqlModel().getSingleResult(selectDeductDebits,
				new Object[0][0]);

			String selectinstallment = "SELECT LOAN_TYPE,NVL((INSTALL_MONTHLY_PRINCIPAL+INSTALL_MONTHLY_INTEREST),0) as loan FROM HRMS_EMP_INSTALLMENTS WHERE EMP_ID=" + emp_id;

			Object[][] loan_amount = getSqlModel().getSingleResult(selectinstallment);
			
			String absenceQuery = "SELECT ATT_EMP_ID,COUNT(ATT_EMP_ID) FROM  HRMS_DAILY_ATTENDANCE_"+ year
			+ " WHERE ATT_EMP_ID="+ emp_id
			+ " AND ATT_STATUS='AA' AND  TO_CHAR(ATT_DATE,'MM') ='"+ month + "' "
			+" AND ATT_STATUS !='LL' GROUP BY ATT_EMP_ID ";

			Object[][] absenceDays = getSqlModel().getSingleResult(absenceQuery);
			
			float lopAmount =0;
			
				/**
				 * 	Calculate TotalCredit Amount
				 */
			
			float totalCreditAmount =0;
			if(credit_amount !=null){
				for (int i = 0; i < credit_amount.length; i++) {
					totalCreditAmount +=Float.parseFloat(String.valueOf(credit_amount[i][1]));
				}
			}
			
			/**
			 * CALCULATE LOSOFPAY ACORDING TO FORMULA-- ((basic+da+dp)*absenceDays))/DaysMonth
			 */
			
			if(absenceDays != null){
				try{
					if(absenceDays[0][1] == null){
						absenceDays[0][1] = "0";
					}
					int days =Integer.parseInt(String.valueOf(absenceDays[0][1]));
					Calendar cal = Calendar.getInstance();
					cal.setTime(Utility.getDate(01+"-"+month+"-"+year));
					int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
					float result = Float.parseFloat(String
							.valueOf(credit_amount[0][1]))
							+ Float.parseFloat(String
								.valueOf(credit_amount[1][1]))
							+ Float.parseFloat(String
								.valueOf(credit_amount[2][1]));
							lopAmount = (result * days )/ daysOfMonth;
				}catch (Exception e) {
					
				}
			}
			float totalDebit = 0 ;
			boolean flag = true ;
			if(debit_amount !=null){
				total_debit_amount = new Object[debit_amount.length][2];
				for (int i = 0; i < debit_amount.length; i++) {
					total_debit_amount[i][0] = debit_amount[i][0];
					total_debit_amount[i][1] = debit_amount[i][1];
					if(i==0){
						total_debit_amount[i][1] =lopAmount;
					}
					if(deduct_amount !=null || deduct_amount.length != 0 ){
						for (int j = 0; j < deduct_amount.length; j++) {
							if(String.valueOf(debit_amount[i][0]).equals(String.valueOf(deduct_amount[j][0]))){
								total_debit_amount[i][1] = Float.parseFloat(String.valueOf(total_debit_amount[i][1]))
															+Float.parseFloat(String.valueOf(deduct_amount[j][1]));
								//logger.info("total_debit_amount[i][1]*********"+String.valueOf(total_debit_amount[i][1]));
							}		//end inner if
						}			// end inner for
					}				// end 2nd inner if
					
					if(loan_amount !=null || loan_amount.length !=0 ){
						for (int j = 0; j < loan_amount.length; j++) {
							if(String.valueOf(debit_amount[i][0]).equals(String.valueOf(loan_amount[j][0]))){
								total_debit_amount[i][1] =Float.parseFloat(String.valueOf(total_debit_amount[i][1]))
															+Float.parseFloat(String.valueOf(loan_amount[j][1]));
								//logger.info("total_debit_amount[i][1]*********"+String.valueOf(total_debit_amount[i][1]));
							}
						}
					}
					totalDebit +=Float.parseFloat(String.valueOf(total_debit_amount[i][1])); 
					if(totalDebit > totalCreditAmount){
						if(flag){
							float extraDebit =totalDebit - totalCreditAmount;
							total_debit_amount[i][1]=Float.parseFloat(String.valueOf(total_debit_amount[i][1]))-extraDebit;
						//	totalDebit = totalCreditAmount ;
						}else{
							total_debit_amount[i][1]=0;
						}
						flag = false;
					}
				} // end of for-loop
			}   //end 1st if-cond
		} catch (Exception e) {
			 e.printStackTrace();
		}

		return total_debit_amount;

	}
/**
 * 			METHOD CALL BY lockRow() ---FOR CALCULATE  ORIGINAL DEBIT
 * @param emp_id
 * @param month
 * @param year
 * @return
 */
	public Object[][] getOriginalDebit(String emp_id,String month, String year) {
		Object[][] total_debit_amount = null;

		try {
			String selectDebits = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0)  FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id + "' )  order BY HRMS_DEBIT_HEAD.DEBIT_CODE ";
			Object[][] debit_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);
			
			String selectDeductDebits = "SELECT DEBIT_CODE,NVL(DEBIT_AMT,0) FROM HRMS_SAL_DEDUCTION_"+ year 
					+" WHERE EMP_ID='"+ emp_id + "' AND MTH='" + month + "' AND CREDIT_CODE IS NULL ORDER BY DEBIT_CODE ";

		
			Object[][] deduct_amount = getSqlModel().getSingleResult(selectDeductDebits,
				new Object[0][0]);

			String selectinstallment = "SELECT LOAN_TYPE,NVL((INSTALL_MONTHLY_PRINCIPAL+INSTALL_MONTHLY_INTEREST),0) as loan FROM HRMS_EMP_INSTALLMENTS WHERE EMP_ID=" + emp_id;

			Object[][] loan_amount = getSqlModel().getSingleResult(selectinstallment);
			
		/*	String absenceQuery = "SELECT ATT_EMP_ID,COUNT(ATT_EMP_ID) FROM  HRMS_DAILY_ATTENDANCE_"+ year
										+ " WHERE ATT_EMP_ID="+ emp_id
										+ " AND ATT_STATUS='AA' AND  TO_CHAR(ATT_DATE,'MM') ='"+ month + "' "
										+" AND ATT_STATUS !='LL' GROUP BY ATT_EMP_ID ";
			
			Object[][] absenceDays = getSqlModel().getSingleResult(absenceQuery);
			
			float lopAmount =0;
			
			if(absenceDays != null){
				int days =Integer.parseInt(String.valueOf(absenceDays[0][1]));
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate(01+"-"+month+"-"+year));
				int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				float result = Float.parseFloat(String
						.valueOf(credit_amount[0][1]))
						+ Float.parseFloat(String
							.valueOf(credit_amount[1][1]))
						+ Float.parseFloat(String
							.valueOf(credit_amount[2][1]));
						lopAmount = (result * days )/ daysOfMonth;
			}
			*/
			
			if(debit_amount !=null){
				total_debit_amount = new Object[debit_amount.length][2];
				for (int i = 0; i < debit_amount.length; i++) {
					total_debit_amount[i][0] = debit_amount[i][0];
					total_debit_amount[i][1] = debit_amount[i][1];
					/*if(i==0){
						total_debit_amount[i][1] =lopAmount;
					}*/
					if(deduct_amount !=null || deduct_amount.length != 0 ){
						for (int j = 0; j < deduct_amount.length; j++) {
							if(String.valueOf(debit_amount[i][0]).equals(String.valueOf(deduct_amount[j][0]))){
								total_debit_amount[i][1] = Float.parseFloat(String.valueOf(total_debit_amount[i][1]))
															+Float.parseFloat(String.valueOf(deduct_amount[j][1]));
								//logger.info("total_debit_amount[i][1]*********"+String.valueOf(total_debit_amount[i][1]));
							}
						}
					}
					// tp be done later
					if(loan_amount !=null || loan_amount.length !=0 ){
						for (int j = 0; j < loan_amount.length; j++) {
							if(String.valueOf(debit_amount[i][0]).equals(String.valueOf(loan_amount[j][0]))){
								total_debit_amount[i][1] =Float.parseFloat(String.valueOf(total_debit_amount[i][1]))
															+Float.parseFloat(String.valueOf(loan_amount[j][1]));
								//logger.info("total_debit_amount[i][1]*********"+String.valueOf(total_debit_amount[i][1]));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}

		return total_debit_amount;

	}

	/*
	 * THIS INSTALLMENT AMOUNT
	 * 
	 */
	public float getInstallments(String emp_id, String loan_type) {
		Object[][] emp = null;
		float ins = 0;

		try {

			String selectinstallment = "SELECT (INSTALL_MONTHLY_PRINCIPAL+INSTALL_MONTHLY_INTEREST) as loan FROM HRMS_EMP_INSTALLMENTS WHERE LOAN_TYPE="
					+ loan_type + " and EMP_ID=" + emp_id;

			/*
			 * GET INSTALLMENT AMOUNT OF EMPLOYEE FOR SOME DEBITS THAT VALUES
			 * ARE NOT IN CURRENT DEBIT TABLE
			 * 
			 */

			emp = getSqlModel().getSingleResult(selectinstallment);
			//logger.info(emp[0][0]);
			ins = Float.parseFloat(String.valueOf(emp[0][0]));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ins;
	}

	/*
	 * VIEW THE PROCESSES SALARY OF EMPLOYEES IN WHICH SET DEBIT HEADER AND
	 * CREDIT HEADER IS SETTED
	 * 
	 */
	public Object[][]  getSalaryAudit(String emp_id,String month,String year){
		//logger.info("\n\n\n*******************AUDIT*******************");
		String SQL="SELECT SAL_AUDIT FROM HRMS_SALARY_"+year+" WHERE SAL_MONTH="+month+
		" AND EMP_ID="+emp_id;
		Object data[][]=getSqlModel().getSingleResult(SQL);
		return data;
	}
	public Object[][] viewSalary(IndustrialSalaryAudit industrialSalary,HttpServletRequest request) {

		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		Object emp_id[][] = getEmpId(industrialSalary);
		ArrayList<IndustrialSalaryAudit> creditNames = new ArrayList<IndustrialSalaryAudit>();
		ArrayList<IndustrialSalaryAudit> debitNames = new ArrayList<IndustrialSalaryAudit>();

		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 * 
			 */
			IndustrialSalaryAudit creditName = new IndustrialSalaryAudit();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);

		}

		industrialSalary.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
			 * 
			 */
			IndustrialSalaryAudit debitName = new IndustrialSalaryAudit();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);

		}

		industrialSalary.setDebitHeader(debitNames);

		int c = debit_header.length + credit_header.length;
		/*
		 * r IS TOTAL NO OF EMPLOYEES THAT MAKES TOTAL ROWS
		 * 
		 */
		int r = emp_id.length;
		Object[][] rows = new Object[r][];
		HashMap hmAudit=new HashMap();
		for (int i = 0; i < emp_id.length; i++) {
			/*
			 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE THAT ARE SAVED
			 * IN DATABASE MEANS THOSE SALARIES HAS BEEN PROCESSED AND SET EACH
			 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
			 * TOTAL NO OF EMPLPYEE
			 */
			for(int j=i*2;j<=(i*2)+1;j++ ){
				
				if(j%2==0){
						Object[][] row = getSalRow(String.valueOf(emp_id[i][0]), String
								.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
								String.valueOf(Integer.parseInt(industrialSalary.getMonth())-1), industrialSalary
								.getYear(),credit_header,debit_header);
						try{
							rows[j]   = row[0];
							///GET AUDIT 
							Object[][] auditObj = getSalaryAudit(String.valueOf(emp_id[i][0]), industrialSalary.getMonth(),  industrialSalary.getYear());
							String audit="";
							if(auditObj.length>0){
								audit=String.valueOf(auditObj[0][0]);							
								hmAudit.put(""+j,audit);
							} else{
								hmAudit.put(""+j,audit);
							}	
						}catch(Exception e){
							e.printStackTrace();
						}
											
				}else{				
						Object[][] row1 = getSalRow(String.valueOf(emp_id[i][0]), String
								.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
								industrialSalary.getMonth(), industrialSalary
								.getYear(),credit_header,debit_header);
						try{
							rows[j]   = row1[0];
							///GET AUDIT
							Object[][] auditObj = getSalaryAudit(String.valueOf(emp_id[i][0]), String.valueOf(Integer.parseInt(industrialSalary.getMonth())-1),  industrialSalary.getYear());
							String audit="";
							if(auditObj.length>0){
								audit=String.valueOf(auditObj[0][0]);							
								hmAudit.put(""+j,audit);
							} else{
								hmAudit.put(""+j,audit);
							}							
						}catch(Exception e){
							e.printStackTrace();
						}						
				}
				
			}

		}
		
		//logger.info("ROW COUNT---->"+rc);
		logger.info("ROW COUNT2---->"+rows[0].length);
		logger.info("ROW COUNT2---->"+rows[0][61]);
		
				
		///COMPARE NET SALARY OF 2 MONTHS FOR AN EMPLOYEE
		int rc=0;
		for(int l=0;l<rows.length;l++){
			for(int m=0;m<rows.length;m++){
				//logger.info("rows[l][0]->"+rows[l][0]+"rows[m][0]->"+rows[m][0]+"l-->"+l+"m-->"+m+"-->"+rows[l][60]+"-->"+rows[m][60]);
				if((rows[l][0]==rows[m][0])  && !(l==m) ){
					if(!String.valueOf(rows[l][61]).equals(String.valueOf(rows[m][61]))){
						rc++;
					}
				}
			}
		}
		

		logger.info("rc--->"+rc);
		
			
		Object rowData[][]=new Object [rc][];
		Object rowMark[][]=new Object [rc][62];
		int count=0;
		
		for(int l=0;l<rows.length;l++){
			for(int m=0;m<rows.length;m++){
				if((rows[l][0]==rows[m][0])  && !(l==m) ){
					if(!String.valueOf(rows[l][61]).equals(String.valueOf(rows[m][61]))){					
						rowData[count]=rows[m];
						rowData[count++]=rows[l];
					}
				}
			}
		}
	
		/*
		count=0;
		int count1=0;
		for(int l=0;l<rowData.length;l++){
			for(int m=0;m<rowData.length;m++){
				if((rowData[l][0]==rowData[m][0])  && !(l==m) ){
					//if(!String.valueOf(rowData[l][61]).equals(String.valueOf(rowData[m][61]))){						
						
						for(int n=0;n<62;n++){
								if(!String.valueOf(rowData[m][n]).equals(String.valueOf(rowData[l][n]))){
									rowMark[count][n]  ="class='text'";
									rowMark[count++][n]= "class='text'";
								}else{
									rowMark[count][n]  ="" ;
									rowMark[count++][n]= "" ;
							}
						}
						
					//}
				}				
			}
		}

*/
		//request.setAttribute("MARKER", rowMark);
		//return rows;*/
		return rowData;

	}

	public Object[][] getSalRow(String emp_id, String emp_token,
			String emp_name, String month, String year,Object debitLength[][],Object creditLength[][]) {
		
		Object [][] otData=null;
		float otAmount=0;
		Object[][] debit_amount = getSalDebit(emp_id, month, year);
		
		/*
		 *  MONTH OF SALARY HAS BEEN PROCESSED  
		 * AND WE ARE FATCHING DEBIT FROM HRMS_SAL_DEBITS TABLE
		 */

		Object[][] credit_amount = getSalCredit(emp_id, month, year);
		/*
		 *  MONTH OF SALARY HAS BEEN PROCESSED  
		 * AND WE ARE FATCHING CREDIT FROM HRMS_SAL_CREDITS TABLE
		 */
		Object[][] total_amount = null;

		float totalCredit = 0;
		float totalDebit = 0;
		float netPay = 0;
		float creditamt = 0;
		int lenDebit = 0;
		/*
		 * TOTAL NO OF COULUM THAT WILL BE IN A ROW 
		 * DUE TO EXTRA COULUM OF EMP_ID , EMP_TOKEN , EMPLOYEE NAME , TOTAL CREDIT , TOTAL DEBIT AND NET PAY
		 * SO COULUM NO IS INCREASE BY 6 
		 */
		//int total_coulum = creditLength.length + debitLength.length + 7;
		int total_coulum = creditLength.length + debitLength.length + 8;

		total_amount = new Object[1][total_coulum];
		try {
			int indx=Integer.parseInt(month);
			String MONTH[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
			total_amount[0][total_coulum-1] = MONTH[indx-1];
			logger.info("MONTH--->"+total_amount[0][total_coulum-1]);
		} catch (Exception e) {
			//logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int c = 0;
			/*
			 * FOR LOOP IS DECREMENT BY 5 BECOZ WE ALREADY SET THE EMP_ID , EMP_TOKEN AND EMP_NAME IN ROW 
			 * AND AFTER THE LOOP COMPLETION THREE MORE FIELD HAS ADD LIKE TOTAL CREDIT , TOTAL DEBIT AND NET PAY
			 * 
			 */
			for (int j = 0; j < total_coulum - 6; j++) {

				if (j < creditLength.length) {
					/*
					 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
					 * 
					 */
					try {

						total_amount[0][j + 3] = "0";
						try {
							/*
							 * FILTER THE NULL AMOUNT IF 
							 * IF CREDIT AMOUNT IS NULL IT WILL TREATED AS ZERO 
							 */
							if (credit_amount[c][1] != null)
								total_amount[0][j + 3] = credit_amount[c][1];
							/*
							 * ADD THE CREDIT INTO TOTAL CREDIT VARIABLE 
							 * 
							 */
							totalCredit = totalCredit
									+ Integer.parseInt(String
											.valueOf(total_amount[0][j + 3]));
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						System.out
								.println("Error in if  loop which is credit ");
					}
				} else if (j == creditLength.length) {
					/*
					 * THIS IS THE PALACE WHERE TOTAL CREDIT WILL TAKE PLACE 
					 * AFTER ALL CREDIT 
					 * 
					 */
					total_amount[0][j + 3] = totalCredit;
					creditamt = totalCredit;
				} else if (j > creditLength.length) {
			
					total_amount[0][j + 3] = "0";
					try {
						if (debit_amount[k][1] != null)
							/*
							 * FOR FILTERING NULL VALUES FROM DATA IF DATA
							 * IS NULL IT WILL TREATED AS O VALUES
							 */
							total_amount[0][j + 3] = debit_amount[k][1];
						//logger.info("total_amount[0][j + 3]*********"+String.valueOf(total_amount[0][j + 3]));
						totalDebit = totalDebit
								+ Float.parseFloat(String.valueOf(total_amount[0][j + 3]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					k++;
				}
				netPay = totalCredit - totalDebit;

				/*
				 * CALCULATION OF NET PAY
				 * 
				 *
				 *
				 */
				//logger.info("toatal debit"+totalDebit);
				total_amount[0][j + 4] = String.valueOf(totalDebit);
				
				
				float otSingleAmount=0;
				float otDoubleAmount=0;
				float otsingleArrear=0;
				try
				{
					String otSelect ="SELECT  OT_SINGLE_AMOUNT , OT_DOUBLE_AMOUNT , OT_ARR_SINGLE_AMOUNT FROM HRMS_OT  WHERE OT_MONTH='"+month+"' AND  OT_YEAR='"+year+"' AND EMP_ID='"+emp_id+"'";
					otData = getSqlModel().getSingleResult(otSelect);
					
					try
					{
						otSingleAmount=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					try
					{
						otDoubleAmount=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					try
					{
						otsingleArrear=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					otAmount=otSingleAmount+otDoubleAmount+otsingleArrear;
					total_amount[0][j + 5] = String.valueOf(otAmount);
					//logger.info("ot amopunr "+otAmount);
				}
				catch(Exception e)
				{
				//	logger.info("Error in OT calculation "+e);
				}
			
				//logger.info(otAmount+"calculated by Niraj Kumar"+total_amount[0][j + 5]);
				total_amount[0][j + 6] = String.valueOf(netPay+otAmount);
				//logger.info(netPay+otAmount+"netPay+otAmount");
				
			

			}

		} catch (Exception e) {
			//logger.info("Error is nothing " + e);
		}
		 
		return total_amount;

	}

	public Object[][] getRow(String emp_id, String emp_token, String emp_name,
			String month, String year,Object debitLength[][],Object creditLength[][] ) {

		Object [][] otData=null;
		float otAmount=0;
		/*
		 * THIS IS ORIGINAL DEBIT AMOUNT ON WHICH MANUPLILATION HAS TO DO
		 * 
		 */
		Object[][] credit_amount = getCredit(emp_id,month,year);
		
		Object[][] debit_amount = getDebit(emp_id,month,year,credit_amount);
		
		
		/*
		 * THIS GIVES CREDIT LENGTH ON WHICH WE WILL ITERATE IN FOR LOOP FOR
		 * SETING VALUE OF CREDIT
		 * 
		 */
		Object[][] total_amount = null;

		float totalCredit = 0;
		float totalDebit = 0;
		float netPay = 0;
		float creditamt = 0;
		int lenDebit = 0;
		int lopDays = 0;
		int totalDays = 30;

		/*
		 * 
		 * 
		 */
		int total_coulum = creditLength.length + debitLength.length + 7;
		/*
		 * TOTAL NO OF VARIABLES THAT HAS BEEN USED IN FOR LOOP FOR SETTING
		 * CREDITS , TOTAL CREDIT , DEBITS , TOTAL DEBIT AND NET PAY
		 * 
		 */

		total_amount = new Object[1][total_coulum];
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {
			//logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int c = 0;
			for (int j = 0; j < total_coulum - 6; j++) {
				
				if (j < creditLength.length) {
				/*
				 * TO DISPLAY INDIVIDUAL CREDITS
				 * 
				 */
				try {

					total_amount[0][j + 3] = "0";
					try {
						if (credit_amount[c][1] != null)
							/*
							 * FOR FILTERING NULL VALUES FROM DATA IF DATA
							 * IS NULL IT WILL TREATED AS O VALUES
							 */
							total_amount[0][j + 3] = credit_amount[c][1];
						totalCredit = totalCredit
								+ Integer.parseInt(String
										.valueOf(total_amount[0][j + 3]));
					} catch (Exception e) {

					}
					c++;
				} catch (Exception e) {
					System.out
							.println("Error in if  loop which is credit ");
				}

			} else if (j == creditLength.length) {
				/*
				 * TO DISPALY TOTAL CREDIT WHEN ALL CREDIT PLACED ON THEIR
				 * POSITION THEN TOTAL CREDIT FIELD WILL BE FILLED //
				 * 
				 */
				total_amount[0][j + 3] = totalCredit;
				creditamt = totalCredit;
			} else if (j > creditLength.length) {
			
					total_amount[0][j + 3] = "0";
					try {
						if (debit_amount[k][1] != null)
							/*
							 * FOR FILTERING NULL VALUES FROM DATA IF DATA
							 * IS NULL IT WILL TREATED AS O VALUES
							 */
							total_amount[0][j + 3] = debit_amount[k][1];
						//logger.info("total_amount[0][j + 3]*********"+String.valueOf(total_amount[0][j + 3]));
						totalDebit = totalDebit
								+ Float.parseFloat(String.valueOf(total_amount[0][j + 3]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					k++;
				}
				netPay = totalCredit - totalDebit;

				/*
				 * CALCULATION OF NET PAY
				 * 
				 */

				total_amount[0][j + 4] = String.valueOf(totalDebit);
				
				
				float otSingleAmount=0;
				float otDoubleAmount=0;
				float otsingleArrear=0;
				try
				{
					String otSelect ="SELECT  OT_SINGLE_AMOUNT , OT_DOUBLE_AMOUNT , OT_ARR_SINGLE_AMOUNT FROM HRMS_OT  WHERE OT_MONTH='"+month+"' AND  OT_YEAR='"+year+"' AND EMP_ID='"+emp_id+"'";
					otData = getSqlModel().getSingleResult(otSelect);
					
					try
					{
						otSingleAmount=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					try
					{
						otDoubleAmount=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					try
					{
						otsingleArrear=Float.parseFloat(String.valueOf(otData[0][0]));
					}
					catch(Exception e)
					{
						
					}
					otAmount=otSingleAmount+otDoubleAmount+otsingleArrear;
					total_amount[0][j + 5] = String.valueOf(otAmount);
				}
				catch(Exception e)
				{
					logger.info("Error in OT calculation "+e);
				}
			
				logger.info(otAmount+"calculated -------"+total_amount[0][j + 5]);
				total_amount[0][j + 6] = String.valueOf(netPay+otAmount);
				
				if (totalDebit > totalCredit) {

					total_amount[0][j + 4] = String.valueOf(totalCredit);
					/*
					 * IF DEBIT IS GREATER THEN CREDIT THEN HIS NET PAY WILL
					 * ZERO
					 * 
					 */
					total_amount[0][j + 6] = String.valueOf(otAmount);
				}

			}

		} catch (Exception e) {
			logger.info("Error is nothing " + e);
		}
		logger.info("total credit" + totalCredit);
		logger.info("total debit" + totalDebit);
		return total_amount;

	}

	/*
	 * SAVE THE CURRENT CREDIT AND DEBIT OF EMPLOYEE INTO SALARIED TABLE
	 * 
	 */

	public boolean save(Object[][] rows, Object[][] d, Object[][] c,
			String[] emp_id, String month, String year, String[] total_credit,
			String[] total_debit,String [] ot_amount) {
		boolean record=false;
		try {
			boolean update = false;
			Object[][] updateData = new Object[emp_id.length][5];
			Object[][] deleteData = new Object[emp_id.length][2];
			
			String deleteQuery = "DELETE FROM HRMS_SALARY_"+year 
									+"  WHERE SAL_MONTH=? AND EMP_ID=?";
			String insertQuery = "INSERT INTO HRMS_SALARY_"+year 
								+" (SAL_MONTH ,emp_id , SAL_TOTAL_DEBIT ,SAL_TOTAL_CREDIT,SAL_OT_PAY ) VALUES(?,?,?,?,?)";

			Object[][] deleteCredit = new Object[emp_id.length][2];
			
			String deleteCreditQuery = "DELETE FROM HRMS_sal_CREDITs_"+ year 
													+"  WHERE SAL_MONTH=? AND EMP_ID=?";
			String deleteDebitQuery  = "DELETE FROM HRMS_SAL_DEBITS_"+ year 
													+" WHERE SAL_MONTH=? AND EMP_ID=?";
			for (int i = 0; i < emp_id.length; i++) {
				deleteCredit[i][0] = month;
				deleteCredit[i][1] = emp_id[i];
			}
			
			update = getSqlModel().singleExecute(deleteCreditQuery, deleteCredit);
			update = getSqlModel().singleExecute(deleteDebitQuery, deleteCredit);
			
			for (int i = 0; i < emp_id.length; i++) {

				Object CreditData[][] = new Object[c.length][3];

				String insertCredit = "INSERT INTO HRMS_sal_CREDITs_"
						+ year
						+ "(EMP_ID , SAL_CREDIT_CODE ,sal_AMounT,SAL_MONTH) VALUES(?,?,?,'"
						+ month + "')";
				Object DebitData[][] = new Object[d.length][3];
				String insertDebit = "INSERT INTO HRMS_SAL_DEBITS_"
						+ year
						+ " (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_MONTH) VALUES(?,?,?,'"
						+ month + "')";

				int k = 0;

				boolean chkupdateCredit = false;
				boolean chkUpdateDebit = false;

				for (int j = 0; j < c.length + d.length; j++) {
					if (j < c.length) {				
								
								logger.info("for insert");
								CreditData[j][0] = emp_id[i]; 	// emp_id
								CreditData[j][1] = c[j][0]; 	// credit_code
								CreditData[j][2] = rows[i][j];	 // amount
	
					} else {
								
								
								DebitData[k][0] = emp_id[i];
								DebitData[k][1] = d[k][0];
								DebitData[k][2] = rows[i][j];
							

							k++;
							
					}
						

				}// this is end of c.length + d.length for loop
					
					try {
						System.out
								.println("<----------Insert---------------->");
						getSqlModel().singleExecute(insertCredit, CreditData);
					} catch (Exception e) {
						logger.info("Exception in insert query " + e);
					}
				try {
	
						getSqlModel().singleExecute(insertDebit, DebitData);
				
				} catch (Exception e) {
					e.printStackTrace();
				}

					

					deleteData[i][0] = month;
					deleteData[i][1] = emp_id[i];
					
					updateData[i][0] = month;
					updateData[i][1] = emp_id[i];
					updateData[i][2] = total_debit[i];
					updateData[i][3] = total_credit[i];
					updateData[i][4] = ot_amount[i];
				

			}// end of for loop which has emp_id.length condition

			// now its a insertion or updation of total_credit , total_debit .
			// month and year
			
				try {

					getSqlModel().singleExecute(deleteQuery, deleteData);
					record=true;
				} catch (Exception e) {
					System.out
							.println("Exception in update total salary query "
									+ e);
			
			}
				try {

					getSqlModel().singleExecute(insertQuery, updateData);
					record=true;
					
			/*		String lockQuery ="INSERT INTO HRMS_SAL_LEDGER (SAL_LOCK,SAL_MONTH,,SAL_YEAR ,SAL_EMP_TYPE ,SAL_PAYBILL)"
						+" ('P',?,?,?,?)";
				Object [][] insertLock= new Object[1][4];
				insertLock[0][0] = nonIndustrialSalary.getMonth();
				insertLock[0][1] = nonIndustrialSalary.getYear();
				insertLock[0][2] = nonIndustrialSalary.getTypeCode();
				insertLock[0][3] = nonIndustrialSalary.getPayBillNo();
				getSqlModel().singleExecute(lockQuery, insertLock);	*/
				} catch (Exception e) {
					System.out
							.println("Exception in insert  total salary  query "
									+ e);
				}
		}catch (Exception e) {
			
		}

		return record;
	}

	// HRMS_SAL_DEDUCTION(MTH, YEAR, EMP_ID, DEBIT_CODE, DEBIT_AMT

	// GET DEDUCTED AMOUNT OF EMPLOYEE THAT HAS BEEN DEDUCTE DFROM EMPLOYEE
	// SALARY IN PREVIUS MONTH

	public Object[][] getDeductionData(String emp_id, String month,
			String year, String debitCode) {
		Object[][] debit_amount = null;

		try {
			String selectDebits = "SELECT  DEBIT_AMT    FROM HRMS_SAL_DEDUCTION_"+year	
				+" WHERE EMP_ID='"+ emp_id+ "' AND MTH='"
					+ month
					+ "' and YEAR='"
					+ year
					+ "' and DEBIT_CODE=" + debitCode + "";
			/*
			 * GIVES A AMOUNT THAT HAS WILL TREATED AS DEBIT
			 * 
			 */
			debit_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return debit_amount;

	}

	/*
	 * LOCK IS USED FOR SAVING THE EMPLOYEES DATA THOSE DON'T HAVE GREATER OR
	 * EQUAL DEBIT AMOUNT AS DEBIT AMOUNT
	 * 
	 */

	public boolean lockRow(String emp_id, String emp_token, String emp_name,
			String month, String year) {
	//	Object[][] credit_amount = getCredit(emp_id,month,year);
		Object[][] debit_amount = getOriginalDebit(emp_id, month, year);
		Object[][] sal_debit_amount = getSalDebit(emp_id, month, year) ;
	 
		boolean lock=false;
	//	Object debitLength[][] = getDebitHeader();
		 
		Object[][] total_amount = null;

		
	 
		
		int lenDebit = 0;

		int total_coulum =debit_amount.length;

		/*
		 * TOTAL NO OF COLOUM THAT WILL AVAiLABLE ON WEB PAGE
		 * 
		 */

		 
		 
		try {
	//		int k = 0;
			 
			for (int j = 0; j < total_coulum ; j++) { 
				 
				/*	
					 * PLACE FOR SETTING DEBIT AMOUNT
					 * 
					 
					float deduct = 0;

					try {
						try {
							
							 * LOOKING FOR DEDUCTION DATA THAT IS REST AMOUNT OF
							 * LAST MONTH
							 * 
							 
							try
							{
							Object DeductionData[][] = getDeductionData(emp_id,
									month, year, String
											.valueOf(debitLength[lenDebit][0]));
							deduct = Float.parseFloat(String
									.valueOf(DeductionData[0][0]));
							logger.info(deduct+"deduction data f0r code "+String.valueOf(debitLength[lenDebit][0]));
							}
							catch(Exception e)
							{
								
							}
						} catch (Exception e) {
							logger.info("No deduction data for "
									+ debitLength[lenDebit][0]);
						}
						
						
						
						float installmentAmount=0;
						try {
						 
								  installmentAmount = getInstallments(emp_id, String
										.valueOf(debitLength[lenDebit][0])); 
								  logger.info(installmentAmount+"installmentAmount data f0r code "+String.valueOf(debitLength[lenDebit][0]));
						} catch (Exception e) {

							System.out
									.println("There is no installments fOR THE DEBIT code "
											+ debitLength[lenDebit][0]);
						}
						*/
						

						if ((debit_amount[j][1] == null)) {
							/*
							 * FILTERATION FOR NULL DATA
							 */
							debit_amount[j][1] = "0";
						}
						
						
						// ADDITION OF DEDUCTIO DATA INTO DEBIT
						float calculatedDebit = Float.parseFloat(String
								.valueOf(debit_amount[j][1]));
								
						
						float originaldDebit = Float.parseFloat(String
								.valueOf(sal_debit_amount[j][1]));
								 

					 
								/*
								 * CHECKING FOR CREDIT IS SUFFICIENT FOR GIVING
								 * THE DEBIT AMOUNT OR NOT
								 * 
								 */
						 
								
								
									float debitToLock =  calculatedDebit-originaldDebit;
									 if(debitToLock>0)
									 {
									try {
										/*
										 * INSERT OF AMOUNT THAT CAN NOT BE PAID
										 * IN CURRENT MONTH
										 * 
										 */
										Object[][] lockData = new Object[1][4];
										lockData[0][0] = (month.equals("12")) ? month="1" : ""+(Integer.parseInt(month)+1);
								//		lockData[0][1] = (month.equals("12")) ? ""+(Integer.parseInt(year)+1) : year;
										String newYear = (month.equals("12")) ? ""+(Integer.parseInt(year)+1) : year;
										lockData[0][1] = emp_id;
										lockData[0][2] = debit_amount[j][0];
										lockData[0][3] = debitToLock;
										String lockInsert = "INSERT INTO HRMS_SAL_DEDUCTION_"
												+ newYear
												+ " (MTH, EMP_ID, DEBIT_CODE, DEBIT_AMT) VALUES(?,?,?,?)";
										getSqlModel().singleExecute(lockInsert, lockData);
									} catch (Exception e) {
										System.out
												.println("can not insert in lock table");
									}
								 }
									 lock = true;
			 
				  }  // end of for loop
			 
		}
			catch(Exception e)
			{
				
			}
		return lock;

	}

	public boolean lockSalary(IndustrialSalaryAudit industrialSalary) {

		boolean lock=false;
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		Object emp_id[][] = getEmpId(industrialSalary);
		ArrayList<IndustrialSalaryAudit> creditNames = new ArrayList<IndustrialSalaryAudit>();
		ArrayList<IndustrialSalaryAudit> debitNames = new ArrayList<IndustrialSalaryAudit>();

		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * PUTTING CREDIT CODE AND ABBR THATS USED AS NAME OF CREDIT ON WEB
			 * PAGE
			 * 
			 */
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 * 
			 */
			IndustrialSalaryAudit creditName = new IndustrialSalaryAudit();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);

		}

		industrialSalary.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING DEBIT HEADER THIS
			 * LOOP IS USED
			 * 
			 */
			IndustrialSalaryAudit debitName = new IndustrialSalaryAudit();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);

		}

		industrialSalary.setDebitHeader(debitNames);

	 
		for (int i = 0; i < emp_id.length; i++) {
			/*
			 * EACH INDIVIUAL employee  debit WILL be selected
			 * 
			 */
			  lock = lockRow(String.valueOf(emp_id[i][0]), String
					.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
					industrialSalary.getMonth(), industrialSalary
							.getYear());
		 

		}
		if(lock){
			String lockQuery = "UPDATE HRMS_SAL_LEDGER SET SAL_LOCK ='L' WHERE  SAL_MONTH =? "
								+" AND SAL_YEAR =? AND SAL_EMP_TYPE =? AND SAL_PAYBILL =? ";
			Object [][] updateLock= new Object[1][4];
			updateLock[0][0] = industrialSalary.getMonth();
			updateLock[0][1] = industrialSalary.getYear();
			updateLock[0][2] = industrialSalary.getTypeCode();
			updateLock[0][3] = industrialSalary.getPayBillNo();
			getSqlModel().singleExecute(lockQuery, updateLock);
		}
		industrialSalary.setDebitHeader(null);
		return  lock;

	}
	
	public void generateReport(IndustrialSalaryAudit industrialSalary,HttpServletResponse response){
	
		HttpServletRequest request=null;
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		
		String[] colNames=new String[credit_header.length+debit_header.length+6];
		int [] cellWidth=new int[credit_header.length+debit_header.length+6];
		Object [][]value = viewSalary(industrialSalary,request);
		
		Object [][]data = new Object[value.length][value[0].length-1];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j]=value[i][j+1];
			}
		}
		
		int [] alignment={1,1,0,1,1,1};
		try
		{
		colNames[0]="TOKEN NO";
		colNames[1]="EMPLOYEE NAME";
		int count = 2;
		
		for (int i = 0; i < credit_header.length; i++) {
			colNames[count]=String.valueOf(credit_header[i][1]);
			count++;
		}
		colNames[count]="TOTAL CREDIT";
		 count = count+1;
		for (int i = 0; i < debit_header.length; i++) {
			colNames[count]=String.valueOf(debit_header[i][1]);
			count++;
		}
		colNames[count]="TOTAL DEBIT";
		colNames[count+1]="OT AMOUNT";
		colNames[count+2]="NET PAY";
		
		for(int i=0;i<colNames.length;i++){ 
			cellWidth[i]=25;
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		String reportName ="Industrial Salary Acquaintance Roll Of"+Utility.month(Integer.parseInt(industrialSalary.getMonth()))+"  -  "+industrialSalary.getYear();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator("Xls",reportName);
		rg.genHeader("");
		rg.tableBody(colNames,data,cellWidth,alignment);
		rg.createReport();
		String file="IndustrialSalaryAcquaintanceRollOf"+Utility.month(Integer.parseInt(industrialSalary.getMonth()))+"-"+industrialSalary.getYear();
		new Report().showReport("C:/"+file+".Xls");
	}

	public boolean saveProcessStatus(IndustrialSalaryAudit bean){
		String lockQuery ="INSERT INTO HRMS_SAL_LEDGER (SAL_LOCK,SAL_MONTH,SAL_YEAR ,SAL_EMP_TYPE ,SAL_PAYBILL)"
						+" VALUES('P',?,?,?,?)";
		Object [][] insertLock= new Object[1][4];
		insertLock[0][0] = bean.getMonth();
		insertLock[0][1] = bean.getYear();
		insertLock[0][2] = bean.getTypeCode();
		insertLock[0][3] = bean.getPayBillNo();
		boolean result =getSqlModel().singleExecute(lockQuery, insertLock);	
		
		return result;
	}
	
	public String checkProcessStatus(IndustrialSalaryAudit bean){
		String lockQuery ="SELECT SAL_LOCK,SAL_MONTH FROM HRMS_SAL_LEDGER WHERE  SAL_MONTH ='"+bean.getMonth()+"' "
								+" AND SAL_YEAR ='"+bean.getYear()+"' AND SAL_EMP_TYPE ='"+bean.getTypeCode()+"' AND SAL_PAYBILL ='"+bean.getPayBillNo()+"' ";
		String check ="";
		try{
		Object[][] result =getSqlModel().getSingleResult(lockQuery);
		
		if(result.length >0){
			check = String.valueOf(result[0][0]);
			
		}
		
		}catch (Exception e) {
		
		}
		return check;
	}
}
