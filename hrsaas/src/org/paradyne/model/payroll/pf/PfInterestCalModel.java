/**
 * 
 */
package org.paradyne.model.payroll.pf;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.pf.PfInterestCal;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


/**
 * @author Mangesh Jadhav
 *
 */
public class PfInterestCalModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.pf.PfInterestCalModel.class);
	
	public void calculatePf(PfInterestCal bean,HttpServletRequest request){
		
		String intQuery="SELECT PFT_INTEREST FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)" 
			+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";

		Object [][]pfInterestRate = getSqlModel().getSingleResult(intQuery);
		
		try {
			if (pfInterestRate != null && pfInterestRate.length > 0) {
				bean.setIntRate(formatter.format(Double.parseDouble(String.valueOf(pfInterestRate[0][0]))));
			}
		} catch (Exception e) {
			bean.setIntRate(formatter.format(0));
		}
		try {
			
			 String	empQuery ="SELECT HRMS_SALARY_MISC.EMP_ID FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" WHERE SAL_PFTRUST_FLAG='Y'  AND (TO_CHAR(EMP_LEAVE_DATE,'YYYY') >="+bean.getFromYear()+" OR EMP_LEAVE_DATE IS NULL) AND ( 1=1 "+getConditionString(bean)+")"
						+" AND HRMS_SALARY_MISC.EMP_ID NOT IN((SELECT PF_EMPID FROM HRMS_PF_LEDGER WHERE PF_FROM_YEAR="+bean.getFromYear()+"))";
						
			
		Object[][] empData = getSqlModel().getSingleResult(empQuery);
		logger.info("empData lenght==="+empData.length);
	
			for (int i = 0; i < empData.length; i++) {
				if (!checkRecordAvailable(bean, String.valueOf(empData[i][0]))) {
					savePfDetails(bean, String.valueOf(empData[i][0]));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			displayPfDetails(bean,request);
		
		
	}
	public String getConditionString(PfInterestCal bean){
		boolean isFilterSelected =false;
		String conditionString="";
		try{
		if(!bean.getApplDivisionCode().equals("")){
			conditionString += " AND EMP_DIV IN("+bean.getApplDivisionCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplBranchCode().equals("")){
			conditionString += " AND EMP_CENTER IN("+bean.getApplBranchCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDeptCode().equals("")){
			conditionString += " AND EMP_DEPT IN("+bean.getApplDeptCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDesgCode().equals("")){
			conditionString += " AND EMP_RANK IN("+bean.getApplDesgCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplETypeCode().equals("")){
			conditionString += " AND EMP_TYPE IN("+bean.getApplETypeCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplGradeCode().equals("")){
			conditionString += " AND EMP_CADRE IN("+bean.getApplGradeCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplEmpCode().equals("")){
			conditionString += " AND HRMS_EMP_OFFC.EMP_ID IN("+bean.getApplEmpCode()+")";
			isFilterSelected =true;
		}
		}catch (Exception e) {
			//conditionString ="OR 1=1";
		}
		return conditionString;
	}
public void savePfDetails(PfInterestCal bean,String empId){
		
		double opnBalance =Double.parseDouble(getOpeningBalance(bean,empId));
		
		
		int fromMonth = 4;
		int toMonth =Integer.parseInt(bean.getToMonth());
		int length= 0;
		if(toMonth <4){
			length = 9 + toMonth;
		}else{
			length= toMonth - 3;
		}
		
		ArrayList tableList = new ArrayList();
		
		double pfProgAmt = 0.0;
		double totProgAmt = 0.0;
		double totPfSub = 0.0;
		double totPfRefund = 0.0;
		double totPfLoan = 0.0;
		double totMpCont = 0.0;
		double totPfRepay = 0.0;
		try{
		for (int i = 0; i < length; i++) {
			int monthInt = (fromMonth+i) % 12;
			String toYearString =bean.getToYear();
			String frmYearString =bean.getFromYear();
			String year="";
			if(monthInt==0){
				monthInt =12;
				
			}
			if(monthInt<4){
				year= toYearString;
			
			}else{
				year= frmYearString;
			}
			Object monthData[][]= getSalData(bean,String.valueOf(monthInt),year,empId);
			
			totPfSub += Double.parseDouble(String.valueOf(monthData[0][0]));
			totPfRefund += Double.parseDouble(String.valueOf(monthData[0][1]));
			totMpCont+= Double.parseDouble("0");
			totPfLoan += Double.parseDouble(String.valueOf(monthData[0][2]));
			totPfRepay += Double.parseDouble("0");
			pfProgAmt =Double.parseDouble(String.valueOf(monthData[0][0]))+Double.parseDouble(String.valueOf(monthData[0][1]))+Double.parseDouble("0")+
								pfProgAmt - Double.parseDouble(String.valueOf(monthData[0][2]));
			totProgAmt += pfProgAmt;
			
		}
		double totPfProgActual=(Math.round(totProgAmt/length));
		
		double intAmount= Double.parseDouble(formatter.format((opnBalance+ totPfProgActual)*
				(Double.parseDouble(bean.getIntRate())/100)));
		double totDeposite =Double.parseDouble(formatter.format(totPfSub+totPfRefund+totMpCont));
		
		double totWithdraw =Double.parseDouble(formatter.format(totPfLoan));
		double closingBalance=Double.parseDouble(formatter.format(opnBalance + totDeposite + intAmount - totWithdraw));
		
		boolean result= false;
		
		Object [][] addObj= new Object[1][8];
		addObj [0][0] = empId;
		addObj [0][1] = bean.getFromYear();
		addObj [0][2] = (Integer.parseInt(bean.getFromYear())+1);
		addObj [0][3] = formatter.format(opnBalance);
		addObj [0][4] = formatter.format(closingBalance);
		addObj [0][5] = formatter.format(intAmount);
		addObj [0][6] = formatter.format(totWithdraw);
		addObj [0][7] = formatter.format(totDeposite);
		
		result = getSqlModel().singleExecute(getQuery(1),addObj);
		
		
		}catch (Exception e) {
			bean.setCalcFlag("false");
		}
	}
public void displayPfDetails(PfInterestCal bean,HttpServletRequest request){
		
	String empQuery ="SELECT HRMS_SALARY_MISC.EMP_ID FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" WHERE SAL_PFTRUST_FLAG='Y' AND ( 1=1 "+getConditionString(bean)+")";
	
		String pfLedgerQuery ="SELECT EMP_TOKEN,EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, "
							+" NVL(PF_OPENING_BAL,0),NVL(PF_DEPOSITS,0), NVL(PF_WITHDRAWAL,0),NVL(PF_INT_AMOUNT,0), NVL(PF_CLOSING_BAL,0) FROM HRMS_PF_LEDGER "
							+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=PF_EMPID) WHERE PF_FROM_YEAR="+bean.getFromYear()
							+" AND PF_EMPID IN("+empQuery+") ORDER BY UPPER(NAME)";
		
		Object [][]ledgerData=getSqlModel().getSingleResult(pfLedgerQuery);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),ledgerData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		
		if(ledgerData != null && ledgerData.length >0){
			ArrayList tableList = new ArrayList();
			
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
				PfInterestCal listBean =new PfInterestCal();
				listBean.setEmpTokenList(String.valueOf(ledgerData[i][0]));
				listBean.setEmpCodeList(String.valueOf(ledgerData[i][1]));
				listBean.setEmpNameList(String.valueOf(ledgerData[i][2]));
				listBean.setOpnBalanceList(String.valueOf(ledgerData[i][3]));
				listBean.setDepositeAmtList(String.valueOf(ledgerData[i][4]));
				listBean.setWithdrawAmtList(String.valueOf(ledgerData[i][5]));
				listBean.setIntAmtList(String.valueOf(ledgerData[i][6]));
				listBean.setClsBalanceList(String.valueOf(ledgerData[i][7]));
				tableList.add(listBean);
			}
			bean.setEmpList(tableList);
		}
		
}
	/*public void getDetails(PfInterestCal bean){
		
		//bean.setOpeningBalance(getOpeningBalance(bean));
		
		String intQuery="SELECT PFT_INTEREST FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)" 
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";
		
		Object [][]pfInterestRate = getSqlModel().getSingleResult(intQuery);
		
		try {
			if (pfInterestRate != null && pfInterestRate.length > 0) {
				bean.setIntRate(formatter.format(Double.parseDouble(String.valueOf(pfInterestRate[0][0]))));
			}
		} catch (Exception e) {
			bean.setIntRate(formatter.format(0));
		}
		int fromMonth = 4;
		int toMonth =Integer.parseInt(bean.getToMonth());
		int length= 0;
		if(toMonth <4){
			length = 9 + toMonth;
		}else{
			length= toMonth - 3;
		}
		
		ArrayList tableList = new ArrayList();
		
		double pfProgAmt = 0.0;
		double totProgAmt = 0.0;
		double totPfSub = 0.0;
		double totPfRefund = 0.0;
		double totPfLoan = 0.0;
		double totMpCont = 0.0;
		double totPfRepay = 0.0;
		try{
		for (int i = 0; i < length; i++) {
			PfInterestCal listBean = new PfInterestCal();
			int monthInt = (fromMonth+i) % 12;
			String toYearString =bean.getToYear();
			String frmYearString =bean.getFromYear();
			String year="";
			if(monthInt==0){
				monthInt =12;
				
			}
			if(monthInt<4){
				year= toYearString;
			
			}else{
				year= frmYearString;
			}
			Object monthData[][]= getSalData(bean,String.valueOf(monthInt),year,"");
			for (int j = 0; j < monthData[0].length; j++) {
				logger.info("monthData[0]["+j+"]=="+monthData[0][j]);
			}
			if(monthInt==1)
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase()+"'"+toYearString.substring(toYearString.length()-2));
			else if(monthInt==4){
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase()+"'"+frmYearString.substring(frmYearString.length()-2));
			}else 
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase());
			listBean.setListPfSub(checkNullToZero(String.valueOf(monthData[0][0])));
			totPfSub += Double.parseDouble(listBean.getListPfSub());
			listBean.setListPfRefund(checkNullToZero(String.valueOf(monthData[0][1])));
			totPfRefund += Double.parseDouble(listBean.getListPfRefund());
			listBean.setListPfMPCont("0");
			totMpCont+= Double.parseDouble(listBean.getListPfMPCont());
			listBean.setListPfLoan(checkNullToZero(String.valueOf(monthData[0][2])));
			totPfLoan += Double.parseDouble(listBean.getListPfLoan());
			listBean.setListPfRepay("0");
			totPfRepay += Double.parseDouble(listBean.getListPfRepay());
			pfProgAmt = Double.parseDouble(listBean.getListPfSub())+Double.parseDouble(listBean.getListPfRefund())+Double.parseDouble(listBean.getListPfMPCont())+
								pfProgAmt - Double.parseDouble(listBean.getListPfLoan());
			totProgAmt += pfProgAmt;
			listBean.setListPfProg(String.valueOf(pfProgAmt));
			tableList.add(listBean);
			
		}
		bean.setTotPfSub(String.valueOf(totPfSub));
		bean.setTotPfRefund(String.valueOf(totPfRefund));
		bean.setTotPfLoan(String.valueOf(totPfLoan));
		bean.setTotPfMPCont(String.valueOf(totMpCont));
		bean.setTotPfRepay(String.valueOf(totPfRepay));
		bean.setTotPfProgActual(String.valueOf(Math.round((totProgAmt/length))));
		bean.setTotPfProg(formatter.format(totProgAmt)+" / "+length+" = "+Math.round((totProgAmt/length)));
		
		bean.setIntAmount(formatter.format((Double.parseDouble(bean.getOpeningBalance())+ Double.parseDouble(bean.getTotPfProgActual()))*
				(Double.parseDouble(bean.getIntRate())/100)));
		bean.setTotDeposite(formatter.format(Double.parseDouble(bean.getTotPfSub())+Double.parseDouble(bean.getTotPfRefund())
							+Double.parseDouble(bean.getTotPfMPCont())));
		
		bean.setTotWithdraw(formatter.format(Double.parseDouble(bean.getTotPfLoan())));
		bean.setClosingBalance(formatter.format(Double.parseDouble(bean.getOpeningBalance())+Double.parseDouble(bean.getTotDeposite())+ Double.parseDouble(bean.getIntAmount())
								- Double.parseDouble(bean.getTotWithdraw())));
		bean.setMonthList(tableList);
		}catch (Exception e) {
			bean.setCalcFlag("false");
		}
	}*/
	public boolean save(PfInterestCal bean,HttpServletRequest request){
		boolean result= false ;
		if(!checkRecordAvailable(bean,bean.getEmpId())){
			result = addEmpPfLedger(bean);
		}else{
			result = updateEmpPfLedger(bean,request);
		}
		return result;
	}
	
	public Object [][] getOpeningBalance(Object [][]empData, String empQuery, PfInterestCal bean){
		String opnBalanceQuery="SELECT NVL(PF_CLOSING_BAL,'0'),PF_EMPID,HRMS_EMP_OFFC.EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME FROM HRMS_PF_LEDGER WHERE PF_EMPID IN("+empQuery+") AND "
		+" PF_FROM_YEAR ="+(Integer.parseInt(bean.getFromYear())-1)
		+" ORDER BY UPPER(NAME)";
		
		Object [][]opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
		
		try {
			int j=0;
			for (int i = 0; i < opnBalanceObj.length; i++) {
				if(j< empData.length && String.valueOf(opnBalanceObj[i][1]).equals(String.valueOf(empData[j][1]))){
					empData[j][3] = String.valueOf(opnBalanceObj[i][0]);
					j++;
				}else{
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return empData;
		
	}
	
	public Object [][] getDepositeAmt(Object [][]empData, String empQuery, PfInterestCal bean){
		String aprToDecQuery="SELECT  SUM(SAL_AMOUNT),EMP_ID,  FROM  	HRMS_SALARY_LEDGER A "		
					+" INNER JOIN  HRMS_SAL_DEBITS_"+bean.getFromYear()+" B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND " 
					+" A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF " 
					+" WHERE PFT_EFFECTIVE_DATE = (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
					+" ORDER BY UPPER(NAME) GROUP BY EMP_ID ";
		String janToMarchQuery=" SELECT  SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SALARY_LEDGER A" 		
					+" INNER JOIN  HRMS_SAL_DEBITS_"+bean.getFromYear()+"B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND 	"	
					+" A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF "
					+" WHERE PFT_EFFECTIVE_DATE = (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF "
					+" WHERE PFT_EFFECTIVE_DATE <= SYSDATE))  	GROUP BY EMP_ID";
		
		Object aprToDecData [][]=getSqlModel().getSingleResult(aprToDecQuery);
		try {
			int j=0;
			for (int i = 0; i < aprToDecData.length; i++) {
				if(j< empData.length && String.valueOf(aprToDecData[i][1]).equals(String.valueOf(empData[j][1]))){
					empData[j][4] = Double.parseDouble(String.valueOf(aprToDecData[i][0]))+ Double.parseDouble(String.valueOf(empData[j][4]));
					j++;
				}else{
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Object janToMarchData [][]=getSqlModel().getSingleResult(janToMarchQuery);
		try {
			int j=0;
			for (int i = 0; i < janToMarchData.length; i++) {
				if(j< empData.length && String.valueOf(janToMarchData[i][1]).equals(String.valueOf(empData[j][1]))){
					empData[j][4] = Double.parseDouble(String.valueOf(janToMarchData[i][0]))+ Double.parseDouble(String.valueOf(empData[j][4]));
					j++;
				}else{
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return empData;
		
	}
	
	public Object [][] getWithdrawAmt(Object [][]empData, String empQuery, PfInterestCal bean){
		
		String aprToDecQuery="SELECT  SUM(SAL_AMOUNT),EMP_ID  FROM  	HRMS_SALARY_LEDGER A "		
					+" INNER JOIN  HRMS_SAL_DEBITS_"+bean.getFromYear()+" B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND " 
					+" A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER " 
					+" WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
					+" GROUP BY EMP_ID ";
		String janToMarchQuery=" SELECT  SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SALARY_LEDGER A" 		
					+" INNER JOIN  HRMS_SAL_DEBITS_"+bean.getFromYear()+"B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND 	"	
					+" A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER " 
					+" WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+" GROUP BY EMP_ID";
		
		Object aprToDecData [][]=getSqlModel().getSingleResult(aprToDecQuery);
		try {
			int j=0;
			for (int i = 0; i < aprToDecData.length; i++) {
				if(j< empData.length && String.valueOf(aprToDecData[i][1]).equals(String.valueOf(empData[j][1]))){
					empData[j][5] = Double.parseDouble(String.valueOf(aprToDecData[i][0]))+ Double.parseDouble(String.valueOf(empData[j][5]));
					j++;
				}else{
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Object janToMarchData [][]=getSqlModel().getSingleResult(janToMarchQuery);
		try {
			int j=0;
			for (int i = 0; i < janToMarchData.length; i++) {
				if(j< empData.length && String.valueOf(janToMarchData[i][1]).equals(String.valueOf(empData[j][1]))){
					empData[j][5] = Double.parseDouble(String.valueOf(janToMarchData[i][0]))+ Double.parseDouble(String.valueOf(empData[j][5]));
					j++;
				}else{
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return empData;
		
	}
	
	public String getOpeningBalance(PfInterestCal bean,String empId){
		String openingBalance ="0.0";
		Object [][]opnBalanceObj =null;
		String opnBalanceQuery="";
		
		opnBalanceQuery="SELECT NVL(PF_OPENING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
		+" PF_FROM_YEAR ="+bean.getFromYear();

		opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);

		try {
			if (opnBalanceObj != null && opnBalanceObj.length > 0) {
				
				openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
				
			}else{
				opnBalanceQuery="SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
				+" PF_FROM_YEAR ="+(Integer.parseInt(bean.getFromYear())-1);
				
				opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
				
				openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
			}
		} catch (Exception e) {
			
			opnBalanceQuery="SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
			+" PF_FROM_YEAR ="+(Integer.parseInt(bean.getFromYear())-1);
			
			opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
			try{
				
			openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
			
			}catch (Exception ex) {
				// TODO: handle exception
			}
		}
		
		return openingBalance;
	}
	public boolean checkRecordAvailable(PfInterestCal bean,String empId){
		boolean result= false;
		String checkQuery ="SELECT NVL(PF_CODE,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND PF_FROM_YEAR="+bean.getFromYear();
		
		Object checkObj[][] = getSqlModel().getSingleResult(checkQuery);
		try {
			if (checkObj.length > 0 && checkObj != null ) {
		 result = true;
			}else{
				result = false;
			}
		}catch (Exception e) {
			 result = false;
		}
		return result;
	}
	public boolean addEmpPfLedger(PfInterestCal bean){
		boolean result= false;
		
		Object [][] addObj= new Object[1][8];
		addObj [0][0] = bean.getEmpId();
		addObj [0][1] = bean.getFromYear();
		addObj [0][2] = (Integer.parseInt(bean.getFromYear())+1);
		addObj [0][3] = bean.getOpeningBalance();
		addObj [0][4] = bean.getClosingBalance();
		addObj [0][5] = bean.getIntAmount();
		addObj [0][6] = bean.getTotWithdraw();
		addObj [0][7] = bean.getTotDeposite();
		
		result = getSqlModel().singleExecute(getQuery(1),addObj);
		
		return result;
	}
	
	public boolean updateEmpPfLedger(PfInterestCal bean,HttpServletRequest request){
		boolean result= false;
		
		Object [][] updateObj= new Object[1][7];
		logger.info("opnbALANCE=="+request.getParameter("openingBalance"));
		logger.info("closing=="+request.getParameter("closingBalance"));
		logger.info("intAmount=="+request.getParameter("intAmount"));
		logger.info("deposit=="+request.getParameter("totDeposite"));
		logger.info("totWithdraw=="+request.getParameter("totWithdraw"));
		logger.info("empId=="+request.getParameter("empId"));
		updateObj [0][0] = request.getParameter("openingBalance");
		updateObj [0][1] = request.getParameter("closingBalance");
		updateObj [0][2] = request.getParameter("intAmount");
		updateObj [0][3] = request.getParameter("totWithdraw");
		updateObj [0][4] = request.getParameter("totDeposite");
		updateObj [0][5] = request.getParameter("empId");
		updateObj [0][6] = bean.getFromYear();
		
		for (int i = 0; i < updateObj[0].length; i++) {
			logger.info("updateObj [0]["+i+"]=="+updateObj [0][i]);
		}
		
		result = getSqlModel().singleExecute(getQuery(2),updateObj);
		
		return result;
	}
	public Object [][]getSalData(PfInterestCal bean, String month, String year,String empId){
		Object [][] returnData = new Object[1][3];
		
		String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER " 
					+"LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+"WHERE LEDGER_MONTH="+String.valueOf(month)+" AND LEDGER_YEAR="+String.valueOf(year)+" AND LEDGER_STATUS = 'SAL_FINAL' AND EMP_ID ="+empId;
		
		
		Object ledgerCode[][]= getSqlModel().getSingleResult(ledgerQuery);
		if(Integer.parseInt(month)<10){
			month ="0"+month;
		}
		try{
		if(ledgerCode.length > 0 && ledgerCode != null){
			/*
			 * calculate PF Subs
			 */
			String pfSubQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
				+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) ";
			
			Object [][]pfSubAmt =getSqlModel().getSingleResult(pfSubQuery);
			try{
				returnData [0][0] =String.valueOf(pfSubAmt[0][0]);
			}catch (Exception pfSubEx) {
				returnData [0][0] = "0";
			}
			
			/*
			 * calculate PF Loan refund amt
			 */
			
			String pfLoanRefQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
				+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT LOAN_DEBIT_CODE FROM HRMS_LOAN_MASTER WHERE LOAN_CODE=(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ))) ";
			
			Object [][]pfLoanRefAmt =getSqlModel().getSingleResult(pfLoanRefQuery);
			double loanRefAmt=0;
			try {
				loanRefAmt = Double.parseDouble(String.valueOf(pfLoanRefAmt[0][0]));
			} catch (Exception e) {
				loanRefAmt =0;
			}
			/*String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
				+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
				+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";

			Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);*/
			double preLoanAmt=0;
			/*try {
				preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
			} catch (Exception e) {
				preLoanAmt =0;
			}*/
			try{
				returnData [0][1] =String.valueOf(loanRefAmt+preLoanAmt);
			}catch (Exception pfloanEx) {
				returnData [0][1] = "0";
			}
			
			/*
			 * calculate PF Loan refund amt
			 */
			
		}else{
			
			
			/*String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
				+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
				+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";

			Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);*/
			double preLoanAmt=0;
			/*try {
				preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
			} catch (Exception e) {
				preLoanAmt =0;
			}*/
			returnData [0][0] ="0";					// pf sub AMt
			returnData [0][1] =preLoanAmt;					// pf loan refund 
								
		}
		}catch (Exception e) {
			returnData [0][0] ="0";					// pf sub AMt
			returnData [0][1] ="0";					// pf loan refund 
			
		}
		
		String pfLoanAmtQuery ="SELECT NVL(SUM(LOAN_AMOUNT),0) FROM HRMS_LOAN_SUPPL_APPL "
			+" WHERE EMP_ID="+empId+" AND TO_CHAR(LOAN_DATE,'MM-YYYY')='"+month+"-"+year+"'";

		Object [][]pfLoanAmt =getSqlModel().getSingleResult(pfLoanAmtQuery);
		
		
		try {
			returnData[0][2] = String.valueOf(pfLoanAmt[0][0]);
		} catch (Exception loanEx) {
			returnData[0][2] = "0";
		}
		return returnData;
	}
	public Object [][]getSalData(String empId, String month, String year){
		Object [][] returnData = new Object[1][3];
		
		String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER " 
					+"LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+"WHERE LEDGER_MONTH="+String.valueOf(month)+" AND LEDGER_YEAR="+String.valueOf(year)+" AND LEDGER_STATUS = 'SAL_FINAL' AND EMP_ID ="+empId;
		
		
		Object ledgerCode[][]= getSqlModel().getSingleResult(ledgerQuery);
		if(Integer.parseInt(month)<10){
			month ="0"+month;
		}
		try{
		if(ledgerCode.length > 0 && ledgerCode != null){
			/*
			 * calculate PF Subs
			 */
			String pfSubQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
				+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) ";
			
			Object [][]pfSubAmt =getSqlModel().getSingleResult(pfSubQuery);
			try{
				returnData [0][0] =String.valueOf(pfSubAmt[0][0]);
			}catch (Exception pfSubEx) {
				returnData [0][0] = "0";
			}
			
			/*
			 * calculate PF Loan refund amt
			 */
			
			String pfLoanRefQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
				+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT LOAN_DEBIT_CODE FROM HRMS_LOAN_MASTER WHERE LOAN_CODE=(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ))) ";
			
			Object [][]pfLoanRefAmt =getSqlModel().getSingleResult(pfLoanRefQuery);
			double loanRefAmt=0;
			try {
				loanRefAmt = Double.parseDouble(String.valueOf(pfLoanRefAmt[0][0]));
			} catch (Exception e) {
				loanRefAmt =0;
			}
			/*String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
				+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
				+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";

			Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);*/
			double preLoanAmt=0;
			/*try {
				preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
			} catch (Exception e) {
				preLoanAmt =0;
			}*/
			try{
				returnData [0][1] =String.valueOf(loanRefAmt+preLoanAmt);
			}catch (Exception pfloanEx) {
				returnData [0][1] = "0";
			}
			
			/*
			 * calculate PF Loan refund amt
			 */
			
		}else{
			
			
			/*String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
				+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
				+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";

			Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);*/
			double preLoanAmt=0;
			/*try {
				preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
			} catch (Exception e) {
				preLoanAmt =0;
			}*/
			returnData [0][0] ="0";					// pf sub AMt
			returnData [0][1] =preLoanAmt;					// pf loan refund 
								
		}
		}catch (Exception e) {
			returnData [0][0] ="0";					// pf sub AMt
			returnData [0][1] ="0";					// pf loan refund 
			
		}
		
		String pfLoanAmtQuery ="SELECT SUM(LOAN_AMOUNT),EMP_ID FROM HRMS_LOAN_SUPPL_APPL "
			+" WHERE EMP_ID="+empId+" AND TO_CHAR(LOAN_PAYMENT_DATE,'MM-YYYY')='"+month+"-"+year+"'";
		Object [][]pfLoanAmt =getSqlModel().getSingleResult(pfLoanAmtQuery);
		
		
		try {
			returnData[0][2] = String.valueOf(pfLoanAmt[0][0]);
		} catch (Exception loanEx) {
			returnData[0][2] = "0";
		}
		return returnData;
	}
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
public void getEmpSalDetails(PfInterestCal bean){
		
		bean.setOpeningBalance(getOpeningBalance(bean,bean.getEmpId()));
		
		String intQuery="SELECT PFT_INTEREST FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)" 
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";
		
		Object [][]pfInterestRate = getSqlModel().getSingleResult(intQuery);
		
		try {
			if (pfInterestRate != null && pfInterestRate.length > 0) {
				bean.setIntRate(formatter.format(Double.parseDouble(String.valueOf(pfInterestRate[0][0]))));
			}
		} catch (Exception e) {
			bean.setIntRate(formatter.format(0));
		}
		int fromMonth = 4;
		int toMonth =Integer.parseInt(bean.getToMonth());
		int length= 0;
		if(toMonth <4){
			length = 9 + toMonth;
		}else{
			length= toMonth - 3;
		}
		
		ArrayList tableList = new ArrayList();
		
		double pfProgAmt = 0.0;
		double totProgAmt = 0.0;
		double totPfSub = 0.0;
		double totPfRefund = 0.0;
		double totPfLoan = 0.0;
		double totMpCont = 0.0;
		double totPfRepay = 0.0;
		try{
		for (int i = 0; i < length; i++) {
			PfInterestCal listBean = new PfInterestCal();
			int monthInt = (fromMonth+i) % 12;
			String toYearString =bean.getToYear();
			String frmYearString =bean.getFromYear();
			String year="";
			if(monthInt==0){
				monthInt =12;
				
			}
			if(monthInt<4){
				year= toYearString;
			
			}else{
				year= frmYearString;
			}
			Object monthData[][]= getSalData(bean,String.valueOf(monthInt),year,bean.getEmpId());
			for (int j = 0; j < monthData[0].length; j++) {
				logger.info("monthData[0]["+j+"]=="+monthData[0][j]);
			}
			if(monthInt==1)
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase()+"'"+toYearString.substring(toYearString.length()-2));
			else if(monthInt==4){
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase()+"'"+frmYearString.substring(frmYearString.length()-2));
			}else 
				listBean.setListMonthName(Utility.month(monthInt).toUpperCase());
			listBean.setListPfSub(formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][0])))));
			totPfSub += Double.parseDouble(listBean.getListPfSub());
			listBean.setListPfRefund(formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][1])))));
			totPfRefund += Double.parseDouble(listBean.getListPfRefund());
			listBean.setListPfMPCont("0");
			totMpCont+= Double.parseDouble(listBean.getListPfMPCont());
			listBean.setListPfLoan(formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][2])))));
			totPfLoan += Double.parseDouble(listBean.getListPfLoan());
			listBean.setListPfRepay("0");
			totPfRepay += Double.parseDouble(listBean.getListPfRepay());
			pfProgAmt = Double.parseDouble(formatter.format(Double.parseDouble(listBean.getListPfSub())+Double.parseDouble(listBean.getListPfRefund())+Double.parseDouble(listBean.getListPfMPCont())+
								pfProgAmt - Double.parseDouble(listBean.getListPfLoan())));
			totProgAmt += pfProgAmt;
			listBean.setListPfProg(String.valueOf(pfProgAmt));
			tableList.add(listBean);
			
		}
		bean.setTotPfSub(formatter.format(totPfSub));
		bean.setTotPfRefund(formatter.format(totPfRefund));
		bean.setTotPfLoan(formatter.format(totPfLoan));
		bean.setTotPfMPCont(formatter.format(totMpCont));
		bean.setTotPfRepay(formatter.format(totPfRepay));
		bean.setTotPfProgActual(formatter.format(Math.round((totProgAmt/length))));
		bean.setTotPfProg(formatter.format(totProgAmt)+" / "+length+" = "+Math.round((totProgAmt/length)));
		
		bean.setIntAmount(formatter.format((Double.parseDouble(bean.getOpeningBalance())+ Double.parseDouble(bean.getTotPfProgActual()))*
				(Double.parseDouble(bean.getIntRate())/100)));
		bean.setTotDeposite(formatter.format(Double.parseDouble(bean.getTotPfSub())+Double.parseDouble(bean.getTotPfRefund())
							+Double.parseDouble(bean.getTotPfMPCont())));
		
		bean.setTotWithdraw(formatter.format(Double.parseDouble(bean.getTotPfLoan())));
		bean.setClosingBalance(formatter.format(Double.parseDouble(bean.getOpeningBalance())+Double.parseDouble(bean.getTotDeposite())+ Double.parseDouble(bean.getIntAmount())
								- Double.parseDouble(bean.getTotWithdraw())));
		bean.setMonthList(tableList);
		}catch (Exception e) {
			bean.setCalcFlag("false");
		}
	}
