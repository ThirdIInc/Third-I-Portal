/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.DebitRecoveryReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * @author varunk
 * CHANGED ON DT:26.04.2008
 * BY PRADEEP 
 *
 */
public class DebitRecoveryReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void generateReport(DebitRecoveryReport bean,HttpServletRequest request, HttpServletResponse response, String reportPath) {
		
		String month = bean.getMonth();
		String year = bean.getYear();
		String debitCode=bean.getDebitCode();
		String ledgerData="";
		org.paradyne.lib.ireportV2.ReportGenerator rg=null;
		ReportDataSet rds = new ReportDataSet();
		
		/*
		 * Following query is used to select the ledger_code 
		 */
		String query = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+"";
		Object ledgerCode[][]=getSqlModel().getSingleResult(query);
		
		if(ledgerCode!=null && ledgerCode.length>0){
			for (int i = 0; i < ledgerCode.length; i++) {
				if(i==ledgerCode.length-1){
					ledgerData += ledgerCode[i][0];
				}
				else{
					ledgerData += ledgerCode[i][0]+",";
				}
			}//End of for loop
			/*
			 * Following query is used to select the employee token,name and the debit amount for the selected debit head where On Hold Salary is 'All' 
			 */
			if(bean.getOnHold().equals("A")){
				String query1 = "SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME AS EMP_NAME,SAL_AMOUNT "
					+" ,NVL(CENTER_NAME,' ') FROM HRMS_SAL_DEBITS_"+year
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID) "
					+"  INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
					+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE) "
					+" WHERE HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE IN("+ledgerData+") and SAL_AMOUNT>0 AND SAL_DEBIT_CODE ="+bean.getDebitCode()+" " ;
					
					if(!bean.getBranchCode().equals("")){
						query1+=" AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchCode();
					}
					if(!bean.getDivId().equals(""))
					{
						query1+=" AND HRMS_EMP_OFFC.EMP_DIV= "+bean.getDivId();
					}
					if(!bean.getPayBillId().equals(""))
					{
						query1+=" AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPayBillId();
					}
					
				query1+=" ORDER BY CENTER_NAME,upper(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
	
