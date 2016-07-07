package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class SafetyCommitteeMasterBean extends BeanBase {

	private String safetyCommitteeID="";
	private String committeeType="";
	private String trainingReceivedFlag="";
	private String roleType="";
	private String empCode="";
	private String empName="";
	private String empToken="";
	private String myPage="";
	private String modeLength ="";
	private String totalRecords ="";
	private String hiddenCode="";
	
	
	ArrayList<Object>  safetyCommitteeList=null ;
	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getSafetyCommitteeID() {
		return safetyCommitteeID;
	}
	public void setSafetyCommitteeID(String safetyCommitteeID) {
		this.safetyCommitteeID = safetyCommitteeID;
	}
	public String getCommitteeType() {
		return committeeType;
	}
	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}
	public String getTrainingReceivedFlag() {
		return trainingReceivedFlag;
	}
	public void setTrainingReceivedFlag(String trainingReceivedFlag) {
		this.trainingReceivedFlag = trainingReceivedFlag;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public ArrayList<Object> getSafetyCommitteeList() {
		return safetyCommitteeList;
	}
	public void setSafetyCommitteeList(ArrayList<Object> safetyCommitteeList) {
		this.safetyCommitteeList = safetyCommitteeList;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	
	
	
	
}
