/**
 * 
 */
package org.paradyne.model.loan;

import java.awt.Color;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.Loan.LoanMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import org.paradyne.lib.report.CrystalReport;

import com.itextpdf.text.BaseColor;
import com.lowagie.text.Font;

/**
 * @author tarunc Date: 10-07-08 LoanMasterModel oto write the business logic to
 *         save, update, delete and view loan types
 *  Modified by GTL-AA1712
 */
public class LoanMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanMasterModel.class);

	/**
	 * Method name--saveLoanType parameters---LoanMaster bean return
	 * type--boolean Purpose ---To save the loan type details
	 */
	public boolean saveLoanType(LoanMaster bean) {
		boolean result = false;
		Object[][] loanType = new Object[1][11];
		loanType[0][0] = bean.getDivCode();
		loanType[0][1] = bean.getLoanTypeName();
		loanType[0][2] = bean.getDebitcode();
		loanType[0][3] = bean.getLoanLimitAmount();

		loanType[0][4] = bean.getInterestType();
		loanType[0][5] = bean.getInterestRate();
		loanType[0][6] = bean.getTaxable();
		loanType[0][7] = bean.getOtherLoanTerms();
		loanType[0][8] = bean.getAdminCode();
		loanType[0][9] = bean.getAccountCode();
		loanType[0][10] = bean.getStdIntRateSBI();

		result = getSqlModel().singleExecute(getQuery(1), loanType);
		if (result) {
			String query = "SELECT MAX(LOAN_CODE)FROM HRMS_LOAN_MASTER";
			Object obj[][] = getSqlModel().getSingleResult(query);
			bean.setLoanTypeCode(checkNull(String.valueOf(obj[0][0])));
		}
		return result;
	}

	/**
	 * Method name--updateLoanType parameters---LoanMaster bean return
	 * type--boolean Purpose ---To update the loan type details for the selected
	 * record
	 */
	public boolean updateLoanType(LoanMaster bean) {
		boolean result = false;
		Object[][] loanType = new Object[1][12];
		loanType[0][0] = bean.getDivCode();
		loanType[0][1] = bean.getLoanTypeName().trim();
		loanType[0][2] = bean.getDebitcode();
		loanType[0][3] = bean.getLoanLimitAmount();

		loanType[0][4] = bean.getInterestType();
		loanType[0][5] = bean.getInterestRate();
		loanType[0][6] = bean.getTaxable();
		loanType[0][7] = bean.getOtherLoanTerms().trim();
		loanType[0][8] = bean.getAdminCode();
		loanType[0][9] = bean.getAccountCode();
		loanType[0][10] = bean.getStdIntRateSBI();

		loanType[0][11] = bean.getLoanTypeCode();
		result = getSqlModel().singleExecute(getQuery(2), loanType);
		return result;
	}

	/**
	 * Method name--deleteLoanType parameters---LoanMaster bean return
	 * type--boolean Purpose ---To delete all the details for the selected
	 * record
	 */
	public boolean deleteLoanType(LoanMaster bean) {
		boolean result = false;
		Object[][] loanCode = new Object[1][1];
		loanCode[0][0] = bean.getLoanTypeCode();

		result = getSqlModel().singleExecute(getQuery(3), loanCode);
		return result;
	}

	/**
	 * Method name--getReport parameters---LoanMaster model, HttpServletRequest
	 * request, HttpServletResponse response, ServletContext context,
	 * HttpSession session return type--void Purpose ---To generate the crystal
	 * report
	 */
	public void getReport(LoanMaster bean, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session) {
		try {
			String reportType = "";
			String title = " Loan Type Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Loan Type Report");
			rds.setReportName(title);
			rds.setReportType("Pdf");
			rds.setPageSize("A4");
			org.paradyne.lib.ireportV2.ReportGenerator rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);

			// For Report heading
		/*	TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);*/

			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: - " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBorder(false);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			// =============Details of loanTypeData====================//
					Object[][] loanTypeData = null;
					try {
						String loanTypeQuery = "SELECT LOAN_CODE, LOAN_DIV_CODE,LOAN_NAME, " +
								"LOAN_DEBIT_CODE,DEBIT_NAME, NVL(LOAN_LIMIT, 0)  ,DECODE(INT_TYPE,'N','No Interest','F','Flat Interest','R','Reducing Principal','I','Reducing Interest'), " +
								" INT_RATE, CASE WHEN TAXABLE IS NULL THEN '' WHEN TAXABLE = 'Y' THEN 'Yes' WHEN TAXABLE = 'N' THEN 'No' END AS Taxable_, OTHER_LOAN_TERM, ADMIN_CODE,OFFC.EMP_TOKEN  || ' - ' || OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, " +
								" ACCOUNT_CODE, OFFC1.EMP_TOKEN  || ' - ' || OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS ACCOUNT_NAME , " +
								"  STD_RATE_SBI " +
								"FROM HRMS_LOAN_MASTER  " +
								"inner JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)  " +
								"LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_MASTER.ADMIN_CODE)  " +
								"LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_LOAN_MASTER.ACCOUNT_CODE) " +
								///"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LOAN_MASTER.LOAN_DIV_CODE) " +
								" where LOAN_CODE="+bean.getLoanTypeCode();
						loanTypeData = getSqlModel().getSingleResult(
								loanTypeQuery);

						if (loanTypeData != null && loanTypeData.length > 0) {
							Object[][] loanDetails = new Object[1][4];// new-------------->
							loanDetails[0][0] = "Loan Type Details :";
							loanDetails[0][1] = "";
							loanDetails[0][2] = "";
							loanDetails[0][3] = "";

							TableDataSet loanInfoTable = new TableDataSet();
							loanInfoTable.setData(loanDetails);
							loanInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
							loanInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
							loanInfoTable.setBorder(false);
							rg.addTableToDoc(loanInfoTable);
							
							Object[][] loanTypeDetails = new Object[7][4];// new-------------->
							loanTypeDetails[0][0] = "Division Name :";
							loanTypeDetails[0][1] = getActualDivName(String.valueOf(loanTypeData[0][1]));
							loanTypeDetails[0][2] = "";
							loanTypeDetails[0][3] = "";
							
							loanTypeDetails[1][0] = "Loan Name :";
							loanTypeDetails[1][1] = String.valueOf(loanTypeData[0][2]);
							loanTypeDetails[1][2] = "";
							loanTypeDetails[1][3] = "";
							
							loanTypeDetails[2][0] = "Loan Deducted Under Debit Head :";
							loanTypeDetails[2][1] = String.valueOf(loanTypeData[0][4]);
							loanTypeDetails[2][2] = "Loan Limit Amount :";
							loanTypeDetails[2][3] = String.valueOf(loanTypeData[0][5]);
							
							loanTypeDetails[3][0] = "Interest Type :";
							loanTypeDetails[3][1] = String.valueOf(loanTypeData[0][6]);
							loanTypeDetails[3][2] = "Interest Rate :";
							loanTypeDetails[3][3] = String.valueOf(loanTypeData[0][7]);
							
							loanTypeDetails[4][0] = "Taxable :";
							loanTypeDetails[4][1] = String.valueOf(loanTypeData[0][8]);
							loanTypeDetails[4][2] = "Other Loan Terms :";
							loanTypeDetails[4][3] = String.valueOf(loanTypeData[0][9]);
							
							loanTypeDetails[5][0] = "HR Approval :";
							loanTypeDetails[5][1] = String.valueOf(loanTypeData[0][11]);
							loanTypeDetails[5][2] = "Accountant Approval :";
							loanTypeDetails[5][3] = String.valueOf(loanTypeData[0][13]);
							
							loanTypeDetails[6][0] = "Standard Interest Rate Of SBI :";
							loanTypeDetails[6][1] = String.valueOf(loanTypeData[0][14]);
							loanTypeDetails[6][2] = "";
							loanTypeDetails[6][3] = "";

							TableDataSet loanTypeInfoTable = new TableDataSet();
							loanTypeInfoTable.setData(loanTypeDetails);
							loanTypeInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
							loanTypeInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
							loanTypeInfoTable.setBorderDetail(3);
							rg.addTableToDoc(loanTypeInfoTable);
							
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
					} catch (Exception e) {
						logger
								.error("exception in challanDataMarDec object",
										e);
					} // end of catch
				
			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method name--data parameters---LoanMaster bean, HttpServletRequest
	 * request return type--void Purpose ---To display the loan type details at
	 * the time of page loading
	 */
	public void data(LoanMaster bean, HttpServletRequest request) {

		Object[][] obj = getSqlModel().getSingleResult(getQuery(4));
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));

		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

			// for (int i = 0; i < obj.length; i++) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				LoanMaster bean1 = new LoanMaster();
				bean1.setLoanTypeCode(checkNull(String.valueOf(obj[i][0])));
				bean1.setLoanTypeName(checkNull(String.valueOf(obj[i][1])));
				bean1.setDebitname(checkNull(String.valueOf(obj[i][2])));
				bean1.setLoanLimitAmount(checkNull(String.valueOf(obj[i][3])));
				//bean1.setAdmApprovalReq(checkNull(String.valueOf(obj[i][3])));
				bean1.setAdminCode(checkNull(String.valueOf(obj[i][3])));
				bean1.setAdminToken(checkNull(String.valueOf(obj[i][3])));
				bean1.setAdminName(checkNull(String.valueOf(obj[i][3])));
			//	bean1.setAccApprovalReq(checkNull(String.valueOf(obj[i][3])));
				bean1.setAccountCode(checkNull(String.valueOf(obj[i][3])));
				bean1.setAccountToken(checkNull(String.valueOf(obj[i][3])));
				bean1.setAccountName(checkNull(String.valueOf(obj[i][3])));

				list.add(bean1);
			}
			bean.setTotalRecords("" + obj.length);
		} else {
			bean.setListLength("false");
		}

		if (list.size() > 0)
			bean.setListLength("true");
		else
			bean.setListLength("false");
		// logger.info("length --> "+obj.length);
		bean.setIteratorlist(list);
	}

	/**
	 * Method name--calforedit parameters---LoanMaster bean return type--void
	 * Purpose ---To edit the selected record
	 */
	public void calforedit(LoanMaster bean) {
		try {
			String query = " select LOAN_CODE, LOAN_NAME, LOAN_DEBIT_CODE,DEBIT_NAME, NVL(LOAN_LIMIT, 0)  "
					+ ",IS_ADMIN_APPROVAL, 	ADMIN_CODE,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, "
					+ "		IS_ACCOUNT_APPROVAL, ACCOUNT_CODE, OFFC1.EMP_TOKEN ,"
					+ "		OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS ACCOUNT_NAME , " +
							" INT_TYPE, INT_RATE, TAXABLE, OTHER_LOAN_TERM, STD_RATE_SBI,LOAN_DIV_CODE "
					+ " FROM HRMS_LOAN_MASTER "
					+ " inner JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_MASTER.ADMIN_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_LOAN_MASTER.ACCOUNT_CODE) "
					///+ "LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LOAN_MASTER.LOAN_DIV_CODE)"
					+ " where LOAN_CODE= " + bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			// setter
			bean.setLoanTypeCode(String.valueOf(data[0][0]));
			bean.setLoanTypeName(String.valueOf(data[0][1]));
			bean.setDebitcode(String.valueOf(data[0][2]));
			bean.setDebitname(String.valueOf(data[0][3]));
			bean.setLoanLimitAmount(String.valueOf(data[0][4]));
			bean.setAdmApprovalReq(String.valueOf(data[0][5]));
			bean.setAdminCode(checkNull(String.valueOf(data[0][6])));
			bean.setAdminToken(checkNull(String.valueOf(data[0][7])));
			bean.setAdminName(checkNull(String.valueOf(data[0][8])));
			bean.setAccApprovalReq(String.valueOf(data[0][9]));
			bean.setAccountCode(checkNull(String.valueOf(data[0][10])));
			bean.setAccountToken(checkNull(String.valueOf(data[0][11])));
			bean.setAccountName(checkNull(String.valueOf(data[0][12])));
			
			bean.setInterestType(checkNull(String.valueOf(data[0][13])));
			bean.setInterestRate(checkNull(String.valueOf(data[0][14])));
			bean.setTaxable(checkNull(String.valueOf(data[0][15])));
			bean.setOtherLoanTerms(checkNull(String.valueOf(data[0][16])));
			bean.setStdIntRateSBI(checkNull(String.valueOf(data[0][17])));
			
			bean.setDivCode(checkNull(String.valueOf(data[0][18])));
			bean.setDivName(checkNull(getActualDivName(String.valueOf(data[0][18]))));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method : getActualDivName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualDivName(final String divID) {
		String divName = "";
		try {
			if (divID == null || divID.equals("null")) {
				divName = "";
			} else {
				String divNameQuery = " SELECT HRMS_DIVISION.DIV_NAME FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID IN ("+ divID + ")";
				Object[][] divNameObj = getSqlModel().getSingleResult(divNameQuery);
				if (divNameObj != null && divNameObj.length > 0) {
					for (int i = 0; i < divNameObj.length; i++) {
						divName += String.valueOf(divNameObj[i][0])+",";
					}
				}
				divName = divName.substring(0, divName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return divName;
	}

	/**
	 * Method name--deletecheckedRecords parameters---LoanMaster bean, String[]
	 * code return type--boolean Purpose ---To delete all the selected records
	 */
	public boolean deletecheckedRecords(LoanMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;
				} // end of if
			} // end of for loop
		} // end of if
		if (count != 0) {
			result = false;
			return result;
		} // end of if
		else {
			result = true;
			return result;
		} // end of else
	}

	/**
	 * Method name-- checkNull parameters--- String result return type-- String
	 * Purpose --- To check whether the value is null or not
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	public void getCreditRecord(LoanMaster bean) {
		// TODO Auto-generated method stub
		String query = " select LOAN_CODE, LOAN_NAME, LOAN_DEBIT_CODE,DEBIT_NAME, NVL(LOAN_LIMIT, 0)  ,IS_ADMIN_APPROVAL," +
				"ADMIN_CODE,OFFC.EMP_TOKEN ,OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, " +
				"		IS_ACCOUNT_APPROVAL, ACCOUNT_CODE, OFFC1.EMP_TOKEN ,	" +
				"	OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS ACCOUNT_NAME  , " +
				"	LOAN_DIV_CODE,INT_TYPE, INT_RATE, TAXABLE, OTHER_LOAN_TERM, STD_RATE_SBI " +
				"	FROM HRMS_LOAN_MASTER   " +
				"	inner JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)   " +
				"	LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_MASTER.ADMIN_CODE)   " +
				"	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_LOAN_MASTER.ACCOUNT_CODE)  " +
				///"	LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LOAN_MASTER.LOAN_DIV_CODE) " +
				"	 where LOAN_CODE=  " + bean.getLoanTypeCode();

		Object[][] data = getSqlModel().getSingleResult(query);
		// setter

		bean.setLoanTypeCode(String.valueOf(data[0][0]));
		bean.setLoanTypeName(String.valueOf(data[0][1]));
		bean.setDebitcode(String.valueOf(data[0][2]));
		bean.setDebitname(String.valueOf(data[0][3]));
		bean.setLoanLimitAmount(String.valueOf(data[0][4]));
		bean.setAdmApprovalReq(String.valueOf(data[0][5]));
		bean.setAdminCode(checkNull(String.valueOf(data[0][6])));
		bean.setAdminToken(checkNull(String.valueOf(data[0][7])));
		bean.setAdminName(checkNull(String.valueOf(data[0][8])));
		bean.setAccApprovalReq(String.valueOf(data[0][9]));
		bean.setAccountCode(checkNull(String.valueOf(data[0][10])));
		bean.setAccountToken(checkNull(String.valueOf(data[0][11])));
		bean.setAccountName(checkNull(String.valueOf(data[0][12])));
		
		bean.setDivCode(checkNull(String.valueOf(data[0][13])));
		bean.setDivName(checkNull(getActualDivName(String.valueOf(data[0][13]))));
		bean.setInterestType(checkNull(String.valueOf(data[0][14])));
		bean.setInterestRate(checkNull(String.valueOf(data[0][15])));
		bean.setTaxable(checkNull(String.valueOf(data[0][16])));
		bean.setOtherLoanTerms(checkNull(String.valueOf(data[0][17])));
		bean.setStdIntRateSBI(checkNull(String.valueOf(data[0][18])));
	}
	
//Added by Tinshuk Banafar....Begin....
	
	public void getLoanReport(LoanMaster loanMast, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = loanMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Loan Master Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Loan Master Details ");
		rds.setShowPageNo(true);
		rds.setGeneratedByText(loanMast.getUserEmpId());
		rds.setUserEmpId(loanMast.getUserEmpId());
		
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setShowPageNo(true);
		rds.setTotalColumns(12);
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", request.getContextPath()+"/loan/LoanMaster_input.action");
			// Initial Page Action name
			}

		String query = "select rownum,LOAN_NAME,DEBIT_NAME,LOAN_DIV_CODE,LOAN_LIMIT,OFFC.EMP_TOKEN,"
				+" OFFC.EMP_FNAME ||' '||OFFC.EMP_MNAME ||' '||OFFC.EMP_LNAME AS ADMIN_NAME,"
				+" OFFC1.EMP_TOKEN ,OFFC1.EMP_FNAME ||' '|| OFFC1.EMP_MNAME ||' '||OFFC1.EMP_LNAME AS ACCOUNT_NAME ,"	
				+" DECODE(INT_TYPE,'F','Flat Interest','N','No Interest','R','Reducing Principal','I','Reducing Interest'),"
				+" DECODE(TAXABLE,'Y','Yes','N','No'),STD_RATE_SBI"
				+" FROM HRMS_LOAN_MASTER " 
				+" INNER JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)" 
				+" LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_MASTER.ADMIN_CODE)"  
				+" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_LOAN_MASTER.ACCOUNT_CODE)";
				//+" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LOAN_MASTER.LOAN_DIV_CODE)";
		
		query+=" order by rownum,LOAN_NAME ";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		
		Object[][]obj=new Object[queryData.length][12];
		
		for (int i = 0; i < queryData.length; i++) {
			obj[i][0]=queryData[i][0];
			obj[i][1]=queryData[i][1];
			obj[i][2]=queryData[i][2];
			String divName = getActualDivName(String.valueOf(queryData[i][3]));
			obj[i][3]=divName;
			obj[i][4]=queryData[i][4];
			obj[i][5]=queryData[i][5];
			obj[i][6]=queryData[i][6];
			obj[i][7]=queryData[i][7];
			obj[i][8]=queryData[i][8];
			obj[i][9]=queryData[i][9];
			obj[i][10]=queryData[i][10];
			obj[i][11]=queryData[i][11];
		}
		//Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();
		tdstable.setHeader(new String[] {"SrNo","Loan Name","Loan Deducted Under Debit Head","Division","Loan Limit","HR EmpId","HR Approval","Accountant EmpId","Accountant Approval","Interest Type","Taxable","SBI Standard Interest Rate"});// defining headers
		
		tdstable.setCellAlignment(new int[]{0,0,0,0,2,0,0,0,0,0,0,2});
		tdstable.setCellWidth(new int[]{5,10,10,10,7,10,13,10,10,10,5,5});
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(obj);// data object from query Loan Deducted Under Debit Head
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setBorderDetail(3);
		rg.addTableToDoc(tdstable);
		
		int totalRecords=queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Records : "
				+ totalRecords } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		
		rg.process();
		
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
	}
	//Added by Tinshuk Banafar....End.....

}
