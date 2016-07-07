/**
 * 
 */
package org.paradyne.model.eGov.reports;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.eGov.reports.PensionPaybill;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.SalPageWiseModel;

/**
 * @author aa0554
 *
 */
public class PensionPaybillModel extends ModelBase { 
	 NumberFormat formatter = new DecimalFormat("#0.00");
		static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalPageWiseModel.class); 
		
		/**
		 * GET CREDIT CODE AND CREDIT ABBR
		 * @param String 'path', where Credit Head XML file has been stored 
		 * @return CREDITS Object 'credit_header'
		 */
		public Object[][] getCreditHeader(String ledgerCode,String year,boolean isArraer) {
			Object credit_header[][] = null;
			Object returnObj [][]=new Object[1][2];
			returnObj[0][0]="0";
			returnObj[0][1]="ARREAR";
			try{			
			
				String query ="SELECT DISTINCT PENS_CREDIT_CODE,TRIM(CREDIT_ABBR) FROM HRMS_PENSION_CREDIT_"+year+" " +
								" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_"+year+".PENS_CREDIT_CODE" +
								" WHERE PENS_LEDGER_CODE IN("+ledgerCode+") ORDER BY PENS_CREDIT_CODE";
			
			credit_header = getSqlModel().getSingleResult(query);
			if(credit_header!=null && credit_header.length>0){
				returnObj =Utility.joinArrays(credit_header,returnObj, 2, 0);
			}
			
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if(!isArraer){
				return credit_header;
			}
			return returnObj;
		} // end of method getCreditHeader()
		
		
		/**
		 * GET CREDIT CODE AND CREDIT ABBR
		 * @param String 'path', where Credit Head XML file has been stored 
		 * @return DEBITS Object 'debit_header'
		 */
		/*public Object[][] getDebitHeader(String ledgerCode,String year) {
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
*/		
		public Object[][] getDebitHeader(String ledgerCode,String year) {
			Object debit_header[][] = new Object[3][2];
			debit_header[0][0] ="0";
			debit_header[0][1] ="Commutation";
			debit_header[1][0] ="0";
			debit_header[1][1] ="Recovery";
			debit_header[2][0] ="0";
			debit_header[2][1] ="MISC";
			return debit_header;
		}  // end of method getDebitHeader()
		public Object[][] getArrearsDebitHeader(String arrearsCode,String year) {
			Object debit_header[][] = null;
			try{
						
				String query ="SELECT DISTINCT ARREARS_DEBIT_CODE,TRIM(DEBIT_ABBR) FROM HRMS_ARREARS_DEBIT_"+year+" " +
							" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE" +
							" WHERE ARREARS_CODE IN("+arrearsCode+") ORDER BY ARREARS_DEBIT_CODE";

				
			debit_header = getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return debit_header;
		}  // end of method getDebitHeader()
			
		public void genReport(PensionPaybill bean, HttpServletResponse response,String recPerPage,String colPerPage)
		{
			String name = "Pension Pay Bill";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean.getReport(), name,"");
			try {
					
				/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
				Object[][] dateData = getSqlModel().getSingleResult(dateQuery);*/
				
				rg.setFName("Pension Pay Bill_"
						+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
						+ bean.getYear() + "" + "." + bean.getReport());
				rg.addTextBold("Pension Pay Bill For The Month "
						+ Utility.month(Integer.parseInt(bean.getMonth()))
						+ " , " + bean.getYear()+"\n", 0, 1, 1,12);
				
				rg.addTextBold(bean.getDivName(), 0, 1, 1,12);
				
				//rg.addText("Date: " + dateData[0][0]+"\n", 0, 2, 0,12);
					
				boolean consCheck = false;
				
				String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_PENSION_LEDGER WHERE LEDGER_MONTH="
										+ bean.getMonth()
										+ " AND LEDGER_YEAR="
										+ bean.getYear()
										+ " AND LEDGER_DIVISION="
										+ bean.getDivCode()
										+" AND LEDGER_STATUS IN ('PENS_START','PENS_FINAL')";
								
				Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			
				String ledgerCode = "";
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
							
									
									/*rg.addTextBold("Salary Details", 0, 0, 0,8);
									rg.addText("\n", 0, 0, 0);*/
									rg=printreport(bean, salCredit, salDebit,rg, response,true,salEmp,"S");
									rg.addText("\n", 0, 0, 0);
									rg.addTextBold("Pension Total", 0, 0, 0,8);
									rg.addText("\n", 0, 0, 0);
									rg=printreport(bean, totalSalCredit, totalSalDebit,rg, response,false,totalSalCredit,"S");
							
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
		
		public Vector getSalary(PensionPaybill bean){
			Vector vect = new Vector();
			try{
				
				String ledgerCode = bean.getLedgerCode();
				
						String empQuery = " SELECT HRMS_PENSION_"+ bean.getYear()+".PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME," +
						"  FML_FNAME||' '||FML_MNAME||' '||FML_LNAME ,NVL(SAL_PENSION_ACCNO,' ')";
						
						empQuery +=" FROM HRMS_PENSION_"+ bean.getYear()
							+ " inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = HRMS_PENSION_"+ bean.getYear()+ ".PENS_EMP_ID "
							//+ " inner join hrms_dept on hrms_dept.DEPT_ID = hrms_salary_"+ bean.getYear()+ ".SAL_DEPT " +
							+" LEFT JOIN HRMS_SALARY_MISC	 on HRMS_SALARY_MISC.EMP_ID =  HRMS_EMP_OFFC.EMP_ID " 
							+" LEFT JOIN HRMS_EMP_NOMINEE ON (HRMS_EMP_NOMINEE.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND NOM_TYPE='P')"
                            +" LEFT JOIN HRMS_EMP_FML ON (HRMS_EMP_NOMINEE.NOM_NOMINEE=FML_ID)";
						//	" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_salary_misc.SAL_MICR_REGULAR  "+
							//" LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
					//	if(bean.getCheckBrn().equals("Y"))
							//empQuery += " inner join hrms_center on   hrms_center.CENTER_ID = hrms_salary_"+ bean.getYear()+".SAL_EMP_CENTER ";
					//	if(bean.getCheckDesg().equals("Y"))	
							//empQuery += " inner join hrms_rank on hrms_rank.RANK_ID = hrms_salary_"+ bean.getYear()+".SAL_EMP_RANK ";
						
						empQuery += " where PENS_LEDGER_CODE in ("+ ledgerCode	+ ")  ";
						
						if(!(bean.getBranchName().equals(""))){
							empQuery+="AND EMP_CENTER="+bean.getBranchCode()+" " ;
						}
						 if(!(bean.getOnHold().equals("A"))){
							 empQuery+="AND PENS_ONHOLD='"+bean.getOnHold()+"' " ;
							}
						
						empQuery += "ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
			
						Object [][] empData = getSqlModel().getSingleResult(empQuery);
						
						if (empData != null && empData.length > 0) {
							
							String creditQuery="";
							String creditQuery1=" ";
							String creditQuery2="";String creditQuery3="",creditQuery4="";;
							String debitQuery="",debitQuery1="",debitQuery2="",debitQuery3="";
							String count="0",totalCreditQuery1="",totalCreditQuery="",groupBy="",totalDebitQuery1="",totalDebitQuery="";
							
							Object[][] creditHead = getCreditHeader(ledgerCode,bean.getYear(),false);
							Object[][] debitHead = getDebitHeader(ledgerCode,bean.getYear());
							
							for (int i = 0; i < creditHead.length; i++) {
                                if(i==0){
                                    creditQuery1 += "select a"+i+".PENS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,'',a"+i+".PENS_AMOUNT,";
                                    
                                    creditQuery2 += " from (select PENS_EMP_ID,PENS_AMOUNT from HRMS_PENSION_CREDIT_"+bean.getYear()+" " +
                                                                    " where PENS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and PENS_LEDGER_CODE in ("+ledgerCode+")) a"+i+" ";
                                    
                                    totalCreditQuery1 += "select ' ','Total  [' || count(*)|| '] ','',sum(a"+i+".PENS_AMOUNT),";
                                    
                                    creditQuery4 = "inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".PENS_EMP_ID "; 
                                    
                                    creditQuery4 += " INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) "
                                            +" inner join HRMS_PENSION_"+bean.getYear()+" on (HRMS_PENSION_"+bean.getYear()+".PENS_EMP_ID=emp_id AND PENS_LEDGER_CODE in ("+ledgerCode+"))";
                                            //+" LEFT JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)";
                                    
                                    creditQuery4 += " WHERE  1=1 ";
                                    
                                    if (!bean.getBranchCode().equals(""))
                                            creditQuery4 += "AND HRMS_EMP_OFFC.EMP_CENTER = " + bean.getBranchCode();
                    
                                    if(!(bean.getOnHold().equals("A"))){
                                    	creditQuery4+="AND PENS_ONHOLD='"+bean.getOnHold()+"' " ;
        							}
                                    creditQuery4 += " ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
                                                                            
                                    groupBy="inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".PENS_EMP_ID ";
                                                            
                                    count=String.valueOf(i);
                            }
                            else{
                                    
                                    creditQuery1 += "a"+i+".PENS_AMOUNT,";
                                    totalCreditQuery1 += "sum(a"+i+".PENS_AMOUNT),";
                                    creditQuery3 += " INNER JOIN (select PENS_EMP_ID,PENS_AMOUNT from HRMS_PENSION_CREDIT_"+bean.getYear()+" WHERE PENS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" AND PENS_LEDGER_CODE IN ("+ledgerCode+")) a"+i+" on a"+count+".PENS_EMP_ID = a"+i+".PENS_EMP_ID ";
                            }
                                                                                    
                    }
							/*if(!bean.getBranchName().equals("")){
								creditQuery3 +=" WHERE SAL_EMP_CENTER="+bean.getBranchCode();
							}*/
							totalCreditQuery1= totalCreditQuery1.substring(0,totalCreditQuery1.length()-1)+" ,sum( PENS_ARREARS_AMOUNT) ";
							creditQuery1= creditQuery1.substring(0,creditQuery1.length()-1)+" , PENS_ARREARS_AMOUNT ";
							creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4;
							totalCreditQuery = totalCreditQuery1 + creditQuery2 +creditQuery3+groupBy
									+" INNER JOIN HRMS_PENSION_"+bean.getYear()+" ON (HRMS_PENSION_"+bean.getYear()+".PENS_EMP_ID=EMP_ID AND PENS_LEDGER_CODE in ("+ledgerCode+")) ";
							if(!(bean.getOnHold().equals("A"))){
								totalCreditQuery+=" WHERE PENS_ONHOLD='"+bean.getOnHold()+"' " ;
							}
							logger.info("creditQuery "+creditQuery );
							logger.info("total Crdit query"+totalCreditQuery);
							
							for (int i = 0; i < debitHead.length; i++) {
								if(i==0){
									debitQuery1 += "select a"+i+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'PAY',a"+i+".sal_amount,";
									debitQuery2 += " from (select emp_id,SAL_AMOUNT from HRMS_SAL_debits_"+bean.getYear()+" " +
													" where SAL_debit_CODE = "+String.valueOf(debitHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" ";
									count=String.valueOf(i);
									totalDebitQuery1 += "select ' ','Total  [' || count(*)|| '] ','PAY', sum(a"+i+".sal_amount),";
									
								}
								else{
									debitQuery1 += "a"+i+".sal_amount,";
									totalDebitQuery1 += "sum(a"+i+".sal_amount),";
									debitQuery3 += " inner join (select emp_id,SAL_AMOUNT from HRMS_SAL_debits_"+bean.getYear()+" where SAL_debit_CODE = "+String.valueOf(debitHead[i][0])+" and SAL_LEDGER_CODE in ("+ledgerCode+")) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";
									
								}
															
							}
							if(!bean.getBranchName().equals("")){
								debitQuery3 +=" WHERE EMP_CENTER="+bean.getBranchCode();
							}
							debitQuery = debitQuery1.substring(0,debitQuery1.length()-1) + debitQuery2 +debitQuery3+creditQuery4;
							totalDebitQuery = totalDebitQuery1.substring(0,totalDebitQuery1.length()-1) + debitQuery2 +debitQuery3+groupBy;
							totalDebitQuery ="select ' ','Total  [' || count(*)|| '] ','PAY',SUM(PENS_COMM_AMOUNT), SUM(PENS_REC_AMOUNT), SUM(PENS_MISC_RECOVERY)"
										+" from HRMS_PENSION_"+bean.getYear()
										+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=PENS_EMP_ID) WHERE PENS_LEDGER_CODE in ("+ledgerCode+")";
										
										if(!(bean.getOnHold().equals("A"))){
											totalDebitQuery+="AND PENS_ONHOLD='"+bean.getOnHold()+"' " ;
										}
										if(!bean.getBranchName().equals("")){
											totalDebitQuery +=" AND EMP_CENTER="+bean.getBranchCode();
										}
							logger.info("debitQuery "+debitQuery );
							logger.info("totalDebitQuery"+totalDebitQuery);
							/*String arrearsQuery="SELECT PENS_ARREARS_AMOUNT FROM HRMS_PENSION_"+bean.getYear()
									+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=PENS_EMP_ID)"
									+" WHERE PENS_LEDGER_CODE IN ("+ ledgerCode	+ ")" ;
							if(!(bean.getBranchName().equals(""))){
								arrearsQuery+="AND EMP_CENTER="+bean.getBranchCode()+" " ;
							}
							arrearsQuery += "ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
							Object [][]empArrearsData = getSqlModel().getSingleResult(arrearsQuery);
							empCreditData =Utility.joinArrays(empCreditData, empArrearsData, 0, empCreditData[0].length);*/
							Object [][]empCreditData = getSqlModel().getSingleResult(creditQuery);
							
							logger.info("empCreditData.LENGTH "+empCreditData.length );
							
							String empDebitQuery="SELECT EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'PAY',PENS_COMM_AMOUNT, PENS_REC_AMOUNT, PENS_MISC_RECOVERY FROM HRMS_PENSION_"+bean.getYear()
									+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=PENS_EMP_ID)"			
									+" WHERE PENS_LEDGER_CODE IN ("+ ledgerCode	+ ") " ;
							if(!(bean.getOnHold().equals("A"))){
								empDebitQuery+="AND PENS_ONHOLD='"+bean.getOnHold()+"' " ;
							}
							if(!(bean.getBranchName().equals(""))){
								empDebitQuery+="AND EMP_CENTER="+bean.getBranchCode()+" " ;
							}
							empDebitQuery += "ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
							Object [][]empDebitData = getSqlModel().getSingleResult(empDebitQuery);
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
		
		public Vector getArrear(PensionPaybill bean,String arrledgerCode){
			Vector vect = new Vector();
			try{
				
				//String arrledgerCode=bean.getArrLedgerCode();	
				
				String empQuery = " SELECT HRMS_ARREARS_"+ bean.getYear()
							+ ".EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH'),ARREARS_YEAR,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional'),NVL('',''),SUM(ARREARS_DAYS),nvl(BANK_NAME,''),nvl(SAL_ACCNO_REGULAR,''),nvl('',''),EMP_CENTER,NVL(CENTER_NAME,' '),EMP_DEPT,NVL(DEPT_NAME,' '),EMP_RANK,NVL(RANK_NAME,' ') ";

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
							+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR  ";
				
					empQuery += " WHERE ARREARS_CODE IN (" + arrledgerCode + ")  ";
				
					if (!(bean.getOnHold().equals("A")))
						empQuery += "AND ARREARS_ONHOLD= '" + bean.getOnHold() + "' ";
					
					if(!(bean.getBranchName().equals(""))){
						empQuery+="AND EMP_CENTER="+bean.getBranchCode()+"  " ;
					}
					empQuery+=" group by HRMS_ARREARS_2010.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH'),ARREARS_YEAR,"
						+" DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional'),NVL('',''),nvl(BANK_NAME,''),nvl(SAL_ACCNO_REGULAR,''),nvl('',''),"
						+" EMP_CENTER,NVL(CENTER_NAME,' '),EMP_DEPT,NVL(DEPT_NAME,' '),EMP_RANK,NVL(RANK_NAME,' ')";
					empQuery += " ORDER BY ";
				
					if (bean.getChkBrnOrder().equals("Y"))
						empQuery += " EMP_CENTER, ";
				
					if (bean.getChkDeptOrder().equals("Y"))
						empQuery += "  EMP_DEPT, ";
				
					if (bean.getChkDesgOrder().equals("Y"))
						empQuery += " EMP_RANK, ";
				
					empQuery += "  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
						
					//logger.info("arrearQuery------------"+empQuery);
					Object[][]empData = getSqlModel().getSingleResult(empQuery);
					
					Object creditTotalObj[][]= null;
					Object debitTotalObj[][]= null;
					
					String [][] creditHead=null;
					String [][]debitHead=null;
					
					Object finalCreditObj[][]=null;
					Object finalDebitObj[][]=null;
					
					if (empData != null && empData.length > 0) {
						//arrear object with credit and debit
							for(int i=0;i<empData.length;i++){
									
								try{						
									
									int count=3;
									
									String creditQuery ="  SELECT ARREARS_CREDIT_CODE,TRIM(CREDIT_ABBR),sum(ARREARS_AMT)  ,ARREARS_EMP_ID FROM HRMS_ARREARS_CREDIT_"+bean.getYear()
														+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ bean.getYear()+ ".ARREARS_CODE  "
														+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+ bean.getYear()+ ".ARREARS_CREDIT_CODE  "
														+" WHERE HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrledgerCode+")"
														+" AND ARREARS_EMP_ID = "+String.valueOf(empData[i][0]) 
														+" AND TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') LIKE '"+String.valueOf(empData[i][3])+"'" 
														+" AND ARREARS_YEAR = "+String.valueOf(empData[i][4]) 
														+" AND DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') LIKE '"+String.valueOf(empData[i][5])+"'" 
														+" GROUP BY ARREARS_CREDIT_CODE,TRIM(CREDIT_ABBR),ARREARS_EMP_ID ORDER BY ARREARS_EMP_ID,ARREARS_CREDIT_CODE ";
									
															
									String debitQuery ="  SELECT ARREARS_DEBIT_CODE,TRIM(DEBIT_ABBR),SUM(ARREARS_AMT),ARREARS_EMP_ID FROM HRMS_ARREARS_DEBIT_"+bean.getYear()
														+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_CODE  "
														+" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+ bean.getYear()+ ".ARREARS_DEBIT_CODE  "
														+" WHERE HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrledgerCode+") "
														+" AND ARREARS_EMP_ID = "+String.valueOf(empData[i][0]) 
														+" AND TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH')LIKE '"+String.valueOf(empData[i][3])+"'" 
														+" AND ARREARS_YEAR ="+String.valueOf(empData[i][4]) 
														+" AND DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') LIKE '"+String.valueOf(empData[i][5])+"'" 
														+" GROUP BY ARREARS_DEBIT_CODE,TRIM(DEBIT_ABBR),ARREARS_EMP_ID ORDER BY ARREARS_EMP_ID,ARREARS_DEBIT_CODE ";
														
									
									Object[][] empCredit = getSqlModel().getSingleResult(creditQuery);

									Object[][] empDebit = getSqlModel().getSingleResult(debitQuery);
									
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
		

		public ReportGenerator printreport(PensionPaybill bean ,Object [][] finalCredit,Object [][] finalDebit,ReportGenerator rg ,HttpServletResponse response,boolean printHeader,Object [][]empData,String type){
			try{
				
				String colPerPage=bean.getColumnNumber();
				String recPerPage=bean.getRowNumber();
				Object[][] creditHead =null;
				Object[][] debitHead =null;
				creditHead = getCreditHeader(bean.getLedgerCode(),bean.getYear(),true);
				debitHead = getDebitHeader(bean.getLedgerCode(),bean.getYear());
				
				
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
				int alignment[]=null;
				int width[]=null;
				
				repHeader = new Object[1][Integer.parseInt(colPerPage)+4];
				header = new String[Integer.parseInt(colPerPage)+4];
				repHeader=intSpace(repHeader);
				header=intSpace(header);
				
				
				if(type.equals("S")){						
					repHeader[0][0] = "Sr No";
					repHeader[0][1] = "Employee Name\nNominee Name\nBank Name   A/c No.";
					
					header[0] = "Sr No";
					header[1] = "Employee Name\nNominee Name\nBank Name   A/c No.";
				}
				
				
				alignment=new int[header.length];
				width=new int[header.length];
				
				width[0] =5;
				width[1] =25;
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
				
				/*if(bean.getChkBrnOrder().equals("Y"))
					brnCheck=true;
				if(bean.getChkDeptOrder().equals("Y"))
					deptCheck=true;
				if(bean.getChkDesgOrder().equals("Y"))
					desgCheck=true;*/
				
				for(int e=0;e<empData.length;e++){
					
					payCount=0;creCount=0;tempCount=0;dedCount=0;dedObjCnt=0;tempDedCount=0;
					double creditTot = 0.0,debitTot=0.0;
					double netCreditTot = 0.0,netDebitTot=0.0;
					
					if(empCount<Integer.parseInt(recPerPage)){
						
						if(printHeader){
							if(type.equals("A")){
															}
							if(type.equals("S")){
								repData[empCount][0] = String.valueOf(e+1);
								repData[empCount][1] = String.valueOf(empData[e][2]) +"\n" + String.valueOf(empData[e][3]) 
														+ "\n"+Utility.checkNull(String.valueOf(empData[e][4]));
							}
							
						}else{
							if(type.equals("S")){
								repData[empCount][0] ="";
								repData[empCount][1] = String.valueOf(empData[e][1]);
							}
						}
						HashMap creTotMap= new HashMap();
						HashMap debTotMap= new HashMap();
						
						/*if(e == 0){
							totalRepData[0][0] = "";
							totalRepData[0][1] = "Total Records ["+empData.length+"]";
						}*/
						
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
											//logger.info("tempJ=="+tempJ);
											//logger.info("e=e=="+e);
											//logger.info("pageTotCredit[0].length=="+pageTotCredit[0].length);
											//logger.info("pageTotCredit[0][tempJ]=="+String.valueOf(pageTotCredit[0][tempJ]));
											//logger.info("String.valueOf(finalCredit[e][tempJ])=="+String.valueOf(finalCredit[e][tempJ]));
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
								/*repData[empCount][repData[0].length -6]=String.valueOf(empData[e][10]);
								repData[empCount][repData[0].length -5]=String.valueOf(empData[e][11]);
								repData[empCount][repData[0].length -4]=String.valueOf(empData[e][3]);
								repData[empCount][repData[0].length -3]=String.valueOf(empData[e][4]);
								repData[empCount][repData[0].length -2]=String.valueOf(empData[e][12]);
								repData[empCount][repData[0].length -1]=String.valueOf(empData[e][13]);*/
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
						
						//rg.tableBody(repHeader, width, alignment,8);
						if(brnCheck || deptCheck || desgCheck){
							if(printHeader)
								setReport(repData, rg, brnCheck, deptCheck, desgCheck, header, width, alignment, 0);
							else
								rg.tableBody(repData, width, alignment,8);
						}else{
							
							if(printHeader)
								rg.tableBodyBold(header, repData, width,alignment,8);	
							else
								rg.tableBody(repData, width, alignment,8);
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
						if(printHeader)
							rg.tableBody(pageTotalFinal, width, alignment,8);
						
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
			int[] cellwidth, int[] alignmnet, int startSum ) {
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

						rg.addTextBold(printString, 0, 0, 0,8);
						//rg.addText("\n", 0, 0, 0);
						if(check==0)
							rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,8);	
						else
							rg.tableBody(reportObj, cellwidth, alignmnet,8);
						
						check++;
						//rg.tableBody(colNames, cellwidth, alignmnet,8);
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

				rg.addTextBold(printString, 0, 0, 0,8);
				//rg.addText("\n", 0, 0, 0);
				if(check==0)
					rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,8);	
				else
					rg.tableBody(reportObj, cellwidth, alignmnet,8);
				
				//rg.tableBody(colNames, cellwidth, alignmnet,8);
				//rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,8);	
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
							if(i==1480){
								logger.info("");
							}
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
}
