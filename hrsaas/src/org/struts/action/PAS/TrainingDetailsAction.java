package org.struts.action.PAS;
import org.paradyne.bean.PAS.TrainingDetails;
import org.paradyne.model.PAS.TrainingDetailsModel;

import org.struts.lib.ParaActionSupport;


public class TrainingDetailsAction extends ParaActionSupport{
	
	TrainingDetails trnDtls;
	
	
	
	public TrainingDetails getTrnDtls() {
		return trnDtls;
	}

	public void setTrnDtls(TrainingDetails trnDtls) {
		this.trnDtls = trnDtls;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trnDtls;
	}
	
	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		trnDtls = new TrainingDetails();
		trnDtls.setMenuCode(748);
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		// TODO Auto-generated method stub
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		String tempCode= (String)request.getAttribute("tempCode");
		trnDtls.setTemplateCode(tempCode);
		
		getNavigationPanel(15);
		
		/*String apprId = "3";
		String templateId = "3";*/
		
		System.out.println("APPR ID -->"+trnDtls.getApprId());
		System.out.println("TMPL ID -->"+trnDtls.getTemplateCode());
		
		String apprId = trnDtls.getApprId();
		String templateId = trnDtls.getTemplateCode();
		
		
		
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		boolean existing = model.checkExistingTrainingDetails(trnDtls,apprId,templateId,request);
		model.terminate();
		
		
		if(existing){ // IF EXISTING TRAINING DETAILS FOR A CALENDAR/TEMPLATE EXISTS
			trnDtls.setMode("update");
			/* 
			 * 
			 * CODE FOR GATHERING EXISTING DATA
			 * 
			 */
				
		}else{
			trnDtls.setMode("add");
			TrainingDetailsModel model1 = new TrainingDetailsModel();
			model1.initiate(context, session);
			model1.getNewTrainingDetails(trnDtls);
			model1.terminate();
			
		}
		return SUCCESS;
	}
	
	public String skip()throws Exception{
		
		request.setAttribute("tempCode",trnDtls.getTemplateCode());
		return "skip";
		
	}
	
	
	public String addQuestion()throws Exception{
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		
		if(trnDtls.getMode().equals("add")){
			//model.getNewTrainingDetails(trnDtls);
			model.getScreenDetails(trnDtls,request);	
		}if(trnDtls.getMode().equals("update")){
			//model.checkExistingTrainingDetails(trnDtls,trnDtls.getApprId(),trnDtls.getTemplateCode(),request);
			//model.
			model.getScreenDetails(trnDtls,request);			
		}
		
		model.addQuestion(trnDtls,request);
		model.terminate();
		trnDtls.setQuestion("");
		trnDtls.setQuestionId("");
		
		getNavigationPanel(15);
		
		return SUCCESS;
		
	}
	
	public String next()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		
		if(trnDtls.getMode().equals("add")){
			insert = model.save(trnDtls,request);//save training details
		}else{
			update = model.update(trnDtls,request);//save training details
		}
		
		if(insert){
			
			//addActionMessage("Record saved successfully.");
			addActionMessage(getMessage("save"));
			
		}if(update){
			
			//addActionMessage("Record updated successfully.");
			addActionMessage(getMessage("update"));
			
		}
		model.terminate();		
		getNavigationPanel(15);	
		request.setAttribute("tempCode",trnDtls.getTemplateCode());
		return input();
	}
	
	public String  saveAndNext()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		
		if(trnDtls.getMode().equals("add")){
			insert = model.save(trnDtls,request);//save training details
		}else{
			update = model.update(trnDtls,request);//save training details
		}
		
		if(insert){
			//addActionMessage("Record saved successfully.");
			//addActionMessage(getMessage("save"));
		}if(update){
			//addActionMessage("Record updated successfully.");
			//addActionMessage(getMessage("update"));
		}
		model.terminate();		
		getNavigationPanel(15);		
		request.setAttribute("tempCode",trnDtls.getTemplateCode());
		return "saveAndNext";
		
	}
	
	public String  saveAndPrevious()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		
		if(trnDtls.getMode().equals("add")){
			insert = model.save(trnDtls,request);//save training details
		}else{
			update = model.update(trnDtls,request);//save training details
		}
		
		if(insert){
			 //addActionMessage("Record saved successfully.");
			//addActionMessage(getMessage("save"));
		}if(update){
			//addActionMessage("Record updated successfully.");
			//addActionMessage(getMessage("update"));
		}
		model.terminate();		
		getNavigationPanel(15);		
		request.setAttribute("tempCode",trnDtls.getTemplateCode());
		return "saveAndPrevious";
		
	}
	
/*	
	public String updateNext()throws Exception{
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		model.update(trnDtls,request);
		model.terminate();
		
		
		
	}*/
	
	
	public String remove(){
		
		TrainingDetailsModel model = new TrainingDetailsModel();
		model.initiate(context, session);
		boolean result=false;
		
		if(trnDtls.getMode().equals("add")){
			
			model.getScreenDetails(trnDtls,request);
			result=model.remove(trnDtls,request);
			//model.getNewTrainingDetails(trnDtls);
			
		}if(trnDtls.getMode().equals("update")){
			
			model.getScreenDetails(trnDtls,request);
			result=model.remove(trnDtls,request);
			//model.checkExistingTrainingDetails(trnDtls,trnDtls.getApprId(),trnDtls.getTemplateCode(),request);
			
		}
	
		model.terminate();
		if(result)addActionMessage("Questions removed successfully.");
		
		
		getNavigationPanel(15);
		
		return SUCCESS;
		
	}
	
	public String f9quest()throws Exception{
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT ROWNUM,APPR_QUES_DESC , APPR_QUES_CODE FROM PAS_APPR_QUESTIONNAIRE"
					  +" INNER JOIN PAS_APPR_QUESTION_CATGORY ON(PAS_APPR_QUESTION_CATGORY.APPR_QUES_CATG_CODE=PAS_APPR_QUESTIONNAIRE.APPR_QUES_CATG_CODE)"
					  +" WHERE APPR_QUES_CATG_ISACTIVE='A' AND APPR_QUES_STATUS='A' AND APPR_QUES_TYPE='D'";
					  //+" ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("trng.sno"),getMessage("trng.quest")};
		
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "5","40" };
		
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "rownum","question","questionId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1,2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
	
}
