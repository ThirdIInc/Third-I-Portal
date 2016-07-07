/* @author: Bhushan Dasare   @date: Aug 13, 2009 */

package org.struts.action.ApplicationStudio;

import org.struts.lib.ParaActionSupport;

public class NavigationPanelAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NavigationPanelAction.class);
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return null;
	}
	
	public void getNavigationButtons() {
		try {
			int nextMode = Integer.parseInt(request.getParameter("nextMode"));
			getNavigationPanel(nextMode);
		} catch(Exception e) {
			logger.error("Exception in getNavigationButtons:" + e);
		}
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {}
}