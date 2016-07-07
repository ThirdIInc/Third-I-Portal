/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.NomineeMisReport;
import org.paradyne.bean.payroll.MonthlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.MonthlyEDSummaryReportModel;

import com.itextpdf.text.BaseColor;

/**
 * @author AA0563
 * 
 */
public class NomineeMisReportModel extends ModelBase {

	public String getReport(NomineeMisReport nominee,
			HttpServletResponse response) {

		String reportName = "Nominee MIS Report";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				nominee.getReportType(), reportName);
		rg.addTextBold(" Nominee MIS Report", 0, 1, 0);

		String query = " SELECT  DISTINCT TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_TOKEN,"
				+ " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_ID,DIV_NAME,DEPT_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_EMP_NOMINEE ON HRMS_EMP_NOMINEE.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV  "
				+ "	LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
				+ " WHERE 1<2  ";

		if (!(nominee.getEmpid().equals("")) && !(nominee.getEmpid() == null)
				&& !nominee.getEmpid().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_ID =" + nominee.getEmpid();
		}

		if (!(nominee.getCenterId().equals(""))
				&& !(nominee.getCenterId() == null)
				&& !nominee.getCenterId().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=" + nominee.getCenterId()
					+ " ";
		}

		if (!(nominee.getDivCode().equals(""))
				&& !(nominee.getDivCode() == null)
				&& !nominee.getDivCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=" + nominee.getDivCode() + " ";
		}

