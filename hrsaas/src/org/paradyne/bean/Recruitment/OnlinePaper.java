package org.paradyne.bean.Recruitment;
import java.util.ArrayList;


//import org.paradyne.lib.ModelBase;

import org.paradyne.lib.BeanBase;

public class OnlinePaper extends BeanBase {
	
	
	 private String subjectName;
	 private String paperName;
	 private String paperCode;
	 private String subjectMarks;
	 private String paperTimeDuration;
	 private String passMarks;
     private String subject;
     private String paperPassCrieteria;
     private String totalNoQuestion;
     private String  mediumQuestion;
	 private String hardQuestion;
	 private String easyQuestion;
	 private String  noOfQuestionPerPage;
	 private String totalNoOfQues;
	 private String subjectCodeItt;
	 private String srNoItt;
	 private String subjectCode;
	 private String subItt;
	 private String numofItt;
	 private String hardItt;
	 private String easyItt;
	 private String mediumItt;
	 private String paperTimeDurationItt;
	 private String noOfQuestionPerPageItt;
	 private  ArrayList list;		
	 private String  subjectCodehdd;
	 private ArrayList show;
	 private String noOfQuestion;
	 private ArrayList showPriview;
	 private ArrayList showPriviewOption;
	 private String questionOption;
	 private String startTime;
	 private String endTime;
	 private String questionDesc;
	 private String questionLevel;
	 private String questionType;
	 private String hiddenNext="";
	 private boolean chk=false;
	 private boolean  flag=false;
	 private boolean  flag1=false;
	 private String quesCodeItt;
	 private String quesTypeItt;
	 private String quesLevelItt;
	 private String quesDescItt;
	 private String  SrNo;
	 private String quesCode;
	 private String questionCode;
	 private String nextCode;
	 private String  optionCode;
	 private String  recTotal;
	 private String  total;
	 private String hiddenPrivious;
	 private String text;
	 private String timerFlag="";
	 private String chkObject="";
	 private String quesCodeChk="";
	 private String SubjectQuestCode="";
	 private String Flag2="false";
	 private String  candidateCode="";
	 private static String infoId = "";
	 String loginName=null;
	 String loginPassword=null;
	 private String testCode;
	 private String testDate;
	 private String testTimeStart;
	 private String testPaperCode;
	 private String testScoreTotal;
	 private String testResult;
	 private String testTimeEnd;
	 private String  testSubjectCode;
	 private String testQuesCode;
	 private String testCandidateAnswere;
	 private String chkFlag="false";
	 private String chkobj="false";
	 private String chkNextValue="";
	 private String chkNextPrevFlag="";
	 
	 private String testObjectiveScore;
	 private String testQuesType;
	 private String SubjOpt;
	 
	 private String  papName;
	 private String  tQues;
	 private String totalQue;
	  public String getTotalQue() {
		return totalQue;
	}

	public void setTotalQue(String totalQue) {
		this.totalQue = totalQue;
	}

	public String getTestSubjectCode() {
		return testSubjectCode;
	}

	public void setTestSubjectCode(String testSubjectCode) {
		this.testSubjectCode = testSubjectCode;
	}

	public String getTestQuesCode() {
		return testQuesCode;
	}

	public void setTestQuesCode(String testQuesCode) {
		this.testQuesCode = testQuesCode;
	}

	public String getTestCandidateAnswere() {
		return testCandidateAnswere;
	}

	public void setTestCandidateAnswere(String testCandidateAnswere) {
		this.testCandidateAnswere = testCandidateAnswere;
	}

	public String getTestObjectiveScore() {
		return testObjectiveScore;
	}

	public void setTestObjectiveScore(String testObjectiveScore) {
		this.testObjectiveScore = testObjectiveScore;
	}

	

	public String getTestQuesType() {
		return testQuesType;
	}

	public void setTestQuesType(String testQuesType) {
		this.testQuesType = testQuesType;
	}

	public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getLoginPassword() {
			return loginPassword;
		}

		public void setLoginPassword(String loginPassword) {
			this.loginPassword = loginPassword;
		}

	public String getCandidateCode() {
			return candidateCode;
		}

		public void setCandidateCode(String candidateCode) {
			this.candidateCode = candidateCode;
		}

		public static String getInfoId() {
			return infoId;
		}

		public static void setInfoId(String infoId) {
			OnlinePaper.infoId = infoId;
		}

