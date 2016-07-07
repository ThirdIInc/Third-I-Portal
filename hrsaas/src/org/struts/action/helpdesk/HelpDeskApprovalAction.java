package org.struts.action.helpdesk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.helpdesk.HelpDeskAppModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * @date 
 * HelpDeskApprovalAction class to change the status 
 * of the application from pending to approved or rejected and also to forward the 
 * application to the next approver
 */

public class HelpDeskApprovalAction extends ParaActionSupport 
{
	HelpDeskApproval helpBean;
	static org.apache.log4j.Logger logger =org.apache.log4j.Logger.getLogger(HelpDeskApprovalAction.class);
	
	public void prepare_local() throws Exception 
	{
		helpBean = new HelpDeskApproval();
		helpBean.setMenuCode(383);
	}

	public Object getModel() {
		return helpBean;
	}

	public HelpDeskApproval getHelpBean() {
		return helpBean;
	}

	public void setHelpBean(HelpDeskApproval helpBean) {
		this.helpBean = helpBean;
	}
	
	public String input() {
		try {
			
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			String listtype= request.getParameter("listType");
			if(listtype!=null && listtype.equals("closed")){
				model.getClosedList(helpBean, request);
				helpBean.setListType("closed");
				
			}else if(listtype!=null && listtype.equals("pending")){
				model.getPendingRequests(helpBean, request);
				helpBean.setListType("pending");
			}else{
				model.getPendingRequests(helpBean, request);
				helpBean.setListType("pending");
			}
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (helpBean.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpBean.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return "input";
		}
	}
	
	public String getClosedList() throws Exception {
		 
		try {
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			model.getClosedList(helpBean, request);
			helpBean.setListType("closed");
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in getClosedList method : " + e);
		}
		return INPUT;
	}
	
	public String retrieveDetails(){
		String source = request.getParameter("src");
		System.out.println("source-------------->>>>>>>>>>>>>>>" + source);
		helpBean.setSource(source);
		
		boolean commentResult = false;
		try {
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			String reqAppCode = request.getParameter("reqAppCode");
			String mgrStatus = request.getParameter("mgrStatus");
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			helpBean.setAppDate(sysDate);
			model.getEmployeeDetails(reqAppCode, helpBean, request);
			//commentResult = model.getPreviousApproverComments(helpBean);
			model.setActivityLogDetails(helpBean);
			if(commentResult){
				helpBean.setApproverCommentListFlag(true);
			}else{
				helpBean.setApproverCommentListFlag(false);
			}
		if(mgrStatus.equals("P")){
			helpBean.setApproverCommentFlag(true);
			getNavigationPanel(2);
		}else if(mgrStatus.equals("A")){
			helpBean.setIsReqApp("false");
			helpBean.setApproverCommentFlag(false);
			getNavigationPanel(3);
		}else if(mgrStatus.equals("R")){
			helpBean.setIsReqApp("false");
			helpBean.setApproverCommentFlag(false);
			getNavigationPanel(3);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return SUCCESS;
	}
	
	public String viewUploadedFile() {
		try {
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = request.getParameter("templateName");
				logger.info("fileName--->>>" + fileName);
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				logger.info("extext--->>>" + ext);
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = getText("data_path") + "/HelpDesk/" + poolName
					+ "/"+fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/" + mimeType
							+ "");
				}

				response.setHeader("Content-disposition", "attachment;filename=\""
						+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while

			} catch (FileNotFoundException e) {

				logger.error("-----in file not found catch", e);
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				logger.info("in finally for closing");
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
		return null;
	}
	
	public String approveRejSendBackRequestApplication() {
		try {
			setLevelForApplication();
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			String status=helpBean.getApproveRejectStatus();
			int level=helpBean.getLevel();
			String initiatorId=helpBean.getEmpId();
			String requestForEmpId = helpBean.getRequestForEmpId();
			String isManagerAppr=helpBean.getIsManagerApproval(); 
			
			String deptId=helpBean.getReqDeptCode(); 
			String reqTypeId=helpBean.getReqTypeCode();
			String requestId=helpBean.getRequestId();
			String approverComments=helpBean.getApproverComments();
			String currentUser=helpBean.getUserEmpId();
			String applicationStatus = model.approveRejectApplication( request,status,level,initiatorId,requestForEmpId,isManagerAppr,deptId,
					reqTypeId,requestId,approverComments,currentUser);
			
			if (applicationStatus.equals("approved")) {
				addActionMessage("Request approved.");
				
			} else if (applicationStatus.equals("sendback")) {
				addActionMessage("Request sent back.");
			} else {
				
				addActionMessage("Request rejected.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (helpBean.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpBean.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return input();
		}
	
	}
	
	public void setLevelForApplication() {
		try {
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			String query = " SELECT REQUEST_APPL_LEVEL FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="
					+ helpBean.getRequestId();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				helpBean.setLevel(Integer.parseInt(String.valueOf(levelObj[0][0])));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String back() {
		input();
		helpBean.setEnableAll("Y");
		return INPUT;
	}
	
	public String onlineApproveRejectSendBack(HttpServletRequest request,
			String appStatus, String level, String initiatorId,String requestForEmpId,
			String isManagerAppr, String deptId, String reqTypeId,
			String requestId, String approverComments, String currentUser, String approverId) {
		HelpDeskAppModel model = new HelpDeskAppModel();
		model.initiate(context, session);
		String result = "";
		String res = "";
		try {
			String query = " SELECT REQUEST_PENDING_WITH,REQUEST_APPL_STATUS FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="
					+ requestId;
			Object approverIdObj[][] = model.getSqlModel().getSingleResult(query);
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("O")) {
					
				res = model.approveRejectApplication(request, appStatus, Integer
						.parseInt(level), initiatorId,requestForEmpId, isManagerAppr, deptId,
						reqTypeId, requestId, approverComments, approverId);
				logger.info("res....." + res);
				if (res.equals("approved")) {
					result = "Helpdesk request has been approved.";
					
				} else if (res.equals("rejected")) {
					result = "Helpdesk request has been rejected.";
				} else if (res.equals("sendback")) {
					result = "Helpdesk request has been sent back to applicant.";
				} else {
					result = "Error Occured.";
				}
			}else{
				result = "Helpdesk request has already been processed.";
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return result;
	}
	
	public void sendForwardNotificationMail(HttpServletRequest request, String requestId, String initiatorId, String managerId, String isManagerAppr, String deptId, String reqTypeId, String userEmpId, int level){
		try {
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);
			
			logger.info("############# REQUEST ID ##################"+requestId);
			logger.info("############# INITIATOR ID ##################"+initiatorId);
			logger.info("############# MANAGER ID ##################"+managerId);
			
			
				/* 2.	Request Submit Notification to Request Owner */
				
				EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
				templateForRequestApproval.initiate(context, session);
				templateForRequestApproval.setEmailTemplate("Helpdesk Request Forward Notification to Owner by Approver");
				templateForRequestApproval.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForApproval1.setParameter(1, userEmpId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval
							.getTemplateQuery(2);// To EMAIL
					templateQueryForApproval2.setParameter(1, managerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval
					.getTemplateQuery(3);
					templateQueryForApproval3.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval
					.getTemplateQuery(4);
					templateQueryForApproval4.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval5 = templateForRequestApproval
					.getTemplateQuery(5);
					templateQueryForApproval5.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval6 = templateForRequestApproval
					.getTemplateQuery(6);
					templateQueryForApproval6.setParameter(1, userEmpId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					templateForRequestApproval.configMailAlert();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR " 
					+ " WHERE DEPT_CODE = "+deptId+" AND REQ_TYPE_CODE = "
					+ reqTypeId+" AND BRANCH_CODE = ( "
					+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+initiatorId
					+ ") ";
				Object[][] managerIdObj = model.getSqlModel().getSingleResult(ownerQuery);
				
				String reqManagerId = "";
				if(managerIdObj != null && managerIdObj.length > 0 ){
					reqManagerId = String.valueOf(managerIdObj[0][0]);
				}
				if(!(managerId.equals("0")) && !(reqManagerId.equals(managerId))){
				// Add approval link -pass parameters to the link
				//String level = "1";
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "HelpdeskSubmission";
				
				link_param[0] = applicationType + "#" + "A" + "#"
						+ level + "#" + initiatorId + "#" + isManagerAppr + "#"
						+ deptId + "#" + reqTypeId+ "#" + requestId + "#" + "...#" + userEmpId + "#" + managerId;
				link_param[1] = applicationType + "#" + "R" + "#"
				+ level + "#" + initiatorId + "#" + isManagerAppr + "#"
				+ deptId + "#" + reqTypeId+ "#" + requestId + "#" + "...#"+userEmpId + "#" + managerId; 
				link_param[2] = applicationType + "#" + "B" + "#"
				+ level + "#" + initiatorId + "#" + isManagerAppr + "#"
				+ deptId + "#" + reqTypeId+ "#" + requestId + "#" + "...#"+userEmpId + "#" + managerId;
				
				
				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				
				templateForRequestApproval.addOnlineLink(request, link_param, link_label);
			}
				templateForRequestApproval.sendApplicationMail();
				templateForRequestApproval.clearParameters();
				templateForRequestApproval.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendForwardNotificationToApplicant(String status, String requestId, String initiatorId, 
			 String isManagerAppr, String deptId, String reqTypeId, String userEmpId){
		System.out.println("++++++++++++++++"+initiatorId);
		System.out.println("++++++++++++++++"+requestId);
		System.out.println("++++++++++++++++"+userEmpId);
		
		EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
		templateForRequestApproval.initiate(context, session);
		templateForRequestApproval.setEmailTemplate("HELP DESK - APPROVER TO APPLICANT");
		templateForRequestApproval.getTemplateQueries();
		
		try {
			System.out.println("===============1============"+userEmpId);
			EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval.getTemplateQuery(1);// FROM EMAIL
			templateQueryForApproval1.setParameter(1, userEmpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("===============2============"+initiatorId);
			EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, initiatorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("===============3============"+requestId);
			EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval.getTemplateQuery(3);
			templateQueryForApproval3.setParameter(1, requestId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("===============4============"+requestId);
			EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval.getTemplateQuery(4);
			templateQueryForApproval4.setParameter(1,userEmpId );
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("===============5============"+requestId);
			EmailTemplateQuery templateQueryForApproval5 = templateForRequestApproval.getTemplateQuery(5);
			templateQueryForApproval5.setParameter(1,  requestId );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("===============6============"+requestId);
			EmailTemplateQuery templateQueryForApproval6 = templateForRequestApproval.getTemplateQuery(6);
			templateQueryForApproval6.setParameter(1,  requestId );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		templateForRequestApproval.configMailAlert();
		templateForRequestApproval.sendApplicationMail();
		templateForRequestApproval.clearParameters();
		templateForRequestApproval.terminate();
		
		
		
	}


}
