package org.paradyne.model.recruitment;

import java.util.ArrayList;

import org.paradyne.bean.Recruitment.RequisitionApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pradeep
 * @date Jan 03, 2009
 * @description RequisitionApprovalModel serves as model for Manpower
 *              Requisition Approval form
 */

public class RequisitionApprovalModel extends ModelBase {
	RequisitionApproval reqApproval = new RequisitionApproval();
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * Added on date:14-10-2009 by Pradeep following function is called to send
	 * the mail if there is another approver
	 * 
	 * @param bean
	 * @param reqsCode
	 * @param nextApproverCode
	 */

	public void sendApproverToNextApprover(RequisitionApproval bean,
			String reqsCode, Object[][] empFlow, HttpServletRequest request, String applicantId) {
		try {
			String nextApproverCode = String.valueOf(empFlow[0][0]);
			String nextLevel = String.valueOf(empFlow[0][2]);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("MANPOWER REQ-APPROVER TO APPROVER");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, bean.getUserEmpId());

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, nextApproverCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, nextApproverCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, "" + bean.getUserEmpId());

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, "" + reqsCode);

			template.configMailAlert();
			
			/*String[] link_parameter_new = new String[1];
			String[] link_labels_new = new String[1];
			String applicationTypeNameNew = "Recruitment";
			 
			link_parameter_new[0] = applicationTypeNameNew + "#" + nextApproverCode + "#applicationDtls#";
		 
			String link1 = "/recruit/EmployeeRequi_viewOnlineReqDetailsFromApproval.action?reqCode="
							+ reqsCode.trim()
							+ "$formAction=RequisitionApproval_showApplicationList.action$flag=true$level=1";
		 
			link_parameter_new[0] += link1;
			link_labels_new[0] = "Click here for Online Approval";
			template.addOnlineLink(request, link_parameter_new, link_labels_new);*/
			
			 
			String applnID = reqsCode;
			String applicant = applicantId;
			String approver = nextApproverCode;
			String level = nextLevel;
			String[] link_parameter = new String[4];
			String[] link_labels = new String[4];
			String applicationType = "Recruitment";
			link_parameter[0] = applicationType + "#" + applicant + "#"
									+ applnID + "#" + "A" + "#" + "..." + "#" + approver
									+ "#" + level;
			link_parameter[1] = applicationType + "#" + applicant + "#"
									+ applnID + "#" + "R" + "#" + "..." + "#" + approver
									+ "#" + level;
			link_parameter[2] = applicationType + "#" + applicant + "#"
						+ applnID + "#" + "S" + "#" + "..." + "#" + approver
						+ "#" + level;
			link_parameter[3] = applicationType + "#" + applicant + "#"
						+ applnID + "#" + "H" + "#" + "..." + "#" + approver
						+ "#" + level;
			link_labels[0] = "Approve";
			link_labels[1] = "Reject";
			link_labels[2] = "Send Back";
			link_labels[3] = "On Hold";
			template.addOnlineLink(request, link_parameter, link_labels);
			 
			String link = "/recruit/EmployeeRequi_viewReqDetailsFromApproval.action";
			String linkParam = "reqCode=" + reqsCode + "&formAction=RequisitionApproval_showApplicationList.action&statusKey=P&flag=true&level="+nextLevel;

			template.sendProcessManagerAlert(nextApproverCode, "Resource Requisition", "A", reqsCode, nextLevel,
										     linkParam, link, "Pending", applicantId, "0", "", "", bean.getUserEmpId());
			
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Added on Date:14-10-2009 by Pradeep following function is called to send
	 * the mail from the approver to the applicant when the requisition is
	 * approved or rejected or on-hold.
	 * 
	 * @param bean
	 * @param reqsCode
	 */
	public void sendMail(RequisitionApproval bean, String reqsCode,
			String status, String finalApproverResult) {
		try {
			removeCompetencyProcessManagerAlert(String.valueOf(reqsCode));
			String applyByQuery = "SELECT HRMS_REC_REQS_HDR.REQS_APPLIED_BY, HRMS_REC_REQS_HDR.REQS_ALTER_APPROVER, HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ reqsCode;
			Object[][] applyBy = getSqlModel().getSingleResult(applyByQuery);

			String KeepInformId = getKeepInform(reqsCode);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("Manpower Requisition Approved/Reject/Send Back/On hold to applicant");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, bean.getUserEmpId());

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, String.valueOf(applyBy[0][0]));

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, bean.getUserEmpId());
			templateQuery4.setParameter(2, reqsCode);

			templateQuery4.setParameter(3, status);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, reqsCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, reqsCode);

			template.configMailAlert();
			
			String link = "/recruit/EmployeeRequi_callForEdit.action";
			String linkParam = "requisitionCode=" + reqsCode + "&applStatus="+status;
	 
			String actualStataus="";
			if (status.equals("A")) {
				actualStataus = "Approved";
			}
			if (status.equals("R")) {
				actualStataus = "Rejected";
			}
			if (status.equals("H")) {
				actualStataus = "On hold";
				String approverLink = "/recruit/EmployeeRequi_viewReqDetailsFromApproval.action";
				String approverLinkParam = "reqCode=" + reqsCode + "&formAction=RequisitionApproval_showApplicationList.action&statusKey=H&flag=true&level=1";
				
				template.sendProcessManagerAlert(bean.getUserEmpId(), "Resource Requisition", "A", reqsCode, "1",
												approverLinkParam, approverLink, actualStataus, String.valueOf(applyBy[0][0]), "0",
												"", "", bean.getUserEmpId());
				
				template.sendProcessManagerAlert("", "Resource Requisition", "I", reqsCode, "1",
												linkParam, link, actualStataus, String.valueOf(applyBy[0][0]),
												"0", KeepInformId, String.valueOf(applyBy[0][0]), bean.getUserEmpId());
			}else if (status.equals("S")) {
				actualStataus = "SentBack";
				template.sendProcessManagerAlert("", "Resource Requisition", "A",
						reqsCode, "1", linkParam, link,
						actualStataus, String.valueOf(applyBy[0][0]), "", "",
						String.valueOf(applyBy[0][0]), bean.getUserEmpId());
				
				linkParam = "requisitionCode=" + reqsCode + "&applStatus=R";

				template.sendProcessManagerAlert(bean.getUserEmpId(),
						"Resource Requisition", "I", reqsCode, "1",
						linkParam, link, actualStataus, String.valueOf(applyBy[0][0]),
						"0", KeepInformId, "",
						bean.getUserEmpId());

			} else {
				template.sendProcessManagerAlert(bean.getUserEmpId(),
						"Resource Requisition", "I", reqsCode, "1",
						linkParam, link, actualStataus, String.valueOf(applyBy[0][0]),
						"0", KeepInformId, String.valueOf(applyBy[0][0]),
						bean.getUserEmpId());
			//Send alert to all the approver who had taken action against current requisition -- BEGINS
				Object[][] allApproverID = getSqlModel().getSingleResult("SELECT DISTINCT HRMS_REC_REQS_PATH.PATH_APPROVER_CODE FROM HRMS_REC_REQS_PATH " +
																			   " WHERE HRMS_REC_REQS_PATH.PATH_REQ_CODE = " + reqsCode + 
																			   " AND HRMS_REC_REQS_PATH.PATH_APPROVER_CODE != " + bean.getUserEmpId());
				if (allApproverID != null && allApproverID.length > 0) {
					String finalApproverID = "";
					for (int i = 0; i < allApproverID.length; i++) {
						finalApproverID = String.valueOf(allApproverID[i][0]) + ",";
					}
					finalApproverID = finalApproverID.substring(0, finalApproverID.length()-1);
					template.sendProcessManagerAlert("", "Resource Requisition", "I", reqsCode, "1",
													linkParam, link, actualStataus, String.valueOf(applyBy[0][0]),
													"0", finalApproverID, "", bean.getUserEmpId());
				}
			//Send alert to all the approver who had taken action against current requisition -- BEGINS	
			}
			
			if (status.equals("S") || status.equals("R") || status.equals("H")) {
				if (KeepInformId != null) {
					template.sendApplicationMailToKeepInfo(KeepInformId);
				}
			} else {
				if (KeepInformId != null) {
					template.sendApplicationMailToKeepInfo(KeepInformId);
				}

				// if requisition is approved by final approver then only send
				// CC mail to recruitment head START
				if (finalApproverResult.equals("true")) {
					String recruitHeadQuery = "SELECT HRMS_REC_CONF.CONF_REC_HEAD FROM HRMS_REC_CONF";
					Object[][] recruitHeadObj = getSqlModel().getSingleResult(
							recruitHeadQuery);

					if (recruitHeadObj != null && recruitHeadObj.length > 0) {
						System.out.println("THIS IS FINAL APPROVER");
						template.sendApplicationMailToKeepInfo(String
								.valueOf(recruitHeadObj[0][0]));
					}
				}
				// if requisition is approved by final approver then only send
				// CC mail to recruitment head END

				template.sendApplicationMailToKeepInfo(String.valueOf(applyBy[0][2]));// Hiring Manager

				if (!String.valueOf(applyBy[0][1]).equals("0")) {
					template.sendApplicationMailToAlternateApprover(String.valueOf(applyBy[0][1]));// Alternate Approver.
				}
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * following function is called to check whether any next approver is
	 * available or not for the applicant.
	 * 
	 * @param empCode
	 * @param type
	 * @param order
	 * @return
	 */
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Added on Date:16-10-2009 by Pradeep following function is called when the
	 * Approve or Reject button is clicked in the Email. If approve button is
	 * clicked system will Approve the application otherwise if reject button is
	 * clicked system will reject the application.First system will check
	 * whether the status is "A" or "R".If the status is "A" then system will
	 * check whether any next approver is there or not for the applicant.If next
	 * approver is there then one entry will be made into HRMS_REQ_REQS_PATH
	 * table and the record will be updated in HRMS_REC_REQS_HDR table i.e. the
	 * approval status will be Pending and the next approver code will be
	 * updated.So that when the next approver will approve the record the same
	 * cycle will be repeated. sendApproverToNextApprover() method is called to
	 * send a mail to the next approver(if any) when the approver approves the
	 * application and if there is any next approver. sendMailToRecruitHead()
	 * function is called to send a mail to the head of the recruitment module
	 * whenever the application is getting approved by the corresponding
	 * approver.
	 * 
	 * If status is "R" the function rejectRecord() is getting called .In this
	 * function one entry is made in the HRMS_REC_REQS_PATH table and the
	 * application gets update in HRMS_REC_REQS_HDR table.sendMail() function is
	 * called to send the mail to the applicant regarding the rejection of the
	 * application.
	 * 
	 * @param request
	 * @param applicantId
	 * @param applicationCode
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 */
	public String onlineApproveReject(HttpServletRequest request,
			String applicantId, String applicationCode, String status,
			String remarks, String approverId, String level) {
		Object[][] empFlow = null;
		String message = "";
		try {
			System.out.println("status >>" + status);
			System.out.println("applicantId >>" + applicantId);
			System.out.println("applicationCode >>" + applicationCode);
			System.out.println("level >>" + level);
			System.out.println("approverId >>" + approverId);
			System.out.println("remarks >>" + remarks);

			if (status.equals("A")) {
				reqApproval.setUserEmpId(approverId);
				if (chkReqsStatus(approverId, applicationCode, "P")) {
					empFlow = generateEmpFlow(applicantId, "Recruitment",
							Integer.parseInt(level) + 1);
					boolean result = saveApprovedRec(empFlow, applicationCode,
							remarks, applicantId, reqApproval);
					if (empFlow != null && empFlow.length > 0) {
						removeCompetencyProcessManagerAlert(String.valueOf(applicationCode));
						sendApproverToNextApprover(reqApproval, applicationCode, empFlow, request, applicantId);
					}

					if (result) {
						sendMail(reqApproval, applicationCode, "A", "true");
					} else {
						//sendMail(reqApproval, applicationCode, "A", "false");
						sendForwardeMailToApplicant(reqApproval, String.valueOf(empFlow[0][0]), applicationCode, "A");
					}
					message = "Application approved successfully.";
				} else if (chkReqsStatus(approverId, applicationCode, "A")) {
					message = "Application already approved.";
				} else if (chkReqsStatus(approverId, applicationCode, "R")) {
					message = "Application already rejected.";
				} else if (chkReqsStatus(approverId, applicationCode, "S")) {
					message = "Application already send back.";
				} else if (chkReqsStatus(approverId, applicationCode, "H")) {
					message = "Application on On Hold.";
				} else {
					message = "Application already processed.";
				}
			} else {
				reqApproval.setUserEmpId(approverId);
				if (chkReqsStatus(approverId, applicationCode, "P")) {
					updateRecordStatus(applicationCode, remarks, applicantId,
							reqApproval, status);
					sendMail(reqApproval, applicationCode, status, "false");
					String str = status;
					if (str.equals("R")) {
						message = "Application rejected successfully.";
					} else if (str.equals("S")) {
						message = "Application Send Back successfully.";
					} else if (str.equals("H")) {
						message = "Application On Hold successfully.";
					}
				} else if (chkReqsStatus(approverId, applicationCode, "A")) {
					message = "Application already approved.";
				} else if (chkReqsStatus(approverId, applicationCode, "R")) {
					message = "Application already rejected.";
				} else if (chkReqsStatus(approverId, applicationCode, "S")) {
					message = "Application already send back.";
				} else if (chkReqsStatus(approverId, applicationCode, "H")) {
					message = "Application already on on hold.";
				} else {
					message = "Application already processed.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * @method showRecords
	 * @purpose to retrieve the application details as per the selected status
	 * @param bean
	 * @param status
	 * @return String
	 */
	public void showRecords(RequisitionApproval bean, String status,
			HttpServletRequest request) {
		String listType = "";
		if (status.equals("P") || status.equals("") || status.equals("null")) {
			listType = "Pending List";
			bean.setCommentHdr("true");
		} else if (status.equals("H")) {
			listType = "On Hold List";
			bean.setCommentHdr("false");
		} else if (status.equals("R")) {
			listType = "Rejected List";
			bean.setCommentHdr("false");
		} else if (status.equals("A")) {
			listType = "Approved List";
			bean.setCommentHdr("false");
		} else if (status.equals("S")) {
			listType = "Send Back List";
			bean.setCommentHdr("false");
		} else if (status.equals("Q")) {
			listType = "Quick Requisition List";
			bean.setCommentHdr("false");
		}

		request.setAttribute("listType", listType);
		try {
			/*
			 * query to select the application data from HRMS_REQS_HDR as per
			 * selected status
			 */
			Object[][] applicationData = getSqlModel().getSingleResult(
					getQuery(1),
					new Object[] { status, bean.getUserEmpId(),
							bean.getUserEmpId() });
			ArrayList<Object> list = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					applicationData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (applicationData != null && applicationData.length != 0) {
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {
					RequisitionApproval bean1 = new RequisitionApproval();

					bean1.setSrNo(String.valueOf(i + 1));  
					bean1.setReqNo(String.valueOf(applicationData[i][0]));  
					bean1.setReqDate(String.valueOf(applicationData[i][1])); 
					bean1.setAppliedFor(String.valueOf(applicationData[i][3]));  
					bean1.setAppliedBy(String.valueOf(applicationData[i][5]));  
					bean1.setHrManager(String.valueOf(applicationData[i][7])); 
					bean1.setReqName(String.valueOf(applicationData[i][8]));  
					bean1.setReqStatus(String.valueOf(applicationData[i][3]));  
					bean1.setLevel(String.valueOf(applicationData[i][9]));  
					if (status.equals("H")) {
						bean1.setHoldFlag("true");
					} else {
						bean1.setHoldFlag("false");
					}
					bean1.setStatusIterator(bean.getStatusIterator());  
					bean1.setEmpCode(String.valueOf(applicationData[i][4])); 

					if (status.equals("P")) {  
						bean1.setApprflag("true");  
						bean1.setCommentItr("true");
					}  else {
						bean1.setApprflag("false");  
						bean1.setCommentItr("false");
					}  

					/*
					 * select comments details from HRMS_REQS_PATH for a
					 * particular application
					 */
					String commentQuery = "SELECT NVL(HRMS_REC_REQS_PATH.PATH_REMARK, ' ') FROM HRMS_REC_REQS_PATH "
							+ "WHERE HRMS_REC_REQS_PATH.PATH_REQ_CODE = "
							+ bean1.getReqNo()
							+ " AND HRMS_REC_REQS_PATH.PATH_APPROVER_CODE = "
							+ bean.getUserEmpId()
							+ " AND HRMS_REC_REQS_PATH.PATH_STATUS = '"
							+ status + "' " + "ORDER BY HRMS_REC_REQS_PATH.PATH_REQ_CODE";

					Object[][] commentData = getSqlModel().getSingleResult(
							commentQuery);

					if (commentData != null && commentData.length != 0) {
						bean1.setComment(String.valueOf(commentData[0][0])); // comments
					}  
					list.add(bean1);
				}  
				bean.setNoData("false");  
			} else {
				bean.setNoData("true");  
			}
			bean.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method forward
	 * @purpose to update the status of the application in HRMS_REQS_HDR table
	 *          also to insert an entry in the HRMS_REQS_PATH table
	 * @param bean
	 * @param status
	 * @param reqNo
	 * @param comments
	 * @param empCode
	 * @return boolean
	 */
	public boolean forward(RequisitionApproval bean, String status,
			String reqNo, String comments, String empCode) {
		boolean result = false;
		try {
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqNo; 
			changeStatus[0][1] = reqNo;  
			changeStatus[0][2] = bean.getUserEmpId(); 
			changeStatus[0][3] = comments; 
			changeStatus[0][4] = status; 
			changeStatus[0][5] = empCode; 
			if (status.equals("A")) { 
				result = getSqlModel().singleExecute(getQuery(4), changeStatus);
			} 
			if (String.valueOf(status).equals("R")
					|| String.valueOf(status).equals("H")) {  
				Object[][] reject = new Object[1][2];
				reject[0][0] = String.valueOf(status);
				reject[0][1] = String.valueOf(reqNo);

				result = getSqlModel().singleExecute(getQuery(3), reject); 
				result = getSqlModel().singleExecute(getQuery(4), changeStatus); 
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @method changeApplStatus
	 * @purpose to update the status of the application in HRMS_REQS_HDR table
	 *          also to insert an entry in the HRMS_REQS_PATH table
	 * @param bean
	 * @param empFlow
	 * @param reqNo
	 * @return boolean
	 */
	public boolean changeApplStatus(RequisitionApproval bean,
			Object[][] empFlow, String reqNo) {
		boolean result = false;
		try {
			if (empFlow != null && empFlow.length != 0) { // if next approver
				// available
				Object[][] updateApprover = new Object[1][3];

				updateApprover[0][0] = empFlow[0][2]; // level of the application
				updateApprover[0][1] = empFlow[0][0]; // next approver code
				updateApprover[0][2] = reqNo; // requisition code

				/*
				 * update the application level and approver code in
				 * HRMS_REQS_HDR table for the selected application
				 */
				result = getSqlModel().singleExecute(getQuery(2),
						updateApprover);
			} // if statement ends
			else {
				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A"; // requisition status
				statusChanged[0][1] = reqNo; // requisition code
				/*
				 * update the application status to A in HRMS_REQS_HDR table
				 */
				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] getData(String reqCode, RequisitionApproval bean) {
		Object[][] data = null;
		try {
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_LEVEL, HRMS_REC_REQS_HDR.REQS_APPLIED_BY FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ reqCode;
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public String getKeepInform(String code) {
		String keepInform = "0";
		String queryKeepInfo = "SELECT HRMS_REC_REQS_HDR.REQS_KEEP_INFO_ID FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE = "
				+ code;
		Object[][] queryKeepInfoObject = getSqlModel().getSingleResult(
				queryKeepInfo);
		if (queryKeepInfoObject != null && queryKeepInfoObject.length > 0)
			keepInform = String.valueOf(queryKeepInfoObject[0][0]);
		return keepInform;
	}

	/**
	 * Added by pradeep On Date:10-09-2009 following function is called when the
	 * Approve button is clicked in the MAnpower Requisition form. following
	 * function is called to update the REQS_APPROVAL_STATUS field of
	 * HRMS_REC_REQS_HDR table to 'A' so that record will be Approved.This
	 * function also checks whether any next approver is available or not. If
	 * the next approver is available system will update HRMS_REC_REQS_HDR table
	 * with the next approver code,level of the approver and the
	 * REQS_APPROVAL_STATUS filed to 'P' means to Pending.So that the current
	 * application will be in Pending stage for the next approver if the current
	 * approver approves the existing application. If the next approver is not
	 * available then system will only update the REQS_APPROVAL_STATUS field of
	 * HRMS_REC_REQS_HDR table for the corresponding requisition. This function
	 * also inserts the record into HRMS_REC_REQS_PATH table.
	 * 
	 * @param empFlow
	 * @param reqCode
	 * @param comment
	 * @param applyBy
	 * @param bean
	 * @return
	 */

	public boolean saveApprovedRec(Object[][] empFlow, String reqCode,
			String comment, String applyBy, RequisitionApproval bean) {
		boolean result = false;
		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level of the
				// application
				updateApprover[0][1] = empFlow[0][0]; // next approver code
				updateApprover[0][2] = "P"; // Requisition Approval Status
				updateApprover[0][3] = reqCode;
				getSqlModel().singleExecute(getQuery(2), updateApprover);
				// sendApproverToNextApprover(bean,reqCode,String.valueOf(empFlow[0][0]));
			} else {
				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A"; // requisition status
				statusChanged[0][1] = reqCode; // requisition code

				/*
				 * update the application status to A in HRMS_REQS_HDR table
				 */
				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);
			} // else statement ends
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqCode;// requisition code
			changeStatus[0][1] = reqCode;// Requisition Code
			changeStatus[0][2] = bean.getUserEmpId();// user employee id
			changeStatus[0][3] = comment;// Comment
			changeStatus[0][4] = "A";// requisition status
			changeStatus[0][5] = applyBy;// applied by employee id
			getSqlModel().singleExecute(getQuery(4), changeStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Added by pradeep On Date:10-09-2009 following function is called to
	 * update the REQS_APPROVAL_STATUS field of HRMS_REC_REQS_HDR table to 'H'
	 * so that record will be On Hold.This function also inserts the record into
	 * HRMS_REC_REQS_PATH table.
	 * 
	 * @param reqCode
	 * @param comment
	 * @param applyBy
	 * @param bean
	 * @return
	 */
	public boolean onHoldRecord(String reqCode, String comment, String applyBy,
			RequisitionApproval bean) {
		boolean result = false;
		try {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "H"; // requisition status
			statusChanged[0][1] = reqCode; // requisition code
			/*
			 * update the application status to H in HRMS_REQS_HDR table
			 */
			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqCode;// requisition code
			changeStatus[0][1] = reqCode;// requisition code
			changeStatus[0][2] = bean.getUserEmpId();// user employee id
			changeStatus[0][3] = comment;// Comment
			changeStatus[0][4] = "H";// requisition status
			changeStatus[0][5] = applyBy;// applied by employee id
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Added by pradeep On Date:10-09-2009 following function is called to
	 * update the REQS_APPROVAL_STATUS field of HRMS_REC_REQS_HDR table to 'R'
	 * so that record will be rejected.This function also inserts the record
	 * into HRMS_REC_REQS_PATH table.
	 * 
	 * @param reqCode
	 * @param comment
	 * @param applyBy
	 * @param bean
	 * @return
	 */

	public boolean rejectRecord(String reqCode, String comment, String applyBy,
			RequisitionApproval bean) {
		boolean result = false;
		try {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "R"; // requisition status
			statusChanged[0][1] = reqCode; // requisition code
			/*
			 * update the application status to H in HRMS_REQS_HDR table
			 */
			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqCode;// requisition code
			changeStatus[0][1] = reqCode;// ""
			changeStatus[0][2] = bean.getUserEmpId();// user employee id
			changeStatus[0][3] = comment;// Comment
			changeStatus[0][4] = "R";// requisition status
			changeStatus[0][5] = applyBy;// applied by employee id
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateRecordStatus(String reqCode, String comment,
			String applyBy, RequisitionApproval bean, String status) {
		boolean result = false;
		try {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = status; // requisition status
			statusChanged[0][1] = reqCode; // requisition code
			/*
			 * update the application status to H in HRMS_REQS_HDR table
			 */
			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqCode;// requisition code
			changeStatus[0][1] = reqCode;// ""
			changeStatus[0][2] = bean.getUserEmpId();// user employee id
			changeStatus[0][3] = comment;// Comment
			changeStatus[0][4] = status;// requisition status
			changeStatus[0][5] = applyBy;// applied by employee id
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following method is called to check the approval status and the approver
	 * code of the corresponding application.
	 * 
	 * @param approvrCode
	 * @param reqsCode
	 * @param status
	 * @return
	 */
	public boolean chkReqsStatus(String approvrCode, String reqsCode,
			String status) {
		boolean result = false;
		try {
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS, HRMS_REC_REQS_HDR.REQS_APPR_CODE, HRMS_REC_REQS_HDR.REQS_CODE FROM HRMS_REC_REQS_HDR "
					+ " WHERE HRMS_REC_REQS_HDR.REQS_APPR_CODE="
					+ approvrCode + " AND HRMS_REC_REQS_HDR.REQS_CODE="+ reqsCode + " AND HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS='" + status + "'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data.length > 0 && data != null) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Added for keep inform to list
	public String getKeepInfoIdList(String applId) {
		String keepInfoId = "0";
		String keepInformedId = " SELECT HRMS_REC_REQS_HDR.REQS_KEEP_INFO_ID FROM HRMS_REC_REQS_HDR "
								+ " WHERE HRMS_REC_REQS_HDR.REQS_CODE=" + applId;

		Object[][] keepInformedObj = getSqlModel().getSingleResult(
				keepInformedId);

		if (keepInformedObj != null && keepInformedObj.length > 0) {
			keepInfoId = String.valueOf(keepInformedObj[0][0]);

		}

		return keepInfoId;
	}

	public boolean sendBackRecord(String reqCode, String comment,
			String applyBy, RequisitionApproval bean) {
		boolean result1 = false;
		try {
			String updateLevelQuery = "UPDATE HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_LEVEL = 1 WHERE HRMS_REC_REQS_HDR.REQS_CODE = "+reqCode;
			getSqlModel().singleExecute(updateLevelQuery);
			
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "S"; // requisition status
			statusChanged[0][1] = reqCode; // requisition code
			/*
			 * update the application status to H in HRMS_REQS_HDR table
			 */
			result1 = getSqlModel().singleExecute(getQuery(3), statusChanged);
			Object[][] changeStatus = new Object[1][6];
			changeStatus[0][0] = reqCode;// requisition code
			changeStatus[0][1] = reqCode;// ""
			changeStatus[0][2] = bean.getUserEmpId();// user employee id
			changeStatus[0][3] = comment;// Comment
			changeStatus[0][4] = "S";// requisition status
			changeStatus[0][5] = applyBy;// applied by employee id
			result1 = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}
	
	public void sendForwardeMailToApplicant(RequisitionApproval bean, String nextApproverCode, String reqCode, String status) {
		try {
			RequisitionApprovalModel model = new RequisitionApprovalModel();
			model.initiate(context, session);
			String applyByQuery = "SELECT HRMS_REC_REQS_HDR.REQS_APPLIED_BY,HRMS_REC_REQS_HDR.REQS_ALTER_APPROVER,HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE="
									+ reqCode;
			Object[][] applyBy = model.getSqlModel().getSingleResult(applyByQuery);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("Manpower Requisition Forwarded Mail To Applicant");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, bean.getUserEmpId());

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, String.valueOf(applyBy[0][0]));

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, reqCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, bean.getUserEmpId());
			templateQuery4.setParameter(2, reqCode);
			templateQuery4.setParameter(3, status);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, nextApproverCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, reqCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, reqCode);
			
			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, reqCode);

			template.configMailAlert();
			String keepInformId = model.getKeepInform(reqCode);
			if(keepInformId != null){
				template.sendApplicationMailToKeepInfo(keepInformId);
			}
			String link = "/recruit/EmployeeRequi_callForEdit.action";
			String linkParam = "requisitionCode=" + reqCode + "&applStatus=P";
			template.sendProcessManagerAlert(bean.getUserEmpId(), "Resource Requisition", "I", reqCode, "1", linkParam, link, "Pending",
											 String.valueOf(applyBy[0][0]), "0", keepInformId, String.valueOf(applyBy[0][0]), bean.getUserEmpId());
			 
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	/**Method : removeCompetencyProcessManagerAlert.
	 * Purpose : This method is used to remove process manager alert that are previously added.
	 * @param requisitionCode :requisitionCode 
	 * @return : Void
	 */
	public void removeCompetencyProcessManagerAlert(final String requisitionCode) {
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(requisitionCode, "Resource Requisition");
			processAlerts.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
}
