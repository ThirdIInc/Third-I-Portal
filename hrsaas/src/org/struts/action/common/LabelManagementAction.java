/**
 * 
 */
package org.struts.action.common;

import java.io.PrintWriter;
import org.paradyne.model.common.LabelManagementModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 * Action class that is being used to 
 * change the label and restore the label from 
 * user screen
 */
public class LabelManagementAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LabelManagementAction.class);
	
	public void prepare_local() throws Exception {
	}

	public Object getModel() {
		return null;
	}
	
	/**
	 * Action gets call when user changes the label on any form
	 * Method gets call by ajax and sends back the response
	 */
	public void changeLabel()
	{
		String menuCode = request.getParameter("menuCode");
		String labelId = request.getParameter("labelId");
		String labelVal = request.getParameter("labelVal");
		LabelManagementModel model = new LabelManagementModel();
		model.initiate(context, session);
		//model method call to change the label
		boolean res = model.changeLabel(labelId, labelVal,menuCode,getText("data_path"));
		
		// code to send back response to AJAX method
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(!res)
				out.println("false");
			else
				out.println("true");
		} // try end
		catch(Exception e)
		{
			logger.error("Exception in restore label while setting response content : "+e);
		}
		model.terminate();
	} // end of chaneLabel Method
	
	
	/**
	 * this action gets call when user restores any label on screen
	 * Method gets call by ajax and sends back the response
	 */
	public void restoreLabel()
	{
		try{
			String menuCode = request.getParameter("menuCode");
			String labelId = request.getParameter("labelId");
			LabelManagementModel model = new LabelManagementModel();
			model.initiate(context, session);
			// model method call to restore the label
			String responseLabel = model.restoreLabel(labelId, menuCode, getText("data_path"));
			logger.info("responseLabel : "+responseLabel);
			//code to send back the response to AJAX method
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responseLabel);
			}//try block end
			catch(Exception e)
			{
				logger.error("Exception in restore label while setting response content : "+e);
			} // catch block end
		}catch(Exception e)
		{
			logger.error("Exception while restoring label "+e);
		}
	} // End of restoreLabel

} // End of class
