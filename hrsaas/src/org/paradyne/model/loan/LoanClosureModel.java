/**
 * 
 */
package org.paradyne.model.loan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanClosure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Tarun Chaturvedi
 * @date 21-07-2008
 * @description LoanClosureModel class to write business logic to save, update, delete
 * 				and view loan closure detail for yhe selected application
 */
public class LoanClosureModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(LoanClosureModel.class);
	
	/**@Method saveLoanClosure
	 * @Purpose To save the pre payment details or closure details for the selected loan application
	 * @param bean
	 * @param installmentDate
	 * @param principalAmount
	 * @param interestAmount
	 * @param installmentAmount
	 * @param balancePrincipalAmt
	 * @param isPaid
	 * @return String
	 */
	//NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
	NumberFormat formatter = new DecimalFormat("#0.00");
	public String saveLoanClosure(LoanClosure bean, String [] installmentDate, String [] principalAmount, String [] interestAmount, 
			 	String []installmentAmount, String [] balancePrincipalAmt, String [] isPaid){
		logger.info("in saveLoanClosure method");
		
		String result = "";
		
		Object [][] insertData = new Object [1][9];
		insertData [0][0]	   = bean.getLoanAppCode();
		insertData [0][1]	   = bean.getLoanAppCode();
		insertData [0][2]	   = bean.getClosureDate();
		insertData [0][3]	   = bean.getAmtPaidByEmp();
		
		insertData [0][5]	   = bean.getAmountPaid();
		insertData [0][6]	   = bean.getBalanceAmount();
		
		
		insertData [0][4]	   = "";
		insertData [0][7]	   = "";
		insertData [0][8]	   = "";
		
		if(bean.getHiddenCalType().equals("P")){
			insertData [0][8] =	bean.getMonthlyPrincAmount();
		}else if(bean.getHiddenCalType().equals("E")){
			insertData [0][7] =	bean.getEmiAmount();
		}else{
			if(bean.getInterestType().equals("I")){
				insertData [0][4]   = bean.getNoOfInstallmentsReduceInt();
			}else{
				insertData [0][4]   = bean.getNoOfInstallmentOther();
			}
		}
		
		boolean queryResult = getSqlModel().singleExecute(getQuery(1), insertData);
		
		if(queryResult){
			if(installmentDate != null && installmentDate.length != 0){
					queryResult = insertInstallmentData(bean, installmentDate, principalAmount, interestAmount, installmentAmount, balancePrincipalAmt, isPaid);
			}	//end of if
			else {
				logger.info("bean.getLoanAppCode()  "+bean.getLoanAppCode());
				
				String deleteQuery = "DELETE FROM HRMS_LOAN_INSTALMENT WHERE LOAN_APPL_CODE = "+bean.getLoanAppCode()
				  					 +" AND LOAN_INSTALLMENT_IS_PAID != 'Y'";

				queryResult = getSqlModel().singleExecute(deleteQuery);
			}
		}	//end of if
		
		if(queryResult){
			result = "saved";
			Object [][]updateProcessObj= new Object[1][3];
			updateProcessObj [0][0] = bean.getInterestRate();
			updateProcessObj [0][1] = bean.getInterestType();
			updateProcessObj [0][2] = bean.getLoanAppCode();
			 getSqlModel().singleExecute(getQuery(14), updateProcessObj);
		}	//end of if
		return result;
	}
	
	/**@Method  insertInstallmentData
	 * @Purpose To generate and save installment details for the selected loan application
	 * @param bean
	 * @param installmentDate
	 * @param principalAmount
	 * @param interestAmount
	 * @param installmentAmount
	 * @param balancePrincipalAmt
	 * @param isPaid
	 * @return boolean
	 */
	public boolean insertInstallmentData(LoanClosure bean, String [] installmentDate, String [] principalAmount, String [] interestAmount, 
		 	String []installmentAmount, String [] balancePrincipalAmt, String [] isPaid){
		String [] dateSplit;
		String day = "";
		String month = "";
		String year = "";
		int j = 0;
		boolean queryResult = false;
		
		
		logger.info("bean.getLoanAppCode()  "+bean.getLoanAppCode());
		
		String deleteQuery = "DELETE FROM HRMS_LOAN_INSTALMENT WHERE LOAN_APPL_CODE = "+bean.getLoanAppCode()
		  					 +" AND LOAN_INSTALLMENT_IS_PAID != 'Y'";

		queryResult = getSqlModel().singleExecute(deleteQuery);

		if(installmentDate!=null && installmentDate.length>0)
		if(queryResult){
		
			Object [][] installmentData = new Object [installmentDate.length][9];
			
			for (int i = 0; i < installmentDate.length; i++) {
				dateSplit = installmentDate[i].split("-");
				day = dateSplit [0];
				month = getMonthNumber(dateSplit[1]);
				year  = dateSplit [2];
				
				if(isPaid [i].equals("Paid")){
					
				}	//end of if
				else{
					installmentData[j][0] = bean.getLoanAppCode();
					installmentData[j][1] = month;
					installmentData[j][2] = year;
					installmentData[j][3] = principalAmount [i];
					installmentData[j][4] = interestAmount [i];
					installmentData[j][5] = installmentAmount [i];
					installmentData[j][6] = balancePrincipalAmt [i];
					installmentData[j][7] = day;
					installmentData[j][8] = bean.getEmpCode();
					
					j++;
				}	//end of else
			}	//end of for loop
			queryResult = getSqlModel().singleExecute(getQuery(9), installmentData);
		}	//end of if
		return queryResult;
	}
	
	/**@Method getMonthNumber
	 * @Purpose To convert the string representation of the month in to the number format
	 * @param month
	 * @return no of month in string form i.e. like Jan, Feb etc.
	 */
	public String getMonthNumber(String month){
		String query = "SELECT TO_CHAR(TO_DATE('"+month+"','MON'),'MM') FROM DUAL";
		
		Object [][]monthNo = getSqlModel().getSingleResult(query);
		
		return String.valueOf(monthNo[0][0]);
	}
	
	/**@Method updateLoanClosure
	 * @Purpose To update the pre payment details or closure details for the selected  
	 * 			loan application (if the record is latest then only)
	 * @param bean
	 * @param installmentDate
	 * @param principalAmount
	 * @param interestAmount
	 * @param installmentAmount
	 * @param balancePrincipalAmt
	 * @param isPaid
	 * @return String
	 */
	public String updateLoanClosure(LoanClosure bean, String [] installmentDate, String [] principalAmount, String [] interestAmount, 
		 		String []installmentAmount, String [] balancePrincipalAmt, String [] isPaid){
		logger.info("in deleteLoanClosure method");
		String result = "";
		
		Object [][] insertData = new Object [1][9];
		insertData [0][0]	   = bean.getClosureDate();
		insertData [0][1]	   = bean.getAmtPaidByEmp();
		
		insertData [0][3]	   = bean.getAmountPaid();
		insertData [0][4]	   = bean.getBalanceAmount();
		
		
		insertData [0][2]	   = "";
		insertData [0][5]	   = "";
		insertData [0][6]	   = "";
		
		if(bean.getHiddenCalType().equals("P")){
			insertData [0][6] =	bean.getMonthlyPrincAmount();
		}else if(bean.getHiddenCalType().equals("E")){
			insertData [0][5] =	bean.getEmiAmount();
		}else{
			if(bean.getInterestType().equals("I")){
				insertData [0][2]   = bean.getNoOfInstallmentsReduceInt();
			}else{
				insertData [0][2]   = bean.getNoOfInstallmentOther();
			}
		}
		
		
		
		insertData [0][7]	   = bean.getLoanAppCode();
		insertData [0][8]	   = bean.getLoanClosureCode();
		
		
		Object [] loanCode       = new Object [1];
		loanCode [0]			 = bean.getLoanAppCode();
		
		Object [][] loanClosureCode = getSqlModel().getSingleResult(getQuery(4), loanCode);
		
		if(loanClosureCode != null && loanClosureCode.length != 0){
			if(String.valueOf(loanClosureCode [0][0]).equals(bean.getLoanClosureCode())){
				boolean queryResult = getSqlModel().singleExecute(getQuery(5), insertData);
				if(queryResult){
					queryResult = insertInstallmentData(bean, installmentDate, principalAmount, interestAmount, installmentAmount, balancePrincipalAmt, isPaid);
					
					if(queryResult){
						result = "updated";
						Object [][]updateProcessObj= new Object[1][3];
						updateProcessObj [0][0] = bean.getInterestRate();
						updateProcessObj [0][1] = bean.getInterestType();
						updateProcessObj [0][2] = bean.getLoanAppCode();
						 getSqlModel().singleExecute(getQuery(14), updateProcessObj);
					}	//end of if
					else{
						result = "error";
					}	//end of else
				}	//end of if
				else{	
					result = "error";
				}	//end of else
			}	//end of if
		}	//end of if
		return result;
	}
	
	/**@Method deleteLoanClosure
	 * @Purpose To delete all the details of a selected loan application
	 * @param bean
	 * @return String
	 */
	public String deleteLoanClosure(LoanClosure bean){
		logger.info("in deleteLoanClosure method");
		String result = "";
		boolean queryResult = false;
		
		Object [][] loanApplCode = new Object[1][2];
		loanApplCode [0][0]      = bean.getLoanAppCode();
		loanApplCode [0][1]      = bean.getLoanClosureCode();
		
		Object [] loanCode       = new Object [1];
		loanCode [0]			 = bean.getLoanAppCode();
		
		Object [][] loanClosureCode = getSqlModel().getSingleResult(getQuery(4), loanCode);
		
		if(loanClosureCode != null && loanClosureCode.length != 0){
			if(String.valueOf(loanClosureCode [0][0]).equals(bean.getLoanClosureCode())){
				queryResult = getSqlModel().singleExecute(getQuery(2), loanApplCode);
				if(queryResult){
					result = "deleted";
				}	//end of if
				else{
					result = "error";
				}	//end of else
			}	//end of if
		}	//end of if
		return result;
	}
	
	/**@Method generateReport
	 * @Purpose To generate report for the selected application 
	 * @param bean
	 * @param response
	 */
	public void generateReport(LoanClosure bean, HttpServletResponse response){
		logger.info("in generateReport method");
		
		String name  = "Loan Closure Report";
		String type  = "Pdf";
		String title = "Loan Closure Report";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
		//rg.genHeader("Conference Booking");
     	rg = getHeader(rg,bean);	
		rg.createReport(response);
	}
	
	/**@Method getHeader
	 * @Purpose To generate report for the selected application 
	 * @param rg
	 * @param bean
	 * @return ReportGenerator instance
	 */
	public ReportGenerator getHeader(ReportGenerator rg, LoanClosure bean){
		
		Object[] loanApplCode  = new Object[1];
		Object [][] heading    = new Object [1][1];
		int []cells={25};
		int []align={0};
		
		loanApplCode[0] 	   = bean.getLoanAppCode();
		
		Object[][] getHdrData = getSqlModel().getSingleResult(getQuery(6), loanApplCode);
		logger.info("result.length " + getHdrData.length);
		
		String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object [][]today = getSqlModel().getSingleResult(dateQuery);
		String date = "Date : "+(String)today[0][0];
		
		Object[][] getTableData = getSqlModel().getSingleResult(getQuery(7), loanApplCode);
		logger.info("result1.length " + getTableData.length);
		
		setBalanceAmount(bean);

		Object [][] empName      = new Object [1][1];
		Object [][] setHdrData   = new Object[3][2];
		Object [][] settableData = new Object[getTableData.length][3];
		
		for (int i = 0; i < getTableData.length; i++) {
			settableData[i][0]   = i+1;//String.valueOf(result1[i][0]);
			settableData[i][1]   = String.valueOf(getTableData[i][0]);
			settableData[i][2]   = String.valueOf(getTableData[i][1]);
		}	//end of for loop
	 	
	        String header        = " Loan Closure Report ";
	        
	        empName [0][0]	     = String.valueOf(" Employee Name :")+"  "+checkNull(String.valueOf(getHdrData[0][0]));
	        
	        setHdrData [0][0]    = String.valueOf(" Branch :")+"  "+checkNull(String.valueOf(getHdrData[0][3])); 		    
	        setHdrData [0][1]    = String.valueOf(" Department : ")+"  "+checkNull(String.valueOf(getHdrData[0][4])); 
	        setHdrData [1][0]    = String.valueOf(" Sanction Amount : ")+"  "+checkNull(String.valueOf(getHdrData[0][2])); 
	        setHdrData [1][1]    = String.valueOf(" Sanction Date :")+"  "+checkNull(String.valueOf(getHdrData[0][1]));
	        setHdrData [2][0]    = String.valueOf(" Amount Paid :")+"  "+bean.getAmountPaid();
	        setHdrData [2][1]    = String.valueOf(" Balance Amount :")+"  "+bean.getBalanceAmount();
	    	 
		    String colNames []   = { "Sr. No.", "Loan Payment/Closure Date",	"Pre Payment Amount"};
			int[] cellwidth      = {15, 30, 30,};
			int [] alignment     = {0, 2, 2};
			
			int [] emprowcellwidth = {100};
			int [] emprowalignmnet = {0};
	
	    	int [] rowcellwidth    = {50, 50};
			int [] rowalignmnet    = {0,0};
			
	    try {
			rg.addFormatedText(header, 6, 0, 1, 0);
			
			rg.addText(date, 0, 2, 0);
			//rg.addText("\n", 0, 0, 0);
			
			heading [0][0] = "Report Details :-";
			rg.tableBodyBold(heading, cells, align) ;
			
			//rg.addFormatedText("Report Details :-", 4, 0, 0, 0);
			
			rg.tableBody(empName, emprowcellwidth, emprowalignmnet);	
			
	    	rg.tableBody(setHdrData, rowcellwidth, rowalignmnet);	
	    	rg.addText("\n", 0, 0, 0);
	    	
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e);
		}
		
		heading [0][0] = "Pre Payment Details :-";
		rg.tableBodyBold(heading, cells, align) ;
		//rg.addFormatedText("Installment Details :-",  4, 0, 0, 0);
		rg.tableBody(colNames, settableData, cellwidth, alignment);
		
		return rg;
	}
	
	/**@Method rescheduleInstallments
	 * @Purpose To reschedule the installment details for the selected application
	 * @param bean
	 * @param principalAmount
	 * @param interestRate
	 * @param interestType
	 * @param noOfInstallment
	 * @param startDate
	 * @param request
	 */
	public void rescheduleInstallments(LoanClosure bean, String principalAmount, String interestRate, 
			String interestType, String noOfInstallment, String startDate, HttpServletRequest request){
		
		LoanProccessModel model = new LoanProccessModel();
		
		Double principal = Double.parseDouble(principalAmount);
		int installments = Integer.parseInt(noOfInstallment);
		HashMap mapdata  = new HashMap();
		
		logger.info("hiddden "+bean.getInterestType());
		
		double totalEmi = 0.0;
		double totalIntPaid = 0.0;
		double totalPrincipal = 0.0;
		int count = 0;
		
		ArrayList<Object> tableList = new ArrayList<Object>();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date startDateParse = sdf.parse(startDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDateParse);
			//cal.add(cal.MONTH, 1);
			
			String lastInstallmentDate = "";
			boolean dateFlag = false;
			String stringDate = sdf.format(cal.getTime());
			
			logger.info("stringDate  "+stringDate);
			
			Object [] applCode = {bean.getLoanAppCode()};
			
			Object [][] getPaidInstallmentsData = null;
			
			getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(10), applCode);
			
			if(getPaidInstallmentsData != null && getPaidInstallmentsData.length != 0){
				for (int i = 0; i < getPaidInstallmentsData.length; i++) {
					LoanClosure bean1 = new LoanClosure();
					
					String installmentDate = String.valueOf(getPaidInstallmentsData[i][0])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][1])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][2]);
					
					if(i == getPaidInstallmentsData.length-1){
						lastInstallmentDate = String.valueOf(getPaidInstallmentsData[i][8]);
					}	//end of if statement
					
					bean1.setMonthYear(installmentDate);
					bean1.setPrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][3])));
					bean1.setInterestAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][4])));
					bean1.setInstallmentAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][5])));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][6])));
					
					if(String.valueOf(getPaidInstallmentsData[i][7]).equals("Y")){
						mapdata.put("installment"+count,"Paid");
					}	//end of if statement
					else{
						mapdata.put("installment"+count,"To Be Paid");
					}	//end of else statement
					
					
					totalEmi += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][5]));
					totalIntPaid += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][4]));
					totalPrincipal += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][3]));
					
					tableList.add(bean1);
					
					count++;
					dateFlag = true;
				}	//end of for loop
			}	//end of if statement
			else{
				dateFlag = false;
				getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(12), applCode);
				
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][3]);
				logger.info("lastInstallmentDate 11111   "+lastInstallmentDate);
			}	//end of else statement
			
			Object [] firstInstallment = calculateFirstInstallment(bean, stringDate, lastInstallmentDate, principal, interestRate, 
											installments, dateFlag, String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]));
			
			LoanClosure bean1 = new LoanClosure();
			
			if(dateFlag){
				bean1.setMonthYear(String.valueOf(firstInstallment[0]));
			}	//end of if statement
			else{
				bean1.setMonthYear(String.valueOf(getPaidInstallmentsData[0][2]));
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]);
				logger.info("lastInstallmentDate for reducing    "+lastInstallmentDate);
			}	//end of else statement
				
			if(interestType.equals("I")){
				bean1.setPrincipalAmt(formatter.format(Double.parseDouble(""+firstInstallment[1])));
				bean1.setInterestAmt(formatter.format(Double.parseDouble(""+firstInstallment[2])));
				bean1.setInstallmentAmt(formatter.format(Double.parseDouble(""+firstInstallment[3])));
				bean1.setBalancePrincipalAmt(Utility.twoDecimals(principalAmount));
				mapdata.put("installment"+count,"To Be Paid");
				count++;
				
				totalEmi += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[3])));
				totalIntPaid += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[2])));
				totalPrincipal += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[1])));
				tableList.add(bean1);
				
				
			}
			Calendar calInst = Calendar.getInstance();
			Date instDate = sdf.parse(lastInstallmentDate);
			calInst.setTime(instDate);
			
			calInst.add(calInst.MONTH, 1);
		
			model.initiate(context, session);
		if(installments!=0){
			String [][] installmentData = model.calculateInstallment(principal, installments, interestRate, interestType, sdf.format(calInst.getTime()),Double.parseDouble(String.valueOf(firstInstallment[2])),false);
			model.terminate();
			
			if(installmentData != null && installmentData.length !=0){
				if(interestType.equals("I")){
					for (int i = 0; i < installmentData.length; i++) {
						LoanClosure bean2 = new LoanClosure();
						
						bean2.setMonthYear(String.valueOf(installmentData[i][0]));
						bean2.setPrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][1])));
						bean2.setInterestAmt(formatter.format(Double.parseDouble(installmentData[i][2])));
						bean2.setInstallmentAmt(formatter.format(Double.parseDouble(installmentData[i][3])));
						bean2.setBalancePrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][4])));
						mapdata.put("installment"+count,"To Be Paid");
						
						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double.parseDouble(installmentData[i][2]);
						totalPrincipal += Double.parseDouble(installmentData[i][1]);
						tableList.add(bean2);
						
						count++;
					}	//end of for loop
				}
				else{
					for (int i = 0; i < installmentData.length; i++) {
						LoanClosure bean2 = new LoanClosure();
						
						bean2.setMonthYear(String.valueOf(installmentData[i][0]));
						bean2.setPrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][1])));
						bean2.setInterestAmt(formatter.format(Double.parseDouble(installmentData[i][2])));
						bean2.setInstallmentAmt(formatter.format(Double.parseDouble(installmentData[i][3])));
						bean2.setBalancePrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][4])));
						mapdata.put("installment"+count,"To Be Paid");
						
						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double.parseDouble(installmentData[i][2]);
						totalPrincipal += Double.parseDouble(installmentData[i][1]);
						tableList.add(bean2);
						
						count++;
					}	//end of for loop
				}
				if (!bean.getInterestType().equals("R")) {
					bean.setTotalPrincipalAmt(formatter.format(Double.parseDouble(bean.getSanctionAmount())));
					bean.setTotalInstallmenteAmt(formatter.format((Double.parseDouble(bean.getSanctionAmount()))
							+ totalIntPaid));
				}	//end of if statement
				else
					bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
					bean.setTotalInterestAmt(formatter.format(totalIntPaid));
					 
					
				
			}	//end of if statement
		}
		bean.setInstallmentFlag("true");
		bean.setInstallmentList(tableList);
		request.setAttribute("mapData", mapdata);	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			// TODO: handle exception
		}	//end of try-catch block
	}
	/**@Method rescheduleInstallmentsForPrinc
	 * @Purpose To reschedule the installment details for the selected application
	 * @param bean
	 * @param principalAmount
	 * @param interestRate
	 * @param interestType
	 * @param noOfInstallment
	 * @param startDate
	 * @param request
	 */
	public void rescheduleInstallmentsForPrinc(LoanClosure bean, String principalAmount, String interestRate, 
			String interestType, String noOfInstallment, String startDate, HttpServletRequest request){
		
		LoanProccessModel model = new LoanProccessModel();
		
		Double principal = Double.parseDouble(principalAmount);
		//int installments = Integer.parseInt(noOfInstallment);
		
		int installments = 0;
		String noOfInstallmentString =String.valueOf(Math.ceil(principal/Double.parseDouble(bean.getMonthlyPrincAmount())));
		installments =Integer.parseInt(noOfInstallmentString.substring(0,noOfInstallmentString.indexOf(".")));
		HashMap mapdata  = new HashMap();
		
		logger.info("hiddden "+bean.getInterestType());
		
		double totalEmi = 0.0;
		double totalIntPaid = 0.0;
		double totalPrincipal = 0.0;
		int count = 0;
		
		ArrayList<Object> tableList = new ArrayList<Object>();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date startDateParse = sdf.parse(startDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDateParse);
			//cal.add(cal.MONTH, 1);
			
			String lastInstallmentDate = "";
			boolean dateFlag = false;
			String stringDate = sdf.format(cal.getTime());
			
			logger.info("stringDate  "+stringDate);
			
			Object [] applCode = {bean.getLoanAppCode()};
			
			Object [][] getPaidInstallmentsData = null;
			
			getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(10), applCode);
			
			if(getPaidInstallmentsData != null && getPaidInstallmentsData.length != 0){
				for (int i = 0; i < getPaidInstallmentsData.length; i++) {
					LoanClosure bean1 = new LoanClosure();
					
					String installmentDate = String.valueOf(getPaidInstallmentsData[i][0])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][1])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][2]);
					
					if(i == getPaidInstallmentsData.length-1){
						lastInstallmentDate = String.valueOf(getPaidInstallmentsData[i][8]);
					}	//end of if statement
					
					bean1.setMonthYear(installmentDate);
					bean1.setPrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][3])));
					bean1.setInterestAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][4])));
					bean1.setInstallmentAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][5])));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][6])));
					
					if(String.valueOf(getPaidInstallmentsData[i][7]).equals("Y")){
						mapdata.put("installment"+count,"Paid");
					}	//end of if statement
					else{
						mapdata.put("installment"+count,"To Be Paid");
					}	//end of else statement
					
					
					totalEmi += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][5]));
					totalIntPaid += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][4]));
					totalPrincipal += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][3]));
					
					tableList.add(bean1);
					
					count++;
					dateFlag = true;
				}	//end of for loop
			}	//end of if statement
			else{
				dateFlag = false;
				getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(12), applCode);
				
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][3]);
				logger.info("lastInstallmentDate 11111   "+lastInstallmentDate);
			}	//end of else statement
			
			Object [] firstInstallment = calculateFirstInstallment(bean, stringDate, lastInstallmentDate, principal, interestRate, 
											installments, dateFlag, String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]));
			
			LoanClosure bean1 = new LoanClosure();
			
			if(dateFlag){
				bean1.setMonthYear(String.valueOf(firstInstallment[0]));
			}	//end of if statement
			else{
				bean1.setMonthYear(String.valueOf(getPaidInstallmentsData[0][2]));
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]);
				logger.info("lastInstallmentDate for reducing    "+lastInstallmentDate);
			}	//end of else statement
				
			
				bean1.setPrincipalAmt(formatter.format(Double.parseDouble(""+firstInstallment[1])));
				bean1.setInterestAmt(formatter.format(Double.parseDouble(""+firstInstallment[2])));
				bean1.setInstallmentAmt(formatter.format(Double.parseDouble(""+firstInstallment[3])));
				bean1.setBalancePrincipalAmt(Utility.twoDecimals(principalAmount));
				mapdata.put("installment"+count,"To Be Paid");
				count++;
				
				totalEmi += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[3])));
				totalIntPaid += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[2])));
				totalPrincipal += Double.parseDouble(formatter.format(Double.parseDouble(""+firstInstallment[1])));
				tableList.add(bean1);
				
				
		
			Calendar calInst = Calendar.getInstance();
			Date instDate = sdf.parse(lastInstallmentDate);
			calInst.setTime(instDate);
			
			calInst.add(calInst.MONTH, 1);
		
			model.initiate(context, session);
		
			
			
			logger.info("balance principal after prepayment=="+principal);
			
			String [][]installmentData =  model.calcReduceInterestInstallmentSch(principal-(Double.parseDouble(bean.getMonthlyPrincAmount())),installments-1,Double.parseDouble(interestRate),
					sdf.format(calInst.getTime()),Double.parseDouble(bean.getMonthlyPrincAmount()));
			
			//String [][] installmentData = model.calculateInstallment(principal, installments, interestRate, interestType, sdf.format(calInst.getTime()),Double.parseDouble(String.valueOf(firstInstallment[2])));
			model.terminate();
			
			if(installmentData != null && installmentData.length !=0){
				
					for (int i = 0; i < installmentData.length; i++) {
						LoanClosure bean2 = new LoanClosure();
						
						bean2.setMonthYear(String.valueOf(installmentData[i][0]));
						bean2.setPrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][1])));
						bean2.setInterestAmt(formatter.format(Double.parseDouble(installmentData[i][2])));
						bean2.setInstallmentAmt(formatter.format(Double.parseDouble(installmentData[i][3])));
						bean2.setBalancePrincipalAmt(formatter.format(Double.parseDouble(installmentData[i][4])));
						mapdata.put("installment"+count,"To Be Paid");
						
						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double.parseDouble(installmentData[i][2]);
						totalPrincipal += Double.parseDouble(installmentData[i][1]);
						tableList.add(bean2);
						
						count++;
					}	//end of for loop
				
				
				
					bean.setTotalPrincipalAmt(formatter.format(Double.parseDouble(bean.getSanctionAmount())));
					bean.setTotalInstallmenteAmt(formatter.format((Double.parseDouble(bean.getSanctionAmount()))
							+ totalIntPaid));
					bean.setTotalInterestAmt(formatter.format(totalIntPaid));
					 
					
				bean.setInstallmentFlag("true");
				bean.setInstallmentList(tableList);
				request.setAttribute("mapData", mapdata);	
			}	//end of if statement
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			// TODO: handle exception
		}	//end of try-catch block
	}
	/**@Method rescheduleInstallmentsForEmi
	 * @Purpose To reschedule the installment details for the selected application
	 * @param bean
	 * @param principalAmount
	 * @param interestRate
	 * @param interestType
	 * @param noOfInstallment
	 * @param startDate
	 * @param request
	 */
	public void rescheduleInstallmentsForEmi(LoanClosure bean, String principalAmount, String interestRate, 
			double emiAmount, String startDate, HttpServletRequest request){
		
		LoanProccessModel model = new LoanProccessModel();
		
		double principal = Double.parseDouble(principalAmount);
		
		HashMap mapdata  = new HashMap();
		
		logger.info("hiddden "+bean.getInterestType());
		
		double totalEmi = 0.0;
		double totalIntPaid = 0.0;
		double totalPrincipal = 0.0;
		int count = 0;
		
		ArrayList<Object> tableList = new ArrayList<Object>();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date startDateParse = sdf.parse(startDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDateParse);
			//cal.add(cal.MONTH, 1);
			
			String lastInstallmentDate = "";
			boolean dateFlag = false;
			String stringDate = sdf.format(cal.getTime());
			
			logger.info("stringDate  "+stringDate);
			
			Object [] applCode = {bean.getLoanAppCode()};
			
			Object [][] getPaidInstallmentsData = null;
			
			getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(10), applCode);
			
			if(getPaidInstallmentsData != null && getPaidInstallmentsData.length != 0){
				for (int i = 0; i < getPaidInstallmentsData.length; i++) {
					LoanClosure bean1 = new LoanClosure();
					
					String installmentDate = String.valueOf(getPaidInstallmentsData[i][0])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][1])+"-"
											 +String.valueOf(getPaidInstallmentsData[i][2]);
					
					if(i == getPaidInstallmentsData.length-1){
						lastInstallmentDate = String.valueOf(getPaidInstallmentsData[i][8]);
					}	//end of if statement
					
					bean1.setMonthYear(installmentDate);
					bean1.setPrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][3])));
					bean1.setInterestAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][4])));
					bean1.setInstallmentAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][5])));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String.valueOf(getPaidInstallmentsData[i][6])));
					
					if(String.valueOf(getPaidInstallmentsData[i][7]).equals("Y")){
						mapdata.put("installment"+count,"Paid");
					}	//end of if statement
					else{
						mapdata.put("installment"+count,"To Be Paid");
					}	//end of else statement
					
					
					totalEmi += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][5]));
					totalIntPaid += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][4]));
					totalPrincipal += Double.parseDouble(String.valueOf(getPaidInstallmentsData[i][3]));
					
					tableList.add(bean1);
					
					count++;
					dateFlag = true;
				}	//end of for loop
			}	//end of if statement
			else{
				dateFlag = false;
				getPaidInstallmentsData = getSqlModel().getSingleResult(getQuery(12), applCode);
				
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][3]);
				logger.info("lastInstallmentDate 11111   "+lastInstallmentDate);
			}	//end of else statement
			
			if(!dateFlag){
				lastInstallmentDate = String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]);
			}
			double firstInterest = 0.0;

			LoanClosure bean1 = new LoanClosure();
			
			
				bean1.setMonthYear(String.valueOf(getPaidInstallmentsData[0][2]));
				
				logger.info("lastInstallmentDate for reducing    "+lastInstallmentDate);
			
				
			
			Calendar calInst = Calendar.getInstance();
			Date instDate = sdf.parse(lastInstallmentDate);
			calInst.setTime(instDate);
			
			calInst.add(calInst.MONTH, 1);
		
			model.initiate(context, session);
			String [][] installmentData = null;
			if(bean.getInterestType().equals("N")){
				installmentData = model.calculateNoOfInstallment(principal, emiAmount,sdf.format(calInst.getTime()));
				
			}else{
				firstInterest = generateFirstInterest(bean, stringDate, lastInstallmentDate, principal, interestRate, 
						 dateFlag, String.valueOf(getPaidInstallmentsData[0][0])+"-"+String.valueOf(getPaidInstallmentsData[0][1]));
			if(bean.getInterestType().equals("R")){
				installmentData = model.calculateNoOfInstallmentForReduce(principal, emiAmount,Double.parseDouble(interestRate),  sdf.format(calInst.getTime()));
			}else
				
				installmentData = model.calculateNoOfInstallment(principal, emiAmount,interestRate,  sdf.format(calInst.getTime()),firstInterest);
			}
			model.terminate();
			logger.info("installmentData length=="+installmentData.length);
			if(installmentData != null && installmentData.length !=0){
				
				
					for (int i = 0; i < installmentData.length; i++) {
						LoanClosure bean2 = new LoanClosure();
						
						bean2.setMonthYear(String.valueOf(installmentData[i][0]));
						bean2.setPrincipalAmt(Utility.twoDecimals(installmentData[i][1]));
						bean2.setInterestAmt(Utility.twoDecimals(installmentData[i][2]));
						bean2.setInstallmentAmt(Utility.twoDecimals(installmentData[i][3]));
						bean2.setBalancePrincipalAmt(Utility.twoDecimals(installmentData[i][4]));
						mapdata.put("installment"+count,"To Be Paid");
						
						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double.parseDouble(installmentData[i][2]);
						totalPrincipal += Double.parseDouble(installmentData[i][1]);
						tableList.add(bean2);
						
						count++;
					}	//end of for loop
				
				
					/*bean.setTotalPrincipalAmt(formatter.format(Double.parseDouble(bean.getSanctionAmount())).replace(",", ",")));
					bean.setTotalInstallmenteAmt(formatter.format((Double.parseDouble(bean.getSanctionAmount()))
							+ totalIntPaid)));
				
					bean.setTotalInterestAmt(formatter.format(totalIntPaid)));*/
					
					if (!bean.getInterestType().equals("R")) {
						bean.setTotalPrincipalAmt(formatter.format(Double.parseDouble(bean.getSanctionAmount())));
						bean.setTotalInstallmenteAmt(formatter.format((Double.parseDouble(bean.getSanctionAmount()))
								+ totalIntPaid));
					}	//end of if statement
					else
						bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
						bean.setTotalInterestAmt(formatter.format(totalIntPaid));
					
				bean.setInstallmentFlag("true");
				bean.setInstallmentList(tableList);
				request.setAttribute("mapData", mapdata);	
			}	//end of if statement
			else{
				bean.setInstallmentFlag("false");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			// TODO: handle exception
		}	//end of try-catch block
	}
	/**@method calculateNumberOfDays 
	 * @purpose to calculate the no of days between two dates
	 * @param fromDate
	 * @param toDate
	 * @return no of days between two dates
	 */
	public int calculateNumberOfDays(String fromDate, String toDate){
		int daysBetween = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Date fromDateParse = sdf.parse(fromDate);
			Date toDateParse   = sdf.parse(toDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);
			
			Calendar calTo = Calendar.getInstance();
			calTo.setTime(toDateParse);
			
			while(cal.before(calTo)){
				cal.add(cal.DAY_OF_MONTH, 1);  
				daysBetween++;  
			}	//end of while loop
			//logger.info("daysBetween   "+daysBetween);
		}catch (Exception e) {
			logger.error("error in date parse "+e);
			//e.printStackTrace();
			// TODO: handle exception
		}	//end of try-catch block
		return daysBetween;
	}
	
	/**@method generateFirstInterest 
	 * @purpose to calculate details for first installment
	 * @param bean
	 * @param prePayDate
	 * @param lastInstallmentDate
	 * @param principalAmount
	 * @param interestRate
	 * @param installments
	 * @param dateFlag
	 * @param startDate
	 * @return installment details object array
	 */
	public double generateFirstInterest(LoanClosure bean, String prePayDate, String lastInstallmentDate, 
			double principalAmount, String interestRate, boolean dateFlag, String startDate){
		
		
		double firstInterest =0.0;
		String date = "";
		int noOfDays = 0;
		double interestAmount = 0.0;
		
		//int daysBeforePrePay = calculateNumberOfDays(lastInstallmentDate, prePayDate);
		//logger.info("daysBeforePrePay  "+daysBeforePrePay);
		
		//interestAmount = Double.parseDouble(interestAmount(Double.parseDouble(bean.getBalanceAmount()), interestRate, daysBeforePrePay));
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			if(dateFlag){
				date = lastInstallmentDate;
			}	//end of if statement
			else{
				date = startDate;
 			}	//end of else statement
			
			Date fromDateParse = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);
			cal.add(cal.MONTH, 1);
			
			/**
			 * select the pre payment date, pre payment amount and balance amount at time of pre payment
			 * form HRMS_LOAN_CLOSURE to calculate the interest amount for first installment after every pre payment
			 */
			String selectQuery = "SELECT TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY'), LOAN_PREPAY_AMOUNT, LOAN_BALANCE_AMOUNT "
								  +"FROM HRMS_LOAN_CLOSURE "
								  +"WHERE LOAN_APPL_CODE = "+bean.getLoanAppCode()+" AND LOAN_PREPAY_DATE BETWEEN "
								  +"TO_DATE('"+lastInstallmentDate+"', 'DD-MM-YYYY') AND TO_DATE('"+sdf.format(cal.getTime())+"', 'DD-MM-YYYY')"
								  +" ORDER BY LOAN_CLOSURE_CODE";
			
			Object [][] prePayData = getSqlModel().getSingleResult(selectQuery);	//getting the data from HRMS_LOAN_CLOSURE in prePayData object array
			
					
					
			if(prePayData != null && prePayData.length != 0){	//if pre payment data available 
				double princiAmount = 0.0;
				for (int i = 0; i < prePayData.length; i++) {	//then iterator over the prePayData array
					if(i == 0){
						//calculate the no of days between last installment date and last pre payment date
						noOfDays = calculateNumberOfDays(lastInstallmentDate, String.valueOf(prePayData[i][0]));
						princiAmount = Double.parseDouble(String.valueOf(prePayData[i][2]));	//principal amount at the time of first pre payment
					}	//end of if statement
					else{
						//calculate the no of days between two successive pre payment dates
						noOfDays = calculateNumberOfDays(String.valueOf(prePayData[i-1][0]), String.valueOf(prePayData[i][0]));
						princiAmount -= Double.parseDouble(String.valueOf(prePayData[i-1][1]));		//principal amount at the time of each pre payment
					}	//end of else statement
					
					logger.info("noOfDays if-----------------"+noOfDays);
					logger.info("princiAmount-----------------"+princiAmount);
					logger.info("interestRate-----------------"+interestRate);
					
					//calculate interest amount after each pre payment 
					interestAmount += Double.parseDouble(interestAmount(princiAmount, interestRate, noOfDays));
					logger.info("total interest amount  "+interestAmount);
				}	//end of for loop
				princiAmount -= Double.parseDouble(String.valueOf(prePayData[prePayData.length-1][1]));	//principal amount at the time of last pre payment
				
				//calculate no of days between last pre payment date and latest pre payment date
				noOfDays = calculateNumberOfDays(String.valueOf(prePayData[prePayData.length-1][0]), prePayDate);
				logger.info("noOfDays after-----------------"+noOfDays);
				logger.info("princiAmount after-----------------"+princiAmount);
				logger.info("interestRate after-----------------"+interestRate);
				
				interestAmount += Double.parseDouble(interestAmount(princiAmount, interestRate, noOfDays));	//calculate interest amount for this duration
			}	//end of if statement
			else{	//if pre payment data is not available then
				//calculate the no of days between last installment date and latest pre payment date
				noOfDays = calculateNumberOfDays(lastInstallmentDate, prePayDate);
				logger.info("daysBeforePrePay  "+noOfDays);
				logger.info("principal amount  "+Double.parseDouble(bean.getBalanceAmount()));
				
				//calculate interest amount for this duration
				interestAmount = Double.parseDouble(interestAmount(Double.parseDouble(bean.getBalanceAmount()), interestRate, noOfDays));
			}	//end of else statement
			//calculate the no of days between latest pre payment date and next installment date 
			noOfDays = calculateNumberOfDays(prePayDate, sdf.format(cal.getTime()));
			logger.info("daysAfterPrePay  "+noOfDays);
			
			logger.info("principal amount last  "+principalAmount);
			
			//calculate interest amount for this duration
			interestAmount += Double.parseDouble(interestAmount(principalAmount, interestRate, noOfDays));
		} catch (Exception e) {
			logger.error("error in date parse "+e);
			// TODO: handle exception
		}	//end of try-catch block
		
		//calculate installment amount
		
		firstInterest = interestAmount;
		return firstInterest;
	}
	/**@method calculateFirstInstallment 
	 * @purpose to calculate details for first installment
	 * @param bean
	 * @param prePayDate
	 * @param lastInstallmentDate
	 * @param principalAmount
	 * @param interestRate
	 * @param installments
	 * @param dateFlag
	 * @param startDate
	 * @return installment details object array
	 */
	public Object[] calculateFirstInstallment(LoanClosure bean, String prePayDate, String lastInstallmentDate, 
			double principalAmount, String interestRate, int installments, boolean dateFlag, String startDate){
		
		Object [] firstInstallment = new Object [4];
		String date = "";
		int noOfDays = 0;
		double interestAmount = 0.0;
		
		//int daysBeforePrePay = calculateNumberOfDays(lastInstallmentDate, prePayDate);
		//logger.info("daysBeforePrePay  "+daysBeforePrePay);
		
		//interestAmount = Double.parseDouble(interestAmount(Double.parseDouble(bean.getBalanceAmount()), interestRate, daysBeforePrePay));
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			if(dateFlag){
				date = lastInstallmentDate;
			}	//end of if statement
			else{
				date = startDate;
 			}	//end of else statement
			
			Date fromDateParse = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateParse);
			cal.add(cal.MONTH, 1);
			
			/**
			 * select the pre payment date, pre payment amount and balance amount at time of pre payment
			 * form HRMS_LOAN_CLOSURE to calculate the interest amount for first installment after every pre payment
			 */
			String selectQuery = "SELECT TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY'), LOAN_PREPAY_AMOUNT, LOAN_BALANCE_AMOUNT "
								  +"FROM HRMS_LOAN_CLOSURE "
								  +"WHERE LOAN_APPL_CODE = "+bean.getLoanAppCode()+" AND LOAN_PREPAY_DATE BETWEEN "
								  +"TO_DATE('"+lastInstallmentDate+"', 'DD-MM-YYYY') AND TO_DATE('"+sdf.format(cal.getTime())+"', 'DD-MM-YYYY')"
								  +" ORDER BY LOAN_CLOSURE_CODE";
			
			Object [][] prePayData = getSqlModel().getSingleResult(selectQuery);	//getting the data from HRMS_LOAN_CLOSURE in prePayData object array
			
			//int daysAfterPrePay = calculateNumberOfDays(prePayDate, sdf.format(cal.getTime()));
			//logger.info("daysAfterPrePay  "+daysAfterPrePay);
			
			//interestAmount += Double.parseDouble(interestAmount(principalAmount, interestRate, daysAfterPrePay));
			
			firstInstallment [0] =  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime());
			
			logger.info("date-----------------"+firstInstallment [0]);
			
			if(prePayData != null && prePayData.length != 0){	//if pre payment data available 
				double princiAmount = 0.0;
				for (int i = 0; i < prePayData.length; i++) {	//then iterator over the prePayData array
					if(i == 0){
						//calculate the no of days between last installment date and last pre payment date
						noOfDays = calculateNumberOfDays(lastInstallmentDate, String.valueOf(prePayData[i][0]));
						princiAmount = Double.parseDouble(String.valueOf(prePayData[i][2]));	//principal amount at the time of first pre payment
					}	//end of if statement
					else{
						//calculate the no of days between two successive pre payment dates
						noOfDays = calculateNumberOfDays(String.valueOf(prePayData[i-1][0]), String.valueOf(prePayData[i][0]));
						princiAmount -= Double.parseDouble(String.valueOf(prePayData[i-1][1]));		//principal amount at the time of each pre payment
					}	//end of else statement
					
					logger.info("noOfDays if-----------------"+noOfDays);
					logger.info("princiAmount-----------------"+princiAmount);
					logger.info("interestRate-----------------"+interestRate);
					
					//calculate interest amount after each pre payment 
					interestAmount += Double.parseDouble(interestAmount(princiAmount, interestRate, noOfDays));
					logger.info("total interest amount  "+interestAmount);
				}	//end of for loop
				princiAmount -= Double.parseDouble(String.valueOf(prePayData[prePayData.length-1][1]));	//principal amount at the time of last pre payment
				
				//calculate no of days between last pre payment date and latest pre payment date
				noOfDays = calculateNumberOfDays(String.valueOf(prePayData[prePayData.length-1][0]), prePayDate);
				logger.info("noOfDays after-----------------"+noOfDays);
				logger.info("princiAmount after-----------------"+princiAmount);
				logger.info("interestRate after-----------------"+interestRate);
				
				interestAmount += Double.parseDouble(interestAmount(princiAmount, interestRate, noOfDays));	//calculate interest amount for this duration
			}	//end of if statement
			else{	//if pre payment data is not available then
				//calculate the no of days between last installment date and latest pre payment date
				noOfDays = calculateNumberOfDays(lastInstallmentDate, prePayDate);
				logger.info("daysBeforePrePay  "+noOfDays);
				logger.info("principal amount  "+Double.parseDouble(bean.getBalanceAmount()));
				
				//calculate interest amount for this duration
				interestAmount = Double.parseDouble(interestAmount(Double.parseDouble(bean.getBalanceAmount()), interestRate, noOfDays));
			}	//end of else statement
			//calculate the no of days between latest pre payment date and next installment date 
			noOfDays = calculateNumberOfDays(prePayDate, sdf.format(cal.getTime()));
			logger.info("daysAfterPrePay  "+noOfDays);
			
			logger.info("principal amount last  "+principalAmount);
			
			//calculate interest amount for this duration
			interestAmount += Double.parseDouble(interestAmount(principalAmount, interestRate, noOfDays));
		} catch (Exception e) {
			logger.error("error in date parse "+e);
			// TODO: handle exception
		}	//end of try-catch block
		
		//calculate installment amount
		double monthlyPrincAmt =0;
		if(bean.getHiddenCalType().equals("P")){
			monthlyPrincAmt =Double.parseDouble(bean.getMonthlyPrincAmount());
			firstInstallment[1] = formatter.format(monthlyPrincAmt);
		}else{
			if(installments==0){
				firstInstallment[1] = 0;
			}else{
			firstInstallment[1] = formatter.format((principalAmount/installments));
			}
		}
		
		double installmentAmount = Double.parseDouble(String.valueOf(firstInstallment[1]))+interestAmount;
		logger.info("installmentAmount  "+installmentAmount);
		
		firstInstallment[2] = formatter.format(interestAmount);
		firstInstallment[3] = formatter.format(installmentAmount);
		
		logger.info("firstInstallment[1]  "+firstInstallment[1]);
		logger.info("firstInstallment[2]  "+firstInstallment[2]);
		logger.info("firstInstallment[3]  "+firstInstallment[3]);
		return firstInstallment;
	}
	
	/**@method interestAmount 
	 * @purpose to calculate interest amount for first installment
	 * @param principalAmount
	 * @param interestRate
	 * @param noOfDays
	 * @return interest amount
	 */
	public String interestAmount(double principalAmount, String interestRate, int noOfDays){
		String interestAmount = Utility.twoDecimals(((principalAmount * Double.parseDouble(interestRate)*noOfDays))/(100.0*365));
		logger.info("intAmt inside generateFirstInterest==="+interestAmount);
		
		return interestAmount;
	}
	
	/**@Method showInstallmentSchedule
	 * @Purpose To retrieve all the installment details for the selected application
	 * @param bean
	 * @param request
	 */
	public void showInstallmentSchedule(LoanClosure bean, HttpServletRequest request){
		try {
			logger.info("inside showInstallmentSchedule");
			String monthYear = "";
			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			ArrayList<Object> tableList = new ArrayList<Object>();
			HashMap mapdata = new HashMap();
			
			Object[] loanApplCode = { bean.getLoanAppCode() };
			Object[] loanClosureCode = { bean.getLoanClosureCode(), bean.getLoanAppCode()};
			Object [][]noOfInstallment = getSqlModel().getSingleResult(getQuery(13), loanClosureCode);
			
			if(!bean.getInterestType().equals("I")){
				bean.setNoOfInstallmentOther(checkNull(String.valueOf(noOfInstallment[0][1])));
			}else{
				bean.setNoOfInstallmentsReduceInt(checkNull(String.valueOf(noOfInstallment[0][1])));
			}
			bean.setEmiAmount(checkNull(String.valueOf(noOfInstallment[0][0])));
			bean.setMonthlyPrincAmount(checkNull(String.valueOf(noOfInstallment[0][2])));
			
			if(!bean.getEmiAmount().equals("")){
				 bean.setHiddenCalType("E");
			 }else if(!bean.getMonthlyPrincAmount().equals("")){
				 bean.setHiddenCalType("P");
			 } else{
				 bean.setHiddenCalType("I");
			 }
			 
			Object[][] installData = getSqlModel().getSingleResult(getQuery(8), loanApplCode);
			
			for (int i = 0; i < installData.length; i++) {
				LoanClosure bean1 = new LoanClosure();
				
				monthYear = String.valueOf(installData[i][0])+ "-" + String.valueOf(installData[i][1])
							+"-" + String.valueOf(installData[i][2]);
				
				bean1.setMonthYear(monthYear);
				bean1.setPrincipalAmt(String.valueOf(Double.parseDouble(String.valueOf(installData[i][3]))));
				bean1.setInterestAmt(String.valueOf(Double.parseDouble(String.valueOf(installData[i][4]))));
				bean1.setInstallmentAmt(String.valueOf(Double.parseDouble(String.valueOf(installData[i][5]))));
				bean1.setBalancePrincipalAmt(Utility.twoDecimals(String.valueOf(installData[i][6])));
				
				totalEmi += Double.parseDouble(String.valueOf(installData[i][5]));
				totalIntPaid += Double.parseDouble(String.valueOf(installData[i][4]));
				totalPrincipal += Double.parseDouble(String.valueOf(installData[i][3]));
				
				if(String.valueOf(installData[i][7]).equals("Y"))
					mapdata.put("installment"+i,"Paid");
				else
					mapdata.put("installment"+i,"To Be Paid");
				tableList.add(bean1);
			}	//end of for loop
			
			bean.setInstallmentFlag("true");
			/*
			 * bean.setTotalInstallmenteAmt(String.valueOf(Double.parseDouble(bean.getSanctionAmount())));
			 * bean.setTotalInterestAmt(String.valueOf(Double.parseDouble("0.0")));
			 * bean.setTotalPrincipalAmt(String.valueOf(Double.parseDouble(bean.getSanctionAmount())));
			 */
			if (!bean.getInterestType().equals("R")) {
				bean.setTotalPrincipalAmt(String.valueOf(Double.parseDouble(bean.getSanctionAmount())));
				bean.setTotalInstallmenteAmt(String.valueOf((Double.parseDouble(bean.getSanctionAmount()))
						+ totalIntPaid));
			}	//end of if
			else
				
				  bean.setTotalInstallmenteAmt(Utility.twoDecimals(totalEmi));
				  bean.setTotalInterestAmt(Utility.twoDecimals(totalIntPaid));
				
				
			bean.setInstallmentList(tableList);
			request.setAttribute("mapData", mapdata);	
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e);
		}
	}
	
	/**@Method checkNull
	 * @Purpose To check whether the value is null or not
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else {
			return result;
		}	//end of else
	}
	
	/**@Method setBalanceAmount
	 * @Purpose To calculate the balance amount and set it to the respective field
	 * @param bean
	 */
	public void setBalanceAmount(LoanClosure bean){
		logger.info("in setBalanceAmount method");
		double paidAmount     = 0; //Double.parseDouble(bean.getAmountPaid());
		Object [] loanApplCode = {bean.getLoanAppCode()};
		
		Object [][] result = getSqlModel().getSingleResult(getQuery(3), loanApplCode);
		
		Object [][] prePayAmount = getSqlModel().getSingleResult(getQuery(11), loanApplCode);
		
		if(result != null && result.length != 0){
			paidAmount = Double.parseDouble(String.valueOf(result[0][0]))
						+ Double.parseDouble(String.valueOf(prePayAmount[0][0]));
			bean.setAmountPaid(formatter.format(paidAmount));
		}	//end of if
		else {
			bean.setAmountPaid("0");
		}	//end of else
		
		double sanctionAmount = Double.parseDouble((bean.getSanctionAmount()));
		//double paidAmount     = Double.parseDouble(bean.getAmountPaid());
		
		double balanceAmount  = sanctionAmount-paidAmount;
		
		bean.setBalanceAmount(formatter.format(balanceAmount).replace(",", ""));
		logger.info("bean.getInterestType() "+bean.getInterestType());
		//bean.setInterestType(bean.getInterestType());
	}
}
