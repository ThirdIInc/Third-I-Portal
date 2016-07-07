package org.struts.action.vendor;

import org.paradyne.bean.vendor.VendorHomePage;
import org.paradyne.model.vendor.VendorLoginModel;


public class VendorHomePageAction extends VendorActionSupport {

	VendorHomePage bean =null;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean =new  VendorHomePage();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	
	public String input(){
		return SUCCESS;
	}
	
	public String create(){
		try {
			String newMenuCode = request.getParameter("menuCode");
			System.out.println("newMenuCode  "+newMenuCode);
			VendorLoginModel model = new VendorLoginModel();
			model.initiate(context, session);
			model.createMainMenu(request, newMenuCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
