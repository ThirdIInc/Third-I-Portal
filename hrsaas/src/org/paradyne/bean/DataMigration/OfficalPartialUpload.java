
package org.paradyne.bean.DataMigration;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author aa0491 Vishwambhar Deshpande
 *
 */

public class OfficalPartialUpload extends BeanBase {
				 
				private String divName ="";
				private String divCode ="";
				private String empType ="";
				private String empTypeCode =""; 
				private String deptName=""; 
				private String deptCode ="";  
				private String branchName ="";
				private String	branchCode ="";
				private String gradeName ="";
				private String gradeCode ="";
				private String desgName ="";
				private String desgCode ="";
			 	private String firstName="";
				private String middleName="";
				private String lastName="";
				private String division="";
				private String branch="";
				private String department="";
				private String designation="";
				private String employeeTitle="";
				private String employeeType="";
				private String reportingTo="";
				private String shift="";
				private String paybill="";
				private String gender="";
				private String birthDate="";
				private String grade="";
				private String joiningDate="";
				private String leaving="";
				private String status="";
				private String dataPath="";
				private boolean fileUploaded=false;	
				private String uploadFileName="";
				private boolean dataExists=false;
				private String note="";
				private String groupJoinDateCheck ="";
				private String reportingto="";
				private String dateofconfirm="";
				private String trade="";
				private String role="";
				
				public String getTrade() {
					return trade;
				}
				public void setTrade(String trade) {
					this.trade = trade;
				}
				public String getRole() {
					return role;
				}
				public void setRole(String role) {
					this.role = role;
				}
				public String getReportingto() {
					return reportingto;
				}
				public void setReportingto(String reportingto) {
					this.reportingto = reportingto;
				}
				public String getDateofconfirm() {
					return dateofconfirm;
				}
				public void setDateofconfirm(String dateofconfirm) {
					this.dateofconfirm = dateofconfirm;
				}
				public String getGroupJoinDateCheck() {
					return groupJoinDateCheck;
				}
				public void setGroupJoinDateCheck(String groupJoinDateCheck) {
					this.groupJoinDateCheck = groupJoinDateCheck;
				}
				public String getNote() {
					return note;
				}
				public void setNote(String note) {
					this.note = note;
				}
				public String getDataPath() {
					return dataPath;
				}
				public void setDataPath(String dataPath) {
					this.dataPath = dataPath;
				}
				public String getFirstName() {
					return firstName;
				}
				public void setFirstName(String firstName) {
					this.firstName = firstName;
				}
				public String getMiddleName() {
					return middleName;
				}
				public void setMiddleName(String middleName) {
					this.middleName = middleName;
				}
				public String getLastName() {
					return lastName;
				}
				public void setLastName(String lastName) {
					this.lastName = lastName;
				}
				public String getDivision() {
					return division;
				}
				public void setDivision(String division) {
					this.division = division;
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
				public String getDesignation() {
					return designation;
				}
				public void setDesignation(String designation) {
					this.designation = designation;
				}
				public String getEmployeeTitle() {
					return employeeTitle;
				}
				public void setEmployeeTitle(String employeeTitle) {
					this.employeeTitle = employeeTitle;
				}
				public String getEmployeeType() {
					return employeeType;
				}
				public void setEmployeeType(String employeeType) {
					this.employeeType = employeeType;
				}
				public String getReportingTo() {
					return reportingTo;
				}
				public void setReportingTo(String reportingTo) {
					this.reportingTo = reportingTo;
				}
				public String getShift() {
					return shift;
				}
				public void setShift(String shift) {
					this.shift = shift;
				}
				 
				public String getPaybill() {
					return paybill;
				}
				public void setPaybill(String paybill) {
					this.paybill = paybill;
				}
				public String getGender() {
					return gender;
				}
				public void setGender(String gender) {
					this.gender = gender;
				}
				public String getBirthDate() {
					return birthDate;
				}
				public void setBirthDate(String birthDate) {
					this.birthDate = birthDate;
				}
				public String getGrade() {
					return grade;
				}
				public void setGrade(String grade) {
					this.grade = grade;
				}
				public String getJoiningDate() {
					return joiningDate;
				}
				public void setJoiningDate(String joiningDate) {
					this.joiningDate = joiningDate;
				}
				public String getLeaving() {
					return leaving;
				}
				public void setLeaving(String leaving) {
					this.leaving = leaving;
				}
				public String getStatus() {
					return status;
				}
				public void setStatus(String status) {
					this.status = status;
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
				public String getBranchName() {
					return branchName;
				}
				public void setBranchName(String branchName) {
					this.branchName = branchName;
				}
				public String getBranchCode() {
					return branchCode;
				}
				public void setBranchCode(String branchCode) {
					this.branchCode = branchCode;
				}
				public String getGradeName() {
					return gradeName;
				}
				public void setGradeName(String gradeName) {
					this.gradeName = gradeName;
				}
				public String getGradeCode() {
					return gradeCode;
				}
				public void setGradeCode(String gradeCode) {
					this.gradeCode = gradeCode;
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
				public boolean isFileUploaded() {
					return fileUploaded;
				}
				public void setFileUploaded(boolean fileUploaded) {
					this.fileUploaded = fileUploaded;
				}
				public String getUploadFileName() {
					return uploadFileName;
				}
				public void setUploadFileName(String uploadFileName) {
					this.uploadFileName = uploadFileName;
				}
				public boolean isDataExists() {
					return dataExists;
				}
				public void setDataExists(boolean dataExists) {
					this.dataExists = dataExists;
				}
				 
			 
}
