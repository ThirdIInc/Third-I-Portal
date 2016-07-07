package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.AdvanceClaimDisbursementReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class AdvanceClaimDisbursementReportModel extends ModelBase {

	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void generateReport(AdvanceClaimDisbursementReportBean bean,
			HttpServletResponse response) {

		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}

			rds.setReportType(reportType);
			rds.setFileName("Travel Advance Claim Disbursement");
			rds.setReportName("Travel Advance Claim Disbursement");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet subtitleName = new TableDataSet();
		
			Object obj[][] = new Object[1][1];
			obj[0][0] = "Travel advance claim disbursement report";
			subtitleName.setData(obj);

			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);
			
			String filters = "Travel advance claim disbursement report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			String advQuery = " SELECT ROWNUM,  EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'     '||EMP_LNAME,"
					+ " NVL(SAL_ACCNO_REGULAR,' '), BANK_NAME, BANK_IFSC_CODE,BANK_MICR_CODE, ADV_DISB_ADVANCE_AMT,TMS_ADV_DISB_BAL.ADV_DISB_CMTS"
					+ " FROM TMS_ADV_DISBURSEMENT"
					+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_ADV_DISBURSEMENT.TRVL_APPID) "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " LEFT JOIN TMS_ADV_DISB_BAL ON (TMS_ADV_DISB_BAL.ADV_DISB_ID = TMS_ADV_DISBURSEMENT.ADV_DISB_ID) " 
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) " 
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID = TMS_APPLICATION.TOUR_CUSTOMER) " 
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID= APPL_EMP_CODE) "
					+ " LEFT JOIN HRMS_BANK  ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR =HRMS_BANK.BANK_MICR_CODE) " 
					+ " WHERE ADV_DISB_DATE >= TO_DATE('"
					+ bean.getFromDate() + "','DD-MM-YYYY')"
					+ " AND ADV_DISB_DATE <= TO_DATE('"
					+ bean.getToDate() + "','DD-MM-YYYY') AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' ";

			if (!bean.getPaymentMode().trim().equals("")) {
				advQuery += "  AND ADV_DISB_PAYMODE='" + bean.getPaymentMode()
						+ "'";
			}

			Object[][] advanceData = getSqlModel().getSingleResult(advQuery);
			if (advanceData != null && advanceData.length > 0) {
				TableDataSet headerTableAdv = new TableDataSet();
				headerTableAdv
						.setData(new Object[][] { { "ADVANCE CLAIM DISBURSEMENT DETAILS" } });
				headerTableAdv.setCellAlignment(new int[] { 0 });
				headerTableAdv.setCellWidth(new int[] { 100 });
				headerTableAdv.setBlankRowsAbove(1);
				headerTableAdv.setBlankRowsBelow(1);
				headerTableAdv.setBorder(false);
				rg.addTableToDoc(headerTableAdv);

				String[] header = { "Sr.No", "Employee ID", "Employee",
						"Account Number", "Bank Name", "IFSC Code",
						"MICR Code", "Amount", "Comments" };
				int[] alignment = { 1, 0, 0, 1, 2, 0, 0, 1, 0 };
				int[] cellwidth = { 5, 15, 25, 15, 15, 20, 15, 15, 15 };
				TableDataSet advancetable = new TableDataSet();
				advancetable.setData(advanceData);
				advancetable.setHeader(header);
				advancetable.setCellAlignment(alignment);
				advancetable.setCellWidth(cellwidth);
				advancetable.setHeaderBGColor(new Color(192, 192, 192));
				advancetable.setBorder(true);
				advancetable.setColumnSum(new int[] { 7 });
				advancetable.setBlankRowsBelow(1);
				advancetable.setHeaderTable(false);
				rg.addTableToDoc(advancetable);
				rg.addProperty(rg.PAGE_BREAK);
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
			AdvanceClaimDisbursementReportBean report) {
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
