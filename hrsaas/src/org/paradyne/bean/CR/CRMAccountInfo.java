package org.paradyne.bean.CR;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**Created on 17th Jan 2012.
 * @author aa0476
 */
public class CRMAccountInfo extends BeanBase {
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
	private String myPage;
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

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the hCustomerCode
	 */
	public String getHCustomerCode() {
		return hCustomerCode;
	}

	/**
	 * @param customerCode
	 *            the hCustomerCode to set
	 */
	public void setHCustomerCode(String customerCode) {
		hCustomerCode = customerCode;
	}
	/**
	 * 	 * @return
	 */
	public String gethCustomerCode() {
		return hCustomerCode;
	}
	/**
	 * 	 * @param hCustomerCode
	 */
	public void sethCustomerCode(String hCustomerCode) {
		this.hCustomerCode = hCustomerCode;
	}
	/**
	 * 	 * @return accountLogo
	 */
	public String getAccountLogo() {
		return accountLogo;
	}
	/**
	 * 	 * @param accountLogo
	 */
	public void setAccountLogo(String accountLogo) {
		this.accountLogo = accountLogo;
	}
	/**
	 * 	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 	 * @return customerOldPassword
	 */
	public String getCustomerOldPassword() {
		return customerOldPassword;
	}
	/**
	 * @param customerOldPassword
	 */
	public void setCustomerOldPassword(String customerOldPassword) {
		this.customerOldPassword = customerOldPassword;
	}
	/**
	 * @return customerNewPassword
	 */
	public String getCustomerNewPassword() {
		return customerNewPassword;
	}
	/**
	 * @param customerNewPassword
	 */
	public void setCustomerNewPassword(String customerNewPassword) {
		this.customerNewPassword = customerNewPassword;
	}
	/**
	 * @return customerConfirmPass
	 */
	public String getCustomerConfirmPass() {
		return customerConfirmPass;
	}
	/**
	 * @param customerConfirmPass
	 */
	public void setCustomerConfirmPass(String customerConfirmPass) {
		this.customerConfirmPass = customerConfirmPass;
	}
	/**
	 * @return accountID
	 */
	public String getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return listLastRun
	 */
	public String getListLastRun() {
		return listLastRun;
	}
	/**
	 * @param listLastRun
	 */
	public void setListLastRun(String listLastRun) {
		this.listLastRun = listLastRun;
	}
	/**
	 * @return listIcon
	 */
	public String getListIcon() {
		return listIcon;
	}
	/**
	 * @return listReportDesc
	 */
	public String getListReportDesc() {
		return listReportDesc;
	}
	/**
	 * @param listReportDesc
	 */
	public void setListReportDesc(String listReportDesc) {
		this.listReportDesc = listReportDesc;
	}
	/**
	 * @param listIcon
	 */
	public void setListIcon(String listIcon) {
		this.listIcon = listIcon;
	}
	/**
	 * @return fromWeek
	 */
	public String getFromWeek() {
		return fromWeek;
	}
	/**
	 * @param fromWeek
	 */
	public void setFromWeek(String fromWeek) {
		this.fromWeek = fromWeek;
	}
	/**
	 * @return toWeek
	 */
	public String getToWeek() {
		return toWeek;
	}
	/**
	 * @param toWeek
	 */
	public void setToWeek(String toWeek) {
		this.toWeek = toWeek;
	}
	/**
	 * @return accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * @param accountCode
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	/**
	 * @return accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * sds.
	 */
	private String reportCode = "";
	/**
	 * 
	 * @return
	 */
	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	/**
	 * @return the accountReportList
	 */
	public ArrayList getAccountReportList() {
		return accountReportList;
	}

	/**
	 * @param accountReportList
	 *            the accountReportList to set
	 */
	public void setAccountReportList(ArrayList accountReportList) {
		this.accountReportList = accountReportList;
	}

	/**
	 * @return the listAccountCode
	 */
	public String getListAccountCode() {
		return listAccountCode;
	}

	/**
	 * @param listAccountCode
	 *            the listAccountCode to set
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
	 * @param listAccountID
	 *            the listAccountID to set
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
	 * @param listAccountName
	 *            the listAccountName to set
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
	 * @param accountList
	 *            the accountList to set
	 */
	public void setAccountList(ArrayList accountList) {
		this.accountList = accountList;
	}

	/**
	 * @return the listContactName
	 */
	public String getListContactName() {
		return listContactName;
	}

	/**
	 * @param listContactName
	 *            the listContactName to set
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
	 * @param listEmailID
	 *            the listEmailID to set
	 */
	public void setListEmailID(String listEmailID) {
		this.listEmailID = listEmailID;
	}

	/**
	 * @return the listReportCode
	 */
	public String getListReportCode() {
		return listReportCode;
	}

	/**
	 * @param listReportCode
	 *            the listReportCode to set
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
	 * @param listReportName
	 *            the listReportName to set
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
	 * @param listGroupName
	 *            the listGroupName to set
	 */
	public void setListGroupName(String listGroupName) {
		this.listGroupName = listGroupName;
	}

	/**
	 * @return the reportList
	 */
	public ArrayList getReportList() {
		return reportList;
	}

	/**
	 * @param reportList
	 *            the reportList to set
	 */
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
}
