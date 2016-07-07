package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlFrequentTravellerReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class TrvlFrequentTravellerReportModel extends ModelBase {

	public void generateReport(TrvlFrequentTravellerReport bean,
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
			rds.setFileName("Frequent Traveller Report");
			rds.setReportName("Frequent Traveller Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj [][] = new Object[1][2];
			obj[0][0] = "Frequent Traveller report for the";
			obj[0][1] = "period "+bean.getFromDate()+" to "+bean.getToDate();
			//subtitleName.setData(new Object[][] { {"Frequent Traveller Report For the Period" },{bean.getFromDate()+" To "+bean.getToDate()}});
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1,1 });
			subtitleName.setCellWidth(new int[] { 50,50 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);
			
			String frequentTravellerEmpwise = " SELECT * FROM ( SELECT EMP_FNAME || ' ' || EMP_LNAME, " 
					 + " NVL(DEPT_NAME,' ') DEPARTMENT, NVL(CENTER_NAME,' ') AS LOC, "
					 + " NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', " 
					 + " 'South','CE','Central'),' ') CT_ZONE, COUNT(*) AS COUNTS FROM TMS_APPLICATION "
					 + " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					 + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) " 
					 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					 + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					 + " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND "
					 + " TOUR_TRAVEL_STARTDT >= "
					 + " TO_DATE('"
					 +bean.getFromDate()
					 + "','DD-MM-YYYY')"
					 + " AND TOUR_TRAVEL_STARTDT <= "
					 + " TO_DATE('"
					 +bean.getToDate()
					  + "','DD-MM-YYYY')" 
					 + " GROUP BY EMP_FNAME || ' ' || EMP_LNAME, DEPT_NAME, CENTER_NAME, CENTER_ZONE "
					 + " ORDER BY COUNTS DESC " 
					 + " )";
			
			Object[][] frequentData = getSqlModel().getSingleResult(frequentTravellerEmpwise);
			
			if (frequentData != null && frequentData.length > 0) {
				System.out.println("frequentData.length ====== "+ frequentData.length);
				String[] header = { "Name of Applicant", "Department",
						"Location", "Zone", "No of Trips"};
				int[] alignment = { 0, 0, 0, 0, 1 };
				int[] cellwidth = { 20, 15, 25, 25, 10 };
				TableDataSet frequentTraveller = new TableDataSet();
				frequentTraveller.setData(frequentData);
				frequentTraveller.setHeader(header);
				frequentTraveller.setCellAlignment(alignment);
				frequentTraveller.setCellWidth(cellwidth);
				frequentTraveller.setHeaderBGColor(new Color(192, 192, 192));
				frequentTraveller.setBlankRowsBelow(frequentData.length);
				frequentTraveller.setBorder(true);
				frequentTraveller.setHeaderTable(false);
				rg.addTableToDoc(frequentTraveller);
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
				
				String frequentTravellerModewiseQuery = " SELECT * FROM ( "
					+ " SELECT TMS_BOOK_SOURCE,TMS_BOOK_DESTINATION , HRMS_TMS_JOURNEY_MODE.JOURNEY_NAME  , COUNT(*) COUNTS "
					+ " FROM TMS_BOOK_TRAVEL "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS  ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) " 
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID = HRMS_TMS_JOURNEY_MODE.JOURNEY_ID) " 
					+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_BOOK_TRAVEL.TRAVEL_APPL_ID ) "
					+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >= "
					+ "TO_DATE('"
					 +bean.getFromDate()
					 + "','DD-MM-YYYY')"
					 +"AND TOUR_TRAVEL_STARTDT <= "
					 + "TO_DATE('"
					 +bean.getToDate()
					  + "','DD-MM-YYYY')" 
					+ " GROUP BY TMS_BOOK_SOURCE,TMS_BOOK_DESTINATION, HRMS_TMS_JOURNEY_MODE.JOURNEY_NAME " 
					+ " ORDER BY COUNTS DESC)"	;
				Object[][] frequentModewiseData = getSqlModel().getSingleResult(frequentTravellerModewiseQuery);
				
				if(frequentModewiseData!=null && frequentModewiseData.length > 0) {
				String[] header = { "Source", "Destination",
						"Mode", "No of Trips" };
				int[] alignment = { 1, 0, 0, 1 };
				int[] cellwidth = { 20, 15, 25, 25 };
				TableDataSet modewisetable = new TableDataSet();
				modewisetable.setData(frequentModewiseData);
				modewisetable.setHeader(header);
				modewisetable.setCellAlignment(alignment);
				modewisetable.setCellWidth(cellwidth);
				modewisetable.setHeaderBGColor(new Color(192, 192, 192));
				modewisetable.setBorder(true);
				modewisetable.setHeaderTable(false);
				rg.addTableToDoc(modewisetable);
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
