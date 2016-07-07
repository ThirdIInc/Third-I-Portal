package org.struts.action.settlement;

import org.paradyne.bean.settlement.ResignationDetails;
import org.paradyne.model.settlement.ResignationDetailsModel;
import org.struts.lib.ParaActionSupport;

public class ResignEmployeeListAction extends ParaActionSupport {

	 
	ResignationDetails resig;
	
	public void prepare_local() throws Exception {
		resig = new ResignationDetails();
		 resig.setMenuCode(35);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return resig;
	}

	public ResignationDetails getResig() {
		return resig;
	}

	public void setResig(ResignationDetails resig) {
		this.resig = resig;
	}

	public String resignEmployeeList()
	{
		try {
			ResignationDetailsModel model = new ResignationDetailsModel();
			model.initiate(context, session);
			model.getResignEmployeeList(resig);
			model.terminate();
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "resignEmpList";
	}
	
}
