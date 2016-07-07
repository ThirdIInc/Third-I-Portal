/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.UploadCredit;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.NonIndustrialSalaryModel;
import org.paradyne.model.payroll.SalaryProcessModel;

/**
 * @author varunk
 *
 */
public class UploadCreditModel extends ModelBase {
static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
public void generateReport(UploadCredit bean,HttpServletResponse response) {
		
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME " 
						 +",NVL('',0),CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC  " +
						 " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "   
						 +"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						 //+" WHERE EMP_STATUS = 'S'" +
						 +"  order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		Object empList[][]=getSqlModel().getSingleResult(query);
		
		try {
				String title = "Employee List";
				org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",title);
				String abc[] = {"Employee Code","Employee Name","Amount","Branch","Department"};
				int cellwidth[] = {10,30,10,15,35};
				int alignment[] = {0,0,0,0,0};
				rg.tableBodyPayDown(abc, empList, cellwidth, alignment);
				rg.createReport(response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String updCredits(UploadCredit upload,Object[][] add,String path){
		try{
			String empQuery ="select emp_id,emp_token from hrms_emp_offc ";
			ArrayList addList =new ArrayList();
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			
				for (int i = 0; i < empObj.length; i++) {
					
					try {
						for (int j = 0; j < add.length; j++) {
							if (String.valueOf(empObj[i][1]).trim().equals(
									String.valueOf(add[j][1]).trim())) {
								if (String.valueOf(empObj[i][0]).equals("null")|| String.valueOf(empObj[i][0])
												.equals(null)|| String.valueOf(empObj[i][0]).trim().equals("")) {
									addList.add("0");
									addList.add(String.valueOf(add[j][0]));
									break;
								} else {
									addList.add(String.valueOf(empObj[i][0]));
									addList.add(String.valueOf(add[j][0]));
									break;
								}
							}
							
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				
			Object[][] finalObj = new Object[addList.size()/2][2];
			int index=0;
			try {
				for (int i = 0; i < addList.size(); i++) {
					finalObj[index][1] = addList.get(i);
					i++;
					finalObj[index][0] = addList.get(i);
					index++;
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
							
			String ledgerCode = "";
			///this Query is fired to retrieve the Ledger Code of the month and year which is Entered. 
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
					+ "ledger_month="
					+ upload.getMonth()
					+ " and ledger_year="
					+ upload.getYear() + " and LEDGER_STATUS='SAL_START'";
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			if (ledgerData != null && ledgerData.length > 0) {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					} else {
						ledgerCode += ledgerData[i][0] + ",";
					}
				}
				
				///the particular Ledger code is set in the SalLedgerCode
				upload.setSalLedgerCode(String.valueOf(ledgerCode));
			} else {
				return "1";
			}
			
			boolean result=false;
			String empIdString ="";
			try {
				
				String updDebQuery = "update hrms_sal_credits_"
					+ upload.getYear()
					+ " set sal_amount=? where emp_id=? and sal_ledger_code in("
					+ ledgerCode + ") " + " and sal_credit_code="
					+ upload.getCreditCode();
				result = getSqlModel().singleExecute(updDebQuery,finalObj);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			Object[][] updateObj = new Object[finalObj.length][3];
			/*for (int i = 0; i < updateObj.length; i++) {
				logger.info("------------------add obj["+i+"][0]"+finalObj[i][0]);
				logger.info("------------------add obj["+i+"][1]"+finalObj[i][1]);
			}*/
			
			try{
				logger.info("finalObj.length"+finalObj.length);
				Object[] empIdObj = new Object[finalObj.length];
				for (int i = 0; i < finalObj.length; i++) {
					empIdString = empIdString + String.valueOf(finalObj[i][1]) +",";
					empIdObj[i]= String.valueOf(finalObj[i][1]);
				}
				empIdString = empIdString.substring(0,empIdString.length()-1);
				logger.info("empIdString "+empIdString );
								
				if(result){
					
					HashMap creditConfDataMap = getCreditConfMap(empIdString);
					
					HashMap creditDataMap = getCreditMap(ledgerCode,upload.getYear(),empIdString);
					
					String profQuery =" select nvl(CONF_PROFHANDI_FLAG,'N') from HRMS_SALARY_CONF";
					Object profFlagObject[][] = getSqlModel().getSingleResult(profQuery);
					
					logger.info("size of creditDataMap"+creditDataMap.size());
					
					if(creditDataMap!=null){
						Object[][] credit_amount = null; 
						Object[][] gross_amount = null; 
						Object[][] debitObject = new Object[finalObj.length*3][3];
						double pf_amount=0,esi_amount=0,ptax_amount=0;
						int debitObjCOunt=0;
						
						for (int i = 0; i < finalObj.length; i++) {
							try {
								credit_amount = (Object[][])creditDataMap.get(String.valueOf(empIdObj[i]));
								
								String typeBranch = "SELECT EMP_TYPE,EMP_CENTER,CENTER_LOCATION,NVL(CENTER_PTAX_STATE,15),NVL(SAL_PTAX_FLAG,'Y') FROM HRMS_EMP_OFFC " +
										" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"+
										" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) " +
										" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
										" WHERE HRMS_EMP_OFFC.EMP_ID="+String.valueOf(empIdObj[i]);
								
								Object typeBranchObj[][] = getSqlModel().getSingleResult(typeBranch);
								logger.info("empIdObj-------------------------- "+empIdObj[i]);
								/*for (int j = 0; j < credit_amount.length; j++) {
									logger.info("credit amount "+credit_amount[j][0]);
									logger.info("credit amount "+credit_amount[j][1]);
								}*/
								pf_amount = getPFAmount("Y", upload.getMonth(),upload.getYear(), credit_amount, path, String.valueOf(typeBranchObj[0][0]) , String.valueOf(typeBranchObj[0][1]),upload);
								logger.info("pf_amount-----------"+pf_amount);
								debitObject[debitObjCOunt][0]= pf_amount;
								debitObject[debitObjCOunt][1]= upload.getPfCode();
								debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								
								debitObjCOunt++;
								
								gross_amount = (Object[][])creditConfDataMap.get(String.valueOf(empIdObj[i]));
								esi_amount =getESIAmount(path, upload.getMonth(), upload.getYear(),  credit_amount, gross_amount,String.valueOf(typeBranchObj[0][0]) , String.valueOf(typeBranchObj[0][1]), String.valueOf(empIdObj[i]),upload);
								logger.info("esi_amount-----------"+esi_amount);
								
								debitObject[debitObjCOunt][0]= esi_amount;
								debitObject[debitObjCOunt][1]= upload.getEsiCode();
								debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								
								debitObjCOunt++;
								
								ptax_amount = getPTAXAmount(credit_amount, path,upload.getMonth(), upload.getYear(), String.valueOf(typeBranchObj[0][0]) ,
														String.valueOf(typeBranchObj[0][1]),  String.valueOf(typeBranchObj[0][3]),upload);
								
								if(String.valueOf(typeBranchObj[0][4]).equals("N")){
									ptax_amount = 0;
								}
								debitObject[debitObjCOunt][0]= ptax_amount;
								debitObject[debitObjCOunt][1]= upload.getPtaxCode();
								debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								
								debitObjCOunt++;
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						SalaryProcessModel salModel= new SalaryProcessModel();
						salModel.initiate(context, session);
						debitObject = salModel.getRoundCheckValues(debitObject);
						salModel.terminate();
						String updateCredit = "update hrms_sal_debits_"+upload.getYear()+" set SAL_AMOUNT=? where SAL_debit_CODE=? and emp_id=? and sal_ledger_code in("+ledgerCode+")";
						getSqlModel().singleExecute(updateCredit,debitObject);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try{
			String creditSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_credits_"+upload.getYear()+" where " +
									" emp_id in ("+empIdString+") and sal_ledger_code in("+ledgerCode+") group by emp_id order by emp_id ";
			Object[][] creditSumObject = getSqlModel().getSingleResult(creditSumQuery);
			
			String debitSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_debits_"+upload.getYear()+" where " +
									" emp_id in ("+empIdString+") and sal_ledger_code in("+ledgerCode+") group by emp_id order by emp_id ";
			
			Object[][] debitSumObject = getSqlModel().getSingleResult(debitSumQuery);
			
			Object[][] finalSalaryObject = new Object[creditSumObject.length][4];
			for (int i = 0; i < creditSumObject.length; i++) {
				finalSalaryObject[i][0] = creditSumObject[i][1];
				finalSalaryObject[i][1] = debitSumObject[i][1];
				finalSalaryObject[i][2] = Double.parseDouble(String.valueOf(creditSumObject[i][1])) - Double.parseDouble(String.valueOf(debitSumObject[i][1]));
				finalSalaryObject[i][3] = creditSumObject[i][0]; 
				}
			
			String updAmt = "UPDATE HRMS_SALARY_"+upload.getYear()+" SET SAL_TOTAL_CREDIT =?,SAL_TOTAL_DEBIT=? ,SAL_NET_SALARY = ?"
							+" where SAL_LEDGER_CODE IN ("+ledgerCode+") AND  EMP_ID=?";
			getSqlModel().singleExecute(updAmt,finalSalaryObject);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			/*if(result){
				try {
					for(int z = 0;z<finalObj.length;z++){
						String totDebit = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_DEBITS_"+upload.getYear()+" WHERE EMP_ID ="+String.valueOf(finalObj[z][1])+" " +
						"AND SAL_LEDGER_CODE IN ("+ledgerCode+") ";
						Object[][] totDebitObj = getSqlModel().getSingleResult(totDebit);
 
						String totCredit = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_CREDITS_"+upload.getYear()+" WHERE EMP_ID ="+String.valueOf(finalObj[z][1])+" " +
						"AND SAL_LEDGER_CODE IN ("+ledgerCode+") ";
						Object[][] totCreditObj = getSqlModel().getSingleResult(totCredit);
						logger.info("credit query "+totCredit);
						 if(totCreditObj[0][0]==null || totCreditObj[0][0].equals("null") || totCreditObj[0][0].equals("")){
							 totCreditObj = new Object[1][1];
							 totCreditObj[0][0] = "0";
						 }
						 if(totDebitObj[0][0]==null ||totDebitObj[0][0].equals("null") || totDebitObj[0][0].equals("")){
							 totDebitObj = new Object[1][1];
							 totDebitObj[0][0] = "0";
						 }
						 
						 updateObj[z][0] = Double.parseDouble(String.valueOf(totCreditObj[0][0]));
						 updateObj[z][1] = Double.parseDouble(String.valueOf(totCreditObj[0][0])) - Double.parseDouble(String.valueOf(totDebitObj[0][0]));
						 updateObj[z][2] = String.valueOf(finalObj[z][1]);
						
					}
					String updAmt = "UPDATE HRMS_SALARY_"+upload.getYear()+" SET SAL_TOTAL_CREDIT =?, SAL_NET_SALARY = ?"
					+" where SAL_LEDGER_CODE IN ("+ledgerCode+") AND  EMP_ID=?";
					getSqlModel().singleExecute(updAmt,updateObj);
				} catch (Exception e) {
				
				}
			}*/
		} catch (Exception e) {
				
			}
		return "2";
	}
	
	public HashMap getCreditMap(String ledgerCode,String year,String empIdString){
		try {
			Object[][] creditObject =null;
			HashMap  creditDataMap = new HashMap();
			int creCount=0;
				String creditQuery = "Select emp_id,sal_credit_code,sal_amount,CREDIT_APPLICABLE_ESI,CREDIT_APPLICABLE_PTAX" +
						" from hrms_sal_credits_"+year+" inner join    hrms_credit_head  on   hrms_credit_head.CREDIT_CODE = hrms_sal_credits_"+year+".SAL_CREDIT_CODE "+ 
									  " where sal_ledger_code in("+ledgerCode+") and emp_id in("+empIdString+")  order by emp_id,sal_credit_code";
				
				creditObject = getSqlModel().getSingleResult(creditQuery);
				
				String creditCount =	" select CREDIT_CODE from hrms_credit_head where CREDIT_PAYFLAG='Y' and  CREDIT_PERIODICITY='M' ORDER BY credit_code";
				
				Object[][] creditCountObj = getSqlModel().getSingleResult(creditCount);
				creCount= creditCountObj.length;
				
				if(creditObject !=null && creditObject .length>0){
					String empId = "";
					Object[][] empData = null;
					empData = new Object[1][4];
					for (int j = 0; j < creditObject.length;) {
						empData = new Object[creCount][4];
						empId = String.valueOf(creditObject[j][0]);
						int empCreCount=1;
						for (int i = 0; i < creCount; i++) {
							try{
							String tempEmpId = String.valueOf(creditObject[j][0]);
							
							if(tempEmpId.equals(empId)){
							try{
								empData[i][0] = creditObject[j][1];								
							}catch(Exception e){
								empData[i][0]="0";
							}
							try{
								empData[i][1] = creditObject[j][2];
							}catch(Exception e){
								empData[i][1]="0";
							}
							try{
								empData[i][2] = creditObject[j][3];
							}catch(Exception e){
								empData[i][2] ="0";
							}
							try{
								empData[i][3] = creditObject[j][4];
							}catch(Exception e){
								empData[i][3]="0";
							}
							j++;
							}else{
								empData[i][0]=creditCountObj[empCreCount][0];
								empData[i][1]="0";
								empData[i][2]="0";
								empData[i][3]="0"; 
							}
							
							}catch (Exception e) {
								empData[i][0]=creditCountObj[i][0];
								empData[i][1]="0";
								empData[i][2]="0";
								empData[i][3]="0"; 
							}
							empCreCount++;
						}
							
							creditDataMap.put(empId, empData);
					} // end of loop
					
				}
			return creditDataMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exception in getCreditMap", e);
			return null;
		}
	}
	
	public HashMap getCreditConfMap(String empIdString){
		try {
			Object[][] creditObject =null;
			HashMap  creditDataMap = new HashMap();
			int creCount=0;
					String selectCredits = "SELECT nvl(emp_id,0),HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N') " +
							" FROM HRMS_CREDIT_HEAD inner JOIN HRMS_EMP_CREDIT ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID in("
							+ empIdString + "  ) where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' order BY emp_id,HRMS_CREDIT_HEAD.CREDIT_CODE";
					creditObject = getSqlModel().getSingleResult(selectCredits);
				
				String creditHead =	" select CREDIT_CODE from hrms_credit_head where CREDIT_PAYFLAG='Y' and  CREDIT_PERIODICITY='M'";
				
				Object[][] creditHeadObj = getSqlModel().getSingleResult(creditHead);
				creCount= Integer.parseInt(String.valueOf(creditHeadObj.length));
				
				if(creditObject !=null && creditObject .length>0){
					String empId = "";
					Object[][] empData = null;
					empData = new Object[1][2];
					for (int j = 0; j < creditObject.length;) {
						empData = new Object[creCount][4];
						empId = String.valueOf(creditObject[j][0]);
						
						int empCreCount = 0;
						for (int i = 0; i < creCount; i++) {
							try{
							String tempEmpId = String.valueOf(creditObject[j][0]);
							
							
							if(tempEmpId.equals(empId)){
							try{
								empData[i][0] = creditObject[j][1];								
							}catch(Exception e){
								empData[i][0]="0";
							}
							try{
								empData[i][1] = creditObject[j][2];
							}catch(Exception e){
								empData[i][1]="0";
							}
							try{
								empData[i][2] = creditObject[j][3];
							}catch(Exception e){
								empData[i][2] ="0";
							}
							try{
								empData[i][3] = creditObject[j][4];
							}catch(Exception e){
								empData[i][3]="0";
							}
							j++;
							}else{
								empData[i][0]=creditHeadObj[empCreCount][0];
								empData[i][1]="0";
								empData[i][2]="0";
								empData[i][3]="0"; 
							}
							empCreCount++;
							}catch (Exception e) {
								empData[i][0]=creditHeadObj[empCreCount][0];
								empData[i][1]="0";
								empData[i][2]="0";
								empData[i][3]="0"; 
								empCreCount++;
							}
						}
							
							creditDataMap.put(empId, empData);
					} // end of loop
					
				}
			return creditDataMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exception in getCreditMap", e);
			return null;
		}
	}
	
	public double getPFAmount(String EPFFlag,String month, String year,Object[][] credit_amount,
							   String path,String typeId,String branchId, UploadCredit upload){
		double pf_amount=0,result=0;
		Object[][] pf_data=null;
		NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
		
		if(EPFFlag.equals("Y")){
			/**
			 * this method is used to retrieve latest record present in the
			 * PF parameter table for calculating PF percentage and the retrieved object
			 * will be set into pf_data[][] for further access. The data will be read from XML inside the method 
			 */ 
			 try {
				pf_data = nonModel.getPFData(path,month,year);//nonIndustrialSalary.getPfBeanData();
				
			/** this method (getTypeBraChk) is used to retrieve  PTax zone,PF Zone,ESI zone records present in the
			 *  EMP TYPE master table and also in branch master table for checking whether PTAX,
			 *  PF and ESI is applicable or not for particular employee type and branch. 
			 */ 
				if(nonModel.getTypeBraChk(typeId, branchId, 3,path)){
						if(pf_data==null){
							}
						else if(pf_data.length<=0){
							}
						else{
							upload.setPfCode(String.valueOf(pf_data[0][0]));
							result=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(pf_data[0][1]), context,session));
							try{
								/* This method will check minimum amount condition 
								 * is on or off 4 particular employee type 4 PF deduction
								 * if YES then check minimum amount is less than or equal to result(BASIC+DA)
								 * 		if MinAmt >= result then PF=0
								 * 		else calculate PF amount as usual
								 * else
								 * 		calculate  PF amount as usual
								 * */
								if(nonModel.getTypeMinAmtChk(typeId,4,path)){
									
									if(!String.valueOf(pf_data[0][5]).trim().equals("0")){
										
										if(String.valueOf(pf_data[0][5]).trim().equals("1")												
											&&  result == Double.parseDouble(String.valueOf(pf_data[0][4]))){
												
												pf_amount = nonModel.getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
												
											}
										if(String.valueOf(pf_data[0][5]).trim().equals("2")												
													&&  result < Double.parseDouble(String.valueOf(pf_data[0][4]))){
												
												pf_amount = nonModel.getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
												
											}
										if(String.valueOf(pf_data[0][5]).trim().equals("3")												
													&&  result > Double.parseDouble(String.valueOf(pf_data[0][4]))){
												
												pf_amount = nonModel.getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
												
											}
										if(String.valueOf(pf_data[0][5]).trim().equals("4")												
													&&  result <= Double.parseDouble(String.valueOf(pf_data[0][4]))){
												
												pf_amount = nonModel.getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
												
											}
										if(String.valueOf(pf_data[0][5]).trim().equals("5")												
													&&  result >= Double.parseDouble(String.valueOf(pf_data[0][4]))){
												
												pf_amount = nonModel.getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
												
											}
										}else
											pf_amount = Math.round((result  * Double.parseDouble(String.valueOf(pf_data[0][2])))/100);
																	
								}else
									pf_amount = Math.round((result  * Double.parseDouble(String.valueOf(pf_data[0][2])))/100);
							}catch(Exception e){
								logger.error(e.getMessage());
								pf_amount=0;
							}
							
						}
					} // end of main if loop
			 	} catch (Exception e) {
					logger.error(e.getMessage());
				}
			 	
			}
		return pf_amount;
	}
	
	public double getESIAmount(String path,String month, String year,
								Object[][] credit_amount,Object[][] grossCredit,String typeId,String branchId,String emp_id,UploadCredit upload){
			double  totalESICreditAmount=0,esi_amount=0,totalESICGross=0;
			Object[][] esi_data=null;
			NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
			nonModel.initiate(context, session);
			if (credit_amount != null) {
				for (int i = 0; i < credit_amount.length; i++) {
					try {
						if(String.valueOf(credit_amount[i][2]).trim().equals("Y")){
							totalESICreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));
						}
					} catch (Exception e) {
					}						
				}
			}
			logger.info("totalESICreditAmount=="+totalESICreditAmount);
			if(grossCredit!=null)
			{
					for (int i = 0; i < grossCredit.length; i++) {
						try {
							if(String.valueOf(grossCredit[i][2]).trim().equals("Y")){
								totalESICGross += Double.parseDouble(String.valueOf(grossCredit[i][1]));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}
						
					}
				}
			logger.info("totalESICGross=="+totalESICGross);
		try {
			esi_data = nonModel.getESIData(path,month,year);//nonIndustrialSalary.getEsiBeanData();
			
			String comLedgerCode = nonModel.prevLedger(month,year,esi_data);
			if(nonModel.getTypeBraChk(typeId, branchId, 1,path)){
			String previousEsic="";
			if(esi_data==null && esi_data.length<=0){
				
			}
			else{
				upload.setEsiCode(String.valueOf(esi_data[0][0]));
				if(totalESICGross <= Integer.parseInt(String.valueOf(esi_data[0][4]))){
					esi_amount = Math.ceil(totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
				}
				//If ESI start month and end month is equal then straight away esi will be calculated.
				else if(month.equals(String.valueOf(esi_data[0][5])) || month.equals(String.valueOf(esi_data[0][6]))){
					if(totalESICGross <= Integer.parseInt(String.valueOf(esi_data[0][4]))){
					//esiResult=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(esi_data[0][1]), context,session));
					esi_amount = Math.ceil(totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
					}
				}
				else if(totalESICGross >= Integer.parseInt(String.valueOf(esi_data[0][4]))){
					/**
					 *if not, system will check whether esi deducted on the specified month or not
					 * this method returns status of esi whether to deduct or not. It it returns
					 * "NP" means salary has not been processed for specified esi cutoff months, 
					 * then condition will remain same depend on gross esi will be deducted. 
					 * If it returns "CE" means esi deducted on cut off months hence esi will be deducted irrespective of gorss 
					 */
					logger.info("comLedgerCode"+comLedgerCode);
					if(comLedgerCode.equals("spilt")){
						previousEsic = nonModel.getPreESICSpilt(month,year,emp_id,esi_data,comLedgerCode);
					}
					else
						previousEsic = nonModel.getPreESIC(month,year,emp_id,esi_data,comLedgerCode);
					if(previousEsic.equals("true")){
								esi_amount = Math.ceil(totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
						}
					
				}
				nonModel.terminate();
			}
		}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return esi_amount;
	}
	
	public double getPTAXAmount(Object[][] credit_amount,String path,
								String month,String year,String typeId, String branchId,String location,UploadCredit upload){
		float totalPTAXCreditAmount = 0;
		double ptax_amount=0;
		NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
		Object[][] ptax_data=null;
		try{
			if (credit_amount != null) {
				for (int i = 0; i < credit_amount.length; i++) {
					try {
						if(String.valueOf(credit_amount[i][3]).trim().equals("Y")){
							totalPTAXCreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));
						}
						
					} catch (Exception e) {
						
					}						
				}
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		try{
			
			
				//logger.info("ptax gross---------"+totalPTAXCreditAmount);
				ptax_data = nonModel.getPtaxAmount(path,month,year,location,totalPTAXCreditAmount);//nonIndustrialSalary.getPtaxLoc();
				upload.setPtaxCode(String.valueOf(ptax_data[0][7]));				
				if(nonModel.getTypeBraChk(typeId, branchId, 2,path)){
					if(location.equals("")||location.equals("null"))
					{
						ptax_amount=0;
					}
					else
					{
					try {
						ptax_amount = nonModel.getEmpPtax(month,ptax_data);
					} catch (Exception e) {
						logger.error("exception in ptax setting",e);
						}
					} // end of else statement
				} // end of if statement	
			
				
		}
		catch(Exception e){
			logger.error("exception in ptax calculation",e);
		}
		
		return ptax_amount;
	}
	
}


