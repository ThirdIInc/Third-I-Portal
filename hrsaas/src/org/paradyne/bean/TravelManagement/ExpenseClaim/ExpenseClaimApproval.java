/**
 * 
 */
package org.paradyne.bean.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class ExpenseClaimApproval extends BeanBase {

	 private String empId="";
	 private String empName ="";
	 private String reqName="";
	 private String appDate ="";
	 private String empToken="";
	 private String claimAppId="";
	 private String hiddenLevel="";
	 private String hiddenEmpId="";
	 private String hiddenClaimId="";
	 private String approverComments="";
	 private String level="";
	 private String checkStatus ="";
	 private String statusNew ="";
	 private String remark ="";
	 private String apprflag="";
	 private String statusFld="";
	 private String noData="false";
	 private String listLength;
	 
	 private ArrayList claimList;
	// for paging 
	 private String hdPage="";
	 private String fromTotRec="";
	 private String toTotRec="";
	 private String show;
	 private String myPage;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getClaimAppId() {
		return claimAppId;
	}
	public void setClaimAppId(String claimAppId) {
		this.claimAppId = claimAppId;
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
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	
	public String getHiddenLevel() {
		return hiddenLevel;
	}
	public void setHiddenLevel(String hiddenLevel) {
		this.hiddenLevel = hiddenLevel;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	
	public String getStatusFld() {
		return statusFld;
	}
	public void setStatusFld(String statusFld) {
		this.statusFld = statusFld;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public ArrayList getClaimList() {
		return claimList;
	}
	public void setClaimList(ArrayList claimList) {
		this.claimList = claimList;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddenEmpId() {
		return hiddenEmpId;
	}
	public void setHiddenEmpId(String hiddenEmpId) {
		this.hiddenEmpId = hiddenEmpId;
	}
	public String getHiddenClaimId() {
		return hiddenClaimId;
	}
	public void setHiddenClaimId(String hiddenClaimId) {
		this.hiddenClaimId = hiddenClaimId;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
}
