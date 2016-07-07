package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author radha
 * 
 */

public class NomineeDetail extends BeanBase implements Serializable {

	String nomineeCode = "";
	String empID = "";
	String empName = "";
	String deptName = "";
	String centerName = "";
	String nomiType = "";
	String memberName = "";
	String memberCode = "";
	String nomiFraction = "";
	String nomiDate = "";
	String paracode = "";
	ArrayList nomList = null;
	String empToken = "";
	String rankName = "";
	String oldFractionValue ="";
	String witnessName = ""; 
	String witnessAddress= "";
	ArrayList<Object> witnessDetailsList = null;
	String witnessID = "";
	String isNotGeneralUser = "false";
	String uploadFileName = "";
	String flag= "";
	String noData= "";
	String profileEmpName= "";
	
	private boolean editFlag=false;
	private boolean editDetail=false;
	String deleteChk="false";
	public ArrayList<Object> getWitnessDetailsList() {
		return witnessDetailsList;
	}

	public void setWitnessDetailsList(ArrayList<Object> witnessDetailsList) {
		this.witnessDetailsList = witnessDetailsList;
	}

	/**
	 * @param nomineeCode
	 * @param empID
	 * @param empName
	 * @param deptName
	 * @param centerName
	 * @param nomiType
	 * @param memberName
	 * @param memberCode
	 * @param nomiFraction
	 * @param nomiDate
	 * @param paracode
	 */
	public NomineeDetail(String nomineeCode, String empID, String empName,
			String deptName, String centerName, String nomiType,
			String memberName, String memberCode, String nomiFraction,
			String nomiDate, String paracode , String witnessName, String witnessAddress, String witnessID ) {
		this.nomineeCode = nomineeCode;
		this.empID = empID;
		this.empName = empName;
		this.deptName = deptName;
		this.centerName = centerName;
		this.nomiType = nomiType;
		this.memberName = memberName;
		this.memberCode = memberCode;
		this.nomiFraction = nomiFraction;
		this.nomiDate = nomiDate;
		this.paracode = paracode;
		this.witnessName = witnessName;
		this.witnessAddress = witnessAddress;
		this.witnessID = witnessID;
	}

	public NomineeDetail() {

	}

	/**
	 * @return the rankCode
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankCode
	 *            the rankCode to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *            the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *            the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the memberCode
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * @param memberCode
	 *            the memberCode to set
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName
	 *            the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the nomiDate
	 */
	public String getNomiDate() {
		return nomiDate;
	}

	/**
	 * @param nomiDate
	 *            the nomiDate to set
	 */
	public void setNomiDate(String nomiDate) {
		this.nomiDate = nomiDate;
	}

	/**
	 * @return the nomiFraction
	 */
	public String getNomiFraction() {
		return nomiFraction;
	}

	/**
	 * @param nomiFraction
	 *            the nomiFraction to set
	 */
	public void setNomiFraction(String nomiFraction) {
		this.nomiFraction = nomiFraction;
	}

	/**
	 * @return the nomineeCode
	 */
	public String getNomineeCode() {
		return nomineeCode;
	}

	/**
	 * @param nomineeCode
	 *            the nomineeCode to set
	 */
	public void setNomineeCode(String nomineeCode) {
		this.nomineeCode = nomineeCode;
	}

	/**
	 * @return the nomiType
	 */
	public String getNomiType() {
		return nomiType;
	}

	/**
	 * @param nomiType
	 *            the nomiType to set
	 */
	public void setNomiType(String nomiType) {
		this.nomiType = nomiType;
	}

	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}

	/**
	 * @param paracode
	 *            the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	/**
	 * @return the nomList
	 */
	public ArrayList getNomList() {
		return nomList;
	}

	/**
	 * @param nomList
	 *            the nomList to set
	 */
	public void setNomList(ArrayList nomList) {
		this.nomList = nomList;
	}

	public String getOldFractionValue() {
		return oldFractionValue;
	}

	public void setOldFractionValue(String oldFractionValue) {
		this.oldFractionValue = oldFractionValue;
	}

	public String getWitnessName() {
		return witnessName;
	}

	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}

	public String getWitnessAddress() {
		return witnessAddress;
	}

	public void setWitnessAddress(String witnessAddress) {
		this.witnessAddress = witnessAddress;
	}

	public String getWitnessID() {
		return witnessID;
	}

	public void setWitnessID(String witnessID) {
		this.witnessID = witnessID;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getProfileEmpName() {
		return profileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	public boolean isEditDetail() {
		return editDetail;
	}

	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

	public String getDeleteChk() {
		return deleteChk;
	}

	public void setDeleteChk(String deleteChk) {
		this.deleteChk = deleteChk;
	}

	
}