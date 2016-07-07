package org.paradyne.model.settlement;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.settlement.SettlementRegister;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class SettlementRegisterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ModelBase.class);
HttpServletResponse response;
NumberFormat formatter = new DecimalFormat("#0.00");

/**
 * 
* Added on Date:22-10-2008 Following function is called in the
* getReportDataUnCheck() function to generate the debit amount for each
* employee when the check box for Branch and Department wise is not
* checked.
* 
* @param emp_id
* @param month
* @param year
* @param settlReg
* @return
*/
public Object[][] getSalaryDebitDataUncheck(String settlCode
		,String fromMonth,String toMonth,String fromYear,String toYear) {
	Object[][] credit_amount = null;

	try {

		String sal_Amount = "SELECT  DEBIT_CODE,SUM(NVL(SETTL_AMT,0.00))  FROM HRMS_DEBIT_HEAD  "
				+" LEFT JOIN HRMS_SETTL_DEBITS ON (SETTL_DEBIT_CODE = DEBIT_CODE AND SETTL_CODE="+settlCode+" and ((SETTL_MTH>="+fromMonth+" and HRMS_SETTL_DEBITS.SETTL_YEAR="+fromYear+") OR (SETTL_MTH<="+toMonth+" and HRMS_SETTL_DEBITS.SETTL_YEAR="+toYear+"))) "
				//+" LEFT JOIN HRMS_SETTL_DTL ON (HRMS_SETTL_DTL.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE AND SETTL_MONTH=SETTL_MTH AND HRMS_SETTL_DEBITS.SETTL_YEAR=HRMS_SETTL_DTL.SETTL_YEAR)"
				+" WHERE DEBIT_PAYFLAG='Y' AND SETTL_MTH_TYPE='CO' GROUP BY DEBIT_CODE ORDER BY DEBIT_CODE";
		credit_amount = getSqlModel().getSingleResult(sal_Amount,
				new Object[0][0]);

	} catch (Exception e) {
		logger.error(e.getMessage());

	}
	return credit_amount;

	}

/**
* Added on Date:22-10-2008 following function is called to generate the
* credit amount for the employee in the function getReportDataUnCheck()
* function when the check box for Branch and department wise is not
* checked.
* 
* @param empCode
* @param year
* @param month
* @param settlReg
* @return
*/
public Object[][] getSalaryCreditDataUncheck(String settlCode
		,String fromMonth,String toMonth,String fromYear,String toYear) {
Object[][] credit_amount = null;

try {

	String sal_Amount = "SELECT  CREDIT_CODE,SUM(NVL(SETTL_AMT,0.00)),CREDIT_APPLICABLE_ESI  FROM HRMS_CREDIT_HEAD  "
			+" LEFT JOIN HRMS_SETTL_CREDITS ON (SETTL_CREDIT_CODE = CREDIT_CODE AND SETTL_CODE="+settlCode+" and ((SETTL_MTH>="+fromMonth+" and HRMS_SETTL_CREDITS.SETTL_YEAR="+fromYear+") OR (SETTL_MTH<="+toMonth+" and HRMS_SETTL_CREDITS.SETTL_YEAR="+toYear+"))) "
			//+" LEFT JOIN HRMS_SETTL_DTL ON (HRMS_SETTL_DTL.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE AND SETTL_MONTH=SETTL_MTH AND HRMS_SETTL_CREDITS.SETTL_YEAR=HRMS_SETTL_DTL.SETTL_YEAR )"
			+" WHERE CREDIT_PAYFLAG='Y'  AND SETTL_MTH_TYPE='CO' GROUP BY CREDIT_CODE,CREDIT_APPLICABLE_ESI ORDER BY CREDIT_CODE";
	credit_amount = getSqlModel().getSingleResult(sal_Amount,
			new Object[0][0]);

} catch (Exception e) {
	logger.error(e.getMessage());

}
return credit_amount;

}

/**
* Added on date:22-10-2008 following function is called when the branch and
* department wise check box is unchecked.
* 
* @param settlReg
* @param response
* @param typeName
* @param desgName
* @param deptName
* @param branchName
* @param settlReg
*/
public void genReport(String reportType,String fromMonth,String fromYear,String toMonth,String toYear, 
	String divCode, String brnCode, String deptCode,
	String empTypeCode, String desgCode, HttpServletResponse response, String divisionName) {
try {
	int check = 0;
	int colTotal = 0;
	String reportName = "Settlement Register of " + divisionName
				+ " from "
				+ Utility.month(Integer.parseInt(fromMonth)) + "," + fromYear
				+ " To "+ Utility.month(Integer.parseInt(toMonth)) + "," + toYear;

	org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
			reportType, reportName, "A4");
	rg.setFName(reportName + "." + reportType);
	if (reportType.equals("Pdf")) {
		rg.addTextBold("Settlement Register of " + divisionName
				+ " from "
				+ Utility.month(Integer.parseInt(fromMonth)) + "," + fromYear
				+ " To "+ Utility.month(Integer.parseInt(toMonth)) + "," + toYear, 0, 1, 0);
	} else {
		Object[][] title = new Object[1][3];
		title[0][0] = "";
		title[0][1] = "";
		title[0][2] = "Settlement Register of " + divisionName
				+ " from "
				+ Utility.month(Integer.parseInt(fromMonth)) + "," + fromYear
				+ " To "+ Utility.month(Integer.parseInt(toMonth)) + "," + toYear;

		int[] cols = { 20, 20, 50 };
		int[] align = { 0, 0, 1 };
		rg.tableBodyNoCellBorder(title, cols, align, 1);
	}


	/*String selectSalaryLoop = "SELECT HRMS_SALARY_" + year
			+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
			+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
	if (brnChk.equals("Y")) {
		selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
	} else {
		selectSalaryLoop += ",' ' ";
	}
	if (deptChk.equals("Y")) {
		selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
	} else {
		selectSalaryLoop += ",' '";
	}
	selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";

	if (desgChk.equals("Y")) {
		selectSalaryLoop += ",NVL(RANK_NAME,' ')";

	} else {
		selectSalaryLoop += ",' '";

	}

	selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
			+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
			+ year
			+ " INNER JOIN HRMS_EMP_OFFC "
			+ " ON HRMS_SALARY_"
			+ year
			+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
			+ year
			+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
			+ " INNER JOIN HRMS_SALARY_LEDGER ON"
			+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
			+ year
			+ ".SAL_LEDGER_CODE "
			+ " AND LEDGER_MONTH="
			+ month
			+ " AND LEDGER_YEAR="
			+ year
			+ ") "
			+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
	if (brnChk.equals("Y")) {
		selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
				+ year + ".SAL_EMP_CENTER)";
	}

	if (deptChk.equals("Y")) {
		selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
				+ year + ".SAL_DEPT)";
	}

	if (desgChk.equals("Y")) {
		selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
				+ year + ".SAL_EMP_RANK)";

	}
	selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";

	selectSalaryLoop += " WHERE SAL_DIVISION=" + divCode;

	if (!(brnCode.equals(""))) {
		selectSalaryLoop += " AND SAL_EMP_CENTER=" + brnCode + " ";
	}
	if (!(onHoldCode.equals("A"))) {
		selectSalaryLoop += "AND sal_onhold='" + onHoldCode + "' ";
	}
	if (!(deptCode.equals(""))) {

		selectSalaryLoop += "and SAL_DEPT=" + deptCode;
	}

	if (!empTypeCode.equals("")) {
		selectSalaryLoop += " AND SAL_EMP_TYPE=" + empTypeCode;
	}
	if (!desgCode.equals("")) {
		selectSalaryLoop += " AND SAL_EMP_RANK=" + desgCode;
	}

	selectSalaryLoop += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";*/

	/*String selectEmpLoop="SELECT HRMS_SETTL_HDR.SETTL_ECODE ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME, NVL(TYPE_NAME,' '),SETTL_DAYS,SETTL_MONTH, SETTL_YEAR,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' '),' ',NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),"
			+" CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other'" 
			+" END,'',HRMS_SETTL_DTL.SETTL_CODE "
			+" FROM HRMS_SETTL_HDR "
			+" inner join HRMS_SETTL_DTL ON (HRMS_SETTL_DTL.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
			+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_SETTL_HDR.SETTL_ECODE = HRMS_EMP_OFFC.EMP_ID  "
			+" LEFT JOIN HRMS_EMP_TYPE ON(EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "
			+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			+" LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
			+" LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE "
			+" WHERE SETTL_TYPE='CO' AND EMP_DIV="+divCode;*/
	
	String selectEmpLoop="SELECT DISTINCT HRMS_SETTL_HDR.SETTL_ECODE ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME, "
		+" HRMS_SETTL_HDR.SETTL_CODE,TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'), NVL(SETTL_LEAVE_ENCASH,0),NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), NVL(SETTL_DEDUCTION,0)," 
		+" NVL(SETTL_TAX_AMT,0)  FROM HRMS_SETTL_HDR  "
		 +" INNER JOIN HRMS_EMP_OFFC  ON HRMS_SETTL_HDR.SETTL_ECODE = HRMS_EMP_OFFC.EMP_ID   "
		 +" inner join HRMS_SETTL_DTL ON (HRMS_SETTL_DTL.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
		 +" WHERE EMP_DIV="+divCode+" AND SETTL_TYPE='CO' and " 
		 +"((SETTL_MONTH>="+fromMonth+" and SETTL_YEAR="+fromYear+") OR (SETTL_MONTH<="+toMonth+" and SETTL_YEAR="+toYear+"))";
	
	if (!(brnCode.equals(""))) {
		selectEmpLoop += " AND EMP_CENTER=" + brnCode + " ";
	}
	if (!(deptCode.equals(""))) {

		selectEmpLoop += " AND EMP_DEPT=" + deptCode;
	}

	if (!empTypeCode.equals("")) {
		selectEmpLoop += " AND EMP_TYPE=" + empTypeCode;
	}
	if (!desgCode.equals("")) {
		selectEmpLoop += " AND EMP_RANK=" + desgCode;
	}
			
	selectEmpLoop +=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)  ";
	

	// UPDATED BY REEBA
	Object[][] reportDataPay = getReportDataUnCheck(selectEmpLoop,
			fromMonth, fromYear, toMonth, toYear, check,
			divisionName);

	// Object[][] reportDataPay =
	// getReportDataUnCheck(selectSalaryLoop,settlReg,arrEmpLength,check);
	if (reportDataPay.length > 1) {

		String finalHeader[] = null;
		int headerLength = 0;
		int[] cellWidth = null;
		int[] alignment = null;
		
			finalHeader = new String[reportDataPay[0].length];
			headerLength = reportDataPay[0].length;
			cellWidth = new int[reportDataPay[0].length];
			alignment = new int[reportDataPay[0].length];
		
		// String finalHeader[] = new String[reportDataPay[0].length];

		// * Following loop is used to set the cell width

		for (int i = 0; i < headerLength; i++) {
			finalHeader[i] = String.valueOf(reportDataPay[0][i]);

			alignment[i] = 0;
			if (i > 1) {
				cellWidth[i] = 7;
				alignment[i] = 2;

			} else {
				cellWidth[0] = 8;
				cellWidth[1] = 15;
			}
		}// End of cell width loop

		Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];

		// * Following loop is used to set the credit and debit values

		for (int i = 0; i < finalData.length; i++) {
			for (int j = 0; j < finalData[0].length; j++) {
				finalData[i][j] = String
						.valueOf(reportDataPay[i + 1][j]);

			}
		}// End of finalData loop

		Object totalByColumn[][] = null;
		
			totalByColumn = new Object[1][finalData[0].length];
			totalByColumn[0][0] = "TOTAL :-";
			totalByColumn[0][1] = "No. of Employees:"
					+ finalData.length;
			colTotal = check + 3;

		for (int i = colTotal; i < finalData[0].length; i++) {
			double total = 0.00;
			for (int j = 0; j < finalData.length; j++) {
				if (String.valueOf(finalData[j][i]).equals("null")
						|| String.valueOf(finalData[j][i]).equals("")
						|| String.valueOf(finalData[j][i]).equals(
								"null.00")) {
					finalData[j][i] = "0.00";
				}
				if (String.valueOf(finalData[j][1])
						.contains("Recovery"))
					total -= Double.parseDouble(String
							.valueOf(finalData[j][i]));
				else
					total += Double.parseDouble(String
							.valueOf(finalData[j][i]));
			}
			
				totalByColumn[0][i] = Utility.twoDecimals(formatter
						.format(total));
			
		}

		rg.addText("\n", 0, 0, 0);

		/*
		 * Object[][] filterObj = new Object[2][4]; filterObj[0][0] =
		 * "Branch : "; filterObj[0][1] = branchName; filterObj[0][2] =
		 * "Department : "; filterObj[0][3] = deptName;
		 * 
		 * filterObj[1][0] = "Designation : "; filterObj[1][1] =
		 * desgName; filterObj[1][2] = "Employee Type : ";
		 * filterObj[1][3] = typeName;
		 * 
		 * rg.tableBody(filterObj,cellWidth,alignment);
		 * rg.addText("\n",0,0,0);
		 */

		
			rg.tableBody(finalHeader, finalData, cellWidth, alignment);
			rg.tableBody(totalByColumn, cellWidth, alignment);
		

	} else {
		rg.addText("Records are not available.", 0, 1, 0);
	}
	rg.createReport(response);
} catch (Exception e) {
	e.printStackTrace();
}

}

// GET EMP_ID THOSE FULLFILL SORTING CRITERIA

public Object[][] getEmpId(SettlementRegister settlReg) {
Object emp_id[][] = null;
try {
	/*
	 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
	 * 
	 */
	// String selectSalary = " SELECT EMP_ID ,EMP_TOKEN ,EMP_FNAME ||'
	// '||EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE
	// EMP_PAYBILL IS NOT NULL ";
	String selectSalary = " SELECT HRMS_SALARY_"
			+ settlReg.getYear()
			+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME "
			+ " FROM HRMS_SALARY_" + settlReg.getYear()
			+ " inner join HRMS_EMP_OFFC  on HRMS_SALARY_"
			+ settlReg.getYear() + ".EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
			/*
			 * " left join hrms_center on (hrms_center.CENTER_ID=
			 * HRMS_EMP_OFFC.EMP_CENTER) "+ " left join HRMS_LOCATION on
			 * (HRMS_LOCATION.LOCATION_CODE =
			 * hrms_center.CENTER_LOCATION) " +
			 */
			" WHERE EMP_PAYBILL IS NOT  NULL ";
	String where = " ";
	try {

		where = where + " AND SAL_MONTH=" + settlReg.getMonth();
		selectSalary = selectSalary + where;

	} catch (Exception e) {
		e.printStackTrace();
	}

	emp_id = getSqlModel().getSingleResult(selectSalary);
} catch (Exception e) {
	logger.error(e.getMessage());
}
return emp_id;
}

/*
* Following function is used in getReportDataNoBranchDept() method to
* return the employee details.
*/
public Object[][] getEmpIdNew(String selectSalary) {
Object emp_id[][] = null;
try {
	emp_id = getSqlModel().getSingleResult(selectSalary);
} catch (Exception e) {
	logger.error(e.getMessage());
}
return emp_id;
}

// Following function is called when branch or department is not selected at
// all
// This method returns the salary amount and the corresponding credit code
public Object[][] getSalaryCreditDataNoSelect(String empCode, String year,
	String month, SettlementRegister settlReg, String brnCode, String deptCode) {
Object[][] credit_amount = null;

try {

	String ledgCode = " select distinct sal_ledger_code from hrms_salary_"
			+ year
			+ " inner join hrms_salary_ledger "
			+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
			+ year
			+ ".sal_ledger_code)"
			+ "  where ledger_month="
			+ month
			+ " and ledger_year=" + year;
	// UPDATED BY REEBA BEGINS
	if (settlReg.getChkBrnOrder().equals("Y")) {
		ledgCode += "  and sal_emp_center=" + brnCode;
	}
	if (settlReg.getChkDeptOrder().equals("Y")) {
		ledgCode += " AND SAL_DEPT=" + deptCode;
	}
	// UPDATED BY REEBA ENDS
	ledgCode += " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START')  AND EMP_ID="
			+ empCode;
	Object[][] ledger_code = getSqlModel().getSingleResult(ledgCode,
			new Object[0][0]);

	// UPDATED BY REEBA
	String selectCredits = "SELECT   SAL_CREDIT_CODE,SAL_AMOUNT , CREDIT_APPLICABLE_ESI FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
			+ year
			+ " ON (SAL_CREDIT_CODE = CREDIT_CODE and EMP_ID="
			+ empCode
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledger_code[0][0])
			+ " ) "
			+ " WHERE CREDIT_PAYFLAG='Y' ORDER BY SAL_CREDIT_CODE ";

	credit_amount = getSqlModel().getSingleResult(selectCredits,
			new Object[0][0]);

} catch (Exception e) {
	credit_amount = new Object[0][0];
	logger.error(e.getMessage());
	e.printStackTrace();
}
return credit_amount;

}

/*
* Following function is called to return the arrear credit amount from
* HRMS_ARREARS_CREDIT_2008(Year Entered) when the arrear type is
* promotional.
*/
public Object[][] arrearCreditData(String arrearCode, String empId,
	String month, String year, Object[][] arrear, String type,
	String promCode, String salYear) {
Object[][] amt = new Object[arrear.length][2];
try {

	// String query=" SELECT NVL(ARREARS_AMT,0) FROM
	// HRMS_ARREARS_credit_"+settlReg.getYear()+" INNER JOIN
	// HRMS_ARREARS_"+settlReg.getYear()+"
	// on(hrms_arrears_"+settlReg.getYear()+".arrears_code=hrms_arrears_credit_"+settlReg.getYear()+".arrears_code)
	// WHERE
	// hrms_arrears_credit_"+settlReg.getYear()+".ARREARS_EMP_ID="+empId+"
	// AND
	// HRMS_ARREARS_CREDIT_"+settlReg.getYear()+".ARREARS_CODE="+arrearCode+"
	// ORDER BY ARREARS_CREDIT_CODE";
	for (int i = 0; i < arrear.length; i++) {
		if (arrear[i][0] != null) {
			String query = " SELECT distinct NVL(ARREARS_AMT,0),CREDIT_APPLICABLE_ESI FROM HRMS_ARREARS_credit_"
					+ salYear
					+ " inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_credit_"
					+ salYear
					+ ".arrears_code)"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ARREARS_CREDIT_CODE)"
					+ " WHERE hrms_arrears_credit_"
					+ salYear
					+ ".ARREARS_EMP_ID="
					+ empId
					+ " AND HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_CODE="
					+ arrearCode
					+ " AND "
					+ " HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_MONTH="
					+ month
					+ " AND HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_YEAR="
					+ year
					+ " AND ARREARS_CREDIT_CODE="
					+ arrear[i][0]
					+ "  AND PROM_CODE="
					+ promCode
					+ " and arrears_type="
					+ "'"
					+ type
					+ "' AND ARREARS_PAY_IN_SAL = 'Y'";

			Object[][] amount = getSqlModel().getSingleResult(query,
					new Object[0][0]);
			if (amount.length == 0 || amount == null) {
				amt[i][0] = "0.00";
				amt[i][1] = "N";
			} else {
				if (String.valueOf(amount[0][0]).equals("")
						|| String.valueOf(amount[0][0]).equals("null")) {
					amt[i][0] = "0.00";
					amt[i][1] = "N";
				} else {
					amt[i][0] = amount[0][0];
					amt[i][1] = amount[0][1];
				}
			}
		}
	}
} catch (Exception e) {

	logger.error(e.getMessage());
}
return amt;
}

/*
* Following function is called to return the arrear credit amount from
* HRMS_ARREARS_CREDIT_2008(Year Entered) when the promotional type is
* monthly.
*/
public Object[][] arrearCreditData(String arrearCode, String empId,
	String month, String year, Object[][] arrear, String type,
	String salYear) {
Object[][] amt = new Object[arrear.length][2];
try {

	for (int i = 0; i < arrear.length; i++) {
		if (arrear[i][0] != null) {
			String query = " SELECT distinct NVL(ARREARS_AMT,0),CREDIT_APPLICABLE_ESI FROM HRMS_ARREARS_credit_"
					+ salYear
					+ " inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_credit_"
					+ salYear
					+ ".arrears_code)"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ARREARS_CREDIT_CODE)"
					+ " WHERE hrms_arrears_credit_"
					+ salYear
					+ ".ARREARS_EMP_ID="
					+ empId
					+ " AND HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_CODE="
					+ arrearCode
					+ " AND "
					+ " HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_MONTH="
					+ month
					+ " AND HRMS_ARREARS_CREDIT_"
					+ salYear
					+ ".ARREARS_YEAR="
					+ year
					+ " AND ARREARS_CREDIT_CODE="
					+ arrear[i][0]
					+ " AND arrears_type="
					+ "'"
					+ type
					+ "' AND ARREARS_PAY_IN_SAL = 'Y'";

			Object[][] amount = getSqlModel().getSingleResult(query,
					new Object[0][0]);
			if (amount.length == 0 || amount == null) {
				amt[i][0] = "0.00";
				amt[i][1] = "N";
			} else {
				if (String.valueOf(amount[0][0]).equals("")
						|| String.valueOf(amount[0][0]).equals("null")) {
					amt[i][0] = "0.00";
					amt[i][1] = "N";
				} else {
					amt[i][0] = amount[0][0];
					amt[i][1] = amount[0][1];
				}
			}
		}
	}
} catch (Exception e) {

	logger.error(e.getMessage());
}
return amt;
}

