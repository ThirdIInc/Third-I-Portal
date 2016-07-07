package org.paradyne.bean.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelAdvanceDefaulter extends BeanBase {
	 
	private String fromDate="";
	private String toDate="";
	private String salMonth="";
	private String salYear="";
	private String advHeaderId=""; 
	private String  processDate="";
	private String  processFlag="false";
	private String  dispDataFlag="false";
	 
	
	// for iterator 
	ArrayList advDftList = new ArrayList();
	private String empName ="";
	private String trRequest ="";
	private String advanceDate ="";
	private String advanceAmt ="";
	private String expSettleDate ="";
	private String pendingDays="";
	private String trAppId="";
	private String trEmpId=""; 
	private String itDefaultChk="";
	private String hidDefaultChk="";
	private String selectAllDefaulter=""; 
	
	
	public String getSelectAllDefaulter() {
		return selectAllDefaulter;
	}
	public void setSelectAllDefaulter(String selectAllDefaulter) {
		this.selectAllDefaulter = selectAllDefaulter;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSalMonth() {
		return salMonth;
	}
	public void setSalMonth(String salMonth) {
		this.salMonth = salMonth;
	}
	public String getSalYear() {
		return salYear;
	}
	public void setSalYear(String salYear) {
		this.salYear = salYear;
	}
	public ArrayList getAdvDftList() {
		return advDftList;
	}
	public void setAdvDftList(ArrayList advDftList) {
		this.advDftList = advDftList;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTrRequest() {
		return trRequest;
	}
	public void setTrRequest(String trRequest) {
		this.trRequest = trRequest;
	}
	public String getAdvanceDate() {
		return advanceDate;
	}
	public void setAdvanceDate(String advanceDate) {
		this.advanceDate = advanceDate;
	}
	public String getAdvanceAmt() {
		return advanceAmt;
	}
	public void setAdvanceAmt(String advanceAmt) {
		this.advanceAmt = advanceAmt;
	}
	public String getExpSettleDate() {
		return expSettleDate;
	}
	public void setExpSettleDate(String expSettleDate) {
		this.expSettleDate = expSettleDate;
	}
	public String getPendingDays() {
		return pendingDays;
	}
	public void setPendingDays(String pendingDays) {
		this.pendingDays = pendingDays;
	}
	public String getTrAppId() {
		return trAppId;
	}
	public void setTrAppId(String trAppId) {
		this.trAppId = trAppId;
	}
	public String getTrEmpId() {
		return trEmpId;
	}
	public void setTrEmpId(String trEmpId) {
		this.trEmpId = trEmpId;
	}
	public String getAdvHeaderId() {
		return advHeaderId;
	}
	public void setAdvHeaderId(String advHeaderId) {
		this.advHeaderId = advHeaderId;
	}
	public String getProcessDate() {
		return processDate;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	public String getItDefaultChk() {
		return itDefaultChk;
	}
	public void setItDefaultChk(String itDefaultChk) {
		this.itDefaultChk = itDefaultChk;
	}
	public String getHidDefaultChk() {
		return hidDefaultChk;
	}
	public void setHidDefaultChk(String hidDefaultChk) {
		this.hidDefaultChk = hidDefaultChk;
	}
	public String getProcessFlag() {
		return processFlag;
	}
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	public String getDispDataFlag() {
		return dispDataFlag;
	}
	public void setDispDataFlag(String dispDataFlag) {
		this.dispDataFlag = dispDataFlag;
	}
  

}
