package org.paradyne.bean.vas;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class VASServiceLogin extends BeanBase {

	private String servAcctCode = "";
	private String servCode = "";
	private String servClientCode = "";
	private String servEmpId = "";
	private String servUsrName = "";
	private String servPwd = "";

	private ArrayList ServloginList = null;

	public String getServAcctCode() {
		return servAcctCode;
	}

	public void setServAcctCode(String servAcctCode) {
		this.servAcctCode = servAcctCode;
	}

	public String getServCode() {
		return servCode;
	}

	public void setServCode(String servCode) {
		this.servCode = servCode;
	}

	public String getServClientCode() {
		return servClientCode;
	}

	public void setServClientCode(String servClientCode) {
		this.servClientCode = servClientCode;
	}

	public String getServUsrName() {
		return servUsrName;
	}

	public void setServUsrName(String servUsrName) {
		this.servUsrName = servUsrName;
	}

	public String getServPwd() {
		return servPwd;
	}

	public void setServPwd(String servPwd) {
		this.servPwd = servPwd;
	}

	public String getServEmpId() {
		return servEmpId;
	}

	public void setServEmpId(String servEmpId) {
		this.servEmpId = servEmpId;
	}

	public ArrayList getServloginList() {
		return ServloginList;
	}

	public void setServloginList(ArrayList servloginList) {
		ServloginList = servloginList;
	}
}
