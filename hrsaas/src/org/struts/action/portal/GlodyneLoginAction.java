package org.struts.action.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.common.*;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.MyCaptchaService;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.StringEncrypter.EncryptionException;
import org.paradyne.model.attendance.LoginAttendanceModel;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MenuModel;
import org.paradyne.model.common.UserModel;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import javax.imageio.ImageIO;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * 
 * @author Vishwambhar_Deshpande
 * 
 */

public class GlodyneLoginAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(GlodyneLoginAction.class);
	LoginBean loginBean = null;
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	private ServletContext context;
	// private logo="";
	String sImgType = "png";
	String Actionmessage = "";

	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}

	public String changeNewTheme() throws Exception {
		String newTheme = request.getParameter("newTheme");

		HttpSession session = request.getSession();
		// String loginName=(String)session.getAttribute("loginName");
		LoginBean bean = (LoginBean) session.getAttribute("loginBeanData");
		String query = "DELETE FROM  HRMS_THEME  WHERE LOGIN_CODE="
				+ bean.getLoginCode();
		LoginModel model = new LoginModel(bean);
		model.initiate(context, session);
		model.getSqlModel().singleExecute(query);
		String homeCode = String.valueOf(session.getAttribute("homeCode"));

		if (homeCode == null || homeCode.equals("null"))
			homeCode = "";
		String insertQuery = "INSERT INTO HRMS_THEME (LOGIN_THEME,LOGIN_FONT,LOGIN_FONTSIZE,LOGIN_CODE,LOGIN_DEF_HOME_CODE) VALUES('"
				+ newTheme// "diwali1"
				+ "','"
				+ "Arial"
				+ "',"
				+ "11"
				+ ","
				+ bean.getLoginCode()
				+ ",'" + homeCode + "')";
		model.getSqlModel().singleExecute(insertQuery);

		String queryTheme = "SELECT LOGIN_THEME,LOGIN_FONT,LOGIN_FONTSIZE,NVL(MENU_LINK,'/common/HomePage_input.action'),LOGIN_DEF_HOME_CODE FROM HRMS_THEME "
				+ " LEFT JOIN HRMS_MENU ON (HRMS_MENU.MENU_CODE = HRMS_THEME.LOGIN_DEF_HOME_CODE)"
				+ " WHERE LOGIN_CODE= " + bean.getLoginCode();
		// logger.info("query" + query);
		Object[][] obj = model.getSqlModel().getSingleResult(queryTheme);
		if (obj != null && obj.length > 0) {
			// logger.info("in if themeSettings");
			session.setAttribute("themeName", String.valueOf(obj[0][0]));
			session.setAttribute("fontName", String.valueOf(obj[0][1]));
			session.setAttribute("fontSize", String.valueOf(obj[0][2]));
			session.setAttribute("homePage", String.valueOf(obj[0][3]));
			request.setAttribute("homeCode", String.valueOf(obj[0][4]));
		} else {
			// logger.info("in else themeSettings");
			session.setAttribute("themeName", "peoplePower");
			session.setAttribute("fontName", "Arial");
			session.setAttribute("fontSize", "11");
			session.setAttribute("homePage", "/common/HomePage_input.action");
			session.setAttribute("homeCode", "");
		}
		model.terminate();
		try {
			createMainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String checkExpiredPass() throws Exception {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			LoginModel login = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean checkCorrectOldPass = login.checkUserPassword();
			login.setCompanyNameAndLogo(request);
			// logger.info("checkCorrectOldPass------" + checkCorrectOldPass);
			if (checkCorrectOldPass) {

				UserModel userModel = new UserModel();
				userModel.initiate(context, session);
				String password = loginBean.getExpNewpsswd1();
				String resultMsg = userModel.checkPasswordSettings(password);
				logger
						.info("Result Msg Is --------------------------------------- "
								+ resultMsg);
				if (!resultMsg.equals("")) {
					login.getPasswordLength(request);
					addActionMessage(resultMsg);
					return "send-to-changePassword";
				}
				if (!login.checkReusePassword(loginBean, request)) {
					if ((Integer) request.getAttribute("reusePeriodicity") == 1) {
						addActionMessage("Previous password cannot be reused. Please select different password!");
					} else {
						addActionMessage("Previous "
								+ request.getAttribute("reusePeriodicity")
										.toString()
								+ " passwords cannot be reused. Please Select different Password!");
					}
					return "send-to-changePassword";
				}
				boolean flag = login.checkExpiredPassRe();
				if (flag) {
					String isApp = login.checkForChangePassApp();
					if (isApp.equals("Y")) {
						String check = login.checkQuesConfigure();

						if (check.equals("Yes")) {
							result = "input";
						} else {
							login.setQuestion();
							result = "send-to-question";
						}
					} else {

						String checkImgApp = login.checkForImageApp();
						if (checkImgApp.equals("Y")) {
							loginBean.setLoginFlag("false");
							loginBean.setPassFlag("false");
							loginBean.setUserFlag("false");
						} else {
							loginBean.setLoginFlag("true");
							loginBean.setPassFlag("true");
							loginBean.setUserFlag("false");
						}

						result = "input";
					}

					// addActionMessage("Your Password has been Changed Please
					// Relogin ");
					// loginBean.setLoginName(loginBean.getExpPassEmpName());
					// loginBean.setLoginPassword(loginBean.getExpNewpsswd1());
					// result = "input";
				} else {
					addActionMessage("Your Password has been Not Changed");
					result = "send-to-changePassword";
				}
			} else {
				addActionMessage("Please Enter correct old password");
				result = "send-to-changePassword";
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			login.getPasswordLength(request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private String checkImageAndTextApp() {
		String result = "";
		try {
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String checkImgApp = loginModel.checkForImageApp();
			// logger.info("value of checkImgApp----------" + checkImgApp);
			if (checkImgApp.equals("Y")) {
				boolean isSettingDefine = loginModel.checkSetting();
				// logger.info("value of isSettingDefine----------" +
				// isSettingDefine);
				if (isSettingDefine) {
					result = "success";
				} else {
					result = "forwardTo";
				}
			} else {
				result = "success";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// logger.info("value of result in checkImageAndTextApp---" + result);
		return result;
	}

	public String checkIsUserFirstLogin() {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String isApp = loginModel.checkForChangePassApp();
			// logger.info("val of checkForChangePassApp--------------------" +
			// isApp);
			if (isApp.equals("Y")) {
				String check = loginModel.checkIsUserFirstLogin(loginBean);
				if (check.equals("YES")) {
					loginModel.setRecord();
					loginModel.getPasswordLength(request);
					result = "send-to-changePwd";

				} else {
					result = "success";
				}
			} else {
				result = checkImageAndTextApp();
				// logger.info("value in else checkImageAndTextApp----------------"
				// + result);
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			loginModel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// logger.info("final val of checkIsUserFirstLogin----------------" +
		// result);
		return result;
	}

	protected boolean checkKeyCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// logger.info("in check............");
		// Get the request params.
		Map paramMap = request.getParameterMap();
		if (paramMap.isEmpty()) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
					"Post method not allowed without parameters.");
			return false;
		}
		String[] arr1 = (String[]) paramMap.get("hidCaptchaID");
		String[] arr2 = (String[]) paramMap.get("inCaptchaChars");
		if (arr1 == null || arr2 == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Expected parameters were not found.");
			return false;
		}

		String sessId = request.getSession().getId();
		String incomingCaptchaId = arr1.length > 0 ? arr1[0] : "";
		String inputChars = arr2.length > 0 ? arr2[0] : "";
		// logger.info("in check............" + inputChars);
		// Check validity and consistency of the data.
		if (sessId == null || incomingCaptchaId == null
				|| !sessId.equals(incomingCaptchaId)) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Browser must support session cookies.");
			return false;
		}

		// Validate whether input from user is correct.
		// logger.info("Validating - inputChars are: " + inputChars);
		boolean passedCaptchaTest = validateCaptcha(incomingCaptchaId,
				inputChars);

		// Set flag into session.
		request.getSession().setAttribute("PassedCaptcha",
				new Boolean(passedCaptchaTest));

		// Forward request to results page.
		// RequestDispatcher rd = getServletContext().getRequestDispatcher(
		// "/results.jsp" );
		// rd.forward( request, response );
		return passedCaptchaTest;

	}

	public String checkReloginPassword() throws Exception {
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		// logger.info("loginName..loginPassword" + loginName + "..." +
		// loginPassword);
		loginBean.setLoginName(loginName);
		loginBean.setLoginPassword(loginPassword);
		LoginModel loginModel = new LoginModel(loginBean);
		loginModel.initiate(context, request.getSession());

		boolean flag = loginModel.checkPassword();
		if (flag) {
			request.getSession().setMaxInactiveInterval(1800);
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>success</message>");
		} else {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>invalid</message>");
		}
		return null;
	}

	public String checkUserLoginIPAdd() {
		String result = "";
		boolean flag = false;
		try {
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String poolDir = (String) session.getAttribute("session_pool");
			String fileName = getText("data_path") + "\\datafiles\\" + poolDir;
			flag = loginModel.checkIsIpFilterApp(fileName, request, loginBean);
			// logger.info("val of loginModel.checkIsIpFilterApp------" + flag);
			if (flag) {
				flag = loginModel.checkExceptionsLogin(fileName, request,
						loginBean);
				// logger.info("val of loginModel.checkExceptionsLogin------" +
				// flag);
				if (flag) {
					result = "Yes";
				} else {
					flag = loginModel.checkValidIPAdd(fileName, request,
							loginBean);
					// logger.info("val of loginModel.checkValidIPAdd------" +
					// flag);
					if (flag) {
						result = "Yes";
					} else {
						result = "No";
					}
				}
			} else {
				result = "Yes";
			}
			loginModel.terminate();
		} catch (Exception e) {
			// logger.info("Exception in checkUserLoginIPAdd----" + e);
		}
		return result;
	}

	public String checkUserPassword() throws Exception {
		// logger.info(" in checkUserPassword method in
		// model1--------------------");
		String poolName = "";
		String str = request.getRemoteAddr();
		String str1 = str.replace(".", "#");
		String[] strSpilt = str1.split("#");
		// logger.info("login 2----------");
		int strPort = request.getRemotePort();
		// logger.info("login 3-------------");
		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			// logger.info("login 4----------");
		} catch (Exception e) {
			// logger.error("Exception in checkUserPassword1--------" + e);
		}
		// logger.info("poolname in checkUserPassword
		// method--------------------" + poolName);
		LoginModel loginModel = new LoginModel(loginBean);
		HttpSession session = request.getSession();
		session.setAttribute("session_pool", poolName);
		loginModel.initiate(context, session);
		
		loginModel.setCompanyNameAndLogo(request);
		
		String forwardString = "invalid";
		try {
			logger
					.info("I am in checkUserPassword method in model2----------------------");
			String loginName = loginBean.getLoginName();
			String password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(loginBean
					.getLoginPassword());

			Object loginData[][] = (Object[][]) loginModel.selectLoginData(
					loginName, password);

			// logger.info("loginModel.selectLoginData length-----------"
			// + loginData.length);

			LoginModel login = new LoginModel(loginBean);
			login.initiate(context, session);

			String resultmodel1 = login.proccessLogin(strSpilt[0], strSpilt[1],
					strSpilt[2], strSpilt[3], strPort);

			// logger
			// .info("value of resultmodel1 in login.proccesslogin
			// action----"
			// + resultmodel1);

			if (resultmodel1.equals("loginSuccess")) {
				// Object[][] lastAcctActivity =
				// loginModel.getLastLoginTime(loginBean.getEmpId());
				// session.setAttribute("lastAcctActivity",
				// String.valueOf(lastAcctActivity[0][0]));
				Object[][] result = loginModel.saveLoginSession(String
						.valueOf(loginData[0][3]), strSpilt[0], strSpilt[1],
						strSpilt[2], strSpilt[3], strPort);
				try {
					LoginBean emailInfo = (LoginBean) context
							.getAttribute("email");
					if (emailInfo == null) {
						Object[][] emailObject = loginModel.getEmailInfo("1");
						if (emailObject != null) {
							LoginBean loginCotext = new LoginBean();
							if (emailObject.length >= 2) {
								if (String.valueOf(emailObject[0][5]).equals(
										"I")
										|| String.valueOf(emailObject[0][1])
												.equals("POP")
										|| String.valueOf(emailObject[0][5])
												.equals("IMAP")) {
									loginCotext.setSendHostName(String
											.valueOf(emailObject[1][0]));
									loginCotext.setSendPortNo(String
											.valueOf(emailObject[1][2]));
									loginCotext.setAuthChk(String
											.valueOf(emailObject[1][6]));
									loginCotext.setAuthUser(String
											.valueOf(emailObject[1][3]));
									loginCotext.setAuthPwd(String
											.valueOf(emailObject[1][4]));
									loginCotext.setHostName(String
											.valueOf(emailObject[0][0]));
									loginCotext.setPortNo(String
											.valueOf(emailObject[0][2]));
								} else {
									loginCotext.setSendHostName(String
											.valueOf(emailObject[0][0]));
									loginCotext.setSendPortNo(String
											.valueOf(emailObject[0][2]));
									loginCotext.setAuthChk(String
											.valueOf(emailObject[0][6]));
									loginCotext.setAuthUser(String
											.valueOf(emailObject[0][3]));
									loginCotext.setAuthPwd(String
											.valueOf(emailObject[0][4]));
									loginCotext.setHostName(String
											.valueOf(emailObject[1][0]));
									loginCotext.setPortNo(String
											.valueOf(emailObject[1][2]));
								}
							} else {
								loginCotext.setSendHostName("");
								loginCotext.setSendPortNo("");
								loginCotext.setHostName("");
								loginCotext.setPortNo("");
							}
							context.setAttribute("email", loginCotext);
						}
					}
				} catch (Exception e) {
					// logger.info("in catch of mailbox server---------------");
				}
				Object emailData[][] = (Object[][]) loginModel
						.selectEmailData(String.valueOf(loginData[0][1]));
				LoginBean loginBean_session = new LoginBean();
				loginBean_session.setEmpId(String.valueOf(loginData[0][1]));
				loginBean_session.setProfileCode(String
						.valueOf(loginData[0][2]));
				loginBean_session.setAccessProfileCode(String
						.valueOf(loginData[0][6]));
				//Added by Prajakta B(4 June 2013) for multiple profile code
				loginBean_session.setMultipleProfileCode(String.valueOf(loginData[0][7]));
				//Ends Added by Prajakta B(4 June 2013) for multiple profile code
				loginBean_session.setLoginCode(String.valueOf(loginData[0][3]));
				loginBean_session.setVisitorNo(String.valueOf(result[0][0]));
				loginBean_session.setLoginName(loginName);
				if (emailData != null && emailData.length > 0) {
					loginBean_session.setEmailId(String
							.valueOf(emailData[0][0]));
					loginBean_session.setEmailPwd(String
							.valueOf(emailData[0][1]));
				}
				String time = String.valueOf(result[0][1]).substring(
						String.valueOf(result[0][1]).length() - 8,
						String.valueOf(result[0][1]).length());
				String dat = String.valueOf(result[0][1]).substring(0,
						String.valueOf(result[0][1]).length() - 8);
				loginBean_session.setLoginDate(dat);
				loginBean_session.setLoginTime(time);
				session.setAttribute("loginBeanData", loginBean_session);
				session.setAttribute("empId", String.valueOf(loginData[0][1]));
				session.setAttribute("lname", loginName);
				session.setAttribute("poolId", loginBean.getInfoId());
				session.setMaxInactiveInterval(120);
				Object[][] theme = loginModel.setMyTheme(loginBean);
				if (theme != null && theme.length > 0) {
					session.setAttribute("themeName", String
							.valueOf(theme[0][0]));
					session.setAttribute("fontName", String
							.valueOf(theme[0][1]));
					session.setAttribute("fontSize", String
							.valueOf(theme[0][2]));
					session.setAttribute("homePage", String
							.valueOf(theme[0][3]));
					session.setAttribute("homeCode", String
							.valueOf(theme[0][4]));
				} else {
					session.setAttribute("themeName", "peoplePower");
					session.setAttribute("fontName", "Arial");
					session.setAttribute("fontSize", "11");
				}
				// logger.info("loginName---------------******************"
				// + loginName);
				// logger
				// .info("******************"
				// + request.getParameter("name"));
				// logger.info("******************"
				// + request.getParameter("chatrooms"));
				// String link = new
				// jChatBox.Service.UserLogin().doLogin(request,
				// session);
				// logger.info("******************" + link);
				// logger.info("---session attribute set---" + time + dat);
				forwardString = "success";
			}

			else if (resultmodel1.equals("send-to-changePassword")) {
				logger
						.info("In resultmodel1.equals send-to-changePassword----");
				addActionMessage("Your password has been expired. Please change your password. ");
				LoginModel model = new LoginModel(loginBean);
				model.initiate(context, session);
				model.getPasswordLength(request);
				model.terminate();
				forwardString = "send-to-changePassword";
			} else if (resultmodel1.equals("loginDenied")) {
				// logger.info("In resultmodel1.equals loginDenied----------");
				addActionMessage("Your Account is locked. Please try after some time.");
				loginBean.setInvalidFlag("false");
				forwardString = "invalid";
				return sessionInvalidate(forwardString);
			} else if (resultmodel1.equals("notActive")) {
				// logger.info("In resultmodel1.equals notActive----------");
				addActionMessage("User is Inactive so can not Login");
				loginBean.setInvalidFlag("false");
				forwardString = "notActive";
				return sessionInvalidate(forwardString);
			} else if (resultmodel1.equals("go-for-imageAuthentication")) {
				logger
						.info("In resultmodel1.equals go-for-imageAuthentication----------");
				//request.setAttribute("infoId", loginBean.getInfoId());
				request.setAttribute("loginName", loginName);
				logger.info("value of loginBean.getInvalidCount()-------------"
						+ loginBean.getInvalidCount());
				int attmpCount = 0, invalCount = 0;

				if (loginBean.getInvalidCount() < 2) {
					loginBean.setInvalidCount(loginBean.getInvalidCount() + 1);
					addActionMessage("Invalid Password!!\nThe browser will be closed after 3 unsuccessful login attempts.");
					
				} else {
					addActionMessage("You have attempted 3 unsuccessful logins. The browser will be closed.");
					loginBean.setLockAttempt(1);
				}

				try {
					/*	try {
							invalCount = Integer.parseInt(String.valueOf(loginBean
									.getInvalAttmpCount()));
							attmpCount = Integer.parseInt(String.valueOf(loginBean
									.getPassLockAttmts()));
						} catch (Exception e) {
							invalCount = 1;
							loginBean.setInvalAttmpCount("1");
						}*/
					// logger.info("invalCount in go-for-imageAuthentication---"
					// + invalCount);
					// logger.info("attmpCount in go-for-imageAuthentication---"
					// + attmpCount);
					// logger.info("val of getPassLockAttmts()---------"
					// + loginBean.getPassLockAttmts());
					// logger.info("val of getInvalAttmpCount()-----"
					// + loginBean.getInvalAttmpCount());
					// attmpCount = attmpCount - invalCount;
					// logger.info("attmpCount====after minus-----" +
					// attmpCount);
					/*if (loginBean.getPassLockFlg().equals("Y")) {
						if (invalCount == 0) {
							forwardString = "invalid";
						} else {
							addActionMessage("Invalid password!\nYour Account will be Locked after "
									+ invalCount
									+ " Unsuccessful Login Attempts");
						}

					}*//* else {
										
										}*/

				} catch (Exception e) {
					logger
							.error("Exception in go-for-imageAuthentication________"
									+ e);
				}

				/* loginBean.setInvalidCount(loginBean.getInvalidCount() + 1); */
				// logger.info("value of loginBean.getSettingFlag()-------"
				// + loginBean.getSettingFlag());
				if (loginBean.getSettingFlag().equals("true")) {
					loginBean.setInvalidFlag("true");
					loginBean.setUserFlag("false");
					loginBean.setLoginFlag("true");
					loginBean.setPassFlag("true");
				} else {
					loginBean.setInvalidFlag("true");
					loginBean.setUserFlag("false");
					loginBean.setLoginFlag("true");
					loginBean.setPassFlag("true");
				}
				forwardString = "invalid";
			}
		} catch (Exception e) {

		}
		// logger.info("checkUserPassword return ****forwardString -------"
		// + forwardString);

		if (forwardString.equals("success")) {
			return createMainMenu();
		} else {
			// logger.info("forwardString in else of checkUserPassword---"
			// + forwardString);
			//request.setAttribute("infoId", poolName);
			return forwardString;
		}
	}

	public String continueUser() {
		// logger.info("inside continueUser method------");
		String result = "";
		LoginModel loginModel=null;
		long startTime = System.currentTimeMillis();
		try {
			// logger.info("in continueUser loginBean.getInfoId()---" +
			// loginBean.getInfoId());
			
			String poolName=null;
			poolName = request.getParameter("infoId");
			logger.info("poolName-----in continueUser method----------"
					+ poolName);

			if (poolName == null) {
				poolName = (String) request.getAttribute("infoId");
			}
			request.setAttribute("infoId", poolName);
			
			  poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			// logger.info("in continueUser poolName---" + poolName);

			loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			loginModel.initiate(context, session);

			String logo = null;
			String ppurl = null;
			String comanyName = null;

			Object[][] data = null;

			try {
				data = loginModel.getComponyLogo();

				if (data != null && data.length > 0) {
					logo = String.valueOf(data[0][0]);
					ppurl = String.valueOf(data[0][1]);
					comanyName = String.valueOf(data[0][2]);
				}
				if (logo.length() > 0) {
					request.setAttribute("logo", ""
							+ request.getParameter("compImg"));
					request.setAttribute("ppurl", ppurl);
				}
			} catch (Exception e) {
				// logger.info("inside continueUser method getComponyLogo------"
				// + e);
			}

			boolean isCorrect = loginModel.checkUsername();
			if (isCorrect) {
				boolean isActive = loginModel.isActive();
				// logger.info("val of isActive in continueUser-----" +
				// isActive);
				if (isActive) {
					String validIp = checkUserLoginIPAdd();
					// logger.info("val of checkUserLoginIPAdd in
					// continueUser-----" + validIp);
					if (validIp.equals("Yes")) {
						String check = loginModel.checkForImageApp();
						// logger.info("val of checkForImageApp in
						// continueUser-----" + check);
						if (check.equals("Y")) {
							boolean isSettingDefine = loginModel.checkSetting();
							logger
									.info("val of checkSetting in continueUser-----"
											+ isSettingDefine);
							if (isSettingDefine) {
								loginModel
										.getUserSecureData(loginBean, request);
								loginBean.setPassFlag("true");
								loginBean.setLoginFlag("true");
								loginBean.setImgTxtFlag("true");
								loginBean.setUserFlag("false");
								loginBean.setSettingFlag("false");
								logger
								.info("IN IF CONDITION");
								result = "invalid";
							} else {
								
								loginBean.setPassFlag("true");
								loginBean.setLoginFlag("true");
								loginBean.setImgTxtFlag("true");
								loginBean.setUserFlag("false");
								loginBean.setSettingFlag("false");
								logger
								.info("IN ELSE CONDITION");
								result = "invalid";
							}
						} else {
							loginBean.setPassFlag("true");
							loginBean.setLoginFlag("true");
							loginBean.setUserFlag("false");
							loginBean.setSettingFlag("false");
							result = "invalid";
						}
						loginBean.setActionMessage("");
					} else {
						addActionMessage("Access not allowed from this location");
						result = "invalid";
					}

				} else {
					addActionMessage("User is inactive so can not Login");
					result = "invalid";
					return sessionInvalidate(result);
				}
			} else {
				// logger.info("login 7----------------------------");
				addActionMessage("Invalid user name");
				loginBean.setUserFlag("true");
				result = "invalid";
			}
			//request.setAttribute("infoId", poolName);
			loginModel.terminate();
		} catch (Exception e) {

			result = "invalid";
			// logger.error("Exception in continueUser-------" + e);
		}
		// loginBean.setUserFlag("false");
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;
		request.setAttribute("respTime", "" + responseTime);
		// logger.info("final value of result in continueUser---" + result);
		String comanyName = null;
		Object data[][]=null;
		try {
			data = loginModel.getComponyLogo();
			if (data != null && data.length > 0) {
				comanyName = String.valueOf(data[0][2]);
			}

		} catch (Exception e) {
			logger.error("Exception in getComponyLogo--------" + e);
		}
		
		request.setAttribute("comanyName", comanyName);
		return result;
	}

	public String createMainMenu() throws Exception {
		logger
				.info("in createMainMenu.....................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...............2"
						+ request.getContextPath());
		LoginBean lb = (LoginBean) request.getSession().getAttribute(
				"loginBeanData");
		String contextPath = request.getContextPath();
		Random random = new Random();
		//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE CODE
		String query = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("
				+ lb.getMultipleProfileCode()
				+ ")"
				+ " AND MENU_PARENT_CODE=0"
				+ " AND MENU_ISRELEASED='Y' AND HRMS_MENU.MENU_CODE NOT IN(35,408,2316,2314,2310,5031,1169,906,825,944,414) "
				+" AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES')) "
				+ " ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE";

		// logger.info("in createMenu.." + query);
		MenuModel model = new MenuModel();
		model.initiate(context, request.getSession());
		String[][] menuList = model.getMenuList(query);
		// --------------------------------------------------------
		String poolName = "";

		try {

			poolName = String.valueOf(request.getSession().getAttribute(
					"session_pool"));
			// //logger.info("in createMenu..22222222222222222222");
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			// //logger.info("in createMenu.."+poolName);
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\thought\\thought.xml";

			// //logger.info("in createMenu..3333333333333");

			String[][] thoughtObj;
			String thought = "";
			try {

				// //logger.info("in createMenu.. "+path);
				thoughtObj = model.processThought(new XMLReaderWriter()
						.parse(new File(path)));
				// int maxLength = thoughtObj.length;
				String[] thouthId = new String[thoughtObj.length];
				for (int i = 0; i < thouthId.length; i++) {
					thouthId[i] = thoughtObj[i][1];
				}
				// logger.info("random.nextInt(thouthId.length before)=="
				// + random.nextInt(thouthId.length));
				// random1 = doRawRandomNumber(maxLength);
				// thouthId[random.nextInt(thouthId.length)]);
				int randomValue = Integer
						.parseInt(String.valueOf(thoughtObj[random
								.nextInt(thouthId.length)][1]));

				for (int k = 0; k < thoughtObj.length; k++) {
					// logger.info("Random Value In loop " + randomValue);
					if (randomValue == Integer.parseInt(String
							.valueOf(thoughtObj[k][1]))) {
						logger
								.info("Thought of the Day is "
										+ thoughtObj[k][2]);
						thought = String.valueOf(thoughtObj[k][2]);
					}
				}
				// logger.info("random.nextInt(thouthId.length)=="
				// + random.nextInt(thouthId.length));

			} catch (Exception e) {
				thoughtObj = new String[0][0];
				// e.printStackTrace();
			}
			// logger.info("thought..................." + thought);

			Object[][] cmpName = model.getCmpName();
			String cmpanyName = null;
			try {
				cmpanyName = String.valueOf(cmpName[0][0]);
			} catch (Exception e) {

			}
			HttpSession session = request.getSession();

			LoginModel loginModel = new LoginModel(loginBean);
			loginModel.initiate(context, request.getSession());
			Object[][] lastAcctActivity = loginModel.getLastLoginTime(lb
					.getEmpId());
			// logger.info("Last Account Activity: "
			// + String.valueOf(lastAcctActivity[0][0]));
			Object[][] obj = loginModel.setLoginInfo(lb.getEmpId());
			if (obj != null && obj.length > 0) {
				// logger.info("UserID----" + String.valueOf(obj[0][0]));
				// logger.info("UserName----" + String.valueOf(obj[0][1]));
				request.setAttribute("UserID", String.valueOf(obj[0][0]));
				request.setAttribute("UserName", String.valueOf(obj[0][1]));
				String photo = String.valueOf(obj[0][2]);
				if (String.valueOf(obj[0][1]) != null && !photo.equals("")) {
					request.setAttribute("profilePhoto", String
							.valueOf(obj[0][3]));
				} else {
					request.setAttribute("profilePhoto", "empimage.gif");
				}
				request.setAttribute("gender", String.valueOf(obj[0][4]));
				request.setAttribute("thought", thought);
				session.setAttribute("lastAcctActivity", String
						.valueOf(lastAcctActivity[0][1]));
			}
			// logger.info("in createMenu..");
			request.setAttribute("menuList", menuList);
			request.setAttribute("compName", cmpanyName);
			
			String comanyName ="";
			try {
				Object companyNameObj[][]   = loginModel.getComponyLogo();

				if (companyNameObj != null && companyNameObj.length > 0) {
							comanyName = String.valueOf(companyNameObj[0][2]);
				}
			} catch (Exception e) {
				logger
						.error("Exception in getComponyLogo input-----------------"
								+ e);
			}
			request.setAttribute("comanyName", comanyName);
			
			model.terminate();
		} catch (Exception e) {

		}
		return "success";
	}

	public String getData() throws Exception {
		return "success";
	}

	public void getImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
		byte[] captchaBytes;
		// logger.info("init method................FFFFFS..");
		if (request.getQueryString() != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"GET request should have no query string.");
			return;
		}
		try {
			// Session ID is used to identify the particular captcha.
			String captchaId = request.getSession().getId();

			// Generate the captcha image.
			BufferedImage challengeImage = MyCaptchaService.getInstance()
					.getImageChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challengeImage, sImgType, imgOutputStream);
			captchaBytes = imgOutputStream.toByteArray();

			// Clear any existing flag.
			request.getSession().removeAttribute("PassedCaptcha");
		} catch (CaptchaServiceException cse) {
			// logger.info("CaptchaServiceException - " + cse.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Problem generating captcha image.");
			return;
		} catch (IOException ioe) {
			// logger.info("IOException - " + ioe.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Problem generating captcha image.");
			return;
		}

		// Set appropriate http headers.
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/"
				+ (sImgType.equalsIgnoreCase("png") ? "png" : "jpeg"));

		// Write the image to the client.
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(captchaBytes);
		outStream.flush();
		outStream.close();
	}

	public String getKeyCode() throws Exception {
		getImage(request, response);
		return null;
	}

	public String getLastAccessDiff() throws Exception {

		long lastAccessTime = request.getSession().getLastAccessedTime();
		long currentTime = System.currentTimeMillis();
		long diffTime = currentTime - lastAccessTime;
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<message>" + diffTime + "</message>");
		return null;
	}

	public org.paradyne.bean.common.LoginBean getLoginBean() {
		return loginBean;
	}

	public Object getModel() {
		return loginBean;
	}

	public ServletContext getServletContext() {
		return context;
	}

	public String input() {
		String poolName = "";
		String result = "";
		long startTime = System.currentTimeMillis();
		HttpSession session = request.getSession();

		String pool = "";
		// logger.info("Login_input.action");
		try {
			// int abc=Math.random().n;
			String clientUser = (String) session.getAttribute("session_pool");
			// logger.info("pool:: " + pool);
			// logger.info("session_pool :" + "session_pool"
			// + session.getId());
			pool = clientUser;
		} catch (Exception e) {
			pool = "";
		}

		try {
			LoginBean bean = (LoginBean) session.getAttribute("loginBeanData");
			if (pool != null && !pool.equals("") && pool.length() > 0
					&& !pool.equals("null") && bean != null) {
				result = "alreadyInSession";
			} else {

				try {

					poolName = request.getParameter("infoId");
					if(poolName==null)
					{
						poolName = (String)request.getAttribute("infoId");
					}
					logger.info("poolName-----in input method----------"
							+ poolName);
					
					String validClientName="0";
					try {
						validClientName = existsClient(poolName);
					} catch (Exception e) {
						validClientName="0";
					}
					System.out.println("validClientName-----------"+validClientName);
						
					if(false)//validClientName.equals("0")
					{
						System.out.println("in validClientName loop--------------------------"+ request.getServerName());
						//response.sendRedirect("http://localhost:8080/pims/common/Login_input.action?infoId=TIxS2uuT3RMp9WGxdbhufQ==");
						response.sendRedirect("http://" + request.getServerName());
						return SUCCESS;
					}
					else{
						poolName = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.URL_ENCRYPTION_KEY)
								.decrypt(poolName);
						session.setAttribute("session_pool", poolName);
					}
					
				 

					// logger.info("in input method-----------------");
				} catch (Exception e) {
					// logger.error("Exception in input-----------------" + e);
				}
				LoginModel loginModel = new LoginModel(loginBean);

				loginModel.initiate(context, session);
				String logo = null;
				String ppurl = null;
				String comanyName ="";
				Object[][] data = null;

				try {
					data = loginModel.getComponyLogo();

					if (data != null && data.length > 0) {
						logo = String.valueOf(data[0][0]);
						ppurl = String.valueOf(data[0][1]);
						comanyName = String.valueOf(data[0][2]);
					}
				} catch (Exception e) {
					logger
							.error("Exception in getComponyLogo input-----------------"
									+ e);
				}
				String check = loginModel.checkForImageApp();
				if (!check.equals("Y")) {
					loginBean.setUserFlag("false");
					loginBean.setPassFlag("true");
					loginBean.setLoginFlag("true");
					loginBean.setSettingFlag("true");
				}
				long endTime = System.currentTimeMillis();
				double responseTime = (endTime - startTime) / 1000.0;
				request.setAttribute("respTime", "" + responseTime);
				request.setAttribute("logo", logo);
				request.setAttribute("ppurl", ppurl);
				request.setAttribute("comanyName", comanyName);
				result = "input";
			}

		} catch (Exception e) {
		}

		return result;
	}

	public String logout() throws Exception {
		String infoId="";
		try {
			// logger.info("I am in logout---------- ");
			HttpSession session = request.getSession();
			String str = (String) session.getAttribute("session_pool");
			String poolName = "";
			String forceLogout = "";
			try {
				forceLogout = (String) request.getParameter("logout");
				if (forceLogout.equals("forceLogout")) {
					request.setAttribute("forceLogout", "forceLogout");
				}
			} catch (Exception e) {
				// logger.error("Exception in logout----------------" + e);
			}
			try {
				poolName = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).encrypt(str);
			} catch (Exception e) {
				// logger.error("Exception in logout----------------" + e);
			}
			try {
				/**
				 * Calculate attendance
				 */
				LoginAttendanceModel LoginAttendanceModel = new LoginAttendanceModel();
				LoginAttendanceModel.initiate(context, session);
				LoginBean bean = (LoginBean) request.getSession().getAttribute(
						"loginBeanData");
				LoginAttendanceModel.calculateAttendance(bean.getEmpId(),
						"LOGOUT",false);
				
				infoId=(String)session.getAttribute("poolId");
				
				LoginAttendanceModel.terminate();				
				session.invalidate();
				request.setAttribute("poolName", poolName);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("infoId  "+infoId);
		request.setAttribute("infoId", infoId);
		return input();
	}

	public void prepare() throws Exception {
		loginBean = new LoginBean();
	}

	public String returnToInput() {
		String result = "";
		try {
			LoginModel login = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			login.initiate(context, session);
			String updateQuery = " UPDATE hrms_login SET LOGIN_AlREADYLOGGED='Y' WHERE LOGIN_CODE="
					+ loginBean.getLoginCode();
			boolean res = login.getSqlModel().singleExecute(updateQuery);
			if (res) {
				result = "input";
			} else {
				result = "input";
			}

			login.terminate();
		} catch (Exception e) {
		}
		return result;
	}

	public String saveChangePassword() throws Exception {
		String result = "";
		try {

			long startTime = System.currentTimeMillis();
			LoginModel login = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean checkCorrectOldPass = login.checkUserPassword();
			login.setCompanyNameAndLogo(request);
			if (checkCorrectOldPass) {
				UserModel userModel = new UserModel();
				userModel.initiate(context, session);
				String password = loginBean.getNewpassword();
				String resultMsg = userModel.checkPasswordSettings(password);
				logger
						.info("Result Msg Is --------------------------------------- "
								+ resultMsg);
				if (!resultMsg.equals("")) {
					addActionMessage(resultMsg);
					login.getPasswordLength(request);
					return result = "send-to-changePwd";
				}

				if (!login.checkReusePassword(loginBean, request)) {
					if ((Integer) request.getAttribute("reusePeriodicity") == 1)
						addActionMessage("Previous password cannot be reused. Please select different password!");
					else
						addActionMessage("Previous "
								+ request.getAttribute("reusePeriodicity")
										.toString()
								+ " passwords cannot be reused. Please Select different Password!");
					return "send-to-changePwd";
				}
				boolean flag = login.saveChangePassword();
				if (flag) {
					// addActionMessage("Your Password has been Changed Please
					// Relogin ");
					// loginBean.setLoginName(loginBean.getExpPassEmpName());
					// loginBean.setLoginPassword(loginBean.getExpNewpsswd1());
					login.setQuestion();

					result = "send-to-question";
				} else {
					addActionMessage("Your password has not been changed");
					result = "send-to-changePwd";
				}
			} else {
				addActionMessage("Please Enter correct old password");
				result = "send-to-changePwd";
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			login.getPasswordLength(request);
			
			String comanyName = null;
			Object data[][]=null;
			try {
				data = login.getComponyLogo();
				if (data != null && data.length > 0) {
					comanyName = String.valueOf(data[0][2]);
				}

			} catch (Exception e) {
				logger.error("Exception in getComponyLogo--------" + e);
			}
			
			request.setAttribute("comanyName", comanyName);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String saveImage() {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			boolean flag = loginModel.saveUserSecureData(loginBean);
			loginModel.setCompanyNameAndLogo(request);
			if (flag) {
				// loginModel.getUserSecureData(loginBean, request);
				loginBean.setPassFlag("false");
				loginBean.setLoginFlag("false");
				loginBean.setImgTxtFlag("false");
				result = returnToInput();
			} else {
				returnToInput();
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			
			loginModel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String saveQuestion() {
		String result = "";
		try {

			LoginModel login = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean isQueSave = login.saveQuestion();
			login.setCompanyNameAndLogo(request);
			
			if (isQueSave) {
				String check = login.checkForImageApp();

				if (check.equals("Y")) {

					result = "forwardTo";
				} else {

					if (loginBean.getSettingFlag().equals("true")) {
						loginBean.setLoginFlag("true");
						loginBean.setPassFlag("true");
						loginBean.setUserFlag("false");
					}

					result = returnToInput();
				}

			}
			 
			login.terminate();
		} catch (Exception e) {
		}
		return result;
	}

	public String sessionInvalidate(String result) {
		request.getSession().invalidate();
		// logger.info("sessionInvalidate");

		return result;

	}

	public void setLoginBean(org.paradyne.bean.common.LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public String submitUser() {

		String result = "invalid";
		// logger.info("loginBean.getInvalidFlag()_________________________" +
		// loginBean.getInvalidFlag());

		try {
			long startTime = System.currentTimeMillis();

			result = "";
			String poolName = null;
			poolName = request.getParameter("infoId");
			logger.info("In submitUser method 1------------" + poolName);

			if (poolName == null || poolName.equals("null")) {
				System.out.println("in else loop--------------------------"+ request.getServerName());
				response.sendRedirect("http://" + request.getServerName());
				return SUCCESS;
			}
			request.setAttribute("infoId", poolName);
			if (poolName != null) {
				try {
					loginBean.setInfoId(poolName);
					poolName = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
							StringEncrypter.URL_ENCRYPTION_KEY)
							.decrypt(loginBean.getInfoId());
					request.getSession().setAttribute("session_pool", poolName);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			logger.info("poolName-----in submitUser method----------"
					+ poolName);
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String logo = null;
			String ppurl = null;
			String comanyName = null;
			Object data[][] = null;
			try {
				data = loginModel.getComponyLogo();
				if (data != null && data.length > 0) {
					logo = String.valueOf(data[0][0]);
					ppurl = String.valueOf(data[0][1]);
					comanyName = String.valueOf(data[0][2]);
				}

			} catch (Exception e) {
				logger.error("Exception in getComponyLogo--------" + e);
			}
			logger.info("logo-------------------------" + logo);
			boolean isCorrect = loginModel.checkUsername();
			logger.info("isCorrect user name in submitUser method****"
					+ isCorrect);
			if (isCorrect) {
				// logger.info("loginBean.getInvalidFlag()________________ " +
				// loginBean.getInvalidFlag());
				if (loginBean.getInvalidFlag().equals("true")) {
					boolean res = checkKeyCode(request, response);
					
					// logger.info("res of checkKeyCode______________" + res);
					if (res) {
						result = checkUserPassword();
						// logger.info("result of checkUserPassword__________ "
						// + result);
					} else {
						if (loginBean.getSettingFlag().equals("true")) {
							loginBean.setLoginFlag("true");
							loginBean.setPassFlag("true");
							loginBean.setUserFlag("false");
						} else {
							loginBean.setLoginFlag("true");
							loginBean.setPassFlag("true");
							loginBean.setUserFlag("false");
							loginBean.setSettingFlag("false");
						}
						addActionMessage("Please Enter correct letters as they are shown in the image");
						/*
						 * loginBean
						 * .setInvalidCount(loginBean.getInvalidCount() + 1);
						 */
						
						request.setAttribute("logo", logo);
						request.setAttribute("ppurl", ppurl);
						request.setAttribute("comanyName", comanyName);

						String check = loginModel.checkForImageApp();
						
					
						result = "invalid";
					}
				} else {
					result = checkUserPassword();
					// logger.info("value of checkUserPassword--***-in
					// submituser method------" + result);
					if (result.equals("invalid")) {
						if (loginBean.getSettingFlag().equals("true")) {
							loginBean.setLoginFlag("true");
							loginBean.setPassFlag("true");
							loginBean.setUserFlag("false");
						} else {
							loginBean.setLoginFlag("true");
							loginBean.setPassFlag("true");
							loginBean.setUserFlag("false");
						}
						/*
						 * loginBean
						 * .setInvalidCount(loginBean.getInvalidCount() + 1);
						 */
						request.setAttribute("logo", logo);
						request.setAttribute("ppurl", ppurl);
						request.setAttribute("comanyName", comanyName);
						// loginBean.setSettingFlag("true");
					}
					if (result.equals("notActive")) {
						loginBean.setLoginFlag("true");
						loginBean.setPassFlag("true");
						loginBean.setUserFlag("false");
						request.setAttribute("logo", logo);
						request.setAttribute("ppurl", ppurl);
						request.setAttribute("comanyName", comanyName);
					}
				}
				// logger.info("value of result----in submituser
				// method-------------------" + result);
				if (result.equals("success")) {
					String validIp = checkUserLoginIPAdd();
					// logger.info("value of checkUserLoginIPAdd-----" +
					// validIp);
					if (validIp.equals("Yes")) {
						String check = loginModel
								.checkIsUserFirstLogin(loginBean);
						// logger.info("value of
						// checkIsUserFirstLogin--------------" + check);
						if (check.equals("YES")) {
							result = checkIsUserFirstLogin();
							// logger.info("value of checkIsUserFirstLogin in
							// if--------" + result);
						} else {
							result = checkImageAndTextApp();
							// logger.info("value of checkImageAndTextApp in
							// else--------" + result);
						}
					} else {
						addActionMessage("Access not allowed from this location");
						loginBean.setLoginFlag("true");
						loginBean.setPassFlag("true");
						loginBean.setUserFlag("false");
						result = "invalid";
					}
				}
			} else {
				// logger.info("login 7----------------------------");
				addActionMessage("Invalid user name");
				if (loginBean.getSettingFlag().equals("true")) {
					loginBean.setLoginFlag("true");
					loginBean.setPassFlag("true");
					loginBean.setUserFlag("false");
				} else {
					loginBean.setLoginFlag("true");
					loginBean.setPassFlag("true");
					loginBean.setUserFlag("false");
				}
				request.setAttribute("logo", logo);
				request.setAttribute("ppurl", ppurl);
				request.setAttribute("comanyName", comanyName);
				result = "invalid";
			}
			/**
			 * Calculate attendance at login
			 */
			if (result.equals("success")) {
				String loginName = loginBean.getLoginName();
				String password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(loginBean.getLoginPassword());

				Object loginData[][] = (Object[][]) loginModel.selectLoginData(
						loginName, password);

				LoginAttendanceModel LoginAttendanceModel = new LoginAttendanceModel();
				LoginAttendanceModel.initiate(context, session);
				LoginAttendanceModel.calculateAttendance(String
						.valueOf(loginData[0][1]), "LOGIN");
				LoginAttendanceModel.terminate();
			}
			long endTime = System.currentTimeMillis();
			// logger.info("(endTime-startTime)" + (endTime - startTime));
			double responseTime = (endTime - startTime) / 1000.0;
			// logger.info("responseTime/1000" + responseTime);
			request.setAttribute("respTime", "" + responseTime);
			loginModel.terminate();
			try {
				// logger.info("value of final result in submituser method is-----"
				// + result);
				// Set Photo image here
				String check = loginModel.checkForImageApp();
				if (check.equals("Y")) {
					boolean isSettingDefine = loginModel.checkSetting();
					if (isSettingDefine) {
						loginModel.getUserSecureData(loginBean, request);
						loginBean.setImgTxtFlag("true");
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "invalid";
		}
		return result;
	}

	private boolean validateCaptcha(String captchaId, String inputChars) {
		boolean bValidated = false;
		try {
			bValidated = MyCaptchaService.getInstance().validateResponseForID(
					captchaId, inputChars);
		} catch (CaptchaServiceException cse) {
		}
		return bValidated;
	}

	public String existsClient(String ClientName) {

		String client = "0";
		try {
			//ResourceBundle boundle =ResourceBundle.getBundle("ClientInfoServlet");
			ResourceBundle boundle = ResourceBundle.getBundle(this.getClass()
					.getName());
			
			try {
				client = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).decrypt(ClientName);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("client    " + client);
			try {
				client = boundle.getString(client);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("client  after  " + client);
		} catch (Exception e) {
			e.printStackTrace();
			client = "0";
		}

		return client;
	}
}