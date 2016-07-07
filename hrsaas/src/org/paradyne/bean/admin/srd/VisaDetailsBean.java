/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @modified by Priyanka. Kumbhar 
 *
 */
public class VisaDetailsBean extends BeanBase {
	/**
	 * Form Fields.
	 */
		private String empId = "";
		private String empToken = "";
		private String empName = "";
		private String branchName = "";
		private String designationName = "";
		private String visaNumber = "";
		private String country = "";
		private String visaType = "";
		private String visaEntry = "";
		private String issuePlace = "";
		String abbrIssuePlace=""; 
		private String issueAuthority = "";
		private String issueDate = "";
		private String validUpto = "";
		private String address = "";
		String abbrAddress="";
		String abbrCountry="";
		String noData = "";
		String passportNoData="";
		String isNotGeneralUser="false";
		
		//For Iterator
		private String visaNumberItt = "";
		private String countryItt = "";
		private String visaTypeItt = "";
		private String visaEntryItt = "";
		private String issuePlaceItt = "";
		private String issueAuthorityItt = "";
		private String issueDateItt = "";
		private String validUptoItt = "";
		private String addressItt = "";

		private String srNo = "";
		private String hiddenEdit = "";		
		private String displayFlag = "false";
		private String buttonFlag ="false";
		private String checkTabFlag="";
		private String hiddenDelete = "";	
	/**
	 * Hidden Fields.
	 */	
		private String visaCode = "";
		private String hdeleteCode = "";
		private String myPage = "";
		private String modeLength = "";
		private String totalNoOfRecords = "";
		private List<Object> visaDetailsList;
		private List<Object> recordsList;
 /**
  * Passport Details
  */		
	private String passport = "";
	private String	passportName = "";
	private String	fatherName = "";
	private String	motherName = "";
	private String	passportIssueFrom = "";
	private String	passportDateOfIssue = "";
	private String	passportExpDate = "";
	private String	passportUidNum = "";	

	private boolean insertFlag = false;
	private boolean updateFlag = false;
	private boolean deleteFlag = false;
	private boolean viewFlag = false;
	private boolean generalFlag = true;
	private boolean editFlag=false;
	private boolean addFlag=false;
	
	private String tabID="";
	String paraId = "";
	String uploadFileName = "";
	String profileEmpName="";
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getProfileEmpName() {
		return profileEmpName;
	}
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getVisaNumber() {
		return visaNumber;
	}
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getVisaEntry() {
		return visaEntry;
	}
	public void setVisaEntry(String visaEntry) {
		this.visaEntry = visaEntry;
	}
	public String getIssuePlace() {
		return issuePlace;
	}
	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}
	public String getIssueAuthority() {
		return issueAuthority;
	}
	public void setIssueAuthority(String issueAuthority) {
		this.issueAuthority = issueAuthority;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getValidUpto() {
		return validUpto;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getVisaCode() {
		return visaCode;
	}
	public void setVisaCode(String visaCode) {
		this.visaCode = visaCode;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
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
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	public List<Object> getVisaDetailsList() {
		return visaDetailsList;
	}
	public void setVisaDetailsList(List<Object> visaDetailsList) {
		this.visaDetailsList = visaDetailsList;
	}
	public List<Object> getRecordsList() {
		return recordsList;
	}
	public void setRecordsList(List<Object> recordsList) {
		this.recordsList = recordsList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getPassportName() {
		return passportName;
	}
	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getPassportIssueFrom() {
		return passportIssueFrom;
	}
	public void setPassportIssueFrom(String passportIssueFrom) {
		this.passportIssueFrom = passportIssueFrom;
	}
	public String getPassportDateOfIssue() {
		return passportDateOfIssue;
	}
	public void setPassportDateOfIssue(String passportDateOfIssue) {
		this.passportDateOfIssue = passportDateOfIssue;
	}
	public String getPassportExpDate() {
		return passportExpDate;
	}
	public void setPassportExpDate(String passportExpDate) {
		this.passportExpDate = passportExpDate;
	}
	public String getPassportUidNum() {
		return passportUidNum;
	}
	public void setPassportUidNum(String passportUidNum) {
		this.passportUidNum = passportUidNum;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getCheckTabFlag() {
		return checkTabFlag;
	}
	public void setCheckTabFlag(String checkTabFlag) {
		this.checkTabFlag = checkTabFlag;
	}
	public String getHiddenDelete() {
		return hiddenDelete;
	}
	public void setHiddenDelete(String hiddenDelete) {
		this.hiddenDelete = hiddenDelete;
	}
	public String getVisaNumberItt() {
		return visaNumberItt;
	}
	public void setVisaNumberItt(String visaNumberItt) {
		this.visaNumberItt = visaNumberItt;
	}
	public String getCountryItt() {
		return countryItt;
	}
	public void setCountryItt(String countryItt) {
		this.countryItt = countryItt;
	}
	public String getVisaTypeItt() {
		return visaTypeItt;
	}
	public void setVisaTypeItt(String visaTypeItt) {
		this.visaTypeItt = visaTypeItt;
	}
	public String getVisaEntryItt() {
		return visaEntryItt;
	}
	public void setVisaEntryItt(String visaEntryItt) {
		this.visaEntryItt = visaEntryItt;
	}
	public String getIssuePlaceItt() {
		return issuePlaceItt;
	}
	public void setIssuePlaceItt(String issuePlaceItt) {
		this.issuePlaceItt = issuePlaceItt;
	}
	public String getIssueAuthorityItt() {
		return issueAuthorityItt;
	}
	public void setIssueAuthorityItt(String issueAuthorityItt) {
		this.issueAuthorityItt = issueAuthorityItt;
	}
	public String getIssueDateItt() {
		return issueDateItt;
	}
	public void setIssueDateItt(String issueDateItt) {
		this.issueDateItt = issueDateItt;
	}
	public String getValidUptoItt() {
		return validUptoItt;
	}
	public void setValidUptoItt(String validUptoItt) {
		this.validUptoItt = validUptoItt;
	}
	public String getAddressItt() {
		return addressItt;
	}
	public void setAddressItt(String addressItt) {
		this.addressItt = addressItt;
	}
	public String getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

	public boolean isInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}
	public boolean isUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public boolean isViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}
	public boolean isGeneralFlag() {
		return generalFlag;
	}
	public void setGeneralFlag(boolean generalFlag) {
		this.generalFlag = generalFlag;
	}
	public String getTabID() {
		return tabID;
	}
	public void setTabID(String tabID) {
		this.tabID = tabID;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public boolean isAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getAbbrIssuePlace() {
		return abbrIssuePlace;
	}
	public void setAbbrIssuePlace(String abbrIssuePlace) {
		this.abbrIssuePlace = abbrIssuePlace;
	}
	public String getAbbrAddress() {
		return abbrAddress;
	}
	public void setAbbrAddress(String abbrAddress) {
		this.abbrAddress = abbrAddress;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getPassportNoData() {
		return passportNoData;
	}
	public void setPassportNoData(String passportNoData) {
		this.passportNoData = passportNoData;
	}
	public String getAbbrCountry() {
		return abbrCountry;
	}
	public void setAbbrCountry(String abbrCountry) {
		this.abbrCountry = abbrCountry;
	}
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	

}