				Object debitData[][]=getSqlModel().getSingleResult(query1);
				
				
				/*
				 * Following query is used to calculate the total debit amount 
				 */
				String query2 = "SELECT sum(SAL_AMOUNT) "
								+" FROM HRMS_SAL_DEBITS_"+year
								+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID) "
								+"  INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
								+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)  "
								+" WHERE HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE IN ("+ledgerData+") AND SAL_DEBIT_CODE ="+debitCode+" ";
				if(!bean.getBranchCode().equals("")){
					query2+=" AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchCode();
				}
				if(!bean.getDivId().equals(""))
				{
					query2+=" AND HRMS_EMP_OFFC.EMP_DIV= "+bean.getDivId();
				}
				if(!bean.getPayBillId().equals(""))
				{
					query2+=" AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPayBillId();
				}
				
				Object sum[][]=getSqlModel().getSingleResult(query2);
				
				Object[][] amt=new Object[1][1];
				if(String.valueOf(sum[0][0]).equals("null") || String.valueOf(sum[0][0]).equals("")){
					amt[0][0]="";
				}else {
					amt[0][0]=sum[0][0];
				}//End if
				
				try {
					String reportType = bean.getReportType();
					String title="Debit Recovery Report";
					String dateQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
					 Object dt[][]=getSqlModel().getSingleResult(dateQuery);
					 String fileName = "Debit Recovery Report"+Utility.getRandomNumber(1000);
					 rds.setReportType(reportType);
					 rds.setFileName(fileName);
					 rds.setReportName("Debit Recovery Report");
					 rds.setPageSize("A4");
					 rds.setPageOrientation("potrait");
					
					 if(reportPath.equals("")){
							rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
						}else{
							logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
							rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
							//request.setAttribute("reportPath", reportPath);
							request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
							request.setAttribute("action", "DebitRecoveryReport_input.action");
						};
						TableDataSet todayDate = new TableDataSet();
						Object dateobj[][]=new Object[1][1];
						dateobj[0][0]="Date :"+String.valueOf(dt[0][0]);
						todayDate.setData(dateobj);
						todayDate.setCellAlignment(new int[] {2});
						todayDate.setCellWidth(new int[] {100});
						todayDate.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
						//todayDate.setBorder(false);
						//todayDate.setHeaderTable(true);
						todayDate.setBlankRowsBelow(1);
						rg.addTableToDoc(todayDate);
					//rg.addFormatedText("Date :"+String.valueOf(dt[0][0]),1,0,2,10);
					//rg.addText("\n",0,0,0);
					String MONTH[]={"","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
					String abc[] = {"Employee Id","Employee Name","Amount","Branch"};
					
					int cellwidth[] = {30,40,30,30};
					int alignment[] = {1,0,2,0};
					if(debitData==null || debitData.length==0){
						Object [][] obj = new Object[1][1];
						
						obj [0][0] = " Records are not available. ";  
						 
					TableDataSet subtitleName = new TableDataSet();
					
					subtitleName.setData(obj);									
					subtitleName.setCellAlignment(new int[] {1});
					subtitleName.setCellWidth(new int[] {100});
					subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
					//subtitleName.setBorder(false);
					//subtitleName.setHeaderTable(true);
					subtitleName.setBlankRowsBelow(1);
					rg.addTableToDoc(subtitleName);
						//rg.addText("Records are not available.",0,1,0);
					}else {
						
							/*if(bean.getReportType().equals("Pdf")){
								//rg.addTextBold(bean.getDebitHead()+" for the month of "+MONTH[Integer.parseInt(bean.getMonth())]+"-"+bean.getYear(), 0, 1, 0);
							}else {*/
								 Object [][] title1 = new Object[1][1];
								
								 title1 [0][0] = bean.getDebitHead()+" for the month of "+MONTH[Integer.parseInt(bean.getMonth())]+"-"+bean.getYear();  
								 
								 int [] cols1 = {1};
								 int [] align1 = {1};
								//rg.tableBodyNoCellBorder(title1,cols,align,1); 
								 
								 TableDataSet subtitleName = new TableDataSet();
									
									subtitleName.setData(title1);									
									subtitleName.setCellAlignment(new int[] {1});
									subtitleName.setCellWidth(new int[] {100});
									subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
									//subtitleName.setBorder(false);
									//subtitleName.setHeaderTable(true);
									subtitleName.setBlankRowsBelow(1);
									rg.addTableToDoc(subtitleName);
									
									
									TableDataSet reportdebitData = new TableDataSet();
									reportdebitData.setHeader(abc);
									reportdebitData.setData(debitData);
									reportdebitData.setCellAlignment(alignment);
									reportdebitData.setCellWidth(cellwidth);				
									//reportdebitData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
									reportdebitData.setHeaderBGColor(new BaseColor(225, 225, 225));
									reportdebitData.setBorder(true);
									reportdebitData.setHeaderTable(true);
									reportdebitData.setBorderDetail(3);
									reportdebitData.setBlankRowsBelow(1);									
									rg.addTableToDoc(reportdebitData);
					//rg.addText("\n",0,0,0);
									
					//rg.tableBody(abc, debitData, cellwidth, alignment);
					//rg.addText("\n\n",0,0,0);
					/*if(bean.getReportType().equals("Pdf")){
						//rg.addText("Total Amount    "+amt[0][0]+"", 0, 2, 0);
						}else {*/
							 Object [][] title2 = new Object[1][3];
							 title2 [0][0] = " ";
							 title2 [0][1] = " ";
							 title2 [0][2] = " Total Amount  "+amt[0][0];  
							 
							 int [] cols2 = {20,20,30};
							 int [] align2 = {0,0,1};
							 
							 TableDataSet totalSum = new TableDataSet();
								
							 totalSum.setData(title2);
							 totalSum.setCellAlignment(align2);
							 totalSum.setCellWidth(cols2);				
							 totalSum.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
								
							 //totalSum.setBorder(true);
							// totalSum.setHeaderTable(true);
							 //totalSum.setBorderDetail(3);
							 totalSum.setBlankRowsBelow(1);									
								rg.addTableToDoc(totalSum);
							//rg.tableBodyNoCellBorder(title1,cols,align,1); 
						//}//End else 
				
					}//End of else condition
					rg.process();
					if(reportPath.equals("")){
						rg.createReport(response);
					}else{
						
						rg.saveReport(response);
						}
				} catch (Exception e) {
					logger.error(e.getMessage());
				    }
				
			}//End if
			else{
			/**
			 * Following query is used to select the employee name,id and amount where On Hold is 'yes' or 'no'
			 */
				System.out.println("Inside Else Condition ::::::::::::::::::::::");
					String query1 = "SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME AS EMP_NAME,SAL_AMOUNT "
									+" ,NVL(CENTER_NAME,' ')  FROM HRMS_SAL_DEBITS_"+year
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID) "
									+"  INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
									+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID " +
											" AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerData+"))"
									+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE) "
									+" WHERE HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE IN("+ledgerData+") and SAL_AMOUNT>0 AND SAL_DEBIT_CODE ="+bean.getDebitCode()+" AND SAL_ONHOLD='"+bean.getOnHold()+"' " ;
									
									if(!bean.getBranchCode().equals("")){
										query1+=" AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchCode();
									}
									if(!bean.getDivId().equals(""))
									{
										query1+=" AND HRMS_EMP_OFFC.EMP_DIV= "+bean.getDivId();
									}
									if(!bean.getPayBillId().equals(""))
									{
										query1+=" AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPayBillId();
									}
									
								query1+=" ORDER BY CENTER_NAME,upper(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
									
								//	" ORDER BY CENTER_NAME,upper(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
					
					Object debitData[][]=getSqlModel().getSingleResult(query1);
					/*
					 * Following query is used to select the total debit amount
					 */
					String query2 = "SELECT sum(SAL_AMOUNT) "
									+" FROM HRMS_SAL_DEBITS_"+year
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID) "
									+"  INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
									+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID " +
											" AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerData+"))"
									+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)  "
									+" WHERE HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE IN ("+ledgerData+") AND SAL_DEBIT_CODE ="+debitCode+" AND SAL_ONHOLD='"+bean.getOnHold()+"' ";
					if(!bean.getBranchCode().equals("")){
						query2+=" AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchCode();
					}
					if(!bean.getDivId().equals(""))
					{
						query2+=" AND HRMS_EMP_OFFC.EMP_DIV= "+bean.getDivId();
					}
					if(!bean.getPayBillId().equals(""))
					{
						query2+=" AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPayBillId();
					}
					
					Object sum[][]=getSqlModel().getSingleResult(query2);
					
					Object[][] amt=new Object[1][1];
					if(String.valueOf(sum[0][0]).equals("null") || String.valueOf(sum[0][0]).equals("")){
						amt[0][0]="";
					}else {
						amt[0][0]=sum[0][0];
					}
					
					try {
						String reportType = bean.getReportType();
						String title="Debit Recovery Report";
						String dateQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
						 Object dt[][]=getSqlModel().getSingleResult(dateQuery);
						
						 String fileName = "Debit Recovery Report"+Utility.getRandomNumber(1000);
						 rds.setReportType(reportType);
						 rds.setFileName(fileName);
						 rds.setReportName("Debit Recovery Report");
						 rds.setPageSize("A4");
						 rds.setPageOrientation("potrait");
						
						 if(reportPath.equals("")){
								rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
							}else{
								logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
								rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
								//request.setAttribute("reportPath", reportPath);
								request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
								request.setAttribute("action", "DebitRecoveryReport_input.action");
							};
							TableDataSet todayDate = new TableDataSet();
							Object dateobj[][]=new Object[1][1];
							dateobj[0][0]="Date :"+String.valueOf(dt[0][0]);
							todayDate.setData(dateobj);
							todayDate.setCellAlignment(new int[] {0,1 });
							todayDate.setCellWidth(new int[] {10,60});
							todayDate.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
							todayDate.setBorder(false);
							todayDate.setHeaderTable(true);
							todayDate.setBlankRowsBelow(1);
							rg.addTableToDoc(todayDate);
						//rg.addFormatedText("Date :"+String.valueOf(dt[0][0]),1,0,2,10);
						//rg.addText("\n",0,0,0);
						String MONTH[]={"","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
						String abc[] = {"Employee Id","Employee Name","Amount","Branch"};
						int cellwidth[] = {30,40,30,30};
						int alignment[] = {1,0,2,0};
						if(debitData==null || debitData.length==0){
							Object [][] obj = new Object[1][1];
							
							obj [0][0] = " Records are not available. ";  
							 
						TableDataSet subtitleName = new TableDataSet();
						
						subtitleName.setData(obj);									
						subtitleName.setCellAlignment(new int[] {1});
						subtitleName.setCellWidth(new int[] {100});
						subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
						//subtitleName.setBorder(false);
						//subtitleName.setHeaderTable(true);
						subtitleName.setBlankRowsBelow(1);
						rg.addTableToDoc(subtitleName);
							//rg.addText("Records are not available.",0,1,0);
						}else {
							
						
								 Object [][] title1 = new Object[1][1];
								
								 title1 [0][0] = bean.getDebitHead()+" for the month of "+MONTH[Integer.parseInt(bean.getMonth())]+"-"+bean.getYear();  
								 
								//rg.tableBodyNoCellBorder(title1,cols,align,1);
								 
							
							
							TableDataSet subtitleName = new TableDataSet();
							
							subtitleName.setData(title1);									
							subtitleName.setCellAlignment(new int[] {1});
							subtitleName.setCellWidth(new int[] {100});
							subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
							//subtitleName.setBorder(false);
							//subtitleName.setHeaderTable(true);
							subtitleName.setBlankRowsBelow(1);
							rg.addTableToDoc(subtitleName);
						
							TableDataSet reportdebitData = new TableDataSet();
							reportdebitData.setHeader(abc);
							reportdebitData.setData(debitData);
							reportdebitData.setCellAlignment(alignment);
							reportdebitData.setCellWidth(cellwidth);				
							//reportdebitData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
							reportdebitData.setHeaderBGColor(new BaseColor(225, 225, 225));
							reportdebitData.setBorder(true);
							reportdebitData.setHeaderTable(true);
							reportdebitData.setBorderDetail(3);
							reportdebitData.setBlankRowsBelow(1);									
							rg.addTableToDoc(reportdebitData);
						//rg.addText("\n",0,0,0);
						//rg.tableBody(abc, debitData, cellwidth, alignment);
						//rg.addText("\n\n",0,0,0);
						
							 Object [][] title2 = new Object[1][3];
							 title2 [0][0] = " ";
							 title2 [0][1] = " ";
							 title2[0][2] = " Total Amount  "+amt[0][0];  
							 
							 int [] cols = {20,20,30};
							 int [] align = {0,0,1};
							 
							 TableDataSet totalSum = new TableDataSet();
								
							 totalSum.setData(title2);
							 totalSum.setCellAlignment(align);
							 totalSum.setCellWidth(cols);				
							 totalSum.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
								
							 //totalSum.setBorder(true);
							// totalSum.setHeaderTable(true);
							 //totalSum.setBorderDetail(3);
							 totalSum.setBlankRowsBelow(1);									
								rg.addTableToDoc(totalSum);
							//rg.tableBodyNoCellBorder(title1,cols,align,1); 
						
					    }//End of else condition
						rg.process();
						if(reportPath.equals("")){
							rg.createReport(response);
						}else{
							
							rg.saveReport(response);
							}
					} catch (Exception e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					    }
				
				
			        }//End of else condition 
		       }//End if condition
		
	      }//End of method
	

  }//End of model class
