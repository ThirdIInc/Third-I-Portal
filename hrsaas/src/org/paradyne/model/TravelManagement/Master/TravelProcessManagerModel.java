package org.paradyne.model.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelProcessManager;
import org.paradyne.lib.ModelBase;
import org.struts.action.common.LoginAction;

public class TravelProcessManagerModel extends ModelBase {
	
	 
	
	 public boolean saveApplication(TravelProcessManager processManager){
		 org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
			(org.struts.action.admin.master.RankMasterAction.class);
		 
		  Object[][]obj=new Object[1][9]; 
		 System.out.println("=========Object Block============"+processManager.getHidreqFlag());
	  if(processManager.getHidreqFlag().equals("Y")) {  
			    obj[0][0]="Y";  
		        }
	  else        
			obj[0][0]="N";
		 
	 if(processManager.getHidreqAppFlag().equals("Y"))  {
	          obj[0][1]="Y";
	          }
     else   
	         obj[0][1]="N";
	 if(processManager.getHidschdFlag().equals("Y")){
		 obj[0][2]="Y";
	 }
	 else
		 obj[0][2]="N";
	 if(processManager.getHidschdAppFlag().equals("Y")){
		 obj[0][3]="Y";
	 }
	 else
		 obj[0][3]="N";
	 if(processManager.getHidconfFlag().equals("Y")){
		 obj[0][4]="Y";
	 }
	 else
		 obj[0][4]="N";
	 if(processManager.getHidclaimFlag().equals("Y")){
		 obj[0][5]="Y";
	 }
	 else
		 obj[0][5]="N";
	 if(processManager.getHidappFlag().equals("Y")){
		 obj[0][6]="Y";
	 }
	 else
		 obj[0][6]="N";
	 if(processManager.getHidlevelFlag().equals("Y")){
		 obj[0][7]="Y";
	 }
	 else
		 obj[0][7]="N";
	 
	 obj[0][8]=processManager.getEffectiveDate();
	 String sql ="SELECT PROCESS_MANAGER_ID FROM HRMS_TMS_PROCESS_MANAGER";
	 Object[][]data =getSqlModel().getSingleResult(sql);
	 System.out.println("data.length====="+data.length);
	 if(data!=null && data.length>0)
	 {
		 System.out.println("in delete===");
		String Query1="DELETE FROM HRMS_TMS_PROCESS_MANAGER WHERE PROCESS_MANAGER_ID=1 ";
		  getSqlModel().singleExecute(Query1);
	 }
	  
	  String Query="INSERT INTO HRMS_TMS_PROCESS_MANAGER(PROCESS_MANAGER_ID,PROCESS_MANAGER_APL_REQ,PROCESS_MANAGER_APL_APPR_REQ,PROCESS_MANAGER_SCH_REQ,PROCESS_MANAGER_SCH_APPR_REQ,PROCESS_MANAGER_CONFIRM_REQ,PROCESS_MANAGER_CLAIM_REQ,PROCESS_MANAGER_CLAIM_APPR_REQ,PROCESS_MANAGER_SEC_LEV_REQ,PROCESS_MANAGER_EFFECT_DATE)"
	              +" VALUES(1,? ,? ,? ,? ,? ,? ,?,?,?)"    ;
	 return getSqlModel().singleExecute(Query,obj);
	    
	  
}

  public void showSetting(TravelProcessManager processManager){
		  String Query=" SELECT PROCESS_MANAGER_APL_REQ,PROCESS_MANAGER_APL_APPR_REQ,PROCESS_MANAGER_SCH_REQ,PROCESS_MANAGER_SCH_APPR_REQ,PROCESS_MANAGER_CONFIRM_REQ,PROCESS_MANAGER_CLAIM_REQ,PROCESS_MANAGER_CLAIM_APPR_REQ,PROCESS_MANAGER_SEC_LEV_REQ,PROCESS_MANAGER_EFFECT_DATE"
			  +"  FROM  HRMS_TMS_PROCESS_MANAGER";
		 Object[][] data=getSqlModel().getSingleResult(Query);
		  if(data!=null && data.length>0){
			  if(data[0][0].equals("Y")){
				  processManager.setReqFlag("checked");
				  System.out.println("========if======="+data[0][0]);
			  }else{
				  processManager.setReqFlag("false");
				  System.out.println("=======Else======="+data[0][0]);
			  }
			 if(data[0][1].equals("Y")){
				processManager.setReqAppFlag("checked") ;
			   }
			 else {
				 processManager.setAppFlag("false");
			 }
				 if(data[0][2].equals("Y")){
					 processManager.setSchdFlag("checked");						 
					 }
				 else{
					 processManager.setSchdFlag("false");}
				 if(data[0][3].equals("Y")){
					 processManager.setSchdAppFlag("checked");
				 }else{
					 processManager.setSchdAppFlag("false");}
				 if(data[0][4].equals("Y")){
					 processManager.setConfFlag("checked");
				 }else{
					 processManager.setConfFlag("false");}
				 if(data[0][5].equals("Y")){
					 processManager.setClaimFlag("checked");
				 }else{
					 processManager.setClaimFlag("false");}
				 if(data[0][6].equals("Y")){
					 processManager.setAppFlag("checked");
				 }else{
					 processManager.setAppFlag("false");}
				 if(data[0][7].equals("Y")){
					 processManager.setLevelFlag("checked");
				 }else{
					 processManager.setLevelFlag("false");}
				
				 processManager.setEffectDate(String.valueOf(data[0][8]));
				 
				 /*if(data[0][8].equals("T")){
					 processManager.setFromDate("checked");}
				 else{
					 processManager.setFromDate("false");
  		
				     }*/
		  }
		  else {
			  processManager.setReqFlag("false");
			  processManager.setReqAppFlag("false") ;
			  processManager.setSchdFlag("false");	
			  processManager.setSchdAppFlag("false");
			  processManager.setConfFlag("false");
			  processManager.setClaimFlag("false");
			  processManager.setAppFlag("false");
			  processManager.setLevelFlag("false");
			 processManager.setFromDate("false");
		  }
			
		     
	  }
	}
