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
public class OnlineTest extends BeanBase {
	
	private String questionUploadedDoc = "";
	private String answerUploadedDoc = "";
	private String dataPath = "";
	private String subject = "";
	private String category = "";
	private boolean documentNotAttachedFlag = false; 
	private String templateCode = "";
	
	private String onlineTestCode = "";
	private String onlineTestCodeItr = "";
	private String requisitionTitleItr = "";
	private String requisitionCodeItr = "";
	private String testTemplateNameItr = "";
	private String testTemplateCodeItr = "";
	private String testDateItr = "";
	private String testTimeItr = "";
	private ArrayList<OnlineTest> scheduledTestList = null;
	private boolean scheduledDataFlag = true;
	
	private String subjectAns = "";
	private String subjAnswerLimit="0";
	
	private String testTemplateCode;
	private String tempName;
	private String tempDuration;
	private String tempTotalMarks;
	private String tempTotalQues;
	private String tempInstruction;
	private String testType;
	private String reqName;
	private String reqCode;
	
	private String randomQuesCodes;
	private String questionName;
	private String questionLevel;
	private String preQuestionLevel;
	private String optionFlag = "true";
	private String optionName;
	private String optionCode;
	private String optionAnswer;
	private String text;
	
	private String lastQuestionSequence="";
	private String sequenceCode = "";
	private String previousButton = "false";
	private String nextButtonFlag = "true";
	private String submitButtonFlag = "false";
	
	private String timerFlag="";
	
	private String checkBox;
	private String chk;
	
	private String result;
	private String passingMarks;
	private String marksObtained;
	private String percentage;
	private String blankAnswer;
	private String correctMarks;
	private String negativeMarks;
	
	
	/**
	 * for different criteria's
	 */
	private String equalweightage = "";
	private String marksWrongAns;
	private String marksNoAns;
	private String onlineScore;
	private String chkOnlineScoreFlag = "false";
	private String marksForCorrect;
	private String marksHard;
	private String marksMedium;
	private String marksEasy;
	
	
	private String  wrongmarksHard;
	private String  wrongmarksEasy;
	private String  wrongmarksMedium;
	private String equalMarksForAll;
	
	private String resultFlag="N";
	private ArrayList randomQueList=null;
	
	private ArrayList optionList=null;

	public ArrayList getRandomQueList() {
		return randomQueList;
	}

