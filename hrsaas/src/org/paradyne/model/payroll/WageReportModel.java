
package org.paradyne.model.payroll;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.WageReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;



/**
 * @author AA0554
 * Date 14/10/2008
 * WageReportModel class to write business logic to generate the report for the wages for selected year
 */


public class WageReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.WageReportModel.class);
	
	/**
	 * @Method getReport
	 * this method fetches the wages of the selected employee for selected financial year and displays it in the PDF format.
	 * @param bean
	 * @param response
	 * @param request
	 * 
	 */
	public void getReport(WageReport bean,HttpServletResponse response,HttpServletRequest request){

		logger.info(" inside getReport   ");
		
		Object  creditHeadData [][] =getSqlModel().getSingleResult("SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PAYFLAG='Y' AND CREDIT_PERIODICITY='M'"
				+" ORDER BY CREDIT_CODE");					// retrieve the credit heads

		Object  debitHeadData [][] =getSqlModel().getSingleResult("SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_PAYFLAG='Y' AND DEBIT_PERIODICITY='M'"
				+" ORDER BY DEBIT_CODE"); 					// retrieve the debit heads

		int length =0;
		length = creditHeadData.length + debitHeadData.length;

		Object salData [][]= new Object[length][13] ;			// Object to collect the credit data & debit data at one place
		
			

		Object salTableData[][]=null;
		
		Object totalObject [][]=null;

		Object tempSalData [][]= calculateSalData(bean, length, "Sal", creditHeadData.length);			// calculate salary amount
		
		Object tempSalDataArr [][]= calculateSalData(bean, length, "Arr", creditHeadData.length);			// calculate arrears amount
				
		/*
		 * adding the tempSalData(salary Data) and tempSalDataArr(Arrears Data)
		 * in the salData object
		 * 
		 */
		for (int i = 0; i < tempSalDataArr.length; i++) {
			salData [i][0] = tempSalData[i][0];
			for (int j = 1; j < 13; j++) {
				
				if(String.valueOf(tempSalData[i][j]).equals("") && String.valueOf(tempSalDataArr[i][j]).equals("")){
					salData [i][j] = "";
				}
				else if(String.valueOf(tempSalData[i][j]).equals("") && ! (String.valueOf(tempSalDataArr[i][j]).equals(""))){
					salData [i][j] = String.valueOf(tempSalDataArr[i][j]);
				}
				else if(!(String.valueOf(tempSalData[i][j]).equals("")) && String.valueOf(tempSalDataArr[i][j]).equals("")){
					salData [i][j] = String.valueOf(tempSalData[i][j]);
				} else if(!(String.valueOf(tempSalData[i][j]).equals("") && String.valueOf(tempSalDataArr[i][j]).equals(""))){
					salData [i][j] = ((Integer.parseInt((String.valueOf(tempSalDataArr[i][j]))))+(Integer.parseInt((String.valueOf(tempSalData[i][j])))));
				}
			}
			
		}
		/*
		 * adding the settlement amount in the salData Object
		 * 
		 */
		Object tempSalDataSettlement [][]=calculateSalDataForSettlement(bean, length, creditHeadData.length,salData);
			
			//logger.info("tempSalData==="+tempSalData.length);
		
		try {
			salTableData = new Object[length][14];
			/*
			 *  collect the Credit headers with their name & data in the saltableData object
			 * 
			 */
			for (int i = 0; i < creditHeadData.length; i++) {
				salTableData[i][0] = String.valueOf(creditHeadData[i][1]);
				for (int k = 1; k <= 12; k++) {
					salTableData[i][k] = String.valueOf(tempSalDataSettlement[i][k]);
				}
			} // end of for loop
			for (int i = 0; i < debitHeadData.length; i++) {
				salTableData[creditHeadData.length + i][0] = String
						.valueOf(debitHeadData[i][1]);
				for (int k = 1; k <= 12; k++) {
					salTableData[i + creditHeadData.length][k] = String
							.valueOf(tempSalDataSettlement[creditHeadData.length + i][k]);
				}
			}// end of for loop
			totalObject = new Object[1][14];
			double totalHead = 0.0;
			double total = 0.0;
			/*
			 * 'for loop' to calculate the total of the particular heads & set it in the salTableData
			 * 
			 */
			for (int i = 0; i < salTableData.length; i++) {
				for (int k = 1; k < 13; k++) {
					//logger.info("salData [" + i + "][" + k + "]"
						//	+ salTableData[i][k]);
					try {
						if (!(String.valueOf(salTableData[i][k]).equals("") || String
								.valueOf(salTableData[i][k]).equals(null))) {
							totalHead += Double.parseDouble(String
									.valueOf(salTableData[i][k]));

						}
					} catch (Exception e) {
						totalHead += 0.0;
						e.printStackTrace();
						//logger.error(e);
					}

				}
				salTableData[i][13] = String.valueOf(totalHead);
				totalHead = 0.0;

			}// end of for loop
			/*
			 * 'for loop' to calculate the total of the all heads for particular month column & set it in the totalObject
			 * 
			 */
			for (int i = 1; i < 14; i++) {
				for (int k = 0; k < salTableData.length; k++) {
					try {
						total += Double.parseDouble(String
								.valueOf(salTableData[k][i]));
					} catch (Exception e) {
						total += 0.0;
						//logger.error(e);
					}
				}
				if (total != 0.0)
					totalObject[0][i] = String.valueOf(total);
				total = 0.0;
			}// end of for loop
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
		}
			/*
			 * query to collect company information used to set the report header
			 */
			/*String companyQuery="SELECT COMPANY_NAME, NVL(COMPANY_ADDRESS,'-'),LOCATION_NAME, NVL(COMPANY_PIN,''), NVL(COMPANY_TELPHONE,'') FROM HRMS_COMPANY "
				+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_COMPANY.COMPANY_CITYID)";*/

			
			String divisionQuery ="SELECT DIV_NAME,NVL(DIV_ADDRESS1 || DIV_ADDRESS2 || DIV_ADDRESS3,' '),LOCATION_NAME,NVL(DIV_PINCODE,''),NVL(DIV_TELEPHONE,'')"
							+" FROM HRMS_DIVISION "
							+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_DIVISION.DIV_CITY_ID)"
							+" INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)"
							+" WHERE EMP_ID = "+bean.getEmpID();
			
			Object divisionData[][]=getSqlModel().getSingleResult(divisionQuery);

			try {
				String columnNames[]={"Payment Head","Apr","May","Jun","July","Aug","Sep","Oct","Nov","Dec","Jan","Feb","Mar","Total"};

				int cellwidth []={60,20,20,20,20,20,20,20,20,20,20,20,20,30};

				int alignment []={0,2,2,2,2,2,2,2,2,2,2,2,2,2};

				Object empData [][]= new Object [1][2];

				empData [0][0] = "Employee ID :\t"+bean.getEmpTokenNo();

				empData [0][1] = "Employee Name :\t"+bean.getEmpName();

				int cellwidthEmp []={30,70};

				int alignmentEmp []={0,0};


				totalObject [0][0] = "\t\t\t\t\tTotal";

				String s = ""+String.valueOf(divisionData[0][0])+"\n\n";
				org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
						s);
				
				rg.setFName("Wage Report For "+bean.getEmpName()+".pdf");
				rg.addFormatedText(s, 2, 0, 1, 0);

				/*
				 * set the Report header 
				 * 
				 */
				rg.addFormatedTextForPurchase(""+String.valueOf(divisionData[0][1])+","+String.valueOf(divisionData[0][2])+"-"+checkNull(String.valueOf(divisionData[0][3]),""), 1, 0, 1, 0);
				rg.addFormatedTextForPurchase("Tel :# "+checkNull(String.valueOf(divisionData[0][4]),""), 1, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addFormatedTextForPurchase("Wage Report for the period 01/04/"+bean.getFromYear()+" to 31/03/"+bean.getToYear(), 1, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				
				rg.tableBodyNoBorder(empData, cellwidthEmp, alignmentEmp);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(columnNames, salTableData, cellwidth,alignment);
				rg.tableBody(totalObject, cellwidth, alignment);
				rg.createReport(response);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}

		
	}
	
