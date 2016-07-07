package org.paradyne.bean.CR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paradyne.lib.BeanBase;
/*
 * 
 * Date:11.06.2012
 */
public class PerformanceMetrics extends BeanBase {
	
	
	private String dashBoardID = "";
	private String modeLength="false";
	private String totalRecords="";
	String isActive;
	ArrayList titleList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String performanceMetricsId = "";
	private String parentCode = "";
	private String parentName = "";
	private String isApplyChild = "";
	
	private String greenValStart = "";
	private String greenValEnd = "";
	private String yellowValueStart = "";
	private String yellowValueEnd = "";
	private String redValueStart = "";
	private String redValueEnd = "";
	private String hiddenColName = "";
	
	private List<PerformanceMetrics> metricsList;
	private String metricCode = "";
	private String metricName = "";
	private String metricAbbr = "";
	private String accountCode = "";
	private String accountName = "";
	private String accountId = "";
	ArrayList iteratorList = null;
	private boolean listLength = false;
	private String childCode = "";
	private boolean viewListDtlFlag = false;
	private String fromDate = "";
	private String toDate = "";
	ArrayList dateList;
	/**
	 * dayName.
	 */
	private String dayName = "";
	private String escalation = "";
	private String miscName = "";
	private String miscCode = "";
	
	private boolean viewPlannedDtlFlag = false;
	ArrayList plannedList;
	private String ittMiscName = "";
	private String ittMiscCode = "";
	private String parentFlag = "";
	private String miscValue = "";
	private String miscWeekendValue = "";
	private String ittFromDate= "";
	private String ittToDate = "";
	private String ittMiscValue = "";
	private String ittMiscWeekendValue = "";
	private String ittMiscId = "";
	private String paracode = "";
	
	private boolean plannedListLength = false;
	private String selectChildCode="";
	private String selectChildName = "";
	private String selectParentFlag = "";
	
	HashMap hashmapDiv;
	HashMap hashmapDivSel;
	private String callType = "";
	private String availDiv;
	private String selDiv;
	private String addNewCall = "";
	private String isPartWaitTimeChecked = "";
	private String hiddenPartWaitTimeFlag = "";
	private boolean edit = false;
	private String hiddenSearchMessage = "";
	private String searchMessage = "";
	// 26 Sept 2012
	
	private String crmFlag = "";
	private String clientFlag = "";
	private String crmFlagHidden = "";
	private String clientFlagHidden = "";
	private String crmAll = "";
	private String clientAll = "";
	
	private String addRepairCode = "";
	HashMap hashmapRepairCode;
	HashMap hashmapRepairCodeSel;
	private String repairCode;
	private String selRepairCode;
	private String addNewCallDesc = "";
	private String addRepairCodeDesc = "";
	//Data Reconciliation fileds
	private String orderId = "";
	private String customerName = "";
	private String callDate = "";
	private String callTime = "";
	private String sla = "";
	private String responseTime = "";
	private String dispatchDate = "";
	private String dispatchTime = "";
	private String onsiteDate = "";
	private String onsiteTime = "";
	private String closedDate = "";
	private String closedTime = "";
	private String contactFlag = "";
	private String contactFlagHidden = "";
	private String responseFlag= "";
	private String responseFlagHidden = "";
	private String restoreFlag = "";
	private String restoreFlagHidden = "";
	private List<PerformanceMetrics> dataReconciliationList;
	private boolean dataReconciliationListLength = false;
	private String otherLengthVar = "";
	private String addClosureCode = "";
	private String addClosureCodeDesc = "";
	private String closureCode = "";
	private String selclosureCode = "";
	HashMap hashmapClosureCodeSel;
	HashMap hashmapClosureCode;
	private boolean viewDataReconListDtlFlag = false;
	private String totalDataReconRecords = "";
	private String myPageDataRecon;
	
	private String hiddenContactFlagCount = "";
	private String hiddenResponceFlagCount = "";
	private String hiddenRestoreFlagCount = "";
	private String orgContactFlagHidden = "";
	private String orgResponseFlagHidden ="";
	private String orgRestoreFlagHidden = "";
	private String hiddenOrgContactFlagCount = "";
	private String hiddenOrgResponceFlagCount = "";
	private String hiddenOrgRestoreFlagCount = "";
	
	private String commentsDataRecon = "";
	private String slaHrs = "";
	
	private String hidden24ContactFlagCount = "";
	private String hidden24ResponceFlagCount = "";
	private String hidden24RestoreFlagCount = "";
	private String hidden8ContactFlagCount = "";
	
	private String fccFlag = "";
	private String fccFlagHidden = "";

	private String hidden8ResponceFlagCount = "";
	private String hidden8RestoreFlagCount = "";
	
	private String hidden4ContactFlagCount = "";
	private String hidden4ResponceFlagCount = "";
	private String hidden4RestoreFlagCount = "";
	
	private String accountChildCode = "";
	
	private boolean viewPublishedFlag = false;
	private String orgFccFlagHidden = "";
	
	private String perSummDate= "";
	private String report = "";
	
	private boolean restoreFlagCheck = false;
	private boolean fccFlagCheck = false;
	private boolean contactFlagCheckedCount= false;
	private boolean responseFlagCheckedCount= false;
	private boolean closedCallSummaryReportFlag= false; 
	private boolean etaReportFlagCheck= false;
	//25-01-2013
	
	private String excludeCallFlag = "";
	private String excludeCallFlagHidden = "";
	private String missedSLACheck = "";
	private String allSLACheck = "";
	private String excludeCallsCheck = "";
	
	private String slaTypeRadio = "";
	private String slaTypeHiddenRadio = "";
	/**
	 *  For ETA
	 */
	private String etaFlagHidden = "";
	/**
	 * For ETA
	 */
	private String orgEtaFlagHidden = "";
	
	//For Adding SLA
	
