package org.struts.action.PAS;
import org.paradyne.bean.PAS.FormFinalization;
import org.paradyne.model.PAS.FormFinalizationModel;

import org.struts.lib.ParaActionSupport;


public class FormFinalizationAction extends ParaActionSupport{
	
	FormFinalization frmFinal;
	
	
	

	public FormFinalization getFrmFinal() {
		return frmFinal;
	}

	public void setFrmFinal(FormFinalization frmFinal) {
		this.frmFinal = frmFinal;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return frmFinal;
	}
	
	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		frmFinal = new FormFinalization();
		frmFinal.setMenuCode(748);
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		// TODO Auto-generated method stub
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		String tempCode= (String)request.getAttribute("tempCode");

		if(tempCode!=null && tempCode.equals("")){
			frmFinal.setTemplateCode(tempCode);
		}		
		
		getNavigationPanel(13);
		
		/*String apprId = "3";
		String templateId = "3";*/
		
		System.out.println("APPR ID -->"+frmFinal.getApprId());
		System.out.println("TMPL ID -->"+frmFinal.getTemplateCode());
		
		String apprId = frmFinal.getApprId();
		String templateId = frmFinal.getTemplateCode();
		
		
		
		
		FormFinalizationModel model = new FormFinalizationModel();
		model.initiate(context, session);
		model.getConfiguredPhases(frmFinal,request);
		model.terminate();
		request.setAttribute("tempCode",frmFinal.getTemplateCode());
		
		return SUCCESS;
	}
		
}
