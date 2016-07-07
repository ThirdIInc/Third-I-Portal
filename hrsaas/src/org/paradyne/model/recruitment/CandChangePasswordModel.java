/**
 * 
 */
package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.CandChangePassword;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

/**
 * @author aa0517
 *
 */
public class CandChangePasswordModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CandChangePasswordModel.class);
	public void getRecord(CandChangePassword chngPass) {
		Object[][]loginData = null;
		System.out.println("NAME : "+ chngPass.getUserEmpId());
		System.out.println("NAME : "+ chngPass.getUserLoginCode());
		
		try {
			String query = "SELECT CAND_FIRSTNAME||' '||CAND_LASTNAME,CAND_PWD FROM HRMS_REC_CAND_LOGIN "
						  +" WHERE CAND_CODE = "+chngPass.getCandidateUserEmpId()+"";
			loginData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in loginData query : "+ e);
		}
		
		if(loginData != null && loginData.length >0){
			chngPass.setCandName(String.valueOf(loginData[0][0]));
		} 
	} //end of getRecord method
	
	public String save(CandChangePassword chngPass) {
		
		Object[][]loginData = null;
		try {
			String query = "SELECT CAND_FIRSTNAME||' '||CAND_LASTNAME,CAND_PWD FROM HRMS_REC_CAND_LOGIN "
						  +" WHERE CAND_CODE = "+chngPass.getCandidateUserEmpId()+"";
			loginData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in save : " + e);
		} //end of catch
		
		if(loginData != null && loginData.length >0){
			String password = "";
			try {
				password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.decrypt(String.valueOf(loginData[0][1]));
			} catch (Exception e) {
				logger.error("exception in password decrypter : " + e);
			} //end of catch
			
			if(!(password.equals(chngPass.getOldPassword()))){
				return "1";
			} //end of if
			else{
					String newPassword = "";
					try {
						newPassword = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
								.encrypt(chngPass.getNewPassword());
					} catch (Exception e) {
						logger.error("exception in password decrypter : " + e);
					} //end of catch
					
					String update = "UPDATE HRMS_REC_CAND_LOGIN SET CAND_PWD = '"+newPassword+"'"
								   +" WHERE CAND_CODE = "+chngPass.getCandidateUserEmpId()+"";
					getSqlModel().singleExecute(update);
			} //end of else
		} //end of if
		return "2";
	} //end of save method
} //end of class
