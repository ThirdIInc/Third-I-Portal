package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.payroll.BranchWiseSalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author  prakash
 * Date:08-06-2009
 */
public class BranchWiseSalReportModel extends ModelBase {
	 NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BranchWiseSalReportModel.class); 
	
	
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader(String ledgerCode,String year) {
		Object credit_header[][] = null;
		try{
			String query ="SELECT DISTINCT SAL_CREDIT_CODE,TRIM(CREDIT_ABBR) FROM HRMS_SAL_CREDITS_"+year+" " +
							" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE" +
							" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") ORDER BY SAL_CREDIT_CODE";
			
		credit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_header;
	} // end of method getCreditHeader()
	
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return DEBITS Object 'debit_header'
	 */
	public Object[][] getDebitHeader(String ledgerCode,String year) {
		Object debit_header[][] = null;
		try{
			String query ="SELECT DISTINCT SAL_DEBIT_CODE,TRIM(DEBIT_ABBR) FROM HRMS_SAL_DEBITS_"+year+" " +
						" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE" +
						" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") ORDER BY SAL_DEBIT_CODE";
			
		debit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	}  // end of method getDebitHeader()
	
	public Vector getSalary(BranchWiseSalReport salreg){
		Vector vect = new Vector();
		try{
							
		String ledgerCode = salreg.getLedgerCode();
		
		String brnQuery = " SELECT DISTINCT SAL_EMP_CENTER,CENTER_NAME FROM HRMS_SALARY_"+salreg.getYear()
								+" INNER JOIN HRMS_CENTER ON (HRMS_SALARY_"+salreg.getYear()+".SAL_EMP_CENTER = HRMS_CENTER.CENTER_ID) WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") ";

				if (!(salreg.getOnHold().equals("A")))
						brnQuery += "AND SAL_ONHOLD= '"+ salreg.getOnHold() + "' ";

				
					brnQuery +=" ORDER BY UPPER(CENTER_NAME) ";


					Object brnData[][] = getSqlModel().getSingleResult(brnQuery);
					
					if (brnData != null && brnData.length > 0) {
						
						
						String creditQuery="",totalCreditQuery="",totalCreditQuery1="";
						String creditQuery1=" ",totalDebitQuery="",totalDebitQuery1="";
						String creditQuery2="";String creditQuery3="",creditQuery4="",creditQuery5="",orderQuery="";;
						String debitQuery="",debitQuery1="",debitQuery2="",debitQuery3="";
						String count="0";
						
						Object[][] creditHead = getCreditHeader(ledgerCode,
								salreg.getYear());
						Object[][] debitHead = getDebitHeader(ledgerCode,
								salreg.getYear());
						
						for (int i = 0; i < creditHead.length; i++) {
							if(i==0){
								
								creditQuery1 += "select CENTER_ID,CENTER_NAME||'  [' || count(a"+i+".sal_amount)|| '] ','PAY', NVL(sum(a"+i+".sal_amount),0),";
								
								totalCreditQuery1 += "select 'Sr.No.','Total  [' || count(a"+i+".sal_amount)|| '] ','PAY', NVL(sum(a"+i+".sal_amount),0),";
									
								creditQuery2 += " from (select emp_id,SAL_AMOUNT from HRMS_SAL_CREDITS_"+salreg.getYear()+" " +
												" where SAL_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" ";
								creditQuery4 = 	" inner join hrms_salary_"+salreg.getYear()+" on  (hrms_salary_"+salreg.getYear()+".emp_id = a"+i+".emp_id  " +
												" and SAL_LEDGER_CODE in ("+ledgerCode+"))";
								
									if(!(salreg.getOnHold().equals("A"))){
										creditQuery4+="AND sal_onhold='"+salreg.getOnHold()+"' " ;
									}
				
								creditQuery4+=" inner join hrms_center on (hrms_salary_"+salreg.getYear()+".SAL_EMP_CENTER = hrms_center.CENTER_ID)  ";
								
								creditQuery5=" group by CENTER_NAME,CENTER_ID";
									
								orderQuery=" ORDER BY  UPPER(CENTER_NAME)";
								
								count=String.valueOf(i);
							}
							else{
								creditQuery1 += " NVL(sum(a"+i+".sal_amount),0),";
								totalCreditQuery1 += "NVL(sum(a"+i+".sal_amount),0),";
								creditQuery3 += " left join (select emp_id,SAL_AMOUNT from HRMS_SAL_CREDITS_"+salreg.getYear()+" where SAL_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";
							}
														
						}
						
						creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4+creditQuery5+orderQuery;
						
						totalCreditQuery = totalCreditQuery1.substring(0,totalCreditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4+orderQuery;
						
						//logger.info("creditQuery "+creditQuery );
						//logger.info("totalCreditQuery "+totalCreditQuery );
						
						for (int i = 0; i < debitHead.length; i++) {
							if(i==0){
																
								totalDebitQuery1 += "select 'Sr.No.','Total  [' || count(a"+i+".sal_amount)|| '] ','PAY', NVL(sum(a"+i+".sal_amount),0),";
								
								debitQuery1 += "select CENTER_ID,CENTER_NAME ,'PAY', NVL(sum(a"+i+".sal_amount),0),";
								debitQuery2 += " from (select emp_id,SAL_AMOUNT from HRMS_SAL_debits_"+salreg.getYear()+" " +
												" where SAL_debit_CODE = "+String.valueOf(debitHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" ";
								count=String.valueOf(i);
							}
							else{
								debitQuery1 += "NVL(sum(a"+i+".sal_amount),0),";
								totalDebitQuery1 += "NVL(sum(a"+i+".sal_amount),0),";
								debitQuery3 += " left join (select emp_id,SAL_AMOUNT from HRMS_SAL_debits_"+salreg.getYear()+" where SAL_debit_CODE = "+String.valueOf(debitHead[i][0])
								+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";				// join change from inner to left
							}
														
						}
						debitQuery = debitQuery1.substring(0,debitQuery1.length()-1) + debitQuery2 +debitQuery3+creditQuery4+creditQuery5+orderQuery;
						totalDebitQuery = totalDebitQuery1.substring(0,totalDebitQuery1.length()-1) + debitQuery2 +debitQuery3+creditQuery4+orderQuery;
						
						//logger.info("debitQuery "+debitQuery );
						//logger.info("totalDebitQuery "+totalDebitQuery );
						
						Object empCreditData[][] = getSqlModel().getSingleResult(creditQuery);
						
						Object empDebitData[][] = getSqlModel().getSingleResult(debitQuery);
						
						Object totalCreditObj[][] = getSqlModel().getSingleResult(totalCreditQuery);
						
						Object totalDebitObj[][] = getSqlModel().getSingleResult(totalDebitQuery);
						
						vect.add(0, empCreditData);
						vect.add(1, empDebitData);
						vect.add(2, totalCreditObj);
						vect.add(3, totalDebitObj);
									
					}
					
		}catch(Exception e){
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		return vect;
	}
	
	public Vector getArrear(BranchWiseSalReport salreg){
		Vector vect = new Vector();
		try{				
			String ledgerCode = salreg.getLedgerCode();
			Object[][] creditHead = getCreditHeader(ledgerCode,
					salreg.getYear());
			Object[][] debitHead = getDebitHeader(ledgerCode,
					salreg.getYear());
			String arrledgerCode=salreg.getArrLedgerCode();	
			
			
			String brnQuery = " SELECT DISTINCT SAL_EMP_CENTER,CENTER_NAME FROM HRMS_ARREARS_"+salreg.getYear()
									+ " INNER JOIN HRMS_SALARY_"+ salreg.getYear()+" ON  HRMS_SALARY_"+ salreg.getYear()+ ".EMP_ID = HRMS_ARREARS_"+ salreg.getYear()+".EMP_ID AND  HRMS_SALARY_"+ salreg.getYear()+ ".SAL_LEDGER_CODE = "+ledgerCode
									+" INNER JOIN HRMS_CENTER ON (HRMS_SALARY_"+salreg.getYear()+".SAL_EMP_CENTER = HRMS_CENTER.CENTER_ID) WHERE ARREARS_CODE IN ("+arrledgerCode+") ";
									
									
					if (!(salreg.getOnHold().equals("A")))
						brnQuery += "AND SAL_ONHOLD= '"+ salreg.getOnHold() + "' ";

				
					brnQuery +=" ORDER BY UPPER(CENTER_NAME) ";

					Object brnData[][] = getSqlModel().getSingleResult(brnQuery);
					
					Object creditTotalObj[][]= null;
					Object debitTotalObj[][]= null;
					
					/*String [][] creditHead=null;
					String [][]debitHead=null;*/
					
					Object finalCreditObj[][]=null;
					Object finalDebitObj[][]=null;
					
					if (brnData != null && brnData.length > 0) {
						
						
						for(int i=0;i<brnData.length;i++){
							
							try{						
									int count=3;
									
									String creditQuery =" SELECT SAL_EMP_CENTER,ARREARS_CREDIT_CODE,sum(ARREARS_AMT) FROM HRMS_ARREARS_CREDIT_"+ salreg.getYear() 
														+" INNER JOIN HRMS_SALARY_"+ salreg.getYear()+" on HRMS_SALARY_"+ salreg.getYear()+".EMP_ID = HRMS_ARREARS_CREDIT_"+ salreg.getYear()+".ARREARS_EMP_ID and SAL_LEDGER_CODE = "+ledgerCode
														+" and SAL_EMP_CENTER = "+String.valueOf(brnData[i][0])
														+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ salreg.getYear()+".ARREARS_CODE "   
														+" INNER JOIN HRMS_CENTER ON (HRMS_SALARY_"+salreg.getYear()+".SAL_EMP_CENTER = HRMS_CENTER.CENTER_ID)"
														+" WHERE HRMS_ARREARS_CREDIT_"+ salreg.getYear()+".ARREARS_CODE IN ("+arrledgerCode+") and SAL_EMP_CENTER =  "+String.valueOf(brnData[i][0])
														+" GROUP BY SAL_EMP_CENTER,ARREARS_CREDIT_CODE "
														+" ORDER BY SAL_EMP_CENTER,ARREARS_CREDIT_CODE  ";		
									
									String debitQuery =" SELECT SAL_EMP_CENTER,ARREARS_DEBIT_CODE,sum(ARREARS_AMT) FROM HRMS_ARREARS_DEBIT_"+ salreg.getYear() 
														+" INNER JOIN HRMS_SALARY_"+ salreg.getYear()+" on HRMS_SALARY_"+ salreg.getYear()+".EMP_ID = HRMS_ARREARS_DEBIT_"+ salreg.getYear()+".ARREARS_EMP_ID and SAL_LEDGER_CODE = "+ledgerCode
														+" and SAL_EMP_CENTER = "+String.valueOf(brnData[i][0])
														+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ salreg.getYear()+".ARREARS_CODE "   
														+" INNER JOIN HRMS_CENTER ON (HRMS_SALARY_"+salreg.getYear()+".SAL_EMP_CENTER = HRMS_CENTER.CENTER_ID)"
														+" WHERE HRMS_ARREARS_DEBIT_"+ salreg.getYear()+".ARREARS_CODE IN ("+arrledgerCode+") and SAL_EMP_CENTER =  "+String.valueOf(brnData[i][0])
														+" GROUP BY SAL_EMP_CENTER,ARREARS_DEBIT_CODE "
														+" ORDER BY SAL_EMP_CENTER,ARREARS_DEBIT_CODE  ";
														
									
									Object[][] empCredit = getSqlModel().getSingleResult(creditQuery);

									Object[][] empDebit = getSqlModel().getSingleResult(debitQuery);
									
									if(i==0){
										
										creditTotalObj= new Object[brnData.length][creditHead.length+3];
										debitTotalObj=new Object[brnData.length][debitHead.length+3];
																			
										creditTotalObj=intZero(creditTotalObj);
																
										creditTotalObj[i][0]=String.valueOf(brnData[i][0]);
										creditTotalObj[i][1]=String.valueOf(brnData[i][1]);
												
										/*creditHead=new String[empCredit.length][1];
										debitHead=new String[empDebit.length][1];*/
										
										finalCreditObj= new Object[1][creditHead.length+3];
										finalDebitObj=new Object[1][debitHead.length+3];
										
										finalCreditObj=intZero(finalCreditObj);
										finalDebitObj=intZero(finalDebitObj);
										
										finalCreditObj[0][0]="";
										finalCreditObj[0][1]="Total ";
										int creditCount=0;
										for (int j = 0; j < creditHead.length; j++) {
											if(creditCount < empCredit.length && String.valueOf(creditHead[j][0]).equals(String.valueOf(empCredit[creditCount][1]))){
											creditTotalObj[i][count] = checkNull(String.valueOf(empCredit[creditCount][2]));
											
											finalCreditObj[0][count]= Double.parseDouble(String.valueOf(finalCreditObj[0][count]))
																		+Double.parseDouble(checkNull(String.valueOf(empCredit[creditCount][2])));
											
											count++;
											creditCount++;
											}
											//creditHead[j][0]= String.valueOf(empCredit[j][1]);
											
										}
										
										count=3;
										debitTotalObj=intZero(debitTotalObj);
										
										debitTotalObj[i][0]=String.valueOf(brnData[i][0]);
										debitTotalObj[i][1]=String.valueOf(brnData[i][1]);
										int debitCount=0;
										for (int j = 0; j < debitHead.length; j++) {
											if(debitCount < empDebit.length && String.valueOf(debitHead[j][0]).equals(String.valueOf(empDebit[debitCount][1]))){
											debitTotalObj[i][count] = checkNull(String.valueOf(empDebit[debitCount][2]));
											
											finalDebitObj[0][count]= Double.parseDouble(String.valueOf(finalDebitObj[0][count]))
																		+Double.parseDouble(checkNull(String.valueOf(empDebit[debitCount][2])));

											
											count++;
											debitCount++;
											}
											//debitHead[j][0]= String.valueOf(empDebit[j][1]);
											
										}
										
									}else{					
									
										creditTotalObj[i][0]=String.valueOf(brnData[i][0]);
										creditTotalObj[i][1]=String.valueOf(brnData[i][1]);
											
										for(int k=0;k<creditHead.length;k++){
													
											for (int j = 0; j < empCredit.length; j++) {
														
												if(String.valueOf(creditHead[k][0]).equals(String.valueOf(empCredit[j][1]))){
													
													creditTotalObj[i][count] = checkNull(String.valueOf(empCredit[j][2]));
													
													finalCreditObj[0][count]= Double.parseDouble(String.valueOf(finalCreditObj[0][count]))
																				+Double.parseDouble(checkNull(String.valueOf(empCredit[j][2])));

													
													break;
												}else{
													creditTotalObj[i][count] = String.valueOf("0.00");
												
												}
											}
											count++;
										}
										
										count=3;
										debitTotalObj[i][0]=String.valueOf(brnData[i][0]);
										debitTotalObj[i][1]=String.valueOf(brnData[i][1]);
										
										for(int k=0;k<debitHead.length;k++){
												
											for (int j = 0; j < empDebit.length; j++) {
												
												if(String.valueOf(debitHead[k][0]).equals(String.valueOf(empDebit[j][1]))){
														
													debitTotalObj[i][count] = checkNull(String.valueOf(empDebit[j][2]));
														
													finalDebitObj[0][count]= Double.parseDouble(String.valueOf(finalDebitObj[0][count]))
																				+Double.parseDouble(checkNull(String.valueOf(empDebit[j][2])));

													
													break;
												}else{
													debitTotalObj[i][count] = String.valueOf("0.00");
													
												}
											}
											count++;
										}
																	
									}

									} catch (Exception e) {
										e.printStackTrace();
										logger.error("error in creating the arrear object");
									}
								}
							
						vect.add(0, creditTotalObj);
						vect.add(1, debitTotalObj);
						vect.add(2, finalCreditObj);
						vect.add(3, finalDebitObj);
						
					}
		}catch(Exception e){
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		return vect;
	}
	
	public void genReport(BranchWiseSalReport salreg, HttpServletResponse response,String recPerPage,String colPerPage)
	{
		String name = "Branchwise Salary Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
				"Pdf", name,"");
		try {
						
			rg.setFName("Branchwise Salary Summary for "
					+ Utility.month(Integer.parseInt(salreg.getMonth())) + "_"
					+ salreg.getYear() + "" + "." + salreg.getReport());
			
			String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
								
			rg.addText("Date: " + dateData[0][0]+"\n", 0, 2, 0, 12);
			
			rg.addTextBold("\nBranchwise Salary Summary for "
					+ Utility.month(Integer.parseInt(salreg.getMonth()))
					+ "," + salreg.getYear()+"\n\n", 0, 1, 1, 12);
			
			rg.addTextBold(salreg.getDivName()+" \n"+salreg.getDivAdd()+" \n"+salreg.getDivCity()+"\n", 0, 1, 1, 12);
			
			
			boolean consCheck = false;
			if(salreg.getConsolidateCheck().equals("Y"))
				consCheck=true;
			
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
									+ salreg.getMonth()
									+ " AND LEDGER_YEAR="
									+ salreg.getYear()
									+ " AND LEDGER_DIVISION="
									+ salreg.getDivCode()
									+" AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL')";
							
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		
			String ledgerCode = "";
			String arrledgerCode = "";
			
			if (ledgerData != null && ledgerData.length > 0){
			
				for (int i = 0; i < ledgerData.length; i++) {
					ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
				}
				ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
				salreg.setLedgerCode(ledgerCode);
				
				Vector salVect = getSalary(salreg);
			
			if(salVect.size() > 1){
			
				Object [][] salCredit=(Object[][])salVect.get(0);
				Object [][] salDebit=(Object[][])salVect.get(1);
				Object [][] totalSalCredit=(Object[][])salVect.get(2);
				Object [][] totalSalDebit=(Object[][])salVect.get(3);
				
				if (salCredit != null && salCredit.length > 0){
									
					Object [][] grnadCredit= new Object[1][totalSalCredit[0].length];
					Object [][] grnadDebit= new Object[1][totalSalDebit[0].length];
					grnadCredit=intSpace(grnadCredit);
					grnadDebit=intSpace(grnadDebit);
											
					String arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH = "
												+ salreg.getMonth()
												+ " AND ARREARS_REF_YEAR ="
												+ salreg.getYear()
												+ " AND ARREARS_DIVISION ="
												+ salreg.getDivCode();
			
					Object arrLedgerData[][] = getSqlModel().getSingleResult(arrearLedgerQuery);
			
					if (arrLedgerData != null && arrLedgerData.length > 0){
				
							for (int i = 0; i < arrLedgerData.length; i++) {
								arrledgerCode += String.valueOf(arrLedgerData[i][0]) + ",";
							}
							arrledgerCode = arrledgerCode.substring(0, arrledgerCode.length() - 1);
							salreg.setArrLedgerCode(arrledgerCode);
							
							Vector arrVect = getArrear(salreg);
							
							if(arrVect.size() > 1){
								
									Object [][] arrCredit=(Object[][])arrVect.get(0);
									Object [][] arrDebit=(Object[][])arrVect.get(1);
									
									Object [][] totalArrCredit=(Object[][])arrVect.get(2);
									Object [][] totalArrDebit=(Object[][])arrVect.get(3);
									
									if (arrCredit != null && arrCredit.length > 0){
									
										for (int j = 0; j < totalSalCredit[0].length; j++) {
											grnadCredit[0][1] = String.valueOf(totalSalCredit[0][1]);
											 if(j >= 3){
												 
												 grnadCredit[0][j] = Double.parseDouble(checkNull(String.valueOf(totalSalCredit[0][j]))) +
																						Double.parseDouble(checkNull(String.valueOf(totalArrCredit[0][j])));
												}
										}
										for (int j = 0; j < totalSalDebit[0].length; j++) {
											grnadDebit[0][1] = String.valueOf(totalSalDebit[0][1]);
											if(j >= 3){
												
												grnadDebit[0][j] = Double.parseDouble(checkNull(String.valueOf(totalSalDebit[0][j]))) +
																					Double.parseDouble(checkNull(String.valueOf(totalArrDebit[0][j])));
												}
										}
						
										if(consCheck){
											
											for (int j = 0; j < salCredit.length; j++) {
												
												for (int k = 0; k < arrCredit.length; k++) {
																	
													if(String.valueOf(salCredit[j][0]).equals(String.valueOf(arrCredit[k][0]))){
														
														for (int l = 3; l < salCredit[0].length; l++) {
															
															salCredit[j][l] = Double.parseDouble(checkNull(String.valueOf(salCredit[j][l]))) +
																								Double.parseDouble(checkNull(String.valueOf(arrCredit[k][l])));
																								
														}
														
														
													}
												}
												
											}
											
											for (int j = 0; j < salDebit.length; j++) {
												
												for (int k = 0; k < arrDebit.length; k++) {
																	
													if(String.valueOf(salDebit[j][0]).equals(String.valueOf(arrDebit[k][0]))){
														
														for (int l = 3; l < salDebit[0].length; l++) {
															
															salDebit[j][l] = Double.parseDouble(checkNull(String.valueOf(salDebit[j][l]))) +
																					Double.parseDouble(checkNull(String.valueOf(arrDebit[k][l])));
														}
														
														
													}
												}
												
											}
											
											rg.addTextBold("Salary With Consolidated Arrears", 0, 0, 0,10);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(salreg, salCredit, salDebit, rg, response,true);
											rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Grand Total", 0, 0, 0,10);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(salreg, grnadCredit, grnadDebit, rg, response,false);
									
									}else{
										rg.addTextBold("Salary Details", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, salCredit, salDebit,rg, response,true);
										rg.addText("\n", 0, 0, 0);
										rg.addTextBold("Salary Total", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, totalSalCredit, totalSalDebit,rg, response,false);
										rg.addText("\n", 0, 0, 0);
										rg.pageBreak();
										rg.addTextBold("Arrear Details", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, arrCredit, arrDebit,rg, response,true);
										rg.addText("\n", 0, 0, 0);
										rg.addTextBold("Arrear Total", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, totalArrCredit, totalArrDebit,rg, response,false);
										rg.addText("\n", 0, 0, 0);
										rg.addTextBold("Grand Total", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, grnadCredit, grnadDebit, rg, response,false);
										
									}
									}else{
										
										rg.addTextBold("Salary Details", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, salCredit, salDebit,rg, response,true);
										rg.addText("\n", 0, 0, 0);
										rg.addTextBold("Salary Total", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, totalSalCredit, totalSalDebit,rg, response,false);
									}
									}else{
										rg.addTextBold("Salary Details", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, salCredit, salDebit,rg, response,true);
										rg.addText("\n", 0, 0, 0);
										rg.addTextBold("Salary Total", 0, 0, 0,10);
										rg.addText("\n", 0, 0, 0);
										rg=printreport(salreg, totalSalCredit, totalSalDebit,rg, response,false);
									}
				
							}else{
											
								rg.addTextBold("Salary Details", 0, 0, 0,10);
								rg.addText("\n", 0, 0, 0);
								rg=printreport(salreg, salCredit, salDebit,rg, response,true);
								rg.addText("\n", 0, 0, 0);
								rg.addTextBold("Salary Total", 0, 0, 0,10);
								rg.addText("\n", 0, 0, 0);
								rg=printreport(salreg, totalSalCredit, totalSalDebit,rg, response,false);
															
							}
					}else
					rg.addText("No records avaliable for selected criteria", 0, 1,1);		
				}else
				rg.addText("No records avaliable for selected criteria", 0, 1,1);					
			}else
				rg.addText("No records avaliable for selected criteria", 0, 1,1);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		rg.createReport(response);
		
	}
	public ReportGenerator printreport(BranchWiseSalReport salreg ,Object [][] finalCredit,Object [][] finalDebit,ReportGenerator rg ,HttpServletResponse response,boolean printHeader){
		
		try{
			String colPerPage=salreg.getColumnNumber();
			String recPerPage=salreg.getRowNumber();
			
			Object[][] creditHead = getCreditHeader(salreg.getLedgerCode(),	salreg.getYear());
			Object[][] debitHead = getDebitHeader(salreg.getLedgerCode(),salreg.getYear());
			
			if(creditHead.length < debitHead.length){
				if(debitHead.length < Integer.parseInt(colPerPage)){
					colPerPage = String.valueOf(debitHead.length);
				}
			}
			else{
				if(creditHead.length<Integer.parseInt(colPerPage)){
					colPerPage = String.valueOf(creditHead.length);
				}
			}
			Object repHeader[][] = new Object[1][Integer.parseInt(colPerPage)+4];
			String header [] = new String[Integer.parseInt(colPerPage)+4];
			repHeader = intSpace(repHeader);
			header = intSpace(header);
			
			repHeader[0][0] = "Sr. No.";
			repHeader[0][1] = "Branch Name";
			
			header[0] = "Sr. No.";
			header[1] = "Branch Name";
			
			double row1=0.0;
			int payCount=0,creCount=0,tempCount=0,dedCount=0,dedObjCnt=0,tempDedCount=0;
			int[] cellwidth = new int[repHeader[0].length];
			int[] alignment = new int[repHeader[0].length];
			cellwidth[0] =5;
			cellwidth[1] =25;
			alignment[0] =1;
			alignment[1] =0;
			
			for (int i = 2; i < repHeader[0].length; i++) {
				
				if(i==2){
					row1 = (double) creditHead.length / Integer.parseInt(colPerPage);
					java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
							row1);
					payCount = Integer.parseInt(bigDecRow1.setScale(0,
							java.math.BigDecimal.ROUND_UP).toString());
						for (int j = 0; j < payCount; j++) {
							repHeader[0][i] = String.valueOf(repHeader[0][i]) +"PAY =>" +"\n";
							header[i] = String.valueOf(header[i]) +"PAY =>" +"\n";
						}
						
						row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
						java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
								row1);
						dedCount = Integer.parseInt(bigDecRow2.setScale(0,
								java.math.BigDecimal.ROUND_UP).toString());
							for (int j = 0; j < dedCount; j++) {
								repHeader[0][i] = String.valueOf(repHeader[0][i]) +"DED =>" +"\n";
								header[i] = String.valueOf(header[i]) +"DED =>" +"\n";
							}
							cellwidth[i] =5;
							alignment[i] =2;
				
				}else if(i<repHeader[0].length-1){
					tempCount= creCount;
						for (int j = 0; j < payCount; j++) {
							if(creCount< creditHead.length){
							repHeader[0][i] = String.valueOf(repHeader[0][i])+ String.valueOf(creditHead[creCount][1])+"\n" ;
							header[i] = String.valueOf(header[i])+ String.valueOf(creditHead[creCount][1])+"\n" ;
							creCount += Integer.parseInt(colPerPage);
							}
							else{
								repHeader[0][i] = String.valueOf(repHeader[0][i])+"\n" ;
								header[i] = String.valueOf(header[i])+"\n" ;
							}
						}
					creCount = tempCount;
					creCount++;
					
					tempDedCount= dedObjCnt;
					for (int j = 0; j < dedCount; j++) {
						if(dedObjCnt< debitHead.length){
						repHeader[0][i] = String.valueOf(repHeader[0][i])+ String.valueOf(debitHead[dedObjCnt][1])+"\n" ;
						header[i] = String.valueOf(header[i])+ String.valueOf(debitHead[dedObjCnt][1])+"\n" ;
						dedObjCnt += Integer.parseInt(colPerPage);
						}
						else{
							repHeader[0][i] = String.valueOf(repHeader[0][i])+"\n" ;
							header[i] = String.valueOf(header[i])+"\n" ;
						}
					}
					dedObjCnt = tempDedCount;
					dedObjCnt++;
					
				}else{
					for (int j = 0; j < payCount; j++) {
						repHeader[0][i] = String.valueOf(repHeader[0][i])+"Total Earn"+"\n";
						header[i] = String.valueOf(header[i])+"Total Earn"+"\n";
					}
					for (int j = 0; j < dedCount; j++) {
						repHeader[0][i] = String.valueOf(repHeader[0][i])+"Total Ded"+"\n";
						header[i] = String.valueOf(header[i])+"Total Ded"+"\n";
					}
									
						repHeader[0][i] = String.valueOf(repHeader[0][i])+"Net Salary";
						header[i] = String.valueOf(header[i])+"Net Salary"+"\n";
				}
				
					cellwidth[i] =10;
					alignment[i] =2;
			}
			
			int empCount = 0;
			
			Object repData[][] = new Object[Integer.parseInt(recPerPage)][Integer.parseInt(colPerPage)+4];
			
			Object pageTotCredit[][] = new Object[1][creditHead.length+3];
			Object pageTotDebit[][] = new Object[1][debitHead.length+3];
			
			Object pageTotalFinal[][] = new Object[1][Integer.parseInt(colPerPage)+4];
			pageTotCredit = intZero(pageTotCredit);
			pageTotDebit = intZero(pageTotDebit);
			
			pageTotalFinal= intSpace(pageTotalFinal);
			
			
			double finalDebTotal = 0.0;
			double finalCreTotal = 0.0;
			if(finalCredit.length < Integer.parseInt(recPerPage))
				repData = new Object[finalCredit.length][Integer.parseInt(colPerPage)+4];
			
			repData = intSpace(repData);
					
			for(int e=0;e<finalCredit.length;e++){
				try{
				payCount=0;creCount=0;tempCount=0;dedCount=0;dedObjCnt=0;tempDedCount=0;
				double creditTot = 0.0,debitTot=0.0;
				if(empCount<Integer.parseInt(recPerPage)){
					
					if(printHeader)
						repData[empCount][0] = String.valueOf(e+1);
					else
						repData[empCount][0] ="";
						
						repData[empCount][1] = String.valueOf(finalCredit[e][1]);
					
					
					HashMap creTotMap= new HashMap();
					HashMap debTotMap= new HashMap();
					
					for (int i = 2; i < repHeader[0].length; i++) {
						
						int tempJ =i;
						int tempI =i;
						if(i==2){
							row1 = (double) creditHead.length / Integer.parseInt(colPerPage);
							java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
									row1);
							payCount = Integer.parseInt(bigDecRow1.setScale(0,
									java.math.BigDecimal.ROUND_UP).toString());
								for (int j = 0; j < payCount; j++) {
									repData[empCount][i] = String.valueOf(repData[empCount][i]) +"PAY =>" +"\n";
								}
								
								row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
								java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
										row1);
								dedCount = Integer.parseInt(bigDecRow2.setScale(0,
										java.math.BigDecimal.ROUND_UP).toString());
									for (int j = 0; j < dedCount; j++) {
										repData[empCount][i] = String.valueOf(repData[empCount][i]) +"DED =>" +"\n";
									}
									repData[empCount][i] = repData[empCount][i] +"NET =>";
									
						}else if(i<repHeader[0].length-1){
							tempCount= creCount;
							
							for (int j = 0; j < payCount; j++) {
								if(creCount< creditHead.length){
									String colTotal = (String)creTotMap.get("a"+String.valueOf(j));
								
									if(colTotal==null)
										colTotal ="0.0";
									
									colTotal = String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble(String.valueOf(finalCredit[e][tempJ])));
									creTotMap.put("a"+String.valueOf(j),colTotal);
									
									repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble(String.valueOf(finalCredit[e][tempJ])))+"\n" ;
									
									creCount += Integer.parseInt(colPerPage);
									tempJ += Integer.parseInt(colPerPage);
								}
								else{
									repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ;
								}
							}
							creCount = tempCount;
							creCount++;
						
							
							tempDedCount= dedObjCnt;
							for (int j = 0; j < dedCount; j++) {
								if(dedObjCnt< debitHead.length){
									String colTotal = (String)debTotMap.get("a"+String.valueOf(j));
									if(colTotal==null)
										colTotal ="0.0";
									
									colTotal = String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble(String.valueOf(finalDebit[e][tempI])));
									debTotMap.put("a"+String.valueOf(j), colTotal);
									
									repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble(String.valueOf(finalDebit[e][tempI])))+"\n" ;
									
									dedObjCnt += Integer.parseInt(colPerPage);
									tempI += Integer.parseInt(colPerPage);
								}
								else{
									repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ;
								}
							}
							dedObjCnt = tempDedCount;
							dedObjCnt++;
							
						}else{
							
							for (int j = 0; j < payCount; j++) {
								creditTot += Double.parseDouble(String.valueOf(creTotMap.get("a"+String.valueOf(j))));
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble((String)creTotMap.get("a"+String.valueOf(j))))+"\n";
								finalCreTotal += Double.parseDouble(String.valueOf(creTotMap.get("a"+String.valueOf(j))));
							}
							for (int j = 0; j < dedCount; j++) {
								debitTot += Double.parseDouble(String.valueOf(debTotMap.get("a"+String.valueOf(j))));
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble((String)debTotMap.get("a"+String.valueOf(j))))+"\n";
								finalDebTotal  += Double.parseDouble(String.valueOf(debTotMap.get("a"+String.valueOf(j))));
							}
							repData[empCount][i] = repData[empCount][i]+ formatter.format(creditTot - debitTot);
						
						}
															
					}
					creditTot=0l;debitTot=0l;
					empCount++;
					
				} else{
					if(printHeader)				
						rg.tableBodyBold(header, repData, cellwidth,alignment,10);
					else
						rg.tableBody(repData, cellwidth, alignment,10);

					rg.pageBreak();
					
					int pendingCount = finalCredit.length - (e);
					if(pendingCount < Integer.parseInt(recPerPage))
						repData = new Object[pendingCount][Integer.parseInt(colPerPage)+4];
					
					repData = intSpace(repData);
					pageTotalFinal= intSpace(pageTotalFinal);
					pageTotDebit = intZero(pageTotDebit);
					pageTotCredit = intZero(pageTotCredit);
					empCount=0;
					payCount=0;creCount=0;tempCount=0;dedCount=0;dedObjCnt=0;tempDedCount=0;
					e--;
					
					
				}
			}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			if(printHeader)				
				rg.tableBodyBold(header, repData, cellwidth,alignment,10);
			else
				rg.tableBody(repData, cellwidth, alignment,10);
			//rg.tableBody(repHeader, cellwidth, alignment,12);
			//rg.tableBody(repData, cellwidth, alignment,12);
			
			
		}catch(Exception e){
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		return rg;
	}
	
			
	public Object[][] intSpace(Object[][] tempObj){
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "";
				}
			}
		} catch (Exception e) {
			
		}
		return tempObj;
	}
	public String[] intSpace(String[] tempObj){
		try {
				for (int j = 0; j < tempObj.length; j++) {
					tempObj[j] = "";
				}
		} catch (Exception e) {
			
		}
		return tempObj;
	}
	public Object[][] intZero(Object[][] tempObj){
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "0";
				}
			}
		} catch (Exception e) {
			
		}
		return tempObj;
	}

	public static String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "0";
		}
		else{
			return result;
		}
	}
}
