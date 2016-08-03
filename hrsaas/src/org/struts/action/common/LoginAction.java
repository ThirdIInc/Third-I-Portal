package org.struts.action.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.MyCaptchaService;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MenuModel;
import org.paradyne.model.common.UserModel;
import org.paradyne.model.common.UserProfileModel;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.*;


public class LoginAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {
	
	/**logger.*/
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginAction.class);
	/** loginBean. */
	public LoginBean loginBean = null;
	/** actionContext. */
	private ActionContext actionContext = ActionContext.getContext();
	/** request. */
	private HttpServletRequest request;
	/** response. */
	private HttpServletResponse response;
	/** context. */
	private ServletContext context;
	/** sImgType. */
	private String sImgType = "png";
	/** Actionmessage. */
	private String Actionmessage = "";

	/**
	 * This method is used for giving alert message to the user.
	 */
	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}

/**
 * This method is used for checking password expiry
 */
	public String checkExpiredPass() throws Exception {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			final  LoginModel login = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean checkCorrectOldPass = login.checkUserPassword();
			login.setCompanyNameAndLogo(request);
			// logger.info("checkCorrectOldPass------" + checkCorrectOldPass);
			if (checkCorrectOldPass) {

				final  UserModel userModel = new UserModel();
				userModel.initiate(context, session);
				final String password = this.loginBean .getExpNewpsswd1();
				final String resultMsg = userModel.checkPasswordSettings(password);
				logger
						.info("Result Msg Is --------------------------------------- "
								+ resultMsg);
				if (!resultMsg.equals("")) {
					login.getPasswordLength(request);
					addActionMessage(resultMsg);
					return "send-to-changePassword";
				}
				if (!login.checkReusePassword(this.loginBean , request)) {
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
					final String isApp = login.checkForChangePassApp();
					if (isApp.equals("Y")) {
						final String check = login.checkQuesConfigure();

						if (check.equals("Yes")) {
							result = "input";
						} else {
							login.setQuestion();
							result = "send-to-question";
						}
					} else {

						final String checkImgApp = login.checkForImageApp();
						if (checkImgApp.equals("Y")) {
							this.loginBean .setLoginFlag("false");
							this.loginBean .setPassFlag("false");
							this.loginBean .setUserFlag("false");
						} else {
							this.loginBean .setLoginFlag("true");
							this.loginBean .setPassFlag("true");
							this.loginBean .setUserFlag("false");
						}

						result = "input";
					}

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
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method is used for checking security image and message settings
	 * @return String
	 */
	private String checkImageAndTextApp() {
		  String result = "";
		try {
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			final String checkImgApp = loginModel.checkForImageApp();
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
		} catch (final Exception e) {
			e.printStackTrace();
		}
		// logger.info("value of result in checkImageAndTextApp---" + result);
		return result;
	}

	/**
	 * This method is used for checking user first login to the system.
	 * @return String
	 */
	public String checkIsUserFirstLogin() {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			final String isApp = loginModel.checkForChangePassApp();
			// logger.info("val of checkForChangePassApp--------------------" +
			// isApp);
			if (isApp.equals("Y")) {
				final String check = loginModel.checkIsUserFirstLogin(this.loginBean );
				if (check.equals("YES")) {
					loginModel.setRecord();
					loginModel.getPasswordLength(request);
					result = "send-to-changePwd";

				} else {
					result = "success";
				}
			} else {
				result = checkImageAndTextApp();
				// logger.info("value in else
				// checkImageAndTextApp----------------"
				// + result);
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			loginModel.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		// logger.info("final val of checkIsUserFirstLogin----------------" +
		// result);
		return result;
	}
/**
 * This method is used for capcha 
 * @param request
 * @param response
 * @return boolean
 * @throws ServletException
 * @throws IOException
 */
	protected boolean checkKeyCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
	boolean checkCode = false;
		final String captcha = (String) request.getSession().getAttribute("myCapcha");
		Map paramMap = request.getParameterMap();
		String[] arr2 = (String[]) paramMap.get("inCaptchaChars");
		final String inputChars = arr2.length > 0 ? arr2[0] : "";
		if (captcha != null && inputChars != null) {
			if (captcha.equalsIgnoreCase(inputChars)) {
				System.out.println("<p class='alert'>Correct</p>");
				checkCode = true;
			}
		}
		return checkCode;
	}
/**
 * This method is used for checking relogin password.
 * @return String
 * @throws Exception
 */
	public String checkReloginPassword() throws Exception {
		final String loginName = request.getParameter("loginName");
		final String loginPassword = request.getParameter("loginPassword");
		// logger.info("loginName..loginPassword" + loginName + "..." +
		// loginPassword);
		this.loginBean .setLoginName(loginName);
		this.loginBean .setLoginPassword(loginPassword);
		final  LoginModel loginModel = new LoginModel(this.loginBean );
		loginModel.initiate(context, request.getSession());

		boolean flag = loginModel.checkPassword();
		if (flag) {
			//request.getSession().setMaxInactiveInterval(1800);
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
/**
 * This method is used for checking user login IP Address for IP filtering
 * @return String
 */
	public String checkUserLoginIPAdd() {
		String result = "";
		boolean flag = false;
		try {
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			final String poolDir = (String) session.getAttribute("session_pool");
			final String fileName = getText("data_path") + "\\datafiles\\" + poolDir;
			flag = loginModel.checkIsIpFilterApp(fileName, request, this.loginBean );
			// logger.info("val of loginModel.checkIsIpFilterApp------" + flag);
			if (flag) {
				flag = loginModel.checkExceptionsLogin(fileName, request,
						this.loginBean );
				// logger.info("val of loginModel.checkExceptionsLogin------" +
				// flag);
				if (flag) {
					result = "Yes";
				} else {
					flag = loginModel.checkValidIPAdd(fileName, request,
							this.loginBean );
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
		} catch (final Exception e) {
			// logger.info("Exception in checkUserLoginIPAdd----" + e);
		}
		return result;
	}
/**
 * This method is used for checking password setting
 * @return String
 * @throws Exception
 */
	public String checkUserPassword() throws Exception {
		// logger.info(" in checkUserPassword method in
		// model1--------------------");
		String poolName = "";
		final String str = "192.168.1.100";
		String str1 = str.replace(".", "#");
		String[] strSpilt = str1.split("#");
		// logger.info("login 2----------");
		int strPort = 8080;
		// logger.info("login 3-------------");
		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(this.loginBean 
					.getInfoId());
			// logger.info("login 4----------");
		} catch (final Exception e) {
			// logger.error("Exception in checkUserPassword1--------" + e);
		}
		// logger.info("poolname in checkUserPassword
		// method--------------------" + poolName);
		final  LoginModel loginModel = new LoginModel(this.loginBean );
		HttpSession session = request.getSession();
		session.setAttribute("session_pool", poolName);
		loginModel.initiate(context, session);

		loginModel.setCompanyNameAndLogo(request);

		  String forwardString = "invalid";
		try {
			logger
					.info("I am in checkUserPassword method in model2----------------------");
			final String loginName = this.loginBean .getLoginName();
			final String password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(this.loginBean 
					.getLoginPassword());

			final Object  loginData[][] = (Object[][]) loginModel.selectLoginData(
					loginName, password);

			// logger.info("loginModel.selectLoginData length-----------"
			// + loginData.length);
			
			final  LoginModel login = new LoginModel(this.loginBean );
			login.initiate(context, session);

			final String resultmodel1 = login.proccessLogin(strSpilt[0], strSpilt[1],
					strSpilt[2], strSpilt[3], strPort);

			// logger
			// .info("value of resultmodel1 in login.proccesslogin
			// action----"
			// + resultmodel1);

			if (resultmodel1.equals("loginSuccess")) {
				// Object[][] lastAcctActivity =
				// loginModel.getLastLoginTime(this.loginBean .getEmpId());
				// session.setAttribute("lastAcctActivity",
				// String.valueOf(lastAcctActivity[0][0]));
					loginModel.saveLoginSession(String
						.valueOf(loginData[0][3]), strSpilt[0], strSpilt[1],
						strSpilt[2], strSpilt[3], strPort);
				try {
					LoginBean emailInfo = (LoginBean) context
							.getAttribute("email");
					if (emailInfo == null) {
						final Object [][] emailObject = loginModel.getEmailInfo("1");
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
				} catch (final Exception e) {
					// logger.info("in catch of mailbox server---------------");
				}
				LoginBean loginBean_session = new LoginBean();
				loginBean_session.setEmpId(String.valueOf(loginData[0][1]));
				loginBean_session.setProfileCode(String
						.valueOf(loginData[0][2]));
				loginBean_session.setAccessProfileCode(String
						.valueOf(loginData[0][6]));
				loginBean_session.setLoginCode(String.valueOf(loginData[0][3]));
				loginBean_session.setLoginName(loginName);
				loginBean_session.setMultipleProfileCode(String.valueOf(loginData[0][7]));
				session.setAttribute("loginBeanData", loginBean_session);
				session.setAttribute("empId", String.valueOf(loginData[0][1]));
				session.setAttribute("lname", loginName);
				session.setAttribute("poolId", loginBean.getInfoId());
			
				
				UserProfileModel userModel = new UserProfileModel();
				userModel.initiate(context, session);
				
				loginBean_session.setUserProfileCenters(userModel.getProfileCenters(String
						.valueOf(loginData[0][6]))); //center
				loginBean_session.setUserProfileEmpType(userModel.getProfileEmpTypes(String
						.valueOf(loginData[0][6]))); //emptype
				loginBean_session.setUserProfilePaybill(userModel.getProfilePaybill(String
						.valueOf(loginData[0][6]))); //emptype
				loginBean_session.setUserProfileDivision(userModel.getProfileDivision(String
						.valueOf(loginData[0][8]))); //paybill 
				userModel.terminate();
				
				
				session.setAttribute("homePage",
				"/common/HomePage_decisionHome.action");
				forwardString = "success";
			}
			else if (resultmodel1.equals("send-to-changePassword")) {
				logger
						.info("In resultmodel1.equals send-to-changePassword----");
				addActionMessage("Your password has been expired. Please change your password. ");
				final  LoginModel model = new LoginModel(this.loginBean );
				model.initiate(context, session);
				model.getPasswordLength(request);
				model.terminate();
				forwardString = "send-to-changePassword";
			} else if (resultmodel1.equals("loginDenied")) {
				addActionMessage("Your Account is locked. Please try after some time.");
				this.loginBean .setInvalidFlag("false");
				forwardString = "invalid";
				return sessionInvalidate(forwardString);
			} else if (resultmodel1.equals("notActive")) {
				addActionMessage("User is Inactive so can not Login");
				this.loginBean .setInvalidFlag("false");
				forwardString = "notActive";
				return sessionInvalidate(forwardString);
			} else if (resultmodel1.equals("go-for-imageAuthentication")) {
				logger
						.info("In resultmodel1.equals go-for-imageAuthentication----------");
				request.setAttribute("loginName", loginName);
				logger.info("value of this.loginBean .getInvalidCount()-------------"
						+ this.loginBean .getInvalidCount());
				int attmpCount = 0, invalCount = 0;

				if (this.loginBean .getInvalidCount() < 2) {
					this.loginBean .setInvalidCount(this.loginBean .getInvalidCount() + 1);
					addActionMessage("Invalid Password!");

				} else {
					addActionMessage("You have attempted 3 unsuccessful logins. The browser will be closed.");
					this.loginBean .setLockAttempt(1);
				}

				try {
					/*
					 * try { invalCount =
					 * Integer.parseInt(String.valueOf(this.loginBean 
					 * .getInvalAttmpCount())); attmpCount =
					 * Integer.parseInt(String.valueOf(this.loginBean 
					 * .getPassLockAttmts())); } catch (final Exception e) {
					 * invalCount = 1; this.loginBean .setInvalAttmpCount("1"); }
					 */
					// logger.info("invalCount in go-for-imageAuthentication---"
					// + invalCount);
					// logger.info("attmpCount in go-for-imageAuthentication---"
					// + attmpCount);
					// logger.info("val of getPassLockAttmts()---------"
					// + this.loginBean .getPassLockAttmts());
					// logger.info("val of getInvalAttmpCount()-----"
					// + this.loginBean .getInvalAttmpCount());
					// attmpCount = attmpCount - invalCount;
					// logger.info("attmpCount====after minus-----" +
					// attmpCount);
					/*
					 * if (this.loginBean .getPassLockFlg().equals("Y")) { if
					 * (invalCount == 0) { forwardString = "invalid"; } else {
					 * addActionMessage("Invalid password!\nYour Account will be
					 * Locked after " + invalCount + " Unsuccessful Login
					 * Attempts"); }
					 *  }
					 *//*
						 * else {
						 *  }
						 */

				} catch (final Exception e) {
					logger
							.error("Exception in go-for-imageAuthentication________"
									+ e);
				}

				if (this.loginBean .getSettingFlag().equals("true")) {
					this.loginBean .setInvalidFlag("true");
					this.loginBean .setUserFlag("false");
					this.loginBean .setLoginFlag("true");
					this.loginBean .setPassFlag("true");
				} else {
					this.loginBean .setInvalidFlag("true");
					this.loginBean .setUserFlag("false");
					this.loginBean .setLoginFlag("true");
					this.loginBean .setPassFlag("true");
				}
				forwardString = "invalid";
			}
		} catch (final Exception e) {

		}

		if (forwardString.equals("success")) {
			return createMainMenu();
		} else {
			return forwardString;
		}
	}
/**
 * This method is called on continue button
 * @return String
 */
	public String continueUser() {
		// logger.info("inside continueUser method------");
		String result = "";
		LoginModel loginModel = null;
		long startTime = System.currentTimeMillis();
		try {
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
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(poolName);
			// logger.info("in continueUser poolName---" + poolName);

			loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			loginModel.initiate(context, session);

			String logo = null;
			String ppurl = null;

			Object[][] data = null;

			try {
				data = loginModel.getComponyLogo();

				if (data != null && data.length > 0) {
					logo = String.valueOf(data[0][0]);
					ppurl = String.valueOf(data[0][1]);
				}
				if (logo.length() > 0) {
					request.setAttribute("logo", ""
							+ request.getParameter("compImg"));
					request.setAttribute("ppurl", ppurl);
				}
			} catch (final Exception e) {
				// logger.info("inside continueUser method getComponyLogo------"
				// + e);
			}

			boolean isCorrect = loginModel.checkUsername();
			if (isCorrect) {
				boolean isActive = loginModel.isActive();
				// logger.info("val of isActive in continueUser-----" +
				// isActive);
				if (isActive) {
					final String validIp = checkUserLoginIPAdd();
					// logger.info("val of checkUserLoginIPAdd in
					// continueUser-----" + validIp);
					if (validIp.equals("Yes")) {
						final String check = loginModel.checkForImageApp();
						// logger.info("val of checkForImageApp in
						// continueUser-----" + check);
						if (check.equals("Y")) {
							boolean isSettingDefine = loginModel.checkSetting();
							logger
									.info("val of checkSetting in continueUser-----"
											+ isSettingDefine);
							if (isSettingDefine) {
								loginModel
										.getUserSecureData(this.loginBean , request);
								this.loginBean .setPassFlag("true");
								this.loginBean .setLoginFlag("true");
								this.loginBean .setImgTxtFlag("true");
								this.loginBean .setUserFlag("false");
								this.loginBean .setSettingFlag("false");
								result = "invalid";
							} else {
								this.loginBean .setPassFlag("true");
								this.loginBean .setLoginFlag("true");
								this.loginBean .setImgTxtFlag("false");
								this.loginBean .setUserFlag("false");
								this.loginBean .setSettingFlag("false");
								result = "invalid";
							}
						} else {
							this.loginBean .setPassFlag("true");
							this.loginBean .setLoginFlag("true");
							this.loginBean .setUserFlag("false");
							this.loginBean .setSettingFlag("false");
							result = "invalid";
						}
						this.loginBean .setActionMessage("");
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
				this.loginBean .setUserFlag("true");
				result = "invalid";
			}
			
			loginModel.terminate();
		} catch (final Exception e) {

			result = "invalid";
			// logger.error("Exception in continueUser-------" + e);
		}
		// this.loginBean .setUserFlag("false");
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;
		request.setAttribute("respTime", "" + responseTime);		
		String comanyName = "";
		try {
			final Object [][] data = loginModel.getComponyLogo();
			if (data != null && data.length > 0) {
				/**If DecisionOne's Employee Logged In then they will See the Different Company Name*/
				if(request.getServerName().indexOf("mydecisionone.com")!= -1){
					comanyName = "DecisionOne Information Center";					
				}else{
					comanyName = String.valueOf(data[0][2]);
				}
			}

		} catch (final Exception e) {
			logger.error("Exception in getComponyLogo--------" + e);
		}
		request.setAttribute("comanyName", comanyName);

		return result;
	}
/**
 * This method is used for display modules list on home page
 * @return
 * @throws Exception
 */
	public String createMainMenu() throws Exception {
	 
		LoginBean lb = (LoginBean) request.getSession().getAttribute(
				"loginBeanData");
		final String contextPath = request.getContextPath();
		Random random = new Random();
		final String query = "SELECT DISTINCT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE  IN("
				+ lb.getMultipleProfileCode()
				+ ")"
				+ " AND MENU_PARENT_CODE=0"
				+ " AND MENU_ISRELEASED='Y' AND HRMS_MENU.MENU_CODE NOT IN(35,408) " 
				+ " AND MENU_ISHOME NOT LIKE'Y' and HRMS_MENU.menu_code not in (2316,2314,2310,5031,1169,906,825,944,414) "
				+ " AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES')) "
				+ " ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE";

		// logger.info("in createMenu.." + query);
		MenuModel model = new MenuModel();
		model.initiate(context, request.getSession());
		String[][] menuList = model.getMenuList(query);
		String [][] d1MenuList = model.getDecisionMenu(contextPath,lb.getMultipleProfileCode());
		// --------------------------------------------------------
		String poolName = "";

		try {

			poolName = String.valueOf(request.getSession().getAttribute("session_pool"));
			// //logger.info("in createMenu..22222222222222222222");
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			// //logger.info("in createMenu.."+poolName);
			final String path = getText("data_path") + "\\datafiles\\" + poolName
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

			} catch (final Exception e) {
				thoughtObj = new String[0][0];
				// e.printStackTrace();
			}
			// logger.info("thought..................." + thought);

			final Object [][] cmpName = model.getCmpName();
			String cmpanyName = null;
			try {
				cmpanyName = String.valueOf(cmpName[0][0]);
			} catch (final Exception e) {

			}
			HttpSession session = request.getSession();

			final  LoginModel loginModel = new LoginModel(this.loginBean );
			loginModel.initiate(context, request.getSession());
			final Object [][] lastAcctActivity = loginModel.getLastLoginTime(lb
					.getEmpId());
			final Object [][] obj = loginModel.setLoginInfo(lb.getEmpId());
			if (obj != null && obj.length > 0) {
				request.setAttribute("UserID", String.valueOf(obj[0][0]));
				request.setAttribute("UserName", String.valueOf(obj[0][1]));
				request.setAttribute("gender", String.valueOf(obj[0][4]));
				final String photo = String.valueOf(obj[0][2]);
				if (String.valueOf(obj[0][1]) != null && !photo.equals("")) {
					request.setAttribute("profilePhoto", String
							.valueOf(obj[0][3]));
				} else {
					request.setAttribute("profilePhoto", "empimage.gif");
				}

				request.setAttribute("thought", thought);
				session.setAttribute("lastAcctActivity", String
						.valueOf(lastAcctActivity[0][1]));
			}
			// logger.info("in createMenu..");
			request.setAttribute("menuList", menuList);
			request.setAttribute("d1MenuList", d1MenuList);
			request.setAttribute("compName", cmpanyName);
			model.terminate();
		} catch (final Exception e) {

		}
		return "success";
	}

	public String getData() throws Exception {
		return "success";
	}
/**
 * This method is used for getting image for capcha
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int width = 100;
	    int height = 50;
	    Random random = new Random();
	    StringBuffer password = new StringBuffer();
		// logger.info("=======");
		char[] smallChars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'L', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };

		for (int i = 0; i < 4; i++) {
			password.append(smallChars[random.nextInt(smallChars.length)]);
		}// end of loop
		final String chars=password.toString();


	    BufferedImage bufferedImage = new BufferedImage(width, height, 
	                  BufferedImage.TYPE_INT_RGB);

	    Graphics2D g2d = bufferedImage.createGraphics();

	    Font font = new Font("Arial", Font.BOLD, 25);
	    g2d.setFont(font);

	    RenderingHints rh = new RenderingHints(
	           RenderingHints.KEY_ANTIALIASING,
	           RenderingHints.VALUE_ANTIALIAS_ON);

	    rh.put(RenderingHints.KEY_RENDERING, 
	           RenderingHints.VALUE_RENDER_QUALITY);

	    g2d.setRenderingHints(rh);

	    GradientPaint gp = new GradientPaint(0, 0, 
	    Color.black, 0, height/2, Color.black, true);

	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, width, height);

	    g2d.setColor(Color.white);

	    Random r = new Random();
	    int index = Math.abs(r.nextInt()) % 5;

	    final String captcha = password.toString();
	    request.getSession().removeAttribute("myCapcha");
	    request.getSession().setAttribute("myCapcha", captcha );

	    int x = 0; 
	    int y = 0;

	    for (int i = 0; i < smallChars.length; i++) {
			
		}
	    
	    for (int i=0; i<chars.length(); i++) {
	    	if(i==0){
	    		x += 8;
	    	}else{
	        x += 20;// + (Math.abs(r.nextInt()) % 15);
	    	}
	        y = 30;// + Math.abs(r.nextInt()) % 20;
	        g2d.drawChars(chars.toCharArray(), i, 1, x, y);
	    }

	    g2d.dispose();

	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(bufferedImage, "png", os);
	    os.close();
		
}
/**
 * This method is used for getting capcha image
 * @return
 * @throws Exception
 */
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
/**
 * This method is used for getting login bean instance.
 * @return LoginBean
 */
	public org.paradyne.bean.common.LoginBean getLoginBean() {
		return loginBean;
	}
	/**
	 * Method : getModel. Purpose : This method is used to return
	 *  loginBean
	 * 
	 * @return Object : loginBean
	 */
	public Object getModel() {
		return loginBean;
	}
/**
 * This method is used for getting  context
 * @return ServletContext
 */
	public ServletContext getServletContext() {
		return context;
	}
/**
 * this methos is called Onload 
 */
	public String input() {
		String poolName = "";
		String result = "";
		
		long startTime = System.currentTimeMillis();
		HttpSession session = request.getSession();
		String comanyName = "";
		String pool = "";
		// logger.info("Login_input.action");
		try {
			// int abc=Math.random().n;
			final String clientUser = (String) session.getAttribute("session_pool");
			 logger.info("clientUser:: " + clientUser);
			// logger.info("session_pool :" + "session_pool"
			// + session.getId());
			pool = clientUser;
		} catch (final Exception e) {
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
					logger.info("poolName-----in input method----------"
							+ poolName);

					if (poolName == null) {
						poolName = (String) request.getAttribute("infoId");
					}

					  String validClientName = "0";
					try {
						validClientName = existsClient(poolName);
					} catch (final Exception e) {
						validClientName = "0";
					}
						if (validClientName.equals("0")) {
						// response.sendRedirect("http://localhost:8080/pims/common/Login_input.action?infoId=TIxS2uuT3RMp9WGxdbhufQ==");
						response.sendRedirect("http://"
								+ request.getServerName());
						return "success";
					} else {
						poolName = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.URL_ENCRYPTION_KEY)
								.decrypt(poolName);
						session.setAttribute("session_pool", poolName);
					}

					// logger.info("in input method-----------------");
				} catch (final Exception e) {
					// logger.error("Exception in input-----------------" + e);
				}
				final  LoginModel loginModel = new LoginModel(loginBean);
				loginModel.initiate(context, session);
				String logo = null;
				String ppurl = null;		
				Object[][] data = null;

				try {
					data = loginModel.getComponyLogo();

					if (data != null && data.length > 0) {
						logo = String.valueOf(data[0][0]);
						ppurl = String.valueOf(data[0][1]);
						/**If ThirdI's Employee Logged In then they will See the Different Logo and Name*/
						if(request.getServerName().indexOf("the3i.com") != -1){
							comanyName = "Thid I";
							logo="d1logo.jpg";
						}//end if
						else{
							comanyName = String.valueOf(data[0][2]);
						}						
					}
				} catch (final Exception e) {
					logger
							.error("Exception in getComponyLogo input-----------------"
									+ e);
				}
				final String check = loginModel.checkForImageApp();
				if (!check.equals("Y")) {
					loginBean.setUserFlag("false");
					loginBean.setPassFlag("true");
					loginBean.setLoginFlag("true");
					loginBean.setSettingFlag("true");
				}
				long endTime = System	.currentTimeMillis();
				double responseTime = (endTime - startTime) / 1000.0;
				request.setAttribute("respTime", "" + responseTime);
				request.setAttribute("logo", logo);
				request.setAttribute("ppurl", ppurl);
				request.setAttribute("comanyName", comanyName);
				result = "input";
			}

		} catch (final Exception e) {
		}
		return result;
	}
/**
 * This method is used for logout from the system.
 * @return String
 * @throws Exception
 */
	public String logout() throws Exception {
		String infoId = "";
		try {
			HttpSession session = request.getSession();			 
			final String str = (String) session.getAttribute("session_pool");
			String poolName = "";
			String forceLogout = "";
			try {
				forceLogout = (String) request.getParameter("logout");
				if (forceLogout.equals("forceLogout")) {
					request.setAttribute("forceLogout", "forceLogout");
				}
			} catch (final Exception e) {
				// logger.error("Exception in logout----------------" + e);
			}
			try {
				poolName = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).encrypt(str);
			} catch (final Exception e) {
				// logger.error("Exception in logout----------------" + e);
			}
			try {
				/**
				 * Calculate attendance
				 */
				infoId = (String) session.getAttribute("poolId");				
				session.removeAttribute("loginBeanData");
				session.removeAttribute("empId");
				session.removeAttribute("lname");
				session.removeAttribute("session_pool");
				session.invalidate();				
				request.setAttribute("poolName", poolName);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}		 
		request.setAttribute("infoId", infoId);
		return "logout";
	}

	public void prepare() throws Exception {
		loginBean = new LoginBean();
	}
/**
 * This method is used for returning back to input.
 * @return String
 */
	public String returnToInput() {
		String result = "";
		try {
			final  LoginModel login = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			login.initiate(context, session);
			final String updateQuery = " UPDATE hrms_login SET LOGIN_AlREADYLOGGED='Y' WHERE LOGIN_CODE="
					+ this.loginBean .getLoginCode();
			boolean res = login.getSqlModel().singleExecute(updateQuery);
			if (res) {
				result = "input";
			} else {
				result = "input";
			}

			login.terminate();
		} catch (final Exception e) {
		}
		return result;
	}
/**
 * This method is used for saving change password.
 * @return
 * @throws Exception
 */
	public String saveChangePassword() throws Exception {
		String result = "";
		try {

			long startTime = System.currentTimeMillis();
			final  LoginModel login = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean checkCorrectOldPass = login.checkUserPassword();

			login.setCompanyNameAndLogo(request);

			if (checkCorrectOldPass) {
				final  UserModel userModel = new UserModel();
				userModel.initiate(context, session);
				final String password = this.loginBean .getNewpassword();
				final String resultMsg = userModel.checkPasswordSettings(password);
				logger
						.info("Result Msg Is --------------------------------------- "
								+ resultMsg);
				if (!resultMsg.equals("")) {
					addActionMessage(resultMsg);
					login.getPasswordLength(request);
					return result = "send-to-changePwd";
				}

				if (!login.checkReusePassword(this.loginBean , request)) {
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

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return result;
	}
/**
 * This method is used for saving user secure image.
 * @return String
 */
	public String saveImage() {
		String result = "";
		try {
			long startTime = System.currentTimeMillis();
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);

			loginModel.setCompanyNameAndLogo(request);

			boolean flag = loginModel.saveUserSecureData(this.loginBean );
			if (flag) {
				// loginModel.getUserSecureData(loginBean, request);
				this.loginBean .setPassFlag("false");
				this.loginBean .setLoginFlag("false");
				this.loginBean .setImgTxtFlag("false");
				result = returnToInput();
			} else {
				returnToInput();
			}
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			loginModel.terminate();

			String comanyName = "";
			try {
				final Object [][] data = loginModel.getComponyLogo();
				if (data != null && data.length > 0) {
					/**If DecisionOne's Employee Logged In then they will See the Different Company Name*/
					if(request.getServerName().indexOf("mydecisionone.com")!= -1){
						comanyName = "DecisionOne Information Center";	
						//comanyName = "DecisionOne Portal";			
					}else{
						comanyName = String.valueOf(data[0][2]);
					}
				}
			} catch (final Exception e) {
				logger.error("Exception in getComponyLogo--------" + e);
			}
			request.setAttribute("comanyName", comanyName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
/**
 * This method is used for saving user security question.
 * @return String
 */
	public String saveQuestion() {
		String result = "";
		try {

			final  LoginModel login = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			login.initiate(context, session);
			boolean isQueSave = login.saveQuestion();

			login.setCompanyNameAndLogo(request);

			if (isQueSave) {
				final String check = login.checkForImageApp();

				if (check.equals("Y")) {

					result = "forwardTo";
				} else {

					if (this.loginBean .getSettingFlag().equals("true")) {
						this.loginBean .setLoginFlag("true");
						this.loginBean .setPassFlag("true");
						this.loginBean .setUserFlag("false");
					}

					result = returnToInput();
				}

			}

			login.terminate();
		} catch (final Exception e) {
		}
		return result;
	}
/**
 * This method is used for invalidating session.
 * @param result
 * @return String
 */
	public String sessionInvalidate(String result) {
		request.getSession().invalidate();
		// logger.info("sessionInvalidate");

		return result;

	}
/**
 * This method is used for setting login bean
 * @param loginBean
 */
	public void setLoginBean(org.paradyne.bean.common.LoginBean loginBean) {
		this.loginBean = loginBean;
	}
/**
 * This method is used for setting servletContext
 */
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
/**
 * This method is used for setting httpServletRequest
 */
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
/**
 * This method is used for setting httpServletResponse
 */
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}
/**
 * This method is called on Login button.
 * @return String
 */
	public String submitUser() {	
		String result = "invalid";
		
		// logger.info("loginBean.getInvalidFlag()_________________________" +
		// loginBean.getInvalidFlag());
		String poolName = null;
		try {
			long startTime = System.currentTimeMillis();

			result = "";
			
			poolName = request.getParameter("infoId");
			logger.info("In submitUser method 1------------" + poolName);

			if (poolName == null || poolName.equals("null")) {
				 
				response.sendRedirect("http://" + request.getServerName());
				return SUCCESS;
			}
			request.setAttribute("infoId", poolName);
			if (poolName != null) {
				try {
					this.loginBean .setInfoId(poolName);
					poolName = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
							StringEncrypter.URL_ENCRYPTION_KEY)
							.decrypt(poolName);
					request.getSession().setAttribute("session_pool", poolName);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}

		 
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String logo = null;
			String ppurl = null;
			Object data[][] = null;
			String comanyName = null;
			try {
				data = loginModel.getComponyLogo();
				if (data != null && data.length > 0) {
					logo = String.valueOf(data[0][0]);
					ppurl = String.valueOf(data[0][1]);	
					/**If ThirdI's Employee Logged In then they will See the Different Logo and Name*/
					if(request.getServerName().indexOf("the3i.com")!= -1){
						comanyName = "Thid I";	
						logo="d1logo.jpg";
					}//end if
					else{
						comanyName = String.valueOf(data[0][2]);
					}
				}

			} catch (final Exception e) {
				logger.error("Exception in getComponyLogo--------" + e);
			}
			logger.info("logo-------------------------" + logo);
			
			
			
			
			
			boolean isCorrect = loginModel.checkUsername();
			logger.info("isCorrect user name in submitUser method****"
					+ isCorrect);
			if (isCorrect) {
				// logger.info("loginBean.getInvalidFlag()________________ " +
				// loginBean.getInvalidFlag());
				if (this.loginBean .getInvalidFlag().equals("true")) {
					boolean res = checkKeyCode(request, response);
					// logger.info("res of checkKeyCode______________" + res);
					if (res) {
						result = checkUserPassword();
						// logger.info("result of checkUserPassword__________ "
						// + result);
					} else {
						if (this.loginBean .getSettingFlag().equals("true")) {
							this.loginBean .setLoginFlag("true");
							this.loginBean .setPassFlag("true");
							this.loginBean .setUserFlag("false");
						} else {
							this.loginBean .setLoginFlag("true");
							this.loginBean .setPassFlag("true");
							this.loginBean .setUserFlag("false");
							this.loginBean .setSettingFlag("false");
						}
						addActionMessage("Please Enter correct letters as they are shown in the image");
						/*
						 * loginBean
						 * .setInvalidCount(loginBean.getInvalidCount() + 1);
						 */
						request.setAttribute("logo", logo);
						request.setAttribute("ppurl", ppurl);
						request.setAttribute("comanyName", comanyName);

						result = "invalid";
					}
				} else {
					result = checkUserPassword();
					// logger.info("value of checkUserPassword--***-in
					// submituser method------" + result);
					if (result.equals("invalid")) {
						if (this.loginBean .getSettingFlag().equals("true")) {
							this.loginBean .setLoginFlag("true");
							this.loginBean .setPassFlag("true");
							this.loginBean .setUserFlag("false");
						} else {
							this.loginBean .setLoginFlag("true");
							this.loginBean .setPassFlag("true");
							this.loginBean .setUserFlag("false");
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
						this.loginBean .setLoginFlag("true");
						this.loginBean .setPassFlag("true");
						this.loginBean .setUserFlag("false");
						request.setAttribute("logo", logo);
						request.setAttribute("ppurl", ppurl);
						request.setAttribute("comanyName", comanyName);
					}
				}
				// logger.info("value of result----in submituser
				// method-------------------" + result);
				if (result.equals("success")) {
					final String validIp = checkUserLoginIPAdd();
					// logger.info("value of checkUserLoginIPAdd-----" +
					// validIp);
					if (validIp.equals("Yes")) {
						String check = loginModel
								.checkIsUserFirstLogin(this.loginBean );
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
						this.loginBean .setLoginFlag("true");
						this.loginBean .setPassFlag("true");
						this.loginBean .setUserFlag("false");
						result = "invalid";
					}
				}
			} else {
				// logger.info("login 7----------------------------");
			////	addActionMessage("Invalid user name");
				request.setAttribute("infoId", request.getParameter("infoId"));
				if (this.loginBean .getSettingFlag().equals("true")) {
					this.loginBean .setLoginFlag("true");
					this.loginBean .setPassFlag("true");
					this.loginBean .setUserFlag("false");
				} else {
					this.loginBean .setLoginFlag("true");
					this.loginBean .setPassFlag("true");
					this.loginBean .setUserFlag("false");
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
				
			 
				final String loginName = this.loginBean .getLoginName();
				final String password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(this.loginBean .getLoginPassword());

				final Object  loginData[][] = (Object[][]) loginModel.selectLoginData(
						loginName, password);

				
				String userEmpId= String.valueOf(loginData[0][1]);
				session.setAttribute("customerUserEmpIdSession", userEmpId);
				
				final String empDivisionName =loginModel.getEmployeeDivisionName(String
						.valueOf(loginData[0][1]));
				request.setAttribute("comanyName", comanyName);
				request.setAttribute("empDivisionName", empDivisionName);
				request.setAttribute("logo", logo);
			}
			long endTime = System.currentTimeMillis();
			// logger.info("(endTime-startTime)" + (endTime - startTime));
			double responseTime = (endTime - startTime) / 1000.0;
			// logger.info("responseTime/1000" + responseTime);
			request.setAttribute("respTime", "" + responseTime);
			
			session.setAttribute("userType", "I");
			loginModel.terminate();
			try {
				final String check = loginModel.checkForImageApp();
				if (check.equals("Y")) {
					boolean isSettingDefine = loginModel.checkSetting();
					if (isSettingDefine) {
						loginModel.getUserSecureData(this.loginBean , request);
						this.loginBean .setImgTxtFlag("true");
					}
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			if (result.equals("success")) {
				String photoQuery = "";
			}
		} catch (final Exception e) {
			e.printStackTrace();
			result = "invalid";
		}		 
		if(poolName!=null && poolName.equals("pool_glodyne") && result.equals("success")){
			
			/** Check client use mydecisionone.com then 
			 * client able to see the decisionone home page */
			if(request.getServerName().indexOf("mydecisionone.com")!= -1) {
				result="decisionOneHome";
			} else {
				result="glodyneHome";
			}			
		}
		
		if( result.equals("invalid")){
			final  LoginModel loginModel = new LoginModel(this.loginBean );
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			
			final String isClientMultiple = loginModel.getMultipleLogin(loginBean, request);
			String flag = "";
			
			
			
			flag = loginModel.submitnewuser(loginBean);
			//session.setAttribute("customerUserEmpIdSession", loginBean.getCustomerUserEmpId());
			final String isClientMultipleDashboard=loginModel.getMultipleLoginforDashBoard(loginBean, request);
			
			
			
			
			if(isClientMultipleDashboard.equals("")){
					 addActionMessage("Invalid User Name or Password"); 
					 result = "invalid";
				}
			else if(isClientMultipleDashboard.equals("1")){
				if (flag.equals("2")) {
					addActionMessage("Invalid Password!");
					
				} else if (flag.equals("3")) {
					addActionMessage("User is Inactive so can not Login");
					
				}else{
					System.out.println("In else...isClientMultipleDashboard.equals('1') ");
					loginBean.setEmpId("346346361");
					loginBean.setProfileCode("1");
					loginBean.setMultipleProfileCode("1");
					loginBean.setAccessProfileCode("");
					loginBean.setLoginCode("2723");
					session.setAttribute("customerUserEmpIdSession", loginBean.getCustomerUserEmpId());
					session.setAttribute("loginBeanData", loginBean);
					session.setAttribute("empId", "83");
					session.setAttribute("lname", "Premchand Sahu");
					session.setAttribute("poolId", loginBean.getInfoId());
					request.setAttribute("comanyName", "My Company");
					request.setAttribute("empDivisionName", "Anaytic Tool");
					request.setAttribute("logo", "myLogo.png");
					request.setAttribute("UserName", loginBean.getLoginName().trim());
					session.setAttribute("ClientEmailID", loginBean.getLoginName().trim());
					request.setAttribute("UserID", "83");
					//request.setAttribute("dashBoardID", "1");
					request.setAttribute("isClientFlag", "true");
					
					session.setAttribute("homePage","/cr/D1AnalyticHomeExternalUser_input.action");
						
				//	}
					result="decisionOneHome";
				}
			}else{
				
				if (flag.equals("2")) {
					addActionMessage("Invalid Password!");
				} else if (flag.equals("3")) {
					addActionMessage("User is Inactive so can not Login");
				}else{
					
					loginBean.setEmpId("2077");
					loginBean.setProfileCode("1");
					//Added by Prajakta B(4 june 2013) for multiple profile code
					loginBean.setMultipleProfileCode("1");
					// Ends Added by Prajakta B(4 june 2013) for multiple profile code
					loginBean.setAccessProfileCode("");
					loginBean.setLoginCode("1980");
					//loginBean_session.setVisitorNo(String.valueOf(result[0][0]));
					session.setAttribute("customerUserEmpIdSession", loginBean.getCustomerUserEmpId());
					session.setAttribute("loginBeanData", loginBean);
					session.setAttribute("empId", "83");
					session.setAttribute("lname", "Premchand Sahu");
					session.setAttribute("poolId", loginBean.getInfoId());
					request.setAttribute("comanyName", "My Company");
					request.setAttribute("empDivisionName", "Analytic Tool");
					request.setAttribute("logo", "../pages/images/d1logo.jpg");
					request.setAttribute("UserName", loginBean.getLoginName().trim());
					request.setAttribute("UserID", "83");
					session.setAttribute("ClientEmailID", loginBean.getLoginName().trim());
					request.setAttribute("cmpName", "pages/images/d1logo.jpg");
										
					request.setAttribute("isClientFlag", "true");
					session.setAttribute("homePage","/cr/D1AnalyticHomeExternalUser_input.action");
					
					result="decisionOneHome";
				}
			}
		}
		
		return result;
	}	
	
/**
 * This method is used for validating capcha
 * @param captchaId
 * @param inputChars
 * @return boolean
 */
	private boolean validateCaptcha(String captchaId, String inputChars) {
		boolean bValidated = false;
		try {
			bValidated = MyCaptchaService.getInstance().validateResponseForID(
					captchaId, inputChars);
		} catch (CaptchaServiceException cse) {
		}
		return bValidated;
	}
/**
 * This method is used for checking existing client.
 * @param ClientName
 * @return String
 */
	public String existsClient(String ClientName) {

		String client = "0";
		try {
			ResourceBundle boundle = ResourceBundle.getBundle("org.paradyne.lib.ConnectionModel");

			try {
				client = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).decrypt(ClientName);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			 
			try {
				client = boundle.getString(client);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			 
		} catch (final Exception e) {
			e.printStackTrace();
			client = "0";
		}

		return client;
	}

	 
}