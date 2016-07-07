package org.struts.action.PAS;
import org.paradyne.bean.PAS.ApprFormTrainingDtls;
import org.paradyne.model.PAS.ApprFormTrainingDtlsModel;
import org.struts.lib.ParaActionSupport;

public class ApprFormTrainingDtlsAction extends ParaActionSupport {

	ApprFormTrainingDtls  apprFormTrngDtls;
	public void prepare_local() throws Exception {
		System.out.println("prepare_local()......................");
		
		apprFormTrngDtls = new ApprFormTrainingDtls();
		apprFormTrngDtls.setMenuCode(759);
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormTrngDtls;
	}


	
	public ApprFormTrainingDtls getApprFormSection() {
		return apprFormTrngDtls;
	}

	public void setApprFormSection(ApprFormTrainingDtls apprFormTrngDtls) {
		this.apprFormTrngDtls = apprFormTrngDtls;
	}
	
	public String input(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		apprFormTrngDtls.setPhaseSequence("1");
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);		
		boolean result = model.checkIsSelf(apprFormTrngDtls);
		System.out.println("SELF>>>>>>"+result);
		
		System.out.println("hemant1");
		boolean result1 = model.checkSectionsEnabeled(apprFormTrngDtls.getApprId(),apprFormTrngDtls.getTemplateCode(),apprFormTrngDtls.getPhaseCode(),"T");
		System.out.println("hemant3"+result1);
		 
		
		
