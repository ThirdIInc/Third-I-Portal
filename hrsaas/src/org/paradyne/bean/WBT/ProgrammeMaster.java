package org.paradyne.bean.WBT;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProgrammeMaster extends BeanBase {
		
	private String moduleCode ="";
	private String moduleCodeItt ="";
	private String programeId ="";
	private String programeName ="";
	private String type="";
	private String duration="";
	private String days="";
	private String module ="";
	private String isActive = "";
	private String  sectionFlag="";
	private String  hiddenOrderCheck ="";
	private String  hiddenContentCheck ="";
	private String  hiddenQuesCheck ="";
	private String moduleOrder="";
	private String moduleListLen="";
	private String passMarksMod="";
	private String manageSectionItt="";
	
	/*For Pagination*/
	private String modeLength="";
	private String myPage="";
	private String totalRecords="";
	
	/*For Module List*/
	private String moduleItt="";
	private String moduleNameAbbrItt="";
	private String srNo ="";
	private String rowNum ="";
	private String order="";
	private String enableContent="";
	private String enableQues="";
	private String noOfAttempts="1";
	
	/*Section Page*/
	private String scetionNm="";
	private String sectionID="";
	private String sectionCodeItt="";
	private String sectionItt="";
	private String sectionAbbrItt="";
	private String content="";
	private String ques="";
	private String passMarkSecItt ="";
	private String hiddenSecContChk="";
	private String hiddenSecQuesChk="";
	private String sectionOrderItt ="";
	private String sectionModuleName ="";
	private String sectionModuleCode ="";
	private String sectionProgrameName ="";
	private String sectionProgrameCode ="";
	private String sectionOrder="";
	private String sectionListLength="";
	
	/*Question Page*/
	private String quesItt="";
	private String quesAbbrItt="";
	private String quesCodeItt="";
	private String quesTypeItt="";
	private String quesLevelItt="";
	private String quesMarkItt="";
	private String quesOrderItt="";
	private String deleteQueCode="";
	private String quesCode="";
	private String quesName="";
	private String quesType="";
	private String quesLevel="";
	private String quesMark="";
	private String SrNoQ="";
	private String randomCheck="";
	private String showNoQues="";
	private String equalEachMark="";
	private String totalMark="";
	private String passQuesMark="0";
	private String equalEachMarkChk="";
	private String equalEachMarkType="";
	private String equalNegaMarkType="";
	private String easyMarkQue="";
	private String mediumMarkQue="";
	private String hardMarkQue="";
	private String easyNegaMarkQ="";
	private String mediumNegaMarkQ="";
	private String hardNegaMarkQ="";
	private String queProgrameName ="";
	private String queProgrameCode ="";
	private String queModuleItt ="";
	private String queModuleCodeItt ="";
	private String queSectionItt ="";
	private String queSectionCodeItt ="";
	private String programQuesID ="";
	private String questionFlag="";
	private String totalQuestion="0";
	private String totalEasyQuestion="0";
	private String totalMediumQuestion="0";
	private String totalHardQuestion="0";
	
	private ArrayList<ProgrammeMaster> sectionList;	
	private ArrayList <Object> programmeList ;
	private ArrayList <Object> moduleList;
	private ArrayList<Object> questionList;	
	
		
	public String getScetionNm() {
		return scetionNm;
	}
	public void setScetionNm(String scetionNm) {
		this.scetionNm = scetionNm;
	}
	public String getSectionID() {
		return sectionID;
	}
	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}
	public String getSectionCodeItt() {
		return sectionCodeItt;
	}
	public void setSectionCodeItt(String sectionCodeItt) {
		this.sectionCodeItt = sectionCodeItt;
	}
	public String getSectionItt() {
		return sectionItt;
	}
	public void setSectionItt(String sectionItt) {
		this.sectionItt = sectionItt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}	
	public String getPassMarkSecItt() {
		return passMarkSecItt;
	}
	public void setPassMarkSecItt(String passMarkSecItt) {
		this.passMarkSecItt = passMarkSecItt;
	}
	public ArrayList<ProgrammeMaster> getSectionList() {
		return sectionList;
	}
	public void setSectionList(ArrayList<ProgrammeMaster> sectionList) {
		this.sectionList = sectionList;
	}
	public String getProgrameName() {
		return programeName;
	}
	public void setProgrameName(String programeName) {
		this.programeName = programeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getProgrameId() {
		return programeId;
	}
	public void setProgrameId(String programeId) {
		this.programeId = programeId;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public ArrayList<Object> getProgrammeList() {
		return programmeList;
	}
	public void setProgrammeList(ArrayList<Object> programmeList) {
		this.programmeList = programmeList;
	}
	public String getModuleItt() {
		return moduleItt;
	}
	public void setModuleItt(String moduleItt) {
		this.moduleItt = moduleItt;
	}
	public ArrayList<Object> getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList<Object> moduleList) {
		this.moduleList = moduleList;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getEnableContent() {
		return enableContent;
	}
	public void setEnableContent(String enableContent) {
		this.enableContent = enableContent;
	}
	public String getEnableQues() {
		return enableQues;
	}
	public void setEnableQues(String enableQues) {
		this.enableQues = enableQues;
	}
	public String getModuleCodeItt() {
		return moduleCodeItt;
	}
	public void setModuleCodeItt(String moduleCodeItt) {
		this.moduleCodeItt = moduleCodeItt;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getHiddenOrderCheck() {
		return hiddenOrderCheck;
	}
	public void setHiddenOrderCheck(String hiddenOrderCheck) {
		this.hiddenOrderCheck = hiddenOrderCheck;
	}
	public String getHiddenContentCheck() {
		return hiddenContentCheck;
	}
	public void setHiddenContentCheck(String hiddenContentCheck) {
		this.hiddenContentCheck = hiddenContentCheck;
	}
	public String getHiddenQuesCheck() {
		return hiddenQuesCheck;
	}
	public void setHiddenQuesCheck(String hiddenQuesCheck) {
		this.hiddenQuesCheck = hiddenQuesCheck;
	}
	public String getQuesItt() {
		return quesItt;
	}
	public void setQuesItt(String quesItt) {
		this.quesItt = quesItt;
	}
	public String getQuesCodeItt() {
		return quesCodeItt;
	}
	public void setQuesCodeItt(String quesCodeItt) {
		this.quesCodeItt = quesCodeItt;
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
	public String getQuesMarkItt() {
		return quesMarkItt;
	}
	public void setQuesMarkItt(String quesMarkItt) {
		this.quesMarkItt = quesMarkItt;
	}
	public ArrayList<Object> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList<Object> questionList) {
		this.questionList = questionList;
	}
	public String getQuesCode() {
		return quesCode;
	}
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}
	public String getQuesName() {
		return quesName;
	}
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public String getQuesLevel() {
		return quesLevel;
	}
	public void setQuesLevel(String quesLevel) {
		this.quesLevel = quesLevel;
	}
	public String getQuesMark() {
		return quesMark;
	}
	public void setQuesMark(String quesMark) {
		this.quesMark = quesMark;
	}
	public String getSrNoQ() {
		return SrNoQ;
	}
	public void setSrNoQ(String srNoQ) {
		SrNoQ = srNoQ;
	}
	public String getRandomCheck() {
		return randomCheck;
	}
	public void setRandomCheck(String randomCheck) {
		this.randomCheck = randomCheck;
	}
	public String getShowNoQues() {
		return showNoQues;
	}
	public void setShowNoQues(String showNoQues) {
		this.showNoQues = showNoQues;
	}
	public String getEqualEachMark() {
		return equalEachMark;
	}
	public void setEqualEachMark(String equalEachMark) {
		this.equalEachMark = equalEachMark;
	}
	public String getTotalMark() {
		return totalMark;
	}
	public void setTotalMark(String totalMark) {
		this.totalMark = totalMark;
	}
	public String getPassQuesMark() {
		return passQuesMark;
	}
	public void setPassQuesMark(String passQuesMark) {
		this.passQuesMark = passQuesMark;
	}
	public String getEqualEachMarkType() {
		return equalEachMarkType;
	}
	public void setEqualEachMarkType(String equalEachMarkType) {
		this.equalEachMarkType = equalEachMarkType;
	}
	public String getEqualNegaMarkType() {
		return equalNegaMarkType;
	}
	public void setEqualNegaMarkType(String equalNegaMarkType) {
		this.equalNegaMarkType = equalNegaMarkType;
	}
	public String getEasyMarkQue() {
		return easyMarkQue;
	}
	public void setEasyMarkQue(String easyMarkQue) {
		this.easyMarkQue = easyMarkQue;
	}
	public String getMediumMarkQue() {
		return mediumMarkQue;
	}
	public void setMediumMarkQue(String mediumMarkQue) {
		this.mediumMarkQue = mediumMarkQue;
	}
	public String getHardMarkQue() {
		return hardMarkQue;
	}
	public void setHardMarkQue(String hardMarkQue) {
		this.hardMarkQue = hardMarkQue;
	}
	public String getEasyNegaMarkQ() {
		return easyNegaMarkQ;
	}
	public void setEasyNegaMarkQ(String easyNegaMarkQ) {
		this.easyNegaMarkQ = easyNegaMarkQ;
	}
	public String getMediumNegaMarkQ() {
		return mediumNegaMarkQ;
	}
	public void setMediumNegaMarkQ(String mediumNegaMarkQ) {
		this.mediumNegaMarkQ = mediumNegaMarkQ;
	}
	public String getHardNegaMarkQ() {
		return hardNegaMarkQ;
	}
	public void setHardNegaMarkQ(String hardNegaMarkQ) {
		this.hardNegaMarkQ = hardNegaMarkQ;
	}
	public String getHiddenSecContChk() {
		return hiddenSecContChk;
	}
	public void setHiddenSecContChk(String hiddenSecContChk) {
		this.hiddenSecContChk = hiddenSecContChk;
	}
	public String getHiddenSecQuesChk() {
		return hiddenSecQuesChk;
	}
	public void setHiddenSecQuesChk(String hiddenSecQuesChk) {
		this.hiddenSecQuesChk = hiddenSecQuesChk;
	}
	public String getSectionFlag() {
		return sectionFlag;
	}
	public void setSectionFlag(String sectionFlag) {
		this.sectionFlag = sectionFlag;
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
	public String getSectionProgrameName() {
		return sectionProgrameName;
	}
	public void setSectionProgrameName(String sectionProgrameName) {
		this.sectionProgrameName = sectionProgrameName;
	}
	public String getSectionProgrameCode() {
		return sectionProgrameCode;
	}
	public void setSectionProgrameCode(String sectionProgrameCode) {
		this.sectionProgrameCode = sectionProgrameCode;
	}
	public String getSectionOrderItt() {
		return sectionOrderItt;
	}
	public void setSectionOrderItt(String sectionOrderItt) {
		this.sectionOrderItt = sectionOrderItt;
	}
	public String getQueProgrameName() {
		return queProgrameName;
	}
	public void setQueProgrameName(String queProgrameName) {
		this.queProgrameName = queProgrameName;
	}
	public String getQueProgrameCode() {
		return queProgrameCode;
	}
	public void setQueProgrameCode(String queProgrameCode) {
		this.queProgrameCode = queProgrameCode;
	}
	public String getQueModuleItt() {
		return queModuleItt;
	}
	public void setQueModuleItt(String queModuleItt) {
		this.queModuleItt = queModuleItt;
	}
	public String getQueModuleCodeItt() {
		return queModuleCodeItt;
	}
	public void setQueModuleCodeItt(String queModuleCodeItt) {
		this.queModuleCodeItt = queModuleCodeItt;
	}	
	public String getModuleOrder() {
		return moduleOrder;
	}
	public void setModuleOrder(String moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	public String getModuleListLen() {
		return moduleListLen;
	}
	public void setModuleListLen(String moduleListLen) {
		this.moduleListLen = moduleListLen;
	}
	public String getSectionOrder() {
		return sectionOrder;
	}
	public void setSectionOrder(String sectionOrder) {
		this.sectionOrder = sectionOrder;
	}
	public String getSectionListLength() {
		return sectionListLength;
	}
	public void setSectionListLength(String sectionListLength) {
		this.sectionListLength = sectionListLength;
	}
	/**
	 * @return the queSectionCodeItt
	 */
	public String getQueSectionCodeItt() {
		return queSectionCodeItt;
	}
	/**
	 * @param queSectionCodeItt the queSectionCodeItt to set
	 */
	public void setQueSectionCodeItt(String queSectionCodeItt) {
		this.queSectionCodeItt = queSectionCodeItt;
	}
	/**
	 * @return the programQuesID
	 */
	public String getProgramQuesID() {
		return programQuesID;
	}
	/**
	 * @param programQuesID the programQuesID to set
	 */
	public void setProgramQuesID(String programQuesID) {
		this.programQuesID = programQuesID;
	}
	/**
	 * @return the quesOrderItt
	 */
	public String getQuesOrderItt() {
		return quesOrderItt;
	}
	/**
	 * @param quesOrderItt the quesOrderItt to set
	 */
	public void setQuesOrderItt(String quesOrderItt) {
		this.quesOrderItt = quesOrderItt;
	}
	/**
	 * @return the deleteQueCode
	 */
	public String getDeleteQueCode() {
		return deleteQueCode;
	}
	/**
	 * @param deleteQueCode the deleteQueCode to set
	 */
	public void setDeleteQueCode(String deleteQueCode) {
		this.deleteQueCode = deleteQueCode;
	}
	/**
	 * @return the equalEachMarkChk
	 */
	public String getEqualEachMarkChk() {
		return equalEachMarkChk;
	}
	/**
	 * @param equalEachMarkChk the equalEachMarkChk to set
	 */
	public void setEqualEachMarkChk(String equalEachMarkChk) {
		this.equalEachMarkChk = equalEachMarkChk;
	}
	/**
	 * @return the questionFlag
	 */
	public String getQuestionFlag() {
		return questionFlag;
	}
	/**
	 * @param questionFlag the questionFlag to set
	 */
	public void setQuestionFlag(String questionFlag) {
		this.questionFlag = questionFlag;
	}
	
	/**
	 * @return the queSectionItt
	 */
	public String getQueSectionItt() {
		return queSectionItt;
	}
	/**
	 * @param queSectionItt the queSectionItt to set
	 */
	public void setQueSectionItt(String queSectionItt) {
		this.queSectionItt = queSectionItt;
	}
	public String getPassMarksMod() {
		return passMarksMod;
	}
	public void setPassMarksMod(String passMarksMod) {
		this.passMarksMod = passMarksMod;
	}
	/**
	 * @return the moduleNameAbbrItt
	 */
	public String getModuleNameAbbrItt() {
		return moduleNameAbbrItt;
	}
	/**
	 * @param moduleNameAbbrItt the moduleNameAbbrItt to set
	 */
	public void setModuleNameAbbrItt(String moduleNameAbbrItt) {
		this.moduleNameAbbrItt = moduleNameAbbrItt;
	}
	/**
	 * @return the sectionAbbrItt
	 */
	public String getSectionAbbrItt() {
		return sectionAbbrItt;
	}
	/**
	 * @param sectionAbbrItt the sectionAbbrItt to set
	 */
	public void setSectionAbbrItt(String sectionAbbrItt) {
		this.sectionAbbrItt = sectionAbbrItt;
	}
	/**
	 * @return the quesAbbrItt
	 */
	public String getQuesAbbrItt() {
		return quesAbbrItt;
	}
	/**
	 * @param quesAbbrItt the quesAbbrItt to set
	 */
	public void setQuesAbbrItt(String quesAbbrItt) {
		this.quesAbbrItt = quesAbbrItt;
	}
	/**
	 * @return the totalQuestion
	 */
	public String getTotalQuestion() {
		return totalQuestion;
	}
	/**
	 * @param totalQuestion the totalQuestion to set
	 */
	public void setTotalQuestion(String totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	/**
	 * @return the totalEasyQuestion
	 */
	public String getTotalEasyQuestion() {
		return totalEasyQuestion;
	}
	/**
	 * @param totalEasyQuestion the totalEasyQuestion to set
	 */
	public void setTotalEasyQuestion(String totalEasyQuestion) {
		this.totalEasyQuestion = totalEasyQuestion;
	}
	/**
	 * @return the totalMediumQuestion
	 */
	public String getTotalMediumQuestion() {
		return totalMediumQuestion;
	}
	/**
	 * @param totalMediumQuestion the totalMediumQuestion to set
	 */
	public void setTotalMediumQuestion(String totalMediumQuestion) {
		this.totalMediumQuestion = totalMediumQuestion;
	}
	/**
	 * @return the totalHardQuestion
	 */
	public String getTotalHardQuestion() {
		return totalHardQuestion;
	}
	/**
	 * @param totalHardQuestion the totalHardQuestion to set
	 */
	public void setTotalHardQuestion(String totalHardQuestion) {
		this.totalHardQuestion = totalHardQuestion;
	}
	/**
	 * @return the manageSectionItt
	 */
	public String getManageSectionItt() {
		return manageSectionItt;
	}
	/**
	 * @param manageSectionItt the manageSectionItt to set
	 */
	public void setManageSectionItt(String manageSectionItt) {
		this.manageSectionItt = manageSectionItt;
	}
	/**
	 * @return the noOfAttempts
	 */
	public String getNoOfAttempts() {
		return noOfAttempts;
	}
	/**
	 * @param noOfAttempts the noOfAttempts to set
	 */
	public void setNoOfAttempts(String noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	
}
