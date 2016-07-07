package org.paradyne.bean.probation;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProbationConfirmation extends BeanBase {

	private String empeeCode = "";
	private String empeeName = "";
	private String issueflag="";
	
	private String selectemployeetoken="";
	private String selectemployeeName="";
	private String probCode="";
	private String stat="";
	
	private String searchStatus ="";
	private String deptId = "";
	private String deptName = "";
	private String branchCode = "";
	private String branchName = "";
	private String evalCodeItt = "";
	private String empToken = "";
	private String empName = "";
	private String employeeToken = "";
	private String employeeName = "";
	private String empId = "";
	private String dateOfJoining = "";
	private String employeeId = "";
	private String currentBranchCode = "";
	private String currentBranch = "";
	private String newBranchCode = "";
	private String newBranch = "";
	private String currentDepartmentCode = "";
	private String currentDepartment = "";
	private String newDepartmentCode = "";
	private String newDepartment = "";
	private String currentEmployeeTypeCode = "";
	private String currentEmployeeType = "";
	private String newEmployeeTypeCode = "";
	private String newEmployeeType = "";

	private String currentGrade = "";
	private String newGrade = "";
	private String currentGradeCode = "";
	private String newGradeCode = "";

	private String currentDesignationCode = "";
	private String currentDesignation = "";
	private String newDesignationCode = "";
	private String newDesignation = "";
	private String currentdivisionCode = "";
	private String currentdivision = "";
	private String newdivisionCode = "";
	private String newdivision = "";
	private String empDataflag = "false";
	private String confirmEmpflag = "true";
	private String empCode = "";
	private String extendedProbationDays = "";
	private String confirmDate = "";
	private String terminationDate = "";
	ArrayList list = null;
	private String status = "";

	private String dateOfConfirm = "";
	private String extendedProbationDate = "";
	private String confirmFlag = "false";
	private String extenProbFlag = "false";
	private String noData = "false";
	private String terminatedFlag = "false";
	private String dateOfTermination = "";

	private String myPage;
	private String show;
	private String selectname;

	private String comments = "";

	private String probationCode = "";

	private String ittprobationCode = "";
	private String branch = "";
	private String department = "";
	private String evalstatus = "";

	private String clBal = "";
	private String onholdBal = "";
	private String leaveCode = "";
	private String leaveName = "";

	ArrayList leaveList = null;

	private String entitleFlag = "false";

	private String policyName = "";
	private String policyCode = "";

	private String balTableFlag = "false";

	private String goFlag = "true";

	private String pageFlag = "true";

	private String ittprobationStatus = "";

	private String confirmDueFlag = "false";

	private String confirmDueDate = "";

	/**
	 * FOR GENERATE CONFIRMATION LETTER
	 */
	private String templateCode = "";
	private String templateName = "";
	private String authoCode = "";
	private String authoName = "";

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

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public ArrayList getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}

	public String getIttprobationCode() {
		return ittprobationCode;
	}

	public void setIttprobationCode(String ittprobationCode) {
		this.ittprobationCode = ittprobationCode;
	}

	public String getProbationCode() {
		return probationCode;
	}

	public void setProbationCode(String probationCode) {
		this.probationCode = probationCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getSelectname() {
		return selectname;
	}

	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentDesignationCode() {
		return currentDesignationCode;
	}

	public void setCurrentDesignationCode(String currentDesignationCode) {
		this.currentDesignationCode = currentDesignationCode;
	}

	public String getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public String getNewDesignationCode() {
		return newDesignationCode;
	}

	public void setNewDesignationCode(String newDesignationCode) {
		this.newDesignationCode = newDesignationCode;
	}

	public String getNewDesignation() {
		return newDesignation;
	}

	public void setNewDesignation(String newDesignation) {
		this.newDesignation = newDesignation;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
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

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCurrentBranchCode() {
		return currentBranchCode;
	}

	public void setCurrentBranchCode(String currentBranchCode) {
		this.currentBranchCode = currentBranchCode;
	}

	public String getCurrentBranch() {
		return currentBranch;
	}

	public void setCurrentBranch(String currentBranch) {
		this.currentBranch = currentBranch;
	}

	public String getNewBranchCode() {
		return newBranchCode;
	}

	public void setNewBranchCode(String newBranchCode) {
		this.newBranchCode = newBranchCode;
	}

	public String getNewBranch() {
		return newBranch;
	}

	public void setNewBranch(String newBranch) {
		this.newBranch = newBranch;
	}

	public String getCurrentDepartmentCode() {
		return currentDepartmentCode;
	}

	public void setCurrentDepartmentCode(String currentDepartmentCode) {
		this.currentDepartmentCode = currentDepartmentCode;
	}

	public String getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public String getNewDepartmentCode() {
		return newDepartmentCode;
	}

	public void setNewDepartmentCode(String newDepartmentCode) {
		this.newDepartmentCode = newDepartmentCode;
	}

	public String getNewDepartment() {
		return newDepartment;
	}

	public void setNewDepartment(String newDepartment) {
		this.newDepartment = newDepartment;
	}

	public String getCurrentEmployeeTypeCode() {
		return currentEmployeeTypeCode;
	}

	public void setCurrentEmployeeTypeCode(String currentEmployeeTypeCode) {
		this.currentEmployeeTypeCode = currentEmployeeTypeCode;
	}

	public String getCurrentEmployeeType() {
		return currentEmployeeType;
	}

	public void setCurrentEmployeeType(String currentEmployeeType) {
		this.currentEmployeeType = currentEmployeeType;
	}

	public String getNewEmployeeTypeCode() {
		return newEmployeeTypeCode;
	}

	public void setNewEmployeeTypeCode(String newEmployeeTypeCode) {
		this.newEmployeeTypeCode = newEmployeeTypeCode;
	}

	public String getNewEmployeeType() {
		return newEmployeeType;
	}

	public void setNewEmployeeType(String newEmployeeType) {
		this.newEmployeeType = newEmployeeType;
	}

	public String getCurrentdivisionCode() {
		return currentdivisionCode;
	}

	public void setCurrentdivisionCode(String currentdivisionCode) {
		this.currentdivisionCode = currentdivisionCode;
	}

	public String getCurrentdivision() {
		return currentdivision;
	}

	public void setCurrentdivision(String currentdivision) {
		this.currentdivision = currentdivision;
	}

	public String getNewdivisionCode() {
		return newdivisionCode;
	}

	public void setNewdivisionCode(String newdivisionCode) {
		this.newdivisionCode = newdivisionCode;
	}

	public String getNewdivision() {
		return newdivision;
	}

	public void setNewdivision(String newdivision) {
		this.newdivision = newdivision;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpDataflag() {
		return empDataflag;
	}

	public void setEmpDataflag(String empDataflag) {
		this.empDataflag = empDataflag;
	}

	public String getConfirmEmpflag() {
		return confirmEmpflag;
	}

	public void setConfirmEmpflag(String confirmEmpflag) {
		this.confirmEmpflag = confirmEmpflag;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getExtendedProbationDays() {
		return extendedProbationDays;
	}

	public void setExtendedProbationDays(String extendedProbationDays) {
		this.extendedProbationDays = extendedProbationDays;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getDateOfConfirm() {
		return dateOfConfirm;
	}

	public void setDateOfConfirm(String dateOfConfirm) {
		this.dateOfConfirm = dateOfConfirm;
	}

	public String getExtendedProbationDate() {
		return extendedProbationDate;
	}

	public void setExtendedProbationDate(String extendedProbationDate) {
		this.extendedProbationDate = extendedProbationDate;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getExtenProbFlag() {
		return extenProbFlag;
	}

	public void setExtenProbFlag(String extenProbFlag) {
		this.extenProbFlag = extenProbFlag;
	}

	public String getTerminatedFlag() {
		return terminatedFlag;
	}

	public void setTerminatedFlag(String terminatedFlag) {
		this.terminatedFlag = terminatedFlag;
	}

	public String getDateOfTermination() {
		return dateOfTermination;
	}

	public void setDateOfTermination(String dateOfTermination) {
		this.dateOfTermination = dateOfTermination;
	}

	public String getClBal() {
		return clBal;
	}

	public void setClBal(String clBal) {
		this.clBal = clBal;
	}

	public String getOnholdBal() {
		return onholdBal;
	}

	public void setOnholdBal(String onholdBal) {
		this.onholdBal = onholdBal;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getEntitleFlag() {
		return entitleFlag;
	}

	public void setEntitleFlag(String entitleFlag) {
		this.entitleFlag = entitleFlag;
	}

	public String getBalTableFlag() {
		return balTableFlag;
	}

	public void setBalTableFlag(String balTableFlag) {
		this.balTableFlag = balTableFlag;
	}

	public String getGoFlag() {
		return goFlag;
	}

	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public String getIttprobationStatus() {
		return ittprobationStatus;
	}

	public void setIttprobationStatus(String ittprobationStatus) {
		this.ittprobationStatus = ittprobationStatus;
	}

	public String getConfirmDueFlag() {
		return confirmDueFlag;
	}

	public void setConfirmDueFlag(String confirmDueFlag) {
		this.confirmDueFlag = confirmDueFlag;
	}

	public String getConfirmDueDate() {
		return confirmDueDate;
	}

	public void setConfirmDueDate(String confirmDueDate) {
		this.confirmDueDate = confirmDueDate;
	}

	public String getAuthoCode() {
		return authoCode;
	}

	public void setAuthoCode(String authoCode) {
		this.authoCode = authoCode;
	}

	public String getAuthoName() {
		return authoName;
	}

	public void setAuthoName(String authoName) {
		this.authoName = authoName;
	}

	public String getCurrentGrade() {
		return currentGrade;
	}

	public void setCurrentGrade(String currentGrade) {
		this.currentGrade = currentGrade;
	}

	public String getNewGrade() {
		return newGrade;
	}

	public void setNewGrade(String newGrade) {
		this.newGrade = newGrade;
	}

	public String getCurrentGradeCode() {
		return currentGradeCode;
	}

	public void setCurrentGradeCode(String currentGradeCode) {
		this.currentGradeCode = currentGradeCode;
	}

	public String getNewGradeCode() {
		return newGradeCode;
	}

	public void setNewGradeCode(String newGradeCode) {
		this.newGradeCode = newGradeCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEvalstatus() {
		return evalstatus;
	}

	public void setEvalstatus(String evalstatus) {
		this.evalstatus = evalstatus;
	}

	public String getEvalCodeItt() {
		return evalCodeItt;
	}

	public void setEvalCodeItt(String evalCodeItt) {
		this.evalCodeItt = evalCodeItt;
	}

	public String getEmpeeCode() {
		return empeeCode;
	}

	public void setEmpeeCode(String empeeCode) {
		this.empeeCode = empeeCode;
	}

	public String getEmpeeName() {
		return empeeName;
	}

	public void setEmpeeName(String empeeName) {
		this.empeeName = empeeName;
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

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getIssueflag() {
		return issueflag;
	}

	public void setIssueflag(String issueflag) {
		this.issueflag = issueflag;
	}

	public String getSelectemployeetoken() {
		return selectemployeetoken;
	}

	public void setSelectemployeetoken(String selectemployeetoken) {
		this.selectemployeetoken = selectemployeetoken;
	}

	public String getSelectemployeeName() {
		return selectemployeeName;
	}

	public void setSelectemployeeName(String selectemployeeName) {
		this.selectemployeeName = selectemployeeName;
	}

	public String getProbCode() {
		return probCode;
	}

	public void setProbCode(String probCode) {
		this.probCode = probCode;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
