/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0540
 *
 */
public class TestResult extends BeanBase {
	private String onlineTestType = "";
	private String onlineTestTemplateCode = "";
	private String objectiveMarksObtained = "";
	private String subjectiveMarksObtained = "";
	private boolean bothOrSubjectiveTypeTestFlag = false; 
	private String resultCode;
	private String requisitionCode;
	private String requisitionName;
	private String position;
	private String reqDate;
	private String hiringManagerCode;
	private String hiringManager;
	private String notAvailable = "true";
	private String notAvailableOnline = "true";
	private String hidTestResultFlag="false";
	private String dummyField;
	
	private String fromDate="";
	private String toDate="";
	private String testType;
	private String searchCriteria;
	private String marksObtained;
	private String resultType;
	
	private String writtenReqName;
	private String writtenReqCode;
	private String writtenCandName;
	private String writtenCandCode;
	private String writtenEmailId;
	private String writtenContactNo;
	private String writtenTestDate;
	private String writtenTestTime;
	private String writtenObtMarks;
	private String writtenTotalMarks;
	private String writtenResult;
	private String writtenHiddenResult;
	private String writtenResultCode = "";
	private String onlineResultCode = "";
	private String writtenFlag = "false";
	private String onlineFlag = "false";
	
	private String onLineReqCode;
	private String onLineReqName;
	private String onLineCandCode;
	private String onLineCandName;
	private String onLineEmailId;
	private String onLineContactNo;
	private String onLineTestDate;
	private String onLineTestTime;
	private String onLineObtMarks;
	private String onLineTotalMarks;
	private String onLineResult;
	private String onLineFlag;
	private String showFlag="";
	private String fromWTestIntListFlag= "false";
	
	private String onlineComments = "";
	private String writtenComments = "";
	private String writtenTestRound = "";
	private String onLineTestRound = "";
	private String hidTestTypeForSchTest = "";
	
	private String onlineTestDtlCode = "";
	private String writtenTestDtlCode = "";
	
