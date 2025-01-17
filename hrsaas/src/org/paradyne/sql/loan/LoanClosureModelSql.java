/**
 * 
 */
package org.paradyne.sql.loan;

import org.paradyne.lib.SqlBase;

/**
 * @author tarunc
 *
 */
public class LoanClosureModelSql extends SqlBase {

	public String getQuery(int id){
		switch (id) {
		case 1 : return "INSERT INTO HRMS_LOAN_CLOSURE (LOAN_CLOSURE_CODE, LOAN_APPL_CODE, LOAN_PREPAY_DATE, "
						+"LOAN_PREPAY_AMOUNT, LOAN_INSTALLMENTS_PENDING, LOAN_PAID_AMOUNT, LOAN_BALANCE_AMOUNT,LOAN_REQ_EMI_AMOUNT,LOAN_MONHLY_PRINCIPAL) "
						+"VALUES ((SELECT NVL(MAX(LOAN_CLOSURE_CODE), 0)+1 FROM HRMS_LOAN_CLOSURE WHERE LOAN_APPL_CODE = ?), "
						+"?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?, ?,?,?)";
		
		case 2 : return "DELETE FROM HRMS_LOAN_CLOSURE WHERE LOAN_APPL_CODE = ? AND LOAN_CLOSURE_CODE = ?";
		
		case 3 : return "SELECT NVL(SUM(LOAN_PRINCIPLE_AMT), 0) "
						+"FROM HRMS_LOAN_INSTALMENT "
						+"WHERE LOAN_APPL_CODE = ? AND LOAN_INSTALLMENT_IS_PAID = 'Y'";
		
		case 4 : return "SELECT MAX(LOAN_CLOSURE_CODE) FROM HRMS_LOAN_CLOSURE WHERE LOAN_APPL_CODE = ?";
		
		case 5 : return "UPDATE HRMS_LOAN_CLOSURE SET LOAN_PREPAY_DATE = TO_DATE(?, 'DD-MM-YYYY'), "
						+"LOAN_PREPAY_AMOUNT = ?, LOAN_INSTALLMENTS_PENDING = ?, LOAN_PAID_AMOUNT = ?, LOAN_BALANCE_AMOUNT = ?,LOAN_REQ_EMI_AMOUNT = ?,LOAN_MONHLY_PRINCIPAL =? "
						+"WHERE LOAN_APPL_CODE = ? AND LOAN_CLOSURE_CODE = ?";
		
		case 6 : return "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
						+"NVL(TO_CHAR(LOAN_SANCTION_DATE, 'DD-MM-YYYY'), ' ') SANCTION_DATE, LOAN_SANCTION_AMOUNT SANCTION_AMOUNT, CENTER_NAME, DEPT_NAME " 
						+"FROM HRMS_LOAN_PROCESS "
						+"INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) " 
						+"INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = LOAN_EMP_ID) " 
						+"INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " 
						+"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+"WHERE HRMS_LOAN_PROCESS.LOAN_APPL_CODE = ?";
		
		case 7 : return "SELECT TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY'), LOAN_PREPAY_AMOUNT "
						+"FROM HRMS_LOAN_CLOSURE "
						+"WHERE LOAN_APPL_CODE = ? "
						+"ORDER BY LOAN_CLOSURE_CODE DESC" ;
		
		case 8 : return "SELECT TO_CHAR(TO_DATE(LOAN_INSTAL_DAY, 'DD'), 'DD') DAY, TO_CHAR(TO_DATE(LOAN_INSTAL_MONTH,'MM'),'MON'), "
						+"LOAN_INSTAL_YEAR, LOAN_PRINCIPLE_AMT, "
						+"LOAN_INSTAL_INTEREST, LOAN_INSTAL_AMT, LOAN_BALANCE_PRINCIPAL, NVL(LOAN_INSTALLMENT_IS_PAID,'N') "
						+"FROM HRMS_LOAN_INSTALMENT "
						+"WHERE LOAN_APPL_CODE=? ORDER BY LOAN_INSTAL_YEAR, LOAN_INSTAL_MONTH";
		
		case 9 : return "INSERT INTO HRMS_LOAN_INSTALMENT (LOAN_APPL_CODE, LOAN_INSTAL_MONTH, LOAN_INSTAL_YEAR, "
						+"LOAN_PRINCIPLE_AMT, LOAN_INSTAL_INTEREST, LOAN_INSTAL_AMT, LOAN_BALANCE_PRINCIPAL, "
						+"LOAN_INSTAL_DAY, LOAN_EMP_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		case 10 : return "SELECT LOAN_INSTAL_DAY DAY, TO_CHAR(TO_DATE(LOAN_INSTAL_MONTH, 'MM'), 'MON') MONTH, "
						 +"LOAN_INSTAL_YEAR YEAR, LOAN_PRINCIPLE_AMT, LOAN_INSTAL_INTEREST, LOAN_INSTAL_AMT, "
						 +"LOAN_BALANCE_PRINCIPAL, LOAN_INSTALLMENT_IS_PAID, TO_CHAR(TO_DATE(LOAN_INSTAL_DAY||'-'||LOAN_INSTAL_MONTH||'-'||LOAN_INSTAL_YEAR, 'DD-MM-YYYY'), 'DD-MM-YYYY') "
						 +"FROM HRMS_LOAN_INSTALMENT " 
						 +"WHERE LOAN_APPL_CODE = ? AND LOAN_INSTALLMENT_IS_PAID = 'Y' "
						 +"ORDER BY LOAN_INSTAL_YEAR ASC, LOAN_INSTAL_MONTH";
		
		case 11 : return "SELECT NVL(SUM(LOAN_PREPAY_AMOUNT), 0) "
						 +"FROM HRMS_LOAN_CLOSURE "
						 +"WHERE LOAN_APPL_CODE = ?";
		
		case 12 : return "SELECT TO_CHAR(LOAN_INSTALLMENT_START_DATE, 'DD'), TO_CHAR(ADD_MONTHS(LOAN_INSTALLMENT_START_DATE, -1), 'MM-YYYY'), "
						 +"TO_CHAR(LOAN_INSTALLMENT_START_DATE, 'DD-MON-YYYY'), TO_CHAR(LOAN_PAYMENT_DATE, 'DD-MM-YYYY') "
						 +"FROM HRMS_LOAN_PROCESS  "
						 +"WHERE LOAN_APPL_CODE = ?";
		
		case 13 : return "SELECT LOAN_REQ_EMI_AMOUNT, LOAN_INSTALLMENTS_PENDING,LOAN_MONHLY_PRINCIPAL FROM HRMS_LOAN_CLOSURE WHERE LOAN_CLOSURE_CODE = ? AND LOAN_APPL_CODE=?";
		
		case 14 : return "UPDATE HRMS_LOAN_PROCESS SET LOAN_INTEREST_RATE = ?, LOAN_INTEREST_TYPE=? WHERE LOAN_APPL_CODE = ? ";
			
		default : return "fram work could not found the query";
		}
	}
}
