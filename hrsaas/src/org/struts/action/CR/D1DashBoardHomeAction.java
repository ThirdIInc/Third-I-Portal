package org.struts.action.CR;

import org.paradyne.bean.CR.D1DashBoardHome;
import org.paradyne.model.CR.D1DashBoardHomeModel;
import org.struts.lib.ParaActionSupport;

/**@purpose : Create Home page for DashBoard Module
 * @author AA1711
 * @Date : 21-Jan-2013
 */
public class D1DashBoardHomeAction extends ParaActionSupport{
	private static final long serialVersionUID = 1L;
	
	D1DashBoardHome dashBean;
	
	/** (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		dashBean= new D1DashBoardHome();
		
	}
	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {		
		return dashBean;
	}
	
	/**Method Name: input()
	 * @purpose : Used to set Sub Menu 
	 * @return String
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input() throws Exception{
		D1DashBoardHomeModel model= new D1DashBoardHomeModel();
		model.initiate(context, session);
		String d1MenuType= "DB";
		model.getD1DashMenuList(request,dashBean,d1MenuType);
		String [][] portalD1Obj = model.getD1DashPortalLink(request,dashBean);
		request.setAttribute("portalD1DashObj", portalD1Obj);
		model.terminate();
		return SUCCESS;
	}

}
