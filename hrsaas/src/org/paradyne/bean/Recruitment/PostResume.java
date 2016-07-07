/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0540
 *
 */
public class PostResume extends BeanBase {
	private String dataPath;
	private String formValue;
	private String listValue;
	private int objectLength = 0;
	
	private ArrayList<Object> formValueList = new ArrayList<Object>();
	private ArrayList<Object> valuesList = new ArrayList<Object>();
	
	private String resumeCode;
	private String requisitionCode;
	private String requisitionName;
	private String position;
	private String hiringManager;
	private String hiringManagerCode;
	private String requisitionDate;
	private String jobDescription;
	private String candCode;
	private String candiName;
	private String candidateNotIn = "";
	
	private String candFirstName;
	private String candLastName;
	private String emailId;
	private String contactNo;
	private String year;
	private String month;
	private String resumeDate;
	private String location;
	private String locationCode;
	private String state;
	private String stateCode;
	private String country;
	private String countryCode;
	private String panNo;
	private String passport;
	private String mobile;
	private String finalduplicateFlag="false";
	
	private String currentCtc;
	private String expectedCtc;
	private String dob;
	private String gender;
	private String hiddenGender;
	private String relocate;
	private String maritalStatus;
	private String hiddenMaritalStatus;
	private String uploadFileName;
	
	private String candidateName;
	private String candidateCode;
	private String candExperience;
	private String postedDate;
	private String ctc;
	private String candGender;
	private String status;
	private String candPosition;
	
	private String firstNameIterator;
	private String lastNameIterator;
	private String emailIdIterator;
	private String contactNoIterator;
	private String yearIterator;
	private String monthIterator;
	private String expCtcIterator;
	private String relocateIterator;
	private String uploadIterator;
	private String locationIterator;
	private String stateIterator;
	private String dobIterator;
	private String genderIterator;
	private String maritalStatusIterator;
	private String checkBoxFlag = "false";
	
	private String flagAdd="false";
	private ArrayList<Object> candidateList = new ArrayList<Object>();
	
	private String formName;
	private String addCandidateFlag = "false";
	private String postFlag = "false";
	private String forwardFlag = "false";
	private String deleteFlag = "false";
	
	private String RefFlag = "";
	private String RefCanTestFlag = "";
	private boolean referalFlag =false;

	private String partnerFlag="false";
	private String reqApprStatusSer;
	private String selectCand;
	
	// FILDS FOR HALF KEEP INFORMED TO
	private String employeeName = "";
	private String employeeId = "";
	private String employeeToken = "";
	
	TreeMap<String, String> roundTypeMap =null ;
	TreeMap<String, String> interviewRoundTypeMap =null ;
	
