package org.struts.action.CR;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.D1AnalyticHome;
import org.paradyne.model.CR.D1AnalyticHomeModel;
import org.paradyne.model.CR.DataReconcileModel;
import org.struts.lib.ParaActionSupport;

/**@purpose : Create Home page for Analytics Module
 * @author AA1711
 * @Date : 17-Jan-2013
 */

public class D1AnalyticHomeAction  extends ParaActionSupport{
	private static final long serialVersionUID = 1L;
	
	D1AnalyticHome d1Home;
	
	static Logger logger = Logger.getLogger(ParaActionSupport.class);
	/**Method Name : prepare_local
	 *  (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		d1Home= new D1AnalyticHome();
		d1Home.setMenuCode(2317);
	}

	/** Method Name :getModel()
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return d1Home;
	}
	
	/**Method Name: input()
	 * @purpose : Used to set Sub Menu 
	 * @return String
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input() throws Exception{
		D1AnalyticHomeModel model= new D1AnalyticHomeModel();
		model.initiate(context, session);
		String d1MenuType= "ANLT";
		model.getD1MenuList(request,d1Home,d1MenuType,response);
		HttpSession session = request.getSession();
		 String userType=(String) session.getAttribute("userType");
		 
		if(!userType.equals("E")){
					String [][] portalD1Obj = model.getD1PortalLink(request,d1Home);
					request.setAttribute("portalD1Obj", portalD1Obj);
		}
		//addActionMessage("Hello..........");
		
		model.terminate();
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails()
    throws Exception
  {
		D1AnalyticHomeModel model= new D1AnalyticHomeModel();
	    model.initiate(this.context, this.session);
	    model.terminate();
  }
	
	/**Method Name :getD1HomeMenu()
	 * Used to assign SubMenu
	 * @return String
	 * @throws Exception
	 */
	public String getD1HomeMenu() throws Exception{
		D1AnalyticHomeModel model = new D1AnalyticHomeModel();
		model.initiate(context, session);
		String d1MenuType= request.getParameter("menuType");
		model.getD1MenuList(request,d1Home,d1MenuType,response);
		model.terminate();
		return null;
	}
	
	/**added by vivek wadhwani
	 * Purpose : This method is used to change password.
	 * @return string
	 */
	public String changePassWordClient() {
		D1AnalyticHomeModel model = new D1AnalyticHomeModel();
		
		model.initiate(context, session);
		boolean result = model.changePassWord(d1Home);
		if (result) {
			System.out.println("1111111111111");
			String str="Password changed successfully";
			addActionMessage(str);	
		} else {
			System.out.println("2222222222222222222");
			String str="Invalid Old Password";
			addActionMessage(str);
			
		}
		model.terminate();
		return "clientChangePass";
	}
	
	/**.
	 * Purpose : This method is used to change password.
	 * @return string
	 */
	public String changePassJsp() {
		
		return "clientChangePass";
	}
}
