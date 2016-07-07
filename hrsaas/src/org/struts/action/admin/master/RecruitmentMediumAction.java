package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.RecruitmentMedium;

import org.paradyne.model.admin.master.RecruitmentmediumModel;
/*
 * author:Pradeep Kumar Sahoo
 */
public class RecruitmentMediumAction extends org.struts.lib.ParaActionSupport{
	
	
	RecruitmentMedium recrtMedium=null;
	/**
	 * @return the recrtMedium
	 */
	public RecruitmentMedium getRecrtMedium() {
		return recrtMedium;
	}


	/**
	 * @param recrtMedium the recrtMedium to set
	 */
	public void setRecrtMedium(RecruitmentMedium recrtMedium) {
		this.recrtMedium = recrtMedium;
	}
	
	
	public Object getModel(){
		
		return recrtMedium;
	}
	
	
	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		recrtMedium = new RecruitmentMedium();
		recrtMedium.setMenuCode(27);

	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		RecruitmentmediumModel model= new RecruitmentmediumModel();
				
				model.initiate(context,session);
				
				model.recData(recrtMedium,request);
				model.terminate();
		}
		public String callPage() throws Exception {
			
			RecruitmentmediumModel model= new RecruitmentmediumModel();
				model.initiate(context,session);
				model.recData(recrtMedium,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			RecruitmentmediumModel model= new RecruitmentmediumModel();
			model.initiate(context,session);
			model.calforedit(recrtMedium);
			
		
			model.recData(recrtMedium,request);
			model.terminate();
			return "success";
		}
		
	public String calfordelete()
		{
		RecruitmentmediumModel model= new RecruitmentmediumModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(recrtMedium);
			if(result){
				addActionMessage(getText("Record deleted Successfully"));
				recrtMedium.setMediumCode("");
				recrtMedium.setMediumName("");
			}
			else{
				addActionMessage("This record is referenced in other resources.So cannot delete.");
			}
			
			model.recData(recrtMedium,request);
			model.terminate();
			
		return "success";
		}
		
	
	public String delete() throws Exception {
		
		logger.info("Inside Delete");
		RecruitmentmediumModel model= new RecruitmentmediumModel();
		model.initiate(context,session);
		boolean result=model.deleteRecrt(recrtMedium);
		model.recData(recrtMedium,request);
		model.terminate();
		if (result){
			addActionMessage(getText("delMessage","Record deleted Successfully"));
			recrtMedium.setMediumCode("");
			recrtMedium.setMediumName("");
			
		}
		else {
		addActionMessage("This record is referenced in other resources.So cannot delete.");
		}
		return "success";
	}
	
	
	
	public String reset() throws Exception{
		try{	
		
		recrtMedium.setMediumCode("");
		recrtMedium.setMediumName("");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	
	
	
public String save()throws Exception {
		
		logger.info("In save() ");
		RecruitmentmediumModel model = new RecruitmentmediumModel();
		logger.info("Object created");
		model.initiate(context,session);
		logger.info("Context initiated");
		/*String str="";
		//boolean result ;
		
		if(recrtMedium.getMediumCode().equals("")){
			logger.info("----mediumName");
			str = model.addRecr(recrtMedium);
		}else{
			logger.info("else modCity");
			 str = model.modRecrt(recrtMedium);
		}*/
		boolean result;

		if (recrtMedium.getMediumCode().equals("")) {
			logger.info("else addDept");
			result = model.addRecr(recrtMedium);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			} else {
				addActionMessage("Recruitment can not be added");
			}
		} else {
			logger.info("else modCity");
			
			result = model.modRecrt(recrtMedium);
			if (result) {
				addActionMessage("Record Updated Successfully");
			} else {
				addActionMessage("Recruitment can not be added");
			}
		}
		model.recData(recrtMedium,request);
		logger.info("Model Terminated1");
		model.terminate();
		
		logger.info("5");
		recrtMedium.setMediumCode("");
		recrtMedium.setMediumName("");
		return "success";
	}


public String report()throws Exception {	
	
	RecruitmentmediumModel model = new RecruitmentmediumModel();
		model.initiate(context,session);
		//model.getMediumReport(recrtMedium);
		model.getReport(recrtMedium,request,response,context,session);
		model.terminate();	
	//return "report"; 
		return null;
	} 


public String f9action() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = " SELECT  MEDIUM_ID,MEDIUM_NAME FROM HRMS_RECUITMENT_MEDIUM ORDER BY MEDIUM_ID ";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Recruitment ID", "Recruitment Name"};
	
	String[] headerWidth={"20", "20"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"mediumCode","mediumName"};
	
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
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod=" ";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}


public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		
		RecruitmentmediumModel model = new RecruitmentmediumModel();
		
		model.initiate(context,session);
		boolean result =model.deletecheckedRecords(recrtMedium,code);
		
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
		

		
		model.recData(recrtMedium,request);
		model.terminate();
		
		return reset();

	}
	
}
	
	
	
	
	
	
	
