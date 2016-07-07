/**
 * 
 */
package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class SectionMapping extends BeanBase {

	
	ArrayList apprGroupList;
	private String ittrApprCode="";
	private String ittrApprId="";
	private String ittrGroupName="";
	private String ittrGroupId="";
	private String ittrGroupDate="";
	
	ArrayList confAppraiseeList;
	private String ittrConfEmpToken="";
	private String ittrConfEmpName="";
	private String ittrConfEmpBranch="";
	private String ittrConfEmpDepartment="";
	private String ittrConfEmpDesgination="";
	private String ittrConfEmpReptTo="";
	private String ittrConfEmpId="";
	
	ArrayList pendAppraiseeList;
	private String ittrPendEmpToken="";
	private String ittrPendEmpName="";
	private String ittrPendEmpBranch="";
	private String ittrPendEmpDepartment="";
	private String ittrPendEmpDesgination="";
	private String ittrPendEmpReptTo="";
	private String ittrPendEmpId="";
	
	private String designationName="";
	private String designationId="";
	private String gradeName="";
	private String gradeId="";
	private String empTypeName="";
	private String empTypeId="";
	private String empreptToken="";
	private String empreptName="";
	private String empreptId="";
	
	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	private String templateCode;
	private String templateName;
	private String sectionId;
	private String sectionName;
	private String divCode;
	private String divName;
	private String branchCode;
	private String branchName;
	private String deptCode;
	private String deptName;
	private String parameterId;
	private String parameter;
	private String weightage;
	private String questionOrder;
	private String groupName;
	private String groupId;
	private String selectGroupName;
	private String selectGroupId;
	private String empTypeFlag="true";
	private String generateListFlag="false";
	private String addQuestionFlag="false";
	private String modifiedListFlag="false";
	private String isSectionDefinedFlag="false";
	private String paraId;
	HashMap hashmapGroup;
	private String moveGroupName;
	private String moveEmpId;
	
	private String sectionIdList;
	private String sectionNameList;
	private String parameterIdList;
	private String parameterList;
	private String weightageList;
	private String questionOrderList;
	ArrayList sectionList;
	
	
	
	public ArrayList getSectionList() {
		return sectionList;
	}
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
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
	public String getSelectGroupName() {
		return selectGroupName;
	}
	public void setSelectGroupName(String selectGroupName) {
		this.selectGroupName = selectGroupName;
	}
	public String getSelectGroupId() {
		return selectGroupId;
	}
	public void setSelectGroupId(String selectGroupId) {
		this.selectGroupId = selectGroupId;
	}
	public String getEmpTypeFlag() {
		return empTypeFlag;
	}
	public void setEmpTypeFlag(String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	public String getGenerateListFlag() {
		return generateListFlag;
	}
	public void setGenerateListFlag(String generateListFlag) {
		this.generateListFlag = generateListFlag;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public HashMap getHashmapGroup() {
		return hashmapGroup;
	}
	public void setHashmapGroup(HashMap hashmapGroup) {
		this.hashmapGroup = hashmapGroup;
	}
	public String getMoveGroupName() {
		return moveGroupName;
	}
	public void setMoveGroupName(String moveGroupName) {
		this.moveGroupName = moveGroupName;
	}
	public String getMoveEmpId() {
		return moveEmpId;
	}
	public void setMoveEmpId(String moveEmpId) {
		this.moveEmpId = moveEmpId;
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
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getParameterId() {
		return parameterId;
	}
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getWeightage() {
		return weightage;
	}
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
	public String getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(String questionOrder) {
		this.questionOrder = questionOrder;
	}
	public String getSectionIdList() {
		return sectionIdList;
	}
	public void setSectionIdList(String sectionIdList) {
		this.sectionIdList = sectionIdList;
	}
	public String getSectionNameList() {
		return sectionNameList;
	}
	public void setSectionNameList(String sectionNameList) {
		this.sectionNameList = sectionNameList;
	}
	public String getParameterIdList() {
		return parameterIdList;
	}
	public void setParameterIdList(String parameterIdList) {
		this.parameterIdList = parameterIdList;
	}
	public String getParameterList() {
		return parameterList;
	}
	public void setParameterList(String parameterList) {
		this.parameterList = parameterList;
	}
	public String getWeightageList() {
		return weightageList;
	}
	public void setWeightageList(String weightageList) {
		this.weightageList = weightageList;
	}
	public String getQuestionOrderList() {
		return questionOrderList;
	}
	public void setQuestionOrderList(String questionOrderList) {
		this.questionOrderList = questionOrderList;
	}
	public String getAddQuestionFlag() {
		return addQuestionFlag;
	}
	public void setAddQuestionFlag(String addQuestionFlag) {
		this.addQuestionFlag = addQuestionFlag;
	}
	public String getModifiedListFlag() {
		return modifiedListFlag;
	}
	public void setModifiedListFlag(String modifiedListFlag) {
		this.modifiedListFlag = modifiedListFlag;
	}
	public String getIsSectionDefinedFlag() {
		return isSectionDefinedFlag;
	}
	public void setIsSectionDefinedFlag(String isSectionDefinedFlag) {
		this.isSectionDefinedFlag = isSectionDefinedFlag;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public ArrayList getApprGroupList() {
		return apprGroupList;
	}
	public void setApprGroupList(ArrayList apprGroupList) {
		this.apprGroupList = apprGroupList;
	}
	public String getIttrApprCode() {
		return ittrApprCode;
	}
	public void setIttrApprCode(String ittrApprCode) {
		this.ittrApprCode = ittrApprCode;
	}
	public String getIttrApprId() {
		return ittrApprId;
	}
	public void setIttrApprId(String ittrApprId) {
		this.ittrApprId = ittrApprId;
	}
	public String getIttrGroupName() {
		return ittrGroupName;
	}
	public void setIttrGroupName(String ittrGroupName) {
		this.ittrGroupName = ittrGroupName;
	}
	public String getIttrGroupId() {
		return ittrGroupId;
	}
	public void setIttrGroupId(String ittrGroupId) {
		this.ittrGroupId = ittrGroupId;
	}
	public String getIttrGroupDate() {
		return ittrGroupDate;
	}
	public void setIttrGroupDate(String ittrGroupDate) {
		this.ittrGroupDate = ittrGroupDate;
	}
	public ArrayList getConfAppraiseeList() {
		return confAppraiseeList;
	}
	public void setConfAppraiseeList(ArrayList confAppraiseeList) {
		this.confAppraiseeList = confAppraiseeList;
	}
	public String getIttrConfEmpToken() {
		return ittrConfEmpToken;
	}
	public void setIttrConfEmpToken(String ittrConfEmpToken) {
		this.ittrConfEmpToken = ittrConfEmpToken;
	}
	public String getIttrConfEmpName() {
		return ittrConfEmpName;
	}
	public void setIttrConfEmpName(String ittrConfEmpName) {
		this.ittrConfEmpName = ittrConfEmpName;
	}
	public String getIttrConfEmpBranch() {
		return ittrConfEmpBranch;
	}
	public void setIttrConfEmpBranch(String ittrConfEmpBranch) {
		this.ittrConfEmpBranch = ittrConfEmpBranch;
	}
	public String getIttrConfEmpDepartment() {
		return ittrConfEmpDepartment;
	}
	public void setIttrConfEmpDepartment(String ittrConfEmpDepartment) {
		this.ittrConfEmpDepartment = ittrConfEmpDepartment;
	}
	public String getIttrConfEmpDesgination() {
		return ittrConfEmpDesgination;
	}
	public void setIttrConfEmpDesgination(String ittrConfEmpDesgination) {
		this.ittrConfEmpDesgination = ittrConfEmpDesgination;
	}
	public String getIttrConfEmpReptTo() {
		return ittrConfEmpReptTo;
	}
	public void setIttrConfEmpReptTo(String ittrConfEmpReptTo) {
		this.ittrConfEmpReptTo = ittrConfEmpReptTo;
	}
	public String getIttrConfEmpId() {
		return ittrConfEmpId;
	}
	public void setIttrConfEmpId(String ittrConfEmpId) {
		this.ittrConfEmpId = ittrConfEmpId;
	}
	public ArrayList getPendAppraiseeList() {
		return pendAppraiseeList;
	}
	public void setPendAppraiseeList(ArrayList pendAppraiseeList) {
		this.pendAppraiseeList = pendAppraiseeList;
	}
	public String getIttrPendEmpToken() {
		return ittrPendEmpToken;
	}
	public void setIttrPendEmpToken(String ittrPendEmpToken) {
		this.ittrPendEmpToken = ittrPendEmpToken;
	}
	public String getIttrPendEmpName() {
		return ittrPendEmpName;
	}
	public void setIttrPendEmpName(String ittrPendEmpName) {
		this.ittrPendEmpName = ittrPendEmpName;
	}
	public String getIttrPendEmpBranch() {
		return ittrPendEmpBranch;
	}
	public void setIttrPendEmpBranch(String ittrPendEmpBranch) {
		this.ittrPendEmpBranch = ittrPendEmpBranch;
	}
	public String getIttrPendEmpDepartment() {
		return ittrPendEmpDepartment;
	}
	public void setIttrPendEmpDepartment(String ittrPendEmpDepartment) {
		this.ittrPendEmpDepartment = ittrPendEmpDepartment;
	}
	public String getIttrPendEmpDesgination() {
		return ittrPendEmpDesgination;
	}
	public void setIttrPendEmpDesgination(String ittrPendEmpDesgination) {
		this.ittrPendEmpDesgination = ittrPendEmpDesgination;
	}
	public String getIttrPendEmpReptTo() {
		return ittrPendEmpReptTo;
	}
	public void setIttrPendEmpReptTo(String ittrPendEmpReptTo) {
		this.ittrPendEmpReptTo = ittrPendEmpReptTo;
	}
	public String getIttrPendEmpId() {
		return ittrPendEmpId;
	}
	public void setIttrPendEmpId(String ittrPendEmpId) {
		this.ittrPendEmpId = ittrPendEmpId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
	}
	public String getEmpreptToken() {
		return empreptToken;
	}
	public void setEmpreptToken(String empreptToken) {
		this.empreptToken = empreptToken;
	}
	public String getEmpreptName() {
		return empreptName;
	}
	public void setEmpreptName(String empreptName) {
		this.empreptName = empreptName;
	}
	public String getEmpreptId() {
		return empreptId;
	}
	public void setEmpreptId(String empreptId) {
		this.empreptId = empreptId;
	}
}
