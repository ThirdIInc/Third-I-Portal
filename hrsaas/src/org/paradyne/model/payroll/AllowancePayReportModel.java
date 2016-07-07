/**
 * 
 */
package org.paradyne.model.payroll;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.AllowancePayReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author aa0431 Lakkichand Golegaonkar
 * @date 17 Nov 2010
 */
public class AllowancePayReportModel extends ModelBase {

	public void generateReport(HttpServletResponse response,
			AllowancePayReport allowancePayReport) {
		// TODO Auto-generated method stub

		String query = " SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '|| NVL(EMP_LNAME,' '), CREDIT_NAME, CREDIT_AMOUNT "
				+ " , TO_CHAR(ALLOWANCE_DATE,'DD-MM-YYYY') ,ALLOWANCE_REMARKS "
				+ " FROM HRMS_ALLOWANCE "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_ALLOWANCE.CREDIT_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_ALLOWANCE.EMP_ID) "

				+ " WHERE ALLOWANCE_MONTH="
				+ allowancePayReport.getMonth()
				+ " AND ALLOWANCE_YEAR=" + allowancePayReport.getYear();
		/*
		 * if (allowancePayReport.getEmpId() != null &&
		 * allowancePayReport.getEmpId().length() > 0) { query += " AND
		 * HRMS_ALLOWANCE.EMP_ID =" + allowancePayReport.getEmpId(); }
		 */
		if (allowancePayReport.getDivCode() != null
				&& allowancePayReport.getDivCode().length() > 0) {
			query += " AND HRMS_ALLOWANCE.EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="
					+ allowancePayReport.getDivCode() + ")";
		}
		if (allowancePayReport.getBrnCode() != null
				&& allowancePayReport.getBrnCode().length() > 0) {
			query += " AND HRMS_ALLOWANCE.EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_CENTER="
					+ allowancePayReport.getBrnCode() + ")";
		}

		if (allowancePayReport.getDeptCode() != null
				&& allowancePayReport.getDeptCode().length() > 0) {
			query += " AND HRMS_ALLOWANCE.EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DEPT="
					+ allowancePayReport.getDeptCode() + ")";
		}
		
		if (allowancePayReport.getCreditCode() != null
				&& allowancePayReport.getCreditCode().length() > 0) {
			query += " AND HRMS_ALLOWANCE.CREDIT_CODE "+ allowancePayReport.getCreditCode() ;
		}

		try {
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType(allowancePayReport.getReportType());
			rds.setFileName("Allowance Pay Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			Object[][] nameData = new Object[3][4];
			nameData[0][0] = "Month :";
			nameData[0][1] = Utility.month(Integer.parseInt(allowancePayReport.getMonth()));
			nameData[0][2] = "Year :";
			nameData[0][3] = allowancePayReport.getYear();
			nameData[1][0] = "";
			nameData[1][1] = "";
			nameData[1][2] = "";
			nameData[1][3] = "";
			nameData[2][0] = "";
			nameData[2][1] = "";
			nameData[2][2] = "";
			nameData[2][3] = "";
			if (!allowancePayReport.getDivCode().equals("")) {
				nameData[1][0] = "Division: ";
				nameData[1][1] = allowancePayReport.getDivName();
			}
			if (!allowancePayReport.getBrnCode().equals("")) {
				nameData[1][2] = "Branch: ";
				nameData[1][3] = allowancePayReport.getBrnName();
			}
			if (!allowancePayReport.getDeptCode().equals("")) {
				nameData[2][0] = "Department: ";
				nameData[2][1] = allowancePayReport.getDeptName();
			}
			/*
			 * if (!allowancePayReport.getEmpId().equals("")) { nameData[2][2] =
			 * "Employee: "; nameData[2][3] = allowancePayReport.getEmpName(); }
			 */
			
			Object[][] headerObj = new Object[1][1];
			headerObj[0][0] = "Allowance Pay Report";

			TableDataSet headerTitle = new TableDataSet();
			headerTitle.setData(headerObj);
			headerTitle.setCellAlignment(new int[] { 1 });
			headerTitle.setCellWidth(new int[] { 100 });
			headerTitle.setBodyFontDetails(
					Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			
			rg.addTableToDoc(headerTitle);
			headerTitle.setBlankRowsBelow(2);
			
			TableDataSet filterTable = new TableDataSet();
			filterTable.setData(nameData);
			int[] cellWidth_filterTable = new int[] { 30, 30, 30, 30 };
			int[] cellAlignment_filterTable = new int[] { 0, 0, 0, 0 };
			filterTable.setCellAlignment(cellAlignment_filterTable);
			filterTable.setCellWidth(cellWidth_filterTable);
			rg.addTableToDoc(filterTable);

			TableDataSet tableDataSet = new TableDataSet();
			int[] cellWidth = new int[] { 15, 30, 30, 10, 15, 30 };
			int[] cellAlignment = new int[] { 0, 0, 0, 2, 1, 0 };
			tableDataSet.setData(dataObj);
			tableDataSet.setHeader(new String[]{"Employee Id","Employee Name","Allowance Head","Amount","Date","Remarks"});
			tableDataSet.setHeaderFontDetails(Font.HELVETICA,
					8, Font.BOLD, new Color(0, 0, 0));
			tableDataSet.setHeaderBGColor(new Color(200, 200,
					200));
			tableDataSet.setCellAlignment(cellAlignment);
			tableDataSet.setCellWidth(cellWidth);
			tableDataSet.setBorder(true);
			rg.addTableToDoc(tableDataSet);

			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
