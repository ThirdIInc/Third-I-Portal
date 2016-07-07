package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.paradyne.bean.payroll.NonIndustrialSalary;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author sunil
 * 
 */
public class NonIndustrialSalaryAuditModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

// GET EMP_ID THOSE FULLFILL SORTING CRITERIA
	
	public Object[][] getEmpId(NonIndustrialSalary nonIndustrialSalary) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 * 
			 */
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_SALARY_2007 ON(HRMS_SALARY_"+nonIndustrialSalary.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" WHERE HRMS_SALARY_"+nonIndustrialSalary.getYear()+".SAL_EMP_PAYBILL IS NOT  NULL "
				+" AND HRMS_SALARY_"+nonIndustrialSalary.getYear()+".SAL_MONTH ="+nonIndustrialSalary.getMonth()+"  " ;
			String where = " ";
			try {
				if (nonIndustrialSalary.getPayBillNo() != null
						&& nonIndustrialSalary.getPayBillNo().length() > 0)

					where = " AND HRMS_SALARY_"+nonIndustrialSalary.getYear()+".SAL_EMP_PAYBILL="
							+ nonIndustrialSalary.getPayBillNo();
				where = where + " AND HRMS_SALARY_"+nonIndustrialSalary.getYear()+".SAL_EMP_TYPE="
						+ nonIndustrialSalary.getTypeCode();
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
	public boolean tableExist(String year){
		boolean result = false;
		Object[][] debit_amount = null;
		try {
			String selectDebits = "SELECT * FROM HRMS_SAL_DEDUCTION_"+ year;
					
			debit_amount = getSqlModel().getSingleResult(selectDebits);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		if(debit_amount !=null){
			result =true;
		}
		return result;
	}
	public Object[][] getSalaryCreditData (String empCode ,String year,String month){
		Object[][] credit_amount = null;
		try {

			String selectCredits = "SELECT   SAL_CREDIT_CODE,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_CREDITS_"+ year
					+ " WHERE EMP_ID='"
					+ empCode
					+ "' AND SAL_MONTH='"
					+ month + "' ORDER BY SAL_CREDIT_CODE ";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {
			credit_amount = new Object [0][0];
			logger.info("Exception in getsalaryCreditdata -----Table doesn't exist ");
		}
		return credit_amount;

	}
	public Object [][] getCreditHeader()
	{
		Object credit_header[][]=null;  
		try
		{
		String selectCredit="SELECT CREDIT_CODE,  CREDIT_ABBR FROM HRMS_CREDIT_HEAD order BY CREDIT_CODE";
		credit_header= getSqlModel().getSingleResult(selectCredit);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return credit_header;
		
	}
	// GET DEBIT CODE AND DEBIT ABBR

	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try {
			String selectDebit = "SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
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
	public Object[][] getSalDebit(String emp_id, String month, String year) {
		Object[][] debit_sal_amount = null;

		try {
			String selectDebits = " SELECT  SAL_DEBIT_CODE ,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"+year
								+" WHERE EMP_ID='"+ emp_id+ "' AND SAL_MONTH='"+ month + "'  ORDER BY SAL_DEBIT_CODE ";
			
			  debit_sal_amount = getSqlModel().getSingleResult(
					selectDebits, new Object[0][0]);

		} catch (Exception e) {
			debit_sal_amount = new Object[0][0];
		}

		return debit_sal_amount;

	}
	public Object[][] viewSalary(NonIndustrialSalary nonIndustrialSalary) {

		String year = nonIndustrialSalary.getYear();
		String month = nonIndustrialSalary.getMonth();
		
		String prevMonth = (month.equals("1")) ? month="12" : ""+(Integer.parseInt(month)-1);
		String prevYear = (month.equals("1")) ? ""+(Integer.parseInt(year)-1) : year;
		
		
		Object emp_id[][] = getEmpId(nonIndustrialSalary);
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();
		ArrayList<NonIndustrialSalary> debitNames = new ArrayList<NonIndustrialSalary>();

		for (int i = 0; i < credit_header.length; i++) {
			/**
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 */
			NonIndustrialSalary creditName = new NonIndustrialSalary();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);

		}

		nonIndustrialSalary.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/**
			 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
			 */
			NonIndustrialSalary debitName = new NonIndustrialSalary();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);

		}

		nonIndustrialSalary.setDebitHeader(debitNames);

		/**
		 *  IS TOTAL NO OF EMPLOYEES THAT MAKES TOTAL ROWS
		 * 
		 */
		//int totalCol = credit_header.length +debit_header.length +6;
		//Object[][] finalData = new Object[2*emp_id.length][totalCol];
		
		Object[][] currentData = getSalaryData(emp_id, credit_header, debit_header, year, month);
		Object[][] previousData = getSalaryData(emp_id, credit_header, debit_header, prevYear, prevMonth);
		
		
		Object[][] finalData =null;
		if(currentData!=null && currentData.length >0 && previousData !=null && previousData.length >0){
			finalData =new Utility().joinArrayByEmpCode(currentData, previousData);
		}
	//	Object[][] finalData = Utility.joinArrays(currentData,previousData,2, 0);
		
		return finalData;

	}
	public Object[][] getSalaryData(Object emp_id[][],Object credit_header[][],Object debit_header[][],String year,String month) {
		int totalCol = credit_header.length +debit_header.length +7;
		Object[][] data = new Object[emp_id.length][totalCol];
		
		for (int i = 0; i < data.length; i++) {

			data[i][0] = emp_id[i][0];
			data[i][1] = emp_id[i][1];
			data[i][2] = emp_id[i][2];
			data[i][3] = Utility.month(Integer.parseInt(month));
			int column = 4;
			/** set salary credit values */
			double total_credit = 0;
			Object salCredit[][] = getSalaryCreditData(String
					.valueOf(emp_id[i][0]), year, month);
			for (int j = 0; j < credit_header.length; j++) {
				data[i][column] = 0;
				for (int k = 0; k < salCredit.length; k++) {
					if (String.valueOf(credit_header[j][0]).equals(
							String.valueOf(salCredit[k][0]))) {
						data[i][column] = salCredit[k][1];
						total_credit += Double.parseDouble(String
								.valueOf(salCredit[k][1]));
					}
				}
				column++;
			}
			data[i][column] = total_credit;
			column = column + 1;
			/**  set salary debits */
			Object salDebit[][] = getSalDebit(String.valueOf(emp_id[i][0]),
					month, year);
			double total_debit = 0;
			for (int k = 0; k < debit_header.length; k++) {
				data[i][column] = 0;
				for (int index = 0; index < salDebit.length; index++) {
					if (String.valueOf(debit_header[k][0]).equals(
							String.valueOf(salDebit[index][0]))) {
						data[i][column] = salDebit[index][1];
						total_debit += Double.parseDouble(String
								.valueOf(salDebit[index][1]));
					}
				}
				column++;
			}
			data[i][column] = total_debit;
			double total_pay = 0;
			total_pay = Double.parseDouble(String
					.valueOf(data[i][credit_header.length + 4]))
					- Double.parseDouble(String.valueOf(data[i][column]));
			column = column + 1;
			data[i][column] = total_pay;
		}
		return data;
	}
	
}
