package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

public class ATRMISReport extends BeanBase {

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
	/**
	 * Tracking Id.
	 */
	private String trackingId = "";
	
	//Tab 2- Check Boxes Flags.
	/**
	 * Employee Name Flag.
	 */
	private String empNameFlag = "";
	/**
	 * Division Flag.
	 */
	private String divFlag = "";
	/**
	 * Department Flag.
	 */
	private String deptFlag = "";
	/**
	 * Branch Flag.
	 */
	private String branchFlag = "";
	/**
	 * Designation Flag.
	 */
	private String desigFlag = "";
	/**
	 * Employee Type Flag.
	 */
	private String empTypeFlag = "";
	/**
	 * Application Date Flag.
	 */
	private String appliDateFlag = "";
	/**
	 * Attendance Date Flag.
	 */
	private String attDateFlag = "";
	/**
	 * Status Flag.
	 */
	private String statusFlag = "";
	/**
	 * Login Flag.
	 */
	private String logInFlag = "";
	/**
	 * Last Hours Flag.
	 */
	private String lateHrsFlag = "";
	/**
	 * Late Hrs Deduct Flag.
	 */
	private String lateHrsDeductFlag = "";
	
	//Tab 3 -Sort Fuctionality.
	/**
	 * Sort by.
	 */
	private String sortBy = "";
	/**
	 * Hidden SOrt By.
	 */
	private String hiddenSortBy = "";
	/**
	 * Sort By Asc.
	 */
	private String sortByAsc = "checked";
	/**
	 * Sort by Descending.
	 */
	private String sortByDsc = "";
	/**
	 * Sort by order.
	 */
	private String sortByOrder = "";
	/**
	 * Then By 1.
	 */
	private String thenBy1 = "";
	/**
	 * Hidden Then By 1.
	 */
	private String hiddenThenBy1 = "";
	/**
	 * Then By Order 1Asc.
	 */
	private String thenByOrder1Asc = "checked";
	/**
	 * Then By Order 1Dsc.
	 */
	private String thenByOrder1Dsc = "";
	/**
	 * Then By Order1.
	 */
	private String thenByOrder1 = "";	
	/**
	 * Then By2.
	 */
	private String thenBy2 = "";
	/**
	 * HiddenThen By2.
	 */
	private String hiddenThenBy2 = "";
	/**
	 * Then By Order 2Asc.
	 */
	private String thenByOrder2Asc = "checked";
	/**
	 * Then By Order2 Dsc.
	 */
	private String thenByOrder2Dsc = "";
	/**
	 * Then By Order2.
	 */
	private String thenByOrder2 = "";
	/**
	 * Column Ordering.
	 */
	private String columnOrdering = "";
	/**
	 * Hidden Columns.
	 */
	private String hiddenColumns = "";
	
	//Tab 4
	
	/**
	 * Application Date Select.
	 */
	private String appliDateSelect = "";
	/**
	 * Application From Date.
	 */
	private String appliFromDate = "";
	/**
	 * Application To Date.
	 */
	private String appliToDate = "";
	/**
	 * Attendance Date Select.
	 */
	private String attDateSelect = "";
	/**
	 * Attendance From Date.
	 */
	private String attFromDate = "";
	/**
	 * Attendance To Date.
	 */
	private String attToDate = "";
	
	/**
	 * Display Options.
	 */
	
