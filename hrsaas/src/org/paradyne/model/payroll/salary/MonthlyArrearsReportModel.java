package org.paradyne.model.payroll.salary;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.salary.MonthlyArrearsReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import org.paradyne.lib.Utility;

/*
 * Author:Balaji and Pradeep
 * Date:31-07-2008
 */
public class MonthlyArrearsReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	NumberFormat formatter = new DecimalFormat("#0.00");

	
	public void genRep(MonthlyArrearsReport bean, HttpServletRequest request, HttpServletResponse response,
			String chekFlag, String reportPath) {

		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		try {
			String reportType = bean.getReportType();
			ReportDataSet rds = new ReportDataSet();
			String fileName = "Arrears_"
				+ Utility.month(Integer.parseInt(bean.getMonth())).substring(0, 3) 
				+ bean.getYear().substring(bean.getYear().length()-2, bean.getYear().length())
				+"_"+ Utility.getRandomNumber(1000);
			String reportName = "Arrears Report  ";
					//+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
					//+ bean.getYear();
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(reportName);
			rds.setPageSize("TABLOID");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(8);
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context,request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ reportPath + fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ reportType);
				request.setAttribute("action","MonthlyArrearsReport_input.action");
				request.setAttribute("fileName", fileName + "."	+ reportType);
			}
			String filters = "Period : "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
			if(!bean.getDivCode().equals("")){
				filters+="\nDivision : "+bean.getDivName();
			}
			if(!bean.getBranchCode().equals("")){
				filters+="\nBranch : "+bean.getBranchName();
			}
			if(!bean.getDeptCode().equals("")){
				filters+="\nDepartment : "+bean.getDeptName();
			}
			if(!bean.getPaybillid().equals("")){
				filters+="\nPaybill : "+bean.getPaybillname();
			}
			if(!bean.getGradeId().equals("")){
				filters+="\nGrade : "+bean.getGradeName();
			}
			if(!bean.getCostcenterid().equals("")){
				filters+="\nCost Center : "+bean.getCostcentername();
			}
			
			if(bean.getSelectarrearType().equalsIgnoreCase("P")){
				filters+="\nArrear Type : Promotion Arrears";
			} else {
				filters+="\nArrear Type : Monthly Arrears";
			}
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{filters}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellWidth(new int[]{100});
			//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorderDetail(0);
			filterData.setBlankRowsBelow(1);
			filterData.setCellColSpan(new int[] {5});
			rg.addTableToDoc(filterData);
			
			rg=getRecords(rg, bean, chekFlag,"A");
			
			rg=getRecords(rg, bean, chekFlag,"R");
			
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				
				rg.saveReport(response);
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * following function is called to genearte the records when Branch wise and
	 * department wise is not checked.
	 * 
	 * @param rg
	 * @param bean
	 */
	public ReportGenerator getRecords(ReportGenerator rg, MonthlyArrearsReport bean,
			String chekFlag,String arreaspaytype) {
		try {
			ArrayList totList = new ArrayList();
			int recCount = 0;
			int arrEmpLength = 0;
			
			String sqlQuery="";			
			int chkcnt=0;
			
			if (!bean.getReportdivision().equals("")) {
				sqlQuery += ",NVL(HRMS_DIVISION.DIV_NAME,'')";
				chkcnt += 1;
			}
			if (!bean.getReportbranch().equals("")) {
				sqlQuery += ",NVL(HRMS_CENTER.CENTER_NAME,'')";
				chkcnt += 1;
			}
			if (!bean.getReportdept().equals("")) {
				sqlQuery += ",NVL(HRMS_DEPT.DEPT_NAME,'')";
				chkcnt += 1;
			}
			if (!bean.getReportmicrcode().equals("")) {
				sqlQuery += ",HRMS_SALARY_MISC.SAL_MICR_REGULAR";
				chkcnt += 1;
			}
			if (!bean.getReportgrade().equals("")) {
				sqlQuery += ",NVL(HRMS_CADRE.CADRE_NAME,'')";
				chkcnt += 1;
			}
			if (!bean.getReportaccno().equals("")) {
				sqlQuery += ",NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')";
				chkcnt += 1;
			}
			if (!bean.getReportbank().equals("")) {
				sqlQuery += ",NVL(HRMS_BANK.BANK_NAME,'')";
				chkcnt += 1;
			}
			
			String query = " SELECT DISTINCT HRMS_ARREARS_"
					+ bean.getYear()
					+ ".EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME" ;
			if(!bean.getReportempType().equals(""))	{
				query +=  ",HRMS_EMP_TYPE.TYPE_NAME"; 
				chkcnt+=1;
			}
			query += sqlQuery + ",HRMS_ARREARS_LEDGER.ARREARS_CODE";
			if(chekFlag.equals("Y")){
				query += " ,''  ,'',''" ;
			}else{
				query += " ,HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_MONTH, HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_YEAR, HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_DAYS" ;
			}
			
			
			query += "   FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_ARREARS_"
					+ bean.getYear()
					+ " ON(HRMS_ARREARS_"
					+ bean.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
					+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_CODE)  "
					+ " 	 LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
					+ bean.getYear()
					+ ".EMP_ID) "
					+ "LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+ "LEFT JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ "LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ "LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ "LEFT JOIN HRMS_COST_CENTER ON (HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_COST_CENTER.COST_CENTER_ID)"
					+ "	WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="
					+ bean.getMonth()
					+ "AND HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_PAY_TYPE = '"+arreaspaytype
					+"' AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR=" + bean.getYear()
					+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE ='" + bean.getArrearType() + "'";
			if (!(bean.getOnHold().equals("A"))) {
				if (bean.getOnHold().equals("N")) {
					query += " AND ARREARS_ONHOLD = 'N' ";
				} else if (bean.getOnHold().equals("Y")) {
					query += " AND ARREARS_ONHOLD = 'Y'";
				}
			}
			if (!(bean.getDivName().equals("") || bean.getDivName().equals(
					"null"))) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + bean.getDivCode() +") ";
			}
			if (!(bean.getBranchName().equals("") || bean.getBranchName()
					.equals("null"))) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + bean.getBranchCode()+") ";
			}
			if (!(bean.getDeptName().equals("") || bean.getDeptName().equals(
					"null"))) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN (" + bean.getDeptCode()+") ";
			}
			if (!bean.getPaybillname().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillid() + ") ";
			}
			if (!bean.getGradeName().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
						+ bean.getGradeId() + ") ";
			}
			if (!bean.getCostcentername().equals("")) {
				query += " AND HRMS_SALARY_MISC.COST_CENTER_ID IN ("
						+ bean.getCostcenterid() + ") ";
			}
			query += " ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ";
				if(!chekFlag.equals("Y"))
				{
					query += " ,HRMS_ARREARS_"+ bean.getYear() + ".ARREARS_MONTH ";
				}
		
			String brnCode = "";
			String deptCode = "";
			Object[][] reportDataPay = getReportDataNoBranchDept(query, bean,
					brnCode, deptCode, arrEmpLength, chekFlag,arreaspaytype);// getReportDataNotSelect(selectSalaryLoop,salReg,String.valueOf(loop_data[a][0]),String.valueOf(loop_data_inner[b][0]),arrEmpLength);//getReportData(selectSalaryLoop,salReg);
			if (reportDataPay != null) {
				String finalHeader[] = new String[reportDataPay[0].length];
				int[] cellWidth = new int[reportDataPay[0].length];
				int[] alignment = new int[reportDataPay[0].length];
				
				int[] cellWidth1 = new int[cellWidth.length - 1];
				
				//boolean [] cellNoWrap = new boolean[reportDataPay[0].length];
				for (int i = 0; i < reportDataPay[0].length; i++) {
					finalHeader[i] = String.valueOf(reportDataPay[0][i]);
					alignment[i] = 0;
					if (i > 1) {
						cellWidth[i] = 3;
						alignment[i] = 2;
					} else {
						cellWidth[0] = 5;
						cellWidth[1] = 10;
					}
				}
				cellWidth1[0] = cellWidth[0] + cellWidth[1];
				for (int i = 2; i < cellWidth.length; i++) {
					cellWidth1[i-1] = cellWidth[i]; 
				}
				/*for (int i = 0; i < reportDataPay[0].length; i++) {
					if(i > 1 && (reportDataPay[0][i].toString().equals("TOT CREDIT")
						|| reportDataPay[0][i].toString().equals("TOT DEBIT")
						|| reportDataPay[0][i].toString().equals("NET-PAY"))){
							cellNoWrap[i] = true;
					} else {
						cellNoWrap[i] = false;
					}
				}*/
				

				Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
				for (int i = 0; i < finalData.length; i++) {
					for (int j = 0; j < finalData[0].length; j++) {
						finalData[i][j] = reportDataPay[i + 1][j];
						// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
					}
				}

				Object totalByColumn[][] = new Object[1][finalData[0].length-1];
				String totalHeader[] = new String[finalData.length];
				totalHeader[0] = "";
				totalByColumn[0][0] = "Total No of Employees :- " + finalData.length;
				
				// totalHeader[1] = "";
				totalByColumn[0][1] = " ";
				int[] cellColSpan = new int[finalData[0].length-1];
				cellColSpan[0] = 2;
				int[] alignment1 = new int[alignment.length-1];
				alignment1[0] = alignment[0] + alignment[1];
				
				
				for (int i = 5+chkcnt; i < finalData[0].length; i++) {
					double total = 0.00;
					
					for (int j = 0; j < finalData.length; j++) {
						
						if (String.valueOf(finalData[j][i]).equals("null")
								|| String.valueOf(finalData[j][i]).equals("")
								|| String.valueOf(finalData[j][i]).equals(" ")
								|| String.valueOf(finalData[j][i]).equals(
										"null.00")) {
							finalData[j][i] = "0.00";
						}
						
						total += Double.parseDouble(String
								.valueOf(finalData[j][i]));
					}
					// totalHeader[i] = "";
					totList.add(Utility.twoDecimals(total));
					totalByColumn[0][i-1] = Utility.twoDecimals(total);
					cellColSpan[i-1] = 1;
					alignment1[i-1] = alignment[i];
					//cellWidth1[i-1] = cellWidth[i];
				}
				//rg.addText("\n", 0, 0, 0);
			//	rg.tableBody(finalHeader, finalData, cellWidth, alignment);
			//	rg.tableBody(totalByColumn, cellWidth, alignment);
				TableDataSet titleData = new TableDataSet();
				if(arreaspaytype.equals("A"))
				{
					//titleData.setData(new Object[][] {{ "Monthly Arrears"}});
					if(bean.getSelectarrearType().equalsIgnoreCase("P")){
						titleData.setData(new Object[][] {{ "Promotion Arrears"}});
					} else {
						titleData.setData(new Object[][] {{ "Monthly Arrears"}});
					}
				}
				else
				{
					titleData.setData(new Object[][] {{ "Arrears Recovery"}});
				}
				
				titleData.setCellAlignment(new int[] {  0 });
				titleData.setCellWidth(new int[] { 100 });
				titleData.setCellColSpan(new int[] { 10 });
				titleData.setBodyFontStyle(1);
				//titleData.setBlankRowsBelow(1);
				titleData.setBorder(false);
				rg.addTableToDoc(titleData);
				TableDataSet tdstable = new TableDataSet();
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeader(finalHeader);
				//tdstable.setHeaderLines(true);
				//tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
				tdstable.setHeaderBorderDetail(3);
				
				tdstable.setData(finalData);
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellWidth);
				//tdstable.setCellNoWrap(cellNoWrap);
				//tdstable.setHeaderNoWrap(cellNoWrap);
				tdstable.setHeaderTable(true);
				//tdstable.setBorderDetail(3);
				tdstable.setBorderDetail(3);
				/*tdstable.setHeaderBGColor(new BaseColor(225,
						225, 225));*/
				//tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				
				
				
				TableDataSet totalData = new TableDataSet();
				totalData.setData(totalByColumn);
				//totalData.setCellAlignment(alignment1);
				totalData.setCellAlignment(alignment1);
				//totalData.setCellWidth(cellWidth1);
				totalData.setCellWidth(cellWidth1);
				totalData.setCellColSpan(cellColSpan);
				//totalData.setCellNoWrap(cellNoWrap);
				//totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
				totalData.setBodyFontStyle(1);
				totalData.setBorderLines(true);
				totalData.setBorderDetail(2);

				//totalData.setHeaderTable(true);
				totalData.setBlankRowsBelow(1);
				rg.addTableToDoc(totalData);
				recCount++;
				// colCount=finalData[0].length;
			}
			if (recCount != 0) {
				Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

				int arrCount = 0;
				for (int i = 0; i < recCount; i++) {
					for (int j = 0; j < (totList.size() / recCount); j++) {
						listValues[i][j] = totList.get(arrCount);
						arrCount++;
						/*logger.info("-----------------values are listValues["
								+ i + "][" + j + "]=" + listValues[i][j]);*/
					}
				}

				Object grand_total[][] = new Object[1][listValues[0].length + 8];

				grand_total[0][0] = "Grand TOTAL :-";
				grand_total[0][1] = " ";
				grand_total[0][2] = " ";
				grand_total[0][3] = " ";
				grand_total[0][4] = " ";
				// grand_total[0][5]=" ";

				for (int i = 0; i < listValues[0].length; i++) {
					double total = 0.00;
					for (int j = 0; j < listValues.length; j++) {
						if (String.valueOf(listValues[j][i]).equals("null")) {
							listValues[j][i] = "0.00";
						}
						total += Double.parseDouble(String
								.valueOf(listValues[j][i]));
					}
					grand_total[0][i + 8] = Utility.twoDecimals(total);
				}
				int[] cellWidth = getCellWidth(grand_total[0].length);
				int[] alignment = getAlignment(grand_total[0].length);
				/*TableDataSet grandtotalData = new TableDataSet();
				grandtotalData.setData(grand_total);
				grandtotalData.setCellAlignment(alignment);
				grandtotalData.setCellWidth(cellWidth);
				//totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
				
				grandtotalData.setBorderDetail(2);				
				//totalData.setHeaderTable(true);
				grandtotalData.setBlankRowsBelow(1);
				rg.addTableToDoc(grandtotalData);*/
				//rg.addText("\n", 0, 0, 0);
				//rg.addText("GRAND TOTAL", 0, 0, 0);
				//rg.tableBody(grand_total, cellWidth, alignment);
			} else {
				//rg.addText("Records are not available.", 0, 1, 0);
				TableDataSet titleData = new TableDataSet();
				if (arreaspaytype.equals("A")) {
					titleData.setData(new Object[][] { { "Monthly Arrears : Records are not available." } });
				} else {
					titleData.setData(new Object[][] { { "Arrears Recovery : Records are not available." } });
				}
				titleData.setCellAlignment(new int[] { 0 });
				titleData.setCellWidth(new int[] { 100 });
				titleData.setBodyFontStyle(1);
				titleData.setBlankRowsAbove(1);
				titleData.setCellColSpan(new int[] { 8 });
				//titleData.setBlankRowsBelow(1);
				titleData.setBorder(false);
				rg.addTableToDoc(titleData);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rg;

	}

	/**
	 * following function is called in the action class to generate the report.
	 * 
	 * @param bean
	 * @param response
	 */
	public void generateDeptReport(MonthlyArrearsReport bean,
			HttpServletResponse response, String chekFlag) {
		org.paradyne.lib.report.ReportGenerator rg = null;
		try {
			String reportType = bean.getReport();
			String reportName = "Arrears Report  "
					+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
					+ bean.getYear();
			rg = new org.paradyne.lib.report.ReportGenerator(reportType,
					reportName, "A4");
			rg.setFName(reportName + "." + reportType);
			if (reportType.equals("Pdf")) {
				rg.addTextBold("Arrears Report of "+bean.getDivName()+" for the month of "
						+ Utility.month(Integer.parseInt(bean.getMonth()))
						+ " " + bean.getYear() + " ", 0, 1, 0);
			} else {
				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = "Arrears Report of "+bean.getDivName()+" for the month of "
						+ Utility.month(Integer.parseInt(bean.getMonth()))
						+ " " + bean.getYear() + " ";

				int[] cols = { 20, 20, 30 };
				int[] align = { 0, 0, 1 };
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			}
			// rg.addText("Arrears Report for the month of
			// "+Utility.month(Integer.parseInt(bean.getMonth()))+"
			// "+bean.getYear()+" ",0, 1,0);
			rg.addText("\n", 0, 1, 0);
			Object[][] loop_data = null;
				if(bean.getDeptCode().equals("")){
				String selectQuery = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT";
				loop_data = getSqlModel().getSingleResult(
						selectQuery);
				
				}else{
					loop_data =new Object[1][2];
					loop_data [0][0] =bean.getDeptCode();
					loop_data [0][1] =bean.getDeptName();
				}
				//reportDataDept(loop_data, rg, bean, chekFlag);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		rg.createReport(response);
	}// REPORT METHOD ENDS

	public void generateBrnReport(MonthlyArrearsReport bean,
			HttpServletResponse response, String chekFlag) {
		org.paradyne.lib.report.ReportGenerator rg = null;
		try {
			String reportType = bean.getReport();
			String reportName = "Arrears Report  "
					+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
					+ bean.getYear();
			rg = new org.paradyne.lib.report.ReportGenerator(reportType,
					reportName, "A4");
			rg.setFName(reportName + "." + reportType);
			if (reportType.equals("Pdf")) {
				rg.addTextBold("Arrears Report of "+bean.getDivName()+" for the month of "
						+ Utility.month(Integer.parseInt(bean.getMonth()))
						+ " " + bean.getYear() + " ", 0, 1, 0);
			} else {
				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = "Arrears Report of "+bean.getDivName()+" for the month of "
						+ Utility.month(Integer.parseInt(bean.getMonth()))
						+ " " + bean.getYear() + " ";

				int[] cols = { 20, 20, 30 };
				int[] align = { 0, 0, 1 };
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			}
			// rg.addText("Arrears Report for the month of
			// "+Utility.month(Integer.parseInt(bean.getMonth()))+"
			// "+bean.getYear()+" ",0, 1,0);
			rg.addText("\n", 0, 1, 0);
			Object[][] loop_data =null;
				if(bean.getBranchCode().equals("")){
				String selectQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
				loop_data = getSqlModel().getSingleResult(
						selectQuery);
				}else{
					loop_data =new Object[1][2];
					loop_data [0][0] =bean.getBranchCode();
					loop_data [0][1] =bean.getBranchName();
				}
				//reportDataBranch(loop_data, rg, bean, chekFlag);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		rg.createReport(response);
	}// REPORT METHOD ENDS
	/**
	 * following function is called when no branch and no department are
	 * selected
	 * 
	 * @param loop_data
	 * @param rg
	 * @param bean
	 */
	public void reportDataNoSelect(Object[][] loop_data, ReportGenerator rg,
			MonthlyArrearsReport bean, String chekFlag,String arreaspaytype) {

		try {
			ArrayList totList = new ArrayList();
			int recCount = 0;
			int arrEmpLength = 0;
			String arrearEmp = "";
			String selectQueryInner = "SELECT DEPT_ID,DEPT_NAME from HRMS_DEPT";
			Object[][] loop_data_inner = getSqlModel().getSingleResult(
					selectQueryInner);
			if (loop_data.length > 0) {
				for (int a = 0; a < loop_data.length; a++) {

					for (int b = 0; b < loop_data_inner.length; b++) {

						try {
						/*	arrearEmp = " SELECT HRMS_ARREARS_"
									+ bean.getYear()
									+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH  ,ARREARS_YEAR ,ARREARS_DAYS ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
									+ "	INNER JOIN HRMS_ARREARS_"
									+ bean.getYear()
									+ " ON(HRMS_ARREARS_"
									+ bean.getYear()
									+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
									+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
									+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
									+ bean.getYear()
									+ ".ARREARS_CODE)  "
									+ "  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
									+ bean.getYear() + ".EMP_ID) 
									"*/
							
							  arrearEmp = " SELECT DISTINCT HRMS_ARREARS_"
								+ bean.getYear()
								+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME";
								if(!bean.getReportempType().equals(""))	{
									arrearEmp +=  ",TYPE_NAME"; 
								}
								arrearEmp +=  ",HRMS_ARREARS_LEDGER.ARREARS_CODE";
						if(chekFlag.equals("Y")){
							arrearEmp += " ,''  ,'',''" ;
						}else{
							arrearEmp += " ,ARREARS_MONTH  ,ARREARS_YEAR,ARREARS_DAYS" ;
						}
						
						
						arrearEmp += " ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
								+"	INNER JOIN HRMS_ARREARS_"
								+ bean.getYear()
								+ " ON(HRMS_ARREARS_"
								+ bean.getYear()
								+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
								+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
								+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
								+ bean.getYear()
								+ ".ARREARS_CODE)  "
								+ " 	 LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
								+ bean.getYear() + ".EMP_ID) "
							 	+ "	WHERE ARREARS_PAID_MONTH="
								+ bean.getMonth()
								+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
								+ " and arrears_type ='"
								+ bean.getArrearType() + "'";

							if (!(bean.getOnHold().equals("A"))) {
								if (bean.getOnHold().equals("N")) {
									arrearEmp += " AND ARREARS_ONHOLD = 'N' ";
								} else if (bean.getOnHold().equals("Y")) {
									arrearEmp += " AND ARREARS_ONHOLD = 'Y'";
								}
							}

							String where = " AND EMP_CENTER IN (" + loop_data[a][0]
									+ ") ";

							where += " AND EMP_DEPT IN (" + loop_data_inner[b][0] +") ";

							arrearEmp = arrearEmp + where;

							if (!(bean.getDivName().equals("") || bean
									.getDivName().equals("null"))) {
								arrearEmp += " AND EMP_DIV IN ("
										+ bean.getDivCode()+") ";
							}
							arrearEmp += " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
							if(!chekFlag.equals("Y"))
							arrearEmp += " ,HRMS_ARREARS_"+ bean.getYear() + ".ARREARS_MONTH ";

						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						Object[][] reportDataPay = getReportDataNoBranchDept(
								arrearEmp, bean, String
										.valueOf(loop_data[a][0]), String
										.valueOf(loop_data_inner[b][0]),
								arrEmpLength, chekFlag,arreaspaytype);// getReportDataNotSelect(selectSalaryLoop,salReg,String.valueOf(loop_data[a][0]),String.valueOf(loop_data_inner[b][0]),arrEmpLength);//getReportData(selectSalaryLoop,salReg);
						if (reportDataPay != null) {
							String finalHeader[] = new String[reportDataPay[0].length];
							int[] cellWidth = new int[reportDataPay[0].length];
							int[] alignment = new int[reportDataPay[0].length];
							for (int i = 0; i < reportDataPay[0].length; i++) {
								finalHeader[i] = String
										.valueOf(reportDataPay[0][i]);
								alignment[i] = 0;
								if (i > 1) {
									cellWidth[i] = 7;
									alignment[i] = 0;
								} else {
									cellWidth[0] = 8;
									cellWidth[1] = 15;
								}
							}

							Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
							for (int i = 0; i < finalData.length; i++) {
								for (int j = 0; j < finalData[0].length; j++) {
									finalData[i][j] = reportDataPay[i + 1][j];
									// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
								}
							}

							Object totalByColumn[][] = new Object[1][finalData[0].length];

							String totalHeader[] = new String[finalData.length];
							totalHeader[0] = "";
							totalByColumn[0][0] = "TOTAL :-";

							// totalHeader[1] = "";
							totalByColumn[0][1] = " ";
							/**
							 * following loop is used to calculate the total
							 * amount column wise
							 */
							for (int i = 8; i < finalData[0].length; i++) {
								double total = 0;
								for (int j = 0; j < finalData.length; j++) {
									if (String.valueOf(finalData[j][i]).equals(
											"null")
											|| String.valueOf(finalData[j][i])
													.equals("null.00")
											|| String.valueOf(finalData[j][i])
													.equals("")
											|| String.valueOf(finalData[j][i]) == null) {
										finalData[j][i] = "0";
									}
									total += Double.parseDouble(String
											.valueOf(finalData[j][i]));
								}
								// totalHeader[i] = "";
								totList.add(Utility.twoDecimals(total));
								totalByColumn[0][i] = Utility
										.twoDecimals(total);
							}
							TableDataSet tdstable = new TableDataSet();
							tdstable.setBlankRowsAbove(1);
							tdstable.setHeader(finalHeader);
							tdstable.setHeaderLines(true);
							tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
							tdstable.setData(finalData);
							tdstable.setCellAlignment(alignment);
							tdstable.setCellWidth(cellWidth);
							tdstable.setHeaderTable(true);
							//tdstable.setBorderDetail(3);
							tdstable.setBorderDetail(3);
							/*tdstable.setHeaderBGColor(new BaseColor(225,
									225, 225));*/
							tdstable.setBlankRowsBelow(1);
							rg.addTableToDoc(tdstable);
							
							TableDataSet totalData = new TableDataSet();
							totalData.setData(totalByColumn);
							totalData.setCellAlignment(alignment);
							totalData.setCellWidth(cellWidth);
							//totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
							
							totalData.setBorderDetail(3);				
							//totalData.setHeaderTable(true);
							totalData.setBlankRowsBelow(1);
							rg.addTableToDoc(totalData);
							//rg.addText("\n", 0, 0, 0);
							//rg.addText("Branch : " + loop_data[a][1]
									//+ "   Department : "
									//+ loop_data_inner[b][1], 0, 0, 0);
							//rg.tableBody(finalHeader, finalData, cellWidth,
									//alignment);
							//rg.tableBody(totalByColumn, cellWidth, alignment);
							recCount++;
							// colCount=finalData[0].length;
						}
					}// End of department for loop
				}// End of branch for loop
				if (recCount != 0) {
					Object[][] listValues = new Object[recCount][(totList
							.size() / recCount)];

					int arrCount = 0;
					for (int i = 0; i < recCount; i++) {
						for (int j = 0; j < (totList.size() / recCount); j++) {
							listValues[i][j] = totList.get(arrCount);
							arrCount++;
							/*logger
									.info("-----------------values are listValues["
											+ i
											+ "]["
											+ j
											+ "]="
											+ listValues[i][j]);*/
						}
					}

					Object grand_total[][] = new Object[1][listValues[0].length + 8];

					grand_total[0][0] = "TOTAL   :-";
					grand_total[0][1] = "    ";
					grand_total[0][2] = "     ";
					grand_total[0][3] = "    ";
					grand_total[0][4] = "    ";
					// grand_total[0][5]=" ";
					/**
					 * following loop is used to calculate the grand total
					 * amount
					 */

					for (int i = 0; i < listValues[0].length; i++) {
						double total = 0.00;
						for (int j = 0; j < listValues.length; j++) {
							if (String.valueOf(listValues[j][i]).equals("null")) {
								listValues[j][i] = "0.00";
							}
							total += Double.parseDouble(String
									.valueOf(listValues[j][i]));
						}
						grand_total[0][i + 8] = Utility.twoDecimals(total);
					}
					int[] cellWidth = getCellWidth(grand_total[0].length);
					int[] alignment = getAlignment(grand_total[0].length);

					//rg.addText("\n", 0, 0, 0);
					//rg.addText("GRAND TOTAL", 0, 0, 0);
					//rg.tableBody(grand_total, cellWidth, alignment);
				} else {
					//rg.addText("Records are not available.", 0, 1, 0);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object[][] getEmpIdNew(String selectSalary, MonthlyArrearsReport bean) {
		Object emp_id[][] = null;
		try {
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}

	public Object[][] getCreditHeaderArrear(String empId, String month,
			String year, String arrearsCode, MonthlyArrearsReport bean, String arreaspaytype ) {

			logger.info("getCreditHeaderArrear==");
		String arrCred = "SELECT DISTINCT ARREARS_CREDIT_CODE,CREDIT_ABBR, ";
			
			if(bean.getCheckFlag().equals("Y"))
			{
				arrCred +=" 0 ";
			}
			else
			{
				arrCred +=" ARREARS_AMT ";
			}
		arrCred +="	ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"
				+ bean.getYear()
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"
				+ bean.getYear()
				+ ".ARREARS_CREDIT_CODE)"
				+ " WHERE ARREARS_CODE="
				+ arrearsCode
				+ " AND ARREARS_EMP_ID="
				+ empId 
				+ " AND HRMS_ARREARS_CREDIT_"
				+ bean.getYear()
				+ ".ARREARS_PAY_TYPE = '"+arreaspaytype	+ "'"
				;
		
		if(!bean.getCheckFlag().equals("Y"))
		{
			arrCred +=" AND ARREARS_MONTH="+ month
				+ " AND ARREARS_YEAR="+ year ;
		}
		arrCred +=" ORDER BY HRMS_ARREARS_CREDIT_"+ bean.getYear()
				+ ".ARREARS_CREDIT_CODE";

		Object[][] arrCredData = getSqlModel().getSingleResult(arrCred);
		return arrCredData;

	}

	/**
	 * following function is called ro get the debit code and debit head from
	 * HRMS_ARREAR_debit_2008(year entered) table.
	 * 
	 * @param empId
	 * @param month
	 * @param year
	 * @param arrearsCode
	 * @param bean
	 * @return
	 */
	public Object[][] getDebitHeaderArrear(String empId, String month,
			String year, String arrearsCode, MonthlyArrearsReport bean, String arreaspaytype) {

		String arrDebit = "SELECT DISTINCT ARREARS_DEBIT_CODE,DEBIT_ABBR, ";
		
		if(bean.getCheckFlag().equals("Y"))
		{
			arrDebit +=" 0 ";
		}
		else
		{
			arrDebit +=" ARREARS_AMT ";
		}
		arrDebit +="	ARREARS_AMT FROM HRMS_ARREARS_DEBIT_"
			+ bean.getYear()
			+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"
			+ bean.getYear()
			+ ".ARREARS_DEBIT_CODE)"
			+ " WHERE ARREARS_CODE="
			+ arrearsCode
			+ " AND ARREARS_EMP_ID="
			+ empId
			+ " AND HRMS_ARREARS_DEBIT_" 
			+ bean.getYear()
			+ ".ARREARS_PAY_TYPE = '" + arreaspaytype + "'"			
			;
	
	if(!bean.getCheckFlag().equals("Y"))
	{
		arrDebit +=" AND ARREARS_MONTH="+ month
			+ " AND ARREARS_YEAR="+ year ;
	}
	arrDebit +=" ORDER BY HRMS_ARREARS_DEBIT_"+ bean.getYear()
			+ ".ARREARS_DEBIT_CODE";

		Object[][] arrDebitData = getSqlModel().getSingleResult(arrDebit);
		return arrDebitData;

	}

	/**
	 * following function is used to get the credit code and credit head
	 * 
	 * @param bean
	 * @return
	 */
	public Object[][] getCreditHeader(MonthlyArrearsReport bean) {

		String arrCred = "SELECT CREDIT_CODE, CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY IN ('M','A') AND CREDIT_PAYFLAG='Y' AND CREDIT_APPLICABLE_ARREARS = 'Y' order BY CREDIT_CODE";

		Object[][] arrCredHead = getSqlModel().getSingleResult(arrCred);
		return arrCredHead;

	}

	/**
	 * following function is used to get the debit code and debit head
	 * 
	 * @param bean
	 * @return
	 */
	public Object[][] getDebitHeader(MonthlyArrearsReport bean) {

		String arrDebit = "SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' AND DEBIT_APPLICABLE_ARREARS = 'Y' "
				+ " ORDER BY DEBIT_CODE";
		Object[][] arrDebitData = getSqlModel().getSingleResult(arrDebit);
		return arrDebitData;

	}

	/**
	 * following function is called when no branch or department is selected to
	 * set the credit and debit header names and their respective values.
	 * 
	 * @param query
	 * @param bean
	 * @param brnCode
	 * @param deptCode
	 * @param arrEmpLength
	 * @param arreaspaytype 
	 * @return
	 */
	public Object[][] getReportDataNoBranchDept(String query,
			MonthlyArrearsReport bean, String brnCode, String deptCode,
			int arrEmpLength, String chekFlag, String arreaspaytype) {
		int dataIndex = 0;
		String year = bean.getYear();
		String month = bean.getMonth();
		Object[][] dataWithHeader = null;
		Object[][] arrearCreditAmt = null;
		Object[][] arrearDebitAmt = null;
		try {
			Object emp_id[][] = getEmpIdNew(query, bean);
			if (emp_id != null && emp_id.length != 0) {
				Object credit_header[][] = getCreditHeader(bean);
				Object debit_header[][] = getDebitHeader(bean);
				double totArrearAmt = 0;
				// Object debit_recovery[][]= getDebitHeader_recovery();
				
				
				int checkCount=0;
				
				if(!bean.getReportdivision().equals("")) {
					checkCount++;
				}
				if (!bean.getReportbranch().equals("")) {
					checkCount++;
				}
				if (!bean.getReportdept().equals("")) {
					checkCount++;
				}
				if (!bean.getReportmicrcode().equals("")) {
					checkCount++;
				}
				if (!bean.getReportgrade().equals("")) {
					checkCount++;
				}
				if (!bean.getReportaccno().equals("")) {
					checkCount++;
				}
				if (!bean.getReportbank().equals("")) {
					checkCount++;
				}
				if (!bean.getReportempType().equals("")) {
					checkCount++;
				}
				int totalCol = credit_header.length + debit_header.length+checkCount + 8;
				String[] colNames = new String[totalCol];

				colNames[0] = "EMP ID";
				colNames[1] = "EMP NAME";
				int chkcnt = 2;
				if (!bean.getReportempType().equals("")) {
					colNames[2] = "EMP TYPE";
					chkcnt += 1;
				}

				if (!bean.getReportdivision().equals("")) {
					colNames[chkcnt] = "Division";
					chkcnt += 1;
				}
				if (!bean.getReportbranch().equals("")) {
					colNames[chkcnt] = "Branch";
					chkcnt += 1;
				}
				if (!bean.getReportdept().equals("")) {
					colNames[chkcnt] = "Department";
					chkcnt += 1;
				}
				if (!bean.getReportmicrcode().equals("")) {
					colNames[chkcnt] = "MICR Code";
					chkcnt += 1;
				}
				if (!bean.getReportgrade().equals("")) {
					colNames[chkcnt] = "Grade";
					chkcnt += 1;
				}
				if (!bean.getReportaccno().equals("")) {
					colNames[chkcnt] = "Account No";
					chkcnt += 1;
				}
				if (!bean.getReportbank().equals("")) {
					colNames[chkcnt] = "Bank";
					chkcnt += 1;
				}
				
				colNames[chkcnt++] = "Month";
				colNames[chkcnt++] = "Arrear Days";
				colNames[chkcnt++] = "Year";

//				colNames[6] = "Account Number";
//
//				colNames[7] = "MIRC Code";

				int count = chkcnt;
				/**
				 * following loop is used to set the credit head names
				 */
				for (int i = 0; i < credit_header.length; i++) {
					colNames[count] = String.valueOf(credit_header[i][1]);
					count++;
				}
				colNames[count] = "Total Credit";
				count = count + 1;
				/**
				 * following loop is used to set the debit header names
				 */
				for (int i = 0; i < debit_header.length; i++) {
					colNames[count] = String.valueOf(debit_header[i][1]);
					count++;
				}
				colNames[count] = "Total Debit";
				count = count + 1;
				colNames[count] = "Net-Pay";
				System.out.println("emp_id.length :::: +++++ ::: "+emp_id.length);
				System.out.println("totalCol :::: +++++ ::: "+totalCol);				
				Object[][] data = new Object[emp_id.length][totalCol];

				/**
				 * following loop is used to set the header values
				 */
				for (int i = 0; i < emp_id.length; i++) {

					data[dataIndex][0] = checkNull(String.valueOf(emp_id[i][1]));// Id
					data[dataIndex][1] = checkNull(String.valueOf(emp_id[i][2]));// Name
					chkcnt = 2;
					if (!bean.getReportempType().equals("")) {
						data[dataIndex][2] = checkNull(String.valueOf(emp_id[i][3]));// emp type
						chkcnt += 1;
					}

					if (!bean.getReportdivision().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportbranch().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportdept().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportmicrcode().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportgrade().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportaccno().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					if (!bean.getReportbank().equals("")) {
						data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt + 1]));
						chkcnt += 1;
					}
					int temp=chkcnt;
					if (chekFlag.equals("Y")) {
						data[dataIndex][chkcnt] = "";
						chkcnt += 1;
					} else {
						data[dataIndex][chkcnt] = Utility
								.month(Integer.parseInt(String.valueOf(emp_id[i][chkcnt + 2])));// Arrear month
						chkcnt += 1;
					}
					
					/*
					 * data[dataIndex][5] =emp_id[i][7];//Arrears Day
					 */

					if (chekFlag.equals("Y")) {
						String arrearDaysQuery = "SELECT NVL(SUM(HRMS_ARREARS_"
								+ year
								+ ".ARREARS_DAYS),0)FROM HRMS_ARREARS_"
								+ year
								+ " INNER JOIN HRMS_ARREARS_LEDGER "
								+ " ON(HRMS_ARREARS_"
								+ year
								+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
								+ " WHERE HRMS_ARREARS_"
								+ year
								+ ".EMP_ID="
								+ String.valueOf(emp_id[i][0])
								+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE=?" //+ bean.getArrearType()	+ "' " 
								+ "AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR=?"	//+ year
								+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH=?";//+ month ;
						
						Object[] parameterObj = new Object[3];
						parameterObj[0]=bean.getArrearType();
						parameterObj[1]=year;
						parameterObj[2]=month;
							
						Object[][] arrears_days = getSqlModel()
								.getSingleResult(arrearDaysQuery,parameterObj);
						if (arrears_days != null || arrears_days.length != 0) {
							data[dataIndex][chkcnt] = Double.parseDouble(String
									.valueOf(arrears_days[0][0]));
						}

					} else {
						data[dataIndex][chkcnt] = emp_id[i][chkcnt+3]; // arears days
					}
					//data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt+1]));
					chkcnt+=1;
					
					data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt+1]));// Year
					chkcnt+=1;
					data[dataIndex][chkcnt] = checkNull(String.valueOf(emp_id[i][chkcnt+1]));

					int column = chkcnt;
					double totArrACredit = 0;
					double totArrDebit = 0;
					int position_totalPay = 0;
					/** set credit values */

					double total_credit = 0.00;
					// String empId,String month,String year,String
					// arrearsCode,MonthlyArrearsReport bean
					Object arrearCredit[][] = getCreditHeaderArrear(String
							.valueOf(emp_id[i][0]), String
							.valueOf(emp_id[i][temp+2]), String
							.valueOf(emp_id[i][temp+3]), String
							.valueOf(emp_id[i][temp+1]), bean, arreaspaytype);// getSalaryCreditData(String.valueOf(emp_id[i][0]),
					// year,
					// month,salReg,Id)
					// ;

					/**
					 * following loop is used to set the credit header values
					 */
					for (int j = 0; j < credit_header.length; j++) {

						data[dataIndex][column] = 0.00;
						if (!(arrearCredit == null || arrearCredit.length == 0))
							for (int k = 0; k < arrearCredit.length; k++) {

								if (String.valueOf(credit_header[j][0]).equals(
										String.valueOf(arrearCredit[k][0]))) {

									if (arrearCredit[k][1] != null) {
										//System.out.println("ABBR================= " + arrearCredit[k][1]);
										//System.out.println("Amount================= " + arrearCredit[k][2]);

										if (chekFlag.equals("Y")) {
											String sql = " SELECT NVL(SUM(HRMS_ARREARS_CREDIT_"	+ year
													+ ".ARREARS_AMT),0.00) FROM HRMS_ARREARS_CREDIT_" + year
													+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
													+ " (HRMS_ARREARS_CREDIT_" + year
													+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID=?"
													//+ emp_id[i][0]
													+ " AND HRMS_ARREARS_CREDIT_" + year
													+ ".ARREARS_PAY_TYPE =? " //'"+arreaspaytype	+ "' " 
													+ "AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH=?" 
													//+ month
													+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR=?" 
													//+ year
													+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE=?" //'" + bean.getArrearType() + "' " 
													+ " AND HRMS_ARREARS_CREDIT_" + year
													+ ".ARREARS_CREDIT_CODE=?"; //+ arrearCredit[k][0];
											
											Object[] parameterObj = new Object[6];
											parameterObj[0] = emp_id[i][0];  
											parameterObj[1] = arreaspaytype;	
											parameterObj[2] = month;
											parameterObj[3] = year;
											parameterObj[4] = bean.getArrearType();
											parameterObj[5] = arrearCredit[k][0];

											Object[][] amt = getSqlModel()
													.getSingleResult(sql,parameterObj);
											if (amt != null || amt.length != 0) {

												 
												data[dataIndex][column] =Double
												.parseDouble(String
														.valueOf(amt[0][0]));
												total_credit += Double
														.parseDouble(String
																.valueOf(data[dataIndex][column]));
											}
										}// End of checkFlag if condition
										else {
											data[dataIndex][column] = Utility
													.twoDecimals(String
															.valueOf(arrearCredit[k][2]));// +Double.parseDouble("0.00");
											total_credit += Double
													.parseDouble(String
															.valueOf(arrearCredit[k][2]));
										}
										/*
										 * data[dataIndex][column] =
										 * Utility.twoDecimals(String.valueOf(arrearCredit[k][2]));
										 * total_credit
										 * +=Double.parseDouble(String.valueOf(arrearCredit[k][2]));
										 */

									}

								}
							}

						column++;
					}

					data[dataIndex][column] = Utility.twoDecimals(total_credit);

					column = column + 1;
					/** set non-recovery debits */
					Object salDebit[][] = getDebitHeaderArrear(String
							.valueOf(emp_id[i][0]), String
							.valueOf(emp_id[i][temp+2]), String
							.valueOf(emp_id[i][temp+3]), String
							.valueOf(emp_id[i][temp+1]), bean, arreaspaytype);
					double total_nonRecovery = 0;
					/**
					 * following loop is used to set the debit header values
					 */
					for (int k = 0; k < debit_header.length; k++) {
						data[dataIndex][column] = 0;
						if (!(salDebit == null || salDebit.length == 0))
							for (int index = 0; index < salDebit.length; index++) {
								if (String.valueOf(debit_header[k][0]).equals(
										String.valueOf(salDebit[index][0]))) {
									if (salDebit[index][1] != null) {

										if (chekFlag.equals("Y")) {
											String sql = " SELECT NVL(SUM(HRMS_ARREARS_DEBIT_" + year
													+ ".ARREARS_AMT),0) from HRMS_ARREARS_DEBIT_" + year
													+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
													+ " (HRMS_ARREARS_DEBIT_" + year
													+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) " 
													+ " WHERE HRMS_ARREARS_DEBIT_" + year
													+ ".ARREARS_EMP_ID=?"
													//+ emp_id[i][0]
													+ "AND HRMS_ARREARS_DEBIT_" + year
													+ ".ARREARS_PAY_TYPE =?" //'" + arreaspaytype + "' " 
													+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH=?"
													//+ month
													+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR=?"
													//+ year
													+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE=?" //'" + bean.getArrearType() + "' " 
													+ "AND HRMS_ARREARS_DEBIT_" + year
													+ ".ARREARS_DEBIT_CODE=?";
													//+ salDebit[index][0];
											
											Object[] parameterObj = new Object[6];
											parameterObj[0] = emp_id[i][0];  
											parameterObj[1] = arreaspaytype;	
											parameterObj[2] = month;
											parameterObj[3] = year;
											parameterObj[4] = bean.getArrearType();
											parameterObj[5] = salDebit[index][0];
											
											Object[][] amt = getSqlModel()
													.getSingleResult(sql,parameterObj);
											if (amt != null || amt.length != 0) {
												/*data[dataIndex][column] = Utility
														.twoDecimals(formatter
																.format(Double
																		.parseDouble(String
																				.valueOf(salDebit[index][2]))
																		+ Double
																				.parseDouble(String
																						.valueOf(amt[0][0]))));
												total_nonRecovery += Double
														.parseDouble(String
																.valueOf(data[dataIndex][column]));*/
												
												 
												data[dataIndex][column] =Double
												.parseDouble(String
														.valueOf(amt[0][0]));
											 
												total_nonRecovery += Double
												.parseDouble(String
														.valueOf(data[dataIndex][column]));
																						
												
											}

										}// End of check flag condition

										else {

											data[dataIndex][column] = Utility
													.twoDecimals(String
															.valueOf(salDebit[index][2]));
											total_nonRecovery += Double
													.parseDouble(String
															.valueOf(salDebit[index][2]));
										}

										/*
										 * data[dataIndex][column] =
										 * Utility.twoDecimals(String.valueOf(salDebit[index][2]));
										 * total_nonRecovery
										 * +=Double.parseDouble(String.valueOf(salDebit[index][2]));
										 */
									}
								}
							}

						column++;
					}
					// logger.info("Length of arrear Debit code
					// pkppp"+arrearDebitCode.length);
					data[dataIndex][column] = Utility
							.twoDecimals(total_nonRecovery);

					double total_pay = 0;
					/*
					total_pay = Double
							.parseDouble(String
									.valueOf(data[dataIndex][credit_header.length + 8]))
							- Double.parseDouble(String
									.valueOf(data[dataIndex][column]));
					*/
					total_pay = total_credit - total_nonRecovery ;
					column = column + 1;
					/*if (Double.parseDouble(Utility.twoDecimals(total_pay)) < 0) { 
						data[dataIndex][column] = Utility.twoDecimals("0");
					} else {
						data[dataIndex][column] = Utility
								.twoDecimals(total_pay);
					}*/
					
					System.out.println("column :: ==== "+column);
						data[dataIndex][column] = Utility
								.twoDecimals(total_pay);
					

					position_totalPay = column;

					dataIndex++;
				}

				dataWithHeader = new Object[data.length + 1][totalCol];

				for (int i = 0; i < colNames.length; i++) {
					dataWithHeader[0][i] = colNames[i];
				}
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[0].length; j++) {
						dataWithHeader[i + 1][j] = data[i][j];
					}
				}

			}

			/*
			 * String finalHeader [] = new String[dataWithHeader[0].length]; int []
			 * cellWidth=new int[dataWithHeader[0].length]; int [] alignment=new
			 * int[dataWithHeader[0].length]; for (int i = 0; i <
			 * dataWithHeader[0].length; i++) { finalHeader[i]
			 * =(String)dataWithHeader[0][i] ; alignment[i]=0; if(i>1){
			 * cellWidth[i]=7; alignment[i]=0; }else{ cellWidth[0]=8;
			 * cellWidth[1]=15; cellWidth[2]=10; cellWidth[3]=7; } } Object
			 * finalData[][] = new
			 * Object[dataWithHeader.length-1][dataWithHeader[0].length]; for
			 * (int i = 0; i < finalData.length; i++) { for (int j = 0; j <
			 * finalData[0].length; j++) { finalData[i][j] =
			 * dataWithHeader[i+1][j];
			 *  } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataWithHeader;
	}

	// for cell

	public int[] getCellWidth(int dataLength) {
		int[] cellWidth = new int[dataLength];
		for (int i = 0; i < dataLength; i++) {
			if (i > 1) {
				cellWidth[i] = 7;
			} else {
				cellWidth[0] = 8;
				cellWidth[1] = 15;
				cellWidth[2] = 15;
				cellWidth[3] = 7;
				cellWidth[4] = 7;
				cellWidth[5] = 7;
			}
		}
		return cellWidth;
	}

	public int[] getAlignment(int dataLength) {
		int[] alignment = new int[dataLength];
		for (int i = 0; i < dataLength; i++) {
			alignment[i] = 0;
		}
		return alignment;
	}
	public int[] getAlignmentBrnDept(int dataLength) {
		int[] alignment = new int[dataLength];
		for (int i = 0; i < dataLength; i++) {
			alignment[i] = 2;
		}
		return alignment;
	}
	public int[] getCellWidthBrnDept(int dataLength) {
		int[] cellWidth = new int[dataLength];
		for (int i = 0; i < dataLength; i++) {
			if (i ==0) {
				cellWidth[i] = 10;
			} else {
				cellWidth[i] = 7;
				
			}
		}
		return cellWidth;
	}
	public Object[][] arrearCreditData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			MonthlyArrearsReport salReg) {
		Object[][] amt = new Object[arrear.length][1];
		try {

			// String query=" SELECT NVL(ARREARS_AMT,0) FROM
			// HRMS_ARREARS_credit_"+salReg.getYear()+" INNER JOIN
			// HRMS_ARREARS_"+salReg.getYear()+"
			// on(hrms_arrears_"+salReg.getYear()+".arrears_code=hrms_arrears_credit_"+salReg.getYear()+".arrears_code)
			// WHERE
			// hrms_arrears_credit_"+salReg.getYear()+".ARREARS_EMP_ID="+empId+"
			// AND
			// HRMS_ARREARS_CREDIT_"+salReg.getYear()+".ARREARS_CODE="+arrearCode+"
			// ORDER BY ARREARS_CREDIT_CODE";
			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					String query = " SELECT DISTINCT NVL(ARREARS_AMT,0) FROM HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ ".ARREARS_CODE)"
							+ " WHERE HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ ".ARREARS_EMP_ID="
							+ empId
							+ " AND HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ ".ARREARS_CODE="
							+ arrearCode
							+ " AND "
							+ " HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ ".ARREARS_MONTH="
							+ month
							+ " AND HRMS_ARREARS_CREDIT_"
							+ salReg.getYear()
							+ ".ARREARS_YEAR="
							+ year
							+ " AND ARREARS_CREDIT_CODE="
							+ arrear[i][0]
							+ " AND ARREARS_TYPE="
							+ "'" + type + "'";

					Object[][] amount = getSqlModel().getSingleResult(query,
							new Object[0][0]);
					if (amount.length == 0 || amount == null) {
						amt[i][0] = "0.00";
					} else {
						if (String.valueOf(amount[0][0]).equals("")
								|| String.valueOf(amount[0][0]).equals("null")) {
							amt[i][0] = "0.00";
						} else {
							amt[i][0] = amount[0][0];
						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return amt;
	}

	public Object[][] arrearDebitData(String arrearCode, String empId,
			String month, String year, Object[][] arrear, String type,
			MonthlyArrearsReport salReg) {
		Object[][] amt = new Object[arrear.length][1];
		try {
			for (int i = 0; i < arrear.length; i++) {
				if (arrear[i][0] != null) {
					// String query=" SELECT NVL(ARREARS_AMT,0) FROM
					// HRMS_ARREARS_DEBIT_"+salReg.getYear()+" INNER JOIN
					// HRMS_ARREARS_"+salReg.getYear()+"
					// on(hrms_arrears_"+salReg.getYear()+".arrears_code=hrms_arrears_debit_"+salReg.getYear()+".arrears_code)
					// WHERE
					// hrms_arrears_debit_"+salReg.getYear()+".ARREARS_EMP_ID="+empId+"
					// AND
					// HRMS_ARREARS_DEBIT_"+salReg.getYear()+".ARREARS_CODE="+arrearCode+"
					// ORDER BY ARREARS_debit_CODE";
					String query = " SELECT DISTINCT NVL(ARREARS_AMT,0) FROM HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ ".ARREARS_CODE)"
							+ " WHERE HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ ".ARREARS_EMP_ID="
							+ empId
							+ " AND HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ ".ARREARS_CODE="
							+ arrearCode
							+ " AND "
							+ " HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ ".ARREARS_MONTH="
							+ month
							+ " AND HRMS_ARREARS_DEBIT_"
							+ salReg.getYear()
							+ ".ARREARS_YEAR="
							+ year
							+ " AND ARREARS_DEBIT_CODE="
							+ arrear[i][0]
							+ " AND ARREARS_TYPE="
							+ "'" + type + "'";
					Object[][] amount = getSqlModel().getSingleResult(query,
							new Object[0][0]);
					if (amount == null || amount.length == 0) {
						amt[i][0] = "0.00";
					} else {
						if (String.valueOf(amount[0][0]).equals("")
								|| String.valueOf(amount[0][0]).equals("null")) {
							amt[i][0] = "0.00";
						} else {
							amt[i][0] = amount[0][0];
						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return amt;
	}

	/**
	 * following function is called when branch and department are selected to
	 * generate the report
	 * 
	 * @param loop_data
	 * @param rg
	 * @param bean
	 */
	public void reportDataBraDept(Object[][] loop_data, ReportGenerator rg,
			MonthlyArrearsReport bean, String chekFlag) {
		try {
			ArrayList totList = new ArrayList();
			int recCount = 0, arrEmpLength = 0;
			if (loop_data.length > 0) {
				for (int a = 0; a < loop_data.length; a++) {
			/*		String arrearEmp = " SELECT HRMS_ARREARS_"
							+ bean.getYear()
							+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH  ,ARREARS_YEAR ,ARREARS_DAYS ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
							+ "	INNER JOIN HRMS_ARREARS_"
							+ bean.getYear()
							+ " ON(HRMS_ARREARS_"
							+ bean.getYear()
							+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
							+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ bean.getYear()
							+ ".ARREARS_CODE)  "
							+ "  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
							+ bean.getYear() + ".EMP_ID)  "
							+ "	WHERE ARREARS_PAID_MONTH=" + bean.getMonth()
							+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
							+ " and arrears_type ='" + bean.getArrearType()
							+ "'";*/

					/*
					 * " SELECT HRMS_SALARY_"+salReg.getYear()+".EMP_ID
					 * ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME " + "
					 * FROM HRMS_SALARY_"+salReg.getYear()+" inner join
					 * HRMS_EMP_OFFC on HRMS_SALARY_"+salReg.getYear()+".EMP_ID =
					 * HRMS_EMP_OFFC.EMP_ID " + " inner join hrms_center on
					 * (hrms_center.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "+ "
					 * inner join hrms_dept on (hrms_dept.DEPT_ID=
					 * HRMS_EMP_OFFC.EMP_DEPT) "+ " WHERE SAL_MONTH="+
					 * salReg.getMonth();
					 */
					// String where = " ";
					
					String  arrearEmp = " SELECT DISTINCT HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE";
				if(chekFlag.equals("Y")){
					arrearEmp += " ,''  ,'',''" ;
				}else{
					arrearEmp += " ,ARREARS_MONTH  ,ARREARS_YEAR,ARREARS_DAYS" ;
				}
				
				
				arrearEmp += " ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
						+"	INNER JOIN HRMS_ARREARS_"
						+ bean.getYear()
						+ " ON(HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
						+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
						+ bean.getYear()
						+ ".ARREARS_CODE)  "
						+ " 	 LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
						+ bean.getYear() + ".EMP_ID) "
					 	+ "	WHERE ARREARS_PAID_MONTH="
						+ bean.getMonth()
						+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
						+ " and arrears_type ='"
						+ bean.getArrearType() + "'";
					
					
					
					
					String where = " AND EMP_CENTER=" + loop_data[a][0] + " ";
					if (!(bean.getOnHold().equals("A"))) {
						where += " and ARREARS_ONHOLD='" + bean.getOnHold()
								+ "' ";
					}
					where += "AND  EMP_DEPT =" + loop_data[a][1];

					arrearEmp = arrearEmp + where;

					if (!(bean.getDivName().equals("") || bean.getDivName()
							.equals("null"))) {
						arrearEmp += " AND EMP_DIV=" + bean.getDivCode();
					}

					arrearEmp += " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME  ";
					
					if(!chekFlag.equals("Y"))
					{
						arrearEmp +=" ,HRMS_ARREARS_"+ bean.getYear() + ".ARREARS_MONTH ";
					}

					Object[][] reportDataPay = getReportData(arrearEmp, bean,
							String.valueOf(loop_data[a][0]), arrEmpLength,
							chekFlag);
					/**
					 * following condition is used to calculate the total amount
					 * row wise and column wise
					 */
					if (reportDataPay != null) {
						String finalHeader[] = new String[reportDataPay[0].length];
						int[] cellWidth = new int[reportDataPay[0].length];
						int[] alignment = new int[reportDataPay[0].length];
						for (int i = 0; i < reportDataPay[0].length; i++) {
							finalHeader[i] = String
									.valueOf(reportDataPay[0][i]);
							alignment[i] = 0;
							if (i > 1) {
								cellWidth[i] = 7;
								alignment[i] = 0;
							} else {
								cellWidth[0] = 8;
								cellWidth[1] = 15;
							}
						}

						Object finalData[][] = new Object[reportDataPay.length - 1][reportDataPay[0].length];
						for (int i = 0; i < finalData.length; i++) {
							for (int j = 0; j < finalData[0].length; j++) {
								finalData[i][j] = reportDataPay[i + 1][j];
								// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
							}
						}

						Object totalByColumn[][] = new Object[1][finalData[0].length];

						String totalHeader[] = new String[finalData.length];
						totalHeader[0] = "";
						totalByColumn[0][0] = "TOTAL :-";

						// totalHeader[1] = "";
						totalByColumn[0][1] = " ";
						/**
						 * following loop is used to calculate the total amount
						 * column wise
						 */
						for (int i = 8; i < finalData[0].length; i++) {
							double total = 0.00;
							for (int j = 0; j < finalData.length; j++) {
								if (String.valueOf(finalData[j][i]).equals(
										"null")) {
									finalData[j][i] = "0.00";
								}
								total += Double.parseDouble(String
										.valueOf(finalData[j][i]));
							}
							// totalHeader[i] = "";
							totList.add(Utility.twoDecimals(total));
							totalByColumn[0][i] = Utility.twoDecimals(total);
						}
						//rg.addText("\n", 0, 0, 0);
						//rg.addText("Branch : " + bean.getBranchName()
								//+ "   Department : " + bean.getDeptName(), 0,
								//0, 0);
						//rg.tableBody(finalHeader, finalData, cellWidth,
								//alignment);
						//rg.tableBody(totalByColumn, cellWidth, alignment);
						recCount++;
						// colCount=finalData[0].length;
					}// End of if condition
				}
				if (recCount != 0) {
					Object[][] listValues = new Object[recCount][(totList
							.size() / recCount)];

					int arrCount = 0;
					for (int i = 0; i < recCount; i++) {
						for (int j = 0; j < (totList.size() / recCount); j++) {
							listValues[i][j] = totList.get(arrCount);
							arrCount++;
							/*logger
									.info("-----------------values are listValues["
											+ i
											+ "]["
											+ j
											+ "]="
											+ listValues[i][j]);*/
						}
					}

					Object grand_total[][] = new Object[1][listValues[0].length + 8];

					grand_total[0][0] = "TOTAL :-";
					grand_total[0][1] = " ";
					grand_total[0][2] = " ";
					grand_total[0][3] = " ";
					grand_total[0][4] = " ";
					// grand_total[0][5] =" ";

					for (int i = 0; i < listValues[0].length; i++) {
						double total = 0.00;
						for (int j = 0; j < listValues.length; j++) {
							if (String.valueOf(listValues[j][i]).equals("null")) {
								listValues[j][i] = "0.00";
							}
							total += Double.parseDouble(String
									.valueOf(listValues[j][i]));
						}
						grand_total[0][i + 8] = Utility.twoDecimals(total);
					}
					int[] cellWidth = getCellWidth(grand_total[0].length);
					int[] alignment = getAlignment(grand_total[0].length);

					//rg.addText("\n", 0, 0, 0);
					//rg.addText("GRAND TOTAL", 0, 0, 0);
					//rg.tableBody(grand_total, cellWidth, alignment);
				} else {
					//rg.addText("Records are not available.", 0, 1, 0);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * following function is called to set the header names,their respective
	 * values and the total credit amount,total debit amount and the net amount
	 * 
	 * @param query
	 * @param bean
	 * @param Id
	 * @param arrEmpLength
	 * @return
	 */
	public Object[][] getReportData(String query, MonthlyArrearsReport bean,
			String Id, int arrEmpLength, String chekFlag) {
		Object[][] dataWithHeader = null;
		logger.info("getReportData==");
		try {
			int dataIndex = 0;
			String year = bean.getYear();
			String month = bean.getMonth();
			dataWithHeader = null;
			Object[][] arrearCreditAmt = null;
			Object[][] arrearDebitAmt = null;
			try {
				Object emp_id[][] = getEmpIdNew(query, bean);
				Object credit_header[][] = getCreditHeader(bean);
				Object debit_header[][] = getDebitHeader(bean);
				logger.info("debit_header length=="+debit_header.length);
				if (emp_id != null && emp_id.length != 0) {
					double totArrearAmt = 0;
					// Object debit_recovery[][]= getDebitHeader_recovery();
					int totalCol = credit_header.length + debit_header.length
							+ 11;
					String[] colNames = new String[totalCol];
					logger.info("totalCol=="+totalCol);
					colNames[0] = "EMP ID";
					colNames[1] = "EMP NAME";
					colNames[2] = "EMP TYPE";
					colNames[3] = "Month";
					colNames[4] = "Arrear Year";
					colNames[5] = "Days";

					colNames[6] = "Account Number";

					colNames[7] = "MIRC Code";

					int count = 8;
					/**
					 * following loop is used to set the credit header names
					 */
					for (int i = 0; i < credit_header.length; i++) {
						colNames[count] = String.valueOf(credit_header[i][1]);
						count++;
					}
					colNames[count] = "TOT CREDIT";
					count = count + 1;
					/**
					 * following loop is used to set the debit header names
					 */
					for (int i = 0; i < debit_header.length; i++) {
						colNames[count] = String.valueOf(debit_header[i][1]);
						count++;
					}
					colNames[count] = "TOT DEBIT";
					count = count + 1;
					colNames[count] = "NET-PAY";
					Object[][] data = new Object[emp_id.length + arrEmpLength][totalCol];

					/**
					 * following loop is used to set the header values
					 */
					for (int i = 0; i < emp_id.length; i++) {

						if (chekFlag.equals("Y")) {
							String arrearDaysQuery = "SELECT NVL(SUM(ARREARS_DAYS),0)FROM HRMS_ARREARS_"
									+ year
									+ " INNER JOIN HRMS_ARREARS_LEDGER "
									+ " ON(HRMS_ARREARS_"
									+ year
									+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE)"
									+ " WHERE HRMS_ARREARS_"
									+ year
									+ ".EMP_ID="
									+ String.valueOf(emp_id[i][0])
									+ " AND ARREARS_TYPE='" + bean.getArrearType()+"' AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="
									+ year
									+ " AND "
									+ " ARREARS_PAID_MONTH="+ month ;
							
							Object[][] arrears_days = getSqlModel()
									.getSingleResult(arrearDaysQuery);
							if (arrears_days != null
									|| arrears_days.length != 0) {
								data[dataIndex][5] = Double.parseDouble(String
										.valueOf(arrears_days[0][0]));
								}

						} else {
							data[dataIndex][5] =checkNull(String.valueOf(emp_id[i][7]));
						}

						data[dataIndex][0] = checkNull(String.valueOf(emp_id[i][1]));// Employee Id
						data[dataIndex][1] = checkNull(String.valueOf(emp_id[i][2]));// Employee Name
						data[dataIndex][2] = checkNull(String.valueOf(emp_id[i][3])); // Employee Type
						if(chekFlag.equals("Y"))
						{
							data[dataIndex][3] = "";
						}
						else
						{
							data[dataIndex][3] = Utility.month(Integer.parseInt(String.valueOf(emp_id[i][5])));// Arrear
						}
						
						// month
						data[dataIndex][4] = checkNull(String.valueOf(emp_id[i][6]));// Year
						// data[dataIndex][5] =emp_id[i][7];//Arrears Day

						data[dataIndex][6] =checkNull(String.valueOf(emp_id[i][8]));// Arrears Day
						data[dataIndex][7] =checkNull(String.valueOf(emp_id[i][9]));// Arrears Day

						int column = 8;
						double totArrACredit = 0;
						double totArrDebit = 0;
						int position_totalPay = 0;
						/** set credit values */

						double total_credit = 0.00;
						Object arrearCredit[][] = getCreditHeaderArrear(String
								.valueOf(emp_id[i][0]), String
								.valueOf(emp_id[i][5]), String
								.valueOf(emp_id[i][6]), String
								.valueOf(emp_id[i][4]), bean, "A");// getSalaryCreditData(String.valueOf(emp_id[i][0]),
						// year,
						// month,salReg,Id)
						// ;
						/**
						 * following loop is used to set the credit header
						 * values
						 */
						logger.info("credit_header length=="+credit_header.length);
						for (int j = 0; j < credit_header.length; j++) {

							data[dataIndex][column] = 0.00;
							if (!(arrearCredit == null || arrearCredit.length == 0))
								for (int k = 0; k < arrearCredit.length; k++) {

									if (String
											.valueOf(credit_header[j][0])
											.equals(
													String
															.valueOf(arrearCredit[k][0]))) {

										if (arrearCredit[k][1] != null) {

											if (chekFlag.equals("Y")) {
												String sql = " SELECT NVL(SUM(ARREARS_AMT),0.00) from HRMS_ARREARS_CREDIT_"
														+ year
														+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
														+ " (HRMS_ARREARS_CREDIT_"
														+ year
														+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
														+ emp_id[i][0]
														+ ""
														+ " AND ARREARS_PAID_MONTH="
														+ month
														+ " AND ARREARS_PAID_YEAR="
														+ year
														+ " AND ARREARS_TYPE='" + bean.getArrearType()+"'   AND ARREARS_CREDIT_CODE="
														+ arrearCredit[k][0];

												Object[][] amt = getSqlModel()
														.getSingleResult(sql);
												if (amt != null
														|| amt.length != 0) {

													double firstValue = Double
															.parseDouble(String
																	.valueOf(arrearCredit[k][2]));

													double secondValue = Double
															.parseDouble(String
																	.valueOf(amt[0][0]));

													data[dataIndex][column] = Utility
															.twoDecimals(firstValue
																	+ secondValue);
													total_credit += Double
															.parseDouble(String
																	.valueOf(data[dataIndex][column]));
												}
											}// End of checkFlag if condition
											else {
												data[dataIndex][column] = Utility
														.twoDecimals(String
																.valueOf(arrearCredit[k][2]));// +Double.parseDouble("0.00");
												total_credit += Double
														.parseDouble(String
																.valueOf(arrearCredit[k][2]));
											}

											/*
											 * data[dataIndex][column] =
											 * Utility.twoDecimals(String.valueOf(arrearCredit[k][2]));
											 * total_credit
											 * +=Double.parseDouble(String.valueOf(arrearCredit[k][2]));
											 */

										}

									}
								}

							column++;
						}
						/*
						 * for (int j = 0; j < credit_header.length; j++) {
						 * data[dataIndex][column] = 0.00; if(!(salCredit==null ||
						 * salCredit.length ==0)) for (int k = 0; k <
						 * salCredit.length; k++) {
						 * if(String.valueOf(credit_header[j][0]).equals(String.valueOf(salCredit[k][0]))){
						 * if(salCredit[k][1]!=null) data[dataIndex][column] =
						 * Utility.twoDecimals(String.valueOf(salCredit[k][1]));
						 * total_credit
						 * +=Double.parseDouble(String.valueOf(salCredit[k][1])); } }
						 * 
						 * 
						 * 
						 * 
						 * column++; }
						 */

						data[dataIndex][column] = Utility
								.twoDecimals(total_credit);
						double total_nonRecovery = 0;
						column = column + 1;
						/** set non-recovery debits */
						Object arrearDebit[][] = getDebitHeaderArrear(String
								.valueOf(emp_id[i][0]), String
								.valueOf(emp_id[i][5]), String
								.valueOf(emp_id[i][6]), String
								.valueOf(emp_id[i][4]), bean, "A");// getSalaryCreditData(String.valueOf(emp_id[i][0]),
						// year,
						// month,salReg,Id)
						// ;
						/**
						 * following loop is used to set the debit header
						 * values.
						 */
						logger.info("debit_header length=="+credit_header.length);
						for (int k = 0; k < debit_header.length; k++) {
							data[dataIndex][column] = Utility.twoDecimals("0");
							if (!(arrearDebit == null || arrearDebit.length == 0))
								for (int index = 0; index < arrearDebit.length; index++) {
									if (String
											.valueOf(debit_header[k][0])
											.equals(
													String
															.valueOf(arrearDebit[index][0]))) {
										if (arrearDebit[index][1] != null) {
											if (chekFlag.equals("Y")) {
												String sql = " SELECT NVL(SUM(ARREARS_AMT),0) from HRMS_ARREARS_DEBIT_"
														+ year
														+ " INNER JOIN HRMS_ARREARS_LEDGER ON "
														+ " (HRMS_ARREARS_DEBIT_"
														+ year
														+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE ARREARS_EMP_ID="
														+ emp_id[i][0]
														+ ""
														+ " AND ARREARS_PAID_MONTH="
														+ month
														+ " AND ARREARS_PAID_YEAR="
														+ year
														+ " AND ARREARS_TYPE IN('"+bean.getArrearType()+"') AND ARREARS_DEBIT_CODE="
														+ arrearDebit[index][0];
												Object[][] amt = getSqlModel()
														.getSingleResult(sql);
												if (amt != null
														|| amt.length != 0) {
													data[dataIndex][column] = Utility
															.twoDecimals(formatter
																	.format(Double
																			.parseDouble(String
																					.valueOf(arrearDebit[index][2]))
																			+ Double
																					.parseDouble(String
																							.valueOf(amt[0][0]))));
													total_nonRecovery += Double
															.parseDouble(String
																	.valueOf(data[dataIndex][column]));
												} else {
													data[dataIndex][column] = Utility
															.twoDecimals(Double
																	.parseDouble(String
																			.valueOf(arrearDebit[index][2])));// ;+Double.parseDouble("0.00");
													total_nonRecovery += Double
															.parseDouble(String
																	.valueOf(data[dataIndex][column]));
												}

											}// End of check flag condition

											else {

												data[dataIndex][column] = Utility
														.twoDecimals(String
																.valueOf(arrearDebit[index][2]));
												total_nonRecovery += Double
														.parseDouble(String
																.valueOf(arrearDebit[index][2]));
											}

										}
										/*
										 * data[dataIndex][column] =
										 * Utility.twoDecimals(String.valueOf(arrearDebit[index][2]));
										 * total_nonRecovery
										 * +=Double.parseDouble(String.valueOf(arrearDebit[index][2]));
										 */
									}
								}

							column++;
						}
						data[dataIndex][column] = Utility
								.twoDecimals(total_nonRecovery);

						double total_pay = 0;
						total_pay = Double
								.parseDouble(String
										.valueOf(data[dataIndex][credit_header.length + 8]))
								- Double.parseDouble(String
										.valueOf(data[dataIndex][column]));
						column = column + 1;
						/*if (Double.parseDouble(Utility.twoDecimals(total_pay)) < 0) {
							data[dataIndex][column] = Utility.twoDecimals("0");
						} else {
							data[dataIndex][column] = Utility
									.twoDecimals(total_pay);
						}*/
						
						
							data[dataIndex][column] = Utility
									.twoDecimals(total_pay);
						

						position_totalPay = column;
						dataIndex++;
					}

					dataWithHeader = new Object[data.length + 1][totalCol];

					for (int i = 0; i < colNames.length; i++) {
						dataWithHeader[0][i] = colNames[i];
					}
					for (int i = 0; i < data.length; i++) {
						for (int j = 0; j < data[0].length; j++) {
							dataWithHeader[i + 1][j] = data[i][j];
						}
					}
				}

				/*
				 * String finalHeader [] = new String[dataWithHeader[0].length];
				 * int [] cellWidth=new int[dataWithHeader[0].length]; int []
				 * alignment=new int[dataWithHeader[0].length]; for (int i = 0;
				 * i < dataWithHeader[0].length; i++) { finalHeader[i]
				 * =(String)dataWithHeader[0][i] ; alignment[i]=0; if(i>1){
				 * cellWidth[i]=7; alignment[i]=0; }else{ cellWidth[0]=8;
				 * cellWidth[1]=15; cellWidth[2]=10; cellWidth[3]=7; } } Object
				 * finalData[][] = new
				 * Object[dataWithHeader.length-1][dataWithHeader[0].length];
				 * for (int i = 0; i < finalData.length; i++) { for (int j = 0;
				 * j < finalData[0].length; j++) { finalData[i][j] =
				 * dataWithHeader[i+1][j];
				 *  } }
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataWithHeader;
	}

	/**
	 * following function is called when branch is selected and department is
	 * not selected to generate the report.
	 * 
	 * @param loop_data
	 * @param rg
	 * @param bean
	 */
	public void reportDataDept(Object[][] loop_data, ReportGenerator rg,
			MonthlyArrearsReport bean, String chekFlag) {
		try {
			ArrayList totList = new ArrayList();
			int recCount = 0, arrEmpLength = 0;
			Object finalData[][]=null;
			String finalHeader[]=null;
			int[] cellWidth = null;
			int[] alignment = null;
			Vector<Object[]> deptData = new Vector<Object[]>();
			if (loop_data.length > 0) {
				for (int a = 0; a < loop_data.length; a++) {
				/*	String arrearEmp = " SELECT HRMS_ARREARS_"
							+ bean.getYear()
							+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH  ,ARREARS_YEAR ,ARREARS_DAYS ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR FROM HRMS_EMP_OFFC "
							+ "	INNER JOIN HRMS_ARREARS_"
							+ bean.getYear()
							+ " ON(HRMS_ARREARS_"
							+ bean.getYear()
							+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
							+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ bean.getYear()
							+ ".ARREARS_CODE)  "
							+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
							+ bean.getYear() + ".EMP_ID)  "
							+ "	WHERE ARREARS_PAID_MONTH=" + bean.getMonth()
							+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
							+ " and arrears_type ='" + bean.getArrearType()
							+ "'";
*/
					
					
					
					String  arrearEmp = " SELECT DISTINCT HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE";
				if(chekFlag.equals("Y")){
					arrearEmp += " ,''  ,'',''" ;
				}else{
					arrearEmp += " ,ARREARS_MONTH  ,ARREARS_YEAR,ARREARS_DAYS" ;
				}
				
				
				arrearEmp += " ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
						+"	INNER JOIN HRMS_ARREARS_"
						+ bean.getYear()
						+ " ON(HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
						+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
						+ bean.getYear()
						+ ".ARREARS_CODE)  "
						+ " 	 LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
						+ bean.getYear() + ".EMP_ID) "
					 	+ "	WHERE ARREARS_PAID_MONTH="
						+ bean.getMonth()
						+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
						+ " and arrears_type ='"
						+ bean.getArrearType() + "'";
					
				 
					
					String where = " AND EMP_DEPT=" + loop_data[a][0]
							+ " ";
					if (!(bean.getOnHold().equals("A"))) {
						where += "AND ARREARS_onhold='" + bean.getOnHold()
								+ "' ";
					}
					if(!bean.getBranchCode().trim().equals(""))
					where += " AND EMP_CENTER=" + bean.getBranchCode();

					arrearEmp = arrearEmp + where;

					if (!(bean.getDivName().equals("") || bean.getDivName()
							.equals("null"))) {
						arrearEmp += " AND EMP_DIV=" + bean.getDivCode();
					}
					arrearEmp += " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
					
					if(!chekFlag.equals("Y"))
					{
						arrearEmp += " ,HRMS_ARREARS_"+ bean.getYear() + ".ARREARS_MONTH ";
					}
					
					/*
					 * String arrearEmp="SELECT
					 * HRMS_ARREARS_"+salReg.getYear()+".EMP_ID,ARREARS_CODE
					 * FROM HRMS_ARREARS_"+salReg.getYear()+" " + " inner join
					 * HRMS_EMP_OFFC on HRMS_EMP_OFFC.emp_id =
					 * HRMS_ARREARS_"+salReg.getYear()+".emp_id " + " WHERE
					 * ARREARS_PAID_MONTH="+salReg.getMonth()+" AND
					 * ARREARS_PAID_YEAR="+salReg.getYear()+"" + " and
					 * arrears_type='M' and EMP_CENTER="+salReg.getBranchCode()+ "
					 * and EMP_DEPT="+String.valueOf(loop_data[a][0]);
					 */

					Object[][] reportDataPay = getReportData(arrearEmp, bean,
							String.valueOf(loop_data[a][0]), arrEmpLength,
							chekFlag);
					if (reportDataPay != null) {
						finalHeader = new String[reportDataPay[0].length];
						 cellWidth = new int[reportDataPay[0].length];
						 alignment = new int[reportDataPay[0].length];
						for (int i = 0; i < reportDataPay[0].length; i++) {
							finalHeader[i] = String.valueOf(reportDataPay[0][i]);
							alignment[i] = 0;
							if (i > 1) {
								cellWidth[i] = 7;
								alignment[i] = 0;
							} else {
								cellWidth[0] = 8;
								cellWidth[1] = 15;
							}
						}

						 finalData = new Object[reportDataPay.length - 1][reportDataPay[0].length];
						for (int i = 0; i < finalData.length; i++) {
							for (int j = 0; j < finalData[0].length; j++) {
								finalData[i][j] = reportDataPay[i + 1][j];
								// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
							}
						}

						Object totalByColumn[][] = new Object[1][finalData[0].length];

						String totalHeader[] = new String[finalData.length];
						totalHeader[0] = "";
						totalByColumn[0][0] = "TOTAL :-";

						// totalHeader[1] = "";
						totalByColumn[0][1] = " ";

						for (int i = 8; i < finalData[0].length; i++) {
							double total = 0.00;
							for (int j = 0; j < finalData.length; j++) {
								if (String.valueOf(finalData[j][i]).equals("null")
										|| String.valueOf(finalData[j][i]).equals("")
										|| String.valueOf(finalData[j][i]).equals(
												"null.00")) {
									finalData[j][i] = "0.00";
								}
								total += Double.parseDouble(String
										.valueOf(finalData[j][i]));
							}
							// totalHeader[i] = "";
							totList.add(Utility.twoDecimals(total));
							totalByColumn[0][i] = Utility.twoDecimals(total);
						}
						//rg.addText("\n", 0, 0, 0);
						//rg.addText("Department Name :"+String.valueOf(loop_data[a][1]), 0, 0, 0);
						//rg.tableBody(finalHeader, finalData, cellWidth, alignment);
						//rg.tableBody(totalByColumn, cellWidth, alignment);
						recCount++;
						// colCount=finalData[0].length;
					} // end of if reportData condition
				}
				if (recCount != 0) {
					Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

					int arrCount = 0;
					for (int i = 0; i < recCount; i++) {
						for (int j = 0; j < (totList.size() / recCount); j++) {
							listValues[i][j] = totList.get(arrCount);
							arrCount++;
							/*logger.info("-----------------values are listValues["
									+ i + "][" + j + "]=" + listValues[i][j]);*/
						}
					}

					Object grand_total[][] = new Object[1][listValues[0].length + 8];

					grand_total[0][0] = "TOTAL :-";
					grand_total[0][1] = " ";
					grand_total[0][2] = " ";
					grand_total[0][3] = " ";
					grand_total[0][4] = " ";
					// grand_total[0][5]=" ";

					for (int i = 0; i < listValues[0].length; i++) {
						double total = 0.00;
						for (int j = 0; j < listValues.length; j++) {
							if (String.valueOf(listValues[j][i]).equals("null")) {
								listValues[j][i] = "0.00";
							}
							total += Double.parseDouble(String
									.valueOf(listValues[j][i]));
						}
						grand_total[0][i + 8] = Utility.twoDecimals(total);
					}
					int[] cellWidthTotal = getCellWidth(grand_total[0].length);
					int[] alignmentTotal = getAlignment(grand_total[0].length);

					//rg.addText("\n", 0, 0, 0);
					//rg.addText("GRAND TOTAL", 0, 0, 0);
					//rg.tableBody(grand_total, cellWidthTotal, alignmentTotal);
					//Object [][]branchFinalData =new Object[branchData.size()][finalData[0].length-7];
					
					/*for (int i = 0; i < branchFinalData.length; i++) {
						branchFinalData[i]= (Object[])branchData.get(i);
					}*/
					/*logger.info("finalHeader length=="+finalHeader.length);
					logger.info("branchFinalData length=="+branchFinalData[0].length);
					logger.info("cellWidth length=="+cellWidth.length);
					logger.info("alignment length=="+alignment.length);*/
					//rg.tableBody(finalHeader, branchFinalData, cellWidth, alignment);
					/*cellWidth = getCellWidthBrnDept(grand_total[0].length);
					alignment = getAlignmentBrnDept(grand_total[0].length);*/
					//rg.addText("\n", 0, 0, 0);
					//finalHeader[0]="";
					//rg.tableBody(grand_total, cellWidth, alignment);
					//// old departmentwise report commented by Mangesh
					/*if (reportDataPay != null) {
						finalHeader = new String[reportDataPay[0].length-7];
						cellWidth = new int[reportDataPay[0].length-7];
						alignment = new int[reportDataPay[0].length-7];
						cellWidth[0]=10;
						alignment[0]=0;
						cellWidth[1]=7;
						alignment[1]=2;
						finalHeader[0]="Department Name";
						for (int i = 8; i < reportDataPay[0].length; i++) {
							finalHeader[i-7] = String.valueOf(reportDataPay[0][i]);
							
							if (i > 8) {
								cellWidth[i-7] = 7;
								alignment[i-7] = 2;
							} 
						}

						finalData = new Object[reportDataPay.length - 1][reportDataPay[0].length];
						*//**
						 * following loop is used to set the total amount branch wise and department wise
						 *//*
						for (int i = 0; i < finalData.length; i++) {
							for (int j = 0; j < finalData[0].length; j++) {
								finalData[i][j] = reportDataPay[i + 1][j];
								//System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
							}
						}

						Object totalByColumn[][] = new Object[1][finalData[0].length-7];

						totalByColumn[0][0] = String.valueOf(loop_data[a][1]);

						//totalHeader[1] = "";
						totalByColumn[0][1] = " ";
						*//**
						 * following loop is used to set the grand total amount column wise
						 *//*
						for (int i = 8; i < finalData[0].length; i++) {
							double total = 0.00;
							for (int j = 0; j < finalData.length; j++) {
								if (String.valueOf(finalData[j][i]).equals("null")) {
									finalData[j][i] = "0.00";
								}
								total += Double.parseDouble(String
										.valueOf(finalData[j][i]));
							}
							//totalHeader[i] = "";
							totList.add(Utility.twoDecimals(total));
							totalByColumn[0][i-7] = Utility.twoDecimals(total);
						}
						//rg.addText("\n", 0, 0, 0);
						//rg.tableBody(finalHeader, totalByColumn, cellWidth,
								//alignment);
						//rg.tableBody(totalByColumn, cellWidth, alignment);
						deptData.add(totalByColumn[0]);
						recCount++;
						// colCount=finalData[0].length;
					}
				}

				System.out.println("pk record count>>>>>>>>>" + recCount);
				System.out.println("Size of lit" + totList.size());

				if (recCount != 0) {
					Object[][] listValues = new Object[recCount][(totList
							.size() / recCount)];

					int arrCount = 0;
					for (int i = 0; i < recCount; i++) {
						for (int j = 0; j < (totList.size() / recCount); j++) {
							listValues[i][j] = totList.get(arrCount);
							arrCount++;
							logger
									.info("-----------------values are listValues["
											+ i
											+ "]["
											+ j
											+ "]="
											+ listValues[i][j]);
						}
					}

					Object grand_total[][] = new Object[1][listValues[0].length+1];

					grand_total[0][0] = "TOTAL :-";
					// grand_total[0][5]=" ";

					for (int i = 0; i < listValues[0].length; i++) {
						double total = 0.00;
						for (int j = 0; j < listValues.length; j++) {
							if (String.valueOf(listValues[j][i]).equals("null")) {
								listValues[j][i] = "0.00";
							}
							total += Double.parseDouble(String
									.valueOf(listValues[j][i]));
						}
						grand_total[0][i +1] = Utility.twoDecimals(total);
					}
					Object [][]deptFinalData =new Object[deptData.size()][finalData[0].length-7];
					
					for (int i = 0; i < deptFinalData.length; i++) {
						deptFinalData[i]= (Object[])deptData.get(i);
					}
					rg.tableBody(finalHeader, deptFinalData, cellWidth, alignment);
					cellWidth = getCellWidthBrnDept(grand_total[0].length);
					alignment = getAlignmentBrnDept(grand_total[0].length);
					rg.addText("\n", 0, 0, 0);
					finalHeader[0]="";
					rg.tableBody(grand_total, cellWidth, alignment);*/
				} else {
					//rg.addText("Records are not available.", 0, 1, 0);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * following function is called when department is selected and branch is
	 * not selected to generate the report
	 * 
	 * @param loop_data
	 * @param rg
	 * @param bean
	 */
	public void reportDataBranch(Object[][] loop_data, ReportGenerator rg,
			MonthlyArrearsReport bean, String chekFlag) {
		ArrayList totList = new ArrayList();
		int recCount = 0, arrEmpLength = 0;
		Vector<Object[]> branchData = new Vector<Object[]>();
		Object finalData[][]=null;
		String finalHeader[]=null;
		
		int[] cellWidth = null;
		int[] alignment = null;
		if (loop_data.length > 0) {
			for (int a = 0; a < loop_data.length; a++) {
				/*String arrearEmp = " SELECT HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH  ,ARREARS_YEAR ,ARREARS_DAYS ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR FROM HRMS_EMP_OFFC "
						+ "	INNER JOIN HRMS_ARREARS_"
						+ bean.getYear()
						+ " ON(HRMS_ARREARS_"
						+ bean.getYear()
						+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
						+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
						+ bean.getYear()
						+ ".ARREARS_CODE)  "
						+ "  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
						+ bean.getYear() + ".EMP_ID) "
						+ "	WHERE ARREARS_PAID_MONTH=" + bean.getMonth()
						+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
						+ " and arrears_type ='" + bean.getArrearType() + "'";
*/
				
				
				
				String  arrearEmp = " SELECT DISTINCT HRMS_ARREARS_"
					+ bean.getYear()
					+ ".EMP_ID,EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,TYPE_NAME,HRMS_ARREARS_LEDGER.ARREARS_CODE";
			if(chekFlag.equals("Y")){
				arrearEmp += " ,''  ,'',''" ;
			}else{
				arrearEmp += " ,ARREARS_MONTH  ,ARREARS_YEAR,ARREARS_DAYS" ;
			}
			
			
			arrearEmp += " ,NVL(SAL_ACCNO_REGULAR,' '),SAL_MICR_REGULAR  FROM HRMS_EMP_OFFC "
					+"	INNER JOIN HRMS_ARREARS_"
					+ bean.getYear()
					+ " ON(HRMS_ARREARS_"
					+ bean.getYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ "  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
					+ "	INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ bean.getYear()
					+ ".ARREARS_CODE)  "
					+ " 	 LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_ARREARS_"
					+ bean.getYear() + ".EMP_ID) "
				 	+ "	WHERE ARREARS_PAID_MONTH="
					+ bean.getMonth()
					+ " AND ARREARS_PAID_YEAR=" + bean.getYear()
					+ " and arrears_type ='"
					+ bean.getArrearType() + "'";
				
			 
				
				
				String where = " AND EMP_CENTER=" + loop_data[a][0] + " ";
				if (!(bean.getOnHold().equals("A"))) {
					where += "and ARREARS_onhold='" + bean.getOnHold() + "' ";
				}
				if(!bean.getDeptCode().trim().equals(""))
				where += "AND EMP_DEPT=" + bean.getDeptCode();

				arrearEmp = arrearEmp + where;

				if (!(bean.getDivName().equals("") || bean.getDivName().equals(
						"null"))) {
					arrearEmp += " AND EMP_DIV=" + bean.getDivCode();
				}
				arrearEmp += " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
				
				if(!chekFlag.equals("Y"))
				{
					arrearEmp += " ,HRMS_ARREARS_"+ bean.getYear() + ".ARREARS_MONTH ";
				}
				Object[][] reportDataPay = getReportData(arrearEmp, bean,
						String.valueOf(loop_data[a][0]), arrEmpLength, chekFlag);
				
				// Object[][] reportDataPay =
				// getReportDataDept(selectSalaryLoop,salReg);
				/**
				 * following condition is used to calculate the total amount row
				 * wise and column wise.
				 */
				if (reportDataPay != null) {
					finalHeader = new String[reportDataPay[0].length];
					 cellWidth = new int[reportDataPay[0].length];
					 alignment = new int[reportDataPay[0].length];
					for (int i = 0; i < reportDataPay[0].length; i++) {
						finalHeader[i] = String.valueOf(reportDataPay[0][i]);
						alignment[i] = 0;
						if (i > 1) {
							cellWidth[i] = 7;
							alignment[i] = 0;
						} else {
							cellWidth[0] = 8;
							cellWidth[1] = 15;
						}
					}

					 finalData = new Object[reportDataPay.length - 1][reportDataPay[0].length];
					for (int i = 0; i < finalData.length; i++) {
						for (int j = 0; j < finalData[0].length; j++) {
							finalData[i][j] = reportDataPay[i + 1][j];
							// System.out.println("data----------------------"+String.valueOf(finalDataWithHeader[i+1][j])+"---"+j);
						}
					}

					Object totalByColumn[][] = new Object[1][finalData[0].length];

					String totalHeader[] = new String[finalData.length];
					totalHeader[0] = "";
					totalByColumn[0][0] = "TOTAL :-";

					// totalHeader[1] = "";
					totalByColumn[0][1] = " ";

					for (int i = 8; i < finalData[0].length; i++) {
						double total = 0.00;
						for (int j = 0; j < finalData.length; j++) {
							if (String.valueOf(finalData[j][i]).equals("null")
									|| String.valueOf(finalData[j][i]).equals("")
									|| String.valueOf(finalData[j][i]).equals(
											"null.00")) {
								finalData[j][i] = "0.00";
							}
							total += Double.parseDouble(String
									.valueOf(finalData[j][i]));
						}
						// totalHeader[i] = "";
						totList.add(Utility.twoDecimals(total));
						totalByColumn[0][i] = Utility.twoDecimals(total);
					}
					//rg.addText("\n", 0, 0, 0);
					//rg.addText("Branch Name :"+String.valueOf(loop_data[a][1]), 0, 0, 0);
					//rg.tableBody(finalHeader, finalData, cellWidth, alignment);
					//rg.tableBody(totalByColumn, cellWidth, alignment);
					recCount++;
					// colCount=finalData[0].length;
				} // end of if reportData condition
			}
			if (recCount != 0) {
				Object[][] listValues = new Object[recCount][(totList.size() / recCount)];

				int arrCount = 0;
				for (int i = 0; i < recCount; i++) {
					for (int j = 0; j < (totList.size() / recCount); j++) {
						listValues[i][j] = totList.get(arrCount);
						arrCount++;
						/*logger.info("-----------------values are listValues["
								+ i + "][" + j + "]=" + listValues[i][j]);*/
					}
				}

				Object grand_total[][] = new Object[1][listValues[0].length + 8];

				grand_total[0][0] = "TOTAL :-";
				grand_total[0][1] = " ";
				grand_total[0][2] = " ";
				grand_total[0][3] = " ";
				grand_total[0][4] = " ";
				// grand_total[0][5]=" ";

				for (int i = 0; i < listValues[0].length; i++) {
					double total = 0.00;
					for (int j = 0; j < listValues.length; j++) {
						if (String.valueOf(listValues[j][i]).equals("null")) {
							listValues[j][i] = "0.00";
						}
						total += Double.parseDouble(String
								.valueOf(listValues[j][i]));
					}
					grand_total[0][i + 8] = Utility.twoDecimals(total);
				}
				int[] cellWidthTotal = getCellWidth(grand_total[0].length);
				int[] alignmentTotal = getAlignment(grand_total[0].length);

				//rg.addText("\n", 0, 0, 0);
				//rg.addText("GRAND TOTAL", 0, 0, 0);
				//rg.tableBody(grand_total, cellWidthTotal, alignmentTotal);
				//Object [][]branchFinalData =new Object[branchData.size()][finalData[0].length-7];
				
				/*for (int i = 0; i < branchFinalData.length; i++) {
					branchFinalData[i]= (Object[])branchData.get(i);
				}*/
				/*logger.info("finalHeader length=="+finalHeader.length);
				logger.info("branchFinalData length=="+branchFinalData[0].length);
				logger.info("cellWidth length=="+cellWidth.length);
				logger.info("alignment length=="+alignment.length);*/
				//rg.tableBody(finalHeader, branchFinalData, cellWidth, alignment);
				/*cellWidth = getCellWidthBrnDept(grand_total[0].length);
				alignment = getAlignmentBrnDept(grand_total[0].length);*/
				//rg.addText("\n", 0, 0, 0);
				//finalHeader[0]="";
				//rg.tableBody(grand_total, cellWidth, alignment);
			} else {
				//rg.addText("Records are not available.", 0, 1, 0);
			}
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

}
