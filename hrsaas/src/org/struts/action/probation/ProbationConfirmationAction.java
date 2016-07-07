package org.struts.action.probation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.paradyne.bean.probation.ProbationConfirmation;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.probation.ProbationConfirmationModel;
import org.struts.lib.ParaActionSupport;
import org.paradyne.lib.PPEncrypter;

public class ProbationConfirmationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationConfirmationAction.class);

	ProbationConfirmation probationConfirm;

	public void prepare_local() throws Exception {
		probationConfirm = new ProbationConfirmation();
		probationConfirm.setMenuCode(933);
	}

	public Object getModel() {
		return probationConfirm;
	}

	public ProbationConfirmation getProbationConfirm() {
		return probationConfirm;
	}

	public void setProbationConfirm(ProbationConfirmation probationConfirm) {
		this.probationConfirm = probationConfirm;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			 //model.getRecord(probationConfirm, "P", request);
			 //probationConfirm.setEmpDataflag("false");
			// probationConfirm.setConfirmEmpflag("true");
			model.terminate();
		} catch (Exception e) {
			logger
					.info("Exception in prepare_withLoginProfileDetails----------"
							+ e);
		}
	}

	public String sendmailConfTemp() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		try {
			String inFileName1 = getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter/ConfirmaionLetter_"
					+ probationConfirm.getEmployeeName() + ".doc";
			String tempCode = probationConfirm.getTemplateCode();
			Template template = new Template(tempCode);
			template.initiate(context, session);

			template.getTemplateQueries();
			TemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, probationConfirm.getProbationCode());

			TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, probationConfirm.getAuthoCode());

			String comleteTemplate = template.executeWriteFile(request,
					response, "CONFIRMATION_LETTER");
			logger.info("comleteTemplate....." + comleteTemplate);

			byte[] buf = comleteTemplate.getBytes();
			// PrintWriter output = null;
			String inFileName = getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter/ConfirmaionLetter_"
					+ probationConfirm.getEmployeeName() + ".doc";
			File file1 = new File(getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter");
			if (!file1.exists()) {
				file1.mkdirs();
			}
			OutputStream out = new FileOutputStream(inFileName);
			out.write(buf);
			out.close();

			EmailTemplateBody mailtemplate = new EmailTemplateBody();
			mailtemplate.initiate(context, session);
			mailtemplate.setEmailTemplate("PROBATION - HR TO CANDIDATE");
			mailtemplate.getTemplateQueries();

			EmailTemplateQuery etemplateQuery1 = mailtemplate
					.getTemplateQuery(1); // FROM
			etemplateQuery1.setParameter(1, probationConfirm.getUserEmpId());

			EmailTemplateQuery etemplateQuery2 = mailtemplate
					.getTemplateQuery(2); // TO
			etemplateQuery2.setParameter(1, probationConfirm.getEmpCode());

			EmailTemplateQuery etemplateQuery3 = mailtemplate
					.getTemplateQuery(3);
			etemplateQuery3.setParameter(1, probationConfirm.getEmpCode());

			EmailTemplateQuery etemplateQuery4 = mailtemplate
					.getTemplateQuery(4);
			etemplateQuery4.setParameter(1, probationConfirm.getUserEmpId());

			String attachPath[] = new String[1];

			System.out.println("probationConfirm.setEmployeeName()      "
					+ probationConfirm.getEmployeeName());
			attachPath[0] = getText("data_path") + "/datafiles/" + poolName
					+ "/ConfirmaionLetter/ConfirmaionLetter_"
					+ probationConfirm.getEmployeeName() + ".doc";
			System.out.println("Containt in attach path : " + attachPath[0]);
			mailtemplate.configMailAlert();
			try {
				mailtemplate.sendApplMailWithAttachment(attachPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			addActionMessage("Mail has been sent successfully to the candidate");
			mailtemplate.clearParameters();
			mailtemplate.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		reset();
		return callstatus();
	}

	/**
	 * @method previewoffer
	 * @purpose to prieview offer letter details
	 * @return String
	 */
	public String generateConfTemp() {
		try {

			String tempCode = probationConfirm.getTemplateCode();
			Template template = new Template(tempCode);
			template.initiate(context, session);

			template.getTemplateQueries();
			TemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, probationConfirm.getProbationCode());

			TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, probationConfirm.getAuthoCode());
			System.out.println("probationConfirm.getAuthoCode()  ::  "
					+ probationConfirm.getAuthoCode());
			String comleteTemplate = template.execute(request, response,
					"CONFIRMATION_LETTER");
			logger.info("comleteTemplate....." + comleteTemplate);
		} catch (Exception e) {
			try {
				String type = "Txt";
				String title = "Template letter";
				e.printStackTrace();
				String finaldata = "<html>" + "" + "</html>";
				org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
						type, title);

				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.getOutputStream().write(buf);
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + "CONFIRMATION_LETTER"
								+ ".doc\"");
				response.setHeader("cache-control", "no-cache");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}

	public String searchRecord() {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			String status = probationConfirm.getSearchStatus().trim();
			if (status.equals(""))
				status = "P";
			model.getRecord(probationConfirm, status, request,
					getprofileQuery(probationConfirm));

			String stat = "";
			if (status.equals("P")) {
				stat = "Confirmation Due List";
			}// end of if
			else if (status.equals("C")) {
				stat = "Confirmed List ";

			}// end of else if
			else if (status.equals("E")) {
				stat = "Extended Probation List";
			}// end of else if
			else if (status.equals("T")) {
				stat = "Terminated List";
			}// end of else if
			request.setAttribute("stat", stat);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String callstatus() {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			String status = "";
			String stat = "";
			System.out.println("The value of Search Staus ------------------------- : "+probationConfirm.getSearchStatus());
			try {
				//status = request.getParameter("status");
				 status =probationConfirm.getSearchStatus();
				// status = String.valueOf(status.charAt(0));
			}// end of try
			catch (Exception e) {
				logger.error("Exception in callstatus-------" + e);
			}// end of catch
			if ((probationConfirm.getSearchStatus().equals(""))) {
				status = "P";
			}// end of if
			else
				status=probationConfirm.getSearchStatus();
			
			System.out.println("The status : ================"+status);
			
			if (status.equals("P")) {
				stat = "Confirmation Due List";
			}// end of if
			else if (status.equals("C")) {
				stat = "Confirmed List ";

			}// end of else if
			else if (status.equals("E")) {
				stat = "Extended Probation List";
			}// end of else if
			else if (status.equals("T")) {
				stat = "Terminated List";
			}// end of else if

			probationConfirm.setSearchStatus(status);

			request.setAttribute("stat", stat);
			boolean result = model.isProbationApplicable();

			System.out.println("result===========" + result);
			if (result) {
				model.getRecord(probationConfirm, status, request,
						getprofileQuery(probationConfirm));
			} else {
				request.setAttribute("totalPage", 1);
				request.setAttribute("pageNo", 1);
				addActionMessage("Probation Setting not define.");
			}

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in callstatus---------" + e);
			e.printStackTrace();
		}
		return "success";

	}// end of callstatus

	public String callPage() throws Exception {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			callstatus();
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in callPage----------" + e);
		}
		return SUCCESS;
	}

	public String clearsearchRecord() {
		ProbationConfirmationModel model = new ProbationConfirmationModel();
		model.initiate(context, session);
		probationConfirm.setEmpeeCode("");
		probationConfirm.setEmpeeName("");
		probationConfirm.setEmpName("");
		probationConfirm.setDeptId("");
		probationConfirm.setDeptName("");
		probationConfirm.setBranchCode("");
		probationConfirm.setBranchName("");
		model.getRecord(probationConfirm, probationConfirm.getSearchStatus(),
				request, getprofileQuery(probationConfirm));
		model.terminate();

		return callstatus();
	}

	public String reset() {
		try {

			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			model.setBalanceDetails(probationConfirm);
			probationConfirm.setComments("");
			probationConfirm.setConfirmDate("");
			probationConfirm.setTerminationDate("");
			probationConfirm.setExtendedProbationDays("");
			probationConfirm.setEmpDataflag("true");
			probationConfirm.setConfirmEmpflag("false");
			// probationConfirm.setStatus("");
			if (probationConfirm.getStatus().equals("C"))
				probationConfirm.setEntitleFlag("true");

			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in reset----------" + e);
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String save() {

		boolean result = false;
		ProbationConfirmationModel model = null;
		String[] leaveCode = request.getParameterValues("leaveCode");// leave
		// code

		String[] clBal = request.getParameterValues("clBal");// closing
		try {

			model = new ProbationConfirmationModel();
			model.initiate(context, session);
			if (probationConfirm.getProbationCode().equals("null")
					|| probationConfirm.getProbationCode().equals("")) {
				result = model.saveRecord(probationConfirm, leaveCode, clBal);
				if (result) {
					addActionMessage("Record saved successfully");
					probationConfirm.setEmpDataflag("true");
					probationConfirm.setConfirmEmpflag("false");
					model.getEmployeeDetails(probationConfirm);
				} else {
					addActionMessage("Record canot saved");
					probationConfirm.setEmpDataflag("true");
					probationConfirm.setConfirmEmpflag("false");
				}
			} else {
				System.out.println("in else loop-------------");
				result = model.updateRecord(probationConfirm, leaveCode, clBal);
				if (result) {
					addActionMessage("Record updated successfully");
					probationConfirm.setEmpDataflag("true");
					probationConfirm.setConfirmEmpflag("false");
					model.getEmployeeDetails(probationConfirm);
				} else {
					addActionMessage("Record canot updated");
					probationConfirm.setEmpDataflag("true");
					probationConfirm.setConfirmEmpflag("false");
				}
			}

			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in save----------" + e);
		}
		getNavigationPanel(2);
		probationConfirm.setEnableAll("Y");
		return SUCCESS;
	}

	public String lock() {
		try {

			ProbationConfirmationModel model = new ProbationConfirmationModel();
			String[] leaveCode = request.getParameterValues("leaveCode");// leave
			// code

			String[] clBal = request.getParameterValues("clBal");// closing
			model.initiate(context, session);

			String result = model.lockProbation(probationConfirm, leaveCode,
					clBal);
			if (result.equals("record saved")) {
				addActionMessage("Record locked successfully");
				probationConfirm.setEmpDataflag("true");
				probationConfirm.setConfirmEmpflag("false");
				probationConfirm.setGoFlag("false");
			}
			if (result.equals("record not saved")) {
				addActionMessage("Record can't locked ");
				probationConfirm.setEmpDataflag("true");
				probationConfirm.setConfirmEmpflag("false");
			}
			if (result.equals("policy not")) {
				addActionMessage("Policy not defined ");
				probationConfirm.setEmpDataflag("true");
				probationConfirm.setConfirmEmpflag("false");
			}

			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in lock----------" + e);
		}
		getNavigationPanel(4);
		return SUCCESS;
	}

	public String unlock() {
		try {

			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			boolean flag = model.unlockProbation(probationConfirm);
			if (flag) {
				// addActionMessage("Record unlocked successfully");
				probationConfirm.setEmpDataflag("true");
				probationConfirm.setConfirmEmpflag("false");
			} else {
				addActionMessage("Record can't unlocked ");
				probationConfirm.setEmpDataflag("true");
				probationConfirm.setConfirmEmpflag("false");
			}

			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in lock----------" + e);
		}
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String showDetails() {
		try {
			getNavigationPanel(1);
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			String employeeID = request.getParameter("employeeId");
			String probationCode = request.getParameter("probCode");
			String stat = request.getParameter("stat");
			String operType = request.getParameter("opertype") != null ? request
					.getParameter("opertype")
					: "";
			if (operType.equals("online")) {
				String sqlQuery = "SELECT PROBATION_STATUS FROM HRMS_PROBATION_CONFIRM WHERE PROBATION_EMPID = "
						+ employeeID;
				Object data[][] = model.getSqlModel().getSingleResult(sqlQuery);
				if (data != null && data.length > 0) {
					return "process";
				}
			}
			probationConfirm.setEmpCode(employeeID);
			if (!probationCode.equals("0")) {
				// SAVED
				model.getEmployeeDetails(probationConfirm);
				if (stat.equals("P")) {
					getNavigationPanel(1);
				}
				if (stat.equals("C") || stat.equals("E") || stat.equals("T")) {
					getNavigationPanel(5);
					probationConfirm.setGoFlag("false");
					probationConfirm.setEnableAll("N");
				}
				String status = "SELECT PROBATION_LOCK FROM hrms_probation_confirm"
						+ "	WHERE PROBATION_EMPID="
						+ probationConfirm.getEmpCode();
				Object obj[][] = model.getSqlModel().getSingleResult(status);

				if (obj != null && obj.length > 0) {
					if (String.valueOf(obj[0][0]).equals("Y")) {
						// SAVED AND LOCKED
						System.out.println("in//SAVED AND LOCKED ");

					} else {
						// SAVED AND NOT LOCKED
						System.out.println("in//SAVED AND NOT LOCKED");

					}
				}

			} else {
				// NOT SAVED

				model.setEmployeeInfo(probationConfirm);
			}
			probationConfirm.setEmpDataflag("true");
			probationConfirm.setConfirmEmpflag("false");
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in showDetails----------" + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String back() {
		try {
			probationConfirm.setEmpDataflag("false");
			probationConfirm.setConfirmEmpflag("true");
			probationConfirm.setProbationCode("");
		} catch (Exception e) {
			logger.info("Exception in back----------" + e);
		}
		return call();
	}

	public String call() {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			String status = "P";
			String stat = "";
			if (status.equals("P")) {
				stat = "Pending For Probation Confirmation List";
			}// end of if
			request.setAttribute("stat", stat);
			model.getRecord(probationConfirm, status, request,
					getprofileQuery(probationConfirm));
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in call----------" + e);
		}
		return SUCCESS;
	}

	public String getEntiteldLeaves() {
		try {
			ProbationConfirmationModel model = new ProbationConfirmationModel();
			model.initiate(context, session);
			model.getEntiteldLeaves(probationConfirm);
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in leaveEntitled----------" + e);
		}
		getNavigationPanel(1);
		probationConfirm.setEnableAll("Y");
		return SUCCESS;
	}

	public String f9CurrentBranchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Branch Code", "Branch Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentBranchCode", "currentBranch" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9NewBranchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Branch Code", "Branch Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newBranchCode", "newBranch" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9CurrentDepartmentaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Department Code", "Department Nmae" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentDepartmentCode", "currentDepartment" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9NewDepartmentaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Department Code", "Department Nmae" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newDepartmentCode", "newDepartment" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9CurrentEmpTypeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  "
				+ " ORDER BY  TYPE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Emp Type Code", "Emp Type Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentEmployeeTypeCode",
				"currentEmployeeType" };

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

	public String f9NewEmpTypeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  "
				+ " ORDER BY  TYPE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Emp Type Code", "Emp Type Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newEmployeeTypeCode", "newEmployeeType" };

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

	public String f9CurrentGradeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CADRE_ID,CADRE_NAME FROM HRMS_CADRE "
				+ " ORDER BY CADRE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Grade Name", "Grade Code" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentGradeCode", "currentGrade" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9NewGradeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CADRE_ID,CADRE_NAME FROM HRMS_CADRE "
				+ " ORDER BY CADRE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Grade Code", "Grade Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newGradeCode", "newGrade" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";

		// query += getprofileQuery(probationConfirm);
		
		query += getprofileQuery(probationConfirm);

		query += " ORDER BY EMP_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Code", "Employee Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenNo", "empeeName", "empeeCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9departmentaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Department ID", "Department Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptId", "deptName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9branchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Branch Code", "Branch Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchCode", "branchName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9CurrentDesignationaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Designation Code", "Designation Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentDesignationCode", "currentDesignation" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9NewDesignationaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Designation Code", "Designation Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newDesignationCode", "newDesignation" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Currentdivaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		if (probationConfirm.getUserProfileDivision() != null
				&& probationConfirm.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ probationConfirm.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", "Division Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "currentdivisionCode", "currentdivision" };

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

	public String f9Newdivaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";

		if (probationConfirm.getUserProfileDivision() != null
				&& probationConfirm.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ probationConfirm.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", "Division Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "newdivisionCode", "newdivision" };

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
	 * To select the policy of selected division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9policy() throws Exception {
		String query = " SELECT DISTINCT LEAVE_POLICY_CODE, LEAVE_POLICY_NAME  FROM HRMS_LEAVE_POLICY_HDR "
						+" WHERE DIV_CODE="+probationConfirm.getNewdivisionCode()
						+ " ORDER BY  LEAVE_POLICY_CODE ";

		String[] headers = { "Policy Code", "Policy Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "policyCode", "policyName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ProbationConfirmation_getEntiteldLeaves.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method

	/**
	 * To select the policy of selected division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9generateConfTemp() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR "
				+ "  WHERE TEMPLATE_TYPE=3 "
				// +" WHERE TEMPLATE_TYPE=TO_CHAR((SELECT LETTERTYPE_ID FROM
				// HRMS_LETTERTYPE WHERE LETTERTYPE LIKE '%CONFIRMATION%')) "
				+ " ORDER BY TEMPLATE_ID";

		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "templateCode", "templateName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method

	public String f9autho() throws Exception {
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ORDER BY EMP_ID";

		String[] headers = { getMessage("token"), getMessage("empName") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "authoToken", "authoName", "authoCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method

	public String f9Allemployee() throws Exception {
		/*
		 * String query = "SELECT
		 * HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS
		 * EMP_NAME,EMP_ID,'0','P' " + "FROM HRMS_EMP_OFFC " + "WHERE EMP_TYPE =
		 * 1 AND EMP_STATUS='S' ";
		 */
		String query = "SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME,EMP_ID,0,'P' "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND EMP_STATUS='S' AND EMP_ID NOT IN(SELECT DISTINCT PROBATION_EMPID FROM HRMS_PROBATION_CONFIRM WHERE PROBATION_EMPID IS NOT NULL) "
				+ "UNION "
				+ "SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,EMP_ID ,NVL(HRMS_PROBATION_CONFIRM.PROBATION_CODE,0),'P' "
				+ "FROM HRMS_PROBATION_CONFIRM "
				+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_CONFIRM.PROBATION_EMPID=HRMS_EMP_OFFC.EMP_ID) ";
		query += getprofileQuery(probationConfirm);
		query += " AND  EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR)  AND PROBATION_LOCK='N' OR HRMS_PROBATION_CONFIRM.PROBATION_STATUS='P'";

		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "selectemployeetoken", "selectemployeeName",
				"empCode", "probCode", "stat" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlag = "true";

		String submitToMethod = "ProbationConfirmation_showDetails.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
}
