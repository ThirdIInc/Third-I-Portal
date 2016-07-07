package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.PFReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @author Prakash Date:02-07-2009
 */

public class PFReportModel extends ModelBase {

	NumberFormat formatter = new DecimalFormat("#0.00");

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PFReportModel.class);
	
	
	public void generateReport(PFReport bean,  HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "PFStatutoryReport"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			if(bean.getCheckEdliSal().equals("Y")){
				rds.setTotalColumns(14);
			}else{
				rds.setTotalColumns(13);
			}
			
			rds.setReportName("PF Statutory Report");
			
			if(!type.equals("Xls")){
				rds.setPageSize("A4");
				rds.setShowPageNo(true);
				rds.setPageOrientation("landscape");
			}
			rds.setUserEmpId(bean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;

			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "PFReport_input.action");
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
	
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, PFReport bean) {

		try {

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{fetchFilters(bean)}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellColSpan(new int[]{3});
			filterData.setCellWidth(new int[]{100});
			filterData.setBlankRowsBelow(1);
			filterData.setBodyFontStyle(0);
			rg.addTableToDoc(filterData);
			
			try {
				PFDataModel pfModel = new PFDataModel();
				pfModel.initiate(context, session);
				pfModel.deletePFData(bean.getMonth(), bean.getYear(), bean.getDivCode());
				pfModel.terminate();
			} catch (Exception e) {
				logger.error("Exception in calling PFData model for deleting pf data "+ e);
			}

			try {
				PFDataModel pfModel = new PFDataModel();
				pfModel.initiate(context, session);
				pfModel.insertPFReportData(bean.getMonth(), bean.getYear(),	bean.getDivCode());
				pfModel.terminate();
			} catch (Exception e) {
				logger.error("Exception in calling PFData model for inserting PF data"+ e);
			}
			String parameterQuery = " SELECT PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DEBIT_CODE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES,PF_PERCENTAGE,PF_FORMULA,NVL(PF_EMOL_NO_MAX_LIMIT_CHK,'N'),PF_EMOL_MAX_LIMIT FROM HRMS_PF_CONF "
					+ " WHERE TO_CHAR(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
					+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
					+ bean.getYear() + "-" + bean.getMonth() + "')";

			Object[][] paramObj = getSqlModel().getSingleResult(parameterQuery);

			if (paramObj != null && paramObj.length > 0) {

				String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
						+ bean.getMonth()
						+ " AND LEDGER_YEAR="
						+ bean.getYear()
						+ " AND LEDGER_DIVISION IN ("
						+ bean.getDivCode()
						+ ") AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL')";

				Object ledgerData[][] = getSqlModel().getSingleResult(
						ledgerQuery);

				String ledgerCode = "";
				logger.info("ledgerData.length---------" + ledgerData.length);

				if (ledgerData != null && ledgerData.length > 0) {

					for (int i = 0; i < ledgerData.length; i++) {
						ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
					}
					ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);

				}

				String ledgerQry = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
						+ bean.getMonth()
						+ " AND ARREARS_PAID_YEAR ="
						+ bean.getYear()
						+ " AND ARREARS_DIVISION IN ("
						+ bean.getDivCode()+")";

				Object ledgerObj[][] = getSqlModel().getSingleResult(ledgerQry);

				String arrCode = "";
				if (ledgerObj != null && ledgerObj.length > 0) {

					for (int i = 0; i < ledgerObj.length; i++) {
						arrCode += String.valueOf(ledgerObj[i][0]) + ",";
					}
					arrCode = arrCode.substring(0, arrCode.length() - 1);
				}

				if (bean.getReportOption().equals("S")) {

					if (ledgerData != null && ledgerData.length > 0) {
						rg = printSalary(bean, ledgerCode, rg, arrCode,	paramObj);

					} else {
						TableDataSet noDataTable = new TableDataSet();
						noDataTable.setData(new Object[][] { { "No salary records avaliable for selected criteria." } });
						noDataTable.setCellAlignment(new int[] { 1 });
						noDataTable.setCellWidth(new int[] { 100 });
						noDataTable.setCellColSpan(new int[]{7});
						noDataTable.setBorderDetail(0);
						rg.addTableToDoc(noDataTable);
					}

				} else if (bean.getReportOption().equals("A")) {

					if (ledgerObj != null && ledgerObj.length > 0) {
						rg = printArrear(bean, ledgerCode, paramObj, rg, arrCode);
					} else {
						TableDataSet noDataTable = new TableDataSet();
						noDataTable.setData(new Object[][] { { "No arrear records avaliable for selected criteria." } });
						noDataTable.setCellAlignment(new int[] { 1 });
						noDataTable.setCellWidth(new int[] { 100 });
						noDataTable.setBorderDetail(0);
						noDataTable.setCellColSpan(new int[]{7});
						rg.addTableToDoc(noDataTable);
					}
				} else if (bean.getReportOption().equals("O")) {

					if (ledgerData != null && ledgerData.length > 0) {
						rg = printSalary(bean, ledgerCode, rg, arrCode,	paramObj);
					} else {
						TableDataSet noDataTable = new TableDataSet();
						noDataTable.setData(new Object[][] { { "No salary records avaliable for selected criteria." } });
						noDataTable.setCellAlignment(new int[] { 1 });
						noDataTable.setCellWidth(new int[] { 100 });
						noDataTable.setBorderDetail(0);
						noDataTable.setCellColSpan(new int[]{7});
						rg.addTableToDoc(noDataTable);
					}

					if (bean.getCheckFlag().equals("N")) {

						if (ledgerObj != null && ledgerObj.length > 0) {
							rg = printArrear(bean, ledgerCode, paramObj, rg, arrCode);
						} else {
							TableDataSet noDataTable = new TableDataSet();
							noDataTable.setData(new Object[][] { { "No arrear records avaliable for selected criteria." } });
							noDataTable.setCellAlignment(new int[] { 1 });
							noDataTable.setCellWidth(new int[] { 100 });
							noDataTable.setBorderDetail(0);
							noDataTable.setCellColSpan(new int[]{7});
							rg.addTableToDoc(noDataTable);
						}
					}

					rg = printSettlement(bean, paramObj, rg);

				} else if (bean.getReportOption().equals("se")) {
					rg = printSettlement(bean, paramObj, rg);

				}
				if (ledgerData != null && ledgerData.length > 0)
					rg = printGrandTotal(bean, rg, paramObj);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return rg;
	}

	public Vector getSalary(PFReport bean, String ledgerCode) {
		Vector dataVect = new Vector();
		try {

			Object[][] dataObj = null;
			Object[][] totObj = null;

			String empQuery = " SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME as name,nvl(HRMS_SALARY_MISC.SAL_GPFNO,' ') as PFNO, "
					+ " nvl(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' ') as dob,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') as doj, "
					+ " TO_CHAR(HRMS_PF_DATA.PF_EMP_BASIC,9999999990.99) as basic,"
					+ " TO_CHAR(HRMS_PF_DATA.PF_PFEPF,9999999990.99) as PF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_PF),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_VPF),9999999990.99)  as VPF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_F_PF),9999999990.99) as efpf, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_PF),9999999990.99)  as CPF,"
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_F_PF),9999999990.99) as CFPF ";

			String totalQuery = " SELECT '','','', "
					+ " '','', "
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_EMP_BASIC),9999999990.99) AS BASIC,"
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_PFEPF),9999999990.99) as PF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_PF)),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_VPF)),9999999990.99) as VPF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_F_PF)),9999999990.99) as efpf, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_PF)),9999999990.99) as CPF,"
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_F_PF)),9999999990.99) as CFPF ";

			if (bean.getCheckEdliSal().equals("Y")) {

				empQuery += " ,TO_CHAR(ROUND(HRMS_PF_DATA.PF_EDLI),9999999990.99) as EDLI";

				totalQuery += " ,TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EDLI)),9999999990.99) as EDLI";
			}

			empQuery += " ,TO_CHAR(HRMS_PF_DATA.PF_TOTAL,9999999990.99) as total ";

			totalQuery += " , TO_CHAR(sum(HRMS_PF_DATA.PF_TOTAL),9999999990.99) as total ";

			empQuery += ",PF_EMP_ID,PF_EMP_BRN,PF_EMP_DEPT,CENTER_NAME,DEPT_NAME ";

			String empQuery1 = " FROM HRMS_PF_DATA "
					+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID "
					+ " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID= HRMS_PF_DATA.PF_EMP_BRN"
					+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_PF_DATA.PF_EMP_DEPT "
					+ " WHERE PF_TYPE = 'S' AND PF_MONTH = " + bean.getMonth()
					+ " AND PF_YEAR = " + bean.getYear() + " AND PF_EMP_DIV IN ("
					+ bean.getDivCode()+")";

			if (!bean.getOnHold().equals("A"))
				empQuery1 += " AND PF_EMP_ONHOLD = '" + bean.getOnHold() + "'";

			if (!bean.getBrnCode().equals(""))
				empQuery1 += " AND PF_EMP_BRN IN (" + bean.getBrnCode() + ")";

			if (!bean.getDeptCode().equals(""))
				empQuery1 += " AND PF_EMP_DEPT IN (" + bean.getDeptCode() + ")";

			if (!bean.getTypeCode().equals(""))
				empQuery1 += " AND PF_EMP_TYPE IN (" + bean.getTypeCode() + ")";
			
			if (!bean.getGradeCode().equals(""))
				empQuery1 += " AND PF_EMP_GRADE IN (" + bean.getGradeCode() + ")";

			if (!bean.getPayBillNo().equals(""))
				empQuery1 += " AND PF_EMP_PAYBILL IN (" + bean.getPayBillNo()+ ")";

			String orderQuery = " ORDER BY";

			if (bean.getBranchFlag().equals("true"))
				orderQuery += " PF_EMP_BRN,";

			if (bean.getDeptFlag().equals("true"))
				orderQuery += " PF_EMP_DEPT,";

			orderQuery += " UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			empQuery = empQuery + empQuery1 + orderQuery;
			totalQuery = totalQuery + empQuery1;

			logger.info("salempQuery---------------" + empQuery);
			logger.info("saltotalQuery---------------" + totalQuery);

			if (!ledgerCode.equals("")) {
				dataObj = getSqlModel().getSingleResult(empQuery);
				totObj = getSqlModel().getSingleResult(totalQuery);
			}
			dataVect.add(0, dataObj);
			dataVect.add(1, totObj);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataVect;
	}

	public Vector getArrear(PFReport bean, String arrLedgerCode) {

		Vector dataVect = new Vector();
		try {

			Object[][] dataObj = null;
			Object[][] totObj = null;

			String empQuery = " SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME as name,TO_CHAR(TO_DATE(PF_ARR_MONTH,'MM'),'MONTH'),PF_ARR_YEAR,DECODE(PF_TYPE,'AM','Monthly','AP','Promotional'),PF_ARR_DAYS, "
					+ " TO_CHAR(HRMS_PF_DATA.PF_EMP_BASIC,9999999990.99) as basic,"
					+ " TO_CHAR(HRMS_PF_DATA.PF_PFEPF,9999999990.99) as PF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_PF),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_VPF),9999999990.99) as VPF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_F_PF),9999999990.99) as efpf, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_PF),9999999990.99)  as CPF,"
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_F_PF),9999999990.99) as CFPF ";

			String totalQuery = " SELECT '','','','','','', "
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_EMP_BASIC),9999999990.99) AS BASIC,"
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_PFEPF),9999999990.99) as PF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_PF)),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_VPF)),9999999990.99) as VPF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_F_PF)),9999999990.99) as efpf, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_PF)),9999999990.99) as CPF,"
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_F_PF)),9999999990.99) as CFPF ";

			if (bean.getCheckEdliSal().equals("Y")) {

				empQuery += " ,TO_CHAR(ROUND(HRMS_PF_DATA.PF_EDLI),9999999990.99) as EDLI";

				totalQuery += " ,TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EDLI)),9999999990.99) as EDLI";
			}

			empQuery += " ,TO_CHAR(HRMS_PF_DATA.PF_TOTAL,9999999990.99) as total ";

			totalQuery += " , TO_CHAR(sum(HRMS_PF_DATA.PF_TOTAL),9999999990.99) as total ";

			empQuery += ",PF_EMP_ID,PF_EMP_BRN,PF_EMP_DEPT,CENTER_NAME,DEPT_NAME ";

			String empQuery1 = " FROM HRMS_PF_DATA"
					+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_PF_DATA.PF_EMP_BRN "
					+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_PF_DATA.PF_EMP_DEPT  "
					+ " WHERE PF_TYPE IN('AM','AP') AND PF_MONTH = "
					+ bean.getMonth() + " AND PF_YEAR = " + bean.getYear()
					+ " AND PF_EMP_DIV IN (" + bean.getDivCode()+")";

			if (!bean.getOnHold().equals("A"))
				empQuery1 += " AND PF_EMP_ONHOLD = '" + bean.getOnHold() + "'";

			if (!bean.getBrnCode().equals(""))
				empQuery1 += " AND PF_EMP_BRN IN (" + bean.getBrnCode() + ")";

			if (!bean.getDeptCode().equals(""))
				empQuery1 += " AND PF_EMP_DEPT IN (" + bean.getDeptCode() + ")";

			if (!bean.getTypeCode().equals(""))
				empQuery1 += " AND PF_EMP_TYPE IN (" + bean.getTypeCode() + ")";

			if (!bean.getGradeCode().equals(""))
				empQuery1 += " AND PF_EMP_GRADE IN (" + bean.getGradeCode() + ")";
			
			if (!bean.getPayBillNo().equals(""))
				empQuery1 += " AND PF_EMP_PAYBILL IN (" + bean.getPayBillNo() + ")";

			String orderQuery = " ORDER BY";

			if (bean.getBranchFlag().equals("true"))
				orderQuery += " PF_EMP_BRN,";

			if (bean.getDeptFlag().equals("true"))
				orderQuery += " PF_EMP_DEPT,";

			orderQuery += " PF_EMP_ID ";

			empQuery = empQuery + empQuery1 + orderQuery;
			totalQuery = totalQuery + empQuery1;

			logger.info("ArrearempQuery--------------------" + empQuery);
			logger.info("ArreartotalQuery--------------------" + totalQuery);

			if (!arrLedgerCode.equals("")) {

				dataObj = getSqlModel().getSingleResult(empQuery);
				totObj = getSqlModel().getSingleResult(totalQuery);

			}
			dataVect.add(0, dataObj);
			dataVect.add(1, totObj);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataVect;
	}

	public Vector getSettlement(PFReport bean) {
		Vector dataVect = new Vector();
		try {
			Object[][] dataObj = null;
			Object[][] totObj = null;

			String empQuery = " SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME ENAME, "
					+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' ') LEVAEDATE,NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' ') SETTLEDATE, "
					+ " nvl(HRMS_SALARY_MISC.SAL_GPFNO,' ') as PFNO, nvl(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' ') as dob,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') as doj, "
					+ " TO_CHAR(HRMS_PF_DATA.PF_EMP_BASIC,9999999990.99) as basic,"
					+ " TO_CHAR(HRMS_PF_DATA.PF_PFEPF,9999999990.99) as PF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_PF),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_VPF),9999999990.99) as VPF, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_EMP_F_PF),9999999990.99) as efpf, "
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_PF),9999999990.99)  as CPF,"
					+ " TO_CHAR(ROUND(HRMS_PF_DATA.PF_CMP_F_PF),9999999990.99) as CFPF ";

			String totalQuery = " SELECT '','','','','','','',"
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_EMP_BASIC),9999999990.99) AS BASIC,"
					+ "  TO_CHAR(sum(HRMS_PF_DATA.PF_PFEPF),9999999990.99) as PF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_PF)),9999999990.99) as epf, "
					//UPDATED BY REEBA
					+ "  TO_CHAR(sum(ROUND(NVL(HRMS_PF_DATA.PF_VPF,0))),9999999990.99) as VPF, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EMP_F_PF)),9999999990.99) as efpf, "
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_PF)),9999999990.99) as CPF,"
					+ "  TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_CMP_F_PF)),9999999990.99) as CFPF ";

			if (bean.getCheckEdliSal().equals("Y")) {

				empQuery += " ,TO_CHAR(ROUND(HRMS_PF_DATA.PF_EDLI),9999999990.99) as EDLI";

				totalQuery += " ,TO_CHAR(sum(ROUND(HRMS_PF_DATA.PF_EDLI)),9999999990.99) as EDLI";
			}

			empQuery += " ,TO_CHAR(HRMS_PF_DATA.PF_TOTAL,9999999990.99) as total ";

			totalQuery += " , TO_CHAR(sum(HRMS_PF_DATA.PF_TOTAL),9999999990.99) as total ";

			empQuery += ",PF_EMP_ID,PF_EMP_BRN,PF_EMP_DEPT,CENTER_NAME,DEPT_NAME ";

			String empQuery1 = " FROM HRMS_PF_DATA "
					+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID "
					+ " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID "
					+ " LEFT JOIN HRMS_SETTL_HDR on HRMS_SETTL_HDR.SETTL_ECODE =  HRMS_PF_DATA.PF_EMP_ID "
					// + " LEFT JOIN HRMS_RESIGN ON HRMS_RESIGN.RESIGN_EMP =
					// HRMS_PF_DATA.PF_EMP_ID "
					// + " LEFT JOIN HRMS_SETTL_HDR on
					// HRMS_SETTL_HDR.SETTL_RESGNO = HRMS_RESIGN.RESIGN_CODE "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER "
					+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_EMP_OFFC.EMP_DEPT "
					+ " WHERE PF_TYPE = 'T' AND PF_MONTH = " + bean.getMonth()
					+ " AND HRMS_PF_DATA.PF_PFEPF>0 AND PF_YEAR = " + bean.getYear() + " AND PF_EMP_DIV IN ("
					+ bean.getDivCode()+")";

			if (!bean.getBrnCode().equals(""))
				empQuery1 += " AND PF_EMP_BRN IN (" + bean.getBrnCode() + ")";

			if (!bean.getDeptCode().equals(""))
				empQuery1 += " AND PF_EMP_DEPT IN (" + bean.getDeptCode() + ")";

			if (!bean.getTypeCode().equals(""))
				empQuery1 += " AND PF_EMP_TYPE IN (" + bean.getTypeCode() + ")";

			if (!bean.getGradeCode().equals(""))
				empQuery1 += " AND PF_EMP_GRADE IN (" + bean.getGradeCode() + ")";
			
			if (!bean.getPayBillNo().equals(""))
				empQuery1 += " AND PF_EMP_PAYBILL IN (" + bean.getPayBillNo() + ")";
			String orderQuery = " ORDER BY";

			if (bean.getBranchFlag().equals("true"))
				orderQuery += " PF_EMP_BRN,";

			if (bean.getDeptFlag().equals("true"))
				orderQuery += " PF_EMP_DEPT,";

			orderQuery += " UPPER(EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME)";

			empQuery = empQuery + empQuery1 + orderQuery;
			totalQuery = totalQuery + empQuery1;

			logger.info("settempQuery---------------" + empQuery);
			logger.info("setttotalQuery---------------" + totalQuery);

			dataObj = getSqlModel().getSingleResult(empQuery);
			totObj = getSqlModel().getSingleResult(totalQuery);

			// if(dataObj !=null && dataObj.length > 0){

			dataVect.add(0, dataObj);
			dataVect.add(1, totObj);

			// }

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataVect;
	}

	public org.paradyne.lib.ireportV2.ReportGenerator printSalary(PFReport bean, String ledgerCode,
			org.paradyne.lib.ireportV2.ReportGenerator rg, String arrCode, Object[][] paramObj) {
		try {

			Vector salVect = getSalary(bean, ledgerCode);
			Object salData[][] = (Object[][]) salVect.get(0);
			Object salaryTotalDataObj[][] = (Object[][]) salVect.get(1);
			if (salData != null && salData.length > 0) {

				if (bean.getCheckFlag().equals("Y")) {

					if (!arrCode.equals("") && arrCode != null) {

						Vector arrVect = getArrear(bean, arrCode);
						Object arrData[][] = (Object[][]) arrVect.get(0);
						Object totalArrData[][] = (Object[][]) arrVect.get(1);

						boolean EDLICheck = false;

						if (bean.getCheckFlag().equals("Y"))
							EDLICheck = true;

						if (arrData != null && arrData.length > 0) {

							for (int j = 0; j < salData.length; j++) {

								for (int k = 0; k < arrData.length; k++) {

									if (String
											.valueOf(
													salData[j][salData[0].length - 5])
											.equals(
													String
															.valueOf(arrData[k][arrData[0].length - 5]))) {

										for (int l = 5; l < salData[0].length - 5; l++) {

											salData[j][l] = formatter
													.format(Double
															.parseDouble(checkNull(String
																	.valueOf(salData[j][l])))
															+ Double
																	.parseDouble(checkNull(String
																			.valueOf(arrData[k][l + 1]))));

											
										}

									}
								}

							}
							for (int l = 5; l < salData[0].length - 5; l++) {

								salaryTotalDataObj[0][l] = formatter.format(Double.parseDouble(checkNull(String.valueOf(salaryTotalDataObj[0][l])))
												+ Double.parseDouble(checkNull(String.valueOf(totalArrData[0][l + 1]))));

							}
						}

					}
				}

				String colNames[] = new String[salData[0].length - 5];
				int[] cellwidth = new int[salData[0].length - 5];
				int[] alignmnet = new int[salData[0].length - 5];

				/*String vpfColNames[] = new String[4];

				vpfColNames[0] = "Employee Token";
				vpfColNames[1] = "Employee Name";
				vpfColNames[2] = "Basic";
				vpfColNames[3] = "VPF Amount";*/

				boolean brnCheck = false;
				boolean deptCheck = false;
				boolean EDLICheck = false;

				if (bean.getBranchFlag().equals("true"))
					brnCheck = true;
				if (bean.getDeptFlag().equals("true"))
					deptCheck = true;

				
					colNames[0] = "Emp Id";
					colNames[1] = "Employee Name";
					colNames[2] = "PF Number";
					colNames[3] = "Date of Birth";
					colNames[4] = "Date of Joining";
					colNames[5] = "Basic";
					colNames[6] = "P.F.+E.P.F.";
					colNames[7] = "Emp. PF";
					//UPDATED BY REEBA
					colNames[8] = "Voluntary PF";
					colNames[9] = "Emp. Family PF";
					colNames[10] = "Employer Pension Fund";
					colNames[11] = "Employer Family PF.";
					if (bean.getCheckEdliSal().equals("Y")) {
						colNames[12] = "E.D.L.I. Salary";
						colNames[13] = "Total";
						EDLICheck = true;
					}else{
						colNames[12] = "Total";
					}

					cellwidth[0] = 10;
					cellwidth[1] = 25;
					cellwidth[2] = 10;
					cellwidth[3] = 10;
					cellwidth[4] = 10;
					cellwidth[5] = 10;
					cellwidth[6] = 10;
					cellwidth[7] = 10;
					//UPDATED BY REEBA
					cellwidth[8] = 10;
					cellwidth[9] = 10;
					cellwidth[10] = 10;
					cellwidth[11] = 10;
					cellwidth[12] = 10;
					if (bean.getCheckEdliSal().equals("Y")) {
						cellwidth[13] = 10;
					}

					alignmnet[0] = 0;
					alignmnet[1] = 0;
					alignmnet[2] = 0;
					alignmnet[3] = 0;
					alignmnet[4] = 0;
					alignmnet[5] = 2;
					alignmnet[6] = 2;
					alignmnet[7] = 2;
					//UPDATED BY REEBA
					alignmnet[8] = 2;
					alignmnet[9] = 2;
					alignmnet[10] = 2;
					alignmnet[11] = 2;
					alignmnet[12] = 2;
					if (bean.getCheckEdliSal().equals("Y")) {
						alignmnet[13] = 2;
					}
					
				
					TableDataSet salaryData = new TableDataSet();
					if (bean.getCheckFlag().equals("Y")){
						salaryData.setData(new Object[][] {{ "Salary With Consolidate Arrear"}});
					}else{
						salaryData.setData(new Object[][] {{ "Salary Details"}});
					}
					salaryData.setCellAlignment(new int[] {  0 });
					salaryData.setCellWidth(new int[] { 100 });
					salaryData.setCellColSpan(new int[]{7});
					salaryData.setBorderDetail(0);
					salaryData.setBodyFontStyle(1);
					rg.addTableToDoc(salaryData);

					bean.setSalTotal(salaryTotalDataObj);

				if (brnCheck || deptCheck) {
					rg = setReport(salData, rg, brnCheck, deptCheck, colNames, cellwidth, alignmnet, 5, EDLICheck, paramObj);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					
					if (bean.getCheckFlag().equals("Y")){
						branchDeptData.setData(new Object[][] {{ "Salary With Consolidate Arrear Total : "}});
					}else{
						branchDeptData.setData(new Object[][] {{ "Salary Total : "}});
					}
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBlankRowsBelow(1);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					if (bean.getCheckFlag().equals("Y")){
						salaryTotalDataObj[0][1] = "Salary With Consolidate Arrear Total";
					}else{
						salaryTotalDataObj[0][1] = "Salary Total";
					}
					
					TableDataSet salaryTotalDataTDS = new TableDataSet();
					salaryTotalDataTDS.setData(salaryTotalDataObj);
					salaryTotalDataTDS.setCellAlignment(alignmnet);
					salaryTotalDataTDS.setCellWidth(cellwidth);
					salaryTotalDataTDS.setBorderDetail(0);
					salaryTotalDataTDS.setBorderLines(true);
					salaryTotalDataTDS.setBodyFontStyle(1);
					rg.addTableToDoc(salaryTotalDataTDS);
					
					if (EDLICheck) {
						rg = extraEDLITable(rg, salaryTotalDataObj, 5, 5 + 7,	paramObj, salData);
					}

				} else {
					TableDataSet salaryTableData = new TableDataSet();
					salaryTableData.setHeader(colNames);
					salaryTableData.setData(salData);
					salaryTableData.setCellAlignment(alignmnet);
					salaryTableData.setCellWidth(cellwidth);
					salaryTableData.setBorderDetail(3);
					salaryTableData.setHeaderBorderDetail(3);
					salaryTableData.setHeaderTable(true);
					rg.addTableToDoc(salaryTableData);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					if (bean.getCheckFlag().equals("Y")){
						branchDeptData.setData(new Object[][] {{ "Salary With Consolidate Arrear Total : "}});
					}else{
						branchDeptData.setData(new Object[][] {{ "Salary Total : "}});
					}
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBlankRowsBelow(1);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					if (bean.getCheckFlag().equals("Y")){
						salaryTotalDataObj[0][1] = "Salary With Consolidate Arrear Total";
					}else{
						salaryTotalDataObj[0][1] = "Salary Total";
					}
					
					TableDataSet salaryTotalTDS = new TableDataSet();
					salaryTotalTDS.setData(salaryTotalDataObj);
					salaryTotalTDS.setCellAlignment(alignmnet);
					salaryTotalTDS.setCellWidth(cellwidth);
					salaryTotalTDS.setBorderDetail(0);
					salaryTotalTDS.setBorderLines(true);
					salaryTotalTDS.setBodyFontStyle(1);
					rg.addTableToDoc(salaryTotalTDS);
					
					Object newOBj[][]=salaryTotalTDS.getData();
					for (int i = 0; i < newOBj.length; i++) {
						for (int j = 0; j < newOBj[0].length; j++) {
							logger.info("newOBj["+i+"]["+j+"]=="+newOBj[i][j]);
						}
					}
					if (EDLICheck) {
						rg = extraEDLITable(rg, salaryTotalDataObj, 5, 5 + 7,	paramObj, salData);
					}
				}
			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No salary records avaliable for selected criteria." } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellColSpan(new int[]{7});
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorderDetail(0);
				rg.addTableToDoc(noDataTable);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}

		return rg;

	}

	public org.paradyne.lib.ireportV2.ReportGenerator printArrear(PFReport bean, String ledgerCode,
			Object[][] paramObj, org.paradyne.lib.ireportV2.ReportGenerator rg, String arrCode) {
		try {

			Vector arrVect = getArrear(bean, arrCode);
			Object arrData[][] = (Object[][]) arrVect.get(0);
			Object totalArrData[][] = (Object[][]) arrVect.get(1);

			if (arrData != null && arrData.length > 0) {

				boolean brnCheck = false;
				boolean deptCheck = false;
				boolean EDLICheck = false;

				if (bean.getBranchFlag().equals("true"))
					brnCheck = true;
				if (bean.getDeptFlag().equals("true"))
					deptCheck = true;

				String colNames[] = new String[arrData[0].length - 5];
				int[] cellwidth = new int[arrData[0].length - 5];
				int[] alignmnet = new int[arrData[0].length - 5];

				
					colNames[0] = "Emp Id";
					colNames[1] = "Employee Name";
					colNames[2] = "Month";
					colNames[3] = "Year";
					colNames[4] = "Type";
					colNames[5] = "Arrear Days";
					colNames[6] = "Arrears Basic";
					colNames[7] = "P.F.+E.P.F.";
					colNames[8] = "Emp. PF";
					//UPDATED BY REEBA
					colNames[9] = "Voluntary PF";
					colNames[10] = "Emp. Family PF";
					colNames[11] = "Employer PF";
					colNames[12] = "Employer Family PF.";
					if (bean.getCheckEdliSal().equals("Y")) {
						EDLICheck = true;
						colNames[13] = "E.D.L.I. Salary";
						colNames[14] = "Total";
					}else{
						colNames[13] = "Total";
					}

					cellwidth[0] = 5;
					cellwidth[1] = 20;
					cellwidth[2] = 10;
					cellwidth[3] = 10;
					cellwidth[4] = 10;
					cellwidth[5] = 10;
					cellwidth[6] = 10;
					cellwidth[7] = 10;
					cellwidth[8] = 10;
					//UPDATED BY REEBA
					cellwidth[9] = 10;
					cellwidth[10] = 10;
					cellwidth[11] = 10;
					cellwidth[12] = 10;
					cellwidth[13] = 10;
					if (bean.getCheckEdliSal().equals("Y")) {
						cellwidth[14] = 10;
					}

					alignmnet[0] = 0;
					alignmnet[1] = 0;
					alignmnet[2] = 1;
					alignmnet[3] = 1;
					alignmnet[4] = 1;
					alignmnet[5] = 1;
					alignmnet[6] = 2;
					alignmnet[7] = 2;
					alignmnet[8] = 2;
					//UPDATED BY REEBA
					alignmnet[9] = 2;
					alignmnet[10] = 2;
					alignmnet[11] = 2;
					alignmnet[12] = 2;
					alignmnet[13] = 2;
					if (bean.getCheckEdliSal().equals("Y")) {
						alignmnet[14] = 2;
					}

				TableDataSet arrearData = new TableDataSet();
				arrearData.setData(new Object[][] {{ "Arrear Details"}});
				arrearData.setCellAlignment(new int[] {  0 });
				arrearData.setCellWidth(new int[] { 100 });
				arrearData.setCellColSpan(new int[]{7});
				arrearData.setBorderDetail(0);
				arrearData.setBodyFontStyle(1);
				rg.addTableToDoc(arrearData);

				bean.setArrTotal(totalArrData);
				
				
				if (brnCheck || deptCheck) {
					rg = setReport(arrData, rg, brnCheck, deptCheck, colNames,
							cellwidth, alignmnet, 6, EDLICheck, paramObj);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					branchDeptData.setData(new Object[][] {{ "Arrear Total"}});
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					totalArrData[0][1] = "Arrear Total";
					TableDataSet totalArrearData1 = new TableDataSet();
					totalArrearData1.setData(totalArrData);
					totalArrearData1.setCellAlignment(alignmnet);
					totalArrearData1.setCellWidth(cellwidth);
					totalArrearData1.setBorderDetail(0);
					totalArrearData1.setBorderLines(true);
					totalArrearData1.setBodyFontStyle(1);
					rg.addTableToDoc(totalArrearData1);
					
					if (EDLICheck) {
						rg = extraEDLITable(rg, totalArrData, 6, 6 + 6,
								paramObj, arrData);
					}

				} else {

					TableDataSet arrearsTableData = new TableDataSet();
					arrearsTableData.setHeader(colNames);
					arrearsTableData.setData(arrData);
					arrearsTableData.setCellAlignment(alignmnet);
					arrearsTableData.setCellWidth(cellwidth);
					arrearsTableData.setBorderDetail(3);
					arrearsTableData.setHeaderBorderDetail(3);
					arrearsTableData.setHeaderTable(true);
					rg.addTableToDoc(arrearsTableData);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					branchDeptData.setData(new Object[][] {{ "Arrear Total"}});
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					totalArrData[0][1] = "Arrear Total";
					TableDataSet totalArrearsDataTDS = new TableDataSet();
					totalArrearsDataTDS.setData(totalArrData);
					totalArrearsDataTDS.setCellAlignment(alignmnet);
					totalArrearsDataTDS.setCellWidth(cellwidth);
					totalArrearsDataTDS.setBorderDetail(0);
					totalArrearsDataTDS.setBorderLines(true);
					totalArrearsDataTDS.setBodyFontStyle(1);
					rg.addTableToDoc(totalArrearsDataTDS);

					if (EDLICheck) {
						rg = extraEDLITable(rg, totalArrData, 6, 6 + 6, paramObj, arrData);
					}
				}

			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No arrear records avaliable for selected criteria." } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorderDetail(0);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}

		return rg;

	}

	public org.paradyne.lib.ireportV2.ReportGenerator printSettlement(PFReport bean,
			Object[][] paramObj, org.paradyne.lib.ireportV2.ReportGenerator rg) {
		try {

			Vector settVect = getSettlement(bean);
			Object settData[][] = (Object[][]) settVect.get(0);
			Object totalSettData[][] = (Object[][]) settVect.get(1);

			if (settData != null && settData.length > 0) {

				String colNames[] = new String[settData[0].length - 5];
				int[] cellwidth = new int[settData[0].length - 5];
				int[] alignmnet = new int[settData[0].length - 5];

					colNames[0] = "Emp Id";
					colNames[1] = "Employee Name";
					colNames[2] = "Date Of Leaving";
					colNames[3] = "Settlement Date";
					colNames[4] = "Pf No.";
					colNames[5] = "Date of\nBirth";
					colNames[6] = "Date of\nJoining";
					colNames[7] = "Basic\nAmount";
					colNames[8] = "P.F.+E.P.F.";
					colNames[9] = "Emp. PF";
					//UPDATED BY REEBA
					colNames[10] = "Voluntary PF";
					colNames[11] = "Emp. Family PF";
					colNames[12] = "Employer PF";
					colNames[13] = "Employer Family PF.";
					if (bean.getCheckEdliSal().equals("Y")) {
						colNames[14] = "E.D.L.I. Salary";
						colNames[15] = "Total";
					}else{
						colNames[14] = "Total";
					}

					cellwidth[0] = 5;
					cellwidth[1] = 10;
					cellwidth[2] = 10;
					cellwidth[3] = 10;
					cellwidth[4] = 10;
					cellwidth[5] = 10;
					cellwidth[6] = 10;
					cellwidth[7] = 10;
					cellwidth[8] = 10;
					cellwidth[9] = 10;
					//UPDATED BY REEBA
					cellwidth[10] = 10;
					cellwidth[11] = 10;
					cellwidth[12] = 10;
					cellwidth[13] = 10;
					cellwidth[14] = 10;
					if (bean.getCheckEdliSal().equals("Y")) {
						cellwidth[15] = 10;
					}

					alignmnet[0] = 0;
					alignmnet[1] = 0;
					alignmnet[2] = 0;
					alignmnet[3] = 1;
					alignmnet[4] = 1;
					alignmnet[5] = 0;
					alignmnet[6] = 0;
					alignmnet[7] = 2;
					alignmnet[8] = 2;
					alignmnet[9] = 2;
					//UPDATED BY REEBA
					alignmnet[10] = 2;
					alignmnet[11] = 2;
					alignmnet[12] = 2;
					alignmnet[13] = 2;
					alignmnet[14] = 2;
					if (bean.getCheckEdliSal().equals("Y")) {
						alignmnet[15] = 2;
					}

				boolean brnCheck = false;
				boolean deptCheck = false;

				if (bean.getBranchFlag().equals("true")){
					brnCheck = true;}
				if (bean.getDeptFlag().equals("true")){
					deptCheck = true;
				}

				TableDataSet settlementData = new TableDataSet();
				settlementData.setData(new Object[][] {{ "Settlement Details"}});
				settlementData.setCellAlignment(new int[] {  0 });
				settlementData.setCellWidth(new int[] { 100 });
				settlementData.setCellColSpan(new int[]{7});
				settlementData.setBorderDetail(0);
				settlementData.setBodyFontStyle(1);
				rg.addTableToDoc(settlementData);
				
				bean.setSettTotal(totalSettData);
				
				if (brnCheck || deptCheck) {
					rg = setReport(settData, rg, brnCheck, deptCheck, colNames,
							cellwidth, alignmnet, 7, false, paramObj);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					branchDeptData.setData(new Object[][] {{ "Settlement Total"}});
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					totalSettData[0][1] = "Settlement Total";
					TableDataSet totalSettlementData1 = new TableDataSet();
					totalSettlementData1.setData(totalSettData);
					totalSettlementData1.setCellAlignment(alignmnet);
					totalSettlementData1.setCellWidth(cellwidth);
					totalSettlementData1.setBorderDetail(0);
					totalSettlementData1.setBorderLines(true);
					totalSettlementData1.setBodyFontStyle(1);
					rg.addTableToDoc(totalSettlementData1);

					if (bean.getCheckEdliSal().equals("Y")) {
						rg = extraEDLITable(rg, totalSettData, 7, 7 + 6,
								paramObj, settData);
					}

				} else {

					TableDataSet settlementTableData = new TableDataSet();
					settlementTableData.setHeader(colNames);
					settlementTableData.setData(settData);
					settlementTableData.setCellAlignment(alignmnet);
					settlementTableData.setCellWidth(cellwidth);
					settlementTableData.setBorderDetail(3);
					settlementTableData.setHeaderBorderDetail(3);
					settlementTableData.setHeaderTable(true);
					rg.addTableToDoc(settlementTableData);
					
					/*TableDataSet branchDeptData = new TableDataSet();
					branchDeptData.setData(new Object[][] {{ "Settlement Total"}});
					branchDeptData.setCellAlignment(new int[] {  0 });
					branchDeptData.setCellWidth(new int[] { 100 });
					branchDeptData.setBorderDetail(0);
					branchDeptData.setBodyFontStyle(1);
					rg.addTableToDoc(branchDeptData);*/
					
					totalSettData[0][1] = "Settlement Total";
					
					TableDataSet settleTotalTDS = new TableDataSet();
					settleTotalTDS.setData(totalSettData);
					settleTotalTDS.setCellAlignment(alignmnet);
					settleTotalTDS.setCellWidth(cellwidth);
					settleTotalTDS.setBorderDetail(0);
					settleTotalTDS.setBorderLines(true);
					settleTotalTDS.setBodyFontStyle(1);
					rg.addTableToDoc(settleTotalTDS);

					
					if (bean.getCheckEdliSal().equals("Y")) {
						rg = extraEDLITable(rg, totalSettData, 7, 7 + 6,
								paramObj, settData);
					}
				}

			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No settlement records avaliable for selected criteria." } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setCellColSpan(new int[]{7});
				noDataTable.setBorderDetail(0);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return rg;

	}

	//OLD METHOD DO NOT DELETE
	public org.paradyne.lib.ireportV2.ReportGenerator printGrandTotal1(PFReport bean, org.paradyne.lib.ireportV2.ReportGenerator rg,
			Object[][] paramObj) {
		Object[][] salObj = null;

		try {

			if (bean.getCheckFlag().equals("Y")) {

				salObj = bean.getSalTotal();

				if (salObj != null && salObj.length > 0) {

					salObj[0][0] = "";
					salObj[0][1] = "";
					salObj[0][2] = "";
					salObj[0][3] = "";
					salObj[0][4] = "";
					Object[][] settObj = bean.getSettTotal();

					if (salObj != null && salObj.length > 0) {

						if (settObj != null && settObj.length > 0) {

							try {
								for (int l = 5; l < salObj[0].length; l++) {

									salObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(salObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(settObj[0][l + 2]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					}

				}
			} else {

				salObj = bean.getSalTotal();

				if (salObj != null && salObj.length > 0) {

					salObj[0][0] = "";
					salObj[0][1] = "";
					salObj[0][2] = "";
					salObj[0][3] = "";
					salObj[0][4] = "";

					Object[][] arrObj = bean.getArrTotal();
					Object[][] settObj = bean.getSettTotal();

					if (salObj != null && salObj.length > 0) {

						if (arrObj != null && arrObj.length > 0) {

							try {
								for (int l = 5; l < salObj[0].length; l++) {

									salObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(salObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(arrObj[0][l + 1]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}

						if (settObj != null && settObj.length > 0) {

							try {
								for (int l = 5; l < salObj[0].length; l++) {

									salObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(salObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(settObj[0][l + 2]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (salObj != null && salObj.length > 0) {

			int[] cellwidth = new int[salObj[0].length];
			int[] alignmnet = new int[salObj[0].length];
			String[] colNames = new String[salObj[0].length];

				colNames[0] = "";
				colNames[1] = "";
				colNames[2] = "";
				colNames[3] = "";
				colNames[4] = "";
				colNames[5] = "Basic";
				colNames[6] = "P.F.+E.P.F.";
				colNames[7] = "Emp. PF";
				//UPDATED BY REEBA
				colNames[8] = "Voluntary PF";
				colNames[9] = "Emp. Family PF";
				colNames[10] = "Employer PF";
				colNames[11] = "Employer Family PF.";
				if (bean.getCheckEdliSal().equals("Y")) {
					colNames[12] = "E.D.L.I. Salary";
					colNames[13] = "Total";
				}else{
					colNames[12] = "Total";
				}

				cellwidth[0] = 10;
				cellwidth[1] = 25;
				cellwidth[2] = 10;
				cellwidth[3] = 10;
				cellwidth[4] = 10;
				cellwidth[5] = 10;
				cellwidth[6] = 10;
				cellwidth[7] = 10;
				//UPDATED BY REEBA
				cellwidth[8] = 10;
				cellwidth[9] = 10;
				cellwidth[10] = 10;
				cellwidth[11] = 10;
				cellwidth[12] = 10;
				if (bean.getCheckEdliSal().equals("Y")) {
					cellwidth[13] = 10;
				}

				alignmnet[0] = 0;
				alignmnet[1] = 0;
				alignmnet[2] = 0;
				alignmnet[3] = 1;
				alignmnet[4] = 1;
				alignmnet[5] = 2;
				alignmnet[6] = 2;
				alignmnet[7] = 2;
				//UPDATED BY REEBA
				alignmnet[8] = 2;
				alignmnet[9] = 2;
				alignmnet[10] = 2;
				alignmnet[11] = 2;
				alignmnet[12] = 2;
				if (bean.getCheckEdliSal().equals("Y")) {
					alignmnet[13] = 2;
				}

			TableDataSet grandTableData = new TableDataSet();
			grandTableData.setData(new Object[][] {{ "Grand Total"}});
			grandTableData.setCellAlignment(new int[] {  0 });
			grandTableData.setCellWidth(new int[] { 100 });
			grandTableData.setCellColSpan(new int[]{7});
			grandTableData.setBorderDetail(0);
			grandTableData.setBodyFontStyle(1);
			rg.addTableToDoc(grandTableData);
			
			TableDataSet grandTableTotalDataTDS = new TableDataSet();
			grandTableTotalDataTDS.setHeader(colNames);
			grandTableTotalDataTDS.setData(salObj);
			grandTableTotalDataTDS.setCellAlignment(alignmnet);
			grandTableTotalDataTDS.setCellWidth(cellwidth);
			grandTableTotalDataTDS.setBorderDetail(3);
			grandTableTotalDataTDS.setHeaderBorderDetail(3);
			rg.addTableToDoc(grandTableTotalDataTDS);

			if (bean.getCheckEdliSal().equals("Y")) {
				// rg = extraEDLITable(rg, salObj, 5, 5 + 6, paramObj);
			}
		}

		// added by vishwambhar

		try {

			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate("01-" + bean.getMonth() + "-"
					+ bean.getYear()));
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			int totDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String effectiveDate = totDays + "-" + bean.getMonth() + "-"
					+ bean.getYear();
			String formulaQuery = " SELECT HRMS_PF_CONF.PF_FORMULA ,PF_PERCENTAGE, PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DEBIT_CODE,PF_ADMIN_CHARGES,"
					+ "  PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES,NVL(PF_EMOL_NO_MAX_LIMIT_CHK,'N'),PF_EMOL_MAX_LIMIT  FROM HRMS_PF_CONF "
					+ "  WHERE  PF_DATE = (SELECT MAX(PF_DATE)  "
					+ "  FROM HRMS_PF_CONF WHERE PF_DATE < TO_DATE('"
					+ effectiveDate + "','DD-MM-YYYY'))";
			Object[][] dataFormula = getSqlModel()
					.getSingleResult(formulaQuery);
			double empEdli = 0.0;
			double adminCharges = 0.0;
			double cmpEdli = 0.0;
			if (dataFormula != null && dataFormula.length > 0) {
				int year = Integer.parseInt(bean.getYear());
				int month = Integer.parseInt(bean.getMonth());

				empEdli = Double.parseDouble("" + dataFormula[0][8]);
				adminCharges = Double.parseDouble("" + dataFormula[0][7]);
				cmpEdli = Double.parseDouble("" + dataFormula[0][9]);

				Object[][] salData = null;

				if (bean.getReportOption().equals("O")) {

					String total = " SELECT sum(PF_EMP_BASIC),sum(round(PF_EMP_PF)),sum(round(PF_CMP_PF)),sum(round(PF_CMP_F_PF)),sum(PF_EDLI),sum(round(NVL(PF_VPF,0))) FROM HRMS_PF_DATA WHERE PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN ( " + bean.getDivCode()+ ")";

					salData = getSqlModel().getSingleResult(total);
				}

				if (bean.getReportOption().equals("S")) {
					String total = "";
					if (String.valueOf(dataFormula[0][10]).equals("Y")) {
						total = " SELECT SUM(CASE WHEN PF_EMP_BASIC >= "
								+ String.valueOf(dataFormula[0][11])
								+ " THEN "
								+ String.valueOf(dataFormula[0][11])
								+ " ELSE PF_EMP_BASIC END ),sum(round(PF_EMP_PF)),sum(round(PF_CMP_PF)),sum(round(PF_CMP_F_PF)),sum(PF_EDLI),sum(round(NVL(PF_VPF,0))) FROM HRMS_PF_DATA WHERE PF_MONTH="
								+ month + " AND PF_YEAR = " + year
								+ " AND PF_EMP_DIV IN ( " + bean.getDivCode()+")"
								+ " AND PF_TYPE = 'S'";
					} else {
						total = " SELECT sum(PF_EMP_BASIC),sum(round(PF_EMP_PF)),sum(round(PF_CMP_PF)),sum(round(PF_CMP_F_PF)),sum(PF_EDLI),sum(round(NVL(PF_VPF,0))) FROM HRMS_PF_DATA WHERE PF_MONTH="
								+ month
								+ " AND PF_YEAR = "
								+ year
								+ " AND PF_EMP_DIV IN ("
								+ bean.getDivCode()
								+ " ) AND PF_TYPE = 'S'";
					}
					salData = getSqlModel().getSingleResult(total);
				}

				if (bean.getReportOption().equals("A")) {

					String total = " SELECT sum(PF_EMP_BASIC),sum(round(PF_EMP_PF)),sum(round(PF_CMP_PF)),sum(round(PF_CMP_F_PF)),sum(PF_EDLI),sum(round(NVL(PF_VPF,0))) FROM HRMS_PF_DATA WHERE PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN ( "
							+ bean.getDivCode()
							+ " ) AND PF_TYPE IN ('AM','AP')";

					salData = getSqlModel().getSingleResult(total);
				}
				if (bean.getReportOption().equals("se")) {

					String total = " SELECT sum(PF_EMP_BASIC),sum(round(PF_EMP_PF)),sum(round(PF_CMP_PF)),sum(round(PF_CMP_F_PF)),sum(PF_EDLI),sum(round(NVL(PF_VPF,0))) FROM HRMS_PF_DATA WHERE PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN ("
							+ bean.getDivCode()
							+ ") AND PF_TYPE = 'se'";

					salData = getSqlModel().getSingleResult(total);
				}
				
				//double vpfAmt = 0.0;
				//UPDATED BY REEBA
				double vpfAmt = Double.parseDouble(String.valueOf(salData[0][5]));

				if (salData != null && salData.length > 0) {
					NumberFormat formatter = new DecimalFormat("#0.00");

					Object twoDimobj[][] = new Object[6][2];
					twoDimobj[0][0] = "A/C.NO.1  :";
					twoDimobj[0][1] = formatter.format(Math.round(Double
							.parseDouble(String.valueOf(salData[0][2]))
							+ Double.parseDouble(String.valueOf(salData[0][1]))
							+ vpfAmt));// EMployee contribution + VPF
					twoDimobj[1][0] = "A/C.NO.2  :";
					twoDimobj[1][1] = formatter.format(Math.round(Double
							.parseDouble(String.valueOf(salData[0][0]))
							* adminCharges / 100));
					twoDimobj[2][0] = "A/C.NO.10  :";
					twoDimobj[2][1] = formatter.format(Math.round(Double
							.parseDouble(String.valueOf(salData[0][3]))));
					twoDimobj[3][0] = "A/C.NO.21  :";
					twoDimobj[3][1] = formatter.format(Math.round(Double
							.parseDouble(String.valueOf(salData[0][4]))
							* empEdli / 100));// EDLI charges
					twoDimobj[4][0] = "A/C.NO.22  :";
					twoDimobj[4][1] = formatter.format(Math.round(Double
							.parseDouble(String.valueOf(salData[0][4]))
							* cmpEdli / 100));// EDLI admin charges;
					twoDimobj[5][0] = "TOTAL  :";
					double acno_1, acno_2, acno_10, acno_21, acno_22;
					acno_1 = Double
							.parseDouble(String.valueOf(twoDimobj[0][1]));
					acno_2 = Double
							.parseDouble(String.valueOf(twoDimobj[1][1]));
					acno_10 = Double.parseDouble(String
							.valueOf(twoDimobj[2][1]));
					acno_21 = Double.parseDouble(String
							.valueOf(twoDimobj[3][1]));
					acno_22 = Double.parseDouble(String
							.valueOf(twoDimobj[4][1]));
					twoDimobj[5][1] = (acno_1 + acno_2 + acno_10 + acno_21 + acno_22);

					int[] cellwidth1 = { 45, 55 };
					int[] alignment1 = { 2, 0 };
					
					TableDataSet twoDimData = new TableDataSet();
					twoDimData.setData(twoDimobj);
					twoDimData.setCellAlignment(alignment1);
					twoDimData.setCellWidth(cellwidth1);
					twoDimData.setBodyFontStyle(1);
					rg.addTableToDoc(twoDimData);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public org.paradyne.lib.ireportV2.ReportGenerator setReport(Object[][] salData, org.paradyne.lib.ireportV2.ReportGenerator rg,
			boolean brnCheck, boolean deptCheck, String[] colNames,
			int[] cellwidth, int[] alignmnet, int startSum, boolean EDLICheck,
			Object[][] paramObj) {
		try {
			Vector braVector = new Vector();
			Object[][] braTotObj = new Object[1][colNames.length];
			braTotObj = intSpace(braTotObj);
			int count = 0;
			String brnCode = String.valueOf(salData[0][salData[0].length - 4]);
			String deptCode = String.valueOf(salData[0][salData[0].length - 3]);
			braTotObj[0][1] = "Total";
			String printString = "";
			for (int i = 0; i < salData.length; i++) {

				if (i < salData.length
						&& checkCondition(
								brnCheck,
								deptCheck,
								deptCode,
								String
										.valueOf(salData[i][salData[0].length - 3]),
								brnCode,
								String
										.valueOf(salData[i][salData[0].length - 4]))) {

					braVector.add(salData[i]);
					count++;
					for (int j = startSum; j < colNames.length; j++) {

						if (String.valueOf(braTotObj[0][j]).equals(""))
							braTotObj[0][j] = "0";

						braTotObj[0][j] = formatter
								.format(((Double.parseDouble(String
										.valueOf(braTotObj[0][j])) + Double
										.parseDouble(String
												.valueOf(salData[i][j])))));
					}

				} else if (count < salData.length) {

					Object[][] reportObj = new Object[braVector.size()][colNames.length];
					for (int j = 0; j < braVector.size(); j++) {
						reportObj[j] = (Object[]) braVector.get(j);
					}
					if (reportObj != null && reportObj.length > 0) {

						if (brnCheck)
							printString += "Branch : "
									+ String
											.valueOf(salData[i - 1][salData[0].length - 2])
									+ "  ";
						if (deptCheck)
							printString += "Department : "
									+ String
											.valueOf(salData[i - 1][salData[0].length - 1])
									+ "  ";
						
						TableDataSet printData = new TableDataSet();
						printData.setData(new Object[][]{{printString}});
						printData.setCellAlignment(new int[]{0});
						printData.setCellWidth(new int[]{100});
						printData.setBorderDetail(0);
						printData.setBodyFontStyle(1);
						rg.addTableToDoc(printData);
						
						TableDataSet reportData1 = new TableDataSet();
						reportData1.setHeader(colNames);
						reportData1.setData(reportObj);
						reportData1.setCellAlignment(alignmnet);
						reportData1.setCellWidth(cellwidth);
						reportData1.setBorderDetail(3);
						reportData1.setHeaderBorderDetail(3);
						reportData1.setHeaderTable(true);
						rg.addTableToDoc(reportData1);
						
						TableDataSet branchTotalData1 = new TableDataSet();
						branchTotalData1.setData(braTotObj);
						branchTotalData1.setCellAlignment(alignmnet);
						branchTotalData1.setCellWidth(cellwidth);
						branchTotalData1.setBorderDetail(0);
						branchTotalData1.setBorderLines(true);
						branchTotalData1.setBodyFontStyle(1);
						rg.addTableToDoc(branchTotalData1);

						if (EDLICheck) {
							rg = extraEDLITable(rg, braTotObj, startSum,
									startSum + 6, paramObj, reportObj);
						}
					}
					printString = "";
					braVector = new Vector();
					braTotObj = new Object[1][colNames.length];
					braTotObj = intSpace(braTotObj);
					braTotObj[0][1] = "Total";
					brnCode = String.valueOf(salData[i][salData[0].length - 4]);
					deptCode = String
							.valueOf(salData[i][salData[0].length - 3]);
					i--;
				}
			}
			Object[][] reportObj = new Object[braVector.size()][colNames.length];
			for (int j = 0; j < braVector.size(); j++) {
				reportObj[j] = (Object[]) braVector.get(j);
			}
			if (reportObj != null && reportObj.length > 0) {

				if (brnCheck)
					printString += "Branch : "
							+ String
									.valueOf(reportObj[0][reportObj[0].length - 2])
							+ "  ";
				if (deptCheck)
					printString += "Department : "
							+ String
									.valueOf(reportObj[0][reportObj[0].length - 1])
							+ "  ";
				
				TableDataSet printData = new TableDataSet();
				printData.setData(new Object[][]{{printString}});
				printData.setCellAlignment(new int[]{0});
				printData.setCellWidth(new int[]{100});
				printData.setBodyFontStyle(1);
				rg.addTableToDoc(printData);
				
				TableDataSet reportData1 = new TableDataSet();
				reportData1.setHeader(colNames);
				reportData1.setData(reportObj);
				reportData1.setCellAlignment(alignmnet);
				reportData1.setCellWidth(cellwidth);
				reportData1.setBorderDetail(3);
				reportData1.setHeaderBorderDetail(3);
				reportData1.setHeaderTable(true);
				rg.addTableToDoc(reportData1);
				
				TableDataSet branchTotalData1 = new TableDataSet();
				branchTotalData1.setData(braTotObj);
				branchTotalData1.setCellAlignment(alignmnet);
				branchTotalData1.setCellWidth(cellwidth);
				branchTotalData1.setBorderDetail(0);
				branchTotalData1.setBorderLines(true);
				branchTotalData1.setBodyFontStyle(1);
				rg.addTableToDoc(branchTotalData1);

				if (EDLICheck) {
					rg = extraEDLITable(rg, braTotObj, startSum, startSum + 7,
							paramObj, reportObj);
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}

		return rg;
	}
	
	public org.paradyne.lib.ireportV2.ReportGenerator printGrandTotal(PFReport bean, org.paradyne.lib.ireportV2.ReportGenerator rg,
			Object[][] paramObj) {
		Object[][] grandTotalObj = null;
		Object [][]grandTotalObjTemp = bean.getSalTotal();
		grandTotalObj = new Object[1][grandTotalObjTemp[0].length];
		for (int i = 0; i < grandTotalObjTemp[0].length; i++) {
			grandTotalObj[0][i]=grandTotalObjTemp[0][i];
		}
		try {

			if (bean.getCheckFlag().equals("Y")) {

				//grandTotalObj = bean.getSalTotal();

				if (grandTotalObj != null && grandTotalObj.length > 0) {

					grandTotalObj[0][0] = "";
					grandTotalObj[0][1] = "";
					grandTotalObj[0][2] = "";
					grandTotalObj[0][3] = "";
					grandTotalObj[0][4] = "";
					Object[][] settObj = bean.getSettTotal();

					if (grandTotalObj != null && grandTotalObj.length > 0) {

						if (settObj != null && settObj.length > 0) {

							try {
								for (int l = 5; l < grandTotalObj[0].length; l++) {

									grandTotalObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(grandTotalObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(settObj[0][l + 2]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					}

				}
			} else {


				if (grandTotalObj != null && grandTotalObj.length > 0) {

					grandTotalObj[0][0] = "";
					grandTotalObj[0][1] = "";
					grandTotalObj[0][2] = "";
					grandTotalObj[0][3] = "";
					grandTotalObj[0][4] = "";

					Object[][] arrObjForGrand = bean.getArrTotal();
					Object[][] settObj = bean.getSettTotal();

					if (grandTotalObj != null && grandTotalObj.length > 0) {

						if (arrObjForGrand != null && arrObjForGrand.length > 0) {

							try {
								for (int l = 5; l < grandTotalObj[0].length; l++) {

									grandTotalObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(grandTotalObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(arrObjForGrand[0][l + 1]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}

						if (settObj != null && settObj.length > 0) {

							try {
								for (int l = 5; l < grandTotalObj[0].length; l++) {

									grandTotalObj[0][l] = formatter
											.format(Double
													.parseDouble(checkNull(String
															.valueOf(grandTotalObj[0][l])))
													+ Double
															.parseDouble(checkNull(String
																	.valueOf(settObj[0][l + 2]))));

								}

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (grandTotalObj != null && grandTotalObj.length > 0) {

			int[] cellwidth = new int[grandTotalObj[0].length];
			int[] alignmnet = new int[grandTotalObj[0].length];
			String[] colNames = new String[grandTotalObj[0].length];

				colNames[0] = "";
				colNames[1] = "";
				colNames[2] = "";
				colNames[3] = "";
				colNames[4] = "";
				colNames[5] = "Basic";
				colNames[6] = "P.F.+E.P.F.";
				colNames[7] = "Emp. PF";
				//UPDATED BY REEBA
				colNames[8] = "Voluntary PF";
				colNames[9] = "Emp. Family PF";
				colNames[10] = "Employer PF";
				colNames[11] = "Employer Family PF.";
				if (bean.getCheckEdliSal().equals("Y")) {
					colNames[12] = "E.D.L.I. Salary";
					colNames[13] = "Total";
				}else{
					colNames[12] = "Total";
				}

				cellwidth[0] = 10;
				cellwidth[1] = 25;
				cellwidth[2] = 10;
				cellwidth[3] = 10;
				cellwidth[4] = 10;
				cellwidth[5] = 10;
				cellwidth[6] = 10;
				cellwidth[7] = 10;
				//UPDATED BY REEBA
				cellwidth[8] = 10;
				cellwidth[9] = 10;
				cellwidth[10] = 10;
				cellwidth[11] = 10;
				cellwidth[12] = 10;
				if (bean.getCheckEdliSal().equals("Y")) {
					cellwidth[13] = 10;
				}

				alignmnet[0] = 0;
				alignmnet[1] = 0;
				alignmnet[2] = 0;
				alignmnet[3] = 1;
				alignmnet[4] = 1;
				alignmnet[5] = 2;
				alignmnet[6] = 2;
				alignmnet[7] = 2;
				//UPDATED BY REEBA
				alignmnet[8] = 2;
				alignmnet[9] = 2;
				alignmnet[10] = 2;
				alignmnet[11] = 2;
				alignmnet[12] = 2;
				if (bean.getCheckEdliSal().equals("Y")) {
					alignmnet[13] = 2;
				}

			TableDataSet grandData = new TableDataSet();
			grandData.setData(new Object[][] {{ "Grand Total"}});
			grandData.setCellAlignment(new int[] {  0 });
			grandData.setCellWidth(new int[] { 100 });
			grandData.setCellColSpan(new int[]{7});
			grandData.setBorderDetail(0);
			grandData.setBlankRowsAbove(1);
			grandData.setBodyFontStyle(1);
			rg.addTableToDoc(grandData);
			
			TableDataSet grandTotalTDS = new TableDataSet();
			grandTotalTDS.setHeader(colNames);
			grandTotalTDS.setData(grandTotalObj);
			grandTotalTDS.setCellAlignment(alignmnet);
			grandTotalTDS.setCellWidth(cellwidth);
			grandTotalTDS.setBorderDetail(3);
			grandTotalTDS.setHeaderBorderDetail(3);
			grandTotalTDS.setHeaderTable(true);
			rg.addTableToDoc(grandTotalTDS);

			if (bean.getCheckEdliSal().equals("Y")) {
				// rg = extraEDLITable(rg, grandTotalObj, 5, 5 + 6, paramObj);
			}
		}
		// added by vishwambhar

		try {

			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate("01-" + bean.getMonth() + "-"
					+ bean.getYear()));
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			int totDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String effectiveDate = totDays + "-" + bean.getMonth() + "-"
					+ bean.getYear();
			String formulaQuery = " SELECT HRMS_PF_CONF.PF_FORMULA ,PF_PERCENTAGE, PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DEBIT_CODE,PF_ADMIN_CHARGES,"
					+ "  PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES,NVL(PF_EMOL_NO_MAX_LIMIT_CHK,'N'),PF_EMOL_MAX_LIMIT  FROM HRMS_PF_CONF "
					+ "  WHERE  PF_DATE = (SELECT MAX(PF_DATE)  "
					+ "  FROM HRMS_PF_CONF WHERE PF_DATE < TO_DATE('"
					+ effectiveDate + "','DD-MM-YYYY'))";
			Object[][] dataFormula = getSqlModel()
					.getSingleResult(formulaQuery);
			double empEdli = 0.0;
			double adminCharges = 0.0;
			double cmpEdli = 0.0;
			if (dataFormula != null && dataFormula.length > 0) {
				int year = Integer.parseInt(bean.getYear());
				int month = Integer.parseInt(bean.getMonth());

				empEdli = Double.parseDouble("" + dataFormula[0][8]);
				adminCharges = Double.parseDouble("" + dataFormula[0][7]);
				cmpEdli = Double.parseDouble("" + dataFormula[0][9]);
				
				String salTotal="";
				Object[][] salData = null;

				if (bean.getReportOption().equals("O")) {
					if (String.valueOf(dataFormula[0][10]).equals("Y")) {
					salTotal = " SELECT SUM(PF_EMP_BASIC),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
						+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
						+ " WHERE PF_EMP_PF>0 AND PF_MONTH="
						+ month
						+ " AND PF_YEAR = "
						+ year
						+ " AND PF_EMP_DIV IN (" + bean.getDivCode() + ")";
					}else{
						salTotal = " SELECT NVL(SUM(ROUND(PF_EMP_BASIC)),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
							+ " WHERE PF_EMP_PF>0 AND PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN (" + bean.getDivCode() + ")";
					}
				}
				if (bean.getReportOption().equals("S")) {
					salTotal = "";
					if (String.valueOf(dataFormula[0][10]).equals("Y")) {
						salTotal = " SELECT SUM(PF_EMP_BASIC),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
								+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
								+ " WHERE PF_EMP_PF>0 AND PF_MONTH="
								+ month + " AND PF_YEAR = " + year
								+ " AND PF_EMP_DIV IN (" + bean.getDivCode() +")"
								+ " AND PF_TYPE = 'S'";
					} else {
						salTotal = " SELECT NVL(SUM(ROUND(PF_EMP_BASIC)),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
								+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
								+ " WHERE PF_EMP_PF>0 AND PF_MONTH="
								+ month
								+ " AND PF_YEAR = "
								+ year
								+ " AND PF_EMP_DIV IN ( "
								+ bean.getDivCode()
								+ " ) AND PF_TYPE = 'S'";
					}
				}

				if (bean.getReportOption().equals("A")) {

					salTotal = " SELECT NVL(SUM(ROUND(PF_EMP_BASIC)),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
							+ " WHERE PF_EMP_PF>0 AND PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN ( "
							+ bean.getDivCode()
							+ " ) AND PF_TYPE IN ('AM','AP')";
				}
				if (bean.getReportOption().equals("se")) {

					salTotal = " SELECT NVL(SUM(ROUND(PF_EMP_BASIC)),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0),NVL(SUM(ROUND(NVL(PF_VPF,0))),0), NVL(SUM(ROUND(PF_EMP_PF+PF_EMP_F_PF)),0) AS EMP_PF_TOTAL FROM HRMS_PF_DATA "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID)"
							+ " WHERE PF_EMP_PF > 0 AND PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV IN ("
							+ bean.getDivCode()
							+ " ) AND PF_TYPE = 'se'";
				}
				if (!bean.getBrnCode().equals("")){
					salTotal += " AND PF_EMP_BRN IN (" + bean.getBrnCode() + ")";
				}

				if (!bean.getDeptCode().equals("")){
					salTotal += " AND PF_EMP_DEPT IN (" + bean.getDeptCode() + ")";
				}
				if (!bean.getTypeCode().equals("")){
					salTotal += " AND PF_EMP_TYPE IN (" + bean.getTypeCode() + ")";
				}
				if (!bean.getGradeCode().equals(""))
					salTotal += " AND PF_EMP_GRADE IN (" + bean.getGradeCode() + ")";
				
				if (!bean.getPayBillNo().equals("")){
					salTotal += " AND PF_EMP_PAYBILL IN (" + bean.getPayBillNo() + ")";
				}
				if (!bean.getOnHold().equals("A"))
					salTotal += " AND PF_EMP_ONHOLD = '" + bean.getOnHold() + "'";
				salData = getSqlModel().getSingleResult(salTotal);
				
				double vpfAmt = 0.0;
				//UPDATED BY REEBA
				if(salData != null && salData.length >0 ){
					vpfAmt = Double.parseDouble(String.valueOf(salData[0][5]));
				}
				
				String percentContribution = "SELECT PF_EMPLOYEE, PF_EMP_FAMILY, PF_COMPANY, PF_CMP_FAMILY, PF_ADMIN_CHARGES, PF_EDLI_EMPCONT, PF_EDLI_ADMIN_CHARGES, PF_PERCENTAGE, PF_DATE FROM HRMS_PF_CONF WHERE " 
							+ " TO_NUMBER(NVL(TO_CHAR(PF_DATE,'yyyy'),0)) <="
							+ year
							+ " ORDER BY PF_DATE DESC";
				Object[][] contributionData = getSqlModel().getSingleResult(percentContribution);
				
				double pf_emp = Double.parseDouble("" + dataFormula[0][2]);
				double pf_emp_family = Double.parseDouble("" + dataFormula[0][3]);
				double pf_comp = Double.parseDouble("" + dataFormula[0][4]);
				double pf_comp_family = Double.parseDouble("" + dataFormula[0][5]);
				double pf_admin_charges = adminCharges;
				double pf_edli_empcont = empEdli;
				double pf_edli_admin_charges = cmpEdli;
				double pf_percentage = Double.parseDouble("" + dataFormula[0][1]);
				
				/*if(contributionData!=null && contributionData.length > 0){
					pf_emp = Double.parseDouble(String.valueOf(contributionData[0][0]));
					pf_emp_family = Double.parseDouble(String.valueOf(contributionData[0][1]));
					pf_comp = Double.parseDouble(String.valueOf(contributionData[0][2]));
					pf_comp_family = Double.parseDouble(String.valueOf(contributionData[0][3]));
					pf_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][4]));
					pf_edli_empcont = Double.parseDouble(String.valueOf(contributionData[0][5]));
					pf_edli_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][6]));
					pf_percentage = Double.parseDouble(String.valueOf(contributionData[0][7]));
				}*/
				
				
				if (salData != null && salData.length > 0) {
					NumberFormat formatter = new DecimalFormat("#0.00");
					
					String headerNames[] = new String[7];
					headerNames[0] = "Sr.No";
					headerNames[1] = "Item";
					headerNames[2] = "A/C No";
					headerNames[3] = "%";
					headerNames[4] = "(Rs.)";
					headerNames[5] = "%";
					headerNames[6] = "(Rs.)";
					int[] cellwidth1 = { 10, 15, 15, 15, 15, 15, 15};
					int[] alignment1 = { 1, 0, 1, 1, 2, 1, 2};
					
					Object twoDimobj[][] = new Object[5][7];
					
					twoDimobj[0][0] = "1";
					twoDimobj[0][1] = "Employees Provident Fund";
					twoDimobj[0][2] = "1";
					twoDimobj[0][3] = Utility.twoDecimals(pf_emp+pf_emp_family);
					twoDimobj[0][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][6])));
					twoDimobj[0][5] = pf_comp_family;
					twoDimobj[0][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][3])));
					
					twoDimobj[1][0] = "2";
					twoDimobj[1][1] = "Family Pension Scheme";
					twoDimobj[1][2] = "10";
					twoDimobj[1][3] = " ";
					twoDimobj[1][4] = " ";
					twoDimobj[1][5] = pf_comp;
					twoDimobj[1][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][2])));
					
					twoDimobj[2][0] = "3";
					twoDimobj[2][1] = "Employee Admin Charges";
					twoDimobj[2][2] = "2";
					twoDimobj[2][3] = " ";
					twoDimobj[2][4] = " ";
					twoDimobj[2][5] = pf_admin_charges;
					twoDimobj[2][6] = formatter.format(Math.round(Double.parseDouble(String.valueOf(salData[0][4]))	* pf_admin_charges / 100));
					
					twoDimobj[3][0] = "4";
					twoDimobj[3][1] = "Employee Deposit Linked \nInsurance (EDLI)";
					twoDimobj[3][2] = "21";
					twoDimobj[3][3] = " ";
					twoDimobj[3][4] = " ";
					twoDimobj[3][5] = pf_edli_empcont;
					twoDimobj[3][6] = formatter.format(Math.round(Double.parseDouble(String.valueOf(salData[0][4]))	* pf_edli_empcont / 100));
					
					twoDimobj[4][0] = "5";
					twoDimobj[4][1] = "Admin Charges on EDLI";
					twoDimobj[4][2] = "22";
					twoDimobj[4][3] = " ";
					twoDimobj[4][4] = " ";
					twoDimobj[4][5] = pf_edli_admin_charges;
					twoDimobj[4][6] = formatter.format(Math.round(Double.parseDouble(String.valueOf(salData[0][4]))	* pf_edli_admin_charges / 100));					             
					
					/*String query = " SELECT NVL(DIV_ADDRESS1 ||' '||DIV_ADDRESS2 ||' '||DIV_ADDRESS3,' ') AS Address FROM HRMS_DIVISION WHERE DIV_ID="
						+ bean.getDivCode().trim();
					Object divNameObj[][] = getSqlModel().getSingleResult(query);
					String divAddress = "";
					if (divNameObj != null && divNameObj.length > 0) {
						divAddress = String.valueOf(divNameObj[0][0]);
					}*/
					
					rg.addProperty(rg.PAGE_BREAK);
					
					TableDataSet filterData = new TableDataSet();
					filterData.setData(new Object[][]{{fetchFilters(bean)}});
					filterData.setCellAlignment(new int[]{0});
					filterData.setCellColSpan(new int[]{3});
					filterData.setCellWidth(new int[]{100});
					filterData.setBlankRowsBelow(1);
					filterData.setBodyFontStyle(0);
					rg.addTableToDoc(filterData);
					

					String summary = "SUMMARY OF PF CONTRIBUTION PAYMENTS FOR THE MONTH OF "
						+ Utility.month(Integer.parseInt(bean.getMonth())) + "-"
						+ bean.getYear();
					
					TableDataSet summaryData = new TableDataSet();
					summaryData.setData(new Object[][] {{summary}});
					summaryData.setCellAlignment(new int[] { 1 });
					summaryData.setCellWidth(new int[] { 100 });
					summaryData.setCellColSpan(new int[] { 7 });
					summaryData.setBorderDetail(0);
					summaryData.setBodyFontStyle(1);
					summaryData.setBlankRowsBelow(1);
					rg.addTableToDoc(summaryData);
					
					
					String bankHeader[]= {"","Amount to be paid in bank"};
					TableDataSet bankHeaderData = new TableDataSet();
					bankHeaderData.setCellAlignment(new int[] { 0, 1 });
					bankHeaderData.setHeaderColSpan(new int[]{3,4});
					bankHeaderData.setHeader(bankHeader);
					bankHeaderData.setCellWidth(new int[] { 10, 15, 15, 15, 15, 15, 15 });
					bankHeaderData.setData(null);
					bankHeaderData.setHeaderBorderDetail(3);
					rg.addTableToDoc(bankHeaderData);

					String mainHeader[]= {"","Employee's Contribution","Employer's Contribution"};
					TableDataSet mainHeaderData = new TableDataSet();
					mainHeaderData.setCellAlignment(new int[] { 0, 1, 1 });
					mainHeaderData.setHeaderColSpan(new int[]{3,2,2});
					mainHeaderData.setHeader(mainHeader);
					mainHeaderData.setCellWidth(new int[] { 10, 15, 15, 15, 15, 15, 15 });
					mainHeaderData.setData(null);
					mainHeaderData.setHeaderBorderDetail(3);
					rg.addTableToDoc(mainHeaderData);
					
					TableDataSet twoDimData = new TableDataSet();
					twoDimData.setHeader(headerNames);
					twoDimData.setData(twoDimobj);
					twoDimData.setCellAlignment(alignment1);
					twoDimData.setCellWidth(cellwidth1);
					twoDimData.setBorderDetail(3);
					twoDimData.setHeaderBorderDetail(3);
					rg.addTableToDoc(twoDimData);
					
					String total =  Utility.twoDecimals(Double.parseDouble(String.valueOf(twoDimobj[0][6]))+Double.parseDouble(String.valueOf(twoDimobj[1][6]))+Double.parseDouble(String.valueOf(twoDimobj[2][6]))+Double.parseDouble(String.valueOf(twoDimobj[3][6]))+Double.parseDouble(String.valueOf(twoDimobj[4][6]))); 
					String bankTotal = Utility.twoDecimals(String.valueOf(Double.parseDouble(String.valueOf(total))+Double.parseDouble(String.valueOf(salData[0][6]))));
					
					TableDataSet totalData = new TableDataSet();
					totalData.setData(new Object[][]{{"","","","Total :" ,Utility.twoDecimals(Double.parseDouble(String.valueOf(salData[0][6])))," ", total }});
					totalData.setCellAlignment(new int[]{1, 0, 1, 1, 2, 1, 2});
					totalData.setCellWidth(new int[]{10, 15, 15, 15, 15, 15, 15});
					totalData.setBorderDetail(0);
					totalData.setBorderLines(true);
					totalData.setBodyFontStyle(1);
					rg.addTableToDoc(totalData);

					TableDataSet amountDepositedData = new TableDataSet();
					amountDepositedData.setData(new Object[][] {{"AMOUNT TO BE DEPOSITED IN TO THE BANK : "+bankTotal}});
					amountDepositedData.setCellAlignment(new int[] { 0 });
					amountDepositedData.setCellWidth(new int[] { 100 });
					amountDepositedData.setCellColSpan(new int[] { 7 });
					amountDepositedData.setBorderDetail(0);
					amountDepositedData.setBodyFontStyle(1);
					rg.addTableToDoc(amountDepositedData);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	public boolean checkCondition(boolean brnFlag, boolean deptFlag,
			String dept1, String dept2, String brn1, String brn2) {

		boolean flag = ((brnFlag && !deptFlag && brn1.equals(brn2))
				|| (!brnFlag && deptFlag && dept1.equals(dept2)) || (brnFlag
				&& deptFlag && brn1.equals(brn2) && dept1.equals(dept2)));

		logger.info("flag-----------" + flag);
		return flag;

	}

	public org.paradyne.lib.ireportV2.ReportGenerator extraEDLITable(org.paradyne.lib.ireportV2.ReportGenerator rg,
			Object[][] totalObj, int bIndex, int eIndex, Object pfData[][],
			Object[][] empData) {
		try {

			Object edliObj[][] = new Object[4][2];
			int[] cellWidth = { 90,10 };
			int[] cellAlign = { 2,2 };
			double basicCount = 0;
			String pfAdminCharges="";
			String label2="";
			String label3="";
			String label4="";
			//if (String.valueOf(pfData[0][10]).equals("Y")) {
				for (int i = 0; i < empData.length; i++) {
					/*if (Double.parseDouble(String.valueOf(empData[i][bIndex])) >= Double
							.parseDouble(String.valueOf(pfData[0][11]))) {
						basicCount = basicCount
								+ Double.parseDouble(String
										.valueOf(pfData[0][11]));
					} else {*/
						basicCount = basicCount
								+ Double.parseDouble(String
										.valueOf(empData[i][eIndex]));
					//}
				}
				pfAdminCharges = formatter.format(Math.round((basicCount * Double.parseDouble(String.valueOf(pfData[0][5]))) / 100));
				/*} else {
				pfAdminCharges = formatter.format(Math.round((Double.parseDouble(String.valueOf(totalObj[0][bIndex])) * Double
										.parseDouble(String.valueOf(pfData[0][5]))) / 100));
			}*/
			edliObj[0][0] = "ADD : PF,PFS ADMINISTRATION CGS.(@"+ String.valueOf(pfData[0][5])+ "% OF TOTAL BASIC SALARY ) : ";
			edliObj[0][1] = pfAdminCharges;
			
			
			label2 = formatter.format(Math.round((Double.parseDouble(String.valueOf(totalObj[0][eIndex])) * Double.parseDouble(String.valueOf(pfData[0][6]))) / 100));
			
			edliObj[1][0] = "ADD : EDLI EMPLOYER'S CONTRIBUTION(@"+ String.valueOf(pfData[0][6]) + "% ON EDLI SALARY) : ";
			edliObj[1][1] = label2;
			
			label3 = formatter.format(Math.round((Double.parseDouble(String.valueOf(totalObj[0][eIndex])) * Double.parseDouble(String.valueOf(pfData[0][7]))) / 100));
			
			edliObj[2][0] = "ADD : EDLI ADMINISTRATION CGS.(@"+ String.valueOf(pfData[0][7]) + "% ON EDLI SALARY)) : ";
			edliObj[2][1] = label3;
			
			label4 = formatter.format(Double.parseDouble(pfAdminCharges)
									+ Double.parseDouble(label2)
									+ Double.parseDouble(label3)
									+ Double.parseDouble(String.valueOf(totalObj[0][6]))
									+ Double.parseDouble(String.valueOf(totalObj[0][10]))
									+ Double.parseDouble(String.valueOf(totalObj[0][11])));
			
			edliObj[3][0] = "TOTAL PAYMENT TO BE MADE : ";
			edliObj[3][1] = label4;
			TableDataSet twoDimData = new TableDataSet();
			twoDimData.setData(edliObj);
			twoDimData.setCellColSpan(new int[] {13,1});
			twoDimData.setCellAlignment(cellAlign);
			twoDimData.setCellWidth(cellWidth);
			twoDimData.setBorderDetail(0);
			rg.addTableToDoc(twoDimData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return rg;
	}

	public static String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

	public Object[][] getVPfDetails(PFReport bean, String ledgerCode,
			String basicFormula) {
		Object[][] result = null;
		try {
			String vpfApplicablQuery = " SELECT CONF_VPF FROM HRMS_SALARY_CONF ";
			Object[][] resultObj = getSqlModel().getSingleResult(
					vpfApplicablQuery);
			if (resultObj != null && resultObj.length > 0) {
				if (String.valueOf(resultObj[0][0]).equals("Y")) {
					String vpfCodeQUery = " SELECT VPF_DEBIT_CODE FROM HRMS_VPF_CONF ";
					Object[][] vpfObj = getSqlModel().getSingleResult(
							vpfCodeQUery);
					if (vpfObj != null && vpfObj.length > 0) {

						String empVpf = " SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME as name,'0',SAL_AMOUNT, "
								+ " HRMS_EMP_OFFC.EMP_ID,EMP_CENTER,EMP_DEPT,CENTER_NAME,DEPT_NAME "
								+ " FROM HRMS_SALARY_"
								+ bean.getYear()
								+ " LEFT JOIN HRMS_SAL_DEBITS_"
								+ bean.getYear()
								+ "  on ( HRMS_SAL_DEBITS_"
								+ bean.getYear()
								+ ".EMP_ID =  HRMS_SALARY_"
								+ bean.getYear()
								+ ".EMP_ID "
								+ " AND HRMS_SAL_DEBITS_"
								+ bean.getYear()
								+ ".SAL_LEDGER_CODE = "
								+ ledgerCode
								+ " and HRMS_SAL_DEBITS_"
								+ bean.getYear()
								+ ".SAL_DEBIT_CODE = "
								+ String.valueOf(vpfObj[0][0])
								+ " )"
								+ " LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
								+ bean.getYear()
								+ ".EMP_ID "
								+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER "
								+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID= HRMS_EMP_OFFC.EMP_DEPT "
								+ " WHERE HRMS_SALARY_"
								+ bean.getYear()
								+ ".SAL_LEDGER_CODE = "
								+ ledgerCode// +" AND SAL_DEBIT_CODE =
								// "+String.valueOf(vpfObj[0][0])
								+ " AND SAL_AMOUNT > 0 AND EMP_DIV IN ( "
								+ bean.getDivCode()+")";

						if (!bean.getOnHold().equals("A"))
							empVpf += " AND HRMS_SALARY_" + bean.getYear()
									+ ".SAL_ONHOLD = '" + bean.getOnHold()
									+ "'";

						if (!bean.getBrnCode().equals(""))
							empVpf += " AND HRMS_SALARY_" + bean.getYear()
									+ ".SAL_EMP_CENTER IN (" + bean.getBrnCode()+ ")";

						if (!bean.getDeptCode().equals(""))
							empVpf += " AND HRMS_SALARY_" + bean.getYear()
									+ ".SAL_DEPT IN (" + bean.getDeptCode() + ")";

						if (!bean.getTypeCode().equals(""))
							empVpf += " AND HRMS_SALARY_" + bean.getYear()
									+ ".SAL_EMP_TYPE IN (" + bean.getTypeCode()	+ ")";

						if (!bean.getPayBillNo().equals(""))
							empVpf += " AND HRMS_SALARY_" + bean.getYear()
									+ ".SAL_EMP_PAYBILL IN (" + bean.getPayBillNo()+")";

						String orderQuery = " ORDER BY";

						if (bean.getBranchFlag().equals("true"))
							orderQuery += " SAL_EMP_CENTER,";

						if (bean.getDeptFlag().equals("true"))
							orderQuery += " SAL_DEPT,";

						orderQuery += " UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

						empVpf = empVpf + orderQuery;

						logger.info("vpfQuery---------------" + empVpf);

						result = getSqlModel().getSingleResult(empVpf);

						if (result != null && result.length > 0) {

							for (int i = 0; i < result.length; i++) {
								double basicAmt = 0.00;
								try {

									String creditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"
											+ bean.getYear()
											+ " WHERE SAL_LEDGER_CODE IN ("
											+ ledgerCode
											+ ") AND EMP_ID ="
											+ String.valueOf(result[i][4]);

									Object[][] empCredit = getSqlModel()
											.getSingleResult(creditQuery);

									if (empCredit != null
											&& empCredit.length > 0) {

										basicAmt = Utility
												.expressionEvaluate(new Utility()
														.generateFormulaPay(
																empCredit,
																basicFormula,
																context,
																session));

										result[i][2] = formatter.format(basicAmt);

									}

								} catch (Exception e) {
									logger.error("Exception in getSalary in geting the basic amount using formula for employee "+ e);
									// e.printStackTrace();
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error in getting VPF object in getVPfDetails " + e);
		}
		return result;
	}

	public Object[][] intSpace(Object[][] tempObj) {
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "";
				}
			}
		} catch (Exception e) {

		}
		return tempObj;
	}
	
	private String fetchFilters(PFReport bean) {
		Date now = new Date();
			String month = bean.getMonth();
			String year = bean.getYear();
			
			
			String filters = "Report Period : " + Utility.month(Integer.parseInt(month)) + " - " + year;

			if(!bean.getDivCode().equals("")){
				filters += "\n\nDivision : " + bean.getDivName();
			}
			if(!bean.getBrnCode().equals("")){
				filters += "\n\nBranch : " + bean.getBrnName();
			}
			if(!bean.getDeptCode().equals("")){
				filters += "\n\nDepartment : " + bean.getDeptName();
			}
			if(!bean.getPayBillNo().equals("")){
				filters += "\n\nPaybill : " + bean.getPayBillName();
			}
			if(!bean.getTypeCode().equals("")){
				filters += "\n\nEmployee Type : " + bean.getTypeName();
			}
		return filters;
	}
}