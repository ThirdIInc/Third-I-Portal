package org.paradyne.bean.PAS;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class WeightageDistribution extends BeanBase{

	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	private String templateCode;
	private String templateName;
	private String phaseId;
	private String phase;
	private String weightage;
	

	
	private ArrayList phaseList;

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

	public ArrayList getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	
	
	
	
	
	
}
