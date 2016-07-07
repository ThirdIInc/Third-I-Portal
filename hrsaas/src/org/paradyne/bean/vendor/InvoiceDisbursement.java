/**
 * 
 */
package org.paradyne.bean.vendor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0491
 *
 */
public class InvoiceDisbursement extends BeanBase {
	
	private String disburseCode ="";
	private String prevApproverID  ="";
	private String prevApproverName ="";
	private String prevApproverDate ="";
	private String prevApproverStatus ="";
	private String prevApproverComment ="";
	private ArrayList  approverCommentList =null ;
	private String balanceAmount ="";
	private String totalAmount ="";
	
	private String hiddenInvoiceCode="";
	private String totDisbrAmt="";
	private String paraId="";
	ArrayList paymentList = new ArrayList();
	private String paymentDet="";
	private String disburseMode="";
	private String editButton="false";
	private String disburseFlag="true";
	private String disbBalId="";
	private String disbBalDate="";
	private String disbBalAmt="";
	private String disbBalCmt="";
	private String chqDate="";
	private String chqNo="";
	private String disbBalPayMode="";
	private String bankId="";
	private String bankName="";
		private String accountNo="";
	///

	private String totalInvoiceDisbrAmt = "";
	private String partnerName = "";
	private String partnerCode = "";
	private String partnerDate = "";
	private String partnerType = "";
	private String partnerInvoiceFrmDate = "";
	private String partnerProject = "";
	private String partnerInvoiceToDate = "";
	TreeMap payModeMap;
	private String listType = "";
	private String status = "";
	private String myPage ="";
	private String vendorCodeItt = "";
	private String invoiceDateItt = "";
	private String disburseAmountItt = "";
	private String statusItt = "";
	private String vendorNameItt = "";
	private ArrayList vendorDisbrList = null;
	
	private String disburseDate = "";
	private String disburseChqDate = "";
	private String disburseChqNo = "";
	private String disburseAccount = "";
	private String disburseAmount = "";
	private String disbursementComment = "";
	private String descCnt1 = "";
	
	private String bankCode = "";
	private String bankIFSCCode = "";
	private String disburseBankName = "";
	private String bankBranch = "";
	private String bankBSRCode = "";
	
	
	
	
	
