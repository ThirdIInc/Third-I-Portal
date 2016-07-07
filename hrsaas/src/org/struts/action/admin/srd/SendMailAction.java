/**
 * 
 */
package org.struts.action.admin.srd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import org.paradyne.bean.admin.srd.SendMail;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.email.EmailAttachment;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.lib.ParaActionSupport;

import com.itextpdf.text.log.SysoLogger;

/**
 * @author shashikant
 * 
 */

public class SendMailAction extends ParaActionSupport {
	SendMail sendMail;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SendMailAction.class);
	String[][] fromMailId = null;
	int mailCount = 0;
	int fireCounter = 0;
	HtmlEmail email = null;
	EmailAttachment attachment = new EmailAttachment();
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		sendMail = new SendMail();
		sendMail.setMenuCode(592);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return sendMail;
	}

	/**
	 * @return the sendMail
	 */
	public SendMail getSendMail() {
		return sendMail;
	}

	/**
	 * @param sendMail
	 *            the sendMail to set
	 */
	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}

	/**
	 * to get the list on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String dataPath = getText("data_path") + "/upload" + poolName
					+ "/MASSMAILATTACH/";
			sendMail.setDataPath(dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}

	/**
	 * To send mass mails using HrWork Application
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		String result = "false";
		try {
			String attachmentPath = "";
			String[] attachments = null;
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String massMessage = model.getMassMessage(sendMail.getMessage());
			Object[][] sendToEmp = (Object[][]) model.getSendToEmp(sendMail);
			// calling functions from mailUtility library
			
			MailUtility mail = new MailUtility();
			mail.initiate(context, session);
			String chkDeftMail[] = mail.getDefaultMailIds();
			String[][] fromMailIds = getDefaultMailIds();
			Object[][] mailBoxData = getServerMailBox("", fromMailIds[0][0]);
			
			if (!(chkDeftMail.length > 0)) {
				addActionMessage("Mail sent failed.\n Please configure mail account properly. ");
				return SUCCESS;
			}
			
			if (!sendMail.getAttachmentFile().equals("")) {
				attachmentPath = sendMail.getDataPath()
						+ sendMail.getAttachmentFile();
				logger.info("########## ATTACHMENT PATH ###########"+ attachmentPath);
				attachments = new String[]{attachmentPath};
				
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
					System.out.println("-------------$$$$$$$$$$$$----------");
					sendMail(sendToEmployee, fromMailIds, sendMail.getSubject(), massMessage, mailBoxData, attachments);
					result = "true";
				}
			}
			if (!(sendMail.getDivisionCode().equals(""))
					|| !(sendMail.getBranchCode().equals(""))
					|| !(sendMail.getDeptCode().equals(""))
					|| !(sendMail.getDesgCode().equals(""))) {

				String[] sendTo = new String[sendToEmp.length];
				for (int i = 0; i < sendToEmp.length; i++) {
					sendTo[i] = String.valueOf(sendToEmp[i][0]);
					System.out.println("=========sendTo==============="+sendTo[i]);
				}
				if (sendToEmp.length > 0) {

					sendMail(sendTo, fromMailIds, sendMail.getSubject(),
							massMessage, mailBoxData, attachments);

					result = "true";
				}
			}
			String ccEmailIds[] = sendMail.getCcMailId().split(";");
			String newccEmailIds[] = null;
			if (ccEmailIds != null && ccEmailIds.length > 0) {
				newccEmailIds = new String[ccEmailIds.length];
				for (int i = 0; i < ccEmailIds.length; i++) {
					newccEmailIds[i] = ccEmailIds[i];

				}
			}
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			sendMail(newccEmailIds, fromMailIds, sendMail.getSubject(),	massMessage, mailBoxData, attachments);
			System.out.println("&&&&&&&&&&&&&%%%%%%%%%%%%%%%>>>>>>>>&&&&&&&&&&&&&&&&&&");
			if (result.equals("true")) {
				addActionMessage("Mail sent successfully ");
				// TO insert sent mail data into HRMS_SEND_MAILS table
				model.saveMail(sendMail);
			} 
			else {
				addActionMessage("Mail could not be sent  ");
			}
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	/**
	 * To select any division to which mails are to be sent
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION";
		
		if(sendMail.getUserProfileDivision() !=null && sendMail.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+sendMail.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "SendMail_resetEmpMail.action";
		sendMail.setMasterMenuCode(42);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * To select any branch to which mails are to be sent
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "branchCode", "branchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "SendMail_resetEmpMail.action";
		sendMail.setMasterMenuCode(31);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * To select any department to which mails are to be sent
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "SendMail_resetEmpMail.action";
		sendMail.setMasterMenuCode(23);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * To select any designation to which mails are to be sent
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9designation() throws Exception {
		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "SendMail_resetEmpMail.action";
		sendMail.setMasterMenuCode(26);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * To select any employee to whom mails are to be sent
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  FROM hrms_emp_offc ";
		query += getprofileQuery(sendMail);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		 
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeCode", "employeeName", "employeeCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "SendMail_resetMassMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * TO search, select and set Sent Mails
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT MAIL_CODE, SUBJECT, TO_CHAR(DATE_TIME,'DD-MM-YYYY') FROM HRMS_SEND_MAIL ORDER BY MAIL_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("mail.Id"), getMessage("subject"),
				getMessage("date") };

		String[] headerWidth = { "15", "70", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "sendMail.mailCode", "sendMail.subject",
				"sendMail.date" };

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
		String submitToMethod = "SendMail_getSentItems.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9sendemp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID from hrms_emp_offc Where EMP_STATUS='S' ";

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

		String[] fieldNames = { "empid", "employee", "empid" };

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
		String submitToMethod = "SendMail_resetAll.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Setting searched sent items
	 * 
	 * @return String
	 */
	public String getSentItems() {
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		model.getSentItems(sendMail);
		sendMail.setViewModeFlag(true);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * to clear the department,branch, division fields after selecting employee
	 * from search window
	 * 
	 * @return String
	 */
	public String resetAll() {
		sendMail.setDepartmentName("");
		sendMail.setDivName("");
		sendMail.setBranch("");

		return "birthDay";

	}

	/**
	 * Reset employee field on selecting any other filter
	 * 
	 * @return String
	 */
	public String resetEmpMail() {
		sendMail.setEmployeeCode("");
		sendMail.setEmployeeName("");
		return getSubject();

	}

	/**
	 * To set the subject field after resetting other fields
	 * 
	 * @return
	 */
	public String getSubject() {
		logger.info("Message-----: " + sendMail.getMessage());
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
		sendMail.setCcMailId("");
		sendMail.setAttachmentFile("");
		return SUCCESS;

	}

	/**
	 * Reset all fields on selecting Employee
	 * 
	 * @return String
	 */
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

	/**
	 * Searching employee type
	 * 
	 * @return String
	 * @throws Exception
	 */
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
	
	
	
	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData, String[] attachments) {
		fromMailId = fromMailIds;

		// email. setDebug(true);
		try {
			// HtmlEmail email=null;
			int patch = Integer.parseInt(String.valueOf(mailBoxData[0][16]));
			if(patch==0)
			{
				patch=80;
			}
			
			if(patch>0)
			{
				System.out.println("patch"+patch);
				int count = toMailId.length / patch;
				int rem = toMailId.length % patch;
				if (rem > 0) {
					count = count + 1;
				}
				int k = 0;
				if (patch > toMailId.length) {
					patch = toMailId.length;
				}
				System.out.println();
				System.out.println(" COUNT  *** NO OF TIMES MAIN FOR LOPP RUNNUNG    :"
								+ count);
				for (int i = 0; i < count; i++) {
					String[] tomailIds = null;
					if (i == count - 1) {
						if (rem > 0) {
							tomailIds = new String[rem];
						} else {
							tomailIds = new String[patch];
						}
					} else {
						tomailIds = new String[patch];
					}
					System.out.println();
					//System.out.println("tomailIds.length"+ tomailIds.length);
					for (int j = 0; j < tomailIds.length; j++) {
						//System.out.println("tomailIds[j]   "+tomailIds[j]);
						tomailIds[j] = toMailId[k];
						//System.out.println("tomailIds[j]==========>>>>>>>>   "+tomailIds[j]);
						k++;
					}
					// HtmlEmail email=setHtmlEmail(mailBoxData, subject, textBody,
					// request, tomailIds);
					fireEmail(mailBoxData, subject, textBody, tomailIds, attachments);

				}	
			}
			
		} catch (Exception e) {
			System.out.println();
			System.out.println("EXCEPTION 2......");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds, String[] attachments) {

		try {

			for (int i = 0; i < tomailIds.length; i++) {
				System.out.println("----EMP ID IN FIRE EMAIL---------"+tomailIds[i]);
			}
			if (mailCount < fromMailId.length) {
				/*
				 * if(mailCount==0){ email=setHtmlEmail(mailBoxData, subject,
				 * textBody, request, tomailIds); }
				 */
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], ""+ fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], ""+ fromMailId[mailCount][0]);
				System.out.println();
				System.out.println("USER NAME    "+ fromMailId[mailCount][0] + "     PASS    "+ fromMailId[mailCount][1]);
				if (String.valueOf(mailBoxData[0][12]).equals("D")) {
					email.setAuthentication(String
							.valueOf(mailBoxData[mailCount][3]), String
							.valueOf(mailBoxData[0][4]));

				} else {
					email.setAuthentication(String
							.valueOf(fromMailId[mailCount][0]), String
							.valueOf(fromMailId[mailCount][1]));

				}
				if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
					try {
						email.setPopBeforeSmtp(true, String
								.valueOf(mailBoxData[0][7]), String
								.valueOf(mailBoxData[0][3]), String
								.valueOf(mailBoxData[0][4]));

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
					email.setSmtpWithTLS(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}
				if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					email.setSmtpWithSSL(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}
				String str ="";
				URL url=null;
				if(attachments!=null && attachments.length>0){
					attachment.setDescription("");
					attachment.setDisposition(EmailAttachment.ATTACHMENT);
					attachment.setName(sendMail.getAttachmentFile());
					attachment.setPath(String.valueOf(attachments[0]));
					attachment.setURL(url);
					email.attach(attachment);
					logger.info(" >>>>>> ADDING ATTACHMENT");
				}
				str = email.send();
				
 
				//mailCount ++;
				System.out.println("send Error  :" + str);
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						// AutoBirthday.out.println();
						// AutoBirthday.out.write(" unsentList...Valid
						// "+unsentList.get(i));
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);
					}
					/*
					 * List invalidList=email.getInvalidList(); for (int i = 0;
					 * i < invalidList.size(); i++) {
					 * AutoBirthday.out.println(); AutoBirthday.out.write("
					 * invalid address"+invalidList.get(i)); }
					 */
					if (fireCounter < 5) {
						fireEmail(mailBoxData, subject, textBody, toObj, attachments);
					} else {
						fireCounter = 0;
					}
					// addTo_CC(email, toObj);
					// str= email.send();
				}
				// AutoBirthday.out.println();AutoBirthday.out.write("MAIL SENT
				// SUCCESSFULLY *********** ... FOR SYSMAIL ID "+mailCount);

			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}

			/*
			 * System.out .println("mail send
			 * successfully________________________________ 1
			 * mailCount"+mailCount);
			 */
		} catch (Exception e) {
			/*
			 * email=setHtmlEmail(mailBoxData, subject, textBody, request,
			 * tomailIds); mailCount++;
			 */
			mailCount++;
			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
			System.out.println("" + e);
			System.out.println();
			// fireEmail(mailBoxData, subject, textBody, request, tomailIds);
		}

	}
	
	
	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {

		try {
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
			for (int i = 0; i < toObj.length; i++) {
				// AutoBirthday.out.println();
				// AutoBirthday.out.write(" "+toObj[i]+" ");
				System.out.println("-------toObj[i]--------"+toObj[i]);
				// AutoBirthday.out.println();
				email.addBcc(toObj[i]);
			}
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return email;
	}
	
	
	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";

		try {
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String massMessage = model.getMassMessage(sendMail.getMessage());
			
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
		 
			// replacedString=getHtmlTextWithBackground(email, replacedString,
			// request);
			email.setHtmlMsg(massMessage);
			email.setTextMsg("Your email client does not support HTML messages");
			for (int i = 0; i < tomailIds.length; i++) {
				System.out.println("----EMP ID HtmlEmail--------"+tomailIds[i]);
			}
			
			addTo_CC(email, tomailIds);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	
	
	public Object[][] getServerMailBox(String empId, String fromMailId)
	{
		System.out.println("empId  in getServerMailBox   " + empId);
		System.out.println("fromMailId  in getServerMailBox   " + fromMailId);
		String getMailBox = "";
		Object[][] empMailBoxData = null;
		Object[][] sysMailData = null;
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		
		if (empId != null && !empId.equals("")) {// this is for pop before
			// smtp check
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER  "
					+ " INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
					+ " WHERE EMAIL_EMP_ID="
					+ empId
					+ "  AND EMAIL_OFFICIAL_FLAG='Y'";
			empMailBoxData = model.getSqlModel().getSingleResult(getMailBox);
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = model.getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
			System.out.println(" in POP BEFORE SMTP");
		}
		if (empMailBoxData == null || empMailBoxData.length == 0) {

			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " ,NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER "
					+ " WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
			empMailBoxData = model.getSqlModel().getSingleResult(getMailBox);
			System.out.println(" ELSE POP");
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = model.getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
		}
		if (sysMailData != null && sysMailData.length > 0) {
			for (int i = 0; i < sysMailData[0].length; i++) {
				System.out.println("  EMAIL SERVER DATA  : "
						+ String.valueOf(sysMailData[0][i]));
			}
		}
		if (empMailBoxData != null && empMailBoxData.length > 0) {
			for (int i = 0; i < empMailBoxData.length; i++) {
				for (int j = 0; j < empMailBoxData[0].length; j++) {
					System.out.println("empMailBoxData   [" + i + "][" + j
							+ "]" + empMailBoxData[i][j]);
				}

			}

		}

		return empMailBoxData;
	}
	
	public String[][] getDefaultMailIds() {
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = model.getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}
	
	public void viewAttachmentFile() {
		try {
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String attachmentFile = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				attachmentFile = request.getParameter("fileName");
				logger.info("fileName--->>>" + attachmentFile);
				attachmentFile = attachmentFile.replace(".", "#");
				String[] extension = attachmentFile.split("#");
				String ext = extension[extension.length - 1];
				attachmentFile = attachmentFile.replace("#", ".");
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
				if (attachmentFile == null) {
					if (attachmentFile.length() <= 0) {
						attachmentFile = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = sendMail.getDataPath()+attachmentFile;
				logger.info(">>>>>>>>>>>>>> PATH >>>>>>>>>>>>>"+path);
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
						+ attachmentFile + "\"");

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
	}

}
