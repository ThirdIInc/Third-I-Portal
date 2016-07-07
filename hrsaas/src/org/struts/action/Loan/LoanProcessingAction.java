package org.struts.action.Loan;

import org.paradyne.bean.Loan.LoanProcessing;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.loan.LoanProccessModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj 13-07-2008 modified @ 12/12/2011 LoanProcessingAction class
 *         to save, update, delete and view loan processing details for the
 *         selected application
 */
public class LoanProcessingAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanProcessingAction.class);
	LoanProcessing loanProcess;

	public LoanProcessing getLoanProcess() {
		return loanProcess;
	}

	public void setLoanProcess(LoanProcessing loanProcess) {
		this.loanProcess = loanProcess;
	}

	@Override
	public void prepare_local() throws Exception {
		loanProcess = new LoanProcessing();
		String loanApplFlag = request.getParameter("loanAppl");
		try {
			if (loanApplFlag.equals("true")) {
				loanProcess.setMenuCode(357);
			} else
				loanProcess.setMenuCode(358);
		} catch (Exception e) {
			loanProcess.setMenuCode(358);
		}
		// TODO Auto-generated method stub

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		model.setPfDetails(loanProcess);
		model.checkPFTFlag(loanProcess);
		model.terminate();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return loanProcess;
	}

	/*
	 * method name : grossSalary purpose : to retrieve the gross salary of the
	 * employee and set that value in respective field return type : String
	 * parameter : none
	 */
	public String getApplicationDetails() {
		LoanProccessModel model = new LoanProccessModel();

		model.initiate(context, session);
		model.grossSalary(loanProcess);
		model.setPfDetails(loanProcess);
		model.checkPFTFlag(loanProcess);
		if (loanProcess.getPfTrustFlag().equals("true")) {
			loanProcess.setPfBalance(model.calPfBalance(loanProcess));
		}
		resetProccessDetail();
		model.loanApproverCommentList(loanProcess);
		model.terminate();

		return SUCCESS;
	}

	/*
	 * method name : f9LoanApplication purpose : to show all the details for the
	 * selected application return type : String parameter : none
	 */
	public String f9LoanApplication() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT LOAN_APPL_CODE, EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_EMP_ID, CENTER_NAME, DEPT_NAME, "
				+ " NVL(LOAN_AMOUNT,0),LOAN_CODE,DECODE(LOAN_PF_TYPE,'Y','Yes','N','No','','Yes'),DECODE(LOAN_PF_TYPE,'Y','true','N','false','','true') "
				+ " FROM HRMS_LOAN_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
				+ " LEFT JOIN HRMS_LOAN_MASTER ON(HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE LOAN_APPL_STATUS='A' AND LOAN_APPL_CODE NOT IN(SELECT LOAN_APPL_CODE "
				+ " FROM HRMS_LOAN_PROCESS) " + " ORDER BY LOAN_APPL_CODE  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("code"), getMessage("employee.id"),
				getMessage("employee"), getMessage("loanType"),
				getMessage("dateApp") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "5", "20", "40", "20", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "loanAppCode", "empToken", "empName",
				"loanType", "appdate", "empCode", "branchName", "deptName",
				"loanAmount", "loanTypeCode", "loanRefundable",
				"loanRefundableFlag" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanProcessing_getApplicationDetails.action"; // LoanProcessing_showApplDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * method name : f9LoanProcess purpose : to show all the details for the
	 * selected application return type : String parameter : none
	 */
	public String f9LoanProcess() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_LOAN_PROCESS.LOAN_APPL_CODE, EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||"
				+ "HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME, LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), "
				+ "LOAN_EMP_ID, NVL(LOAN_AMOUNT,0),LOAN_CODE,DECODE(LOAN_PF_TYPE,'Y','Yes','N','No','','Yes'),DECODE(LOAN_PF_TYPE,'Y','true','N','false','','true') "
				+ "FROM HRMS_LOAN_PROCESS "
				+ "INNER JOIN HRMS_LOAN_APPLICATION ON(HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) "
				+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
				+ "LEFT JOIN HRMS_LOAN_MASTER ON(HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
				+ "ORDER BY LOAN_APPL_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("code"), getMessage("employee.id"),
				getMessage("employee"), getMessage("loanType"),
				getMessage("dateApp") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "5", "20", "40", "20", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "loanAppCode", "empToken", "empName",
				"loanType", "appdate", "empCode", "loanAmount", "loanTypeCode",
				"loanRefundable", "loanRefundableFlag" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanProcessing_showProccessDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * method name : f9SanctionedBy purpose : to select an employee return type :
	 * String parameter : none
	 */
	public String f9SanctionedBy() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME|| ' ' || EMP_LNAME), "
				+ " EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND EMP_ID !="
				+ loanProcess.getEmpCode() + " ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "15", "85" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "sancPersonToken", "sancPersonName",
				"sancPersonCode" };

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
	}

	/*
	 * method name : showApplDetails purpose : to show all the details for the
	 * selected application return type : String parameter : none
	 */
	public String showApplDetails() {
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		model.showApplDetails(loanProcess);
		// loanProcess.setView("false");
		resetProccessDetail();
		model.terminate();
		return SUCCESS;
	}

	/*
	 * method name : showApplDetails purpose : to show all the loan processing
	 * details for the selected application return type : String parameter :
	 * none
	 */
	public String showProccessDetails() {

		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		model.setPfDetails(loanProcess);
		////model.checkPFTFlag(loanProcess);
		if (loanProcess.getPfTrustFlag().equals("true")) {
			loanProcess.setPfBalance(model.calPfBalance(loanProcess));
		}
		model.setLoanList(loanProcess, "Y");
		model.showProccessDetails(loanProcess);
		model.showInstallmentSchedule(loanProcess, request);
		model.showPrePayemntDetails(loanProcess);
		loanProcess.setView("true");
		String lable = "";
		if (!(loanProcess.getInterestType().equals("R")))
			lable = "Principal Amount";
		else
			lable = "Principal Reduction";
		request.setAttribute("lable", lable);

		model.loanApproverCommentList(loanProcess);

		model.terminate();
		return SUCCESS;
	}

	/*
	 * method name : showApplDetails purpose : to show guarantor details for the
	 * selected application return type : String parameter : none
	 */
	public String showGuarantor() {
		logger.info("inside the showGuarantor method");
		loanProcess.setGuarantorFlag("true");
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		model.showGuarantor(loanProcess);
		model.terminate();
		return "guarnatorPage";
	}

	/*
	 * method name : reset purpose : to reset all the form fields and set all
	 * values to empty strings return type : String parameter : none
	 */
	public String reset() {
		loanProcess.setLoanAppCode("");
		loanProcess.setLoanAmount("");
		loanProcess.setLoanType("");
		loanProcess.setAppdate("");
		loanProcess.setBankName("");
		loanProcess.setBranchName("");
		loanProcess.setChequeNumber("");
		loanProcess.setDeptName("");
		loanProcess.setEmpCode("");
		loanProcess.setEmpName("");
		loanProcess.setEmpToken("");
		loanProcess.setInstallmentNumber("");
		loanProcess.setInterestRate("");
		loanProcess.setInterestType("");
		loanProcess.setPaymentDate("");
		loanProcess.setPaymentMode("");
		loanProcess.setSancPersonCode("");
		loanProcess.setSancPersonName("");
		loanProcess.setSancPersonToken("");
		loanProcess.setSanctionAmount("");
		loanProcess.setSanctionDate("");
		loanProcess.setSecurityDetails("");
		loanProcess.setSecurityValue("");
		loanProcess.setStartingDate("");
		loanProcess.setSubmissionDate("");
		loanProcess.setStartingMonth("");
		loanProcess.setStartingYear("");
		loanProcess.setInstallmentFlag("false");
		loanProcess.setView("false");
		loanProcess.setGrossSalary("");
		loanProcess.setInstallmentNumberFlat("");
		loanProcess.setEmiAmount("");
		loanProcess.setMonthlyPrincAmount("");
		loanProcess.setInstallmentPaidFlag("false");
		loanProcess.setPrePaymentFlag("false");

		loanProcess.setPfTrustFlag("false");
		loanProcess.setPfBalance("");
		loanProcess.setMinLoanSanctAmt("");
		loanProcess.setMaxLoanSanctAmt("");
		loanProcess.setLoanRefundable("Y");
		loanProcess.setHiddenCalType("I");
		loanProcess.setLoanRefundableFlag("true");

		return SUCCESS;
	}

	/*
	 * method name : reset purpose : to reset all the form fields and set all
	 * values to empty strings return type : String parameter : none
	 */
	public String resetProccessDetail() {
		// loanProcess.setLoanAmount("");
		// loanProcess.setLoanType("");
		loanProcess.setBankName("");
		// loanProcess.setBranchName("");
		loanProcess.setChequeNumber("");
		loanProcess.setInstallmentNumber("");
		loanProcess.setEmiAmount("");
		loanProcess.setMonthlyPrincAmount("");
		loanProcess.setInterestRate("");
		loanProcess.setInterestType("");
		loanProcess.setPaymentDate("");
		loanProcess.setPaymentMode("");
		loanProcess.setSancPersonCode("");
		loanProcess.setSancPersonName("");
		loanProcess.setSancPersonToken("");
		loanProcess.setSanctionAmount("");
		loanProcess.setSanctionDate("");
		loanProcess.setSecurityDetails("");
		loanProcess.setSecurityValue("");
		loanProcess.setStartingDate("");
		loanProcess.setSubmissionDate("");
		loanProcess.setStartingMonth("");
		loanProcess.setStartingYear("");
		loanProcess.setInstallmentFlag("false");
		loanProcess.setView("false");

		return SUCCESS;
	}

	/*
	 * method name : save purpose : to add new record or to modify the existing
	 * record return type : String parameter : none
	 */
	public String save() throws Exception {
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		String[] monthYear = request.getParameterValues("monthYear");
		String[] monthNo = request.getParameterValues("monthNo");
		String[] princAmt = request.getParameterValues("principalAmt");
		String[] inteAmt = request.getParameterValues("interestAmt");
		String[] installAmt = request.getParameterValues("installmentAmt");
		String[] balancePrincAmt = request
				.getParameterValues("balancePrincipalAmt");
		String[] isPaid = request.getParameterValues("checkFlag");

		String applicationId = request.getParameter("loanCodeVal");// application
																	// code
		String empCode = loanProcess.getEmpCode(); // employee code

		// String status = loanApproval.getApplicationStatus(); //status
		// selected by approver
		String comments = loanProcess.getComment(); // comments given by
													// approver
		// logger.info("status---------"+status);
		String status = request.getParameter("status");

		String apprAmount = request.getParameter("apprAmount");

		boolean result = false;

		/*
		 * for (int i = 0; i < isPaid.length; i++) { logger.info("isPAid
		 * flag["+i+"]=="+isPaid[i]); } //end of for loop
		 */if (loanProcess.getView().equals("false")) {
			result = model.save(loanProcess, monthYear, princAmt, inteAmt,
					installAmt, balancePrincAmt, isPaid, status, applicationId,
					empCode, comments, apprAmount,monthNo);
			if (result) {
				addActionMessage(getMessage("save"));

				// ------------------------Process Manager
				// Alert------------------------start
				String applnID = String.valueOf(applicationId);
				String module = "Loan";

				String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = String.valueOf(empCode);
					oldApprover = loanProcess.getUserEmpId();

				} catch (Exception e) {
					logger.error(e);
				}
				// String alertLevel = String.valueOf(Integer.parseInt(level) +
				// 1);
				sendAccountantAlert(applnID, module, applicant, oldApprover,
						newApprover, status);
				// ------------------------Process Manager
				// Alert------------------------end

				reset();
			} // end of if
			else
				addActionMessage("Record can not be saved.");
		} // end of if
		else if (loanProcess.getView().equals("true")) {
			result = model.update(loanProcess, monthYear, princAmt, inteAmt,
					installAmt, balancePrincAmt, isPaid, status, applicationId,
					empCode, comments, apprAmount,monthNo);
			if (result) {
				addActionMessage(getMessage("update"));
				reset();
			} // end of if
			else
				addActionMessage("Record can not be updated.");
		} // end of else
		model.terminate();
		/*
		 * input(); return INPUT;
		 */
		///return "mymessages";
		if (loanProcess.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			String listType = loanProcess.getListType();
			if(listType.equals("pending")) {
				input();
			} else if(listType.equals("approved")) {
				getApprovedList();
			} 
			return INPUT;
			////return input();
		}
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	private void sendAccountantAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String status) {

		/**
		 * Remove process manager entry from mypage.
		 */
		MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
		processAlerts.initiate(context, session);
		String prmodule = "Loan";
		processAlerts.removeProcessAlert(String.valueOf(applnID), prmodule);

		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);

		String link = "";
		String linkParam = "";

		String initiatorId = loanProcess.getInitiatorCode();
		String keepInformedId = " SELECT DISTINCT  LOAN_APPROVER_CODE FROM HRMS_LOAN_PATH WHERE LOAN_APPL_CODE ="
				+ applnID
				+ " AND LOAN_APPROVER_CODE NOT IN ("
				+ loanProcess.getUserEmpId() + ")";

		Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
				keepInformedId);
		// send alert from oldApprover to applicant

		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);

		template.setEmailTemplate("LOAN APPLICATION - ACCOUNTANT TO APPLICANT");

		// template.setEmailTemplate("LOAN -FINAL APPROVER TO APPLICANT");

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

		template.configMailAlert();
		/*
		 * try { template.sendProcessManagerAlert(empID, module, msgType,
		 * applnID, alertLevel); template.sendApplicationMail(); }
		 * catch(Exception e) { logger.error(e); }
		 */

		/*
		 * try {
		 * 
		 * link = "/loan/LoanProcessing_viewApplication.action"; linkParam =
		 * "ittloanApplCode=" + applnID;
		 * 
		 * 
		 * 
		 * template.sendProcessManagerAlert(oldApprover, module, "I", applnID,
		 * "", linkParam, link, "Approved", applicant, "", "",
		 * applicant,oldApprover);
		 *  } catch (Exception e) { logger.error(e); }
		 */

		try {

			link = "/loan/LoanApplication_viewApplicationFunction.action";
			linkParam = "loanApplCode=" + applnID;

			String ccId = "";

			if (keepInformedObj != null && keepInformedObj.length > 1) {

				for (int i = 0; i < keepInformedObj.length; i++) {
					ccId += String.valueOf(keepInformedObj[i][0]) + ",";
				}
				ccId = ccId.substring(0, ccId.length() - 1);
			}

			template.sendProcessManagerAlert(oldApprover, module, "I", applnID,
					"", linkParam, link, "Approved", applicant, initiatorId,
					ccId, applicant, oldApprover);

			if (keepInformedObj != null && keepInformedObj.length > 0) {
				// keepInfo = String.valueOf(keepInformedObj[0][0]);
				template.sendApplicationMailToKeepInfo(ccId);
			}

		} catch (Exception e) {
			logger.error(e);
		}

		template.sendApplicationMail();
		template.clearParameters();
		template.terminate();

	}

	/*
	 * method name : generateInstallmentSch purpose : to generate the
	 * installment schedule as per the given information return type : String
	 * parameter : none
	 */
	public String generateInstallmentSch() {
		LoanProccessModel model = new LoanProccessModel();
		String lable = "";
		boolean result = false;

		model.initiate(context, session);

		if (loanProcess.getHiddenCalfrmId().equals("E")) {
			result = model.installmentScheduleForEMI(loanProcess, request);

		} else if (loanProcess.getHiddenCalfrmId().equals("P")) {
			result = model
					.installmentScheduleForPrincipal(loanProcess, request);
		} else {
			result = model.generateInstallmentSchedule(loanProcess, request);
		}
		if (loanProcess.getInstallmentFlag().equals("true")) {
			if (!(loanProcess.getInterestType().equals("R")))
				lable = "Principal Amount";
			else
				lable = "Principal Reduction";

			request.setAttribute("lable", lable);

		} else if (loanProcess.getInstallmentFlag().equals("false")) {
			if (loanProcess.getHiddenCalfrmId().equals("E")) {
				addActionMessage("Please check the EMI amount");
			}

		}

		model.setLoanList(loanProcess, "Y");

		loanProcess.setHiddenCalfrmId(loanProcess.getHiddenCalfrmId());
		model.loanApproverCommentList(loanProcess);

		setApproverList(loanProcess.getEmpCode()); // setting approver list

		loanProcess.setEnableAll("Y");
		loanProcess.setAccountantFlag(true);
		loanProcess.setHrFlag(false);
		loanProcess.setApproverCommentsFlag(true);
		
		String requestID = request.getParameter("ittloanApplCode");
		//for showing applicant comment and EMI Type
		model.viewApplicantDetails(loanProcess, requestID);
		String status= loanProcess.getApplicationStatus();
		if (status.equals("RR") || status.equals("A") || status.equals("R")
				|| status.equals("B")) {

		///	loanProcess.setEnableAll("N");
			getNavigationPanel(2);
		///	loanProcess.setApproverCommentsFlag(false);
		} else {

			//loanProcess.setApproverCommentsFlag(false);
			getNavigationPanel(4);
		}
		
		//getNavigationPanel(4);
		model.terminate();
		return "pageDtls";
	}

	/*
	 * method name : report purpose : to generate the report for the selected
	 * application return type : String parameter : none
	 */
	public String report() {
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		model.getReport(loanProcess, request, response);
		model.terminate();
		return null;
	}

	public String openCalEMI() {
		String sancAmount = request.getParameter("loanAmt");
		request.setAttribute("loanAppl", "true");
		logger.info("hiddenCalType==" + loanProcess.getHiddenCalType());
		loanProcess.setSanctionAmount(sancAmount);
		return "calculateEMI";
	}

	public String calculateEMI() {
		loanProcess.setStartingDate("01-01-2009");
		loanProcess.setPaymentDate("01-01-2009");
		logger.info("hiddenCalType in calculate=="
				+ loanProcess.getHiddenCalType());
		generateInstallmentSch();
		return "calculateEMI";
	}

	/*
	 * method name : delete purpose : to delete the selected record return type :
	 * String parameter : none
	 */
	public String delete() {
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		if (model.delete(loanProcess)) {
			addActionMessage(getMessage("delete"));
			reset();
		} // end of if
		else {
			addActionMessage("Record can't be deleted");
		} // end of else
		model.terminate();
		return SUCCESS;
	}

	/**
	 * INPUT METHOD
	 */

	public String input() {
		LoanProccessModel model = new LoanProccessModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session);
		//model.input(loanProcess, request);
		
		String userId = loanProcess.getUserEmpId();
		boolean isCurrentUser = model.isCurrentUser(userId,loanProcess);
		if(isCurrentUser) {
			model.getPendingList(loanProcess, request, userId);
		}

		
		loanProcess.setListType("pending");
		
		model.terminate();
		// reset();
		/*if (loanProcess.getFlag().equals("")) {
			loanProcess.setHeaderName("Pending Application");
		} else if (loanProcess.getFlag().equals("RR")) {
			loanProcess.setHeaderName("Rejected Application");
		} else if (loanProcess.getFlag().equals("AA")) {
			loanProcess.setHeaderName("Approved Application");
		}*/
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
		try {
			LoanProccessModel model = new LoanProccessModel(); // creating an
																// instance of
																// LoanProccessModel
																// class
			model.initiate(context, session);
			
			/**
			 * for mypage set source
			 */
			String source = request.getParameter("src");
			loanProcess.setSource(source);
			
			loanProcess.setBackButtonFlag("fromHr");
			String requestID = request.getParameter("ittloanApplCode");

			boolean result = model.editApplication(loanProcess, requestID);

			String query = " SELECT ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER "
					+ "INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_CODE = HRMS_LOAN_MASTER.LOAN_CODE) "
					+ "WHERE HRMS_LOAN_MASTER.LOAN_CODE = "
					+ loanProcess.getLoanCode()
					+ " AND HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = "
					+ requestID;
			Object queryObj[][] = model.getSqlModel().getSingleResult(query);
			if (queryObj != null && queryObj.length > 0) {
				if (String.valueOf(queryObj[0][0]).equals(
						loanProcess.getUserEmpId())) {
					loanProcess.setHrFlag(true);
					loanProcess.setHiddenLoginfrmId("H");
					loanProcess.setAccountantFlag(false);

				} else if (String.valueOf(queryObj[0][1]).equals(
						loanProcess.getUserEmpId())) {
					loanProcess.setHiddenLoginfrmId("A");
					loanProcess.setEnableAll("Y");
					loanProcess.setHrFlag(false);
					loanProcess.setAccountantFlag(true);
				}

			}
			setApproverList(loanProcess.getEmpCode()); // setting approver list

			String status = loanProcess.getApplicationStatus();
			model.setLoanList(loanProcess, "Y");
			model.getAmountLimit(loanProcess);
			model.getApproverComments(loanProcess, requestID);
			model.terminate();
			try {
				// setApproverList(loanApproval.getEmpCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			/* String[] status=request.getParameterValues("status"); */

			if (status.equals("RR") || status.equals("AA")
					|| status.equals("R") || status.equals("B")) {
				getNavigationPanel(2);
				loanProcess.setEnableAll("N");
				loanProcess.setApproverCommentsFlag(false);
			} else {

				loanProcess.setApproverCommentsFlag(true);
				getNavigationPanel(4);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// loanProcess.setEnableAll("N");
		return "pageDtls";
	}

	/**
	 * EDIT APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */

	public String viewApplication() throws Exception {
		try {
			LoanProccessModel model = new LoanProccessModel(); // creating an
																// instance of
																// LoanProccessModel
																// class
			model.initiate(context, session);
			loanProcess.setBackButtonFlag("fromHr");
			String requestID = request.getParameter("ittloanApplCode");

			boolean result = model.viewApplication(loanProcess, requestID);
			showProccessDetails();

			setApproverList(loanProcess.getEmpCode()); // setting approver list

			String status = loanProcess.getApplicationStatus();
			model.setLoanList(loanProcess, "Y");
		////	model.getAmountLimit(loanProcess);
			model.getApproverComments(loanProcess, requestID);

			try {
				// setApproverList(loanApproval.getEmpCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			/* String[] status=request.getParameterValues("status"); */

			loanProcess.setAccountantFlag(true);
			loanProcess.setEnableAll("Y");
			getNavigationPanel(2);

			if (status.equals("RR") || status.equals("A") || status.equals("R")
					|| status.equals("B")) {

				
				getNavigationPanel(2);
				loanProcess.setApproverCommentsFlag(true);
			} else {

				loanProcess.setApproverCommentsFlag(false);
				getNavigationPanel(4);
			}

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setApproverList(String empCode) {
		try {
			// bean.setFirstApproverCode("");
			LoanProccessModel model = new LoanProccessModel(); // creating an
																// instance of
																// LoanProccessModel
																// class
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			if (!empCode.equals("")) {
				Object[][] empFlow = model1.generateEmpFlow(empCode, "Loan");
				model.setApproverData(loanProcess, empFlow);
			}
			model.setLoanList(loanProcess, "Y");
		//	model.getAmountLimit(loanProcess);

			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	/**
	 * @method name : saveApproval
	 * @purpose : to change the status of the selected application or to forward
	 *          the application to the next approver
	 * @return type : String
	 * @parameter : none
	 */
	public String saveHrApproval() {

		boolean result = false;

		LoanProccessModel model = new LoanProccessModel(); // creating an
															// instance of
															// LoanApprovalModel
															// class
		model.initiate(context, session); // initialize the LoanApprovalModel
											// class

		String appStatus = loanProcess.getLoanAppStatus();
		String loanCode = request.getParameter("loanCodeVal");// application
																// code
		String empCode = loanProcess.getEmpCode(); // employee code
		String level = request.getParameter("levelVal"); // application level
		// String status = loanApproval.getApplicationStatus(); //status
		// selected by approver
		String comments = loanProcess.getComment(); // comments given by
													// approver
		// logger.info("status---------"+status);

		String status = request.getParameter("status");
		loanProcess.setHiddenStatus(appStatus);

		String apprAmount = request.getParameter("apprAmount");

		String applnStatus = "";

		// for(int i=0;i<status.length(); i++){

		if (!(status.equals("P"))) { // if status is not P
			if (status.equals("A")) { // and if status is A

				/**
				 * call changeApplStatus(loanApproval, empFlow,
				 * String.valueOf(loanCode[i])) method of LoanApprovalModel
				 * class to change the status of the selected application in
				 * HRMS_LOAN_APPLICATION table
				 */
				applnStatus = model.changeApplStatus(loanProcess, String
						.valueOf(loanCode), empCode);
				result = true;
			} // end of if statement

			/**
			 * call forward(loanApproval, status[i], loanCode[i], empCode[i],
			 * comments[i]) method of LoanApprovalModel class to change the
			 * status of the selected application in HRMS_LOAN_APPLICATION table
			 * and to insert an entry related to approver details in
			 * HRMS_LOAN_PATH table
			 */
			applnStatus = model.forward(loanProcess, status, loanCode, empCode,
					comments, apprAmount);
			result = true;

			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(loanCode);
			String module = "Loan";

			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = String.valueOf(empCode);
				oldApprover = loanProcess.getUserEmpId();

			} catch (Exception e) {
				logger.error(e);
			}
			// String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
			sendApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, applnStatus);
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
		// model.showApplications(loanProcess);
		model.terminate(); // terminate the LoanApprovalModel class

		// return "success";
		///return "mymessages";
		if (loanProcess.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "successApprov";
		}
	}

	private void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String applnStatus) {
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

			LoanProccessModel model = new LoanProccessModel();
			model.initiate(context, session);

			

			String empID = "", msgType = "";
			if (applnStatus.equals("rejected")) {
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
		templateQuery10.setParameter(1, loanProcess.getUserEmpId());

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

					template.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, "", linkParam, link, "Pending", applicant,
							alternateApprover, "", applicant, oldApprover);

				} catch (Exception e) {
					logger.error(e);
				}

				template.clearParameters();
				template.terminate();
			} else {
				
				String keepInformedId = " SELECT DISTINCT LOAN_APPROVER_CODE FROM HRMS_LOAN_PATH WHERE LOAN_APPL_CODE ="
					+ applnID
					+ " AND LOAN_APPROVER_CODE NOT IN ("
					+ loanProcess.getUserEmpId() + ")";

			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);

				empID = applicant;
				msgType = "A";
				String InititorId = loanProcess.getInitiatorCode();

				// send alert from HR Admin to Accountant

				String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
						+ loanProcess.getLoanCode();

				Object data[][] = model.getSqlModel().getSingleResult(hrQuery);

				EmailTemplateBody templateAppr = new EmailTemplateBody();
				templateAppr.initiate(context, session);

				templateAppr
						.setEmailTemplate("LOAN APPLICATION - ADMIN TO ACCOUNTANT");

				// template.setEmailTemplate("LOAN -FINAL APPROVER TO
				// APPLICANT");

				templateAppr.getTemplateQueries();

				EmailTemplateQuery template1 = templateAppr.getTemplateQuery(1); // FROM
																					// EMAIL
				template1.setParameter(1, oldApprover);
				EmailTemplateQuery template2 = templateAppr.getTemplateQuery(2); // TO
																					// EMAIL
				template2.setParameter(1, String.valueOf(data[0][2]));
				EmailTemplateQuery template3 = templateAppr.getTemplateQuery(3);
				template3.setParameter(1, applnID);

				EmailTemplateQuery template4 = templateAppr.getTemplateQuery(4);
				template4.setParameter(1, applnID);

				EmailTemplateQuery template5 = templateAppr.getTemplateQuery(5);
				template5.setParameter(1, applnID);

				EmailTemplateQuery template6 = templateAppr.getTemplateQuery(6);
				template6.setParameter(1, applnID);

				EmailTemplateQuery template7 = templateAppr.getTemplateQuery(7);
				template7.setParameter(1, applnID);
				
				EmailTemplateQuery template8 = templateAppr.getTemplateQuery(8);
				template8.setParameter(1, loanProcess.getUserEmpId());
		

				templateAppr.configMailAlert();
				/*
				 * try { templateAppr.sendProcessManagerAlert(empID, module,
				 * msgType, applnID, alertLevel);
				 * templateAppr.sendApplicationMail(); } catch(Exception e) {
				 * logger.error(e); }
				 */

				try {
					link = "/loan/LoanProcessing_editApplication.action";
					linkParam = "ittloanApplCode=" + applnID;
					templateAppr.sendProcessManagerAlert(String
							.valueOf(data[0][2]), module, msgType, applnID, "",
							linkParam, link, "Pending", applicant, "", "", "",
							oldApprover);

					String ccId = "";

					if (keepInformedObj != null && keepInformedObj.length > 0) {

						for (int i = 0; i < keepInformedObj.length; i++) {
							ccId += String.valueOf(keepInformedObj[i][0]) + ",";
						}
						ccId = ccId.substring(0, ccId.length() - 1);
					}
					if (keepInformedObj != null && keepInformedObj.length > 0) {
						// keepInfo = String.valueOf(keepInformedObj[0][0]);
						templateAppr.sendApplicationMailToKeepInfo(ccId);
					}

				} catch (Exception e) {
					logger.error(e);
				}
				if (!InititorId.equals(applicant)) {
					templateAppr.sendApplicationMailToKeepInfo(InititorId);
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
		templateQuery10.setParameter(1, loanProcess.getUserEmpId());

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

					

					
					String ccId = "";

					if (keepInformedObj != null && keepInformedObj.length > 0) {

						for (int i = 0; i < keepInformedObj.length; i++) {
							ccId += String.valueOf(keepInformedObj[i][0]) + ",";
						}
						ccId = ccId.substring(0, ccId.length() - 1);
					}

					/*template.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, "", linkParam, link, "Pending", applicant,
							alternateApprover, InititorId, applicant,
							oldApprover);*/
					
					
					template.sendProcessManagerAlert(oldApprover, module, "I", applnID,
							"", linkParam, link, "Pending", applicant, InititorId,
							ccId, applicant, oldApprover);
					

					/*if (keepInformedObj != null && keepInformedObj.length > 1) {
						// keepInfo = String.valueOf(keepInformedObj[0][0]);
						
							System.out.println("ccId===Ganesh=="+ccId);
						template.sendApplicationMailToKeepInfo(ccId);
						
						
					}
					*/
					
					
				} catch (Exception e) {
					logger.error(e);
				}

				if (!InititorId.equals(applicant)) {
					template.sendApplicationMailToKeepInfo(applicant);
				}
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();

			}

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

	public String sendBackApplication() {
		try {
			String applicationId = request.getParameter("loanCodeVal");// application
																		// code
			LoanProccessModel model = new LoanProccessModel(); // creating an
																// instance of
																// LoanProccessModel
																// class
			model.initiate(context, session);

			String empCode = loanProcess.getEmpCode(); // employee code

			// String status = loanApproval.getApplicationStatus(); //status
			// selected by approver
			String comments = loanProcess.getComment(); // comments given by
														// approver
			// logger.info("status---------"+status);
			String level = request.getParameter("levelVal"); // application
																// level

			String apprAmount = request.getParameter("apprAmount");

			String apprStatus = request.getParameter("status");

			String status = model.sendBack(loanProcess, applicationId, empCode,
					comments, apprAmount, apprStatus);
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
				oldApprover = loanProcess.getUserEmpId();

			} catch (Exception e) {
				logger.error(e);
			}
			String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
			sendRejectAccountantAlert(applnID, module, applicant, oldApprover,
					newApprover, apprStatus, alertLevel);
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

	private void sendRejectAccountantAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String apprStatus, String alertLevel) {
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

			String keepInfo = "";
			String keepInformedId = " SELECT DISTINCT  LOAN_APPROVER_CODE FROM HRMS_LOAN_PATH WHERE LOAN_APPL_CODE ="
					+ applnID
					+ " AND LOAN_APPROVER_CODE NOT IN ("
					+ loanProcess.getUserEmpId() + ")";

			LoanProccessModel model = new LoanProccessModel();
			model.initiate(context, session);

			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			// send alert from oldApprover to applicant

			String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
					+ loanProcess.getLoanCode();

			Object data[][] = model.getSqlModel().getSingleResult(hrQuery);

			String InititorId = loanProcess.getInitiatorCode();

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
	templateQuery10.setParameter(1, loanProcess.getUserEmpId());
	
			template.configMailAlert();
			/*
			 * try { template.sendProcessManagerAlert(empID, module, msgType,
			 * applnID, alertLevel); template.sendApplicationMail(); }
			 * catch(Exception e) { logger.error(e); }
			 */

			if (apprStatus.equals("B")) {
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
						applicant, "", InititorId, applicant, oldApprover);

				
				link = "/loan/LoanApplication_viewApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;
				
				template.sendProcessManagerAlert(oldApprover, module, "I",
						applnID, alertLevel, linkParam, link, actualStataus,
						applicant, alternateApprover, ccId, loanProcess
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

				/*
				 * template.sendProcessManagerAlert(applicant, module, "I",
				 * applnID, alertLevel, linkParam, link, actualStataus,
				 * applicant, "", InititorId, applicant,oldApprover);
				 */

				/*template.sendProcessManagerAlert(applicant, module, "I",
						applnID, alertLevel, linkParam, link, actualStataus,
						applicant, alternateApprover, ccId, loanProcess
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
	/**This function is used to go list page.
	 * @return INPUT
	 * @throws Exception : IO Exception
	 */
	
	public String callBack() throws Exception {
		String listType = loanProcess.getListType();
		loanProcess.setHiddenCode("");
		if(listType.equals("pending")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} 
		return INPUT;
	}
	
	
	
	
	/**This method id used to show approved list.
	 * @return INPUT
	 * @throws Exception : IO Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			LoanProccessModel model = new LoanProccessModel(); // creating an
			// instance of
			// LoanApprovalModel
			// class
			model.initiate(context, session);
			String userId = loanProcess.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId,loanProcess);
			if(isCurrentUser) {
				model.getApprovedList(loanProcess, request, userId);
			}
			loanProcess.setListType("approved");
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		
		return INPUT;
	}
	
	
	/*
	 * method name : generateInstallmentSch purpose : to generate the
	 * installment schedule as per the given information return type : String
	 * parameter : none
	 */
	public String rescheduleInstallments() {
		
		/**
		 * getting all the form fields values which are necessary to
		 * reschedule the loan installments
		 */
		String principalAmount = request.getParameter("sanctionAmount");				//principal amount
		String interestRate    = request.getParameter("interestRate");				//interest rate
		String interestType    = request.getParameter("interestType");		//interest type
		String noOfInstallment = "";			
		String startDate       = request.getParameter("startingDate");				//installment start date
		if(!interestType.equals("I")){
			noOfInstallment = request.getParameter("installmentNumberFlat");			//no of installments
		}else {
			noOfInstallment = request.getParameter("emiAmount");			//no of installments
		}
		logger.info("principalAmount  "+principalAmount);
		logger.info("interestRate  "+interestRate);
		logger.info("interestType  "+interestType);
		logger.info("noOfInstallment  "+noOfInstallment);
		logger.info("startDate  "+startDate);
		
		
		LoanProccessModel model = new LoanProccessModel();
		String lable = "";
		boolean result = false;

		model.initiate(context, session);

		if (loanProcess.getHiddenCalfrmId().equals("E")) {
			result = model.installmentScheduleForEMI(loanProcess, request);

		} else if (loanProcess.getHiddenCalfrmId().equals("P")) {
			result = model
					.installmentScheduleForPrincipal(loanProcess, request);
		} else {
			result = model.generateInstallmentSchedule(loanProcess, request);
		}
		if (loanProcess.getInstallmentFlag().equals("true")) {
			if (!(loanProcess.getInterestType().equals("R")))
				lable = "Principal Amount";
			else
				lable = "Principal Reduction";

			request.setAttribute("lable", lable);

		} else if (loanProcess.getInstallmentFlag().equals("false")) {
			if (loanProcess.getHiddenCalfrmId().equals("E")) {
				addActionMessage("Please check the EMI amount");
				loanProcess.setInstallmentFlag("true");
			}

		}

		model.setLoanList(loanProcess, "Y");

		loanProcess.setHiddenCalfrmId(loanProcess.getHiddenCalfrmId());
		model.loanApproverCommentList(loanProcess);

		setApproverList(loanProcess.getEmpCode()); // setting approver list

		loanProcess.setEnableAll("Y");
		loanProcess.setAccountantFlag(true);
		loanProcess.setHrFlag(false);
		loanProcess.setApproverCommentsFlag(true);
		
		String requestID = request.getParameter("ittloanApplCode");
		//for showing applicant comment and EMI Type
		model.viewApplicantDetails(loanProcess, requestID);
String status= loanProcess.getApplicationStatus();
		if (status.equals("RR") || status.equals("A") || status.equals("R")
				|| status.equals("B")) {

		///	loanProcess.setEnableAll("N");
			
			getNavigationPanel(5);
		///	loanProcess.setApproverCommentsFlag(false);
		} else {

			//loanProcess.setApproverCommentsFlag(false);
			getNavigationPanel(4);
		}
		
		//getNavigationPanel(4);
		model.terminate();
		return "pageDtls";
	}
}
