package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

public class HireRehireMisReport extends BeanBase {

	private String backFlag = "";
	
	private String savedReport = "";
	private String reportId = "";
	private String reportTitle = "";
	
	//Tab 1
	private String divName = "";
	private String divId = "";
	private String deptName = "";
	private String deptId = "";
	private String branchName = "";
	private String branchId = "";
	private String empTypeName = "";
	private String empTypeId = "";
	private String desigName = "";
	private String desigId = "";
	private String empToken = "";
	private String empName = "";
	private String empId = "";
	private String status = "";
	
	//Tab 2
	private String empNameFlag = "";
	private String divFlag = "";
	private String deptFlag = "";
	private String branchFlag = "";
	private String desigFlag = "";
	private String empTypeFlag = "";
	private String appliDateFlag = "";
	private String attDateFlag = "";
	private String statusFlag = "";
	private String logInFlag = "";
	private String lateHrsFlag = "";
	private String lateHrsDeductFlag = "";
	
	//Tab 3
	private String sortBy = "";
	private String hiddenSortBy = "";
	private String sortByAsc="checked";
	private String sortByDsc = "";
	private String sortByOrder = "";
	
	private String thenBy1 = "";
	private String hiddenThenBy1 = "";
	private String thenByOrder1Asc="checked";
	private String thenByOrder1Dsc = "";
	private String thenByOrder1 = "";	
	
	private String thenBy2 = "";
	private String hiddenThenBy2 = "";
	private String thenByOrder2Asc="checked";
	private String thenByOrder2Dsc = "";
	private String thenByOrder2 = "";
	
	private String columnOrdering = "";
	private String hiddenColumns = "";
	
	//Tab 4
	private String appliDateSelect = "";
	private String appliFromDate = "";
	private String appliToDate = "";
	
	private String attDateSelect = "";
	private String attFromDate = "";
	private String attToDate = "";
	
	//Display Options
	private String hidReportView="checked";
	private String hidReportRadio = "";
	private String reportType = "";
	
	private String settingName = "";
	
	private String reqStatus = "";

	private String myPage;
	private String show;
	private String noData;
	private String dataLength;
	private String exportAll="";
	
