package org.struts.action.PAS;
import org.paradyne.bean.PAS.ApprFormDiscpMeasures;
import org.paradyne.model.PAS.ApprFormDiscpMeasuresModel;
import org.paradyne.model.PAS.ApprFormTrainingDtlsModel;
import org.struts.lib.ParaActionSupport;

public class ApprFormDiscpMeasuresAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormDiscpMeasuresAction.class);
	
	ApprFormDiscpMeasures apprFormDiscpMea;
	
	public void prepare_local() throws Exception {
		System.out.println("prepare_local()......................");
		
		apprFormDiscpMea = new ApprFormDiscpMeasures();
		apprFormDiscpMea.setMenuCode(759);
		
		apprFormDiscpMea.setPhaseSequence("1");//1-Self
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormDiscpMea;
	}
	
    
	public ApprFormDiscpMeasures getApprFormDiscpMea() {
		return apprFormDiscpMea;
	}
	
	public void setApprFormDiscpMea(ApprFormDiscpMeasures apprFormDiscpMea) {
		this.apprFormDiscpMea = apprFormDiscpMea;
	}
	
	public void reset(){
		
	}
	
	public String input(){
		System.out.println("input()......................"+apprFormDiscpMea.getPhaseSequence());
		//getNavigationPanel(3);
		
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormDiscpMea.getApprId(),apprFormDiscpMea.getTemplateCode(),apprFormDiscpMea.getPhaseCode(),"D");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormDiscpMea.getPhaseForwardFlag().equals("true")){
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
				
				
			}else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////		
	
		
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		boolean result = model.checkIsSelf(apprFormDiscpMea);
		
		if(result){ //SELF
			System.out.println("APPRAISEE SECTION*****");
			 
			 result = model.checkDiscpApplicability(apprFormDiscpMea);
			if(result){//DISCP APPLICABLE common for self/n rest
				
				result = model.checkDiscpVisibility(apprFormDiscpMea);
				
				if(result){//DISCP IS VISIBLE
				
					model.checkExistingDiscpMeasures(apprFormDiscpMea);
				
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			}else{
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{
				System.out.println("APPRAISER SECTION*****");
				 result = model.checkDiscpApplicability(apprFormDiscpMea);
				if(result){//DISCP APPLICABLE common for self/n rest
					
					result = model.checkDiscpVisibility(apprFormDiscpMea);
					
					if(result){//DISCP IS VISIBLE
						
						model.setPreviousPhaseDetailsFlag(apprFormDiscpMea);
						model.checkExistingDiscpComments(apprFormDiscpMea);
						//result = model.checkExistingTrainingDetails(apprFormDiscpMea);
						
					}else{
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormDiscpMea.setRemoveDiscpCode("");
		model.terminate();
		return SUCCESS;
	}
	
	public String previous(){
		System.out.println("input()......................"+apprFormDiscpMea.getPhaseSequence());
		if(apprFormDiscpMea.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		//getNavigationPanel(3);
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		
		if(apprFormDiscpMea.getIsSelf().equals("true")){ //SELF
			System.out.println("APPRAISEE SECTION*****");
			
			boolean result = model.checkDiscpApplicability(apprFormDiscpMea);
			if(result){//DISCP APPLICABLE common for self/n rest
				
				result = model.checkDiscpVisibility(apprFormDiscpMea);
				
				if(result){//DISCP IS VISIBLE
				
					model.checkExistingDiscpMeasures(apprFormDiscpMea);
				
				}else{
					return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			}else{
				return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
			}
			
		}else{
				System.out.println("APPRAISER SECTION*****");
				boolean result = model.checkDiscpApplicability(apprFormDiscpMea);
				if(result){//DISCP APPLICABLE common for self/n rest
					
					result = model.checkDiscpVisibility(apprFormDiscpMea);
					
					if(result){//DISCP IS VISIBLE
						
						model.setPreviousPhaseDetailsFlag(apprFormDiscpMea);
						model.checkExistingDiscpComments(apprFormDiscpMea);
						//result = model.checkExistingTrainingDetails(apprFormDiscpMea);
						
					}else{
						return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
					
				}else{
					return "previous";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
				
		}
		apprFormDiscpMea.setRemoveDiscpCode("");
		model.terminate();
		return SUCCESS;
	}
	
	public String addDiscpMeasures()throws Exception{
			ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
			model.initiate(context, session);
			model.addDiscpMeasures(apprFormDiscpMea,request);
			//model.getScreenDetails(apprFormDiscpMea,request);
			model.terminate();
			
////////////////////////////////
			ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
			model1.initiate(context, session);		
			boolean result1 = model1.checkSectionsEnabeled(apprFormDiscpMea.getApprId(),apprFormDiscpMea.getTemplateCode(),apprFormDiscpMea.getPhaseCode(),"D");
			model1.terminate();
			//System.out.println("FINISH BUTTON>>"+result1);
				if(apprFormDiscpMea.getPhaseForwardFlag().equals("true")){
					if(!result1){
						getNavigationPanel(7);
					}else{
						getNavigationPanel(2);
					}
					
					
				}else{
						
						if(!result1){//Other sections are disabled
							getNavigationPanel(4);
						}else{//Other sections are available
							getNavigationPanel(3);
						}
						
					}
			//////////////////////////////	
			return SUCCESS;
	}
	
	public String addDiscpMeansuresComments()throws Exception{
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		model.addDiscpMeansuresComments(apprFormDiscpMea,request);
		//model.getScreenDetails(apprFormDiscpMea,request);
		model.terminate();
		
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormDiscpMea.getApprId(),apprFormDiscpMea.getTemplateCode(),apprFormDiscpMea.getPhaseCode(),"D");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormDiscpMea.getPhaseForwardFlag().equals("true")){
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
				
				
			}else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////	
		return SUCCESS;
}
	
	public String removeDiscpMeasures()throws Exception{
		
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		boolean result = model.checkReferencesExist(apprFormDiscpMea,request);
		logger.info("removeDiscpMeasuresComments----------------------->>>>>>>>>>>"+result);
		if(result){
			addActionMessage(getMessage("del.error"));
			model.checkExistingDiscpMeasures(apprFormDiscpMea);
		
		}else{
			model.removeDiscpMeasures(apprFormDiscpMea,request);
		}
		//model.getScreenDetails(apprFormDiscpMea,request);
		model.terminate();
		
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormDiscpMea.getApprId(),apprFormDiscpMea.getTemplateCode(),apprFormDiscpMea.getPhaseCode(),"D");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormDiscpMea.getPhaseForwardFlag().equals("true")){
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
				
				
			}else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		return SUCCESS;
	}
	
	public String removeDiscpMeasuresComments()throws Exception{
			
			ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
			model.initiate(context, session);
			boolean result = model.checkReferencesExist(apprFormDiscpMea,request);
			logger.info("removeDiscpMeasuresComments----------------------->>>>>>>>>>>"+result);
			if(!result){
				model.removeDiscpMeasuresComments(apprFormDiscpMea,request);
			}else{
				addActionMessage(getMessage("del.error"));	
				model.checkExistingDiscpComments(apprFormDiscpMea);
				
			}
			
			//model.getScreenDetails(apprFormDiscpMea,request);
			model.terminate();
			
			ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
			model1.initiate(context, session);		
			boolean result1 = model1.checkSectionsEnabeled(apprFormDiscpMea.getApprId(),apprFormDiscpMea.getTemplateCode(),apprFormDiscpMea.getPhaseCode(),"D");
			model1.terminate();
			//System.out.println("FINISH BUTTON>>"+result1);
				if(apprFormDiscpMea.getPhaseForwardFlag().equals("true")){
					if(!result1){
						getNavigationPanel(7);
					}else{
						getNavigationPanel(2);
					}
					
					
				}else{
						
						if(!result1){//Other sections are disabled
							getNavigationPanel(4);
						}else{//Other sections are available
							getNavigationPanel(3);
						}
						
					}
			return SUCCESS;
		}
	
	public String save()throws Exception{
		
		 
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		
		if(apprFormDiscpMea.getIsSelf().equals("true")){//SELF
			
				result = model.saveDiscpMeasures(apprFormDiscpMea,request);
			
				if(result.equals("insert")){
					addActionMessage(getMessage("save"));
					
				}else if(result.equals("update")){
					addActionMessage(getMessage("update"));
					
				}else{
					addActionMessage(getMessage("save.error"));
				}
				
		}else{//OTHER PHASES
				
				/////DO IT ON SUNDAY
			     result = model.saveDiscpComments(apprFormDiscpMea,request);
				
			     if(result.equals("insert")){
						addActionMessage(getMessage("save"));
						
					}else if(result.equals("update")){
						addActionMessage(getMessage("update"));
						
					}else{
						addActionMessage(getMessage("save.error"));
					}
				
		}
		apprFormDiscpMea.setRemoveDiscpCode("");
		return input();
	}
	
	public String saveAndNext()throws Exception{
		
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		
		if(apprFormDiscpMea.getIsSelf().equals("true")){//SELF
			
				result = model.saveDiscpMeasures(apprFormDiscpMea,request);
			
		}else{//OTHER PHASES
				
				/////DO IT ON SUNDAY
			     result = model.saveDiscpComments(apprFormDiscpMea,request);
				 				
		}
		apprFormDiscpMea.setRemoveDiscpCode("");
		return "saveAndNext";
		
	}
	public String saveAndPrevious() throws Exception{
		
		 
		boolean insert=false;
		boolean update=false;
		String result = "";
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		
		if(apprFormDiscpMea.getIsSelf().equals("true")){//SELF
			
				result = model.saveDiscpMeasures(apprFormDiscpMea,request);
			
		}else{//OTHER PHASES
				
				/////DO IT ON SUNDAY
			     result = model.saveDiscpComments(apprFormDiscpMea,request);
				
				
		}
		apprFormDiscpMea.setRemoveDiscpCode("");
		return "saveAndPrevious";
		
	}

	public String previousPhaseDiscpMeasures()throws Exception{
		
		ApprFormDiscpMeasuresModel model = new ApprFormDiscpMeasuresModel();
		model.initiate(context, session);
		model.previousPhaseDiscpMeasures(apprFormDiscpMea,request);
		model.terminate();
		return "previousPhaseDetails";
	}
		
}