		if (!(nominee.getDesgCode().equals(""))
				&& !(nominee.getDesgCode() == null)
				&& !nominee.getDesgCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK=" + nominee.getDesgCode()
					+ " ";
		}
		if (!(nominee.getDeptCode().equals(""))
				&& !(nominee.getDeptCode() == null)
				&& !nominee.getDeptCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=" + nominee.getDeptCode()
					+ " ";
		}
		if (!(nominee.getStatus().equals("-1"))) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='" + nominee.getStatus()
					+ "'";
		}
		query += "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		Object empData[][] = getSqlModel().getSingleResult(query);

		// TODO Auto-generated method stub

		String relquery = "";
		int[] attCellWidth = { 20, 20, 20, 20 };
		int[] attAlign = { 1, 1, 1, 1 };
		String[] attCol = { "Nominee Name", "Type", "Fraction", "Date" };

		Object[][] employee = new Object[3][7];
		if (empData != null && empData.length > 0) {
			for (int i = 0; i < empData.length; i++) {

				relquery = "SELECT NVL(FML_FNAME ||' '||FML_MNAME || ' '||FML_LNAME,' '),"

						+ " DECODE(NOM_TYPE,'G','GRATUITY','F','PF','P','PENSION','S','ESIC',' '), "
						+ " NVL(NOM_SHARE,0), "
						+ " TO_CHAR(NOM_DATE,'DD-MM-YYYY'),EMP_ID FROM HRMS_EMP_NOMINEE  LEFT JOIN HRMS_EMP_OFFC "
						+ " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_NOMINEE.EMP_ID) INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
						+ " LEFT JOIN HRMS_EMP_FML ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE)"
						+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
						+ " WHERE HRMS_EMP_NOMINEE.EMP_ID="
						+ String.valueOf(empData[i][4]);
				Object emprelativesData[][] = getSqlModel().getSingleResult(
						relquery);
				if (!(emprelativesData == null || emprelativesData.length == 0)) {
					employee[0][0] = "Employee Id";
					employee[0][1] = ":";
					employee[0][2] = "" + empData[i][1];
					employee[0][3] = "";
					employee[0][4] = "Employee Name";
					employee[0][5] = ":";
					employee[0][6] = "" + empData[i][0];
					employee[1][0] = "Designation";
					employee[1][1] = ":";
					employee[1][2] = "" + empData[i][2];
					employee[1][3] = "";
					employee[1][4] = "Department";
					employee[1][5] = ":";
					employee[1][6] = "" + empData[i][6];
					employee[2][0] = "Branch";
					employee[2][1] = ":";
					employee[2][2] = "" + empData[i][3];
					employee[2][3] = "";
					employee[2][4] = "Division";
					employee[1][5] = ":";
					employee[2][6] = "" + empData[i][5];
					int[] cellwidth = { 8, 2, 10, 8, 8, 2, 15 };
					int[] alignment = { 0, 0, 0, 0, 0, 0, 0 };
					rg.setFName("NomineeMISReport");
					rg.addText("\n", 0, 0, 0);
					rg.addText("\n", 0, 0, 0);

					if (nominee.getReportType().equals("Xls")) {
						rg.tableBodyNoCellBorder(employee, cellwidth,
								alignment, 0);
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);
						rg.addText("\n\n", 0, 0, 0);

					}
					if (nominee.getReportType().equals("Pdf")) {
						rg.tableBodyNoCellBorder(employee, cellwidth,
								alignment, 0);
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);

					}
					if (nominee.getReportType().equals("Txt")) {
						rg.tableBody(employee, cellwidth, alignment);
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);

					}

				}
			}
		} else {
			rg.addFormatedText("    ", 0, 0, 1, 0);
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}
		rg.createReport(response);

		return null;
	}



	// ----------------------------BY VIJAY SONAWANE
	// START------------------------------------

	public void getReport2(NomineeMisReport nominee,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = nominee.getReport();
		rds.setReportType(type);
		String fileName = "NomineeMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setTotalColumns(14);
		rds.setReportName("Nominee MIS Report");
		rds.setUserEmpId(nominee.getUserEmpId());
		//rds.setPageSize("A4");
		rds.setShowPageNo(true);
		rds.setGeneratedByText(nominee.getUserEmpId());
		rds.setPageOrientation("landscape");		
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		// Attachment Path Definition
		if (reportPath.equals("")) {
			rg = new ReportGenerator(rds, session, context, request);
		} else {
			rg = new ReportGenerator(rds, reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);			
			request.setAttribute("action", "/srd/NomineeReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action
		}

		String filter = "";

		if (!nominee.getDivsion().equals("")) {
			filter += "Division: " + nominee.getDivsion();
		}
		if (!nominee.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + nominee.getDeptName();
		}
		if (!nominee.getCenterName().equals("")) {
			filter += "\n\nBranch: " + nominee.getCenterName();
		}

		if (!nominee.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + nominee.getDesgName();
		}
		if (!nominee.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + nominee.getEmpName();
		}
		
		String status="";
		if (!nominee.getStatus().equals("-1")) {
			
			if(nominee.getStatus().equals("S")){
				status="Service";
			}else if(nominee.getStatus().equals("R")){
				status="Retired";
			}else if(nominee.getStatus().equals("N")){
				status="Resigned";
			}else if(nominee.getStatus().equals("E")){
				status="Terminated";
			}
			
			filter += "\n\nStatus: " + status;
		}
		
		org.paradyne.lib.ireportV2.TableDataSet filterData = new org.paradyne.lib.ireportV2.TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 14 });
		filterData.setCellNoWrap(new boolean[] { false });
		filterData.setBlankRowsBelow(1);
		rg.addTableToDoc(filterData);
		
		String addressQuery = "";

		addressQuery = " SELECT  DISTINCT(ROWNUM),HRMS_EMP_OFFC.EMP_TOKEN ,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " HRMS_RANK.RANK_NAME,DIV_NAME,DEPT_NAME,HRMS_CENTER.CENTER_NAME,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','E','Terminated','N','Resigned'), "
				+ " HRMS_EMP_FML.FML_ID,HRMS_EMP_FML.FML_FNAME||' '||HRMS_EMP_FML.FML_LNAME,HRMS_RELATION.RELATION_NAME,"
				+ " DECODE(HRMS_EMP_NOMINEE.NOM_TYPE,'G','Gratuity','F','Provident Fund','P','Pension','S','ESIC'),"
				+ " HRMS_EMP_NOMINEE.NOM_SHARE,TO_CHAR(HRMS_EMP_NOMINEE.NOM_DATE,'DD-MM-YYYY')"
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_EMP_NOMINEE ON HRMS_EMP_NOMINEE.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV  "
				+ "	LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
				+ " LEFT JOIN HRMS_EMP_FML ON  HRMS_EMP_FML.EMP_ID=HRMS_EMP_NOMINEE.EMP_ID"
				+ " LEFT JOIN HRMS_RELATION ON HRMS_EMP_FML.FML_RELATION=HRMS_RELATION.RELATION_CODE"
				+ " WHERE 1<2  ";

		if (nominee.getDeptCode() != null
				&& !(nominee.getDeptCode().equals(""))
				&& nominee.getDeptCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN  ("
					+ nominee.getDeptCode() + ")";

		}
		if (nominee.getCenterId() != null
				&& !(nominee.getCenterId().equals(""))
				&& nominee.getCenterId().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ nominee.getCenterId() + ")";

		}
		if (nominee.getDivCode() != null && (!nominee.getDivCode().equals(""))
				&& nominee.getDivCode().length() > 0) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN  ("
					+ nominee.getDivCode() + ")";
		}
		if (nominee.getDesgCode() != null
				&& (!nominee.getDesgCode().equals(""))
				&& nominee.getDesgCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN  ("
					+ nominee.getDesgCode() + ")";
		}
		if (nominee.getStatus() != null && !(nominee.getStatus().equals("-1"))
				&& nominee.getStatus().length() > 0) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ nominee.getStatus() + "')";
			System.out.println("StatusCode =" + nominee.getStatus());
		}
		if (nominee.getEmpName() != null && !(nominee.getEmpName().equals(""))) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_ID ='" + nominee.getEmpid()
					+ "'";
		}

		Object[][] qData = getSqlModel().getSingleResult(addressQuery);

		if (qData == null || qData.length == 0) {
			
			 TableDataSet hdrtable = new TableDataSet();
				Object[][] queryData = getSqlModel().getSingleResult(addressQuery);
				hdrtable.setHeader(new String[] { "Sr. No.", "Employee Id", "Name",
						"Designation", "Divsion", "Department", "Branch", "Status",
						"Nominee Id", "Nominee Name", "Relation",
						"Nomination Type", "Fraction", "Date" });
				hdrtable.setHeaderTable(true);
			
				hdrtable.setHeaderBorderDetail(3);

				hdrtable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 2, 1 });
				hdrtable.setCellWidth(new int[] { 5, 5, 10, 8, 8, 8, 8, 5, 5, 10,
						8, 8, 5, 7 });
				hdrtable.setBorderDetail(3);
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
			 TableDataSet tdstable = new TableDataSet();
			Object[][] queryData = getSqlModel().getSingleResult(addressQuery);
			tdstable.setHeader(new String[] { "Sr. No.", "Employee Id", "Name",
					"Designation", "Divsion", "Department", "Branch", "Status",
					"Nominee Id", "Nominee Name", "Relation",
					"Nomination Type", "Fraction", "Date" });
			tdstable.setHeaderTable(true);
			tdstable.setData(queryData);
			tdstable.setHeaderBorderDetail(3);

			tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 2, 1 });
			tdstable.setCellWidth(new int[] { 5, 5, 10, 8, 8, 8, 8, 5, 5, 10,
					8, 8, 5, 7 });
			tdstable.setBorderDetail(3);
			rg.addTableToDoc(tdstable);
			
			int totalEmployees=queryData.length;
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
			
			
		}
		rg.process();
		
		

		if (reportPath.equals("")) {
			
			rg.createReport(response);
		} else {
		
			rg.saveReport(response);
		}

	}

}