/*
* Following function is called to return the arrear debit amount from
* HRMS_ARREARS_DEBIT_2008(Year Entered) when the arrear type is
* promotional.
*/
public Object[][] arrearDebitData(String arrearCode, String empId,
	String month, String year, Object[][] arrear, String type,
	String promCode, String salYear) {
Object[][] amt = new Object[arrear.length][2];
try {
	for (int i = 0; i < arrear.length; i++) {
		if (arrear[i][0] != null) {
			String query = " SELECT distinct NVL(ARREARS_AMT,0),ARREARS_DEBIT_CODE FROM HRMS_ARREARS_debit_"
					+ salYear
					+ " inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_debit_"
					+ salYear
					+ ".arrears_code)"
					+ " WHERE hrms_arrears_debit_"
					+ salYear
					+ ".ARREARS_EMP_ID="
					+ empId
					+ " AND HRMS_ARREARS_DEBIT_"
					+ salYear
					+ ".ARREARS_CODE="
					+ arrearCode
					+ " AND "
					+ " HRMS_ARREARS_debit_"
					+ salYear
					+ ".ARREARS_MONTH="
					+ month
					+ " AND HRMS_ARREARS_DEBIT_"
					+ salYear
					+ ".ARREARS_YEAR="
					+ year
					+ " AND ARREARS_DEBIT_CODE="
					+ arrear[i][0]
					+ " AND PROM_CODE="
					+ promCode
					+ "and arrears_type="
					+ "'"
					+ type
					+ "' AND ARREARS_PAY_IN_SAL = 'Y'";
			Object[][] amount = getSqlModel().getSingleResult(query,
					new Object[0][0]);
			if (amount == null || amount.length == 0) {
				amt[i][0] = "0.00";
				amt[i][1] = "N";
			} else {
				if (String.valueOf(amount[0][0]).equals("")
						|| String.valueOf(amount[0][0]).equals("null")) {
					amt[i][0] = "0.00";
					amt[i][1] = "N";
				} else {
					amt[i][0] = amount[0][0];
					amt[i][1] = amount[0][1];
				}
			}
		}
	}
} catch (Exception e) {

	logger.error(e.getMessage());
}
return amt;
}

/*
* Following function is called to return the arrear debit amount from
* HRMS_ARREARS_DEBIT_2008(Year Entered) when the arrear type is monthly.
*/
public Object[][] arrearDebitData(String arrearCode, String empId,
	String month, String year, Object[][] arrear, String type,
	String salYear) {
Object[][] amt = new Object[arrear.length][2];
try {
	for (int i = 0; i < arrear.length; i++) {
		if (arrear[i][0] != null) {
			// String query=" SELECT NVL(ARREARS_AMT,0) FROM
			// HRMS_ARREARS_DEBIT_"+settlReg.getYear()+" INNER JOIN
			// HRMS_ARREARS_"+settlReg.getYear()+"
			// on(hrms_arrears_"+settlReg.getYear()+".arrears_code=hrms_arrears_debit_"+settlReg.getYear()+".arrears_code)
			// WHERE
			// hrms_arrears_debit_"+settlReg.getYear()+".ARREARS_EMP_ID="+empId+"
			// AND
			// HRMS_ARREARS_DEBIT_"+settlReg.getYear()+".ARREARS_CODE="+arrearCode+"
			// ORDER BY ARREARS_debit_CODE";
			String query = " SELECT distinct NVL(ARREARS_AMT,0),ARREARS_DEBIT_CODE FROM HRMS_ARREARS_debit_"
					+ salYear
					+ " inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_debit_"
					+ salYear
					+ ".arrears_code)"
					+ " WHERE hrms_arrears_debit_"
					+ salYear
					+ ".ARREARS_EMP_ID="
					+ empId
					+ " AND HRMS_ARREARS_DEBIT_"
					+ salYear
					+ ".ARREARS_CODE="
					+ arrearCode
					+ " AND "
					+ " HRMS_ARREARS_debit_"
					+ salYear
					+ ".ARREARS_MONTH="
					+ month
					+ " AND HRMS_ARREARS_DEBIT_"
					+ salYear
					+ ".ARREARS_YEAR="
					+ year
					+ " AND ARREARS_DEBIT_CODE="
					+ arrear[i][0]
					+ " and arrears_type="
					+ "'"
					+ type
					+ "' AND ARREARS_PAY_IN_SAL = 'Y'";
			Object[][] amount = getSqlModel().getSingleResult(query,
					new Object[0][0]);
			if (amount == null || amount.length == 0) {
				amt[i][0] = "0.00";
				amt[i][1] = "N";
			} else {
				if (String.valueOf(amount[0][0]).equals("")
						|| String.valueOf(amount[0][0]).equals("null")) {
					amt[i][0] = "0.00";
					amt[i][1] = "N";
				} else {
					amt[i][0] = amount[0][0];
					amt[i][1] = amount[0][1];
				}
			}
		}
	}
} catch (Exception e) {

	logger.error(e.getMessage());
}
return amt;
}

// Following function is called when branch or department or both are
// selected
public Object[][] getSalaryCreditData(String empCode, String year,
	String month, SettlementRegister settlReg, String Id) {
Object[][] credit_amount = null;
Object[][] ledgCode = null;
String ledgerCode = null;

try {
	if (!(settlReg.getBranchCode().equals(""))
			&& settlReg.getDeptCode().equals("")) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_BRN="+settlReg.getBranchCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_emp_center="
				+ settlReg.getBranchCode()
				+ " AND SAL_DEPT="
				+ Id
				+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (settlReg.getBranchCode().equals("")
			&& (!(settlReg.getDeptCode().equals("")))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_DEPT="+settlReg.getDeptCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_dept="
				+ settlReg.getDeptCode()
				+ " and sal_emp_center="
				+ Id
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (!(settlReg.getBranchCode().equals(""))
			&& !(settlReg.getDeptCode().equals(""))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_BRN="+settlReg.getBranchCode()+" AND
		// LEDGER_DEPT="+settlReg.getDeptCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_emp_center="
				+ settlReg.getBranchCode()
				+ " and sal_dept="
				+ settlReg.getDeptCode()
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);

	}

	// UPDATED BY REEBA
	String sal_Amount = "SELECT   SAL_CREDIT_CODE,SAL_AMOUNT,CREDIT_APPLICABLE_ESI  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
			+ year
			+ " ON (SAL_CREDIT_CODE = CREDIT_CODE and EMP_ID="
			+ empCode
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledgCode[0][0])
			+ ") "
			+ " WHERE CREDIT_PAYFLAG='Y' ORDER BY SAL_CREDIT_CODE ";
	credit_amount = getSqlModel().getSingleResult(sal_Amount,
			new Object[0][0]);

} catch (Exception e) {
	logger.error(e.getMessage());

}

return credit_amount;

}

// Following function is called when pay bill or emp type is not selected at
// all
public Object[][] getSalaryCreditDataNoEmpTypePayBill(String empCode,
	String year, String month, SettlementRegister settlReg, String typeId,
	String payId) {
Object[][] credit_amount = null;
try {
	// String ledgCode="SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE
	// LEDGER_MONTH="+month+" and ledger_year="+year+" and
	// ledger_emptype="+typeId+" and ledger_paybill="+payId+" and
	// LEDGER_STATUS='SAL_FINAL' order by ledger_code";
	String ledgCode = " select sal_ledger_code from hrms_salary_"
			+ year
			+ " inner join hrms_salary_ledger "
			+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
			+ year
			+ ".sal_ledger_code)"
			+ "  where ledger_month="
			+ month
			+ " and ledger_year="
			+ year
			+ "  and sal_emp_type="
			+ typeId
			+ " AND SAL_emp_paybill="
			+ payId
			+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
			+ empCode;
	Object[][] ledger_code = getSqlModel().getSingleResult(ledgCode,
			new Object[0][0]);

	String selectCredits = "SELECT   SAL_CREDIT_CODE,SAL_AMOUNT  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
			+ year
			+ " ON (SAL_CREDIT_CODE = CREDIT_CODE and EMP_ID="
			+ empCode
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledger_code[0][0])
			+ ") "
			+ " WHERE CREDIT_PAYFLAG='Y' ORDER BY SAL_CREDIT_CODE ";

	credit_amount = getSqlModel().getSingleResult(selectCredits,
			new Object[0][0]);

} catch (Exception e) {
	credit_amount = new Object[0][0];

}
return credit_amount;

}

// Following function is called emp type or pay bill or both are selected.
public Object[][] getSalaryCreditDataEmpTypePayBill(String empCode,
	String year, String month, SettlementRegister settlReg, String id,
	String id1) {
Object[][] credit_amount = null;
Object[][] ledgCode = null;
String ledgerCode = null;

try {
	if (!(settlReg.getTypeCode().equals(""))
			&& settlReg.getPayBillNo().equals("")) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_EMPTYPE="+settlReg.getTypeCode();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_type="
				+ settlReg.getTypeCode()
				+ " and sal_emp_paybill="
				+ id
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if ((settlReg.getTypeCode().equals("") && !(settlReg
			.getPayBillNo().equals("")))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_PAYBILL="+settlReg.getPayBillNo();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_paybill="
				+ settlReg.getPayBillNo()
				+ " and sal_emp_type="
				+ id1
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (!(settlReg.getTypeCode().equals(""))
			&& !(settlReg.getPayBillNo().equals(""))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_EMPTYPE="+settlReg.getTypeCode()+" AND
		// LEDGER_PAYBILL="+settlReg.getPayBillNo();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_type="
				+ settlReg.getTypeCode()
				+ " and sal_emp_paybill="
				+ settlReg.getPayBillNo()
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ empCode;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);

	}
	String sal_Amount = "SELECT  CREDIT_CODE,SAL_AMOUNT  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
			+ year
			+ " ON (SAL_CREDIT_CODE = CREDIT_CODE and EMP_ID="
			+ empCode
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledgCode[0][0])
			+ ") "
			+ " WHERE CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
	credit_amount = getSqlModel().getSingleResult(sal_Amount,
			new Object[0][0]);

} catch (Exception e) {

}

return credit_amount;

}

/**
* Following method is used in getReportDataNoBranchDept() which returns the
* credit code,credit abbr to set the credit head names.
* 
* @return
*/
public Object[][] getCreditHeader() {
Object credit_header[][] = null;
try {
	String selectCredit = "SELECT CREDIT_CODE,  CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_PAYFLAG='Y' order BY CREDIT_CODE";
	credit_header = getSqlModel().getSingleResult(selectCredit);
} catch (Exception e) {
	e.printStackTrace();
}
return credit_header;

}

/**
* Following method is used in getReportDataNoBranchDept() which returns the
* debit code,debit abbr to set the debit head names.
* 
* @return
*/
public Object[][] getDebitHeader() {
Object debit_header[][] = null;
try {
	String selectDebit = "SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD WHERE DEBIT_PAYFLAG='Y' "
			+ " ORDER BY DEBIT_CODE";
	debit_header = getSqlModel().getSingleResult(selectDebit);
} catch (Exception e) {
	logger.error(e.getMessage());
}
return debit_header;

}

// //Following function is called emp type or pay bill or both are selected.
public Object[][] getSalDebitEmpTypePayBill(String emp_id, String month,
	String year, SettlementRegister settlReg, String id, String id1) {
Object[][] debit_amount = null;
Object[][] ledgCode = null;
String ledgerCode = null;

try {
	if (!(settlReg.getTypeCode().equals(""))
			&& settlReg.getPayBillNo().equals("")) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_EMPTYPE="+settlReg.getTypeCode();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_type="
				+ settlReg.getTypeCode()
				+ " and sal_emp_paybill="
				+ id
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if ((settlReg.getTypeCode().equals("") && !(settlReg
			.getPayBillNo().equals("")))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_PAYBILL="+settlReg.getPayBillNo();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_paybill="
				+ settlReg.getPayBillNo()
				+ " and sal_emp_type="
				+ id1
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (!(settlReg.getTypeCode().equals(""))
			&& !(settlReg.getPayBillNo().equals(""))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_EMPTYPE="+settlReg.getTypeCode()+" AND
		// LEDGER_PAYBILL="+settlReg.getPayBillNo();

		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ " and sal_emp_type="
				+ settlReg.getTypeCode()
				+ " and sal_emp_paybill="
				+ settlReg.getPayBillNo()
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);

	}
	String sal_Amount = "SELECT   SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
			+ year
			+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
			+ emp_id
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledgCode[0][0])
			+ ") "
			+ " WHERE DEBIT_PAYFLAG='Y' ORDER BY SAL_DEBIT_CODE ";
	debit_amount = getSqlModel().getSingleResult(sal_Amount,
			new Object[0][0]);

} catch (Exception e) {
	logger.error(e.getMessage());

}

return debit_amount;

}

// Following function is called emp type or pay bill or both are not
// selected at all.
public Object[][] getSalDebitNoEmpTypePayBill(String emp_id, String month,
	String year, SettlementRegister settlReg, String typeId, String payId) {
Object[][] debit_amount = null;
try {
	String ledgCode = " select distinct sal_ledger_code from hrms_salary_"
			+ year
			+ " inner join hrms_salary_ledger "
			+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
			+ year
			+ ".sal_ledger_code)"
			+ "  where ledger_month="
			+ month
			+ " and ledger_year="
			+ year
			+ "  and sal_emp_type="
			+ typeId
			+ " AND SAL_emp_paybill="
			+ payId
			+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
			+ emp_id;
	Object[][] ledger_code = getSqlModel().getSingleResult(ledgCode,
			new Object[0][0]);

	String selectCredits = "SELECT   SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
			+ year
			+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
			+ emp_id
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledger_code[0][0])
			+ ") "
			+ " WHERE  DEBIT_PAYFLAG='Y' ORDER BY SAL_DEBIT_CODE ";

	debit_amount = getSqlModel().getSingleResult(selectCredits,
			new Object[0][0]);

} catch (Exception e) {
	debit_amount = new Object[0][0];
	logger.error(e.getMessage());

}
return debit_amount;

}

// Following function is called branch or department or both are selected.

public Object[][] getSalDebit(String emp_id, String month, String year,
	SettlementRegister settlReg, String Id) {
Object[][] debit_amount = null;
Object[][] ledgCode = null;
String ledgerCode = null;

try {
	if (!(settlReg.getBranchCode().equals(""))
			&& settlReg.getDeptCode().equals("")) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_BRN="+settlReg.getBranchCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_emp_center="
				+ settlReg.getBranchCode()
				+ " AND SAL_DEPT="
				+ Id
				+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (settlReg.getBranchCode().equals("")
			&& (!(settlReg.getDeptCode().equals("")))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_DEPT="+settlReg.getDeptCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_dept="
				+ settlReg.getDeptCode()
				+ " and sal_emp_center="
				+ Id
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);
	} else if (!(settlReg.getBranchCode().equals(""))
			&& !(settlReg.getDeptCode().equals(""))) {
		// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
		// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
		// LEDGER_BRN="+settlReg.getBranchCode()+" AND
		// LEDGER_DEPT="+settlReg.getDeptCode();
		ledgerCode = " select distinct sal_ledger_code from hrms_salary_"
				+ year
				+ " inner join hrms_salary_ledger "
				+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
				+ year
				+ ".sal_ledger_code)"
				+ "  where ledger_month="
				+ month
				+ " and ledger_year="
				+ year
				+ "  and sal_emp_center="
				+ settlReg.getBranchCode()
				+ " and sal_dept="
				+ settlReg.getDeptCode()
				+ " and LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
				+ emp_id;
		ledgCode = getSqlModel().getSingleResult(ledgerCode,
				new Object[0][0]);

	}
	String sal_Amount = "SELECT   DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
			+ year
			+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
			+ emp_id
			+ " AND SAL_LEDGER_CODE="
			+ String.valueOf(ledgCode[0][0])
			+ ") "
			+ " WHERE DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
	debit_amount = getSqlModel().getSingleResult(sal_Amount,
			new Object[0][0]);

} catch (Exception e) {
	logger.error(e.getMessage());

}

return debit_amount;

}

// Following function is called branch or department not selected at all.
// Following function is called in getReportDataNoBrnDept() method which
// returns the debit head amount and debit code
public Object[][] getSalDebitNotSelect(String emp_id, String month,
	String year, SettlementRegister settlReg, String brnCode, String deptCode) {
Object[][] debit_amount = null;
try {
	String ledgCode = " select distinct sal_ledger_code from hrms_salary_"
			+ year
			+ " inner join hrms_salary_ledger "
			+ "  on(hrms_salary_ledger.ledger_code=hrms_salary_"
			+ year
			+ ".sal_ledger_code)"
			+ "  where ledger_month="
			+ month
			+ " and ledger_year=" + year;
	// UPDATED BY REEBA BEGINS
	if (settlReg.getChkBrnOrder().equals("Y")) {
		ledgCode += "  and sal_emp_center=" + brnCode;
	}
	if (settlReg.getChkDeptOrder().equals("Y")) {
		ledgCode += " AND SAL_DEPT=" + deptCode;
	}
	// UPDATED BY REEBA ENDS
	ledgCode += " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START') AND EMP_ID="
			+ emp_id;
	Object[][] ledger_code = getSqlModel().getSingleResult(ledgCode,
			new Object[0][0]);

	for (int i = 0; i < ledger_code.length; i++) {

		String selectCredits = "SELECT   DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
				+ year
				+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
				+ emp_id
				+ " AND SAL_LEDGER_CODE="
				+ String.valueOf(ledger_code[i][0])
				+ ") "
				+ " WHERE DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE ";

		debit_amount = getSqlModel().getSingleResult(selectCredits,
				new Object[0][0]);
	}
} catch (Exception e) {
	debit_amount = new Object[0][0];
	logger.error(e.getMessage());

}
return debit_amount;

}

/*
* Following function is called in the action class to generate the report.
*/
public void generateReport(SettlementRegister settlReg,
	HttpServletResponse response) {
// String radioChk = settlReg.getRadioChk();

String reportType = settlReg.getReport();
String reportName = "Salary Register_" + settlReg.getDivName() + " for "
		+ Utility.month(Integer.parseInt(settlReg.getMonth())) + "_"
		+ settlReg.getYear();
org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
		reportType, reportName, "A4");
rg.setFName(reportName + "." + reportType);
if (reportType.equals("Pdf")) {
	rg.addTextBold("Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ", 0, 1, 0);
} else {
	Object[][] title = new Object[1][3];
	title[0][0] = "";
	title[0][1] = "";
	title[0][2] = "Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ";

	int[] cols = { 20, 20, 50 };
	int[] align = { 0, 0, 1 };
	rg.tableBodyNoCellBorder(title, cols, align, 1);
}

rg.addText("\n", 0, 1, 0);

if ((!(settlReg.getBranchCode().equals("")) && settlReg.getDeptCode()
		.equals(""))) {
	String selectQuery = "SELECT DEPT_ID,DEPT_NAME from HRMS_DEPT";
	Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
	reportDataBranch(loop_data, rg, settlReg);
} else if (settlReg.getBranchCode().equals("")
		&& (!(settlReg.getDeptCode().equals("")))) {
	String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
	Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
	reportDataDept(loop_data, rg, settlReg);
} else if (!(settlReg.getBranchCode().equals(""))
		&& !(settlReg.getDeptCode().equals(""))) {
	// String selectQuery= "SELECT CENTER_ID,CENTER_NAME FROM
	// HRMS_CENTER";
	Object[][] loop_data = new Object[1][2];
	loop_data[0][0] = settlReg.getBranchCode();
	loop_data[0][1] = settlReg.getDeptCode();
	reportDataBraDept(loop_data, rg, settlReg);
} else if (settlReg.getBranchCode().equals("")
		&& settlReg.getDeptCode().equals("")) {
	String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
	Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
	reportDataNoSelect(loop_data, rg, settlReg);
}

rg.createReport(response);
}

