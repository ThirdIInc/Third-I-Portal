package org.paradyne.bean.CR;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CustomerReportLoginBean extends BeanBase {
	//private String candidateCode = "";
	private String customerCode = "";
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
	 
	private String listAccountCode = "";
	private String listAccountID = "";
	private String listAccountName ="";
	private ArrayList accountList=null;
	
	private String loginMultipleName = "";
	private String accountCode = "";
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the listAccountCode
	 */
	public String getListAccountCode() {
		return listAccountCode;
	}

	/**
	 * @param listAccountCode the listAccountCode to set
	 */
	public void setListAccountCode(String listAccountCode) {
		this.listAccountCode = listAccountCode;
	}

	/**
	 * @return the listAccountID
	 */
	public String getListAccountID() {
		return listAccountID;
	}

	/**
	 * @param listAccountID the listAccountID to set
	 */
	public void setListAccountID(String listAccountID) {
		this.listAccountID = listAccountID;
	}

	/**
	 * @return the listAccountName
	 */
	public String getListAccountName() {
		return listAccountName;
	}

	/**
	 * @param listAccountName the listAccountName to set
	 */
	public void setListAccountName(String listAccountName) {
		this.listAccountName = listAccountName;
	}

	/**
	 * @return the accountList
	 */
	public ArrayList getAccountList() {
		return accountList;
	}

	/**
	 * @param accountList the accountList to set
	 */
	public void setAccountList(ArrayList accountList) {
		this.accountList = accountList;
	}

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

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return the loginMultipleName
	 */
	public String getLoginMultipleName() {
		return loginMultipleName;
	}

	/**
	 * @param loginMultipleName the loginMultipleName to set
	 */
	public void setLoginMultipleName(String loginMultipleName) {
		this.loginMultipleName = loginMultipleName;
	}
}
