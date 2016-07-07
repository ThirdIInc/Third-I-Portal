/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0517
 *
 */
public class Form16MisReport extends BeanBase {
	
	private int toYear = 0;
	private int frmYear = 0;
	
	private String source ="";
	
	private String repType;
	private String divisionId = "";
	private String divisionName = "";
	private String branchId = "";
	private String branchName = "";
	private String deptId = "";
	private String deptName = "";
	private String empTypeId = "";
	private String empTypeName = "";
	private String empId = "";
	private String empToken = "";
	private String empName = "";
	
	private String rangeCode = "";    //  this  field will give List URL sequence No Starting 0 to n.
	
	private boolean recordFlag = false;   // if the recordFlag True then only URL's will set.
	
	private String signAuthtoken="";
	private String signAuthName="";
	private String signAuthEmpId="";
	private String signAuthEmpDesg="";
	private String signAuthEmpFather="";
	private String signDate = "";
	
	private HashMap empOfficialDataMap = null;
	private HashMap settlementDataMap = null;
	private HashMap taxSalaryDataMap = null;
	private HashMap perquisiteDataMap = null;
	private HashMap otherIncomeDataMap = null;
	private HashMap exemptInvestmentDataMap = null;
	private HashMap invstmentHraDataMap = null;
	private HashMap investmentDeducDataMap = null;
	private HashMap otherSecDataMap = null;
	private HashMap challanDataAprToDecMap = null;
	private HashMap challanDataJanToMarMap = null;
	private HashMap empCertMap = null;
	private HashMap salDesignationMap = null;
	
	private String form12Flag = "";

	
	private int recordsPerPage = 0;
	
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
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
	public int getToYear() {
		return toYear;
	}
	public void setToYear(int toYear) {
		this.toYear = toYear;
	}
	public int getFrmYear() {
		return frmYear;
	}
	public void setFrmYear(int frmYear) {
		this.frmYear = frmYear;
	}
	public boolean isRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(boolean recordFlag) {
		this.recordFlag = recordFlag;
	}
	public String getRangeCode() {
		return rangeCode;
	}
	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}
	public HashMap getEmpOfficialDataMap() {
		return empOfficialDataMap;
	}
	public void setEmpOfficialDataMap(HashMap empOfficialDataMap) {
		this.empOfficialDataMap = empOfficialDataMap;
	}
	public HashMap getSettlementDataMap() {
		return settlementDataMap;
	}
	public void setSettlementDataMap(HashMap settlementDataMap) {
		this.settlementDataMap = settlementDataMap;
	}
	public HashMap getTaxSalaryDataMap() {
		return taxSalaryDataMap;
	}
	public void setTaxSalaryDataMap(HashMap taxSalaryDataMap) {
		this.taxSalaryDataMap = taxSalaryDataMap;
	}
	public HashMap getPerquisiteDataMap() {
		return perquisiteDataMap;
	}
	public void setPerquisiteDataMap(HashMap perquisiteDataMap) {
		this.perquisiteDataMap = perquisiteDataMap;
	}
	public HashMap getOtherIncomeDataMap() {
		return otherIncomeDataMap;
	}
	public void setOtherIncomeDataMap(HashMap otherIncomeDataMap) {
		this.otherIncomeDataMap = otherIncomeDataMap;
	}
	public HashMap getExemptInvestmentDataMap() {
		return exemptInvestmentDataMap;
	}
	public void setExemptInvestmentDataMap(HashMap exemptInvestmentDataMap) {
		this.exemptInvestmentDataMap = exemptInvestmentDataMap;
	}
	public HashMap getInvstmentHraDataMap() {
		return invstmentHraDataMap;
	}
	public void setInvstmentHraDataMap(HashMap invstmentHraDataMap) {
		this.invstmentHraDataMap = invstmentHraDataMap;
	}
	public HashMap getInvestmentDeducDataMap() {
		return investmentDeducDataMap;
	}
	public void setInvestmentDeducDataMap(HashMap investmentDeducDataMap) {
		this.investmentDeducDataMap = investmentDeducDataMap;
	}
	public HashMap getOtherSecDataMap() {
		return otherSecDataMap;
	}
	public void setOtherSecDataMap(HashMap otherSecDataMap) {
		this.otherSecDataMap = otherSecDataMap;
	}
	public HashMap getChallanDataAprToDecMap() {
		return challanDataAprToDecMap;
	}
	public void setChallanDataAprToDecMap(HashMap challanDataAprToDecMap) {
		this.challanDataAprToDecMap = challanDataAprToDecMap;
	}
	public HashMap getChallanDataJanToMarMap() {
		return challanDataJanToMarMap;
	}
	public void setChallanDataJanToMarMap(HashMap challanDataJanToMarMap) {
		this.challanDataJanToMarMap = challanDataJanToMarMap;
	}
	public String getSignAuthtoken() {
		return signAuthtoken;
	}
	public void setSignAuthtoken(String signAuthtoken) {
		this.signAuthtoken = signAuthtoken;
	}
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getSignAuthEmpId() {
		return signAuthEmpId;
	}
	public void setSignAuthEmpId(String signAuthEmpId) {
		this.signAuthEmpId = signAuthEmpId;
	}
	public String getSignAuthEmpDesg() {
		return signAuthEmpDesg;
	}
	public void setSignAuthEmpDesg(String signAuthEmpDesg) {
		this.signAuthEmpDesg = signAuthEmpDesg;
	}
	public String getSignAuthEmpFather() {
		return signAuthEmpFather;
	}
	public void setSignAuthEmpFather(String signAuthEmpFather) {
		this.signAuthEmpFather = signAuthEmpFather;
	}
	public HashMap getEmpCertMap() {
		return empCertMap;
	}
	public void setEmpCertMap(HashMap empCertMap) {
		this.empCertMap = empCertMap;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public HashMap getSalDesignationMap() {
		return salDesignationMap;
	}
	public void setSalDesignationMap(HashMap salDesignationMap) {
		this.salDesignationMap = salDesignationMap;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the form12Flag
	 */
	public String getForm12Flag() {
		return form12Flag;
	}
	/**
	 * @param form12Flag the form12Flag to set
	 */
	public void setForm12Flag(String form12Flag) {
		this.form12Flag = form12Flag;
	}
}