	public String getDisburseDate() {
		return disburseDate;
	}
	public void setDisburseDate(String disburseDate) {
		this.disburseDate = disburseDate;
	}
	public String getDisburseChqDate() {
		return disburseChqDate;
	}
	public void setDisburseChqDate(String disburseChqDate) {
		this.disburseChqDate = disburseChqDate;
	}
	public String getDisburseChqNo() {
		return disburseChqNo;
	}
	public void setDisburseChqNo(String disburseChqNo) {
		this.disburseChqNo = disburseChqNo;
	}
	public String getDisburseAccount() {
		return disburseAccount;
	}
	public void setDisburseAccount(String disburseAccount) {
		this.disburseAccount = disburseAccount;
	}
	public String getDisburseAmount() {
		return disburseAmount;
	}
	public void setDisburseAmount(String disburseAmount) {
		this.disburseAmount = disburseAmount;
	}
	public String getDisbursementComment() {
		return disbursementComment;
	}
	public void setDisbursementComment(String disbursementComment) {
		this.disbursementComment = disbursementComment;
	}
	public String getDescCnt1() {
		return descCnt1;
	}
	public void setDescCnt1(String descCnt1) {
		this.descCnt1 = descCnt1;
	}
	public String getVendorCodeItt() {
		return vendorCodeItt;
	}
	public void setVendorCodeItt(String vendorCodeItt) {
		this.vendorCodeItt = vendorCodeItt;
	}
	public String getInvoiceDateItt() {
		return invoiceDateItt;
	}
	public void setInvoiceDateItt(String invoiceDateItt) {
		this.invoiceDateItt = invoiceDateItt;
	}
	public String getDisburseAmountItt() {
		return disburseAmountItt;
	}
	public void setDisburseAmountItt(String disburseAmountItt) {
		this.disburseAmountItt = disburseAmountItt;
	}
	public String getStatusItt() {
		return statusItt;
	}
	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}
	public String getVendorNameItt() {
		return vendorNameItt;
	}
	public void setVendorNameItt(String vendorNameItt) {
		this.vendorNameItt = vendorNameItt;
	}
	public ArrayList getVendorDisbrList() {
		return vendorDisbrList;
	}
	public void setVendorDisbrList(ArrayList vendorDisbrList) {
		this.vendorDisbrList = vendorDisbrList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public TreeMap getPayModeMap() {
		return payModeMap;
	}
	public void setPayModeMap(TreeMap payModeMap) {
		this.payModeMap = payModeMap;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getPartnerDate() {
		return partnerDate;
	}
	public void setPartnerDate(String partnerDate) {
		this.partnerDate = partnerDate;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getPartnerInvoiceFrmDate() {
		return partnerInvoiceFrmDate;
	}
	public void setPartnerInvoiceFrmDate(String partnerInvoiceFrmDate) {
		this.partnerInvoiceFrmDate = partnerInvoiceFrmDate;
	}
	public String getPartnerProject() {
		return partnerProject;
	}
	public void setPartnerProject(String partnerProject) {
		this.partnerProject = partnerProject;
	}
	public String getPartnerInvoiceToDate() {
		return partnerInvoiceToDate;
	}
	public void setPartnerInvoiceToDate(String partnerInvoiceToDate) {
		this.partnerInvoiceToDate = partnerInvoiceToDate;
	}
	public String getTotalInvoiceDisbrAmt() {
		return totalInvoiceDisbrAmt;
	}
	public void setTotalInvoiceDisbrAmt(String totalInvoiceDisbrAmt) {
		this.totalInvoiceDisbrAmt = totalInvoiceDisbrAmt;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankIFSCCode() {
		return bankIFSCCode;
	}
	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}
	public String getDisburseBankName() {
		return disburseBankName;
	}
	public void setDisburseBankName(String disburseBankName) {
		this.disburseBankName = disburseBankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankBSRCode() {
		return bankBSRCode;
	}
	public void setBankBSRCode(String bankBSRCode) {
		this.bankBSRCode = bankBSRCode;
	}
	public String getEditButton() {
		return editButton;
	}
	public void setEditButton(String editButton) {
		this.editButton = editButton;
	}
	public String getDisburseFlag() {
		return disburseFlag;
	}
	public void setDisburseFlag(String disburseFlag) {
		this.disburseFlag = disburseFlag;
	}
	public String getDisbBalId() {
		return disbBalId;
	}
	public void setDisbBalId(String disbBalId) {
		this.disbBalId = disbBalId;
	}
	public String getDisbBalDate() {
		return disbBalDate;
	}
	public void setDisbBalDate(String disbBalDate) {
		this.disbBalDate = disbBalDate;
	}
	public String getDisbBalAmt() {
		return disbBalAmt;
	}
	public void setDisbBalAmt(String disbBalAmt) {
		this.disbBalAmt = disbBalAmt;
	}
	public String getDisbBalCmt() {
		return disbBalCmt;
	}
	public void setDisbBalCmt(String disbBalCmt) {
		this.disbBalCmt = disbBalCmt;
	}
	public String getChqDate() {
		return chqDate;
	}
	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}
	public String getChqNo() {
		return chqNo;
	}
	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}
	public String getDisbBalPayMode() {
		return disbBalPayMode;
	}
	public void setDisbBalPayMode(String disbBalPayMode) {
		this.disbBalPayMode = disbBalPayMode;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getDisburseMode() {
		return disburseMode;
	}
	public void setDisburseMode(String disburseMode) {
		this.disburseMode = disburseMode;
	}
	public String getPaymentDet() {
		return paymentDet;
	}
	public void setPaymentDet(String paymentDet) {
		this.paymentDet = paymentDet;
	}
	public ArrayList getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getTotDisbrAmt() {
		return totDisbrAmt;
	}
	public void setTotDisbrAmt(String totDisbrAmt) {
		this.totDisbrAmt = totDisbrAmt;
	}
	public String getHiddenInvoiceCode() {
		return hiddenInvoiceCode;
	}
	public void setHiddenInvoiceCode(String hiddenInvoiceCode) {
		this.hiddenInvoiceCode = hiddenInvoiceCode;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPrevApproverID() {
		return prevApproverID;
	}
	public void setPrevApproverID(String prevApproverID) {
		this.prevApproverID = prevApproverID;
	}
	public String getPrevApproverName() {
		return prevApproverName;
	}
	public void setPrevApproverName(String prevApproverName) {
		this.prevApproverName = prevApproverName;
	}
	public String getPrevApproverDate() {
		return prevApproverDate;
	}
	public void setPrevApproverDate(String prevApproverDate) {
		this.prevApproverDate = prevApproverDate;
	}
	public String getPrevApproverStatus() {
		return prevApproverStatus;
	}
	public void setPrevApproverStatus(String prevApproverStatus) {
		this.prevApproverStatus = prevApproverStatus;
	}
	public String getPrevApproverComment() {
		return prevApproverComment;
	}
	public void setPrevApproverComment(String prevApproverComment) {
		this.prevApproverComment = prevApproverComment;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getDisburseCode() {
		return disburseCode;
	}
	public void setDisburseCode(String disburseCode) {
		this.disburseCode = disburseCode;
	}
}
