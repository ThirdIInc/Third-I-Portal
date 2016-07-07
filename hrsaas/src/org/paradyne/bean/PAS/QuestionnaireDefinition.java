package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class QuestionnaireDefinition extends BeanBase {
	
	/* Main Parameter */
	private String sQuestionCode = null;
	private String sQuestion = null;
	private String sQuestionType = null;
	private String sQuestionStatus = null;
	private String sAnwserLimit = null;
	private String sQuestionMandatory = null;
	private String sQuestionCategoryCode = null;
	private String sQuestionCategoryName = null;
	
	private ArrayList lstQuestion = null;
	private HashMap lstQuestionCategory = null; 
	
	private Boolean flgStatus = false;
	private Boolean flgQuestionType = false;
	
	
	
	/* Navigation and Mode Parameter */
	private String sMode = null;
	private String myPage;
	private String hiddencode;
	private String readFlag="true";
	
	
	public String getSQuestionCode() {
		return sQuestionCode;
	}
	public void setSQuestionCode(String questionCode) {
		sQuestionCode = questionCode;
	}
	public String getSQuestion() {
		return sQuestion;
	}
	public void setSQuestion(String question) {
		sQuestion = question;
	}
	public String getSQuestionType() {
		return sQuestionType;
	}
	public void setSQuestionType(String questionType) {
		sQuestionType = questionType;
	}
	public String getSQuestionStatus() {
		return sQuestionStatus;
	}
	public void setSQuestionStatus(String questionStatus) {
		sQuestionStatus = questionStatus;
	}
	public String getSQuestionMandatory() {
		return sQuestionMandatory;
	}
	public void setSQuestionMandatory(String questionMandatory) {
		sQuestionMandatory = questionMandatory;
	}
	public String getSQuestionCategoryCode() {
		return sQuestionCategoryCode;
	}
	public void setSQuestionCategoryCode(String questionCategoryCode) {
		sQuestionCategoryCode = questionCategoryCode;
	}
	public ArrayList getLstQuestion() {
		return lstQuestion;
	}
	public void setLstQuestion(ArrayList lstQuestion) {
		this.lstQuestion = lstQuestion;
	}
	public String getSMode() {
		return sMode;
	}
	public void setSMode(String mode) {
		sMode = mode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	public HashMap getLstQuestionCategory() {
		return lstQuestionCategory;
	}
	public void setLstQuestionCategory(HashMap lstQuestionCategory) {
		this.lstQuestionCategory = lstQuestionCategory;
	}
	public String getSAnwserLimit() {
		return sAnwserLimit;
	}
	public void setSAnwserLimit(String anwserLimit) {
		sAnwserLimit = anwserLimit;
	}
	public String getSQuestionCategoryName() {
		return sQuestionCategoryName;
	}
	public void setSQuestionCategoryName(String questionCategoryName) {
		sQuestionCategoryName = questionCategoryName;
	}
	public Boolean getFlgStatus() {
		return flgStatus;
	}
	public void setFlgStatus(Boolean flgStatus) {
		this.flgStatus = flgStatus;
	}
	public Boolean getFlgQuestionType() {
		return flgQuestionType;
	}
	public void setFlgQuestionType(Boolean flgQuestionType) {
		this.flgQuestionType = flgQuestionType;
	}
}
