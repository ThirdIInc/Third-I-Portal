package org.paradyne.bean.CR;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**Created on 17th Jan 2012.
 * @author aa0476
 */
public class CustomerAccountInfo extends BeanBase {
	/** string. * */
	private String listAccountCode = "";
	/** string. * */
	private String listAccountID = "";
	/** string. * */
	private String listAccountName = "";
	/** string. * */
	private String listContactName = "";
	/** string. * */
	private String listEmailID = "";
	/** string. * */
	private String accountName = "";
	/** string. * */
	private String accountCode = "";
	/** string. * */
	private String accountLogo = "";
	/** string. * */
	private String hCustomerCode = "";
	/** string. * */
	private String accountID = "";
	/** string. * */
	private String customerName = "";
	/** string. * */
	private String customerOldPassword = "";
	/** string. * */
	private String customerNewPassword = "";
	/** string. * */
	private String customerConfirmPass = "";
	/** string. * */
	private String listIcon = "";
	/** string. * */
	private String listReportDesc = "";
	/** string. * */
	private String listLastRun = "";
	/** string. * */
	private String fromWeek = "";
	/** string. * */
	private String toWeek = "";
	/** string. * */
	private String listReportCode = "";
	/** string. * */
	private String listReportName = "";
	/** string. * */
	private String listGroupName = "";
	/** ArrayList. * */
	private ArrayList accountList = null;
	/** ArrayList. * */
	private ArrayList accountReportList = null;
	/** ArrayList. * */
	private ArrayList reportList = null;
	/** string. * */
	private String reportCode = "";
	private String loginName = "";
	private String customerCode = "";
	private String loginMultipleName = "";
	private String custCode = "";
	private String publishDate = "";
	private String hPublishDate = "";
	
	private String accCode = "";
	/**
	 * @return the accCode
	 */
	public String getAccCode() {
		return accCode;
	}
	/**
	 * @param accCode the accCode to set
	 */
	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}
	/**
	 * @return the hPublishDate
	 */
	public String getHPublishDate() {
		return hPublishDate;
	}
	/**
	 * @param publishDate the hPublishDate to set
	 */
	public void setHPublishDate(String publishDate) {
		hPublishDate = publishDate;
	}
	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
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
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	 * @return the listContactName
	 */
	public String getListContactName() {
		return listContactName;
	}
	/**
	 * @param listContactName the listContactName to set
	 */
	public void setListContactName(String listContactName) {
		this.listContactName = listContactName;
	}
	/**
	 * @return the listEmailID
	 */
	public String getListEmailID() {
		return listEmailID;
	}
	/**
	 * @param listEmailID the listEmailID to set
	 */
	public void setListEmailID(String listEmailID) {
		this.listEmailID = listEmailID;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
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
	 * @return the accountLogo
	 */
	public String getAccountLogo() {
		return accountLogo;
	}
	/**
	 * @param accountLogo the accountLogo to set
	 */
	public void setAccountLogo(String accountLogo) {
		this.accountLogo = accountLogo;
	}
	/**
	 * @return the hCustomerCode
	 */
	public String getHCustomerCode() {
		return hCustomerCode;
	}
	/**
	 * @param customerCode the hCustomerCode to set
	 */
	public void setHCustomerCode(String customerCode) {
		hCustomerCode = customerCode;
	}
	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerOldPassword
	 */
	public String getCustomerOldPassword() {
		return customerOldPassword;
	}
	/**
	 * @param customerOldPassword the customerOldPassword to set
	 */
	public void setCustomerOldPassword(String customerOldPassword) {
		this.customerOldPassword = customerOldPassword;
	}
	/**
	 * @return the customerNewPassword
	 */
	public String getCustomerNewPassword() {
		return customerNewPassword;
	}
	/**
	 * @param customerNewPassword the customerNewPassword to set
	 */
	public void setCustomerNewPassword(String customerNewPassword) {
		this.customerNewPassword = customerNewPassword;
	}
	/**
	 * @return the customerConfirmPass
	 */
	public String getCustomerConfirmPass() {
		return customerConfirmPass;
	}
	/**
	 * @param customerConfirmPass the customerConfirmPass to set
	 */
	public void setCustomerConfirmPass(String customerConfirmPass) {
		this.customerConfirmPass = customerConfirmPass;
	}
	/**
	 * @return the listIcon
	 */
	public String getListIcon() {
		return listIcon;
	}
	/**
	 * @param listIcon the listIcon to set
	 */
	public void setListIcon(String listIcon) {
		this.listIcon = listIcon;
	}
	/**
	 * @return the listReportDesc
	 */
	public String getListReportDesc() {
		return listReportDesc;
	}
	/**
	 * @param listReportDesc the listReportDesc to set
	 */
	public void setListReportDesc(String listReportDesc) {
		this.listReportDesc = listReportDesc;
	}
	/**
	 * @return the listLastRun
	 */
	public String getListLastRun() {
		return listLastRun;
	}
	/**
	 * @param listLastRun the listLastRun to set
	 */
	public void setListLastRun(String listLastRun) {
		this.listLastRun = listLastRun;
	}
	/**
	 * @return the fromWeek
	 */
	public String getFromWeek() {
		return fromWeek;
	}
	/**
	 * @param fromWeek the fromWeek to set
	 */
	public void setFromWeek(String fromWeek) {
		this.fromWeek = fromWeek;
	}
	/**
	 * @return the toWeek
	 */
	public String getToWeek() {
		return toWeek;
	}
	/**
	 * @param toWeek the toWeek to set
	 */
	public void setToWeek(String toWeek) {
		this.toWeek = toWeek;
	}
	/**
	 * @return the listReportCode
	 */
	public String getListReportCode() {
		return listReportCode;
	}
	/**
	 * @param listReportCode the listReportCode to set
	 */
	public void setListReportCode(String listReportCode) {
		this.listReportCode = listReportCode;
	}
	/**
	 * @return the listReportName
	 */
	public String getListReportName() {
		return listReportName;
	}
	/**
	 * @param listReportName the listReportName to set
	 */
	public void setListReportName(String listReportName) {
		this.listReportName = listReportName;
	}
	/**
	 * @return the listGroupName
	 */
	public String getListGroupName() {
		return listGroupName;
	}
	/**
	 * @param listGroupName the listGroupName to set
	 */
	public void setListGroupName(String listGroupName) {
		this.listGroupName = listGroupName;
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
	/**
	 * @return the accountReportList
	 */
	public ArrayList getAccountReportList() {
		return accountReportList;
	}
	/**
	 * @param accountReportList the accountReportList to set
	 */
	public void setAccountReportList(ArrayList accountReportList) {
		this.accountReportList = accountReportList;
	}
	/**
	 * @return the reportList
	 */
	public ArrayList getReportList() {
		return reportList;
	}
	/**
	 * @param reportList the reportList to set
	 */
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
	/**
	 * @return the reportCode
	 */
	public String getReportCode() {
		return reportCode;
	}
	/**
	 * @param reportCode the reportCode to set
	 */
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
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

	
}
