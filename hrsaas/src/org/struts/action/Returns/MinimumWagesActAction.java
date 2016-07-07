package org.struts.action.Returns;

import org.paradyne.bean.Returns.MinimumWagesActBean;
import org.paradyne.model.Returns.MinimumWagesActModel;
import org.struts.lib.ParaActionSupport;

public class MinimumWagesActAction extends ParaActionSupport {

	MinimumWagesActBean minwageBean;
	public void prepare_local() throws Exception {
		minwageBean= new MinimumWagesActBean();
		minwageBean.setMenuCode(1138);
	}

	public Object getModel() {
		
		return minwageBean;
	}

	
	public MinimumWagesActBean getMinwageBean() {
		return minwageBean;
	}

	
	public void setMinwageBean(MinimumWagesActBean minwageBean) {
		this.minwageBean = minwageBean;
	}

	/**This input function is get called for displaying Onload List*/
	public String input()  {
System.out.println("m in input");


getNavigationPanel(1);
	return "input";
	}
	
	
}
