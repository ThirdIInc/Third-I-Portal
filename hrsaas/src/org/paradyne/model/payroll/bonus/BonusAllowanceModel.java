package org.paradyne.model.payroll.bonus;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.bonus.BonusAllowance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.apache.log4j.Logger;

/**
 * Created on 17th Jan 2012.
 * 
 * @author aa1380
 * 
 */
public class BonusAllowanceModel extends ModelBase {
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");
	/** logger. */
	private Logger logger = Logger.getLogger(BonusAllowanceModel.class);
	/** zeroStr. */
	private String zeroStr = "0";
	/** oneStr. */
	private String oneStr = "1";
	/** manualStr. */
	private String manualStr = "Manual";
	/** systemStr. */
	private String systemStr = "System";
	/** empTokenStr. */
	private String empTokenStr = "Employee Token";
	/** empNameStr. */
	private String empNameStr = "Employee Name";
	/** taxAmountStr. */
	private String taxAmountStr = "Tax Amount";
	/** bonusAmountStr. */
	private String bonusAmountStr = "Bonus Amount";
	/** bonusTotalAmountStr. */
	private String bonusTotalAmountStr = "Bonus Total Amount";
	/** totalAmountStr. */
	private String totalAmountStr = "Total Amount";
	/** nullStr. */
	private String nullStr = "null";
	/** messageStr. */
	private String messageStr = "Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.";
	
	/** yesStr. */
	private String yesStr = "Y";
	/** falseStr. */
	private String falseStr = "false";
	/** trueStr. */
	private String trueStr = "true";
	/** starStr. */
	private String starStr = "*";
	/** selectRatingsStr. */
	private String selectRatingsStr = "selectRatings";
	/** selectBonusStr. */
	private String selectBonusStr = "selectBonus";
	/** processedRatingsTemplateStr. */
	private String processedRatingsTemplateStr = "processedRatingsTemplate";
	/** processedBonusTemplateStr. */
	private String processedBonusTemplateStr = "processedBonusTemplate";
	/** nStr. */
	private String nStr = "N";
	