	/**
	 * HidReportView.
	 */
	private String hidReportView = "checked";
	/**
	 * HidReport Radio.
	 */
	private String hidReportRadio = "";
	/**
	 * Report Type.
	 */
	private String reportType = "";
	/**
	 * Setting Name.
	 */
	private String settingName = "";
	/**
	 * Request Status.
	 */
	private String reqStatus = "";
	/**
	 * My Page.
	 */
	private String myPage;
	/**
	 * Show.
	 */
	private String show;
	/**
	 * No Data.
	 */
	private String noData;
	/**
	 * Data Length.
	 */
	private String dataLength;
	/**
	 * Export All.
	 */
	private String exportAll = "";
	/**
	 * Tracking Number.
	 */
	private String trackingNumber = "";
	/**
	 * Emp First Name.
	 */
	private String empFirstName="";
	/**
	 *Action Reason
	 */
	private String actionReason = "";
	/**
	 * First Name Flag.
	 */
	private String firstNameFlag = "";
	/**
	 * Middle Name Flag.
	 */
	private String middleNameFlag = "";
	/**
	 * lastNameFlag.
	 */
	private String lastNameFlag = "";
	/**
	 *Social Security NumberFlag.
	 */
	private String socialSecurityNumberFlag = "";
	/**
	 * City Flag.
	 */
	private String cityFlag = "";
	/**
	 * State Flag.
	 */
	private String stateFlag = "";
	/**
	 * Phone Number Flag.
	 */
	private String phoneNumberFlag = "";
	/**
	 * Sex Flag.
	 */
	private String sexFlag = "";
	/**
	 * Martial Status Flag.
	 */
	private String martialStatusFlag = "";
	/**
	 * Education Flag.
	 */
	private String educationFlag = "";
	/**
	 * Birth Date Flag.
	 */
	private String birthDateFlag = "";
	/**
	 * Referal Source Flag.
	 */
	private String referalSourceFlag = "";
	/**
	 * Hire Date Flag.
	 */
	private String hireDateFlag = "";
	/**
	 * Physical Work Location Flag.
	 */
	private String physicalWorkLocationFlag = "";
	/**
	 * Action Reason Flag.
	 */
	private String actionReasonFlag = "";
	/**
	 * Grade Flag.
	 */
	private String gradeFlag = "";
	/**
	 * Pay Group Flag.
	 */
	private String payGroupFlag = "";
	/**
	 * Annual Salary Flag.
	 */
	private String annualSalaryFlag = "";
	/**
	 * Executive Employee Flag.
	 */
	private String executiveEmployeeFlag = "";
	/**
	 * Tracking Number Flag.
	 */
	private String trackingNumberFlag = "";
	/**
	 * Social Insurance Number Flag.
	 */
	private String socialInsuranceNumberFlag = "";
	