/*
* Following function is called when department is not selected and branch
* selected.
*/
public void reportDataBranch(Object[][] loop_data, ReportGenerator rg,
	SettlementRegister settlReg) {
ArrayList<String> totList = new ArrayList<String>();
int recCount = 0, arrEmpLength = 0;

int check = 0;
if (settlReg.getCheckBrn().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDob().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckBank().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckAccount().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckPan().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckEmpType().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDept().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDesg().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDoj().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckGender().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckHold().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGrade().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}
// UPDATED BY REEBA ENDS


String finalHeader[] = null;
int colTotal = 0;
if(loop_data.length>0){

	for (int a = 0; a < loop_data.length; a++) {

		String selectSalaryLoop = "SELECT HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
				+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
		} else {
			selectSalaryLoop += ",' ' ";
		}
		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
		} else {
			selectSalaryLoop += ",' '";
		}
		selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += ",NVL(RANK_NAME,' ')";

		} else {
			selectSalaryLoop += ",' '";
		}

		selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
				+ " ,CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
				+ settlReg.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC "
				+ " ON HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON"
				+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_LEDGER_CODE "
				+ " AND LEDGER_MONTH="
				+ settlReg.getMonth()
				+ " AND LEDGER_YEAR="
				+ settlReg.getYear()
				+ ") "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_CENTER)";
		}

		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_DEPT)";
		}

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_RANK)";

		}

		/*
		 * String selectSalaryLoop =" SELECT
		 * HRMS_SALARY_"+settlReg.getYear()+".EMP_ID ,EMP_TOKEN ,EMP_FNAME
		 * ||' '||EMP_MNAME ||' '||EMP_LNAME,NVL(TYPE_NAME,'
		 * '),NVL(SAL_DAYS,0) " +" FROM HRMS_SALARY_"+settlReg.getYear()+"
		 * INNER JOIN HRMS_EMP_OFFC " +" ON
		 * HRMS_SALARY_"+settlReg.getYear()+".EMP_ID =
		 * HRMS_EMP_OFFC.EMP_ID " +" LEFT JOIN HRMS_EMP_TYPE
		 * ON(HRMS_SALARY_"+settlReg.getYear()+".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)" +"
		 * INNER JOIN HRMS_SALARY_LEDGER ON " +"
		 * (HRMS_SALARY_LEDGER.LEDGER_CODE =
		 * HRMS_SALARY_"+settlReg.getYear()+".SAL_LEDGER_CODE AND
		 * LEDGER_MONTH="+settlReg.getMonth()+" AND
		 * LEDGER_YEAR="+settlReg.getYear()+") AND
		 * SAL_DIVISION="+settlReg.getDivCode();
		 */
		selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";

		selectSalaryLoop += " WHERE SAL_DIVISION="
				+ settlReg.getDivCode();
		String where = " AND SAL_EMP_CENTER=" + settlReg.getBranchCode()
				+ " ";
		if (!(settlReg.getOnHold().equals("A"))) {
			where += "AND sal_onhold='" + settlReg.getOnHold() + "' ";
		}
		where += "and SAL_DEPT=" + loop_data[a][0];
		if (!settlReg.getTypeCode().equals("")) {
			where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
		}
		if (!settlReg.getDesgCode().equals("")) {
			where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
		}
		where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
		selectSalaryLoop = selectSalaryLoop + where;

		/*
		 * String arrearEmp="SELECT
		 * HRMS_ARREARS_"+settlReg.getYear()+".EMP_ID,ARREARS_CODE FROM
		 * HRMS_ARREARS_"+settlReg.getYear()+" " + " inner join
		 * HRMS_EMP_OFFC on HRMS_EMP_OFFC.emp_id =
		 * HRMS_ARREARS_"+settlReg.getYear()+".emp_id " + " WHERE
		 * ARREARS_PAID_MONTH="+settlReg.getMonth()+" AND
		 * ARREARS_PAID_YEAR="+settlReg.getYear()+"" + " and
		 * arrears_type='M' and EMP_CENTER="+settlReg.getBranchCode()+ "
		 * and EMP_DEPT="+String.valueOf(loop_data[a][0]);
		 */
		try {
			String arrearEmp = " SELECT HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ")"
					+ " INNER JOIN HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE)"
					+ " WHERE ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
					+ " AND SAL_DIVISION=" + settlReg.getDivCode()
					+ " AND SAL_EMP_CENTER=" + settlReg.getBranchCode()
					+ " and SAL_DEPT="
					+ String.valueOf(loop_data[a][0]);

			if (!(settlReg.getOnHold().equals("A"))) {
				arrearEmp += "AND sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			where += "and SAL_DEPT=" + loop_data[a][0];
			if (!settlReg.getTypeCode().equals("")) {
				arrearEmp += " AND SAL_EMP_TYPE="
						+ settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				arrearEmp += " AND SAL_EMP_RANK="
						+ settlReg.getDesgCode();
			}
			Object[][] arrEmpChk = getSqlModel().getSingleResult(
					arrearEmp);
			if (arrEmpChk == null) {
				arrEmpLength = 0;
			} else if (arrEmpChk.length == 0) {
				arrEmpLength = 0;
			} else {
				arrEmpLength = arrEmpChk.length;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		// int b=0;
		Object[][] reportDataPay = getReportData(selectSalaryLoop,
				settlReg, String.valueOf(loop_data[a][0]), arrEmpLength,
				check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
		if (reportDataPay.length > 1) {

			int headerLength = 0;
			int[] cellWidth = null;
			int[] alignment = null;
			if (settlReg.getChkConsSummary().equals("N")) {
				finalHeader = new String[reportDataPay[0].length];
				headerLength = reportDataPay[0].length;
				cellWidth = new int[reportDataPay[0].length];
				alignment = new int[reportDataPay[0].length];

			} else {
				finalHeader = new String[reportDataPay[0].length - 1];
				headerLength = reportDataPay[0].length - 1;
				cellWidth = new int[reportDataPay[0].length - 1];
				alignment = new int[reportDataPay[0].length - 1];
			}
			// String finalHeader[] = new
			// String[reportDataPay[0].length];

			/*
			 * Following loop is used to set the cell width
			 */
			for (int i = 0; i < headerLength; i++) {
				finalHeader[i] = String.valueOf(reportDataPay[0][i]);
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 7;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 8;
					cellWidth[1] = 15;
				}
			}// End of cell width loop

			Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
			/*
			 * Following loop is used to set the credit and debit values
			 */
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = reportDataPay[i + 1][j];
					// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
				}
			}// End of finalData loop

			Object totalByColumn[][] = null;
			String totalHeader[] = new String[finalData.length];
			totalHeader[0] = "";
			// totalHeader[1] = "";
			if (settlReg.getChkConsSummary().equals("N")) {
				totalByColumn = new Object[1][finalData[0].length];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = "No. of Employees:"
						+ finalData.length;
			} else {
				totalByColumn = new Object[1][finalData[0].length - 1];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = finalData.length;
			}

			// Following loop is used to set the sum of the individual
			// credit and debit amount values
			
			if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 1; //check+3-2
			} else if (settlReg.getCheckEmployerPF().equals("Y")) {
				colTotal = check + 2;//check+3-1
			} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 2;//check+3-1
			}else
				colTotal = check + 3;
			
			for (int i = colTotal; i < finalData[0].length; i++) {
				double total = 0.00;
				for (int j = 0; j < finalData.length; j++) {
					if (String.valueOf(finalData[j][i]).equals("null")
							|| String.valueOf(finalData[j][i]).equals(
									"")
							|| String.valueOf(finalData[j][i]).equals(
									"null.00")) {
						finalData[j][i] = "0.00";
					}
					if (String.valueOf(finalData[j][1]).contains(
							"Recovery"))
						total -= Double.parseDouble(String
								.valueOf(finalData[j][i]));
					else
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
				}// End of inner for loop
				// totalHeader[i] = "";
				totList.add(Utility
						.twoDecimals(formatter.format(total)));
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn[0][i] = Utility.twoDecimals(formatter
							.format(total));
				} else {
					totalByColumn[0][i - 1] = Utility
							.twoDecimals(formatter.format(total));
				}
			}// End of outer for loop
			rg.addText("\n", 0, 0, 0);

			/*
			 * Object[][] filterObj = new Object[1][4]; filterObj[0][0] =
			 * "Designation : "; filterObj[0][1] = settlReg.getDesgName();
			 * filterObj[0][2] = "Employee Type : "; filterObj[0][3] =
			 * settlReg.getTypeName();
			 * 
			 * rg.tableBody(filterObj,cellWidth,alignment);
			 * rg.addText("\n",0,0,0);
			 */

			rg.addText("Branch : " + settlReg.getBranchName()
					+ "   Department : " + loop_data[a][1], 0, 0, 0);
			if (settlReg.getChkConsSummary().equals("N")) {
				rg.tableBody(finalHeader, finalData, cellWidth,
						alignment);
				rg.tableBody(totalByColumn, cellWidth, alignment);
			} else {
				// Object[][] summaryObj = new Object[1][1];
				// summaryObj[0][0] = "\n";
				rg.tableBody(finalHeader, totalByColumn, cellWidth,
						alignment);
				// rg.tableBody(totalByColumn, cellWidth, alignment);
			}
			recCount++;
			// colCount=finalData[0].length;
		}// End of reportData if condition
	}// End of loop_data loop

	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = totList.get(arrCount);
				arrCount++;

			}
		}

		
		Object[][] grand_total = null;
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}
		
		
		grand_total[0][0] ="GRAND TOTAL :-";
		grand_total[0][1] =" ";
		grand_total[0][2]=" ";
		grand_total[0][3]=" ";

		/*
		 * Following loop is used to set the grand total values
		 */
		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}// End of inner for loop
			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}
		}// End of outer loop
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);

		rg.addText("\n", 0, 0, 0);

		finalHeader[1]="";
		rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
	}else {
		rg.addText("Records are not available.",0,1,0);

	}
} // End of loop_data condition
}

/*
* Following function is called when department is selected and branch not
* selected.
*/

public void reportDataDept(Object[][] loop_data, ReportGenerator rg,
	SettlementRegister settlReg) {

String finalHeader[] = null;
ArrayList<String> totList = new ArrayList<String>();
int recCount = 0, arrEmpLength = 0;
int check = 0;
if (settlReg.getCheckBrn().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDob().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckBank().equals("Y")) {
	check = check + 1;

}
if (settlReg.getCheckAccount().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckPan().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckEmpType().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDept().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDesg().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDoj().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGender().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckHold().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGrade().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}
// UPDATED BY REEBA ENDS
int colTotal = 0;
if (loop_data.length > 0) {
	for (int a = 0; a < loop_data.length; a++) {

		/*
		 * " SELECT HRMS_SALARY_"+settlReg.getYear()+".EMP_ID ,EMP_TOKEN
		 * ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME, NVL(TYPE_NAME,'
		 * '),NVL(SAL_DAYS,0)" +" FROM HRMS_SALARY_"+settlReg.getYear()+"
		 * INNER JOIN HRMS_EMP_OFFC " +" ON
		 * HRMS_SALARY_"+settlReg.getYear()+".EMP_ID =
		 * HRMS_EMP_OFFC.EMP_ID " +" LEFT JOIN HRMS_EMP_TYPE
		 * ON(HRMS_SALARY_"+settlReg.getYear()+".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)" +"
		 * INNER JOIN HRMS_SALARY_LEDGER ON " +"
		 * (HRMS_SALARY_LEDGER.LEDGER_CODE =
		 * HRMS_SALARY_"+settlReg.getYear()+".SAL_LEDGER_CODE AND
		 * LEDGER_MONTH="+settlReg.getMonth()+" AND
		 * LEDGER_YEAR="+settlReg.getYear()+") WHERE
		 * SAL_DIVISION="+settlReg.getDivCode();
		 * 
		 */

		String selectSalaryLoop = "SELECT HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
				+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
		} else {
			selectSalaryLoop += ",' ' ";
		}
		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
		} else {
			selectSalaryLoop += ",' '";
		}
		selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += ",NVL(RANK_NAME,' ')";

		} else {
			selectSalaryLoop += ",' '";
		}

		selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
				+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
				+ settlReg.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC "
				+ " ON HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON"
				+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_LEDGER_CODE "
				+ " AND LEDGER_MONTH="
				+ settlReg.getMonth()
				+ " AND LEDGER_YEAR="
				+ settlReg.getYear()
				+ ") "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_CENTER)";
		}

		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_DEPT)";
		}

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_RANK)";

		}

		selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
		selectSalaryLoop += " WHERE SAL_DIVISION="
				+ settlReg.getDivCode();

		String where = " AND SAL_EMP_CENTER=" + loop_data[a][0] + " ";
		if (!(settlReg.getOnHold().equals("A"))) {
			where += "and sal_onhold='" + settlReg.getOnHold() + "' ";
		}
		where += "AND SAL_DEPT=" + settlReg.getDeptCode();
		if (!settlReg.getTypeCode().equals("")) {
			where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
		}
		if (!settlReg.getDesgCode().equals("")) {
			where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
		}

		where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

		selectSalaryLoop = selectSalaryLoop + where;
		try {
			String arrearEmp = " SELECT HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ")"
					+ " INNER JOIN HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE)"
					+ " WHERE ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
					+ " AND SAL_DIVISION=" + settlReg.getDivCode()
					+ " AND SAL_EMP_CENTER=" + loop_data[a][0]
					+ " and SAL_DEPT=" + settlReg.getDeptCode();

			if (!(settlReg.getOnHold().equals("A"))) {
				arrearEmp += "AND sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			where += "and SAL_DEPT=" + loop_data[a][0];
			if (!settlReg.getTypeCode().equals("")) {
				arrearEmp += " AND SAL_EMP_TYPE="
						+ settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				arrearEmp += " AND SAL_EMP_RANK="
						+ settlReg.getDesgCode();
			}

			/*
			 * String arrearEmp="SELECT
			 * HRMS_ARREARS_"+settlReg.getYear()+".EMP_ID,ARREARS_CODE
			 * FROM HRMS_ARREARS_"+settlReg.getYear()+" " + " inner join
			 * HRMS_EMP_OFFC on HRMS_EMP_OFFC.emp_id =
			 * HRMS_ARREARS_"+settlReg.getYear()+".emp_id " + " WHERE
			 * ARREARS_PAID_MONTH="+settlReg.getMonth()+" AND
			 * ARREARS_PAID_YEAR="+settlReg.getYear()+"" + " and
			 * arrears_type='M' and EMP_CENTER="+loop_data[a][0]+ " and
			 * EMP_DEPT="+settlReg.getDeptCode();
			 */
			Object[][] arrEmpChk = getSqlModel().getSingleResult(
					arrearEmp);
			if (arrEmpChk == null) {
				arrEmpLength = 0;
			} else if (arrEmpChk.length == 0) {
				arrEmpLength = 0;
			} else {
				arrEmpLength = arrEmpChk.length;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}


		// int aa=0;
		Object[][] reportDataPay = getReportData(selectSalaryLoop,
				settlReg, String.valueOf(loop_data[a][0]), arrEmpLength,
				check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
		// Object[][] reportDataPay =
		// getReportDataDept(selectSalaryLoop,settlReg);
		if (reportDataPay.length > 1) {
			int headerLength = 0;
			int[] cellWidth = null;
			int[] alignment = null;
			if (settlReg.getChkConsSummary().equals("N")) {
				finalHeader = new String[reportDataPay[0].length];
				headerLength = reportDataPay[0].length;
				cellWidth = new int[reportDataPay[0].length];
				alignment = new int[reportDataPay[0].length];
			} else {
				finalHeader = new String[reportDataPay[0].length - 1];
				headerLength = reportDataPay[0].length - 1;
				cellWidth = new int[reportDataPay[0].length - 1];
				alignment = new int[reportDataPay[0].length - 1];
			}
			// String finalHeader[] = new
			// String[reportDataPay[0].length];

			/*
			 * Following loop is used to set the cell width of the table
			 */
			for (int i = 0; i < headerLength; i++) {
				finalHeader[i] = String.valueOf(reportDataPay[0][i]);
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 7;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 8;
					cellWidth[1] = 15;
				}
			}

			Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
			/*
			 * Following loop is used to set the credit and debit head
			 * values.
			 */
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = reportDataPay[i + 1][j];
					// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
				}
			}

			Object totalByColumn[][] = null;
			String totalHeader[] = new String[finalData.length];
			totalHeader[0] = "";
			// totalHeader[1] = "";
			if (settlReg.getChkConsSummary().equals("N")) {
				totalByColumn = new Object[1][finalData[0].length];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = "No. of Employees:"
						+ finalData.length;
			} else {
				totalByColumn = new Object[1][finalData[0].length - 1];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = finalData.length;
			}
			
			if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 1; //check+3-2
			} else if (settlReg.getCheckEmployerPF().equals("Y")) {
				colTotal = check + 2;//check+3-1
			} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 2;//check+3-1
			}else
				colTotal = check + 3;

			for (int i = colTotal; i < finalData[0].length; i++) {
				double total = 0.00;
				/**
				 * Following loop is used to calculate the sum of the
				 * individual credit and debit head amount.
				 */
				for (int j = 0; j < finalData.length; j++) {
					if (String.valueOf(finalData[j][i]).equals("null")) {
						finalData[j][i] = "0.00";
					}

					if (String.valueOf(finalData[j][1]).contains(
							"Recovery"))
						total -= Double.parseDouble(String
								.valueOf(finalData[j][i]));
					else
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
				}// End of inner loop
				// totalHeader[i] = "";
				totList.add(Utility
						.twoDecimals(formatter.format(total)));
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn[0][i] = Utility.twoDecimals(formatter
							.format(total));
				} else {
					totalByColumn[0][i - 1] = Utility
							.twoDecimals(formatter.format(total));
				}
			}// End of outer loop
			rg.addText("\n", 0, 0, 0);

			/*
			 * Object[][] filterObj = new Object[1][4]; filterObj[0][0] =
			 * "Designation : "; filterObj[0][1] = settlReg.getDesgName();
			 * filterObj[0][2] = "Employee Type : "; filterObj[0][3] =
			 * settlReg.getTypeName();
			 * 
			 * rg.tableBody(filterObj,cellWidth,alignment);
			 * rg.addText("\n",0,0,0);
			 */

			rg.addText("Branch : " + loop_data[a][1]
					+ "   Department : " + settlReg.getDeptName(), 0, 0,
					0);
			if (settlReg.getChkConsSummary().equals("N")) {
				rg.tableBody(finalHeader, finalData, cellWidth,
						alignment);
				rg.tableBody(totalByColumn, cellWidth, alignment);
			} else {
				// Object[][] summaryObj = new Object[1][1];
				// summaryObj[0][0] = "\n";
				rg.tableBody(finalHeader, totalByColumn, cellWidth,
						alignment);
				// rg.tableBody(totalByColumn, cellWidth, alignment);
			}
			recCount++;
			// colCount=finalData[0].length;
		}// End of reportDataPay if condition
	} // End of for loop loop_data
	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = totList.get(arrCount);
				arrCount++;

			}
		}

		
		Object[][] grand_total = null;
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}
						
		grand_total[0][0] ="GRAND TOTAL :-";
		grand_total[0][1] =" ";
		

		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			/*
			 * Following loop is used to calculate the grand total
			 * amount
			 */
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}// End of inner loop
			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}
		}// End of outer loop
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);

		rg.addText("\n", 0, 0, 0);
		finalHeader[1]="";
		rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
	} else {
		rg.addText("Records are not available.", 0, 1, 0);

	}
} // End of loop_data if condition
}

