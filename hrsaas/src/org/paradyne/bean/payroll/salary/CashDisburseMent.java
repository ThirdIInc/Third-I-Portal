package org.paradyne.bean.payroll.salary;
import java.util.ArrayList;
/*
 * author:Pradeep 
 * Date:02-04-2008
 */

import org.paradyne.lib.BeanBase;
public class CashDisburseMent extends BeanBase {
	
	
	
	private String empId="";
	private String empToken="";
	private String empName="";
	private String claimDate="";
	private String claimParticulars="";
	private String claimAmt="";
	private String pmChkFlag="";
	private String status="";
	private String fromDate="";
	private String toDate="";
	private String flag="";
	ArrayList disburseList=null;
	
	private String listType = "";
	private String itClmAppId = "";
	
	private String closedEmpId="";
	private String closedClmAppId = "";
	private String closedLength = "false";
	ArrayList<Object> closedList = null;
	private String myPage = "";
	
	private String viewEmpToken = "";
	private String viewEmpName = "";
	private String viewEmpId = "";
	private String branchName = "";
	private String departmentName = "";
	private String divisionName = "";
	private String designationName = "";
	private String disburseDate = "";
	private String disburseStatus = "";
	private String paymentDate = "";
	private String paymentmode = "";
	private String chequeNumber = "";
	private String chequeDate = "";
	private String accountNo = "";
	private String bank = "";
	private String bankid = "";
	private String disburseAmount = "";
	private String comment = "";
	private String claimId = "";
	
	
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getClosedEmpId() {
		return closedEmpId;
	}
	public void setClosedEmpId(String closedEmpId) {
		this.closedEmpId = closedEmpId;
	}
	public String getClosedClmAppId() {
		return closedClmAppId;
	}
	public void setClosedClmAppId(String closedClmAppId) {
		this.closedClmAppId = closedClmAppId;
	}
	public String getClosedLength() {
		return closedLength;
	}
	public void setClosedLength(String closedLength) {
		this.closedLength = closedLength;
	}
	public ArrayList<Object> getClosedList() {
		return closedList;
	}
	public void setClosedList(ArrayList<Object> closedList) {
		this.closedList = closedList;
	}
	public String getItClmAppId() {
		return itClmAppId;
	}
	public void setItClmAppId(String itClmAppId) {
		this.itClmAppId = itClmAppId;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList getDisburseList() {
		return disburseList;
	}
	public void setDisburseList(ArrayList disburseList) {
		this.disburseList = disburseList;
	}
	public CashDisburseMent(String empId, String empToken, String empName,
			String claimDate, String claimParticulars, String claimAmt,
			String pmChkFlag, ArrayList disburseList) {
		super();
		this.empId = empId;
		this.empToken = empToken;
		this.empName = empName;
		this.claimDate = claimDate;
		this.claimParticulars = claimParticulars;
		this.claimAmt = claimAmt;
		this.pmChkFlag = pmChkFlag;
		this.disburseList = disburseList;
	}
	public CashDisburseMent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	public String getClaimParticulars() {
		return claimParticulars;
	}
	public void setClaimParticulars(String claimParticulars) {
		this.claimParticulars = claimParticulars;
	}
	public String getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}
	public String getPmChkFlag() {
		return pmChkFlag;
	}
	public void setPmChkFlag(String pmChkFlag) {
		this.pmChkFlag = pmChkFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getViewEmpToken() {
		return viewEmpToken;
	}
	public void setViewEmpToken(String viewEmpToken) {
		this.viewEmpToken = viewEmpToken;
	}
	public String getViewEmpName() {
		return viewEmpName;
	}
	public void setViewEmpName(String viewEmpName) {
		this.viewEmpName = viewEmpName;
	}
	public String getViewEmpId() {
		return viewEmpId;
	}
	public void setViewEmpId(String viewEmpId) {
		this.viewEmpId = viewEmpId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getDisburseDate() {
		return disburseDate;
	}
	public void setDisburseDate(String disburseDate) {
		this.disburseDate = disburseDate;
	}
	public String getDisburseStatus() {
		return disburseStatus;
	}
	public void setDisburseStatus(String disburseStatus) {
		this.disburseStatus = disburseStatus;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getDisburseAmount() {
		return disburseAmount;
	}
	public void setDisburseAmount(String disburseAmount) {
		this.disburseAmount = disburseAmount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
