package org.paradyne.model.email;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.email.CongEmailAccount;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;

public class CongEmailAccountModel extends ModelBase {

	public String getAccountData(CongEmailAccount congEmailAcc) {
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
			result="accountsAdded";
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				CongEmailAccount bean = new CongEmailAccount();
				bean.setIttAccountName(String.valueOf(data[i][0]));
				bean.setIttUserName(String.valueOf(data[i][1]));
				bean.setIttServerName(String.valueOf(data[i][2]));
				bean.setIttHiddenCode(String.valueOf(data[i][3]));
				bean.setIttOffcicialCheckBox(String.valueOf(data[i][4]));
				// SET PARAMETER FOR LOGIN
				bean.setServer(String.valueOf(data[i][5]));
				bean.setPort(String.valueOf(data[i][6]));
				bean.setProtocol(String.valueOf(data[i][7]));
				bean.setImapuser(String.valueOf(data[i][8]));
				bean.setPass(PPEncrypter.decryptEmailUser(String.valueOf(data[i][9])));
				System.out.println("bean.setHPass(....."+bean.getHPass());
				bean.setSmtphost(String.valueOf(data[i][10]));
				bean.setSmtpport(String.valueOf(data[i][11]));
				
				
				list.add(bean);
			}

			congEmailAcc.setList(list);

		} else {
			congEmailAcc.setList(null);
		}

		return result;
	}

	public void setServerData(CongEmailAccount congEmailAcc) {
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

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public Boolean saveEmailAccount(CongEmailAccount congEmailAcc) {
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

		
		String pass="";
		try {
			pass = PPEncrypter.encryptEmailUser(congEmailAcc.getUserPassword());			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		
		Object[][] data = new Object[1][6];
		data[0][0] = congEmailAcc.getUserEmpId();
		data[0][1] = congEmailAcc.getAccountName();
		data[0][2] = congEmailAcc.getUserName();
		data[0][3] = pass;//congEmailAcc.getUserPassword();
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

	public Boolean updateEmailAccount(CongEmailAccount congEmailAcc) {
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

		Object[][] data = new Object[1][7];

		String pass="";
		try {
			pass = PPEncrypter.encryptEmailUser(congEmailAcc.getUserPassword());			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		data[0][0] = congEmailAcc.getAccountName();
		data[0][1] = congEmailAcc.getUserName();
		data[0][2] =pass;// congEmailAcc.getUserPassword();
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

	public boolean deletemailAccount(CongEmailAccount congEmailAcc) {
		String query = "DELETE FROM HRMS_EMAIL_ACCOUNT WHERE EMAIL_ACCOUNT_CODE IN("
				+ congEmailAcc.getHiddenCode() + ")";
		boolean result = getSqlModel().singleExecute(query);
		congEmailAcc.setHiddenCode("");
		return result;
	}

	public boolean editEmailAccount(CongEmailAccount congEmailAcc) {

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
	
	public String getMyPassword(String hiddenCode) {

		String selQuery = "SELECT NVL(EMAIL_ACCOUNT_NAME,' '),NVL(EMAIL_USER_NAME,' '),NVL(EMAIL_USER_PASS,' '),HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE,EMAIL_OFFICIAL_FLAG "
				+ "	FROM HRMS_EMAIL_ACCOUNT "
				+ "	LEFT JOIN HRMS_EMAIL_SERVER ON (HRMS_EMAIL_SERVER.SERVER_CODE=HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE) "
				+ "	WHERE " 
				+ " EMAIL_ACCOUNT_CODE="
				+ hiddenCode;
		Object[][] accData = getSqlModel().getSingleResult(selQuery);
		String pass="";
		try {
			pass = PPEncrypter.decryptEmailUser(String.valueOf(accData[0][2]));			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return pass;
	}

	public void loginEmail(CongEmailAccount congEmailAcc,
			HttpServletRequest request,HttpServletResponse response) {	

		String query = "SELECT NVL(EMAIL_USER_NAME,' '),NVL(EMAIL_USER_PASS,' '), SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE, SERVER_OUT_IP, SERVER_OUT_PORT "
			+ " FROM HRMS_EMAIL_ACCOUNT  " 
			+ "	INNER JOIN HRMS_EMAIL_SERVER ON (HRMS_EMAIL_SERVER.SERVER_CODE=HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE) "
			+ "	WHERE EMAIL_EMP_ID = "
			+ congEmailAcc.getUserEmpId()
			+ " AND EMAIL_ACCOUNT_CODE="
			+ congEmailAcc.getHiddenCode()
			+ " ORDER BY EMAIL_ACCOUNT_CODE ";
		
		Object[][] serverData = getSqlModel().getSingleResult(query);
		congEmailAcc.setChkFlag("false");
		congEmailAcc.setChkDisableFlag("true");
		if (serverData != null && serverData.length > 0) {
			request.setAttribute("imapuser", String.valueOf(serverData[0][0]));
			request.setAttribute("pass", String.valueOf(serverData[0][1]));			
			request.setAttribute("server", String.valueOf(serverData[0][2]));
			request.setAttribute("port", String.valueOf(serverData[0][3]));
			request.setAttribute("protocol", String.valueOf(serverData[0][4]));
			request.setAttribute("smtphost", String.valueOf(serverData[0][5]));
			request.setAttribute("smtpport", String.valueOf(serverData[0][6]));			
			request.setAttribute("new_lang", "en_US");
			request.setAttribute("select_view", "dimp");
			congEmailAcc.setImapuser(String.valueOf(serverData[0][0]));
			congEmailAcc.setPass(PPEncrypter.decryptEmailUser(String.valueOf(serverData[0][1])));
			congEmailAcc.setServer(String.valueOf(serverData[0][2]));
			congEmailAcc.setPort(String.valueOf(serverData[0][3]));
			congEmailAcc.setProtocol(String.valueOf(serverData[0][4]));
			congEmailAcc.setSmtphost(String.valueOf(serverData[0][5]));
			congEmailAcc.setSmtpport(String.valueOf(serverData[0][6]));
			congEmailAcc.setNew_lang("en_US");
			congEmailAcc.setSelect_view("dimp");
			
			try {
		/*	RequestDispatcher rd=request.getRequestDispatcher("http://192.168.25.22/webmail/imp/redirect.php");
			rd.forward(request, response);
				
				response.sendRedirect("http://192.168.25.22/webmail/imp/redirect.php");*/
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
