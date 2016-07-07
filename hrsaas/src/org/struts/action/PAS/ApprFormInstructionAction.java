package org.struts.action.PAS;

import org.paradyne.bean.PAS.ApprFormInstuction;
import org.paradyne.model.PAS.ApprFormInstructionModel;
import org.struts.lib.ParaActionSupport;

public class ApprFormInstructionAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormInstructionAction.class);
	
	
	ApprFormInstuction  apprFormInstruction;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		apprFormInstruction = new ApprFormInstuction();
		apprFormInstruction.setMenuCode(759);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormInstruction;
	}

	public ApprFormInstuction getApprFormInstruction() {
		return apprFormInstruction;
	}

	public void setApprFormInstruction(ApprFormInstuction apprFormInstruction) {
		this.apprFormInstruction = apprFormInstruction;
	}
	
	public String input(){
		
		getNavigationPanel(2);
		ApprFormInstructionModel model = new ApprFormInstructionModel();
		model.initiate(context, session);
		model.getInstructions(apprFormInstruction);
		model.terminate();
		return SUCCESS;
		
	}
	public String getInstructions(){
		getNavigationPanel(2);
		
		ApprFormInstructionModel model = new ApprFormInstructionModel();
		model.initiate(context, session);
		model.getInstructions(apprFormInstruction);
		model.terminate();
		return SUCCESS;
	}
	
	public String previousFirst(){
		
		getNavigationPanel(2);
		ApprFormInstructionModel model = new ApprFormInstructionModel();
		model.initiate(context, session);
		boolean result = model.getInstrApplicable(apprFormInstruction);
		model.terminate();
		logger.info("appraisal form instruction-----input---------"+result);
		if(result){
			getInstructions();
			return SUCCESS;
		}
		else
			return "previous";
	}

}
