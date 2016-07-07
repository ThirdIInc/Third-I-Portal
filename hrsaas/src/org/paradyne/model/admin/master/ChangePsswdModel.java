/**
 * 
 */
package org.paradyne.model.admin.master;

import org.paradyne.bean.admin.master.ChangePsswdBean;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.StringEncrypter.EncryptionException;

/**
 * @author MuzaffarS
 *
 */
public class ChangePsswdModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void changePsswd(ChangePsswdBean bean) throws EncryptionException
	{
		String log_code=bean.getUserLoginCode();
	String emp_id=bean.getUserEmpId();
	String oldPswd="";
	String emp=" SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||" +
			" HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="+emp_id ;
	String psswd=" SELECT LOGIN_PASSWORD FROM HRMS_LOGIN WHERE EMP_ID="+emp_id;
	
	Object arg[][]=	getSqlModel().getSingleResult(emp);
	Object arg1[][]=	getSqlModel().getSingleResult(psswd);
	oldPswd = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(String.valueOf(arg1[0][0]));	
	logger.info("******************"+oldPswd);
	bean.setPssword(oldPswd);
	bean.setEmpnm(String.valueOf(arg[0][0]));
	bean.setEmp_id(String.valueOf(emp_id));
	}
	public void savePsswd(ChangePsswdBean bean) throws EncryptionException
	{
		String log_code=bean.getUserLoginCode();
		//String old=bean.getOldpsswd();
		String newpsswd1=bean.getNewpsswd1();
		String newpsswd2=bean.getNewpsswd2();
		String emp_id=bean.getEmp_id();
		String oldpssswd=new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean.getOldpsswd());
		String query=" SELECT LOGIN_PASSWORD	FROM HRMS_LOGIN WHERE EMP_ID="+emp_id+" and  LOGIN_CODE="+log_code;
		Object arg[][]=	getSqlModel().getSingleResult(query);
	//	String oldpssd=new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(String.valueOf(arg[0][0]));
		logger.info(" outside if");
		if(oldpssswd.equals(String.valueOf(arg[0][0])))
		{
			logger.info(" into old psswd stat and old pswd:"+oldpssswd);
		if(newpsswd1.equals(newpsswd2))
		{
			String newpsswd= new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean.getNewpsswd1());
			logger.info(" into new  psswd stat and new pswdPPPPPPPPPPPPPPPPPP:"+newpsswd);
			String psswd=" update hrms_login set LOGIN_PASSWORD='"+newpsswd+"' where emp_id="+emp_id + " AND  LOGIN_CODE="+log_code;
			getSqlModel().singleExecute(psswd);
		}
		}
	}
	
	public boolean saveMailPassword(ChangePsswdBean bean){
		logger.info("in side model");
		boolean result;
		Object [][] insertData  = new Object[1][3];
		insertData[0][0] = bean.getEmp_id();
		insertData[0][1] = bean.getUserName();
		insertData[0][2]  = bean.getOldPass();
		//String confPass  = bean.getConfPass();
		logger.info("emp id---------"+insertData[0][0]);
		String chekQuery = "SELECT EMPMAIL_USERID, EMPMAIL_PASSWORD FROM HRMS_SETTINGS_EMPMAIL WHERE EMP_ID = "+insertData[0][0];
		Object passData [][] = getSqlModel().getSingleResult(chekQuery);
		logger.info("selected data---------"+passData.length);
		if(passData.length==0){
			String insertQuery = "INSERT INTO HRMS_SETTINGS_EMPMAIL (EMP_ID, EMPMAIL_USERID, EMPMAIL_PASSWORD) "
								 +"VALUES(?, ?, ?)";
			result = getSqlModel().singleExecute(insertQuery, insertData);
			logger.info("result-------------"+result);
		}else{
			Object [][] updateData  = new Object[1][3];
			updateData[0][2] = bean.getEmp_id();
			updateData[0][0] = bean.getUserName();
			updateData[0][1]  = bean.getOldPass();
			String updateQuery = "UPDATE HRMS_SETTINGS_EMPMAIL SET EMPMAIL_USERID=?, EMPMAIL_PASSWORD=? WHERE EMP_ID=?";
			result = getSqlModel().singleExecute(updateQuery, updateData);
		}
		return result;
	}
	
	public void getPassword(ChangePsswdBean bean){
		logger.info("in side getPassword");
		String emp_id = bean.getUserEmpId();
		String query  = "SELECT EMPMAIL_USERID, EMPMAIL_PASSWORD FROM HRMS_SETTINGS_EMPMAIL WHERE EMP_ID = "+emp_id;
		Object [][] result  =  getSqlModel().getSingleResult(query);
		bean.setUserName(String.valueOf(result[0][0]));
		bean.setOldPass(String.valueOf(result[0][1]));
		//bean.setMailPassword(String.valueOf(result[0][1]));
	}

}
