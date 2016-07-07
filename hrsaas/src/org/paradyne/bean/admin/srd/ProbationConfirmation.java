package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProbationConfirmation extends BeanBase {

	
	private String empToken="";
	private String empName="";
	private String employeeToken="";
	private String employeeName="";
	private String empId="";
	private String dateOfJoining="";
	private String employeeId="";
	private String currentBranchCode="";
	private String currentBranch="";
	private String newBranchCode="";
	private String newBranch="";
	private String currentDepartmentCode="";
	private String currentDepartment="";
	private String newDepartmentCode="";
	private String newDepartment="";
	private String currentEmployeeTypeCode="";
	private String currentEmployeeType="";
	private String newEmployeeTypeCode="";
	private String newEmployeeType="";
	private String currentDesignationCode="";
	private String currentDesignation="";
	private String newDesignationCode="";
	private String newDesignation="";
	private String currentdivisionCode="";
	private String currentdivision="";
	private String newdivisionCode="";
	private String newdivision="";
	private String empDataflag="false";
	private String confirmEmpflag="true";
	private String empCode="";
	private String extendedProbationDays="";
	private String confirmDate="";
	private String terminationDate="";
	ArrayList list =null;
	
	
	
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
	
}
