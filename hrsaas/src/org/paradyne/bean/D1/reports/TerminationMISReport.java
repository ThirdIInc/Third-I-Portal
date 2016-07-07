package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

/**
 * @author Nilesh Dhandare on 11th May 11.
 *
 */
public class TerminationMISReport extends BeanBase {
	/**
	 * Set Checked.
	 */
	private static final String CHECKED = "checked";
	
	/**
	 *Back Flag Hidden field.
	 */
	private String backFlag = "";
	/**
	* Save report hidden field.
	*/
	private String savedReport = "";
	/**
	 * report id hidden field.
	 */
	private String reportId = "";
	/**
	* Report Title.
	*/
	private String reportTitle = "";
	
	//Tab 1
		
	/**
	* Division Name. 
	*/
	private String divName = "";
	/**
	* Division hidden id.
	*/
	private String divId = "";
	/**
	* Department Name.
	*/
	private String deptName = "";
	/**
	* Department hidden id.
	*/
	private String deptId = "";
	/**
	* Branch Name.
	*/
	private String branchName = "";
	/**
	* branch hidden id.
	*/
	private String branchId = "";
	/**
	* Employee Type.
	*/
	private String empTypeName = "";
	/**
	 * Employee Type hidden id.
	 */
	private String empTypeId = "";
	/**
	* Designation Name.
	*/
	private String desigName = "";
	/**
	* Designation hidden id.
	*/
	private String desigId = "";
	/**
	* Employee Token.
	*/
	private String empToken = "";
	/**
	* Employee Name.
	*/
	private String empName = "";
	/**
	* Employee Id.
	*/
	private String empId = "";
	/**
	* Status.
	*/
	private String status = "";
	
	//Tab 2
		
	/**
	* Employee Name Flag for check box.
	*/
	private String empNameFlag = "";
	/**
	* Division Name Flag for check box.
	*/
	private String divFlag = "";
	/**
	* Department Name Flag for check box.
	*/
	private String deptFlag = "";
	/**
	* Branch Name Flag for check box.
	*/
	private String branchFlag = "";
	/**
	* Designation Name Flag for check box.
	*/
	private String desigFlag = "";
	/**
	* Employee Type Flag for check box.
	*/
	private String empTypeFlag = "";
	/**
	* Application Date Flag for check box.
	*/
	private String appliDateFlag = "";
	/**
	* Manager Name Check box flag.
	*/
	private String managerNameFlag = "";
	/**
	* Tracking Number Check box flag.
	*/
	private String trackingNumberFlag = "";
	/**
	* Termination Type Check box flag.
	*/
	private String terminationTypeFlag = "";
	/**
	* Termination Reason Check box flag.
	*/
	private String terminationReasonFlag = "";
	/**
	* Application Status Flag.
	*/
	private String applStatusFlag = "";
	/**
	* City Check box flag.
	*/
	private String cityFlag = "";
	/**
	* Termination Date Check box flag.
	*/
	private String terminationDateFlag = "";
	/**
	* Check box flag.
	*/
	private String applicationDateFlag = "";
	/**
	* Last Day Check box flag. 
	*/
	private String lastDayFlag = "";
	
	//Tab 3
		
	/**
	* Sort by.
	*/
	private String sortBy = "";
	/**
	* hidden field SortBy.
	*/
	private String hiddenSortBy = "";
	/**
	* sortByAsc to sort Ascending.
	*/
	private String sortByAsc = TerminationMISReport.CHECKED;
	/**
	* sortByDsc to sort Descending.
	*/
	private String sortByDsc = "";
	/**
	* Sort by order.
	*/
	private String sortByOrder = "";
	/**
	* thenBy1.
	*/
	private String thenBy1 = "";
	/**
	* hiddenThenBy1.
	*/
	private String hiddenThenBy1 = "";
	/**
	* thenByOrder1Asc.
	*/
	private String thenByOrder1Asc = TerminationMISReport.CHECKED;
	/**
	 * thenByOrder1Dsc.
	 */
	private String thenByOrder1Dsc = "";
	/**
	* thenByOrder1.
	*/
	private String thenByOrder1 = "";
	/**
	*	 thenBy2.
	*/
	private String thenBy2 = "";
	/**
	* hiddenThenBy2.
	*/
	private String hiddenThenBy2 = "";
	/**
	* thenByOrder2Asc.
	*/
	private String thenByOrder2Asc = TerminationMISReport.CHECKED;
	/**
	* thenByOrder2Dsc.
	*/
	private String thenByOrder2Dsc = "";
	/**
	*  thenByOrder2.
	*/
	private String thenByOrder2 = "";
	/**
	*
	*/
	private String columnOrdering = "";
	/**
	* hiddenColumns.
	*/
	private String hiddenColumns = "";
	
	//Tab 4 appropriate advance filter option for report generation
	
