package org.paradyne.bean.TravelManagement.TravelReports;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.lib.BeanBase;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlApprvrReport extends BeanBase {

	private String empToken = "";
	private String empCode = "";
	private String empName = "";
	private String applStatus = "";
	private String apprvrAmtSelect = "";
	private String apprvrAmtFrom = "";
	private String apprvrAmtTo = "";
	private String apprvdDateSelect = "";
	private String apprvdDateFrm = "";
	private String apprvdDateTo = "";
	private String applFor = "";

	private String travelCheckVal = "";
	private String travelCheck = "";
	private String hidTravelSelf = "";
	private String trvlSelf = "";
	private String hidTravelComp = "";
	private String trvlComp = "";
	private String accomCheckVal = "";
	private String accomCheck = "";
	private String hidAccomSelf = "";
	private String accomSelf = "";
	private String hidAccomComp = "";
	private String accomComp = "";
	private String localCheckVal = "";
	private String localCheck = "";
	private String hidLocalSelf = "";
	private String localSelf = "";
	private String hidLocalComp = "";
	private String localComp = "";

	private String empIdFlag = "";
	private String trvlTypeFlag = "";
	private String advAmtFlag = "";
	private String empNameFlag = "";
	private String applDateFlag = "";
	private String apprvdAmtFlag = "";
	private String travelIdFlag = "";
	private String travelStrtDateFlag = "";
	private String polViolatedFlag = "";
	private String trvlPurposeFlag = "";
	private String travelEndDateFlag = "";

	private String sortBy = "";
	private String hiddenSortBy = "";
	private String sortByOrder = "";
	private String thenBy1 = "";
	private String hiddenThenBy1 = "";
	private String thenByOrder1 = "";
	private String thenByOrder2 = "";
	private String thenBy2 = "";
	private String hiddenThenBy2 = "";

	private String sortByAsc = "checked";
	private String sortByDsc = "";
	private String thenByOrder1Asc = "checked";
	private String thenByOrder1Dsc = "";
	private String thenByOrder2Asc = "checked";
	private String thenByOrder2Dsc = "";

	// for advance filter options

	private String applDateSelect = "";
	private String DOAfromDate = "";
	private String DOAToDate = "";
	private String schTrvlDateSelect = "";
	private String STDfromDate = "";
	private String STDToDate = "";
	private String trvlStrtDateSelect = "";
	private String TSDfromDate = "";
	private String TSDToDate = "";
	private String trvlEndDateSelect = "";
	private String TEDfromDate = "";
	private String TEDToDate = "";
	private String schCycleSelect = "";
	private String schCycleFrom = "";
	private String schCycleTo = "";
	private String trvlType = "";
	private LinkedMap trvlTypeMap;
	private String gradeId = "";
	private String gradeName = "";
	private String brnchCode = "";
	private String branchName = "";
	private String deptCode = "";
	private String deptName = "";

	private String reportType = "";
	private String reportTitle = "";
	private String settingName = "";
	private String reportId = "";

	private String columnOrdering = "";
	private String hiddenColumns = "";

	private String reqStatus = "";

	private String myPage = "";
	private String show = "";
	private String dataLength = "";
	private String hidReportView = "checked";
	private String hidReportRadio = "";
	private String exportAll = "";
	private String noData = "false";

	private String backFlag = "";

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
	}

	/**
	 * @param reportTitle
	 *            the reportTitle to set
	 */
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	/**
	 * @return the settingName
	 */
	public String getSettingName() {
		return settingName;
	}

	/**
	 * @param settingName
	 *            the settingName to set
	 */
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 *            the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the empIdFlag
	 */
	public String getEmpIdFlag() {
		return empIdFlag;
	}

	/**
	 * @param empIdFlag
	 *            the empIdFlag to set
	 */
	public void setEmpIdFlag(String empIdFlag) {
		this.empIdFlag = empIdFlag;
	}

	/**
	 * @return the trvlTypeFlag
	 */
	public String getTrvlTypeFlag() {
		return trvlTypeFlag;
	}

	/**
	 * @param trvlTypeFlag
	 *            the trvlTypeFlag to set
	 */
	public void setTrvlTypeFlag(String trvlTypeFlag) {
		this.trvlTypeFlag = trvlTypeFlag;
	}

	/**
	 * @return the advAmtFlag
	 */
	public String getAdvAmtFlag() {
		return advAmtFlag;
	}

	/**
	 * @param advAmtFlag
	 *            the advAmtFlag to set
	 */
	public void setAdvAmtFlag(String advAmtFlag) {
		this.advAmtFlag = advAmtFlag;
	}

	/**
	 * @return the empNameFlag
	 */
	public String getEmpNameFlag() {
		return empNameFlag;
	}

	/**
	 * @param empNameFlag
	 *            the empNameFlag to set
	 */
	public void setEmpNameFlag(String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}

	/**
	 * @return the applDateFlag
	 */
	public String getApplDateFlag() {
		return applDateFlag;
	}

	/**
	 * @param applDateFlag
	 *            the applDateFlag to set
	 */
	public void setApplDateFlag(String applDateFlag) {
		this.applDateFlag = applDateFlag;
	}

	/**
	 * @return the apprvdAmtFlag
	 */
	public String getApprvdAmtFlag() {
		return apprvdAmtFlag;
	}

	/**
	 * @param apprvdAmtFlag
	 *            the apprvdAmtFlag to set
	 */
	public void setApprvdAmtFlag(String apprvdAmtFlag) {
		this.apprvdAmtFlag = apprvdAmtFlag;
	}

	/**
	 * @return the travelIdFlag
	 */
	public String getTravelIdFlag() {
		return travelIdFlag;
	}

	/**
	 * @param travelIdFlag
	 *            the travelIdFlag to set
	 */
	public void setTravelIdFlag(String travelIdFlag) {
		this.travelIdFlag = travelIdFlag;
	}

	/**
	 * @return the travelStrtDateFlag
	 */
	public String getTravelStrtDateFlag() {
		return travelStrtDateFlag;
	}

	/**
	 * @param travelStrtDateFlag
	 *            the travelStrtDateFlag to set
	 */
	public void setTravelStrtDateFlag(String travelStrtDateFlag) {
		this.travelStrtDateFlag = travelStrtDateFlag;
	}

	/**
	 * @return the polViolatedFlag
	 */
	public String getPolViolatedFlag() {
		return polViolatedFlag;
	}

	/**
	 * @param polViolatedFlag
	 *            the polViolatedFlag to set
	 */
	public void setPolViolatedFlag(String polViolatedFlag) {
		this.polViolatedFlag = polViolatedFlag;
	}

	/**
	 * @return the trvlPurposeFlag
	 */
	public String getTrvlPurposeFlag() {
		return trvlPurposeFlag;
	}

	/**
	 * @param trvlPurposeFlag
	 *            the trvlPurposeFlag to set
	 */
	public void setTrvlPurposeFlag(String trvlPurposeFlag) {
		this.trvlPurposeFlag = trvlPurposeFlag;
	}

	/**
	 * @return the travelEndDateFlag
	 */
	public String getTravelEndDateFlag() {
		return travelEndDateFlag;
	}

	/**
	 * @param travelEndDateFlag
	 *            the travelEndDateFlag to set
	 */
	public void setTravelEndDateFlag(String travelEndDateFlag) {
		this.travelEndDateFlag = travelEndDateFlag;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the applStatus
	 */
	public String getApplStatus() {
		return applStatus;
	}

	/**
	 * @param applStatus
	 *            the applStatus to set
	 */
	public void setApplStatus(String applStatus) {
		this.applStatus = applStatus;
	}

	/**
	 * @return the apprvrAmtSelect
	 */
	public String getApprvrAmtSelect() {
		return apprvrAmtSelect;
	}

	/**
	 * @param apprvrAmtSelect
	 *            the apprvrAmtSelect to set
	 */
	public void setApprvrAmtSelect(String apprvrAmtSelect) {
		this.apprvrAmtSelect = apprvrAmtSelect;
	}

	/**
	 * @return the apprvrAmtFrom
	 */
	public String getApprvrAmtFrom() {
		return apprvrAmtFrom;
	}

	/**
	 * @param apprvrAmtFrom
	 *            the apprvrAmtFrom to set
	 */
	public void setApprvrAmtFrom(String apprvrAmtFrom) {
		this.apprvrAmtFrom = apprvrAmtFrom;
	}

	/**
	 * @return the apprvrAmtTo
	 */
	public String getApprvrAmtTo() {
		return apprvrAmtTo;
	}

	/**
	 * @param apprvrAmtTo
	 *            the apprvrAmtTo to set
	 */
	public void setApprvrAmtTo(String apprvrAmtTo) {
		this.apprvrAmtTo = apprvrAmtTo;
	}

	/**
	 * @return the apprvdDateSelect
	 */
	public String getApprvdDateSelect() {
		return apprvdDateSelect;
	}

	/**
	 * @param apprvdDateSelect
	 *            the apprvdDateSelect to set
	 */
	public void setApprvdDateSelect(String apprvdDateSelect) {
		this.apprvdDateSelect = apprvdDateSelect;
	}

	/**
	 * @return the apprvdDateFrm
	 */
	public String getApprvdDateFrm() {
		return apprvdDateFrm;
	}

	/**
	 * @param apprvdDateFrm
	 *            the apprvdDateFrm to set
	 */
	public void setApprvdDateFrm(String apprvdDateFrm) {
		this.apprvdDateFrm = apprvdDateFrm;
	}

	/**
	 * @return the apprvdDateTo
	 */
	public String getApprvdDateTo() {
		return apprvdDateTo;
	}

	/**
	 * @param apprvdDateTo
	 *            the apprvdDateTo to set
	 */
	public void setApprvdDateTo(String apprvdDateTo) {
		this.apprvdDateTo = apprvdDateTo;
	}

	/**
	 * @return the applFor
	 */
	public String getApplFor() {
		return applFor;
	}

	/**
	 * @param applFor
	 *            the applFor to set
	 */
	public void setApplFor(String applFor) {
		this.applFor = applFor;
	}

	/**
	 * @return the travelCheckVal
	 */
	public String getTravelCheckVal() {
		return travelCheckVal;
	}

	/**
	 * @param travelCheckVal
	 *            the travelCheckVal to set
	 */
	public void setTravelCheckVal(String travelCheckVal) {
		this.travelCheckVal = travelCheckVal;
	}

	/**
	 * @return the travelCheck
	 */
	public String getTravelCheck() {
		return travelCheck;
	}

	/**
	 * @param travelCheck
	 *            the travelCheck to set
	 */
	public void setTravelCheck(String travelCheck) {
		this.travelCheck = travelCheck;
	}

	/**
	 * @return the hidTravelSelf
	 */
	public String getHidTravelSelf() {
		return hidTravelSelf;
	}

	/**
	 * @param hidTravelSelf
	 *            the hidTravelSelf to set
	 */
	public void setHidTravelSelf(String hidTravelSelf) {
		this.hidTravelSelf = hidTravelSelf;
	}

	/**
	 * @return the trvlSelf
	 */
	public String getTrvlSelf() {
		return trvlSelf;
	}

	/**
	 * @param trvlSelf
	 *            the trvlSelf to set
	 */
	public void setTrvlSelf(String trvlSelf) {
		this.trvlSelf = trvlSelf;
	}

	/**
	 * @return the hidTravelComp
	 */
	public String getHidTravelComp() {
		return hidTravelComp;
	}

	/**
	 * @param hidTravelComp
	 *            the hidTravelComp to set
	 */
	public void setHidTravelComp(String hidTravelComp) {
		this.hidTravelComp = hidTravelComp;
	}

	/**
	 * @return the trvlComp
	 */
	public String getTrvlComp() {
		return trvlComp;
	}

	/**
	 * @param trvlComp
	 *            the trvlComp to set
	 */
	public void setTrvlComp(String trvlComp) {
		this.trvlComp = trvlComp;
	}

	/**
	 * @return the accomCheckVal
	 */
	public String getAccomCheckVal() {
		return accomCheckVal;
	}

	/**
	 * @param accomCheckVal
	 *            the accomCheckVal to set
	 */
	public void setAccomCheckVal(String accomCheckVal) {
		this.accomCheckVal = accomCheckVal;
	}

	/**
	 * @return the accomCheck
	 */
	public String getAccomCheck() {
		return accomCheck;
	}

	/**
	 * @param accomCheck
	 *            the accomCheck to set
	 */
	public void setAccomCheck(String accomCheck) {
		this.accomCheck = accomCheck;
	}

	/**
	 * @return the hidAccomSelf
	 */
	public String getHidAccomSelf() {
		return hidAccomSelf;
	}

	/**
	 * @param hidAccomSelf
	 *            the hidAccomSelf to set
	 */
	public void setHidAccomSelf(String hidAccomSelf) {
		this.hidAccomSelf = hidAccomSelf;
	}

	/**
	 * @return the accomSelf
	 */
	public String getAccomSelf() {
		return accomSelf;
	}

	/**
	 * @param accomSelf
	 *            the accomSelf to set
	 */
	public void setAccomSelf(String accomSelf) {
		this.accomSelf = accomSelf;
	}

	/**
	 * @return the hidAccomComp
	 */
	public String getHidAccomComp() {
		return hidAccomComp;
	}

	/**
	 * @param hidAccomComp
	 *            the hidAccomComp to set
	 */
	public void setHidAccomComp(String hidAccomComp) {
		this.hidAccomComp = hidAccomComp;
	}

	/**
	 * @return the accomComp
	 */
	public String getAccomComp() {
		return accomComp;
	}

	/**
	 * @param accomComp
	 *            the accomComp to set
	 */
	public void setAccomComp(String accomComp) {
		this.accomComp = accomComp;
	}

	/**
	 * @return the localCheckVal
	 */
	public String getLocalCheckVal() {
		return localCheckVal;
	}

	/**
	 * @param localCheckVal
	 *            the localCheckVal to set
	 */
	public void setLocalCheckVal(String localCheckVal) {
		this.localCheckVal = localCheckVal;
	}

	/**
	 * @return the localCheck
	 */
	public String getLocalCheck() {
		return localCheck;
	}

	/**
	 * @param localCheck
	 *            the localCheck to set
	 */
	public void setLocalCheck(String localCheck) {
		this.localCheck = localCheck;
	}

	/**
	 * @return the hidLocalSelf
	 */
	public String getHidLocalSelf() {
		return hidLocalSelf;
	}

	/**
	 * @param hidLocalSelf
	 *            the hidLocalSelf to set
	 */
	public void setHidLocalSelf(String hidLocalSelf) {
		this.hidLocalSelf = hidLocalSelf;
	}

	/**
	 * @return the localSelf
	 */
	public String getLocalSelf() {
		return localSelf;
	}

	/**
	 * @param localSelf
	 *            the localSelf to set
	 */
	public void setLocalSelf(String localSelf) {
		this.localSelf = localSelf;
	}

	/**
	 * @return the hidLocalComp
	 */
	public String getHidLocalComp() {
		return hidLocalComp;
	}

	/**
	 * @param hidLocalComp
	 *            the hidLocalComp to set
	 */
	public void setHidLocalComp(String hidLocalComp) {
		this.hidLocalComp = hidLocalComp;
	}

	/**
	 * @return the localComp
	 */
	public String getLocalComp() {
		return localComp;
	}

	/**
	 * @param localComp
	 *            the localComp to set
	 */
	public void setLocalComp(String localComp) {
		this.localComp = localComp;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the hiddenSortBy
	 */
	public String getHiddenSortBy() {
		return hiddenSortBy;
	}

	/**
	 * @param hiddenSortBy
	 *            the hiddenSortBy to set
	 */
	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}

	/**
	 * @return the sortByOrder
	 */
	public String getSortByOrder() {
		return sortByOrder;
	}

	/**
	 * @param sortByOrder
	 *            the sortByOrder to set
	 */
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}

	/**
	 * @return the thenBy1
	 */
	public String getThenBy1() {
		return thenBy1;
	}

	/**
	 * @param thenBy1
	 *            the thenBy1 to set
	 */
	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}

	/**
	 * @return the hiddenThenBy1
	 */
	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}

	/**
	 * @param hiddenThenBy1
	 *            the hiddenThenBy1 to set
	 */
	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}

	/**
	 * @return the thenByOrder1
	 */
	public String getThenByOrder1() {
		return thenByOrder1;
	}

	/**
	 * @param thenByOrder1
	 *            the thenByOrder1 to set
	 */
	public void setThenByOrder1(String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}

	/**
	 * @return the thenByOrder2
	 */
	public String getThenByOrder2() {
		return thenByOrder2;
	}

	/**
	 * @param thenByOrder2
	 *            the thenByOrder2 to set
	 */
	public void setThenByOrder2(String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}

	/**
	 * @return the thenBy2
	 */
	public String getThenBy2() {
		return thenBy2;
	}

	/**
	 * @param thenBy2
	 *            the thenBy2 to set
	 */
	public void setThenBy2(String thenBy2) {
		this.thenBy2 = thenBy2;
	}

	/**
	 * @return the hiddenThenBy2
	 */
	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}

	/**
	 * @param hiddenThenBy2
	 *            the hiddenThenBy2 to set
	 */
	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}

	/**
	 * @return the sortByAsc
	 */
	public String getSortByAsc() {
		return sortByAsc;
	}

	/**
	 * @param sortByAsc
	 *            the sortByAsc to set
	 */
	public void setSortByAsc(String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}

	/**
	 * @return the sortByDsc
	 */
	public String getSortByDsc() {
		return sortByDsc;
	}

	/**
	 * @param sortByDsc
	 *            the sortByDsc to set
	 */
	public void setSortByDsc(String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}

	/**
	 * @return the thenByOrder1Asc
	 */
	public String getThenByOrder1Asc() {
		return thenByOrder1Asc;
	}

	/**
	 * @param thenByOrder1Asc
	 *            the thenByOrder1Asc to set
	 */
	public void setThenByOrder1Asc(String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}

	/**
	 * @return the thenByOrder1Dsc
	 */
	public String getThenByOrder1Dsc() {
		return thenByOrder1Dsc;
	}

	/**
	 * @param thenByOrder1Dsc
	 *            the thenByOrder1Dsc to set
	 */
	public void setThenByOrder1Dsc(String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}

	/**
	 * @return the thenByOrder2Asc
	 */
	public String getThenByOrder2Asc() {
		return thenByOrder2Asc;
	}

	/**
	 * @param thenByOrder2Asc
	 *            the thenByOrder2Asc to set
	 */
	public void setThenByOrder2Asc(String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}

	/**
	 * @return the thenByOrder2Dsc
	 */
	public String getThenByOrder2Dsc() {
		return thenByOrder2Dsc;
	}

	/**
	 * @param thenByOrder2Dsc
	 *            the thenByOrder2Dsc to set
	 */
	public void setThenByOrder2Dsc(String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}

	/**
	 * @return the applDateSelect
	 */
	public String getApplDateSelect() {
		return applDateSelect;
	}

	/**
	 * @param applDateSelect
	 *            the applDateSelect to set
	 */
	public void setApplDateSelect(String applDateSelect) {
		this.applDateSelect = applDateSelect;
	}

	/**
	 * @return the dOAfromDate
	 */
	public String getDOAfromDate() {
		return DOAfromDate;
	}

	/**
	 * @param afromDate
	 *            the dOAfromDate to set
	 */
	public void setDOAfromDate(String afromDate) {
		DOAfromDate = afromDate;
	}

	/**
	 * @return the dOAToDate
	 */
	public String getDOAToDate() {
		return DOAToDate;
	}

	/**
	 * @param toDate
	 *            the dOAToDate to set
	 */
	public void setDOAToDate(String toDate) {
		DOAToDate = toDate;
	}

	/**
	 * @return the schTrvlDateSelect
	 */
	public String getSchTrvlDateSelect() {
		return schTrvlDateSelect;
	}

	/**
	 * @param schTrvlDateSelect
	 *            the schTrvlDateSelect to set
	 */
	public void setSchTrvlDateSelect(String schTrvlDateSelect) {
		this.schTrvlDateSelect = schTrvlDateSelect;
	}

	/**
	 * @return the sTDfromDate
	 */
	public String getSTDfromDate() {
		return STDfromDate;
	}

	/**
	 * @param dfromDate
	 *            the sTDfromDate to set
	 */
	public void setSTDfromDate(String dfromDate) {
		STDfromDate = dfromDate;
	}

	/**
	 * @return the sTDToDate
	 */
	public String getSTDToDate() {
		return STDToDate;
	}

	/**
	 * @param toDate
	 *            the sTDToDate to set
	 */
	public void setSTDToDate(String toDate) {
		STDToDate = toDate;
	}

	/**
	 * @return the trvlStrtDateSelect
	 */
	public String getTrvlStrtDateSelect() {
		return trvlStrtDateSelect;
	}

	/**
	 * @param trvlStrtDateSelect
	 *            the trvlStrtDateSelect to set
	 */
	public void setTrvlStrtDateSelect(String trvlStrtDateSelect) {
		this.trvlStrtDateSelect = trvlStrtDateSelect;
	}

	/**
	 * @return the tSDfromDate
	 */
	public String getTSDfromDate() {
		return TSDfromDate;
	}

	/**
	 * @param dfromDate
	 *            the tSDfromDate to set
	 */
	public void setTSDfromDate(String dfromDate) {
		TSDfromDate = dfromDate;
	}

	/**
	 * @return the tSDToDate
	 */
	public String getTSDToDate() {
		return TSDToDate;
	}

	/**
	 * @param toDate
	 *            the tSDToDate to set
	 */
	public void setTSDToDate(String toDate) {
		TSDToDate = toDate;
	}

	/**
	 * @return the trvlEndDateSelect
	 */
	public String getTrvlEndDateSelect() {
		return trvlEndDateSelect;
	}

	/**
	 * @param trvlEndDateSelect
	 *            the trvlEndDateSelect to set
	 */
	public void setTrvlEndDateSelect(String trvlEndDateSelect) {
		this.trvlEndDateSelect = trvlEndDateSelect;
	}

	/**
	 * @return the tEDfromDate
	 */
	public String getTEDfromDate() {
		return TEDfromDate;
	}

	/**
	 * @param dfromDate
	 *            the tEDfromDate to set
	 */
	public void setTEDfromDate(String dfromDate) {
		TEDfromDate = dfromDate;
	}

	/**
	 * @return the tEDToDate
	 */
	public String getTEDToDate() {
		return TEDToDate;
	}

	/**
	 * @param toDate
	 *            the tEDToDate to set
	 */
	public void setTEDToDate(String toDate) {
		TEDToDate = toDate;
	}

	/**
	 * @return the schCycleSelect
	 */
	public String getSchCycleSelect() {
		return schCycleSelect;
	}

	/**
	 * @param schCycleSelect
	 *            the schCycleSelect to set
	 */
	public void setSchCycleSelect(String schCycleSelect) {
		this.schCycleSelect = schCycleSelect;
	}

	/**
	 * @return the schCycleFrom
	 */
	public String getSchCycleFrom() {
		return schCycleFrom;
	}

	/**
	 * @param schCycleFrom
	 *            the schCycleFrom to set
	 */
	public void setSchCycleFrom(String schCycleFrom) {
		this.schCycleFrom = schCycleFrom;
	}

	/**
	 * @return the schCycleTo
	 */
	public String getSchCycleTo() {
		return schCycleTo;
	}

	/**
	 * @param schCycleTo
	 *            the schCycleTo to set
	 */
	public void setSchCycleTo(String schCycleTo) {
		this.schCycleTo = schCycleTo;
	}

	/**
	 * @return the trvlType
	 */
	public String getTrvlType() {
		return trvlType;
	}

	/**
	 * @param trvlType
	 *            the trvlType to set
	 */
	public void setTrvlType(String trvlType) {
		this.trvlType = trvlType;
	}

	/**
	 * @return the gradeId
	 */
	public String getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId
	 *            the gradeId to set
	 */
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @param gradeName
	 *            the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * @return the brnchCode
	 */
	public String getBrnchCode() {
		return brnchCode;
	}

	/**
	 * @param brnchCode
	 *            the brnchCode to set
	 */
	public void setBrnchCode(String brnchCode) {
		this.brnchCode = brnchCode;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName
	 *            the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the columnOrdering
	 */
	public String getColumnOrdering() {
		return columnOrdering;
	}

	/**
	 * @param columnOrdering
	 *            the columnOrdering to set
	 */
	public void setColumnOrdering(String columnOrdering) {
		this.columnOrdering = columnOrdering;
	}

	/**
	 * @return the hiddenColumns
	 */
	public String getHiddenColumns() {
		return hiddenColumns;
	}

	/**
	 * @param hiddenColumns
	 *            the hiddenColumns to set
	 */
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the dataLength
	 */
	public String getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength
	 *            the dataLength to set
	 */
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the hidReportView
	 */
	public String getHidReportView() {
		return hidReportView;
	}

	/**
	 * @param hidReportView
	 *            the hidReportView to set
	 */
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}

	/**
	 * @return the hidReportRadio
	 */
	public String getHidReportRadio() {
		return hidReportRadio;
	}

	/**
	 * @param hidReportRadio
	 *            the hidReportRadio to set
	 */
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}

	/**
	 * @return the exportAll
	 */
	public String getExportAll() {
		return exportAll;
	}

	/**
	 * @param exportAll
	 *            the exportAll to set
	 */
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData
	 *            the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the backFlag
	 */
	public String getBackFlag() {
		return backFlag;
	}

	/**
	 * @param backFlag
	 *            the backFlag to set
	 */
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return reqStatus;
	}

	/**
	 * @param reqStatus
	 *            the reqStatus to set
	 */
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	/**
	 * @return the trvlTypeMap
	 */
	public LinkedMap getTrvlTypeMap() {
		return trvlTypeMap;
	}

	/**
	 * @param trvlTypeMap
	 *            the trvlTypeMap to set
	 */
	public void setTrvlTypeMap(LinkedMap trvlTypeMap) {
		this.trvlTypeMap = trvlTypeMap;
	}

}
