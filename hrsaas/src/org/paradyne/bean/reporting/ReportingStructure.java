
package org.paradyne.bean.reporting;

import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.BeanBase;

/**
 * @author Vishwambhar Deshpande
 * 
 * Bean class for Reporting Structure
 *
 */
public class ReportingStructure extends BeanBase {
	
 
	private String moveDivCode ="";
	private String empTypeFlag="true";
	private String selectGroupId ="";
	private String moveEmpId="";
	private HashMap hashmapGroup=null;
	
	
	private String defineSelectSource ="";
	private String moduleFlag="false";
	private String groupFlag="false";
	
	
	private String selectExistModule ="";
	private String selectExistModuleAbbr ="";
	 
	
	private String srNoItt ="";
	private String showFlag ="false";
	 private String showGroup ="false";
	private String paraIdEmp  ="";
	private String countEmp  ="";
	private ArrayList confEmployeeList =null;
	private String ittrConfEmpToken ="";
	private String ittrConfEmpId ="";
	private String ittrConfEmpName =""; 
	private String ittrConfEmpBranch ="";
	private String ittrConfEmpDepartment ="";
	private String ittrConfEmpDesgination ="";
	private String ittrConfEmpReptTo ="";
	private String moveAppraisee ="";
	private String moveEmployee ="";
	private String deleteEmployee ="";
	
	private String count  ="";
	private String paraId  ="";
	private String  ittrPendEmpId  ="";
	private String empGroupName ="";
	private String empGroupCode ="";
	private String empDivCode ="";
	private String empModuleAbbr ="";
	private String  empModuleName  ="";
	private String ittrPendEmpToken   ="";
	private String ittrPendEmpName   ="";
	private String ittrPendEmpBranch   ="";
	private String ittrPendEmpDepartment   ="";
	private String ittrPendEmpDesgination   ="";
	private String ittrPendEmpReptTo   ="";
	private String addAppraisee   ="";
	private String pendEmpList   ="";
	private String  teamGroupName  ="";
	private String  teamGroupCode  ="";

	private String  teamModuleName  ="";
	private String  teamModuleAbbr  ="";
	
	private String teamDivCode ="";
	private String empReportingTo ="";
	private String tableLength ="true";
	private ArrayList pendEmployeeList =null;

 
	
	private String reportingtotoken ="";
	private String reportingto ="";
	private String reportingtoCode ="";
	private String division ="";
	private String divisionCode ="";

	private String branchCode ="";
	private String branch ="";

	private String deptName ="";
	private String deptCode ="";


	private String desgName ="";
	private String desgCode ="";
 

	private String grade ="";
	private String gradeCode ="";

	private String empType ="";
	private String empTypeCode ="";
	
	private String divCodeApprover ="";
	private String moduleNameApprover ="";
	private String moduleAbbrApprover ="";

	private String groupNameApprover ="";
	private String groupCodeApprover ="";
	
	private String manager1EmpToken ="";
	private String manager1EmpName ="";
	private String manager1EmpCode ="";

	private String manager1EmpTokenAlter ="";
	private String manager1EmpNameAlter ="";
	private String manager1EmpCodeAlter ="";

	private String manager2EmpToken ="";
	private String manager2EmpName ="";
	private String manager2EmpCode ="";

	private String manager2EmpTokenAlter ="";
	private String manager2EmpNameAlter ="";
	private String manager2EmpCodeAlter ="";
 
	private String manager3EmpToken ="";
	private String manager3EmpName ="";
	private String manager3EmpCode ="";

	private String manager3EmpTokenAlter ="";
	private String manager3EmpNameAlter ="";
	private String manager3EmpCodeAlter ="";
	
	private String groupDivision ="";
	private ArrayList   groupList =null;

	private String groupNameItt ="";
	
	private String groupIdItt ="";

	private String groupCreateDate ="";
	
	private String moduleAbbrSelect ="";  
	private String moduleNameSelect ="";  

	private String divName ="";  
	private String divCode ="";
	private ArrayList  moduleList=null;
	private String moduleName ="";
	private String isConfigure ="";
	private String reportingType ="";
	private String reportingLevel ="";
	private String moduleNameItt ="";
	private String moduleAbbrItt ="";
	private String 	groupName ="";
	private String selectgroupName ="";
	 
