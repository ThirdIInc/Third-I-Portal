/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.bean.payroll.incometax.TdsCalculator;
import org.paradyne.bean.payroll.salary.CashClaim;
import org.paradyne.lib.ModelBase;

/**
 * @author varunk
 *
 */
public class TdsCalculatorModel extends ModelBase { static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

////////////////////////////////new for seperate methods////////////////////////////////////////////////////////////
	
	public Object[][] getchapType(TdsCalculator tdsCal) {
		String query = "select INV_CODE,INV_NAME from HRMS_TAX_INVESTMENT ORDER BY INV_CODE";
		
		Object[][]invChapType = getSqlModel().getSingleResult(query);
		return invChapType;
	}

	


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






	
	public TdsCalculator viewInvestment(TdsCalculator invList) {
		
		String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_LIMIT1,NVL(INV_VALID_AMOUNT,0),INV_CHAPTER " 
						+" FROM HRMS_TAX_INVESTMENT "
						+" left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="+invList.getEmpId()+" and INV_FINYEAR_FROM="+invList.getFromYear()+" and INV_FINYEAR_TO="+invList.getToYear()+" ) "
						+" WHERE INV_CHAPTER = 'EXEMPT' order by HRMS_TAX_INVESTMENT.INV_CODE ";
		
		ArrayList<Object> viewList = new ArrayList<Object>();
		double total =0.0;
		Object investmentList[][] = getSqlModel().getSingleResult(query);
		
			for(int i=0;i<investmentList.length;i++ ){
				
				TdsCalculator getList = new TdsCalculator();
				
				getList.setInvExemptId(String.valueOf(investmentList[i][0]));
				getList.setInvExemptName(String.valueOf(investmentList[i][1]));
				
				Object investmentLimit =investmentList[i][2];
				if(investmentLimit == null)
					getList.setInvExemptLimit("0");
				else
					getList.setInvExemptLimit(String.valueOf(investmentList[i][2]));
				
				Object investmentAmt =investmentList[i][2];
				if(investmentAmt == null)
					getList.setInvExemptAmt("0");
				else
					getList.setInvExemptAmt(String.valueOf(investmentList[i][3]));
				
				total += Double.parseDouble(String.valueOf(investmentList[i][3]));
				
				
				viewList.add(getList);
			}
			invList.setTotalExemptInvAmt(String.valueOf(total));
			invList.setEmpExemptInvList(viewList);
		
		return invList ;
	}
	
	public TdsCalculator viewOtherInvestment(TdsCalculator invList) {
		
			String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_LIMIT1,NVL(INV_VALID_AMOUNT,0),INV_CHAPTER " 
			+" FROM HRMS_TAX_INVESTMENT "
			+" left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="+invList.getEmpId()+" and INV_FINYEAR_FROM="+invList.getFromYear()+" and INV_FINYEAR_TO="+invList.getToYear()+" ) "
			+" WHERE INV_CHAPTER = 'OTHER' order by HRMS_TAX_INVESTMENT.INV_CODE ";
			
			ArrayList<Object> viewOtherList = new ArrayList<Object>();
			double total =0.0;
			Object investmentOtherList[][] = getSqlModel().getSingleResult(query);
			
			for(int i=0;i<investmentOtherList.length;i++){
				
				TdsCalculator getOtherList = new TdsCalculator();
				
				getOtherList.setInvOtherId(String.valueOf(investmentOtherList[i][0]));
				getOtherList.setInvOtherName(String.valueOf(investmentOtherList[i][1]));
				
				Object investmentLimit =investmentOtherList[i][2];
				if(investmentLimit == null)
					getOtherList.setInvOtherLimit("0");
				else
					getOtherList.setInvOtherLimit(String.valueOf(investmentOtherList[i][2]));
				
				Object investmentAmt =investmentOtherList[i][2];
				if(investmentAmt == null)
					getOtherList.setInvOtherAmt("0");
				else
					getOtherList.setInvOtherAmt(String.valueOf(investmentOtherList[i][3]));
				
				total += Double.parseDouble(String.valueOf(investmentOtherList[i][3]));
				
				
				viewOtherList.add(getOtherList);
				
			}
			invList.setTotalOtherInvAmt(String.valueOf(total));
			invList.setEmpOtherInvList(viewOtherList);
	
			return invList ;
		}
	
