package org.paradyne.model.payroll.incometax;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.StmtTaxCalculation;
import org.paradyne.lib.ModelBase;

public class StmtTaxCalulationModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(StmtTaxCalulationModel.class);

	public void getReport(StmtTaxCalculation stmt, HttpServletResponse response) {
		String frmYear = stmt.getFromYear();
		String toYear = stmt.getToYear();
		double year=0,sec10Amt=0;
		
		Object[][]processData = null,processData1=null,debitCode = null;
		String ledgerCode = "",ledgerCode1 = "";
		double taxPaidTillDate = 0;
		double taxPaidAmt = 0.00,taxPaidAmt1=0.00;
		
		try {
			String query = "SELECT TDS_DEBIT_CODE FROM HRMS_TAX_PARAMETER";
			debitCode = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in debitCode query",e);
		} //end of catch
		
		try {
			/**
			 * this processData contains the Ledger codes of the processed salary
			 * in processData----it is checked for April to December
			 * in processData1----it is checked forr Jan To March
			 */
			processData = retProcessData(4, 12, frmYear, stmt);
		} catch (Exception e) {
			logger.error("exception in processData",e);
		} //end of catch
		
		try {
			/**
			 * this processData contains the Ledger codes of the processed salary
			 * in processData----it is checked for April to December
			 * in processData1----it is checked forr Jan To March
			 */
			processData1 = retProcessData(1, 3, toYear, stmt);
		} catch (Exception e) {
			logger.error("exception in processData",e);
		} //end of catch
		
		/**
		 * this for loop is for salary processed in April To December
		 */
		/*if (processData == null) {

		}//end of if
		else if (processData.length == 0) {

		}//end of else if
		else{
			ledgerCode = getledgerCode(processData);
			taxPaidAmt = getTaxPaidAmt(frmYear,String.valueOf(debitCode[0][0]),ledgerCode);
		} //end of else
*/		
		/**
		 * this for loop is for salary processed in Jan to March
		 */
		/*if (processData1 == null) {

		}//end of if
		else if (processData1.length == 0) {

		}//end of else if
		else{
			ledgerCode1 = getledgerCode(processData1);
			taxPaidAmt1 = getTaxPaidAmt(toYear,String.valueOf(debitCode[0][0]),ledgerCode1);
		} //end of else
		
		taxPaidTillDate = taxPaidAmt + taxPaidAmt1;*/

		String reportType="Pdf";	
		
		String reportName="Tax Statment of "+stmt.getEmpName()+" for "+stmt.getFromYear()+"-"+stmt.getToYear();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		rg.setFName(reportName+"."+reportType);
		rg.genHeader("");
		rg.addText("", 0, 0, 0);

		String companyQuery = " SELECT COMPANY_NAME, nvl(COMPANY_ADDRESS,' '),HRMS_LOCATION.LOCATION_NAME,COMPANY_PIN  "
				+ " FROM hrms_company "
				+ " inner join hrms_location on (hrms_location.LOCATION_CODE = hrms_company.COMPANY_CITYID) ";
		Object[][] companyData = getSqlModel().getSingleResult(companyQuery);

		rg.addTextBold("" + String.valueOf(companyData[0][0]) + "", 0, 1, 0);
		rg.addTextBold("" + String.valueOf(companyData[0][1]) + "", 0, 1, 0);
		

///////////////////////////////////////// NEW CODE ////////////////////////////////////////////////////////
		
		Object[][] taxData=null;
		
		try {
			String taxQuery = "select NVL(TDS_GROSS_SALARY,0),NVL(TDS_EXCEMPTIONS,0),NVL(TDS_REBATE,0),NVL(TDS_OTH_INCOME,0), "
					+ " NVL(TDS_DEDUCTIONS,0),NVL(TDS_TAXABLE_INCOME,0),NVL(TDS_TOTAL_TAX,0),NVL(TDS_EDUC_CESS,0), "
					+ " NVL(TDS_SURCHARGE,0),NVL(TDS_NET_TAX,0),NVL(TDS_TAXPERMONTH,0),NVL(TDS_TAX_PAID,0), "
					+ " NVL(TDS_PROF_TAX,0),CASE WHEN EMP_REGULAR_DATE <= TO_DATE('01-04-"+stmt.getFromYear()+"','DD-MM-YYYY') "
					+" THEN '01-04-"+stmt.getFromYear()+"' "
					+" WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"+stmt.getFromYear()+"','DD-MM-YYYY') "
					+" THEN TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "
					+" END AS JOINING FROM HRMS_TDS  "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID "
					+ " WHERE TDS_EMP_ID="
					+ stmt.getEmpId()
					+ " and TDS_FROM_YEAR="
					+ stmt.getFromYear()
					+ " and TDS_TO_YEAR=" + stmt.getToYear() + " ";
			taxData = getSqlModel().getSingleResult(taxQuery);
		} catch (Exception e) {
			logger.error("error in taxData query   "+e);
		}
		if(taxData == null){
			String text3[]={"TDS Calculation not saved"};
			int style3[]={2};
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addFormatedText(text3,style3,0,1,40);
		} //end of if
		else if(!(taxData.length >0)){
			String text3[]={"TDS Calculation not saved"};
			int style3[]={2};
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addText("\n",0,0,0);
			rg.addFormatedText(text3,style3,0,1,40);
		}// end of else if
		else{ //else 'a'
			try{
				
				Object[][]settleData = null;
				try {
					String settleQuery = "SELECT TO_CHAR(SETTL_SEPRDT,'DD/MM/YYYY') FROM HRMS_SETTL_HDR "
					+" WHERE SETTL_RESGNO IN(SELECT MAX(RESIGN_CODE) FROM HRMS_RESIGN WHERE RESIGN_EMP = "+stmt.getEmpId()+") "
					+" AND SETTL_LOCKFLAG = 'Y' AND  "
					+" SETTL_SETTLDT BETWEEN TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') AND TO_DATE('31-03-"+toYear+"','DD-MM-YYYY')";
					settleData = getSqlModel().getSingleResult(settleQuery);
				} catch (Exception e) {
					logger.error("exception in settle query",e);
				} //end of catch
				String periodTo = "";
				if(settleData !=null && settleData.length > 0){
					periodTo = String.valueOf(settleData[0][0]);
				} //end of if
				else{
					periodTo = "31-03-"+stmt.getToYear()+"";
				}
				
				
				String s = "\n Statement Of Tax Calculation of  " + " "
				+ stmt.getEmpName() + "   ";	
				rg.addTextBold(s, 0, 1, 0);
				rg.addTextBold("Part A                                  \t\t\t\t "
						+ " Financial Year" + stmt.getFromYear() + " " + "-" + " "
						+ stmt.getToYear() + " ", 0, 0, 0);
				rg.addTextBold("Period From - "+taxData[0][13]+" To - "+periodTo+" ", 0, 1, 0);
				
				Object taxTotaRs[][]= new Object[1][4];
				taxTotaRs[0][0]="";
				taxTotaRs[0][1]="(Rs.)";
				taxTotaRs[0][2]="(Rs.)";
				taxTotaRs[0][3]="(Rs.)";
				int [] cellWidthRs={50,15,15,20};
				int [] cellAlignRs={0,2,2,2};
				rg.tableBody1(taxTotaRs,cellWidthRs,cellAlignRs);
				
				Object [][]perquisiteData = null;
				double totalPerqAmt=0;
				try {
					String perqQuery = "SELECT NVL(TDS_PERQ_AMOUNT,0)FROM HRMS_TDS_PERQ "
							+ " LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_TDS_PERQ.TDS_PERQ_HEAD) "
							+ " WHERE TDS_EMP_ID="
							+ stmt.getEmpId()
							+ " AND TDS_YEAR_FROM="
							+ frmYear
							+ " AND TDS_YEAR_TO="
							+ toYear + "";
					perquisiteData = getSqlModel().getSingleResult(perqQuery);
				} catch (Exception e) {
					logger.error("Exception in perq query  "+e);
				} //end of catch
				
				if(perquisiteData == null){
					
				} //end of if
				else if(!(perquisiteData.length >0)){
					
				} //end of else if
				else{
					try {
						for (int i = 0; i < perquisiteData.length; i++) {
							totalPerqAmt += Double.parseDouble(String
									.valueOf(perquisiteData[i][0]));
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in perquisiteData loop",e);
					} //end of catch
				} //end of else
				
				Object[][] otherIncomeData = null;
				String add="",amt="",total="";
				double totalOtherAmt=0;
				
				try {
					String otherQuery = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,NVL(INV_VALID_AMOUNT,0),INV_OTHER_FLAG "
					+" FROM HRMS_TAX_INVESTMENT "
					+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
					+" WHERE INV_CHAPTER = 'OTHER' and INV_FINYEAR_FROM="+ frmYear+ " and INV_FINYEAR_TO="+ toYear
					+ "  and EMP_ID="+ stmt.getEmpId();
					otherIncomeData = getSqlModel().getSingleResult(otherQuery);
				} catch (Exception e) {
					logger.error("Exception in creating otherIncomeData--"+e);
				} //end of catch
				
				if(otherIncomeData == null){
					add = "7. Add:Any other income reported by the employee";
				} //end of if
				else if(otherIncomeData.length == 0){
					add = "7. Add:Any other income reported by the employee";
				} //end of else if
				else{
					add = "7. Add:Any other income reported by the employee";
					try {
						for (int i = 0; i < otherIncomeData.length; i++) {
							add += "\n" + String.valueOf(otherIncomeData[i][1]);
							amt += "\n" + String.valueOf(otherIncomeData[i][2]);
								if(String.valueOf(otherIncomeData[i][3]).equals("D")){
									totalOtherAmt -= Double.parseDouble(String
											.valueOf(otherIncomeData[i][2]));
								} //end of if
								else{
									totalOtherAmt += Double.parseDouble(String
											.valueOf(otherIncomeData[i][2]));
								} //end of else
							
							total += "\n";
						} //end of loop
					} catch (Exception e) {
						logger.error("Exception in calculating otherIncomeData--"+e);
					} //end of catch
				} //end of else
				
				Object taxTotal[][]= new Object[1][4];
				taxTotal[0][0]="1. Gross salary\n" +
							   "   (a) Salary as per provisions contained in\n" +
							   "        section 17(1)\n" +
							   "   (b) Value of perquisites under section 17(2)\n" +
							   "	       (as per Form No.12BA,wherever\n" +
							   "        applicable)\n" +
							   "   (c) Profits in lieu of salary under section\n" +
							   "	       17(3)(as per Form No.12BA,wherever\n" +
							   "        applicable)\n" +
							   "   (d) Total";
				if(String.valueOf(taxData[0][0]).trim().equals("") || String.valueOf(taxData[0][0]).trim().equals("null")){
					taxTotal[0][1]="\nNIL\n\n"+0+"\n\n\nNIL";
					taxTotal[0][2]="\n\n\n\n\n\n\n\n\nNIL";
				} //end of if
				else{
					taxTotal[0][1]="\n"+String.valueOf(Math.round((Double.parseDouble(String.valueOf(taxData[0][0])))) - totalPerqAmt - totalOtherAmt)+"\n\n"+Math.round(totalPerqAmt)+"\n\n\nNIL";
					taxTotal[0][2]="\n\n\n\n\n\n\n\n\n"+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxData[0][0])) - totalOtherAmt))+"";
				} //end of else
				taxTotal[0][3]="";
				int [] cellWidth={50,15,15,20};
				int [] cellAlign={0,2,2,2};
				rg.tableBody1(taxTotal,cellWidth,cellAlign);
				
				Object[][] taxParameter=null;
				
				try {
					String tdsParaQuery = "SELECT TDS_HRAEXEMPT_INVCODE,TDS_EMPPF_INVCODE,TDS_REBATEMAX_AMOUNT,TDS_SIGNING_AUTH,EMP_TOKEN, "
					+" NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+" NVL(RANK_NAME,' ') FROM HRMS_TAX_PARAMETER  "
					+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TAX_PARAMETER.TDS_SIGNING_AUTH) " 
					+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  "
					+" WHERE TDS_CODE = (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
					taxParameter = getSqlModel().getSingleResult(tdsParaQuery);
				} catch (Exception e) {
					logger.error("error in taxParameter query",e);
				}
				
				Object[][] empInvHra=null;
				try {
					if (taxParameter != null && taxParameter.length > 0) {
						String empInvQuery = "SELECT INV_CODE,INV_VALID_AMOUNT FROM HRMS_EMP_INVESTMENT "
								+ " WHERE EMP_ID = "
								+ stmt.getEmpId()
								+ " AND INV_FINYEAR_FROM="
								+ frmYear
								+ " AND INV_FINYEAR_TO="
								+ toYear
								+ " AND INV_CODE = " + taxParameter[0][0] + "";
						empInvHra = getSqlModel().getSingleResult(empInvQuery);
					} //end of if
				} catch (Exception e) {
					logger.error("error in empInvHra query",e);
				} //end of catch
				
				Object[][] exemptInvData=null;
				try {
					String exemptQuery = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,SUM(INV_VALID_AMOUNT ) "
							+ " FROM HRMS_TAX_INVESTMENT  "
							+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = "
							+ " HRMS_TAX_INVESTMENT.INV_CODE) "
							+ " WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10  "
							+ " and INV_FINYEAR_FROM="
							+ frmYear
							+ " and INV_FINYEAR_TO="
							+ toYear
							+ " "
							+ " and EMP_ID="
							+ stmt.getEmpId()
							+ ""
							+ " GROUP BY HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION ";
					exemptInvData = getSqlModel().getSingleResult(exemptQuery);
				} catch (Exception e) {
					logger.error("error in exemptInvData query   "+e);
				} //end of catch
				
				Object exemptData[][]= new Object[exemptInvData.length+1][4];
				exemptData[0][0]="2. Less:Allowance to the exempt under\n" +
							   "    section 10";
				exemptData[0][1]="";
				exemptData[0][2]="";
				exemptData[0][3]="";
				
				if(exemptData == null){
					
				} //end of if
				else if(!(exemptData.length >0)){
				
				} //end of else if
				else{
					try {
						for (int i = 0; i < exemptInvData.length; i++) {
							exemptData[i + 1][0] = "" + exemptInvData[i][1] + "";
							
							if (empInvHra != null && empInvHra.length > 0 && String.valueOf(exemptInvData[i][0]).trim()
											.equals(String.valueOf(empInvHra[0][0]).trim())) {
								
								exemptData[i + 1][1] = "" + empInvHra[0][1] + "";
							} //end of if 
							else {
								if (String.valueOf(exemptInvData[i][3]).trim()
										.equals("")
										|| String.valueOf(exemptInvData[i][3])
												.trim().equals("null")) {
									exemptData[i + 1][1] = "0";
								} //end of if
								else {
									exemptData[i + 1][1] = "" + exemptInvData[i][3]
											+ "";
								} //end of else
							} //end of else
							exemptData[i + 1][2] = "";
							exemptData[i + 1][3] = "";
							sec10Amt = sec10Amt
									+ Double.parseDouble(String
											.valueOf(exemptData[i + 1][1]));
						} //end of loop
					} catch (Exception e) {
						logger.error("error in calculating exemptData   "+e);
					} //end of castch
				} //end of else
				int [] cellWidthExempt={50,15,15,20};
				int [] cellAlignExempt={0,2,2,2};
				rg.tableBody1(exemptData,cellWidthExempt,cellAlignExempt);
				
				Object taxTotal1[][]= new Object[8][4];
				taxTotal1[0][0]="3. Balance (1-2)";
				taxTotal1[0][1]="";
				taxTotal1[0][2]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal[0][2])) - sec10Amt));
				taxTotal1[0][3]="";
				
				taxTotal1[1][0]="4. Deductions:";
				taxTotal1[1][1]="";
				taxTotal1[1][2]="";
				taxTotal1[1][3]="";
				
				taxTotal1[2][0]="     a)Entertainment allowance      NIL";
				taxTotal1[2][1]="";
				taxTotal1[2][2]="";
				taxTotal1[2][3]="";
				
				taxTotal1[3][0]="     b)Tax on Employment             "+taxData[0][12]+"";
				taxTotal1[3][1]="";
				taxTotal1[3][2]="";
				taxTotal1[3][3]="";
				
				taxTotal1[4][0]="5. Aggregate of 4(a) and 4(b)";
				taxTotal1[4][1]=""+taxData[0][12]+"";
				taxTotal1[4][2]="";
				taxTotal1[4][3]="";
				
				taxTotal1[5][0]="6. INCOME CHARGEABLE UNDER THE HEAD\n" +
								"   'SALARIES' (3-5)";
				taxTotal1[5][1]="";
				taxTotal1[5][2]="";
				taxTotal1[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal1[0][2])) - Double.parseDouble(String.valueOf(taxTotal1[4][1]))));
				
				taxTotal1[6][0]=add;
				taxTotal1[6][1]=amt;
				taxTotal1[6][2]="";
				taxTotal1[6][3]=total + totalOtherAmt;
				
				
				taxTotal1[7][0]="8. GROSS TOTAL INCOME (6+7)";
				taxTotal1[7][1]="";
				taxTotal1[7][2]="";
				taxTotal1[7][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal1[5][3])) + totalOtherAmt));
				int [] cellWidth1={50,15,15,20};
				int [] cellAlign1={0,2,2,2};
				rg.tableBody1(taxTotal1,cellWidth1,cellAlign1);
				rg.addText("", 0, 0, 0);
				
				Object[][] invDeducData=null;
				try {
					String invDeductionQuery = "SELECT  INV_CODE,INV_NAME,ROWNUM FROM HRMS_TAX_INVESTMENT "
							+ " WHERE INV_SECTION ='80C' ORDER BY INV_CODE";
					invDeducData = getSqlModel().getSingleResult(invDeductionQuery);
				} catch (Exception e) {
					logger.error("Exception in creating invDeducData--"+e);
				} //end of catch

				Object[][] invDeducAmt=null;
				try {
					String invDeductionAmtQuery = "SELECT INV_CODE,NVL(INV_VALID_AMOUNT,0) FROM HRMS_EMP_INVESTMENT  WHERE EMP_ID = "
							+ stmt.getEmpId()
							+ " "
							+ " AND INV_FINYEAR_FROM="
							+ frmYear
							+ " AND INV_FINYEAR_TO="
							+ toYear + " ";
					invDeducAmt = getSqlModel().getSingleResult(
							invDeductionAmtQuery);
				} catch (Exception e) {
					logger.error("Exception in invDeducAmt query--"+e);
				} //end of catch
				
				double deductionTotal=0;
				Object taxTotal2[][] =	null;
				if(invDeducData!=null && invDeducData.length>0){
					taxTotal2= new Object[invDeducData.length + 7][5];
				} //end of if
				else{
					taxTotal2= new Object[3][5];
				} //end of else
				taxTotal2[0][0]="9. DEDUCTIONS UNDER\n" +
				"   CHAPTER VI-A";
				taxTotal2[0][1]="";
				taxTotal2[0][2]="";
				taxTotal2[0][3]="";
				taxTotal2[0][4]="";
				
				taxTotal2[1][0]="A) 80C, 80CCC AND 80CCD";
				taxTotal2[1][1]="GROSS AMOUNT";
				taxTotal2[1][2]="QUALIFYING AMOUNT";
				taxTotal2[1][3]="DEDUCTIBLE AMOUNT";
				taxTotal2[1][4]="";
				
				taxTotal2[2][0]="a) Section 80C";
				taxTotal2[2][1]="(Rs.)";
				taxTotal2[2][2]="(Rs.)";
				taxTotal2[2][3]="(Rs.)";
				taxTotal2[2][4]="";
				
				if(invDeducData == null){
					
				} //end of if
				else if(invDeducData.length == 0){
					
				} //end of else if
				else{
					try {
						for (int i = 0; i < invDeducData.length; i++) {

							taxTotal2[i + 3][0] = "   " + invDeducData[i][2]
									+ "." + invDeducData[i][1] + "";
							if (invDeducAmt == null) {

							} //end of if
							else if (invDeducAmt.length == 0) {

							} //end of else if
							else {
								try{
									for (int j = 0; j < invDeducAmt.length; j++) {
										if (String.valueOf(invDeducData[i][0])
												.trim().equals(String.valueOf(invDeducAmt[j][0]).trim())) {
											taxTotal2[i + 3][1] = Math.round(Double.parseDouble(String
																	.valueOf(invDeducAmt[j][1])));
										} //end of if
									} //end of loop
								} catch (Exception e) {
									logger.error("Exception in invDeducAmt loop",e);
								} //end of catch
							} //end of else

							taxTotal2[i + 3][2] = "";
							taxTotal2[i + 3][3] = "";
							taxTotal2[i + 3][4] = "";
							if (String.valueOf(taxTotal2[i + 3][1]).equals("")
									|| String.valueOf(taxTotal2[i + 3][1])
											.equals("null")) {
								taxTotal2[i + 3][1] = 0;
							} //end of if
							deductionTotal = Math.round(deductionTotal
									+ Double.parseDouble(String
											.valueOf(taxTotal2[i + 3][1])));
						} //end of loop
					} catch (Exception e) {
						logger.error("Exception in invDeducData--"+e);
					} //end of catch
				} //end of else
				
				if (taxParameter != null && taxParameter.length > 0) {
					if(deductionTotal > Double.parseDouble(String.valueOf(taxParameter[0][2])) ){
						deductionTotal = Double.parseDouble(String.valueOf(taxParameter[0][2]));
					} //end of if
				} //end of if
					taxTotal2[invDeducData.length + 3][0]="Total [1 to "+invDeducData.length+"]";
					taxTotal2[invDeducData.length + 3][1]=""+Math.round(deductionTotal)+"";
					taxTotal2[invDeducData.length + 3][2]=""+Math.round(deductionTotal)+"";
					taxTotal2[invDeducData.length + 3][3]=""+Math.round(deductionTotal)+"";
					taxTotal2[invDeducData.length + 3][4]="";
			
				Object[][] cccSection=null;
				double cccAmt=0,ccdAmt=0;
				
				try {
					String ccdquery = "SELECT  HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION "
							+ " FROM HRMS_TAX_INVESTMENT "
							+ " WHERE INV_SECTION IN('80CCC','80CCCD')";
					cccSection = getSqlModel().getSingleResult(ccdquery);
				} catch (Exception e) {
					logger.error("execption in cccSection----"+e);
				} //end of catch
				
				try {	
					if(cccSection == null){
						
					} //end of if
					else if(cccSection.length == 0){
						
					} //end of else if
					else{
						try{
							for (int j = 0; j < cccSection.length; j++) {
								 if(cccSection[j][2].equals("80CCC")){
									 if(invDeducAmt == null){
											
										} //end of if
										else if(invDeducAmt.length == 0){
											
										} //end of else if
										else{
											try{
												for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
													 if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][0]).trim())){
														 cccAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][1]))); 
													 } //end of if
												} //end of loop
											} catch (Exception e) {
												logger.error("execption in invDeducAmt loop",e);
											} //end of catch
										} //end of else
								 } //end of if
								 
								 if(cccSection[j][2].equals("80CCD")){
									 if(invDeducAmt == null){
											
										} //end of if
										else if(invDeducAmt.length == 0){
											
										} //end of else if
										else{
											try{
												for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
													 if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][0]).trim())){
														 ccdAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][1]))); 
													 } //end of if
												} //end of loop
											} catch (Exception e) {
												logger.error("execption in invDeducAmt loop",e);
											} //end of catch
										} //end of else
								 } //end of if
							} //end of loop
						} catch (Exception e) {
							logger.error("execption in cccSection----"+e);
						} //end of catch
					} //end of else
					
				} catch (Exception e) {
					logger.error("execption in calculating the sum of ccc & ccd amt----"+e);
				} //end of catch
				
			//	Object taxTotaNote[][]= new Object[2][5];
				taxTotal2[invDeducData.length + 3+1][0]="b) Section 80CCC";
				taxTotal2[invDeducData.length + 3+1][1]=""+Math.round(cccAmt)+"";
				taxTotal2[invDeducData.length + 3+1][2]=""+Math.round(cccAmt)+"";
				taxTotal2[invDeducData.length + 3+1][3]=""+Math.round(cccAmt)+"";
				taxTotal2[invDeducData.length + 3+1][4]="";
				
				taxTotal2[invDeducData.length + 3+2][0]="c) Section 80CCD \n Note: 1.aggregate amount deductible under section 80C shall not exceed one lakh rupees \n" +
				"         2.aggregate amount deductible under sections, i.e., 80C,80CCC and 80CCD,shall not \n " +
				"exceed one lakh rupees";
				taxTotal2[invDeducData.length + 3+2][1]=""+Math.round(ccdAmt)+"";
				taxTotal2[invDeducData.length + 3+2][2]=""+Math.round(ccdAmt)+"";
				taxTotal2[invDeducData.length + 3+2][3]=""+Math.round(ccdAmt)+"";
				taxTotal2[invDeducData.length + 3+2][4]="";
				
				Object[][] otherSec = null;
				try{
					String otherSecQuery = "SELECT INV_AMOUNT,INV_SECTION FROM HRMS_EMP_INVESTMENT "
						+" LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "
						+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID = "+stmt.getEmpId()+"" +
						 " AND INV_FINYEAR_FROM = "+stmt.getFromYear()+" AND  INV_FINYEAR_TO = "+stmt.getToYear()+"";
					otherSec = getSqlModel().getSingleResult(otherSecQuery);
				} catch (Exception e) {
					// TODO: handle exception
				} //end of catch
				
				double invAmt80D=0,invAmt80E=0,invAmt80G=0;
				if((otherSec.length> 0)){
					invAmt80D=0;
					invAmt80E=0;
					invAmt80G=0;
				} //end of if
				
				try{
					for (int i = 0; i < otherSec.length; i++) {
						 if(String.valueOf(otherSec[i][1]).equals("80D")){
							 invAmt80D += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
						 } //end of if
						 else if(String.valueOf(otherSec[i][1]).equals("80E")){
							 invAmt80E += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
						 } //end of else if
						 else if(String.valueOf(otherSec[i][1]).equals("80G")){
							 invAmt80G += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
						 } //end of else if
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in otherSec",e);
				} //end of catch
				
				//Object taxTotalOther[][]= new Object[1][5];
				taxTotal2[invDeducData.length + 3+3][0] ="B) Other Sections (for e.g.     80E,80G etc)" +
									 "    \n" +
									 "    a) Section 80D \n" +
									 "    b) Section 80G \n" +
									 "    c) Section 80E";
				taxTotal2[invDeducData.length + 3+3][1] ="";
				taxTotal2[invDeducData.length + 3+3][2] ="\n\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80G)+"";
				taxTotal2[invDeducData.length + 3+3][3] ="\n\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80G)+"";
				taxTotal2[invDeducData.length + 3+3][4] ="";
				int [] cellWidth2={30,20,15,15,20};
				int [] cellAlign2={0,2,2,2,2};
				rg.tableBody1(taxTotal2,cellWidth2,cellAlign2);
				
				Object taxTotal3[][]= new Object[10][4];
				taxTotal3[0][0]="10.Aggregate of deductible amount under Chapter VI-A";
				taxTotal3[0][1]="";
				taxTotal3[0][2]="";
				taxTotal3[0][3]=""+(Math.round(deductionTotal) + Math.round(invAmt80D) + Math.round(invAmt80E) + 
						Math.round(invAmt80G) + Math.round(cccAmt) + Math.round(ccdAmt))+"";
				logger.info("String.valueOf(taxTotal1[1][3])===="+String.valueOf(taxTotal1[7][3]));
				logger.info("String.valueOf(taxTotal3[0][3])===="+String.valueOf(taxTotal3[0][3]));
				taxTotal3[1][0]="11.TOTAL INCOME (R/o)(8-10)";
				taxTotal3[1][1]="";
				taxTotal3[1][2]="";
				double z = Math.round(Double.parseDouble(String.valueOf(taxTotal1[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])));
				double mod=Math.round(Double.parseDouble(String.valueOf(taxTotal1[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])))%10;
				if(mod >0)
				{
					z=(z-mod)+10;
				} //end of if
				
				if(z<0)
				{
					logger.info("in ifc z   :");
					taxTotal3[1][3]=0;
				} //end of if
				else
					logger.info("z in else   :"+z);
				taxTotal3[1][3]=String.valueOf(Math.round(z));
				
				taxTotal3[2][0]="12.TAX ON TOTAL INCOME(Net)";
				taxTotal3[2][1]="";
				taxTotal3[2][2]="";
				taxTotal3[2][3]=""+taxData[0][6]+"";
				
				taxTotal3[3][0]="13.Surcharge(on tax computed at S.No.12)";
				taxTotal3[3][1]="";
				taxTotal3[3][2]="";
				taxTotal3[3][3]=""+taxData[0][8]+"";
				
				taxTotal3[4][0]="14.Education Cess(on tax at S.No.12 and Surcharge at S.No.13)";
				taxTotal3[4][1]="";
				taxTotal3[4][2]="";
				taxTotal3[4][3]=""+taxData[0][7]+"";
				
				taxTotal3[5][0]="15.TAX PAYABLE (12+13+14)";
				taxTotal3[5][1]="";
				taxTotal3[5][2]="";
				taxTotal3[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[2][3])) + Double.parseDouble(String.valueOf(taxTotal3[3][3])) + Double.parseDouble(String.valueOf(taxTotal3[4][3]))));
				
				taxTotal3[6][0]="16.Relief Under section 89(attach details)";
				taxTotal3[6][1]="";
				taxTotal3[6][2]="";
				taxTotal3[6][3]="NIL";
				
				taxTotal3[7][0]="17.TAX PAYABLE(15-16)";
				taxTotal3[7][1]="";
				taxTotal3[7][2]="";
				taxTotal3[7][3]=""+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[5][3]))))+"";
				
				 Object[][] challanDataMarDec = null;
				 try{
					 String challanQueryMarDec="SELECT rownum,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,BANK_BSR_CODE, "
					 +" TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE "
					 +" FROM HRMS_TAX_CHALLAN_DTL  "
					 +" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
					 +" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK  "
					 +" WHERE EMP_ID="+stmt.getEmpId()+" AND CHALLAN_YEAR="+stmt.getFromYear()+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12)";
					 challanDataMarDec = getSqlModel().getSingleResult(challanQueryMarDec);
				 } catch (Exception e) {
						logger.error("exception in challanDataMarDec",e);
				 } //end of catch
				 
				 Object[][] challanDataJanMar = null;
				 try{
					 String challanQueryJanMar=" SELECT '',CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,BANK_BSR_CODE, "
					 +" TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE  "
					 +" FROM HRMS_TAX_CHALLAN_DTL  "
					 +" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
					 +" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK  "
					 +" WHERE EMP_ID="+stmt.getEmpId()+" AND CHALLAN_YEAR="+stmt.getToYear()+" AND CHALLAN_MONTH IN(1,2,3)";
					 challanDataJanMar = getSqlModel().getSingleResult(challanQueryJanMar);
				 } catch (Exception e) {
						logger.error("exception in challanDataJanMar",e);
				 } //end of catch
				 
				 double finalTotTaxAmt=0,finalTotTaxAmt1=0,finalAmt=0;
				 int rowCount=0;
				 
				 if(challanDataMarDec.length >0){
					 try {
							for (int i = 0; i < challanDataMarDec.length; i++) {
								finalTotTaxAmt += Double.parseDouble(String.valueOf(challanDataMarDec[i][4]));
								rowCount++;
							} //end of loop
						} catch (Exception e) {
							logger.error("exception in challanDataMarDec loop",e);
						} //end of catch
				 }
				 
				 if(challanDataJanMar.length >0){
					 try {
							for (int i = 0; i < challanDataJanMar.length; i++) {
								finalTotTaxAmt1 += Double.parseDouble(String.valueOf(challanDataJanMar[i][4]));
								challanDataJanMar[i][0] = rowCount + i + 1;
							} //end of loop
						} catch (Exception e) {
							logger.error("exception in challanDataJanMar loop",e);
						} //end of catch
				 }
				 finalAmt = finalTotTaxAmt + finalTotTaxAmt1;
				
				
				taxTotal3[8][0]="18.LESS: (a)TAX DEDUCTED AT SOURCE U/S       192(1).\n" +
					"    (b) Tax Paid By The Employer On Behalf Of     The Employee U/S 192(A) On Perquisites U/S    17(2).";
				taxTotal3[8][1]="";
				taxTotal3[8][2]=""+String.valueOf(Math.round(finalAmt))+"\n\nNIL";
				taxTotal3[8][3]="\n\n\n\n"+String.valueOf(Math.round(finalAmt))+"";
				
				taxTotal3[9][0]="19. TAX PAYABLE/REFUNDABLE (17-18)";
				taxTotal3[9][1]="";
				taxTotal3[9][2]="";
				taxTotal3[9][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[8][3]))));
				
				 int [] cellWidth3={50,15,15,20};
				 int [] cellAlign3={0,2,2,2};
				 rg.tableBody1(taxTotal3,cellWidth3,cellAlign3);
				 
				 /////////////------new tax challan table----/////////////////////////////
				 
				 	rg.addFormatedText("PartB",5,0,0,0);
					rg.addTextBold("Statment Of Actual Tax Deducted", 0, 0, 0);
				 
				 Object[][] taxChallan= new Object[1][9];
				 	taxChallan[0][0]="Sr No";
				 	taxChallan[0][1]="TDS (Rs.)";
					taxChallan[0][2]="Surcharge (Rs.)";
					taxChallan[0][3]="Education Cess (Rs.)";
					taxChallan[0][4]="Total Tax Deposited (Rs.)";
					taxChallan[0][5]="Cheque/DD No.";
					taxChallan[0][6]="BSR Code of Bank branch";
					taxChallan[0][7]="Date on Which Tax deposited";
					taxChallan[0][8]="Challan Identification No";
					
					 int [] cellWidthChallan={5,10,15,15,15,15,20,20,20};
					 int [] cellAlignChallan={1,1,1,1,1,1,1,1,1};
					 rg.tableBody1(taxChallan,cellWidthChallan,cellAlignChallan);
					
					if(challanDataMarDec.length >0){
						 int [] cellWidthAprToDec={5,10,15,15,15,15,20,20,20};
						 int [] cellAlignAprToDec={2,2,2,2,2,1,2,2,2};
						 rg.tableBody1(challanDataMarDec,cellWidthAprToDec,cellAlignAprToDec);
					} //end of if
					
					if(challanDataJanMar.length >0){
						 int [] cellWidthJanToMar={5,10,15,15,15,15,20,20,20};
						 int [] cellAlignJanToMar={2,2,2,2,2,1,2,2,2};
						 rg.tableBody1(challanDataJanMar,cellWidthJanToMar,cellAlignJanToMar);
					} //end of if
					
					 Object[][] taxChallanTotal= new Object[1][9];
					 taxChallanTotal[0][0]="";
					 taxChallanTotal[0][1]="";
					 taxChallanTotal[0][2]="TOTAL";
					 taxChallanTotal[0][3]="";
					 taxChallanTotal[0][4]=""+Math.round(finalAmt)+"";
					 taxChallanTotal[0][5]="";
					 taxChallanTotal[0][6]="";
					 taxChallanTotal[0][7]="";
					 taxChallanTotal[0][8]="";
					 
					 int [] cellWidthTotal={5,10,15,15,15,15,20,20,20};
					 int [] cellAlignTotal={2,2,2,2,2,2,2,2,2};
					 rg.tableBody1(taxChallanTotal,cellWidthTotal,cellAlignTotal);
				
			} catch (Exception e) {
				// TODO: handle exception
			} //end of final catch
			
		} //end of else 'a'
		rg.createReport(response);
	} //end of method
	
