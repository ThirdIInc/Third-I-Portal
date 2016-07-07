package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.EpfMonthlyReconciliationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.incometax.Form12Model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class EpfMonthlyReconciliationReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(Form12Model.class);

	public void getReport(EpfMonthlyReconciliationReport bean,  HttpServletRequest request, HttpServletResponse response, String reportPath ) {
		
		try {
			int year = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
			String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '), NVL(DIV_ADDRESS2,' '), NVL(DIV_ADDRESS3,' '), NVL(DIV_PFACCOUNTNO,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE DIV_ID IN("
					+ bean.getDivId() + ") ";
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = bean.getReportType();
			String fileName= "EPF_Reconc_" + Utility.month(Integer.parseInt(bean.getMonth())).substring(0, 3)
				+ bean.getYear().substring(bean.getYear().length() - 2,bean.getYear().length()) + "_"
				+ Utility.getRandomNumber(1000); 

			
			rds.setReportType(reportType);
			rds.setFileName(fileName);
			rds.setReportName("EPF Monthly Reconciliation Report");
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
				request.setAttribute("fileName", fileName+"."+reportType);
				request.setAttribute("action", "EpfMonthlyReconciliation_input.action");
			}
			/*
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[4][1];
			if (divisionObj != null && divisionObj.length > 0) {
				obj[0][0] = String.valueOf(divisionObj[0][0]);
				obj[1][0] = String.valueOf(divisionObj[0][1]) + "\n"
						+ String.valueOf(divisionObj[0][2]) + "\n"
						+ String.valueOf(divisionObj[0][3]);
			} else {
				obj[0][0] = "";
				obj[1][0] = "";
			}
			obj[2][0] = "EPF Monthly Reconciliation Report";
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
				cnt++;
			}	
			
			subtitleName.setData(new Object[][] { { filterObj } });
			subtitleName.setCellAlignment(new int[] { 0 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setCellNoWrap(new boolean[]{false});
			subtitleName.setCellColSpan(new int[]{ 8 });
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			
			String pfDebitCodeQuery = "SELECT PF_DEBIT_CODE FROM HRMS_PF_CONF"
				+ " WHERE TO_CHAR(PF_DATE,'dd-MON-yyyy')  = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF WHERE TO_CHAR(PF_DATE,'yyyy-mm') <= '"
				+ bean.getYear() + "-" + bean.getMonth() + "')";
			Object[][] debitCode = getSqlModel().getSingleResult(pfDebitCodeQuery);
			
			// Method calls to display records for additions & deletions table
			if(debitCode!=null && debitCode.length>0){
				rg = getAdditions(bean, rg, debitCode);
				rg = getDeletions(bean, rg, debitCode);
				rg = getRegular(bean, rg, debitCode);
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
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ReportGenerator getAdditions(EpfMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]debitCode){
		
		try {
			
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
			int previousMonth=12;
			if(month>1){
				previousMonth = month-1;
			}else{
				prevYear-=prevYear;
			}
			
			int fromYear = 0001; 
			int toYear = 0001; 
			
			if(month<4){
				fromYear = year-1;
			}else{
				fromYear = year;
			}
			toYear = fromYear+1;
			
			String header[] = { "Sn", "Employee Id","EPF No", "Name Of the Employee",
					"Father Name", "DOB", "DOJ", "DOR", "Location" };
			int[] cellwidth = { 15, 15, 25, 35, 35, 25, 25, 25, 45 };
			int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0, 0 };
			
			
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
			String additionsQuery = "SELECT  ROWNUM, HRMS_EMP_OFFC.EMP_TOKEN, NVL(HRMS_SALARY_MISC.SAL_GPFNO,' '), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '), TO_CHAR(EMP_DOB,'DD-MM-YYYY'), TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
					+ " TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),CENTER_NAME  FROM HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) WHERE EMP_ID NOT IN ("
					+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+previousMonth+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+") ) AND SAL_AMOUNT>0" +query;

			Object[][] additionsObj = getSqlModel().getSingleResult(
					additionsQuery);
			if (additionsObj != null && additionsObj.length > 0) {
				
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "EPF Addition Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setCellColSpan(new int[] {3});
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,true,true,true };
				TableDataSet additionsData = new TableDataSet();
				additionsData.setData(additionsObj);
				additionsData.setHeader(header);
				additionsData.setHeaderBorderDetail(3);
				additionsData.setCellAlignment(alignment);
				additionsData.setCellWidth(cellwidth);
				additionsData.setBorder(false);
				additionsData.setBorderDetail(3);
				additionsData.setHeaderTable(true);
				additionsData.setCellNoWrap(bcellwrap);
				//additionsData.setHeaderLines(true);
				additionsData.setHeaderBGColor(new BaseColor(225, 225, 225));
				additionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(additionsData);
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[1][1];
				paramTotalRec[0][0]="Total Records: " + additionsObj.length;
				reportObjData.setCellAlignment(new int[] {0});
				reportObjData.setCellWidth(new int[] {100});
				reportObjData.setCellColSpan(new int[] {9});
				reportObjData.setData(paramTotalRec);
				//reportObjData.setBorder(true);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				reportObjData.setBorderDetail(0);
				reportObjData.setBodyFontStyle(1);
				reportObjData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjData);
				
				
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "EPF Addition Details : No records avaliable for addition" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setCellColSpan(new int[] {5});
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			TableDataSet noData = new TableDataSet();
			noData.setData(new Object[][] { { "" },
					{ "EPF Addition Details : No records avaliable for addition" } });
			noData.setCellAlignment(new int[] { 0 });
			noData.setCellWidth(new int[] { 100 });
			noData.setCellColSpan(new int[] {5});
			noData.setBodyFontStyle(1);
			noData.setBorder(false);
			noData.setHeaderTable(false);
			noData.setBlankRowsBelow(1);
			rg.addTableToDoc(noData);
		}
		return rg;
	}
	
	

	public ReportGenerator getRegular(EpfMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]debitCode){
		
		try {
			
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
			int previousMonth=12;
			if(month>1){
				previousMonth = month-1;
			}else{
				prevYear-=prevYear;
			}
			
			int fromYear = 0001; 
			int toYear = 0001; 
			
			if(month<4){
				fromYear = year-1;
			}else{
				fromYear = year;
			}
			toYear = fromYear+1;
			
			String header[] = { "Sn", "Employee Id","EPF No", "Name Of the Employee",
					"Father Name", "DOB", "DOJ", "DOR", "Location" };
			int[] cellwidth = { 15, 15, 25, 35, 35, 25, 25, 25, 45 };
			int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0, 0 };
			
			
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
			String additionsQuery = "SELECT  ROWNUM, HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_SALARY_MISC.SAL_GPFNO,' '), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '), TO_CHAR(EMP_DOB,'DD-MM-YYYY'), TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
					+ " TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),CENTER_NAME  FROM HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) WHERE EMP_ID IN ("
					+ " SELECT HRMS_SAL_DEBITS_"+prevYear+".EMP_ID   FROM HRMS_SAL_DEBITS_"+prevYear
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+previousMonth+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+") ) AND SAL_AMOUNT>0" +query;

			Object[][] additionsObj = getSqlModel().getSingleResult(
					additionsQuery);
			if (additionsObj != null && additionsObj.length > 0) {
				
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "EPF Regular Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setCellColSpan(new int[] {3});
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,true,true,true };
				TableDataSet additionsData = new TableDataSet();
				additionsData.setData(additionsObj);
				additionsData.setHeader(header);
				additionsData.setHeaderBorderDetail(3);
				additionsData.setCellAlignment(alignment);
				additionsData.setCellWidth(cellwidth);
				additionsData.setBorder(false);
				additionsData.setBorderDetail(3);
				additionsData.setHeaderTable(true);
				additionsData.setCellNoWrap(bcellwrap);
				//additionsData.setHeaderLines(true);
				additionsData.setHeaderBGColor(new BaseColor(225, 225, 225));
				additionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(additionsData);
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[1][1];
				paramTotalRec[0][0]="Total Records: " + additionsObj.length;
				reportObjData.setCellAlignment(new int[] {0});
				reportObjData.setCellWidth(new int[] {100});
				reportObjData.setCellColSpan(new int[] {9});
				reportObjData.setData(paramTotalRec);
				//reportObjData.setBorder(true);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				reportObjData.setBorderDetail(0);
				reportObjData.setBodyFontStyle(1);
				reportObjData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjData);
				
				
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "EPF Regular Details : No records avaliable for regular" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setCellColSpan(new int[] {5});
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			TableDataSet noData = new TableDataSet();
			noData.setData(new Object[][] { { "" },
					{ "EPF Regular Details : No records avaliable for regular" } });
			noData.setCellAlignment(new int[] { 0 });
			noData.setCellWidth(new int[] { 100 });
			noData.setCellColSpan(new int[] {5});
			noData.setBodyFontStyle(1);
			noData.setBorder(false);
			noData.setHeaderTable(false);
			noData.setBlankRowsBelow(1);
			rg.addTableToDoc(noData);
		}
		return rg;
	}
	
	
	public ReportGenerator getDeletions(EpfMonthlyReconciliationReport bean, ReportGenerator rg, Object[][]debitCode){
		
		try {
			
			int year = Integer.parseInt(bean.getYear());
			int prevYear = Integer.parseInt(bean.getYear());
			int month = Integer.parseInt(bean.getMonth());
			int previousMonth=12;
			if(month>1){
				previousMonth = month-1;
			}else{
				prevYear-=prevYear;
			}
			
			int fromYear = 0001; 
			int toYear = 0001; 
			
			if(month<4){
				fromYear = year-1;
			}else{
				fromYear = year;
			}
			toYear = fromYear+1;
			
			String header[] = { "Sn", "Employee Id", "EPF No", "Name Of the Employee",
					"Father Name", "DOB", "DOJ", "DOR", "Location" };
			int[] cellwidth = { 15, 15, 25, 35, 35, 25, 25, 25, 45 };
			int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0, 0 };
			
			
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
			
			String deletionsQuery = " SELECT  ROWNUM, HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_SALARY_MISC.SAL_GPFNO,' '), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+ " NVL(HRMS_EMP_OFFC.EMP_MNAME,' '), TO_CHAR(EMP_DOB,'DD-MM-YYYY'), TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY'), "
					+ " TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),CENTER_NAME  FROM HRMS_SAL_DEBITS_"+prevYear
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+prevYear+".EMP_ID)" 
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+previousMonth+" AND LEDGER_YEAR="+prevYear+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) WHERE EMP_ID NOT IN ("
					+ " SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID   FROM HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION IN("+bean.getDivId()+") AND SAL_DEBIT_CODE="+String.valueOf(debitCode[0][0])+") )AND SAL_AMOUNT>0 "+query;

			Object[][] deletionObj = getSqlModel().getSingleResult(
					deletionsQuery);
			if (deletionObj != null && deletionObj.length > 0) {
				
				TableDataSet addData = new TableDataSet();
				addData.setData(new Object[][] { { "EPF Deletions Details" }, { "" } });
				addData.setCellAlignment(new int[] { 0 });
				addData.setCellWidth(new int[] { 100 });
				addData.setBodyFontStyle(1);
				addData.setCellColSpan(new int[] {3});
				addData.setBorder(false);
				addData.setHeaderTable(false);
				rg.addTableToDoc(addData);
				
				
				boolean[] bcellwrap = new boolean[] {true,true,true,true,true,true,true,true,true };
				TableDataSet deletionsData = new TableDataSet();
				deletionsData.setData(deletionObj);
				deletionsData.setHeader(header);
				deletionsData.setHeaderBorderDetail(3);
				deletionsData.setCellAlignment(alignment);
				deletionsData.setCellWidth(cellwidth);
				deletionsData.setBorder(false);
				deletionsData.setBorderDetail(3);
				deletionsData.setHeaderTable(true);
				//deletionsData.setHeaderLines(true);
				deletionsData.setHeaderBGColor(new BaseColor(225, 225, 225));
				deletionsData.setCellNoWrap(bcellwrap);
				deletionsData.setBlankRowsBelow(1);
				rg.addTableToDoc(deletionsData);
				
				
				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[1][1];
				paramTotalRec[0][0]="Total Records: " + deletionObj.length;
				reportObjData.setCellAlignment(new int[] {0});
				reportObjData.setCellWidth(new int[] {100});
				reportObjData.setCellColSpan(new int[] {9});
				reportObjData.setData(paramTotalRec);
				reportObjData.setBodyBGColor(new BaseColor(200,200,200));
				//reportObjData.setBorder(true);
				reportObjData.setBodyFontStyle(1);
				reportObjData.setBorderDetail(0);
				reportObjData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjData);
				
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "EPF Deletions Details : No records avaliable for deletion" } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setCellColSpan(new int[] {5});
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			TableDataSet noData = new TableDataSet();
			noData.setData(new Object[][] { { "" },
					{ "EPF Deletions Details : No records avaliable for deletion" } });
			noData.setCellAlignment(new int[] { 0 });
			noData.setCellWidth(new int[] { 100 });
			noData.setCellColSpan(new int[] {5});
			noData.setBodyFontStyle(1);
			noData.setBorder(false);
			noData.setHeaderTable(false);
			noData.setBlankRowsBelow(1);
			rg.addTableToDoc(noData);
		}
		return rg;
	}

}
