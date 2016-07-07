/**
 * 
 */
package org.struts.action.common;

import org.paradyne.bean.common.FormulaBuilder;
 import org.struts.lib.ParaActionSupport;

/**
 * @author sunil
 *
 */
public class FormulaBuilderAction extends ParaActionSupport {

	FormulaBuilder builder	;
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		builder = new FormulaBuilder();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	 public Object getModel() {
		// TODO Auto-generated method stub
		return builder;
	}
	
	public String f9Credit() throws Exception 
	{
		
		String query = "SELECT CREDIT_CODE,CREDIT_NAME,TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";		
		
		
		String[] headers={"Credit Code", "Credit Name","Credit Abbreviation"};
		
		String[] headerWidth={"20", "60","20"};
		
		
		String[] fieldNames={"creditCode","creditAbbr","creditAbbr"};
		
		
		int[] columnIndex={0,1,2};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9Debit() throws Exception 
	{
		
		String query = "SELECT DEBIT_CODE , DEBIT_NAME,TRIM(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD  ORDER BY DEBIT_CODE";		
		
		
		String[] headers={"Debit Code" , "Debit Name","Debit Abbreviation"};
		
		String[] headerWidth={"20", "60" , "20"};
		
		
		String[] fieldNames={"debitCode" , "debitAbbr","debitAbbr"};
		
		
		int[] columnIndex={0,1,2};
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

}