/*
* Following function is called to generate the report when both branch and
* department are selected.
*/
public void reportDataBraDept(Object[][] loop_data, ReportGenerator rg,
	SettlementRegister settlReg) {
/*
 * String selectSalaryLoop = " SELECT
 * HRMS_SALARY_"+settlReg.getYear()+".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||'
 * '||EMP_MNAME ||' '||EMP_LNAME,NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) " +"
 * FROM HRMS_SALARY_"+settlReg.getYear()+" INNER JOIN HRMS_EMP_OFFC " +"
 * ON HRMS_SALARY_"+settlReg.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID " +"
 * LEFT JOIN HRMS_EMP_TYPE
 * ON(HRMS_SALARY_"+settlReg.getYear()+".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)" +"
 * INNER JOIN HRMS_SALARY_LEDGER ON" +" (HRMS_SALARY_LEDGER.LEDGER_CODE =
 * HRMS_SALARY_"+settlReg.getYear()+".SAL_LEDGER_CODE " +" AND
 * LEDGER_MONTH="+ settlReg.getMonth()+" AND
 * LEDGER_YEAR="+settlReg.getYear()+") WHERE
 * SAL_DIVISION="+settlReg.getDivCode();
 * 
 *  " SELECT HRMS_SALARY_"+settlReg.getYear()+".EMP_ID ,EMP_TOKEN
 * ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME " + " FROM
 * HRMS_SALARY_"+settlReg.getYear()+" inner join HRMS_EMP_OFFC on
 * HRMS_SALARY_"+settlReg.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID " + "
 * inner join hrms_center on (hrms_center.CENTER_ID=
 * HRMS_EMP_OFFC.EMP_CENTER) "+ " inner join hrms_dept on
 * (hrms_dept.DEPT_ID= HRMS_EMP_OFFC.EMP_DEPT) "+ " WHERE SAL_MONTH="+
 * settlReg.getMonth();
 */
ArrayList<String> totList = new ArrayList<String>();
int recCount = 0, arrEmpLength = 0;

int check = 0;
if (settlReg.getCheckBrn().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDob().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckBank().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckAccount().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckPan().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckEmpType().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDept().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDesg().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDoj().equals("Y")) {
	check = check + 1;

}

if (settlReg.getCheckGender().equals("Y")) {
	check = check + 1;

}

if (settlReg.getCheckHold().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGrade().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA ENDS
String finalHeader[] = null;
int colTotal = 0;
if (loop_data.length > 0) {

	for (int a = 0; a < loop_data.length; a++) {

		String selectSalaryLoop = "SELECT HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
				+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
		} else {
			selectSalaryLoop += ",' ' ";
		}
		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
		} else {
			selectSalaryLoop += ",' '";
		}
		selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += ",NVL(RANK_NAME,' ')";

		} else {
			selectSalaryLoop += ",' '";
		}

		selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
				+ " ,CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
				+ settlReg.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC "
				+ " ON HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON"
				+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
				+ settlReg.getYear()
				+ ".SAL_LEDGER_CODE "
				+ " AND LEDGER_MONTH="
				+ settlReg.getMonth()
				+ " AND LEDGER_YEAR="
				+ settlReg.getYear()
				+ ") "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
		if (settlReg.getCheckBrn().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_CENTER)";
		}

		if (settlReg.getCheckDept().equals("Y")) {
			selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_DEPT)";
		}

		if (settlReg.getCheckDesg().equals("Y")) {
			selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
					+ settlReg.getYear() + ".SAL_EMP_RANK)";

		}

		selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
		selectSalaryLoop += " WHERE SAL_DIVISION="
				+ settlReg.getDivCode();

		String where = " AND SAL_EMP_CENTER=" + loop_data[a][0] + " ";
		if (!(settlReg.getOnHold().equals("A"))) {
			where += " and sal_onhold='" + settlReg.getOnHold() + "' ";
		}
		where += "AND  SAL_DEPT=" + loop_data[a][1];
		if (!settlReg.getTypeCode().equals("")) {
			where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
		}
		if (!settlReg.getDesgCode().equals("")) {
			where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
		}
		where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
		selectSalaryLoop = selectSalaryLoop + where;

		/*
		 * String arrearEmp="SELECT
		 * HRMS_ARREARS_"+settlReg.getYear()+".EMP_ID,ARREARS_CODE FROM
		 * HRMS_ARREARS_"+settlReg.getYear()+" " + " inner join
		 * HRMS_EMP_OFFC on HRMS_EMP_OFFC.emp_id =
		 * HRMS_ARREARS_"+settlReg.getYear()+".emp_id " + " WHERE
		 * ARREARS_PAID_MONTH="+settlReg.getMonth()+" AND
		 * ARREARS_PAID_YEAR="+settlReg.getYear()+"" +
		 *  " and arrears_type='M' and
		 * EMP_CENTER="+settlReg.getBranchCode()+ " and
		 * EMP_DEPT="+settlReg.getDeptCode();
		 */
		try {
			String arrearEmp = " SELECT HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ")"
					+ " INNER JOIN HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE)"
					+ " WHERE ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
					+ " AND SAL_DIVISION=" + settlReg.getDivCode()
					+ " AND SAL_EMP_CENTER=" + settlReg.getBranchCode()
					+ " and SAL_DEPT=" + settlReg.getDeptCode();

			if (!(settlReg.getOnHold().equals("A"))) {
				arrearEmp += "AND sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			where += "and SAL_DEPT=" + loop_data[a][0];
			if (!settlReg.getTypeCode().equals("")) {
				arrearEmp += " AND SAL_EMP_TYPE="
						+ settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				arrearEmp += " AND SAL_EMP_RANK="
						+ settlReg.getDesgCode();
			}
			Object[][] arrEmpChk = getSqlModel().getSingleResult(
					arrearEmp);
			if (arrEmpChk == null) {
				arrEmpLength = 0;
			} else if (arrEmpChk.length == 0) {
				arrEmpLength = 0;
			} else {
				arrEmpLength = arrEmpChk.length;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}


		Object[][] reportDataPay = getReportData(selectSalaryLoop,
				settlReg, String.valueOf(loop_data[a][0]), arrEmpLength,
				check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
		if (reportDataPay.length > 1) {

			int headerLength = 0;
			int[] cellWidth = null;
			int[] alignment = null;
			if (settlReg.getChkConsSummary().equals("N")) {
				finalHeader = new String[reportDataPay[0].length];
				headerLength = reportDataPay[0].length;
				cellWidth = new int[reportDataPay[0].length];
				alignment = new int[reportDataPay[0].length];
			} else {
				finalHeader = new String[reportDataPay[0].length - 1];
				headerLength = reportDataPay[0].length - 1;
				cellWidth = new int[reportDataPay[0].length - 1];
				alignment = new int[reportDataPay[0].length - 1];
			}
			// String finalHeader[] = new
			// String[reportDataPay[0].length];

			/*
			 * Following loop is used to set the cell width of the
			 * table.
			 */
			for (int i = 0; i < headerLength; i++) {
				finalHeader[i] = String.valueOf(reportDataPay[0][i]);
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 7;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 8;
					cellWidth[1] = 15;
				}
			}

			Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
			/*
			 * Following loop is used to set the credit and debit head
			 * amount
			 */
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = reportDataPay[i + 1][j];
					// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
				}// End of inner loop
			}// End of outer loop

			Object totalByColumn[][] = null;
			String totalHeader[] = new String[finalData.length];
			totalHeader[0] = "";
			// totalHeader[1] = "";
			if (settlReg.getChkConsSummary().equals("N")) {
				totalByColumn = new Object[1][finalData[0].length];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = "No. of Employees:"
						+ finalData.length;
			} else {
				totalByColumn = new Object[1][finalData[0].length - 1];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = finalData.length;
			}

			if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 1; //check+3-2
			} else if (settlReg.getCheckEmployerPF().equals("Y")) {
				colTotal = check + 2;//check+3-1
			} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 2;//check+3-1
			}else
				colTotal = check + 3;

			for (int i = colTotal; i < finalData[0].length; i++) {
				double total = 0.00;
				/*
				 * Following loop is used to set the sum of individual
				 * credit and debit head values.
				 */
				for (int j = 0; j < finalData.length; j++) {
					if (String.valueOf(finalData[j][i]).equals("null")) {
						finalData[j][i] = "0.00";
					}
					if (String.valueOf(finalData[j][1]).contains(
							"Recovery"))
						total -= Double.parseDouble(String
								.valueOf(finalData[j][i]));
					else
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
				}// End of inner for loop
				// totalHeader[i] = "";
				totList.add(Utility
						.twoDecimals(formatter.format(total)));
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn[0][i] = Utility.twoDecimals(formatter
							.format(total));
				} else {
					totalByColumn[0][i - 1] = Utility
							.twoDecimals(formatter.format(total));
				}
			}// End of outer for loop
			rg.addText("\n", 0, 0, 0);

			/*
			 * Object[][] filterObj = new Object[1][4]; filterObj[0][0] =
			 * "Designation : "; filterObj[0][1] = settlReg.getDesgName();
			 * filterObj[0][2] = "Employee Type : "; filterObj[0][3] =
			 * settlReg.getTypeName();
			 * 
			 * rg.tableBody(filterObj,cellWidth,alignment);
			 * rg.addText("\n",0,0,0);
			 */

			rg.addText("Branch : " + settlReg.getBranchName()
					+ "   Department : " + settlReg.getDeptName(), 0, 0,
					0);
			if (settlReg.getChkConsSummary().equals("N")) {
				rg.tableBody(finalHeader, finalData, cellWidth,
						alignment);
				rg.tableBody(totalByColumn, cellWidth, alignment);
			} else {
				// Object[][] summaryObj = new Object[1][1];
				// summaryObj[0][0] = "\n";
				rg.tableBody(finalHeader, totalByColumn, cellWidth,
						alignment);
				// rg.tableBody(totalByColumn, cellWidth, alignment);
			}
			recCount++;
			// colCount=finalData[0].length;
		}// End of reportDataPay if condition
	}// End of for loop loop_data
	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = totList.get(arrCount);
				arrCount++;
				// logger.info("-----------------values are
				// listValues["+i+"]["+j+"]="+listValues[i][j]);
			}
		}
		
		Object[][] grand_total = null;
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}
		
		grand_total[0][0] ="GRAND TOTAL :-";
		grand_total[0][1] =" ";
		
		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			/**
			 * Following loop is used to set the grand total values
			 */
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}// End of inner loop
			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}
		}// End of for loop
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);

		rg.addText("\n", 0, 0, 0);
		// rg.addText("GRAND TOTAL",0,0,0);
		finalHeader[1]="";
		rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
	} else {
		rg.addText("Records are not available.", 0, 1, 0);

	}
}// End of loop_data if condition
}

/**
* @author REEBA_JOSEPH
* @param loop_data
* @param rg
* @param settlReg
*/

public void reportOnlyForBranch(Object[][] loop_data,ReportGenerator rg,SettlementRegister settlReg){

ArrayList<String> totList=new ArrayList<String>();
int recCount=0;		
int arrEmpLength=0;
int check=0;
String finalHeader[] = null;
if(settlReg.getCheckBrn().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckDob().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckBank().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckAccount().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckPan().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckEmpType().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckDept().equals("Y")){
	check=check+1;
}

if(settlReg.getCheckDesg().equals("Y")){
	check=check+1;
}

if(settlReg.getCheckDoj().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckGender().equals("Y")){
	check=check+1;
}
if(settlReg.getCheckHold().equals("Y")){
	check=check+1;
}

if(settlReg.getCheckGrade().equals("Y")){
	check=check+1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}
// UPDATED BY REEBA ENDS
int colTotal = 0;
if (loop_data.length > 0) {
	// logger.info("Loop count ============= " + loop_data.length);
	for (int a = 0; a < loop_data.length; a++) {
		// logger.info("For Branch ============= " + loop_data[a][0]);
		String selectSalaryLoop = "";
		try {
			selectSalaryLoop = "SELECT HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
					+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
			} else {
				selectSalaryLoop += ",' ' ";
			}
			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";
			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += ",NVL(RANK_NAME,' ')";

			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
					+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC "
					+ " ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON"
					+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ") "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_CENTER)";
			}
			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_DEPT)";
			}
			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_RANK)";

			}
			selectSalaryLoop += "  LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
			selectSalaryLoop += " WHERE SAL_DIVISION="
					+ settlReg.getDivCode();
			String where = " AND SAL_EMP_CENTER=" + loop_data[a][0]
					+ "";
			if (!(settlReg.getOnHold().equals("A"))) {
				where += " and sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			// where+=" AND SAL_DEPT="+loop_data_inner[b][0];
			if (!settlReg.getTypeCode().equals("")) {
				where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
			}
			where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			selectSalaryLoop = selectSalaryLoop + where;
		} catch (Exception e) {
			logger.error("Error in Salary query : " + e);
			e.printStackTrace();
		}
		try {
			String arrearEmp = " SELECT HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ")"
					+ " INNER JOIN HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE)"
					+ " WHERE ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " and arrears_type IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y'"
					+ " AND SAL_DIVISION=" + settlReg.getDivCode()
					+ " AND SAL_EMP_CENTER=" + loop_data[a][0];
			// +" and SAL_DEPT="+loop_data_inner[b][0];

			Object[][] arrEmpChk = getSqlModel().getSingleResult(
					arrearEmp);
			if (arrEmpChk == null) {
				arrEmpLength = 0;
			} else if (arrEmpChk.length == 0) {
				arrEmpLength = 0;
			} else {
				arrEmpLength = arrEmpChk.length;
			}

		} catch (Exception e) {
			logger.error("Error in arrears query : " + e);
			e.printStackTrace();
		}
		/*
		 * Object[][] reportDataPay = getReportDataNoBranchDept(
		 * selectSalaryLoop, settlReg, String .valueOf(loop_data[a][0]),
		 * String .valueOf(loop_data_inner[b][0]), arrEmpLength, check);
		 */
		Object[][] reportDataPay = null;
		try {
			reportDataPay = getReportDataNoBranchDept(selectSalaryLoop,
					settlReg, String.valueOf(loop_data[a][0]), "0",
					arrEmpLength, check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
		} catch (Exception e) {
			logger.error("Error in getting report data : " + e);
			e.printStackTrace();
		}

		if (reportDataPay.length > 1) {
			
			int headerLength = 0;
			int[] cellWidth = null;
			int[] alignment = null;
			if (settlReg.getChkConsSummary().equals("N")) {
				finalHeader = new String[reportDataPay[0].length];
				headerLength = reportDataPay[0].length;
				cellWidth = new int[reportDataPay[0].length];
				alignment = new int[reportDataPay[0].length];
			} else {
				finalHeader = new String[reportDataPay[0].length - 1];
				headerLength = reportDataPay[0].length - 1;
				cellWidth = new int[reportDataPay[0].length - 1];
				alignment = new int[reportDataPay[0].length - 1];
			}
			// String finalHeader[] = new
			// String[reportDataPay[0].length];

			/*
			 * Following loop is used to set the cell width
			 */
			for (int i = 0; i < headerLength; i++) {
				finalHeader[i] = String.valueOf(reportDataPay[0][i]);
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 7;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 8;
					cellWidth[1] = 15;
				}
			}
			Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
			/*
			 * Following loop is used to set the credit and debit head
			 * values
			 */
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = reportDataPay[i + 1][j];
					// logger.info("finalData["+i+"]["+j+"]=========="+finalData[i][j]);
				}// End of inner loop
			}// End of outer loop
			Object totalByColumn[][] = null;
			String totalHeader[] = new String[finalData.length];
			totalHeader[0] = "";
			// totalHeader[1] = "";
			if (settlReg.getChkConsSummary().equals("N")) {
				totalByColumn = new Object[1][finalData[0].length];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = "No. of Employees:"
						+ finalData.length;
			} else {
				totalByColumn = new Object[1][finalData[0].length - 1];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = finalData.length;
			}
			/**
			 * Following loop is used to set the sum of the individual
			 * credit and debit head values.
			 */
			if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 1; //check+3-2
			} else if (settlReg.getCheckEmployerPF().equals("Y")) {
				colTotal = check + 2;//check+3-1
			} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 2;//check+3-1
			}else
				colTotal = check + 3;

			for (int i = colTotal; i < finalData[0].length; i++) {
				double total = 0;
				for (int j = 0; j < finalData.length; j++) {
					if (String.valueOf(finalData[j][i]).equals("null")
							|| String.valueOf(finalData[j][i]).equals(
									"null.00")
							|| String.valueOf(finalData[j][i]).equals(
									"")
							|| String.valueOf(finalData[j][i]) == null) {
						finalData[j][i] = "0";
					}
					if (String.valueOf(finalData[j][1]).contains(
							"Recovery"))
						total -= Double.parseDouble(String
								.valueOf(finalData[j][i]));
					else
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
				}// End of inner loop
				// totalHeader[i] = "";
				totList.add(Utility
						.twoDecimals(formatter.format(total)));
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn[0][i] = Utility.twoDecimals(formatter
							.format(total));
				} else {
					totalByColumn[0][i - 1] = Utility
							.twoDecimals(formatter.format(total));
				}
			}// End of outer loop
			rg.addText("\n", 0, 0, 0);
			rg.addText("Branch : " + loop_data[a][1], 0, 0, 0);
			if (settlReg.getChkConsSummary().equals("N")) {
				rg.tableBody(finalHeader, finalData, cellWidth,
						alignment);
				rg.tableBody(totalByColumn, cellWidth, alignment);
			} else {
				/*
				 * Object[][] summaryObj = new
				 * Object[1][reportDataPay[0].length-1];
				 * summaryObj[0][0] = " ";
				 */
				/*logger
						.info("finalHeader length=="
								+ finalHeader.length);
				logger.info("cellWidth length==" + cellWidth.length);
				logger.info("alignment length==" + alignment.length);
				logger.info("totalByColumn length=="
						+ totalByColumn[0].length);*/
				rg.tableBody(finalHeader, totalByColumn, cellWidth,
						alignment);

				// rg.tableBody(totalByColumn, cellWidth, alignment);
			}
			recCount++;
		} // End of reportDataPay if condition
	} // End of loop_data loop

	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = formatter.format(Double
						.parseDouble(String.valueOf(totList
								.get(arrCount))));
				arrCount++;

			}
		}

		Object[][] grand_total = null;
		
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}

		grand_total[0][0] = "GRAND TOTAL :-";
		grand_total[0][1] = " ";
		
		//grand_total[0][2] = " ";

		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}
			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}
		}
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);
		
		finalHeader[1]="";
		rg.addText("\n", 0, 0, 0);
		rg.tableBody(finalHeader,grand_total, cellWidth, alignment);
	} else {
		rg.addText("Records are not available.", 0, 1, 0);
	}

} // End of loop_data if condition
}

/*
* Following function is used to set cell width
*/
public int[] getCellWidth(int dataLength) {
int[] cellWidth = new int[dataLength];
for (int i = 0; i < dataLength; i++) {
	if (i > 1) {
		cellWidth[i] = 7;
	} else {
		cellWidth[0] = 8;
		cellWidth[1] = 15;
		cellWidth[2] = 10;
		cellWidth[3] = 7;
	}
}
return cellWidth;
}

/*
* Following function is used to set the alignment
*/
public int[] getAlignment(int dataLength) {
int[] alignment = new int[dataLength];
for (int i = 0; i < dataLength; i++) {
	alignment[i] = 0;
}
return alignment;
}

/**
* following function is called in genReport() function when the branch wise
* and department wise check box is unchecked.
* 
* @param query
* @param settlReg
* @param arrEmpLength
* @param colTotal
* @param settlReg
* @return
*/

