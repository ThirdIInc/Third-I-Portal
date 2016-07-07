package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Form6A;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;


public class Form6AModel extends ModelBase {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Form6AModel.class);
	
	private NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT.
	 * 
	 * @param form6a
	 * @param response
	 */
	public void getReport(Form6A form6a, HttpServletResponse response,HttpServletRequest request, String reportPath, String logoPath) {
		try {
			
			String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' '),NVL(DIV_PFACCOUNTNO,' '), NVL(ACCOUNT_GROUP_CODE,0), NVL(HRMS_DIVISION.DIV_ABBR,' ')  FROM HRMS_DIVISION WHERE DIV_ID=" + form6a.getDivId() + " ";
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);

			ReportDataSet rds = new ReportDataSet();
			String reportType = form6a.getReportType();
			String fileName = "PF6A_" + divisionObj[0][6] + "_" + form6a.getFromYear() + "-" + form6a.getToYear() + "_" + Utility.getRandomNumber(1000);
			
			rds.setReportType(reportType);
			rds.setFileName(fileName);
			rds.setReportName("Form-6A");
			//rds.setPageSize("A4");
			//rds.setUserEmpId(form6a.getUserEmpId());
			rds.setPageOrientation("landscape");
			rds.setReportHeaderRequired(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new ReportGenerator(rds, session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName + "." + reportType);
				rg = new ReportGenerator(rds, reportPath, session, context,	request);
				request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
				request.setAttribute("fileName", fileName + "." + reportType);
				request.setAttribute("action", "Form6A_input.action?path=" + reportPath + fileName + "." + reportType);
			}
			
			if (reportType.equalsIgnoreCase("pdf")) {
				TableDataSet subtitleName = new TableDataSet();
				Object obj[][] = new Object[2][3];
				//obj[0][0] = rg.getImage("D:/HRSAAS9FEB11/pims/WebContent/pages/images/employee/Form6A.bmp");
				obj[0][0] = rg.getImage(logoPath);
				obj[0][1] = "Form 6A (Revised) \n (For Unexempted Establishment Only)\n THE EMPLOYEES PROVIDENT FUND SCHEME, 1952 [PARA 43] \n AND \n THE EMPLOYEES PENSION SCHEME, 1995 [PARA 20(4)]";
				/*
				obj[1][1] = "Annual statement of contribution for the currency period from 1st April "
					+ form6a.getFromYear() + " To 31st March "+ form6a.getToYear();
				*/
				obj[1][1] = "Annual statement of contribution for the currency period from "
					+ Utility.month(Integer.parseInt(form6a.getFromMonth())) + " " 
					+ form6a.getFromYear() + " To " + Utility.month(Integer.parseInt(form6a.getToMonth())) + " "
					+ form6a.getToYear();
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 0, 1, 0 });
				subtitleName.setCellWidth(new int[] { 15, 70, 15 });
				subtitleName.setCellColSpan(new int[] { 1, 9, 0 });
				subtitleName.setBodyFontStyle(1);
				subtitleName.setBorder(false);
				//subtitleName.setHeaderTable(true);
				subtitleName.setBlankRowsBelow(1);
				rg.addTableToDoc(subtitleName);
			} else {
				TableDataSet subtitleName = new TableDataSet();
				Object obj[][] = new Object[2][2];
				//obj[0][0] = rg.getImage("D:/HRSAAS9FEB11/pims/WebContent/pages/images/employee/Form6A.bmp");
				//obj[0][0] = rg.getImage(logoPath);
				obj[0][1] = "Form 6A (Revised) \n (For Unexempted Establishment Only)\n THE EMPLOYEES PROVIDENT FUND SCHEME, 1952 [PARA 43] \n AND \n THE EMPLOYEES PENSION SCHEME, 1995 [PARA 20(4)]";
				/*
				obj[1][1] = "Annual statement of contribution for the currency period from 1st April "
					+ form6a.getFromYear() + " To 31st March "+ form6a.getToYear();
				*/
				obj[1][1] = "Annual statement of contribution for the currency period from "
					+ Utility.month(Integer.parseInt(form6a.getFromMonth())) + " " 
					+ form6a.getFromYear() + " To " + Utility.month(Integer.parseInt(form6a.getToMonth())) + " "
					+ form6a.getToYear();
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 0, 1 });
				subtitleName.setCellWidth(new int[] { 15, 70 });
				subtitleName.setCellColSpan(new int[] { 0, 10 });
				subtitleName.setBodyFontStyle(1);
				subtitleName.setBorder(false);
				//subtitleName.setHeaderTable(true);
				subtitleName.setBlankRowsBelow(1);
				rg.addTableToDoc(subtitleName);
			}
			//Establishment Data
			
			
			
			Object division[][] = new Object[5][2];
			
			if (divisionObj != null && divisionObj.length > 0) {
				division[0][0] = "Name & Address of the Establishment : " + String.valueOf(divisionObj[0][0]);
				
				//+ ", " + String.valueOf(divisionObj[0][1]) + "," + String.valueOf(divisionObj[0][2]) + "," + String.valueOf(divisionObj[0][3]);
				
				if (!String.valueOf(divisionObj[0][1]).trim().equals("")) {
					division[0][0] = division[0][0] + "," + this.checkNull(String.valueOf(divisionObj[0][1]));
				}
				if (!String.valueOf(divisionObj[0][2]).trim().equals("")) {
					division[0][0] = division[0][0] + "," + this.checkNull(String.valueOf(divisionObj[0][2]));
				}
				if (!String.valueOf(divisionObj[0][3]).trim().equals("")) {
					division[0][0] = division[0][0] + "," + this.checkNull(String.valueOf(divisionObj[0][3]));
				}
				
				
				division[0][1] = "Statutory rate of contribution : 12% ";
				//division[1][0] = 
				division[1][1] = "No of members voluntarily contributing at higher rate :";
			
			
			TableDataSet establishmentData = new TableDataSet();
			establishmentData.setCellAlignment(new int[] { 0, 0 });
			establishmentData.setCellWidth(new int[] { 60, 40 });
			establishmentData.setCellColSpan(new int[] { 5, 5 });
			establishmentData.setData(division);
			establishmentData.setBorder(false);
			establishmentData.setHeaderTable(false);
			rg.addTableToDoc(establishmentData);
			
			TableDataSet accountGroupData = new TableDataSet();
			accountGroupData.setCellAlignment(new int[] { 0, 0 });
			accountGroupData.setCellWidth(new int[] { 34, 33 });
			accountGroupData.setCellColSpan(new int[] { 5, 5 });
			accountGroupData.setData(new Object[][] { { "Code no of Establishment : " + String.valueOf(divisionObj[0][4]), "" } });
			//accountGroupData.setData(new Object[][]{{ "Code no of Establishment : "+String.valueOf(divisionObj[0][4]), "Accounts Group :"+String.valueOf(divisionObj[0][5])}});
			accountGroupData.setBorder(false);
			//accountGroupData.setHeaderTable(true);
			accountGroupData.setBlankRowsBelow(1);
			rg.addTableToDoc(accountGroupData);
			}
			// employee data
			
			/*
			 * Insert the data in HRMS_PF_DATA if not present
			 * 
			 */
			int frommonth = Integer.parseInt(form6a.getFromMonth());
			int fromyear = Integer.parseInt(form6a.getFromYear());
			int tomonth = Integer.parseInt(form6a.getToMonth());
			int toyear = Integer.parseInt(form6a.getToYear());
			int count = 0;
			if (fromyear == toyear) {
				count = tomonth - frommonth;
			} else {
				count = 12 - (frommonth - tomonth);
			}
			
			int month1 = 1;
			int month2 = 4;
			int year = Integer.parseInt(form6a.getFromYear());
			int month = frommonth;
			for (int i = 0; i < 12 && i <= count; i++) {
				/*
				if (i < 9) {
					year = Integer.parseInt(form6a.getFromYear());
					month = month2++;
				} else {
					year = Integer.parseInt(form6a.getToYear());
					month = month1++;
				}
				*/
				String PFDataQuery = "SELECT * FROM HRMS_PF_DATA WHERE PF_MONTH ="
						+ month
						+ " AND PF_YEAR = "
						+ year
						+ " AND  PF_EMP_DIV = " + form6a.getDivId();
				  
				Object [][] pfExist = getSqlModel().getSingleResult(PFDataQuery);
				 
				if (!(pfExist != null && pfExist.length > 0)) {
					try {

						PFDataModel pfModel = new PFDataModel();
						pfModel.initiate(context, session);
						pfModel.insertPFReportData(String.valueOf(month), String.valueOf(year), form6a.getDivId());
						pfModel.terminate();

					} catch (Exception e) {
						logger.error("Exception in calling PFData model for inserting PF data" + e);
					}
				}
				/*
				 * End insert data in HRMS_PF_DATA
				 * 
				 */
				month++;
				if (month > 12) {
					month = 1;
					year++;
				}
			}
			Object [][] dataFormula = new Object[1][4];
			dataFormula[0][0] = "0";
			dataFormula[0][1] = "0";
			dataFormula[0][2] = "0";
			dataFormula[0][3] = "0";
			
		String query = "SELECT NVL(HRMS_SALARY_MISC.SAL_GPFNO,' ') , UPPER(NVL(EMP_FNAME||' '||EMP_LNAME,' ')),"
				+ " SUM(PF_EMP_BASIC) AS WAGES,SUM(NVL(PF_PFEPF,0)) AS EMPLCONTRI,"
				+ " SUM(NVL(PF_CMP_F_PF,0)) AS EPF_DIFF, SUM(NVL(PF_CMP_PF,0)) AS PENSION_FUND, HRMS_EMP_OFFC.EMP_ID"
				+ " FROM HRMS_PF_DATA "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_PF_DATA.PF_EMP_ID) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_PF_DATA.PF_EMP_ID) "
				+ " WHERE  PF_EMP_DIV=" + form6a.getDivId();
	
		if (form6a.getBrnId() != null && form6a.getBrnId().length() > 0) {
			query += "AND HRMS_EMP_OFFC.EMP_CENTER=" + form6a.getBrnId()
					+ " ";
		} 
		if (form6a.getTypeId() != null && form6a.getTypeId().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE=" + form6a.getTypeId()
					+ " ";
		} 
		if (form6a.getDeptId() != null && form6a.getDeptId().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=" + form6a.getDeptId()
					+ " ";
		} 
		query += " AND ((PF_MONTH>=" + form6a.getFromMonth() + " AND PF_YEAR=" + form6a.getFromYear() + ") OR (PF_MONTH<=" + form6a.getToMonth() + " AND PF_YEAR=" + form6a.getToYear() + ")) "
					+ " GROUP BY HRMS_SALARY_MISC.SAL_GPFNO, NVL(EMP_FNAME||' '||EMP_LNAME,' '),HRMS_EMP_OFFC.EMP_ID";	

		Object[][] empData = getSqlModel().getSingleResult(query);

		Object finalObj[][] = new Object[empData.length + 1][11];

		/*finalObj[0][0] = "1";
		finalObj[0][1] = "2";
		finalObj[0][2] = "3";
		finalObj[0][3] = "4";
		finalObj[0][4] = "5";
		finalObj[0][5] = "6";
		finalObj[0][6] = "7";
		finalObj[0][7] = "8";
		finalObj[0][8] = "9";
		finalObj[0][9] = "10";*/

		double totalWages = 0.0;
		double totalEmpContri = 0.0;
		double totalEpfDiff = 0.0;
		double totalPensionFund = 0.0;
		double total = 0.0;
		double grandTotal = 0.0;
	
		if (empData != null && empData.length > 0) {
				for (int i = 0; i < empData.length; i++) {
					
					total = Double.parseDouble(String.valueOf(empData[i][3]))
							+ Double.parseDouble(String.valueOf(empData[i][4])) 
							+ Double.parseDouble(String.valueOf(empData[i][5]));
					finalObj[i][0] = i + 1;
					finalObj[i][1] = String.valueOf(empData[i][0]);
					finalObj[i][2] = String.valueOf(empData[i][1]);
					finalObj[i][3] = formatter.format(Double.parseDouble(String.valueOf(empData[i][2])));
					finalObj[i][4] = formatter.format(Double.parseDouble(String.valueOf(empData[i][3])));
					finalObj[i][5] = formatter.format(Double.parseDouble(String.valueOf(empData[i][4])));
					finalObj[i][6] = formatter.format(Double.parseDouble(String.valueOf(empData[i][5])));
					finalObj[i][7] = formatter.format(total);
					finalObj[i][8] = "0.00";
					finalObj[i][9] = "0.00";
					finalObj[i][10] = " ";
					
					totalWages = totalWages + Double.parseDouble(String.valueOf(empData[i][2]));
					totalEmpContri = totalEmpContri + Double.parseDouble(String.valueOf(empData[i][3]));
					totalEpfDiff = totalEpfDiff + Double.parseDouble(String.valueOf(empData[i][4]));
					totalPensionFund = totalPensionFund + Double.parseDouble(String.valueOf(empData[i][5]));
					grandTotal = grandTotal + total;
				}
			}
			finalObj[empData.length][2] = "Grand Total Rs.";
			finalObj[empData.length][3] = Utility.twoDecimals(formatter.format(totalWages));
			finalObj[empData.length][4] = Utility.twoDecimals(formatter.format(totalEmpContri));
			finalObj[empData.length][5] = Utility.twoDecimals(formatter.format(totalEpfDiff));
			finalObj[empData.length][6] = Utility.twoDecimals(formatter.format(totalPensionFund));
			finalObj[empData.length][7] = formatter.format(grandTotal);
		
			String header[][] = new String[2][11];

			header[0][0] = "Sr No";
			header[0][1] = "Account Number";
			header[0][2] = "Name of the Member(In block letters)";
			header[0][3] = "Wages, Retaining allowance(if any) & D.A.including cash value of food concession paid during the currency period ";
			header[0][4] = "Amount of Workers contributions deducted from the wages ";
			header[0][5] = "E.P.F.differences between 10% & 8.1/3%";
			header[0][6] = "Pension fund 8.1/3%";
			header[0][7] = "Total";
			header[0][8] = "Refund of advances";
			header[0][9] = "Rate of higher voluntary contribution (if any)";
			header[0][10] = "Remarks";
			
			header[1][0] = "1";
			header[1][1] = "2";
			header[1][2] = "3";
			header[1][3] = "4";
			header[1][4] = "5";
			header[1][5] = "6";
			header[1][6] = "7";
			header[1][7] = "";
			header[1][8] = "8";
			header[1][9] = "9";
			header[1][10] = "10";
			
			int[] cellwidth = { 5, 10, 15, 15, 10, 10, 10, 10, 10, 10, 20 };
			int[] alignment = { 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2 };
			
			
			if(finalObj!=null && finalObj.length > 0){
				TableDataSet form6aData = new TableDataSet();
				form6aData.setData(finalObj);
				form6aData.setHeaderData(header);
				form6aData.setCellAlignment(alignment);
				form6aData.setCellWidth(cellwidth);
				form6aData.setBorder(true);
				form6aData.setBorderDetail(3);
				//form6aData.setColumnSum(new int[]{3,4,5,6});
				form6aData.setHeaderTable(true);
				form6aData.setHeaderBorderDetail(3);
				form6aData.setHeaderLines(false);
				form6aData.setHeaderBGColor(new BaseColor(225, 225, 225));
				form6aData.setBlankRowsBelow(1);
				rg.addTableToDoc(form6aData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}

		}catch (Exception e) {
			logger.error("Exception in  getReport 2-------------------- " + e);
			e.printStackTrace();
		}
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