	/**
	 * Method : getInitialRecords. 
	 * Purpose : This method is used to display saved records
	 * 
	 * @param request : request
	 * @param bonusBean : bonusBean
	 */
	public void getInitialRecords(final HttpServletRequest request,
			final BonusAllowance bonusBean) {
		try {
			final Object[][] initialRecordsObj = this.getSqlModel().getSingleResult(this.getQuery(1));
			final List<BonusAllowance> initialList = new ArrayList<BonusAllowance>();
			final String[] pageIndexDrafted = Utility.doPaging(bonusBean.getMyPage(), initialRecordsObj.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = this.zeroStr;
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = this.oneStr;
				pageIndexDrafted[3] = this.oneStr;
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals(this.oneStr)) {
				bonusBean.setMyPage(this.oneStr);
			}

			if (initialRecordsObj != null && initialRecordsObj.length > 0) {
				bonusBean.setInitialRecordListFlag(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final BonusAllowance beanItt = new BonusAllowance();
					beanItt.setBonusAllowanceIDItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][0])));
					beanItt.setBonusAllowancePeriodItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][1])));
					beanItt.setProcessDateItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][2])));
					beanItt.setDivisionIDItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][3])));
					beanItt.setDivisionNameItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][4])));
					beanItt.setPayBillIDItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][5])));
					beanItt.setPayBillNameItr(this.getActualPayBillName(String.valueOf(initialRecordsObj[i][5])));
					beanItt.setTotalBonusAllowanceItr(this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(initialRecordsObj[i][6])))));
					beanItt.setHeadCountItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][7])));
					beanItt.setPaidInSalaryMonthItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][8])));
					beanItt.setLockUnlockItr(Utility.checkNull(String.valueOf(initialRecordsObj[i][9])));
					initialList.add(beanItt);
				}
				bonusBean.setInitialRecordListIterator(initialList);
			} else {
				bonusBean.setInitialEmptyListFlag(true);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : generateTemplate. Purpose : This function is used to create a
	 * template for uploading salary details & to generate report.
	 * 
	 * @param bonusBean - bean
	 * @param response - response
	 * @param type - Determines whether to generate template or generate report
	 * @param templateName : Name of the template
	 */
	public void generateTemplate(final BonusAllowance bonusBean,
			final HttpServletResponse response, final String type,
			final String templateName) {
		try {
			String fileName = "";
			if (this.manualStr.equals(templateName)) {
				fileName = "Template for uploading Bonus/Allowance";
			} else if (this.systemStr.equals(templateName)) {
				fileName = "Template for uploading individual Ratings";
			} else {
				fileName = "Bonus/Allowance Report";
			}
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			if ("TEMPLATE".equals(type)) {
				rg = this.getTemplate(rg, bonusBean, templateName, "underProcessTemplate");
			} else if ("PROCESSEDRATINGS".equals(type)) {
				rg = this.getTemplate(rg, bonusBean, templateName, this.processedRatingsTemplateStr);
			} else if ("PROCESSEDBONUS".equals(type)) {
				rg = this.getTemplate(rg, bonusBean, templateName, this.processedBonusTemplateStr);
			}
			rg.createReport(response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : getTemplate. 
	 * Purpose : This method generates the template to be uploaded for selected credits & debits.
	 * 
	 * @param rg - Report Generator Object
	 * @param bonusBean - bean
	 * @param templateName : templateName
	 * @param typeOfTemplate : typeOfTemplate
	 * @return Report Generator object
	 */
	private ReportGenerator getTemplate(final ReportGenerator rg,
			final BonusAllowance bonusBean, final String templateName,
			final String typeOfTemplate) {
		try {
			final Object[][] header = new Object[2][3];
			header[0][0] = this.empTokenStr;
			header[1][0] = this.starStr;
			header[0][1] = "Name";
			header[1][1] = "";
			if (this.manualStr.equals(templateName)) {
				header[0][2] = "Bonus/Allowance";
				header[1][2] = this.starStr;
			}

			if (this.systemStr.equals(templateName)) {
				header[0][2] = "Ratings";
				header[1][2] = this.starStr;
			}

			final int[] alignment = new int[header[0].length];
			final int[] cellwidth = new int[header[0].length];
			for (int i = 0; i < header[0].length; i++) {
				alignment[i] = 0;
				cellwidth[i] = 30;
			}

			Object[][] dataObject = null;

			if (this.processedRatingsTemplateStr.equals(typeOfTemplate)) {
				dataObject = this.getProcessedRecords(bonusBean, this.selectRatingsStr);
			} else if (this.processedBonusTemplateStr.equals(typeOfTemplate)) {
				dataObject = this.getProcessedRecords(bonusBean, this.selectBonusStr);
			} else {
				dataObject = this.getSelectedFilterWiseEmployees(bonusBean);
			}

			final Object[][] color = new Object[dataObject.length][header[0].length];
			if (color != null && color.length > 0) {
				for (int i = 0; i < color.length; i++) {
					for (int j = 0; j < color[0].length; j++) {
						color[i][0] = Utility.WHITE;
						color[i][1] = Utility.WHITE;
						color[i][2] = Utility.WHITE;
					}
				}
			}
			rg.tableBodyWithColor(header, dataObject, cellwidth, alignment, color);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * Method : getProcessedRatingsRecords. 
	 * Purpose : This method is used to view processed Records
	 * 
	 * @param bonusBean : bonusBean
	 * @param columnToSelect : columnToSelect
	 * @return Object
	 */
	private Object[][] getProcessedRecords(final BonusAllowance bonusBean, final String columnToSelect) {
		Object[][] finalObject = null;
		try {
			String stringToAppend = "";
			if (this.selectRatingsStr.equals(columnToSelect)) {
				stringToAppend = "HRMS_BONUS_EMP.BONUS_RATING";
			}

			if (this.selectBonusStr.equals(columnToSelect)) {
				stringToAppend = "HRMS_BONUS_EMP.EMP_BONUS_AMT";
			}

			final String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) , " + stringToAppend + 
					" FROM HRMS_BONUS_EMP " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID();

			final Object[][] empDataObj = this.getSqlModel().getSingleResult(query);
			if (empDataObj != null && empDataObj.length > 0) {
				finalObject = new Object[empDataObj.length][3];
				for (int i = 0; i < finalObject.length; i++) {
					for (int j = 0; j < finalObject[0].length; j++) {
						finalObject[i][j] = String.valueOf(empDataObj[i][j]);
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return finalObject;
	}

	/**
	 * Method : getSelectedFilterWiseEmployees. Purpose : This method adds
	 * employees as per the filter to the excel table.
	 * 
	 * @param bonusBean - bean
	 * @return Object
	 */
	public final Object[][] getSelectedFilterWiseEmployees(final BonusAllowance bonusBean) {
		Object[][] finalObject = null;
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) " + 
					" FROM HRMS_EMP_OFFC " + " WHERE 1=1 ";
			if (!"".equals(bonusBean.getDivisionID())) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV =" + bonusBean.getDivisionID();
			}
			if (!"".equals(bonusBean.getBranchID())) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN( " + bonusBean.getBranchID() + " )";
			}
			if (!"".equals(bonusBean.getPaybillID())) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN( " + bonusBean.getPaybillID() + " )";
			}
			if (!"".equals(bonusBean.getDepartmentID())) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN( " + bonusBean.getDepartmentID() + ")";
			}
			if (!"".equals(bonusBean.getFilterEmpId())) {
				query += " AND HRMS_EMP_OFFC.EMP_ID IN(" + bonusBean.getFilterEmpId() + ")";
			}
			query += "  AND HRMS_EMP_OFFC.EMP_STATUS='S' AND " + 
					" HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('" + bonusBean.getProcessedToMonth() + "-" + 
					bonusBean.getToYear() + "','MM-YYYY')) ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			final Object[][] empDataObj = this.getSqlModel().getSingleResult(query);

			if (empDataObj != null && empDataObj.length > 0) {
				finalObject = new Object[empDataObj.length][2];
				for (int i = 0; i < finalObject.length; i++) {
					for (int j = 0; j < finalObject[0].length; j++) {
						finalObject[i][j] = String.valueOf(empDataObj[i][j]);
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return finalObject;
	}

	/**Method : getReport.
	 * Purpose : This method is used to generate report
	 *  
	 * @param bonusBean : bonusBean
	 * @param response : response
	 * @param request : request
	 * @param reportPath : reportPath
	 */
	public void getReport(final BonusAllowance bonusBean,
			final HttpServletResponse response, final HttpServletRequest request,
			final String reportPath) {
		try {
			final ReportDataSet rds = new ReportDataSet();
			final String type = "pdf";
			rds.setReportType(type);
			final String fileName = "Bonus Summary Report " + Utility.getRandomNumber(1000);
			final String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Bonus Summary Report");
			rds.setPageSize("A4");
			// rds.setPageOrientation("landscape");
			rds.setUserEmpId(bonusBean.getUserEmpId());
			// rds.setMarginBottom(25);
			// rds.setMarginLeft(25);
			// rds.setMarginRight(25);
			rds.setShowPageNo(true);
			// rds.setTotalColumns(16);
			// rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if ("".equals(reportPath)) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				this.logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"YearlyEDSummaryReport_input.action");
			}
			rg = this.getReport(rg, bonusBean);
			rg.process();
			if ("".equals(reportPath)) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment */
				rg.saveReport(response);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : getReport. Purpose : This function generates the Bonus details
	 * report of the employees as per the filter selected.
	 * 
	 * @param rg - Report Generator Object
	 * @param bonusBean - bean
	 * @return Report New Generator object
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(
			final org.paradyne.lib.ireportV2.ReportGenerator rg,
			final BonusAllowance bonusBean) {
		try {
			final String fromYear = Utility.month(Integer.parseInt(bonusBean.getFromMonth())) + " " + bonusBean.getFromYear();
			final String toYear = Utility.month(Integer.parseInt(bonusBean.getToMonth())) + " " + bonusBean.getToYear();
			String filters = "Bonus Period : " + fromYear + " - " + toYear;
			if (!"".equals(bonusBean.getDivisionName().trim())) {
				filters += "\n\nDivision : " + bonusBean.getDivisionName();
			}

			if (!"".equals(bonusBean.getBranchName().trim())) {
				filters += "\n\nBranch : " + bonusBean.getBranchName();
			}

			if (!"".equals(bonusBean.getDepartmentName())) {
				filters += "\n\nDepartment : " + bonusBean.getDepartmentName();
			}

			if (!"".equals(bonusBean.getPaybillName().trim())) {
				filters += "\n\nPaybill : " + bonusBean.getPaybillName();
			}

			if (!"".equals(bonusBean.getFilterEmpName().trim())) {
				filters += "\n\nEmployee : " + bonusBean.getFilterEmpName();
			}

			final TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] {{filters}});
			filterData.setCellAlignment(new int[] {0});
			filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] {100});
			filterData.setCellColSpan(new int[] {16});
			filterData.setCellNoWrap(new boolean[] {false});
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);

			if (bonusBean.getSystemCalculatedBonusCheckBox().equals(this.trueStr)) {
				if (bonusBean.getSlabwiseMethodCheckbox().equals(this.trueStr)) {
					final Object[][] slabTableHeading = new Object[1][1];
					slabTableHeading[0][0] = "Slab Details";
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
					slabTableHeader[0] = "From Amount " + bonusBean.getCalCulatedBonusComponents();
					slabTableHeader[1] = "To Amount " + bonusBean.getCalCulatedBonusComponents();
					slabTableHeader[2] = "Percentage " + bonusBean.getCalCulatedBonusComponents();
					slabTableHeader[3] = "Minimum Amount " + bonusBean.getCalCulatedBonusComponents();
					slabTableHeader[4] = "Maximum Amount " + bonusBean.getCalCulatedBonusComponents();

					final int[] slabCellAlign = {2, 2, 2, 2, 2};
					final int[] slabCellWidth = {20, 20, 20, 20, 20};

					final Object[][] slabDataObject = this.getSqlModel().getSingleResult(
									"SELECT NVL(HRMS_BONUS_SLAB.BONUS_FROM_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_TO_AMT,0.0), NVL(HRMS_BONUS_SLAB.BOMUS_PERSENTAGE,0.0), " + 
											" NVL(HRMS_BONUS_SLAB.BONUS_MIN_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_MAX_AMT,0.0) FROM HRMS_BONUS_SLAB" + 
											" WHERE HRMS_BONUS_SLAB.BONUS_CODE = " + bonusBean.getBonusAllowanceID());

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

				if (bonusBean.getIndividualRatingsMethodCheckbox().equals(
						this.trueStr)) {
					final Object[][] individualRatingSection = new Object[1][1];
					individualRatingSection[0][0] = "Individual Rating Details";
					final int[] cellWidthIndividualRating = {100};
					final int[] cellAlignIndividualRating = {0};

					final TableDataSet tableHeadingDateData = new TableDataSet();
					tableHeadingDateData.setData(individualRatingSection);
					tableHeadingDateData.setCellWidth(cellWidthIndividualRating);
					tableHeadingDateData.setCellAlignment(cellAlignIndividualRating);
					tableHeadingDateData.setBodyFontStyle(1);
					tableHeadingDateData.setCellColSpan(new int[] {16});
					tableHeadingDateData.setBorderDetail(0);
					tableHeadingDateData.setBlankRowsAbove(1);
					rg.addTableToDoc(tableHeadingDateData);

					String[] individualRatingHeader = null;
					individualRatingHeader = new String[6];
					individualRatingHeader[0] = this.empTokenStr;
					individualRatingHeader[1] = this.empNameStr;
					individualRatingHeader[2] = "Rating";
					individualRatingHeader[3] = this.bonusAmountStr;
					individualRatingHeader[4] = this.taxAmountStr;
					individualRatingHeader[5] = this.bonusTotalAmountStr;

					final int[] individualRatingHeaderCellAlign = new int[individualRatingHeader.length];
					final int[] individualRatingHeaderCellWidth = new int[individualRatingHeader.length];
					final boolean[] individualRatingHeaderCellWidthCellwrap = new boolean[individualRatingHeader.length];
					for (int i = 0; i < individualRatingHeader.length; i++) {
						if (i == 0) {
							individualRatingHeaderCellAlign[i] = 0;
							individualRatingHeaderCellWidth[i] = 15;
							individualRatingHeaderCellWidthCellwrap[i] = true;
						} else if (i == 1) {
							individualRatingHeaderCellAlign[i] = 0;
							individualRatingHeaderCellWidth[i] = 30;
							individualRatingHeaderCellWidthCellwrap[i] = true;
						} else {
							individualRatingHeaderCellAlign[i] = 2;
							individualRatingHeaderCellWidth[i] = 15;
							individualRatingHeaderCellWidthCellwrap[i] = true;
						}
					}

					final Object[][] individualRatingDataObject = this.getSqlModel().getSingleResult(
									"  SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
											"  NVL(HRMS_BONUS_EMP.BONUS_RATING,0), NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0.0), NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0.0), NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0.0) FROM HRMS_BONUS_EMP " + 
											"  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " + 
											"  WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID() + 
											"  ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ASC");

					final TableDataSet individualRatingTableDataHeader = new TableDataSet();
					individualRatingTableDataHeader.setHeader(individualRatingHeader);
					individualRatingTableDataHeader.setHeaderBorderDetail(3);
					individualRatingTableDataHeader.setCellWidth(individualRatingHeaderCellWidth);
					individualRatingTableDataHeader.setCellAlignment(individualRatingHeaderCellAlign);
					individualRatingTableDataHeader.setData(individualRatingDataObject);
					individualRatingTableDataHeader.setBodyFontStyle(1);
					individualRatingTableDataHeader.setBorderDetail(3);
					individualRatingTableDataHeader.setHeaderTable(true);
					rg.addTableToDoc(individualRatingTableDataHeader);
					rg.seperatorLine();

					final Object[][] totalRatingDataObject = this.getSqlModel().getSingleResult(
									"  SELECT NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_AMT),0.0), " + 
											"   NVL(sum(HRMS_BONUS_EMP.BONUS_TAX_AMT),0.0), " + 
											"   NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),0.0)  " + 
											"   FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID());

					final Object[][] totalRatingObj = new Object[1][6];
					totalRatingObj[0][0] = this.totalAmountStr;
					totalRatingObj[0][1] = " ";
					totalRatingObj[0][2] = " ";
					totalRatingObj[0][3] = String.valueOf(totalRatingDataObject[0][0]);
					totalRatingObj[0][4] = String.valueOf(totalRatingDataObject[0][1]);
					totalRatingObj[0][5] = String.valueOf(totalRatingDataObject[0][2]);

					final int[] toalCellAlign = {0, 0, 2, 2, 2, 2};
					final int[] toalCellWidth = {15, 30, 15, 15, 15, 15};

					final TableDataSet totalDataSet = new TableDataSet();
					totalDataSet.setData(totalRatingObj);
					totalDataSet.setCellWidth(toalCellWidth);
					totalDataSet.setCellAlignment(toalCellAlign);
					totalDataSet.setBodyFontStyle(1);
					rg.addTableToDoc(totalDataSet);
					rg.seperatorLine();

				}

				if (bonusBean.getFlatRateMethodCheckbox().equals(this.trueStr)) {
					final String flatRate = "Defined Flat Rate : " + bonusBean.getFlatRateBonusAllowanceCalculation();
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

			if (bonusBean.getSlabwiseMethodCheckbox().equals(this.trueStr) || 
					bonusBean.getFlatRateMethodCheckbox().equals(this.trueStr) || 
					bonusBean.getManuallyCalculatedBonusCheckBox().equals(
							this.trueStr)) {
				final Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "Bonus Details";
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

				String[] header = null;
				header = new String[5];
				header[0] = this.empTokenStr;
				header[1] = this.empNameStr;
				header[2] = this.bonusAmountStr;
				header[3] = this.taxAmountStr;
				header[4] = this.bonusTotalAmountStr;

				final int[] bcellAlign = new int[header.length];
				final int[] bcellWidth = new int[header.length];
				final boolean[] bcellwrap = new boolean[header.length];
				for (int i = 0; i < header.length; i++) {
					if (i == 0) {
						bcellAlign[i] = 0;
						bcellWidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 1) {
						bcellAlign[i] = 0;
						bcellWidth[i] = 30;
						bcellwrap[i] = true;
					} else {
						bcellAlign[i] = 2;
						bcellWidth[i] = 15;
						bcellwrap[i] = true;
					}
				}

				final Object[][] dataObject = this.getSqlModel().getSingleResult(
								" SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
										"   NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0.0), NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0.0), NVL(HRMS_BONUS_EMP.EMP_BONUS_TOTAL,0.0)  FROM HRMS_BONUS_EMP " + 
										"   INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " + 
										"   WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID() + 
										"   ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ASC");

				final TableDataSet tableDataHeader = new TableDataSet();
				tableDataHeader.setHeader(header);
				tableDataHeader.setHeaderBorderDetail(3);
				tableDataHeader.setCellWidth(bcellWidth);
				tableDataHeader.setCellAlignment(bcellAlign);
				tableDataHeader.setData(dataObject);
				tableDataHeader.setBodyFontStyle(1);
				tableDataHeader.setBorderDetail(3);
				tableDataHeader.setHeaderTable(true);
				rg.addTableToDoc(tableDataHeader);
				rg.seperatorLine();

				final Object[][] totalBonusDataObject = this.getSqlModel().getSingleResult(
								" SELECT NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_AMT),0.0), " + 
										" NVL(sum(HRMS_BONUS_EMP.BONUS_TAX_AMT),0.0), " + 
										" NVL(sum(HRMS_BONUS_EMP.EMP_BONUS_TOTAL),0.0)  " + 
										" FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID());

				final Object[][] totalBonusObj = new Object[1][5];
				totalBonusObj[0][0] = this.totalAmountStr;
				totalBonusObj[0][1] = " ";
				totalBonusObj[0][2] = String.valueOf(totalBonusDataObject[0][0]);
				totalBonusObj[0][3] = String.valueOf(totalBonusDataObject[0][1]);
				totalBonusObj[0][4] = String.valueOf(totalBonusDataObject[0][2]);

				final int[] toalBonusCellAlign = {0, 0, 2, 2, 2};
				final int[] toalBonusCellWidth = {15, 30, 15, 15, 15};

				final TableDataSet totalDataSet = new TableDataSet();
				totalDataSet.setData(totalBonusObj);
				totalDataSet.setCellWidth(toalBonusCellWidth);
				totalDataSet.setCellAlignment(toalBonusCellAlign);
				totalDataSet.setBodyFontStyle(1);
				rg.addTableToDoc(totalDataSet);
				rg.seperatorLine();

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * Method : uploadBonusAllowanceData. Purpose : This method is used to
	 * manually upload Bonus/Allowance
	 * 
	 * @param response : response
	 * @param request : request
	 * @param bonusBean : bonusBean
	 * @return boolean
	 */
	public boolean uploadBonusAllowanceData(HttpServletResponse response,
			HttpServletRequest request, BonusAllowance bonusBean) {
		boolean finalReturnResult = false;
		try {
			bonusBean.setFileNameManuallyUploadedBonusAllowance(bonusBean.getFileNameForManuallyCalculatedBonusAllowance());
			final String filePath = bonusBean.getDataPath() + "" + bonusBean.getFileNameForManuallyCalculatedBonusAllowance();
			logger.info("############# FILE PATH #############" + filePath);
			bonusBean.setTempFileNameManuallyUploadedBonus("ManuallyUploadedBonusAllowance");
			MigratePayrollExcelData.getFile(filePath);

			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigratePayrollExcelData.isColumnsMandatory();

			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') FROM HRMS_EMP_OFFC WHERE 1 = 1 ";

			if (!bonusBean.getDivisionID().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN(" + bonusBean.getDivisionID() + ")";
			}
			if (!bonusBean.getBranchID().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(" + bonusBean.getBranchID() + ")";
			}
			if (!bonusBean.getPaybillID().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + bonusBean.getPaybillID() + ")";
			}
			if (!bonusBean.getDepartmentID().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN(" + bonusBean.getDepartmentID() + ")";
			}
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + bonusBean.getToMonth() + "-" + bonusBean.getToYear() + "','MM-YYYY')) " + 
			" ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			final Object[][] employeeMaster = this.getSqlModel().getSingleResult(query);

			Object[][] employeeTokenObj = null;
			Object[][] empNameObj = null;
			if (employeeMaster != null && employeeMaster.length > 0) {
				employeeTokenObj = MigratePayrollExcelData.uploadExcelData(1, employeeMaster, MigratePayrollExcelData.MASTER_TYPE, columnInformation.get(1));
				empNameObj = MigratePayrollExcelData.uploadExcelData(2, null, MigratePayrollExcelData.STRING_TYPE, columnInformation.get(2));
			}

			final Object[][] bonusAmountObj = MigratePayrollExcelData.uploadExcelData(3, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(3));
			/* Checks if the values are in proper format */
			final boolean result = MigratePayrollExcelData.isFileToBeUploaded();
			this.logger.info("#### Integrity Check ####" + result);
			if (result) {
				boolean insertUpdateHdrDataFlag = false;
				if (bonusBean.getBonusAllowanceID().trim().equals("")) {
					final String maxBonusHdrCodeQuery = "SELECT NVL(MAX(HRMS_BONUS_HDR.BONUS_CODE),0)+1 FROM HRMS_BONUS_HDR";
					final Object[][] maxBonusHdrCodeObj = this.getSqlModel().getSingleResult(maxBonusHdrCodeQuery);
					if (maxBonusHdrCodeObj != null && maxBonusHdrCodeObj.length > 0) {
						bonusBean.setBonusAllowanceID(String.valueOf(maxBonusHdrCodeObj[0][0]));
					}
					insertUpdateHdrDataFlag = this.insertIntoBonusHdr(bonusBean);
				} else {
					insertUpdateHdrDataFlag = this.updateBonusHdr(bonusBean);
				}

				if (insertUpdateHdrDataFlag) {
					final String bonusCode = bonusBean.getBonusAllowanceID();
					final Object[][] finalEmpBonusObj = new Object[employeeTokenObj.length][6];
					final Object[][] finalDeleteObj = new Object[employeeTokenObj.length][2];
					boolean insertResult;
					if (employeeTokenObj != null && employeeTokenObj.length > 0) {
						for (int i = 0; i < employeeTokenObj.length; i++) {
							// First delete those records which are available in
							// HRMS_BONUS_EMP
							finalDeleteObj[i][0] = String.valueOf(employeeTokenObj[i][0]);
							finalDeleteObj[i][1] = bonusCode;

							finalEmpBonusObj[i][0] = bonusCode;
							finalEmpBonusObj[i][1] = String.valueOf(employeeTokenObj[i][0]);
							if (bonusAmountObj != null && bonusAmountObj.length > 0) {
								finalEmpBonusObj[i][2] = String.valueOf(bonusAmountObj[i][0]);
							} else {
								finalEmpBonusObj[i][2] = "0.00";
							}

							if (bonusBean.getDeductIncomeTaxCheckBox().equals(this.trueStr)) {
								finalEmpBonusObj[i][3] = "0.00";
							} else {
								finalEmpBonusObj[i][3] = "0.00";
							}

							if (bonusBean.getDeductIncomeTaxCheckBox().equals(this.trueStr)) {
								finalEmpBonusObj[i][4] = (Double.parseDouble((String.valueOf(bonusAmountObj[i][0]))) + Double.parseDouble(String.valueOf(finalEmpBonusObj[i][3])));
							} else {
								finalEmpBonusObj[i][4] = String.valueOf(bonusAmountObj[i][0]);
							}
							finalEmpBonusObj[i][5] = 0;
						}

						boolean deleteResult = this.getSqlModel().singleExecute(getQuery(4), finalDeleteObj);
						if (deleteResult) {
							insertResult = getSqlModel().singleExecute(getQuery(5), finalEmpBonusObj);
							if (insertResult) {
								bonusBean.setStatus("Success");
								bonusBean.setNote("Data uploaded successfully, Please verify the same.");
								finalReturnResult = true;
							} else {
								bonusBean.setNote("Error inserting data");
							}
							/*
							 * String updateBonusTotalAmountQuery = "UPDATE
							 * HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_TOTAL_AMT = " +
							 * "(SELECT SUM(EMP_BONUS_AMT) FROM HRMS_BONUS_EMP
							 * WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusCode + " )" + "
							 * WHERE HRMS_BONUS_HDR.BONUS_CODE = " + bonusCode;
							 * getSqlModel().singleExecute(updateBonusTotalAmountQuery);
							 * 
							 * String updateBonusEmployeeCountQuery = "UPDATE
							 * HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_EMP_COUNT = " +
							 * "(SELECT COUNT(*) FROM HRMS_BONUS_EMP WHERE
							 * HRMS_BONUS_EMP.BONUS_CODE = "+ bonusCode + ") "+ "
							 * WHERE HRMS_BONUS_HDR.BONUS_CODE = " +bonusCode;
							 * getSqlModel().singleExecute(updateBonusEmployeeCountQuery);
							 */
							final Object[][] bonusIDObj = new Object[1][3];
							bonusIDObj[0][0] = bonusBean.getBonusAllowanceID();
							bonusIDObj[0][1] = bonusBean.getBonusAllowanceID();
							bonusIDObj[0][2] = bonusBean.getBonusAllowanceID();
							this.getSqlModel().singleExecute(getQuery(13), bonusIDObj);
						} else {
							bonusBean.setStatus("Fail");
							bonusBean.setNote("Error inserting data");
							finalReturnResult = false;
						}
					}
				}
			} else {
				bonusBean.setStatus("Fail");
				bonusBean.setNote(this.messageStr);
				finalReturnResult = false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			bonusBean.setStatus("Fail");
			bonusBean
					.setNote(this.messageStr);
			finalReturnResult = false;
		}
		bonusBean.setDisplayNoteFlag(true);
		return finalReturnResult;
	}

	/**
	 * Method : updateBonusHdr.
	 * 
	 * @param bonusBean : bonusBean
	 * @return boolean
	 */
	public boolean updateBonusHdr(BonusAllowance bonusBean) {
		boolean result = false;
		try {
			final Object[][] updateHdrObj = new Object[1][27];
			updateHdrObj[0][0] = bonusBean.getFromMonth();
			updateHdrObj[0][1] = bonusBean.getFromYear();
			updateHdrObj[0][2] = bonusBean.getToMonth();
			updateHdrObj[0][3] = bonusBean.getToYear();
			updateHdrObj[0][4] = bonusBean.getPayInSalaryCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][5] = bonusBean.getSalaryMonth();
			updateHdrObj[0][6] = bonusBean.getSalaryYear();
			updateHdrObj[0][7] = bonusBean.getPayInComponentID();
			updateHdrObj[0][8] = bonusBean.getDeductIncomeTaxCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][9] = bonusBean.getSystemCalculatedBonusCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][10] = bonusBean.getManuallyCalculatedBonusCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][11] = bonusBean.getCalculatePaidDaysCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][12] = bonusBean.getCalCulatedBonusComponents();
			updateHdrObj[0][13] = bonusBean.getSlabwiseMethodCheckbox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][14] = bonusBean.getIndividualRatingsMethodCheckbox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][15] = bonusBean.getFlatRateMethodCheckbox().equals(this.trueStr) ? this.yesStr : this.nStr;
			updateHdrObj[0][16] = bonusBean.getFlatRateBonusAllowanceCalculation();
			updateHdrObj[0][17] = bonusBean.getFlatRateMaximumBonusAllowance();
			updateHdrObj[0][18] = bonusBean.getFlatRateMinimumBonusAllowance();
			updateHdrObj[0][19] = "".equals(bonusBean.getBonusAllowanceStatus().trim()) ? "U" : bonusBean.getBonusAllowanceStatus();
			updateHdrObj[0][20] = bonusBean.getDivisionID();
			updateHdrObj[0][21] = bonusBean.getBranchID();
			updateHdrObj[0][22] = bonusBean.getPaybillID();
			updateHdrObj[0][23] = bonusBean.getDepartmentID();
			updateHdrObj[0][24] = "0.00";
			updateHdrObj[0][25] = bonusBean.getFilterEmpId();
			updateHdrObj[0][26] = bonusBean.getBonusAllowanceID();
			result = this.getSqlModel().singleExecute(this.getQuery(3), updateHdrObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : insertIntoBonusHdr. Purpose : This method is used to insert data
	 * into HRMS_BONUS_HDR table
	 * 
	 * @param bonusBean : bonusBean
	 * @return boolean
	 */
	public boolean insertIntoBonusHdr(final BonusAllowance bonusBean) {
		boolean result = false;
		try {
			final Object[][] insertIntoHdrObj = new Object[1][27];
			insertIntoHdrObj[0][0] = bonusBean.getBonusAllowanceID();
			insertIntoHdrObj[0][1] = bonusBean.getFromMonth();
			insertIntoHdrObj[0][2] = bonusBean.getFromYear();
			insertIntoHdrObj[0][3] = bonusBean.getToMonth();
			insertIntoHdrObj[0][4] = bonusBean.getToYear();
			insertIntoHdrObj[0][5] = bonusBean.getPayInSalaryCheckBox().equals(
					this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][6] = bonusBean.getSalaryMonth();
			insertIntoHdrObj[0][7] = bonusBean.getSalaryYear();
			insertIntoHdrObj[0][8] = bonusBean.getPayInComponentID();
			insertIntoHdrObj[0][9] = bonusBean.getDeductIncomeTaxCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][10] = bonusBean.getSystemCalculatedBonusCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][11] = bonusBean.getManuallyCalculatedBonusCheckBox().equals(this.trueStr) ? this.yesStr	: this.nStr;
			insertIntoHdrObj[0][12] = bonusBean.getCalculatePaidDaysCheckBox().equals(this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][13] = bonusBean.getCalCulatedBonusComponents();
			insertIntoHdrObj[0][14] = bonusBean.getSlabwiseMethodCheckbox().equals(this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][15] = bonusBean.getIndividualRatingsMethodCheckbox().equals(this.trueStr) ? this.yesStr	: this.nStr;
			insertIntoHdrObj[0][16] = bonusBean.getFlatRateMethodCheckbox().equals(this.trueStr) ? this.yesStr : this.nStr;
			insertIntoHdrObj[0][17] = bonusBean.getFlatRateBonusAllowanceCalculation();
			insertIntoHdrObj[0][18] = bonusBean.getFlatRateMaximumBonusAllowance();
			insertIntoHdrObj[0][19] = bonusBean.getFlatRateMinimumBonusAllowance();
			insertIntoHdrObj[0][20] = "".equals(bonusBean.getBonusAllowanceStatus().trim()) ? "U" : bonusBean.getBonusAllowanceStatus();
			insertIntoHdrObj[0][21] = bonusBean.getDivisionID();
			insertIntoHdrObj[0][22] = bonusBean.getBranchID();
			insertIntoHdrObj[0][23] = bonusBean.getPaybillID();
			insertIntoHdrObj[0][24] = bonusBean.getDepartmentID();
			insertIntoHdrObj[0][25] = "0.00";
			insertIntoHdrObj[0][26] = bonusBean.getFilterEmpId();
			result = this.getSqlModel().singleExecute(this.getQuery(2), insertIntoHdrObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : updateBonusAllowanceStatus. 
	 * Purpose : This method is used to lock Bonus Allowance
	 * 
	 * @param bonusBean : bonusBean
	 */
	public boolean updateBonusAllowanceStatus(BonusAllowance bonusBean, String statusToUpdate) {
		boolean lockResult = false;
		try {
			final Object[][] updateHdrObj = new Object[1][2];
			updateHdrObj[0][0] = statusToUpdate;
			updateHdrObj[0][1] = bonusBean.getBonusAllowanceID();
			String TDS_DEBIT_CODE = "";
			final String query = "  SELECT NVL(TDS_DEBIT_CODE,0) from HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = " + 
					String.valueOf(Integer.parseInt(bonusBean.getSalaryYear())) + 
					" AND TDS_FINANCIALYEAR_TO = " + String.valueOf(Integer.parseInt(bonusBean.getSalaryYear()) + 1);
			final Object[][] tdsDebitCodeObj = this.getSqlModel().getSingleResult(query);
			if (tdsDebitCodeObj != null && tdsDebitCodeObj.length > 0) {
				TDS_DEBIT_CODE = String.valueOf(tdsDebitCodeObj[0][0]);
			}
			lockResult = this.getSqlModel().singleExecute(this.getQuery(6), updateHdrObj);
			if (lockResult) {
				Object[][] insertObj = null;
				Object[][] insertTDSObj = null;
				Object[][] deleteObj = null;
				Object[][] deleteTDSObj = null;
				Object[][] updateObj = null;

				final String getProccessedEmployeesQuery = "SELECT HRMS_BONUS_EMP.EMP_ID, NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0),NVL(BONUS_TAX_AMT,0) FROM HRMS_BONUS_EMP " + 
						" WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID();
				final Object[][] proccessedObj = this.getSqlModel().getSingleResult(getProccessedEmployeesQuery);
				if (proccessedObj != null && proccessedObj.length > 0) {
					insertObj = new Object[proccessedObj.length][7];
					insertTDSObj = new Object[proccessedObj.length][7];
					deleteObj = new Object[proccessedObj.length][4];
					deleteTDSObj = new Object[proccessedObj.length][4];
					updateObj = new Object[proccessedObj.length][1];
					for (int i = 0; i < proccessedObj.length; i++) {
						insertObj[i][0] = Utility.checkNull(String.valueOf(proccessedObj[i][0]));
						insertObj[i][1] = bonusBean.getSalaryMonth();
						insertObj[i][2] = bonusBean.getSalaryYear();
						insertObj[i][3] = "C";
						insertObj[i][4] = bonusBean.getPayInComponentID();
						insertObj[i][5] = Utility.checkNull(String.valueOf(proccessedObj[i][1]));
						insertObj[i][6] = this.yesStr;

						insertTDSObj[i][0] = Utility.checkNull(String.valueOf(proccessedObj[i][0]));
						insertTDSObj[i][1] = bonusBean.getSalaryMonth();
						insertTDSObj[i][2] = bonusBean.getSalaryYear();
						insertTDSObj[i][3] = "D";
						insertTDSObj[i][4] = TDS_DEBIT_CODE;
						insertTDSObj[i][5] = Utility.checkNull(String.valueOf(proccessedObj[i][2]));
						insertTDSObj[i][6] = this.nStr;

						deleteObj[i][0] = Utility.checkNull(String.valueOf(proccessedObj[i][0]));
						deleteObj[i][1] = bonusBean.getSalaryMonth();
						deleteObj[i][2] = bonusBean.getSalaryYear();
						deleteObj[i][3] = bonusBean.getPayInComponentID();

						deleteTDSObj[i][0] = Utility.checkNull(String.valueOf(proccessedObj[i][0]));
						deleteTDSObj[i][1] = bonusBean.getSalaryMonth();
						deleteTDSObj[i][2] = bonusBean.getSalaryYear();
						deleteTDSObj[i][3] = TDS_DEBIT_CODE;

						updateObj[i][0] = Utility.checkNull(String.valueOf(proccessedObj[i][0]));
					}
				}

				if (bonusBean.getPayInSalaryCheckBox().equals(this.trueStr)) {
					if ("L".equals(statusToUpdate)) {
						final String insertQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (HRMS_MISC_SALARY_UPLOAD.EMP_ID, HRMS_MISC_SALARY_UPLOAD.MONTH, HRMS_MISC_SALARY_UPLOAD.YEAR, HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE, HRMS_MISC_SALARY_UPLOAD.SALARY_CODE, HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT, HRMS_MISC_SALARY_UPLOAD.UPLOAD_IS_OVERWRITE" + 
								"  ,APPL_CODE,APPL_TYPE,DISPLAY_FLAG,COMMENTS) " + 
								"  VALUES(?, ?, ?, ?, ?, ?, ?," + bonusBean.getBonusAllowanceID() + 
								",'B','Y','BONUS AMOUNT')";
						this.getSqlModel().singleExecute(insertQuery, insertObj);

						final String insertTDSQuery = "INSERT INTO HRMS_MISC_SALARY_UPLOAD (HRMS_MISC_SALARY_UPLOAD.EMP_ID, HRMS_MISC_SALARY_UPLOAD.MONTH, HRMS_MISC_SALARY_UPLOAD.YEAR, HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE, HRMS_MISC_SALARY_UPLOAD.SALARY_CODE, HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT, HRMS_MISC_SALARY_UPLOAD.UPLOAD_IS_OVERWRITE" + 
								" ,APPL_CODE,APPL_TYPE,DISPLAY_FLAG,COMMENTS) " + 
								" VALUES(?, ?, ?, ?, ?, ?, ?," + bonusBean.getBonusAllowanceID() + ",'B','N','BONUS AMOUNT')";
						this.getSqlModel().singleExecute(insertTDSQuery, insertTDSObj);

						// DELETE ROWS
						final String deleteQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_TYPE='B' AND SALARY_AMOUNT=0 AND DISPLAY_FLAG='N' AND APPL_CODE=" + bonusBean.getBonusAllowanceID();
						this.getSqlModel().singleExecute(deleteQuery);
						// UPDATE SALARY FLAG

						final String updateSalary = " UPDATE HRMS_SALARY_" + bonusBean.getSalaryYear() + 
								"  SET EMP_MISC_UPLOAD_FLAG='Y' WHERE EMP_ID = ? AND SAL_MONTH=" + bonusBean.getSalaryMonth() + 
								"  AND SAL_YEAR =" + bonusBean.getSalaryYear();
						this.getSqlModel().singleExecute(updateSalary, updateObj);
					}
				}

				if ("U".equals(statusToUpdate)) {
					final String deleteQuery = " DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE HRMS_MISC_SALARY_UPLOAD.EMP_ID = ?" + 
							"  AND HRMS_MISC_SALARY_UPLOAD.MONTH = ? AND HRMS_MISC_SALARY_UPLOAD.YEAR = ? " + 
							" AND HRMS_MISC_SALARY_UPLOAD.SALARY_CODE = ? AND APPL_TYPE='B' AND DISPLAY_FLAG='Y' AND APPL_CODE=" + bonusBean.getBonusAllowanceID();
					this.getSqlModel().singleExecute(deleteQuery, deleteObj);

					final String deleteTDSQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE HRMS_MISC_SALARY_UPLOAD.EMP_ID = ?" + 
							" AND HRMS_MISC_SALARY_UPLOAD.MONTH = ? AND HRMS_MISC_SALARY_UPLOAD.YEAR = ? " + 
							" AND HRMS_MISC_SALARY_UPLOAD.SALARY_CODE = ? AND APPL_TYPE='B' AND DISPLAY_FLAG='N' AND APPL_CODE=" + bonusBean.getBonusAllowanceID();
					this.getSqlModel().singleExecute(deleteTDSQuery, deleteTDSObj);

					final String updateSalary = "UPDATE HRMS_SALARY_" + bonusBean.getSalaryYear() + 
							" SET EMP_MISC_UPLOAD_FLAG='Y' WHERE EMP_ID = ? AND SAL_MONTH=" + bonusBean.getSalaryMonth() + 
							" AND SAL_YEAR =" + bonusBean.getSalaryYear();
					this.getSqlModel().singleExecute(updateSalary, updateObj);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return lockResult;
	}

	/**
	 * Method : getBonusAllowanceDetails. 
	 * Purpose : This method is used to retrieve Bonus Allowance details
	 * 
	 * @param bonusBean : bonusBean
	 * @param bonusCode : Bonus Code
	 */
	public void getBonusAllowanceDetails(final BonusAllowance bonusBean, final String bonusCode) {
		try {
			final Object[][] bonusAllowanceDetailsObj = this.getSqlModel().getSingleResult(this.getQuery(7), new Object[] {bonusCode});
			if (bonusAllowanceDetailsObj != null && bonusAllowanceDetailsObj.length > 0) {
				bonusBean.setBonusAllowanceID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][0])));
				bonusBean.setFromMonth(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][1])));
				bonusBean.setProcessedFromMonth(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][1])));
				bonusBean.setFromYear(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][2])));
				bonusBean.setToMonth(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][3])));
				bonusBean.setProcessedToMonth(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][3])));
				bonusBean.setToYear(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][4])));
				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][5])).equals(this.yesStr)) {
					bonusBean.setPayInSalaryCheckBox(this.trueStr);
				} else {
					bonusBean.setPayInSalaryCheckBox(this.falseStr);
				}
				bonusBean.setSalaryMonth(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][6])));
				bonusBean.setSalaryYear(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][7])));
				bonusBean.setPayInComponentID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][8])));
				bonusBean.setPayInComponentAbbreviation(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][9])));
				bonusBean.setPayInComponentName(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][10])));

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][11])).equals(this.yesStr)) {
					bonusBean.setDeductIncomeTaxCheckBox(this.trueStr);
				} else {
					bonusBean.setDeductIncomeTaxCheckBox(this.falseStr);
				}

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][12])).equals(this.yesStr)) {
					bonusBean.setSystemCalculatedBonusCheckBox(this.trueStr);
					bonusBean.setViewAlreadyUploadedRatingsDetailsFlag(true);
				} else {
					bonusBean.setSystemCalculatedBonusCheckBox(this.falseStr);
				}

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][13])).equals(this.yesStr)) {
					bonusBean.setManuallyCalculatedBonusCheckBox(this.trueStr);
					bonusBean.setViewAlreadyUploadedBonusFlag(true);
				} else {
					bonusBean.setManuallyCalculatedBonusCheckBox(this.falseStr);
				}

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][14])).equals(this.yesStr)) {
					bonusBean.setCalculatePaidDaysCheckBox(this.trueStr);
				} else {
					bonusBean.setCalculatePaidDaysCheckBox(this.falseStr);
				}

				bonusBean.setCalCulatedBonusComponents(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][15])));

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][16])).equals(this.yesStr)) {
					bonusBean.setSlabwiseMethodCheckbox(this.trueStr);
					this.showSlabWiseDetails(bonusBean, bonusCode);
				} else {
					bonusBean.setSlabwiseMethodCheckbox(this.falseStr);
				}

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][17])).equals(this.yesStr)) {
					bonusBean.setIndividualRatingsMethodCheckbox(this.trueStr);
				} else {
					bonusBean.setIndividualRatingsMethodCheckbox(this.falseStr);
				}

				if (Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][18])).equals(this.yesStr)) {
					bonusBean.setFlatRateMethodCheckbox(this.trueStr);
				} else {
					bonusBean.setFlatRateMethodCheckbox(this.falseStr);
				}

				bonusBean.setFlatRateBonusAllowanceCalculation(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][19])));
				bonusBean.setFlatRateMaximumBonusAllowance(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][20])));
				bonusBean.setFlatRateMinimumBonusAllowance(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][21])));
				bonusBean.setBonusAllowanceStatus(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][22])));
				bonusBean.setDivisionID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][23])));
				bonusBean.setDivisionName(this.getActualDivisionName(String.valueOf(bonusAllowanceDetailsObj[0][23])));
				bonusBean.setBranchID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][24])));
				bonusBean.setBranchName(this.getActualBranchName(String.valueOf(bonusAllowanceDetailsObj[0][24])));
				bonusBean.setPaybillID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][25])));
				bonusBean.setPaybillName(this.getActualPayBillName(String.valueOf(bonusAllowanceDetailsObj[0][25])));
				bonusBean.setDepartmentID(Utility.checkNull(String.valueOf(bonusAllowanceDetailsObj[0][26])));
				bonusBean.setDepartmentName(this.getActualDepartmentName(String.valueOf(bonusAllowanceDetailsObj[0][26])));
				bonusBean.setFilterEmpId(String.valueOf(bonusAllowanceDetailsObj[0][27]));
				bonusBean.setFilterEmpName(String.valueOf(bonusAllowanceDetailsObj[0][28]));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : showSlabWiseDetails. 
	 * Purpose : This method is called from
	 * getBonusAllowanceDetails() only if slabWiseCheckBox is checked
	 * 
	 * @param bonusCode : bonusCode
	 * @param bonusBean : bonusBean
	 */
	private void showSlabWiseDetails(final BonusAllowance bonusBean, final String bonusCode) {
		try {
			final String selectSlabDetailsQuery = "SELECT NVL(HRMS_BONUS_SLAB.BONUS_FROM_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_TO_AMT,0.0), NVL(HRMS_BONUS_SLAB.BOMUS_PERSENTAGE,0.0), NVL(HRMS_BONUS_SLAB.BONUS_MIN_AMT,0.0), NVL(HRMS_BONUS_SLAB.BONUS_MAX_AMT,0.0)" + 
					" FROM HRMS_BONUS_SLAB WHERE HRMS_BONUS_SLAB.BONUS_CODE = " + bonusCode;
			final Object[][] slabDetailsObj = this.getSqlModel().getSingleResult(selectSlabDetailsQuery);
			if (slabDetailsObj != null && slabDetailsObj.length > 0) {
				final List<BonusAllowance> innerSlabList = new ArrayList<BonusAllowance>();
				for (int i = 0; i < slabDetailsObj.length; i++) {
					final BonusAllowance innerBean = new BonusAllowance();
					innerBean.setSlabItrID(String.valueOf(i));
					innerBean.setSlabItrFromAmount(String.valueOf(slabDetailsObj[i][0]));
					innerBean.setSlabItrToAmount(String.valueOf(slabDetailsObj[i][1]));
					innerBean.setSlabItrPercentage(String.valueOf(slabDetailsObj[i][2]));
					innerBean.setSlabItrMin(String.valueOf(slabDetailsObj[i][3]));
					innerBean.setSlabItrMax(String.valueOf(slabDetailsObj[i][4]));
					innerSlabList.add(innerBean);
				}
				bonusBean.setSlabDetailsIterator(innerSlabList);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

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
				divisionName = this.commaSeparatedValues(divisionNameObj); 
				divisionName = divisionName.substring(0, divisionName.length() - 1);
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
			if (branchID == null || this.nullStr.equals(branchID)) {
				branchName = "";
			} else {
				final String branchNameQuery = "SELECT HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_ID IN (" + branchID + ")";
				final Object[][] branchNameObj = this.getSqlModel().getSingleResult(branchNameQuery);
				branchName = this.commaSeparatedValues(branchNameObj); 
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
	 * 
	 * @param deptID : deptID
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
				departmentName = this.commaSeparatedValues(departmentNameObj);  
				departmentName = departmentName.substring(0, departmentName.length() - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return departmentName;
	}

	/**
	 * Method : getActualPayBillName. 
	 * Purpose : this method is used to get payBill names based on supplied pay bill ID
	 * 
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualPayBillName(final String payBillID) {
		String payBillName = "";
		try {
			if (payBillID == null || this.nullStr.equals(payBillID)) {
				payBillName = "";
			} else {
				final String payBillNameQuery = "SELECT HRMS_PAYBILL.PAYBILL_GROUP FROM HRMS_PAYBILL WHERE HRMS_PAYBILL.PAYBILL_ID IN (" + payBillID + ")";
				final Object[][] payBillNameObj = this.getSqlModel().getSingleResult(payBillNameQuery);
				payBillName = this.commaSeparatedValues(payBillNameObj); 
				payBillName = payBillName.substring(0, payBillName.length() - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return payBillName;
	}

	/**
	 * Method : uploadIndividualRatingsData. 
	 * Purpose : this method is used to upload Individual ratings
	 * 
	 * @param response : response
	 * @param request : request
	 * @param bonusBean : bonusBean
	 * @return boolean
	 */
	public boolean uploadIndividualRatingsData(
			final HttpServletResponse response,
			final HttpServletRequest request, final BonusAllowance bonusBean) {
		boolean finalReturnResult = false;
		try {
			bonusBean.setFileNameIndividualRatingsUploaded(bonusBean.getFileNameForIndividualRatings());
			final String filePath = bonusBean.getDataPath() + "" + bonusBean.getFileNameForIndividualRatings();
			this.logger.info("############# FILE PATH #############" + filePath);
			bonusBean.setTempFileNameIndividualRatingsUploaded("IndividualRatingsBonusAllowance");
			MigratePayrollExcelData.getFile(filePath);

			/**
			 * Get column numbers with mandatory information
			 */
			final Map<Integer, Boolean> columnInformation = MigratePayrollExcelData.isColumnsMandatory();

			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') FROM HRMS_EMP_OFFC WHERE 1 = 1 ";

			if (!"".equals(bonusBean.getDivisionID())) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN(" + bonusBean.getDivisionID() + ")";
			}
			if (!"".equals(bonusBean.getBranchID())) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(" + bonusBean.getBranchID() + ")";
			}
			if (!"".equals(bonusBean.getPaybillID())) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + bonusBean.getPaybillID() + ")";
			}
			if (!"".equals(bonusBean.getDepartmentID())) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN(" + bonusBean.getDepartmentID() + ")";
			}
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='S' AND " + 
					" HRMS_EMP_OFFC.EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + 
					bonusBean.getToMonth() + "-" + bonusBean.getToYear() + 
					"','MM-YYYY')) " + " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			final Object[][] employeeMaster = this.getSqlModel().getSingleResult(query);

			Object[][] employeeTokenObj = null;
			Object[][] empNameObj = null;
			if (employeeMaster != null && employeeMaster.length > 0) {
				employeeTokenObj = MigratePayrollExcelData.uploadExcelData(1,
						employeeMaster, MigratePayrollExcelData.MASTER_TYPE,
						columnInformation.get(1));
				empNameObj = MigratePayrollExcelData.uploadExcelData(2, null,
						MigratePayrollExcelData.STRING_TYPE, columnInformation.get(2));
			}

			final Object[][] ratingObj = MigratePayrollExcelData.uploadExcelData(3,
					null, MigratePayrollExcelData.NUMBER_INTEGER_TYPE,
					columnInformation.get(3));
			/* Checks if the values are in proper format */
			final boolean result = MigratePayrollExcelData.isFileToBeUploaded();
			this.logger.info("#### Integrity Check ####" + result);
			if (result) {
				boolean insertUpdateHdrDataFlag = false;
				if ("".equals(bonusBean.getBonusAllowanceID().trim())) {
					final String maxBonusHdrCodeQuery = "SELECT NVL(MAX(HRMS_BONUS_HDR.BONUS_CODE),0)+1 FROM HRMS_BONUS_HDR";
					final Object[][] maxBonusHdrCodeObj = this.getSqlModel().getSingleResult(maxBonusHdrCodeQuery);
					if (maxBonusHdrCodeObj != null && maxBonusHdrCodeObj.length > 0) {
						bonusBean.setBonusAllowanceID(String.valueOf(maxBonusHdrCodeObj[0][0]));
					}
					insertUpdateHdrDataFlag = this.insertIntoBonusHdr(bonusBean);
				} else {
					insertUpdateHdrDataFlag = this.updateBonusHdr(bonusBean);
				}

				if (insertUpdateHdrDataFlag) {
					final String bonusCode = bonusBean.getBonusAllowanceID();
					final Object[][] finalEmpRatingObj = new Object[employeeTokenObj.length][6];
					final Object[][] finalDeleteObj = new Object[employeeTokenObj.length][2];
					boolean insertResult;
					double formulaValue = 0.0;
					double finalBonusAmount = 0.0;
					if (employeeTokenObj != null && employeeTokenObj.length > 0) {
						for (int i = 0; i < employeeTokenObj.length; i++) {
							// First delete those records which are available in
							// HRMS_BONUS_EMP
							finalDeleteObj[i][0] = String.valueOf(employeeTokenObj[i][0]);
							finalDeleteObj[i][1] = bonusCode;

							finalEmpRatingObj[i][0] = bonusCode;
							finalEmpRatingObj[i][1] = String.valueOf(employeeTokenObj[i][0]);
							if (ratingObj != null && ratingObj.length > 0) {
								final String getCreditConfigInfoQuery = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE, NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE HRMS_EMP_CREDIT.EMP_ID = " + String.valueOf(employeeTokenObj[i][0]);
								final Object[][] creditConfigInfoObj = this.getSqlModel().getSingleResult(getCreditConfigInfoQuery);
								try {
									formulaValue = Utility.expressionEvaluate(new Utility().generateFormulaPay(creditConfigInfoObj, bonusBean.getCalCulatedBonusComponents(),
															context, session));
									finalBonusAmount = (formulaValue * Double.parseDouble(String.valueOf(ratingObj[i][0]))) / 100;
								} catch (final Exception e) {
									formulaValue = 0.0;
								}
								finalEmpRatingObj[i][2] = String.valueOf(ratingObj[i][0]);
							} else {
								finalEmpRatingObj[i][2] = this.zeroStr;
							}

							if (this.trueStr.equals(bonusBean.getDeductIncomeTaxCheckBox())) {
								finalEmpRatingObj[i][3] = "0.00";
							} else {
								finalEmpRatingObj[i][3] = "0.00";
							}

							if (this.trueStr.equals(bonusBean.getDeductIncomeTaxCheckBox())) {
								finalEmpRatingObj[i][4] = finalBonusAmount + Double.parseDouble(String.valueOf(finalEmpRatingObj[i][3]));
							} else {
								finalEmpRatingObj[i][4] = String.valueOf(finalBonusAmount);
							}

							finalEmpRatingObj[i][5] = String.valueOf(finalBonusAmount);

						}

						final boolean deleteResult = this.getSqlModel().singleExecute(this.getQuery(4), finalDeleteObj);
						if (deleteResult) {
							insertResult = this.getSqlModel().singleExecute(this.getQuery(8), finalEmpRatingObj);
							if (insertResult) {
								bonusBean.setStatus("Success");
								bonusBean.setNote("Data uploaded successfully, Please verify the same.");
								finalReturnResult = true;
							} else {
								bonusBean.setNote("Error inserting data");
								finalReturnResult = false;
							}
							/*
							 * String updateBonusTotalAmountQuery = "UPDATE
							 * HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_TOTAL_AMT = " +
							 * "(SELECT SUM(EMP_BONUS_AMT) FROM HRMS_BONUS_EMP
							 * WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusCode + " )" + "
							 * WHERE HRMS_BONUS_HDR.BONUS_CODE = " + bonusCode;
							 * getSqlModel().singleExecute(updateBonusTotalAmountQuery);
							 * 
							 * String updateBonusEmployeeCountQuery = "UPDATE
							 * HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_EMP_COUNT = " +
							 * "(SELECT COUNT(*) FROM HRMS_BONUS_EMP WHERE
							 * HRMS_BONUS_EMP.BONUS_CODE = "+ bonusCode + ") "+ "
							 * WHERE HRMS_BONUS_HDR.BONUS_CODE = " +bonusCode;
							 * getSqlModel().singleExecute(updateBonusEmployeeCountQuery);
							 */

							final Object[][] bonusIDObj = new Object[1][3];
							bonusIDObj[0][0] = bonusBean.getBonusAllowanceID();
							bonusIDObj[0][1] = bonusBean.getBonusAllowanceID();
							bonusIDObj[0][2] = bonusBean.getBonusAllowanceID();
							this.getSqlModel().singleExecute(this.getQuery(13), bonusIDObj);
						} else {
							bonusBean.setStatus("Fail");
							bonusBean.setNote("Error inserting data");
							finalReturnResult = false;
						}
					}
				}
			} else {
				bonusBean.setStatus("Fail");
				bonusBean.setNote(this.messageStr);
				finalReturnResult = false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		bonusBean.setDisplayNoteFlag(true);
		return finalReturnResult;
	}

	/**
	 * Method : saveSlabDetails. 
	 * Purpose : This method is used to save slab details
	 * @param bonusBean : bonusBean
	 * @param slabFromAmount : slabFromAmount
	 * @param slabToAmount : slabToAmount
	 * @param slabPercentage : slabPercentage
	 * @param slabMinimumAmount : slabMinimumAmount
	 * @param slabMaximumAmount : slabMaximumAmount
	 * @return boolean           
	 */
	public boolean saveSlabDetails(final BonusAllowance bonusBean,
			final String[] slabFromAmount, final String[] slabToAmount,
			final String[] slabPercentage, final String[] slabMinimumAmount,
			final String[] slabMaximumAmount) {
		boolean result = false;
		try {
			Object[][] insertSlabDataObj = null;
			if (slabFromAmount != null && slabFromAmount.length > 0) {
				insertSlabDataObj = new Object[slabFromAmount.length][6];
				for (int i = 0; i < slabMaximumAmount.length; i++) {
					insertSlabDataObj[i][0] = bonusBean.getBonusAllowanceID();
					insertSlabDataObj[i][1] = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(slabFromAmount[i])))));
					insertSlabDataObj[i][2] = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(slabToAmount[i])))));
					insertSlabDataObj[i][3] = this.checkForNullValue(String.valueOf(slabPercentage[i]));
					insertSlabDataObj[i][4] = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(slabMinimumAmount[i])))));
					insertSlabDataObj[i][5] = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(String.valueOf(slabMaximumAmount[i])))));
				}
				final String deleteSlabDataQuery = "DELETE FROM HRMS_BONUS_SLAB WHERE HRMS_BONUS_SLAB.BONUS_CODE = " + bonusBean.getBonusAllowanceID();
				this.getSqlModel().singleExecute(deleteSlabDataQuery);

				result = this.getSqlModel().singleExecute(this.getQuery(9), insertSlabDataObj);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : getProcessedEmployeeList. Purpose : This method is used to get
	 * all the employee list for which bonus data is processed.
	 * @param bonusBean : bonusBean
	 * @param request : instance of request
	 */
	public void getProcessedEmployeeList(final BonusAllowance bonusBean,
			final HttpServletRequest request) {
		try {
			final Object[][] employeeListObj = this.getSqlModel().getSingleResult(this.getQuery(10),
					new Object[] {bonusBean.getBonusAllowanceID()});

			final String[] pageIndex = Utility.doPaging(bonusBean.getMyPage(),
					employeeListObj.length, 10);
			if (pageIndex == null) {
				pageIndex[0] = this.zeroStr;
				pageIndex[1] = "10";
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}
			request.setAttribute("totalEmpPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageEmpNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)) {
				bonusBean.setMyPage(this.oneStr);
			}

			final List<BonusAllowance> innerList = new ArrayList<BonusAllowance>();
			if (employeeListObj != null && employeeListObj.length > 0) {
				bonusBean.setEmployeeListEditRecordFlag(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final BonusAllowance innerBean = new BonusAllowance();
					innerBean.setEmpIDEditRecordItr(String.valueOf(employeeListObj[i][0]));
					innerBean.setEmpTokenEditRecordItr(String.valueOf(employeeListObj[i][1]));
					innerBean.setEmpNameEditRecordItr(String.valueOf(employeeListObj[i][2]));
					innerBean.setBonusAllowanceAmountEditRecordItr(this.formatter.format(Double.parseDouble(String.valueOf(employeeListObj[i][3]))));
					innerBean.setTaxEditRecordItr(this.formatter.format(Double.parseDouble(String.valueOf(employeeListObj[i][4]))));
					innerBean.setTotalAmountEditRecordItr(this.formatter.format(Double.parseDouble(String.valueOf(employeeListObj[i][5]))));
					innerList.add(innerBean);
				}
				bonusBean.setEmployeeListEditRecordIterator(innerList);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : deleteSelectedEmployeesFromList. Purpose : This method is used
	 * to add selected employee from list
	 * 
	 * @param bonusBean :
	 *            bonusBean
	 * @param selectedEmployees :
	 *            selectedEmployees
	 * @return boolean
	 */
	public boolean deleteSelectedEmployeesFromList(
			final BonusAllowance bonusBean, final String selectedEmployees) {
		boolean result = false;
		try {
			final Object[][] deleteEmpObj = new Object[1][2];
			deleteEmpObj[0][0] = selectedEmployees;
			deleteEmpObj[0][1] = bonusBean.getBonusAllowanceID();

			result = this.getSqlModel().singleExecute("DELETE FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.EMP_ID IN (" + selectedEmployees + ")" + 
													  " AND HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : addSelectedEmployeeToList. 
	 * Purpose : This method is used to add
	 * selected employee to the list
	 * @param bonusBean : instance of BonusAllowance
	 * @return boolean
	 */
	public boolean addSelectedEmployeeToList(final BonusAllowance bonusBean) {
		boolean addEmpResult = false;
		try {
			final boolean deleteEmpFirstResult = this.deleteSelectedEmployeesFromList(bonusBean, bonusBean.getEmployeeIDEditRecord());
			if (deleteEmpFirstResult) {
				final double bonusAmount = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(bonusBean.getBonusAllowanceAmountEditRecord()))));
				final double tdsAmount = Double.parseDouble(this.formatter.format(Double.parseDouble(this.checkForNullValue(bonusBean.getTdsEditRecord()))));
				final Object[][] addEmpObj = new Object[1][5];
				addEmpObj[0][0] = bonusBean.getBonusAllowanceID();
				addEmpObj[0][1] = bonusBean.getEmployeeIDEditRecord();
				addEmpObj[0][2] = String.valueOf(bonusAmount);
				addEmpObj[0][3] = String.valueOf(tdsAmount);
				if ("".equals(bonusBean.getTdsEditRecord().trim())) {
					addEmpObj[0][4] = String.valueOf(bonusAmount);
				} else {
					final double bonusEditAmount = Double.parseDouble(bonusBean.getBonusAllowanceAmountEditRecord()) - Double.parseDouble(bonusBean.getTdsEditRecord());
					addEmpObj[0][4] = String.valueOf(bonusEditAmount);
				}

				addEmpResult = this.getSqlModel().singleExecute(this.getQuery(12), addEmpObj);
				if (addEmpResult) {
					final String updateTotalBonusAmountQuery = "UPDATE HRMS_BONUS_HDR SET HRMS_BONUS_HDR.BONUS_TOTAL_AMT = (SELECT SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL) " + 
																" FROM HRMS_BONUS_EMP WHERE HRMS_BONUS_EMP.BONUS_CODE = " + bonusBean.getBonusAllowanceID() + ") " + 
																" WHERE HRMS_BONUS_HDR.BONUS_CODE = " + bonusBean.getBonusAllowanceID();
					this.getSqlModel().singleExecute(updateTotalBonusAmountQuery);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return addEmpResult;
	}

	/**
	 * Method : validateExistingSlabPeriod. Purpose : This method is used to
	 * check whether selected Period is already available OR not.
	 * 
	 * @param bonusBean :
	 *            bonusBean
	 * @return boolean
	 */
	public boolean validateExistingSlabPeriod(final BonusAllowance bonusBean) {
		boolean result = false;
		try {
			String bonusCode = "";
			bonusCode = bonusBean.getBonusAllowanceID();
			if (!"".equals(bonusCode)) {
				result = true;
			} else {
				String selectPeriodQuery = "SELECT HRMS_BONUS_HDR.BONUS_FROM_MONTH, HRMS_BONUS_HDR.BONUS_FROM_YEAR, HRMS_BONUS_HDR.BONUS_TO_MONTH, HRMS_BONUS_HDR.BONUS_TO_YEAR FROM HRMS_BONUS_HDR " + 
						" WHERE HRMS_BONUS_HDR.DIV_CODE = " + bonusBean.getDivisionID();

				if (!"".equals(bonusBean.getDivisionID())) {
					selectPeriodQuery += " AND HRMS_BONUS_HDR.DIV_CODE =" + bonusBean.getDivisionID();
				}
				if (!"".equals(bonusBean.getBranchID())) {
					selectPeriodQuery += " AND HRMS_BONUS_HDR.BRANCH_CODE IN(" + bonusBean.getBranchID() + ")";
				}
				if (!"".equals(bonusBean.getPaybillID())) {
					selectPeriodQuery += " AND HRMS_BONUS_HDR.PAYBILL_CODE IN(" + bonusBean.getPaybillID() + ")";
				}
				if (!"".equals(bonusBean.getDepartmentID())) {
					selectPeriodQuery += " AND HRMS_BONUS_HDR.DEPT_CODE IN(" + bonusBean.getDepartmentID() + ")";
				}
				if (!"".equals(bonusBean.getFilterEmpId())) {
					selectPeriodQuery += " AND HRMS_BONUS_HDR.EMP_CODE IN(" + bonusBean.getFilterEmpId() + ")";
				}

				final Object[][] selectPeriodObj = this.getSqlModel().getSingleResult(selectPeriodQuery);
				result = this.checkSlabs(bonusBean, selectPeriodObj);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : checkSlabs 
	 * Purpose : This method is used to restrict duplicate period.
	 * @param bonusBean : BonusAllowance
	 * @param selectPeriodObj : This contains existing period.
	 * @return boolean
	 */
	public boolean checkSlabs(final BonusAllowance bonusBean,
			final Object[][] selectPeriodObj) {
		boolean finalResult = false;
		try {
			// Begins : Following code is used to append zero if month is less
			// then october i.e. 10
			// e.g. if entered month is April i.e. 4 then below code returns it
			// as "04" instead of "4"
			final String appendedFromMonth = bonusBean.getFromMonth().length() == 1 ? this.zeroStr + 
					 bonusBean.getFromMonth() : bonusBean.getFromMonth();
			final String appendedToMonth = bonusBean.getToMonth().length() == 1 ? this.zeroStr + 
					 bonusBean.getToMonth()	: bonusBean.getToMonth();
			// End
			final String enteredFromPeriod = (bonusBean.getFromYear()) + "" + appendedFromMonth;
			final int finalEnteredFromPeriod = Integer.parseInt(enteredFromPeriod);

			final String enteredToPeriod = (bonusBean.getToYear()) + "" + appendedToMonth;
			final int finalEnteredToPeriod = Integer.parseInt(enteredToPeriod);

			boolean isValid = true;
			int count = 0;
			for (int i = 0; i < selectPeriodObj.length; i++) {

				final String systemFromPeriod = (String.valueOf(selectPeriodObj[i][1])) + "" +
						 (String.valueOf(selectPeriodObj[i][0]).length() == 1 ? this.zeroStr + 
								 String.valueOf(selectPeriodObj[i][0]) : String.valueOf(selectPeriodObj[i][0]));
				final int finalSystemFromPeriod = Integer.parseInt(systemFromPeriod);

				final String systemToPeriod = (String.valueOf(selectPeriodObj[i][3])) + "" +
						 (String.valueOf(selectPeriodObj[i][2]).length() == 1 ? this.zeroStr + String.valueOf(selectPeriodObj[i][2]) : 
								String.valueOf(selectPeriodObj[i][2]));
				final int finalSystemToPeriod = Integer.parseInt(systemToPeriod);

				if ((finalEnteredFromPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredFromPeriod >= finalSystemToPeriod) && 
						 (finalEnteredToPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredToPeriod <= finalSystemToPeriod)) {
					isValid = false;
					System.out.println("CASE 1");
				}

				if ((finalEnteredFromPeriod <= finalSystemFromPeriod) && 
						 (finalEnteredFromPeriod <= finalSystemToPeriod) && 
						 (finalEnteredToPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredToPeriod <= finalSystemToPeriod)) {
					isValid = false;
					System.out.println("CASE 2");
				}

				if ((finalEnteredFromPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredFromPeriod <= finalSystemToPeriod) && 
						 (finalEnteredToPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredToPeriod >= finalSystemToPeriod)) {
					isValid = false;
					System.out.println("CASE 3");
				}

				if ((finalEnteredFromPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredFromPeriod <= finalSystemToPeriod) && 
						 (finalEnteredToPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredToPeriod <= finalSystemToPeriod)) {
					isValid = false;
					System.out.println("CASE 4");
				}

				if ((finalEnteredFromPeriod <= finalSystemFromPeriod) && 
						 (finalEnteredFromPeriod <= finalSystemToPeriod) && 
						 (finalEnteredToPeriod >= finalSystemFromPeriod) && 
						 (finalEnteredToPeriod >= finalSystemToPeriod)) {
					isValid = false;
					System.out.println("CASE 5");
				}

				count++;
			}
			System.out.println("IS VALID = " + isValid + " FOR " + count + " ITERATIONS");
			if (isValid) {
				finalResult = true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return finalResult;

	}

	/**Method : checkForNullValue.
	 * Purpose : This method is used to check for null decimal values.
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with 0.
	 * @param result :- Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkForNullValue(final String result) {
		if (result == null || this.nullStr.equals(result)) {
			return this.zeroStr;
		} else {
			return result;
		}
	}
	
	/**Method : commaSeparatedValues.
	 * Purpose : This method is used to return comma separated values.
	 * @param initialData :- Input object data
	 * @return : - comma separated values
	 */
	public String commaSeparatedValues(final Object[][] initialData) {
		String result = "";
		try {
			if (initialData != null && initialData.length > 0) {
				for (int i = 0; i < initialData.length; i++) {
					result += String.valueOf(initialData[i][0]) + ",";
				}
			}
		} catch (final Exception e) {
			this.logger.error("Error occured in commaSeparatedValues >>");
		}
		return result;
	}
}
