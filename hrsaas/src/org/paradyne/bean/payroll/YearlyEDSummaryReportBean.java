/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * Bean for MonthlyLeaveRegistry application.
 * Date : 13 Dec'2011
 *
 */
public class YearlyEDSummaryReportBean extends BeanBase {
	
	private String fromYear = "";
	private String toYear = "";
	private String payBillNo="";
	
	private String empId="";
	private String empName="";
	private String empToken="";
	private String payName="";
	
	private String typeCode="";
	private String typeName="";
	private String deptCode="";
	private String deptName="";
	private String branchCode=""; 
	private String branchName="";
	
	private String report="";
	
	
	private String divCode="";
	private String divName="";
	
	
	private String paybillId = "";
	private String paybillName = "";
	private String cadreCode = "";
	private String cadreName = "";
	
	private String checkEarningHead = "";
	private String checkDeductionHead = "";
	private String hiddenChechfrmId = "";
	private String checkConsolidatedArrears = "";
	
	private String creditCode = "";
	private String creditName = "";
	private String debitCode = "";
	private String debitName = "";
	
	private String costCenterId = "";
	private String costCenterName = "";
	private String subCostCenterId = "";
	private String subCostCenterName = "";
	
	public String getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getSubCostCenterId() {
		return subCostCenterId;
	}
	public void setSubCostCenterId(String subCostCenterId) {
		this.subCostCenterId = subCostCenterId;
	}
	public String getSubCostCenterName() {
		return subCostCenterName;
	}
	public void setSubCostCenterName(String subCostCenterName) {
		this.subCostCenterName = subCostCenterName;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCheckEarningHead() {
		return checkEarningHead;
	}
	public void setCheckEarningHead(String checkEarningHead) {
		this.checkEarningHead = checkEarningHead;
	}
	public String getCheckDeductionHead() {
		return checkDeductionHead;
	}
	public void setCheckDeductionHead(String checkDeductionHead) {
		this.checkDeductionHead = checkDeductionHead;
	}
	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}
	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}
	public String getCheckConsolidatedArrears() {
		return checkConsolidatedArrears;
	}
	public void setCheckConsolidatedArrears(String checkConsolidatedArrears) {
		this.checkConsolidatedArrears = checkConsolidatedArrears;
	}
	
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
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
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getCadreCode() {
		return cadreCode;
	}
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
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
	
	
	
	
}
