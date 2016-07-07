package org.struts.action.PAS;
import org.paradyne.bean.PAS.ApprFormAwardDtls;
import org.paradyne.bean.PAS.ApprFormTrainingDtls;
import org.paradyne.model.PAS.ApprFormAwardDtlsModel;
import org.paradyne.model.PAS.ApprFormTrainingDtlsModel;
import org.struts.lib.ParaActionSupport;

public class ApprFormAwardDtlsAction extends ParaActionSupport {
	
	ApprFormAwardDtls  apprFormAwdDtls;
	
	public void prepare_local() throws Exception {
		System.out.println("prepare_local()......................");
		
		apprFormAwdDtls = new ApprFormAwardDtls();
		apprFormAwdDtls.setMenuCode(759);
		/*
		apprFormAwdDtls.setApprId("5");
		apprFormAwdDtls.setTemplateCode("3");
		apprFormAwdDtls.setPhaseCode("13");
	
		apprFormAwdDtls.setEmpId("1"); 
		
		apprFormAwdDtls.setApprCode("GTL-DEC_2009");
		apprFormAwdDtls.setApprStartDate("");
		apprFormAwdDtls.setApprEndDate("");
		
		apprFormAwdDtls.setPhaseName("Self");
		apprFormAwdDtls.setPhaseStartDate("");
		apprFormAwdDtls.setPhaseEndDate("");*/
		
		apprFormAwdDtls.setPhaseSequence("1");//1-Self
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormAwdDtls;
	}
	
	public ApprFormAwardDtls getApprFormAwdDtls() {
		return apprFormAwdDtls;
	}

	public void setApprFormAwdDtls(ApprFormAwardDtls apprFormAwdDtls) {
		this.apprFormAwdDtls = apprFormAwdDtls;
	}
    
	public void reset(){
		
		apprFormAwdDtls.setAward("");
		apprFormAwdDtls.setReason("");
		
	}
	
	public String input(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		
		////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormAwdDtls.getApprId(),apprFormAwdDtls.getTemplateCode(),apprFormAwdDtls.getPhaseCode(),"A");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			{
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}
				else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
		
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		boolean result = model.checkIsSelf(apprFormAwdDtls);
		
