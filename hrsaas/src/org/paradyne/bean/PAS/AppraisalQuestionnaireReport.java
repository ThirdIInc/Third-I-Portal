package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class AppraisalQuestionnaireReport extends BeanBase 
{
	private String sQuestionCategoryCode = null;
	private String sQuestionType = null;
	private String sQuestionStatus = null;
	private String sQuestionMandatory = null;
	private String report = "";
	private String QuestionCategory = "";
	private HashMap lstQuestionCategory = null; 
	private ArrayList lstQuestion = null;
	
	public String getSQuestionCategoryCode() {
		return sQuestionCategoryCode;
	}
	public void setSQuestionCategoryCode(String questionCategoryCode) {
		sQuestionCategoryCode = questionCategoryCode;
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
	public HashMap getLstQuestionCategory() {
		return lstQuestionCategory;
	}
	public void setLstQuestionCategory(HashMap lstQuestionCategory) {
		this.lstQuestionCategory = lstQuestionCategory;
	}
	public ArrayList getLstQuestion() {
		return lstQuestion;
	}
	public void setLstQuestion(ArrayList lstQuestion) {
		this.lstQuestion = lstQuestion;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getQuestionCategory() {
		return QuestionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		QuestionCategory = questionCategory;
	}
	
	
}
