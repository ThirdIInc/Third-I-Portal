package org.paradyne.model.payroll;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.NonIndustrialSalary;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


/**
 * @author sunil
 *
 */
public class NonIndustrialSalaryAcquaintanceRollModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	// GET EMP_ID THOSE FULLFILL SORTING CRITERIA
	
	public Object[][] getEmpId(NonIndustrialSalary nonIndustrialSalary) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 * 
			 */
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME||'\n'|| "
					+"HRMS_RANK.RANK_NAME||'\n'||EMP_TOKEN||'\n'||'DOB : '|| nvl(EMP_DOB,'')||'\n'||'DOA : '||nvl(EMP_REGULAR_DATE,'') " +
							"||'\n'||BANK_NAME||'\n'||BRANCH_NAME||'\n'||'A/C. No.'||SAL_ACCNO_REGULAR||'\n'||'MICR CODE :'||SAL_MICR_REGULAR FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_SALARY_2007 ON(HRMS_SALARY_"+nonIndustrialSalary.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) " +
						"INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
						"left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
						"left join HRMS_BANK on (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
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
	
	public Object [][] getDebitHeader_nonRecovery()
	{
		Object debit_header[][]=null;
		try
		{
			String selectDebit="SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD "
				+" WHERE DEBIT_IS_TABLERECOVERY !='Y' ORDER BY DEBIT_CODE";
			debit_header= getSqlModel().getSingleResult(selectDebit);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return debit_header;
		
	}
	public Object [][] getDebitHeader_recovery()
	{
		Object debit_header[][]=null;
		try
		{
			String selectDebit="SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD "
				+" WHERE DEBIT_IS_TABLERECOVERY ='Y' ORDER BY DEBIT_CODE";
			debit_header= getSqlModel().getSingleResult(selectDebit);
		}
		catch(Exception e)
		{
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
	public void generateReport(NonIndustrialSalary nonIndustrialSalary ,HttpServletResponse response) throws Exception {
		
		boolean result =true;
		String reportType = new String("Txt");
		String pagesize="A3";
		String reportName = "Non Industrial Salary Acquaintance Roll_"
				+ Utility.month(Integer.parseInt(nonIndustrialSalary
						.getMonth())) + "_" + nonIndustrialSalary.getYear();
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName,pagesize);
		
		
			String year = nonIndustrialSalary.getYear();
			String month = nonIndustrialSalary.getMonth();
			Object emp_id[][] = getEmpId(nonIndustrialSalary);
			Object credit_header[][] = getCreditHeader();
			Object debit_nonRecovery[][] = getDebitHeader_nonRecovery();
			Object debit_recovery[][] = getDebitHeader_recovery();
			int totalCol = credit_header.length + debit_nonRecovery.length
					+ debit_recovery.length + 7;
			String[] colNames = new String[totalCol];
			colNames[0] = "S No.";
			colNames[1] = "BIO DATA";
			int count = 2;
			for (int i = 0; i < credit_header.length; i++) {
				colNames[count] = String.valueOf(credit_header[i][1]);
				count++;
			}
			colNames[count] = "TOT CRE";
			count = count + 1;
			for (int i = 0; i < debit_nonRecovery.length; i++) {
				colNames[count] = String.valueOf(debit_nonRecovery[i][1]);
				count++;
			}
			colNames[count] = "TOT-NONREC";
			count = count + 1;
			colNames[count] = "NET-PAY";
			count = count + 1;
			for (int i = 0; i < debit_recovery.length; i++) {
				colNames[count] = String.valueOf(debit_recovery[i][1]);
				count++;
			}
			colNames[count] = "TOT-REC";
			count = count + 1;
			colNames[count] = "NET-SALARY";
			int sno=1;
			Object[][] data = new Object[emp_id.length][totalCol];
			for (int i = 0; i < emp_id.length; i++) {
				data[i][0] = sno;
				data[i][1] = emp_id[i][1];
				int column = 2;
				int position_totalPay = 0;
				/** set credit values */
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
				data[i][column] = Math.round(total_credit);
				column = column + 1;
				/**  set non-recovery debits */
				Object salDebit[][] = getSalDebit(String.valueOf(emp_id[i][0]),
						month, year);
				double total_nonRecovery = 0;
				for (int k = 0; k < debit_nonRecovery.length; k++) {
					data[i][column] = 0;
					for (int index = 0; index < salDebit.length; index++) {
						if (String.valueOf(debit_nonRecovery[k][0]).equals(
								String.valueOf(salDebit[index][0]))) {
							data[i][column] = salDebit[index][1];
							total_nonRecovery += Double.parseDouble(String
									.valueOf(salDebit[index][1]));
						}
					}
					column++;
				}
				data[i][column] = Math.round(total_nonRecovery);
				double total_pay = 0;
				total_pay = Double.parseDouble(String
						.valueOf(data[i][credit_header.length + 2]))
						- Double.parseDouble(String.valueOf(data[i][column]));
				column = column + 1;
				data[i][column] = total_pay;
				position_totalPay = column;
				column = column + 1;
				/**  set recovery debits */
				double total_recovery = 0;
				for (int j = 0; j < debit_recovery.length; j++) {
					data[i][column] = 0;
					for (int k = 0; k < salDebit.length; k++) {
						if (String.valueOf(debit_recovery[j][0]).equals(
								String.valueOf(salDebit[k][0]))) {
							data[i][column] = salDebit[k][1];
							total_recovery += Double.parseDouble(String
									.valueOf(salDebit[k][1]));
						}
					}
					column++;
				}
				data[i][column] = Math.round(total_recovery);
				double total_salary_pay = 0;
				total_salary_pay = Double.parseDouble(String
						.valueOf(data[i][position_totalPay]))
						- Double.parseDouble(String.valueOf(data[i][column]));
				column = column + 1;
				data[i][column] = Math.round(total_salary_pay);
				
				sno++;
			}
			Object[][] dataWithHeader = new Object[data.length + 1][totalCol];
			for (int i = 0; i < colNames.length; i++) {
				dataWithHeader[0][i] = colNames[i];
			}
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					dataWithHeader[i + 1][j] = data[i][j];
				}
			}
			String finalDataWithHeader[][] = Utility
					.deleteColumnIfZeroWithHeader(dataWithHeader);
			System.out
					.println("length of final object----------------------------"
							+ finalDataWithHeader.length);
			System.out
					.println("column of final object----------------------------"
							+ finalDataWithHeader[0].length);
			String finalHeader[] = new String[finalDataWithHeader[0].length];
			int[] cellWidth = new int[finalDataWithHeader[0].length];
			int[] alignment = new int[finalDataWithHeader[0].length];
			for (int i = 0; i < finalDataWithHeader[0].length; i++) {
				finalHeader[i] = finalDataWithHeader[0][i];
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 5;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 4;
					cellWidth[1] = 25;
				}
			}
			Object finalData[][] = new Object[finalDataWithHeader.length - 1][finalDataWithHeader[0].length];
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = finalDataWithHeader[i + 1][j];
					//logger.info("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
				}
			}
			/*Object totalByColumn[][] = new Object[1][finalData[0].length];
			String totalHeader[] = new String[finalHeader.length];
			totalHeader[0] = "";
			totalByColumn[0][0] = "TOTAL :-----";
			totalHeader[1] = "";
			totalByColumn[0][1] = " ";
			for (int i = 2; i < finalData[0].length; i++) {
				double total = 0;
				for (int j = 0; j < finalData.length; j++) {
					total += Double
							.parseDouble(String.valueOf(finalData[j][i]));
				}
				totalHeader[i] = "";
				totalByColumn[0][i] = total;
			}*/
		
			rg.tableBody(finalHeader, finalData, cellWidth, alignment,rg,5,2,"PAY-BILL OF NON-INDUSTRIAL STAFF OF ND(MUMBAI) FOR THE MONTH OF "+Utility.month(Integer.parseInt(month))+" "+year+"");
			//rg.tableBody(totalByColumn, cellWidth, alignment);
			
			rg.createReport(response);
		
		
	}
	
		
}
