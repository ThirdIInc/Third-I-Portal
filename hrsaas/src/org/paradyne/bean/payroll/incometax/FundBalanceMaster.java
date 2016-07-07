package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class FundBalanceMaster extends BeanBase {
	
	private String debitName="";
	private String debitCode="";
	private String paybillID="";
	private String paybillGroup="";
	private String flag;
	private String showFlag;
	private String saveFlag="";
	
	
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getPaybillID() {
		return paybillID;
	}
	public void setPaybillID(String paybillID) {
		this.paybillID = paybillID;
	}
	public String getPaybillGroup() {
		return paybillGroup;
	}
	public void setPaybillGroup(String paybillGroup) {
		this.paybillGroup = paybillGroup;
	}
	

}
