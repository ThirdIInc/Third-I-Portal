package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalInstruction;
import org.paradyne.lib.Utility;
import org.paradyne.model.PAS.AppraisalInstructionModel;
import org.struts.action.Loan.LoanApplicationAction;
import org.struts.lib.ParaActionSupport;

public class AppraisalInstructionAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraisalInstructionAction.class);
	
	AppraisalInstruction appraisalInstruction;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		appraisalInstruction = new AppraisalInstruction();
		appraisalInstruction.setMenuCode(748);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return appraisalInstruction;
	}

	public AppraisalInstruction getAppraisalInstruction() {
		return appraisalInstruction;
	}

	public void setAppraisalInstruction(AppraisalInstruction appraisalInstruction) {
		this.appraisalInstruction = appraisalInstruction;
	}
	
	public String input() throws Exception {
		getNavigationPanel(11);
		AppraisalInstructionModel model = new AppraisalInstructionModel();
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		model.initiate(context, session);
		logger.info("in appr instructions actionnnnnnnnnnnn");
		String tempCode= (String)request.getAttribute("tempCode");
		appraisalInstruction.setTemplateCode(tempCode);
		model.getRatingdetails(appraisalInstruction);
		model.getInstructions(appraisalInstruction);
		model.terminate();
		return INPUT;
	}
		
	public String save() {
		getNavigationPanel(11);
		AppraisalInstructionModel model = new AppraisalInstructionModel();
		model.initiate(context, session);
		try{
		Object [][] updateInstrObj = new Object[1][4];
		if(String.valueOf(appraisalInstruction.getInstrApplicable()).equals("true"))
			updateInstrObj [0] [0]= "Y";
		else
			updateInstrObj [0] [0]= "N";
		
			updateInstrObj [0] [1]= appraisalInstruction.getInstruction();
			updateInstrObj [0] [2]= appraisalInstruction.getApprId();
			updateInstrObj [0] [3]= appraisalInstruction.getTemplateCode();
		
			boolean result = model.saveInstructioons(updateInstrObj);
			
			if(result){
				addActionMessage(getMessage("update"));
			}else
				addActionMessage(getMessage("update.error"));
			
			request.setAttribute("tempCode",appraisalInstruction.getTemplateCode());
		
			input();
		}catch(Exception e){
			logger.error(e);
		}
			model.terminate();
		return INPUT;
	}

	public String saveAndNext(){
		getNavigationPanel(11);
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		AppraisalInstructionModel model = new AppraisalInstructionModel();
		model.initiate(context, session);
		try{
		Object [][] updateInstrObj = new Object[1][4];
		if(String.valueOf(appraisalInstruction.getInstrApplicable()).equals("true"))
			updateInstrObj [0] [0]= "Y";
		else
			updateInstrObj [0] [0]= "N";
		
			updateInstrObj [0] [1]= appraisalInstruction.getInstruction();
			updateInstrObj [0] [2]= appraisalInstruction.getApprId();
			updateInstrObj [0] [3]= appraisalInstruction.getTemplateCode();
		
			boolean result = model.saveInstructioons(updateInstrObj);
			
			request.setAttribute("tempCode",appraisalInstruction.getTemplateCode());
			input();
		}catch(Exception e){
			logger.error(e);
		}
			model.terminate();
		return "next";
	}
	public String saveAndPrevious(){
		getNavigationPanel(11);
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		AppraisalInstructionModel model = new AppraisalInstructionModel();
		model.initiate(context, session);
		try{
		Object [][] updateInstrObj = new Object[1][4];
		if(String.valueOf(appraisalInstruction.getInstrApplicable()).equals("true"))
			updateInstrObj [0] [0]= "Y";
		else
			updateInstrObj [0] [0]= "N";
		
			updateInstrObj [0] [1]= appraisalInstruction.getInstruction();
			updateInstrObj [0] [2]= appraisalInstruction.getApprId();
			updateInstrObj [0] [3]= appraisalInstruction.getTemplateCode();
		
			boolean result = model.saveInstructioons(updateInstrObj);
			
			request.setAttribute("tempCode",appraisalInstruction.getTemplateCode());
			input();
		}catch(Exception e){
			logger.error(e);
		}
		model.terminate();
		return "previous";
	}
	
	public String reset(){
		getNavigationPanel(11);
		
		appraisalInstruction.setInstrApplicable("false");
		appraisalInstruction.setInstruction("");
				
		return SUCCESS;
	}
	

}
