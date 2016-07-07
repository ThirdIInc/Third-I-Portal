package org.paradyne.model.payroll;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.EsicForm6;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class EsicForm6Model extends ModelBase {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	public void report(EsicForm6 esicBean, HttpServletRequest request, HttpServletResponse response, String logoPath,  String reportPath) {
		try {
			
			int fromYear = Integer.parseInt(esicBean.getFromYear());
			int toYear = Integer.parseInt(esicBean.getToYear());
			String contPeriod = esicBean.getContributionPeriod();
			String period ="October - "+fromYear+" to March - "+toYear;
			String months = "";
			String [] months1 = new String[]{"10, 11, 12", " 1, 2, 3", "4, 5, 6, 7, 8, 9"};
			int year = fromYear;
			if(contPeriod.equals("A")){
				period = "April - "+fromYear+" to September - "+fromYear;
			}
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = esicBean.getRepType();
			String fileName ="ESIC Form-6"; 
			rds.setReportType(reportType);
			rds.setFileName("ESIC Form-6");
			rds.setReportName("ESIC Form-6");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(esicBean.getUserEmpId());
			rds.setReportHeaderRequired(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if(reportPath.equals("")){
				rg = new ReportGenerator(rds,session,context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
				rg = new ReportGenerator(rds,reportPath,session,context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "EsicForm6_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[2][1];
			obj[0][0] = "Annexure to From No 6 (E.S.I.C. Regulation 32)";
			obj[1][0] = period;
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontStyle(1);
			//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
			//subtitleName.setBorderDetail(0);
			subtitleName.setHeaderTable(false);
			subtitleName.setCellColSpan(new int[]{8});
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			
			String[] header1 = new String []{"Sr.No \n\n\n\n(1)", 
					"Insurance Number \n\n\n\n(2)", 
					"Name of Insured Person \n\n\n\n(3)",
					"No of Days for which Wages Paid \n\n(4)",
					"Total Amount of Wages Paid \n\n\n(5)",
					"Employees Contribution Deducted \n\n(6)",
					"Daily Wages \n\n\n\n(7)", "Remarks \n\n\n\n(8)"};
			int[] width1 = new int[]{5, 15, 20, 10, 10, 10, 10, 20};
			int[] alignment1 = new int[]{1, 0, 0, 2, 2, 2, 2, 0};
			
			String query = "";
			String wagesQuery="";
			String ledgerCodes = "";
			HashMap wagesMap=null;
			HashMap wagesMap1=null;
			HashMap wagesMap2=null;
			HashMap empMap1=null;
			Object [][]empQuarter1Obj=null;
			Object [][]empQuarter2Obj=null;
			Object [][]wagesQuarter1Obj=null;
			Object [][]wagesQuarter2Obj=null;
			Object[][] empObject=null;
			Object[][] empObject1=null;
			Object [][]finalEmployeeObj=null;
			String esiCodeQuery = "SELECT HRMS_ESI.ESI_DEBIT_CODE,NVL(HRMS_ESI.ESI_EMP_PERCENTAGE,0), NVL(HRMS_ESI.ESI_COMP_PERCENTAGE,0) FROM HRMS_ESI "
				+ " WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'dd-MON-yyyy')  = (SELECT MAX(HRMS_ESI.ESI_DATE) FROM HRMS_ESI WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'yyyy') <= '"
				+ esicBean.getFromYear() + "')";
			Object[][] esicCode = getSqlModel().getSingleResult(esiCodeQuery);
			if(!contPeriod.equals("A")){
				for (int i = 0; i < 2; i++) {
					year+=i;
					months=String.valueOf(months1[i]);
					
					ledgerCodes = getLedgerCodes(year, months, esicBean.getDivisionCode());
					
					query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
						+ " TO_CHAR(SUM(HRMS_SALARY_"+year+".SAL_DAYS),9999999999.99),0," 
						+ " TO_CHAR(SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT),9999999999.99) AS CONTRIBUTION, 0, ''" 
						+ " FROM   HRMS_SAL_DEBITS_"+year
						+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_"+year+".SAL_LEDGER_CODE " 
						+ " AND HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID AND SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+" AND HRMS_SAL_DEBITS_"+year
						+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0)" 
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID ) "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
						+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" 
						+ " WHERE  SAL_AMOUNT >0 GROUP BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
						+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID ";
				
					wagesQuery = " SELECT EMP_ID,'','','', TO_CHAR(SUM(SAL_AMOUNT),9999999999.99),'',0,'' FROM HRMS_SAL_CREDITS_"+year
						+ " LEFT JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND CREDIT_APPLICABLE_ESI='Y')" 
						+ " WHERE SAL_LEDGER_CODE IN("+ledgerCodes+") GROUP BY HRMS_EMP_OFFC.EMP_ID";
					
					
					
					if(i>0){
						empQuarter2Obj = getSqlModel().getSingleResult(query);
						wagesQuarter2Obj = getSqlModel().getSingleResult(wagesQuery);
						finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, empQuarter2Obj, 0, new int[]{3,4,5,6}, 8);
						finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, wagesQuarter2Obj, 0, new int[]{4}, 8);
					}else{
						empQuarter1Obj = getSqlModel().getSingleResult(query);
						wagesQuarter1Obj = getSqlModel().getSingleResult(wagesQuery);
						finalEmployeeObj = Utility.consolidateArrearsObject(empQuarter1Obj, null, 0, new int[]{3,4,5,6}, 8);
						finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, wagesQuarter1Obj, 0, new int[]{4}, 8);
					}
				}
				finalEmployeeObj =Utility.removeNullRows(finalEmployeeObj, 2);
				if(finalEmployeeObj!=null && finalEmployeeObj.length>0){
					
					for (int i = 0; i < finalEmployeeObj.length; i++) {
							finalEmployeeObj[i][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(finalEmployeeObj[i][4]))/Double.parseDouble(String.valueOf(finalEmployeeObj[i][3]))); //put daily wages here
							finalEmployeeObj[i][0] = ""+i+1;
					}
					
					TableDataSet empDataObj1 = new TableDataSet();
					empDataObj1.setHeader(header1);
					
					empDataObj1.setData(finalEmployeeObj);
					empDataObj1.setCellAlignment(alignment1);
					empDataObj1.setCellWidth(width1);
					//empDataObj1.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
					empDataObj1.setBorder(true);
					empDataObj1.setHeaderBorderDetail(3);
					empDataObj1.setBorderDetail(3);
					empDataObj1.setBlankRowsBelow(1);
					empDataObj1.setHeaderTable(true);
					//empDataObj1.setHeaderLines(true);
					//empDataObj1.setHeaderBGColor(new BaseColor(225, 225, 225));
					empDataObj1.setColumnSum(new int[] {3, 4, 5, 6});
					rg.addTableToDoc(empDataObj1);
				}else{
					TableDataSet noData = new TableDataSet();
					noData.setData(new Object[][] { { "" },
							{ "No records available for selected criteria" } });
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					//noData.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
					noData.setBorderDetail(0);
					noData.setCellColSpan(new int[]{8});
					noData.setHeaderTable(false);
					rg.addTableToDoc(noData);
				}
			}else{
				months=String.valueOf(months1[2]);
				ledgerCodes = getLedgerCodes(year, months, esicBean.getDivisionCode());
				
				query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
					+ " TO_CHAR(SUM(HRMS_SALARY_"+year+".SAL_DAYS),9999999999.99),' '," 
					+ " TO_CHAR(SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT),9999999999.99) AS CONTRIBUTION, ' ', ' '" 
					+ " FROM   HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_"+year+".SAL_LEDGER_CODE " 
					+ " AND HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID AND SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+" AND HRMS_SAL_DEBITS_"+year
					+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0)" 
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID ) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" 
					+ " WHERE  SAL_AMOUNT >0 GROUP BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
					+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID";
			
				wagesQuery = " SELECT EMP_ID, TO_CHAR(SUM(SAL_AMOUNT),9999999999.99) FROM HRMS_SAL_CREDITS_"+year
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND CREDIT_APPLICABLE_ESI='Y')" 
					+ " WHERE SAL_LEDGER_CODE IN("+ledgerCodes+") GROUP BY EMP_ID";
				
				empObject = getSqlModel().getSingleResult(query);
				
				wagesMap = getSqlModel().getSingleResultMap(wagesQuery,0, 2 );
				
				if(empObject!=null && empObject.length >0){
					Object [][] finalEmpObject=new Object[empObject.length][empObject[0].length];
					for (int i = 0; i < empObject.length; i++) {
						for (int j = 1; j < empObject[0].length; j++) {
							finalEmpObject[i][0] = i+1;
							Object[][] empWage=null;
							if(j==4){
								empWage = (Object[][]) wagesMap.get(String.valueOf(empObject[i][0]));
								finalEmpObject[i][j] = String.valueOf(empWage[0][1]);
							}else if(j==6){
								empWage = (Object[][]) wagesMap.get(String.valueOf(empObject[i][0]));
								finalEmpObject[i][j] = Utility.twoDecimals(Double.parseDouble(String.valueOf(empWage[0][1]))/Double.parseDouble(String.valueOf(empObject[i][3]))); //put daily wages here
							}else{
								finalEmpObject[i][j] = String.valueOf(empObject[i][j]);
							}
							
						}
					}
					
					TableDataSet empDataObj1 = new TableDataSet();
					empDataObj1.setHeader(header1);
					empDataObj1.setHeaderBorderDetail(3);
					empDataObj1.setData(finalEmpObject);
					empDataObj1.setCellAlignment(alignment1);
					empDataObj1.setCellWidth(width1);
					//empDataObj1.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
					empDataObj1.setBorder(true);
					empDataObj1.setBorderDetail(3);
					empDataObj1.setBlankRowsBelow(1);
					
					empDataObj1.setHeaderTable(true);
					//empDataObj1.setHeaderLines(true);
					//empDataObj1.setHeaderBGColor(new BaseColor(225, 225, 225));
					empDataObj1.setColumnSum(new int[] {3, 4, 5,6});
					rg.addTableToDoc(empDataObj1);
				}else{
					TableDataSet noData = new TableDataSet();
					noData.setData(new Object[][] { { "" },
							{ "No records available for selected criteria" } });
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					//noData.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
					noData.setBorderDetail(0);
					noData.setCellColSpan(new int[]{8});
					noData.setHeaderTable(false);
					rg.addTableToDoc(noData);
				}
			}
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getLedgerCodes(int year, String months, String divisionId){
		String ledgerCode = "";
		
		try {
			String ledgerCodeQuery = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR ="
					+ year + " AND LEDGER_MONTH IN ("+ months+ ") AND LEDGER_DIVISION=" + divisionId;
			Object[][] ledgerCodeObject = getSqlModel().getSingleResult(ledgerCodeQuery);
			
			if (ledgerCodeObject != null && ledgerCodeObject.length > 0) {
				for (int i = 0; i < ledgerCodeObject.length; i++) {
					ledgerCode += ledgerCodeObject[i][0] + ",";
				}
				if (ledgerCode.length() > 1) {
					ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ledgerCode;
	}

}
