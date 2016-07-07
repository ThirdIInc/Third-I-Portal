package org.paradyne.model.common;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.lib.ModelBase;

/**
 * @author sunil
 * @Date 03-04-2007
 */
public class F9Model extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(F9Model.class); 
	
	public String[][] getData(String query, boolean isLms,String otherDB) {
		//CHANGED BY REEBA
		Object[][] obj = null;
		if(otherDB!=null){
			obj = getSqlModel(otherDB).getSingleResult(query);
		}
		else if(isLms){
			obj = getLMSSqlModel().getSingleResult(query);
		}else{
			obj = getSqlModel().getSingleResult(query);
		}
		
		String[][] strObj;
		if(obj != null && obj.length > 0) {
			strObj = new String[obj.length][obj[0].length];
			for(int i = 0; i < strObj.length; i++) {
				for(int j = 0; j < strObj[0].length; j++) {
					strObj[i][j]=String.valueOf(obj[i][j]);
				}
			}
		} else {
			strObj = new String[0][0];			
		}
		return strObj;	
	}
	
	public String getIntegratedLink(int masterMenuCode, String userProfileID, HttpServletRequest request) {
		String integratedLink = "";
		try {
			String menuProfileDetailSql = " SELECT CASE WHEN PROFILE_INSERT_FLAG = 'Y' AND PROFILE_UPDATE_FLAG = 'Y' AND PROFILE_DELETE_FLAG = 'Y' " +
			" AND PROFILE_VIEW_FLAG = 'Y' THEN MENU_LINK END PROFILE_FLAG FROM HRMS_PROFILE_DTL " +
			" INNER JOIN HRMS_MENU ON(HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE) " +
			" WHERE PROFILE_GENERAL_FLAG = 'N' AND HRMS_PROFILE_DTL.MENU_CODE = " + masterMenuCode + " AND PROFILE_CODE = " + userProfileID;
			Object[][] menuProfileDetailObj = getSqlModel().getSingleResult(menuProfileDetailSql);
			
			if(menuProfileDetailObj != null && menuProfileDetailObj.length > 0) {
				integratedLink = String.valueOf(menuProfileDetailObj[0][0]);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return integratedLink;
	}
}