package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

public class UploadSalary extends BeanBase {
	
	/**
	 * dataPath
	 */
	private String dataPath = "";
	/**
	 * earningCompId
	 */
	private String earningCompId = "";
	/**
	 * earningCompName
	 */
	private String earningCompName = "";
	/**
	 * deductionCompId
	 */
	private String deductionCompId= "";
	/**
	 * deductionCompName
	 */
	private String deductionCompName= "";
	/**
	 * divisionId
	 */
	private String divisionId = "";
	/**
	 * divisionName
	 */
	private String divisionName = "";
	/**
	 * divisionAbbrevation
	 */
	private String divisionAbbrevation = "";
	/**
	 * branchId
	 */
	private String branchId = "";
	/**
	 * branchName
	 */
	private String branchName = "";
	/**
	 * paybillId
	 */
	private String paybillId = "";
	/**
	 * paybillName
	 */
	private String paybillName = "";
	/**
	 * gradeId
	 */
	private String gradeId = "";
	/**
	 * gradeName
	 */
	private String gradeName = "";
	/**
	 * departmentId
	 */
	private String departmentId = "";
	/**
	 * departmentName
	 */
	private String departmentName = "";
	/**
	 * empId
	 */
	private String empId = "";
	/**
	 * empName
	 */
	private String empName = "";
	/**
	 * year
	 */
	private String year = "";
	/**
	 * month
	 */
	private String month = "";
	/**
	 * note
	 */
	private String note = "";
	/**
	 * status
	 */
	private String status = "Fail";
	/**
	 * uploadName
	 */
	private String uploadName;
	/**
	 * uploadFileName
	 */
	private String uploadFileName = "";
	/**
	 * uploadedFile
	 */
	private String uploadedFile = "";
	/**
	 * displayNoteFlag
	 */
	private boolean displayNoteFlag = false;
	/**
	 * reportType
	 */
	private String reportType = "";
	/**
	 * @return the dataPath
	 */
	public final String getDataPath() {
		return this.dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public final void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the earningCompId
	 */
	public final String getEarningCompId() {
		return this.earningCompId;
	}
	/**
	 * @param earningCompId the earningCompId to set
	 */
	public final void setEarningCompId(final String earningCompId) {
		this.earningCompId = earningCompId;
	}
	/**
	 * @return the earningCompName
	 */
	public final String getEarningCompName() {
		return this.earningCompName;
	}
	/**
	 * @param earningCompName the earningCompName to set
	 */
	public final void setEarningCompName(final String earningCompName) {
		this.earningCompName = earningCompName;
	}
	/**
	 * @return the deductionCompId
	 */
	public final String getDeductionCompId() {
		return this.deductionCompId;
	}
	/**
	 * @param deductionCompId the deductionCompId to set
	 */
	public final void setDeductionCompId(final String deductionCompId) {
		this.deductionCompId = deductionCompId;
	}
	/**
	 * @return the deductionCompName
	 */
	public final String getDeductionCompName() {
		return this.deductionCompName;
	}
	/**
	 * @param deductionCompName the deductionCompName to set
	 */
	public final void setDeductionCompName(final String deductionCompName) {
		this.deductionCompName = deductionCompName;
	}
	/**
	 * @return the divisionId
	 */
	public final String getDivisionId() {
		return this.divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public final void setDivisionId(final String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the divisionName
	 */
	public final String getDivisionName() {
		return this.divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public final void setDivisionName(final String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the divisionAbbrevation
	 */
	public final String getDivisionAbbrevation() {
		return this.divisionAbbrevation;
	}
	/**
	 * @param divisionAbbrevation the divisionAbbrevation to set
	 */
	public final void setDivisionAbbrevation(final String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
	}
	/**
	 * @return the branchId
	 */
	public final String getBranchId() {
		return this.branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public final void setBranchId(final String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public final String getBranchName() {
		return this.branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public final void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the paybillId
	 */
	public final String getPaybillId() {
		return this.paybillId;
	}
	/**
	 * @param paybillId the paybillId to set
	 */
	public final void setPaybillId(final String paybillId) {
		this.paybillId = paybillId;
	}
	/**
	 * @return the paybillName
	 */
	public final String getPaybillName() {
		return this.paybillName;
	}
	/**
	 * @param paybillName the paybillName to set
	 */
	public final void setPaybillName(final String paybillName) {
		this.paybillName = paybillName;
	}
	/**
	 * @return the gradeId
	 */
	public final String getGradeId() {
		return this.gradeId;
	}
	/**
	 * @param gradeId the gradeId to set
	 */
	public final void setGradeId(final String gradeId) {
		this.gradeId = gradeId;
	}
	/**
	 * @return the gradeName
	 */
	public final String getGradeName() {
		return this.gradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public final void setGradeName(final String gradeName) {
		this.gradeName = gradeName;
	}
	/**
	 * @return the departmentId
	 */
	public final String getDepartmentId() {
		return this.departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public final void setDepartmentId(final String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the departmentName
	 */
	public final String getDepartmentName() {
		return this.departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public final void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the empId
	 */
	public final String getEmpId() {
		return this.empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public final void setEmpId(final String empId) {
		this.empId = empId;
	}
	/**
	 * @return the empName
	 */
	public final String getEmpName() {
		return this.empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public final void setEmpName(final String empName) {
		this.empName = empName;
	}
	/**
	 * @return the year
	 */
	public final String getYear() {
		return this.year;
	}
	/**
	 * @param year the year to set
	 */
	public final void setYear(final String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public final String getMonth() {
		return this.month;
	}
	/**
	 * @param month the month to set
	 */
	public final void setMonth(final String month) {
		this.month = month;
	}
	/**
	 * @return the note
	 */
	public final String getNote() {
		return this.note;
	}
	/**
	 * @param note the note to set
	 */
	public final void setNote(final String note) {
		this.note = note;
	}
	/**
	 * @return the status
	 */
	public final String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public final void setStatus(final String status) {
		this.status = status;
	}
	/**
	 * @return the uploadName
	 */
	public final String getUploadName() {
		return this.uploadName;
	}
	/**
	 * @param uploadName the uploadName to set
	 */
	public final void setUploadName(final String uploadName) {
		this.uploadName = uploadName;
	}
	/**
	 * @return the uploadFileName
	 */
	public final String getUploadFileName() {
		return this.uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public final void setUploadFileName(final String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the uploadedFile
	 */
	public final String getUploadedFile() {
		return this.uploadedFile;
	}
	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public final void setUploadedFile(final String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	/**
	 * @return the displayNoteFlag
	 */
	public final boolean isDisplayNoteFlag() {
		return this.displayNoteFlag;
	}
	/**
	 * @param displayNoteFlag the displayNoteFlag to set
	 */
	public final void setDisplayNoteFlag(final boolean displayNoteFlag) {
		this.displayNoteFlag = displayNoteFlag;
	}
	/**
	 * @return the reportType
	 */
	public final String getReportType() {
		return this.reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public final void setReportType(final String reportType) {
		this.reportType = reportType;
	}
	
}