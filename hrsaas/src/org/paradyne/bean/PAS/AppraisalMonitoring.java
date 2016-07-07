package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class AppraisalMonitoring extends BeanBase {

	private String apprCode;
	private String frmDate;
	private String toDate;
	private String apprId;
	private String lockFlag;
	private String closureDate;
	private String empToken;
	private String empName;
	private String empCode;
	
	private String appraiseeGroup;
	private String phaseCode;
	private String phaseName;
	private String appraiserName;
	private String appraiserLevel;
	private String phaseCompleted;
	
	private ArrayList phaseList;
	
	private String noData="false";
	private String displayFlag ="false";
	
	private String sbPhaseName;
	private String sbAppraiserName;
	private String sbAppraiserLevel;
	private String sbPhaseCode;
	private String sbAppraiserCode;
	private String sbPhaseOrder;
	
	private String groupName;
	private String groupId;
	
	private String addEmpToken;
	private String addEmpName;
	private String addEmpId;
	private String addAppraiserGroup;
	private String addAppraiserGroupCode;
	private String templateName;
	private String quesGroupName;
	private String quesGroupCode;
	
	private String removeEmpToken;
	private String removeEmpName;
	private String removeEmpId;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSbPhaseOrder() {
		return sbPhaseOrder;
	}
	public void setSbPhaseOrder(String sbPhaseOrder) {
		this.sbPhaseOrder = sbPhaseOrder;
	}
	public String getSbPhaseName() {
		return sbPhaseName;
	}
	public void setSbPhaseName(String sbPhaseName) {
		this.sbPhaseName = sbPhaseName;
	}
	public String getSbPhaseCode() {
		return sbPhaseCode;
	}
	public void setSbPhaseCode(String sbPhaseCode) {
		this.sbPhaseCode = sbPhaseCode;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getAppraiserName() {
		return appraiserName;
	}
	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}
	public String getAppraiserLevel() {
		return appraiserLevel;
	}
	public void setAppraiserLevel(String appraiserLevel) {
		this.appraiserLevel = appraiserLevel;
	}
	public String getPhaseCompleted() {
		return phaseCompleted;
	}
	public void setPhaseCompleted(String phaseCompleted) {
		this.phaseCompleted = phaseCompleted;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getSbAppraiserLevel() {
		return sbAppraiserLevel;
	}
	public void setSbAppraiserLevel(String sbAppraiserLevel) {
		this.sbAppraiserLevel = sbAppraiserLevel;
	}
	public String getSbAppraiserCode() {
		return sbAppraiserCode;
	}
	public void setSbAppraiserCode(String sbAppraiserCode) {
		this.sbAppraiserCode = sbAppraiserCode;
	}
	public String getAppraiseeGroup() {
		return appraiseeGroup;
	}
	public void setAppraiseeGroup(String appraiseeGroup) {
		this.appraiseeGroup = appraiseeGroup;
	}
	public String getSbAppraiserName() {
		return sbAppraiserName;
	}
	public void setSbAppraiserName(String sbAppraiserName) {
		this.sbAppraiserName = sbAppraiserName;
	}
	public String getAddEmpToken() {
		return addEmpToken;
	}
	public void setAddEmpToken(String addEmpToken) {
		this.addEmpToken = addEmpToken;
	}
	public String getAddEmpName() {
		return addEmpName;
	}
	public void setAddEmpName(String addEmpName) {
		this.addEmpName = addEmpName;
	}
	public String getAddEmpId() {
		return addEmpId;
	}
	public void setAddEmpId(String addEmpId) {
		this.addEmpId = addEmpId;
	}
	public String getRemoveEmpToken() {
		return removeEmpToken;
	}
	public void setRemoveEmpToken(String removeEmpToken) {
		this.removeEmpToken = removeEmpToken;
	}
	public String getRemoveEmpName() {
		return removeEmpName;
	}
	public void setRemoveEmpName(String removeEmpName) {
		this.removeEmpName = removeEmpName;
	}
	public String getRemoveEmpId() {
		return removeEmpId;
	}
	public void setRemoveEmpId(String removeEmpId) {
		this.removeEmpId = removeEmpId;
	}
	public String getAddAppraiserGroup() {
		return addAppraiserGroup;
	}
	public void setAddAppraiserGroup(String addAppraiserGroup) {
		this.addAppraiserGroup = addAppraiserGroup;
	}
	public String getAddAppraiserGroupCode() {
		return addAppraiserGroupCode;
	}
	public void setAddAppraiserGroupCode(String addAppraiserGroupCode) {
		this.addAppraiserGroupCode = addAppraiserGroupCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getQuesGroupName() {
		return quesGroupName;
	}
	public void setQuesGroupName(String quesGroupName) {
		this.quesGroupName = quesGroupName;
	}
	public String getQuesGroupCode() {
		return quesGroupCode;
	}
	public void setQuesGroupCode(String quesGroupCode) {
		this.quesGroupCode = quesGroupCode;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getClosureDate() {
		return closureDate;
	}
	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}
}