	public TdsCalculator viewParaInvestment(TdsCalculator invList) {
		
		String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_LIMIT1,NVL(INV_VALID_AMOUNT,0),INV_CHAPTER " 
		+" FROM HRMS_TAX_INVESTMENT "
		+" left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="+invList.getEmpId()+" and INV_FINYEAR_FROM="+invList.getFromYear()+" and INV_FINYEAR_TO="+invList.getToYear()+" ) "
		+" WHERE INV_CHAPTER = 'PARA' order by HRMS_TAX_INVESTMENT.INV_CODE ";
		
		ArrayList<Object> viewParaList = new ArrayList<Object>();
		double total =0.0;
		Object investmentParaList[][] = getSqlModel().getSingleResult(query);
		
		for(int i=0;i<investmentParaList.length;i++){
			
			TdsCalculator getParaList = new TdsCalculator();
			
			getParaList.setInvParaId(String.valueOf(investmentParaList[i][0]));
			getParaList.setInvParaName(String.valueOf(investmentParaList[i][1]));
			
			Object investmentLimit =investmentParaList[i][2];
			if(investmentLimit == null)
				getParaList.setInvParaLimit("0");
			else
				getParaList.setInvParaLimit(String.valueOf(investmentParaList[i][2]));
			
			Object investmentAmt =investmentParaList[i][2];
			if(investmentAmt == null)
				getParaList.setInvParaAmt("0");
			else
				getParaList.setInvParaAmt(String.valueOf(investmentParaList[i][3]));
			
			total += Double.parseDouble(String.valueOf(investmentParaList[i][3]));
			
			
			viewParaList.add(getParaList);
			
		}
		invList.setTotalParaInvAmt(String.valueOf(total));
		invList.setEmpParaInvList(viewParaList);

		return invList ;
	}
	
public TdsCalculator viewVIAInvestment(TdsCalculator invList) {
		
		String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_LIMIT1,NVL(INV_VALID_AMOUNT,0),INV_CHAPTER " 
		+" FROM HRMS_TAX_INVESTMENT "
		+" left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="+invList.getEmpId()+" and INV_FINYEAR_FROM="+invList.getFromYear()+" and INV_FINYEAR_TO="+invList.getToYear()+" ) "
		+" WHERE INV_CHAPTER = 'VI-A'  order by HRMS_TAX_INVESTMENT.INV_CODE";
		
		ArrayList<Object> viewVIAList = new ArrayList<Object>();
		double total =0.0;
		Object investmentVIAList[][] = getSqlModel().getSingleResult(query);
		
		for(int i=0;i<investmentVIAList.length;i++){
			
			TdsCalculator getVIAList = new TdsCalculator();
			
			getVIAList.setInvVIAId(String.valueOf(investmentVIAList[i][0]));
			getVIAList.setInvVIAName(String.valueOf(investmentVIAList[i][1]));
			
			Object investmentLimit =investmentVIAList[i][2];
			if(investmentLimit == null)
				getVIAList.setInvVIALimit("0");
			else
				getVIAList.setInvVIALimit(String.valueOf(investmentVIAList[i][2]));
			
			Object investmentAmt =investmentVIAList[i][2];
			if(investmentAmt == null)
				getVIAList.setInvVIAAmt("0");
			else
				getVIAList.setInvVIAAmt(String.valueOf(investmentVIAList[i][3]));
			
			total += Double.parseDouble(String.valueOf(investmentVIAList[i][3]));
			
			
			viewVIAList.add(getVIAList);
			
		}
		invList.setTotalVIAInvAmt(String.valueOf(total));
		invList.setEmpVIAInvList(viewVIAList);

		return invList ;
	}

public TdsCalculator viewRebateInvestment(TdsCalculator invList) {
	
	String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_LIMIT1,NVL(INV_VALID_AMOUNT,0),INV_CHAPTER " 
	+" FROM HRMS_TAX_INVESTMENT "
	+" left join HRMS_EMP_INVESTMENT on (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE and HRMS_EMP_INVESTMENT.EMP_ID ="+invList.getEmpId()+" and INV_FINYEAR_FROM="+invList.getFromYear()+" and INV_FINYEAR_TO="+invList.getToYear()+" ) "
	+" WHERE INV_CHAPTER = 'VI' order by HRMS_TAX_INVESTMENT.INV_CODE ";
	
	ArrayList<Object> viewRebateList = new ArrayList<Object>();
	double total =0.0;
	Object investmentRebateList[][] = getSqlModel().getSingleResult(query);
	
	for(int i=0;i<investmentRebateList.length;i++){
		
		TdsCalculator getRebateList = new TdsCalculator();
		
		getRebateList.setInvRebateId(String.valueOf(investmentRebateList[i][0]));
		getRebateList.setInvRebateName(String.valueOf(investmentRebateList[i][1]));
		
		Object investmentLimit =investmentRebateList[i][2];
		if(investmentLimit == null)
			getRebateList.setInvRebateLimit("0");
		else
			getRebateList.setInvRebateLimit(String.valueOf(investmentRebateList[i][2]));
		
		Object investmentAmt =investmentRebateList[i][2];
		if(investmentAmt == null)
			getRebateList.setInvRebateAmt("0");
		else
			getRebateList.setInvRebateAmt(String.valueOf(investmentRebateList[i][3]));
		
		total += Double.parseDouble(String.valueOf(investmentRebateList[i][3]));
		
		
		viewRebateList.add(getRebateList);
		
	}
	invList.setTotalRebateInvAmt(String.valueOf(total));
	invList.setEmpRebateInvList(viewRebateList);

	return invList ;
}

public TdsCalculator calculatorGrossSal(TdsCalculator tdsCalculator) { //// this method is called from TDS Calculator Action
	
	double totalGrossAmt =0.0;
	double totalIncome =0.0;
	double totalProcessedAmt =0.0;
	
	int pr=0,pr1=0,income=0,processCreditedSalary=0,processCreditedSalary1=0;
	ArrayList<Object> viewGrossList = new ArrayList<Object>();
		
	String queryCredit = "SELECT EMP_ID,HRMS_EMP_CREDIT.CREDIT_CODE,CREDIT_NAME,NVL(CREDIT_AMT,0) "
							+"FROM HRMS_EMP_CREDIT "
							+"LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
							+"WHERE EMP_ID = "+tdsCalculator.getEmpId()+" ";
		Object[][] creditData = getSqlModel().getSingleResult(queryCredit);
		
		
		/////////////////to calculate already process salary
		
		 String process = " SELECT LEDGER_MONTH FROM HRMS_SALARY_LEDGER "
					+"LEFT JOIN HRMS_SALARY_"+tdsCalculator.getFromYear()+" ON (HRMS_SALARY_"+tdsCalculator.getFromYear()+".SAL_LEDGER_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE) "
					+"WHERE LEDGER_YEAR="+tdsCalculator.getFromYear()+" AND LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH>=4 "
					+"AND LEDGER_MONTH<=12 AND EMP_ID ="+tdsCalculator.getEmpId()+" ";
		 Object[][] processData = getSqlModel().getSingleResult(process);
		 
		 if(processData==null){
			 pr=9;
		 }
		 else{
			 pr= 9 - Integer.parseInt(String.valueOf(processData.length));
		 
	
			 if(processData.length!=0)
			 {
			 
			 	 
			 String salQuery ="SELECT NVL(SUM(SAL_TOTAL_CREDIT),0) "
							+" FROM HRMS_SALARY_"+tdsCalculator.getFromYear()+" "
							+" right JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+tdsCalculator.getFromYear()+".EMP_ID and  "
							+" SAL_MONTH IN( SELECT LEDGER_MONTH FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR="+tdsCalculator.getFromYear()+"  "
							+" AND LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH>=4 AND LEDGER_MONTH<=12))   "
							+" WHERE  SAL_MONTH>=4 AND SAL_MONTH<=12 ";
										 
			 Object[][] salData = getSqlModel().getSingleResult(salQuery);
			 
			
				 if(salData!=null){
					 processCreditedSalary = Integer.parseInt(String.valueOf(salData[0][0]));
				 }
			 }
		 } 
		 
		 String process1 = "SELECT LEDGER_MONTH FROM HRMS_SALARY_LEDGER "
							+"INNER JOIN HRMS_SALARY_"+tdsCalculator.getToYear()+" ON (HRMS_SALARY_"+tdsCalculator.getToYear()+".SAL_LEDGER_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE) "
							+"WHERE LEDGER_YEAR="+tdsCalculator.getToYear()+" AND LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH>=1 AND LEDGER_MONTH<=3 AND EMP_ID ="+tdsCalculator.getEmpId()+"";
		 Object[][] processData1 = getSqlModel().getSingleResult(process1);
		 if(processData1==null){
			 pr1=3;
		 }
		 else{
			 pr1=3 - Integer.parseInt(String.valueOf(processData1.length));
			 if(processData1.length!=0)
			 {
				
				 String salQuery1 = "SELECT NVL(SUM(SAL_TOTAL_CREDIT),0)  "
							+"FROM HRMS_SALARY_"+tdsCalculator.getToYear()+" "
							+"right JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+tdsCalculator.getToYear()+".EMP_ID and " 
							+"SAL_MONTH IN( SELECT LEDGER_MONTH FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR="+tdsCalculator.getToYear()+" AND LEDGER_STATUS='SAL_FINAL' "
							+"AND LEDGER_MONTH>=1 AND LEDGER_MONTH<=3))  "
							+"WHERE  SAL_MONTH>=1 AND SAL_MONTH<=3";
	
			 Object[][] salData1 = getSqlModel().getSingleResult(salQuery1);
	
				 if(salData1!=null){	
					 processCreditedSalary1 = Integer.parseInt(String.valueOf(salData1[0][0]));
				 }
			 }
		 } 
		 
		 //////////////////////////////////////////////////////////////	
		
		
		
		for(int i=0;i<creditData.length;i++){
			
			TdsCalculator calculator = new TdsCalculator();
			
			calculator.setCreditId(String.valueOf(creditData[i][1]));
			calculator.setCreditName(String.valueOf(creditData[i][2]));
			
			/*Object creditAmount =creditData[i][3];
			if(creditAmount == null)
				calculator.setCreditAmt("0");
			else
				calculator.setCreditAmt(String.valueOf(creditData[i][3]));*/
			
			if(pr==0 && pr1==0){
				income = Integer.parseInt(String.valueOf(creditData[i][3]));
			}
			else{
				income = Integer.parseInt(String.valueOf(creditData[i][3]))* (pr+pr1);
				
				logger.info("value of income if salary is process------"+income);
			}
			
			calculator.setCreditAmt(String.valueOf(income));
			
			//int totalIncome = (Integer.parseInt(String.valueOf(salArray[i])) + Integer.parseInt(String.valueOf(salArray1[i]))) + income ; 
			
			totalGrossAmt += Double.parseDouble(String.valueOf(income));
			
			
			viewGrossList.add(calculator);
			
		}
		 
		totalProcessedAmt = Double.parseDouble(String.valueOf(processCreditedSalary)+ String.valueOf(processCreditedSalary1));
			tdsCalculator.setProcessedAmt(String.valueOf(totalProcessedAmt));
			logger.info("value of processed amt------------"+tdsCalculator.getProcessedAmt());
		 totalIncome = Double.parseDouble(String.valueOf(processCreditedSalary)+ String.valueOf(processCreditedSalary1)) + totalGrossAmt;
		tdsCalculator.setTotalGrossAmt(String.valueOf(totalIncome));
		tdsCalculator.setEmpGrossSalList(viewGrossList);
		
	return tdsCalculator;
}

public TdsCalculator calculateSlab(TdsCalculator tdsCalculator) {
	
		String genderQuery = " SELECT EMP_GENDER,TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(EMP_DOB,'YYYY') FROM HRMS_EMP_OFFC WHERE EMP_ID ="+tdsCalculator.getEmpId()+"";
		Object[][] gender = getSqlModel().getSingleResult(genderQuery);
		
		String queryMen="select TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where "+
		" TAX_FROM_YEAR="+tdsCalculator.getFromYear()+" and TAX_to_YEAR="+tdsCalculator.getToYear()+" and TAX_EMP_TYPE='M' order by tax_percent" ;
		
		Object[][] taxMen= getSqlModel().getSingleResult(queryMen);
		
		String queryWomen="select TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where "+
		" TAX_FROM_YEAR="+tdsCalculator.getFromYear()+" and TAX_to_YEAR="+tdsCalculator.getToYear()+" and TAX_EMP_TYPE='F' order by tax_percent" ;
		
		Object[][] taxWomen= getSqlModel().getSingleResult(queryWomen);
		
		double nettaxableIncome = Double.parseDouble(String.valueOf(tdsCalculator.getNetTaxableIncome()));
			
		if(Integer.parseInt(String.valueOf(gender[0][1]))>65)
		{
			tdsCalculator = getAmountSer1(nettaxableIncome,tdsCalculator);
		//logger.info("value of total[i][10]---------------------"+taxAmt);
		}
	
	
	return tdsCalculator;
}
public TdsCalculator getAmountSer1(Double total,TdsCalculator tdsCalculator)
{
	logger.info("In get Amount for Senior------------------------"+String.valueOf(total));
	
	String query="select TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where "+
					" TAX_FROM_YEAR="+tdsCalculator.getFromYear()+" and TAX_to_YEAR="+tdsCalculator.getToYear()+" and TAX_EMP_TYPE='S' order by tax_percent" ;
	
	int diff=0;
	double taxAmt=0;
	String xyz = String.valueOf(total);
	double remain = total;
	Object[][] taxData = getSqlModel().getSingleResult(query);
	
	/*if(taxData.length==0 || taxData.length<4){
		return 0;
	}*/
	ArrayList<Object> viewList = new ArrayList<Object>();
	try {
		
	logger.info("length of query--------------------------"+taxData.length);
	
	for(int i=0;i<taxData.length;i++){
		
		TdsCalculator getList = new TdsCalculator();
		//logger.info("length of query--------------------------"+i+"diff"+diff);
		//logger.info("length of query--------------------------"+i+"total"+total);
		
		if(total >=Integer.parseInt(String.valueOf(taxData[i][1])))
		{
			logger.info("In  If-----------------------------------------------"+i+"from amount"+(Integer.parseInt(String.valueOf(taxData[i][0]))));
			logger.info("In  If-----------------------------------------------"+i+"to amount"+(Integer.parseInt(String.valueOf(taxData[i][1]))));
			logger.info("In  If-----------------------------------------------"+i+"Percentage"+(Integer.parseInt(String.valueOf(taxData[i][2]))));
			getList.setSlabFrmAmt(String.valueOf(taxData[i][0]));
			getList.setSlabToAmt(String.valueOf(taxData[i][1]));
			getList.setSlabTaxRate(String.valueOf(taxData[i][2]));
		//	logger.info("In 50000 If-----------------------------------------------"+i+"rowwwwwwwwwwwwwww"+(Integer.parseInt(String.valueOf(taxData[i][1])) - Integer.parseInt(String.valueOf(taxData[i][0]))));
			
			diff= Math.abs(((Integer.parseInt(String.valueOf(taxData[i][1])) - Integer.parseInt(String.valueOf(taxData[i][0])))));
			logger.info("In  If-----------------------------------------------"+i+"Diff="+diff);
				getList.setSlabAmt(String.valueOf(diff));
			
			remain=remain-diff;
			logger.info("In  If-----------------------------------------------"+i+"Remain="+remain);
			taxAmt= ((diff * (Integer.parseInt(String.valueOf(taxData[i][2]))))/100);
			diff=0;
			logger.info("In  If-----------------------------------------------"+i+"TaxAmt="+taxAmt);
				
				getList.setSlabTax(String.valueOf(taxAmt));
				viewList.add(getList);
		}
		else
		{
			logger.info("In else-----------------------------------------------"+i+"from amount"+(Integer.parseInt(String.valueOf(taxData[i][0]))));
			logger.info("In else-----------------------------------------------"+i+"to amount"+(Integer.parseInt(String.valueOf(taxData[i][1]))));
			logger.info("In else-----------------------------------------------"+i+"Percentage"+(Integer.parseInt(String.valueOf(taxData[i][2]))));
			logger.info("In  else-----------------------------------------------"+i+"Remain="+remain);
			
			getList.setSlabFrmAmt(String.valueOf(taxData[i][0]));
			getList.setSlabToAmt(String.valueOf(taxData[i][1]));
			getList.setSlabTaxRate(String.valueOf(taxData[i][2]));
			getList.setSlabAmt(String.valueOf(remain));
			
			taxAmt= (remain * (Integer.parseInt(String.valueOf(taxData[i][2]))))/100;
			logger.info("In  else-----------------------------------------------Tax="+taxAmt);
			getList.setSlabTax(String.valueOf(taxAmt));
			viewList.add(getList);
			break;
			
		}
		
		
		
		//logger.info("In afterlse-----------------------------------------------Tax="+taxAmt);
	}
	tdsCalculator.setTdslabList(viewList);
	
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		
	}
	return tdsCalculator;
}






}