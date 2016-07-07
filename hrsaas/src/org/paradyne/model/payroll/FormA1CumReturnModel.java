/**
 * 
 */
package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.FormA1CumReturn;
import org.paradyne.bean.payroll.YearlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;

/**
 * @author AA0623 
 *
 */
public class FormA1CumReturnModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FormA1CumReturnModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
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
	public void getReport(FormA1CumReturn formA1CumReturn,
			HttpServletResponse response, HttpServletRequest request,
			 String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			Date date = new Date();
			final String fromYear = formA1CumReturn.getFromYear(); // From Year

			final String toYear = formA1CumReturn.getToYear(); // To year
			String type = formA1CumReturn.getReport();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = "LWF1_Cum_Return_"+formA1CumReturn.getDivisionName()+"_"+fromYear+"-"+toYear+"_"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			//rds.setReportName("Form-A1 Cum Return");
			
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(formA1CumReturn.getUserEmpId());
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
		//	rds.setTotalColumns(15);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "FormA1CumReturn_input.action");
			}
			rg = getReport(rg, formA1CumReturn);
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
	
	
	
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, FormA1CumReturn formA1CumReturn) {
		try{
			
			Object[][] headingData = new Object[1][1];
			headingData[0][0] = "MAHARASHTRA LABOUR WELFARE BOARD";
			int[] cellWidthHeader = { 100 };
			int[] cellAlignHeader = { 1 };
			TableDataSet tableheadingData = new TableDataSet();
			tableheadingData.setData(headingData);
			tableheadingData.setCellWidth(cellWidthHeader);
			tableheadingData.setCellAlignment(cellAlignHeader);
			tableheadingData.setBodyFontDetails(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0));
			tableheadingData.setBorder(false);
			tableheadingData.setBodyFontStyle(1);
			rg.addTableToDoc(tableheadingData);
			
			
			Object[][] headingSubData1 = new Object[1][1];
			headingSubData1[0][0] = "HUTATMA BABU GANU, MUMBAI GIRNI KAMGAR KREEDA BHAVAN, SENAPATI BAPAT MARG, ELEPHINSTONE, MUMBAI - 400 013.";
			int[] cellWidthSubData1Header = { 100 };
			int[] cellAlignSubData1Header = { 1 };
			TableDataSet tableheadingSubData1 = new TableDataSet();
			tableheadingSubData1.setData(headingSubData1);
			tableheadingSubData1.setCellWidth(cellWidthSubData1Header);
			tableheadingSubData1.setCellAlignment(cellAlignSubData1Header);
			tableheadingSubData1.setBodyFontStyle(1);
			tableheadingSubData1.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
			tableheadingSubData1.setBorder(false);
			//tableheadingData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingSubData1);
			
			Object[][] headingSubData2 = new Object[1][1];
			headingSubData2[0][0] = "FORM A-1 CUM RETURN";
			int[] cellWidthSubData2Header = { 100 };
			int[] cellAlignSubData2Header = { 1 };
			TableDataSet tableheadingSubData2 = new TableDataSet();
			tableheadingSubData2.setData(headingSubData2);
			tableheadingSubData2.setCellWidth(cellWidthSubData2Header);
			tableheadingSubData2.setCellAlignment(cellAlignSubData2Header);
			tableheadingSubData2.setBodyFontStyle(1);
			tableheadingSubData2.setBodyFontDetails(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0));
			tableheadingSubData2.setBorder(false);
			//tableheadingData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingSubData2);
			
			Object[][] headingSubData3 = new Object[1][1];
			headingSubData3[0][0] = "( Vide Rule 3-A )";
			int[] cellWidthSubData3Header = { 100 };
			int[] cellAlignSubData3Header = { 1 };
			TableDataSet tableheadingSubData3 = new TableDataSet();
			tableheadingSubData3.setData(headingSubData3);
			tableheadingSubData3.setCellWidth(cellWidthSubData3Header);
			tableheadingSubData3.setCellAlignment(cellAlignSubData3Header);
			tableheadingSubData3.setBodyFontDetails(Font.FontFamily.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));
			tableheadingSubData3.setBodyFontStyle(1);
			tableheadingSubData3.setBorder(false);
			//tableheadingData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingSubData3);
			
			
			/*Object[][] headingAddr = new Object[4][1];
			headingAddr[0][0] = "MAHARASHTRA LABOUR WELFARE BOARD";
			headingAddr[1][0] = "HUTATMA BABU GANU, MUMBAI GIRNI KAMGAR KREEDA BHAVAN, SENAPATI BAPAT MARG, ELEPHINSTONE, MUMBAI - 400 013.";
			headingAddr[2][0] = "FORM A-1 CUM RETURN";
			headingAddr[3][0] = "( Vide Rule 3-A )";
			int[] cellWidthHeadingAddr = { 100 };
			int[] cellAlignHeadingAddr = { 1 };
			TableDataSet tableHeadingAddr = new TableDataSet();
			tableHeadingAddr.setData(headingAddr);
			tableHeadingAddr.setCellWidth(cellWidthHeadingAddr);
			tableHeadingAddr.setCellAlignment(cellAlignHeadingAddr);
			
			tableHeadingAddr.setBorder(false);
			tableHeadingAddr.setBlankRowsBelow(1);
			rg.addTableToDoc(tableHeadingAddr);*/
			
			Object[][] dataObj = new Object[1][1];
			dataObj[0][0] = "Note:\n 1\t This Form-cum Return is required to be submitted by every Employer along with the\n "
				+ " \t\t\t\t   payment of Employee’s and Employer’s six monthly contribution made by him in respect\n"
				+ " \t\t\t\t    of all Employees whose names stand on the register of his establishment as on 30th June /\n"
				+ " \t\t\t\t    31st December as per the Provisions of Section 6BB of the Bombay Labour Walfare Fund\n"
				+ " \t\t\t\t    Act, 1953\n "
				+ "2\t  Section 2(2)(B) of Bombay Labour Welfare Fund Act, 1953; “Supervisor” means who, \n"
				+ " \t\t\t\t    being employed in a supervisory capacity, draws wages exceeding Three Thousand Five \n"
				+ " \t\t\t\t    Hundred Rupees per month or exercise, either by the nature of the duties attached to \n"
				+ " \t\t\t\t    the office, or by reason of the powers vested in him, functions mainly of a managerial nature. \n"
				+ " 3\t  EEC= Employee’s Contribution, ERC= Employer’s Contribution";
			
			
			String data5Msg=" \t\t\t Manager\n\n";
			String data6Msg= " \t\t\t Employees working in supervisory capacity"
				+ " \t\t\t Drawing wages exceeding 3,500/- p.m. \n"
				+ " \t\t\t\t\t ( Please refer NOTE 2 above)";	
			String data7Msg="";
			String data8Msg="";
				
			TableDataSet data = new TableDataSet();
			data.setData(dataObj);
			data.setCellAlignment(new int[] { 0 });
			data.setCellWidth(new int[] { 100 });
			data.setBorder(false);
			data.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			//data.setBorderDetail(3);
			
			
			Object[][] dataObj1 = new Object[1][1];
			dataObj1[0][0] = "for office use only";
			TableDataSet data1 = new TableDataSet();
			data1.setData(dataObj1);
			data1.setCellAlignment(new int[] { 0 });
			data1.setCellWidth(new int[] { 100 });
			data1.setBorder(false);
			
			Object[][] dataObj2 = new Object[2][1];
			dataObj2[0][0] = "\n";
			dataObj2[1][0] = "\n\n\n\n\n\n\n";
			
			TableDataSet data2 = new TableDataSet();
			data2.setData(dataObj2);
			data2.setCellAlignment(new int[] { 1 });
			data2.setCellWidth(new int[] { 100 });
			///data2.setBorder(true);
			data2.setBorderDetail(3);
			HashMap<String, Object> map1 = rg.joinTableDataSet(data1, data2, false, 0);
			HashMap<String, Object> map2 = rg.joinTableDataSet(data, map1, true, 70);
			
			rg.addTableToDoc(map2);
			
			String query = " SELECT NVL(DIV_NAME,' '), NVL(DIV_ADDRESS1,' '), NVL(DIV_ADDRESS2,' '), NVL(DIV_ADDRESS3,' '), NVL(ESTABLISHMENT_CODE,0) "
				+ " FROM HRMS_DIVISION WHERE DIV_ID="+formA1CumReturn.getDivisionId();
			Object[][] divObj = getSqlModel().getSingleResult(query);
			
			Object[][] dataObj3 = new Object[1][2];
			if(String.valueOf(divObj[0][4]).equals("0"))
				dataObj3[0][0] = "Establishment Code No. :";
			else
				dataObj3[0][0] = "Establishment Code No. :"+String.valueOf(divObj[0][4]);
			dataObj3[0][1] = "";
			TableDataSet data3 = new TableDataSet();
			data3.setData(dataObj3);
			data3.setCellAlignment(new int[] { 0,0 });
			data3.setCellWidth(new int[] { 30,70 });
			
			data3.setBlankRowsAbove(1);
			data3.setBorder(false);
			rg.addTableToDoc(data3);
			
			Object[][] dataObj4 = new Object[7][1];
			dataObj4[0][0] = "1.\t Name & Address Of The Establishment";
			dataObj4[1][0] =                   String.valueOf(divObj[0][0])+"\n"+     String.valueOf(divObj[0][1])+"\n"+     String.valueOf(divObj[0][2]);
			dataObj4[2][0] = "2.\t Name Of The Employer";
			dataObj4[3][0] =                   String.valueOf(divObj[0][0]);
			dataObj4[4][0] = "3.\t Class Of The Establishment (i.e. whether Factory, " 
					+ " \n\t\t\t\t\t  motor tmnibus service, motor transport undertaking \n"
					+ " \t\t\t\t\t or commercial establishment, a shop, a residential hotel ,\n" 
					+ " \t\t\t\t\t restaurant , eating house , theatre or other place of \n"
					+ " \t\t\t\t\t amusement or public entertainment.)";
			dataObj4[5][0] = "";
			dataObj4[6][0] = "4.\t Total number of employees whose names\n \t\tstood on the establishment register as\n "
				+ " \t\t\t\t\t on 30th June\n" 
				+ " \t\t\t\t\t ------------------\n"
				+ " \t\t\t\t\t on 31st December)";
			
			TableDataSet data4 = new TableDataSet();
			data4.setData(dataObj4);
			data4.setCellAlignment(new int[] { 0 });
			data4.setCellWidth(new int[] { 100 });
			data4.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			data4.setBorder(false);
			///data4.setBlankRowsBelow(1);
			rg.addTableToDoc(data4);
			
			
			
			String[] header = new String[5];
			header[0] = "No.of Employees\n\n\n";
			header[1] = "E.E.C Rs\n\n\n";
			header[2] = "E.R.C Rs\n\n\n";
			header[3] = "Penal Int\n\n\n";
			header[4] = "Total Rs\n\n\n";
			
			//Code for getting table values
			String lwfCodeQuery = " SELECT LWF_DEBIT_CODE FROM HRMS_LWF_CONFIGURATION WHERE LWF_APPLICABLE='Y' ";
			Object[][] lwfCode = getSqlModel().getSingleResult(lwfCodeQuery);
			
			String deductionMonths = " SELECT LWF_MONTH_DEDUCTIONS FROM HRMS_LWF_SLAB_HDR WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MON-YYYY')= "
				+ " (SELECT MAX(LWF_EFFECTIVE_FROM) FROM HRMS_LWF_SLAB_HDR WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'YYYY') <= '"+formA1CumReturn.getFromYear()+"')";
			Object[][] deductMonths = getSqlModel().getSingleResult(deductionMonths);
			
			double amoutEEC = 0.0;
			double amoutERC = 0.0;
			double total = 0.0;
			double totalWords = 0.0;
			
			Object salarySlabObjFinal[][]= null;
			Object[][] monthObj = null;
			Object[][] totalAmtWord = new Object[1][1];
			if(deductMonths!=null && deductMonths.length > 0){
				for (int i = 0; i < deductMonths.length; i++) {
					System.out.println("deductMonths[i][0]==="+String.valueOf(deductMonths[i][0]));
					String[] splitMonths = String.valueOf(deductMonths[i][0]).split(",");
					String slabQuery = " SELECT LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB, LWF_MONTH_DEDUCTIONS,LWF_SLAB_FROM,LWF_SLAB_TO FROM HRMS_LWF_SLAB_DTL "
						+ " LEFT JOIN HRMS_LWF_SLAB_HDR ON(HRMS_LWF_SLAB_HDR.LWF_CODE=HRMS_LWF_SLAB_DTL.LWF_CODE) "
						+ " WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MON-YYYY')  = (SELECT MAX(LWF_EFFECTIVE_FROM) FROM HRMS_LWF_SLAB_HDR " 
						+ " WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'YYYY') <= '"+formA1CumReturn.getFromYear()+"')"
						+ " ORDER BY LWF_DTL_CODE";
					Object[][] slabObj = getSqlModel().getSingleResult(slabQuery);
					monthObj = new Object[3+(splitMonths.length*slabObj.length)][1];
					salarySlabObjFinal = new Object[3+(slabObj.length)][8];
					
					monthObj[0][0] = "\n\n";
					monthObj[1][0] = "\n\n";
					monthObj[monthObj.length-1][0]="Total";
					//dataObj5[1][0] = data5Msg;
					salarySlabObjFinal[0][0] = "a.";
					
					salarySlabObjFinal[0][1] = data5Msg;
					salarySlabObjFinal[0][2] = "\n\n";
					salarySlabObjFinal[0][3] = "NIL\n\n";
					salarySlabObjFinal[0][4] = "NIL\n\n";
					salarySlabObjFinal[0][5] = "NIL\n\n";
					salarySlabObjFinal[0][6] = "NIL\n\n";
					salarySlabObjFinal[0][7] = "NIL\n\n";
					salarySlabObjFinal[1][0] = "b.";
					salarySlabObjFinal[1][1] = data6Msg;
					salarySlabObjFinal[1][2] = "\n\n";
					salarySlabObjFinal[1][3] = "NIL\n\n";
					salarySlabObjFinal[1][4] = "NIL\n\n";
					salarySlabObjFinal[1][5] = "NIL\n\n";
					salarySlabObjFinal[1][6] = "NIL\n\n";
					salarySlabObjFinal[1][7] = "NIL\n\n";
					salarySlabObjFinal[2][0] = "c.";
					//salarySlabObjFinal[4][0] = "5.";
					///salarySlabObjFinal[4][1] = "Total";
					
					
						
						if(slabObj!=null && slabObj.length>0){
							int count=0;
							for (int k = 0; k < slabObj.length; k++) {
								if(k==slabObj.length-1){
									data8Msg +="\t\t\t\tEmployees drawing Wages exceeding \n"
										+ " \t\t\t of Rs. "+String.valueOf(slabObj[k][3])+"/- p.m.  \n"
									///	+ " \t\t\t  \n"
										+ " \t\t\t EEC @ Rs. "+String.valueOf(slabObj[k][0])+" per employees \n"
										+ " \t\t\t ERC @ Rs. "+String.valueOf(slabObj[k][1])+" per Employee  \n";
								}else{
									data7Msg +="\t\t\tEmployees drawing wages upto & inclusive \n"
										+ " \t\t\t of Rs. "+String.valueOf(slabObj[k][4])+"/- p.m.  \n"
									///	+ " \t\t\t  \n"
										+ " \t\t\t EEC @ Rs. "+String.valueOf(slabObj[k][0])+" per employees \n"
										+ " \t\t\t ERC @ Rs. "+String.valueOf(slabObj[k][1])+" per Employee  \n"
									+ " \t\t\t  \n";
								}
								
								double totalCount = 0.0;
								double totalEEC = 0.0;
								double totalERC = 0.0;
								for (int j = 0; j < splitMonths.length; j++) {
								
								monthObj[2+count][0] = Utility.month(Integer.parseInt(splitMonths[j].trim()))+"-"+ formA1CumReturn.getFromYear();
								
								//salarySlabObjFinal[2+count][0] = "";
								//salarySlabObjFinal[2+count][1] = "";
								
								
								salarySlabObjFinal[2][1] = data7Msg;
								//salarySlabObjFinal[3+count][0] = "";
								salarySlabObjFinal[3][1] = data8Msg;
								
								
								salarySlabObjFinal[2+count][2] = "\n"+checkNull(String.valueOf(salarySlabObjFinal[2+count][2]))+"\n"+Utility.month(Integer.parseInt(splitMonths[j].trim()))+"-"+ formA1CumReturn.getFromYear();
								
								
								String countQuery = " SELECT COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+formA1CumReturn.getFromYear()+" WHERE SAL_LEDGER_CODE =  "
								+ " (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH='"+splitMonths[j].trim()+"' "
								+ " AND LEDGER_DIVISION="+formA1CumReturn.getDivisionId()+" AND LEDGER_YEAR ='"+formA1CumReturn.getFromYear()+"') "
								+ " AND SAL_DEBIT_CODE = "+lwfCode[0][0]+"  AND SAL_AMOUNT ="+slabObj[k][0];
								Object[][] countObj = getSqlModel().getSingleResult(countQuery);
								
								
								salarySlabObjFinal[salarySlabObjFinal.length-1][0] = "5.";
								salarySlabObjFinal[salarySlabObjFinal.length-1][1] = "Total of (a) to (c) above";
								
								
								totalCount+=(Double.parseDouble(checkNullValue(String.valueOf(salarySlabObjFinal[2+count][3]).trim()))+Double.parseDouble(checkNullValue(String.valueOf(countObj[0][0]).trim())));
								
								salarySlabObjFinal[2+count][3] = "\n"+checkNull(String.valueOf(salarySlabObjFinal[2+count][3]))+"\n"+String.valueOf(countObj[0][0]);//total employees
								
								
								
								
								amoutEEC = Double.parseDouble(String.valueOf(slabObj[j][0]))*Double.parseDouble(String.valueOf(countObj[0][0]));
								
								totalEEC+=(Double.parseDouble(checkNullValue(String.valueOf(salarySlabObjFinal[2+count][4])))+amoutEEC);
								
								salarySlabObjFinal[2+count][4] = "\n"+checkNull(String.valueOf(salarySlabObjFinal[2+count][4]))+"\n"+amoutEEC; // amount of LWF eec
								
								
								
								amoutERC = Double.parseDouble(String.valueOf(slabObj[j][1]))*Double.parseDouble(String.valueOf(countObj[0][0]));
								
								totalERC+=(Double.parseDouble(checkNullValue(String.valueOf(salarySlabObjFinal[2+count][5])))+amoutERC);
								
								salarySlabObjFinal[2+count][5] = "\n"+checkNull(String.valueOf(salarySlabObjFinal[2+count][5]))+"\n"+amoutERC; // amount of LWF erc
								salarySlabObjFinal[2+count][6] = "\n\n\n"+"0\n"+"0";// penal amt
								total = amoutEEC+amoutERC;
								salarySlabObjFinal[2+count][7] = "\n"+checkNull(String.valueOf(salarySlabObjFinal[2+count][7]))+"\n"+total; // total amt
								
								
								// Showing Total Records start
								salarySlabObjFinal[salarySlabObjFinal.length-1][3] = totalCount;
									
								salarySlabObjFinal[salarySlabObjFinal.length-1][4] =totalEEC;
								
								salarySlabObjFinal[salarySlabObjFinal.length-1][5] = totalERC;
								
								salarySlabObjFinal[salarySlabObjFinal.length-1][6] = "0";
								
								salarySlabObjFinal[salarySlabObjFinal.length-1][7] = checkNullValue(String.valueOf(total));
								
								
								// Showing Total Records in words  start
								totalWords = Double
								.parseDouble(String.valueOf(salarySlabObjFinal[salarySlabObjFinal.length-1][7]));
								
								totalAmtWord[0][0] = "( "+Utility.convert(totalWords)+" )";
								
								//count++;
							}
								count++;
						}
					}
				}
				
				
				
			}
			
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(new String[] {"SNo","Particulars","Month","No.of Employees","E.E.C. Rs","E.R.C.  Rs","Penal  Int.","TOTAL  Rs"});// defining headers
			tdstable.setCellAlignment(new int[]{0,0,2,2,2,2,2,2});
			tdstable.setCellWidth(new int[]{5,34,16,12,8,8,8,8});
			///tdstable.setCellColSpan(new int[] { 12 });
			tdstable.setData(salarySlabObjFinal);// data object from query Loan Deducted Under Debit Head
		///	tdstable.setColumnSum(new int[]{3,4,5,6,7});
			tdstable.setHeaderBorderDetail(3);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			tdstable.setBorderDetail(3);
			//tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			
			
			TableDataSet totalAmt = new TableDataSet();
			//data10.setHeader(new String[] {"\n\n"});
			totalAmt.setData(totalAmtWord);
			totalAmt.setCellAlignment(new int[] { 2 });
			totalAmt.setCellWidth(new int[] { 100 });
			rg.addTableToDoc(totalAmt);
			
			
			
			String data9Msg = "5.\t Total of (a) to (c) above \n";
			/*+ "6.\t Mode of payment :- \n"
			+ " \t\t (please specify No. & date of cheque /draft/money \n"
			+ " \t\t order/ cash against this item.)" ;*/
			Object[][] dataObj5 = new Object[6][1];
			dataObj5[0][0] = "";
			dataObj5[1][0] = data5Msg;
			dataObj5[2][0] = data6Msg;
			dataObj5[3][0] = data7Msg;
			dataObj5[4][0] = data8Msg;
			dataObj5[5][0] = data9Msg;
					
			/*TableDataSet data5 = new TableDataSet();
			
			data5.setData(dataObj5);
			data5.setCellAlignment(new int[] { 0 });
			data5.setCellWidth(new int[] { 100 });
			data5.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			data5.setBorderDetail(3);
			
			data5.setHeaderTable(false);
			data5.setBorder(false);
			
			
			
			
			TableDataSet data10 = new TableDataSet();
			data10.setHeader(new String[] {"Month\n\n\n\n"});
			data10.setData(monthObj);
			data10.setCellAlignment(new int[] { 2 });
			data10.setCellWidth(new int[] { 70 });
			data10.setBorderDetail(3);
			data10.setBorder(false);
			
			TableDataSet data6 = new TableDataSet();
			data6.setHeader(header);
			
			data6.setHeaderBorderDetail(3);
			
			data6.setData(salarySlabObjFinal);
			data6.setCellAlignment(new int[] { 2,2,2,2,2 });
			data6.setCellWidth(new int[] { 30,15,15,15,15 });
		///	data6.setColumnSum(new int[]{0,1,2,3,4});
			data6.setBorderDetail(3);
			
			
			TableDataSet totalAmt = new TableDataSet();
			//data10.setHeader(new String[] {"\n\n"});
			totalAmt.setData(totalAmtWord);
			totalAmt.setCellAlignment(new int[] { 2 });
			totalAmt.setCellWidth(new int[] { 100 });
			
			HashMap<String, Object> map8 = rg.joinTableDataSet(data6, totalAmt, false, 40);
			//rg.addTableToDoc(map8);
			
			HashMap<String, Object> map3 = rg.joinTableDataSet(data10, map8, true, 30);
			HashMap<String, Object> map4 = rg.joinTableDataSet(data5, map3, true, 40);
			rg.addTableToDoc(map4);*/
			
			
			Object[][] dataObjSub = new Object[1][1];
			dataObjSub[0][0] =  "6.\t Mode of payment :- \n"
				+ " \t\t (please specify No. & date of cheque /draft/money \n"
				+ " \t\t order/ cash against this item.)" ;
			TableDataSet dataSub = new TableDataSet();
			dataSub.setData(dataObjSub);
			dataSub.setCellAlignment(new int[] { 0 });
			dataSub.setCellWidth(new int[] { 100 });
			dataSub.setBorder(false);
			dataSub.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			rg.addTableToDoc(dataSub);
			
			
			Object[][] dataObj7 = new Object[1][1];
			dataObj7[0][0] = " \t\t IMPORTANT";
			TableDataSet data7 = new TableDataSet();
			data7.setData(dataObj7);
			data7.setCellAlignment(new int[] { 0 });
			data7.setCellWidth(new int[] { 100 });
			
			data7.setBorder(false);
			data7.setBlankRowsAbove(1);
			data7.setBodyFontStyle(1);
			rg.addTableToDoc(data7);
			
			Object[][] dataObj8 = new Object[5][1];
			dataObj8[0][0] = " \t\t 1) Cheque /DD should be drawn to each Estt, Code Number\n"
				+ " \t\t\t\t   Separately & in favour of Maharashtra Labour Welfare Fund.";
			dataObj8[1][0] = " \t\t 2) Cash payment will be accepted from 10.30 a.m. to 3.00 p.m.";
			dataObj8[2][0] = " \t\t 3) Code no. of the Establishment allotted to you should be quoted\n"
				+ " \t\t \t\t at the appropriate place in this form.";
			dataObj8[3][0] = " \t\t 4) DD should be payable at BOMBAY only.";
			dataObj8[4][0] = " \t\t 5) please write the Establishment code number on the back\n" +
					" \t\t side of the cheque/DD";
					
			TableDataSet data8 = new TableDataSet();
			data8.setData(dataObj8);
			data8.setCellAlignment(new int[] { 0 });
			data8.setCellWidth(new int[] { 100 });
			data8.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			data8.setBorder(false);
			
			Object[][] dataObj9 = new Object[5][1];
			dataObj9[0][0] = "\t\tCheque No: ";
			dataObj9[1][0] = "\t\tCheque Date: ";
			dataObj9[2][0] = " Certified that the information/particulars furnished above is/are\n"
				+ " true to the best of my knowledge & behalf";
			dataObj9[3][0] = "\n\n\n\n";
			dataObj9[4][0] = "Signature with name & designation of \nthe Authority filling this Form-cum Return.";
					
			TableDataSet data9 = new TableDataSet();
			data9.setData(dataObj9);
			data9.setCellAlignment(new int[] { 0 });
			data9.setCellWidth(new int[] { 100 });
			data9.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			data9.setBorder(false);
			
			HashMap<String, Object> map5 = rg.joinTableDataSet(data8, data9, true, 50);
			rg.addTableToDoc(map5);
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
}
