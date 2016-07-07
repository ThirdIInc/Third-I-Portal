package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author pradeep K
 * 
 */

public class AwardDetails extends BeanBase implements Serializable {

	String empId = "";
	String empName = "";
	String empDept = "";
	String empCent = "";
	String awdId = "";
	String empTrade = "";
	String empRank = "";
	String awardType = "";
	String awdDesc = "";
	String awdDt = "";
	String auth = "";
	String ordrDt = "";
	String authDt = "";
	String awdCode = "";
	String employeeToken = "";
	String paraId = "";
	String slNo = "";//Added on 26.07.2007
	String uploadFileName = "";
	String photoUploadFileName = "";
	String flag = "";
	String noData = "";
	String profileEmpName = "";
	String recordId = "";
	String isNotGeneralUser="false";
	
	private String activeInDashlet = "";
	private String uploadImageName = "";
	private String uploadMyFileName = "";
	private String uploadMyFileNameTxt = "";
	
	private String uploadImageNameItt ="";
	private String isActiveItt ="";
	private boolean editFlag=false;
	
	private ArrayList awardList = null;

	public AwardDetails() {
	}

	/**
	 * Constructor for AwardDetails
	 * @param empId
	 * @param empName
	 * @param empDept
	 * @param empCent
	 * @param empTrade
	 * @param empRank
	 * @param awardType
	 * @param awdDesc
	 * @param awdDt
	 * @param auth
	 * @param ordrDt
	 * @param authDt
	 * @param awdId
	 * @param employeeToken
	 * @param slNo
	 * @param paraId
	 */
	public AwardDetails(String empId, String empName, String empDept,
			String empCent, String empTrade, String empRank, String awardType,
			String awdDesc, String awdDt, String auth, String ordrDt,
			String authDt, String awdId, String employeeToken, String slNo,
			String paraId) {

		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empCent = empCent;
		this.empTrade = empTrade;
		this.empRank = empRank;
		this.awardType = awardType;
		this.awdDesc = awdDesc;
		this.awdDt = awdDt;
		this.auth = auth;
		this.ordrDt = ordrDt;
		this.authDt = authDt;
		this.awdId = awdId;
		this.employeeToken = employeeToken;
		this.slNo = slNo;
		this.paraId = paraId;

	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the authDt
	 */
	public String getAuthDt() {
		return authDt;
	}

	/**
	 * @param authDt the authDt to set
	 */
	public void setAuthDt(String authDt) {
		this.authDt = authDt;
	}

	/**
	 * @return the awardType
	 */
	public String getAwardType() {
		return awardType;
	}

	/**
	 * @param awardType the awardType to set
	 */
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	/**
	 * @return the awdDesc
	 */
	public String getAwdDesc() {
		return awdDesc;
	}

	/**
	 * @param awdDesc the awdDesc to set
	 */
	public void setAwdDesc(String awdDesc) {
		this.awdDesc = awdDesc;
	}

	/**
	 * @return the awdDt
	 */
	public String getAwdDt() {
		return awdDt;
	}

	/**
	 * @param awdDt the awdDt to set
	 */
	public void setAwdDt(String awdDt) {
		this.awdDt = awdDt;
	}

	/**
	 * @return the empCent
	 */
	public String getEmpCent() {
		return empCent;
	}

	/**
	 * @param empCent the empCent to set
	 */
	public void setEmpCent(String empCent) {
		this.empCent = empCent;
	}

	/**
	 * @return the empDept
	 */
	public String getEmpDept() {
		return empDept;
	}

	/**
	 * @param empDept the empDept to set
	 */
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empRank
	 */
	public String getEmpRank() {
		return empRank;
	}

	/**
	 * @param empRank the empRank to set
	 */
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	/**
	 * @return the empTrade
	 */
	public String getEmpTrade() {
		return empTrade;
	}

	/**
	 * @param empTrade the empTrade to set
	 */
	public void setEmpTrade(String empTrade) {
		this.empTrade = empTrade;
	}

	/**
	 * @return the ordrDt
	 */
	public String getOrdrDt() {
		return ordrDt;
	}

	/**
	 * @param ordrDt the ordrDt to set
	 */
	public void setOrdrDt(String ordrDt) {
		this.ordrDt = ordrDt;
	}

	/**
	 * @return the awdId
	 */
	public String getAwdId() {
		return awdId;
	}

	/**
	 * @param awdId the awdId to set
	 */
	public void setAwdId(String awdId) {
		this.awdId = awdId;
	}

	/**
	 * @return the awdCode
	 */
	public String getAwdCode() {
		return awdCode;
	}

	/**
	 * @param awdCode the awdCode to set
	 */
	public void setAwdCode(String awdCode) {
		this.awdCode = awdCode;
	}

	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}

	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}

	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the slNo
	 */
	public String getSlNo() {
		return slNo;
	}

	/**
	 * @param slNo the slNo to set
	 */
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the awardList
	 */
	public ArrayList getAwardList() {
		return awardList;
	}

	/**
	 * @param awardList the awardList to set
	 */
	public void setAwardList(ArrayList awardList) {
		this.awardList = awardList;
	}

	public String getActiveInDashlet() {
		return activeInDashlet;
	}

	public void setActiveInDashlet(String activeInDashlet) {
		this.activeInDashlet = activeInDashlet;
	}

	public String getUploadImageName() {
		return uploadImageName;
	}

	public void setUploadImageName(String uploadImageName) {
		this.uploadImageName = uploadImageName;
	}

	public String getUploadImageNameItt() {
		return uploadImageNameItt;
	}

	public void setUploadImageNameItt(String uploadImageNameItt) {
		this.uploadImageNameItt = uploadImageNameItt;
	}

	public String getIsActiveItt() {
		return isActiveItt;
	}

	public void setIsActiveItt(String isActiveItt) {
		this.isActiveItt = isActiveItt;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}


	public String getPhotoUploadFileName() {
		return photoUploadFileName;
	}

	public void setPhotoUploadFileName(String photoUploadFileName) {
		this.photoUploadFileName = photoUploadFileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProfileEmpName() {
		return profileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getUploadMyFileName() {
		return uploadMyFileName;
	}

	public void setUploadMyFileName(String uploadMyFileName) {
		this.uploadMyFileName = uploadMyFileName;
	}

	public String getUploadMyFileNameTxt() {
		return uploadMyFileNameTxt;
	}

	public void setUploadMyFileNameTxt(String uploadMyFileNameTxt) {
		this.uploadMyFileNameTxt = uploadMyFileNameTxt;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

}
