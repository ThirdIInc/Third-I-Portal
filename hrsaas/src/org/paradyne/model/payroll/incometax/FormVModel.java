/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.FormV;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;

/**
 * @author Dipti
 *
 */
public class FormVModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.Form1Model.class);
	private NumberFormat formatter = new DecimalFormat("#0.00");
	
/**
 * THIS METHOD IS USED FOR GENERATING REPORT
 * @param request
 * @param response
 * @param fmv
 * @param path
 */
	public void getReport(HttpServletRequest request, HttpServletResponse response, FormV fmv, String path) {
		try {
			// TODO Auto-generated method stub
			
			String query="SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3, NVL(HRMS_DIVISION.DIV_ABBR,' ')"
				+"  FROM HRMS_DIVISION  WHERE HRMS_DIVISION.DIV_ID=?";// + fmv.getDivCode();
			
			Object[] parameterObj = null;
			parameterObj = new Object[1];
			parameterObj[0] = fmv.getDivCode();
			
			Object[][] queryData = getSqlModel().getSingleResult(query,parameterObj);
			
			String title = "FormV";
			String reportType = fmv.getReportType();
			String fileName = "PTAX_V_"+ queryData[0][2] +"_"
				+ Utility.month(Integer.parseInt(fmv.getFinmonth())).substring(0, 3) 
				+ fmv.getYear().substring(fmv.getYear().length()-2, fmv.getYear().length())
				+"_"+ Utility.getRandomNumber(1000);
			
			ReportDataSet  rds = new ReportDataSet();
			String reportPathName=fileName+"."+reportType;
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			//rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportHeaderRequired(false);
			//rds.setPageSize("A3");
			rds.setTotalColumns(8);
			ReportGenerator rg = null;
			
			if(path.equals("")){
				rg = new ReportGenerator(rds,session,context, request);
				new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+path+fileName+"."+reportType);
				rg = new ReportGenerator(rds,path,session,context, request);
				request.setAttribute("reportPath", path+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "FormV_input.action");
			}	
			
			//rg.addTextBold("FORM V                                   \t\t\t\t\t\t\t\t RETURNS OF TAX PAYABLE BY EMPLOYER", 0, 1, 0);			
			Object tilteObj[][] = null;
			TableDataSet subtitleName = null;
			subtitleName = new TableDataSet();
			tilteObj = new Object[1][2];
			tilteObj[0][0] = "FORM V";
			tilteObj[0][1] = "RETURNS OF TAX PAYABLE BY EMPLOYER";
			subtitleName.setData(tilteObj);
			subtitleName.setCellAlignment(new int[] { 1,1 });
			subtitleName.setCellWidth(new int[] { 15,85 });
			subtitleName.setBodyFontStyle(1);
			//subtitleName.setBodyFontSize(10);
			subtitleName.setBorder(false);
			//subtitleName.setBlankRowsBelow(1);
			subtitleName.setCellColSpan(new int[] { 2,4 });
			rg.addTableToDoc(subtitleName);
			
			//rg.addText("(See Rule 12)               \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Under sub-section (1) of Section 7 of the A.P Tax on Professions", 0, 1, 0);
			//rg.addText("                           \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Trades ,Callings and Employments Act, 1987.", 0, 1, 0);
			
			Object tilteObj1[][] = null;
			TableDataSet subtitleName1 = null;
			subtitleName1 = new TableDataSet();
			tilteObj1 = new Object[1][2];
			tilteObj1[0][0] = "(See Rule 12)";
			tilteObj1[0][1] = "Under sub-section (1) of Section 7 of the A.P Tax on Professions \n Trades ,Callings and Employments Act, 1987.";
			subtitleName1.setData(tilteObj1);
			subtitleName1.setCellAlignment(new int[] { 1,1 });
			subtitleName1.setCellWidth(new int[] { 15,85 });
			subtitleName1.setBorder(false);
			subtitleName1.setBlankRowsBelow(3);
			subtitleName1.setCellColSpan(new int[] { 2,4 });
			rg.addTableToDoc(subtitleName1);
			
			//rg.addText("\n", 0, 0, 0);
			
			
			String sql="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] sqlData = getSqlModel().getSingleResult(sql);
			
			String sqlTax=" SELECT DISTINCT HRMS_PTAX_DTL.PTAX_FROMAMT,HRMS_PTAX_DTL.PTAX_UPTOAMT,HRMS_PTAX_DTL.PTAX_FIXEDAMT FROM HRMS_PTAX_HDR " 
						 +" INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_HDR.PTAX_CODE = HRMS_PTAX_DTL.PTAX_CODE) " 
						 +" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_PARENT_CODE = HRMS_PTAX_HDR.PTAX_LOCATION) " 
						 +" INNER JOIN HRMS_CENTER ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION) " 
						 +" WHERE HRMS_CENTER.CENTER_ID IN ("+fmv.getBranchCode()+") AND HRMS_PTAX_HDR.PTAX_DATE = (SELECT MAX(HRMS_PTAX_HDR.PTAX_DATE) FROM HRMS_PTAX_HDR " 
						 +" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_PARENT_CODE = HRMS_PTAX_HDR.PTAX_LOCATION) "
						 +" INNER JOIN HRMS_CENTER ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION AND HRMS_CENTER.CENTER_ID IN ("+fmv.getBranchCode()+")) " 
						 +" WHERE HRMS_PTAX_HDR.PTAX_DATE < SYSDATE) "
						 +" ORDER BY HRMS_PTAX_DTL.PTAX_FROMAMT,HRMS_PTAX_DTL.PTAX_UPTOAMT,HRMS_PTAX_DTL.PTAX_FIXEDAMT ";
			
			Object[][] sqlTaxData = getSqlModel().getSingleResult(sqlTax);
			String branchTaxAmt = "";
			for (int i = 0; i < sqlTaxData.length; i++) 
				branchTaxAmt += ""+sqlTaxData[i][2]+",";
			if(branchTaxAmt.length() > 0)
				branchTaxAmt = branchTaxAmt.substring(0,branchTaxAmt.length()-1);
			
			System.out.println("------------------------Sting for code is "+branchTaxAmt);
			
			String profTaxCodeQuery = " SELECT HRMS_PTAX_HDR.PTAX_DEBIT_CODE,HRMS_PTAX_HDR.PTAX_CODE FROM HRMS_PTAX_HDR WHERE TO_CHAR(HRMS_PTAX_HDR.PTAX_DATE,'dd-MON-yyyy') = (select max(HRMS_PTAX_HDR.PTAX_DATE) FROM HRMS_PTAX_HDR WHERE to_char(HRMS_PTAX_HDR.PTAX_DATE,'yyyy-mm')<='"
				+ fmv.getYear()+ "-" + fmv.getFinmonth()
				+ "'  AND HRMS_PTAX_HDR.PTAX_LOCATION="
				+ " (SELECT NVL(CENTER_PTAX_STATE,'15') FROM HRMS_CENTER WHERE CENTER_ID="
				+ fmv.getBranchCode() + "))";
			Object[][] ptaxcodeObj = getSqlModel().getSingleResult(profTaxCodeQuery);
			Object[][] sqlEmpData = null;
			if(ptaxcodeObj != null && ptaxcodeObj.length > 0){
				String sqlEmp="SELECT HRMS_SAL_DEBITS_"+fmv.getYear()+".SAL_AMOUNT,COUNT(*) FROM HRMS_SAL_DEBITS_"+fmv.getYear()+""
					+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SAL_DEBITS_"+fmv.getYear()+".SAL_LEDGER_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE AND HRMS_SAL_DEBITS_"+fmv.getYear()+".SAL_DEBIT_CODE = "+ String.valueOf(ptaxcodeObj[0][0])+")"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+fmv.getYear()+".EMP_ID)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH = "+fmv.getFinmonth()+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR ="+fmv.getYear()
					+ " AND HRMS_SAL_DEBITS_"+fmv.getYear()+".SAL_AMOUNT IN ("+branchTaxAmt+")"
					+ " AND HRMS_DIVISION.DIV_ID = "+fmv.getDivCode()
					+ " AND HRMS_CENTER.CENTER_ID IN ("+fmv.getBranchCode()
					+") GROUP BY HRMS_SAL_DEBITS_"+fmv.getYear()+".SAL_AMOUNT";
				sqlEmpData = getSqlModel().getSingleResult(sqlEmp);
			}
			
			/*
			Object obj[][]=new Object[1][2];
			obj[0][0]="FORM V \n (See Rule 12)";
			obj[0][1]="RETURNS OF TAX PAYABLE BY EMPLOYER \n Under sub-section (1) of Section 7 of the A.P Tax on Professions \n Trades ,Callings and Employments Act, 1987.";
			*/
			Object objData[][]=new Object[3][2];
			objData[0][0]="Return of tax payable for the month ending on :";
			objData[0][1]=Utility.month(Integer.parseInt(fmv.getFinmonth()))+"-"+fmv.getYear();
			objData[1][0]="Name of the Employer :";
			objData[1][1]=""+ queryData[0][0];
			objData[2][0]="Address :";
			objData[2][1]=""+ queryData[0][1];
			Object objText[][]=new Object[2][1];
			objText[0][0]="Registration Certificate No. :";
			objText[1][0]="No. of Employees during the month in respect of who the tax is payable is as under:";
			
			
			
			Object objTable[][]=new Object[sqlTaxData.length][5];
			double totalPTax = 0.0;
			for (int i = 0; i < objTable.length; i++) {
				objTable[i][0]=i+1;
				objTable[i][1]=sqlTaxData[i][0]+" To "+sqlTaxData[i][1];
				objTable[i][2]="0";
				if (sqlEmpData != null && sqlEmpData.length > 0) {
					for (int j = 0; j < sqlEmpData.length; j++) {
						if (String.valueOf(sqlEmpData[j][0]).equals(String.valueOf(sqlTaxData[i][2]))) {
							objTable[i][2] = sqlEmpData[j][1];
							break;
						}// end of if
					}// end of nested for
				}//end of if
				objTable[i][3]= formatter.format(Double.parseDouble(String.valueOf(sqlTaxData[i][2])));
				objTable[i][4]= formatter.format(Math.round(Integer.parseInt(""+sqlTaxData[i][2]) * Integer.parseInt(""+objTable[i][2])));
				totalPTax+=Math.round( Float.parseFloat(""+objTable[i][4]));
			}//end of for
			
			Object objTableData[][]=new Object[3][2];
			objTableData[0][0]="Total Rs.";
			objTableData[0][1]=formatter.format(Math.round(totalPTax));
			objTableData[1][0]="Simple Interest payable (if any) on the above amount at two percent per month or part thereof (Vide Section 11 of the Act)";
			objTableData[1][1]="0.00";
			objTableData[2][0]="Grand Total Rs.";
			objTableData[2][1]=formatter.format(Math.round(totalPTax+Double.parseDouble(""+objTableData[1][1])));
			
			Object objTabAmt[][]=new Object[1][5];
			objTabAmt[0][0]="Amount Paid : ";
			objTabAmt[0][1]=formatter.format(Math.round(totalPTax+Double.parseDouble(""+objTableData[1][1])));
			objTabAmt[0][2]="Under Challan No. : ";
			objTabAmt[0][3]=fmv.getChallan();
			objTabAmt[0][4]="Date : "+fmv.getDate();
			
			
			String placeQuery = "SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' ') FROM HRMS_LOCATION " +
					"LEFT JOIN HRMS_DIVISION ON HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID " +
					"WHERE HRMS_DIVISION.DIV_ID =? ";// + fmv.getDivCode(); 
			
			parameterObj = new Object[1];
			parameterObj[0] = fmv.getDivCode();
			
			Object[][] sqlPlaceData = getSqlModel().getSingleResult(placeQuery,parameterObj);
			
			Object objdecl[][]=new Object[2][5];
			objdecl[0][0]="Place : ";
			objdecl[0][1]= sqlPlaceData[0][0];
			objdecl[0][2]="";
			objdecl[0][3]="Signature : ";
			objdecl[0][4]="";
			objdecl[1][0]="Date : ";
			objdecl[1][1]=""+sqlData[0][0];
			objdecl[1][2]="";
			objdecl[1][3]="Status : ";
			objdecl[1][4]="";
			
			Object objAuth[][]=new Object[3][3];
			objAuth[0][0]="Tax Assessed";
			objAuth[0][1]="Rs. .........................";
			objAuth[0][2]="";
			
			objAuth[1][0]="Tax Paid";
			objAuth[1][1]="Rs. .........................";
			objAuth[1][2]="";
			
			objAuth[2][0]="Balance";
			objAuth[2][1]="Rs. .........................";
			objAuth[2][2]="Assessing Authority";
		
			
			
			//rg.addText("\n\n\n", 0, 0, 0);
			//rg.tableBodyNoBorder(objData,new int[] {20,20},new int[] {0,0});
			TableDataSet tableDataSet = new TableDataSet();
			tableDataSet.setData(objData);
			tableDataSet.setCellAlignment(new int[] { 0,0 });
			tableDataSet.setCellWidth(new int[] { 50,50 });
			tableDataSet.setBorder(false);
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setCellColSpan(new int[] { 3,3 });
			rg.addTableToDoc(tableDataSet);
			
			//rg.tableBodyNoBorder(objText,new int[] {20},new int[] {0});
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objText);
			tableDataSet.setCellAlignment(new int[] { 0 });
			tableDataSet.setCellWidth(new int[] { 100 });
			tableDataSet.setBorder(false);
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(tableDataSet);
			
			
			//rg.tableBody(objTable,new int[] {6,20,10,10,10},new int[] {1,1,1,1,1});
			String header[]=new String[5];
			header[0]="Sr. No.";
			header[1]="Employees who salaries or wages or both are";
			header[2]="No. of Employees";
			header[3]="Rate of Tax per Month (Rs.)";
			header[4]="Amount of Tax Deducted (Rs.)";
			
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objTable);
			tableDataSet.setCellAlignment(new int[] {1,0,2,2,2});
			tableDataSet.setCellWidth(new int[] {10,30,20,20,20});
			tableDataSet.setHeaderTable(true);
			tableDataSet.setHeader(header);
			tableDataSet.setHeaderBorderDetail(3);
			tableDataSet.setBorder(true);
			tableDataSet.setBorderDetail(3);
			//tableDataSet.setBlankRowsBelow(1);
			rg.addTableToDoc(tableDataSet);
			
			
			//rg.tableBodyNoBorder(objTableData,new int[] {30,10,10,10},new int[] {1,1,1,1});
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objTableData);
			tableDataSet.setCellAlignment(new int[] {2,2});
			tableDataSet.setCellWidth(new int[] {80,20});
			tableDataSet.setCellColSpan(new int[] {4,1});
			tableDataSet.setHeaderTable(false);
			tableDataSet.setBorder(true);
			tableDataSet.setBorderDetail(3);
			tableDataSet.setBlankRowsBelow(1);
			rg.addTableToDoc(tableDataSet);
			
			//rg.addText("\n", 0, 0, 0);
			//rg.tableBodyNoBorder(objTabAmt,new int[] {20,20,20,20,20},new int[] {1,1,1,1,1});
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objTabAmt);
			tableDataSet.setCellAlignment(new int[] {2,0,2,0,1});
			tableDataSet.setCellWidth(new int[] {20,20,20,20,20});
			tableDataSet.setBlankRowsBelow(1);
			rg.addTableToDoc(tableDataSet);
			
			//rg.addFormatedText("     I certify that all the employees who are liable to pay the tax in my employ during the period of return \n have been covered by the foregoing particulars. I also certify that the necessary revision in the amount of \n tax deductible from the salary or wages of the employees on account variation in the salary or wages \n earned by them has been made wherever necessary \n\n\n I, \n\n Solemnly declare that the above statements are true to the best of my knowledge and belief", 0, 0, 0, 0, 9);
			Object objTableData1[][]=new Object[2][1];
			objTableData1[0][0]="     I certify that all the employees who are liable to pay the tax in my employ during the period of return have been covered by the foregoing particulars. I also certify that the necessary revision in the amount of tax deductible from the salary or wages of the employees on account variation in the salary or wages earned by them has been made wherever necessary.";
			objTableData1[1][0]="I, \nSolemnly declare that the above statements are true to the best of my knowledge and belief";
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objTableData1);
			tableDataSet.setCellAlignment(new int[] {0});
			tableDataSet.setCellWidth(new int[] {100});
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(tableDataSet);
			
			//rg.addText("\n\n", 0, 0, 0);
			//rg.tableBodyNoBorder(objdecl,new int[] {20,20,20,20,20},new int[] {0,0,0,0,0});
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objdecl);
			tableDataSet.setCellAlignment(new int[] {0,0,0,0,0});
			tableDataSet.setCellWidth(new int[] {20,20,20,20,20});
			tableDataSet.setBlankRowsBelow(1);
			rg.addTableToDoc(tableDataSet);
			
			
			//rg.addTextBold("__________________________________________________________________________", 0, 0, 0);
			
			Vector blackLineVector = new Vector();
			blackLineVector.add(new BaseColor(0, 0, 0));
			blackLineVector.add(Rectangle.TOP);
			rg.addLine(blackLineVector);
			
			//rg.addTextBold("( For Official use )", 0, 2, 0);
			tableDataSet = new TableDataSet();
			tableDataSet.setData(new Object[][] {{ "( For Official use )"}});
			tableDataSet.setCellAlignment(new int[] {2});
			tableDataSet.setCellWidth(new int[] {100});			
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setBodyFontStyle(1);
			tableDataSet.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(tableDataSet);
			
			//rg.addText("\n", 0, 0, 0);
			//rg.addFormatedText("The return is accepted on verification", 0, 0, 0, 0, 9);
			tableDataSet = new TableDataSet();
			tableDataSet.setData(new Object[][] {{ "The return is accepted on verification" }});
			tableDataSet.setCellAlignment(new int[] {0});
			tableDataSet.setCellWidth(new int[] {100});			
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setBodyFontStyle(1);
			tableDataSet.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(tableDataSet);
			
			//rg.addText("\n", 0, 0, 0);
			//rg.tableBodyNoBorder(objAuth,new int[] {20,20,20,20,20},new int[] {0,0,0,0,0});
			tableDataSet = new TableDataSet();
			tableDataSet.setData(objAuth);
			tableDataSet.setCellAlignment(new int[] {0,0,2}); 
			tableDataSet.setCellWidth(new int[] {20,30,50});			
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setCellColSpan(new int[] { 1,1,4 });
			rg.addTableToDoc(tableDataSet);
			
			//rg.addText("\n", 0, 0, 0);
			//rg.addFormatedText("Note : Where the returns is not acceptable separate order of assessment should be passed", 0, 0, 0, 0, 9);
			tableDataSet = new TableDataSet();
			tableDataSet.setData(new Object[][] {{ "Note : Where the returns is not acceptable separate order of assessment should be passed" }});
			tableDataSet.setCellAlignment(new int[] {0});
			tableDataSet.setCellWidth(new int[] {100});			
			tableDataSet.setBlankRowsBelow(1);
			tableDataSet.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(tableDataSet);
			
			rg.process();			
			if(path.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		}//end of try
		catch (Exception e) {
			e.printStackTrace();
			
		}//end of catch
		
	}
	
	public boolean checkSalaryStatus()
	{
		String statusQuery = "SELECT SAL";
		return false;
	}//end of checkSalaryStatus

}