public Object[][] getReportDataUnCheck(String query, String fromMonth,
	String fromYear, String toMonth, String toYear, int colTotal, String divisionName) {
int dataIndex = 0;

Object[][] dataWithHeader = null;
try {
	Object emp_id[][] = getEmpIdNew(query);

	Object credit_header[][] = getCreditHeader();
	Object debit_header[][] = getDebitHeader();
	double totArrearAmt = 0;
	// Object debit_recovery[][]= getDebitHeader_recovery();
	int totalCol = credit_header.length + debit_header.length
			+ colTotal + 6 + 5;
	int counter = 0;
	String[] colNames = null;
	
	colNames = new String[totalCol];
	colNames[counter] = "Emp Id";
	counter = counter + 1;
	colNames[counter] = "Employee Name";
	counter = counter + 1;
	colNames[counter] = "Settlement Date";
	counter = counter + 1;

	
	
	/*
	 * Following loop sets the credit head names
	 */
	for (int i = 0; i < credit_header.length; i++) {
		colNames[counter] = String.valueOf(credit_header[i][1]);
		counter++;
	}
	colNames[counter] = "Leave Encashment";
	counter = counter + 1;
	
	colNames[counter] = "Gratuity";
	counter = counter + 1;
	
	colNames[counter] = "Other Reimbursement";
	counter = counter + 1;
	
	colNames[counter] = "Tot Credit";
	counter = counter + 1;
	/*
	 * Following loop sets the debit head names
	 */
	for (int i = 0; i < debit_header.length; i++) {
		colNames[counter] = String.valueOf(debit_header[i][1]);
		counter++;
	}
	
	colNames[counter] = "Other Deduction";
	counter = counter + 1;
	
	colNames[counter] = "Outstanding TDS";
	counter = counter + 1;
	
	colNames[counter] = "Total Debit";
	counter = counter + 1;

	colNames[counter] = "Net-Pay";
	counter = counter + 1;

	
	Object[][] data = new Object[emp_id.length][totalCol];
	

	/*
	 * Following loop sets the emp id,emp name,credit head value,debit
	 * head value and arrear values.
	 */
	for (int i = 0; i < emp_id.length; i++) {
		data[dataIndex][0] = emp_id[i][1];
		data[dataIndex][1] = emp_id[i][2];
		data[dataIndex][2] = emp_id[i][4];
		
		int column = 3;
		double totArrACredit = 0;
		double totArrDebit = 0;
		int position_totalPay = 0;

		
		/** set credit values */

		double total_credit = 0.00;
		
		double employerESIC = 0.0;
		double esicCredit = 0.0;
		double employerPF = 0.0;
		
		Object salCredit[][] = getSalaryCreditDataUncheck(String
				.valueOf(emp_id[i][3]),fromMonth,toMonth,fromYear,toYear);
		/*Object leaveEncashData[][] = getLeaveEncashmentDataUncheck(
				String.valueOf(emp_id[i][0]), year, month);*/
		/*
		 * Following loop sets the credit head values
		 */
		for (int j = 0; j < credit_header.length; j++) {
			esicCredit = 0.0;
			data[dataIndex][column] = 0.00;
			if (!(salCredit == null || salCredit.length == 0))
				for (int k = 0; k < salCredit.length; k++) {
					// UPDATED BY REEBA BEGINS
					try {// totalESICCredit
						if (String.valueOf(salCredit[k][2]).trim()
								.equals("Y")) {
							esicCredit += Double
									.parseDouble(String
											.valueOf(salCredit[k][1]));
							// logger.info("employerESIC========="+employerESIC);
						}
					} catch (Exception e) {
						logger
								.error("Exception in employerESIC :"
										+ e);
					}
					// UPDATED BY REEBA ENDS
					
					/*
					 * Following if condition compares the credit code
					 * from hrms_credit_head table with
					 * hrms_sal_cedits_2008 table
					 */
					if (String.valueOf(credit_header[j][0]).equals(
							String.valueOf(salCredit[k][0]))) {

						if (salCredit[k][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary credit amount will be added
							 * with the arrear credit amount
							 */
							
								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salCredit[k][1]));// +Double.parseDouble("0.00");
								total_credit += Double
										.parseDouble(String
												.valueOf(salCredit[k][1]));
							
						}// End of salCredit if condition

					} // End of credit code comparison if condition

					

				} // End of salCredit for loop
			//logger.info("employerESIC========="
			//		+ esicCredit);

			/*try {
				if (leaveEncashData != null
						|| leaveEncashData.length > 0) {
					for (int k2 = 0; k2 < leaveEncashData.length; k2++) {
						if (String.valueOf(credit_header[j][0]).equals(
								String.valueOf(leaveEncashData[k2][1]))) {
							data[dataIndex][column] = Utility
									.twoDecimals(String
											.valueOf(leaveEncashData[k2][0]));//
							total_credit += Double.parseDouble(String
									.valueOf(leaveEncashData[k2][0]));
						}
					}
				}
			} catch (Exception e) {
				logger.error("exception in leaveEncahData " + e);
				// e.printStackTrace();
			}*/

			data[dataIndex][column] = checkNullToZero(String
					.valueOf(data[dataIndex][column]));
			column++;
		} // End of credit header for loop
		/*
		 * for (int j = 0; j < credit_header.length; j++) {
		 * data[dataIndex][column] = 0.00; if(!(salCredit==null ||
		 * salCredit.length ==0)) for (int k = 0; k < salCredit.length;
		 * k++) {
		 * if(String.valueOf(credit_header[j][0]).equals(String.valueOf(salCredit[k][0]))){
		 * if(salCredit[k][1]!=null) data[dataIndex][column] =
		 * Utility.twoDecimals(String.valueOf(salCredit[k][1]));
		 * total_credit
		 * +=Double.parseDouble(String.valueOf(salCredit[k][1])); } }
		 * 
		 * 
		 * 
		 * 
		 * column++; }
		 */
		data[dataIndex][column++] = checkNullToZero(String
				.valueOf(String.valueOf(emp_id[i][5])));		// leave enachment
		
		data[dataIndex][column++] = checkNullToZero(String
				.valueOf(String.valueOf(emp_id[i][6])));		// gratuity
		data[dataIndex][column++] = checkNullToZero(String
				.valueOf(String.valueOf(emp_id[i][7])));		// other reimbursement
		
		total_credit = total_credit+(Double.parseDouble(String
				.valueOf(emp_id[i][5])))+Double.parseDouble(String
				.valueOf(String.valueOf(emp_id[i][6])))+Double.parseDouble(String
				.valueOf(String.valueOf(emp_id[i][7])));
		
		data[dataIndex][column] = Utility.twoDecimals(formatter
				.format(total_credit));
		
		column = column + 1;
		/** set non-recovery debits */
		Object salDebit[][] = getSalaryDebitDataUncheck(String
				.valueOf(emp_id[i][3]),fromMonth,toMonth,fromYear,toYear);
		double total_nonRecovery = 0;
		// UPDATED BY REEBA BEGINS
		Object[][] esi_addata = null;
		
		
		// UPDATED BY REEBA ENDS
		
		/*
		 * Following loop is used to set the debit head amount values.
		 */
		for (int k = 0; k < debit_header.length; k++) {
			data[dataIndex][column] = 0;
			if (!(salDebit == null || salDebit.length == 0))

				for (int index = 0; index < salDebit.length; index++) {
					/*
					 * Following loop compares the debit code from
					 * hrms_debit_head with the debit code from
					 * hrms_sal_debits_2008(Year entered)
					 */
					if (String.valueOf(debit_header[k][0]).equals(
							String.valueOf(salDebit[index][0]))) {
						if (salDebit[index][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary debit amount will be added
							 * with the arrear debit amount
							 */
							
								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salDebit[index][1]));
								total_nonRecovery += Double
										.parseDouble(String
												.valueOf(salDebit[index][1]));
						}// End of salDebit if condition
					}// End of debit code comparison

					

				} // End of salDebit for loop

			column++;
		}// End of debit header for loop
		/*
		 * for (int k = 0; k < debit_header.length; k++) {
		 * data[dataIndex][column] = Utility.twoDecimals("0");
		 * if(!(salDebit==null || salDebit.length ==0)) for (int index =
		 * 0; index < salDebit.length; index++) {
		 * if(String.valueOf(debit_header[k][0]).equals(String.valueOf(salDebit[index][0]))){
		 * if(salDebit[index][1]!=null) data[dataIndex][column] =
		 * Utility.twoDecimals(String.valueOf(salDebit[index][1]));
		 * total_nonRecovery
		 * +=Double.parseDouble(String.valueOf(salDebit[index][1])); } }
		 * 
		 * 
		 * 
		 * column++; }
		 */
		logger.info("total_nonRecovery=="+total_nonRecovery);
		data[dataIndex][column++] = Utility.twoDecimals(String.valueOf(emp_id[i][8]));			// other deductions
		data[dataIndex][column++] = Utility.twoDecimals(String.valueOf(emp_id[i][9]));			// outstanding tax
		
		total_nonRecovery = total_nonRecovery + (Double.parseDouble(String.valueOf(emp_id[i][8]))+Double.parseDouble(String.valueOf(emp_id[i][9])));
		
		logger.info("total_nonRecovery=="+total_nonRecovery);
		data[dataIndex][column] = formatter.format(total_nonRecovery);
		double total_pay = 0;
		total_pay = total_credit
				- total_nonRecovery;
		column = column + 1;

		if (Double.parseDouble(String.valueOf(total_pay)) < 0) {
			data[dataIndex][column] = "0.00";
		} else {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(total_pay));
		}
		

		position_totalPay = column;
		esicCredit =0;
		employerESIC=0;
		employerPF=0;
		try {
			/**
			 * Following condition is used to select the arrears for the
			 * employees.
			 */
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		dataIndex++;
	} // End of emp_id for loop

	dataWithHeader = new Object[data.length + 1][totalCol];
	/*
	 * Following query is used to set the credit and debit head names.
	 */
	for (int i = 0; i < colNames.length; i++) {
		dataWithHeader[0][i] = colNames[i];

	}

	/*
	 * Following query is used to set the credit and debit head amount.
	 */
	for (int i = 0; i < data.length; i++) {
		for (int j = 0; j < data[0].length; j++) {
			dataWithHeader[i + 1][j] = data[i][j];
		}
	}

} catch (Exception e) {
	e.printStackTrace();
}

return dataWithHeader;
}

// Following function is called when branch is selected or department is
// selected or both got selected for credit and debit details of the
// employee
public Object[][] getReportData(String query, SettlementRegister settlReg,
	String Id, int arrEmpLength, int colTotal, String PFChk, String ESICChk) {
int dataIndex = 0;
String year = settlReg.getYear();
String month = settlReg.getMonth();

Object[][] dataWithHeader = null;
Object[][] arrearCreditAmt = null;
Object[][] arrearDebitAmt = null;
try {
	Object emp_id[][] = getEmpIdNew(query);

	Object credit_header[][] = getCreditHeader();
	Object debit_header[][] = getDebitHeader();
	double totArrearAmt = 0;
	// Object debit_recovery[][]= getDebitHeader_recovery();
	int totalCol = credit_header.length + debit_header.length
			+ colTotal + 6;
	String[] colNames = null;
	int counter = 0;

	// UPDATED BY REEBA BEGINS
	if (settlReg.getChkConsSummary().equals("N")) {
		colNames = new String[totalCol];
		colNames[counter] = "Emp Id";
		counter = counter + 1;
		colNames[counter] = "Employee Name";
		counter = counter + 1;
		colNames[counter] = "Sal Days";
		counter = counter + 1;
	} else {
		colNames = new String[totalCol - 1];
		colNames[counter] = "";
		counter = counter + 1;
		colNames[counter] = "No. of Employees";
		counter = counter + 1;
	}
	// UPDATED BY REEBA ENDS

	/*
	 * If any check box is selected then that name will appear in the
	 * column head of the report except consolidated arrears.
	 */
	if (settlReg.getCheckBrn().equals("Y")) {

		colNames[counter] = "Branch";
		counter = counter + 1;
	}

	if (settlReg.getCheckDept().equals("Y")) {

		colNames[counter] = "Dept";
		counter = counter + 1;
	}

	if (settlReg.getCheckDesg().equals("Y")) {

		colNames[counter] = "Desg";
		counter = counter + 1;
	}

	if (settlReg.getCheckDoj().equals("Y")) {
		colNames[counter] = "Date of\nJoining";
		counter = counter + 1;
	}

	if (settlReg.getCheckDob().equals("Y")) {

		colNames[counter] = "Date of\nBirth";
		counter = counter + 1;
	}
	if (settlReg.getCheckEmpType().equals("Y")) {

		colNames[counter] = "Emp Type";
		counter = counter + 1;
	}

	if (settlReg.getCheckBank().equals("Y")) {

		colNames[counter] = "Bank";
		counter = counter + 1;
	}

	if (settlReg.getCheckAccount().equals("Y")) {

		colNames[counter] = "Acc. No.";
		counter = counter + 1;
	}

	if (settlReg.getCheckPan().equals("Y")) {

		colNames[counter] = "Pan No.";
		counter = counter + 1;
	}

	if (settlReg.getCheckGender().equals("Y")) {
		colNames[counter] = "Gender";
		counter = counter + 1;
	}

	if (settlReg.getCheckHold().equals("Y")) {
		colNames[counter] = "On Hold";
		counter = counter + 1;
	}

	if (settlReg.getCheckGrade().equals("Y")) {
		colNames[counter] = "Grade";
		counter = counter + 1;
	}

	/*
	 * Following loop sets the credit head names
	 */
	for (int i = 0; i < credit_header.length; i++) {
		colNames[counter] = String.valueOf(credit_header[i][1]);
		counter++;
	}

	colNames[counter] = "Tot Credit";
	counter = counter + 1;
	/*
	 * Following loop sets the debit head names
	 */
	for (int i = 0; i < debit_header.length; i++) {
		colNames[counter] = String.valueOf(debit_header[i][1]);
		counter++;
	}
	colNames[counter] = "Tot Debit";
	counter = counter + 1;
	colNames[counter] = "Net-Pay";
	
	// UPDATED BY REEBA BEGINS
	counter = counter + 1;
	if (PFChk.equals("Y")) {
		colNames[counter] = "Employer contribution to PF";
		counter = counter + 1;
	}

	if (ESICChk.equals("Y")) {
		colNames[counter] = "Employer contribution to ESIC";
	}

	if (PFChk.equals("Y") && ESICChk.equals("Y")) {
		colTotal = colTotal - 2;
	} else if (PFChk.equals("Y")) {
		colTotal = colTotal - 1;
	} else if (ESICChk.equals("Y")) {
		colTotal = colTotal - 1;
	}
	// UPDATED BY REEBA ENDS
	
	Object[][] data = null;
	if (settlReg.getCheckFlag().equals("N")) {
		data = new Object[emp_id.length + arrEmpLength][totalCol];
	} else {
		data = new Object[emp_id.length][totalCol];
	}

	/*
	 * Following loop sets the emp id,emp name,credit head value,debit
	 * head value and arrear values.
	 */
	for (int i = 0; i < emp_id.length; i++) {
		data[dataIndex][0] = emp_id[i][1];
		data[dataIndex][1] = emp_id[i][2];
		if (settlReg.getCheckFlag().equals("Y")) {
			String arrearDaysQuery = "SELECT NVL(SUM(ARREARS_DAYS),0)FROM HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_ARREARS_LEDGER "
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					+ " WHERE HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID="
					+ String.valueOf(emp_id[i][0])
					+ " AND ARREARS_TYPE IN ('M','P') AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " AND "
					+ " ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_PAY_IN_SAL = 'Y' ";// +" group by
														// ARREARS_CREDITS_AMT,arrears_net_amt
														// " ;
			Object[][] arrears_days = getSqlModel().getSingleResult(
					arrearDaysQuery);
			if (arrears_days != null || arrears_days.length != 0) {
				data[dataIndex][2] = Double.parseDouble(String
						.valueOf(arrears_days[0][0]))
						+ Double.parseDouble(String
								.valueOf(emp_id[i][4]));

			}

		} else {
			data[dataIndex][2] = emp_id[i][4];
		}

		int column = 3;
		double totArrACredit = 0;
		double totArrDebit = 0;
		int position_totalPay = 0;

		/*
		 * If any check box is selected then the value for that column
		 * will appear in the report.
		 */
		if (settlReg.getCheckBrn().equals("Y")) {
			data[dataIndex][column] = emp_id[i][5];// Branch
			column++;
		}

		if (settlReg.getCheckDept().equals("Y")) {
			data[dataIndex][column] = emp_id[i][6];// Department
			column++;
		}

		if (settlReg.getCheckDesg().equals("Y")) {
			data[dataIndex][column] = emp_id[i][11];// Designation
			column++;
		}

		if (settlReg.getCheckDoj().equals("Y")) {
			data[dataIndex][column] = emp_id[i][12];// Date of Joining
			column++;
		}

		if (settlReg.getCheckDob().equals("Y")) {
			data[dataIndex][column] = emp_id[i][7];// Date of Birth
			column++;
		}
		if (settlReg.getCheckEmpType().equals("Y")) {
			data[dataIndex][column] = emp_id[i][3];// Employee Type
			column++;
		}

		if (settlReg.getCheckBank().equals("Y")) {
			data[dataIndex][column] = emp_id[i][8];// Bank
			column++;
		}

		if (settlReg.getCheckAccount().equals("Y")) {
			data[dataIndex][column] = emp_id[i][9];// Salary Account
													// Number
			column++;
		}

		if (settlReg.getCheckPan().equals("Y")) {
			data[dataIndex][column] = emp_id[i][10];// Pan number
			column++;
		}

		if (settlReg.getCheckGender().equals("Y")) {
			if (String.valueOf(emp_id[i][13]).equals("")
					|| String.valueOf(emp_id[i][13]).equals("null")) {
				data[dataIndex][column] = "";
			} else {
				data[dataIndex][column] = emp_id[i][13];// Gender
			}
			column++;
		}

		if (settlReg.getCheckHold().equals("Y")) {
			data[dataIndex][column] = emp_id[i][14];// On Hold
			column++;
		}

		if (settlReg.getCheckGrade().equals("Y")) {
			data[dataIndex][column] = emp_id[i][15];// Grade
			column++;
		}

		/** set credit values */

		double total_credit = 0.00;
		Object salCredit[][] = getSalaryCreditData(String
				.valueOf(emp_id[i][0]), year, month, settlReg, Id);
		// UPDATED BY REEBA BEGINS
		double employerESIC = 0.0;
		double esicCredit = 0.0;
		double employerPF = 0.0;
		// UPDATED BY REEBA ENDS
		/*
		 * Following loop sets the credit head values
		 */
		for (int j = 0; j < credit_header.length; j++) {
			// UPDATED BY REEBA
			esicCredit = 0.0;
			
			data[dataIndex][column] = 0.00;
			if (!(salCredit == null || salCredit.length == 0))
				for (int k = 0; k < salCredit.length; k++) {
					
					// UPDATED BY REEBA BEGINS
					try {// totalESICCredit
						if (String.valueOf(salCredit[k][2]).trim()
								.equals("Y")) {
							esicCredit += Double
									.parseDouble(String
											.valueOf(salCredit[k][1]));
							// logger.info("employerESIC========="+employerESIC);
						}
					} catch (Exception e) {
						logger
								.error("Exception in employerESIC :"
										+ e);
					}
					// UPDATED BY REEBA ENDS
					
					/*
					 * Following if condition compares the credit code
					 * from hrms_credit_head table with
					 * hrms_sal_cedits_2008 table
					 */
					if (String.valueOf(credit_header[j][0]).equals(
							String.valueOf(salCredit[k][0]))) {

						if (salCredit[k][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary credit amount will be added
							 * with the arrear credit amount
							 */
							if (settlReg.getCheckFlag().equals("Y")) {
								String sql = " SELECT NVL(SUM(ARREARS_AMT),0) from HRMS_ARREARS_CREDIT_"
										+ year
										+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
										+ " (HRMS_ARREARS_CREDIT_"
										+ year
										+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
										+ emp_id[i][0]
										+ ""
										+ " AND ARREARS_PAID_MONTH="
										+ month
										+ " AND ARREARS_PAID_YEAR="
										+ year
										+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_CREDIT_CODE="
										+ salCredit[k][0]
										+ " AND ARREARS_PAY_IN_SAL = 'Y' ";
								Object[][] amt = getSqlModel()
										.getSingleResult(sql);
								if (amt != null || amt.length != 0) {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salCredit[k][1]))
															+ Double
																	.parseDouble(String
																			.valueOf(amt[0][0]))));
									total_credit += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								}
							}// End of checkFlag if condition
							else {
								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salCredit[k][1]));
								total_credit += Double
										.parseDouble(String
												.valueOf(salCredit[k][1]));
							}
						}// End of salCredit if condition

					}// End of credit code comparison if condition
				}// End of salCredit for loop

			column++;
		}// End of credit header for loop
		/*
		 * for (int j = 0; j < credit_header.length; j++) {
		 * data[dataIndex][column] = 0.00; if(!(salCredit==null ||
		 * salCredit.length ==0)) for (int k = 0; k < salCredit.length;
		 * k++) {
		 * if(String.valueOf(credit_header[j][0]).equals(String.valueOf(salCredit[k][0]))){
		 * if(salCredit[k][1]!=null) data[dataIndex][column] =
		 * Utility.twoDecimals(String.valueOf(salCredit[k][1]));
		 * total_credit
		 * +=Double.parseDouble(String.valueOf(salCredit[k][1])); } }
		 * 
		 * 
		 * 
		 * 
		 * column++; }
		 */

		data[dataIndex][column] = Utility.twoDecimals(formatter
				.format(total_credit));

		column = column + 1;
		/** set non-recovery debits */
		Object salDebit[][] = getSalDebit(String.valueOf(emp_id[i][0]),
				month, year, settlReg, Id);
		double total_nonRecovery = 0;
		
		// UPDATED BY REEBA BEGINS
		Object[][] esi_data = null;
		try {
			String esi_query = " SELECT ESI_CODE, ESI_DATE, ESI_COMP_PERCENTAGE, ESI_DEBIT_CODE FROM HRMS_ESI "
					+ " WHERE TO_CHAR(ESI_DATE,'DD-MON-YYYY')  = (SELECT MAX(ESI_DATE) FROM HRMS_ESI "
					+ " WHERE TO_CHAR(ESI_DATE,'YYYY-MM') <= '"
					+ year
					+ "-" + month + "') ";
			esi_data = getSqlModel().getSingleResult(esi_query);

		} catch (Exception e) {
			logger.error("Error in calculation employer ESIC: " + e);
		}
		
		Object[][] pf_data = null;
		try {
			String pf_query = " SELECT PF_CODE, PF_DATE, PF_PERCENTAGE, PF_DEBIT_CODE FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'DD-MON-YYYY')  = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'YYYY-MM') <='"
				+ year
				+ "-" + month + "') ";
			pf_data = getSqlModel().getSingleResult(pf_query);
		} catch (Exception e) {
			logger.error("Error in calculation employer PF: " + e);
		}
		// UPDATED BY REEBA ENDS
		
		
		/*
		 * Following loop is used to set the debit head amount values.
		 */
		for (int k = 0; k < debit_header.length; k++) {
			data[dataIndex][column] = 0;
			if (!(salDebit == null || salDebit.length == 0))

				for (int index = 0; index < salDebit.length; index++) {
					/*
					 * Following loop compares the debit code from
					 * hrms_debit_head with the debit code from
					 * hrms_sal_debits_2008(Year entered)
					 */
					if (String.valueOf(debit_header[k][0]).equals(
							String.valueOf(salDebit[index][0]))) {
						if (salDebit[index][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary debit amount will be added
							 * with the arrear debit amount
							 */
							if (settlReg.getCheckFlag().equals("Y")) {
								String sql = " SELECT NVL(SUM(ARREARS_AMT),0) from HRMS_ARREARS_DEBIT_"
										+ year
										+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
										+ " (HRMS_ARREARS_DEBIT_"
										+ year
										+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
										+ emp_id[i][0]
										+ ""
										+ " AND ARREARS_PAID_MONTH="
										+ month
										+ " AND ARREARS_PAID_YEAR="
										+ year
										+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_DEBIT_CODE="
										+ salDebit[index][0]
										+ "AND ARREARS_PAY_IN_SAL = 'Y'";
								Object[][] amt = getSqlModel()
										.getSingleResult(sql);
								if (amt != null || amt.length != 0) {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salDebit[index][1]))
															+ Double
																	.parseDouble(String
																			.valueOf(amt[0][0]))));
									total_nonRecovery += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								} else {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salDebit[index][1]))
															+ Double
																	.parseDouble("0.00")));
									total_nonRecovery += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								}

							}// End of check flag condition

							else {

								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salDebit[index][1]));
								total_nonRecovery += Double
										.parseDouble(String
												.valueOf(salDebit[index][1]));
							}
						}// End of salDebit if condition
					}// End of debit code comparison
					
					// UPDATED BY REEBA BEGINS
					if (esi_data != null && esi_data.length > 0) {
						if (String.valueOf(esi_data[0][3]).equals(
								String.valueOf(salDebit[index][0]))) {
							if (Double.parseDouble(String
									.valueOf(salDebit[index][1])) > 0){
								employerESIC = esicCredit
										* Double
												.parseDouble(String
														.valueOf(esi_data[0][2]))
										/ 100;
							}else
								employerESIC = 0.0;
							
							
						}
					}
					
					if (pf_data != null && pf_data.length > 0) {
						if (String.valueOf(pf_data[0][3]).equals(
								String.valueOf(salDebit[index][0]))) {
							employerPF = Double.parseDouble(String.valueOf(salDebit[index][1]));
						}
					}
					// UPDATED BY REEBA ENDS

					
					
				}// End of salDebit for loop

			column++;
		}// End of debit header for loop
		/*
		 * for (int k = 0; k < debit_header.length; k++) {
		 * data[dataIndex][column] = Utility.twoDecimals("0");
		 * if(!(salDebit==null || salDebit.length ==0)) for (int index =
		 * 0; index < salDebit.length; index++) {
		 * if(String.valueOf(debit_header[k][0]).equals(String.valueOf(salDebit[index][0]))){
		 * if(salDebit[index][1]!=null) data[dataIndex][column] =
		 * Utility.twoDecimals(String.valueOf(salDebit[index][1]));
		 * total_nonRecovery
		 * +=Double.parseDouble(String.valueOf(salDebit[index][1])); } }
		 * 
		 * 
		 * 
		 * column++; }
		 */
		data[dataIndex][column] = Utility.twoDecimals(formatter
				.format(total_nonRecovery));

		double total_pay = 0;
		total_pay = Double.parseDouble(String
				.valueOf(data[dataIndex][credit_header.length + 3
						+ colTotal]))
				- Double.parseDouble(String
						.valueOf(data[dataIndex][column]));
		column = column + 1;
		if (Double.parseDouble(Utility.twoDecimals(total_pay)) < 0) {
			data[dataIndex][column] = Utility.twoDecimals("0");
		} else {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(total_pay));
		}
		
		
		// UPDATED BY REEBA BEGINS
		column = column + 1;

		if (PFChk.equals("Y")) {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(employerPF));// Employer contribution to
											// PF
			column++;
		}

		if (ESICChk.equals("Y")) {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(employerESIC));
			;// Employer contribution to ESIC
		}
		// UPDATED BY REEBA ENDS
		

		position_totalPay = column;
		employerESIC = 0;
		employerPF =0;
		esicCredit=0;
		try {
			/**
			 * Following condition is used to select the arrears for the
			 * employees.
			 */
			if (settlReg.getCheckFlag().equals("N")) {
				if (arrEmpLength != 0) {
					String arrear = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,ARREARS_TYPE,ARREARS_PROMCODE,DECODE(NVL(ARREARS_PAY_TYPE,'ADD'),'ADD','Arrears',"
							+ " 'DED','Recovery') FROM HRMS_ARREARS_"
							+ settlReg.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ settlReg.getYear()
							+ ".ARREARS_CODE) WHERE ARREARS_PAID_MONTH="
							+ settlReg.getMonth()
							+ " AND ARREARS_PAID_YEAR="
							+ settlReg.getYear()
							+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' AND EMP_ID="
							+ String.valueOf(emp_id[i][0])
							+ " ORDER BY ARREARS_MONTH,ARREARS_YEAR";
					Object[][] arrearCode = getSqlModel()
							.getSingleResult(arrear);
					if (!(arrearCode == null || arrearCode.length == 0)) {

						for (int arrCode = 0; arrCode < arrearCode.length; arrCode++) {
							totArrACredit = 0;
							totArrDebit = 0;
							if (String.valueOf(arrearCode[arrCode][5])
									.equals("")
									|| String.valueOf(
											arrearCode[arrCode][5])
											.equals("null")) {
								arrearCreditAmt = arrearCreditData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										credit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										year);
								arrearDebitAmt = arrearDebitData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										debit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										year);
							} else {
								arrearDebitAmt = arrearDebitData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										debit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										String
												.valueOf(arrearCode[arrCode][5]),
										year);
								arrearCreditAmt = arrearCreditData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										credit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										String
												.valueOf(arrearCode[arrCode][5]),
										year);
							}

							String arrMonth = Utility
									.month(Integer
											.parseInt(String
													.valueOf(arrearCode[arrCode][1])));

							int arrearCol = 0;
							data[dataIndex + 1][arrearCol] = ""
									+ emp_id[i][1];// Emp id
							arrearCol = arrearCol + 1;
							data[dataIndex + 1][arrearCol] = ""
									+ emp_id[i][2]
									+ "\n"
									+ String
											.valueOf(arrearCode[arrCode][6])
									+ " for "
									+ arrMonth
									+ "-"
									+ String
											.valueOf(arrearCode[arrCode][2]);// Employee
																				// Name
							arrearCol = arrearCol + 1;
							if (String.valueOf(arrearCode[arrCode][3])
									.equals("null")
									|| String.valueOf(
											arrearCode[arrCode][3])
											.equals("")) {
								data[dataIndex + 1][arrearCol] = "";// Arrears
																	// Days
							} else {
								data[dataIndex + 1][arrearCol] = ""
										+ String
												.valueOf(arrearCode[arrCode][3]);
							}
							arrearCol = arrearCol + 1;
							/*
							 * Following conditions set the values for
							 * the selected check boxes in the salary
							 * register report.
							 */
							if (settlReg.getCheckBrn().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][5];// Branch
								arrearCol++;
							}

							if (settlReg.getCheckDept().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][6];// Department
								arrearCol++;
							}

							if (settlReg.getCheckDesg().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][11];// Designation
								arrearCol++;
							}

							if (settlReg.getCheckDoj().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][12];// Date
																				// of
																				// Joining
								arrearCol++;
							}

							if (settlReg.getCheckDob().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][7];// Date
																				// of
																				// Birth
								arrearCol++;
							}
							if (settlReg.getCheckEmpType().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][3];// Employee
																				// Type
								arrearCol++;
							}

							if (settlReg.getCheckBank().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][8];// Bank
																				// Name
								arrearCol++;
							}

							if (settlReg.getCheckAccount().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][9];// Salary
																				// Account
																				// No.
								arrearCol++;
							}

							if (settlReg.getCheckPan().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][10];// Pan
																				// Number
								arrearCol++;
							}

							if (settlReg.getCheckGender().equals("Y")) {
								if (String.valueOf(emp_id[i][13])
										.equals("")
										|| String
												.valueOf(emp_id[i][13])
												.equals("null")) {
									data[dataIndex + 1][arrearCol] = "";
								} else {
									data[dataIndex + 1][arrearCol] = emp_id[i][13];// Gender
								}
								arrearCol++;
							}

							if (settlReg.getCheckHold().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][14];// On
																				// Hold
								arrearCol++;
							}

							if (settlReg.getCheckGrade().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][15];// Grade
								arrearCol++;
							}
							/*
							 * Following loop is used to set the arrear
							 * credit amount from
							 * HRMS_ARREARS_CREDIT_2008(*Year Entered)
							 * table
							 */
							for (int ac = 0; ac < arrearCreditAmt.length; ac++) {

								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals(String
												.valueOf(arrearCreditAmt[ac][0]));
								if (String.valueOf(
										arrearCreditAmt[ac][0]).equals(
										"null")
										|| String.valueOf(
												arrearCreditAmt[ac][0])
												.equals("")
										|| String.valueOf(
												arrearCreditAmt[ac][0])
												.equals("")) {
									totArrACredit += Double
											.parseDouble(String
													.valueOf("0.00"));
								} else {
									totArrACredit += Double
											.parseDouble(String
													.valueOf(arrearCreditAmt[ac][0]));
									if (String.valueOf(arrearCreditAmt[ac][1]).trim()
											.equals("Y")) {
										esicCredit += Double
												.parseDouble(String
														.valueOf(arrearCreditAmt[ac][0]));
									}
								}
								arrearCol++;
							}

							data[dataIndex + 1][arrearCol] = Utility
									.twoDecimals(formatter
											.format(totArrACredit));
							arrearCol = arrearCol + 1;

							/*
							 * Following loop is used to set the arrear
							 * debit amount from HRMS_ARREARS_DEBIT_2008
							 */
							for (int ad = 0; ad < arrearDebitAmt.length; ad++) {

								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals(String
												.valueOf(arrearDebitAmt[ad][0]));
								if (String.valueOf(
										arrearDebitAmt[ad][0]).equals(
										"null")
										|| String.valueOf(
												arrearDebitAmt[ad][0])
												.equals("")) {
									totArrDebit += Double
											.parseDouble(String
													.valueOf("0.00"));
								} else {
									totArrDebit += Double
											.parseDouble(String
													.valueOf(arrearDebitAmt[ad][0]));
								}

								arrearCol++;
								if (esi_data != null && esi_data.length > 0) {
									if (String.valueOf(esi_data[0][3]).equals(
											String.valueOf(arrearDebitAmt[ad][1]))) {
										if (Double.parseDouble(String
												.valueOf(arrearDebitAmt[ad][0])) > 0){
											employerESIC = esicCredit
													* Double
															.parseDouble(String
																	.valueOf(esi_data[0][2]))
													/ 100;
										}else
											employerESIC = 0.0;
										
										
									}
								}
								
								if (pf_data != null && pf_data.length > 0) {
									if (String.valueOf(pf_data[0][3]).equals(
											String.valueOf(arrearDebitAmt[ad][1]))) {
										employerPF = Double.parseDouble(String.valueOf(arrearDebitAmt[ad][0]));
									}
								}
							}

							data[dataIndex + 1][arrearCol] = Utility
									.twoDecimals(formatter
											.format(totArrDebit));

							totArrearAmt = Double
									.parseDouble(String
											.valueOf(data[dataIndex + 1][credit_header.length
													+ colTotal + 3]))
									- Double
											.parseDouble(String
													.valueOf(data[dataIndex + 1][arrearCol]));

							// data[dataIndex+1][arrearCol]=Utility.twoDecimals(totArrDebit);

							arrearCol = arrearCol + 1;

							if (Double.parseDouble(String
									.valueOf(totArrearAmt)) < 0) {

								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals("0.00");
							} else {

								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals(formatter
												.format(totArrearAmt));
							}
							
							// UPDATED BY REEBA BEGINS
							arrearCol++;
							if (PFChk.equals("Y")) {
								data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
										.format(employerPF));			// Employer
																		// contribution
																		// to
																		// PF
								arrearCol++;
							}

							if (ESICChk.equals("Y")) {
								data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
										.format(employerESIC));	;// Employer
																		// contribution
																		// to
																		// ESIC
							}
							// UPDATED BY REEBA ENDS
							

							dataIndex++;
						}// End of arrear code for loop
					}// End of arrear code if condition
				}// End of arrearEmpLength if condition
			}// End of check flag
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		dataIndex++;
	}// End of emp_id for loop

	dataWithHeader = new Object[data.length + 1][totalCol];
	/*
	 * Following query is used to set the credit and debit head names.
	 */
	for (int i = 0; i < colNames.length; i++) {
		dataWithHeader[0][i] = colNames[i];
	}

	/*
	 * Following query is used to set the credit and debit head amount.
	 */
	for (int i = 0; i < data.length; i++) {
		for (int j = 0; j < data[0].length; j++) {
			dataWithHeader[i + 1][j] = data[i][j];
		}
	}

	String finalHeader[] = new String[dataWithHeader[0].length];
	int[] cellWidth = new int[dataWithHeader[0].length];
	int[] alignment = new int[dataWithHeader[0].length];
	for (int i = 0; i < dataWithHeader[0].length; i++) {
		finalHeader[i] = (String) dataWithHeader[0][i];
		alignment[i] = 0;
		if (i > 1) {
			cellWidth[i] = 7;
			alignment[i] = 0;
		} else {
			cellWidth[0] = 8;
			cellWidth[1] = 15;
			cellWidth[2] = 10;
			cellWidth[3] = 7;
		}
	}
	Object finalData[][] = new Object[dataWithHeader.length - 1][dataWithHeader[0].length];
	for (int i = 0; i < finalData.length; i++) {
		for (int j = 0; j < finalData[0].length; j++) {
			finalData[i][j] = dataWithHeader[i + 1][j];

		}
	}
} catch (Exception e) {
	logger.error(e.getMessage());
}

