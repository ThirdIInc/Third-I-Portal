package org.paradyne.bean.gov;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class SalarySlipMisReportGov extends BeanBase {

	private String EmpCode = "";
	private String EmpName = "";
	private String EmpToken = "";
	private String salYear = "";
	private String salMonth = "";

	private String salBranchName= "";
	private String salBranchId= "";
	private String salDivisionName= "";
	private String salDivisionId= "";

	private String salDeptName= "";
	private String salDeptId= "";
	private String salEmpTypeName= "";
	private String salEmpTypeId= "";
	private String arrearFlag = "N";
	
	private String empRankId = "";
	private String empRank = "";
	private String paybillId = "";
	private String paybillName = "";
	private String reportingToId = "";
	private String reportingToToken = "";
	private String reportingToName = "";
	

	private String rangeCode = "";    //  this  field will give List URL sequence No Starting 0 to n.
	
	ArrayList urlList = new ArrayList();  // it will set  all the Generated URL'S.
	
	private boolean recordFlag = false;   // if the recordFlag True then only URL's will set.
	
	private String recrdsPerPage = "";   // This field will be user full how many pages u want in a single URL.

	//ADDED BY REEBA
	private String salarySlipFor = "S";
	
	/**
	 * @return the urlList
	 */
	public ArrayList getUrlList() {
		return urlList;
	}

	/**
	 * @param urlList
	 *            the urlList to set
	 */
	public void setUrlList(ArrayList urlList) {
		this.urlList = urlList;
	}

	/**
	 * @return the rangeCode
	 */
	public String getRangeCode() {
		return rangeCode;
	}

	/**
	 * @param rangeCode
	 *            the rangeCode to set
	 */
	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return EmpCode;
	}

	/**
	 * @param empCode
	 *            the empCode to set
	 */
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return EmpName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		EmpName = empName;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return EmpToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		EmpToken = empToken;
	}

	/**
	 * @return the salYear
	 */
	public String getSalYear() {
		return salYear;
	}

	/**
	 * @param salYear
	 *            the salYear to set
	 */
	public void setSalYear(String salYear) {
		this.salYear = salYear;
	}

	/**
	 * @return the salMonth
	 */
	public String getSalMonth() {
		return salMonth;
	}

	/**
	 * @param salMonth
	 *            the salMonth to set
	 */
	public void setSalMonth(String salMonth) {
		this.salMonth = salMonth;
	}

	/**
	 * @return the salBranchName
	 */
	public String getSalBranchName() {
		return salBranchName;
	}

	/**
	 * @param salBranchName
	 *            the salBranchName to set
	 */
	public void setSalBranchName(String salBranchName) {
		this.salBranchName = salBranchName;
	}

	/**
	 * @return the salBranchId
	 */
	public String getSalBranchId() {
		return salBranchId;
	}

	/**
	 * @param salBranchId
	 *            the salBranchId to set
	 */
	public void setSalBranchId(String salBranchId) {
		this.salBranchId = salBranchId;
	}

	/**
	 * @return the salDivisionName
	 */
	public String getSalDivisionName() {
		return salDivisionName;
	}

	/**
	 * @param salDivisionName
	 *            the salDivisionName to set
	 */
	public void setSalDivisionName(String salDivisionName) {
		this.salDivisionName = salDivisionName;
	}

	/**
	 * @return the salDivisionId
	 */
	public String getSalDivisionId() {
		return salDivisionId;
	}

	/**
	 * @param salDivisionId
	 *            the salDivisionId to set
	 */
	public void setSalDivisionId(String salDivisionId) {
		this.salDivisionId = salDivisionId;
	}

	/**
	 * @return the salDeptName
	 */
	public String getSalDeptName() {
		return salDeptName;
	}

	/**
	 * @param salDeptName
	 *            the salDeptName to set
	 */
	public void setSalDeptName(String salDeptName) {
		this.salDeptName = salDeptName;
	}

	/**
	 * @return the salDeptId
	 */
	public String getSalDeptId() {
		return salDeptId;
	}

	/**
	 * @param salDeptId
	 *            the salDeptId to set
	 */
	public void setSalDeptId(String salDeptId) {
		this.salDeptId = salDeptId;
	}

	/**
	 * @return the salEmpTypeName
	 */
	public String getSalEmpTypeName() {
		return salEmpTypeName;
	}

	/**
	 * @param salEmpTypeName
	 *            the salEmpTypeName to set
	 */
	public void setSalEmpTypeName(String salEmpTypeName) {
		this.salEmpTypeName = salEmpTypeName;
	}

	/**
	 * @return the salEmpTypeId
	 */
	public String getSalEmpTypeId() {
		return salEmpTypeId;
	}

	/**
	 * @param salEmpTypeId
	 *            the salEmpTypeId to set
	 */
	public void setSalEmpTypeId(String salEmpTypeId) {
		this.salEmpTypeId = salEmpTypeId;
	}

	/**
	 * @return the arrearFlag
	 */
	public String getArrearFlag() {
		return arrearFlag;
	}

	/**
	 * @param arrearFlag
	 *            the arrearFlag to set
	 */
	public void setArrearFlag(String arrearFlag) {
		this.arrearFlag = arrearFlag;
	}

	/**
	 * @return the recordFlag
	 */
	public boolean isRecordFlag() {
		return recordFlag;
	}

	/**
	 * @param recordFlag
	 *            the recordFlag to set
	 */
	public void setRecordFlag(boolean recordFlag) {
		this.recordFlag = recordFlag;
	}

	/**
	 * @return the recrdsPerPage
	 */
	public String getRecrdsPerPage() {
		return recrdsPerPage;
	}

	/**
	 * @param recrdsPerPage
	 *            the recrdsPerPage to set
	 */
	public void setRecrdsPerPage(String recrdsPerPage) {
		this.recrdsPerPage = recrdsPerPage;
	}

	public String getSalarySlipFor() {
		return salarySlipFor;
	}

	public void setSalarySlipFor(String salarySlipFor) {
		this.salarySlipFor = salarySlipFor;
	}

	public String getEmpRankId() {
		return empRankId;
	}

	public void setEmpRankId(String empRankId) {
		this.empRankId = empRankId;
	}

	public String getEmpRank() {
		return empRank;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
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

	public String getReportingToId() {
		return reportingToId;
	}

	public void setReportingToId(String reportingToId) {
		this.reportingToId = reportingToId;
	}

	public String getReportingToName() {
		return reportingToName;
	}

	public void setReportingToName(String reportingToName) {
		this.reportingToName = reportingToName;
	}

	public String getReportingToToken() {
		return reportingToToken;
	}

	public void setReportingToToken(String reportingToToken) {
		this.reportingToToken = reportingToToken;
	}

}
