package org.paradyne.bean.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.lib.BeanBase;

public class TrvlAdminReport extends BeanBase {

	private String backFlag = "";

	private String savedReport = "";
	private String reportId = "";
	private String reportTitle = "";

	// Tab1
	private String finanYear = "";
	private LinkedMap finanYearMap;
	private String gradeId = "";
	private String gradeName = "";
	private String trvlType = "";
	private LinkedMap trvlTypeMap;
	private String appliFor = "";
	private String travelCheck = "";
	private String trvlRadio = "";
	private String hidTravelSelf = "";
	private String hidTravelComp = "checked";
	private String accomCheck = "";
	private String accomRadio = "";
	private String hidAccomSelf = "";
	private String hidAccomComp = "checked";
	private String localCheck = "";
	private String localRadio = "";
	private String hidLocalSelf = "";
	private String hidLocalComp = "checked";

	// Tab2
	private String empIdFlag = "";
	private String appliNameFlag = "";
	private String gradeFlag = "";
	private String appliDateFlag = "";
	private String travelIdFlag = "";
	private String startDateFlag = "";
	private String endDateFlag = "";
	private String purposeFlag = "";
	private String typeFlag = "";
	private String apprvdAmtFlag = "";
	private String advanceAmtFlag = "";
	private String travelCostFlag = "";
	private String accomCostFlag = "";
	private String localCostFlag = "";

	// Tab3
	private String sortBy = "";
	private String hiddenSortBy = "";
	private String sortByAsc = "checked";
	private String sortByDsc = "";
	private String sortByOrder = "";

	private String thenBy1 = "";
	private String hiddenThenBy1 = "";
	private String thenByOrder1Asc = "checked";
	private String thenByOrder1Dsc = "";
	private String thenByOrder1 = "";

	private String thenBy2 = "";
	private String hiddenThenBy2 = "";
	private String thenByOrder2Asc = "checked";
	private String thenByOrder2Dsc = "";
	private String thenByOrder2 = "";

	private String columnOrdering = "";
	private String hiddenColumns = "";

	// Tab4
	private String trvlPurpose = "";
	private LinkedMap trvlPurposeMap;
	private String branchId = "";
	private String branchName = "";
	private String deptId = "";
	private String deptName = "";
	private String empId = "";
	private String empToken = "";
	private String empName = "";

	// Display Options
	private String hidReportView = "checked";
	private String hidReportRadio = "";
	private String reportType = "";

	private String settingName = "";

	private String reqStatus = "";

	private String myPage;
	private String show;
	private String noData;
	private String dataLength;
	private String exportAll = "";

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

	public String getFinanYear() {
		return finanYear;
	}

	public void setFinanYear(String finanYear) {
		this.finanYear = finanYear;
	}

	public LinkedMap getFinanYearMap() {
		return finanYearMap;
	}

