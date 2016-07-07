package org.paradyne.bean.TravelManagement.TravelReports;

import org.paradyne.lib.BeanBase;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlApplicantReport extends BeanBase {
	private String backFlag = "";
	private String savedReport = "";
	private String reportId = "";
	private String reportTitle = "";
	private String startDtSelect = "";
	private String endDtSelect = "";
	private String applDtSelect = "";
	private String applicationDate = "";
	private String applForSelect = "";
	private String stsSelect = "";
	private String travelId = "";
	private String trvlPurpose = "";
	private String trvlPurposeId = "";
	private String arrangeSelect = "";
	private String trvlId = "";

	//columns
	private String empId = "";
	private String empName = "";
	private String applDate = "";
	private String branch = "";
	private String travelStartDate = "";
	private String grade = "";
	private String travelEndDate = "";
	private String travelType = "";
	private String travelPurpose = "";
	private String trvlAdvAmt = "";
	private String approver = "";
	private String initName = "";

	//sorting
	private String sortByLabel = "";
	private String sortBy = "";
	private String sortByAsc = "checked";
	private String sortByDsc = "";
	private String sortByOrder = "";
	private String thenBy1 = "";
	private String thenByLabel = "";
	private String thenByOrder1Asc = "checked";
	private String thenByOrder1Dsc = "";
	private String thenByOrder1 = "";
	private String thenBy2 = "";
	private String thenByOrder2Asc = "checked";
	private String thenByOrder2Dsc = "";
	private String thenByOrder2 = "";
	private String columnOrdering = "";
	private String hiddenColumns = "";
	private String hiddenSortBy = "";
	private String hiddenThenBy1 = "";
	private String hiddenThenBy2 = "";
	//Advance Filters

	private String selectAdvanceFilter = "";
	private String costwiseSelect = "";
	private String costwise = "";
	private String startDate = "";
	private String endDate = "";
	private String cyclewiseSelect = "";
	private String cyclewise = "";
	private String durationwiselSelect = "";
	private String durationwise = "";
	private String trvlFlag = "";
	private String accoFlag = "";
	private String convFlag = "";

	//added by krishna

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

	private String hidReportRadio = "";
	private String reportView = "";
	private String reportType = "";
	private String settingName = "";
	private String reqStatus = "";

	private String myPage = "";
	private String show = "";
	private String dataLength = "";
	private String hidReportView = "checked";

	private String exportAll = "";
	private String noData = "false";

	private String trRadio;
	private String accomRadio;
	private String locRadio;

	// flags-radio buttons
	private String trvlArrng;
	private String accom;
	private String localConv;

	private String trvBean = "";
	private String accomBean = "";
	private String convBean = "";
	private String hTrvChk = "";
	private String hAccomChk = "";
	private String hTrConv = "";
	private String trvChk = "";
	private String accomChk = "";
	private String trConv = "";

	/**
	 * @return the trvChk
	 */
	public String getTrvChk() {
		return trvChk;
	}

	/**
	 * @param trvChk
	 *            the trvChk to set
	 */
	public void setTrvChk(String trvChk) {
		this.trvChk = trvChk;
	}

	/**
	 * @return the accomChk
	 */
	public String getAccomChk() {
		return accomChk;
	}

	/**
	 * @param accomChk
	 *            the accomChk to set
	 */
	public void setAccomChk(String accomChk) {
		this.accomChk = accomChk;
	}

	/**
	 * @return the trConv
	 */
	public String getTrConv() {
		return trConv;
	}

	/**
	 * @param trConv
	 *            the trConv to set
	 */
	public void setTrConv(String trConv) {
		this.trConv = trConv;
	}

	/**
	 * @return the trvBean
	 */
	public String getTrvBean() {
		return trvBean;
	}

	/**
	 * @param trvBean
	 *            the trvBean to set
	 */
	public void setTrvBean(String trvBean) {
		this.trvBean = trvBean;
	}

	/**
	 * @return the accomBean
	 */
	public String getAccomBean() {
		return accomBean;
	}

	/**
	 * @param accomBean
	 *            the accomBean to set
	 */
	public void setAccomBean(String accomBean) {
		this.accomBean = accomBean;
	}

	/**
	 * @return the convBean
	 */
	public String getConvBean() {
		return convBean;
	}

	/**
	 * @param convBean
	 *            the convBean to set
	 */
	public void setConvBean(String convBean) {
		this.convBean = convBean;
	}

	/**
	 * @return the trRadio
	 */
	public String getTrRadio() {
		return trRadio;
	}

	/**
	 * @param trRadio
	 *            the trRadio to set
	 */
	public void setTrRadio(String trRadio) {
		this.trRadio = trRadio;
	}

	/**
	 * @return the accomRadio
	 */
	public String getAccomRadio() {
		return accomRadio;
	}

	/**
	 * @param accomRadio
	 *            the accomRadio to set
	 */
	public void setAccomRadio(String accomRadio) {
		this.accomRadio = accomRadio;
	}

	/**
	 * @return the locRadio
	 */
	public String getLocRadio() {
		return locRadio;
	}

	/**
	 * @param locRadio
	 *            the locRadio to set
	 */
	public void setLocRadio(String locRadio) {
		this.locRadio = locRadio;
	}

	/**
	 * @return the trvlArrng
	 */
	public String getTrvlArrng() {
		return trvlArrng;
	}

	/**
	 * @param trvlArrng
	 *            the trvlArrng to set
	 */
	public void setTrvlArrng(String trvlArrng) {
		this.trvlArrng = trvlArrng;
	}

	/**
	 * @return the accom
	 */
	public String getAccom() {
		return accom;
	}

	/**
	 * @param accom
	 *            the accom to set
	 */
	public void setAccom(String accom) {
		this.accom = accom;
	}

	/**
	 * @return the localConv
	 */
	public String getLocalConv() {
		return localConv;
	}

	/**
	 * @param localConv
	 *            the localConv to set
	 */
	public void setLocalConv(String localConv) {
		this.localConv = localConv;
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

	public String getApplDtSelect() {
		return applDtSelect;
	}

	public void setApplDtSelect(String applDtSelect) {
		this.applDtSelect = applDtSelect;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApplForSelect() {
		return applForSelect;
	}

	public void setApplForSelect(String applForSelect) {
		this.applForSelect = applForSelect;
	}

	public String getStsSelect() {
		return stsSelect;
	}

	public void setStsSelect(String stsSelect) {
		this.stsSelect = stsSelect;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getTrvlPurpose() {
		return trvlPurpose;
	}

	public void setTrvlPurpose(String trvlPurpose) {
		this.trvlPurpose = trvlPurpose;
	}

	public String getArrangeSelect() {
		return arrangeSelect;
	}

	public void setArrangeSelect(String arrangeSelect) {
		this.arrangeSelect = arrangeSelect;
	}

	public String getTrvlId() {
		return trvlId;
	}

	public void setTrvlId(String trvlId) {
		this.trvlId = trvlId;
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

	public String getApplDate() {
		return applDate;
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(String travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTravelEndDate() {
		return travelEndDate;
	}

	public void setTravelEndDate(String travelEndDate) {
		this.travelEndDate = travelEndDate;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public String getTrvlAdvAmt() {
		return trvlAdvAmt;
	}

	public void setTrvlAdvAmt(String trvlAdvAmt) {
		this.trvlAdvAmt = trvlAdvAmt;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getInitName() {
		return initName;
	}

	public void setInitName(String initName) {
		this.initName = initName;
	}

	public String getSortByLabel() {
		return sortByLabel;
	}

	public void setSortByLabel(String sortByLabel) {
		this.sortByLabel = sortByLabel;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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

	public String getThenByLabel() {
		return thenByLabel;
	}

	public void setThenByLabel(String thenByLabel) {
		this.thenByLabel = thenByLabel;
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

	public String getSelectAdvanceFilter() {
		return selectAdvanceFilter;
	}

	public void setSelectAdvanceFilter(String selectAdvanceFilter) {
		this.selectAdvanceFilter = selectAdvanceFilter;
	}

	public String getCostwiseSelect() {
		return costwiseSelect;
	}

	public void setCostwiseSelect(String costwiseSelect) {
		this.costwiseSelect = costwiseSelect;
	}

	public String getCostwise() {
		return costwise;
	}

	public void setCostwise(String costwise) {
		this.costwise = costwise;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCyclewiseSelect() {
		return cyclewiseSelect;
	}

	public void setCyclewiseSelect(String cyclewiseSelect) {
		this.cyclewiseSelect = cyclewiseSelect;
	}

	public String getCyclewise() {
		return cyclewise;
	}

	public void setCyclewise(String cyclewise) {
		this.cyclewise = cyclewise;
	}

	public String getDurationwiselSelect() {
		return durationwiselSelect;
	}

	public void setDurationwiselSelect(String durationwiselSelect) {
		this.durationwiselSelect = durationwiselSelect;
	}

	public String getDurationwise() {
		return durationwise;
	}

	public void setDurationwise(String durationwise) {
		this.durationwise = durationwise;
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

	public String getReportView() {
		return reportView;
	}

	public void setReportView(String reportView) {
		this.reportView = reportView;
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

	public String getTrvlPurposeId() {
		return trvlPurposeId;
	}

	public void setTrvlPurposeId(String trvlPurposeId) {
		this.trvlPurposeId = trvlPurposeId;
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

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getThenBy1() {
		return thenBy1;
	}

	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}

	public String getTrvlFlag() {
		return trvlFlag;
	}

	public void setTrvlFlag(String trvlFlag) {
		this.trvlFlag = trvlFlag;
	}

	public String getAccoFlag() {
		return accoFlag;
	}

	public void setAccoFlag(String accoFlag) {
		this.accoFlag = accoFlag;
	}

	public String getConvFlag() {
		return convFlag;
	}

	public void setConvFlag(String convFlag) {
		this.convFlag = convFlag;
	}

	public String getStartDtSelect() {
		return startDtSelect;
	}

	public void setStartDtSelect(String startDtSelect) {
		this.startDtSelect = startDtSelect;
	}

	public String getEndDtSelect() {
		return endDtSelect;
	}

	public void setEndDtSelect(String endDtSelect) {
		this.endDtSelect = endDtSelect;
	}

	/**
	 * @return the hTrvChk
	 */
	public String getHTrvChk() {
		return hTrvChk;
	}

	/**
	 * @param trvChk
	 *            the hTrvChk to set
	 */
	public void setHTrvChk(String trvChk) {
		hTrvChk = trvChk;
	}

	/**
	 * @return the hAccomChk
	 */
	public String getHAccomChk() {
		return hAccomChk;
	}

	/**
	 * @param accomChk
	 *            the hAccomChk to set
	 */
	public void setHAccomChk(String accomChk) {
		hAccomChk = accomChk;
	}

	/**
	 * @return the hTrConv
	 */
	public String getHTrConv() {
		return hTrConv;
	}

	/**
	 * @param trConv
	 *            the hTrConv to set
	 */
	public void setHTrConv(String trConv) {
		hTrConv = trConv;
	}

	/**
	 * @return the travelCheckVal
	 */
	public String getTravelCheckVal() {
		return travelCheckVal;
	}

	/**
	 * @param travelCheckVal the travelCheckVal to set
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
	 * @param travelCheck the travelCheck to set
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
	 * @param hidTravelSelf the hidTravelSelf to set
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
	 * @param trvlSelf the trvlSelf to set
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
	 * @param hidTravelComp the hidTravelComp to set
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
	 * @param trvlComp the trvlComp to set
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
	 * @param accomCheckVal the accomCheckVal to set
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
	 * @param accomCheck the accomCheck to set
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
	 * @param hidAccomSelf the hidAccomSelf to set
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
	 * @param accomSelf the accomSelf to set
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
	 * @param hidAccomComp the hidAccomComp to set
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
	 * @param accomComp the accomComp to set
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
	 * @param localCheckVal the localCheckVal to set
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
	 * @param localCheck the localCheck to set
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
	 * @param hidLocalSelf the hidLocalSelf to set
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
	 * @param localSelf the localSelf to set
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
	 * @param hidLocalComp the hidLocalComp to set
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
	 * @param localComp the localComp to set
	 */
	public void setLocalComp(String localComp) {
		this.localComp = localComp;
	}

	public String getHiddenSortBy() {
		return hiddenSortBy;
	}

	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}

	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}

	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}

	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}

	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}

}
