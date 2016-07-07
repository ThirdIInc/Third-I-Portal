package org.paradyne.bean.vendor;
/** @ Author : Archana Salunkhe
 * @ purpose : Developed for Vendor Registration  
 * @ Date : 08-May-2012
 */
import org.paradyne.lib.BeanBase;

public class VendorRegistration extends BeanBase {
	
	private String partnerName="";
	private String partnerCode="";
	private String emailId="";
	private String loginName="";
	private String password="";	
	
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
