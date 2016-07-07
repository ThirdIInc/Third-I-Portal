package org.paradyne.model.payroll.salary;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.salary.SalaryStatement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;


/**
 * @author varunk and Pradeep Kumar Sahoo
 *Date:05-04-2008
 */
public class SalaryStatementModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);	
	/*
	 * Following function is called to generate the Payment Statement Report when Payment Statement Report button is clicked. 
	 */
	public void generateReport(SalaryStatement bean, HttpServletResponse response){		
		String reportType = bean.getReportType();

		String reportName = "Salary Statement";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
		rg.setFName(reportName+"."+reportType);
		try {

			/*
			 * Following query is used to select the employee name,account no.,net salary and employee id
			 */
			String query ="SELECT HRMS_EMP_OFFC.EMP_FNAME||' "+
			"'||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+
			"nvl(SAL_ACCNO_REGULAR,' '),SAL_NET_SALARY,HRMS_SALARY_"+bean.getYear()+".EMP_ID "+ 
			"FROM HRMS_SALARY_"+bean.getYear()+"  "+
			"INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+
			"inner join HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE) "+
			"left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "+ 
			"WHERE SAL_DIVISION="+bean.getDivCode()+" and LEDGER_MONTH="+bean.getMonth()+" and LEDGER_year="+bean.getYear()+" AND SAL_NET_SALARY <>0 " +
			" ";

			if(!(bean.getOnHold().equals("A"))){	
				if(bean.getOnHold().equals("N")){
					query+=" AND SAL_ONHOLD = 'N' ";
				}else if(bean.getOnHold().equals("Y")){
					query+=" AND SAL_ONHOLD = 'Y'";
				}
			}//End of on hold condition
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){

				query+=" and sal_emp_center="+bean.getBrnCode();
			}

			if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
				query+=" and sal_dept="+bean.getDeptCode();

			}
			if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
				query+=" and sal_emp_type="+bean.getTypeCode();

			}
			if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
				query+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
			}

			if(!(bean.getPayMode().equals("A"))){
				query+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
			}

			if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
				query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
			}

			query=query+ "   order by upper(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			Object data[][] = getSqlModel().getSingleResult(query);

			if(data.length>0){

				String MONTH[]={"","January","February","March","April","May","June","July","August","September","October","November","December"};	


				String[] colNames = {"Employee Name", "A/C NO","Amount"};

				int[] cellWidth = {50, 30,20 };

				int[] alignment = { 0, 1, 2};
				Object[][] param=new Object[data.length][3];
				/*
				 * It checks whether check box has been clicked or not.
				 */
				if(bean.getCheckFlag().equals("Y")){
					for(int i=0;i<data.length;i++){
						param[i][0]=data[i][0];//Employee Name
						param[i][1]=data[i][1];//Account No.
						/*
						 * Following query is used to select the total arrear amount paid to an employee.
						 * It will be added to the net salary amount received by the employee.  
						 */
						String arrQuery="SELECT SUM(NVL(ARREARS_NET_AMT,0)) FROM HRMS_ARREARS_"+bean.getYear()+" INNER JOIN HRMS_ARREARS_LEDGER "
						+" ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
						+" WHERE EMP_ID="+data[i][3]+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" AND ARREARS_TYPE IN ('M','P') AND ARREARS_PAY_IN_SAL = 'Y' ";
						Object[][] arrearAmt=getSqlModel().getSingleResult(arrQuery);

						if(arrearAmt!=null || arrearAmt.length!=0){
							if(!(String.valueOf(arrearAmt[0][0]).equals("") || String.valueOf(arrearAmt[0][0]).equals("null"))){
								param[i][2]=Integer.parseInt(String.valueOf(arrearAmt[0][0]))+Integer.parseInt(String.valueOf(data[i][2]));
							}else {
								param[i][2]=data[i][2];//Amount

							}

						}//End of arrearAmt if condition




					}//End of for loop data
					/*
					 *End of check flag if condition
					 */
				}   else {
					/*
					 * Following query is used to set the employee name,account no. and amount values
					 */
					for(int i=0;i<data.length;i++){
						param[i][0]=data[i][0];//Employee Name
						param[i][1]=data[i][1];//Account No.
						param[i][2]=data[i][2];//Amount
					}


				}

				if(reportType.equals("Pdf")){	
					rg.addTextBold("Payment Statement for "+MONTH[Integer.parseInt(bean.getMonth())]+"-"+bean.getYear()+"", 0, 1, 0);
				}else {
					Object [][] title = new Object[1][3];
					title [0][0] = "";
					title [0][1] = "";
					title [0][2] = "Payment Statement for "+MONTH[Integer.parseInt(bean.getMonth())]+"-"+bean.getYear()+"";  

					int [] cols = {20,20,40};
					int [] align = {0,0,1};
					rg.tableBodyNoCellBorder(title,cols,align,1); 
				}
				rg.addText("\n",0,0,0);
				if(param!=null || param.length!=0){
					rg.tableBody(colNames, param, cellWidth, alignment);		
				}
				rg.addText("\n\n",0,0,0);
			}//End of data if condition
			else{


				rg.addText("Records are not available.", 0, 1, 0);
				rg.createReport(response);

			}

			/*
			 * fOLLOWING QUERY IS USED TO GENERATE THE ARREAR DETAILS added on Date:22-07-2008
			 */

			/*
			 * If check box is not selected arrears report will be generated otherwise will not be generated.
			 */
			if(bean.getCheckFlag().equals("N")){
				try{
					/*
					 *Following query will first find the emp id and then these emp id will be passed in the for loop to find out the arrears amount,employee name,arrear month,year etc.  
					 */
					String arrEmpIdQuery=" SELECT HRMS_ARREARS_"+bean.getYear()+".EMP_ID,HRMS_ARREARS_LEDGER.ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR ,ARREARS_PROMCODE FROM HRMS_SALARY_"+bean.getYear()
					+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"+bean.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE AND LEDGER_MONTH="+bean.getMonth()+" AND LEDGER_YEAR="+bean.getYear()+")"
					+" INNER JOIN HRMS_ARREARS_"+bean.getYear() +" ON(HRMS_ARREARS_"+bean.getYear() +".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
					+" left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+" WHERE SAL_DIVISION="+bean.getDivCode()+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" and arrears_type IN ('M','P') AND ARREARS_PAY_IN_SAL = 'Y' ";

					if(!(bean.getOnHold().equals("A"))){	
						if(bean.getOnHold().equals("N")){
							arrEmpIdQuery+=" AND SAL_ONHOLD = 'N' ";
						}
						else if(bean.getOnHold().equals("Y")){
							arrEmpIdQuery+=" AND SAL_ONHOLD = 'Y'";
						}
					}
					if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){

						arrEmpIdQuery+=" and sal_emp_center="+bean.getBrnCode();
					}

					if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
						arrEmpIdQuery+=" and sal_dept="+bean.getDeptCode();

					}
					if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
						arrEmpIdQuery+=" and sal_emp_type="+bean.getTypeCode();

					}
					if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
						arrEmpIdQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
					}

					if(!(bean.getPayMode().equals("A"))){
						arrEmpIdQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
					}
					if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
						query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
					}

					Object param[][] = getSqlModel().getSingleResult(arrEmpIdQuery);
					Object arrearAmt[][]=new Object[param.length][5];
					if(param!=null || param.length>0){	

						for(int i=0;i<param.length;i++){
							/*
							 * Following query will be used to find the name of the employee
							 */
							String arrearQuery="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ARREARS_MONTH,ARREARS_YEAR,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional'),ARREARS_NET_AMT FROM HRMS_ARREARS_"+bean.getYear()
							+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+bean.getYear()+".EMP_ID)" 
							+" WHERE ARREARS_MONTH="+param[i][2]+" AND ARREARS_YEAR="+param[i][3]+" AND HRMS_ARREARS_"+bean.getYear()+".EMP_ID="+param[i][0]+" and HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE="+param[i][1]+" AND ARREARS_PAY_IN_SAL = 'Y' ";//+" AND ARREARS_PROMCODE="+param[i][4];

							if(!(String.valueOf(param[i][4]).equals("null") || String.valueOf(param[i][4]).equals(""))){
								arrearQuery+=" AND ARREARS_PROMCODE="+param[i][4];
							}
							Object[][] arrear= getSqlModel().getSingleResult(arrearQuery);
							if(arrear!=null || arrear.length!=0){
								arrearAmt[i][0]=arrear[0][0];//Employee Name
								arrearAmt[i][1]=Utility.month(Integer.parseInt(String.valueOf(arrear[0][1])));//Arrear Month
								arrearAmt[i][2]=arrear[0][2];//Arrear Year
								arrearAmt[i][3]=arrear[0][3];//Arrear Type
								arrearAmt[i][4]=arrear[0][4];//Arrear Amount
							}
						}
					}//End of the param if condition

					String []colArr={"Employee Name","Arrear Month","Arrear Year","Arrear Type","Arrear Amount"};
					int[] cellWidthArr={30,25,25,20,20}; 
					int[] arrAlignment={0,1,1,1,2};
					if(arrearAmt!=null && arrearAmt.length!=0){
						rg.addText("Arrears", 0,0,0);
						/*
						 * Following table is used to find out the arrear details.
						 */
						rg.tableBody(colArr, arrearAmt, cellWidthArr, arrAlignment);
					}
				}catch(Exception e){//End of second try and catch block.
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}//End of the check flag condition.

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		//return true;
		rg.createReport(response);

	}
	/*
	 * Following function is called to generate the covering letter when Covering letter button is clicked.
	 */
	public void getCovReport(SalaryStatement bean,HttpServletResponse response) throws Exception
	{
		try{	
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Pdf", "Payment Statement Covering Letter");
			rg.setFName("Payment Statement Covering Letter.Pdf");
			String cheque=bean.getChq();
			String date=bean.getChqDate();
			String dd=null;
			String mm=null;
			String yy=null;
			String mon=null;
			int amt;
			String amount;
			double sal;
			if(!(date.equals("") || date==null)){
				dd=bean.getChqDate().substring(0,2);
				mm=bean.getChqDate().substring(3,5);
				yy=bean.getChqDate().substring(6,10);


				if(mm.equals("01")){
					mon="January";

				}else if(mm.equals("02")){
					mon="February";

				}else if(mm.equals("03")){
					mon="March";

				}else if(mm.equals("04")){
					mon="April";

				}else if(mm.equals("05")){
					mon="May";

				}else if(mm.equals("06")){
					mon="June";

				}else if(mm.equals("07")){
					mon="July";

				}else if(mm.equals("08")){
					mon="August";

				}else if(mm.equals("09")){
					mon="September";

				}else if(mm.equals("10")){
					mon="October";

				}else if(mm.equals("11")){
					mon="November";

				}else if(mm.equals("12")){
					mon="December";

				}

			}else {

				dd="";
				mm="";
				yy="";
				mon="";
			}
			String curDateQuery=" SELECT TO_CHAR(SYSDATE,'MonthDD,YYYY') FROM DUAL";
			Object[][] dt=getSqlModel().getSingleResult(curDateQuery);

			String query=" SELECT NVL(BANK_NAME,' '),NVL(BRANCH_ADDRESS,' '),NVL(HRMS_DIVISION.DIV_ACCOUNT_NUMBER,' ') FROM HRMS_BANK INNER JOIN HRMS_DIVISION "
				+" ON(HRMS_BANK.BANK_MICR_CODE=HRMS_DIVISION.DIV_BANK) "
				+" WHERE DIV_ID= "+bean.getDivCode();
			Object div[][]=getSqlModel().getSingleResult(query);
			if(div==null || div.length <=0){
				rg.addText("Salry has not been processed for this Division.",0,1,0);
				rg.createReport(response);
			}

			String salQuery="  SELECT NVL(SUM(SAL_NET_SALARY),0) FROM HRMS_SALARY_"+bean.getYear()
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+bean.getYear()+".EMP_ID)"
			+" INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID) " +

			" inner join HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE)"
			+" left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+" WHERE SAL_DIVISION="+bean.getDivCode()+" and LEDGER_MONTH="+bean.getMonth()+" and LEDGER_year="+bean.getYear()+"  AND  SAL_NET_SALARY <>0 ";
			if(!(bean.getOnHold().equals("A"))){
				salQuery+="and sal_onhold='"+bean.getOnHold()+"' " ;
			}
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){
				salQuery+=" and sal_emp_center="+bean.getBrnCode();
			}

			if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
				salQuery+=" and sal_dept="+bean.getDeptCode();

			}
			if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
				salQuery+=" and sal_emp_type="+bean.getTypeCode();

			}
			if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
				salQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
			}

			if(!(bean.getPayMode().equals("A"))){
				salQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
			}

			if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
				query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
			}


			Object salary[][]=getSqlModel().getSingleResult(salQuery);
			if(salary==null || salary.length <=0){
				rg.addText("Records are not available.",0,1,0);
				rg.createReport(response);
			}

			if(bean.getCheckFlag().equals("Y")){
				/*
				 * If check box will be checked then the total amount will be:salary amount+arrears amount
				 */
				String arrQuery=	
					"  SELECT NVL(SUM(ARREARS_NET_AMT),0) FROM HRMS_SALARY_"+bean.getYear()+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"+bean.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE AND LEDGER_MONTH="+bean.getMonth()+" AND LEDGER_YEAR="+bean.getYear()+") "
					+" INNER JOIN HRMS_ARREARS_"+bean.getYear()+" ON(HRMS_ARREARS_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
					+" left join HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+" WHERE SAL_DIVISION="+bean.getDivCode()+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" and arrears_type IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y'  ";

				if(!(bean.getOnHold().equals("A"))){
					arrQuery+="and sal_onhold='"+bean.getOnHold()+"' " ;
				}
				if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){
					arrQuery+=" and sal_emp_center="+bean.getBrnCode();
				}

				if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
					arrQuery+=" and sal_dept="+bean.getDeptCode();

				}
				if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
					arrQuery+=" and sal_emp_type="+bean.getTypeCode();

				}
				if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
					arrQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
				}

				if(!(bean.getPayMode().equals("A"))){
					arrQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
				}

				Object arrAmt[][]=getSqlModel().getSingleResult(arrQuery);
				if(arrAmt==null || arrAmt.length==0){
					amt=Integer.parseInt(String.valueOf(salary[0][0]))+Integer.parseInt(String.valueOf("0"));
					amount=org.paradyne.lib.Utility.convert(amt);
					sal=Double.parseDouble(String.valueOf(salary[0][0]))+Double.parseDouble(String.valueOf("0"));
				}else{
					amt=Integer.parseInt(String.valueOf(salary[0][0]))+Integer.parseInt(String.valueOf(arrAmt[0][0]));
					amount=org.paradyne.lib.Utility.convert(amt);
					sal=Double.parseDouble(String.valueOf(salary[0][0]))+Double.parseDouble(String.valueOf(arrAmt[0][0]));
				}		

			}else {
				amt=Integer.parseInt(String.valueOf(salary[0][0]));
				amount=org.paradyne.lib.Utility.convert(amt);
				sal=Double.parseDouble(String.valueOf(salary[0][0]));
			}





			String []st={String.valueOf(dt[0][0])};
			int[] style={0};
			rg.addText("\n\n\n\n\n\n\n\n",0,0,0);
			rg.addFormatedText(st, style, 0,0,0);
			rg.addText("\n",0,0,0);	
			String []msg={"To,\n"+String.valueOf(div[0][0])+",\n"+String.valueOf(div[0][1])};
			int[] style1={0};
			rg.addFormatedText(msg, style1,0,0,0);
			rg.addText("\n\n",0,0,0);


			String []msg1={"\t\t\t\tSub :- Our Current A/c No.  "+String.valueOf(div[0][2])};
			int[] style2={2};
			rg.addFormatedText(msg1, style2, 0,1,0);
			rg.addText("\n\n",0,0,0);

			String []msg3={"Dear Sir,\n\n\t\t\t\t\t\t\t\t\tPlease find enclosed our cheque number "+cheque+" dated "+mon+" "+dd+" ,"+yy+" drawn on your bank for Rs. "+String.valueOf(sal)+" "+"(Rupees " 
					+amount+ ").\n\n\t\t\t\t\t\t\t\t\tWe request you to kindly debit our above captioned account for the said amount   and  credit  the  proceeds  to  our  employees salary  accounts maintained with you "
					+" as per the enclosed list."};
			int style3[]={0};
			rg.addFormatedText(msg3, style3, 0,0,0);

			rg.addText("\n\n\n",0,0,0);
			String []msg4={"Thanking you,"};
			int[] style4={0};
			rg.addFormatedText(msg4, style4, 0,0,0);
			rg.addText("\n\n",0,0,0);
			String []msg5={"Yours faithfully\nFor  "+bean.getDivName()};
			int []style5={0};
			rg.addFormatedText(msg5, style5, 0,0,0);

			rg.addText("\n\n\n",0,0,0);

			String []msg6={"Authorised Signatories"};
			int []style6={0};
			rg.addFormatedText(msg6, style6, 0,0,0);
			rg.addText("\n\n",0,0,0);

			String []msg7={"Encl  :\t\t\t1) List of Employees with saving A/c Nos.\n\t\t\t\t\t\t\t\t\t\t\t\t\t2) Cheque for Rs. "+String.valueOf(salary[0][0])};
			int []style7={0};
			rg.addFormatedText(msg7, style7, 0,0,0);
			rg.createReport(response);

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}	
	/*
	 * Following function is called to generate the bank statement report in xls or pdf format
	 * Added on 13-08-2008 by Pradeep
	 */
	public void getBankStatement(SalaryStatement bean,HttpServletResponse response) {
		String reportType = "Xls";
		String reportName = "Bank Statement";
		try{
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
			rg.setFName(reportName+"."+reportType);

			String query ="SELECT nvl(SAL_ACCNO_REGULAR,' '),'C',NVL(SAL_NET_SALARY,0),EMP_FNAME||' "+
			"'||EMP_MNAME||' '||EMP_LNAME, "+
			"nvl(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_SALARY_"+bean.getYear()+".EMP_ID, "+ 
			" NVL(BANK_IFSC_CODE,' '), EMP_TOKEN,BANK_NAME  "+
			"FROM HRMS_SALARY_"+bean.getYear()+"  "+
			"INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+
			"inner join HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE) "+
			"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "+
			"left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "+ 
			"left join HRMS_BANK on (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "+  
			"WHERE SAL_DIVISION="+bean.getDivCode()+" and LEDGER_MONTH="+bean.getMonth()+" and LEDGER_year="+bean.getYear()+" AND SAL_NET_SALARY >0 " +
			" ";

			if(!(bean.getOnHold().equals("A"))){	
				if(bean.getOnHold().equals("N")){
					query+=" AND SAL_ONHOLD = 'N' ";
				}
				else if(bean.getOnHold().equals("Y")){
					query+=" AND SAL_ONHOLD = 'Y'";
				}
			}
						
			
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){

				query+=" and sal_emp_center="+bean.getBrnCode();
			}

			if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
				query+=" and sal_dept="+bean.getDeptCode();

			}
			if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
				query+=" and sal_emp_type="+bean.getTypeCode();

			}
			if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
				query+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
			}

			if(!(bean.getPayMode().equals("A"))){
				query+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
			}
			if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
				query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
			}
			//query=query+ "   order by upper(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			query=query+ "   order by BANK_MICR_CODE,upper(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			Object data[][] = getSqlModel().getSingleResult(query);
			Object[][] param=new Object[data.length][8];
			if(!(data==null || data.length==0)){
				for(int i=0;i<data.length;i++){
					param[i][0]=" "+data[i][6]+" "; //IFSC CODE
					param[i][1]=" "+data[i][0]+" "; //Account No.
					param[i][2]=data[i][1];//Credit
					/*
					 * If consolidated arrears is checked then arrears(total) amount will be added to the salary(total) amount. 
					 */
					Object[][] arrearAmt = null;
					if(bean.getCheckFlag().equals("Y")){
						String arrQuery="SELECT SUM(NVL(ARREARS_NET_AMT,0)) FROM HRMS_ARREARS_"+bean.getYear()+" INNER JOIN HRMS_ARREARS_LEDGER "
						+" ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
						+" WHERE EMP_ID="+data[i][5]+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" AND ARREARS_TYPE IN ('M','P') AND ARREARS_PAY_IN_SAL = 'Y' ";
						arrearAmt=getSqlModel().getSingleResult(arrQuery);
						if(!(arrearAmt==null || arrearAmt.length==0)){
							if(String.valueOf(arrearAmt[0][0]).equals("") || String.valueOf(arrearAmt[0][0]).equals("null")){
								arrearAmt[0][0]=0;
							}
							param[i][3]=Double.parseDouble(String.valueOf(data[i][2]))+Double.parseDouble(String.valueOf(arrearAmt[0][0]));				
						}
					}else{
						param[i][3]=data[i][2];//Amount
					}
					param[i][4] = String.valueOf(data[i][7]); // employee code
					Object extraObj[][]=null;
					String benQuery="SELECT SUM(AMOUNT),EMPID FROM "
						+" (SELECT  CREDIT_CODE , SUM(CREDIT_AMOUNT) AMOUNT, "
						+" HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID EMPID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH,"
						+"  HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR "
						+" FROM HRMS_EXTRAWORK_PROCESS_CREDITS "
						+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR "
						+" ON(HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE)"
						+" WHERE EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH="+bean.getMonth()+"  AND EXTRAWORK_INCLUDE_SAL_YEAR="+bean.getYear()
						+" AND HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID="+data[i][5]
						+" GROUP BY CREDIT_CODE,HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH, "
						+"  HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR ) "
						+" GROUP BY EMPID  HAVING EMPID="+data[i][5];
		
			try{	
			extraObj=getSqlModel().getSingleResult(benQuery);						
			if(extraObj!=null && extraObj.length>0)
			{
				logger.info("when benfit amount added."+extraObj[0][0]);
				param[i][3]=Double.parseDouble(String.valueOf(param[i][3]))+Double.parseDouble(String.valueOf(extraObj[0][0]));
			}else{
				// do nothing..!
				logger.info("when no benfit was added............!!");
			}
		}catch(Exception ee)
		{
			logger.info("Exception is raised when adding extra benfit amount."+ee);
		}
		
		try {
			if (Double.parseDouble(String.valueOf(param[i][3])) < 0) {
				param[i][3] = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		param[i][5]=data[i][3];//Name
					String str="";
					if(!String.valueOf(data[i][0]).equals(" ")){
						str+=String.valueOf(data[i][0])+","+String.valueOf(data[i][1]);
					}else {
						str+=String.valueOf(data[i][1]);
					}
						/*   commented by sai instead of this 
					if(!String.valueOf(data[i][2]).equals("")){
						if(bean.getCheckFlag().equals("Y") && arrearAmt != null && arrearAmt.length > 0)
						str+=","+(Double.parseDouble(String.valueOf(data[i][2]))
						+Double.parseDouble(String.valueOf(arrearAmt[0][0])));
						else
							str+=","+String.valueOf(data[i][2]);
					}
					*/
					
					str+=","+String.valueOf(param[i][3]);

					if(!String.valueOf(data[i][3]).equals("")){
						str+=","+String.valueOf(data[i][3]);
					}

					param[i][6]=str;//Concat of account,credit,amount and name. 
					param[i][7]=String.valueOf(String.valueOf(data[i][8]));

				}
				
				String month=Utility.month(Integer.parseInt(bean.getMonth()));
				String colName[]={"IFSC Code","Account","Credit","Amount","Employee Code","Narration","Account,Credit,Amount,Narration","Bank Name"};	
				int[] align={0,1,1,2,0,0,0,0};
				int[] cellWidth={10,12,7,10,10,21,40,20};
				Object [][] title = new Object[1][3];
				title [0][0] = "";
				title [0][1] = "";
				title [0][2] = "Bank Statement for the Division "+bean.getDivName()+" ,"+month+" - "+bean.getYear();  

				int [] cols1 = {20,20,40};
				int [] align1 = {0,0,1};
				/*
				 * Following table shows the heading of the bank statement report
				 */
				rg.tableBodyNoCellBorder(title,cols1,align1,1); 
				rg.addText("\n",0,0,0);
				/*
				 * Following table shows the bank statement report  details in xls format
				 */
				rg.xlsTableBody(colName, param, cellWidth, align);
			}else {
				rg.addText("Records are not available. ", 0,0,10);
				rg.createReport(response);
			}

			/*
			 * When consolidated arrears is not checked this report displays the arrears and salary details separately . 
			 */
			if(bean.getCheckFlag().equals("N")){
				/*
				 * Following query is used to select the employee name,arrear month,year,arrear type,arrear net amount. 
				 */
				String totArrAmtQuery="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_ARREARS_"+bean.getYear()+".ARREARS_MONTH,HRMS_ARREARS_"+bean.getYear()+".ARREARS_YEAR"
				+" ,ARREARS_DAYS,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional'),NVL(ARREARS_NET_AMT,0) FROM HRMS_ARREARS_"+bean.getYear()+" "
				+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+bean.getYear()+".EMP_ID) "
				+" INNER JOIN HRMS_SALARY_"+bean.getYear()+" ON(HRMS_SALARY_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" INNER JOIN HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE) "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
				+" WHERE SAL_DIVISION="+bean.getDivCode()+" AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+bean.getMonth()+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+bean.getYear()+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" AND ARREARS_PAY_IN_SAL = 'Y'";

				if(!(bean.getBrnCode().equals("") || bean.getBrnCode().equals("null"))){
					totArrAmtQuery+=" AND SAL_EMP_CENTER="+bean.getBrnCode();
				}
				if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
					totArrAmtQuery+=" AND SAL_DEPT="+bean.getDeptCode();
				}
				if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
					totArrAmtQuery+=" AND SAL_EMP_TYPE="+bean.getTypeCode();

				}
				if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
					totArrAmtQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
				}

				if(!(bean.getPayMode().equals("A"))){
					totArrAmtQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
				}

				if(!(bean.getOnHold().equals("A"))){
					totArrAmtQuery+=" AND SAL_ONHOLD="+"'"+bean.getOnHold()+"'";
				}

				if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
					query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
				}

				totArrAmtQuery+=" ORDER BY HRMS_ARREARS_"+bean.getYear()+".ARREARS_MONTH,HRMS_ARREARS_"+bean.getYear()+".ARREARS_YEAR ";

				Object[][] arrearData=getSqlModel().getSingleResult(totArrAmtQuery);
				if(arrearData!=null && arrearData.length!=0)
				{
					Object[][] arrearsDtl=new Object[arrearData.length][7];
					/*
					 * Following loop sets the arrear details to the array arrearsDtl
					 */
					for(int i=0;i<arrearData.length;i++){
						arrearsDtl[i][0]=arrearData[i][0];
	
						arrearsDtl[i][0]=arrearData[i][0];//Name
						arrearsDtl[i][1]=Utility.month(Integer.parseInt(String.valueOf(arrearData[i][1])));
						String str=Utility.month(Integer.parseInt(String.valueOf(arrearData[i][1])));
						arrearsDtl[i][2]=arrearData[i][2];//Arrear Month
						arrearsDtl[i][3]=arrearData[i][3];//Arrear Days
						arrearsDtl[i][4]=arrearData[i][4];//Arrear Year
						arrearsDtl[i][5]=arrearData[i][5];//Arrear Type
						arrearsDtl[i][6]=String.valueOf(arrearData[i][0])+","+str+","+String.valueOf(arrearData[i][2])+","+String.valueOf(arrearData[i][3])+
						","+String.valueOf(arrearData[i][4])+","+String.valueOf(arrearData[i][5]);	
					}
					String[] cols={"Name","Month","Year","Days","Type","Amount","Name,Month,Year,Days,Type,Amount"};
					int[] width={15,10,8,12,10,10,40};
					int[] align={0,0,1,1,0,2,0};
					rg.addText("\n", 0,1,10);
					/*
					 * If arrears data is not null and arrears data length is not zero then the following table displays the arrears 
					 */
					rg.addText("Arrears", 0,1,10);
					rg.xlsTableBody(cols,arrearsDtl,width,align);
				}
			}
            	rg.createReport(response);

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
	
	// this method is returning total salary amount value and date which will be useful for template.
	public String[] getCovReportAmount(SalaryStatement bean) throws Exception
	{
		String []myobj=new String[4];
		try{	
		
			myobj[0]="0";
			myobj[1]="0";
			myobj[2]="0";
			myobj[3]="0";
			String cheque=bean.getChq();
			String date=bean.getChqDate();
			String dd=null;
			String mm=null;
			String yy=null;
			String mon=null;
			int amt;
			String amount;
			double sal;
			if(!(date.equals("") || date==null)){
				dd=bean.getChqDate().substring(0,2);
				mm=bean.getChqDate().substring(3,5);
				yy=bean.getChqDate().substring(6,10);


				if(mm.equals("01")){
					mon="January";

				}else if(mm.equals("02")){
					mon="February";

				}else if(mm.equals("03")){
					mon="March";

				}else if(mm.equals("04")){
					mon="April";

				}else if(mm.equals("05")){
					mon="May";

				}else if(mm.equals("06")){
					mon="June";

				}else if(mm.equals("07")){
					mon="July";

				}else if(mm.equals("08")){
					mon="August";

				}else if(mm.equals("09")){
					mon="September";

				}else if(mm.equals("10")){
					mon="October";

				}else if(mm.equals("11")){
					mon="November";

				}else if(mm.equals("12")){
					mon="December";

				}

			}else {

				dd="";
				mm="";
				yy="";
				mon="";
			}
			String curDateQuery=" SELECT TO_CHAR(SYSDATE,'MonthDD,YYYY') FROM DUAL";
			Object[][] dt=getSqlModel().getSingleResult(curDateQuery);

			String query=" SELECT NVL(BANK_NAME,' '),NVL(BRANCH_ADDRESS,' '),NVL(HRMS_DIVISION.DIV_ACCOUNT_NUMBER,' ') FROM HRMS_BANK INNER JOIN HRMS_DIVISION "
				+" ON(HRMS_BANK.BANK_MICR_CODE=HRMS_DIVISION.DIV_BANK) "
				+" WHERE DIV_ID= "+bean.getDivCode();
			Object div[][]=getSqlModel().getSingleResult(query);
			if(div==null || div.length <=0){
				/*rg.addText("Salry has not been processed for this Division.",0,1,0);
				rg.createReport(response);*/
				
				myobj[0]="1";
			}

			String salQuery="  SELECT NVL(SUM(SAL_NET_SALARY),0) FROM HRMS_SALARY_"+bean.getYear()
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+bean.getYear()+".EMP_ID)"
			+" INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID) " +

			" inner join HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE)"
			+" left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+" WHERE SAL_DIVISION="+bean.getDivCode()+" and LEDGER_MONTH="+bean.getMonth()+" and LEDGER_year="+bean.getYear()+"  AND  SAL_NET_SALARY <>0 ";
			if(!(bean.getOnHold().equals("A"))){
				salQuery+="and sal_onhold='"+bean.getOnHold()+"' " ;
			}
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){
				salQuery+=" and sal_emp_center="+bean.getBrnCode();
			}

			if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
				salQuery+=" and sal_dept="+bean.getDeptCode();

			}
			if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
				salQuery+=" and sal_emp_type="+bean.getTypeCode();

			}
			if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
				salQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
			}

			if(!(bean.getPayMode().equals("A"))){
				salQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
			}

			if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
				query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
			}


			Object salary[][]=getSqlModel().getSingleResult(salQuery);
			if(salary==null || salary.length <=0){
			//	rg.addText("Records are not available.",0,1,0);
				//rg.createReport(response);
			}

			if(bean.getCheckFlag().equals("Y")){
				/*
				 * If check box will be checked then the total amount will be:salary amount+arrears amount
				 */
				String arrQuery=	
					"  SELECT NVL(SUM(ARREARS_NET_AMT),0) FROM HRMS_SALARY_"+bean.getYear()+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_SALARY_"+bean.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE AND LEDGER_MONTH="+bean.getMonth()+" AND LEDGER_YEAR="+bean.getYear()+") "
					+" INNER JOIN HRMS_ARREARS_"+bean.getYear()+" ON(HRMS_ARREARS_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
					+" left join HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+" WHERE SAL_DIVISION="+bean.getDivCode()+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" and arrears_type IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y'  ";

				if(!(bean.getOnHold().equals("A"))){
					arrQuery+="and sal_onhold='"+bean.getOnHold()+"' " ;
				}
				if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){
					arrQuery+=" and sal_emp_center="+bean.getBrnCode();
				}

				if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
					arrQuery+=" and sal_dept="+bean.getDeptCode();

				}
				if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
					arrQuery+=" and sal_emp_type="+bean.getTypeCode();

				}
				if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
					arrQuery+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
				}

				if(!(bean.getPayMode().equals("A"))){
					arrQuery+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
				}

				Object arrAmt[][]=getSqlModel().getSingleResult(arrQuery);
				if(arrAmt==null || arrAmt.length==0){
					amt=Integer.parseInt(String.valueOf(salary[0][0]))+Integer.parseInt(String.valueOf("0"));
					amount=org.paradyne.lib.Utility.convert(amt);
					sal=Double.parseDouble(String.valueOf(salary[0][0]))+Double.parseDouble(String.valueOf("0"));
				}else{
					amt=Integer.parseInt(String.valueOf(salary[0][0]))+Integer.parseInt(String.valueOf(arrAmt[0][0]));
					amount=org.paradyne.lib.Utility.convert(amt);
					sal=Double.parseDouble(String.valueOf(salary[0][0]))+Double.parseDouble(String.valueOf(arrAmt[0][0]));
				}		

			}else {
				amt=Integer.parseInt(String.valueOf(salary[0][0]));
				amount=org.paradyne.lib.Utility.convert(amt);
				sal=Double.parseDouble(String.valueOf(salary[0][0]));
			}

			try {
				amt += totalDivisionExtraBenfitAmount(bean);
				amount = org.paradyne.lib.Utility.convert(amt);
				sal += totalDivisionExtraBenfitAmount(bean);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("ERROR in adding extra benfit error "+e);
			}
			
			
			myobj[1]=" "+cheque+" dated "+mon+" "+dd+" , "+yy;
			myobj[2]=" "+org.paradyne.lib.Utility.twoDecimals(""+sal);
			myobj[3]=" "+amount;
			myobj[4]=" ";
			

		}catch(Exception e){
			//e.printStackTrace();
			//logger.error(e.getMessage());
		}

		return myobj;
	}
	
	
	
	// this method for generating bank statement in text format!
	public void bankStatementView(SalaryStatement bean, HttpServletRequest request, HttpServletResponse response, String filepath) {
		
		String reportName = "Bank Statement";
		String monthandyear=Utility.month(Integer.parseInt(bean.getMonth()))+" "+bean.getYear();
		Object bankstObj[][]=new Object[1][1];
		Object data[][]=null;
		String myfile="";    // generating the bank statement  with this name.
		if(bean.getDivCode()!=null)
			myfile=bean.getMonth()+bean.getYear()+bean.getDivCode();
		
		
		try{
			
			           
			String query ="SELECT nvl(SAL_ACCNO_REGULAR,' '),'C',NVL(SAL_NET_SALARY,0.0),EMP_FNAME||' "+
			"'||EMP_MNAME||' '||EMP_LNAME, "+
			"nvl(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_SALARY_"+bean.getYear()+".EMP_ID "+ 
			"FROM HRMS_SALARY_"+bean.getYear()+"  "+
			"INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+bean.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+
			"inner join HRMS_SALARY_LEDGER on (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getYear()+".SAL_LEDGER_CODE) "+
			"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "+
			"left join HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "+ 
			"left join HRMS_BANK on (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_REIMBANK) "+  
			"WHERE SAL_DIVISION="+bean.getDivCode()+" and LEDGER_MONTH="+bean.getMonth()+" and LEDGER_year="+bean.getYear()+" AND SAL_NET_SALARY <>0 " +
			" ";

			if(!(bean.getOnHold().equals("A"))){	
				if(bean.getOnHold().equals("N")){
					query+=" AND SAL_ONHOLD = 'N' ";
				}
				else if(bean.getOnHold().equals("Y")){
					query+=" AND SAL_ONHOLD = 'Y'";
				}
			}
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode()==null)){

				query+=" and sal_emp_center="+bean.getBrnCode();
			}

			if(!(bean.getDeptCode().equals("") || bean.getDeptCode()==null)){
				query+=" and sal_dept="+bean.getDeptCode();

			}
			if(!(bean.getTypeCode().equals("") || bean.getTypeCode()==null)){
				query+=" and sal_emp_type="+bean.getTypeCode();

			}
			if(!(bean.getPayBillNo().equals("") || bean.getPayBillNo()==null)){
				query+=" and SAL_EMP_PAYBILL="+bean.getPayBillNo();
			}

			if(!(bean.getPayMode().equals("A"))){
				query+=" AND SAL_PAY_MODE="+"'"+bean.getPayMode()+"'";
			}
			if(!(bean.getBankName().equals("") || bean.getBankName()==null)){
				query+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR="+bean.getBankCode();
			}



			query=query+ "   order by upper(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			data= getSqlModel().getSingleResult(query);
			
			Object[][] param=new Object[data.length][5];



			if(!(data==null || data.length==0)){
			  // salary details available  (salary was process...!)
				 bankstObj=new Object[data.length][1];
				 
				 
				for(int i=0;i<data.length;i++){
					param[i][0]=data[i][0];//Account No.
					param[i][1]=data[i][1];//Credit
					/*
					 * If consolidated arrears is checked then arrears(total) amount will be added to the salary(total) amount. 
					 */
					Object[][] arrearAmt = null;
					
						String arrQuery="SELECT SUM(NVL(ARREARS_NET_AMT,0)) FROM HRMS_ARREARS_"+bean.getYear()+" INNER JOIN HRMS_ARREARS_LEDGER "
						+" ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+bean.getYear()+".ARREARS_CODE)"
						+" WHERE EMP_ID="+data[i][5]+" AND ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+bean.getYear()+" AND ARREARS_TYPE IN ('M','P') AND ARREARS_PAY_IN_SAL='Y'";
						arrearAmt=getSqlModel().getSingleResult(arrQuery);
						logger.info("arrQuery available...!"+arrQuery);
						if(!(arrearAmt==null && arrearAmt.length==0)){
							logger.info("arrears available...!");
							if(String.valueOf(arrearAmt[0][0]).equals("") || String.valueOf(arrearAmt[0][0]).equals("null")){
								arrearAmt[0][0]=0;
							}
							param[i][2]=Double.parseDouble(String.valueOf(data[i][2]))+Double.parseDouble(String.valueOf(arrearAmt[0][0]));				
						}
					else{
						logger.info("arrears not available ...!");
						param[i][2]=data[i][2];//Amount
					}

					// Logic for adding extra Beneficiaries amount to the salary amount+arrears amout.
						
						// data[i][5] -emp id
						
						String benQuery="SELECT SUM(AMOUNT),EMPID FROM "
									+" (SELECT  CREDIT_CODE , SUM(CREDIT_AMOUNT) AMOUNT, "
									+" HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID EMPID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH,"
									+"  HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR "
									+" FROM HRMS_EXTRAWORK_PROCESS_CREDITS "
									+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR "
									+" ON(HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE)"
									+" WHERE EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH= "+bean.getMonth()+" " +
											" AND EXTRAWORK_INCLUDE_SAL_YEAR="+bean.getYear()
									+" AND HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID="+data[i][5]
									+" GROUP BY CREDIT_CODE,HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH, "
									+"  HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR ) "
									+" GROUP BY EMPID  HAVING EMPID="+data[i][5];
					
						try{	
						Object extraObj[][]=getSqlModel().getSingleResult(benQuery);						
						if(extraObj!=null && extraObj.length>0)
						{
							logger.info("when benfit is added............!!"+extraObj[0][0]);
							param[i][2]=Double.parseDouble(String.valueOf(param[i][2]))+Double.parseDouble(String.valueOf(extraObj[0][0]));
						}else{
							// do nothing..!
							logger.info("no benfitiory amount added............!!");
						}
					}catch(Exception ee)
					{
						logger.info("Exception is raised when adding extra benfit amount."+ee);
					}
						
						
						
						
						
						
						
					param[i][3]=data[i][3];//Name
					String str="";
					if(!String.valueOf(data[i][0]).equals(" ")){
						str+=String.valueOf(data[i][0])+","+String.valueOf(data[i][1]);
					}else {
						str+=String.valueOf(data[i][1]);
					}

					if(!String.valueOf(data[i][2]).equals("")){
						if(arrearAmt != null && arrearAmt.length > 0)
						str+=","+(Double.parseDouble(String.valueOf(data[i][2]))
						+Double.parseDouble(String.valueOf(arrearAmt[0][0])));
						else
							str+=","+String.valueOf(data[i][2]);
					}

					if(!String.valueOf(data[i][3]).equals("")){
						str+=","+String.valueOf(data[i][3]);
					}

					param[i][4]=str;//Concat of account,credit,amount and name. 
					//accountno 4spaces INR+first4charof accountno 4spaces + C  6spaces  amount salaryfor may 2009
					
					String empaccountdtl="";
					empaccountdtl="";
					
					if(!String.valueOf(data[i][0]).equals(" "))
					{
						empaccountdtl+=addSpaces(String.valueOf(data[i][0]),(16-String.valueOf(data[i][0]).length()))+
						"INR";
					logger.info("if condition is true...!");
					}else
					 empaccountdtl+=addSpaces("",16)+"INR";
					
					
					if(String.valueOf(data[i][0])!=null && !String.valueOf(data[i][0]).equals(""))
					{
						if(String.valueOf(data[i][0]).length()>4)
					  {	empaccountdtl+=String.valueOf(data[i][0]).substring(0,4);
					
					   }else
						empaccountdtl+="    ";	
					}
					empaccountdtl+="    "+"C"+"       "+org.paradyne.lib.Utility.twoDecimals(""+param[i][2])+"SALARY FOR "+monthandyear+"";
					
					
					bankstObj[i][0]=empaccountdtl;
					
					
					
				}
			
			}
			else {
				bankstObj[0][0]="Records are not available. ";
			}

				PrintWriter pw =null;
				response.setContentType("application/txt");
				response.setHeader("Content-Disposition","attachment; filename=\""+myfile+".txt"+"\"");
				response.setHeader("cache-control", "no-cache");
		         pw = response.getWriter();
		         pw.flush();
		         
		         logger.info("after title...!");
					for (int i = 0; i < bankstObj.length; i++) {	
						 pw.append(""+bankstObj[i][0]);
						 pw.println();
					}
					
		         
		         pw.flush();
		         pw.close();
		         
            	

		}catch(Exception e){
			
			try{
			PrintWriter pw1 =null;
			
			response.setContentType("application/txt");
			response.setHeader("Content-Disposition","attachment; filename=\""+myfile+".txt"+"\"");
			response.setHeader("cache-control", "no-cache");
	         pw1 = response.getWriter();
	         
	         pw1.append(""+"Salary is not processed for this month.");
	         pw1.flush();
	         pw1.close();
			
			}catch (Exception e1) {
				
			}
			
		}

	
		
		
	}	

	
	
	public String  addSpaces(String str,int space)
	{
	 for (int i=0;i<space;i++)
	 {
		 str+=" ";
	 }
	 return str;

	}
	
	
	
	public double totalDivisionExtraBenfitAmount(SalaryStatement bean) {
		double totbenfitAmt=0.0;
		
		try {
			
			String query1 ="SELECT SUM(AMT) FROM (SELECT SUM(AMOUNT) AMT ,EMPID FROM "
				+ " (SELECT  CREDIT_CODE , SUM(CREDIT_AMOUNT) AMOUNT, "
				+ " 	HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID EMPID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH,"
				+ " 	HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR "
				+ " 	FROM HRMS_EXTRAWORK_PROCESS_CREDITS "
				+ " 	INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR " +
			     "	ON(HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE) "
				+ " 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID) "
				+ " 	LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID) "
				+ " 	WHERE EXTRAWORK_INCLUDE_SAL_FLAG='Y' "
				+ " 	AND EXTRAWORK_INCLUDE_SAL_MONTH="+ bean.getMonth()
				+ " 	AND EXTRAWORK_INCLUDE_SAL_YEAR="+ bean.getYear()
				+ " 	AND HRMS_DIVISION.DIV_ID="+bean.getDivCode()
				+ " 	GROUP BY CREDIT_CODE,HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID, HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH," 
				+ " 	HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR ) "
				+ " 	GROUP BY EMPID  )"; 
			
			Object[][] BenfitAmt = getSqlModel().getSingleResult(query1);
			
			if (BenfitAmt != null && BenfitAmt.length > 0) {
				logger.info("when amount is added...!!"+BenfitAmt[0][0]);
				return Double.parseDouble(String.valueOf(BenfitAmt[0][0]));
			}
			
		} catch (Exception e) {
			logger.info("Exception in Total Benfit calculation."+e);
		}
		return totbenfitAmt;
	}
	
	

}
