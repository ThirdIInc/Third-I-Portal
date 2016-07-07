package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.AwardMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.*;
import com.itextpdf.text.BaseColor;

/**
 * @author AA1711 on 09-02-2012
 * @Purpose :AwardMISReportModel class is developed to generate PDF report for
 *          awards present in Award table
 */
public class AwardMisReportModel extends ModelBase {

	/**
	 * @ Method getAwardMISReport @ purpose: @param awr, request, response,
	 *   reportPath
	 */
	public void getAwardMISReport(AwardMisReport awr,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type=awr.getReport();
		rds.setReportType(type);
		String fileName = "AwardMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Award MIS Report");
		rds.setTotalColumns(11);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(awr.getUserEmpId());

		// Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		// Attachment Path Definition
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);			
			request.setAttribute("action", "/srd/AwardMisReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action
		}

		/* Setting Filter Details */

		String filter = "";

		if (!awr.getDivision().equals("")) {
			filter += "\nDivision: " + awr.getDivision();
		}

		if (!awr.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + awr.getDeptName();
		}

		if (!awr.getCenterName().equals("")) {
			filter += "\n\nBranch: " + awr.getCenterName();
		}

		if (!awr.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + awr.getDesgName();
		}

		if (!awr.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + awr.getEmpName();
		}

		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setBodyFontStyle(1);
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[] { 11 });
		filterData.setCellNoWrap(new boolean[] { false });
		rg.addTableToDoc(filterData);

		String filterClause = "";

		if (!awr.getDivision().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN(" + awr.getDivCode()
					+ ")";
			System.out.println("+awr.getDivCode() =" + awr.getDivCode());
		}

		if (!awr.getDeptName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ awr.getDeptCode() + ")";
			System.out.println("+awr.getDeptId() =" + awr.getDeptCode());
		}

		if (!awr.getCenterName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ awr.getCenterId() + ")";
			System.out.println("+awr.getBranchCode() =" + awr.getCenterId());
		}

		if (!awr.getDesgName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN("
					+ awr.getDesgCode() + ")";
			System.out.println("+awr.getDesgCode() =" + awr.getDesgCode());
		}

		if (!awr.getEmpName().equals("")) {
			System.out.println(awr.getEmpid());
			filterClause += " AND HRMS_EMP_OFFC.EMP_ID IN(" + awr.getEmpid()
					+ ")";
			System.out.println("+awr.getEmpCode() =" + awr.getEmpid());
		}
		if (!(awr.getStatus().equals("-1"))) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='" + awr.getStatus()
					+ "'";
		}

		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_RANK.RANK_NAME,"
				+ " HRMS_CENTER.CENTER_NAME, HRMS_DIVISION.DIV_NAME, HRMS_DEPT.DEPT_NAME,"
				+ " DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retire','N','Resigned','E','Terminated'), HRMS_AWARD.AWARD_TYPE, HRMS_AWARD.AWARD_NAME,"
				+ " TO_CHAR( HRMS_AWARD.AWARD_DATE,'DD-MM-YYYY'), HRMS_AWARD.AWARD_REASON"
				+ " FROM HRMS_AWARD INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_AWARD.EMP_ID)"
				+ " LEFT JOIN HRMS_RANK ON HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID"
				+ " LEFT JOIN HRMS_CENTER ON HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID"
				+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV"
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT"
				+ " WHERE 1<2 ";

		query += filterClause;
		query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		// Defining table Structure and Data
		org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		Object[][] queryData = getSqlModel().getSingleResult(query);

		if (queryData == null || queryData.length == 0) {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noDataObj[0][0] = "No records available";
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		} else {
			tdstable.setHeader(new String[] { "Emp ID", " Name",
					" Designation", " Branch", " Division", " Department",
					"Status", "Award Type", "Award Name", "Award Date",
					"Award Reason" });
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
					0 });
			tdstable.setCellWidth(new int[] { 7, 10, 10, 8, 10, 8, 8, 10, 10,
					8, 11 });
			tdstable.setData(queryData);
			// tdstable.setHeaderLines(true);
			// tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
			tdstable.setBorderDetail(3);
			// tdstable.setBorder(true);
			rg.addTableToDoc(tdstable);
		}
		int totalEmployee = queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Employees : "
				+ totalEmployee } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);

		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			rg.saveReport(response);
		}
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * @param empList
	 * @return
	 */
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains the code for Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		return empId;
	} // end of getEmpList method

}