return dataWithHeader;
}

/*
* Following function is called in the reportDatanoSelect() method.This
* method returns the emp id,emp name,credit head,debit head and values and
* also the arrear value if any.
*/
public Object[][] getReportDataNoBranchDept(String query,
	SettlementRegister settlReg, String brnCode, String deptCode,
	int arrEmpLength, int colTotal, String PFChk, String ESICChk) {
int dataIndex = 0;
String year = settlReg.getYear();
String month = settlReg.getMonth();
Object[][] dataWithHeader = null;
Object[][] arrearCreditAmt = null;
Object[][] arrearDebitAmt = null;
try {

	Object emp_id[][] = getEmpIdNew(query);

	Object credit_header[][] = getCreditHeader();
	Object debit_header[][] = getDebitHeader();
	double totArrearAmt = 0;
	// Object debit_recovery[][]= getDebitHeader_recovery();
	int totalCol = credit_header.length + debit_header.length
			+ colTotal + 6;
	int counter = 0;
	String[] colNames = null;
	// UPDATED BY REEBA BEGINS
	if (settlReg.getChkConsSummary().equals("N")) {
		colNames = new String[totalCol];
		colNames[counter] = "Emp Id";
		counter = counter + 1;
		colNames[counter] = "Employee Name";
		counter = counter + 1;
		colNames[counter] = "Sal Days";
		counter = counter + 1;
	} else {
		colNames = new String[totalCol - 1];
		colNames[counter] = "";
		counter = counter + 1;
		colNames[counter] = "No. of Employees";
		counter = counter + 1;
	}
	// UPDATED BY REEBA ENDS

	if (settlReg.getCheckBrn().equals("Y")) {
		colNames[counter] = "Branch";
		counter = counter + 1;
	}

	if (settlReg.getCheckDept().equals("Y")) {

		colNames[counter] = "Dept";
		counter = counter + 1;
	}
	if (settlReg.getCheckDesg().equals("Y")) {
		colNames[counter] = "Desg";
		counter = counter + 1;
	}

	if (settlReg.getCheckDoj().equals("Y")) {

		colNames[counter] = "Date Of\nJoining";
		counter = counter + 1;
	}

	if (settlReg.getCheckDob().equals("Y")) {

		colNames[counter] = "Date of\nBirth";
		counter = counter + 1;
	}
	if (settlReg.getCheckEmpType().equals("Y")) {

		colNames[counter] = "Emp Type";
		counter = counter + 1;
	}

	if (settlReg.getCheckBank().equals("Y")) {

		colNames[counter] = "Bank";
		counter = counter + 1;
	}

	if (settlReg.getCheckAccount().equals("Y")) {

		colNames[counter] = "Acc. No.";
		counter = counter + 1;
	}

	if (settlReg.getCheckPan().equals("Y")) {

		colNames[counter] = "Pan No.";
		counter = counter + 1;
	}

	if (settlReg.getCheckGender().equals("Y")) {

		colNames[counter] = "Gender";
		counter = counter + 1;
	}

	if (settlReg.getCheckHold().equals("Y")) {

		colNames[counter] = "On Hold";
		counter = counter + 1;
	}

	if (settlReg.getCheckGrade().equals("Y")) {

		colNames[counter] = "Grade";
		counter = counter + 1;
	}

	/*
	 * Following loop sets the credit head names
	 */
	for (int i = 0; i < credit_header.length; i++) {
		colNames[counter] = String.valueOf(credit_header[i][1]);
		counter++;
	}

	colNames[counter] = "TOT CREDIT";
	counter = counter + 1;

	/*
	 * Following loop sets the debit head names
	 */
	for (int i = 0; i < debit_header.length; i++) {
		colNames[counter] = String.valueOf(debit_header[i][1]);
		counter++;
	}
	colNames[counter] = "TOT DEBIT";
	counter = counter + 1;
	colNames[counter] = "NET-PAY";
	
	// UPDATED BY REEBA BEGINS
	counter = counter + 1;
	if (PFChk.equals("Y")) {
		colNames[counter] = "Employer contribution to PF";
		counter = counter + 1;
	}

	if (ESICChk.equals("Y")) {
		colNames[counter] = "Employer contribution to ESIC";
	}

	if (PFChk.equals("Y") && ESICChk.equals("Y")) {
		colTotal = colTotal - 2;
	} else if (PFChk.equals("Y")) {
		colTotal = colTotal - 1;
	} else if (ESICChk.equals("Y")) {
		colTotal = colTotal - 1;
	}
	// UPDATED BY REEBA ENDS
	
	Object[][] data = null;
	if (settlReg.getCheckFlag().equals("N")) {
		data = new Object[emp_id.length + arrEmpLength][totalCol];
	} else {
		data = new Object[emp_id.length][totalCol];
	}
	/*
	 * Following loop sets the corresponding values to the headers
	 */
	for (int i = 0; i < emp_id.length; i++) {
		data[dataIndex][0] = emp_id[i][1];
		data[dataIndex][1] = emp_id[i][2];

		if (settlReg.getCheckFlag().equals("Y")) {
			String arrearDaysQuery = "SELECT NVL(SUM(ARREARS_DAYS),0)FROM HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_ARREARS_LEDGER "
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
					+ " WHERE HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID="
					+ String.valueOf(emp_id[i][0])
					+ " AND ARREARS_TYPE IN ('M','P') AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="
					+ settlReg.getYear()
					+ " AND "
					+ " ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ "AND ARREARS_PAY_IN_SAL = 'Y' ";// +" group by
														// ARREARS_CREDITS_AMT,arrears_net_amt
														// " ;
			Object[][] arrears_days = getSqlModel().getSingleResult(
					arrearDaysQuery);
			if (arrears_days != null || arrears_days.length != 0) {
				data[dataIndex][2] = Double.parseDouble(String
						.valueOf(arrears_days[0][0]))
						+ Double.parseDouble(String
								.valueOf(emp_id[i][4]));

			}

		} else {
			data[dataIndex][2] = emp_id[i][4];
		}
		// data[dataIndex][3]=emp_id[i][4];

		int column = 3;
		if (settlReg.getCheckBrn().equals("Y")) {
			data[dataIndex][column] = emp_id[i][5];
			column++;
		}

		if (settlReg.getCheckDept().equals("Y")) {
			data[dataIndex][column] = emp_id[i][6];
			column++;
		}

		if (settlReg.getCheckDesg().equals("Y")) {
			data[dataIndex][column] = emp_id[i][11];
			column++;
		}

		if (settlReg.getCheckDoj().equals("Y")) {
			data[dataIndex][column] = emp_id[i][12];
			column++;
		}

		if (settlReg.getCheckDob().equals("Y")) {
			data[dataIndex][column] = emp_id[i][7];
			column++;
		}
		if (settlReg.getCheckEmpType().equals("Y")) {
			data[dataIndex][column] = emp_id[i][3];
			column++;
		}

		if (settlReg.getCheckBank().equals("Y")) {
			data[dataIndex][column] = emp_id[i][8];
			column++;
		}

		if (settlReg.getCheckAccount().equals("Y")) {
			data[dataIndex][column] = emp_id[i][9];
			column++;
		}

		if (settlReg.getCheckPan().equals("Y")) {
			data[dataIndex][column] = emp_id[i][10];
			column++;
		}

		if (settlReg.getCheckGender().equals("Y")) {
			if (String.valueOf(emp_id[i][13]).equals("")
					|| String.valueOf(emp_id[i][13]).equals("null")) {
				data[dataIndex][column] = "";
			} else {
				data[dataIndex][column] = emp_id[i][13];// Gender
			}
			column++;
		}

		if (settlReg.getCheckHold().equals("Y")) {
			data[dataIndex][column] = emp_id[i][14];// On Hold
			column++;
		}

		if (settlReg.getCheckGrade().equals("Y")) {
			data[dataIndex][column] = emp_id[i][15];// Grade
			column++;
		}
		double totArrACredit = 0;
		double totArrDebit = 0;
		int position_totalPay = 0;
		/** set credit values */

		double total_credit = 0.00;
		
		// UPDATED BY REEBA BEGINS
		double employerESIC = 0.0;
		double esicCredit = 0.0;
		double employerPF = 0.0;
		// UPDATED BY REEBA ENDS
		
		Object salCredit[][] = getSalaryCreditDataNoSelect(String
				.valueOf(emp_id[i][0]), year, month, settlReg, brnCode,
				deptCode);
		// getSalaryCreditData(String.valueOf(emp_id[i][0]), year,
		// month,settlReg,Id) ;

		/*
		 * Following query is used to set the credit head amount
		 */
		for (int j = 0; j < credit_header.length; j++) {

			// UPDATED BY REEBA
			esicCredit = 0.0;
			
			data[dataIndex][column] = 0.00;
			if (!(salCredit == null || salCredit.length == 0))
				/**
				 * Following loop compares the credit code from
				 * HRMS_CREDIT_HEAD with HRMS_SAL_CREDIT_2008(Year
				 * Entered)
				 */
				for (int k = 0; k < salCredit.length; k++) {
					
					// UPDATED BY REEBA BEGINS
					try {// totalESICCredit
						if (String.valueOf(salCredit[k][2]).trim()
								.equals("Y")) {
							esicCredit += Double
									.parseDouble(String
											.valueOf(salCredit[k][1]));
							// logger.info("employerESIC========="+employerESIC);
						}
					} catch (Exception e) {
						logger
								.error("Exception in employerESIC :"
										+ e);
					}
					// UPDATED BY REEBA ENDS

					if (String.valueOf(credit_header[j][0]).equals(
							String.valueOf(salCredit[k][0]))) {

						if (salCredit[k][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary credit amount will be added
							 * with the arrear credit amount
							 */
							if (settlReg.getCheckFlag().equals("Y")) {
								String sql = " SELECT NVL(SUM(ARREARS_AMT),0) from HRMS_ARREARS_CREDIT_"
										+ year
										+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
										+ " (HRMS_ARREARS_CREDIT_"
										+ year
										+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
										+ emp_id[i][0]
										+ ""
										+ " AND ARREARS_PAID_MONTH="
										+ month
										+ " AND ARREARS_PAID_YEAR="
										+ year
										+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_CREDIT_CODE="
										+ salCredit[k][0]
										+ " AND ARREARS_PAY_IN_SAL = 'Y' ";

								Object[][] amt = getSqlModel()
										.getSingleResult(sql);
								if (amt != null || amt.length != 0) {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salCredit[k][1]))
															+ Double
																	.parseDouble(String
																			.valueOf(amt[0][0]))));
									total_credit += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								}
							}// End of the check flag condition
							else {
								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salCredit[k][1]));
								total_credit += Double
										.parseDouble(String
												.valueOf(salCredit[k][1]));
							}
						}// End of Salcredit condition

					}
				}// End of inner for loop

			column++;
		}// End of outer for loop

		data[dataIndex][column] = Utility.twoDecimals(formatter
				.format(total_credit));

		column = column + 1;
		/** set non-recovery debits */
		Object salDebit[][] = getSalDebitNotSelect(String
				.valueOf(emp_id[i][0]), month, year, settlReg, brnCode,
				deptCode);
		// getSalDebit(String.valueOf(emp_id[i][0]),month,year,settlReg,Id)
		// ;
		double total_nonRecovery = 0;
		// UPDATED BY REEBA BEGINS
		Object[][] esi_data = null;
		try {
			String esi_query = " SELECT ESI_CODE, ESI_DATE, ESI_COMP_PERCENTAGE, ESI_DEBIT_CODE FROM HRMS_ESI "
					+ " WHERE TO_CHAR(ESI_DATE,'DD-MON-YYYY')  = (SELECT MAX(ESI_DATE) FROM HRMS_ESI "
					+ " WHERE TO_CHAR(ESI_DATE,'YYYY-MM') <= '"
					+ year
					+ "-" + month + "') ";
			esi_data = getSqlModel().getSingleResult(esi_query);

		} catch (Exception e) {
			logger.error("Error in calculation employer ESIC: " + e);
		}
		
		Object[][] pf_data = null;
		try {
			String pf_query = " SELECT PF_CODE, PF_DATE, PF_PERCENTAGE, PF_DEBIT_CODE FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'DD-MON-YYYY')  = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'YYYY-MM') <='"
				+ year
				+ "-" + month + "') ";
			pf_data = getSqlModel().getSingleResult(pf_query);
		} catch (Exception e) {
			logger.error("Error in calculation employer PF: " + e);
		}
		// UPDATED BY REEBA ENDS
		
		
		/*
		 * Following loop is used to set the debit head amount.
		 */
		for (int k = 0; k < debit_header.length; k++) {
			data[dataIndex][column] = 0;
			if (!(salDebit == null || salDebit.length == 0))
				/*
				 * Following loop compares the debit code from
				 * hrms_debit_head with the debit code from
				 * hrms_sal_debits_2008(Year entered)
				 */
				for (int index = 0; index < salDebit.length; index++) {
					if (String.valueOf(debit_header[k][0]).equals(
							String.valueOf(salDebit[index][0]))) {
						if (salDebit[index][1] != null) {
							/*
							 * Following if condition checks if
							 * consolidated arrears check box is checked
							 * then salary debit amount will be added
							 * with the arrear debit amount
							 */
							if (settlReg.getCheckFlag().equals("Y")) {
								String sql = " SELECT NVL(SUM(ARREARS_AMT),0) from HRMS_ARREARS_DEBIT_"
										+ year
										+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
										+ " (HRMS_ARREARS_DEBIT_"
										+ year
										+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
										+ emp_id[i][0]
										+ ""
										+ " AND ARREARS_PAID_MONTH="
										+ month
										+ " AND ARREARS_PAID_YEAR="
										+ year
										+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_DEBIT_CODE="
										+ salDebit[index][0]
										+ " AND ARREARS_PAY_IN_SAL = 'Y' ";
								Object[][] amt = getSqlModel()
										.getSingleResult(sql);
								if (amt != null || amt.length != 0) {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salDebit[index][1]))
															+ Double
																	.parseDouble(String
																			.valueOf(amt[0][0]))));
									total_nonRecovery += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								} else {
									data[dataIndex][column] = Utility
											.twoDecimals(formatter
													.format(Double
															.parseDouble(String
																	.valueOf(salDebit[index][1]))
															+ Double
																	.parseDouble("0.00")));
									total_nonRecovery += Double
											.parseDouble(String
													.valueOf(data[dataIndex][column]));
								}

							}// End of check flag condition
							else {

								data[dataIndex][column] = Utility
										.twoDecimals(String
												.valueOf(salDebit[index][1]));
								total_nonRecovery += Double
										.parseDouble(String
												.valueOf(salDebit[index][1]));
							}
						}// end of sal debit if condition
					}// End of comparison of debit code condition
					
					// UPDATED BY REEBA BEGINS
					if (esi_data != null && esi_data.length > 0) {
						if (String.valueOf(esi_data[0][3]).equals(
								String.valueOf(salDebit[index][0]))) {
							if (Double.parseDouble(String
									.valueOf(salDebit[index][1])) > 0){
								employerESIC = esicCredit
										* Double
												.parseDouble(String
														.valueOf(esi_data[0][2]))
										/ 100;
							}else
								employerESIC = 0.0;
							
							
						}
					}
					
					if (pf_data != null && pf_data.length > 0) {
						if (String.valueOf(pf_data[0][3]).equals(
								String.valueOf(salDebit[index][0]))) {
							employerPF = Double.parseDouble(String.valueOf(salDebit[index][1]));
						}
					}
					// UPDATED BY REEBA ENDS
					
				}// End of sal debit for loop

			column++;
		}// End of debit head loop
		// logger.info("Length of arrear Debit code
		// pkppp"+arrearDebitCode.length);
		data[dataIndex][column] = Utility.twoDecimals(formatter
				.format(total_nonRecovery));

		double total_pay = 0;
		total_pay = Double.parseDouble(String
				.valueOf(data[dataIndex][credit_header.length
						+ colTotal + 3]))
				- Double.parseDouble(String
						.valueOf(data[dataIndex][column]));
		column = column + 1;
		if (Double.parseDouble(Utility.twoDecimals(total_pay)) < 0) {
			data[dataIndex][column] = Utility.twoDecimals("0");
		} else {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(total_pay));
		}
		
		// UPDATED BY REEBA BEGINS
		column = column + 1;

		if (PFChk.equals("Y")) {
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(employerPF));// Employer contribution to
											// PF
			column++;
		}

		if (ESICChk.equals("Y")) {
			//logger.info("employerESIC last=========" + employerESIC);
			//logger.info("employerESIC column=========" + column);
			data[dataIndex][column] = Utility.twoDecimals(formatter
					.format(employerESIC));
			;// Employer contribution to ESIC
		}
		// UPDATED BY REEBA ENDS

		position_totalPay = column;
		employerESIC = 0;
		employerPF =0;
		esicCredit=0;
		//logger.info("position_totalPay :" + position_totalPay);
		try {
			/**
			 * Following condition is used to select the arrears for the
			 * employees.
			 */
			if (settlReg.getCheckFlag().equals("N")) {
				if (arrEmpLength != 0) {
					String arrear = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,ARREARS_TYPE,"
							+ "ARREARS_PROMCODE,DECODE(NVL(ARREARS_PAY_TYPE,'ADD'),'ADD','Arrears',"
							+ " 'DED','Recovery') FROM HRMS_ARREARS_"
							+ settlReg.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ settlReg.getYear()
							+ ".ARREARS_CODE)"
							+ " WHERE ARREARS_PAID_MONTH="
							+ settlReg.getMonth()
							+ " AND ARREARS_PAID_YEAR="
							+ settlReg.getYear()
							+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' AND EMP_ID="
							+ String.valueOf(emp_id[i][0])
							+ " ORDER BY ARREARS_MONTH,ARREARS_YEAR";
					Object[][] arrearCode = getSqlModel()
							.getSingleResult(arrear);
					if (!(arrearCode == null || arrearCode.length == 0)) {

						for (int arrCode = 0; arrCode < arrearCode.length; arrCode++) {
							totArrACredit = 0;
							totArrDebit = 0;
							if (String.valueOf(arrearCode[arrCode][5])
									.equals("")
									|| String.valueOf(
											arrearCode[arrCode][5])
											.equals("null")) {
								arrearCreditAmt = arrearCreditData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										credit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										year);
								arrearDebitAmt = arrearDebitData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										debit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										year);
							} else {
								arrearDebitAmt = arrearDebitData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										debit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										String
												.valueOf(arrearCode[arrCode][5]),
										year);
								arrearCreditAmt = arrearCreditData(
										String
												.valueOf(arrearCode[arrCode][0]),
										String.valueOf(emp_id[i][0]),
										String
												.valueOf(arrearCode[arrCode][1]),
										String
												.valueOf(arrearCode[arrCode][2]),
										credit_header,
										String
												.valueOf(arrearCode[arrCode][4]),
										String
												.valueOf(arrearCode[arrCode][5]),
										year);
							}
							String arrMonth = Utility
									.month(Integer
											.parseInt(String
													.valueOf(arrearCode[arrCode][1])));
							// arrearCreditAmt=arrearCreditData(String.valueOf(arrearCode[arrCode][0]),String.valueOf(emp_id[i][0]),String.valueOf(arrearCode[arrCode][1]),String.valueOf(arrearCode[arrCode][2]),credit_header,String.valueOf(arrearCode[arrCode][4]),String.valueOf(arrearCode[arrCode][5]),settlReg);
							// arrearDebitAmt=arrearDebitData(String.valueOf(arrearCode[arrCode][0]),String.valueOf(emp_id[i][0]),String.valueOf(arrearCode[arrCode][1]),String.valueOf(arrearCode[arrCode][2]),debit_header,String.valueOf(arrearCode[arrCode][4]),String.valueOf(arrearCode[arrCode][5]),settlReg);
							// String
							// arrMonth=Utility.month(Integer.parseInt(String.valueOf(arrearCode[arrCode][1])));

							int arrearCol = 0;
							data[dataIndex + 1][arrearCol] = ""
									+ emp_id[i][1];
							arrearCol = arrearCol + 1;
							data[dataIndex + 1][arrearCol] = ""
									+ emp_id[i][2]
									+ "\n"
									+ String
											.valueOf(arrearCode[arrCode][6])
									+ " for "
									+ arrMonth
									+ "-"
									+ String
											.valueOf(arrearCode[arrCode][2]);
							arrearCol = arrearCol + 1;
							if (String.valueOf(arrearCode[arrCode][3])
									.equals("null")
									|| String.valueOf(
											arrearCode[arrCode][3])
											.equals("")) {
								data[dataIndex + 1][arrearCol] = "";// +String.valueOf(arrearCode[arrCode][3]);
							} else {
								data[dataIndex + 1][arrearCol] = ""
										+ String
												.valueOf(arrearCode[arrCode][3]);
							}
							arrearCol = arrearCol + 1;
							if (settlReg.getCheckBrn().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][5];
								arrearCol++;
							}

							if (settlReg.getCheckDept().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][6];
								arrearCol++;
							}

							if (settlReg.getCheckDesg().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][11];
								arrearCol++;
							}

							if (settlReg.getCheckDoj().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][12];
								arrearCol++;
							}

							if (settlReg.getCheckDob().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][7];
								arrearCol++;
							}
							if (settlReg.getCheckEmpType().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][3];
								arrearCol++;
							}

							if (settlReg.getCheckBank().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][8];
								arrearCol++;
							}

							if (settlReg.getCheckAccount().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][9];
								arrearCol++;
							}

							if (settlReg.getCheckPan().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][10];
								arrearCol++;
							}
							if (settlReg.getCheckGender().equals("Y")) {
								if (String.valueOf(emp_id[i][13])
										.equals("")
										|| String
												.valueOf(emp_id[i][13])
												.equals("null")) {
									data[dataIndex + 1][arrearCol] = "";
								} else {
									data[dataIndex + 1][arrearCol] = emp_id[i][13];// Gender
								}
								arrearCol++;
							}

							if (settlReg.getCheckHold().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][14];
								arrearCol++;
							}

							if (settlReg.getCheckGrade().equals("Y")) {
								data[dataIndex + 1][arrearCol] = emp_id[i][15];
								arrearCol++;
							}

							/*
							 * arrearCol=arrearCol+1;
							 * data[dataIndex+1][arrearCol]=""+emp_id[i][3];//Emp
							 * type
							 */
							// Following loop is used to set the arrear
							// credit amount.
							for (int ac = 0; ac < arrearCreditAmt.length; ac++) {

								if (arrearCreditAmt != null)
									data[dataIndex + 1][arrearCol] = Utility
											.twoDecimals(String
													.valueOf(arrearCreditAmt[ac][0]));
								if (String.valueOf(
										arrearCreditAmt[ac][0]).equals(
										"null")
										|| String.valueOf(
												arrearCreditAmt[ac][0])
												.equals("")) {
									totArrACredit += Double
											.parseDouble(String
													.valueOf("0.00"));
								} else {
									totArrACredit += Double
											.parseDouble(String
													.valueOf(arrearCreditAmt[ac][0]));
									if (String.valueOf(arrearCreditAmt[ac][1]).trim()
											.equals("Y")) {
										esicCredit += Double
												.parseDouble(String
														.valueOf(arrearCreditAmt[ac][0]));
									}
								}
								arrearCol++;
							}

							data[dataIndex + 1][arrearCol] = Utility
									.twoDecimals(formatter
											.format(totArrACredit));
							arrearCol = arrearCol + 1;

							/*
							 * Following loop is used to the arrear
							 * debit amount
							 */
							for (int ad = 0; ad < arrearDebitAmt.length; ad++) {
								if (arrearDebitAmt != null)
									data[dataIndex + 1][arrearCol] = Utility
											.twoDecimals(String
													.valueOf(arrearDebitAmt[ad][0]));
								if (String.valueOf(
										arrearDebitAmt[ad][0]).equals(
										"")
										|| String.valueOf(
												arrearDebitAmt[ad][0])
												.equals("null")) {
									totArrDebit += Double
											.parseDouble(String
													.valueOf("0.00"));
								} else {
									totArrDebit += Double
											.parseDouble(String
													.valueOf(arrearDebitAmt[ad][0]));
									if (esi_data != null && esi_data.length > 0) {
										if (String.valueOf(esi_data[0][3]).equals(
												String.valueOf(arrearDebitAmt[ad][1]))) {
											if (Double.parseDouble(String
													.valueOf(arrearDebitAmt[ad][0])) > 0){
												employerESIC = esicCredit
														* Double
																.parseDouble(String
																		.valueOf(esi_data[0][2]))
														/ 100;
											}else
												employerESIC = 0.0;
											
											
										}
									}
									
									if (pf_data != null && pf_data.length > 0) {
										if (String.valueOf(pf_data[0][3]).equals(
												String.valueOf(arrearDebitAmt[ad][1]))) {
											employerPF = Double.parseDouble(String.valueOf(arrearDebitAmt[ad][0]));
										}
									}
								}
								arrearCol++;

							}

							data[dataIndex + 1][arrearCol] = Utility
									.twoDecimals(formatter
											.format(totArrDebit));
							// arrearCol=arrearCol+1;
							// data[dataIndex+1][arrearCol]=Utility.twoDecimals(totArrDebit);

							totArrearAmt = Double
									.parseDouble(String
											.valueOf(data[dataIndex + 1][credit_header.length
													+ colTotal + 3]))
									- Double
											.parseDouble(String
													.valueOf(data[dataIndex + 1][arrearCol]));
							arrearCol = arrearCol + 1;
							if (Double.parseDouble(String
									.valueOf(totArrearAmt)) < 0) {
								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals("0.00");
							} else {
								data[dataIndex + 1][arrearCol] = Utility
										.twoDecimals(formatter
												.format(totArrearAmt));
							}
							
							// UPDATED BY REEBA BEGINS
							arrearCol++;
							if (PFChk.equals("Y")) {
								data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
										.format(employerPF));// Employer
																		// contribution
																		// to
																		// PF
								arrearCol++;
							}

							if (ESICChk.equals("Y")) {
								data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
										.format(employerESIC));// Employer;// Employer
																		// contribution
																		// to
																		// ESIC
							}
							// UPDATED BY REEBA ENDS

							dataIndex++;
						}// End of arrear code for loop
					}
				}// End of arrearEmpLength if condition
			}// End of check flag
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		dataIndex++;
	}// End of emp_id for loop

	dataWithHeader = new Object[data.length + 1][totalCol];
	/*
	 * Following loop sets the column names
	 */
	for (int i = 0; i < colNames.length; i++) {
		dataWithHeader[0][i] = colNames[i];
	}
	/*
	 * Following loop sets the column values
	 */
	for (int i = 0; i < data.length; i++) {
		for (int j = 0; j < data[0].length; j++) {
			dataWithHeader[i + 1][j] = data[i][j];
		}
	}

	String finalHeader[] = new String[dataWithHeader[0].length];
	int[] cellWidth = new int[dataWithHeader[0].length];
	int[] alignment = new int[dataWithHeader[0].length];
	for (int i = 0; i < dataWithHeader[0].length; i++) {
		finalHeader[i] = (String) dataWithHeader[0][i];
		alignment[i] = 0;
		if (i > 1) {
			cellWidth[i] = 7;
			alignment[i] = 0;
		} else {
			cellWidth[0] = 8;
			cellWidth[1] = 15;
			cellWidth[2] = 10;
			cellWidth[3] = 7;
		}
	}
	Object finalData[][] = new Object[dataWithHeader.length - 1][dataWithHeader[0].length];
	for (int i = 0; i < finalData.length; i++) {
		for (int j = 0; j < finalData[0].length; j++) {
			finalData[i][j] = dataWithHeader[i + 1][j];

		}
	}
} catch (Exception e) {
	logger.error(e.getMessage());
}

