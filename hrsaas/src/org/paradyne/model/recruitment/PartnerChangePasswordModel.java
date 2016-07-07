/**
 * aa0417
   Jul 7, 2009
 */
package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.PartnerChangePassword;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

/**
 * @author aa0417
 *
 */
public class PartnerChangePasswordModel extends ModelBase {
	
	public void callonLoad(PartnerChangePassword bean)
	{
		String sql =" SELECT REC_PARTNERNAME  FROM HRMS_REC_PARTNER WHERE REC_CODE ="+bean.getUserEmpId();
		Object [][] data = getSqlModel().getSingleResult(sql);
		if(data != null && data.length >0)
		{
			bean.setPartnerName(""+data[0][0]);
		}
	}
	
	public String save(PartnerChangePassword bean)
	{ 
		String sql =" SELECT REC_PASSWORD  FROM HRMS_REC_PARTNER WHERE REC_CODE ="+bean.getUserEmpId();
		Object [][] data = getSqlModel().getSingleResult(sql); 
		if(data != null && data.length >0)
		{ 
			try
			{
		         String  oldPass  =String.valueOf(data[0][0]).trim(); 
		      String oldChk = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean.getOldPass().trim());
		      String newPass = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean.getNewPass().trim());
		   if(oldChk.equals(oldPass))
				{
					String modSql =" UPDATE HRMS_REC_PARTNER SET REC_PASSWORD ='"+newPass+"' WHERE REC_CODE ="+bean.getUserEmpId();
					getSqlModel().singleExecute(modSql);
					return "success";
				}else
				{
					return "oldInvalid";
				} 
			}catch (Exception e) { 
				e.printStackTrace();
			}  
		}
		return "success";
	}

}
