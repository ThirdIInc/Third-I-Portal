package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.ClassEnrollmentApprover;
import org.paradyne.bean.D1.PersonalDataChangeApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ClassEnrollmentApproverModel;
import org.paradyne.model.D1.PersonalDataChangeApprovalModel;
import org.paradyne.model.D1.PersonalDataChangeModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChangeApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalAction.class);

	PersonalDataChangeApproval bean;

	public String callPage() {
		getPendingList();
		return SUCCESS;
	}
	public String input()
	{
		try {
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if(isITDeptApprover)
			{
				model.getPendingList(bean, request);	
			}
			else
			{
				model.getPendingList(bean, request,userId);	
			}
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String viewApplicationFunction()
	{
		String typeOfList = bean.getListType();
		logger.info("List Type : "+typeOfList);
		bean.setListTypeDetailPage(typeOfList);
		
		try {
			String applicationID = request.getParameter("persDataChangeApproverId");
			System.out.println("hiddenCode = " + applicationID);
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			model.viewApplicationFunction(bean, applicationID, typeOfList);
			Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(typeOfList.equals("pending"))
		{
			bean.setApproverCommentsFlag(true);
			getNavigationPanel(5);
			bean.setEnableAll("N");
		}else
			if(typeOfList.equals("approved"))
			{
				bean.setEnableAll("N");
				getNavigationPanel(2);
			}
			else
				{
					bean.setEnableAll("N");
					getNavigationPanel(2);
				}
		
		return SUCCESS;
	}
	
	
	
	private void populateApproverComments(Object[][] apprCommentListObj) {
		ArrayList approverCommentList = new ArrayList<Object>(apprCommentListObj.length);

		for(int i = 0; i < apprCommentListObj.length; i++) {
			PersonalDataChangeApproval innerBean = new PersonalDataChangeApproval();
			innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
			innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
			innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
			innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
			approverCommentList.add(innerBean);
		}
		bean.setApproverCommentList(approverCommentList);
	}
	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
		
	public String backToList() throws Exception {
		input();
		return INPUT;
	}
	
	public String approveApplication()
	{
		try {	
			String applicationId = bean.getPersDataChangeApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getInitiatorCode();
			
			
			String status = bean.getApplnStatus();
			System.out.println("status=======" + status);
			int level = Integer.parseInt(bean.getLevel());

			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			String checkApproverQuery = "SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'H'";
			Object chk[][] = model.getSqlModel().getSingleResult(
					checkApproverQuery);
			
			if (chk != null && chk.length > 0) {
			
			String nextApprover = userId;

			/*Object[][] empFlow = generateEmpFlow(requesterCode, "D1", (level + 1));

			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
				level += 1;
			}
*/			
			
			/*
			if(status.equals("C")) {
				Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
				
				if(empFlow != null && empFlow.length > 0) {
					nextApprover = String.valueOf(empFlow[0][0]);
				}
			} else {
				Object[][] empFlow = generateEmpFlow(employeeCode, "D1", (level + 1));

				if(empFlow != null && empFlow.length > 0) {
					nextApprover = String.valueOf(empFlow[0][0]);
					level += 1;
				}
			}*/
			
			System.out.println("nextApprover ===== " + nextApprover);
			System.out.println("level = "+ level);
			status = model.approve(applicationId, approverComments, userId, status, nextApprover, level);
/*
			Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);*/
			bean.setApplnStatus(status);			
			bean.setApproverComments("");

			addActionMessage("Application approved successfully.");
			System.out.println("status=======" + status);
			if(model.isUserAMagaer(applicationId, userId)) {
				sendApprovalMail(applicationId, userId, employeeCode, status);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status);
			}
			
			model.terminate();
			} else {
				
				addActionMessage("HR Authorities Approver Not Defined for this application\n"
						+ "Please Contact your HR Department. ");
			}
	    } catch (Exception e) {
		  e.printStackTrace();
	  }	
	    input();
	    return INPUT;
	}
	
	private void sendApprovalMailToApplicantFinal(String persDataChangeId, String userId, String employeeCode, String status) {
		try {
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " WHERE APP_SETTINGS_TYPE = 'H' AND APP_EMAIL_ID IS NOT NULL ";// ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			String initiatorQuery = " select NVL(PERS_EMP_ID,0) from HRMS_D1_PERSONAL_DATA_CHANGE where PERS_CHANGE_ID = "+ persDataChangeId;
			Object initiatorData[][] = model.getSqlModel().getSingleResult(initiatorQuery);
			
			String apprQuery = " SELECT PERS_APPROVER FROM HRMS_D1_PERS_DATA_PATH WHERE PERS_CHANGE_ID =  "+ persDataChangeId;
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, persDataChangeId);
			templateApp.configMailAlert();
			String[] empData = null;
			
			if(initiatorData != null && initiatorData.length > 0) {
				empData = new String[initiatorData.length];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(initiatorData[i][0]);
				}
			}
			
			String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 1) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}
			
			
			
			String keepData[]=new String[1];
			keepData[0]=String.valueOf(apprDataObj[0][0]);
			
			
			if (status.equals("A")) {
				System.out.println("bean.getEmployeeCode()====" + bean.getEmployeeCode());
				System.out.println("bean.getInitiatorCode()====" + bean.getInitiatorCode());
				if(!bean.getEmployeeCode().equals(bean.getInitiatorCode())){
					System.out.println("INSIDE IFFFFFFF");
					templateApp.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
				}
				
			}
			
			templateApp.sendApplicationMailToKeepInfo(keepData);
			if(data != null && data.length > 0) {
				
				templateApp.sendApplicationMailToGroups(data);
			}
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void sendApprovalMail(String persDataChangeId, String userId, String employeeCode, String status) {

		try {
			System.out.println("SEND APPROVAL MAIL");
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, persDataChangeId);
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}

			try {
				PersonalDataChangeModel model = new PersonalDataChangeModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
				/*
				 * String query = "SELECT NVL(APP_SETTINGS_EMP_ID,0) FROM HRMS_D1_APPROVAL_SETTINGS " + " where APP_SETTINGS_TYPE = 'H' ORDER BY
				 * APP_SETTINGS_EMP_ID";
				 */

				
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS FROM  APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, "0");
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, persDataChangeId);
				/*EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, String.valueOf(data[0][0]));*/
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, persDataChangeId);
				//String[] empData = null;
				
				
				String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'H' AND APP_EMAIL_ID IS NOT NULL ";  //ORDER BY APP_SETTINGS_EMP_ID";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				
				/*if(data != null && data.length > 1) {
					empData = new String[data.length];
					for(int i = 1; i < empData.length; i++) {
						empData[i] = String.valueOf(data[i][0]);
					}
					
					System.out.println("empData = " + empData.length);
				}*/

				templateApp.configMailAlert();
				//templateApp.sendApplicationMailToKeepInfo(empData);
				if(data != null && data.length > 0) {
					templateApp.configMailAlert();
					templateApp.sendApplicationMailToGroups(data);
				}
				//templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}
	
	public String sendBackApplication()
	{
		try {
			String applicationId = bean.getPersDataChangeApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getInitiatorCode();

			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);

			String nextApprover = userId;
			
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}
				
			String status = model.sendBack(applicationId, approverComments, userId, nextApprover);
			addActionMessage("Application send back successfully.");
			
			if(model.isUserAMagaer(applicationId, userId)) {
				sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status);
			}
			
			
			
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	
	public String rejectApplication()
	{
		try {
			String applicationId = bean.getPersDataChangeApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			//String requesterCode = bean.getRequesterID();
			String status = bean.getApplnStatus();
			String employeeCode = bean.getEmployeeCode();
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			
				String nextApprover = userId;
			
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}
			
			
			model.reject(applicationId, approverComments, userId);
			System.out.println("status=======" + status);
			if(model.isUserAMagaer(applicationId, userId)) {
				sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status);
			}
			
			model.terminate();
			addActionMessage("Application rejected successfully.");

	
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	private void sendRejectSenBackMailToApplicant(String persDataChangeId, String userId, String employeeCode, String status) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS REJECTED/SENDBACK FROM  APPROVER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, persDataChangeId);
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getRejectedList() throws Exception {		
		try {
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if(isITDeptApprover)
			{
				model.getRejectedList(bean, request);
			}
			else
			{
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String getApprovedList() throws Exception {		
		try {
			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if(isITDeptApprover)
			{
				model.getApprovedList(bean, request);
			}
			else
			{
				model.getApprovedList(bean, request, userId);
			}
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/// end
	public String getApprovedList_old() {
		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);

		String userId = bean.getUserEmpId();
		String pageForApprovedApp = bean.getPageForApprovedApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if(isUserAHRApprover) {
			List approvedAppList = model.getApprovedApplicationList(pageForApprovedApp, request);
			if(approvedAppList != null && !approvedAppList.isEmpty()) {
				bean.setPagingForApprovedApp(true);
			} else {
				bean.setPagingForApprovedApp(false);
			}
			bean.setApprovedAppList(approvedAppList);
		} else {
			List approvedAppList = model.getApprovedApplicationList(userId, pageForApprovedApp, request);
			if(approvedAppList != null && !approvedAppList.isEmpty()) {
				bean.setPagingForApprovedApp(true);
			} else {
				bean.setPagingForApprovedApp(false);
			}
			bean.setApprovedAppList(approvedAppList);
		}

		bean.setListType("approved");

		model.terminate();
		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	public String getPendingList() {
		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);

		String userId = bean.getUserEmpId();
		String pageForPendingApp = bean.getPageForPendingApp();
		String pageForPendingCancelApp = bean.getPageForPendingCancelApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if(isUserAHRApprover) {
			/**
			 * Forwarded application list
			 */
			List forwardedAppList = model.getForwardedApplicationList(pageForPendingApp, request);
			if(forwardedAppList != null && !forwardedAppList.isEmpty()) {
				bean.setPagingForPendingApp(true);
			} else {
				bean.setPagingForPendingApp(false);
			}
			bean.setPendingAppList(forwardedAppList);

			/**
			 * Pending cancelled application list
			 */
			List pendingCancelAppList = model.getPendingCancellationApplicationList(pageForPendingCancelApp, request);
			if(pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				bean.setPagingForPendingCancelApp(true);
			} else {
				bean.setPagingForPendingCancelApp(false);
			}
			bean.setPendingCancelAppList(pendingCancelAppList);
		} else {
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
		}

		bean.setListType("pending");

		model.terminate();
		return SUCCESS;
	}

	public String getRejectedList_old() {
		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);

		String userId = bean.getUserEmpId();
		String pageForRejectedApp = bean.getPageForRejectedApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if(isUserAHRApprover) {
			List rejectedAppList = model.getRejectedApplicationList(pageForRejectedApp, request);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()) {
				bean.setPagingForRejectedApp(true);
			} else {
				bean.setPagingForRejectedApp(false);
			}
			bean.setRejectedAppList(rejectedAppList);
		} else {
			List rejectedAppList = model.getRejectedApplicationList(userId, pageForRejectedApp, request);
			if(rejectedAppList != null && !rejectedAppList.isEmpty()) {
				bean.setPagingForRejectedApp(true);
			} else {
				bean.setPagingForRejectedApp(false);
			}
			bean.setRejectedAppList(rejectedAppList);
		}

		bean.setListType("rejected");

		model.terminate();
		return INPUT;
	}

	//@Override
	/*public String input() throws Exception {
		try {
			getPendingList();
			return SUCCESS;
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new PersonalDataChangeApproval();
		bean.setMenuCode(1171);
	}
}