package org.struts.action.PAS;
import org.paradyne.bean.PAS.DisciplinaryMeasures;
import org.paradyne.model.PAS.AwardsRecognitionModel;
import org.paradyne.model.PAS.DisciplinaryMeasuresModel;

import org.struts.lib.ParaActionSupport;


public class DisciplinaryMeasuresAction extends ParaActionSupport{
	
	DisciplinaryMeasures discpMeasures;
	

	public DisciplinaryMeasures getAwardRecog() {
		return discpMeasures;
	}

	public void setAwardRecog(DisciplinaryMeasures discpMeasures) {
		this.discpMeasures = discpMeasures;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return discpMeasures;
	}
	
	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		discpMeasures = new DisciplinaryMeasures();
		discpMeasures.setMenuCode(748);
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		String tempCode= (String)request.getAttribute("tempCode");
		discpMeasures.setTemplateCode(tempCode);
		// TODO Auto-generated method stub
		getNavigationPanel(15);
		
		/*String apprId = "3";
		String templateId = "3";*/
		
		System.out.println("APPR ID -->"+discpMeasures.getApprId());
		System.out.println("TMPL ID -->"+discpMeasures.getTemplateCode());
		
		String apprId = discpMeasures.getApprId();
		String templateId = discpMeasures.getTemplateCode();
		
		
		
		discpMeasures.setApprId(apprId);
		discpMeasures.setTemplateCode(templateId);
		
		
		
		DisciplinaryMeasuresModel model = new DisciplinaryMeasuresModel();
		model.initiate(context, session);
		boolean existing = model.checkExistingAwardsDetails(discpMeasures,apprId,templateId,request);
		model.terminate();
		
		
		if(existing){ // IF EXISTING TRAINING DETAILS FOR A CALENDAR/TEMPLATE EXISTS
			discpMeasures.setMode("update");
		}else{
			discpMeasures.setMode("add");
			DisciplinaryMeasuresModel model1 = new DisciplinaryMeasuresModel();
			model1.initiate(context, session);
			model1.getNewAwardsDetails(discpMeasures);
			model1.terminate();
			
		}
		return SUCCESS;
	}
	
	
	public String skip()throws Exception{
		
		request.setAttribute("tempCode",discpMeasures.getTemplateCode());
		return "skip";
		
	}
	
	
	public String next()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		DisciplinaryMeasuresModel model = new DisciplinaryMeasuresModel();
		model.initiate(context, session);
		
		if(discpMeasures.getMode().equals("add")){
			insert = model.save(discpMeasures,request);//save award details
		}else{
			update = model.update(discpMeasures,request);//save award details
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
		request.setAttribute("tempCode",discpMeasures.getTemplateCode());
		return input();
	}
	
	public String saveAndNext()throws Exception{
			
			System.out.println("iNSIDE SAVE()..........");
			boolean insert=false;
			boolean update=false;
			
			DisciplinaryMeasuresModel model = new DisciplinaryMeasuresModel();
			model.initiate(context, session);
			
			if(discpMeasures.getMode().equals("add")){
				insert = model.save(discpMeasures,request);//save award details
			}else{
				update = model.update(discpMeasures,request);//save award details
			}
			
			if(insert){
				//addActionMessage("Record saved successfully.");
			}if(update){
				//addActionMessage("Record updated successfully.");
			}
			model.terminate();		
			getNavigationPanel(15);	
			request.setAttribute("tempCode",discpMeasures.getTemplateCode());
			request.setAttribute("cols", "0");
			return "saveAndNext";
		}
	
	public String saveAndPrevious()throws Exception{
		
		System.out.println("iNSIDE SAVE()..........");
		boolean insert=false;
		boolean update=false;
		
		DisciplinaryMeasuresModel model = new DisciplinaryMeasuresModel();
		model.initiate(context, session);
		
		if(discpMeasures.getMode().equals("add")){
			insert = model.save(discpMeasures,request);//save award details
		}else{
			update = model.update(discpMeasures,request);//save award details
		}
		
		if(insert){
			//addActionMessage("Record saved successfully.");
		}if(update){
			//addActionMessage("Record updated successfully.");
		}
		model.terminate();		
		getNavigationPanel(15);		
		request.setAttribute("tempCode",discpMeasures.getTemplateCode());
		return "saveAndPrevious";
	}

}
