package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;

public class CandidateLoginBean extends BeanBase {
	private String candidateCode = "";
	private String infoId = "";
	private String loginName = "";
	private String loginPassword = "";
	private String submitFlag = "";
	private String emailId = "";
	private String firstName = "";
	private String lastname = "";
	private String mobile = "";
	private String dateOfBirth = "";
	private String userNameForgot = "";
	private String forgotCandidatePasswordSubmitFlag ="";
	 

	public String getUserNameForgot() {
		return userNameForgot;
	}

	public void setUserNameForgot(String userNameForgot) {
		this.userNameForgot = userNameForgot;
	}

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCandidateCode() {
		return candidateCode;
	}

	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
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

	public String getForgotCandidatePasswordSubmitFlag() {
		return forgotCandidatePasswordSubmitFlag;
	}

	public void setForgotCandidatePasswordSubmitFlag(
			String forgotCandidatePasswordSubmitFlag) {
		this.forgotCandidatePasswordSubmitFlag = forgotCandidatePasswordSubmitFlag;
	}
}
