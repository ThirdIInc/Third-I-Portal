/**
 * 
 */
package org.struts.action.Asset;


import org.paradyne.bean.Asset.PurchaseInward;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.Asset.PurchaseInwardModel;
import org.struts.lib.ParaActionSupport;

import common.Logger;

/**
 * @author mangeshj
 *
 */
public class PurchaseInwardAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.action.Asset.PurchaseInwardAction.class);
	PurchaseInward inward;
	public void prepare_local() throws Exception {
		inward = new PurchaseInward();
		inward.setMenuCode(659);
	}

	
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			PurchaseInwardModel model = new PurchaseInwardModel();
			logger.info("Status in prepare_withLoginProfileDetails =="
					+ inward.getStatus());
			model.initiate(context, session);
			model.showList(inward, "P", request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return inward;
	}

	public PurchaseInward getInward() {
		return inward;
	}

	public void setInward(PurchaseInward inward) {
		this.inward = inward;
	}
	
	public String checkData(){
		try {
			PurchaseInwardModel model = new PurchaseInwardModel();
			model.initiate(context, session);
			model.showList(inward, inward.getStatus(), request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String cancelOrder(){
		try {
			PurchaseInwardModel model = new PurchaseInwardModel();
			model.initiate(context, session);
			if (model.cancelOrder(inward)) {
				addActionMessage("Purchase Order canceled successfully.");

				//------------------------Process Manager Alert------------------------start
				String applnID = inward.getHiddenPurchaseCode();
				String module = "Purchase";

				String applicant = "", oldApprover = "";
				try {
					applicant = inward.getEmpCodeHidden();
					oldApprover = inward.getUserEmpId();
				} catch (Exception e) {
					logger.error(e);
				}
				String alertLevel = "1";
				sendCancelAlert(applnID, module, applicant, oldApprover,
						alertLevel);
				//------------------------Process Manager Alert------------------------end

			} else
				addActionMessage("Purchase Order can't be canceled. ");
			model.showList(inward, inward.getStatus(), request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS ;
	}
	
	public void sendCancelAlert(String applnID, String module, String applicant, String oldApprover, String alertLevel) {
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			
			processAlerts.removeProcessAlert(applnID, module);
			
			module ="Purchase Inward";
			processAlerts.removeProcessAlert(applnID, module);
			
			String empID = "", msgType = "";
			
				empID = applicant;
				msgType = "I";
										
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				
				template.setEmailTemplate("PURCHASE - ASSIGNER CANCEL");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);
				
				template.configMailAlert();
				try {
					template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					template.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
