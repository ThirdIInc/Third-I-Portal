package org.paradyne.bean.WBT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.paradyne.lib.BeanBase;

public class OnlineProgram extends BeanBase {
	
	private String userSessionId ="";
	private String remainingAttemptCount ="";
	private String sectionAttemptRemaining  ="";
	private String attempt  ="";
	private String attemptsRemaining  ="";
	private String sectionNameItt  ="";
	private String sectionCodeItt  ="";
	private String sectionCompletionStatusItt  ="";
	private String sectionMarksItt  ="";
	private String sectionResultItt  ="";
	private ArrayList sectionList=null;
	
	private String moduleNameItt ="";
	private String moduleCodeItt ="";
	private String completionStatusItt ="";
	private String moduleMarksItt ="";
	private String moduleResultItt ="";
	private String isOrderRequired ="";
	
	private ArrayList moduleList=null;
	
	private String finishBtnFlag ="";
	private String passColor =""; 
	private String clientInfoId =""; 
	private String userCode ="";
	private String  sectionTakeTestBtnFlag ="true";
	private String userType ="";
	 
	private String isRandomQues="";
	private String dataPath ="";
	private String isModuleCompleted ="";
	private String testProgramName ="";
	private String  testSectionDisplayFlag ="false";
	private String sectionDisplayFlag  ="";
	private String noQuesAvailable ="";
	private String testStatusCheck = "";
	private String takeTestBtnFlag="true";
	private String  equalMarkForQueTypeCheck = "";
	private String testTemplateCode= "";
	private String tempName= "";
	private String tempDuration= "";
	private String tempTotalQues= "";
	private String tempInstruction= "";
	private String testType= "";
	
	private String equalweightage = "";
	private String marksWrongAns = "";
	private String marksNoAns = "";
	private String onlineScore = "";
	private String chkOnlineScoreFlag = "false";
 
	
	private String marksHard = "";
	private String marksMedium = "";
	private String marksEasy = "";

	private String wrongmarksHard = "";
	private String wrongmarksEasy = "";
	private String wrongmarksMedium = "";
	private String equalMarksForAll= "";

	private String resultFlag = "N";
	private String tempTotalMarks = "";
	private String result = "";
	private String passingMarks = "";
	private String marksObtained = "";
	private String percentage = "";
	private String blankAnswer = "";
	private String correctMarks = "";
	private String negativeMarks = "";

	private String sectionName = "";

	private String sectionProgramName = "";
	private String source = "";
	private String testSectionName = "";
	private String testSectionCode = "";

	private String sectionCode = "";

	private String showModuleDtlFlag = "";
	private String marksForCorrect = "";
	private String answerUploadedDoc = "";
	private String questionUploadedDoc = "";
	private boolean documentNotAttachedFlag = false;
	private String subjectAns = "";
	private String subjAnswerLimit = "";

	private String testProgramCode = "";

	private String sectionProgramCode = "";

	private String testModuleName = "";

	private String testModuleCode = "";

	private String sequenceCode = "";

	private String lastQuestionSequence = "";

	private String randomQuesCodes = "";
	private ArrayList randomQueList = null;
	private String optionFlag = "true";
	private String nextButtonFlag = "true";
	private String previousButton = "";
	private String submitButtonFlag = "";

	private ArrayList optionList = null;
	private String chk = "";
	private String optionName = "";
	private String optionCode = "";
	private String optionAnswer = "";

	private String questionName = "";
	private String questionLevel = "";

	private String moduleContent = "";

	private String sectionModuleName = "";
	private String sectionModuleCode = "";
	private String moduleInstruction = "";

	private String moduleName = "";
	private String progrmInstructions = "";
	private String programCode = "";

	private String prgCode = "";
	private String prgName = "";
	private String prgDuration = "";
	private String prgInstruction = "";

	private String programName = "";
	private String applicationCode = "";
	private String programTemplateName = "";
	private String programTemplateCode = "";
	private String programTimeItr = "";
	private String onlineProgramCodeItr = "";
	private String programDateItr = "";
	private ArrayList programList = null;

	private Map contentList = null; // to set the module or sections content list dropdown
	private String contentId = "";
	private String instance_name = "";
	
	private String userDatetime = "";
	
	
	/**
	 * @return the instance_name
	 */
	public String getInstance_name() {
		return instance_name;
	}

	/**
	 * @param instance_name the instance_name to set
	 */
	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the contentList
	 */
	public Map getContentList() {
		return contentList;
	}

