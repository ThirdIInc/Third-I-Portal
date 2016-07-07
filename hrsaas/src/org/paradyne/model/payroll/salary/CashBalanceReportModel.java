package org.paradyne.model.payroll.salary;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.CashBalance;
import org.paradyne.bean.payroll.salary.CashBalanceReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class CashBalanceReportModel extends ModelBase {

	public boolean generateReport(CashBalanceReportBean bean,
			HttpServletResponse response, String[] label, String[] labelTable) {

		ReportDataSet rds = new ReportDataSet();

		String reportType = "";
		if (bean.getReportType().equals("P")) {
			reportType = "Pdf";
		}
		if (bean.getReportType().equals("X")) {
			reportType = "Xls";
		}

		rds.setReportType(reportType);
		rds.setFileName("Cash Balance Report ");
		rds.setReportName("Cash Balance Report ");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		// ----------------
		String title = "Cash Balance Report ";
		String subTitle = "";
		String divisionName = "", divisionAddress = "";

		String divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
				+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,'')  FROM HRMS_DIVISION where DIV_ID="
				+ bean.getDivisionId();
		Object divisionDtl[][] = getSqlModel().getSingleResult(divDetailsQury);

		if (divisionDtl != null && divisionDtl.length > 0) {
			divisionName = String.valueOf(divisionDtl[0][0]);
			divisionAddress = String.valueOf(checkNull("" + divisionDtl[0][2]))
					+ "\n" + String.valueOf(checkNull("" + divisionDtl[0][3]))
					+ String.valueOf(checkNull("" + divisionDtl[0][4]));
		}

		Object[][] nameObj = new Object[1][1];
		boolean isLogo = false;
		try {
			String logoQuery = "select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
			Object logoObj[][] = getSqlModel().getSingleResult(logoQuery);
			if (logoObj != null && logoObj.length > 0) {
				String filename = "";
				if (!String.valueOf(logoObj[0][1]).equals("")) {
					String clientUser = (String) session
							.getAttribute("session_pool");
					filename = String.valueOf(logoObj[0][1]);
					String filePath = context.getRealPath("/") + "pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ filename;
				
					
					nameObj[0][0] = Image.getInstance(filePath);
					isLogo = true;
				} else
					nameObj[0][0] = "";
			} else {
				nameObj[0][0] = "";
			}
		} catch (Exception eee) {
			nameObj[0][0] = " ";
		}

		TableDataSet logoData = new TableDataSet();
		logoData.setData(nameObj);
		logoData.setCellAlignment(new int[] { 0 });
		logoData.setCellWidth(new int[] { 100 });
		logoData.setBorder(false);
		//logoData.setHeaderTable(true);

		Object[][] titleObj = new Object[1][1];
		titleObj[0][0] = "" + divisionName;

		TableDataSet titleName = new TableDataSet();
		titleName.setData(titleObj);
		titleName.setCellAlignment(new int[] { 1 });
		titleName.setCellWidth(new int[] { 100 });
		titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD, new Color(
				0, 0, 0));
		titleName.setBorder(false);

		Object[][] subtitleObj = new Object[1][1];
		subtitleObj[0][0] = "" + divisionAddress;

		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(subtitleObj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName.setBorder(false);

		Object[][] subtitleObj2 = new Object[1][1];
		subtitleObj2[0][0] = "" + title + " " + subTitle;

		TableDataSet subtitleName2 = new TableDataSet();
		subtitleName2.setData(subtitleObj2);
		subtitleName2.setCellAlignment(new int[] { 1 });
		subtitleName2.setCellWidth(new int[] { 100 });
		subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName2.setBorder(false);

		HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,
				subtitleName, false, 0);

		HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,
				subtitleName2, false, 0);
		HashMap<String, Object> mapThree = null;
		if (isLogo)
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
		else
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);
		//rg.addTableToDoc(mapThree);
		rg.createHeader(mapThree);

		// to generate table

		String selQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_DIVISION.DIV_NAME, "
				+ " HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME, NVL(DEPT_NAME,' '), nvl(CREDIT_NAME,' '), "
				+ " NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
				+ " FROM HRMS_CASH_BALANCE "
				+ " inner join hrms_credit_head on(hrms_credit_head.credit_code=hrms_cash_balance.cash_credit_code) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = hrms_cash_balance.EMP_ID) "
				+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+ " WHERE CREDIT_PAYFLAG='N' AND CREDIT_PERIODICITY='M' ";

		String empName = "";
		if (!bean.getEmpId().equals("")) {
			selQuery += "   AND HRMS_CASH_BALANCE.EMP_ID=" + bean.getEmpId();
			empName = bean.getEmployeeName();
		}

		String divName = "";
		if (!bean.getDivisionId().equals("")) {
			selQuery += "   AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
			divName = bean.getDivisionName();
		}

		String branchName = "";
		if (!bean.getBranchId().equals("")) {
			selQuery += "   AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
			branchName = bean.getBranchName();
		}

		String designation = "";
		if (!bean.getDesignationId().equals("")) {
			selQuery += "   AND HRMS_EMP_OFFC.EMP_RANK="
					+ bean.getDesignationId();
			designation = bean.getDesignation();
		}

		if (!bean.getDepartmentId().equals("")) {
			selQuery += "   AND HRMS_EMP_OFFC.EMP_DEPT="
					+ bean.getDepartmentId();

		}
		String creditName="";
		if (!bean.getCreditId().equals("")) {
			selQuery += "   AND hrms_credit_head.credit_code="
					+ bean.getCreditId();
			creditName=bean.getCreditName();

		}

		selQuery += " ORDER BY HRMS_CASH_BALANCE.EMP_ID, CASH_CREDIT_CODE ";

		Object[][] empDetails = getSqlModel().getSingleResult(selQuery);

		Object data[][] = new Object[3][4];
		if (!bean.getEmpId().equals("")) {
			data[0][0] = label[0];
			data[0][1] = empName;
		}

		if (!bean.getDivisionId().equals("")) {
			data[0][2] = label[1];
			data[0][3] = divName;
		}

		if (!bean.getBranchId().equals("")) {
			data[1][0] = label[2];
			data[1][1] = branchName;
		}

		if (!bean.getDesignationId().equals("")) {
			data[1][2] = label[3];
			data[1][3] = designation;
		}
		if (!bean.getCreditId().equals("")) {
			data[2][0] = label[4];
			data[2][1] = creditName;
		}
		

		int[] cellwidth = { 25, 25, 25, 25 };
		int[] alignment = { 0, 0, 0, 0 };
		TableDataSet tdstable = new TableDataSet();
		tdstable.setData(data);
		tdstable.setCellAlignment(alignment);
		tdstable.setCellWidth(cellwidth);
		tdstable.setBorder(false);
		tdstable.setBlankRowsBelow(1);
		//tdstable.setHeaderTable(false);
		rg.addTableToDoc(tdstable);

		String[] headerNames = new String[9];
		headerNames[0] = "EMP ID";
		headerNames[1] = "EMP NAME";
		headerNames[2] = "DIVISION";
		headerNames[3] = "BRANCH";
		headerNames[4] = "DESIGNATION";
		headerNames[5] = "DEPARTMENT";
		headerNames[6] = "CREDIT NAME";
		headerNames[7] = "CASH BALANCE AMT";
		headerNames[8] = "CASH ONHOLD AMT";

		if(empDetails!=null && empDetails.length>0){
			TableDataSet cashBal = new TableDataSet();
			cashBal.setHeader(headerNames);
			cashBal.setData(empDetails);
			cashBal.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 2, 2 });
			cashBal.setCellWidth(new int[] { 10, 20, 20, 20, 20, 15, 20, 15, 15 });
			cashBal.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,
					0, 0));
			cashBal.setHeaderBGColor(new Color(200, 200, 200));
			cashBal.setBorder(true);
			cashBal.setColumnSum(new int[] { 7, 8 });
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

		rg.process();
		rg.createReport(response);
		return true;
	}

	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
