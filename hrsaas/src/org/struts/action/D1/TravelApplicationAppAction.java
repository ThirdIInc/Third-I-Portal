package org.struts.action.D1;

import java.util.List;
import org.paradyne.bean.D1.TravelApplicationAppBean;
import org.paradyne.model.D1.TravelApplicationAppModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1439
 *
 */
public class TravelApplicationAppAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalAction.class);
	TravelApplicationAppBean bean;
	@Override
	public void prepare_local() throws Exception {
		bean = new TravelApplicationAppBean();
		bean.setMenuCode(2026);
		
	}

	public Object getModel() {
		return bean;
	}

	/**
	 * @return the bean
	 */
	public TravelApplicationAppBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(TravelApplicationAppBean bean) {
		this.bean = bean;
	}

	
	@Override
	public String input() throws Exception {
		try {
			String list = checkNull(bean.getListType());
			if ("".equals(list)) {
				getPendingList();
			} else if(bean.getListType().equals("approved")) {
				getApprovedList();
			} else if(bean.getListType().equals("rejected")) {
				getRejectedList();
			} else {
				getPendingList();
			}
		} catch(Exception e) {
			logger.error(e);
		}
		return INPUT;
	}
	
	public String getPendingList() {
		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);
		String userId = bean.getUserEmpId();
		String pageForPendingApp = bean.getPageForPendingApp();
		String pageForPendingCancelApp = bean.getPageForPendingCancelApp();
		
		/*boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if(isUserAHRApprover) {
			
			// Forwarded application list
			
			List forwardedAppList = model.getForwardedApplicationList(pageForPendingApp, request);
			if(forwardedAppList != null && !forwardedAppList.isEmpty()) {
				bean.setPagingForPendingApp(true);
			} else {
				bean.setPagingForPendingApp(false);
			}
			bean.setPendingAppList(forwardedAppList);

			
			 // Pending cancelled application list
			 
			List pendingCancelAppList = model.getPendingCancellationApplicationList(pageForPendingCancelApp, request);
			if(pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				bean.setPagingForPendingCancelApp(true);
			} else {
				bean.setPagingForPendingCancelApp(false);
			}
			bean.setPendingCancelAppList(pendingCancelAppList);
		} else {*/
			List pendingAppList = model.getPendingApplicationList(userId, pageForPendingApp, request);
			if(pendingAppList != null && !pendingAppList.isEmpty()) {
				bean.setPagingForPendingApp(true);
			} else {
				bean.setPagingForPendingApp(false);
			}
			bean.setPendingAppList(pendingAppList);

			/**
			 * Pending cancelled application list
			 */
			List pendingCancelAppList = model.getPendingCancellationApplicationList(userId, pageForPendingCancelApp, request);
			if(pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				bean.setPagingForPendingCancelApp(true);
			} else {
				bean.setPagingForPendingCancelApp(false);
			}
			bean.setPendingCancelAppList(pendingCancelAppList);
		//}

		bean.setListType("pending");

		model.terminate();
		return SUCCESS;
	}
	
	public String getApprovedList() {
		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);

		String userId = bean.getUserEmpId();
		String pageForApprovedApp = bean.getPageForApprovedApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		/*if(isUserAHRApprover) {
			List approvedAppList = model.getApprovedApplicationList(pageForApprovedApp, request);
			if(approvedAppList != null && !approvedAppList.isEmpty()) {
				bean.setPagingForApprovedApp(true);
			} else {
				bean.setPagingForApprovedApp(false);
			}
			bean.setApprovedAppList(approvedAppList);
		} else {*/
			List approvedAppList = model.getApprovedApplicationList(userId, pageForApprovedApp, request);
			if(approvedAppList != null && !approvedAppList.isEmpty()) {
				bean.setPagingForApprovedApp(true);
			} else {
				bean.setPagingForApprovedApp(false);
			}
			bean.setApprovedAppList(approvedAppList);
		//}

		bean.setListType("approved");

		model.terminate();
		return SUCCESS;
	}
	
	public String getRejectedList() {
		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);

		String userId = bean.getUserEmpId();
		String pageForRejectedApp = bean.getPageForRejectedApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		/*if(isUserAHRApprover) {
			List rejectedAppList = model.getRejectedApplicationList(pageForRejectedApp, request);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()) {
				bean.setPagingForRejectedApp(true);
			} else {
				bean.setPagingForRejectedApp(false);
			}
			bean.setRejectedAppList(rejectedAppList);
		} else {*/
			List rejectedAppList = model.getRejectedApplicationList(userId, pageForRejectedApp, request);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()) {
				bean.setPagingForRejectedApp(true);
			} else {
				bean.setPagingForRejectedApp(false);
			}
			bean.setRejectedAppList(rejectedAppList);
		//}

		bean.setListType("rejected");

		model.terminate();
		return SUCCESS;
	}

	 /**
     * Method to check string is null or not.
     * 
     * @param result - Check null or not
     * @return - String after checking null
     */
    public String checkNull(final String result) {

            if (result == null || "null".equals(result)) {
                    return "";
            } else {
                    return result;
            }
    }
}
