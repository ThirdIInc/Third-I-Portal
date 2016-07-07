/**
 * 
 */
package org.struts.action.Loan;

import org.paradyne.bean.Loan.LoanApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.loan.LoanApprovalModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * @date 15-07-2008 modified @ 12/12/2011
 * @description LoanApprovalAction class to change the status of the application
 *              from pending to approved or rejected and also to forward the
 *              application to the next approver
 */
public class LoanApprovalAction extends ParaActionSupport {

	LoanApproval loanApproval;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanApprovalAction.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		loanApproval = new LoanApproval();
		loanApproval.setMenuCode(627);
		// TODO Auto-generated method stub

	}

	/**
	 * @method name : prepare_withLoginProfileDetails
	 * @purpose : to display the pending applications in tabular format
	 * @return type : void
	 * @parameter : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		loanApproval.setLoanAppStatus("P"); // set the application status as
											// pending
		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session); // initialize the LoanApprovalModel
											// class

		/**
		 * call showApplications(loanApproval) method of LoanApprovalModel class
		 * to retrieve the pending application details from
		 * HRMS_LOAN_APPLICATION
		 */
		// model.showApplications(loanApproval);
		model.terminate(); // terminate the LoanApprovalModel class
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return loanApproval;
	}

	public LoanApproval getLoanApproval() {
		return loanApproval;
	}

	public void setLoanApproval(LoanApproval loanApproval) {
		this.loanApproval = loanApproval;
	}

	/**
	 * @method name : ckeckdata
	 * @purpose : to display the applications in tabular format as per the
	 *          selected status
	 * @return type : String
	 * @parameter : none
	 */
	public String ckeckdata() {

		String status = request.getParameter("status"); // getting status which
														// passed as a request
														// parameter from form

		loanApproval.setLoanAppStatus(status); // set application status as it
												// is in request parameter

		String stat = "";

		if (status.equals("P") || status.equals(" ")) { // if status is P
			stat = "Pending List"; // set header status as Pending List

			loanApproval.setStatusIterator("Pending");
			loanApproval.setApprFlag("false");
		} // end of if statement
		else if (status.equals("A") || status.equals("F")) { // if status is
																// A
			stat = "Approved List"; // set header status as Approved List

			loanApproval.setStatusIterator("Approved");

			loanApproval.setApprFlag("true");
		} // end of else if statement
		else if (status.equals("R")) { // if status is R
			stat = "Rejected List"; // set header status as Rejected List

			loanApproval.setStatusIterator("Rejected");

			loanApproval.setApprFlag("true");
		} // end of else if statement
		else if (status.equals("H")) { // if status is A
			stat = "On Hold List"; // set header status as On Hold List

			loanApproval.setStatusIterator("On Hold");

			loanApproval.setApprFlag("false");
		} // end of else if statement

		request.setAttribute("stat", stat); // set header status as a request
											// attribute

		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session); // initialize the LoanApprovalModel
											// class

		/**
		 * call showApplications(loanApproval) method of LoanApprovalModel class
		 * to retrieve all application details as per the selected status
		 */
		model.showApplications(loanApproval);

		model.terminate(); // terminate the LoanApprovalModel class
		return "success";
	}

	/**
	 * @method name : saveApproval
	 * @purpose : to change the status of the selected application or to forward
	 *          the application to the next approver
	 * @return type : String
	 * @parameter : none
	 */
	public String saveApproval() {

		boolean result = false;

		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session); // initialize the LoanApprovalModel
											// class

		String appStatus = loanApproval.getLoanAppStatus();

		/**
		 * getting all the form fields values which are necessary to change the
		 * status of the selected application
		 */

		String loanCode = request.getParameter("loanCodeVal");// application
																// code
		String empCode = loanApproval.getEmpCode(); // employee code
		String level = request.getParameter("levelVal"); // application level
		// String status = loanApproval.getApplicationStatus(); //status
		// selected by approver
		String comments = loanApproval.getComment(); // comments given by
														// approver
		// logger.info("status---------"+status);

		String status = request.getParameter("status");
		loanApproval.setHiddenStatus(appStatus);

		String apprAmount = request.getParameter("apprAmount");

		String applnStatus = "";

		// for(int i=0;i<status.length(); i++){

		Object[][] empFlow = null;
		if (!(status.equals("P"))) { // if status is not P
			if (status.equals("A")) { // and if status is A

				/**
				 * call generateEmpFlow(empCode, type, level) method of
				 * ParaActionSupport class to get the next approver details if
				 * any exists
				 */
				empFlow = generateEmpFlow(empCode, "Loan", Integer
						.parseInt(level) + 1);
				// logger.info("empflow--------"+empFlow+" length
				// "+empFlow.length);

				/**
				 * call changeApplStatus(loanApproval, empFlow,
				 * String.valueOf(loanCode[i])) method of LoanApprovalModel
				 * class to change the status of the selected application in
				 * HRMS_LOAN_APPLICATION table
				 */
				applnStatus = model.changeApplStatus(loanApproval, empFlow,
						String.valueOf(loanCode), empCode);
				result = true;
			} // end of if statement

			/**
			 * call forward(loanApproval, status[i], loanCode[i], empCode[i],
			 * comments[i]) method of LoanApprovalModel class to change the
			 * status of the selected application in HRMS_LOAN_APPLICATION table
			 * and to insert an entry related to approver details in
			 * HRMS_LOAN_PATH table
			 */
			applnStatus = model.forward(loanApproval, status, loanCode,
					empCode, comments, apprAmount);
			result = true;

			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(loanCode);
			String module = "Loan";

			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = String.valueOf(empCode);
				oldApprover = loanApproval.getUserEmpId();
				newApprover = String.valueOf(empFlow[0][0]);
			} catch (Exception e) {
				logger.error(e);
			}
			String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
			sendApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, alertLevel, applnStatus, level, empFlow);
			// ------------------------Process Manager
			// Alert------------------------end

		} // end of if statement
		// } //end of for loop
		if (result) { // is status changed with out any error
			addActionMessage(getText("addMessage", "")); // display save
															// message
		} // end of if statement
		else {
			addActionMessage("Record status can not be changed !"); // else
																	// display
																	// error
																	// message
		} // end of else statement

		/**
		 * call showApplications(loanApproval) method of LoanApprovalModel class
		 * to retrieve all application details as per the selected status
		 */
		model.showApplications(loanApproval);

		model.terminate(); // terminate the LoanApprovalModel class

		// return "success";
		// model.input(loanApproval,request,loanApproval.getUserEmpId());
		// /return INPUT;

		return "mymessages";
	}

	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel, String applStatus, String level,
			Object[][] empFlow) {
		try {
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Loan";
			processAlerts.removeProcessAlert(String.valueOf(applnID), prmodule);
			String alternateApprover = "";
			String link = "";
			String linkParam = "";
			String actualStataus = "";

			if (applStatus.equals("A")) {
				actualStataus = "Approved";
			}
			if (applStatus.equals("R")) {
				actualStataus = "Rejected";
			}

			try {

				if (actualStataus.equals("B")) {
					newApprover = "";
				} else {
					newApprover = String.valueOf(empFlow[0][0]);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
			if (Integer.parseInt(level) == 1) {
				empFlow = generateEmpFlow(applicant, "Loan", Integer
						.parseInt(level));
			} else {
				empFlow = generateEmpFlow(applicant, "Loan", Integer
						.parseInt(level));
			}

			String empID = "", msgType = "";
			if (applStatus.equals("R") || applStatus.equals("B")) {
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template
						.setEmailTemplate("LOAN APPLICATION -FINAL APPROVER TO APPLICANT");
				// template.setEmailTemplate("LOAN - APPROVER REJECT");

				template.getTemplateQueries();

				/*
				 * EmailTemplateQuery templateQuery1 =
				 * template.getTemplateQuery(1); //FROM EMAIL
				 * templateQuery1.setParameter(1, oldApprover);
				 * 
				 * EmailTemplateQuery templateQuery2 =
				 * template.getTemplateQuery(2); //TO EMAIL
				 * templateQuery2.setParameter(1, applicant);
				 * 
				 * EmailTemplateQuery templateQuery3 =
				 * template.getTemplateQuery(3); templateQuery3.setParameter(1,
				 * applnID);
				 * 
				 * EmailTemplateQuery templateQuery4 =
				 * template.getTemplateQuery(4); templateQuery4.setParameter(1,
				 * oldApprover);
				 */

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applnID);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applnID);

				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, applnID);

				EmailTemplateQuery templateQuery10 = template
				.getTemplateQuery(10);
		templateQuery10.setParameter(1, loanApproval.getUserEmpId());
		
				template.configMailAlert();
				/*
				 * try { template.sendProcessManagerAlert(empID, module,
				 * msgType, applnID, alertLevel);
				 * template.sendApplicationMail(); } catch(Exception e) {
				 * logger.error(e); }
				 */

				try {

					link = "/loan/LoanApplication_viewApplicationFunction.action";
					linkParam = "loanApplCode=" + applnID;

					alternateApprover = (empFlow != null && !String.valueOf(
							empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					oldApprover = String.valueOf(empFlow[0][0]);

				} catch (Exception e) {
					logger.error(e);
				}

				if (applStatus.equals("B")) {
					actualStataus = "SentBack";

					template.sendProcessManagerAlert(oldApprover, module, "A",
							applnID, alertLevel, linkParam, link,
							actualStataus, applicant, "", "", applicant,
							oldApprover);

				} else {

					template.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, alertLevel, linkParam, link,
							actualStataus, applicant, alternateApprover, "",
							applicant, oldApprover);

				}

				template.clearParameters();
				template.terminate();
			} else {
				if (!newApprover.equals("")) {
					// send alert from oldApprover to newApprover
					empID = newApprover;
					msgType = "A";
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("LOAN APPLICATION FROM  APPROVER1 TO APPROVER2");

					// template.setEmailTemplate("LOAN - APPROVER1 TO
					// APPROVER2");

					template.getTemplateQueries();

					/*
					 * EmailTemplateQuery templateQuery1 =
					 * template.getTemplateQuery(1); //FROM EMAIL
					 * templateQuery1.setParameter(1, oldApprover);
					 * 
					 * EmailTemplateQuery templateQuery2 =
					 * template.getTemplateQuery(2); //TO EMAIL
					 * templateQuery2.setParameter(1, newApprover);
					 * 
					 * EmailTemplateQuery templateQuery3 =
					 * template.getTemplateQuery(3);
					 * templateQuery3.setParameter(1, applnID); // application
					 * details
					 * 
					 * EmailTemplateQuery templateQuery4 =
					 * template.getTemplateQuery(4);
					 * templateQuery4.setParameter(1, oldApprover); // current
					 * approver details
					 * 
					 * EmailTemplateQuery templateQuery5 =
					 * template.getTemplateQuery(5);
					 * templateQuery5.setParameter(1, newApprover);
					 */// next
																		// approver
																		// details

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, newApprover);
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID); // application
																// details

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applnID); // current
																// approver
																// details

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applnID); // next approver
																// details

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applnID);

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, applnID);

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, applnID);

					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, oldApprover); // current
																	// approver
																	// details

					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(10);
					templateQuery10.setParameter(1, newApprover);

					template.configMailAlert();
					/*
					 * try { template.sendProcessManagerAlert(empID, module,
					 * msgType, applnID, alertLevel);
					 * template.sendApplicationMail(); } catch(Exception e) {
					 * logger.error(e); }
					 */

					try {
						link = "/loan/LoanApproval_editApplication.action";
						linkParam = "ittloanApplCode=" + applnID;
						template.sendProcessManagerAlert(newApprover, module,
								msgType, applnID, alertLevel, linkParam, link,
								"Pending", applicant, "", "", "", oldApprover);

					} catch (Exception e) {
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

					// template1.setEmailTemplate("LOAN - APPROVER TO
					// APPLICANT");
					template1
							.setEmailTemplate("LOAN APPLICATION FROM  APPROVER TO APPLICANT");
					template1.getTemplateQueries();

					/*
					 * EmailTemplateQuery templateQuery11 =
					 * template1.getTemplateQuery(1); //FROM EMAIL
					 * templateQuery11.setParameter(1, oldApprover);
					 * 
					 * EmailTemplateQuery templateQuery12 =
					 * template1.getTemplateQuery(2); //TO EMAIL
					 * templateQuery12.setParameter(1, applicant);
					 * 
					 * EmailTemplateQuery templateQuery13 =
					 * template1.getTemplateQuery(3);
					 * templateQuery13.setParameter(1, applnID);
					 * 
					 * EmailTemplateQuery templateQuery14 =
					 * template1.getTemplateQuery(4);
					 * templateQuery14.setParameter(1, oldApprover);
					 * 
					 * EmailTemplateQuery templateQuery15 =
					 * template1.getTemplateQuery(5);
					 * templateQuery15.setParameter(1, newApprover);
					 */

					EmailTemplateQuery templateQuery11 = template1
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery11.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery12 = template1
							.getTemplateQuery(2); // TO EMAIL
					templateQuery12.setParameter(1, applicant);

					EmailTemplateQuery templateQuery13 = template1
							.getTemplateQuery(3);
					templateQuery13.setParameter(1, applnID);

					EmailTemplateQuery templateQuery14 = template1
							.getTemplateQuery(4);
					templateQuery14.setParameter(1, applnID);

					EmailTemplateQuery templateQuery15 = template1
							.getTemplateQuery(5);
					templateQuery15.setParameter(1, applnID);

					EmailTemplateQuery templateQuery16 = template1
							.getTemplateQuery(6);
					templateQuery16.setParameter(1, applnID);

					EmailTemplateQuery templateQuery17 = template1
							.getTemplateQuery(7);
					templateQuery17.setParameter(1, applnID);

					EmailTemplateQuery templateQuery18 = template1
							.getTemplateQuery(8);
					templateQuery18.setParameter(1, applnID);

					template1.configMailAlert();
					/*try {
						template1.sendProcessManagerAlert(empID, module,
								msgType, applnID, alertLevel);
						template1.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}*/

					try {

						link = "/loan/LoanApplication_viewApplicationFunction.action";
						linkParam = "loanApplCode=" + applnID;

						alternateApprover = (empFlow != null && !String
								.valueOf(empFlow[0][3]).equals("0")) ? String
								.valueOf(empFlow[0][3]) : "";

						oldApprover = String.valueOf(empFlow[0][0]);

						template1.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								"Pending", applicant, alternateApprover, "",
								applicant, oldApprover);

					} catch (Exception e) {
						logger.error(e);
					}
					template1.sendApplicationMail();
					template1.clearParameters();
					template1.terminate();
				} else {

					empID = applicant;
					msgType = "A";

					String managerId = loanApproval.getInitiatorCode();
					// send alert from Approver to HR Admin

					LoanApprovalModel model = new LoanApprovalModel(); // creating
																		// an
																		// instance
																		// of
																		// LoanApprovalModel
																		// class
					model.initiate(context, session);

					String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
							+ loanApproval.getLoanCode();

					Object data[][] = model.getSqlModel().getSingleResult(
							hrQuery);

					EmailTemplateBody templateAppr = new EmailTemplateBody();
					templateAppr.initiate(context, session);

					templateAppr
							.setEmailTemplate("LOAN APPLICATION - APPROVER TO ADMIN");

					// template.setEmailTemplate("LOAN -FINAL APPROVER TO
					// APPLICANT");

					templateAppr.getTemplateQueries();

					EmailTemplateQuery template1 = templateAppr
							.getTemplateQuery(1); // FROM EMAIL
					template1.setParameter(1, oldApprover);
					EmailTemplateQuery template2 = templateAppr
							.getTemplateQuery(2); // TO EMAIL
					template2.setParameter(1, String.valueOf(data[0][1]));
					EmailTemplateQuery template3 = templateAppr
							.getTemplateQuery(3);
					template3.setParameter(1, applnID);

					EmailTemplateQuery template4 = templateAppr
							.getTemplateQuery(4);
					template4.setParameter(1, applnID);

					EmailTemplateQuery template5 = templateAppr
							.getTemplateQuery(5);
					template5.setParameter(1, applnID);

					EmailTemplateQuery template6 = templateAppr
							.getTemplateQuery(6);
					template6.setParameter(1, applnID);

					EmailTemplateQuery template7 = templateAppr
							.getTemplateQuery(7);
					template7.setParameter(1, applnID);

					templateAppr.configMailAlert();
					/*
					 * try { templateAppr.sendProcessManagerAlert(empID, module,
					 * msgType, applnID, alertLevel);
					 * templateAppr.sendApplicationMail(); } catch(Exception e) {
					 * logger.error(e); }
					 */

					try {
						link = "/loan/LoanApproval_editApplication.action";
						linkParam = "ittloanApplCode=" + applnID;

						templateAppr.sendProcessManagerAlert(String
								.valueOf(data[0][1]), module, msgType, applnID,
								alertLevel, linkParam, link, "Pending",
								applicant, "", "", "", oldApprover);

					} catch (Exception e) {
						logger.error(e);
					}

					if (!managerId.equals(applicant)) {
						templateAppr.sendApplicationMailToKeepInfo(managerId);
					}
					templateAppr.sendApplicationMail();
					templateAppr.clearParameters();
					templateAppr.terminate();

					// send alert from oldApprover to applicant

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("LOAN APPLICATION -FINAL APPROVER TO APPLICANT");

					// template.setEmailTemplate("LOAN -FINAL APPROVER TO
					// APPLICANT");

					template.getTemplateQueries();

					/*
					 * EmailTemplateQuery templateQuery1 =
					 * template.getTemplateQuery(1); //FROM EMAIL
					 * templateQuery1.setParameter(1, oldApprover);
					 * 
					 * EmailTemplateQuery templateQuery2 =
					 * template.getTemplateQuery(2); //TO EMAIL
					 * templateQuery2.setParameter(1, applicant);
					 * 
					 * EmailTemplateQuery templateQuery3 =
					 * template.getTemplateQuery(3);
					 * templateQuery3.setParameter(1, applnID);
					 * 
					 * EmailTemplateQuery templateQuery4 =
					 * template.getTemplateQuery(4);
					 * templateQuery4.setParameter(1, oldApprover);
					 */

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, applicant);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applnID);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applnID);

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applnID);

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, applnID);

					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, applnID);

					EmailTemplateQuery templateQuery10 = template
					.getTemplateQuery(10);
			templateQuery10.setParameter(1, loanApproval.getUserEmpId());
					
					template.configMailAlert();
					/*
					 * try { template.sendProcessManagerAlert(empID, module,
					 * msgType, applnID, alertLevel);
					 * template.sendApplicationMail(); } catch(Exception e) {
					 * logger.error(e); }
					 */

					try {

						link = "/loan/LoanApplication_viewApplicationFunction.action";
						linkParam = "loanApplCode=" + applnID;

						alternateApprover = (empFlow != null && !String
								.valueOf(empFlow[0][3]).equals("0")) ? String
								.valueOf(empFlow[0][3]) : "";

						oldApprover = String.valueOf(empFlow[0][0]);

						template.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								"Pending", applicant, alternateApprover, "",
								applicant, oldApprover);

					} catch (Exception e) {
						logger.error(e);
					}
					if (!managerId.equals(applicant)) {
						template.sendApplicationMailToKeepInfo(applicant);
						
					} 
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * INPUT METHOD
	 */

	public String input() {
		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session);
		////model.input(loanApproval, request, loanApproval.getUserEmpId());
		// reset();
		String userId = loanApproval.getUserEmpId();
		boolean isCurrentUser = model.isCurrentUser(userId,loanApproval);
		if(isCurrentUser) {
			model.getPendingList(loanApproval, request, userId);
		}

		model.terminate();
		loanApproval.setListType("pending");
		getNavigationPanel(6);
		return INPUT;
	}

	/**
	 * EDIT APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */

	public String editApplication() throws Exception {
		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class

		model.initiate(context, session);

		/**
		 * for mypage set source
		 */
		String source = request.getParameter("src");
		loanApproval.setSource(source);
		
		
		loanApproval.setBackButtonFlag("fromApprover");

		String requestID = request.getParameter("ittloanApplCode");
		boolean result = model.editApplication(loanApproval, requestID);

		String query = " SELECT ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER "
				+ "INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_CODE = HRMS_LOAN_MASTER.LOAN_CODE) "
				+ "WHERE HRMS_LOAN_MASTER.LOAN_CODE = "
				+ loanApproval.getLoanCode()
				+ " AND HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = " + requestID;
		Object queryObj[][] = model.getSqlModel().getSingleResult(query);
		if (queryObj != null && queryObj.length > 0) {
			if (String.valueOf(queryObj[0][0]).equals(
					loanApproval.getUserEmpId())) {
				loanApproval.setHiddenLoginfrmId("H");
			} else if (String.valueOf(queryObj[0][1]).equals(
					loanApproval.getUserEmpId())) {
				loanApproval.setHiddenLoginfrmId("ACC");
			} else {
				loanApproval.setHiddenLoginfrmId("");
			}

		}
System.out.println("EMP CODE::::::::::::::::"+loanApproval.getEmpCode());
		setApproverList(loanApproval.getEmpCode()); // setting approver list

		String status = loanApproval.getApplicationStatus();

		model.setLoanList(loanApproval, "Y");
		model.getAmountLimit(loanApproval);
		model.getApproverComments(loanApproval, requestID);
		model.terminate();
		try {
			// setApproverList(loanApproval.getEmpCode());
		} catch (Exception e) {
			// TODO: handle exception
		}

		/* String[] status=request.getParameterValues("status"); */
		if (status != null) {
			if (status.equals("RR") || status.equals("AA")
					|| status.equals("R") || status.equals("B")) {
				getNavigationPanel(2);
				loanApproval.setEnableAll("N");
				loanApproval.setApproverCommentsFlag(false);
			} else {
				loanApproval.setHrFlag(true);
				loanApproval.setApproverCommentsFlag(true);
				getNavigationPanel(4);
				loanApproval.setEnableAll("N");
			}
		}

		return "pageDtls";
	}

	/**
	 * View APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */

	public String viewApplication() throws Exception {
		LoanApprovalModel model = new LoanApprovalModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session);

		loanApproval.setBackButtonFlag("fromApprover");

		String requestID = request.getParameter("ittloanApplCode");
		boolean result = model.editApplication(loanApproval, requestID);

		setApproverList(loanApproval.getEmpCode()); // setting approver list

		String status = loanApproval.getApplicationStatus();

		// model.setLoanList(loanApproval, "N");
		model.getAmountLimit(loanApproval);
		model.getApproverComments(loanApproval, requestID);
		model.terminate();
		try {
			// setApproverList(loanApproval.getEmpCode());
		} catch (Exception e) {
			// TODO: handle exception
		}

		getNavigationPanel(2);
		loanApproval.setEnableAll("N");
		loanApproval.setApproverCommentsFlag(false);

		return "pageDtls";
	}

	public void setApproverList(String empCode) {
		try {
			// bean.setFirstApproverCode("");
			LoanApprovalModel model = new LoanApprovalModel(); // creating an
																// instance of
																// LoanApprovalModel
																// class
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			if (!empCode.equals("")) {
				Object[][] empFlow = model1.generateEmpFlow(empCode, "Loan");
				model.setApproverData(loanApproval, empFlow);
			}
			model.setLoanList(loanApproval, "Y");
			model.getAmountLimit(loanApproval);

			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME "
				+ "  , EMP_ID" + " FROM HRMS_EMP_OFFC ";

		query += "	ORDER BY EMP_ID ASC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "searchEmpToken", "searchEmpName",
				"searchEmpCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

	public String search() {
		try {
			LoanApprovalModel model = new LoanApprovalModel(); // creating an
																// instance of
																// LoanApprovalModel
																// class
			model.initiate(context, session);

			if (loanApproval.getFlag().equals("")) {
				loanApproval.setHeaderName("Pending Application");

			} else if (loanApproval.getFlag().equals("RR")) {
				loanApproval.setHeaderName("Rejected Application");
			} else if (loanApproval.getFlag().equals("AA")) {
				loanApproval.setHeaderName("Approved Application");
			}

			model.input(loanApproval, request, loanApproval.getUserEmpId());

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String sendBackApplication() {
		try {
			String applicationId = request.getParameter("loanCodeVal");// application
																		// code

			LoanApprovalModel model = new LoanApprovalModel(); // creating an
																// instance of
																// LoanApprovalModel
																// class
			model.initiate(context, session);

			String empCode = loanApproval.getEmpCode(); // employee code

			// String status = loanApproval.getApplicationStatus(); //status
			// selected by approver
			String comments = loanApproval.getComment(); // comments given by
															// approver
			// logger.info("status---------"+status);

			String apprAmount = request.getParameter("apprAmount");
			String apprStatus = request.getParameter("status");
			String level = request.getParameter("levelVal"); // application
																// level

			Object[][] empFlow = null;

			empFlow = generateEmpFlow(empCode, "Loan",
					Integer.parseInt(level) + 1);

			String status = model.sendBack(loanApproval, applicationId,
					empCode, comments, apprAmount, apprStatus,level);
			String applstatus = "";
			if (apprStatus.equals("B")) {
				applstatus = "Send Back";
			} else if (apprStatus.equals("R")) {
				applstatus = "Rejected";
			}

			addActionMessage("Application " + applstatus + " successfully.");

			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(applicationId);
			String module = "Loan";

			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = String.valueOf(empCode);
				oldApprover = loanApproval.getUserEmpId();
				newApprover = "";
			} catch (Exception e) {
				e.printStackTrace();
			}
			String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
			sendRejectApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, alertLevel, applstatus, level, empFlow);
			// ------------------------Process Manager
			// Alert------------------------end

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * input(); return INPUT;
		 */

		return "mymessages";

	}

	private void sendRejectApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel, String applstatus, String level,
			Object[][] empFlow) {

		try {
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Loan";
			processAlerts.removeProcessAlert(String.valueOf(applnID), prmodule);
			String alternateApprover = "";
			String link = "";
			String linkParam = "";
			String actualStataus = "";

			if (applstatus.equals("Send Back")) {
				actualStataus = "Send Back";
			}
			if (applstatus.equals("Rejected")) {
				actualStataus = "Rejected";
			}

			String keepInformedId = " SELECT DISTINCT  LOAN_APPROVER_CODE FROM HRMS_LOAN_PATH WHERE LOAN_APPL_CODE ="
					+ applnID
					+ " AND LOAN_APPROVER_CODE NOT IN ("
					+ loanApproval.getUserEmpId() + ")";

			LoanApprovalModel model = new LoanApprovalModel();
			model.initiate(context, session);

			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			
			
			// send alert from oldApprover to applicant

			String InititorId = loanApproval.getInitiatorCode();

			String empID = "", msgType = "";

			empID = applicant;
			msgType = "I";

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template
					.setEmailTemplate("LOAN APPLICATION  SENDBACK/REJECT FROM APPROVER TO APPLICANT");
			// template.setEmailTemplate("LOAN - APPROVER REJECT");

			template.getTemplateQueries();

			/*
			 * EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			 * //FROM EMAIL templateQuery1.setParameter(1, oldApprover);
			 * 
			 * EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			 * //TO EMAIL templateQuery2.setParameter(1, applicant);
			 * 
			 * EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			 * templateQuery3.setParameter(1, applnID);
			 * 
			 * EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			 * templateQuery4.setParameter(1, oldApprover);
			 */

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
																				// EMAIL
			templateQuery1.setParameter(1, oldApprover);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery2.setParameter(1, applicant);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, oldApprover);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applnID);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applnID);

			EmailTemplateQuery templateQuery10 = template
			.getTemplateQuery(10);
	templateQuery10.setParameter(1, loanApproval.getUserEmpId());
	
			template.configMailAlert();
			/*
			 * try { template.sendProcessManagerAlert(empID, module, msgType,
			 * applnID, alertLevel); template.sendApplicationMail(); }
			 * catch(Exception e) { logger.error(e); }
			 */

			if (applstatus.equals("Send Back")) {
				actualStataus = "Send Back";

				link = "/loan/LoanApplication_editApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;

				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
				}

				template.sendProcessManagerAlert(applicant, module, "A",
						applnID, alertLevel, linkParam, link, actualStataus,
						applicant, "", "", applicant, oldApprover);

				link = "/loan/LoanApplication_viewApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;
				
				template.sendProcessManagerAlert(oldApprover, module, "I",
						applnID, alertLevel, linkParam, link, actualStataus,
						applicant, alternateApprover, ccId, loanApproval
								.getApproverCode(), oldApprover);

				if (keepInformedObj != null && keepInformedObj.length > 0) {
					// keepInfo = String.valueOf(keepInformedObj[0][0]);
					template.sendApplicationMailToKeepInfo(ccId);
				}

			} else {
				actualStataus = "Reject";

				link = "/loan/LoanApplication_viewApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;

				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
				}
				/*template.sendProcessManagerAlert(applicant, module, "I",
						applnID, alertLevel, linkParam, link, actualStataus,
						applicant, alternateApprover, ccId, loanApproval
								.getApproverCode(), oldApprover);*/
				
				template.sendProcessManagerAlert(oldApprover, module,
						"I", applnID, alertLevel, linkParam, link,
						actualStataus, applicant, "",
						ccId, applicant,oldApprover);
				
				if (keepInformedObj != null && keepInformedObj.length > 0) {
					// keepInfo = String.valueOf(keepInformedObj[0][0]);
					template.sendApplicationMailToKeepInfo(ccId);
				}

			}
			
			/*try {

				link = "/loan/LoanApplication_viewApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;

				alternateApprover = (empFlow != null && !String.valueOf(
						empFlow[0][3]).equals("0")) ? String
						.valueOf(empFlow[0][3]) : "";
						
						System.out.println("alternateApprover=New=="+alternateApprover);

				oldApprover = String.valueOf(empFlow[0][0]);
				
				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
					System.out.println("ccId--ganesh-1NEW--AAAAA----------"+ccId);
					
					
					template.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, alertLevel, linkParam, link, actualStataus,
							applicant, alternateApprover, ccId, loanApproval
									.getApproverCode(), oldApprover);

					System.out.println("ccId--ganesh-2-NEW---AAAA--------"+ccId);
					if (keepInformedObj != null && keepInformedObj.length > 0) {
						// keepInfo = String.valueOf(keepInformedObj[0][0]);
						template.sendApplicationMailToKeepInfo(ccId);
					}
					
				}
				
			} catch (Exception e) {
				logger.error(e);
			}*/

			if (!InititorId.equals(applicant)) {
				template.sendApplicationMailToKeepInfo(InititorId);
			}

			template.sendApplicationMail();

			template.clearParameters();
			template.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**This function is used to show approved list.
	 * @return INPUT
	 * @throws Exception : IO Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			LoanApprovalModel model = new LoanApprovalModel(); // creating an
			// instance of
			// LoanApprovalModel
			// class
			model.initiate(context, session);
			String userId = loanApproval.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId,loanApproval);
			if(isCurrentUser) {
				model.getApprovedList(loanApproval, request, userId);
			}
			loanApproval.setListType("approved");
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		
		return INPUT;
	}
	/**This function is used to go list page.
	 * @return INPUT
	 * @throws Exception : IO Exception
	 */
	
	public String callBack() throws Exception {
		String listType = loanApproval.getListType();
		loanApproval.setHiddenCode("");
		if(listType.equals("pending")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else if(listType.equals("rejected")) {
			getRejectedList();
		} 
		return INPUT;
	}
	
	/**This function is used to show rejected list.
	 * @return INPUT
	 * @throws Exception : IO Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			LoanApprovalModel model = new LoanApprovalModel(); // creating an
			// instance of
			// LoanApprovalModel
			// class
			model.initiate(context, session);
			String userId = loanApproval.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId,loanApproval);
			if(isCurrentUser) {
				model.getRejectedList(loanApproval, request, userId);
			}
			loanApproval.setListType("rejected");
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		
		return INPUT;
	}

}
