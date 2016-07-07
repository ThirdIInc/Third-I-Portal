/**
 * 
 */
package org.paradyne.model.loan;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanMISApplicationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.common.ReportingModel;

import com.itextpdf.text.BaseColor;
import com.lowagie.text.Font;

/**
 * @author ganesh
 * @date 17-01-2011
 * 
 */
public class LoanMISApplicationReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanMISApplicationReportModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/* method name : checkNull 
	 * purpose     : to check whether the value of the string parameter is null or not.
	 * 					if yes then it will set the value of that string to blank
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if 
		else {
			return result;
		}	//end of else
	}
	
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public void getReport(LoanMISApplicationReport bean,
			HttpServletResponse response, HttpServletRequest request,
			 String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "Loan MIS Report "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Loan MIS Report ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setShowPageNo(true);
		rds.setTotalColumns(10);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", request.getContextPath()+"/loan/LoanMISApplicationReport_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, LoanMISApplicationReport bean) {
		try {
			final String fromDate = bean.getFromDate(); // From Date
			final String toDate = bean.getToDate(); // To Date
			
			/* Setting filter details */
			String filters = "Application Date: "+fromDate+ " to " + toDate;
			if (!bean.getDivName().equals("")) {
				filters += "\n\nDivision: " + bean.getDivName();
			}
			if (!bean.getBranchName().equals("")) {
				filters += "\n\nBranch: " + bean.getBranchName();
			}
			if (!bean.getDeptName().equals("")) {
				filters += "\n\nDepartment: " + bean.getDeptName();
			}
			if (!bean.getPaybillName().equals("")) {
				filters += "\n\nPaybill: " + bean.getPaybillName();
			}
			if(!bean.getCostCenterName().equals("")){
				filters+="\n\nCost Center: "+bean.getCostCenterName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{16});
			filterData.setCellNoWrap(new boolean[]{false});
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);
			
			Object[][] summaryData = new Object[1][1];
			summaryData[0][0] = "Loan Applications Details  " ;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 0 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(summaryData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
			tableheadingDateData.setBodyFontStyle(1);  
			tableheadingDateData.setCellColSpan(new int[]{16});
			tableheadingDateData.setBorderDetail(0);
			 tableheadingDateData.setBlankRowsAbove(1);
			rg.addTableToDoc(tableheadingDateData);

			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
			}
			 if(!bean.getDivName().equals("")) { 
				 whereClause +=" AND  HRMS_EMP_OFFC.EMP_DIV IN ("+bean.getDivId()+") ";
			  }
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptId()+") ";
			}
			if (!bean.getEmpName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_ID IN (" + bean.getEmpCode()+") ";
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillId()+") ";
			}
			if (!bean.getCostCenterName().equals("")) {
				whereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
			}
			
			String pendingQuery=" SELECT LOAN_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, " +
					" TO_CHAR(LOAN_APPL_DATE, 'DD-MM-YYYY'), TO_CHAR(NVL(LOAN_AMOUNT,0),9999999999990.99) ," +
					"LOAN_APPL_CODE "
					+"FROM HRMS_LOAN_APPLICATION  "
					+"INNER JOIN HRMS_EMP_OFFC ON (LOAN_EMP_ID = HRMS_EMP_OFFC.EMP_ID)  "
					+"LEFT JOIN HRMS_LOAN_PROCESS  ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) " 
					+"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+" LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID = HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"WHERE LOAN_APPL_STATUS IN('F') " +
					"AND LOAN_APPL_DATE >=TO_DATE('"+fromDate+"','DD-MM-YYYY') " +
					"AND LOAN_APPL_DATE <=TO_DATE('"+toDate+"','DD-MM-YYYY') "
					+" ";
			
			pendingQuery += whereClause;
			pendingQuery += "ORDER BY LOAN_APPL_DATE DESC";
			
			Object[][] loanApplPendingDetail = null;
			String processedQuery=" SELECT LOAN_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, " +
					" TO_CHAR(LOAN_APPL_DATE, 'DD-MM-YYYY'), TO_CHAR(NVL(LOAN_AMOUNT,0),9999999999990.99) ," +
					"LOAN_APPL_CODE "
					+"FROM HRMS_LOAN_APPLICATION  "
					+"INNER JOIN HRMS_EMP_OFFC ON (LOAN_EMP_ID = HRMS_EMP_OFFC.EMP_ID)  "
					+"LEFT JOIN HRMS_LOAN_PROCESS  ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) " 
					+"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+" LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID = HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"WHERE LOAN_APPL_STATUS IN('A') " +
					"AND LOAN_APPL_DATE >=TO_DATE('"+fromDate+"','DD-MM-YYYY') " +
					"AND LOAN_APPL_DATE <=TO_DATE('"+toDate+"','DD-MM-YYYY') "
					+"";
			
			processedQuery += whereClause;
			processedQuery += " ORDER BY LOAN_APPL_DATE DESC ";
			
			Object[][] loanApplProcessedDetail = null;
			//status:All
			if(bean.getHiddenChechfrmId().equals("A")){
				loanApplPendingDetail = getSqlModel().getSingleResult(
						pendingQuery);
				loanApplProcessedDetail = getSqlModel().getSingleResult(
						processedQuery);
			}
			//status:Processed
			if(bean.getHiddenChechfrmId().equals("P")){
				loanApplProcessedDetail = getSqlModel().getSingleResult(
						processedQuery);
						}
			//status:Pending
			if(bean.getHiddenChechfrmId().equals("F")){
				loanApplPendingDetail = getSqlModel().getSingleResult(
						pendingQuery);
			}
			
			String[] header = null;
			header = new String[5];

				header[0] = "Sn.";
				header[1] = "Employee Id";
				header[2] = "Employee Name";
				header[3] = "Application Date";
				header[4] = "Loan Amount";
			
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 8;
					bcellwrap[i]=true;
				} else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 30;
					bcellwrap[i]=true;
				} else if (i == 2) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=true;
				} else if (i == 3) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=true;
				}
				else  {
					bcellAlign[i] = 2;
					bcellWidth[i] = 40;
					bcellwrap[i]=true;
				}
			}
			
			Object[][] objTotalTabularData =  null;
			Object[][] objTotalProcessedTabularData =  null;
			
			//status:All
			if(bean.getHiddenChechfrmId().equals("A")){
				objTotalTabularData =  new Object[loanApplPendingDetail.length][header.length];
				objTotalProcessedTabularData =  new Object[loanApplProcessedDetail.length][header.length];
			}
			//status:Processed
			if(bean.getHiddenChechfrmId().equals("P")){
				objTotalProcessedTabularData =  new Object[loanApplProcessedDetail.length][header.length];
						}
			//status:Pending
			if(bean.getHiddenChechfrmId().equals("F")){
				objTotalTabularData =  new Object[loanApplPendingDetail.length][header.length];
			}
			
			// for processed details start		
			int count = 1;
			Object[][] processedHeadData = new Object[1][1];
			processedHeadData[0][0] = "Status: Processed" ;
			TableDataSet processedHeadDtlData = new TableDataSet();
			processedHeadDtlData.setData(processedHeadData);
			processedHeadDtlData.setCellWidth(cellWidthDateHeader);
			processedHeadDtlData.setCellAlignment(cellAlignDateHeader);  
			processedHeadDtlData.setBodyFontStyle(1);  
			processedHeadDtlData.setBorderDetail(0);
			processedHeadDtlData.setBlankRowsAbove(1);
			
			if((bean.getHiddenChechfrmId().equals("A")) || (bean.getHiddenChechfrmId().equals("P"))){
				rg.addTableToDoc(processedHeadDtlData);
				}
			
			if (loanApplProcessedDetail != null && loanApplProcessedDetail.length > 0) {
				int MAINCOUNT = 0;
				
				for (int i = 0; i < loanApplProcessedDetail.length; i++) {
					objTotalProcessedTabularData[MAINCOUNT][0] = count++;
					objTotalProcessedTabularData[MAINCOUNT][1] = String.valueOf(loanApplProcessedDetail[i][1]);
					objTotalProcessedTabularData[MAINCOUNT][2] = String.valueOf(loanApplProcessedDetail[i][2]);

					objTotalProcessedTabularData[MAINCOUNT][3] = String.valueOf(loanApplProcessedDetail[i][3]);
					objTotalProcessedTabularData[MAINCOUNT][4] = String.valueOf(loanApplProcessedDetail[i][4]);
				MAINCOUNT++;
				}
				TableDataSet loanTypeInfoTable = new TableDataSet();
				loanTypeInfoTable.setHeader(header);
				loanTypeInfoTable.setHeaderBorderDetail(3);
				loanTypeInfoTable.setData(objTotalProcessedTabularData);
				loanTypeInfoTable.setBorderDetail(3);
				loanTypeInfoTable.setHeaderTable(true);
				loanTypeInfoTable.setCellWidth(bcellWidth);
				loanTypeInfoTable.setCellAlignment(bcellAlign);
				loanTypeInfoTable.setBorder(true);
				rg.addTableToDoc(loanTypeInfoTable);
				
				double Rentamount = 0.0;
				try {
					for (int i = 0; i < loanApplProcessedDetail.length; i++) {
						loanApplProcessedDetail[i][0] = String.valueOf(i + 1);
						Rentamount += Double.parseDouble(String
								.valueOf(loanApplProcessedDetail[i][4]));
					} // end of loop
				} catch (Exception e) {
					logger.error("exception in houseRentDtl loop", e);
				} // end of catch

			
			TableDataSet reportObjData = new TableDataSet();
			Object [][] paramTotalRec=new Object[1][1];
			paramTotalRec[0][0]="Total Amount : " + formatter.format(Double.parseDouble(checkNull(String.valueOf(Rentamount))));
			reportObjData.setCellAlignment(new int[] {2});
			reportObjData.setCellWidth(new int[] {100});
			reportObjData.setCellColSpan(new int[] {5} );
			reportObjData.setData(paramTotalRec);
			reportObjData.setBorderDetail(0);
			rg.addTableToDoc(reportObjData);
			
			TableDataSet totalnoEmpObjData = new TableDataSet();
			Object [][] totalnoEmpTotalRec=new Object[1][1];
			totalnoEmpTotalRec[0][0]="Total No of Employees:   "+loanApplProcessedDetail.length;
			totalnoEmpObjData.setCellAlignment(new int[] {0});
			totalnoEmpObjData.setCellWidth(new int[] {100});
			totalnoEmpObjData.setData(totalnoEmpTotalRec);
			totalnoEmpObjData.setBodyBGColor(new BaseColor(200,200,200));
			totalnoEmpObjData.setBorderDetail(0);
			rg.addTableToDoc(totalnoEmpObjData);
			}else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				if((bean.getHiddenChechfrmId().equals("A")) || (bean.getHiddenChechfrmId().equals("P"))){
					rg.addTableToDoc(noData);
					}
			}
