/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 */
public class SubjectiveMarks extends BeanBase {
	
	private String candidateName="";
	private String candidateCode="";
	private String testCode="";
	private String paperCode="";
	private String paperName="";
	private String fromDate="";
	private String toDate="";
	private String subCode="";
	
	private String quesName="";
	private String maxMarks="";
	private String obtMarks="";
	private String answer="";
	private String	marks="";
	
	private String quesCode="";
	private String subjectQuestCode="";
	private String quesDescItt="";
	 private ArrayList  showListOption;
	 private ArrayList  showList; 

	private String SubjOpt="";
	private String 	text="";
	
	/**
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}
	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	/**
	 * @return the candidateCode
	 */
	public String getCandidateCode() {
		return candidateCode;
	}
	/**
	 * @param candidateCode the candidateCode to set
	 */
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	/**
	 * @return the testCode
	 */
	public String getTestCode() {
		return testCode;
	}
	/**
	 * @param testCode the testCode to set
	 */
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	/**
	 * @return the paperName
	 */
	public String getPaperName() {
		return paperName;
	}
	/**
	 * @param paperName the paperName to set
	 */
	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
	 * @return the subCode
	 */
	public String getSubCode() {
		return subCode;
	}
	/**
	 * @param subCode the subCode to set
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
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
	 * @return the maxMarks
	 */
	public String getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}
	/**
	 * @return the obtMarks
	 */
	public String getObtMarks() {
		return obtMarks;
	}
	/**
	 * @param obtMarks the obtMarks to set
	 */
	public void setObtMarks(String obtMarks) {
		this.obtMarks = obtMarks;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the paperCode
	 */
	public String getPaperCode() {
		return paperCode;
	}
	/**
	 * @param paperCode the paperCode to set
	 */
	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getQuesDescItt() {
		return quesDescItt;
	}
	public void setQuesDescItt(String quesDescItt) {
		this.quesDescItt = quesDescItt;
	}


	public String getSubjectQuestCode() {
		return subjectQuestCode;
	}
	public void setSubjectQuestCode(String subjectQuestCode) {
		this.subjectQuestCode = subjectQuestCode;
	}


	public String getSubjOpt() {
		return SubjOpt;
	}
	public void setSubjOpt(String subjOpt) {
		SubjOpt = subjOpt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList getShowListOption() {
		return showListOption;
	}

	public ArrayList getShowList() {
		return showList;
	}
	public void setShowList(ArrayList showList) {
		this.showList = showList;
	}
	public void setShowListOption(ArrayList showListOption) {
		this.showListOption = showListOption;
	}
	
	

}
