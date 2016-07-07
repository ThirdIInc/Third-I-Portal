package org.paradyne.bean.PAS;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class FormFinalization extends BeanBase{
	
	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	private String templateCode;
	private String templateName;
	private String phaseId;
	private String phase;
	private String weightage;
	
	private String step;
	private String applicability;
	private String status;
	
	private ArrayList stepList;

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

	public String getWeightage() {
		return weightage;
	}

	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList getStepList() {
		return stepList;
	}

	public void setStepList(ArrayList stepList) {
		this.stepList = stepList;
	}
	
	
	
	
	
}
