package org.paradyne.bean.LMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class ReturnActBean extends BeanBase {
	
	private String listType;
	private String srNo;
	private String returnID;
	private String returnCode;
	private String returnMasterId;
	private String returnFrequency;
	private String returnName;
	private String returnCompany;
	private String financialYear;
	private String returnStatus;
	private boolean orgFlag = false;
	private String orgName;
	private String orgId;
	ArrayList<Object> pendingReturnsList;
	ArrayList<Object> returnActNames;
	LinkedHashMap<String, String> actMap;

	private String pageToShow;
    private String previousPage;
    private String frequency = "";
    private String fromPeriod = "";
    private String toPeriod = "";
	
    
    /**
	 * @return the previousPage
	 */
	public String getPreviousPage() {
		return previousPage;
	}

	/**
	 * @param previousPage the previousPage to set
	 */
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * @return the pageToShow
	 */
	public String getPageToShow() {
		return pageToShow;
	}

	/**
	 * @param pageToShow
	 *            the pageToShow to set
	 */
	public void setPageToShow(String pageToShow) {
		this.pageToShow = pageToShow;
	}

	

	

	

	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}

	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	/**
	 * @return the returnID
	 */
	public String getReturnID() {
		return returnID;
	}

	/**
	 * @param returnID the returnID to set
	 */
	public void setReturnID(String returnID) {
		this.returnID = returnID;
	}

	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the returnMasterId
	 */
	public String getReturnMasterId() {
		return returnMasterId;
	}

	/**
	 * @param returnMasterId the returnMasterId to set
	 */
	public void setReturnMasterId(String returnMasterId) {
		this.returnMasterId = returnMasterId;
	}

	/**
	 * @return the returnFrequency
	 */
	public String getReturnFrequency() {
		return returnFrequency;
	}

	/**
	 * @param returnFrequency the returnFrequency to set
	 */
	public void setReturnFrequency(String returnFrequency) {
		this.returnFrequency = returnFrequency;
	}

	/**
	 * @return the returnName
	 */
	public String getReturnName() {
		return returnName;
	}

	/**
	 * @param returnName the returnName to set
	 */
	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	/**
	 * @return the financialYear
	 */
	public String getFinancialYear() {
		return financialYear;
	}

	/**
	 * @param financialYear the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return the returnStatus
	 */
	public String getReturnStatus() {
		return returnStatus;
	}

	/**
	 * @param returnStatus the returnStatus to set
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	/**
	 * @return the pendingReturnsList
	 */
	public ArrayList<Object> getPendingReturnsList() {
		return pendingReturnsList;
	}

	/**
	 * @param pendingReturnsList the pendingReturnsList to set
	 */
	public void setPendingReturnsList(ArrayList<Object> pendingReturnsList) {
		this.pendingReturnsList = pendingReturnsList;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgFlag
	 */
	public boolean isOrgFlag() {
		return orgFlag;
	}

	/**
	 * @param orgFlag the orgFlag to set
	 */
	public void setOrgFlag(boolean orgFlag) {
		this.orgFlag = orgFlag;
	}

	public ArrayList<Object> getReturnActNames() {
		return returnActNames;
	}

	public void setReturnActNames(ArrayList<Object> returnActNames) {
		this.returnActNames = returnActNames;
	}

	public LinkedHashMap<String, String> getActMap() {
		return actMap;
	}

	public void setActMap(LinkedHashMap<String, String> actMap) {
		this.actMap = actMap;
	}

	public String getReturnCompany() {
		return returnCompany;
	}

	public void setReturnCompany(String returnCompany) {
		this.returnCompany = returnCompany;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public String getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}

	

	

}
