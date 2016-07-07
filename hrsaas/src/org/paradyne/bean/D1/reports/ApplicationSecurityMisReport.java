package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1380.
 */
public class ApplicationSecurityMisReport extends BeanBase {
	/** checked. * */
	private final String checked = "checked";
	/** trackingNo. * */
	private String trackingNo = "";
	/** trackingNoFlag. * */
	private String trackingNoFlag = "";
	/** backFlag. * */
	private String backFlag = "";
	/** savedReport. * */
	private String savedReport = "";
	/** reportId. * */
	private String reportId = "";
	/** reportTitle. * */
	private String reportTitle = "";
	
	//Tab 1
	/** divName. * */
	private String divName = "";
	/** divId. * */
	private String divId = "";
	/** deptName. * */
	private String deptName = "";
	/** deptId. * */
	private String deptId = "";
	/** branchName. * */
	private String branchName = "";
	/** branchId. * */
	private String branchId = "";
	/** empTypeName. * */
	private String empTypeName = "";
	/** empTypeId. * */
	private String empTypeId = "";
	/** desigName. * */
	private String desigName = "";
	/** desigId. * */
	private String desigId = "";
	/** empToken. * */
	private String empToken = "";
	/** empName. * */
	private String empName = "";
	/** empId. * */
	private String empId = "";
	/** status. * */
	private String status = "";
	
	//Tab 2
	/** empNameFlag. * */
	private String empNameFlag = "";
	/** divFlag. * */
	private String divFlag = "";
	/** deptFlag. * */
	private String deptFlag = "";
	/** branchFlag. * */
	private String branchFlag = "";
	/** desigFlag. * */
	private String desigFlag = "";
	/** empTypeFlag. * */
	private String empTypeFlag = "";
	/** appliDateFlag. * */
	private String appliDateFlag = "";
	/** attDateFlag. * */
	private String attDateFlag = "";
	/** statusFlag. * */
	private String statusFlag = "";
	/** managerFlag. * */
	private String managerFlag = "";
	
	
	//Tab 3
	/** sortBy. * */
	private String sortBy = "";
	/** hiddenSortBy. * */
	private String hiddenSortBy = "";
	/** sortByAsc. * */
	private String sortByAsc = this.checked;
	/** sortByDsc. * */
	private String sortByDsc = "";
	/** sortByOrder. * */
	private String sortByOrder = "";
	/** thenBy1. * */
	private String thenBy1 = "";
	/** hiddenThenBy1. * */
	private String hiddenThenBy1 = "";
	/** thenByOrder1Asc. * */
	private String thenByOrder1Asc = this.checked;
	/** thenByOrder1Dsc. * */
	private String thenByOrder1Dsc = "";
	/** thenByOrder1. * */
	private String thenByOrder1 = "";	
	/** thenBy2. * */
	private String thenBy2 = "";
	/** hiddenThenBy2. * */
	private String hiddenThenBy2 = "";
	/** . * */ 
	private String thenByOrder2Asc = this.checked;
	/** thenByOrder2Asc. * */
	private String thenByOrder2Dsc = "";
	/** thenByOrder2. * */
	private String thenByOrder2 = "";
	/** columnOrdering. * */
	private String columnOrdering = "";
	/** hiddenColumns. * */
	private String hiddenColumns = "";
	
	//Tab 4
	/** appliDateSelect. * */
	private String appliDateSelect = "";
	/** appliFromDate. * */
	private String appliFromDate = "";
	/** appliToDate. * */
	private String appliToDate = "";
	/** approvedDateSelect. * */
	private String approvedDateSelect = "";
	/** attFromDate. * */
	private String attFromDate = "";
	/** attToDate. * */
	private String attToDate = "";
	
	//Display Options
	/** hidReportView. * */
	private String hidReportView = this.checked;
	/** hidReportRadio. * */
	private String hidReportRadio = "";
	/** reportType. * */
	private String reportType = "";
	/** settingName. * */
	private String settingName = "";
	/** reqStatus. * */
	private String reqStatus = "";
	/** myPage. * */
	private String myPage;
	/** show. * */
	private String show;
	/** noData. * */
	private String noData;
	/** dataLength. * */
	private String dataLength;
	/** . * */
	private String exportAll = "";
	
