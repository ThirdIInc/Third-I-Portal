package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlBestTravellerReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class TrvlBestTravellerReportModel extends ModelBase {

	public void generateReport(TrvlBestTravellerReport bean,
			HttpServletResponse response) {
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";
			
			if (bean.getReportType().equals("Pdf")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("Xls")) {
				reportType = "Xls";
			}
		
			rds.setReportType(reportType);
			rds.setFileName("Travel Best Traveller Report");
			rds.setReportName("Travel Best Traveller Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][2];
			obj[0][0] = "Best traveller report for the";
			obj[0][1] = "period "+bean.getFromDate()+" to "+bean.getToDate();
			subtitleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Best traveller report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				subtitleName.setData(obj);
			}
			//subtitleName.setData(new Object[][] { { "Travel Best Traveller Report" } });
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBlankRowsBelow(obj.length);
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			rg.createHeader(subtitleName);
			
			String bestTravellerQuery = "  SELECT APPL.EMP_TOKEN,APPL.EMP_FNAME||' '||APPL.EMP_LNAME APPLICANT, " 
				+ " NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(CENTER_NAME,' ') AS LOC , "
				+ " NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', 'South','CE','Central'),' ') CT_ZONE ,"
				+ " SUM( CASE " 
				+ " WHEN TMS_BOOK_TRAVELCANCEL_AMT > 0 THEN -10 ELSE 0 END + "
				+ " CASE "
				+ " WHEN (EXP_TRVL_START_DATE-APPL_DATE ) < 0 THEN -10 " 
				+ " WHEN (EXP_TRVL_START_DATE-APPL_DATE ) BETWEEN 0 AND 3 THEN -5 "
				+ " WHEN (EXP_TRVL_START_DATE-APPL_DATE ) BETWEEN 3 AND 5 THEN 5 "
				+ " WHEN (EXP_TRVL_START_DATE-APPL_DATE ) BETWEEN 5 AND 10 THEN 10 "
				+ " WHEN (EXP_TRVL_START_DATE-APPL_DATE ) > 10  THEN 20 ELSE 0 END + "
				+ " CASE "
				+ " WHEN (EXP_APP_DATE-EXP_TRVL_END_DATE ) BETWEEN 0 AND 3 THEN 5 "
				+ " WHEN (EXP_APP_DATE-EXP_TRVL_END_DATE ) BETWEEN 3 AND 15 THEN 2 "
				+ " WHEN (EXP_APP_DATE-EXP_TRVL_END_DATE ) BETWEEN 15 AND 30 THEN 0 "
				+ " WHEN ((EXP_APP_DATE-EXP_TRVL_END_DATE ) > 30 OR (EXP_APP_DATE-EXP_TRVL_END_DATE ) < 0 )  THEN -10 ELSE 0 END ) AS POINTS "
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_CLAIM_APPL.EXP_TRVL_APPID=TMS_APPLICATION.APPL_ID) " 
				+ " INNER JOIN HRMS_EMP_OFFC APPL ON (APPL.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = APPL.EMP_DEPT) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = APPL.EMP_CENTER)" 
				+ " INNER JOIN TMS_BOOK_TRAVEL ON(TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >= "
				+ " TO_DATE('"
				+bean.getFromDate()
				+ " ','DD-MM-YYYY')"
				+ " AND TOUR_TRAVEL_STARTDT <= "
				+ " TO_DATE('"
				+bean.getToDate()
				+ " ','DD-MM-YYYY')" 
				+ " GROUP BY APPL.EMP_TOKEN,APPL.EMP_FNAME||' '||APPL.EMP_LNAME, " 
				+ " NVL(DEPT_NAME,' ') , NVL(CENTER_NAME,' '), CENTER_ZONE, TMS_BOOK_TRAVELCANCEL_AMT "
				+ " ORDER BY POINTS DESC ";
			Object[][] bestTravellerData = getSqlModel().getSingleResult(bestTravellerQuery);
			
						
			/*Object[][] bestTravellerDataObj = new Object[bestTravellerData.length][5];
			if(bestTravellerData!= null && bestTravellerData.length > 0){
				for (int i = 0; i < bestTravellerDataObj.length; i++) {
					bestTravellerDataObj[i][0] = String.valueOf(bestTravellerData[i][0]);
					bestTravellerDataObj[i][1] = String.valueOf(bestTravellerData[i][1]);
					bestTravellerDataObj[i][2] = String.valueOf(bestTravellerData[i][2]);
					bestTravellerDataObj[i][3] = String.valueOf(bestTravellerData[i][3]);
					bestTravellerDataObj[i][4] = String.valueOf(bestTravellerData[i][4]);
				}
			}*/
			
			if (bestTravellerData != null && bestTravellerData.length > 0) {
				System.out.println("bestTravellerData.length ====== "	+ bestTravellerData.length);
				String[] header = {"Applicant ID", "Applicant Name", "Department",	"Location", "Zone", "Points" };
				int[] alignment = { 0, 0, 0, 0,0, 2};
				int[] cellwidth = { 15, 25, 25, 20, 15,10 };
				TableDataSet bestTravellertable = new TableDataSet();
				bestTravellertable.setData(bestTravellerData);
				bestTravellertable.setHeader(header);
				bestTravellertable.setCellAlignment(alignment);
				bestTravellertable.setCellWidth(cellwidth);
				bestTravellertable.setHeaderBGColor(new Color(192, 192, 192));
				bestTravellertable.setBorder(true);
				bestTravellertable.setHeaderTable(false);
				rg.addTableToDoc(bestTravellertable);
			} else {
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
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
