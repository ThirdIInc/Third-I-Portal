package org.paradyne.model.payroll.incometax;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.EmployeeWisePerqsReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;


public class EmployeeWisePerqsReportModel extends ModelBase {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeWisePerqsReportModel.class);
	
	private NumberFormat formatter = new DecimalFormat("#0.00");
	
	 /** This method is used to set up the library variables for generating the report.
	 * @param bean - AnnualEarningRep 
	 * @param request - request
	 * @param response - response
	 * @param reportPath - reportPath
	 */
	public final void generateReport(final EmployeeWisePerqsReport bean, final HttpServletRequest request, final HttpServletResponse response, final String reportPath) {
		try {
			String fromYr = bean.getFromYear();
			String toYr = bean.getToYear();

			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "EmployeeWise Perquisite Report" + Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("EmployeeWise Perquisite for financial year " + fromYr + " - " + toYr);
			
			rds.setPageOrientation("landscape");
		///	rds.setPageSize("A4");
			rds.setPageSize("TABLOID");
			rds.setShowPageNo(true);
			rds.setTotalColumns(10);
			rds.setUserEmpId(bean.getUserEmpId());

			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "/incometax/EmployeeWisePerqsReport_input.action");
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
	
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT.
	 * 
	 * @param bean
	 * @param response
	 */
	public final org.paradyne.lib.ireportV2.ReportGenerator getReport(final org.paradyne.lib.ireportV2.ReportGenerator  rg, final EmployeeWisePerqsReport bean) {
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
		
			String taxFlag = bean.getTaxableFlag();
			String creditsQuery = "";
			if(taxFlag.equals("Y")){
				
				creditsQuery = "SELECT DISTINCT NVL(HRMS_PERQUISITE_HEAD.PERQ_ABBR,' '), " +
					" HRMS_PERQUISITE_HEAD.PERQ_CODE FROM HRMS_PERQUISITE_HEAD  " +
					" WHERE HRMS_PERQUISITE_HEAD.PERQ_TAXABLE_FLAG IN('Y') " +
					" ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ";
			} else if(taxFlag.equals("N")){
				
				creditsQuery = "SELECT DISTINCT NVL(HRMS_PERQUISITE_HEAD.PERQ_ABBR,' '), " +
					" HRMS_PERQUISITE_HEAD.PERQ_CODE FROM HRMS_PERQUISITE_HEAD  " +
					" WHERE HRMS_PERQUISITE_HEAD.PERQ_TAXABLE_FLAG IN('N') " +
					" ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ";
			}else{
				
				creditsQuery = "SELECT DISTINCT NVL(HRMS_PERQUISITE_HEAD.PERQ_ABBR,' '), " +
					" HRMS_PERQUISITE_HEAD.PERQ_CODE FROM HRMS_PERQUISITE_HEAD  " +
					" WHERE HRMS_PERQUISITE_HEAD.PERQ_TAXABLE_FLAG IN('Y','N') " +
					" ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ";
			}
			 

			Object[][] creditsHeadObj = getSqlModel().getSingleResult(creditsQuery);
			/*Setting no of credits length*/
			if (creditsHeadObj != null && creditsHeadObj.length > 0) {
				creditHeadLength = creditsHeadObj.length;
			}

			/*Setting header with credits & other employee details*/

			String []header = new String[3 + creditHeadLength + 1];
			int[] alignment = new int[header.length];
			int[] cellwidth = new int[header.length];

			header[0] = "S.No";
			header[1] = "Employee Id";
			header[2] = "Employee Name";

			alignment[0] = 0;
			alignment[1] = 0;
			alignment[2] = 0;

			cellwidth[0] = 10;
			cellwidth[1] = 20;
			cellwidth[2] = 30;

			int creditsCount = 3;
			if (creditsHeadObj != null && creditsHeadObj.length > 0) {
				for (int i = 0; i < header.length - 4; i++) {
					header[creditsCount] = String.valueOf(creditsHeadObj[i][0]);
					alignment[creditsCount] = 2;
					cellwidth[creditsCount] = 25;
					creditsCount++;
				}
			}

			header[creditsCount] = "Total Amount";
			
			alignment[creditsCount] = 2;
			cellwidth[creditsCount] = 20;
			

			/*Setting the report data*/
			String totalEmpQuery = "SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, HRMS_EMP_PERQUISITE.EMP_ID " 
				+ " FROM HRMS_EMP_PERQUISITE "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERQUISITE.EMP_ID)"
				+ " WHERE HRMS_EMP_PERQUISITE.FROM_YEAR = " + bean.getFromYear() + " AND HRMS_EMP_PERQUISITE.TO_YEAR = " + bean.getToYear()
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

			

			Object[][] totalEmpObj =  getSqlModel().getSingleResult(totalEmpQuery);

			String empCreditQuery = "SELECT HRMS_EMP_PERQUISITE.EMP_ID||'#'||HRMS_EMP_PERQUISITE.PERQ_CODE AS KEY, HRMS_EMP_PERQUISITE.PERQ_AMT "
				+ " FROM HRMS_EMP_PERQUISITE WHERE HRMS_EMP_PERQUISITE.FROM_YEAR = " + bean.getFromYear() + " AND HRMS_EMP_PERQUISITE.TO_YEAR = " + bean.getToYear();

			HashMap empCreditsMap = getSqlModel().getSingleResultMap(empCreditQuery, 0, 2);

			/*Preparing the final data object*/
			Object [][] finalDataObj = new Object[totalEmpObj.length][header.length];

			if(totalEmpObj != null && totalEmpObj.length > 0){

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
				noDataTable.setData(new Object[][] {{"EmployeeWise Perquisite Report not available for the selected filter."}});
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
	private String fetchFilters(final EmployeeWisePerqsReport bean) {
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
		if(bean.getTaxableFlag().equals("Y")){
			filters+= "\n\nTaxable Flag: Yes";
		} else if(bean.getTaxableFlag().equals("N")){
			filters+= "\n\nTaxable Flag: No";
		}else {
			filters+= "\n\nTaxable Flag: All";
		}
		
		return filters;
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
}// end of class
