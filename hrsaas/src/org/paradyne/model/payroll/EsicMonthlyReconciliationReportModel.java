package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.EsicMonthlyReconciliationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.incometax.Form12Model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class EsicMonthlyReconciliationReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(Form12Model.class);
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public void getReport(EsicMonthlyReconciliationReport bean, HttpServletRequest request, HttpServletResponse response, String reportPath ) {
		try {
			int year = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
			String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '), NVL(DIV_ADDRESS2,' '), NVL(DIV_ADDRESS3,' '), NVL(DIV_PFACCOUNTNO,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE DIV_ID IN("
					+ bean.getDivId() + ") ";
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = bean.getReportType();
			String fileName = "ESIC_Reconc_" + Utility.month(Integer.parseInt(bean.getMonth())).substring(0, 3)
				+ bean.getYear().substring(bean.getYear().length() - 2,bean.getYear().length()) + "_"
				+ Utility.getRandomNumber(1000); 
			
			rds.setReportType(reportType);
			rds.setFileName(fileName);
			rds.setReportName("ESIC Monthly Reconciliation Report");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setTotalColumns(9);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if(reportPath.equals("")){
				rg = new ReportGenerator(rds,session,context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
				rg = new ReportGenerator(rds,reportPath,session,context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "EsicMonthlyReconciliation_input.action");
			}
			
			
			/*
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[4][1];
			if (divisionObj != null && divisionObj.length > 0) {
				obj[0][0] = String.valueOf(divisionObj[0][0]);
				obj[1][0] = Utility.checkNull(String.valueOf(divisionObj[0][1])) + "\n"
						+ Utility.checkNull(String.valueOf(divisionObj[0][2])) + "\n"
						+ Utility.checkNull(String.valueOf(divisionObj[0][3]));
			} else {
				obj[0][0] = "";
				obj[1][0] = "";
			}
			obj[2][0] = "ESIC Contribution Reconciliation";
			obj[3][0] = "For the month " + Utility.month(month) + " - " + year;
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,	new BaseColor(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			*/
			
			TableDataSet subtitleName = new TableDataSet();
			String filterObj = "";
			filterObj = "Period  : " + Utility.month(month) + " - " + year;
			filterObj += "\n\nDivision : " + bean.getDivName();
			int cnt=2;
			if(!bean.getBrnId().equals("")){
				filterObj += "\n\nBranch : " + bean.getBrnName();
			}
			if(!bean.getDeptId().equals("")){
				filterObj += "\n\nDepartment : " + bean.getDeptName();
			}
			if(!bean.getPayBillNo().equals("")){
				filterObj += "\n\nPay Bill : " + bean.getPayBillName();
			}
			if(!bean.getCadreCode().equals("")){
				filterObj += "\n\nGrade : " + bean.getCadreName();
			}
			
			subtitleName.setData(new Object[][] { { filterObj } });
			subtitleName.setCellAlignment(new int[] { 0 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			subtitleName.setCellNoWrap(new boolean[]{false});
			subtitleName.setCellColSpan(new int[]{ 9 });
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			
			String esiCodeQuery = "SELECT HRMS_ESI.ESI_DEBIT_CODE FROM HRMS_ESI "
				+ " WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'dd-MON-yyyy')  = (SELECT MAX(HRMS_ESI.ESI_DATE) FROM HRMS_ESI WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'yyyy-mm') <= '"
				+ bean.getYear() + "-" + bean.getMonth() + "')";
			Object[][] esicCode = getSqlModel().getSingleResult(esiCodeQuery);
			
			// Method calls to display records for additions & deletions table
			if(esicCode!=null && esicCode.length>0){
				rg = getAdditions(bean, rg, esicCode);
				rg = getDeletions(bean, rg, esicCode);
				rg = getRegular(bean, rg, esicCode);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },	{ "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
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
	
public ReportGenerator getAdditions(EsicMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]esicCode ){
		
		try {
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
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
			
			String header[] = { "Emp Id", "Employee Name",
					"Father Name","ESI No", "Birth Date", "Joining Date", "Resignation Date", "Location", "Wages" };
			int[] cellwidth = { 15, 45, 35, 20, 20, 20, 20, 30, 20 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 2 };
			
			String query="";
			if(!bean.getBrnId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CENTER IN("+bean.getBrnId()+")";
			}
			if(!bean.getDeptId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_DEPT IN("+bean.getDeptId()+")";
			}
			if(!bean.getPayBillNo().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL IN("+bean.getPayBillNo()+")";
			}
			if(!bean.getCadreCode().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CADRE IN("+bean.getCadreCode()+")";
			}
			
			
			String additionsQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '),NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'), TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
				+ " TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'),HRMS_CENTER.CENTER_NAME, 0,HRMS_EMP_OFFC.EMP_ID  FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+")"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) WHERE HRMS_EMP_OFFC.EMP_ID NOT IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+prevYear+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+") ) AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0 "+query;
			
			String wagesQuery = "SELECT  '', '', '','', '', '', '','', SUM(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT),HRMS_SAL_CREDITS_"+year+".EMP_ID  FROM HRMS_SAL_CREDITS_"+year
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") )"
				+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y') "
				+" WHERE HRMS_SAL_CREDITS_"+year+".EMP_ID NOT IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+prevYear+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+") )"
				+" AND HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT>0 "+query+" GROUP BY HRMS_SAL_CREDITS_"+year+".EMP_ID ";
			
			Object[][] additionsObj = getSqlModel().getSingleResult(
					additionsQuery);
			Object[][] wagesObj = getSqlModel().getSingleResult(
					wagesQuery);
			if (additionsObj != null && additionsObj.length > 0) {
				additionsObj = Utility.consolidateArrearsObject(additionsObj, wagesObj, 9, new int[]{8}, 10);
				additionsObj=Utility.removeNullRows(additionsObj, 1);
				additionsObj=Utility.removeColumns(additionsObj, new int[]{9});
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "ESIC Addition Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,false,true,true };
				TableDataSet additionsData = new TableDataSet();
				additionsData.setData(additionsObj);
				additionsData.setHeader(header);
				additionsData.setCellAlignment(alignment);
				additionsData.setCellWidth(cellwidth);
				additionsData.setBorder(true);
				additionsData.setBorderDetail(3);
				additionsData.setHeaderTable(true);
				//additionsData.setHeaderLines(true);
				additionsData.setHeaderBorderDetail(3);
				additionsData.setCellNoWrap(bcellwrap);
				additionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(additionsData);
				
				double totalWages = 0.0;
				for (int i = 0; i < additionsObj.length; i++) {
					additionsObj[i][8] = formatter.format(additionsObj[i][8]);
					totalWages = totalWages + Double.parseDouble(String.valueOf(additionsObj[i][8])); 
				}
				
				Object[][] paramTotal = new Object[1][9];
				paramTotal[0][0] = "";
				paramTotal[0][1] = "";
				paramTotal[0][2] = "";
				paramTotal[0][3] = "";
				paramTotal[0][4] = "";
				paramTotal[0][5] = "";
				paramTotal[0][6] = "";
				paramTotal[0][7] = "Total wages amount : ";
				paramTotal[0][8] = formatter.format(totalWages);

				TableDataSet reportObjTotalData = new TableDataSet();
				reportObjTotalData.setCellAlignment(alignment);
				reportObjTotalData.setCellWidth(cellwidth);
				reportObjTotalData.setData(paramTotal);
				reportObjTotalData.setBorderDetail(0);
				reportObjTotalData.setBodyFontStyle(1);
				reportObjTotalData.setBorderLines(true);
				reportObjTotalData.setBodyFontStyle(1);
				rg.addTableToDoc(reportObjTotalData);
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[2][2];
				paramTotalRec[0][0]="Total No of employee : " + additionsObj.length;
				//paramTotalRec[1][1]="Total wages amount : " + formatter.format(totalWages);
				reportObjData.setCellAlignment(new int[] {0,2});
				reportObjData.setCellWidth(new int[] {50,50});
				reportObjData.setCellColSpan(new int[] {4,5} );
				reportObjData.setData(paramTotalRec);
				//reportObjData.setBorder(true);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				reportObjData.setBorderDetail(0);
				reportObjData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjData);
				
				
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "ESIC Addition Details : No records avaliable for additions" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setCellColSpan(new int[] {4});
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	public ReportGenerator getDeletions(EsicMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]esicCode ){
		
		try {
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
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
			String header[] = { "Emp Id", "Employee Name",
					"Father Name", "ESI No", "Birth Date", "Joining Date", "Resignation Date", "Location", "Wages" };
			int[] cellwidth = { 20, 45, 35, 25, 20, 20, 20, 30, 20 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 2 };
			String query="";
			if(!bean.getBrnId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CENTER IN("+bean.getBrnId()+")";
			}
			if(!bean.getDeptId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_DEPT IN("+bean.getDeptId()+")";
			}
			if(!bean.getPayBillNo().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL IN("+bean.getPayBillNo()+")";
			}
			if(!bean.getCadreCode().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CADRE IN("+bean.getCadreCode()+")";
			}
			String deletionsQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '), NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' '),TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'), TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
				+ " TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'),HRMS_CENTER.CENTER_NAME, 0,HRMS_EMP_OFFC.EMP_ID  FROM HRMS_SAL_DEBITS_"+prevYear
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID)" 
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+prevYear+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+")"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) WHERE HRMS_SAL_DEBITS_"+prevYear+".EMP_ID NOT IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID   FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+") ) AND HRMS_SAL_DEBITS_"+prevYear+".SAL_AMOUNT>0 "+query;
			
			String wagesQuery = " SELECT  '', '', '', '','', '', '','', SUM(HRMS_SAL_CREDITS_"+prevYear+".SAL_AMOUNT),HRMS_SAL_CREDITS_"+prevYear+".EMP_ID  FROM HRMS_SAL_CREDITS_"+prevYear
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_CREDITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+"))"
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"+prevYear+".SAL_CREDIT_CODE AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y') WHERE HRMS_SAL_CREDITS_"+prevYear+".EMP_ID NOT IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID   FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+")"
				+" ) AND HRMS_SAL_CREDITS_"+prevYear+".SAL_AMOUNT>0 "+query+" GROUP BY HRMS_SAL_CREDITS_"+prevYear+".EMP_ID";

			Object[][] deletionObj = getSqlModel().getSingleResult(
					deletionsQuery);
			Object[][] wagesObj = getSqlModel().getSingleResult(
					wagesQuery);
			if (deletionObj != null && deletionObj.length > 0) {
				deletionObj = Utility.consolidateArrearsObject(deletionObj, wagesObj, 9, new int[]{8}, 10);
				deletionObj=Utility.removeNullRows(deletionObj, 1);
				deletionObj=Utility.removeColumns(deletionObj, new int[]{9});
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "ESIC Deletions Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,true,true,true };
				TableDataSet deletionsData = new TableDataSet();
				deletionsData.setData(deletionObj);
				deletionsData.setHeader(header);
				deletionsData.setCellAlignment(alignment);
				deletionsData.setCellWidth(cellwidth);
				deletionsData.setBorder(true);
				deletionsData.setBorderDetail(3);
				deletionsData.setHeaderTable(true);
				//deletionsData.setHeaderLines(true);
				deletionsData.setHeaderBorderDetail(3);
				deletionsData.setCellNoWrap(bcellwrap);
				deletionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(deletionsData);
				
				double totalWages = 0.0;
				for (int i = 0; i < deletionObj.length; i++) {
					deletionObj[i][8] = formatter.format(deletionObj[i][8]);
					totalWages = totalWages + Double.parseDouble(String.valueOf(deletionObj[i][8])); 
				}
				
				Object[][] paramTotal = new Object[1][9];
				paramTotal[0][0] = "";
				paramTotal[0][1] = "";
				paramTotal[0][2] = "";
				paramTotal[0][3] = "";
				paramTotal[0][4] = "";
				paramTotal[0][5] = "";
				paramTotal[0][6] = "";
				paramTotal[0][7] = "Total wages amount : ";
				paramTotal[0][8] = formatter.format(totalWages);

				TableDataSet reportObjTotalData = new TableDataSet();
				reportObjTotalData.setCellAlignment(alignment);
				reportObjTotalData.setCellWidth(cellwidth);
				reportObjTotalData.setData(paramTotal);
				reportObjTotalData.setBorderDetail(0);
				reportObjTotalData.setBodyFontStyle(1);
				reportObjTotalData.setBorderLines(true);
				reportObjTotalData.setBodyFontStyle(1);
				rg.addTableToDoc(reportObjTotalData);
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[2][2];
				paramTotalRec[0][0]="Total No of employee : " + deletionObj.length;
				//paramTotalRec[1][1]="Total wages amount : " + formatter.format(totalWages);
				reportObjData.setCellAlignment(new int[] {0,2});
				reportObjData.setCellWidth(new int[] {50,50});
				reportObjData.setCellColSpan(new int[] {4,5} );
				reportObjData.setData(paramTotalRec);
				//reportObjData.setBorder(true);
				reportObjData.setBorderDetail(0);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				rg.addTableToDoc(reportObjData);
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "ESIC Deletions Details : No records avaliable for deletions" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setCellColSpan(new int[] {4});
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

public ReportGenerator getRegular(EsicMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]esicCode ){
		
		try {
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
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
			
			String header[] = { "Emp Id", "Employee Name",
					"Father Name","ESI No", "Birth Date", "Joining Date", "Resignation Date", "Location", "Wages" };
			int[] cellwidth = { 15, 45, 35, 20, 20, 20, 20, 30, 20 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 2 };
			
			String query="";
			if(!bean.getBrnId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CENTER IN("+bean.getBrnId()+")";
			}
			if(!bean.getDeptId().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_DEPT IN("+bean.getDeptId()+")";
			}
			if(!bean.getPayBillNo().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL IN("+bean.getPayBillNo()+")";
			}
			if(!bean.getCadreCode().equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CADRE IN("+bean.getCadreCode()+")";
			}
			
			
			String additionsQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '),NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'), TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
				+ " TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'),HRMS_CENTER.CENTER_NAME, 0,HRMS_EMP_OFFC.EMP_ID  FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+")"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) WHERE HRMS_SAL_DEBITS_"+year+".EMP_ID IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+prevYear+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+")) AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0 "+query;
			
			String wagesQuery = "SELECT  '', '', '','', '', '', '','', SUM(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT),HRMS_SAL_CREDITS_"+year+".EMP_ID  FROM HRMS_SAL_CREDITS_"+year
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") )"
				+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y') "
				+" WHERE HRMS_SAL_CREDITS_"+year+".EMP_ID IN ("
				+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+previousMonth+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+prevYear+" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN("+bean.getDivId()+") AND HRMS_SAL_DEBITS_"+prevYear+".SAL_DEBIT_CODE="+String.valueOf(esicCode[0][0])+") )"
				+" AND HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT>0 "+query+" GROUP BY HRMS_SAL_CREDITS_"+year+".EMP_ID ";
			
			Object[][] additionsObj = getSqlModel().getSingleResult(
					additionsQuery);
			Object[][] wagesObj = getSqlModel().getSingleResult(
					wagesQuery);
			if (additionsObj != null && additionsObj.length > 0) {
				additionsObj = Utility.consolidateArrearsObject(additionsObj, wagesObj, 9, new int[]{8}, 10);
				additionsObj=Utility.removeNullRows(additionsObj, 1);
				additionsObj=Utility.removeColumns(additionsObj, new int[]{9});
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "ESIC Regular Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,false,true,true };
				TableDataSet additionsData = new TableDataSet();
				additionsData.setData(additionsObj);
				additionsData.setHeader(header);
				additionsData.setCellAlignment(alignment);
				additionsData.setCellWidth(cellwidth);
				additionsData.setBorder(true);
				additionsData.setBorderDetail(3);
				additionsData.setHeaderTable(true);
				//additionsData.setHeaderLines(true);
				additionsData.setHeaderBorderDetail(3);
				additionsData.setCellNoWrap(bcellwrap);
				additionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(additionsData);
				
				double totalWages = 0.0;
				for (int i = 0; i < additionsObj.length; i++) {
					additionsObj[i][8] = formatter.format(additionsObj[i][8]);
					totalWages = totalWages + Double.parseDouble(String.valueOf(additionsObj[i][8])); 
				}
				
				Object[][] paramTotal = new Object[1][9];
				paramTotal[0][0] = "";
				paramTotal[0][1] = "";
				paramTotal[0][2] = "";
				paramTotal[0][3] = "";
				paramTotal[0][4] = "";
				paramTotal[0][5] = "";
				paramTotal[0][6] = "";
				paramTotal[0][7] = "Total wages amount : ";
				paramTotal[0][8] = formatter.format(totalWages);

				TableDataSet reportObjTotalData = new TableDataSet();
				reportObjTotalData.setCellAlignment(alignment);
				reportObjTotalData.setCellWidth(cellwidth);
				reportObjTotalData.setData(paramTotal);
				reportObjTotalData.setBorderDetail(0);
				reportObjTotalData.setBodyFontStyle(1);
				reportObjTotalData.setBorderLines(true);
				reportObjTotalData.setBodyFontStyle(1);
				rg.addTableToDoc(reportObjTotalData);
				
				
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[2][2];
				paramTotalRec[0][0]="Total No of employee : " + additionsObj.length;
				//paramTotalRec[1][1]="Total wages amount : " + formatter.format(totalWages);
				reportObjData.setCellAlignment(new int[] {0,2});
				reportObjData.setCellWidth(new int[] {50,50});
				reportObjData.setCellColSpan(new int[] {4,5} );
				reportObjData.setData(paramTotalRec);
				//reportObjData.setBorder(true);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				reportObjData.setBorderDetail(0);
				reportObjData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjData);
				
				
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "ESIC Regular Details : No records avaliable for regular" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setCellColSpan(new int[] {4});
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	
}
