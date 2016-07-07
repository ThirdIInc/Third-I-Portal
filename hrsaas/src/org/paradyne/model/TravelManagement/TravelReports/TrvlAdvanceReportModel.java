package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAdvanceReport;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlHotelwiseCostReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class TrvlAdvanceReportModel extends ModelBase {

	public void generateReport(TrvlAdvanceReport bean, HttpServletResponse response) {
		
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
			rds.setFileName("Travel Advance Report");
			rds.setReportName("Travel Advance Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			/*TableDataSet subtitleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][2];
			obj[0][0] = "Travel advance report for the";
			obj[0][1] = "period "+bean.getFromDate()+" to "+bean.getToDate();
			subtitleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Travel advance report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				subtitleName.setData(obj);
			}
			
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);*/
			
			String filters = "Travel advance report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			String advanceQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||'  '||EMP_LNAME, " 
					 + " NVL(DEPT_NAME,' ') AS DEPARTMENT,NVL(CENTER_NAME,' ') AS "
					 + " LOC,NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', " 
					 + " 'South','CE','Central'),' ') CT_ZONE , TO_CHAR(APPL_DATE,'DD-MM-YYYY') APPLDATE, " 
					 + " TO_CHAR(ADV_DISB_DATE,'DD-MM-YYYY') DISBDATE, "
					 + " TMS_APP_EMPDTL.APPL_APPROVED_ADVANCE_AMOUNT ADV, DECODE(ADV_DISB_STATUS,'C','Disbursed','Pending')  "
					 + " FROM TMS_APP_EMPDTL "
					 + " LEFT JOIN TMS_ADV_DISBURSEMENT  ON (TMS_APP_EMPDTL.APPL_ID = TMS_ADV_DISBURSEMENT.TRVL_APPID AND " 
					 + " TMS_APP_EMPDTL.APPL_CODE = TMS_ADV_DISBURSEMENT.TRVL_APPCODE) "
					 + " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = APPL_EMP_CODE) "
					 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
					 + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " 
					 + " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) " 
					 + " WHERE TMS_APP_EMPDTL.APPL_APPROVED_ADVANCE_AMOUNT > 0 AND ADV_DISB_STATUS IN ('C', 'T') "
					 + " AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' ORDER BY TMS_APP_EMPDTL.APPL_APPROVED_ADVANCE_AMOUNT DESC ";
					// + " WHERE 1 = 1 ";
			Object[][] advanceData = getSqlModel()
					.getSingleResult(advanceQuery);
			if (advanceData != null && advanceData.length > 0) {
				System.out.println("advanceData.length ====== "
						+ advanceData.length);
				String[] header = {"Applicant ID", "Name of Applicant", "Department",
						"Location", "Zone", "Application Date",
						"Disburse Date", "Advance Amount", "Status" };
				int[] alignment = {0, 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] cellwidth = { 10,10, 15, 25, 25, 20, 15, 20, 20 };
				TableDataSet advancetable = new TableDataSet();
				advancetable.setData(advanceData);
				advancetable.setHeader(header);
				advancetable.setCellAlignment(alignment);
				advancetable.setCellWidth(cellwidth);
				advancetable.setHeaderBGColor(new Color(192, 192, 192));
				advancetable.setBorder(true);
				advancetable.setHeaderTable(false);
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
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean setCurrencyList(HttpServletResponse response,
			TrvlAdvanceReport report) {
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
