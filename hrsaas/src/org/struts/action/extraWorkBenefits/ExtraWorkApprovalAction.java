/**
 * 
 */
package org.struts.action.extraWorkBenefits;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkApproval;
import org.paradyne.model.extraWorkBenefits.ExtraWorkApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class ExtraWorkApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ExtraWorkApprovalAction.class);
	
	ExtraWorkApproval approval = null;
	public ExtraWorkApproval getApproval() {
		return approval;
	}

	public void setApproval(ExtraWorkApproval approval) {
		this.approval = approval;
	}
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		approval = new ExtraWorkApproval();
		approval.setMenuCode(564);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return approval;
	}
	
	public String callstatus() {
		ExtraWorkApprovalModel model = new ExtraWorkApprovalModel();
		model.initiate(context, session);
		model.getAllTypeOfApplications(approval, request,approval.getUserEmpId());
		approval.setListType("pending");
		model.terminate();
		return SUCCESS;
	}
	
	public String getApprovedList() throws Exception {
		 
		try {
			ExtraWorkApprovalModel model = new ExtraWorkApprovalModel();
			model.initiate(context, session);
			model.getApprovedList(approval, request, approval
					.getUserEmpId());
			approval.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList method : " + e);
		}
		return SUCCESS;
	}
	
	
	public String getRejectedList() throws Exception {
		 
		try {
			ExtraWorkApprovalModel model = new ExtraWorkApprovalModel();
			model.initiate(context, session);
			model.getRejectedList(approval, request, approval
					.getUserEmpId());
			approval.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList method : " + e);
		}
		return SUCCESS;
	}

}