	private ArrayList<Object> writtenTestList = new ArrayList<Object>();
	private ArrayList<Object> onLineTestList  = new ArrayList<Object>();
	
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
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
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}
	/**
	 * @param testType the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
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
	 * @return the marksObtained
	 */
	public String getMarksObtained() {
		return marksObtained;
	}
	/**
	 * @param marksObtained the marksObtained to set
	 */
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}
	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	/**
	 * @return the writtenReqName
	 */
	public String getWrittenReqName() {
		return writtenReqName;
	}
	/**
	 * @param writtenReqName the writtenReqName to set
	 */
	public void setWrittenReqName(String writtenReqName) {
		this.writtenReqName = writtenReqName;
	}
	/**
	 * @return the writtenReqCode
	 */
	public String getWrittenReqCode() {
		return writtenReqCode;
	}
	/**
	 * @param writtenReqCode the writtenReqCode to set
	 */
	public void setWrittenReqCode(String writtenReqCode) {
		this.writtenReqCode = writtenReqCode;
	}
	/**
	 * @return the writtenCandName
	 */
	public String getWrittenCandName() {
		return writtenCandName;
	}
	/**
	 * @param writtenCandName the writtenCandName to set
	 */
	public void setWrittenCandName(String writtenCandName) {
		this.writtenCandName = writtenCandName;
	}
	/**
	 * @return the writtenCandCode
	 */
	public String getWrittenCandCode() {
		return writtenCandCode;
	}
	/**
	 * @param writtenCandCode the writtenCandCode to set
	 */
	public void setWrittenCandCode(String writtenCandCode) {
		this.writtenCandCode = writtenCandCode;
	}
	/**
	 * @return the writtenEmailId
	 */
	public String getWrittenEmailId() {
		return writtenEmailId;
	}
	/**
	 * @param writtenEmailId the writtenEmailId to set
	 */
	public void setWrittenEmailId(String writtenEmailId) {
		this.writtenEmailId = writtenEmailId;
	}
	/**
	 * @return the writtenContactNo
	 */
	public String getWrittenContactNo() {
		return writtenContactNo;
	}
	/**
	 * @param writtenContactNo the writtenContactNo to set
	 */
	public void setWrittenContactNo(String writtenContactNo) {
		this.writtenContactNo = writtenContactNo;
	}
	/**
	 * @return the writtenTestDate
	 */
	public String getWrittenTestDate() {
		return writtenTestDate;
	}
	/**
	 * @param writtenTestDate the writtenTestDate to set
	 */
	public void setWrittenTestDate(String writtenTestDate) {
		this.writtenTestDate = writtenTestDate;
	}
	/**
	 * @return the writtenTestTime
	 */
	public String getWrittenTestTime() {
		return writtenTestTime;
	}
	/**
	 * @param writtenTestTime the writtenTestTime to set
	 */
	public void setWrittenTestTime(String writtenTestTime) {
		this.writtenTestTime = writtenTestTime;
	}
	/**
	 * @return the writtenObtMarks
	 */
	public String getWrittenObtMarks() {
		return writtenObtMarks;
	}
	/**
	 * @param writtenObtMarks the writtenObtMarks to set
	 */
	public void setWrittenObtMarks(String writtenObtMarks) {
		this.writtenObtMarks = writtenObtMarks;
	}
	/**
	 * @return the writtenTotalMarks
	 */
	public String getWrittenTotalMarks() {
		return writtenTotalMarks;
	}
	/**
	 * @param writtenTotalMarks the writtenTotalMarks to set
	 */
	public void setWrittenTotalMarks(String writtenTotalMarks) {
		this.writtenTotalMarks = writtenTotalMarks;
	}
	/**
	 * @return the writtenResult
	 */
	public String getWrittenResult() {
		return writtenResult;
	}
	/**
	 * @param writtenResult the writtenResult to set
	 */
	public void setWrittenResult(String writtenResult) {
		this.writtenResult = writtenResult;
	}
	/**
	 * @return the writtenFlag
	 */
	public String getWrittenFlag() {
		return writtenFlag;
	}
	/**
	 * @param writtenFlag the writtenFlag to set
	 */
	public void setWrittenFlag(String writtenFlag) {
		this.writtenFlag = writtenFlag;
	}
	/**
	 * @return the onLineReqCode
	 */
	public String getOnLineReqCode() {
		return onLineReqCode;
	}
	/**
	 * @param onLineReqCode the onLineReqCode to set
	 */
	public void setOnLineReqCode(String onLineReqCode) {
		this.onLineReqCode = onLineReqCode;
	}
	/**
	 * @return the onLineReqName
	 */
	public String getOnLineReqName() {
		return onLineReqName;
	}
	/**
	 * @param onLineReqName the onLineReqName to set
	 */
	public void setOnLineReqName(String onLineReqName) {
		this.onLineReqName = onLineReqName;
	}
	/**
	 * @return the onLineCandCode
	 */
	public String getOnLineCandCode() {
		return onLineCandCode;
	}
	/**
	 * @param onLineCandCode the onLineCandCode to set
	 */
	public void setOnLineCandCode(String onLineCandCode) {
		this.onLineCandCode = onLineCandCode;
	}
	/**
	 * @return the onLineCandName
	 */
	public String getOnLineCandName() {
		return onLineCandName;
	}
	/**
	 * @param onLineCandName the onLineCandName to set
	 */
	public void setOnLineCandName(String onLineCandName) {
		this.onLineCandName = onLineCandName;
	}
	/**
	 * @return the onLineEmailId
	 */
	public String getOnLineEmailId() {
		return onLineEmailId;
	}
	/**
	 * @param onLineEmailId the onLineEmailId to set
	 */
	public void setOnLineEmailId(String onLineEmailId) {
		this.onLineEmailId = onLineEmailId;
	}
	/**
	 * @return the onLineContactNo
	 */
	public String getOnLineContactNo() {
		return onLineContactNo;
	}
	/**
	 * @param onLineContactNo the onLineContactNo to set
	 */
	public void setOnLineContactNo(String onLineContactNo) {
		this.onLineContactNo = onLineContactNo;
	}
	/**
	 * @return the onLineTestDate
	 */
	public String getOnLineTestDate() {
		return onLineTestDate;
	}
	/**
	 * @param onLineTestDate the onLineTestDate to set
	 */
	public void setOnLineTestDate(String onLineTestDate) {
		this.onLineTestDate = onLineTestDate;
	}
	/**
	 * @return the onLineTestTime
	 */
	public String getOnLineTestTime() {
		return onLineTestTime;
	}
	/**
	 * @param onLineTestTime the onLineTestTime to set
	 */
	public void setOnLineTestTime(String onLineTestTime) {
		this.onLineTestTime = onLineTestTime;
	}
	/**
	 * @return the onLineObtMarks
	 */
	public String getOnLineObtMarks() {
		return onLineObtMarks;
	}
	/**
	 * @param onLineObtMarks the onLineObtMarks to set
	 */
	public void setOnLineObtMarks(String onLineObtMarks) {
		this.onLineObtMarks = onLineObtMarks;
	}
	/**
	 * @return the onLineTotalMarks
	 */
	public String getOnLineTotalMarks() {
		return onLineTotalMarks;
	}
	/**
	 * @param onLineTotalMarks the onLineTotalMarks to set
	 */
	public void setOnLineTotalMarks(String onLineTotalMarks) {
		this.onLineTotalMarks = onLineTotalMarks;
	}
	/**
	 * @return the onLineResult
	 */
	public String getOnLineResult() {
		return onLineResult;
	}
	/**
	 * @param onLineResult the onLineResult to set
	 */
	public void setOnLineResult(String onLineResult) {
		this.onLineResult = onLineResult;
	}
	/**
	 * @return the onLineFlag
	 */
	public String getOnLineFlag() {
		return onLineFlag;
	}
	/**
	 * @param onLineFlag the onLineFlag to set
	 */
	public void setOnLineFlag(String onLineFlag) {
		this.onLineFlag = onLineFlag;
	}
	/**
	 * @return the writtenTestList
	 */
	public ArrayList<Object> getWrittenTestList() {
		return writtenTestList;
	}
	/**
	 * @param writtenTestList the writtenTestList to set
	 */
	public void setWrittenTestList(ArrayList<Object> writtenTestList) {
		this.writtenTestList = writtenTestList;
	}
	/**
	 * @return the onLineTestList
	 */
	public ArrayList<Object> getOnLineTestList() {
		return onLineTestList;
	}
	/**
	 * @param onLineTestList the onLineTestList to set
	 */
	public void setOnLineTestList(ArrayList<Object> onLineTestList) {
		this.onLineTestList = onLineTestList;
	}
	/**
	 * @return the writtenHiddenResult
	 */
	public String getWrittenHiddenResult() {
		return writtenHiddenResult;
	}
	/**
	 * @param writtenHiddenResult the writtenHiddenResult to set
	 */
	public void setWrittenHiddenResult(String writtenHiddenResult) {
		this.writtenHiddenResult = writtenHiddenResult;
	}
	/**
	 * @return the writtenResultCode
	 */
	public String getWrittenResultCode() {
		return writtenResultCode;
	}
	/**
	 * @param writtenResultCode the writtenResultCode to set
	 */
	public void setWrittenResultCode(String writtenResultCode) {
		this.writtenResultCode = writtenResultCode;
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
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getFromWTestIntListFlag() {
		return fromWTestIntListFlag;
	}
	public void setFromWTestIntListFlag(String fromWTestIntListFlag) {
		this.fromWTestIntListFlag = fromWTestIntListFlag;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getNotAvailable() {
		return notAvailable;
	}
	public void setNotAvailable(String notAvailable) {
		this.notAvailable = notAvailable;
	}
	public String getNotAvailableOnline() {
		return notAvailableOnline;
	}
	public void setNotAvailableOnline(String notAvailableOnline) {
		this.notAvailableOnline = notAvailableOnline;
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
	public String getOnlineComments() {
		return onlineComments;
	}
	public void setOnlineComments(String onlineComments) {
		this.onlineComments = onlineComments;
	}
	public String getWrittenComments() {
		return writtenComments;
	}
	public void setWrittenComments(String writtenComments) {
		this.writtenComments = writtenComments;
	}
	public String getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public String getOnlineResultCode() {
		return onlineResultCode;
	}
	public void setOnlineResultCode(String onlineResultCode) {
		this.onlineResultCode = onlineResultCode;
	}
	public String getWrittenTestRound() {
		return writtenTestRound;
	}
	public void setWrittenTestRound(String writtenTestRound) {
		this.writtenTestRound = writtenTestRound;
	}
	public String getOnLineTestRound() {
		return onLineTestRound;
	}
	public void setOnLineTestRound(String onLineTestRound) {
		this.onLineTestRound = onLineTestRound;
	}
	public String getHidTestTypeForSchTest() {
		return hidTestTypeForSchTest;
	}
	public void setHidTestTypeForSchTest(String hidTestTypeForSchTest) {
		this.hidTestTypeForSchTest = hidTestTypeForSchTest;
	}
	public String getOnlineTestDtlCode() {
		return onlineTestDtlCode;
	}
	public void setOnlineTestDtlCode(String onlineTestDtlCode) {
		this.onlineTestDtlCode = onlineTestDtlCode;
	}
	public String getWrittenTestDtlCode() {
		return writtenTestDtlCode;
	}
	public void setWrittenTestDtlCode(String writtenTestDtlCode) {
		this.writtenTestDtlCode = writtenTestDtlCode;
	}
	/**
	 * @return the objectiveMarksObtained
	 */
	public String getObjectiveMarksObtained() {
		return this.objectiveMarksObtained;
	}
	/**
	 * @param objectiveMarksObtained the objectiveMarksObtained to set
	 */
	public void setObjectiveMarksObtained(String objectiveMarksObtained) {
		this.objectiveMarksObtained = objectiveMarksObtained;
	}
	/**
	 * @return the subjectiveMarksObtained
	 */
	public String getSubjectiveMarksObtained() {
		return this.subjectiveMarksObtained;
	}
	/**
	 * @param subjectiveMarksObtained the subjectiveMarksObtained to set
	 */
	public void setSubjectiveMarksObtained(String subjectiveMarksObtained) {
		this.subjectiveMarksObtained = subjectiveMarksObtained;
	}
	/**
	 * @return the bothOrSubjectiveTypeTestFlag
	 */
	public boolean isBothOrSubjectiveTypeTestFlag() {
		return this.bothOrSubjectiveTypeTestFlag;
	}
	/**
	 * @param bothOrSubjectiveTypeTestFlag the bothOrSubjectiveTypeTestFlag to set
	 */
	public void setBothOrSubjectiveTypeTestFlag(boolean bothOrSubjectiveTypeTestFlag) {
		this.bothOrSubjectiveTypeTestFlag = bothOrSubjectiveTypeTestFlag;
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
	/**
	 * @return the onlineTestType
	 */
	public String getOnlineTestType() {
		return this.onlineTestType;
	}
	/**
	 * @param onlineTestType the onlineTestType to set
	 */
	public void setOnlineTestType(String onlineTestType) {
		this.onlineTestType = onlineTestType;
	}
}