	public void setRandomQueList(ArrayList randomQueList) {
		this.randomQueList = randomQueList;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempDuration() {
		return tempDuration;
	}

	public void setTempDuration(String tempDuration) {
		this.tempDuration = tempDuration;
	}

	public String getTempTotalMarks() {
		return tempTotalMarks;
	}

	public void setTempTotalMarks(String tempTotalMarks) {
		this.tempTotalMarks = tempTotalMarks;
	}

	

	public String getTempInstruction() {
		return tempInstruction;
	}

	public void setTempInstruction(String tempInstruction) {
		this.tempInstruction = tempInstruction;
	}

	public String getTestTemplateCode() {
		return testTemplateCode;
	}

	public void setTestTemplateCode(String testTemplateCode) {
		this.testTemplateCode = testTemplateCode;
	}

	public String getTempTotalQues() {
		return tempTotalQues;
	}

	public void setTempTotalQues(String tempTotalQues) {
		this.tempTotalQues = tempTotalQues;
	}

	public String getRandomQuesCodes() {
		return randomQuesCodes;
	}

	public void setRandomQuesCodes(String randomQuesCodes) {
		this.randomQuesCodes = randomQuesCodes;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getOptionAnswer() {
		return optionAnswer;
	}

	public void setOptionAnswer(String optionAnswer) {
		this.optionAnswer = optionAnswer;
	}

	public ArrayList getOptionList() {
		return optionList;
	}

	public void setOptionList(ArrayList optionList) {
		this.optionList = optionList;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(String previousButton) {
		this.previousButton = previousButton;
	}

	public String getNextButtonFlag() {
		return nextButtonFlag;
	}

	public void setNextButtonFlag(String nextButtonFlag) {
		this.nextButtonFlag = nextButtonFlag;
	}

	public String getSubmitButtonFlag() {
		return submitButtonFlag;
	}

	public void setSubmitButtonFlag(String submitButtonFlag) {
		this.submitButtonFlag = submitButtonFlag;
	}

	public String getLastQuestionSequence() {
		return lastQuestionSequence;
	}

	public void setLastQuestionSequence(String lastQuestionSequence) {
		this.lastQuestionSequence = lastQuestionSequence;
	}

	public String getTimerFlag() {
		return timerFlag;
	}

	public void setTimerFlag(String timerFlag) {
		this.timerFlag = timerFlag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEqualweightage() {
		return equalweightage;
	}

	public void setEqualweightage(String equalweightage) {
		this.equalweightage = equalweightage;
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

	public String getMarksWrongAns() {
		return marksWrongAns;
	}

	public void setMarksWrongAns(String marksWrongAns) {
		this.marksWrongAns = marksWrongAns;
	}

	public String getMarksNoAns() {
		return marksNoAns;
	}

	public void setMarksNoAns(String marksNoAns) {
		this.marksNoAns = marksNoAns;
	}

	public String getOnlineScore() {
		return onlineScore;
	}

	public void setOnlineScore(String onlineScore) {
		this.onlineScore = onlineScore;
	}

	public String getChkOnlineScoreFlag() {
		return chkOnlineScoreFlag;
	}

	public void setChkOnlineScoreFlag(String chkOnlineScoreFlag) {
		this.chkOnlineScoreFlag = chkOnlineScoreFlag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPassingMarks() {
		return passingMarks;
	}

	public void setPassingMarks(String passingMarks) {
		this.passingMarks = passingMarks;
	}

	public String getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getMarksForCorrect() {
		return marksForCorrect;
	}

	public void setMarksForCorrect(String marksForCorrect) {
		this.marksForCorrect = marksForCorrect;
	}

	public String getMarksHard() {
		return marksHard;
	}

	public void setMarksHard(String marksHard) {
		this.marksHard = marksHard;
	}

	public String getMarksMedium() {
		return marksMedium;
	}

	public void setMarksMedium(String marksMedium) {
		this.marksMedium = marksMedium;
	}

	public String getMarksEasy() {
		return marksEasy;
	}

	public void setMarksEasy(String marksEasy) {
		this.marksEasy = marksEasy;
	}

	public String getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}

	public String getBlankAnswer() {
		return blankAnswer;
	}

	public void setBlankAnswer(String blankAnswer) {
		this.blankAnswer = blankAnswer;
	}

	public String getCorrectMarks() {
		return correctMarks;
	}

	public void setCorrectMarks(String correctMarks) {
		this.correctMarks = correctMarks;
	}

	public String getNegativeMarks() {
		return negativeMarks;
	}

	public void setNegativeMarks(String negativeMarks) {
		this.negativeMarks = negativeMarks;
	}

	public String getPreQuestionLevel() {
		return preQuestionLevel;
	}

	public void setPreQuestionLevel(String preQuestionLevel) {
		this.preQuestionLevel = preQuestionLevel;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public String getWrongmarksHard() {
		return wrongmarksHard;
	}

	public void setWrongmarksHard(String wrongmarksHard) {
		this.wrongmarksHard = wrongmarksHard;
	}

	public String getWrongmarksEasy() {
		return wrongmarksEasy;
	}

	public void setWrongmarksEasy(String wrongmarksEasy) {
		this.wrongmarksEasy = wrongmarksEasy;
	}

	public String getWrongmarksMedium() {
		return wrongmarksMedium;
	}

	public void setWrongmarksMedium(String wrongmarksMedium) {
		this.wrongmarksMedium = wrongmarksMedium;
	}

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getEqualMarksForAll() {
		return equalMarksForAll;
	}

	public void setEqualMarksForAll(String equalMarksForAll) {
		this.equalMarksForAll = equalMarksForAll;
	}

	public String getSubjectAns() {
		return subjectAns;
	}

	public void setSubjectAns(String subjectAns) {
		this.subjectAns = subjectAns;
	}

	public String getSubjAnswerLimit() {
		return subjAnswerLimit;
	}

	public void setSubjAnswerLimit(String subjAnswerLimit) {
		this.subjAnswerLimit = subjAnswerLimit;
	}

	/**
	 * @return the requisitionTitleItr
	 */
	public String getRequisitionTitleItr() {
		return this.requisitionTitleItr;
	}

	/**
	 * @param requisitionTitleItr the requisitionTitleItr to set
	 */
	public void setRequisitionTitleItr(String requisitionTitleItr) {
		this.requisitionTitleItr = requisitionTitleItr;
	}

	/**
	 * @return the requisitionCodeItr
	 */
	public String getRequisitionCodeItr() {
		return this.requisitionCodeItr;
	}

	/**
	 * @param requisitionCodeItr the requisitionCodeItr to set
	 */
	public void setRequisitionCodeItr(String requisitionCodeItr) {
		this.requisitionCodeItr = requisitionCodeItr;
	}

	/**
	 * @return the testTemplateNameItr
	 */
	public String getTestTemplateNameItr() {
		return this.testTemplateNameItr;
	}

	/**
	 * @param testTemplateNameItr the testTemplateNameItr to set
	 */
	public void setTestTemplateNameItr(String testTemplateNameItr) {
		this.testTemplateNameItr = testTemplateNameItr;
	}

	/**
	 * @return the testTemplateCodeItr
	 */
	public String getTestTemplateCodeItr() {
		return this.testTemplateCodeItr;
	}

	/**
	 * @param testTemplateCodeItr the testTemplateCodeItr to set
	 */
	public void setTestTemplateCodeItr(String testTemplateCodeItr) {
		this.testTemplateCodeItr = testTemplateCodeItr;
	}

	/**
	 * @return the testDateItr
	 */
	public String getTestDateItr() {
		return this.testDateItr;
	}

	/**
	 * @param testDateItr the testDateItr to set
	 */
	public void setTestDateItr(String testDateItr) {
		this.testDateItr = testDateItr;
	}

	/**
	 * @return the testTimeItr
	 */
	public String getTestTimeItr() {
		return this.testTimeItr;
	}

	/**
	 * @param testTimeItr the testTimeItr to set
	 */
	public void setTestTimeItr(String testTimeItr) {
		this.testTimeItr = testTimeItr;
	}

	/**
	 * @return the scheduledTestList
	 */
	public ArrayList<OnlineTest> getScheduledTestList() {
		return this.scheduledTestList;
	}

	/**
	 * @param scheduledTestList the scheduledTestList to set
	 */
	public void setScheduledTestList(ArrayList<OnlineTest> scheduledTestList) {
		this.scheduledTestList = scheduledTestList;
	}

	/**
	 * @return the scheduledDataFlag
	 */
	public boolean isScheduledDataFlag() {
		return this.scheduledDataFlag;
	}

	/**
	 * @param scheduledDataFlag the scheduledDataFlag to set
	 */
	public void setScheduledDataFlag(boolean scheduledDataFlag) {
		this.scheduledDataFlag = scheduledDataFlag;
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
	 * @return the documentNotAttachedFlag
	 */
	public boolean isDocumentNotAttachedFlag() {
		return this.documentNotAttachedFlag;
	}

	/**
	 * @param documentNotAttachedFlag the documentNotAttachedFlag to set
	 */
	public void setDocumentNotAttachedFlag(boolean documentNotAttachedFlag) {
		this.documentNotAttachedFlag = documentNotAttachedFlag;
	}

	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return this.templateCode;
	}

	/**
	 * @param templateCode the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * @return the onlineTestCodeItr
	 */
	public String getOnlineTestCodeItr() {
		return this.onlineTestCodeItr;
	}

	/**
	 * @param onlineTestCodeItr the onlineTestCodeItr to set
	 */
	public void setOnlineTestCodeItr(String onlineTestCodeItr) {
		this.onlineTestCodeItr = onlineTestCodeItr;
	}

	/**
	 * @return the onlineTestCode
	 */
	public String getOnlineTestCode() {
		return this.onlineTestCode;
	}

	/**
	 * @param onlineTestCode the onlineTestCode to set
	 */
	public void setOnlineTestCode(String onlineTestCode) {
		this.onlineTestCode = onlineTestCode;
	}


}
