
package org.paradyne.model.payroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;


import org.paradyne.bean.payroll.DaParaMaster;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Venkatesh
 *
 */
public class DaParaModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean checkValidDate(DaParaMaster bean){
		String query=" SELECT NVL(MAX(TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY')),'') FROM HRMS_DA_PARAMETER ";
		Object[][] daDate = getSqlModel().getSingleResult(query);
		boolean res=false;
		String selectdate=bean.getDaEffDate();
		String maxDate=String.valueOf(daDate[0][0]);
		int check=0;
			if(daDate[0][0]==null){
				res= true;
			}
			else{
				check=new Utility().checkDate(selectdate,maxDate);
				if(check==0||check==1){
					res= true;
				}
			}
			return res;
	}
	public boolean addDaPara(DaParaMaster bean,ServletContext context) {
		boolean result1=false;
		Object addObj[][] =new Object[1][2];
			addObj[0][0] =bean.getDaRate(); 
			addObj[0][1] =bean.getDaEffDate();
			logger.info(bean.getDaRate());
			logger.info(bean.getDaEffDate());
			logger.info("into model");
			boolean result= getSqlModel().singleExecute(getQuery(1),addObj);
			if(result){
				result1=updateCreditDA(bean);
			}
			return result1;
			
	}
	
	public boolean modDaPara(DaParaMaster bean) {
		Object addObj[][] =new Object[1][3];
		boolean result=false; 
		boolean result1=false;
		String query="SELECT MAX(DA_CODE) FROM HRMS_DA_PARAMETER ";
		   Object[][] daCode = getSqlModel().getSingleResult(query);
		   logger.info("code"+daCode[0][0]);
		   if(String.valueOf(daCode[0][0]).equals(bean.getDaCode())){
		
			   	addObj[0][0] =bean.getDaRate();
				addObj[0][1] =bean.getDaEffDate(); 
				addObj[0][2] =bean.getDaCode();
				result = getSqlModel().singleExecute(getQuery(2), addObj);
			/*	String selQuery="SELECT CREDIT_AMT ,EMP_ID FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE=1";
				String selQuery=" SELECT SUM(CREDIT_AMT) ,EMP_ID FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE IN (1,2) "
					+" GROUP BY EMP_ID";
				Object[][] rows = getSqlModel().getSingleResult(selQuery);
				logger.info("Length of rows++++++++++"+rows);
				String updateQuery="UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=? *"+bean.getDaRate()+"/100 WHERE EMP_ID=? AND CREDIT_CODE=3";*/
				
				if(result){
					result1=updateCreditDA(bean);
					//result1= getSqlModel().singleExecute(updateQuery,rows);
				}
				
		   }
		   return result1;
				
	}
	
	public boolean updateCreditDA(DaParaMaster bean){
		boolean result1=false;
		String formulaQuery="SELECT CREDIT_FORMULA FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE=3";
		Object[][] res=getSqlModel().getSingleResult(formulaQuery);
		String formula=String.valueOf(res[0][0]);
		logger.info("formula........"+formula);
		logger.info("formula........"+formula.length());
		for (int i = formula.length()-1; i>=0 ; i--) {
			if(formula.charAt(i)=='*'){
				formula=formula.substring(0, i+1);
				formula=formula.concat(String.valueOf(Double.parseDouble(bean.getDaRate())/100));
				break;
			}
			
		}
		/*
		formula=formula.substring(0,formula.length()-2);
		formula=formula.concat(bean.getDaRate());
		*/Object updateObj[][]=new Object[1][1];
		updateObj[0][0]=formula;
	
	boolean update_formula=getSqlModel().singleExecute(getQuery(6),updateObj);
	
	String selQuery="SELECT EMP_ID FROM HRMS_EMP_CREDIT  WHERE CREDIT_CODE=3";
	
	/*String selQuery=" SELECT SUM(CREDIT_AMT) ,EMP_ID FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE IN (1,2) "
		+" GROUP BY EMP_ID";*/
	Object[][] rows = getSqlModel().getSingleResult(selQuery);
	logger.info("Length of rows++++++++++"+rows.length);
	Object [][] daData = new Object[rows.length][2];
	for (int i = 0; i < rows.length; i++) {
		daData[i][1] =String.valueOf(rows[i][0]);
		daData[i][0] = (Utility.expressionEvaluate(new Utility().generateFormula(String.valueOf(rows[i][0]),formula, context)));
		//daData[i][0] = (Utility.expressionEvaluate(new Utility().generateFormula(String.valueOf(rows[i][0]),formula, context)))*(Double.parseDouble(bean.getDaRate())/100);        
	
	}
	
	logger.info("Length of rows++++++++++");
	String updateQuery="UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=? WHERE EMP_ID=? AND CREDIT_CODE=3";
	
	if(update_formula){
		result1= getSqlModel().singleExecute(updateQuery,daData);
	}
	return result1;
	}
	
	public boolean deleteDaPara(DaParaMaster bean) {
		Object addObj[][] =new Object[1][1];
		boolean result=false;
		addObj[0][0] =bean.getDaCode();
		String query="SELECT MAX(DA_CODE) FROM HRMS_DA_PARAMETER ";
		   Object[][] daCode = getSqlModel().getSingleResult(query);
		   logger.info("code"+daCode[0][0]);
		   if(!(String.valueOf(daCode[0][0]).equals(bean.getDaCode()))){
			   result= getSqlModel().singleExecute(getQuery(3),addObj);	
		   }
		return result;
		
	}
	public void getDaParaReport(DaParaMaster bean)
	{
		Object addObj[] =new Object[1];
		ArrayList<Object> dispList=new ArrayList();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for(int i=0; i<data.length; i++) {
			DaParaMaster bean1=new DaParaMaster();
			logger.info("I am get report");
			bean1.setDaCode(String.valueOf(data[i][0]));
			bean1.setDaRate(String.valueOf(data[i][1]));
			bean1.setDaEffDate(String.valueOf(data[i][2]));
			dispList.add(bean1);
		}
		bean.setDaArray(dispList);
		
	}
	public void  getDaParaRecord(DaParaMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getDaCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("I am 2");
			bean.setDaCode(String.valueOf(data[i][0]));
			bean.setDaRate(String.valueOf(data[i][1]));
			bean.setDaEffDate(String.valueOf(data[i][2]));
						
		}
		
	}
}