		if(apprFormTrngDtls.getPhaseForwardFlag().equals("true"))
		{
			if(!result1){
				getNavigationPanel(7);//PREVIOUS
			}else{
				getNavigationPanel(2);//NEXT-PREVIOUS
			}
		}
		else{
			
			if(!result1){//Other sections are disabled
				
				getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
				
			}else{//Other sections are available
				
				getNavigationPanel(3);//SAVE-SAVE and NEXT-SAVE and PREVIOUS
				
			}
			
		}
		
		
		
		
		if(result){ //SELF
			System.out.println("APPRAISEEEE>>>>");
			 result = model.checkTrainingApplicability(apprFormTrngDtls);
			
			if(result){//TRAINING APPLICABLE common for self/n rest
				
				result = model.checkTrainingVisibility(apprFormTrngDtls);
				
				if(result){//TRAINING IS VISIBLE
					
					model.checkExistingTrainingAttended(apprFormTrngDtls);
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
			}else{
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{ //OTHER PHASES
			System.out.println("APPRAISER>>>>");
		
				result = model.checkTrainingApplicability(apprFormTrngDtls);
				if(result){//TRAINING APPLICABLE common for self/n rest
					
					result = model.checkTrainingVisibility(apprFormTrngDtls);
					
					if(result){//TRAINING IS VISIBLE
						
						//String comments = model.checkTrainingComments(apprFormTrngDtls);
						model.setPreviousPhaseDetailsFlag(apprFormTrngDtls);
						model.checkExistingTrainingAttendedComments(apprFormTrngDtls);
						result = model.checkExistingTrainingDetails(apprFormTrngDtls);//RECOMMENDED
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormTrngDtls.setMode("update");
						}else{
							apprFormTrngDtls.setMode("add");
							model.getTrainingQuestions(apprFormTrngDtls);
						}
						//model.getEmployeeTrainingDetails(apprFormTrngDtls);
						
					}else{
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormTrngDtls.setRemoveTrngCode("");
		model.terminate();
		return SUCCESS;
	}
	
	
	public String next(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);

		if(apprFormTrngDtls.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		
		if(apprFormTrngDtls.getIsSelf().equals("true")){ //SELF
			
			boolean result = model.checkTrainingApplicability(apprFormTrngDtls);
			
			if(result){//TRAINING APPLICABLE common for self/n rest
				
				result = model.checkTrainingVisibility(apprFormTrngDtls);
				
				if(result){//TRAINING IS VISIBLE
					
					model.checkExistingTrainingAttended(apprFormTrngDtls);
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
			}else{
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{ //OTHER PHASES
		
				boolean result = model.checkTrainingApplicability(apprFormTrngDtls);
				if(result){//TRAINING APPLICABLE common for self/n rest
					
					result = model.checkTrainingVisibility(apprFormTrngDtls);
					
					if(result){//TRAINING IS VISIBLE
						
						//String comments = model.checkTrainingComments(apprFormTrngDtls);
						model.setPreviousPhaseDetailsFlag(apprFormTrngDtls);
						model.checkExistingTrainingAttendedComments(apprFormTrngDtls);
						result = model.checkExistingTrainingDetails(apprFormTrngDtls);
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormTrngDtls.setMode("update");
						}else{
							apprFormTrngDtls.setMode("add");
							model.getTrainingQuestions(apprFormTrngDtls);
						}
						model.getEmployeeTrainingDetails(apprFormTrngDtls);
						
					}else{
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormTrngDtls.setRemoveTrngCode("");
		model.terminate();
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	public String previous(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		
		if(apprFormTrngDtls.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		if(apprFormTrngDtls.getIsSelf().equals("true")){ //SELF
			
			boolean result = model.checkTrainingApplicability(apprFormTrngDtls);
			
			if(result){//TRAINING APPLICABLE common for self/n rest
				
				result = model.checkTrainingVisibility(apprFormTrngDtls);
				
				if(result){//TRAINING IS VISIBLE
					
					model.checkExistingTrainingAttended(apprFormTrngDtls);
				}else{
					if(model.getPrevious(apprFormTrngDtls))
						return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
					else
						return "previousToInstr";
				}
				
			}else{
				if(model.getPrevious(apprFormTrngDtls))
					return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
				else
					return "previousToInstr";			
			}
			
		}else{ //OTHER PHASES
		
				boolean result = model.checkTrainingApplicability(apprFormTrngDtls);
				if(result){//TRAINING APPLICABLE common for self/n rest
					
					result = model.checkTrainingVisibility(apprFormTrngDtls);
					
					if(result){//TRAINING IS VISIBLE
						
						//String comments = model.checkTrainingComments(apprFormTrngDtls);
						model.setPreviousPhaseDetailsFlag(apprFormTrngDtls);
						model.checkExistingTrainingAttendedComments(apprFormTrngDtls);
						result = model.checkExistingTrainingDetails(apprFormTrngDtls);
						
						
						if(result){//TRAINING DETAILS ALREADY EXISTS
							apprFormTrngDtls.setMode("update");
						}else{
							apprFormTrngDtls.setMode("add");
							model.getTrainingQuestions(apprFormTrngDtls);
						}
						model.getEmployeeTrainingDetails(apprFormTrngDtls);
						
					}else{
						if(model.getPrevious(apprFormTrngDtls))
							return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
						else
							return "previousToInstr";
					}
					
					
				}else{
					if(model.getPrevious(apprFormTrngDtls))
						return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
					else
						return "previousToInstr";
				}
				
		}
		apprFormTrngDtls.setRemoveTrngCode("");
		model.terminate();
		return SUCCESS;
	}
	
	
	
	
	
	public String addTrainingDetails()throws Exception{
		
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		model.addTrainingDetails(apprFormTrngDtls,request);
		//model.getScreenDetails(apprFormTrngDtls,request);
		
		System.out.println("hemant1");
		boolean result1 = model.checkSectionsEnabeled(apprFormTrngDtls.getApprId(),apprFormTrngDtls.getTemplateCode(),apprFormTrngDtls.getPhaseCode(),"T");
		System.out.println("hemant3"+result1);
		model.terminate();
		if(apprFormTrngDtls.getPhaseForwardFlag().equals("true"))
		{
			if(!result1){
				getNavigationPanel(7);//PREVIOUS
			}else{
				getNavigationPanel(2);//NEXT-PREVIOUS
			}
		}
		else{
			
			if(!result1){//Other sections are disabled
				
				getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
				
			}else{//Other sections are available
				
				getNavigationPanel(3);//SAVE-SAVE and NEXT-SAVE and PREVIOUS
				
			}
			
		}
		return SUCCESS;
	}
	
	public String removeTrainingDetails()throws Exception{
		
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		boolean result = model.checkReferencesExist(apprFormTrngDtls,request);
		if(result){
			//addActionMessage("Cannot delete the record as references exists.");
			addActionMessage(getMessage("del.error"));
			model.checkExistingTrainingAttended(apprFormTrngDtls);
		}else{
			model.removeTrainingDetails(apprFormTrngDtls,request);
		}

		System.out.println("hemant1");
		boolean result1 = model.checkSectionsEnabeled(apprFormTrngDtls.getApprId(),apprFormTrngDtls.getTemplateCode(),apprFormTrngDtls.getPhaseCode(),"T");
		System.out.println("hemant3"+result1);
		model.terminate();
		if(apprFormTrngDtls.getPhaseForwardFlag().equals("true"))
		{
			if(!result1){
				getNavigationPanel(7);//PREVIOUS
			}else{
				getNavigationPanel(2);//NEXT-PREVIOUS
			}
		}
		else{
			
			if(!result1){//Other sections are disabled
				
				getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
				
			}else{//Other sections are available
				
				getNavigationPanel(3);//SAVE-SAVE and NEXT-SAVE and PREVIOUS
				
			}
			
		}
		return SUCCESS;
		
	}
	
	public String save()throws Exception{
		
		 System.out.println("in acton "+apprFormTrngDtls.getIsSelf());
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		
		if(apprFormTrngDtls.getIsSelf().equals("true")){//SELF
			
			//if(apprFormTrngDtls.getMode().equals("add")){//INSERT
				result = model.saveTrainingAttendedDetails(apprFormTrngDtls,request);
			//}else{//UPDATE
				//result = model.updateTrainingAttendedDetails(apprFormTrngDtls,request);
			//}
			
			//result = model.updateTrainingAttendedDetails(apprFormTrngDtls,request);
				if(result.equals("insert")){
					addActionMessage(getMessage("save"));
				}else if(result.equals("update")){
					addActionMessage(getMessage("update"));
				}else{
					addActionMessage(getMessage("save.error"));
				}
		}else{//OTHER PHASES			
				 
				 result = model.saveTrainingAttendedComments(apprFormTrngDtls,request);
				 result = model.saveTrainingRecommendationsDetails(apprFormTrngDtls,request);
				
				 if(result.equals("insert")){
						addActionMessage(getMessage("save"));
					}else if(result.equals("update")){
						addActionMessage(getMessage("update"));
					}else{
						addActionMessage(getMessage("save.error"));
					}
		}
		apprFormTrngDtls.setRemoveTrngCode("");
		return input();
	}
	
	
	public String saveAndNext() throws Exception{
		
		boolean insert=false;
		boolean update=false;
		String result = "";
		
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		
		if(apprFormTrngDtls.getIsSelf().equals("true")){//SELF
			
				result = model.saveTrainingAttendedDetails(apprFormTrngDtls,request);
				
		}else{//OTHER PHASES
							
				result = model.saveTrainingRecommendationsDetails(apprFormTrngDtls,request);
				result = model.saveTrainingAttendedComments(apprFormTrngDtls,request);
						
		}
		apprFormTrngDtls.setRemoveTrngCode("");
		return "saveAndNext";
		
	}
	
	
public String saveAndPrevious() throws Exception{
		
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		
		if(apprFormTrngDtls.getIsSelf().equals("true")){//SELF
			
			result = model.saveTrainingAttendedDetails(apprFormTrngDtls,request);
			
		
	}else{//OTHER PHASES
						
			result = model.saveTrainingRecommendationsDetails(apprFormTrngDtls,request);
			result = model.saveTrainingAttendedComments(apprFormTrngDtls,request);
					
	}
		apprFormTrngDtls.setRemoveTrngCode("");
		boolean result1 = model.getPrevious(apprFormTrngDtls);
		
		if(result1)
			return "saveAndPrevious";
		else
			return "previousToInstr";
		
	}
	
	public String getPrevious(){
		
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		boolean result1 = model.getPrevious(apprFormTrngDtls);
		
		if(result1)
			return "previous";
		else
			return "previousToInstr";
		
	}
	
	public String previousPhaseTrainingAttendedDtls()throws Exception{
		apprFormTrngDtls.setMenuCode(759);
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		model.previousPhaseTrainingAttendedDtls(apprFormTrngDtls,request);
		model.terminate();
		request.setAttribute("FLAG","TRNGATT");
		return "previousPhaseDetails";
	}
	
	public String previousPhaseTrainingRecommDtls()throws Exception{
		
		ApprFormTrainingDtlsModel model = new ApprFormTrainingDtlsModel();
		model.initiate(context, session);
		model.previousPhaseTrainingRecommDtls(apprFormTrngDtls,request);
		model.terminate();
		request.setAttribute("FLAG","TRNGREC");
		return "previousPhaseDetails";
		
	}
	
}
