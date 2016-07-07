package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.HandicapMaster;

import org.paradyne.model.admin.master.HandicapModel;


/**
 * @author pranali 
 * Date 24-04-07
 */
public class HandicapMasterAction extends org.struts.lib.ParaActionSupport {

	

	HandicapMaster handicapMaster;
	
	public org.paradyne.bean.admin.master.HandicapMaster getHandicapMaster() 
	{
		
		return handicapMaster;   
	}     
	
	
	public Object getModel()
	{
		
		return handicapMaster;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		handicapMaster = new HandicapMaster();
	}
	public void setHandicapMaster(org.paradyne.bean.admin.master.HandicapMaster handicapMaster) 
	{
		
		this.handicapMaster = handicapMaster;
	}
	
	public void prepare() throws Exception
	{
		handicapMaster =new HandicapMaster();
	}
	
	public String save()throws Exception 
	{
		
		HandicapModel model = new HandicapModel();
		
		model.initiate(context,session);
		boolean result ;
		if(handicapMaster.getCatagoryID().equals(""))
		{
			
			 result = model.addHandicap(handicapMaster);
			 
		}
		else
		{
			
			 result = model.modHandicap(handicapMaster);
			 
		}
		model.terminate();
		 
		if(result)
		{
			addActionMessage(getText("addMessage",""));
		} 
		else 
		{
			addActionMessage("Handicap Catagory can not be added");
		}
		
		return "success";
	}
	public String reset() throws Exception
	{
		
		try
		{
			
			handicapMaster.setCatagoryID("");
			handicapMaster.setCatagoryName("");
			
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
		logger.info("in DELETE");
		HandicapModel model = new HandicapModel();
		
		model.initiate(context,session);
		boolean result ;
		
		result = model.deleteHandicap(handicapMaster);  
		
		if(result)
		{
			logger.info("in DELETE********");
			handicapMaster.setCatagoryID(" ");
			handicapMaster.setCatagoryName(" ");
			
		}
	
		model.terminate();
		return "success";
	}
	
	
	public String report()throws Exception 
	{
		logger.info("in report");
		HandicapModel model = new HandicapModel();
		
		model.initiate(context,session);
		logger.info("before handicapreport");
		model.getHandicapReport(handicapMaster);
		
		model.terminate();	
		return "report";    
	}
	
	public String f9action() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = " SELECT CATG_ID,CATG_NAME FROM HRMS_HANDI_CATG";		
		
		
		String[] headers={"Catagory code", "Catagory name"};
		
		String[] headerWidth={"50", "50"};
		
		
		String[] fieldNames={"handicapMaster.catagoryID","handicapMaster.catagoryName"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
}
