/**
 * 
 */
package org.struts.action.settings;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.paradyne.bean.settings.Services;
import org.paradyne.model.settings.ServicesModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author prakashs
 *
 */
public class ServicesAction extends ParaActionSupport {
		Services service;
	
		ServicesModel model=new ServicesModel();
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
			service=new Services();
			service.setMenuCode(385);
			System.out.println("services coede=="+service.getServiceCode());
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return service;
	}
	public String save() throws Exception {
		System.out.println("inside add link");
		boolean result=false;
		model.initiate(context,session);
		if(service.getChkfield().equals(""))
				{
					if(service.getServiceCode().equals("")){
							result=model.save(service);
							service.setChkfield("saved");
					}else
		{	
			result=model.mod(service);
		
		}	
		
		addActionMessage("Record Saved Successfully !");
		}else{
			result=model.mod(service);
			if(service.getChkfield().equals("saved")){
				addActionMessage("Record Modified Successfully !");
			}
		}
		model.terminate();
		return showDetails();
	}
	public String addLink(){
		boolean result=false;
		model.initiate(context,session);
		if(service.getServiceCode().equals("")){
			result=model.addList(service);
			
		}
		else {
			result=model.modList(service);
			
		}
		if(result){
			service.setServiceName("");
			service.setServiceLink("");
			service.setServiceLinkCode("");
		}
		model.terminate();
		return showDetails();
	}
	public String edit(){
		
		System.out.println("inside edit===");
		model.initiate(context,session);
		model.edit(service);
		model.terminate();
		return showDetails();
	}
	public String deleteDtl(){
		System.out.println("inside deleteDtl");
		boolean result=false;
		model.initiate(context,session);
		result=model.deleteDtl(service);
		if(result){
			addActionMessage("Link Deleted Successfully !");
		
		}
		else addActionMessage("Link can't be Deleted !");
		service.setServiceLinkCode("");
		model.terminate();
		return showDetails();
	}
	public String delete(){
		model.initiate(context,session);
		if(model.delete(service)){
			addActionMessage("Record Deleted Successfully !");
			reset();
		}else addActionMessage("Record Can't be Deleted !");
		model.terminate();
		return SUCCESS;
	}
public String f9action() throws Exception {
		
		String query = " SELECT SERVICES_HDR_CODE ,SERVICES_HDR_SUBHEADER FROM HRMS_SERVICES_HDR ORDER BY SERVICES_HDR_CODE";
  
		
		
		String[] headers={"Code","Sub Header"};
		
		String[] headerWidth={"20","80"};
		
		String[] fieldNames={"serviceCode","subheadName"};
		
		
		int[] columnIndex={0,1};
		
		
		String submitFlag = "true";
		
		
		String submitToMethod="Services_showDetails.action";
		
	
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
public String showDetails(){
	System.out.println("inside show details");
	model.initiate(context,session);
	model.showDetails(service);
	/*service.setChkfield("saved");*/
	model.terminate();
	return SUCCESS;
}
public String reset(){
	service.setServiceCode("");
	service.setSubheadName("");
	service.setServiceName("");
	service.setServiceLink("");
	service.setChkfield("");
	return SUCCESS;
}

public Services getService() {
	return service;
}

public void setService(Services service) {
	this.service = service;
}
}
