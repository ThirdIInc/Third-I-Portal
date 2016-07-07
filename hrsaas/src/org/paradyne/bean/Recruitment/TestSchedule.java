/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class TestSchedule extends BeanBase {
	
	private String requisitionCodeIterator;
	private String requisitionName;
	private String candidateName;
	private String candidateCode;
	private String position;
	private String contactPerson;
	private String venue="";
	private String conveyanceDetail="";
	private String testType;
	private String testRequirements="";
	private String testTemplate="";
	private String templateId="";
	private String hiringManagerIterator;
	private String hiringManagerCode;
	private String recruiterId;
	private String emailId;
	private String testDate;
	private String testTime;
	private String comments;
	private String checkBox;
	private String chk;
	private String contactNo;
	private String chkAll;
	private String cntPersonToken;
	private String requisitionCode;
	private String hiringManager;
	private String cntPersonId;
	private String testRequirementsHid = "";
	private String testReqCodeHid = "";
	private String testReqCode;
	private String testCodeHidNext = "";
	private String testReqHidNext = "";
	private String checklistCode;
	private String checklistName;
	private String reqName;
	private String positionCode;
	private String f9Flag = "true";	
	private String RefFlag="false";	  // refFlag will be true when  u r comming from candidatescreening
	private String RefCanTestFlag="false";
	private String dummyField;
	 
	private String fromTestResultFlag = "false";// if navigated from test result
	
	//this fields are used for test result form.
	private String fromDate="";
	private String toDate="";
	private String searchCriteria;
	private String marksObtained;
	private String resultType;
	private String hidTestTypeForSchTest = "";
	
	private String fromTestRescheduleFlag = "false";
	private String testCode = "";
	private String testDtlCode = "";
	private String isFrmTestReschedule = "false";
	private String formName = "";
	
	private ArrayList testDataList;
	private ArrayList candidateList=null;
	
	private String testRoundType = "";
	
	public ArrayList getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(ArrayList candidateList) {
		this.candidateList = candidateList;
	}
	/*public String getHiringManager() {
		return hiringManager;
	}
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}*/
	public String getHiringManagerCode() {
		return hiringManagerCode;
	}
	public void setHiringManagerCode(String hiringManagerCode) {
		this.hiringManagerCode = hiringManagerCode;
	}
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/*public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}*/
	public String getRequisitionName() {
		return requisitionName;
	}
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getConveyanceDetail() {
		return conveyanceDetail;
	}
	public void setConveyanceDetail(String conveyanceDetail) {
		this.conveyanceDetail = conveyanceDetail;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestRequirements() {
		return testRequirements;
	}
	public void setTestRequirements(String testRequirements) {
		this.testRequirements = testRequirements;
	}
	public String getTestTemplate() {
		return testTemplate;
	}
	public void setTestTemplate(String testTemplate) {
		this.testTemplate = testTemplate;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getChkAll() {
		return chkAll;
	}
	public void setChkAll(String chkAll) {
		this.chkAll = chkAll;
	}
	public String getCntPersonToken() {
		return cntPersonToken;
	}
	public void setCntPersonToken(String cntPersonToken) {
		this.cntPersonToken = cntPersonToken;
	}
	public String getCntPersonId() {
		return cntPersonId;
	}
	public void setCntPersonId(String cntPersonId) {
		this.cntPersonId = cntPersonId;
	}
	public String getTestRequirementsHid() {
		return testRequirementsHid;
	}
	public void setTestRequirementsHid(String testRequirementsHid) {
		this.testRequirementsHid = testRequirementsHid;
	}
	public String getTestReqCodeHid() {
		return testReqCodeHid;
	}
	public void setTestReqCodeHid(String testReqCodeHid) {
		this.testReqCodeHid = testReqCodeHid;
	}
	public String getTestReqCode() {
		return testReqCode;
	}
	public void setTestReqCode(String testReqCode) {
		this.testReqCode = testReqCode;
	}
	public String getTestCodeHidNext() {
		return testCodeHidNext;
	}
	public void setTestCodeHidNext(String testCodeHidNext) {
		this.testCodeHidNext = testCodeHidNext;
	}
	public String getTestReqHidNext() {
		return testReqHidNext;
	}
	public void setTestReqHidNext(String testReqHidNext) {
		this.testReqHidNext = testReqHidNext;
	}
	public ArrayList getTestDataList() {
		return testDataList;
	}
	public void setTestDataList(ArrayList testDataList) {
		this.testDataList = testDataList;
	}
	public String getChecklistCode() {
		return checklistCode;
	}
	public void setChecklistCode(String checklistCode) {
		this.checklistCode = checklistCode;
	}
	public String getChecklistName() {
		return checklistName;
	}
	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getF9Flag() {
		return f9Flag;
	}
	public void setF9Flag(String flag) {
		f9Flag = flag;
	}
	/**
	 * @return the requisitionCodeIterator
	 */
	public String getRequisitionCodeIterator() {
		return requisitionCodeIterator;
	}
	/**
	 * @param requisitionCodeIterator the requisitionCodeIterator to set
	 */
	public void setRequisitionCodeIterator(String requisitionCodeIterator) {
		this.requisitionCodeIterator = requisitionCodeIterator;
	}
	/**
	 * @return the hiringManagerIterator
	 */
	public String getHiringManagerIterator() {
		return hiringManagerIterator;
	}
	/**
	 * @param hiringManagerIterator the hiringManagerIterator to set
	 */
	public void setHiringManagerIterator(String hiringManagerIterator) {
		this.hiringManagerIterator = hiringManagerIterator;
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
	public String getFromTestRescheduleFlag() {
		return fromTestRescheduleFlag;
	}
	public void setFromTestRescheduleFlag(String fromTestRescheduleFlag) {
		this.fromTestRescheduleFlag = fromTestRescheduleFlag;
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
	public String getIsFrmTestReschedule() {
		return isFrmTestReschedule;
	}
	public void setIsFrmTestReschedule(String isFrmTestReschedule) {
		this.isFrmTestReschedule = isFrmTestReschedule;
	}
	public String getDummyField() {
		return dummyField;
	}
	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}
	public String getTestRoundType() {
		return testRoundType;
	}
	public void setTestRoundType(String testRoundType) {
		this.testRoundType = testRoundType;
	}
	public String getFromTestResultFlag() {
		return fromTestResultFlag;
	}
	public void setFromTestResultFlag(String fromTestResultFlag) {
		this.fromTestResultFlag = fromTestResultFlag;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public String getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getHidTestTypeForSchTest() {
		return hidTestTypeForSchTest;
	}
	public void setHidTestTypeForSchTest(String hidTestTypeForSchTest) {
		this.hidTestTypeForSchTest = hidTestTypeForSchTest;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
}
