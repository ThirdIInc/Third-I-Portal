package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class CandidateSearch extends BeanBase {

	private String searchCode;
	private String keywordSearch;
	private String searchCriteria;
	private String shortListed;
	private String rejected;
	private String newResume;
	private String showAll;
	
	private String searchFlag = "false";
	
	private String requisitionName;
	private String requisitionCode;
	private String position;
	private String positionCode;
	private String requisitionDate;
	private String hiringManager;
	private String jobDescription;
	private String sourceOfResume;
	private String positioningDate;
	private String fromDate;
	private String toDate;
	private String minExperience;
	private String maxExperience;
	private String firstName;
	private String lastName;
	private String gender;
	private String maritalStatus;
	private String resumeName="";
	
	private String hiringManagerCode;
	
	private String status;
	
	private String city;
	private String cityFlag = "false";
	private HashMap<Object, Object> cityMap = new HashMap<Object, Object>();
	private String state;
	private String stateFlag = "false";
	private HashMap<Object, Object> stateMap = new HashMap<Object, Object>();
	private String country;
	private String countryFlag = "false";
	private HashMap<Object, Object> countryMap = new HashMap<Object, Object>();
	
	private String functionalArea;
	private String functionalAreaFlag = "false";
	private HashMap<Object, Object> functionalAreaMap = new HashMap<Object, Object>();
	private String industryType;
	private String industryFlag = "false";
	private HashMap<Object, Object> industryTypeMap = new HashMap<Object, Object>();
	
	private String qualification;
	private String qualificationFlag = "false";
	private HashMap<Object, Object> qualificationMap = new HashMap<Object, Object>();
	private String specialization;
	private String specializationFlag = "false";
	private HashMap<Object, Object> specializationMap = new HashMap<Object, Object>();
	private String skillSet;
	private String skillFlag = "false";
	private HashMap<Object, Object> skillSetMap = new HashMap<Object, Object>();
	private String sortOn;
	private String enableSearch;
	
	private ArrayList<Object> candidateList = new  ArrayList<Object>();
	private String candidateCode;
	private String candidateName;
	private String candExperience;
	private String postedDate;
	private String ctc;
	private String candGender;
	private String noData = "false";
	private boolean dashletFlag=false;
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
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
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}
	/**
	 * @param searchCode the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	/**
	 * @return the keywordSearch
	 */
	public String getKeywordSearch() {
		return keywordSearch;
	}
	/**
	 * @param keywordSearch the keywordSearch to set
	 */
	public void setKeywordSearch(String keywordSearch) {
		this.keywordSearch = keywordSearch;
	}
	/**
	 * @return the searchCriteria
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}
	/**
	 * @param searchCriteria the searchCriteria to set
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/**
	 * @return the shortListed
	 */
	public String getShortListed() {
		return shortListed;
	}
	/**
	 * @param shortListed the shortListed to set
	 */
	public void setShortListed(String shortListed) {
		this.shortListed = shortListed;
	}
	/**
	 * @return the rejected
	 */
	public String getRejected() {
		return rejected;
	}
	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	/**
	 * @return the newResume
	 */
	public String getNewResume() {
		return newResume;
	}
	/**
	 * @param newResume the newResume to set
	 */
	public void setNewResume(String newResume) {
		this.newResume = newResume;
	}
	/**
	 * @return the showAll
	 */
	public String getShowAll() {
		return showAll;
	}
	/**
	 * @param showAll the showAll to set
	 */
	public void setShowAll(String showAll) {
		this.showAll = showAll;
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
	 * @return the positionCode
	 */
	public String getPositionCode() {
		return positionCode;
	}
	/**
	 * @param positionCode the positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	/**
	 * @return the sourceOfResume
	 */
	public String getSourceOfResume() {
		return sourceOfResume;
	}
	/**
	 * @param sourceOfResume the sourceOfResume to set
	 */
	public void setSourceOfResume(String sourceOfResume) {
		this.sourceOfResume = sourceOfResume;
	}
	/**
	 * @return the positioningDate
	 */
	public String getPositioningDate() {
		return positioningDate;
	}
	/**
	 * @param positioningDate the positioningDate to set
	 */
	public void setPositioningDate(String positioningDate) {
		this.positioningDate = positioningDate;
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
	 * @return the minExperience
	 */
	public String getMinExperience() {
		return minExperience;
	}
	/**
	 * @param minExperience the minExperience to set
	 */
	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}
	/**
	 * @return the maxExperience
	 */
	public String getMaxExperience() {
		return maxExperience;
	}
	/**
	 * @param maxExperience the maxExperience to set
	 */
	public void setMaxExperience(String maxExperience) {
		this.maxExperience = maxExperience;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the functionalArea
	 */
	public String getFunctionalArea() {
		return functionalArea;
	}
	/**
	 * @param functionalArea the functionalArea to set
	 */
	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}
	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}
	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}
	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	/**
	 * @return the skillSet
	 */
	public String getSkillSet() {
		return skillSet;
	}
	/**
	 * @param skillSet the skillSet to set
	 */
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	/**
	 * @return the sortOn
	 */
	public String getSortOn() {
		return sortOn;
	}
	/**
	 * @param sortOn the sortOn to set
	 */
	public void setSortOn(String sortOn) {
		this.sortOn = sortOn;
	}
	/**
	 * @return the enableSearch
	 */
	public String getEnableSearch() {
		return enableSearch;
	}
	/**
	 * @param enableSearch the enableSearch to set
	 */
	public void setEnableSearch(String enableSearch) {
		this.enableSearch = enableSearch;
	}
	/**
	 * @return the cityMap
	 */
	public HashMap<Object, Object> getCityMap() {
		return cityMap;
	}
	/**
	 * @param cityMap the cityMap to set
	 */
	public void setCityMap(HashMap<Object, Object> cityMap) {
		this.cityMap = cityMap;
	}
	/**
	 * @return the stateMap
	 */
	public HashMap<Object, Object> getStateMap() {
		return stateMap;
	}
	/**
	 * @param stateMap the stateMap to set
	 */
	public void setStateMap(HashMap<Object, Object> stateMap) {
		this.stateMap = stateMap;
	}
	/**
	 * @return the countryMap
	 */
	public HashMap<Object, Object> getCountryMap() {
		return countryMap;
	}
	/**
	 * @param countryMap the countryMap to set
	 */
	public void setCountryMap(HashMap<Object, Object> countryMap) {
		this.countryMap = countryMap;
	}
	/**
	 * @return the functionalAreaMap
	 */
	public HashMap<Object, Object> getFunctionalAreaMap() {
		return functionalAreaMap;
	}
	/**
	 * @param functionalAreaMap the functionalAreaMap to set
	 */
	public void setFunctionalAreaMap(HashMap<Object, Object> functionalAreaMap) {
		this.functionalAreaMap = functionalAreaMap;
	}
	/**
	 * @return the industryTypeMap
	 */
	public HashMap<Object, Object> getIndustryTypeMap() {
		return industryTypeMap;
	}
	/**
	 * @param industryTypeMap the industryTypeMap to set
	 */
	public void setIndustryTypeMap(HashMap<Object, Object> industryTypeMap) {
		this.industryTypeMap = industryTypeMap;
	}
	/**
	 * @return the qualificationMap
	 */
	public HashMap<Object, Object> getQualificationMap() {
		return qualificationMap;
	}
	/**
	 * @param qualificationMap the qualificationMap to set
	 */
	public void setQualificationMap(HashMap<Object, Object> qualificationMap) {
		this.qualificationMap = qualificationMap;
	}
	/**
	 * @return the specializationMap
	 */
	public HashMap<Object, Object> getSpecializationMap() {
		return specializationMap;
	}
	/**
	 * @param specializationMap the specializationMap to set
	 */
	public void setSpecializationMap(HashMap<Object, Object> specializationMap) {
		this.specializationMap = specializationMap;
	}
	/**
	 * @return the skillSetMap
	 */
	public HashMap<Object, Object> getSkillSetMap() {
		return skillSetMap;
	}
	/**
	 * @param skillSetMap the skillSetMap to set
	 */
	public void setSkillSetMap(HashMap<Object, Object> skillSetMap) {
		this.skillSetMap = skillSetMap;
	}
	/**
	 * @return the cityFlag
	 */
	public String getCityFlag() {
		return cityFlag;
	}
	/**
	 * @param cityFlag the cityFlag to set
	 */
	public void setCityFlag(String cityFlag) {
		this.cityFlag = cityFlag;
	}
	/**
	 * @return the stateFlag
	 */
	public String getStateFlag() {
		return stateFlag;
	}
	/**
	 * @param stateFlag the stateFlag to set
	 */
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	/**
	 * @return the countryFlag
	 */
	public String getCountryFlag() {
		return countryFlag;
	}
	/**
	 * @param countryFlag the countryFlag to set
	 */
	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}
	/**
	 * @return the functionalAreaFlag
	 */
	public String getFunctionalAreaFlag() {
		return functionalAreaFlag;
	}
	/**
	 * @param functionalAreaFlag the functionalAreaFlag to set
	 */
	public void setFunctionalAreaFlag(String functionalAreaFlag) {
		this.functionalAreaFlag = functionalAreaFlag;
	}
	/**
	 * @return the industryFlag
	 */
	public String getIndustryFlag() {
		return industryFlag;
	}
	/**
	 * @param industryFlag the industryFlag to set
	 */
	public void setIndustryFlag(String industryFlag) {
		this.industryFlag = industryFlag;
	}
	/**
	 * @return the qualificationFlag
	 */
	public String getQualificationFlag() {
		return qualificationFlag;
	}
	/**
	 * @param qualificationFlag the qualificationFlag to set
	 */
	public void setQualificationFlag(String qualificationFlag) {
		this.qualificationFlag = qualificationFlag;
	}
	/**
	 * @return the specializationFlag
	 */
	public String getSpecializationFlag() {
		return specializationFlag;
	}
	/**
	 * @param specializationFlag the specializationFlag to set
	 */
	public void setSpecializationFlag(String specializationFlag) {
		this.specializationFlag = specializationFlag;
	}
	/**
	 * @return the skillFlag
	 */
	public String getSkillFlag() {
		return skillFlag;
	}
	/**
	 * @param skillFlag the skillFlag to set
	 */
	public void setSkillFlag(String skillFlag) {
		this.skillFlag = skillFlag;
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
	 * @return the searchFlag
	 */
	public String getSearchFlag() {
		return searchFlag;
	}
	/**
	 * @param searchFlag the searchFlag to set
	 */
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getHiringManagerCode() {
		return hiringManagerCode;
	}
	public void setHiringManagerCode(String hiringManagerCode) {
		this.hiringManagerCode = hiringManagerCode;
	}
	public boolean isDashletFlag() {
		return dashletFlag;
	}
	public void setDashletFlag(boolean dashletFlag) {
		this.dashletFlag = dashletFlag;
	}
	
}
