package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.MonthlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;

/**
 * @author aa1385 Date:30-11-2011
 */
public class MonthlyEDSummaryReportModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * report type Pdf.
	 */
	public static final String REPORT_TYPE_PDF = "Pdf";
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MonthlyEDSummaryReportModel.class);

	/**
	 * @param str :
	 *            string
	 * @param space :
	 *            space
	 * @return str
	 */
	public String addSpaces(String str, final int space) {
		for (int i = 0; i < space; i++) {
			str += " ";
		}
		return str;

	}

	/**
	 * Method to check string is null or not.
	 * 
	 * @param result -
	 *            Check null or not
	 * @return - String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}

	public void getReport(MonthlyEDSummaryReportBean bean,
			HttpServletResponse response, HttpServletRequest request,
			String checkBoxVar, String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			
			String type = bean.getReport();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = "Monthly Earning-Deduction Summary "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Monthly Earning-Deduction Summary");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("A4");
			/*rds.setMarginBottom(25f);
			rds.setMarginLeft(25f);
			rds.setMarginRight(25f);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(5);
			//rds.setMarginTop(25f);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "MonthlyEDSummaryReport_input.action");
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

	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, MonthlyEDSummaryReportBean bean) {
		
		try {
			String title = " Monthly Earning-Deduction Summary  ";
			Date date = new Date();
			final String month = bean.getMonth(); // month
		
			final String year = bean.getYear(); // year

		/*	Object[][] nameObj = new Object[1][1];
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
						String filePath = context.getRealPath("/")
								+ "pages/Logo/"
								+ session.getAttribute("session_pool") + "/"
								+ filename;
						nameObj[0][0] = Image.getInstance(filePath);
						isLogo = true;

					} else
						nameObj[0][0] = "";

				} else {
					nameObj[0][0] = "";
				}
				// Image
				// im=Image.getInstance("C:/hrwork/dataFiles/sal_logo.jpg");

				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			} catch (Exception eee) {
				logger.info("when assign the image...!" + eee);

				nameObj[0][0] = " ";
				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			}

			TableDataSet logoData = new TableDataSet();
			logoData.setData(nameObj);
			logoData.setCellAlignment(new int[] { 0 });
			logoData.setCellWidth(new int[] { 100 });
			logoData.setBorder(false);
			
			Object[][] titleObj = new Object[1][1];
			titleObj[0][0] = "" ;

			TableDataSet titleName = new TableDataSet();
			titleName.setData(titleObj);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			titleName.setBorder(false);

			Object[][] subtitleObj = new Object[1][1];
			subtitleObj[0][0] = "" ;

			TableDataSet subtitleName = new TableDataSet();
			subtitleName.setData(subtitleObj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			
			subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			subtitleName.setBorder(false);

			Object[][] subtitleObj2 = new Object[1][1];
			subtitleObj2[0][0] = "" + title + " " ;

			TableDataSet subtitleName2 = new TableDataSet();
			subtitleName2.setData(subtitleObj2);
			subtitleName2.setCellAlignment(new int[] { 1 });
			subtitleName2.setCellWidth(new int[] { 100 });
			
			subtitleName2.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
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

			rg.addTableToDoc(mapThree);*/

			// For Report heading
			/*TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);*/
			
			/* Setting filter details */
			String filters = "Period: " + Utility.month(Integer.parseInt(month)) + " - "+ year + "";
			
			
			if(!bean.getDivName().equals("")){
				filters+="\n\nDivision: "+bean.getDivName();
			}
			
			if(!bean.getBranchName().equals("")){
				filters+="\n\nBranch: "+bean.getBranchName();
			}
		
			if(!bean.getDeptName().equals("")){
				filters+="\n\nDepartment: "+bean.getDeptName();
			}
			
			if(!bean.getPaybillName().equals("")){
				filters+="\n\nPaybill: "+bean.getPaybillName();
			}
			
			if(!bean.getTypeName().equals("")){
				filters+="\n\nEmployee Type: "+bean.getTypeName();
			}
			
			if(!bean.getCostCenterName().equals("")){
				filters+="\n\nCost Center: "+bean.getCostCenterName();
			}
			
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{filters}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellWidth(new int[]{100});
			//filterData.setBodyFontStyle(1);
			filterData.setCellColSpan(new int[]{5});
			//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			//filterData.setBorder(false);
			//filterData.setBorderDetail(0);
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
		
	
			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
						+ bean.getBranchCode()+")";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			/*
			 * if(!bean.getDivName().equals("")) { whereClause +=" AND
			 * HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivCode();
			 * System.out.println("+bean.getDivId() =" +bean.getDivCode()); }
			 */
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
						+ bean.getDeptCode()+")";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			if (!bean.getEmpName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_ID IN(" + bean.getEmpId()+")";
				System.out.println("+bean.getEmpCode() =" + bean.getEmpId());
			}
			if (!bean.getCadreName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN("
						+ bean.getCadreCode()+")";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN("
						+ bean.getPaybillId()+")";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			if (!bean.getTypeName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_TYPE IN("
						+ bean.getTypeCode()+")";
				System.out
						.println("+bean.getTypeCode() =" + bean.getTypeCode());
			}
			
			if (!bean.getCostCenterName().equals("")) {
				whereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}

		//	whereClause += "  ORDER BY LEDGER_CODE DESC";

			// Current Month
			String ledgerQuery = " SELECT HRMS_SALARY_LEDGER.LEDGER_CODE, HRMS_SALARY_LEDGER.LEDGER_MONTH, HRMS_SALARY_LEDGER.LEDGER_YEAR , "
					+ "HRMS_SALARY_LEDGER.LEDGER_DIVISION FROM HRMS_SALARY_LEDGER " 
					+ "INNER join HRMS_SALARY_"+ year + " on(HRMS_SALARY_"+ year + ".SAL_LEDGER_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+ "INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+ year + ".EMP_ID)"
					+ "INNER join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "INNER join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+" left join HRMS_PAYBILL on(HRMS_PAYBILL.PAYBILL_ID = HRMS_EMP_OFFC.EMP_PAYBILL) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH = "
					+ bean.getMonth()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_YEAR = "
					+ bean.getYear()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+")";

			ledgerQuery += whereClause;
			
			Object[][] expDetail = getSqlModel().getSingleResult(
					ledgerQuery);

			String[] header = null;
			int creditCount = 0;
			int debitCount = 0;

			header = new String[5];

			///if (!bean.getCreditCode().equals("")) {
			//	header[0] = "Sr. No.";
				header[0] = "Earning";
				header[1] = "Amount (Rs.) ";
				header[2] = "";
				header[3] = "Deduction ";
				header[4] = "Amount (Rs.) ";
			/*} else {
				header[0] = "Sr. No.";
				header[1] = "Deduction Head";
				header[2] = "Deduction (Rs.)";
				header[3] = "Arrears Deduction (Rs.) ";
				header[4] = "Net (Rs.)";
			}*/

			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 22;
					bcellwrap[i]=true;
				} else if (i == 1) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 22;
					bcellwrap[i]=true;
				} else if (i == 2) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 0;
					bcellwrap[i]=true;
				} else if (i == 3) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 22;
					bcellwrap[i]=true;
				}
				else  {
					bcellAlign[i] = 2;
					bcellWidth[i] = 22;
					bcellwrap[i]=true;
				}
			}
			
			/////////////////
			String CRQuery = "";

			// Current Month
			CRQuery = " SELECT HRMS_SAL_CREDITS_"+ year+".SAL_CREDIT_CODE,SUM(HRMS_SAL_CREDITS_"+ year+".SAL_AMOUNT),HRMS_SAL_CREDITS_"+ year+".SAL_MONTH,HRMS_SAL_CREDITS_"+ year+".SAL_YEAR "
					+ "FROM HRMS_SAL_CREDITS_"+ year+" "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"
					+ year
					+ ".SAL_LEDGER_CODE) "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+year+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ " WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "
					+ bean.getDivCode()
					+ " )"
					+ " AND HRMS_SAL_CREDITS_"+ year+".SAL_MONTH="+month+" AND HRMS_SAL_CREDITS_"+ year+".SAL_AMOUNT>0   ";

			CRQuery += whereClause;
			CRQuery += " GROUP BY 	SAL_CREDIT_CODE,SAL_MONTH,SAL_YEAR";

			Object[][] CRDetail = getSqlModel().getSingleResult(CRQuery);
			HashMap<String, String> creditMap = new HashMap<String, String>();
			HashMap<String, String> creditArrMap = new HashMap<String, String>();

			String debitMnQuery = "";
			debitMnQuery = " SELECT HRMS_SAL_DEBITS_"+ year+".SAL_DEBIT_CODE,SUM(HRMS_SAL_DEBITS_"+ year+".SAL_AMOUNT),HRMS_SAL_DEBITS_"+ year+".SAL_MONTH,HRMS_SAL_DEBITS_"+ year+".SAL_YEAR "
					+ "FROM HRMS_SAL_DEBITS_"+ year+" "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ year
					+ ".SAL_LEDGER_CODE) "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "
					+ bean.getDivCode()
					+ " )"
					+ " AND HRMS_SAL_DEBITS_"+ year+".SAL_MONTH="+month+" AND HRMS_SAL_DEBITS_"+ year+".SAL_AMOUNT>0    ";

			debitMnQuery += whereClause;
			debitMnQuery += " GROUP BY 	SAL_DEBIT_CODE,SAL_MONTH,SAL_YEAR ";

			Object[][] debitMnDetail = getSqlModel().getSingleResult(
					debitMnQuery);
			HashMap<String, String> mapDrDetail12 = new HashMap<String, String>();

			// Consolidated Arrears Start
			String consolidatedArrearsCRQuery = "";
			

			String consolidatedArrearsDRQuery = "";
			

			// if (!bean.getCreditCode().equals("")) {
			if (bean.getCheckConsolidatedArrears().equals("true")) {
				consolidatedArrearsCRQuery = " SELECT HRMS_ARREARS_CREDIT_"+ year+".ARREARS_CREDIT_CODE," +
						" SUM(CASE WHEN HRMS_ARREARS_CREDIT_"+year+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_CREDIT_"+ year+".ARREARS_AMT ELSE HRMS_ARREARS_CREDIT_"+ year+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ year+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_CREDIT_"+ year+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"
						+ year
						+ ".ARREARS_CODE) "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN( "
						+ bean.getDivCode()
						+ " ) "
						+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_CREDIT_"+ year+".ARREARS_AMT>0     ";

				consolidatedArrearsCRQuery += whereClause;
				
				consolidatedArrearsCRQuery += "GROUP BY ARREARS_CREDIT_CODE ,ARREARS_PAID_MONTH, ARREARS_YEAR ";

				consolidatedArrearsDRQuery = " SELECT HRMS_ARREARS_DEBIT_"+ year+".ARREARS_DEBIT_CODE, " +
						"SUM( CASE WHEN HRMS_ARREARS_DEBIT_"+ year+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_DEBIT_"+ year+".ARREARS_AMT ELSE  HRMS_ARREARS_DEBIT_"+ year+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+ year+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_DEBIT_"+ year+ " "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"
						+ year
						+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN( "
						+ bean.getDivCode()
						+ " ) "
						+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_DEBIT_"+ year+".ARREARS_AMT>0     ";

				consolidatedArrearsDRQuery += whereClause;
				
				consolidatedArrearsDRQuery += "GROUP BY ARREARS_DEBIT_CODE ,ARREARS_PAID_MONTH, ARREARS_YEAR  ";

			}

			Object[][] consolidatedArrearsCRDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsCRQuery);
			

			Object[][] consolidatedArrearsDRDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsDRQuery);
			
			
			////////////////
			

			if (expDetail != null && expDetail.length > 0) {

				for (int i = 0; i < CRDetail.length; i++) {
					creditMap.put(String.valueOf(CRDetail[i][0]) , String
							.valueOf(CRDetail[i][1]));
				}

				for (int ii = 0; ii < consolidatedArrearsCRDetail.length; ii++) {
					creditArrMap.put(String.valueOf(consolidatedArrearsCRDetail[ii][0]) , String
							.valueOf(consolidatedArrearsCRDetail[ii][1]));
				}
				
				if (debitMnDetail != null && debitMnDetail.length > 0) {
					for (int i = 0; i < debitMnDetail.length; i++) {
						System.out.println("debitMnDetail[i][0]:"+debitMnDetail[i][0]+"  ###:"+debitMnDetail[i][1]);
						mapDrDetail12.put(String.valueOf(debitMnDetail[i][0]),String.valueOf(debitMnDetail[i][1]));
						
					}
				}

				HashMap<String, String> mapDrDetailArr = new HashMap<String, String>();
				if (consolidatedArrearsDRDetail != null && consolidatedArrearsDRDetail.length > 0) {
					for (int i = 0; i < consolidatedArrearsDRDetail.length; i++) {
						mapDrDetailArr.put(String.valueOf(consolidatedArrearsDRDetail[i][0]),
								String.valueOf(consolidatedArrearsDRDetail[i][1]));
					}
				}
				
				
				
				String creditTypeQuery = "";
				String debitTypeQuery = "";
				Object[][] creditDetail = null;
				Object[][] debitDetail = null;
					creditTypeQuery = "SELECT  DISTINCT  HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD " +
							"INNER JOIN HRMS_SAL_CREDITS_"+ year + " ON(HRMS_SAL_CREDITS_"+ year + ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)" +
							"INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ year + ".SAL_LEDGER_CODE)" +
							" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+ " ) " +
							" AND HRMS_SAL_CREDITS_"+ year + ".SAL_MONTH="+month+" AND HRMS_SAL_CREDITS_"+ year + ".SAL_AMOUNT>0 ";
					
					
					creditDetail = getSqlModel().getSingleResult(
							creditTypeQuery);
					

					debitTypeQuery = "SELECT DISTINCT  HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD " +
					" INNER JOIN HRMS_SAL_DEBITS_"+ year + " ON(HRMS_SAL_DEBITS_"+ year + ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)" +
					"INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ year + ".SAL_LEDGER_CODE)" +
							" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+ " ) " +
							" AND HRMS_SAL_DEBITS_"+ year + ".SAL_MONTH ="+month+" AND HRMS_SAL_DEBITS_"+ year + ".SAL_AMOUNT>0 ";
				//	debitTypeQuery += " ORDER BY DEBIT_CODE ";
					// for credit type
					debitDetail = getSqlModel().getSingleResult(debitTypeQuery);
					
					
				// for emp type
				Object[][] empTypeDetail = null;

					empTypeDetail = getSqlModel().getSingleResult(
							creditTypeQuery);

				Object[][] objTotalTabularData = null;
					if(creditDetail.length>debitDetail.length){
						objTotalTabularData = new Object[creditDetail.length][header.length];
					}
					else{
						objTotalTabularData = new Object[debitDetail.length][header.length];
					}

			

				Object[][] totalByColumn = new Object[1][header.length];

				int countSr = 1;

				
				if (empTypeDetail != null && empTypeDetail.length > 0) {
					int MAINCOUNT = 0;
					
					
						double valueCreditArrearsTotal = 0.0;
						
						for (int i = 0; i < creditDetail.length; i++) {
							
							
							//objTotalTabularData[MAINCOUNT][0] = countSr++;
							objTotalTabularData[MAINCOUNT][0] = String
									.valueOf(empTypeDetail[i][1]);
							objTotalTabularData[MAINCOUNT][1] = 0.0;

							//objTotalTabularData[MAINCOUNT][3] = "0.0";

						/*	if(CRDetail!=null && CRDetail.length>0){
								for (int j = 0; j < CRDetail.length; j++) {
									if(String.valueOf(creditDetail[i][0]).equals(String.valueOf(CRDetail[j][0]))){
										objTotalTabularData[MAINCOUNT][1] =formatter.format(Double.parseDouble(String.valueOf(CRDetail[j][1])));
									}
								}
							}*/
							if(creditMap!=null && creditMap.size()>0){
								String values=creditMap.get(String.valueOf(creditDetail[i][0]));
								if(values!=null && values.length()>0){
									objTotalTabularData[MAINCOUNT][1] =formatter.format(Double.parseDouble(values));	
								}
							}
							
							
							if (bean.getCheckConsolidatedArrears().equals(
							"true")) {
								/*if(consolidatedArrearsCRDetail!=null && consolidatedArrearsCRDetail.length>0){
									for (int j = 0; j < consolidatedArrearsCRDetail.length; j++) {
										if(String.valueOf(creditDetail[i][0]).equals(String.valueOf(consolidatedArrearsCRDetail[j][0]))){
											objTotalTabularData[MAINCOUNT][1] =formatter.format(Double.parseDouble(String.valueOf(CRDetail[j][1]))+Double.parseDouble(String.valueOf(consolidatedArrearsCRDetail[j][1])));
										}
									}
								}*/
								
								if(creditArrMap!=null && creditArrMap.size()>0){
									String values=creditArrMap.get(String.valueOf(creditDetail[i][0]));
									if(values!=null && values.length()>0){
										objTotalTabularData[MAINCOUNT][1] =formatter.format(Double.parseDouble(values)+Double.parseDouble(String.valueOf(objTotalTabularData[MAINCOUNT][1])));	
									}
								}	

					}
							
							// Total Credit
							totalByColumn[0][1] = formatter.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(totalByColumn[0][1])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objTotalTabularData[MAINCOUNT][1]))));
							

							valueCreditArrearsTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(totalByColumn[0][3])));
							/*totalByColumn[0][3] = Utility
									.twoDecimals(Double
											.parseDouble(String
													.valueOf(objTotalTabularData[MAINCOUNT][2]))
											);*/

						

						
							MAINCOUNT++;
							
						}
						
					
						 MAINCOUNT = 0;
						for (int i = 0; i < debitDetail.length; i++) {
							
							
							
							
							objTotalTabularData[MAINCOUNT][3] = String
									.valueOf(debitDetail[i][1]);
							//objTotalTabularData[MAINCOUNT][count] = 0.0;
							objTotalTabularData[MAINCOUNT][4] = "0.0";
							
							
							/*if(debitMnDetail!=null && debitMnDetail.length>0){
								for (int j = 0; j < debitMnDetail.length; j++) {
									if(String.valueOf(debitDetail[i][0]).equals(String.valueOf(debitMnDetail[j][0]))){
										objTotalTabularData[MAINCOUNT][4] =formatter.format(Double.parseDouble(String.valueOf(debitMnDetail[j][1])));
									}
								}
							}*/
							if(mapDrDetail12!=null && mapDrDetail12.size()>0){
								System.out.println("key:::"+debitDetail[i][0]);
								String values=mapDrDetail12.get(String.valueOf(debitDetail[i][0]));
								System.out.println("values:::"+values);
								if(values!=null && values.length()>0){
									objTotalTabularData[MAINCOUNT][4] =formatter.format(Double.parseDouble(values));	
								}
							}
							
							if (bean.getCheckConsolidatedArrears().equals(
							"true")) {
								/*if(consolidatedArrearsDRDetail!=null && consolidatedArrearsDRDetail.length>0){
									for (int j = 0; j < consolidatedArrearsDRDetail.length; j++) {
										if(String.valueOf(debitDetail[i][0]).equals(String.valueOf(consolidatedArrearsDRDetail[j][0]))){
											objTotalTabularData[MAINCOUNT][4] =formatter.format(Double.parseDouble(String.valueOf(debitMnDetail[j][1]))+Double.parseDouble(String.valueOf(consolidatedArrearsDRDetail[j][1])));
										}
									}
								}*/
								
										
								if(mapDrDetailArr!=null && mapDrDetailArr.size()>0){
									System.out.println("key11:::"+debitDetail[i][0]);
									String values=mapDrDetailArr.get(String.valueOf(debitDetail[i][0]));
									if(values!=null && values.length()>0){
										objTotalTabularData[MAINCOUNT][4] =formatter.format(Double.parseDouble(values)+Double.parseDouble(String.valueOf(objTotalTabularData[MAINCOUNT][4])));	
									}
								}

					}
							// Total Debit
							totalByColumn[0][4] = formatter.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(totalByColumn[0][4])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objTotalTabularData[MAINCOUNT][4]))));
							

						

					
							
							MAINCOUNT++;

						}
						
						/*totalByColumn[0][4] =  Utility
						.twoDecimals(Double
						.parseDouble(checkNullValue(String
								.valueOf(totalByColumn[0][2])))
						- Double.parseDouble(checkNullValue(String
								.valueOf(totalByColumn[0][3]))));*/

				

					TableDataSet tdstable = new TableDataSet();
					tdstable.setHeader(header);
					tdstable.setData(objTotalTabularData);
					//tdstable.setHeaderFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					tdstable.setHeaderBorderDetail(3);
					//tdstable.setHeaderLines(true);
					//tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
					tdstable.setCellAlignment(bcellAlign);
					tdstable.setCellWidth(bcellWidth);
					tdstable.setCellNoWrap(bcellwrap);
					tdstable.setBorderDetail(3);
					tdstable.setHeaderTable(true);
					//tdstable.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					//tdstable.setHeaderBGColor(new BaseColor(200, 200, 200));
					 tdstable.setBlankRowsAbove(1);
					rg.addTableToDoc(tdstable);
					
					totalByColumn[0][0] = "Total Earnings:";
					totalByColumn[0][2] = " ";
					totalByColumn[0][3] = "Total Deductions:";
					TableDataSet netEarningDataSet = new TableDataSet();
					netEarningDataSet.setData(totalByColumn);
					//netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
					//		10, 11, 12, 13, 14 });
					netEarningDataSet.setCellWidth(bcellWidth);
					netEarningDataSet.setCellAlignment(bcellAlign);
					netEarningDataSet.setBlankRowsAbove(1);
					netEarningDataSet.setBodyFontStyle(1);
					//netEarningDataSet.setBorderDetail(2);
				//	netEarningDataSet.isTotalCol();
					netEarningDataSet.setBorderLines(true);
					rg.addTableToDoc(netEarningDataSet);
					
				}

				

				/*String footerQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME "
						+ " FROM HRMS_EMP_OFFC "
						+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ "WHERE EMP_ID=" + bean.getUserEmpId();

				// for credit emp type
				Object[][] footerDetail = getSqlModel().getSingleResult(
						footerQuery);*/
				
				
				String countEmpQuery = "SELECT COUNT(*) FROM HRMS_SALARY_"+ year + " WHERE SAL_DIVISION IN( " + bean.getDivCode()+")";
				if(!bean.getBranchCode().equals("")){
					countEmpQuery+=" AND SAL_EMP_CENTER ="+bean.getBranchCode()+"  "; // Branch
				}
				
				if(!bean.getDeptCode().equals("")){
					countEmpQuery+=" AND SAL_DEPT ="+bean.getDeptCode()+"  "; // Department
				}
				
				if(!bean.getPaybillId().equals("")){
					countEmpQuery+=" AND SAL_EMP_PAYBILL ="+bean.getPaybillId()+"  "; // Paybill
				}
				
				if(!bean.getTypeCode().equals("")){
					countEmpQuery+=" AND SAL_EMP_TYPE ="+bean.getTypeCode()+"  "; // Employee Type
				}

			// for credit emp type
			Object[][] countEmpDetail = getSqlModel().getSingleResult(
					countEmpQuery);
			
			

				Object footerObj[][] = new Object[1][2];

				footerObj[0][0] = " Net Total ( Total Credits - Total Debits ):";
				footerObj[0][1] = formatter.format(Double
						.parseDouble(checkNullValue(String
								.valueOf(totalByColumn[0][1])))
						- Double.parseDouble(checkNullValue(String
								.valueOf(totalByColumn[0][4]))));

				/*footerObj[1][0] = "Generated By:";
				footerObj[1][1] = "" + String.valueOf(footerDetail[0][3])
						+ " on "
						+ DateFormat.getDateTimeInstance().format(date);*/

				TableDataSet footerDataSet = new TableDataSet();
				footerDataSet.setData(footerObj);
				footerDataSet.setCellWidth(new int[] { 41, 59 });
				footerDataSet.setCellAlignment(new int[] { 0, 2 });
				footerDataSet.setBodyFontStyle(1);
				footerDataSet.setBodyBGColor(new BaseColor(200,200,200));
				// signDataSet.setBlankRowsAbove(3);
				footerDataSet.setBlankRowsBelow(1);
				footerDataSet.setBorder(false);
				
				//footerDataSet.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
				rg.addTableToDoc(footerDataSet);

				/*TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][]{{"Generated By : "+String.valueOf(footerDetail[0][1])+" on "+date.toString()}});
				generatedByData.setCellAlignment(new int[]{0});
				generatedByData.setCellWidth(new int[]{100});
				//generatedByData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
				generatedByData.setBorderDetail(0);
				//generatedByData.setBorderDetail(1);
				//generatedByData.setHeaderTable(true);
			//	generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);
				Vector lineVector=new Vector();
				lineVector.add(new BaseColor(0, 0, 0));
				lineVector.add(Rectangle.BOTTOM);
				rg.addLine(lineVector);*/
				
				
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				
				//noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			//rg.process();
		//	rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}
