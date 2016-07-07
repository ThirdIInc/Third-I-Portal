package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

public class WorkCompInjuryIllnessMISReport extends BeanBase {
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

	//Text fields name & hidden Ids
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
	 * Manager Name.
	 */
	private String managerName = "";
	/**
	 * Manager Id.
	 */
	private String managerId = "";
	/**
	 * Tracking Number.
	 */
	private String trackingNumber = "";
	/**
	 * Tracking Id.
	 */
	private String trackingId = "";
	/**
	 * Emp Status.
	 */
	private String empstatus = "";
	/**
	 * Appl Status.
	 */
	private String applStatus = "";

	

	/**
	 *  Flags Start for Check Boxes.
	 */
	
	/**
	 * Emp Name Flag.
	 */
	private String empNameFlag = "";
	/**
	 * Division Name Flag.
	 */
	private String divFlag = "";
	/**
	 * Department Name Flag.
	 */
	private String deptFlag = "";
	/**
	 * Branch Name Flag.
	 */
	private String branchFlag = "";
	/**
	 * Designation Name Flag.
	 */
	private String desigFlag = "";
	/**
	 * Employee Type Flag.
	 */
	private String empTypeFlag = "";
	/**
	 * Manager Name  Flag.
	 */
	private String managerNameFlag = "";
	/**
	 * Tracking Number Flag.
	 */
	private String trackingNumberFlag = "";
	/**
	 * Date of injury flag.
	 */
	private String dateofInjuryFlag = "";
	/**
	 * aaaaaFlag.
	 */
	private String aaaaaFlag = "";
	/**
	 * Time of injury flag.
	 */
	private String timeofInjuryFlag = "";
	/**
	 * city flag.
	 */
	private String cityFlag = "";
	/**
	 * number of dependant flag.
	 */
	private String numberofDependantFlag = "";
	/**
	 * Working hours flag.
	 */
	private String workinghoursToFlag = "";
	/**
	 * injury date flag.
	 */
	private String injuryDateFlag = "";
	/**
	 * hours worked flag.
	 */
	private String hoursWorkedFlag = "";
	/**
	 * normal working hours flag.
	 */
	private String normalworkingHoursFlag = "";
	/**
	 * Injuered return flag.
	 */
	private String injueredreturnFlag = "";
	/**
	 * disebility flag.
	 */
	private String disebilityFlag = "";
	/**
	 * loass of work days flag.
	 */
	private String lossworkDaysFlag = "";
	/**
	 * loat flag.
	 */
	private String lostFlag = "";
	/**
	 * bbbbbFlag.
	 */
	private String bbbbbFlag = "";
	/**
	 * Appl Date Flag.
	 */
	private String applDateFlag = "";
	/**
	 * Appl Status flag.
	 */
	private String applStatusFlag = "";

	///not used flag
	/**
	 * Ststus Flag.
	 */
	private String statusFlag = "";
	

	
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
	private String sortByAsc = WorkCompInjuryIllnessMISReport.CHECKED;
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
	private String thenByOrder1Asc = WorkCompInjuryIllnessMISReport.CHECKED;
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
	private String thenByOrder2Asc = WorkCompInjuryIllnessMISReport.CHECKED;
	/**
	* thenByOrder2Dsc.
	*/
	private String thenByOrder2Dsc = "";
	/**
	*  thenByOrder2.
	*/
	private String thenByOrder2 = "";

	/**
	*Column Ordering.
	*/
	private String columnOrdering = "";
	/**
	* Hidden Columns.
	*/
	private String hiddenColumns = "";

	// Advanced Filters Start
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
	 * Injury Date Select.
	 */
	private String injuryDateSelect = "";
	/**
	 * Injury From Date.
	 */
	private String injuryFromDate = "";
	/**
	 * Injury To Date.
	 */
	private String injuryToDate = "";
	/**
	 * Approval Date Select.
	 */
	private String approvalDateSelect = "";
	/**
	 * Approval From Date.
	 */
	private String approvalFromDate = "";
	/**
	 * Approval To Date.
	 */
	private String approvalToDate = "";

	/**
	 *  Advanced Filters End - Display Options.
	 */
 
	/**
	 * Hidden Report View.
	 */
	private String hidReportView = WorkCompInjuryIllnessMISReport.CHECKED;
	/**
	 * Hidden Report Radiao.
	 */
	private String hidReportRadio = "";
	/**
	 * Reort Type.
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
	 * @return the backFlag
	 */
	public String getBackFlag() {
		return this.backFlag;
	}

