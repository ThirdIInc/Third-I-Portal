package org.paradyne.bean.employeeSurvey;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 * Aug 21, 2010
 */

public class EmployeeSurvey extends BeanBase {
	private int surveyCode;
	private int srNo;
	private String surveyName;
	private String surveyFromDate;
	private String surveyToDate;
	private List<EmployeeSurvey> surveyList = new ArrayList<EmployeeSurvey>();
	private boolean dataExists = false;
	private String myPage;
	private String totalRecords;
	private String surveyListLength;
	
	private String hiddencode = "";
	private boolean questionExists = false;
	private String questionCode="";
	private String surveyQuestion="";
	private String questionType="";
	private String sectionName="";
	private String sectionCode="";
	private String qnSrNo;
	private String myQnPage;
	private List<EmployeeSurvey> questionList = new ArrayList<EmployeeSurvey>();
	private String typeFlag = "";
	private String optionCode="";
	private String optionName = "";
	private List<EmployeeSurvey> optionList = new ArrayList<EmployeeSurvey>();
	private boolean finalize = false;
	
	private String comment = "";
	private String optionValues = "";
	
	private String qnPageNoField = "";
	private String empSurveyCode = "";
	
	private boolean sectionHide = false;
	private String showSection = "";
	private String finalizedStat = "";
	private boolean finalizedStatFlag = false;
	private boolean finalizedStatItrFlag = false;
	private boolean finalizedOptionItrFlag = false;
	
	private String optionType = "";
	private int optionLength; 
	

	public int getOptionLength() {
		return optionLength;
	}

	public void setOptionLength(int optionLength) {
		this.optionLength = optionLength;
	}

	public String getFinalizedStat() {
		return finalizedStat;
	}

	public void setFinalizedStat(String finalizedStat) {
		this.finalizedStat = finalizedStat;
	}

	/**
	 * @return the empSurveyCode
	 */
	public String getEmpSurveyCode() {
		return empSurveyCode;
	}

