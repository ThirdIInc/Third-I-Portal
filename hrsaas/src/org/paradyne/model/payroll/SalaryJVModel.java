/**
 * 
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.SalaryJV;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
/**
 * @author Pankaj_Jain
 *
 */
public class SalaryJVModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(SalaryJVModel.class);
	
/***
 * following method is called to return the query for debit.
 * @param bean
 * @param resultQry
 * @param grpStr
 * @param colStr
 * @param str
 * @param grpBy
 * @return
 */	
	public String getDebitQuery(SalaryJV bean,String resultQry,String grpStr,String colStr,String str,String grpBy){
			String finalQry="";
			String debitQry="";
			
			if(!(bean.getDebitCode().equals("") || bean.getDebitCode().equals("null"))){
				//resultQry=resultQry.replace(",LEDGER_CODE", "");
				resultQry+=" AND DEBIT_CODE="+bean.getDebitCode();
			}
			
			resultQry+=" GROUP BY DEBIT_ABBR,DEBIT_NAME";
		if (bean.getFromYear().equals(bean.getToYear())) {
			
			resultQry = resultQry.replace(",LEDGER_CODE","");
			grpStr=grpStr.replace(",LEDGER_CODE","");
			finalQry=resultQry+grpStr;
			debitQry = finalQry.replace("CREDITS","DEBITS");
			debitQry = finalQry.replace("CREDIT","DEBIT");
			} else {
				int year=Integer.parseInt(bean.getFromYear());
				int yr=Integer.parseInt(bean.getToYear());
				if(bean.getEmployeeFlag().equals("true")){
					finalQry+="SELECT DEBIT_ABBR,DEBIT_NAME "+ str+" ,EMP_TOKEN,FULLNAME";
				}else{
					finalQry+="SELECT DEBIT_ABBR,DEBIT_NAME "+ colStr;
				}
				
				finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + grpStr+" ,LEDGER_CODE" +" UNION ";
				
				resultQry = resultQry.replace("CREDITS_" +year, "DEBITS_"
						+ (yr));
				if(bean.getEmployeeFlag().equals("true")){
				finalQry += resultQry + grpStr +" ,LEDGER_CODE" +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
				}else{
					
					finalQry += resultQry + grpStr +",LEDGER_CODE)";
					grpStr=grpStr.replace(",LEDGER_CODE","");
					finalQry+=" GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
					
					
				}
				debitQry = finalQry.replace("CREDITS", "DEBITS");
				debitQry = finalQry.replace("CREDIT", "DEBIT");
			}
		
		return debitQry;
	}
	public String getArrearsDebitQuery(SalaryJV bean,String resultQry,String grpStr,String colStr,String str,String grpBy){

		String finalQry="";
		String debitQry="";
		
		if(!(bean.getDebitCode().equals("") || bean.getDebitCode().equals("null"))){
			//resultQry=resultQry.replace(",LEDGER_CODE", "");
			resultQry+=" AND DEBIT_CODE="+bean.getDebitCode();
		}
		
		resultQry+=" GROUP BY DEBIT_ABBR,DEBIT_NAME";
	if (bean.getFromYear().equals(bean.getToYear())) {
		
		resultQry = resultQry.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE","");
		grpStr=grpStr.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE","");
		finalQry=resultQry+grpStr;
		debitQry = finalQry.replace("CREDITS","DEBITS");
		debitQry = finalQry.replace("CREDIT","DEBIT");
		} else {
			int year=Integer.parseInt(bean.getFromYear());
			int yr=Integer.parseInt(bean.getToYear());
			if(bean.getEmployeeFlag().equals("true")){
				finalQry+="SELECT DEBIT_ABBR,DEBIT_NAME "+ str+" ,EMP_TOKEN,FULLNAME";
			}else{
				finalQry+="SELECT DEBIT_ABBR,DEBIT_NAME "+ colStr;
			}
			
			finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + grpStr+" ,HRMS_ARREARS_LEDGER.ARREARS_CODE" +" UNION ";
			
			resultQry = resultQry.replace("CREDITS_" +year, "DEBITS_"
					+ (yr));
			if(bean.getEmployeeFlag().equals("true")){
			finalQry += resultQry + grpStr +" ,HRMS_ARREARS_LEDGER.ARREARS_CODE" +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
			}else{
				
				finalQry += resultQry + grpStr +",HRMS_ARREARS_LEDGER.ARREARS_CODE.LEDGER_CODE)";
				grpStr=grpStr.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE.LEDGER_CODE","");
				finalQry+=" GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
				
				
			}
			debitQry = finalQry.replace("CREDITS", "DEBITS");
			debitQry = finalQry.replace("CREDIT", "DEBIT");
		}
	
	return debitQry;

	}
	
	
	public void generateReport(SalaryJV bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String query="";
			
			String s = "";
			String str=""; 
			String grpBy="";
			if (bean.getFromMonth().equals(bean.getToMonth()))
				s = Utility.month(Integer.parseInt(bean.getFromMonth())) + " "
						+ bean.getFromYear();
			else
				s = Utility.month(Integer.parseInt(bean.getFromMonth())) + " "
						+ bean.getFromYear() + "-" + " "
						+ Utility.month(Integer.parseInt(bean.getToMonth()))
						+ " " + bean.getToYear();
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean.getReportType(), s);
			
			if(bean.getReportType().equals("Xls")){
				 Object [][] title = new Object[2][3];
				 title [0][0] = "";
				 title [0][1] = "";
				 if(!s.equals("")){
					 title [0][2] = "Salary JV Report of  "+bean.getDivName()+" for "+s; 
				 }else{
					 title [0][2] = "Salary JV Report";  
				 }
				 title [1][0] = "";
				 title [1][1] = "";
				 title [1][2] = "";  
				 
				 int [] cols = {20,20,30};
				 int [] align = {0,0,1};
				rg.tableBodyNoCellBorder(title,cols,align,1);  
			}
			
			rg.setFName("Salary JV Report");
			String columns[] = null;
			int[] bcellWidth = null;
			int[] bcellAlign = null;
			rg.addFormatedText("\n Salary JV Report of  " +bean.getDivName()+" for "+s, 6, 0, 1, 0);
			rg.addFormatedText("\n", 6, 0, 1, 0);
			String resultQry = "";
			Object[][] resultData = null;
			if (bean.getFromYear().equals(bean.getToYear()))
				resultQry = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
						+ " WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION = "
						+ bean.getDivCode()
						+ " AND HRMS_SALARY_LEDGER.LEDGER_MONTH >= "
						+ bean.getFromMonth() + " AND LEDGER_MONTH <="
						+ bean.getToMonth() + " AND LEDGER_YEAR = "
						+ bean.getFromYear();
			else
				resultQry = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
						+ " WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION= "
						+ bean.getDivCode()
						+ " AND ((HRMS_SALARY_LEDGER.LEDGER_MONTH >= "
						+ bean.getFromMonth()
						+ " OR LEDGER_MONTH <="
						+ bean.getToMonth()
						+ " AND LEDGER_YEAR = "
						+ bean.getFromYear()
						+" OR LEDGER_YEAR="
						+ bean.getToYear()
						+ ") OR (HRMS_SALARY_LEDGER.LEDGER_MONTH >= 1 AND LEDGER_MONTH <= "
						+ bean.getToMonth() + " AND LEDGER_YEAR = "
						+ bean.getToYear() + ")) ";
			resultData = getSqlModel().getSingleResult(resultQry);
			String ledgerCode = "", colStr = "", grpStr = "";
			int year = Integer.valueOf(bean.getFromYear());
			if (resultData == null || resultData.length < 1){
				if(bean.getReportType().equals("Pdf"))
				rg.addFormatedText("Salary of  " + bean.getDivName()
						+ " for the period "+s+" has not been processed", 3, 0,
						1, 0);
				else
					rg.addText("Salary of " + bean.getDivName()+
						 " for the period "+s+" has not been processed", 0,1,0);
			}else {
				for (int i = 0; i < resultData.length; i++)
					ledgerCode += resultData[i][0] + ",";
				ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
			}
			resultQry = " SELECT CREDIT_ABBR,CREDIT_NAME ";
			try {
				int count = 4, k = 2;
				boolean brnFlag, deptFlag, empTypeFlag,costCenterFlag,employeeFlag,tradeFlag;
				brnFlag = deptFlag = empTypeFlag = costCenterFlag=employeeFlag=tradeFlag=false;

				if (bean.getBrnFlag().equals("true")) {
					brnFlag = true;
					count=count+2;
				}
				if (bean.getDeptFlag().equals("true")) {
					deptFlag = true;
					count=count+2;
				}
				if (bean.getEmpTypeFlag().equals("true")) {
					empTypeFlag = true;
					count=count+2;
				}
				if(bean.getCostCenterFlag().equals("true")){
					costCenterFlag=true;
					count=count+2;
				}
				if(bean.getTradeFlag().equals("true")){
					tradeFlag=true;
					count=count+2;
				}
				if(bean.getEmployeeFlag().equals("true")){
					employeeFlag=true;
					count=count+2;
				}

				columns = new String[count];
				bcellWidth = new int[count];
				bcellAlign = new int[count];
				
				columns[0] = "Credit/Debit Abbr";
				bcellWidth[0] = 10;
				bcellAlign[0] = 0;

				
				columns[1] = "Credit/Debit Head";
				bcellWidth[1] = 20;
				bcellAlign[1] = 0;

				if (brnFlag) {
					columns[k]="Branch Abbr";
					bcellWidth[k] = 10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Branch";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					k++;
					colStr += " ,CENTER_ABBR, CENTER_NAME ";
					
				}
				if (deptFlag) {
					columns[k]="Department Abbr";
					bcellWidth[k] =10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Department";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					colStr += " ,DEPT_ABBR, DEPT_NAME ";
					k++;
				}
				if (empTypeFlag) {
					columns[k]="Employee Type Abbr";
					bcellWidth[k] =10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Employee Type";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					colStr += " , TYPE_ABBR,TYPE_NAME ";
					k++;
				}
				
				if(costCenterFlag){
					columns[k]="Cost Abbr";
					bcellWidth[k] =10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Cost Center";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					colStr += " , COST_CENTER_ABBR,COST_CENTER_NAME  ";
					k++;
				}
				if(tradeFlag){
					columns[k]="Trade Abbr";
					bcellWidth[k] =10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Trade";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					colStr += " , TRADE_ABBR,TRADE_NAME ";
					k++;
				}
				
		str=colStr;		
				if(employeeFlag){
					columns[k]="Emp No";
					bcellWidth[k] =10;
					bcellAlign[k] = 0;
					k++;
					columns[k] = "Emp Name";
					bcellWidth[k] = 20;
					bcellAlign[k] = 0;
					colStr += " , EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS FULLNAME ";
					k++;
				}
				
				resultQry += colStr
						+ ",'0.0',ROUND(SUM(NVL(SAL_AMOUNT,0))) S,LEDGER_CODE FROM HRMS_CREDIT_HEAD "
						+ " LEFT JOIN HRMS_SAL_CREDITS_"
						+ year
						+ " ON(HRMS_SAL_CREDITS_"
						+ year
						+ ".SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE) "
						+ " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"
						+ year
						+ ".SAL_LEDGER_CODE AND "
						+ " SAL_LEDGER_CODE IN("+ledgerCode+")) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"
						+ year + ".EMP_ID)" ;
						
						if(!bean.getOnHold().equals("A")){
							resultQry +=" INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_"
						+ year + ".SAL_LEDGER_CODE AND SAL_ONHOLD='"+bean.getOnHold()+"' AND HRMS_SAL_CREDITS_"+year+".EMP_ID=HRMS_SALARY_"+year+".EMP_ID) ";  
						}
						
								
				if (brnFlag)
					resultQry += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) ";
				if (deptFlag)
					resultQry += " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) ";
				if (empTypeFlag)
					resultQry += " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) ";
					
				
				if(costCenterFlag){
					resultQry+="	LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)  "
							+"      LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";
					        }
				if(tradeFlag)
					resultQry += "LEFT JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_EMP_OFFC.EMP_TRADE)";
				
				resultQry += " WHERE HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' ";
				query=resultQry;
				if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null"))){
					resultQry+=" AND CREDIT_CODE="+bean.getCreditCode();
				}
				resultQry +=" GROUP BY CREDIT_ABBR,CREDIT_NAME ";

				if (brnFlag)
					grpStr += ", CENTER_ABBR,CENTER_NAME";
				if (deptFlag)
					grpStr += ", DEPT_ABBR,DEPT_NAME";
				if (empTypeFlag)
					grpStr += ",TYPE_ABBR,TYPE_NAME";
				if(costCenterFlag)
					grpStr+=",COST_CENTER_ABBR,COST_CENTER_NAME";
				if(tradeFlag)
					grpStr+=",TRADE_ABBR,TRADE_NAME";
				
				grpBy=grpStr;
				if(employeeFlag)
					grpStr+=",EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
				
				grpStr+=",LEDGER_CODE";
				resultQry += grpStr;

				columns[k] = "Credit";
				bcellWidth[k] = 10;
				bcellAlign[k] = 2;
				k++;
				columns[k] = "Debit";
				bcellWidth[k] = 10;
				bcellAlign[k] = 2;

			} catch (Exception e) {
				logger.error("Exception in salary JV " + e);
				e.printStackTrace();
			}
			String finalQry = "";
			if (bean.getFromYear().equals(bean.getToYear()))
				finalQry = resultQry.replace(",LEDGER_CODE", "");
			else {
			
			
				
				finalQry = "SELECT CREDIT_ABBR,CREDIT_NAME  ";
				if(bean.getEmployeeFlag().equals("true")){
					finalQry+=str+" ,EMP_TOKEN,FULLNAME";
				}else{
					finalQry+=colStr;
				}
				 
				finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + " UNION ";
				resultQry = resultQry.replace("CREDITS_" + year, "CREDITS_"
						+ (++year)).replace("SALARY_" + (year-1), "SALARY_"
						+ (year));
				
				if(bean.getEmployeeFlag().equals("true")){
					finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
					}else{
						grpStr=grpStr.replace(",LEDGER_CODE","");
						finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
					}
			}
			resultData = getSqlModel().getSingleResult(finalQry);
			Object[][] debitResultData = null;
			String debitQry = "";
		if(bean.getDebitCode().equals("") || bean.getDebitCode().equals("null")){
			
				if (bean.getFromYear().equals(bean.getToYear())) {
					resultQry=resultQry.replace(",LEDGER_CODE","");
					debitQry = resultQry.replace("CREDITS","DEBITS");
					debitQry = resultQry.replace("CREDIT","DEBIT");
					if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null")))
					 debitQry = debitQry.replace("AND DEBIT_CODE="+bean.getCreditCode(),"");
				} else {
					int yr=Integer.parseInt(bean.getFromYear());
					finalQry = "SELECT CREDIT_ABBR,CREDIT_NAME  ";
					if(bean.getEmployeeFlag().equals("true")){
						finalQry+=str+" ,EMP_TOKEN,FULLNAME";
					}else{
						finalQry+=colStr;
					}
					resultQry = resultQry.replace("CREDITS_" + year, "CREDITS_"
							+ (yr));
					resultQry = resultQry.replace("SALARY_" + year, "SALARY_"
							+ (yr));
					finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + " UNION ";
					resultQry = resultQry.replace("CREDITS_" + yr, "CREDITS_"
							+ (++yr)).replace("SALARY_" + (yr-1), "SALARY_"
									+ (yr));
					
					if(bean.getEmployeeFlag().equals("true")){
						finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
						}else{
							grpStr=grpStr.replace(",LEDGER_CODE","");
							finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
						}
					
				
					debitQry = finalQry.replace("CREDITS", "DEBITS");
					debitQry = finalQry.replace("CREDIT", "DEBIT");
					if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null")))
						 debitQry = debitQry.replace("AND DEBIT_CODE="+bean.getCreditCode(),"");
				}
	     }else{
	    	 	   debitQry=getDebitQuery(bean,query,grpStr,colStr,str,grpBy);
		
	     }
			debitResultData = getSqlModel().getSingleResult(debitQry);
			if (debitResultData != null && debitResultData.length > 0) {
				Object obj = new Object();
				int totCol = debitResultData[0].length - 1;

				//swap the index for debit object where debit
				// should be 0 and the amount should go in credit  1002950445
				for (int i = 0; i < debitResultData.length; i++) {
					obj = debitResultData[i][totCol];
					debitResultData[i][totCol] = debitResultData[i][totCol - 1];
					debitResultData[i][totCol - 1] = obj;
				}
				//joining two objects of credit and debit in to one

				if (resultData != null && resultData.length > 0
						&& debitResultData != null
						&& debitResultData.length > 0)
					resultData = Utility.joinArrays(resultData,
							debitResultData, 1, 0);
				else
					resultData = debitResultData;
			}
			if (resultData != null && resultData.length > 0){
				Object[][] data=new Object[resultData.length][resultData[0].length];
				for(int i=0;i<resultData.length;i++){
					for(int j=0;j<resultData[0].length;j++){
						data[i][j]=Utility.checkNull(String.valueOf(resultData[i][j]));
					}
				}
				rg.tableBody(columns, data, bcellWidth, bcellAlign);
			}
			try{
			if (resultData != null && resultData.length > 0){
				rg=getArrearsTable(rg,bean);
			}
			}catch (Exception e) {
				logger.info("exception in arrears table");
			}
			
			try {
				rg.createReport(response);
			} catch (Exception e) {
				logger.info("Exception while generating report");
			}
		} catch (Exception e) {
			logger.error("Excpetion in method------ "+e);
			e.printStackTrace();
		}
	} // End of method - generateReport
	
	public ReportGenerator getArrearsTable(ReportGenerator rg,SalaryJV bean){
		
		String query="";
		
		String str=""; 
		String grpBy="";
		String resultQry = "";
		Object[][] resultData = null;
		String columns[] = null;
		int[] bcellWidth = null;
		int[] bcellAlign = null;
		if (bean.getFromYear().equals(bean.getToYear()))
			resultQry = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER "
					+ " WHERE ARREARS_DIVISION = "+ bean.getDivCode()
					+ " AND ARREARS_REF_MONTH >= "+ bean.getFromMonth() + " AND ARREARS_REF_MONTH <="
					+ bean.getToMonth() + " AND ARREARS_REF_YEAR = "
					+ bean.getFromYear();
		else
			resultQry = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER "
				+ " WHERE ARREARS_DIVISION = "+ bean.getDivCode()
					+ " AND ((ARREARS_REF_MONTH >= "
					+ bean.getFromMonth()
					+ " OR ARREARS_REF_MONTH <="
					+ bean.getToMonth()
					+ " AND ARREARS_REF_YEAR = "
					+ bean.getFromYear()
					+" OR ARREARS_REF_YEAR="
					+ bean.getToYear()
					+ ") OR (ARREARS_REF_MONTH >= 1 AND ARREARS_REF_MONTH <= "
					+ bean.getToMonth() + " AND ARREARS_REF_YEAR = "
					+ bean.getToYear() + ")) ";
		resultData = getSqlModel().getSingleResult(resultQry);
		String ledgerCode = "", colStr = "", grpStr = "";
		int year = Integer.valueOf(bean.getFromYear());
		if (resultData == null || resultData.length < 1){
			
		}else {
			for (int i = 0; i < resultData.length; i++)
				ledgerCode += resultData[i][0] + ",";
			ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
		
		resultQry = " SELECT CREDIT_ABBR,CREDIT_NAME ";
		try {
			int count = 4, k = 2;
			boolean brnFlag, deptFlag, empTypeFlag,costCenterFlag,employeeFlag,tradeFlag;
			brnFlag = deptFlag = empTypeFlag = costCenterFlag=employeeFlag=tradeFlag=false;

			if (bean.getBrnFlag().equals("true")) {
				brnFlag = true;
				count=count+2;
			}
			if (bean.getDeptFlag().equals("true")) {
				deptFlag = true;
				count=count+2;
			}
			if (bean.getEmpTypeFlag().equals("true")) {
				empTypeFlag = true;
				count=count+2;
			}
			if(bean.getCostCenterFlag().equals("true")){
				costCenterFlag=true;
				count=count+2;
			}
			if(bean.getTradeFlag().equals("true")){
				tradeFlag=true;
				count=count+2;
			}
			if(bean.getEmployeeFlag().equals("true")){
				employeeFlag=true;
				count=count+2;
			}

			columns = new String[count];
			bcellWidth = new int[count];
			bcellAlign = new int[count];
			
			columns[0] = "Credit/Debit Abbr";
			bcellWidth[0] = 10;
			bcellAlign[0] = 0;

			
			columns[1] = "Credit/Debit Head";
			bcellWidth[1] = 20;
			bcellAlign[1] = 0;

			if (brnFlag) {
				columns[k]="Branch Abbr";
				bcellWidth[k] = 10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Branch";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				k++;
				colStr += " ,CENTER_ABBR, CENTER_NAME ";
				
			}
			if (deptFlag) {
				columns[k]="Department Abbr";
				bcellWidth[k] =10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Department";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				colStr += " ,DEPT_ABBR, DEPT_NAME ";
				k++;
			}
			if (empTypeFlag) {
				columns[k]="Employee Type Abbr";
				bcellWidth[k] =10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Employee Type";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				colStr += " , TYPE_ABBR,TYPE_NAME ";
				k++;
			}
			
			if(costCenterFlag){
				columns[k]="Cost Abbr";
				bcellWidth[k] =10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Cost Center";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				colStr += " , COST_CENTER_ABBR,COST_CENTER_NAME  ";
				k++;
			}
			if(tradeFlag){
				columns[k]="Trade Abbr";
				bcellWidth[k] =10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Trade";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				colStr += " , TRADE_ABBR,TRADE_NAME ";
				k++;
			}
			
	str=colStr;		
			if(employeeFlag){
				columns[k]="Emp No";
				bcellWidth[k] =10;
				bcellAlign[k] = 0;
				k++;
				columns[k] = "Emp Name";
				bcellWidth[k] = 20;
				bcellAlign[k] = 0;
				colStr += " , EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS FULLNAME ";
				k++;
			}
			
			resultQry += colStr
					+ ",'0.0',ROUND(SUM(NVL(ARREARS_AMT,0))) S,HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_ARREARS_CREDIT_"
					+ year
					+ " ON(HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE) "
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_CODE AND "
					+ " HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE IN("+ledgerCode+")) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"
					+ year + ".ARREARS_EMP_ID)" ;
					
					
							
			if (brnFlag)
				resultQry += " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) ";
			if (deptFlag)
				resultQry += " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) ";
			if (empTypeFlag)
				resultQry += " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) ";
				
			
			if(costCenterFlag){
				resultQry+="	LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)  "
						+"      LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)";
				        }
			if(tradeFlag)
				resultQry += "LEFT JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_EMP_OFFC.EMP_TRADE)";
			
			resultQry += " WHERE HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' ";
			query=resultQry;
			if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null"))){
				resultQry+=" AND CREDIT_CODE="+bean.getCreditCode();
			}
			resultQry +=" GROUP BY CREDIT_ABBR,CREDIT_NAME ";

			if (brnFlag)
				grpStr += ", CENTER_ABBR,CENTER_NAME";
			if (deptFlag)
				grpStr += ", DEPT_ABBR,DEPT_NAME";
			if (empTypeFlag)
				grpStr += ",TYPE_ABBR,TYPE_NAME";
			if(costCenterFlag)
				grpStr+=",COST_CENTER_ABBR,COST_CENTER_NAME";
			if(tradeFlag)
				grpStr+=",TRADE_ABBR,TRADE_NAME";
			
			grpBy=grpStr;
			if(employeeFlag)
				grpStr+=",EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
			
			grpStr+=",HRMS_ARREARS_LEDGER.ARREARS_CODE";
			resultQry += grpStr;

			columns[k] = "Credit";
			bcellWidth[k] = 10;
			bcellAlign[k] = 2;
			k++;
			columns[k] = "Debit";
			bcellWidth[k] = 10;
			bcellAlign[k] = 2;

		} catch (Exception e) {
			logger.error("Exception in salary JV " + e);
			e.printStackTrace();
		}
		String finalQry = "";
		if (bean.getFromYear().equals(bean.getToYear()))
			finalQry = resultQry.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE", "");
		else {
		
		
			
			finalQry = "SELECT CREDIT_ABBR,CREDIT_NAME  ";
			if(bean.getEmployeeFlag().equals("true")){
				finalQry+=str+" ,EMP_TOKEN,FULLNAME";
			}else{
				finalQry+=colStr;
			}
			 
			finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + " UNION ";
			resultQry = resultQry.replace("CREDITS_" + year, "CREDITS_"
					+ (++year));
			
			if(bean.getEmployeeFlag().equals("true")){
				finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
				}else{
					grpStr=grpStr.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE","");
					finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
				}
		}
		resultData = getSqlModel().getSingleResult(finalQry);
		Object[][] debitResultData = null;
		String debitQry = "";
	if(bean.getDebitCode().equals("") || bean.getDebitCode().equals("null")){
		
			if (bean.getFromYear().equals(bean.getToYear())) {
				resultQry=resultQry.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE","");
				debitQry = resultQry.replace("CREDITS","DEBITS");
				debitQry = resultQry.replace("CREDIT","DEBIT");
				if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null")))
				 debitQry = debitQry.replace("AND DEBIT_CODE="+bean.getCreditCode(),"");
			} else {
				int yr=Integer.parseInt(bean.getFromYear());
				finalQry = "SELECT CREDIT_ABBR,CREDIT_NAME  ";
				if(bean.getEmployeeFlag().equals("true")){
					finalQry+=str+" ,EMP_TOKEN,FULLNAME";
				}else{
					finalQry+=colStr;
				}
				resultQry = resultQry.replace("CREDITS_" + year, "CREDITS_"
						+ (yr));
				finalQry+= ",'0',ROUND(SUM(S)) FROM (" + resultQry + " UNION ";
				resultQry = resultQry.replace("CREDITS_" + yr, "CREDITS_"
						+ (++yr));
				
				if(bean.getEmployeeFlag().equals("true")){
					finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpBy+" ,EMP_TOKEN,FULLNAME";
					}else{
						grpStr=grpStr.replace(",HRMS_ARREARS_LEDGER.ARREARS_CODE","");
						finalQry += resultQry +") GROUP BY CREDIT_ABBR,CREDIT_NAME "+ grpStr;
					}
				
			
				debitQry = finalQry.replace("CREDITS", "DEBITS");
				debitQry = finalQry.replace("CREDIT", "DEBIT");
				if(!(bean.getCreditCode().equals("") ||  bean.getCreditCode().equals("null")))
					 debitQry = debitQry.replace("AND DEBIT_CODE="+bean.getCreditCode(),"");
			}
     }else{
    	 	   debitQry=getArrearsDebitQuery(bean,query,grpStr,colStr,str,grpBy);
	
     }
		debitResultData = getSqlModel().getSingleResult(debitQry);
		if (debitResultData != null && debitResultData.length > 0) {
			Object obj = new Object();
			int totCol = debitResultData[0].length - 1;

			//swap the index for debit object where debit
			// should be 0 and the amount should go in credit  1002950445
			for (int i = 0; i < debitResultData.length; i++) {
				obj = debitResultData[i][totCol];
				debitResultData[i][totCol] = debitResultData[i][totCol - 1];
				debitResultData[i][totCol - 1] = obj;
			}
			//joining two objects of credit and debit in to one

			if (resultData != null && resultData.length > 0
					&& debitResultData != null
					&& debitResultData.length > 0)
				resultData = Utility.joinArrays(resultData,
						debitResultData, 1, 0);
			else
				resultData = debitResultData;
		}
		if (resultData != null && resultData.length > 0){
			Object[][] data=new Object[resultData.length][resultData[0].length];
			for(int i=0;i<resultData.length;i++){
				for(int j=0;j<resultData[0].length;j++){
					data[i][j]=Utility.checkNull(String.valueOf(resultData[i][j]));
				}
			}
			if(bean.getReportType().equals("Pdf")){
			Object[][] heading = new Object[1][1];

			int[] cells = { 25 };
			int[] align = { 0 };
			rg.addText("", 1, 1, 1);
			heading[0][0] = "Arrears :";
			
			rg.tableBodyBold(heading, cells, align);
			}else{
				rg.addText("Arrears :", 1, 1, 1);
			}
			rg.tableBody(columns, data, bcellWidth, bcellAlign);
		}
		
		}
		return rg;
		
	}

}//End of class
