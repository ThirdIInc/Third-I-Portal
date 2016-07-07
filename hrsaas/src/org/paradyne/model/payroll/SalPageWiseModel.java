package org.paradyne.model.payroll;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.SalaryPayBill;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;


/**
 * @author  venkatesh/prakash
 * Date:30-05-2009
 */
public class SalPageWiseModel extends ModelBase { 
	 NumberFormat formatter = new DecimalFormat("#0");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalPageWiseModel.class); 
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader(String ledgerCode,String year) {
		Object credit_header[][] = null;
		try{			
		
			String query ="SELECT DISTINCT  PARENT_TABLE.PARENT_CREDIT,TRIM(PARENT_TABLE.CREDIT_ABBR) FROM HRMS_SAL_CREDITS_"+year+" " +
							" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE" 
							+" INNER JOIN HRMS_CREDIT_HEAD PARENT_TABLE ON (HRMS_CREDIT_HEAD.PARENT_CREDIT=PARENT_TABLE.CREDIT_CODE)"
							+" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") ORDER BY  PARENT_TABLE.PARENT_CREDIT";
		
		credit_header = getSqlModel().getSingleResult(query);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_header;
	} // end of method getCreditHeader()
	public Object[][] getCreditHeaderOld(String ledgerCode,String year) {
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
	
	public Object[][] getArrearCreditHeader(String arrearsCode,String year) {
		Object credit_header[][] = null;
		try{			
		
			String query ="SELECT DISTINCT ARREARS_CREDIT_CODE,TRIM(CREDIT_ABBR) FROM HRMS_ARREARS_CREDIT_"+year+" " +
							" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE" +
							" WHERE ARREARS_CODE IN("+arrearsCode+") ORDER BY ARREARS_CREDIT_CODE";
		
		credit_header = getSqlModel().getSingleResult(query);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_header;
	}
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return DEBITS Object 'debit_header'
	 */
	public Object[][] getDebitHeaderOld(String ledgerCode,String year) {
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
	
	public Object[][] getDebitHeader(String ledgerCode,String year) {
		Object debit_header[][] = null;
		try{
					
			String query ="SELECT DISTINCT  PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR) FROM HRMS_SAL_DEBITS_"+year+" " +
						" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE" 
						+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE)"
						+" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") ORDER BY PARENT_TABLE.PARENT_DEBIT";

			
		debit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	}  // end of method getDebitHeader()
	
	public Object[][] getArrearsDebitHeader(String arrearsCode,String year) {
		Object debit_header[][] = null;
		try{
					
			String query ="SELECT DISTINCT  PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR) FROM HRMS_ARREARS_DEBIT_"+year+" " +
						" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE " 
						+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE) "
						+" WHERE ARREARS_CODE IN("+arrearsCode+") ORDER BY PARENT_TABLE.PARENT_DEBIT";

			
		debit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	}  // end of method getDebitHeader()
		
	public void genReport(SalaryPayBill bean, HttpServletResponse response,String recPerPage,String colPerPage)
	{
		String name = "Salary Pay Bill";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean.getReport(), name,new int[]{10,9,10,10});
		try {
				
			/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);*/
			
			rg.setFName("Salary Pay Bill_"
					+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
					+ bean.getYear());
			String reportName="Salary Pay Bill For The Month "
					+ Utility.month(Integer.parseInt(bean.getMonth()))+ " , " + bean.getYear()+"\n";
			if(!bean.getPaybillName().equals("")){
				reportName="Salary PayBill of "+bean.getPaybillName()+" For The Month "
					+ Utility.month(Integer.parseInt(bean.getMonth()))+ " , " + bean.getYear()+"\n";
			}
			rg.addTextBold(reportName, 0, 1, 1,17);
			
			rg.addTextBold(bean.getDivName(), 0, 1, 1,17);
			
			//rg.addText("Date: " + dateData[0][0]+"\n", 0, 2, 0,17);
			//rg.pageBreak();	
			boolean consCheck = false;
			if(bean.getCheckFlag().equals("Y"))
				consCheck=true;
			
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
									+ bean.getMonth()
									+ " AND LEDGER_YEAR="
									+ bean.getYear()
									+ " AND LEDGER_DIVISION="
									+ bean.getDivCode()
									+" AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL')";
							
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		
			String ledgerCode = "";
			String arrledgerCode = "";
			String recledgerCode = "";
			if (ledgerData != null && ledgerData.length > 0){
				
				for (int i = 0; i < ledgerData.length; i++) {
					ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
				}
				ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
				
				bean.setLedgerCode(ledgerCode);
				
				Vector salVect = getSalary(bean);
				
				
				Object arrLedgerData[][]=null;
				Object recoveryLedgerData[][]=null;
				
				if(salVect.size()> 1){
					
						Object [][] salCredit=(Object[][])salVect.get(0);
						Object [][] salDebit=(Object[][])salVect.get(1);
						Object [][] totalSalCredit=(Object[][])salVect.get(2);
						Object [][] totalSalDebit=(Object[][])salVect.get(3);
						Object [][] salEmp=(Object[][])salVect.get(4);
						
						if(salEmp != null && salEmp.length > 0 ){
							
							Object [][] grnadCredit= new Object[1][totalSalCredit[0].length];
							Object [][] grnadDebit= new Object[1][totalSalDebit[0].length];
							grnadCredit=intSpace(grnadCredit);
							grnadDebit=intSpace(grnadDebit);
							String arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
								+ bean.getMonth()
								+ " AND ARREARS_PAID_YEAR ="
								+ bean.getYear()
								+ " AND ARREARS_DIVISION ="
								+ bean.getDivCode();
								//+" AND ARREARS_PAY_TYPE='ADD'";
							
								arrLedgerData = getSqlModel().getSingleResult(
										arrearLedgerQuery);
								
							String recoveryLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH = "
									+ bean.getMonth()
									+ " AND ARREARS_REF_YEAR ="
									+ bean.getYear()
									+ " AND ARREARS_DIVISION ="
									+ bean.getDivCode()
									+" AND ARREARS_PAY_TYPE='DED'";
								
									recoveryLedgerData = getSqlModel().getSingleResult(
											recoveryLedgerQuery);
								
							if (recoveryLedgerData != null && recoveryLedgerData.length > 0){
								for (int i = 0; i < recoveryLedgerData.length; i++) {
										recledgerCode += String.valueOf(recoveryLedgerData[i][0]) + ",";
									}
								recledgerCode = recledgerCode.substring(0, recledgerCode.length() - 1);
								//Vector recoveryVect = getRecovery(bean, recledgerCode,ledgerCode);
								//Vector recoveryVect=null;
								/*	if (recoveryVect.size() > 1) {

								Object[][] recvCredit = (Object[][]) recoveryVect.get(0);
								Object[][] totalRecvCredit = (Object[][]) recoveryVect.get(1);
							//	Object[][] totalRecvDebit = (Object[][]) recoveryVect.get(3);
								//Object[][] recvEmp = (Object[][]) recoveryVect.get(4);
								for (int j = 0; j < totalSalCredit[0].length; j++) {
									grnadCredit[0][j] = String.valueOf(totalSalCredit[0][j]);
									 
								}
								try {
									int[] addCol = new int[grnadCredit[0].length - 4];
									for (int i = 0; i < addCol.length; i++) {
										addCol[i] = i + 3;
									}
									grnadCredit = consolidateArrearsObject(
													grnadCredit,
													totalRecvCredit, 0, addCol,
													grnadCredit[0].length,false);
									totalSalCredit = consolidateArrearsObject(
											totalSalCredit,
											totalRecvCredit, 0, addCol,
											totalSalCredit[0].length,false);
								} catch (Exception e) {
									e.printStackTrace();
								}
								for (int j = 0; j < totalSalCredit[0].length; j++) {
									grnadCredit[0][1] = String.valueOf(totalSalCredit[0][1]);
									 if(j >= 3){
										 
										 try {
											grnadCredit[0][j] = Double
													.parseDouble(checkNull(String
															.valueOf(totalSalCredit[0][j])))
													- Double
															.parseDouble(checkNull(String
																	.valueOf(totalRecvCredit[0][j])));
										} catch (Exception e) {
											e.printStackTrace();
										}
										}
								}
								try {
									for (int j = 0; j < totalSalDebit[0].length; j++) {
										grnadDebit[0][1] = String
												.valueOf(totalSalDebit[0][1]);
										if (j >= 3) {

											grnadDebit[0][j] = Double
													.parseDouble(checkNull(String
															.valueOf(totalSalDebit[0][j])))
													- Double
															.parseDouble(checkNull(String
																	.valueOf(totalRecvDebit[0][j])));
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									int[] addCol = new int[salCredit[0].length - 4];
									for (int i = 0; i < addCol.length; i++) {
										addCol[i] = i + 3;
									}
									salCredit = Utility.consolidateArrearsObject(salCredit,recvCredit, 0, addCol,salCredit[0].length);
								} catch (Exception e) {
									e.printStackTrace();
								}
								for (int j = 0; j < salCredit.length; j++) {
									
									for (int k = 0; k < recvCredit.length; k++) {
														
										if(String.valueOf(salCredit[j][0]).equals(String.valueOf(recvCredit[k][0]))){
											try{
												for (int l = 3; l < salCredit[0].length; l++) {
													
													salCredit[j][l] = Double.parseDouble((String.valueOf(salCredit[j][l]))) -
																						Double.parseDouble(checkNull(String.valueOf(recvCredit[k][l])));
																						
												}
											}catch(Exception e){
												e.printStackTrace();
											}
										}
									}
									
								}
								
								 * subtract recovery amt from salary end
								 
				
							}*/
							}
							if (bean.getPaybillFor().equals("AL")) {
							if (arrLedgerData != null && arrLedgerData.length > 0){
							
									for (int i = 0; i < arrLedgerData.length; i++) {
										arrledgerCode += String.valueOf(arrLedgerData[i][0]) + ",";
									}
									arrledgerCode = arrledgerCode.substring(0, arrledgerCode.length() - 1);
									bean.setArrLedgerCode(arrledgerCode);
									
									Vector arrVect = getArrear(bean,arrledgerCode);
									
									if(arrVect.size()> 1){	
										
										Object [][] arrCredit=(Object[][])arrVect.get(0);
										Object [][] arrDebit=(Object[][])arrVect.get(1);
										Object [][] totalArrCredit=(Object[][])arrVect.get(2);
										Object [][] totalArrDebit=(Object[][])arrVect.get(3);
										Object [][] arrEmp=(Object[][])arrVect.get(4);
							
										if (arrEmp != null && arrEmp.length > 0){
																					
											for (int j = 0; j < totalSalCredit[0].length; j++) {
												try {
													//grnadCredit[0][1] = String.valueOf(totalSalCredit[0][1]);
													if (j >= 3) {

														grnadCredit[0][j] = Double
																.parseDouble(checkNull(String
																		.valueOf(totalSalCredit[0][j])))
																+ Double
																		.parseDouble(checkNull(String
																				.valueOf(totalArrCredit[0][j])));
													}
												} catch (Exception e) {
													// TODO: handle exception
												}
											}
											for (int j = 0; j < totalSalDebit[0].length; j++) {
												grnadDebit[0][1] = String.valueOf(totalSalDebit[0][1]);
												try {
													if (j >= 3) {

														grnadDebit[0][j] = Double
																.parseDouble(checkNull(String
																		.valueOf(totalSalDebit[0][j])))
																+ Double
																		.parseDouble(checkNull(String
																				.valueOf(totalArrDebit[0][j])));
													}
												} catch (Exception e) {
													// TODO: handle exception
												}
											}
							
											if(consCheck){
												
												for (int j = 0; j < salCredit.length; j++) {
													
													for (int k = 0; k < arrCredit.length; k++) {
																		
														if(String.valueOf(salCredit[j][0]).equals(String.valueOf(arrCredit[k][0]))){
															try{
																for (int l = 3; l < salCredit[0].length; l++) {
																	
																	salCredit[j][l] = Double.parseDouble((String.valueOf(salCredit[j][l]))) +
																										Double.parseDouble(checkNull(String.valueOf(arrCredit[k][l])));
																										
																}
															}catch(Exception e){
																e.printStackTrace();
															}
														}
													}
													
												}
												
												for (int j = 0; j < salDebit.length; j++) {
													
													for (int k = 0; k < arrDebit.length; k++) {
																		
														if(String.valueOf(salDebit[j][0]).equals(String.valueOf(arrDebit[k][0]))){
															try{
																for (int l = 3; l < salDebit[0].length; l++) {
																	
																	salDebit[j][l] = Double.parseDouble((String.valueOf(salDebit[j][l]))) +
																							Double.parseDouble(checkNull(String.valueOf(arrDebit[k][l])));
																}
															}catch(Exception e){
																e.printStackTrace();
															}
															
															
														}
													}
													
												}
								
											rg.addTextBold("Salary With Consolidated Arrears", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, salCredit, salDebit, rg, response,true,salEmp,"S");
											rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Grand Total", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, grnadCredit, grnadDebit, rg, response,false,grnadCredit,"S");
															
									}else{
								
											/*rg.addTextBold("Salary Details", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);*/
											rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
											//rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Salary Total", 0, 0, 0,17);
											//rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");
											rg.addText("\n", 0, 0, 0);
											rg.pageBreak();
											rg.addTextBold("Arrear Details", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, arrCredit, arrDebit,rg, response,true,arrEmp,"A");
											rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Arrear Total", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, totalArrCredit, totalArrDebit,rg, response,false,totalArrCredit,"A");
											rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Grand Total", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, grnadCredit, grnadDebit, rg, response,false,grnadCredit,"S");
																					
										}
									}else{									
											/*rg.addTextBold("Salary Details", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);*/
											rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
											rg.addText("\n", 0, 0, 0);
											//rg.addTextBold("Salary Total", 0, 0, 0,17);
											//rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");

								}										
								}else{											
											/*rg.addTextBold("Salary Details", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);*/
											rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
											rg.addText("\n", 0, 0, 0);
											//rg.addTextBold("Salary Total", 0, 0, 0,17);
											//rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");
			
								}
								}else{
							
										/*rg.addTextBold("Salary Details", 0, 0, 0,17);
										rg.addText("\n", 0, 0, 0);*/
										rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
										rg.addText("\n", 0, 0, 0);
										//rg.addTextBold("Salary Total", 0, 0, 0,17);
										//rg.addText("\n", 0, 0, 0);
										rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");
								}
							}else{
								
								/*rg.addTextBold("Salary Details", 0, 0, 0,17);
								rg.addText("\n", 0, 0, 0);*/
								rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
								rg.addText("\n", 0, 0, 0);
								//rg.pageBreak();
								//rg.addTextBold("Salary Total", 0, 0, 0,17);
								//rg.addText("\n", 0, 0, 0);
								rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");
						}
					}else
						rg.addText("No records avaliable for selected criteria", 0, 1,1);	
				}else
					rg.addText("No records avaliable for selected criteria", 0, 1,1);	
			}else
				rg.addText("No records avaliable for selected criteria", 0, 1,1);	
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		rg.createReport(response);
	}
	
	private HashMap getAttendanceDetails(SalaryPayBill bean) {
		HashMap attendanceMap=new HashMap();
		try{
			
			String ledgerCode = bean.getLedgerCode();
			
					String empQuery = "SELECT HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_EMP_CODE,NVL(LEAVE_ABBR,''),"
								+" HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_LEAVE_ADJUST,NVL(HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_LEAVE_ADJUST_RECOVERY,0),"
								+" HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_UNPAID_LEVS,NVL(HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_RECOVERY_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+""
								+" LEFT JOIN HRMS_MONTH_ATT_DTL_"+ bean.getYear()+" ON (HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_CODE=HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_CODE AND "
								+" HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_EMP_ID=HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_EMP_CODE) "
								+" LEFT JOIN HRMS_LEAVE ON (HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_LEAVE_CODE=HRMS_LEAVE.LEAVE_ID AND LEAVE_ID NOT  IN (5,8))"
								+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_EMP_ID "
								+" AND ATT_LEAVE_AVAILABLE>0 AND  HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_CODE IN ("+ ledgerCode+"))";
					String conditionQuery=" WHERE HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_CODE IN("+ ledgerCode+")";
					
					
					if(!(bean.getBranchName().equals(""))){
						conditionQuery+=" AND EMP_CENTER="+bean.getBranchCode()+" " ;
					}
					if(!(bean.getPaybillName().equals(""))){
						conditionQuery+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
					}
					empQuery+=	conditionQuery;				
					empQuery += " ORDER BY HRMS_MONTH_ATT_DTL_"+ bean.getYear()+".ATT_EMP_CODE ";
					
					 attendanceMap=getSqlModel().getSingleResultMap(empQuery, 0, 2);
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	
		return attendanceMap;
	}
	private HashMap getChildDebitDetails(SalaryPayBill bean) {
		HashMap bankLoanMap=new HashMap();
		try{
			
			String ledgerCode = bean.getLedgerCode();
			
					String empQuery = "SELECT SAL_AMOUNT,DEBIT_ABBR,HRMS_EMP_OFFC.EMP_ID||'#'||PARENT_DEBIT,NVL(LIC_POLICY_NUMBER,'') FROM HRMS_SAL_DEBITS_"+bean.getYear()
									+" INNER JOIN HRMS_DEBIT_HEAD ON (DEBIT_CODE=SAL_DEBIT_CODE)  "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+bean.getYear()+".EMP_ID)"
									+" LEFT JOIN HRMS_LIC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LIC.EMP_ID and HRMS_SAL_DEBITS_"+bean.getYear()+".SAL_DEBIT_CODE= HRMS_LIC.LIC_PAID_IN_DEBIT_CODE)"
									+" WHERE PARENT_DEBIT!=DEBIT_CODE AND PARENT_DEBIT IS NOT NULL AND SAL_AMOUNT>0 AND HRMS_SAL_DEBITS_"+ bean.getYear()+".SAL_LEDGER_CODE IN ("+ ledgerCode+")";
					String conditionQuery=" ";
					
					
					if(!(bean.getBranchName().equals(""))){
						conditionQuery+=" AND EMP_CENTER="+bean.getBranchCode()+" " ;
					}
					if(!(bean.getPaybillName().equals(""))){
						conditionQuery+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
					}
					empQuery+=	conditionQuery;				
					empQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID,DEBIT_ABBR,PARENT_DEBIT ";
					
					bankLoanMap=getSqlModel().getSingleResultMap(empQuery, 2, 2);
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	
		return bankLoanMap;
	}
	private HashMap getEmpCreditDetails(SalaryPayBill bean,String reportType) {
		HashMap empCreditMap=new HashMap();
		try{
			
			String ledgerCode = bean.getLedgerCode();
			String empQuery="";
			if(reportType.equals("S")){
			 empQuery = "SELECT NVL(ORIGINAL_SAL_AMOUNT,nvl(SAL_AMOUNT,0)),CREDIT_NAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_SAL_CREDITS_"+bean.getYear()
							+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=SAL_CREDIT_CODE)  "
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_CREDITS_"+bean.getYear()+".EMP_ID)"
							+" WHERE CREDIT_CODE IN (1,2)  AND HRMS_SAL_CREDITS_"+bean.getYear()+".SAL_LEDGER_CODE IN ("+ ledgerCode+")";
			String conditionQuery=" ";
			
			if(!(bean.getBranchName().equals(""))){
				conditionQuery+=" AND EMP_CENTER="+bean.getBranchCode()+" " ;
			}
			if(!(bean.getPaybillName().equals(""))){
				conditionQuery+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
			}
			empQuery+=	conditionQuery;				
			empQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID,CREDIT_CODE ";
			}
			else if(reportType.equals("A")){
				 empQuery = " SELECT NVL(ORIGINAL_SAL_AMOUNT,nvl(SAL_AMOUNT,0)),CREDIT_NAME,HRMS_EMP_OFFC.EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR,CREDIT_CODE FROM HRMS_SAL_CREDITS_"+bean.getYear()
						+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=SAL_CREDIT_CODE)  "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_CREDITS_"+bean.getYear()+".EMP_ID)"
						+" WHERE CREDIT_CODE IN (1,2) ";

				 String conditionQuery=" ";
					
					if(!(bean.getBranchName().equals(""))){
						conditionQuery+=" AND EMP_CENTER="+bean.getBranchCode()+" " ;
					}
					if(!(bean.getPaybillName().equals(""))){
						conditionQuery+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
					}
					empQuery+=	conditionQuery;	
					empQuery+=" UNION ALL ";
					empQuery+=" SELECT NVL(ORIGINAL_SAL_AMOUNT,nvl(SAL_AMOUNT,0)),CREDIT_NAME,HRMS_EMP_OFFC.EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR,CREDIT_CODE FROM HRMS_SAL_CREDITS_"+(Integer.parseInt(bean.getYear())-1)
						+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=SAL_CREDIT_CODE)  "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_CREDITS_"+(Integer.parseInt(bean.getYear())-1)+".EMP_ID)"
						+" WHERE CREDIT_CODE IN (1,2) ";
					empQuery+=	conditionQuery;	
					empQuery += " ORDER BY 3,2 ";
					
			}
			
			
			empCreditMap=getSqlModel().getSingleResultMap(empQuery, 2, 2);
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		
		return empCreditMap;
	}
	public void genArrearsReport(SalaryPayBill bean, HttpServletResponse response,String recPerPage,String colPerPage)
	{
		String name = "Supplimentory Pay Bill";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean.getReport(), name,new int[]{10,9,9,10});
		try {
				
			/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);*/
			
			rg.setFName("Supplimentory Pay Bill_"
					+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
					+ bean.getYear());
			rg.addTextBold("Supplimentory Pay Bill For The Month "
					+ Utility.month(Integer.parseInt(bean.getMonth()))
					+ " , " + bean.getYear()+"\n", 0, 1, 1,17);
			
			rg.addTextBold(bean.getDivName(), 0, 1, 1,17);
			
			//rg.addText("Date: " + dateData[0][0]+"\n", 0, 2, 0,17);
			
			String arrledgerCode = "";
				
							String arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
								+ bean.getMonth()
								+ " AND ARREARS_PAID_YEAR ="
								+ bean.getYear()
								+ " AND ARREARS_PAY_TYPE!='DED' AND ARREARS_DIVISION ="
								+ bean.getDivCode();
		
							Object arrLedgerData[][] = getSqlModel().getSingleResult(arrearLedgerQuery);
						
							if (arrLedgerData != null && arrLedgerData.length > 0){
							
									for (int i = 0; i < arrLedgerData.length; i++) {
										arrledgerCode += String.valueOf(arrLedgerData[i][0]) + ",";
									}
									arrledgerCode = arrledgerCode.substring(0, arrledgerCode.length() - 1);
									bean.setArrLedgerCode(arrledgerCode);
									
									Vector arrVect = getArrear(bean,arrledgerCode);
									
									if(arrVect.size()> 1){	
										
										Object [][] arrCredit=(Object[][])arrVect.get(0);
										Object [][] arrDebit=(Object[][])arrVect.get(1);
										Object [][] totalArrCredit=(Object[][])arrVect.get(2);
										Object [][] totalArrDebit=(Object[][])arrVect.get(3);
										Object [][] arrEmp=(Object[][])arrVect.get(4);
							
										if (arrEmp != null && arrEmp.length > 0){
																					
																	
											
											/*rg.addTextBold("Arrear Details", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);*/
											rg=printreport(bean, arrCredit, arrDebit,rg, response,true,arrEmp,"A");
											rg.addText("\n", 0, 0, 0);
											rg.addTextBold("Arrear Total", 0, 0, 0,17);
											rg.addText("\n", 0, 0, 0);
											rg=printreport(bean, totalArrCredit, totalArrDebit,rg, response,false,totalArrCredit,"A");
																					
										
									}else{									
										rg.addText("No records avaliable for selected criteria", 0, 1,1);	

								}										
								}else{											
									rg.addText("No records avaliable for selected criteria", 0, 1,1);	
			
								}
								}else{
									rg.addText("No records avaliable for selected criteria", 0, 1,1);	
									}
					
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		rg.createReport(response);
	}
	public Vector getSalary(SalaryPayBill bean){
		Vector vect = new Vector();
		try{
			
			String ledgerCode = bean.getLedgerCode();
			
					String empQuery = " SELECT hrms_salary_"+ bean.getYear()+".emp_id,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,SAL_DEPT,DEPT_NAME,NVL(SAL_DAYS,0)-NVL(ATTN_RECOVERY_DAYS,0)," +
					"  nvl(BANK_NAME,''),nvl(SAL_ACCNO_REGULAR,''),nvl(SAL_PAYSCALE,''),NVL(SALGRADE_TYPE,' ') ";
					
				//	if(bean.getCheckBrn().equals("Y"))
						empQuery +=" ,SAL_EMP_CENTER,CENTER_NAME ";
				//	if(bean.getCheckDesg().equals("Y"))	
						empQuery +=" ,SAL_EMP_RANK,RANK_NAME,REPORTING.EMP_FNAME||' '||REPORTING.EMP_LNAME";
					
					empQuery +=" from hrms_salary_"+ bean.getYear()
						+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+ bean.getYear()+ ".EMP_ID "
						+ " LEFT JOIN HRMS_EMP_OFFC REPORTING ON REPORTING.EMP_ID = HRMS_EMP_OFFC.EMP_REPORTING_OFFICER "
						+ " INNER JOIN HRMS_DEPT on HRMS_DEPT.DEPT_ID = hrms_salary_"+ bean.getYear()+ ".SAL_DEPT " +
						" LEFT JOIN HRMS_SALARY_MISC	 ON HRMS_SALARY_MISC.EMP_ID =  hrms_salary_"+ bean.getYear()+".EMP_ID " 
						+" INNER JOIN HRMS_MONTH_ATTENDANCE_"+bean.getYear()+" ON (HRMS_MONTH_ATTENDANCE_"+bean.getYear()+".ATTN_CODE=HRMS_SALARY_"+ bean.getYear()+".SAL_LEDGER_CODE"
							+" AND HRMS_MONTH_ATTENDANCE_"+ bean.getYear()+".ATTN_EMP_ID=HRMS_SALARY_"+ bean.getYear()+".EMP_ID) "		
						+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR  "+
						" LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
				//	if(bean.getCheckBrn().equals("Y"))
						empQuery += " inner join hrms_center on   hrms_center.CENTER_ID = hrms_salary_"+ bean.getYear()+".SAL_EMP_CENTER ";
				//	if(bean.getCheckDesg().equals("Y"))	
						empQuery += " inner join hrms_rank on hrms_rank.RANK_ID = hrms_salary_"+ bean.getYear()+".SAL_EMP_RANK ";
					
					empQuery += " where sal_ledger_code in ("+ ledgerCode	+ ")  ";
					
					if(!(bean.getOnHold().equals("A"))){
						empQuery+="AND sal_onhold='"+bean.getOnHold()+"' " ;
					}
					if(!(bean.getBranchName().equals(""))){
						empQuery+="AND SAL_EMP_CENTER="+bean.getBranchCode()+" " ;
					}
					if(!(bean.getPaybillName().equals(""))){
						empQuery+=" AND HRMS_EMP_OFFC.EMP_PAYBILL="+bean.getPaybillId()+" " ;
					}
					
					
					empQuery += "ORDER BY";
					
					if(bean.getChkBrnOrder().equals("Y"))
						empQuery += " SAL_EMP_CENTER, ";
					
					if(bean.getChkDeptOrder().equals("Y"))
						empQuery += " SAL_DEPT, ";
					
					if(bean.getChkDesgOrder().equals("Y"))
						empQuery += "  SAL_EMP_RANK,";
						
					empQuery +="  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		
					Object [][] empData = getSqlModel().getSingleResult(empQuery);
					
					if (empData != null && empData.length > 0) {
						
						String creditQuery="";
						String creditQuery1=" ";
						String creditQuery2="";String creditQuery3="",creditQuery4="";;
						String debitQuery="",debitQuery1="",debitQuery2="",debitQuery3="";
						String count="0",totalCreditQuery1="",totalCreditQuery="",groupBy="",totalDebitQuery1="",totalDebitQuery="";
						
						Object[][] creditHead = getCreditHeader(ledgerCode,bean.getYear());
						Object[][] debitHead = getDebitHeader(ledgerCode,bean.getYear());
						
						for (int i = 0; i < creditHead.length; i++) {
							if(i==0){
								creditQuery1 += "select a"+i+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'PAY',NVL(a"+i+".sal,0),";
								creditQuery2 += " from (select emp_id,sum(SAL_AMOUNT) sal from HRMS_SAL_CREDITS_"+bean.getYear()+" " 
												+" inner join hrms_credit_head on(SAL_credit_CODE=credit_code)"
												+" where PARENT_CREDIT = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")  group by emp_id) a"+i+" ";
								
								totalCreditQuery1 += "select ' ','Salary Total  [' || count(*)|| '] ','PAY', sum(a"+i+".sal),";
								
								creditQuery4 = "inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".emp_id " +
												" inner join hrms_salary_"+bean.getYear()+" on  (hrms_salary_"+bean.getYear()+".emp_id = a"+i+".emp_id  " +
												" and SAL_LEDGER_CODE in ("+ledgerCode+"))";
								
												if(!(bean.getOnHold().equals("A"))){
													creditQuery4+="AND sal_onhold='"+bean.getOnHold()+"' " ;
												}
												if(!(bean.getBranchName().equals(""))){
													creditQuery4+="AND SAL_EMP_CENTER="+bean.getBranchCode()+" " ;
												}				
												if(!(bean.getPaybillName().equals(""))){
													creditQuery4+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
												}
												creditQuery4 += "ORDER BY";
												
												if(bean.getChkBrnOrder().equals("Y"))
													creditQuery4 += " SAL_EMP_CENTER, ";
												
												if(bean.getChkDeptOrder().equals("Y"))
													creditQuery4 += " SAL_DEPT, ";
												
												if(bean.getChkDesgOrder().equals("Y"))
													creditQuery4 += "  SAL_EMP_RANK,";
													
												creditQuery4 +="  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
									
								
								
								groupBy=" inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".emp_id " +
								" inner join hrms_salary_"+bean.getYear()+" on  (hrms_salary_"+bean.getYear()+".emp_id = a"+i+".emp_id  " +
										" and SAL_LEDGER_CODE in ("+ledgerCode+"))";
						
										if(!(bean.getOnHold().equals("A"))){
											groupBy+="AND sal_onhold='"+bean.getOnHold()+"' " ;
										}
										if(!(bean.getBranchName().equals(""))){
											groupBy+=" AND SAL_EMP_CENTER="+bean.getBranchCode()+"  " ;
										}
										if(!(bean.getPaybillName().equals(""))){
											groupBy+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
										}
								
								count=String.valueOf(i);
							}
							else{
								creditQuery1 += "nvl(a"+i+".sal,0),";
								totalCreditQuery1 += "nvl(sum(a"+i+".sal),0),";
								creditQuery3 += " left join (select emp_id,sum(SAL_AMOUNT) sal from HRMS_SAL_CREDITS_"+bean.getYear()
								+" inner join hrms_credit_head on(SAL_credit_CODE=credit_code)"
								+" where PARENT_CREDIT = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+") group by emp_id) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";
							}
														
						}
						/*if(!bean.getBranchName().equals("")){
							creditQuery3 +=" WHERE SAL_EMP_CENTER="+bean.getBranchCode();
						}*/
						
						creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4;
						totalCreditQuery = totalCreditQuery1.substring(0,totalCreditQuery1.length()-1) + creditQuery2 +creditQuery3+groupBy;
						logger.info("creditQuery "+creditQuery );
						logger.info("total Crdit query"+totalCreditQuery);
						
						for (int i = 0; i < debitHead.length; i++) {
							if(i==0){
								debitQuery1 += "select a"+i+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'PAY',NVL(a"+i+".sal,0),";
								debitQuery2 += " from (select emp_id,sum(SAL_AMOUNT) sal  from HRMS_SAL_debits_"+bean.getYear()+" " 
								+" inner join hrms_debit_head on(SAL_debit_CODE=debit_code)"		
								+" where PARENT_DEBIT = "+String.valueOf(debitHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")  group by emp_id) a"+i+" ";
								count=String.valueOf(i);
								totalDebitQuery1 += "select ' ','Total  [' || count(*)|| '] ','PAY', sum(a"+i+".sal),";
								
							}
							else{
								debitQuery1 += "NVL(a"+i+".sal,0),";
								totalDebitQuery1 += "NVL(sum(a"+i+".sal),0),";
								debitQuery3 += " left join (select emp_id,sum(SAL_AMOUNT) sal from HRMS_SAL_debits_"+bean.getYear()
								+" inner join hrms_debit_head on(SAL_debit_CODE=debit_code)"	
								+" where PARENT_DEBIT = "+String.valueOf(debitHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+") group by emp_id) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";
								
							}
														
						}
						/*if(!bean.getBranchName().equals("")){
							debitQuery3 +=" WHERE SAL_EMP_CENTER="+bean.getBranchCode();
						}*/
						debitQuery = debitQuery1.substring(0,debitQuery1.length()-1) + debitQuery2 +debitQuery3+creditQuery4;
						//totalDebitQuery = totalDebitQuery1.substring(0,totalDebitQuery1.length()-1) + debitQuery2 +debitQuery3+creditQuery4;
						totalDebitQuery = totalDebitQuery1.substring(0,totalDebitQuery1.length()-1) + debitQuery2 +debitQuery3+groupBy;
						logger.info("debitQuery "+debitQuery );
						logger.info("totalDebitQuery"+totalDebitQuery);
						
						Object [][]empCreditData = getSqlModel().getSingleResult(creditQuery);
						logger.info("empCreditData.LENGTH "+empCreditData.length );
						Object [][]empDebitData = getSqlModel().getSingleResult(debitQuery);
						logger.info("empDebitData.LENGTH "+empDebitData.length );
						
						Object[][] totalCreditObj = getSqlModel().getSingleResult(totalCreditQuery);
						Object[][] totalDebitObj= getSqlModel().getSingleResult(totalDebitQuery);
						
						vect.add(0, empCreditData);
						vect.add(1, empDebitData);
						vect.add(2, totalCreditObj);
						vect.add(3, totalDebitObj);
						vect.add(4,empData);
						
					}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return vect;
	}
	public Vector getRecovery(SalaryPayBill bean,String ledgerCode,String salLedgerCode){
		Vector vect = new Vector();
		try{
			
			//String ledgerCode = bean.getLedgerCode();		
				
						
						String creditQuery="";
						String creditQuery1=" ";
						String creditQuery2="";String creditQuery3="",creditQuery4="";;
						String debitQuery="",debitQuery1="",debitQuery2="",debitQuery3="";
						String count="0",totalCreditQuery1="",totalCreditQuery="",groupBy="",totalDebitQuery1="",totalDebitQuery="";
						
						Object[][] creditHead = getCreditHeader(salLedgerCode,bean.getYear());
						//Object[][] debitHead = getDebitHeader(salLedgerCode,bean.getYear());
						
						for (int i = 0; i < creditHead.length; i++) {
							if(i==0){
								creditQuery1 += "select a"+i+".ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'PAY',a"+i+".ARREARS_AMT,";
								creditQuery2 += " from (select ARREARS_EMP_ID,ARREARS_AMT from HRMS_ARREARS_CREDIT_"+bean.getYear()+" " +
												" where ARREARS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and ARREARS_CODE in ("+ledgerCode+")) a"+i+" ";
								
								totalCreditQuery1 += "select ' ','Total  [' || count(*)|| '] ','PAY', sum(a"+i+".ARREARS_AMT),";
								
								creditQuery4 = "LEFT join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".ARREARS_EMP_ID  " +
												" LEFT join HRMS_ARREARS_"+bean.getYear()+" on  (hrms_ARREARS_"+bean.getYear()+".emp_id = a"+i+".ARREARS_EMP_ID  " +
												" and ARREARS_CODE in ("+ledgerCode+"))";
								
												if(!(bean.getOnHold().equals("A"))){
													creditQuery4+="AND ARREARS_ONHOLD='"+bean.getOnHold()+"' " ;
												}
												if(!(bean.getBranchName().equals(""))){
													creditQuery4+="AND EMP_CENTER="+bean.getBranchCode()+" " ;
												}	
												if(!(bean.getPaybillName().equals(""))){
													creditQuery4+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
												}
												
												creditQuery4 += "ORDER BY";
												
												if(bean.getChkBrnOrder().equals("Y"))
													creditQuery4 += " EMP_CENTER, ";
												
												if(bean.getChkDeptOrder().equals("Y"))
													creditQuery4 += " EMP_DEPT, ";
												
												if(bean.getChkDesgOrder().equals("Y"))
													creditQuery4 += "  EMP_RANK,";
													
												creditQuery4 +="  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
									
								
								
								groupBy="LEFT join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".ARREARS_EMP_ID " +
										" LEFT join hrms_ARREARS_"+bean.getYear()+" on  (hrms_ARREARS_"+bean.getYear()+".emp_id = a"+i+".ARREARS_EMP_ID  " +
										" and ARREARS_CODE in ("+ledgerCode+"))";
						
										if(!(bean.getOnHold().equals("A"))){
											groupBy+="AND ARREARS_ONHOLD='"+bean.getOnHold()+"' " ;
										}
										if(!(bean.getBranchName().equals(""))){
											groupBy+=" AND EMP_CENTER="+bean.getBranchCode()+"  " ;
										}
										if(!(bean.getPaybillName().equals(""))){
											groupBy+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
										}
								
								count=String.valueOf(i);
							}
							else{
								creditQuery1 += "a"+i+".ARREARS_AMT,";
								totalCreditQuery1 += "sum(a"+i+".ARREARS_AMT),";
								creditQuery3 += " LEFT join (select ARREARS_EMP_ID,ARREARS_AMT from HRMS_ARREARS_CREDIT_"+bean.getYear()+" where ARREARS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and ARREARS_CODE in ("+ledgerCode+")) a"+i+" on a"+count+".arrears_emp_id = a"+i+".arrears_emp_id ";
							}
														
						}
						/*if(!bean.getBranchName().equals("")){
							creditQuery3 +=" WHERE SAL_EMP_CENTER="+bean.getBranchCode();
						}*/
						
						creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4;
						totalCreditQuery = totalCreditQuery1.substring(0,totalCreditQuery1.length()-1) + creditQuery2 +creditQuery3+groupBy;
						logger.info("creditQuery "+creditQuery );
						logger.info("total Crdit query"+totalCreditQuery);
						
					
						/*if(!bean.getBranchName().equals("")){
							debitQuery3 +=" WHERE SAL_EMP_CENTER="+bean.getBranchCode();
						}*/
						
						
						Object [][]empCreditData = getSqlModel().getSingleResult(creditQuery);
						logger.info("empCreditData.LENGTH "+empCreditData.length );
						
						Object[][] totalCreditObj = getSqlModel().getSingleResult(totalCreditQuery);
						//Object[][] totalDebitObj= getSqlModel().getSingleResult(totalDebitQuery);
						
						vect.add(0, empCreditData);
						vect.add(1, totalCreditObj);
						//vect.add(3, totalDebitObj);
						//vect.add(4,empData);
						
					
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return vect;
	}
	
	public Vector getArrear(SalaryPayBill bean,String arrledgerCode){
		Vector vect = new Vector();
		try{
			
			//String arrledgerCode=bean.getArrLedgerCode();	
			
			String empQuery = " SELECT HRMS_ARREARS_"+ bean.getYear()+".EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH'),ARREARS_YEAR,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional')," 
								+" NVL('',''),SUM(ARREARS_DAYS),nvl(BANK_NAME,''),nvl(SAL_ACCNO_REGULAR,''),nvl('',''),EMP_CENTER,NVL(CENTER_NAME,' '),EMP_DEPT,NVL(DEPT_NAME,' ')," 
								+" EMP_RANK,NVL(RANK_NAME,' '), HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_MONTH, " 
								+" CASE WHEN HRMS_ARREARS_"+ bean.getYear()+".ARREARS_FOR='R' THEN TO_CHAR(HRMS_LEAVE.LEAVE_ABBR||':'||SUM(ARREARS_DAYS)) WHEN  HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_FOR='A' THEN TO_CHAR('PRS'||':'||SUM(ARREARS_DAYS)) ELSE 'MISC PAY' END 	,"
								+" HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_NARRATION,HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_FOR,HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_LEAVE_CODE ";
				
			/*empQuery += " FROM HRMS_ARREARS_"+ bean.getYear()
						+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+ bean.getYear()+ ".EMP_ID "
						+" INNER JOIN HRMS_SALARY_"+ bean.getYear()+ " ON  HRMS_SALARY_"+ bean.getYear()+ ".EMP_ID = HRMS_ARREARS_"+ bean.getYear()+ ".EMP_ID AND  HRMS_SALARY_"+ bean.getYear()+ ".SAL_LEDGER_CODE IN ("+ledgerCode+")"
						+" INNER JOIN HRMS_ARREARS_LEDGER ON HRMS_ARREARS_LEDGER.ARREARS_CODE =  HRMS_ARREARS_"+ bean.getYear()+".ARREARS_CODE "
						+" INNER JOIN HRMS_CENTER ON   HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+ bean.getYear() + ".SAL_EMP_CENTER "
						+" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_SALARY_"+ bean.getYear() + ".SAL_DEPT "
						+" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_SALARY_"+ bean.getYear() + ".SAL_EMP_RANK "
						+" LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE "
						+" LEFT JOIN HRMS_SALARY_MISC	 ON HRMS_SALARY_MISC.EMP_ID =  HRMS_SALARY_"+ bean.getYear()+".EMP_ID " 
						+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR  ";*/
				
				empQuery += " FROM HRMS_ARREARS_"+ bean.getYear()
						+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+ bean.getYear()+ ".EMP_ID "
						//+" INNER JOIN HRMS_SALARY_"+ bean.getYear()+ " ON  HRMS_SALARY_"+ bean.getYear()+ ".EMP_ID = HRMS_ARREARS_"+ bean.getYear()+ ".EMP_ID AND  HRMS_SALARY_"+ bean.getYear()+ ".SAL_LEDGER_CODE IN ("+ledgerCode+")"
						+" INNER JOIN HRMS_ARREARS_LEDGER ON HRMS_ARREARS_LEDGER.ARREARS_CODE =  HRMS_ARREARS_"+ bean.getYear()+".ARREARS_CODE "
						+" INNER JOIN HRMS_CENTER ON   HRMS_CENTER.CENTER_ID = EMP_CENTER "
						+" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = EMP_DEPT "
						+" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = EMP_RANK "
						//+" LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE "
						+" LEFT JOIN HRMS_SALARY_MISC	 ON HRMS_SALARY_MISC.EMP_ID =  HRMS_EMP_OFFC.EMP_ID " 
						+" LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID=HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_LEAVE_CODE)"
						+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR  ";
			
				empQuery += " WHERE ARREARS_CODE IN (" + arrledgerCode + ")  ";
			
				if (!(bean.getOnHold().equals("A")))
					empQuery += "AND ARREARS_ONHOLD= '" + bean.getOnHold() + "' ";
				
				if(!(bean.getBranchName().equals(""))){
					empQuery+="AND EMP_CENTER="+bean.getBranchCode()+"  " ;
				}
				if(!(bean.getPaybillName().equals(""))){
					empQuery+=" AND EMP_PAYBILL="+bean.getPaybillId()+" " ;
				}
				empQuery+=" group by HRMS_ARREARS_"+ bean.getYear()+ ".EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH'),ARREARS_YEAR,"
					+" DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional'),NVL('',''),nvl(BANK_NAME,''),nvl(SAL_ACCNO_REGULAR,''),nvl('',''),"
					+" EMP_CENTER,NVL(CENTER_NAME,' '),EMP_DEPT,NVL(DEPT_NAME,' '),EMP_RANK,NVL(RANK_NAME,' '),HRMS_LEAVE.LEAVE_ABBR,ARREARS_MONTH,"
				    +" HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_FOR,HRMS_ARREARS_"+ bean.getYear()+ ".ARREARS_NARRATION,ARREARS_LEAVE_CODE ";
				empQuery += " ORDER BY ";
			
				if (bean.getChkBrnOrder().equals("Y"))
					empQuery += " EMP_CENTER, ";
			
				if (bean.getChkDeptOrder().equals("Y"))
					empQuery += "  EMP_DEPT, ";
			
				if (bean.getChkDesgOrder().equals("Y"))
					empQuery += " EMP_RANK, ";
			
				empQuery += "  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
					
				//logger.info("arrearQuery------------"+empQuery);
				Object[][]empData = getSqlModel().getSingleResult(empQuery);
				
				Object creditTotalObj[][]= null;
				Object debitTotalObj[][]= null;
				
				String [][] creditHead=null;
				String [][]debitHead=null;
				
				Object finalCreditObj[][]=null;
				Object finalDebitObj[][]=null;
				String creditQuery ="  SELECT ARREARS_CREDIT_CODE,TRIM(CREDIT_ABBR),sum(ARREARS_AMT)  ,ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||"+
						" ARREARS_FOR||'#'||ARREARS_LEAVE_CODE||'#'||DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') FROM HRMS_ARREARS_CREDIT_"+bean.getYear()
						+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ bean.getYear()+ ".ARREARS_CODE  "
						+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+ bean.getYear()+ ".ARREARS_CREDIT_CODE  "
						+" WHERE HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrledgerCode+")"
						//+" AND ARREARS_EMP_ID = "+String.valueOf(empData[i][0]) 
						//+" AND TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') LIKE '"+String.valueOf(empData[i][3])+"'" 
						//+" AND ARREARS_YEAR = "+String.valueOf(empData[i][4]) 
						//+" AND DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') LIKE '"+String.valueOf(empData[i][5])+"'" 
						+" GROUP BY ARREARS_CREDIT_CODE,TRIM(CREDIT_ABBR),ARREARS_EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_FOR,ARREARS_LEAVE_CODE,ARREARS_TYPE "
						+" ORDER BY ARREARS_EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_TYPE ,ARREARS_FOR,ARREARS_LEAVE_CODE,ARREARS_CREDIT_CODE ";
				
				
				String debitQuery ="  SELECT PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR),SUM(ARREARS_AMT),ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||" 
						+" ARREARS_FOR||'#'||ARREARS_LEAVE_CODE||'#'|| DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') FROM HRMS_ARREARS_DEBIT_"+bean.getYear()
						+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_CODE  "
						+" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_DEBIT_CODE  "
						+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE) "
						+" WHERE HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrledgerCode+") "
						//+" AND ARREARS_EMP_ID = "+String.valueOf(empData[i][0]) 
						//+" AND TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH')LIKE '"+String.valueOf(empData[i][3])+"'" 
						//+" AND ARREARS_YEAR ="+String.valueOf(empData[i][4]) 
						//+" AND DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') LIKE '"+String.valueOf(empData[i][5])+"'" 
						+" GROUP BY PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR),ARREARS_EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_FOR,ARREARS_LEAVE_CODE,ARREARS_TYPE"
						+" ORDER BY ARREARS_EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_TYPE ,ARREARS_FOR,ARREARS_LEAVE_CODE,PARENT_TABLE.PARENT_DEBIT";
				
				HashMap empCreditMap=getSqlModel().getSingleResultMap(creditQuery, 3, 2);
				HashMap empDebitMap=getSqlModel().getSingleResultMap(debitQuery, 3, 2);
				if (empData != null && empData.length > 0) {
					//arrear object with credit and debit
						for(int i=0;i<empData.length;i++){
								
							try{						
								
								int count=3;
								
									
														
								/*String debitQuery ="  SELECT PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR),SUM(ARREARS_AMT),ARREARS_EMP_ID FROM HRMS_ARREARS_DEBIT_"+bean.getYear()
													+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_CODE  "
													+" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_DEBIT_CODE  "
													+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE) "
													+" WHERE HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrledgerCode+") "
													+" AND ARREARS_EMP_ID = "+String.valueOf(empData[i][0]) 
													+" AND TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH')LIKE '"+String.valueOf(empData[i][3])+"'" 
													+" AND ARREARS_YEAR ="+String.valueOf(empData[i][4]) 
													+" AND DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') LIKE '"+String.valueOf(empData[i][5])+"'" 
													+" GROUP BY PARENT_TABLE.PARENT_DEBIT,TRIM(PARENT_TABLE.DEBIT_ABBR),ARREARS_EMP_ID  ORDER BY ARREARS_EMP_ID,PARENT_TABLE.PARENT_DEBIT ";*/
													
								String key=String.valueOf(empData[i][0])+"#"+String.valueOf(empData[i][17])+"#"+String.valueOf(empData[i][4])+"#"+String.valueOf(empData[i][20])+"#"+String.valueOf(empData[i][21])+"#"+String.valueOf(empData[i][5]);
								Object[][] empCredit=null;
								
								try {
									empCredit = (Object[][]) empCreditMap.get(key);
								} catch (Exception e) {
									// TODO: handle exception
								}
									Object[][] empDebit=null;
								
								try {
									empDebit = (Object[][]) empDebitMap.get(key);
								} catch (Exception e) {
									// TODO: handle exception
								}
								//Object[][] empDebit = getSqlModel().getSingleResult(debitQuery);
								
								if(i==0){
									
									creditTotalObj= new Object[empData.length][empCredit.length+3];
									debitTotalObj=new Object[empData.length][empDebit.length+3];
																		
									creditTotalObj=intZero(creditTotalObj);
															
									creditTotalObj[i][0]=String.valueOf(empData[i][0]);
									creditTotalObj[i][1]=String.valueOf(empData[i][2]);
									creditTotalObj[i][2]="";
											
									creditHead=new String[empCredit.length][2];
									debitHead=new String[empDebit.length][2];
									
									finalCreditObj= new Object[1][empCredit.length+3];
									finalDebitObj=new Object[1][empDebit.length+3];
									
									finalCreditObj=intZero(finalCreditObj);
									finalDebitObj=intZero(finalDebitObj);
									
									finalCreditObj[0][0]="";
									finalCreditObj[0][1]="Total ";
									
									for (int j = 0; j < empCredit.length; j++) {
										creditTotalObj[i][count] = checkNull(String.valueOf(empCredit[j][2]));
										
										finalCreditObj[0][count]= Double.parseDouble(checkNull(String.valueOf(finalCreditObj[0][count])))
																	+Double.parseDouble(checkNull(String.valueOf(empCredit[j][2])));
										
										count++;
										creditHead[j][0]= String.valueOf(empCredit[j][0]);
										creditHead[j][1]= String.valueOf(empCredit[j][1]);
									}
									
									count=3;
									debitTotalObj=intZero(debitTotalObj);
									finalDebitObj=intZero(finalDebitObj);
									
									debitTotalObj[i][0]=String.valueOf(empData[i][0]);
									debitTotalObj[i][1]=String.valueOf(empData[i][2]);
									debitTotalObj[i][2]="";
									
									for (int j = 0; j < empDebit.length; j++) {
										debitTotalObj[i][count] = checkNull(String.valueOf(empDebit[j][2]));
										
										finalDebitObj[0][count]= Double.parseDouble(String.valueOf(finalDebitObj[0][count]))
																	+Double.parseDouble(checkNull(String.valueOf(empDebit[j][2])));
			
										count++;
										
										debitHead[j][0]= String.valueOf(empDebit[j][0]);
										debitHead[j][1]= String.valueOf(empDebit[j][1]);
									}
									
								}else{
																		
									creditTotalObj[i][0]=String.valueOf(empData[i][0]);
									creditTotalObj[i][1]=String.valueOf(empData[i][2]);
									creditTotalObj[i][2]="";
										
									for(int k=0;k<creditHead.length;k++){
												
										for (int j = 0; j < empCredit.length; j++) {
													
											if(String.valueOf(creditHead[k][0]).equals(String.valueOf(empCredit[j][0]))){
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
									
									debitTotalObj[i][0]=String.valueOf(empData[i][0]);
									debitTotalObj[i][1]=String.valueOf(empData[i][2]);
									debitTotalObj[i][2]="";
									
									for(int k=0;k<debitHead.length;k++){
											
										for (int j = 0; j < empDebit.length; j++) {
											
											if(String.valueOf(debitHead[k][0]).equals(String.valueOf(empDebit[j][0]))){
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
								
							}catch(Exception e){
								logger.error("error in creating the arrear object"+e);
								//e.printStackTrace();
							}
						}
						logger.info("creditTotalObj length=="+creditTotalObj.length);
						vect.add(0, creditTotalObj);
						vect.add(1, debitTotalObj);
						vect.add(2, finalCreditObj);
						vect.add(3, finalDebitObj);
						vect.add(4,empData);
				}
					
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return vect;		
	}
	

	public ReportGenerator printreport(SalaryPayBill bean ,Object [][] finalCredit,Object [][] finalDebit,ReportGenerator rg ,HttpServletResponse response,boolean printHeader,Object [][]empData,String type){
		try{
			
			String colPerPage=bean.getColumnNumber();
			String recPerPage=bean.getRowNumber();
			Object[][] creditHead =null;
			Object[][] debitHead =null;
			if(type.equals("S")){
				creditHead = getCreditHeader(bean.getLedgerCode(),bean.getYear());
				debitHead = getDebitHeader(bean.getLedgerCode(),bean.getYear());
			}else{
				creditHead = getArrearCreditHeader(bean.getArrLedgerCode(),bean.getYear());
				debitHead = getArrearsDebitHeader(bean.getArrLedgerCode(),bean.getYear());
			}
			
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
			String header[]= new String[Integer.parseInt(colPerPage)+4];	
			HashMap attLeavesMap=null;
			HashMap empChildDebitMap=null;
			HashMap attNarationMap=null;
			HashMap empCreditMap=null;
			HashMap punishMap=null;
			if(printHeader){
			if(type.equals("S")){
				attLeavesMap=	getAttendanceDetails(bean);
				empChildDebitMap=getChildDebitDetails(bean);
				attNarationMap=getAttendanceNaration(bean);
				punishMap=getPunishmentHistory(bean.getMonth(),bean.getYear());
			}
			
			empCreditMap=getEmpCreditDetails(bean,type); 
			}
			int alignment[]=null;
			
			int width[]=null;
			
			repHeader = new Object[1][Integer.parseInt(colPerPage)+4];
			header = new String[Integer.parseInt(colPerPage)+4];
			repHeader=intSpace(repHeader);
			header=intSpace(header);
			
			if(type.equals("A")){						
				repHeader[0][0] = "Sr No\n\nEmp Id\n\nArrear Days ";
				repHeader[0][1] = "Employee Name\nDept   \nBank Name   A/c No.\nArrear Type  Arrear Month   Arrear Year";
				
				header[0] = "Sr No\nEmp Id";      // \n\nArrear Days ";
				header[1] = "Employee Name\nDept   \nBank Name   A/c No.\nArrear Type  Arrear Month   Arrear Year\nArrears For";
			}
			
			if(type.equals("S")){						
				repHeader[0][0] = "Sr No\n\nEmp Id\n\nSal Days ";
				repHeader[0][1] = "Employee Name\nDept    \nBank Name   A/c No.\nReporting\n\nPay Scale";
				
				header[0] = "Sr No\nEmp Id\nSal Days ";
				header[1] = "Employee Name\nDept \nBank Name   A/c No.\nReporting\n\nPay Scale";
			}
			
			
			alignment=new int[header.length];
			width=new int[header.length];
			
			width[0] =5;
			width[1] =30;
			alignment[0] =0;
			alignment[1] =0;
			
			double row1=0.0;
			int payCount=0,creCount=0,tempCount=0,dedCount=0,dedObjCnt=0,tempDedCount=0;
			
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
				width[i] =5;
				alignment[i] =2;
				}
				else if(i<repHeader[0].length-1){
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
					
					}
				else{
						for (int j = 0; j < payCount; j++) {
								repHeader[0][i] = String.valueOf(repHeader[0][i])+ "Total Credit\n";
								header[i] = String.valueOf(header[i])+ "Total Credit\n";
						}
						for (int j = 0; j < dedCount; j++) {
								repHeader[0][i] =String.valueOf(repHeader[0][i])+ "Total Debit\n";
								header[i] =String.valueOf(header[i])+ "Total Debit\n";
						}
						repHeader[0][i] =String.valueOf(repHeader[0][i])+ "Net Pay\n";
						header[i] =String.valueOf(header[i])+ "Net Pay\n";
					}
					width[i] =10;
					alignment[i] =2;
				}
			
				/*for (int i = 0; i < repHeader.length; i++) {
					for (int j = 0; j < repHeader[0].length; j++) {
						logger.info("repHeader data"+repHeader[i][j]);
					}
				}*/
			//-----------------------printing employee----------------------------------
			
			//logger.info("colPerPage "+colPerPage );
			int empCount = 0;
			Object repData[][] = new Object[Integer.parseInt(recPerPage)][Integer.parseInt(colPerPage)+4+6];
			Object[][] otherData=new Object[repData.length][2];
			Object totalRepData[][] = new Object[1][Integer.parseInt(colPerPage)+4];
			Object pageTotCredit[][] = new Object[1][creditHead.length+3];
			Object pageTotDebit[][] = new Object[1][debitHead.length+3];
			
			Object pageTotalFinal[][] = new Object[1][Integer.parseInt(colPerPage)+4];
			pageTotCredit = intZero(pageTotCredit);
			pageTotDebit = intZero(pageTotDebit);
			
			if(empData.length < Integer.parseInt(recPerPage))
				repData = new Object[empData.length][Integer.parseInt(colPerPage)+4+6];
			
			repData = intSpace(repData);
			totalRepData = intSpace(totalRepData);
			pageTotalFinal= intSpace(pageTotalFinal);
			
			HashMap pageCTotMap= new HashMap();
			HashMap pageDTotMap= new HashMap();
			
			HashMap finalCTotMap= new HashMap();
			HashMap finalDTotMap= new HashMap();
			double finalDebTotal = 0;
			double finalCreTotal = 0;
			
			
			boolean brnCheck= false;
			boolean deptCheck=false;
			boolean desgCheck=false;
			
			if(bean.getChkBrnOrder().equals("Y"))
				brnCheck=true;
			if(bean.getChkDeptOrder().equals("Y"))
				deptCheck=true;
			if(bean.getChkDesgOrder().equals("Y"))
				desgCheck=true;
			
			for(int e=0;e<empData.length;e++){
				
				payCount=0;creCount=0;tempCount=0;dedCount=0;dedObjCnt=0;tempDedCount=0;
				double creditTot = 0.0,debitTot=0.0;
				double netCreditTot = 0.0,netDebitTot=0.0;
				
				if(empCount<Integer.parseInt(recPerPage)){
					
					if(printHeader){
						String punishment="";
						Object [][]empCreditObj=null;
						String empCreditDetails="";
						if(type.equals("S")){
							try {
								empCreditObj = (Object[][]) empCreditMap.get(String
										.valueOf(empData[e][0]));
								
							} catch (Exception ex) {
								// TODO: handle exception
							}
							try {
								punishment = String.valueOf(punishMap.get(String
										.valueOf(empData[e][0])));
							} catch (Exception ex) {
								// TODO: handle exception
							}

						}else{
							String key=String.valueOf(empData[e][0])+"#"+String.valueOf(empData[e][17])+"#"+String.valueOf(empData[e][4]);
							try {
								
								empCreditObj = (Object[][]) empCreditMap.get(key);
								
							} catch (Exception ex) {
								// TODO: handle exception
							}
						}
						empCreditDetails=getEmpCreditDisplayString(empCreditObj);
						
						if(type.equals("A")){
							repData[empCount][0] = String.valueOf(e+1)+"\n"+String.valueOf(empData[e][1]);     //+"\n"+Utility.checkNull(String.valueOf(empData[e][7]));
							repData[empCount][1] = String.valueOf(empData[e][2])+"\n"+String.valueOf(empData[e][14])+"  "
																			//	+"\n"+Utility.checkNull(String.valueOf(empData[e][6]))+"  "+Utility.checkNull(String.valueOf(empData[e][10]))
																				+"\n"+Utility.checkNull(String.valueOf(empData[e][8]))+"  "+Utility.checkNull(String.valueOf(empData[e][9]))
																				+"\n"+empCreditDetails
																				+"\n\n"+Utility.checkNull(String.valueOf(empData[e][5]))+"  "+Utility.checkNull(String.valueOf(empData[e][3]))+"  "+Utility.checkNull(String.valueOf(empData[e][4]))
																				+"\n"+Utility.checkNull(String.valueOf(empData[e][18]));
							if(!Utility.checkNull(String.valueOf(empData[e][19])).equals(""))
								otherData[empCount][1]="Narration :"+Utility.checkNull(String.valueOf(empData[e][19]));
							else{
								otherData[empCount][1]="";
							}
						}
						if(type.equals("S")){
							repData[empCount][0] = String.valueOf(e+1)+"\n"+String.valueOf(empData[e][1])+"\n"+String.valueOf(empData[e][5]);
							repData[empCount][1] = String.valueOf(empData[e][2]) +"\n" + String.valueOf(empData[e][4])       //"   "+ Utility.checkNull(String.valueOf(empData[e][5]))
													//+ "\n"+Utility.checkNull(String.valueOf(empData[e][9]))+"  " + Utility.checkNull(String.valueOf(empData[e][8])) +"\n" 
													+"\n" + Utility.checkNull(String.valueOf(empData[e][6]))
													+ "  " + Utility.checkNull(String.valueOf(empData[e][7])) +"\n"+Utility.checkNull(String.valueOf(empData[e][14])) ;
							if(empCreditDetails !=null && !Utility.checkNull(empCreditDetails).equals("")){
								repData[empCount][1]=String.valueOf(repData[empCount][1])+"\n\n"+empCreditDetails;
							}
							if(punishment !=null && !Utility.checkNull(punishment).equals("")){
								logger.info("punishment found for emp"+String.valueOf(empData[e][0]));
								repData[empCount][1]=String.valueOf(repData[empCount][1])+"\n\n"+"\t\t\tSuspended :-"+Utility.checkNull(punishment);
							}
						}
						
					}else{
						if(type.equals("S")){
							repData[empCount][0] ="";
							repData[empCount][1] = String.valueOf(empData[e][1]);
						}
					}
					
					otherData[empCount][0]="";			//Utility.checkNull(String.valueOf(empData[e][14]));
				//	otherData[empCount][1]="";
					HashMap creTotMap= new HashMap();
					HashMap debTotMap= new HashMap();
					if(type.equals("S")){
					Object [][]attLeaveObj=null;
					Object [][]childDebitObj=null;
					String attNaration=null;
					try {
						attLeaveObj = (Object[][]) attLeavesMap.get(String
								.valueOf(empData[e][0]));
					} catch (Exception ex) {
						// TODO: handle exception
					}
					String displayStringFinal="";
					try {
						for (int i = 0; i < debitHead.length; i++) {
							try {
								childDebitObj = (Object[][]) empChildDebitMap.get(String
										.valueOf(empData[e][0])+"#"+String.valueOf(debitHead[i][0]));
							String displayString=getBankDisplayString(childDebitObj);
							if(!displayString.equals("")){
								displayStringFinal+=displayString+"\n";
							}
							}catch (Exception ex) {
								// TODO: handle exception
							}
						}
						/*bankLoanObj = (Object[][]) empBankLoanMap.get(String
								.valueOf(empData[e][0]));*/
					} catch (Exception ex) {
						// TODO: handle exception
					}
					try {
						attNaration = String.valueOf(attNarationMap.get(String
								.valueOf(empData[e][0])));
					} catch (Exception ex) {
						// TODO: handle exception
					}
					
					//otherData[empCount][0]=displayString;
					if(type.equals("S") && printHeader)
					otherData[empCount][1]=displayStringFinal+getAttendanceDisplayString(attLeaveObj,attNaration.trim());
				}
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
									
									/*if(e == 0)
										totalRepData[0][i] = String.valueOf(totalRepData[0][i]) +"PAY =>" +"\n";
									*/
								}
								
								row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
								java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
										row1);
								dedCount = Integer.parseInt(bigDecRow2.setScale(0,
										java.math.BigDecimal.ROUND_UP).toString());
									for (int j = 0; j < dedCount; j++) {
										repData[empCount][i] = String.valueOf(repData[empCount][i]) +"DED =>" +"\n";
										
										/*if(e == 0)
											totalRepData[0][i] = String.valueOf(totalRepData[0][i]) +"DED =>" +"\n";
									*/
									}
									repData[empCount][i] = repData[empCount][i] +"NET =>";
									
									/*if(e == 0)
										totalRepData[0][i] = totalRepData[0][i] +"NET =>";*/
						}
						else if(i<repHeader[0].length-1){
							tempCount= creCount;
					
								for (int j = 0; j < payCount; j++) {
									if(creCount< creditHead.length){
										//to set the column total
										String colTotal = String.valueOf(creTotMap.get("a"+String.valueOf(j)));
										if(colTotal==null)
											colTotal ="0.0";
										else if(colTotal.equals("null"))
											colTotal ="0.0";
										pageTotCredit[0][tempJ] =  Double.parseDouble(String.valueOf(pageTotCredit[0][tempJ])) +  Double.parseDouble( String.valueOf(finalCredit[e][tempJ]));
										colTotal = String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble( String.valueOf(finalCredit[e][tempJ])));
										creTotMap.put("a"+String.valueOf(j), Utility.twoDecimal(Double.parseDouble(colTotal),2));
										//to set the credit as per the head
										repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble(String.valueOf(finalCredit[e][tempJ])))+"\n" ;
										// to set the grand total of entire report which is coming through query and will be set only once at first counter
										/*if(e==0){
										
											String finalCredit = (String)finalCTotMap.get("a"+String.valueOf(j));
											
											if(finalCredit==null)
												finalCredit ="0.0";
											
											finalCredit = String.valueOf(Double.parseDouble(finalCredit) + Double.parseDouble(String.valueOf(finalCreditObj[0][tempJ])));
											finalCTotMap.put("a"+String.valueOf(j),finalCredit);
																											
											totalRepData[0][i] = String.valueOf(totalRepData[0][i])+ Utility.twoDecimal(Double.parseDouble(String.valueOf(finalCreditObj[0][tempJ])),2)+"\n" ;
										}*/
										creCount += Integer.parseInt(colPerPage);
										tempJ += Integer.parseInt(colPerPage);
									}
									else{
										repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ;
										/*if(e == 0){
											totalRepData[0][i] = String.valueOf(totalRepData[0][i])+"\n" ;
											
										}*/
									}
								}
							creCount = tempCount;
							creCount++;
							
							
							tempDedCount= dedObjCnt;
							for (int j = 0; j < dedCount; j++) {
								if(dedObjCnt< debitHead.length){
									//to set the column total
									String colTotal = String.valueOf(debTotMap.get("a"+String.valueOf(j)));
									if(colTotal==null)
										colTotal ="0.0";
									else if(colTotal.equals("null"))
										colTotal ="0.0";
									pageTotDebit[0][tempI] =  Double.parseDouble(String.valueOf(pageTotDebit[0][tempI])) +  Double.parseDouble( String.valueOf(finalDebit[e][tempI]));
									colTotal = String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble( String.valueOf(finalDebit[e][tempI])));
									debTotMap.put("a"+String.valueOf(j), Utility.twoDecimal(Double.parseDouble(colTotal),2));
									repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble(String.valueOf(finalDebit[e][tempI])))+"\n" ;
																						
									/*if(e==0){
									
										String finalDebit = (String)finalDTotMap.get("a"+String.valueOf(j));
										
										if(finalDebit==null)
											finalDebit ="0.0";
										
										finalDebit = String.valueOf(Double.parseDouble(finalDebit) + Double.parseDouble(String.valueOf(finalDebitObj[0][tempI])));
										finalDTotMap.put("a"+String.valueOf(j),finalDebit);

										totalRepData[0][i] = String.valueOf(totalRepData[0][i])+ Utility.twoDecimal(Double.parseDouble(String.valueOf(finalDebitObj[0][tempI])),2)+"\n" ;
									}*/
									dedObjCnt += Integer.parseInt(colPerPage);
									tempI += Integer.parseInt(colPerPage);
								}
								else{
									repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ;
									/*if(e == 0){
										totalRepData[0][i] = String.valueOf(totalRepData[0][i])+"\n" ;
										
									}*/
								}
							}
							dedObjCnt = tempDedCount;
							dedObjCnt++;
							
							}
							else{
								for (int j = 0; j < payCount; j++) {
									//total credit of 5 employee all rows
									creditTot += Double.parseDouble(String.valueOf(creTotMap.get("a"+String.valueOf(j))));
									//total of credit as per the row of credit 
									repData[empCount][i] = (String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble((String.valueOf(creTotMap.get("a"+String.valueOf(j)))))))+"\n";
																																
									/*if(e == empData.length-1){
										finalCreTotal += Double.parseDouble(String.valueOf(finalCTotMap.get("a"+String.valueOf(j))));
										totalRepData[0][i] = String.valueOf(totalRepData[0][i]) + formatter.format(Double.parseDouble((String.valueOf(finalCTotMap.get("a"+String.valueOf(j))))))+"\n";
									}*/
								}
								for (int j = 0; j < dedCount; j++) {
									debitTot += Double.parseDouble(String.valueOf(debTotMap.get("a"+String.valueOf(j))));
									repData[empCount][i] = String.valueOf(repData[empCount][i])+ formatter.format(Double.parseDouble((String.valueOf(debTotMap.get("a"+String.valueOf(j))))))+"\n";
									
									/*if(e == empData.length-1){
										finalDebTotal  += Double.parseDouble(String.valueOf(finalDTotMap.get("a"+String.valueOf(j))));
										totalRepData[0][i] = String.valueOf(totalRepData[0][i]) + formatter.format(Double.parseDouble((String.valueOf(finalDTotMap.get("a"+String.valueOf(j))))))+"\n";
									}*/

								}
								repData[empCount][i] = repData[empCount][i]+ formatter.format(creditTot - debitTot);
								/*if(e == empData.length-1)
									totalRepData[0][i] = String.valueOf(totalRepData[0][i]) + formatter.format(finalCreTotal - finalDebTotal);*/
							}
							
						}
					if(printHeader){
						if(type.equals("S")){
							repData[empCount][repData[0].length -6]=String.valueOf(empData[e][10]);
							repData[empCount][repData[0].length -5]=String.valueOf(empData[e][11]);
							repData[empCount][repData[0].length -4]=String.valueOf(empData[e][3]);
							repData[empCount][repData[0].length -3]=String.valueOf(empData[e][4]);
							repData[empCount][repData[0].length -2]=String.valueOf(empData[e][12]);
							repData[empCount][repData[0].length -1]=String.valueOf(empData[e][13]);
						}
						if(type.equals("A")){
							repData[empCount][repData[0].length -6]=String.valueOf(empData[e][empData[e].length -6]);
							repData[empCount][repData[0].length -5]=String.valueOf(empData[e][empData[e].length -5]);
							repData[empCount][repData[0].length -4]=String.valueOf(empData[e][empData[e].length -4]);
							repData[empCount][repData[0].length -3]=String.valueOf(empData[e][empData[e].length -3]);
							repData[empCount][repData[0].length -2]=String.valueOf(empData[e][empData[e].length -2]);
							repData[empCount][repData[0].length -1]=String.valueOf(empData[e][empData[e].length -1]);
							
						}
					}
					/*braList.add(repData);
						empVect.add(repData);*/
						creditTot=0.0;debitTot=0.0;
						empCount++;
					}
					
				if(empCount==Integer.parseInt(recPerPage) || e == empData.length-1){	
					
					//rg.tableBody(repHeader, width, alignment,17);
					if(brnCheck || deptCheck || desgCheck){
						if(printHeader)
							setReport(repData, rg, brnCheck, deptCheck, desgCheck, header, width, alignment, 0,otherData);
						else
							rg.tableBody(repData, width, alignment,17);
					}else{
						
						if(printHeader)
							rg.tableBodyForPaybill(header, repData, width,alignment,17,otherData);	
						else
							rg.tableBody(repData, width, alignment,17);
					}
						
						pageTotalFinal[0][1] = "Page Total";
						creCount=0;tempCount=0;dedObjCnt=0;tempDedCount=0;
						netCreditTot = 0.0;netDebitTot=0.0;
						
					for (int i = 2; i < pageTotalFinal[0].length; i++) {
						if(i==2){									
							row1 = (double) creditHead.length / Integer.parseInt(colPerPage);
							java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
									row1);
							payCount = Integer.parseInt(bigDecRow1.setScale(0,
									java.math.BigDecimal.ROUND_UP).toString());
								for (int j = 0; j < payCount; j++) {
									pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) +"PAY =>" +"\n";
								}
								row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
								java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
										row1);
								dedCount = Integer.parseInt(bigDecRow2.setScale(0,
										java.math.BigDecimal.ROUND_UP).toString());
								for (int j = 0; j < dedCount; j++) {
									pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) +"DED =>" +"\n";
									}
								pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) +"NET =>" +"\n";
						}
						else if(i<pageTotalFinal[0].length-1){
							tempCount= creCount;
							
							int temp = i;
								for (int j = 0; j < payCount; j++) {
									if(creCount < creditHead.length){
										
									String pageCreTotal = String.valueOf(pageCTotMap.get("a"+String.valueOf(j)));
										if(pageCreTotal==null)
											pageCreTotal ="0.0";
										else if(pageCreTotal.equals("null"))
											pageCreTotal ="0.0";
										
										pageCreTotal = String.valueOf((Double.parseDouble(pageCreTotal) + Double.parseDouble(String.valueOf(pageTotCredit[0][temp]))));
										pageCTotMap.put("a"+String.valueOf(j), Utility.twoDecimal(Double.parseDouble(pageCreTotal),2));
																				
									pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+ formatter.format(Double.parseDouble(String.valueOf(pageTotCredit[0][temp])))+"\n" ;
									
									temp += Integer.parseInt(colPerPage);
									creCount += Integer.parseInt(colPerPage);
									}
									else
										pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+"\n" ;
								}
							creCount = tempCount;
							creCount++;
							//logger.info("pageTotalFinal[0][i]CC----------- "+pageTotalFinal[0][i]);
							
							int temp1 = i;
							tempDedCount= dedObjCnt;
							
							for (int j = 0; j < dedCount; j++) {
								if(dedObjCnt < debitHead.length){
									
									String pageDebTotal = String.valueOf(pageDTotMap.get("a"+String.valueOf(j)));
									if(pageDebTotal==null)
										pageDebTotal ="0.0";
									else if(pageDebTotal.equals("null"))
										pageDebTotal ="0.0";
									
									pageDebTotal = String.valueOf((Double.parseDouble(pageDebTotal) + Double.parseDouble( String.valueOf(pageTotDebit[0][temp1]))));
									pageDTotMap.put("a"+String.valueOf(j), Utility.twoDecimal(Double.parseDouble(pageDebTotal),2));
																					
									pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+ formatter.format(Double.parseDouble(String.valueOf(pageTotDebit[0][temp1])))+"\n" ;
									temp1 += Integer.parseInt(colPerPage);
									dedObjCnt += Integer.parseInt(colPerPage);
								}
								else
									pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+"\n" ;
							}
							dedObjCnt = tempDedCount;
							dedObjCnt++;
							//logger.info("pageTotalFinal[0][i] DD----------- "+pageTotalFinal[0][i]);
							}
						else{
							for (int j = 0; j < payCount; j++) {
								netCreditTot += Double.parseDouble(String.valueOf(pageCTotMap.get("a"+String.valueOf(j)))); 
								pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) + formatter.format(Double.parseDouble(String.valueOf(pageCTotMap.get("a"+String.valueOf(j))))) +"\n";
								
							}
							for (int j = 0; j < dedCount; j++) {
								
								netDebitTot += Double.parseDouble(String.valueOf(pageDTotMap.get("a"+String.valueOf(j))));
								pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) + formatter.format(Double.parseDouble(String.valueOf(pageDTotMap.get("a"+String.valueOf(j))))) +"\n";
								
							}
							pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) + formatter.format(netCreditTot - netDebitTot);									
						}
					}
					if(printHeader){
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(pageTotalFinal, width, alignment,17);
						
					}
					
					if(empCount == Integer.parseInt(recPerPage))
						rg.pageBreak();
				/*	braList = new ArrayList();
					empVect = new Vector();*/
					
					int pendingCount = empData.length - (e+1);
					if(pendingCount < Integer.parseInt(recPerPage))
						repData = new Object[pendingCount][Integer.parseInt(colPerPage)+4+6];
					
					//repData = new Object[Integer.parseInt(recPerPage)][Integer.parseInt(colPerPage)+4];
					repData = intSpace(repData);
					pageTotalFinal = new Object[1][Integer.parseInt(colPerPage)+4];
					pageTotalFinal= intSpace(pageTotalFinal);
					
					pageCTotMap=new HashMap();
					pageDTotMap=new HashMap();
					pageTotDebit = intZero(pageTotDebit);
					pageTotCredit = intZero(pageTotCredit);
					empCount=0;
					payCount=0;creCount=0;tempCount=0;dedCount=0;dedObjCnt=0;tempDedCount=0;
					//e--;
				}
				
				
			}
			
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return rg;
	}

	private HashMap getAttendanceNaration(SalaryPayBill bean) {
		String query="SELECT NVL(HRMS_UPLOAD_ATTENDANCE_"+bean.getYear()+".EMP_NARRATION,''),ATTN_EMP_ID FROM HRMS_UPLOAD_ATTENDANCE_"+bean.getYear()
					+" WHERE ATTN_MONTH="+bean.getMonth()+" AND ATTN_YEAR="+bean.getYear()+" AND HRMS_UPLOAD_ATTENDANCE_"+bean.getYear()+".EMP_NARRATION IS NOT NULL";
		Object [][]attObj=getSqlModel().getSingleResult(query);
		HashMap<String, String> attMap=new HashMap<String, String>();
		try {
			if (attObj != null && attObj.length > 0) {
				for (int j = 0; j < attObj.length; j++) {
					attMap.put(String.valueOf(attObj[j][1]), String
							.valueOf(attObj[j][0]));
				}

			}
		} catch (Exception e) {
			return null;
		}
		return attMap;
	}
	private HashMap getArrearsNaration(SalaryPayBill bean) {
		String query="SELECT NVL(HRMS_ARREARS_"+bean.getYear()+".ARREARS_NARRATION,''),HRMS_ARREARS_"+bean.getYear()+".EMP_ID FROM HRMS_ARREARS_"+bean.getYear()
					+" WHERE HRMS_ARREARS_"+bean.getYear()+".ARREARS_NARRATION IS NOT NULL";
		Object [][]attObj=getSqlModel().getSingleResult(query);
		HashMap<String, String> attMap=new HashMap<String, String>();
		try {
			if (attObj != null && attObj.length > 0) {
				for (int j = 0; j < attObj.length; j++) {
					attMap.put(String.valueOf(attObj[j][1]), String
							.valueOf(attObj[j][0]));
				}

			}
		} catch (Exception e) {
			return null;
		}
		return attMap;
	}
	private String getAttendanceDisplayString(Object[][] attendanceObj,String narration) {
		String returnString="Attn Details:";
		if(attendanceObj!=null && attendanceObj.length>0){
			returnString+="ABS  "+String.valueOf(attendanceObj[0][4])+"\t\t\tREC  "+String.valueOf(attendanceObj[0][5]);
			for (int i = 0; i < attendanceObj.length; i++) {
				//if(Double.parseDouble(String.valueOf(attendanceObj[i][2]))>0)
				//logger.info("String.valueOf(attendanceObj[i][1])="+String.valueOf(attendanceObj[i][1]));
				if(!(Utility.checkNull(String.valueOf(attendanceObj[i][1])).equals("")|| Utility.checkNull(String.valueOf(attendanceObj[i][1])).equals("null"))){
					returnString+="\t\t\t"+String.valueOf(attendanceObj[i][1])+"  "+String.valueOf(attendanceObj[i][2]);
					//if(Double.parseDouble(String.valueOf(attendanceObj[i][3]))>0)
						returnString+="\t\t\t"+String.valueOf(attendanceObj[i][1])+" R   "+String.valueOf(attendanceObj[i][3]);
				}
		}
		}
		
		if(narration!=null && !Utility.checkNull(narration).equals("")&& !Utility.checkNull(narration).equals("null")){
			if(returnString.equals("")){
				if(!narration.equals(""))
				returnString+="Narration\t\t\t"+narration;
			}else{
				returnString+="\nNarration\t\t\t"+narration;
			}
			
		}
		return returnString;
	}
	private String getBankDisplayString(Object[][] bankLoanObj) {
		String returnString="";
		if(bankLoanObj!=null && bankLoanObj.length>0){
			for (int i = 0; i < bankLoanObj.length; i++) {
				if(String.valueOf(bankLoanObj[i][3]).equals("")||String.valueOf(bankLoanObj[i][3]).equals("null"))
				{
					returnString+="\t\t"+String.valueOf(bankLoanObj[i][1])+":  "+String.valueOf(bankLoanObj[i][0]);
				}
				else{
					returnString+="\t\t"+String.valueOf(bankLoanObj[i][3])+":  "+String.valueOf(bankLoanObj[i][0]);
				}
		}
		}
		/*if(!returnString.equals("")){
			returnString="Bank Loan Details:"+returnString;
		}*/
		return returnString;
	}
	private String getEmpCreditDisplayString(Object[][] empCreditObj) {
		String returnString="";
		if(empCreditObj!=null && empCreditObj.length>0){
			for (int i = 0; i < empCreditObj.length; i++) {
			returnString+="\t\t\t"+String.valueOf(empCreditObj[i][1])+":  "+String.valueOf(empCreditObj[i][0]);
		}
		}
		
		return returnString;
	}
	public boolean checkCondition(boolean brnFlag, boolean deptFlag,boolean desgFlag
		,String brn1, String brn2,String dept1, String dept2,String desg1, String desg2 ) {

	boolean flag = ((brnFlag && deptFlag && desgFlag && brn1.equals(brn2) && dept1.equals(dept2) && desg1.equals(desg2))
					|| (brnFlag && deptFlag && !desgFlag && brn1.equals(brn2) && dept1.equals(dept2))
					|| (brnFlag && desgFlag && !deptFlag && brn1.equals(brn2) && desg1.equals(desg2))
					|| (deptFlag && desgFlag && !brnFlag && dept1.equals(dept2) && desg1.equals(desg2))
					|| (brnFlag && !deptFlag && !desgFlag && brn1.equals(brn2))
					|| (deptFlag && !brnFlag && !desgFlag && dept1.equals(dept2))
					|| (desgFlag && !brnFlag && !deptFlag && desg1.equals(desg2))
	);

	//logger.info("flag-----------" + flag);
	return flag;
}

	public ReportGenerator setReport(Object[][] salData, ReportGenerator rg,
		boolean brnCheck, boolean deptCheck, boolean desgCheck ,String[] colNames,
		int[] cellwidth, int[] alignmnet, int startSum,Object[][]otherData ) {
	try {
		Vector braVector = new Vector();
		Object[][] braTotObj = new Object[1][colNames.length];
		braTotObj = intSpace(braTotObj);
		int count = 0;
		int check=0;
		String brnCode = String.valueOf(salData[0][salData[0].length - 6]);
		String deptCode = String.valueOf(salData[0][salData[0].length - 4]);
		String desgCode = String.valueOf(salData[0][salData[0].length - 2]);
		braTotObj[0][1] = "Total";
		String printString="";
		for (int i = 0; i < salData.length; i++) {

			if (i < salData.length 
					&& checkCondition(brnCheck, deptCheck, desgCheck,
							brnCode, String.valueOf(salData[i][salData[0].length - 6]),
							deptCode, String.valueOf(salData[i][salData[0].length - 4]), 
							desgCode, String.valueOf(salData[i][salData[0].length - 2]))){

				braVector.add(salData[i]);
				count++;
				/*for (int j = startSum; j < colNames.length; j++) {

					if (String.valueOf(braTotObj[0][j]).equals(""))
						braTotObj[0][j] = "0";

					braTotObj[0][j] = Math.round((Double.parseDouble(String
							.valueOf(braTotObj[0][j])) + Double
							.parseDouble(String.valueOf(salData[i][j]))));
				}*/

			} else if (count < salData.length) {
				
				Object[][] reportObj = new Object[braVector.size()][colNames.length];
				for (int j = 0; j < braVector.size(); j++) {
					reportObj[j] = (Object[]) braVector.get(j);
				}
				if (reportObj != null && reportObj.length > 0) {
					
					rg.addText("\n", 0, 0, 0);
					if (brnCheck)
						printString += "Branch : "+ String.valueOf(salData[i - 1][salData[0].length - 5])+"   ";
					if (deptCheck)
						printString += "Department : "+ String.valueOf(salData[i - 1][salData[0].length - 3])+"   ";
					if (desgCheck)
						printString += "Designation : "+ String.valueOf(salData[i - 1][salData[0].length - 1])+"   ";

					rg.addTextBold(printString, 0, 0, 0,17);
					//rg.addText("\n", 0, 0, 0);
					if(check==0)
						rg.tableBodyForPaybill(colNames, reportObj, cellwidth,alignmnet,12,otherData);	
					else
						rg.tableBody(reportObj, cellwidth, alignmnet,17);
					
					check++;
					//rg.tableBody(colNames, cellwidth, alignmnet,17);
					//rg.tableBody(braTotObj, cellwidth, alignmnet);
					printString="";
					
				}

				braVector = new Vector();
				/*braTotObj = new Object[1][colNames.length];
				braTotObj = intSpace(braTotObj);
				braTotObj[0][1] = "Total";*/
				brnCode = String.valueOf(salData[i][salData[0].length - 6]);
				deptCode = String.valueOf(salData[i][salData[0].length - 4]);
				desgCode = String.valueOf(salData[i][salData[0].length - 2]);
				i--;

			}

		}
		Object[][] reportObj = new Object[braVector.size()][colNames.length];
		for (int j = 0; j < braVector.size(); j++) {
			reportObj[j] = (Object[]) braVector.get(j);
		}
		if (reportObj != null && reportObj.length > 0) {
			rg.addText("\n", 0, 0, 0);
			if (brnCheck)
				printString += "Branch : "+ String.valueOf(reportObj[0][reportObj[0].length - 5])+"   ";
			if (deptCheck)
				printString += "Department : "+ String.valueOf(reportObj[0][reportObj[0].length - 3])+"   ";
			if (desgCheck)
				printString += "Designation : "+ String.valueOf(reportObj[0][reportObj[0].length - 1])+"   ";

			rg.addTextBold(printString, 0, 0, 0,17);
			//rg.addText("\n", 0, 0, 0);
			if(check==0)
				rg.tableBodyForPaybill(colNames, reportObj, cellwidth,alignmnet,12,otherData);	
			else
				rg.tableBody(reportObj, cellwidth, alignmnet,17);
			
			//rg.tableBody(colNames, cellwidth, alignmnet,17);
			//rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,17);	
			//rg.tableBody(braTotObj, cellwidth, alignmnet);
			
		}

	} catch (Exception e) {
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
	public Object[][] consolidateArrearsObject(Object salaObject[][],
			Object arrearsObject[][], int x, int[] colNo, int totalCol,boolean addFlag) {
		Object[][] consolidatedObject = null;
		Object[][] arrearsNewObject = null;
		Object[][] salaNewObject = null;
		try {
			salaObject = Utility.checkNullObjArr(salaObject, "0");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			arrearsObject = Utility.checkNullObjArr(arrearsObject, "0");
		} catch (Exception e) {
			// TODO: handle exception
		}
		HashMap<String, Object[]> empMap = new HashMap<String, Object[]>();
		try {
			if (salaObject != null || salaObject.length > 0) {
				salaNewObject = new Object[salaObject.length][totalCol];
				salaNewObject=intZero(salaNewObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (arrearsObject != null || arrearsObject.length > 0) {
				arrearsNewObject = new Object[arrearsObject.length][totalCol];
				arrearsNewObject =intZero(arrearsNewObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			try {
				if (salaObject != null || salaObject.length > 0) {
					try {
					for (int j = 0; j < salaNewObject.length; j++) {
						try {
							for (int k = 0; k < salaNewObject[0].length; k++) {
								salaNewObject[j][k] = salaObject[j][k];
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					}catch (Exception e) {
						logger.error("Exception in salaNewObject" + e);
					}
					
					for (int i = 0; i < salaNewObject.length; i++) {
						if (empMap.containsKey(String
								.valueOf(salaNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(salaNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {
								if(addFlag)
								temObj[colNo[j]] = Double.parseDouble(String
										.valueOf(temObj[colNo[j]]))
										+ Double
												.parseDouble(String
														.valueOf(salaNewObject[i][colNo[j]]));
								else
									temObj[colNo[j]] = Double.parseDouble(String
											.valueOf(temObj[colNo[j]]))
											- Double
													.parseDouble(String
															.valueOf(salaNewObject[i][colNo[j]]));
							}
							empMap.put(String.valueOf(salaNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(salaNewObject[i][x]),
									salaNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in salaNewObject" + e);
			}
			try {
				if (arrearsObject != null || arrearsObject.length > 0) {
					try {
						for (int j = 0; j < arrearsNewObject.length; j++) {
							try {
								for (int k = 0; k < arrearsNewObject[0].length; k++) {
									arrearsNewObject[j][k] = arrearsObject[j][k];
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					} catch (Exception e) {
						logger.error("Exception in arrearsNewObject" + e);
					}
					for (int i = 0; i < arrearsNewObject.length; i++) {
						if (empMap.containsKey(String
								.valueOf(arrearsNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(arrearsNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {
								if(addFlag)
								temObj[colNo[j]] = Double.parseDouble(String
										.valueOf(temObj[colNo[j]]))
										+ Double
												.parseDouble(String
														.valueOf(arrearsNewObject[i][colNo[j]]));
								else 
									temObj[colNo[j]] = Double.parseDouble(String
											.valueOf(temObj[colNo[j]]))
											- Double
													.parseDouble(String
															.valueOf(arrearsNewObject[i][colNo[j]]));
									
							}
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									arrearsNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in arrearsNewObject" + e);
			}

			if (empMap.size() > 0) {
				consolidatedObject = new Object[empMap.size()][totalCol];
				int i = 0;
				for (Iterator<String> iter = empMap.keySet().iterator(); iter
						.hasNext();) {
					String empId = iter.next();

					consolidatedObject[i++] = empMap.get(empId);

				}
			}
			/*
			 * for (int j = 0; j < consolidatedObject.length; j++) { for (int k =
			 * 0; k < consolidatedObject[0].length; k++) {
			 * logger.info("consolidatedObject[==" + consolidatedObject[j][k]); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidatedObject;
	}
	public HashMap getPunishmentHistory(String month,String year){
		String query="SELECT PUNISH_NAME,EMP_ID "
					+"	FROM HRMS_PUNISH "
					+"	INNER JOIN HRMS_PUNISHMENT ON (HRMS_PUNISH.DISCP_ID = HRMS_PUNISHMENT.PUNISH_ID) " 
					+"	WHERE PUNISH_SUSP_STATUS='A' AND PUNISH_SALARY='Y'"
					+"  AND TO_DATE('02-"+month+"-"+year+"','DD-MM-YYYY') BETWEEN PUNISH_WEF AND PUNISH_TODATE   ORDER BY HRMS_PUNISH.PUNISH_ID  ";
		Object[][]data=getSqlModel().getSingleResult(query);
		
		HashMap punsihMap=new HashMap();
		if(data !=null && data.length>0){
			for (int j = 0; j < data.length; j++) {
				punsihMap.put(String.valueOf(data[j][1]), String.valueOf(data[j][0]));
			}
			
		}
		return punsihMap;
		}
		
	
}


