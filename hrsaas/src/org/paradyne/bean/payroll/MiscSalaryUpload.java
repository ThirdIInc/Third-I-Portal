package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class MiscSalaryUpload extends BeanBase {
	
	private String earningCompId = "";
	private String earningCompName = "";
	private String deductionCompId= "";
	private String deductionCompName= "";
	private String divisionId = "";
	private String divisionName = "";
	private String divisionAbbrevation = "";
	private String branchId = "";
	private String branchName = "";
	private String paybillId = "";
	private String paybillName = "";
	private String gradeId = "";
	private String gradeName = "";
	private String departmentId = "";
	private String departmentName = "";
	private String empId = "";
	private String empName = "";
	private String overwriteChk = "";
	private String appendChk = "";
	private String year = "";
	private String month = "";
	private String dataPath = "";
	private String note = "";
	private String status = "Fail";
	private String uploadName;
	private String uploadFileName = "";
	private String uploadedFile = "";
	private boolean displayNoteFlag = false;
	private String reportType = "";
	
	/*Manager authorization related variables*/
	private String managerCode = "";
	private String salaryAuthorizationCode = "";
	private String salaryAuthorizationType = "";
	private String managerDivCode = "";
	private String managerPaybillCode = "";
	private String managerBranchCode = "";
	private String managerDebitCode = "";
	private String managerCreditCode = "";
	
	/* Statistics related variables*/
	private boolean showStatisticsFlag;
	private ArrayList creditList =null ; 
	private String creditNameItt = "";
	private String creditCountItt = "";
	private String creditAmountItt = "";

	private ArrayList debitList =null ; 
	private String debitNameItt = "";
	private String debitCountItt = "";
	private String debitAmountItt = "";
	public String linkSource="";
	public String monthView="";
	
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	public String getLinkSource() {
		return linkSource;
	}
	public void setLinkSource(String linkSource) {
		this.linkSource = linkSource;
	}
	public boolean isDisplayNoteFlag() {
		return displayNoteFlag;
	}
	public void setDisplayNoteFlag(boolean displayNoteFlag) {
		this.displayNoteFlag = displayNoteFlag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEarningCompId() {
		return earningCompId;
	}
	public void setEarningCompId(String earningCompId) {
		this.earningCompId = earningCompId;
	}
	public String getEarningCompName() {
		return earningCompName;
	}
	public void setEarningCompName(String earningCompName) {
		this.earningCompName = earningCompName;
	}
	public String getDeductionCompId() {
		return deductionCompId;
	}
	public void setDeductionCompId(String deductionCompId) {
		this.deductionCompId = deductionCompId;
	}
	public String getDeductionCompName() {
		return deductionCompName;
	}
	public void setDeductionCompName(String deductionCompName) {
		this.deductionCompName = deductionCompName;
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
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getOverwriteChk() {
		return overwriteChk;
	}
	public void setOverwriteChk(String overwriteChk) {
		this.overwriteChk = overwriteChk;
	}
	public String getAppendChk() {
		return appendChk;
	}
	public void setAppendChk(String appendChk) {
		this.appendChk = appendChk;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getSalaryAuthorizationCode() {
		return salaryAuthorizationCode;
	}
	public void setSalaryAuthorizationCode(String salaryAuthorizationCode) {
		this.salaryAuthorizationCode = salaryAuthorizationCode;
	}
	public String getSalaryAuthorizationType() {
		return salaryAuthorizationType;
	}
	public void setSalaryAuthorizationType(String salaryAuthorizationType) {
		this.salaryAuthorizationType = salaryAuthorizationType;
	}
	public String getManagerDivCode() {
		return managerDivCode;
	}
	public void setManagerDivCode(String managerDivCode) {
		this.managerDivCode = managerDivCode;
	}
	public String getManagerPaybillCode() {
		return managerPaybillCode;
	}
	public void setManagerPaybillCode(String managerPaybillCode) {
		this.managerPaybillCode = managerPaybillCode;
	}
	public String getManagerBranchCode() {
		return managerBranchCode;
	}
	public void setManagerBranchCode(String managerBranchCode) {
		this.managerBranchCode = managerBranchCode;
	}
	public String getManagerDebitCode() {
		return managerDebitCode;
	}
	public void setManagerDebitCode(String managerDebitCode) {
		this.managerDebitCode = managerDebitCode;
	}
	public String getManagerCreditCode() {
		return managerCreditCode;
	}
	public void setManagerCreditCode(String managerCreditCode) {
		this.managerCreditCode = managerCreditCode;
	}
	public ArrayList getCreditList() {
		return creditList;
	}
	public void setCreditList(ArrayList creditList) {
		this.creditList = creditList;
	}
	public String getCreditNameItt() {
		return creditNameItt;
	}
	public void setCreditNameItt(String creditNameItt) {
		this.creditNameItt = creditNameItt;
	}
	public String getCreditCountItt() {
		return creditCountItt;
	}
	public void setCreditCountItt(String creditCountItt) {
		this.creditCountItt = creditCountItt;
	}
	public String getCreditAmountItt() {
		return creditAmountItt;
	}
	public void setCreditAmountItt(String creditAmountItt) {
		this.creditAmountItt = creditAmountItt;
	}
	public ArrayList getDebitList() {
		return debitList;
	}
	public void setDebitList(ArrayList debitList) {
		this.debitList = debitList;
	}
	public String getDebitNameItt() {
		return debitNameItt;
	}
	public void setDebitNameItt(String debitNameItt) {
		this.debitNameItt = debitNameItt;
	}
	public String getDebitCountItt() {
		return debitCountItt;
	}
	public void setDebitCountItt(String debitCountItt) {
		this.debitCountItt = debitCountItt;
	}
	public String getDebitAmountItt() {
		return debitAmountItt;
	}
	public void setDebitAmountItt(String debitAmountItt) {
		this.debitAmountItt = debitAmountItt;
	}
	public boolean isShowStatisticsFlag() {
		return showStatisticsFlag;
	}
	public void setShowStatisticsFlag(boolean showStatisticsFlag) {
		this.showStatisticsFlag = showStatisticsFlag;
	}
	public String getDivisionAbbrevation() {
		return divisionAbbrevation;
	}
	public void setDivisionAbbrevation(String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

}
