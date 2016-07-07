package org.struts.action.D1;

import java.util.ArrayList;

import org.paradyne.bean.D1.ClassEnrollmentApprover;
import org.paradyne.bean.D1.PersonalRequisitionApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.PersonalRequisitionApproverModel;
import org.paradyne.model.D1.PersonalRequisitionModel;
import org.struts.lib.ParaActionSupport;


public class PersonalRequisitionApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonalRequisitionApproverAction.class);

	PersonalRequisitionApprover bean;

	public void prepare_local() throws Exception {
		bean = new PersonalRequisitionApprover();
		bean.setMenuCode(2031);
	}

	public Object getModel() {
		return bean;
	}

	public String input() {
		try {
			PersonalRequisitionApproverModel model = new PersonalRequisitionApproverModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isDirectorApprover = model.isUserADirectorApprover(userId);
			if (isDirectorApprover) {
				//model.getPendingList(bean, request);
			} else {
				model.getPendingList(bean, request, userId);
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
			PersonalRequisitionApproverModel model = new PersonalRequisitionApproverModel();
			model.initiate(context, session);

			String userId = bean.getUserEmpId();
			boolean isDirectorApprover = model.isUserADirectorApprover(userId);
			if (isDirectorApprover) {
			//	model.getApprovedList(bean, request);
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
			PersonalRequisitionApproverModel model = new PersonalRequisitionApproverModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isDirectorApprover = model.isUserADirectorApprover(userId);
			if (isDirectorApprover) {
				// model.getRejectedList(bean, request);
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

	public String approveApplication() {
		try {
			String applicationId = bean.getRequestID();
			// String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String nextAppr = bean.getApproverCode();
			String forwardedTo = bean.getForwardedForApprovar();
			String status = bean.getPersReqStatus();
			// int level = Integer.parseInt(bean.getLevel());

			PersonalRequisitionApproverModel model = new PersonalRequisitionApproverModel();

			model.initiate(context, session);

			String query = " select FORWARDED_TO from HRMS_D1_PERSONAL_REQUISITION where PERSONAL_REQ_ID="
					+ applicationId;
			Object forwardedObj[][] = model.getSqlModel()
					.getSingleResult(query);

			boolean result = false;
			if (forwardedObj != null && forwardedObj.length > 0) {
				if (String.valueOf(forwardedObj[0][0]).equals("Pr")) {
					 
					getNavigationPanel(6);
					result = model.approve(applicationId, userId, nextAppr,
							bean.getApproverComments(), "A", forwardedTo);
					sendFinalApplicantToApprovalMail(applicationId, userId);
					addActionMessage("Application approved successfully");
					
					if(status.equals("A") && !bean.getContractorEmailAddress().equals("")){
						String email=bean.getContractorEmailAddress().trim();
						sendFinalMail(applicationId, userId, nextAppr, status,email);
					}
				} 
				if (status.equals("S")) {
					//getNavigationPanel(6);
					result = model.approve(applicationId, userId, nextAppr,
							bean.getApproverComments(), "S", forwardedTo);
					sendFinalApplicantToApprovalMail(applicationId, userId);
					addActionMessage("Application authorized sign off successfully");
					
					if(status.equals("A") && !bean.getContractorEmailAddress().equals("")){
						String email=bean.getContractorEmailAddress().trim();
						sendFinalMail(applicationId, userId, nextAppr, status,email);
					}
				} else if (status.equals("F")) {
					result = model.approve(applicationId, userId, nextAppr,
							bean.getApproverComments(), status, forwardedTo);
					sendApplicantToApprovalMail(applicationId, userId);
					addActionMessage("Application forwarded successfully");
				}
			}

			if (result) {
				//addActionMessage("Application forwarded successfully");
			} else {
				//addActionMessage("Application not forwarded successfully");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return input();
	}

	private void sendFinalMail(String applicationId, String userId,
			String nextAppr, String status, String email) {
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS TO AGENCY");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, email.trim());
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);
			
		
			
		//	String[] empData = null;
			
			templateApp.configMailAlert();
			

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void sendApplicantToApprovalMail(String applicationId,
			String userID) {
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS FROM MANAGER TO NEXT APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, bean.getApproverCode());
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userID);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, applicationId);
			
			String query = "SELECT DISTINCT PERS_APPROVER_CODE FROM HRMS_D1_PERSREQ_PATH WHERE PERS_APPROVER_CODE not in("+bean.getUserEmpId()+") and  PERS_REQ_ID = "+applicationId;
			Object[][] queryObj = model.getSqlModel().getSingleResult(query);
			String[] empData = null;
			if(queryObj!=null && queryObj.length>0)
			{
					empData = new String[queryObj.length];
					for(int i = 0; i < queryObj.length; i++) {
						empData[i] = String.valueOf(queryObj[i][0]);
					}
			}
			
			templateApp.configMailAlert();
			templateApp.sendApplicationMailToKeepInfo(empData);
			

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			// MAIL FROM Approver To  Applicant 
			model.initiate(context, session);
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS FROM MANAGER TO APPLICANT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, bean.getHiringManagerCode());
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userID);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, applicationId);
			
			String[] empData = null;
			
		/*	
			if(data != null && data.length > 1) {
				System.out.println("data.length : "+data.length);
				empData = new String[data.length - 1];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
					System.out.println("empData[i] = " + empData[i]);
				}
			}*/
			
			
			
			templateApp.configMailAlert();
			

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void sendFinalApplicantToApprovalMail(String applicationId,
			String userID) {
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			/*String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
				+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);*/
			
			String initId = bean.getInitiatorCode();
			
			
			String apprQuery = " select DISTINCT PERS_APPROVER_CODE from HRMS_D1_PERSREQ_PATH WHERE PERS_APPROVER_CODE not in("+bean.getUserEmpId()+") AND PERS_REQ_ID =" + bean.getRequestID();
			Object apprObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			
			String requestQuery = " select NVL(HIRING_MGNR_ID,0) from HRMS_D1_PERSONAL_REQUISITION where PERSONAL_REQ_ID = "+ applicationId;
			Object initiatorData[][] = model.getSqlModel().getSingleResult(requestQuery);
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS APRROVE/REJECT/SENDBACK FROM PRESIDENT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, initId);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userID);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, userID);
			
			EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp9 = templateApp.getTemplateQuery(9);
			templateQueryApp9.setParameter(1, applicationId);
			
			/*String[] empData = new String[data.length];
			
			if(data != null && data.length > 1) {
				System.out.println("data.length : "+data.length);
				empData = new String[data.length - 1];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
					System.out.println("empData[i] = " + empData[i]);
				}
			}*/
			
			
			String[] apprData = new String[apprObj.length];
			
			if(apprObj != null && apprObj.length > 1) {
				apprData = new String[apprObj.length];
				for(int i = 0; i < apprData.length; i++) {
					apprData[i] = String.valueOf(apprObj[i][0]);
				}
			}
			
			
			String[] iniData = null;
			
			if(initiatorData != null && initiatorData.length > 0) {
				iniData = new String[initiatorData.length];
				for(int i = 0; i < iniData.length; i++) {
					iniData[i] = String.valueOf(initiatorData[i][0]);
				}
			}
			
			templateApp.configMailAlert();
			/*try {
				if(empData !=null && empData.length>0){
					templateApp.sendApplicationMailToKeepInfo(empData);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			
			
		//	String keepData[]=new String[2];
		//	keepData[0]=String.valueOf(data[0][0]);
		//	keepData[1]=String.valueOf(initiatorData[0][0]);
			templateApp.sendApplicationMailToKeepInfo(apprData);
			
			
			//templateApp.sendApplMailWithAttachmentToKeepInf(apprData,null);

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void populateApproverComments(Object[][] apprCommentListObj) {
		ArrayList approverCommentList = new ArrayList<Object>(
				apprCommentListObj.length);

		for (int i = 0; i < apprCommentListObj.length; i++) {
			ClassEnrollmentApprover innerBean = new ClassEnrollmentApprover();
			innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
			innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(innerBean);
		}
		bean.setApproverCommentList(approverCommentList);
	}


	  public String rejectApplication()
	  { 
		  try { 
			  String applicationId =  bean.getRequestID(); 
			  String approverComments =  bean.getApproverComments();
			  String userId = bean.getUserEmpId();
	  
	  String status = bean.getPersReqStatus();
	  String employeeCode =  bean.getEmployeeCode(); 
	  PersonalRequisitionApproverModel model = new
	  PersonalRequisitionApproverModel(); 
	  model.initiate(context, session);
	  model.rejectSendBack(applicationId, approverComments, userId,status);
	  
	 // Object[][] apprCommentListObj =  model.getApproverCommentList(applicationId);
	  //populateApproverComments(apprCommentListObj);
	  sendFinalApplicantToApprovalMail(applicationId, userId);
		  model.terminate();
		  addActionMessage("Application rejected  successfully.");
		  } catch (Exception e) {
				e.printStackTrace();
			}
	  //sendRejectSenBackMailToApplicant(persDataChangeId, userId,
	input(); 
	return INPUT;
	}
	  
	  public String sendBackApplication()
	  { 
		  try { 
			  String applicationId =  bean.getRequestID(); 
			  String approverComments =  bean.getApproverComments();
			  String userId = bean.getUserEmpId();
	  
	  String status = bean.getPersReqStatus();
	  String employeeCode =  bean.getEmployeeCode(); 
	  PersonalRequisitionApproverModel model = new
	  PersonalRequisitionApproverModel(); 
	  model.initiate(context, session);
	  model.rejectSendBack(applicationId, approverComments, userId,status);
	  
	 // Object[][] apprCommentListObj =  model.getApproverCommentList(applicationId);
	  //populateApproverComments(apprCommentListObj);
	  sendFinalApplicantToApprovalMail(applicationId, userId);
		  model.terminate();
		  addActionMessage("Application sent back  successfully.");
		  } catch (Exception e) {
				e.printStackTrace();
			}
	  //sendRejectSenBackMailToApplicant(persDataChangeId, userId,
	input(); 
	return INPUT;
	}
	 
	public String viewApplicationFunction() {
		String typeOfList = bean.getListType();
		bean.setListTypeDetailPage(typeOfList);
		String applCode=request.getParameter("applCode");
		try {
			String applicationID = request.getParameter("PersonalRequisitionApprID");
			PersonalRequisitionApproverModel model = new PersonalRequisitionApproverModel();
			model.initiate(context, session);
			String query = " select FORWARDED_TO from HRMS_D1_PERSONAL_REQUISITION where FORWARDED_TO='Pr' and PERSONAL_REQ_ID="
					+ applicationID;
			Object forwardedObj[][] = model.getSqlModel()
					.getSingleResult(query);

			/*
			 * FOR SUPER USER
			 */
			
			if(applCode !=null &&applCode.length()>0){
				applicationID=applCode;
			}
			
			model.viewApplicationFunction(bean, applicationID, typeOfList);

			// model.viewApplicationFunction(bean, applicationID, typeOfList);
		//	Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
			// populateApproverComments(apprCommentListObj);
			model.terminate();

			if (typeOfList.equals("pending")) {
				bean.setEnableAll("N");
				getNavigationPanel(5);
				bean.setCommentFlag("true");
				bean.setForwardFlag("true");
			} else if (typeOfList.equals("approved")) {
				
				bean.setEnableAll("N");
				getNavigationPanel(2);
				bean.setCommentFlag("false");
			}
			

			else {
				bean.setEnableAll("N");
				getNavigationPanel(2);
			}
			if (forwardedObj != null && forwardedObj.length > 0) {
				if (String.valueOf(forwardedObj[0][0]).equals("Pr") && typeOfList.equals("pending")) {
					getNavigationPanel(6);
					bean.setForwardFlag("false");
				}
				else {
					
					getNavigationPanel(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(applCode !=null &&applCode.length()>0){
			String status = bean.getPersReqStatus();
			if(status.equals("D")||status.equals("A")||status.equals("B")||status.equals("R")) {
				getNavigationPanel(8);
				bean.setEnableAll("N");
			} else {
				bean.setApproverCommentsFlag(true);
				getNavigationPanel(7);
				bean.setEnableAll("N");
			}
		}
		return SUCCESS;
	}

	public String backToList() throws Exception {
		String listType = bean.getListType();
		if(listType.equals("pending") || listType.equals("")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else {
			getRejectedList();
		}
		return INPUT;
	}

	public PersonalRequisitionApprover getBean() {
		return bean;
	}

	public void setBean(PersonalRequisitionApprover bean) {
		this.bean = bean;
	}

	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";

		if(!bean.getReqReplacingCode().equals("")) {
			 str = bean.getReqReplacingCode();
		}

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";

		//query += getprofileQuery(bean);
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + "," + bean.getUserEmpId() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "approverToken", "approverName", "approverCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	

}
