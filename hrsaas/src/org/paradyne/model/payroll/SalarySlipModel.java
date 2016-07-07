/**
 * 
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.SalarySlip;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Venkatesh & Pradeep
 *Date:14-03-2008
 */
public class SalarySlipModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void generateReport(SalarySlip salarySlip,HttpServletResponse response,String [][]data,String onHoldChk){
		String title ="Salary Slip for "+Utility.month(Integer.parseInt(salarySlip.getSalaryMonth()))+"-"+salarySlip.getSalaryYear();
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",title);
		rg.setFName(title+".Pdf");
		String empCode = salarySlip.getSalaryEmpCode();
		String year = salarySlip.getSalaryYear();
		String month = salarySlip.getSalaryMonth();
		String empDetailQuery = " SELECT HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  ,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(DEPT_NAME,' ') ,NVL(CENTER_NAME,' '), "
							 +" HRMS_RANK.RANK_NAME,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),NVL(SAL_GPFNO,' ') ,NVL(SAL_ACCNO_REGULAR	,' '),NVL(BANK_NAME	,' '),"
							 +" CASE WHEN SAL_PAY_MODE='T' THEN 'Transfer' WHEN SAL_PAY_MODE='C' THEN 'Cash' WHEN SAL_PAY_MODE='H' THEN 'Cheque' ELSE '' END,SALGRADE_TYPE,NVL(SAL_PANNO,' '),NVL(DIV_NAME,' ')  FROM HRMS_EMP_OFFC"
							 +" INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
							 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							 +" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_SALARY_"+year+".SAL_EMP_RANK  "
							 +" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
							 +" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+year+".SAL_EMP_CENTER  "
							 +" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_SALARY_"+year+".SAL_DEPT	 "
							 +" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_SALARY_"+year+".SAL_DIVISION) "
							 +" LEFT JOIN HRMS_PAYBILL ON HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_"+year+".SAL_EMP_PAYBILL  "
  							 +" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=HRMS_BANK.BANK_MICR_CODE)"
  							 +" LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)"
							 +" WHERE HRMS_SALARY_"+year+".EMP_ID='"+empCode+"' and HRMS_SALARY_"+year+".sal_ledger_code in ("+salarySlip.getSalLedgerCode()+")";
		