return dataWithHeader;
}

public Object[][] getLeaveEncashmentDataUncheck(String emp_id, String year,
	String month) {
String query = "SELECT SUM(NVL(ENCASHMENT_ENCASH_AMOUNT,0)),ENCASHMENT_CREDIT_CODE FROM HRMS_ENCASHMENT_PROCESS_HDR"
		+ " LEFT JOIN HRMS_ENCASHMENT_PROCESS_DTL ON(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE=HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE AND EMP_ID="
		+ emp_id
		+ ")"
		+ " WHERE ENCASHMENT_PROCESS_FLAG= 'Y' AND ENCASHMENT_INCLUDE_SAL_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_MONTH="
		+ month
		+ " AND ENCASHMENT_INCLUDE_SAL_YEAR="
		+ year
		+ " GROUP BY ENCASHMENT_CREDIT_CODE";

Object leaveEncashData[][] = getSqlModel().getSingleResult(query);
return leaveEncashData;
}

/**
* Checks for the null value and if it finds it to be null then replaces it
* with blank.
* 
* @param result :-
*            Input String to be checked
* @return : - returns the checked string
*/
public static String checkNullToZero(String result) {
if (result == null || result.equals("null")) {
	return "0";
} else {
	return result;
}
}

public void reportDataNoSelect(Object[][] loop_data, ReportGenerator rg,
	SettlementRegister settlReg) {

ArrayList<String> totList = new ArrayList<String>();
int recCount = 0;
int arrEmpLength = 0;
int check = 0;
if (settlReg.getCheckBrn().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDob().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckBank().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckAccount().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckPan().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckEmpType().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDept().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDesg().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDoj().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckGender().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckHold().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGrade().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}
// UPDATED BY REEBA ENDS
String finalHeader[] = null;
int colTotal = 0;
String selectQueryInner = "SELECT DEPT_ID,DEPT_NAME from HRMS_DEPT";
Object[][] loop_data_inner = getSqlModel().getSingleResult(
		selectQueryInner);
if (loop_data.length > 0) {
	for (int a = 0; a < loop_data.length; a++) {

		for (int b = 0; b < loop_data_inner.length; b++) {

			String selectSalaryLoop = "SELECT HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
					+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
			} else {
				selectSalaryLoop += ",' ' ";
			}
			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";

			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += ",NVL(RANK_NAME,' ')";

			} else {
				selectSalaryLoop += ",' '";
			}

			selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
					+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC "
					+ " ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON"
					+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ") "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_CENTER)";
			}

			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_DEPT)";
			}

			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_RANK)";

			}

			selectSalaryLoop += "  LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
			selectSalaryLoop += " WHERE SAL_DIVISION="
					+ settlReg.getDivCode();

			String where = " AND SAL_EMP_CENTER=" + loop_data[a][0]
					+ "";
			if (!(settlReg.getOnHold().equals("A"))) {
				where += " and sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			where += " AND SAL_DEPT=" + loop_data_inner[b][0];
			if (!settlReg.getTypeCode().equals("")) {
				where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
			}
			where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			selectSalaryLoop = selectSalaryLoop + where;
			try {
				String arrearEmp = " SELECT HRMS_ARREARS_"
						+ settlReg.getYear()
						+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
						+ settlReg.getYear()
						+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
						+ settlReg.getYear()
						+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
						+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
						+ settlReg.getYear()
						+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
						+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
						+ settlReg.getYear()
						+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
						+ settlReg.getMonth()
						+ " AND LEDGER_YEAR="
						+ settlReg.getYear()
						+ ")"
						+ " INNER JOIN HRMS_ARREARS_"
						+ settlReg.getYear()
						+ " ON(HRMS_ARREARS_"
						+ settlReg.getYear()
						+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
						+ settlReg.getYear()
						+ ".ARREARS_CODE)"
						+ " WHERE ARREARS_PAID_MONTH="
						+ settlReg.getMonth()
						+ " AND ARREARS_PAID_YEAR="
						+ settlReg.getYear()
						+ " and arrears_type IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y'"
						+ " AND SAL_DIVISION=" + settlReg.getDivCode()
						+ " AND SAL_EMP_CENTER=" + loop_data[a][0]
						+ " and SAL_DEPT=" + loop_data_inner[b][0];
				/*
				 * String arrearEmp="SELECT
				 * HRMS_ARREARS_"+settlReg.getYear()+".EMP_ID,ARREARS_CODE
				 * FROM HRMS_ARREARS_"+settlReg.getYear()+" " + " inner
				 * join HRMS_EMP_OFFC on HRMS_EMP_OFFC.emp_id =
				 * HRMS_ARREARS_"+settlReg.getYear()+".emp_id " + " WHERE
				 * ARREARS_PAID_MONTH="+settlReg.getMonth()+" AND
				 * ARREARS_PAID_YEAR="+settlReg.getYear()+"" + " and
				 * arrears_type='M' and EMP_CENTER="+loop_data[a][0]+ "
				 * and EMP_DEPT="+loop_data_inner[b][0];
				 */
				Object[][] arrEmpChk = getSqlModel().getSingleResult(
						arrearEmp);
				if (arrEmpChk == null) {
					arrEmpLength = 0;
				} else if (arrEmpChk.length == 0) {
					arrEmpLength = 0;
				} else {
					arrEmpLength = arrEmpChk.length;
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			Object[][] reportDataPay = getReportDataNoBranchDept(
					selectSalaryLoop, settlReg, String
							.valueOf(loop_data[a][0]), String
							.valueOf(loop_data_inner[b][0]),
					arrEmpLength, check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
			// getReportDataNotSelect(selectSalaryLoop,settlReg,String.valueOf(loop_data[a][0]),String.valueOf(loop_data_inner[b][0]),arrEmpLength);//getReportData(selectSalaryLoop,settlReg);
			if (reportDataPay.length > 1) {
				
				int headerLength = 0;
				int[] cellWidth = null;
				int[] alignment = null;
				if (settlReg.getChkConsSummary().equals("N")) {
					finalHeader = new String[reportDataPay[0].length];
					headerLength = reportDataPay[0].length;
					cellWidth = new int[reportDataPay[0].length];
					alignment = new int[reportDataPay[0].length];
				} else {
					finalHeader = new String[reportDataPay[0].length - 1];
					headerLength = reportDataPay[0].length - 1;
					cellWidth = new int[reportDataPay[0].length - 1];
					alignment = new int[reportDataPay[0].length - 1];
				}
				// String finalHeader[] = new
				// String[reportDataPay[0].length];

				/*
				 * Following loop is used to set the cell width
				 */
				for (int i = 0; i < headerLength; i++) {
					finalHeader[i] = String
							.valueOf(reportDataPay[0][i]);
					alignment[i] = 0;
					if (i > 1) {
						cellWidth[i] = 7;
						alignment[i] = 0;
					} else {
						cellWidth[0] = 8;
						cellWidth[1] = 15;
					}
				}

				Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
				/*
				 * Following loop is used to set the credit and debit
				 * head values
				 */
				for (int i = 0; i < finalData.length; i++) {
					for (int j = 0; j < finalData[0].length; j++) {
						finalData[i][j] = reportDataPay[i + 1][j];
						// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
					}// End of inner loop
				}// End of outer loop

				Object totalByColumn[][] = null;
				String totalHeader[] = new String[finalData.length];
				totalHeader[0] = "";
				// totalHeader[1] = "";
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn = new Object[1][finalData[0].length];
					totalByColumn[0][0] = "TOTAL :-";
					totalByColumn[0][1] = "No. of Employees:"
							+ finalData.length;
				} else {
					totalByColumn = new Object[1][finalData[0].length - 1];
					totalByColumn[0][0] = "TOTAL :-";
					totalByColumn[0][1] = finalData.length;
				}
				/**
				 * Following loop is used to set the sum of the
				 * individual credit and debit head values.
				 */
				if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
					colTotal = check + 1; //check+3-2
				} else if (settlReg.getCheckEmployerPF().equals("Y")) {
					colTotal = check + 2;//check+3-1
				} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
					colTotal = check + 2;//check+3-1
				}else
					colTotal = check + 3;

				for (int i = colTotal; i < finalData[0].length; i++) {
					double total = 0;
					for (int j = 0; j < finalData.length; j++) {
						if (String.valueOf(finalData[j][i]).equals(
								"null")
								|| String.valueOf(finalData[j][i])
										.equals("null.00")
								|| String.valueOf(finalData[j][i])
										.equals("")
								|| String.valueOf(finalData[j][i]) == null) {
							finalData[j][i] = "0";
						}
						if (String.valueOf(finalData[j][1]).contains(
								"Recovery"))
							total -= Double.parseDouble(String
									.valueOf(finalData[j][i]));
						else
							total += Double.parseDouble(String
									.valueOf(finalData[j][i]));
					}// End of inner loop
					// totalHeader[i] = "";
					totList.add(Utility.twoDecimals(formatter
							.format(total)));
					if (settlReg.getChkConsSummary().equals("N")) {
						totalByColumn[0][i] = Utility
								.twoDecimals(formatter.format(total));
					} else {
						totalByColumn[0][i - 1] = Utility
								.twoDecimals(formatter.format(total));
					}
				}// End of outer loop
				rg.addText("\n", 0, 0, 0);

				/*
				 * Object[][] filterObj = new Object[1][4];
				 * filterObj[0][0] = "Designation : "; filterObj[0][1] =
				 * settlReg.getDesgName(); filterObj[0][2] = "Employee
				 * Type : "; filterObj[0][3] = settlReg.getTypeName();
				 * 
				 * rg.tableBody(filterObj,cellWidth,alignment);
				 * rg.addText("\n",0,0,0);
				 */

				rg.addText("Branch : " + loop_data[a][1]
						+ "   Department : " + loop_data_inner[b][1],
						0, 0, 0);
				if (settlReg.getChkConsSummary().equals("N")) {
					rg.tableBody(finalHeader, finalData, cellWidth,
							alignment);
					rg.tableBody(totalByColumn, cellWidth, alignment);
				} else {
					// Object[][] summaryObj = new Object[1][1];
					// summaryObj[0][0] = "\n";
					rg.tableBody(finalHeader, totalByColumn, cellWidth,
							alignment);
					// rg.tableBody(totalByColumn, cellWidth,
					// alignment);
				}
				recCount++;
				// colCount=finalData[0].length;
			}// End of reportDataPay if condition
		}// End of loop_data_inner loop
	}// End of loop_data loop

	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = formatter.format(Double
						.parseDouble(String.valueOf(totList
								.get(arrCount))));
				arrCount++;

			}
		}

		Object[][] grand_total = null;
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}
		grand_total[0][0] = "GRAND TOTAL :-";
		grand_total[0][1] = " ";

		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}

			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}
		}
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);

		rg.addText("\n", 0, 0, 0);
		//rg.addText("GRAND TOTAL",0,0,0);
		finalHeader[1]="";
		rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
	} else {
		rg.addText("Records are not available.", 0, 1, 0);
	}

}//End of loop_data if condition

}

