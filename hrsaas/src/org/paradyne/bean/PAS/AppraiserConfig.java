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
public class AppraiserConfig extends BeanBase {

	
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
	
	
	
	private String apprCode;
	private String apprId ;
	private String frmDate ;
	private String toDate ;
	private String groupName;
	private String groupId;
	private String selectGroupName;
	private String selectGroupId;
	private String empTypeFlag="true";
	private String generateListFlag="false";
	private String addAppraiserFlag="false";
	private String modifiedListFlag="false";
	private String divCode;
	private String divName;
	private String branchCode;
	private String branchName;
	private String deptCode;
	private String deptName;
	
	private String isSelfPhase;
	private String isSelfPhaseList;
	private String phaseName;
	private String phaseId;
	private String selfAppraisalCheck;
	private String appraiserId;
	private String appraiserName;
	private String appraiserCode;
	private String appraiserLevel;
	
	ArrayList appraiserList;
	private String appraisalCodeList;
	
	private String appraisalIdList;
	private String phaseNameList;
	private String phaseIdList;
	private String appraiserNameList;
	private String appraiserCodeList;
	private String appraisalLevelList;
	private String removeAppraiser;
	private String paraId;
	HashMap hashmapGroup;
	private String moveGroupName;
	private String moveEmpId;
	
	private String calUpdateflag="false";
	public String getCalUpdateflag() {
		return calUpdateflag;
	}
	public void setCalUpdateflag(String calUpdateflag) {
		this.calUpdateflag = calUpdateflag;
	}
	public String getMoveEmpId() {
		return moveEmpId;
	}
	public void setMoveEmpId(String moveEmpId) {
		this.moveEmpId = moveEmpId;
	}
	public String getMoveGroupName() {
		return moveGroupName;
	}
	public void setMoveGroupName(String moveGroupName) {
		this.moveGroupName = moveGroupName;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
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
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
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
	public String getEmpTypeFlag() {
		return empTypeFlag;
	}
	public void setEmpTypeFlag(String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getSelfAppraisalCheck() {
		return selfAppraisalCheck;
	}
	public void setSelfAppraisalCheck(String selfAppraisalCheck) {
		this.selfAppraisalCheck = selfAppraisalCheck;
	}
	public String getAppraiserId() {
		return appraiserId;
	}
	public void setAppraiserId(String appraiserId) {
		this.appraiserId = appraiserId;
	}
	public String getAppraiserName() {
		return appraiserName;
	}
	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}
	public String getAppraiserCode() {
		return appraiserCode;
	}
	public void setAppraiserCode(String appraiserCode) {
		this.appraiserCode = appraiserCode;
	}
	public String getAppraiserLevel() {
		return appraiserLevel;
	}
	public void setAppraiserLevel(String appraiserLevel) {
		this.appraiserLevel = appraiserLevel;
	}
	public ArrayList getAppraiserList() {
		return appraiserList;
	}
	public void setAppraiserList(ArrayList appraiserList) {
		this.appraiserList = appraiserList;
	}
	public String getAppraisalCodeList() {
		return appraisalCodeList;
	}
	public void setAppraisalCodeList(String appraisalCodeList) {
		this.appraisalCodeList = appraisalCodeList;
	}
	public String getAppraisalIdList() {
		return appraisalIdList;
	}
	public void setAppraisalIdList(String appraisalIdList) {
		this.appraisalIdList = appraisalIdList;
	}
	public String getPhaseNameList() {
		return phaseNameList;
	}
	public void setPhaseNameList(String phaseNameList) {
		this.phaseNameList = phaseNameList;
	}
	public String getPhaseIdList() {
		return phaseIdList;
	}
	public void setPhaseIdList(String phaseIdList) {
		this.phaseIdList = phaseIdList;
	}
	public String getAppraiserNameList() {
		return appraiserNameList;
	}
	public void setAppraiserNameList(String appraiserNameList) {
		this.appraiserNameList = appraiserNameList;
	}
	public String getAppraiserCodeList() {
		return appraiserCodeList;
	}
	public void setAppraiserCodeList(String appraiserCodeList) {
		this.appraiserCodeList = appraiserCodeList;
	}
	public String getAppraisalLevelList() {
		return appraisalLevelList;
	}
	public void setAppraisalLevelList(String appraisalLevelList) {
		this.appraisalLevelList = appraisalLevelList;
	}
	public String getRemoveAppraiser() {
		return removeAppraiser;
	}
	public void setRemoveAppraiser(String removeAppraiser) {
		this.removeAppraiser = removeAppraiser;
	}
	public HashMap getHashmapGroup() {
		return hashmapGroup;
	}
	public void setHashmapGroup(HashMap hashmapGroup) {
		this.hashmapGroup = hashmapGroup;
	}
	public String getGenerateListFlag() {
		return generateListFlag;
	}
	public void setGenerateListFlag(String generateListFlag) {
		this.generateListFlag = generateListFlag;
	}
	public String getIsSelfPhase() {
		return isSelfPhase;
	}
	public void setIsSelfPhase(String isSelfPhase) {
		this.isSelfPhase = isSelfPhase;
	}
	public String getIsSelfPhaseList() {
		return isSelfPhaseList;
	}
	public void setIsSelfPhaseList(String isSelfPhaseList) {
		this.isSelfPhaseList = isSelfPhaseList;
	}
	public String getAddAppraiserFlag() {
		return addAppraiserFlag;
	}
	public void setAddAppraiserFlag(String addAppraiserFlag) {
		this.addAppraiserFlag = addAppraiserFlag;
	}
	public String getModifiedListFlag() {
		return modifiedListFlag;
	}
	public void setModifiedListFlag(String modifiedListFlag) {
		this.modifiedListFlag = modifiedListFlag;
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
	public ArrayList getPendAppraiseeList() {
		return pendAppraiseeList;
	}
	public void setPendAppraiseeList(ArrayList pendAppraiseeList) {
		this.pendAppraiseeList = pendAppraiseeList;
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
	public String getIttrPendEmpToken() {
		return ittrPendEmpToken;
	}
	public void setIttrPendEmpToken(String ittrPendEmpToken) {
		this.ittrPendEmpToken = ittrPendEmpToken;
	}
	
}
