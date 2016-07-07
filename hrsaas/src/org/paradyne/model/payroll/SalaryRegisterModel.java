package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.SalaryRegister;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
//import org.paradyne.lib.ireportV2.ReportDataSet;
//import org.paradyne.lib.ireportV2.TableDataSet;
/*import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;*/

/**
 * @author Pradeep Kumar Sahoo Date:21-04-2008
 * @ modified by Mangesh Jadhav
 */
public class SalaryRegisterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	HttpServletResponse response;
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public void generateReport(SalaryRegister salReg, HttpServletRequest request, HttpServletResponse response, String reportPath){
		
		//ReportDataSet rds = new ReportDataSet();
		String type = salReg.getReport();
		//rds.setReportType(type);
		String fileName = salReg.getDivisionAbbrevation()+Utility.getRandomNumber(1000);
		//rds.setFileName(fileName);
		String reportName = "";
		try {
			reportName = "Salary Register_" + salReg.getDivName() + " for "
					+ Utility.month(Integer.parseInt(salReg.getMonth())) + "_"
					+ salReg.getYear();
			//rds.setReportName(reportName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(type, reportName, "A4");

		// report for both branchwise & dept wise
		if (salReg.getChkBrnOrder().equals("Y") && salReg.getChkDeptOrder().equals("Y")) {
			rg=generateBranchDeptwiseReport(salReg, response, rg);
			rg.createReport(response);
		} 
		else if(salReg.getChkBrnOrder().equals("Y")){  // report for branchwise 
			rg=generateBranchwiseReport(salReg, response, rg);
			rg.createReport(response);
		}
		else if(salReg.getChkDeptOrder().equals("Y")){ // report for dept wise
			rg=generateDepartmentwiseReport(salReg, response, rg);
			rg.createReport(response);
		}else {    // normal report
			genReport(salReg.getReport(), salReg.getMonth(), salReg
					.getYear(), salReg.getCheckBrn(), salReg.getCheckDob(),
					salReg.getCheckBank(), salReg.getCheckAccount(), salReg
							.getCheckPan(), salReg.getCheckEmpType(), salReg
							.getCheckDept(), salReg.getCheckDesg(), salReg
							.getCheckDoj(), salReg.getCheckGender(), salReg
							.getCheckHold(), salReg.getCheckGrade(),salReg.getCheckEmpGrade(), salReg
							.getDivCode(), salReg.getBranchCode(), salReg
							.getOnHold(), salReg.getDeptCode(), salReg
							.getTypeCode(), salReg.getDesgCode(), salReg
							.getCheckFlag(), response, salReg.getBranchName(),
							salReg.getDeptName(), salReg.getDesgName(), salReg
							.getTypeName(), salReg.getDivName(), salReg
							.getChkConsSummary(), salReg.getCheckEmployerPF(),
							salReg.getCheckEmployerESIC(),salReg.getPaybillId(), 
							salReg.getPaybillCheck(),salReg.getCostcenterid(), 
							salReg.getCostcentername(),salReg.getSubcostcenterid(),
							salReg.getSubcostcentername(), salReg.getCheckCostCenter(), salReg.getCheckSubCostCenter(), salReg.getEmpGradeId());
		}
		
	}

	/**
	 * Added on Date:22-10-2008 Following function is called in the
	 * getReportDataUnCheck() function to generate the debit amount for each
	 * employee when the check box for Branch and Department wise is not
	 * checked.
	 * 
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param salReg
	 * @return
	 */
	public Object[][] getSalDebitUncheck(String emp_id, String month, String year) {
		Object[][] debit_amount = null;
		Object[][] ledgCode = null;
		String ledgerCode = null;

		try {
			ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
					+ year
					+ " INNER JOIN HRMS_SALARY_LEDGER "
					+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
					+ year
					+ ".SAL_LEDGER_CODE)"
					+ " WHERE LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ "  AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
					+ emp_id;
			ledgCode = getSqlModel().getSingleResult(ledgerCode,
					new Object[0][0]);

			String sal_Amount = "SELECT   DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
					+ year
					+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
					+ emp_id
					+ " AND SAL_LEDGER_CODE="
					+ String.valueOf(ledgCode[0][0])
					+ ") "
					+ " WHERE DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE ";
			debit_amount = getSqlModel().getSingleResult(sal_Amount,
					new Object[0][0]);

		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		return debit_amount;

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
	 * @param salReg
	 * @return
	 */
	public Object[][] getSalaryCreditDataUncheck(String empCode, String year,
			String month) {
		Object[][] credit_amount = null;
		Object[][] ledgCode = null;
		String ledgerCode = null;

		try {

			// ledgerCode=" SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where
			// LEDGER_MONTH="+month+" and LEDGER_YEAR="+year+" AND
			// LEDGER_BRN="+salReg.getBranchCode();
			ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
					+ year
					+ " INNER JOIN HRMS_SALARY_LEDGER "
					+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
					+ year
					+ ".SAL_LEDGER_CODE)"
					+ " WHERE LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
					+ empCode;
			ledgCode = getSqlModel().getSingleResult(ledgerCode,
					new Object[0][0]);
			// UPDATED BY REEBA 
			String sal_Amount = "SELECT  SAL_CREDIT_CODE,NVL(SAL_AMOUNT,0.00),CREDIT_APPLICABLE_ESI  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
					+ year
					+ " ON (SAL_CREDIT_CODE = CREDIT_CODE AND EMP_ID="
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

	/**
	 * Added on date:22-10-2008 following function is called when the branch and
	 * department wise check box is unchecked.
	 * @param string 
	 * 
	 * @param salReg
	 * @param response
	 * @param typeName
	 * @param desgName
	 * @param deptName
	 * @param branchName
	 * @param paybillChk 
	 * @param subcostcenterName 
	 * @param subcostcenterId 
	 * @param costcenterName 
	 * @param costcentId 
	 * @param salReg
	 */
	public void genReport(String reportType, String month, String year,
			String brnChk, String dobChk, String bankChk, String accountChk,
			String panChk, String empTypeChk, String deptChk, String desgChk,
			String dojChk, String genderChk, String holdChk, String gradeChk,String empGradeChk,
			String divCode, String brnCode, String onHoldCode, String deptCode,
			String empTypeCode, String desgCode, String chekFlag,
			 HttpServletResponse response, String branchName, String deptName,
			String desgName, String typeName, String divisionName,
			String summaryChk, String PFChk, String ESICChk, String paybillCode, 
			String paybillChk, String costcentId, String costcenterName, String subcostcenterId, 
			String subcostcenterName, String costCenterChk, String subCostCenterChk, String empGradeId) {
		logger.info("##################### Default Options #####################");
		org.paradyne.lib.report.ReportGenerator rg=null;
		try {
			int arrEmpLength = 0;
			int check = 0;
			int colTotal = 0;
			
			
			String reportName = "Salary Register_" + divisionName + " for "
								+ Utility.month(Integer.parseInt(month)) + "_"
								+ year;
			rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName, "A4");
			
			if (reportType.equals("Pdf")) {
				rg.addTextBold(reportName, 0, 1, 0);
			} else {
				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = reportName;

				int[] cols = { 20, 20, 50 };
				int[] align = { 0, 0, 1 };
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			}
			rg.addText("", 0, 0, 0);
			/*TableDataSet titleData = new TableDataSet();
			titleData.setData(new Object[][] {{reportTitle}});
			titleData.setCellAlignment(new int[] { 1 });
			titleData.setCellWidth(new int[] { 100 });
			titleData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
			titleData.setBlankRowsBelow(1);
			titleData.setBorder(false);*/
			//rg.addTableToDoc(titleData);

			// check to add columns in the reports
			if (brnChk.equals("Y")) {
				check = check + 1;
			}
			if (dobChk.equals("Y")) {
				check = check + 1;
			}
			if (bankChk.equals("Y")) {
				check = check + 1;
			}
			if (accountChk.equals("Y")) {
				check = check + 1;
			}
			if (panChk.equals("Y")) {
				check = check + 1;
			}
			if (empTypeChk.equals("Y")) {
				check = check + 1;
			}
			if (deptChk.equals("Y")) {
				check = check + 1;
			}
			if (desgChk.equals("Y")) {
				check = check + 1;
			}
			if (dojChk.equals("Y")) {
				check = check + 1;
			}
			if (genderChk.equals("Y")) {
				check = check + 1;
			}
			if (holdChk.equals("Y")) {
				check = check + 1;
			}
			if (gradeChk.equals("Y")) {
				check = check + 1;
			}
			if (empGradeChk.equals("Y")) {
				check = check + 1;
			}
			if (PFChk.equals("Y")) {
				check = check + 1;
			}
			if (ESICChk.equals("Y")) {
				check = check + 1;
			}
			if (paybillChk.equals("Y")) {
				check = check + 1;
			}
			if (costCenterChk.equals("Y")) {
				check = check + 1;
			}
			if (subCostCenterChk.equals("Y")) {
				check = check + 1;
			}
			
			// employee details query
			String selectSalaryLoop = "SELECT HRMS_SALARY_" + year
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
			selectSalaryLoop += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '),NVL(SAL_EMP_ACC_NO,' '),NVL(SAL_PANNO,' ')";

			if (desgChk.equals("Y")) {
				selectSalaryLoop += ",NVL(RANK_NAME,' ')";

			} else {
				selectSalaryLoop += ",' '";

			}

			selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END"
					+ ",CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' '), NVL(CADRE_NAME,' '), NVL(HRMS_PAYBILL.PAYBILL_GROUP,' '), NVL(HRMS_COST_CENTER.COST_CENTER_NAME,' '), NVL(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_NAME,' ') FROM HRMS_SALARY_"
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
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_"+ year+".SAL_EMP_BANK_MISC_CODE)";
			if (brnChk.equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"
						+ year + ".SAL_EMP_CENTER)";
			}

			if (deptChk.equals("Y")) {
				selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
						+ year + ".SAL_DEPT)";
			}

			if (desgChk.equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"+ year + ".SAL_EMP_RANK)";

			}
			selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
			//added by ganesh
			selectSalaryLoop += " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_SALARY_"+ year + ".SAL_EMP_GRADE) ";
			//added by ganesh end
			
			selectSalaryLoop += " LEFT JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_EMP_OFFC.EMP_PAYBILL)";
			selectSalaryLoop += " LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";
			selectSalaryLoop += " LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID AND HRMS_SUB_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";
			
			selectSalaryLoop += " WHERE SAL_DIVISION IN("+divCode+")";

			// filters checked
			if (!(brnCode.equals(""))) {
				selectSalaryLoop += " AND SAL_EMP_CENTER IN(" + brnCode + ") ";
			}
			if (!(onHoldCode.equals("A"))) {
				selectSalaryLoop += "AND SAL_ONHOLD='" + onHoldCode + "' ";
			}
			if (!(deptCode.equals(""))) {

				selectSalaryLoop += "AND SAL_DEPT IN(" + deptCode+")";
			}

			if (!empTypeCode.equals("")) {
				selectSalaryLoop += " AND SAL_EMP_TYPE IN(" + empTypeCode+")";
			}
			if (!desgCode.equals("")) {
				selectSalaryLoop += " AND SAL_EMP_RANK IN(" + desgCode+")";
			}
			if (!paybillCode.equals("")) {
				selectSalaryLoop += " AND SAL_EMP_PAYBILL IN("+ paybillCode+")";
			}
			if (!empGradeId.equals("")) {
				selectSalaryLoop += " AND SAL_EMP_GRADE IN("+ empGradeId+")";
			}
			if (!costcentId.equals("")) {
				selectSalaryLoop += " AND COST_CENTER_ID IN("+ costcentId+")";
			}
			if (!subcostcenterId.equals("")) {
				selectSalaryLoop += " AND SUB_COST_CENTER_ID IN("+ subcostcenterId+")";
			}

			selectSalaryLoop += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			try {
				// query for employee arrears
				String arrearEmp = " SELECT HRMS_ARREARS_"
						+ year
						+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE  FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
						+ year
						+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
						+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
						+ year
						+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
						+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ ")"
						+ " INNER JOIN HRMS_ARREARS_"
						+ year
						+ " ON(HRMS_ARREARS_"
						+ year
						+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
						+ year
						+ ".ARREARS_CODE)"
						+ " WHERE ARREARS_PAID_MONTH="
						+ month
						+ " AND ARREARS_PAID_YEAR="
						+ year
						+ " and ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
						+ " AND SAL_DIVISION IN("+ divCode+")";

				// filters checked
				if (!(onHoldCode.equals("A"))) {
					arrearEmp += " AND SAL_ONHOLD='" + onHoldCode + "' ";
				}

				if (!(deptCode.equals(""))) {
					arrearEmp += " AND SAL_DEPT IN(" + deptCode+")";
				}
				if (!empTypeCode.equals("")) {
					arrearEmp += " AND SAL_EMP_TYPE IN(" + empTypeCode+")";
				}

				if (!desgCode.equals("")) {
					arrearEmp += " AND SAL_EMP_RANK IN(" + desgCode+")";
				}

				if (!(brnCode.equals(""))) {
					arrearEmp += " AND SAL_EMP_CENTER IN(" + brnCode + " )";
				}
				Object[][] arrEmpChk = getSqlModel().getSingleResult(arrearEmp);
				if (arrEmpChk == null) {
					arrEmpLength = 0;
				} else if (arrEmpChk.length == 0) {
					arrEmpLength = 0;
				} else {
					arrEmpLength = arrEmpChk.length;
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

			// UPDATED BY REEBA
			
			// get the required report data in object
			Object[][] reportDataPay = getReportDataUnCheck(selectSalaryLoop,
					month, year, brnChk, deptChk, desgChk, dojChk, dobChk,
					empTypeChk, bankChk, accountChk, panChk, genderChk,
					holdChk, gradeChk,empGradeChk, chekFlag, arrEmpLength, check,
					divisionName, summaryChk, PFChk, ESICChk, paybillChk, costCenterChk, subCostCenterChk);

			// getReportDataUnCheck(selectSalaryLoop,salReg,arrEmpLength,check);
			if (reportDataPay.length > 1) {

				String finalHeader[] = null;
				int headerLength = 0;
				int[] cellWidth = null;
				int[] alignment = null;
				if (summaryChk.equals("N")) {
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
				if (summaryChk.equals("N")) {
					totalByColumn = new Object[1][finalData[0].length];
					totalByColumn[0][0] = "TOTAL :-";
					totalByColumn[0][1] = "No. of Employees:"
							+ finalData.length;
				} else {
					totalByColumn = new Object[1][finalData[0].length - 1];
					totalByColumn[0][0] = "TOTAL :-";
					totalByColumn[0][1] = finalData.length;
				}

				if (PFChk.equals("Y") && ESICChk.equals("Y")) {   //for both PF & ESIC 
					colTotal = check + 1; //check+3-2
				} else if (PFChk.equals("Y")) {
					colTotal = check + 2;//check+3-1
				} else if (ESICChk.equals("Y")) {
					colTotal = check + 2;//check+3-1
				}else {
					colTotal = check + 3;
				}

				// loop to calculate the credit wise total amount 
				for (int i = colTotal; i < finalData[0].length; i++) {
					double total = 0.00;
					for (int j = 0; j < finalData.length; j++) {
						if (String.valueOf(finalData[j][i]).equals("null")
								|| String.valueOf(finalData[j][i]).equals("")
								|| String.valueOf(finalData[j][i]).equals(
										"null.00")) {
							finalData[j][i] = "0.00";
						}
						if (String.valueOf(finalData[j][1]).contains("Recovery")) {
							total -= Double.parseDouble(String.valueOf(finalData[j][i]));
						} else {
							total += Double.parseDouble(String.valueOf(finalData[j][i]));
						}
					}
					if (summaryChk.equals("N")) {
						totalByColumn[0][i] = Utility.twoDecimals(formatter
								.format(total));
					} else {
						totalByColumn[0][i - 1] = Utility.twoDecimals(formatter
								.format(total));
					}
				}

				if (summaryChk.equals("N")) {
					rg.tableBody(finalHeader, finalData, cellWidth, alignment);
					rg.tableBody(totalByColumn, cellWidth, alignment);
					
				} else {   // for summary check
					rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
					
				}

			} else {
				rg.addText("Records are not available.", 0, 1, 0);
			}
			//rg.process();
			//rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rg.createReport(response);
		//return rg;
	}

	/**
	 * // GET EMP_ID THOSE FULLFILL SORTING CRITERIA
	 * @param salReg
	 * @return
	 */

	public Object[][] getEmpId(SalaryRegister salReg) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 * 
			 */
			String selectSalary = " SELECT HRMS_SALARY_"
					+ salReg.getYear()
					+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME "
					+ " FROM HRMS_SALARY_" + salReg.getYear()
					+ " inner join HRMS_EMP_OFFC  on HRMS_SALARY_"
					+ salReg.getYear() + ".EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
					" WHERE EMP_PAYBILL IS NOT  NULL ";
			String where = " ";
			try {
				where = where + " AND SAL_MONTH=" + salReg.getMonth();
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

	/**
	 * Following function is used in getReportDataNoBranchDept() method to
	 * return the employee details.
	 * @param selectSalary
	 * @return
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

	/**
	 *  Following function is called when branch or department is not selected at
	 * This method returns the salary amount and the corresponding credit code
	 * @param year
	 * @param month
	 * @param empIdString
	 * @return
	 */
	public HashMap getSalaryCreditDataNoSelect(String year,	String month, String empIdString) {
		Object[][] credit_amount = null;
		HashMap creditAmountMap=null;
		try {
			
			
			String creditsQuery = "SELECT  HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE, NVL(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT,0)," 
					+ " HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI, HRMS_SAL_CREDITS_"+year+".EMP_ID "
					+ " FROM HRMS_CREDIT_HEAD " 
					+ " LEFT JOIN HRMS_SAL_CREDITS_"+year+" ON (HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE)"  
					+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_SAL_CREDITS_"+year+".SAL_MONTH="+month+" AND HRMS_SAL_CREDITS_"+year+".SAL_YEAR="+year
					+ Utility.getConcatenatedIds("HRMS_SAL_CREDITS_"+year+".EMP_ID",empIdString)
					+" ORDER BY HRMS_SAL_CREDITS_"+year+".EMP_ID";
			
			creditAmountMap = getSqlModel().getSingleResultMap(creditsQuery, 3, 2 );
			
		} catch (Exception e) {
			credit_amount = new Object[0][0];
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return creditAmountMap;
	}
	
	/**
	 * // Following function is called branch or department not selected at all.
	* Following function is called in getReportDataNoBrnDept() method which
	* returns the debit head amount and debit code
	 * @param year
	 * @param month
	 * @param empIdString
	 * @return
	 */
	public HashMap getSalDebitNotSelect(String year, String month, String empIdString) {
		Object[][] debit_amount = null;
		HashMap debitAmountMap = null;
		try {
			
			
			  String debitsQuery = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT,0) ,HRMS_SAL_DEBITS_"+year+".EMP_ID  FROM HRMS_DEBIT_HEAD " 
						  + " LEFT JOIN HRMS_SAL_DEBITS_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE ) " 
						  + " WHERE HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' AND HRMS_SAL_DEBITS_"+year+".SAL_MONTH="+month+" AND HRMS_SAL_DEBITS_"+year+".SAL_YEAR="+year
						  + Utility.getConcatenatedIds("HRMS_SAL_DEBITS_"+year+".EMP_ID",empIdString)
						  +" ORDER BY HRMS_SAL_DEBITS_"+year+".EMP_ID ";
			
			  debitAmountMap = getSqlModel().getSingleResultMap(debitsQuery, 2, 2 );
		} catch (Exception e) {
			debit_amount = new Object[0][0];
			logger.error(e.getMessage());

		}
		return debitAmountMap;

	}
	
	/*
	 * Following function is called to return the arrear credit amount from
	 * HRMS_ARREARS_CREDIT_Year when the arrear type is
	 * promotional.
	 */
	public Object[][] promArrearCreditData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			String promCode, String salYear, String payType) {
		String arrPayType="A";
		try{
			arrPayType=String.valueOf(payType.charAt(0));
		}catch (Exception e) {
			// TODO: handle exception
		}
		Object[][] amt = new Object[arrear.length][2];
		String query = " SELECT NVL(ARREARS_AMT,0),CREDIT_APPLICABLE_ESI,ARREARS_CREDIT_CODE FROM HRMS_ARREARS_CREDIT_"
			+ salYear
			+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"
			+ salYear
			+ ".ARREARS_CODE)"
			+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ARREARS_CREDIT_CODE)"
			+ " WHERE HRMS_ARREARS_CREDIT_"
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
			/*+ " AND ARREARS_CREDIT_CODE="
			+ arrear[i][0]*/
			+ "  AND PROM_CODE="
			+ promCode
			+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE="
			+ "'"
			+ type
			+ "' AND HRMS_ARREARS_CREDIT_"+ salYear+".ARREARS_PAY_TYPE="
			+ "'"
			+ arrPayType
			+ "' AND ARREARS_PAY_IN_SAL = 'Y' ORDER BY ARREARS_CREDIT_CODE";
		HashMap arrearCreditMap=getSqlModel().getSingleResultMap(query, 2, 2);
		try {
			
			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					

					Object[][] amount = (Object[][]) arrearCreditMap.get(String.valueOf(arrear[i][0]));
					if (amount == null || amount.length==0) {
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
	 * HRMS_ARREARS_CREDIT_Year when the promotional type is
	 * monthly.
	 */
	public Object[][] arrearCreditData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			String salYear,String payType) {
		Object[][] amt = new Object[arrear.length][2];
		String arrPayType="A";
		try{
			arrPayType=String.valueOf(payType.charAt(0));
		}catch (Exception e) {
			// TODO: handle exception
		}
		String query = " SELECT DISTINCT NVL(ARREARS_AMT,0),CREDIT_APPLICABLE_ESI,ARREARS_CREDIT_CODE FROM HRMS_ARREARS_CREDIT_"
			+ salYear
			+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"
			+ salYear
			+ ".ARREARS_CODE)"
			+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ARREARS_CREDIT_CODE)"
			+ " WHERE HRMS_ARREARS_CREDIT_"
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
			/*+ " AND ARREARS_CREDIT_CODE="
			+ arrear[i][0]*/
			+ " AND ARREARS_TYPE="
			+ "'"
			+ type
			+ "' AND HRMS_ARREARS_CREDIT_"+ salYear+".ARREARS_PAY_TYPE="
			+ "'"
			+ arrPayType
			+ "' AND ARREARS_PAY_IN_SAL = 'Y' ORDER BY ARREARS_CREDIT_CODE";
		HashMap arrearCreditMap=getSqlModel().getSingleResultMap(query, 2, 2);
		try {

			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					

					Object[][] amount = (Object[][]) arrearCreditMap.get(String.valueOf(arrear[i][0]));
					if (amount == null || amount.length==0) {
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
			e.printStackTrace();
		}
		return amt;
	}

	/*
	 * Following function is called to return the arrear debit amount from
	 * HRMS_ARREARS_DEBIT_Year when the arrear type is
	 * promotional.
	 */
	public Object[][] promArrearDebitData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			String promCode, String salYear, String payType) {
		String arrPayType="A";
		try{
			arrPayType=String.valueOf(payType.charAt(0));
		}catch (Exception e) {
			// TODO: handle exception
		}
		Object[][] amt = new Object[arrear.length][2];
		String query = " SELECT DISTINCT NVL(ARREARS_AMT,0),ARREARS_DEBIT_CODE FROM HRMS_ARREARS_DEBIT_"
			+ salYear
			+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"
			+ salYear
			+ ".ARREARS_CODE)"
			+ " WHERE HRMS_ARREARS_DEBIT_"
			+ salYear
			+ ".ARREARS_EMP_ID="
			+ empId
			+ " AND HRMS_ARREARS_DEBIT_"
			+ salYear
			+ ".ARREARS_CODE="
			+ arrearCode
			+ " AND "
			+ " HRMS_ARREARS_DEBIT_"
			+ salYear
			+ ".ARREARS_MONTH="
			+ month
			+ " AND HRMS_ARREARS_DEBIT_"
			+ salYear
			+ ".ARREARS_YEAR="
			+ year
			/*+ " AND ARREARS_DEBIT_CODE="
			+ arrear[i][0]*/
			+ " AND PROM_CODE="
			+ promCode
			+ " AND ARREARS_TYPE="
			+ "'"
			+ type
			+ "' AND HRMS_ARREARS_DEBIT_"+ salYear+".ARREARS_PAY_TYPE="
			+ "'"
			+ arrPayType
			+ "' AND ARREARS_PAY_IN_SAL = 'Y' ORDER BY ARREARS_DEBIT_CODE";
		HashMap arrearsMap=getSqlModel().getSingleResultMap(query, 1, 2);
		try {
			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					/*String query = " SELECT DISTINCT NVL(ARREARS_AMT,0),ARREARS_DEBIT_CODE FROM HRMS_ARREARS_DEBIT_"
							+ salYear
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"
							+ salYear
							+ ".ARREARS_CODE)"
							+ " WHERE HRMS_ARREARS_DEBIT_"
							+ salYear
							+ ".ARREARS_EMP_ID="
							+ empId
							+ " AND HRMS_ARREARS_DEBIT_"
							+ salYear
							+ ".ARREARS_CODE="
							+ arrearCode
							+ " AND "
							+ " HRMS_ARREARS_DEBIT_"
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
							+ " AND ARREARS_TYPE="
							+ "'"
							+ type
							+ "' AND ARREARS_PAY_IN_SAL = 'Y'";*/
					Object[][] amount = (Object[][])arrearsMap.get(String.valueOf(arrear[i][0]));
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
	 * HRMS_ARREARS_DEBIT_Year when the arrear type is monthly.
	 */
	public Object[][] arrearDebitData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			String salYear,String payType) {
		String arrPayType="A";
		try{
			arrPayType=String.valueOf(payType.charAt(0));
		}catch (Exception e) {
			// TODO: handle exception
		}
		Object[][] amt = new Object[arrear.length][2];
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
			/*+ " AND ARREARS_DEBIT_CODE="
			+ arrear[i][0]*/
			+ " and arrears_type="
			+ "'"
			+ type
			+ "' AND HRMS_ARREARS_DEBIT_"+ salYear+".ARREARS_PAY_TYPE="
			+ "'"
			+ arrPayType
			+ "' AND ARREARS_PAY_IN_SAL = 'Y'";
		HashMap arrearDebitMap=getSqlModel().getSingleResultMap(query, 1, 2);
		try {
			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					
					Object[][] amount =(Object[][]) arrearDebitMap.get(String.valueOf(arrear[i][0]));
					if (amount == null || amount.length==0) {
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
			String month, SalaryRegister salReg, String Id) {
		Object[][] credit_amount = null;
		Object[][] ledgCode = null;
		String ledgerCode = null;

		try {
			if (!(salReg.getBranchCode().equals(""))
					&& salReg.getDeptCode().equals("")) {
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_EMP_CENTER IN("
						+ salReg.getBranchCode()
						+ ") AND SAL_DEPT IN("
						+ Id
						+ ") AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ empCode;
				ledgCode = getSqlModel().getSingleResult(ledgerCode,
						new Object[0][0]);
			} else if (salReg.getBranchCode().equals("")
					&& (!(salReg.getDeptCode().equals("")))) {
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_DEPT IN("
						+ salReg.getDeptCode()
						+ ") AND SAL_EMP_CENTER IN("
						+ Id
						+ ") AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ empCode;
				ledgCode = getSqlModel().getSingleResult(ledgerCode,
						new Object[0][0]);
			} else if (!(salReg.getBranchCode().equals(""))
					&& !(salReg.getDeptCode().equals(""))) {
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_EMP_CENTER IN("
						+ salReg.getBranchCode()
						+ ") AND SAL_DEPT IN("
						+ salReg.getDeptCode()
						+ ") AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ empCode;
				ledgCode = getSqlModel().getSingleResult(ledgerCode, new Object[0][0]);

			}

			// UPDATED BY REEBA
			String sal_Amount = "SELECT SAL_CREDIT_CODE,SAL_AMOUNT,CREDIT_APPLICABLE_ESI  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_SAL_CREDITS_"
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


	/**
	 * Following method is used in getReportDataNoBranchDept() which returns the
	 * credit code,credit abbr to set the credit head names.
	 * 
	 * @return
	 */
	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {
			String selectCredit = "SELECT CREDIT_CODE,  CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
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

	

	/**
	 * Following function is called branch or department or both are selected.
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param salReg
	 * @param Id
	 * @return
	 */

	public Object[][] getSalDebit(String emp_id, String month, String year,	SalaryRegister salReg, String Id) {
		Object[][] debit_amount = null;
		Object[][] ledgCode = null;
		String ledgerCode = null;

		try {
			if (!(salReg.getBranchCode().equals("")) && salReg.getDeptCode().equals("")) { // if only branch filter is selected 
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_EMP_CENTER IN("
						+ salReg.getBranchCode()
						+ ") AND SAL_DEPT IN("
						+ Id
						+ ") AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ emp_id;
				ledgCode = getSqlModel().getSingleResult(ledgerCode,
						new Object[0][0]);
			} else if (salReg.getBranchCode().equals("") && (!(salReg.getDeptCode().equals("")))) {   // if only dept filter is selected
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_DEPT IN("
						+ salReg.getDeptCode()
						+ ") AND SAL_EMP_CENTER IN("
						+ Id
						+ ") AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ emp_id;
				ledgCode = getSqlModel().getSingleResult(ledgerCode, new Object[0][0]);
			} else if (!(salReg.getBranchCode().equals("")) && !(salReg.getDeptCode().equals(""))) {    // if both branch & dept selected
				ledgerCode = " SELECT DISTINCT SAL_LEDGER_CODE FROM HRMS_SALARY_"
						+ year
						+ " INNER JOIN HRMS_SALARY_LEDGER "
						+ " ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SALARY_"
						+ year
						+ ".SAL_LEDGER_CODE)"
						+ " WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR="
						+ year
						+ " AND SAL_EMP_CENTER IN("
						+ salReg.getBranchCode()+")"
						+ " AND SAL_DEPT IN("
						+ salReg.getDeptCode()+")"
						+ " AND LEDGER_STATUS IN ('SAL_FINAL','SAL_START','ATTN_UNLOCK') AND EMP_ID="
						+ emp_id;
				ledgCode = getSqlModel().getSingleResult(ledgerCode, new Object[0][0]);
			}
			String sal_Amount = "SELECT   DEBIT_CODE,NVL(SAL_AMOUNT,0)  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_SAL_DEBITS_"
					+ year
					+ " ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="
					+ emp_id
					+ " AND SAL_LEDGER_CODE IN("
					+ String.valueOf(ledgCode[0][0])
					+ ")) "
					+ " WHERE DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE ";
			debit_amount = getSqlModel().getSingleResult(sal_Amount,
					new Object[0][0]);

		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		return debit_amount;

	}
	
	/**
	 * Following function is called in the action class to generate the report.  branchwise& deptwise
	 * @param salReg
	 * @param response
	 * @param rg
	 * @return
	 */
	public org.paradyne.lib.report.ReportGenerator generateBranchDeptwiseReport(SalaryRegister salReg, HttpServletResponse response, org.paradyne.lib.report.ReportGenerator rg) {
		logger.info(" #################### Branch & Department #####################");
		
		String reportTitle = "Salary Register of " + salReg.getDivName()+ " for the month of "
		+ Utility.month(Integer.parseInt(salReg.getMonth())) + " "+ salReg.getYear();
		
		
		if (salReg.getReport().equals("Pdf")) {
			rg.addTextBold(reportTitle, 0, 1, 0);
		} else {
			Object[][] title = new Object[1][3];
			title[0][0] = "";
			title[0][1] = "";
			title[0][2] = reportTitle;

			int[] cols = { 20, 20, 50 };
			int[] align = { 0, 0, 1 };
			rg.tableBodyNoCellBorder(title, cols, align, 1);
		}

		if ((!(salReg.getBranchCode().equals("")) && salReg.getDeptCode().equals(""))) {
			String selectQuery = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT";
			Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
			rg = reportDataBranch(loop_data, rg, salReg);
		} else if (salReg.getBranchCode().equals("") && (!(salReg.getDeptCode().equals("")))) {
			String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
			Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
			rg = reportDataDept(loop_data, rg, salReg);
		} else if (!(salReg.getBranchCode().equals("")) && !(salReg.getDeptCode().equals(""))) {
			Object[][] loop_data = new Object[1][2];
			loop_data[0][0] = salReg.getBranchCode();
			loop_data[0][1] = salReg.getDeptCode();
			rg = reportDataBraDept(loop_data, rg, salReg);
		} else if (salReg.getBranchCode().equals("") && salReg.getDeptCode().equals("")) {
			String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
			Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
			rg = reportDataNoSelect(loop_data, rg, salReg);
		}
		return rg;
	}

	/*
	 * Following function is called when department is not selected and branch
	 * selected.
	 */
	public org.paradyne.lib.report.ReportGenerator reportDataBranch(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg,	SalaryRegister salReg) {
		ArrayList<String> totList = new ArrayList<String>();
		int recCount = 0; 
		int arrEmpLength = 0;
		int check = 0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		String mapKey = "SAL_DEPT"; 	
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
		
		String finalHeader[] = null;
		int colTotal = 0;
		if(loop_data.length>0){

			for (int a = 0; a < loop_data.length; a++) {
				try {
					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get(String.valueOf(loop_data[a][0]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(arrEmpChk!=null && arrEmpChk.length > 0){
						arrEmpLength = arrEmpChk.length;
					}else{
						arrEmpLength = 0;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				Object[][] empData = null;
				try {
					empData = (Object[][]) employeeDataMap.get(String.valueOf(loop_data[a][0]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Object[][] reportDataPay = null;
				String empIdString="0";
				
				try {
					for (int empCount = 0; empCount < empData.length; empCount++) {
						empIdString += ","
								+ String.valueOf(empData[empCount][0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// get various salary & arrears details for employees
				Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
				
				// get the final report data
				if(empData!=null && empData.length >0 ){
					reportDataPay = getReportData(empData,
							salReg, String.valueOf(loop_data[a][0]), arrEmpLength,
							check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
				}
				
				if (reportDataPay != null && reportDataPay.length > 0 ) {  // if records available

					int headerLength = 0;
					int[] cellWidth = null;
					int[] alignment = null;
					if (salReg.getChkConsSummary().equals("N")) {
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
						}
					}// End of finalData loop

					Object totalByColumn[][] = null;
					String totalHeader[] = new String[finalData.length];
					totalHeader[0] = "";
					// totalHeader[1] = "";
					if (salReg.getChkConsSummary().equals("N")) {
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
					
					if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 1; //check+3-2
					} else if (salReg.getCheckEmployerPF().equals("Y")) {
						colTotal = check + 2;//check+3-1
					} else if (salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 2;//check+3-1
					}else {
						colTotal = check + 3;
					}
					// loop to sum up the credit values
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
						if (salReg.getChkConsSummary().equals("N")) {
							totalByColumn[0][i] = Utility.twoDecimals(formatter
									.format(total));
						} else {
							totalByColumn[0][i - 1] = Utility
									.twoDecimals(formatter.format(total));
						}
					}// End of outer for loop


					rg.addText("Branch : " + salReg.getBranchName()
							+ "   Department : " + loop_data[a][1], 0, 0, 0);
					
					if (salReg.getChkConsSummary().equals("N")) {
						rg.tableBody(finalHeader, finalData, cellWidth,	alignment);
						rg.tableBody(totalByColumn, cellWidth, alignment);
						
					} else {
						
						rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
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
				if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getChkConsSummary().equals("N")) {
						grand_total[0][i + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					} else {
						grand_total[0][(i - 1) + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					}
				}// End of outer loop
				int[] cellWidth = getCellWidth(grand_total[0].length);
				int[] alignment = getAlignment(grand_total[0].length);


				finalHeader[1]="";
				rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
			}else {
				rg.addText("Records are not available.",0,1,0);

			}
		} // End of loop_data condition
		
		return rg;
	}

	/*
	 * Following function is called when department is selected and branch not
	 * selected.
	 */

	public org.paradyne.lib.report.ReportGenerator reportDataDept(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg, SalaryRegister salReg) {
		
		String finalHeader[] = null;
		ArrayList<String> totList = new ArrayList<String>();
		int recCount = 0;
		int arrEmpLength = 0;
		int check = 0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		String mapKey = "SAL_EMP_CENTER "; 	
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
		int colTotal = 0;
		if (loop_data.length > 0) {
			for (int a = 0; a < loop_data.length; a++) {

				try {
					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get(String.valueOf(loop_data[a][0]));
					} catch (Exception e) {
						e.printStackTrace();
					}
									
					if(arrEmpChk != null && arrEmpChk.length > 0){
						arrEmpLength = arrEmpChk.length;
					}else{
						arrEmpLength = 0;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				
				Object[][] empData = null;
				try {
					empData = (Object[][]) employeeDataMap.get(String.valueOf(loop_data[a][0]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Object[][] reportDataPay = null;
				String empIdString="0";
				
				try {
					for (int empCount = 0; empCount < empData.length; empCount++) {
						empIdString += ","
								+ String.valueOf(empData[empCount][0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				// get various salary & arrears details for employees
				Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
				
				// get the final report data
				if(empData != null && empData.length > 0){
					reportDataPay = getReportData(empData,
							salReg, String.valueOf(loop_data[a][0]), arrEmpLength,
							check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
				}
				 
				if (reportDataPay != null && reportDataPay.length > 0) {
					int headerLength = 0;
					int[] cellWidth = null;
					int[] alignment = null;
					if (salReg.getChkConsSummary().equals("N")) {
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
						}
					}

					Object totalByColumn[][] = null;
					String totalHeader[] = new String[finalData.length];
					totalHeader[0] = "";
					if (salReg.getChkConsSummary().equals("N")) {
						totalByColumn = new Object[1][finalData[0].length];
						totalByColumn[0][0] = "TOTAL :-";
						totalByColumn[0][1] = "No. of Employees:"
								+ finalData.length;
					} else {
						totalByColumn = new Object[1][finalData[0].length - 1];
						totalByColumn[0][0] = "TOTAL :-";
						totalByColumn[0][1] = finalData.length;
					}
					
					if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 1;
					} else if (salReg.getCheckEmployerPF().equals("Y")) {
						colTotal = check + 2;
					} else if (salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 2;
					}else {
						colTotal = check + 3;
					}

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
						totList.add(Utility
								.twoDecimals(formatter.format(total)));
						if (salReg.getChkConsSummary().equals("N")) {
							totalByColumn[0][i] = Utility.twoDecimals(formatter
									.format(total));
						} else {
							totalByColumn[0][i - 1] = Utility
									.twoDecimals(formatter.format(total));
						}
					}// End of outer loop

					rg.addText("Branch : " + loop_data[a][1]+ "   Department : " + salReg.getDeptName(), 0, 0, 0);
					
					
					if (salReg.getChkConsSummary().equals("N")) {
						rg.tableBody(finalHeader, finalData, cellWidth,	alignment);
						rg.tableBody(totalByColumn, cellWidth, alignment);
						
					} else {
						rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
						
					}
					recCount++;
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
				if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getChkConsSummary().equals("N")) {
						grand_total[0][i + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					} else {
						grand_total[0][(i - 1) + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					}
				}// End of outer loop
				int[] cellWidth = getCellWidth(grand_total[0].length);
				int[] alignment = getAlignment(grand_total[0].length);

				finalHeader[1]="";
				rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
				
			} else {
				rg.addText("Records are not available.",0,1,0);
			}
		} // End of loop_data if condition
		return rg;
	}

	/*
	 * Following function is called to generate the report when both branch and
	 * department are selected.
	 */
	public org.paradyne.lib.report.ReportGenerator reportDataBraDept(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg,
			SalaryRegister salReg) {
		
		ArrayList<String> totList = new ArrayList<String>();
		int recCount = 0;
		int arrEmpLength = 0;
		int check = 0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		String mapKey = "0"; 	
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
		
		String finalHeader[] = null;
		int colTotal = 0;
		if (loop_data.length > 0) {

			//for (int a = 0; a < loop_data.length; a++) {

				try {
					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get("0");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
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

				Object[][] empData = null;
				try {
					empData = (Object[][]) employeeDataMap.get(String
							.valueOf(loop_data[0][0])
							+ "#" + String.valueOf(loop_data[0][1]));
				} catch (Exception e) {
					e.printStackTrace();
				} 
				Object[][] reportDataPay = null;
				String empIdString="0";
				
				try {
					for (int empCount = 0; empCount < empData.length; empCount++) {
						empIdString += ","
								+ String.valueOf(empData[empCount][0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				// get various salary & arrears details for employees
				Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
				// get the final report data
				if(empData != null && empData.length > 0){
					reportDataPay = getReportData(empData, salReg,"", arrEmpLength,
							check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
				}
				
				if (reportDataPay != null && reportDataPay.length > 0) {

					int headerLength = 0;
					int[] cellWidth = null;
					int[] alignment = null;
					if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getChkConsSummary().equals("N")) {
						totalByColumn = new Object[1][finalData[0].length];
						totalByColumn[0][0] = "TOTAL :-";
						totalByColumn[0][1] = "No. of Employees:"
								+ finalData.length;
					} else {
						totalByColumn = new Object[1][finalData[0].length - 1];
						totalByColumn[0][0] = "TOTAL :-";
						totalByColumn[0][1] = finalData.length;
					}

					if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 1; //check+3-2
					} else if (salReg.getCheckEmployerPF().equals("Y")) {
						colTotal = check + 2;//check+3-1
					} else if (salReg.getCheckEmployerESIC().equals("Y")) {
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
						if (salReg.getChkConsSummary().equals("N")) {
							totalByColumn[0][i] = Utility.twoDecimals(formatter
									.format(total));
						} else {
							totalByColumn[0][i - 1] = Utility
									.twoDecimals(formatter.format(total));
						}
					}// End of outer for loop

					rg.addText("Branch : " + salReg.getBranchName()	+ "   Department : " + salReg.getDeptName(), 0, 0, 0);
					
					
					if (salReg.getChkConsSummary().equals("N")) {
						rg.tableBody(finalHeader, finalData, cellWidth,	alignment);
						rg.tableBody(totalByColumn, cellWidth, alignment);
						
					} else {
						rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
						
					}
					recCount++;
				}// End of reportDataPay if condition
			//}// End of for loop loop_data
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
				if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getChkConsSummary().equals("N")) {
						grand_total[0][i + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					} else {
						grand_total[0][(i - 1) + colTotal] = Utility
								.twoDecimals(formatter.format(total));
					}
				}// End of for loop
				int[] cellWidth = getCellWidth(grand_total[0].length);
				int[] alignment = getAlignment(grand_total[0].length);

				finalHeader[1]="";
				rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
				
			} else {
				rg.addText("Records are not available.",0,1,0);
			}
		}// End of loop_data if condition
		
		return rg;
	}

	/** This method is used to generate the report when only branch is selected
	 * @author REEBA_JOSEPH
	 * @param loop_data
	 * @param rg
	 * @param salReg
	 */

	public org.paradyne.lib.report.ReportGenerator reportOnlyForBranch(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg,SalaryRegister salReg){
	
		ArrayList<String> totList=new ArrayList<String>();
		int recCount=0;		
		int arrEmpLength=0;
		String finalHeader[] = null;
		
		int check=0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		String mapKey = "SAL_EMP_CENTER"; 	
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
			
		int colTotal = 0;
		if (loop_data.length > 0) {
			// logger.info("Loop count ============= " + loop_data.length);
			for (int a = 0; a < loop_data.length; a++) {
				try {
					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get(String.valueOf(loop_data[a][0]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(arrEmpChk != null && arrEmpChk.length > 0){
						arrEmpLength = arrEmpChk.length;
					}else{
						arrEmpLength = 0;
					}
				} catch (Exception e) {
					logger.error("Error in arrears query : " + e);
					e.printStackTrace();
				}
				Object[][] empData = null;
				try {
					empData = (Object[][]) employeeDataMap.get(String.valueOf(loop_data[a][0]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Object[][] reportDataPay = null;
				String empIdString="0";
				
				try {
					for (int empCount = 0; empCount < empData.length; empCount++) {
						empIdString += ","
								+ String.valueOf(empData[empCount][0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// get various salary & arrears details for employees
				Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
				if(empData != null && empData.length > 0){
					reportDataPay = getReportDataNoBranchDept(empData, salReg,arrEmpLength, check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
				}

				if (reportDataPay != null && reportDataPay.length >0) {
					
					int headerLength = 0;
					int[] cellWidth = null;
					int[] alignment = null;
					if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 1; //check+3-2
					} else if (salReg.getCheckEmployerPF().equals("Y")) {
						colTotal = check + 2;//check+3-1
					} else if (salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 2;//check+3-1
					}else
						colTotal = check + 3;

					// loop to calculate the total credit wise amount 
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
						if (salReg.getChkConsSummary().equals("N")) {
							totalByColumn[0][i] = Utility.twoDecimals(formatter
									.format(total));
						} else {
							totalByColumn[0][i - 1] = Utility
									.twoDecimals(formatter.format(total));
						}
					}// End of outer loop
					rg.addText("Branch : " + loop_data[a][1], 0, 0, 0);

											
					if (salReg.getChkConsSummary().equals("N")) {
						rg.tableBody(finalHeader, finalData, cellWidth, alignment);
										
						rg.tableBody(totalByColumn, cellWidth, alignment);
					} else {
						rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
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
				
				if (salReg.getChkConsSummary().equals("N")) {
					grand_total = new Object[1][listValues[0].length
					    						+ colTotal ];
					
				}else{
					grand_total = new Object[1][listValues[0].length
					    						+ colTotal - 1];
				}

				grand_total[0][0] = "GRAND TOTAL :-";
				grand_total[0][1] = " ";
				
				// loop to calculate the grand total

				for (int i = 0; i < listValues[0].length; i++) {
					double total = 0.00;
					for (int j = 0; j < listValues.length; j++) {
						if (String.valueOf(listValues[j][i]).equals("null")) {
							listValues[j][i] = "0.00";
						}
						total += Double.parseDouble(String
								.valueOf(listValues[j][i]));
					}
					if (salReg.getChkConsSummary().equals("N")) {
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
				rg.tableBody(finalHeader,grand_total, cellWidth, alignment);
				
			} else {
				rg.addText("Records are not available.",0,1,0);
			}
		} // End of loop_data if condition
		
		return rg;
	}

	/**
	 * This method is used to get all the information for passed employees for month & year filter
	 * which includes salary credit-debit, arrears credit-debit,ESI data,PF data
	 * @param empIdString
	 * @param year
	 * @param month
	 * @return
	 */
	private Vector getDataVector(String empIdString,String year,String month) {
		/*String empIdString="0";
		
		for (int empCount = 0; empCount < emp_id.length; empCount++) {
			empIdString+=","+String.valueOf(emp_id[empCount][0]);
		}*/
		
	Vector dataVector=new Vector();	
		/* SAL CREDIT MAP*/
	HashMap salCreditMap = getSalaryCreditDataNoSelect(year, month,empIdString);
	dataVector.add(salCreditMap);
		/* SAL DEBIT MAP*/
	HashMap salDebitMap = getSalDebitNotSelect(year, month,empIdString);
	dataVector.add(salDebitMap);
		
		/*
		 * Following loop sets the corresponding values to the headers
		 */

	
		String arrearsQuery = "SELECT NVL(SUM(case WHEN HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_DAYS else ARREARS_DAYS end),0), HRMS_ARREARS_"+year+".EMP_ID  FROM HRMS_ARREARS_"+year
		+" INNER JOIN HRMS_ARREARS_LEDGER ON  (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
		+" WHERE 1=1 "+ Utility.getConcatenatedIds("HRMS_ARREARS_"+year+".EMP_ID",empIdString) 
		+ " AND ARREARS_PAID_MONTH="+ month+ " AND ARREARS_PAID_YEAR="+ year+ " AND ARREARS_PAY_IN_SAL = 'Y'"
			+ " GROUP BY HRMS_ARREARS_"+year+".EMP_ID ORDER BY HRMS_ARREARS_"+year+".EMP_ID ";
		HashMap arrearDaysMap = getSqlModel().getSingleResultMap(arrearsQuery, 1, 2 );
		dataVector.add(arrearDaysMap);
		
		String arrearsAmtQuery = " SELECT NVL(SUM(case WHEN HRMS_ARREARS_CREDIT_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_AMT else ARREARS_AMT end),0),ARREARS_EMP_ID||'#'||ARREARS_CREDIT_CODE from HRMS_ARREARS_CREDIT_"
		+ year
		+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
		+ " (HRMS_ARREARS_CREDIT_"
		+ year
		+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE 1=1 "+ Utility.getConcatenatedIds("ARREARS_EMP_ID",
		empIdString) 
		+ " AND ARREARS_PAID_MONTH="
		+ month
		+ " AND ARREARS_PAID_YEAR="
		+ year
		+ " AND ARREARS_TYPE IN('M','P')"
		+ " AND ARREARS_PAY_IN_SAL = 'Y' GROUP BY ARREARS_EMP_ID,ARREARS_CREDIT_CODE" ;

		HashMap arrearsAmtMap = getSqlModel().getSingleResultMap(arrearsAmtQuery, 1, 2);
		dataVector.add(arrearsAmtMap);
		String arrearsDebitQuery = " SELECT NVL(SUM(case WHEN HRMS_ARREARS_DEBIT_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_AMT else ARREARS_AMT end),0),ARREARS_EMP_ID||'#'||ARREARS_DEBIT_CODE from HRMS_ARREARS_DEBIT_"
		+ year
		+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
		+ " (HRMS_ARREARS_DEBIT_"
		+ year
		+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE 1=1 "+ Utility.getConcatenatedIds("ARREARS_EMP_ID",
		empIdString) 
		+ " AND ARREARS_PAID_MONTH="
		+ month
		+ " AND ARREARS_PAID_YEAR="
		+ year
		+ " AND ARREARS_TYPE IN('M','P')"
		+ " AND ARREARS_PAY_IN_SAL = 'Y' GROUP BY ARREARS_EMP_ID||'#'||ARREARS_DEBIT_CODE";
		HashMap arrearsDebitMap= getSqlModel().getSingleResultMap(arrearsDebitQuery, 1, 2);
		dataVector.add(arrearsDebitMap);
		String arrear = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,ARREARS_TYPE,"
		+ "ARREARS_PROMCODE,DECODE(NVL(HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE,'A'),'A','Arrears',"
		+ " 'R','Recovery', 'O','Onhold Salary released'),EMP_ID FROM HRMS_ARREARS_"
		+ year
		+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
		+ year
		+ ".ARREARS_CODE)"
		+ " WHERE ARREARS_PAID_MONTH="
		+ month
		+ " AND ARREARS_PAID_YEAR="
		+ year
		+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "+ Utility.getConcatenatedIds("EMP_ID",
				empIdString) 
		//+ String.valueOf(emp_id[i][0])
		+ " ORDER BY HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR";
		HashMap arrearCodeMap = getSqlModel().getSingleResultMap(arrear, 7, 2)	;
		dataVector.add(arrearCodeMap);
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
		dataVector.add(esi_data);
		Object[][] pf_data = null;
		try {
			String pf_query = " SELECT PF_CODE, PF_DATE, PF_PERCENTAGE, PF_DEBIT_CODE FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'DD-MON-YYYY')  = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'YYYY-MM') <='"
				+ year
				+ "-" + month + "') ";
			pf_data = getSqlModel().getSingleResult(pf_query);
			dataVector.add(pf_data);
		} catch (Exception e) {
			logger.error("Error in calculation employer PF: " + e);
		}
		return dataVector;
	}

	/**
	 * Following function is used to set cell width
	 * @param dataLength
	 * @return
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

	/**
	 * Following function is used to set the alignment
	 * @param dataLength
	 * @return
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
	 * @param salReg
	 * @param arrEmpLength
	 * @param colTotal
	 * @param salReg
	 * @return
	 */

	public Object[][] getReportDataUnCheck(String query, String month,
			String year, String brnChk, String deptChk, String desgChk,
			String dojChk, String dobChk, String empTypeChk, String bankChk,
			String accountChk, String panChk, String genderChk,
			String onHoldChk, String gradeChk,String empGradeChk, String chekFlag,
			int arrEmpLength, int colTotal, String divisionName,
			String summaryChk, String PFChk, String ESICChk, String paybillChk, String costCenterChk, String subCostCenterChk) {
		int dataIndex = 0;

		Object[][] dataWithHeader = null;
		Object[][] arrearCreditAmt = null;
		Object[][] arrearDebitAmt = null;
		Object [][]roundObject=null;
		String roundQuery="SELECT NVL(CONF_TOTCREDIT_OPTION,0), NVL(CONF_DEBIT_ROUND,0), NVL(CONF_TOTDEBIT_ROUND,0), NVL(CONF_NETPAY_ROUND,0)"
						+" FROM HRMS_SALARY_CONF ";
		roundObject=getSqlModel().getSingleResult(roundQuery);
		try {
			Object emp_id[][] = getEmpIdNew(query);

			Object credit_header[][] = getCreditHeader();
			Object debit_header[][] = getDebitHeader();
			double totArrearAmt = 0;
			// Object debit_recovery[][]= getDebitHeader_recovery();
			int totalCol = credit_header.length + debit_header.length + colTotal + 6;
			int counter = 0;
			String[] colNames = null;
			// UPDATED BY REEBA BEGINS
			if (summaryChk.equals("N")) {
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
			if (brnChk.equals("Y")) {

				colNames[counter] = "Branch";
				counter = counter + 1;
			}

			if (deptChk.equals("Y")) {

				colNames[counter] = "Dept";
				counter = counter + 1;
			}

			if (desgChk.equals("Y")) {

				colNames[counter] = "Desg";
				counter = counter + 1;
			}

			if (dojChk.equals("Y")) {
				colNames[counter] = "Date of\nJoining";
				counter = counter + 1;
			}

			if (dobChk.equals("Y")) {

				colNames[counter] = "Date of\nBirth";
				counter = counter + 1;
			}
			if (empTypeChk.equals("Y")) {

				colNames[counter] = "Emp Type";
				counter = counter + 1;
			}

			if (bankChk.equals("Y")) {

				colNames[counter] = "Bank";
				counter = counter + 1;
			}

			if (accountChk.equals("Y")) {

				colNames[counter] = "Acc. No.";
				counter = counter + 1;
			}

			if (panChk.equals("Y")) {

				colNames[counter] = "Pan No.";
				counter = counter + 1;
			}

			if (genderChk.equals("Y")) {
				colNames[counter] = "Gender";
				counter = counter + 1;
			}

			if (onHoldChk.equals("Y")) {
				colNames[counter] = "On Hold";
				counter = counter + 1;
			}

			if (gradeChk.equals("Y")) {
				colNames[counter] = "Salary Grade";
				counter = counter + 1;
			}
			//Added by ganesh
			if (empGradeChk.equals("Y")) {
				colNames[counter] = "Grade";
				counter = counter + 1;
			}
			
			//Added by ganesh end
			if (paybillChk.equals("Y")) {
				colNames[counter] = "Paybill";
				counter = counter + 1;
			}
			
			if (costCenterChk.equals("Y")) {
				colNames[counter] = "Cost Center";
				counter = counter + 1;
			}
			if (subCostCenterChk.equals("Y")) {
				colNames[counter] = "Sub Cost Center";
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
			counter = counter + 1;

			// UPDATED BY REEBA BEGINS
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
			if (chekFlag.equals("N")) {
				data = new Object[emp_id.length + arrEmpLength][totalCol];
			} else {
				data = new Object[emp_id.length][totalCol];
			}
			String empIdString="0";
			try {
				for (int empCount = 0; empCount < emp_id.length; empCount++) {
					empIdString += "," + String.valueOf(emp_id[empCount][0]);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			HashMap salCreditMap = getSalaryCreditDataNoSelect(year, month,empIdString);
			/* SAL DEBIT MAP*/
			HashMap salDebitMap = getSalDebitNotSelect(year, month,empIdString);
			
			HashMap leaveEncashMap = getLeaveEncashmentDataMap(year, month,empIdString);
			
			/*
			 * Following loop sets the corresponding values to the headers
			 */
			HashMap arrearDaysMap=new HashMap();
			HashMap arrearsAmtMap=new HashMap();
			HashMap arrearsDebitMap=new HashMap();
			if (chekFlag.equals("Y")) {
			String arrearsQuery = "SELECT NVL(SUM(case WHEN HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_DAYS else ARREARS_DAYS end),0), HRMS_ARREARS_"+year+".EMP_ID  FROM HRMS_ARREARS_"+year
						+" INNER JOIN HRMS_ARREARS_LEDGER ON  (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
						+" WHERE 1=1 "+ Utility.getConcatenatedIds("HRMS_ARREARS_"+year+".EMP_ID",empIdString) 
						+ " AND ARREARS_PAID_MONTH="+ month+ " AND ARREARS_PAID_YEAR="+ year+ " AND ARREARS_PAY_IN_SAL = 'Y'"
						+ " GROUP BY HRMS_ARREARS_"+year+".EMP_ID ORDER BY HRMS_ARREARS_"+year+".EMP_ID ";
			arrearDaysMap = getSqlModel().getSingleResultMap(arrearsQuery, 1, 2 );
			
			
			String arrearsAmtQuery = " SELECT NVL(SUM(case WHEN HRMS_ARREARS_CREDIT_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_AMT else ARREARS_AMT end),0),ARREARS_EMP_ID||'#'||ARREARS_CREDIT_CODE from HRMS_ARREARS_CREDIT_"
				+ year
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
				+ " (HRMS_ARREARS_CREDIT_"
				+ year
				+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE 1=1 "+ Utility.getConcatenatedIds("ARREARS_EMP_ID",
						empIdString) 
				+ " AND ARREARS_PAID_MONTH="
				+ month
				+ " AND ARREARS_PAID_YEAR="
				+ year
				+ " AND ARREARS_TYPE IN('M','P')"
				+ " AND ARREARS_PAY_IN_SAL = 'Y' GROUP BY ARREARS_EMP_ID,ARREARS_CREDIT_CODE" ;

		 arrearsAmtMap = getSqlModel().getSingleResultMap(arrearsAmtQuery, 1, 2);
			
		String arrearsDebitQuery = " SELECT NVL(SUM(case WHEN HRMS_ARREARS_DEBIT_"+year+".ARREARS_PAY_TYPE='R' then -ARREARS_AMT else ARREARS_AMT end),0),ARREARS_EMP_ID||'#'||ARREARS_DEBIT_CODE from HRMS_ARREARS_DEBIT_"
				+ year
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
				+ " (HRMS_ARREARS_DEBIT_"
				+ year
				+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE 1=1 "+ Utility.getConcatenatedIds("ARREARS_EMP_ID",
						empIdString) 
				+ " AND ARREARS_PAID_MONTH="
				+ month
				+ " AND ARREARS_PAID_YEAR="
				+ year
				+ " AND ARREARS_TYPE IN('M','P')"
				+ " AND ARREARS_PAY_IN_SAL = 'Y' GROUP BY ARREARS_EMP_ID||'#'||ARREARS_DEBIT_CODE";
			arrearsDebitMap= getSqlModel().getSingleResultMap(arrearsDebitQuery, 1, 2);
			}
			String arrear = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,ARREARS_TYPE,"
				+ "ARREARS_PROMCODE,DECODE(NVL(HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE,'A'),'A','Arrears',"
				+ " 'R','Recovery', 'O','Onhold Salary released'),EMP_ID FROM HRMS_ARREARS_"
				+ year
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
				+ year
				+ ".ARREARS_CODE)"
				+ " WHERE ARREARS_PAID_MONTH="
				+ month
				+ " AND ARREARS_PAID_YEAR="
				+ year
				+ " and arrears_type in('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "+ Utility.getConcatenatedIds("EMP_ID",
								empIdString) 
				//+ String.valueOf(emp_id[i][0])
				+ " ORDER BY HRMS_ARREARS_"+year+".EMP_ID,ARREARS_MONTH,ARREARS_YEAR";
			HashMap arrearCodeMap = getSqlModel().getSingleResultMap(arrear, 7, 2)	;
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
				// query for PF data
				String pf_query = " SELECT PF_CODE, PF_DATE, PF_PERCENTAGE, PF_DEBIT_CODE FROM HRMS_PF_CONF "
					+ " WHERE TO_CHAR(PF_DATE,'DD-MON-YYYY')  = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF "
					+ " WHERE TO_CHAR(PF_DATE,'YYYY-MM') <='"
					+ year
					+ "-" + month + "') ";
				pf_data = getSqlModel().getSingleResult(pf_query);
			} catch (Exception e) {
				logger.error("Error in calculation employer PF: " + e);
			}
			/*
			 * Following loop sets the emp id,emp name,credit head value,debit
			 * head value and arrear values.
			 */
			for (int i = 0; i < emp_id.length; i++) {
				data[dataIndex][0] = emp_id[i][1];
				data[dataIndex][1] = emp_id[i][2];
				if (chekFlag.equals("Y")) {  // for consolidated arrears
					Object[][] arrears_days = null;
					arrears_days=(Object[][])arrearDaysMap.get(String.valueOf(emp_id[i][0]));
					if (arrears_days != null && arrears_days.length > 0) {
						data[dataIndex][2] = Double.parseDouble(String
								.valueOf(arrears_days[0][0]))
								+ Double.parseDouble(String
										.valueOf(emp_id[i][4]));

					}else{
						data[dataIndex][2] = emp_id[i][4];
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
				if (brnChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][5];// Branch
					column++;
				}

				if (deptChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][6];// Department
					column++;
				}

				if (desgChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][11];// Designation
					column++;
				}

				if (dojChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][12];// Date of Joining
					column++;
				}

				if (dobChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][7];// Date of Birth
					column++;
				}
				if (empTypeChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][3];// Employee Type
					column++;
				}

				if (bankChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][8];// Bank
					column++;
				}

				if (accountChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][9];// Salary Account
															// Number
					column++;
				}

				if (panChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][10];// Pan number
					column++;
				}

				if (genderChk.equals("Y")) {
					if (String.valueOf(emp_id[i][13]).equals("")
							|| String.valueOf(emp_id[i][13]).equals("null")) {
						data[dataIndex][column] = "";
					} else {
						data[dataIndex][column] = emp_id[i][13];// Gender
					}
					column++;
				}

				if (onHoldChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][14];// On Hold
					column++;
				}

				if (gradeChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][15];// On Hold
					column++;
				}
				//added by ganesh
				if (empGradeChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][16];// On grade
					column++;
				}
				//added by ganesh end 
				if (paybillChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][17];// Paybill
					column++;
				}
				
				if (costCenterChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][18];// Cost Center
					column++;
				}
				if (subCostCenterChk.equals("Y")) {
					data[dataIndex][column] = emp_id[i][19];// Sub Cost Center
					column++;
				}
				
				/** set credit values */

				double total_credit = 0.00;
				// UPDATED BY REEBA BEGINS
				double employerESIC = 0.0;
				double esicCredit = 0.0;
				double employerPF = 0.0;
				// UPDATED BY REEBA ENDS
				/*Object salCredit[][] = getSalaryCreditDataUncheck(String
						.valueOf(emp_id[i][0]), year, month);*/
				Object salCredit[][] = (Object[][])salCreditMap.get(String.valueOf(emp_id[i][0]));
				/*Object leaveEncashData[][] = getLeaveEncashmentDataUncheck(
						String.valueOf(emp_id[i][0]), year, month);*/
				
				Object leaveEncashData[][] = null;
				
				try {
					leaveEncashData = (Object[][]) leaveEncashMap.get(String
							.valueOf(emp_id[i][0]));
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*
				 * Following loop sets the credit head values
				 */
				for (int j = 0; j < credit_header.length; j++) {
					esicCredit = 0.0;
					data[dataIndex][column] = 0.00;
					if ((salCredit != null && salCredit.length > 0))
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
							 * hrms_sal_cedits_year table
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
									if (chekFlag.equals("Y")) {

										Object[][] amt = null;
										amt = (Object[][] )arrearsAmtMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salCredit[k][0]));
										if (amt != null && amt.length > 0) {
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
										}else{
											data[dataIndex][column] = Utility
											.twoDecimals(String
													.valueOf(salCredit[k][1]));// +Double.parseDouble("0.00");
											total_credit += Double
											.parseDouble(String
													.valueOf(salCredit[k][1]));
										}
									}// End of checkFlag if condition
									else {
										data[dataIndex][column] = Utility
												.twoDecimals(String
														.valueOf(salCredit[k][1]));// +Double.parseDouble("0.00");
										total_credit += Double
												.parseDouble(String
														.valueOf(salCredit[k][1]));
									}
								}// End of salCredit if condition

							} // End of credit code comparison if condition

						} // End of salCredit for loop

					// add leave encashment data
					try {
						if (leaveEncashData != null
								&& leaveEncashData.length > 0) {
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
						//logger.error("exception in leaveEncahData " + e);
						//e.printStackTrace();
					}

					data[dataIndex][column] = checkNullToZero(String
							.valueOf(data[dataIndex][column]));
					column++;
				} // End of credit header for loop

				data[dataIndex][column] = Utility.twoDecimals(formatter
						.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][0])),total_credit)));

				column = column + 1;
				/** set debits values */
				Object salDebit[][] = (Object[][])salDebitMap.get(String
						.valueOf(emp_id[i][0]));
				double total_nonRecovery = 0;
				
				
				/*
				 * Following loop is used to set the debit head amount values.
				 */
				for (int k = 0; k < debit_header.length; k++) {
					data[dataIndex][column] = 0;
					if ((salDebit != null && salDebit.length > 0))

						for (int index = 0; index < salDebit.length; index++) {
							/*
							 * Following loop compares the debit code from
							 * hrms_debit_head with the debit code from
							 * hrms_sal_debits_Year
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
									if (chekFlag.equals("Y")) {
										Object[][] amt = null;
										 amt = (Object[][])arrearsDebitMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salDebit[index][0]));
										if (amt != null && amt.length > 0) {
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
													.twoDecimals(Double
															.parseDouble(String
																	.valueOf(salDebit[index][1])));// ;+Double.parseDouble("0.00");
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
							
							// employer PF calculation
							if (pf_data != null && pf_data.length > 0) {
								if (String.valueOf(pf_data[0][3]).equals(
										String.valueOf(salDebit[index][0]))) {
									employerPF = Double.parseDouble(checkNullToZero(String.valueOf(salDebit[index][1])));
								}
							}
							// UPDATED BY REEBA ENDS

						} // End of salDebit for loop

					column++;
				}// End of debit header for loop
				data[dataIndex][column] = formatter.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][2])),total_nonRecovery));
				double total_pay = 0;
				total_pay = Double.parseDouble(String
						.valueOf(data[dataIndex][credit_header.length + 3
								+ colTotal]))
						- Double.parseDouble(String
								.valueOf(data[dataIndex][column]));
				column = column + 1;

				data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][3])),total_pay)));
				
				// UPDATED BY REEBA BEGINS
				column = column + 1;

				if (PFChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerPF));// Employer contribution to PF
					column++;
				}

				if (ESICChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerESIC));
					;// Employer contribution to ESIC
				}
				// UPDATED BY REEBA ENDS
				
				

				position_totalPay = column;
				esicCredit =0;
				employerESIC=0;
				employerPF=0;
				try {
					/**
					 * Following condition is used to select the arrears for the
					 * employees.
					 */
					if (chekFlag.equals("N")) {
						if (arrEmpLength != 0) {
							Object[][] arrearCode = null;
							 arrearCode = (Object[][])arrearCodeMap.get(String.valueOf(emp_id[i][0]));
							if ((arrearCode != null && arrearCode.length >0)) {

								for (int arrCode = 0; arrCode < arrearCode.length; arrCode++) {
									totArrACredit = 0;
									totArrDebit = 0;
									if (String.valueOf(arrearCode[arrCode][5])
											.equals("")
											|| String.valueOf(
													arrearCode[arrCode][5])
													.equals("null")) {  // for monthly arrears
										// get arrears credit for particular month,year & type
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
										// get arrears debit for particular month,year & type
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
									} else {   // for promotional arrears
										
										// get arrears debit for particular month,year & type
										arrearDebitAmt = promArrearDebitData(
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
										// get arrears credit for particular month,year & type
										arrearCreditAmt = promArrearCreditData(
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
												year, String
												.valueOf(arrearCode[arrCode][6]));
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
									if (brnChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][5];// Branch
										arrearCol++;
									}

									if (deptChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][6];// Department
										arrearCol++;
									}

									if (desgChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][11];// Designation
										arrearCol++;
									}

									if (dojChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][12];// Date of Joining
										arrearCol++;
									}

									if (dobChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][7];// Date of Birth
										arrearCol++;
									}
									if (empTypeChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][3];// Employee Type
										arrearCol++;
									}

									if (bankChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][8];// Bank Name
										arrearCol++;
									}

									if (accountChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][9];// Salary Account No.
										arrearCol++;
									}

									if (panChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][10];// Pan Number
										arrearCol++;
									}

									if (genderChk.equals("Y")) {
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

									if (onHoldChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][14];// On Hold
										arrearCol++;
									}

									if (gradeChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][15];// sal grade
										arrearCol++;
									}
									//added by ganesh
									if (empGradeChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][16];// emp grade
																						
										arrearCol++;
									}
									if (paybillChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][17];// Paybill
										arrearCol++;
									}
									
									if (costCenterChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][18];// costcenter
										arrearCol++;
									}
									if (subCostCenterChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][19];// subcost-center
										arrearCol++;
									}
									//added by ganesh end
									/*
									 * Following loop is used to set the arrear
									 * credit amount from
									 * HRMS_ARREARS_CREDIT_Year 
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
													.equals("Y")) {   // ESIC applicable ESIC
												esicCredit += Double
														.parseDouble(String
																.valueOf(arrearCreditAmt[ac][0]));
										}
										}
										arrearCol++;
									}

									data[dataIndex + 1][arrearCol] = Utility
											.twoDecimals(formatter
													.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][0])), totArrACredit)));
									arrearCol = arrearCol + 1;

									/*
									 * Following loop is used to set the arrear
									 * debit amount from HRMS_ARREARS_DEBIT_year
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
										// employer contribution to PF
										if (pf_data != null && pf_data.length > 0) {
											if (String.valueOf(pf_data[0][3]).equals(
													String.valueOf(arrearDebitAmt[ad][1]))) {
												employerPF = Double.parseDouble(String.valueOf(arrearDebitAmt[ad][0]));
											}
										}
										arrearCol++;
									}

									data[dataIndex + 1][arrearCol] = Utility
											.twoDecimals(formatter
													.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][2])),totArrDebit)));

									totArrearAmt = Double
											.parseDouble(String
													.valueOf(data[dataIndex + 1][credit_header.length
															+ colTotal + 3]))
											- Double
													.parseDouble(String
															.valueOf(data[dataIndex + 1][arrearCol]));

									// data[dataIndex+1][arrearCol]=Utility.twoDecimals(totArrDebit);

									arrearCol = arrearCol + 1;

								
										data[dataIndex + 1][arrearCol] = Utility
												.twoDecimals(formatter
														.format(roundCheck(Integer.parseInt(String.valueOf(roundObject[0][3])),totArrearAmt)));
								

									// UPDATED BY REEBA BEGINS
									arrearCol++;
									
									if (PFChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerPF));// Employer contribution to PF
										arrearCol++;
									}

									if (ESICChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerESIC));// Employer contribution to ESIC
									}
									// UPDATED BY REEBA ENDS
									

									dataIndex++;
								} // End of arrear code for loop
							}// End of arrear code if condition
						}// End of arrearEmpLength if condition
					}// End of check flag
					
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
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

	/**
	 *  Following function is called when branch is selected or department is
	 * selected or both got selected for credit and debit details of the employee
	 * @param emp_id
	 * @param salReg
	 * @param Id
	 * @param arrEmpLength
	 * @param colTotal
	 * @param PFChk
	 * @param ESICChk
	 * @param dataVector
	 * @return
	 */
	public Object[][] getReportData(Object emp_id[][], SalaryRegister salReg,
			String Id, int arrEmpLength, int colTotal, String PFChk, String ESICChk,Vector dataVector) {
		int dataIndex = 0;
		String year = salReg.getYear();
		String month = salReg.getMonth();

		Object[][] dataWithHeader = null;
		Object[][] arrearCreditAmt = null;
		Object[][] arrearDebitAmt = null;
		HashMap salCreditMap=(HashMap)dataVector.get(0);
		HashMap salDebitMap=(HashMap)dataVector.get(1);
		HashMap arrearDaysMap=(HashMap)dataVector.get(2);
		HashMap arrearsAmtMap=(HashMap)dataVector.get(3);
		HashMap arrearsDebitMap=(HashMap)dataVector.get(4);
		HashMap arrearCodeMap=(HashMap)dataVector.get(5);
		Object[][] esi_data = (Object[][])dataVector.get(6);
		Object[][] pf_data = (Object[][])dataVector.get(7);
		try {
			//Object emp_id[][] = getEmpIdNew(query);

			Object credit_header[][] = getCreditHeader();
			Object debit_header[][] = getDebitHeader();
			double totArrearAmt = 0;
			// Object debit_recovery[][]= getDebitHeader_recovery();
			int totalCol = credit_header.length + debit_header.length
					+ colTotal + 6;
			String[] colNames = null;
			int counter = 0;

			// UPDATED BY REEBA BEGINS
			if (salReg.getChkConsSummary().equals("N")) {
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
			if (salReg.getCheckBrn().equals("Y")) {

				colNames[counter] = "Branch";
				counter = counter + 1;
			}

			if (salReg.getCheckDept().equals("Y")) {

				colNames[counter] = "Dept";
				counter = counter + 1;
			}

			if (salReg.getCheckDesg().equals("Y")) {

				colNames[counter] = "Desg";
				counter = counter + 1;
			}

			if (salReg.getCheckDoj().equals("Y")) {
				colNames[counter] = "Date of\nJoining";
				counter = counter + 1;
			}

			if (salReg.getCheckDob().equals("Y")) {

				colNames[counter] = "Date of\nBirth";
				counter = counter + 1;
			}
			if (salReg.getCheckEmpType().equals("Y")) {

				colNames[counter] = "Emp Type";
				counter = counter + 1;
			}

			if (salReg.getCheckBank().equals("Y")) {

				colNames[counter] = "Bank";
				counter = counter + 1;
			}

			if (salReg.getCheckAccount().equals("Y")) {

				colNames[counter] = "Acc. No.";
				counter = counter + 1;
			}

			if (salReg.getCheckPan().equals("Y")) {

				colNames[counter] = "Pan No.";
				counter = counter + 1;
			}

			if (salReg.getCheckGender().equals("Y")) {
				colNames[counter] = "Gender";
				counter = counter + 1;
			}

			if (salReg.getCheckHold().equals("Y")) {
				colNames[counter] = "On Hold";
				counter = counter + 1;
			}

			if (salReg.getCheckGrade().equals("Y")) {
				colNames[counter] = "Salary Grade";
				counter = counter + 1;
			}
			//added ganesh start
			
			if (salReg.getCheckEmpGrade().equals("Y")) {
				colNames[counter] = "Grade";
				counter = counter + 1;
			}
			//added ganesh end
			
			if (salReg.getPaybillCheck().equals("Y")) {
				colNames[counter] = "Paybill";
				counter = counter + 1;
			}
			if (salReg.getCheckCostCenter().equals("Y")) {
				colNames[counter] = "Cost Center";
				counter = counter + 1;
			}
			if (salReg.getCheckSubCostCenter().equals("Y")) {
				colNames[counter] = "Sub Cost Center";
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
			if (salReg.getCheckFlag().equals("N")) {
				data = new Object[emp_id.length + arrEmpLength][totalCol];
			} else {
				data = new Object[emp_id.length][totalCol];
			}
			
			for (int i = 0; i < emp_id.length; i++) {
				data[dataIndex][0] = emp_id[i][1];
				data[dataIndex][1] = emp_id[i][2];
				if (salReg.getCheckFlag().equals("Y")) {  // for consolidated arrears
					Object[][] arrears_days=null;
					arrears_days=(Object[][])arrearDaysMap.get(String.valueOf(emp_id[i][0]));
					if (arrears_days != null && arrears_days.length > 0) {
						data[dataIndex][2] = Double.parseDouble(String.valueOf(arrears_days[0][0]))
								+ Double.parseDouble(String.valueOf(emp_id[i][4]));
					}else {
						data[dataIndex][2] = emp_id[i][4];
					}
				} else {
					data[dataIndex][2] = emp_id[i][4];
				}

				int column = 3;
				double totArrACredit = 0;
				double totArrDebit = 0;

				/*
				 * If any check box is selected then the value for that column
				 * will appear in the report.
				 */
				if (salReg.getCheckBrn().equals("Y")) {
					data[dataIndex][column] = emp_id[i][5];// Branch
					column++;
				}

				if (salReg.getCheckDept().equals("Y")) {
					data[dataIndex][column] = emp_id[i][6];// Department
					column++;
				}

				if (salReg.getCheckDesg().equals("Y")) {
					data[dataIndex][column] = emp_id[i][11];// Designation
					column++;
				}

				if (salReg.getCheckDoj().equals("Y")) {
					data[dataIndex][column] = emp_id[i][12];// Date of Joining
					column++;
				}

				if (salReg.getCheckDob().equals("Y")) {
					data[dataIndex][column] = emp_id[i][7];// Date of Birth
					column++;
				}
				if (salReg.getCheckEmpType().equals("Y")) {
					data[dataIndex][column] = emp_id[i][3];// Employee Type
					column++;
				}

				if (salReg.getCheckBank().equals("Y")) {
					data[dataIndex][column] = emp_id[i][8];// Bank
					column++;
				}

				if (salReg.getCheckAccount().equals("Y")) {
					data[dataIndex][column] = emp_id[i][9];// Salary Account Number
					column++;
				}

				if (salReg.getCheckPan().equals("Y")) {
					data[dataIndex][column] = emp_id[i][10];// Pan number
					column++;
				}

				if (salReg.getCheckGender().equals("Y")) {
					if (String.valueOf(emp_id[i][13]).equals("")
							|| String.valueOf(emp_id[i][13]).equals("null")) {
						data[dataIndex][column] = "";
					} else {
						data[dataIndex][column] = emp_id[i][13];// Gender
					}
					column++;
				}

				if (salReg.getCheckHold().equals("Y")) {
					data[dataIndex][column] = emp_id[i][14];// On Hold
					column++;
				}

				if (salReg.getCheckGrade().equals("Y")) {
					data[dataIndex][column] = emp_id[i][15];// Salary Grade
					column++;
				}
				//added by ganesh
				if (salReg.getCheckEmpGrade().equals("Y")) {
					data[dataIndex][column] = emp_id[i][16];// Grade
					column++;
				}
				//added by ganesh end
				/** set credit values */
				
				if (salReg.getPaybillCheck().equals("Y")) {
					data[dataIndex][column] = emp_id[i][17];// Paybill
					column++;
				}
				
				if (salReg.getCheckCostCenter().equals("Y")) {
					data[dataIndex][column] = emp_id[i][18];// Cost Center
					column++;
				}
				if (salReg.getCheckSubCostCenter().equals("Y")) {
					data[dataIndex][column] = emp_id[i][19];// Sub Cost Center
					column++;
				}
				
				double total_credit = 0.00;
				/*Object salCredit[][] = getSalaryCreditData(String
						.valueOf(emp_id[i][0]), year, month, salReg, Id);*/
				Object salCredit[][] = null;
				salCredit = (Object[][])salCreditMap.get(String
						.valueOf(emp_id[i][0]));
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
					if ((salCredit != null && salCredit.length > 0))
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
							 * hrms_sal_cedits_year table
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
									if (salReg.getCheckFlag().equals("Y")) {
										Object[][] amt =null;
										 amt = (Object[][])arrearsAmtMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salCredit[k][0]));
										if (amt != null && amt.length > 0) {
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
										}else {
											data[dataIndex][column] = Utility
											.twoDecimals(String
													.valueOf(salCredit[k][1]));
									total_credit += Double
											.parseDouble(String
													.valueOf(salCredit[k][1]));
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

				data[dataIndex][column] = Utility.twoDecimals(formatter
						.format(total_credit));

				column = column + 1;
				/** set non-recovery debits */
				
				Object salDebit[][] = null;
				salDebit=(Object[][])salDebitMap.get(String.valueOf(emp_id[i][0]));
				double total_nonRecovery = 0;
				
				
				/*
				 * Following loop is used to set the debit head amount values.
				 */
				for (int k = 0; k < debit_header.length; k++) {
					data[dataIndex][column] = 0;
					if ((salDebit != null && salDebit.length > 0))

						for (int index = 0; index < salDebit.length; index++) {
							/*
							 * Following loop compares the debit code from
							 * hrms_debit_head with the debit code from
							 * hrms_sal_debits_Year
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
									if (salReg.getCheckFlag().equals("Y")) {
										Object[][] amt=null;
										amt=(Object[][])arrearsDebitMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salDebit[index][0]));
										if (amt != null && amt.length > 0) {
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
				data[dataIndex][column] = Utility.twoDecimals(formatter
						.format(total_nonRecovery));

				double total_pay = 0;
				total_pay = Double.parseDouble(String
						.valueOf(data[dataIndex][credit_header.length + 3
								+ colTotal]))
						- Double.parseDouble(String
								.valueOf(data[dataIndex][column]));
				column = column + 1;
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(total_pay));
				
				// UPDATED BY REEBA BEGINS
				column = column + 1;

				if (PFChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerPF));// Employer contribution to PF
					column++;
				}

				if (ESICChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerESIC));
					;// Employer contribution to ESIC
				}
				// UPDATED BY REEBA ENDS

				employerESIC = 0;
				employerPF =0;
				esicCredit=0;
				try {
					/**
					 * Following condition is used to select the arrears for the
					 * employees.
					 */
					if (salReg.getCheckFlag().equals("N")) {
						if (arrEmpLength != 0) {
							Object[][] arrearCode = null;
							arrearCode=(Object[][])arrearCodeMap.get(String.valueOf(emp_id[i][0]));
							if ((arrearCode != null && arrearCode.length > 0)) {

								for (int arrCode = 0; arrCode < arrearCode.length; arrCode++) {
									totArrACredit = 0;
									totArrDebit = 0;
									if (String.valueOf(arrearCode[arrCode][5])
											.equals("")
											|| String.valueOf(
													arrearCode[arrCode][5])
													.equals("null")) {  // for monthly arrears
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
									} else {   // for promotional arrears
										arrearDebitAmt = promArrearDebitData(
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
										arrearCreditAmt = promArrearCreditData(
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
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
													.valueOf(arrearCode[arrCode][2]);// Employee Name
									arrearCol = arrearCol + 1;
									if (String.valueOf(arrearCode[arrCode][3])
											.equals("null")
											|| String.valueOf(
													arrearCode[arrCode][3])
													.equals("")) {
										data[dataIndex + 1][arrearCol] = "";// Arrears Days
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
									if (salReg.getCheckBrn().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][5];// Branch
										arrearCol++;
									}

									if (salReg.getCheckDept().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][6];// Department
										arrearCol++;
									}

									if (salReg.getCheckDesg().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][11];// Designation
										arrearCol++;
									}

									if (salReg.getCheckDoj().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][12];// Date of Joining
										arrearCol++;
									}

									if (salReg.getCheckDob().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][7];// Date of Birth
										arrearCol++;
									}
									if (salReg.getCheckEmpType().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][3];// Employee Type
										arrearCol++;
									}

									if (salReg.getCheckBank().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][8];// Bank Name
										arrearCol++;
									}

									if (salReg.getCheckAccount().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][9];// Salary Account No.
										arrearCol++;
									}

									if (salReg.getCheckPan().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][10];// Pan Number
										arrearCol++;
									}

									if (salReg.getCheckGender().equals("Y")) {
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

									if (salReg.getCheckHold().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][14];// On
																						// Hold
										arrearCol++;
									}

									if (salReg.getCheckGrade().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][15];//Salary Grade
										arrearCol++;
									}
									//added by ganesh 
									
									if (salReg.getCheckEmpGrade().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][16];// Grade
										arrearCol++;
									}
									//added by ganesh end
									if (salReg.getPaybillCheck().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][17];// Paybill
										arrearCol++;
									}
									
									if (salReg.getCheckCostCenter().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][18];// Cost Center
										arrearCol++;
									}
									if (salReg.getCheckSubCostCenter().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][19];// Sub Cost Center
										arrearCol++;
									}
									/*
									 * Following loop is used to set the arrear
									 * credit amount from
									 * HRMS_ARREARS_CREDIT_Year
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
									 * debit amount from HRMS_ARREARS_DEBIT_year
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
										
										// employerr contribution to ESIC
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
										// employer contribution to PF
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


										data[dataIndex + 1][arrearCol] = Utility
												.twoDecimals(formatter
														.format(totArrearAmt));
									// UPDATED BY REEBA BEGINS
									arrearCol++;
									if (PFChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerPF));			// Employer contribution to PF
										arrearCol++;
									}

									if (ESICChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerESIC));	;// Employer contribution to ESIC
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

	/**
	 * Following function is called in the reportDatanoSelect() method.This
	 * method returns the emp id,emp name,credit head,debit head and values and also the arrear value if any.
	 * @param emp_id
	 * @param salReg
	 * @param arrEmpLength
	 * @param colTotal
	 * @param PFChk
	 * @param ESICChk
	 * @param dataVetor
	 * @return
	 */
	public Object[][] getReportDataNoBranchDept(Object[][] emp_id,
			SalaryRegister salReg,int arrEmpLength, int colTotal, String PFChk, String ESICChk,Vector dataVetor) {
		int dataIndex = 0;
		String year = salReg.getYear();
		String month = salReg.getMonth();
		Object[][] dataWithHeader = null;
		Object[][] arrearCreditAmt = null;
		Object[][] arrearDebitAmt = null;
		
		HashMap salCreditMap=(HashMap)dataVetor.get(0);
		HashMap salDebitMap=(HashMap)dataVetor.get(1);
		HashMap arrearDaysMap=(HashMap)dataVetor.get(2);
		HashMap arrearsAmtMap=(HashMap)dataVetor.get(3);
		HashMap arrearsDebitMap=(HashMap)dataVetor.get(4);
		HashMap arrearCodeMap=(HashMap)dataVetor.get(5);
		Object[][] esi_data = (Object[][])dataVetor.get(6);
		Object[][] pf_data = (Object[][])dataVetor.get(7);
		try {
			logger.info("##### DEPTWISE EMPLOYEES #######");
			//Object emp_id[][] = getEmpIdNew(query);
			Object credit_header[][] = getCreditHeader();
			Object debit_header[][] = getDebitHeader();
			double totArrearAmt = 0;
			// Object debit_recovery[][]= getDebitHeader_recovery();
			int totalCol = credit_header.length + debit_header.length+ colTotal + 6;
			int counter = 0;
			String[] colNames = null;
			// UPDATED BY REEBA BEGINS
			if (salReg.getChkConsSummary().equals("N")) {
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
			if (salReg.getCheckBrn().equals("Y")) {
				colNames[counter] = "Branch";
				counter = counter + 1;
			}
			if (salReg.getCheckDept().equals("Y")) {
				colNames[counter] = "Dept";
				counter = counter + 1;
			}
			if (salReg.getCheckDesg().equals("Y")) {
				colNames[counter] = "Desg";
				counter = counter + 1;
			}
			if (salReg.getCheckDoj().equals("Y")) {
				colNames[counter] = "Date Of\nJoining";
				counter = counter + 1;
			}
			if (salReg.getCheckDob().equals("Y")) {
				colNames[counter] = "Date of\nBirth";
				counter = counter + 1;
			}
			if (salReg.getCheckEmpType().equals("Y")) {
				colNames[counter] = "Emp Type";
				counter = counter + 1;
			}
			if (salReg.getCheckBank().equals("Y")) {
				colNames[counter] = "Bank";
				counter = counter + 1;
			}
			if (salReg.getCheckAccount().equals("Y")) {
				colNames[counter] = "Acc. No.";
				counter = counter + 1;
			}
			if (salReg.getCheckPan().equals("Y")) {
				colNames[counter] = "Pan No.";
				counter = counter + 1;
			}
			if (salReg.getCheckGender().equals("Y")) {
				colNames[counter] = "Gender";
				counter = counter + 1;
			}
			if (salReg.getCheckHold().equals("Y")) {
				colNames[counter] = "On Hold";
				counter = counter + 1;
			}
			if (salReg.getCheckGrade().equals("Y")) {
				colNames[counter] = "Salary Grade";
				counter = counter + 1;
			}
			if (salReg.getCheckEmpGrade().equals("Y")) {
				colNames[counter] = "Grade";
				counter = counter + 1;
			}
			if (salReg.getPaybillCheck().equals("Y")) {
				colNames[counter] = "Paybill";
				counter = counter + 1;
			}
			if (salReg.getCheckCostCenter().equals("Y")) {
				colNames[counter] = "Cost Center";
				counter = counter + 1;
			}
			if (salReg.getCheckSubCostCenter().equals("Y")) {
				colNames[counter] = "Sub Cost Center";
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
			Object[][] data = null;
			if (salReg.getCheckFlag().equals("N")) {
				data = new Object[emp_id.length + arrEmpLength][totalCol];
			} else {
				data = new Object[emp_id.length][totalCol];
			}
			
			for (int i = 0; i < emp_id.length; i++) {
				data[dataIndex][0] = emp_id[i][1];
				data[dataIndex][1] = emp_id[i][2];

				if (salReg.getCheckFlag().equals("Y")) {
					Object[][] arrears_days = null;
					arrears_days = (Object[][])arrearDaysMap.get(String.valueOf(emp_id[i][0]));
					
					if (arrears_days != null && arrears_days.length > 0) {
						data[dataIndex][2] = Double.parseDouble(String.valueOf(arrears_days[0][0]))
								+ Double.parseDouble(String.valueOf(emp_id[i][4]));
					}else{
						/* Maintaining the index value with emp_id[i][4] in case arrear days r not available*/
						data[dataIndex][2] = Double.parseDouble(String.valueOf(emp_id[i][4]));
					}
				} else {
					data[dataIndex][2] = emp_id[i][4];
				}
				int column = 3;
				if (salReg.getCheckBrn().equals("Y")) {
					data[dataIndex][column] = emp_id[i][5];
					column++;
				}
				if (salReg.getCheckDept().equals("Y")) {
					data[dataIndex][column] = emp_id[i][6];
					column++;
				}
				if (salReg.getCheckDesg().equals("Y")) {
					data[dataIndex][column] = emp_id[i][11];
					column++;
				}
				if (salReg.getCheckDoj().equals("Y")) {
					data[dataIndex][column] = emp_id[i][12];
					column++;
				}
				if (salReg.getCheckDob().equals("Y")) {
					data[dataIndex][column] = emp_id[i][7];
					column++;
				}
				if (salReg.getCheckEmpType().equals("Y")) {
					data[dataIndex][column] = emp_id[i][3];
					column++;
				}
				if (salReg.getCheckBank().equals("Y")) {
					data[dataIndex][column] = emp_id[i][8];
					column++;
				}
				if (salReg.getCheckAccount().equals("Y")) {
					data[dataIndex][column] = emp_id[i][9];
					column++;
				}
				if (salReg.getCheckPan().equals("Y")) {
					data[dataIndex][column] = emp_id[i][10];
					column++;
				}
				if (salReg.getCheckGender().equals("Y")) {
					if (String.valueOf(emp_id[i][13]).equals("")
							|| String.valueOf(emp_id[i][13]).equals("null")) {
						data[dataIndex][column] = "";
					} else {
						data[dataIndex][column] = emp_id[i][13];// Gender
					}
					column++;
				}
				if (salReg.getCheckHold().equals("Y")) {
					data[dataIndex][column] = emp_id[i][14];// On Hold
					column++;
				}
				if (salReg.getCheckGrade().equals("Y")) {
					data[dataIndex][column] = emp_id[i][15];//Salary Grade
					column++;
				}
				if (salReg.getCheckEmpGrade().equals("Y")) {
					data[dataIndex][column] = emp_id[i][16];// Grade
					column++;
				}
				if (salReg.getPaybillCheck().equals("Y")) {
					data[dataIndex][column] = emp_id[i][17];// Paybill
					column++;
				}
				if (salReg.getCheckCostCenter().equals("Y")) {
					data[dataIndex][column] = emp_id[i][18];// Paybill
					column++;
				}
				if (salReg.getCheckSubCostCenter().equals("Y")) {
					data[dataIndex][column] = emp_id[i][19];// Paybill
					column++;
				}
				double totArrACredit = 0;
				double totArrDebit = 0;
				int position_totalPay = 0;
				/** set credit values */

				double total_credit = 0.00;
				
				double employerESIC = 0.0;
				double esicCredit = 0.0;
				double employerPF = 0.0;
				
				//Object salCredit[][] = getSalaryCreditDataNoSelect(String.valueOf(emp_id[i][0]), year, month, salReg, brnCode, deptCode);
				
				/*Using salCredit[][] for value returned from a map*/
				Object salCredit[][] = null;
				try {
					salCredit = (Object[][]) salCreditMap.get(String.valueOf(emp_id[i][0]));
				} catch (Exception e) {
					logger.error(">>>>>>>>>>>>>>>>>>>>>Exception in salCreditMap");
				}
				/*
				 * Following query is used to set the credit head amount
				 */
				for (int j = 0; j < credit_header.length; j++) {
					esicCredit = 0.0;
					
					data[dataIndex][column] = 0.00;
					if ((salCredit != null && salCredit.length > 0)){
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
								logger.error("Exception in employerESIC :"+ e);
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
									if (salReg.getCheckFlag().equals("Y")) {
										Object[][] amt =null;
										amt =(Object[][]) arrearsAmtMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salCredit[k][0]));
										if (amt != null && amt.length > 0) {
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
										}else {
											data[dataIndex][column] = Utility
											.twoDecimals(String
													.valueOf(salCredit[k][1]));
									total_credit += Double
											.parseDouble(String
													.valueOf(salCredit[k][1]));
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
				}
				}// End of outer for loop

				data[dataIndex][column] = Utility.twoDecimals(formatter.format(total_credit));

				column = column + 1;
				/** set non-recovery debits */
				Object salDebit[][] = null;
				try {
					salDebit = (Object[][]) salDebitMap.get(String.valueOf(emp_id[i][0]));
				} catch (Exception e) {
					logger.error(">>>>>>>>>>>>>>>>>>>>>Exception in salDebitMap");
				} 
					
				double total_nonRecovery = 0;
				/*
				 * Following loop is used to set the debit head amount.
				 */
				for (int k = 0; k < debit_header.length; k++) {
					data[dataIndex][column] = 0;
					if ((salDebit != null && salDebit.length > 0))
						/*
						 * Following loop compares the debit code from
						 * hrms_debit_head with the debit code from
						 * hrms_sal_debits_Year
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
									if (salReg.getCheckFlag().equals("Y")) {
										Object[][] amt = null;
										amt = (Object[][])arrearsDebitMap.get(String.valueOf(emp_id[i][0])+"#"+String.valueOf(salDebit[index][0]));
										if (amt != null && amt.length > 0) {
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
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(total_pay));
				column = column + 1;

				if (PFChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerPF));// Employer contribution to
													// PF
					column++;
				}

				if (ESICChk.equals("Y")) {
					data[dataIndex][column] = Utility.twoDecimals(formatter
							.format(employerESIC)) ;// Employer contribution to ESIC
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
					if (salReg.getCheckFlag().equals("N")) {
						if (arrEmpLength != 0) {
							Object[][] arrearCode =null;
							arrearCode = (Object[][])arrearCodeMap.get(String.valueOf(emp_id[i][0]));
							if ((arrearCode != null && arrearCode.length > 0)) {

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
												year,String
												.valueOf(arrearCode[arrCode][6]));
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
									} else {
										arrearDebitAmt = promArrearDebitData(
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
										arrearCreditAmt = promArrearCreditData(
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
												year,String
												.valueOf(arrearCode[arrCode][6]));
									}
									String arrMonth = Utility
											.month(Integer
													.parseInt(String
															.valueOf(arrearCode[arrCode][1])));

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
									if (salReg.getCheckBrn().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][5];
										arrearCol++;
									}

									if (salReg.getCheckDept().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][6];
										arrearCol++;
									}

									if (salReg.getCheckDesg().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][11];
										arrearCol++;
									}

									if (salReg.getCheckDoj().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][12];
										arrearCol++;
									}

									if (salReg.getCheckDob().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][7];
										arrearCol++;
									}
									if (salReg.getCheckEmpType().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][3];
										arrearCol++;
									}

									if (salReg.getCheckBank().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][8];
										arrearCol++;
									}

									if (salReg.getCheckAccount().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][9];
										arrearCol++;
									}

									if (salReg.getCheckPan().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][10];
										arrearCol++;
									}
									if (salReg.getCheckGender().equals("Y")) {
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

									if (salReg.getCheckHold().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][14];
										arrearCol++;
									}

									if (salReg.getCheckGrade().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][15];
										arrearCol++;
									}
									//added by ganesh
									if (salReg.getCheckEmpGrade().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][16];
										arrearCol++;
									}
									//added by ganesh end 
									if (salReg.getPaybillCheck().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][17];
										arrearCol++;
									}
									
									if (salReg.getCheckCostCenter().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][18];
										arrearCol++;
									}
									if (salReg.getCheckSubCostCenter().equals("Y")) {
										data[dataIndex + 1][arrearCol] = emp_id[i][19];
										arrearCol++;
									}
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
										data[dataIndex + 1][arrearCol] = Utility
												.twoDecimals(formatter
														.format(totArrearAmt));
									// UPDATED BY REEBA BEGINS
									arrearCol++;
									if (PFChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerPF));// Employer contribution to PF
										arrearCol++;
									}

									if (ESICChk.equals("Y")) {
										data[dataIndex + 1][arrearCol] = Utility.twoDecimals(formatter
												.format(employerESIC));// Employer contribution to ESIC
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
/**
 * Method is used to get the leave encashment amount paid in particular month
 * @param emp_id
 * @param year
 * @param month
 * @return
 */
	
	public HashMap getLeaveEncashmentDataMap(String year,String month, String empIdString) {
		HashMap leaveEncashmentDataMap=null;
		String query = "SELECT SUM(NVL(ENCASHMENT_ENCASH_AMOUNT,0)),ENCASHMENT_CREDIT_CODE,EMP_ID FROM HRMS_ENCASHMENT_PROCESS_HDR"
			+ " LEFT JOIN HRMS_ENCASHMENT_PROCESS_DTL ON(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE=HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE )"
			+ " WHERE ENCASHMENT_PROCESS_FLAG= 'Y' AND ENCASHMENT_INCLUDE_SAL_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_MONTH="
			+ month
			+ " AND ENCASHMENT_INCLUDE_SAL_YEAR="
			+ year
			+ Utility.getConcatenatedIds("EMP_ID",empIdString)
			+ " GROUP BY ENCASHMENT_CREDIT_CODE,EMP_ID";
		
		leaveEncashmentDataMap = getSqlModel().getSingleResultMap(query, 2, 2);
		return leaveEncashmentDataMap;
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
/**
 * method to create report when no filter selected
 * @param loop_data
 * @param rg
 * @param salReg
 * @return
 */
	public org.paradyne.lib.report.ReportGenerator reportDataNoSelect(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg, SalaryRegister salReg) {

		ArrayList<String> totList = new ArrayList<String>();
		int recCount = 0;
		int arrEmpLength = 0;
		int check = 0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		/*This method returns the employee map as per the selected filter*/
		
		String mapKey = "SAL_EMP_CENTER||'#'||SAL_DEPT";
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
		
		// UPDATED BY REEBA ENDS
		String finalHeader[] = null;
		int colTotal = 0;
		
		String selectQueryInner = "SELECT DEPT_ID,DEPT_NAME from HRMS_DEPT";
		Object[][] loop_data_inner = getSqlModel().getSingleResult(selectQueryInner);
		
		if (loop_data.length > 0) {
			
			for (int a = 0; a < loop_data.length; a++) {//No of Centers
				for (int b = 0; b < loop_data_inner.length; b++) {//No of Dept in iterated Center

					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get(loop_data[a][0]+"#"+loop_data_inner[b][0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
						
						if (arrEmpChk != null && arrEmpChk.length > 0 ) {
							arrEmpLength = arrEmpChk.length;
						} else {
							arrEmpLength = 0;
						}
						
						Object[][] empData = null;
						try {
							empData = (Object[][]) employeeDataMap.get(String.valueOf(loop_data[a][0]) + "#"+ loop_data_inner[b][0]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						Object[][] reportDataPay = null;
						String empIdString="0";
						try {
							for (int empCount = 0; empCount < empData.length; empCount++) {
								empIdString += ","
										+ String.valueOf(empData[empCount][0]);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						// get various salary & arrears details for employees
						Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
						if(empData != null && empData.length > 0){
							reportDataPay = getReportDataNoBranchDept(empData, salReg	,arrEmpLength, check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
						}
						
						if (reportDataPay != null && reportDataPay.length > 0) {
						
							int headerLength = 0;
							int[] cellWidth = null;
							int[] alignment = null;
							if (salReg.getChkConsSummary().equals("N")) {
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
						if (salReg.getChkConsSummary().equals("N")) {
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
						if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
							colTotal = check + 1; 
						} else if (salReg.getCheckEmployerPF().equals("Y")) {
							colTotal = check + 2;
						} else if (salReg.getCheckEmployerESIC().equals("Y")) {
							colTotal = check + 2;
						}else {
							colTotal = check + 3;
						}

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
								if (String.valueOf(finalData[j][1]).contains("Recovery")) {
									total -= Double.parseDouble(String
											.valueOf(finalData[j][i]));
								} else {
									total += Double.parseDouble(String
											.valueOf(finalData[j][i]));
								}
							}// End of inner loop
							// totalHeader[i] = "";
							totList.add(Utility.twoDecimals(formatter.format(total)));
							if (salReg.getChkConsSummary().equals("N")) {
								totalByColumn[0][i] = Utility
										.twoDecimals(formatter.format(total));
							} else {
								totalByColumn[0][i - 1] = Utility
										.twoDecimals(formatter.format(total));
							}
						}// End of outer loop

						rg.addText("Branch : " + loop_data[a][1] + "   Department : " + loop_data_inner[b][1], 0, 0, 0);
						
						if (salReg.getChkConsSummary().equals("N")) {
							rg.tableBody(finalHeader, finalData, cellWidth, alignment);
							rg.tableBody(totalByColumn, cellWidth, alignment);
							
						} else {
							rg.tableBody(finalHeader, totalByColumn, cellWidth,	alignment);
							
						}
						recCount++;
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
				if (salReg.getChkConsSummary().equals("N")) {
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

					if (salReg.getChkConsSummary().equals("N")) {
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
				
				rg.tableBody(finalHeader,grand_total,cellWidth,alignment);
			} else {
				rg.addText("Records are not available.",0,1,0);
			}

		}//End of loop_data if condition
		return rg;
	}

	/** Method to create branch wise report 
	 * @author REEBA_JOSEPH
	 * To generate report Branchwise
	 * @param salReg
	 * @param response
	 */
	public org.paradyne.lib.report.ReportGenerator generateBranchwiseReport(SalaryRegister salReg, HttpServletResponse response, org.paradyne.lib.report.ReportGenerator rg) {
		logger.info("##################### Only Branch #####################");
		
		String reportTitle = "Salary Register of " + salReg.getDivName()+ " for the month of "
		+ Utility.month(Integer.parseInt(salReg.getMonth())) + " "+ salReg.getYear();
		
		if (salReg.getReport().equals("Pdf")) {
			rg.addTextBold(reportTitle, 0, 1, 0);
		} else {
			Object[][] title = new Object[1][3];
			title[0][0] = "";
			title[0][1] = "";
			title[0][2] = reportTitle;

			int[] cols = { 20, 20, 50 };
			int[] align = { 0, 0, 1 };
			rg.tableBodyNoCellBorder(title, cols, align, 1);
		}
		

		if (salReg.getBranchCode().equals("")) {
			//logger.info("Only Branch====Branch code nil");
			String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";
			Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
			reportOnlyForBranch(loop_data, rg, salReg);
		} else {
			//logger.info("Only Branch====Branch code present : "
			//		+ salReg.getBranchCode());
			Object[][] loop_data = new Object[1][2];
			loop_data[0][0] = salReg.getBranchCode();
			loop_data[0][1] = salReg.getBranchName();
			reportOnlyForBranch(loop_data, rg, salReg);
		}
		return rg;
	}

	/** To generate report Departmentwise
	 *
	 * @param salReg
	 * @param response
	 */
	public org.paradyne.lib.report.ReportGenerator generateDepartmentwiseReport(SalaryRegister salReg, HttpServletResponse response, org.paradyne.lib.report.ReportGenerator rg) {
		logger.info("##################### Only Department #####################");
			
		String reportTitle = "Salary Register of " + salReg.getDivName()+ " for the month of "
		+ Utility.month(Integer.parseInt(salReg.getMonth())) + " "+ salReg.getYear();
		
		if (salReg.getReport().equals("Pdf")) {
			rg.addTextBold(reportTitle, 0, 1, 0);
		} else {
			Object[][] title = new Object[1][3];
			title[0][0] = "";
			title[0][1] = "";
			title[0][2] = reportTitle;

			int[] cols = { 20, 20, 50 };
			int[] align = { 0, 0, 1 };
			rg.tableBodyNoCellBorder(title, cols, align, 1);
		}

		if (salReg.getDeptCode().equals("")) {
			//logger.info("Only Department====Department code nil");
			String selectQuery = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
			Object[][] loop_data = getSqlModel().getSingleResult(selectQuery);
			rg = reportOnlyForDepartment(loop_data, rg, salReg);
		} else {
			//logger.info("Only Department====Department code present : "
			//		+ salReg.getDeptCode());
			Object[][] loop_data = new Object[1][2];
			loop_data[0][0] = salReg.getDeptCode();
			loop_data[0][1] = salReg.getDeptName();
			rg = reportOnlyForDepartment(loop_data, rg, salReg);
		}
		return rg;
	}

	/**
	 * @author REEBA_JOSEPH
	 * @param loop_data
	 * @param rg
	 * @param salReg
	 */
	public org.paradyne.lib.report.ReportGenerator reportOnlyForDepartment(Object[][] loop_data, org.paradyne.lib.report.ReportGenerator rg, SalaryRegister salReg) {

		ArrayList<String> totList = new ArrayList<String>();
		int recCount = 0;
		int arrEmpLength = 0;
		int check = 0;
		/*This method returns the count of the checkbox selected, Updated by Prashant*/
		check = fetchCheckBoxCount(salReg);
		
		String mapKey = "SAL_DEPT";
		HashMap employeeDataMap = fetchEmployessByFilter(salReg, mapKey);
		HashMap employeeArrearsMap = fetchArrearsByFilter(salReg, mapKey);
		
		String finalHeader[] = null;
		int colTotal = 0;
		if (loop_data.length > 0) {
			//logger.info("Loop count ============= " + loop_data.length);
			for (int a = 0; a < loop_data.length; a++) {
				
				try {

					Object[][] arrEmpChk = null;
					try {
						arrEmpChk = (Object[][]) employeeArrearsMap.get(String.valueOf(loop_data[a][0]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(arrEmpChk != null && arrEmpChk.length > 0 ){
						arrEmpLength = arrEmpChk.length;
					}else{
						arrEmpLength = 0;
					}
				} catch (Exception e) {
					logger.error("Error in arrears query : " + e);
					e.printStackTrace();
				}
				Object[][] empData = null;
				try {
					empData = (Object[][]) employeeDataMap.get(String.valueOf(loop_data[a][0]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Object[][] reportDataPay = null;
				String empIdString="0";
				try {
					for (int empCount = 0; empCount < empData.length; empCount++) {
						empIdString += ","
								+ String.valueOf(empData[empCount][0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				// get various salary & arrears details for employees
				Vector dataVector=getDataVector(empIdString,salReg.getYear(),salReg.getMonth());
				if(empData != null && empData.length > 0){
					reportDataPay = getReportDataNoBranchDept(empData, salReg, 
							arrEmpLength, check, salReg.getCheckEmployerPF(), salReg.getCheckEmployerESIC(),dataVector);
				}
				
				if (reportDataPay != null && reportDataPay.length > 0) {
					int headerLength = 0;
					int[] cellWidth = null;
					int[] alignment = null;
					if (salReg.getChkConsSummary().equals("N")) {
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
						}// End of inner loop
					}// End of outer loop
					Object totalByColumn[][] = null;
					String totalHeader[] = new String[finalData.length];
					totalHeader[0] = "";
					// totalHeader[1] = "";
					if (salReg.getChkConsSummary().equals("N")) {
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
					if (salReg.getCheckEmployerPF().equals("Y") && salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 1; //check+3-2
					} else if (salReg.getCheckEmployerPF().equals("Y")) {
						colTotal = check + 2;//check+3-1
					} else if (salReg.getCheckEmployerESIC().equals("Y")) {
						colTotal = check + 2;//check+3-1
					}else
						colTotal = check + 3;

					// loop to calculate the creditwise total
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
						if (salReg.getChkConsSummary().equals("N")) {
							totalByColumn[0][i] = Utility.twoDecimals(formatter
									.format(total));
						} else {
							totalByColumn[0][i - 1] = Utility
									.twoDecimals(formatter.format(total));
						}
					}// End of outer loop
					rg.addText("Department : " + loop_data[a][1], 0, 0, 0);
					
					
					if (salReg.getChkConsSummary().equals("N")) {
						rg.tableBody(finalHeader, finalData, cellWidth,	alignment);
						rg.tableBody(totalByColumn, cellWidth, alignment);
					} else {
						rg.tableBody(finalHeader, totalByColumn, cellWidth, alignment);
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
				if (salReg.getChkConsSummary().equals("N")) {
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

					if (salReg.getChkConsSummary().equals("N")) {
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
				rg.tableBody(finalHeader,grand_total, cellWidth, alignment);
				
			} else {
				rg.addText("Records are not available.",0,1,0);
			}
		} //End of loop_data if condition
		return rg;
	}
	
	/** This method returns the count of the checkbox selected to generated the report accordingly. 
	 * @author Prashant
	 * @param salReg
	 * @return int
	 */
	public int fetchCheckBoxCount(SalaryRegister salReg){
		int check = 0;
		if (salReg.getCheckBrn().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckDob().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckBank().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckAccount().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckPan().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckEmpType().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckDept().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckDesg().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckDoj().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckGender().equals("Y")) {
			check = check + 1;
		}

		if (salReg.getCheckHold().equals("Y")) {
			check = check + 1;
		}

		if (salReg.getCheckGrade().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckEmpGrade().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckEmployerPF().equals("Y")) {
			check = check + 1;
		}

		if (salReg.getCheckEmployerESIC().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getPaybillCheck().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckCostCenter().equals("Y")) {
			check = check + 1;
		}
		if (salReg.getCheckSubCostCenter().equals("Y")) {
			check = check + 1;
		}
		logger.info("############ No Of Checks ###########"+check);
		return check;
	}
	
	/**
	 * This method is used to get the employee details according to filters & map key
	 * @param salReg
	 * @param mapKey
	 * @return
	 */
	public HashMap fetchEmployessByFilter(SalaryRegister salReg, String mapKey){
		
		String selectSalaryLoop = "SELECT HRMS_SALARY_"+ salReg.getYear()+ ".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||"
		+ " EMP_LNAME, NVL(TYPE_NAME,' '),NVL(SAL_DAYS,0) ";
			if (salReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += ",NVL(CENTER_NAME,' ') ";
			} else {
				selectSalaryLoop += ",' ' ";
			}
			if (salReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += ",NVL(DEPT_NAME,' ')";
			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += " , NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(BANK_NAME,' '), NVL(SAL_EMP_ACC_NO,' '), NVL(SAL_PANNO,' ')";
		
			if (salReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += ",NVL(RANK_NAME,' ')";
		
			} else {
				selectSalaryLoop += ",' '";
			}
			selectSalaryLoop += ",NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' WHEN EMP_GENDER='O' THEN 'Other' END "
					+ ", CASE WHEN SAL_ONHOLD='Y' THEN 'On Hold' WHEN SAL_ONHOLD='N' THEN ' ' END,NVL(SALGRADE_TYPE,' '),NVL(CADRE_NAME,' '), NVL(HRMS_PAYBILL.PAYBILL_GROUP,' '), NVL(HRMS_COST_CENTER.COST_CENTER_NAME,' '), NVL(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_NAME,' '), "+mapKey+" FROM HRMS_SALARY_"
					+ salReg.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC "
					+ " ON HRMS_SALARY_"
					+ salReg.getYear()
					+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
					+ salReg.getYear()
					+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON"
					+ " (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
					+ salReg.getYear()
					+ ".SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ salReg.getMonth()
					+ " AND LEDGER_YEAR="
					+ salReg.getYear()
					+ ") "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_"+ salReg.getYear()+".SAL_EMP_BANK_MISC_CODE)";
			if (salReg.getCheckBrn().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_SALARY_"+ salReg.getYear() + ".SAL_EMP_CENTER)";
			}
			if (salReg.getCheckDept().equals("Y")) {
				selectSalaryLoop += "  INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_SALARY_"
						+ salReg.getYear() + ".SAL_DEPT)";
			}
			if (salReg.getCheckDesg().equals("Y")) {
				selectSalaryLoop += " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_SALARY_"
						+ salReg.getYear() + ".SAL_EMP_RANK)";
			}
			selectSalaryLoop += " LEFT JOIN HRMS_SALGRADE_HDR ON HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_EMP_OFFC.EMP_SAL_GRADE ";
			
			selectSalaryLoop += " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_SALARY_"+ salReg.getYear() + ".SAL_EMP_GRADE)";
			
			selectSalaryLoop += " LEFT JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_EMP_OFFC.EMP_PAYBILL) ";
			
			selectSalaryLoop += " LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";
			selectSalaryLoop += " LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID AND HRMS_SUB_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";

			selectSalaryLoop += " WHERE SAL_DIVISION IN(" + salReg.getDivCode()+")" ;
			
			
			// checking filters
			if (!(salReg.getOnHold().equals("A"))) {
				selectSalaryLoop += " AND SAL_ONHOLD='" + salReg.getOnHold()+ "' ";
			}
			if (!salReg.getBranchCode().equals("")) {
				selectSalaryLoop += " AND SAL_EMP_CENTER IN(" + salReg.getBranchCode()+")";
			}
			if (!salReg.getDeptCode().equals("")) {
				selectSalaryLoop += " AND SAL_DEPT IN(" + salReg.getDeptCode()+")";
			}
			if (!salReg.getTypeCode().equals("")) {
				selectSalaryLoop += " AND SAL_EMP_TYPE IN(" + salReg.getTypeCode()+")";
			}
			if (!salReg.getDesgCode().equals("")) {
				selectSalaryLoop += " AND SAL_EMP_RANK IN(" + salReg.getDesgCode()+")";
			}
			if (!salReg.getPaybillId().equals("")) {
				selectSalaryLoop += " AND SAL_EMP_PAYBILL IN(" +salReg.getPaybillId()+")";
			}
			if (!salReg.getEmpGradeId().equals("")) {
				selectSalaryLoop += " AND SAL_EMP_GRADE IN("+ salReg.getEmpGradeId()+")";
			}
			if (!salReg.getCostcenterid().equals("")) {
				selectSalaryLoop += " AND COST_CENTER_ID IN(" +salReg.getCostcenterid()+")";
			}
			if (!salReg.getSubcostcenterid().equals("")) {
				selectSalaryLoop += " AND SUB_COST_CENTER_ID IN(" +salReg.getSubcostcenterid()+")";
			}
			if(!mapKey.equals("0"))
			selectSalaryLoop += " ORDER BY "+mapKey;
			
			HashMap branchDeptEmployeeMap = getSqlModel().getSingleResultMap(selectSalaryLoop, 20, 2 );
		
			return branchDeptEmployeeMap;
	}
	
	/**
	 * This method is used to get the arrears employee details according to the filter passed
	 * @param salReg
	 * @param filter
	 * @return
	 */
	public HashMap fetchArrearsByFilter(SalaryRegister salReg, String filter){
			
			String arrearEmp = " SELECT HRMS_ARREARS_"
				+ salReg.getYear()
				+ ".EMP_ID ,HRMS_ARREARS_LEDGER.ARREARS_CODE, "+filter+"  FROM HRMS_SALARY_"
				+ salReg.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"
				+ salReg.getYear()
				+ ".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_SALARY_"
				+ salReg.getYear()
				+ ".SAL_EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"
				+ salReg.getYear()
				+ ".SAL_LEDGER_CODE AND LEDGER_MONTH="
				+ salReg.getMonth()
				+ " AND LEDGER_YEAR="
				+ salReg.getYear()
				+ ")"
				+ " INNER JOIN HRMS_ARREARS_"
				+ salReg.getYear()
				+ " ON(HRMS_ARREARS_"
				+ salReg.getYear()
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
				+ salReg.getYear()
				+ ".ARREARS_CODE)"
				+ " WHERE ARREARS_PAID_MONTH="
				+ salReg.getMonth()
				+ " AND ARREARS_PAID_YEAR="
				+ salReg.getYear()
				+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
				+ " AND SAL_DIVISION IN (" + salReg.getDivCode()+")";
				
			if (!salReg.getBranchCode().equals("")) {
				arrearEmp += " AND SAL_EMP_CENTER IN(" + salReg.getBranchCode()+")";
			}
			if (!salReg.getDeptCode().equals("")) {
				arrearEmp += " AND SAL_DEPT IN(" + salReg.getDeptCode()+")";
			}
			if (!(salReg.getOnHold().equals("A"))) {
				arrearEmp += "AND SAL_ONHOLD='" + salReg.getOnHold()
						+ "' ";
			}
			if (!salReg.getTypeCode().equals("")) {
				arrearEmp += " AND SAL_EMP_TYPE IN("
						+ salReg.getTypeCode()+")";
			}
			if (!salReg.getDesgCode().equals("")) {
				arrearEmp += " AND SAL_EMP_RANK IN("
						+ salReg.getDesgCode()+")";
			}
			HashMap arrearsMap = getSqlModel().getSingleResultMap(arrearEmp, 2, 2 );
			return arrearsMap;
		}
	/**
	 * Purpose : this method is used to round of amount
	 * @param ch
	 * @param amount
	 * @return
	 */
	public double roundCheck(int ch,double amount){
		  try {
			 
				switch(ch){
				  case 0: 	return (Double.parseDouble(formatter.format(amount)));
					  		
				  case 1: 	return Math.round(Double.parseDouble(formatter.format(amount)));
					  		
				  case 2: 	return Math.floor(Double.parseDouble(formatter.format(amount)));
					  		
				  case 3: 	return Math.ceil(Double.parseDouble(formatter.format(amount)));
					  		
				  case 4: 	if(!(Math.round((amount))%10 == 0))
					  			return Double.parseDouble(formatter.format((Math.round(amount) + (10 -(Math.round(amount)%10)))));
				  			else
				  				return Math.round(amount);
				  				
				  default : return amount;
				  }
		} catch (Exception e) {
			logger.error("Exception in getting roundCheck  ---------"+e);
			return Double.parseDouble(formatter.format(amount));
		}
	  }
}
