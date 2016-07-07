/**
 * 
 */
package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1439
 *
 */
public class TravelApplicationAppBean extends BeanBase{
	private String authorizedToken;
	private String travelId;
	private String empToken;
	private String empName;
	private String applicationDate;
	private String flag="";
	private List pendingAppList;
	private List pendingCancelAppList;
	private List approvedAppList;
	private List rejectedAppList;

	private boolean pagingForPendingApp;
	private boolean pagingForPendingCancelApp;
	private boolean pagingForApprovedApp;
	private boolean pagingForRejectedApp;

	private String pageForPendingApp;
	private String pageForPendingCancelApp;
	private String pageForApprovedApp;
	private String pageForRejectedApp;

	private String listType;
	private String destinationItr = "";
	private String fromTravelDateItr = "";
	

	/**
	 * @return the destinationItr
	 */
	public String getDestinationItr() {
		return this.destinationItr;
	}

	/**
	 * @param destinationItr the destinationItr to set
	 */
	public void setDestinationItr(String destinationItr) {
		this.destinationItr = destinationItr;
	}

	/**
	 * @return the fromTravelDateItr
	 */
	public String getFromTravelDateItr() {
		return this.fromTravelDateItr;
	}

	/**
	 * @param fromTravelDateItr the fromTravelDateItr to set
	 */
	public void setFromTravelDateItr(String fromTravelDateItr) {
		this.fromTravelDateItr = fromTravelDateItr;
	}

	/**
	 * @return the travelId
	 */
	public String getTravelId() {
		return travelId;
	}

	/**
	 * @param travelId the travelId to set
	 */
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @return the pendingAppList
	 */
	public List getPendingAppList() {
		return pendingAppList;
	}

	/**
	 * @param pendingAppList the pendingAppList to set
	 */
	public void setPendingAppList(List pendingAppList) {
		this.pendingAppList = pendingAppList;
	}

	/**
	 * @return the pendingCancelAppList
	 */
	public List getPendingCancelAppList() {
		return pendingCancelAppList;
	}

	/**
	 * @param pendingCancelAppList the pendingCancelAppList to set
	 */
	public void setPendingCancelAppList(List pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}

	/**
	 * @return the approvedAppList
	 */
	public List getApprovedAppList() {
		return approvedAppList;
	}

	/**
	 * @param approvedAppList the approvedAppList to set
	 */
	public void setApprovedAppList(List approvedAppList) {
		this.approvedAppList = approvedAppList;
	}

	/**
	 * @return the rejectedAppList
	 */
	public List getRejectedAppList() {
		return rejectedAppList;
	}

	/**
	 * @param rejectedAppList the rejectedAppList to set
	 */
	public void setRejectedAppList(List rejectedAppList) {
		this.rejectedAppList = rejectedAppList;
	}

	/**
	 * @return the pagingForPendingApp
	 */
	public boolean isPagingForPendingApp() {
		return pagingForPendingApp;
	}

	/**
	 * @param pagingForPendingApp the pagingForPendingApp to set
	 */
	public void setPagingForPendingApp(boolean pagingForPendingApp) {
		this.pagingForPendingApp = pagingForPendingApp;
	}

	/**
	 * @return the pagingForPendingCancelApp
	 */
	public boolean isPagingForPendingCancelApp() {
		return pagingForPendingCancelApp;
	}

	/**
	 * @param pagingForPendingCancelApp the pagingForPendingCancelApp to set
	 */
	public void setPagingForPendingCancelApp(boolean pagingForPendingCancelApp) {
		this.pagingForPendingCancelApp = pagingForPendingCancelApp;
	}

	/**
	 * @return the pagingForApprovedApp
	 */
	public boolean isPagingForApprovedApp() {
		return pagingForApprovedApp;
	}

	/**
	 * @param pagingForApprovedApp the pagingForApprovedApp to set
	 */
	public void setPagingForApprovedApp(boolean pagingForApprovedApp) {
		this.pagingForApprovedApp = pagingForApprovedApp;
	}

	/**
	 * @return the pagingForRejectedApp
	 */
	public boolean isPagingForRejectedApp() {
		return pagingForRejectedApp;
	}

	/**
	 * @param pagingForRejectedApp the pagingForRejectedApp to set
	 */
	public void setPagingForRejectedApp(boolean pagingForRejectedApp) {
		this.pagingForRejectedApp = pagingForRejectedApp;
	}

	/**
	 * @return the pageForPendingApp
	 */
	public String getPageForPendingApp() {
		return pageForPendingApp;
	}

	/**
	 * @param pageForPendingApp the pageForPendingApp to set
	 */
	public void setPageForPendingApp(String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}

	/**
	 * @return the pageForPendingCancelApp
	 */
	public String getPageForPendingCancelApp() {
		return pageForPendingCancelApp;
	}

	/**
	 * @param pageForPendingCancelApp the pageForPendingCancelApp to set
	 */
	public void setPageForPendingCancelApp(String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}

	/**
	 * @return the pageForApprovedApp
	 */
	public String getPageForApprovedApp() {
		return pageForApprovedApp;
	}

	/**
	 * @param pageForApprovedApp the pageForApprovedApp to set
	 */
	public void setPageForApprovedApp(String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}

	/**
	 * @return the pageForRejectedApp
	 */
	public String getPageForRejectedApp() {
		return pageForRejectedApp;
	}

	/**
	 * @param pageForRejectedApp the pageForRejectedApp to set
	 */
	public void setPageForRejectedApp(String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
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
	 * @return the authorizedToken
	 */
	public String getAuthorizedToken() {
		return authorizedToken;
	}

	/**
	 * @param authorizedToken the authorizedToken to set
	 */
	public void setAuthorizedToken(String authorizedToken) {
		this.authorizedToken = authorizedToken;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
