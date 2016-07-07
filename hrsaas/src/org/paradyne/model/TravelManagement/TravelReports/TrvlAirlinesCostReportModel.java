/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAirlinesCostReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0623
 *
 */
public class TrvlAirlinesCostReportModel extends ModelBase {
	
	public LinkedHashMap<String, String> setTravelModes() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

		String query = " SELECT JOURNEY_ID, JOURNEY_NAME FROM HRMS_TMS_JOURNEY_MODE ORDER BY JOURNEY_ID ";
		Object[][] mapObj = getSqlModel().getSingleResult(query);
		if (mapObj != null && mapObj.length > 0) {
			for (int i = 0; i < mapObj.length; i++) {
				map.put(String.valueOf(mapObj[i][0]), String.valueOf(mapObj[i][1]));
			}
		}
		return map;
	}
	
	public void generateModewiseCostReport(
			HttpServletResponse response, TrvlAirlinesCostReport bean) {
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
			rds.setFileName("Airlinewise Cost Report");
			rds.setReportName("Airlinewise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			/*TableDataSet titleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][3];
			obj[0][0] = "Airline modewise cost report for";
			obj[0][1] = " the period  "+bean.getFromDate()+" to ";
			obj[0][2] = bean.getToDate();
			titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Airline modewise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName.setData(obj);
			}
			titleName.setBlankRowsBelow(obj.length);
			//titleName.setData(new Object[][] { { "Airlinewise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			*/
			
			String filters = "Airline modewise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
			if (!bean.getCurrencyType().equals("")) {
				filters += "\n\nCurrency Type : " + bean.getCurrencyType();
			}
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			String modewiseQuery = "SELECT NVL(TRAVEL_CARRIER_NAME,' '),SUM(TMS_BOOK_TRAVEL_COST) BEST_FAIR, SUM(TMS_BOOK_TRAVEL_ACTUALCOST) ACTUAL, " 
				+ " SUM(TMS_BOOK_TRAVELCANCEL_AMT) CANCELLATIONS, '', COUNT(*) NO_OF_TRIPS  "
				+ " FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID)  "
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "  
				+ " INNER JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER  "
				+ " AND TMS_CARRIER.TRAVEL_MODE_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) " 
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TRAVEL_MODE_ID = "
				+bean.getTravelMode()
				+ " AND TOUR_TRAVEL_STARTDT >=" 
				+ " TO_DATE('"
				+bean.getFromDate()
				+ "','DD-MM-YYYY')"
				+"AND TOUR_TRAVEL_STARTDT <= "
				+ "TO_DATE('"
				+bean.getToDate()
				+ "','DD-MM-YYYY')"
				+ " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY TRAVEL_CARRIER_NAME ";
				

			Object[][] dataObj = getSqlModel().getSingleResult(modewiseQuery);
			Object[][] modewiseObj = null;
			
			String precentQuery = " SELECT SUM(TMS_BOOK_TRAVEL_COST) BEST_FAIR FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) " 
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "  
				+ " INNER JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER  "
				+ " AND TMS_CARRIER.TRAVEL_MODE_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) " 
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID)  "
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C')  AND TRAVEL_MODE_ID = "+bean.getTravelMode()
				+ " AND TOUR_TRAVEL_STARTDT >=" 
				+ " TO_DATE('"
				+ bean.getFromDate()
 				+ " ','DD-MM-YYYY')"
 				+ " AND TOUR_TRAVEL_STARTDT <= "
 				+ " TO_DATE('"
 				+ bean.getToDate()
				+ " ','DD-MM-YYYY')"
				+ " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY TRAVEL_MODE_ID";
			
			Object[][] precentObj = getSqlModel().getSingleResult(precentQuery);
			
			
			if(dataObj != null && dataObj.length > 0){
				modewiseObj = new Object[dataObj.length][6];
			}
			double percent = 0.0;
			if (modewiseObj != null && modewiseObj.length > 0) {
				for (int i = 0; i < modewiseObj.length; i++) {
					modewiseObj[i][0] = String.valueOf(dataObj[i][0]);
					modewiseObj[i][1] = String.valueOf(dataObj[i][1]);
					modewiseObj[i][2] = String.valueOf(dataObj[i][2]);
					modewiseObj[i][3] = String.valueOf(dataObj[i][3]);
					
					if(precentObj!=null && precentObj.length >0){
						double sumOfBestFare = Double.parseDouble(String.valueOf(precentObj[0][0]));
						percent = (Double.parseDouble(String.valueOf(dataObj[i][1]))/sumOfBestFare)*100;
					}
					modewiseObj[i][4] = Utility.twoDecimals(percent);//percent data
					modewiseObj[i][5] = String.valueOf(dataObj[i][5]);
				}
				
				
				TableDataSet airlinewiseTable = new TableDataSet();
				airlinewiseTable.setHeader(new String[] { "Carrier",
						"Best Fare", "Actual Fare", "Cancellations","%Expense", "No of Trips" });
				airlinewiseTable.setData(modewiseObj);
				airlinewiseTable
						.setCellAlignment(new int[] { 0, 1, 1, 1, 1, 1 });
				airlinewiseTable.setCellWidth(new int[] { 15, 10, 10, 10, 10, 10 });
				airlinewiseTable.setBorder(true);
				airlinewiseTable.setHeaderTable(false);
				rg.addTableToDoc(airlinewiseTable);
			}else{
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
	
	public void generateRoutewiseModewiseCostReport(
			HttpServletResponse response, TrvlAirlinesCostReport bean) {
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
			rds.setFileName("Routewise Airlinewise Cost Report");
			rds.setReportName("Routewise Airlinewise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
		
			
			/*TableDataSet titleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][3];
			obj[0][0] = "Airline routewise cost report for";
			obj[0][1] = " the period  "+bean.getFromDate()+" to ";
			obj[0][2] = bean.getToDate();
			titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Airline routewise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName.setData(obj);
			}
			titleName.setBlankRowsBelow(obj.length);
			//titleName.setData(new Object[][] { { "Routewise Airlinewise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);*/
			
			
			String filters = "Airline routewise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
			if (!bean.getCurrencyType().equals("")) {
				filters += "\n\nCurrency Type : " + bean.getCurrencyType();
			}
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			String routewiseQuery = " SELECT LOWER(TMS_BOOK_SOURCE), LOWER(TMS_BOOK_DESTINATION), NVL(TRAVEL_CARRIER_NAME,' '),  SUM(TMS_BOOK_TRAVEL_COST) BEST_FAIR, " 
					+ " SUM(TMS_BOOK_TRAVEL_ACTUALCOST) BKNG_EXPNS,  SUM(TMS_BOOK_TRAVELCANCEL_AMT) CNCL_EXPNS, '', COUNT(*) NO_OF_TRIPS " 
					+ " FROM TMS_APPLICATION "
					+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID)  "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) " 
					+ " INNER JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER "
					+ " AND TMS_CARRIER.TRAVEL_MODE_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID "
					+ " ) "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "  
					+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TRAVEL_MODE_ID = "
					+bean.getTravelMode()
					+ " AND TOUR_TRAVEL_STARTDT >=" 
					 + "TO_DATE('"
					 +bean.getFromDate()
					 + "','DD-MM-YYYY')"
					 +"AND TOUR_TRAVEL_STARTDT <= "
					 + "TO_DATE('"
					 +bean.getToDate()
					  + "','DD-MM-YYYY')"
					+ " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY LOWER(TMS_BOOK_SOURCE), LOWER(TMS_BOOK_DESTINATION),TRAVEL_CARRIER_NAME";  

			Object[][] dataObj = getSqlModel().getSingleResult(routewiseQuery);
			String precentQuery = " SELECT SUM(TMS_BOOK_TRAVEL_COST) BEST_FAIR FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) " 
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "  
				+ " INNER JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER  "
				+ " AND TMS_CARRIER.TRAVEL_MODE_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) " 
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID)  "
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C')  AND TRAVEL_MODE_ID = "+bean.getTravelMode()
				+ " AND TOUR_TRAVEL_STARTDT >=" 
				+ " TO_DATE('"
				+ bean.getFromDate()
 				+ " ','DD-MM-YYYY')"
 				+ " AND TOUR_TRAVEL_STARTDT <= "
 				+ " TO_DATE('"
 				+ bean.getToDate()
				+ " ','DD-MM-YYYY')"
				+ " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY TRAVEL_MODE_ID";
			
			Object[][] precentObj = getSqlModel().getSingleResult(precentQuery);
			
			Object[][] routewiseObj = null;
			if (dataObj != null && dataObj.length > 0) {
				routewiseObj = new Object[dataObj.length][8];
			}
			double percent = 0.00;
			if (routewiseObj != null && routewiseObj.length > 0) {
				for (int i = 0; i < routewiseObj.length; i++) {
					routewiseObj[i][0] = String.valueOf(dataObj[i][0]);
					routewiseObj[i][1] = String.valueOf(dataObj[i][1]);
					routewiseObj[i][2] = String.valueOf(dataObj[i][2]);
					routewiseObj[i][3] = String.valueOf(dataObj[i][3]);
					if(precentObj!=null && precentObj.length >0){
						double sumOfBestFare = Double.parseDouble(String.valueOf(precentObj[0][0]));
						percent = (Double.parseDouble(String.valueOf(dataObj[i][3]))/sumOfBestFare)*100;
					}
					routewiseObj[i][4] = String.valueOf(dataObj[i][4]);
					routewiseObj[i][5] = String.valueOf(dataObj[i][5]);
					routewiseObj[i][6] = Utility.twoDecimals(percent);//percent data
					routewiseObj[i][7] = String.valueOf(dataObj[i][7]);
				}
				TableDataSet airlinewiseTable = new TableDataSet();
				airlinewiseTable.setHeader(new String[] { "Source",
						"Destination", "Carrier", "Best Fare", "Actual Fare", "Cancel Expense","%Expense",
						"No of Trips" });
				airlinewiseTable.setData(routewiseObj);
				airlinewiseTable.setCellAlignment(new int[] { 0, 0, 0, 1, 1, 1, 1, 1 });
				airlinewiseTable.setCellWidth(new int[] { 15, 15, 25, 10, 10, 10, 10, 10 });
				airlinewiseTable.setBorder(true);
				airlinewiseTable.setHeaderTable(false);
				rg.addTableToDoc(airlinewiseTable);
			}else{
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

	
	public boolean setCurrencyList(HttpServletResponse response,
			TrvlAirlinesCostReport report) {
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