	/**
	 * @return the divName
	 */
	public String getDivName() {
		return this.divName;
	}
	/**
	 * @param divName the divName to set
	 */
	public void setDivName(final String divName) {
		this.divName = divName;
	}
	/**
	 * @return the divId
	 */
	public String getDivId() {
		return this.divId;
	}
	/**
	 * @param divId the divId to set
	 */
	public void setDivId(final String divId) {
		this.divId = divId;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(final String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return this.deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(final String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return this.branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return this.branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(final String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the empTypeName
	 */
	public String getEmpTypeName() {
		return this.empTypeName;
	}
	/**
	 * @param empTypeName the empTypeName to set
	 */
	public void setEmpTypeName(final String empTypeName) {
		this.empTypeName = empTypeName;
	}
	/**
	 * @return the empTypeId
	 */
	public String getEmpTypeId() {
		return this.empTypeId;
	}
	/**
	 * @param empTypeId the empTypeId to set
	 */
	public void setEmpTypeId(final String empTypeId) {
		this.empTypeId = empTypeId;
	}
	/**
	 * @return the desigName
	 */
	public String getDesigName() {
		return this.desigName;
	}
	/**
	 * @param desigName the desigName to set
	 */
	public void setDesigName(final String desigName) {
		this.desigName = desigName;
	}
	/**
	 * @return the desigId
	 */
	public String getDesigId() {
		return this.desigId;
	}
	/**
	 * @param desigId the desigId to set
	 */
	public void setDesigId(final String desigId) {
		this.desigId = desigId;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return this.empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(final String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return this.empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(final String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return this.empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(final String empId) {
		this.empId = empId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return this.trackingNo;
	}
	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(final String trackingNo) {
		this.trackingNo = trackingNo;
	}
	/**
	 * @return the trackingNoFlag
	 */
	public String getTrackingNoFlag() {
		return this.trackingNoFlag;
	}
	/**
	 * @param trackingNoFlag the trackingNoFlag to set
	 */
	public void setTrackingNoFlag(final String trackingNoFlag) {
		this.trackingNoFlag = trackingNoFlag;
	}
	/**
	 * @return the backFlag
	 */
	public String getBackFlag() {
		return this.backFlag;
	}
	/**
	 * @param backFlag the backFlag to set
	 */
	public void setBackFlag(final String backFlag) {
		this.backFlag = backFlag;
	}
	/**
	 * @return the savedReport
	 */
	public String getSavedReport() {
		return this.savedReport;
	}
	/**
	 * @param savedReport the savedReport to set
	 */
	public void setSavedReport(final String savedReport) {
		this.savedReport = savedReport;
	}
	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return this.reportId;
	}
	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(final String reportId) {
		this.reportId = reportId;
	}
	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return this.reportTitle;
	}
	/**
	 * @param reportTitle the reportTitle to set
	 */
	public void setReportTitle(final String reportTitle) {
		this.reportTitle = reportTitle;
	}
	/**
	 * @return the empNameFlag
	 */
	public String getEmpNameFlag() {
		return this.empNameFlag;
	}
	/**
	 * @param empNameFlag the empNameFlag to set
	 */
	public void setEmpNameFlag(final String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}
	/**
	 * @return the divFlag
	 */
	public String getDivFlag() {
		return this.divFlag;
	}
	/**
	 * @param divFlag the divFlag to set
	 */
	public void setDivFlag(final String divFlag) {
		this.divFlag = divFlag;
	}
	/**
	 * @return the deptFlag
	 */
	public String getDeptFlag() {
		return this.deptFlag;
	}
	/**
	 * @param deptFlag the deptFlag to set
	 */
	public void setDeptFlag(final String deptFlag) {
		this.deptFlag = deptFlag;
	}
	/**
	 * @return the branchFlag
	 */
	public String getBranchFlag() {
		return this.branchFlag;
	}
	/**
	 * @param branchFlag the branchFlag to set
	 */
	public void setBranchFlag(final String branchFlag) {
		this.branchFlag = branchFlag;
	}
	/**
	 * @return the desigFlag
	 */
	public String getDesigFlag() {
		return this.desigFlag;
	}
	/**
	 * @param desigFlag the desigFlag to set
	 */
	public void setDesigFlag(final String desigFlag) {
		this.desigFlag = desigFlag;
	}
	/**
	 * @return the empTypeFlag
	 */
	public String getEmpTypeFlag() {
		return this.empTypeFlag;
	}
	/**
	 * @param empTypeFlag the empTypeFlag to set
	 */
	public void setEmpTypeFlag(final String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	/**
	 * @return the appliDateFlag
	 */
	public String getAppliDateFlag() {
		return this.appliDateFlag;
	}
	/**
	 * @param appliDateFlag the appliDateFlag to set
	 */
	public void setAppliDateFlag(final String appliDateFlag) {
		this.appliDateFlag = appliDateFlag;
	}
	/**
	 * @return the attDateFlag
	 */
	public String getAttDateFlag() {
		return this.attDateFlag;
	}
	/**
	 * @param attDateFlag the attDateFlag to set
	 */
	public void setAttDateFlag(final String attDateFlag) {
		this.attDateFlag = attDateFlag;
	}
	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return this.statusFlag;
	}
	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(final String statusFlag) {
		this.statusFlag = statusFlag;
	}
	 
	/**
	 * @return the managerFlag
	 */
	public String getManagerFlag() {
		return this.managerFlag;
	}
	/**
	 * @param managerFlag the managerFlag to set
	 */
	public void setManagerFlag(final String managerFlag) {
		this.managerFlag = managerFlag;
	}
	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return this.sortBy;
	}
	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(final String sortBy) {
		this.sortBy = sortBy;
	}
	/**
	 * @return the hiddenSortBy
	 */
	public String getHiddenSortBy() {
		return this.hiddenSortBy;
	}
	/**
	 * @param hiddenSortBy the hiddenSortBy to set
	 */
	public void setHiddenSortBy(final String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}
	/**
	 * @return the sortByAsc
	 */
	public String getSortByAsc() {
		return this.sortByAsc;
	}
	/**
	 * @param sortByAsc the sortByAsc to set
	 */
	public void setSortByAsc(final String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}
	/**
	 * @return the sortByDsc
	 */
	public String getSortByDsc() {
		return this.sortByDsc;
	}
	/**
	 * @param sortByDsc the sortByDsc to set
	 */
	public void setSortByDsc(final String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}
	/**
	 * @return the sortByOrder
	 */
	public String getSortByOrder() {
		return this.sortByOrder;
	}
	/**
	 * @param sortByOrder the sortByOrder to set
	 */
	public void setSortByOrder(final String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}
	/**
	 * @return the thenBy1
	 */
	public String getThenBy1() {
		return this.thenBy1;
	}
	/**
	 * @param thenBy1 the thenBy1 to set
	 */
	public void setThenBy1(final String thenBy1) {
		this.thenBy1 = thenBy1;
	}
	/**
	 * @return the hiddenThenBy1
	 */
	public String getHiddenThenBy1() {
		return this.hiddenThenBy1;
	}
	/**
	 * @param hiddenThenBy1 the hiddenThenBy1 to set
	 */
	public void setHiddenThenBy1(final String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}
	/**
	 * @return the thenByOrder1Asc
	 */
	public String getThenByOrder1Asc() {
		return this.thenByOrder1Asc;
	}
	/**
	 * @param thenByOrder1Asc the thenByOrder1Asc to set
	 */
	public void setThenByOrder1Asc(final String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}
	/**
	 * @return the thenByOrder1Dsc
	 */
	public String getThenByOrder1Dsc() {
		return this.thenByOrder1Dsc;
	}
	/**
	 * @param thenByOrder1Dsc the thenByOrder1Dsc to set
	 */
	public void setThenByOrder1Dsc(final String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}
	/**
	 * @return the thenByOrder1
	 */
	public String getThenByOrder1() {
		return this.thenByOrder1;
	}
	/**
	 * @param thenByOrder1 the thenByOrder1 to set
	 */
	public void setThenByOrder1(final String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}
	/**
	 * @return the thenBy2
	 */
	public String getThenBy2() {
		return this.thenBy2;
	}
	/**
	 * @param thenBy2 the thenBy2 to set
	 */
	public void setThenBy2(final String thenBy2) {
		this.thenBy2 = thenBy2;
	}
	/**
	 * @return the hiddenThenBy2
	 */
	public String getHiddenThenBy2() {
		return this.hiddenThenBy2;
	}
	/**
	 * @param hiddenThenBy2 the hiddenThenBy2 to set
	 */
	public void setHiddenThenBy2(final String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}
	/**
	 * @return the thenByOrder2Asc
	 */
	public String getThenByOrder2Asc() {
		return this.thenByOrder2Asc;
	}
	/**
	 * @param thenByOrder2Asc the thenByOrder2Asc to set
	 */
	public void setThenByOrder2Asc(final String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}
	/**
	 * @return the thenByOrder2Dsc
	 */
	public String getThenByOrder2Dsc() {
		return this.thenByOrder2Dsc;
	}
	/**
	 * @param thenByOrder2Dsc the thenByOrder2Dsc to set
	 */
	public void setThenByOrder2Dsc(final String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}
	/**
	 * @return the thenByOrder2
	 */
	public String getThenByOrder2() {
		return this.thenByOrder2;
	}
	/**
	 * @param thenByOrder2 the thenByOrder2 to set
	 */
	public void setThenByOrder2(final String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}
	/**
	 * @return the columnOrdering
	 */
	public String getColumnOrdering() {
		return this.columnOrdering;
	}
	/**
	 * @param columnOrdering the columnOrdering to set
	 */
	public void setColumnOrdering(final String columnOrdering) {
		this.columnOrdering = columnOrdering;
	}
	/**
	 * @return the hiddenColumns
	 */
	public String getHiddenColumns() {
		return this.hiddenColumns;
	}
	/**
	 * @param hiddenColumns the hiddenColumns to set
	 */
	public void setHiddenColumns(final String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}
	/**
	 * @return the appliDateSelect
	 */
	public String getAppliDateSelect() {
		return this.appliDateSelect;
	}
	/**
	 * @param appliDateSelect the appliDateSelect to set
	 */
	public void setAppliDateSelect(final String appliDateSelect) {
		this.appliDateSelect = appliDateSelect;
	}
	/**
	 * @return the appliFromDate
	 */
	public String getAppliFromDate() {
		return this.appliFromDate;
	}
	/**
	 * @param appliFromDate the appliFromDate to set
	 */
	public void setAppliFromDate(final String appliFromDate) {
		this.appliFromDate = appliFromDate;
	}
	/**
	 * @return the appliToDate
	 */
	public String getAppliToDate() {
		return this.appliToDate;
	}
	/**
	 * @param appliToDate the appliToDate to set
	 */
	public void setAppliToDate(final String appliToDate) {
		this.appliToDate = appliToDate;
	}
	/**
	 * @return the approvedDateSelect
	 */
	public String getApprovedDateSelect() {
		return this.approvedDateSelect;
	}
	/**
	 * @param approvedDateSelect the approvedDateSelect to set
	 */
	public void setApprovedDateSelect(final String approvedDateSelect) {
		this.approvedDateSelect = approvedDateSelect;
	}
	/**
	 * @return the attFromDate
	 */
	public String getAttFromDate() {
		return this.attFromDate;
	}
	/**
	 * @param attFromDate the attFromDate to set
	 */
	public void setAttFromDate(final String attFromDate) {
		this.attFromDate = attFromDate;
	}
	/**
	 * @return the attToDate
	 */
	public String getAttToDate() {
		return this.attToDate;
	}
	/**
	 * @param attToDate the attToDate to set
	 */
	public void setAttToDate(final String attToDate) {
		this.attToDate = attToDate;
	}
	/**
	 * @return the hidReportView
	 */
	public String getHidReportView() {
		return this.hidReportView;
	}
	/**
	 * @param hidReportView the hidReportView to set
	 */
	public void setHidReportView(final String hidReportView) {
		this.hidReportView = hidReportView;
	}
	/**
	 * @return the hidReportRadio
	 */
	public String getHidReportRadio() {
		return this.hidReportRadio;
	}
	/**
	 * @param hidReportRadio the hidReportRadio to set
	 */
	public void setHidReportRadio(final String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return this.reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(final String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the settingName
	 */
	public String getSettingName() {
		return this.settingName;
	}
	/**
	 * @param settingName the settingName to set
	 */
	public void setSettingName(final String settingName) {
		this.settingName = settingName;
	}
	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return this.reqStatus;
	}
	/**
	 * @param reqStatus the reqStatus to set
	 */
	public void setReqStatus(final String reqStatus) {
		this.reqStatus = reqStatus;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return this.show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(final String show) {
		this.show = show;
	}
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return this.noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(final String noData) {
		this.noData = noData;
	}
	/**
	 * @return the dataLength
	 */
	public String getDataLength() {
		return this.dataLength;
	}
	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(final String dataLength) {
		this.dataLength = dataLength;
	}
	/**
	 * @return the exportAll
	 */
	public String getExportAll() {
		return this.exportAll;
	}
	/**
	 * @param exportAll the exportAll to set
	 */
	public void setExportAll(final String exportAll) {
		this.exportAll = exportAll;
	}
}