public Object[][] retProcessData(int frmMonth,int toMonth,String year,StmtTaxCalculation stmt) {
		
		Object[][] processData = null;
		
		try {
			String process = " SELECT LEDGER_CODE,LEDGER_MONTH FROM HRMS_SALARY_LEDGER "
					+ "WHERE LEDGER_YEAR="
					+ year
					+ " AND LEDGER_STATUS in('SAL_FINAL','SAL_START') AND LEDGER_MONTH>="
					+ frmMonth + " " + "AND LEDGER_MONTH<=" + toMonth + " ";
			processData = getSqlModel().getSingleResult(process);
		} catch (Exception e) {
			logger.error("exception in processData",e);
		} //end of catch
		return processData;
	} //end of method

public String getledgerCode(Object[][] processData){
	String ledgerCode="";
		try {
			for (int i = 0; i < processData.length; i++) {
				if (i == processData.length - 1) {
					ledgerCode += processData[i][0];
				} //end of if 
				else {
					ledgerCode += processData[i][0] + ",";
				} //end of else
			} //end of loop
		} catch (Exception e) {
			logger.error("exception in processData",e);
		} //end of catch
	return ledgerCode;
} //end of method

public double getTaxPaidAmt(String frmYear,String debitCode,String ledgerCode){
	Object[][]taxPaid = null;
	double taxAmt =0.00; 
	
	try {
		String query = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_DEBITS_"
				+ frmYear
				+ " WHERE SAL_DEBIT_CODE = "
				+ debitCode
				+ " AND  SAL_LEDGER_CODE IN ("
				+ ledgerCode + ")";
		taxPaid = getSqlModel().getSingleResult(query);
	} catch (Exception e) {
		logger.error("exception in taxPaid query",e);
	} //end of catch
	
	if(taxPaid == null){
		
	} //end of if
	else if(taxPaid.length == 0){
		
	} //end of else if
	else{
		taxAmt = Double.parseDouble(String.valueOf(taxPaid[0][0]));
	} //end of else
	
	return taxAmt;
} //end of method

} //end of class
