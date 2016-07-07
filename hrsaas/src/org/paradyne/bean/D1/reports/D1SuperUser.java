package org.paradyne.bean.D1.reports;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class D1SuperUser extends BeanBase {

	
	public String applicationType="";
	public String applicantCode="";
	public String applicantToken="";
	public String applicantName="";
	public String applicationStatus="";
	public String ittTrackingNo="";
	public String ittEmpToken="";
	public String ittEmpame="";
	public String ittApplicationType="";
	public String ittApplicationDate="";
	public String ittApplicationCode="";
	public String ittStatus="";
	
	ArrayList superUserIteratorList = null;
	private boolean superUserListLength = false;
	private String myPage = "";
	private String modeLength = "";
	private String totalRecords = "";
	private String ittPendingWith = "";
	private String ittScheduleDate = "";
	private String  scheduleDateFlag= "false";
	
	public ArrayList list=null;
	
	//BRD SUPER USER.
	private String ittBrdNo = "";
	private String ittProjName = "";
	private String ittExpDate = "";
	private String ittCurrentStage = "";
	private String ittPendingWithRole = "";
	private String ittProjStatus = "";
	private String ittPendingWithName = "";
	private String ittHiddenCode = "";
	 private String serialNo = "";
	
	private boolean superUserBRDListLength = false;
	private String  displayApplicationFlag= "false";
	private String  BrdFlag = "false";
	public ArrayList brdList=null;
	
	
	private String myPageOngoing = ""; 
	

	public ArrayList getSuperUserIteratorList() {
		return superUserIteratorList;
	}

	public void setSuperUserIteratorList(ArrayList superUserIteratorList) {
		this.superUserIteratorList = superUserIteratorList;
	}

	public boolean isSuperUserListLength() {
		return superUserListLength;
	}

	public void setSuperUserListLength(boolean superUserListLength) {
		this.superUserListLength = superUserListLength;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}

	public String getApplicantToken() {
		return applicantToken;
	}

	public void setApplicantToken(String applicantToken) {
		this.applicantToken = applicantToken;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getIttTrackingNo() {
		return ittTrackingNo;
	}

	public void setIttTrackingNo(String ittTrackingNo) {
		this.ittTrackingNo = ittTrackingNo;
	}

	public String getIttEmpToken() {
		return ittEmpToken;
	}

	public void setIttEmpToken(String ittEmpToken) {
		this.ittEmpToken = ittEmpToken;
	}

	public String getIttEmpame() {
		return ittEmpame;
	}

	public void setIttEmpame(String ittEmpame) {
		this.ittEmpame = ittEmpame;
	}

	public String getIttApplicationType() {
		return ittApplicationType;
	}

	public void setIttApplicationType(String ittApplicationType) {
		this.ittApplicationType = ittApplicationType;
	}

	public String getIttApplicationDate() {
		return ittApplicationDate;
	}

	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getIttStatus() {
		return ittStatus;
	}

	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}

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

	public String getIttPendingWith() {
		return ittPendingWith;
	}

	public void setIttPendingWith(String ittPendingWith) {
		this.ittPendingWith = ittPendingWith;
	}

	public String getIttApplicationCode() {
		return ittApplicationCode;
	}

	public void setIttApplicationCode(String ittApplicationCode) {
		this.ittApplicationCode = ittApplicationCode;
	}

	public String getIttScheduleDate() {
		return ittScheduleDate;
	}

	public void setIttScheduleDate(String ittScheduleDate) {
		this.ittScheduleDate = ittScheduleDate;
	}

	public String getScheduleDateFlag() {
		return scheduleDateFlag;
	}

	public void setScheduleDateFlag(String scheduleDateFlag) {
		this.scheduleDateFlag = scheduleDateFlag;
	}

	/**
	 * @return the ittBrdNo
	 */
	public String getIttBrdNo() {
		return ittBrdNo;
	}

	/**
	 * @param ittBrdNo the ittBrdNo to set
	 */
	public void setIttBrdNo(String ittBrdNo) {
		this.ittBrdNo = ittBrdNo;
	}

	/**
	 * @return the ittProjName
	 */
	public String getIttProjName() {
		return ittProjName;
	}

	/**
	 * @param ittProjName the ittProjName to set
	 */
	public void setIttProjName(String ittProjName) {
		this.ittProjName = ittProjName;
	}

	/**
	 * @return the ittExpDate
	 */
	public String getIttExpDate() {
		return ittExpDate;
	}

	/**
	 * @param ittExpDate the ittExpDate to set
	 */
	public void setIttExpDate(String ittExpDate) {
		this.ittExpDate = ittExpDate;
	}

	/**
	 * @return the ittCurrentStage
	 */
	public String getIttCurrentStage() {
		return ittCurrentStage;
	}

	/**
	 * @param ittCurrentStage the ittCurrentStage to set
	 */
	public void setIttCurrentStage(String ittCurrentStage) {
		this.ittCurrentStage = ittCurrentStage;
	}

	/**
	 * @return the ittPendingWithRole
	 */
	public String getIttPendingWithRole() {
		return ittPendingWithRole;
	}

	/**
	 * @param ittPendingWithRole the ittPendingWithRole to set
	 */
	public void setIttPendingWithRole(String ittPendingWithRole) {
		this.ittPendingWithRole = ittPendingWithRole;
	}

	/**
	 * @return the ittPendingWithName
	 */
	public String getIttPendingWithName() {
		return ittPendingWithName;
	}

	/**
	 * @param ittPendingWithName the ittPendingWithName to set
	 */
	public void setIttPendingWithName(String ittPendingWithName) {
		this.ittPendingWithName = ittPendingWithName;
	}

	/**
	 * @return the ittHiddenCode
	 */
	public String getIttHiddenCode() {
		return ittHiddenCode;
	}

	/**
	 * @param ittHiddenCode the ittHiddenCode to set
	 */
	public void setIttHiddenCode(String ittHiddenCode) {
		this.ittHiddenCode = ittHiddenCode;
	}

	/**
	 * @return the superUserBRDListLength
	 */
	public boolean isSuperUserBRDListLength() {
		return superUserBRDListLength;
	}

	/**
	 * @param superUserBRDListLength the superUserBRDListLength to set
	 */
	public void setSuperUserBRDListLength(boolean superUserBRDListLength) {
		this.superUserBRDListLength = superUserBRDListLength;
	}

	/**
	 * @return the displayApplicationFlag
	 */
	public String getDisplayApplicationFlag() {
		return displayApplicationFlag;
	}

	/**
	 * @param displayApplicationFlag the displayApplicationFlag to set
	 */
	public void setDisplayApplicationFlag(String displayApplicationFlag) {
		this.displayApplicationFlag = displayApplicationFlag;
	}

	/**
	 * @return the myPageOngoing
	 */
	public String getMyPageOngoing() {
		return myPageOngoing;
	}

	/**
	 * @param myPageOngoing the myPageOngoing to set
	 */
	public void setMyPageOngoing(String myPageOngoing) {
		this.myPageOngoing = myPageOngoing;
	}

	/**
	 * @return the brdList
	 */
	public ArrayList getBrdList() {
		return brdList;
	}

	/**
	 * @param brdList the brdList to set
	 */
	public void setBrdList(ArrayList brdList) {
		this.brdList = brdList;
	}

	/**
	 * @return the brdFlag
	 */
	public String getBrdFlag() {
		return BrdFlag;
	}

	/**
	 * @param brdFlag the brdFlag to set
	 */
	public void setBrdFlag(String brdFlag) {
		BrdFlag = brdFlag;
	}

	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the ittProjStatus
	 */
	public String getIttProjStatus() {
		return ittProjStatus;
	}

	/**
	 * @param ittProjStatus the ittProjStatus to set
	 */
	public void setIttProjStatus(String ittProjStatus) {
		this.ittProjStatus = ittProjStatus;
	}

	
}
