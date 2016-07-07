package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.AnnualEarningRep;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @author Prakash Shetkar
 * Date : 01-08-2009
 * Modified by Prashant on 30 Apr 2012
 */
public class AnnualEarningModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AnnualEarningModel.class);

	 /** This method is used to set up the library variables for generating the report.
	 * @param bean - AnnualEarningRep 
	 * @param request - request
	 * @param response - response
	 * @param reportPath - reportPath
	 */
	public final void generateReport(final AnnualEarningRep bean, final HttpServletRequest request, final HttpServletResponse response, final String reportPath) {
		try {
			String fromYr = bean.getFromYear();
			String toYr = bean.getToYear();

			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "Annual earnings report";
			rds.setFileName(fileName);
			rds.setReportName("Annual earnings for financial year " + fromYr + " - " + toYr);
			if (type.equals("Xls")) {
				rds.setTotalColumns(9);
			} else {
				rds.setPageOrientation("landscape");
				rds.setPageSize("A4");
			}
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());

			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "/payroll/AnnualEarning_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This method sets the actual data to be displayed in the report.
	 * @param rg - report generator instance
	 * @param bean - AnnualEarningRep
	 * @return rg
	 */
	public final org.paradyne.lib.ireportV2.ReportGenerator getReport(final org.paradyne.lib.ireportV2.ReportGenerator  rg, final AnnualEarningRep bean) {
		try {
			Date date = new Date();
			/* Setting filter details */
			String filters = fetchFilters(bean);

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] {{filters}});
			filterData.setCellAlignment(new int[] {0});
			filterData.setCellWidth(new int[] {100});
			filterData.setBlankRowsBelow(1);
			filterData.setCellColSpan(new int[]{9});
			rg.addTableToDoc(filterData);

			/*Credit head query*/
			int creditHeadLength = 0;

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
				+ " WHERE HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG = 'Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			Object[][] creditsHeadObj = getSqlModel().getSingleResult(creditsQuery);
			/*Setting no of credits length*/
			if (creditsHeadObj != null && creditsHeadObj.length > 0) {
				creditHeadLength = creditsHeadObj.length;
			}

			/*Setting header with credits & other employee details*/

			String []header = new String[3 + creditHeadLength + 5];
			int[] alignment = new int[header.length];
			int[] cellwidth = new int[header.length];

			header[0] = "Sr.No";
			header[1] = "Employee Id";
			header[2] = "Employee Name";

			alignment[0] = 0;
			alignment[1] = 0;
			alignment[2] = 0;

			cellwidth[0] = 10;
			cellwidth[1] = 20;
			cellwidth[2] = 20;

			int creditsCount = 3;
			if (creditsHeadObj != null && creditsHeadObj.length > 0) {
				for (int i = 0; i < header.length - 8; i++) {
					header[creditsCount] = String.valueOf(creditsHeadObj[i][0]);
					alignment[creditsCount] = 2;
					cellwidth[creditsCount] = 20;
					creditsCount++;
				}
			}

			header[creditsCount] = "Total Credit";
			header[creditsCount + 1] = "Pf";
			header[creditsCount + 2] = "PTax";
			header[creditsCount + 3] = "TDS + EDU CESS";
			header[creditsCount + 4] = "Total Debit";

			alignment[creditsCount] = 2;
			alignment[creditsCount + 1] = 2;
			alignment[creditsCount + 2] = 2;
			alignment[creditsCount + 3] = 2;
			alignment[creditsCount + 4] = 2;

			cellwidth[creditsCount] = 20;
			cellwidth[creditsCount + 1] = 20;
			cellwidth[creditsCount + 2] = 20;
			cellwidth[creditsCount + 3] = 20;
			cellwidth[creditsCount + 4] = 20;

			/*Setting the report data*/
			String totalEmpQuery = "SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, HRMS_TDS_SALARY.TDS_EMP_ID" 
				+ " FROM HRMS_TDS_SALARY "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_TDS_SALARY.TDS_EMP_ID)"
				+ " WHERE HRMS_TDS_SALARY.TDS_YEAR_FROM = " + bean.getFromYear() + " AND HRMS_TDS_SALARY.TDS_YEAR_TO = " + bean.getToYear()
				+ " AND HRMS_EMP_OFFC.EMP_DIV = " + bean.getDivId();
			if (!bean.getBrnId().equals("")) {
				totalEmpQuery += "AND EMP_CENTER = " + bean.getBrnId();
			}

			if (!bean.getDeptId().equals("")) {
				totalEmpQuery += "AND EMP_DEPT = " + bean.getDeptId();
				}
			if (!bean.getTypeId().equals("")) {
				totalEmpQuery += "AND EMP_TYPE = " + bean.getTypeId();
			}

			if (!bean.getDesgId().equals("")) {
				totalEmpQuery += "AND EMP_RANK = " + bean.getDesgId();
			}

			Object[][] totalEmpObj =  getSqlModel().getSingleResult(totalEmpQuery);

			String empCreditQuery = "SELECT HRMS_TDS_SALARY.TDS_EMP_ID||'#'||HRMS_TDS_SALARY.TDS_CREDIT_HEAD AS KEY, HRMS_TDS_SALARY.TDS_CREDIT_AMOUNT"
				+ " FROM HRMS_TDS_SALARY WHERE HRMS_TDS_SALARY.TDS_YEAR_FROM = " + bean.getFromYear() + " AND HRMS_TDS_SALARY.TDS_YEAR_TO = " + bean.getToYear();

			HashMap empCreditsMap = getSqlModel().getSingleResultMap(empCreditQuery, 0, 2);

			/*Preparing the final data object*/
			Object [][] finalDataObj = new Object[totalEmpObj.length][header.length];

			if(totalEmpObj != null && totalEmpObj.length > 0){

				String pfCodeQuery = " SELECT TDS_EMPPF_INVCODE FROM HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = " + bean.getFromYear() + " AND TDS_FINANCIALYEAR_TO = " + bean.getToYear(); 
				Object [][] pfCode = getSqlModel().getSingleResult(pfCodeQuery);

				HashMap pfAmtMap = null;

				if (pfCode != null && pfCode.length > 0) {
					String pfAmtQuery = " SELECT EMP_ID, NVL(INV_VALID_AMOUNT, 0) FROM HRMS_EMP_INVESTMENT WHERE INV_CODE  = " + String.valueOf(pfCode[0][0]) + " AND INV_FINYEAR_FROM = " + bean.getFromYear() + " AND INV_FINYEAR_TO = " + bean.getToYear();
					pfAmtMap = getSqlModel().getSingleResultMap(pfAmtQuery, 0, 2);
				}

				/* P Tax, TDS+EDU Cess Query */
				String ptaxTdsEduCessQuery = " SELECT TDS_EMP_ID, NVL(TDS_PROF_TAX,0), NVL(TDS_TOTAL_TAX,0) + NVL(TDS_EDUC_CESS,0) FROM HRMS_TDS "
					+ " WHERE TDS_FROM_YEAR = " + bean.getFromYear() + " AND TDS_TO_YEAR = " + bean.getToYear();

				HashMap ptaxTdsEduCessMap = getSqlModel().getSingleResultMap(ptaxTdsEduCessQuery, 0, 2);

				for (int i = 0; i < totalEmpObj.length; i++) {
					finalDataObj[i][0] = i + 1; //sr no
					finalDataObj[i][1] = String.valueOf(totalEmpObj[i][0]); // emp token
					finalDataObj[i][2] = String.valueOf(totalEmpObj[i][1]); // emp name

					int creditCount = 3;
					double totalCredits = 0.00;
					for (int j = 0; j < creditHeadLength; j++) {
						String creditAmount = "0.00";
						Object [][] creditAmt = (Object[][]) empCreditsMap.get(String.valueOf(totalEmpObj[i][2]) + "#" + String.valueOf(creditsHeadObj[j][1]));

						if (creditAmt != null && creditAmt.length > 0) {
							creditAmount = formatter.format(Double.parseDouble(String.valueOf(creditAmt[0][1])));
						}

						finalDataObj[i][creditCount] = creditAmount; //Credit head amount
						creditCount++;
						totalCredits += Double.parseDouble(creditAmount);
					} //end of for

					finalDataObj[i][creditCount] = formatter.format(totalCredits); // Total Credits

					/*Setting PF Amount for employee*/
					String pfAmount = "0.00";
					Object [][] pfAmountObj = (Object[][]) pfAmtMap.get(String.valueOf(totalEmpObj[i][2]));

					if (pfAmountObj != null && pfAmountObj.length > 0) {
						pfAmount = formatter.format(Double.parseDouble(String.valueOf(pfAmountObj[0][1])));
					}
					finalDataObj[i][++creditCount] = pfAmount; // PF Amount

					/*Setting TDS+EDU Cess for employee*/
					String ptaxAmount = "0.00";
					String tdsEduCessAmount = "0.00";
					Object [][] ptaxTdsEduCessObj = (Object[][]) ptaxTdsEduCessMap.get(String.valueOf(totalEmpObj[i][2]));
					if (ptaxTdsEduCessObj != null && ptaxTdsEduCessObj.length > 0) {
						ptaxAmount = String.valueOf(ptaxTdsEduCessObj[0][1]);
						tdsEduCessAmount = String.valueOf(ptaxTdsEduCessObj[0][2]);
					}
					finalDataObj[i][++creditCount] = formatter.format(Double.parseDouble(ptaxAmount)); // P Tax Amount
					finalDataObj[i][++creditCount] = formatter.format(Double.parseDouble(tdsEduCessAmount)); // TDS+EDU Cess

					/*Setting Total Debits for employee*/
					String totalDebitsAmt = formatter.format(Double.parseDouble(pfAmount) + Double.parseDouble(ptaxAmount) + Double.parseDouble(tdsEduCessAmount));

					finalDataObj[i][++creditCount] = totalDebitsAmt; // Total Debits
				}
				TableDataSet tableData1 = new TableDataSet();
				tableData1.setHeader(header);
				tableData1.setHeaderTable(true);
				tableData1.setData(finalDataObj);
				tableData1.setCellAlignment(alignment);
				tableData1.setCellWidth(cellwidth);
				tableData1.setBorderDetail(3);
				tableData1.setHeaderBorderDetail(3);
				rg.addTableToDoc(tableData1);
			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] {{"Employee earnings not available for the selected filter."}});
				noDataTable.setCellAlignment(new int[] {1});
				noDataTable.setCellWidth(new int[] {100});
				noDataTable.setBorderDetail(0);
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**This function returns the selected form filters as string.
	 * @param bean - AnnualEarningRep
	 * @return filter
	 */
	private String fetchFilters(final AnnualEarningRep bean) {
		String fromYr = bean.getFromYear();
		String toYr = bean.getToYear();

		String filters = "Report Period : " + fromYr + " - " + toYr;
		if (!bean.getDivId().equals("")) {
			filters += "\n\nDivision : " + bean.getDivName();
		}
		if (!bean.getBrnId().equals("")) {
			filters += "\n\nBranch : " + bean.getBrnName();
		}
		if (!bean.getDeptId().equals("")) {
			filters += "\n\nDepartment : " + bean.getDeptName();
		}
		if (!bean.getTypeId().equals("")) {
			filters += "\n\nEmployee Type : " + bean.getTypeName();
		}
		if (!bean.getDesgId().equals("")) {
			filters += "\n\nDesignation : " + bean.getDesgName();
		}
		return filters;
	}
}