	/**
	* Application Date selection field.
	*/
	private String appliDateSelect = "";
	/**
	* Application From Date field. 
	*/
	private String appliFromDate = "";
	/**
	* Application To Date field.
	*/
	private String appliToDate = "";
	/**
	* Work Date selection field.
	*/
	private String workDateSelect = "";
	/**
	* Work From Date  field.
	*/
	private String workFromDate = "";
	/**
	* Work To Date  field.
	*/
	private String workToDate = "";
	 /**
	  *  Termination Date selection field. 
	  */
	private String terDateSelect = "";
	/**
	 * Termination From Date field.
	 */
	private String terFromDate = "";
	/**
	 * Termiantion To Date Field.
	 */
	private String terToDate = "";
	/**
	* Approval Date selection field.
	*/
	private String apprDateSelect = "";
	/**
	 * From Approval Date.
	 */
	private String apprFromDate = "";
	/**
	 * To Approval Date.
	 */
	private String apprToDate = "";
		
	//Display Options
	
	/**
	 * No data field.
	 */
	private String noData;
	/**
	 * Data Length field.
	 */
	private String dataLength;
	/**
	 * Export All field.
	 */
	private String exportAll = "";
	
	/**
	 * hidReportView.
	 */
	private String hidReportView = TerminationMISReport.CHECKED;
	/**
	 * hidReportRadio.
	 */
	private String hidReportRadio = "";
	/**
	 * Reporting Type.
	 */
	private String reportType = "";
	/**
	 * Setting Name.
	 */
	private String settingName = "";
	/**
	 * Request Status hidden field.
	 */
	private String reqStatus = "";
	/**
	 * My Page paging field.
	 */
	private String myPage;
		
	//Extra Fields Added
	