	/**
	 * @param empSurveyCode the empSurveyCode to set
	 */
	public void setEmpSurveyCode(String empSurveyCode) {
		this.empSurveyCode = empSurveyCode;
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
	 * @return the questionExists
	 */
	public boolean isQuestionExists() {
		return questionExists;
	}

	/**
	 * @param questionExists the questionExists to set
	 */
	public void setQuestionExists(boolean questionExists) {
		this.questionExists = questionExists;
	}

	/**
	 * @return the questionCode
	 */
	public String getQuestionCode() {
		return questionCode;
	}

	/**
	 * @param questionCode the questionCode to set
	 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	/**
	 * @return the surveyQuestion
	 */
	public String getSurveyQuestion() {
		return surveyQuestion;
	}

	/**
	 * @param surveyQuestion the surveyQuestion to set
	 */
	public void setSurveyQuestion(String surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	/**
	 * @return the questionType
	 */
	public String getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

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
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @return the srNo
	 */
	public int getSrNo() {
		return srNo;
	}
	
	/**
	 * @return the surveyCode
	 */
	public int getSurveyCode() {
		return surveyCode;
	}

	/**
	 * @return the surveyFromDate
	 */
	public String getSurveyFromDate() {
		return surveyFromDate;
	}

	/**
	 * @return the surveyList
	 */
	public List<EmployeeSurvey> getSurveyList() {
		return surveyList;
	}
	
	/**
	 * @return the surveyListLength
	 */
	public String getSurveyListLength() {
		return surveyListLength;
	}
	
	/**
	 * @return the surveyName
	 */
	public String getSurveyName() {
		return surveyName;
	}
	
	/**
	 * @return the surveyToDate
	 */
	public String getSurveyToDate() {
		return surveyToDate;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	
	/**
	 * @return the dataExists
	 */
	public boolean isDataExists() {
		return dataExists;
	}
	
	/**
	 * @param dataExists the dataExists to set
	 */
	public void setDataExists(boolean dataExists) {
		this.dataExists = dataExists;
	}
	
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	
	/**
	 * @param surveyCode the surveyCode to set
	 */
	public void setSurveyCode(int surveyCode) {
		this.surveyCode = surveyCode;
	}



	
	/**
	 * @param surveyFromDate the surveyFromDate to set
	 */
	public void setSurveyFromDate(String surveyFromDate) {
		this.surveyFromDate = surveyFromDate;
	}



	
	/**
	 * @param surveyList the surveyList to set
	 */
	public void setSurveyList(List<EmployeeSurvey> surveyList) {
		this.surveyList = surveyList;
	}



	
	/**
	 * @param surveyListLength the surveyListLength to set
	 */
	public void setSurveyListLength(String surveyListLength) {
		this.surveyListLength = surveyListLength;
	}



	
	/**
	 * @param surveyName the surveyName to set
	 */
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}



	
	/**
	 * @param surveyToDate the surveyToDate to set
	 */
	public void setSurveyToDate(String surveyToDate) {
		this.surveyToDate = surveyToDate;
	}



	
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the myQnPage
	 */
	public String getMyQnPage() {
		return myQnPage;
	}

	/**
	 * @param myQnPage the myQnPage to set
	 */
	public void setMyQnPage(String myQnPage) {
		this.myQnPage = myQnPage;
	}

	/**
	 * @return the qnSrNo
	 */
	public String getQnSrNo() {
		return qnSrNo;
	}

	/**
	 * @param qnSrNo the qnSrNo to set
	 */
	public void setQnSrNo(String qnSrNo) {
		this.qnSrNo = qnSrNo;
	}

	/**
	 * @return the questionList
	 */
	public List<EmployeeSurvey> getQuestionList() {
		return questionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List<EmployeeSurvey> questionList) {
		this.questionList = questionList;
	}

	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the typeFlag
	 */
	public String getTypeFlag() {
		return typeFlag;
	}

	/**
	 * @param typeFlag the typeFlag to set
	 */
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	/**
	 * @return the optionList
	 */
	public List<EmployeeSurvey> getOptionList() {
		return optionList;
	}

	/**
	 * @param optionList the optionList to set
	 */
	public void setOptionList(List<EmployeeSurvey> optionList) {
		this.optionList = optionList;
	}

	/**
	 * @return the finalize
	 */
	public boolean isFinalize() {
		return finalize;
	}

	/**
	 * @param finalize the finalize to set
	 */
	public void setFinalize(boolean finalize) {
		this.finalize = finalize;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the optionValues
	 */
	public String getOptionValues() {
		return optionValues;
	}

	/**
	 * @param optionValues the optionValues to set
	 */
	public void setOptionValues(String optionValues) {
		this.optionValues = optionValues;
	}

	/**
	 * @return the qnPageNoField
	 */
	public String getQnPageNoField() {
		return qnPageNoField;
	}

	/**
	 * @param qnPageNoField the qnPageNoField to set
	 */
	public void setQnPageNoField(String qnPageNoField) {
		this.qnPageNoField = qnPageNoField;
	}

	public boolean isSectionHide() {
		return sectionHide;
	}

	public void setSectionHide(boolean sectionHide) {
		this.sectionHide = sectionHide;
	}

	public String getShowSection() {
		return showSection;
	}

	public void setShowSection(String showSection) {
		this.showSection = showSection;
	}

	public boolean isFinalizedStatFlag() {
		return finalizedStatFlag;
	}

	public void setFinalizedStatFlag(boolean finalizedStatFlag) {
		this.finalizedStatFlag = finalizedStatFlag;
	}

	public boolean isFinalizedStatItrFlag() {
		return finalizedStatItrFlag;
	}

	public void setFinalizedStatItrFlag(boolean finalizedStatItrFlag) {
		this.finalizedStatItrFlag = finalizedStatItrFlag;
	}

	public boolean isFinalizedOptionItrFlag() {
		return finalizedOptionItrFlag;
	}

	public void setFinalizedOptionItrFlag(boolean finalizedOptionItrFlag) {
		this.finalizedOptionItrFlag = finalizedOptionItrFlag;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

}