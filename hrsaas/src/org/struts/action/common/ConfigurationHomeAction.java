/**
 * 
 */
package org.struts.action.common;

import org.paradyne.bean.common.ConfigurationHome;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.ConfigurationHomeModel;
import org.paradyne.model.common.HomePageModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class ConfigurationHomeAction extends ParaActionSupport {
	
	ConfigurationHome configHome;
	public void prepare_local() throws Exception {
		configHome = new ConfigurationHome();
	}
	
	public Object getModel() {
		return configHome;
	}
	
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		/**For new Design of Configuration's Home Page*/
		//model.getDashletConfig("415", configHome.getUserProfileId(),configHome.getUserLoginCode(), request);
		model.terminate();
		return "input";
	}
	
	public String getConfigureMenu(){
		try {
			HomePageModel model = new HomePageModel();
			model.initiate(context, session);
			String menuType= request.getParameter("menuType");
			model.getMenuList(request, configHome,menuType);
			model.terminate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
}