	private String trackingNumber="";
	private String empFirstName="";
	private String actionReason="";
	private String firstNameFlag="";
	private String middleNameFlag="";
	private String lastNameFlag = "";
	private String socialSecurityNumberFlag="";
	private String cityFlag="";
	private String stateFlag="";
	private String phoneNumberFlag="";
	private String sexFlag="";
	private String martialStatusFlag="";
	private String educationFlag = "";
	private String birthDateFlag = "";
	private String referalSourceFlag="";
	private String hireDateFlag="";
	private String physicalWorkLocationFlag="";
	private String actionReasonFlag="";
	private String gradeFlag="";
	private String payGroupFlag="";
	private String annualSalaryFlag="";
	private String executiveEmployeeFlag="";
	private String trackingNumberFlag="";
	private String socialInsuranceNumberFlag="";
	
	
	public String getSocialInsuranceNumberFlag() {
		return socialInsuranceNumberFlag;
	}
	public void setSocialInsuranceNumberFlag(String socialInsuranceNumberFlag) {
		this.socialInsuranceNumberFlag = socialInsuranceNumberFlag;
	}
	public String getCityFlag() {
		return cityFlag;
	}
	public void setCityFlag(String cityFlag) {
		this.cityFlag = cityFlag;
	}
	public String getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	public String getPhoneNumberFlag() {
		return phoneNumberFlag;
	}
	public void setPhoneNumberFlag(String phoneNumberFlag) {
		this.phoneNumberFlag = phoneNumberFlag;
	}
	public String getSexFlag() {
		return sexFlag;
	}
	public void setSexFlag(String sexFlag) {
		this.sexFlag = sexFlag;
	}
	public String getMartialStatusFlag() {
		return martialStatusFlag;
	}
	public void setMartialStatusFlag(String martialStatusFlag) {
		this.martialStatusFlag = martialStatusFlag;
	}
	public String getEducationFlag() {
		return educationFlag;
	}
	public void setEducationFlag(String educationFlag) {
		this.educationFlag = educationFlag;
	}
	public String getBirthDateFlag() {
		return birthDateFlag;
	}
	public void setBirthDateFlag(String birthDateFlag) {
		this.birthDateFlag = birthDateFlag;
	}
	public String getReferalSourceFlag() {
		return referalSourceFlag;
	}
	public void setReferalSourceFlag(String referalSourceFlag) {
		this.referalSourceFlag = referalSourceFlag;
	}
	public String getHireDateFlag() {
		return hireDateFlag;
	}
	public void setHireDateFlag(String hireDateFlag) {
		this.hireDateFlag = hireDateFlag;
	}
	public String getPhysicalWorkLocationFlag() {
		return physicalWorkLocationFlag;
	}
	public void setPhysicalWorkLocationFlag(String physicalWorkLocationFlag) {
		this.physicalWorkLocationFlag = physicalWorkLocationFlag;
	}
	public String getActionReasonFlag() {
		return actionReasonFlag;
	}
	public void setActionReasonFlag(String actionReasonFlag) {
		this.actionReasonFlag = actionReasonFlag;
	}
	public String getGradeFlag() {
		return gradeFlag;
	}
	public void setGradeFlag(String gradeFlag) {
		this.gradeFlag = gradeFlag;
	}
	public String getPayGroupFlag() {
		return payGroupFlag;
	}
	public void setPayGroupFlag(String payGroupFlag) {
		this.payGroupFlag = payGroupFlag;
	}
	public String getAnnualSalaryFlag() {
		return annualSalaryFlag;
	}
	public void setAnnualSalaryFlag(String annualSalaryFlag) {
		this.annualSalaryFlag = annualSalaryFlag;
	}
	public String getExecutiveEmployeeFlag() {
		return executiveEmployeeFlag;
	}
	public void setExecutiveEmployeeFlag(String executiveEmployeeFlag) {
		this.executiveEmployeeFlag = executiveEmployeeFlag;
	}
	public String getTrackingNumberFlag() {
		return trackingNumberFlag;
	}
	public void setTrackingNumberFlag(String trackingNumberFlag) {
		this.trackingNumberFlag = trackingNumberFlag;
	}
	public String getFirstNameFlag() {
		return firstNameFlag;
	}
	public void setFirstNameFlag(String firstNameFlag) {
		this.firstNameFlag = firstNameFlag;
	}
	public String getMiddleNameFlag() {
		return middleNameFlag;
	}
	public void setMiddleNameFlag(String middleNameFlag) {
		this.middleNameFlag = middleNameFlag;
	}
	public String getLastNameFlag() {
		return lastNameFlag;
	}
	public void setLastNameFlag(String lastNameFlag) {
		this.lastNameFlag = lastNameFlag;
	}
	public String getSocialSecurityNumberFlag() {
		return socialSecurityNumberFlag;
	}
	public void setSocialSecurityNumberFlag(String socialSecurityNumberFlag) {
		this.socialSecurityNumberFlag = socialSecurityNumberFlag;
	}
	public String getActionReason() {
		return actionReason;
	}
	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getSavedReport() {
		return savedReport;
	}
	public void setSavedReport(String savedReport) {
		this.savedReport = savedReport;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
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
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getDesigId() {
		return desigId;
	}
	public void setDesigId(String desigId) {
		this.desigId = desigId;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNameFlag() {
		return empNameFlag;
	}
	public void setEmpNameFlag(String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}
	public String getDivFlag() {
		return divFlag;
	}
	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public String getDesigFlag() {
		return desigFlag;
	}
	public void setDesigFlag(String desigFlag) {
		this.desigFlag = desigFlag;
	}
	public String getEmpTypeFlag() {
		return empTypeFlag;
	}
	public void setEmpTypeFlag(String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	public String getAppliDateFlag() {
		return appliDateFlag;
	}
	public void setAppliDateFlag(String appliDateFlag) {
		this.appliDateFlag = appliDateFlag;
	}
	public String getAttDateFlag() {
		return attDateFlag;
	}
	public void setAttDateFlag(String attDateFlag) {
		this.attDateFlag = attDateFlag;
	}
	public String getLogInFlag() {
		return logInFlag;
	}
	public void setLogInFlag(String logInFlag) {
		this.logInFlag = logInFlag;
	}
	public String getLateHrsFlag() {
		return lateHrsFlag;
	}
	public void setLateHrsFlag(String lateHrsFlag) {
		this.lateHrsFlag = lateHrsFlag;
	}
	public String getLateHrsDeductFlag() {
		return lateHrsDeductFlag;
	}
	public void setLateHrsDeductFlag(String lateHrsDeductFlag) {
		this.lateHrsDeductFlag = lateHrsDeductFlag;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getHiddenSortBy() {
		return hiddenSortBy;
	}
	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}
	public String getSortByAsc() {
		return sortByAsc;
	}
	public void setSortByAsc(String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}
	public String getSortByDsc() {
		return sortByDsc;
	}
	public void setSortByDsc(String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}
	public String getSortByOrder() {
		return sortByOrder;
	}
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}
	public String getThenBy1() {
		return thenBy1;
	}
	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}
	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}
	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}
	public String getThenByOrder1Asc() {
		return thenByOrder1Asc;
	}
	public void setThenByOrder1Asc(String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}
	public String getThenByOrder1Dsc() {
		return thenByOrder1Dsc;
	}
	public void setThenByOrder1Dsc(String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}
	public String getThenByOrder1() {
		return thenByOrder1;
	}
	public void setThenByOrder1(String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}
	public String getThenBy2() {
		return thenBy2;
	}
	public void setThenBy2(String thenBy2) {
		this.thenBy2 = thenBy2;
	}
	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}
	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}
	public String getThenByOrder2Asc() {
		return thenByOrder2Asc;
	}
	public void setThenByOrder2Asc(String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}
	public String getThenByOrder2Dsc() {
		return thenByOrder2Dsc;
	}
	public void setThenByOrder2Dsc(String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}
	public String getThenByOrder2() {
		return thenByOrder2;
	}
	public void setThenByOrder2(String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}
	public String getColumnOrdering() {
		return columnOrdering;
	}
	public void setColumnOrdering(String columnOrdering) {
		this.columnOrdering = columnOrdering;
	}
	public String getHiddenColumns() {
		return hiddenColumns;
	}
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}
	public String getAppliDateSelect() {
		return appliDateSelect;
	}
	public void setAppliDateSelect(String appliDateSelect) {
		this.appliDateSelect = appliDateSelect;
	}
	public String getAppliFromDate() {
		return appliFromDate;
	}
	public void setAppliFromDate(String appliFromDate) {
		this.appliFromDate = appliFromDate;
	}
	public String getAppliToDate() {
		return appliToDate;
	}
	public void setAppliToDate(String appliToDate) {
		this.appliToDate = appliToDate;
	}
	public String getAttDateSelect() {
		return attDateSelect;
	}
	public void setAttDateSelect(String attDateSelect) {
		this.attDateSelect = attDateSelect;
	}
	public String getAttFromDate() {
		return attFromDate;
	}
	public void setAttFromDate(String attFromDate) {
		this.attFromDate = attFromDate;
	}
	public String getAttToDate() {
		return attToDate;
	}
	public void setAttToDate(String attToDate) {
		this.attToDate = attToDate;
	}
	public String getHidReportView() {
		return hidReportView;
	}
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}
	public String getHidReportRadio() {
		return hidReportRadio;
	}
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
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
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	
}
