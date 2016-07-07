/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAccomodationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0623
 * 
 */
public class TrvlAccomodationReportModel extends ModelBase {
	public void generateEmpwiseAccommodationReport(
			HttpServletResponse response, TrvlAccomodationReport bean) {
		try {
			String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, CADRE_NAME, '', NVL(HOTEL_TYPE_NAME,' '), SUM(LODGE_BOOKING_AMT) "
					+ " ,sum(NVL(LODGE_NOOFDAYS,0)) FROM TMS_APPLICATION "
					+ " INNER JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE) "
					+ " WHERE  APPL_STATUS IN ('C') ";
			if (!bean.getFromDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getFromDate() + "','DD-MM-YYYY') ";
			}
			if (!bean.getToDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, CADRE_NAME, HOTEL_TYPE_NAME ORDER BY EMP_FNAME||' '||EMP_LNAME";


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
			rds.setFileName("Employeewise Accommodation Report");
			rds.setReportName("Employeewise Accommodation Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Accommodation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			Object[][] empwiseObj = getSqlModel().getSingleResult(query);
			
			if (empwiseObj != null && empwiseObj.length > 0) {
				TableDataSet titleName = new TableDataSet();
				titleName
						.setData(new Object[][] { { "Employeewise Accommodation Report" } });
				titleName.setCellAlignment(new int[] { 1 });
				titleName.setCellWidth(new int[] { 100 });
				titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				titleName.setBorder(false);
				rg.createHeader(titleName);
				Object[][] accomObj = null;
			
			HashMap<String, Integer> daysMap = new HashMap<String, Integer>();

			int totalDays = 0;
			
			if (empwiseObj != null && empwiseObj.length > 0) {
				for (int i = 0; i < empwiseObj.length; i++) {

					if (!daysMap.containsKey(String.valueOf(empwiseObj[i][0]))) {
						totalDays =Integer
						.parseInt(String.valueOf(empwiseObj[i][6]));
						daysMap.put(String.valueOf(empwiseObj[i][0]), totalDays);
					} else {
						totalDays = daysMap.get(String
								.valueOf(empwiseObj[i][0]));
						totalDays += Integer.parseInt(String
								.valueOf(empwiseObj[i][6]));
						daysMap
								.put(String.valueOf(empwiseObj[i][0]),
										totalDays);
					}

				}
			}
		  
			Set<String> empWise = new HashSet<String>();
			if (empwiseObj != null && empwiseObj.length > 0) {
				for (int i = 0; i < empwiseObj.length; i++) {
					empWise.add(String.valueOf(empwiseObj[i][1]));
				}
				accomObj = Utility.transverse(empwiseObj,
						new int[] { 0, 1, 2, 3 }, 4, 5, "0", true, new String[] {
								"Applicant Id", "Applicant Name", "Grade", "No Of Days" });
			}

			 if(accomObj!=null && accomObj.length >0)
			 {
				 for (int i = 1; i < accomObj.length; i++) {
						accomObj[i][3] = daysMap.get(String.valueOf(accomObj[i][0]));
						 
					} 
			 }
		 	
			int cellAlignmentCount =0;
			 if(accomObj!=null && accomObj.length >0)
			 {
				 cellAlignmentCount= accomObj[0].length; //empWise.size() + 2;
			 }
				
				
			int[] cellAlignArray = new int[cellAlignmentCount];
			int[] cellWidthArray = new int[cellAlignmentCount];
			for (int i = 0; i < cellAlignmentCount; i++) {
				cellAlignArray[i] = 0;
				if (i == 0) {
					cellWidthArray[i] = 40;
				} else {
					cellWidthArray[i] = 30;
				}
			}

			TableDataSet empwiseCostTable = new TableDataSet();
			empwiseCostTable.setData(accomObj);
			empwiseCostTable.setCellAlignment(cellAlignArray);
			empwiseCostTable.setCellWidth(cellWidthArray);
			empwiseCostTable.setBorder(true);
			empwiseCostTable.setHeaderTable(false);
			rg.addTableToDoc(empwiseCostTable);
			
			}else{
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(new Object[][]{{"No data to display"}});
				empwiseCostTable.setCellAlignment(new int[]{0});
				empwiseCostTable.setCellWidth(new int[]{100});
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				rg.addTableToDoc(empwiseCostTable);
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateDeptwiseAccommodationReport(
			HttpServletResponse response, TrvlAccomodationReport bean) {
		try {
			String query = "SELECT NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(HOTEL_TYPE_NAME,' '), SUM(LODGE_BOOKING_AMT) "
					+ " FROM TMS_APPLICATION "
					+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE) "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE  APPL_STATUS IN ('C') ";
			if (!bean.getFromDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getFromDate() + "','DD-MM-YYYY') ";
			}
			if (!bean.getToDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY DEPT_NAME, HOTEL_TYPE_NAME ORDER BY DEPT_NAME";
			Object[][] deptwiseObj = getSqlModel().getSingleResult(query);

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
			rds.setFileName("Departmentwise Accommodation Report");
			rds.setReportName("Departmentwise Accommodation Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Accommodation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			
			Object[][] accomObj = null;
			
			if(deptwiseObj != null && deptwiseObj.length > 0 ){
			
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Departmentwise Accommodation Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			

			Set<String> empWise = new HashSet<String>();
			if (deptwiseObj != null && deptwiseObj.length > 0) {
				for (int i = 0; i < deptwiseObj.length; i++) {
					empWise.add(String.valueOf(deptwiseObj[i][1]));
				}
				accomObj = Utility.transverse(deptwiseObj, new int[] { 0 }, 1,
						2, "0", true, new String[] { "Department" });
			}

			int cellAlignmentCount =0;
			if(accomObj!=null && accomObj.length>0){
				cellAlignmentCount=accomObj[0].length;// empWise.size() + 2;
			}
			int[] cellAlignArray = new int[cellAlignmentCount];
			int[] cellWidthArray = new int[cellAlignmentCount];
			for (int i = 0; i < cellAlignmentCount; i++) {
				cellAlignArray[i] = 0;
				if (i == 0) {
					cellWidthArray[i] = 40;
				} else {
					cellWidthArray[i] = 30;
				}
			}

			int[] rowSumArray = new int[empWise.size() + 1];
			int count = 1;
			for (int i = 0; i < rowSumArray.length; i++) {
				rowSumArray[i] = count;
				count++;
			}

			TableDataSet empwiseCostTable = new TableDataSet();
			empwiseCostTable.setData(accomObj);
			empwiseCostTable.setCellAlignment(cellAlignArray);
			empwiseCostTable.setCellWidth(cellWidthArray);
			empwiseCostTable.setBorder(true);
			empwiseCostTable.setHeaderTable(false);
			// empwiseCostTable.setRowSum(rowSumArray);
			rg.addTableToDoc(empwiseCostTable);
			
			}else{
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(new Object[][]{{"No data to display"}});
				empwiseCostTable.setCellAlignment(new int[]{0});
				empwiseCostTable.setCellWidth(new int[]{100});
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				rg.addTableToDoc(empwiseCostTable);
			}
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateZonewiseAccommodationReport(
			HttpServletResponse response, TrvlAccomodationReport bean) {
		try {
			String query = "SELECT NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', "
					+ " 'South','CE','Central'),' ') CT_ZONE, NVL(HOTEL_TYPE_NAME,' '), SUM(LODGE_BOOKING_AMT) "
					+ " FROM TMS_APPLICATION "
					+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID)"
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " WHERE CENTER_ZONE IS NOT NULL AND APPL_STATUS IN ('C') ";
			if (!bean.getFromDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getFromDate() + "','DD-MM-YYYY') ";
			}
			if (!bean.getToDate().equals("")) {
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY CENTER_ZONE, HOTEL_TYPE_NAME ORDER BY CENTER_ZONE ";
			Object[][] zonewiseObj = getSqlModel().getSingleResult(query);

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
			rds.setFileName("Zonewise Accommodation Report");
			rds.setReportName("Zonewise Accommodation Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Accommodation report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			if (zonewiseObj != null && zonewiseObj.length > 0) {
				TableDataSet titleName = new TableDataSet();
				titleName
						.setData(new Object[][] { { "Zonewise Accommodation Report" } });
				titleName.setCellAlignment(new int[] { 1 });
				titleName.setCellWidth(new int[] { 100 });
				titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				titleName.setBorder(false);
				rg.createHeader(titleName);
				Object[][] accomObj = null;

			Set<String> empWise = new HashSet<String>();
			if (zonewiseObj != null && zonewiseObj.length > 0) {
				for (int i = 0; i < zonewiseObj.length; i++) {
					empWise.add(String.valueOf(zonewiseObj[i][1]));
				}
				accomObj = Utility.transverse(zonewiseObj, new int[] { 0 }, 1,
						2, "0", true, new String[] { "Zone" });
			}

			int cellAlignmentCount = 0;
			if(accomObj!=null && accomObj.length>0){
				cellAlignmentCount =accomObj[0].length;
			}
			//empWise.size() + 2;
			int[] cellAlignArray = new int[cellAlignmentCount];
			int[] cellWidthArray = new int[cellAlignmentCount];
			for (int i = 0; i < cellAlignmentCount; i++) {
				cellAlignArray[i] = 0;
				if (i == 0) {
					cellWidthArray[i] = 40;
				} else {
					cellWidthArray[i] = 30;
				}
			}

			int[] rowSumArray = new int[empWise.size() + 1];
			int count = 1;
			for (int i = 0; i < rowSumArray.length; i++) {
				rowSumArray[i] = count;
				count++;
			}

			TableDataSet empwiseCostTable = new TableDataSet();
			empwiseCostTable.setData(accomObj);
			empwiseCostTable.setCellAlignment(cellAlignArray);
			empwiseCostTable.setCellWidth(cellWidthArray);
			empwiseCostTable.setBorder(true);
			empwiseCostTable.setHeaderTable(false);
			// empwiseCostTable.setRowSum(rowSumArray);
			rg.addTableToDoc(empwiseCostTable);
			
			}else{
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(new Object[][]{{"No data to display"}});
				empwiseCostTable.setCellAlignment(new int[]{0});
				empwiseCostTable.setCellWidth(new int[]{100});
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				rg.addTableToDoc(empwiseCostTable);
			}
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean setCurrencyList(HttpServletResponse response,
			TrvlAccomodationReport report) {
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
