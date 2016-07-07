/**
 * 
 */
package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class MgmntReviewPanel extends BeanBase {

	
	private String groupHeadName;
	private String apprCode;
	private String apprId;
	private String frmDate ;
	private String toDate ;
	LinkedHashMap<String, String> phaseMap=new LinkedHashMap<String, String>();
	private boolean grpHeadFlag = false;
	private String grpHeadId;
	private String hidGroupHeadId;
	
	private String grpHeadName;
	private String grpHeadDept;
	private String grpHeadDesg;
	private String grpHeadCenter;
	private ArrayList<Object> grpHeadList;
	private int totalRecords = 0;
	private boolean recordsAvailable = false;
	private String myPage;
	private String show;
	private String groupId;
	private String groupName;
	private String phaseCode;
	private String hidGroupId;
	private boolean managerFlag = false;
	private String managerId;
	private String managerIdList;
	private String managerName;
	private String managerDept;
	private String managerDiv;
	private String managerDesg;
	private String managerCenter;
	private boolean mgrRecordsAvailable = false;
	
	private ArrayList<Object> managerList;
	
	private String empId;
	private String empName;
	private String empKRAScore;
	private String empCompetencyScore;
	private String empModScore;
	private String empTotalScore;
	private String empHRAction;
	private ArrayList<Object> employeeList;
	private boolean empRecordsAvailable = false;
	
	/*
	 * added by prashant
	 */
	
	private String hiddenEmpId;
	private String hiddenManagerId;
	private String employeeId;
	private String empToken;
	private String employeeName;
	private String branchId;
	private String branchName;
	private String deptId;
	private String deptName;
	private String desigId;
	private String desigName;
	private String divisionId;
	private String divisionName;
	private String kraScore;
	private String hiddenKraScore;
	private String competencyScore;
	private String hiddenCompetencyScore;
	
	
	private ArrayList<Object> analysisList;
	private String questionCodeItt;
	private String competencyItt;
	private String competencyRatingItt;
	private String competencyManagerRatingItt;
	private String selfJustificationItt;
	private String managerJustificationItt;
	
	public String getHiddenEmpId() {
		return hiddenEmpId;
	}
	public void setHiddenEmpId(String hiddenEmpId) {
		this.hiddenEmpId = hiddenEmpId;
	}
	public String getHiddenManagerId() {
		return hiddenManagerId;
	}
	public void setHiddenManagerId(String hiddenManagerId) {
		this.hiddenManagerId = hiddenManagerId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesigId() {
		return desigId;
	}
	public void setDesigId(String desigId) {
		this.desigId = desigId;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getKraScore() {
		return kraScore;
	}
	public void setKraScore(String kraScore) {
		this.kraScore = kraScore;
	}
	public String getHiddenKraScore() {
		return hiddenKraScore;
	}
	public void setHiddenKraScore(String hiddenKraScore) {
		this.hiddenKraScore = hiddenKraScore;
	}
	public String getCompetencyScore() {
		return competencyScore;
	}
	public void setCompetencyScore(String competencyScore) {
		this.competencyScore = competencyScore;
	}
	public String getHiddenCompetencyScore() {
		return hiddenCompetencyScore;
	}
	public void setHiddenCompetencyScore(String hiddenCompetencyScore) {
		this.hiddenCompetencyScore = hiddenCompetencyScore;
	}
	public ArrayList<Object> getAnalysisList() {
		return analysisList;
	}
	public void setAnalysisList(ArrayList<Object> analysisList) {
		this.analysisList = analysisList;
	}
	public String getQuestionCodeItt() {
		return questionCodeItt;
	}
	public void setQuestionCodeItt(String questionCodeItt) {
		this.questionCodeItt = questionCodeItt;
	}
	public String getCompetencyItt() {
		return competencyItt;
	}
	public void setCompetencyItt(String competencyItt) {
		this.competencyItt = competencyItt;
	}
	public String getCompetencyRatingItt() {
		return competencyRatingItt;
	}
	public void setCompetencyRatingItt(String competencyRatingItt) {
		this.competencyRatingItt = competencyRatingItt;
	}
	public String getCompetencyManagerRatingItt() {
		return competencyManagerRatingItt;
	}
	public void setCompetencyManagerRatingItt(String competencyManagerRatingItt) {
		this.competencyManagerRatingItt = competencyManagerRatingItt;
	}
	public String getSelfJustificationItt() {
		return selfJustificationItt;
	}
	public void setSelfJustificationItt(String selfJustificationItt) {
		this.selfJustificationItt = selfJustificationItt;
	}
	public String getManagerJustificationItt() {
		return managerJustificationItt;
	}
	public void setManagerJustificationItt(String managerJustificationItt) {
		this.managerJustificationItt = managerJustificationItt;
	}
	public boolean isEmpRecordsAvailable() {
		return empRecordsAvailable;
	}
	public void setEmpRecordsAvailable(boolean empRecordsAvailable) {
		this.empRecordsAvailable = empRecordsAvailable;
	}
	public String getHidGroupId() {
		return hidGroupId;
	}
	public void setHidGroupId(String hidGroupId) {
		this.hidGroupId = hidGroupId;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}
	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;
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
	public LinkedHashMap<String, String> getPhaseMap() {
		return phaseMap;
	}
	public void setPhaseMap(LinkedHashMap<String, String> phaseMap) {
		this.phaseMap = phaseMap;
	}
	public String getGrpHeadId() {
		return grpHeadId;
	}
	public void setGrpHeadId(String grpHeadId) {
		this.grpHeadId = grpHeadId;
	}
	public String getGrpHeadName() {
		return grpHeadName;
	}
	public void setGrpHeadName(String grpHeadName) {
		this.grpHeadName = grpHeadName;
	}
	public String getGrpHeadDept() {
		return grpHeadDept;
	}
	public void setGrpHeadDept(String grpHeadDept) {
		this.grpHeadDept = grpHeadDept;
	}
	public String getGrpHeadDesg() {
		return grpHeadDesg;
	}
	public void setGrpHeadDesg(String grpHeadDesg) {
		this.grpHeadDesg = grpHeadDesg;
	}
	public String getGrpHeadCenter() {
		return grpHeadCenter;
	}
	public void setGrpHeadCenter(String grpHeadCenter) {
		this.grpHeadCenter = grpHeadCenter;
	}
	public ArrayList<Object> getGrpHeadList() {
		return grpHeadList;
	}
	public void setGrpHeadList(ArrayList<Object> grpHeadList) {
		this.grpHeadList = grpHeadList;
	}
	public boolean isGrpHeadFlag() {
		return grpHeadFlag;
	}
	public void setGrpHeadFlag(boolean grpHeadFlag) {
		this.grpHeadFlag = grpHeadFlag;
	}
	public boolean isManagerFlag() {
		return managerFlag;
	}
	public void setManagerFlag(boolean managerFlag) {
		this.managerFlag = managerFlag;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerDept() {
		return managerDept;
	}
	public void setManagerDept(String managerDept) {
		this.managerDept = managerDept;
	}
	public String getManagerDesg() {
		return managerDesg;
	}
	public void setManagerDesg(String managerDesg) {
		this.managerDesg = managerDesg;
	}
	public String getManagerCenter() {
		return managerCenter;
	}
	public void setManagerCenter(String managerCenter) {
		this.managerCenter = managerCenter;
	}
	public boolean isMgrRecordsAvailable() {
		return mgrRecordsAvailable;
	}
	public void setMgrRecordsAvailable(boolean mgrRecordsAvailable) {
		this.mgrRecordsAvailable = mgrRecordsAvailable;
	}
	public ArrayList<Object> getManagerList() {
		return managerList;
	}
	public void setManagerList(ArrayList<Object> managerList) {
		this.managerList = managerList;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getManagerDiv() {
		return managerDiv;
	}
	public void setManagerDiv(String managerDiv) {
		this.managerDiv = managerDiv;
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
	public String getEmpKRAScore() {
		return empKRAScore;
	}
	public void setEmpKRAScore(String empKRAScore) {
		this.empKRAScore = empKRAScore;
	}
	public String getEmpCompetencyScore() {
		return empCompetencyScore;
	}
	public void setEmpCompetencyScore(String empCompetencyScore) {
		this.empCompetencyScore = empCompetencyScore;
	}
	public String getEmpModScore() {
		return empModScore;
	}
	public void setEmpModScore(String empModScore) {
		this.empModScore = empModScore;
	}
	public String getEmpTotalScore() {
		return empTotalScore;
	}
	public void setEmpTotalScore(String empTotalScore) {
		this.empTotalScore = empTotalScore;
	}
	public String getEmpHRAction() {
		return empHRAction;
	}
	public void setEmpHRAction(String empHRAction) {
		this.empHRAction = empHRAction;
	}
	public ArrayList<Object> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(ArrayList<Object> employeeList) {
		this.employeeList = employeeList;
	}
	public String getHidGroupHeadId() {
		return hidGroupHeadId;
	}
	public void setHidGroupHeadId(String hidGroupHeadId) {
		this.hidGroupHeadId = hidGroupHeadId;
	}
	public String getManagerIdList() {
		return managerIdList;
	}
	public void setManagerIdList(String managerIdList) {
		this.managerIdList = managerIdList;
	}
	public String getGroupHeadName() {
		return groupHeadName;
	}
	public void setGroupHeadName(String groupHeadName) {
		this.groupHeadName = groupHeadName;
	}
}