	private String captionName="";
	private String slaType="";
	private String hrs="";
	private String slaGreenValStart="";
	private String slaGreenValEnd="";
	private String slaYellowValStart="";
	private String slaYellowValEnd="";
	private String slaRedValStart="";
	private String slaRedValEnd="";
	private String slaCrmFlag="";
	private String slaClientFlag="";
	private String slaCrmFlagHidden="";
	private String slaClientFlagHidden="";
	private String autoId="";
	private String responseDate = "";
	private String dashBoardName="";
	private String parentFlagHiddenChk="";
	
	 
	ArrayList<PerformanceMetrics> slaList = null;
	private boolean dateListLength = false;
	
	
	public boolean isDateListLength() {
		return dateListLength;
	}
	public void setDateListLength(boolean dateListLength) {
		this.dateListLength = dateListLength;
	}
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
	public String getSlaCrmFlag() {
		return slaCrmFlag;
	}
	public void setSlaCrmFlag(String slaCrmFlag) {
		this.slaCrmFlag = slaCrmFlag;
	}
	public String getSlaClientFlag() {
		return slaClientFlag;
	}
	public void setSlaClientFlag(String slaClientFlag) {
		this.slaClientFlag = slaClientFlag;
	}
	/**
	 * @return the excludeCallsCheck
	 */
	public String getExcludeCallsCheck() {
		return excludeCallsCheck;
	}
	/**
	 * @param excludeCallsCheck the excludeCallsCheck to set
	 */
	public void setExcludeCallsCheck(String excludeCallsCheck) {
		this.excludeCallsCheck = excludeCallsCheck;
	}
	/**
	 * @return the slaTypeRadio
	 */
	public String getSlaTypeRadio() {
		return slaTypeRadio;
	}
	/**
	 * @param slaTypeRadio the slaTypeRadio to set
	 */
	public void setSlaTypeRadio(String slaTypeRadio) {
		this.slaTypeRadio = slaTypeRadio;
	}
	/**
	 * @return the slaTypeHiddenRadio
	 */
	public String getSlaTypeHiddenRadio() {
		return slaTypeHiddenRadio;
	}
	/**
	 * @param slaTypeHiddenRadio the slaTypeHiddenRadio to set
	 */
	public void setSlaTypeHiddenRadio(String slaTypeHiddenRadio) {
		this.slaTypeHiddenRadio = slaTypeHiddenRadio;
	}
	/**
	 * @return the missedSLACheck
	 */
	public String getMissedSLACheck() {
		return missedSLACheck;
	}
	/**
	 * @param missedSLACheck the missedSLACheck to set
	 */
	public void setMissedSLACheck(String missedSLACheck) {
		this.missedSLACheck = missedSLACheck;
	}
	/**
	 * @return the allSLACheck
	 */
	public String getAllSLACheck() {
		return allSLACheck;
	}
	/**
	 * @param allSLACheck the allSLACheck to set
	 */
	public void setAllSLACheck(String allSLACheck) {
		this.allSLACheck = allSLACheck;
	}
	/**
	 * @return the excludeCallFlag
	 */
	public String getExcludeCallFlag() {
		return excludeCallFlag;
	}
	/**
	 * @param excludeCallFlag the excludeCallFlag to set
	 */
	public void setExcludeCallFlag(String excludeCallFlag) {
		this.excludeCallFlag = excludeCallFlag;
	}
	/**
	 * @return the excludeCallFlagHidden
	 */
	public String getExcludeCallFlagHidden() {
		return excludeCallFlagHidden;
	}
	/**
	 * @param excludeCallFlagHidden the excludeCallFlagHidden to set
	 */
	public void setExcludeCallFlagHidden(String excludeCallFlagHidden) {
		this.excludeCallFlagHidden = excludeCallFlagHidden;
	}
	/**
	 * @return the closedCallSummaryReportFlag
	 */
	public boolean isClosedCallSummaryReportFlag() {
		return closedCallSummaryReportFlag;
	}
	/**
	 * @param closedCallSummaryReportFlag the closedCallSummaryReportFlag to set
	 */
	public void setClosedCallSummaryReportFlag(boolean closedCallSummaryReportFlag) {
		this.closedCallSummaryReportFlag = closedCallSummaryReportFlag;
	}
	/**
	 * @return the contactFlagCheckedCount
	 */
	public boolean isContactFlagCheckedCount() {
		return contactFlagCheckedCount;
	}
	/**
	 * @param contactFlagCheckedCount the contactFlagCheckedCount to set
	 */
	public void setContactFlagCheckedCount(boolean contactFlagCheckedCount) {
		this.contactFlagCheckedCount = contactFlagCheckedCount;
	}
	/**
	 * @return the responseFlagCheckedCount
	 */
	public boolean isResponseFlagCheckedCount() {
		return responseFlagCheckedCount;
	}
	/**
	 * @param responseFlagCheckedCount the responseFlagCheckedCount to set
	 */
	public void setResponseFlagCheckedCount(boolean responseFlagCheckedCount) {
		this.responseFlagCheckedCount = responseFlagCheckedCount;
	}
	/**
	 * @return the fccFlagCheck
	 */
	public boolean isFccFlagCheck() {
		return fccFlagCheck;
	}
	/**
	 * @param fccFlagCheck the fccFlagCheck to set
	 */
	public void setFccFlagCheck(boolean fccFlagCheck) {
		this.fccFlagCheck = fccFlagCheck;
	}
	/**
	 * @return the restoreFlagCheck
	 */
	public boolean isRestoreFlagCheck() {
		return restoreFlagCheck;
	}
	/**
	 * @param restoreFlagCheck the restoreFlagCheck to set
	 */
	public void setRestoreFlagCheck(boolean restoreFlagCheck) {
		this.restoreFlagCheck = restoreFlagCheck;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	/**
	 * @return the perSummDate
	 */
	public String getPerSummDate() {
		return perSummDate;
	}
	/**
	 * @param perSummDate the perSummDate to set
	 */
	public void setPerSummDate(String perSummDate) {
		this.perSummDate = perSummDate;
	}
	/**
	 * @return the orgFccFlagHidden
	 */
	public String getOrgFccFlagHidden() {
		return orgFccFlagHidden;
	}
	/**
	 * @param orgFccFlagHidden the orgFccFlagHidden to set
	 */
	public void setOrgFccFlagHidden(String orgFccFlagHidden) {
		this.orgFccFlagHidden = orgFccFlagHidden;
	}
	/**
	 * @return the viewPublishedFlag
	 */
	public boolean isViewPublishedFlag() {
		return viewPublishedFlag;
	}
	/**
	 * @param viewPublishedFlag the viewPublishedFlag to set
	 */
	public void setViewPublishedFlag(boolean viewPublishedFlag) {
		this.viewPublishedFlag = viewPublishedFlag;
	}
	/**
	 * @return the accountChildCode
	 */
	public String getAccountChildCode() {
		return accountChildCode;
	}
	/**
	 * @param accountChildCode the accountChildCode to set
	 */
	public void setAccountChildCode(String accountChildCode) {
		this.accountChildCode = accountChildCode;
	}
	/**
	 * @return the hidden4ContactFlagCount
	 */
	public String getHidden4ContactFlagCount() {
		return hidden4ContactFlagCount;
	}
	/**
	 * @param hidden4ContactFlagCount the hidden4ContactFlagCount to set
	 */
	public void setHidden4ContactFlagCount(String hidden4ContactFlagCount) {
		this.hidden4ContactFlagCount = hidden4ContactFlagCount;
	}
	/**
	 * @return the hidden4ResponceFlagCount
	 */
	public String getHidden4ResponceFlagCount() {
		return hidden4ResponceFlagCount;
	}
	/**
	 * @param hidden4ResponceFlagCount the hidden4ResponceFlagCount to set
	 */
	public void setHidden4ResponceFlagCount(String hidden4ResponceFlagCount) {
		this.hidden4ResponceFlagCount = hidden4ResponceFlagCount;
	}
	/**
	 * @return the hidden4RestoreFlagCount
	 */
	public String getHidden4RestoreFlagCount() {
		return hidden4RestoreFlagCount;
	}
	/**
	 * @param hidden4RestoreFlagCount the hidden4RestoreFlagCount to set
	 */
	public void setHidden4RestoreFlagCount(String hidden4RestoreFlagCount) {
		this.hidden4RestoreFlagCount = hidden4RestoreFlagCount;
	}
	/**
	 * @return the hidden8ResponceFlagCount
	 */
	public String getHidden8ResponceFlagCount() {
		return hidden8ResponceFlagCount;
	}
	/**
	 * @param hidden8ResponceFlagCount the hidden8ResponceFlagCount to set
	 */
	public void setHidden8ResponceFlagCount(String hidden8ResponceFlagCount) {
		this.hidden8ResponceFlagCount = hidden8ResponceFlagCount;
	}
	/**
	 * @return the hidden8RestoreFlagCount
	 */
	public String getHidden8RestoreFlagCount() {
		return hidden8RestoreFlagCount;
	}
	/**
	 * @param hidden8RestoreFlagCount the hidden8RestoreFlagCount to set
	 */
	public void setHidden8RestoreFlagCount(String hidden8RestoreFlagCount) {
		this.hidden8RestoreFlagCount = hidden8RestoreFlagCount;
	}
	/**
	 * @return the fccFlag
	 */
	public String getFccFlag() {
		return fccFlag;
	}
	/**
	 * @param fccFlag the fccFlag to set
	 */
	public void setFccFlag(String fccFlag) {
		this.fccFlag = fccFlag;
	}
	/**
	 * @return the fccFlagHidden
	 */
	public String getFccFlagHidden() {
		return fccFlagHidden;
	}
	/**
	 * @param fccFlagHidden the fccFlagHidden to set
	 */
	public void setFccFlagHidden(String fccFlagHidden) {
		this.fccFlagHidden = fccFlagHidden;
	}
	/**
	 * @return the hidden8ContactFlagCount
	 */
	public String getHidden8ContactFlagCount() {
		return hidden8ContactFlagCount;
	}
	/**
	 * @param hidden8ContactFlagCount the hidden8ContactFlagCount to set
	 */
	public void setHidden8ContactFlagCount(String hidden8ContactFlagCount) {
		this.hidden8ContactFlagCount = hidden8ContactFlagCount;
	}
	/**
	 * @return the hidden24ContactFlagCount
	 */
	public String getHidden24ContactFlagCount() {
		return hidden24ContactFlagCount;
	}
	/**
	 * @param hidden24ContactFlagCount the hidden24ContactFlagCount to set
	 */
	public void setHidden24ContactFlagCount(String hidden24ContactFlagCount) {
		this.hidden24ContactFlagCount = hidden24ContactFlagCount;
	}
	/**
	 * @return the hidden24ResponceFlagCount
	 */
	public String getHidden24ResponceFlagCount() {
		return hidden24ResponceFlagCount;
	}
	/**
	 * @param hidden24ResponceFlagCount the hidden24ResponceFlagCount to set
	 */
	public void setHidden24ResponceFlagCount(String hidden24ResponceFlagCount) {
		this.hidden24ResponceFlagCount = hidden24ResponceFlagCount;
	}
	/**
	 * @return the hidden24RestoreFlagCount
	 */
	public String getHidden24RestoreFlagCount() {
		return hidden24RestoreFlagCount;
	}
	/**
	 * @param hidden24RestoreFlagCount the hidden24RestoreFlagCount to set
	 */
	public void setHidden24RestoreFlagCount(String hidden24RestoreFlagCount) {
		this.hidden24RestoreFlagCount = hidden24RestoreFlagCount;
	}
	/**
	 * @return the commentsDataRecon
	 */
	public String getCommentsDataRecon() {
		return commentsDataRecon;
	}
	/**
	 * @param commentsDataRecon the commentsDataRecon to set
	 */
	public void setCommentsDataRecon(String commentsDataRecon) {
		this.commentsDataRecon = commentsDataRecon;
	}
	/**
	 * @return the slaHrs
	 */
	public String getSlaHrs() {
		return slaHrs;
	}
	/**
	 * @param slaHrs the slaHrs to set
	 */
	public void setSlaHrs(String slaHrs) {
		this.slaHrs = slaHrs;
	}
	/**
	 * @return the orgContactFlagHidden
	 */
	public String getOrgContactFlagHidden() {
		return orgContactFlagHidden;
	}
	/**
	 * @param orgContactFlagHidden the orgContactFlagHidden to set
	 */
	public void setOrgContactFlagHidden(String orgContactFlagHidden) {
		this.orgContactFlagHidden = orgContactFlagHidden;
	}
	/**
	 * @return the orgResponseFlagHidden
	 */
	public String getOrgResponseFlagHidden() {
		return orgResponseFlagHidden;
	}
	/**
	 * @param orgResponseFlagHidden the orgResponseFlagHidden to set
	 */
	public void setOrgResponseFlagHidden(String orgResponseFlagHidden) {
		this.orgResponseFlagHidden = orgResponseFlagHidden;
	}
	/**
	 * @return the orgRestoreFlagHidden
	 */
	public String getOrgRestoreFlagHidden() {
		return orgRestoreFlagHidden;
	}
	/**
	 * @param orgRestoreFlagHidden the orgRestoreFlagHidden to set
	 */
	public void setOrgRestoreFlagHidden(String orgRestoreFlagHidden) {
		this.orgRestoreFlagHidden = orgRestoreFlagHidden;
	}
	/**
	 * @return the hiddenOrgContactFlagCount
	 */
	public String getHiddenOrgContactFlagCount() {
		return hiddenOrgContactFlagCount;
	}
	/**
	 * @param hiddenOrgContactFlagCount the hiddenOrgContactFlagCount to set
	 */
	public void setHiddenOrgContactFlagCount(String hiddenOrgContactFlagCount) {
		this.hiddenOrgContactFlagCount = hiddenOrgContactFlagCount;
	}
	/**
	 * @return the hiddenOrgResponceFlagCount
	 */
	public String getHiddenOrgResponceFlagCount() {
		return hiddenOrgResponceFlagCount;
	}
	/**
	 * @param hiddenOrgResponceFlagCount the hiddenOrgResponceFlagCount to set
	 */
	public void setHiddenOrgResponceFlagCount(String hiddenOrgResponceFlagCount) {
		this.hiddenOrgResponceFlagCount = hiddenOrgResponceFlagCount;
	}
	/**
	 * @return the hiddenOrgRestoreFlagCount
	 */
	public String getHiddenOrgRestoreFlagCount() {
		return hiddenOrgRestoreFlagCount;
	}
	/**
	 * @param hiddenOrgRestoreFlagCount the hiddenOrgRestoreFlagCount to set
	 */
	public void setHiddenOrgRestoreFlagCount(String hiddenOrgRestoreFlagCount) {
		this.hiddenOrgRestoreFlagCount = hiddenOrgRestoreFlagCount;
	}
	/**
	 * @return the hiddenContactFlagCount
	 */
	public String getHiddenContactFlagCount() {
		return hiddenContactFlagCount;
	}
	/**
	 * @param hiddenContactFlagCount the hiddenContactFlagCount to set
	 */
	public void setHiddenContactFlagCount(String hiddenContactFlagCount) {
		this.hiddenContactFlagCount = hiddenContactFlagCount;
	}
	/**
	 * @return the hiddenResponceFlagCount
	 */
	public String getHiddenResponceFlagCount() {
		return hiddenResponceFlagCount;
	}
	/**
	 * @param hiddenResponceFlagCount the hiddenResponceFlagCount to set
	 */
	public void setHiddenResponceFlagCount(String hiddenResponceFlagCount) {
		this.hiddenResponceFlagCount = hiddenResponceFlagCount;
	}
	/**
	 * @return the hiddenRestoreFlagCount
	 */
	public String getHiddenRestoreFlagCount() {
		return hiddenRestoreFlagCount;
	}
	/**
	 * @param hiddenRestoreFlagCount the hiddenRestoreFlagCount to set
	 */
	public void setHiddenRestoreFlagCount(String hiddenRestoreFlagCount) {
		this.hiddenRestoreFlagCount = hiddenRestoreFlagCount;
	}
	/**
	 * @return the myPageDataRecon
	 */
	public String getMyPageDataRecon() {
		return myPageDataRecon;
	}
	/**
	 * @param myPageDataRecon the myPageDataRecon to set
	 */
	public void setMyPageDataRecon(String myPageDataRecon) {
		this.myPageDataRecon = myPageDataRecon;
	}
	/**
	 * @return the totalDataReconRecords
	 */
	public String getTotalDataReconRecords() {
		return totalDataReconRecords;
	}
	/**
	 * @param totalDataReconRecords the totalDataReconRecords to set
	 */
	public void setTotalDataReconRecords(String totalDataReconRecords) {
		this.totalDataReconRecords = totalDataReconRecords;
	}
	/**
	 * @return the viewDataReconListDtlFlag
	 */
	public boolean isViewDataReconListDtlFlag() {
		return viewDataReconListDtlFlag;
	}
	/**
	 * @param viewDataReconListDtlFlag the viewDataReconListDtlFlag to set
	 */
	public void setViewDataReconListDtlFlag(boolean viewDataReconListDtlFlag) {
		this.viewDataReconListDtlFlag = viewDataReconListDtlFlag;
	}
	/**
	 * @return the addClosureCode
	 */
	public String getAddClosureCode() {
		return addClosureCode;
	}
	/**
	 * @param addClosureCode the addClosureCode to set
	 */
	public void setAddClosureCode(String addClosureCode) {
		this.addClosureCode = addClosureCode;
	}
	/**
	 * @return the addClosureCodeDesc
	 */
	public String getAddClosureCodeDesc() {
		return addClosureCodeDesc;
	}
	/**
	 * @param addClosureCodeDesc the addClosureCodeDesc to set
	 */
	public void setAddClosureCodeDesc(String addClosureCodeDesc) {
		this.addClosureCodeDesc = addClosureCodeDesc;
	}
	/**
	 * @return the closureCode
	 */
	public String getClosureCode() {
		return closureCode;
	}
	/**
	 * @param closureCode the closureCode to set
	 */
	public void setClosureCode(String closureCode) {
		this.closureCode = closureCode;
	}
	/**
	 * @return the selclosureCode
	 */
	public String getSelclosureCode() {
		return selclosureCode;
	}
	/**
	 * @param selclosureCode the selclosureCode to set
	 */
	public void setSelclosureCode(String selclosureCode) {
		this.selclosureCode = selclosureCode;
	}
	/**
	 * @return the hashmapClosureCodeSel
	 */
	public HashMap getHashmapClosureCodeSel() {
		return hashmapClosureCodeSel;
	}
	/**
	 * @param hashmapClosureCodeSel the hashmapClosureCodeSel to set
	 */
	public void setHashmapClosureCodeSel(HashMap hashmapClosureCodeSel) {
		this.hashmapClosureCodeSel = hashmapClosureCodeSel;
	}
	/**
	 * @return the hashmapClosureCode
	 */
	public HashMap getHashmapClosureCode() {
		return hashmapClosureCode;
	}
	/**
	 * @param hashmapClosureCode the hashmapClosureCode to set
	 */
	public void setHashmapClosureCode(HashMap hashmapClosureCode) {
		this.hashmapClosureCode = hashmapClosureCode;
	}
	/**
	 * @return the otherLengthVar
	 */
	public String getOtherLengthVar() {
		return otherLengthVar;
	}
	/**
	 * @param otherLengthVar the otherLengthVar to set
	 */
	public void setOtherLengthVar(String otherLengthVar) {
		this.otherLengthVar = otherLengthVar;
	}
	/**
	 * @return the dataReconciliationListLength
	 */
	public boolean isDataReconciliationListLength() {
		return dataReconciliationListLength;
	}
	/**
	 * @param dataReconciliationListLength the dataReconciliationListLength to set
	 */
	public void setDataReconciliationListLength(boolean dataReconciliationListLength) {
		this.dataReconciliationListLength = dataReconciliationListLength;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the callDate
	 */
	public String getCallDate() {
		return callDate;
	}
	/**
	 * @param callDate the callDate to set
	 */
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	/**
	 * @return the callTime
	 */
	public String getCallTime() {
		return callTime;
	}
	/**
	 * @param callTime the callTime to set
	 */
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	/**
	 * @return the sla
	 */
	public String getSla() {
		return sla;
	}
	/**
	 * @param sla the sla to set
	 */
	public void setSla(String sla) {
		this.sla = sla;
	}
	/**
	 * @return the responseTime
	 */
	public String getResponseTime() {
		return responseTime;
	}
	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	/**
	 * @return the dispatchDate
	 */
	public String getDispatchDate() {
		return dispatchDate;
	}
	/**
	 * @param dispatchDate the dispatchDate to set
	 */
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	/**
	 * @return the dispatchTime
	 */
	public String getDispatchTime() {
		return dispatchTime;
	}
	/**
	 * @param dispatchTime the dispatchTime to set
	 */
	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	/**
	 * @return the onsiteDate
	 */
	public String getOnsiteDate() {
		return onsiteDate;
	}
	/**
	 * @param onsiteDate the onsiteDate to set
	 */
	public void setOnsiteDate(String onsiteDate) {
		this.onsiteDate = onsiteDate;
	}
	/**
	 * @return the onsiteTime
	 */
	public String getOnsiteTime() {
		return onsiteTime;
	}
	/**
	 * @param onsiteTime the onsiteTime to set
	 */
	public void setOnsiteTime(String onsiteTime) {
		this.onsiteTime = onsiteTime;
	}
	/**
	 * @return the closedDate
	 */
	public String getClosedDate() {
		return closedDate;
	}
	/**
	 * @param closedDate the closedDate to set
	 */
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	/**
	 * @return the closedTime
	 */
	public String getClosedTime() {
		return closedTime;
	}
	/**
	 * @param closedTime the closedTime to set
	 */
	public void setClosedTime(String closedTime) {
		this.closedTime = closedTime;
	}
	/**
	 * @return the contactFlag
	 */
	public String getContactFlag() {
		return contactFlag;
	}
	/**
	 * @param contactFlag the contactFlag to set
	 */
	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}
	/**
	 * @return the contactFlagHidden
	 */
	public String getContactFlagHidden() {
		return contactFlagHidden;
	}
	/**
	 * @param contactFlagHidden the contactFlagHidden to set
	 */
	public void setContactFlagHidden(String contactFlagHidden) {
		this.contactFlagHidden = contactFlagHidden;
	}
	/**
	 * @return the responseFlag
	 */
	public String getResponseFlag() {
		return responseFlag;
	}
	/**
	 * @param responseFlag the responseFlag to set
	 */
	public void setResponseFlag(String responseFlag) {
		this.responseFlag = responseFlag;
	}
	/**
	 * @return the responseFlagHidden
	 */
	public String getResponseFlagHidden() {
		return responseFlagHidden;
	}
	/**
	 * @param responseFlagHidden the responseFlagHidden to set
	 */
	public void setResponseFlagHidden(String responseFlagHidden) {
		this.responseFlagHidden = responseFlagHidden;
	}
	/**
	 * @return the restoreFlag
	 */
	public String getRestoreFlag() {
		return restoreFlag;
	}
	/**
	 * @param restoreFlag the restoreFlag to set
	 */
	public void setRestoreFlag(String restoreFlag) {
		this.restoreFlag = restoreFlag;
	}
	/**
	 * @return the restoreFlagHidden
	 */
	public String getRestoreFlagHidden() {
		return restoreFlagHidden;
	}
	/**
	 * @param restoreFlagHidden the restoreFlagHidden to set
	 */
	public void setRestoreFlagHidden(String restoreFlagHidden) {
		this.restoreFlagHidden = restoreFlagHidden;
	}
	/**
	 * @return the dataReconciliationList
	 */
	public List<PerformanceMetrics> getDataReconciliationList() {
		return dataReconciliationList;
	}
	/**
	 * @param dataReconciliationList the dataReconciliationList to set
	 */
	public void setDataReconciliationList(
			List<PerformanceMetrics> dataReconciliationList) {
		this.dataReconciliationList = dataReconciliationList;
	}
	/**
	 * @return the addNewCallDesc
	 */
	public String getAddNewCallDesc() {
		return addNewCallDesc;
	}
	/**
	 * @param addNewCallDesc the addNewCallDesc to set
	 */
	public void setAddNewCallDesc(String addNewCallDesc) {
		this.addNewCallDesc = addNewCallDesc;
	}
	/**
	 * @return the addRepairCodeDesc
	 */
	public String getAddRepairCodeDesc() {
		return addRepairCodeDesc;
	}
	/**
	 * @param addRepairCodeDesc the addRepairCodeDesc to set
	 */
	public void setAddRepairCodeDesc(String addRepairCodeDesc) {
		this.addRepairCodeDesc = addRepairCodeDesc;
	}
	/**
	 * @return the addRepairCode
	 */
	public String getAddRepairCode() {
		return addRepairCode;
	}
	/**
	 * @param addRepairCode the addRepairCode to set
	 */
	public void setAddRepairCode(String addRepairCode) {
		this.addRepairCode = addRepairCode;
	}
	/**
	 * @return the hashmapRepairCode
	 */
	public HashMap getHashmapRepairCode() {
		return hashmapRepairCode;
	}
	/**
	 * @param hashmapRepairCode the hashmapRepairCode to set
	 */
	public void setHashmapRepairCode(HashMap hashmapRepairCode) {
		this.hashmapRepairCode = hashmapRepairCode;
	}
	/**
	 * @return the hashmapRepairCodeSel
	 */
	public HashMap getHashmapRepairCodeSel() {
		return hashmapRepairCodeSel;
	}
	/**
	 * @param hashmapRepairCodeSel the hashmapRepairCodeSel to set
	 */
	public void setHashmapRepairCodeSel(HashMap hashmapRepairCodeSel) {
		this.hashmapRepairCodeSel = hashmapRepairCodeSel;
	}
	/**
	 * @return the repairCode
	 */
	public String getRepairCode() {
		return repairCode;
	}
	/**
	 * @param repairCode the repairCode to set
	 */
	public void setRepairCode(String repairCode) {
		this.repairCode = repairCode;
	}
	/**
	 * @return the selRepairCode
	 */
	public String getSelRepairCode() {
		return selRepairCode;
	}
	/**
	 * @param selRepairCode the selRepairCode to set
	 */
	public void setSelRepairCode(String selRepairCode) {
		this.selRepairCode = selRepairCode;
	}
	/**
	 * @return the crmAll
	 */
	public String getCrmAll() {
		return crmAll;
	}
	/**
	 * @param crmAll the crmAll to set
	 */
	public void setCrmAll(String crmAll) {
		this.crmAll = crmAll;
	}
	/**
	 * @return the clientAll
	 */
	public String getClientAll() {
		return clientAll;
	}
	/**
	 * @param clientAll the clientAll to set
	 */
	public void setClientAll(String clientAll) {
		this.clientAll = clientAll;
	}
	
	public String getCrmFlagHidden() {
		return crmFlagHidden;
	}
	public void setCrmFlagHidden(String crmFlagHidden) {
		this.crmFlagHidden = crmFlagHidden;
	}
	public String getClientFlagHidden() {
		return clientFlagHidden;
	}
	public void setClientFlagHidden(String clientFlagHidden) {
		this.clientFlagHidden = clientFlagHidden;
	}
	public String getCrmFlag() {
		return crmFlag;
	}
	public void setCrmFlag(String crmFlag) {
		this.crmFlag = crmFlag;
	}
	public String getClientFlag() {
		return clientFlag;
	}
	public void setClientFlag(String clientFlag) {
		this.clientFlag = clientFlag;
	}
	/**
	 * @return the hiddenSearchMessage
	 */
	public String getHiddenSearchMessage() {
		return hiddenSearchMessage;
	}
	/**
	 * @param hiddenSearchMessage the hiddenSearchMessage to set
	 */
	public void setHiddenSearchMessage(String hiddenSearchMessage) {
		this.hiddenSearchMessage = hiddenSearchMessage;
	}
	/**
	 * @return the searchMessage
	 */
	public String getSearchMessage() {
		return searchMessage;
	}
	/**
	 * @param searchMessage the searchMessage to set
	 */
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	/**
	 * @return the edit
	 */
	public boolean isEdit() {
		return edit;
	}
	/**
	 * @param edit the edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	/**
	 * @return the addNewCall
	 */
	public String getAddNewCall() {
		return addNewCall;
	}
	/**
	 * @param addNewCall the addNewCall to set
	 */
	public void setAddNewCall(String addNewCall) {
		this.addNewCall = addNewCall;
	}
	/**
	 * @return the isPartWaitTimeChecked
	 */
	public String getIsPartWaitTimeChecked() {
		return isPartWaitTimeChecked;
	}
	/**
	 * @param isPartWaitTimeChecked the isPartWaitTimeChecked to set
	 */
	public void setIsPartWaitTimeChecked(String isPartWaitTimeChecked) {
		this.isPartWaitTimeChecked = isPartWaitTimeChecked;
	}
	/**
	 * @return the hiddenPartWaitTimeFlag
	 */
	public String getHiddenPartWaitTimeFlag() {
		return hiddenPartWaitTimeFlag;
	}
	/**
	 * @param hiddenPartWaitTimeFlag the hiddenPartWaitTimeFlag to set
	 */
	public void setHiddenPartWaitTimeFlag(String hiddenPartWaitTimeFlag) {
		this.hiddenPartWaitTimeFlag = hiddenPartWaitTimeFlag;
	}
	/**
	 * @return the availDiv
	 */
	public String getAvailDiv() {
		return availDiv;
	}
	/**
	 * @param availDiv the availDiv to set
	 */
	public void setAvailDiv(String availDiv) {
		this.availDiv = availDiv;
	}
	/**
	 * @return the selDiv
	 */
	public String getSelDiv() {
		return selDiv;
	}
	/**
	 * @param selDiv the selDiv to set
	 */
	public void setSelDiv(String selDiv) {
		this.selDiv = selDiv;
	}
	/**
	 * @return the hashmapDiv
	 */
	public HashMap getHashmapDiv() {
		return hashmapDiv;
	}
	/**
	 * @param hashmapDiv the hashmapDiv to set
	 */
	public void setHashmapDiv(HashMap hashmapDiv) {
		this.hashmapDiv = hashmapDiv;
	}
	/**
	 * @return the hashmapDivSel
	 */
	public HashMap getHashmapDivSel() {
		return hashmapDivSel;
	}
	/**
	 * @param hashmapDivSel the hashmapDivSel to set
	 */
	public void setHashmapDivSel(HashMap hashmapDivSel) {
		this.hashmapDivSel = hashmapDivSel;
	}
	/**
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}
	/**
	 * @param callType the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}
	/**
	 * @return the selectParentFlag
	 */
	public String getSelectParentFlag() {
		return selectParentFlag;
	}
	/**
	 * @param selectParentFlag the selectParentFlag to set
	 */
	public void setSelectParentFlag(String selectParentFlag) {
		this.selectParentFlag = selectParentFlag;
	}
	/**
	 * @return the plannedListLength
	 */
	public boolean isPlannedListLength() {
		return plannedListLength;
	}
	/**
	 * @param plannedListLength the plannedListLength to set
	 */
	public void setPlannedListLength(boolean plannedListLength) {
		this.plannedListLength = plannedListLength;
	}
	/**
	 * @return the ittFromDate
	 */
	public String getIttFromDate() {
		return ittFromDate;
	}
	/**
	 * @param ittFromDate the ittFromDate to set
	 */
	public void setIttFromDate(String ittFromDate) {
		this.ittFromDate = ittFromDate;
	}
	/**
	 * @return the ittToDate
	 */
	public String getIttToDate() {
		return ittToDate;
	}
	/**
	 * @param ittToDate the ittToDate to set
	 */
	public void setIttToDate(String ittToDate) {
		this.ittToDate = ittToDate;
	}
	/**
	 * @return the ittMiscValue
	 */
	public String getIttMiscValue() {
		return ittMiscValue;
	}
	/**
	 * @param ittMiscValue the ittMiscValue to set
	 */
	public void setIttMiscValue(String ittMiscValue) {
		this.ittMiscValue = ittMiscValue;
	}
	/**
	 * @return the ittMiscWeekendValue
	 */
	public String getIttMiscWeekendValue() {
		return ittMiscWeekendValue;
	}
	/**
	 * @param ittMiscWeekendValue the ittMiscWeekendValue to set
	 */
	public void setIttMiscWeekendValue(String ittMiscWeekendValue) {
		this.ittMiscWeekendValue = ittMiscWeekendValue;
	}
	/**
	 * @return the ittMiscId
	 */
	public String getIttMiscId() {
		return ittMiscId;
	}
	/**
	 * @param ittMiscId the ittMiscId to set
	 */
	public void setIttMiscId(String ittMiscId) {
		this.ittMiscId = ittMiscId;
	}
	/**
	 * @return the miscValue
	 */
	public String getMiscValue() {
		return miscValue;
	}
	/**
	 * @param miscValue the miscValue to set
	 */
	public void setMiscValue(String miscValue) {
		this.miscValue = miscValue;
	}
	/**
	 * @return the miscWeekendValue
	 */
	public String getMiscWeekendValue() {
		return miscWeekendValue;
	}
	/**
	 * @param miscWeekendValue the miscWeekendValue to set
	 */
	public void setMiscWeekendValue(String miscWeekendValue) {
		this.miscWeekendValue = miscWeekendValue;
	}
	/**
	 * @return the ittMiscName
	 */
	public String getIttMiscName() {
		return ittMiscName;
	}
	/**
	 * @param ittMiscName the ittMiscName to set
	 */
	public void setIttMiscName(String ittMiscName) {
		this.ittMiscName = ittMiscName;
	}
	/**
	 * @return the ittMiscCode
	 */
	public String getIttMiscCode() {
		return ittMiscCode;
	}
	/**
	 * @param ittMiscCode the ittMiscCode to set
	 */
	public void setIttMiscCode(String ittMiscCode) {
		this.ittMiscCode = ittMiscCode;
	}
	/**
	 * @return the miscName
	 */
	public String getMiscName() {
		return miscName;
	}
	/**
	 * @param miscName the miscName to set
	 */
	public void setMiscName(String miscName) {
		this.miscName = miscName;
	}
	/**
	 * @return the miscCode
	 */
	public String getMiscCode() {
		return miscCode;
	}
	/**
	 * @param miscCode the miscCode to set
	 */
	public void setMiscCode(String miscCode) {
		this.miscCode = miscCode;
	}
	/**
	 * @return the dayName
	 */
	public String getDayName() {
		return dayName;
	}
	/**
	 * @param dayName the dayName to set
	 */
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	/**
	 * @return the dateList
	 */
	public ArrayList getDateList() {
		return dateList;
	}
	/**
	 * @param dateList the dateList to set
	 */
	public void setDateList(ArrayList dateList) {
		this.dateList = dateList;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the childCode
	 */
	public String getChildCode() {
		return childCode;
	}
	/**
	 * @param childCode the childCode to set
	 */
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the iteratorList
	 */
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	/**
	 * @param iteratorList the iteratorList to set
	 */
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	/**
	 * @return the listLength
	 */
	public boolean isListLength() {
		return listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(boolean listLength) {
		this.listLength = listLength;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	/**
	 * @return the metricCode
	 */
	public String getMetricCode() {
		return metricCode;
	}
	/**
	 * @param metricCode the metricCode to set
	 */
	public void setMetricCode(String metricCode) {
		this.metricCode = metricCode;
	}
	/**
	 * @return the metricName
	 */
	public String getMetricName() {
		return metricName;
	}
	/**
	 * @param metricName the metricName to set
	 */
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	/**
	 * @return the metricAbbr
	 */
	public String getMetricAbbr() {
		return metricAbbr;
	}
	/**
	 * @param metricAbbr the metricAbbr to set
	 */
	public void setMetricAbbr(String metricAbbr) {
		this.metricAbbr = metricAbbr;
	}
	/**
	 * @return the metricsList
	 */
	public List<PerformanceMetrics> getMetricsList() {
		return metricsList;
	}
	/**
	 * @param metricsList the metricsList to set
	 */
	public void setMetricsList(List<PerformanceMetrics> metricsList) {
		this.metricsList = metricsList;
	}
	/**
	 * @return the hiddenColName
	 */
	public String getHiddenColName() {
		return hiddenColName;
	}
	/**
	 * @param hiddenColName the hiddenColName to set
	 */
	public void setHiddenColName(String hiddenColName) {
		this.hiddenColName = hiddenColName;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the titleList
	 */
	public ArrayList getTitleList() {
		return titleList;
	}
	/**
	 * @param titleList the titleList to set
	 */
	public void setTitleList(ArrayList titleList) {
		this.titleList = titleList;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
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
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return the performanceMetricsId
	 */
	public String getPerformanceMetricsId() {
		return performanceMetricsId;
	}
	/**
	 * @param performanceMetricsId the performanceMetricsId to set
	 */
	public void setPerformanceMetricsId(String performanceMetricsId) {
		this.performanceMetricsId = performanceMetricsId;
	}
	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}
	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @return the isApplyChild
	 */
	public String getIsApplyChild() {
		return isApplyChild;
	}
	/**
	 * @param isApplyChild the isApplyChild to set
	 */
	public void setIsApplyChild(String isApplyChild) {
		this.isApplyChild = isApplyChild;
	}
	
	/**
	 * @return the greenValStart
	 */
	public String getGreenValStart() {
		return greenValStart;
	}
	/**
	 * @param greenValStart the greenValStart to set
	 */
	public void setGreenValStart(String greenValStart) {
		this.greenValStart = greenValStart;
	}
	/**
	 * @return the greenValEnd
	 */
	public String getGreenValEnd() {
		return greenValEnd;
	}
	/**
	 * @param greenValEnd the greenValEnd to set
	 */
	public void setGreenValEnd(String greenValEnd) {
		this.greenValEnd = greenValEnd;
	}
	/**
	 * @return the yellowValueStart
	 */
	public String getYellowValueStart() {
		return yellowValueStart;
	}
	/**
	 * @param yellowValueStart the yellowValueStart to set
	 */
	public void setYellowValueStart(String yellowValueStart) {
		this.yellowValueStart = yellowValueStart;
	}
	/**
	 * @return the yellowValueEnd
	 */
	public String getYellowValueEnd() {
		return yellowValueEnd;
	}
	/**
	 * @param yellowValueEnd the yellowValueEnd to set
	 */
	public void setYellowValueEnd(String yellowValueEnd) {
		this.yellowValueEnd = yellowValueEnd;
	}
	/**
	 * @return the redValueStart
	 */
	public String getRedValueStart() {
		return redValueStart;
	}
	/**
	 * @param redValueStart the redValueStart to set
	 */
	public void setRedValueStart(String redValueStart) {
		this.redValueStart = redValueStart;
	}
	/**
	 * @return the redValueEnd
	 */
	public String getRedValueEnd() {
		return redValueEnd;
	}
	/**
	 * @param redValueEnd the redValueEnd to set
	 */
	public void setRedValueEnd(String redValueEnd) {
		this.redValueEnd = redValueEnd;
	}
	/**
	 * @return the viewListDtlFlag
	 */
	public boolean isViewListDtlFlag() {
		return viewListDtlFlag;
	}
	/**
	 * @param viewListDtlFlag the viewListDtlFlag to set
	 */
	public void setViewListDtlFlag(boolean viewListDtlFlag) {
		this.viewListDtlFlag = viewListDtlFlag;
	}
	/**
	 * @return the escalation
	 */
	public String getEscalation() {
		return escalation;
	}
	/**
	 * @param escalation the escalation to set
	 */
	public void setEscalation(String escalation) {
		this.escalation = escalation;
	}
	/**
	 * @return the viewPlannedDtlFlag
	 */
	public boolean isViewPlannedDtlFlag() {
		return viewPlannedDtlFlag;
	}
	/**
	 * @param viewPlannedDtlFlag the viewPlannedDtlFlag to set
	 */
	public void setViewPlannedDtlFlag(boolean viewPlannedDtlFlag) {
		this.viewPlannedDtlFlag = viewPlannedDtlFlag;
	}
	/**
	 * @return the plannedList
	 */
	public ArrayList getPlannedList() {
		return plannedList;
	}
	/**
	 * @param plannedList the plannedList to set
	 */
	public void setPlannedList(ArrayList plannedList) {
		this.plannedList = plannedList;
	}
	/**
	 * @return the parentFlag
	 */
	public String getParentFlag() {
		return parentFlag;
	}
	/**
	 * @param parentFlag the parentFlag to set
	 */
	public void setParentFlag(String parentFlag) {
		this.parentFlag = parentFlag;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return the selectChildCode
	 */
	public String getSelectChildCode() {
		return selectChildCode;
	}
	/**
	 * @param selectChildCode the selectChildCode to set
	 */
	public void setSelectChildCode(String selectChildCode) {
		this.selectChildCode = selectChildCode;
	}
	/**
	 * @return the selectChildName
	 */
	public String getSelectChildName() {
		return selectChildName;
	}
	/**
	 * @param selectChildName the selectChildName to set
	 */
	public void setSelectChildName(String selectChildName) {
		this.selectChildName = selectChildName;
	}

	public String getDashBoardID() {
		return dashBoardID;
	}
	public void setDashBoardID(String dashBoardID) {
		this.dashBoardID = dashBoardID;
	}
	public boolean isEtaReportFlagCheck() {
		return etaReportFlagCheck;
	}
	public void setEtaReportFlagCheck(boolean etaReportFlagCheck) {
		this.etaReportFlagCheck = etaReportFlagCheck;
	}
	public String getEtaFlagHidden() {
		return etaFlagHidden;
	}
	public void setEtaFlagHidden(String etaFlagHidden) {
		this.etaFlagHidden = etaFlagHidden;
	}
	public String getOrgEtaFlagHidden() {
		return orgEtaFlagHidden;
	}
	public void setOrgEtaFlagHidden(String orgEtaFlagHidden) {
		this.orgEtaFlagHidden = orgEtaFlagHidden;
	}

	public String getCaptionName() {
		return captionName;
	}
	public void setCaptionName(String captionName) {
		this.captionName = captionName;
	}
	public String getSlaType() {
		return slaType;
	}
	public void setSlaType(String slaType) {
		this.slaType = slaType;
	}
	public String getHrs() {
		return hrs;
	}
	public void setHrs(String hrs) {
		this.hrs = hrs;
	}
	public String getSlaGreenValStart() {
		return slaGreenValStart;
	}
	public void setSlaGreenValStart(String slaGreenValStart) {
		this.slaGreenValStart = slaGreenValStart;
	}
	public String getSlaGreenValEnd() {
		return slaGreenValEnd;
	}
	public void setSlaGreenValEnd(String slaGreenValEnd) {
		this.slaGreenValEnd = slaGreenValEnd;
	}
	public String getSlaYellowValStart() {
		return slaYellowValStart;
	}
	public void setSlaYellowValStart(String slaYellowValStart) {
		this.slaYellowValStart = slaYellowValStart;
	}
	public String getSlaYellowValEnd() {
		return slaYellowValEnd;
	}
	public void setSlaYellowValEnd(String slaYellowValEnd) {
		this.slaYellowValEnd = slaYellowValEnd;
	}
	public String getSlaRedValStart() {
		return slaRedValStart;
	}
	public void setSlaRedValStart(String slaRedValStart) {
		this.slaRedValStart = slaRedValStart;
	}
	public String getSlaRedValEnd() {
		return slaRedValEnd;
	}
	public void setSlaRedValEnd(String slaRedValEnd) {
		this.slaRedValEnd = slaRedValEnd;
	}
	public String getSlaCrmFlagHidden() {
		return slaCrmFlagHidden;
	}
	public void setSlaCrmFlagHidden(String slaCrmFlagHidden) {
		this.slaCrmFlagHidden = slaCrmFlagHidden;
	}
	public String getSlaClientFlagHidden() {
		return slaClientFlagHidden;
	}
	public void setSlaClientFlagHidden(String slaClientFlagHidden) {
		this.slaClientFlagHidden = slaClientFlagHidden;
	}
	public ArrayList<PerformanceMetrics> getSlaList() {
		return slaList;
	}
	public void setSlaList(ArrayList<PerformanceMetrics> slaList) {
		this.slaList = slaList;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getDashBoardName() {
		return dashBoardName;
	}
	public void setDashBoardName(String dashBoardName) {
		this.dashBoardName = dashBoardName;
	}
	/**
	 * @return the parentFlagHiddenChk
	 */
	/**
	 * @return the parentFlagHiddenChk
	 */
	public String getParentFlagHiddenChk() {
		return parentFlagHiddenChk;
	}
	/**
	 * @param parentFlagHiddenChk the parentFlagHiddenChk to set
	 */
	public void setParentFlagHiddenChk(String parentFlagHiddenChk) {
		this.parentFlagHiddenChk = parentFlagHiddenChk;
	}

	
	

	

}