	public String getModuleNameItt() {
		return moduleNameItt;
	}
	public void setModuleNameItt(String moduleNameItt) {
		this.moduleNameItt = moduleNameItt;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSelectgroupName() {
		return selectgroupName;
	}
	public void setSelectgroupName(String selectgroupName) {
		this.selectgroupName = selectgroupName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getIsConfigure() {
		return isConfigure;
	}
	public void setIsConfigure(String isConfigure) {
		this.isConfigure = isConfigure;
	}
	public String getReportingType() {
		return reportingType;
	}
	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}
	public String getReportingLevel() {
		return reportingLevel;
	}
	public void setReportingLevel(String reportingLevel) {
		this.reportingLevel = reportingLevel;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public ArrayList getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}
	public String getModuleAbbrItt() {
		return moduleAbbrItt;
	}
	public void setModuleAbbrItt(String moduleAbbrItt) {
		this.moduleAbbrItt = moduleAbbrItt;
	}
	public String getModuleAbbrSelect() {
		return moduleAbbrSelect;
	}
	public void setModuleAbbrSelect(String moduleAbbrSelect) {
		this.moduleAbbrSelect = moduleAbbrSelect;
	}
	public String getModuleNameSelect() {
		return moduleNameSelect;
	}
	public void setModuleNameSelect(String moduleNameSelect) {
		this.moduleNameSelect = moduleNameSelect;
	}
	public ArrayList getGroupList() {
		return groupList;
	}
	public void setGroupList(ArrayList groupList) {
		this.groupList = groupList;
	}
	public String getGroupNameItt() {
		return groupNameItt;
	}
	public void setGroupNameItt(String groupNameItt) {
		this.groupNameItt = groupNameItt;
	}
	public String getGroupCreateDate() {
		return groupCreateDate;
	}
	public void setGroupCreateDate(String groupCreateDate) {
		this.groupCreateDate = groupCreateDate;
	}
	public String getGroupIdItt() {
		return groupIdItt;
	}
	public void setGroupIdItt(String groupIdItt) {
		this.groupIdItt = groupIdItt;
	}
	public String getGroupDivision() {
		return groupDivision;
	}
	public void setGroupDivision(String groupDivision) {
		this.groupDivision = groupDivision;
	}
	public String getManager1EmpToken() {
		return manager1EmpToken;
	}
	public void setManager1EmpToken(String manager1EmpToken) {
		this.manager1EmpToken = manager1EmpToken;
	}
	public String getManager1EmpName() {
		return manager1EmpName;
	}
	public void setManager1EmpName(String manager1EmpName) {
		this.manager1EmpName = manager1EmpName;
	}
	public String getManager1EmpCode() {
		return manager1EmpCode;
	}
	public void setManager1EmpCode(String manager1EmpCode) {
		this.manager1EmpCode = manager1EmpCode;
	}
	public String getManager1EmpTokenAlter() {
		return manager1EmpTokenAlter;
	}
	public void setManager1EmpTokenAlter(String manager1EmpTokenAlter) {
		this.manager1EmpTokenAlter = manager1EmpTokenAlter;
	}
	public String getManager1EmpNameAlter() {
		return manager1EmpNameAlter;
	}
	public void setManager1EmpNameAlter(String manager1EmpNameAlter) {
		this.manager1EmpNameAlter = manager1EmpNameAlter;
	}
	public String getManager1EmpCodeAlter() {
		return manager1EmpCodeAlter;
	}
	public void setManager1EmpCodeAlter(String manager1EmpCodeAlter) {
		this.manager1EmpCodeAlter = manager1EmpCodeAlter;
	}
	public String getManager2EmpToken() {
		return manager2EmpToken;
	}
	public void setManager2EmpToken(String manager2EmpToken) {
		this.manager2EmpToken = manager2EmpToken;
	}
	public String getManager2EmpName() {
		return manager2EmpName;
	}
	public void setManager2EmpName(String manager2EmpName) {
		this.manager2EmpName = manager2EmpName;
	}
	public String getManager2EmpCode() {
		return manager2EmpCode;
	}
	public void setManager2EmpCode(String manager2EmpCode) {
		this.manager2EmpCode = manager2EmpCode;
	}
	public String getManager2EmpTokenAlter() {
		return manager2EmpTokenAlter;
	}
	public void setManager2EmpTokenAlter(String manager2EmpTokenAlter) {
		this.manager2EmpTokenAlter = manager2EmpTokenAlter;
	}
	public String getManager2EmpNameAlter() {
		return manager2EmpNameAlter;
	}
	public void setManager2EmpNameAlter(String manager2EmpNameAlter) {
		this.manager2EmpNameAlter = manager2EmpNameAlter;
	}
	public String getManager2EmpCodeAlter() {
		return manager2EmpCodeAlter;
	}
	public void setManager2EmpCodeAlter(String manager2EmpCodeAlter) {
		this.manager2EmpCodeAlter = manager2EmpCodeAlter;
	}
	public String getManager3EmpToken() {
		return manager3EmpToken;
	}
	public void setManager3EmpToken(String manager3EmpToken) {
		this.manager3EmpToken = manager3EmpToken;
	}
	public String getManager3EmpName() {
		return manager3EmpName;
	}
	public void setManager3EmpName(String manager3EmpName) {
		this.manager3EmpName = manager3EmpName;
	}
	public String getManager3EmpCode() {
		return manager3EmpCode;
	}
	public void setManager3EmpCode(String manager3EmpCode) {
		this.manager3EmpCode = manager3EmpCode;
	}
	public String getManager3EmpTokenAlter() {
		return manager3EmpTokenAlter;
	}
	public void setManager3EmpTokenAlter(String manager3EmpTokenAlter) {
		this.manager3EmpTokenAlter = manager3EmpTokenAlter;
	}
	public String getManager3EmpNameAlter() {
		return manager3EmpNameAlter;
	}
	public void setManager3EmpNameAlter(String manager3EmpNameAlter) {
		this.manager3EmpNameAlter = manager3EmpNameAlter;
	}
	public String getManager3EmpCodeAlter() {
		return manager3EmpCodeAlter;
	}
	public void setManager3EmpCodeAlter(String manager3EmpCodeAlter) {
		this.manager3EmpCodeAlter = manager3EmpCodeAlter;
	}
	public String getModuleNameApprover() {
		return moduleNameApprover;
	}
	public void setModuleNameApprover(String moduleNameApprover) {
		this.moduleNameApprover = moduleNameApprover;
	}
	 
	public String getGroupNameApprover() {
		return groupNameApprover;
	}
	public void setGroupNameApprover(String groupNameApprover) {
		this.groupNameApprover = groupNameApprover;
	}
	public String getGroupCodeApprover() {
		return groupCodeApprover;
	}
	public void setGroupCodeApprover(String groupCodeApprover) {
		this.groupCodeApprover = groupCodeApprover;
	}
	public String getModuleAbbrApprover() {
		return moduleAbbrApprover;
	}
	public void setModuleAbbrApprover(String moduleAbbrApprover) {
		this.moduleAbbrApprover = moduleAbbrApprover;
	}
	public String getDivCodeApprover() {
		return divCodeApprover;
	}
	public void setDivCodeApprover(String divCodeApprover) {
		this.divCodeApprover = divCodeApprover;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getReportingto() {
		return reportingto;
	}
	public void setReportingto(String reportingto) {
		this.reportingto = reportingto;
	}
	public String getReportingtoCode() {
		return reportingtoCode;
	}
	public void setReportingtoCode(String reportingtoCode) {
		this.reportingtoCode = reportingtoCode;
	}
	public String getReportingtotoken() {
		return reportingtotoken;
	}
	public void setReportingtotoken(String reportingtotoken) {
		this.reportingtotoken = reportingtotoken;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	 
	 
	public String getEmpReportingTo() {
		return empReportingTo;
	}
	public void setEmpReportingTo(String empReportingTo) {
		this.empReportingTo = empReportingTo;
	}
	public String getTeamDivCode() {
		return teamDivCode;
	}
	public void setTeamDivCode(String teamDivCode) {
		this.teamDivCode = teamDivCode;
	}
	public String getTeamGroupName() {
		return teamGroupName;
	}
	public void setTeamGroupName(String teamGroupName) {
		this.teamGroupName = teamGroupName;
	}
	public String getTeamGroupCode() {
		return teamGroupCode;
	}
	public void setTeamGroupCode(String teamGroupCode) {
		this.teamGroupCode = teamGroupCode;
	}
	public String getTeamModuleName() {
		return teamModuleName;
	}
	public void setTeamModuleName(String teamModuleName) {
		this.teamModuleName = teamModuleName;
	}
	public String getTeamModuleAbbr() {
		return teamModuleAbbr;
	}
	public void setTeamModuleAbbr(String teamModuleAbbr) {
		this.teamModuleAbbr = teamModuleAbbr;
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
	public String getAddAppraisee() {
		return addAppraisee;
	}
	public void setAddAppraisee(String addAppraisee) {
		this.addAppraisee = addAppraisee;
	}
	public String getPendEmpList() {
		return pendEmpList;
	}
	public void setPendEmpList(String pendEmpList) {
		this.pendEmpList = pendEmpList;
	}
	public String getEmpGroupName() {
		return empGroupName;
	}
	public void setEmpGroupName(String empGroupName) {
		this.empGroupName = empGroupName;
	}
	public String getEmpGroupCode() {
		return empGroupCode;
	}
	public void setEmpGroupCode(String empGroupCode) {
		this.empGroupCode = empGroupCode;
	}
	public String getEmpDivCode() {
		return empDivCode;
	}
	public void setEmpDivCode(String empDivCode) {
		this.empDivCode = empDivCode;
	}
	public String getEmpModuleAbbr() {
		return empModuleAbbr;
	}
	public void setEmpModuleAbbr(String empModuleAbbr) {
		this.empModuleAbbr = empModuleAbbr;
	}
	public String getEmpModuleName() {
		return empModuleName;
	}
	public void setEmpModuleName(String empModuleName) {
		this.empModuleName = empModuleName;
	}
	public String getIttrPendEmpId() {
		return ittrPendEmpId;
	}
	public void setIttrPendEmpId(String ittrPendEmpId) {
		this.ittrPendEmpId = ittrPendEmpId;
	}
	public ArrayList getPendEmployeeList() {
		return pendEmployeeList;
	}
	public void setPendEmployeeList(ArrayList pendEmployeeList) {
		this.pendEmployeeList = pendEmployeeList;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getIttrConfEmpToken() {
		return ittrConfEmpToken;
	}
	public void setIttrConfEmpToken(String ittrConfEmpToken) {
		this.ittrConfEmpToken = ittrConfEmpToken;
	}
	public String getIttrConfEmpId() {
		return ittrConfEmpId;
	}
	public void setIttrConfEmpId(String ittrConfEmpId) {
		this.ittrConfEmpId = ittrConfEmpId;
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
	public String getMoveAppraisee() {
		return moveAppraisee;
	}
	public void setMoveAppraisee(String moveAppraisee) {
		this.moveAppraisee = moveAppraisee;
	}
	public String getMoveEmployee() {
		return moveEmployee;
	}
	public void setMoveEmployee(String moveEmployee) {
		this.moveEmployee = moveEmployee;
	}
	public String getDeleteEmployee() {
		return deleteEmployee;
	}
	public void setDeleteEmployee(String deleteEmployee) {
		this.deleteEmployee = deleteEmployee;
	}
	public ArrayList getConfEmployeeList() {
		return confEmployeeList;
	}
	public void setConfEmployeeList(ArrayList confEmployeeList) {
		this.confEmployeeList = confEmployeeList;
	}
	public String getParaIdEmp() {
		return paraIdEmp;
	}
	public void setParaIdEmp(String paraIdEmp) {
		this.paraIdEmp = paraIdEmp;
	}
	public String getCountEmp() {
		return countEmp;
	}
	public void setCountEmp(String countEmp) {
		this.countEmp = countEmp;
	}
	public String getShowGroup() {
		return showGroup;
	}
	public void setShowGroup(String showGroup) {
		this.showGroup = showGroup;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getSrNoItt() {
		return srNoItt;
	}
	public void setSrNoItt(String srNoItt) {
		this.srNoItt = srNoItt;
	}
	 
	public String getModuleFlag() {
		return moduleFlag;
	}
	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}
	public String getGroupFlag() {
		return groupFlag;
	}
	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}
	public String getDefineSelectSource() {
		return defineSelectSource;
	}
	public void setDefineSelectSource(String defineSelectSource) {
		this.defineSelectSource = defineSelectSource;
	}
	public String getEmpTypeFlag() {
		return empTypeFlag;
	}
	public void setEmpTypeFlag(String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	public String getSelectGroupId() {
		return selectGroupId;
	}
	public void setSelectGroupId(String selectGroupId) {
		this.selectGroupId = selectGroupId;
	}
	public String getMoveEmpId() {
		return moveEmpId;
	}
	public void setMoveEmpId(String moveEmpId) {
		this.moveEmpId = moveEmpId;
	}
	public HashMap getHashmapGroup() {
		return hashmapGroup;
	}
	public void setHashmapGroup(HashMap hashmapGroup) {
		this.hashmapGroup = hashmapGroup;
	}
	public String getMoveDivCode() {
		return moveDivCode;
	}
	public void setMoveDivCode(String moveDivCode) {
		this.moveDivCode = moveDivCode;
	}
	public String getSelectExistModule() {
		return selectExistModule;
	}
	public void setSelectExistModule(String selectExistModule) {
		this.selectExistModule = selectExistModule;
	}
	public String getSelectExistModuleAbbr() {
		return selectExistModuleAbbr;
	}
	public void setSelectExistModuleAbbr(String selectExistModuleAbbr) {
		this.selectExistModuleAbbr = selectExistModuleAbbr;
	}
	 
	 
}
