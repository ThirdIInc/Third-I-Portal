/**
 * 
 */
package org.paradyne.model.vendor;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.vendor.VendorLogin;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.admin.srd.SendMailModel;

/**
 * @author AA0491
 * 
 */
public class VendorLoginModel extends ModelBase {

	public Object[][] getComponyLogo() {
		Object[][] logoquery=null;
		try {
			String query = "SELECT COMPANY_LOGO,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME) FROM  HRMS_COMPANY";
			  logoquery = getSqlModel().getSingleResult(query);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logoquery;
	}

	public Object[][] selectLoginData(String login, String password,
			HttpServletRequest request) {
		Object[][] result = null;
		try {
			result = null;
			String selectSql = " SELECT PARTNER_LOGIN_CODE, PARTNER_CODE, PARTNER_LOGIN_NAME, PARTNER_LOGIN_PASS, PARTNER_NAME, PARTNER_EMAIL FROM VENDOR_INFO "
					+ " WHERE PARTNER_LOGIN_NAME=? AND PARTNER_LOGIN_PASS=? ";

			result = getSqlModel().getSingleResult(selectSql,
					new Object[] { login, password });
			if (result != null && result.length > 0) {
				 request.setAttribute("partnerName", result[0][4]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getLoginData(String login, String password, HttpServletRequest request) {
		 String result = "";
		try {
			String sqlUserName = " SELECT NVL(PARTNER_LOGIN_NAME,'')FROM VENDOR_INFO  WHERE PARTNER_LOGIN_NAME =?";
			Object[][] sqlUserNameObj = getSqlModel().getSingleResult(sqlUserName, new Object[]{login});
			if(sqlUserNameObj ==null || sqlUserNameObj.length == 0) {
				result = "InCorrectUserName";
			} else {
				String sqlPassword = " SELECT NVL(PARTNER_LOGIN_PASS,'')FROM VENDOR_INFO WHERE PARTNER_LOGIN_PASS =?";
				Object[][] sqlPasswordObj = getSqlModel().getSingleResult(sqlPassword, new Object[]{password});
				if(sqlPasswordObj == null || sqlPasswordObj.length == 0) {
					result = "InCorrectPassword";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void createMainMenu(HttpServletRequest request, String newMenuCode) throws Exception {
		try {
			String contextPath = request.getContextPath();
			String query = "SELECT distinct HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
					+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
					+ contextPath
					+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
					+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU "
					+ " FROM HRMS_MENU "
					+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					+ " WHERE MENU_ISRELEASED='Y'  "
					+ " START WITH HRMS_MENU.MENU_CODE = 5023" //+ newMenuCode
					+ " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
					+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			String[][] strObj;
			if (obj.length > 0) {
				strObj = new String[obj.length][obj[0].length];
				for (int i = 0; i < strObj.length; i++) {
					for (int j = 0; j < strObj[0].length; j++) {
						strObj[i][j] = String.valueOf(obj[i][j]);
					}
				}
			} else {
				strObj = new String[0][0];
			}
			request.setAttribute("twoDimObjArr", strObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getForgotPartnerPassword(VendorLogin bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String getPartnerDataQuery = "SELECT PARTNER_NAME, PARTNER_LOGIN_NAME, PARTNER_LOGIN_PASS ,PARTNER_EMAIL FROM VENDOR_INFO WHERE PARTNER_LOGIN_NAME = '"+bean.getVendorUserNameForgot().trim()+"'";
			Object forgotUserData[][] = getSqlModel().getSingleResult(getPartnerDataQuery);
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
					+ "/vendor/VendorLogin_input.action?infoId=" + poolName;
		
				String password = "";
				String partnerName = String.valueOf(forgotUserData[0][0]);
				String[] to_Emailid = new String[1];
				to_Emailid[0] = String.valueOf(forgotUserData[0][3]);
				String[] subject = { "Vendor Login Details" };
				String[] message = new String[1];
				String loginPassMsg = "";
				password = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(String.valueOf(forgotUserData[0][2]).trim());
							 
					loginPassMsg = "User Name :" + String.valueOf(forgotUserData[0][1]) + "<br>Password  :" + password + "<br><br>";
					message[0] = getMessage(partnerName, URL, loginPassMsg);
					MailUtility mod = new MailUtility();
					mod.initiate(context, session);
					mod.sendMail(to_Emailid, mod.getDefaultMailIds(), subject, message, "", "");
					mod.terminate();
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getMessage(String partnerName, String url,
			String loginPassMsg) {
		String[] htmlText = new String[1];
		String tempMsg = "";
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
				+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='66%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> "
				+ "<td><p>Dear&nbsp;<b>"
				+ partnerName
				+ "</b>, </p><br /> "
				+ "Welcome to People Power-Vendor Management Tool  "
				+ "Kindly find your account information below.</p> "
				+ "<br /><p>People Power Link : <a href='"
				+ url
				+ "'>"
				+ url
				+ "</a></p>"
				+ loginPassMsg
				+ "<br /><p>For any assistance, please contact the System Administrator.</p><br />"
				+ "<p>Best Regards,</p>" + "<p>Human Resource Team</p>"
				+ "</td> " + "</tr> " + "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);
		return tempMsg;
	}
}
