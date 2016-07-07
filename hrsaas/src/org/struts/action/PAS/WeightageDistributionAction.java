package org.struts.action.PAS;
import org.paradyne.bean.PAS.WeightageDistribution;
import org.paradyne.model.PAS.WeightageDistributionModel;

import org.struts.lib.ParaActionSupport;


public class WeightageDistributionAction extends ParaActionSupport{
	
	WeightageDistribution weighDistri;
	
	

	public WeightageDistribution getWeighDistri() {
		return weighDistri;
	}

	public void setWeighDistri(WeightageDistribution weighDistri) {
		this.weighDistri = weighDistri;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return weighDistri;
	}
	
	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		weighDistri = new WeightageDistribution();
		weighDistri.setMenuCode(748);
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		String tempCode= (String)request.getAttribute("tempCode");
		if(tempCode!=null && tempCode.equals("")){
			weighDistri.setTemplateCode(tempCode);
		}	
		
		
		// TODO Auto-generated method stub
		getNavigationPanel(12);
		
		/*String apprId = "3";
		String templateId = "3";*/
		
		System.out.println("APPR ID -->"+weighDistri.getApprId());
		System.out.println("TMPL ID -->"+weighDistri.getTemplateCode());
		
		String apprId = weighDistri.getApprId();
		String templateId = weighDistri.getTemplateCode();
		
		
		
		
		WeightageDistributionModel model = new WeightageDistributionModel();
		model.initiate(context, session);
		model.getConfiguredPhases(weighDistri,request);
		model.terminate();
		
		
		return SUCCESS;
	}
		
}
