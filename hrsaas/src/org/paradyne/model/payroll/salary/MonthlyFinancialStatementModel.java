package org.paradyne.model.payroll.salary;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.MonthlyFinancialStatement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class MonthlyFinancialStatementModel extends ModelBase {

	public void report(MonthlyFinancialStatement bean,
			HttpServletResponse response) {
		String reportType = "Xls"; // report type
		String reportName = "Monthly Financial Statement";
		String month=bean.getMonth();
		String year=bean.getYear();
		String divisionId=bean.getDivisionId();
		String ledgerCode="";
		String creditCode="";
		String debitCode="";
		String BAC="";
		String DA="";
		String HRA="";
		String MB="";
		String OT="";
		String LE="";
		String BONUS="";
		String GRATUITY="";
		String WORKMEN="";
		String empId="";
		
		String PF="";
		String ESI="";
		String TAX="";
		String ODEDUCT="";
		String RECOVERY="";
		String EMPEPF="";
		String EMPESIC="";
		
		
		ReportGenerator rg = new ReportGenerator(reportType, reportName + "_" + Utility.month(Integer.parseInt(month)) + "_" + year, "A4");
		
		/**
		 * GET LEDGER CODE
		 */
		String[] allCols={"Sr. No.","Employee Name","Unique Employee Number","Gender","Date of Payment","Bank Account Number","Bank IFSC Code","Employee EPFO Code","Employee ESIC Code","Basic","DA","HRA","Maternity Benefit","OverTime","Leave Encashment","Bonus","Gratuity","Workmen Compensation","Other Allowances","Total Payment","PF","ESI","Tax","Other","Recovery","Net Income","Employer Contribution EPF","Employer Contribution ESIC"};
		String[] format={"","","","","","000000000000","","","","","","","","","","","",""," ","","","","","","","","",""};
		
		
		
		Object[][]data= null;
		int[] cellWidth = {5,30,25,10,18,25,25,25,25,10,10,10,20,15,20,10,10,25,20,15,10,10,10,10,15,15,30,30};	
		int[] cellAln = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		String ledgerQuery="SELECT  LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month+"  AND LEDGER_YEAR="+year+"  AND LEDGER_DIVISION="+divisionId+" ";
		Object[][]ledObj=getSqlModel().getSingleResult(ledgerQuery);
		/**
		 * GET FINANCIAL STATEMENT SETTING
		 */
		String settingQuery="SELECT NVL(BASIC_CODE,0), NVL(DA_CODE,0), NVL(HRA_CODE,0), NVL(MB_CODE,0), NVL(OT_CODE,0), NVL(LE_CODE,0), NVL(BONUS_CODE,0), NVL(GRATUITY_CODE,0), NVL(WRK_CMP_CODE,0), NVL(OTHER_CREDIT_CODE,0), NVL(PF_CODE,0), NVL(ESI_CODE,0), NVL(TAX_CODE,0), NVL(OTHER_DEBIT_CODE,0), NVL(RECOVERY_CODE,0), NVL(EMP_EPF_CODE,0), NVL(EMP_ESI_CODE,0) " 
							+"	FROM HRMS_MONTH_FNY_STATEMENT";
		Object[][]settingObj=getSqlModel().getSingleResult(settingQuery);
		if(settingObj!=null && settingObj.length>0){
			creditCode=settingObj[0][0]+","+settingObj[0][1]+","+settingObj[0][2]+","+settingObj[0][3]+","+settingObj[0][4]+","+settingObj[0][5]+","+settingObj[0][6]+","+settingObj[0][7]+","+settingObj[0][8]+","+settingObj[0][9];
		
			BAC=String.valueOf(settingObj[0][0]);
			DA=String.valueOf(settingObj[0][1]);
			HRA=String.valueOf(settingObj[0][2]);
			MB=String.valueOf(settingObj[0][3]);
			OT=String.valueOf(settingObj[0][4]);
			LE=String.valueOf(settingObj[0][5]);
			BONUS=String.valueOf(settingObj[0][6]);
			GRATUITY=String.valueOf(settingObj[0][7]);
			WORKMEN=String.valueOf(settingObj[0][8]);
			
			debitCode=settingObj[0][10]+","+settingObj[0][11]+","+settingObj[0][12]+","+settingObj[0][13]+","+settingObj[0][14]+","+settingObj[0][15]+","+settingObj[0][16];
			
			PF=String.valueOf(settingObj[0][10]);
			ESI=String.valueOf(settingObj[0][11]);
			TAX=String.valueOf(settingObj[0][12]);
			ODEDUCT=String.valueOf(settingObj[0][13]);
			RECOVERY=String.valueOf(settingObj[0][14]);
			EMPEPF=String.valueOf(settingObj[0][15]);
			EMPESIC=String.valueOf(settingObj[0][16]);
			
			
			
		}		
		if(ledObj!=null && ledObj.length>0){
					ledgerCode=String.valueOf(ledObj[0][0]);
				String query="SELECT NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS EMPLOYEE_NAME, "
							+"		NVL(EMP_TOKEN,' ') ,DECODE(EMP_GENDER,'M','Male','F','Female'),TO_CHAR(SYSDATE,'DD-Mon-YY') AS DAT_OF_PAY,SAL_ACCNO_REGULAR "
							+"		,HRMS_EMP_OFFC.EMP_ID,NVL(SAL_TOTAL_CREDIT,0),NVL(SAL_NET_SALARY,0),NVL(BANK_IFSC_CODE,' '), NVL(SAL_GPFNO,' '),NVL(SAL_ESCINUMBER,' ') FROM HRMS_SALARY_"+year+"  "
							+"		INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)	"		
							+"		LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							+"     LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)	  "
							+"		WHERE EMP_STATUS='S' AND SAL_LEDGER_CODE="+ledgerCode+" ORDER BY EMP_TOKEN	ASC ";
			
				Object[][]obj=getSqlModel().getSingleResult(query);				
				
				/**
				 * GET CREDITS
				 */
				String creditQUery="SELECT EMP_ID,SAL_CREDIT_CODE,SAL_AMOUNT,EMP_ID||'#'||SAL_CREDIT_CODE FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_LEDGER_CODE="+ledgerCode+" AND SAL_CREDIT_CODE IN("+creditCode+")";
				HashMap<String, Object[][]>creditMap=getSqlModel().getSingleResultMap(creditQUery, 3, 2);
				
				/**
				 * OTHER ALLOWANCE
				 */
				String otherQUery="SELECT EMP_ID,SUM(SAL_AMOUNT),EMP_ID||'#'||EMP_ID FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_LEDGER_CODE="+ledgerCode+" AND SAL_CREDIT_CODE NOT IN("+creditCode+")  GROUP BY EMP_ID,EMP_ID||'#'||EMP_ID";
				HashMap<String, Object[][]>otherMap=getSqlModel().getSingleResultMap(otherQUery, 2, 2);
				

				/**
				 * GET DEBITS
				 */
				String debitQUery="SELECT EMP_ID,SAL_DEBIT_CODE,SAL_AMOUNT,EMP_ID||'#'||SAL_DEBIT_CODE FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_LEDGER_CODE="+ledgerCode+" AND SAL_DEBIT_CODE IN("+debitCode+")";
				HashMap<String, Object[][]>debitMap=getSqlModel().getSingleResultMap(debitQUery, 3, 2);
				/**
				 * OTHER DEBIT ALLOWANCE
				 */
				String otherDebitQUery="SELECT EMP_ID,SUM(SAL_AMOUNT),EMP_ID||'#'||EMP_ID FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_LEDGER_CODE="+ledgerCode+" AND SAL_DEBIT_CODE NOT IN("+debitCode+")  GROUP BY EMP_ID,EMP_ID||'#'||EMP_ID";
				HashMap<String, Object[][]>otherDebitMap=getSqlModel().getSingleResultMap(otherDebitQUery, 2, 2);
				
				double empESI=1.75;
				double compESI=4.75;
				
				Object[][] esi_data = null;
				try {
					String esi_query = " SELECT ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI "
							+ " WHERE TO_CHAR(ESI_DATE,'DD-MON-YYYY')  = (SELECT MAX(ESI_DATE) FROM HRMS_ESI "
							+ " WHERE TO_CHAR(ESI_DATE,'YYYY-MM') <= '"
							+ year
							+ "-" + month + "') ";
					esi_data = getSqlModel().getSingleResult(esi_query);

					 empESI=Double.parseDouble(String.valueOf(esi_data[0][0]));
					 compESI=Double.parseDouble(String.valueOf(esi_data[0][1]));
				} catch (Exception e) {
				}
				
				
				if(obj!=null && obj.length>0){
					data=new Object[obj.length][25+3];
					for (int i = 0; i < obj.length; i++) {
						data[i][0]=""+(i+1);
						data[i][1]=checkNull(String.valueOf(obj[i][0]));//EMPLOYEENAME
						data[i][2]=checkNull(String.valueOf(obj[i][1]));//TOKEN
						data[i][3]=checkNull(String.valueOf(obj[i][2]));//GENDER
						data[i][4]=checkNull(String.valueOf(obj[i][3]));//DATE OF PAYMENT
						data[i][5]=checkNull(String.valueOf(obj[i][4]));//BANK ACC NO
						
						data[i][6]=checkNull(String.valueOf(obj[i][8]));//BANK ACC NO
						data[i][7]=checkNull(String.valueOf(obj[i][9]));//BANK ACC NO
						data[i][8]=checkNull(String.valueOf(obj[i][10]));//BANK ACC NO
						
						
						data[i][6+3]="0";//BASIC
						data[i][7+3]="0";//DA
						data[i][8+3]="0";//HRA
						data[i][9+3]="0";//ML
						data[i][10+3]="0";//OT
						data[i][11+3]="0";//LE
						data[i][12+3]="0";//BONUS
						data[i][13+3]="0";//GRATUITY
						data[i][14+3]="0";//WORKMEN
						data[i][15+3]="0";//OTher alllw
						data[i][16+3]=String.valueOf(obj[i][6]);//TOTAL CREDIT
						data[i][22+3]=String.valueOf(obj[i][7]);//NET INCOME
					
						data[i][17+3]="0";
						data[i][18+3]="0";
						data[i][19+3]="0";
						data[i][20+3]="0";
						data[i][21+3]="0";
						data[i][23+3]="0";
						data[i][24+3]="0";
						
						
						empId=String.valueOf(obj[i][5]);
						if(creditMap!=null && creditMap.size()>0){
							//SET BASIC
							Object[][]ba=creditMap.get((empId+"#"+BAC));
							if(ba!=null && ba.length>0){
								data[i][6+3]=String.valueOf(ba[0][2]);//BASIC
							}
							//SET DA
							Object[][]da=creditMap.get((empId+"#"+DA));
							if(da!=null && da.length>0){
								data[i][7+3]=String.valueOf(da[0][2]);//DA
							}
							//SET hra
							Object[][]hra=creditMap.get((empId+"#"+HRA));
							if(hra!=null && hra.length>0){
								data[i][8+3]=String.valueOf(hra[0][2]);//DA
							}
							//SET mb
							Object[][]mb=creditMap.get((empId+"#"+MB));
							if(mb!=null && mb.length>0){
								data[i][9+3]=String.valueOf(mb[0][2]);//DA
							}
							//SET ot
							Object[][]ot=creditMap.get((empId+"#"+OT));
							if(ot!=null && ot.length>0){
								data[i][10+3]=String.valueOf(ot[0][2]);//DA
							}
							//======
							//SET le
							Object[][]le=creditMap.get((empId+"#"+LE));
							if(le!=null && le.length>0){
								data[i][11+3]=String.valueOf(le[0][2]);//DA
							}
							//SET bonus
							Object[][]bonus=creditMap.get((empId+"#"+BONUS));
							if(bonus!=null && bonus.length>0){
								data[i][12+3]=String.valueOf(bonus[0][2]);//DA
							}
							//SET grat
							Object[][]grat=creditMap.get((empId+"#"+GRATUITY));
							if(grat!=null && grat.length>0){
								data[i][13+3]=String.valueOf(grat[0][2]);//DA
							}
							//SET work
							Object[][]work=creditMap.get((empId+"#"+WORKMEN));
							if(work!=null && work.length>0){
								data[i][14+3]=String.valueOf(work[0][2]);//DA
							}
							
							//SET otherall
							Object[][]other=otherMap.get((empId+"#"+empId));
							if(other!=null && other.length>0){
								data[i][15+3]=String.valueOf(other[0][1]);//DA
							}
							
							
							//SET PF
							Object[][]pf=debitMap.get((empId+"#"+PF));
							if(pf!=null && pf.length>0){
								data[i][17+3]=String.valueOf(pf[0][2]);//DA
							}
							//SET esi
							double esiValue=0.0;
							
							Object[][]esi=debitMap.get((empId+"#"+ESI));
							if(esi!=null && esi.length>0){
								data[i][18+3]=String.valueOf(esi[0][2]);//DA
								esiValue=Double.parseDouble(String.valueOf(esi[0][2]));
							}
							
							
							double empCntribution=0.0;
							
							 try {
								empCntribution = ((esiValue * compESI) / empESI);
							} catch (Exception e) {
								// TODO: handle exception
							}
							//SET tax
							Object[][]tax=debitMap.get((empId+"#"+TAX));
							if(tax!=null && pf.length>0){
								data[i][19+3]=String.valueOf(tax[0][2]);//DA
							}
							
							//20 set other
							//SET otherall
							Object[][]otherDebit=otherDebitMap.get((empId+"#"+empId));
							if(otherDebit!=null && otherDebit.length>0){
								data[i][20+3]=String.valueOf(otherDebit[0][1]);//DA
							}
							
							//SET PF
							Object[][]recovery=debitMap.get((empId+"#"+RECOVERY));
							if(recovery!=null && recovery.length>0){
								data[i][21+3]=String.valueOf(recovery[0][2]);//DA
							}
							//SET empepf
							Object[][]empepf=debitMap.get((empId+"#"+PF));
							if(empepf!=null && empepf.length>0){
								data[i][23+3]=String.valueOf(empepf[0][2]);//DA
							}//SET empesic
							
							data[i][24+3]=String.valueOf(Math.ceil(Utility.twoDecimal(empCntribution,2)));//DA
							
						}
					
					
					}
					
					
					
					
					
					
				}
				else{
					rg.addTextBold("Salary not processed.", 0, 1, 0);
				}
				if(data!=null && data.length>0){
				//rg.xlsTableBody(allCols, data, cellWidth, cellAln);		
				rg.xlsTableBodyForStatement(allCols, data, cellWidth, cellAln,format);
				}
				
		}
		else{
			rg.addTextBold("Salary not processed.", 0, 1, 0);
		}
		rg.createReport(response);// Creates a report
		
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
		}
}
