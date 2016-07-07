/**
 * 
 */
package org.paradyne.bean.promotion;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 *
 */
public class PromotionMisReport extends BeanBase {

	
	private String empCode="";
	private String empToken="";
	private String empName="";
	private String repToName="";
	private String repToCode="";
	private String proBranch="";
	private String prBranCode="";
	private String proDept="";
	private String prDeptCode="";
	private String proDesg="";
	private String prDesgCode="";
	private String prGrdCode="";
	private String proGrade="";
	private String proDiv="";
	private String prDivCode="";

	private String prRepToNm="";
	private String prRepCode="";
	private String proByNm="";
	private String proByCode="";
	private String reportType;
	private String frmDate;
	private String toDate;
	
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	
	public String getRepToName() {
		return repToName;
	}
	public void setRepToName(String repToName) {
		this.repToName = repToName;
	}
	public String getRepToCode() {
		return repToCode;
	}
	public void setRepToCode(String repToCode) {
		this.repToCode = repToCode;
	}
	
	public String getProBranch() {
		return proBranch;
	}
	public void setProBranch(String proBranch) {
		this.proBranch = proBranch;
	}
	public String getPrBranCode() {
		return prBranCode;
	}
	public void setPrBranCode(String prBranCode) {
		this.prBranCode = prBranCode;
	}
	public String getProDept() {
		return proDept;
	}
	public void setProDept(String proDept) {
		this.proDept = proDept;
	}
	public String getPrDeptCode() {
		return prDeptCode;
	}
	public void setPrDeptCode(String prDeptCode) {
		this.prDeptCode = prDeptCode;
	}
	public String getProDesg() {
		return proDesg;
	}
	public void setProDesg(String proDesg) {
		this.proDesg = proDesg;
	}
	public String getPrDesgCode() {
		return prDesgCode;
	}
	public void setPrDesgCode(String prDesgCode) {
		this.prDesgCode = prDesgCode;
	}
	public String getPrGrdCode() {
		return prGrdCode;
	}
	public void setPrGrdCode(String prGrdCode) {
		this.prGrdCode = prGrdCode;
	}
	public String getProGrade() {
		return proGrade;
	}
	public void setProGrade(String proGrade) {
		this.proGrade = proGrade;
	}
	public String getProDiv() {
		return proDiv;
	}
	public void setProDiv(String proDiv) {
		this.proDiv = proDiv;
	}
	public String getPrDivCode() {
		return prDivCode;
	}
	public void setPrDivCode(String prDivCode) {
		this.prDivCode = prDivCode;
	}
	public String getPrRepToNm() {
		return prRepToNm;
	}
	public void setPrRepToNm(String prRepToNm) {
		this.prRepToNm = prRepToNm;
	}
	public String getPrRepCode() {
		return prRepCode;
	}
	public void setPrRepCode(String prRepCode) {
		this.prRepCode = prRepCode;
	}
	public String getProByNm() {
		return proByNm;
	}
	public void setProByNm(String proByNm) {
		this.proByNm = proByNm;
	}
	public String getProByCode() {
		return proByCode;
	}
	public void setProByCode(String proByCode) {
		this.proByCode = proByCode;
	}
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