	/**
	 * Manager Name.
	 */
	private String managerName = "";
	/**
	 * Manager Id.
	 */
	private String managerId = "";
	/**
	 * Tracking NUmber.
	 */
	private String trackingNumber = "";
	/**
	 * Tracking Id.
	 */
	private String trackingId = "";
	/**
	 * Termination Type.
	 */
	private String terminationType = "";
	/**
	 * Termination Reason. 
	 */
	private String terminationReason = "";
	/**
	 * Application Status.
	 */
	private String applStatus = "";
	/**
	 * City.
	 */
	private String city = "";
	/**
	 * Termination Date.
	 */
	private String terminationDate = "";
	/**
	 * Application Date.
	 */
	private String applicationDate = "";
	/**
	 * Last Day.
	 */
	private String lastDay = "";
	
	
	
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
	 * @return the managerName
	 */
	public String getManagerName() {
		return this.managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(final String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the managerId
	 */
	public String getManagerId() {
		return this.managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(final String managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return this.trackingId;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(final String trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @return the terminationType
	 */
	public String getTerminationType() {
		return this.terminationType;
	}
	/**
	 * @param terminationType the terminationType to set
	 */
	public void setTerminationType(final String terminationType) {
		this.terminationType = terminationType;
	}
	/**
	 * @return the applStatus
	 */
	public String getApplStatus() {
		return this.applStatus;
	}
	/**
	 * @param applStatus the applStatus to set
	 */
	public void setApplStatus(final String applStatus) {
		this.applStatus = applStatus;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	/**
	 * @return the terminationDate
	 */
	public String getTerminationDate() {
		return this.terminationDate;
	}
	/**
	 * @param terminationDate the terminationDate to set
	 */
	public void setTerminationDate(final String terminationDate) {
		this.terminationDate = terminationDate;
	}
	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return this.applicationDate;
	}
	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(final String applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	/**
	 * @return the lastDay
	 */
	public String getLastDay() {
		return this.lastDay;
	}
	/**
	 * @param lastDay the lastDay to set
	 */
	public void setLastDay(final String lastDay) {
		this.lastDay = lastDay;
	}
	/**
	 * @return the terminationReason
	 */
	public String getTerminationReason() {
		return this.terminationReason;
	}
	/**
	 * @param terminationReason the terminationReason to set
	 */
	public void setTerminationReason(final String terminationReason) {
		this.terminationReason = terminationReason;
	}
	/**
	 * @return the workDateSelect
	 */
	public String getWorkDateSelect() {
		return this.workDateSelect;
	}
	/**
	 * @param workDateSelect the workDateSelect to set
	 */
	public void setWorkDateSelect(final String workDateSelect) {
		this.workDateSelect = workDateSelect;
	}
	/**
	 * @return the workFromDate
	 */
	public String getWorkFromDate() {
		return this.workFromDate;
	}
	/**
	 * @param workFromDate the workFromDate to set
	 */
	public void setWorkFromDate(final String workFromDate) {
		this.workFromDate = workFromDate;
	}
	/**
	 * @return the workToDate
	 */
	public String getWorkToDate() {
		return this.workToDate;
	}
	/**
	 * @param workToDate the workToDate to set
	 */
	public void setWorkToDate(final String workToDate) {
		this.workToDate = workToDate;
	}
	/**
	 * @return the terDateSelect
	 */
	public String getTerDateSelect() {
		return this.terDateSelect;
	}
	/**
	 * @param terDateSelect the terDateSelect to set
	 */
	public void setTerDateSelect(final String terDateSelect) {
		this.terDateSelect = terDateSelect;
	}
	/**
	 * @return the terFromDate
	 */
	public String getTerFromDate() {
		return this.terFromDate;
	}
	/**
	 * @param terFromDate the terFromDate to set
	 */
	public void setTerFromDate(final String terFromDate) {
		this.terFromDate = terFromDate;
	}
	/**
	 * @return the terToDate
	 */
	public String getTerToDate() {
		return this.terToDate;
	}
	/**
	 * @param terToDate the terToDate to set
	 */
	public void setTerToDate(final String terToDate) {
		this.terToDate = terToDate;
	}
	/**
	 * @return the apprDateSelect
	 */
	public String getApprDateSelect() {
		return this.apprDateSelect;
	}
	/**
	 * @param apprDateSelect the apprDateSelect to set
	 */
	public void setApprDateSelect(final String apprDateSelect) {
		this.apprDateSelect = apprDateSelect;
	}
	/**
	 * @return the apprFromDate
	 */
	public String getApprFromDate() {
		return this.apprFromDate;
	}
	/**
	 * @param apprFromDate the apprFromDate to set
	 */
	public void setApprFromDate(final String apprFromDate) {
		this.apprFromDate = apprFromDate;
	}
	/**
	 * @return the apprToDate
	 */
	public String getApprToDate() {
		return this.apprToDate;
	}
	/**
	 * @param apprToDate the apprToDate to set
	 */
	public void setApprToDate(final String apprToDate) {
		this.apprToDate = apprToDate;
	}
	/**
	 * @return the trackingNumberFlag
	 */
	public String getTrackingNumberFlag() {
		return this.trackingNumberFlag;
	}
	/**
	 * @param trackingNumberFlag the trackingNumberFlag to set
	 */
	public void setTrackingNumberFlag(final String trackingNumberFlag) {
		this.trackingNumberFlag = trackingNumberFlag;
	}
	/**
	 * @return the managerNameFlag
	 */
	public String getManagerNameFlag() {
		return this.managerNameFlag;
	}
	/**
	 * @param managerNameFlag the managerNameFlag to set
	 */
	public void setManagerNameFlag(final String managerNameFlag) {
		this.managerNameFlag = managerNameFlag;
	}
	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return this.trackingNumber;
	}
	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	/**
	 * @return the terminationTypeFlag
	 */
	public String getTerminationTypeFlag() {
		return this.terminationTypeFlag;
	}
	/**
	 * @param terminationTypeFlag the terminationTypeFlag to set
	 */
	public void setTerminationTypeFlag(final String terminationTypeFlag) {
		this.terminationTypeFlag = terminationTypeFlag;
	}
	/**
	 * @return the terminationReasonFlag
	 */
	public String getTerminationReasonFlag() {
		return this.terminationReasonFlag;
	}
	/**
	 * @param terminationReasonFlag the terminationReasonFlag to set
	 */
	public void setTerminationReasonFlag(final String terminationReasonFlag) {
		this.terminationReasonFlag = terminationReasonFlag;
	}
	/**
	 * @return the applStatusFlag
	 */
	public String getApplStatusFlag() {
		return this.applStatusFlag;
	}
	/**
	 * @param applStatusFlag the applStatusFlag to set
	 */
	public void setApplStatusFlag(final String applStatusFlag) {
		this.applStatusFlag = applStatusFlag;
	}
	/**
	 * @return the cityFlag
	 */
	public String getCityFlag() {
		return this.cityFlag;
	}
	/**
	 * @param cityFlag the cityFlag to set
	 */
	public void setCityFlag(final String cityFlag) {
		this.cityFlag = cityFlag;
	}
	/**
	 * @return the terminationDateFlag
	 */
	public String getTerminationDateFlag() {
		return this.terminationDateFlag;
	}
	/**
	 * @param terminationDateFlag the terminationDateFlag to set
	 */
	public void setTerminationDateFlag(final String terminationDateFlag) {
		this.terminationDateFlag = terminationDateFlag;
	}
	/**
	 * @return the applicationDateFlag
	 */
	public String getApplicationDateFlag() {
		return this.applicationDateFlag;
	}
	/**
	 * @param applicationDateFlag the applicationDateFlag to set
	 */
	public void setApplicationDateFlag(final String applicationDateFlag) {
		this.applicationDateFlag = applicationDateFlag;
	}
	/**
	 * @return the lastDayFlag
	 */
	public String getLastDayFlag() {
		return this.lastDayFlag;
	}
	/**
	 * @param lastDayFlag the lastDayFlag to set
	 */
	public void setLastDayFlag(final String lastDayFlag) {
		this.lastDayFlag = lastDayFlag;
	}
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	/**
	 * @return the dataLength
	 */
	public String getDataLength() {
		return dataLength;
	}
	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	/**
	 * @return the exportAll
	 */
	public String getExportAll() {
		return exportAll;
	}
	/**
	 * @param exportAll the exportAll to set
	 */
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}

}
