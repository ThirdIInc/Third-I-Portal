/**
 * 
 */
package org.struts.action.admin.srd;

import java.util.ArrayList;
import org.paradyne.bean.admin.srd.SendMail;
import org.paradyne.lib.MailUtility;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author shashikant
 * 
 */

public class BirthdayAction extends ParaActionSupport {
	SendMail sendMail;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BirthdayAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		sendMail = new SendMail();
		sendMail.setMenuCode(593);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return sendMail;
	}

	public SendMail getSendMail() {
		return sendMail;
	}

	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}

	/**
	 * to get the list on load
	 */
	/*
	 * public void prepare_withLoginProfileDetails() throws Exception {
	 * 
	 * SendMailModel model = new SendMailModel();
	 * model.initiate(context,session); model.getList(sendMail,request);
	 * model.terminate(); }
	 */

	public String deptlist() throws Exception {
		logger.info("in deptlist model");
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);

		String query = "";

		String code = request.getParameter("code");
		String actionName = request.getParameter("actionName");

		logger.info("code--------- " + code);
		logger.info("actionName--------- " + actionName);

		if (actionName.equals("dp")) {
			query = " SELECT HRMS_DEPT.DEPT_ID , DEPT_NAME FROM HRMS_DEPT";
		} else if (actionName.equals("dv")) {
			query = " SELECT HRMS_DIVISION.DIV_ID , DIV_NAME FROM HRMS_DIVISION";
			if(sendMail.getUserProfileDivision() !=null && sendMail.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+sendMail.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
		} else if (actionName.equals("br")) {
			query = " SELECT HRMS_CENTER.CENTER_ID , CENTER_NAME FROM HRMS_CENTER";
		}

		Object obj[][] = model.getSqlModel().getSingleResult(query);
		ArrayList<Object> arr = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {
			SendMail sm = new SendMail();
			sm.setDepartmentCode(String.valueOf(obj[i][0]));
			sm.setDepartmentName(String.valueOf(obj[i][1]));
			arr.add(sm);
		}// end of for

		request.setAttribute("code", code);

		sendMail.setList(arr);
		model.terminate();
		return "successjsp";
	}

	public String sendMail() throws Exception {
		String result = "false";
		String imgPath = "";
		// String xyz[]=request.getParameterValues("empCode");
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		String massMessage = model.getMassMessage(sendMail.getMessage());
		Object[][] sendToEmp = (Object[][]) model.getSendToEmp(sendMail);

		MailUtility mail = new MailUtility();
		mail.initiate(context, session);
		String chkDeftMail[] = mail.getDefaultMailIds();
		if (!(chkDeftMail.length > 0)) {
			addActionMessage("Message sent failed.\n Please configure mail account properly. ");
			return SUCCESS;
		}
		if (!sendMail.getUploadFileName().equals("")) {
			imgPath = sendMail.getUploadFileName();
		}
		if (!(String.valueOf(sendMail.getEmployeeCode()).equals("null") || String
				.valueOf(sendMail.getEmployeeCode()).equals(""))) {
			System.out.println("Under emp mail");
			Object[][] sendByEmp = (Object[][]) model.getSendByEmp(sendMail);
			String[] sendToEmployee = new String[1];
			for (int i = 0; i < sendByEmp.length; i++) {
				sendToEmployee[0] = String.valueOf(sendByEmp[0][0]);
			}
			if (sendByEmp.length > 0) {
			/*	mail.sendMail(sendToEmployee, mail.getDefaultMailIds(),
						sendMail.getSubject(), massMessage, imgPath,true);
				result = "true";*/
			}
		}
		if (!(sendMail.getDivisionCode().equals(""))
				|| !(sendMail.getBranchCode().equals(""))
				|| !(sendMail.getDeptCode().equals(""))
				|| !(sendMail.getDesgCode().equals(""))) {

			String[] sendTo = new String[sendToEmp.length];
			for (int i = 0; i < sendToEmp.length; i++) {
				sendTo[i] = String.valueOf(sendToEmp[i][0]);
			}
			if (sendToEmp.length > 0) {
				/*mail.sendMail(sendTo, mail.getDefaultMailIds(), sendMail
						.getSubject(), massMessage, imgPath);
				result = "true";*/
			}
		}
		model.terminate();
		if (result.equals("true")) {
			addActionMessage("Message sent successfully ");
			// TO insert sent mail data into HRMS_SEND_MAILS table

			model.saveMail(sendMail);
		} else {
			addActionMessage("Message could not be sent  ");
		}
		reset();
		return SUCCESS;

	}

	/*
	 * public String sendMail1()throws Exception { MailUtility model = new
	 * MailUtility(); model.initiate(context,session);
	 * String[]to_mailIds={"lakkichand.golegaonkar@glodyne.com","shashikant.doke@glodyne.com"};
	 * model.sendMail(to_mailIds, model.getDefaultMailIds(), " By common mail
	 * method", "hi", ""); model.terminate(); return SUCCESS; }
	 */
	/**
	 * to send birthday mail
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String sendBirthDayList() throws Exception {
		try {
			String code[] = request.getParameterValues("hdeleteCode");
			for (int i = 0; i < code.length; i++) {
				logger.info("Emp Idssssss" + code[i]);

			}// end of loop
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String result = model.sendBirthDayMail(sendMail, code, request);
			if (result.equals("true")) {
				addActionMessage("Message sent successfully. ");
			}// end of if
			else if (result.equals("absentDob")) {
				addActionMessage("Message sent failed.\n No birthdays have been found on today's date");
			}// end of else if

			else if (result.equals("unavailable")) {
				addActionMessage("Message sent failed.\n No email id of the employee are available");
			}// end of else if
			else {
				addActionMessage("Message sent failed.\n Please configure mail account properly. ");
			}// end of else
			model.terminate();
			sendMail.setEmpid("");
			sendMail.setEmployee("");
			sendMail.setToken("");
			model.getList(sendMail, request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "birthdayform";

	}

	public String birthDay() throws Exception {

		return "birthDay";
	}

	public String f9division() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ORDER BY DIV_ID  ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "BirthdayMail_resetEmpMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "branchCode", "branchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "BirthdayMail_resetEmpMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "BirthdayMail_resetEmpMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9designation() throws Exception {
		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "BirthdayMail_resetEmpMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9employee() throws Exception {
		String query = " SELECT EMP_ID,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME FROM hrms_emp_offc ";
		
		query += getprofileQuery(sendMail);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				 

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeCode", "employeeName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "BirthdayMail_resetMassMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9sendemp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID,'   --Select--   ','','' from hrms_emp_offc  ";
		
		query += getprofileQuery(sendMail);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "15", "85" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "token", "employee", "empid" };

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
		String submitToMethod = "BirthdayMail_resetAll.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String getSentItems() {
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		model.getSentItems(sendMail);
		model.terminate();
		return SUCCESS;
	}

	public String resetAll() {
		sendMail.setDepartmentName("");
		sendMail.setDivName("");
		sendMail.setBranch("");
		// String xyz[]=request.getParameterValues("hdeleteCode");

		/*
		 * for (int i = 0; i < xyz.length; i++) {
		 * System.out.println("__________"+xyz[i]);
		 * 
		 *  }
		 */
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		System.out
				.println("in reset all value of flag is" + sendMail.getFlag());
		if (sendMail.getFlag().equals("B")) {
			model.getList(sendMail, request);
			model.terminate();
			return "birthdayform";
		} else {
			model.getAnniversaryList(sendMail, request);
			model.terminate();
			return "anniversary";
		}

	}

	public String resetEmpMail() {
		sendMail.setEmployeeCode("");
		sendMail.setEmployeeName("");
		return getSubject();

	}

	public String getSubject() {
		System.out
				.println("__________________________" + sendMail.getMessage());
		sendMail.setSubject(sendMail.getSubject());
		sendMail.setMessage(sendMail.getMessage());
		return SUCCESS;
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		sendMail.setDivisionCode("");
		sendMail.setDivisionName("");
		sendMail.setBranchCode("");
		sendMail.setBranchName("");
		sendMail.setDeptCode("");
		sendMail.setDeptName("");
		sendMail.setDesgCode("");
		sendMail.setDesgName("");
		sendMail.setEmployeeCode("");
		sendMail.setEmployeeName("");
		sendMail.setSubject("");
		sendMail.setMessage("");
		sendMail.setUploadFileName("");
		return SUCCESS;

	}

	public String resetMassMail() {
		sendMail.setDivisionCode("");
		sendMail.setDivisionName("");
		sendMail.setBranchCode("");
		sendMail.setBranchName("");
		sendMail.setDeptCode("");
		sendMail.setDeptName("");
		sendMail.setDesgCode("");
		sendMail.setDesgName("");
		return SUCCESS;

	}

	public String f9emp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("type.code"), getMessage("type.name") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "typMaster.typeID", "typMaster.typeName" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "EmpTypeMaster_typeRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9mailTemplate() throws Exception {

		String query = " SELECT TEMPLATE_ID, NVL(TEMPLATE_NAME,'') FROM HRMS_BIRTHDAYTEMPLATE_HDR ORDER BY TEMPLATE_NAME";
		String[] headers = { getMessage("template.code"),
				getMessage("template") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "tempCode", "tempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String sendBirthDay() {
		System.out.println("enter to sendbirthday method");
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		sendMail.setFlag("B");
		model.getList(sendMail, request);
		model.terminate();
		return "birthdayform";
	}

	public String sendAnniversary() {
		System.out.println("enter to sendanniversary method");
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		sendMail.setFlag("A");
		model.getAnniversaryList(sendMail, request);
		model.terminate();
		return "anniversary";
	}

	public String defaultpage() {
		return "birthDay";
	}

	public String f9anniversarymailTemplate() throws Exception {

		String query = " SELECT TEMPLATE_ID, NVL(TEMPLATE_NAME,'') FROM HRMS_ANNIVERSARY_TEMPLATE ORDER BY TEMPLATE_ID";
		String[] headers = { getMessage("template.code"),
				getMessage("template") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "tempCode", "tempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	// for sending Anniversary mail

	public String sendAnniversaryMailList() {
		String code[] = request.getParameterValues("hdeleteCode");

		for (int i = 0; i < code.length; i++) {
			logger.info("Emp Idssssss" + code[i]);

		}// end of loop
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		String result = model.sendAnniversaryDayMail(sendMail, code);
		if (result.equals("true")) {
			addActionMessage("Message sent successfully. ");
		}// end of if
		else if (result.equals("absentmarriagedate")) {
			addActionMessage("Message sent failed.\n No email id of the employee are available");
		}// end of else if
		else {
			addActionMessage("Message sent failed.\n Please configure mail account properly. ");
		}// end of else
		model.terminate();

		sendMail.setEmpid("");
		sendMail.setEmployee("");
		sendMail.setToken("");
		model.getAnniversaryList(sendMail, request);
		return "anniversary";

	}

	public String homepage() {

		if (sendMail.getFlag().equals("B")) {

			return "birthDay";
		} else {

			return "birthDay";
		}

	}
}
