package org.paradyne.bean.CR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.lib.BeanBase;
/*
 * 
 * Date:11.06.2012
 */
public class AccountMaster extends BeanBase {
	
	  private String fromDate = "";
	  private String toDate = "";
	  private String report = "";
	  
	private String modeLength="false";
	private String totalRecords="";
	String isActive;
	ArrayList titleList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String address;
	private String hdeleteCode;
	private String accountCode = "";
	private String businessName = "";
	private String accountId = "";
	private String isParent = "";
	private String parentCode = "";
	private String parentName = "";
	private String cityId = "";
	private String cityName = "";
	private String countryName = "";
	private String stateName = "";
	private String zipCode = "";
	private String contactName = "";
	private String emailAddress = "";
	ArrayList iteratorList = null;
	private boolean listLength = false;
	private String recordCreatedBy = ""; 
	private String recordCreatedOn = "";
	private String recordModifiedBy = ""; 
	private String recordModifiedOn = "";
	private String hiddenCheckBoxFlag = "";
	private String informCode = "";
	private String informToken = "";
	private String informName = "";
	private String reportCode = "";
	private String reportName="";
	private String keepInformToCode = "";
	private String keepInformToName = "";
	private String keepInformToToken = "";
	private ArrayList keepInformedList;
	
	private String firstName = "";
	private String lastName = "";
	private String emailClientAddress = "";
	private String cellNumber = "";
	private String isClientActive = "";
	ArrayList clientUserList = null;
	private String ittClientUserId = "";
	private String ittFirstName = "";
	private String ittLastName = "";
	private String ittEmailId = "";
	private String ittCellNumber = "";
	private String ittIsActive = "";
	private String ittCreatedOn = "";
	private String paracode = "";
	private String myPageInProcess = "";
	private String ittAccountCode = "";
	private String ittAccountName = "";
	private String parentClientCode = "";
	private String parentClientName = "";
	HashMap businessHashmap;
	private String businessType = "";
	private String hiddenSearchMessage = "";
	private String searchMessage = "";
	private String ittSubAccountName = "";
	private String ittSubAccountId = "";
	private String uploadFileName = "";
	private String dataPath = "";
	private String ittModifiedOn = "";
	private List<AccountMaster> activityLogIterator;
	private String hiddenClientActive = "";
	private String hiddenActiveFlag = "";
	private String password = "";
	private String isPartWaitTimeChecked = "";
	private String hiddenPartWaitTimeFlag = "";
	private String crmName = "";
	private String crmToken = "";
	private String crmCode = "";
	
	
	/**
	 * rptType.
	 */
	private String rptType = "";
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the hiddenActiveFlag
	 */
	public String getHiddenActiveFlag() {
		return hiddenActiveFlag;
	}
	/**
	 * @param hiddenActiveFlag the hiddenActiveFlag to set
	 */
	public void setHiddenActiveFlag(String hiddenActiveFlag) {
		this.hiddenActiveFlag = hiddenActiveFlag;
	}
	/**
	 * @return the hiddenClientActive
	 */
	public String getHiddenClientActive() {
		return hiddenClientActive;
	}
	/**
	 * @param hiddenClientActive the hiddenClientActive to set
	 */
	public void setHiddenClientActive(String hiddenClientActive) {
		this.hiddenClientActive = hiddenClientActive;
	}
	/**
	 * @return the ittModifiedOn
	 */
	public String getIttModifiedOn() {
		return ittModifiedOn;
	}
	/**
	 * @param ittModifiedOn the ittModifiedOn to set
	 */
	public void setIttModifiedOn(String ittModifiedOn) {
		this.ittModifiedOn = ittModifiedOn;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
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
	 * @return the hiddenSearchMessage
	 */
	public String getHiddenSearchMessage() {
		return hiddenSearchMessage;
	}
	/**
	 * @param hiddenSearchMessage the hiddenSearchMessage to set
	 */
	public void setHiddenSearchMessage(String hiddenSearchMessage) {
		this.hiddenSearchMessage = hiddenSearchMessage;
	}
	/**
	 * @return the searchMessage
	 */
	public String getSearchMessage() {
		return searchMessage;
	}
	/**
	 * @param searchMessage the searchMessage to set
	 */
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	/**
	 * @return the businessHashmap
	 */
	public HashMap getBusinessHashmap() {
		return businessHashmap;
	}
	/**
	 * @param businessHashmap the businessHashmap to set
	 */
	public void setBusinessHashmap(HashMap businessHashmap) {
		this.businessHashmap = businessHashmap;
	}
	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * @return the parentClientCode
	 */
	public String getParentClientCode() {
		return parentClientCode;
	}
	/**
	 * @param parentClientCode the parentClientCode to set
	 */
	public void setParentClientCode(String parentClientCode) {
		this.parentClientCode = parentClientCode;
	}
	/**
	 * @return the parentClientName
	 */
	public String getParentClientName() {
		return parentClientName;
	}
	/**
	 * @param parentClientName the parentClientName to set
	 */
	public void setParentClientName(String parentClientName) {
		this.parentClientName = parentClientName;
	}
	/**
	 * @return the ittAccountName
	 */
	public String getIttAccountName() {
		return ittAccountName;
	}
	/**
	 * @param ittAccountName the ittAccountName to set
	 */
	public void setIttAccountName(String ittAccountName) {
		this.ittAccountName = ittAccountName;
	}
	/**
	 * @return the ittAccountCode
	 */
	public String getIttAccountCode() {
		return ittAccountCode;
	}
	/**
	 * @param ittAccountCode the ittAccountCode to set
	 */
	public void setIttAccountCode(String ittAccountCode) {
		this.ittAccountCode = ittAccountCode;
	}
	/**
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the emailClientAddress
	 */
	public String getEmailClientAddress() {
		return emailClientAddress;
	}
	/**
	 * @param emailClientAddress the emailClientAddress to set
	 */
	public void setEmailClientAddress(String emailClientAddress) {
		this.emailClientAddress = emailClientAddress;
	}
	/**
	 * @return the cellNumber
	 */
	public String getCellNumber() {
		return cellNumber;
	}
	/**
	 * @param cellNumber the cellNumber to set
	 */
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	/**
	 * @return the isClientActive
	 */
	public String getIsClientActive() {
		return isClientActive;
	}
	/**
	 * @param isClientActive the isClientActive to set
	 */
	public void setIsClientActive(String isClientActive) {
		this.isClientActive = isClientActive;
	}
	/**
	 * @return the clientUserList
	 */
	public ArrayList getClientUserList() {
		return clientUserList;
	}
	/**
	 * @param clientUserList the clientUserList to set
	 */
	public void setClientUserList(ArrayList clientUserList) {
		this.clientUserList = clientUserList;
	}
	/**
	 * @return the ittClientUserId
	 */
	public String getIttClientUserId() {
		return ittClientUserId;
	}
	/**
	 * @param ittClientUserId the ittClientUserId to set
	 */
	public void setIttClientUserId(String ittClientUserId) {
		this.ittClientUserId = ittClientUserId;
	}
	/**
	 * @return the ittFirstName
	 */
	public String getIttFirstName() {
		return ittFirstName;
	}
	/**
	 * @param ittFirstName the ittFirstName to set
	 */
	public void setIttFirstName(String ittFirstName) {
		this.ittFirstName = ittFirstName;
	}
	/**
	 * @return the ittLastName
	 */
	public String getIttLastName() {
		return ittLastName;
	}
	/**
	 * @param ittLastName the ittLastName to set
	 */
	public void setIttLastName(String ittLastName) {
		this.ittLastName = ittLastName;
	}
	/**
	 * @return the ittEmailId
	 */
	public String getIttEmailId() {
		return ittEmailId;
	}
	/**
	 * @param ittEmailId the ittEmailId to set
	 */
	public void setIttEmailId(String ittEmailId) {
		this.ittEmailId = ittEmailId;
	}
	/**
	 * @return the ittCellNumber
	 */
	public String getIttCellNumber() {
		return ittCellNumber;
	}
	/**
	 * @param ittCellNumber the ittCellNumber to set
	 */
	public void setIttCellNumber(String ittCellNumber) {
		this.ittCellNumber = ittCellNumber;
	}
	/**
	 * @return the ittIsActive
	 */
	public String getIttIsActive() {
		return ittIsActive;
	}
	/**
	 * @param ittIsActive the ittIsActive to set
	 */
	public void setIttIsActive(String ittIsActive) {
		this.ittIsActive = ittIsActive;
	}
	/**
	 * @return the ittCreatedOn
	 */
	public String getIttCreatedOn() {
		return ittCreatedOn;
	}
	/**
	 * @param ittCreatedOn the ittCreatedOn to set
	 */
	public void setIttCreatedOn(String ittCreatedOn) {
		this.ittCreatedOn = ittCreatedOn;
	}
	/**
	 * @return the keepInformedList
	 */
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	/**
	 * @param keepInformedList the keepInformedList to set
	 */
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	/**
	 * @return the keepInformToCode
	 */
	public String getKeepInformToCode() {
		return keepInformToCode;
	}
	/**
	 * @param keepInformToCode the keepInformToCode to set
	 */
	public void setKeepInformToCode(String keepInformToCode) {
		this.keepInformToCode = keepInformToCode;
	}
	/**
	 * @return the keepInformToName
	 */
	public String getKeepInformToName() {
		return keepInformToName;
	}
	/**
	 * @param keepInformToName the keepInformToName to set
	 */
	public void setKeepInformToName(String keepInformToName) {
		this.keepInformToName = keepInformToName;
	}
	/**
	 * @return the keepInformToToken
	 */
	public String getKeepInformToToken() {
		return keepInformToToken;
	}
	/**
	 * @param keepInformToToken the keepInformToToken to set
	 */
	public void setKeepInformToToken(String keepInformToToken) {
		this.keepInformToToken = keepInformToToken;
	}
	/**
	 * @return the reportCode
	 */
	public String getReportCode() {
		return reportCode;
	}
	/**
	 * @param reportCode the reportCode to set
	 */
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}
	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	/**
	 * @return the informCode
	 */
	public String getInformCode() {
		return informCode;
	}
	/**
	 * @param informCode the informCode to set
	 */
	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}
	/**
	 * @return the informToken
	 */
	public String getInformToken() {
		return informToken;
	}
	/**
	 * @param informToken the informToken to set
	 */
	public void setInformToken(String informToken) {
		this.informToken = informToken;
	}
	/**
	 * @return the informName
	 */
	public String getInformName() {
		return informName;
	}
	/**
	 * @param informName the informName to set
	 */
	public void setInformName(String informName) {
		this.informName = informName;
	}
	/**
	 * @return the hiddenCheckBoxFlag
	 */
	public String getHiddenCheckBoxFlag() {
		return hiddenCheckBoxFlag;
	}
	/**
	 * @param hiddenCheckBoxFlag the hiddenCheckBoxFlag to set
	 */
	public void setHiddenCheckBoxFlag(String hiddenCheckBoxFlag) {
		this.hiddenCheckBoxFlag = hiddenCheckBoxFlag;
	}
	/**
	 * @return the iteratorList
	 */
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	/**
	 * @param iteratorList the iteratorList to set
	 */
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	/**
	 * @return the listLength
	 */
	public boolean isListLength() {
		return listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(boolean listLength) {
		this.listLength = listLength;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the titleList
	 */
	public ArrayList getTitleList() {
		return titleList;
	}
	/**
	 * @param titleList the titleList to set
	 */
	public void setTitleList(ArrayList titleList) {
		this.titleList = titleList;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}
	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the recordCreatedBy
	 */
	public String getRecordCreatedBy() {
		return recordCreatedBy;
	}
	/**
	 * @param recordCreatedBy the recordCreatedBy to set
	 */
	public void setRecordCreatedBy(String recordCreatedBy) {
		this.recordCreatedBy = recordCreatedBy;
	}
	/**
	 * @return the recordCreatedOn
	 */
	public String getRecordCreatedOn() {
		return recordCreatedOn;
	}
	/**
	 * @param recordCreatedOn the recordCreatedOn to set
	 */
	public void setRecordCreatedOn(String recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}
	/**
	 * @return the recordModifiedBy
	 */
	public String getRecordModifiedBy() {
		return recordModifiedBy;
	}
	/**
	 * @param recordModifiedBy the recordModifiedBy to set
	 */
	public void setRecordModifiedBy(String recordModifiedBy) {
		this.recordModifiedBy = recordModifiedBy;
	}
	/**
	 * @return the recordModifiedOn
	 */
	public String getRecordModifiedOn() {
		return recordModifiedOn;
	}
	/**
	 * @param recordModifiedOn the recordModifiedOn to set
	 */
	public void setRecordModifiedOn(String recordModifiedOn) {
		this.recordModifiedOn = recordModifiedOn;
	}
	/**
	 * @return the ittSubAccountName
	 */
	public String getIttSubAccountName() {
		return ittSubAccountName;
	}
	/**
	 * @param ittSubAccountName the ittSubAccountName to set
	 */
	public void setIttSubAccountName(String ittSubAccountName) {
		this.ittSubAccountName = ittSubAccountName;
	}
	/**
	 * @return the ittSubAccountId
	 */
	public String getIttSubAccountId() {
		return ittSubAccountId;
	}
	/**
	 * @param ittSubAccountId the ittSubAccountId to set
	 */
	public void setIttSubAccountId(String ittSubAccountId) {
		this.ittSubAccountId = ittSubAccountId;
	}
	/**
	 * @return the activityLogIterator
	 */
	public List<AccountMaster> getActivityLogIterator() {
		return activityLogIterator;
	}
	/**
	 * @param activityLogIterator the activityLogIterator to set
	 */
	public void setActivityLogIterator(List<AccountMaster> activityLogIterator) {
		this.activityLogIterator = activityLogIterator;
	}
	/**
	 * @return the rptType
	 */
	public String getRptType() {
		return rptType;
	}
	/**
	 * @param rptType the rptType to set
	 */
	public void setRptType(String rptType) {
		this.rptType = rptType;
	}
	/**
	 * @return the isPartWaitTimeChecked
	 */
	public String getIsPartWaitTimeChecked() {
		return isPartWaitTimeChecked;
	}
	/**
	 * @param isPartWaitTimeChecked the isPartWaitTimeChecked to set
	 */
	public void setIsPartWaitTimeChecked(String isPartWaitTimeChecked) {
		this.isPartWaitTimeChecked = isPartWaitTimeChecked;
	}
	/**
	 * @return the hiddenPartWaitTimeFlag
	 */
	public String getHiddenPartWaitTimeFlag() {
		return hiddenPartWaitTimeFlag;
	}
	/**
	 * @param hiddenPartWaitTimeFlag the hiddenPartWaitTimeFlag to set
	 */
	public void setHiddenPartWaitTimeFlag(String hiddenPartWaitTimeFlag) {
		this.hiddenPartWaitTimeFlag = hiddenPartWaitTimeFlag;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}
	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}
	/**
	 * @return the crmName
	 */
	public String getCrmName() {
		return crmName;
	}
	/**
	 * @param crmName the crmName to set
	 */
	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}
	/**
	 * @return the crmToken
	 */
	public String getCrmToken() {
		return crmToken;
	}
	/**
	 * @param crmToken the crmToken to set
	 */
	public void setCrmToken(String crmToken) {
		this.crmToken = crmToken;
	}
	/**
	 * @return the crmCode
	 */
	public String getCrmCode() {
		return crmCode;
	}
	/**
	 * @param crmCode the crmCode to set
	 */
	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}
	
	

}