		if(result){//SELF
			System.out.println("APPRAISEE SECTION*****");

			result = model.checkAwardApplicability(apprFormAwdDtls);
			if(result){//AWARD APPLICABLE common for self/n rest
			
				result = model.checkAwardVisibility(apprFormAwdDtls);
				
				if(result){//AWARD IS VISIBLE
				
					model.checkExistingAwardDetails(apprFormAwdDtls);
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			}else{
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{
				System.out.println("APPRAISER SECTION*****");
				result = model.checkAwardApplicability(apprFormAwdDtls);
				if(result){//AWARD APPLICABLE common for self/n rest
					
					result = model.checkAwardVisibility(apprFormAwdDtls);
					
					if(result){//AWARD IS VISIBLE
						
						model.setPreviousPhaseDetailsFlag(apprFormAwdDtls);
						model.checkExistingAwardComments(apprFormAwdDtls);
						//result = model.checkExistingTrainingDetails(apprFormAwdDtls);
						
						//Check whether Appraiser can nominate for an award
						result = model.checkAwardNomApplicability(apprFormAwdDtls);
						
						if(result){//NOMINATION IS APPLICABLE
							
							model.checkExistingAwardNomination(apprFormAwdDtls);
							
						}
						
						
						/*
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormAwdDtls.setMode("update");
						}else{
							apprFormAwdDtls.setMode("add");
							model.getTrainingQuestions(apprFormAwdDtls);
						}
						model.getEmployeeTrainingDetails(apprFormAwdDtls);*/
						
					}else{
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		apprFormAwdDtls.setRemoveNomCode("");
		model.terminate();
		return SUCCESS;
	}
	
	public String next(){
		System.out.println("input()......................");
		getNavigationPanel(3);
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		
		if(apprFormAwdDtls.getIsSelf().equals("true")){//SELF
			System.out.println("APPRAISEE SECTION*****");

			boolean result = model.checkAwardApplicability(apprFormAwdDtls);
			if(result){//AWARD APPLICABLE common for self/n rest
			
				result = model.checkAwardVisibility(apprFormAwdDtls);
				
				if(result){//AWARD IS VISIBLE
				
					model.checkExistingAwardDetails(apprFormAwdDtls);
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			}else{
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{
				System.out.println("APPRAISER SECTION*****");
				boolean result = model.checkAwardApplicability(apprFormAwdDtls);
				if(result){//AWARD APPLICABLE common for self/n rest
					
					result = model.checkAwardVisibility(apprFormAwdDtls);
					
					if(result){//AWARD IS VISIBLE
						
						model.setPreviousPhaseDetailsFlag(apprFormAwdDtls);
						model.checkExistingAwardComments(apprFormAwdDtls);
						//result = model.checkExistingTrainingDetails(apprFormAwdDtls);
						
						//Check whether Appraiser can nominate for an award
						result = model.checkAwardNomApplicability(apprFormAwdDtls);
						
						if(result){//NOMINATION IS APPLICABLE
							
							model.checkExistingAwardNomination(apprFormAwdDtls);
							
						}
						
						
						/*
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormAwdDtls.setMode("update");
						}else{
							apprFormAwdDtls.setMode("add");
							model.getTrainingQuestions(apprFormAwdDtls);
						}
						model.getEmployeeTrainingDetails(apprFormAwdDtls);*/
						
					}else{
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		apprFormAwdDtls.setRemoveNomCode("");
		model.terminate();
		return SUCCESS;
	}
	
	
	
	public String previous(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		
		if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		
		if(apprFormAwdDtls.getIsSelf().equals("true")){//SELF
			System.out.println("APPRAISEE SECTION*****");

			boolean result = model.checkAwardApplicability(apprFormAwdDtls);
			if(result){//AWARD APPLICABLE common for self/n rest
			
				result = model.checkAwardVisibility(apprFormAwdDtls);
				
				if(result){//AWARD IS VISIBLE
				
					model.checkExistingAwardDetails(apprFormAwdDtls);
					
				}else{
					return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			}else{
				return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{
				System.out.println("APPRAISER SECTION*****");
				boolean result = model.checkAwardApplicability(apprFormAwdDtls);
				if(result){//AWARD APPLICABLE common for self/n rest
					
					result = model.checkAwardVisibility(apprFormAwdDtls);
					
					if(result){//AWARD IS VISIBLE
						
						model.setPreviousPhaseDetailsFlag(apprFormAwdDtls);
						model.checkExistingAwardComments(apprFormAwdDtls);
						//result = model.checkExistingTrainingDetails(apprFormAwdDtls);
						
						//Check whether Appraiser can nominate for an award
						result = model.checkAwardNomApplicability(apprFormAwdDtls);
						
						if(result){//NOMINATION IS APPLICABLE
							
							model.checkExistingAwardNomination(apprFormAwdDtls);
							
						}
						
						
						/*
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormAwdDtls.setMode("update");
						}else{
							apprFormAwdDtls.setMode("add");
							model.getTrainingQuestions(apprFormAwdDtls);
						}
						model.getEmployeeTrainingDetails(apprFormAwdDtls);*/
						
					}else{
						return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		apprFormAwdDtls.setRemoveNomCode("");
		model.terminate();
		return SUCCESS;
	}
	
	
	public String previousPhaseAwardDtls()throws Exception{
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		model.previousPhaseAwardDtls(apprFormAwdDtls,request);
		model.terminate();
		
		return "previousPhaseDetails";
		
	}
	
	public String addAwardDetails()throws Exception{
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		model.addAwardDetails(apprFormAwdDtls,request);
		model.getNomineeList(apprFormAwdDtls, request);
		//model.getScreenDetails(apprFormAwdDtls,request);
		model.terminate();
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormAwdDtls.getApprId(),apprFormAwdDtls.getTemplateCode(),apprFormAwdDtls.getPhaseCode(),"A");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			{
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}
				else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
		return SUCCESS;
	}
	
	public String removeAwardDetails()throws Exception{
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		boolean result = model.checkReferencesExist(apprFormAwdDtls,request);
		if(result){
			//addActionMessage("Cannot delete the record as references exists.");
			addActionMessage(getMessage("del.error"));
			model.getScreenDetails(apprFormAwdDtls,request);
		}else{
			model.removeAwardDetails(apprFormAwdDtls,request);
			
		}
		model.getNomineeList(apprFormAwdDtls, request);
		model.terminate();
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormAwdDtls.getApprId(),apprFormAwdDtls.getTemplateCode(),apprFormAwdDtls.getPhaseCode(),"A");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			{
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}
				else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
		return SUCCESS;
		
	}
	
	public String addNomination()throws Exception{
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.getScreenDetails(apprFormAwdDtls,request);
		model.addNomination(apprFormAwdDtls,request);
		model.terminate();
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormAwdDtls.getApprId(),apprFormAwdDtls.getTemplateCode(),apprFormAwdDtls.getPhaseCode(),"A");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			{
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}
				else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
		reset();
		return SUCCESS;
	}
	
	public String removeNomination()throws Exception{
		
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.getScreenDetails(apprFormAwdDtls,request);
		model.removeNomination(apprFormAwdDtls,request);
		model.terminate();
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormAwdDtls.getApprId(),apprFormAwdDtls.getTemplateCode(),apprFormAwdDtls.getPhaseCode(),"A");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormAwdDtls.getPhaseForwardFlag().equals("true"))
			{
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}
				else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
		return SUCCESS;
	}
	
	public String save()throws Exception{
		
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		
		if(apprFormAwdDtls.getIsSelf().equals("true")){//SELF
				
				result = model.saveAwardAchievedDetails(apprFormAwdDtls,request);
				
				if(result.equals("insert")){
					addActionMessage(getMessage("save"));
					
				}else if(result.equals("update")){
					addActionMessage(getMessage("update"));
					
				}else{
					addActionMessage(getMessage("save.error"));
				}
		}else{//OTHER PHASES
			
			
			result = model.saveAwardAchievedComments(apprFormAwdDtls,request);
			//result = 
				model.saveAwardNominationComments(apprFormAwdDtls,request);
			
				if(result.equals("insert")){
					addActionMessage(getMessage("save"));
					
				}else if(result.equals("update")){
					addActionMessage(getMessage("update"));
					
				}else{
					addActionMessage(getMessage("save.error"));
				}
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		return input();
	}
	
	
	public String saveNext()throws Exception{
		
		 
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		
		if(apprFormAwdDtls.getIsSelf().equals("true")){//SELF
			
				result = model.saveAwardAchievedDetails(apprFormAwdDtls,request);
				
		}else{ //OTHER PHASES
			
			
			result = model.saveAwardAchievedComments(apprFormAwdDtls,request);
			result = model.saveAwardNominationComments(apprFormAwdDtls,request);
			
			
			
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		apprFormAwdDtls.setRemoveNomCode("");
		return "saveAndNext";
	}
	
	public String savePrevious()throws Exception{
		
		 
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormAwardDtlsModel model = new ApprFormAwardDtlsModel();
		model.initiate(context, session);
		
		if(apprFormAwdDtls.getIsSelf().equals("true")){//SELF
			
				result = model.saveAwardAchievedDetails(apprFormAwdDtls,request);
			
		}else{ //OTHER PHASES
			
			
			result = model.saveAwardAchievedComments(apprFormAwdDtls,request);
			result = model.saveAwardNominationComments(apprFormAwdDtls,request);
						
		}
		apprFormAwdDtls.setRemoveAwdCode("");
		apprFormAwdDtls.setRemoveNomCode("");
		return "saveAndPrevious";
	}
	
	
}
