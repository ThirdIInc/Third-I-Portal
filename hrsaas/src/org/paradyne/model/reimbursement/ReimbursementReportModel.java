package org.paradyne.model.reimbursement;

import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.reimbursement.ReimbursementReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class ReimbursementReportModel extends ModelBase
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	public void getReport(ReimbursementReport reimbursebean,
			HttpServletRequest request, HttpServletResponse response, String reportPath) {
		// TODO Auto-generated method stub
		try {			
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;			
			String reportType=reimbursebean.getReportType();
			String title = " Reimbursement Report ";
			ReportDataSet rds = new ReportDataSet();
			String fileName = "Reimbursement Report_"+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setUserEmpId(reimbursebean.getUserEmpId());
			rds.setReportName(title);
			rds.setPageSize("landscape");
			//System.out.println("reimbursebean.getReportType() : "+reimbursebean.getReportType());
			rds.setReportType(reportType);
			rds.setTotalColumns(13);
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "ReimbursementReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String filters = "";

			filters+="Period : "+reimbursebean.getFromDate()+" To "+reimbursebean.getToDate();
			
			if(!reimbursebean.getDivId().equals("")){
				filters+="\n\nDivision : "+reimbursebean.getDivision();
			}
			if(!reimbursebean.getBranchId().equals("")){
				filters+="\n\nBranch : "+reimbursebean.getBranch();
			}
			if(!reimbursebean.getDeptId().equals("")){
				filters+="\n\nDepartment : "+reimbursebean.getDept();
			}
			if(!reimbursebean.getPaybillid().equals("")){
				filters+="\n\nPaybill : "+reimbursebean.getPaybillname();
			}
			if(!reimbursebean.getGradeId().equals("")){
				filters+="\n\nGrade : "+reimbursebean.getGradeName();
			}
			
			if(!reimbursebean.getCreditCode().equals("")){
				filters+="\n\nReimburse Head  : "+reimbursebean.getCreditName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			//filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{13});
			filterData.setBlankRowsBelow(1);
			filterData.setCellNoWrap(new boolean[]{false});
			
		//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			try {
				//For Report heading
				
				if (reimbursebean.getStaticshow().equals("D")) {
					String whereClause = " ";
					try {
						if (!reimbursebean.getDivision().equals("")) {
							whereClause += " AND EMP_DIV IN ("
									+ reimbursebean.getDivId() + ") ";
						}
						if (!reimbursebean.getBranch().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
									+ reimbursebean.getBranchId() + ") ";
						}
						if (!reimbursebean.getDept().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
									+ reimbursebean.getDeptId() + ") ";
						}
						if (!reimbursebean.getPaybillname().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
									+ reimbursebean.getPaybillid() + ") ";
						}
						if (!reimbursebean.getGradeName().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
									+ reimbursebean.getGradeId() + ") ";
						}
						/*if(!reimbursebean.getDesg().equals(""))
						{
							whereClause +=" AND HRMS_EMP_OFFC.EMP_RANK ="+reimbursebean.getDesgId();
						}*/

						if ((!reimbursebean.getFromDate().equals(""))
								&& (reimbursebean.getToDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') >= TO_DATE('"
									+ reimbursebean.getFromDate() + "','dd-mm-yyyy')";
						} else if ((!reimbursebean.getToDate().equals(""))
								&& (reimbursebean.getFromDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') <= TO_DATE('"
									+ reimbursebean.getToDate() + "','dd-mm-yyyy')";
						} else if ((!reimbursebean.getFromDate().equals(""))
								&& (!reimbursebean.getToDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') >= TO_DATE('"
									+ reimbursebean.getFromDate()
									+ "','dd-mm-yyyy') "
									+ " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') <= TO_DATE('"
									+ reimbursebean.getToDate() + "','dd-mm-yyyy')";
						}

						if (!reimbursebean.getCreditName().equals("")) {
							whereClause += " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
									+ reimbursebean.getCreditCode() + ") ";
						}
					} catch (Exception e) {
						whereClause = "";
					}
					if (!reimbursebean.getAccountantID().equals("")) {
						whereClause += " AND REIMB_ACCOUNTANT ="
								+ reimbursebean.getAccountantID();
					}
					String empSql = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
							+ "	NVL(HRMS_DEPT.DEPT_NAME,''), HRMS_CREDIT_HEAD.CREDIT_NAME , TO_CHAR(HRMS_REIMB_APPLICATION.REIMB_APPL_DATE,'DD-MM-YYYY'),NVL(HRMS_REIMB_APPLICATION.REIMB_APPL_CODE,''), "
							+ " HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,HRMS_BANK.BANK_MICR_CODE, HRMS_BANK.BANK_NAME,HRMS_BANK.BANK_IFSC_CODE,"
							+ " CASE WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='P' THEN 'Pending For Manager Approval'  WHEN REIMB_APPL_STATUS='D' THEN 'Pending For Disbursement' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='C' THEN 'Closed' WHEN REIMB_APPL_STATUS='R' THEN 'Rejected by manager' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='B' THEN 'Sent back by manager' "
							+ "	WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='P' THEN 'Pending For Admin Approval' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='R' THEN 'Rejected by Admin' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='B' THEN 'Sent back by Admin' ELSE 'Pending' END,"
							+ " SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,HRMS_REIMB_APPL_DTL.REIMB_CLAIM_AMOUNT)), "
							+ " HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID, HRMS_EMP_OFFC.EMP_DEPT"
							+ " FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_REIMB_APPLICATION ON (HRMS_REIMB_APPLICATION.REIMB_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
							+ " INNER JOIN HRMS_REIMB_APPL_DTL ON (HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID) "
							+ " INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) "
							+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) "
							+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							+ " LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_MICR_REGULAR) "
							+ " INNER JOIN HRMS_DEPT ON (EMP_DEPT=DEPT_ID) "
							+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) WHERE 1=1";

					empSql += whereClause;

					empSql += " GROUP BY NVL(DEPT_NAME,''),NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
							+ " HRMS_DIVISION.DIV_NAME, CREDIT_NAME,REIMB_APPL_STATUS,REIMB_ADMIN_STATUS,REIMB_APPL_DATE,REIMB_APPL_CODE,HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID,SAL_ACCNO_REGULAR, "
							+ " BANK_NAME,BANK_MICR_CODE,BANK_IFSC_CODE,SAL_MICR_REGULAR,EMP_DEPT ";

					Object empData[][] = getSqlModel().getSingleResult(empSql);
					if(empData!=null && empData.length>0)
					{
						HashMap deptMap = getSqlModel().getSingleResultMap(empSql,
								13, 2);

						String deptQuery = "SELECT HRMS_DEPT.DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_ID ";
						Object deptObj[][] = getSqlModel().getSingleResult(
								deptQuery);
						if (deptObj != null && deptObj.length > 0) {
							for (int j = 0; j < deptObj.length; j++) {
								Object[][] empDataDeptWise = (Object[][]) deptMap
										.get(String.valueOf(deptObj[j][0]));
								/*System.out.println("empDataDeptWise :: "
										+ empDataDeptWise);*/

								double totalAmount = 0.0d;
								if (empDataDeptWise != null
										&& empDataDeptWise.length > 0) {
									/*System.out
											.println(" deptMap : :::  " + deptMap);*/
									Object[][] objTabularData = new Object[empDataDeptWise.length][13];
									Object[][] objTotalData = new Object[1][13];
									boolean[] bcellwrap = new boolean[13];
									objTotalData = Utility.checkNullObjArr(
											objTotalData, "");
									String[] columns = new String[] { "Sr. No",
											"Employee Code", "Employee Name",
											"Department", "Reimburse Head",
											"Application Date", "Application ID",
											"Account Number", "MICR code", "Bank ",
											"IFSC Code ", "Status", "Amount" };
									int[] bcellAlign = new int[] { 1, 0, 0, 0, 0,
											0, 0, 0, 0, 0, 0, 0, 2 };
									int[] bcellWidth = new int[] { 4, 10, 10, 8,
											10, 10, 8, 8, 6, 6, 5, 6, 9 };
									int count = 1;
									for (int i = 0; i < empDataDeptWise.length; i++) {
										objTabularData[i][0] = count++;
										objTabularData[i][1] = checkNull(String
												.valueOf(empDataDeptWise[i][0]));
										objTabularData[i][2] = checkNull(String
												.valueOf(empDataDeptWise[i][1]));
										objTabularData[i][3] = checkNull(String
												.valueOf(empDataDeptWise[i][2]));
										objTabularData[i][4] = checkNull(String
												.valueOf(empDataDeptWise[i][3]));
										objTabularData[i][5] = checkNull(String
												.valueOf(empDataDeptWise[i][4]));
										objTabularData[i][6] = checkNull(String
												.valueOf(empDataDeptWise[i][5]));
										objTabularData[i][7] = checkNull(String
												.valueOf(empDataDeptWise[i][6]));
										objTabularData[i][8] = checkNull(String
												.valueOf(empDataDeptWise[i][7]));
										objTabularData[i][9] = checkNull(String
												.valueOf(empDataDeptWise[i][8]));
										objTabularData[i][10] = checkNull(String
												.valueOf(empDataDeptWise[i][9]));
										objTabularData[i][11] = checkNull(String
												.valueOf(empDataDeptWise[i][10]));
										objTabularData[i][12] = Utility.twoDecimals(Double
										.parseDouble(checkNull(String
												.valueOf(empDataDeptWise[i][11]))));
										totalAmount += Double
												.parseDouble(checkNull(String
														.valueOf(empDataDeptWise[i][11])));
									}
									objTotalData[0][11] = "Total Amount ";
									objTotalData[0][12] = totalAmount;

									//For table data
									TableDataSet tdstable = new TableDataSet();
									tdstable.setBlankRowsAbove(1);
									tdstable.setHeader(columns);
									tdstable.setData(objTabularData);
									tdstable.setCellAlignment(bcellAlign);
									tdstable.setCellWidth(bcellWidth);
									tdstable.setBorderDetail(3);
									tdstable.setCellNoWrap(bcellwrap);
									tdstable.setBorder(true);
									tdstable.setHeaderBorderDetail(3);
//									tdstable.setHeaderBGColor(new BaseColor(225,
//											225, 225));
									//tdstable.setBlankRowsBelow(1);
									rg.addTableToDoc(tdstable);

									//For total amount
									TableDataSet totaltable = new TableDataSet();
									totaltable.setData(objTotalData);
									totaltable.setCellAlignment(bcellAlign);
									totaltable.setCellWidth(bcellWidth);
									totaltable.setBorderDetail(3);
									rg.addTableToDoc(totaltable);
								}

								
							}
						}

					}
					else
					{
						TableDataSet noData = new TableDataSet();
						Object[][] noDataObj = new Object[1][1];
						noDataObj[0][0] = "No records available";
						
						noData.setData(noDataObj);
						noData.setCellAlignment(new int[] { 1 });
						noData.setCellWidth(new int[] { 100 });
//						noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
//								Font.BOLD, new BaseColor(0, 0, 0));
						noData.setBorderDetail(3);
						rg.addTableToDoc(noData);
						
					}
					
					//if (reimbursebean.getDeptId().equals("")) {
						
						
						

						if (empData!=null && empData.length>0) {
							Vector deptStatVect = getDeptStatistic(reimbursebean,
									whereClause);
							rg.addProperty(rg.PAGE_BREAK);
							Object statHeading[][] = new Object[1][1];
							statHeading[0][0] = "Department Statistics ";
							TableDataSet deptTitle = new TableDataSet();
							deptTitle.setData(statHeading);
							deptTitle.setCellAlignment(new int[] { 0 });
							deptTitle.setCellWidth(new int[] { 100 });
							deptTitle.setBodyFontStyle(1);
//							deptTitle.setBodyFontDetails(Font.FontFamily.HELVETICA,
//									10, Font.BOLD, new BaseColor(0, 0, 0));
							deptTitle.setBlankRowsAbove(1);
							rg.addTableToDoc(deptTitle);
							TableDataSet cashStatTable = new TableDataSet();
							cashStatTable.setHeader((String[]) deptStatVect
									.get(0));
							cashStatTable.setData((Object[][]) deptStatVect
									.get(1));
							cashStatTable.setCellWidth((int[]) deptStatVect
									.get(2));
							cashStatTable.setCellAlignment((int[]) deptStatVect
									.get(3));
							cashStatTable.setBorder(true);
							cashStatTable.setBorderDetail(3);
							cashStatTable.setHeaderBorderDetail(3);
							/*cashStatTable.setHeaderBGColor(new BaseColor(225,
									225, 225));*/
							cashStatTable.setHeaderBorderDetail(3);
							rg.addTableToDoc(cashStatTable);

						} else {

							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";

							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
//							noData.setBodyFontDetails(
//									Font.FontFamily.HELVETICA, 10, Font.BOLD,
//									new BaseColor(0, 0, 0));
							noData.setBorderDetail(0);
							rg.addTableToDoc(noData);
						}

					//}
				} else {
					

					String whereClause = " ";
					try {
						if (!reimbursebean.getDivision().equals("")) {
							whereClause += " AND EMP_DIV IN ("
									+ reimbursebean.getDivId() + ") ";
						}
						if (!reimbursebean.getBranch().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
									+ reimbursebean.getBranchId() + ") ";
						}
						if (!reimbursebean.getDept().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
									+ reimbursebean.getDeptId() + ") ";
						}
						if (!reimbursebean.getPaybillname().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
									+ reimbursebean.getPaybillid() + ") ";
						}
						if (!reimbursebean.getGradeName().equals("")) {
							whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
									+ reimbursebean.getGradeId() + ") ";
						}
						/*if(!reimbursebean.getDesg().equals(""))
						{
							whereClause +=" AND HRMS_EMP_OFFC.EMP_RANK ="+reimbursebean.getDesgId();
						}*/

						if ((!reimbursebean.getFromDate().equals(""))
								&& (reimbursebean.getToDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') >= TO_DATE('"
									+ reimbursebean.getFromDate() + "','dd-mm-yyyy')";
						} else if ((!reimbursebean.getToDate().equals(""))
								&& (reimbursebean.getFromDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') <= TO_DATE('"
									+ reimbursebean.getToDate() + "','dd-mm-yyyy')";
						} else if ((!reimbursebean.getFromDate().equals(""))
								&& (!reimbursebean.getToDate().equals(""))) {
							whereClause += " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') >= TO_DATE('"
									+ reimbursebean.getFromDate()
									+ "','dd-mm-yyyy') "
									+ " AND TO_DATE(TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),'dd-mm-yyyy') <= TO_DATE('"
									+ reimbursebean.getToDate() + "','dd-mm-yyyy')";
						}

						if (!reimbursebean.getCreditName().equals("")) {
							whereClause += " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
									+ reimbursebean.getCreditCode() + ") ";
						}
					} catch (Exception e) {
						whereClause = "";
					}
					if (!reimbursebean.getAccountantID().equals("")) {
						whereClause += " AND REIMB_ACCOUNTANT ="
								+ reimbursebean.getAccountantID();
					}
					String empSql = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
							+ "	NVL(HRMS_COST_CENTER.COST_CENTER_NAME,''), HRMS_CREDIT_HEAD.CREDIT_NAME ,  TO_CHAR(HRMS_REIMB_APPLICATION.REIMB_APPL_DATE,'DD-MM-YYYY'),NVL(HRMS_REIMB_APPLICATION.REIMB_APPL_CODE,''), "
							+ " HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,HRMS_BANK.BANK_MICR_CODE, HRMS_BANK.BANK_NAME,HRMS_BANK.BANK_IFSC_CODE,"
							+ " CASE WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='P' THEN 'Pending For Manager Approval'  WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='D' THEN 'Pending For Disbursement' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='C' THEN 'Closed' WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='R' THEN 'Rejected by manager' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_APPL_STATUS='B' THEN 'Sent back by manager' "
							+ "	WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='P' THEN 'Pending For Admin Approval' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='R' THEN 'Rejected by Admin' "
							+ " WHEN HRMS_REIMB_APPLICATION.REIMB_ADMIN_STATUS='B' THEN 'Sent back by Admin' ELSE 'Pending' END,"
							+ " SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,HRMS_REIMB_APPL_DTL.REIMB_CLAIM_AMOUNT)),  HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID, HRMS_SALARY_MISC.COST_CENTER_ID"
							+ " FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_REIMB_APPLICATION ON (HRMS_REIMB_APPLICATION.REIMB_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
							+ " INNER JOIN HRMS_REIMB_APPL_DTL ON (HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID) "
							+ " INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) "
							+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) "
							+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							+ " LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_MICR_REGULAR) "
							+ " INNER JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID) "
							+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) WHERE 1=1";

					empSql += whereClause;

					empSql += " GROUP BY NVL(COST_CENTER_NAME,''),NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
							+ " HRMS_DIVISION.DIV_NAME, CREDIT_NAME,REIMB_APPL_STATUS,REIMB_ADMIN_STATUS,REIMB_APPL_DATE,REIMB_APPL_CODE,HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID,SAL_ACCNO_REGULAR, "
							+ " BANK_NAME,BANK_MICR_CODE,BANK_IFSC_CODE,SAL_MICR_REGULAR,HRMS_SALARY_MISC.COST_CENTER_ID ";

					Object empData[][] = getSqlModel().getSingleResult(empSql);
					if(empData!=null &&empData.length>0)
					{
						HashMap deptMap = getSqlModel().getSingleResultMap(empSql,
								13, 2);

						String deptQuery = "SELECT HRMS_COST_CENTER.COST_CENTER_ID FROM HRMS_COST_CENTER ORDER BY HRMS_COST_CENTER.COST_CENTER_ID ";
						Object deptObj[][] = getSqlModel().getSingleResult(
								deptQuery);
						if (deptObj != null && deptObj.length > 0) {
							for (int j = 0; j < deptObj.length; j++) {
								Object[][] empDataDeptWise = (Object[][]) deptMap
										.get(String.valueOf(deptObj[j][0]));
								/*System.out.println("empDataDeptWise :: "
										+ empDataDeptWise);*/

								double totalAmount = 0.0d;
								if (empDataDeptWise != null
										&& empDataDeptWise.length > 0) {
									System.out.println(" deptMap : " + deptMap);
									Object[][] objTabularData = new Object[empDataDeptWise.length][13];
									Object[][] objTotalData = new Object[1][13];
									objTotalData = Utility.checkNullObjArr(
											objTotalData, "");
									String[] columns = new String[] { "Sr. No",
											"Employee Code", "Employee Name",
											"Cost Center", "Reimburse Head",
											"Application Date", "Application ID",
											"Account Number", "MICR code", "Bank ",
											"IFSC Code ", "Status", "Amount" };
									int[] bcellAlign = new int[] { 1, 0, 0, 0, 0,
											0, 0, 0, 0, 0, 0, 0, 2 };
									int[] bcellWidth = new int[] { 4, 10, 10, 8,
											10, 10, 8, 8, 6, 6, 5, 6, 9 };
									int count = 1;
									for (int i = 0; i < empDataDeptWise.length; i++) {
										objTabularData[i][0] = count++;
										objTabularData[i][1] = checkNull(String
												.valueOf(empDataDeptWise[i][0]));
										objTabularData[i][2] = checkNull(String
												.valueOf(empDataDeptWise[i][1]));
										objTabularData[i][3] = checkNull(String
												.valueOf(empDataDeptWise[i][2]));
										objTabularData[i][4] = checkNull(String
												.valueOf(empDataDeptWise[i][3]));
										objTabularData[i][5] = checkNull(String
												.valueOf(empDataDeptWise[i][4]));
										objTabularData[i][6] = checkNull(String
												.valueOf(empDataDeptWise[i][5]));
										objTabularData[i][7] = checkNull(String
												.valueOf(empDataDeptWise[i][6]));
										objTabularData[i][8] = checkNull(String
												.valueOf(empDataDeptWise[i][7]));
										objTabularData[i][9] = checkNull(String
												.valueOf(empDataDeptWise[i][8]));
										objTabularData[i][10] = checkNull(String
												.valueOf(empDataDeptWise[i][9]));
										objTabularData[i][11] = checkNull(String
												.valueOf(empDataDeptWise[i][10]));
										objTabularData[i][12] = Utility.twoDecimals(Double
										.parseDouble(checkNull(String
												.valueOf(empDataDeptWise[i][11]))));
										totalAmount += Double
												.parseDouble(checkNull(String
														.valueOf(empDataDeptWise[i][11])));
									}
									objTotalData[0][11] = "Total Amount ";
									objTotalData[0][12] = totalAmount;

									//For table data
									TableDataSet tdstable = new TableDataSet();
									//tdstable.setBlankRowsAbove(1);
									tdstable.setHeader(columns);
									tdstable.setData(objTabularData);
									tdstable.setCellAlignment(bcellAlign);
									tdstable.setCellWidth(bcellWidth);
									tdstable.setBorder(true);
									tdstable.setBorderDetail(3);
									//tdstable.setHeaderBGColor(new BaseColor(225,
									//225, 225));
									//tdstable.setBlankRowsBelow(1);
									rg.addTableToDoc(tdstable);

									//For total amount
									TableDataSet totaltable = new TableDataSet();
									totaltable.setData(objTotalData);
									totaltable.setCellAlignment(bcellAlign);
									totaltable.setCellWidth(bcellWidth);
									totaltable.setBorderDetail(3);
									totaltable.setBlankRowsBelow(1);
									rg.addTableToDoc(totaltable);
								}

								
							}
						}

						//if (reimbursebean.getDeptId().equals("")) {
							//if(empData)
							Vector costStatVect = getCostStatistic(reimbursebean,
									whereClause);
							rg.addProperty(rg.PAGE_BREAK);
							Object statHeading[][] = new Object[1][1];
							statHeading[0][0] = "Costcenter Statistics ";
							TableDataSet deptTitle = new TableDataSet();
							deptTitle.setData(statHeading);
							deptTitle.setBodyFontStyle(1);
							deptTitle.setCellAlignment(new int[] { 0 });
							deptTitle.setCellWidth(new int[] { 100 });
							
							deptTitle.setBlankRowsAbove(1);
							rg.addTableToDoc(deptTitle);

							if (!(empData == null || empData.length <= 0)) {
								TableDataSet cashStatTable = new TableDataSet();
								cashStatTable.setHeader((String[]) costStatVect
										.get(0));
								cashStatTable.setData((Object[][]) costStatVect
										.get(1));
								cashStatTable.setCellWidth((int[]) costStatVect
										.get(2));
								cashStatTable.setCellAlignment((int[]) costStatVect
										.get(3));
								cashStatTable.setBorder(true);
								cashStatTable.setBorderDetail(3);
								cashStatTable.setHeaderBorderDetail(3);
								/*cashStatTable.setHeaderBGColor(new BaseColor(225,
										225, 225));*/
								rg.addTableToDoc(cashStatTable);

							} 

						//}
						
					}
					else
					{
						TableDataSet noData = new TableDataSet();
						Object[][] noDataObj = new Object[1][1];
						noDataObj[0][0] = "No records available";

						noData.setData(noDataObj);
						noData.setCellAlignment(new int[] { 1 });
						noData.setCellWidth(new int[] { 100 });
						/*noData.setBodyFontDetails(
								Font.FontFamily.HELVETICA, 10, Font.BOLD,
								new BaseColor(0, 0, 0));*/
						noData.setBorder(false);
						rg.addTableToDoc(noData);
					}
					

				}
			} catch (Exception e) {
				e.printStackTrace();
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
						Font.BOLD, new BaseColor(0, 0, 0));*/
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				
				rg.saveReport(response);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private Vector getCostStatistic(ReimbursementReport reimbursebean,
			 String whereClause) {

		
		Object [][]creditHead =null;
		Object [][]deptName=null;
		Vector deptStaticVect=new Vector();
		if(!reimbursebean.getCreditCode().equals("")){
			creditHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_NAME "
					+" FROM HRMS_REIMB_CONFIG INNER JOIN HRMS_CREDIT_HEAD ON  (CREDIT_CODE=REIMB_CREDIT_HEAD)"
					+" Where CREDIT_CODE IN ("+reimbursebean.getCreditCode()+")"
					+" ORDER BY UPPER(CREDIT_NAME)");
		}else{
			creditHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_NAME "
						+" FROM HRMS_REIMB_CONFIG INNER JOIN HRMS_CREDIT_HEAD ON  (CREDIT_CODE=REIMB_CREDIT_HEAD)"
						+" ORDER BY UPPER(CREDIT_NAME)");
			}
		 
		
		deptName =getSqlModel().getSingleResult("SELECT DISTINCT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_REIMB_APPLICATION "+
												"INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_EMP_ID) "+
												"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+
												"INNER JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID) "+
												"ORDER BY UPPER(COST_CENTER_NAME)" );
		
		/*
		 * FOR CASH CLAIM
		 * 
		 */
		Object [][] deptStaticData=null;
		String dataQuery="";
		int deptNameLength =deptName.length;
		
		
		String colNames []=new String[creditHead.length+3] ;
		int cellwidth[]=new int[creditHead.length+3];
		int alignment[]=new int[creditHead.length+3];
		
		int colNameLength =colNames.length;
		colNames[1]="CostCenter";
		cellwidth[1]=25;
		alignment[1]=0;
		
		colNames[0]="Sr. No.";
		cellwidth[0]=5;
		alignment[0]=1;
		
		colNames[colNameLength-1]="Total";
		cellwidth[colNameLength-1]=25;
		alignment[colNameLength-1]=2;
		
		for (int i = 2; i < colNameLength-1; i++) {
			colNames[i]=String.valueOf(creditHead[i-2][1]);
			cellwidth[i]=20;
			alignment[i]=2;
		}
		
		dataQuery = "SELECT NVL(TO_CHAR(SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT)),9999999999990.99),0),COST_CENTER_ID||'#'||CREDIT_CODE FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_REIMB_APPL_DTL ON(REIMB_CREDIT_CODE=CREDIT_CODE)  INNER JOIN HRMS_REIMB_APPLICATION ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID) INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_EMP_ID) "
				+ "LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "INNER JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID) WHERE 1=1 "
				+ whereClause + " GROUP BY COST_CENTER_ID,CREDIT_CODE";
		deptStaticData =new Object[deptName.length+1][creditHead.length+3];
		//System.out.println("deptStaticData : "+deptStaticData.length);
		Object tempAmtData[][]=null;
		
		HashMap deptStatMap=getSqlModel().getSingleResultMap(dataQuery, 1, 2);
		
		int cnt=0;
		int creditCnt=0;
		for (int i = 0; i < deptStaticData.length; i++) {
			
			if(i !=deptStaticData.length-1){
				double creditHeadTot =0.0;
				
				//System.out.println("deptStaticData["+i+"].length : "+deptStaticData[i].length);
				
			for (int j = 0; j < deptStaticData[0].length; j++) {
				
				if(j==0){
					deptStaticData[i][j] = i+1;
					 creditCnt=0;
				}else if(j==1 && i !=deptStaticData.length-1){
					
					//System.out.println("The dept Name : "+String.valueOf(deptName[i][1]));
							deptStaticData[i][j] =String.valueOf(deptName[i][1]) ;
					
				}else if(j==deptStaticData[0].length-1){
					
					deptStaticData[i][j] = Utility.twoDecimals(creditHeadTot);
				}
				else if(i !=deptStaticData.length-1){
					 //creditHeadTot =0.0;
					
					/*System.out.println("deptStaticData["+i+"].length : "+deptStaticData[i].length);
					
							System.out.println("the helllo :: "+String.valueOf(deptName[i][0])+"#"+String.valueOf(creditHead[creditCnt][0]));
*/							tempAmtData =(Object[][])deptStatMap.get(String.valueOf(deptName[i][0])+"#"+String.valueOf(creditHead[creditCnt++][0]));
							
							if(tempAmtData !=null && tempAmtData.length>0){
								deptStaticData[i][j] = String.valueOf(tempAmtData[0][0]);
								//System.out.println("deptStaticData["+i+"]["+j+"] : :::::: : "+deptStaticData[i][j]);
								creditHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
							}else{
								deptStaticData[i][j] = "0";
								//System.out.println("deptStaticData["+i+"]["+j+"] : :::::: : "+deptStaticData[i][j]);
								creditHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
							}
							
					
				}
				}
			}else{
				deptStaticData[i][1] ="Total";
			}
	
			}
		
			double totalAmt =0.00;
			for (int j = 2; j < deptStaticData[0].length-1; j++) {
				double deptTot =0.0;
				for (int i = 0; i < deptStaticData.length; i++) {
					if(i !=deptStaticData.length-1){
						deptTot +=Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}else {
						deptStaticData[i][j] =Utility.twoDecimals(deptTot);
						totalAmt +=deptTot;
					}
				}
			}
			deptStaticData[deptStaticData.length-1][deptStaticData[0].length-1]=Utility.twoDecimals(totalAmt);
			deptStaticData[deptStaticData.length-1][0]=" ";
			
		
		
		
		deptStaticVect.add(colNames);
		deptStaticVect.add(deptStaticData);
		deptStaticVect.add(cellwidth);
		deptStaticVect.add(alignment);
		
		
		
		/*
		 * END CASH CLAIM
		 */
		
		
		
		return deptStaticVect;
	
	}
	
	private Vector getDeptStatistic(ReimbursementReport reimbursebean,
			 String whereClause) {

		
		Object [][]creditHead =null;
		Object [][]deptName=null;
		Vector deptStaticVect=new Vector();
		if(!reimbursebean.getCreditCode().equals("")){
			creditHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_NAME "
					+" FROM HRMS_REIMB_CONFIG INNER JOIN HRMS_CREDIT_HEAD ON  (CREDIT_CODE=REIMB_CREDIT_HEAD)"
					+" Where CREDIT_CODE IN ("+reimbursebean.getCreditCode()+")"
					+" ORDER BY UPPER(CREDIT_NAME)");
		}else{
			creditHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_NAME "
						+" FROM HRMS_REIMB_CONFIG INNER JOIN HRMS_CREDIT_HEAD ON  (CREDIT_CODE=REIMB_CREDIT_HEAD)"
						+" ORDER BY UPPER(CREDIT_NAME)");
			}
		 
		if(!reimbursebean.getDeptId().equals("")){
			/*deptName =new Object[1][2];
			deptName[0][0]=reimbursebean.getDeptId();
			deptName[0][1]=reimbursebean.getDept();*/
			deptName =getSqlModel().getSingleResult("SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_REIMB_APPLICATION"
					+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_EMP_ID)"
					+" INNER JOIN HRMS_DEPT ON(EMP_DEPT=DEPT_ID)  " +
					"WHERE DEPT_ID IN ("+reimbursebean.getDeptId()+")"
							+"ORDER BY UPPER(DEPT_NAME)" );

			
		}else{
		deptName =getSqlModel().getSingleResult("SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_REIMB_APPLICATION"
								+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_EMP_ID)"
								+" INNER JOIN HRMS_DEPT ON(EMP_DEPT=DEPT_ID)  ORDER BY UPPER(DEPT_NAME)" );
		}
		/*
		 * FOR CASH CLAIM
		 * 
		 */
		Object [][] deptStaticData=null;
		String dataQuery="";
		int deptNameLength =deptName.length;
		
		
		String colNames []=new String[creditHead.length+3] ;
		int cellwidth[]=new int[creditHead.length+3];
		int alignment[]=new int[creditHead.length+3];
		
		int colNameLength =colNames.length;
		colNames[1]="Department";
		cellwidth[1]=25;
		alignment[1]=0;
		
		colNames[0]="Sr. No.";
		cellwidth[0]=5;
		alignment[0]=1;
		
		colNames[colNameLength-1]="Total";
		cellwidth[colNameLength-1]=25;
		alignment[colNameLength-1]=2;
		
		for (int i = 2; i < colNameLength-1; i++) {
			colNames[i]=String.valueOf(creditHead[i-2][1]);
			cellwidth[i]=20;
			alignment[i]=2;
		}
		
		dataQuery ="SELECT NVL(TO_CHAR(SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT)),9999999999990.99),0),DEPT_ID||'#'||CREDIT_CODE FROM HRMS_CREDIT_HEAD " 
				+" LEFT JOIN HRMS_REIMB_APPL_DTL ON(REIMB_CREDIT_CODE=CREDIT_CODE) "
				+" INNER JOIN HRMS_REIMB_APPLICATION ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
				+" INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)" 
				+" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_EMP_ID)"
				+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=EMP_DEPT) WHERE 1=1 "+whereClause
				+" GROUP BY DEPT_ID,CREDIT_CODE";
		deptStaticData =new Object[deptName.length+1][creditHead.length+3];
		//System.out.println("deptStaticData : "+deptStaticData.length);
		Object tempAmtData[][]=null;
		
		HashMap deptStatMap=getSqlModel().getSingleResultMap(dataQuery, 1, 2);
		
		int cnt=0;
		int creditCnt=0;
		for (int i = 0; i < deptStaticData.length; i++) {
			
			if(i !=deptStaticData.length-1){
				double creditHeadTot =0.0;
				
				//System.out.println("deptStaticData["+i+"].length : "+deptStaticData[i].length);
				
			for (int j = 0; j < deptStaticData[0].length; j++) {
				
				if(j==0){
					deptStaticData[i][j] = i+1;
					 creditCnt=0;
				}else if(j==1 && i !=deptStaticData.length-1){
					/*System.out.println("The dept Name : "+String.valueOf(deptName[i][1]));*/
							deptStaticData[i][j] =String.valueOf(deptName[i][1]) ;
					
				}else if(j==deptStaticData[0].length-1){
					//System.out.println("creditHeadTot : &&&&& : "+creditHeadTot);
					deptStaticData[i][j] = Utility.twoDecimals(creditHeadTot);
					//System.out.println("deptStaticData["+i+"]["+j+"] : :::::: : "+deptStaticData[i][j]);
				}
				else if(i !=deptStaticData.length-1){
					 //creditHeadTot =0.0;
					
					//System.out.println("deptStaticData["+i+"].length : "+deptStaticData[i].length);
					
							//System.out.println("the helllo :: "+String.valueOf(deptName[i][0])+"#"+String.valueOf(creditHead[creditCnt][0]));
							tempAmtData =(Object[][])deptStatMap.get(String.valueOf(deptName[i][0])+"#"+String.valueOf(creditHead[creditCnt++][0]));
							
							if(tempAmtData !=null && tempAmtData.length>0){
								deptStaticData[i][j] = String.valueOf(tempAmtData[0][0]);
								//System.out.println("deptStaticData["+i+"]["+j+"] : :::::: : "+deptStaticData[i][j]);
								creditHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
								//System.out.println("creditHeadTot : Inside If : "+creditHeadTot);
							}else{
								deptStaticData[i][j] = "0";
								//System.out.println("deptStaticData["+i+"]["+j+"] : :::::: : "+deptStaticData[i][j]);
								creditHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
								//System.out.println("creditHeadTot : Inside else : "+creditHeadTot);
							}
							
					
				}
				}
			}else{
				deptStaticData[i][1] ="Total";
			}
	
			}
		
			double totalAmt =0.00;
			for (int j = 2; j < deptStaticData[0].length-1; j++) {
				double deptTot =0.0;
				for (int i = 0; i < deptStaticData.length; i++) {
					if(i !=deptStaticData.length-1){
						deptTot +=Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}else {
						deptStaticData[i][j] =Utility.twoDecimals(deptTot);
						totalAmt +=deptTot;
					}
				}
			}
			deptStaticData[deptStaticData.length-1][deptStaticData[0].length-1]=Utility.twoDecimals(totalAmt);
			deptStaticData[deptStaticData.length-1][0]=" ";
			
		
		
		
		deptStaticVect.add(colNames);
		deptStaticVect.add(deptStaticData);
		deptStaticVect.add(cellwidth);
		deptStaticVect.add(alignment);
		
		
		
		/*
		 * END CASH CLAIM
		 */
		
		
		
		return deptStaticVect;
	
	}
	

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}
}