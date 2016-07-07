package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.action.common.LoginAction;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:05-05-2009
 *
 */
public class CandidateRegistrationModel extends ModelBase {
	Random random = new Random();
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginAction.class);

	/**
	 * following function is called when submit button is clicked.
	 * @param bean
	 * @param request 
	 * @return
	 */

	public boolean saveData(CandidateLoginBean bean, HttpServletRequest request)
			throws Exception {
		boolean result = false;
		try {
			/* String maxCode = "SELECT NVL(MAX(CAND_CODE)+1,1) FROM HRMS_REC_CAND_LOGIN";
					candCode = getSqlModel().getSingleResult(maxCode); */
			Object[][] addLoginData = new Object[1][4];
			addLoginData[0][0] = bean.getFirstName();
			addLoginData[0][1] = bean.getLastname();
			//addLoginData[0][2]=bean.getEmailId();
			//Here user name will be same as the email id entered by the candidate
			addLoginData[0][2] = bean.getEmailId();
			addLoginData[0][3] = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
					.encrypt(getAltRandomPassword());
			String insertLogin = "INSERT INTO HRMS_REC_CAND_LOGIN(CAND_LOGINID,CAND_FIRSTNAME,CAND_LASTNAME,CAND_USERNAME,CAND_PWD)  "
					+ " VALUES((SELECT NVL(MAX(CAND_LOGINID)+1,1) FROM HRMS_REC_CAND_LOGIN),?,?,?,?)";
			result = getSqlModel().singleExecute(insertLogin, addLoginData);
			if (result) {
				String insertDatabank = "INSERT INTO HRMS_REC_CDOL_DATABANK(CAND_CODE,CAND_FIRST_NAME,CAND_LAST_NAME,CAND_EMAIL_ID,CAND_REF_TYPE,CAND_SHORT_STATUS,CAND_DOB,CAND_MOB_PHONE) "
						+ " VALUES ((SELECT NVL(MAX(CAND_CODE)+1,1) FROM HRMS_REC_CDOL_DATABANK),'"
						+ bean.getFirstName()
						+ "','"
						+ bean.getLastname()
						+ "','"
						+ bean.getEmailId()
						+ "','O','N',TO_DATE('"
						+ bean.getDateOfBirth()
						+ "','DD-MM-YYYY'),'"
						+ bean.getMobile() + "')";
				getSqlModel().singleExecute(insertDatabank);
				sendCandidateMail(request, addLoginData, bean);
			}
			String updateLogin = "UPDATE HRMS_REC_CAND_LOGIN SET CAND_CODE = (SELECT NVL(MAX(CAND_CODE),1) FROM HRMS_REC_CDOL_DATABANK) WHERE CAND_LOGINID = (SELECT NVL(MAX(CAND_LOGINID),1) FROM HRMS_REC_CAND_LOGIN)";
			getSqlModel().singleExecute(updateLogin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void sendCandidateMail(HttpServletRequest request,
			Object[][] addLoginData, CandidateLoginBean bean) {
		try {
			String poolName = "";
			try {
				poolName = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY)
						.encrypt((String) session.getAttribute("session_pool"));
			} catch (Exception e) {
				poolName="";
			}

			String URL = "";
			URL = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/recruit/CandidateLogin_input.action?infoId=" + poolName;
			
			
			/*String URL = "";
			URL = "http://" + request.getServerName() + "/recruit";*/
			String password = "";
			String username = bean.getFirstName();
			String[] to_Emailid = new String[1];
			to_Emailid[0] = bean.getEmailId();
			String[] subject = { "Candidate Login Details" };
			String[] message = new String[1];
			String loginPassMsg = "";
			if (addLoginData != null && addLoginData.length > 0) {
				password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.decrypt(String.valueOf(addLoginData[0][3]));
				logger.info("password in model of pradeep " + password);

				loginPassMsg = "User Name :" + bean.getEmailId() + "<br><br>Password  :" + password + "<br><br>";
				message[0] = getMessage(username, URL, loginPassMsg);
				MailUtility mod = new MailUtility();
				mod.initiate(context, session);
				mod.sendMail(to_Emailid, mod.getDefaultMailIds(), subject, message, "", "");
				mod.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMessage(String uname, String url, String msg) {
		String[] htmlText = new String[1];
		String tempMsg = "";
		try {
			htmlText[0] = "<html> "
					+ "<style>"
					+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
					+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
					+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
					+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
					+ ".style15 {color: #CC0000} "
					+ ".style16 {color: #D23A49} "
					+ "</style>"
					+ "	<body> "
					+ "<table width='80%' border='0' cellpadding='0' cellspacing='0'>"
					+ "<tr> "
					+ "<td width='80%'>"
					+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
					+ "<tr> "
					+ "<td><p>Dear&nbsp;<b>"
					+ uname
					+ "</b>, </p><br /> "
					+ "Welcome to People Power-Recruitment Management System "
					+ "Kindly find your account information below.</p> "
					+ "<br /><p>People Power Link :<a href='"
					+ url
					+ "'>"
					+ url
					+ "</a></p>"
					+ msg

					+ "<br /><p>For any assistance, please contact the System Administrator.</p><br />"
					+ "<p>Best Regards,</p>" + "<p>Human Resource Team</p>"
					+ "</td> " + "</tr> " + "</table> " + "</td> " + "</tr> "

					+ "</table> " + "</body> " + "</html> ";
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			tempMsg = model.getMassMessage(htmlText[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempMsg;
	}

	/**
	 * following function is called to check the user name 
	 * @param bean
	 * @return
	 */
	public String chkUserAvailability(CandidateLoginBean bean) {
		String result = "";
		try {
			String query = "SELECT CAND_USERNAME FROM HRMS_REC_CAND_LOGIN WHERE UPPER(CAND_USERNAME) LIKE "
					+ "'" + bean.getEmailId().trim().toUpperCase() + "'";
			Object userData[][] = getSqlModel().getSingleResult(query);
			if (userData.length > 0 && userData != null) {
				result = "true";
			} else {
				result = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to generate the password randomly for the candidate who registers on line. 
	 * @return
	 */
	public String getAltRandomPassword() {
		StringBuffer password = new StringBuffer();
		try {
			char[] smallChars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
					'h', 'i', 'j', 'k','m', 'n','p', 'q', 'r', 's',
					't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
					'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N','P', 'Q',
					'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','2',
					'3', '4', '5', '6', '7', '8', '9'};
			for (int i = 0; i < 7; i++) {
				password.append(smallChars[random.nextInt(smallChars.length)]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password.toString();
	}

	public boolean getForgotedPassword(CandidateLoginBean loginBean, HttpServletRequest request) {
		boolean result = false;
		try {
			String getpassword = "SELECT CAND_FIRSTNAME||' '||CAND_LASTNAME, CAND_USERNAME, CAND_PWD FROM HRMS_REC_CAND_LOGIN WHERE CAND_USERNAME = '"+loginBean.getUserNameForgot().trim()+"'";
			Object forgotUserData[][] = getSqlModel().getSingleResult(getpassword);
			if(forgotUserData != null && forgotUserData.length > 0) {
				result = true;
				String poolName = "";
				try {
					poolName = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
							StringEncrypter.URL_ENCRYPTION_KEY)
							.encrypt((String) session.getAttribute("session_pool"));
				} catch (Exception e) {
					poolName="";
				}
				String URL = "";
				URL = "http://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/recruit/CandidateLogin_input.action?infoId=" + poolName;
				String password = "";
				String username = String.valueOf(forgotUserData[0][0]);
				String[] to_Emailid = new String[1];
				to_Emailid[0] = String.valueOf(forgotUserData[0][1]);
				//String[] subject = { "Candidate Login Details" };
				String subject ="Candidate Login Details";
				//String[] message = new String[1];
				String loginPassMsg = "";
					password = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.decrypt(String.valueOf(forgotUserData[0][2]).trim());

					loginPassMsg = "User Name :" + String.valueOf(forgotUserData[0][1]) + "<br>Password  :" + password + "<br><br>";
				//	message[0] = getMessage(username, URL, loginPassMsg);
					
					String message= getMessage(username, URL, loginPassMsg);
					MailUtility mod = new MailUtility();
					mod.initiate(context, session);
					mod.sendMail(to_Emailid, mod.getDefaultMailIds(), subject, message, "", "");
					
					  mod.sendMail(to_Emailid, mod.getDefaultMailIds(),
							  subject, message, "", "",false);
			 
					mod.terminate();
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] getComponyLogo() {
		Object[][] logoquery = null;
		try {
			String query = "SELECT COMPANY_LOGO,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME) FROM  HRMS_COMPANY";
			logoquery = getSqlModel().getSingleResult(query);
			/*
			 * if (logoquery != null && logoquery.length > 0 &&
			 * !String.valueOf(logoquery[0][0]).equals("null")) { companyLogo =
			 * String.valueOf(logoquery[0][0]); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logoquery;
	}
	
}
