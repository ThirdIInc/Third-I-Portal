package org.paradyne.model.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.StringEncrypter.EncryptionException;
import org.paradyne.model.admin.srd.SendMailModel;

public class LoginModel extends ModelBase {
	/** login. */
	private LoginBean login = null;

	/**
	 * @param :
	 *            parameterrized Constructor
	 */

	public LoginModel(LoginBean login) {
		this.login = login;
	}

	/**
	 * @param :
	 *            No parameters Default Constructor
	 */
	public LoginModel() {

	}

	/** logger. */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginModel.class);

	/**
	 * This method is used for selecting login details.
	 * 
	 * @param login
	 * @param password
	 * @return Object[][]
	 */
	public Object[][] selectLoginData(String login, String password) {
		logger.info("inside selectLoginData()________________ ");
		//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE CODE
		final String  selectSql = " SELECT LOGIN_NAME,hrms_login.EMP_ID,PROFILE_CODE,LOGIN_CODE,EMP_ACTIVE,HRMS_EMP_OFFC.EMP_CENTER,ACCESS_PROFILE_CODE,LOGIN_PROFILE,LOGIN_ACCESS_PROFILE "
				+ " FROM hrms_login  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= hrms_login.EMP_ID) "
				+ " WHERE LOGIN_NAME = ? " + "  AND LOGIN_PASSWORD = ?"
				+ " AND TO_DATE(TO_CHAR(NVL(LOGIN_VALID_UPTO,SYSDATE + 1),'DD-MM-YYYY')||'2359','DD-MM-YYYYHH24MI')>= SYSDATE";
	return getSqlModel().getSingleResult(selectSql,
				new Object[] { login, password });
	}

	/**
	 * This method is used for selecting email data.
	 * 
	 * @param empCode
	 * @return Object[][]
	 */
	public Object[][] selectEmailData(String empCode) {
		logger.info(" inside selectEmailData()__________ ");
		final String  selectSql = " SELECT EMPMAIL_USERID,EMPMAIL_PASSWORD FROM HRMS_SETTINGS_EMPMAIL WHERE EMP_ID = ? ";
		return getSqlModel().getSingleResult(selectSql,
				new Object[] { empCode });
	}

	/**
	 * This method is used for saving data into login session table.
	 * 
	 * @param loginCode
	 *            login code if ip address is 192.168.25.189 then
	 * @param ipAddress1
	 *            IP address first 192
	 * @param ipAddress2
	 *            IP address second 168
	 * @param ipAddress3
	 *            IP address third 25
	 * @param ipAddress4
	 *            IP address forth 189
	 * @param port
	 *            port number
	 * @return boolean
	 */
	public boolean saveLoginSession(String loginCode, final String  ipAddress1,
			String ipAddress2, String ipAddress3, String ipAddress4, int port) {
		boolean result = false;
		try {
			final String  insertSql = " INSERT INTO HRMS_LOGIN_SESSION(LOGIN_ID ,LOGIN_DATETIME,LOGIN_IP_ADDR1,LOGIN_IP_ADDR2,LOGIN_IP_ADDR3,LOGIN_IP_ADDR4,LOGIN_PORT) VALUES(?,SYSDATE,?,?,?,?,?) ";
			final Object  loginCd[][] = new Object[1][6];
			loginCd[0][0] = loginCode;
			loginCd[0][1] = ipAddress1;
			loginCd[0][2] = ipAddress2;
			loginCd[0][3] = ipAddress3;
			loginCd[0][4] = ipAddress4;
			loginCd[0][5] = port;
			logger
					.info("saveLoginSession insert query============================"
							+ insertSql);
			result = getSqlModel().singleExecute(insertSql, loginCd);
		} catch (final Exception e) {
			// TODO: handle exception
		}
		/*
		 * String visitString = "SELECT COUNT(*),TO_CHAR(SYSDATE,'Day DD Mon
		 * YYYY HH:MI:SS') FROM hrms_login_SESSION ";
		 * getSqlModel().getSingleResult(visitString);
		 */
		return result;
	}

	/**
	 * This method is used for getting email information
	 * 
	 * @param compCode
	 * @return Object[][]
	 */
	public Object[][] getEmailInfo(String compCode) {

		final String  query = " SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,"
				+ "MAILBOX_PASSW,MAILBOX_FLAG,MAILBOX_AUTH_CHK FROM HRMS_SETTINGS_MAILBOX WHERE MAILBOX_COMP ="
				+ compCode;
		return getSqlModel().getSingleResult(query);

	}

	// --------ADDED BY SHASHIKANT
	/**
	 * This method is used for checking password expiry
	 */
	public boolean checkExpiredPassRe() throws EncryptionException {
		boolean result = false;
		final String  newpsswd = new StringEncrypter(
				StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
				.getExpNewpsswd1());
		logger.info("In checkExpiredPassRe newpsswd------" + newpsswd);
		final String  psswd = " UPDATE HRMS_LOGIN SET LOGIN_PASSWORD='" + newpsswd
				+ "',LOGIN_PWD_CHANGE_DATE=SYSDATE , LOGIN_ALREADYLOGGED='Y' "
				+ " WHERE   LOGIN_CODE=" + login.getLoginCode();
		result = getSqlModel().singleExecute(psswd);

		if (result) {
			final String  backUpQuery = " INSERT INTO HRMS_PASSWORD_HIST(LOGIN_CODE,OLD_PASSWORD,NEW_PASSWORD,LOGIN_CHANGEDATE) "
					+ " VALUES (?,?,?,SYSDATE) ";
			final Object  backUpObj[][] = new Object[1][3];
			backUpObj[0][0] = login.getLoginCode();
			backUpObj[0][1] = login.getOldpssword(); // old password
			backUpObj[0][2] = newpsswd; // new password with encrypt
			result = getSqlModel().singleExecute(backUpQuery, backUpObj);
		}
		return result;
	}

	/**
	 * @param strPort
	 * @param string5
	 * @param string4
	 * @param string3
	 * @param string2
	 * @param string
	 * @param :
	 *            LoginBean login
	 * @return String value of result process the Login as per Flowchart
	 * @throws EncryptionException
	 */

	public String proccessLogin(String ipAddress1, String ipAddress2,
			String ipAddress3, String ipAddress4, int port)
			throws EncryptionException {
		String result = "";
		/**
		 * set All parameters by calling this method
		 */
		getLoginDetails();

		/**
		 * check for username
		 */

		logger
				.info("inside proccessLogin method in model---------------------");
		boolean isCorrect = checkUsername();
		// ----if user name correct
		logger.info("value of isCorrect in proccessLogin--" + isCorrect);
		if (isCorrect) {
			boolean isLock = isLocked();
			logger.info("value of isLock in proccessLogin--" + isLock);
			if (isLock) {
				result = "loginDenied";
			} else {
				boolean isPass = checkPassword();
				logger.info("value of isPass in proccessLogin--" + isPass);
				if (isPass) {
					boolean isExpired = isExpired();
					logger.info("value of isExpired in proccessLogin--"
							+ isExpired);
					if (isExpired) {
						int isExpireDays = 0;
						int passExpPeriod = 0;

						final String  pwdChangeDateQuery = " SELECT TO_CHAR(LOGIN_PWD_CHANGE_DATE,'DD-MM-YYYY') FROM HRMS_LOGIN WHERE LOGIN_CODE="
								+ login.getLoginCode();

						final Object  pwdChangeDateObj[][] = getSqlModel()
								.getSingleResult(pwdChangeDateQuery);

						if (!String.valueOf(pwdChangeDateObj[0][0]).equals(
								"null")
								&& !String.valueOf(pwdChangeDateObj[0][0])
										.equals("")) {
							final String  isExpireQuery = "  SELECT round(to_number(sysdate-LOGIN_PWD_CHANGE_DATE))"
									+ "	FROM HRMS_LOGIN WHERE LOGIN_CODE="
									+ login.getLoginCode() + " ";

							final Object [][] isExpDays = getSqlModel()
									.getSingleResult(isExpireQuery);

							isExpireDays = Integer.parseInt(String
									.valueOf(isExpDays[0][0]));
							passExpPeriod = Integer.parseInt(String
									.valueOf(login.getPassExpirePrdcity()));

							if (isExpireDays >= passExpPeriod) {
								result = "send-to-changePassword";
							} else {
								boolean isActive = isActive();
								logger
										.info("value of isActive in proccessLogin--"
												+ isActive);
								if (isActive) {
									result = "loginSuccess";
								} else {
									result = "notActive";
								}
							}

						} else {
							result = "send-to-changePassword";
						}

					} else {
						boolean isActive = isActive();
						logger.info("value of isActive in else proccessLogin--"
								+ isActive);
						if (isActive) {
							result = "loginSuccess";
						} else {
							result = "notActive";
						}
					}
				}
				// --------for wrong
				// password---------------------------------------
				else {
					/**
					 * check for the invalid password entries and if invalid
					 * entries are exceeded lockAttempts lock the user and
					 * result="loginDenied"
					 */
					int lockAttm = 0;
					int countAtt = 0;
					int countinvalcheck = 0;
					logger.info("inside wrong password=======11==========");
					final String  insertSql = " INSERT INTO HRMS_LOGIN_SESSION(LOGIN_ID ,LOGIN_DATETIME,LOGIN_IP_ADDR1,LOGIN_IP_ADDR2,LOGIN_IP_ADDR3,LOGIN_IP_ADDR4,LOGIN_PORT,LOGIN_SUCCESS_FLAG) VALUES(?,SYSDATE,?,?,?,?,?,'N') ";
					final Object  loginCd[][] = new Object[1][6];
					loginCd[0][0] = login.getLoginCode();
					loginCd[0][1] = ipAddress1;
					loginCd[0][2] = ipAddress2;
					loginCd[0][3] = ipAddress3;
					loginCd[0][4] = ipAddress4;
					loginCd[0][5] = port;
					final String  updateQuery = " UPDATE HRMS_LOGIN SET LOGIN_LASTLOGIN_DATE=SYSDATE WHERE LOGIN_CODE="
							+ login.getLoginCode();
					getSqlModel().singleExecute(insertSql, loginCd);
					getSqlModel().singleExecute(updateQuery);// update query
					// here--------------------------
					final String  countAttmQuery = " SELECT LOGIN_DATETIME,LOGIN_SUCCESS_FLAG,ROWNUM FROM HRMS_LOGIN_SESSION where login_id="
							+ login.getLoginCode() + " ORDER BY ROWNUM DESC ";
					final Object [][] countAttmData = getSqlModel().getSingleResult(
							countAttmQuery);
					// ----------------calculate wrong attempt

					logger
							.info("login.getPassLockFlg() in wrong password================"
									+ login.getPassLockFlg());
					if (String.valueOf(login.getPassLockFlg()).equals("Y")) {
						lockAttm = Integer.parseInt(String.valueOf(login
								.getPassLockAttmts()));
						if (countAttmData.length > lockAttm) {
							for (int i = 0; i < lockAttm; i++) {
								if (String.valueOf(countAttmData[i][1]).equals(
										"N")) {
									countAtt++;
									// logger.info("count is N
									// value======countAtt======"+ countAtt);
								}
							}
							for (int i = 0; i < lockAttm; i++) {
								if (String.valueOf(countAttmData[i][1]).equals(
										"Y")) {
									break;
								} else {
									countinvalcheck++;
								}
							}
							logger.info("=====lockAttm in wrong password======"
									+ lockAttm);
							logger
									.info("count is N value======countAtt in wrong password======"
											+ countAtt);
							logger
									.info("=====countinvalcheck in wrong password======"
											+ countinvalcheck);

							// int check =countAtt-countinvalcheck;
							// logger.info("=====total wrong
							// attempts-------======"+check);
							/*
							 * login.setInvalAttmpCount(String
							 * .valueOf(countinvalcheck));
							 */
							login.setInvalAttmpCount(String.valueOf(lockAttm
									- countinvalcheck));
							if (countAtt >= lockAttm) {
								final String  lochUserQuery = "UPDATE HRMS_LOGIN SET LOCK_FLAG='Y' WHERE LOGIN_CODE="
										+ login.getLoginCode() + " ";
								getSqlModel().singleExecute(lochUserQuery);
								result = "loginDenied";
							} else
								result = "go-for-imageAuthentication";
						} else {
							for (int i = 0; i < countAttmData.length; i++) {
								if (String.valueOf(countAttmData[i][1]).equals(
										"N")) {
									countAtt++;
									// logger.info("count is N
									// value======countAtt======"+ countAtt);
								}
							}
							login.setInvalAttmpCount(String.valueOf(lockAttm
									- countAtt));
							result = "go-for-imageAuthentication";
						}
					} else {
						result = "go-for-imageAuthentication";
					}
				}
			}
		}
		// -----------------------if user name invalid
		else {
			result = "go-for-imageAuthentication";
		}

		logger
				.info("value of result----in proccessLogin model-----------------"
						+ result);
		return result;

	}

	/**
	 * 
	 * 
	 * set all variables here select from configuration and set following bean
	 * variables isLock,isExpired ,lockAttemps,lockPriodicity,expirePeriodicity
	 * 
	 */
	public void getLoginDetails() {

		final String  getLoginQuery = "SELECT PWD_EXPIRY_FLAG, PWD_EXPIRY_PERIODICITY, PWD_REUSE_FLAG, PWD_REUSE_PERIODICITY,PWD_LOCK_FLAG,PWD_LOCK_PERIODICITY,PWD_LOCK_ATTEMPTS "
				+ " FROM HRMS_SETTINGS_PWD ";
		logger.info("In getLoginDetails query---------" + getLoginQuery);

		final Object [][] loginData = getSqlModel().getSingleResult(getLoginQuery);
		if (loginData != null && loginData.length > 0) {
			login.setPassExpireFlg(String.valueOf(loginData[0][0]));
			login.setPassExpirePrdcity(String.valueOf(loginData[0][1]));
			login.setPassReuseFlg(String.valueOf(loginData[0][2]));
			login.setPassReusePrdcity(String.valueOf(loginData[0][3]));
			login.setPassLockFlg(String.valueOf(loginData[0][4]));
			login.setPassLockPrdcity(String.valueOf(loginData[0][5]));
			login.setPassLockAttmts(String.valueOf(loginData[0][6]));
		}

	}

	/**
	 * This method is used for checking correct user name
	 * 
	 * @return boolean value of isCorrect check only username here
	 */
	public boolean checkUsername() {
		boolean isCorrect = false;
		final String  chkUserQuery = " SELECT LOGIN_NAME,LOGIN_CODE,LOGIN_SECURE_IMG,LOGIN_SECURE_MSG FROM HRMS_LOGIN"
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOGIN.EMP_ID)"
				+ " WHERE LOGIN_NAME= ?  AND EMP_STATUS='S'";
		final Object [][] userData = getSqlModel().getSingleResult(chkUserQuery,
				new Object[] { login.getLoginName() });
		if (userData != null && userData.length > 0
				&& !String.valueOf(userData[0][0]).equals("null")) {
			login.setExpPassEmpName(String.valueOf(userData[0][0]));
			login.setLoginCode(String.valueOf(userData[0][1]));
			isCorrect = true;
		}
		logger.info("value of isCorrect in checkUsername-----");
		return isCorrect;
	}

	/**
	 * 
	 * @return boolean value of isLock check user is already Locked or not
	 */
	// ----conf: is Locked user
	public boolean isLocked() {
		logger.info("Inside isLocked method in model--------------"
				+ login.getPassLockFlg());
		boolean isLock = false;
		int chkAttm = 0;
		int chklockPeriodicity = 0;
		if (String.valueOf(login.getPassLockFlg()).equals("Y")) {// is lock
			// used
			logger.info("-----under is locked---" + login.getPassLockFlg());
			final String  chkUserQuery = " SELECT LOGIN_NAME,LOCK_FLAG FROM HRMS_LOGIN WHERE LOGIN_NAME= ?";
			final Object [][] emplockData = getSqlModel().getSingleResult(
					chkUserQuery, new Object[] { login.getLoginName() });
			// String chkUserHrs=" select
			// round(to_number(sysdate-LOGIN_PWD_CHANGE_DATE)*24) from
			// hrms_login where login_code="+login.getLoginCode()+" ";
			final String  chkUserHrs = " SELECT round(to_number(sysdate-LOGIN_DATETIME)*24),rownum  FROM HRMS_LOGIN_SESSION  WHERE LOGIN_ID="
					+ login.getLoginCode() + "  order   by rownum desc ";
			final Object [][] empHrskData = getSqlModel().getSingleResult(chkUserHrs);
			if (emplockData.length > 0
					&& String.valueOf(emplockData[0][1]).equals("Y"))// is
			// user
			// locked
			{
				if (empHrskData.length > 0) {
					chklockPeriodicity = Integer.parseInt(String
							.valueOf(empHrskData[0][0]));
					chkAttm = Integer.parseInt(String.valueOf(login
							.getPassLockPrdcity()));
					logger.info("-----chklockPeriodicity in isLocked---"
							+ chklockPeriodicity);
					logger.info("-----chkAttm in isLocked---" + chkAttm);
					if (chklockPeriodicity < chkAttm) {
						isLock = true;
						logger.info("-----under is locked--****Inside true-");
					} else {
						// ----update flag "Y"
						final String  lochUserQuery = "UPDATE HRMS_LOGIN SET LOCK_FLAG='N' WHERE LOGIN_CODE= ?";
						getSqlModel().singleExecute(lochUserQuery,
								new Object[][] { { login.getLoginCode() } });
						logger.info("-----under is locked--****Inside False-");
					}
				}

			}

		}
		logger.info("value of isLock in model......" + isLock);
		return isLock;
	}

	/**
	 * 
	 * @return boolean value of isPass check password is correct or not
	 * @throws EncryptionException
	 */
	public boolean checkPassword() throws EncryptionException {
		logger.info("---in checkPassword method in model----");
		boolean isPass = false;
		final String  password = new StringEncrypter(
				StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
				.getLoginPassword());

		final String  chkPassQuery = " SELECT LOGIN_NAME FROM hrms_login WHERE LOGIN_NAME=? AND LOGIN_PASSWORD=?";

		final Object [][] chkPassData = getSqlModel().getSingleResult(chkPassQuery,
				new Object[] { login.getLoginName(), password });
		if (chkPassData != null && chkPassData.length > 0) {
			login.setTxt(login.getLoginPassword());
			isPass = true;
		}
		logger.info("---value of isPass in checkPassword method model----"
				+ isPass);
		return isPass;
	}

	/**
	 * 
	 * @return boolean value of isPass check users expiry of password
	 */
	public boolean isExpired() {
		boolean isExprd = false;
		if (String.valueOf(login.getPassExpireFlg()).equals("Y")) {// is lock
			// used
			isExprd = true;
		}
		logger.info("----value of isExprd in model---" + isExprd);
		return isExprd;
	}

	/**
	 * 
	 * @return boolean value of isActive check user active or not
	 * @throws EncryptionException
	 */
	public boolean isActive() {
		logger.info("--------inside isActive in model---");
		boolean isActive = false;
		final String  chkPassQuery = " SELECT LOGIN_NAME,EMP_ACTIVE FROM HRMS_LOGIN WHERE LOGIN_CODE="
				+ login.getLoginCode() + " ";
		final Object [][] chkPassData = getSqlModel().getSingleResult(chkPassQuery);
		if (chkPassData != null && chkPassData.length > 0) {
			if (String.valueOf(chkPassData[0][1]).equals("Y")) {
				isActive = true;
			}
		}
		logger.info("--------value of isActive in model---" + isActive);
		return isActive;
	}

	/**
	 * @return boolean value of isActive insert the details into
	 *         hrms_login_SESSION table
	 */
	public boolean setSessionDetails() {
		boolean isActive = false;
		/**
		 * TO-DO
		 * 
		 */
		return isActive;
	}

	/**
	 * This method is used for getting secured question for a user
	 * 
	 * @param loginCode
	 * @return
	 */
	public String getSecureQuestion(String loginCode) {
		// TODO Auto-generated method stub
		String result = "";
		try {

			final String  forgotPassQue = "SELECT QUES_CODE,SECURITY_QUESNAME FROM HRMS_SECURITY_ANSWER "
					+ " INNER JOIN HRMS_SECURITY_QUESTIONS ON(HRMS_SECURITY_ANSWER.QUES_CODE=HRMS_SECURITY_QUESTIONS.SECURITY_QUESCODE)"
					+ " WHERE LOGIN_CODE="
					+ loginCode
					+ " ORDER BY DBMS_RANDOM.VALUE ";

			final Object [][] questionObj = getSqlModel().getSingleResult(
					forgotPassQue);
			if (questionObj != null && questionObj.length > 0
					&& !String.valueOf(questionObj[0][0]).equals("null")) {
				login.setForgotPassQueCode(String.valueOf(questionObj[0][0]));
				login.setForgotPassQue(String.valueOf(questionObj[0][1]));
				login.setLoginCode(loginCode);
				result = "Yes";
			} else {
				result = "No";
			}

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * check which parameter that user has entered then check whether the user
	 * entered parameter is correct or wrong if it is correct check the user is
	 * active/in service if valid check mail id and is it alive or not if
	 * available send mail
	 */
	public String getUserDetails(HttpServletRequest request) throws Exception {

		final String  poolName = (String) session.getAttribute("session_pool");
		final String [] company = poolName.split("_");
		String URL = "";
		String compInfoId = "";

		try {
			/*
			 * URL = "http://" + request.getServerName() + ":" +
			 * request.getServerPort() +request.getContextPath()+"/client/" +
			 * company[1];
			 */
			// URL = "http://" + request.getServerName() + ":"
			// + request.getServerPort() + "/hrwork/client/" + company[1];
			ResourceBundle boundle = ResourceBundle
					.getBundle("org.paradyne.lib.ConnectionModel");
			String clientName = "www";
			try {
				clientName = boundle.getString(poolName);
			} catch (final Exception e) {
				e.printStackTrace();
				clientName = "www";
			}
				if (request.getRequestURL().toString().contains(
					"http://localhost:8080")) {
				URL = "http://www.the3i.com";
			} else {
				URL = "http://" + clientName + ".com";
			}

			/*
			 * compInfoId=new
			 * StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
			 * .encrypt(company[1]); login.setInfoId(compInfoId);
			 * logger.info("comp name"+company[1]+" ID"+compInfoId);
			 * logger.info("url to display"+URL);
			 */

		} catch (final Exception e) {

			logger.info(e);
		}

		String username;
		final String [] to_Emailid = new String[1];
		final String [] subject = { "Login Details" };
		final String [] message = new String[1];
		int len = 0;
		String LoginPassStr = "";
		String msgr = "";

		if (login.getUserNamefg().length() > 0) {
			// for user name
			final String  userNameQuery = "SELECT LOGIN_NAME  FROM hrms_login  WHERE LOGIN_NAME=?";

			final Object [][] userNameData = getSqlModel().getSingleResult(
					userNameQuery, new Object[] { login.getUserNamefg() });
			logger.info("----------------------------------------");
			final String  userDetailsQuery = "SELECT LOGIN_NAME ,LOGIN_PASSWORD,EMP_STATUS,EMP_ACTIVE,ADD_TYPE,ADD_EMAIL,"
					+ "EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOGIN_CODE ,HRMS_LOGIN.EMP_ID FROM HRMS_LOGIN "
					+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					// + "LEFT JOIN HRMS_TITLE
					// ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
					+ "WHERE LOGIN_NAME =? AND ADD_TYPE='P'";

			final Object [][] userDetails = getSqlModel().getSingleResult(
					userDetailsQuery, new Object[] { login.getUserNamefg() });
			logger.info("kkkkkkkkk1");
			logger.info(">>>>>>>>kriss>>userData length" + userDetails.length);
			if (userNameData.length > 0) {
				if (userDetails.length > 0) {
					try {
						for (int i = 0; i < userDetails.length; i++) {
							msgr = String.valueOf(userDetails[i][0])
									+ ","
									+ new StringEncrypter("DESede")
											.decrypt(String
													.valueOf(userDetails[i][1]))
									+ ",";
							LoginPassStr = LoginPassStr + msgr;
						}
					} catch (final Exception e) {

					}

					logger.info(">>>>getting String from db" + LoginPassStr);

					for (int i = 0; i < userDetails.length; i++) {
						username = String.valueOf(userDetails[i][6]);
						to_Emailid[0] = String.valueOf(userDetails[i][5]);
						logger.info(userDetails.length);

						if (String.valueOf(userDetails[i][0]).equals(
								login.getUserNamefg())) {

							if (String.valueOf(userDetails[i][2]).equals("S")) {

								if (String.valueOf(userDetails[i][3]).equals(
										"Y")) {

									if (!String.valueOf(userDetails[i][5])
											.equals("null")) {
										// CALL FOR SEND MAIL
										// variable to check,to call
										// sendMail
										// method
										String checkForgotPassQueSetting = checkSettingForForgotPassQues();

										String result = "";
										if (checkForgotPassQueSetting
												.equals("Y")) {
											result = getSecureQuestion(String
													.valueOf(userDetails[0][7]));

										}
										if (result.equals("Yes")) { // result.equals("Yes")
											return "forgot";
										} else {

											len++;
											logger.info("len variabaleewwww"
													+ len);
											if (len == userDetails.length) {
												String[] temp = null;
												temp = LoginPassStr.split(",");
												String loginPassMsg = "";
												String lp = "";
												// display login name and
												// password in
												// temp
												for (int z = 0; z < temp.length; z++) {
													logger.info(temp[z]);
												}
												// creating message containing
												// login
												// and password details

												for (int z = 0; z < temp.length; z = z + 2) {
													lp = "User Name :"
															+ temp[z]
															+ "<br>Password  :"
															+ temp[z + 1]
															+ "<br><br>";
													logger.info(z);
													loginPassMsg = loginPassMsg
															+ lp;
													logger.info(loginPassMsg);
												}

												logger
														.info("calling mail method");

												try {
													message[0] = getMessage(
															username, URL,
															loginPassMsg);
													session
															.setAttribute(
																	"empId",
																	String
																			.valueOf(userDetails[0][8]));
													 
													MailUtility mod = new MailUtility();
													mod.initiate(context,
															session);

													mod
															.sendMail(
																	to_Emailid,
																	mod
																			.getDefaultMailIds(),
																	subject[0],
																	message[0],
																	"",
																	String
																			.valueOf(userDetails[0][8]),
																	true);

													mod.terminate();
												} catch (final Exception e) {
													logger
															.info("Exception found"
																	+ e
																			.getMessage());

													return "noMailBox";
												}

												return "mail";
											}
										}
									} else {

										return "noEmailId";
									}
								} else {

									return "inactive";
								}
							} else {
								return "notInSevvice";
							}
						}
					}
				} else {
					return "notPermanentAdd";
				}
			} else {
				return "invalidUserName";
			}
		}

		// checking email id

		else if (login.emailIdfg.length() > 0) {
			final String  mailIdQuery = "SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS WHERE ADD_EMAIL=? ";
			final Object [][] emailData = getSqlModel().getSingleResult(mailIdQuery,
					new Object[] { login.getEmailIdfg() });

			final String  mailQuery = " SELECT ADD_EMAIL,ADD_TYPE,EMP_STATUS,EMP_ACTIVE,LOGIN_NAME ,LOGIN_PASSWORD,"
					+ "TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOGIN_CODE,HRMS_LOGIN.EMP_ID FROM HRMS_EMP_ADDRESS "
					+ "LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "LEFT JOIN HRMS_LOGIN ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "WHERE ADD_EMAIL=? AND ADD_TYPE='P'";

			final Object [][] mailData = getSqlModel().getSingleResult(mailQuery,
					new Object[] { login.getEmailIdfg() });
			logger.info(">>>mailQuery>>" + mailData.length);

			if (emailData.length > 0) {
				if (mailData.length > 0) {
					try {
						for (int i = 0; i < mailData.length; i++) {
							msgr = String.valueOf(mailData[i][4])
									+ ","
									+ new StringEncrypter("DESede")
											.decrypt(String
													.valueOf(mailData[i][5]))
									+ ",";
							LoginPassStr = LoginPassStr + msgr;
						}
					} catch (final Exception e) {

					}

					logger.info(">>>>username and password String from db "
							+ LoginPassStr);

					for (int i = 0; i < mailData.length; i++) {
						username = String.valueOf(mailData[i][6]);
						to_Emailid[0] = String.valueOf(mailData[i][0]);

						logger.info(">>krishna> >>"
								+ String.valueOf(mailData[0][0]).equals(
										login.getEmailIdfg()));

						if (String.valueOf(mailData[0][0]).equals(
								login.getEmailIdfg())) {

							logger.info(String.valueOf(mailData[0][0]).equals(
									login.getEmailIdfg()));
							if (String.valueOf(mailData[0][2]).equals("S")) {
								if (String.valueOf(mailData[0][3]).equals("Y")) {
									// CALL FOR SEND MAIL

									logger
											.info(">>>callingggggggggg mail method");

									// variable to check,to call
									// sendMailWithThread method
									len++;
									logger.info("len variable" + len);

									String result = "";
									if (mailData != null && mailData.length > 1) {
										getUserNames();
										return "userNameJsp";
									} else {

										final String  checkForgotPassQueSetting = checkSettingForForgotPassQues();

										if (checkForgotPassQueSetting
												.equals("Y")) {
											result = getSecureQuestion(String
													.valueOf(mailData[0][7]));
										}

										/*
										 * result = getSecureQuestion(String
										 * .valueOf(mailData[0][7]));
										 */
									}

									logger
											.info("value of result------------------"
													+ result);
									if (result.equals("Yes")) {// result.equals("Yes")
										return "forgot";
									} else {

										if (len == mailData.length) {

											String[] temp = null;
											temp = LoginPassStr.split(",");

											String loginPassMsg = "";
											String lp = "";
											// display login name and password
											// in

											for (int z = 0; z < temp.length; z++) {
												logger.info(temp[z]);
											}
											// creating actual message of login
											// and
											// password
											for (int z = 0; z < temp.length; z = z + 2) {
												lp = "User Name :" + temp[z]
														+ "<br>Password  :"
														+ temp[z + 1]
														+ "<br><br>";
												logger.info(z);
												loginPassMsg = loginPassMsg
														+ lp;
												logger.info(loginPassMsg);
											}

											logger
													.info(">>>>>calling mail method");
											try {
												message[0] = getMessage(
														username, URL,
														loginPassMsg);
												session
														.setAttribute(
																"empId",
																String
																		.valueOf(mailData[0][8]));
											 
												MailUtility mod = new MailUtility();
												mod.initiate(context, session);

												mod
														.sendMail(
																to_Emailid,
																mod
																		.getDefaultMailIds(),
																subject[0],
																message[0],
																"",
																String
																		.valueOf(mailData[0][8]),
																true);
												mod.terminate();
											} catch (final Exception e) {
												logger.info("Exception found"
														+ e.getMessage());

												return "noMailBox";
											}

											return "mail";
										}
									}
								} else {

									return "inactive";
								}

							} else {

								return "notInSevvice";
							}
						} else {

							return "noEmailId";
						}
					}
				}

				else {
					return "notPermanentAdd";
				}

			}

			else {
				return "noEmailId";
			}
		}
		// checking employee id

		else if (login.getEmpIdfg().length() > 0) {

			final String  empIdQuery = "SELECT EMP_TOKEN FROM HRMS_EMP_OFFC WHERE EMP_TOKEN= ? ";
			final Object [][] empIdData = getSqlModel().getSingleResult(empIdQuery,
					new Object[] { login.getEmpIdfg() });

			final String  empQuery = "SELECT EMP_TOKEN,EMP_STATUS,EMP_ACTIVE,ADD_EMAIL,ADD_TYPE,LOGIN_NAME,LOGIN_PASSWORD,"
					+ "TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOGIN_CODE,HRMS_LOGIN.EMP_ID FROM HRMS_EMP_OFFC "
					+ "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "LEFT JOIN HRMS_LOGIN ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_OFFC.EMP_ID)WHERE EMP_TOKEN= ? AND ADD_TYPE='P'";

			final Object [][] empData = getSqlModel().getSingleResult(empQuery,
					new Object[] { login.getEmpIdfg() });

			if (empIdData.length > 0) {

				if (empData.length > 0) {

					try {
						for (int i = 0; i < empData.length; i++) {
							msgr = String.valueOf(empData[i][5])
									+ ","
									+ new StringEncrypter("DESede")
											.decrypt(String
													.valueOf(empData[i][6]))
									+ ",";
							LoginPassStr = LoginPassStr + msgr;
						}
					} catch (final Exception e) {

					}

					logger.info(">>>>getting String from db" + LoginPassStr);

					for (int i = 0; i < empData.length; i++) {
						username = String.valueOf(empData[i][7]);
						to_Emailid[0] = String.valueOf(empData[i][3]);

						if (String.valueOf(empData[0][0]).equals(
								login.getEmpIdfg())) {

							logger.info(">>>empId"
									+ String.valueOf(empData[0][0]));

							if (String.valueOf(empData[0][1]).equals("S")) {

								if (String.valueOf(empData[0][2]).equals("Y")) {

									if (!String.valueOf(empData[0][3]).equals(
											"null")) {
										// CALL FOR SEND MAIL
										// variable to check,to call
										// sendMailWithThread
										// method
										len++;
										logger.info("len variabal" + len);
										String result = "";
 

										if (empData != null
												&& empData.length > 1) {
											getUserNames();
											return "userNameJsp";
										} else {

											final String  checkForgotPassQueSetting = checkSettingForForgotPassQues();

											if (checkForgotPassQueSetting
													.equals("Y")) {
												result = getSecureQuestion(String
														.valueOf(empData[0][8]));
											}

											/*
											 * result = getSecureQuestion(String
											 * .valueOf(empData[0][8]));
											 */
										}

										if (result.equals("Yes")) {// result.equals("Yes")
											return "forgot";
										} else {

											if (len == empData.length) {

												String[] temp = null;
												temp = LoginPassStr.split(",");

												String loginPassMsg = "";
												String lp = "";
												// display login name and
												// password
												for (int z = 0; z < temp.length; z++) {
													logger.info(temp[z]);
												}
												// creating actual message of
												// login
												// and pwd

												for (int z = 0; z < temp.length; z = z + 2) {
													lp = "User Name :"
															+ temp[z]
															+ "<br>Password  :"
															+ temp[z + 1]
															+ "<br><br>";
													logger.info(z);
													loginPassMsg = loginPassMsg
															+ lp;
													logger.info(loginPassMsg);
												}
												// call method to get message

												logger
														.info(">>>>>>calling mail method");
												try {

													message[0] = getMessage(
															username, URL,
															loginPassMsg);
													session
															.setAttribute(
																	"empId",
																	String
																			.valueOf(empData[0][9]));
												 
													MailUtility mod = new MailUtility();
													mod.initiate(context,
															session);

													mod
															.sendMail(
																	to_Emailid,
																	mod
																			.getDefaultMailIds(),
																	subject[0],
																	message[0],
																	"",
																	String
																			.valueOf(empData[0][9]),
																	true);
													mod.terminate();
												} catch (final Exception e) {
													logger
															.info("Exception found"
																	+ e
																			.getMessage());

													return "noMailBox";
												}

												return "mail";

											}
										}
									} else {

										return "noEmailId";
									}
								} else {

									return "inactive";
								}

							} else {

								return "notInSevvice";
							}

						}

					}
				} else {
					logger.info("invalid empId");
					return "notPermanentAdd";
				}
			} else {
				return "noEmpId";
			}

		}// checking empid

		logger.info(">>>>>>>>>>>>>>>>>>>>..returning invalidUserName");

		return "notPermanentAdd";

	}

	/**
	 * This method is used for sending password mail to user
	 * 
	 * @param request
	 * @return String
	 */
	public String mailPasswordToUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		final String  poolName = (String) session.getAttribute("session_pool");
		final String [] company = poolName.split("_");
		String URL = "";
		String compInfoId = "";

		try {

			ResourceBundle boundle = ResourceBundle
					.getBundle("org.paradyne.lib.ConnectionModel");
			  String  clientName = "www";
			try {
				clientName = boundle.getString(poolName);
			} catch (final Exception e) {
				e.printStackTrace();
				clientName = "www";
			}

			if (request.getRequestURL().toString().contains(
					"http://www.myglodyne")) {
				URL = "http://www.the3i.com";
			} else {
				URL = "http://" + clientName + ".com";
			}

			String username;
			String empName;
			final String [] to_Emailid = new String[1];
			final String [] subject = { "Login Details" };
			final String [] message = new String[1];
			int len = 0;

			String msgr = "";

			final String  userDetailsQuery = "SELECT LOGIN_NAME ,LOGIN_PASSWORD,EMP_STATUS,EMP_ACTIVE,ADD_TYPE,ADD_EMAIL,"
					+ "TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOGIN_CODE ,HRMS_LOGIN.EMP_ID FROM HRMS_LOGIN "
					+ "LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_LOGIN.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
					+ "WHERE LOGIN_CODE =? AND ADD_TYPE='P'";

			final Object [][] userDetails = getSqlModel().getSingleResult(
					userDetailsQuery, new Object[] { login.getLoginCode() });

			empName = String.valueOf(userDetails[0][6]);

			username = String.valueOf(userDetails[0][0]);

			final String  password = new StringEncrypter("DESede").decrypt(String
					.valueOf(userDetails[0][1]));

			to_Emailid[0] = String.valueOf(userDetails[0][5]);
			msgr = "User Name : " + username + "<br>Password  : " + password
					+ "<br><br>";

			if (String.valueOf(userDetails[0][2]).equals("S")) {
				if (String.valueOf(userDetails[0][3]).equals("Y")) {

					if (!String.valueOf(userDetails[0][5]).equals("null")) {

						try {
							message[0] = getMessage(empName, URL, msgr);
							session.setAttribute("empId", String
									.valueOf(userDetails[0][8]));
							MailUtility mod = new MailUtility();
							mod.initiate(context, session);

							mod.sendMail(to_Emailid, mod.getDefaultMailIds(),
									subject[0], message[0], "", String
											.valueOf(userDetails[0][8]), true);

							mod.terminate();
						} catch (final Exception e) {
							logger.info("Exception found" + e.getMessage());
							e.printStackTrace();

							return "noMailBox";
						}

						return "mail";

					} else {

						return "noEmailId";
					}
				} else {

					return "noEmailId";
				}
			} else {
				return "notInSevvice";
			}

		} catch (final Exception e) {

		}

		return "";

	}

	/**
	 * This method is used for getting message for a mail
	 * 
	 * @param uname
	 * @param url
	 * @param msg
	 * @return String
	 */
	public String getMessage(String uname, String url, String msg) {
		final String [] htmlText = new String[1];
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
				+ uname
				+ "</b>, </p><br /> "
				+ "Welcome to HRMS portal "
				+ "Kindly find your account information below.</p> "
				+ "<br /><p>HRMS Portal Link :<a href='"
				+ url
				+ "'>"
				+ url
				+ "</a></p>"
				+ msg

				+ "<br /><p>For any assistance, please contact the System Administrator.</p><br />"
				+ "<b>Note:-</b> Please change your password for security reasons.<br><p>Best Regards,</p>"
				+ "<p>Human Resource Team</p>" + "</td> " + "</tr> "
				+ "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);

		return tempMsg;

	}

	/**
	 * This method is used for getting password setting for max length of
	 * password.
	 * 
	 * @param request
	 */
	public void getPasswordLength(HttpServletRequest request) {
		try {
			final String  query = " SELECT NVL(PWD_MIN_LENGTH,'0'), NVL(PWD_MAX_LENGTH,'0'),PWD_INCLUDE_ALPHA,"
					+ "PWD_INCLUDE_SPCHAR,PWD_INCLUDE_NUM,PWD_INCLUDE_UPCASE FROM HRMS_SETTINGS_PWD ";
			final Object [][] lengthObj = getSqlModel().getSingleResult(query);
			logger.info("In Login getPasswordLength >>>>>>>>>>>>>>>>>>>>>>. "
					+ lengthObj.length);
			logger.info("PWD_MIN_LENGTH >>>>>>>>>>>>>>>>>>>>>>. "
					+ lengthObj[0][0]);
			logger.info("PWD_MAX_LENGTH >>>>>>>>>>>>>>>>>>>>>>. "
					+ lengthObj[0][1]);
			if (lengthObj != null & lengthObj.length > 0) {
				request.setAttribute("passInfoObj", lengthObj);
			}
		} catch (final Exception e) {

			logger
					.error("Exception in getPasswordLength------------------"
							+ e);
		}
	}

	/**
	 * This method is used for checking password reuse.
	 * 
	 * @param bean
	 * @param request
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkReusePassword(LoginBean bean, HttpServletRequest request)
			throws Exception {
		try {
			final String  reuseQuery = " SELECT PWD_REUSE_FLAG,NVL(PWD_REUSE_PERIODICITY,0) FROM HRMS_SETTINGS_PWD ";
			final Object [][] reuseFlag = getSqlModel().getSingleResult(reuseQuery);
			if (reuseFlag != null) {
				int reuseCount = Integer.parseInt(String
						.valueOf(reuseFlag[0][1]));
				if (reuseFlag.length > 0
						&& String.valueOf(reuseFlag[0][0])
								.equalsIgnoreCase("Y")) {
					request.setAttribute("reusePeriodicity", reuseCount);
					final String  psswdHistoryQuery = "SELECT NEW_PASSWORD FROM HRMS_PASSWORD_HIST WHERE LOGIN_CODE = ? ORDER BY LOGIN_CHANGEDATE DESC";
					final Object [][] psswdHistory = getSqlModel().getSingleResult(
							psswdHistoryQuery,
							new Object[] { bean.getLoginCode() });
					if (psswdHistory != null & psswdHistory.length > 0) {
						for (int i = 0; i < reuseCount; i++) {
							final String  newEncriptPwd = new StringEncrypter(
									StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
									.encrypt(bean.getExpNewpsswd1());
							if (newEncriptPwd.equals(String
									.valueOf(psswdHistory[i][0]))) {
								logger.info("password is repeating....!!  "
										+ psswdHistory[i][0] + "----- "
										+ newEncriptPwd);
								return false;
							}
						}
					}
				}
			}
			return true;
		} catch (final Exception e) {
			logger.info("Exception in password changing or user management "
					+ e);
			e.printStackTrace();
			return true;
		}
	}

	/**
	 * This method is used for checking exception employee fro IP filtering
	 * 
	 * @param fileName
	 * @param request
	 * @param loginBean
	 * @return boolean
	 */
	public boolean checkExceptionsLogin(String fileName,
			HttpServletRequest request, LoginBean loginBean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		final String  fName = fileName + "\\xml\\IPFilter\\ipfilter.xml";
		File file = new File(fName);
		if (file.exists()) {
			try {
				Document document;
				document = new XMLReaderWriter().parse(file);
				List Nodes = document
						.selectNodes("//IP_FILTER/IP_APPLICABLE/EXCEPTIONS");
				String str = "";
				final Object [][] loginDataObj = loginData();
				final String  loginEmpId = String.valueOf(loginDataObj[0][3]);
				for (Iterator<Element> itr = Nodes.iterator(); itr.hasNext();) {
					Element Exception = (Element) itr.next();
					str = String.valueOf(Exception.attributeValue("empIDs"));
					final String  str1[] = str.split(",");
					for (int i = 0; i < str1.length; i++) {
						if (loginEmpId.equals(str1[i])) {
							flag = true;
						}
					}
				}
			} catch (final Exception e) {

				logger.info("Exception in checkExceptionsLogin------" + e);
			}
		}
		return flag;
	}

	/**
	 * This method is used for checking Ip address for IP filtering
	 * 
	 * @param fileName
	 * @param request
	 * @param loginBean
	 * @return boolean
	 */
	public boolean checkValidIPAdd(String fileName, HttpServletRequest request,
			LoginBean loginBean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			  Object [][] result = null;
			final String  fName = fileName + "\\xml\\IPFilter\\ipfilter.xml";
			File file = new File(fName);
			Document document;
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE/TYPE");
			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				final String  ipType = String.valueOf(data.attributeValue("ipType"));
				if (ipType.equals("O")) {
					result = getDataFrmOrgXml(fileName, loginBean, request);
					long longIpAdd = 0;
					long frmIpAdd = 0;
					long toIpAdd = 0;
					final String  str = request.getRemoteAddr();
					final String  str1 = str.replace(".", "");
					longIpAdd = Long.parseLong(str1);
					if (result != null && result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							final String  fromIpXml = String.valueOf(result[i][0]);
							final String  fromIp = fromIpXml.replace(".", "");
							frmIpAdd = Long.parseLong(fromIp);
							final String  toIpXml = String.valueOf(result[i][1]);
							final String  toIp = toIpXml.replace(".", "");
							toIpAdd = Long.parseLong(toIp);
							if (longIpAdd >= frmIpAdd && longIpAdd <= toIpAdd) {
								flag = true;
							}
						}
					}
				}
				if (ipType.equals("B")) {
					final Object [][] brnData = getDataFrmBrnXml(fileName, loginBean,
							request);

					if (brnData != null && brnData.length > 0) {
						final String  remoteAdd = request.getRemoteAddr();
						final String  remoteAddIp = remoteAdd.replace(".", "");
						long remoteIp = Long.parseLong(remoteAddIp);
						for (int i = 0; i < brnData.length; i++) {
							final String  fromIpBrn = String.valueOf(brnData[i][0]);
							final String  frmIp = fromIpBrn.replace(".", "");
							long frmIpAddBrn = Long.parseLong(frmIp);
							final String  toIpBrn = String.valueOf(brnData[i][1]);
							final String  toIpBranch = toIpBrn.replace(".", "");
							long toIpAddBrn = Long.parseLong(toIpBranch);
							if (remoteIp >= frmIpAddBrn
									&& remoteIp <= toIpAddBrn) {
								flag = true;
							}
						}
					} else {
						flag = true;
					}

				}
			}
		} catch (final Exception e) {

			logger.info("Exception in checkValidIPAdd------" + e);
		}
		return flag;
	}

	/**
	 * This method is used for getting branch wise data for IP filter
	 * 
	 * @param fileName
	 * @param loginBean
	 * @param request
	 * @return Object[][]
	 */
	private Object[][] getDataFrmBrnXml(String fileName, LoginBean loginBean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		final Object [][] loginDataObj = loginData();
		final String  brnCode = String.valueOf(loginDataObj[0][2]);
		logger.info("brnCode--------------" + brnCode);
		final String  brnXmlFile = fileName + "\\xml\\IPFilter\\branch.xml";
		File file = new File(brnXmlFile);
		Object[][] objData = null;
		if (file.exists()) {
			Document document;
			try {
				document = new XMLReaderWriter().parse(file);
				List nodes = document.selectNodes("//BRANCH/ID[@brnCode='"
						+ brnCode + "']/IP");
				objData = new Object[nodes.size()][2];
				int cnt = 0;
				for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
					Element data = (Element) it.next();
					objData[cnt][0] = String.valueOf(data.element("FROM")
							.attributeValue("fromIpAdd"));
					objData[cnt][1] = String.valueOf(data.element("TO")
							.attributeValue("toIpAdd"));
					cnt++;
				}
			} catch (final Exception e) {

				logger.error("Exception in getDataFrmBrnXml----------" + e);
			}
		}
		return objData;
	}

	/**
	 * This method is used for getting organization wise data for IP filter.
	 * 
	 * @param fileName
	 * @param loginBean
	 * @param request
	 * @return Object[][]
	 */
	private Object[][] getDataFrmOrgXml(String fileName, LoginBean loginBean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		final String  orgXmlFile = fileName + "\\xml\\IPFilter\\organisation.xml";
		File file = new File(orgXmlFile);
		Object[][] objData = null;
		if (file.exists()) {
			Document document;
			try {
				document = new XMLReaderWriter().parse(file);
				List nodes = document.selectNodes("//ORGANISATION/IP");
				objData = new Object[nodes.size()][2];
				int cnt = 0;
				for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
					Element data = (Element) it.next();
					objData[cnt][0] = String.valueOf(data.element("FROM")
							.attributeValue("fromIpAdd"));
					objData[cnt][1] = String.valueOf(data.element("TO")
							.attributeValue("toIpAdd"));
					cnt++;
				}
			} catch (final Exception e) {

				logger.error("Exception in getDataFrmOrgXml----------" + e);
			}
		}
		return objData;
	}

	/**
	 * This method is used for checking IP filter applicability
	 * 
	 * @param fileName
	 * @param request
	 * @param loginBean
	 * @return boolean
	 */
	public boolean checkIsIpFilterApp(String fileName,
			HttpServletRequest request, LoginBean loginBean) {
		boolean flag = false;
		final String  fName = fileName + "\\xml\\IPFilter\\ipfilter.xml";
		File file = new File(fName);
		if (file.exists()) {
			try {
				Document document;
				document = new XMLReaderWriter().parse(file);
				List Nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE");
				for (Iterator<Element> it = Nodes.iterator(); it.hasNext();) {
					Element data = (Element) it.next();
					final String  applicable = String.valueOf(data
							.attributeValue("applicable"));
					if (applicable.equals("Y")) {
						flag = true;
					}
				}
			} catch (final Exception e) {
				logger.error("Exception in checkIsIpFilterApp----------" + e);
			}
		}
		return flag;
	}

	/**
	 * This method is used for checking Secure image authentication setting
	 * 
	 * @return
	 */
	public String checkForImageApp() {

		String result = "";
		try {
			final String  query = "SELECT PWD_ENABLE_TXTIMG FROM  HRMS_SETTINGS_PWD ";
			final Object [][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0
					&& !String.valueOf(dataObj[0][0]).equals("null")) {
				result = String.valueOf(dataObj[0][0]);
			}
		} catch (final Exception e) {

			logger.error("Exception in checkForImageApp----------" + e);
		}
		return result;
	}

	/**
	 * This method is used for checking security question enable or not.
	 * 
	 * @return String
	 */
	public String checkForChangePassApp() {
		String result = "";
		try {
			final String  query = " SELECT PWD_ENABLE_SECUREQUE FROM  HRMS_SETTINGS_PWD ";
			final Object [][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0
					&& !String.valueOf(dataObj[0][0]).equals("null")) {
				result = String.valueOf(dataObj[0][0]);
			}
		} catch (final Exception e) {
			logger.error("Exception in checkForChangePassApp-------" + e);
		}
		return result;
	}

	/**
	 * This method is used for getting company logo
	 * 
	 * @return Object[][]
	 */
	public Object[][] getComponyLogo() {
		// TODO Auto-generated method stub
		String companyLogo = "";
		final String  query = "SELECT COMPANY_LOGO,COMPANY_PPURL ,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME),COMPANY_NAME FROM  HRMS_COMPANY";
		final Object [][] logoquery = getSqlModel().getSingleResult(query);
		/*
		 * if (logoquery != null && logoquery.length > 0 &&
		 * !String.valueOf(logoquery[0][0]).equals("null")) { //companyLogo =
		 * String.valueOf(logoquery[0][0]); }
		 */
		return logoquery;
	}

	/*
	 * public Object[][] setMyTheme(LoginBean loginBean) { // TODO
	 * Auto-generated method stub String themeQuery = "SELECT
	 * LOGIN_THEME,LOGIN_FONT,LOGIN_FONTSIZE,NVL(MENU_LINK,'/common/HomePage_input.action'),LOGIN_DEF_HOME_CODE
	 * FROM HRMS_THEME " + " LEFT JOIN HRMS_MENU ON (HRMS_MENU.MENU_CODE =
	 * HRMS_THEME.LOGIN_DEF_HOME_CODE)" + " WHERE LOGIN_CODE= ? ";
	 * //logger.info("login Theme" + themeQuery); Object[][] theme =
	 * getSqlModel().getSingleResult(themeQuery, new
	 * Object[][]{{loginBean.getLoginCode()}}); return theme; }
	 */
	/**
	 * This method is for checking user first login
	 */
	public String checkIsUserFirstLogin(LoginBean loginBean) {
		// TODO Auto-generated method stub
		String result = "NO";
		Object loginDataObj[][] = null;
		final String  loginDataQuery = "SELECT LOGIN_AlREADYLOGGED FROM HRMS_LOGIN WHERE LOGIN_NAME=? ";
		loginDataObj = getSqlModel().getSingleResult(loginDataQuery,
				new Object[] { login.getLoginName() });
		if (loginDataObj != null && loginDataObj.length > 0
				&& !String.valueOf(loginDataObj[0][0]).equals("null")) {
			if (String.valueOf(loginDataObj[0][0]).equals("N")) {
				result = "YES";
			}
		}
		return result;
	}

	/**
	 * This method is used for setting user name and login code.
	 */
	public void setRecord() {
		try {
			final Object  loginData[][] = loginData();
			if (loginData != null && loginData.length > 0
					&& !String.valueOf(loginData[0][0]).equals("null")
					&& !String.valueOf(loginData[0][1]).equals("null")) {
				login.setUserName(String.valueOf(loginData[0][0]));
				login.setLoginCode(String.valueOf(loginData[0][1]));
				try {
					checkPassword();
				} catch (EncryptionException e) {
					// TODO Auto-generated catch block
					logger.error("Exception in setRecord checkPassword-------"
							+ e);
				}
			}
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in setRecord-------" + e);
		}
	}

	/**
	 * This method is used for setting user secured question
	 */
	public void setQuestion() {
		try {
			int cnt = 0;
			final String  questionQuery = "SELECT SECURITY_QUESCODE,SECURITY_QUESNAME FROM HRMS_SECURITY_QUESTIONS ORDER BY DBMS_RANDOM.VALUE ";
			final Object  questionObj[][] = getSqlModel().getSingleResult(
					questionQuery);
			TreeMap map_1 = new TreeMap();
			TreeMap map_2 = new TreeMap();
			TreeMap map_3 = new TreeMap();
			int length = questionObj.length / 3;
			if (questionObj != null && questionObj.length > 0) {
				for (int i = 0; i < questionObj.length; i++) {
					if (i < length) {
						map_1.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
					} else if (cnt < length) {
						map_2.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
						cnt++;
					} else {
						map_3.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
					}
				}
				login.setTmap(map_1);
				login.setQmap(map_2);
				login.setQuesmap(map_3);
			}
		} catch (final Exception e) {

			logger.error("Exception in setQuestion-----------" + e);
		}
	}

	/**
	 * This method is used for getting secure image details.
	 * 
	 * @return
	 */
	public boolean checkSetting() {
		boolean isCorrect = false;
		final String  chkUserQuery = " SELECT LOGIN_NAME,LOGIN_CODE,LOGIN_SECURE_IMG,LOGIN_SECURE_MSG FROM HRMS_LOGIN"
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOGIN.EMP_ID)"
				+ " WHERE LOGIN_NAME=? AND EMP_STATUS='S'";
		final Object [][] userData = getSqlModel().getSingleResult(chkUserQuery,
				new Object[] { login.getLoginName() });

		if (String.valueOf(userData[0][2]) != null
				&& !String.valueOf(userData[0][2]).equals("null")
				&& String.valueOf(userData[0][3]) != null
				&& !String.valueOf(userData[0][3]).equals("null")) {
			isCorrect = true;
		}
		return isCorrect;
	}

	/**
	 * This method is used for saving user secure message and image.
	 * 
	 * @param loginBean
	 * @return boolean
	 */
	public boolean saveUserSecureData(LoginBean loginBean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = false;
			String encryptedText = "";
			try {
				encryptedText = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
						.getUserText());
			} catch (EncryptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final String  updateQuery = "UPDATE hrms_login  SET  LOGIN_SECURE_IMG=?,LOGIN_SECURE_MSG=? WHERE LOGIN_CODE=?";

			flag = getSqlModel().singleExecute(
					updateQuery,
					new Object[][] { { loginBean.getImgName(), encryptedText,
							login.getLoginCode() } });
		} catch (final Exception e) {

			logger.error("Exception in saveUserSecureData----------------" + e);
		}
		return flag;
	}

	/**
	 * This method is used for getting user secure message and image.
	 * 
	 * @param loginBean
	 * @param request
	 */
	public void getUserSecureData(LoginBean loginBean,
			HttpServletRequest request) {
		try {
			Object secureData[][] = null;
			final String  selectSecureData = " SELECT LOGIN_SECURE_IMG,LOGIN_SECURE_MSG FROM HRMS_LOGIN  WHERE LOGIN_CODE= ? ";
			secureData = getSqlModel().getSingleResult(selectSecureData,
					new Object[] { login.getLoginCode() });
			String decryptedText = "";
			try {
				if (secureData != null && secureData.length > 0
						&& !String.valueOf(secureData[0][0]).equals("null")
						&& !String.valueOf(secureData[0][1]).equals("null")) {
					decryptedText = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.decrypt(String.valueOf(secureData[0][1]));
					loginBean.setUserMsg(decryptedText);
					final String  imgName = "../pages/common/css/default/images/"
							+ String.valueOf(secureData[0][0]) + ".jpg";
					request.setAttribute("imgName", imgName);
				}

			} catch (EncryptionException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in getUserSecureData 1--------------"
						+ e);
			}

		} catch (final Exception e) {

			logger.error("Exception in getUserSecureData 2--------------" + e);
		}
	}

	/**
	 * This method is used for getting user details from login table.
	 * 
	 * @return Object[][]
	 */
	public Object[][] loginData() {
		Object[][] loginDataObj = null;
		try {
			final String  loginDataQuery = "SELECT LOGIN_NAME,LOGIN_CODE ,HRMS_EMP_OFFC.EMP_CENTER,hrms_login.EMP_ID,LOGIN_AlREADYLOGGED FROM hrms_login "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=hrms_login.EMP_ID)"
					+ " WHERE LOGIN_NAME=? and EMP_STATUS='S'";
			loginDataObj = getSqlModel().getSingleResult(loginDataQuery,
					new Object[] { login.getLoginName() });
		} catch (final Exception e) {
			logger.error("Exception in loginData--------" + e);
		}
		return loginDataObj;
	}

	/**
	 * This method is used for saving changed password.
	 * 
	 * @return boolean
	 */
	public boolean saveChangePassword() {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			final String  newpsswd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
					.getNewpassword());
			logger.info("-----newpsswd------" + newpsswd);
			final String  psswd = " UPDATE hrms_login SET LOGIN_PASSWORD=? ,LOGIN_PWD_CHANGE_DATE=SYSDATE, LOGIN_ALREADYLOGGED='Y' WHERE   LOGIN_CODE= ? ";
			result = getSqlModel().singleExecute(psswd,
					new Object[][] { { newpsswd, login.getLoginCode() } });
			if (result) {
				final String  backUpQuery = " INSERT INTO HRMS_PASSWORD_HIST(LOGIN_CODE,OLD_PASSWORD,NEW_PASSWORD,LOGIN_CHANGEDATE) "
						+ " VALUES (?,?,?,SYSDATE) ";
				final Object  backUpObj[][] = new Object[1][3];
				backUpObj[0][0] = login.getLoginCode();
				backUpObj[0][1] = login.getOldpssword(); // old password
				backUpObj[0][2] = newpsswd; // new password with encrypt
				result = getSqlModel().singleExecute(backUpQuery, backUpObj);

			}
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			logger
					.error("Exception in saveChangePassword-----------------"
							+ e);
		}
		return result;
	}

	/**
	 * This method is used for getting user secured question answer.
	 * 
	 * @param request
	 * @return String
	 */
	public String getAnswerDetails(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String result = "";
		try {

			String checkAnsQuery = "";
			Object checkAnsObj[][] = null;

			if (Integer.parseInt(login.getAnswercnt()) <= 2) {
				checkAnsQuery = " SELECT SECURE_ANS FROM HRMS_SECURITY_ANSWER WHERE LOGIN_CODE=? AND QUES_CODE= ? ";
				checkAnsObj = getSqlModel().getSingleResult(
						checkAnsQuery,
						new Object[] { login.getLoginCode(),
								login.getForgotPassQueCode() });
				String checkAnsDb = "";

				try {
					if (checkAnsObj != null
							&& checkAnsObj.length > 0
							&& !String.valueOf(checkAnsObj[0][0])
									.equals("null")) {
						checkAnsDb = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.DEFAULT_ENCRYPTION_KEY)
								.decrypt(String.valueOf(checkAnsObj[0][0]));

						logger
								.info("answer of secure question is--------------"
										+ checkAnsDb);
					}

				} catch (final Exception e) {

					logger
							.error("Exception in getAnswerDetails 1-----------------"
									+ e);
				}
				String forgotQueAns = "";
				try {
					forgotQueAns = login.getForgotPassAns();

				} catch (final Exception e) {

					logger
							.error("Exception in getAnswerDetails 2----------------"
									+ e);
				}

				if (checkAnsDb.equals(forgotQueAns)) {
					result = "Yes";
				}

				else {
					result = "No";
					login.setAnswercnt(String.valueOf(Integer.parseInt(login
							.getAnswercnt()) + 1));
					login.setForgotPassAns("");
				}
			} else {
				result = "wrongans";
			}

		} catch (final Exception e) {

			logger.error("Exception in getAnswerDetails-----------------" + e);
		}

		return result;
	}

	/**
	 * This method is used for getting employee details from hrms_emp_offc
	 * table.
	 * 
	 * @param empID
	 * @return Object[][]
	 */
	public Object[][] setLoginInfo(String empID) {
		Object[][] objEmp = null;
		try {
			final String  loginString = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME,EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_PHOTO,'') ,EMP_GENDER FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID = ? ";
			objEmp = getSqlModel().getSingleResult(loginString,
					new Object[] { empID });

		} catch (final Exception e) {

		}
		return objEmp;
	}

	/**
	 * This method is used for getting user last access login time
	 * 
	 * @param empID
	 * @return Object[][]
	 */
	public Object[][] getLastLoginTime(String empID) {
		Object[][] lastLoginObj = null;
		try {
			/*
			 * String lastLoginQuery = " SELECT
			 * TO_CHAR(MAX(LOGIN_DATETIME-1),'DD-MM-YYYY HH:MI:SS') FROM
			 * hrms_login_SESSION " + " LEFT JOIN hrms_login
			 * ON(hrms_login.LOGIN_CODE = hrms_login_SESSION.LOGIN_ID) " + "
			 * WHERE EMP_ID = '" + empID + "' AND LOGIN_SUCCESS_FLAG='Y' " + "
			 * ORDER BY LOGIN_DATETIME DESC";
			 */

			final String  lastLoginQuery = " SELECT LOGIN_DATETIME ,TO_CHAR(LOGIN_DATETIME,'DD-MM-YYYY HH:MI:SS') FROM "
					+ " (SELECT LOGIN_DATETIME ,TO_CHAR(LOGIN_DATETIME,'DD-MM-YYYY HH:MI:SS') FROM "
					+ "		(SELECT LOGIN_DATETIME ,TO_CHAR(LOGIN_DATETIME,'DD-MM-YYYY HH:MI:SS') FROM HRMS_LOGIN_SESSION   "
					+ "	LEFT JOIN HRMS_LOGIN ON(HRMS_LOGIN.LOGIN_CODE = HRMS_LOGIN_SESSION.LOGIN_ID)  "
					+ "	WHERE EMP_ID = ? AND LOGIN_SUCCESS_FLAG='Y'  "
					+ "	ORDER BY LOGIN_DATETIME DESC) "
					+ " WHERE ROWNUM<=2 ORDER BY LOGIN_DATETIME ASC ) "
					+ " WHERE ROWNUM=1 ";

			lastLoginObj = getSqlModel().getSingleResult(lastLoginQuery,
					new Object[] { empID });

		} catch (final Exception e) {

		}
		return lastLoginObj;
	}

	/**
	 * This method is used for checking user password
	 * 
	 * @return boolean
	 * @throws EncryptionException
	 */
	public boolean checkUserPassword() throws EncryptionException {

		boolean isPass = false;
		try {
			final String  chkPassQuery = "SELECT LOGIN_PASSWORD  FROM hrms_login WHERE LOGIN_CODE= ?";
			final Object [][] chkPassData = getSqlModel().getSingleResult(
					chkPassQuery, new Object[] { login.getLoginCode() });
			String oldpass = "";
			try {
				if (chkPassData != null && chkPassData.length > 0
						&& !String.valueOf(chkPassData[0][0]).equals("null")) {
					oldpass = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.decrypt(String.valueOf(chkPassData[0][0]));
				}

			} catch (final Exception e) {

			}

			final String  newpass = login.getOldpssword();
			if (oldpass.equals(newpass)) {
				isPass = true;
			}

		} catch (final Exception e) {

		}

		return isPass;
	}

	/**
	 * This method is used for saving secure question and its answer given by
	 * the user.
	 * 
	 * @return boolean
	 */
	public boolean saveQuestion() {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			final String  secureAns1 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
					.getSecureAns1());
			final String  secureAns2 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
					.getSecureAns2());
			final String  secureAns3 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(login
					.getSecureAns3());
			final Object [][] data = new Object[3][3];
			data[0][0] = login.getLoginCode();
			data[0][1] = login.getSecureQue1();
			data[0][2] = secureAns1;
			data[1][0] = login.getLoginCode();
			data[1][1] = login.getSecureQue2();
			data[1][2] = secureAns2;
			data[2][0] = login.getLoginCode();
			data[2][1] = login.getSecureQue3();
			data[2][2] = secureAns3;
			final String  inserQuery = " INSERT INTO HRMS_SECURITY_ANSWER(LOGIN_CODE,QUES_CODE,SECURE_ANS) VALUES(?,?,?)";
			result = getSqlModel().singleExecute(inserQuery, data);
		} catch (final Exception e) {

		}
		return result;
	}

	/**
	 * This method is used for checking question configured or not by the user.
	 * 
	 * @return String
	 */
	public String checkQuesConfigure() {
		// TODO Auto-generated method stub
		String result = "";

		try {
			result = "";
			final String  query = " SELECT LOGIN_CODE, QUES_CODE, SECURE_ANS FROM HRMS_SECURITY_ANSWER "
					+ " WHERE  LOGIN_CODE= ? ";
			final Object [][] dataObj = getSqlModel().getSingleResult(query,
					new Object[] { login.getLoginCode() });
			if (dataObj != null && dataObj.length > 0
					&& !String.valueOf(dataObj[0][0]).equals("null")) {

				result = "Yes";
			} else {
				result = "No";
			}
		} catch (final Exception e) {

			logger.error("Exception in checkQuesConfigure-------" + e);
		}
		return result;
	}

	/**
	 * This method is used for getting user names for an employee.
	 */
	public void getUserNames() {
		// TODO Auto-generated method stub

		try {
			  String  query = "SELECT LOGIN_NAME "
					+ "  FROM HRMS_EMP_ADDRESS "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN hrms_login ON(hrms_login.EMP_ID=HRMS_EMP_OFFC.EMP_ID) ";
			if (!(login.getEmailIdfg().equals(""))
					&& !(login.getEmailIdfg() == null)
					&& !login.getEmailIdfg().equals("null")) {
				query += " WHERE ADD_EMAIL='" + login.getEmailIdfg() + "'";
			}
			if (!(login.getEmpIdfg().equals(""))
					&& !(login.getEmpIdfg() == null)
					&& !login.getEmpIdfg().equals("null")) {
				query += " WHERE EMP_TOKEN='" + login.getEmpIdfg() + "' ";
			}
			query += " AND ADD_TYPE='P'";
			final Object  obj[][] = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < obj.length; i++) {
					LoginBean bean = new LoginBean();
					bean.setUserNames(checkNull(String.valueOf(obj[i][0])));
					list.add(bean);
				}
				login.setList(list);
			}
		} catch (final Exception e) {

			logger.error("Exception in getUserNames-------" + e);
		}

	}

	/**
	 * This method is used for checking for null values if null then it returns
	 * blank .
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * This method is used for checking setting for forgot password question
	 * 
	 * @return
	 */
	public String checkSettingForForgotPassQues() {
		  String  str = "N";
		try {
			final String  query = " SELECT NVL(PWD_ENABLE_FORGOT_QUES,'N') FROM HRMS_SETTINGS_PWD ";
			final Object [][] result = getSqlModel().getSingleResult(query);
			if (result != null && result.length > 0) {
				str = String.valueOf(result[0][0]);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			str = "N";
		}
		return str;
	}

	/**
	 * This method is used for setting company name and logo on home page.
	 * 
	 * @param request
	 */
	public void setCompanyNameAndLogo(HttpServletRequest request) {
		try {

			String comanyName = null;
			String logo = null;
			Object data[][] = null;
			try {
				final String  query = "SELECT COMPANY_LOGO,COMPANY_PPURL ,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME),COMPANY_NAME FROM  HRMS_COMPANY";
				data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					logo = String.valueOf(data[0][0]);
					if(request.getLocalAddr().equals("192.168.25.211")){
						comanyName = "Decision One";						
					}else{
						comanyName = String.valueOf(data[0][2]);
					}
				}
			} catch (final Exception e) {
				logger.error("Exception in getComponyLogo--------" + e);
				e.printStackTrace();
			}
			request.setAttribute("comanyName", comanyName);
			request.setAttribute("logo", logo);
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * This method is used for getting name of employee division.
	 * 
	 * @param empCode
	 * @return String
	 */
	public String getEmployeeDivisionName(String empCode) {
		String empDivisionName = "";
		try {
			final String  query = " select NVL(DIV_DISPLAY_NAME,DIV_NAME) from hrms_division where div_id=(select emp_div from hrms_emp_offc where emp_id="
					+ empCode + ")";
			final Object [][] empDivNameObj = getSqlModel().getSingleResult(query);
			if (empDivNameObj != null && empDivNameObj.length > 0) {
				empDivisionName = String.valueOf(empDivNameObj[0][0]);
			}
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return empDivisionName;
	}

	/**This method is used for crm client login.
	 * @param loginBean : loginBean
	 * @param request
	 * @return
	 */
	public String getMultipleLogin(LoginBean loginBean, HttpServletRequest request) {
		String result = "";
		try {
			String empLoginId = loginBean.getLoginName().trim();
			String queryName = "SELECT EMAIL_ID  FROM CR_CLIENT_USERS WHERE EMAIL_ID='"+empLoginId+"' ";
			Object [][] data1 = getSqlModel().getSingleResult(queryName);
			if(data1!=null && data1.length>0){
				result=String.valueOf(data1.length);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getMultipleLoginforDashBoard(LoginBean loginBean, HttpServletRequest request) {
		String result = "";
		try {
			HttpSession session = request.getSession();
			String empLoginId = loginBean.getCustomerUserEmpId().trim();
		//	HttpSession session = request.getSession();
			//String emp_id = (String)session.getAttribute("customerUserEmpIdSession");
			String queryName = "select count(distinct DashBoard_id) from cr_emp_client_mapp " +
					"where user_type='E' " +
					"and user_id="+empLoginId;
			Object [][] data1 = getSqlModel().getSingleResult(queryName);
			if(data1!=null && data1.length>0){
				result=String.valueOf(data1[0][0]);
				
			}
			String userType="E";
			session.setAttribute("noOfDashboard", result);
			session.setAttribute("userType", userType);
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	/**This method is used for crm client login validation.
	 * @param loginBean : loginBean
	 * @param request
	 * @return
	 */
	public String submitnewuser(LoginBean loginBean) {
		String result = "";
		String encryptPwd = "";
		Object data[][] = null;
		Object pwdData[][] = null;
		try {
			Object[] loginName = new Object[1];
			loginName[0] = loginBean.getLoginName().trim();
			String queryName = "SELECT CRUSER_CODE, FIRST_NAME, LAST_NAME, EMAIL_ID, PASSWORD, CRUSER_CODE,NVL(IS_ACTIVE,'N'),ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE EMAIL_ID= ? ";
			data = getSqlModel().getSingleResult(queryName, loginName);
			if (data == null) {
				result = "1";
				return result;
			} else if (data.length == 0) {
				result = "1";
				return result;
			} else if (data!=null && data.length>0&&String.valueOf(data[0][6]).equals("N")) {
				result = "3";
				return result;
			} else {
				if (loginBean.getLoginPassword().trim().equals("")) {
					result = "2";
					return result;
				} else {
					encryptPwd = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.encrypt(loginBean.getLoginPassword().trim());
					Object[] encryptedPassword = new Object[2];
					encryptedPassword[0] = encryptPwd;
					encryptedPassword[1] = loginBean.getLoginName().trim();
					/*
					 * encryptPwd = new StringEncrypter(
					 * StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					 * StringEncrypter.URL_ENCRYPTION_KEY).decrypt(String.valueOf(data[0][5]));
					 */
		
					String pwdQuery = "SELECT * FROM CR_CLIENT_USERS where PASSWORD=? AND EMAIL_ID=? ";
					pwdData = getSqlModel().getSingleResult(pwdQuery,
							encryptedPassword);
					
					if (pwdData == null) {
						result = "2";
						return result;
					} else if (pwdData.length == 0) {
						result = "2";
						return result;
					}else if (pwdData.length == 1) {
						result = "";
						loginBean.setLoginName(String.valueOf(pwdData[0][4]));
						/*loginBean.setCustomerCode(String.valueOf(data[0][0]));
						loginBean.setAccountCode(String.valueOf(data[0][7]));*/
						loginBean.setCustomerUserEmpId(String.valueOf(pwdData[0][0]));
						return result;
					}else if (String.valueOf(pwdData) != null && pwdData.length > 0) {
						result = "4";
						loginBean.setLoginName(String.valueOf(pwdData[0][4]));
						loginBean.setCustomerUserEmpId(String.valueOf(pwdData[0][0]));
						return result;
					}
					//loginBean.setCustomerCode(String.valueOf(data[0][5]));
				}
			}
			// Check for password BEGINS
			if (encryptPwd.equals(String.valueOf(data[0][4]))) {
				result = "";
				return result;
			} else {
				result = "2";
				return result;
			}
			
			// Check for password ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		}

	public Object[][] setMyTheme(LoginBean loginBean) {
		// TODO Auto-generated method stub
		return null;
	}
}
