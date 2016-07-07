package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CreditMaster;
import org.paradyne.model.admin.master.CreditModel;
/**
 * @author pranali 
 * Date 25-04-07
 */

public class CreditMasterAction extends org.struts.lib.ParaActionSupport{
	
	CreditMaster creditMaster;
	
	public org.paradyne.bean.admin.master.CreditMaster getCreditMaster() 
	{	
		return creditMaster;   
	}    
	
	public Object getModel()
	{
		return creditMaster;
	}
	
	public void setCreditMaster(org.paradyne.bean.admin.master.CreditMaster creditMaster) 
	{			
		this.creditMaster = creditMaster;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
	
		creditMaster=new CreditMaster();
	}
	
	public String save()throws Exception 
	{
	
		CreditModel model = new CreditModel();
		model.initiate(context,session);
		boolean result ;
		boolean result1 = false ;
		if(creditMaster.getCreditCode().equals(" "))
		{
			 result = model.addCredit(creditMaster);
		}
		else
		{
			 result = model.modCredit(creditMaster);	
		}
		model.terminate();
		 
		if(result1)
		{
			addActionMessage(getText("modMessage",""));
		} 
		if(result)
		{
			addActionMessage(getText("addMessage",""));
		} 
		else 
		{
			addActionMessage("Qualification can not be added");
		}
		return "success";
	}
	
	public String reset() throws Exception
	{
		try
		{
			creditMaster.setCreditCode(" ");
			creditMaster.setCreditName(" ");
			creditMaster.setCreditAbbr(" ");
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}


	public String delete() throws Exception
	{
		CreditModel model = new CreditModel();
		model.initiate(context,session);
		boolean result ;
		result = model.deleteCredit(creditMaster);  
		if(result)
		{
			creditMaster.setCreditCode(" ");
			creditMaster.setCreditName(" ");
			creditMaster.setCreditAbbr(" ");
		}
		model.terminate();
		return "success";
	}
	
	
	public String report()throws Exception 
	{
		CreditModel model = new CreditModel();
		model.initiate(context,session);
		model.getCreditReport(creditMaster);
		model.terminate();	
		return "report";    
	}
	
	
	
	public String f9action() throws Exception 
	{
		
		logger.info("in f9 action &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		
		String query = " SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR FROM HRMS_HR_CREDIT_HEAD ";		
		
		
		String[] headers={"Credit Code", "Credit Name","Credit Abbr"};
		
		String[] headerWidth={"20", "60","20"};
		
		
		String[] fieldNames={"creditMaster.creditCode","creditMaster.creditName","creditMaster.creditAbbr"};
		
		
		int[] columnIndex={0,1,2};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	
	
	
	
}
