package org.paradyne.model.recruitment;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

public class CandidateLoginModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public String submitnewuser(CandidateLoginBean loginBean) {
		
		 
		String result = "";
		String encryptPwd = "";
		Object data[][] = null;
		Object pwdData[][] = null;
		try {
			Object[] loginName = new Object[1];
			loginName[0] = loginBean.getLoginName().trim();
			String queryName = "SELECT CAND_LOGINID, CAND_FIRSTNAME, CAND_LASTNAME, CAND_USERNAME, CAND_PWD, CAND_CODE, CAND_DATABANK_CODE FROM HRMS_REC_CAND_LOGIN where CAND_USERNAME= ? ";
			data = getSqlModel().getSingleResult(queryName, loginName);

			if (data == null) {
				result = "1";
				return result;
			} else if (data.length == 0) {
				result = "1";
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

					String pwdQuery = "SELECT * FROM HRMS_REC_CAND_LOGIN where CAND_PWD=? AND CAND_USERNAME=? ";
					pwdData = getSqlModel().getSingleResult(pwdQuery,
							encryptedPassword);

					if (pwdData == null) {
						result = "2";
						return result;
					} else if (pwdData.length == 0) {
						result = "2";
						return result;
					}

					loginBean.setCandidateCode(String.valueOf(data[0][5]));
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
	 */
	public void getCandName(CandidateLoginBean loginBean,
			HttpServletRequest request) {
		try {
			String candQuery = " SELECT NVL(CAND_FIRST_NAME,CAND_FIRSTNAME)||' '||NVL(CAND_LAST_NAME,CAND_LASTNAME) FROM HRMS_REC_CDOL_DATABANK " 
                             + " left join HRMS_REC_CAND_LOGIN on(HRMS_REC_CAND_LOGIN.CAND_CODE=HRMS_REC_CDOL_DATABANK.CAND_CODE)"
                             + " WHERE HRMS_REC_CDOL_DATABANK.CAND_CODE='"
					+ loginBean.getCandidateCode() + "'";
			Object[][] data = getSqlModel().getSingleResult(candQuery);
			request.setAttribute("CandName", String.valueOf(data[0][0]));
		} catch (Exception e) {
			e.printStackTrace();
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
}
