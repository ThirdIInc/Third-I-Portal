/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlDeviationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0623
 *
 */
public class TrvlDeviationReportModel extends ModelBase {
	public void generateDeviationReport(HttpServletResponse response,
			TrvlDeviationReport bean) {
		try {
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";
			if (bean.getReportType().equals("Pdf")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("Xls")) {
				reportType = "Xls";
			}
			rds.setReportType(reportType);
			rds.setPageOrientation("landscape");
			rds.setFileName("Deviation Report");
			rds.setReportName("Deviation Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet titleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][3];
			obj[0][0] = "Application date deviation";
			obj[0][1] = "report for the period ";
			obj[0][2] = bean.getFromDate()+" to "+bean.getToDate();
			titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Application date deviation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName.setData(obj);
			}
			
			titleName.setBlankRowsBelow(obj.length);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			
			String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'  '||EMP_LNAME, "
					 + " NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(CADRE_NAME,' ') BAND, TO_CHAR(APPL_DATE,'DD-MM-YYYY') APPLDATE , " 
					 + " TO_CHAR( TMS_APPLICATION.TOUR_TRAVEL_STARTDT ,'DD-MM-YYYY') STR, TOUR_TRAVEL_STARTDT-APPL_DATE DEV "
					 + " FROM TMS_APP_EMPDTL "
					 + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = APPL_EMP_CODE) "
					 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					 + " INNER JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					 + " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) " 
					 + " WHERE APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT - APPL_DATE < 3 "
					 + " AND TOUR_TRAVEL_STARTDT >= "
					 + " TO_DATE('"
					 +bean.getFromDate()
					 + "','DD-MM-YYYY') "
					 + " AND TOUR_TRAVEL_STARTDT <= "
					 + " TO_DATE('"
					 +bean.getToDate()
					 + "','DD-MM-YYYY')" 
					 + " ORDER BY DEV DESC";
			Object[][] deviationObj = getSqlModel().getSingleResult(query);

			String policyDeviationquery = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'  '||EMP_LNAME, "
					 + " NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(CADRE_NAME,' ') BAND, HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_NAME , TMS_EXP_DTL.EXP_DTL_EXP_ELIGBLEAMT," 
					 + " TMS_EXP_DTL.EXP_DTL_EXP_AMT FROM TMS_EXP_DTL "
					 + " INNER JOIN TMS_CLAIM_APPL ON (TMS_CLAIM_APPL.EXP_APPID = TMS_EXP_DTL.EXP_APPID) "
					 + " INNER JOIN HRMS_TMS_EXP_CATEGORY ON ( TMS_EXP_DTL.EXP_DTL_EXP_TYPE =  HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID) "
					 + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
					 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					 + " INNER JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
					 + " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_EXP_DTL.EXP_APPID)"
					 + " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TMS_EXP_DTL.IS_POLICY_VIOLATED = 'Y'"
					 + " AND TOUR_TRAVEL_STARTDT >= "
					 + " TO_DATE('"
					 +bean.getFromDate()
					 + "','DD-MM-YYYY')"
					 +" AND TOUR_TRAVEL_STARTDT <= "
					 + "TO_DATE('"
					 +bean.getToDate()
					  + "','DD-MM-YYYY')" ;
				
				Object[][] policyDeviationObj = getSqlModel().getSingleResult(policyDeviationquery);
				
				String policyGenralDeviationquery = "SELECT APPL.EMP_TOKEN, APPL.EMP_FNAME||' '||APPL.EMP_LNAME APPLICANT, APPL_TRAVEL_ID, "
					 + " NVL(APPL_VIOLATION_DETAILS,' ') DEVN, DECODE(TRIM(APPL_STATUS),'P','Pending for approval', " 
					 + " 'C','Booking Completed','A','Approved','N','Draft','B','Sent Back','R','Rejected','F','Revoked') " 
					 + " STATUS, APPL_ID " 
					 + " FROM TMS_APPLICATION "
					 + " INNER JOIN HRMS_EMP_OFFC APPL ON (APPL.EMP_ID = TMS_APPLICATION.APPL_INITIATOR)" 
					 + " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND APPL_VIOLATION_DETAILS IS NOT NULL "
					 + " AND TOUR_TRAVEL_STARTDT >= "
					 + " TO_DATE('"
					 +bean.getFromDate()
					 + " ','DD-MM-YYYY') "
					 + " AND TOUR_TRAVEL_STARTDT <= "
					 + " TO_DATE('"
					 +bean.getToDate()
					 + "','DD-MM-YYYY')" 
					 + " ORDER BY APPL_ID ";
				Object[][] policyGenralDeviationObj = getSqlModel().getSingleResult(policyGenralDeviationquery);
				
