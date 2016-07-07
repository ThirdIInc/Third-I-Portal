package org.paradyne.bean.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Change Password of Partner
 * @ Date :20-May-2012
 */
import org.paradyne.lib.BeanBase;

public class PartnerChgPassword extends BeanBase{

	private String partnerChgNm = "";
	private String partnerChgCode = "";
	private String partnerOldPass="";
	private String partnerNewPass = "";
	private String partnerConfPass="";
	
	public String getPartnerChgNm() {
		return partnerChgNm;
	}
	public void setPartnerChgNm(String partnerChgNm) {
		this.partnerChgNm = partnerChgNm;
	}
	public String getPartnerChgCode() {
		return partnerChgCode;
	}
	public void setPartnerChgCode(String partnerChgCode) {
		this.partnerChgCode = partnerChgCode;
	}
	public String getPartnerOldPass() {
		return partnerOldPass;
	}
	public void setPartnerOldPass(String partnerOldPass) {
		this.partnerOldPass = partnerOldPass;
	}
	public String getPartnerNewPass() {
		return partnerNewPass;
	}
	public void setPartnerNewPass(String partnerNewPass) {
		this.partnerNewPass = partnerNewPass;
	}
	public String getPartnerConfPass() {
		return partnerConfPass;
	}
	public void setPartnerConfPass(String partnerConfPass) {
		this.partnerConfPass = partnerConfPass;
	}
	
}
