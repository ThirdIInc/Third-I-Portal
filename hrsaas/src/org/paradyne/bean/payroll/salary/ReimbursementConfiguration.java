package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ReimbursementConfiguration extends BeanBase {

	private String myPage;
	private String hiddenEmpId;
	private String hiddenDivId;
	private String accountantEmpToken;
	private String accountantEmpName;
	private String accountantDivName;
	private String accountantEmpId;
	private String accountantDivId;
	private String accountantEmpNameItt;
	private String accountantEmpIdItt;
	private String accountantDivIdItt;
	private String accountantDivNameItt;
	private ArrayList accountantListArray=null;

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getAccountantEmpId() {
		return accountantEmpId;
	}

	public void setAccountantEmpId(String accountantEmpId) {
		this.accountantEmpId = accountantEmpId;
	}

	public String getAccountantDivId() {
		return accountantDivId;
	}

	public void setAccountantDivId(String accountantDivId) {
		this.accountantDivId = accountantDivId;
	}

	public ArrayList getAccountantListArray() {
		return accountantListArray;
	}

	public void setAccountantListArray(ArrayList accountantListArray) {
		this.accountantListArray = accountantListArray;
	}


	public String getAccountantEmpName() {
		return accountantEmpName;
	}

	public void setAccountantEmpName(String accountantEmpName) {
		this.accountantEmpName = accountantEmpName;
	}

	public String getAccountantDivName() {
		return accountantDivName;
	}

	public void setAccountantDivName(String accountantDivName) {
		this.accountantDivName = accountantDivName;
	}

	public String getAccountantEmpNameItt() {
		return accountantEmpNameItt;
	}

	public void setAccountantEmpNameItt(String accountantEmpNameItt) {
		this.accountantEmpNameItt = accountantEmpNameItt;
	}

	public String getAccountantEmpIdItt() {
		return accountantEmpIdItt;
	}

	public void setAccountantEmpIdItt(String accountantEmpIdItt) {
		this.accountantEmpIdItt = accountantEmpIdItt;
	}

	public String getAccountantDivIdItt() {
		return accountantDivIdItt;
	}

	public void setAccountantDivIdItt(String accountantDivIdItt) {
		this.accountantDivIdItt = accountantDivIdItt;
	}

	public String getAccountantDivNameItt() {
		return accountantDivNameItt;
	}

	public void setAccountantDivNameItt(String accountantDivNameItt) {
		this.accountantDivNameItt = accountantDivNameItt;
	}

	public String getAccountantEmpToken() {
		return accountantEmpToken;
	}

	public void setAccountantEmpToken(String accountantEmpToken) {
		this.accountantEmpToken = accountantEmpToken;
	}

	public String getHiddenEmpId() {
		return hiddenEmpId;
	}

	public void setHiddenEmpId(String hiddenEmpId) {
		this.hiddenEmpId = hiddenEmpId;
	}

	public String getHiddenDivId() {
		return hiddenDivId;
	}

	public void setHiddenDivId(String hiddenDivId) {
		this.hiddenDivId = hiddenDivId;
	}
}
