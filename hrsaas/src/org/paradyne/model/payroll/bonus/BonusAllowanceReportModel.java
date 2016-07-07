package org.paradyne.model.payroll.bonus;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.bonus.BonusAllowanceReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * Created on 30th Jan 2012.
 * 
 * @author AA1380
 * 
 */
public class BonusAllowanceReportModel extends ModelBase {
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");

	/** trueStr. */
	private String trueStr = "true";
	/** nullStr. */
	private String nullStr = "null";

	/**
	 * Method : getReport. Purpose : This method is used to generate report
	 * 
	 * @param bonusReportBean :
	 *            bonusReportBean
	 * @param response :
	 *            response
	 * @param request :
	 *            request
	 * @param reportPath :
	 *            reportPath
	 */
	public void getReport(final BonusAllowanceReport bonusReportBean,
			final HttpServletResponse response,
			final HttpServletRequest request, final String reportPath) {
		try {
			final ReportDataSet rds = new ReportDataSet();
			final String type = bonusReportBean.getReportType();
			rds.setReportType(type);
			final String fileName = "Bonus Report " + Utility.getRandomNumber(1000);
			// final String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Bonus Report");
			rds.setPageSize("A4");
			rds.setUserEmpId(bonusReportBean.getUserEmpId());
			rds.setShowPageNo(true);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if ("".equals(reportPath)) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "BonusReport_input.action");
			}
			rg = this.getReport(rg, bonusReportBean);
			rg.process();
			if ("".equals(reportPath)) {
				rg.createReport(response);
			} else {
				rg.saveReport(response);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : getReport. Purpose : This method is used to display report
	 * 
	 * @param rg :
	 *            rg
	 * @param bonusReportBean :
	 *            bonusReportBean
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(
			final org.paradyne.lib.ireportV2.ReportGenerator rg,
			final BonusAllowanceReport bonusReportBean) {
		try {

			final String filterRecordQuery = "SELECT DECODE(HRMS_BONUS_HDR.PAY_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December')||'-'||HRMS_BONUS_HDR.PAY_YEAR, HRMS_BONUS_HDR.FLAT_RATE, " + 
					" HRMS_DIVISION.DIV_NAME, HRMS_BONUS_HDR.BRANCH_CODE, HRMS_BONUS_HDR.DEPT_CODE, HRMS_BONUS_HDR.BONUS_FORMULA, " + 
					" NVL(HRMS_BONUS_HDR.SYSTEM_CAL_FLAG,'N'), NVL(HRMS_BONUS_HDR.MANUALLY_CAL_FLAG,'N'), NVL(HRMS_BONUS_HDR.SLAB_WISE_FLAG,'N'), NVL(HRMS_BONUS_HDR.FLAT_RATE_FLAG,'N'), NVL(HRMS_BONUS_HDR.FLAT_RATE,'')" + 
					" FROM HRMS_BONUS_HDR " + 
					" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_BONUS_HDR.DIV_CODE) " + 
					" WHERE HRMS_BONUS_HDR.BONUS_CODE = "	+ bonusReportBean.getBonusAllowanceID();
			final Object[][] filterRecordObj = this.getSqlModel().getSingleResult(filterRecordQuery);

			/* Setting filter details */
			// final String fromYear =
			// Utility.month(Integer.parseInt(bonusReportBean.getFromMonth())) +
			// " " + bonusReportBean.getFromYear();
			// final String toYear =
			// Utility.month(Integer.parseInt(bonusReportBean.getToMonth())) + "
			// " + bonusReportBean.getToYear();
			// String filters = "Period : " + fromYear + " - " + toYear;
			String filters = "Period : " + bonusReportBean.getBonusAllowancePeriod();

			if (filterRecordObj != null && filterRecordObj.length > 0) {
				if (bonusReportBean.getDivisionReportCheckBox().trim().equals(
						this.trueStr)) {
					filters += "\n\nDivision : " + Utility.checkNull(String.valueOf(filterRecordObj[0][2]));
				}

				if (bonusReportBean.getBranchReportCheckBox().trim().equals(
						this.trueStr)) {
					filters += "\n\nBranch : " + this.getActualBranchName(Utility.checkNull(String.valueOf(filterRecordObj[0][3])));
				}

				if (bonusReportBean.getDepartmentReportCheckBox().equals(
						this.trueStr)) {
					filters += "\n\nDepartment : " + this.getActualDepartmentName(Utility.checkNull(String.valueOf(filterRecordObj[0][4])));
				}

				if (bonusReportBean.getApplicableCreditReportCheckBox().trim().equals(this.trueStr)) {
					filters += "\n\nApplicable Credit : " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));
				}

				filters += "\n\nPaid In salary Month : " + String.valueOf(filterRecordObj[0][0]);

				final TableDataSet filterData = new TableDataSet();
				filterData.setData(new Object[][] {{filters}});
				filterData.setCellAlignment(new int[] {0});
				filterData.setBodyFontStyle(1);
				filterData.setCellWidth(new int[] {100});
				filterData.setCellColSpan(new int[] {16});
				filterData.setCellNoWrap(new boolean[] {false});
				filterData.setBorder(false);
				rg.addTableToDoc(filterData);

				if ("Y".equals(Utility.checkNull(String.valueOf(filterRecordObj[0][6])))) {
					if ("Y".equals(Utility.checkNull(String.valueOf(filterRecordObj[0][8])))) {
						final Object[][] slabTableHeading = new Object[1][1];
						slabTableHeading[0][0] = "Slab Details : ";
						final int[] cellWidthSlabHeader = {100};
						final int[] cellAlignSlabHeader = {0};

						final TableDataSet tableSlabData = new TableDataSet();
						tableSlabData.setData(slabTableHeading);
						tableSlabData.setCellWidth(cellWidthSlabHeader);
						tableSlabData.setCellAlignment(cellAlignSlabHeader);
						tableSlabData.setBodyFontStyle(1);
						tableSlabData.setCellColSpan(new int[] {16});
						tableSlabData.setBorderDetail(0);
						tableSlabData.setBlankRowsAbove(1);
						rg.addTableToDoc(tableSlabData);

						String[] slabTableHeader = null;
						slabTableHeader = new String[5];
						slabTableHeader[0] = "From Amount " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));
						slabTableHeader[1] = "To Amount " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));
						slabTableHeader[2] = "Percentage " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));
						slabTableHeader[3] = "Minimum Amount " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));
						slabTableHeader[4] = "Maximum Amount " + Utility.checkNull(String.valueOf(filterRecordObj[0][5]));

						final int[] slabCellAlign = {2, 2, 2, 2, 2};
						final int[] slabCellWidth = {20, 20, 20, 20, 20};

						final Object[][] slabDataObject = this.getSqlModel().getSingleResult(
										"SELECT NVL(HRMS_BONUS_SLAB.BONUS_FROM_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_TO_AMT,0.0), NVL(HRMS_BONUS_SLAB.BOMUS_PERSENTAGE,0.0), " + 
												" NVL(HRMS_BONUS_SLAB.BONUS_MIN_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_MAX_AMT,0.0) FROM HRMS_BONUS_SLAB" + 
												" WHERE HRMS_BONUS_SLAB.BONUS_CODE = " + bonusReportBean.getBonusAllowanceID());

						final TableDataSet slabTable = new TableDataSet();
						slabTable.setHeader(slabTableHeader);
						slabTable.setHeaderBorderDetail(3);
						slabTable.setCellWidth(slabCellWidth);
						slabTable.setCellAlignment(slabCellAlign);
						slabTable.setData(slabDataObject);
						slabTable.setBodyFontStyle(1);
						slabTable.setBorderDetail(3);
						slabTable.setHeaderTable(true);
						rg.addTableToDoc(slabTable);
						rg.seperatorLine();
					}

					if ("Y".equals(Utility.checkNull(String.valueOf(filterRecordObj[0][9])))) {
						final String flatRate = "Defined Flat Rate : " + Utility.checkNull(String.valueOf(filterRecordObj[0][10]));
						final TableDataSet flatRateData = new TableDataSet();
						flatRateData.setData(new Object[][] {{flatRate}});
						flatRateData.setCellAlignment(new int[] {0});
						flatRateData.setBodyFontStyle(1);
						flatRateData.setCellWidth(new int[] {100});
						flatRateData.setCellColSpan(new int[] {16});
						flatRateData.setCellNoWrap(new boolean[] {false});
						flatRateData.setBorder(false);
						flatRateData.setBlankRowsAbove(1);
						rg.addTableToDoc(flatRateData);
					}
				}

				final Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "Bonus Details : ";
				final int[] cellWidthDateHeader = {100};
				final int[] cellAlignDateHeader = {0};

				final TableDataSet tableHeadingDateData = new TableDataSet();
				tableHeadingDateData.setData(summaryData);
				tableHeadingDateData.setCellWidth(cellWidthDateHeader);
				tableHeadingDateData.setCellAlignment(cellAlignDateHeader);
				tableHeadingDateData.setBodyFontStyle(1);
				tableHeadingDateData.setCellColSpan(new int[] {16});
				tableHeadingDateData.setBorderDetail(0);
				tableHeadingDateData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableHeadingDateData);

				int headerObjCount = 5;

				if (bonusReportBean.getBranchReportCheckBox().equals(
						this.trueStr)) {
					headerObjCount++;
				}
				if (bonusReportBean.getDepartmentReportCheckBox().equals(
						this.trueStr)) {
					headerObjCount++;
				}
				if (bonusReportBean.getGradeReportCheckBox().equals(
						this.trueStr)) {
					headerObjCount++;
				}
				if (bonusReportBean.getAccountNumberReportCheckBox().equals(
						this.trueStr)) {
					headerObjCount++;
				}
				if (bonusReportBean.getBankReportCheckBox().equals(this.trueStr)) {
					headerObjCount++;
				}
				if (bonusReportBean.getIndividualRatingReportCheckBox().equals(
						this.trueStr)) {
					headerObjCount++;
				}

				final String[] header = new String[headerObjCount];
				int headerCount = 2;
				header[0] = "Employee Id";
				header[1] = "Employee Name";

				if (bonusReportBean.getBranchReportCheckBox().equals(
						this.trueStr)) {
					header[headerCount] = "Branch";
					headerCount++;
				}
				if (bonusReportBean.getDepartmentReportCheckBox().equals(
						this.trueStr)) {
					header[headerCount] = "Department";
					headerCount++;
				}

				if (bonusReportBean.getGradeReportCheckBox().equals(
						this.trueStr)) {
					header[headerCount] = "Grade";
					headerCount++;
				}
				if (bonusReportBean.getAccountNumberReportCheckBox().equals(
						this.trueStr)) {
					header[headerCount] = "Account Number";
					headerCount++;
				}
				if (bonusReportBean.getBankReportCheckBox().equals(this.trueStr)) {
					header[headerCount] = "Bank";
					headerCount++;
				}
				if (bonusReportBean.getIndividualRatingReportCheckBox().equals(
						this.trueStr)) {
					header[headerCount] = "Individual Ratings";
					headerCount++;
				}

				header[headerCount] = "Bonus Amount";
				headerCount++;

				header[headerCount] = "TDS";
				headerCount++;

				header[headerCount] = "Net (Rs.)";
				headerCount++;

				final int[] bcellAlign = new int[headerCount];
				final int[] bcellWidth = new int[headerCount];
				int allignWidthCount = 2;
				bcellAlign[0] = 0;
				bcellWidth[0] = 15;
				bcellAlign[1] = 0;
				bcellWidth[1] = 20;

				if (bonusReportBean.getBranchReportCheckBox().equals(
						this.trueStr)) {
					bcellAlign[allignWidthCount] = 0;
					bcellWidth[allignWidthCount] = 15;
					allignWidthCount++;
				}

				if (bonusReportBean.getDepartmentReportCheckBox().equals(
						this.trueStr)) {
					bcellAlign[allignWidthCount] = 0;
					bcellWidth[allignWidthCount] = 15;
					allignWidthCount++;
				}

				if (bonusReportBean.getGradeReportCheckBox().equals(
						this.trueStr)) {
					bcellAlign[allignWidthCount] = 0;
					bcellWidth[allignWidthCount] = 15;
					allignWidthCount++;
				}

				if (bonusReportBean.getAccountNumberReportCheckBox().equals(
						this.trueStr)) {
					bcellAlign[allignWidthCount] = 0;
					bcellWidth[allignWidthCount] = 15;
					allignWidthCount++;
				}

				if (bonusReportBean.getBankReportCheckBox().equals(this.trueStr)) {
					bcellAlign[allignWidthCount] = 0;
					bcellWidth[allignWidthCount] = 15;
					allignWidthCount++;
				}

				if (bonusReportBean.getIndividualRatingReportCheckBox().equals(
						this.trueStr)) {
					bcellAlign[allignWidthCount] = 2;
					bcellWidth[allignWidthCount] = 10;
					allignWidthCount++;
				}

				bcellAlign[allignWidthCount] = 2;
				bcellWidth[allignWidthCount] = 10;
				allignWidthCount++;

				bcellAlign[allignWidthCount] = 2;
				bcellWidth[allignWidthCount] = 10;
				allignWidthCount++;

				bcellAlign[allignWidthCount] = 2;
				bcellWidth[allignWidthCount] = 10;

				final Object[][] dataObject = this.getSqlModel().getSingleResult(
								"SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " +
										" HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, " +
										" NVL(HRMS_CADRE.CADRE_NAME,'') , HRMS_SALARY_MISC.SAL_ACCNO_REGULAR, " +
										" HRMS_BANK.BANK_NAME, HRMS_BONUS_EMP.BONUS_RATING, " +
										" NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0.0), NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0.0) , NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0.0)" +
										" FROM HRMS_BONUS_EMP " +
										" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
										" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " +
										" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
										" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
										" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " +
										" LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) " +
										" WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusReportBean.getBonusAllowanceID() +
										" ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ASC  ");

				final Object[][] finalDataObj = new Object[dataObject.length][headerCount];

				for (int i = 0; i < finalDataObj.length; i++) {
					finalDataObj[i][0] = Utility.checkNull(String.valueOf(dataObject[i][0]));
					finalDataObj[i][1] = Utility.checkNull(String.valueOf(dataObject[i][1]));
					int headerDataObjCount = 2;
					if (bonusReportBean.getBranchReportCheckBox().equals(
							this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][2]));
						headerDataObjCount++;
					}
					if (bonusReportBean.getDepartmentReportCheckBox().equals(
							this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][3]));
						headerDataObjCount++;
					}
					if (bonusReportBean.getGradeReportCheckBox().equals(
							this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][4]));
						headerDataObjCount++;
					}
					if (bonusReportBean.getAccountNumberReportCheckBox().equals(this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][5]));
						headerDataObjCount++;
					}
					if (bonusReportBean.getBankReportCheckBox().equals(this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][6]));
						headerDataObjCount++;
					}
					if (bonusReportBean.getIndividualRatingReportCheckBox().equals(this.trueStr)) {
						finalDataObj[i][headerDataObjCount] = Utility.checkNull(String.valueOf(dataObject[i][7]));
						headerDataObjCount++;
					}

					finalDataObj[i][headerDataObjCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(dataObject[i][8]))));
					headerDataObjCount++;

					finalDataObj[i][headerDataObjCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(dataObject[i][9]))));
					headerDataObjCount++;

					finalDataObj[i][headerDataObjCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(dataObject[i][10]))));
				}

				final TableDataSet tableDataHeader = new TableDataSet();
				tableDataHeader.setHeader(header);
				tableDataHeader.setHeaderBorderDetail(3);
				tableDataHeader.setCellWidth(bcellWidth);
				tableDataHeader.setCellAlignment(bcellAlign);
				tableDataHeader.setData(finalDataObj);
				tableDataHeader.setBodyFontStyle(1);
				tableDataHeader.setBorderDetail(3);
				tableDataHeader.setHeaderTable(true);
				rg.addTableToDoc(tableDataHeader);
				rg.seperatorLine();

				final Object[][] totalDataObject = this.getSqlModel().getSingleResult(
								" SELECT NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_AMT),0.0), " +
										" NVL(sum(HRMS_BONUS_EMP.BONUS_TAX_AMT),0.0), " +
										" NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),0.0)  " +
										" FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = "	+ bonusReportBean.getBonusAllowanceID());

				final Object[][] finalTotalObj = new Object[totalDataObject.length][headerCount];
				if (finalTotalObj != null && finalTotalObj.length > 0) {
					final int[] toalCellAlign = new int[headerCount];
					final int[] toalCellWidth = new int[headerCount];

					int totalCount = 2;
					finalTotalObj[0][0] = "Total Amount";
					toalCellAlign[0] = 0;
					toalCellWidth[0] = 15;
					finalTotalObj[0][1] = " ";
					toalCellAlign[1] = 0;
					toalCellWidth[1] = 20;

					if (bonusReportBean.getBranchReportCheckBox().equals(
							this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 15;
						totalCount++;
					}

					if (bonusReportBean.getDepartmentReportCheckBox().equals(
							this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 15;
						totalCount++;
					}

					if (bonusReportBean.getGradeReportCheckBox().equals(
							this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 15;
						totalCount++;
					}

					if (bonusReportBean.getAccountNumberReportCheckBox().equals(this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 15;
						totalCount++;
					}

					if (bonusReportBean.getBankReportCheckBox().equals(
							this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 15;
						totalCount++;
					}

					if (bonusReportBean.getIndividualRatingReportCheckBox().equals(this.trueStr)) {
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 2;
						toalCellWidth[totalCount] = 10;
						totalCount++;
					}

					finalTotalObj[0][totalCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(totalDataObject[0][0]))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 10;
					totalCount++;

					finalTotalObj[0][totalCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(totalDataObject[0][1]))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 10;
					totalCount++;

					finalTotalObj[0][totalCount] = this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(totalDataObject[0][2]))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 10;

					final TableDataSet totalDataSet = new TableDataSet();
					totalDataSet.setData(finalTotalObj);
					totalDataSet.setCellWidth(toalCellWidth);
					totalDataSet.setCellAlignment(toalCellAlign);
					totalDataSet.setBodyFontStyle(1);
					rg.addTableToDoc(totalDataSet);
				}

				final Object[][] totalNumberOfEmp = new Object[1][1];
				totalNumberOfEmp[0][0] = "\n\nTotal No of Employees : " + String.valueOf(dataObject.length);

				final TableDataSet totalCountDataSet = new TableDataSet();
				totalCountDataSet.setData(totalNumberOfEmp);
				totalCountDataSet.setCellWidth(new int[] {100});
				totalCountDataSet.setCellAlignment(new int[] {0});
				totalCountDataSet.setBodyFontStyle(1);
				totalCountDataSet.setBorderDetail(0);
				rg.addTableToDoc(totalCountDataSet);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * Method : getActualDivisionName. Purpose : This method is used to get
	 * Division names based on supplied pay bill ID
	 * 
	 * @param divisionID :
	 *            divisionID
	 * @return String
	 */
	private String getActualDivisionName(final String divisionID) {
		String divisionName = "";
		try {
			if (divisionID == null || this.nullStr.equals(divisionID)) {
				divisionName = "";
			} else {
				final String divisionNameQuery = "SELECT HRMS_DIVISION.DIV_NAME FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID IN (" + divisionID + ")";
				final Object[][] divisionNameObj = this.getSqlModel().getSingleResult(divisionNameQuery);
				if (divisionNameObj != null && divisionNameObj.length > 0) {
					for (int i = 0; i < divisionNameObj.length; i++) {
						divisionName += String.valueOf(divisionNameObj[i][0]) + ",";
					}
				}
				divisionName = divisionName.substring(0,
						divisionName.length() - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return divisionName;
	}

	/**
	 * Method : getActualBranchName. Purpose : This method is used to get Branch
	 * names based on supplied branch ID
	 * 
	 * @param branchID :
	 *            branchID
	 * @return String
	 */
	private String getActualBranchName(final String branchID) {
		String branchName = "";
		try {
			if (branchID == null || this.nullStr.equals(branchID) || "".equals(branchID)) {
				branchName = "";
			} else {
				final String branchNameQuery = "SELECT HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_ID IN (" + branchID + ")";
				final Object[][] branchNameObj = this.getSqlModel().getSingleResult(branchNameQuery);
				if (branchNameObj != null && branchNameObj.length > 0) {
					for (int i = 0; i < branchNameObj.length; i++) {
						branchName += String.valueOf(branchNameObj[i][0]) + ",";
					}
				}
				branchName = branchName.substring(0, branchName.length() - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return branchName;
	}

	/**
	 * Method : getActualDepartmentName. 
	 * Purpose : This method is used to get department names based on supplied department ID
	 * @param deptID : department ID
	 * @return String
	 */
	private String getActualDepartmentName(final String deptID) {
		String departmentName = "";
		try {
			if (deptID == null || this.nullStr.equals(deptID)) {
				departmentName = "";
			} else {
				final String departmentNameQuery = "SELECT HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT WHERE HRMS_DEPT.DEPT_ID IN (" + deptID + ")";
				final Object[][] departmentNameObj = this.getSqlModel().getSingleResult(departmentNameQuery);
				if (departmentNameObj != null && departmentNameObj.length > 0) {
					for (int i = 0; i < departmentNameObj.length; i++) {
						departmentName += String.valueOf(departmentNameObj[i][0]) + ",";
					}
				}
				departmentName = departmentName.substring(0, departmentName.length() - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return departmentName;
	}

	/**Method : checkForNullValue.
	 * Purpose : This method is used to check for null decimal values.
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with 0.
	 * 
	 * @param result :- Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkForNullValue(final String result) {
		if (result == null || this.nullStr.equals(result)) {
			return "0";
		} else {
			return result;
		}
	}
}
