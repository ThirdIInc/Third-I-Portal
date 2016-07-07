package org.paradyne.bean.vendor;
/**
 * @ author Archana Salunkhe
 * @ purpose : Provide Invoice Acceptance Functionality
 * @ Date : 19-Apr-2012 
 */
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class InvoiceAcceptance extends BeanBase{
	
	private String partnerNameIA="";
	private String partnerCodeIA="";
	private String invoiceDateIA="";
	private String partnerTypeIA="";
	private String invoiceFromDateIA="";
	private String invoiceToDateIA="";
	private String achivementIA="";
	private String invoiceAttachedIA="";
	private String invoiceAmountIA="";
	private String partnerCommentsIA="";
	private String projectNameIA="";
	private String disburseDate="";
	private String invoiceIDIA="";
	private String invoiceStatusIA="";
	private String invoiceNumberIA="";
	private String invoiceLevelIA="";
	private ArrayList pendingList;
	private ArrayList acceptList;
	private String listType=""; 
	
	/*For Approver Comment List*/
    private String  isAppComtListIA="";
    private String approverComtIA="";
    private ArrayList aprCommentListIA;
    private String approverIDIA ="";
    private String approverNameIA="";
    private String approveDateIA="";
    private String approveStatusIA="";
    private String disburseFlg="";	
    private String accButtonFlag=""; /*To show buttons*/
    
    /*For Paging*/
    private String pendingAccLength ="false";
	private String myPage;
	
	public String getPartnerCodeIA() {
		return partnerCodeIA;
	}
	public void setPartnerCodeIA(String partnerCodeIA) {
		this.partnerCodeIA = partnerCodeIA;
	}
	public String getInvoiceDateIA() {
		return invoiceDateIA;
	}
	public void setInvoiceDateIA(String invoiceDateIA) {
		this.invoiceDateIA = invoiceDateIA;
	}
	public String getPartnerTypeIA() {
		return partnerTypeIA;
	}
	public void setPartnerTypeIA(String partnerTypeIA) {
		this.partnerTypeIA = partnerTypeIA;
	}
	public String getInvoiceFromDateIA() {
		return invoiceFromDateIA;
	}
	public void setInvoiceFromDateIA(String invoiceFromDateIA) {
		this.invoiceFromDateIA = invoiceFromDateIA;
	}
	public String getInvoiceToDateIA() {
		return invoiceToDateIA;
	}
	public void setInvoiceToDateIA(String invoiceToDateIA) {
		this.invoiceToDateIA = invoiceToDateIA;
	}
	public String getAchivementIA() {
		return achivementIA;
	}
	public void setAchivementIA(String achivementIA) {
		this.achivementIA = achivementIA;
	}
	public String getInvoiceAttachedIA() {
		return invoiceAttachedIA;
	}
	public void setInvoiceAttachedIA(String invoiceAttachedIA) {
		this.invoiceAttachedIA = invoiceAttachedIA;
	}
	public String getInvoiceAmountIA() {
		return invoiceAmountIA;
	}
	public void setInvoiceAmountIA(String invoiceAmountIA) {
		this.invoiceAmountIA = invoiceAmountIA;
	}
	public String getPartnerCommentsIA() {
		return partnerCommentsIA;
	}
	public void setPartnerCommentsIA(String partnerCommentsIA) {
		this.partnerCommentsIA = partnerCommentsIA;
	}
	public String getProjectNameIA() {
		return projectNameIA;
	}
	public void setProjectNameIA(String projectNameIA) {
		this.projectNameIA = projectNameIA;
	}
	public String getDisburseDate() {
		return disburseDate;
	}
	public void setDisburseDate(String disburseDate) {
		this.disburseDate = disburseDate;
	}
	public String getInvoiceIDIA() {
		return invoiceIDIA;
	}
	public void setInvoiceIDIA(String invoiceIDIA) {
		this.invoiceIDIA = invoiceIDIA;
	}
	public String getInvoiceStatusIA() {
		return invoiceStatusIA;
	}
	public void setInvoiceStatusIA(String invoiceStatusIA) {
		this.invoiceStatusIA = invoiceStatusIA;
	}
	public ArrayList getAcceptList() {
		return acceptList;
	}
	public void setAcceptList(ArrayList acceptList) {
		this.acceptList = acceptList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getInvoiceNumberIA() {
		return invoiceNumberIA;
	}
	public void setInvoiceNumberIA(String invoiceNumberIA) {
		this.invoiceNumberIA = invoiceNumberIA;
	}
	public String getInvoiceLevelIA() {
		return invoiceLevelIA;
	}
	public void setInvoiceLevelIA(String invoiceLevelIA) {
		this.invoiceLevelIA = invoiceLevelIA;
	}
	public String getPartnerNameIA() {
		return partnerNameIA;
	}
	public void setPartnerNameIA(String partnerNameIA) {
		this.partnerNameIA = partnerNameIA;
	}
	public String getIsAppComtListIA() {
		return isAppComtListIA;
	}
	public void setIsAppComtListIA(String isAppComtListIA) {
		this.isAppComtListIA = isAppComtListIA;
	}
	public String getApproverComtIA() {
		return approverComtIA;
	}
	public void setApproverComtIA(String approverComtIA) {
		this.approverComtIA = approverComtIA;
	}
	public ArrayList getAprCommentListIA() {
		return aprCommentListIA;
	}
	public void setAprCommentListIA(ArrayList aprCommentListIA) {
		this.aprCommentListIA = aprCommentListIA;
	}
	public String getApproverIDIA() {
		return approverIDIA;
	}
	public void setApproverIDIA(String approverIDIA) {
		this.approverIDIA = approverIDIA;
	}
	public String getApproverNameIA() {
		return approverNameIA;
	}
	public void setApproverNameIA(String approverNameIA) {
		this.approverNameIA = approverNameIA;
	}
	public String getApproveStatusIA() {
		return approveStatusIA;
	}
	public void setApproveStatusIA(String approveStatusIA) {
		this.approveStatusIA = approveStatusIA;
	}
	public String getApproveDateIA() {
		return approveDateIA;
	}
	public void setApproveDateIA(String approveDateIA) {
		this.approveDateIA = approveDateIA;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}	
	public String getAccButtonFlag() {
		return accButtonFlag;
	}
	public void setAccButtonFlag(String accButtonFlag) {
		this.accButtonFlag = accButtonFlag;
	}
	public String getDisburseFlg() {
		return disburseFlg;
	}
	public void setDisburseFlg(String disburseFlg) {
		this.disburseFlg = disburseFlg;
	}
	public String getPendingAccLength() {
		return pendingAccLength;
	}
	public void setPendingAccLength(String pendingAccLength) {
		this.pendingAccLength = pendingAccLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
}
