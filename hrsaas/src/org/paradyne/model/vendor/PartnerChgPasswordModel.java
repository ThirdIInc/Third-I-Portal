package org.paradyne.model.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Change Password of Partner
 * @ Date :20-May-2012
 */

import org.paradyne.bean.vendor.PartnerChgPassword;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

public class PartnerChgPasswordModel extends ModelBase{
	
	public void getPartnerDetails(PartnerChgPassword partnerBean) {
		
		System.out.println("+ partnerBean.getLoginPartnerCode()--------------"+ partnerBean.getLoginPartnerCode());
		String query = "SELECT PARTNER_CODE, PARTNER_NAME FROM VENDOR_INFO "
			         	+" WHERE PARTNER_LOGIN_CODE ="+ partnerBean.getLoginPartnerCode();
		Object [][] outObj = getSqlModel().getSingleResult(query);
		if(outObj != null && outObj.length >0){
			partnerBean.setPartnerChgCode(String.valueOf(outObj[0][0]));
			partnerBean.setPartnerChgNm(String.valueOf(outObj[0][1]));			
		}		
	}

	public String save(PartnerChgPassword pChgPassBean) {
		
		String query = "SELECT  PARTNER_LOGIN_PASS FROM VENDOR_INFO WHERE PARTNER_LOGIN_CODE = "+ pChgPassBean.getLoginPartnerCode();
		Object [][] passOutObj = getSqlModel().getSingleResult(query);
		if(passOutObj !=null && passOutObj.length >0){
			try
			{
		         String  oldPass  =String.valueOf(passOutObj[0][0]).trim(); 
		      String oldChk = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(pChgPassBean.getPartnerOldPass().trim());
		      String newPass = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(pChgPassBean.getPartnerNewPass().trim());
		      
		      System.out.println("oldPass--------"+oldPass);
		      System.out.println("oldChk--------"+oldChk);
		      System.out.println("newPass--------"+newPass);
		   if(oldChk.equals(oldPass))
				{
					String modSql =" UPDATE VENDOR_INFO SET PARTNER_LOGIN_PASS = '"+newPass+"' WHERE PARTNER_LOGIN_CODE = "+pChgPassBean.getLoginPartnerCode();
					getSqlModel().singleExecute(modSql);
					return "success";
				}else
				{
					return "InvalidOld";
				} 
			}catch (Exception e) { 
				e.printStackTrace();
			}  
		}
		return "success";
		
	}

}
