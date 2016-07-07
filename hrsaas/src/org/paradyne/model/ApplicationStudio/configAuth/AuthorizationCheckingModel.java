/* Bhushan Dasare Dec 2, 2009 */

package org.paradyne.model.ApplicationStudio.configAuth;

import org.paradyne.lib.ModelBase;

public class AuthorizationCheckingModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuthorizationCheckingModel.class);
	
	public String doAuthorization(String loginEmpId, String authPassword, String moduleId) {
		String message = "";
		try {
			String authorised = "F";
			
			String authSettingSql = " SELECT AUTH_TYPE, AUTH_EMP_ID FROM HRMS_AUTHORIZATION_SETTINGS WHERE AUTH_TYPE IN('S', 'M') " +
			" AND AUTH_MODULE_ID = " + moduleId;
			Object[][] authSettingsObj = getSqlModel().getSingleResult(authSettingSql);
			
			if(authSettingsObj != null && authSettingsObj.length > 0) {
				if(String.valueOf(authSettingsObj[0][0]).equals("M")) {
					String[] authEmpId = String.valueOf(authSettingsObj[0][1]).split(",");
					if(authEmpId != null && authEmpId.length > 0) {
						for(int i = 0; i < authEmpId.length; i++) {
							if(loginEmpId.equals(authEmpId[i])) {
								authorised = isPasswordValid(loginEmpId, authPassword);
								break;
							}
						}
					}
				} else if(String.valueOf(authSettingsObj[0][0]).equals("S")) {
					authorised = isPasswordValid(loginEmpId, authPassword);
				} else {
					authorised = "F";
				}
			} else {
				authorised = "F";
			}
			
			if(authorised.equals("F")) {
				message = "Authorization Failed!\nRecord cannot be unlocked.,N";
			} else if(authorised.equals("N")) {
				message = "You are not authorised to unlock the record.,N";
			} else {
				message = "Record unlocked successfully.,Y";
			}
		} catch(Exception e) {
			logger.error("Exception in doAuthorization:" + e);
			message = "Authorization Failed!\nRecord cannot be unlocked.,N";
		}
		return message;
	}
	
	private String isPasswordValid(String loginEmpId, String authPassword) {
		String authorised = "F";
		try {
			String authorizationSql = " SELECT AUTH_PASSWORD FROM HRMS_AUTHORIZATION_CONFIG WHERE AUTH_EMP_ID = " + loginEmpId;
			Object[][] authorizationObj = getSqlModel().getSingleResult(authorizationSql);
			
			if(authorizationObj != null && authorizationObj.length > 0) {
				if(String.valueOf(authorizationObj[0][0]).equals(authPassword)) {
					authorised = "Y";
				}
			} else {
				authorised = "N";
			}
		} catch(Exception e) {
			logger.error("Exception in isPasswordValid:" + e);
		}
		return authorised;
	}
}