	public void setFinanYearMap(LinkedMap finanYearMap) {
		this.finanYearMap = finanYearMap;
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

	public String getTrvlType() {
		return trvlType;
	}

	public void setTrvlType(String trvlType) {
		this.trvlType = trvlType;
	}

	public LinkedMap getTrvlTypeMap() {
		return trvlTypeMap;
	}

	public void setTrvlTypeMap(LinkedMap trvlTypeMap) {
		this.trvlTypeMap = trvlTypeMap;
	}

	public String getAppliFor() {
		return appliFor;
	}

	public void setAppliFor(String appliFor) {
		this.appliFor = appliFor;
	}

	public String getTravelCheck() {
		return travelCheck;
	}

	public void setTravelCheck(String travelCheck) {
		this.travelCheck = travelCheck;
	}

	public String getTrvlRadio() {
		return trvlRadio;
	}

	public void setTrvlRadio(String trvlRadio) {
		this.trvlRadio = trvlRadio;
	}

	public String getHidTravelSelf() {
		return hidTravelSelf;
	}

	public void setHidTravelSelf(String hidTravelSelf) {
		this.hidTravelSelf = hidTravelSelf;
	}

	public String getHidTravelComp() {
		return hidTravelComp;
	}

	public void setHidTravelComp(String hidTravelComp) {
		this.hidTravelComp = hidTravelComp;
	}

	public String getAccomCheck() {
		return accomCheck;
	}

	public void setAccomCheck(String accomCheck) {
		this.accomCheck = accomCheck;
	}

	public String getAccomRadio() {
		return accomRadio;
	}

	public void setAccomRadio(String accomRadio) {
		this.accomRadio = accomRadio;
	}

	public String getHidAccomSelf() {
		return hidAccomSelf;
	}

	public void setHidAccomSelf(String hidAccomSelf) {
		this.hidAccomSelf = hidAccomSelf;
	}

	public String getHidAccomComp() {
		return hidAccomComp;
	}

	public void setHidAccomComp(String hidAccomComp) {
		this.hidAccomComp = hidAccomComp;
	}

	public String getLocalCheck() {
		return localCheck;
	}

	public void setLocalCheck(String localCheck) {
		this.localCheck = localCheck;
	}

	public String getLocalRadio() {
		return localRadio;
	}

	public void setLocalRadio(String localRadio) {
		this.localRadio = localRadio;
	}

	public String getHidLocalSelf() {
		return hidLocalSelf;
	}

	public void setHidLocalSelf(String hidLocalSelf) {
		this.hidLocalSelf = hidLocalSelf;
	}

	public String getHidLocalComp() {
		return hidLocalComp;
	}

	public void setHidLocalComp(String hidLocalComp) {
		this.hidLocalComp = hidLocalComp;
	}

	public String getAppliNameFlag() {
		return appliNameFlag;
	}

	public void setAppliNameFlag(String appliNameFlag) {
		this.appliNameFlag = appliNameFlag;
	}

	public String getGradeFlag() {
		return gradeFlag;
	}

	public void setGradeFlag(String gradeFlag) {
		this.gradeFlag = gradeFlag;
	}

	public String getAppliDateFlag() {
		return appliDateFlag;
	}

	public void setAppliDateFlag(String appliDateFlag) {
		this.appliDateFlag = appliDateFlag;
	}

	public String getTravelIdFlag() {
		return travelIdFlag;
	}

	public void setTravelIdFlag(String travelIdFlag) {
		this.travelIdFlag = travelIdFlag;
	}

	public String getStartDateFlag() {
		return startDateFlag;
	}

	public void setStartDateFlag(String startDateFlag) {
		this.startDateFlag = startDateFlag;
	}

	public String getEndDateFlag() {
		return endDateFlag;
	}

	public void setEndDateFlag(String endDateFlag) {
		this.endDateFlag = endDateFlag;
	}

	public String getPurposeFlag() {
		return purposeFlag;
	}

	public void setPurposeFlag(String purposeFlag) {
		this.purposeFlag = purposeFlag;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getApprvdAmtFlag() {
		return apprvdAmtFlag;
	}

	public void setApprvdAmtFlag(String apprvdAmtFlag) {
		this.apprvdAmtFlag = apprvdAmtFlag;
	}

	public String getAdvanceAmtFlag() {
		return advanceAmtFlag;
	}

	public void setAdvanceAmtFlag(String advanceAmtFlag) {
		this.advanceAmtFlag = advanceAmtFlag;
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

	public String getTrvlPurpose() {
		return trvlPurpose;
	}

	public void setTrvlPurpose(String trvlPurpose) {
		this.trvlPurpose = trvlPurpose;
	}

	public LinkedMap getTrvlPurposeMap() {
		return trvlPurposeMap;
	}

	public void setTrvlPurposeMap(LinkedMap trvlPurposeMap) {
		this.trvlPurposeMap = trvlPurposeMap;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	/**
	 * @return the travelCostFlag
	 */
	public String getTravelCostFlag() {
		return travelCostFlag;
	}

	/**
	 * @param travelCostFlag the travelCostFlag to set
	 */
	public void setTravelCostFlag(String travelCostFlag) {
		this.travelCostFlag = travelCostFlag;
	}

	/**
	 * @return the accomCostFlag
	 */
	public String getAccomCostFlag() {
		return accomCostFlag;
	}

	/**
	 * @param accomCostFlag the accomCostFlag to set
	 */
	public void setAccomCostFlag(String accomCostFlag) {
		this.accomCostFlag = accomCostFlag;
	}

	/**
	 * @return the localCostFlag
	 */
	public String getLocalCostFlag() {
		return localCostFlag;
	}

	/**
	 * @param localCostFlag the localCostFlag to set
	 */
	public void setLocalCostFlag(String localCostFlag) {
		this.localCostFlag = localCostFlag;
	}

	public String getEmpIdFlag() {
		return empIdFlag;
	}

	public void setEmpIdFlag(String empIdFlag) {
		this.empIdFlag = empIdFlag;
	}

}
