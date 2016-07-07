package org.struts.action.recruitment;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.recruitment.QuestionnaireMasterModel;
import org.paradyne.model.recruitment.RecruitmentSettingModel;
import org.paradyne.bean.Recruitment.QuestionnaireMaster;
import org.paradyne.bean.Recruitment.RecruitmentSetting;

public class RecruitmentSettingAction extends ParaActionSupport {
	RecruitmentSetting recSet=null;
	
	
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		recSet = new RecruitmentSetting();
		
	}
	public void prepare_withLoginProfileDetails(){
		
		
			request.setAttribute("data","N");
	    	request.setAttribute("data1","N");
	    	request.setAttribute("data2","N");
	
		
		
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return recSet;
	}

	public RecruitmentSetting getRecSet() {
		return recSet;
	}

	public void setRecSet(RecruitmentSetting recSet) {
		this.recSet = recSet;
	}

	
	public String save()throws Exception
	{
		boolean result=false;
		RecruitmentSettingModel model= new RecruitmentSettingModel();
		
		model.initiate(context,session);
		if(recSet.getRecuisitonCode().equals("")){
			result=model.addSetting(recSet);
			if(result){
				addActionMessage("Record Saved Successfully !");
			}else addActionMessage("Record can't be saved !");
		}else{
		result=model.modSetting(recSet);
		if(result){
			addActionMessage("Record modified Successfully !");
		}else addActionMessage("Record can't be saved !");
		}
		recSet.setRecuisitonCode("");
		recSet.setReqNo("");
		model.terminate();
		return "success";
		
	}
	
	
	public String getSettings() {
				
		RecruitmentSettingModel model= new RecruitmentSettingModel();
		model.initiate(context,session);
		model.getRecords(recSet, request);
		model.terminate();
		return "success";
		
	}
	
	
	public String reset() throws Exception{
		recSet.setRecuisitonCode("");
		recSet.setReqNo("");
		recSet.setReqPlnFlw("");
		recSet.setSchFlw("");
		recSet.setReqWrkFlw("");
		return "success";
	}
	public String delete () throws Exception{
		boolean result;
		RecruitmentSettingModel model= new RecruitmentSettingModel();
		model.initiate(context,session);
		result=model.delSetting(recSet);
		if(result){
			addActionMessage("Record deleted Successfully !");
			reset();
		}
		else 
		addActionMessage("Record can't be deleted !");
		model.terminate();
		
		return "success";
	}
	
	
	public String f9setaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  REC_SET_CODE,REC_REQ_CODE FROM HRMS_RECRUIT_SETTING ORDER BY REC_SET_CODE "; 
						
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Setting Code","No. of Requirement"};
		
		String[] headerWidth={"20","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"recuisitonCode","reqNo"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="RecruitmentSetting_getSettings.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  EMP_REQ_CODE FROM HRMS_EMP_REQ ORDER BY EMP_REQ_CODE "; 
						
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"No. of Requirement"};
		
		String[] headerWidth={"20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"reqNo"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	
	
	
	
}


