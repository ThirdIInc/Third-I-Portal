package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class InterviewSchedule extends BeanBase {
	private String testDetailCode = "";
	private String onlineTestTemplateCode = "";
	private String questionUploadedDoc = "";
	private String answerUploadedDoc = "";
	private String dataPath = "";
	private boolean documentAttachedFlag = false;
	private boolean answerAttachedFlag = false;
	private String subject = "";
	private String category = "";
	private String dummyField;
	private String requisitionName;
	private String requisitionCode;
	private String position;
	private String hiringManager;
	private String hiringManagerCode;
	private String contactPerson;
	private String cntPersonId;
	private String cntPersonToken;
	private String conveyanceDetail="";
	private String testRequirements;
	private String testReqCode;
	private String checklistCode;
	private String checklistName;
	private String candidateName;
	private String candidateCode;
	private String intDate;
	private String intTime;
	private String venue="";
	private String interviewerName;
	private String interviewerCode;
	private String comments="";
	private String checkBox;
	private String chk;
	private String recruiterId;
	private String intRoundType;
	private String rowId;
	private String interviewDtlCode;
	private String fromSchdIntListFlag = "false";
	private String interviewCode;
	private String positionCode;
	private String f9Flag = "true";
	private ArrayList testDataList;
	private ArrayList candidateList=null;
	private String headerView="true";
	private String reportType;	
	private String chkAll;
	private String checkBoxFlag="false";
	private String RefFlag="false";	  // refFlag will be true when  u r comming from candidatescreening
	private String refInterviewFlag="false";
	private String testFlag="false";
	private String rescheduleFlag="false";
	private String interviewReschFlag="false"; // checking var for reschudule
	private String hidReschFlag="false";
	private String hidTestResultFlag;
	
	//this fields are used for test result form.
	private String fromDate="";
	private String toDate="";
	private String testType;
	private String searchCriteria;
	private String marksObtained;
	private String resultType;
	private String hidTestTypeForSchTest = "";
	
	//for test view result
	private String candCode="";
	private String viewReq="";
	private String viewPosition="";
	private String viewTotalMarks="";
	private String viewOptMarks=""; 
	private String viewResult="";
	private String viewTestType=""; 
	private String viewTestDate="";
	private String viewTestTime="";
	private String viewTestComments="";
	private String viewCandidateName="";
	private String viewTestFlag="false";
	private String viewTestRoundType=""; 
	private String questionName = "";
	private String subjAnswer = "";
	private String optionCheckBox = "";
	private String optionName = "";
	private String questionSequence="1";
	private String previousButtonFlag = "true";
	private String nextButtonFlag = "true";
	private String subjectiveFlag = "false";
	ArrayList onLineTestList = new ArrayList();
	ArrayList viewTestList = new ArrayList();
	TreeMap<String, String> interviewRoundTypeMap =null ;
	
	public String getHidReschFlag() {
		return hidReschFlag;
	}
	public void setHidReschFlag(String hidReschFlag) {
		this.hidReschFlag = hidReschFlag;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public ArrayList getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(ArrayList candidateList) {
		this.candidateList = candidateList;
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
	public String getRequisitionName() {
		return requisitionName;
	}
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHiringManager() {
		return hiringManager;
	}
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}
	public String getHiringManagerCode() {
		return hiringManagerCode;
	}
	public void setHiringManagerCode(String hiringManagerCode) {
		this.hiringManagerCode = hiringManagerCode;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getCntPersonId() {
		return cntPersonId;
	}
	public void setCntPersonId(String cntPersonId) {
		this.cntPersonId = cntPersonId;
	}
	public String getCntPersonToken() {
		return cntPersonToken;
	}
	public void setCntPersonToken(String cntPersonToken) {
		this.cntPersonToken = cntPersonToken;
	}
	public String getConveyanceDetail() {
		return conveyanceDetail;
	}
	public void setConveyanceDetail(String conveyanceDetail) {
		this.conveyanceDetail = conveyanceDetail;
	}
	public String getTestRequirements() {
		return testRequirements;
	}
	public void setTestRequirements(String testRequirements) {
		this.testRequirements = testRequirements;
	}
	public String getTestReqCode() {
		return testReqCode;
	}
	public void setTestReqCode(String testReqCode) {
		this.testReqCode = testReqCode;
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
	public String getIntDate() {
		return intDate;
	}
	public void setIntDate(String intDate) {
		this.intDate = intDate;
	}
	public String getIntTime() {
		return intTime;
	}
	public void setIntTime(String intTime) {
		this.intTime = intTime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getInterviewerCode() {
		return interviewerCode;
	}
	public void setInterviewerCode(String interviewerCode) {
		this.interviewerCode = interviewerCode;
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
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getIntRoundType() {
		return intRoundType;
	}
	public void setIntRoundType(String intRoundType) {
		this.intRoundType = intRoundType;
	}
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getInterviewDtlCode() {
		return interviewDtlCode;
	}
	public void setInterviewDtlCode(String interviewDtlCode) {
		this.interviewDtlCode = interviewDtlCode;
	}
	public String getFromSchdIntListFlag() {
		return fromSchdIntListFlag;
	}
	public void setFromSchdIntListFlag(String fromSchdIntListFlag) {
		this.fromSchdIntListFlag = fromSchdIntListFlag;
	}
	public String getInterviewCode() {
		return interviewCode;
	}
	public void setInterviewCode(String interviewCode) {
		this.interviewCode = interviewCode;
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
	public String getHeaderView() {
		return headerView;
	}
	public void setHeaderView(String headerView) {
		this.headerView = headerView;
	}
	public String getRefFlag() {
		return RefFlag;
	}
	public void setRefFlag(String refFlag) {
		RefFlag = refFlag;
	}
	public String getRefInterviewFlag() {
		return refInterviewFlag;
	}
	public void setRefInterviewFlag(String refInterviewFlag) {
		this.refInterviewFlag = refInterviewFlag;
	}
	public String getTestFlag() {
		return testFlag;
	}
	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}
	public String getRescheduleFlag() {
		return rescheduleFlag;
	}
	public void setRescheduleFlag(String rescheduleFlag) {
		this.rescheduleFlag = rescheduleFlag;
	}
	public String getInterviewReschFlag() {
		return interviewReschFlag;
	}
	public void setInterviewReschFlag(String interviewReschFlag) {
		this.interviewReschFlag = interviewReschFlag;
	}
	public String getHidTestResultFlag() {
		return hidTestResultFlag;
	}
	public void setHidTestResultFlag(String hidTestResultFlag) {
		this.hidTestResultFlag = hidTestResultFlag;
	}
	public String getDummyField() {
		return dummyField;
	}
	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}
	public String getCandCode() {
		return candCode;
	}
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	public String getViewReq() {
		return viewReq;
	}
	public void setViewReq(String viewReq) {
		this.viewReq = viewReq;
	}
	public String getViewPosition() {
		return viewPosition;
	}
	public void setViewPosition(String viewPosition) {
		this.viewPosition = viewPosition;
	}
	public String getViewTotalMarks() {
		return viewTotalMarks;
	}
	public void setViewTotalMarks(String viewTotalMarks) {
		this.viewTotalMarks = viewTotalMarks;
	}
	public String getViewOptMarks() {
		return viewOptMarks;
	}
	public void setViewOptMarks(String viewOptMarks) {
		this.viewOptMarks = viewOptMarks;
	}
	public String getViewResult() {
		return viewResult;
	}
	public void setViewResult(String viewResult) {
		this.viewResult = viewResult;
	}
	public String getViewTestType() {
		return viewTestType;
	}
	public void setViewTestType(String viewTestType) {
		this.viewTestType = viewTestType;
	}
	public String getViewTestDate() {
		return viewTestDate;
	}
	public void setViewTestDate(String viewTestDate) {
		this.viewTestDate = viewTestDate;
	}
	public String getViewTestTime() {
		return viewTestTime;
	}
	public void setViewTestTime(String viewTestTime) {
		this.viewTestTime = viewTestTime;
	}
	public String getViewTestComments() {
		return viewTestComments;
	}
	public void setViewTestComments(String viewTestComments) {
		this.viewTestComments = viewTestComments;
	}
	public String getViewCandidateName() {
		return viewCandidateName;
	}
	public void setViewCandidateName(String viewCandidateName) {
		this.viewCandidateName = viewCandidateName;
	}
	public String getViewTestFlag() {
		return viewTestFlag;
	}
	public void setViewTestFlag(String viewTestFlag) {
		this.viewTestFlag = viewTestFlag;
	}
	public String getChkAll() {
		return chkAll;
	}
	public void setChkAll(String chkAll) {
		this.chkAll = chkAll;
	}
	public String getCheckBoxFlag() {
		return checkBoxFlag;
	}
	public void setCheckBoxFlag(String checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
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

	public String getViewTestRoundType() {
		return viewTestRoundType;
	}
	public void setViewTestRoundType(String viewTestRoundType) {
		this.viewTestRoundType = viewTestRoundType;
	}
	public ArrayList getViewTestList() {
		return viewTestList;
	}
	public void setViewTestList(ArrayList viewTestList) {
		this.viewTestList = viewTestList;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getSubjAnswer() {
		return subjAnswer;
	}
	public void setSubjAnswer(String subjAnswer) {
		this.subjAnswer = subjAnswer;
	}
	public String getOptionCheckBox() {
		return optionCheckBox;
	}
	public void setOptionCheckBox(String optionCheckBox) {
		this.optionCheckBox = optionCheckBox;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public ArrayList getOnLineTestList() {
		return onLineTestList;
	}
	public void setOnLineTestList(ArrayList onLineTestList) {
		this.onLineTestList = onLineTestList;
	}
	public String getQuestionSequence() {
		return questionSequence;
	}
	public void setQuestionSequence(String questionSequence) {
		this.questionSequence = questionSequence;
	}
	public String getPreviousButtonFlag() {
		return previousButtonFlag;
	}
	public void setPreviousButtonFlag(String previousButtonFlag) {
		this.previousButtonFlag = previousButtonFlag;
	}
	public String getNextButtonFlag() {
		return nextButtonFlag;
	}
	public void setNextButtonFlag(String nextButtonFlag) {
		this.nextButtonFlag = nextButtonFlag;
	}
	public String getSubjectiveFlag() {
		return subjectiveFlag;
	}
	public void setSubjectiveFlag(String subjectiveFlag) {
		this.subjectiveFlag = subjectiveFlag;
	}
	public TreeMap<String, String> getInterviewRoundTypeMap() {
		return interviewRoundTypeMap;
	}
	public void setInterviewRoundTypeMap(
			TreeMap<String, String> interviewRoundTypeMap) {
		this.interviewRoundTypeMap = interviewRoundTypeMap;
	}
	/**
	 * @return the questionUploadedDoc
	 */
	public String getQuestionUploadedDoc() {
		return this.questionUploadedDoc;
	}
	/**
	 * @param questionUploadedDoc the questionUploadedDoc to set
	 */
	public void setQuestionUploadedDoc(String questionUploadedDoc) {
		this.questionUploadedDoc = questionUploadedDoc;
	}
	/**
	 * @return the answerUploadedDoc
	 */
	public String getAnswerUploadedDoc() {
		return this.answerUploadedDoc;
	}
	/**
	 * @param answerUploadedDoc the answerUploadedDoc to set
	 */
	public void setAnswerUploadedDoc(String answerUploadedDoc) {
		this.answerUploadedDoc = answerUploadedDoc;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the documentAttachedFlag
	 */
	public boolean isDocumentAttachedFlag() {
		return this.documentAttachedFlag;
	}
	/**
	 * @param documentAttachedFlag the documentAttachedFlag to set
	 */
	public void setDocumentAttachedFlag(boolean documentAttachedFlag) {
		this.documentAttachedFlag = documentAttachedFlag;
	}
	/**
	 * @return the answerAttachedFlag
	 */
	public boolean isAnswerAttachedFlag() {
		return this.answerAttachedFlag;
	}
	/**
	 * @param answerAttachedFlag the answerAttachedFlag to set
	 */
	public void setAnswerAttachedFlag(boolean answerAttachedFlag) {
		this.answerAttachedFlag = answerAttachedFlag;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return this.category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the testDetailCode
	 */
	public String getTestDetailCode() {
		return this.testDetailCode;
	}
	/**
	 * @param testDetailCode the testDetailCode to set
	 */
	public void setTestDetailCode(String testDetailCode) {
		this.testDetailCode = testDetailCode;
	}
	/**
	 * @return the onlineTestTemplateCode
	 */
	public String getOnlineTestTemplateCode() {
		return this.onlineTestTemplateCode;
	}
	/**
	 * @param onlineTestTemplateCode the onlineTestTemplateCode to set
	 */
	public void setOnlineTestTemplateCode(String onlineTestTemplateCode) {
		this.onlineTestTemplateCode = onlineTestTemplateCode;
	}
	 
}
