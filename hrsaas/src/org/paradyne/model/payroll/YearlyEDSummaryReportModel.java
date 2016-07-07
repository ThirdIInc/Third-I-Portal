package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.YearlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;



import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author aa1385 Date:30-11-2011
 */
public class YearlyEDSummaryReportModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * report type Pdf.
	 */
	public static final String REPORT_TYPE_PDF = "Pdf";
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(YearlyEDSummaryReportModel.class);

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

	public void getReport(YearlyEDSummaryReportBean bean,
			HttpServletResponse response, HttpServletRequest request,
			String checkBoxVar, String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			Date date = new Date();
			final String fromYear = bean.getFromYear(); // From Year

			final String toYear = bean.getToYear(); // To year
			String type = bean.getReport();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = "Yearly Earning-Deduction Summary "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Yearly Earning-Deduction Summary");
			
			rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(15);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "YearlyEDSummaryReport_input.action");
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
	
private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, YearlyEDSummaryReportBean bean) {
		
		try {
			
			/*
			 * // For Report heading TableDataSet repTitle = new TableDataSet();
			 * Object[][] repTitleObj = new Object[1][1]; repTitleObj[0][0] =
			 * title; repTitle.setData(repTitleObj);
			 * repTitle.setCellAlignment(new int[] { 1 });
			 * repTitle.setCellWidth(new int[] { 100 });
			 * repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new
			 * Color(0, 0, 0)); repTitle.setBorder(false);
			 * repTitle.setBlankRowsBelow(1); rg.addTableToDoc(repTitle);
			 */
			String title = " Yearly Earning-Deduction Summary  ";
			Date date = new Date();
			final String fromYear = bean.getFromYear(); // From Year

			final String toYear = bean.getToYear(); // To year
			
			/*Object[][] nameObj = new Object[1][1];
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
			titleObj[0][0] = "";

			TableDataSet titleName = new TableDataSet();
			titleName.setData(titleObj);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			titleName.setBorder(false);

			Object[][] subtitleObj = new Object[1][1];
			subtitleObj[0][0] = "";

			TableDataSet subtitleName = new TableDataSet();
			subtitleName.setData(subtitleObj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			subtitleName.setBorder(false);

			Object[][] subtitleObj2 = new Object[1][1];
			subtitleObj2[0][0] = "" + title + " ";

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

			/* Setting filter details */
			String filters = "Financial year : " + fromYear + " - " + toYear;

			if (!bean.getDivName().equals("")) {
				filters += "\n\nDivision : " + bean.getDivName();
			}

			if (!bean.getBranchName().equals("")) {
				filters += "\n\nBranch : " + bean.getBranchName();
			}

			if (!bean.getDeptName().equals("")) {
				filters += "\n\nDepartment : " + bean.getDeptName();
			}

			if (!bean.getPaybillName().equals("")) {
				filters += "\n\nPaybill : " + bean.getPaybillName();
			}

			if (!bean.getTypeName().equals("")) {
				filters += "\n\nEmployee Type : " + bean.getTypeName();
			}
			
			if(!bean.getCostCenterName().equals("")){
				filters+="\n\nCost Center : "+bean.getCostCenterName();
			}
			
			if(!bean.getSubCostCenterName().equals("")){
				filters+="\n\nSub Cost Center : "+bean.getSubCostCenterName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			//filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{16});
			filterData.setCellNoWrap(new boolean[]{false});
			
		//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			/*java.util.Date d = new java.util.Date();
			logger.info("Date :- " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);*/

			

			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			/*
			 * if(!bean.getDivName().equals("")) { whereClause +=" AND
			 * HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivCode();
			 * System.out.println("+bean.getDivId() =" +bean.getDivCode()); }
			 */
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			if (!bean.getEmpName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_ID IN (" + bean.getEmpId()+") ";
				System.out.println("+bean.getEmpCode() =" + bean.getEmpId());
			}
			if (!bean.getCadreName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
						+ bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			if (!bean.getTypeName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_TYPE IN ("
						+ bean.getTypeCode()+") ";
				System.out
						.println("+bean.getTypeCode() =" + bean.getTypeCode());
			}

			if (!bean.getCostCenterName().equals("")) {
				whereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			
			
			if (!bean.getSubCostCenterName().equals("")) {
				whereClause += " AND HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID IN("
						+ bean.getSubCostCenterId()+")";
				System.out
						.println("+bean.getSubCostCenterId() =" + bean.getSubCostCenterId());
			}
			
			// whereClause += " ORDER BY LEDGER_CODE DESC";

			String[] header = null;
			
			header = new String[15];

			header[0] = "Sn.";
			header[1] = "Earning Head";
			//header[2] = " ";
			header[2] = "Apr- " + fromYear + " ";
			header[3] = "May- " + fromYear + " ";
			header[4] = "Jun- " + fromYear + " ";
			header[5] = "Jul- " + fromYear + " ";
			header[6] = "Aug- " + fromYear + " ";
			header[7] = "Sep- " + fromYear + " ";
			header[8] = "Oct- " + fromYear + " ";
			header[9] = "Nov- " + fromYear + " ";
			header[10] = "Dec- " + fromYear + " ";
			header[11] = "Jan- " + toYear + " ";
			header[12] = "Feb- " + toYear + " ";
			header[13] = "Mar- " + toYear + " ";
			header[14] = "Total ";

			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 10;
					bcellwrap[i]=true;
				} else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=false;
				}  /*else if (i == 2) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 5;
				}else if (i == 4) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 40;
				}*/else {
					bcellAlign[i] = 2;
					bcellWidth[i] = 25;
					bcellwrap[i]=true;
				}
			}
			
			
			//DEBIT HEAD
			
			String[] headerDebit = null;
			
			headerDebit = new String[15];

			headerDebit[0] = "Sn";
			headerDebit[1] = "Deduction Head";
			//headerDebit[2] = " ";
			headerDebit[2] = "Apr- " + fromYear + " ";
			headerDebit[3] = "May- " + fromYear + " ";
			headerDebit[4] = "Jun- " + fromYear + " ";
			headerDebit[5] = "Jul- " + fromYear + " ";
			headerDebit[6] = "Aug- " + fromYear + " ";
			headerDebit[7] = "Sep- " + fromYear + " ";
			headerDebit[8] = "Oct- " + fromYear + " ";
			headerDebit[9] = "Nov- " + fromYear + " ";
			headerDebit[10] = "Dec- " + fromYear + " ";
			headerDebit[11] = "Jan- " + toYear + " ";
			headerDebit[12] = "Feb- " + toYear + " ";
			headerDebit[13] = "Mar- " + toYear + " ";
			headerDebit[14] = "Total ";

			int[] bcellAlignDebit = new int[headerDebit.length];
			int[] bcellWidthDebit = new int[headerDebit.length];
			boolean[] bcellDebitwrap = new boolean[headerDebit.length];
			for (int i = 0; i < headerDebit.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlignDebit[i] = 0;
					bcellWidthDebit[i] = 10;
					bcellDebitwrap[i]=false;
				//} else if (i == 1) {
				}else if (i == 1) {
					bcellAlignDebit[i] = 0;
					bcellWidthDebit[i] = 40;
					bcellDebitwrap[i]=true;
				}
				
				else  {
					bcellAlignDebit[i] = 2;
					bcellWidthDebit[i] = 25;
					bcellDebitwrap[i]=true;
				}/*else if (i == 2) {
					bcellAlignDebit[i] = 0;
					bcellWidthDebit[i] = 5;
				} 
				else if (i == 4) {
					bcellAlignDebit[i] = 2;
					bcellWidthDebit[i] = 40;
				} else {
					bcellAlignDebit[i] = 2;
					bcellWidthDebit[i] = 40;
				}*/
			}
			///////////////////
			String ledgerQuery = "";

			// Current Month
			ledgerQuery = " SELECT HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_CREDIT_CODE,SUM(HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_AMOUNT),HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_MONTH,HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_CREDITS_"+ fromYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+fromYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID) "
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( "
					+ bean.getDivCode()
					+ ") "
					+ " ";

			ledgerQuery += whereClause;
			ledgerQuery += " GROUP BY HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_CREDIT_CODE,HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_MONTH,HRMS_SAL_CREDITS_"+ fromYear+ ".SAL_YEAR";

			Object[][] expDetail = getSqlModel().getSingleResult(ledgerQuery);
			HashMap<String, String> map = new HashMap<String, String>();

			String debitMnQuery = "";
			debitMnQuery = " SELECT HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_DEBIT_CODE,SUM(HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_AMOUNT),HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_MONTH,HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_DEBITS_"+ fromYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+fromYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( "
					+ bean.getDivCode()
					+ ") "
					+ " ";

			debitMnQuery += whereClause;
			debitMnQuery += " GROUP BY 	HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_DEBIT_CODE,HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_MONTH,HRMS_SAL_DEBITS_"+ fromYear+ ".SAL_YEAR ";

			Object[][] debitMnDetail = getSqlModel().getSingleResult(
					debitMnQuery);
			HashMap<String, String> mapDrDetail = new HashMap<String, String>();

			String toYearCRQuery = " SELECT HRMS_SAL_CREDITS_"+ toYear+ ".SAL_CREDIT_CODE,SUM(HRMS_SAL_CREDITS_"+ toYear+ ".SAL_AMOUNT),HRMS_SAL_CREDITS_"+ toYear+ ".SAL_MONTH,HRMS_SAL_CREDITS_"+ toYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_CREDITS_"+ toYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ toYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+toYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( "
					+ bean.getDivCode()
					+ ") AND HRMS_SAL_CREDITS_"+ toYear+ ".SAL_MONTH IN(1,2,3) "
					+ " ";
			if(!bean.getSubCostCenterName().equals("")){
				toYearCRQuery = " SELECT HRMS_SAL_CREDITS_"+ toYear+ ".SAL_CREDIT_CODE,SUM(HRMS_SAL_CREDITS_"+ toYear+ ".SAL_AMOUNT),HRMS_SAL_CREDITS_"+ toYear+ ".SAL_MONTH,HRMS_SAL_CREDITS_"+ toYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_CREDITS_"+ toYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ toYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+toYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID) "
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( "
					+ bean.getDivCode()
					+ ") AND HRMS_SAL_CREDITS_"+ toYear+ ".SAL_MONTH IN(1,2,3) "
					+ " ";
			}
			toYearCRQuery += whereClause;
			toYearCRQuery += " GROUP BY HRMS_SAL_CREDITS_"+ toYear+ ".SAL_CREDIT_CODE,HRMS_SAL_CREDITS_"+ toYear+ ".SAL_MONTH,HRMS_SAL_CREDITS_"+ toYear+ ".SAL_YEAR";

			Object[][] toYearCRDetail = getSqlModel().getSingleResult(
					toYearCRQuery);
			HashMap<String, String> mapToCRYear = new HashMap<String, String>();

			String toYearDRQuery = " SELECT HRMS_SAL_DEBITS_"+ toYear+ ".SAL_DEBIT_CODE,SUM(HRMS_SAL_DEBITS_"+ toYear+ ".SAL_AMOUNT),HRMS_SAL_DEBITS_"+ toYear+ ".SAL_MONTH,HRMS_SAL_DEBITS_"+ toYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_DEBITS_"+ toYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ toYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+toYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
				//	+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID) "
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "
					+ bean.getDivCode()
					+ ")  AND HRMS_SAL_DEBITS_"+ toYear+ ".SAL_MONTH IN(1,2,3) "
					+ " ";
			
			if(!bean.getSubCostCenterName().equals("")){
				toYearDRQuery = " SELECT HRMS_SAL_DEBITS_"+ toYear+ ".SAL_DEBIT_CODE,SUM(HRMS_SAL_DEBITS_"+ toYear+ ".SAL_AMOUNT),HRMS_SAL_DEBITS_"+ toYear+ "SAL_MONTH,HRMS_SAL_DEBITS_"+ toYear+ ".SAL_YEAR "
					+ "FROM HRMS_SAL_DEBITS_"+ toYear+ " "
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ toYear+ ".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+toYear+".EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
					+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "
					+ bean.getDivCode()
					+ ") AND HRMS_SAL_DEBITS_"+ toYear+ "SAL_MONTH IN(1,2,3) "
					+ " ";
			}
			
			
			toYearDRQuery += whereClause;
			toYearDRQuery += " GROUP BY HRMS_SAL_DEBITS_"+ toYear+ ".SAL_DEBIT_CODE,HRMS_SAL_DEBITS_"+ toYear+ ".SAL_MONTH,HRMS_SAL_DEBITS_"+ toYear+ ".SAL_YEAR";

			Object[][] toYearDRDetail = getSqlModel().getSingleResult(
					toYearDRQuery);
			HashMap<String, String> mapToDRYear = new HashMap<String, String>();

			// Consolidated Arrears Start
			String consolidatedArrearsCRQuery = "";
			String consolidatedArrearsCRFromQuery = "";

			String consolidatedArrearsDRQuery = "";
			String consolidatedArrearsDRFromQuery = "";

			// if (!bean.getCreditCode().equals("")) {
			if (bean.getCheckConsolidatedArrears().equals("true")) {
				consolidatedArrearsCRQuery = " SELECT HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_CREDIT_CODE," +
						" SUM( CASE WHEN HRMS_ARREARS_CREDIT_"+fromYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_AMT ELSE HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_CREDIT_"+ fromYear	+ " "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ fromYear+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+fromYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					//	+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ")  AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(4,5,6,7,8,9,10,11,12)   "
						+ "  ";
				
				if(!bean.getSubCostCenterName().equals("")){
					consolidatedArrearsCRQuery =  "SELECT HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_CREDIT_CODE, " +
							"SUM(CASE WHEN HRMS_ARREARS_CREDIT_"+fromYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_AMT ELSE HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_YEAR  "
					+ "FROM HRMS_ARREARS_CREDIT_"+ fromYear+" "
					+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ fromYear+ ".ARREARS_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+fromYear+".ARREARS_EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID " +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
					+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
					+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(4,5,6,7,8,9,10,11,12)   "
						+ " ";
				}
				

				consolidatedArrearsCRQuery += whereClause;
				consolidatedArrearsCRQuery += " GROUP BY HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_CREDIT_CODE ,HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ fromYear+".ARREARS_YEAR";
				
				consolidatedArrearsCRFromQuery = " SELECT HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_CREDIT_CODE," +
						" SUM( CASE WHEN HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_AMT ELSE HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_CREDIT_"+ toYear+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ toYear+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					//	+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(1,2,3)    "
						+ " ";

				if(!bean.getSubCostCenterName().equals("")){
					consolidatedArrearsCRFromQuery =  " SELECT HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_CREDIT_CODE," +
							" SUM(CASE WHEN HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_AMT ELSE HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_CREDIT_"+ toYear+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ toYear+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+toYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
								" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(1,2,3)   "
						+ " ";
				}
				
				
				consolidatedArrearsCRFromQuery += whereClause;
				consolidatedArrearsCRFromQuery += " GROUP BY HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_CREDIT_CODE ,HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_CREDIT_"+ toYear+".ARREARS_YEAR ";
				
				consolidatedArrearsDRQuery = " SELECT HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_DEBIT_CODE,  " +
						" SUM( CASE WHEN HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_AMT ELSE HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_DEBIT_"+ fromYear+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ fromYear+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+fromYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					//	+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ") AND  HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(4,5,6,7,8,9,10,11,12)    "
						+ " ";
				
				if(!bean.getSubCostCenterName().equals("")){
					consolidatedArrearsDRQuery =  " SELECT HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_DEBIT_CODE, " +
							"SUM( CASE WHEN HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_AMT ELSE HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_DEBIT_"+ fromYear+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ fromYear+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+fromYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
								" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(4,5,6,7,8,9,10,11,12)   "
						+ " ";
				}
				

				consolidatedArrearsDRQuery += whereClause;
				consolidatedArrearsDRQuery += " GROUP BY HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_DEBIT_CODE ,HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+ fromYear+".ARREARS_YEAR  ";
				
				consolidatedArrearsDRFromQuery = " SELECT HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_DEBIT_CODE, " +
						"SUM( CASE WHEN HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_AMT ELSE HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_YEAR  "
						+ "FROM HRMS_ARREARS_DEBIT_"+ toYear+" "
						+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ toYear	+ ".ARREARS_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_EMP_ID) "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						//+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID) "
						+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
						+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(1,2,3)   "
						+ "  ";
				
				if(!bean.getSubCostCenterName().equals("")){
					consolidatedArrearsDRFromQuery =" SELECT HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_DEBIT_CODE," +
							" SUM(CASE WHEN HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_PAY_TYPE='R' THEN -HRMS_ARREARS_DEBIT_"+ toYear+".ARREARS_AMT ELSE HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_AMT END),HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_YEAR  "
					+ "FROM HRMS_ARREARS_DEBIT_"+toYear+" "
					+ "INNER JOIN  HRMS_ARREARS_LEDGER ON ( HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ toYear+ ".ARREARS_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
					+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID" +
							" AND HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID ) "
					+ "WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN ( "
					+ bean.getDivCode()
						+ ") AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH IN(1,2,3)   "
						+ " ";
				}
				
				consolidatedArrearsDRFromQuery += whereClause;
				consolidatedArrearsDRFromQuery += " GROUP BY HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_DEBIT_CODE ,HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH, HRMS_ARREARS_DEBIT_"+toYear+".ARREARS_YEAR  ";
				
			}

			Object[][] consolidatedArrearsCRDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsCRQuery);
			HashMap<String, String> mapConsolidatedCRArrears = new HashMap<String, String>();

			Object[][] consolidatedArrearsCRFromDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsCRFromQuery);
			HashMap<String, String> mapConsolidatedCRFromArrears = new HashMap<String, String>();

			Object[][] consolidatedArrearsDRDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsDRQuery);
			HashMap<String, String> mapConsolidatedDRArrears = new HashMap<String, String>();

			Object[][] consolidatedArrearsDRFromDetail = getSqlModel()
					.getSingleResult(consolidatedArrearsDRFromQuery);
			HashMap<String, String> mapConsolidatedDRFromArrears = new HashMap<String, String>();

			// /Consolidated arrears end
			boolean ARREAR_FLAG = false;
			int ARREAR_COUNT = 1;
			if (bean.getCheckConsolidatedArrears().equals("true")) {
				ARREAR_FLAG = true;
				ARREAR_COUNT = 2;
			}

			//if (expDetail != null && expDetail.length > 0) {

				for (int i = 0; i < expDetail.length; i++) {
					map.put(String.valueOf(expDetail[i][0]) + "#"
							+ String.valueOf(expDetail[i][2]), String
							.valueOf(expDetail[i][1]));
				}

				if (debitMnDetail != null && debitMnDetail.length > 0) {
					for (int i = 0; i < debitMnDetail.length; i++) {
						mapDrDetail.put(String.valueOf(debitMnDetail[i][0])
								+ "#" + String.valueOf(debitMnDetail[i][2]),
								String.valueOf(debitMnDetail[i][1]));
					}
				}

				if (toYearCRDetail != null && toYearCRDetail.length > 0) {
					for (int i = 0; i < toYearCRDetail.length; i++) {
						mapToCRYear.put(String.valueOf(toYearCRDetail[i][0])
								+ "#" + String.valueOf(toYearCRDetail[i][2]),
								String.valueOf(toYearCRDetail[i][1]));
					}
				}

				if (toYearDRDetail != null && toYearDRDetail.length > 0) {
					for (int i = 0; i < toYearDRDetail.length; i++) {
						mapToDRYear.put(String.valueOf(toYearDRDetail[i][0])
								+ "#" + String.valueOf(toYearDRDetail[i][2]),
								String.valueOf(toYearDRDetail[i][1]));
					}
				}

				if (bean.getCheckConsolidatedArrears().equals("true")) {
					for (int i = 0; i < consolidatedArrearsCRDetail.length; i++) {
						mapConsolidatedCRArrears
								.put(
										String
												.valueOf(consolidatedArrearsCRDetail[i][0])
												+ "#"
												+ String
														.valueOf(consolidatedArrearsCRDetail[i][2]),
										String
												.valueOf(consolidatedArrearsCRDetail[i][1]));
					}

					for (int i = 0; i < consolidatedArrearsCRFromDetail.length; i++) {
						mapConsolidatedCRFromArrears
								.put(
										String
												.valueOf(consolidatedArrearsCRFromDetail[i][0])
												+ "#"
												+ String
														.valueOf(consolidatedArrearsCRFromDetail[i][2]),
										String
												.valueOf(consolidatedArrearsCRFromDetail[i][1]));
					}

					for (int i = 0; i < consolidatedArrearsDRDetail.length; i++) {
						mapConsolidatedDRArrears
								.put(
										String
												.valueOf(consolidatedArrearsDRDetail[i][0])
												+ "#"
												+ String
														.valueOf(consolidatedArrearsDRDetail[i][2]),
										String
												.valueOf(consolidatedArrearsDRDetail[i][1]));
					}

					for (int i = 0; i < consolidatedArrearsDRFromDetail.length; i++) {
						mapConsolidatedDRFromArrears
								.put(
										String
												.valueOf(consolidatedArrearsDRFromDetail[i][0])
												+ "#"
												+ String
														.valueOf(consolidatedArrearsDRFromDetail[i][2]),
										String
												.valueOf(consolidatedArrearsDRFromDetail[i][1]));
					}

				}
				String creditTypeQuery = "";
				String debitTypeQuery = "";
				Object[][] creditDetail = null;
				Object[][] debitDetail = null;

				creditTypeQuery = "SELECT  DISTINCT  HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD " +
					"INNER JOIN HRMS_SAL_CREDITS_"+ fromYear + " ON(HRMS_SAL_CREDITS_"+ fromYear + ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)" +
					" INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ fromYear + ".SAL_LEDGER_CODE) " +
					" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+" ) AND HRMS_SAL_CREDITS_"+ fromYear + ".SAL_MONTH in(4,5,6,7,8,9,10,11,12) AND HRMS_SAL_CREDITS_"+ fromYear + ".SAL_AMOUNT>0  union "+
					"SELECT  DISTINCT  HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD " +
					"INNER JOIN HRMS_SAL_CREDITS_"+ toYear + " ON(HRMS_SAL_CREDITS_"+ toYear + ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)" +
					" INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+ toYear + ".SAL_LEDGER_CODE) " +
					" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+" ) AND HRMS_SAL_CREDITS_"+ toYear + ".SAL_MONTH in(1,2,3) AND HRMS_SAL_CREDITS_"+ toYear + ".SAL_AMOUNT>0   ";
				
				
				// creditTypeQuery += " WHERE LEAVE_ID=" + expDetail[0][0];
				// for credit type

				creditDetail = getSqlModel().getSingleResult(creditTypeQuery);

				debitTypeQuery = "SELECT DISTINCT  HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD " +
					" INNER JOIN HRMS_SAL_DEBITS_"+ fromYear + " ON(HRMS_SAL_DEBITS_"+ fromYear + ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)" +
					" INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ fromYear + ".SAL_LEDGER_CODE) " +
							" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+" ) AND HRMS_SAL_DEBITS_"+ fromYear + ".SAL_MONTH in(4,5,6,7,8,9,10,11,12) AND HRMS_SAL_DEBITS_"+ fromYear + ".SAL_AMOUNT>0  union "+ 
							"SELECT DISTINCT  HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD " +
							" INNER JOIN HRMS_SAL_DEBITS_"+ toYear + " ON(HRMS_SAL_DEBITS_"+ toYear + ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)" +
							" INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+ toYear + ".SAL_LEDGER_CODE) " +
									" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+" ) AND HRMS_SAL_DEBITS_"+ toYear + ".SAL_MONTH in(1,2,3) AND HRMS_SAL_DEBITS_"+ toYear + ".SAL_AMOUNT>0 ";
				// for credit type
				debitDetail = getSqlModel().getSingleResult(debitTypeQuery);

				// for emp type
				Object[][] empTypeDetail = null;

				empTypeDetail = getSqlModel().getSingleResult(creditTypeQuery);
				
				Object[][] empTypeDebitDetail = null;

				empTypeDebitDetail = getSqlModel().getSingleResult(debitTypeQuery);

				Object[][] objTotalTabularData = null;
				
				Object[][] objDebitTotalTabularData = null;

				
				
				int countSr = 1;
				int countDebit = 1;

				Object netEarningObj[][] = new Object[1][header.length];
				
				Object netEarningDebitObj[][] = new Object[1][header.length];
				
				
				Object netEarningTotalObj[][] = new Object[1][header.length];

				
				int[] cellWidthDateHeader = { 100 };
				int[] cellAlignDateHeader = { 0 };
				
				if (empTypeDetail != null && empTypeDetail.length > 0 && creditDetail != null && creditDetail.length>0) {

					
					objTotalTabularData = new Object[creditDetail.length][header.length];					
					
					Object[][] summaryData = new Object[1][1];
					summaryData[0][0] = "Earning Summary " ;
					
					TableDataSet tableheadingDateData = new TableDataSet();
					tableheadingDateData.setData(summaryData);
					tableheadingDateData.setCellWidth(cellWidthDateHeader);
					tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
					tableheadingDateData.setBodyFontStyle(1);  
					tableheadingDateData.setCellColSpan(new int[]{16});
				//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					tableheadingDateData.setBorderDetail(0);
					//tableheadingDateData.setHeaderTable(true);
					 tableheadingDateData.setBlankRowsAbove(1);
					rg.addTableToDoc(tableheadingDateData);
					
					
					int MAINCOUNT = 0;

					for (int i = 0; i < creditDetail.length; i++) {
						int count = 2;
						objTotalTabularData[MAINCOUNT][0] = countSr++;
						objTotalTabularData[MAINCOUNT][1] = String
								.valueOf(creditDetail[i][1]);// CREDTHEAD
						objTotalTabularData[MAINCOUNT][2] = " ";// CR
						objTotalTabularData[MAINCOUNT][count] = "0.00";
						double totalSum = 0.0;

						for (int K = 4; K <= 12; K++) {

							String value = map.get(String
									.valueOf(creditDetail[i][0])
									+ "#" + K);

							String valueConsolidatedArears = mapConsolidatedCRArrears
									.get(String.valueOf(creditDetail[i][0])
											+ "#" + K);

							objTotalTabularData[MAINCOUNT][count] = "0.00";
							if (value != null) {
								objTotalTabularData[MAINCOUNT][count] = formatter.format(Double.parseDouble(value));

							}

							if (valueConsolidatedArears != null) {
								objTotalTabularData[i][count] = formatter.format(Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[i][count])))
										+ Double
												.parseDouble(valueConsolidatedArears));
							}

							totalSum += Double
									.parseDouble(checkNullValue(String
											.valueOf(objTotalTabularData[MAINCOUNT][count])));

							// Net Earnings
							netEarningObj[0][count] = formatter
											.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(netEarningObj[0][count])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objTotalTabularData[MAINCOUNT][count]))));

							count++;
						}

						for (int K = 1; K <= 3; K++) {

							String value = mapToCRYear.get(String
									.valueOf(creditDetail[i][0])
									+ "#" + K);

							String valueConsolidatedArears = mapConsolidatedCRFromArrears
									.get(String.valueOf(creditDetail[i][0])
											+ "#" + K);

							objTotalTabularData[MAINCOUNT][count] = "0.00";
							if (value != null) {
								objTotalTabularData[MAINCOUNT][count] = formatter.format(Double.parseDouble(value));
							
							}

							if (valueConsolidatedArears != null) {
								objTotalTabularData[MAINCOUNT][count] = formatter.format(Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][count])))
										+ Double
												.parseDouble(valueConsolidatedArears));
							}

							totalSum += Double
									.parseDouble(checkNullValue(String
											.valueOf(objTotalTabularData[MAINCOUNT][count])));

							// Net Earnings
							netEarningObj[0][count] = formatter
											.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(netEarningObj[0][count])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objTotalTabularData[MAINCOUNT][count]))));

							count++;
						}

						// Total
						objTotalTabularData[MAINCOUNT][14] = formatter
										.format(totalSum);
						
						netEarningObj[0][14]=formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningObj[0][14])))+Double.parseDouble(String.valueOf(objTotalTabularData[MAINCOUNT][14])));
						MAINCOUNT++;

					}

					// Table for Credit Details start
					TableDataSet tdstable = new TableDataSet();
					//tdstable.setHeaderFontSize(-1);
					
					tdstable.setHeader(header);
					tdstable.setHeaderBorderDetail(3);
					//tdstable.setHeaderFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				//	tdstable.setHeaderLines(true);
					//tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
					//tdstable.setHeaderBGColor(new BaseColor(180,180,180));
					tdstable.setCellNoWrap(bcellwrap);
					tdstable.setData(objTotalTabularData);
					// tdstable.setColumnSum(new int[]{4,5,6,7,8,9,10,11,12,13});
					tdstable.setCellAlignment(bcellAlign);
					tdstable.setCellWidth(bcellWidth);
					tdstable.setBorderDetail(3);
					tdstable.setHeaderTable(true);
				//	tdstable.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				//	tdstable.setHeaderBGColor(new BaseColor(200, 200, 200));
					// tdstable.setBlankRowsBelow(1);
					rg.addTableToDoc(tdstable);
					/*Vector lineVector=new Vector();
					lineVector.add(new BaseColor(0, 0, 0));
					lineVector.add(Rectangle.TOP);*/
					//rg.seperatorLine();
					// Table for Credit Details end
					System.out.println("netEarningObj[0][14]::"+netEarningObj[0][14]);
					netEarningObj[0][1] = "Total Earnings :";
					//netEarningObj[1][0] = "Net Deductions :";
				//	netEarningObj[2][0] = "Net Payable :";
					TableDataSet netEarningDataSet = new TableDataSet();
					netEarningDataSet.setData(netEarningObj);
					/*netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
							10, 11, 12, 13, 14 });*/
					netEarningDataSet.setCellWidth(bcellWidth);
					netEarningDataSet.setCellAlignment(bcellAlign);
					netEarningDataSet.setBodyFontStyle(1);
					//netEarningDataSet.setBodyBGColor(new BaseColor(200,200,200));
					netEarningDataSet.setBorderLines(true);
					//netEarningDataSet.setBlankRowsAbove(1);
					netEarningDataSet.setBlankRowsBelow(1);
					netEarningDataSet.setCellNoWrap(bcellwrap);
									
					netEarningDataSet.setBorderDetail(0);
				//	netEarningDataSet.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));

					rg.addTableToDoc(netEarningDataSet);
					rg.addProperty(rg.PAGE_BREAK);
				

				}
				else {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "Earning Summary : No records available";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 0 });
					noData.setCellWidth(new int[] { 100 });
				///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}
				
				if (empTypeDetail != null && empTypeDetail.length > 0 && debitDetail != null && debitDetail.length>0) {
					
					objDebitTotalTabularData = new Object[debitDetail.length][headerDebit.length];
					
					Object[][] dedSummaryData = new Object[1][1];
					dedSummaryData[0][0] = "Deduction Summary " ;
					//int[] cellWidthDateHeader = { 100 };
					//int[] cellAlignDateHeader = { 0 };
					TableDataSet deductionDateData = new TableDataSet();
					deductionDateData.setData(dedSummaryData);
					deductionDateData.setCellWidth(cellWidthDateHeader);
					deductionDateData.setCellAlignment(cellAlignDateHeader);  
					deductionDateData.setBodyFontStyle(1);  
					deductionDateData.setCellColSpan(new int[]{16});
				//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
					deductionDateData.setBorderDetail(0);
					//tableheadingDateData.setHeaderTable(true);
					deductionDateData.setBlankRowsAbove(1);
					rg.addTableToDoc(deductionDateData);
					
					
					
					int MAINCOUNT = 0;
					for (int i = 0; i < debitDetail.length; i++) {
						int count = 2;
						objDebitTotalTabularData[MAINCOUNT][0] = countDebit++;
						objDebitTotalTabularData[MAINCOUNT][1] = String
								.valueOf(debitDetail[i][1]);// DEBIT HEAD
						objDebitTotalTabularData[MAINCOUNT][2] = " ";// 

						double totalSum = 0.0;

						for (int K = 4; K <= 12; K++) {

							String value = mapDrDetail.get(String
									.valueOf(debitDetail[i][0])
									+ "#" + K);

							String valueConsolidatedArears = mapConsolidatedDRArrears
									.get(String.valueOf(debitDetail[i][0])
											+ "#" + K);

							objDebitTotalTabularData[MAINCOUNT][count] = "0.00";
							if (value != null) {
								objDebitTotalTabularData[MAINCOUNT][count] = formatter.format(Double.parseDouble(value));
							}

							if (valueConsolidatedArears != null) {
								objDebitTotalTabularData[MAINCOUNT][count] = Double
										.parseDouble(checkNullValue(String
												.valueOf(objDebitTotalTabularData[MAINCOUNT][count])))
										+ Double
												.parseDouble(valueConsolidatedArears);
								
							}

							totalSum += Double
									.parseDouble(checkNullValue(String
											.valueOf(objDebitTotalTabularData[MAINCOUNT][count])));

							// Net Earnings
							netEarningDebitObj[0][count] = formatter
											.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(netEarningDebitObj[0][count])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objDebitTotalTabularData[MAINCOUNT][count]))));

							count++;
						}

						for (int K = 1; K <= 3; K++) {

							String value = mapToDRYear.get(String
									.valueOf(debitDetail[i][0])
									+ "#" + K);

							String valueConsolidatedArears = mapConsolidatedDRFromArrears
									.get(String.valueOf(debitDetail[i][0])
											+ "#" + K);

							objDebitTotalTabularData[MAINCOUNT][count] = "0.00";
							if (value != null) {
								objDebitTotalTabularData[MAINCOUNT][count] = formatter.format(Double.parseDouble(value));

							}

							if (valueConsolidatedArears != null) {
								objDebitTotalTabularData[MAINCOUNT][count] = formatter.format(Double
										.parseDouble(checkNullValue(String
												.valueOf(objDebitTotalTabularData[MAINCOUNT][count])))
										+ Double
												.parseDouble(valueConsolidatedArears));
							}

							totalSum += Double
									.parseDouble(checkNullValue(String
											.valueOf(objDebitTotalTabularData[MAINCOUNT][count])));

							// Net Earnings
							netEarningDebitObj[0][count] = formatter
											.format(Double
											.parseDouble(checkNullValue(String
													.valueOf(netEarningDebitObj[0][count])))
											+ Double
													.parseDouble(checkNullValue(String
															.valueOf(objDebitTotalTabularData[MAINCOUNT][count]))));

							count++;
						}

						// Total
						objDebitTotalTabularData[MAINCOUNT][14] = formatter
						.format(totalSum);

						netEarningDebitObj[0][14]=formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningDebitObj[0][14])))+Double.parseDouble(String.valueOf(objDebitTotalTabularData[MAINCOUNT][14])));
						
						MAINCOUNT++;
					}
					
					
					// Table for Debit Details start
					
					TableDataSet tdstableDebit = new TableDataSet();
					//tdstable.setHeaderFontSize(-1);
					tdstableDebit.setHeader(headerDebit);
					tdstableDebit.setHeaderBorderDetail(3);
					//tdstable.setHeaderFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				//	tdstableDebit.setHeaderLines(true);
					tdstableDebit.setCellNoWrap(bcellDebitwrap);
					//tdstableDebit.setHeaderBorderColor(new BaseColor(255,0,0));
					//tdstableDebit.setHeaderBGColor(new BaseColor(180,180,180));
					tdstableDebit.setData(objDebitTotalTabularData);
					// tdstable.setColumnSum(new int[]{4,5,6,7,8,9,10,11,12,13});
					tdstableDebit.setCellAlignment(bcellAlignDebit);
					tdstableDebit.setCellWidth(bcellWidthDebit);
					//tdstableDebit.setCellNoWrap(bcellwrap);
					tdstableDebit.setBorderDetail(3);
					tdstableDebit.setHeaderTable(true);
				//	tdstable.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				//	tdstable.setHeaderBGColor(new BaseColor(200, 200, 200));
					// tdstable.setBlankRowsBelow(1);
					rg.addTableToDoc(tdstableDebit);
					
					/*Vector lineVector1=new Vector();
					lineVector1.add(new BaseColor(0, 0, 0));
					lineVector1.add(Rectangle.TOP);*/
					//rg.seperatorLine();
					// Table for Debit Details end
					
					//netEarningDebitObj[0][0] = "Net Earnings :";
					netEarningDebitObj[0][1] = "Total Deductions :";
					//netEarningDebitObj[1][0] = " ";
					//netEarningDebitObj[2][0] = "Net Payable :";
					TableDataSet netEarningDebitDataSet = new TableDataSet();
					netEarningDebitDataSet.setData(netEarningDebitObj);
					/*netEarningDebitDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
							10, 11, 12, 13, 14 });*/
					netEarningDebitDataSet.setCellWidth(bcellWidthDebit);
					netEarningDebitDataSet.setCellAlignment(bcellAlignDebit);
					//netEarningDebitDataSet.setBodyBGColor(new BaseColor(200,200,200));
					netEarningDebitDataSet.setBorderLines(true);
					netEarningDebitDataSet.setBodyFontStyle(1);
					netEarningDebitDataSet.setCellNoWrap(bcellwrap);
					//netEarningDebitDataSet.setBlankRowsAbove(1);
					netEarningDebitDataSet.setBlankRowsBelow(1);
									
					netEarningDebitDataSet.setBorderDetail(0);
				//	netEarningDataSet.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));

					rg.addTableToDoc(netEarningDebitDataSet);
					
					
					
				}
				else {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "Deduction Summary : No records available";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 0 });
					noData.setCellWidth(new int[] { 100 });
				///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}
				
				// Net Payable
				for (int j = 2; j < 15; j++) {

					if (Double.parseDouble(checkNullValue(String
							.valueOf(netEarningObj[0][j]))) > Double
							.parseDouble(checkNullValue(String
									.valueOf(netEarningDebitObj[0][j])))) {
					
						netEarningTotalObj[0][j] = formatter
								.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(netEarningObj[0][j])))
								- Double.parseDouble(checkNullValue(String
										.valueOf(netEarningDebitObj[0][j]))));
					} else {
						
						netEarningTotalObj[0][j] = "0.00";
					}
				}
				//total net earnings
				double totalNet=0.0;
				if(objTotalTabularData!=null&& objTotalTabularData.length>0){
					for (int i = 0; i < objTotalTabularData.length; i++) {
						totalNet+=Double.parseDouble(formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningObj[0][14])))));
						
						
					}
				}
				netEarningObj[0][14]=formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningObj[0][14]))));
				
				//total net deduction
				double totalDeduction=0.0;
				if(objDebitTotalTabularData!=null&& objDebitTotalTabularData.length>0){
					for (int i = 0; i < objDebitTotalTabularData.length; i++) {
						totalDeduction+=Double.parseDouble(formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningDebitObj[0][14])))));
						
						
					}
				}
				
				
				                      
				                      
				netEarningDebitObj[0][14]=formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningDebitObj[0][14]))));
				
				netEarningTotalObj[0][14]=formatter.format(Double.parseDouble(checkNullValue(String.valueOf(netEarningObj[0][14])))-Double.parseDouble(checkNullValue(String.valueOf(netEarningDebitObj[0][14]))));
				
				
				
				if (empTypeDetail != null && empTypeDetail.length > 0 && creditDetail != null && creditDetail.length>0 && debitDetail != null && debitDetail.length>0) {
				
				
				
				//netEarningDebitObj[0][0] = "Net Earnings :";
				
				netEarningTotalObj[0][1] = "Net Payable :";
				TableDataSet netEarningTotalDataSet = new TableDataSet();
				netEarningTotalDataSet.setData(netEarningTotalObj);
				/*netEarningTotalDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
						10, 11, 12, 13, 14 });*/
				netEarningTotalDataSet.setCellWidth(bcellWidth);
				netEarningTotalDataSet.setCellAlignment(bcellAlign);
				netEarningTotalDataSet.setBodyFontStyle(1);
				netEarningTotalDataSet.setCellNoWrap(bcellwrap);
				netEarningTotalDataSet.setBodyBGColor(new BaseColor(200,200,200));
				//netEarningTotalDataSet.setBlankRowsAbove(1);
				netEarningTotalDataSet.setBlankRowsBelow(1);
								
				netEarningTotalDataSet.setBorderDetail(0);
			//	netEarningDataSet.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));

				rg.addTableToDoc(netEarningTotalDataSet);
				
				}
				String footerQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME "
						+ " FROM HRMS_EMP_OFFC "
						+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ "WHERE EMP_ID=" + bean.getUserEmpId();

				// for credit emp type
				Object[][] footerDetail = getSqlModel().getSingleResult(
						footerQuery);

				String countEmpQuery = "SELECT COUNT(*) FROM HRMS_SALARY_"
						+ fromYear
						+ " "
						+ "INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+fromYear+".SAL_LEDGER_CODE) "
						+ "WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+ bean.getDivCode()+")";

				if(!bean.getBranchCode().equals("")){
					countEmpQuery+=" AND SAL_EMP_CENTER IN("+bean.getBranchCode()+")  "; // Branch
				}
				
				if(!bean.getDeptCode().equals("")){
					countEmpQuery+=" AND SAL_DEPT IN("+bean.getDeptCode()+")  "; // Department
				}
				
				if(!bean.getPaybillId().equals("")){
					countEmpQuery+=" AND SAL_EMP_PAYBILL IN("+bean.getPaybillId()+")  "; // Paybill
				}
				
				if(!bean.getTypeCode().equals("")){
					countEmpQuery+=" AND SAL_EMP_TYPE IN("+bean.getTypeCode()+")  "; // Employee Type
				}
				
				// for credit emp type
				/*Object[][] countEmpDetail = getSqlModel().getSingleResult(
						countEmpQuery);*/

				
				/*TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][]{{"Generated By : "+String.valueOf(footerDetail[0][1])+" on "+date.toString()}});
				generatedByData.setCellAlignment(new int[]{0});
				generatedByData.setCellWidth(new int[]{100});
				generatedByData.setCellColSpan(new int[]{16});
				//generatedByData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
				generatedByData.setBorderDetail(0);
				//generatedByData.setBorderDetail(1);
				//generatedByData.setHeaderTable(true);
				generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);*/
				/*Vector lineVector=new Vector();
				lineVector.add(new BaseColor(0, 0, 0));
				lineVector.add(Rectangle.BOTTOM);
				rg.addLine(lineVector);*/
				
				
				

			/*} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
			///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}*/

		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}
