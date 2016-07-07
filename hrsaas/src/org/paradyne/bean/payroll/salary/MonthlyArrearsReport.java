/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

/**
 * @author balajim and Pradeep 
 *Date :31-07-2008
 */
public class MonthlyArrearsReport extends BeanBase {
	String promotionReportCode="";
	String month ="";
	String year ="";
	String  divCode="";
	String  divName="";
	String  branchCode="";
	String  branchName="";
	String deptCode ="";
	String deptName ="";
	String arrearType ="";
	String report="";
	String onHold="";
	String checkBrnDept="N";
	String checkBrn="N";
	String checkDept="N";
	private String checkFlag="N";
	private String reportType ="";
	private String reportAction ="";
	private String selectarrearType = "M";
	private String reportdivision = "";
	private String reportbranch = "";
	private String reportdept = "";
	private String reportmicrcode = "";
	private String reportgrade = "";
	private String reportaccno = "";
	private String reportbank = "";
	private String reportempType = "";
	private String paybillname="";
	private String paybillid="";
	private String gradeId="";
	private String gradeName="";
	private String costcentername = "";
	private String costcenterid = "";
	
	
	public String getPaybillname() {
		return paybillname;
	}
	public void setPaybillname(String paybillname) {
		this.paybillname = paybillname;
	}
	public String getPaybillid() {
		return paybillid;
	}
	public void setPaybillid(String paybillid) {
		this.paybillid = paybillid;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getCostcentername() {
		return costcentername;
	}
	public void setCostcentername(String costcentername) {
		this.costcentername = costcentername;
	}
	public String getCostcenterid() {
		return costcenterid;
	}
	public void setCostcenterid(String costcenterid) {
		this.costcenterid = costcenterid;
	}
	public String getReportbank() {
		return reportbank;
	}
	public void setReportbank(String reportbank) {
		this.reportbank = reportbank;
	}
	public String getSelectarrearType() {
		return selectarrearType;
	}
	public void setSelectarrearType(String selectarrearType) {
		this.selectarrearType = selectarrearType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getArrearType() {
		return arrearType;
	}
	public void setArrearType(String arrearType) {
		this.arrearType = arrearType;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}

	public String getCheckBrnDept() {
		return checkBrnDept;
	}
	public void setCheckBrnDept(String checkBrnDept) {
		this.checkBrnDept = checkBrnDept;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getCheckBrn() {
		return checkBrn;
	}
	public void setCheckBrn(String checkBrn) {
		this.checkBrn = checkBrn;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	public String getReportdivision() {
		return reportdivision;
	}
	public void setReportdivision(String reportdivision) {
		this.reportdivision = reportdivision;
	}
	public String getReportbranch() {
		return reportbranch;
	}
	public void setReportbranch(String reportbranch) {
		this.reportbranch = reportbranch;
	}
	public String getReportdept() {
		return reportdept;
	}
	public void setReportdept(String reportdept) {
		this.reportdept = reportdept;
	}
	public String getReportmicrcode() {
		return reportmicrcode;
	}
	public void setReportmicrcode(String reportmicrcode) {
		this.reportmicrcode = reportmicrcode;
	}
	public String getReportgrade() {
		return reportgrade;
	}
	public void setReportgrade(String reportgrade) {
		this.reportgrade = reportgrade;
	}
	public String getReportaccno() {
		return reportaccno;
	}
	public void setReportaccno(String reportaccno) {
		this.reportaccno = reportaccno;
	}
	/**
	 * @return the reportempType
	 */
	public String getReportempType() {
		return reportempType;
	}
	/**
	 * @param reportempType the reportempType to set
	 */
	public void setReportempType(String reportempType) {
		this.reportempType = reportempType;
	}
	/**
	 * @return the promotionReportCode
	 */
	public String getPromotionReportCode() {
		return promotionReportCode;
	}
	/**
	 * @param promotionReportCode the promotionReportCode to set
	 */
	public void setPromotionReportCode(String promotionReportCode) {
		this.promotionReportCode = promotionReportCode;
	}
	 
	
}
