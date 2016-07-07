package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1439/aa1381.
 *
 */
public class CDRomApprovalBean extends BeanBase {
	
	/**
	 * CD Rom hidden id.
	 */
	private String cdRomID;
	/**
	 * Emp Token.
	 */
	private String empToken;
	/**
	 * Emp Name.
	 */
	private String empName;
	/**
	 * Application Date.
	 */
	private String applicationDate;
	
	/**
	 * List Fields.
	 */
	
	/**
	 * Pending Appln List.
	 */
	private List<CDRomApprovalBean> pendingAppList;
	/**
	 * Pending Cancel Appln List.
	 */
	private List<CDRomApprovalBean> pendingCancelAppList;
	/**
	 * Approved List.
	 */
	private List<CDRomApprovalBean> approvedAppList;
	/**
	 * Rejected List.
	 */
	private List<CDRomApprovalBean> rejectedAppList;

	/**
	 * for display paging purpose. 
	 */
	
	/**
	 * Paging For Pending Application.
	 */
	private boolean pagingForPendingApp;
	/**
	 * Paging For Pending Cancel Application.
	 */
	private boolean pagingForPendingCancelApp;
	/**
	 * Paging For Approved Application.
	 */
	private boolean pagingForApprovedApp;
	/**
	 * Paging For Rejected Application.
	 */
	private boolean pagingForRejectedApp;

	/**
	 * For Paging purpose fields.
	 */
	
	/**
	 * Page For Pending Application.
	 */
	private String pageForPendingApp;
	/**
	 * Page For Pending Cancel Application.
	 */
	private String pageForPendingCancelApp;
	/**
	 * Page For Approved Application.
	 */
	private String pageForApprovedApp;
	/**
	 * Page For Rejected Application.
	 */
	private String pageForRejectedApp;

	/**
	 * List Type hidden field.
	 */
	private String listType;
	/**
	 * Authorized Token.
	 */
	private String authorizedToken;

	/**
	 * Added by Nilesh.
	 */ 
	
	/**
	 * Application Status(P/D/A/C/R/X).
	 */
	private String applnStatus = "";
	/**
	 * Data path.
	 */
	private String dataPath = "";
	/**
	 * added by nilesh for addressInfoFile1 & addressInfoFile2 hidden data path fields.
	 */
	
