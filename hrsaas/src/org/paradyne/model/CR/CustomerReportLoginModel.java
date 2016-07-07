package org.paradyne.model.CR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.CR.CustomerReportLoginBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

public class CustomerReportLoginModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ModelBase.class);

public String submitnewuser(CustomerReportLoginBean loginBean) {

 
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
				loginBean.setLoginName(String.valueOf(data[0][3]));
				loginBean.setCustomerCode(String.valueOf(data[0][0]));
				loginBean.setAccountCode(String.valueOf(data[0][7]));
				return result;
			}else if (String.valueOf(pwdData) != null && pwdData.length > 0) {
				result = "4";
				loginBean.setLoginName(String.valueOf(data[0][3]));
				return result;
			}

			loginBean.setCustomerCode(String.valueOf(data[0][5]));
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

public void createMenu(HttpServletRequest request) {
try {
	String contextPath = request.getContextPath();
	String query = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
			+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
			+ contextPath
			+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
			+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,MENU_PLACEINMENU,MENU_TABORDER "
			+ " FROM HRMS_MENU "
			+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE )"
			/*
			 * "AND ( PROFILE_INSERT_FLAG='Y' " + " OR
			 * PROFILE_UPDATE_FLAG ='Y' OR PROFILE_DELETE_FLAG ='Y' OR
			 * PROFILE_VIEW_FLAG ='Y' " + " OR PROFILE_GENERAL_FLAG
			 * ='Y'))"
			 */
			+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
			+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE =58"
			+ " AND MENU_PARENT_CODE=0"
			+ " AND MENU_ISRELEASED='Y'"
			+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";
	String[][] strObj = null;
	Object[][] obj = getSqlModel().getSingleResult(query);
	if (obj != null) {
		strObj = new String[obj.length][5];

		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);
			strObj[i][2] = String.valueOf(obj[i][2]);
			strObj[i][3] = String.valueOf(obj[i][3]);
			strObj[i][4] = String.valueOf(obj[i][4]);
		}
	}
	request.setAttribute("menuList", strObj);
} catch (Exception e) {
	e.printStackTrace();
}
}

/**
* added by Pradeep on date:09-11-2009 following function is called to
* display the Candidate Name
* 
* @param loginBean
* @param request
 * @param customerCode 
*/
public void getCandName(CustomerReportLoginBean bean,
	HttpServletRequest request, String customerCode) {
	String accountCode="";
try {
	String candQuery = "SELECT FIRST_NAME||' '||LAST_NAME,ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE='"
			+ customerCode + "'";
	Object[][] data = getSqlModel().getSingleResult(candQuery);
	if(data!=null && data.length>0){
	request.setAttribute("CandName", String.valueOf(data[0][0]));
	bean.setAccountCode(String.valueOf(data[0][1]));
	accountCode= String.valueOf(data[0][1]);
	}
} catch (Exception e) {
	e.printStackTrace();
}

/**
 * SET ACCOUNT INFORMATION
 */

	String accQuery="SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ')  " +
			" FROM CR_CLIENT_MASTER WHERE (ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+customerCode+")" +
			"  OR PARENT_CODE  IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+customerCode+"))";
	
	Object[][]accObj=getSqlModel().getSingleResult(accQuery);
	bean.setAccountList(null);
	if(accObj!=null && accObj.length>0){
		ArrayList list=new ArrayList();
		for (int i = 0; i < accObj.length; i++) {
			CustomerReportLoginBean subBean=new CustomerReportLoginBean();
			subBean.setListAccountCode(String.valueOf(accObj[i][0]));
			subBean.setListAccountID(String.valueOf(accObj[i][1]));
			subBean.setListAccountName(String.valueOf(accObj[i][2]));
			list.add(subBean);
		}		
		bean.setAccountList(list);
	}

}

/**
* Added by Pradeep on Date:09-11-2009 following method is called to display
* the logo for the corresponding company
* 
* @return
*/
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

public void createMainMenu(HttpServletRequest request) {
try {
	String contextPath = request.getContextPath();
	// int random1 = 0;
	// Random random = new Random();
	String query = "SELECT distinct HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "

			+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL AND MENU_TARGET='main' THEN  '"
			+ contextPath
			+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
			+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU "
			+ " FROM HRMS_MENU "
			// + " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE =
			// HRMS_PROFILE_DTL.MENU_CODE) "

			/*
			 * + " and HRMS_PROFILE_DTL.PROFILE_CODE ='58' AND (
			 * PROFILE_INSERT_FLAG='Y' " + " OR PROFILE_UPDATE_FLAG ='Y'
			 * OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' " + "
			 * OR PROFILE_GENERAL_FLAG ='Y')) "
			 */
			+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
			+ " WHERE MENU_ISRELEASED='Y'  and HRMS_MENU.MENU_PARENT_CODE!=0 "
			+ " START WITH HRMS_MENU.MENU_CODE =884 "
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

public Object[][] getJobBoardList() {
Object[][] listObj = null;
try {
	String query = " SELECT REQS_NAME,CENTER_NAME,SUM(VACAN_NUMBERS),REQS_JOB_DESCRIPTION "
			+ " FROM HRMS_REC_REQS_HDR  INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE)  INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE = HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE)  INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  WHERE REQS_STATUS = 'O'  AND REQS_APPROVAL_STATUS IN ('A','Q') AND VACAN_STATUS = 'P' AND PUB_TO_CANDJOB = 'Y'  GROUP BY HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_POSITION,REQS_BRANCH,CENTER_NAME,REQS_JOB_DESCRIPTION ";
	listObj = getSqlModel().getSingleResult(query);
} catch (Exception e) {
	// TODO: handle exception
}
return listObj;
}

public void getMultipleLogin(CustomerReportLoginBean bean,
		HttpServletRequest request) {
	String accountCode="";
	try {
		Object data1[][] = null;
		Object[] loginName = new Object[1];
		loginName[0] = bean.getLoginName().trim();
		String queryName = "SELECT DISTINCT  EMAIL_ID  FROM CR_CLIENT_USERS WHERE EMAIL_ID= ? ";
		data1 = getSqlModel().getSingleResult(queryName, loginName);
		
		
		if(data1!=null && data1.length>0){
			request.setAttribute("CandName", String.valueOf(data1[0][0]));
			///accountCode= String.valueOf(data1[0][1]);
			bean.setLoginName(String.valueOf(data1[0][0]));
			bean.setCustomerUserEmpId(bean.getCustomerUserEmpId());
			
			}
		
		String candQuery = "SELECT FIRST_NAME||' '||LAST_NAME,ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE='"
				+ bean.getCustomerCode() + "'";
		Object[][] data = getSqlModel().getSingleResult(candQuery);
		if(data!=null && data.length>0){
		request.setAttribute("CandName", String.valueOf(data1[0][0]));
		bean.setAccountCode(String.valueOf(data[0][1]));
		accountCode= String.valueOf(data[0][1]);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
}