			if (deviationObj != null && deviationObj.length > 0) {
				
				TableDataSet deviationTable = new TableDataSet();
				deviationTable.setHeader(new String[] {"Applicant ID", "Applicant",
						"Department", "Grade", "Application Date",
						"Start Date", "Deviation" });
				deviationTable.setData(deviationObj);
				deviationTable.setCellAlignment(new int[] {0, 0, 0, 0, 0, 0, 1 });
				deviationTable
						.setCellWidth(new int[] {10, 30, 10, 10, 15, 15, 10 });
				deviationTable.setBorder(true);
				deviationTable.setHeaderTable(false);
				deviationTable.setBlankRowsBelow(1);
				rg.addTableToDoc(deviationTable);
				
			}else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			} 
			
			TableDataSet titleName1 = new TableDataSet();
			
			Object obj1 [][] = null;
			if(reportType.equals("Xls")){
				obj1 = new Object[1][3];
				obj1[0][0] = "Claim deviation report";
				obj1[0][1] = "for the period " +bean.getFromDate();
				obj1[0][2] = " to "+bean.getToDate();
			titleName1.setData(obj1);
			}else{
				obj1 = new Object[1][1];
				obj1[0][0] = "Claim deviation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName1.setData(obj1);
			}
			
			titleName1.setBlankRowsBelow(1);
			titleName1.setCellAlignment(new int[] { 1 });
			titleName1.setCellWidth(new int[] { 100 });
			titleName1.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName1.setBorder(false);
			rg.addTableToDoc(titleName1);
			
			if(policyDeviationObj!= null && policyDeviationObj.length > 0){
				
				TableDataSet policyDeviationTable = new TableDataSet();
				policyDeviationTable.setHeader(new String[] { "Applicant ID","Applicant",
						"Department", "Grade", "Expense Category",
						"Expense Eligible Amount", "Expense Amount" });
				policyDeviationTable.setData(policyDeviationObj);
				policyDeviationTable.setCellAlignment(new int[] { 0,0, 0, 0, 0, 0, 1 });
				policyDeviationTable
						.setCellWidth(new int[] { 10,30, 10, 10, 20, 10, 10 });
				policyDeviationTable.setBlankRowsBelow(1);
				policyDeviationTable.setBorder(true);
				policyDeviationTable.setHeaderTable(false);
				rg.addTableToDoc(policyDeviationTable);
			}else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			
			TableDataSet titleName2 = new TableDataSet();
			Object obj2 [][] = null;
			if(reportType.equals("Xls")){
				obj2 = new Object[1][3];
			obj2[0][0] = "Travel application deviation";
			obj2[0][1] = "report for the period ";
			obj2[0][2] = bean.getFromDate()+" to "+bean.getToDate();
			titleName2.setData(obj2);
			}else{
				obj2 = new Object[1][1];
				obj2[0][0] = "Travel application deviation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName2.setData(obj2);
			}
			
			titleName2.setBlankRowsBelow(1);
			titleName2.setCellAlignment(new int[] { 1 });
			titleName2.setCellWidth(new int[] { 100 });
			titleName2.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName2.setBorder(false);
			rg.addTableToDoc(titleName2);
			if(policyGenralDeviationObj!= null && policyGenralDeviationObj.length > 0){
				
				TableDataSet policyGeneralTable = new TableDataSet();
				policyGeneralTable.setHeader(new String[] { "Applicant ID","Applicant",
						"Travel Id", "Deviation", "Status",	"Application Id"});
				policyGeneralTable.setData(policyGenralDeviationObj);
				policyGeneralTable.setCellAlignment(new int[] {0, 0, 0, 0, 0, 0});
				policyGeneralTable
						.setCellWidth(new int[] {10, 30, 10, 40, 10, 20 });
				policyGeneralTable.setBorder(true);
				policyGeneralTable.setHeaderTable(false);
				rg.addTableToDoc(policyGeneralTable);
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(true);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
