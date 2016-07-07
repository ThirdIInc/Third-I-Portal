package org.paradyne.model.payroll.incometax;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.Form12;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.model.payroll.PFDataModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

/*
 * @Prakash
 * Date:20/08/2009
 */
public class Form12Model extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Form12Model.class);
	
	public void report(Form12 formbean, HttpServletRequest request,	HttpServletResponse response, String logoPath, String reportPath) {
		try{
			
			int year = Integer.parseInt(formbean.getFromYear());
			int prevYear = Integer.parseInt(formbean.getFromYear());
			int month = Integer.parseInt(formbean.getMonthName());
			int previousMonth=12;
			if(month>1){
				previousMonth = month-1;
			}else{
				prevYear-=1;
			}
			
			int fromYear = 0001; 
			int toYear = 0001; 
			
			if(month<4){
				fromYear = year-1;
			}else{
				fromYear = year;
			}
			toYear = fromYear+1;
			
			String bankDetails = "SELECT NVL(BANK_NAME,'N/A'), NVL(CHALLAN_CHEQ_NO,'0'), NVL(CHALLAN_AMOUNT,'0'), TO_CHAR(CHALLAN_CHEQ_DATE,'mm-dd-yyyy') FROM HRMS_PF_CHALLAN "
				+ " INNER JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_PF_CHALLAN.CHALLAN_BANK_MICR_CODE)"
				+ " WHERE CHALLAN_DIVISION IN ("
				+formbean.getDivisioncode()
				+ ") AND CHALLAN_MONTH ="
				+month
				+ " AND CHALLAN_YEAR ="
				+year;
			
			Object[][] bankDataObj = getSqlModel().getSingleResult(bankDetails);
			
			String bankName="";
			String chequeNo="";
			double chequeAmount=0;
			String chequeDate="";
			if(bankDataObj!=null && bankDataObj.length >0){
				bankName = String.valueOf(bankDataObj[0][0]);
				chequeNo = String.valueOf(bankDataObj[0][1]);
				//chequeAmount = String.valueOf(bankDataObj[0][2]);
				chequeDate = String.valueOf(bankDataObj[0][3]);
			}
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = formbean.getReportType();
			System.out.println("reportType : "+reportType);
			String fileName = "Form-12A";
			rds.setReportType(reportType);
			rds.setFileName("Form-12A");
			rds.setReportName("Form-12A");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(formbean.getUserEmpId());
			rds.setReportHeaderRequired(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,reportPath,session,context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "Form12_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[3][1];
			//obj[0][0] = rg.getImage(logoPath);
			obj[0][0] = "Form 12A (Revised) \n (For Unexempted Establishment Only)\n EMPLOYEE'S PROVIDENT FUND AND MISC.Provision ACT 1952 \n The EMPLOYEE'S Pension Scheme[Para-20(4)]";
			obj[1][0] = "Currency Period From : 1 st April,"+fromYear+" to 31 st March,"+toYear;
			obj[2][0] = "Statement of contribution for the month of : " + ""+ Utility.month(month)+" - "+year;
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
			subtitleName.setBorderDetail(0);
			subtitleName.setBodyFontStyle(1);
			subtitleName.setCellColSpan(new int[]{9});
			//subtitleName.setHeaderTable(true);
			//subtitleName.setHeaderLines(true);
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);

			//Establishment Data
			
			String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE DIV_ID IN ("+formbean.getDivisioncode()+" )";
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);
			
			Object division[][] = new Object[5][2];
			
			/*if(divisionObj!=null && divisionObj.length > 0){
				division[0][0] = "Name & Address of the Establishment :";
				division[0][1] = "Statutory rate of contribution : 12% ";
				division[1][0] = String.valueOf(divisionObj[0][0]);
				division[1][1] = "No of members voluntarily contributing at higher rate :";
				division[2][0] = String.valueOf(divisionObj[0][1]);
				division[3][0] = String.valueOf(divisionObj[0][2])+","+String.valueOf(divisionObj[0][3]);
				division[3][1] = "Code no of Establishment : "+String.valueOf(divisionObj[0][4]);
				division[4][1] = "Account Group No : "+Utility.checkNull(String.valueOf(divisionObj[0][5]));
			}*/
			if(divisionObj!=null && divisionObj.length > 0){
				division[0][0] = "Name & Address of the Establishment :";				
				division[1][0] = String.valueOf(divisionObj[0][0]);				
				division[2][0] = String.valueOf(divisionObj[0][1]);
				division[3][0] = String.valueOf(divisionObj[0][2])+","+String.valueOf(divisionObj[0][3]);
				
			}
			division[1][1]="Establishment Status : ";
			TableDataSet establishmentData = new TableDataSet();
			establishmentData.setCellAlignment(new int[] { 0,0 });
			establishmentData.setCellWidth(new int[] { 80,20 });
			establishmentData.setCellColSpan(new int[]{5,5});
			establishmentData.setData(division);
			establishmentData.setBorderDetail(0);
			establishmentData.setHeaderTable(false);
			establishmentData.setBlankRowsBelow(1);
			rg.addTableToDoc(establishmentData);
			
			
			Object subtitle[][]=new Object[1][3];
			if(divisionObj!=null && divisionObj.length > 0){
				subtitle[0][0]="Code no of Establishment : "+String.valueOf(divisionObj[0][4]);
				subtitle[0][1]="Statutory rate of contribution : 12% ";
				subtitle[0][2]="Group Code : "+Utility.checkNull(String.valueOf(divisionObj[0][5]));
			}
			
			TableDataSet subtitleData = new TableDataSet();
			subtitleData.setCellAlignment(new int[] { 0,0,0 });
			subtitleData.setCellWidth(new int[] { 30,40,30 });
			subtitleData.setCellColSpan(new int[]{3,3,4});
			subtitleData.setData(subtitle);
			subtitleData.setBorderDetail(0);
			subtitleData.setHeaderTable(false);
			subtitleData.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleData);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate("01-" + formbean.getMonthName() + "-"+ formbean.getFromYear()));
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			
			/*
			 * Insert the data in HRMS_PF_DATA if not present
			 * 
			 */
			
			  String PFDataQuery = "SELECT * FROM HRMS_PF_DATA WHERE PF_MONTH ="+month+" AND PF_YEAR = "+year 
			  +" AND  PF_EMP_DIV IN ( "+formbean.getDivisioncode()+")";
			  
			  Object [][] pfExist = getSqlModel().getSingleResult(PFDataQuery);
			 
			if(!(pfExist !=null && pfExist.length>0)){
			try {

				PFDataModel pfModel = new PFDataModel();
				pfModel.initiate(context, session);
				pfModel.insertPFReportData(String.valueOf(month), String.valueOf(year),
						formbean.getDivisioncode());
				pfModel.terminate();

			} catch (Exception e) {
				logger.error("Exception in calling PFData model for inserting PF data"
								+ e);
			}
			}
			String percentContribution = "SELECT PF_EMPLOYEE, PF_EMP_FAMILY, PF_COMPANY, PF_CMP_FAMILY, PF_ADMIN_CHARGES, PF_EDLI_EMPCONT, PF_EDLI_ADMIN_CHARGES, PF_PERCENTAGE, PF_EMOL_NO_MAX_LIMIT_CHK, PF_EMOL_MAX_LIMIT FROM HRMS_PF_CONF WHERE " 
				+ " TO_NUMBER(NVL(TO_CHAR(PF_DATE,'yyyy'),0)) <="
				+ year
				+ " ORDER BY PF_DATE DESC";
			Object[][] contributionData = getSqlModel().getSingleResult(percentContribution);
			
			double pf_emp = 0.0;
			double pf_emp_family = 0.0;
			double pf_comp = 0.0;
			double pf_comp_family = 0.0;
			double pf_admin_charges = 0.0;
			double pf_edli_empcont = 0.0;
			double pf_edli_admin_charges = 0.0;
			double pf_percentage = 0.0;
			String checkFlag = "N";
			double emolumentAmtLimit = 0.0;
			
			if(contributionData!=null && contributionData.length > 0){
				pf_emp = Double.parseDouble(String.valueOf(contributionData[0][0]));
				pf_emp_family = Double.parseDouble(String.valueOf(contributionData[0][1]));
				pf_comp = Double.parseDouble(String.valueOf(contributionData[0][2]));
				pf_comp_family = Double.parseDouble(String.valueOf(contributionData[0][3]));
				pf_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][4]));
				pf_edli_empcont = Double.parseDouble(String.valueOf(contributionData[0][5]));
				pf_edli_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][6]));
				pf_percentage = Double.parseDouble(String.valueOf(contributionData[0][7]));
				checkFlag = String.valueOf(contributionData[0][8]);
				emolumentAmtLimit = Double.parseDouble(String.valueOf(contributionData[0][9]));
			}
			
			String salLedger =" SELECT CEIL(SUM("
				+ " PF_EMP_BASIC)),CEIL(SUM(PF_EMP_PF+PF_EMP_F_PF)),CEIL(SUM(PF_CMP_PF)),CEIL(SUM(PF_CMP_F_PF)),CEIL(SUM(PF_EDLI)),COUNT(*)," 
				+ "SUM( CASE WHEN PF_CMP_PF=0 THEN 0"
				+ " ELSE " 
				+ " CASE WHEN ('Y'='Y' AND PF_EMP_BASIC < "
				+emolumentAmtLimit
				+ " )"
				+ " THEN PF_EMP_BASIC WHEN ('Y'='Y' AND PF_EMP_BASIC > "
				+emolumentAmtLimit
				+ " )"
				+ " THEN "
				+emolumentAmtLimit
				+ " ELSE PF_EMP_BASIC END "
				+ " END), TO_CHAR(CHALLAN_PAY_DATE,'dd-mm-yy')"
				+ " FROM HRMS_PF_DATA "
				+ " LEFT JOIN HRMS_PF_CHALLAN ON (HRMS_PF_CHALLAN.CHALLAN_DIVISION = HRMS_PF_DATA.PF_EMP_DIV AND CHALLAN_MONTH=PF_MONTH AND CHALLAN_YEAR=PF_YEAR )"
				+ " WHERE PF_EMP_PF>0 AND PF_MONTH="+ month+ " AND PF_YEAR = "+ year
				+ " AND PF_EMP_DIV IN ( "+formbean.getDivisioncode()
				+ ") GROUP BY CHALLAN_PAY_DATE";
				
				Object [][] salData = getSqlModel().getSingleResult(salLedger);
				
				if (salData != null && salData.length > 0) {	
					
					String []header1 = new String[9];
					
					header1[0] = "Particulars  ";
					header1[1] = "Wages on which contributions are payable ";
					header1[2] = "Recovered from the Worker ";
					header1[3] = "Payable by the Employer";
					header1[4] = "Worker's Share";
					header1[5] = "Employer's Share";
					header1[6] = "Amount of Admin charges due ";
					header1[7] = "Amount of Admin charges remitted ";
					header1[8] = "Date of Remittance";
					
					String []mainHeader = new String[4];
					mainHeader[0] = "";
					mainHeader[1] = "Amount of Contribution";
					mainHeader[2] = "Amount of Contribution Remitted ";
					mainHeader[3] = "";
					
					int[] cellwidth = { 35, 28, 25, 25, 25, 25, 35, 35, 35 };
					int[] alignment = { 0, 2, 2, 2, 2, 2, 2, 2, 1 };
					
					Object [][] dataObj = new Object[3][9];
					
					/*dataObj[0][0] = " ";
					dataObj[0][1] = " ";
					dataObj[0][2] = "Worker's Share";
					dataObj[0][3] = "Employer's Share";
					dataObj[0][4] = "Worker's Share";
					dataObj[0][5] = "Employer's Share";
					dataObj[0][6] = " ";
					dataObj[0][7] = " ";
					dataObj[0][8] = " ";*/
					
					dataObj[0][0] = "E.P.F A/c No. 01";
					dataObj[0][1] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][0])));
					dataObj[0][2] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][1])));//add
					dataObj[0][3] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][3])));//add
					dataObj[0][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][1])));
					dataObj[0][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][3])));
					dataObj[0][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_admin_charges/100);//add
					dataObj[0][7] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_admin_charges/100);
					dataObj[0][8] = String.valueOf(salData[0][7]);
					
					dataObj[1][0] = "Pension fund A/c No. 10";
					dataObj[1][1] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][4])));
					dataObj[1][2] = " ";
					dataObj[1][3] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][2])));//aadd
					dataObj[1][4] = " ";
					dataObj[1][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][2])));
					dataObj[1][6] = " ";
					dataObj[1][7] = " ";
					dataObj[1][8] = String.valueOf(salData[0][7]);
					
					dataObj[2][0] = "E.D.L.I A/c No. 21";
					dataObj[2][1] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][4])));
					dataObj[2][2] = " ";
					dataObj[2][3] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_edli_empcont/100);//add
					dataObj[2][4] = " ";
					dataObj[2][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_edli_empcont/100);
					dataObj[2][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_edli_admin_charges/100);//add
					dataObj[2][7] = Utility.twoDecimals(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_edli_admin_charges/100);
					dataObj[2][8] = String.valueOf(salData[0][7]);
					
					TableDataSet mainHeaderTDS = new TableDataSet();
					mainHeaderTDS.setData(null);
					mainHeaderTDS.setHeader(mainHeader);
					mainHeaderTDS.setCellAlignment(new int[]{0,1,1,0});
					mainHeaderTDS.setCellWidth(cellwidth);
					//form12aData.setHeaderBGColor(new BaseColor(225, 225, 225));
					mainHeaderTDS.setHeaderBorderDetail(3);
					mainHeaderTDS.setHeaderColSpan(new int[]{2,2,2,3});
					rg.addTableToDoc(mainHeaderTDS);
					
					TableDataSet form12aData = new TableDataSet();
					form12aData.setData(dataObj);
					form12aData.setHeader(header1);
					form12aData.setCellAlignment(alignment);
					form12aData.setCellWidth(cellwidth);
					form12aData.setBorder(true);
					form12aData.setBorderDetail(3);
					form12aData.setHeaderTable(false);
					//form12aData.setHeaderBGColor(new BaseColor(225, 225, 225));
					form12aData.setHeaderBorderDetail(3);
					form12aData.setBlankRowsBelow(1);
					rg.addTableToDoc(form12aData);
					
					int debitCode=Integer.parseInt(getPFCode(month, year));
					
					String noofEmployees = "SELECT COUNT(*) FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE AND TYPE_PF='N')"
						+ " WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ formbean.getDivisioncode()+")";
					
					Object[][] contractEmp = getSqlModel().getSingleResult(noofEmployees);
					
					String noofRegEmployees = "SELECT COUNT(*) FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE AND TYPE_PF='Y')"
						+ " WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ formbean.getDivisioncode()+")";
					
					Object[][] regEmp = getSqlModel().getSingleResult(noofRegEmployees);
					
					String noofNewEmp = "SELECT COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+year
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ ") AND SAL_AMOUNT>0  AND EMP_ID NOT IN (SELECT (EMP_ID) FROM HRMS_SAL_DEBITS_"+prevYear
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+previousMonth+" AND LEDGER_YEAR="+prevYear+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ " ) AND SAL_AMOUNT>0)";
					
					Object[][] newEmpObj = getSqlModel().getSingleResult(noofNewEmp);
					
					String noOfEmployeeLastMonth = "SELECT COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+prevYear
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+previousMonth+" AND LEDGER_YEAR="+prevYear+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ " )AND SAL_AMOUNT>0";
					
					Object[][] lastMonthEmpObj = getSqlModel().getSingleResult(noOfEmployeeLastMonth);
					
					String noOfEmployeeCurrentMonth = "SELECT COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+year
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ " )AND SAL_AMOUNT>0";
					Object[][] currentMonthEmpObj = getSqlModel().getSingleResult(noOfEmployeeCurrentMonth);
					
					String noofEmployeeToExclude = "SELECT COUNT(*) FROM HRMS_PF_DATA WHERE PF_MONTH="+ month+ " AND PF_YEAR = "+ year
						+ " AND PF_EMP_DIV IN ("+formbean.getDivisioncode()
						+ ") AND PF_CMP_PF = 0 ";
					Object[][] excludedEmpObj = getSqlModel().getSingleResult(noofEmployeeToExclude);
					
					String lastMonthPensionEmp = "SELECT COUNT(*) FROM HRMS_PF_DATA WHERE PF_MONTH="+ previousMonth+ " AND PF_YEAR = "+ prevYear
					+ " AND PF_EMP_DIV IN ("+formbean.getDivisioncode()
					+ ") AND PF_CMP_PF > 0 ";
					Object[][] lastMonthPensionEmpObj = getSqlModel().getSingleResult(lastMonthPensionEmp);
					
					String noofEmployeeResigned = "SELECT COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+prevYear
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+previousMonth+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ ")AND SAL_AMOUNT>0 AND EMP_ID NOT IN (SELECT (EMP_ID) FROM HRMS_SAL_DEBITS_"+year
						+ " INNER JOIN HRMS_SALARY_LEDGER ON "
						+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN ("+formbean.getDivisioncode()
						+ ") AND SAL_DEBIT_CODE="
						+debitCode
						+ " ) AND SAL_AMOUNT>0 )";
					
					Object[][] resignedEmpObj = getSqlModel().getSingleResult(noofEmployeeResigned);
					
					int contract=0;
					int rest=0;
					int newEmployee=0;
					int excludedEmpCount=0;
					int resignedEmpCount=0;
					int lastMonthEmployeeCount=0;
					int lastMonthPensionEmployeeCount=0;
					int currentMonthEmployeeCount=0;
					
					
					if (contractEmp != null && contractEmp.length > 0){
						contract = Integer.parseInt(String.valueOf(contractEmp[0][0]));
					}
					if (regEmp != null && regEmp.length > 0){
						rest = Integer.parseInt(String.valueOf(regEmp[0][0]));
					}
					if(newEmpObj!=null && newEmpObj.length > 0){
						newEmployee = Integer.parseInt(String.valueOf(newEmpObj[0][0]));
					}
					if(excludedEmpObj!=null && excludedEmpObj.length > 0){
						excludedEmpCount = Integer.parseInt(String.valueOf(excludedEmpObj[0][0]));
					}
					if(lastMonthPensionEmpObj!=null && lastMonthPensionEmpObj.length > 0){
						lastMonthPensionEmployeeCount = Integer.parseInt(String.valueOf(lastMonthPensionEmpObj[0][0]));
					}
					if(resignedEmpObj!=null && resignedEmpObj.length > 0){
						resignedEmpCount = Integer.parseInt(String.valueOf(resignedEmpObj[0][0]));
					}
					if(lastMonthEmpObj!=null && lastMonthEmpObj.length > 0){
						lastMonthEmployeeCount = Integer.parseInt(String.valueOf(lastMonthEmpObj[0][0]));
					}
					if(currentMonthEmpObj!=null && currentMonthEmpObj.length > 0){
						currentMonthEmployeeCount = Integer.parseInt(String.valueOf(currentMonthEmpObj[0][0]));
					}
					
					Object [][] noOfEmpTable1 = new Object[4][4];
					noOfEmpTable1[0][0] = "Details Of Subscribers";
					noOfEmpTable1[1][0] = "Contract";
					noOfEmpTable1[1][1] = contract;
					noOfEmpTable1[2][0] = "Rest";
					noOfEmpTable1[2][1] = rest;
					noOfEmpTable1[3][0] = "Total";
					noOfEmpTable1[3][1] = (rest + contract);
					
					noOfEmpTable1[0][2] = "Name & Address of the bank in which the amount is remitted";
					noOfEmpTable1[0][3] = "";
					
					int[] cellwidth1 = { 20,10,10,10};
					int[] alignment1 = { 0, 2, 0, 2 };
					
					
					TableDataSet empTable1 = new TableDataSet();
					empTable1.setData(noOfEmpTable1);
					//form12aData1.setHeader(headerdata12);
					empTable1.setCellAlignment(alignment1);
					empTable1.setCellWidth(cellwidth1);
					empTable1.setBorder(true);
					empTable1.setHeaderTable(false);
					empTable1.setBorder(true);
					empTable1.setBorderDetail(0);
					rg.addTableToDoc(empTable1);
					
					Object [][] bankDetailsTable1 = new Object[2][2];
					bankDetailsTable1[0][0] = "";
					bankDetailsTable1[0][1] = "Name & Address of the bank in which the amount is remitted";
					bankDetailsTable1[1][0] = "";
					bankDetailsTable1[1][1] = "";
					
					TableDataSet empTable2 = new TableDataSet();
					empTable2.setData(bankDetailsTable1);
					//form12aData1.setHeader(headerdata12);
					empTable2.setCellAlignment(new int[]{0, 0});
					empTable2.setCellWidth(new int[]{30, 70});
					empTable2.setBorderDetail(0);
					empTable2.setHeaderTable(false);
					//rg.addTableToDoc(empTable2);
					
					chequeAmount=Math.round(Double.parseDouble(String.valueOf(salData[0][1])))+
					Math.round(Double.parseDouble(String.valueOf(salData[0][2])))+
					Math.round(Double.parseDouble(String.valueOf(salData[0][3])))+
					Math.round(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_admin_charges/100)+
					Math.round(Double.parseDouble(String.valueOf(dataObj[0][1]))* pf_edli_empcont/100)+
					Math.round(Double.parseDouble(String.valueOf(dataObj[0][1]))*pf_edli_admin_charges/100);
					/*Object [][] bankDetailsTable2 = new Object[3][2];
					bankDetailsTable2[0][0] = "Drawn on : "+bankName;
					bankDetailsTable2[0][1] = "";
					//bankDetailsTable2[0][2] = "";
					bankDetailsTable2[1][0] = "Cheque no : "+chequeNo;
					bankDetailsTable2[1][1] = "Date : "+chequeDate;
					//bankDetailsTable2[1][2] = "";
					bankDetailsTable2[2][0] = "For Rs :"+Utility.twoDecimals(chequeAmount);
					bankDetailsTable2[2][1] = "";
					//bankDetailsTable2[2][2] = "";
					
					TableDataSet empTable3 = new TableDataSet();
					empTable3.setData(bankDetailsTable2);
					empTable3.setCellAlignment(new int[]{0,0});
					empTable3.setCellWidth(new int[]{50, 40});
					empTable3.setBorder(false);
					empTable3.setHeaderTable(false);*/
					//rg.addTableToDoc(empTable3);
					
					//HashMap<String ,Object> map1 = rg.joinTableDataSet(empTable1, empTable2, true, 30);
					//HashMap<String ,Object> map = rg.joinTableDataSet(map1, empTable3, true, 60);
					//rg.addTableToDoc(map1);
					
					/*Details of subscriber last table code*/
					String[] header3 = new String[4];
					header3[0] = "Details of Subscriber";
					header3[1] = "EPF";
					header3[2] = "Pension Fund";
					header3[3] = "E.D.L.I.";
					
					Object [][] headerdata4 = new Object[4][4];
					headerdata4[0][0] = "No. of Subscribers as per last month";
					headerdata4[0][1] = lastMonthEmployeeCount;
					headerdata4[0][2] = lastMonthPensionEmployeeCount;
					headerdata4[0][3] = lastMonthEmployeeCount;
					
					headerdata4[1][0] = "No. of New Subscribers (Vide Form 5)";
					headerdata4[1][1] = newEmployee;
					headerdata4[1][2] = excludedEmpCount;
					headerdata4[1][3] = newEmployee;
					
					headerdata4[2][0] = "No. of Subscribers left Service (Vide Form 10)";
					headerdata4[2][1] = resignedEmpCount;
					headerdata4[2][2] = "0";
					headerdata4[2][3] = resignedEmpCount;
					
					headerdata4[3][0] = "Total No. of Subscribers ";
					headerdata4[3][1] = currentMonthEmployeeCount;
					headerdata4[3][2] = lastMonthPensionEmployeeCount-excludedEmpCount;
					headerdata4[3][3] = currentMonthEmployeeCount;
					
					
					int[] cellwidth2 = { 20, 10, 10, 10};
					int[] alignment2 = { 0, 1, 1, 1};
					
					TableDataSet form12aData2 = new TableDataSet();
					form12aData2.setData(headerdata4);
					form12aData2.setHeader(header3);
					form12aData2.setCellAlignment(alignment2);
					form12aData2.setCellWidth(cellwidth2);
					form12aData2.setBorder(true);
					form12aData2.setHeaderTable(true);
					form12aData2.setHeaderBorderDetail(3);
					form12aData2.setBorderDetail(3);
					rg.addTableToDoc(form12aData2);
					//form12aData2.setBlankRowsAbove(1);
					
					Object [][] divisionDataObj = new Object[2][1];
					/*divisionDataObj[0][0] = " ";
					divisionDataObj[0][1] = " ";
					divisionDataObj[0][2] = " ";
					divisionDataObj[1][0] = " ";
					divisionDataObj[1][1] = " ";
					divisionDataObj[1][2] = " ";
					divisionDataObj[2][0] = " ";
					divisionDataObj[2][1] = " ";
					divisionDataObj[2][2] = " ";
					divisionDataObj[3][0] = " ";*/
					divisionDataObj[0][0] = formbean.getDivisionName();
					divisionDataObj[1][0] = "Signature of the employers with Office(seal)";
					
					
					TableDataSet divisionTable = new TableDataSet();
					divisionTable.setData(divisionDataObj);
					divisionTable.setCellAlignment(new int[]{0});
					divisionTable.setCellWidth(new int[]{100});
					divisionTable.setCellColSpan(new int[]{3});
					divisionTable.setBorderDetail(0);
					//form12aData2.setBlankRowsAbove(1);
					
					/*HashMap<String ,Object> map2 = rg.joinTableDataSet(form12aData2, divisionTable, true, 50);
					rg.addTableToDoc(map2);*/
				}else{
					TableDataSet noData = new TableDataSet();
					noData.setData(new Object[][] {  { "No records avaliable for selected criteria" } });
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					/*noData.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,
							new BaseColor(0, 0, 0));*/
					noData.setBorderDetail(0);
					noData.setHeaderTable(false);
					rg.addTableToDoc(noData);
				}
				rg.process();
				if(reportPath.equals("")){
					rg.createReport(response);
				}else{
					/* Generates the report as attachment*/
					rg.saveReport(response);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getPFCode(int month, int year) {
		try {
			String debitCode="0";
			String pfSql = " SELECT PF_DEBIT_CODE FROM HRMS_PF_CONF "
					+ " WHERE PF_DATE = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF WHERE PF_DATE <= TO_DATE('"
					+ month+"-"+year+ "','MM-YYYY')) ";
			Object[][] pfData = getSqlModel().getSingleResult(pfSql);
			if(pfData!=null && pfData.length >0){
				debitCode = String.valueOf(pfData[0][0]);
			}
			return debitCode;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	}
}