	public TreeMap<String, String> getRoundTypeMap() {
		return roundTypeMap;
	}
	public void setRoundTypeMap(TreeMap<String, String> roundTypeMap) {
		this.roundTypeMap = roundTypeMap;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	
	
	public String getPartnerFlag() {
		return partnerFlag;
	}
	public void setPartnerFlag(String partnerFlag) {
		this.partnerFlag = partnerFlag;
	}
	
	public boolean isReferalFlag() {
		return referalFlag;
	}
	public void setReferalFlag(boolean referalFlag) {
		this.referalFlag = referalFlag;
	}
	
	
	/**
	 * @return the deleteFlag
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return the forwardFlag
	 */
	public String getForwardFlag() {
		return forwardFlag;
	}
	/**
	 * @param forwardFlag the forwardFlag to set
	 */
	public void setForwardFlag(String forwardFlag) {
		this.forwardFlag = forwardFlag;
	}
	/**
	 * @return the addCandidateFlag
	 */
	public String getAddCandidateFlag() {
		return addCandidateFlag;
	}
	/**
	 * @param addCandidateFlag the addCandidateFlag to set
	 */
	public void setAddCandidateFlag(String addCandidateFlag) {
		this.addCandidateFlag = addCandidateFlag;
	}
	/**
	 * @return the resumeCode
	 */
	public String getResumeCode() {
		return resumeCode;
	}
	/**
	 * @param resumeCode the resumeCode to set
	 */
	public void setResumeCode(String resumeCode) {
		this.resumeCode = resumeCode;
	}
	/**
	 * @return the requisitionCode
	 */
	public String getRequisitionCode() {
		return requisitionCode;
	}
	/**
	 * @param requisitionCode the requisitionCode to set
	 */
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	/**
	 * @return the requisitionName
	 */
	public String getRequisitionName() {
		return requisitionName;
	}
	/**
	 * @param requisitionName the requisitionName to set
	 */
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the hiringManager
	 */
	public String getHiringManager() {
		return hiringManager;
	}
	/**
	 * @param hiringManager the hiringManager to set
	 */
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}
	/**
	 * @return the candFirstName
	 */
	public String getCandFirstName() {
		return candFirstName;
	}
	/**
	 * @param candFirstName the candFirstName to set
	 */
	public void setCandFirstName(String candFirstName) {
		this.candFirstName = candFirstName;
	}
	/**
	 * @return the candLastName
	 */
	public String getCandLastName() {
		return candLastName;
	}
	/**
	 * @param candLastName the candLastName to set
	 */
	public void setCandLastName(String candLastName) {
		this.candLastName = candLastName;
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
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the resumeDate
	 */
	public String getResumeDate() {
		return resumeDate;
	}
	/**
	 * @param resumeDate the resumeDate to set
	 */
	public void setResumeDate(String resumeDate) {
		this.resumeDate = resumeDate;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}
	/**
	 * @param locationCode the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	/**
	 * @return the currentCtc
	 */
	public String getCurrentCtc() {
		return currentCtc;
	}
	/**
	 * @param currentCtc the currentCtc to set
	 */
	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}
	/**
	 * @return the expectedCtc
	 */
	public String getExpectedCtc() {
		return expectedCtc;
	}
	/**
	 * @param expectedCtc the expectedCtc to set
	 */
	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the relocate
	 */
	public String getRelocate() {
		return relocate;
	}
	/**
	 * @param relocate the relocate to set
	 */
	public void setRelocate(String relocate) {
		this.relocate = relocate;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}
	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	/**
	 * @return the candidateCode
	 */
	public String getCandidateCode() {
		return candidateCode;
	}
	/**
	 * @param candidateCode the candidateCode to set
	 */
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	/**
	 * @return the candExperience
	 */
	public String getCandExperience() {
		return candExperience;
	}
	/**
	 * @param candExperience the candExperience to set
	 */
	public void setCandExperience(String candExperience) {
		this.candExperience = candExperience;
	}
	/**
	 * @return the postedDate
	 */
	public String getPostedDate() {
		return postedDate;
	}
	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	/**
	 * @return the ctc
	 */
	public String getCtc() {
		return ctc;
	}
	/**
	 * @param ctc the ctc to set
	 */
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}
	/**
	 * @return the candGender
	 */
	public String getCandGender() {
		return candGender;
	}
	/**
	 * @param candGender the candGender to set
	 */
	public void setCandGender(String candGender) {
		this.candGender = candGender;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the candidateList
	 */
	public ArrayList<Object> getCandidateList() {
		return candidateList;
	}
	/**
	 * @param candidateList the candidateList to set
	 */
	public void setCandidateList(ArrayList<Object> candidateList) {
		this.candidateList = candidateList;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the postFlag
	 */
	public String getPostFlag() {
		return postFlag;
	}
	/**
	 * @param postFlag the postFlag to set
	 */
	public void setPostFlag(String postFlag) {
		this.postFlag = postFlag;
	}
	/**
	 * @return the firstNameIterator
	 */
	public String getFirstNameIterator() {
		return firstNameIterator;
	}
	/**
	 * @param firstNameIterator the firstNameIterator to set
	 */
	public void setFirstNameIterator(String firstNameIterator) {
		this.firstNameIterator = firstNameIterator;
	}
	/**
	 * @return the lastNameIterator
	 */
	public String getLastNameIterator() {
		return lastNameIterator;
	}
	/**
	 * @param lastNameIterator the lastNameIterator to set
	 */
	public void setLastNameIterator(String lastNameIterator) {
		this.lastNameIterator = lastNameIterator;
	}
	/**
	 * @return the emailIdIterator
	 */
	public String getEmailIdIterator() {
		return emailIdIterator;
	}
	/**
	 * @param emailIdIterator the emailIdIterator to set
	 */
	public void setEmailIdIterator(String emailIdIterator) {
		this.emailIdIterator = emailIdIterator;
	}
	/**
	 * @return the contactNoIterator
	 */
	public String getContactNoIterator() {
		return contactNoIterator;
	}
	/**
	 * @param contactNoIterator the contactNoIterator to set
	 */
	public void setContactNoIterator(String contactNoIterator) {
		this.contactNoIterator = contactNoIterator;
	}
	/**
	 * @return the yearIterator
	 */
	public String getYearIterator() {
		return yearIterator;
	}
	/**
	 * @param yearIterator the yearIterator to set
	 */
	public void setYearIterator(String yearIterator) {
		this.yearIterator = yearIterator;
	}
	/**
	 * @return the monthIterator
	 */
	public String getMonthIterator() {
		return monthIterator;
	}
	/**
	 * @param monthIterator the monthIterator to set
	 */
	public void setMonthIterator(String monthIterator) {
		this.monthIterator = monthIterator;
	}
	/**
	 * @return the expCtcIterator
	 */
	public String getExpCtcIterator() {
		return expCtcIterator;
	}
	/**
	 * @param expCtcIterator the expCtcIterator to set
	 */
	public void setExpCtcIterator(String expCtcIterator) {
		this.expCtcIterator = expCtcIterator;
	}
	/**
	 * @return the relocateIterator
	 */
	public String getRelocateIterator() {
		return relocateIterator;
	}
	/**
	 * @param relocateIterator the relocateIterator to set
	 */
	public void setRelocateIterator(String relocateIterator) {
		this.relocateIterator = relocateIterator;
	}
	/**
	 * @return the uploadIterator
	 */
	public String getUploadIterator() {
		return uploadIterator;
	}
	/**
	 * @param uploadIterator the uploadIterator to set
	 */
	public void setUploadIterator(String uploadIterator) {
		this.uploadIterator = uploadIterator;
	}
	/**
	 * @return the locationIterator
	 */
	public String getLocationIterator() {
		return locationIterator;
	}
	/**
	 * @param locationIterator the locationIterator to set
	 */
	public void setLocationIterator(String locationIterator) {
		this.locationIterator = locationIterator;
	}
	/**
	 * @return the dobIterator
	 */
	public String getDobIterator() {
		return dobIterator;
	}
	/**
	 * @param dobIterator the dobIterator to set
	 */
	public void setDobIterator(String dobIterator) {
		this.dobIterator = dobIterator;
	}
	/**
	 * @return the genderIterator
	 */
	public String getGenderIterator() {
		return genderIterator;
	}
	/**
	 * @param genderIterator the genderIterator to set
	 */
	public void setGenderIterator(String genderIterator) {
		this.genderIterator = genderIterator;
	}
	/**
	 * @return the maritalStatusIterator
	 */
	public String getMaritalStatusIterator() {
		return maritalStatusIterator;
	}
	/**
	 * @param maritalStatusIterator the maritalStatusIterator to set
	 */
	public void setMaritalStatusIterator(String maritalStatusIterator) {
		this.maritalStatusIterator = maritalStatusIterator;
	}
	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return the requisitionDate
	 */
	public String getRequisitionDate() {
		return requisitionDate;
	}
	/**
	 * @param requisitionDate the requisitionDate to set
	 */
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return the stateIterator
	 */
	public String getStateIterator() {
		return stateIterator;
	}
	/**
	 * @param stateIterator the stateIterator to set
	 */
	public void setStateIterator(String stateIterator) {
		this.stateIterator = stateIterator;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the candCode
	 */
	public String getCandCode() {
		return candCode;
	}
	/**
	 * @param candCode the candCode to set
	 */
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	/**
	 * @return the checkBoxFlag
	 */
	public String getCheckBoxFlag() {
		return checkBoxFlag;
	}
	/**
	 * @param checkBoxFlag the checkBoxFlag to set
	 */
	public void setCheckBoxFlag(String checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
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
	 * @return the formValuesList
	 */
	public ArrayList<Object> getFormValueList() {
		return formValueList;
	}
	/**
	 * @param formValuesList the formValuesList to set
	 */
	public void setFormValueList(ArrayList<Object> formValueList) {
		this.formValueList = formValueList;
	}
	/**
	 * @return the formValue
	 */
	public String getFormValue() {
		return formValue;
	}
	/**
	 * @param formValue the formValue to set
	 */
	public void setFormValue(String formValue) {
		this.formValue = formValue;
	}
	/**
	 * @return the hiringManagerCode
	 */
	public String getHiringManagerCode() {
		return hiringManagerCode;
	}
	/**
	 * @param hiringManagerCode the hiringManagerCode to set
	 */
	public void setHiringManagerCode(String hiringManagerCode) {
		this.hiringManagerCode = hiringManagerCode;
	}
	/**
	 * @return the valuesList
	 */
	public ArrayList<Object> getValuesList() {
		return valuesList;
	}
	/**
	 * @param valuesList the valuesList to set
	 */
	public void setValuesList(ArrayList<Object> valuesList) {
		this.valuesList = valuesList;
	}
	/**
	 * @return the listValue
	 */
	public String getListValue() {
		return listValue;
	}
	/**
	 * @param listValue the listValue to set
	 */
	public void setListValue(String listValue) {
		this.listValue = listValue;
	}
	/**
	 * @return the objectLength
	 */
	public int getObjectLength() {
		return objectLength;
	}
	/**
	 * @param objectLength the objectLength to set
	 */
	public void setObjectLength(int objectLength) {
		this.objectLength = objectLength;
	}
	/**
	 * @return the candPosition
	 */
	public String getCandPosition() {
		return candPosition;
	}
	/**
	 * @param candPosition the candPosition to set
	 */
	public void setCandPosition(String candPosition) {
		this.candPosition = candPosition;
	}
	/**
	 * @return the hiddenGender
	 */
	public String getHiddenGender() {
		return hiddenGender;
	}
	/**
	 * @param hiddenGender the hiddenGender to set
	 */
	public void setHiddenGender(String hiddenGender) {
		this.hiddenGender = hiddenGender;
	}
	/**
	 * @return the hiddenMaritalStatus
	 */
	public String getHiddenMaritalStatus() {
		return hiddenMaritalStatus;
	}
	/**
	 * @param hiddenMaritalStatus the hiddenMaritalStatus to set
	 */
	public void setHiddenMaritalStatus(String hiddenMaritalStatus) {
		this.hiddenMaritalStatus = hiddenMaritalStatus;
	}
	/**
	 * @return the candiName
	 */
	public String getCandiName() {
		return candiName;
	}
	/**
	 * @param candiName the candiName to set
	 */
	public void setCandiName(String candiName) {
		this.candiName = candiName;
	}
	/**
	 * @return the candidateNotIn
	 */
	public String getCandidateNotIn() {
		return candidateNotIn;
	}
	/**
	 * @param candidateNotIn the candidateNotIn to set
	 */
	public void setCandidateNotIn(String candidateNotIn) {
		this.candidateNotIn = candidateNotIn;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
	public String getFinalduplicateFlag() {
		return finalduplicateFlag;
	}
	public void setFinalduplicateFlag(String finalduplicateFlag) {
		this.finalduplicateFlag = finalduplicateFlag;
	}
	
	public String getReqApprStatusSer() {
		return reqApprStatusSer;
	}
	public void setReqApprStatusSer(String reqApprStatusSer) {
		this.reqApprStatusSer = reqApprStatusSer;
	}
	public String getFlagAdd() {
		return flagAdd;
	}
	public void setFlagAdd(String flagAdd) {
		this.flagAdd = flagAdd;
	}
	public String getSelectCand() {
		return selectCand;
	}
	public void setSelectCand(String selectCand) {
		this.selectCand = selectCand;
	}
	public String getRefFlag() {
		return RefFlag;
	}
	public void setRefFlag(String refFlag) {
		RefFlag = refFlag;
	}
	public String getRefCanTestFlag() {
		return RefCanTestFlag;
	}
	public void setRefCanTestFlag(String refCanTestFlag) {
		RefCanTestFlag = refCanTestFlag;
	}
	public TreeMap<String, String> getInterviewRoundTypeMap() {
		return interviewRoundTypeMap;
	}
	public void setInterviewRoundTypeMap(
			TreeMap<String, String> interviewRoundTypeMap) {
		this.interviewRoundTypeMap = interviewRoundTypeMap;
	}
}
