/**
 * 
 */
package org.struts.action.settings;

import org.paradyne.bean.settings.SuggestionAppr;
import org.paradyne.model.settings.SuggestionApprModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ritac
 *
 */
public class SuggestionApprAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	SuggestionAppr suggAppr;
	SuggestionApprModel model=new SuggestionApprModel();
	public SuggestionAppr getSuggAppr() {
		return suggAppr;
	}

	public void setSuggAppr(SuggestionAppr suggAppr) {
		this.suggAppr = suggAppr;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		suggAppr=new SuggestionAppr();
		suggAppr.setMenuCode(400);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		
		showData();
		
	}
	
	public String showData()throws Exception{
		
		model.initiate(context,session);
		boolean res;
		res=model.setData(suggAppr,suggAppr.getSuggStatus());
		System.out.println("resresres=="+res);
		if(res==false)
		{
			addActionMessage("NO record");
		}
		model.terminate();
		return SUCCESS;
		
	}
	public String save()throws Exception{
		
		boolean result;
		Object[] suggCode=request.getParameterValues("suggestionCode");
		Object[] checkStatus=request.getParameterValues("checkStatus");
		
		
		model.initiate(context,session);
		result=model.saveData(suggAppr,suggCode,checkStatus);
		if(result){
			addActionMessage("Status has been Changed !");
		}else addActionError("Status not changed !");
		model.terminate();
		return SUCCESS;
		
	}
	public String ckeckdata(){
		model.initiate(context,session);
		suggAppr.getSuggStatus();
		model.terminate();
		return SUCCESS;
	}
	public String reset(){
		suggAppr.setCheckStatus("");
		suggAppr.setSrNo("");
		suggAppr.setStatus("");
		suggAppr.setSuggestionCode("");
		suggAppr.setSuggestionSub("");
		suggAppr.setSuggStatus("");
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return suggAppr;
	}

}