public Object [][] calculateSalData(WageReport bean,int totalLength, String type,int creditLength){
	
	logger.info("inside calculateSalData");
	Object salData [][] = new Object [totalLength][13];
	
	/*
	 * for loop to collect the credit data in salData Object
	 */
	for (int j = 1; j <= 12; j++) {
		int month =(j+3) % 12;
		if (month==0)
			month=12;
		String year = bean.getFromYear();
		if(month < 4)
			year = bean.getToYear();

		String innerQuery = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_LEDGER "
			+" LEFT JOIN HRMS_SAL_CREDITS_"+year+" ON (HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE =HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+" WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR ="+year+" AND EMP_ID="+bean.getEmpID()	;

		String innerQueryArrears="SELECT DISTINCT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER "
			+" LEFT JOIN HRMS_ARREARS_CREDIT_"+year+" ON (HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE =HRMS_ARREARS_LEDGER.ARREARS_CODE)"
			+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR ="+year+" AND ARREARS_EMP_ID="+bean.getEmpID();
 
		String creditQuery =" SELECT CREDIT_CODE,SAL_AMOUNT FROM HRMS_CREDIT_HEAD " 
			+" LEFT JOIN  HRMS_SAL_CREDITS_"+year+" ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE "
			+" AND EMP_ID ="+bean.getEmpID()+" AND SAL_LEDGER_CODE=("+innerQuery+")) "
			+" LEFT JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE ) "
			+" WHERE  CREDIT_PAYFLAG='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";
		
		String creditQueryArrears ="SELECT CREDIT_CODE,ARREARS_AMT FROM HRMS_CREDIT_HEAD "
			+" LEFT JOIN  HRMS_ARREARS_CREDIT_"+year+" ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE "
			+" AND ARREARS_EMP_ID="+bean.getEmpID()+" AND ARREARS_CODE=("+innerQueryArrears+")) " 
			+" LEFT JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE )"
			+" WHERE  CREDIT_PAYFLAG='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE";

		Object creditData [][] = null;
		if(type.equals("Sal")){
			creditData  = getSqlModel().getSingleResult(creditQuery);
			
		}else {
		creditData  = getSqlModel().getSingleResult(creditQueryArrears);
		
		}
		
		try {
			//  collection of the credit data
			for (int i = 0; i < creditData.length; i++) {
				try {
					salData[i][0] = checkNull(String
							.valueOf(creditData[i][0]), "");
					salData[i][j] = checkNull(String
							.valueOf(creditData[i][1]), "");
				} catch (Exception e) {
					salData[i][0] = checkNull(String
							.valueOf(creditData[i][0]), "");
					salData[i][j] = "";
				}
			} // end of for loop
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
		}


	} // end of for loop
	
	/*
	 * for loop to collect the debit data in salData Object
	 */
	for (int j = 1; j <= 12; j++) {
		int month =(j+3) % 12;
		if (month==0)
			month=12;
		String year = bean.getFromYear();
		if(month < 4)
			year = bean.getToYear();

		String innerQuery = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_LEDGER "
			+" LEFT JOIN HRMS_SAL_DEBITS_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE =HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+" WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR ="+year+" AND EMP_ID="+bean.getEmpID()	;

		String debitQuery =" SELECT DEBIT_CODE,SAL_AMOUNT FROM HRMS_DEBIT_HEAD " 
			+" LEFT JOIN  HRMS_SAL_DEBITS_"+year+" ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE "
			+" AND EMP_ID ="+bean.getEmpID()+" AND SAL_LEDGER_CODE=("+innerQuery+")) "
			+" LEFT JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE ) "
			+" WHERE  DEBIT_PAYFLAG='Y' AND DEBIT_PERIODICITY='M' ORDER BY DEBIT_CODE ";

		String innerQueryArrears="SELECT DISTINCT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER "
			+" LEFT JOIN HRMS_ARREARS_DEBIT_"+year+" ON (HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE =HRMS_ARREARS_LEDGER.ARREARS_CODE)"
			+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR ="+year+" AND ARREARS_EMP_ID="+bean.getEmpID();
		
		
		String debitQueryArrears ="SELECT DEBIT_CODE,ARREARS_AMT FROM HRMS_DEBIT_HEAD "
			+" LEFT JOIN  HRMS_ARREARS_DEBIT_"+year+" ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE "
			+" AND ARREARS_EMP_ID="+bean.getEmpID()+" AND ARREARS_CODE=("+innerQueryArrears+")) " 
			+" LEFT JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE )"
			+" WHERE  DEBIT_PAYFLAG='Y' AND DEBIT_PERIODICITY='M' ORDER BY DEBIT_CODE";
		
		Object debitData [][]= null;
		if(type.equals("Sal")){
			
		debitData  = getSqlModel().getSingleResult(debitQuery);
		
		}else 
			
		debitData  = getSqlModel().getSingleResult(debitQueryArrears);
		

	//  collection of the credit data
		
	try {
		for (int i = 0; i < debitData.length; i++) {
			try {
				salData[creditLength + i][0] = checkNull(
						String.valueOf(debitData[i][0]), "");
				salData[creditLength + i][j] = checkNull(
						String.valueOf(debitData[i][1]), "-");
			} catch (Exception e) {
				salData[creditLength + i][0] = checkNull(
						String.valueOf(debitData[i][0]), "");
				salData[creditLength + i][j] = "";
			}

		}// end of for loop
	} catch (Exception e) {
		e.printStackTrace();
		//logger.error(e);
	}
	
	} // end of for loop
		return salData;
	}
	/**
	 * @Method checkNull
	 * To check whether the value is null or not
	 * @param bean
	 * @param response
	 * @param request
	 * 
	 */
	public String checkNull(String result,String sign) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else if (result.equals("0"))
			return "0";
		else {
			return sign+result;
		}	//end of else
	}
	
	public Object [][] calculateSalDataForSettlement(WageReport bean, int totalLength, int length, Object [][]salData){
		/*
		 * 
		 * Calculate Credit data
		 * 
		 * 
		 */
		for (int j = 1; j <= 12; j++) {
			int month =(j+3) % 12;
			if (month==0)
				month=12;
			String year = bean.getFromYear();
			if(month < 4)
				year = bean.getToYear();
			
		String settlementAmtQuery= "SELECT CREDIT_CODE, SETTL_AMT FROM HRMS_CREDIT_HEAD "
							+" LEFT JOIN HRMS_SETTL_CREDITS ON(HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
							+" LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
							+" LEFT JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
							+" WHERE CREDIT_PAYFLAG='Y' AND CREDIT_PERIODICITY='M' AND RESIGN_EMP ="+bean.getEmpID()+" AND SETTL_MTH="+month+" AND SETTL_YEAR="+year;
		
		
		Object[][] settlementAmtData = getSqlModel().getSingleResult(
					settlementAmtQuery);

			if (settlementAmtData != null || settlementAmtData.length != 0) {

				for (int i = 0; i < settlementAmtData.length; i++) {

					for (int k = 0; k < length; k++) {
						/*
						 * check for the credit code, if credit code matches
						 * then add in the salData
						 * 
						 */
						if (String.valueOf(salData[k][0]).equals(
								String.valueOf(settlementAmtData[i][0]))) {
							if (!String.valueOf(salData[k][j]).equals(""))
								try {
									salData[k][j] = Integer.parseInt(String
											.valueOf(salData[k][j]))
											+ Integer
													.parseInt(String
															.valueOf(settlementAmtData[i][1]));
								} catch (Exception e) {
									// TODO: handle exception
								}
							else {
								salData[k][j] = String
										.valueOf(settlementAmtData[i][1]);
							}
						} // end of if
					} // end of for loop
				} // end of for loop
			} // end of if statement
		} // end of for loop
		/*
		 * 
		 * Calculate Debit data
		 * 
		 * 
		 */
		for (int j = 1; j <= 12; j++) {
			int month =(j+3) % 12;
			if (month==0)
				month=12;
			String year = bean.getFromYear();
			if(month < 4)
				year = bean.getToYear();

		String settlementAmtQuery= "SELECT DEBIT_CODE, SETTL_AMT FROM HRMS_DEBIT_HEAD "
							+" LEFT JOIN HRMS_SETTL_DEBITS ON(HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
							+" LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE)"
							+" LEFT JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
							+" WHERE DEBIT_PAYFLAG='Y' AND DEBIT_PERIODICITY='M' AND RESIGN_EMP ="+bean.getEmpID()+" AND SETTL_MTH="+month+" AND SETTL_YEAR="+year;
		
		
		Object [][]settlementAmtData = getSqlModel().getSingleResult(settlementAmtQuery);
		
			if(settlementAmtData != null || settlementAmtData.length !=0){
				
				for (int i = 0; i < settlementAmtData.length; i++) {
					
						for (int k = length; k < totalLength; k++) {
							/*
							 * check for the credit code, if credit code matches then add in the salData
							 * 
							 */
							if(String.valueOf(salData [k][0]).equals(String.valueOf(settlementAmtData[i][0]))){
								
								if(!String.valueOf(salData [k][j]).equals(""))
								try{
								salData [k][j]= Integer.parseInt(String.valueOf(salData [k][j]))-Integer.parseInt(String.valueOf(settlementAmtData[i][1]));
								}catch (Exception e) {
									// TODO: handle exception
								}
								else {
									
									salData [k][j]= checkNull(String.valueOf(settlementAmtData[i][1]),"-");
								}
							}		// end of if
						}		// end of for loop
				}			// end of for loop
			}			// end of if statement
		}				// end of for loop
		return salData;
	}
}
