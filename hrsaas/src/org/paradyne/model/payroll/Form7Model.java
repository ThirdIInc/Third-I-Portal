package org.paradyne.model.payroll;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Form7;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/*
 * Author:Pradeep
 * Date:17-09-2008
 */
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

public class Form7Model extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(Form7Model.class);
	
	/** This method is used to set up the variables for generating the report.
	 * @param bean - Form7
	 * @param response
	 */
	public void generateReport(Form7 bean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			String fromYr = bean.getFromYear();
			String fromMonth = Utility.month(Integer.parseInt(bean.getFromMonth()));
			String toYr = bean.getToYear();
			String toMonth = Utility.month(Integer.parseInt(bean.getToMonth()));
			
			String periodString = fromMonth + fromYr + " - " +toMonth + toYr;
			
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "Form7_"+bean.getDivAbbr()+"_"+periodString+"_"+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Form 7");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(15);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "Form7_input.action");
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
	
	
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, Form7 bean) {
		try {
			Date date = new Date();
			
			TableDataSet name = new TableDataSet();
			name.setData(new Object[][] { { "(REGULATION 32)" } });
			name.setCellAlignment(new int[] { 1 });
			name.setCellWidth(new int[] { 100 });
			name.setBorderDetail(0);
			name.setBodyFontStyle(1);
			rg.addTableToDoc(name);
			
			/* Setting filter details */
			String filters = fetchFilters(bean);
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			/*
			 * Following query is used to select the division address
			 */
			
			String divQuery = "SELECT NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' ') FROM HRMS_DIVISION WHERE DIV_ID="+bean.getDivId();
			Object divObj[][]=getSqlModel().getSingleResult(divQuery);
			
			/*
			 * Following query is used to select the months and years present between the selected from month,year and to month,year
			 */
			String monYrQuery=" SELECT DISTINCT LEDGER_MONTH,LEDGER_YEAR FROM HRMS_SALARY_LEDGER WHERE TO_DATE(LEDGER_MONTH||'-'||LEDGER_YEAR,'MM-YYYY') "
			+" BETWEEN TO_DATE('"+bean.getFromMonth()+"-"+bean.getFromYear()+"','MM-YYYY') AND TO_DATE('"+bean.getToMonth()+"-"+bean.getToYear()+"','MM-YYYY') ORDER BY LEDGER_YEAR,LEDGER_MONTH ";
			Object monYear[][]=getSqlModel().getSingleResult(monYrQuery);
			/*
			 * Following query is used to select the debit code for the esi 
			 */
			
			String esiCodeQuery="SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy') = (select max(ESI_DATE) FROM HRMS_ESI WHERE to_char(ESI_DATE,'yyyy')<="+bean.getToYear()+")" ;
			Object[][] esi_code = getSqlModel().getSingleResult(esiCodeQuery);
			
			String colName[]={"Sr.\nNo.","Insurance\nNo.","Employee Id","Name","Designation","Date Of\nJoining","If\nappointed\nduring the\ncont.\nperiod\ndate of\nappnt.","No. of\ndays\nfor\nwhich\nwages\npaid/pa\nyable","Total\namount\nof\nwages\npaid/pa\nyable","Employee's\nshare of\ncontrb.","Daily\nwages"};
			int[] cellwidth={10,10,10,25,20,10,12,10,10,10,10};
			int[] align={1,1,1,0,1,1,1,2,2,2,2};
			
			if(monYear.length >0){
				/*
				 * Following loop is used to select the employee name,salary days,e.s.i contribution and the gross salary.
				 */
			for(int i=0;i<monYear.length;i++){
				int s=1;
				double totSalDays=0.00;
				double totEmpContr=0.00;
				double totEmpDaySal=0.00;
				double totSal=0.00;
				String ledgerCode="";
				String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="+String.valueOf(monYear[i][0])+" and ledger_year="+String.valueOf(monYear[i][1]);
				Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
				/*
				 * Following loop calculates the ledger code available for the month and year from HRMS_SALARY_LEDGER table
				 */
				if(ledgerData!=null && ledgerData.length>0){
					for (int j = 0; j < ledgerData.length; j++) {
						if(j==ledgerData.length-1){
							ledgerCode += ledgerData[j][0];
						}
						else{
							ledgerCode += ledgerData[j][0]+",";
						}
					}
				}//End of ledger data loop
				/*
				 * Following query is used to select the emp name,appointment date,esi amount,designation etc.
				 */
				String salQuery="SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(RANK_NAME,'')," +
						 " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),SAL_DAYS,SAL_NET_SALARY,nvl(SAL_AMOUNT,''), " +
						 " HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID ,nvl(HRMS_SALARY_MISC.SAL_ESCINUMBER,'')" +
						 " FROM HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+" INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID " +
						 " INNER JOIN HRMS_SALARY_"+String.valueOf(monYear[i][1])+" ON (HRMS_SALARY_"+String.valueOf(monYear[i][1])+".EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID  " +
					     " AND SAL_DEBIT_CODE="+String.valueOf(esi_code[0][0])+" AND HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".sal_ledger_code in ("+ledgerCode+"))" +
						 " LEFT JOIN HRMS_RANK ON(HRMS_SALARY_"+String.valueOf(monYear[i][1])+".SAL_EMP_RANK=HRMS_RANK.RANK_ID)" +
						 " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID WHERE HRMS_SALARY_"+String.valueOf(monYear[i][1])+".sal_ledger_code in ("+ledgerCode+")" +
						 " AND SAL_DIVISION ="+bean.getDivId()+ " and HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".SAL_AMOUNT > 0 ";//order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				
				if(!(bean.getOnHold().equals("A"))){
					salQuery+=" AND SAL_ONHOLD="+"'"+bean.getOnHold()+"'";
				}
				if(!(bean.getBrnId().equals("") || bean.getBrnId().equals("null"))){
					salQuery+= " AND SAL_EMP_CENTER="+bean.getBrnId();
				}
				
				if(!(bean.getDeptId().equals("") || bean.getDeptId().equals("null"))){
					salQuery+=" and SAL_DEPT="+bean.getDeptId();
				}
				
				if(!(bean.getTypeId().equals("") || bean.getTypeId().equals("null"))){
					
					salQuery+=" and SAL_EMP_TYPE="+bean.getTypeId();
				}
				
				if(!(bean.getPayId().equals("") || bean.getPayId().equals("null"))){
					salQuery+=" AND SAL_EMP_PAYBILL="+bean.getPayId();
				}
				
				salQuery+="ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
				
		
				String st=String.valueOf(monYear[i][0])+"-"+String.valueOf(monYear[i][1]) ;
				Object[][] amount = getSqlModel().getSingleResult(salQuery);
				Object[][] param=new Object[amount.length][11];
				//Object[][] tot=new Object[1][9];
				   for(int k=0;k<amount.length;k++){
					   
					   param[k][0]=s++;//Serial No.
					   param[k][1]=amount[k][8];;//Insurance No.
					   param[k][2]=amount[k][0];;//Employee Id.
					   param[k][3]=amount[k][1];//Employee Name
					   param[k][4]=amount[k][2];//Designation
					   param[k][5]=amount[k][3];;//DOJ
					 
					   String str=String.valueOf(amount[k][3]).substring(3,10);
					   if(st.equals(str)){
						  param[k][6]=amount[k][3];//Date of Appointment
					   }else {
						  param[k][6]="";//Date of Appointment
					   }
					   param[k][7]=amount[k][4];//Salary days         
					   totSalDays+=Math.round(Double.parseDouble(String.valueOf(amount[k][4])));//Total Salary Days
					   param[k][8]=amount[k][5];//Net Salary
					   totSal+=Math.round(Double.parseDouble(String.valueOf(amount[k][5])));//Total Salary
					   param[k][9]=amount[k][6];//Esi Amount
					   totEmpContr+=Math.round(Double.parseDouble(String.valueOf(amount[k][6])));//Total Employee contribution in esi
					   param[k][10]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[k][5]))/Double.parseDouble(String.valueOf(amount[k][4]))));//Daily Wages
					   totEmpDaySal+=Math.round(Double.parseDouble(String.valueOf(param[k][10])));//Total wages per day
						 
					   
				   }
				   
				/*tot[0][0]="";tot[0][1]="";tot[0][2]="";tot[0][3]="";tot[0][4]="Total";
				tot[0][5]=""+Utility.twoDecimals(totSalDays);//Total Salary Days
				tot[0][6]=""+Utility.twoDecimals(totSal);//Total salary 
				tot[0][7]=""+Utility.twoDecimals(totEmpContr);//Total esi contribution made by the emoloyee.
				tot[0][8]=""+Utility.twoDecimals(totEmpDaySal);//Total salary per day by all the employees.*/
				
					if(param!=null && param.length >0){
						
						String label1 = ""+Utility.month(Integer.parseInt(String.valueOf(monYear[i][0])))+"-"+String.valueOf(monYear[i][1]);
						
						TableDataSet table1 = new TableDataSet();
						table1.setData(new Object[][] { { label1 } });
						table1.setCellAlignment(new int[] { 0 });
						table1.setCellWidth(new int[] { 100 });
						table1.setBorderDetail(0);
						table1.setBlankRowsBelow(1);
						table1.setBodyFontStyle(1);
						rg.addTableToDoc(table1);
						
						TableDataSet tableData = new TableDataSet();
						tableData.setHeader(colName);
						tableData.setHeaderBorderDetail(3);
						tableData.setHeaderTable(true);
						tableData.setHeaderFontStyle(1);
						tableData.setData(param);
						tableData.setCellAlignment(align);
						tableData.setCellWidth(cellwidth);
						tableData.setBorderDetail(3);
						tableData.setColumnSum(new int[]{7, 8, 9, 10});
						rg.addTableToDoc(tableData);
						
					}
				
				}//End of monYear for loop
			}//End of monYear if condition
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	private String fetchFilters(Form7 bean) {
		String fromYr = bean.getFromYear();
		String fromMonth = Utility.month(Integer.parseInt(bean.getFromMonth()));
		String toYr = bean.getToYear();
		String toMonth = Utility.month(Integer.parseInt(bean.getToMonth()));
		
		String filters = "Report Period : " +fromMonth +" "+ fromYr + " - " +toMonth+" "+ toYr;
		if (!bean.getDivId().equals("")) {
			filters += "\n\nDivision : " + bean.getDivName();
		}
		if (!bean.getBrnId().equals("")) {
			filters += "\n\nBranch : " + bean.getBrnName();
		}
		if (!bean.getDeptId().equals("")) {
			filters += "\n\nDepartment : " + bean.getDeptName();
		}
		if (!bean.getPayId().equals("")) {
			filters += "\n\nPaybill : " + bean.getPayName();
		}
		if (!bean.getTypeId().equals("")) {
			filters += "\n\nEmployee Type : " + bean.getTypeName();
		}
		return filters;
	}
	
	/*
	 * Following function is called in the action class when view report button is clicked. 
	 */
	public void generateReport(Form7 bean,HttpServletResponse response){
		try{
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Pdf","Form7");
			rg.addTextBold("Form 7", 0,1,0);
			rg.addText("(REGULATION 32)", 0,1,0);
			rg.addText("\n", 0,0,0);
			rg.addText("Contribution Period from "+Utility.month(Integer.parseInt(bean.getFromMonth()))+" "+bean.getFromYear()+" to"+" "+Utility.month(Integer.parseInt(bean.getToMonth()))+" "+bean.getToYear(), 0,2,0);
			
			/*
			 * Following query is used to select the division address
			 */
			String divQuery="SELECT NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' ') FROM HRMS_DIVISION WHERE DIV_ID="+bean.getDivId();
			Object divAddrs[][]=getSqlModel().getSingleResult(divQuery);
			rg.addTextBold("Company Name :"+bean.getDivName()+"\n\nAddress : "+String.valueOf(divAddrs[0][0])+"\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+String.valueOf(divAddrs[0][1])+"\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+String.valueOf(divAddrs[0][2]),0,0,0);
			
			/*
			 * Following query is used to select the months and years present between the selected from month,year and to month,year
			 */
			String monYrQuery=" SELECT DISTINCT LEDGER_MONTH,LEDGER_YEAR FROM HRMS_SALARY_LEDGER WHERE TO_DATE(LEDGER_MONTH||'-'||LEDGER_YEAR,'MM-YYYY') "
			+" BETWEEN TO_DATE('"+bean.getFromMonth()+"-"+bean.getFromYear()+"','MM-YYYY') AND TO_DATE('"+bean.getToMonth()+"-"+bean.getToYear()+"','MM-YYYY') ORDER BY LEDGER_YEAR,LEDGER_MONTH ";
			Object monYear[][]=getSqlModel().getSingleResult(monYrQuery);
			/*
			 * Following query is used to select the debit code for the esi 
			 */
			
			String esiCodeQuery="SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy') = (select max(ESI_DATE) FROM HRMS_ESI WHERE to_char(ESI_DATE,'yyyy')<="+bean.getToYear()+")" ;
			Object[][] esi_code = getSqlModel().getSingleResult(esiCodeQuery);
			
			
			String colName[]={"Sr.\nNo.","Insurance\nNo.","Employee Id","Name","Designation","Date Of\nJoining","If\nappointed\nduring the\ncont.\nperiod\ndate of\nappnt.","No. of\ndays\nfor\nwhich\nwages\npaid/pa\nyable","Total\namount\nof\nwages\npaid/pa\nyable","Employee's\nshare of\ncontrb.","Daily\nwages"};
			int[] cellwidth={10,10,10,25,20,10,12,10,10,10,10};
			int[] align={1,1,1,0,1,1,1,2,2,2,2};
			
			
			if(monYear.length >0){
				/*
				 * Following loop is used to select the employee name,salary days,e.s.i contribution and the gross salary.
				 */
			for(int i=0;i<monYear.length;i++){
				int s=1;
				double totSalDays=0.00;
				double totEmpContr=0.00;
				double totEmpDaySal=0.00;
				double totSal=0.00;
				String ledgerCode="";
				String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="+String.valueOf(monYear[i][0])+" and ledger_year="+String.valueOf(monYear[i][1]);
				Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
				/*
				 * Following loop calculates the ledger code available for the month and year from HRMS_SALARY_LEDGER table
				 */
				if(ledgerData!=null && ledgerData.length>0){
					for (int j = 0; j < ledgerData.length; j++) {
						if(j==ledgerData.length-1){
							ledgerCode += ledgerData[j][0];
						}
						else{
							ledgerCode += ledgerData[j][0]+",";
						}
					}
				}//End of ledger data loop
				/*
				 * Following query is used to select the emp name,appointment date,esi amount,designation etc.
				 */
				String salQuery="SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(RANK_NAME,'')," +
						 " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),SAL_DAYS,SAL_NET_SALARY,nvl(SAL_AMOUNT,''), " +
						 " HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID ,nvl(HRMS_SALARY_MISC.SAL_ESCINUMBER,'')" +
						 " FROM HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+" INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID " +
						 " INNER JOIN HRMS_SALARY_"+String.valueOf(monYear[i][1])+" ON (HRMS_SALARY_"+String.valueOf(monYear[i][1])+".EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID  " +
					     " AND SAL_DEBIT_CODE="+String.valueOf(esi_code[0][0])+" AND HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".sal_ledger_code in ("+ledgerCode+"))" +
						 " LEFT JOIN HRMS_RANK ON(HRMS_SALARY_"+String.valueOf(monYear[i][1])+".SAL_EMP_RANK=HRMS_RANK.RANK_ID)" +
						 " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".EMP_ID WHERE HRMS_SALARY_"+String.valueOf(monYear[i][1])+".sal_ledger_code in ("+ledgerCode+")" +
						 " AND SAL_DIVISION ="+bean.getDivId()+ " and HRMS_SAL_DEBITS_"+String.valueOf(monYear[i][1])+".SAL_AMOUNT > 0 ";//order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
				
				
				if(!(bean.getOnHold().equals("A"))){
					salQuery+=" AND SAL_ONHOLD="+"'"+bean.getOnHold()+"'";
				}
				if(!(bean.getBrnId().equals("") || bean.getBrnId().equals("null"))){
					salQuery+= " AND SAL_EMP_CENTER="+bean.getBrnId();
				}
				
				if(!(bean.getDeptId().equals("") || bean.getDeptId().equals("null"))){
					salQuery+=" and SAL_DEPT="+bean.getDeptId();
				}
				
				if(!(bean.getTypeId().equals("") || bean.getTypeId().equals("null"))){
					
					salQuery+=" and SAL_EMP_TYPE="+bean.getTypeId();
				}
				
				if(!(bean.getPayId().equals("") || bean.getPayId().equals("null"))){
					salQuery+=" AND SAL_EMP_PAYBILL="+bean.getPayId();
				}
				
				salQuery+="ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
				
		
				String st=String.valueOf(monYear[i][0])+"-"+String.valueOf(monYear[i][1]) ;
				Object[][] amount = getSqlModel().getSingleResult(salQuery);
				Object[][] param=new Object[amount.length][11];
				Object[][] tot=new Object[1][9];
				   for(int k=0;k<amount.length;k++){
					   
					   param[k][0]=s++;//Serial No.
					   param[k][1]=amount[k][8];;//Insurance No.
					   param[k][2]=amount[k][0];;//Employee Id.
					   param[k][3]=amount[k][1];//Employee Name
					   param[k][4]=amount[k][2];//Designation
					   param[k][5]=amount[k][3];;//DOJ
					 
					   String str=String.valueOf(amount[k][3]).substring(3,10);
					   if(st.equals(str)){
						  param[k][6]=amount[k][3];//Date of Appointment
					   }else {
						  param[k][6]="";//Date of Appointment
					   }
					   param[k][7]=amount[k][4];//Salary days         
					   totSalDays+=Math.round(Double.parseDouble(String.valueOf(amount[k][4])));//Total Salary Days
					   param[k][8]=amount[k][5];//Net Salary
					   totSal+=Math.round(Double.parseDouble(String.valueOf(amount[k][5])));//Total Salary
					   param[k][9]=amount[k][6];//Esi Amount
					   totEmpContr+=Math.round(Double.parseDouble(String.valueOf(amount[k][6])));//Total Employee contribution in esi
					   param[k][10]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[k][5]))/Double.parseDouble(String.valueOf(amount[k][4]))));//Daily Wages
					   totEmpDaySal+=Math.round(Double.parseDouble(String.valueOf(param[k][10])));//Total wages per day
						 
					   
				   }
				   
				tot[0][0]="";tot[0][1]="";tot[0][2]="";tot[0][3]="";tot[0][4]="Total";
				tot[0][5]=""+Utility.twoDecimals(totSalDays);//Total Salary Days
				tot[0][6]=""+Utility.twoDecimals(totSal);//Total salary 
				tot[0][7]=""+Utility.twoDecimals(totEmpContr);//Total esi contribution made by the emoloyee.
				tot[0][8]=""+Utility.twoDecimals(totEmpDaySal);//Total salary per day by all the employees.
				
				rg.addText("\n",0,0,0);
				if(param!=null && param.length >0)
				rg.addText(""+Utility.month(Integer.parseInt(String.valueOf(monYear[i][0])))+"-"+String.valueOf(monYear[i][1]), 0,0,0);
				/*
				 *Following table displays the employee name,designation,salary days,salary per day,esi contribution details 
				 */
				rg.tableBody(colName, param, cellwidth, align);
				if(totSalDays!=0.00)
					/*
					 * Following table displays the total salary,total salary days,total salry per days,total esi amount
					 */
				rg.tableBody(tot, cellwidth, align);
			
				
				}//End of monYear for loop
				}//End of monYear if condition
				
				rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
}
