/**
 * 
 */
package org.paradyne.model.common;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.common.ChangePsswdBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.StringEncrypter.EncryptionException;


/**
 * @author MuzaffarS
 * 
 */
public class ChangePsswdModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * Setting name and id of employee
	 * 
	 * @param bean
	 */
	public void changePsswd(ChangePsswdBean bean) {
		try {
			String emp_id = bean.getUserEmpId();
			String emp = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||"
					+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="
					+ emp_id;

			Object arg[][] = getSqlModel().getSingleResult(emp);
			bean.setEmpnm(checkNull(String.valueOf(arg[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(arg[0][0])));
			bean.setEmp_id(String.valueOf(emp_id));
		} catch (Exception e) {
			logger.error("Exception in my config password change " + e);
		}
	}

	/**
	 * TO check password re-usability To check after how many attempts a
	 * password can be reused
	 * 
	 * @param bean
	 * @param request
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkReusePassword(ChangePsswdBean bean,
			HttpServletRequest request) throws Exception {
		try {
			String reuseQuery = " SELECT PWD_REUSE_FLAG,PWD_REUSE_PERIODICITY FROM HRMS_SETTINGS_PWD ";
			Object[][] reuseFlag = getSqlModel().getSingleResult(reuseQuery);
			if (reuseFlag != null) {
				logger
						.info("Reuse Flag Status--------------------------------- "
								+ reuseFlag[0][0]);
				if (reuseFlag.length > 0
						&& String.valueOf(reuseFlag[0][0])
								.equalsIgnoreCase("Y")) {
					/* Setting reuse count */
					int reuseCount = Integer.parseInt(String
							.valueOf(reuseFlag[0][1]));
					request.setAttribute("reusePeriodicity", reuseCount);
					String psswdHistoryQuery = "SELECT NEW_PASSWORD FROM HRMS_PASSWORD_HIST WHERE LOGIN_CODE="
							+ bean.getUserLoginCode()
							+ " ORDER BY LOGIN_CHANGEDATE DESC";
					Object[][] psswdHistory = getSqlModel().getSingleResult(
							psswdHistoryQuery);
					/* Comparing with password history */
					if (psswdHistory != null & psswdHistory.length > 0) {
						for (int i = 0; i < reuseCount; i++) {
							String newEncriptPwd = new StringEncrypter(
									StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
									.encrypt(bean.getNewpsswd1());
							if ((newEncriptPwd.equals(String
									.valueOf(psswdHistory[i][0])))) {
								logger.info("password is repeating....!!  "
										+ psswdHistory[i][0] + "----- "
										+ newEncriptPwd);
								return false;
							}// END nested if (password history)
						}
					}// END nested if (password history)
				}// END nested if length>0 (reuse flag)
			}// END if reuse flag
			return true;
		} catch (Exception e) {
			logger.error("Exception Caught in Checking Password Reuse: " + e);
			return true;
		}
	}

	/**
	 * To save the password
	 * 
	 * @param bean
	 * @throws EncryptionException
	 */
	public void savePsswd(ChangePsswdBean bean) throws EncryptionException {
		try {
			String passQuery = " SELECT LOGIN_PASSWORD FROM HRMS_LOGIN WHERE EMP_ID= "
					+ bean.getUserEmpId();
					//+ " and LOGIN_NAME = '";
					//+ bean.getChatLogin() + "'";
			Object[][] resultPass = getSqlModel().getSingleResult(passQuery);
			String log_code = bean.getUserLoginCode();
			String emp_id = bean.getEmp_id();
			String newpsswd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
					.getNewpsswd1());
			logger.info(" into new  psswd stat and new pswdPPPPPPPPPPPPPPPPPP:"
					+ newpsswd);
			// insert
			String backUpQuery = " INSERT INTO HRMS_PASSWORD_HIST(LOGIN_CODE,OLD_PASSWORD,NEW_PASSWORD,LOGIN_CHANGEDATE) "
					+ " VALUES (?,?,?,SYSDATE) ";
			Object backUpObj[][] = new Object[1][3];
			backUpObj[0][0] = log_code;
			backUpObj[0][1] = String.valueOf(resultPass[0][0]); // old password
			backUpObj[0][2] = newpsswd; // new password with encrypt
			getSqlModel().singleExecute(backUpQuery, backUpObj);
			// update
			String psswd = " UPDATE HRMS_LOGIN SET LOGIN_PASSWORD='" + newpsswd
					+ "' WHERE EMP_ID=" + emp_id + " AND  LOGIN_CODE="
					+ log_code;
			getSqlModel().singleExecute(psswd);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in savePsswd----------- " + e);
		}
	}

	
	/**
	 * Save or Update Email password
	 * 
	 * @param bean
	 * @return boolean (insert or update)
	 * @throws EncryptionException
	 */
	public boolean saveMailPassword(ChangePsswdBean bean)
			throws EncryptionException {
		logger.info("in side model");
		boolean result;
		Object[][] insertData = new Object[1][3];
		insertData[0][0] = bean.getEmp_id();
		insertData[0][1] = bean.getUserName();
		insertData[0][2] = new StringEncrypter(
				StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
				.getOldPass());
		// String confPass = bean.getConfPass();
		logger.info("emp id---------" + insertData[0][0]);
		logger.info("username" + insertData[0][1]);
		logger.info("password" + bean.getOldPass());
		String chekQuery = "SELECT EMPMAIL_USERID, EMPMAIL_PASSWORD FROM HRMS_SETTINGS_EMPMAIL WHERE EMP_ID = "
				+ insertData[0][0];
		Object passData[][] = getSqlModel().getSingleResult(chekQuery);
		logger.info("selected data---------" + passData.length);
		// insert if no data is present
		if (passData.length == 0) {
			String insertQuery = "INSERT INTO HRMS_SETTINGS_EMPMAIL (EMP_ID, EMPMAIL_USERID, EMPMAIL_PASSWORD) "
					+ "VALUES(?, ?, ?)";
			result = getSqlModel().singleExecute(insertQuery, insertData);
			logger.info("result-------------" + result);
		}// END if
		else {
			// update
			Object[][] updateData = new Object[1][3];
			updateData[0][2] = bean.getEmp_id();
			updateData[0][0] = bean.getUserName();
			updateData[0][1] = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
					.getOldPass());
			String updateQuery = "UPDATE HRMS_SETTINGS_EMPMAIL SET EMPMAIL_USERID=?, EMPMAIL_PASSWORD=? WHERE EMP_ID=?";
			result = getSqlModel().singleExecute(updateQuery, updateData);
		}// END else
		return result;
	}

	/**
	 * Setting password (old password)
	 * 
	 * @param bean
	 */
	public void getPassword(ChangePsswdBean bean) {

		try {
			String emp_id = bean.getUserEmpId();
			// Email user id & password
			String query = "SELECT NVL(EMPMAIL_USERID,''), NVL(EMPMAIL_PASSWORD,'') FROM HRMS_SETTINGS_EMPMAIL WHERE EMP_ID = "
					+ emp_id;
			Object[][] result = getSqlModel().getSingleResult(query);
			if (result != null & result.length > 0) {
				// sets user name and old password
				bean.setUserName(String.valueOf(result[0][0]));
				bean.setOldPass(String.valueOf(result[0][1]));
			}// END if
		} catch (Exception e) {
			logger.error("Exception in my config password change " + e);
		}
	}

	/**
	 * Setting password Password used by employee to login to HrWorK
	 * 
	 * @param bean
	 */
	public void setLoginPassword(ChangePsswdBean bean) {
		try {
			// HrWork Login
			String passQuery = " SELECT LOGIN_PASSWORD FROM HRMS_LOGIN WHERE EMP_ID= "
					+ bean.getUserEmpId();
					//+ " and LOGIN_NAME = '"
					//+ bean.getChatLogin() + "'";
			Object[][] resultPass = getSqlModel().getSingleResult(passQuery);
			String dPass = "";
			// setting password
			dPass = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DEFAULT_ENCRYPTION_KEY).decrypt(String
					.valueOf(resultPass[0][0]));
			bean.setPssword(dPass);
		} catch (Exception e) {
			logger.error("Exception in while setting password for my config "
					+ e);
		}
	}

	/**
	 * Retrieving Length of password
	 * 
	 * @param request
	 */
	public void getPasswordLength(HttpServletRequest request) { // from filters
																// (Password
																// Settings)
		String query = "SELECT NVL(PWD_MIN_LENGTH,'0'), NVL(PWD_MAX_LENGTH,'0'),PWD_INCLUDE_ALPHA,"
				+ "PWD_INCLUDE_SPCHAR,PWD_INCLUDE_NUM,PWD_INCLUDE_UPCASE FROM HRMS_SETTINGS_PWD";
		Object[][] lengthObj = getSqlModel().getSingleResult(query);
		if (lengthObj != null & lengthObj.length > 0) { //setting length
			request.setAttribute("passInfoObj", lengthObj);
		}//END if
	}

	public boolean saveQuestion(ChangePsswdBean bean) {
		// TODO Auto-generated method stub
		logger.info("bean.getUserLoginCode()-------------"+bean.getUserLoginCode());
		boolean res=false;
		try {
			String delQuery = "DELETE FROM  HRMS_SECURITY_ANSWER WHERE LOGIN_CODE="
					+ bean.getUserLoginCode();
			 res = getSqlModel().singleExecute(delQuery);
			if (res) {
				String secureAns1 = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getSecureAns1());
				String secureAns2 = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getSecureAns2());
				String secureAns3 = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getSecureAns3());
				Object[][] data = new Object[3][3];
				data[0][0] = bean.getUserLoginCode();
				data[0][1] = bean.getSecureQue1();
				data[0][2] = secureAns1;
				data[1][0] = bean.getUserLoginCode();
				data[1][1] = bean.getSecureQue2();
				data[1][2] = secureAns2;
				data[2][0] = bean.getUserLoginCode();
				data[2][1] = bean.getSecureQue3();
				data[2][2] = secureAns3;
				String inserQuery = " INSERT INTO HRMS_SECURITY_ANSWER(LOGIN_CODE,QUES_CODE,SECURE_ANS) VALUES(?,?,?)";
				res = getSqlModel().singleExecute(inserQuery, data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in saveQuestion---------------"+e);
		}
		return res;
	}

	public boolean saveImage(ChangePsswdBean bean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = false;
			String encryptedText = "";
			try {
				encryptedText = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getUserText());
			} catch (EncryptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String updateQuery = "UPDATE HRMS_LOGIN  SET  LOGIN_SECURE_IMG='"
					+ bean.getImgName() + "',LOGIN_SECURE_MSG='"
					+ encryptedText + "' WHERE LOGIN_CODE="
					+ bean.getUserLoginCode();
			System.out.println("updateQuery------------------------"
					+ updateQuery);
			flag = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			logger.info("Exception in saveImage---------------"+e);
		}
		return flag;
	}

	
	public void getImage(ChangePsswdBean bean,HttpServletRequest request) {
		try {
			Object secureData[][] = null;
			String selectSecureData = " SELECT LOGIN_SECURE_IMG,LOGIN_SECURE_MSG FROM HRMS_LOGIN  WHERE LOGIN_CODE="
					+ bean.getUserLoginCode();
			secureData = getSqlModel().getSingleResult(selectSecureData);
			
			if(secureData!=null && secureData.length>0 && !String.valueOf(secureData[0][1]).equals("null"))
			{
				String decryptedText = "";
				try {
					decryptedText = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.decrypt(String.valueOf(secureData[0][1]));
				} catch (EncryptionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bean.setUserText(decryptedText);
				String imgName = "../pages/common/css/default/images/"
					+ String.valueOf(secureData[0][0]) + ".jpg";
				request.setAttribute("imgName", imgName);
				bean.setIsImage("true");
			}
			
			
		} catch (Exception e) {
			logger.info("Exception in getImage---------------"+e);
		}
	}

	public void setHomeTab(ChangePsswdBean bean) {
		
		String menuQry = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),MENU_TABORDER,HRMS_MENU.MENU_CODE   "
			+ " FROM HRMS_MENU "
			+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
			+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
			+ " OR PROFILE_GENERAL_FLAG ='Y'))"
			+" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
			+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ='"
			+ bean.getUserProfileId()
			+ "'"
			+ " AND MENU_PARENT_CODE=0 AND MENU_ISRELEASED='Y'"
			+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";
		Object[][] menuObj = getSqlModel().getSingleResult(menuQry);
		LinkedHashMap lMap = new LinkedHashMap();
		if(menuObj!= null && menuObj.length > 0)
		{
			for (int i = 0; i < menuObj.length; i++) 
				lMap.put(String.valueOf(menuObj[i][0]), String.valueOf(menuObj[i][1]));
			bean.setHomeTab(lMap);
		}
		String homeTabQry = " SELECT LOGIN_DEF_HOME_CODE FROM HRMS_THEME WHERE LOGIN_CODE = "+bean.getUserLoginCode();
		Object[][] homeTabObj = getSqlModel().getSingleResult(homeTabQry);
		if(homeTabObj!= null && homeTabObj.length > 0)
			bean.setSelHomeTab(String.valueOf(homeTabObj[0][0]));
	}
	
	public void saveHomeTab(String homeCode, String loginCode)
	{
		String chkTheme = "SELECT * FROM HRMS_THEME WHERE LOGIN_CODE = "+loginCode;
		Object[][] chkObj = getSqlModel().getSingleResult(chkTheme);
		String setHomeQry = "";
		if(chkObj != null && chkObj.length > 0)
			setHomeQry = "UPDATE HRMS_THEME SET LOGIN_DEF_HOME_CODE = "+homeCode+" WHERE LOGIN_CODE = "+loginCode;
		else
			setHomeQry = "INSERT INTO HRMS_THEME (LOGIN_CODE,LOGIN_DEF_HOME_CODE) VALUES("+loginCode+","+homeCode+")";
		getSqlModel().singleExecute(setHomeQry);
	}
	
	/**
	 * CONFIGURE EMAIL ACCOUNT
	 */
	
	public String getAccountData(ChangePsswdBean congEmailAcc) {
		// TODO Auto-generated method stub
		String result = "";
		String query = "SELECT NVL(EMAIL_ACCOUNT_NAME,' '),NVL(EMAIL_USER_NAME,' '),NVL(DOMAIN_NAME,' '),EMAIL_ACCOUNT_CODE,DECODE(EMAIL_OFFICIAL_FLAG,'N','No','Yes') "
				+ " , SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS,SERVER_OUT_IP, SERVER_OUT_PORT "
				+ "	FROM HRMS_EMAIL_ACCOUNT "
				+ "	INNER JOIN HRMS_EMAIL_SERVER ON (HRMS_EMAIL_SERVER.SERVER_CODE=HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE) "
				+ "	WHERE EMAIL_EMP_ID = "
				+ congEmailAcc.getUserEmpId()
				+ " ORDER BY EMAIL_ACCOUNT_CODE ";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				ChangePsswdBean bean = new ChangePsswdBean();
				bean.setIttAccountName(String.valueOf(data[i][0]));
				bean.setIttUserName(String.valueOf(data[i][1]));
				bean.setIttServerName(String.valueOf(data[i][2]));
				bean.setIttHiddenCode(String.valueOf(data[i][3]));
				bean.setIttOffcicialCheckBox(String.valueOf(data[i][4]));				
				list.add(bean);
			}
			congEmailAcc.setList(list);

		} else {
			congEmailAcc.setList(null);
		}

		return result;
	}
	public boolean editEmailAccount(ChangePsswdBean congEmailAcc) {

		String query = "SELECT NVL(EMP_TOKEN,' '),EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME AS NAME FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ congEmailAcc.getUserEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);
		congEmailAcc.setEmpToken(String.valueOf(data[0][0]));
		congEmailAcc.setEmpName(String.valueOf(data[0][1]));

		String selQuery = "SELECT NVL(EMAIL_ACCOUNT_NAME,' '),NVL(EMAIL_USER_NAME,' '),NVL(EMAIL_USER_PASS,' '),HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE,EMAIL_OFFICIAL_FLAG "
				+ "	FROM HRMS_EMAIL_ACCOUNT "
				+ "	LEFT JOIN HRMS_EMAIL_SERVER ON (HRMS_EMAIL_SERVER.SERVER_CODE=HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE) "
				+ "	WHERE EMAIL_EMP_ID = "
				+ congEmailAcc.getUserEmpId()
				+ " AND EMAIL_ACCOUNT_CODE="
				+ congEmailAcc.getHiddenCode()
				+ " ORDER BY EMAIL_ACCOUNT_CODE ";

		Object[][] accData = getSqlModel().getSingleResult(selQuery);
		if (accData != null && accData.length > 0) {
			congEmailAcc.setAccountName(String.valueOf(accData[0][0]));
			congEmailAcc.setUserName(String.valueOf(accData[0][1]));
			congEmailAcc.setUserPassword("");
			congEmailAcc.setServerList(String.valueOf(accData[0][3]));
			congEmailAcc.setOfficialMailCheck("false");
			if (String.valueOf(accData[0][4]).equals("Y")) {
				congEmailAcc.setOfficialMailCheck("true");
			}
		}

		// congEmailAcc.setHiddenCode("");
		setServerData(congEmailAcc);
		return true;
	}
	public void setServerData(ChangePsswdBean congEmailAcc) {
		// TODO Auto-generated method stub
		String query = "SELECT DOMAIN_NAME, SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE, SERVER_OUT_IP, SERVER_OUT_PORT "
				+ " FROM HRMS_EMAIL_SERVER WHERE SERVER_CODE="
				+ congEmailAcc.getServerList();
		Object[][] serverData = getSqlModel().getSingleResult(query);
		congEmailAcc.setChkFlag("false");
		congEmailAcc.setChkDisableFlag("true");
		if (serverData != null && serverData.length > 0) {
			congEmailAcc.setChkFlag("true");
			congEmailAcc.setServerName(checkNull(String
					.valueOf(serverData[0][0])));
			congEmailAcc.setInServerIP(checkNull(String
					.valueOf(serverData[0][1])));
			congEmailAcc.setInServerPort(checkNull(String
					.valueOf(serverData[0][2])));
			congEmailAcc.setServerType(checkNull(String
					.valueOf(serverData[0][3])));
			congEmailAcc.setOutServerIP(checkNull(String
					.valueOf(serverData[0][4])));
			congEmailAcc.setOutServerPort(checkNull(String
					.valueOf(serverData[0][5])));
		}

		if (congEmailAcc.getServerList().equals("O")) {
			congEmailAcc.setChkDisableFlag("false");
			congEmailAcc.setChkFlag("true");
			congEmailAcc.setServerName("");
			congEmailAcc.setInServerIP("");
			congEmailAcc.setInServerPort("");
			congEmailAcc.setServerType("");
			congEmailAcc.setOutServerIP("");
			congEmailAcc.setOutServerPort("");
		}

	}
	public boolean deletemailAccount(ChangePsswdBean congEmailAcc) {
		String query = "DELETE FROM HRMS_EMAIL_ACCOUNT WHERE EMAIL_ACCOUNT_CODE IN("
				+ congEmailAcc.getHiddenCode() + ")";
		boolean result = getSqlModel().singleExecute(query);
		congEmailAcc.setHiddenCode("");
		return result;
	}
	
	public Boolean saveEmailAccount(ChangePsswdBean congEmailAcc) {
		boolean flag = false;
		/**
		 * CHECK FOR SERVER TYPE OTHER INSERT ONE ENTRY INTO HRMS_EMAIL_SERVER
		 */
		boolean result = true;
		if (congEmailAcc.getServerList().equals("O")) {
			String query = "SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER";
			Object[][] maxSerCode = getSqlModel().getSingleResult(query);
			if (maxSerCode != null && maxSerCode.length > 0) {
				congEmailAcc.setServerList(String.valueOf(maxSerCode[0][0]));
			}
		 
			Object[][] setData = new Object[1][8];
			setData[0][0] = congEmailAcc.getServerList();
			setData[0][1] = congEmailAcc.getServerName();
			setData[0][2] = congEmailAcc.getInServerIP();
			setData[0][3] = congEmailAcc.getInServerPort();
			setData[0][4] = congEmailAcc.getServerType();
			setData[0][5] = congEmailAcc.getOutServerIP();
			setData[0][6] = congEmailAcc.getOutServerPort();
			setData[0][7] = congEmailAcc.getUserEmpId();
			String insQuery = "INSERT INTO HRMS_EMAIL_SERVER(SERVER_CODE, DOMAIN_NAME, SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE, SERVER_OUT_IP, SERVER_OUT_PORT ,SERVER_RECORD_EMP)"
					+ " VALUES(?,?,?,?,?,?,?,?)  ";
			result = getSqlModel().singleExecute(insQuery, setData);
		}
		/*String pass="";
		try {
			pass = PPEncrypter.encryptEmailUser(congEmailAcc.getUserPassword());			
		} catch (Exception e) {			
			e.printStackTrace();
		}*/
		Object[][] data = new Object[1][6];
		data[0][0] = congEmailAcc.getUserEmpId();
		data[0][1] = congEmailAcc.getAccountName();
		data[0][2] = congEmailAcc.getUserName();
		data[0][3] = congEmailAcc.getUserPassword();
		data[0][4] = congEmailAcc.getServerList();//
		data[0][5] = "N";
		if (congEmailAcc.getOfficialMailCheck().equals("true")) {
			data[0][5] = "Y";
		}
		String insert = "INSERT INTO HRMS_EMAIL_ACCOUNT(EMAIL_ACCOUNT_CODE, EMAIL_EMP_ID, EMAIL_ACCOUNT_NAME, EMAIL_USER_NAME, EMAIL_USER_PASS, EMAIL_SERVER_CODE,EMAIL_OFFICIAL_FLAG)"
				+ "  VALUES((SELECT NVL(MAX(EMAIL_ACCOUNT_CODE),0)+1 FROM HRMS_EMAIL_ACCOUNT),?,?,?,?,?,?) ";
		if (true) {
			flag = getSqlModel().singleExecute(insert, data);
		}
		return flag;
	}

	public Boolean updateEmailAccount(ChangePsswdBean congEmailAcc) {
		boolean flag = false;
		/**
		 * CHECK FOR SERVER TYPE OTHER INSERT ONE ENTRY INTO HRMS_EMAIL_SERVER
		 */
		boolean result = true;
		if (congEmailAcc.getServerList().equals("O")) {
			String query = "SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER";
			Object[][] maxSerCode = getSqlModel().getSingleResult(query);
			if (maxSerCode != null && maxSerCode.length > 0) {
				congEmailAcc.setServerList(String.valueOf(maxSerCode[0][0]));
			}
			Object[][] setData = new Object[1][8];
			setData[0][0] = congEmailAcc.getServerList();
			setData[0][1] = congEmailAcc.getServerName();
			setData[0][2] = congEmailAcc.getInServerIP();
			setData[0][3] = congEmailAcc.getInServerPort();
			setData[0][4] = congEmailAcc.getServerType();
			setData[0][5] = congEmailAcc.getOutServerIP();
			setData[0][6] = congEmailAcc.getOutServerPort();
			setData[0][7] = congEmailAcc.getUserEmpId();
			String insQuery = "INSERT INTO HRMS_EMAIL_SERVER(SERVER_CODE, DOMAIN_NAME, SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE, SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_RECORD_EMP )"
					+ " VALUES(?,?,?,?,?,?,?,?)  ";
			result = getSqlModel().singleExecute(insQuery, setData);
		}
		/*String pass="";
		try {
			pass = PPEncrypter.encryptEmailUser(congEmailAcc.getUserPassword());			
		} catch (Exception e) {			
			e.printStackTrace();
		}*/
		Object[][] data = new Object[1][7];

		data[0][0] = congEmailAcc.getAccountName();
		data[0][1] = congEmailAcc.getUserName();
		data[0][2] = congEmailAcc.getUserPassword();
		data[0][3] = congEmailAcc.getServerList();//
		data[0][4] = "N";
		System.out.println("congEmailAcc.getOfficialMailCheck()  :   "
				+ congEmailAcc.getOfficialMailCheck());
		if (congEmailAcc.getOfficialMailCheck().equals("true")) {
			data[0][4] = "Y";
			System.out.println("congEmailAcc.getOfficialMailCheck()  :   "
					+ congEmailAcc.getOfficialMailCheck());
		}

		data[0][5] = congEmailAcc.getHiddenCode();
		data[0][6] = congEmailAcc.getUserEmpId();

		String update = "UPDATE HRMS_EMAIL_ACCOUNT SET  EMAIL_ACCOUNT_NAME=?, EMAIL_USER_NAME=?, EMAIL_USER_PASS=?, EMAIL_SERVER_CODE=?,EMAIL_OFFICIAL_FLAG=? WHERE EMAIL_ACCOUNT_CODE=? AND EMAIL_EMP_ID=?";

		if (true) {
			flag = getSqlModel().singleExecute(update, data);
		}
		return flag;
	}

	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * To save the password
	 * 
	 * @param bean
	 * @throws EncryptionException
	 */
	public boolean saveAuthorizationPsswd(ChangePsswdBean bean) throws EncryptionException {
		
		boolean res= false;
		try {
		
			String password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
					.getAuthorizationPassword());
			logger.info(" into new  psswd stat and new :"
					+ password);
			String emp_id = bean.getEmp_id();
			logger.info("emp_id    "+emp_id);
		String delQuery =" DELETE FROM HRMS_AUTHORIZATION_CONFIG WHERE AUTH_EMP_ID="+emp_id;
		res =getSqlModel().singleExecute(delQuery);
		if(res)
		{
			String insertQuery = " INSERT INTO HRMS_AUTHORIZATION_CONFIG(AUTH_PASSWORD, AUTH_EMP_ID)VALUES('"+password+"',"+emp_id+") ";
			 
			res =getSqlModel().singleExecute(insertQuery);
		}
			 
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in savePsswd----------- " + e);
		}
		return res;
	}

	 

}
