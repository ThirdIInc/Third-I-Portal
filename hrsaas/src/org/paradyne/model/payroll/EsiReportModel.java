package org.paradyne.model.payroll;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.ESICReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

/*
 * 
 * Using new report library.
 */
public class EsiReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EsiReportModel.class);
	/**
	 * This function is used to create ESIC report based on different filters
	 * @param esibean
	 * @param request
	 * @param response
	 * @param reportPath
	 */
	public void getEsiReport(ESICReport esibean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rgs=null;
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = esibean.getReportType();
			rds.setReportType(reportType);
			String fileName = "ESIC Statutory Report "+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setUserEmpId(esibean.getUserEmpId());
			rds.setReportName("ESIC Statutory Report");
			rds.setTotalColumns(8);
			if(reportType.equals("Pdf")){
				rds.setPageOrientation("landscape");
			}
			if(reportPath.equals("")){    // to generate report
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{   // to save the generated report at specified path
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "ESICReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String filters = "";
		

			filters+="\n\nPeriod : "+ Utility.month(Integer.parseInt(esibean.getMonth())) + "-"+ esibean.getYear();
		
			if(esibean.getDivName()!= null && !esibean.getDivName().equals("")){
				
				filters+="\n\nDivision : "+esibean.getDivName();
				
			}
			
			if(esibean.getBrnName()!= null && !esibean.getBrnName().equals("")){
				filters+="\n\nBranch : "+esibean.getBrnName();
				
			}
			if(esibean.getDeptName()!= null && !esibean.getDeptName().equals("")){
				filters+="\n\nDepartment : "+esibean.getDeptName();
				
			}
			if(esibean.getPayBillName() != null && !esibean.getPayBillName().equals("")){
				filters+="\n\nPaybill : "+esibean.getPayBillName();
				
			}
			if(esibean.getTypeName() != null && !esibean.getTypeName().equals("")){
				filters+="\n\nEmployee Type : "+esibean.getTypeName();
				
			}			
			if(esibean.getCadreName() != null && !esibean.getCadreName().equals("")){
				filters+="\n\nGrade : "+esibean.getCadreName();
			}
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{8});
			filterData.setBlankRowsBelow(1);
			filterData.setCellNoWrap(new boolean[]{false});
			
		//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rgs.addTableToDoc(filterData);
			
			rgs=getEsiData(esibean, response, rgs);
			rgs.process();
			
			if(reportPath.equals("")){
				rgs.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rgs.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method calls the appropriate method to generate the report based on filters & different options
	 * @param esi
	 * @param response
	 * @param rgs
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getEsiData(ESICReport esi, HttpServletResponse response, org.paradyne.lib.ireportV2.ReportGenerator rgs) {

		try {
			// to find out applicable ESIC code
			String esiCodeQuery = "SELECT HRMS_ESI.ESI_DEBIT_CODE,HRMS_ESI.ESI_EMP_PERCENTAGE,HRMS_ESI.ESI_COMP_PERCENTAGE FROM HRMS_ESI "
					+ " WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy')  = (SELECT MAX(ESI_DATE) FROM HRMS_ESI WHERE TO_CHAR(ESI_DATE,'yyyy-mm') <= '"
					+ esi.getYear() + "-" + esi.getMonth() + "')";
			Object[][] esi_code = getSqlModel().getSingleResult(esiCodeQuery);

			// to find out ledger code
			String ledgerQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
					+ esi.getMonth()
					+ " AND LEDGER_YEAR="
					+ esi.getYear()
					+ " AND LEDGER_DIVISION IN (" + esi.getDivCode()+")";

			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			
			// to find out arrears code
			String ledgerQry = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
					+ esi.getMonth()
					+ " AND ARREARS_PAID_YEAR ="
					+ esi.getYear()
					+" AND ARREARS_DIVISION IN ("+esi.getDivCode()+")";
			Object ledgerObj[][] = getSqlModel().getSingleResult(ledgerQry);
			String arrCode = "";
			
			double esiEmpPercentage = 1.75;
			double esiCompanyPercentage = 4.75;
			// set percentage according to parameter
			if(esi_code!=null && esi_code.length>0){
				esiEmpPercentage = Double.parseDouble(String.valueOf(esi_code[0][1]));
				esiCompanyPercentage = Double.parseDouble(String.valueOf(esi_code[0][2]));
				
			}
			
			for (int i = 0; i < ledgerObj.length; i++){
				arrCode += ledgerObj[i][0] + ",";
			}
			if(arrCode.length() > 1){
				arrCode = arrCode.substring(0, arrCode.length() - 1);
			}

			String ledgerCode = "";
				if(ledgerData != null && ledgerData.length > 0){
					for (int i = 0; i < ledgerData.length; i++){ 
						ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
					}
				}
				if(ledgerCode.length() > 1)
					ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
				
				if (esi.getReportOption().equals("S")) {   // if report option only salary
					rgs = getSalaryEsi(rgs, esi, esi_code, ledgerCode, arrCode, esiEmpPercentage, esiCompanyPercentage);
				} else if (esi.getReportOption().equals("A")) { // if report option only arrears
					if (esi.getCheckFlag().equals("Y")) {  // if report option consolidated arrears
						rgs = getEsiReportConsolidatedArrears(rgs, esi, esi_code, arrCode, esiEmpPercentage, esiCompanyPercentage);
					}else{
						rgs = getEsiReportArrears(rgs, esi, esi_code, arrCode, esiEmpPercentage, esiCompanyPercentage);
					}
				} else if (esi.getReportOption().equals("O")) { // if report option All
					rgs = getSalaryEsi(rgs, esi, esi_code, ledgerCode, arrCode, esiEmpPercentage, esiCompanyPercentage);
					if (esi.getCheckFlag().equals("N")) {
						rgs = getEsiReportArrears(rgs, esi, esi_code, arrCode, esiEmpPercentage, esiCompanyPercentage);
					}
					if (esi.getCheckFlag().equals("N")){ 
						rgs = getSettlementEsi(rgs, esi, esi_code, esiEmpPercentage, esiCompanyPercentage);
					}
				} else if (esi.getReportOption().equals("se")) {  // if report option only settlement
					rgs = getSettlementEsi(rgs, esi, esi_code, esiEmpPercentage, esiCompanyPercentage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	/**
	 * This method is used to get the employee ESIC details from salary
	 * @param rgs
	 * @param esi
	 * @param esi_obj
	 * @param ledgerCode
	 * @param arrCode
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getSalaryEsi(org.paradyne.lib.ireportV2.ReportGenerator rgs, ESICReport esi,
			Object[][] esi_obj, String ledgerCode, String arrCode,Double esiEmpPercentage, Double esiCompanyPercentage) {
		try {
		
			// query for salary amount
			String salQuery = "SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(SAL_ESCINUMBER,'NA'),SAL_DAYS,"
					+ " 0,TO_CHAR(SUM(HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT),9999999999.99),TO_CHAR( NVL(HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT,0),9999999999.99),"
					+" CASE WHEN NVL(DEBIT_ROUND,0)=0 THEN TO_CHAR((SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100),9999999999.99) "
					+"  WHEN NVL(DEBIT_ROUND,0)=1 THEN TO_CHAR(ROUND(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100),9999999999.99) "
					+" WHEN NVL(DEBIT_ROUND,0)=2 THEN TO_CHAR(FLOOR(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100),9999999999.99) "
					+" WHEN NVL(DEBIT_ROUND,0)=3 THEN TO_CHAR(CEIL(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100),9999999999.99)"
					+" WHEN NVL(DEBIT_ROUND,0)=4 THEN TO_CHAR(ROUND(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100 +4, -1),9999999999.99) "
					+" ELSE TO_CHAR(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100,9999999999.99) END"
					+		",NVL(CENTER_NAME,' '),SAL_DEPT,HRMS_SAL_DEBITS_"+ esi.getYear()+".EMP_ID "
					+ " FROM HRMS_SAL_DEBITS_"+ esi.getYear()+"  "
					+ "LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SAL_DEBITS_"+ esi.getYear()+" .EMP_ID = HRMS_SALARY_MISC.EMP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID)"
					+ " INNER JOIN HRMS_SALARY_"
					+ esi.getYear()
					+ "  ON (HRMS_SALARY_"
					+ esi.getYear()
					+ " .EMP_ID = HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID AND SAL_DEBIT_CODE="
					+ String.valueOf(esi_obj[0][0])
					+ " "
					+ " AND HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ")) "
					+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.EMP_ID =  HRMS_SALARY_"
					+ esi.getYear()
					+ " .EMP_ID)"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON ( hrms_credit_head.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE"
					+ "  AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y') "
					+ " INNER join HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ "  ON (HRMS_EMP_CREDIT.EMP_ID= HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .EMP_ID AND  "
					+ " HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_CREDIT_CODE "
					+ " and HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE IN ("
					+ ledgerCode+"))"
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+ esi.getYear()+".SAL_EMP_CENTER) " 
					+" LEFT JOIN HRMS_DEBIT_HEAD ON DEBIT_CODE=SAL_DEBIT_CODE"
					+ " WHERE HRMS_SALARY_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ")  AND SAL_DIVISION IN ("
					+ esi.getDivCode()
					+ ") AND "
					+ " HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT >0 AND CREDIT_PERIODICITY='M'  ";
			
			salQuery=setQueryFilters(esi,salQuery);
			if(!esi.getOnHold().equals("A"))
				salQuery+="AND SAL_ONHOLD = '"+esi.getOnHold()+"'";
			
			salQuery+= " GROUP BY EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " SAL_DAYS,HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT,NVL(CENTER_NAME,' '),SAL_DEPT,HRMS_SAL_DEBITS_"+ esi.getYear()+".EMP_ID,NVL(SAL_ESCINUMBER,'NA'),DEBIT_ROUND"
					+ " ORDER BY NVL(CENTER_NAME,' '),SAL_DEPT,UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

			Object[][] salary_esi = null;
			double grossTotal=0;
			double monthTotal=0;
			if(!ledgerCode.equals("")){
				salary_esi = getSqlModel().getSingleResult(salQuery);
				if(salary_esi != null && salary_esi.length > 0){
					// for gross ESIC amount
					String grossQry =" SELECT HRMS_SALARY_"+esi.getYear()+".EMP_ID,TO_CHAR(SUM(CREDIT_AMT),9999999999.99) FROM HRMS_SALARY_"+esi.getYear()
							+" INNER JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.EMP_ID =  HRMS_SALARY_"+esi.getYear()+".EMP_ID) " 
							+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE  "
							+" WHERE SAL_LEDGER_CODE in("+ledgerCode+") AND CREDIT_APPLICABLE_ESI = 'Y'	 GROUP BY HRMS_SALARY_"+esi.getYear()+".EMP_ID ";
					// monthly gross amount
					String monthGrossQry=" SELECT HRMS_SAL_CREDITS_"+esi.getYear()+".EMP_ID,TO_CHAR(SUM(SAL_AMOUNT),9999999999.99) FROM HRMS_SAL_CREDITS_"+esi.getYear()+" "
							+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+esi.getYear()+".SAL_CREDIT_CODE "  
							+" WHERE SAL_LEDGER_CODE in("+ledgerCode+") AND CREDIT_APPLICABLE_ESI = 'Y' "	 
							+" GROUP BY HRMS_SAL_CREDITS_"+esi.getYear()+".EMP_ID ";
					
					HashMap<String,Object[]> map = getSqlModel().getSingleResultMap(grossQry,0,0);
					
					HashMap<String,Object[]> map_month = getSqlModel().getSingleResultMap(monthGrossQry,0,0);
					
					if( map != null && map.size() > 0){
						for (int i = 0; i < salary_esi.length; i++) {
							Object [] tempObj = (Object[])map.get(String.valueOf(salary_esi[i][10]));
							salary_esi[i][4] = String.valueOf(tempObj[1]);
							grossTotal=grossTotal+Double.parseDouble(String.valueOf(salary_esi[i][4]));
							
							Object [] tempObj_month = (Object[])map_month.get(String.valueOf(salary_esi[i][10]));
							if(tempObj_month != null && tempObj_month.length > 0) {
								salary_esi[i][5] =  String.valueOf(tempObj_month[1]);
							}else
								salary_esi[i][5] = "0";
							
							monthTotal=monthTotal+Double.parseDouble(String.valueOf(salary_esi[i][5]));
						}
					}
				}
			}
			
			// query for total amount of all employee
			String salQueryToal = "SELECT 0,TO_CHAR(SUM(MONTHSAL),9999999999.99),TO_CHAR(SUM(ESI),9999999999.99),TO_CHAR(SUM(COMPESI),9999999999.99) FROM (SELECT HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,SAL_DAYS,"
					+ " TO_CHAR(SUM(credit_AMT),9999999.00) gross,TO_CHAR(sum(HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT),9999999.00) MONTHSAL, TO_CHAR(NVL(HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT,0),9999999.00) ESI,"
					+" CASE WHEN NVL(DEBIT_ROUND,0)=0 THEN (SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100)"
					+"  WHEN NVL(DEBIT_ROUND,0)=1 THEN ROUND(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100)"
					+" WHEN NVL(DEBIT_ROUND,0)=2 THEN FLOOR(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100)"
					+" WHEN NVL(DEBIT_ROUND,0)=3 THEN CEIL(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100)"
					+" WHEN NVL(DEBIT_ROUND,0)=4 THEN ROUND(SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100 +4, -1) "
					+" ELSE SUM(HRMS_SAL_CREDITS_"+ esi.getYear()+" .SAL_AMOUNT)*"+String.valueOf(esi_obj[0][2])+"/100 END "
					+" COMPESI,SAL_DEPT,NVL(CENTER_NAME,' ')	 "
					+ " FROM HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ "  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID)"
					+ " INNER JOIN HRMS_SALARY_"
					+ esi.getYear()
					+ "  ON (HRMS_SALARY_"
					+ esi.getYear()
					+ " .EMP_ID = HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID AND SAL_DEBIT_CODE="
					+ String.valueOf(esi_obj[0][0])
					+ " "
					+ " AND HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE in("
					+ ledgerCode
					+ ")) "
					+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.EMP_ID =  HRMS_SALARY_"
					+ esi.getYear()
					+ " .EMP_ID)"
					+ " inner JOIN HRMS_CREDIT_HEAD ON ( hrms_credit_head.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE"
					+ "  and CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' ) "
					+ " inner join HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ "  ON (HRMS_EMP_CREDIT.EMP_ID= HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .EMP_ID AND  "
					+ " HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_CREDIT_CODE "
					+ " and HRMS_SAL_CREDITS_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE in ("
					+ ledgerCode+"))"
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+ esi.getYear()+".SAL_EMP_CENTER) "
					+" LEFT JOIN HRMS_DEBIT_HEAD ON DEBIT_CODE=SAL_DEBIT_CODE"
					+ " WHERE HRMS_SALARY_"
					+ esi.getYear()
					+ " .SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ")  AND SAL_DIVISION IN ("
					+ esi.getDivCode()
					+ ") AND "
					+ " HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT >0 AND CREDIT_PERIODICITY='M'  ";
					salQueryToal=setQueryFilters(esi,salQueryToal);
					salQueryToal+= " GROUP BY DEBIT_ROUND,HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .EMP_ID,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " SAL_DAYS,HRMS_SAL_DEBITS_"
					+ esi.getYear()
					+ " .SAL_AMOUNT,SAL_DEPT,NVL(CENTER_NAME,' ')  "
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),NVL(CENTER_NAME,' '),SAL_DEPT)";

			Object[][] salary_esi_total = null;
			if(!ledgerCode.equals("")){
				salary_esi_total = getSqlModel().getSingleResult(salQueryToal);
				salary_esi_total [0][0] = String.valueOf(grossTotal);
				salary_esi_total [0][1] = String.valueOf(monthTotal);
			}
			try {
				if (esi.getCheckFlag().equals("Y")) {   // check if consolidated
					// get the arrears amount
					Object[][] arrsObj = getArrearsAmt(esi, esi_obj, arrCode);
					
					// arrears total amount
					String arrearsQry = " SELECT 0,TO_CHAR(SUM(ARRAMT),9999999999.99),TO_CHAR(SUM(ESIAMT),9999999999.99),TO_CHAR(SUM(COMPESI),9999999999.99) FROM (SELECT EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME AS NAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') AS MONTH,ARREARS_YEAR,ARREARS_DAYS,DECODE(ARREARS_TYPE,'M','Monthly','Promotional') as ARREARS_TYPE "
						+ " ,NVL(SUM(CREDIT_AMT),0) SUMCRD,ARREARS_CREDITS_AMT ARRAMT, NVL(HRMS_ARREARS_DEBIT_"
						+ esi.getYear()
						+ ".ARREARS_AMT,0.00) ESIAMT, CEIL(NVL(ARREARS_CREDITS_AMT,0.00) * "
						+ String.valueOf(esi_obj[0][2])
						+ "/100 )COMPESI,EMP_CENTER,EMP_DEPT "
						+ " FROM HRMS_ARREARS_DEBIT_"
						+ esi.getYear()
						+ " "
						+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"
						+ esi.getYear()
						+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
						+ " INNER JOIN HRMS_ARREARS_"+esi.getYear()+" ON (HRMS_ARREARS_"+esi.getYear()+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_CODE " 
						+ " AND HRMS_ARREARS_"+esi.getYear()+".EMP_ID =HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_EMP_ID " 
						+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_MONTH = HRMS_ARREARS_"+esi.getYear()+".ARREARS_MONTH "
						+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_YEAR = HRMS_ARREARS_"+esi.getYear()+".ARREARS_YEAR) "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"
						+ esi.getYear()
						+ ".ARREARS_EMP_ID) "
						+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_ARREARS_DEBIT_"
						+ esi.getYear()
						+ ".ARREARS_EMP_ID = HRMS_EMP_CREDIT.EMP_ID)"
						+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)   "
						+ " WHERE ARREARS_PAID_MONTH = "
						+ esi.getMonth()
						+ " AND ARREARS_PAID_YEAR="
						+ esi.getYear()
						+ "	AND CREDIT_PERIODICITY = 'M' AND CREDIT_APPLICABLE_ESI='Y' AND "
						+ " HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND ARREARS_AMT>0 AND ARREARS_DEBIT_CODE= "
						+ String.valueOf(esi_obj[0][0]);
					if(!esi.getOnHold().equals("A")){
						arrearsQry +=" AND ARREARS_ONHOLD='"+esi.getOnHold()+"'";
					}
					arrearsQry=setQueryFilters(esi,arrearsQry);
					arrearsQry+= " GROUP BY EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME,ARREARS_MONTH,ARREARS_YEAR ,ARREARS_DAYS,ARREARS_TYPE,ARREARS_AMT,ARREARS_CREDITS_AMT,EMP_CENTER,EMP_DEPT "
						+ " ORDER BY UPPER(NAME))";

				   Object[][] arr_Tot_Obj = getSqlModel().getSingleResult(arrearsQry);

				   // add arrears total amount to salary total amount
					if(arrsObj != null && arrsObj.length > 0){
						for (int i = 1; i < salary_esi_total[0].length; i++) {
							
							salary_esi_total[0][i]= Math.round(Double.parseDouble(String.valueOf(salary_esi_total[0][i])) 
													+ Double.parseDouble(String.valueOf(arr_Tot_Obj[0][i])));
											
						}
					}
					arrsObj = Utility.removeColumns(arrsObj, new int[]{8,9,10});
					salary_esi = Utility.consolidateArrearsObject(salary_esi, arrsObj, 0, new int[]{3,5,6,7}, 10);
				}
			} catch (Exception e) {
				logger.error("Exception while adding salary and arrears object "+ e);
				e.printStackTrace();
			}
			// print the report on different sorting options
			if (salary_esi != null && salary_esi.length > 0) {
				if (esi.getBranchFlag().equals("true") && esi.getDeptFlag().equals("true")) {
					rgs = setBrnDeptReport(rgs, salary_esi, salary_esi_total, ledgerCode, esi.getYear(), esiEmpPercentage, esiCompanyPercentage);
				} else if (esi.getBranchFlag().equals("true")) {
					rgs = setBrnReport(rgs, salary_esi, salary_esi_total, ledgerCode, esi.getYear(),esi, esiEmpPercentage, esiCompanyPercentage);
				} else if (esi.getDeptFlag().equals("true")) {
					rgs = setDeptReport(rgs, salary_esi, salary_esi_total, ledgerCode, esi.getYear(), esiEmpPercentage, esiCompanyPercentage);
				} else{
					salary_esi = Utility.removeColumns(salary_esi, new int[]{8,9});
					rgs = setSalReport(rgs, salary_esi, salary_esi_total, esiEmpPercentage, esiCompanyPercentage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	/**
	 * This method is used to generate ESI salary report for branch wise & dept wise sorting
	 * @param rgs
	 * @param salary_esi
	 * @param salary_esi_total
	 * @param ledgerCode
	 * @param year
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator setBrnDeptReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] salary_esi,
			Object[][] salary_esi_total, String ledgerCode,
			String year, Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 1 ###############");
		try {
			
			String brnQuery = "select distinct sal_emp_center,center_name,sal_dept,dept_name from HRMS_SALARY_"
					+ year
					+ " inner join hrms_center on hrms_center.center_id= HRMS_SALARY_"
					+ year
					+ ".sal_emp_center"
					+ " inner join hrms_dept on hrms_dept.dept_id= HRMS_SALARY_"
					+ year
					+ ".sal_dept"
					+ "  where sal_ledger_code in("
					+ ledgerCode
					+ ") "
					+ "  group by sal_emp_center,center_name,SAL_DEPT,dept_name order by sal_emp_center,sal_dept ";

			Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
			int braCount = 0;
			int[] cellwidth = { 15, 30, 15, 15, 15, 15, 10, 15 };
			int[] alignment = { 0, 0, 0, 1, 1, 1, 1, 1 };
			String colNames[] = { "Emp Id", "Employee Name","Insurance No",
					"Salary Days", "Monthly Gross", "Amount on which ESI is calculated ", "ESI Subscription @"+esiEmpPercentage+"%(Rs)",
			"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)"  };
			
			if (branch_data != null && branch_data.length > 0) {
				Vector braVector = new Vector();
				Object[][] braTotObj = new Object[1][8];
				braTotObj = intZero(braTotObj);

				for (int i = 0; i < salary_esi.length + 1; i++) {
					// branch wise data
					if (i < salary_esi.length
							&& String.valueOf(branch_data[braCount][0]).equals(
									String.valueOf(salary_esi[i][8]))
							&& String.valueOf(branch_data[braCount][2]).equals(
									String.valueOf(salary_esi[i][9]))) {
						braVector.add(salary_esi[i]);
						braTotObj[0][0] = "Total";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";
						braTotObj[0][3] = "";
						braTotObj[0][4] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][4]))
										+ Double.parseDouble(String
												.valueOf(salary_esi[i][4])));
						braTotObj[0][5] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][5]))
										+ Double.parseDouble(String
												.valueOf(salary_esi[i][5])));
						braTotObj[0][6] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][6]))
										+ Double.parseDouble(String
												.valueOf(salary_esi[i][6])));

						braTotObj[0][7] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][7]))
										+ Double.parseDouble(String
												.valueOf(salary_esi[i][7])));

					} else if (braCount < branch_data.length) {  // print branch wise data
						
						Object[][] reportObj = new Object[braVector.size()][8];
						for (int j = 0; j < braVector.size(); j++) {
							reportObj[j] = (Object[]) braVector.get(j);
						}
						Object othrTotalData[][] = new Object[4][2];
						if(reportObj!= null && reportObj.length > 0)
						{
							Object[][] summaryData = new Object[1][1];
							summaryData[0][0] = "Salary Details " ;
							int[] cellWidthDateHeader = { 100 };
							int[] cellAlignDateHeader = { 0 };
							TableDataSet tableheadingDateData = new TableDataSet();
							tableheadingDateData.setData(summaryData);
							tableheadingDateData.setCellWidth(cellWidthDateHeader);
							tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
							tableheadingDateData.setBodyFontStyle(1);  
							tableheadingDateData.setCellColSpan(new int[]{8});
						//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
							tableheadingDateData.setBorderDetail(0);
							//tableheadingDateData.setHeaderTable(true);
							// tableheadingDateData.setBlankRowsAbove(1);
							rgs.addTableToDoc(tableheadingDateData);
							
							TableDataSet reportObjData = new TableDataSet();
							reportObjData.setHeader(colNames);
							reportObjData.setCellAlignment(alignment);
							reportObjData.setCellWidth(cellwidth);
							reportObjData.setData(reportObj);
							reportObjData.setColumnSum(new int[]{3,4, 5, 6, 7});
							reportObjData.setBorder(true);
							//reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
							reportObjData.setHeaderTable(true);
							reportObjData.setHeaderBorderDetail(3);
							reportObjData.setBorderDetail(3);
							reportObjData.setBlankRowsBelow(1);
							rgs.addTableToDoc(reportObjData);
							
							
							othrTotalData[0][0] = "Employee's Contribution";
							othrTotalData[0][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][6])));
							othrTotalData[1][0] = "Employer's Contribution";
							othrTotalData[1][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][7])));
							othrTotalData[2][0] = "Total ESIC";
							othrTotalData[2][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][6]))
									+ Double.parseDouble(String
											.valueOf(braTotObj[0][7])));
							othrTotalData[3][0] = "Total No. of Employees";
							othrTotalData[3][1] = reportObj.length;
							
							// print total data
							TableDataSet totalData = new TableDataSet();
							totalData.setCellAlignment(new int[]{0,0});
							totalData.setCellWidth(new int[]{20, 30});
							totalData.setData(othrTotalData);
							totalData.setBorderDetail(3);
							totalData.setHeaderTable(false);
							totalData.setBlankRowsBelow(1);
							rgs.addTableToDoc(totalData);
							
						}
						braVector = new Vector();   // reset the vector to new
						braTotObj = new Object[1][8];
						braTotObj = intZero(braTotObj);
						braCount++;
						i--;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	/**
	 * This method is used to generate ESI salary report for branch wise sorting
	 * @param rgs
	 * @param salary_esi
	 * @param salary_esi_total
	 * @param ledgerCode
	 * @param year
	 * @param esi
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator setBrnReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] salary_esi,
			Object[][] salary_esi_total, String ledgerCode,
			String year,ESICReport esi,  Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 2 ###############");
		try {
			String branchName = "";
			
			//Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
			LinkedHashMap<String, Object[][]> branchMap= (LinkedHashMap<String, Object[][]> )Utility.getGroupObj(salary_esi, new int[]{8});
			String[] uniqueKeys = new String[branchMap.size()];
			int noOfBrnDept = 0;
			for (Iterator iterator = branchMap.keySet().iterator(); iterator.hasNext();) {
				uniqueKeys[noOfBrnDept] = (String) iterator.next();
				Object[][] brnDataObj = branchMap.get(uniqueKeys[noOfBrnDept]);
				
								String colNames[] = { "Emp Id", "Employee Name","Insurance No",
						"Salary Days", "Monthly Gross", "Amount on which ESI is calculated ", "ESI Subscription @"+esiEmpPercentage+"%(Rs)",
				"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)"  };
				Object othrTotalData[][] = new Object[4][2];
				int[] alignment = new int[colNames.length];
				int[] cellwidth = new int[colNames.length];
				boolean[] bcellwrap = new boolean[colNames.length];
				for (int i = 0; i < colNames.length; i++) {
					// bcellAlign[i] = 1;
					// bcellWidth[i] = 40;
					if (i == 0) {
						alignment[i] = 0;
						cellwidth[i] = 15;
						bcellwrap[i]=true;
					} else if (i == 1) {
						alignment[i] = 0;
						cellwidth[i] = 30;
						bcellwrap[i]=false;
					}  /*else if (i == 2) {
						bcellAlign[i] = 0;
						bcellWidth[i] = 5;
					}else if (i == 4) {
						bcellAlign[i] = 2;
						bcellWidth[i] = 40;
					}*/else {
						alignment[i] = 2;
						cellwidth[i] = 15;
						bcellwrap[i]=true;
					}
				}
				
				if(brnDataObj!= null && brnDataObj.length > 0)
				{
					Object[][] brancheading = new Object[1][1];
					brancheading[0][0] = "Branch : "+uniqueKeys[noOfBrnDept] ;
					int[] cellWidthDateHeader = { 100 };
					int[] cellAlignDateHeader = { 0 };
					TableDataSet tablebranchheading = new TableDataSet();
					tablebranchheading.setData(brancheading);
					tablebranchheading.setCellWidth(cellWidthDateHeader);
					tablebranchheading.setCellAlignment(cellAlignDateHeader);  
					tablebranchheading.setBodyFontStyle(1);  
					tablebranchheading.setCellColSpan(new int[]{8});
				//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					tablebranchheading.setBorderDetail(0);
					rgs.addTableToDoc(tablebranchheading);
					
					
					Object[][] summaryData = new Object[1][1];
					summaryData[0][0] = "Salary Details " ;
					TableDataSet tableheadingDateData = new TableDataSet();
					tableheadingDateData.setData(summaryData);
					tableheadingDateData.setCellWidth(cellWidthDateHeader);
					tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
					tableheadingDateData.setBodyFontStyle(1);  
					tableheadingDateData.setCellColSpan(new int[]{8});
				//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					tableheadingDateData.setBorderDetail(0);
					rgs.addTableToDoc(tableheadingDateData);
					
					TableDataSet branchData = new TableDataSet();
					branchData.setHeader(colNames);
					branchData.setCellAlignment(alignment);
					branchData.setCellWidth(cellwidth);
					branchData.setData(brnDataObj);
					//branchData.setCellColSpan(new int[]{8});
					branchData.setBorder(true);
					branchData.setColumnSum(new int[]{3,4, 5, 6, 7});
					branchData.setCellNoWrap(bcellwrap);
					//branchData.setHeaderBGColor(new BaseColor(225, 225, 225));
					branchData.setHeaderTable(true);
					branchData.setHeaderBorderDetail(3);
					branchData.setBorderDetail(3);
					//branchData.setHeaderLines(true);
					branchData.setBlankRowsBelow(1);
					rgs.addTableToDoc(branchData);
					
					Object[][] braTotObj = new Object[1][8];
					braTotObj = intZero(braTotObj);
					
					braTotObj =totalSum(brnDataObj, null,  new int []{3,4,5,6,7});
					braTotObj[0][0] = "Total";
					braTotObj[0][1] = "";
					braTotObj[0][2] = "";
					
					othrTotalData[0][0] = "Employee's Contribution";
					othrTotalData[0][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][6])));
					othrTotalData[1][0] = "Employer's Contribution";
					othrTotalData[1][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][7])));
					othrTotalData[2][0] = "Total ESIC";
					othrTotalData[2][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][6]))
							+ Double.parseDouble(String
									.valueOf(braTotObj[0][7])));
					othrTotalData[3][0] = "Total No. of Employees";
					othrTotalData[3][1] = brnDataObj.length;
					
					TableDataSet totalData = new TableDataSet();
					totalData.setCellAlignment(new int[]{0,0});
					totalData.setCellWidth(new int[]{20, 30});
					totalData.setData(othrTotalData);
					//totalData.setHeaderTable(false);
					totalData.setBorderDetail(3);
					totalData.setBlankRowsBelow(1);
					rgs.addTableToDoc(totalData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	/**
	 * This method is used to generate ESI salary report for dept wise sorting
	 * @param rgs
	 * @param salary_esi
	 * @param salary_esi_total
	 * @param ledgerCode
	 * @param year
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator setDeptReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] salary_esi,
			Object[][] salary_esi_total, String ledgerCode,
			String year, Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 3 ###############");
		try {
			
			String brnQuery = "SELECT DISTINCT SAL_DEPT, DEPT_NAME FROM HRMS_SALARY_"
					+ year
					+ " "
					+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_SALARY_"
					+ year
					+ ".SAL_DEPT"
					+ " WHERE SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ") "
					+ "  GROUP BY SAL_DEPT,DEPT_NAME ORDER BY SAL_DEPT ";

			Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
			int braCount = 0;
			if (branch_data != null && branch_data.length > 0) {
				
				Vector braVector = new Vector();
				Object[][] braTotObj = new Object[1][8];
				braTotObj = intZero(braTotObj);
				//rg.addTableHeader("\n Salary Details :", 6, 0, 0, true);
				Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "Salary Details " ;
				int[] cellWidthDateHeader = { 100 };
				int[] cellAlignDateHeader = { 0 };
				TableDataSet tableheadingDateData = new TableDataSet();
				tableheadingDateData.setData(summaryData);
				tableheadingDateData.setCellWidth(cellWidthDateHeader);
				tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
				tableheadingDateData.setBodyFontStyle(1);  
				tableheadingDateData.setCellColSpan(new int[]{8});
			//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				tableheadingDateData.setBorderDetail(0);
				rgs.addTableToDoc(tableheadingDateData);
				for (int i = 0; i < salary_esi.length + 1; i++) {
				 if (braCount < branch_data.length) {
						Object[][] reportObj = new Object[braVector.size()][8];
						for (int j = 0; j < braVector.size(); j++) {
							reportObj[j] = (Object[]) braVector.get(j);
						}
						int[] cellwidth = { 15, 30,30, 15, 15, 15, 10, 15 };
						int[] alignment = { 0, 0, 0, 1, 1, 1, 1, 1 };
						String colNames[] = { "Emp Id", "Employee Name","Insurance No",
								"Salary Days", "Monthly Gross", "Amount on which ESI is calculated ", "ESI Subscription @"+esiEmpPercentage+"%(Rs)",
						"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)"  };
						Object [][]othrTotalData=new Object[4][2];
						if(reportObj!= null && reportObj.length > 0)
						{
							Object[][] headertitle = new Object[1][1];
							headertitle[0][0] = "Salary Details " ;
							int[] cellWidthDateHeader1 = { 100 };
							int[] cellAlignDateHeader1= { 0 };
							TableDataSet tableheadingData = new TableDataSet();
							tableheadingData.setData(headertitle);
							tableheadingData.setCellWidth(cellWidthDateHeader1);
							tableheadingData.setCellAlignment(cellAlignDateHeader1);  
							tableheadingData.setBodyFontStyle(1);  
							tableheadingData.setCellColSpan(new int[]{8});
						//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
							tableheadingData.setBorderDetail(0);
							//tableheadingDateData.setHeaderTable(true);
							tableheadingData.setBlankRowsAbove(1);
							rgs.addTableToDoc(tableheadingData);
							
							TableDataSet reportData = new TableDataSet();
							reportData.setHeader(colNames);
							reportData.setCellAlignment(alignment);
							reportData.setCellWidth(cellwidth);
							reportData.setData(reportObj);
							reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
							reportData.setBorder(true);
							//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
							reportData.setHeaderTable(true);
							reportData.setHeaderBorderDetail(3);
							reportData.setBorderDetail(3);
							reportData.setBlankRowsBelow(1);
							rgs.addTableToDoc(reportData);
							
							
							othrTotalData[0][0] = "Employee's Contribution";
							othrTotalData[0][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][6])));
							othrTotalData[1][0] = "Employer's Contribution";
							othrTotalData[1][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][7])));
							othrTotalData[2][0] = "Total ESIC";
							othrTotalData[2][1] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(braTotObj[0][6]))
									+ Double.parseDouble(String
											.valueOf(braTotObj[0][7])));
							othrTotalData[3][0] = "Total No. of Employees";
							othrTotalData[3][1] = reportObj.length;
							
							//rg.tableBody(othrTotalData, colWidth, colAlign, 10);
							
							TableDataSet totalData = new TableDataSet();
							totalData.setCellAlignment(new int[]{0,0});
							totalData.setCellWidth(new int[]{20, 30});
							totalData.setData(othrTotalData);
							totalData.setHeaderTable(false);
							totalData.setBorderDetail(3);
							totalData.setBlankRowsBelow(1);
							rgs.addTableToDoc(totalData);
						}
						braVector = new Vector();
						braTotObj = new Object[1][8];
						braTotObj = intZero(braTotObj);
						braCount++;
						i--;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	/**
	 *  This method is used to generate ESI salary report when no sorting option selected
	 * @param rgs
	 * @param salary_esi
	 * @param salary_esi_total
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator setSalReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] salary_esi,
			Object[][] salary_esi_total, Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 4 ###############");
		try {
			
			String colNames[] = { "Emp Id", "Employee Name","Insurance No",
					"Salary Days", "Monthly Gross", "Amount on which ESI is calculated ", "ESI Subscription @"+esiEmpPercentage+"%(Rs)",
					"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)"  };
			//rg.addTableHeader("\n ", 6, 0, 0, true);
			
			int[] alignment = new int[colNames.length];
			int[] cellwidth = new int[colNames.length];
			boolean[] bcellwrap = new boolean[colNames.length];
			for (int i = 0; i < colNames.length; i++) {
				if (i == 0) {
					alignment[i] = 0;
					cellwidth[i] = 15;
					bcellwrap[i]=true;
				} else if (i == 1) {
					alignment[i] = 0;
					cellwidth[i] = 30;
					bcellwrap[i]=false;
				}else {
					alignment[i] = 2;
					cellwidth[i] = 40;
					bcellwrap[i]=false;
				}
			}
			
			Object totalEsi[][] = new Object[1][8];

			Object othrTotalData[][] = new Object[4][2];
			totalEsi = totalSum(salary_esi, null, new int[]{3,4,5,6,7});
			if (salary_esi_total != null && salary_esi_total.length > 0) {
				totalEsi[0][0] = "Total";
				totalEsi[0][1] = "";
				totalEsi[0][2] = "";

				othrTotalData[0][0] = "Employee's Contribution";
				othrTotalData[0][1] = Utility.twoDecimals(Double
						.parseDouble(String.valueOf(totalEsi[0][6])));
				othrTotalData[1][0] = "Employer's Contribution";
				othrTotalData[1][1] = Utility.twoDecimals(Double
						.parseDouble(String.valueOf(totalEsi[0][7])));
				othrTotalData[2][0] = "Total ESIC";
				othrTotalData[2][1] = Utility.twoDecimals(Double
						.parseDouble(String.valueOf(totalEsi[0][6]))
						+ Double.parseDouble(String
								.valueOf(totalEsi[0][7])));
				othrTotalData[3][0] = "Total No. of Employees";
				othrTotalData[3][1] = salary_esi.length;
			}
			
			Object[][] headertitle = new Object[1][1];
			headertitle[0][0] = "Salary Details " ;
			int[] cellWidthDateHeader1 = { 100 };
			int[] cellAlignDateHeader1= { 0 };
			TableDataSet tableheadingData = new TableDataSet();
			tableheadingData.setData(headertitle);
			tableheadingData.setCellWidth(cellWidthDateHeader1);
			tableheadingData.setCellAlignment(cellAlignDateHeader1);  
			tableheadingData.setBodyFontStyle(1);  
			tableheadingData.setCellColSpan(new int[]{8});
			tableheadingData.setBorderDetail(3);
			tableheadingData.setBlankRowsAbove(1);
			rgs.addTableToDoc(tableheadingData);
					
			TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(salary_esi);
			reportData.setCellNoWrap(bcellwrap);
			reportData.setBorder(true);
			reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
			reportData.setHeaderTable(true);
			reportData.setHeaderBorderDetail(3);
			reportData.setBorderDetail(3);
			reportData.setBlankRowsBelow(1);
			rgs.addTableToDoc(reportData);
			
			
			TableDataSet totalData = new TableDataSet();
			totalData.setCellAlignment(new int[]{0, 0});
			totalData.setCellWidth(new int[]{20, 30});
			totalData.setData(othrTotalData);
			totalData.setHeaderTable(false);
			totalData.setBorderDetail(3);
			totalData.setBlankRowsBelow(1);
			rgs.addTableToDoc(totalData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rgs;
	}
	/**
	 * This method is used to generate ESI consolidated arrears report 
	 * @param rgs
	 * @param esi
	 * @param esi_code
	 * @param arrCode
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getEsiReportConsolidatedArrears(org.paradyne.lib.ireportV2.ReportGenerator rgs,
			ESICReport esi, Object[][] esi_code, String arrCode, Double esiEmpPercentage, Double esiCompanyPercentage) {
		Object[][] arrAmtObj = null;
		// get the arrears amount
		if(!arrCode.equals("")){
			arrAmtObj = getArrearsAmt(esi, esi_code, arrCode);
		}
		String arrsTotQry = " SELECT SUM(SUMCRD),SUM(ARRAMT),SUM(ESIAMT),SUM(COMPESI) FROM (SELECT EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME AS NAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') AS MONTH,ARREARS_YEAR,ARREARS_DAYS,DECODE(ARREARS_TYPE,'M','Monthly','Promotional') as ARREARS_TYPE "
				+ " ,NVL(SUM(CREDIT_AMT),0) SUMCRD,ARREARS_CREDITS_AMT ARRAMT, NVL(HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_AMT,0.00) ESIAMT, NVL(ARREARS_CREDITS_AMT,0.00) * "
				+ esi_code[0][2]
				+ "/100 COMPESI,EMP_CENTER,EMP_DEPT "
				+ " FROM HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ " "
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
				+ " INNER JOIN HRMS_ARREARS_"+esi.getYear()+" ON (HRMS_ARREARS_"+esi.getYear()+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_CODE " 
				+ " AND HRMS_ARREARS_"+esi.getYear()+".EMP_ID =HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_EMP_ID " 
				+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_MONTH = HRMS_ARREARS_"+esi.getYear()+".ARREARS_MONTH "
				+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_YEAR = HRMS_ARREARS_"+esi.getYear()+".ARREARS_YEAR) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_EMP_ID) "
				+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_EMP_ID = HRMS_EMP_CREDIT.EMP_ID)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)   "
				+ " WHERE ARREARS_PAID_MONTH = "
				+ esi.getMonth()
				+ " AND ARREARS_PAID_YEAR="
				+ esi.getYear()
				+ "	AND CREDIT_PERIODICITY = 'M' AND "
				+ " HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND ARREARS_AMT>0 AND ARREARS_DEBIT_CODE= "
				+ esi_code[0][0];
			if(!esi.getOnHold().equals("A")){
				arrsTotQry +=" AND ARREARS_ONHOLD='"+esi.getOnHold()+"'";
			}
				arrsTotQry=setQueryFilters(esi,arrsTotQry);
				arrsTotQry+= " GROUP BY EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME,ARREARS_MONTH,ARREARS_YEAR ,ARREARS_DAYS,ARREARS_TYPE,ARREARS_AMT,ARREARS_CREDITS_AMT,EMP_CENTER,EMP_DEPT "
				+ " ORDER BY UPPER(NAME))";
		Object arrAmtObj_total[][] = null;
		if(!arrCode.equals(""))
			arrAmtObj_total = getSqlModel().getSingleResult(arrsTotQry);
		if (arrAmtObj != null && arrAmtObj.length > 0) {
			// print the report on different sorting options
			if (esi.getBranchFlag().equals("true") && esi.getDeptFlag().equals("true")) {
				rgs = setBrnDeptArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
			} else if (esi.getBranchFlag().equals("true")) {
				logger.info("esi.getCheckFlag().=="+esi.getCheckFlag());
				if(esi.getCheckFlag().equals("Y")){
					rgs = setBrnArrReportConsolidated(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
				}else{
					rgs = setBrnArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
				}
			} else if (esi.getDeptFlag().equals("true")) {
				rgs = setDeptArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
			} else if(esi.getCheckFlag().equals("Y")){
					rgs = setArrReportForConsolidated(rgs, arrAmtObj, arrAmtObj_total, esiEmpPercentage, esiCompanyPercentage);
				}else{
					rgs = setArrReport(rgs, arrAmtObj, arrAmtObj_total, esiEmpPercentage, esiCompanyPercentage);
				}
		}
		return rgs;
	}
	
	/**
	 * This method is used to generate ESI arrears report for branch wise & dept wise sorting
	 * @param rgs
	 * @param arrAmtObj
	 * @param arrTotObj
	 * @param esi
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
		public org.paradyne.lib.ireportV2.ReportGenerator setBrnDeptArrReport(org.paradyne.lib.ireportV2.ReportGenerator rgs,Object[][] arrAmtObj,
				Object[][] arrTotObj,  ESICReport esi, Double esiEmpPercentage, Double esiCompanyPercentage) {
			System.out.println("################# 5 ###############");
			String brnQuery = "SELECT DISTINCT SAL_EMP_CENTER,CENTER_NAME,SAL_DEPT,DEPT_NAME FROM HRMS_SALARY_"
					+ esi.getYear()
					+ " "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID= HRMS_SALARY_"
					+ esi.getYear()
					+ ".sal_emp_center"
					+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_SALARY_"
					+ esi.getYear()
					+ ".SAL_DEPT"
					+ " WHERE SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
					+ esi.getMonth()
					+ " AND LEDGER_YEAR="
					+ esi.getYear()
					+ "  AND LEDGER_DIVISION="
					+ esi.getDivCode()
					+ ") "
					+ " AND EMP_ID IN (SELECT HRMS_ARREARS_"
					+ esi.getYear()
					+ ".EMP_ID 	FROM HRMS_ARREARS_"
					+ esi.getYear()
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_"
					+ esi.getYear()
					+ ".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE) "
					+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH = "
					+ esi.getMonth()
					+ " AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION = "
					+ esi.getDivCode()
					+ " AND "
					+ " HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR = "
					+ esi.getYear()
					+ ") "
					+ "  GROUP BY SAL_EMP_CENTER,CENTER_NAME,SAL_DEPT,DEPT_NAME ORDER BY SAL_EMP_CENTER,SAL_DEPT ";
			Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
			int braCount = 0;
			//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
			int[] cellwidth = { 15, 20, 8, 8, 10, 10, 10, 10, 10,14};
			int[] alignment = { 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 };
			String colNames[] = { "Emp Id", "Employee Name",
					"Month", "Year", "Arrears Days",
					"Arrears type", "Monthly Gross",
					"Arrears Amount", "ESI Subscription @"+esiEmpPercentage+"%(Rs)", "Employer's Contribution @"+esiCompanyPercentage+"%(Rs)" };
			if (branch_data != null && branch_data.length > 0) {
				Vector braVector = new Vector();
				Object[][] braTotObj = new Object[1][10];
				braTotObj = intZero(braTotObj);

				for (int i = 0; i < arrAmtObj.length + 1; i++) {
					if (i < arrAmtObj.length
							&& String.valueOf(branch_data[braCount][0]).equals(
									String.valueOf(arrAmtObj[i][10]))
							&& String.valueOf(branch_data[braCount][2]).equals(
									String.valueOf(arrAmtObj[i][11]))) {
						braVector.add(arrAmtObj[i]);
						braTotObj[0][0] = "Total";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";
						braTotObj[0][3] = "";
						braTotObj[0][4] = "";
						braTotObj[0][5] = "";
						braTotObj[0][6] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(braTotObj[0][6]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][6])));
						braTotObj[0][7] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][7]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][7])));
						braTotObj[0][8] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][8]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][8])));

						braTotObj[0][9] = Math.round(Double
								.parseDouble(String.valueOf(braTotObj[0][9]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][9])));

					} else if (braCount < branch_data.length) {
						Object[][] reportObj = new Object[braVector.size()][10];
						for (int j = 0; j < braVector.size(); j++) {
							reportObj[j] = (Object[]) braVector.get(j);
						}
						if(reportObj!= null && reportObj.length > 0)
						{
							
							TableDataSet reportData = new TableDataSet();
							reportData.setHeader(colNames);
							reportData.setCellAlignment(alignment);
							reportData.setCellWidth(cellwidth);
							reportData.setData(reportObj);
							//reportData.setCellColSpan(new int[]{8});
							reportData.setBorder(true);
							reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
							reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
							reportData.setHeaderTable(true);
							reportData.setHeaderBorderDetail(3);
							reportData.setBlankRowsBelow(1);
							reportData.setBorderDetail(3);
							rgs.addTableToDoc(reportData);
							
						}
						braVector = new Vector();
						braTotObj = new Object[1][10];
						braTotObj = intZero(braTotObj);
						braCount++;
						i--;
					}
				}
			}
			return rgs;
		}
	
		/**
		 * this method is used to generate arrears records if report option All selected
		 * @param rgs
		 * @param esi
		 * @param esi_code
		 * @param arrCode
		 * @param esiEmpPercentage
		 * @param esiCompanyPercentage
		 * @return
		 */
		public org.paradyne.lib.ireportV2.ReportGenerator getEsiReportArrears(org.paradyne.lib.ireportV2.ReportGenerator rgs,
				ESICReport esi, Object[][] esi_code, String arrCode, Double esiEmpPercentage, Double esiCompanyPercentage) {
			Object[][] arrAmtObj = null;
			if(!arrCode.equals(""))
				arrAmtObj = getArrearsAmt(esi, esi_code, arrCode);
			String arrsTotQry = " SELECT SUM(SUMCRD),SUM(ARRAMT),SUM(ESIAMT),SUM(COMPESI) FROM (SELECT EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME AS NAME,TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') AS MONTH,ARREARS_YEAR,ARREARS_DAYS,DECODE(ARREARS_TYPE,'M','Monthly','Promotional') as ARREARS_TYPE "
					+ " ,NVL(SUM(CREDIT_AMT),0) SUMCRD,ARREARS_CREDITS_AMT ARRAMT, NVL(HRMS_ARREARS_DEBIT_"
					+ esi.getYear()
					+ ".ARREARS_AMT,0.00) ESIAMT, NVL(ARREARS_CREDITS_AMT,0.00) * "
					+ esi_code[0][2]
					+ "/100 COMPESI,EMP_CENTER,EMP_DEPT "
					+ " FROM HRMS_ARREARS_DEBIT_"
					+ esi.getYear()
					+ " "
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"
					+ esi.getYear()
					+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
					+ " INNER JOIN HRMS_ARREARS_"+esi.getYear()+" ON (HRMS_ARREARS_"+esi.getYear()+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_CODE " 
					+ " AND HRMS_ARREARS_"+esi.getYear()+".EMP_ID =HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_EMP_ID " 
					+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_MONTH = HRMS_ARREARS_"+esi.getYear()+".ARREARS_MONTH "
					+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_YEAR = HRMS_ARREARS_"+esi.getYear()+".ARREARS_YEAR) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"
					+ esi.getYear()
					+ ".ARREARS_EMP_ID) "
					+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_ARREARS_DEBIT_"
					+ esi.getYear()
					+ ".ARREARS_EMP_ID = HRMS_EMP_CREDIT.EMP_ID)"
					+" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)   "
					+ " WHERE ARREARS_PAID_MONTH = "
					+ esi.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ esi.getYear()
					+ "	AND CREDIT_PERIODICITY = 'M' AND "
					+ " HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND ARREARS_AMT>0 AND ARREARS_DEBIT_CODE= "
					+ esi_code[0][0];
				if(!esi.getOnHold().equals("A")){
					arrsTotQry +=" AND ARREARS_ONHOLD='"+esi.getOnHold()+"'";
				}
					arrsTotQry=setQueryFilters(esi,arrsTotQry);
					arrsTotQry+= " GROUP BY EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME,ARREARS_MONTH,ARREARS_YEAR ,ARREARS_DAYS,ARREARS_TYPE,ARREARS_AMT,ARREARS_CREDITS_AMT,EMP_CENTER,EMP_DEPT "
					+ " ORDER BY UPPER(NAME))";
			Object arrAmtObj_total[][] = null;
			if(!arrCode.equals(""))
				arrAmtObj_total = getSqlModel().getSingleResult(arrsTotQry);
			// print the report on different sorting options
			if (arrAmtObj != null && arrAmtObj.length > 0) {
				if (esi.getBranchFlag().equals("true")
						&& esi.getDeptFlag().equals("true")) {   // for both branchwise & dept wise
					rgs = setBrnDeptArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
				} else if (esi.getBranchFlag().equals("true")) {  // for branch wise
					rgs = setBrnArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
				} else if (esi.getDeptFlag().equals("true")) { // for dept wise
					rgs = setDeptArrReport(rgs, arrAmtObj, arrAmtObj_total, esi, esiEmpPercentage, esiCompanyPercentage);
				} else{
					rgs = setArrReport(rgs, arrAmtObj, arrAmtObj_total, esiEmpPercentage, esiCompanyPercentage);
				}
			}
			return rgs;
		}
	/**
	 * This method is used to generate ESI arrears report for branch wise sorting
	 * @param rgs
	 * @param arrAmtObj
	 * @param totalObj
	 * @param esi
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
		public org.paradyne.lib.ireportV2.ReportGenerator setBrnArrReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] arrAmtObj,
				Object[][] totalObj, ESICReport esi, Double esiEmpPercentage, Double esiCompanyPercentage) {
			System.out.println("################# 6 ###############");
			
			try {
				//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
				int braCount = 0;
				LinkedHashMap<String, Object[][]> branchMap = (LinkedHashMap<String, Object[][]>) Utility
						.getGroupObj(arrAmtObj, new int[] { 11 });
				String[] uniqueKeys = new String[branchMap.size()];
				int noOfBrnDept = 0;
				for (Iterator iterator = branchMap.keySet().iterator(); iterator
						.hasNext();) {
					uniqueKeys[noOfBrnDept] = (String) iterator.next();
					Object[][] brnDataObj = branchMap
							.get(uniqueKeys[noOfBrnDept]);

					int[] cellwidth = { 15, 30, 15, 15, 15, 15, 10, 15 };
					int[] alignment = { 0, 0, 1, 1, 1, 1, 1, 1 };
					String colNames[] = {
							"Emp Id",
							"Employee Name",
							"Insurance No",
							"Salary Days",
							"Monthly Gross",
							"Amount on which ESI is calculated ",
							"ESI Subscription @" + esiEmpPercentage + "%(Rs)",
							"Employer's Contribution @" + esiCompanyPercentage
									+ "%(Rs)" };
					Object othrTotalData[][] = new Object[4][2];
					if (brnDataObj != null && brnDataObj.length > 0) {

						TableDataSet reportData = new TableDataSet();
						reportData.setHeader(colNames);
						reportData.setCellAlignment(alignment);
						reportData.setCellWidth(cellwidth);
						reportData.setData(brnDataObj);
						reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
						reportData.setBorder(true);
						//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
						reportData.setBorderDetail(3);
						reportData.setHeaderTable(true);
						reportData.setHeaderBorderDetail(3);
						reportData.setBlankRowsBelow(1);
						rgs.addTableToDoc(reportData);

						Object[][] braTotObj = new Object[1][11];
						braTotObj = intZero(braTotObj);

						braTotObj = totalSum(brnDataObj, null, new int[] { 3,
								4, 5, 6, 7 });
						braTotObj[0][0] = "Total";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";

						othrTotalData[0][0] = "Employee's Contribution";
						othrTotalData[0][1] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(braTotObj[0][6])));
						othrTotalData[1][0] = "Employer's Contribution";
						othrTotalData[1][1] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(braTotObj[0][7])));
						othrTotalData[2][0] = "Total ESIC";
						othrTotalData[2][1] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(braTotObj[0][6]))
								+ Double.parseDouble(String
										.valueOf(braTotObj[0][7])));
						othrTotalData[3][0] = "Total No. of Employees";
						othrTotalData[3][1] = brnDataObj.length;

						TableDataSet totalData = new TableDataSet();
						totalData.setCellAlignment(new int[]{0,0});
						totalData.setCellWidth(new int[]{20, 30});
						totalData.setData(othrTotalData);
						totalData.setHeaderTable(false);
						totalData.setBlankRowsBelow(1);
						totalData.setBorderDetail(3);
						rgs.addTableToDoc(totalData);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rgs;
		}	
		/**
		 * This method is used to generate ESI consolidated arrears report for branch wise sorting
		 * @param rgs
		 * @param arrAmtObj
		 * @param totalObj
		 * @param esi
		 * @param esiEmpPercentage
		 * @param esiCompanyPercentage
		 * @return
		 */
		public org.paradyne.lib.ireportV2.ReportGenerator setBrnArrReportConsolidated(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] arrAmtObj,
				Object[][] totalObj, ESICReport esi, Double esiEmpPercentage, Double esiCompanyPercentage) {
			//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
			System.out.println("################# 7 ###############");
			try{
				arrAmtObj= Utility.consolidateArrearsObject(arrAmtObj, null, 0, new int[] {3,5,6,7}, 12);
				arrAmtObj = Utility.removeColumns(arrAmtObj, new int[]{8,9,10});
			LinkedHashMap<String, Object[][]> branchMap= (LinkedHashMap<String, Object[][]> )Utility.getGroupObj(arrAmtObj, new int[]{8});
			String[] uniqueKeys = new String[branchMap.size()];
			int noOfBrnDept = 0;
			for (Iterator iterator = branchMap.keySet().iterator(); iterator.hasNext();) {
				uniqueKeys[noOfBrnDept] = (String) iterator.next();
				Object[][] brnDataObj = branchMap.get(uniqueKeys[noOfBrnDept]);
				
				int[] cellwidth = { 15, 30, 15, 15, 15, 15, 10, 15 };
				int[] alignment = { 0, 0, 1, 1, 1, 1, 1, 1 };
				String colNames[] = { "Emp Id", "Employee Name","Insurance No",
						"Salary Days", "Monthly Gross", "Amount on which ESI is calculated ", "ESI Subscription @"+esiEmpPercentage+"%(Rs)",
				"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)"  };
				Object othrTotalData[][] = new Object[4][2];
				if(brnDataObj!= null && brnDataObj.length > 0) {
					
					TableDataSet reportData = new TableDataSet();
					reportData.setHeader(colNames);
					reportData.setCellAlignment(alignment);
					reportData.setCellWidth(cellwidth);
					reportData.setData(brnDataObj);
					reportData.setBorder(true);
					reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
					reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportData.setHeaderTable(true);
					reportData.setHeaderBorderDetail(3);
					reportData.setBlankRowsBelow(1);
					reportData.setBorderDetail(3);
					rgs.addTableToDoc(reportData);
					Object[][] braTotObj = new Object[1][8];
					braTotObj = intZero(braTotObj);
					
					braTotObj =totalSum(brnDataObj, null,  new int []{3,4,5,6,7});
					braTotObj[0][0] = "Total";
					braTotObj[0][1] = "";
					braTotObj[0][2] = "";
					
					
					othrTotalData[0][0] = "Employee's Contribution";
					othrTotalData[0][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][6])));
					othrTotalData[1][0] = "Employer's Contribution";
					othrTotalData[1][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][7])));
					othrTotalData[2][0] = "Total ESIC";
					othrTotalData[2][1] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(braTotObj[0][6]))
							+ Double.parseDouble(String
									.valueOf(braTotObj[0][7])));
					othrTotalData[3][0] = "Total No. of Employees";
					othrTotalData[3][1] = brnDataObj.length;
					
					
					TableDataSet totalData = new TableDataSet();
					totalData.setCellAlignment(new int[]{0,0});
					totalData.setCellWidth(new int[]{20, 30});
					totalData.setData(othrTotalData);
					totalData.setBorder(true);
					totalData.setHeaderTable(false);
					totalData.setBorderDetail(3);
					totalData.setBlankRowsBelow(1);
					rgs.addTableToDoc(totalData);
					
				}
			}
					} catch (Exception e) {
						e.printStackTrace();
					}
			return rgs;
		}
		/**
		 * This method is used to generate ESI arrears report for dept wise sorting
		 * @param rgs
		 * @param arrAmtObj
		 * @param totalObj
		 * @param esi
		 * @param esiEmpPercentage
		 * @param esiCompanyPercentage
		 * @return
		 */
		public org.paradyne.lib.ireportV2.ReportGenerator setDeptArrReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] arrAmtObj,
				Object[][] totalObj, ESICReport esi, Double esiEmpPercentage, Double esiCompanyPercentage) {
			System.out.println("################# 8 ###############");
			try {
				String brnQuery = "SELECT DISTINCT SAL_DEPT,DEPT_NAME FROM HRMS_SALARY_"
						+ esi.getYear()
						+ " "
						+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_SALARY_"
						+ esi.getYear()
						+ ".SAL_DEPT"
						+ "  WHERE SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
						+ esi.getMonth()
						+ " AND LEDGER_YEAR="
						+ esi.getYear()
						+ "  AND LEDGER_DIVISION="
						+ esi.getDivCode()
						+ ") "
						+ " AND EMP_ID IN (SELECT HRMS_ARREARS_"
						+ esi.getYear()
						+ ".EMP_ID 	FROM HRMS_ARREARS_"
						+ esi.getYear()
						+ " "
						+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_"
						+ esi.getYear()
						+ ".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE) "
						+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION = "
						+ esi.getDivCode()
						+ " AND "
						+ " HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR = "
						+ esi.getYear()
						+ ") "
						+ " GROUP BY SAL_DEPT,DEPT_NAME ORDER BY SAL_DEPT ";
				Object[][] branch_data = getSqlModel()
						.getSingleResult(brnQuery);
				int braCount = 0;
				if (branch_data != null && branch_data.length > 0) {
					Vector braVector = new Vector();
					Object[][] braTotObj = new Object[1][10];
					braTotObj = intZero(braTotObj);
					//rg.addText("Arrears Details :", 6, 0, 0);
					//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
					for (int i = 0; i < arrAmtObj.length + 1; i++) {
						if (i < arrAmtObj.length
								&& String
										.valueOf(branch_data[braCount][0])
										.equals(
												String
														.valueOf(arrAmtObj[i][11]))) {
							braVector.add(arrAmtObj[i]);
							braTotObj[0][0] = "Total";
							braTotObj[0][1] = "";
							braTotObj[0][2] = "";
							braTotObj[0][3] = "";
							braTotObj[0][4] = "";
							braTotObj[0][5] = "";
							braTotObj[0][6] = Math.round(Double
									.parseDouble(String
											.valueOf(braTotObj[0][6]))
									+ Double.parseDouble(String
											.valueOf(arrAmtObj[i][6])));
							braTotObj[0][7] = Math.round(Double
									.parseDouble(String
											.valueOf(braTotObj[0][7]))
									+ Double.parseDouble(String
											.valueOf(arrAmtObj[i][7])));
							braTotObj[0][8] = Math.round(Double
									.parseDouble(String
											.valueOf(braTotObj[0][8]))
									+ Double.parseDouble(String
											.valueOf(arrAmtObj[i][8])));

							braTotObj[0][9] = Math.round(Double
									.parseDouble(String
											.valueOf(braTotObj[0][9]))
									+ Double.parseDouble(String
											.valueOf(arrAmtObj[i][9])));

						} else if (braCount < branch_data.length) {
							Object[][] reportObj = new Object[braVector.size()][7];
							for (int j = 0; j < braVector.size(); j++) {
								reportObj[j] = (Object[]) braVector.get(j);
							}
							int[] cellwidth = { 15, 20, 8, 8, 10, 10, 10, 10,
									10, 16 };
							int[] alignment = { 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 };
							String colNames[] = {
									"Emp Id",
									"Employee Name",
									"Month",
									"Year",
									"Arrears Days",
									"Arrears type",
									"Monthly Gross",
									"Arrears Amount",
									"ESI Subscription @" + esiEmpPercentage
											+ "%(Rs)",
									"Employer's Contribution @"
											+ esiCompanyPercentage + "%(Rs)" };
							if (reportObj != null && reportObj.length > 0) {

								TableDataSet reportData = new TableDataSet();
								reportData.setHeader(colNames);
								reportData.setCellAlignment(alignment);
								reportData.setCellWidth(cellwidth);
								reportData.setData(reportObj);
								reportData.setBorder(true);
								reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
								//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
								reportData.setHeaderTable(true);
								reportData.setHeaderBorderDetail(3);
								reportData.setBorderDetail(3);
								reportData.setBlankRowsBelow(1);
								rgs.addTableToDoc(reportData);

							}
							braVector = new Vector();
							braTotObj = new Object[1][10];
							braTotObj = intZero(braTotObj);
							braCount++;
							i--;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rgs;
		}
		/**
		 * This method is used to generate ESI arrears report with no sorting option
		 * @param rgs
		 * @param arrAmtObj
		 * @param arrTotObj
		 * @param esiEmpPercentage
		 * @param esiCompanyPercentage
		 * @return
		 */
		public org.paradyne.lib.ireportV2.ReportGenerator setArrReport(org.paradyne.lib.ireportV2.ReportGenerator rgs,Object[][] arrAmtObj, Object[][] arrTotObj, Double esiEmpPercentage, Double esiCompanyPercentage) {
			System.out.println("################# 9 ###############");
			try {
				int[] cellwidth = { 15, 20, 8, 8, 10, 10, 10, 10, 10, 14, 15 };
				int[] alignment = { 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1 };
				String colNames[] = {
						"Emp Id",
						"Employee Name",
						"Insurance NO.",
						"Arrears Days",
						"Monthly Gross",
						"Arrears Amount",
						"ESI Subscription @" + esiEmpPercentage + "%(Rs)",
						"Employer's Contribution @" + esiCompanyPercentage
								+ "%(Rs)", "Month", "Year", "Arrears type" };
				//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
				Object totalEsi[][] = new Object[1][11];
				Object arrAmtObjFinal[][] = new Object[arrAmtObj.length][11];
				for (int j = 0; j < arrAmtObjFinal.length; j++) {
					for (int k = 0; k < arrAmtObjFinal[0].length; k++) {
						arrAmtObjFinal[j][k] = arrAmtObj[j][k];
					}
				}
				totalEsi = totalSum(arrAmtObjFinal, null, new int[] { 3, 4, 5,
						6, 7 });
				//arrAmtObj = Utility.consolidateArrearsObject(arrAmtObj, null, 0, new int[] {}, 11);
				Object othrTotalData[][] = new Object[4][2];
				if (arrTotObj != null && arrTotObj.length > 0) {
					totalEsi[0][0] = "Total";
					totalEsi[0][1] = "";
					totalEsi[0][2] = "";
					//totalEsi[0][3] = "";
					totalEsi[0][8] = "";
					totalEsi[0][9] = "";
					totalEsi[0][10] = "";

					othrTotalData[0][0] = "Employee's Contribution";
					othrTotalData[0][1] = Utility.twoDecimals(Double.parseDouble(String
							.valueOf(totalEsi[0][6])));
					othrTotalData[1][0] = "Employer's Contribution";
					othrTotalData[1][1] = Utility.twoDecimals(Double.parseDouble(String
							.valueOf(totalEsi[0][7])));
					othrTotalData[2][0] = "Total ESIC";
					othrTotalData[2][1] = Math
							.round(Double.parseDouble(String
									.valueOf(totalEsi[0][6]))
									+ Double.parseDouble(String
											.valueOf(totalEsi[0][7])));
					othrTotalData[3][0] = "Total No. of Employees";
					othrTotalData[3][1] = arrAmtObj.length;
				}
				
				TableDataSet reportData = new TableDataSet();
				reportData.setHeader(colNames);
				reportData.setCellAlignment(alignment);
				reportData.setCellWidth(cellwidth);
				reportData.setData(arrAmtObjFinal);
				reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
				reportData.setBorder(true);
				//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportData.setHeaderTable(true);
				reportData.setHeaderBorderDetail(3);
				reportData.setBorderDetail(3);
				reportData.setBlankRowsBelow(1);
				rgs.addTableToDoc(reportData);
				
				
				TableDataSet totalData = new TableDataSet();
				totalData.setCellAlignment(new int[]{0,0});
				totalData.setCellWidth(new int[]{20, 30});
				totalData.setData(othrTotalData);
				totalData.setHeaderTable(false);
				totalData.setBorderDetail(3);
				totalData.setBlankRowsBelow(1);
				rgs.addTableToDoc(totalData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rgs;
		}
		
	/**
	 * This method is used to get the filtered query
	 * @param esi
	 * @param query
	 * @return
	 */	
	public String setQueryFilters(ESICReport esi, String query) {
		if (!(esi.getBrnCode() == null || esi.getBrnCode().equals("")))
			query += " AND EMP_CENTER IN (" + esi.getBrnCode()+") ";
		if (!(esi.getDeptCode() == null || esi.getDeptCode().equals("")))
			query += " AND EMP_DEPT IN (" + esi.getDeptCode()+") ";
		if (!(esi.getTypeCode() == null || esi.getTypeCode().equals("")))
			query += " AND EMP_TYPE IN (" + esi.getTypeCode()+") ";
		if (!(esi.getPayBillNo() == null || esi.getPayBillNo().equals("")))
			query += " AND EMP_PAYBILL IN (" + esi.getPayBillNo()+") ";
		if (!(esi.getCadreCode() == null || esi.getCadreCode().equals("")))
			query += " AND EMP_CADRE IN (" + esi.getCadreCode()+") ";
		
		return query;
	}
	/**
	 * Used to add the amount of specified indexes
	 * @param obj1
	 * @param obj2
	 * @param x
	 * @return
	 */
	public Object[][] totalSum(Object[][] obj1,Object[][] obj2, int[] x){
		Object[][] totalObj;
		totalObj = new Object[1][obj1[0].length];
		try {
			double[][] sumObj = new double[1][obj1[0].length];
			for (int i = 0; i < sumObj.length; i++) {
				sumObj[0][i] = 0;
			}
			for (int i = 0; i < obj1.length; i++) {
				for (int j = 0; j < x.length; j++) {
					sumObj[0][x[j]] += Double.parseDouble(String
							.valueOf(obj1[i][x[j]]));
				}
			}
			if (obj2 != null) {
				for (int i = 0; i < obj2.length; i++) {
					for (int j = 0; j < x.length; j++) {
						sumObj[0][x[j]] += Double.parseDouble(String
								.valueOf(obj2[i][x[j]]));
					}
				}
			}
			for (int i = 0; i < sumObj.length; i++) {
				for (int j = 0; j < sumObj[0].length; j++) {
					totalObj[i][j] = Utility.twoDecimals(Double.parseDouble(String
							.valueOf(sumObj[i][j])));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalObj;
	}
	/**
	 * Get the ESIC details deducted in arrears for specified arrears code
	 * @param esi
	 * @param esi_code
	 * @param arrCode
	 * @return
	 */
	public Object[][] getArrearsAmt(ESICReport esi, Object[][] esi_code, String arrCode) {
		
		String arrearsQry = " SELECT EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME AS NAME,NVL(SAL_ESCINUMBER,'NA') AS ESIC_NO,ARREARS_DAYS,NVL(SUM(CREDIT_AMT),0)," 
				+" ARREARS_CREDITS_AMT ,NVL(HRMS_ARREARS_DEBIT_"+ esi.getYear()+ ".ARREARS_AMT,0.00),CEIL(NVL(ARREARS_CREDITS_AMT,0.00) * "
				+ esi_code[0][2]
				+ "/100),TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MONTH') AS MONTH," 
				+" ARREARS_YEAR,DECODE(ARREARS_TYPE,'M','Monthly','Promotional') as ARREARS_TYPE,NVL(CENTER_NAME,' '),SAL_DEPT,HRMS_EMP_OFFC.EMP_ID "
				//+ " , "
				+ " FROM HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ " "
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
				+ " INNER JOIN HRMS_ARREARS_"+esi.getYear()+" ON (HRMS_ARREARS_"+esi.getYear()+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_CODE " 
				+ " AND HRMS_ARREARS_"+esi.getYear()+".EMP_ID =HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_EMP_ID " 
				+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_MONTH = HRMS_ARREARS_"+esi.getYear()+".ARREARS_MONTH "
				+ " AND HRMS_ARREARS_DEBIT_"+esi.getYear()+".ARREARS_YEAR = HRMS_ARREARS_"+esi.getYear()+".ARREARS_YEAR) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_EMP_ID) "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)"
				+ " INNER JOIN HRMS_EMP_CREDIT ON(HRMS_ARREARS_DEBIT_"
				+ esi.getYear()
				+ ".ARREARS_EMP_ID = HRMS_EMP_CREDIT.EMP_ID)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)   "
				+ " INNER JOIN HRMS_SALARY_"
				+ esi.getYear()
				+ " ON(HRMS_SALARY_"
				+ esi.getYear()
				+ ".EMP_ID = HRMS_ARREARS_"
				+ esi.getYear()
				+ ".EMP_ID AND SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
				+ esi.getMonth()
				+ " AND LEDGER_YEAR="
				+ esi.getYear()
				+ " AND LEDGER_DIVISION="
				+ esi.getDivCode()
				+ " ))"
				+ " LEFT JOIN HRMS_CENTER  ON (HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+ esi.getYear()+".SAL_EMP_CENTER) "
				+ " WHERE ARREARS_PAID_MONTH = "
				+ esi.getMonth()
				+ " AND ARREARS_PAID_YEAR="
				+ esi.getYear()
				+ "	AND CREDIT_PERIODICITY = 'M' AND "
				+ " HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND ARREARS_AMT>0 AND ARREARS_DEBIT_CODE= "
				+ esi_code[0][0];
		
				if(!esi.getOnHold().equals("A")){
					arrearsQry +=" AND ARREARS_ONHOLD='"+esi.getOnHold()+"'";
				}
			    arrearsQry=setQueryFilters(esi,arrearsQry);
		        arrearsQry+= " GROUP BY NVL(SAL_ESCINUMBER,'NA'),EMP_TOKEN,EMP_FNAME||' ' || EMP_MNAME || ' '|| EMP_LNAME,ARREARS_MONTH,ARREARS_YEAR ,ARREARS_DAYS,ARREARS_TYPE,ARREARS_AMT,ARREARS_CREDITS_AMT,NVL(CENTER_NAME,' '),SAL_DEPT "
				+ " ,HRMS_EMP_OFFC.EMP_ID ORDER BY NVL(CENTER_NAME,' '),SAL_DEPT,UPPER(NAME),HRMS_EMP_OFFC.EMP_ID";

		Object[][] arrsAmtObj = getSqlModel().getSingleResult(arrearsQry);
	
		if (arrsAmtObj == null || arrsAmtObj.length < 1){
			return null;
		}
		return arrsAmtObj;
	}
	
	/**
	 * Method is used to print the consolidated arrears report
	 * @param rgs
	 * @param arrAmtObj
	 * @param arrTotObj
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator setArrReportForConsolidated(org.paradyne.lib.ireportV2.ReportGenerator rgs, Object[][] arrAmtObj,
			Object[][] arrTotObj,  Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 11 ###############");
		try {
			int[] cellwidth = { 20, 30, 20, 15, 15, 15, 15, 15 };
			int[] alignment = { 0, 0, 1, 1, 1, 1, 1, 1 };
			String colNames[] = {
					"Emp Id",
					"Employee Name",
					"Insurance No",
					"Salary Days",
					"Monthly Gross",
					"Amount on which ESI is calculated ",
					"ESI Subscription @" + esiEmpPercentage + "%(Rs)",
					"Employer's Contribution @" + esiCompanyPercentage
							+ "%(Rs)" };
			//rg.addTableHeader("Arrears Details :", 6, 0, 0, true);
			arrAmtObj = Utility.consolidateArrearsObject(arrAmtObj, null, 0,
					new int[] { 3, 5, 6, 7 }, 8);
			Object totalEsi[][] = new Object[1][8];
			totalEsi = totalSum(arrAmtObj, null, new int[] { 3, 4, 5, 6, 7 });
			Object othrTotalData[][] = new Object[4][2];
			if (arrTotObj != null && arrTotObj.length > 0) {
				totalEsi[0][0] = "Total";
				totalEsi[0][1] = "";
				totalEsi[0][2] = "";
				

				othrTotalData[0][0] = "Employee's Contribution";
				othrTotalData[0][1] = Utility.twoDecimals(Double.parseDouble(String
						.valueOf(totalEsi[0][6])));
				othrTotalData[1][0] = "Employer's Contribution";
				othrTotalData[1][1] = Utility.twoDecimals(Double.parseDouble(String
						.valueOf(totalEsi[0][7])));
				othrTotalData[2][0] = "Total ESIC";
				othrTotalData[2][1] = Utility.twoDecimals(Double.parseDouble(String
						.valueOf(totalEsi[0][6]))
						+ Double.parseDouble(String.valueOf(totalEsi[0][7])));
				othrTotalData[3][0] = "Total No. of Employees";
				othrTotalData[3][1] = arrAmtObj.length;
			}
			TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(arrAmtObj);
			reportData.setBorder(true);
			reportData.setColumnSum(new int[]{3,4, 5, 6, 7});
			//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
			reportData.setHeaderTable(true);
			reportData.setHeaderBorderDetail(3);
			reportData.setBorderDetail(3);
			reportData.setBlankRowsBelow(1);
			rgs.addTableToDoc(reportData);
			
			TableDataSet totalData = new TableDataSet();
			totalData.setCellAlignment(new int[]{0,0});
			totalData.setCellWidth(new int[]{20, 30});
			totalData.setData(othrTotalData);
			totalData.setHeaderTable(false);
			totalData.setBorderDetail(3);
			totalData.setBlankRowsBelow(1);
			rgs.addTableToDoc(totalData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	/**
	 * following function is called when Esi report button is clicked and report
	 * option is All or Settlement
	 * 
	 * @param rg
	 * @param esi
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getSettlementEsi(org.paradyne.lib.ireportV2.ReportGenerator rgs, ESICReport esi, Object[][] esi_code, Double esiEmpPercentage, Double esiCompanyPercentage) {
		System.out.println("################# 12 ###############");
		String branchName = "";
		
		try {
			String monYear = esi.getMonth() + "-" + esi.getYear();
			
			String settleQuery="SELECT TOKEN, ENAME ,INSURANCENO,SUM(MONTHSAL), SUM(COMPESI), SUM(ESI),SUM(COMPESI)+SUM(ESI) "
				+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_EMP_OFFC.EMP_ID EMPCOUNT, EMP_TOKEN TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ENAME,NVL(SAL_ESCINUMBER,'NA') AS INSURANCENO," 
				+ " SUM(HRMS_SETTL_CREDITS .SETTL_AMT) MONTHSAL, NVL(HRMS_SETTL_DEBITS.SETTL_AMT,0) ESI, "
				+ " ROUND(SUM(HRMS_SETTL_CREDITS .SETTL_AMT)*"+esiCompanyPercentage+"/100) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME "  
				+ " FROM HRMS_SETTL_DEBITS "
				+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE AND SETTL_DEBIT_CODE=7 AND " 
				+ " SETTL_MTH_TYPE='CO')"  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) " 
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)" 
				+ " INNER JOIN HRMS_SETTL_CREDITS  ON (HRMS_SETTL_HDR.SETTL_CODE= HRMS_SETTL_CREDITS.SETTL_CODE)" 
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE  AND " 
				+ " CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER) WHERE EMP_DIV IN ("+esi.getDivCode()+") AND  HRMS_SETTL_DEBITS .SETTL_AMT >0 "
				+ " AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY')=TO_DATE('"+monYear+"','MM-YYYY') AND CREDIT_PERIODICITY='M'"
				+ " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_SETTL_DEBITS.SETTL_AMT,"
				+ " NVL(CENTER_NAME,' '), CENTER_ESI_CODE,HRMS_SETTL_HDR.SETTL_CODE, SAL_ESCINUMBER ORDER BY NVL(CENTER_NAME,' '))"
				+ " GROUP BY TOKEN, ENAME, INSURANCENO ";
			
			Object settData[][] = getSqlModel().getSingleResult(settleQuery);
			
			String settleTotalQuery="SELECT SUM(MONTHSAL), SUM(COMPESI), SUM(ESI),SUM(COMPESI)+SUM(ESI) "
				+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_EMP_OFFC.EMP_ID EMPCOUNT, EMP_TOKEN TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ENAME,NVL(SAL_ESCINUMBER,'NA') AS INSURANCENO," 
				+ " SUM(HRMS_SETTL_CREDITS .SETTL_AMT) MONTHSAL, NVL(HRMS_SETTL_DEBITS.SETTL_AMT,0) ESI, "
				+ " ROUND(SUM(HRMS_SETTL_CREDITS .SETTL_AMT)*"+esiCompanyPercentage+"/100) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME "  
				+ " FROM HRMS_SETTL_DEBITS "
				+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE AND SETTL_DEBIT_CODE=7 AND " 
				+ " SETTL_MTH_TYPE='CO')"  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) " 
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)" 
				+ " INNER JOIN HRMS_SETTL_CREDITS  ON (HRMS_SETTL_HDR.SETTL_CODE= HRMS_SETTL_CREDITS.SETTL_CODE)" 
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE  AND " 
				+ " CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER) WHERE EMP_DIV IN ("+esi.getDivCode()+") AND  HRMS_SETTL_DEBITS .SETTL_AMT >0 "
				+ " AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY')=TO_DATE('"+monYear+"','MM-YYYY') AND CREDIT_PERIODICITY='M'"
				+ " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_SETTL_DEBITS.SETTL_AMT,"
				+ " NVL(CENTER_NAME,' '), CENTER_ESI_CODE,HRMS_SETTL_HDR.SETTL_CODE, SAL_ESCINUMBER ORDER BY NVL(CENTER_NAME,' '))";
				
			
			Object settTotalData[][] = getSqlModel().getSingleResult(settleTotalQuery);
			
			if (settData != null && settData.length > 0) {
					String settleCol[] = {
						"Emp Id",
						"Employee Name",
						"Insurance No",
						"Monthly Gross",
						"Amount on which ESI is calculated ",
						"ESI Subscription @" + esiEmpPercentage + "%(Rs)",
						"Employer's Contribution @" + esiCompanyPercentage
								+ "%(Rs)" };
				
					rgs = tableHeading(rgs, "Settlement Details", branchName, "");
					
					
					int[] settleAlign = new int[settleCol.length];
					int[] settleWidth = new int[settleCol.length];
					boolean[] bcellwrap = new boolean[settleCol.length];
					for (int i = 0; i < settleCol.length; i++) {
						if (i == 0) {
							settleAlign[i] = 0;
							settleWidth[i] = 20;
							bcellwrap[i]=true;
						} else if (i == 1) {
							settleAlign[i] = 0;
							settleWidth[i] = 35;
							bcellwrap[i]=true;
						}  else if (i == 2) {
							settleAlign[i] = 2;
							settleWidth[i] = 35;
							bcellwrap[i]=true;
						}
						else {
							settleAlign[i] = 2;
							settleWidth[i] = 30;
							bcellwrap[i]=false;
						}
					}
					
					
					TableDataSet reportData = new TableDataSet();
					reportData.setHeader(settleCol);
					reportData.setCellAlignment(settleAlign);
					reportData.setCellWidth(settleWidth);
					reportData.setData(settData);
					reportData.setBorder(true);
					//reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportData.setHeaderTable(false);
					reportData.setHeaderBorderDetail(3);
					reportData.setCellNoWrap(bcellwrap);
					reportData.setColumnSum(new int[]{3, 4, 5, 6});
					//reportData.setHeaderLines(true);
					//reportData.setCellColSpan(new int[]{8});
					reportData.setBorderDetail(3);
					reportData.setBlankRowsBelow(1);
					rgs.addTableToDoc(reportData);
					
					Object othrTotalSettle[][] = new Object[4][2];
					othrTotalSettle[0][0] = "Employee's Contribution";
					othrTotalSettle[0][1] = String.valueOf(settTotalData[0][2]);
					othrTotalSettle[1][0] = "Employer's Contribution";
					othrTotalSettle[1][1] = String.valueOf(settTotalData[0][1]);
					othrTotalSettle[2][0] = "Total ESIC";
					othrTotalSettle[2][1] = String.valueOf(settTotalData[0][3]);					
					othrTotalSettle[3][0] = "Total No. of Employees";
					othrTotalSettle[3][1] = settData.length;
					int[] colWidth = { 20, 30 };
					int[] colAlign = { 0, 0 };
					
					
					TableDataSet totalData = new TableDataSet();
					totalData.setCellAlignment(colAlign);
					totalData.setCellWidth(colWidth);
					totalData.setData(othrTotalSettle);
					totalData.setBorder(true);
					//totalData.setHeaderTable(false);
					totalData.setBlankRowsBelow(1);
					totalData.setBorderDetail(3);
					rgs.addTableToDoc(totalData);

			}else {
				esi.setEsiSetFlag("true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	/**
	 * This method is used to print the heading for the settlement report
	 * @param rgs
	 * @param tableHeading
	 * @param branchName
	 * @param deptName
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator tableHeading(org.paradyne.lib.ireportV2.ReportGenerator rgs, String tableHeading, String branchName, String deptName){
		System.out.println("#################################"+tableHeading);
		try {
			Object dataObject[][] = new Object[3][2];
			 
			dataObject[0][0] = tableHeading;
			dataObject[0][1] = "";
			if(!branchName.equals("")){
				dataObject[1][0] = "Branch : "+branchName;
				dataObject[1][1] = "";
			}else if(!deptName.equals("")){
				dataObject[2][0] = "Department : "+deptName;
				dataObject[2][1] = "";
			}else{
				dataObject[2][0] = "";
				dataObject[2][1] = "";
			}
			
			
			TableDataSet descriptionObj = new TableDataSet();
			descriptionObj.setCellAlignment(new int[]{0,0});
			descriptionObj.setCellWidth(new int[]{60, 40});
			descriptionObj.setData(dataObject);
			descriptionObj.setBorder(false);
			descriptionObj.setHeaderTable(false);
			rgs.addTableToDoc(descriptionObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
		
	}
	/**
	 * 
	 * @param tempObj
	 * @return
	 */
	public Object[][] intZero(Object[][] tempObj) {
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

}
