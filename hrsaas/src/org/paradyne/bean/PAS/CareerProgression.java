package org.paradyne.bean.PAS;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CareerProgression extends BeanBase{

	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	private String templateCode;
	private String templateName;
	private String phaseId;
	private String phase;
	
	private String rownum;
	private String questionId;
	private String question;
	
	private String hSectionId;
	private String hQuestionId;
	private String hQuestion;
	private String chkCareerAppl;
	private String mode;
	private String removeQuestions;
	
	private String removeFlag="true";
	
	private ArrayList trngList;
	private ArrayList questionList;
	
	private String genComFlg="N";	//Allow General Comments Flag
	private String noOfCols="0";	//No. of columns	
	
	public String getGenComFlg() {
		return genComFlg;
	}
	public void setGenComFlg(String genComFlg) {
		this.genComFlg = genComFlg;
	}
	public String getChkCareerAppl() {
		return chkCareerAppl;
	}
	public void setChkCareerAppl(String chkCareerAppl) {
		this.chkCareerAppl = chkCareerAppl;
	}
	public String getRemoveFlag() {
		return removeFlag;
	}
	public void setRemoveFlag(String removeFlag) {
		this.removeFlag = removeFlag;
	}
	public ArrayList getTrngList() {
		return trngList;
	}
	public void setTrngList(ArrayList trngList) {
		this.trngList = trngList;
	}
	public String getHSectionId() {
		return hSectionId;
	}
	public void setHSectionId(String sectionId) {
		hSectionId = sectionId;
	}
	public String getRemoveQuestions() {
		return removeQuestions;
	}
	public void setRemoveQuestions(String removeQuestions) {
		this.removeQuestions = removeQuestions;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getHQuestionId() {
		return hQuestionId;
	}
	public void setHQuestionId(String questionId) {
		hQuestionId = questionId;
	}
	public String getHQuestion() {
		return hQuestion;
	}
	public void setHQuestion(String question) {
		hQuestion = question;
	}
	public ArrayList getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList questionList) {
		this.questionList = questionList;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getNoOfCols() {
		return noOfCols;
	}
	public void setNoOfCols(String noOfCols) {
		this.noOfCols = noOfCols;
	}

	
	
	
}
