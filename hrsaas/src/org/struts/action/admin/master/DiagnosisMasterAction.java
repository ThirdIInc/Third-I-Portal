package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DiagnosisMaster;

import org.paradyne.model.admin.master.DiagnosisMasterModel;
/*
 * Pradeep Kumar Sahoo
 * Date:04.07.2007
 */

public class DiagnosisMasterAction extends org.struts.lib.ParaActionSupport {
	
	DiagnosisMaster dm=null;

	public DiagnosisMaster getDm() {
		return dm;
	}


	public void setDm(DiagnosisMaster dm) {
		this.dm = dm;
	}

	
	public Object getModel(){
		
		return dm;
	}
	

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		dm = new DiagnosisMaster();
		dm.setMenuCode(229);
	}
	
public String delete() throws Exception {
		
		logger.info("Inside Delete");
		DiagnosisMasterModel model= new DiagnosisMasterModel();
		model.initiate(context,session);
		boolean result=model.delDiag(dm);
		model.Data(dm,request);
		model.terminate();
		if (result){
			addActionMessage(getText("Record deleted Successfully"));
			dm.setDName("");
			dm.setDMCode("");
			dm.setDCode("");
		}
		else {
		addActionMessage("This record is referenced in other resources.So cannot delete.");
		}
		return "success";
	}
	
	
	
	public String reset() throws Exception{
		try{	
		
		dm.setDMCode("");
		dm.setDName("");
		dm.setDCode("");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	
	
	/*public String report() throws Exception{
		DiagnosisMasterModel model= new DiagnosisMasterModel();
		model.initiate(context,session);
		model.callReport(dm);
		model.terminate();
		return "report";
	}*/
	
	public String report()throws Exception 
	{
		DiagnosisMasterModel model = new DiagnosisMasterModel();
	 model.initiate(context,session);			
	 model.getReport(dm,request,response,context,session);
	 model.terminate();	
	 return null;    
	}
	
	
	
public String save()throws Exception {
		
		logger.info("In save() ");
		DiagnosisMasterModel model = new DiagnosisMasterModel();
		logger.info("Object created");
		model.initiate(context,session);
		logger.info("Context initiated");
		//boolean result ;
		String str="";
		
        if(dm.getDCode().equals("")){
			
			logger.info("Value of award code"+dm.getDCode());
		//	result = model.addDiag(dm);
			str=model.addDiag(dm);
		}else{
			
			// result = model.modDiag(dm);
			str=model.modDiag(dm);
		}
		logger.info("Model Terminated1");
		model.Data(dm,request);
		model.terminate();
		dm.setDMCode("");
		dm.setDName("");
		dm.setDCode("");
		
	//	if(result) {
			addActionMessage(getText(str));
		//} else {
		//	addActionMessage("Duplicate entry Found!");
	//	}
		logger.info("5");
		return "success";
	}





public String f9action() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = " SELECT  DIAGNOSIS_CODE,DIAGNOSIS_NAME,DIAGNOSIS_MEDICAL_CODE FROM HRMS_DIAGNOSIS  ORDER BY DIAGNOSIS_CODE ";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Diagnosis Code ", "Diagnosis Name","Medical Code"};
	
	String[] headerWidth={"20", "35","35"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"dCode","dName","dMCode"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1,2};
	
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

public void prepare_withLoginProfileDetails() throws Exception {
	DiagnosisMasterModel model = new DiagnosisMasterModel();	
			model.initiate(context,session);
				
			model.Data(dm,request);
			model.terminate();
	}
	public String callPage() throws Exception {
		
		DiagnosisMasterModel model = new DiagnosisMasterModel();
			model.initiate(context,session);
			model.Data(dm,request);
			model.terminate();
		 return SUCCESS;
		
		}
	
	public String calforedit() throws Exception{
		DiagnosisMasterModel model = new DiagnosisMasterModel();
		model.initiate(context,session);
		model.calforedit(dm);
		//getRecord();
		
	
		model.Data(dm,request);
		model.terminate();
		return "success";
	}
	
public String calfordelete()
	{
	DiagnosisMasterModel model = new DiagnosisMasterModel();
	model.initiate(context,session);
		boolean result;
		result = model.calfordelete(dm);
		if(result){
			addActionMessage(getText("Record  Deleted Successfully"));
				//reseting all bean varibles	
			dm.setDMCode("");
			dm.setDName("");
			dm.setDCode("");
			dm.setHiddencode("");
		}
		else{
			addActionMessage("Record can not be deleted");
		}
		
		model.Data(dm,request);
		model.terminate();
		
	return "success";
	}


public String delete1()throws Exception {
	String code[]=request.getParameterValues("hdeleteCode");
	
	DiagnosisMasterModel model = new DiagnosisMasterModel();
	
	model.initiate(context,session);
	boolean result =model.deletecheckedRecords(dm,code);
	
		if(result) {
			addActionMessage(getText("delMessage",""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
	

	
	model.Data(dm,request);
	model.terminate();
	
	return reset();

}



	

}

