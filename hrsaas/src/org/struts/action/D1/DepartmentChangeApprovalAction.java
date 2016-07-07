package org.struts.action.D1;

import java.util.List;

import org.paradyne.bean.D1.DepartmentChangeApprovalBean;
import org.paradyne.bean.D1.PersonalDataChangeApproval;
import org.paradyne.model.D1.DepartmentChangeApprovalModel;
import org.paradyne.model.D1.DepartmentandLocationChangeModel;
import org.paradyne.model.D1.PersonalDataChangeApprovalModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentChangeApprovalAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalAction.class);
	DepartmentChangeApprovalBean bean;
	/**
	 * @return the logger
	 */
	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}
	/**
	 * @param logger the logger to seti
	 */
	public static void setLogger(org.apache.log4j.Logger logger) {
		DepartmentChangeApprovalAction.logger = logger;
	}
	/**
	 * @return the bean
	 */
	public DepartmentChangeApprovalBean getBean() {
		return bean;
	}
	/**
	 * @param bean the bean to set
	 */
	public void setBean(DepartmentChangeApprovalBean bean) {
		this.bean = bean;
	}
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new DepartmentChangeApprovalBean();
		bean.setMenuCode(1175);
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	@Override
	public String input() throws Exception {
		try {
			getList();
			return SUCCESS;
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		/*model.initialData(deptlocChangeBean, request);
		model.pendingData(deptlocChangeBean, request);
		model.sendBack(deptlocChangeBean, request);*/
		input();
		getList();
		model.terminate();
	}
	
	/*private void getList() {
		try {
			DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
			model.initiate(context, session);
			
			String userId = bean.getUserEmpId();
			System.out.println("userId----"+userId);
			boolean isUserAHRApprover = model.isUserAHRApprover(userId);
			
			if(isUserAHRApprover) {
				List pendingAppList = model.getForwardedApplicationList();
				bean.setPendingAppList(pendingAppList);
				
				List approvedAppList = model.getApprovedApplicationList();
				bean.setApprovedAppList(approvedAppList);
				
				List rejectedAppList = model.getRejectedApplicationList();
				bean.setRejectedAppList(rejectedAppList);
			} else {
				List pendingAppList = model.getPendingApplicationList(userId);
				bean.setPendingAppList(pendingAppList);
				
				List approvedAppList = model.getApprovedApplicationList(userId);
				bean.setApprovedAppList(approvedAppList);
				
				List rejectedAppList = model.getRejectedApplicationList(userId);
				bean.setRejectedAppList(rejectedAppList);
			}
			
			

			model.terminate();
		} catch(Exception e) {
			logger.error(e);
		}
	}*/
	private void getList() {

	try {
		DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
		model.initiate(context, session);
		
		String userId = bean.getUserEmpId();
		System.out.println("inside hr ---yogesh---"+userId);
		String pageForPendingApp = bean.getPageForPendingApp();
		String pageForPendingCancelApp = bean.getPageForPendingCancelApp();
		String pageForApprovedApp = bean.getPageForApprovedApp();
		String pageForRejectedApp = bean.getPageForRejectedApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);
	
		if(isUserAHRApprover) {
			System.out.println("inside HR");
			/**
			 * Forwarded application list
			 */
			List forwardedAppList = model.getForwardedApplicationList(pageForPendingApp, request,userId);	
			
			if(forwardedAppList != null && !forwardedAppList.isEmpty() && !forwardedAppList.equals("null")) {
				bean.setPagingForPendingApp(true);
				bean.setPendingAppList(forwardedAppList);
			} else {
				System.out.println("inside else HR");
				bean.setPagingForPendingApp(false);
			}
			
			
			/**
			 * Pending cancelled application list
			 */
			List pendingCancelAppList = model.getPendingCancellationApplicationListHR(pageForPendingCancelApp, request,userId);
			if(pendingCancelAppList != null && !pendingCancelAppList.isEmpty() && !pendingCancelAppList.equals("null")) {
				bean.setPagingForPendingCancelApp(true);
				bean.setPendingCancelAppList(pendingCancelAppList);
			} else {
				System.out.println("inside else HR1");
				///bean.setPendingCancelAppList(null);
				bean.setPagingForPendingCancelApp(false);
			}
			
			
			/**
			 * Approved application list
			 */
			List approvedAppList = model.getApprovedApplicationListHR(pageForApprovedApp, request,userId);
			if(approvedAppList != null && !approvedAppList.isEmpty() && !approvedAppList.equals("null")) {
				bean.setPagingForApprovedApp(true);
				bean.setApprovedAppList(approvedAppList);
			} else {
				System.out.println("inside else HR3");
				//bean.setApprovedAppList(null);
				bean.setPagingForApprovedApp(false);
			}
		
			
			/**
			 * Rejected application list
			 */
			List rejectedAppList = model.getRejectedApplicationListHR(pageForRejectedApp, request,userId);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()&& !rejectedAppList.equals("null")) {
				bean.setRejectedAppList(rejectedAppList);
				bean.setPagingForApprovedApp(true);
			} else {
				System.out.println("inside else 4HR");
				//bean.setRejectedAppList(null);
				bean.setPagingForApprovedApp(false);
			}
			
		} else {
			System.out.println("inside Manager");
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
			
			/**
			 * Approved application list
			 */
			List approvedAppList = model.getApprovedApplicationList(userId, pageForApprovedApp, request);
			if(approvedAppList != null && !approvedAppList.isEmpty()) {
				bean.setPagingForApprovedApp(true);
			} else {
				bean.setPagingForApprovedApp(false);
			}
			bean.setApprovedAppList(approvedAppList);
			
			/**
			 * Rejected application list
			 */
			List rejectedAppList = model.getRejectedApplicationList(userId, pageForRejectedApp, request);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()) {
				bean.setPagingForRejectedApp(true);
			} else {
				bean.setPagingForRejectedApp(false);
			}
			bean.setRejectedAppList(rejectedAppList);
		}

		model.terminate();
	} catch(Exception e) {
		logger.error(e);
	}
}
	
	public String callPage() {
		getList();
		return SUCCESS;
	}
}
