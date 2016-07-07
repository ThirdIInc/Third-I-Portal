package org.paradyne.bean.reimbursement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ReimbConfigurationBean extends BeanBase {

	/** 
	 * for Reimbursement  list
	 */
	private String ittReimbCode="";
	private String ittSrN0="";
	private String ittReimbHead="";
	private String ittReimbManagerApproval="";
	private String ittReimbAdminApproval="";
	private String ittReimbAdmin="";
	private String ittReimbAccountant="";
	
	ArrayList ReimbConfigurationItt=null ;
	
	/*
	 * for Reimbursement data 
	 * 
	 */
	private String reimbHead="";
	private String reimbManagerApproval="";
	private String reimbAdminApproval="";
	private String reimbAdmin="";
	private String reimbAccountant="";
	private String paymentModeCash="";
	private String paymentModeCheque="";
	private String paymentMode="";
	private String paymentModeSalary="";
	private String transferAccount="";
	private String reimbursementPeriod="";
	private String carrierForward="";
	private String carrierPercentage="";
	private String advanceAllowed="";
	private String startmonth="";
	private String endmonth="";
	private String yearFrom="";
	private String yearTo="";
	private String reimbAccountantReqd="";
	private String carryFwdPeriod="";
	private String advAllowedPeriod="";
	
	
	private String IdHead="";
	private String TypeHead="";
	private String EmpIdAdmin="";
	private String EmpTokenAdmin="";
	private String EmpIdAccountant="";
	private String EmpTokenAccountant="";
	
	private String myPage="";
	private String ModeId="";
	
	public String getIttReimbCode() {
		return ittReimbCode;
	}
	public void setIttReimbCode(String ittReimbCode) {
		this.ittReimbCode = ittReimbCode;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getIttReimbHead() {
		return ittReimbHead;
	}
	public void setIttReimbHead(String ittReimbHead) {
		this.ittReimbHead = ittReimbHead;
	}
	public String getIttReimbManagerApproval() {
		return ittReimbManagerApproval;
	}
	public void setIttReimbManagerApproval(String ittReimbManagerApproval) {
		this.ittReimbManagerApproval = ittReimbManagerApproval;
	}
	public String getIttReimbAdminApproval() {
		return ittReimbAdminApproval;
	}
	public void setIttReimbAdminApproval(String ittReimbAdminApproval) {
		this.ittReimbAdminApproval = ittReimbAdminApproval;
	}
	public String getIttReimbAdmin() {
		return ittReimbAdmin;
	}
	public void setIttReimbAdmin(String ittReimbAdmin) {
		this.ittReimbAdmin = ittReimbAdmin;
	}
	public String getIttReimbAccountant() {
		return ittReimbAccountant;
	}
	public void setIttReimbAccountant(String ittReimbAccountant) {
		this.ittReimbAccountant = ittReimbAccountant;
	}
	public ArrayList getReimbConfigurationItt() {
		return ReimbConfigurationItt;
	}
	public void setReimbConfigurationItt(ArrayList reimbConfigurationItt) {
		ReimbConfigurationItt = reimbConfigurationItt;
	}
	public String getReimbHead() {
		return reimbHead;
	}
	public void setReimbHead(String reimbHead) {
		this.reimbHead = reimbHead;
	}
	public String getReimbManagerApproval() {
		return reimbManagerApproval;
	}
	public void setReimbManagerApproval(String reimbManagerApproval) {
		this.reimbManagerApproval = reimbManagerApproval;
	}
	public String getReimbAdminApproval() {
		return reimbAdminApproval;
	}
	public void setReimbAdminApproval(String reimbAdminApproval) {
		this.reimbAdminApproval = reimbAdminApproval;
	}
	public String getReimbAdmin() {
		return reimbAdmin;
	}
	public void setReimbAdmin(String reimbAdmin) {
		this.reimbAdmin = reimbAdmin;
	}
	public String getReimbAccountant() {
		return reimbAccountant;
	}
	public void setReimbAccountant(String reimbAccountant) {
		this.reimbAccountant = reimbAccountant;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getTransferAccount() {
		return transferAccount;
	}
	public void setTransferAccount(String transferAccount) {
		this.transferAccount = transferAccount;
	}
	public String getReimbursementPeriod() {
		return reimbursementPeriod;
	}
	public void setReimbursementPeriod(String reimbursementPeriod) {
		this.reimbursementPeriod = reimbursementPeriod;
	}
	public String getCarrierForward() {
		return carrierForward;
	}
	public void setCarrierForward(String carrierForward) {
		this.carrierForward = carrierForward;
	}
	public String getAdvanceAllowed() {
		return advanceAllowed;
	}
	public void setAdvanceAllowed(String advanceAllowed) {
		this.advanceAllowed = advanceAllowed;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getModeId() {
		return ModeId;
	}
	public void setModeId(String modeId) {
		ModeId = modeId;
	}
	
	public String getEmpIdAdmin() {
		return EmpIdAdmin;
	}
	public void setEmpIdAdmin(String empIdAdmin) {
		EmpIdAdmin = empIdAdmin;
	}
	public String getEmpTokenAdmin() {
		return EmpTokenAdmin;
	}
	public void setEmpTokenAdmin(String empTokenAdmin) {
		EmpTokenAdmin = empTokenAdmin;
	}
	public String getEmpIdAccountant() {
		return EmpIdAccountant;
	}
	public void setEmpIdAccountant(String empIdAccountant) {
		EmpIdAccountant = empIdAccountant;
	}
	public String getEmpTokenAccountant() {
		return EmpTokenAccountant;
	}
	public void setEmpTokenAccountant(String empTokenAccountant) {
		EmpTokenAccountant = empTokenAccountant;
	}
	public String getIdHead() {
		return IdHead;
	}
	public void setIdHead(String idHead) {
		IdHead = idHead;
	}
	public String getTypeHead() {
		return TypeHead;
	}
	public void setTypeHead(String typeHead) {
		TypeHead = typeHead;
	}
	public String getCarrierPercentage() {
		return carrierPercentage;
	}
	public void setCarrierPercentage(String carrierPercentage) {
		this.carrierPercentage = carrierPercentage;
	}
	public String getPaymentModeCash() {
		return paymentModeCash;
	}
	public void setPaymentModeCash(String paymentModeCash) {
		this.paymentModeCash = paymentModeCash;
	}
	public String getPaymentModeCheque() {
		return paymentModeCheque;
	}
	public void setPaymentModeCheque(String paymentModeCheque) {
		this.paymentModeCheque = paymentModeCheque;
	}
	public String getPaymentModeSalary() {
		return paymentModeSalary;
	}
	public void setPaymentModeSalary(String paymentModeSalary) {
		this.paymentModeSalary = paymentModeSalary;
	}
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getYearFrom() {
		return yearFrom;
	}
	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}
	public String getYearTo() {
		return yearTo;
	}
	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}
	public String getReimbAccountantReqd() {
		return reimbAccountantReqd;
	}
	public void setReimbAccountantReqd(String reimbAccountantReqd) {
		this.reimbAccountantReqd = reimbAccountantReqd;
	}
	public String getCarryFwdPeriod() {
		return carryFwdPeriod;
	}
	public void setCarryFwdPeriod(String carryFwdPeriod) {
		this.carryFwdPeriod = carryFwdPeriod;
	}
	public String getAdvAllowedPeriod() {
		return advAllowedPeriod;
	}
	public void setAdvAllowedPeriod(String advAllowedPeriod) {
		this.advAllowedPeriod = advAllowedPeriod;
	}
}
