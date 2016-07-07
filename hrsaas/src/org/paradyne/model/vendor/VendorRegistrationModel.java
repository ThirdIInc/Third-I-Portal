package org.paradyne.model.vendor;
/** @ Author : Archana Salunkhe
 * @ purpose : Developed for Vendor Registration  
 * @ Date : 08-May-2012
 */
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.vendor.VendorLogin;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.action.common.LoginAction;

public class VendorRegistrationModel extends ModelBase {
	Random random = new Random();
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginAction.class);
	public Object[][] getComponyLogo() {
		Object[][] logoquery = null;
		try {
			String query = "SELECT COMPANY_LOGO,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME) FROM  HRMS_COMPANY";
			logoquery = getSqlModel().getSingleResult(query);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logoquery;
	}
	
	public boolean saveData(VendorLogin bean, HttpServletRequest request)
			throws Exception {
		boolean result = false;
		try {
			 Object[][] addLoginData = new Object[1][6];
			  String maxCode = "SELECT NVL(MAX(PARTNER_LOGIN_CODE)+1,1) FROM" 
				  				+" VENDOR_INFO"; 
			  Object [][] outObj = getSqlModel().getSingleResult(maxCode);
			 if(outObj != null && outObj.length >0){
				 addLoginData[0][0] =  outObj[0][0];
			 }						
			addLoginData[0][1] = bean.getPartnerCode();			
			addLoginData[0][2]= bean.getLoginName();
			addLoginData[0][3] = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
					.encrypt(getAltRandomPassword());
			addLoginData[0][4] = bean.getPartnerName();
			addLoginData[0][5]= bean.getEmailId();
			if(addLoginData != null && addLoginData.length >0){
			String insertLogin = "INSERT INTO VENDOR_INFO(PARTNER_LOGIN_CODE,PARTNER_CODE,PARTNER_LOGIN_NAME,PARTNER_LOGIN_PASS,PARTNER_NAME, PARTNER_EMAIL)"
								 +" VALUES(?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(insertLogin, addLoginData);			
			if (result) {
				sendPartnerMail(request, addLoginData, bean);
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void sendPartnerMail(HttpServletRequest request,
			Object[][] addLoginData, VendorLogin bean) {
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
					+ "/vendor/VendorLogin_input.action?infoId=" + poolName;
			
			
			/*String URL = "";
			URL = "http://" + request.getServerName() + "/recruit";*/
			String password = "";
			String username = bean.getLoginName();
			String[] to_Emailid = new String[1];
			to_Emailid[0] = bean.getEmailId();
			String[] subject = { "Partner Login Details" };
			String[] message = new String[1];
			String loginPassMsg = "";
			if (addLoginData != null && addLoginData.length > 0) {
				password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.decrypt(String.valueOf(addLoginData[0][3]));
				logger.info("password in model of" + password);

				loginPassMsg = "User Name :" + username + "<br><br>Password  :" + password + "<br><br>";
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

	/**
	 * following function is called to generate the password randomly.
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
					+ "Welcome to People Power-Vendor Management System "
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

}
