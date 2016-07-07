/**
 * aa0417
   Jul 7, 2009
 */
package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0417
 *
 */
public class PartnerChangePassword extends BeanBase {
	private String partnerName="";
	private String oldPass="";
	private String newPass="";
	
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
		
}
