package org.paradyne.bean.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.lib.BeanBase;

public class TrvlAccntReport extends BeanBase {

	private String backFlag = "";
	
	private String savedReport = "";
	private String reportId = "";
	private String reportTitle = "";
	
	//Tab1
	private String status = "";
	private String typeExp = "";
	private String gradeId = "";
	private String gradeName = "";
	private String branchId ="";
	private String branchName ="";
	private String deptId = "";
	private String deptName = "";
	private String advAmtSelect ="";
	private String advAmtFrom = "";
	private String advAmtTo = "";
	private String claimsetSelect = "";
	private String claimsetFrom = "";
	private String claimsetTo = "";
	private String fromDateSelect = "";
	private String fromDateFromDate = "";
	private String fromDateToDate = "";
	private String toDateSelect = "";
	private String toDateFromDate = "";
	private String toDateToDate= "";
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
	
	//Tab2
	private String appliNameFlag = "";
	private String gradeFlag = "";
	private String branchFlag = "";
	//private String travelIdFlag = "";
	private String empIdFlag = "";
	private String startDateFlag = "";
	private String endDateFlag = "";
	private String statusFlag = "";
	private String purposeFlag = "";
	private String typeFlag = "";
	private String requestFlag = "";
	private String advAmtFlag = "";
	
	//Tab3
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
	
	//Tab4
	private String trvlType = "";
	LinkedMap trvlTypeMap;
	private String trvlPurpose = "";
	LinkedMap trvlPurposeMap;
	private String startDateSelect = "";
	private String startFromDate = "";
	private String startToDate = "";
	private String endDateSelect = "";
	private String endFromDate = "";
	private String endToDate = "";
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeExp() {
		return typeExp;
	}
	public void setTypeExp(String typeExp) {
		this.typeExp = typeExp;
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
	public String getAdvAmtSelect() {
		return advAmtSelect;
	}
	public void setAdvAmtSelect(String advAmtSelect) {
		this.advAmtSelect = advAmtSelect;
	}
	public String getAdvAmtFrom() {
		return advAmtFrom;
	}
	public void setAdvAmtFrom(String advAmtFrom) {
		this.advAmtFrom = advAmtFrom;
	}
	public String getAdvAmtTo() {
		return advAmtTo;
	}
	public void setAdvAmtTo(String advAmtTo) {
		this.advAmtTo = advAmtTo;
	}
	public String getClaimsetSelect() {
		return claimsetSelect;
	}
	public void setClaimsetSelect(String claimsetSelect) {
		this.claimsetSelect = claimsetSelect;
	}
	public String getClaimsetFrom() {
		return claimsetFrom;
	}
	public void setClaimsetFrom(String claimsetFrom) {
		this.claimsetFrom = claimsetFrom;
	}
	public String getClaimsetTo() {
		return claimsetTo;
	}
	public void setClaimsetTo(String claimsetTo) {
		this.claimsetTo = claimsetTo;
	}
	public String getFromDateSelect() {
		return fromDateSelect;
	}
	public void setFromDateSelect(String fromDateSelect) {
		this.fromDateSelect = fromDateSelect;
	}
	public String getFromDateFromDate() {
		return fromDateFromDate;
	}
	public void setFromDateFromDate(String fromDateFromDate) {
		this.fromDateFromDate = fromDateFromDate;
	}
	public String getFromDateToDate() {
		return fromDateToDate;
	}
	public void setFromDateToDate(String fromDateToDate) {
		this.fromDateToDate = fromDateToDate;
	}
	public String getToDateSelect() {
		return toDateSelect;
	}
	public void setToDateSelect(String toDateSelect) {
		this.toDateSelect = toDateSelect;
	}
	public String getToDateFromDate() {
		return toDateFromDate;
	}
	public void setToDateFromDate(String toDateFromDate) {
		this.toDateFromDate = toDateFromDate;
	}
	public String getToDateToDate() {
		return toDateToDate;
	}
	public void setToDateToDate(String toDateToDate) {
		this.toDateToDate = toDateToDate;
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
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	/*
	public String getTravelIdFlag() {
		return travelIdFlag;
	}
	public void setTravelIdFlag(String travelIdFlag) {
		this.travelIdFlag = travelIdFlag;
	}
	*/
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
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
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
	public String getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(String requestFlag) {
		this.requestFlag = requestFlag;
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
	public String getStartDateSelect() {
		return startDateSelect;
	}
	public void setStartDateSelect(String startDateSelect) {
		this.startDateSelect = startDateSelect;
	}
	public String getStartFromDate() {
		return startFromDate;
	}
	public void setStartFromDate(String startFromDate) {
		this.startFromDate = startFromDate;
	}
	public String getStartToDate() {
		return startToDate;
	}
	public void setStartToDate(String startToDate) {
		this.startToDate = startToDate;
	}
	public String getEndDateSelect() {
		return endDateSelect;
	}
	public void setEndDateSelect(String endDateSelect) {
		this.endDateSelect = endDateSelect;
	}
	public String getEndFromDate() {
		return endFromDate;
	}
	public void setEndFromDate(String endFromDate) {
		this.endFromDate = endFromDate;
	}
	public String getEndToDate() {
		return endToDate;
	}
	public void setEndToDate(String endToDate) {
		this.endToDate = endToDate;
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
	public String getEmpIdFlag() {
		return empIdFlag;
	}
	public void setEmpIdFlag(String empIdFlag) {
		this.empIdFlag = empIdFlag;
	}
	public String getAdvAmtFlag() {
		return advAmtFlag;
	}
	public void setAdvAmtFlag(String advAmtFlag) {
		this.advAmtFlag = advAmtFlag;
	}
	
}
