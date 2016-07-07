package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PurchaseOrderApproval extends BeanBase {
	
	private String purchaseNoItt="";
	private String appcomment="";
	private String hiddenPurchaseCode="";
	private String hiddenStatus="";
	private String hiddenStatusval="";
	private String empToken;
	private String pendingWith;
	
	private String status;
	private String totalAmt;
	private String purchaseCode;
	private String empID;
	private String empName;
	private String orderDate;
	private String empCode;
	
	private String myPage;

	private String noData="false";
	private String apprflag="false";
	private String listLength="0"; 
	private String level="";
	private String checkStatus;
	private int applicationLevel=0;
	
	
	private String comment;
	private ArrayList<Object> recordList;
	private String statusNew;
	
	
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ArrayList<Object> getRecordList() {
		return recordList;
	}
	public void setRecordList(ArrayList<Object> recordList) {
		this.recordList = recordList;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getAppcomment() {
		return appcomment;
	}
	public void setAppcomment(String appcomment) {
		this.appcomment = appcomment;
	}
	public String getHiddenPurchaseCode() {
		return hiddenPurchaseCode;
	}
	public void setHiddenPurchaseCode(String hiddenPurchaseCode) {
		this.hiddenPurchaseCode = hiddenPurchaseCode;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getHiddenStatusval() {
		return hiddenStatusval;
	}
	public void setHiddenStatusval(String hiddenStatusval) {
		this.hiddenStatusval = hiddenStatusval;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getPurchaseNoItt() {
		return purchaseNoItt;
	}
	public void setPurchaseNoItt(String purchaseNoItt) {
		this.purchaseNoItt = purchaseNoItt;
	}
	public int getApplicationLevel() {
		return applicationLevel;
	}
	public void setApplicationLevel(int applicationLevel) {
		this.applicationLevel = applicationLevel;
	}
	
}
