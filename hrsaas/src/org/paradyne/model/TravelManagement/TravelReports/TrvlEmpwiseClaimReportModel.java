package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlEmpwiseClaimReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0623
 * 
 */

public class TrvlEmpwiseClaimReportModel extends ModelBase {
	public void generateEmpwiseClaimReport(
			HttpServletResponse response, TrvlEmpwiseClaimReport bean) {
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
			rds.setFileName("Employeewise Claim Report");
			rds.setReportName("Employeewise Claim Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			/*TableDataSet titleName = new TableDataSet();
			
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][3];
			obj[0][0] = "Employeewise claim report for";
			obj[0][1] = " the period  "+bean.getFromDate()+" to ";
			obj[0][2] = bean.getToDate();
			titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Employeewise claim report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName.setData(obj);
			}
			
			titleName.setBlankRowsBelow(obj.length);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);*/
			
			String filters = "Employeewise claim report for the period "+bean.getFromDate()+" to "+bean.getToDate();
			
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
			
			String query = "SELECT DISTINCT TMS_CLAIM_APPL.EXP_TRVL_ID,EMP_TOKEN , EMP_FNAME||' '||EMP_LNAME APPLICANT,  "
				+ " TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY'), TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY'),  "
				+ " NVL(EXP_CATEGORY_NAME,' '), SUM(EXP_DTL_EXP_AMT) "
				+ " FROM TMS_CLAIM_APPL "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID)  "
				+ " INNER JOIN TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID = TMS_CLAIM_APPL.EXP_APPID) "
				+ " INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID = TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_CLAIM_APPL.EXP_TRVL_APPID) ";
				//+ " INNER JOIN TMS_EXP_DISBURSEMENT ON (TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID = TMS_CLAIM_APPL.EXP_APPID) "
				//+ " WHERE EXP_DISB_STATUS IN ('T','C') "; 
				if(bean.getFromDate()!=null && !bean.getFromDate().equals("")&& bean.getToDate()!=null && !bean.getToDate().equals("")){
					query += " WHERE   EXP_APP_DATE >= TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') " 
					+" AND EXP_APP_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')";
				}
				query += " AND TMS_CLAIM_APPL.EXP_CLAIM_CURRENCY='"+bean.getCurrencyType()+"' GROUP BY TMS_CLAIM_APPL.EXP_TRVL_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME, EXP_TRVL_START_DATE, EXP_APP_DATE, EXP_CATEGORY_NAME ";

		Object[][] empwiseObj = getSqlModel().getSingleResult(query);
		
			Set<String> empWise = new HashSet<String>();
			if (empwiseObj != null && empwiseObj.length > 0) {
				for (int i = 0; i < empwiseObj.length; i++) {
					empWise.add(String.valueOf(empwiseObj[i][1]));
				}
				empwiseObj = Utility
						.transverse(empwiseObj, new int[] { 0, 1, 2,3,4 }, 5, 6,
								"0", true, new String[] { "Travel Id","Employee Id","Applicant Name",
										"Travel Date", "Claim Date" });
				int cellAlignmentCount =0;
				if(empwiseObj!=null && empwiseObj.length>0){
					cellAlignmentCount = empwiseObj[0].length;
				}
				//int cellAlignmentCount = empWise.size() + 2;
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
				empwiseCostTable.setData(empwiseObj);
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
			TrvlEmpwiseClaimReport report) {
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
