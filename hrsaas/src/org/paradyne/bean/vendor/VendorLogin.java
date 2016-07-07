/**
 * 
 */
package org.paradyne.bean.vendor;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0491
 *
 */
public class VendorLogin extends BeanBase {
	
	
	private String vendorLoginId="";	
	private String showForgot ="false";
	private String infoId = "";
	private String loginName = null;
	private String loginPassword = null;
	private String empId = null;
	private String loginCode = null;
	private String partnerName="";
	private String partnerCode="";
	private String emailId="";
	private String submitFlag = "";
	
	private String vendorUserNameForgot ="";
	public String getVendorUserNameForgot() {
		return vendorUserNameForgot;
	}
	public void setVendorUserNameForgot(String vendorUserNameForgot) {
		this.vendorUserNameForgot = vendorUserNameForgot;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getShowForgot() {
		return showForgot;
	}
	public void setShowForgot(String showForgot) {
		this.showForgot = showForgot;
	}
	public String getVendorLoginId() {
		return vendorLoginId;
	}
	public void setVendorLoginId(String vendorLoginId) {
		this.vendorLoginId = vendorLoginId;
	}
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
	public String getSubmitFlag() {
		return submitFlag;
	}
	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
}