	/**
	 * @param contentList the contentList to set
	 */
	public void setContentList(Map contentList) {
		this.contentList = contentList;
	}

	public ArrayList getProgramList() {
		return programList;
	}

	public void setProgramList(ArrayList programList) {
		this.programList = programList;
	}

	public String getProgramDateItr() {
		return programDateItr;
	}

	public void setProgramDateItr(String programDateItr) {
		this.programDateItr = programDateItr;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramTemplateName() {
		return programTemplateName;
	}

	public void setProgramTemplateName(String programTemplateName) {
		this.programTemplateName = programTemplateName;
	}

	public String getProgramTemplateCode() {
		return programTemplateCode;
	}

	public void setProgramTemplateCode(String programTemplateCode) {
		this.programTemplateCode = programTemplateCode;
	}

	public String getProgramTimeItr() {
		return programTimeItr;
	}

	public void setProgramTimeItr(String programTimeItr) {
		this.programTimeItr = programTimeItr;
	}

	public String getOnlineProgramCodeItr() {
		return onlineProgramCodeItr;
	}

	public void setOnlineProgramCodeItr(String onlineProgramCodeItr) {
		this.onlineProgramCodeItr = onlineProgramCodeItr;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getPrgCode() {
		return prgCode;
	}

	public void setPrgCode(String prgCode) {
		this.prgCode = prgCode;
	}

	public String getPrgName() {
		return prgName;
	}

	public void setPrgName(String prgName) {
		this.prgName = prgName;
	}

	public String getPrgDuration() {
		return prgDuration;
	}

	public void setPrgDuration(String prgDuration) {
		this.prgDuration = prgDuration;
	}

	public String getPrgInstruction() {
		return prgInstruction;
	}

	public void setPrgInstruction(String prgInstruction) {
		this.prgInstruction = prgInstruction;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getProgrmInstructions() {
		return progrmInstructions;
	}

	public void setProgrmInstructions(String progrmInstructions) {
		this.progrmInstructions = progrmInstructions;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getSectionModuleName() {
		return sectionModuleName;
	}

	public void setSectionModuleName(String sectionModuleName) {
		this.sectionModuleName = sectionModuleName;
	}

	public String getSectionModuleCode() {
		return sectionModuleCode;
	}

	public void setSectionModuleCode(String sectionModuleCode) {
		this.sectionModuleCode = sectionModuleCode;
	}

	public String getModuleInstruction() {
		return moduleInstruction;
	}

	public void setModuleInstruction(String moduleInstruction) {
		this.moduleInstruction = moduleInstruction;
	}

	public String getModuleContent() {
		return moduleContent;
	}

	public void setModuleContent(String moduleContent) {
		this.moduleContent = moduleContent;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}

	public ArrayList getOptionList() {
		return optionList;
	}

	public void setOptionList(ArrayList optionList) {
		this.optionList = optionList;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
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

	public String getNextButtonFlag() {
		return nextButtonFlag;
	}

	public void setNextButtonFlag(String nextButtonFlag) {
		this.nextButtonFlag = nextButtonFlag;
	}

	public String getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(String previousButton) {
		this.previousButton = previousButton;
	}

	public String getSubmitButtonFlag() {
		return submitButtonFlag;
	}

	public void setSubmitButtonFlag(String submitButtonFlag) {
		this.submitButtonFlag = submitButtonFlag;
	}

	public String getRandomQuesCodes() {
		return randomQuesCodes;
	}

	public void setRandomQuesCodes(String randomQuesCodes) {
		this.randomQuesCodes = randomQuesCodes;
	}

	public ArrayList getRandomQueList() {
		return randomQueList;
	}

	public void setRandomQueList(ArrayList randomQueList) {
		this.randomQueList = randomQueList;
	}

	public String getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}

	public String getTestModuleName() {
		return testModuleName;
	}

	public void setTestModuleName(String testModuleName) {
		this.testModuleName = testModuleName;
	}

	public String getTestModuleCode() {
		return testModuleCode;
	}

	public void setTestModuleCode(String testModuleCode) {
		this.testModuleCode = testModuleCode;
	}

	public String getLastQuestionSequence() {
		return lastQuestionSequence;
	}

	public void setLastQuestionSequence(String lastQuestionSequence) {
		this.lastQuestionSequence = lastQuestionSequence;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getSectionProgramCode() {
		return sectionProgramCode;
	}

	public void setSectionProgramCode(String sectionProgramCode) {
		this.sectionProgramCode = sectionProgramCode;
	}

	public String getTestProgramCode() {
		return testProgramCode;
	}

	public void setTestProgramCode(String testProgramCode) {
		this.testProgramCode = testProgramCode;
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

	public String getQuestionUploadedDoc() {
		return questionUploadedDoc;
	}

	public void setQuestionUploadedDoc(String questionUploadedDoc) {
		this.questionUploadedDoc = questionUploadedDoc;
	}

	public boolean isDocumentNotAttachedFlag() {
		return documentNotAttachedFlag;
	}

	public void setDocumentNotAttachedFlag(boolean documentNotAttachedFlag) {
		this.documentNotAttachedFlag = documentNotAttachedFlag;
	}

	public String getAnswerUploadedDoc() {
		return answerUploadedDoc;
	}

	public void setAnswerUploadedDoc(String answerUploadedDoc) {
		this.answerUploadedDoc = answerUploadedDoc;
	}

	public String getMarksForCorrect() {
		return marksForCorrect;
	}

	public void setMarksForCorrect(String marksForCorrect) {
		this.marksForCorrect = marksForCorrect;
	}

	public String getShowModuleDtlFlag() {
		return showModuleDtlFlag;
	}

	public void setShowModuleDtlFlag(String showModuleDtlFlag) {
		this.showModuleDtlFlag = showModuleDtlFlag;
	}

	public String getTestSectionName() {
		return testSectionName;
	}

	public void setTestSectionName(String testSectionName) {
		this.testSectionName = testSectionName;
	}

	public String getTestSectionCode() {
		return testSectionCode;
	}

	public void setTestSectionCode(String testSectionCode) {
		this.testSectionCode = testSectionCode;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSectionProgramName() {
		return sectionProgramName;
	}

	public void setSectionProgramName(String sectionProgramName) {
		this.sectionProgramName = sectionProgramName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTempTotalMarks() {
		return tempTotalMarks;
	}

	public void setTempTotalMarks(String tempTotalMarks) {
		this.tempTotalMarks = tempTotalMarks;
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

	public String getEqualMarksForAll() {
		return equalMarksForAll;
	}

	public void setEqualMarksForAll(String equalMarksForAll) {
		this.equalMarksForAll = equalMarksForAll;
	}

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getEqualweightage() {
		return equalweightage;
	}

	public void setEqualweightage(String equalweightage) {
		this.equalweightage = equalweightage;
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

	public String getTestTemplateCode() {
		return testTemplateCode;
	}

	public void setTestTemplateCode(String testTemplateCode) {
		this.testTemplateCode = testTemplateCode;
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

	public String getTempTotalQues() {
		return tempTotalQues;
	}

	public void setTempTotalQues(String tempTotalQues) {
		this.tempTotalQues = tempTotalQues;
	}

	public String getTempInstruction() {
		return tempInstruction;
	}

	public void setTempInstruction(String tempInstruction) {
		this.tempInstruction = tempInstruction;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getEqualMarkForQueTypeCheck() {
		return equalMarkForQueTypeCheck;
	}

	public void setEqualMarkForQueTypeCheck(String equalMarkForQueTypeCheck) {
		this.equalMarkForQueTypeCheck = equalMarkForQueTypeCheck;
	}

	public String getTakeTestBtnFlag() {
		return takeTestBtnFlag;
	}

	public void setTakeTestBtnFlag(String takeTestBtnFlag) {
		this.takeTestBtnFlag = takeTestBtnFlag;
	}

	 

	public String getTestStatusCheck() {
		return testStatusCheck;
	}

	public void setTestStatusCheck(String testStatusCheck) {
		this.testStatusCheck = testStatusCheck;
	}

	public String getNoQuesAvailable() {
		return noQuesAvailable;
	}

	public void setNoQuesAvailable(String noQuesAvailable) {
		this.noQuesAvailable = noQuesAvailable;
	}

	public String getSectionDisplayFlag() {
		return sectionDisplayFlag;
	}

	public void setSectionDisplayFlag(String sectionDisplayFlag) {
		this.sectionDisplayFlag = sectionDisplayFlag;
	}

	public String getTestSectionDisplayFlag() {
		return testSectionDisplayFlag;
	}

	public void setTestSectionDisplayFlag(String testSectionDisplayFlag) {
		this.testSectionDisplayFlag = testSectionDisplayFlag;
	}

	public String getTestProgramName() {
		return testProgramName;
	}

	public void setTestProgramName(String testProgramName) {
		this.testProgramName = testProgramName;
	}

	public String getIsModuleCompleted() {
		return isModuleCompleted;
	}

	public void setIsModuleCompleted(String isModuleCompleted) {
		this.isModuleCompleted = isModuleCompleted;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsRandomQues() {
		return isRandomQues;
	}

	public void setIsRandomQues(String isRandomQues) {
		this.isRandomQues = isRandomQues;
	}

	public String getClientInfoId() {
		return clientInfoId;
	}

	public void setClientInfoId(String clientInfoId) {
		this.clientInfoId = clientInfoId;
	}

	public String getSectionTakeTestBtnFlag() {
		return sectionTakeTestBtnFlag;
	}

	public void setSectionTakeTestBtnFlag(String sectionTakeTestBtnFlag) {
		this.sectionTakeTestBtnFlag = sectionTakeTestBtnFlag;
	}

	public String getPassColor() {
		return passColor;
	}

	public void setPassColor(String passColor) {
		this.passColor = passColor;
	}

	public String getFinishBtnFlag() {
		return finishBtnFlag;
	}

	public void setFinishBtnFlag(String finishBtnFlag) {
		this.finishBtnFlag = finishBtnFlag;
	}

	public String getModuleNameItt() {
		return moduleNameItt;
	}

	public void setModuleNameItt(String moduleNameItt) {
		this.moduleNameItt = moduleNameItt;
	}

	public String getModuleCodeItt() {
		return moduleCodeItt;
	}

	public void setModuleCodeItt(String moduleCodeItt) {
		this.moduleCodeItt = moduleCodeItt;
	}

	public String getCompletionStatusItt() {
		return completionStatusItt;
	}

	public void setCompletionStatusItt(String completionStatusItt) {
		this.completionStatusItt = completionStatusItt;
	}

	public String getModuleMarksItt() {
		return moduleMarksItt;
	}

	public void setModuleMarksItt(String moduleMarksItt) {
		this.moduleMarksItt = moduleMarksItt;
	}

	public String getModuleResultItt() {
		return moduleResultItt;
	}

	public void setModuleResultItt(String moduleResultItt) {
		this.moduleResultItt = moduleResultItt;
	}

	public String getIsOrderRequired() {
		return isOrderRequired;
	}

	public void setIsOrderRequired(String isOrderRequired) {
		this.isOrderRequired = isOrderRequired;
	}

	public ArrayList getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}

	public String getSectionNameItt() {
		return sectionNameItt;
	}

	public void setSectionNameItt(String sectionNameItt) {
		this.sectionNameItt = sectionNameItt;
	}

	public String getSectionCodeItt() {
		return sectionCodeItt;
	}

	public void setSectionCodeItt(String sectionCodeItt) {
		this.sectionCodeItt = sectionCodeItt;
	}

	public String getSectionCompletionStatusItt() {
		return sectionCompletionStatusItt;
	}

	public void setSectionCompletionStatusItt(String sectionCompletionStatusItt) {
		this.sectionCompletionStatusItt = sectionCompletionStatusItt;
	}

	public String getSectionMarksItt() {
		return sectionMarksItt;
	}

	public void setSectionMarksItt(String sectionMarksItt) {
		this.sectionMarksItt = sectionMarksItt;
	}

	public String getSectionResultItt() {
		return sectionResultItt;
	}

	public void setSectionResultItt(String sectionResultItt) {
		this.sectionResultItt = sectionResultItt;
	}

	public ArrayList getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}

	public String getAttempt() {
		return attempt;
	}

	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}

	public String getAttemptsRemaining() {
		return attemptsRemaining;
	}

	public void setAttemptsRemaining(String attemptsRemaining) {
		this.attemptsRemaining = attemptsRemaining;
	}

	public String getSectionAttemptRemaining() {
		return sectionAttemptRemaining;
	}

	public void setSectionAttemptRemaining(String sectionAttemptRemaining) {
		this.sectionAttemptRemaining = sectionAttemptRemaining;
	}

	public String getRemainingAttemptCount() {
		return remainingAttemptCount;
	}

	public void setRemainingAttemptCount(String remainingAttemptCount) {
		this.remainingAttemptCount = remainingAttemptCount;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

	public String getUserDatetime() {
		return userDatetime;
	}

	public void setUserDatetime(String userDatetime) {
		this.userDatetime = userDatetime;
	}
 
}
