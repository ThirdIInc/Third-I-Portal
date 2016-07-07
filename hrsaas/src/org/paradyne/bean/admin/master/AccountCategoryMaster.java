package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author Dilip Kumar
 * Date:21/09/09
 */
public class AccountCategoryMaster  extends BeanBase{
	ArrayList accountList;
	 private String hiddencode="";
	 private String accountID="";
	 private String acctNameId="";
	 private String accountName="";
	 private String accountAbbreviation="";
	 
	 private String isActive=""; // Status is added by Abhijit
	 
	 private String accountLength="false";
	 private String myPage="";
	 private String totalRecords="";
	 private String hdeleteCode="";
	public ArrayList getAccountList() {
		return accountList;
	}
	public void setAccountList(ArrayList accountList) {
		this.accountList = accountList;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAcctNameId() {
		return acctNameId;
	}
	public void setAcctNameId(String acctNameId) {
		this.acctNameId = acctNameId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountAbbreviation() {
		return accountAbbreviation;
	}
	public void setAccountAbbreviation(String accountAbbreviation) {
		this.accountAbbreviation = accountAbbreviation;
	}
	public String getAccountLength() {
		return accountLength;
	}
	public void setAccountLength(String accountLength) {
		this.accountLength = accountLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
