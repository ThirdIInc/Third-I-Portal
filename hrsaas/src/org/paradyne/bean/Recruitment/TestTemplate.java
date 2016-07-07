package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class TestTemplate extends BeanBase{
	
	//by varun
	private String marksAllocatedForEach = "0";
	private String equalDistriRadio ="checked";
	private String defineDistriRadio;
	private String radioOption;
	private String totalNoOfQues;
	private String randomQuestion;
	private String addQuestion = "checked";
	private String addQuestionList;
	private String marksForNoAns= "0";
	private String checkedQuestions = "";
	private String addToListQuestions = "";
	private int scriptPageNo = 10;
	
	private String selectQuesCode;
	private String quesSubject;
	private String quesName;
	private String complexicity;
	
	ArrayList<Object> quesDataList = new ArrayList<Object>();
	
	private String equalWeightage;
	private String marksForHard = "0";
	private String marksForMedium= "0";
	private String marksForEasy= "0";
	private String correctCheck = "false";
	private String markWrong;
	private String marksForNoAnswer= "0";
	private String questionFrom;
	
	private String hard;
	private String medium;
	private String easy;
	
	private String hiddenSubject;
	private String hiddenCategory;
	private String buttonPanelcode;
	
	private String notAvailable;
	private String questionListFlag = "false";
	private String subjectCategory;
	private String randomSubCode;
	private String randomCatCode;
	private String RandomComplexicity;
	private String complexicityCode;
	private String availableQuestion;
	private String testQuestion;
	ArrayList<Object> questionList = new ArrayList<Object>();
	
	private String testName;
	private String testDuration;
	private String totalQuestions;
	private String srNo;
	private String noData="false";
	private ArrayList list;
	private String listFlag = "false";
	private String markCorrect;
	
	private String testTotalMarks;
	private String passingMark;
	private String totalNoQue;
	private String instructionNotes;
	private String selectquestions;
	private String enableScore;
	private String testType;
	private String compLevel;
	private String subject;
	private String subjectFlag;
	private HashMap<Object, Object> subjectMap = new HashMap<Object, Object>();
	private String category;
	private String categoryFlag;
	private HashMap<Object, Object> categoryMap = new HashMap<Object, Object>();
	private String quescode;
	private String quesname;
	private String cancelFlag="false";
	private ArrayList testDataList;
	private String checklistCode;
	private String checklistName;
	private String testRequirements;
	private String testReqCode;
	private String flagview;
	
	private String testTemplateCode;
	private String myPage;
	private String show;
	private String testType1;
	private String  totalRecords ="";
	
	
	
	private String testName1;
	private String testDuration1;
	private String totalQuestions1;
	private String hidAjaxSubject;
	private String cCode;
	private String sCode;
	private String subCode;
	private String catCode;
	
	private ArrayList addlist;
	
	private String wrongmarksForHard= "0";
	private String wrongmarksForMedium= "0";
	private String wrongmarksForEasy= "0";
	private String wrongCheck;
	
	private int srno;
	private String selectQuCode;
	private String qusSubject;
	private String qusName;
	private String cnt;
	private String paraId;
	private String subcat;
	
	private String dupQusName;
	private String dupQusSubject;
	private String dupComplexicity;
	private String dupSelectQuCode;
	
	private String hardCompLevel;
	private String mediumCompLevel;
	private String easyCompLevel;
	
	
	private String compLevel1;
	
	
	private String cntHard;
	private String cntMedium;
	private String cntEasy;
	private String totalnoOfQus;
	
	private String markHard;
	private String markMedium;
	private String markEasy;
	private String totalMarkss;
	
	private String counterVar;
	private String hiddenQuestionCode;
	private String checkFlag;
	
	private String recCount;
	
	
	private String cntRandomHard = "0";
	private String cntRandomMedium = "0";
	private String cntRandomEasy = "0";
	private String totalRandomnoOfQus = "0";
	
	private String markRandomHard = "0";
	private String markRandomMedium = "0";
	private String markRandomEasy = "0";
	private String totalRandomMarkss = "0";
	
	
	private String equalWeightageFlag;
	private String correctCheckFlag;
	private String chFlag="false";
	private String hiddenQuCodeWithStar="";
	
	public String getWrongmarksForHard() {
		return wrongmarksForHard;
	}
	public void setWrongmarksForHard(String wrongmarksForHard) {
		this.wrongmarksForHard = wrongmarksForHard;
	}
	public String getWrongmarksForMedium() {
		return wrongmarksForMedium;
	}
	public void setWrongmarksForMedium(String wrongmarksForMedium) {
		this.wrongmarksForMedium = wrongmarksForMedium;
	}
	public String getWrongmarksForEasy() {
		return wrongmarksForEasy;
	}
	public void setWrongmarksForEasy(String wrongmarksForEasy) {
		this.wrongmarksForEasy = wrongmarksForEasy;
	}
	public String getWrongCheck() {
		return wrongCheck;
	}
	public void setWrongCheck(String wrongCheck) {
		this.wrongCheck = wrongCheck;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getHidAjaxSubject() {
		return hidAjaxSubject;
	}
	public void setHidAjaxSubject(String hidAjaxSubject) {
		this.hidAjaxSubject = hidAjaxSubject;
	}
	public String getTestName1() {
		return testName1;
	}
	public void setTestName1(String testName1) {
		this.testName1 = testName1;
	}
	public String getTestDuration1() {
		return testDuration1;
	}
	public void setTestDuration1(String testDuration1) {
		this.testDuration1 = testDuration1;
	}
	public String getTotalQuestions1() {
		return totalQuestions1;
	}
	public void setTotalQuestions1(String totalQuestions1) {
		this.totalQuestions1 = totalQuestions1;
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
	public String getQuescode() {
		return quescode;
	}
	public void setQuescode(String quescode) {
		this.quescode = quescode;
	}
	public String getQuesname() {
		return quesname;
	}
	public void setQuesname(String quesname) {
		this.quesname = quesname;
	}
	public String getCompLevel() {
		return compLevel;
	}
	public void setCompLevel(String compLevel) {
		this.compLevel = compLevel;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getMarkCorrect() {
		return markCorrect;
	}
	public void setMarkCorrect(String markCorrect) {
		this.markCorrect = markCorrect;
	}
	public String getMarkWrong() {
		return markWrong;
	}
	public void setMarkWrong(String markWrong) {
		this.markWrong = markWrong;
	}
	public String getMarksForNoAnswer() {
		return marksForNoAnswer;
	}
	public void setMarksForNoAnswer(String marksForNoAnswer) {
		this.marksForNoAnswer = marksForNoAnswer;
	}
	public String getTestTotalMarks() {
		return testTotalMarks;
	}
	public void setTestTotalMarks(String testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}
	public String getPassingMark() {
		return passingMark;
	}
	public void setPassingMark(String passingMark) {
		this.passingMark = passingMark;
	}
	public String getTotalNoQue() {
		return totalNoQue;
	}
	public void setTotalNoQue(String totalNoQue) {
		this.totalNoQue = totalNoQue;
	}
	public String getInstructionNotes() {
		return instructionNotes;
	}
	public void setInstructionNotes(String instructionNotes) {
		this.instructionNotes = instructionNotes;
	}
	public String getSelectquestions() {
		return selectquestions;
	}
	public void setSelectquestions(String selectquestions) {
		this.selectquestions = selectquestions;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(String testDuration) {
		this.testDuration = testDuration;
	}
	public String getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public HashMap<Object, Object> getSubjectMap() {
		return subjectMap;
	}
	public void setSubjectMap(HashMap<Object, Object> subjectMap) {
		this.subjectMap = subjectMap;
	}
	public String getSubjectFlag() {
		return subjectFlag;
	}
	public void setSubjectFlag(String subjectFlag) {
		this.subjectFlag = subjectFlag;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryFlag() {
		return categoryFlag;
	}
	public void setCategoryFlag(String categoryFlag) {
		this.categoryFlag = categoryFlag;
	}
	public HashMap<Object, Object> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(HashMap<Object, Object> categoryMap) {
		this.categoryMap = categoryMap;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public ArrayList getTestDataList() {
		return testDataList;
	}
	public void setTestDataList(ArrayList testDataList) {
		this.testDataList = testDataList;
	}
	public String getFlagview() {
		return flagview;
	}
	public void setFlagview(String flagview) {
		this.flagview = flagview;
	}
	
	
	
	public String getTestTemplateCode() {
		return testTemplateCode;
	}
	public void setTestTemplateCode(String testTemplateCode) {
		this.testTemplateCode = testTemplateCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the quesSubject
	 */
	public String getQuesSubject() {
		return quesSubject;
	}
	/**
	 * @param quesSubject the quesSubject to set
	 */
	public void setQuesSubject(String quesSubject) {
		this.quesSubject = quesSubject;
	}
	/**
	 * @return the quesName
	 */
	public String getQuesName() {
		return quesName;
	}
	/**
	 * @param quesName the quesName to set
	 */
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	/**
	 * @return the complexicity
	 */
	public String getComplexicity() {
		return complexicity;
	}
	/**
	 * @param complexicity the complexicity to set
	 */
	public void setComplexicity(String complexicity) {
		this.complexicity = complexicity;
	}
	/**
	 * @return the quesDataList
	 */
	public ArrayList<Object> getQuesDataList() {
		return quesDataList;
	}
	/**
	 * @param quesDataList the quesDataList to set
	 */
	public void setQuesDataList(ArrayList<Object> quesDataList) {
		this.quesDataList = quesDataList;
	}
	/**
	 * @return the selectQuesCode
	 */
	public String getSelectQuesCode() {
		return selectQuesCode;
	}
	/**
	 * @param selectQuesCode the selectQuesCode to set
	 */
	public void setSelectQuesCode(String selectQuesCode) {
		this.selectQuesCode = selectQuesCode;
	}
	/**
	 * @return the enableScore
	 */
	public String getEnableScore() {
		return enableScore;
	}
	/**
	 * @param enableScore the enableScore to set
	 */
	public void setEnableScore(String enableScore) {
		this.enableScore = enableScore;
	}
	/**
	 * @return the equalWeightage
	 */
	public String getEqualWeightage() {
		return equalWeightage;
	}
	/**
	 * @param equalWeightage the equalWeightage to set
	 */
	public void setEqualWeightage(String equalWeightage) {
		this.equalWeightage = equalWeightage;
	}
	/**
	 * @return the marksForHard
	 */
	public String getMarksForHard() {
		return marksForHard;
	}
	/**
	 * @param marksForHard the marksForHard to set
	 */
	public void setMarksForHard(String marksForHard) {
		this.marksForHard = marksForHard;
	}
	/**
	 * @return the marksForMedium
	 */
	public String getMarksForMedium() {
		return marksForMedium;
	}
	/**
	 * @param marksForMedium the marksForMedium to set
	 */
	public void setMarksForMedium(String marksForMedium) {
		this.marksForMedium = marksForMedium;
	}
	/**
	 * @return the marksForEasy
	 */
	public String getMarksForEasy() {
		return marksForEasy;
	}
	/**
	 * @param marksForEasy the marksForEasy to set
	 */
	public void setMarksForEasy(String marksForEasy) {
		this.marksForEasy = marksForEasy;
	}
	/**
	 * @return the correctCheck
	 */
	public String getCorrectCheck() {
		return correctCheck;
	}
	/**
	 * @param correctCheck the correctCheck to set
	 */
	public void setCorrectCheck(String correctCheck) {
		this.correctCheck = correctCheck;
	}
	/**
	 * @return the questionFrom
	 */
	public String getQuestionFrom() {
		return questionFrom;
	}
	/**
	 * @param questionFrom the questionFrom to set
	 */
	public void setQuestionFrom(String questionFrom) {
		this.questionFrom = questionFrom;
	}
	/**
	 * @return the notAvailable
	 */
	public String getNotAvailable() {
		return notAvailable;
	}
	/**
	 * @param notAvailable the notAvailable to set
	 */
	public void setNotAvailable(String notAvailable) {
		this.notAvailable = notAvailable;
	}
	/**
	 * @return the questionListFlag
	 */
	public String getQuestionListFlag() {
		return questionListFlag;
	}
	/**
	 * @param questionListFlag the questionListFlag to set
	 */
	public void setQuestionListFlag(String questionListFlag) {
		this.questionListFlag = questionListFlag;
	}
	/**
	 * @return the subjectCategory
	 */
	public String getSubjectCategory() {
		return subjectCategory;
	}
	/**
	 * @param subjectCategory the subjectCategory to set
	 */
	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	/**
	 * @return the complexicityCode
	 */
	public String getComplexicityCode() {
		return complexicityCode;
	}
	/**
	 * @param complexicityCode the complexicityCode to set
	 */
	public void setComplexicityCode(String complexicityCode) {
		this.complexicityCode = complexicityCode;
	}
	/**
	 * @return the availableQuestion
	 */
	public String getAvailableQuestion() {
		return availableQuestion;
	}
	/**
	 * @param availableQuestion the availableQuestion to set
	 */
	public void setAvailableQuestion(String availableQuestion) {
		this.availableQuestion = availableQuestion;
	}
	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(ArrayList<Object> questionList) {
		this.questionList = questionList;
	}
	/**
	 * @return the randomSubCode
	 */
	public String getRandomSubCode() {
		return randomSubCode;
	}
	/**
	 * @param randomSubCode the randomSubCode to set
	 */
	public void setRandomSubCode(String randomSubCode) {
		this.randomSubCode = randomSubCode;
	}
	/**
	 * @return the randomCatCode
	 */
	public String getRandomCatCode() {
		return randomCatCode;
	}
	/**
	 * @param randomCatCode the randomCatCode to set
	 */
	public void setRandomCatCode(String randomCatCode) {
		this.randomCatCode = randomCatCode;
	}
	/**
	 * @return the questionList
	 */
	public ArrayList<Object> getQuestionList() {
		return questionList;
	}
	/**
	 * @return the randomComplexicity
	 */
	public String getRandomComplexicity() {
		return RandomComplexicity;
	}
	/**
	 * @param randomComplexicity the randomComplexicity to set
	 */
	public void setRandomComplexicity(String randomComplexicity) {
		RandomComplexicity = randomComplexicity;
	}
	/**
	 * @return the hiddenSubject
	 */
	public String getHiddenSubject() {
		return hiddenSubject;
	}
	/**
	 * @param hiddenSubject the hiddenSubject to set
	 */
	public void setHiddenSubject(String hiddenSubject) {
		this.hiddenSubject = hiddenSubject;
	}
	/**
	 * @return the hiddenCategory
	 */
	public String getHiddenCategory() {
		return hiddenCategory;
	}
	/**
	 * @param hiddenCategory the hiddenCategory to set
	 */
	public void setHiddenCategory(String hiddenCategory) {
		this.hiddenCategory = hiddenCategory;
	}
	/**
	 * @return the testQuestion
	 */
	public String getTestQuestion() {
		return testQuestion;
	}
	/**
	 * @param testQuestion the testQuestion to set
	 */
	public void setTestQuestion(String testQuestion) {
		this.testQuestion = testQuestion;
	}
	/**
	 * @return the hard
	 */
	public String getHard() {
		return hard;
	}
	/**
	 * @param hard the hard to set
	 */
	public void setHard(String hard) {
		this.hard = hard;
	}
	/**
	 * @return the medium
	 */
	public String getMedium() {
		return medium;
	}
	/**
	 * @param medium the medium to set
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}
	/**
	 * @return the easy
	 */
	public String getEasy() {
		return easy;
	}
	/**
	 * @param easy the easy to set
	 */
	public void setEasy(String easy) {
		this.easy = easy;
	}
	/**
	 * @return the buttonPanelcode
	 */
	public String getButtonPanelcode() {
		return buttonPanelcode;
	}
	/**
	 * @param buttonPanelcode the buttonPanelcode to set
	 */
	public void setButtonPanelcode(String buttonPanelcode) {
		this.buttonPanelcode = buttonPanelcode;
	}
	public String getTestType1() {
		return testType1;
	}
	public void setTestType1(String testType1) {
		this.testType1 = testType1;
	}
	public String getCCode() {
		return cCode;
	}
	public void setCCode(String code) {
		cCode = code;
	}
	public String getSCode() {
		return sCode;
	}
	public void setSCode(String code) {
		sCode = code;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getSelectQuCode() {
		return selectQuCode;
	}
	public void setSelectQuCode(String selectQuCode) {
		this.selectQuCode = selectQuCode;
	}
	public String getQusSubject() {
		return qusSubject;
	}
	public void setQusSubject(String qusSubject) {
		this.qusSubject = qusSubject;
	}
	public String getQusName() {
		return qusName;
	}
	public void setQusName(String qusName) {
		this.qusName = qusName;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getSubcat() {
		return subcat;
	}
	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}
	public ArrayList getAddlist() {
		return addlist;
	}
	public void setAddlist(ArrayList addlist) {
		this.addlist = addlist;
	}
	public String getDupQusName() {
		return dupQusName;
	}
	public void setDupQusName(String dupQusName) {
		this.dupQusName = dupQusName;
	}
	public String getDupQusSubject() {
		return dupQusSubject;
	}
	public void setDupQusSubject(String dupQusSubject) {
		this.dupQusSubject = dupQusSubject;
	}
	public String getDupComplexicity() {
		return dupComplexicity;
	}
	public void setDupComplexicity(String dupComplexicity) {
		this.dupComplexicity = dupComplexicity;
	}
	public String getDupSelectQuCode() {
		return dupSelectQuCode;
	}
	public void setDupSelectQuCode(String dupSelectQuCode) {
		this.dupSelectQuCode = dupSelectQuCode;
	}
	public String getCompLevel1() {
		return compLevel1;
	}
	public void setCompLevel1(String compLevel1) {
		this.compLevel1 = compLevel1;
	}
	public String getHardCompLevel() {
		return hardCompLevel;
	}
	public void setHardCompLevel(String hardCompLevel) {
		this.hardCompLevel = hardCompLevel;
	}
	public String getMediumCompLevel() {
		return mediumCompLevel;
	}
	public void setMediumCompLevel(String mediumCompLevel) {
		this.mediumCompLevel = mediumCompLevel;
	}
	public String getEasyCompLevel() {
		return easyCompLevel;
	}
	public void setEasyCompLevel(String easyCompLevel) {
		this.easyCompLevel = easyCompLevel;
	}
	public String getCntHard() {
		return cntHard;
	}
	public void setCntHard(String cntHard) {
		this.cntHard = cntHard;
	}
	public String getCntMedium() {
		return cntMedium;
	}
	public void setCntMedium(String cntMedium) {
		this.cntMedium = cntMedium;
	}
	public String getCntEasy() {
		return cntEasy;
	}
	public void setCntEasy(String cntEasy) {
		this.cntEasy = cntEasy;
	}
	public String getTotalnoOfQus() {
		return totalnoOfQus;
	}
	public void setTotalnoOfQus(String totalnoOfQus) {
		this.totalnoOfQus = totalnoOfQus;
	}
	public String getMarkHard() {
		return markHard;
	}
	public void setMarkHard(String markHard) {
		this.markHard = markHard;
	}
	public String getMarkMedium() {
		return markMedium;
	}
	public void setMarkMedium(String markMedium) {
		this.markMedium = markMedium;
	}
	public String getMarkEasy() {
		return markEasy;
	}
	public void setMarkEasy(String markEasy) {
		this.markEasy = markEasy;
	}
	public String getTotalMarkss() {
		return totalMarkss;
	}
	public void setTotalMarkss(String totalMarkss) {
		this.totalMarkss = totalMarkss;
	}
	public String getCounterVar() {
		return counterVar;
	}
	public void setCounterVar(String counterVar) {
		this.counterVar = counterVar;
	}
	public String getHiddenQuestionCode() {
		return hiddenQuestionCode;
	}
	public void setHiddenQuestionCode(String hiddenQuestionCode) {
		this.hiddenQuestionCode = hiddenQuestionCode;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getRecCount() {
		return recCount;
	}
	public void setRecCount(String recCount) {
		this.recCount = recCount;
	}
	public String getCntRandomHard() {
		return cntRandomHard;
	}
	public void setCntRandomHard(String cntRandomHard) {
		this.cntRandomHard = cntRandomHard;
	}
	public String getCntRandomMedium() {
		return cntRandomMedium;
	}
	public void setCntRandomMedium(String cntRandomMedium) {
		this.cntRandomMedium = cntRandomMedium;
	}
	public String getCntRandomEasy() {
		return cntRandomEasy;
	}
	public void setCntRandomEasy(String cntRandomEasy) {
		this.cntRandomEasy = cntRandomEasy;
	}
	public String getTotalRandomnoOfQus() {
		return totalRandomnoOfQus;
	}
	public void setTotalRandomnoOfQus(String totalRandomnoOfQus) {
		this.totalRandomnoOfQus = totalRandomnoOfQus;
	}
	public String getMarkRandomHard() {
		return markRandomHard;
	}
	public void setMarkRandomHard(String markRandomHard) {
		this.markRandomHard = markRandomHard;
	}
	public String getMarkRandomMedium() {
		return markRandomMedium;
	}
	public void setMarkRandomMedium(String markRandomMedium) {
		this.markRandomMedium = markRandomMedium;
	}
	public String getMarkRandomEasy() {
		return markRandomEasy;
	}
	public void setMarkRandomEasy(String markRandomEasy) {
		this.markRandomEasy = markRandomEasy;
	}
	public String getTotalRandomMarkss() {
		return totalRandomMarkss;
	}
	public void setTotalRandomMarkss(String totalRandomMarkss) {
		this.totalRandomMarkss = totalRandomMarkss;
	}
	public String getEqualWeightageFlag() {
		return equalWeightageFlag;
	}
	public void setEqualWeightageFlag(String equalWeightageFlag) {
		this.equalWeightageFlag = equalWeightageFlag;
	}
	public String getCorrectCheckFlag() {
		return correctCheckFlag;
	}
	public void setCorrectCheckFlag(String correctCheckFlag) {
		this.correctCheckFlag = correctCheckFlag;
	}
	 
	public String getChFlag() {
		return chFlag;
	}
	public void setChFlag(String chFlag) {
		this.chFlag = chFlag;
	}
	public String getHiddenQuCodeWithStar() {
		return hiddenQuCodeWithStar;
	}
	public void setHiddenQuCodeWithStar(String hiddenQuCodeWithStar) {
		this.hiddenQuCodeWithStar = hiddenQuCodeWithStar;
	}
	public String getMarksAllocatedForEach() {
		return marksAllocatedForEach;
	}
	public void setMarksAllocatedForEach(String marksAllocatedForEach) {
		this.marksAllocatedForEach = marksAllocatedForEach;
	}
	public String getEqualDistriRadio() {
		return equalDistriRadio;
	}
	public void setEqualDistriRadio(String equalDistriRadio) {
		this.equalDistriRadio = equalDistriRadio;
	}
	public String getDefineDistriRadio() {
		return defineDistriRadio;
	}
	public void setDefineDistriRadio(String defineDistriRadio) {
		this.defineDistriRadio = defineDistriRadio;
	}
	public String getRadioOption() {
		return radioOption;
	}
	public void setRadioOption(String radioOption) {
		this.radioOption = radioOption;
	}
	public String getTotalNoOfQues() {
		return totalNoOfQues;
	}
	public void setTotalNoOfQues(String totalNoOfQues) {
		this.totalNoOfQues = totalNoOfQues;
	}
	public String getRandomQuestion() {
		return randomQuestion;
	}
	public void setRandomQuestion(String randomQuestion) {
		this.randomQuestion = randomQuestion;
	}
	public String getAddQuestion() {
		return addQuestion;
	}
	public void setAddQuestion(String addQuestion) {
		this.addQuestion = addQuestion;
	}
	public String getAddQuestionList() {
		return addQuestionList;
	}
	public void setAddQuestionList(String addQuestionList) {
		this.addQuestionList = addQuestionList;
	}
	public String getMarksForNoAns() {
		return marksForNoAns;
	}
	public void setMarksForNoAns(String marksForNoAns) {
		this.marksForNoAns = marksForNoAns;
	}
	public String getCheckedQuestions() {
		return checkedQuestions;
	}
	public void setCheckedQuestions(String checkedQuestions) {
		this.checkedQuestions = checkedQuestions;
	}
	public String getAddToListQuestions() {
		return addToListQuestions;
	}
	public void setAddToListQuestions(String addToListQuestions) {
		this.addToListQuestions = addToListQuestions;
	}
	public int getScriptPageNo() {
		return scriptPageNo;
	}
	public void setScriptPageNo(int scriptPageNo) {
		this.scriptPageNo = scriptPageNo;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
		
}
