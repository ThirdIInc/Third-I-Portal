package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlBookingReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class TrvlBookingReportModel extends ModelBase {

public void generateReport(TrvlBookingReport bean, HttpServletResponse response) {
		
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
			rds.setFileName("Travel Booking Report");
			rds.setReportName("Travel Booking Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet subtitleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][2];
			obj[0][0] = "Travel booking report for the";
			obj[0][1] = " period "+bean.getFromDate()+" to "+bean.getToDate();
			subtitleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Travel booking report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				subtitleName.setData(obj);
			}
			
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);
			

				String filters = "\n\nCurrency Type : " + bean.getCurrencyType();
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			String travelQuery = " SELECT DISTINCT APPL_TRAVEL_ID, TO_CHAR(APPL_DATE,'DD-MM-YYYY') APPLDATE, "
				+ " TO_CHAR(TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY')||' '||TMS_BOOK_TRAVEL_TIME BOOK_DATETIME, "
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') STARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') ENDDATE, " 
				+ " EMP_TOKEN , EMP_FNAME||' '||EMP_LNAME APPLICANT, NVL(CENTER_NAME,' ') AS LOC,NVL(DECODE(CENTER_ZONE, " 
				+ " 'EA','East','WE','West','NO','North','SO','South','CE','Central'),' ') CT_ZONE, " 
				+ " NVL(JOURNEY_NAME,' ') ||'-'||NVL(CLASS_NAME,' ') TRAVELMODE, NVL(TRAVEL_CARRIER_NAME,' ') CARRIER, " 
				+ " CASE WHEN APPL_STATUS = 'F' THEN 'Cancelled' ELSE 'Booking Completed' END STATUS, " 
				+ " NVL(TMS_BOOK_TRAVEL_COST,0) BOOKAMT, TO_CHAR(REVOKE_DATE,'DD-MM-YYYY') CANCELDATE, " 
				+ " NVL(DEPT_NAME,' ') AS DEPARTMENT, TMS_BOOK_SOURCE,TMS_BOOK_DESTINATION, " 
				+ " NVL(AGENCY_NAME,' ') AGENCY "
				+ " FROM TMS_BOOK_TRAVEL " 
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_BOOK_TRAVEL.TRAVEL_APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) " 
				+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) "  
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
				+ " INNER JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER) " 
				+ " INNER JOIN TMS_TRAVEL_AGENCY ON (TMS_TRAVEL_AGENCY.AGENCY_CODE = TMS_BOOK_TRAVEL.TMS_BOOK_AGENCYID) "
				+ " WHERE  TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >= "
				+ " TO_DATE('"
				+bean.getFromDate()
				+ "','DD-MM-YYYY')"
				+ " AND TOUR_TRAVEL_STARTDT <= "
				+ " TO_DATE('"
				+bean.getToDate()
				+ "','DD-MM-YYYY') AND TMS_BOOK_TRAVEL.TMS_BOOK_CURRENCY='"+bean.getCurrencyType()+"' ";
						
					
			Object[][] travelData = getSqlModel().getSingleResult(travelQuery);
			if (travelData != null && travelData.length > 0) {
				System.out.println("advanceData.length ====== "
						+ travelData.length);
				String[] header = { "Travel Id", "Application Date",
						"Booked for (Date/Time)", "Travel Date",
						"Return Date", "Applicant ID","Applicant Name", "Location", "Zone", 
						"Travel Mode", "Airline/Train Name", "Status","Booking Amount", "Cancel Date", 
						"Department", "Source", "Destination", "Agency Name" };
				int[] alignment = { 0, 1, 0, 1, 1, 0, 0,0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0 };
				int[] cellwidth = { 15, 15, 15, 15, 15,10, 30, 15, 10, 15, 20, 15, 10, 15, 20, 20, 20, 20 };
				TableDataSet advancetable = new TableDataSet();
				advancetable.setData(travelData);
				advancetable.setHeader(header);
				advancetable.setCellAlignment(alignment);
				advancetable.setCellWidth(cellwidth);
				advancetable.setHeaderBGColor(new Color(192, 192, 192));
				advancetable.setBorder(true);
				advancetable.setHeaderTable(false);
				advancetable.setBlankRowsBelow(1);
				rg.addTableToDoc(advancetable);
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
			
			String hotelQuery = " SELECT DISTINCT APPL_TRAVEL_ID, TO_CHAR(APPL_DATE,'DD-MM-YYYY') APPLDATE, "
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') STARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') ENDDATE,  "
				+ " EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME APPLICANT, NVL(CENTER_NAME,' ') AS LOC,NVL(DECODE(CENTER_ZONE, " 
				+ " 'EA','East','WE','West','NO','North','SO','South','CE','Central'),' ') CT_ZONE, "
				+ " NVL(HOTEL_NAME,' ') HOTEL, NVL(HOTEL_TYPE_NAME,' ') HOTELTYPE, NVL(ROOM_TYPE_NAME,' ') ROOMTYPE, " 
				+ " LODGE_CITY, LODGE_ADDRESS, TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY')||' '||LODGE_FRMTIME FROM_DATETIME, "
				+ " TO_CHAR(LODGE_TODATE,'DD-MM-YYYY')||' '||LODGE_TOTIME TO_DATETIME, LODGE_NOOFDAYS, "
				+ " LODGE_BOOKING_AMT, LODGE_DETAILS, LODGE_CORPORATE_RATE "
				+ " FROM TMS_BOOK_LODGE "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_BOOK_LODGE.TRAVEL_APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID = TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
				+ " LEFT JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID = TMS_BOOK_LODGE.LODGE_ROOMTYPE) "
				+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE) "
				+ " WHERE  TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >= "
				+ " TO_DATE('"
				+bean.getFromDate()
				+ "','DD-MM-YYYY')"
				+ " AND TOUR_TRAVEL_STARTDT <= "
				+ " TO_DATE('"
				+bean.getToDate()
				+ "','DD-MM-YYYY') AND TMS_BOOK_LODGE.LODGE_CURRENCY ='"+bean.getCurrencyType()+"' ";
			Object[][] hotelData = getSqlModel().getSingleResult(hotelQuery);
			if(hotelData!= null && hotelData.length > 0){
				
				TableDataSet titleName1 = new TableDataSet();
			
				Object obj1 [][] = null;
				if(reportType.equals("Xls")){
					obj1 = new Object[1][3];
					obj1[0][0] = "Travel hotel booking";
					obj1[0][1] = "report for the period ";
					obj1[0][2] = bean.getFromDate()+" to "+bean.getToDate();
				titleName1.setData(obj1);
				}else{
					obj1 = new Object[1][1];
					obj1[0][0] = "Travel hotel booking report for the period "+bean.getFromDate()+" to "+bean.getToDate();
					titleName1.setData(obj1);
				}
				
				titleName1.setCellAlignment(new int[] { 1 });
				titleName1.setCellWidth(new int[] { 100 });
				titleName1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				titleName1.setBorder(false);
				rg.addTableToDoc(titleName1);
				
				TableDataSet policyDeviationTable = new TableDataSet();
				policyDeviationTable.setHeader(new String[] { "Travel Id", "Application Date",
						 "Travel Date", "Return Date","Applicant ID", "Applicant Name", "Location", "Zone", 
						"Hotel Name", "Hotel Type", "Room Type","Hotel City", "Hotel Address", 
						"Lodging From","Lodging To", "No of Days", "Booking Amount", "Details", "Corporate Amount"});
				policyDeviationTable.setData(hotelData);
				policyDeviationTable.setCellAlignment(new int[] { 0, 1, 1, 1,
						0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2 });
				policyDeviationTable
						.setCellWidth(new int[] { 20, 10, 10, 10,10, 30, 15, 10,
								30, 20, 20, 25, 30, 10, 10, 10, 15, 20, 15});
				policyDeviationTable.setBlankRowsBelow(1);
				policyDeviationTable.setBorder(true);
				policyDeviationTable.setHeaderTable(false);
				rg.addTableToDoc(policyDeviationTable);
			}else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { " Travel hotel booking : No data to display" } });
				noData.setCellAlignment(new int[] { 0 });
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

	public boolean setCurrencyList(HttpServletResponse response,
		TrvlBookingReport report) {
		boolean result = false;
		String quer = " SELECT CURRENCY_ID,CURRENCY_ABBR FROM HRMS_CURRENCY ORDER BY CURRENCY_ID";
	
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		
		if(iterator!=null && iterator.length > 0){
			try {
				
				for (int i = 0; i < iterator.length; i++) {
					mp.put(String.valueOf(iterator[i][0]), String
							.valueOf(iterator[i][1]));
				}
				mp=(HashMap<Object, Object>)org.paradyne.lib.Utility.sortHashMapByKey(mp);
				report.setCurrencyHashmap(mp);
				 result =true ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			mp.put("","");
			report.setCurrencyHashmap(mp);
		}
		return result ;
	}
	
}
