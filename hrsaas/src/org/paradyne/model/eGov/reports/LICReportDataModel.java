package org.paradyne.model.eGov.reports;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.eGov.reports.LICReportDataBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;



public class LICReportDataModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);


	/**
	 * Generation of report
	 * 
	 * @param form5
	 * @param response
	 */
	public void genLICReport(LICReportDataBean licreportBean,HttpServletRequest request, HttpServletResponse response, String reportPath ) {
		// TODO Auto-generated method stub

		try {
			String fromMonth = licreportBean.getFromMonth();
			String fromYear = licreportBean.getToYear();
			String debitCode = licreportBean.getDebitId();
			String type = licreportBean.getRepType();
			String div = licreportBean.getDivId();

			//org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
			//	"Pdf", title, "A4");
			String title = "LIC Report";
			
			ReportDataSet rds = new ReportDataSet();
			String fileName ="LIC Report"; 
			rds.setReportType(type);
			rds.setFileName(fileName);
			rds.setReportName("LIC Report");
			rds.setPageOrientation("portrait");
			rds.setPageSize("A4");
			
			//ReportGenerator rg = new ReportGenerator(type, title, "A4");
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			
			if(reportPath.equals("")){
				rg = new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new ReportGenerator(rds,reportPath,session,context);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("action", "LICReport_input.action?path="+reportPath);
			}
			
			
			
//Added For Filtering Purpose
			
			Object tableDataNew [][] = new Object[1][4];

			tableDataNew[0][0] = Utility.month(Integer.parseInt(licreportBean.getFromMonth()));
			tableDataNew[0][1] = ""+ String.valueOf(licreportBean.getToYear()) + "";
					
			tableDataNew[0][2] =  ""+ String.valueOf(licreportBean.getDivName()) + "";
			tableDataNew[0][3] = ""+String.valueOf(licreportBean.getDebit())+"";
			
			String colnames1 [] ={"Month","Year","Division","Debit Name"};
			
			                 
			int[] cellwidth = { 15,10,30,30 };
			int[] alignment = { 0,0,0,0};
			
			
			
			
			/*
			if (type.equals("Pdf")) { // Report is opening in Pdf
				rg.addFormatedText(title, 6, 0, 1, 0);
				rg.tableBody(colnames1, tableDataNew,cellwidth, alignment);
			} else { // Report is opening in xls or txt
				Object[][] lictitle = new Object[1][3];
				lictitle[0][0] = "";
				lictitle[0][1] = "";
				lictitle[0][2] = title;

				int[] cols = { 20, 20, 30 };
				int[] align = { 0, 0, 1 };
				rg.tableBodyNoCellBorder(lictitle, cols, align, 1);
				rg.tableBody(colnames1, tableDataNew,cellwidth, alignment);
			} // end of else statement
			*/

			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][1];
			obj[0][0] = title;
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setCellColSpan(new int[]{6});
			subtitleName.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName);
			
			
			TableDataSet dataObj1 = new TableDataSet();
			dataObj1.setHeader(colnames1);
			dataObj1.setHeaderBorderDetail(3);
			dataObj1.setData(tableDataNew);
			dataObj1.setCellAlignment(alignment);
			dataObj1.setCellWidth(cellwidth);
			dataObj1.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
			dataObj1.setBorder(true);
			dataObj1.setBorderDetail(3);
			dataObj1.setBlankRowsBelow(2);
			dataObj1.setHeaderTable(true);
			dataObj1.setHeaderBGColor(new BaseColor(225, 225, 225));
			rg.addTableToDoc(dataObj1);
			
			
			
			/*rg.addFormatedText("" + title, 4, 0, 1, 0);*/
			//rg.addText("\n", 0, 0, 0);

			// Retrieving Month and year

			//rg.addText("\n", 0, 0, 0);

			String licQuery = "SELECT rownum,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,nvl(LIC_NAME,' '),nvl(LIC_POLICY_NUMBER,' '),"
					+ " HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_AMOUNT"
					+ " FROM HRMS_SALARY_LEDGER"
					+ " INNER JOIN HRMS_SALARY_"+ fromYear+ " ON(HRMS_SALARY_"+ fromYear+ ".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE) "
					+ " INNER JOIN HRMS_SAL_DEBITS_"+ fromYear+ " ON(HRMS_SAL_DEBITS_"+ fromYear+ ".EMP_ID=HRMS_SALARY_"+ fromYear+ ".EMP_ID AND HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_DEBIT_CODE="+ debitCode+ " AND HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+ fromYear+ ".EMP_ID) "
					+ " LEFT JOIN HRMS_LIC ON(HRMS_LIC.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND LIC_PAID_IN_DEBIT_CODE="+ debitCode+ ") "
					+ " WHERE LEDGER_DIVISION="+ div+" AND LEDGER_MONTH="+ fromMonth	+"  AND LEDGER_YEAR="+ fromYear+ "";

			String whereClause = "";
			if (!licreportBean.getBrnName().equals("")) {
				whereClause += " AND CENTER_ID=" + licreportBean.getBrnId();
			}

			/*if (!licreportBean.getDeptName().equals("")) {
				whereClause += " AND DEPT_ID=" + licreportBean.getDeptId();
			}*/

			if (!licreportBean.getTypeName().equals("")) {
				whereClause += " AND TYPE_ID=" + licreportBean.getTypeId();
			}
			
			 licQuery += whereClause;
			Object[][] tableData = getSqlModel().getSingleResult(licQuery);
			
			
				
				
				
			int[] cellwidth1 = { 10, 15, 25, 25, 15, 15 };
			int[] alignment1 = { 0, 0, 0, 0, 0, 0 };

			String colnames[] = { "SrNo", "Employee Id", "Employee Name",
					"LIC Name", "LIC Plolicy No", "Premium Ammount "};

			try {
				if (tableData.length != 0) {

					for (int i = 0; i < tableData.length; i++) {
						tableData[i][0] = String.valueOf(i + 1);
					}// end of for
					//rg.tableBody(colnames, tableData, cellwidth1, alignment1);
					TableDataSet dataObj2 = new TableDataSet();
					dataObj2.setHeader(colnames);
					dataObj2.setHeaderBorderDetail(3);
					dataObj2.setData(tableData);
					dataObj2.setCellAlignment(alignment1);
					dataObj2.setCellWidth(cellwidth1);
					dataObj2.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
					dataObj2.setBorder(true);
					dataObj2.setBorderDetail(3);
					dataObj2.setBlankRowsBelow(1);
					dataObj2.setHeaderTable(true);
					dataObj2.setHeaderBGColor(new BaseColor(225, 225, 225));
					rg.addTableToDoc(dataObj2);
					
					
				}// end of if
				else {
					//rg.addFormatedText("No records to display ", 0, 0, 1, 0);
					Object[][] obj1 = new Object[1][1];
					obj1[0][0] = "No records to display" ; 
					
					TableDataSet tds = new TableDataSet();
					tds.setData(obj1);
					tds.setCellAlignment(new int[] {1});
					tds.setCellWidth(new int[] {100});
					tds.setBorder(true);
					tds.setBlankRowsBelow(1);
					rg.addTableToDoc(tds);
				}// end of else
			}// end of try
			catch (Exception e) {
				//rg.addFormatedText("No records to display ", 0, 0, 1, 0);
				Object[][] obj1 = new Object[1][1];
				obj1[0][0] = "No records to display" ; 
				
				TableDataSet tds = new TableDataSet();
				tds.setData(tableData);
				tds.setCellAlignment(new int[] {1});
				tds.setCellWidth(new int[] {100});
				tds.setBorder(true);
				tds.setBlankRowsBelow(1);
				rg.addTableToDoc(tds);
			}// end of catch

			//rg.addFormatedText("\n", 0, 0, 0, 0);

			/*
			 * String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM
			 * DUAL"; Object[][] dateData =
			 * getSqlModel().getSingleResult(dateQuery); Object bottomData[][] =
			 * new Object[4][2]; bottomData[0][0] = ""; bottomData[0][1] = "";
			 * bottomData[1][0] = ""; bottomData[1][1] = ""; bottomData[2][0] =
			 * ""; bottomData[2][1] = ""; bottomData[3][0] = "Date " +
			 * dateData[0][0] + ""; bottomData[3][1] = "\n\nSignature of the
			 * Employee or other authorised officer/Stamp of the " +
			 * "Factory/Establishment";
			 * 
			 * rg.tableBodyNoBorder(bottomData, new int[] { 60, 40 }, new int[] {
			 * 30, 70 });
			 */

			//rg.createReport(response);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				rg.saveReport(response);
			}
		} catch (Exception e) {

		}
	}// end of getReport

	public static String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

}
