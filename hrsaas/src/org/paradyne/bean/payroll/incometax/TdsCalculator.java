/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class TdsCalculator extends BeanBase {
	
	String empToken;
	String empName;
	String empDepart;
	String empCenter;
	String empRank;
	String empId;
	String empGender;
	String grossSal;
	String fromYear;
	String toYear;
	
	
	private String noExemptData="false";
	private  ArrayList<Object> empExemptInvList=null;
	String invExemptId;
	String invExemptName;
	String invExemptLimit;
	String invExemptAmt;
	String totalExemptInvAmt;
	
	
	private String noOtherData="false";
	private  ArrayList<Object> empOtherInvList=null;
	String invOtherId;
	String invOtherName;
	String invOtherLimit;
	String invOtherAmt;
	String totalOtherInvAmt;
	
	private String noParaData="false";
	private  ArrayList<Object> empParaInvList=null;
	String invParaId;
	String invParaName;
	String invParaLimit;
	String invParaAmt;
	String totalParaInvAmt;
	
	private String noVIAData="false";
	private  ArrayList<Object> empVIAInvList=null;
	String invVIAId;
	String invVIAName;
	String invVIALimit;
	String invVIAAmt;
	String totalVIAInvAmt;
	
	private String noRebateData="false";
	private  ArrayList<Object> empRebateInvList=null;
	String invRebateId;
	String invRebateName;
	String invRebateLimit;
	String invRebateAmt;
	String totalRebateInvAmt;
	
	private String noGrossData="false";
	private  ArrayList<Object> empGrossSalList=null;
	String creditId;
	String creditName;
	String creditAmt;
	String totalGrossAmt;
	String processedAmt;
	
	String totalNetInvAmt;
	String netTaxableIncome;
	
	private String noSlabData="false";
	private  ArrayList<Object> tdslabList=null;
	String slabFrmAmt;
	String slabToAmt;
	String slabTaxRate;
	String slabAmt;
	String slabTax;
	String taxOnIncome;
	String eduCess;
	String surCharge;
	String netTax;
	String taxPerMonth;
	private String isSaveButton = "false";
	
	public String getNetTax() {
		return netTax;
	}
	public void setNetTax(String netTax) {
		this.netTax = netTax;
	}
	public String getSurCharge() {
		return surCharge;
	}
	public void setSurCharge(String surCharge) {
		this.surCharge = surCharge;
	}
	public String getEduCess() {
		return eduCess;
	}
	public void setEduCess(String eduCess) {
		this.eduCess = eduCess;
	}
	public String getTaxOnIncome() {
		return taxOnIncome;
	}
	public void setTaxOnIncome(String taxOnIncome) {
		this.taxOnIncome = taxOnIncome;
	}
	public String getNetTaxableIncome() {
		return netTaxableIncome;
	}
	public void setNetTaxableIncome(String netTaxableIncome) {
		this.netTaxableIncome = netTaxableIncome;
	}
	public String getTotalNetInvAmt() {
		return totalNetInvAmt;
	}
	public void setTotalNetInvAmt(String totalNetInvAmt) {
		this.totalNetInvAmt = totalNetInvAmt;
	}
	public String getProcessedAmt() {
		return processedAmt;
	}
	public void setProcessedAmt(String processedAmt) {
		this.processedAmt = processedAmt;
	}
	public String getTotalGrossAmt() {
		return totalGrossAmt;
	}
	public void setTotalGrossAmt(String totalGrossAmt) {
		this.totalGrossAmt = totalGrossAmt;
	}
	public String getNoGrossData() {
		return noGrossData;
	}
	public void setNoGrossData(String noGrossData) {
		this.noGrossData = noGrossData;
	}
	public ArrayList<Object> getEmpGrossSalList() {
		return empGrossSalList;
	}
	public void setEmpGrossSalList(ArrayList<Object> empGrossSalList) {
		this.empGrossSalList = empGrossSalList;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
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
	public String getEmpDepart() {
		return empDepart;
	}
	public void setEmpDepart(String empDepart) {
		this.empDepart = empDepart;
	}
	public String getEmpCenter() {
		return empCenter;
	}
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getGrossSal() {
		return grossSal;
	}
	public void setGrossSal(String grossSal) {
		this.grossSal = grossSal;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getNoExemptData() {
		return noExemptData;
	}
	public void setNoExemptData(String noExemptData) {
		this.noExemptData = noExemptData;
	}
	public ArrayList<Object> getEmpExemptInvList() {
		return empExemptInvList;
	}
	public void setEmpExemptInvList(ArrayList<Object> empExemptInvList) {
		this.empExemptInvList = empExemptInvList;
	}
	public String getInvExemptId() {
		return invExemptId;
	}
	public void setInvExemptId(String invExemptId) {
		this.invExemptId = invExemptId;
	}
	public String getInvExemptName() {
		return invExemptName;
	}
	public void setInvExemptName(String invExemptName) {
		this.invExemptName = invExemptName;
	}
	public String getInvExemptLimit() {
		return invExemptLimit;
	}
	public void setInvExemptLimit(String invExemptLimit) {
		this.invExemptLimit = invExemptLimit;
	}
	public String getInvExemptAmt() {
		return invExemptAmt;
	}
	public void setInvExemptAmt(String invExemptAmt) {
		this.invExemptAmt = invExemptAmt;
	}
	public String getTotalExemptInvAmt() {
		return totalExemptInvAmt;
	}
	public void setTotalExemptInvAmt(String totalExemptInvAmt) {
		this.totalExemptInvAmt = totalExemptInvAmt;
	}
	public String getNoOtherData() {
		return noOtherData;
	}
	public void setNoOtherData(String noOtherData) {
		this.noOtherData = noOtherData;
	}
	public ArrayList<Object> getEmpOtherInvList() {
		return empOtherInvList;
	}
	public void setEmpOtherInvList(ArrayList<Object> empOtherInvList) {
		this.empOtherInvList = empOtherInvList;
	}
	public String getInvOtherId() {
		return invOtherId;
	}
	public void setInvOtherId(String invOtherId) {
		this.invOtherId = invOtherId;
	}
	public String getInvOtherName() {
		return invOtherName;
	}
	public void setInvOtherName(String invOtherName) {
		this.invOtherName = invOtherName;
	}
	public String getInvOtherLimit() {
		return invOtherLimit;
	}
	public void setInvOtherLimit(String invOtherLimit) {
		this.invOtherLimit = invOtherLimit;
	}
	public String getInvOtherAmt() {
		return invOtherAmt;
	}
	public void setInvOtherAmt(String invOtherAmt) {
		this.invOtherAmt = invOtherAmt;
	}
	public String getTotalOtherInvAmt() {
		return totalOtherInvAmt;
	}
	public void setTotalOtherInvAmt(String totalOtherInvAmt) {
		this.totalOtherInvAmt = totalOtherInvAmt;
	}
	public String getNoParaData() {
		return noParaData;
	}
	public void setNoParaData(String noParaData) {
		this.noParaData = noParaData;
	}
	public ArrayList<Object> getEmpParaInvList() {
		return empParaInvList;
	}
	public void setEmpParaInvList(ArrayList<Object> empParaInvList) {
		this.empParaInvList = empParaInvList;
	}
	public String getInvParaId() {
		return invParaId;
	}
	public void setInvParaId(String invParaId) {
		this.invParaId = invParaId;
	}
	public String getInvParaName() {
		return invParaName;
	}
	public void setInvParaName(String invParaName) {
		this.invParaName = invParaName;
	}
	public String getInvParaLimit() {
		return invParaLimit;
	}
	public void setInvParaLimit(String invParaLimit) {
		this.invParaLimit = invParaLimit;
	}
	public String getInvParaAmt() {
		return invParaAmt;
	}
	public void setInvParaAmt(String invParaAmt) {
		this.invParaAmt = invParaAmt;
	}
	public String getTotalParaInvAmt() {
		return totalParaInvAmt;
	}
	public void setTotalParaInvAmt(String totalParaInvAmt) {
		this.totalParaInvAmt = totalParaInvAmt;
	}
	public String getNoVIAData() {
		return noVIAData;
	}
	public void setNoVIAData(String noVIAData) {
		this.noVIAData = noVIAData;
	}
	public ArrayList<Object> getEmpVIAInvList() {
		return empVIAInvList;
	}
	public void setEmpVIAInvList(ArrayList<Object> empVIAInvList) {
		this.empVIAInvList = empVIAInvList;
	}
	public String getInvVIAId() {
		return invVIAId;
	}
	public void setInvVIAId(String invVIAId) {
		this.invVIAId = invVIAId;
	}
	public String getInvVIAName() {
		return invVIAName;
	}
	public void setInvVIAName(String invVIAName) {
		this.invVIAName = invVIAName;
	}
	public String getInvVIALimit() {
		return invVIALimit;
	}
	public void setInvVIALimit(String invVIALimit) {
		this.invVIALimit = invVIALimit;
	}
	public String getInvVIAAmt() {
		return invVIAAmt;
	}
	public void setInvVIAAmt(String invVIAAmt) {
		this.invVIAAmt = invVIAAmt;
	}
	public String getTotalVIAInvAmt() {
		return totalVIAInvAmt;
	}
	public void setTotalVIAInvAmt(String totalVIAInvAmt) {
		this.totalVIAInvAmt = totalVIAInvAmt;
	}
	public String getNoRebateData() {
		return noRebateData;
	}
	public void setNoRebateData(String noRebateData) {
		this.noRebateData = noRebateData;
	}
	public ArrayList<Object> getEmpRebateInvList() {
		return empRebateInvList;
	}
	public void setEmpRebateInvList(ArrayList<Object> empRebateInvList) {
		this.empRebateInvList = empRebateInvList;
	}
	public String getInvRebateId() {
		return invRebateId;
	}
	public void setInvRebateId(String invRebateId) {
		this.invRebateId = invRebateId;
	}
	public String getInvRebateName() {
		return invRebateName;
	}
	public void setInvRebateName(String invRebateName) {
		this.invRebateName = invRebateName;
	}
	public String getInvRebateLimit() {
		return invRebateLimit;
	}
	public void setInvRebateLimit(String invRebateLimit) {
		this.invRebateLimit = invRebateLimit;
	}
	public String getInvRebateAmt() {
		return invRebateAmt;
	}
	public void setInvRebateAmt(String invRebateAmt) {
		this.invRebateAmt = invRebateAmt;
	}
	public String getTotalRebateInvAmt() {
		return totalRebateInvAmt;
	}
	public void setTotalRebateInvAmt(String totalRebateInvAmt) {
		this.totalRebateInvAmt = totalRebateInvAmt;
	}
	public String getNoSlabData() {
		return noSlabData;
	}
	public void setNoSlabData(String noSlabData) {
		this.noSlabData = noSlabData;
	}
	public ArrayList<Object> getTdslabList() {
		return tdslabList;
	}
	public void setTdslabList(ArrayList<Object> tdslabList) {
		this.tdslabList = tdslabList;
	}
	public String getSlabFrmAmt() {
		return slabFrmAmt;
	}
	public void setSlabFrmAmt(String slabFrmAmt) {
		this.slabFrmAmt = slabFrmAmt;
	}
	public String getSlabToAmt() {
		return slabToAmt;
	}
	public void setSlabToAmt(String slabToAmt) {
		this.slabToAmt = slabToAmt;
	}
	public String getSlabTaxRate() {
		return slabTaxRate;
	}
	public void setSlabTaxRate(String slabTaxRate) {
		this.slabTaxRate = slabTaxRate;
	}
	public String getSlabAmt() {
		return slabAmt;
	}
	public void setSlabAmt(String slabAmt) {
		this.slabAmt = slabAmt;
	}
	public String getSlabTax() {
		return slabTax;
	}
	public void setSlabTax(String slabTax) {
		this.slabTax = slabTax;
	}
	public String getTaxPerMonth() {
		return taxPerMonth;
	}
	public void setTaxPerMonth(String taxPerMonth) {
		this.taxPerMonth = taxPerMonth;
	}
	public String getIsSaveButton() {
		return isSaveButton;
	}
	public void setIsSaveButton(String isSaveButton) {
		this.isSaveButton = isSaveButton;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	
	
	
	
}