	/**
	 * Manager Flag.
	 */
	private String managerFlag = "";
	/**
	 * ATR Date Request Flag.
	 */
	private String atrDateRequestFlag = "";
	/**
	 * From Travel Date Flag.
	 */
	private String fromTravelDateFlag = "";
	/**
	 * To Travel Date Flag.
	 */
	private String toTravelDateFlag = "";
	/**
	 * Direct Billing Flag.
	 */
	private String directBillingFlag = "";
	/**
	 * Total Expense Flag.
	 */
	private String totalExpenseFlag = "";
	/**
	 * Name Flag.
	 */
	private String nameFlag = "";
	
	

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
	 * @return the logInFlag
	 */
	public String getLogInFlag() {
		return this.logInFlag;
	}
	/**
	 * @param logInFlag the logInFlag to set
	 */
	public void setLogInFlag(final String logInFlag) {
		this.logInFlag = logInFlag;
	}
	/**
	 * @return the lateHrsFlag
	 */
	public String getLateHrsFlag() {
		return this.lateHrsFlag;
	}
	/**
	 * @param lateHrsFlag the lateHrsFlag to set
	 */
	public void setLateHrsFlag(final String lateHrsFlag) {
		this.lateHrsFlag = lateHrsFlag;
	}
	/**
	 * @return the lateHrsDeductFlag
	 */
	public String getLateHrsDeductFlag() {
		return this.lateHrsDeductFlag;
	}
	/**
	 * @param lateHrsDeductFlag the lateHrsDeductFlag to set
	 */
	public void setLateHrsDeductFlag(final String lateHrsDeductFlag) {
		this.lateHrsDeductFlag = lateHrsDeductFlag;
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
		return hiddenColumns;
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
	 * @return the attDateSelect
	 */
	public String getAttDateSelect() {
		return this.attDateSelect;
	}
	/**
	 * @param attDateSelect the attDateSelect to set
	 */
	public void setAttDateSelect(final String attDateSelect) {
		this.attDateSelect = attDateSelect;
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
	public void setSettingName(final  String settingName) {
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
	 * @return the empFirstName
	 */
	public String getEmpFirstName() {
		return this.empFirstName;
	}
	/**
	 * @param empFirstName the empFirstName to set
	 */
	public void setEmpFirstName(final String empFirstName) {
		this.empFirstName = empFirstName;
	}
	/**
	 * @return the actionReason
	 */
	public String getActionReason() {
		return this.actionReason;
	}
	/**
	 * @param actionReason the actionReason to set
	 */
	public void setActionReason(final String actionReason) {
		this.actionReason = actionReason;
	}
	/**
	 * @return the firstNameFlag
	 */
	public String getFirstNameFlag() {
		return this.firstNameFlag;
	}
	/**
	 * @param firstNameFlag the firstNameFlag to set
	 */
	public void setFirstNameFlag(final String firstNameFlag) {
		this.firstNameFlag = firstNameFlag;
	}
	/**
	 * @return the middleNameFlag
	 */
	public String getMiddleNameFlag() {
		return this.middleNameFlag;
	}
	/**
	 * @param middleNameFlag the middleNameFlag to set
	 */
	public void setMiddleNameFlag(final String middleNameFlag) {
		this.middleNameFlag = middleNameFlag;
	}
	/**
	 * @return the lastNameFlag
	 */
	public String getLastNameFlag() {
		return this.lastNameFlag;
	}
	/**
	 * @param lastNameFlag the lastNameFlag to set
	 */
	public void setLastNameFlag(final String lastNameFlag) {
		this.lastNameFlag = lastNameFlag;
	}
	/**
	 * @return the socialSecurityNumberFlag
	 */
	public String getSocialSecurityNumberFlag() {
		return this.socialSecurityNumberFlag;
	}
	/**
	 * @param socialSecurityNumberFlag the socialSecurityNumberFlag to set
	 */
	public void setSocialSecurityNumberFlag(final String socialSecurityNumberFlag) {
		this.socialSecurityNumberFlag = socialSecurityNumberFlag;
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
	 * @return the stateFlag
	 */
	public String getStateFlag() {
		return this.stateFlag;
	}
	/**
	 * @param stateFlag the stateFlag to set
	 */
	public void setStateFlag(final String stateFlag) {
		this.stateFlag = stateFlag;
	}
	/**
	 * @return the phoneNumberFlag
	 */
	public String getPhoneNumberFlag() {
		return this.phoneNumberFlag;
	}
	/**
	 * @param phoneNumberFlag the phoneNumberFlag to set
	 */
	public void setPhoneNumberFlag(final String phoneNumberFlag) {
		this.phoneNumberFlag = phoneNumberFlag;
	}
	/**
	 * @return the sexFlag
	 */
	public String getSexFlag() {
		return this.sexFlag;
	}
	/**
	 * @param sexFlag the sexFlag to set
	 */
	public void setSexFlag(final String sexFlag) {
		this.sexFlag = sexFlag;
	}
	/**
	 * @return the martialStatusFlag
	 */
	public String getMartialStatusFlag() {
		return this.martialStatusFlag;
	}
	/**
	 * @param martialStatusFlag the martialStatusFlag to set
	 */
	public void setMartialStatusFlag(final String martialStatusFlag) {
		this.martialStatusFlag = martialStatusFlag;
	}
	/**
	 * @return the educationFlag
	 */
	public String getEducationFlag() {
		return this.educationFlag;
	}
	/**
	 * @param educationFlag the educationFlag to set
	 */
	public void setEducationFlag(final String educationFlag) {
		this.educationFlag = educationFlag;
	}
	/**
	 * @return the birthDateFlag
	 */
	public String getBirthDateFlag() {
		return this.birthDateFlag;
	}
	/**
	 * @param birthDateFlag the birthDateFlag to set
	 */
	public void setBirthDateFlag(final String birthDateFlag) {
		this.birthDateFlag = birthDateFlag;
	}
	/**
	 * @return the referalSourceFlag
	 */
	public String getReferalSourceFlag() {
		return this.referalSourceFlag;
	}
	/**
	 * @param referalSourceFlag the referalSourceFlag to set
	 */
	public void setReferalSourceFlag(final String referalSourceFlag) {
		this.referalSourceFlag = referalSourceFlag;
	}
	/**
	 * @return the hireDateFlag
	 */
	public String getHireDateFlag() {
		return this.hireDateFlag;
	}
	/**
	 * @param hireDateFlag the hireDateFlag to set
	 */
	public void setHireDateFlag(final String hireDateFlag) {
		this.hireDateFlag = hireDateFlag;
	}
	/**
	 * @return the physicalWorkLocationFlag
	 */
	public String getPhysicalWorkLocationFlag() {
		return this.physicalWorkLocationFlag;
	}
	/**
	 * @param physicalWorkLocationFlag the physicalWorkLocationFlag to set
	 */
	public void setPhysicalWorkLocationFlag(final String physicalWorkLocationFlag) {
		this.physicalWorkLocationFlag = physicalWorkLocationFlag;
	}
	/**
	 * @return the actionReasonFlag
	 */
	public String getActionReasonFlag() {
		return this.actionReasonFlag;
	}
	/**
	 * @param actionReasonFlag the actionReasonFlag to set
	 */
	public void setActionReasonFlag(final String actionReasonFlag) {
		this.actionReasonFlag = actionReasonFlag;
	}
	/**
	 * @return the gradeFlag
	 */
	public String getGradeFlag() {
		return this.gradeFlag;
	}
	/**
	 * @param gradeFlag the gradeFlag to set
	 */
	public void setGradeFlag(final String gradeFlag) {
		this.gradeFlag = gradeFlag;
	}
	/**
	 * @return the payGroupFlag
	 */
	public String getPayGroupFlag() {
		return this.payGroupFlag;
	}
	/**
	 * @param payGroupFlag the payGroupFlag to set
	 */
	public void setPayGroupFlag(final String payGroupFlag) {
		this.payGroupFlag = payGroupFlag;
	}
	/**
	 * @return the annualSalaryFlag
	 */
	public String getAnnualSalaryFlag() {
		return this.annualSalaryFlag;
	}
	/**
	 * @param annualSalaryFlag the annualSalaryFlag to set
	 */
	public void setAnnualSalaryFlag(final String annualSalaryFlag) {
		this.annualSalaryFlag = annualSalaryFlag;
	}
	/**
	 * @return the executiveEmployeeFlag
	 */
	public String getExecutiveEmployeeFlag() {
		return this.executiveEmployeeFlag;
	}
	/**
	 * @param executiveEmployeeFlag the executiveEmployeeFlag to set
	 */
	public void setExecutiveEmployeeFlag(final String executiveEmployeeFlag) {
		this.executiveEmployeeFlag = executiveEmployeeFlag;
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
	 * @return the socialInsuranceNumberFlag
	 */
	public String getSocialInsuranceNumberFlag() {
		return this.socialInsuranceNumberFlag;
	}
	/**
	 * @param socialInsuranceNumberFlag the socialInsuranceNumberFlag to set
	 */
	public void setSocialInsuranceNumberFlag(final String socialInsuranceNumberFlag) {
		this.socialInsuranceNumberFlag = socialInsuranceNumberFlag;
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
	 * @return the atrDateRequestFlag
	 */
	public String getAtrDateRequestFlag() {
		return this.atrDateRequestFlag;
	}
	/**
	 * @param atrDateRequestFlag the atrDateRequestFlag to set
	 */
	public void setAtrDateRequestFlag(final String atrDateRequestFlag) {
		this.atrDateRequestFlag = atrDateRequestFlag;
	}
	/**
	 * @return the fromTravelDateFlag
	 */
	public String getFromTravelDateFlag() {
		return this.fromTravelDateFlag;
	}
	/**
	 * @param fromTravelDateFlag the fromTravelDateFlag to set
	 */
	public void setFromTravelDateFlag(final String fromTravelDateFlag) {
		this.fromTravelDateFlag = fromTravelDateFlag;
	}
	/**
	 * @return the toTravelDateFlag
	 */
	public String getToTravelDateFlag() {
		return this.toTravelDateFlag;
	}
	/**
	 * @param toTravelDateFlag the toTravelDateFlag to set
	 */
	public void setToTravelDateFlag(final String toTravelDateFlag) {
		this.toTravelDateFlag = toTravelDateFlag;
	}
	/**
	 * @return the directBillingFlag
	 */
	public String getDirectBillingFlag() {
		return this.directBillingFlag;
	}
	/**
	 * @param directBillingFlag the directBillingFlag to set
	 */
	public void setDirectBillingFlag(final String directBillingFlag) {
		this.directBillingFlag = directBillingFlag;
	}
	/**
	 * @return the totalExpenseFlag
	 */
	public String getTotalExpenseFlag() {
		return this.totalExpenseFlag;
	}
	/**
	 * @param totalExpenseFlag the totalExpenseFlag to set
	 */
	public void setTotalExpenseFlag(final String totalExpenseFlag) {
		this.totalExpenseFlag = totalExpenseFlag;
	}
	/**
	 * @return the nameFlag
	 */
	public String getNameFlag() {
		return this.nameFlag;
	}
	/**
	 * @param nameFlag the nameFlag to set
	 */
	public void setNameFlag(final String nameFlag) {
		this.nameFlag = nameFlag;
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
	
}
