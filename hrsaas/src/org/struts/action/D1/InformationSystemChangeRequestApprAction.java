package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.paradyne.bean.D1.InformationSystemChangeRequestAppr;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.InformationSystemChangeRequestApprModel;
import org.struts.lib.ParaActionSupport;

public class InformationSystemChangeRequestApprAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(InformationSystemChangeRequestApprAction.class);
	
	InformationSystemChangeRequestAppr bean;

	public InformationSystemChangeRequestAppr getBean() {
		return bean;
	}

	public void setBean(InformationSystemChangeRequestAppr bean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {
		bean = new InformationSystemChangeRequestAppr();
		bean.setMenuCode(2057);
	}

	public Object getModel() {
		return bean;
	}
	
	public String input()
	{
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isItDeptApprover = model.isItDeptApprover(userId);
			if(isItDeptApprover) {
				model.getPendingList(bean, request);	
			} else {
				model.getPendingList(bean, request,userId);	
			}
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String getApprovedList() throws Exception {		
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			
			String userId = bean.getUserEmpId();
			boolean isItDeptApprover = model.isItDeptApprover(userId);
			if(isItDeptApprover) {
				model.getApprovedList(bean, request);
			} else {
				model.getApprovedList(bean, request, userId);
			}
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isItDeptApprover = model.isItDeptApprover(userId);
			if(isItDeptApprover) {
				model.getRejectedList(bean, request);
			} else {
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String approveApplication()
	{
		try {	
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			
			String checkApproverQuery = "SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'N'";
			Object chk[][] = model.getSqlModel().getSingleResult(checkApproverQuery);
			
			if (chk != null && chk.length > 0) {
			String applicationId = bean.getInfoSysReqApprId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getInitiatorCode();
			String status = bean.getStatus();
			String hrApprover = bean.getItApprover();
			String nextApprover = userId;
			String forwardToEmpRole = bean.getSelectForwardToRole();
			String uploadFileNameApprover = bean.getUploadFileNameApprover();
			
			status = model.approve(applicationId, approverComments, userId, status, nextApprover, forwardToEmpRole, uploadFileNameApprover);
			Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			bean.setStatus(status);			
			bean.setApproverComments("");

			addActionMessage("Application approved successfully.");
			
			String path=bean.getDataPath();
			String fileanme=bean.getUploadFileName();
			String optioanlfileanme=bean.getUploadOptionalFileName();
			
			if(model.isUserAMagaer(applicationId, userId)) {
				sendApprovalMail(applicationId, userId, employeeCode, status,hrApprover,path,fileanme,optioanlfileanme);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			}
			model.terminate();
			} else {
				addActionMessage("IT Authorities Approver Not Defined for this application\n"
						+ "Please Contact your HR Department. ");
			}
	    } catch (Exception e) {
		  e.printStackTrace();
	  }	
	    input();
	    return INPUT;
	}
		
	private void sendApprovalMail(String applicationId, String userId,
			String employeeCode, String status, String hrApprover, String path, String fileanme, String optioanlfileanme) {
		try {
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS FROM APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationId);
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationId);
				
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationId);
				
				EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, userId);
				
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				if(!fileanme.equals("")){
					String[]attachment=new String[1];
					attachment[0]=path+fileanme;
					template.sendApplMailWithAttachment(attachment);
				}
				else if(!optioanlfileanme.equals("")){
					String[]attachment=new String[1];
					attachment[0]=path+optioanlfileanme;
					template.sendApplMailWithAttachment(attachment);
				}
				else{
					template.sendApplicationMail();
				}
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				
				InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
				
				String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'N'AND APP_EMAIL_ID IS NOT NULL "; //AND APP_SETTINGS_EMP_ID NOT IN ("+bean.getUserEmpId()+") ";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				
					
						EmailTemplateBody templateApp = new EmailTemplateBody();
						templateApp.initiate(context, session);
						templateApp.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS FROM  APPROVER TO MANAGER");
						templateApp.getTemplateQueries();
						EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
						templateQueryApp1.setParameter(1, userId);
						EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
						templateQueryApp2.setParameter(1, "0");
						EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
						templateQueryApp3.setParameter(1, applicationId);
						EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
						templateQueryApp4.setParameter(1, applicationId);
						EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
						templateQueryApp5.setParameter(1, applicationId);
						
						/*EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
						templateQueryApp6.setParameter(1, "1");*/
						
						
						/*String[] empData = null;
						
						if(data != null && data.length > 1) {
							empData = new String[data.length];
							for(int i = 1; i < empData.length; i++) {
								empData[i] = String.valueOf(data[i][0]);
							}
						}*/
						//System.out.println("empData = " + empData.length);
						templateApp.configMailAlert();
						//templateApp.sendApplicationMailToKeepInfo(empData);
						if(data != null && data.length > 0) {
							
							//templateApp.sendApplicationMailToGroups(data);
							
							if(!fileanme.equals("")){
								String[]attachment=new String[1];
								attachment[0]=path+fileanme;
								templateApp.sendApplicationMailToGroups(data,attachment);
							}
							else if(!optioanlfileanme.equals("")){
								String[]attachment=new String[1];
								attachment[0]=path+optioanlfileanme;
								templateApp.sendApplicationMailToGroups(data,attachment);
							}
							else {
								templateApp.sendApplicationMailToGroups(data);
							}
						}

					//	templateApp.sendApplicationMail();
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
	
	private void sendApprovalMailToApplicantFinal(String applicationId, String userId, String employeeCode, String status, String path, String fileanme, String optioanlfileanme) {
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID  "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " WHERE APP_SETTINGS_TYPE = 'N' AND APP_EMAIL_ID IS NOT NULL ";// ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			/*String requestQuery = " select NVL(EMP_ID,0) from HRMS_D1_CLASS_ENROLL where ENROLL_ID = "+ applicationId;
			Object initiatorData[][] = model.getSqlModel().getSingleResult(requestQuery);*/
			
			String apprQuery = " SELECT DISTINCT INF_CNG_APPR_CODE FROM HRMS_D1_INF_CNG_PATH WHERE INF_CNG_CODE =  "+ applicationId;
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS SENDBACK/REJECT FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, userId);
			
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);

						
			/*String[] trainingData = null;
			
			if(data != null && data.length > 0) {
				trainingData = new String[data.length];
				for(int i = 1; i < trainingData.length; i++) {
					trainingData[i] = String.valueOf(data[i][0]);
				}
			}*/
			
			templateApp.configMailAlert();
			
			String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 0) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}
			
			
			
			String keepData[]=new String[1];
			keepData[0]=String.valueOf(apprDataObj[0][0]);
					
			
			//templateApp.sendApplicationMailToKeepInfo(keepData);
			//templateApp.sendApplMailWithAttachmentToKeepInf(keepData,attachment);
			
			if(!fileanme.equals("")){
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
			
				templateApp.sendApplMailWithAttachmentToKeepInf(keepData,attachment);
			}
			else if(!optioanlfileanme.equals("")){
				String[]attachment=new String[1];
			
				attachment[0]=path+optioanlfileanme;
				templateApp.sendApplMailWithAttachmentToKeepInf(keepData,attachment);
			}
			else{
				templateApp.sendApplMailWithAttachmentToKeepInf(keepData,null);
			}
			
			if(data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			
			if(!fileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
			
				templateApp.sendApplMailWithAttachment(attachment);
			} else if(!optioanlfileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+optioanlfileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void populateApproverComments(Object[][] apprCommentListObj) {
		ArrayList approverCommentList = new ArrayList<Object>(apprCommentListObj.length);
		for(int i = 0; i < apprCommentListObj.length; i++) {
			InformationSystemChangeRequestAppr innerBean = new InformationSystemChangeRequestAppr();
			innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
			innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
			innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
			innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
			innerBean.setApproverRole(checkNull(String.valueOf(apprCommentListObj[i][4])));
			innerBean.setUploadFileNameApproverItr(checkNull(String.valueOf(apprCommentListObj[i][5])));
			approverCommentList.add(innerBean);
		}
		bean.setApproverCommentList(approverCommentList);
	}
	
	
	public String rejectApplication()
	{
		try {
			String applicationId = bean.getInfoSysReqApprId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			
			String status = bean.getStatus();
			String employeeCode = bean.getInitiatorCode();
			String selectForwardToRole = bean.getSelectForwardToRole();
			String uploadFileNameApprover = bean.getUploadFileNameApprover();
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			model.reject(applicationId, approverComments, userId, selectForwardToRole, uploadFileNameApprover);

			Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			
			//sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
			model.terminate();
			addActionMessage("Application rejected successfully.");

			String path=bean.getDataPath();
			String fileanme=bean.getUploadFileName();
			String optioanlfileanme=bean.getUploadOptionalFileName();
			
			if(model.isUserAMagaer(applicationId, userId)) {
				sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	
	public String closedApplication()
	{
		try {
			String applicationId = bean.getInfoSysReqApprId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			
			String status = bean.getStatus();
			String employeeCode = bean.getInitiatorCode();
			String selectForwardToRole = bean.getSelectForwardToRole();
			String uploadFileNameApprover = bean.getUploadFileNameApprover();
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			model.closedApplication(applicationId, approverComments, userId, selectForwardToRole, uploadFileNameApprover);

			Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			
			//sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
			model.terminate();
			addActionMessage("Ticket Closed Successfully.");

			String path=bean.getDataPath();
			String fileanme=bean.getUploadFileName();
			String optioanlfileanme=bean.getUploadOptionalFileName();
			
			if(model.isUserAMagaer(applicationId, userId)) {
				sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	public String sendBackApplication()
	{
		try {
			String applicationId = bean.getInfoSysReqApprId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getInitiatorCode();
			String uploadFileNameApprover = bean.getUploadFileNameApprover();
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);

			String nextApprover = userId;
			String selectForwardToRole = bean.getSelectForwardToRole();
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}

			String status = model.sendBack(applicationId, approverComments, userId, nextApprover, selectForwardToRole, uploadFileNameApprover);

			/*Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);*/
			 
			bean.setStatus(status);
			bean.setApproverComments("");
			
		//sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
			
			addActionMessage("Application send back successfully.");
			
			String path=bean.getDataPath();
			String fileanme=bean.getUploadFileName();
			String optioanlfileanme=bean.getUploadOptionalFileName();
			
			if(model.isUserAMagaer(applicationId, userId)) {
				sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status,path,fileanme,optioanlfileanme);
			}
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	private void sendRejectSenBackMailToApplicant(String applicationId, String userId, String employeeCode, String status, String path, String fileanme, String optioanlfileanme) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS SENDBACK/REJECT FROM  APPROVER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);
			
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			if(!fileanme.equals("")){
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
				template.sendApplMailWithAttachment(attachment);
			}
			else if(!optioanlfileanme.equals("")){
				String[]attachment=new String[1];
				attachment[0]=path+optioanlfileanme;
				template.sendApplMailWithAttachment(attachment);
			}
			else{
				template.sendApplicationMail();
			}
			template.clearParameters();
			template.terminate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
		
public String viewApplicationFunction() {
	InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
	model.initiate(context, session);
	String searchStatus = request.getParameter("status");
	//System.out.println("status >>>>>>"+ searchStatus);
	//String typeOfList = model.setRespectiveListType(searchStatus, bean);
	//String typeOfList = bean.getListType();
	//bean.setListTypeDetailPage(typeOfList);
	String typeOfList = bean.getListType();
	String applCode=request.getParameter("applCode");
	try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
			bean.setDataPath(dataPath);
			
			String applicationID = request.getParameter("infoSysReqApprId");
			
			/*
			 * FOR SUPER USER
			 */
			if(applCode !=null &&applCode.length()>0){
				applicationID=applCode;
			}
			
			model.viewApplicationFunction(bean, applicationID, typeOfList);
			if (applicationID == null) {
				applicationID = bean.getInfoSysReqApprId();
			}
			Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.setEmployeeRole(bean);
			model.terminate();
		if(typeOfList.equals("pending")) {
			if (bean.getStatus().equals("F") || bean.getStatus().equals("Q")) {
				bean.setActivityAssignmentForwardEmpFlag(true);
				bean.setApproverCommentsFlag(true);
				bean.setItInfoSystemChangeGroupFlag(true);
				getNavigationPanel(10);
				bean.setEnableAll("N");
			} else if (bean.getStatus().equals("Z")) {
				bean.setActivityAssignmentForwardEmpFlag(true);
				bean.setForwardedEmployeeFlag(true);
				bean.setApproverCommentsFlag(true);
				getNavigationPanel(11);
				bean.setEnableAll("N");
			} else if (bean.getStatus().equals("Y")) {
				if(model.isItDeptApprover(bean.getUserEmpId())) {
					bean.setApproverCommentsFlag(true);
					bean.setItInfoSystemChangeGroupFlag(true);
					getNavigationPanel(12);
					bean.setEnableAll("N");
				} else {
					bean.setEnableAll("N");
					getNavigationPanel(2);
				}
			} else {
				bean.setApproverCommentsFlag(true);
				getNavigationPanel(5);
				bean.setEnableAll("N");
			}
		}else if(typeOfList.equals("approved")) {
			String applicationId = bean.getInfoSysReqApprId();
			String userId = bean.getUserEmpId();
			if (bean.getStatus().equals("A")) {
				if(model.isUserAMagaer(applicationId, userId)) {
					bean.setEnableAll("N");
					getNavigationPanel(2);
				} else {
					bean.setFeedbackSubmitFlag(true);
					getNavigationPanel(8);
					bean.setEnableAll("N");
				}
			} else if (bean.getStatus().equals("Z")) {
				if(model.isItDeptApprover(userId)) {
					bean.setActivityAssignmentForwardEmpFlag(true);
					bean.setForwardedEmployeeFlag(true);
					bean.setApproverCommentsFlag(true);
					getNavigationPanel(11);
					bean.setEnableAll("N");
				} else {
					bean.setEnableAll("N");
					getNavigationPanel(2);
				}
			} else if (bean.getStatus().equals("Y")) {
				if(model.isItDeptApprover(userId)) {
					bean.setApproverCommentsFlag(true);
					bean.setItInfoSystemChangeGroupFlag(true);
					getNavigationPanel(12);
					bean.setEnableAll("N");
				} else {
					bean.setEnableAll("N");
					getNavigationPanel(2);
				}
			} else {
					bean.setEnableAll("N");
					getNavigationPanel(2);
			}
		} else {
			if(bean.getStatus().equals("X")) {
				bean.setFeedbackSubmitFlag(true);
			}
			bean.setEnableAll("N");
			getNavigationPanel(2);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	if(applCode !=null &&applCode.length()>0) {
		String status = bean.getStatus();
		if(status.equals("D")|| status.equals("A")|| status.equals("B")|| status.equals("R")|| status.equals("C"))
		{
			bean.setEnableAll("N");
			getNavigationPanel(9);
		} else {
			bean.setApproverCommentsFlag(true);
			getNavigationPanel(7);
			bean.setEnableAll("N");
		}
	}
	return SUCCESS;
}
	
	
	public String backToList() throws Exception {
		InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
		model.initiate(context, session);
		String listType = model.checkNull(bean.getListType());
		if ("".equals(listType) || "pending".equals(listType)) {
			input();
		} else if ("approved".equals(listType)) {
			getApprovedList();
		} else if ("rejected".equals(listType)) {
			getRejectedList();
		} else if ("closed".equals(listType)) {
			getClosedTicketList();
		}
		
		return INPUT;
	}
	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public void viewUploadedFile() throws IOException{
		final String uploadFileName = request.getParameter("uploadFileName");
		final String[] extension = uploadFileName.replace(".","#").split("#");
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = bean.getDataPath();
		final String filePath = dataPath + uploadFileName;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = "jpg";
			final String applnTxt = "gif";
			final String applnGif = "txt";

			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg =  "jpg";
			final String mimeTypeTxt = "gif";
			final String mimeTypeGif = "txt";

			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename=\"" 	+ uploadFileName + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
			 
	}
	public String getClosedTicketList() throws Exception {		
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			//boolean isItDeptApprover = model.isItDeptApprover(userId);
			model.getClosedTicketList(bean, request);
			bean.setListType("closed");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	/**Method : f9ForwardToEmployee.
	 * Purpose : This method is used to select forward to employee.
	 * @return String
	 */
	public String f9ForwardToEmployee() {
		final String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME || ' ' ||HRMS_EMP_OFFC.EMP_MNAME || ' ' ||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID " +
					   " FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' AND HRMS_EMP_OFFC.EMP_ID NOT IN (" + bean.getUserEmpId() + ") ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME) ";

		final String[] headers = { "Employee Id", "Employee Name" };
		final String[] headerwidth = { "40", "60" };
		final String[] fieldNames = { "forwardToEmployeeToken", "forwardToEmployeeName", "forwardToEmployeeId" };
		final int[] columnIndex = { 0, 1, 2};
		final String submitFlage = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerwidth, fieldNames, columnIndex, submitFlage, submitToMethod);
		return "f9page";
	}
	
	public String forwardApplication() {
		final InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
		model.initiate(context, session);
		
		final String applicationId = bean.getInfoSysReqApprId();
		final String comments = bean.getAssignmentComments();
		final String userId = bean.getUserEmpId();
		final String approverCodeToUpdate = bean.getForwardToEmployeeId();
		final String employeeCode = bean.getInitiatorCode();
		final String path = bean.getDataPath();
		final String fileanme = bean.getUploadFileName();
		final String optioanlfileanme = bean.getUploadOptionalFileName();
		final String status = "Z";
		final String selectForwardToRole = bean.getSelectForwardToRole();
		final String uploadFileNameApprover = bean.getUploadFileNameApprover();
		boolean result = model.updateApplicationStatus(bean, applicationId, comments, userId, approverCodeToUpdate, status, selectForwardToRole, uploadFileNameApprover);
		if (result) {
			this.addActionMessage("Application forwarded successfully");
			sendForwardedMailToApplicant(applicationId, userId, employeeCode, path, fileanme, optioanlfileanme);
			sendMailToForwardedEmployee(applicationId, userId, employeeCode, path, fileanme, optioanlfileanme);
		} else {
			this.addActionMessage("Unable to forward this application");
		}
		model.terminate();
		input();
		return INPUT;
	}
	

	/**Method : closeActivity.
	 * Purpose : This method is used to close activity assigned by IT group manager 
	 * @return String
	 */
	public String closeActivity() {
		final InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
		model.initiate(context, session);
		final String applicationId = bean.getInfoSysReqApprId();
		final String comments = bean.getApproverComments();
		final String userId = bean.getUserEmpId();
		final String approverCodeToUpdate = bean.getUserEmpId();
		final String employeeCode = bean.getInitiatorCode();
		final String path = bean.getDataPath();
		final String fileanme = bean.getUploadFileName();
		final String optionalfileanme = bean.getUploadOptionalFileName();
		final String status = "Y";
		final String selectForwardToRole = bean.getSelectForwardToRole();
		final String uploadFileNameApprover = bean.getUploadFileNameApprover();
		boolean result = model.updateApplicationStatus(bean, applicationId, comments, userId, approverCodeToUpdate, status, selectForwardToRole, uploadFileNameApprover);
		if (result) {
			this.addActionMessage("Activity has been closed successfully");
			sendCloseActivityMailToItGroup(applicationId, userId);
		} else {
			this.addActionMessage("Unable to forward this application");
		}
		
		model.terminate();
		input();
		return INPUT;
	}
	
	/**Method : sendCloseActivityMailToItGroup.
	 * Purpose : Send close activity mail to IT Group
	 * @param applicationId : Application ID
	 * @param userId : Current login Employee ID
	 */
	private void sendCloseActivityMailToItGroup(String applicationId,
			String userId) {
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE ACTION MAIL TO IT GROUP");
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userId);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, "0");
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);
				
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);
				
			template.configMailAlert();
		 
			Object infoSysChngGroupID[][] = model.getSqlModel().getSingleResult(" SELECT NVL(APP_EMAIL_ID,'') AS INFO_CHNG_GROUP_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
											" WHERE APP_SETTINGS_TYPE = 'N' AND APP_EMAIL_ID IS NOT NULL ");
			if(infoSysChngGroupID != null && infoSysChngGroupID.length > 0) {
				template.sendApplicationMailToGroups(infoSysChngGroupID);
			}
			template.clearParameters();
			template.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : sendMailToForwardedEmployee.
	 * Purpose : This method is used to send mail to forwarded employee
	 * @param applicationId : Information System Change application ID
	 * @param userId : Current user employee ID
	 * @param employeeCode : Initiator ID
	 * @param path : Attached file path
	 * @param fileanme : Attached file name
	 * @param optionalfileanme : Optional File name
	 */
	private void sendMailToForwardedEmployee(String applicationId,
			String userId, String employeeCode, String path, String fileanme,
			String optionalfileanme) {
		try {
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST FROM GROUP TO FORWARDED EMP");
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, bean.getForwardToEmployeeId());
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);
				
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);
				
			template.configMailAlert();
			 
			if(!fileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
				template.sendApplMailWithAttachment(attachment);
			} else if(!optionalfileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+optionalfileanme;
				template.sendApplMailWithAttachment(attachment);
			} else {
				template.sendApplicationMail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**Method : sendForwardedMail.
	 * Purpose : This method is used to send mail to applicant/initiator & manager, and group ID if current user is not in IT Group 
	 * @param applicationId : Information System Change application ID
	 * @param userId : Current user employee ID
	 * @param employeeCode : Initiator ID
	 * @param path : Attached file path
	 * @param fileanme : Attached file name
	 * @param optionalfileanme : Optional File name
	 */
	private void sendForwardedMailToApplicant(String applicationId, String userId,
			String employeeCode, String path, String fileanme,
			String optionalfileanme) {
		try {
			//BEGINS -- Send mail to applicant and Cc. to manager and group ID if current user is not in IT Group 
			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS FROM APPROVER TO APPLICANT");
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, employeeCode);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);
				
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);
				
			template.configMailAlert();
			 
			if(!fileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
				template.sendApplMailWithAttachment(attachment);
			} else if(!optionalfileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+optionalfileanme;
				template.sendApplMailWithAttachment(attachment);
			} else {
				template.sendApplicationMail();
			}
			 
			
			//BEGINS -- Cc. with attachemtnt if any, mail to manager
		/*
			String apprQuery = " SELECT DISTINCT INF_CNG_APPR_CODE FROM HRMS_D1_INF_CNG_PATH WHERE INF_CNG_CODE =  "+ applicationId;
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			
			String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 0) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}
		*/	
			String[] approverData = new String[1];
			if(!bean.getApproverCode().equals("")) {
				approverData[0] = bean.getApproverCode();
			} else {
				approverData[0] = bean.getFirstApproverCode();
			}
			
			if(!fileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
				template.sendApplMailWithAttachmentToKeepInf(approverData,attachment);
			} else if(!optionalfileanme.equals("")) {
				String[]attachment=new String[1];
				attachment[0]=path+optionalfileanme;
				template.sendApplMailWithAttachmentToKeepInf(approverData,attachment);
			} else {
				template.sendApplMailWithAttachmentToKeepInf(approverData,null);
			}
			//ENDS -- Cc. with attachemtnt if any, mail to manager
		
			
			//BEGINS -- Send mail to IT Group System Change ID if current user is not in that Group
			boolean isItDeptApprover = model.isItDeptApprover(userId);
			if(!isItDeptApprover) {
				Object infoSysChngGroupID[][] = model.getSqlModel().getSingleResult(" SELECT NVL(APP_EMAIL_ID,'') AS INFO_CHNG_GROUP_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
  												" WHERE APP_SETTINGS_TYPE = 'N' AND APP_EMAIL_ID IS NOT NULL ");
				if(infoSysChngGroupID != null && infoSysChngGroupID.length > 0) {
					template.sendApplicationMailToGroups(infoSysChngGroupID);
				}
			}
			//ENDS -- Send mail to IT Group System Change ID if current user is not in that Group
			
			
			template.clearParameters();
			template.terminate();
		//BEGINS -- Send mail to applicant and Cc. to manager and group ID if current user is not in IT Group	
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String f9SearchRecords() {
		InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
		model.initiate(context, session);
		String userId = bean.getUserEmpId();
		boolean isItDeptApprover = model.isItDeptApprover(userId);
		
		String searchListType= request.getParameter("searchListType"); 
		
		String query = " SELECT DISTINCT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, "+
  		 			   " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+  
  		 			   " NVL(CNG_TITLE,''), DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen', 'X','Feedback Given'), "+
  		 			   " HRMS_D1_INF_CNG_REQ.INF_CNG_CODE "+
  		 			   " FROM HRMS_D1_INF_CNG_REQ "+
  		 			   " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) ";
		if(searchListType.equals("pending")) {
			if(isItDeptApprover) {
				  query += " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('F','Q','Y') OR (HRMS_D1_INF_CNG_REQ.APPL_STATUS='P' AND APPROVER_CODE = " + userId +")";
			} else {
				  query += " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  IN ('P','Z') AND HRMS_D1_INF_CNG_REQ.APPROVER_CODE = " + userId ;
			}
		}
		
		if(searchListType.equals("approved")) {
			if(isItDeptApprover) {
				  query += " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN( 'A', 'Z', 'Y' ) ";
			} else {
				  query += " INNER JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
				  		   " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('F', 'A', 'Y', 'Z') AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE =  " + userId ;
			}
		}
		
		if(searchListType.equals("rejected")) {
			if(isItDeptApprover) {
				  query += " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'R'";
			} else {
				  query += "  WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS = 'R' AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE = " + userId ;
			}
		}
		
		if(searchListType.equals("closed")) {
			query += " INNER JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
					"WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('C','X') AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE = " + userId ;
		}
		
		
		/*if(isItDeptApprover) {
			  query += " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('A', 'F', 'Q', 'Z', 'Y', 'R', 'X') OR (HRMS_D1_INF_CNG_REQ.APPL_STATUS='P' AND APPROVER_CODE = "+bean.getUserEmpId() +")";
		} else {
			  query +=  " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  IN ('A', 'Y', 'P', 'Z', 'R', 'F', 'X') AND HRMS_D1_INF_CNG_REQ.APPROVER_CODE = " + userId;
		}*/
		
		query += " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC "; 

		String[] fieldNames = { "trackingNo", "initiatorName", "changeTitle", "status", "infoSysReqApprId" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		
		String[] headers = { "Tracking No.", "Employee", "Change Title", "status" };

		String[] headerwidth = { "20", "30", "30", "20" };

		String submitFlage = "true";

		String submitToMethod = "InformationSystemChangeRequestAppr_viewApplicationFunction.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
}
