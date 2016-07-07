package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProfReferences extends BeanBase {
	
	private String empId;
	private String empName;
	private String empbranch;	
	private String empdesignation;
	private String address1;
	private String address2;
	private String address3;
	private String phoneNo;
	private String mobileNo;		
	private String paraId;
	private String employeeToken;
	private String newFlag;
	private String hiddType;	
	private String profFname;
	private String profMname;
	private String profLname;
	private String emailId;
	private String profrefName;
	private String occupation;
	private String profRefId;
	private String flag;
	private String uploadFileName;
	private String isNotGeneralUser;
	private String profileEmpName;
	private String editFlag;
	private String editDetail;
	private String noData;
	private String faxNo;
	private String cityName;
	private String countryName;
	private String companyName;
	private String pinCode;
	private String extension;
	private String otherInfo;
	private String stateName;
	private String abbrAdd1="";
	private String abbrAdd2="";
	private String abbrAdd3="";
	private String abbrOccupation="";
	private String abbrCompany="";
	private String abbrOtherInfo="";
	private String abbrEmailId="";
	ArrayList<ProfReferences> profList;
	
	// for combo box
	
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
	 * @return the empbranch
	 */
	public String getEmpbranch() {
		return empbranch;
	}
	/**
	 * @param empbranch the empbranch to set
	 */
	public void setEmpbranch(String empbranch) {
		this.empbranch = empbranch;
	}
	/**
	 * @return the empdesignation
	 */
	public String getEmpdesignation() {
		return empdesignation;
	}
	/**
	 * @param empdesignation the empdesignation to set
	 */
	public void setEmpdesignation(String empdesignation) {
		this.empdesignation = empdesignation;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}
	/**
	 * @param newFlag the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}
	/**
	 * @return the hiddType
	 */
	public String getHiddType() {
		return hiddType;
	}
	/**
	 * @param hiddType the hiddType to set
	 */
	public void setHiddType(String hiddType) {
		this.hiddType = hiddType;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the profrefName
	 */
	public String getProfrefName() {
		return profrefName;
	}
	/**
	 * @param profrefName the profrefName to set
	 */
	public void setProfrefName(String profrefName) {
		this.profrefName = profrefName;
	}
	/**
	 * @return the profList
	 */
	public ArrayList<ProfReferences> getProfList() {
		return profList;
	}
	public void setProfList(ArrayList<ProfReferences> profList) {
		this.profList = profList;
	}
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return the profFname
	 */
	public String getProfFname() {
		return profFname;
	}
	/**
	 * @param profFname the profFname to set
	 */
	public void setProfFname(String profFname) {
		this.profFname = profFname;
	}
	/**
	 * @return the profMname
	 */
	public String getProfMname() {
		return profMname;
	}
	/**
	 * @param profMname the profMname to set
	 */
	public void setProfMname(String profMname) {
		this.profMname = profMname;
	}
	/**
	 * @return the profLname
	 */
	public String getProfLname() {
		return profLname;
	}
	/**
	 * @param profLname the profLname to set
	 */
	public void setProfLname(String profLname) {
		this.profLname = profLname;
	}
	/**
	 * @return the profRefId
	 */
	public String getProfRefId() {
		return profRefId;
	}
	/**
	 * @param profRefId the profRefId to set
	 */
	public void setProfRefId(String profRefId) {
		this.profRefId = profRefId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}
	public String getProfileEmpName() {
		return profileEmpName;
	}
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getEditDetail() {
		return editDetail;
	}
	public void setEditDetail(String editDetail) {
		this.editDetail = editDetail;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/** Method to get Address to Property in JSP
	 * @return address
	 */
		public String getAbbrAdd1() {
		return abbrAdd1;
	}
	public void setAbbrAdd1(String abbrAdd1) {
		this.abbrAdd1 = abbrAdd1;
	}
	public String getAbbrAdd2() {
		return abbrAdd2;
	}
	public void setAbbrAdd2(String abbrAdd2) {
		this.abbrAdd2 = abbrAdd2;
	}
	public String getAbbrAdd3() {
		return abbrAdd3;
	}
	public void setAbbrAdd3(String abbrAdd3) {
		this.abbrAdd3 = abbrAdd3;
	}
	public String getAbbrOccupation() {
		return abbrOccupation;
	}
	public void setAbbrOccupation(String abbrOccupation) {
		this.abbrOccupation = abbrOccupation;
	}
	public String getAbbrCompany() {
		return abbrCompany;
	}
	public void setAbbrCompany(String abbrCompany) {
		this.abbrCompany = abbrCompany;
	}
	public String getAbbrOtherInfo() {
		return abbrOtherInfo;
	}
	public void setAbbrOtherInfo(String abbrOtherInfo) {
		this.abbrOtherInfo = abbrOtherInfo;
	}
	public String getAbbrEmailId() {
		return abbrEmailId;
	}
	public void setAbbrEmailId(String abbrEmailId) {
		this.abbrEmailId = abbrEmailId;
	}
}