		String msgQuery="SELECT MSG_MESSAGE FROM HRMS_PAYSLIP_MSG WHERE MSG_MONTH="+month+" AND MSG_YEAR="+year 
						+" AND (EMP_ID="+empCode+"  OR (EMP_ID IS NULL AND MSG_PAYBILL IS NULL ) OR (EMP_ID IS NULL AND MSG_PAYBILL=(SELECT EMP_PAYBILL FROM HRMS_EMP_OFFC WHERE EMP_ID="+empCode+")) )";
	
		
		String attendanceQuery=" SELECT ATTN_DAYS, ATTN_WOFF, ATTN_COMPOFF, ATTN_HOLIDAY, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS "
						+"	FROM HRMS_MONTH_ATTENDANCE_"+year+" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_CODE) "
						+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID)"
						+"	WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID  ="+empCode 
						+"	AND ROWNUM=1 ";
		
		String empType=" SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID="+empCode;
		Object[][] empTypeCode=getSqlModel().getSingleResult(empType);
		String leaveQuery=null;
		if(!(empTypeCode==null || empTypeCode.length==0)){
		leaveQuery=" SELECT TO_CHAR(LEAVE_ABBR) ,NVL(ATT_LEAVE_AVAILABLE,0),NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LATEMARK_ADJUST,0)+ " +
				"		NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0),NVL(ATT_LEAVE_BALANCE,0) "
						+"	FROM HRMS_MONTH_ATT_DTL_"+year+"  INNER JOIN HRMS_LEAVE "
						+" ON(HRMS_LEAVE.LEAVE_ID=HRMS_MONTH_ATT_DTL_"+year+".ATT_LEAVE_CODE) "
						//+" INNER JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_POLICY.LEAVE_CODE) "
						+" WHERE ATT_EMP_CODE ="+empCode+"  " +
							" AND ATT_CODE IN ("+salarySlip.getSalLedgerCode()+") ORDER BY LEAVE_ID";
		}
		/*String divQuery="SELECT DISTINCT NVL(DIV_NAME,' ') FROM HRMS_DIVISION INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_DIVISION.DIV_ID=HRMS_SALARY_"+year+".SAL_DIVISION) WHERE HRMS_SALARY_"+year+".EMP_ID='"+empCode+"' ";
		Object div[][]=getSqlModel().getSingleResult(divQuery);*/
	
		Object empData[][]=getSqlModel().getSingleResult(empDetailQuery);
		Object msgData[][]=getSqlModel().getSingleResult(msgQuery);
		Object attendance[][]=getSqlModel().getSingleResult(attendanceQuery);
		Object[][] leaveBal=getSqlModel().getSingleResult(leaveQuery);
		
		
		
		
		Object [][] empObj=new Object[6][7];
		Object[][] employee=new Object[7][7];
		try {
			if(!(String.valueOf(empData[0][10]).equals("") || String.valueOf(empData[0][10]).equals("null"))){
				employee[0][0]="  Name";employee[0][1]=":";employee[0][2]=""+empData[0][0];employee[0][3]="";employee[0][4]="Employee Id";employee[0][5]=":";employee[0][6]=""+empData[0][1];
				employee[1][0]="  Dept";employee[1][1]=":";employee[1][2]=""+empData[0][2];employee[1][3]="";employee[1][4]="Branch";employee[1][5]=":";employee[1][6]=""+empData[0][3];
				employee[2][0]="  Designation";employee[2][1]=":";employee[2][2]=""+empData[0][4];employee[2][3]="";employee[2][4]="Date of Joining";employee[2][5]=":";
				if(String.valueOf(empData[0][5]).equals("") || String.valueOf(empData[0][5]).equals(null)){
					employee[2][6]="";
				}else{
					employee[2][6]=""+empData[0][5];
			    
				}
				employee[3][0]="  PF No.";employee[3][1]=":";employee[3][2]=""+empData[0][6];employee[3][3]="";employee[3][4]="Acc. No.";employee[3][5]=":";employee[3][6]=""+empData[0][7];
				employee[4][0]="  Bank Name";employee[4][1]=":";employee[4][2]=""+empData[0][8];employee[4][3]="";employee[4][4]="Pay Mode";	employee[4][5]=":";
				if(String.valueOf(empData[0][9]).equals("null") || String.valueOf(empData[0][9]).equals("")){
					employee[4][6]="";
					
				}else {
					employee[4][6]=""+empData[0][9];
				}
				
				employee[5][0]="  Salary Grade";employee[5][1]=":";employee[5][2]=""+empData[0][10];employee[5][3]="";employee[5][4]="";employee[5][5]="";employee[5][6]="";
				
				employee[6][0]="  PAN No.";employee[6][1]=":";employee[6][2]=""+empData[0][11];employee[6][3]="";employee[6][4]="";employee[6][5]="";employee[6][6]="";
			}else {//if sal grade is not present for the employee then following array will be used
			
			
				empObj[0][0]="  Name";empObj[0][1]=":";empObj[0][2]=""+empData[0][0];empObj[0][3]="";empObj[0][4]="Employee Id";empObj[0][5]=":";empObj[0][6]=""+empData[0][1];
				empObj[1][0]="  Dept";empObj[1][1]=":";empObj[1][2]=""+empData[0][2];empObj[1][3]="";empObj[1][4]="Branch";empObj[1][5]=":";empObj[1][6]=""+empData[0][3];
				empObj[2][0]="  Designation";empObj[2][1]=":";empObj[2][2]=""+empData[0][4];empObj[2][3]="";empObj[2][4]="Date of Joining";empObj[2][5]=":";
				if(String.valueOf(empData[0][5]).equals("") || String.valueOf(empData[0][5]).equals(null)){
					empObj[2][6]="";
				}else{
					empObj[2][6]=""+empData[0][5];
			    
				}
				empObj[3][0]="  PF No.";empObj[3][1]=":";empObj[3][2]=""+empData[0][6];empObj[3][3]="";empObj[3][4]="Acc. No.";empObj[3][5]=":";empObj[3][6]=""+empData[0][7];
				empObj[4][0]="  Bank Name";empObj[4][1]=":";empObj[4][2]=""+empData[0][8];empObj[4][3]="";empObj[4][4]="Pay Mode";	empObj[4][5]=":";
				if(String.valueOf(empData[0][9]).equals("null") || String.valueOf(empData[0][9]).equals("")){
					empObj[4][6]="";
					
				}else {
				empObj[4][6]=""+empData[0][9];
				}
				empObj[5][0]="  PAN No.";empObj[5][1]=":";empObj[5][2]=""+empData[0][11];empObj[5][3]="";empObj[5][4]="";empObj[5][5]=" ";empObj[5][6]="";
				}
			
			
	
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			// TODO: handle exception
		}
		String  comp="";
		try{
		if(empData!=null){
			if(!(empData.length<=0)){
				if(String.valueOf(empData[0][12]).equals("") || String.valueOf(empData[0][12]).equals("null")){
					
				}
				else
					comp=String.valueOf(empData[0][12]);
			}
			
		}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}	
		
		
		String []colNamesArr ={"Salary Component","Salary Credits","Arrear Credits","Debits","Salary Debits","Arrear Debits"}; 
		int[] cellWidthArr = {30,10,10,30,10,10};
		int []alignMentArr={0,2,2,0,2,2};
	
		String []colNames ={"Salary Component","Salary Credits","Debits","Salary Debits"}; 
			int[]cellWidth = {30,20,30,20};
			int []alignMent={0,2,0,2};
		
		String []attCol={"Attendance\nDays","Weekly\nOffs","Comp\nOffs","Holidays","Paid\nLeaves","Unpaid\nLeaves","Salary\nDays"};
		int []attCellWidth={15,15,15,15,15,15,15};
		int []attAlign={1,1,1,1,1,1,1};
	
		
		String []leaveCol={"Leave Type","Available Balance","Adjusted Leave","Balance"};
		int []leaveCellWidth={25,25,25,25};
		int []leaveAlign={1,1,1,1};
		
		rg.addFormatedText(comp, 1, 0, 1, 0);
		rg.addFormatedText(title, 1, 0, 1, 0);
		rg.addText("\n", 0, 0, 0);

		if(empData==null || empData.length <1){
		
		}else {
	
			int[] cellwidth={8,2,10,8,8,2,15};
			int[] alignment={0,0,0,0,0,0,0};
			if(!(String.valueOf(empData[0][10]).equals(" ") || String.valueOf(empData[0][10]).equals("null"))){
				rg.tableBodyNoCellBorder(employee, cellwidth, alignment, 0);
			}else {
			    rg.tableBodyNoCellBorder(empObj, cellwidth, alignment, 0);
			}
			rg.addText("\n", 0, 0, 0);
		}
		
		if(onHoldChk.equals("true")){
			rg.addText("Employee Salary is OnHold for this month.", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
		}
		else{
			if(!(attendance==null || attendance.length==0)){
			//	String[] att={"Monthly Attendance :"};
			//	int[] style={2};
				
				rg.addText("Monthly Attendance :",6,0,0);
				rg.tableBody(attCol, attendance, attCellWidth, attAlign);
				rg.addText("\n", 0, 0, 0);
			
				}
			
			
			if(data!=null){
			if(!(leaveBal==null || leaveBal.length==0)){
			//	String[] leave={"Leave Balance :"};
			//	int[] style={2};
				rg.addText("Leave Balance :",6,0,0);
				rg.tableBody(leaveCol, leaveBal, leaveCellWidth, leaveAlign);
				
			}
			}
			
			if(data==null){
				rg.addText("Records are not available.",0,1,0);
			}
			else{
				rg.addText("\n",0,0,0);
				rg.addText("\nSalary Information :",6,0,0);
				
				if(salarySlip.getCheckFlag().equals("Y")){
					rg.tableBody(colNamesArr, data, cellWidthArr,alignMentArr);
				}else {
				rg.tableBody(colNames, data, cellWidth,alignMent);
				}
				rg.addText("\n",0,0,0);
			
			
				
				if(msgData!=null && msgData.length>0){
					rg.addFormatedText("Messages :\n",0,0,0,0);
					
				rg.tableAddWithBorder(msgData);
				rg.addText("\n",0,0,0);
				
				}
			}
		}	
			String msg[]={"This is a computer generated statement.Hence no signature is required."};
			int style[]={1};
			rg.addFormatedText(msg, style, 0,0,0);
		
		rg.createReport(response);
	}
	/**
	 *Following function is used to display the salary credit,debit and arrears credit,debit head names and their corresponding values.  
	 * @param salarySlip
	 * @return
	 */
	
	public Object[][] prepareData(SalarySlip salarySlip){
			 String empCode = salarySlip.getSalaryEmpCode();
			String year = salarySlip.getSalaryYear();
			String month = salarySlip.getSalaryMonth();
			String ledgerCode="";
			String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="+month+" and ledger_year="+year+" and LEDGER_STATUS='SAL_FINAL'";
		
		Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
		/*
		 * Following loop calculates the salary ledger code.If ledger code is not available it returns null  
		 */
		if(ledgerData!=null && ledgerData.length>0){
			for (int i = 0; i < ledgerData.length; i++) {
				if(i==ledgerData.length-1){
					ledgerCode += ledgerData[i][0];
				}
				else{
					ledgerCode += ledgerData[i][0]+",";
				}
			}
			salarySlip.setSalLedgerCode(String.valueOf(ledgerCode));
		}
		else{
			return null;
		}
		
		String creditQuery ="SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,SAL_AMOUNT "
			+" FROM HRMS_SAL_CREDITS_"+year+
			" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_SAL_CREDITS_"+year+
			".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "+
			" WHERE CREDIT_PAYFLAG='Y' and SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"'  AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		String debitQuery ="SELECT HRMS_DEBIT_HEAD.DEBIT_CODE,HRMS_DEBIT_HEAD.DEBIT_NAME,SAL_AMOUNT "
			+" FROM HRMS_SAL_DEBITS_"+year+
			" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_SAL_DEBITS_"+year+
			".SAL_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE "+
			" WHERE SAL_AMOUNT>0 and SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"' and HRMS_debit_HEAD.DEBIT_PERIODICITY='M' ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
		String netSalQuery ="SELECT SAL_TOTAL_CREDIT,SAL_TOTAL_DEBIT,SAL_NET_SALARY,SAL_ONHOLD "
			+" from hrms_salary_"+year+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"'";
		Object creditData[][]=getSqlModel().getSingleResult(creditQuery);
		Object debitData[][]=getSqlModel().getSingleResult(debitQuery);
		Object netSalData[][]=getSqlModel().getSingleResult(netSalQuery);

		double arrearCreditAmount=0;
		double arrearDebitAmount=0;
		double totalCreditSum=0;
		double totalDebitSum=0;
		
		double totalExtrabefitAmt=0;
		
		try {
			totalExtrabefitAmt = totalExtraBenfitAmount(salarySlip, empCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(creditData== null || debitData == null || netSalData == null ){
			return new Object[1][1];
		}
		else if(creditData.length<=0 && debitData.length<=0 && netSalData.length<=0){
			return null;
		}
		else if(netSalData[0][3].equals("Y")){
			Object[][] onHold = new Object[1][1];
			onHold[0][0]= "onHold";
			return onHold;
		}
		Object [][]finalObject = null  ;
		int row =0;	
		
     if(salarySlip.getCheckFlag().equals("Y")){
    	 /*
    	  * If check box is checked then arrears credit and salary credit will come in 2 nd and 3rd position of finalObject
    	  */
    	 try{
    	 
    	 /*
    	  * Following 2 queries will find the credit and debit codes present in the salary credit and debit
    	  */
    	 String creditCodeQueryCheck ="SELECT HRMS_CREDIT_HEAD.CREDIT_CODE "
 					+" FROM HRMS_SAL_CREDITS_"+year+
 					" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_SAL_CREDITS_"+year+
 					".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "+
 					" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"'  AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
    	 			Object Data[][]=getSqlModel().getSingleResult(creditCodeQueryCheck);
    	 String debitCodeQueryCheck="SELECT HRMS_DEBIT_HEAD.DEBIT_CODE "
    	 				+" FROM HRMS_SAL_DEBITS_"+year+
    	 				" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_SAL_DEBITS_"+year+
    	 				".SAL_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE "+
    	 				" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"' and HRMS_debit_HEAD.DEBIT_PERIODICITY='M'ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
    	 Object Data1[][]=getSqlModel().getSingleResult(debitCodeQueryCheck);
    	 
    	 int debitCount=0;
    	 /*
    	  * Following query is used to find the size of debit length
    	  */
    	 for(int i=0;i<Data1.length;i++){
    		 
    		 
    		 String debitCodeQuery ="SELECT HRMS_DEBIT_HEAD.DEBIT_CODE,HRMS_DEBIT_HEAD.DEBIT_NAME,NVL(SAL_AMOUNT,0) "
					+" FROM HRMS_SAL_DEBITS_"+year+
					" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_SAL_DEBITS_"+year+
					".SAL_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE "+
					" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"' and HRMS_debit_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_CODE="+Data1[i][0];
    		 Object debitSalAmt[][]=getSqlModel().getSingleResult(debitCodeQuery);
    		 
    		 String arrQuery="SELECT NVL(SUM(ARREARS_AMT),0) FROM HRMS_ARREARS_DEBIT_"+year
				+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE)"
				+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR="+year+" AND ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
				+" AND ARREARS_EMP_ID="+empCode+" AND ARREARS_DEBIT_CODE="+Data1[i][0];
    		 Object[][] arrAmt=getSqlModel().getSingleResult(arrQuery);
    		 
    		 if(!(String.valueOf(debitSalAmt[0][2]).equals("0") && String.valueOf(arrAmt[0][0]).equals("0"))){
    			 debitCount++;
	 			}
    		 
    	 }//End of Data1 for loop i.e. used to find the length of debit head
    	 /*
    	  * Following query is used to set the debit name,debit value,arrear debit value
    	  */
    	 Object dc[][]=new Object[debitCount][3];
    	 int d=0;
    	 for(int i=0;i<Data1.length;i++){
    		 
    		 String debitDataQuery ="SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,SAL_AMOUNT "
				+" FROM HRMS_SAL_DEBITS_"+year+
				" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_SAL_DEBITS_"+year+
				".SAL_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE "+
				" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"' and HRMS_debit_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_CODE="+Data1[i][0];
 		 Object debitSalAmt[][]=getSqlModel().getSingleResult(debitDataQuery);
 		 
 		 String arrQuery="SELECT NVL(SUM(ARREARS_AMT),0) FROM HRMS_ARREARS_DEBIT_"+year
				+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE)"
				+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR="+year+" AND ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
				+" AND ARREARS_EMP_ID="+empCode+" AND ARREARS_DEBIT_CODE="+Data1[i][0];
 		 Object[][] arrAmt=getSqlModel().getSingleResult(arrQuery);
 		 
 		if (arrAmt != null && arrAmt.length > 0){
 				
	 		 if(!(String.valueOf(debitSalAmt[0][1]).equals("0") && String.valueOf(arrAmt[0][0]).equals("0"))){
	 			 try{
	 			 dc[d][0]=debitSalAmt[0][0];
	 			 dc[d][1]=debitSalAmt[0][1];
	 			 dc[d][2]=checkNull(String.valueOf(arrAmt[0][0]));
	 			 
	 			 d++;
	 			 }catch(Exception e){
	 				 logger.error("in setting the credit amt"+e);
	 			 }
		 	}
 		}else{
 			try{
 			 dc[d][0]=debitSalAmt[0][0];
 			 dc[d][1]=debitSalAmt[0][1];
 			 dc[d][2]="0";
 			 
 			 d++;
 			}catch(Exception e){
 				logger.error("in setting the credit amt"+e);
 			}
 		}
    	 }//End of Data1 for loop i.e. used to set the debit head name,debit head value and the arrear debit amount.
    	 
			
    	 int counter=0;
 		for(int i=0;i<Data.length;i++){
 			
 		/*
 		 * Following query is used to find the length of the credit data
 		 */
			 	String creditQueryAmt="SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(SAL_AMOUNT,0) "
			 	+" FROM HRMS_SAL_CREDITS_"+year+
			 	" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_SAL_CREDITS_"+year+
			 	".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "+
			 	" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"'  AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' and HRMS_CREDIT_HEAD.CREDIT_CODE="+Data[i][0];
			 	Object cr[][]=getSqlModel().getSingleResult(creditQueryAmt);
			 			
			 	String arrQuery="SELECT NVL(SUM(ARREARS_AMT),0) FROM HRMS_ARREARS_CREDIT_"+year
			 	+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE)"
			 	+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR="+year+" AND ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
			 	+" AND ARREARS_EMP_ID="+empCode+" AND ARREARS_CREDIT_CODE="+Data[i][0];
			 			
			 	Object[][] arrAmt=getSqlModel().getSingleResult(arrQuery);
			 		if(!(String.valueOf(cr[0][1]).equals("0") && String.valueOf(arrAmt[0][0]).equals("0"))){
			 				counter++;
			 			}
 		
 			
 			
 		}//End of loop Data i.e. used to find the length of credit data
 		
 		/*
 		 * Following query is used to set the credit value and name
 		 */
 		Object[][] ac=new Object[counter][3];
 		int count=0;
 		for(int i=0;i<Data.length;i++){
 			
 			String creditQueryAmt="SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(SAL_AMOUNT,0),HRMS_CREDIT_HEAD.CREDIT_CODE "
	 				+" FROM HRMS_SAL_CREDITS_"+year+
	 				" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_SAL_CREDITS_"+year+
	 				".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "+
	 				" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND EMP_ID ='"+empCode+"'  AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' and HRMS_CREDIT_HEAD.CREDIT_CODE="+Data[i][0];
 			Object cr[][]=getSqlModel().getSingleResult(creditQueryAmt);
 			
 			String arrQuery="SELECT NVL(SUM(ARREARS_AMT),0) FROM HRMS_ARREARS_CREDIT_"+year
		 			+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE)"
		 			+" WHERE ARREARS_REF_MONTH="+month+" AND ARREARS_REF_YEAR="+year+" AND ARREARS_TYPE IN('M','P') AND ARREARS_PAY_IN_SAL = 'Y' "
		 			+" AND ARREARS_EMP_ID="+empCode+" AND ARREARS_CREDIT_CODE="+Data[i][0];
 			
 			Object[][] arrAmt=getSqlModel().getSingleResult(arrQuery);
 			
 			if (arrAmt != null && arrAmt.length > 0){
	 			if(!(String.valueOf(cr[0][1]).equals("0") && String.valueOf(arrAmt[0][0]).equals("0"))){
	 				try{
	 				ac[count][0]=cr[0][0];
	 				ac[count][1]=Double.parseDouble(String.valueOf(cr[0][1]))+extraBenfitAmount(salarySlip, empCode, String.valueOf(cr[0][2]));
	 				logger.info("credit amount. cr[0][1] "+cr[0][1]);
	 				logger.info("after extra benfit added. ac[count][1] :"+ac[count][1]);
	 				ac[count][2]=checkNull(String.valueOf(arrAmt[0][0]));
	 				count++;
	 				}catch(Exception e){
	 					logger.error("in setting the debit amt"+e);
	 				}
	 			}
 			}else{
 				try{
 				ac[count][0]=cr[0][0];
 				logger.info("when extra benfit added.");
 				ac[count][1]=Double.parseDouble(String.valueOf(cr[0][1]))+extraBenfitAmount(salarySlip, empCode, String.valueOf(cr[0][2]));
 				logger.info("after extra benfit added."+ac[count][1]);
 				ac[count][2]="0";
 				count++;
 				}catch(Exception e){
 					logger.error("in setting the debit amt"+e);
 				}
 				
 			}
 			
 		 }//End of loop Data i.e. used to set the credit head,head value and arrear credit amount
 		
 		if(ac.length > dc.length){
			finalObject = new Object[ac.length+3][6];
			row = ac.length;
		}else{
			finalObject = new Object[dc.length+3][6];
			row = dc.length;
		}
 		
 		/*
 		 * Following loop is used to set the credit head ,sal credit value and arrear credit value.
 		 */
 		for(int i=0;i<ac.length;i++){
 			finalObject[i][0]=ac[i][0];//Salary Credit Head Name
 			finalObject[i][1]=ac[i][1];//Salary Credit Amount
 			finalObject[i][2]=ac[i][2];//Arrear Credit Amount
 			arrearCreditAmount+=Double.parseDouble((String.valueOf(ac[i][2])));
 			
 		}
 		/*
 		 * Following loop is used to set the debit head anme,sal debit value and arrear debit value.
 		 */
 		
 		for(int i=0;i<dc.length;i++){
 			finalObject[i][3]=dc[i][0];
 			finalObject[i][4]=dc[i][1];
 			finalObject[i][5]=dc[i][2];
 			arrearDebitAmount+=Double.parseDouble((String.valueOf(dc[i][2])));
 		}
 		
 		
 		/*
		 * Following row shows the total credit and debit amount from salary table 
		 */
		finalObject[row][0] = "Total";
		finalObject[row][1] = Math.round(Double.parseDouble(String.valueOf(netSalData[0][0])))+Math.round(totalExtrabefitAmt);
		finalObject[row][2]=  Math.round(arrearCreditAmount);
		finalObject[row][3] = "Total";
		finalObject[row][4] =  Math.round(Double.parseDouble(String.valueOf(netSalData[0][1])));
		finalObject[row][5]=   Math.round(arrearDebitAmount);
		
		
		/*
		 * Following row displays the sum of the credit and debit amount form salary table and arrear table.
		 */
		
		finalObject[row+1][0] = "Total Credit";
		finalObject[row+1][1] ="";
		finalObject[row+1][2] =Math.round(Double.parseDouble(String.valueOf(netSalData[0][0])))+Math.round(arrearCreditAmount)+Math.round(totalExtrabefitAmt);
		totalCreditSum=Math.round(Double.parseDouble(String.valueOf(netSalData[0][0])))+Math.round(arrearCreditAmount)+Math.round(totalExtrabefitAmt);
		finalObject[row+1][3] ="Total Debit";
		finalObject[row+1][4] ="";
		finalObject[row+1][5] =Math.round(Double.parseDouble(String.valueOf(netSalData[0][1])))+Math.round(arrearDebitAmount);
		totalDebitSum=Math.round(Double.parseDouble(String.valueOf(netSalData[0][1])))+Math.round(arrearDebitAmount);
		
		/*
		 * Following row displays the net salary by subtracting totalDebitSum from totalCreditSum
		 */
		finalObject[row+2][0] ="Net Salary";
		finalObject[row+2][1]=Math.round(totalCreditSum-totalDebitSum);
		//+Math.round(totalExtrabefitAmt);
		finalObject[row+2][2] ="";
		finalObject[row+2][3] ="";
		finalObject[row+2][4] ="";
		finalObject[row+2][5] ="";
		
 		 }catch(Exception e){
    		 e.printStackTrace();
    		 logger.error(e.getMessage());
    	 }
		}	else {//End of check flag .
					
					
					try{
					
					if(creditData.length > debitData.length){
						finalObject = new Object[creditData.length+2][4];
						row = creditData.length;
					}else{
						finalObject = new Object[debitData.length+2][4];
						row = debitData.length;
					}
					/*
					 * Following loop is used to insert the credit name and amount in the first and second position of the final object.
					 */
					for (int i = 0; i < creditData.length; i++) {
						finalObject[i][0] = creditData[i][1];
						logger.info("Before adding extraBenfit amount..!creditData[i][2]"+creditData[i][2]);
						finalObject[i][1] = Math.round(Double.parseDouble(String.valueOf(creditData[i][2]))+extraBenfitAmount(salarySlip, empCode, String.valueOf(creditData[i][0])));
						logger.info("AFTER adding extraBenfit amount..!finalObject[i][1] "+finalObject[i][1]);
					}
					
					/*
					 * Following loop is used to insert the debit name and amount in the third and fourth position of the final object.
					 */
					for (int i = 0; i < debitData.length; i++) {
						finalObject[i][2] = debitData[i][1];
						finalObject[i][3] = debitData[i][2];
					}
					finalObject[row][0] = "Total";
					finalObject[row][1] = Math.round(Double.parseDouble(String.valueOf(netSalData[0][0])))+Math.round(totalExtrabefitAmt);
					finalObject[row][2] = "Total";
					finalObject[row][3] = Math.round(Double.parseDouble(String.valueOf(netSalData[0][1])));
					finalObject[row+1][0] = "Net Salary";
					finalObject[row+1][1] = Math.round(Double.parseDouble(String.valueOf(netSalData[0][2])))+Math.round(totalExtrabefitAmt);
					finalObject[row+1][2] = "";
					finalObject[row+1][3] = "";
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			// TODO: handle exception
		}
		
	}
		return finalObject;
	}
	/*
	 * Following function displays the employee name,employee is when a general user makes login.
	 */
	public void getEmployeeDetails(String empId, SalarySlip salarySlip) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	EMP_TOKEN  "
				+ "	FROM HRMS_EMP_OFFC "
				
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID =" + beanObj[0] + " ";
		Object[][] values = getSqlModel().getSingleResult(query);
			salarySlip.setSalaryEmpCode(String.valueOf(values[0][0]));
			salarySlip.setSalaryEmpName(String.valueOf(values[0][1]));
			salarySlip.setSalaryEmpToken(String.valueOf(values[0][2]));
			
		
	}
	public static String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "0";
		}
		else{
			return result;
		}
	}
	
	
	public double extraBenfitAmount(SalarySlip salarySlip,String empId,String creditCode) {
		double benfitAmt=0.0;
		
		try {
			logger.info("empId "+empId);
			logger.info("creditCode "+creditCode);
			
		/*	String query = "SELECT  CREDIT_CODE , SUM(CREDIT_AMOUNT) AMOUNT,  HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID EMPID, "
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH, "
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR  FROM HRMS_EXTRAWORK_PROCESS_CREDITS  "
					+ " INNER JOIN  HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE)"
					+ " WHERE EXTRAWORK_INCLUDE_SAL_FLAG='Y' "
					+ " AND EXTRAWORK_INCLUDE_SAL_MONTH= "
					+ salarySlip.getSalaryMonth()
					+ " AND EXTRAWORK_INCLUDE_SAL_YEAR="
					+ salarySlip.getSalaryYear()
					+ " AND HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID="
					+ empId
					+ " and CREDIT_CODE="
					+ creditCode
					+ " GROUP BY CREDIT_CODE,HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID,"
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH,"
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR";*/
			
			
			String query="SELECT EXTRAWORK_BENEFIT_CREDITED_TO,SUM(EXTRAWORK_BENEFIT_TOTAL_AMT) "
							+" FROM HRMS_EXTRAWORK_PROCESS_DTL "
							+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE)" 
							+" WHERE "
							+" HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID="+ empId
							+" AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_MONTH="+ salarySlip.getSalaryMonth()
							+" AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_YEAR="+ salarySlip.getSalaryYear() 
							+" GROUP BY EXTRAWORK_BENEFIT_CREDITED_TO HAVING  EXTRAWORK_BENEFIT_CREDITED_TO="+creditCode;
			
			Object[][] BenfitAmt = getSqlModel().getSingleResult(query);
			
			if (BenfitAmt != null && BenfitAmt.length > 0) {
				logger.info("when amount is added...!!"+BenfitAmt[0][1]);
				return Double.parseDouble(String.valueOf(BenfitAmt[0][1]));
			}
			
		} catch (Exception e) {
			
		}
		return benfitAmt;
	}
	
	public double totalExtraBenfitAmount(SalarySlip salarySlip,String empId) {
		double totbenfitAmt=0.0;
		
		try {
			logger.info("empId "+empId);
			
			
		/*	String query = "SELECT SUM(AMOUNT) FROM (SELECT  CREDIT_CODE , SUM(CREDIT_AMOUNT) AMOUNT,  HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID EMPID, "
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH, "
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR  FROM HRMS_EXTRAWORK_PROCESS_CREDITS  "
					+ " INNER JOIN  HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE)"
					+ " WHERE EXTRAWORK_INCLUDE_SAL_FLAG='Y' "
					+ " AND EXTRAWORK_INCLUDE_SAL_MONTH= "
					+ salarySlip.getSalaryMonth()
					+ " AND EXTRAWORK_INCLUDE_SAL_YEAR="
					+ salarySlip.getSalaryYear()
					+ " AND HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID="
					+ empId
				//	+ " and CREDIT_CODE="					
					+ " GROUP BY CREDIT_CODE,HRMS_EXTRAWORK_PROCESS_CREDITS.EMP_ID,"
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_MONTH,"
					+ " HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_YEAR)";*/
			
			
			String query="Select nvl(sum(Amount),0) from ( SELECT EXTRAWORK_BENEFIT_CREDITED_TO,SUM(EXTRAWORK_BENEFIT_TOTAL_AMT) Amount "
				+" FROM HRMS_EXTRAWORK_PROCESS_DTL "
				+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE)" 
				+" WHERE "
				+" HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID="+ empId
				+" AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_MONTH="+ salarySlip.getSalaryMonth()
				+" AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_YEAR="+ salarySlip.getSalaryYear() 
				+" GROUP BY EXTRAWORK_BENEFIT_CREDITED_TO )";

			
			Object[][] BenfitAmt = getSqlModel().getSingleResult(query);
			
			if (BenfitAmt != null && BenfitAmt.length > 0) {
				logger.info("when amount is added...!!"+BenfitAmt[0][0]);
				System.out.println("when amount is added...!!"+BenfitAmt[0][0]);
				return Double.parseDouble(String.valueOf(BenfitAmt[0][0]));
			//	return 0;
			}
			
		} catch (Exception e) {
			logger.info("Exception in Total Benfit calculation."+e);
			System.out.println("Exception in Total Benfit calculation."+e);
		}
		return totbenfitAmt;
	}
	
	
	
}
