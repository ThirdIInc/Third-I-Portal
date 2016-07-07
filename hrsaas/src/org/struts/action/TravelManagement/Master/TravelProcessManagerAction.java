package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.HotelType;
import org.paradyne.bean.TravelManagement.Master.TravelProcessManager;
import org.paradyne.model.TravelManagement.Master.LocationTypeModel;
import org.paradyne.model.TravelManagement.Master.TravelProcessManagerModel;
import org.struts.lib.ParaActionSupport;

public class TravelProcessManagerAction  extends ParaActionSupport{

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	TravelProcessManager processManager;
	@Override
	public void prepare_local() throws Exception {
		processManager= new TravelProcessManager();
		processManager.setMenuCode(785);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return processManager;
	}
	
	@Override
	public String input() throws Exception {
		getNavigationPanel(1);
		 TravelProcessManagerModel model= new TravelProcessManagerModel();
		model.initiate(context, session);
		processManager.setOnLoadFlag(true);
		showSetting();
		model.terminate();
		return INPUT;
		
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		showSetting();
	}
	public TravelProcessManager getProcessManger() {
		return processManager;
	}

	public void setProcessManger(TravelProcessManager processManger) {
		this.processManager = processManager;
	}
	
	 public String save() throws Exception{
		 getNavigationPanel(2);
		  boolean result;
				  TravelProcessManagerModel model= new TravelProcessManagerModel();
				  model.initiate(context,session);
				  result= model.saveApplication( processManager);
		  if(result)
		
		   addActionMessage("Setting save Successully");
		     
		  else
		 
			    addActionMessage("Setting can't saved Successully");
			    model.showSetting(processManager);
			    model.terminate();
		        return "success";
		 
	          }
	
	  public String showSetting() throws Exception{
		  TravelProcessManagerModel model= new TravelProcessManagerModel();
			   model.initiate(context,session);
			   model.showSetting(processManager);
			   model.terminate();
			   return "success";
		  
	 }
	  /*  public String reset()throws Exception{
		  
		 // processManager.setReqFlag("");
		  processManager.setReqAppFlag("");
		  processManager.setSchdFlag("");
		  processManager.setSchdAppFlag("");
		  processManager.setAppFlag("");
		  processManager.setConfFlag("");
		  processManager.setClaimFlag("");
		  processManager.setLevelFlag("");
		  processManager.setFromDate("");
		  
		  
		  return "success";
		
	}*/

	public TravelProcessManager getProcessManager() {
		return processManager;
	}

	public void setProcessManager(TravelProcessManager processManager) {
		this.processManager = processManager;
	}
	
    }
