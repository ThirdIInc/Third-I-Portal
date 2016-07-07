package org.struts.action.PAS;
import org.paradyne.bean.PAS.AwardsRecognition;
import org.paradyne.model.PAS.AwardsRecognitionModel;

import org.struts.lib.ParaActionSupport;


public class AwardsRecognitionAction extends ParaActionSupport{
	
	AwardsRecognition awardRecog;
	

	public AwardsRecognition getAwardRecog() {
		return awardRecog;
	}

	public void setAwardRecog(AwardsRecognition awardRecog) {
		this.awardRecog = awardRecog;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return awardRecog;
	}
	
	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		awardRecog = new AwardsRecognition();
		awardRecog.setMenuCode(748);
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		String tempCode= (String)request.getAttribute("tempCode");
		awardRecog.setTemplateCode(tempCode);
		
		// TODO Auto-generated method stub
		getNavigationPanel(15);
		
		/*String apprId = "3";
		String templateId = "3";*/
		
		System.out.println("APPR ID -->"+awardRecog.getApprId());
		System.out.println("TMPL ID -->"+awardRecog.getTemplateCode());
		
		String apprId = awardRecog.getApprId();
		String templateId = awardRecog.getTemplateCode();
		
		
		
		awardRecog.setApprId(apprId);
		awardRecog.setTemplateCode(templateId);
		
		
		
		AwardsRecognitionModel model = new AwardsRecognitionModel();
		model.initiate(context, session);
		boolean existing = model.checkExistingAwardsDetails(awardRecog,apprId,templateId,request);
		model.terminate();
		
		
		if(existing){ // IF EXISTING TRAINING DETAILS FOR A CALENDAR/TEMPLATE EXISTS
			awardRecog.setMode("update");
		}else{
			awardRecog.setMode("add");
			AwardsRecognitionModel model1 = new AwardsRecognitionModel();
			model1.initiate(context, session);
			model1.getNewAwardsDetails(awardRecog);
			model1.terminate();
			
		}
		return SUCCESS;
	}
	
	
	public String skip()throws Exception{
		
		request.setAttribute("tempCode",awardRecog.getTemplateCode());
		return "skip";
		
	}
	
	
	public String next()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		AwardsRecognitionModel model = new AwardsRecognitionModel();
		model.initiate(context, session);
		
		if(awardRecog.getMode().equals("add")){
			insert = model.save(awardRecog,request);//save award details
		}else{
			update = model.update(awardRecog,request);//save award details
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
		request.setAttribute("tempCode",awardRecog.getTemplateCode());
		return input();
	}
	
	public String  saveAndNext()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		AwardsRecognitionModel model = new AwardsRecognitionModel();
		model.initiate(context, session);
		
		if(awardRecog.getMode().equals("add")){
			insert = model.save(awardRecog,request);//save award details
		}else{
			update = model.update(awardRecog,request);//save award details
		}
		
		if(insert){
			//addActionMessage("Record saved successfully.");
		}if(update){
			//addActionMessage("Record updated successfully.");
		}
		model.terminate();		
		getNavigationPanel(15);
		request.setAttribute("tempCode",awardRecog.getTemplateCode());
		return "saveAndNext";
		
	}
	
public String  saveAndPrevious()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		AwardsRecognitionModel model = new AwardsRecognitionModel();
		model.initiate(context, session);
		
		if(awardRecog.getMode().equals("add")){
			insert = model.save(awardRecog,request);//save award details
		}else{
			update = model.update(awardRecog,request);//save award details
		}
		
		if(insert){
			//addActionMessage("Record saved successfully.");
		}if(update){
			//addActionMessage("Record updated successfully.");
		}
		model.terminate();		
		getNavigationPanel(15);		
		request.setAttribute("tempCode",awardRecog.getTemplateCode());
		return "saveAndPrevious";
		
	}
	
	
}
