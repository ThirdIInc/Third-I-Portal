package org.struts.action.payroll;

import java.io.File;

import org.nfunk.jep.function.Str;
import org.paradyne.bean.payroll.EmailPayrollReport;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.SendEmail;
import org.paradyne.lib.Utility;
import org.paradyne.model.admin.srd.SendMailModel;
import org.paradyne.model.payroll.ot.OtMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1383
 *
 */
public class EmailPayrollReportAction extends ParaActionSupport {
	EmailPayrollReport emailBean;
	public void prepare_local() throws Exception {
		emailBean = new EmailPayrollReport();
	}
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(OtMISReportModel.class);
	public Object getModel() {
		return emailBean;
	}
	/**
	 * @return employee
	 */
	public final String f9employeeToMail() {
		try {
			String query = "SELECT NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			query += getprofileQuery(emailBean);
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P' ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = {"Email Address Book"};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"mailToEmployeeEmail"};
			int[] columnIndex = {0};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public final String f9employeeCCMail() {
		try {
			String query = "SELECT NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			query += getprofileQuery(emailBean);
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P' ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = {"Email Address Book"};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"ccEmailIds"};
			int[] columnIndex = {0};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public final String f9employeeToMailOld() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			query += getprofileQuery(emailBean);
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P' ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = { getMessage("employee.id"), getMessage("employee")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"mailToEmployeeToken", "mailToEmployeeName", "mailToEmployeeId", "mailToEmployeeEmail"};
			int[] columnIndex = {0, 1, 2, 3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public String f9employeeMailId() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			query += getprofileQuery(emailBean);
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P' ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = { getMessage("employee.id"), getMessage("employee")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"mailToEmployeeToken", "mailToEmployeeName", "mailToEmployeeId", "mailToEmployeeEmail"};
			int[] columnIndex = {0, 1, 2, 3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	/**This method sends a mail to the ids enterd/selected with attachment.
	 * Attachment path is fetched via request parameter & must be named
	 * as 'reportPath' while setting it in the request.
	 * @return INPUT
	 * 
	 */
	public String sendEmail() {
		try {
			MailModel mailModel = new MailModel();
			mailModel.initiate(context, session);
			System.out.println("SEND MAIL111::");
			String backAction = emailBean.getActionBack();
		//	Object[] toEmailIds = request.getParameterValues("mailToListEmpEmail");
			Object[] toEmailIds=null;
			String toMail = emailBean.getMailToEmployeeEmail();
			try {
				if (toMail.charAt(toMail.length() - 1) == ';') {
					toMail = toMail.substring(0,toMail.length() - 1);
				}
				toEmailIds = (Object[]) toMail.split(";");
			} catch (Exception e) {
				// TODO: handle exception
			}
			Object[] ccMailIds=null;
			if(!emailBean.getCcEmailIds().equals("")){
				ccMailIds = emailBean.getCcEmailIds().split(";");
			}
			
			String emailSubject = emailBean.getSubject();
			String emailDescription = emailBean.getDescription();
			
			String reportPath =  emailBean.getAttachmentPath();
			Object [] totalEmailIds = null;
			if (toEmailIds != null && toEmailIds.length > 0) {
				totalEmailIds = toEmailIds;
			}
			if (ccMailIds != null && ccMailIds.length > 0) {
				totalEmailIds = ccMailIds;
			}
			if ((toEmailIds != null && toEmailIds.length > 0) && (ccMailIds != null && ccMailIds.length > 0)) {
				totalEmailIds = Utility.joinOneDimArray(toEmailIds, ccMailIds);
			}
			String [] mailToEmailIds = null;
			if (totalEmailIds != null && totalEmailIds.length > 0) {
				mailToEmailIds = new String [totalEmailIds.length];
				for (int i = 0; i < totalEmailIds.length; i++) {
					mailToEmailIds[i] = String.valueOf(totalEmailIds[i]);
					System.out.println("mailToEmailIds::"+mailToEmailIds[i]);
				}
			}
			
			//Object[][] mailBoxData = mailModel.getServerMailBox("", emailBean.getUserEmpId());
			mailModel.terminate();
			//MailModel mod=new MailModel();
		
			//MailUtility email=new MailUtility();
			//String[][] fromEmail = getDefaultMailIds();
			
			final MailUtility mailModel1 = new MailUtility();
			mailModel1.initiate(context, session);
			String[] fromEmail1= getDefaultMailIds(emailBean.getUserEmpId());
			SendMailModel sendMail = new SendMailModel();
			sendMail.initiate(context, session);
			emailDescription=sendMail.getMassMessage(emailDescription);
			if(fromEmail1 !=null && fromEmail1.length>0)
			try {
				mailModel1.sendMail(mailToEmailIds, fromEmail1,
						emailSubject, emailDescription, reportPath, null, false);
				deleteFile(reportPath);
				addActionMessage("Mail sent successfully.");
			} catch (Exception e) {
				addActionMessage("Error sending email.");
			}else{
				emailBean.setSentFlag("false");
				addActionMessage("E-mail id is not defined for you in official details");
			}
			//logger.info("backAction set=="+backAction);
			request.setAttribute("action",backAction);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	public void deleteFile(String reportPath){
			File file = new File(reportPath);
		if(file.exists()){
			file.delete();
		}
	}
	public String[] getDefaultMailIds(String empId) {
		MailModel model = new MailModel();
		model.initiate(context, session);
		//String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_SERVER_CODE ";
		String fromQuery = "SELECT NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ') FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID"
			+" AND HRMS_EMP_ADDRESS.ADD_TYPE='P') WHERE HRMS_EMP_ADDRESS.ADD_EMAIL IS NOT NULL AND HRMS_EMP_ADDRESS.EMP_ID= "+empId;
		Object fromEmp[][] = model.getSqlModel().getSingleResult(fromQuery);
		
		if (fromEmp != null && fromEmp.length > 0) {
			String[] mailIds = new String[fromEmp.length];
			for (int i = 0; i < fromEmp.length; i++) {
				mailIds[i] = String.valueOf(fromEmp[i][0]);
			}
		return mailIds;
		}else return null;
	}
}
