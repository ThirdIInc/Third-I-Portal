package org.struts.action.Loan;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.paradyne.bean.Loan.LoanApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.loan.LoanApplicationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.bean.Loan.*;
import org.struts.lib.ParaActionSupport;

/**
 * @author tarunc
 * @date Jul 07, 2008 modified @ 12/12/2011
 * @description LoanApplicationAction serves as action for loan application form
 *              to save, update, delete and view loan application
 */
public class LoanApplicationAction extends ParaActionSupport {

	LoanApplication loanApp;
	LoanProcessing loanProcess = new LoanProcessing();
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanApplicationAction.class);

	@Override
	public void prepare_local() throws Exception {
		loanApp = new LoanApplication();
		loanApp.setMenuCode(357);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the application date and to retrieve the employee
	 *          information if the user is general
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		boolean result;
		String source = request.getParameter("src");
		loanApp.setSource(source);
		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		if (loanApp.getLoanApplCode().equals("")) { // if the form loaded i.e.
			// no application is
			// selected

			/**
			 * call setApplicationDate(loanApp) method of LoanApplicationModel
			 * class set the loan application date as system date
			 */
			model.setApplicationDate(loanApp);
		} // end of if statement
		model.setPfDetails(loanApp);
		// model.setLoanList(loanApp, "Y");

		if (loanApp.isGeneralFlag()) { // if user is a general employee, set
			// the employee details on form loading
			/**
			 * call getEmployeeDetails(loanApp) method of LoanApplicationModel
			 * class to retrieve the employee details
			 */
			model.getEmployeeDetails(loanApp);
			setApproverList(loanApp.getUserEmpId()); // setting approver list

			result = model.setLoanList(loanApp, "N");
			if (!result) {

				///addActionMessage("Loan Type not defined.");

			}
		} // end of if statement
		else {
			model.setLoanList(loanApp, "Y");
			model.getEmployeeDetails(loanApp);
			setApproverList(loanApp.getEmpCode()); // setting approver list

		}

		// loanApp.setPfLoanCode(model.currentPfLoanCode());
		model.terminate(); // terminate the LoanApplicationModel class
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return loanApp;
	}

	public LoanApplication getLoanApp() {
		return loanApp;
	}

	public void setLoanApp(LoanApplication loanApp) {
		this.loanApp = loanApp;
	}

	/**
	 * @method saveLoanApplication
	 * @purpose to insert the loan application details in HRMS_LOAN_APPLICATION
	 *          table
	 * @return String
	 */
	public String saveLoanApplication() {

		boolean isValidLimit;
		boolean result;
		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		isValidLimit = model.getLoanLimitAmountFromLoanMaster(loanApp, loanApp
				.getLoanAmount());
		if (isValidLimit) {

			/**
			 * call to generateEmpFlow(empCode, type, level) method of
			 * ParaActionSupport class to get the approver details for the
			 * person who applied for loan
			 */
			Object[][] empFlow = generateEmpFlow(loanApp.getEmpCode(), "Loan",
					1);

			if (empFlow != null) { // if approver is present
				if (loanApp.getLoanApplCode().equals("")) { // and loan
					// application code
					// is null or blank

					/**
					 * call saveLoanApplication(loanApp, empFlow) of
					 * LoanApplicationModel class to insert the application
					 * details in HRMS_LOAN_APPLICATION table
					 */
					result = model.saveLoanApplication(loanApp, empFlow);

					if (result) { // if application saved with out any error
						addActionMessage(getText("addMessage", "")); // display
						// save
						// message

						// ------------------------Process Manager
						// Alert------------------------start
						try {
							String applnID = loanApp.getHiddenCode();
							String module = "Loan";
							String applicant = loanApp.getEmpCode();
							String approver = String.valueOf(empFlow[0][0]);
							String applnDate = loanApp.getApplicationdate();
							String applicantId = loanApp.getUserEmpId();
							sendApplicationAlert(applnID, module, applicant,
									approver, applnDate, applicantId, empFlow);
						} catch (Exception e) {
							logger.error(e);
						}
						// ------------------------Process Manager
						// Alert------------------------end

						reset(); // call reset() method to clear all form
						// fields values
					} // end of if statement
					else {
						addActionMessage("Loan application can not be saved"); // else
						// display
						// error
						// message
					} // end of else statement
				} else { // if loan application code present

					/**
					 * call updateLoanApplication(loanApp, empFlow) of
					 * LoanApplicationModel class to update application details
					 * in HRMS_LOAN_APPLICATION table
					 */
					result = model.updateLoanApplication(loanApp, empFlow);

					if (result) { // if application updated with out any error
						addActionMessage(getMessage("update")); // display
						// update
						// message
						reset(); // call reset() method to clear all form
						// fields values
					} // end of if statement
					else {
						addActionMessage("Loan application can not be updated"); // else
						// display
						// error
						// message
					} // end of else statement
				} // end of else statement
			} // end of if statement
			else { // if approver is not present then display error message
				addActionMessage("Reporting structure not defined for the employee\n"
						+ loanApp.getEmpName());
				addActionMessage("Please contact your HR Manager");
			}// end of else statement
		} else {
			addActionMessage("Loan amount exceeds the allowed limit for this type of loan");
		}

		model.terminate(); // terminate the LoanApplicationModel class
		return "success";
	}

	/**
	 * @method getEmployeeDetailsAction
	 * @purpose to retrieve required details of the selected employee from
	 *          HRMS_EMP_OFFC table
	 * @return String
	 */
	public String getEmployeeDetailsAction() {
		boolean result;
		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		setApproverList(loanApp.getEmpCode()); // setting approver list

		/**
		 * call getEmployeeDetails(loanApp) method of LoanApplicationModel class
		 * to retrieve all the details of selected employee
		 */
		model.getEmployeeDetails(loanApp);
		model.getSetEmployeeDetails(loanApp);// getting

		result = model.setLoanList(loanApp, "N");

		if (!result) {

			addActionMessage("Loan Type not defined.");

		}

		// employee
		// name,date etc
		loanApp.setLoanAllowedLimit("");
		// loanApp.setApplicantComment("");
		model.terminate(); // terminate the model class

		/**
		 * call resetOnEmployeeChange() method to clear all the employee detail
		 * fields values on employee change
		 */
		resetOnEmployeeChange();
		getNavigationPanel(3);
		return "success";
	}

	/**
	 * @method resetOnEmployeeChange
	 * @purpose to clear all the employee details related form fields on
	 *          employee change
	 * @return String
	 */
	public String resetOnEmployeeChange() {
		loanApp.setLoanCode("");
		loanApp.setLoanType("");
		loanApp.setLoanAmount("");

		loanApp.setInterestRate("");
		loanApp.setInterestType("");
		loanApp.setExpectedEmi("");
		loanApp.setTenure("");

		loanApp.setRecommendedByCode("");
		loanApp.setRecommendedByName("");
		loanApp.setRecommendedByToken("");
		loanApp.setFirstGuarantorCode("");
		loanApp.setFirstGuarantorName("");
		loanApp.setFirstGuarantorToken("");
		loanApp.setSecondGuarantorCode("");
		loanApp.setSecondGuarantorName("");
		loanApp.setSecondGuarantorToken("");
		return "success";
	}

	/**
	 * @method showApplicationRecord
	 * @purpose to retrieve all the details from HRMS_LOAN_APPLICATION table for
	 *          the selected application and display them on the respective form
	 *          fields
	 * @return String
	 */
	public String showApplicationRecord() {

		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		/**
		 * call showApplicationRecord(loanApp) method of LoanApplicationModel
		 * class to retrieve all the details of selected application
		 */
		model.showApplicationRecord(loanApp);

		model.terminate(); // terminate the model class
		return "success";
	}

	public String f9Purpose() {

		String query = "SELECT PFT_PURPOSE, PFT_ELIGIBILITY, PFT_PURPOSE_CODE FROM  HRMS_PFTRUST_LOAN_PURS"
				+ " INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_CODE=HRMS_PFTRUST_LOAN_PURS.PFT_CODE)"
				+ " WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";

		String[] headers = { getMessage("purpose"), getMessage("eligibility") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "loanPurposeValue", "loanEligibility",
				"loanPurpose" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * @method deleteApplication
	 * @purpose to delete all the details form HRMS_LOAN_APPLICATION table for
	 *          the selected application
	 * @return String
	 */
	public String deleteApplication() {

		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		/**
		 * call deleteApplication(loanApp) method of LoanApplicationModel class
		 * to delete the selected application from HRMS_LOAN_APPLICATION table
		 */
		boolean result = model.deleteApplication(loanApp);

		if (result) { // if application deleted with out any error
			addActionMessage(getText("delMessage", "")); // display delete
			// message

			deleteProcessManagerAlert("Loan", loanApp.getHiddenCode());

			reset(); // call reset() method to clear all form fields values
		} // end of if statement
		else {
			addActionMessage("Loan application can not be deleted"); // else
			// display
			// the
			// error
			// message
		} // end of else statement

		model.terminate(); // terminate the model class
		return "mymessages";
	}

	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method retriveForApproval
	 * @purpose to retrieve all the details from HRMS_LOAN_APPLICATION table for
	 *          the application selected from loan approval form and display
	 *          them on the respective form fields
	 * @return String
	 */
	public String retriveForApproval() {
		/**
		 * getting the form parameters which are necessary to retrieve the
		 * application details
		 */
		String loanApplCode = request.getParameter("loanCode"); // application
		// code
		String empCode = request.getParameter("empCode"); // employee code

		loanApp.setLoanApplCode(loanApplCode);
		loanApp.setEmpCode(empCode);

		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		/**
		 * call getApplicationDetailsForApproval(loanApp, request) method of
		 * LoanApplicationModel class to retrieve all the details of selected
		 * application from Loan Approval form
		 */
		loanApp.setBackButtonFlag("fromApprover");
		loanApp.setApproverCommentsFlag(true);
		model.getApplicationDetailsForApproval(loanApp, request);
		model.getAmountLimit(loanApp);
		model.terminate(); // terminate the LoanApplicationModel class

		loanApp.setIsApprove("true"); // set the approve flag to true to
		// display the approval related details
		getNavigationPanel(4);
		loanApp.setEnableAll("N");
		return "success";
	}

	/**
	 * @method report
	 * @purpose to generate report for selected application
	 * @return String
	 */
	public String report() {

		LoanApplicationModel model = new LoanApplicationModel(); // creating
		// an
		// instance
		// of the
		// LoanApplicationModel
		// class
		model.initiate(context, session); // initialize the
		// LoanApplicationModel class

		/**
		 * call report(loanApp, response) of LoanApplicationModel class to
		 * generate report for the selected application
		 */
		model.report(loanApp, response);

		model.terminate(); // terminate the LoanApplicationModel class
		return null;
	}

	/**
	 * @method reset
	 * @purpose to clear all form fields values
	 * @return String
	 */
	public String reset() {
		logger.info("in reset method");

		try {

			LoanApplicationModel model = new LoanApplicationModel(); // creating
			// an
			// instance
			// of the
			// LoanApplicationModel
			// class
			model.initiate(context, session); // initialize the
			// LoanApplicationModel class

			loanApp.setLoanApplCode("");
			loanApp.setEmpCode("");
			loanApp.setEmpName("");
			loanApp.setEmpToken("");
			loanApp.setBranchCode("");
			loanApp.setBranchName("");
			loanApp.setDeptCode("");
			loanApp.setDeptName("");
			loanApp.setDesgCode("");
			loanApp.setDesgName("");
			loanApp.setGrade("");
			loanApp.setGradeCode("");
			loanApp.setConfirmationDate("");
			loanApp.setLoanCode("");
			loanApp.setApplicationdate("");
			loanApp.setGrossSalary("");
			loanApp.setLoanSubType("");
			loanApp.setLoanPurpose("");
			loanApp.setHiddenLoanSubType("");
			loanApp.setLoanEligibility("");
			loanApp.setLoanPurposeValue("");
			loanApp.setMinSanctionLimit("");
			loanApp.setMaxSanctionLimit("");
			loanApp.setMinSanctionAmt("");
			loanApp.setMaxSanctionAmt("");
			loanApp.setPfBalance("");
			loanApp.setApplicantComment("");
			loanApp.setLoanAllowedLimit("");

			loanApp.setInterestType("");
			loanApp.setInterestRate("");
			loanApp.setExpectedEmi("");
			loanApp.setTenure("");
			/**
			 * call resetOnEmployeeChange() method to clear all the employee
			 * detail fields values on employee change
			 */
			resetOnEmployeeChange();
			try {
				boolean result;
				/**
				 * call prepare_withLoginProfileDetails() method to set the
				 * general employee details and application date to system date
				 */
				// prepare_withLoginProfileDetails();
				if (loanApp.isGeneralFlag()) { // if user is a general
												// employee, set
					// the employee details on form loading
					/**
					 * call getEmployeeDetails(loanApp) method of
					 * LoanApplicationModel class to retrieve the employee
					 * details
					 */
					model.getEmployeeDetails(loanApp);
					setApproverList(loanApp.getUserEmpId()); // setting
																// approver list

					result = model.setLoanList(loanApp, "N");
					if (!result) {

						addActionMessage("Loan Type not defined.");

					}
				} // end of if statement
				else {
					model.setLoanList(loanApp, "Y");
					model.getEmployeeDetails(loanApp);
					setApproverList(loanApp.getEmpCode()); // setting approver
															// list

				}

			} catch (Exception e) {
				logger.error(e);
				// TODO: handle exception
			} // end of try-catch block
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return "success";
	}

	/**
	 * @method f9EmployeeNameAction
	 * @purpose to select the employee details
	 * @return String
	 * @throws Exception
	 */
	public String f9EmployeeNameAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
				+ "HRMS_EMP_OFFC.EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_STATUS = 'S' " + "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empToken", "empName", "empCode" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanApplication_getEmployeeDetailsAction.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9RecommendedByAction
	 * @purpose to select the recommended by name
	 * @return String
	 * @throws Exception
	 */
	public String f9RecommendedByAction() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, EMP_ID "
				+ "FROM HRMS_EMP_OFFC  " + " WHERE EMP_STATUS = 'S' ";

		if (!loanApp.getEmpCode().equals("")) {
			query += "AND EMP_ID != " + loanApp.getEmpCode();
		}

		query += " ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "recommendedByToken", "recommendedByName",
				"recommendedByCode" };

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

	/**
	 * @method f9FirstGuarantorAction
	 * @purpose to select the first guarantor name
	 * @return String
	 * @throws Exception
	 */
	public String f9FirstGuarantorAction() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, EMP_ID "
				+ "FROM HRMS_EMP_OFFC  " + " WHERE EMP_STATUS = 'S' ";

		if (!loanApp.getEmpCode().equals("")) {
			query += "AND EMP_ID != " + loanApp.getEmpCode();
		}
		if (!loanApp.getSecondGuarantorCode().equals("")) {
			query += "AND EMP_ID != " + loanApp.getSecondGuarantorCode();
		}

		query += " ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "firstGuarantorToken", "firstGuarantorName",
				"firstGuarantorCode" };

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

	/**
	 * @method f9SecondGuarantorAction
	 * @purpose to select the second guarantor name
	 * @return String
	 * @throws Exception
	 */
	public String f9SecondGuarantorAction() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, EMP_ID "
				+ "FROM HRMS_EMP_OFFC  " + " WHERE EMP_STATUS = 'S' ";

		if (!loanApp.getEmpCode().equals("")) {
			query += "AND EMP_ID != " + loanApp.getEmpCode();
		}
		if (!loanApp.getFirstGuarantorCode().equals("")) {
			query += "AND EMP_ID != " + loanApp.getFirstGuarantorCode();
		}

		query += " ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "secondGuarantorToken", "secondGuarantorName",
				"secondGuarantorCode" };

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

	/**
	 * @method f9LoanTypeAction
	 * @purpose to select the loan type
	 * @return String
	 * @throws Exception
	 */
	public String f9LoanTypeAction() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT LOAN_CODE, LOAN_NAME FROM HRMS_LOAN_MASTER ORDER BY LOAN_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("loanCode"), getMessage("typeLoan") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "loanCode", "loanType" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

	/**
	 * @method f9Action
	 * @purpose to select the already saved application details
	 * @return String
	 * @throws Exception
	 */
	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT LOAN_APPL_CODE, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "TO_CHAR(LOAN_APPL_DATE, 'DD-MM-YYYY'), DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected'), "
				+ "EMP_TOKEN, LOAN_EMP_ID, LOAN_APPL_APPROVER, LOAN_APPLICANT_COMMENT "
				+ "FROM HRMS_LOAN_APPLICATION "
				+ "INNER JOIN HRMS_EMP_OFFC ON (LOAN_EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";

		if (loanApp.isGeneralFlag()) {
			query += "WHERE LOAN_EMP_ID = " + loanApp.getUserEmpId();
		}

		query += " ORDER BY LOAN_APPL_DATE DESC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("appCode"), getMessage("employee"),
				getMessage("dateApp"), getMessage("appStatus") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "10", "30", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "loanApplCode", "empName", "applicationdate",
				"applicationStatus", "empToken", "empCode", "approverCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

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
		String submitToMethod = "LoanApplication_showApplicationRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String approver, String applnDate,
			String empCode, Object[][] empFlow) {
		try {
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Loan";
			processAlerts.removeProcessAlert(String.valueOf(applnID), prmodule);

			String applicantID = empCode;

			String msgType = "A";
			String level = "1";

			// Mail From Applicant to Approver
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			/*
			 * template.setEmailTemplate("LOAN -APPLICANT TO APPROVER");
			 * 
			 * template.getTemplateQueries();
			 * 
			 * EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			 * //FROM EMAIL templateQuery1.setParameter(1, applicant);
			 * 
			 * EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			 * //TO EMAIL templateQuery2.setParameter(1, approver);
			 * 
			 * EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			 * templateQuery3.setParameter(1, applnID);
			 */

			template
					.setEmailTemplate("LOAN APPLICATION FROM APPLICANT TO APPROVER");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, approver);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);

			// create process alerts for send for approval

			String link = "";
			String linkParam = "";

			link = "/loan/LoanApproval_editApplication.action";
			linkParam = "ittloanApplCode=" + applnID;
			try {
				template.sendProcessManagerAlert(approver, module, msgType,
						applnID, level, linkParam, link, "Pending",
						applicantID, "", "", "", applicantID);
			} catch (Exception e) {
				e.printStackTrace();
			}

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			// Mail From Applicant to Approver End

			// Mail From System to Applicant
			EmailTemplateBody templateApplicant = new EmailTemplateBody();
			templateApplicant.initiate(context, session);

			templateApplicant
					.setEmailTemplate("LOAN APPLICATION DETAILS TO APPLICANT");

			templateApplicant.getTemplateQueries();

			EmailTemplateQuery templateApplicantQuery1 = templateApplicant
					.getTemplateQuery(1); // FROM EMAIL
			// templateApplicantQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateApplicantQuery2 = templateApplicant
					.getTemplateQuery(2); // TO EMAIL
			templateApplicantQuery2.setParameter(1, loanApp.getEmpCode());

			System.out.println("applicantID =" + loanApp.getEmpCode());

			EmailTemplateQuery templateApplicantQuery3 = templateApplicant
					.getTemplateQuery(3);
			templateApplicantQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateApplicantQuery4 = templateApplicant
					.getTemplateQuery(4);
			templateApplicantQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateApplicantQuery5 = templateApplicant
					.getTemplateQuery(5);
			templateApplicantQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateApplicantQuery6 = templateApplicant
					.getTemplateQuery(6);
			templateApplicantQuery6.setParameter(1, applnID);

			templateApplicant.configMailAlert();
			// templateApplicant.sendProcessManagerAlert(approver, module,
			// msgType, applnID, level);

			try {
				link = "/loan/LoanApplication_viewApplicationFunction.action";
				linkParam = "loanApplCode=" + applnID;
				templateApplicant.sendProcessManagerAlert("", module, "I",
						applnID, level, linkParam, link, "Pending",
						applicantID, "", "", applicantID, applicantID);
			} catch (Exception e) {
				e.printStackTrace();
			}

			templateApplicant.sendApplicationMail();
			templateApplicant.clearParameters();
			templateApplicant.terminate();

			// Mail From System to Applicant End

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public String openCalEMI() {
		String sancAmount = request.getParameter("loanAmt");
		request.setAttribute("loanAppl", "true");
		
		//String calEmiValue = request.getParameter("hiddenCheckfrmId"); 
		//request.setAttribute("loanAppl", "true");
		
		//String empExpValue = request.getParameter("expEmpValue"); 
		///request.setAttribute("loanAppl", "true");
		
		// logger.info("hiddenCalType=="+loanProcess.getHiddenCalType());
		loanApp.setSanctionAmount(sancAmount);
		//loanApp.setHiddenCalType(calEmiValue);
		//loanApp.setExpEmpValue(empExpValue);
		return "calculateEMI";
	}

	public String calculateEMI() {
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		String sysDate = formater.format(date);
		loanApp.setStartingDate(loanApp.getStartingDate());
		loanApp.setPaymentDate(loanApp.getPaymentDate());

		System.out.println("########loanApp.getStartingDate()########"
				+ String.valueOf(loanApp.getStartingDate()));
		System.out.println("########loanApp.getPaymentDate()########"
				+ String.valueOf(loanApp.getPaymentDate()));
		logger
				.info("hiddenCalType in calculate=="
						+ loanApp.getHiddenCalType());
		generateInstallmentSch();
		return "calculateEMI";
	}

	public String generateInstallmentSch() {
		LoanApplicationModel model = new LoanApplicationModel();
		String lable = "";
		boolean result = false;
		logger.info("loanProcess.getView()==" + loanProcess.getView());

		model.initiate(context, session);
		if (loanApp.getHiddenCalType().equals("E")) {
			result = model.installmentScheduleForEMI(loanApp, request);

		} else if (loanApp.getHiddenCalType().equals("P")) {
			result = model.installmentScheduleForPrincipal(loanApp, request);
		} else {
			result = model.generateInstallmentSchedule(loanApp, request);
		}
		if (loanApp.getInstallmentFlag().equals("true")) {
			if (!(loanApp.getInterestType().equals("R")))
				lable = "Principal Amount";
			else
				lable = "Principal Reduction";

			request.setAttribute("lable", lable);

		} else if (loanApp.getInstallmentFlag().equals("false")) {
			if (loanApp.getHiddenCalType().equals("E")) {
				addActionMessage("Please check the EMI amount");
			}

		}

		logger.info("interst type==" + loanProcess.getInterestType());

		loanProcess.setInterestTypeHidden(loanProcess.getInterestType());

		model.terminate();
		return SUCCESS;
	}

	public String input() {
		try {

			LoanApplicationModel model = new LoanApplicationModel();
			model.initiate(context, session);
			String userId = loanApp.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				// model.getPendingList(loanApp, request, userId);
			}
			boolean result;
			if (loanApp.isGeneralFlag()) {
				result = model.setLoanList(loanApp, "N");
				if (!result) {
					addActionMessage("Loan Type not defined.");
				}
			} // end of if statement
			else {
				model.setLoanList(loanApp, "Y");
			}
			
			loanApp.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return INPUT;
	}

	public String addNew() {
		try {

			LoanApplicationModel model = new LoanApplicationModel();
			model.initiate(context, session);

			// model.getSysDate(loanApp);
			// reset();
			// model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
			// employee branch,designation etc

			if (loanApp.isGeneralFlag()) {
				// loanApp.setEmployeeCode(loanApp.getEmployeeCode());

				model.getEmployeeDetails(loanApp);
			}// end of
			else {
				model.getEmployeeDetails(loanApp);
			}
			// model.getSetEmployeeDetails(loanApp);// getting
			// employee
			// name,date etc
			setApproverList(loanApp.getEmpCode()); // setting approver list

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	public void setApproverList(String empCode) {
		try {
			// bean.setFirstApproverCode("");
			LoanApplicationModel model = new LoanApplicationModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			if (!empCode.equals("")) {
				Object[][] empFlow = model1.generateEmpFlow(empCode, "Loan");
				model.setApproverData(loanApp, empFlow);
			}
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	public String back() throws Exception {

		input();
		// getNavigationPanel(1);
		loanApp.setEnableAll("Y");
		return INPUT;
	}

	public String editApplicationFunction() {
		try {

			String requestID = request.getParameter("loanApplCode");
			LoanApplicationModel model = new LoanApplicationModel();
			model.initiate(context, session);
			model.editApplicationFunction(loanApp, requestID);
			model.setLoanList(loanApp, "N");
			model.getAmountLimit(loanApp);
			setApproverList(loanApp.getEmpCode()); // setting approver list
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return SUCCESS;
	}

	public String draftFunction() throws Exception {
		try {

			boolean isValidLimit;
			boolean result;
			LoanApplicationModel model = new LoanApplicationModel(); 
			model.initiate(context, session);
			isValidLimit = model.getLoanLimitAmountFromLoanMaster(loanApp,
					loanApp.getLoanAmount());
			
			Object[][] empFlow = generateEmpFlow(loanApp.getEmpCode(), "Loan",
					1);

			String status = request.getParameter("status");
			if (empFlow != null && empFlow.length > 0) { // if approver is
				// present
				if (loanApp.getHiddenCode().equals("")) { // and loan
					// application code
					// is null or blank

					/**
					 * call saveLoanApplication(loanApp, empFlow) of
					 * LoanApplicationModel class to insert the application
					 * details in HRMS_LOAN_APPLICATION table
					 */
					result = model.draftFunction(loanApp, empFlow, request,
							status);

					if (result) { // if application saved with out any error
						addActionMessage(getText("addMessage", "")); // display
						// save
						// message

						// reset(); //call reset() method to clear all form
						// fields values

						/**
						 * call sendProcessManagerAlertDraft for saving draft
						 * entry into process manager alert table.
						 */

						sendProcessManagerAlertDraft();

					} // end of if statement
					else {
						addActionMessage("Loan application can not be saved"); // else
						// display
						// error
						// message
					} // end of else statement
				} else { // if loan application code present

					/**
					 * call updateLoanApplication(loanApp, empFlow) of
					 * LoanApplicationModel class to update application details
					 * in HRMS_LOAN_APPLICATION table
					 */
					result = model.updateLoanApplication(loanApp, empFlow);

					if (result) { // if application updated with out any error
						addActionMessage(getMessage("update")); // display
						// update
						// message

						// reset(); //call reset() method to clear all form
						// fields values
						sendProcessManagerAlertDraft();
					} // end of if statement
					else {
						addActionMessage("Loan application can not be updated"); // else
						// display
						// error
						// message
					} // end of else statement
				} // end of else statement
			} // end of if statement
			else { // if approver is not present then display error message
				addActionMessage("Reporting structure not defined for the employee\n"
						+ loanApp.getEmpName());
				addActionMessage("Please contact your HR Manager");
			}// end of else statement
			// } else {
			// addActionMessage("Loan amount exceeds the allowed limit for this
			// type of loan");
			// }

			model.terminate(); // terminate the LoanApplicationModel class

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "mymessages";

	}

	/**
	 * @method getAmountLimit
	 * @purpose to retrieve required details of the selected employee from
	 *          HRMS_EMP_OFFC table
	 * @return String
	 */
	public String getAmountLimit() {

		try {
			LoanApplicationModel model = new LoanApplicationModel(); // creating
			// an
			// instance
			// of
			// the
			// LoanApplicationModel
			// class
			model.initiate(context, session); // initialize the
			// LoanApplicationModel class
			/**
			 * call getEmployeeDetails(loanApp) method of LoanApplicationModel
			 * class to retrieve all the details of selected employee
			 */
			model.getAmountLimit(loanApp);
			model.getSetEmployeeDetails(loanApp);// getting
			// employee
			// name,date etc
			setApproverList(loanApp.getEmpCode()); // setting approver list
			model.setLoanList(loanApp, "N");
			model.terminate(); // terminate the model class
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return SUCCESS;
	}

	public String sendForApprovalFunction() {
		try {
			LoanApplicationModel model = new LoanApplicationModel(); // creating
			// an
			// instance
			// of
			// the
			// LoanApplicationModel
			// class
			model.initiate(context, session);

			String status = request.getParameter("status");

			Object[][] empFlow = generateEmpFlow(loanApp.getEmpCode(), "Loan",
					1);
			if (empFlow != null) { // if approver is present

				boolean result = model.sendForApprovalFunction(loanApp,
						request, empFlow, status);
				model.terminate();
				if (result) {
					String trackCOde = loanApp.getTrackingNo();
					addActionMessage("Application send for approval successfully \n Tracking No: "
							+ trackCOde);

					// ------------------------Process Manager
					// Alert------------------------start
					try {
						String applnID = loanApp.getHiddenCode();
						System.out.println("applnID===" + applnID);
						String module = "Loan";
						String applicant = loanApp.getEmpCode();
						String approver = String.valueOf(empFlow[0][0]);
						String applnDate = loanApp.getApplicationdate();
						String applicantId = loanApp.getUserEmpId();
						sendApplicationAlert(applnID, module, applicant,
								approver, applnDate, applicantId, empFlow);
					} catch (Exception e) {
						logger.error(e);
					}
					// ------------------------Process Manager
					// Alert------------------------end

				} else {
					addActionMessage("Error occured sending application.");
				}

			} // end of if statement
			else { // if approver is not present then display error message
				addActionMessage("Reporting structure not defined for the employee\n"
						+ loanApp.getEmpName());
				addActionMessage("Please contact your HR Manager");
			}// end of else statement

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mymessages";
	}

	public String viewApplicationFunction() {

		try {
			String requestID = request.getParameter("loanApplCode");
			LoanApplicationModel model = new LoanApplicationModel(); // creating
			// an
			// instance
			// of
			// the
			// LoanApplicationModel
			// class
			model.initiate(context, session);

			model.viewApplicationFunction(loanApp, requestID);
			String ApplStatus = loanApp.getApplicationStatus();
			if (ApplStatus.equals("A")) {
				loanApp.setInstallmentFlag("true");
				model.showInstallmentSchedule(loanApp, request);
			}

			model.setLoanList(loanApp, "Y");
			model.getAmountLimit(loanApp);
			String lable = "";
			if (!(loanApp.getInterestType().equals("R")))
				lable = "Principal Amount";
			else
				lable = "Principal Reduction";
			request.setAttribute("lable", lable);

			model.getApproverCommentList(loanApp, requestID);
			setApproverList(loanApp.getEmpCode()); // setting approver list
			model.terminate();

			loanApp.setEnableAll("N");
			getNavigationPanel(2);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = loanApp.getEmpCode();
			String module = "Loan";
			String applnID = loanApp.getHiddenCode();
			String level = "1";
			String link = "/loan/LoanApplication_editApplicationFunction.action";
			String linkParam = "loanApplCode=" + applnID;
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", loanApp.getEmpName()
					.trim());
			message = message.replace("<#APPL_TYPE#>", "Loan");
			template.sendProcessManagerAlertDraft(loanApp.getUserEmpId(),
					module, "A", applnID, level, linkParam, link, message,
					"Draft", applicantID, loanApp.getUserEmpId());
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