	/**
	 * @param backFlag
	 *            the backFlag to set
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
	 * @param savedReport
	 *            the savedReport to set
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
	 * @param reportId
	 *            the reportId to set
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
	 * @param reportTitle
	 *            the reportTitle to set
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
	 * @param divName
	 *            the divName to set
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
	 * @param divId
	 *            the divId to set
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
	 * @param deptName
	 *            the deptName to set
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
	 * @param deptId
	 *            the deptId to set
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
	 * @param branchName
	 *            the branchName to set
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
	 * @param branchId
	 *            the branchId to set
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
	 * @param empTypeName
	 *            the empTypeName to set
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
	 * @param empTypeId
	 *            the empTypeId to set
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
	 * @param desigName
	 *            the desigName to set
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
	 * @param desigId
	 *            the desigId to set
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
	 * @param empToken
	 *            the empToken to set
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
	 * @param empName
	 *            the empName to set
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
	 * @param empId the empId to set.
	 *            
	 */
	public void setEmpId(final String empId) {
		this.empId = empId;
	}

	/**
	 * @return the status
	 */
	public String getEmpstatus() {
		return this.empstatus;
	}

	/**
	 * @param empstatus the empstatus to set.
	 */
	public void setEmpstatus(final String empstatus) {
		this.empstatus = empstatus;
	}

	/**
	 * @return the empNameFlag
	 */
	public String getEmpNameFlag() {
		return this.empNameFlag;
	}

	/**
	 * @param empNameFlag the empNameFlag to set.
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
	 * @param divFlag the divFlag to set.
	 */
	public void setDivFlag(final String divFlag) {
		this.divFlag = divFlag;
	}

	/**
	 * @return the deptFlag.
	 */
	public String getDeptFlag() {
		return this.deptFlag;
	}

	/**
	 * @param deptFlag the deptFlag to set. 
	 */
	public void setDeptFlag(final String deptFlag) {
		this.deptFlag = deptFlag;
	}

	/**
	 * @return the branchFlag.
	 */
	public String getBranchFlag() {
		return this.branchFlag;
	}

	/**
	 * @param branchFlag the branchFlag to set.
	 */
	public void setBranchFlag(final String branchFlag) {
		this.branchFlag = branchFlag;
	}

	/**
	 * @return the desigFlag.
	 */
	public String getDesigFlag() {
		return this.desigFlag;
	}

	/**
	 * @param desigFlag  the desigFlag to set.
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
	 * @param empTypeFlag
	 *            the empTypeFlag to set
	 */
	public void setEmpTypeFlag(final String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}

