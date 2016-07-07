package org.paradyne.model.payroll;

import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.SalaryReconcilation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
/**
 * @author  Pankaj_Jain
 */
public class SalaryReconcilationReportModel extends ModelBase { 
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalaryReconcilationReportModel.class); 
	
	Object pageTotalFinal[][] = null;
	Object [][]totalCrdObj = null;
	Object [][]totalDbtObj = null;
	String totString="";
	volatile int count=0;
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try{
		
		String query =" SELECT CREDIT_CODE,TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
					 +" WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_PRIORITY";
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
	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try{
			String query =" SELECT DEBIT_CODE,TRIM(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
				 +" WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY";
		debit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	}  // end of method getDebitHeader()
	
	public void genReport(SalaryReconcilation bean, HttpServletResponse response,String recPerPage,String colPerPage)
	{
		try {
			recPerPage="10";
			colPerPage="5";
			
			String divDtlsQry=" SELECT NVL(DIV_ADDRESS1,' ')||'\n'||NVL(DIV_ADDRESS2,' ')||'\n'||" 
					+" NVL(DIV_ADDRESS3,' ')||'\n'||NVL(LOCATION_NAME, ' ')||'-'||NVL(DIV_PINCODE,'') FROM HRMS_DIVISION "
					+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID) "
					+" WHERE DIV_ID="+bean.getDivCode();
			Object [][] divObj = getSqlModel().getSingleResult(divDtlsQry);
			String name = "Salary Reconciliation Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", name,"A4");
			rg.setFName("Salary Reconciliation ");
			if(divObj != null)
			{
				rg.addTextBold(bean.getDivName()+"\n"+String.valueOf(divObj[0][0]), 0, 1, 1,12);
				rg.addText("\n", 0, 0, 0);
			}
			
			java.util.Date d = new java.util.Date();
			logger.info("Date : - "+d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
			String strDate=sdf.format(d);
			rg.addTextBold("Reconciliation Statement for "+
					Utility.month(Integer.parseInt(bean.getMonthTo()))+" "
					+bean.getYearTo() +" with "+Utility.month(Integer.parseInt(bean.getMonthFor()))+" "+bean.getYearFor(), 0, 1, 1, 12);
			rg.addTextBold("Date : - "+strDate, 0, 2, 1, 12);
			rg.addText("\n", 0, 0, 0);
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "
					+ bean.getMonthFor()
					+ " AND LEDGER_YEAR = "
					+ bean.getYearFor()
					+ " AND LEDGER_DIVISION = "
					+ bean.getDivCode();
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			String ledgerCode = "";
			if (ledgerData == null || ledgerData.length <= 0) {
				logger.info("In else");
				rg.addText("\n Salary for "+Utility.month(Integer.parseInt(bean.getMonthFor()))+" "+bean.getYearFor()+" has not been processed",0,0,0,12);
				rg.createReport(response);
				return;
			} else {
				for (int i = 0; i < ledgerData.length; i++) {
					ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
				}
				ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
					try {
						Object[][] creditHead = getCreditHeader();
						Object[][] debitHead = getDebitHeader();
						String creditQuery = getCreditQry(creditHead, bean.getYearFor(), bean.getMonthFor(), ledgerCode);
						String debitQuery = getdebitQuery(debitHead, bean.getYearFor(), bean.getMonthFor(), ledgerCode);
						String condnQry = "WHERE NVL(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),' ') NOT LIKE '"+bean.getMonthTo()+"-"+bean.getYearTo()+"'"
						+" AND NVL(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),' ') NOT LIKE '"+bean.getMonthTo()+"-"+bean.getYearTo()+"' AND SAL_ONHOLD = 'N'";
						String orderBy = " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
						creditQuery+=orderBy;
						debitQuery+=orderBy;
						Object empCreditData[][] = getSqlModel().getSingleResult(creditQuery);
						Object empDebitData[][] = getSqlModel().getSingleResult(debitQuery);
						ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "
						+ bean.getMonthTo()
						+ " AND LEDGER_YEAR = "
						+ bean.getYearTo()
						+ " AND LEDGER_DIVISION = "
						+ bean.getDivCode();
						ledgerData = getSqlModel().getSingleResult(ledgerQuery);
						ledgerCode="";
						logger.info("After Executing Query");
						if(ledgerData != null && ledgerData.length > 0)
						{
							for (int i = 0; i < ledgerData.length; i++) 
								ledgerCode+=ledgerData[i][0]+",";
							ledgerCode=ledgerCode.substring(0,ledgerCode.length()-1);						
						}
						else
						{
							logger.info("In else");
							rg.addText("\n Salary for "+Utility.month(Integer.parseInt(bean.getMonthTo()))+" "+bean.getYearTo()+" has not been processed",0,0,0,12);
							rg.createReport(response);
							return;
						}
						Vector v = new Vector();
						Object newJoineesCrdObj[][] = null;
						Object newJoineesDbtObj[][] = null;
						Object leftEmpCrdObj[][] = null;
						Object leftEmpDbtObj[][] = null;
						Object [][] finalCreditObj = null;
						Object [][] finalDebitObj = null;
						
						if(!ledgerCode.equals(""))
						{
							creditQuery = getCreditQry(creditHead, bean.getYearTo(), bean.getMonthTo(), ledgerCode);
							debitQuery = getdebitQuery(debitHead, bean.getYearTo(), bean.getMonthTo(), ledgerCode);
							String newJoineesCrdtQry = creditQuery, newjoineesDbtQry=debitQuery;
							creditQuery+=condnQry+orderBy;
							debitQuery+=condnQry+orderBy;
							
							newJoineesCrdtQry+=" WHERE NVL(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),' ') LIKE '"+bean.getMonthTo()+"-"+bean.getYearTo()+"' AND SAL_ONHOLD='N' ";
							newJoineesCrdtQry+=orderBy;
							
							newjoineesDbtQry+=" WHERE NVL(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),' ') LIKE '"+bean.getMonthTo()+"-"+bean.getYearTo()+"' AND SAL_ONHOLD='N' ";
							newjoineesDbtQry+=orderBy;
							
							newJoineesCrdObj = getSqlModel().getSingleResult(newJoineesCrdtQry);
							newJoineesDbtObj = getSqlModel().getSingleResult(newjoineesDbtQry);
							
							String leftEmpCrdQry = newJoineesCrdtQry.replace("EMP_REGULAR_DATE","EMP_LEAVE_DATE");
							String leftEmpDbtQry = newjoineesDbtQry.replace("EMP_REGULAR_DATE","EMP_LEAVE_DATE");
							
							leftEmpCrdObj=getSqlModel().getSingleResult(leftEmpCrdQry);
							leftEmpDbtObj=getSqlModel().getSingleResult(leftEmpDbtQry);
							Object[][] settleCrdObj = settlementCreditData(creditHead, bean.getMonthTo(),bean.getYearTo());
							Object[][] settleDebObj = settlementDebitData(debitHead, bean.getMonthTo(),bean.getYearTo());
							if(settleCrdObj != null && settleCrdObj.length > 0)
								leftEmpCrdObj = Utility.joinArrays(leftEmpCrdObj,settleCrdObj, 1, 0);
							if(settleDebObj != null && settleDebObj.length > 0)
								leftEmpDbtObj = Utility.joinArrays(leftEmpDbtObj,settleDebObj, 1, 0);
							
							
							Object empCreditDataNext [][] = getSqlModel().getSingleResult(creditQuery);
							Object empDebitDataNext [][] = getSqlModel().getSingleResult(debitQuery);
							
							try {
								String arrearsQry=" SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER " 
											    + " WHERE ARREARS_DIVISION = "+bean.getDivCode()+" AND ARREARS_REF_MONTH = "+bean.getMonthFor()+" AND ARREARS_REF_YEAR ="+bean.getYearFor();
								Object arrearsObj[][] = getSqlModel().getSingleResult(arrearsQry);
								String arrearsCode="";
								if(arrearsObj != null && arrearsObj.length > 0)
								{
									for (int i = 0; i < arrearsObj.length; i++) 
										arrearsCode+=String.valueOf(arrearsObj[i][0])+",";
									arrearsCode=arrearsCode.substring(0,arrearsCode.length()-1);
									arrearsObj  = getSqlModel().getSingleResult(
										creditArrears(creditHead, bean.getYearFor(),arrearsCode));
									empCreditData = addValues(empCreditData, arrearsObj, 0, 3, empCreditData.length);
									arrearsObj  = getSqlModel().getSingleResult(
										debitArrears(debitHead, bean.getYearFor(), arrearsCode));
									empDebitData = addValues(empDebitData, arrearsObj, 0, 3, empDebitData.length);
								}
								arrearsQry=" SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER " 
								    + " WHERE ARREARS_DIVISION = "+bean.getDivCode()+" AND ARREARS_REF_MONTH = "+bean.getMonthTo()+" AND ARREARS_REF_YEAR ="+bean.getYearTo();
								arrearsObj =  getSqlModel().getSingleResult(arrearsQry);
								
								if(arrearsObj != null && arrearsObj.length > 0)
								{
									for (int i = 0; i < arrearsObj.length; i++) 
										arrearsCode+=String.valueOf(arrearsObj[i][0])+",";
									arrearsCode=arrearsCode.substring(0,arrearsCode.length()-1);
									arrearsObj  = getSqlModel().getSingleResult(
											creditArrears(creditHead, bean.getYearTo(),arrearsCode));
									empCreditDataNext = addValues(empCreditDataNext, arrearsObj, 0, 4, empCreditDataNext.length);
									arrearsObj  = getSqlModel().getSingleResult(
										debitArrears(debitHead, bean.getYearTo(), arrearsCode));
									empDebitDataNext = addValues(empDebitDataNext, arrearsObj, 0, 4, empDebitDataNext.length);
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
							Object tempObj[][] = null;
							
							if(empCreditDataNext.length > empCreditData.length)
							{
								for (int i = 0; i < empCreditDataNext.length; i++)
								{
									for (int k = 0; k < empCreditData.length; k++) {
										
									if(String.valueOf(empCreditData[k][0])
											.equals(String.valueOf(empCreditDataNext[i][0])))
									{
										tempObj=new Object[1][empCreditDataNext[0].length];
										for (int j = 0; j < tempObj[0].length; j++)
										{
											if(j<4)
												tempObj[0][j]=empCreditDataNext[i][j];
											else
												tempObj[0][j]=(Double.parseDouble(String.valueOf(empCreditDataNext[i][j]))-Double.parseDouble(String.valueOf(empCreditData[k][j]))
														);
										}
										v.add(tempObj);
										tempObj=new Object[1][empDebitDataNext[0].length];
										for (int j = 0; j < tempObj[0].length; j++)
										{
											if(j<4)
												tempObj[0][j]=empDebitDataNext[i][j];
											else
												tempObj[0][j]=(Double.parseDouble(String.valueOf(empDebitDataNext[i][j]))-Double.parseDouble(String.valueOf(empDebitData[k][j])));
										}
										v.add(tempObj);
									}
								}
								}
							}
							else
							{
								logger.info("In Else final Obj-----");
								try {
									for (int k = 0; k < empCreditData.length; k++) 
									{
										for (int i = 0; i < empCreditDataNext.length; i++) {
										if(String.valueOf(empCreditDataNext[i][0])
												.equals(String.valueOf(empCreditData[k][0])))
										{
											tempObj=new Object[1][empCreditDataNext[0].length];
											for (int j = 0; j < tempObj[0].length; j++)
											{
												if(j<4)
													tempObj[0][j]=empCreditDataNext[i][j];
												else
													tempObj[0][j]=(Double.parseDouble(String.valueOf(empCreditDataNext[i][j]))
															-Double.parseDouble(String.valueOf(empCreditData[k][j])));
											}
											v.add(tempObj);
											tempObj=new Object[1][empDebitDataNext[0].length];
											for (int j = 0; j < tempObj[0].length; j++)
											{
												if(j<4)
													tempObj[0][j]=empDebitDataNext[i][j];
												else
													tempObj[0][j]=(Double.parseDouble(String.valueOf(empDebitDataNext[i][j]))
															-Double.parseDouble(String.valueOf(empDebitData[k][j])));
											}
											v.add(tempObj);
											}
										}
									}
								}catch(Exception e)
								{
									logger.info("Exception in reconciliation while adding to vector "+e);
									e.printStackTrace();
								}
							}
							finalCreditObj = new Object[v.size()/2][empCreditData[0].length];
							finalDebitObj = new Object[v.size()/2][empDebitData[0].length];
							int crCount=0,drCount=0;
							Object[][] tempRowObj=null;
							for (int j = 0; j < v.size(); j++) {
								tempRowObj = (Object[][])v.get(j);
								if(j%2==0)
									finalCreditObj[crCount++]=tempRowObj[0];
								else
									finalDebitObj[drCount++]=tempRowObj[0];
							}
						}
						else
						{
							rg.addText("\n Salary for "+Utility.month(Integer.parseInt(bean.getMonthFor()))+" "+bean.getYearFor()+" has not been processed",0,0,0,12);
							rg.createReport(response);
							return;
						}
						if(creditHead.length < debitHead.length){
							if(debitHead.length < Integer.parseInt(colPerPage)){
								colPerPage = String.valueOf(debitHead.length);
								logger.info("in if "+colPerPage );
							}
						}
						else{
							if(creditHead.length<Integer.parseInt(colPerPage)){
								colPerPage = String.valueOf(creditHead.length);
								logger.info("in else"+colPerPage );
							}
						}
						logger.info("colPerPage "+colPerPage);
						Object repHeader[][] = new Object[1][Integer.parseInt(colPerPage)+6];
						repHeader = intSpace(repHeader);
						repHeader[0][0] = "Employee Id";
						repHeader[0][1] = "Employee Name";
						repHeader[0][2] = "Salary Days";
						double row1=0.0;
						int payCount=0,creCount=0,tempCount=0,dedCount=0,dedObjCnt=0,tempDedCount=0;
						int[] cellwidth = new int[repHeader[0].length];
						int[] alignment = new int[repHeader[0].length];
						cellwidth[0] =10;
						cellwidth[1] =20;
						cellwidth[2] =5;
						cellwidth[3] =5;
						alignment[0] =0;
						alignment[1] =0;
						alignment[2] =1;
						alignment[3] =2;
						
						row1 = (double) creditHead.length / Integer.parseInt(colPerPage);
						java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
								row1);
						payCount = Integer.parseInt(bigDecRow1.setScale(0,
								java.math.BigDecimal.ROUND_UP).toString());
							row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
							java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
									row1);
							dedCount = Integer.parseInt(bigDecRow2.setScale(0,
									java.math.BigDecimal.ROUND_UP).toString());
						
						for (int i = 4; i < repHeader[0].length; i++) {
							if(i<repHeader[0].length-2){
								tempCount= creCount;
									for (int j = 0; j < payCount; j++) {
										if(creCount< creditHead.length){
										repHeader[0][i] = String.valueOf(repHeader[0][i])+ String.valueOf(creditHead[creCount][1])+"\n" ;
										creCount += Integer.parseInt(colPerPage);
										}
										else
											repHeader[0][i] = String.valueOf(repHeader[0][i])+"\n" ;
									}
								creCount = tempCount;
								creCount++;
								
								tempDedCount= dedObjCnt;
								for (int j = 0; j < dedCount; j++) {
									if(dedObjCnt< debitHead.length){
									repHeader[0][i] = String.valueOf(repHeader[0][i])+ String.valueOf(debitHead[dedObjCnt][1])+"\n" ;
									dedObjCnt += Integer.parseInt(colPerPage);
									}
									else
										repHeader[0][i] = String.valueOf(repHeader[0][i])+"\n" ;
								}
								dedObjCnt = tempDedCount;
								dedObjCnt++;
								
								}
							else if(i<repHeader[0].length-1){
								for (int j = 0; j < payCount; j++) {
									repHeader[0][i] = String.valueOf(repHeader[0][i])+"Total Credit\n";
								}
								for (int j = 0; j < dedCount; j++) {
									repHeader[0][i] = String.valueOf(repHeader[0][i])+"Total Debit\n";
								}
							}
							else
							{
								repHeader[0][i] = String.valueOf(repHeader[0][i])+"Net Salary";
							}
							cellwidth[i] =10;
							alignment[i] =2;
							}
							
							if(empCreditData != null && empCreditData.length > 0)
							{
								rg.addTableHeader("Salary for "+Utility.month(Integer.parseInt(bean.getMonthFor()))+"-"+bean.getYearFor(),0,0,0,false,12);
								rg.addText("\n",0,0,0);
								pageTotalFinal = new Object[1][Integer.parseInt(colPerPage)+6];
								pageTotalFinal = intSpace(pageTotalFinal);
								pageTotalFinal[0][0]="Total For";
								pageTotalFinal[0][1]=Utility.month(Integer.parseInt(bean.getMonthFor()))+" "
								+bean.getYearFor()+" ["+empCreditData.length+"]";
								totString+="Prev. Month : ";
								repHeader[0][0]="";
								repHeader[0][1]="Month-Year  No. of Employees";
								repHeader[0][2]="";
								row1 = (double) creditHead.length / Integer.parseInt(colPerPage);
								bigDecRow1 = new java.math.BigDecimal(
										row1);
								payCount = Integer.parseInt(bigDecRow1.setScale(0,
										java.math.BigDecimal.ROUND_UP).toString());
									for (int j = 0; j < payCount; j++) {
										repHeader[0][3] = String.valueOf(repHeader[0][3]) +"PAY =>" +"\n";
									}
									row1 = (double) debitHead.length / Integer.parseInt(colPerPage);
									bigDecRow2 = new java.math.BigDecimal(row1);
									dedCount = Integer.parseInt(bigDecRow2.setScale(0,
											java.math.BigDecimal.ROUND_UP).toString());
									for (int j = 0; j < dedCount; j++) {
										repHeader[0][3] = String.valueOf(repHeader[0][3]) +"DED =>" +"\n";
										}
								pageTotalFinal[0][3]=String.valueOf(repHeader[0][3]);
								rg.tableBody(repHeader, cellwidth, alignment,12);
								rg = addReportData(rg, empCreditData, empDebitData, recPerPage, 
									colPerPage, payCount, dedCount, repHeader[0].length, 
									creditHead.length, debitHead.length, cellwidth, alignment,false);
								
								repHeader[0][0]="Employee Id";
								repHeader[0][1]="Employee Name";
								repHeader[0][2]="Salary Days";
							}
						
							if(newJoineesCrdObj != null && newJoineesCrdObj.length > 0)
							{
								rg.addTableHeader("New Joinees",0,0,0,false,12);
								rg.addText("\n",0,0,0);
								pageTotalFinal = new Object[1][Integer.parseInt(colPerPage)+6];
								pageTotalFinal = intSpace(pageTotalFinal);
								pageTotalFinal[0][0]="Total ";
								pageTotalFinal[0][1]="New Employees ["+newJoineesCrdObj.length+"]";
								pageTotalFinal[0][3]=String.valueOf(repHeader[0][3]);
								totString+="New Joinees : ";
								rg.tableBody(repHeader, cellwidth, alignment,12);
								rg = addReportData(rg, newJoineesCrdObj, newJoineesDbtObj, recPerPage, 
									colPerPage, payCount, dedCount, repHeader[0].length, 
									creditHead.length, debitHead.length, cellwidth, alignment,true);
								
							}
							if(leftEmpCrdObj != null && leftEmpCrdObj.length > 0)
							{
								rg.addTableHeader("Left Employees",0,0,0,false,12);
								rg.addText("\n",0,0,0);
								pageTotalFinal = new Object[1][Integer.parseInt(colPerPage)+6];
								pageTotalFinal = intSpace(pageTotalFinal);
								pageTotalFinal[0][0]="Total ";
								pageTotalFinal[0][1]="Left Employees ["+leftEmpCrdObj.length+"]";
								pageTotalFinal[0][3]=String.valueOf(repHeader[0][3]);
								totString+="Left Employees : ";
								rg.tableBody(repHeader, cellwidth, alignment,12);
								rg = addReportData(rg, leftEmpCrdObj, leftEmpDbtObj, recPerPage, 
									colPerPage, payCount, dedCount, repHeader[0].length, 
									creditHead.length, debitHead.length, cellwidth, alignment,true);
							}
							if(finalCreditObj != null && finalCreditObj.length > 0)
							{
								rg.addTableHeader("Variance",0,0,0,false,12);
								rg.addText("\n",0,0,0);
								pageTotalFinal = new Object[1][Integer.parseInt(colPerPage)+6];
								pageTotalFinal = intSpace(pageTotalFinal);
								pageTotalFinal[0][0]="Total ";
								pageTotalFinal[0][1]="Variance "+Utility.month(Integer.parseInt(bean.getMonthTo()))+" "
								+bean.getYearTo();
								pageTotalFinal[0][3]=String.valueOf(repHeader[0][3]);
								totString+="Variance : ";
								rg.tableBody(repHeader, cellwidth, alignment,12);
								rg = addReportData(rg, finalCreditObj, finalDebitObj, recPerPage, 
									colPerPage, payCount, dedCount, repHeader[0].length, 
									creditHead.length, debitHead.length, cellwidth, alignment,true);
							}
							
							rg.addText("\n"+totString, 0, 0, 0,14);
							rg.createReport(response);
						}
				
					 catch (Exception e) {
						e.printStackTrace();
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public String getCreditQry(Object[][] creditHead,String year,String month, String ledgerCode)
	{
		String creditQuery,creditQuery1="",creditQuery2="",creditQuery3="",creditQuery4="";
		creditQuery1 += "SELECT A0.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,SAL_DAYS,A0.A,";
		creditQuery2 += " FROM (SELECT EMP_ID,NVL(SAL_AMOUNT,0) AS A FROM HRMS_SAL_CREDITS_"+year+" " +
						" WHERE SAL_CREDIT_CODE = "+String.valueOf(creditHead[0][0])+" AND SAL_LEDGER_CODE IN ("+ledgerCode+")) A0 ";
		creditQuery4 = " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = A0.EMP_ID " +
					   " INNER JOIN HRMS_SALARY_"+year+" ON  (HRMS_SALARY_"+year+".EMP_ID = A0.EMP_ID  " +
					   " AND SAL_LEDGER_CODE IN ("+ledgerCode+") AND SAL_ONHOLD='N')" ;
		for (int i = 1; i < creditHead.length; i++) {
				creditQuery1 += "NVL(A"+i+".SAL_AMOUNT,0),";
				creditQuery3 += " LEFT JOIN (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" AND SAL_LEDGER_CODE IN ("+ledgerCode+")) A"+i+" ON A0.EMP_ID = A"+i+".EMP_ID ";
		}
		creditQuery = creditQuery1.substring(0,creditQuery1.length()-1)+creditQuery2+creditQuery3+creditQuery4;
		return creditQuery;
	}
	
	public String getdebitQuery(Object[][] debitHead, String year, String month, String ledgerCode)
	{
		String debitQuery,debitQuery1="",debitQuery2="",debitQuery3="",debitQuery4="";
		debitQuery1 += "SELECT A0.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,SAL_DAYS,A0.A,";
		debitQuery2 += " FROM (SELECT EMP_ID,NVL(SAL_AMOUNT,0) AS A FROM HRMS_SAL_DEBITS_"+year+" " +
						" WHERE SAL_DEBIT_CODE = "+String.valueOf(debitHead[0][0])+" AND SAL_LEDGER_CODE IN ("+ledgerCode+")) A0 ";
		debitQuery4 = " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = A0.EMP_ID " +
					   " INNER JOIN HRMS_SALARY_"+year+" ON  (HRMS_SALARY_"+year+".EMP_ID = A0.EMP_ID  " +
					   " AND SAL_LEDGER_CODE IN ("+ledgerCode+") AND SAL_ONHOLD='N')" ;
		for (int i = 1; i < debitHead.length; i++) {
				debitQuery1 += "NVL(A"+i+".SAL_AMOUNT,0),";
				debitQuery3 += " LEFT JOIN (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE = "+String.valueOf(debitHead[i][0])+" AND SAL_LEDGER_CODE IN ("+ledgerCode+")) A"+i+" ON A0.EMP_ID = A"+i+".EMP_ID ";
		}
		debitQuery = debitQuery1.substring(0,debitQuery1.length()-1) + debitQuery2 +debitQuery3+debitQuery4;
		return debitQuery;
	}
	
	public ReportGenerator addReportData(ReportGenerator rg,Object [][] dataCrdObj, Object[][] dataDbtObj,
			String recPerPage, String colPerPage, int payCount, int dedCount, 
			int hdrLen, int crdLen, int dbtLen, int[] cellwidth, int[] alignment, boolean showRecords)
	{
		try {
		if(totalCrdObj == null)
		{
			totalCrdObj = new Object[1][crdLen+5];
			totalDbtObj = new Object[1][dbtLen+5];
			totalCrdObj = intZero(totalCrdObj);
			totalDbtObj = intZero(totalDbtObj);
		}
		if(dataCrdObj != null && dataCrdObj.length > 0)
		{
			Object[][] repData = null;
			repData = new Object[dataCrdObj.length][Integer.parseInt(colPerPage)+6];
			repData = intSpace(repData);
			int empCount=0;
			int creCount=0,tempCount=0,dedObjCnt=0,tempDedCount=0;
			for(int e=0;e<dataCrdObj.length;e++){
			creCount=0;tempCount=0;dedObjCnt=0;tempDedCount=0;
			double creditTot = 0.0,debitTot=0.0;
				repData[empCount][0] = dataCrdObj[e][1];
				repData[empCount][1] = String.valueOf(dataCrdObj[e][2]);
				repData[empCount][2] = dataCrdObj[e][3];
				HashMap creTotMap= new HashMap();
				HashMap debTotMap= new HashMap();
				try {
					double row1 = (double) crdLen / Integer.parseInt(colPerPage);
					java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
							row1);
					payCount = Integer.parseInt(bigDecRow1.setScale(0,
							java.math.BigDecimal.ROUND_UP).toString());
						for (int j = 0; j < payCount; j++) {
							repData[empCount][3] = String.valueOf(repData[empCount][3]) +"PAY =>" +"\n";
						}
						row1 = (double) dbtLen / Integer.parseInt(colPerPage);
						java.math.BigDecimal bigDecRow2 = new java.math.BigDecimal(
								row1);
						dedCount = Integer.parseInt(bigDecRow2.setScale(0,
								java.math.BigDecimal.ROUND_UP).toString());
						for (int j = 0; j < dedCount; j++) {
							repData[empCount][3] = String.valueOf(repData[empCount][3]) +"DED =>" +"\n";
							}
				}catch(Exception ex)
				{
					logger.error("Exception e : in bigdecimal"+e);
					ex.printStackTrace();
				}
				for (int i = 4; i < hdrLen; i++) {
					int tempJ =i;
					int tempI =i;
					if(i<hdrLen-2){
						tempCount= creCount;
						for (int j = 0; j < payCount; j++) {
							try {
							if(creCount< crdLen){
								String colTotal = (String)creTotMap.get("a"+String.valueOf(j));
								if(colTotal==null)
									colTotal ="0.0";
								colTotal = String.valueOf(Math.round(Double.parseDouble(String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble( String.valueOf(dataCrdObj[e][tempJ]))))));
								creTotMap.put("a"+String.valueOf(j), colTotal);
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ String.valueOf(dataCrdObj[e][tempJ])+"\n" ;
								totalCrdObj[0][tempJ] = Double.parseDouble(String.valueOf(totalCrdObj[0][tempJ]))
														+ Double.parseDouble(String.valueOf(dataCrdObj[e][tempJ]));
								creCount += Integer.parseInt(colPerPage);
								tempJ += Integer.parseInt(colPerPage);
							}
							else
							{
								repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ;
							}
							}catch(Exception ex1)
							{
								logger.error("Exception in paycount loop : "+ex1);
								ex1.printStackTrace();
							}
						}
						creCount = tempCount;
						creCount++;
						tempDedCount= dedObjCnt;
						for (int j = 0; j < dedCount; j++) {
							try {
							if(dedObjCnt< dbtLen){
								String colTotal = (String)debTotMap.get("a"+String.valueOf(j));
								if(colTotal==null)
									colTotal ="0.0";
								colTotal = String.valueOf(Math.round(Double.parseDouble(String.valueOf(Double.parseDouble(colTotal) + Double.parseDouble( String.valueOf(dataDbtObj[e][tempI]))))));
								debTotMap.put("a"+String.valueOf(j), colTotal);
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ String.valueOf(dataDbtObj[e][tempI])+"\n" ;
								totalDbtObj[0][tempI] = Double.parseDouble(String.valueOf(totalDbtObj[0][tempI]))
								+ Double.parseDouble(String.valueOf(dataDbtObj[e][tempI]));
								dedObjCnt += Integer.parseInt(colPerPage);
								tempI += Integer.parseInt(colPerPage);
							}
							else
							{
								repData[empCount][i] = String.valueOf(repData[empCount][i])+"\n" ; 
							}
							}catch(Exception ex2)
							{
								logger.error("Exception in dedCount loop : "+ex2);
								ex2.printStackTrace();
							}
						}
						dedObjCnt = tempDedCount;
						dedObjCnt++;
						
						}else if(i< hdrLen-1){
							try {
							for (int j = 0; j < payCount; j++) {
								creditTot += Double.parseDouble(String.valueOf(creTotMap.get("a"+String.valueOf(j))));
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ creTotMap.get("a"+String.valueOf(j))+"\n";
							}
							for (int j = 0; j < dedCount; j++) {
								debitTot += Double.parseDouble(String.valueOf(debTotMap.get("a"+String.valueOf(j))));
								repData[empCount][i] = String.valueOf(repData[empCount][i])+ debTotMap.get("a"+String.valueOf(j))+"\n";
							}
							}catch(Exception ex3)
							{
								logger.error("Exception in totCredit and TotDebit : "+ex3);
								ex3.printStackTrace();
							}
						}		
						else{
							repData[empCount][i] = Math.round(Double.parseDouble(String.valueOf(creditTot - debitTot)));
						}
					}
					creditTot=0.0;debitTot=0.0;
					empCount++;
			}
			creCount=0;tempCount=0;dedObjCnt=0;tempDedCount=0;
			HashMap finalCreTotMap = new HashMap();
			HashMap finalDebTotMap = new HashMap();
			String finalCreTotal=null,finalDebTotal=null;
			double netCreditTot=0.0,netDebitTot=0.0;
				for (int j2 = 4; j2 < totalCrdObj[0].length; j2++)
				{
					try {
					totalCrdObj[0][j2]=Math.round(Double.parseDouble(String.valueOf(totalCrdObj[0][j2])));
					}catch(Exception e)
					{
						logger.error("Error in type casting totCredit: "+e);
					}
				}
				for (int j2 = 4; j2 < totalDbtObj[0].length; j2++)
				{
					try {
						totalDbtObj[0][j2]=Math.round(Double.parseDouble(String.valueOf(totalDbtObj[0][j2])));
					}catch(Exception e)
					{
						logger.error("Error in type casting totDebit: "+e);
					}
				}
					
			for (int i = 4; i < pageTotalFinal[0].length; i++) {
				 if(i<pageTotalFinal[0].length-2){
					tempCount= creCount;
					int temp = i;
						for (int j = 0; j < payCount; j++) {
							try {
							if(creCount < crdLen){
								 finalCreTotal = (String)finalCreTotMap.get("a"+String.valueOf(j));
								if(finalCreTotal==null)
									finalCreTotal ="0.0";
								//pageTotalFinal[0][i] =  Double.parseDouble(String.valueOf(pageTotalFinal[0][i])) +  Double.parseDouble( String.valueOf(pageTotCredit[0][temp]));
								finalCreTotal = String.valueOf(Math.round(Double.parseDouble((String.valueOf(Double.parseDouble(finalCreTotal) + Double.parseDouble(String.valueOf(totalCrdObj[0][temp])))))));
								finalCreTotMap.put("a"+String.valueOf(j), finalCreTotal);
																			
							pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+ String.valueOf(totalCrdObj[0][temp])+"\n" ;
							temp += Integer.parseInt(colPerPage);
							creCount += Integer.parseInt(colPerPage);
							}
							else
								pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+"\n" ;
						}catch(Exception e)
						{
							logger.error("Exception in pageTotal payCount: "+e);
							e.printStackTrace();
						}
					}
					creCount = tempCount;
					creCount++;
					//logger.info("pageTotalFinal[0][i]CC----------- "+pageTotalFinal[0][i]);
					
					int temp1 = i;
					tempDedCount= dedObjCnt;
					
					for (int j = 0; j < dedCount; j++) {
						try {
						if(dedObjCnt < dbtLen){
							finalDebTotal = (String)finalDebTotMap.get("a"+String.valueOf(j));
							if(finalDebTotal==null)
								finalDebTotal ="0.0";
							//pageTotalFinal[0][i] =  Double.parseDouble(String.valueOf(pageTotalFinal[0][i])) +  Double.parseDouble( String.valueOf(pageTotCredit[0][temp]));
							finalDebTotal = String.valueOf(Math.round(Double.parseDouble((String.valueOf((Double.parseDouble(finalDebTotal) + Double.parseDouble( String.valueOf(totalDbtObj[0][temp1]))))))));
							finalDebTotMap.put("a"+String.valueOf(j), finalDebTotal);
																				
							pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+ String.valueOf(totalDbtObj[0][temp1])+"\n" ;
							temp1 += Integer.parseInt(colPerPage);
							dedObjCnt += Integer.parseInt(colPerPage);
						}
						else
							pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i])+"\n" ;
						}catch(Exception e)
						{
							logger.error("Excpetion in pageTot dedCount e : "+e);
							e.printStackTrace();
						}
					}
					dedObjCnt = tempDedCount;
					dedObjCnt++;
					//logger.info("pageTotalFinal[0][i] DD----------- "+pageTotalFinal[0][i]);
					}
				else if(i < pageTotalFinal[0].length-1){
					try{
					for (int j = 0; j < payCount; j++) {
						netCreditTot += Double.parseDouble(String.valueOf(finalCreTotMap.get("a"+String.valueOf(j)))); 
						pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) + finalCreTotMap.get("a"+String.valueOf(j)) +"\n";
						
					}
					for (int j = 0; j < dedCount; j++) {
						netDebitTot += Double.parseDouble(String.valueOf(finalDebTotMap.get("a"+String.valueOf(j))));
						pageTotalFinal[0][i] = String.valueOf(pageTotalFinal[0][i]) + finalDebTotMap.get("a"+String.valueOf(j)) +"\n";
						
					}
				}catch(Exception e)
				{
					logger.error("Exception in PageTot tot credit : "+e);
					e.printStackTrace();
				}
				}
				else
					pageTotalFinal[0][i] = Math.round(Double.parseDouble(String.valueOf(netCreditTot - netDebitTot)));									
			}

			if(String.valueOf(pageTotalFinal[0][1]).startsWith("Variance"))
			{
				Vector<Object> v = new Vector<Object>();
				for (int j = 0; j < repData.length; j++) 
					if(Double.parseDouble(String.valueOf(repData[j][repData[j].length-1])) != 0)
						v.add(repData[j]);
				
				repData = new Object[v.size()][repData[0].length];
				for (int j = 0; j < v.size(); j++) 
					repData[j] = (Object[])v.get(j);
				pageTotalFinal[0][1]=String.valueOf(pageTotalFinal[0][1])+" ["+v.size()+"]";
				totString+=" ["+v.size()+"]        ";
			}
			else
				totString+=" ["+dataCrdObj.length+"]        ";
			
			if(showRecords)
				rg.tableBody(repData, cellwidth, alignment,12);
			rg.tableBody(pageTotalFinal, cellwidth, alignment,12);
			rg.addText("\n",0,0,0);
		}
		 }catch(Exception e)
		  {
			  logger.error("Exception in addReport method : "+e);
		  }
		return rg;
	}
	
	public String creditArrears(Object[][] creditHead,String year, String arrearsCode)
	{
		String creditQuery,creditQuery1="",creditQuery2="",creditQuery3="",creditQuery4="";
		creditQuery1 += "SELECT A0.ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,ARREARS_DAYS,A0.A,";
		creditQuery2 += " FROM (SELECT ARREARS_EMP_ID,NVL(ARREARS_AMT,0) AS A FROM HRMS_ARREARS_CREDIT_"+year+" " +
						" WHERE ARREARS_CREDIT_CODE = "+String.valueOf(creditHead[0][0])+" AND ARREARS_CODE IN ("+arrearsCode+")) A0 ";
		creditQuery4 = " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = A0.ARREARS_EMP_ID " +
					   " INNER JOIN HRMS_ARREARS_"+year+" ON  (HRMS_ARREARS_"+year+".EMP_ID = A0.ARREARS_EMP_ID  " +
					   " AND ARREARS_CODE IN ("+arrearsCode+"))" ;
		for (int i = 1; i < creditHead.length; i++) {
				creditQuery1 += "NVL(A"+i+".ARREARS_AMT,0),";
				creditQuery3 += " LEFT JOIN (SELECT ARREARS_EMP_ID,ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"+year+" WHERE ARREARS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" AND ARREARS_CODE IN ("+arrearsCode+")) A"+i+" ON A0.ARREARS_EMP_ID = A"+i+".ARREARS_EMP_ID ";
		}
		creditQuery = creditQuery1.substring(0,creditQuery1.length()-1)+creditQuery2+creditQuery3+creditQuery4;
		creditQuery+=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		return creditQuery;
	}
	
	public String debitArrears(Object[][] debitHead,String year,String arrearsCode)
	{
		String debitQuery,debitQuery1="",debitQuery2="",debitQuery3="",debitQuery4="";
		debitQuery1 += "SELECT A0.ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,ARREARS_DAYS,A0.A,";
		debitQuery2 += " FROM (SELECT ARREARS_EMP_ID,NVL(ARREARS_AMT,0) AS A FROM HRMS_ARREARS_DEBIT_"+year+" " +
						" WHERE ARREARS_DEBIT_CODE = "+String.valueOf(debitHead[0][0])+" AND ARREARS_CODE IN ("+arrearsCode+")) A0 ";
		debitQuery4 = " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = A0.ARREARS_EMP_ID " +
					   " INNER JOIN HRMS_ARREARS_"+year+" ON  (HRMS_ARREARS_"+year+".EMP_ID = A0.ARREARS_EMP_ID  " +
					   " AND ARREARS_CODE IN ("+arrearsCode+"))" ;
		for (int i = 1; i < debitHead.length; i++) {
				debitQuery1 += "NVL(A"+i+".ARREARS_AMT,0),";
				debitQuery3 += " LEFT JOIN (SELECT ARREARS_EMP_ID,ARREARS_AMT FROM HRMS_ARREARS_DEBIT_"+year+" WHERE ARREARS_DEBIT_CODE = "+String.valueOf(debitHead[i][0])+" AND ARREARS_CODE IN ("+arrearsCode+")) A"+i+" ON A0.ARREARS_EMP_ID = A"+i+".ARREARS_EMP_ID ";
		}
		debitQuery = debitQuery1.substring(0,debitQuery1.length()-1)+debitQuery2+debitQuery3+debitQuery4;
		debitQuery+=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		return debitQuery;
	}
	
	
	public Object[][] settlementCreditData(Object[][] creditHead, String month , String year)
	{
		String settleCreditQuery=" SELECT HRMS_SETTL_HDR.SETTL_ECODE,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS ENAME,'', SUM(SETTL_AMT) FROM HRMS_SETTL_HDR "
							   + " INNER JOIN HRMS_SETTL_CREDITS ON HRMS_SETTL_CREDITS.SETTL_CODE = HRMS_SETTL_HDR.SETTL_CODE "
							   + " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE "
							   + " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
							   + " WHERE TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'MM') = "+month+" AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'YYYY') = "+year+" "
							   + " GROUP BY HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE,SETTL_ECODE,HRMS_EMP_OFFC.EMP_TOKEN, "
							   + " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,CREDIT_PRIORITY,CREDIT_CODE "
							   + " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),CREDIT_PRIORITY,CREDIT_CODE";
		Object[][] settleCreditData = getSqlModel().getSingleResult(settleCreditQuery);
		
		if(settleCreditData == null || settleCreditData.length == 0)
			return null;
		
		Object [][] finalCreditData = new Object[settleCreditData.length/creditHead.length][creditHead.length+4];
		finalCreditData = intZero(finalCreditData);
		try {
				int count = 0;
				for (int i = 0; i < settleCreditData.length;)
				{
					finalCreditData[count][0] = settleCreditData[i][0];
					finalCreditData[count][1] = settleCreditData[i][1];
					finalCreditData[count][2] = settleCreditData[i][2];
					finalCreditData[count][3] = settleCreditData[i][3];
					try {
						for (int j = 4; j < creditHead.length+4; j++) 
							finalCreditData[count][j] = settleCreditData[i++][4];
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					count++;
				}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return finalCreditData;
	}
	
	public Object[][] settlementDebitData(Object[][] debitHead, String month , String year)
	{
		String settleDebitQuery=" SELECT HRMS_SETTL_HDR.SETTL_ECODE,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS ENAME,'', SUM(SETTL_AMT) FROM HRMS_SETTL_HDR "
			                  + " INNER JOIN HRMS_SETTL_DEBITS ON HRMS_SETTL_DEBITS.SETTL_CODE = HRMS_SETTL_HDR.SETTL_CODE "
			                  + " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE "
			                  + " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE) "
			                  + " WHERE TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'MM') = "+month+" AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'YYYY') = "+year+" "
			                  + " GROUP BY HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE,SETTL_ECODE,HRMS_EMP_OFFC.EMP_TOKEN, "
			                  + " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,DEBIT_PRIORITY,DEBIT_CODE "
			                  + " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),DEBIT_PRIORITY,DEBIT_CODE";
		Object[][] settleDebitData = getSqlModel().getSingleResult(settleDebitQuery);
		
		if(settleDebitData == null || settleDebitData.length == 0)
			return null;
		
		Object [][] finalDebitData = new Object[settleDebitData.length/debitHead.length][debitHead.length+4];
		finalDebitData = intZero(finalDebitData);
		logger.info("settlement data : "+settleDebitData.length);
		logger.info("final settlement debit data : "+finalDebitData.length);
		logger.info("debitHead : "+debitHead.length);
		try {
				int count = 0;
				for (int i = 0; i < settleDebitData.length;)
				{
					finalDebitData[count][0] = settleDebitData[i][0];
					finalDebitData[count][1] = settleDebitData[i][1];
					finalDebitData[count][2] = settleDebitData[i][2];
					finalDebitData[count][3] = settleDebitData[i][3];
					try {
						for (int j = 4; j < debitHead.length + 4; j++)
							finalDebitData[count][j] = settleDebitData[i++][4];
					} catch (Exception e) {
						e.printStackTrace();
					}
					count++;
				}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return finalDebitData;
	}
	
	public Object[][] addValues(Object[][] obj1, Object[][] obj2, int matchIndex, int startIndex, int endIndex)
	{
		if(obj1.length > obj2.length)
		{
			for (int i = 0; i < obj1.length; i++) 
				for (int j = 0; j < obj2.length; j++) 
					if(String.valueOf(obj1[i][matchIndex]).equals(String.valueOf(obj2[j][matchIndex])))
					{
						for (int j2 = startIndex; j2 < endIndex; j2++) {
							try {
								obj1[i][j2] = Math.round(Double
										.parseDouble(String
												.valueOf(obj1[i][j2])))
										+ Double.parseDouble(String
												.valueOf(obj2[j][j2]));
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						break;
					}
			return obj1;
		}
		else
		{
			for (int i = 0; i < obj2.length; i++) 
				for (int j = 0; j < obj1.length; j++) 
					if(String.valueOf(obj2[i][matchIndex]).equals(String.valueOf(obj1[j][matchIndex])))
					{
						for (int j2 = startIndex; j2 < endIndex; j2++) {
							try {
								obj2[i][j2] = Math.round(Double
										.parseDouble(String
												.valueOf(obj2[i][j2])))
										+ Double.parseDouble(String
												.valueOf(obj1[j][j2]));
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						break;
					}
			return obj2;
		}				
		
	}
	
	public Object[][] getTotalForObject(Object[][] dataObj, int startIndex, int endIndex)
	{
		Object [][] totalObj = new Object[1][dataObj[0].length];
		totalObj = intZero(totalObj);
		for (int i = 0; i < dataObj.length; i++) 
			for (int j = startIndex; j < endIndex; j++) {
				try {
					totalObj[0][j] = Math
							.round(Double.parseDouble(String
									.valueOf(totalObj[0][j]))
									+ Double.parseDouble(String
											.valueOf(dataObj[0][j])));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		return totalObj;
	}
	
}


