package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DefineSection extends BeanBase {

	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	
	private String templateCode;
	private String templateName;
	
	private String sectionName;
	private String secCode;
	private String secName;
	private ArrayList sectionList;
	private String srNo;
	private String priority;
	
	private String phaseCode;
	private String phaseName;
	private ArrayList phaseList;
	private String addFLag="true";
	private String resetFLag="true";
	private String lockAppraisal="false";
	
		
	
	public String getLockAppraisal() {
		return lockAppraisal;
	}
	public void setLockAppraisal(String lockAppraisal) {
		this.lockAppraisal = lockAppraisal;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public ArrayList getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public ArrayList getSectionList() {
		return sectionList;
	}
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public String getAddFLag() {
		return addFLag;
	}
	public void setAddFLag(String addFLag) {
		this.addFLag = addFLag;
	}
	public String getResetFLag() {
		return resetFLag;
	}
	public void setResetFLag(String resetFLag) {
		this.resetFLag = resetFLag;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
