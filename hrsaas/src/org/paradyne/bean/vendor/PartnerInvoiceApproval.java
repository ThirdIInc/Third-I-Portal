package org.paradyne.bean.vendor;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PartnerInvoiceApproval extends BeanBase {
	
	private ArrayList appPendingList=null;
	private String partnerCodeApp ="";
	private String invoicePartnerNameApp ="";
	private String invoiceIDApp ="";
	private String invoiceDateApp ="";
	private String invoiceAppStatusApp ="";
	private String appStatus="";
	private String listType = "";
	private ArrayList approvedListApp = null;
	private ArrayList rejectedListApp = null;
	private String approverCommentsApp=null;	
	
	/*For Paging*/
	private String pendingAppLength ="false";
	private String approvedLength="false";
	private String rejectedLength="false";
	private String myPage;
				
	public String getApproverCommentsApp() {
		return approverCommentsApp;
	}
	public void setApproverCommentsApp(String approverCommentsApp) {
		this.approverCommentsApp = approverCommentsApp;
	}
	public ArrayList getAppPendingList() {
		return appPendingList;
	}
	public void setAppPendingList(ArrayList appPendingList) {
		this.appPendingList = appPendingList;
	}
	public String getPartnerCodeApp() {
		return partnerCodeApp;
	}
	public void setPartnerCodeApp(String partnerCodeApp) {
		this.partnerCodeApp = partnerCodeApp;
	}
	public String getInvoicePartnerNameApp() {
		return invoicePartnerNameApp;
	}
	public void setInvoicePartnerNameApp(String invoicePartnerNameApp) {
		this.invoicePartnerNameApp = invoicePartnerNameApp;
	}
	public String getInvoiceIDApp() {
		return invoiceIDApp;
	}
	public void setInvoiceIDApp(String invoiceIDApp) {
		this.invoiceIDApp = invoiceIDApp;
	}
	public String getInvoiceDateApp() {
		return invoiceDateApp;
	}
	public void setInvoiceDateApp(String invoiceDateApp) {
		this.invoiceDateApp = invoiceDateApp;
	}
	public String getInvoiceAppStatusApp() {
		return invoiceAppStatusApp;
	}
	public void setInvoiceAppStatusApp(String invoiceAppStatusApp) {
		this.invoiceAppStatusApp = invoiceAppStatusApp;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}	
	public ArrayList getApprovedListApp() {
		return approvedListApp;
	}
	public void setApprovedListApp(ArrayList approvedListApp) {
		this.approvedListApp = approvedListApp;
	}
	public ArrayList getRejectedListApp() {
		return rejectedListApp;
	}
	public void setRejectedListApp(ArrayList rejectedListApp) {
		this.rejectedListApp = rejectedListApp;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getPendingAppLength() {
		return pendingAppLength;
	}
	public void setPendingAppLength(String pendingAppLength) {
		this.pendingAppLength = pendingAppLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getApprovedLength() {
		return approvedLength;
	}
	public void setApprovedLength(String approvedLength) {
		this.approvedLength = approvedLength;
	}
	public String getRejectedLength() {
		return rejectedLength;
	}
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}
}
