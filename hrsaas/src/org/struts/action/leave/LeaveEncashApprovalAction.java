package org.struts.action.leave;

import java.io.FileInputStream;
import java.util.Properties;

import org.paradyne.bean.leave.LeaveEncashApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.paradyne.model.leave.LeaveEncashApprovalModel;
import org.struts.lib.ParaActionSupport;

public class LeaveEncashApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	LeaveEncashApproval leaveencash;

	public void prepare_local() throws Exception {
		leaveencash = new LeaveEncashApproval();
		leaveencash.setMenuCode(581); // setting menu code
	}

	public Object getModel() {
		return leaveencash;
	}

	public LeaveEncashApproval getLeaveencash() {
		return leaveencash;
	}

	public void setLeaveencash(LeaveEncashApproval leaveencash) {
		this.leaveencash = leaveencash;
	}

	public void prepare_withLoginProfileDetails() throws Exception {

		LeaveEncashApprovalModel model = new LeaveEncashApprovalModel();
		model.initiate(context, session);
		model.collect(leaveencash, "P", request);
		leaveencash.setPen("true");
		model.terminate();
	}

	/**
	 * THIS METHOD IS USED FOR DISPLAYING RECORDS FOR PENDING,APPROVED,REJECTED LIST OF LEAVE ENCASHMENT APPLICATIONS
	 * 
	 * @return String
	 */
	public String callstatus() {
		LeaveEncashApprovalModel model = new LeaveEncashApprovalModel();
		model.initiate(context, session);
		String status = "";
		String stat = "";
		try {
			status = request.getParameter("status");
			status = String.valueOf(status.charAt(0));
		} catch(Exception e) {
			logger.error("Exception in callstatus---------------" + e);
		}// end of catch
		
		if(status == null || status.equals("")) {
			status = "P";
		} else if(status.equals("F")) {
			status = "P";
		}// end of else if

		if(status.equals("P")) {
			leaveencash.setPen("true");
			stat = "Pending List";
		} else if(status.equals("A")) {
			leaveencash.setApp("true");
			stat = "Approved List";
		} else if(status.equals("R")) {
			leaveencash.setRej("true");
			stat = "Rejected List";
		} else if(status.equals("H")) {
			leaveencash.setHol("true");
			stat = "On-HOld List";
		}// end of else if
		
		if(!(status.equals("P"))) {
			leaveencash.setApprflag("true");
		}// end of if
		
		request.setAttribute("stat", stat);
		model.collect(leaveencash, status, request);
		model.terminate();
		return "success";

	}// end of callstatus

	public String callPage1() throws Exception {
		LeaveEncashApprovalModel model = new LeaveEncashApprovalModel();
		model.initiate(context, session);
		callstatus();
		model.terminate();
		return SUCCESS;
	}

	public String callPage2() throws Exception {
		logger.info("call page 2");
		LeaveEncashApprovalModel model = new LeaveEncashApprovalModel();
		model.initiate(context, session);
		callstatus();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SAVE
	 * 
	 * @return String
	 */
	public String saveStatus() {

		String applStatus = "";
		boolean result = false;
		int j = 0;
		LeaveEncashApprovalModel model = new LeaveEncashApprovalModel();
		model.initiate(context, session);
		String[] encashAppNo = request.getParameterValues("encashAppNo"); // application code
		String[] empCode = request.getParameterValues("empCode");// employee id
		String[] status = request.getParameterValues("checkStatus");// status
		System.out.println("status================="+status);
		
		String level = request.getParameter("level");// level
		System.out.println("level================="+level);
		String[] remarks = request.getParameterValues("statusRemark"); // remarks
		String[] date = request.getParameterValues("date");// date
		String alternateApprover = "";
		/*private String salarymonth = "";
		private String salaryyear = "";
		private String creditCode = "";
		private String creditType = "";
		private String salaryCheck = "";
		
		private String approverComments = "";*/
		
		if(status != null && status.length > 0) {
			for(int i = 0; i < encashAppNo.length; i++) {
				Object[][] empFlow = null;
				if(!(status[i].equals("P"))) {
					applStatus = model.forward(leaveencash, status[i], encashAppNo[i], remarks[i]);
					result = true;
					j = 1;
					if(status[i].equals("A")) {
						empFlow = generateEmpFlow(empCode[i], "LeaveEncash", Integer.parseInt(level) + 1);
						
						applStatus = model.changeApplStatus(leaveencash, empFlow, String.valueOf(encashAppNo[i]), empCode[i], request);
						result = true;
						j = 1;
					}// end of nested if
				} // end of if

				// --------Email Templates + Process Manager Alert--------------start
				try {
					if(!String.valueOf(status[i]).equals("P")) {
						ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
						processAlerts.initiate(context, session);

						String module = "LeaveEncash";
						processAlerts.removeProcessAlert(String.valueOf(encashAppNo[i]), module);

						String applicant = "", oldApprover = "", newApprover = "";
						try {
							applicant = empCode[i];
							oldApprover = leaveencash.getUserEmpId();
							newApprover = String.valueOf(empFlow[0][0]);
							System.out.println("newApprover==22222222==="+newApprover);
						} catch(Exception e) {
							logger.error(e);
						}

						String empID = "", msgType = "";
						String applnID = String.valueOf(encashAppNo[i]);
						String applnDate = String.valueOf(date[i]);
						String alertLevel = String.valueOf(Integer.parseInt(level) + 1);

						if(!newApprover.equals("")) {
							// send alert from oldApprover to newApprover
							
							
							
							empID = newApprover;
							msgType = "A";

							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);

							template.setEmailTemplate("LEAVE ENC-APPROVER TO APPROVER");

							template.getTemplateQueries();

							EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
							templateQuery1.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
							templateQuery2.setParameter(1, newApprover);

							EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
							//templateQuery3.setParameter(1, applnDate);
							templateQuery3.setParameter(1, applicant);

							EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
							templateQuery4.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
							templateQuery5.setParameter(1, newApprover);
							
							System.out.println("newApprover==3333333==="+newApprover);
							
							EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
							templateQuery6.setParameter(1, applicant);
							templateQuery6.setParameter(2, applnID);
							
							EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
							templateQuery7.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
							templateQuery8.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
							templateQuery9.setParameter(1, applnID);
							

							template.configMailAlert();
							try {
								//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
								
								String link = "";
								String linkParam = "";

								link = "/leaves/LeaveEncashment_retriveForApproval.action";
								linkParam = "leaveEncashAppNo=" + applnID + "&encashEmpId=" + applicant;
								try {
									/*template.sendProcessManagerAlert(oldApprover, module, "I",
											applnID, alertLevel, linkParam, link, applStatus,
											empID, "", "", applicant, oldApprover);
									
									template.sendProcessManagerAlert(newApprover, module, "A",
											applnID, alertLevel, linkParam, link, applStatus,
											"", "", "","", newApprover);*/
									
									template.sendProcessManagerAlert(newApprover, module, msgType, applnID,
											alertLevel, linkParam, link, "Pending",
											applicant, "", "", "", oldApprover);
									
									
									
									String[] keep = request.getParameterValues("keepInformToCode");
									if (keep != null) {
										template.sendApplicationMailToKeepInfo(keep);
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							} catch(Exception e) {
								logger.error(e);
							}
							template.sendApplicationMail();
							template.clearParameters();
							template.terminate();

							// send alert from oldApprover to applicant
							empID = applicant;
							msgType = "I";

							EmailTemplateBody template1 = new EmailTemplateBody();
							template1.initiate(context, session);

							template1.setEmailTemplate("LEAVE ENC-APPROVER1 TO APPLICANT");

							template1.getTemplateQueries();

							EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); // FROM EMAIL
							templateQuery11.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); // TO EMAIL
							templateQuery12.setParameter(1, applicant);

							EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
							//templateQuery13.setParameter(1, applnDate);
							templateQuery13.setParameter(1, applicant);

							EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
							templateQuery14.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery15 = template1.getTemplateQuery(5);
							templateQuery15.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery16 = template1.getTemplateQuery(6);
							templateQuery16.setParameter(1, applicant);
							templateQuery16.setParameter(2, applnID);
							
							EmailTemplateQuery templateQuery17 = template1.getTemplateQuery(7);
							templateQuery17.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery18 = template1.getTemplateQuery(8);
							templateQuery18.setParameter(1, applnID);

							template1.configMailAlert();
							try {
								//template1.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
								
								String link = "";
								String linkParam = "";

								if(String.valueOf(status[i]).equals("B")) {
									link = "/leaves/LeaveEncashment_editLeaveRecord.action";
									linkParam = "leaveEncashAppNo=" + applnID;
								}
								else {
									link = "/leaves/LeaveEncashment_editManagerLeaveRecord.action";
									linkParam = "leaveEncashAppNo=" + applnID;
								}
								
								
								
								
								try {
									
									
									/*template.sendProcessManagerAlert(oldApprover, module, "I",
											applnID, alertLevel, linkParam, link, applStatus,
											empID, "", "", applicant, oldApprover);*/
									
									if(String.valueOf(status[i]).equals("B")) {
										
										link = "/leaves/LeaveEncashment_editLeaveRecord.action";
										linkParam = "leaveEncashAppNo=" + applnID;
										
										template1.sendProcessManagerAlert(applicant, module, "A",
												applnID, alertLevel, linkParam, link, applStatus,
												applicant, "", "", applicant, oldApprover);

										
										link = "/leaves/LeaveEncashment_editManagerLeaveRecord.action";
										linkParam = "leaveEncashAppNo=" + applnID;
										
										template1.sendProcessManagerAlert(oldApprover, module, "I",
												applnID, alertLevel, linkParam, link, applStatus,
												"", "", "", applicant, oldApprover);
										
									}else{
										template1.sendProcessManagerAlert(oldApprover, module, "I",
												applnID, alertLevel, linkParam, link, applStatus,
												empID, "", "", applicant, oldApprover);
									}
									
									
									String[] keep = request.getParameterValues("keepInformToCode");
									if (keep != null) {
										template1.sendApplicationMailToKeepInfo(keep);
									}
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							} catch(Exception e) {
								logger.error(e);
							}
							template1.sendApplicationMail();
							template1.clearParameters();
							template1.terminate();
						} else {
							// send alert from oldApprover to applicant
							empID = applicant;
							msgType = "I";
							System.out.println("FINAl LEVEL");
							EmailTemplateBody templateFinal = new EmailTemplateBody();
							templateFinal.initiate(context, session);

							templateFinal.setEmailTemplate("LEAVE ENC-APPROVER2 TO APPLICANT");

							templateFinal.getTemplateQueries();

							EmailTemplateQuery templateQuery1 = templateFinal.getTemplateQuery(1); // FROM EMAIL
							templateQuery1.setParameter(1, oldApprover);

							EmailTemplateQuery templateQuery2 = templateFinal.getTemplateQuery(2); // TO EMAIL
							templateQuery2.setParameter(1, applicant);

							EmailTemplateQuery templateQuery3 = templateFinal.getTemplateQuery(3);
							templateQuery3.setParameter(1, applicant);

							EmailTemplateQuery templateQuery4 = templateFinal.getTemplateQuery(4);
							templateQuery4.setParameter(1, oldApprover);
							
							EmailTemplateQuery templateQuery5 = templateFinal.getTemplateQuery(5);
							templateQuery5.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery6 = templateFinal.getTemplateQuery(6);
							templateQuery6.setParameter(1, applicant);
							templateQuery6.setParameter(2, applnID);
							
							EmailTemplateQuery templateQuery7 = templateFinal.getTemplateQuery(7);
							templateQuery7.setParameter(1, applnID);
							
							EmailTemplateQuery templateQuery8 = templateFinal.getTemplateQuery(8);
							templateQuery8.setParameter(1, applnID);

							templateFinal.configMailAlert();
							try {
								//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
								
								String link = "";
								String linkParam = "";
								if(String.valueOf(status[i]).equals("B")) {
									link = "/leaves/LeaveEncashment_editLeaveRecord.action";
									linkParam = "leaveEncashAppNo=" + applnID;
								}else{
									link = "/leaves/LeaveEncashment_editManagerLeaveRecord.action";
									linkParam = "leaveEncashAppNo=" + applnID;
								}
								
								try {
									//approver to applicant 
									if(String.valueOf(status[i]).equals("B")) {
										
										link = "/leaves/LeaveEncashment_editLeaveRecord.action";
										linkParam = "leaveEncashAppNo=" + applnID;
										
										templateFinal.sendProcessManagerAlert(applicant, module, "A",
												applnID, alertLevel, linkParam, link, applStatus,
												applicant, "", "", applicant, oldApprover);
										
										link = "/leaves/LeaveEncashment_editManagerLeaveRecord.action";
										linkParam = "leaveEncashAppNo=" + applnID;
										
										templateFinal.sendProcessManagerAlert(oldApprover, module, "I",
												applnID, alertLevel, linkParam, link, applStatus,
												"", leaveencash.getApproverCode(), "", "", oldApprover);

									}else{
										templateFinal.sendProcessManagerAlert(oldApprover, module, "I",
												applnID, alertLevel, linkParam, link, applStatus,
												empID, leaveencash.getApproverCode(), "", applicant, oldApprover);
									}
									
									
									/*template.sendProcessManagerAlert(oldApprover, module,
											"I", applnID, alertLevel, linkParam, link,
											"Pending", applicant, "", "",
											applicant, oldApprover);*/
									
									String keepInformedId ="";
									String approverName ="";
									String empName = "";
									String query = " SELECT NVL(HRMS_LEAVE_ENCASH_HDR.ENCASH_KEEP_INFORM_ID,' '), " +
											" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME " +
											" FROM HRMS_LEAVE_ENCASH_HDR " +
											" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.APPROVED_BY) " +
											" WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE=" + applnID;
									
									Object[][] keepInfoObj = model.getSqlModel().getSingleResult(query);
									if (keepInfoObj != null && keepInfoObj.length > 0) {
										keepInformedId = String.valueOf(keepInfoObj[0][0]);
										approverName =  String.valueOf(keepInfoObj[0][1]);
									}								
									
									query = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME " +
										" FROM HRMS_EMP_OFFC " +
										" INNER JOIN HRMS_LEAVE_ENCASH_HDR ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID) " +
										" WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE=" + applnID;
							
									Object[][] empNameObj = model.getSqlModel().getSingleResult(query);
									if (empNameObj != null && empNameObj.length > 0) {
										empName = String.valueOf(empNameObj[0][0]); 
									}
									
									//approver to himself
								///	sendProcessManagerAlertForSendforApproval(empName, applStatus, approverName, oldApprover, 
								///			applnID, applicant, keepInformedId);
									
									
									
									String[] keep = request.getParameterValues("keepInformToCode");
									if (keep != null) {
										templateFinal.sendApplicationMailToKeepInfo(keep);
									}
									
									templateFinal.sendApplicationMailToKeepInfo(leaveencash.getApproverCode());
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							} catch(Exception e) {
								logger.error(e);
							}
							templateFinal.sendApplicationMail();
							templateFinal.clearParameters();
							templateFinal.terminate();
						}
					}
				} catch(Exception e) {
					logger.error(e);
				}
				// --------Email Templates + Process Manager Alert--------------end

			}// end of for
		}// end of if
		if(result && j == 1) {
			addActionMessage(getMessage("save"));
		}// end of if
		model.collect(leaveencash, "P", request);
		model.terminate();
		return "mymessages";
	}// end of saveStatus

	/**
	 * THIS METHOD IS USED FOR RESET FORM
	 * 
	 * @return String
	 */
	public String reset() {
		leaveencash.setApp("");
		leaveencash.setApprflag("");
		leaveencash.setCheckStatus("");
		leaveencash.setDate("");
		leaveencash.setEmpCode("");
		leaveencash.setEmpName("");
		leaveencash.setHol("");
		leaveencash.setLevel("");
		// leaveencash.setListLength("0");
		leaveencash.setPen("");
		leaveencash.setRej("");
		leaveencash.setStatus("");
		leaveencash.setEncashAppNo("");
		return SUCCESS;
	}// end of reset

	
	public void sendProcessManagerAlertForSendforApproval(String empName,String status,String approverName,String approverCode,String applicationCode,String empCode ,String keepInformedId) {
		try {
		 
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");			
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "I";
			String applicantID = empCode;
			String module = "LeaveEncash";
			String applnID = applicationCode;
			String level = "1";
		 
			String link = "/leaves/LeaveEncashment_retriveForApproval.action";
			String linkParam = "leaveEncashAppNo=" + applnID + "&encashEmpId=" + applicantID;
			
			String message = alertProp.getProperty("applicationAlertMessage");
			message = message.replace("<#MODULE#>", "LeaveEncash");
			message = message.replace("<#EMPLOYEE_NAME#>", empName );
			message = message.replace("<#STATUS#>",status);
			message = message.replace("<#APPROVER_NAME#>",approverName);
			
			template.sendProcessManagerAlertWithdraw("", module, msgType,
					applnID, level, linkParam, link, message, status,
					"", applicantID,keepInformedId,leaveencash.getUserEmpId());
			
			template.terminate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}// end of class
