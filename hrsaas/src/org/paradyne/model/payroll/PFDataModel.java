/**
 * 
 */
package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0418 Prakash Date:28-08-2009
 *
 */
public class PFDataModel extends ModelBase {
	
	NumberFormat formatter = new DecimalFormat("#0.00");

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PFDataModel.class);
	
	public void insertPFReportData(String month,String year,String division){
		
		try{			
			String parameterQuery = " SELECT PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DEBIT_CODE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES,PF_PERCENTAGE,PF_FORMULA"
									+ ",CASE WHEN PF_EMOL_NO_MAX_LIMIT_CHK='Y' THEN (NVL(PF_EMOL_MAX_LIMIT,0)) ELSE 0 END AS EMOLAMT FROM HRMS_PF_CONF "
									+ " WHERE TO_CHAR(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
									+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"+ year + "-" + month + "')";

			Object[][] paramObj = getSqlModel().getSingleResult(parameterQuery);
			
			if (paramObj != null && paramObj.length > 0) {
				
				//query to retrieve debit code for VPF - ADDED BY REEBA
				String VPFQuery = " SELECT NVL(VPF_DEBIT_CODE,0) FROM HRMS_VPF_CONF";
				Object[][] VPFObj = getSqlModel().getSingleResult(VPFQuery);
				//query to retrieve debit code for VPF - ADDED BY REEBA ENDS
				if(!(VPFObj!=null && VPFObj.length>0)){
					VPFObj =new Object[1][1];
					VPFObj[0][0]="0";
				}
				String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ " AND LEDGER_DIVISION IN ("
					+ division+")"
					+ " AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL')";

				Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
				
				String ledgerCode = "";
				logger.info("salary ledgerData.length---------" + ledgerData.length);

					if (ledgerData != null && ledgerData.length > 0) {
	
						for (int i = 0; i < ledgerData.length; i++) {
							ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
						}
						
						ledgerCode = ledgerCode.substring(0,ledgerCode.length() - 1);
						
						//UPDATED BY REEBA
						Object [][] salData = getSalary(month, year, ledgerCode, paramObj, VPFObj);
						
						if (salData != null && salData.length > 0) {
							try{
							
								String  salInsert = "INSERT INTO HRMS_PF_DATA (PF_MONTH,PF_YEAR,PF_EMP_ID,PF_EMP_BASIC,PF_PFEPF,PF_EMP_PF,"
													+" PF_EMP_F_PF,PF_CMP_PF,PF_CMP_F_PF,PF_EDLI,PF_TYPE,PF_TOTAL,PF_EMP_DIV,PF_EMP_BRN,PF_EMP_DEPT,PF_EMP_DESG,PF_EMP_PAYBILL,PF_EMP_TYPE,PF_EMP_ONHOLD,PF_VPF,PF_EMP_GRADE )"
													+" VALUES(?,?,?,?,?,?,?,?,?,?,'S',?,?,?,?,?,?,?,?,?,?)";
								
								boolean result = getSqlModel().singleExecute(salInsert, salData);
								
								if(result)
									logger.info("PF data 4 salary saved successfully");
								else
									logger.info("Error while saving PF data 4 salary");
															
							}catch(Exception e){
								logger.error("Exception while inserting the salary records in PF data");
							}
							
						}
						
					}
				
				String ledgerQry = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
						+ month
						+ " AND ARREARS_PAID_YEAR ="
						+ year
						+ " AND ARREARS_DIVISION IN ("
						+ division+")";
						//+" AND ARREARS_PAY_IN_SAL = 'Y' ";

					Object ledgerObj[][] = getSqlModel().getSingleResult(ledgerQry);
		
					String arrCode = "";
					logger.info("arrear ledgerData.length---------" + ledgerObj.length);
					if (ledgerObj != null && ledgerObj.length > 0) {
		
						for (int i = 0; i < ledgerObj.length; i++) {
							arrCode += String.valueOf(ledgerObj[i][0]) + ",";
						}
						arrCode = arrCode.substring(0, arrCode.length() - 1);
												
					}

					//UPDATED BY REEBA
					Object[][] arrearData = getArrear(month, year, arrCode, paramObj, ledgerCode, VPFObj);
			
					if (arrearData != null && arrearData.length > 0) {
											
						arrearData = processArrear(arrearData, paramObj, division, arrCode);
						
							if (arrearData != null && arrearData.length > 0) {
								/*for (int i = 0; i < arrearData.length; i++) {
									logger.info("Onhold arrear====="+arrearData[i][21]);
								}*/
									try{
										//UPDATED BY REEBA
										String  arrInsert = "INSERT INTO HRMS_PF_DATA (PF_MONTH,PF_YEAR,PF_EMP_ID,PF_ARR_MONTH,PF_ARR_YEAR,PF_TYPE,PF_ARR_DAYS,PF_EMP_BASIC,"
															+" PF_PFEPF,PF_EMP_PF,PF_EMP_F_PF,PF_CMP_PF,PF_CMP_F_PF,PF_EDLI,PF_TOTAL,"
															+" PF_EMP_DIV,PF_EMP_BRN,PF_EMP_DEPT,PF_EMP_DESG,PF_EMP_PAYBILL,PF_EMP_TYPE,PF_EMP_ONHOLD, PF_VPF,PF_EMP_GRADE )"
															+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?)";
										
										boolean result = getSqlModel().singleExecute(arrInsert, arrearData);
										
										if(result)
											logger.info("PF data 4 arrear saved successfully");
										else
											logger.info("Error while arrear PF data 4 salary");
							
									}catch(Exception e){
										logger.error("Exception while inserting the arrear records in PF data");
									}
						
							}
					}
					
					//UPDATED BY REEBA
					Object [][] settlementObj = getSettlement(month, year, paramObj, division, VPFObj);
					
					if (settlementObj != null && settlementObj.length > 0) {
						try{
							//UPDATED BY REEBA
							String  settInsert = "INSERT INTO HRMS_PF_DATA (PF_MONTH,PF_YEAR,PF_EMP_ID,PF_EMP_BASIC,PF_PFEPF,PF_EMP_PF,"
												+" PF_EMP_F_PF,PF_CMP_PF,PF_CMP_F_PF,PF_EDLI,PF_TYPE,PF_TOTAL,PF_EMP_DIV,PF_EMP_BRN,PF_EMP_DEPT,PF_EMP_DESG,PF_EMP_PAYBILL,PF_EMP_TYPE,PF_EMP_ONHOLD, PF_VPF,PF_EMP_GRADE )"
												+" VALUES(?,?,?,?,?,?,?,?,?,?,'T',?,?,?,?,?,?,?,?,?,?)";
							
							boolean result = getSqlModel().singleExecute(settInsert, settlementObj);
							
							if(result)
								logger.info("PF data 4 settlement saved successfully");
							else
								logger.info("Error while settlement PF data 4 salary");
							
						}catch(Exception e){
							logger.error("Exception while inserting the settlement records in PF data");
						}
					}
				
			}else
				logger.info("Pf parameters not defined");

			
		}catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception in getReportData"+e.getMessage());
		}
	}
	public Object[][] getSalary(String month ,String year, String ledgerCode,Object[][] paramObj, Object[][] VPFObj) {
		Object[][] dataObj = null;
		HashMap ageMap=null;
		Object[][] ageObj=null;
		try {
			String seniorCitizenAgeQuery ="SELECT TO_CHAR(TDS_EFF_DATE, 'yyyy'), TDS_SRCITIZEN_AGE FROM HRMS_TAX_PARAMETER ";
			
			ageMap = getSqlModel().getSingleResultMap(seniorCitizenAgeQuery,0, 2 );
			String mapYear=year;
			if(Integer.parseInt(month) > 3){
				mapYear = mapYear;
			}else{
				mapYear=String.valueOf(Integer.parseInt(mapYear)-1);
			}
			ageObj = (Object[][]) ageMap.get(mapYear);
			
			int citizenAge=0;
			if(ageObj!=null && ageObj.length > 0){
				citizenAge = Integer.parseInt(String.valueOf(ageObj[0][1]));
				System.out.println("############### AGE ####################"+citizenAge);
			}
			
			
			String empQuery = " SELECT "+month+","+year+",HRMS_SALARY_"+year+".EMP_ID,"
					+ " '',"
					+ " NVL(ROUND(HRMS_SAL_DEBITS_"+year+ ".SAL_AMOUNT),0) as PF, "
					+ " '' AS EPF, "
					+ " '' AS EFPF, "
					+ " CASE WHEN (SYSDATE-HRMS_EMP_OFFC.EMP_DOB)/365 > "+citizenAge+" THEN 'true'"
					+ " ELSE"
					+ " 'false' END AS CPF,"
					+ " '' AS CFPF ";

			empQuery += " ,'0'";


			empQuery += " ,'' AS TOTAL ";

			
			empQuery += ",SAL_DIVISION,SAL_EMP_CENTER,SAL_DEPT,SAL_EMP_RANK,SAL_EMP_PAYBILL,SAL_EMP_TYPE,SAL_ONHOLD, TO_CHAR(NVL(SAL_VPF.SAL_AMOUNT,0),9999999999.99) AS VPF,SAL_EMP_GRADE ";

			String empQuery1 = " FROM HRMS_SALARY_"+year
					//+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+year+ ".EMP_ID "
					+ " LEFT JOIN HRMS_SAL_DEBITS_"+year+ "  on ( HRMS_SAL_DEBITS_"+year+ ".EMP_ID =  HRMS_SALARY_"+year+ ".EMP_ID "+ " AND HRMS_SAL_DEBITS_"+year+ ".SAL_LEDGER_CODE IN( "+ ledgerCode+ ") and HRMS_SAL_DEBITS_"+year+ ".SAL_DEBIT_CODE = "+ String.valueOf(paramObj[0][4])+ " )"
					//ADDED BY REEBA FOR VPF
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
					+ " LEFT JOIN HRMS_SAL_DEBITS_"+year+ " SAL_VPF on ( SAL_VPF.EMP_ID =  HRMS_SALARY_"+year+ ".EMP_ID "+ " AND SAL_VPF.SAL_LEDGER_CODE IN( "+ ledgerCode+ ") and SAL_VPF.SAL_DEBIT_CODE = "+ String.valueOf(VPFObj[0][0])+ " )"
					+ " WHERE HRMS_SALARY_"+year+ ".SAL_LEDGER_CODE IN( " + ledgerCode+ " ) and (HRMS_SAL_DEBITS_"+year
					+ ".SAL_AMOUNT > 0) ";



			empQuery = empQuery + empQuery1;

			logger.info("salempQuery---------------" + empQuery);
			
			String creditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT,EMP_ID FROM HRMS_SAL_CREDITS_"+year+ " WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") ORDER BY EMP_ID,SAL_CREDIT_CODE ";   //+String.valueOf(dataObj[i][2]);
			HashMap creditMap=getSqlModel().getSingleResultMap(creditQuery, 2, 2);

			if(!ledgerCode.equals("")){
				
				dataObj = getSqlModel().getSingleResult(empQuery);
				
				if(dataObj != null && dataObj.length > 0){
					
					for(int i=0;i<dataObj.length;i++){
						double basicAmt=0.0;
						try{
							
							/*String creditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"+year+ " WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ="+String.valueOf(dataObj[i][2]);
							
							Object [][] empCredit = getSqlModel().getSingleResult(creditQuery);*/
							
							Object [][] empCredit = (Object[][])creditMap.get(String.valueOf(dataObj[i][2]));
							
							if(empCredit != null && empCredit.length > 0){
								
								basicAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(empCredit, String.valueOf(paramObj[0][9]),context,session));
								
								dataObj[i][3] =  formatter.format(basicAmt);
								double maxEmolAmt=Double.parseDouble(String.valueOf(paramObj[0][10]));
								if(basicAmt > maxEmolAmt && maxEmolAmt!=0)
									basicAmt =  Double.parseDouble(formatter.format(maxEmolAmt));
								
								dataObj[i][9] =  formatter.format(basicAmt);
								
								double epf=0;				//8.33%
								double pfAmount=Double.parseDouble(String.valueOf(dataObj[i][4]));//12%
								double efpf=0;//3.67
								double cpf=0;
								double cfpf=0;
								double totalPf=0;
								double vpf=Double.parseDouble(String.valueOf(dataObj[i][18]));
								
								epf=Math.round(basicAmt*Double.parseDouble(String.valueOf(paramObj[0][0]))/100);
								efpf=Math.round(pfAmount-epf);
								
								if(String.valueOf(dataObj[i][7]).equals("true")){
									cpf=0;
									cfpf=Math.round(Double.parseDouble(String.valueOf(paramObj[0][8]))*basicAmt/100);
								}else{
									if(Math.round(basicAmt*Double.parseDouble(String.valueOf(paramObj[0][2]))/100)>541){
										cfpf=Math.round((basicAmt*Double.parseDouble(String.valueOf(paramObj[0][3]))/100)+(basicAmt*Double.parseDouble(String.valueOf(paramObj[0][2]))/100))-541;
										cpf=541;
									}else{
										cpf=Math.round(basicAmt*Double.parseDouble(String.valueOf(paramObj[0][2]))/100);
										cfpf=Math.round(Double.parseDouble(String.valueOf(dataObj[i][4])))-Math.round(basicAmt*Double.parseDouble(String.valueOf(paramObj[0][2]))/100);
									}
								}
								totalPf=epf+efpf+cpf+cfpf+vpf;
								dataObj[i][4] =  pfAmount;
								dataObj[i][5] =  formatter.format(Math.round(epf));
								dataObj[i][6] =  formatter.format(Math.round(efpf));
								dataObj[i][7] =  formatter.format(Math.round(cpf));
								dataObj[i][8] =  formatter.format(Math.round(cfpf));
								dataObj[i][10] =  formatter.format(Math.round(totalPf));
							}
														
						}catch(Exception e){
							logger.error("Exception in getSalary in geting the basic amount using formula for employee "+e);
							//e.printStackTrace();
						}
						
					}
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getSalary");
			logger.error(e.getMessage());
		}
		return dataObj;
	}
	public Object[][] getArrear(String month ,String year, String arrLedgerCode,Object[][] paramObj,String ledgerCode, Object[][] VPFObj){
		Object[][] dataObj = null;
		try{
			
			/*String empQuery = " SELECT "+month+","+year+",ARREARS_EMP_ID,ARREARS_MONTH,ARREARS_YEAR,TRIM(ARREARS_TYPE),'', "
				+ " '',"
				+ " TO_CHAR(HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT,9999999999.00) as PF, "
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+ ",9999999999.00) as epf, "
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][1])+ ")/"+ String.valueOf(paramObj[0][8])+ ",9999999999.00) as efpf, "
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as CPF,"
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as CFPF ";

			empQuery += " ,0 ";

			empQuery += ",TO_CHAR((HRMS_ARREARS_DEBIT_" +year+".ARREARS_AMT * " + String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+ ",9999999999.00)" + " + " 
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+ ".ARREARS_AMT * "+ String.valueOf(paramObj[0][1]) + ")/"+ String.valueOf(paramObj[0][8])+ ",9999999999.00)" + " + "
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) "+ " + " 
				+ " TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) "+ " + " 
				//UPDATED BY REEBA
				+ " TO_CHAR(ARR_VPF.ARREARS_AMT,9999999999.00)  as total";

			empQuery += ",SAL_DIVISION,SAL_EMP_CENTER,SAL_DEPT,SAL_EMP_RANK,SAL_EMP_PAYBILL,SAL_EMP_TYPE,ARREARS_ONHOLD" 
				//UPDATED BY REEBA
				+ " , TO_CHAR(ARR_VPF.ARREARS_AMT,9999999999.00) AS VPF ";

			String empQuery1 = " FROM HRMS_ARREARS_DEBIT_"+year
				//+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID "
				+ " INNER JOIN HRMS_SALARY_"+year+" ON  (HRMS_SALARY_"+year+".EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID  and  SAL_LEDGER_CODE in ("+ ledgerCode+ ") ) "
				+ " INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE  "
				//UPDATED BY REEBA
				+ " INNER JOIN HRMS_ARREARS_"+year+"  on HRMS_ARREARS_"+year+".ARREARS_CODE= HRMS_ARREARS_LEDGER.ARREARS_CODE "
				+ " INNER JOIN HRMS_ARREARS_DEBIT_"+year+" ARR_VPF ON  (ARR_VPF.ARREARS_EMP_ID = HRMS_SALARY_"+year+".EMP_ID and  SAL_LEDGER_CODE in ("+ ledgerCode+ ")  " 
				+ " AND ARR_VPF.ARREARS_DEBIT_CODE = "+ String.valueOf(VPFObj[0][0])
				+ " AND ARR_VPF.ARREARS_MONTH=HRMS_ARREARS_DEBIT_"+year+".ARREARS_MONTH and HRMS_ARREARS_DEBIT_"+year+".ARREARS_YEAR=ARR_VPF.ARREARS_YEAR "
				+ " AND HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE=ARR_VPF.ARREARS_CODE) "
				//UPDATED BY REEBA ENDS
				+ " WHERE (HRMS_ARREARS_DEBIT_" +year+".ARREARS_AMT > 0 OR ARR_VPF.ARREARS_AMT > 0) and HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE in ("+ arrLedgerCode+" )"
				+ " and HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE = "+ String.valueOf(paramObj[0][4])+" ORDER BY ARREARS_EMP_ID,ARREARS_TYPE";*/
			
			String empQuery=" SELECT "+month+","+year+",HRMS_ARREARS_"+year+".EMP_ID , HRMS_ARREARS_"+year+".ARREARS_MONTH,HRMS_ARREARS_"+year+".ARREARS_YEAR, " 
						+" TRIM(ARREARS_TYPE),'', '', TO_CHAR(HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT,9999999999.00) as PF, "
						+" TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as epf,  TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][1])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as efpf,  TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as CPF, TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as CFPF  ,0 ,TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) +  TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][1])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) +  TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00)  +  TO_CHAR((HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT *"+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+",9999999999.00) as total, "
						+" SAL_DIVISION, SAL_EMP_CENTER, SAL_DEPT, SAL_EMP_RANK, SAL_EMP_PAYBILL, SAL_EMP_TYPE "
						+" ,HRMS_ARREARS_"+year+".ARREARS_ONHOLD "
						+" ,0 AS VPF,SAL_EMP_GRADE  ";
			
			String empQuery1 =" FROM HRMS_ARREARS_"+year
						+" INNER JOIN HRMS_ARREARS_DEBIT_"+year+" ON (HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE = HRMS_ARREARS_"+year+".ARREARS_CODE AND HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID AND HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE = "+ String.valueOf(paramObj[0][4])+"" 
						+" AND HRMS_ARREARS_DEBIT_"+year+".ARREARS_MONTH=HRMS_ARREARS_"+year+".ARREARS_MONTH and HRMS_ARREARS_DEBIT_"+year+".ARREARS_YEAR=HRMS_ARREARS_"+year+".ARREARS_YEAR) "
						+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE  = HRMS_ARREARS_"+year+".ARREARS_CODE)"
						+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID = HRMS_ARREARS_"+year+".EMP_ID AND SAL_LEDGER_CODE in ("+ ledgerCode+ ") ) " 
						+" WHERE HRMS_ARREARS_"+year+".ARREARS_CODE IN ("+ arrLedgerCode+") ";
			empQuery = empQuery + empQuery1;

			logger.info("ArrearempQuery--------------------" + empQuery);
			
			
			String arrType=" SELECT HRMS_ARREARS_"+year+ ".EMP_ID,ARREARS_TYPE,ARREARS_DAYS,ARREARS_MONTH,ARREARS_YEAR  "
				+" FROM HRMS_ARREARS_LEDGER " 
				+" INNER JOIN HRMS_ARREARS_"+year+ " ON HRMS_ARREARS_"+year+ ".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE "
				+" WHERE HRMS_ARREARS_LEDGER.ARREARS_CODE in ("+arrLedgerCode+") AND HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE!='R'";
			
			
			if(!ledgerCode.equals("") && !arrLedgerCode.equals("")){
				
				dataObj = getSqlModel().getSingleResult(empQuery);
				
				if(dataObj !=null && dataObj.length > 0){
					
					Object[][] typeObj = getSqlModel().getSingleResult(arrType);
					
					for (int j = 0; j < dataObj.length; j++) {
						double basicAmt = 0.0;
						
						if(typeObj !=null && typeObj.length > 0){
							
							try{
								for (int l = 0; l < typeObj.length; l++) {
		
									if (String.valueOf(dataObj[j][2]).trim().equals(String.valueOf(typeObj[l][0]).trim())
											&& String.valueOf(dataObj[j][5]).trim().equals(String.valueOf(typeObj[l][1]).trim())
											&& String.valueOf(dataObj[j][3]).trim().equals(String.valueOf(typeObj[l][3]).trim())
											&& String.valueOf(dataObj[j][4]).trim().equals(String.valueOf(typeObj[l][4]).trim())
											
									) {
																				
										dataObj[j][6]=String.valueOf(typeObj[l][2]);
										break;
									}
								}
							}catch(Exception e){
								logger.error("Exception in getArrear in adding arrear days into final object "+e);
								//e.printStackTrace();
							}
						}
						try{
							String creditQuery = " SELECT ARREARS_CREDIT_CODE ,ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"+year
									+" INNER JOIN HRMS_ARREARS_LEDGER  on HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE "
									+" WHERE ARREARS_EMP_ID = "+String.valueOf(dataObj[j][2])+" AND HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE in ("+arrLedgerCode+") AND " 
									+" ARREARS_MONTH = "+String.valueOf(dataObj[j][3])+" AND ARREARS_YEAR = "+String.valueOf(dataObj[j][4])
									+" AND HRMS_ARREARS_CREDIT_"+year+".ARREARS_PAY_TYPE!='R' AND ARREARS_TYPE LIKE '"+String.valueOf(dataObj[j][5])+"'";
								
							Object [][] empCredit = getSqlModel().getSingleResult(creditQuery);
								
							if(empCredit != null && empCredit.length > 0){
									
									basicAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(empCredit, String.valueOf(paramObj[0][9]),context,session));
									
									dataObj[j][7] =  formatter.format(basicAmt);
									double maxEmolAmt=Double.parseDouble(String.valueOf(paramObj[0][10]));
									if(basicAmt > maxEmolAmt && maxEmolAmt>0)
										dataObj[j][13] =  formatter.format(maxEmolAmt);
									else
										dataObj[j][13] =  formatter.format(basicAmt);
									
									
									
							}
							
						}catch(Exception e){
							logger.error("Exception in getArrear in getting  the basic amount using formula for employee "+e);
							//e.printStackTrace();
						}
						
						
					}
					
				}
				
			}

			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in getArrear");
			logger.error(e.getMessage());
		}
		return dataObj;
	}
	public Object[][] processArrear(Object[][] arrearObj,Object[][] paramObj,String divCode,String arrearCode){
		try{
						
			//Object salObj [][] = null;
			//Object salCreditObj [][] = null;
			//Object arrCreditObj [][] = null;
			//Object arrObj [][] = null;
			
			
			for (int i = 0; i < arrearObj.length; i++) {
				
				String ledgerCode="";
				
				String ledgerQuery=" SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
					+" LEDGER_MONTH = "+String.valueOf(arrearObj[i][3])+" AND LEDGER_YEAR = "+String.valueOf(arrearObj[i][4])+" AND LEDGER_DIVISION IN ("+divCode+")";

				Object ledgerObj [][] = getSqlModel().getSingleResult(ledgerQuery);
				
				if(String.valueOf(arrearObj[i][5]).trim().equals("M"))
					arrearObj[i][5]="AM";	
				else if(String.valueOf(arrearObj[i][5]).trim().equals("P"))
					arrearObj[i][5]="AP";
				
				
				if (ledgerObj != null && ledgerObj.length > 0) {
				
					for (int j = 0; j < ledgerObj.length; j++) {
						ledgerCode += String.valueOf(ledgerObj[j][0]) + ",";
					}
					ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
					
					String empDebit = " SELECT '',SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+String.valueOf(arrearObj[i][4])+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ="
										+String.valueOf(arrearObj[i][2])+" AND SAL_DEBIT_CODE ="+String.valueOf(paramObj[0][4]);
							
					String empQuery= " SELECT SAL_CREDIT_CODE,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"+String.valueOf(arrearObj[i][4])+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ="
										+String.valueOf(arrearObj[i][2]);
					
					if(ledgerObj != null && ledgerObj.length > 0){
						
						Object salCreditObj [][] = getSqlModel().getSingleResult(empQuery);
						
						Object salObj [][] = getSqlModel().getSingleResult(empDebit);
						
						if(salCreditObj != null && salCreditObj.length > 0 &&
								salObj != null && salObj.length > 0)
							salObj[0][0] = Utility.expressionEvaluate(new Utility().generateFormulaPay(salCreditObj, String.valueOf(paramObj[0][9]),context,session));
						
						
						String arrDebit = " SELECT '',ARREARS_AMT FROM HRMS_ARREARS_DEBIT_"+String.valueOf(arrearObj[i][4])+" WHERE "
										 +" HRMS_ARREARS_DEBIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_MONTH = "+String.valueOf(arrearObj[i][3])
										 +" AND HRMS_ARREARS_DEBIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_YEAR = "+String.valueOf(arrearObj[i][4])
										 +" AND HRMS_ARREARS_DEBIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_DEBIT_CODE = "+String.valueOf(paramObj[0][4])
										 +" AND HRMS_ARREARS_DEBIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_EMP_ID = "+String.valueOf(arrearObj[i][2])
										 +" AND ARREARS_CODE NOT IN ("+arrearCode+")";
						
						String arrCredit = " SELECT ARREARS_CREDIT_CODE,ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"+String.valueOf(arrearObj[i][4])+" WHERE "
										+" HRMS_ARREARS_CREDIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_MONTH = "+String.valueOf(arrearObj[i][3])
										+" AND HRMS_ARREARS_CREDIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_YEAR = "+String.valueOf(arrearObj[i][4])
										+" AND HRMS_ARREARS_CREDIT_"+String.valueOf(arrearObj[i][4])+".ARREARS_EMP_ID = "+String.valueOf(arrearObj[i][2])
										+" AND ARREARS_CODE NOT IN ("+arrearCode+")";
											
						Object arrCreditObj [][] = getSqlModel().getSingleResult(arrCredit);
						
						Object arrObj [][] = getSqlModel().getSingleResult(arrDebit);
						
						if(arrCreditObj != null && arrCreditObj.length > 0 &&
								arrObj != null && arrObj.length > 0)
							arrObj[0][0] = Utility.expressionEvaluate(new Utility().generateFormulaPay(arrCreditObj, String.valueOf(paramObj[0][9]),context,session));
					
						if(salObj != null && salObj.length > 0){
							
							if(arrObj != null && arrObj.length > 0){
								
								salObj[0][0] = Double.parseDouble(String.valueOf(salObj[0][0])) +
										Double.parseDouble(String.valueOf(arrObj[0][0]));
			
								salObj[0][1] = Double.parseDouble(String.valueOf(salObj[0][1])) +
										Double.parseDouble(String.valueOf(arrObj[0][1]));
								
							}
							try{
																
									if( (Double.parseDouble(String.valueOf(salObj[0][1])) 
											* Double.parseDouble(String.valueOf(paramObj[0][2])) 
											/ Double.parseDouble(String.valueOf(paramObj[0][8]))) > 541 ){
																
										arrearObj[i][12] = ((Double.parseDouble(String.valueOf(arrearObj[i][8]))
												* Double.parseDouble(String.valueOf(paramObj[0][2])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8])))
												+(Double.parseDouble(String.valueOf(arrearObj[i][8]))
												* Double.parseDouble(String.valueOf(paramObj[0][3])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8]))));
										arrearObj[i][11] = 0.00;
																	
									}else if( ((Double.parseDouble(String.valueOf(salObj[0][1])) 
											 * Double.parseDouble(String.valueOf(paramObj[0][2])) 
											 / Double.parseDouble(String.valueOf(paramObj[0][8])))
											 +(Double.parseDouble(String.valueOf(arrearObj[i][8])) 
											 * Double.parseDouble(String.valueOf(paramObj[0][3])) 
											 / Double.parseDouble(String.valueOf(paramObj[0][8]))))  > 541 ){
										
										arrearObj[i][12] = ((Double.parseDouble(String.valueOf(arrearObj[i][8]))
												* Double.parseDouble(String.valueOf(paramObj[0][2])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8])))
											 +((Double.parseDouble(String.valueOf(salObj[0][1]))
												* Double.parseDouble(String.valueOf(paramObj[0][3])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8])))
												+ (Double.parseDouble(String.valueOf(arrearObj[i][8]))
												* Double.parseDouble(String.valueOf(paramObj[0][3])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8]))) - 541));
										arrearObj[i][11] = (541 - (Double.parseDouble(String.valueOf(salObj[0][1])) 
							 					* Double.parseDouble(String.valueOf(paramObj[0][2])) 
							 					/ Double.parseDouble(String.valueOf(paramObj[0][8]))));
																	
									}else{
										
										arrearObj[i][12] = (Double.parseDouble(String.valueOf(arrearObj[i][8]))
												* Double.parseDouble(String.valueOf(paramObj[0][3])) 
												/ Double.parseDouble(String.valueOf(paramObj[0][8])));
										arrearObj[i][11] = ((Double.parseDouble(String.valueOf(arrearObj[i][8])) 
		 										* Double.parseDouble(String.valueOf(paramObj[0][2])) 
		 										/ Double.parseDouble(String.valueOf(paramObj[0][8]))));
										
									}
									
							}catch(Exception e){
								logger.error("Exception in setting arrear Object at Employer PF in ProcessArrear"+e);
								//e.printStackTrace();
							}
							
							arrearObj[i][11] = formatter.format(Double.parseDouble(String.valueOf(arrearObj[i][11])));
							
							/*try{
									if( (Double.parseDouble(String.valueOf(salObj[0][1])) 
											* Double.parseDouble(String.valueOf(paramObj[0][3])) 
											/ Double.parseDouble(String.valueOf(paramObj[0][8]))) > 541 ){
										
										
										
										
									}else if( ((Double.parseDouble(String.valueOf(salObj[0][1])) 
											 * Double.parseDouble(String.valueOf(paramObj[0][3])) 
											 / Double.parseDouble(String.valueOf(paramObj[0][8])))
											 +(Double.parseDouble(String.valueOf(arrearObj[i][8])) 
											 * Double.parseDouble(String.valueOf(paramObj[0][3])) 
											 / Double.parseDouble(String.valueOf(paramObj[0][8]))))  > 541 ){
										
									
									
									}else{
										
									
													
									}
									
							}catch(Exception e){
								logger.error("Exception in setting arrear Object at Employer Family PF in ProcessArrear "+e);
								//e.printStackTrace();
							}*/
							
							arrearObj[i][12] = formatter.format(Double.parseDouble(String.valueOf(arrearObj[i][12])));
							
							try{
								
									if( Double.parseDouble(String.valueOf(salObj[0][0])) > 6500 ){
										
										arrearObj[i][13] = 0.00;
										
									}else if( (Double.parseDouble(String.valueOf(salObj[0][0]))
												+ Double.parseDouble(String.valueOf(arrearObj[i][7]))) > 6500){
										
										arrearObj[i][13] = 6500 - Double.parseDouble(String.valueOf(salObj[0][0]));
										
									}else{
										
										arrearObj[i][13] = Double.parseDouble(String.valueOf(arrearObj[i][7]));
										
									}
									
							}catch(Exception e){
								logger.error("Exception in setting arrear Object at EDLI salary in ProcessArrear "+e);
								//e.printStackTrace();
							}
							arrearObj[i][13] = formatter.format(Double.parseDouble(String.valueOf(arrearObj[i][13])));
							try{							
							arrearObj[i][14] = Double.parseDouble(String.valueOf(arrearObj[i][9]))
														+ Double.parseDouble(String.valueOf(arrearObj[i][10]))
														+ Double.parseDouble(String.valueOf(arrearObj[i][11]))
														+ Double.parseDouble(String.valueOf(arrearObj[i][12]))
														//UPDATED BY REEBA
														+ Double.parseDouble(String.valueOf(arrearObj[i][22]));
							}catch(Exception e){
								logger.error("Exception in setting arrear Object at total in ProcessArrear "+e);
								//e.printStackTrace();
							}
												
							arrearObj[i][14] = formatter.format(Double.parseDouble(String.valueOf(arrearObj[i][14])));
						}
					}
				}
			}
			
		}catch(Exception e){
			logger.error("Exception in setting arrear Object in ProcessArrear "+e);
			//e.printStackTrace();
		}
		return arrearObj;
	}
	
	public Object[][] getSettlement(String month ,String year,Object[][] paramObj,String divCode, Object[][] VPFObj){
		Object dataObj [][] =null;
		try{
			
			String empQuery = " SELECT "+month+","+year+",SETTL_ECODE,"
						+ " '0'," 
						+ " TO_CHAR(SUM(HRMS_SETTL_DEBITS.SETTL_AMT),9999999999.00) as PF,  "
						+ " TO_CHAR(SUM ((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+ "),9999999999.00) as epf, "
						+ " TO_CHAR(SUM((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][1])+ ")/"+ String.valueOf(paramObj[0][8])+ "),9999999999.00) as efpf, "
						+ " to_char(SUM(CASE WHEN  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " > 541 "
						+ "  THEN (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])
						+ " + ((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " - 541)  "
						+ " else (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+ "  end ),9999999999.00) as CPF, "
						+ " to_char(SUM(CASE WHEN  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " > 541 "
						+ " THEN 541  "
						+ " else  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ "  end ),9999999999.00) as CFPF ";
						
				empQuery += ",'0'";
				
				empQuery += " ,to_char(TO_CHAR(SUM ((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][0])+ ")/"+ String.valueOf(paramObj[0][8])+ "),9999999999.00) +  "
						+ " TO_CHAR(SUM((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][1])+ ")/"+ String.valueOf(paramObj[0][8])+ "),9999999999.00) + "
						+ " to_char(SUM(CASE WHEN  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " > 541 "
						+ "  THEN (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])
						+ " + ((HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " - 541)  "
						+ " else (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][2])+ ")/"+ String.valueOf(paramObj[0][8])+ "  end ),9999999999.00) + "
						+ " to_char(SUM(CASE WHEN  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ " > 541 "
						+ " THEN 541  "
						+ " else  (HRMS_SETTL_DEBITS.SETTL_AMT * "+ String.valueOf(paramObj[0][3])+ ")/"+ String.valueOf(paramObj[0][8])+ "  end ),9999999999.00),9999999999.00)"
						//UPDATED BY REEBA
						+ " +TO_CHAR(SUM(SETTL_VPF.SETTL_AMT),9999999999.00) as total  ";
					
			
				empQuery += ",EMP_DIV,EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_PAYBILL,EMP_TYPE,'', TO_CHAR(SUM(SETTL_VPF.SETTL_AMT),9999999999.00) as VPF,EMP_CADRE ";
				
				empQuery += " FROM HRMS_RESIGN "
							+ " INNER JOIN HRMS_SETTL_HDR on HRMS_SETTL_HDR.SETTL_RESGNO =  HRMS_RESIGN.RESIGN_CODE "
							+ " INNER JOIN HRMS_SETTL_DEBITS  on HRMS_SETTL_DEBITS.SETTL_CODE =  HRMS_SETTL_HDR.SETTL_CODE  AND HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE = "+ String.valueOf(paramObj[0][4])+" AND HRMS_SETTL_DEBITS.SETTL_MTH_TYPE='CO'"
							+ " LEFT JOIN HRMS_SETTL_DEBITS SETTL_VPF  on (SETTL_VPF.SETTL_CODE =  HRMS_SETTL_HDR.SETTL_CODE  AND SETTL_VPF.SETTL_DEBIT_CODE = "+ String.valueOf(VPFObj[0][0])+" AND SETTL_VPF.SETTL_MTH_TYPE='CO') "
							+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = SETTL_ECODE "
							+ " WHERE  HRMS_EMP_OFFC.EMP_DIV IN ("+divCode+")"
							+ " AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')='"+month+"-"+year+"' " ;

				empQuery += " group by "+month+","+year+",'0',SETTL_ECODE,EMP_DIV,EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_PAYBILL,EMP_TYPE,'',EMP_CADRE ";

				logger.info("settempQuery---------------" + empQuery);
				
				dataObj = getSqlModel().getSingleResult(empQuery);
				
				if(dataObj !=null && dataObj.length > 0){
					
					for (int j = 0; j < dataObj.length; j++) {
						double  basicAmount = 0.0;
						
						try{
							String basicQuery=" SELECT SETTL_CREDIT_CODE,SUM(SETTL_AMT) FROM HRMS_SETTL_CREDITS "
									+" INNER JOIN HRMS_SETTL_HDR ON HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE AND SETTL_MTH_TYPE='CO' AND SETTL_ECODE ="+String.valueOf(dataObj[j][2])
									+" GROUP BY SETTL_CREDIT_CODE ";
							
							Object baseObject [][] = getSqlModel().getSingleResult(basicQuery);
							
							if(baseObject !=null && baseObject.length > 0){
								
								basicAmount = Utility.expressionEvaluate(new Utility().generateFormulaPay(baseObject, String.valueOf(paramObj[0][9]),context,session));
								
								dataObj[j][3]=formatter.format(basicAmount);
								double maxEmolAmt=Double.parseDouble(String.valueOf(paramObj[0][10]));
								if(basicAmount > maxEmolAmt && maxEmolAmt>0)
									dataObj[j][9] =  formatter.format(maxEmolAmt);
								else
									dataObj[j][9] =  formatter.format(basicAmount);
														
							}
							
						}catch(Exception e){
							logger.error("Exception in getSettlement while adding basic amount  "+e);
						}
										
					}
					
				}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in getSettlement"+e);
		}
		return dataObj;
	}
	public void deletePFData(String month,String year,String divCode){
		
		try {
			String query = " DELETE FROM HRMS_PF_DATA WHERE PF_MONTH = "+month+" AND PF_YEAR ="+year+" AND PF_EMP_DIV IN ("+divCode+")";
			
			getSqlModel().singleExecute(query);
			
		} catch (Exception e) {
			logger.error("Exception in getSettlement"+e);
			//e.printStackTrace();
		}
		
		
	}
	
}