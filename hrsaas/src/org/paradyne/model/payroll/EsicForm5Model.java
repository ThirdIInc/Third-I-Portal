package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.EsicForm5;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class EsicForm5Model extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	/*
	 * Following function is called in the action class when view report button
	 * is clicked.
	 */
	public void generateReport(EsicForm5 bean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			ReportDataSet rds = new ReportDataSet();
			String reportType = bean.getReportType();
			rds.setReportType(reportType);
			String fileName = "ESIC5_" + bean.getDivisionAbbrevation() + "_"
				+ bean.getFromYear() + "-" + bean.getToYear() + "_"
				+ Utility.getRandomNumber(1000);

			rds.setFileName(fileName);
			rds.setReportName("Esic Form -5");
			rds.setPageSize("A4");
			rds.setPageOrientation("potrait");
			rds.setReportHeaderRequired(false);
			//rds.setUserEmpId(bean.getUserEmpId());
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "EsicForm5_input.action");
			}
			TableDataSet subtitleName = new TableDataSet();
			Object headerObj[][] = new Object[1][1];
			
			headerObj[0][0]=" Regulation Form 5" +
							" \n Returns of Contribution " +
							" \n EMPLOYEE'S STATE INSURANCE CORPORATION " +
							" \n (Regulation 26)";
			
			subtitleName.setData(headerObj);
			subtitleName.setCellAlignment(new int[] {1});
			subtitleName.setCellWidth(new int[] {100});
			subtitleName.setCellColSpan(new int[] {9});
			subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName);
			
			String strQuery = "SELECT DIV_ESI_CODE FROM HRMS_DIVISION WHERE DIV_ID="+ bean.getDivId();
			Object divEsicCode[][] = getSqlModel().getSingleResult(strQuery);

			String strBranchNameQuery = "SELECT HRMS_LOCATION.LOCATION_NAME FROM HRMS_DIVISION"
					+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_DIVISION.DIV_CITY_ID)"
					+ " LEFT JOIN HRMS_LOCATION LOC ON (LOC.LOCATION_CODE=HRMS_DIVISION.DIV_CIT_CITY) WHERE"
					+ " HRMS_DIVISION.DIV_ID=" + bean.getDivId();
			Object branchName[][] = getSqlModel().getSingleResult(
					strBranchNameQuery);

			String esiCodeQuery = "SELECT HRMS_ESI.ESI_DEBIT_CODE,NVL(HRMS_ESI.ESI_EMP_PERCENTAGE,0), NVL(HRMS_ESI.ESI_COMP_PERCENTAGE,0) FROM HRMS_ESI "
				+ " WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'dd-MON-yyyy')  = (SELECT MAX(HRMS_ESI.ESI_DATE) FROM HRMS_ESI WHERE TO_CHAR(HRMS_ESI.ESI_DATE,'yyyy') <= '"
				+ bean.getFromYear() + "')";
			Object[][] esicCode = getSqlModel().getSingleResult(esiCodeQuery);
			Object[][] obj = new Object[1][2];
			TableDataSet subtitleName1 = new TableDataSet();
			obj[0][0] = "Name of Branch Office : " + checkNull(String.valueOf(branchName[0][0]));
			obj[0][1] = "Employers' Code Number : " + checkNull(String.valueOf(divEsicCode[0][0]));
			
			subtitleName1.setData(obj);
			subtitleName1.setCellAlignment(new int[] { 0, 0 });
			subtitleName1.setCellWidth(new int[] { 50, 50 });
			subtitleName1.setBorder(false);
			subtitleName1.setCellColSpan(new int[] {5,4});
			subtitleName1.setHeaderTable(false);
			subtitleName1.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName1);
			
			String strQuery1 = "SELECT DIV_NAME,DIV_ADDRESS1,DIV_ADDRESS2, DIV_ADDRESS3 FROM HRMS_DIVISION WHERE DIV_ID="+ bean.getDivId();
			Object divAdd[][] = getSqlModel().getSingleResult(strQuery1);
			Object[][] objDiv = new Object[4][2];
			TableDataSet  subtitleName2=new TableDataSet();
			objDiv[0][0] = "Name and Address of the Establishment:";
			objDiv[0][1] = checkNull(String.valueOf(divAdd[0][0]));
			objDiv[1][1] = checkNull(String.valueOf(divAdd[0][1]));
			objDiv[2][1]= checkNull(String.valueOf(divAdd[0][2])); 
			objDiv[3][1]= checkNull(String.valueOf(divAdd[0][3])); 	          
			          
			subtitleName2.setData(objDiv);
			subtitleName2.setCellAlignment(new int[] {0,0});
			subtitleName2.setCellWidth(new int[] {25,75});
			subtitleName2.setBorder(false);
			subtitleName2.setCellColSpan(new int[] {2,7});
			subtitleName2.setHeaderTable(false);
			subtitleName2.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName2);
			
			String strQueryEmployerDetails = "SELECT  DIV_EMPLOYER_NAME, DIV_EMPLOYER_DESG FROM HRMS_DIVISION WHERE DIV_ID="+ bean.getDivId();
			Object employerDetails[][] = getSqlModel().getSingleResult(strQueryEmployerDetails);
			
			Object[][] addressObj = new Object[4][2];
			TableDataSet  subtitleName3=new TableDataSet();
			addressObj[0][0]="Particulars of the Principal employer(s) ";
			addressObj[1][0]="\t (a) Name :";
			addressObj[1][1]=checkNull(String.valueOf(employerDetails[0][0]));
			addressObj[2][0]="\t (b) Designation :";
			addressObj[2][1]=checkNull(String.valueOf(employerDetails[0][1]));
			addressObj[3][0]="\t (c) Residential Address :";
			
			subtitleName3.setData(addressObj);
			subtitleName3.setCellAlignment(new int[] {0,0});
			subtitleName3.setCellWidth(new int[] { 25,75});
			subtitleName3.setBorder(false);
			subtitleName3.setCellColSpan(new int[] {2,7});
			subtitleName3.setHeaderTable(false);
			subtitleName3.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName3);
			
			Object[][] employeeObj = null;
			Object[][] totalObj = null;
			Object[][] headerNosObj = new Object[1][9];
			Object[][] finalEmployeeObj = null;
			
			employeeObj = getEmpSalDetails(bean,String.valueOf(esicCode[0][0]));
			if(employeeObj!=null && employeeObj.length>0){
				
				for (int i = 0; i < employeeObj.length; i++) {
					employeeObj[i][6] = formatter.format(Double
							.parseDouble(String.valueOf(employeeObj[i][4])));
					employeeObj[i][4] = formatter.format(Double
							.parseDouble(String.valueOf(employeeObj[i][4])));
					employeeObj[i][5] = formatter.format(Double
							.parseDouble(String.valueOf(employeeObj[i][5])));
				}
				
				totalObj = getFinalEmpObject(employeeObj);
				 
				finalEmployeeObj = new Object[employeeObj.length][];
				/*headerNosObj[0][0] = "1";
				headerNosObj[0][1] = "2";
				headerNosObj[0][2] = "3";
				headerNosObj[0][3] = "4";
				headerNosObj[0][4] = "5";
				headerNosObj[0][5] = "6";
				headerNosObj[0][6] = "7";
				headerNosObj[0][7] = "8";
				headerNosObj[0][8] = "9";
				
				finalEmployeeObj[0] = headerNosObj[0];*/
				for(int i=0; i < employeeObj.length; i++ ){
					finalEmployeeObj[i] = employeeObj[i]; 
				}
				//finalEmployeeObj[employeeObj.length+1] = totalObj[0];
			}
			
			Object[][] contributionObj = new Object[1][1];
			TableDataSet  subtitleName4=new TableDataSet();
			int fromYear = Integer.parseInt(bean.getFromYear());
			int toYear = Integer.parseInt(bean.getToYear());
			String contPeriod = bean.getContributionPeriod();
			String period ="Contribution Period from October - "+fromYear+" to March - "+toYear;
			if(contPeriod.equals("A")){
				period = "Contribution Period from April - "+fromYear+" to September - "+fromYear;
			}
			contributionObj[0][0] = ""+period;
			
			subtitleName4.setData(contributionObj);
			subtitleName4.setCellAlignment(new int[] {0 });
			subtitleName4.setCellWidth(new int[] { 10});
			subtitleName4.setBorder(false);
			subtitleName4.setCellColSpan(new int[] {9});
			subtitleName4.setHeaderTable(false);
			subtitleName4.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName4);
			
			
			// rg.addText("\n", 0,0,0);
			String message1 = "\t\t\t\t\t\t\t\t  I furnish below the details of the Employer's and Employee's " +
					"share of contribution in respect of the under mentioned insured persons. I hereby declare " +
					"that the return includes each and every employee, employed directly or through an immediate" +
					" employer or in connection with the work of factory/establishment or any work " +
					"connected with administration of the factory/establishment or purchase of raw "	+ 
					"materials sale or distribution of finished products etc. to whom the ESI Act,1948 " +
					"applies in contribution period to which this return relates and that the contribution" +
					" in respect of employer's and employee's share have been correctly paid in accordance" +
					" with provisions of the Act and Regulations.";
			Object[][] matter = new Object[1][1];
			TableDataSet  subtitleName5=new TableDataSet();
			matter[0][0] = message1;
			subtitleName5.setData(matter);
			subtitleName5.setCellAlignment(new int[] {0 });
			subtitleName5.setCellWidth(new int[] { 10});
			subtitleName5.setBorder(false);
			subtitleName5.setCellColSpan(new int[] {9});
			subtitleName5.setHeaderTable(false);
			subtitleName5.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName5);
		
			double empShare = 0;
			double employerShare = 0;
			double totalEsicWages = 0;
			if(employeeObj!=null && employeeObj.length>0){
			for (int i = 0; i < employeeObj.length; i++) {
				employeeObj[i][0]=""+(i+1);
				empShare += Double.parseDouble(String.valueOf(employeeObj[i][5]));
				//totalWages += Double.parseDouble(String.valueOf(employeeObj[i][4]));
			}
			}
			totalEsicWages=empShare*100/Double.parseDouble(String.valueOf(esicCode[0][1]));				// esi employee percentage
			employerShare = Double.parseDouble(String.valueOf(esicCode[0][2]))*totalEsicWages/100;		//esi company percentage
			Object[][] empShareObj=new Object[3][2];
			TableDataSet  subtitleName6=new TableDataSet();
			empShareObj[0][0] = "Employee's share Rs."+ checkNull(String.valueOf(formatter.format(empShare)));
			empShareObj[1][0] = "Employer's share Rs."+formatter.format(employerShare) ;
			empShareObj[2][0] = "Total Contribution Rs."+(formatter.format(employerShare+empShare)) ;
			
			subtitleName6.setData(empShareObj);
			subtitleName6.setCellAlignment(new int[] { 1});
			subtitleName6.setCellWidth(new int[] { 100});
			subtitleName6.setBorder(false);
			subtitleName6.setCellColSpan(new int[] {9});
			subtitleName6.setHeaderTable(false);
			subtitleName6.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName6);
			
		
			
			Object[][] challanDtlHeader  = new Object[1][1];
			challanDtlHeader[0][0]="Details of Challans :- ";
			TableDataSet  subtitleNameOfChallanDtl=new TableDataSet();
			subtitleNameOfChallanDtl.setData(challanDtlHeader);
			subtitleNameOfChallanDtl.setCellAlignment(new int[] {0});
			subtitleNameOfChallanDtl.setCellWidth(new int[] {20});
		    subtitleNameOfChallanDtl.setBorder(false);
		    subtitleNameOfChallanDtl.setCellColSpan(new int[] {9});
			subtitleNameOfChallanDtl.setHeaderTable(false);
			subtitleNameOfChallanDtl.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleNameOfChallanDtl);
			
			
			String strChallanAmtQuery = "SELECT  (TO_CHAR(TO_DATE(CHALLAN_MONTH,'MM'),'MON')||','||CHALLAN_YEAR),TO_CHAR(CHALLAN_PAYDATE,'DD/MM/YYYY'),NVL(CHALLAN_AMOUNT,0) FROM HRMS_ESI_CHALLAN WHERE";
			if (!(bean.getDivId().equals("") || bean.getDivId().equals("null"))) {
				strChallanAmtQuery += " CHALLAN_DIVISION=" + bean.getDivId();
			}
			if (contPeriod.equals("A")){
				strChallanAmtQuery += "  AND CHALLAN_MONTH in (4,5,6,7,8,9) AND CHALLAN_YEAR="+bean.getFromYear();
							
			}else {
				strChallanAmtQuery += "  AND ((CHALLAN_MONTH IN (10,11,12) AND CHALLAN_YEAR="+bean.getFromYear()+") OR "
				+" (CHALLAN_MONTH IN (1,2,3) AND CHALLAN_YEAR="+bean.getToYear()+"))";
			}

			Object ChallanAmt[][] = getSqlModel().getSingleResult(strChallanAmtQuery);
			Object[][] tableData = new Object[ChallanAmt.length + 1][5];
			double total = 0;
			for (int i = 0; i <= ChallanAmt.length; i++) {
				if (i == 0) {
					tableData[0][0] = "Sr.No.";
					tableData[0][1] = "Month";
					tableData[0][2] = "Date of Challan";
					tableData[0][3] = "Amount";
					tableData[0][4] = "Name of Bank and Branch";
				} else {

					tableData[i][0] = String.valueOf(i);
					tableData[i][1] = String.valueOf(ChallanAmt[i - 1][0]);
					tableData[i][2] = String.valueOf(ChallanAmt[i - 1][1]);
					tableData[i][3] =  formatter.format(Double.parseDouble(String.valueOf(ChallanAmt[i - 1][2])));
					double value=(String.valueOf(tableData[i][3]) != null && !String
							   .valueOf(tableData[i][3]).equals("null")) ? 
									   Double.parseDouble(String.valueOf(tableData[i][3])): 0;
					total = total+ value;
					System.out.println("TOTAL------"+total);
					tableData[i][4] = "";

				}

			}
			
			
			
			TableDataSet  subtitleName7=new TableDataSet();
			subtitleName7.setData(tableData);
			subtitleName7.setCellAlignment(new int[] {1, 0, 0, 2, 0});
			subtitleName7.setCellWidth(new int[] {10, 20, 20, 20, 20  });
			subtitleName7.setBorder(true);
			subtitleName7.setBorderDetail(3);
			subtitleName7.setHeaderTable(false);
			rg.addTableToDoc(subtitleName7);
			
			// rg.tableBody(tableData, tableCellwidth, tableAllignment);
			
			Object[][] totalTableData = new Object[1][5];
			totalTableData[0][0] = " ";
			totalTableData[0][1] = " ";
			totalTableData[0][2] = "Total amount paid : Rs ";
			totalTableData[0][3] = formatter.format(total);
			totalTableData[0][4] = " ";
			
			TableDataSet  subtitleNameTotal = new TableDataSet();
			subtitleNameTotal.setData(totalTableData);
			subtitleNameTotal.setCellAlignment(new int[] {1, 0, 0, 2, 0});
			subtitleNameTotal.setCellWidth(new int[] {10, 20, 20, 20, 20  });
			subtitleNameTotal.setBorder(true);
			subtitleNameTotal.setBorderDetail(3);
			subtitleNameTotal.setBodyFontStyle(1);
			subtitleNameTotal.setHeaderTable(false);
			subtitleNameTotal.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleNameTotal);
			
			
			
			Object[][] signObj=new Object[2][1];
			TableDataSet  subtitleName8=new TableDataSet();
			signObj[0][0]="Place :";
			//signObj[0][2]="Total amount paid : Rs" + String.valueOf(total);
			signObj[1][0]="Date :";
			
			subtitleName8.setData(signObj);
			subtitleName8.setCellAlignment(new int[] {0});
			subtitleName8.setCellWidth(new int[] {100} );
			subtitleName8.setBorder(false);
			subtitleName8.setCellColSpan(new int[] {9});
			subtitleName8.setHeaderTable(false);
			subtitleName8.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName8);
			
			Object[][] signObj1 = new Object[1][1];
			signObj1[0][0]="Signature and Designation of the Employer \n (with Rubber Stamp)";
			TableDataSet  subtitleName13 =new TableDataSet();
			subtitleName13.setData(signObj1);
			subtitleName13.setCellAlignment(new int[] {2});
			subtitleName13.setCellWidth(new int[] {100} );
			subtitleName13.setBorder(false);
			subtitleName13.setCellColSpan(new int[] {9});
			subtitleName13.setBodyFontStyle(1);
			subtitleName13.setHeaderTable(false);
			subtitleName13.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName13);
			
			// rg.pageBreak();
			
			//rg.PAGE_BREAK=1;
			Object[][] instList=new Object[2][1]; 
			// rg.addTextBold("Important Instructions :", 0, 0, 0);
			
			String message2 = "(i) If any I.P. is appointed for the first time and/or leaves service during the contribution period, indicate 'A.............(date)'"
					+ " and/or 'L..............(Date)'"
					+ "\n(ii) Please indicate Insurance Nos. in ascending order."
					+ "\n(iii) Figures in Columns 4,5 & 6 shall be in respect of wage periods ended during the contribution period."
					+ "\n(iv) Invariably strike totals of Columns of 4,5 and 6 of the Return"
					+ "\n(v). No over-writing shall be made. Any corrections should be signed by the employer"
					+ "\n(vi). Every page of this return should bear full signature & rubber stamp of the employer."
					+ "\n(vii). 'Daily Wages' in Col.7 of the return shall be calculated by dividing figures in Col. 5 by figures in Col. 4 to two decimal Places.";

			instList[0][0] = "Important Instructions : Information to be given in 'Remarks Column (No. 9)'";
			instList[1][0] = message2;
			TableDataSet  subtitleName9=new TableDataSet();
			subtitleName9.setData(instList);
			subtitleName9.setCellAlignment(new int[] {0});
			subtitleName9.setCellWidth(new int[] {100});
			subtitleName9.setCellColSpan(new int[] {9});
			subtitleName9.setBorder(false);
			subtitleName9.setHeaderTable(false);
			subtitleName9.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName9);
			
		

			Object CPMess[][] = new Object[2][1];
			CPMess[0][0] = "For *CP ending 31st March, due date is 12th May.";
			CPMess[1][0] = "For CP ending 30th September, due date is 11th November.";
			TableDataSet  subtitleName10=new TableDataSet();
			subtitleName10.setData(CPMess);
			subtitleName10.setCellAlignment(new int[] {0});
			subtitleName10.setCellWidth(new int[] {100});
			subtitleName10.setCellColSpan(new int[] {9});
			subtitleName10.setBorder(false);
			subtitleName10.setHeaderTable(false);
			subtitleName10.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName10);
			
			rg.addProperty(rg.PAGE_BREAK);
			Object[][] obj1=new Object[1][1];
			TableDataSet  subtitleName11=new TableDataSet();
			obj1[0][0]="EMPLOYEE'S STATE INSURANCE CORPORATION";
			subtitleName11.setData(obj1);
			subtitleName11.setCellAlignment(new int[] {1});
			subtitleName11.setCellWidth(new int[] {100});
			subtitleName11.setBodyFontStyle(1);
			subtitleName11.setCellColSpan(new int[] {9});
			subtitleName11.setBorder(false);
			subtitleName11.setHeaderTable(false);
			subtitleName11.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName11);
			

			Object empInsuranceCoDetails[][] = new Object[2][2];
			TableDataSet  subtitleName12 = new TableDataSet();
			empInsuranceCoDetails[0][0] = "Employer's Name and Address : " + checkNull(String.valueOf(employerDetails[0][0]));
			empInsuranceCoDetails[0][1] = "";
			empInsuranceCoDetails[1][0] = "Employer's Code No : "+ String.valueOf(divEsicCode[0][0]);
			empInsuranceCoDetails[1][1] = period;
			//empInsuranceCoDetails[1][2] = "to :"+checkNull(Utility.month(Integer.parseInt(bean.getToMonth()))+ " " + bean.getToYear());
			
			subtitleName12.setData(empInsuranceCoDetails);
			subtitleName12.setCellAlignment(new int[] {0, 0});
			subtitleName12.setCellWidth(new int[] {60, 40});
			subtitleName12.setCellColSpan(new int[] {5,4});
			subtitleName12.setBorder(false);
			subtitleName12.setHeaderTable(false);
			subtitleName12.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName12);
			//rg.tableBodyNoBorder(empInsuranceCoDetails,new int[] { 20, 10, 10 }, new int[] { 0, 0, 0 });
			
			if(finalEmployeeObj!=null && finalEmployeeObj.length>0){
				String header1[][] = new String[2][9];
				header1[0][0] = "Sr. No"; 
				header1[0][1] = "Insurance Number"; 
				header1[0][2] = "Name of Insured Person";
				header1[0][3] = "No of Days for which Wages Paid";
				header1[0][4] = "Total Amount of Wages Paid";
				header1[0][5] = "Employees Contribution Deducted";
				header1[0][6] = "Daily Wages";
				header1[0][7] = "Whether still continues working"; 
				header1[0][8] = "Remarks";
			
				header1[1][0] = "1";
				header1[1][1] = "2";
				header1[1][2] = "3";
				header1[1][3] = "4";
				header1[1][4] = "5";
				header1[1][5] = "6";
				header1[1][6] = "7";
				header1[1][7] = "8";
				header1[1][8] = "9";
			
			
			int[] width1 = new int[]{10, 10, 15, 10, 10, 10, 10, 10, 15};
			int[] alignment1 = new int[]{1, 1, 0, 2, 2, 2, 2, 0, 0};	
			TableDataSet empDataObj1 = new TableDataSet();
			empDataObj1.setHeaderData(header1);
			empDataObj1.setData(finalEmployeeObj);
			empDataObj1.setCellAlignment(alignment1);
			empDataObj1.setCellWidth(width1);
			empDataObj1.setBorder(true);
			empDataObj1.setBorderDetail(3);
			empDataObj1.setHeaderTable(true);
			empDataObj1.setHeaderBorderDetail(3);
			empDataObj1.setHeaderBGColor(new BaseColor(225, 225, 225));
			//empDataObj1.setColumnSum(new int[] {3, 4, 5, 6});
			//empDataObj1.setTotalCol(true);
			rg.addTableToDoc(empDataObj1);
			
			TableDataSet empTotalDataObj = new TableDataSet();
			empTotalDataObj.setData(totalObj);
			empTotalDataObj.setCellAlignment(alignment1);
			empTotalDataObj.setCellWidth(width1);
			empTotalDataObj.setBorder(true);
			empTotalDataObj.setBlankRowsBelow(1);
			empTotalDataObj.setBorderDetail(3);
			empTotalDataObj.setBodyFontStyle(1);
			rg.addTableToDoc(empTotalDataObj);
			
			}else{
				
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },
						{ "No records available for selected criteria" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			
			}
			
			Object empMess[][] = new Object[1][1];
			empMess[0][0] = "*Date of appointment and leaving the job may be given in remarks column.";
			
			TableDataSet  subtitleName14 = new TableDataSet();
			subtitleName14.setData(empMess);
			subtitleName14.setCellAlignment(new int[] {0 });
			subtitleName14.setCellWidth(new int[] {100});
			subtitleName14.setBorder(false);
			subtitleName14.setCellColSpan(new int[] {9});
			subtitleName14.setHeaderTable(false);
			subtitleName14.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName14);
			
			Object empMess1[][] = new Object[1][1];
			empMess1[0][0] = "Signature of the Employer";
			
			TableDataSet  subtitleName17 = new TableDataSet();
			subtitleName17.setData(empMess1);
			subtitleName17.setCellAlignment(new int[] {2 });
			subtitleName17.setCellWidth(new int[] {100});
			subtitleName17.setBorder(false);
			subtitleName17.setBodyFontStyle(1);
			subtitleName17.setCellColSpan(new int[] {9});
			subtitleName17.setHeaderTable(false);
			subtitleName17.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName17);
			
			//rg.tableBodyNoBorder(EmpMess, new int[] { 10, 10, 10 }, new int[] {0, 0, 0 });
			//rg.addText("\n", 0, 0, 0);
			TableDataSet  subtitleName15=new TableDataSet();
			Object obj2[][] = new Object[1][1];
			
			obj2[0][0]="(FOR OFFICIAL USE)";
			subtitleName15.setData(obj2);
			subtitleName15.setCellAlignment(new int[] {1});
			subtitleName15.setCellWidth(new int[] {100});
			subtitleName15.setBorder(false);
			subtitleName15.setHeaderTable(false);
			subtitleName15.setCellColSpan(new int[] {9});
			subtitleName15.setBodyFontStyle(1);
			subtitleName15.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName15);
			
			//rg.addText("(FOR OFFICIAL USE)", 0, 1, 0);
			
			Object EmpMess1[][] = new Object[3][2];
			EmpMess1[0][0] = "1.";
			EmpMess1[0][1] = "Entitlement position marked";

			EmpMess1[1][0] = "2.";
			EmpMess1[1][1] = "Total of col.5 of return checked and Found correct/incorrect amount is indicated";

			EmpMess1[2][0] = "3.";
			EmpMess1[2][1] = "Checked the amount of Employer's/Employee's contribution paid which is in order / observation memo enclosed. ";
			TableDataSet EmpMessTable=new TableDataSet();
			
			EmpMessTable.setData(EmpMess1);
			EmpMessTable.setCellAlignment(new int[] {1,0 });
			EmpMessTable.setCellWidth(new int[] {2,50});
			EmpMessTable.setBorder(false);
			EmpMessTable.setCellColSpan(new int[] {1,8});
			EmpMessTable.setHeaderTable(false);
			EmpMessTable.setBlankRowsBelow(3);
			rg.addTableToDoc(EmpMessTable);
			Object signMsg[][] = new Object[3][3];
			signMsg[0][0] = "";
			signMsg[0][1] = "";
			signMsg[0][2] = "Counter Signature.........................";

			signMsg[1][0] = "";
			signMsg[1][1] = "";
			signMsg[1][2] = "";
			
			signMsg[2][0] = "U.D.C.";
			signMsg[2][1] = "Head Clerk";
			signMsg[2][2] = "Branch Officer";
			
			TableDataSet subtitleName16=new TableDataSet();
			
			subtitleName16.setData(signMsg);
			subtitleName16.setCellAlignment(new int[] {0,1,2 });
			subtitleName16.setCellWidth(new int[] {30,30,40});
			subtitleName16.setBorder(false);
			subtitleName16.setHeaderTable(false);
			subtitleName16.setCellColSpan(new int[] {3,3,3});
			subtitleName16.setBodyFontStyle(1);
			subtitleName16.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName16);
			
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	private Object[][] getEmpSalDetails(EsicForm5 bean,String esicCode) {
		String query = "";
		String wagesQuery="";
		String ledgerCodes = "";
		HashMap wagesMap=null;
		
		Object [][]empQuarter1Obj=null;
		Object [][]empQuarter2Obj=null;
		Object [][]wagesQuarter1Obj=null;
		Object [][]wagesQuarter2Obj=null;
		Object[][] empObject=null;
		Object [][]finalEmployeeObj=null;
		int year = Integer.parseInt(bean.getFromYear());
		String contPeriod=bean.getContributionPeriod();
		String months = "";
		String [] months1 = new String[]{"10, 11, 12", " 1, 2, 3", "4, 5, 6, 7, 8, 9"};
		if(!contPeriod.equals("A")){
			for (int i = 0; i < 2; i++) {
				year+=i;
				months=String.valueOf(months1[i]);
				
				ledgerCodes = getLedgerCodes(year, months, bean.getDivId());
				
				query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
					+ " SUM(HRMS_SALARY_"+year+".SAL_DAYS),0," 
					+ " SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT) AS CONTRIBUTION, 0,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','YES','NO'), ''" 
					+ " FROM   HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID ) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)" 
					+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
					+ " WHERE HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT >0 AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+esicCode+" AND HRMS_SAL_DEBITS_"+year
					+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0 "
					+ " GROUP BY HRMS_EMP_OFFC.EMP_STATUS,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
					+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID ";
				
				
				/*query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
					+ " SUM(SAL_DAYS),0," 
					+ " SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT) AS CONTRIBUTION, 0,DECODE(EMP_STATUS,'S','YES','NO'), ''" 
					+ " FROM   HRMS_SAL_DEBITS_"+year
					+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_"+year+".SAL_LEDGER_CODE " 
					+ " AND HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID AND SAL_DEBIT_CODE="+esicCode+" AND HRMS_SAL_DEBITS_"+year
					+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0)" 
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID ) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" 
					+ " WHERE  SAL_AMOUNT >0 GROUP BY EMP_STATUS,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
					+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID ";
				*/
			
				wagesQuery = " SELECT HRMS_SAL_CREDITS_"+year+".EMP_ID,'','','', SUM(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT),'',0,'','' FROM HRMS_SAL_CREDITS_"+year
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y')" 
					+ " WHERE HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE IN("+ledgerCodes+") GROUP BY HRMS_SAL_CREDITS_"+year+".EMP_ID";
				
				
				
				if(i>0){
					empQuarter2Obj = getSqlModel().getSingleResult(query);
					wagesQuarter2Obj = getSqlModel().getSingleResult(wagesQuery);
					finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, empQuarter2Obj, 0, new int[]{3,4,5,6}, 9);
					finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, wagesQuarter2Obj, 0, new int[]{4}, 9);
				}else{	
					empQuarter1Obj = getSqlModel().getSingleResult(query);
					wagesQuarter1Obj = getSqlModel().getSingleResult(wagesQuery);
					finalEmployeeObj = Utility.consolidateArrearsObject(empQuarter1Obj, null, 0, new int[]{3,4,5,6}, 9);
					finalEmployeeObj = Utility.consolidateArrearsObject(finalEmployeeObj, wagesQuarter1Obj, 0, new int[]{4}, 9);
				}
			}
			finalEmployeeObj =Utility.removeNullRows(finalEmployeeObj, 2);
			if(finalEmployeeObj!=null && finalEmployeeObj.length>0){
				
				for (int i = 0; i < finalEmployeeObj.length; i++) {
					finalEmployeeObj[i][3] = String.valueOf(finalEmployeeObj[i][3]);						
					finalEmployeeObj[i][6] = formatter.format(Double.parseDouble(String.valueOf(finalEmployeeObj[i][4]))/Double.parseDouble(String.valueOf(finalEmployeeObj[i][3]))); //put daily wages here
					finalEmployeeObj[i][4] = formatter.format(Double.parseDouble(String.valueOf(finalEmployeeObj[i][4])));
					finalEmployeeObj[i][5] = formatter.format(Double.parseDouble(String.valueOf(finalEmployeeObj[i][5])));
					finalEmployeeObj[i][0] = ""+i+1;
				}
			}
		}else{

			months=String.valueOf(months1[2]);
			ledgerCodes = getLedgerCodes(year, months, bean.getDivId());
			
			query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+ " SUM(HRMS_SALARY_"+year+".SAL_DAYS),0," 
				+ " SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT) AS CONTRIBUTION, 0,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','YES','NO'), ''" 
				+ " FROM   HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID ) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)" 
				+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
				+ " WHERE HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT >0 AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE="+esicCode+" AND HRMS_SAL_DEBITS_"+year
				+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0 "
				+ " GROUP BY HRMS_EMP_OFFC.EMP_STATUS,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
				+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID ";
			
			/*query = "SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ') AS ESIC_NO, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+ " SUM(SAL_DAYS),' '," 
				+ " SUM(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT) AS CONTRIBUTION, 0, DECODE(EMP_STATUS,'S','YES','NO'),' '" 
				+ " FROM   HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_"+year+".SAL_LEDGER_CODE " 
				+ " AND HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID AND SAL_DEBIT_CODE="+esicCode+" AND HRMS_SAL_DEBITS_"+year
				+ " .SAL_LEDGER_CODE IN ("+ledgerCodes+") AND HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT>0)" 
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID ) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" 
				+ " WHERE  SAL_AMOUNT >0 GROUP BY EMP_STATUS,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID," 
				+ " HRMS_SALARY_MISC.SAL_ESCINUMBER, HRMS_SAL_DEBITS_"+year+".EMP_ID";
		*/
			wagesQuery = " SELECT HRMS_SAL_CREDITS_"+year+".EMP_ID, SUM(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT) FROM HRMS_SAL_CREDITS_"+year
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y')" 
				+ " WHERE HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE IN("+ledgerCodes+") GROUP BY HRMS_SAL_CREDITS_"+year+".EMP_ID";
			
			empObject = getSqlModel().getSingleResult(query);
			
			wagesMap = getSqlModel().getSingleResultMap(wagesQuery,0, 2 );
			
			if(empObject!=null && empObject.length >0){
				Object [][] finalEmpObject=new Object[empObject.length][empObject[0].length];
				for (int i = 0; i < empObject.length; i++) {
					for (int j = 1; j < empObject[0].length; j++) {
						finalEmpObject[i][0] = i+1;
						Object[][] empWage=null;
						if(j==4){
							empWage = (Object[][]) wagesMap.get(String.valueOf(empObject[i][0]));
							finalEmpObject[i][j] = formatter.format(Double.parseDouble(String.valueOf(empWage[0][1])));
						}else if(j==6){
							empWage = (Object[][]) wagesMap.get(String.valueOf(empObject[i][0]));
							finalEmpObject[i][j] = formatter.format(Double.parseDouble(String.valueOf(empWage[0][1]))/Double.parseDouble(String.valueOf(empObject[i][3]))); //put daily wages here
						}else{
							finalEmpObject[i][j] = String.valueOf(empObject[i][j]);
						}
						
					}
				}
				//finalEmpObject=Utility.removeColumns(finalEmpObject, new int []{0});
				return finalEmpObject;
			}else{
				return null;
			}
		
		}
		//finalEmployeeObj=Utility.removeColumns(finalEmployeeObj, new int []{0});
		return finalEmployeeObj;
	}

	public Object[][] getESICode(String debitEffectDate) {
		try {
			String esiSql = "SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE  FROM HRMS_ESI "
					+ " WHERE ESI_DATE = (SELECT MAX(ESI_DATE) FROM HRMS_ESI WHERE ESI_DATE <= TO_DATE('"
					+ debitEffectDate + "','MM-YYYY')) ";

			Object[][] esiData = getSqlModel().getSingleResult(esiSql);
			//bean.setEsiData(esiData);
			return esiData;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
	
	public String getLedgerCodes(int year, String months, String divisionId){
		String ledgerCode = "";
		
		try {
			String ledgerCodeQuery = " SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_YEAR ="
					+ year + " AND HRMS_SALARY_LEDGER.LEDGER_MONTH IN ("+ months+ ") AND HRMS_SALARY_LEDGER.LEDGER_DIVISION=" + divisionId;
			Object[][] ledgerCodeObject = getSqlModel().getSingleResult(ledgerCodeQuery);
			
			if (ledgerCodeObject != null && ledgerCodeObject.length > 0) {
				for (int i = 0; i < ledgerCodeObject.length; i++) {
					ledgerCode += ledgerCodeObject[i][0] + ",";
				}
				if (ledgerCode.length() > 1) {
					ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ledgerCode;
	}
	
	public Object[][] getFinalEmpObject(Object[][] employeeObj){
		Object[][] finalEmployeeObj = new Object[1][9];
		double totalDays = 0.0;
		double totalWages = 0.0;
		double totalEmpContri = 0.0;
		double totalDailyWages = 0.0;
		
		for (int i = 0; i < employeeObj.length; i++) {
			totalDays = totalDays + Double.parseDouble(String.valueOf(employeeObj[i][3]));
			totalWages = totalWages + Double.parseDouble(String.valueOf(employeeObj[i][4]));
			totalEmpContri = totalEmpContri + Double.parseDouble(String.valueOf(employeeObj[i][5]));
			totalDailyWages = totalDailyWages + Double.parseDouble(String.valueOf(employeeObj[i][6]));
		}
		finalEmployeeObj[0][2] = "Total";
		finalEmployeeObj[0][3] = formatter.format(totalDays);
		finalEmployeeObj[0][4] = formatter.format(totalWages);
		finalEmployeeObj[0][5] = formatter.format(totalEmpContri);
		finalEmployeeObj[0][6] = formatter.format(totalDailyWages);
		
		return finalEmployeeObj;
	}
	
}
