/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
/**
 * @author AA0431
 *
 */
public class OnlineApprovalModel  extends ModelBase{

	public String checkOnlineAuth(String applicationType) {
		String result="";
		String query="SELECT MODULE_AUTHFLAG FROM HRMS_MODULE WHERE MODULE_TYPE='"+applicationType+"'";
		Object[][] authFlag=getSqlModel().getSingleResult(query);
		if(authFlag!=null && authFlag.length>0){
			if(String.valueOf(authFlag[0][0]).equals("Y"))
				result="authRequired";
			else
				result="authNotRequired";
		}
		else
			result="authNotFound";
			
		
		return result;
	}

	public boolean checkUser(String approverId, String username, String password) {
		boolean result=false;
		try {
			password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String chkPassQuery = " SELECT LOGIN_NAME FROM HRMS_LOGIN WHERE LOGIN_NAME='"
			+username+ "' AND LOGIN_PASSWORD='"+ password+ "'  AND EMP_ID="+approverId;
		Object[][] passCheck=getSqlModel().getSingleResult(chkPassQuery);
		if(passCheck!=null && passCheck.length>0)
			result=true;
	return result;	
	}
}
