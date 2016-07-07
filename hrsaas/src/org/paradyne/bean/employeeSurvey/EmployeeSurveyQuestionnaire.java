/**
 * 
 */
package org.paradyne.bean.employeeSurvey;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 */
public class EmployeeSurveyQuestionnaire extends BeanBase {
	private String option="";
	private String optionName="";
	private String optionCode="";
	private ArrayList optionList;
	private String quesCode="";
	private String srNo="";
	private String optionFlag="false";
	private String optionId;
	private String paraId;
	private String optionTextarea="";
	private String surveyCode="";
	private String surveyName="";
	private String question="";
	private String ansOptions;
	
	private String quesCodeItt="";
	private String myPage;
	private String noData="false";
	private String totRec="";
	private ArrayList questionList;
	
	private String sectionName="";
	private String sectionCode="";
	private String multipleSelect="";
	
	/**
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}
	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/**
	 * @return the sectionCode
	 */
	public String getSectionCode() {
		return sectionCode;
	}
	/**
	 * @param sectionCode the sectionCode to set
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	/**
	 * @return the ansOptions
	 */
	public String getAnsOptions() {
		return ansOptions;
	}
	/**
	 * @param ansOptions the ansOptions to set
	 */
	public void setAnsOptions(String ansOptions) {
		this.ansOptions = ansOptions;
	}
	/**
	 * @return the surveyCode
	 */
	public String getSurveyCode() {
		return surveyCode;
	}
	/**
	 * @param surveyCode the surveyCode to set
	 */
	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}
	/**
	 * @return the surveyName
	 */
	public String getSurveyName() {
		return surveyName;
	}
	/**
	 * @param surveyName the surveyName to set
	 */
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the optionTextarea
	 */
	public String getOptionTextarea() {
		return optionTextarea;
	}
	/**
	 * @param optionTextarea the optionTextarea to set
	 */
	public void setOptionTextarea(String optionTextarea) {
		this.optionTextarea = optionTextarea;
	}
	/**
	 * @return the optionFlag
	 */
	public String getOptionFlag() {
		return optionFlag;
	}
	/**
	 * @param optionFlag the optionFlag to set
	 */
	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}
	/**
	 * @return the optionId
	 */
	public String getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}
	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}
	/**
	 * @return the optionName
	 */
	public String getOptionName() {
		return optionName;
	}
	/**
	 * @param optionName the optionName to set
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	 * @return the optionList
	 */
	public ArrayList getOptionList() {
		return optionList;
	}
	/**
	 * @param optionList the optionList to set
	 */
	public void setOptionList(ArrayList optionList) {
		this.optionList = optionList;
	}
	/**
	 * @return the quesCode
	 */
	public String getQuesCode() {
		return quesCode;
	}
	/**
	 * @param quesCode the quesCode to set
	 */
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the optionCode
	 */
	public String getOptionCode() {
		return optionCode;
	}
	/**
	 * @param optionCode the optionCode to set
	 */
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	/**
	 * @return the quesCodeItt
	 */
	public String getQuesCodeItt() {
		return quesCodeItt;
	}
	/**
	 * @param quesCodeItt the quesCodeItt to set
	 */
	public void setQuesCodeItt(String quesCodeItt) {
		this.quesCodeItt = quesCodeItt;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
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
	 * @return the totRec
	 */
	public String getTotRec() {
		return totRec;
	}
	/**
	 * @param totRec the totRec to set
	 */
	public void setTotRec(String totRec) {
		this.totRec = totRec;
	}
	/**
	 * @return the questionList
	 */
	public ArrayList getQuestionList() {
		return questionList;
	}
	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(ArrayList questionList) {
		this.questionList = questionList;
	}
	public String getMultipleSelect() {
		return multipleSelect;
	}
	public void setMultipleSelect(String multipleSelect) {
		this.multipleSelect = multipleSelect;
	}
}