	public String getFlag2() {
		return Flag2;
	}

	public void setFlag2(String flag2) {
		Flag2 = flag2;
	}

	public String getTimerFlag() {
		return timerFlag;
	}

	public void setTimerFlag(String timerFlag) {
		this.timerFlag = timerFlag;
	}

	public String getHiddenPrivious() {
		return hiddenPrivious;
	}

	public void setHiddenPrivious(String hiddenPrivious) {
		this.hiddenPrivious = hiddenPrivious;
	}

	//private String quesCode;
	 //public String   paperCode;
	 public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getTotalNoOfQues() {
		return totalNoOfQues;
	}

	public void setTotalNoOfQues(String totalNoOfQues) {
		this.totalNoOfQues = totalNoOfQues;
	}

	public String getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(String noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	public OnlinePaper() {
			super();}
			
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getPaperCode() {
		return paperCode;
	}
	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}
	public String getSubjectMarsk() {
		return subjectMarks;
	}
	public void setSubjectMarks(String subjectMarks) {
		this.subjectMarks = subjectMarks;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getHardQuestion() {
		return hardQuestion;
	}
	public void setHardQuestion(String hardQuestion) {
		this.hardQuestion = hardQuestion;
	}
	
	public String getPaperTimeDuration() {
		return paperTimeDuration;
	}
	public void setPaperTimeDuration(String paperTimeDuration) {
		this.paperTimeDuration = paperTimeDuration;
	}
	public String getPassMarks() {
		return passMarks;
	}
	public void setPassMarks(String passMarks) {
		this.passMarks = passMarks;
	}
	public String getEasyQuestion() {
		return easyQuestion;
	}
	public void setEasyQuestion(String easyQuestion) {
		this.easyQuestion = easyQuestion;
	}

	public String getSubjectMarks() {
		return subjectMarks;
	}

	public String getSrNoItt() {
		return srNoItt;
	}

	public void setSrNoItt(String srNoItt) {
		this.srNoItt = srNoItt;
	}

	public String getSubItt() {
		return subItt;
	}

	public void setSubItt(String subItt) {
		this.subItt = subItt;
	}

	public String getNumofItt() {
		return numofItt;
	}

	public void setNumofItt(String numofItt) {
		this.numofItt = numofItt;
	}

	public String getHardItt() {
		return hardItt;
	}

	public void setHardItt(String hardItt) {
		this.hardItt = hardItt;
	}

	public String getEasyItt() {
		return easyItt;
	}

	public void setEasyItt(String easyItt) {
		this.easyItt = easyItt;
	}

	public String getMediumItt() {
		return mediumItt;
	}

	public void setMediumItt(String mediumItt) {
		this.mediumItt = mediumItt;
	}

	public String getSubjectCodeItt() {
		return subjectCodeItt;
	}

	public void setSubjectCodeItt(String subjectCodeItt) {
		this.subjectCodeItt = subjectCodeItt;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	

	public String getPaperPassCrieteria() {
		return paperPassCrieteria;
	}

	public void setPaperPassCrieteria(String paperPassCrieteria) {
		this.paperPassCrieteria = paperPassCrieteria;
	}

	public String getTotalNoQuestion() {
		return totalNoQuestion;
	}

	public void setTotalNoQuestion(String totalNoQuestion) {
		this.totalNoQuestion = totalNoQuestion;
	}

	public ArrayList getShow() {
		return show;
	}

	public void setShow(ArrayList show) {
		this.show = show;
	}

	public ArrayList getList() {
		return list;
	}
		

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getMediumQuestion() {
		return mediumQuestion;
	}

	public void setMediumQuestion(String mediumQuestion) {
		this.mediumQuestion = mediumQuestion;
	}

	public String getNoOfQuestionPerPage() {
		return noOfQuestionPerPage;
	}

	public void setNoOfQuestionPerPage(String noOfQuestionPerPage) {
		this.noOfQuestionPerPage = noOfQuestionPerPage;
	}

	public String getPaperTimeDurationItt() {
		return paperTimeDurationItt;
	}

	public String getNoOfQuestionPerPageItt() {
		return noOfQuestionPerPageItt;
	}

	public void setNoOfQuestionPerPageItt(String noOfQuestionPerPageItt) {
		this.noOfQuestionPerPageItt = noOfQuestionPerPageItt;
	}

	public String getSubjectCodehdd() {
		return subjectCodehdd;
	}

	public void setSubjectCodehdd(String subjectCodehdd) {
		this.subjectCodehdd = subjectCodehdd;
	}

	public String getQuesCode() {
		return quesCode;
	}

	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuesTypeItt() {
		return quesTypeItt;
	}

	public void setQuesTypeItt(String quesTypeItt) {
		this.quesTypeItt = quesTypeItt;
	}

	public String getQuesLevelItt() {
		return quesLevelItt;
	}

	public void setQuesLevelItt(String quesLevelItt) {
		this.quesLevelItt = quesLevelItt;
	}

	public String getQuesDescItt() {
		return quesDescItt;
	}

	public void setQuesDescItt(String quesDescItt) {
		this.quesDescItt = quesDescItt;
	}

	public String getSrNo() {
		return SrNo;
	}

	public void setSrNo(String srNo) {
		SrNo = srNo;
	}

	public String getQuesCodeItt() {
		return quesCodeItt;
	}

	public void setQuesCodeItt(String quesCodeItt) {
		this.quesCodeItt = quesCodeItt;
	}

	public ArrayList getShowPriview() {
		return showPriview;
	}

	public void setShowPriview(ArrayList showPriview) {
		this.showPriview = showPriview;
	}

	public ArrayList getShowPriviewOption() {
		return showPriviewOption;
	}

	public void setShowPriviewOption(ArrayList showPriviewOption) {
		this.showPriviewOption = showPriviewOption;
	}

	public String getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(String questionOption) {
		this.questionOption = questionOption;
	}

	public String getNextCode() {
		return nextCode;
	}

	public void setNextCode(String nextCode) {
		this.nextCode = nextCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRecTotal() {
		return recTotal;
	}

	public void setRecTotal(String recTotal) {
		this.recTotal = recTotal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getHiddenNext() {
		return hiddenNext;
	}

	public void setHiddenNext(String hiddenNext) {
		this.hiddenNext = hiddenNext;
	}

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public void setPaperTimeDurationItt(String paperTimeDurationItt) {
		this.paperTimeDurationItt = paperTimeDurationItt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getChkObject() {
		return chkObject;
	}

	public void setChkObject(String chkObject) {
		this.chkObject = chkObject;
	}

	public String getQuesCodeChk() {
		return quesCodeChk;
	}

	public void setQuesCodeChk(String quesCodeChk) {
		this.quesCodeChk = quesCodeChk;
	}

	public String getSubjectQuestCode() {
		return SubjectQuestCode;
	}

	public void setSubjectQuestCode(String subjectQuestCode) {
		SubjectQuestCode = subjectQuestCode;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getTestTimeStart() {
		return testTimeStart;
	}

	public void setTestTimeStart(String testTimeStart) {
		this.testTimeStart = testTimeStart;
	}

	public String getTestPaperCode() {
		return testPaperCode;
	}

	public void setTestPaperCode(String testPaperCode) {
		this.testPaperCode = testPaperCode;
	}

	public String getTestScoreTotal() {
		return testScoreTotal;
	}

	public void setTestScoreTotal(String testScoreTotal) {
		this.testScoreTotal = testScoreTotal;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTestTimeEnd() {
		return testTimeEnd;
	}

	public void setTestTimeEnd(String testTimeEnd) {
		this.testTimeEnd = testTimeEnd;
	}

	public String getChkNextValue() {
		return chkNextValue;
	}

	public void setChkNextValue(String chkNextValue) {
		this.chkNextValue = chkNextValue;
	}

	public String getChkNextPrevFlag() {
		return chkNextPrevFlag;
	}

	public void setChkNextPrevFlag(String chkNextPrevFlag) {
		this.chkNextPrevFlag = chkNextPrevFlag;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	public String getChkobj() {
		return chkobj;
	}

	public void setChkobj(String chkobj) {
		this.chkobj = chkobj;
	}

	public String getSubjOpt() {
		return SubjOpt;
	}

	public void setSubjOpt(String subjOpt) {
		SubjOpt = subjOpt;
	}

	


	public String getPapName() {
		return papName;
	}

	public void setPapName(String papName) {
		this.papName = papName;
	}

	public String getTQues() {
		return tQues;
	}

	public void setTQues(String ques) {
		tQues = ques;
	}

	
	
	
}