/**
* @author REEBA_JOSEPH
* To generate report Branchwise
* @param settlReg
* @param response
*/
public void generateBranchwiseReport(SettlementRegister settlReg,
	HttpServletResponse response) {

String reportType = settlReg.getReport();
String reportName = "Salary Register_" + settlReg.getDivName() + " for "
		+ Utility.month(Integer.parseInt(settlReg.getMonth())) + "_"
		+ settlReg.getYear();
org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
		reportType, reportName, "A4");
rg.setFName(reportName + "." + reportType);
if (reportType.equals("Pdf")) {
	rg.addTextBold("Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ", 0, 1, 0);
} else {
	Object[][] title = new Object[1][3];
	title[0][0] = "";
	title[0][1] = "";
	title[0][2] = "Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ";

	int[] cols = { 20, 20, 50 };
	int[] align = { 0, 0, 1 };
	rg.tableBodyNoCellBorder(title, cols, align, 1);
}

rg.addText("\n", 0, 1, 0);

if (settlReg.getBranchCode().equals("")) {
	//logger.info("Only Branch====Branch code nil");
	String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";
	Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
	reportOnlyForBranch(loop_data, rg, settlReg);
} else {
	//logger.info("Only Branch====Branch code present : "
	//		+ settlReg.getBranchCode());
	Object[][] loop_data = new Object[1][2];
	loop_data[0][0] = settlReg.getBranchCode();
	loop_data[0][1] = settlReg.getBranchName();
	reportOnlyForBranch(loop_data, rg, settlReg);
}

rg.createReport(response);
}

/**
* @author REEBA_JOSEPH
* To generate report Departmentwise
* @param settlReg
* @param response
*/
public void generateDepartmentwiseReport(SettlementRegister settlReg,
	HttpServletResponse response) {

String reportType = settlReg.getReport();
String reportName = "Salary Register_" + settlReg.getDivName() + " for "
		+ Utility.month(Integer.parseInt(settlReg.getMonth())) + "_"
		+ settlReg.getYear();
org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
		reportType, reportName, "A4");
rg.setFName(reportName + "." + reportType);
if (reportType.equals("Pdf")) {
	rg.addTextBold("Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ", 0, 1, 0);
} else {
	Object[][] title = new Object[1][3];
	title[0][0] = "";
	title[0][1] = "";
	title[0][2] = "Salary Register of " + settlReg.getDivName()
			+ " for the month of "
			+ Utility.month(Integer.parseInt(settlReg.getMonth())) + " "
			+ settlReg.getYear() + " ";

	int[] cols = { 20, 20, 50 };
	int[] align = { 0, 0, 1 };
	rg.tableBodyNoCellBorder(title, cols, align, 1);
}

rg.addText("\n", 0, 1, 0);

if (settlReg.getDeptCode().equals("")) {
	//logger.info("Only Department====Department code nil");
	String selectQuery = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
	Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
	reportOnlyForDepartment(loop_data, rg, settlReg);
} else {
	//logger.info("Only Department====Department code present : "
	//		+ settlReg.getDeptCode());
	Object[][] loop_data = new Object[1][2];
	loop_data[0][0] = settlReg.getDeptCode();
	loop_data[0][1] = settlReg.getDeptName();
	reportOnlyForDepartment(loop_data, rg, settlReg);
}

rg.createReport(response);
}

/**
* @author REEBA_JOSEPH
* @param loop_data
* @param rg
* @param settlReg
*/
public void reportOnlyForDepartment(Object[][] loop_data,
	ReportGenerator rg, SettlementRegister settlReg) {

ArrayList<String> totList = new ArrayList<String>();
int recCount = 0;
int arrEmpLength = 0;
int check = 0;
if (settlReg.getCheckBrn().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDob().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckBank().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckAccount().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckPan().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckEmpType().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckDept().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDesg().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckDoj().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckGender().equals("Y")) {
	check = check + 1;
}
if (settlReg.getCheckHold().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckGrade().equals("Y")) {
	check = check + 1;
}

// UPDATED BY REEBA BEGINS
if (settlReg.getCheckEmployerPF().equals("Y")) {
	check = check + 1;
}

if (settlReg.getCheckEmployerESIC().equals("Y")) {
	check = check + 1;
}
// UPDATED BY REEBA ENDS

String finalHeader[] = null;
int colTotal = 0;
if (loop_data.length > 0) {
	//logger.info("Loop count ============= " + loop_data.length);
	for (int a = 0; a < loop_data.length; a++) {
		//logger.info("For Department ============= " + loop_data[a][0]);
		String selectSalaryLoop = "";
		try {
			selectSalaryLoop = "SELECT HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
					+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
			} else {
				selectSalaryLoop += ",' ' ";
			}
			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),nvl(SAL_ACCNO_REGULAR,' '),NVL(SAL_PANNO,' ')";
			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += ",NVL(RANK_NAME,' ')";

			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
					+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' ') FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC "
					+ " ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON"
					+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ") "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)";
			if (settlReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_CENTER)";
			}
			if (settlReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_DEPT)";
			}
			if (settlReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
						+ settlReg.getYear() + ".SAL_EMP_RANK)";

			}
			selectSalaryLoop += "  LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
			selectSalaryLoop += " WHERE SAL_DIVISION="
					+ settlReg.getDivCode();
			//String where = " AND SAL_EMP_CENTER=" + loop_data[a][0]
			//		+ "";
			String where = " AND SAL_DEPT=" + loop_data[a][0] + "";
			if (!(settlReg.getOnHold().equals("A"))) {
				where += " and sal_onhold='" + settlReg.getOnHold()
						+ "' ";
			}
			// where+=" AND SAL_DEPT="+loop_data_inner[b][0];
			if (!settlReg.getTypeCode().equals("")) {
				where += " AND SAL_EMP_TYPE=" + settlReg.getTypeCode();
			}
			if (!settlReg.getDesgCode().equals("")) {
				where += " AND SAL_EMP_RANK=" + settlReg.getDesgCode();
			}
			where += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			selectSalaryLoop = selectSalaryLoop + where;
		} catch (Exception e) {
			logger.error("Error in Salary query : " + e);
			e.printStackTrace();
		}
		try {
			String arrearEmp = " SELECT HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
					+ settlReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ settlReg.getYear()
					+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ settlReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ settlReg.getYear()
					+ ")"
					+ " INNER JOIN HRMS_ARREARS_"
					+ settlReg.getYear()
					+ " ON(HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ settlReg.getYear()
					+ ".ARREARS_CODE)"
					+ " WHERE ARREARS_PAID_MONTH="
					+ settlReg.getMonth()
					+ " AND ARREARS_REF_YEAR="
					+ settlReg.getYear()
					+ " and arrears_type IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y'"
					+ " AND SAL_DIVISION=" + settlReg.getDivCode()
					//+ " AND SAL_EMP_CENTER=" + loop_data[a][0];
					+ " AND SAL_DEPT=" + loop_data[a][0];

			Object[][] arrEmpChk = getSqlModel().getSingleResult(
					arrearEmp);
			if (arrEmpChk == null) {
				arrEmpLength = 0;
			} else if (arrEmpChk.length == 0) {
				arrEmpLength = 0;
			} else {
				arrEmpLength = arrEmpChk.length;
			}

		} catch (Exception e) {
			logger.error("Error in arrears query : " + e);
			e.printStackTrace();
		}
		Object[][] reportDataPay = null;
		try {
			reportDataPay = getReportDataNoBranchDept(selectSalaryLoop,
					settlReg, "0", String.valueOf(loop_data[a][0]),
					arrEmpLength, check, settlReg.getCheckEmployerPF(), settlReg.getCheckEmployerESIC());
		} catch (Exception e) {
			logger.error("Error in getting report data : " + e);
			e.printStackTrace();
		}
		if (reportDataPay.length > 1) {
			int headerLength = 0;
			int[] cellWidth = null;
			int[] alignment = null;
			if (settlReg.getChkConsSummary().equals("N")) {
				finalHeader = new String[reportDataPay[0].length];
				headerLength = reportDataPay[0].length;
				cellWidth = new int[reportDataPay[0].length];
				alignment = new int[reportDataPay[0].length];
			} else {
				finalHeader = new String[reportDataPay[0].length - 1];
				headerLength = reportDataPay[0].length - 1;
				cellWidth = new int[reportDataPay[0].length - 1];
				alignment = new int[reportDataPay[0].length - 1];
			}
			//String finalHeader[] = new String[reportDataPay[0].length];

			/*
			 * Following loop is used to set the cell width
			 */
			for (int i = 0; i < headerLength; i++) {
				finalHeader[i] = String.valueOf(reportDataPay[0][i]);
				alignment[i] = 0;
				if (i > 1) {
					cellWidth[i] = 7;
					alignment[i] = 0;
				} else {
					cellWidth[0] = 8;
					cellWidth[1] = 15;
				}
			}
			Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
			/*
			 * Following loop is used to set the credit and debit head
			 * values
			 */
			for (int i = 0; i < finalData.length; i++) {
				for (int j = 0; j < finalData[0].length; j++) {
					finalData[i][j] = reportDataPay[i + 1][j];
					//logger.info("finalData["+i+"]["+j+"]=========="+finalData[i][j]);
				}// End of inner loop
			}// End of outer loop
			Object totalByColumn[][] = null;
			String totalHeader[] = new String[finalData.length];
			totalHeader[0] = "";
			// totalHeader[1] = "";
			if (settlReg.getChkConsSummary().equals("N")) {
				totalByColumn = new Object[1][finalData[0].length];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = "No. of Employees:"
						+ finalData.length;
			} else {
				totalByColumn = new Object[1][finalData[0].length - 1];
				totalByColumn[0][0] = "TOTAL :-";
				totalByColumn[0][1] = finalData.length;
			}
			/**
			 * Following loop is used to set the sum of the individual
			 * credit and debit head values.
			 */
			if (settlReg.getCheckEmployerPF().equals("Y") && settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 1; //check+3-2
			} else if (settlReg.getCheckEmployerPF().equals("Y")) {
				colTotal = check + 2;//check+3-1
			} else if (settlReg.getCheckEmployerESIC().equals("Y")) {
				colTotal = check + 2;//check+3-1
			}else
				colTotal = check + 3;

			for (int i = colTotal; i < finalData[0].length; i++) {
				double total = 0;
				for (int j = 0; j < finalData.length; j++) {
					if (String.valueOf(finalData[j][i]).equals("null")
							|| String.valueOf(finalData[j][i]).equals(
									"null.00")
							|| String.valueOf(finalData[j][i]).equals(
									"")
							|| String.valueOf(finalData[j][i]) == null) {
						finalData[j][i] = "0";
					}
					if (String.valueOf(finalData[j][1]).contains(
							"Recovery"))
						total -= Double.parseDouble(String
								.valueOf(finalData[j][i]));
					else
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
				}// End of inner loop
				// totalHeader[i] = "";
				totList.add(Utility
						.twoDecimals(formatter.format(total)));
				if (settlReg.getChkConsSummary().equals("N")) {
					totalByColumn[0][i] = Utility.twoDecimals(formatter
							.format(total));
				} else {
					totalByColumn[0][i - 1] = Utility
							.twoDecimals(formatter.format(total));
				}
			}// End of outer loop
			rg.addText("\n", 0, 0, 0);
			rg.addText("Department : " + loop_data[a][1], 0, 0, 0);
			if (settlReg.getChkConsSummary().equals("N")) {
				rg.tableBody(finalHeader, finalData, cellWidth,
						alignment);
				rg.tableBody(totalByColumn, cellWidth, alignment);
			} else {
				//Object[][] summaryObj = new Object[1][1];
				//summaryObj[0][0] = "\n";
				rg.tableBody(finalHeader, totalByColumn, cellWidth,
						alignment);
				//rg.tableBody(totalByColumn, cellWidth, alignment);
			}
			recCount++;

		}// End of reportDataPay if condition
	} // End of loop_data loop

	if (recCount != 0) {
		Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

		int arrCount = 0;
		for (int i = 0; i < recCount; i++) {
			for (int j = 0; j < (totList.size() / recCount); j++) {
				listValues[i][j] = formatter.format(Double
						.parseDouble(String.valueOf(totList
								.get(arrCount))));
				arrCount++;

			}
		}

		Object[][] grand_total = null;
		if (settlReg.getChkConsSummary().equals("N")) {
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal ];
			
		}else{
			grand_total = new Object[1][listValues[0].length
			    						+ colTotal - 1];
		}

		grand_total[0][0] = "GRAND TOTAL :-";
		grand_total[0][1] = " ";

		for (int i = 0; i < listValues[0].length; i++) {
			double total = 0.00;
			for (int j = 0; j < listValues.length; j++) {
				if (String.valueOf(listValues[j][i]).equals("null")) {
					listValues[j][i] = "0.00";
				}
				total += Double.parseDouble(String
						.valueOf(listValues[j][i]));
			}

			if (settlReg.getChkConsSummary().equals("N")) {
				grand_total[0][i + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			} else {
				grand_total[0][(i - 1) + colTotal] = Utility
						.twoDecimals(formatter.format(total));
			}

		}
		int[] cellWidth = getCellWidth(grand_total[0].length);
		int[] alignment = getAlignment(grand_total[0].length);

		rg.addText("\n", 0, 0, 0);
		// rg.addText("GRAND TOTAL",0,0,0);
		finalHeader[1]="";
		rg.tableBody(finalHeader,grand_total, cellWidth, alignment);
	} else {
		rg.addText("Records are not available.", 0, 1, 0);
	}

} //End of loop_data if condition
}

}
