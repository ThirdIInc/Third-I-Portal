
package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.Form6EsicReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * @author AA0554
 * Date 15/10/2008
 * Form6EsicReportModel class to write business logic to generate the Form 6 ESIC report for the 
 * selected Division & year
 * 
 */
public class Form6EsicReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * @Method getReport
	 * this method fetches the wages, salary days & ESIC of the employees of the selected division
	 *  for selected financial year and displays it in the PDF format.
	 * @param bean
	 * @param response
	 *  
	 */
	
	
	public void getReport(Form6EsicReport bean,HttpServletRequest request, HttpServletResponse response, String reportPath){
		
		org.paradyne.lib.ireportV2.ReportGenerator rg=null;
		
		ReportDataSet rds = new ReportDataSet();
		String reportType = bean.getReportType();
		rds.setReportType(reportType);
		String fileName = "FORM No -6(ESIC)For "+bean.getDivisionName()+Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportHeaderRequired(false);
		rds.setReportName("FORM No -6(ESIC)For "+bean.getDivisionName());
		rds.setUserEmpId(bean.getUserEmpId());
		if(reportType.equals("Pdf")){
			rds.setPageOrientation("landscape");
		}
		if(reportPath.equals("")){
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
			//request.setAttribute("reportPath", reportPath);
			request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
			request.setAttribute("action", "Form6Report_input.action");
			request.setAttribute("fileName", fileName + "." + reportType);
			
		}
		
		String header = "FORM No -5\n (Return of Contribution) Regulation 26";					// Report Header
		
		TableDataSet subtitleName = new TableDataSet();
		Object obj[][] = new Object[2][1];
		
		
		obj[0][0]= String.valueOf("FORM No -5\n (Return of Contribution) Regulation 26");
		obj[1][0]=	String.valueOf("Employee's State Insurance Corporation");
		
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setBodyFontStyle(1);
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setCellColSpan(new int[]{9});
		//subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 12, Font.NORMAL,new BaseColor(0, 0, 0));
		subtitleName.setBorder(false);
		subtitleName.setHeaderTable(false);
		
		
		//subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);
		
		
		//rg.addFormatedText(header, 1, 0, 1, 0);
		//rg.addFormatedTextForPurchase("Employee's State Insurance Corporation", 1, 0, 1, 0);
		
		//rg.setFName("FORM No -6(ESIC)For "+bean.getDivisionName());
		
		Object localOfficeName [][] = new Object [1][2];
		Object divisionAddress [][] = new Object [1][2];
		Object principalEmployer [][] = new Object [2][2];
		Object principalEmp [][] = new Object [2][2];
		Object paraEmployer [][] = new Object [3][1];
		Object paraEmp [][] = new Object [3][1];
		Object challanData [][]= null;
		String challanQuery ="";

		Object tableData [][] = null; 

		String contrPeriodFrom ="";
		String contrPeriodTo ="";
		
		String queryForDebitCode ="SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI "
			+" WHERE SYSDATE > ESI_DATE  AND ESI_DATE = (SELECT MAX(ESI_DATE) "
			+" FROM HRMS_ESI WHERE ESI_DATE < SYSDATE)";
		Object debitCodeData [][] = getSqlModel().getSingleResult(queryForDebitCode);
		
		if(bean.getContributionPeriod().equals("A")){
			contrPeriodFrom ="April  "+bean.getFromYear();
			
			contrPeriodTo ="September  "+bean.getFromYear();
			
			challanQuery ="SELECT TO_CHAR(CHALLAN_PAYDATE,'DD-MM-YYYY'), NVL(CHALLAN_AMOUNT,0) FROM HRMS_ESI_CHALLAN WHERE CHALLAN_MONTH BETWEEN 4 AND 9 AND CHALLAN_YEAR ="+bean.getFromYear()
							+" AND CHALLAN_DIVISION="+bean.getDivisionCode();
			
		}else {
			contrPeriodFrom ="October  "+bean.getFromYear();
			
			contrPeriodTo ="March  "+((bean.getToYear()));
			
			challanQuery ="SELECT TO_CHAR(CHALLAN_PAYDATE,'DD-MM-YYYY'), NVL(CHALLAN_AMOUNT,0) FROM HRMS_ESI_CHALLAN WHERE (CHALLAN_MONTH BETWEEN 10 AND 12 AND CHALLAN_YEAR ="+bean.getFromYear()+")"
							+" OR (CHALLAN_MONTH BETWEEN 1 AND 3 AND CHALLAN_YEAR ="+bean.getToYear()+")"
							+" AND CHALLAN_DIVISION="+bean.getDivisionCode();
		}
		
		challanData = getSqlModel().getSingleResult(challanQuery);
		/*
		 * Division Address Object
		 * 
		 */
		Object divAddress[][]=getSqlModel().getSingleResult("SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '), NVL(DIV_ADDRESS2,' '), NVL(DIV_ADDRESS3,' '),LOCATION_NAME FROM HRMS_DIVISION " 
				+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE= HRMS_DIVISION.DIV_CITY_ID)"
				+" WHERE DIV_ID="+bean.getDivisionCode());
		
		
		localOfficeName [0][0] = "\nName Of the Local Office :"+String.valueOf(divAddress[0][0])+"\n"+String.valueOf(divAddress[0][4]);

		localOfficeName [0][1] = "\nEmployers Code No.................";

		divisionAddress [0][0]   = String.valueOf(divAddress[0][0])+"\n"+String.valueOf(divAddress[0][1])+"\n"+String.valueOf(divAddress[0][2])+"\n"+String.valueOf(divAddress[0][3])+"\n" ;

		divisionAddress [0][1]   ="\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tContribution Period\n From :\t\t\t\t\t\t\t"+contrPeriodFrom+"\n To   :\t\t\t\t\t\t\t\t "+contrPeriodTo;

		principalEmp [0][0] ="a) Name :" ;
		principalEmp [0][1] ="b) Designation :" ;
		principalEmp [1][0] ="b) Residential Address :" ;
		principalEmp [1][1] ="";

		int cellwidthForLocal []= {60,40};
		int allignForLocal[]     = {0,0};

		int cellwidthForParaEmployer []= {100};
		int allignForParaEmployer[]     = {0};

		int cellwidthForPrincipalEmployer [] = {40,60};
		int allignForPrincipalEmployer[]     = {0,0};

		int cellwidthForChallan [] = {10, 30, 30, 30};
		int allignForChallan[]     = {2, 0, 0, 2};
		TableDataSet localofficedesc = new TableDataSet();
		localofficedesc.setData(localOfficeName);
		localofficedesc.setCellAlignment(allignForLocal);
		localofficedesc.setCellWidth(cellwidthForLocal);
		localofficedesc.setCellColSpan(new int[]{4,5});
		//localofficedesc.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
		localofficedesc.setBlankRowsBelow(1);
		//localofficedesc.setBorder(false);
		//localofficedesc.setHeaderTable(false);
		rg.addTableToDoc(localofficedesc);
		
		TableDataSet divisonaddress = new TableDataSet();
		divisonaddress.setData(divisionAddress);
		divisonaddress.setCellAlignment(allignForLocal);
		divisonaddress.setCellWidth(cellwidthForLocal);				
		//divisonaddress.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
		divisonaddress.setBorder(true);
		divisonaddress.setBorderDetail(3);
		divisonaddress.setCellColSpan(new int[]{4,5});
		divisonaddress.setBlankRowsBelow(1);
		//localofficedesc.setHeaderTable(false);
		rg.addTableToDoc(divisonaddress);
		

		TableDataSet leftheading = new TableDataSet();
		Object lefobj[][] = new Object[1][1];
		
		
		lefobj[0][0]= String.valueOf("PARTICULARS OF THE PRINCIIPAL EMPLOYERS:-");
		
		
		leftheading.setData(lefobj);
		leftheading.setCellAlignment(new int[] { 0 });
		leftheading.setCellWidth(new int[] { 100 });
		leftheading.setCellColSpan(new int[]{9});
		//leftheading.setBodyFontDetails(Font.FontFamily.HELVETICA, 15, Font.NORMAL,new BaseColor(0, 0, 0));
		//leftheading.setBorder(false);
		//leftheading.setHeaderTable(false);
		leftheading.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(leftheading);
		
		TableDataSet principalEmployr = new TableDataSet();
		principalEmployr.setData(principalEmp);
		principalEmployr.setCellAlignment(allignForPrincipalEmployer);
		principalEmployr.setCellWidth(cellwidthForPrincipalEmployer);
		principalEmployr.setCellColSpan(new int[]{4,5});
		//principalEmployr.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
		principalEmployr.setBlankRowsBelow(1);
		//localofficedesc.setBorder(false);
		//localofficedesc.setHeaderTable(false);
		rg.addTableToDoc(principalEmployr);

	/*
	 * Adding the local office Object & Division Address
	 * 
	 */
		//rg.tableBodyNoBorder(localOfficeName, cellwidthForLocal, allignForLocal);

		//rg.tableBody(divisionAddress, cellwidthForLocal, allignForLocal);

		//rg.addFormatedText("\n", 1, 0, 0, 0);
		//rg.addTextPromo("PARTICULARS OF THE PRINCIIPAL EMPLOYERS:-", 0, Element.ALIGN_LEFT, 0);
		
		//rg.addFormatedText("\n", 1, 0, 0, 0);
		//rg.tableBodyNoBorder(principalEmployer, cellwidthForPrincipalEmployer, allignForPrincipalEmployer);


		double totalEsi =0.0;
		double companyEsi =0.0;
		double employeeEsi =0.0;
		double totalSalDays =0.0;
		double totalWages =0.0;
		double totalAvgWages =0.0;
		double totalEmployeeContri =0.0;
		
		String option = "All";
		if(option.equals("All")){
		tableData = getTableData(bean,"Sal",String.valueOf(debitCodeData[0][0]));
		
		Object [][] tempArrearsData =getTableData(bean,"Arr",String.valueOf(debitCodeData[0][0]));
		
		Object [][] tempSettlmentData =getTableData(bean,"Settl",String.valueOf(debitCodeData[0][0]));
		
		if(tableData != null && tableData.length!=0){
			totalSalDays = Double.parseDouble(String.valueOf(tableData[tableData.length-1][3]));
			
			totalWages = Double.parseDouble(String.valueOf(tableData[tableData.length-1][4]));
			
			totalAvgWages = Double.parseDouble(String.valueOf(tableData[tableData.length-1][5]));
			
			totalEmployeeContri = Double.parseDouble(String.valueOf(tableData[tableData.length-1][6]));
		
			
		if(tempArrearsData!=null && tempArrearsData.length >1){
		for (int i = 0; i < tempArrearsData.length  -1; i++) {

			if(String.valueOf(tableData[i][2]).equals(String.valueOf(tempArrearsData[i][2]))){
				tableData[i][3] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][3])+Double.parseDouble(""+tempArrearsData[i][3]));
				tableData[i][4] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][4])+Double.parseDouble(""+tempArrearsData[i][4]));
				tableData[i][5] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][5])+Double.parseDouble(""+tempArrearsData[i][5]));
				tableData[i][6] = Utility.twoDecimals((Double.parseDouble(""+tableData[i][4]))/(Double.parseDouble(""+tableData[i][3])));
				
				totalSalDays += Double.parseDouble(String.valueOf(tempArrearsData[i][3]));
				
				totalWages += Double.parseDouble(String.valueOf(tempArrearsData[i][4]));
				
				totalAvgWages += Double.parseDouble(String.valueOf(tempArrearsData[i][5]));
				
				totalEmployeeContri += Double.parseDouble(String.valueOf(tempArrearsData[i][6]));
				
				
			}
		}
		
		}
		
		
		if(tempSettlmentData.length >1){
			for (int i = 0; i < tempSettlmentData.length - 1; i++) {
				//logger.info("String.valueOf(tableData["+i+"][2])=="+String.valueOf(tableData[i][2])+"AA");
				
				//logger.info("String.valueOf(tempSettlmentData["+i+"][2])=="+String.valueOf(tempSettlmentData[i][2])+"BB");
				
				if(String.valueOf(tableData[i][2]).equals(String.valueOf(tempSettlmentData[i][2]))){
					tableData[i][3] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][3])+Double.parseDouble(""+tempSettlmentData[i][3]));
					tableData[i][4] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][4])+Double.parseDouble(""+tempSettlmentData[i][4]));
					tableData[i][5] = Utility.twoDecimals(Double.parseDouble(""+tableData[i][5])+Double.parseDouble(""+tempSettlmentData[i][5]));
					tableData[i][6] = Utility.twoDecimals((Double.parseDouble(""+tableData[i][4]))/(Double.parseDouble(""+tableData[i][3])));
					
					totalSalDays += Double.parseDouble(String.valueOf(tempSettlmentData[i][3]));
					
					totalWages += Double.parseDouble(String.valueOf(tempSettlmentData[i][4]));
					totalWages= Double.parseDouble(formatter.format(totalWages));
					logger.info("totalWages in settlement=="+totalWages);
					totalAvgWages += Double.parseDouble(String.valueOf(tempSettlmentData[i][5]));
					totalAvgWages= Double.parseDouble(formatter.format(totalAvgWages));
					
					totalEmployeeContri += Double.parseDouble(String.valueOf(tempSettlmentData[i][6]));
					totalEmployeeContri= Double.parseDouble(formatter.format(totalEmployeeContri));
				}
			}
		}
		logger.info("totalWages in final=="+totalWages);
			tableData[tableData.length - 1][3] = formatter.format(totalSalDays);
			tableData[tableData.length - 1][4] = formatter.format(totalWages);
			tableData[tableData.length - 1][5] = formatter.format(totalAvgWages);
			tableData[tableData.length-1][6] = formatter.format(totalEmployeeContri);
		}
		
	} 
		else  {
			
			tableData = getTableData(bean,option, String.valueOf(debitCodeData[0][0]));
		}
		try{
		companyEsi = Double.parseDouble(""+tableData[tableData.length -1][4])* Double.parseDouble(String.valueOf(debitCodeData[0][2]))/100;
		companyEsi= Double.parseDouble(formatter.format(companyEsi));
		employeeEsi = Double.parseDouble(""+tableData[tableData.length -1][5]);
		employeeEsi= Double.parseDouble(formatter.format(employeeEsi));
		totalEsi  = companyEsi + employeeEsi ;
		totalEsi= Double.parseDouble(formatter.format(totalEsi));

		}catch (Exception e) {
			companyEsi = 0.00;
			employeeEsi =0.00;
			totalEsi =0.00;
		}
		String message1 ="I Furnish Below the Details of the employer's and employee's share of contribution in respect of the under mentioned Insured Person.";
		String message2 = "\n\nI hearby declare That the return includes every employee, employed directly or through an immediate employer or in connection with the"
			+" work of the factory/establishment or any work connected with the administration of the factory/establishment or purchase of raw"
			+" \nmaterials, sales or distribution of finished product etc. to them the contribution period to which this return relates , applies and that the"
			+" contribution in respect of employer's and employee's share have been correctly paid in accordance with the provision of the Act and "
			+" \nRegulation relating to the payment of contribution vide challans detailed below :-";

		String message3 ="Total Contribution amounting to Rs."+Utility.twoDecimals(totalEsi)+" Comprising of Rs."+Utility.twoDecimals(companyEsi)+" Employer's share and Rs ."+Utility.twoDecimals(employeeEsi)+" as Employee's share";

		String message4 ="(Total of col6 of the return) paid as under :";
		
	
		if(challanData != null && challanData.length !=0){
			Object challanTableData [][]= new Object [challanData.length+1][4];
			double totalChallan = 0.0;
			for (int i = 0; i < challanTableData.length -1; i++) {
				challanTableData [i][0] = ""+(i+1);
				challanTableData [i][1] = "Challan Dated   "+challanData[i][0];
				challanTableData [i][2] = "For Rs. ";
				challanTableData [i][3] = Double.parseDouble(""+challanData[i][1]);
				totalChallan  +=  Double.parseDouble(""+challanData[i][1]);
			}
			challanTableData [challanTableData.length -1][0] ="";
			challanTableData [challanTableData.length -1][1] ="";
			challanTableData [challanTableData.length -1][2] ="Total Rs.";
			challanTableData [challanTableData.length -1][3] =""+totalChallan;
			paraEmp [0][0] = message1 ;

			paraEmp [1][0] = message2 ;

			paraEmp [2][0] = message3+"\n\n"+message4 ;
			
			TableDataSet praemp1 = new TableDataSet();
			praemp1.setData(paraEmp);
			praemp1.setCellAlignment(allignForParaEmployer);
			praemp1.setCellWidth(cellwidthForParaEmployer);		
			//praemp1.setCellColSpan(new int[]{9});
			//praemp1.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
			praemp1.setBlankRowsBelow(1);
			praemp1.setCellColSpan(new int[]{9});
			//localofficedesc.setBorder(false);
			//localofficedesc.setHeaderTable(false);
			rg.addTableToDoc(praemp1);
			
			TableDataSet praemp2 = new TableDataSet();
			praemp2.setData(challanTableData);
			praemp2.setCellAlignment(allignForChallan);
			praemp2.setCellWidth(cellwidthForChallan);	
			//praemp2.setCellColSpan(new int[]{9});
			//praemp2.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
			praemp2.setBlankRowsBelow(1);
			//localofficedesc.setBorder(false);
			//localofficedesc.setHeaderTable(false);
			rg.addTableToDoc(praemp2);
			//rg.addTextPromo("\n", 0, Element.ALIGN_LEFT, 0);
			//rg.tableBodyNoBorder(paraEmployer, cellwidthForParaEmployer, allignForParaEmployer);
			//rg.tableBody(challanTableData, cellwidthForChallan, allignForChallan);
		}else {
			paraEmp [0][0] = message1 ;

			paraEmp [1][0] = message2 ;

			paraEmp [2][0] = message3 ;
			
			TableDataSet praemp1 = new TableDataSet();
			praemp1.setData(paraEmp);
			praemp1.setCellAlignment(allignForParaEmployer);
			praemp1.setCellWidth(cellwidthForParaEmployer);	
			//praemp1.setCellColSpan(new int[]{9});
			//praemp1.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
			praemp1.setBlankRowsBelow(1);
			//localofficedesc.setBorder(false);
			//localofficedesc.setHeaderTable(false);
			rg.addTableToDoc(praemp1);
			//rg.addTextPromo("\n", 0, Element.ALIGN_LEFT, 0);
			//rg.tableBodyNoBorder(paraEmployer, cellwidthForParaEmployer, allignForParaEmployer);
		}
 
		
		
		
		
		principalEmployer [0][0] ="Place :" ;
		principalEmployer [0][1] ="Signature " ;
		principalEmployer [1][0] ="Date :" ;
		principalEmployer [1][1] ="Designation :";
		
		TableDataSet prinemp = new TableDataSet();
		prinemp.setData(principalEmployer);
		prinemp.setCellAlignment(allignForPrincipalEmployer);
		prinemp.setCellWidth(cellwidthForPrincipalEmployer);
		//prinemp.setCellColSpan(new int[]{9});
		//prinemp.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
		prinemp.setBlankRowsBelow(1);
		//localofficedesc.setBorder(false);
		//localofficedesc.setHeaderTable(false);
		rg.addTableToDoc(prinemp);
		//rg.addFormatedText("\n", 0, 0, 0, 0);
		//rg.tableBodyNoBorder(principalEmployer, cellwidthForPrincipalEmployer, allignForPrincipalEmployer);

		String message5 = "Important Instructions";

		String message6 = "1. If any I.P. is appointed for the first time and/or leaves service during the contribution period, indicate 'A.............(Date)'"
			+" and/or 'L..............(Date)'', in the remark column (No.8)"
			+"\n2. Please indicate Insurance Numbers in chronological (ascending) order."
			+"\n3. Figures in Columns 4,5 & 6 shall be in respect of wage periods ended during the contribution period."
			+"\n4. Invariably strike totals of Columns of 4,5 & 6 of the return"
			+"\n5. No over-writing shall be made. Any corrections should be signed by the employer"
			+"\n6. Every page of this return should bear full signature & rubber stamp of the employer."
			+"\n7. 'Daily Wages' in Col.7 of the return shall be calculated by dividing figures in Col. 5 by figures in Col. 4 to two decimal Places.";


		paraEmployer [0][0] = message5 ;

		paraEmployer [1][0] = message6 ;

		paraEmployer [2][0] = "";
		
		TableDataSet praemp3 = new TableDataSet();
		praemp3.setData(paraEmployer);
		praemp3.setCellAlignment(allignForParaEmployer);
		praemp3.setCellWidth(cellwidthForParaEmployer);	
		praemp3.setCellColSpan(new int[]{9});
		//praemp3.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
		praemp3.setBlankRowsBelow(1);
		//localofficedesc.setBorder(false);
		//localofficedesc.setHeaderTable(false);
		rg.addTableToDoc(praemp3);

		//rg.addFormatedText("\n", 0, 0, 0, 0);
		//rg.tableBodyNoBorder(paraEmployer, cellwidthForParaEmployer, allignForParaEmployer);
		rg.addProperty(rg.PAGE_BREAK);

		String tableColumns [] = {"Sr.No\n(1)", "Insurance Number\n(2)", "Name of insured Person \n(3)", "No of Days for which wages\n(4)", "Total amount of Wages Paid\n(5)", "Employee's contribution deducted\n(6)",
				"Average Daily's Wages 5/4\n(7)", "Dispensary\n(7A)", "Remarks\n(8)"};

		int tableCellwidth [] ={10, 20, 40, 15, 20, 22, 20, 25, 30};
		int tableAllignment [] ={1, 0, 0, 2, 2, 2, 2, 0, 0};

		try {
			TableDataSet tabledataheader = new TableDataSet();
			tabledataheader.setData(tableData);
			tabledataheader.setHeader(tableColumns);
			tabledataheader.setCellAlignment(tableAllignment);
			tabledataheader.setCellWidth(tableCellwidth);				
			//tabledata.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL,new BaseColor(0, 0, 0));
			tabledataheader.setHeaderBGColor(new BaseColor(225, 225, 225));
			tabledataheader.setBorder(true);
			tabledataheader.setHeaderBorderDetail(3);
			tabledataheader.setBorderDetail(3);
			tabledataheader.setBlankRowsBelow(1);
			tabledataheader.setHeaderTable(true);
			//localofficedesc.setHeaderTable(false);
			rg.addTableToDoc(tabledataheader);
			//rg.addFormatedText("\n", 0, 0, 0, 0);
			//.tableBody(tableColumns, tableData, tableCellwidth,
					//tableAllignment);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		rg.process();
		
		if(reportPath.equals("")){
			rg.createReport(response);
		}else{
			
			rg.saveReport(response);
			}

	}

	public Object [][] getTableData(Form6EsicReport bean,String type, String debitCode){
		
		Object [][] returnTableData= null;
		
		double totalSalDays =0.0;
		double totalWages =0.0;
		double totalAvgWages =0.0;
		double totalEmployeeContri =0.0;
		
		Object salDaysApriltoSep [][] =null;
		Object salAmountOctToDec [][]= null;
		Object salDaysOctToDec [][]= null;
		Object salAmountJanToMar [][]= null;
		Object salDaysJanToMar [][]= null;

		String empString="";
		
		
		/*
		 * retrieve the salary amount for ESIC for April to September
		 * 
		 */
		String querySalAmountAprilToSep = "SELECT HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME,"
				+" SUM(SAL_AMOUNT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SAL_DEBITS_"+bean.getFromYear()+" " 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE  HRMS_SAL_DEBITS_"+bean.getFromYear()+".SAL_LEDGER_CODE IN ("+getLedgerCode("4 AND 9", bean.getFromYear(), type)+") AND EMP_DIV ="+bean.getDivisionCode()
				+" AND SAL_AMOUNT > 0 AND SAL_DEBIT_CODE="+debitCode
				+" GROUP BY HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME, SAL_ESCINUMBER"
				+" ORDER BY UPPER(NAME) ";																//HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID";
		
		String queryArrAmountAprilToSep = "SELECT HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME ,"
				+" SUM(ARREARS_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_ARREARS_DEBIT_"+bean.getFromYear() 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" AND ARREARS_DEBIT_CODE="+debitCode+" AND HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_CODE IN ("+getLedgerCode("4 AND 9", bean.getFromYear(), type)+")"
				+" WHERE HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_CODE IN ("+getLedgerCode("4 AND 9", bean.getFromYear(), type)+") AND EMP_DIV="+bean.getDivisionCode()
				+" AND ARREARS_AMT > 0"
				+" GROUP BY HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER  ORDER BY UPPER(NAME)";
		
		String querySettlAmountAprilToSep = "SELECT HRMS_RESIGN.RESIGN_EMP,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+" HRMS_EMP_OFFC.EMP_LNAME AS NAME ,SUM(SETTL_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SETTL_HDR  "
				+" LEFT JOIN HRMS_SETTL_DEBITS ON (HRMS_SETTL_DEBITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE) "
				+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE SETTL_DEBIT_CODE="+debitCode+" AND SETTL_AMT >0 AND SETTL_MTH BETWEEN 4 AND 9 AND SETTL_YEAR="+bean.getFromYear()+" AND EMP_DIV="+bean.getDivisionCode()
				+" AND SETTL_AMT > 0"
				+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER  ORDER BY UPPER(NAME)";

		Object salAmountAprilToSepData [][]= null;


		/*
		 * retrieve the salary amount for ESIC for October to December
		 * 
		 */
		String querySalAmountOctToDec = "SELECT HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME,"
				+" SUM(SAL_AMOUNT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SAL_DEBITS_"+bean.getFromYear()+" " 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE  HRMS_SAL_DEBITS_"+bean.getFromYear()+".SAL_LEDGER_CODE IN ("+getLedgerCode("10 AND 12", bean.getFromYear(), type)+") AND EMP_DIV ="+bean.getDivisionCode()
				+" AND SAL_AMOUNT > 0 AND SAL_DEBIT_CODE="+debitCode
				+" GROUP BY HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER "
				+" ORDER BY UPPER(NAME) ";									//HRMS_SAL_DEBITS_"+bean.getFromYear()+".EMP_ID";
		
		String queryArrAmountOctToDec = "SELECT HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME ,"
				+" SUM(ARREARS_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_ARREARS_DEBIT_"+bean.getFromYear() 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" AND ARREARS_DEBIT_CODE="+debitCode+" AND HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_CODE IN ("+getLedgerCode("10 AND 12", bean.getFromYear(), type)+")"
				+" WHERE HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_CODE IN ("+getLedgerCode("10 AND 12", bean.getFromYear(), type)+") AND EMP_DIV="+bean.getDivisionCode()
				+" AND ARREARS_AMT > 0"
				+" GROUP BY HRMS_ARREARS_DEBIT_"+bean.getFromYear()+".ARREARS_EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER  ORDER BY UPPER(NAME)";
		
		String querySettlAmountOctToDec = "SELECT HRMS_RESIGN.RESIGN_EMP,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+" HRMS_EMP_OFFC.EMP_LNAME AS NAME,SUM(SETTL_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SETTL_HDR  "
				+" LEFT JOIN HRMS_SETTL_DEBITS ON (HRMS_SETTL_DEBITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE) "
				+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE SETTL_DEBIT_CODE="+debitCode+" AND SETTL_AMT >0 AND SETTL_MTH BETWEEN 10 AND 12 AND SETTL_YEAR="+bean.getFromYear()+" AND EMP_DIV="+bean.getDivisionCode()
				+" AND SETTL_AMT > 0"
				+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER  ORDER BY UPPER(NAME)";
		
		/*
		 * retrieve the salary amount for ESIC for January to March of the next year
		 * 
		 */
		String querySalAmountJanToMar = "SELECT HRMS_SAL_DEBITS_"+bean.getToYear()+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME,"
				+" SUM(SAL_AMOUNT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SAL_DEBITS_"+bean.getToYear()+" " 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+bean.getToYear()+".EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE  HRMS_SAL_DEBITS_"+bean.getToYear()+".SAL_LEDGER_CODE IN ("+getLedgerCode("1 AND 3", bean.getToYear(), type)+") AND EMP_DIV ="+bean.getDivisionCode()
				+" AND SAL_AMOUNT > 0 AND SAL_DEBIT_CODE="+debitCode
				+" GROUP BY HRMS_SAL_DEBITS_"+bean.getToYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER "
				+" ORDER BY UPPER(NAME) ";									 //HRMS_SAL_DEBITS_"+bean.getToYear()+".EMP_ID";
		
		String queryArrAmountJanToMar = "SELECT HRMS_ARREARS_DEBIT_"+bean.getToYear()+".ARREARS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME AS NAME ,"
				+" SUM(ARREARS_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_ARREARS_DEBIT_"+bean.getToYear() 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_DEBIT_"+bean.getToYear()+".ARREARS_EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" AND ARREARS_DEBIT_CODE="+debitCode+" AND HRMS_ARREARS_DEBIT_"+bean.getToYear()+".ARREARS_CODE IN ("+getLedgerCode("1 AND 3", bean.getToYear(), type)+")"
				+" WHERE HRMS_ARREARS_DEBIT_"+bean.getToYear()+".ARREARS_CODE IN ("+getLedgerCode("1 AND 3", bean.getToYear(), type)+") AND EMP_DIV="+bean.getDivisionCode()
				+" AND ARREARS_AMT > 0"
				+" GROUP BY HRMS_ARREARS_DEBIT_"+bean.getToYear()+".ARREARS_EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER  ORDER BY UPPER(NAME)";
		
		String querySettlAmountJanToMar = "SELECT HRMS_RESIGN.RESIGN_EMP,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+" HRMS_EMP_OFFC.EMP_LNAME AS NAME ,SUM(SETTL_AMT),NVL(SAL_ESCINUMBER,'N/A') FROM HRMS_SETTL_HDR  "
				+" LEFT JOIN HRMS_SETTL_DEBITS ON (HRMS_SETTL_DEBITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE) "
				+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" WHERE SETTL_DEBIT_CODE="+debitCode+" AND SETTL_AMT >0 AND SETTL_MTH BETWEEN 1 AND 3 AND SETTL_YEAR="+bean.getToYear()+" AND EMP_DIV="+bean.getDivisionCode()
				+" AND SETTL_AMT > 0"
				+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME,SAL_ESCINUMBER ORDER BY UPPER(NAME)";
		

		/*
		 * Calculation if contribution period selected is April to September
		 * 
		 */
		if(bean.getContributionPeriod().equals("A")){

			if(type.equals("Sal")){
			salAmountAprilToSepData = getSqlModel().getSingleResult(querySalAmountAprilToSep);
			}else if(type.equals("Arr")){
				salAmountAprilToSepData = getSqlModel().getSingleResult(queryArrAmountAprilToSep);
			}else if(type.equals("Settl")){
				salAmountAprilToSepData = getSqlModel().getSingleResult(querySettlAmountAprilToSep);
			}

			/*
			 * get empCode in the String
			 * 
			 */
			if(salAmountAprilToSepData!=null && salAmountAprilToSepData.length !=0 )
			{	
				for (int i = 0; i < salAmountAprilToSepData.length - 1; i++) 
					
					empString += salAmountAprilToSepData[i][0]+",";
					empString += salAmountAprilToSepData[salAmountAprilToSepData.length - 1][0];
			}
			else
				empString="0";
			
			/*
			 * Query to get the Salary days and total wages paid for particular employee (from April to September)
			 * 
			 * 
			 */
			String querySalDaysApriltoSep = "SELECT HRMS_SALARY_"+bean.getFromYear()+".EMP_ID,SUM(SAL_DAYS),SUM(SAL_TOTAL_CREDIT)," 
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_SALARY_"+bean.getFromYear()+" "
					+" LEFT JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE =HRMS_SALARY_"+bean.getFromYear()+".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+bean.getFromYear()+".EMP_ID)"
					+" WHERE LEDGER_MONTH BETWEEN 4 and 9 AND LEDGER_YEAR= "+bean.getFromYear()+" AND HRMS_SALARY_"+bean.getFromYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_SALARY_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER BY UPPER(NAME)";									//	HRMS_SALARY_"+bean.getFromYear()+".EMP_ID" ;
			
			String queryArrDaysApriltoSep = "SELECT HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID,SUM(ARREARS_DAYS),SUM(ARREARS_CREDITS_AMT), "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_ARREARS_"+bean.getFromYear()+" "
					+" LEFT JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE =HRMS_ARREARS_"+bean.getFromYear()+".ARREARS_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID)"
					+" WHERE ARREARS_REF_MONTH BETWEEN 4 and 9 AND ARREARS_REF_YEAR= "+bean.getFromYear()+" AND HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER BY UPPER(NAME)";
			
			String querySettlDaysApriltoSep = "SELECT HRMS_RESIGN.RESIGN_EMP,SETTL_ELIGIBLEDAYS,SUM(SETTL_AMT),  "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME "
					+" FROM HRMS_SETTL_HDR  "
					+" LEFT JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE)" 
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
					+" WHERE SETTL_MTH BETWEEN 4 AND 9 AND SETTL_YEAR="+bean.getFromYear()+" AND HRMS_RESIGN.RESIGN_EMP IN ("+empString+") "
					+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME, SETTL_ELIGIBLEDAYS  ORDER BY UPPER(NAME)";
			if(type.equals("Sal"))
				
				salDaysApriltoSep = getSqlModel().getSingleResult(querySalDaysApriltoSep);
			else if(type.equals("Arr"))
				salDaysApriltoSep = getSqlModel().getSingleResult(queryArrDaysApriltoSep);
			
			else if(type.equals("Settl"))
				salDaysApriltoSep = getSqlModel().getSingleResult(querySettlDaysApriltoSep);
			
			Object joinDate[][]=getSqlModel().getSingleResult("SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), EMP_ID FROM HRMS_EMP_OFFC WHERE TO_CHAR(EMP_REGULAR_DATE,'MM') BETWEEN 4 AND 9 AND "
					+"TO_CHAR(EMP_REGULAR_DATE,'YYYY') ="+bean.getFromYear()+" AND EMP_ID IN ("+empString+")");

			Object leaveDate[][]=getSqlModel().getSingleResult("SELECT TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'), EMP_ID FROM HRMS_EMP_OFFC WHERE TO_CHAR(EMP_LEAVE_DATE,'MM') BETWEEN 4 AND 9 AND "
					+"TO_CHAR(EMP_LEAVE_DATE,'YYYY') ="+bean.getFromYear()+" AND EMP_ID IN ("+empString+")");


			
			try {
				returnTableData = new Object[salAmountAprilToSepData.length + 1][9];
				
				//logger.info("salDaysApriltoSep length===="+salDaysApriltoSep.length);
				for (int i = 0; i < returnTableData.length - 1; i++) {
					
					
					returnTableData[i][0] = "" + (i + 1);
					returnTableData[i][1] = String.valueOf(salAmountAprilToSepData[i][3]); // Insurance number
					
					returnTableData[i][2] = String.valueOf(salAmountAprilToSepData[i][1]); // name of employee
					
					returnTableData[i][3] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salDaysApriltoSep[i][1]))); // salary days
					
					returnTableData[i][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salDaysApriltoSep[i][2]))); // Total wages paid
					
					returnTableData[i][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salAmountAprilToSepData[i][2]))); // Employee's contribution
					
					returnTableData[i][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(returnTableData[i][4]))/ 
										Double.parseDouble(String.valueOf(returnTableData[i][3]))); // average wages per day
					
					returnTableData[i][7] = "";
					
					if (joinDate != null || joinDate.length != 0)
						for (int j = 0; j < joinDate.length; j++) {
							if (joinDate[j][1]
									.equals(salAmountAprilToSepData[i][0])) {
								returnTableData[i][8] = "A.." + joinDate[j][0];

							}
						}
					
					if (leaveDate != null || leaveDate.length != 0)
						for (int j = 0; j < leaveDate.length; j++) {
							if (leaveDate[j][1]
									.equals(salAmountAprilToSepData[i][0])) {
								returnTableData[i][8] = "L.." + leaveDate[j][0];
							}
						}
					/*if(tableData [i][8]==null || tableData [i][8].equals("null"))
					tableData [i][8] = "";*/

					totalSalDays += Double.parseDouble(String
							.valueOf(salDaysApriltoSep[i][1]));
					totalWages += Double.parseDouble(String
							.valueOf(salDaysApriltoSep[i][2]));
					totalEmployeeContri += Double.parseDouble(String
							.valueOf(salAmountAprilToSepData[i][2]));
					totalAvgWages += Double.parseDouble(String
							.valueOf(returnTableData[i][6]));
					
					
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				logger.error(e);
			}
		}



		else if(bean.getContributionPeriod().equals("O")){

			if(type.equals("Sal")){
				
				salAmountOctToDec = getSqlModel().getSingleResult(querySalAmountOctToDec);
			}else if(type.equals("Arr")) {
				salAmountOctToDec = getSqlModel().getSingleResult(queryArrAmountOctToDec);
			}
			else if(type.equals("Settl")){
				salAmountOctToDec = getSqlModel().getSingleResult(querySettlAmountOctToDec);
			}

			empString ="";

			if(salAmountOctToDec!=null && salAmountOctToDec.length !=0)
			{	
				for (int i = 0; i < salAmountOctToDec.length - 1; i++) 
					empString += salAmountOctToDec[i][0]+",";
				empString += salAmountOctToDec[salAmountOctToDec.length - 1][0];
			}
			else
				empString="0";
			
			/*
			 * Query to get the Salary days and total wages paid for particular employee (from October to December)
			 * 
			 * 
			 */
			String querySalDaysOctToDec = "SELECT HRMS_SALARY_"+bean.getFromYear()+".EMP_ID,SUM(SAL_DAYS),SUM(SAL_TOTAL_CREDIT),"
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_SALARY_"+bean.getFromYear()
					+" LEFT JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE =HRMS_SALARY_"+bean.getFromYear()+".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+bean.getFromYear()+".EMP_ID)"
					+" WHERE LEDGER_MONTH BETWEEN 10 and 12 AND LEDGER_YEAR= "+bean.getFromYear()+"AND HRMS_SALARY_"+bean.getFromYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_SALARY_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER by UPPER(NAME)"; 														//HRMS_SALARY_"+bean.getFromYear()+".EMP_ID" ;
			
			String queryArrDaysOctToDec = "SELECT HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID,SUM(ARREARS_DAYS),SUM(ARREARS_CREDITS_AMT), "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_ARREARS_"+bean.getFromYear()+" "
					+" LEFT JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE =HRMS_ARREARS_"+bean.getFromYear()+".ARREARS_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID)"
					+" WHERE ARREARS_REF_MONTH BETWEEN 10 and 12 AND ARREARS_REF_YEAR="+bean.getFromYear()+" AND HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_ARREARS_"+bean.getFromYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER BY UPPER(NAME)"; 
			
			String querySettlDaysOctToDec = "SELECT HRMS_RESIGN.RESIGN_EMP,SETTL_ELIGIBLEDAYS,SUM(SETTL_AMT),  "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME "
					+" FROM HRMS_SETTL_HDR  "
					+" LEFT JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE) "
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
					+" WHERE SETTL_MTH BETWEEN 10 AND 12 AND SETTL_YEAR="+bean.getFromYear()+" AND HRMS_RESIGN.RESIGN_EMP IN ("+empString+")" 
					+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME,SETTL_ELIGIBLEDAYS  ORDER BY UPPER(NAME)";
			
			
			logger.info("querySalDaysOctToDec==="+querySalDaysOctToDec);
			if(type.equals("Sal"))
			{	
			salDaysOctToDec = getSqlModel().getSingleResult(querySalDaysOctToDec);
			salAmountJanToMar = getSqlModel().getSingleResult(querySalAmountJanToMar);
			}
			else if(type.equals("Arr")){
				salDaysOctToDec = getSqlModel().getSingleResult(queryArrDaysOctToDec);
				salAmountJanToMar = getSqlModel().getSingleResult(queryArrAmountJanToMar);
			}
			else if(type.equals("Settl")){
				salDaysOctToDec = getSqlModel().getSingleResult(querySettlDaysOctToDec);
				salAmountJanToMar = getSqlModel().getSingleResult(querySettlAmountJanToMar);
			}
			

			empString ="";	
			if(salAmountJanToMar!=null && salAmountJanToMar.length !=0 )
			{	
				for (int i = 0; i < salAmountJanToMar.length - 1; i++) 
					empString += salAmountJanToMar[i][0]+",";
				empString += salAmountJanToMar[salAmountJanToMar.length - 1][0];
			}
			else
				empString="0";



			String querySalDaysJanToMar = "SELECT HRMS_SALARY_"+bean.getToYear()+".EMP_ID,SUM(SAL_DAYS),SUM(SAL_TOTAL_CREDIT)," 
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_SALARY_"+bean.getToYear()
					+" LEFT JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE =HRMS_SALARY_"+bean.getToYear()+".SAL_LEDGER_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+bean.getToYear()+".EMP_ID)"
					+" WHERE LEDGER_MONTH BETWEEN 1 and 3 AND LEDGER_YEAR= "+bean.getToYear()+"AND HRMS_SALARY_"+bean.getToYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_SALARY_"+bean.getToYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER BY UPPER(NAME)";													//HRMS_SALARY_"+bean.getToYear()+".EMP_ID" ;
			
			String queryArrDaysJanToMar = "SELECT HRMS_ARREARS_"+bean.getToYear()+".EMP_ID,SUM(ARREARS_DAYS),SUM(ARREARS_CREDITS_AMT), "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME FROM HRMS_ARREARS_"+bean.getToYear()+" "
					+" LEFT JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE =HRMS_ARREARS_"+bean.getToYear()+".ARREARS_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+bean.getToYear()+".EMP_ID)"
					+" WHERE ARREARS_REF_MONTH BETWEEN 1 and 3 AND ARREARS_REF_YEAR= "+bean.getToYear()+" AND HRMS_ARREARS_"+bean.getToYear()+".EMP_ID IN ("+empString+")"
					+" GROUP BY HRMS_ARREARS_"+bean.getToYear()+".EMP_ID,EMP_FNAME, EMP_MNAME,EMP_LNAME "
					+" ORDER BY UPPER(NAME)";
			
			String querySettlDaysJanToMar = "SELECT HRMS_RESIGN.RESIGN_EMP,SETTL_ELIGIBLEDAYS,SUM(SETTL_AMT),  "
					+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME "
					+" FROM HRMS_SETTL_HDR  "
					+" LEFT JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE =HRMS_SETTL_HDR.SETTL_CODE) "
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
					+" WHERE SETTL_MTH BETWEEN 1 AND 3 AND SETTL_YEAR="+bean.getToYear()+" AND HRMS_RESIGN.RESIGN_EMP IN ("+empString+")" 
					+" GROUP BY HRMS_RESIGN.RESIGN_EMP,EMP_FNAME, EMP_MNAME,EMP_LNAME ,SETTL_ELIGIBLEDAYS ORDER BY UPPER(NAME)";
			
			if(type.equals("Sal")){
			
				salDaysJanToMar = getSqlModel().getSingleResult(querySalDaysJanToMar);
			}else if(type.equals("Arr")){
				salDaysJanToMar = getSqlModel().getSingleResult(queryArrDaysJanToMar);
			}
			else if(type.equals("Settl")){
				salDaysJanToMar = getSqlModel().getSingleResult(querySettlDaysJanToMar);
			}

			try {
				Object salAmountOctToDecNew[][] = new Object[salAmountOctToDec.length][7]; // temporary Object
				Object salAmountJanToMarNew[][] = null;
				int counter = 0;
				String index = "";
				if (salDaysJanToMar != null && salDaysJanToMar.length != 0) {
					for (int i = 0; i < salAmountOctToDec.length; i++) {
						for (int j = 0; j < salAmountJanToMar.length; j++) {
							if (salAmountOctToDec[i][0]
									.equals(salAmountJanToMar[j][0])) {
								salAmountOctToDecNew[i][0] = salAmountOctToDec[i][1]; // Employee Name
								salAmountOctToDecNew[i][1] = Double
										.parseDouble("" + salDaysOctToDec[i][1])
										+ Double.parseDouble(""
												+ salDaysJanToMar[j][1]); // salary days
								salAmountOctToDecNew[i][2] = Double
										.parseDouble("" + salDaysOctToDec[i][2])
										+ Double.parseDouble(""
												+ salDaysJanToMar[j][2]); // wages paid
								salAmountOctToDecNew[i][3] = Double
										.parseDouble(""
												+ salAmountOctToDec[i][2])
										+ Double.parseDouble(""
												+ salAmountJanToMar[j][2]); // Employee's Contribution
								salAmountOctToDecNew[i][4] = Utility
										.twoDecimals(Double
												.parseDouble(String
														.valueOf(salAmountOctToDecNew[i][2]))
												/ Double
														.parseDouble(String
																.valueOf(salAmountOctToDecNew[i][1]))); // Wages paid per day
								salAmountOctToDecNew[i][5] = salAmountOctToDec[i][0];
								salAmountOctToDecNew[i][6] = salAmountOctToDec[i][3];
								counter += 1;
							} else {
								salAmountOctToDecNew[i][0] = salAmountOctToDec[i][1]; // Employee Name
								salAmountOctToDecNew[i][1] = Double
										.parseDouble("" + salDaysOctToDec[i][1]); // salary days
								salAmountOctToDecNew[i][2] = Double
										.parseDouble("" + salDaysOctToDec[i][2]); // wages paid
								salAmountOctToDecNew[i][3] = Double
										.parseDouble(""
												+ salAmountOctToDec[i][2]); // Employee's Contribution
								salAmountOctToDecNew[i][4] = Utility
										.twoDecimals(Double
												.parseDouble(String
														.valueOf(salAmountOctToDecNew[i][2]))
												/ Double
														.parseDouble(String
																.valueOf(salAmountOctToDecNew[i][1])));
								salAmountOctToDecNew[i][5] = salAmountOctToDec[i][0];
								salAmountOctToDecNew[i][6] = salAmountOctToDec[i][3];
								index += j + ",";
							}
						}
					}
					counter = salAmountJanToMar.length - counter;
					if (counter != 0) {
						String empIdSplit[] = index.split(",");
						salAmountJanToMarNew = new Object[counter][7];
						for (int i = 0; i < salAmountJanToMarNew.length; i++) {
							salAmountJanToMarNew[i][0] = salAmountJanToMar[Integer
									.parseInt(empIdSplit[i])][1];
							salAmountJanToMarNew[i][1] = salDaysJanToMar[Integer
									.parseInt(empIdSplit[i])][1];
							salAmountJanToMarNew[i][2] = salDaysJanToMar[Integer
									.parseInt(empIdSplit[i])][2];
							salAmountJanToMarNew[i][3] = salAmountJanToMar[Integer
									.parseInt(empIdSplit[i])][2];
							salAmountJanToMarNew[i][4] = Utility
									.twoDecimals(Double
											.parseDouble(String
													.valueOf(salAmountJanToMarNew[i][2]))
											/ Double
													.parseDouble(String
															.valueOf(salAmountJanToMarNew[i][1])));
							salAmountJanToMarNew[i][5] = salAmountJanToMar[Integer
									.parseInt(empIdSplit[i])][0];
							salAmountJanToMarNew[i][6] = salAmountJanToMar[Integer
							           									.parseInt(empIdSplit[i])][3];
						}
					}
				} // end if 312
				else {
					for (int i = 0; i < salAmountOctToDecNew.length; i++) {

						salAmountOctToDecNew[i][0] = salAmountOctToDec[i][1]; // Employee Name
						salAmountOctToDecNew[i][1] = Double.parseDouble(""
								+ salDaysOctToDec[i][1]); // salary days
						salAmountOctToDecNew[i][2] = Double.parseDouble(""
								+ salDaysOctToDec[i][2]); // wages paid
						salAmountOctToDecNew[i][3] = Double.parseDouble(""
								+ salAmountOctToDec[i][2]); // Employee's Contribution
						salAmountOctToDecNew[i][4] = Utility.twoDecimals(Double
								.parseDouble(String
										.valueOf(salAmountOctToDecNew[i][2]))
								/ Double.parseDouble(String
										.valueOf(salAmountOctToDecNew[i][1])));
						salAmountOctToDecNew[i][5] = salAmountOctToDec[i][0];
						salAmountOctToDecNew[i][6] = salAmountOctToDec[i][3];
					}
				}
				returnTableData = new Object[salAmountOctToDecNew.length
						+ salAmountJanToMar.length + 1][9];
				
				Object joinDate[][] = getSqlModel().getSingleResult("SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), EMP_ID FROM HRMS_EMP_OFFC "
										+"WHERE TO_CHAR(EMP_REGULAR_DATE,'MM') BETWEEN 4 AND 9 AND "
										+ "TO_CHAR(EMP_REGULAR_DATE,'YYYY') ="+ bean.getFromYear()+ " AND EMP_ID IN ("+ empString + ")");
				Object leaveDate[][] = getSqlModel().getSingleResult("SELECT TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'), EMP_ID FROM HRMS_EMP_OFFC WHERE TO_CHAR(EMP_LEAVE_DATE,'MM') BETWEEN 4 AND 9 AND "
										+ "TO_CHAR(EMP_LEAVE_DATE,'YYYY') ="+ bean.getFromYear()+ " AND EMP_ID IN ("+ empString + ")");
				
				for (int i = 0; i < salAmountOctToDecNew.length; i++) {
					returnTableData[i][0] = "" + (i + 1);
					returnTableData[i][1] = salAmountOctToDecNew[i][6]; // Insurance number
					returnTableData[i][2] = salAmountOctToDecNew[i][0]; // name of employee
					returnTableData[i][3] = salAmountOctToDecNew[i][1]; // salary days
					returnTableData[i][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salAmountOctToDecNew[i][2]))); // Total wages paid
					returnTableData[i][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salAmountOctToDecNew[i][3]))); // Employee's contribution
					returnTableData[i][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(salAmountOctToDecNew[i][4]))); // average wages per day
					returnTableData[i][7] = "";
					if (joinDate != null && joinDate.length != 0)
						for (int j = 0; j < joinDate.length; j++) {
							if (joinDate[j][1]
									.equals(salAmountOctToDecNew[i][5])) {
								returnTableData[i][8] = "A.." + joinDate[j][0];
							}
						}
					if (leaveDate != null && leaveDate.length != 0)
						for (int j = 0; j < leaveDate.length; j++) {
							if (leaveDate[j][1]
									.equals(salAmountOctToDecNew[i][5])) {
								returnTableData[i][8] = "L.." + leaveDate[j][0];
							}
						}

					totalSalDays += Double.parseDouble(String
							.valueOf(returnTableData[i][3]));
					totalWages += Double.parseDouble(String
							.valueOf(returnTableData[i][4]));
					totalEmployeeContri += Double.parseDouble(String
							.valueOf(returnTableData[i][5]));
					totalAvgWages += Double.parseDouble(String
							.valueOf(returnTableData[i][6]));
				}
				if (salAmountJanToMarNew != null
						&& salAmountJanToMarNew.length != 0) {
					for (int k = salAmountOctToDecNew.length; k < salAmountOctToDecNew.length
							+ salAmountJanToMarNew.length; k++) {

						returnTableData[k][0] = "" + (k + 1);
						returnTableData[k][1] = salAmountJanToMarNew[k - salAmountOctToDecNew.length][6]; // Insurance number
						returnTableData[k][2] = salAmountJanToMarNew[k - salAmountOctToDecNew.length][0]; // name of employee
						
						returnTableData[k][3] = (Double.parseDouble(""+ salAmountJanToMarNew[k - salAmountOctToDecNew.length][1])); // salary days
						
						returnTableData[k][4] = Utility.twoDecimals(Double.parseDouble(""+ salAmountJanToMarNew[k - salAmountOctToDecNew.length][2])); // Total wages paid
						
						returnTableData[k][5] = Utility.twoDecimals(Double.parseDouble(""+ salAmountJanToMarNew[k - salAmountOctToDecNew.length][3])); // Employee's contribution
						
						returnTableData[k][6] = Utility.twoDecimals(Double.parseDouble(""+ salAmountJanToMarNew[k- salAmountOctToDecNew.length][4])); // average wages per day
						
						returnTableData[k][7] = "";
						returnTableData[k][8] = "";

						if (joinDate != null && joinDate.length != 0)
							for (int j = 0; j < joinDate.length; j++) {
								if (joinDate[j][1].equals(salAmountJanToMarNew[k - salAmountOctToDecNew.length][5])) {
									
									returnTableData[k][8] = "A.." + joinDate[j][0];
								}
							}
						if (leaveDate != null && leaveDate.length != 0)
							for (int j = 0; j < leaveDate.length; j++) {
								if (leaveDate[j][1].equals(salAmountJanToMarNew[k - salAmountOctToDecNew.length][5])) {
									returnTableData[k][8] = "L.." + leaveDate[j][0];
								}
							}
						totalSalDays += Double.parseDouble(String.valueOf(returnTableData[k][3]));
						
						totalWages += Double.parseDouble(String
								.valueOf(returnTableData[k][4]));
						totalWages= Double.parseDouble(formatter.format(totalWages));
						totalEmployeeContri += Double.parseDouble(String
								.valueOf(returnTableData[k][5]));
						totalEmployeeContri= Double.parseDouble(formatter.format(totalEmployeeContri));
						totalAvgWages += Double.parseDouble(String
								.valueOf(returnTableData[k][6]));
						totalAvgWages= Double.parseDouble(formatter.format(totalAvgWages));
					} // end for loop 369			

				} // end if  368
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}

		} 			// end if 260
		try {
			logger.info("totalWages=="+totalWages+" "+type);
			//totalWages= Double.parseDouble(formatter.format(totalWages));
			logger.info("totalWages=="+totalWages+" "+type);
			returnTableData[returnTableData.length - 1][0] = "";
			returnTableData[returnTableData.length - 1][1] = "";
			returnTableData[returnTableData.length - 1][2] = " Total ";
			returnTableData[returnTableData.length - 1][3] = ""
					+ Utility.twoDecimals(totalSalDays); // total salary days
			returnTableData[returnTableData.length - 1][4] = ""
					+ Utility.twoDecimals(formatter.format(totalWages)); // Total wages paid
			returnTableData[returnTableData.length - 1][5] = ""
					+ Utility.twoDecimals(formatter.format(totalEmployeeContri)); // total Employee's contribution
			returnTableData[returnTableData.length - 1][6] = ""
					+ Utility.twoDecimals(formatter.format(totalAvgWages)); // Total average wages per day
			returnTableData[returnTableData.length - 1][7] = "";
			returnTableData[returnTableData.length - 1][8] = "";
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return returnTableData;
	}
	public String getOnholdString(String year, Form6EsicReport bean, String type) {
		String onHoldString = "";
		if (type.equals("Sal")) {
			if (bean.getOnHold().equals("Y")) {
				onHoldString = " AND HRMS_SALARY_" + year + ".SAL_ONHOLD='Y' ";
			} else if (bean.getOnHold().equals("N")) {
				onHoldString = " AND HRMS_SALARY_" + year + ".SAL_ONHOLD='N' ";
			}
		} else if (type.equals("Arr")) {
			if (bean.getOnHold().equals("Y")) {
				onHoldString = " AND HRMS_ARREARS_" + year + ".ARREARS_ONHOLD='Y' ";
			} else if (bean.getOnHold().equals("N")) {
				onHoldString = " AND HRMS_ARREARS_"+ year + ".ARREARS_ONHOLD='N' ";
			}
		}
		return onHoldString;
	}
	
	public String getLedgerCode(String monthString, String year, String type) {

		String query =" SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH BETWEEN "
				+ monthString + " AND LEDGER_YEAR= " + year;
		if(type.equals("Sal")){
			query = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH BETWEEN "
				+ monthString + " AND LEDGER_YEAR= " + year;
		}else if(type.equals("Arr")){
			query = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH BETWEEN "
				+ monthString +" AND ARREARS_REF_YEAR ="+year ;
		}
		Object[][] ledgerCodes = getSqlModel().getSingleResult(query);

		String ledgerCodeString = "";
		try {
			for (int i = 0; i < ledgerCodes.length - 1; i++)
				ledgerCodeString += ledgerCodes[i][0] + ",";
			ledgerCodeString += ledgerCodes[ledgerCodes.length - 1][0];
		} catch (Exception e) {
			ledgerCodeString = "0";
		}
		return ledgerCodeString;
	}
}
