package org.paradyne.model.settings;

import org.paradyne.bean.settings.UserUpdation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

/**
 * @author reebaj
 *
 */

public class UserUpdationModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	UserUpdation userUpdation = null;

	public UserUpdationModel() {
		// TODO Auto-generated constructor stub
	}

	public boolean process(UserUpdation userUpdation) {
		boolean result = false;
		try {
			// TODO Auto-generated method stub
			String query = "SELECT EMP_ID FROM HRMS_LOGIN1";
			Object[][] id = getSqlModel().getSingleResult(query);
			System.out.println("length..." + id.length);
			Object[][] finalObj = new Object[id.length][3];
			for (int i = 0; i < id.length; i++) {
				String passQuery = " SELECT LOGIN_NAME,LOGIN_PASSWORD,LOGIN_CODE FROM HRMS_LOGIN1 "
						+ " WHERE EMP_ID= " + String.valueOf(id[i][0]);

				Object data[][] = getSqlModel().getSingleResult(passQuery);
				
				logger.info("login name before setting:......."+String.valueOf(data[0][0]));

				finalObj[i][0] = String.valueOf(data[0][0]); //LOGIN_NAME
				logger.info("login name:......."+String.valueOf(data[0][0]));
				finalObj[i][1] = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(String.valueOf(data[0][1])); //PASSWORD
				logger.info("password:......."+String.valueOf(data[0][1]));
				finalObj[i][2] = String.valueOf(data[0][2]); //LOGIN_CODE
				logger.info("login code:......."+String.valueOf(data[0][2]));

			}
			
			String updateQuery = " UPDATE HRMS_LOGIN1 SET LOGIN_NAME = ?,LOGIN_PASSWORD = ? " +
								 " WHERE LOGIN_CODE =? ";
			result = getSqlModel().singleExecute(updateQuery, finalObj); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
		

}
