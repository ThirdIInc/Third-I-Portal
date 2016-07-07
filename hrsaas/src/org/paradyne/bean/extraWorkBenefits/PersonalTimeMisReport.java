package org.paradyne.bean.extraWorkBenefits;

import org.paradyne.lib.BeanBase;

public class PersonalTimeMisReport extends BeanBase {

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
	private String statusFlag = "";
	private String fromDateFlag = "";
	private String toDateFlag = "";
	private String fromTimeFlag = "";
	private String toTimeFlag = "";
	private String totalTimeFlag = "";
	
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
	
	private String time1DateSelect = "";
	private String time1FromDate = "";
	private String time1ToDate = "";
	
	private String time2DateSelect = "";
	private String time2FromDate = "";
	private String time2ToDate = "";
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getFromTimeFlag() {
		return fromTimeFlag;
	}
	public void setFromTimeFlag(String fromTimeFlag) {
		this.fromTimeFlag = fromTimeFlag;
	}
	public String getToTimeFlag() {
		return toTimeFlag;
	}
	public void setToTimeFlag(String toTimeFlag) {
		this.toTimeFlag = toTimeFlag;
	}
	public String getTotalTimeFlag() {
		return totalTimeFlag;
	}
	public void setTotalTimeFlag(String totalTimeFlag) {
		this.totalTimeFlag = totalTimeFlag;
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
	public String getTime1DateSelect() {
		return time1DateSelect;
	}
	public void setTime1DateSelect(String time1DateSelect) {
		this.time1DateSelect = time1DateSelect;
	}
	public String getTime1FromDate() {
		return time1FromDate;
	}
	public void setTime1FromDate(String time1FromDate) {
		this.time1FromDate = time1FromDate;
	}
	public String getTime1ToDate() {
		return time1ToDate;
	}
	public void setTime1ToDate(String time1ToDate) {
		this.time1ToDate = time1ToDate;
	}
	public String getTime2DateSelect() {
		return time2DateSelect;
	}
	public void setTime2DateSelect(String time2DateSelect) {
		this.time2DateSelect = time2DateSelect;
	}
	public String getTime2FromDate() {
		return time2FromDate;
	}
	public void setTime2FromDate(String time2FromDate) {
		this.time2FromDate = time2FromDate;
	}
	public String getTime2ToDate() {
		return time2ToDate;
	}
	public void setTime2ToDate(String time2ToDate) {
		this.time2ToDate = time2ToDate;
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
	public String getFromDateFlag() {
		return fromDateFlag;
	}
	public void setFromDateFlag(String fromDateFlag) {
		this.fromDateFlag = fromDateFlag;
	}
	public String getToDateFlag() {
		return toDateFlag;
	}
	public void setToDateFlag(String toDateFlag) {
		this.toDateFlag = toDateFlag;
	}	
	
}
