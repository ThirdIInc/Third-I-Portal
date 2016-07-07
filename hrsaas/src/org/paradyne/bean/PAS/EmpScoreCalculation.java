/*
 * Author Anantha lakshmi
 */
package org.paradyne.bean.PAS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;
public class EmpScoreCalculation extends BeanBase {
	
	
	private String publishFlag="false";
	private String listType = "";
	private String myPage = "";
	private String pendingLength="false";
	private String appraisalId = "";
	private String appraisalCode = "";
	private String appraisalName = "";
	private String departmentId = "";
	private String departmentName = "";
	private String branchId = "";
	private String branchName = "";
	private String groupHeadId = "";
	private String groupHeadToken = "";
	private String groupHeadName = "";
	private String managerId = "";
	private String managerToken = "";
	private String managerName = "";
	private String empId = "";
	private String empName = "";
	private String statusFilter="";
	private String actionName = "";
	private String formulaCode = "";
	private String formulaName = "";
	private String templateCode = "";
	private String templateName = "";
	private String status = "";
	private String promCodeItt = "";
	private String promotionDate = "";
	private String promoEffDate = "";
	private String promotionFlag="false";
	private String ratingChangedFlag="false";
	private String empMailId="";

	private String appraisalIdItt = "";
	private String employeeIdItt = "";
	private String employeeTokenItt = "";
	private String employeeNameItt = "";
	private String empGradeIdItt = "";
	private String empGradeItt = "";
	private String empNewGradeIdItt = "";
	private String empNewGradeItt = "";
	private String employeeScoreItt = "";
	private String oldCtcItt = "";
	private String newCtcItt = "";
	private String modCtcItt = "";
	private String percentIncrementItt = "";
	private String criticalIncrementItt = "";
	private String phaseCode="";
	LinkedHashMap<String, String> phaseMap=new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> formulaMap=new LinkedHashMap<String, String>();
	private boolean searchFlag = false;
	private ArrayList pendingScoreList;
	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getAppraisalId() {
		return appraisalId;
	}
	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}
	public String getAppraisalCode() {
		return appraisalCode;
	}
	public void setAppraisalCode(String appraisalCode) {
		this.appraisalCode = appraisalCode;
	}
	public String getAppraisalName() {
		return appraisalName;
	}
	public void setAppraisalName(String appraisalName) {
		this.appraisalName = appraisalName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAppraisalIdItt() {
		return appraisalIdItt;
	}
	public void setAppraisalIdItt(String appraisalIdItt) {
		this.appraisalIdItt = appraisalIdItt;
	}
	public String getEmployeeIdItt() {
		return employeeIdItt;
	}
	public void setEmployeeIdItt(String employeeIdItt) {
		this.employeeIdItt = employeeIdItt;
	}
	public String getEmployeeTokenItt() {
		return employeeTokenItt;
	}
	public void setEmployeeTokenItt(String employeeTokenItt) {
		this.employeeTokenItt = employeeTokenItt;
	}
	public String getEmployeeNameItt() {
		return employeeNameItt;
	}
	public void setEmployeeNameItt(String employeeNameItt) {
		this.employeeNameItt = employeeNameItt;
	}
	
	public String getOldCtcItt() {
		return oldCtcItt;
	}
	public void setOldCtcItt(String oldCtcItt) {
		this.oldCtcItt = oldCtcItt;
	}
	public String getNewCtcItt() {
		return newCtcItt;
	}
	public void setNewCtcItt(String newCtcItt) {
		this.newCtcItt = newCtcItt;
	}
	public String getModCtcItt() {
		return modCtcItt;
	}
	public void setModCtcItt(String modCtcItt) {
		this.modCtcItt = modCtcItt;
	}
	public boolean isSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
	}
	public ArrayList getPendingScoreList() {
		return pendingScoreList;
	}
	public void setPendingScoreList(ArrayList pendingScoreList) {
		this.pendingScoreList = pendingScoreList;
	}
	public String getEmpGradeItt() {
		return empGradeItt;
	}
	public void setEmpGradeItt(String empGradeItt) {
		this.empGradeItt = empGradeItt;
	}
	public String getPercentIncrementItt() {
		return percentIncrementItt;
	}
	public void setPercentIncrementItt(String percentIncrementItt) {
		this.percentIncrementItt = percentIncrementItt;
	}
	public String getCriticalIncrementItt() {
		return criticalIncrementItt;
	}
	public void setCriticalIncrementItt(String criticalIncrementItt) {
		this.criticalIncrementItt = criticalIncrementItt;
	}
	public String getEmployeeScoreItt() {
		return employeeScoreItt;
	}
	public void setEmployeeScoreItt(String employeeScoreItt) {
		this.employeeScoreItt = employeeScoreItt;
	}
	public String getGroupHeadId() {
		return groupHeadId;
	}
	public void setGroupHeadId(String groupHeadId) {
		this.groupHeadId = groupHeadId;
	}
	public String getGroupHeadName() {
		return groupHeadName;
	}
	public void setGroupHeadName(String groupHeadName) {
		this.groupHeadName = groupHeadName;
	}
	public String getGroupHeadToken() {
		return groupHeadToken;
	}
	public void setGroupHeadToken(String groupHeadToken) {
		this.groupHeadToken = groupHeadToken;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerToken() {
		return managerToken;
	}
	public void setManagerToken(String managerToken) {
		this.managerToken = managerToken;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getEmpGradeIdItt() {
		return empGradeIdItt;
	}
	public void setEmpGradeIdItt(String empGradeIdItt) {
		this.empGradeIdItt = empGradeIdItt;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public LinkedHashMap<String, String> getPhaseMap() {
		return phaseMap;
	}
	public void setPhaseMap(LinkedHashMap<String, String> phaseMap) {
		this.phaseMap = phaseMap;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getFormulaCode() {
		return formulaCode;
	}
	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}
	public LinkedHashMap<String, String> getFormulaMap() {
		return formulaMap;
	}
	public void setFormulaMap(LinkedHashMap<String, String> formulaMap) {
		this.formulaMap = formulaMap;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPromCodeItt() {
		return promCodeItt;
	}
	public void setPromCodeItt(String promCodeItt) {
		this.promCodeItt = promCodeItt;
	}
	public String getPromotionDate() {
		return promotionDate;
	}
	public void setPromotionDate(String promotionDate) {
		this.promotionDate = promotionDate;
	}
	public String getPromoEffDate() {
		return promoEffDate;
	}
	public void setPromoEffDate(String promoEffDate) {
		this.promoEffDate = promoEffDate;
	}
	public String getEmpNewGradeIdItt() {
		return empNewGradeIdItt;
	}
	public void setEmpNewGradeIdItt(String empNewGradeIdItt) {
		this.empNewGradeIdItt = empNewGradeIdItt;
	}
	public String getEmpNewGradeItt() {
		return empNewGradeItt;
	}
	public void setEmpNewGradeItt(String empNewGradeItt) {
		this.empNewGradeItt = empNewGradeItt;
	}
	public String getPromotionFlag() {
		return promotionFlag;
	}
	public void setPromotionFlag(String promotionFlag) {
		this.promotionFlag = promotionFlag;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
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
	public String getEmpMailId() {
		return empMailId;
	}
	public void setEmpMailId(String empMailId) {
		this.empMailId = empMailId;
	}
	public String getStatusFilter() {
		return statusFilter;
	}
	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}
	public String getRatingChangedFlag() {
		return ratingChangedFlag;
	}
	public void setRatingChangedFlag(String ratingChangedFlag) {
		this.ratingChangedFlag = ratingChangedFlag;
	}
	public String getPublishFlag() {
		return publishFlag;
	}
	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}
	
	
	
}