public void setEmployeeDetails(PfInterestCal bean){
	String empQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
		+" (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) AS NAME ,NVL(CENTER_NAME,' '),EMP_STATUS, "
		+" DIV_NAME,DEPT_NAME,RANK_NAME,NVL(SAL_GPFNO,' ') FROM HRMS_EMP_OFFC  "
		+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
		+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
		+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
		+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
		+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
		+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
		+" WHERE HRMS_EMP_OFFC.EMP_ID="+bean.getEmpId();
	
	Object empObject[][]=getSqlModel().getSingleResult(empQuery);
	
	bean.setEmpToken(String.valueOf(empObject[0][0]));
	bean.setEmpName(String.valueOf(empObject[0][1]));
	bean.setDivName(String.valueOf(empObject[0][4]));
	bean.setBranchName(String.valueOf(empObject[0][2]));
	bean.setDeptName(String.valueOf(empObject[0][5]));
	bean.setEmpPfNo(String.valueOf(empObject[0][7]));
	bean.setEmpStatus(String.valueOf(empObject[0][3]));
	bean.setDesgName(String.valueOf(empObject[0][6]));
	
	
}

	public String checkNullToZero(String result){
		if(result ==null ||result.equals("null")){
			return "0";
		}
		else{
			return result;
		}
	}
}
