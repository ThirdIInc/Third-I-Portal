package org.struts.action.D1;

import org.paradyne.bean.D1.ShortTermDisabilityBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ShortTermDisabilityModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

public class ShortTermDisabilityApprAction extends ParaActionSupport {

	ShortTermDisabilityBean bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
			bean=new ShortTermDisabilityBean();
			bean.setMenuCode(2010);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ShortTermDisabilityBean getBean() {
		return bean;
	}

	public void setBean(ShortTermDisabilityBean bean) {
		this.bean = bean;
	}
	/**
	 * INPUT METHOD
	 */
	public String input(){
		bean.setFlag("SS");
		bean.setApptype("appr");
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		model.input(bean,request,"A");
		getNavigationPanel(1);
		model.terminate();
		
		return INPUT;
	}
	public String getData(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		model.input(bean,request,"A");
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**
	 * INPUT METHOD
	 */
	public String back(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		model.input(bean,request,"A");
		model.terminate();
		return INPUT;
	}
	
	
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String editApplication(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		boolean result=model.editApplication(bean);
		String status=request.getParameter("status");
		bean.setEnableAll("N");
		if(bean.getFlag().equals("SS")){
			getNavigationPanel(2);
		}
		else if(bean.getFlag().equals("RS")){
			getNavigationPanel(2);
			
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
}
