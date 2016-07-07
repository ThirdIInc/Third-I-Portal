package org.paradyne.model.payroll.salary;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.LTCReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class LTCReportModel extends ModelBase {

	public void generateReport(LTCReportBean bean,
			HttpServletResponse response, String month ){
		String reportQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||EMP_LNAME, ATTENDANCE_DAYS, TRAVEL_DAYS, "
			+ "  LTC_DAYS, LTC_AMOUNT, SAL_ACCNO_REGULAR, SAL_MICR_REGULAR, NVL(BANK_NAME, ' ') "
			+ " FROM HRMS_LTC "
			+ " INNER JOIN HRMS_LTC_DTL ON (HRMS_LTC_DTL.LTC_CODE = HRMS_LTC.LTC_CODE) "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LTC_DTL.LTC_EMP_ID) "
			+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
			+ " WHERE LTC_MONTH = "+month+" AND LTC_YEAR = "+bean.getYear()+" AND LTC_DIVISION = "+bean.getDivisionId();
		
		String empName = "";
		if (!bean.getEmpCode().equals("")) {
			reportQuery += "   AND HRMS_LTC_DTL.LTC_EMP_ID IN =" + bean.getEmpCode();
			empName = bean.getEmployeeName();
		}

		String divName = "";
		if (!bean.getDivisionId().equals("")) {
			reportQuery += "   AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
			divName = bean.getDivisionName();
		}

		String branchName = "";
		if (!bean.getBranchId().equals("")) {
			reportQuery += "   AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
			branchName = bean.getBranchName();
		}

		String designation = "";
		if (!bean.getDesignationId().equals("")) {
			reportQuery += "   AND HRMS_EMP_OFFC.EMP_RANK="
					+ bean.getDesignationId();
			designation = bean.getDesignation();
		}

		String department = "";
		if (!bean.getDepartmentId().equals("")) {
			reportQuery += "   AND HRMS_EMP_OFFC.EMP_DEPT="
					+ bean.getDepartmentId();
			department = bean.getDepartmentName();
		}
		reportQuery += " ORDER BY HRMS_LTC_DTL.LTC_EMP_ID ";
		
		Object[][] reportObj = getSqlModel().getSingleResult(reportQuery);
		
		ReportDataSet rds = new ReportDataSet();

		String reportType = "Xls";
		if (bean.getReportType().equals("P")) {
			reportType = "Pdf";
		}

		rds.setReportType(reportType);
		rds.setFileName("LTC Report ");
		rds.setReportName("LTC Report ");
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		TableDataSet titleName = new TableDataSet();
		titleName.setData(new Object[][] {{"LTC Report"}});
		titleName.setCellAlignment(new int[] { 1 });
		titleName.setCellWidth(new int[] { 100 });
		titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD, new Color(
				0, 0, 0));
		titleName.setBorder(false);
		rg.createHeader(titleName);
		
		Object data[][] = new Object[4][4];
		data[0][0] = "MONTH: ";
		data[0][1] = Utility.month(Integer.parseInt(String.valueOf(month)));
		data[0][2] = "YEAR: ";
		data[0][3] = bean.getYear();
		
		data[1][0] = "DIVISION: ";
		data[1][1] = divName;
		
		if (!bean.getBranchId().equals("")) {
			data[1][2] = "BRANCH: ";
			data[1][3] = branchName;
		}

		if (!bean.getDesignationId().equals("")) {
			data[2][0] = "DESIGNATION: ";
			data[2][1] = designation;
		}
		if (!bean.getDepartmentId().equals("")) {
			data[2][2] = "DEPARTMENT: ";
			data[2][3] = department;
		}
		if (!bean.getEmpCode().equals("")) {
			data[3][0] = "EMPLOYEE: ";
			data[3][1] = empName;
		}


		int[] cellwidth = { 25, 25, 25, 25 };
		int[] alignment = { 0, 0, 0, 0 };
		TableDataSet tdstable = new TableDataSet();
		tdstable.setData(data);
		tdstable.setCellAlignment(alignment);
		tdstable.setCellWidth(cellwidth);
		tdstable.setBorder(false);
		tdstable.setBlankRowsBelow(1);
		tdstable.setHeaderTable(false);
		rg.addTableToDoc(tdstable);
		
		String[] headerNames = new String[9];
		headerNames[0] = "EMP ID";
		headerNames[1] = "EMP NAME";
		headerNames[2] = "ATTENDANCE DAYS";
		headerNames[3] = "TRAVEL DAYS";
		headerNames[4] = "LTC DAYS";
		headerNames[5] = "LTC AMOUNT";
		headerNames[6] = "A/C NO";
		headerNames[7] = "BANK MICR";
		headerNames[8] = "BANK";

		if(reportObj!=null && reportObj.length>0){
			TableDataSet cashBal = new TableDataSet();
			cashBal.setHeader(headerNames);
			cashBal.setData(reportObj);
			cashBal.setCellAlignment(new int[] { 0, 0, 2, 2, 2, 2, 0, 0, 0 });
			cashBal.setCellWidth(new int[] { 10, 25, 15, 15, 15, 15, 15, 15, 20 });
			cashBal.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,
					0, 0));
			cashBal.setHeaderBGColor(new Color(200, 200, 200));
			cashBal.setBorder(true);
			cashBal.setColumnSum(new int[] { 5 });
			cashBal.setHeaderTable(false);
			rg.addTableToDoc(cashBal);
		}
		else{
			TableDataSet noData = new TableDataSet();
			noData.setData(new Object[][]{{"No data to display"}});
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] {100 });
			rg.addTableToDoc(noData);	
		}
		
		System.out.println("Return Report");
		rg.process();
		rg.createReport(response);
		
	}

}