	/**
	 * Data Path 1.
	 */
	private String dataPath1 = "";
	/**
	 * Data Path 2.
	 */
	private String dataPath2 = "";
	/**
	 * Data Path Addressing.
	 */
	private String dataPathAddressing = "";
	/**
	 * Data Path Additional.
	 */
	private String dataPathAdditional = "";
	/**
	 *Request Detail File.
	 */
	private String requestDetailFile = "";
	/**
	 * Address Info.
	 */
	private String addressInfoFile1 = "";
	/**
	 * Data Path 1.
	 */
	private String addressInfoFile2 = "";
	
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}

	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @return the dataPath1
	 */
	public String getDataPath1() {
		return this.dataPath1;
	}

	/**
	 * @param dataPath1 the dataPath1 to set
	 */
	public void setDataPath1(final String dataPath1) {
		this.dataPath1 = dataPath1;
	}

	/**
	 * @return the dataPath2
	 */
	public String getDataPath2() {
		return this.dataPath2;
	}

	/**
	 * @param dataPath2 the dataPath2 to set
	 */
	public void setDataPath2(final String dataPath2) {
		this.dataPath2 = dataPath2;
	}

	/**
	 * @return the requestDetailFile
	 */
	public String getRequestDetailFile() {
		return this.requestDetailFile;
	}

	/**
	 * @param requestDetailFile the requestDetailFile to set
	 */
	public void setRequestDetailFile(final String requestDetailFile) {
		this.requestDetailFile = requestDetailFile;
	}

	/**
	 * @return the addressInfoFile1
	 */
	public String getAddressInfoFile1() {
		return this.addressInfoFile1;
	}

	/**
	 * @param addressInfoFile1 the addressInfoFile1 to set
	 */
	public void setAddressInfoFile1(final String addressInfoFile1) {
		this.addressInfoFile1 = addressInfoFile1;
	}

	/**
	 * @return the addressInfoFile2
	 */
	public String getAddressInfoFile2() {
		return this.addressInfoFile2;
	}

	/**
	 * @param addressInfoFile2 the addressInfoFile2 to set
	 */
	public void setAddressInfoFile2(final String addressInfoFile2) {
		this.addressInfoFile2 = addressInfoFile2;
	}

	/**
	 * @return the authorizedToken
	 */
	public String getAuthorizedToken() {
		return this.authorizedToken;
	}

	/**
	 * @param authorizedToken the authorizedToken to set
	 */
	public void setAuthorizedToken(final String authorizedToken) {
		this.authorizedToken = authorizedToken;
	}

	/**
	 * @return the cdRomID
	 */
	public String getCdRomID() {
		return this.cdRomID;
	}

	/**
	 * @param cdRomID the cdRomID to set
	 */
	public void setCdRomID(final String cdRomID) {
		this.cdRomID = cdRomID;
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
	 * @return the pendingAppList
	 */
	public List<CDRomApprovalBean> getPendingAppList() {
		return this.pendingAppList;
	}

	/**
	 * @param pendingAppList the pendingAppList to set
	 */
	public void setPendingAppList(final List<CDRomApprovalBean> pendingAppList) {
		this.pendingAppList = pendingAppList;
	}

	/**
	 * @return the pendingCancelAppList
	 */
	public List<CDRomApprovalBean> getPendingCancelAppList() {
		return this.pendingCancelAppList;
	}

	/**
	 * @param pendingCancelAppList the pendingCancelAppList to set
	 */
	public void setPendingCancelAppList(final List<CDRomApprovalBean> pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}

	/**
	 * @return the approvedAppList
	 */
	public List<CDRomApprovalBean> getApprovedAppList() {
		return this.approvedAppList;
	}

	/**
	 * @param approvedAppList the approvedAppList to set
	 */
	public void setApprovedAppList(final List<CDRomApprovalBean> approvedAppList) {
		this.approvedAppList = approvedAppList;
	}

	/**
	 * @return the rejectedAppList
	 */
	public List<CDRomApprovalBean> getRejectedAppList() {
		return this.rejectedAppList;
	}

	/**
	 * @param rejectedAppList the rejectedAppList to set
	 */
	public void setRejectedAppList(final List<CDRomApprovalBean> rejectedAppList) {
		this.rejectedAppList = rejectedAppList;
	}

	/**
	 * @return the pagingForPendingApp
	 */
	public boolean isPagingForPendingApp() {
		return this.pagingForPendingApp;
	}

	/**
	 * @param pagingForPendingApp the pagingForPendingApp to set
	 */
	public void setPagingForPendingApp(final boolean pagingForPendingApp) {
		this.pagingForPendingApp = pagingForPendingApp;
	}

	/**
	 * @return the pagingForPendingCancelApp
	 */
	public boolean isPagingForPendingCancelApp() {
		return this.pagingForPendingCancelApp;
	}

	/**
	 * @param pagingForPendingCancelApp the pagingForPendingCancelApp to set
	 */
	public void setPagingForPendingCancelApp(final boolean pagingForPendingCancelApp) {
		this.pagingForPendingCancelApp = pagingForPendingCancelApp;
	}

	/**
	 * @return the pagingForApprovedApp
	 */
	public boolean isPagingForApprovedApp() {
		return this.pagingForApprovedApp;
	}

	/**
	 * @param pagingForApprovedApp the pagingForApprovedApp to set
	 */
	public void setPagingForApprovedApp(final boolean pagingForApprovedApp) {
		this.pagingForApprovedApp = pagingForApprovedApp;
	}

	/**
	 * @return the pagingForRejectedApp
	 */
	public boolean isPagingForRejectedApp() {
		return this.pagingForRejectedApp;
	}

	/**
	 * @param pagingForRejectedApp the pagingForRejectedApp to set
	 */
	public void setPagingForRejectedApp(final boolean pagingForRejectedApp) {
		this.pagingForRejectedApp = pagingForRejectedApp;
	}

	/**
	 * @return the pageForPendingApp
	 */
	public String getPageForPendingApp() {
		return this.pageForPendingApp;
	}

	/**
	 * @param pageForPendingApp the pageForPendingApp to set
	 */
	public void setPageForPendingApp(final String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}

	/**
	 * @return the pageForPendingCancelApp
	 */
	public String getPageForPendingCancelApp() {
		return this.pageForPendingCancelApp;
	}

	/**
	 * @param pageForPendingCancelApp the pageForPendingCancelApp to set
	 */
	public void setPageForPendingCancelApp(final String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}

	/**
	 * @return the pageForApprovedApp
	 */
	public String getPageForApprovedApp() {
		return this.pageForApprovedApp;
	}

	/**
	 * @param pageForApprovedApp the pageForApprovedApp to set
	 */
	public void setPageForApprovedApp(final String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}

	/**
	 * @return the pageForRejectedApp
	 */
	public String getPageForRejectedApp() {
		return this.pageForRejectedApp;
	}

	/**
	 * @param pageForRejectedApp the pageForRejectedApp to set
	 */
	public void setPageForRejectedApp(final String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return this.listType;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}

	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}

	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(final String applnStatus) {
		this.applnStatus = applnStatus;
	}

	/**
	 * @return the dataPathAddressing
	 */
	public String getDataPathAddressing() {
		return this.dataPathAddressing;
	}

	/**
	 * @param dataPathAddressing the dataPathAddressing to set
	 */
	public void setDataPathAddressing(final String dataPathAddressing) {
		this.dataPathAddressing = dataPathAddressing;
	}

	/**
	 * @return the dataPathAdditional
	 */
	public String getDataPathAdditional() {
		return this.dataPathAdditional;
	}

	/**
	 * @param dataPathAdditional the dataPathAdditional to set
	 */
	public void setDataPathAdditional(final String dataPathAdditional) {
		this.dataPathAdditional = dataPathAdditional;
	}
}
