//START BY VIJAY SONAWANE
package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.PromotionHistoryMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author AA0563
 *
 */
public class PromotionHistoryMisReportModel extends ModelBase {
	
	
	
	public void getReport2(PromotionHistoryMisReport promotion,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = promotion.getReport();
		rds.setReportType(type);
		String fileName = "PromotionMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Promotion History MIS Report");
		rds.setTotalColumns(14);
		rds.setShowPageNo(true);
		rds.setGeneratedByText(promotion.getUserEmpId());
		rds.setUserEmpId(promotion.getUserEmpId());
		rds.setPageOrientation("landscape");

		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "PromotionMisReport_input.action");
			
		}

		String filter = "";

		if (!promotion.getDivsion().equals("")) {
			filter += "Division: " + promotion.getDivsion();
		}
		if (!promotion.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + promotion.getDeptName();
		}
		if (!promotion.getCenterName().equals("")) {
			filter += "\n\nBranch: " + promotion.getCenterName();
		}

		if (!promotion.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + promotion.getDesgName();
		}
		if (!promotion.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + promotion.getEmpName();
		}
		if (!promotion.getStatus().equals("-1")) {
			filter += "\n\nEmployee Status: " ;
			
				if(promotion.getStatus().equals("S"))
				{
					filter+="Service";
				}else if(promotion.getStatus().equals("E"))
				{
					filter+="Terminated";
				}else if(promotion.getStatus().equals("R"))
				{
					filter+="Retired";
				}else
					filter+="Resigned";
				
		}
		

		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 14 });
		filterData.setCellNoWrap(new boolean[] { true });
		filterData.setBlankRowsBelow(1);
		rg.addTableToDoc(filterData);

		String addressQuery = "";
		addressQuery = " SELECT  DISTINCT(ROWNUM),HRMS_EMP_OFFC.EMP_TOKEN ,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " HRMS_RANK.RANK_NAME,DIV_NAME,DEPT_NAME,HRMS_CENTER.CENTER_NAME,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','E','Terminated','N','Resigned'), "
				+ " HRMS_PROMO_HISTORY.PROMO_ID,"
				//+ " COMMON_RANK.RANK_NAME,"
				//+ " COMMON_DEPT.DEPT_NAME,"
				//+ " COMMON_CENTER.CENTER_NAME,"
				+ " (SELECT HRMS_RANK.RANK_NAME FROM HRMS_RANK WHERE RANK_ID=HRMS_PROMO_HISTORY.PROMO_POST)," 
				+ " (SELECT HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID=HRMS_PROMO_HISTORY.PROMO_DEPT),"
				+ " (SELECT HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_ID=HRMS_PROMO_HISTORY.PROMO_BRANCH),"
				+ " HRMS_PROMO_HISTORY.PROMO_CTC,"
				+ "TO_CHAR(HRMS_PROMO_HISTORY.PROMO_FROM_DT,'DD-MM-YYYY')"
				
				+ "	FROM HRMS_PROMO_HISTORY "
				//+ " INNER JOIN HRMS_RANK COMMON_RANK ON COMMON_RANK.RANK_ID=HRMS_PROMO_HISTORY.PROMO_POST"
				//+ " INNER JOIN HRMS_DEPT COMMON_DEPT ON COMMON_DEPT.DEPT_ID=HRMS_PROMO_HISTORY.PROMO_DEPT"
				//+ " INNER JOIN HRMS_CENTER COMMON_CENTER ON COMMON_CENTER.CENTER_ID=HRMS_PROMO_HISTORY.PROMO_BRANCH"
				+ "	INNER JOIN HRMS_EMP_OFFC ON HRMS_PROMO_HISTORY.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV  "
				+ "	LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
				+ " WHERE 1<2 ";
	
		if (promotion.getDeptCode() != null
				&& !(promotion.getDeptCode().equals(""))
				&& promotion.getDeptCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN  ("
					+ promotion.getDeptCode() + ")";

		}
		if (promotion.getCenterId() != null
				&& !(promotion.getCenterId().equals(""))
				&& promotion.getCenterId().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ promotion.getCenterId() + ")";

		}
		if (promotion.getDivCode() != null && (!promotion.getDivCode().equals(""))
				&& promotion.getDivCode().length() > 0) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN  ("
					+ promotion.getDivCode() + ")";
		}
		if (promotion.getDesgCode() != null
				&& (!promotion.getDesgCode().equals(""))
				&& promotion.getDesgCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN  ("
					+ promotion.getDesgCode() + ")";
		}
		if (promotion.getStatus() != null && !(promotion.getStatus().equals("-1"))
				&& promotion.getStatus().length() > 0) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ promotion.getStatus() + "')";
			System.out.println("StatusCode =" + promotion.getStatus());
		}
		if (promotion.getEmpName() != null && !(promotion.getEmpName().equals(""))) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_ID ='" + promotion.getEmpid()
					+ "'";
		}

		TableDataSet hdrtable = new TableDataSet();
		hdrtable.setHeader(new String[]{"Sr. No.","Employee Id","Name",
				"Designation","Divsion","Department","Branch","Status",
				"Promotion Id", "Promotion Designation Name", "Department",
				"Branch", "CTC", "Promotion Date" });
		
		hdrtable.setHeaderTable(true);
		hdrtable.setHeaderBorderDetail(3);
		hdrtable.setCellAlignment(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
				0, 0, 2, 1 });
		hdrtable.setCellWidth(new int[] { 5, 5, 10, 8, 8, 8, 8, 5, 5, 10,
				8, 5, 5, 10 });
		hdrtable.setBorderDetail(3);
		hdrtable.setBorderLines(true);
		
		
		Object[][] qData = getSqlModel().getSingleResult(addressQuery);
		int totalEmployees = 0;
		totalEmployees = qData.length;
		
		if (qData == null || qData.length == 0) {
			
			hdrtable.setBlankRowsAbove(1);
			rg.addTableToDoc(hdrtable);
		
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		} else {
			
			hdrtable.setData(qData);
			rg.addTableToDoc(hdrtable);
			
			TableDataSet totalEmp = new TableDataSet();
			totalEmp.setData(new Object[][] { { "Total Employees : "
					+ totalEmployees } });
			totalEmp.setCellAlignment(new int[] { 0 });
			totalEmp.setCellWidth(new int[] { 100 });
			totalEmp.setBorderDetail(0);
			totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
			totalEmp.setBodyFontStyle(1);
			totalEmp.setBlankRowsAbove(1);
			rg.addTableToDoc(totalEmp);
			/*
			 * TableDataSet tableData = new TableDataSet();
					tableData.setHeader(headers);
					tableData.setHeaderTable(true);
					tableData.setHeaderFontStyle(1);
					tableData.setData(finalDataObj);
					tableData.setColumnSum(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
					tableData.setCellAlignment(cellAlignment);
					tableData.setCellWidth(cellWidth);
					tableData.setBorderDetail(3);
					tableData.setCellNoWrap(cellWrap);
					rg.addTableToDoc(tableData);
			 */
		}
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			rg.saveReport(response);
		}

	}
}




