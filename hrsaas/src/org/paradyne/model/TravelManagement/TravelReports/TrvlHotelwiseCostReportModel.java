/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanApplication;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlHotelwiseCostReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author AA0623
 *
 */
public class TrvlHotelwiseCostReportModel extends ModelBase {

	public void generateHotelwiseCostReport(HttpServletResponse response,
			TrvlHotelwiseCostReport bean) {
		try {
			String query = "SELECT NVL(HOTEL_NAME,' ') HOTEL, NVL(HOTEL_CITY,' ') LOC, NVL(DECODE(HOTEL_ZONE,'EA','East','WE','West', "
				+ " 'NO','North','SO','South','CE','Central'),' ') HOTELZONE, SUM(LODGE_NOOFDAYS) DAYS,  "
				+ " SUM(LODGE_BOOKING_AMT) AMOUNT,HOTEL_AVERAGE_RATING FEEDBACK "
				+ " FROM TMS_APPLICATION " 
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID = TMS_BOOK_LODGE.LODGE_HOTEL_ID) " 
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				/*+ " FROM TMS_BOOK_LODGE "
				+ " INNER JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID = TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_BOOK_LODGE .TRAVEL_APPL_ID) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID ) "*/
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >= "
				+ " TO_DATE('"
				+bean.getFromDate()
				+ "','DD-MM-YYYY')"
				+ " AND TOUR_TRAVEL_STARTDT <= "
				+ " TO_DATE('"
				+bean.getToDate()
				+ " ','DD-MM-YYYY') AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' " 
				+ " GROUP BY HOTEL_NAME,HOTEL_CITY,HOTEL_ZONE,HOTEL_AVERAGE_RATING ";
			Object[][] hotelwiseObj = getSqlModel().getSingleResult(query);
			
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
			rds.setFileName("Hotelwise Cost Report");
			rds.setReportName("Hotelwise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet titleName = new TableDataSet();
			
			Object obj [][] = null;
		///	if(reportType.equals("Xls")){
				/*obj = new Object[1][3];
			obj[0][0] = "Hotelwise cost report for the";
			obj[0][1] = "period "+bean.getFromDate()+" to "+bean.getToDate();
			obj[0][2] = "Currency Type : "+bean.getCurrencyType();*/
			
				String filters = "Hotelwise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
	
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
		///	}
			/*titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Hotelwise cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				obj[0][2] = "Currency Type : "+bean.getCurrencyType();
				titleName.setData(obj);
			}*/
			
			/*titleName.setBlankRowsBelow(obj.length);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);*/
			if (hotelwiseObj != null && hotelwiseObj.length > 0 ) {
				
				Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "HotelWise Details: " ;
				
				TableDataSet tableheadingDateData = new TableDataSet();
				tableheadingDateData.setData(summaryData);
				tableheadingDateData.setCellWidth(new int[]{100});
				tableheadingDateData.setCellAlignment(new int[]{0});  
				tableheadingDateData.setBodyFontStyle(1);  
				tableheadingDateData.setCellColSpan(new int[]{16});
				////tableheadingDateData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableheadingDateData);
				
				TableDataSet hotelwiseCostTable = new TableDataSet();
				hotelwiseCostTable.setHeader(new String[]{"Name of Hotel","Location", "Zone","Total days of stay","Total Amount","Feedback"});
				hotelwiseCostTable.setData(hotelwiseObj);
				hotelwiseCostTable.setCellAlignment(new int[]{0,0,0,1,2,1});
				hotelwiseCostTable.setCellWidth(new int[]{40,30,20,20,20,20});
				hotelwiseCostTable.setBorder(true);
				hotelwiseCostTable.setHeaderTable(false);
				rg.addTableToDoc(hotelwiseCostTable);
			}else{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No Records available for the period "+bean.getFromDate()+" to "+bean.getToDate();

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
			///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public boolean setCurrencyList(HttpServletResponse response,
			TrvlHotelwiseCostReport report) {
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