	/**
	 * @return the statusFlag.
	 */
	public String getStatusFlag() {
		return this.statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set.
	 */
	public void setStatusFlag(final String statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * @return the managerNameFlag.
	 */
	public String getManagerNameFlag() {
		return this.managerNameFlag;
	}

	/**
	 * @param managerNameFlag the managerNameFlag to set.
	 */
	public void setManagerNameFlag(final String managerNameFlag) {
		this.managerNameFlag = managerNameFlag;
	}

	/**
	 * @return the trackingNumberFlag.
	 */
	public String getTrackingNumberFlag() {
		return this.trackingNumberFlag;
	}

	/**
	 * @param trackingNumberFlag the trackingNumberFlag.
	 */
	public void setTrackingNumberFlag(final String trackingNumberFlag) {
		this.trackingNumberFlag = trackingNumberFlag;
	}

	/**
	 * @return the numberofDependantFlag.
	 */
	public String getNumberofDependantFlag() {
		return this.numberofDependantFlag;
	}

	/**
	 * @param numberofDependantFlag the numberofDependantFlag to set.
	 */
	public void setNumberofDependantFlag(final String numberofDependantFlag) {
		this.numberofDependantFlag = numberofDependantFlag;
	}

	/**
	 * @return the dateofInjuryFlag.
	 */
	public String getDateofInjuryFlag() {
		return this.dateofInjuryFlag;
	}

	/**
	 * @param dateofInjuryFlag the dateofInjuryFlag to set.
	 */
	public void setDateofInjuryFlag(final String dateofInjuryFlag) {
		this.dateofInjuryFlag = dateofInjuryFlag;
	}

	/**
	 * @return the timeofInjuryFlag.
	 */
	public String getTimeofInjuryFlag() {
		return this.timeofInjuryFlag;
	}

	/**
	 * @param timeofInjuryFlag the timeofInjuryFlag to set.
	 */
	public void setTimeofInjuryFlag(final String timeofInjuryFlag) {
		this.timeofInjuryFlag = timeofInjuryFlag;
	}

	/**
	 * @return the hoursWorkedFlag.
	 */
	public String getHoursWorkedFlag() {
		return this.hoursWorkedFlag;
	}

	/**
	 * @param hoursWorkedFlag the hoursWorkedFlag to set.
	 */
	public void setHoursWorkedFlag(final String hoursWorkedFlag) {
		this.hoursWorkedFlag = hoursWorkedFlag;
	}

	/**
	 * @return the normalworkingHoursFlag.
	 */
	public String getNormalworkingHoursFlag() {
		return this.normalworkingHoursFlag;
	}

	/**
	 * @param normalworkingHoursFlag the normalworkingHoursFlag to set.
	 */
	public void setNormalworkingHoursFlag(final String normalworkingHoursFlag) {
		this.normalworkingHoursFlag = normalworkingHoursFlag;
	}

	/**
	 * @return the workinghoursToFlag
	 */
	public String getWorkinghoursToFlag() {
		return this.workinghoursToFlag;
	}

	/**
	 * @param workinghoursToFlag the workinghoursToFlag to set.
	 */
	public void setWorkinghoursToFlag(final String workinghoursToFlag) {
		this.workinghoursToFlag = workinghoursToFlag;
	}

	/**
	 * @return the injuryDateFlag.
	 */
	public String getInjuryDateFlag() {
		return this.injuryDateFlag;
	}

	/**
	 * @param injuryDateFlag the injuryDateFlag to set.
	 */
	public void setInjuryDateFlag(final String injuryDateFlag) {
		this.injuryDateFlag = injuryDateFlag;
	}

	/**
	 * @return the lossworkDaysFlag.
	 */
	public String getLossworkDaysFlag() {
		return this.lossworkDaysFlag;
	}

	/**
	 * @param lossworkDaysFlag the lossworkDaysFlag to set.
	 */
	public void setLossworkDaysFlag(final String lossworkDaysFlag) {
		this.lossworkDaysFlag = lossworkDaysFlag;
	}

	/**
	 * @return the lostFlag.
	 */
	public String getLostFlag() {
		return this.lostFlag;
	}

	/**
	 * @param lostFlag the lostFlag to set.
	 */
	public void setLostFlag(final String lostFlag) {
		this.lostFlag = lostFlag;
	}

	/**
	 * @return the disebilityFlag
	 */
	public String getDisebilityFlag() {
		return this.disebilityFlag;
	}

	/**
	 * @param disebilityFlag the disebilityFlag to set.
	 */
	public void setDisebilityFlag(final String disebilityFlag) {
		this.disebilityFlag = disebilityFlag;
	}

	/**
	 * @return the applDateFlag.
	 */
	public String getApplDateFlag() {
		return this.applDateFlag;
	}

	/**
	 * @param applDateFlag the applDateFlag to set.
	 */
	public void setApplDateFlag(final String applDateFlag) {
		this.applDateFlag = applDateFlag;
	}

	/**
	 * @return the sortBy.
	 */
	public String getSortBy() {
		return this.sortBy;
	}

	/**
	 * @param sortBy the sortBy to set. 
	 */
	public void setSortBy(final String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the hiddenSortBy.
	 */
	public String getHiddenSortBy() {
		return this.hiddenSortBy;
	}

	/**
	 * @param hiddenSortBy  the hiddenSortBy to set.
	 */
	public void setHiddenSortBy(final String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}

	/**
	 * @return the sortByAsc  the sortByAsc to set
	 */
	public String getSortByAsc() {
		return this.sortByAsc;
	}

	/**
	 * @param sortByAsc
	 *            the sortByAsc to set
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
	 * @param sortByDsc
	 *            the sortByDsc to set
	 */
	public void setSortByDsc(final  String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}

	/**
	 * @return the sortByOrder
	 */
	public String getSortByOrder() {
		return this.sortByOrder;
	}

	/**
	 * @param sortByOrder
	 *            the sortByOrder to set
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
	 * @param thenBy1
	 *            the thenBy1 to set
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
	 * @param hiddenThenBy1
	 *            the hiddenThenBy1 to set
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
	 * @param thenByOrder1Asc
	 *            the thenByOrder1Asc to set
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
	 * @param thenByOrder1Dsc
	 *            the thenByOrder1Dsc to set
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
	 * @param thenByOrder1
	 *            the thenByOrder1 to set
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
	 * @param thenBy2
	 *            the thenBy2 to set
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
	 * @param hiddenThenBy2
	 *            the hiddenThenBy2 to set
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
	 * @param thenByOrder2Asc
	 *            the thenByOrder2Asc to set
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
	 * @param thenByOrder2Dsc
	 *            the thenByOrder2Dsc to set
	 */
	public void setThenByOrder2Dsc(final  String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}

	/**
	 * @return the thenByOrder2
	 */
	public String getThenByOrder2() {
		return this.thenByOrder2;
	}

	/**
	 * @param thenByOrder2
	 *            the thenByOrder2 to set
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
	 * @param columnOrdering
	 *            the columnOrdering to set
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
	 * @param hiddenColumns
	 *            the hiddenColumns to set
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
	 * @param appliDateSelect
	 *            the appliDateSelect to set
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
	 * @param appliFromDate
	 *            the appliFromDate to set
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
	 * @param appliToDate
	 *            the appliToDate to set
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
	 * @param hidReportView
	 *            the hidReportView to set
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
	 * @param hidReportRadio
	 *            the hidReportRadio to set
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
	 * @param reportType
	 *            the reportType to set
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
	 * @param settingName
	 *            the settingName to set
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
	 * @param reqStatus
	 *            the reqStatus to set
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
	 * @param myPage
	 *            the myPage to set
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
	 * @param show
	 *            the show to set
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
	 * @param noData
	 *            the noData to set
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
	 * @param dataLength
	 *            the dataLength to set
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
	 * @param exportAll
	 *            the exportAll to set
	 */
	public void setExportAll(final String exportAll) {
		this.exportAll = exportAll;
	}

	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return this.managerName;
	}

	/**
	 * @param managerName
	 *            the managerName to set
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
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(final String managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return this.trackingNumber;
	}

	/**
	 * @param trackingNumber
	 *            the trackingNumber to set
	 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return this.trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(final String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the applStatus
	 */
	public String getApplStatus() {
		return this.applStatus;
	}

	/**
	 * @param applStatus
	 *            the applStatus to set
	 */
	public void setApplStatus(final String applStatus) {
		this.applStatus = applStatus;
	}

	/**
	 * @return the injuryDateSelect
	 */
	public String getInjuryDateSelect() {
		return this.injuryDateSelect;
	}

	/**
	 * @param injuryDateSelect
	 *            the injuryDateSelect to set
	 */
	public void setInjuryDateSelect(final String injuryDateSelect) {
		this.injuryDateSelect = injuryDateSelect;
	}

	/**
	 * @return the injuryFromDate
	 */
	public String getInjuryFromDate() {
		return this.injuryFromDate;
	}

	/**
	 * @param injuryFromDate
	 *            the injuryFromDate to set
	 */
	public void setInjuryFromDate(final String injuryFromDate) {
		this.injuryFromDate = injuryFromDate;
	}

	/**
	 * @return the injuryToDate
	 */
	public String getInjuryToDate() {
		return this.injuryToDate;
	}

	/**
	 * @param injuryToDate
	 *            the injuryToDate to set
	 */
	public void setInjuryToDate(final String injuryToDate) {
		this.injuryToDate = injuryToDate;
	}

	/**
	 * @return the approvalDateSelect
	 */
	public String getApprovalDateSelect() {
		return this.approvalDateSelect;
	}

	/**
	 * @param approvalDateSelect
	 *            the approvalDateSelect to set
	 */
	public void setApprovalDateSelect(final String approvalDateSelect) {
		this.approvalDateSelect = approvalDateSelect;
	}

	/**
	 * @return the approvalFromDate
	 */
	public String getApprovalFromDate() {
		return this.approvalFromDate;
	}

	/**
	 * @param approvalFromDate
	 *            the approvalFromDate to set
	 */
	public void setApprovalFromDate(final String approvalFromDate) {
		this.approvalFromDate = approvalFromDate;
	}

	/**
	 * @return the approvalToDate
	 */
	public String getApprovalToDate() {
		return this.approvalToDate;
	}

	/**
	 * @param approvalToDate the approvalToDate to set
	 */
	public void setApprovalToDate(final String approvalToDate) {
		this.approvalToDate = approvalToDate;
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
	 * @return the injueredreturnFlag
	 */
	public String getInjueredreturnFlag() {
		return this.injueredreturnFlag;
	}

	/**
	 * @param injueredreturnFlag the injueredreturnFlag to set
	 */
	public void setInjueredreturnFlag(final String injueredreturnFlag) {
		this.injueredreturnFlag = injueredreturnFlag;
	}

	/**
	 * @return the aaaaaFlag
	 */
	public String getAaaaaFlag() {
		return this.aaaaaFlag;
	}

	/**
	 * @param aaaaaFlag the aaaaaFlag to set
	 */
	public void setAaaaaFlag(final String aaaaaFlag) {
		this.aaaaaFlag = aaaaaFlag;
	}

	/**
	 * @return the bbbbbFlag
	 */
	public String getBbbbbFlag() {
		return this.bbbbbFlag;
	}

	/**
	 * @param bbbbbFlag the bbbbbFlag to set
	 */
	public void setBbbbbFlag(final String bbbbbFlag) {
		this.bbbbbFlag = bbbbbFlag;
	}

}
