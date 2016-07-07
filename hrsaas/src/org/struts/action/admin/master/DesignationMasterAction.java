package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DesignationMaster;
import org.paradyne.model.admin.master.DesignationModel;
/**
 * @author Hemanth 
 * Date 25-04-07
 */



public class DesignationMasterAction extends org.struts.lib.ParaActionSupport {

			DesignationMaster desgMaster;
		
			public org.paradyne.bean.admin.master.DesignationMaster getDesgMaster() 
			{
				return desgMaster;   
			}     
			
			
			public Object getModel()
			{	
				return desgMaster;
			}
			
			
			public void setQuaMaster(org.paradyne.bean.admin.master.DesignationMaster desgMaster) 
			{
				this.desgMaster = desgMaster;
			}
			
			
			static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
			
				desgMaster=new DesignationMaster();
				desgMaster.setMenuCode(35);
			}
			
			
			public String save()throws Exception 
			{
				DesignationModel model = new DesignationModel();
				model.initiate(context,session);
				boolean result ;
				if(desgMaster.getDesgID().equals(""))
				{
					 result = model.addDesignation(desgMaster);
					 if(result){
						 addActionMessage(getText("addMessage",""));
					 }
				}
				else
				{
					 result = model.modDesignation(desgMaster);
					 if(result){
						 addActionMessage(getText("modMessage",""));
					 }
				}
				model.terminate();				 
				if(!result) 
				{
					addActionMessage("Designation can not be added");
				}
				desgMaster.setDesgID("");
				desgMaster.setDesgName("");
				desgMaster.setDesgParCode("");
				desgMaster.setDesgHighCode("");
				desgMaster.setDesgAbbr("");
				desgMaster.setDesgDesc("");
				desgMaster.setDesgLevel("");
				desgMaster.setApprAuth("N");
				desgMaster.setRcmndAuth("N");
				return "success";
			}
			
			
			public String reset() throws Exception
			{
				try
				{
					logger.info("in reset");
					desgMaster.setDesgID("");
					desgMaster.setDesgName("");
					desgMaster.setDesgParCode("");
					desgMaster.setDesgHighCode("");
					desgMaster.setDesgAbbr("");
					desgMaster.setDesgDesc("");
					desgMaster.setDesgLevel("");
					desgMaster.setApprAuth("N");
					desgMaster.setRcmndAuth("N");
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
				DesignationModel model = new DesignationModel();
				model.initiate(context,session);
				boolean result ;
				result = model.deleteDesignation(desgMaster);  
				if(result)
				{
					addActionMessage(getText("delMessage",""));
					desgMaster.setDesgID("");
					desgMaster.setDesgName("");
					desgMaster.setDesgParCode("");
					desgMaster.setDesgHighCode("");
					desgMaster.setDesgAbbr("");
					desgMaster.setDesgDesc("");
					desgMaster.setDesgLevel("");
				}
				model.terminate();
				return "success";
			}
			

			/*public String report()throws Exception 
			{
				DesignationModel model = new DesignationModel();
				model.initiate(context,session);
				model.getDesignationReport(desgMaster);
				model.terminate();	
				return "report";    
			}*/
			
			public String report()throws Exception 
			{
				logger.fatal(">>DESG ACTION>>>>>");
			 DesignationModel model = new DesignationModel();
			 model.initiate(context,session);			
			 model.getReport(desgMaster,request,response,context,session);
			 model.terminate();	
			 return null;    
			}
			
			public String designationRecord()throws Exception {	
				
				DesignationModel model = new DesignationModel();
				model.initiate(context,session);
				model.getDesignationRecord(desgMaster);
				model.terminate();
				return "success";
			}
			 
			
			public String f9action() throws Exception 
			{
				
				logger.info("in f9 action");
				
				String query = " SELECT DESG_ID,DESG_NAME FROM HRMS_DESG ORDER BY DESG_ID";		
				
				
				String[] headers={"Designation Code", "Designation Name"};
				
				String[] headerWidth={"50", "50"};
				
				
				String[] fieldNames={"desgMaster.desgID","desgMaster.desgName"};
				
				
				int[] columnIndex={0,1};
				
				String submitFlag = "true";
				
				
				String submitToMethod="DesignationMaster_designationRecord.action";
				
				
				
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
			}
			
			
			
			
			
			
}
