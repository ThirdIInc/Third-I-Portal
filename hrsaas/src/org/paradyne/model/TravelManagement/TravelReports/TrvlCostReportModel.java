/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlCostReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author AA0623
 *
 */
public class TrvlCostReportModel extends ModelBase {
	
	public void generateEmployeewiseCostReport(HttpServletResponse response,
			TrvlCostReport bean) {
		try {
			String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,CADRE_NAME, NVL(JOURNEY_NAME,' '), "
					+ " SUM(TMS_BOOK_TRAVEL_COST) "
					+ " FROM TMS_APPLICATION "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
					+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
					+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
					+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY ='"+bean.getCurrencyType()+"' GROUP BY EMP_FNAME||' '||EMP_LNAME, JOURNEY_NAME, EMP_TOKEN, CADRE_NAME ORDER BY EMP_FNAME||' '||EMP_LNAME";
					
			Object[][] empwiseObj = getSqlModel().getSingleResult(query);
			
			String accomQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, CADRE_NAME,SUM(LODGE_BOOKING_AMT)  "
				+ " FROM TMS_APPLICATION  " 
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "    
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "  
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			accomQuery += " AND TMS_APP_EMPDTL.APPL_CURRENCY ='"+bean.getCurrencyType()+"' GROUP BY EMP_FNAME||' '||EMP_LNAME, EMP_TOKEN, CADRE_NAME ORDER BY EMP_FNAME||' '||EMP_LNAME";
			Object[][] accomObj = getSqlModel().getSingleResult(accomQuery);
			
			String claimQuey = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME, CADRE_NAME,NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT)   " 
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) "
				+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			claimQuey += " AND TMS_EXP_DTL.EXP_DTL_CURRENCY ='"+bean.getCurrencyType()+"' GROUP BY EMP_FNAME||' '||EMP_LNAME, EXP_CATEGORY_NAME, EMP_TOKEN, CADRE_NAME ORDER BY EMP_FNAME||' '||EMP_LNAME ";
			Object[][] claimObj = getSqlModel().getSingleResult(claimQuey);
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
			rds.setFileName("Employeewise Cost Report");
			rds.setReportName("Employeewise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			
			String filters = "Cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
						
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Employeewise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			Object[][] journeyModeObj = null;
			Object[][] jrnAccomObj = null;
			Object[][] finalOBj = null;
			Set<String>empWise = new HashSet<String>();
			Set<String>claimWise = new HashSet<String>();
			int empLength = 0;
			int claimLength = 0;
			
			if(empwiseObj != null && empwiseObj.length > 0 ){
				for (int i = 0; i < empwiseObj.length; i++) {
					empWise.add(String.valueOf(empwiseObj[i][1]));
				}
				empLength = empWise.size();
				
				journeyModeObj = Utility.transverse(empwiseObj, new int[]{0, 1, 2}, 3, 4, "0", true, new String[]{"Applicant Id", "Applicant Name", "Grade"});
				
				for (int j = 0; j < journeyModeObj[0].length; j++) {
					System.out.println("Headers..."+String.valueOf(journeyModeObj[0][j]));
					if(String.valueOf(journeyModeObj[0][j]).trim().equals("")){
						journeyModeObj = Utility.removeColumns(journeyModeObj, new int[]{j});
						empLength = empLength -1;
					}
				}
				if(accomObj != null && accomObj.length > 0 ){
					jrnAccomObj = Utility.joinArrayWithUniqueCol(journeyModeObj, accomObj, 0, 0, new int[]{0, 1, 2});
				}else{
					jrnAccomObj = journeyModeObj;
				}
				if(claimObj != null && claimObj.length > 0 ){
					for (int i = 0; i < claimObj.length; i++) {
						claimWise.add(String.valueOf(claimObj[i][1]));
					}
					claimLength = claimWise.size();
					
					claimObj = Utility.transverse(claimObj, new int[]{0, 1, 2}, 3, 4, "0", true, new String[]{"Applicant Id", "Applicant Name", "Grade"});
					finalOBj = Utility.joinArrayWithUniqueCol(jrnAccomObj, claimObj, 0, 0, new int[]{0, 1, 2});
				}else{
					finalOBj = jrnAccomObj;
				}
				int cellAlignmentCount=0;
			if(finalOBj!=null && finalOBj.length>0){
				 cellAlignmentCount =finalOBj[0].length;
			}
				
				int[]cellAlignArray = new int [cellAlignmentCount];
				int[]cellWidthArray = new int [cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if(i==0){
						cellWidthArray[i]=40;
					}
					else{
						cellWidthArray[i] = 30;
					}
				}
			
				int[]rowSumArray = new int [empLength + claimLength +1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				/*for (int i = 0; i < finalOBj.length; i++) {
					for (int j = 0; j < finalOBj[0].length; j++) {
						System.out.println("finalOBj["+i+"]["+j+"]....."+finalOBj[i][j]);
					}
				}*/
				finalOBj[0][jrnAccomObj[0].length-1] = "Accommodation";
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(finalOBj);
				empwiseCostTable.setCellAlignment(cellAlignArray);
				empwiseCostTable.setCellWidth(cellWidthArray);
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				//empwiseCostTable.setRowSum(rowSumArray);
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
	
	public void generateDeptwiseCostReport(HttpServletResponse response,
			TrvlCostReport bean) {
		try {
			String query = "SELECT NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(JOURNEY_NAME,' '), SUM(TMS_BOOK_TRAVEL_COST) "
				+ " FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
				+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY DEPT_NAME, JOURNEY_NAME ORDER BY DEPT_NAME ";
			Object[][] deptwiseObj = getSqlModel().getSingleResult(query);
			
			String accomQuery = " SELECT NVL(DEPT_NAME,' ') AS DEPARTMENT, SUM(LODGE_BOOKING_AMT) "
				+ " FROM TMS_APPLICATION  " 
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "    
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			accomQuery += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY DEPT_NAME ORDER BY DEPT_NAME";
			Object[][] accomObj = getSqlModel().getSingleResult(accomQuery);
			
			String claimQuey = " SELECT NVL(DEPT_NAME,' ') AS DEPARTMENT, NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT) " 
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) "
				+ " WHERE APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			claimQuey += " AND TMS_EXP_DTL.EXP_DTL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY DEPT_NAME, EXP_CATEGORY_NAME ORDER BY DEPT_NAME ";
			Object[][] claimObj = getSqlModel().getSingleResult(claimQuey);
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
			rds.setFileName("Departmentwise Cost Report");
			rds.setReportName("Departmentwise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Departmentwise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			Object[][] journeyModeObj = null;
			Object[][] jrnAccomObj = null;
			Object[][] finalOBj = null;
			Set<String>empWise = new HashSet<String>();
			Set<String>claimWise = new HashSet<String>();
			int empLength = 0;
			int claimLength = 0;
			
			if(deptwiseObj != null && deptwiseObj.length > 0 ){
				for (int i = 0; i < deptwiseObj.length; i++) {
					empWise.add(String.valueOf(deptwiseObj[i][1]));
				}
				empLength = empWise.size();
				
				journeyModeObj = Utility.transverse(deptwiseObj, new int[]{0}, 1, 2, "0", true, new String[]{"Department Name"});
				for (int j = 0; j < journeyModeObj[0].length; j++) {
					System.out.println("Headers..."+String.valueOf(journeyModeObj[0][j]));
					if(String.valueOf(journeyModeObj[0][j]).trim().equals("")){
						journeyModeObj = Utility.removeColumns(journeyModeObj, new int[]{j});
						empLength = empLength -1;
					}
				}
				if(accomObj != null && accomObj.length > 0 ){
					jrnAccomObj = Utility.joinArrayWithUniqueCol(journeyModeObj, accomObj, 0, 0, new int[]{0});
				}else{
					jrnAccomObj = journeyModeObj;
				}
				if(claimObj != null && claimObj.length > 0 ){
					for (int i = 0; i < claimObj.length; i++) {
						claimWise.add(String.valueOf(claimObj[i][2]));
					}
					claimLength = claimWise.size();
					
					claimObj = Utility.transverse(claimObj, new int[]{0}, 1, 2, "0", true, new String[]{"Department Name"});
					finalOBj = Utility.joinArrayWithUniqueCol(jrnAccomObj, claimObj, 0, 0, new int[]{0});
				}else{
					finalOBj = jrnAccomObj;
				}
				int cellAlignmentCount = empLength + claimLength+2;
				int[]cellAlignArray = new int [cellAlignmentCount];
				int[]cellWidthArray = new int [cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if(i==0){
						cellWidthArray[i]=40;
					}
					else{
						cellWidthArray[i] = 30;
					}
				}
				
				int[]rowSumArray = new int [empLength + claimLength+1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				for (int i = 0; i < finalOBj.length; i++) {
					for (int j = 0; j < finalOBj[0].length; j++) {
						System.out.println("finalOBj["+i+"]["+j+"]....."+finalOBj[i][j]);
					}
				}
				finalOBj[0][empLength+1] = "Accommodation";
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(finalOBj);
				empwiseCostTable.setCellAlignment(cellAlignArray);
				empwiseCostTable.setCellWidth(cellWidthArray);
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				//empwiseCostTable.setRowSum(rowSumArray);
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
	
	public void generateZonewiseCostReport(HttpServletResponse response,
			TrvlCostReport bean) {
		try {
			String query = "SELECT NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', "
				+ " 'South','CE','Central'),' ') CT_ZONE, NVL(JOURNEY_NAME,' '), SUM(TMS_BOOK_TRAVEL_COST) "
				+ " FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
				+ " WHERE CENTER_ZONE IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY CENTER_ZONE, JOURNEY_NAME ORDER BY CENTER_ZONE  ";
			Object[][] zonewiseObj = getSqlModel().getSingleResult(query);
			
			String accomQuery = " SELECT NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', "
				+ " 'South','CE','Central'),' ') CT_ZONE, SUM(LODGE_BOOKING_AMT) "
				+ " FROM TMS_APPLICATION  " 
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " WHERE CENTER_ZONE IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			accomQuery += "  AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY CENTER_ZONE ORDER BY CENTER_ZONE ";
			Object[][] accomObj = getSqlModel().getSingleResult(accomQuery);
			
			String claimQuey = " SELECT NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', "
				+ " 'South','CE','Central'),' ') CT_ZONE "
				+ " , NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT) "
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) "
				+ " WHERE CENTER_ZONE IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			claimQuey += "  AND TMS_EXP_DTL.EXP_DTL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY CENTER_ZONE, EXP_CATEGORY_NAME ORDER BY CENTER_ZONE   ";
			Object[][] claimObj = getSqlModel().getSingleResult(claimQuey);
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
			rds.setFileName("Zonewise Cost Report");
			rds.setReportName("Zonewise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Zonewise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			Object[][] journeyModeObj = null;
			Object[][] jrnAccomObj = null;
			Object[][] finalOBj = null;
			Set<String>empWise = new HashSet<String>();
			Set<String>claimWise = new HashSet<String>();
			int empLength = 0;
			int claimLength = 0;
			
			if(zonewiseObj != null && zonewiseObj.length > 0 ){
				for (int i = 0; i < zonewiseObj.length; i++) {
					empWise.add(String.valueOf(zonewiseObj[i][1]));
				}
				empLength = empWise.size();
				
				journeyModeObj = Utility.transverse(zonewiseObj, new int[]{0}, 1, 2, "0", true, new String[]{"Zone"});
				for (int j = 0; j < journeyModeObj[0].length; j++) {
					System.out.println("Headers..."+String.valueOf(journeyModeObj[0][j]));
					if(String.valueOf(journeyModeObj[0][j]).trim().equals("")){
						journeyModeObj = Utility.removeColumns(journeyModeObj, new int[]{j});
						empLength = empLength -1;
					}
				}
				if(accomObj != null && accomObj.length > 0 ){
					jrnAccomObj = Utility.joinArrayWithUniqueCol(journeyModeObj, accomObj, 0, 0, new int[]{0});
				}else{
					jrnAccomObj = journeyModeObj;
				}
				if(claimObj != null && claimObj.length > 0 ){
					for (int i = 0; i < claimObj.length; i++) {
						claimWise.add(String.valueOf(claimObj[i][2]));
					}
					claimLength = claimWise.size();
					
					claimObj = Utility.transverse(claimObj, new int[]{0}, 1, 2, "0", true, new String[]{"Zone"});
					finalOBj = Utility.joinArrayWithUniqueCol(jrnAccomObj, claimObj, 0, 0, new int[]{0});
				}else{
					finalOBj = jrnAccomObj;
				}
			
				int cellAlignmentCount =0;
				if(finalOBj!=null && finalOBj.length>0){
					cellAlignmentCount=finalOBj[0].length;
				}
				int[]cellAlignArray = new int [cellAlignmentCount];
				int[]cellWidthArray = new int [cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if(i==0){
						cellWidthArray[i]=40;
					}
					else{
						cellWidthArray[i] = 30;
					}
				}
				
				int[]rowSumArray = new int [empLength + claimLength+1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				/*for (int i = 0; i < finalOBj.length; i++) {
					for (int j = 0; j < finalOBj[0].length; j++) {
						System.out.println("finalOBj["+i+"]["+j+"]....."+finalOBj[i][j]);
					}
				}*/
				finalOBj[0][empLength+1] = "Accommodation";
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(finalOBj);
				empwiseCostTable.setCellAlignment(cellAlignArray);
				empwiseCostTable.setCellWidth(cellWidthArray);
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				//empwiseCostTable.setRowSum(rowSumArray);
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
	
	public void generateProjectwiseCostReport(HttpServletResponse response,
			TrvlCostReport bean) {
		try {
			String query = "SELECT NVL(PROJECT_NAME,' ') PROJECT, NVL(JOURNEY_NAME,' '), SUM(TMS_BOOK_TRAVEL_COST) "
				+ " FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
				+ " WHERE TOUR_PROJECT IS NOT NULL AND APPL_STATUS IN ('C')  ";
			if(!bean.getFromDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			query += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY PROJECT_NAME, JOURNEY_NAME ORDER BY PROJECT_NAME ";
			Object[][] projwiseObj = getSqlModel().getSingleResult(query);
			
			System.out.println("projwiseObj length..... "+projwiseObj.length);
			
			String accomQuery = " SELECT NVL(PROJECT_NAME,' ') AS PROJECT, SUM(LODGE_BOOKING_AMT) "
				+ " FROM TMS_APPLICATION  " 
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
				+ " WHERE TOUR_PROJECT IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			accomQuery += "  AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY PROJECT_NAME ORDER BY PROJECT_NAME ";
			Object[][] accomObj = getSqlModel().getSingleResult(accomQuery);
			
			String claimQuey = " SELECT NVL(PROJECT_NAME,' ') PROJECT, NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT) "
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) "
				+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
				+ " WHERE TOUR_PROJECT IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			claimQuey += "  AND TMS_EXP_DTL.EXP_DTL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY PROJECT_NAME, EXP_CATEGORY_NAME ORDER BY PROJECT_NAME ";
			Object[][] claimObj = getSqlModel().getSingleResult(claimQuey);
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
			rds.setFileName("Projectwise Cost Report");
			rds.setReportName("Projectwise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Projectwise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			Object[][] journeyModeObj = null;
			Object[][] jrnAccomObj = null;
			Object[][] finalOBj = null;
			Set<String>empWise = new HashSet<String>();
			Set<String>claimWise = new HashSet<String>();
			int empLength = 0;
			int claimLength = 0;
			
			if(projwiseObj != null && projwiseObj.length > 0 ){
				for (int i = 0; i < projwiseObj.length; i++) {
					empWise.add(String.valueOf(projwiseObj[i][1]));
				}
				empLength = empWise.size();
				
				journeyModeObj = Utility.transverse(projwiseObj, new int[]{0}, 1, 2, "0", true, new String[]{"Project"});
				for (int j = 0; j < journeyModeObj[0].length; j++) {
					System.out.println("Headers..."+String.valueOf(journeyModeObj[0][j]));
					if(String.valueOf(journeyModeObj[0][j]).trim().equals("")){
						journeyModeObj = Utility.removeColumns(journeyModeObj, new int[]{j});
						empLength = empLength -1;
					}
				}
				if(accomObj != null && accomObj.length > 0 ){
					jrnAccomObj = Utility.joinArrayWithUniqueCol(journeyModeObj, accomObj, 0, 0, new int[]{0});
				}else{
					jrnAccomObj = journeyModeObj;
				}
				if(claimObj != null && claimObj.length > 0 ){
					for (int i = 0; i < claimObj.length; i++) {
						claimWise.add(String.valueOf(claimObj[i][2]));
					}
					claimLength = claimWise.size();
					
					claimObj = Utility.transverse(claimObj, new int[]{0}, 1, 2, "0", true, new String[]{"Project"});
					finalOBj = Utility.joinArrayWithUniqueCol(jrnAccomObj, claimObj, 0, 0, new int[]{0});
				}else{
					finalOBj = jrnAccomObj;
				}
				
				int cellAlignmentCount = 0;
				if(finalOBj!=null && finalOBj.length>0){
					cellAlignmentCount=finalOBj[0].length;
				}
				int[]cellAlignArray = new int [cellAlignmentCount];
				int[]cellWidthArray = new int [cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if(i==0){
						cellWidthArray[i]=40;
					}
					else{
						cellWidthArray[i] = 30;
					}
				}
				
				int[]rowSumArray = new int [empLength + claimLength+1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				/*for (int i = 0; i < finalOBj.length; i++) {
					for (int j = 0; j < finalOBj[0].length; j++) {
						System.out.println("finalOBj["+i+"]["+j+"]....."+finalOBj[i][j]);
					}
				}*/
				finalOBj[0][empLength+1] = "Accommodation";
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(finalOBj);
				empwiseCostTable.setCellAlignment(cellAlignArray);
				empwiseCostTable.setCellWidth(cellWidthArray);
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				//empwiseCostTable.setRowSum(rowSumArray);
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
	
	public void generateCustomerwiseCostReport(HttpServletResponse response,
			TrvlCostReport bean) {
		try {
			String query = "SELECT NVL(TRAVEL_CUST_NAME,' ') CUSTOMER, NVL(JOURNEY_NAME,' '), SUM(TMS_BOOK_TRAVEL_COST) "
				+ " FROM TMS_APPLICATION "
				+ " LEFT JOIN TMS_BOOK_TRAVEL ON (TMS_BOOK_TRAVEL.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID = TMS_APPLICATION.TOUR_CUSTOMER  "
				+ " AND TMS_TRAVEL_CUSTOMER.TRAVEL_PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID =   HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  "
				+ " WHERE TOUR_CUSTOMER IS NOT NULL AND APPL_STATUS IN ('C')  ";
			if(!bean.getFromDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				query += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			query += "  AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY TRAVEL_CUST_NAME, JOURNEY_NAME ORDER BY TRAVEL_CUST_NAME ";
			Object[][] custwiseObj = getSqlModel().getSingleResult(query);
			
			String accomQuery = " SELECT NVL(TRAVEL_CUST_NAME,' ') AS CUSTOMER, SUM(LODGE_BOOKING_AMT) "
				+ " FROM TMS_APPLICATION  " 
				+ " LEFT JOIN TMS_BOOK_LODGE ON (TMS_BOOK_LODGE.TRAVEL_APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID = TMS_APPLICATION.TOUR_CUSTOMER " 
				+ " AND TMS_TRAVEL_CUSTOMER.TRAVEL_PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
				+ " WHERE TOUR_CUSTOMER IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				accomQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			accomQuery += " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"'  GROUP BY TRAVEL_CUST_NAME ORDER BY TRAVEL_CUST_NAME ";
			Object[][] accomObj = getSqlModel().getSingleResult(accomQuery);
			
			String claimQuey = " SELECT NVL(TRAVEL_CUST_NAME,' ') CUSTOMER, NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT) "
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) "
				+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID = TMS_APPLICATION.TOUR_CUSTOMER) "
				+ " WHERE TOUR_CUSTOMER IS NOT NULL AND APPL_STATUS IN ('C') ";
			if(!bean.getFromDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
			}
			if(!bean.getToDate().equals("")){
				claimQuey += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
			}
			claimQuey += "  AND TMS_EXP_DTL.EXP_DTL_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY TRAVEL_CUST_NAME, EXP_CATEGORY_NAME ORDER BY TRAVEL_CUST_NAME ";
			Object[][] claimObj = getSqlModel().getSingleResult(claimQuey);
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
			rds.setFileName("Customerwise Cost Report");
			rds.setReportName("Customerwise Cost Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			
			String filters = "Cost report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			
			TableDataSet titleName = new TableDataSet();
			titleName
					.setData(new Object[][] { { "Customerwise Cost Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);
			Object[][] journeyModeObj = null;
			Object[][] jrnAccomObj = null;
			Object[][] finalOBj = null;
			Set<String>empWise = new HashSet<String>();
			Set<String>claimWise = new HashSet<String>();
			int empLength = 0;
			int claimLength = 0;
			
			if(custwiseObj != null && custwiseObj.length > 0 ){
				for (int i = 0; i < custwiseObj.length; i++) {
					empWise.add(String.valueOf(custwiseObj[i][1]));
				}
				empLength = empWise.size();
				
				journeyModeObj = Utility.transverse(custwiseObj, new int[]{0}, 1, 2, "0", true, new String[]{"Customer"});
				for (int j = 0; j < journeyModeObj[0].length; j++) {
					System.out.println("Headers..."+String.valueOf(journeyModeObj[0][j]));
					if(String.valueOf(journeyModeObj[0][j]).trim().equals("")){
						journeyModeObj = Utility.removeColumns(journeyModeObj, new int[]{j});
						empLength = empLength -1;
					}
				}
				if(accomObj != null && accomObj.length > 0 ){
					jrnAccomObj = Utility.joinArrayWithUniqueCol(journeyModeObj, accomObj, 0, 0, new int[]{0});
				}else{
					jrnAccomObj = journeyModeObj;
				}
				if(claimObj != null && claimObj.length > 0 ){
					for (int i = 0; i < claimObj.length; i++) {
						claimWise.add(String.valueOf(claimObj[i][2]));
					}
					claimLength = claimWise.size();
					
					claimObj = Utility.transverse(claimObj, new int[]{0}, 1, 2, "0", true, new String[]{"Customer"});
					finalOBj = Utility.joinArrayWithUniqueCol(jrnAccomObj, claimObj, 0, 0, new int[]{0});
				}else{
					finalOBj = jrnAccomObj;
				}
				int cellAlignmentCount =0;
				if(finalOBj!=null && finalOBj.length>0){
					 cellAlignmentCount =finalOBj[0].length;
				}
				
				int[]cellAlignArray = new int [cellAlignmentCount];
				int[]cellWidthArray = new int [cellAlignmentCount];
				for (int i = 0; i < cellAlignmentCount; i++) {
					cellAlignArray[i] = 0;
					if(i==0){
						cellWidthArray[i]=40;
					}
					else{
						cellWidthArray[i] = 30;
					}
				}
				
				int[]rowSumArray = new int [empLength + claimLength+1];
				int count = 1;
				for (int i = 0; i < rowSumArray.length; i++) {
					rowSumArray[i] = count;
					count++;
				}
				/*for (int i = 0; i < finalOBj.length; i++) {
					for (int j = 0; j < finalOBj[0].length; j++) {
						System.out.println("finalOBj["+i+"]["+j+"]....."+finalOBj[i][j]);
					}
				}*/
				finalOBj[0][empLength+1] = "Accommodation";
				TableDataSet empwiseCostTable = new TableDataSet();
				empwiseCostTable.setData(finalOBj);
				empwiseCostTable.setCellAlignment(cellAlignArray);
				empwiseCostTable.setCellWidth(cellWidthArray);
				empwiseCostTable.setBorder(true);
				empwiseCostTable.setHeaderTable(false);
				//empwiseCostTable.setRowSum(rowSumArray);
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
			TrvlCostReport report) {
		boolean result = false;
		String quer = " SELECT CURRENCY_ID,CURRENCY_ABBR FROM HRMS_CURRENCY ORDER BY CURRENCY_ID ";
	
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
