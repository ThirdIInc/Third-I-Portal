package org.paradyne.bean.admin.srd;

import java.io.Serializable;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 *  @author pradeep k s
 *  Date:08-07-2007
 *
 */

public class EmpLic extends BeanBase implements Serializable   {
	
	String licId;
	String licIdHead;
	String empId;
	String empName;
	String empToken;
	String empCent;
	String empRank;
	String licName;
	String licPolicyNo;
	String insuranceAmt;
	String licPremiumAmt;
	String licStDate;
	String licEndDt;
	String taxExempt;
	String paidInSal;	
	String paraId;
	String licActive;
	private String invCode="";
	private String invName="";
	private String debitName="";
	private String debitCode="";
	private ArrayList licList = null;
	private ArrayList debList = null;
	private String hDebitCode="";
	private String hDebitName="";
	private String hLicName="";
	private String hPolicyNo="";
	private String hStartDate="";
	private String hEndDate="";
	private String hTaxEx="";
	private String hDebitExempt="";
	private String hInsAMt="";
	private String hPremAMt="";
	private String hDebitExemptCode="";
	private String flag="false";
	private String updatePlicyFlag="false"; 
	
	
	public String getUpdatePlicyFlag() {
		return updatePlicyFlag;
	}
	public void setUpdatePlicyFlag(String updatePlicyFlag) {
		this.updatePlicyFlag = updatePlicyFlag;
	}
	
	public String getHDebitExemptCode() {
		return hDebitExemptCode;
	}
	public void setHDebitExemptCode(String debitExemptCode) {
		hDebitExemptCode = debitExemptCode;
	}
	public String getHDebitCode() {
		return hDebitCode;
	}
	public void setHDebitCode(String debitCode) {
		hDebitCode = debitCode;
	}
	public String getHDebitName() {
		return hDebitName;
	}
	public void setHDebitName(String debitName) {
		hDebitName = debitName;
	}
	public String getHLicName() {
		return hLicName;
	}
	public void setHLicName(String licName) {
		hLicName = licName;
	}
	public String getHPolicyNo() {
		return hPolicyNo;
	}
	public void setHPolicyNo(String policyNo) {
		hPolicyNo = policyNo;
	}
	public String getHStartDate() {
		return hStartDate;
	}
	public void setHStartDate(String startDate) {
		hStartDate = startDate;
	}
	public String getHEndDate() {
		return hEndDate;
	}
	public void setHEndDate(String endDate) {
		hEndDate = endDate;
	}
	public String getHTaxEx() {
		return hTaxEx;
	}
	public void setHTaxEx(String taxEx) {
		hTaxEx = taxEx;
	}
	public String getHDebitExempt() {
		return hDebitExempt;
	}
	public void setHDebitExempt(String debitExempt) {
		hDebitExempt = debitExempt;
	}
	public String getHInsAMt() {
		return hInsAMt;
	}
	public void setHInsAMt(String insAMt) {
		hInsAMt = insAMt;
	}
	public String getHPremAMt() {
		return hPremAMt;
	}
	public void setHPremAMt(String premAMt) {
		hPremAMt = premAMt;
	}
	public String getEmpCent() {
		return empCent;
	}
	public void setEmpCent(String empCent) {
		this.empCent = empCent;
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
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(String insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}
	public String getLicEndDt() {
		return licEndDt;
	}
	public void setLicEndDt(String licEndDt) {
		this.licEndDt = licEndDt;
	}
	public String getLicId() {
		return licId;
	}
	public void setLicId(String licId) {
		this.licId = licId;
	}
	public ArrayList getLicList() {
		return licList;
	}
	public void setLicList(ArrayList licList) {
		this.licList = licList;
	}
	public String getLicName() {
		return licName;
	}
	public void setLicName(String licName) {
		this.licName = licName;
	}
	public String getLicPolicyNo() {
		return licPolicyNo;
	}
	public void setLicPolicyNo(String licPolicyNo) {
		this.licPolicyNo = licPolicyNo;
	}
	public String getLicPremiumAmt() {
		return licPremiumAmt;
	}
	public void setLicPremiumAmt(String licPremiumAmt) {
		this.licPremiumAmt = licPremiumAmt;
	}
	public String getLicStDate() {
		return licStDate;
	}
	public void setLicStDate(String licStDate) {
		this.licStDate = licStDate;
	}
	public String getPaidInSal() {
		return paidInSal;
	}
	public void setPaidInSal(String paidInSal) {
		this.paidInSal = paidInSal;
	}
	public String getTaxExempt() {
		return taxExempt;
	}
	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getLicActive() {
		return licActive;
	}
	public void setLicActive(String licActive) {
		this.licActive = licActive;
	}
	public ArrayList getDebList() {
		return debList;
	}
	public void setDebList(ArrayList debList) {
		this.debList = debList;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLicIdHead() {
		return licIdHead;
	}
	public void setLicIdHead(String licIdHead) {
		this.licIdHead = licIdHead;
	}

	}

