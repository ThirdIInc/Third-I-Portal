/**
 * 
 */
package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author mangeshj
 *
 */
public class AssetApproval extends BeanBase {
	
	private String source ="";
	private String assetId="";
	private String empCode;
	private String approverCode;
	private String hiddenStatus;
	private String appcomment="";
	private String code="";
	private int applicationLevel=0;
	private String hiddenappCode="";
	private String keepInfoFlag="true";
	
	private String empToken;
	private String pendingWith;
	
	private String status;
	private String ammount;
	private String assetCode;
	private String empID;
	private String empName;
	private String appliedDate;
	
	private String myPage;
	
	private String noData="false";
	private String apprflag="false";
	private String listLength="0"; 
	private String level="";
	private String checkStatus;
	
	
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
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
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
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getAppcomment() {
		return appcomment;
	}
	public void setAppcomment(String appcomment) {
		this.appcomment = appcomment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHiddenappCode() {
		return hiddenappCode;
	}
	public void setHiddenappCode(String hiddenappCode) {
		this.hiddenappCode = hiddenappCode;
	}
	public int getApplicationLevel() {
		return applicationLevel;
	}
	public void setApplicationLevel(int applicationLevel) {
		this.applicationLevel = applicationLevel;
	}
	public String getKeepInfoFlag() {
		return keepInfoFlag;
	}
	public void setKeepInfoFlag(String keepInfoFlag) {
		this.keepInfoFlag = keepInfoFlag;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