// for pending details start	
			int countSr = 1;
			Object[][] pendingHeadData = new Object[1][1];
			pendingHeadData[0][0] = "Status: Pending" ;
			TableDataSet pendingHeadDtlData = new TableDataSet();
			pendingHeadDtlData.setData(pendingHeadData);
			pendingHeadDtlData.setCellWidth(cellWidthDateHeader);
			pendingHeadDtlData.setCellAlignment(cellAlignDateHeader);  
			pendingHeadDtlData.setBodyFontStyle(1);  
			pendingHeadDtlData.setBorderDetail(0);
			pendingHeadDtlData.setBlankRowsAbove(1);
			
			if((bean.getHiddenChechfrmId().equals("A")) || (bean.getHiddenChechfrmId().equals("F"))){
				rg.addTableToDoc(pendingHeadDtlData);
				}
			
			if (loanApplPendingDetail != null && loanApplPendingDetail.length > 0) {
				int MAINCOUNT = 0;
				
				for (int i = 0; i < loanApplPendingDetail.length; i++) {
				objTotalTabularData[MAINCOUNT][0] = countSr++;
				objTotalTabularData[MAINCOUNT][1] = String.valueOf(loanApplPendingDetail[i][1]);
				objTotalTabularData[MAINCOUNT][2] = String.valueOf(loanApplPendingDetail[i][2]);

				objTotalTabularData[MAINCOUNT][3] = String.valueOf(loanApplPendingDetail[i][3]);
				objTotalTabularData[MAINCOUNT][4] = String.valueOf(loanApplPendingDetail[i][4]);
				MAINCOUNT++;
				}
				TableDataSet loanTypeInfoTable = new TableDataSet();
				loanTypeInfoTable.setHeader(header);
				loanTypeInfoTable.setHeaderBorderDetail(3);
				loanTypeInfoTable.setData(objTotalTabularData);
				loanTypeInfoTable.setBorderDetail(3);
				loanTypeInfoTable.setHeaderTable(true);
				loanTypeInfoTable.setCellWidth(bcellWidth);
				loanTypeInfoTable.setCellAlignment(bcellAlign);
				loanTypeInfoTable.setBorder(true);
				rg.addTableToDoc(loanTypeInfoTable);
				
				double Rentamount = 0.0;
				try {
					for (int i = 0; i < loanApplPendingDetail.length; i++) {
						loanApplPendingDetail[i][0] = String.valueOf(i + 1);
						Rentamount += Double.parseDouble(String
								.valueOf(loanApplPendingDetail[i][4]));
					} // end of loop
				} catch (Exception e) {
					logger.error("exception in houseRentDtl loop", e);
				} // end of catch

				TableDataSet reportObjData = new TableDataSet();
				Object [][] paramTotalRec=new Object[1][1];
				paramTotalRec[0][0]="Total Amount : " + formatter.format(Double.parseDouble(checkNull(String.valueOf(Rentamount))));
				reportObjData.setCellAlignment(new int[] {2});
				reportObjData.setCellWidth(new int[] {100});
				reportObjData.setCellColSpan(new int[] {5} );
				reportObjData.setData(paramTotalRec);
				reportObjData.setBorderDetail(0);
				rg.addTableToDoc(reportObjData);
				
				TableDataSet totalEmpObjData = new TableDataSet();
				Object [][] empTotalRec=new Object[1][1];
				empTotalRec[0][0]="Total No of Employees:   "+loanApplPendingDetail.length;
				totalEmpObjData.setCellAlignment(new int[] {0});
				totalEmpObjData.setCellWidth(new int[] {100});
				totalEmpObjData.setData(empTotalRec);
				totalEmpObjData.setBodyBGColor(new BaseColor(200,200,200));
				totalEmpObjData.setBorderDetail(0);
				rg.addTableToDoc(totalEmpObjData);
			//rg.seperatorLine();	
			}else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				if((bean.getHiddenChechfrmId().equals("A")) || (bean.getHiddenChechfrmId().equals("F"))){
				rg.addTableToDoc(noData);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
}

