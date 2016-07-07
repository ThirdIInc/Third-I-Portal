/**
 * 
 */
package org.paradyne.model.payroll.pension;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeavePolicyModel;

/**
 * @author aa0418 Prakash
 * Date 23-09-2009
 *
 */
public class PensionCalculationModel extends ModelBase {
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PensionCalculationModel.class);

	public void calculatePension(Object [][]empObj){
		
		//Object [][]empObj = new Object[3][5];
		
		/*empObj[0][0]= "1212316";//empId
		empObj[0][1]= "12-08-2009";//retirementDate
		empObj[0][2]= "1";//pensionType
		empObj[0][3]= "55";//yearOfService
		empObj[0][4]= "39";//divCode
		
		empObj[1][0]= "577";
		empObj[1][1]= "12-05-2009";
		empObj[1][2]= "2";
		empObj[1][3]= "65";
		empObj[1][4]= "2";
		
		empObj[2][0]= "1212316";
		empObj[2][1]= "12-02-2009";
		empObj[2][2]= "3";
		empObj[2][3]= "75";
		empObj[2][4]= "2";*/
		
		try{	
			
			if (empObj != null && empObj.length > 0) {
				Object [][] pensionHeads = getPensionHeads();
				String emolMonths="";
				String maxQualiYearSer = "";
				String emolFormula="";
				String penMinAmt="";
				
				String basicHead="0";
				String basicPerc="0";
				String dpHead="0";
				String dpPerc="0";
				String daHead="0";
				String daPern="0";
				double wtgYear = 0.0;
				
				Object [][] formulaObj = getEmolFormula();
				
				Object [][] splitObj = getPensionSplitUp();
				
				for(int i=0; i< empObj.length;i++ ){
							
					if (formulaObj != null && formulaObj.length > 0) {
						
						if (splitObj != null && splitObj.length > 0) {
							
							basicHead=String.valueOf(splitObj[0][0]);
							basicPerc=String.valueOf(splitObj[0][1]);
							dpHead=String.valueOf(splitObj[0][2]);
							dpPerc=String.valueOf(splitObj[0][3]);
							daHead=String.valueOf(splitObj[0][4]);
							daPern=String.valueOf(splitObj[0][5]);
							
						}
												
						for(int f=0;f<formulaObj.length;f++){
							
							if(String.valueOf(formulaObj[f][0]).equals(String.valueOf(empObj[i][2]))){
														
								emolFormula = String.valueOf(formulaObj[f][1]);
								maxQualiYearSer = String.valueOf(formulaObj[f][2]);
								emolMonths = String.valueOf(formulaObj[f][3]);
								penMinAmt = String.valueOf(formulaObj[f][4]);
								wtgYear = Double.parseDouble( String.valueOf(formulaObj[f][5]));
								
								break;
							}
								
						}
						
					String currentDate = String.valueOf(empObj[i][1]);
					
					String previousQuery = "SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+currentDate+"','DD-MM-YYYY'),- "+emolMonths+"),'DD-MM-YYYY') FROM DUAL ";

					Object [][] previousObj = getSqlModel().getSingleResult(previousQuery);
					
					String previousDate = String.valueOf(previousObj[0][0]);
					
					String leaveCount ="0";
					
					try {
						leaveCount = getLeaveCount(String.valueOf(empObj[i][0]), previousDate,
								currentDate);
					} catch (Exception e) {
						leaveCount = "0";
						logger.error("Exception in calculating leave count"+e);
					}
					if (!leaveCount.equals("0")) {
						
						String tempQuery  = " SELECT TO_DATE('"+previousDate+"','DD-MM-YYYY') - "+leaveCount+" FROM DUAL ";
						
						Object [][] tempObj = getSqlModel().getSingleResult(tempQuery);  
						
						previousDate = String.valueOf(tempObj[0][0]);
												
					}
									
						String  creditCodes = Utility.getFormulaCreditHeads(emolFormula);

						Object salData [][] = getEmpSalary(previousDate,  String.valueOf(empObj[i][0]),  String.valueOf(empObj[i][4]), creditCodes, currentDate);
						
						Object arrearData [][] = getEmpArrear(previousDate, String.valueOf(empObj[i][0]), String.valueOf(empObj[i][4]), creditCodes, currentDate);
						
						if (salData != null && salData.length > 0) {
							
							if (arrearData != null && arrearData.length > 0) {
								
								for (int l = 0; l < salData.length; l++) {
									
									for (int k = 0; k < arrearData.length; k++) {
														
										if(String.valueOf(salData[l][0]).equals(String.valueOf(arrearData[k][0]))){
											try{
													
												salData[l][1] = Double.parseDouble((String.valueOf(salData[l][1]))) +
																Double.parseDouble(checkNull(String.valueOf(arrearData[k][1])));
												
											}catch(Exception e){
												logger.error("Exception in adding the credit heads  of salary and arrear"+e);
												e.printStackTrace();
											}
											break;
										}
									}
									
								}
								
							}
						}
						
						boolean result = false;
						double avgEmoluments = 0.0;
						double creditEmoluments = 0.0;
						String yearOfSer ="";
						Object [][] penObject = new Object[1][8];
						Object [][] creditObject = null;
						double newPensionAmt=0;
						if (salData != null && salData.length > 0) {
												
								creditObject =  new Object[pensionHeads.length][3];
							
								yearOfSer = String.valueOf(Double.parseDouble(String.valueOf(empObj[i][3])) + wtgYear);
								
								/*creditObject =  new Object[salData.length+1][3];
								
									for(int k=0;k<salData.length;k++){
										
										creditEmoluments = Double.parseDouble(String.valueOf(salData[k][1])) / Double.parseDouble(emolMonths);
																		
										creditObject[k][0] = String.valueOf(empObj[i][0]); 
										creditObject[k][1] = String.valueOf(salData[k][0]); 
										creditObject[k][2] = getPensionAmt(String.valueOf(creditEmoluments), yearOfSer, maxQualiYearSer); 
										
										logger.info("Salary head ------------- "+salData[k][0]);
										logger.info("avg Salary amt ------------- "+creditEmoluments);
										logger.info("year of service ------------- "+yearOfSer);
										logger.info("maxQualiYearSer ------------- "+maxQualiYearSer);
										logger.info("pension head amt ------------- "+creditObject[k][2]);
														
									}*/
								try{
									avgEmoluments = Math.round(Utility.expressionEvaluate(new Utility().generateFormulaPay(salData,emolFormula,context,session)) / Double.parseDouble(emolMonths));
								}catch (Exception e) {
									logger.info("Error while calculating avgEmoluments");
									avgEmoluments =0;
								}										
									penObject[0][0] = String.valueOf(empObj[i][0]);
									penObject[0][1] = String.valueOf(empObj[i][1]);
									penObject[0][2] = String.valueOf(empObj[i][2]);
									penObject[0][3] = String.valueOf(empObj[i][3]);
									
									double penAmt = Double.parseDouble(getPensionAmt(String.valueOf(avgEmoluments),yearOfSer, maxQualiYearSer));
									
									if(penAmt < Double.parseDouble(penMinAmt))
										penObject[0][4] = penMinAmt;
									else
										penObject[0][4] = Math.ceil(penAmt); 
									
									penObject[0][5] = "U";
									penObject[0][6] = formatter.format(avgEmoluments);
															
									logger.info("avgEmoluments ------------- "+avgEmoluments);
									logger.info("final pension amt ------------- "+penObject[0][4]);
									
									
									/*
									 * Hardcoded credits commented by mangesh
									 * creditObject[0][0] = String.valueOf(empObj[i][0]); 
									creditObject[0][1] = basicHead; 
									creditObject[0][2] = Math.round(Double.parseDouble(String.valueOf(penObject[0][4]))*Double.parseDouble((basicPerc))/100); 
									
									creditObject[1][0] = String.valueOf(empObj[i][0]); 
									creditObject[1][1] = dpHead; 
									creditObject[1][2] = Math.round(Double.parseDouble(String.valueOf(creditObject[0][2]))* Double.parseDouble((dpPerc))/100); 
									
									creditObject[2][0] = String.valueOf(empObj[i][0]); 
									creditObject[2][1] = daHead; 
									creditObject[2][2] = Math.round(Double.parseDouble(String.valueOf(penObject[0][4]))*Double.parseDouble((daPern))/100); 
									
									
									creditObject[3][0] = String.valueOf(empObj[i][0]); 
									creditObject[3][1] = "0"; 
									creditObject[3][2] = "0"; */
									
									for (int j = 0; j < creditObject.length; j++) {
										creditObject[j][0] = String.valueOf(empObj[i][0]); 	//empID
										creditObject[j][1] = pensionHeads[j][0]; //credit code
										if(String.valueOf(pensionHeads[j][1]).equals("F")){		// if credit type is fixed
											creditObject[j][2]=pensionHeads[j][2];				// fixed value
											newPensionAmt +=Double.parseDouble(String.valueOf(pensionHeads[j][2]));
										}else{
											creditObject[j][2] = Math.round(Double.parseDouble(String.valueOf(penObject[0][4]))*Double.parseDouble((String.valueOf(pensionHeads[j][2])))/100);
											newPensionAmt +=Double.parseDouble(String.valueOf(creditObject[j][2]));
										}
									}
									
									
									
									penObject[0][7] = Double.parseDouble(String.valueOf(creditObject[0][2])) + Double.parseDouble(String.valueOf(creditObject[1][2])) +
											Double.parseDouble(String.valueOf(creditObject[2][2]));
									logger.info("basicHead ------------- "+basicHead);
									logger.info("basicHead amt ------------- "+creditObject[0][2]);
									logger.info("dpHead ------------- "+dpHead);
									logger.info("dpHead amt ------------- "+creditObject[1][2]);
									logger.info("daHead ------------- "+dpHead);
									logger.info("daHead amt ------------- "+creditObject[2][2]);
																	
							}else{
									logger.info("Salary not availabe for employee id "+empObj[i][0]);
								
									penObject[0][0] = String.valueOf(empObj[i][0]);
									penObject[0][1] = String.valueOf(empObj[i][1]);
									penObject[0][2] = String.valueOf(empObj[i][2]);
									penObject[0][3] = String.valueOf(empObj[i][3]);
									penObject[0][4] = "0";
									penObject[0][5] = "U";
									penObject[0][6] = "0";
									penObject[0][7] = "0";
									/*
									 * Hardcoded credits commented by mangesh
									 * 
									 * creditObject =  new Object[4][3];
									
									creditObject[0][0] = String.valueOf(empObj[i][0]); 
									creditObject[0][1] = basicHead; 
									creditObject[0][2] = "0"; 
									
									creditObject[1][0] = String.valueOf(empObj[i][0]); 
									creditObject[1][1] = dpHead; 
									creditObject[1][2] = "0";
									
									creditObject[2][0] = String.valueOf(empObj[i][0]); 
									creditObject[2][1] = daHead; 
									creditObject[2][2] = "0"; 
									
									creditObject[3][0] = String.valueOf(empObj[i][0]); 
									creditObject[3][1] = "16"; 
									creditObject[3][2] = "0"; */
									
									creditObject = new Object[pensionHeads.length][3];
									for (int j = 0; j < creditObject.length; j++) {
										creditObject[j][0] = String.valueOf(empObj[i][0]); 	//empID
										creditObject[j][1] = pensionHeads[j][0]; //credit code
										if(String.valueOf(pensionHeads[j][1]).equals("F")){		// if credit type is fixed
											creditObject[j][2]=pensionHeads[j][2];				// fixed value
											newPensionAmt +=Double.parseDouble(String.valueOf(pensionHeads[j][2]));
										}else{
											creditObject[j][2] = "0";
										}
									}
									
								
							}
								if (penObject != null && penObject.length > 0) {
									penObject [0][7] = newPensionAmt;
									try{
										String insertQuery = "INSERT INTO HRMS_PENSION_EMPLOYEES (PENS_EMP_ID,PENS_RETIRE_DATE,PENS_PENSION_TYPE,PENS_QUAL_SERVICE,"
															+" PENS_AMOUNT,PENS_CAL_STATUS,PENS_AVGEMOL,PENS_GROSS_AMOUNT) VALUES(?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?)";
										
										result = getSqlModel().singleExecute(insertQuery, penObject);
										
										if(result)
											logger.info("employee pension saved successfully");
										else
											logger.info("Error while saving employee pension");
																											
									}catch(Exception e){
										logger.error("Exception while inserting the employee pension records "+e);
									}
								}	
								if (creditObject != null && creditObject.length > 0){
									try{
										String insertQuery = "INSERT INTO HRMS_PENSION_EMPCREDIT (PENS_EMP_ID,PENS_CREDIT_CODE,PENS_AMOUNT) VALUES(?,?,?)";
										
										result = getSqlModel().singleExecute(insertQuery, creditObject);
										
										if(result)
											logger.info("pension credit saved successfully");
										else
											logger.info("Error while saving pension credit");
										
																		
									}catch(Exception e){
										logger.error("Exception while inserting the pension credit records "+e);
									}
								}
						}else
						logger.info("Pension fprmula not availabe for employee id "+empObj[i][0]);
				}
			}else
				logger.info("Employee object is null.");
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error in calculatePension  "+e);
		}
		//return pensionObj;
	}
	public Object[][] getEmolFormula(){
		
		String formula = " SELECT PENS_TYPE_CODE,PENS_EMOL_FORMULA,PENS_MAX_SERVICE,PENS_EMOL_MONTHS,PENS_MIN_AMOUNT,NVL(PENS_VOL_WEIYEARS,0) " 
						+" FROM HRMS_PENSION_CONF " 
						+" WHERE TO_DATE(PENS_EFECTIVE_FROM,'DD-MM-YYYY') <= TO_DATE(SYSDATE,'DD-MM-YYYY') "
						+" GROUP BY PENS_TYPE_CODE,PENS_EMOL_FORMULA,PENS_MAX_SERVICE,PENS_EMOL_MONTHS,PENS_MIN_AMOUNT,NVL(PENS_VOL_WEIYEARS,0) ";
		
		Object [][] formulaObj = getSqlModel().getSingleResult(formula);
		
		return formulaObj;
	}
	public Object [][] getPensionSplitUp(){
			
		String query = " SELECT NVL(PENS_BASICHEAD,0),NVL(PENS_BASIC_PERN,0),NVL(PENS_DPHEAD,0),NVL(PENS_DP_PERN,0),NVL(PENS_DAHEAD,0),NVL(PENS_DA_PERN,0) FROM HRMS_PENSION_AMTSPLIT ";
		
		Object [][] settingObj = getSqlModel().getSingleResult(query);
		
		return settingObj;
	}
	public String getPensionAmt(String amount,String serYear,String maxSerYear){
		double pensionAmt =0.0;
		try{
			if(Double.parseDouble(serYear) > Double.parseDouble(maxSerYear))
				serYear = maxSerYear;
				
			pensionAmt = ((Double.parseDouble(amount) * 0.50 ) * (Double.parseDouble(serYear)/Double.parseDouble(maxSerYear)));
			
			//logger.info("avg emoluments------------- "+amount);
			//logger.info("service years ------------- "+serYear);
			//logger.info("maxSerYear ------------- "+maxSerYear);
			//logger.info("pensionAmt ------------- "+pensionAmt);
						
		}catch(Exception e){
			logger.error("Exception in getPensionAmt  "+e);
			pensionAmt =0.0;
		}
		
		return formatter.format(pensionAmt);
	}
	public Object [][] getEmpSalary(String previousDate,String empCode,String divCode,String creditCodes,String currentDate){
		
		Object [][] salData = null;
		try{
				ArrayList salList = new ArrayList();
			
				int count =0;
				
				Object [][] firstObj = getSalary(previousDate, empCode , divCode, creditCodes,true,"F");
							
				String nextDate = genNextMonth(previousDate);
			
				
				if (firstObj != null && firstObj.length > 0){
					salList.add(count,firstObj);
					count++;
				}
				boolean check = false;
			
				do{
					
					Object tempObj [][] = getSalary(nextDate, empCode, divCode, creditCodes,false,"");
					
					if (tempObj != null && tempObj.length > 0){
						salList.add(count,tempObj);
						count++;
					}
					
					nextDate = genNextMonth(nextDate);
					
					check = checkDate(nextDate,currentDate);
					
					
				}while(check);
			
				Object [][] lastObj = getSalary(currentDate, empCode, divCode, creditCodes,true,"L");
			
				if (lastObj != null && lastObj.length > 0){
					salList.add(count,lastObj);
					count++;
				}
					
				for(int j=0;j<salList.size();j++){
					
					if(j==0){								
						
						salData = (Object [][])salList.get(j);
						
					}else{
						
						Object [][] tempObj = (Object [][])salList.get(j);
						
						if (salData != null && salData.length > 0){
							
							if (tempObj != null && tempObj.length > 0){
								
								for (int l = 0; l < salData.length; l++) {
									
									for (int k = 0; k < tempObj.length; k++) {
														
										if(String.valueOf(salData[l][0]).equals(String.valueOf(tempObj[k][0]))){
											try{
													
												salData[l][1] = Double.parseDouble((String.valueOf(salData[l][1]))) +
																Double.parseDouble(checkNull(String.valueOf(tempObj[k][1])));
												
											}catch(Exception e){
												logger.error("Exception in adding the credit heads  in getEmpSalary"+e);
												e.printStackTrace();
											}
											break;
										}
									}
									
								}
							}
							
						}
						
					}
				}
		}catch(Exception e){
			logger.error("Exception in getEmpSalary  "+e);
		}
		return salData;
		
	}
	public Object [][] getEmpArrear(String previousDate,String empCode,String divCode,String creditCodes,String currentDate){
		Object [][] arrearObj = null;
		try{
			String getLedger = ledgerString(previousDate,currentDate,divCode);
			
			logger.info("getLedgerString=--------------"+getLedger);
			ArrayList salList = new ArrayList();
			
			int count =0;
			Object [][] firstObj = getArrear(previousDate, empCode , divCode, creditCodes,true,"F",getLedger);
		
			String nextDate = genNextMonth(previousDate);
		
			if (firstObj != null && firstObj.length > 0){
				salList.add(count,firstObj);
				count++;
			}
			boolean check = false;
			
			do{
				
				Object tempObj [][] = getArrear(nextDate, empCode, divCode, creditCodes,false,"",getLedger);
				
				if (tempObj != null && tempObj.length > 0){
					salList.add(count,tempObj);
					count++;
				}
				
				nextDate = genNextMonth(nextDate);
				
				check = checkDate(nextDate,currentDate);
				
				
			}while(check);
		
			Object [][] lastObj = getArrear(currentDate, empCode, divCode, creditCodes,true,"L",getLedger);
			
			if (lastObj != null && lastObj.length > 0){
				salList.add(count,lastObj);
				count++;
			}
			
			for(int j=0;j<salList.size();j++){
				
				if(j==0){								
					
					arrearObj = (Object [][])salList.get(j);
					
				}else{
					
					Object [][] tempObj = (Object [][])salList.get(j);
					
					if (arrearObj != null && arrearObj.length > 0){
						
						if (tempObj != null && tempObj.length > 0){
							
							for (int l = 0; l < arrearObj.length; l++) {
								
								for (int k = 0; k < tempObj.length; k++) {
													
									if(String.valueOf(arrearObj[l][0]).equals(String.valueOf(tempObj[k][0]))){
										try{
												
											arrearObj[l][1] = Double.parseDouble((String.valueOf(arrearObj[l][1]))) +
															Double.parseDouble(checkNull(String.valueOf(tempObj[k][1])));
											
										}catch(Exception e){
											logger.error("Exception in adding the credit heads in getEmpArrear "+e);
											e.printStackTrace();
										}
										
										break;
									}
								}
								
							}
						}
						
					}
					
				}
			}
			
			
		}catch(Exception e){
			logger.error("Exception in getEmpArrear  "+e);
			e.printStackTrace();
		}
		return arrearObj;
	}
	public Object [][] getSalary(String date,String empCode,String divCode,String creditCodes,boolean daysFlag,String chkString ){
		Object [][] salObj = null;
		try{
			Object dateData [] = date.split("-");
						
			String month = String.valueOf(dateData[1]);
			String year = String.valueOf(dateData[2]);
			
			String ledgerQuery = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "+month
							+" AND LEDGER_YEAR = "+year+" AND LEDGER_DIVISION = "+divCode+" AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL') ";
			
			Object [][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
			String 	ledgerCode="";		
			
			if (ledgerObj != null && ledgerObj.length > 0) {
				
				for (int i = 0; i < ledgerObj.length; i++) {
					ledgerCode += String.valueOf(ledgerObj[i][0]) + ",";
				}
				
				ledgerCode = ledgerCode.substring(0,ledgerCode.length() - 1);
				
				String creditQuery = "";
				int actualDays = 0;
				if(daysFlag){
									
					String days = String.valueOf(dateData[0]);
									
					int monthDays = getMonthDays(month, year);
					//actualDays = monthDays - Integer.parseInt(days);
					if(chkString.equals("F"))
						actualDays = monthDays - Integer.parseInt(days) + 1; 
					if(chkString.equals("L"))
						actualDays = Integer.parseInt(days); 
					
					creditQuery = " SELECT SAL_CREDIT_CODE,ROUND(((SAL_AMOUNT * "+actualDays+")/"+monthDays+")) FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") "
									+" AND EMP_ID = "+empCode+" AND SAL_CREDIT_CODE IN ( "+creditCodes+" ) ORDER BY SAL_CREDIT_CODE";
					
				}else{
					
					creditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") "
									+" AND EMP_ID = "+empCode+" AND SAL_CREDIT_CODE IN ( "+creditCodes+" ) ORDER BY SAL_CREDIT_CODE";
				}
				salObj = getSqlModel().getSingleResult(creditQuery); 
			}
					
			}catch(Exception e){
				logger.error("Exception in getSalary  "+e);
			}
		return salObj;
	}
	public Object [][] getArrear1(String date,String empCode,String divCode,String creditCodes,boolean daysFlag,String chkString ){
		Object [][] salObj = null;
		try{
			Object dateData [] = date.split("-");
			
			String month = String.valueOf(dateData[1]);
			String year = String.valueOf(dateData[2]);
			
			String ledgerQuery = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH = "+month
								+" AND ARREARS_REF_YEAR = "+year+" AND ARREARS_DIVISION = "+divCode;
			
			Object [][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
			String 	ledgerCode="";		
			
			if (ledgerObj != null && ledgerObj.length > 0) {
				
				for (int i = 0; i < ledgerObj.length; i++) {
					ledgerCode += String.valueOf(ledgerObj[i][0]) + ",";
				}
				
				ledgerCode = ledgerCode.substring(0,ledgerCode.length() - 1);
				
				String creditQuery = "";
				int actualDays = 0;
				
				if(daysFlag){
					
					String days = String.valueOf(dateData[0]);
					
					int monthDays = getMonthDays(month, year);
					
					if(chkString.equals("F"))
						actualDays = monthDays - Integer.parseInt(days) + 1; 
					if(chkString.equals("L"))
						actualDays = Integer.parseInt(days); 
															
					creditQuery = " SELECT ARREARS_CREDIT_CODE,ROUND(((ARREARS_AMT * "+actualDays+" )/ "+monthDays+")) FROM HRMS_ARREARS_CREDIT_"+year+" WHERE ARREARS_CODE IN ("+ledgerCode+") "
									+" AND ARREARS_EMP_ID = "+empCode+" AND ARREARS_CREDIT_CODE IN ( "+creditCodes+" ) ORDER BY ARREARS_CREDIT_CODE ";

				}else{
					
					creditQuery = " SELECT ARREARS_CREDIT_CODE,ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"+year+" WHERE ARREARS_CODE IN ("+ledgerCode+") "
									+" AND ARREARS_EMP_ID = "+empCode+" AND ARREARS_CREDIT_CODE IN ( "+creditCodes+" ) ORDER BY ARREARS_CREDIT_CODE ";

				}
				
				salObj = getSqlModel().getSingleResult(creditQuery); 
			}
					
			}catch(Exception e){
				logger.error("Exception in getArrear  "+e);
			}
		return salObj;
	}
	public String genNextMonth (String date){
		
		Object [] dateData = date.split("-");
		
		dateData[0] = dateData[0];
		if(Integer.parseInt(String.valueOf(dateData[1]))== 12){
			
			dateData[1] = "1";
			dateData[2] = String.valueOf(Integer.parseInt(String.valueOf(dateData[2]))+1);
			
		}else{
			
			dateData[1] = String.valueOf(Integer.parseInt(String.valueOf(dateData[1]))+1);
			dateData[2] = String.valueOf(dateData[2]);
		}
		
		String nextDate="";
		
		if (dateData != null && dateData.length > 0) {
			
			for (int i = 0; i < dateData.length; i++) {
				nextDate += String.valueOf(dateData[i]) + "-";
			}
			
			nextDate = nextDate.substring(0,nextDate.length() - 1);
		}
		return nextDate;
	}
	/*public void getEmployeeList(PensionCalculation bean){
		
		try {
			String empQuery ="SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,DECODE(PENS_PENSION_TYPE,'1','Super Annuation', "
							+" '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0') FROM HRMS_PENSION_EMPLOYEES "
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID )"
							+" WHERE PENS_CAL_STATUS = 'U'";
			
			Object [][] empObj = getSqlModel().getSingleResult(empQuery);
			
			ArrayList empList = new ArrayList();
			
				if (empObj != null && empObj.length != 0) {
				
					for (int i = 0; i < empObj.length; i++) {
						
						PensionCalculation bean1 = new PensionCalculation();
						
						bean1.setEmpId(String.valueOf(empObj[i][0]));
						bean1.setEmpCode(String.valueOf(empObj[i][1]));
						bean1.setEmpName(String.valueOf(empObj[i][2]));
						bean1.setPensionType(String.valueOf(empObj[i][3]));
						bean1.setPensionAmt(String.valueOf(empObj[i][4]));
						
						empList.add(bean1);
						
					}
					bean.setEmpList(empList);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/*public boolean lockPension(Object [][] updateObject ){
		boolean result = false;
				
		String lockQuery = " UPDATE HRMS_PENSION_EMPLOYEES SET PENS_CAL_STATUS = 'L' WHERE PENS_EMP_ID = ?";
		
		result = getSqlModel().singleExecute(lockQuery, updateObject);
			
		return result;
	}*/
	public String getLeaveCount(String empCode,String previousDate,String currentDate){
		
		String leaveCount = "0";
		String policyCode = getLeavePolicyCode(empCode);
		Object[][] countObj = null;
		try {
			String sql = " SELECT NVL(SUM(NVL(LEAVE_DAYS, 0)), 0) UNPAID_LEVS FROM  HRMS_LEAVE_DTLHISTORY "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ") "
					+ " WHERE LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY_DTL.LEAVE_PAID_UNPAID IN ('U') "
					+ " AND EMP_ID="
					+ empCode
					+ " AND LEAVE_DTL_STATUS='A' AND LEAVE_FROM_DATE >= TO_DATE('"
					+ previousDate
					+ "', 'DD-MM-YYYY') "
					+ " AND LEAVE_TO_DATE <= TO_DATE( '"
					+ currentDate
					+ "', 'DD-MM-YYYY')";
			if (policyCode != null && policyCode != "")
				countObj = getSqlModel().getSingleResult(sql);
			if (countObj != null && countObj.length > 0)
				leaveCount = String.valueOf(countObj[0][0]);
		} catch (Exception e) {
			logger.error("Exception in calculating leave count"+e);
		}
		return leaveCount;
	}
	public String getLeavePolicyCode(String empId)
	{
		String leavePolicyCode = "";
		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getLeavePolicyCode  "+e);
		}
		return leavePolicyCode;
	}//end of getLeavePolicyCode
	public boolean checkDate(String fromDate,String toDate)
	{		
		boolean result = false;
		/*Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
		Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
			
		if(fromDateCalendar.before(toDateCalendar))
			result = true;*/
		
		Object [] fData = fromDate.split("-");
		Object [] tData = toDate.split("-");
		
		if(Integer.parseInt(String.valueOf(fData[1])) != Integer.parseInt(String.valueOf(tData[1])))
			result = true;
		else{
			if(Integer.parseInt(String.valueOf(fData[2])) != Integer.parseInt(String.valueOf(tData[2])))
				result = true;
		}
			
		return result;
	}
	public static String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "0";
		}
		else{
			return result;
		}
	}
	/**
	 * Get days of month
	 * @param month: Current Month
	 * @param year: Current Year
	 * @return int as days of month
	 */
	private int getMonthDays(String month, String year) {
		int monthDays = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + month + "-" + year));
			monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error("Exception in getMonthDays:" + e);
		}
		return monthDays;
	}
	public String ledgerString(String fromDate,String toDate,String divCode){
		
		String ledger="";
		Object tempObj [][] = null;
		
		tempObj  = arrearLedger(fromDate, divCode);
		
		if(tempObj != null && tempObj.length > 0)
			ledger += String.valueOf(tempObj[0][0])+",";
			
		String nextDate = genNextMonth(fromDate);
		
		boolean check = false;
		
		do{			
			tempObj = arrearLedger(nextDate, divCode);
			
			if (tempObj != null && tempObj.length > 0){
				ledger += String.valueOf(tempObj[0][0])+",";
			}
			
			nextDate = genNextMonth(nextDate);
			
			check = checkDate(nextDate,toDate);
			
			
		}while(check);
		
		tempObj  = arrearLedger(toDate, divCode);
		
		if(tempObj != null && tempObj.length > 0)
			ledger += String.valueOf(tempObj[0][0])+",";
		if(ledger != null && ledger != "")			
			ledger = ledger.substring(0,ledger.length() - 1);
			
		return ledger;
		
	}
	public Object[][] arrearLedger(String date1,String divCode){
		
		Object dateData [] = date1.split("-");

		String month = String.valueOf(dateData[1]);
		String year = String.valueOf(dateData[2]);
		
		String ledgerQuery = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH = "+month
							+" AND ARREARS_REF_YEAR = "+year+" AND ARREARS_DIVISION = "+divCode;

		Object [][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
				
		return ledgerObj;
	}
	public Object [][] getArrear(String date,String empCode,String divCode,String creditCodes,boolean daysFlag,String chkString,String ledger ){
		Object [][] salObj = null;
		try{
			Object dateData [] = date.split("-");
			
			String month = String.valueOf(dateData[1]);
			String year = String.valueOf(dateData[2]);
								
			if (ledger != null && ledger != "") {
							
				String creditQuery = "";
				int actualDays = 0;
				
				if(daysFlag){
					
					String days = String.valueOf(dateData[0]);
					
					int monthDays = getMonthDays(month, year);
					
					if(chkString.equals("F"))
						actualDays = monthDays - Integer.parseInt(days) + 1; 
					if(chkString.equals("L"))
						actualDays = Integer.parseInt(days); 
															
					creditQuery = " SELECT ARREARS_CREDIT_CODE,ROUND(sum((ARREARS_AMT * "+actualDays+" )/ "+monthDays+")) FROM HRMS_ARREARS_CREDIT_"+year
									+" WHERE ARREARS_MONTH = "+month+" and ARREARS_YEAR = "+year+" and ARREARS_EMP_ID = "+empCode+" and ARREARS_CREDIT_CODE IN ("+creditCodes+") "
									+" AND ARREARS_CODE in ("+ledger+") "
									+" GROUP BY ARREARS_CREDIT_CODE "
									+" ORDER BY ARREARS_CREDIT_CODE ";
				}else{
					
					creditQuery = " SELECT ARREARS_CREDIT_CODE,sum(ARREARS_AMT) FROM HRMS_ARREARS_CREDIT_"+year
									+" WHERE ARREARS_MONTH = "+month+" and ARREARS_YEAR = "+year+" and ARREARS_EMP_ID = "+empCode+" and ARREARS_CREDIT_CODE IN ("+creditCodes+") "
									+" AND ARREARS_CODE in ("+ledger+") "
									+" GROUP BY ARREARS_CREDIT_CODE "
									+" ORDER BY ARREARS_CREDIT_CODE ";
				}
				
				salObj = getSqlModel().getSingleResult(creditQuery); 
			}
					
			}catch(Exception e){
				logger.error("Exception in getArrear  "+e);
			}
		return salObj;
	}
	public Object[][] getPensionHeads(){
		
		String pensionQUery = " SELECT DISTINCT HRMS_PENSION_CREDIT_CONF.CREDIT_CODE,CREDIT_TYPE, CREDIT_VALUES FROM HRMS_PENSION_CREDIT_CONF "
							//+" INNER JOIN HRMS_CREDIT_HEAD on HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_CONF.CREDIT_CODE " 
							+" ORDER BY HRMS_PENSION_CREDIT_CONF.CREDIT_CODE ";
		
		Object [][] pensObj = getSqlModel().getSingleResult(pensionQUery);
						
		return pensObj;
	}
}
