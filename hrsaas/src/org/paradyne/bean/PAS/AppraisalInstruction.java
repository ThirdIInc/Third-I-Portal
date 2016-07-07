package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AppraisalInstruction extends BeanBase {

	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	private String saveFlag;
	
	
	private String templateCode;
	private String templateName;
	
	private String ratingValue;
	private String ratingDesc;
	private ArrayList ratingList;
	
	private String instrApplicable;
	private String instruction;
	private String ratingType="";
	
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getInstrApplicable() {
		return instrApplicable;
	}
	public void setInstrApplicable(String instrApplicable) {
		this.instrApplicable = instrApplicable;
	}
	public String getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(String ratingValue) {
		this.ratingValue = ratingValue;
	}
	public String getRatingDesc() {
		return ratingDesc;
	}
	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
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
	public ArrayList getRatingList() {
		return ratingList;
	}
	public void setRatingList(ArrayList ratingList) {
		this.ratingList = ratingList;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
}
