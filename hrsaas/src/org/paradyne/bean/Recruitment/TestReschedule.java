/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0517
 *
 */
public class TestReschedule extends BeanBase {
	private String noData="true";
	private ArrayList list=null;
	private String totalRecords="";
	private String myPage="";
	private String requisitionCode="";
	private String requisitionName="";
	private String requisitionId = "";
	private String candidateCode="";
	private String candidateName="";
	private String testDate;
	private String testTime;
	private String testCode;
	private String testDtlCode;
	private String comments;
	private String radioButton;
	private String stats="";
	private String  modeLength="";
	private String buttonFlag = "true";
	private String testStatus = "N";
	private String testRound = "";
	private String testType ="";
	
	public String getTestRound() {
		return testRound;
	}
	public void setTestRound(String testRound) {
		this.testRound = testRound;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getRadioButton() {
		return radioButton;
	}
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	public String getRequisitionName() {
		return requisitionName;
	}
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestDtlCode() {
		return testDtlCode;
	}
	public void setTestDtlCode(String testDtlCode) {
		this.testDtlCode = testDtlCode;
	}
	public String getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
}
