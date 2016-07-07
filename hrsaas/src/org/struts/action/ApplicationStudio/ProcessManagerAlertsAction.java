/**
 * @author Bhushan Dasare
 * @date Feb 11, 2009
 */

package org.struts.action.ApplicationStudio;

import org.paradyne.lib.BeanBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class ProcessManagerAlertsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProcessManagerAlertsAction.class);
	BeanBase bean;
	
	public String getAlert() {
		try {
			ProcessManagerAlertsModel model = new ProcessManagerAlertsModel();
			model.initiate(context, session);
			
			String alertID = PPEncrypter.decrypt(request.getParameter("alertID"));
			model.getAlert(alertID, request);
			
			return "alert";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public BeanBase getBean() {
		return bean;
	}

	public Object getModel() {
		return bean;
	}
	
	public void prepare_local() throws Exception {
		bean = new BeanBase();
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			ProcessManagerAlertsModel model = new ProcessManagerAlertsModel();
			model.initiate(context, session);
			
			String userEmpId = bean.getUserEmpId();
			
			model.showAlerts(userEmpId, request, getText("data_path"));
			model.showAlertsAppraisal(userEmpId, request);
			
			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public void setBean(BeanBase bean) {
		this.bean = bean;
	}
}