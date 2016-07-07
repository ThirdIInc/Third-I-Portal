package org.paradyne.bean.Loan;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class NewLoansIssuedReport extends BeanBase {

	//ADDED BY REEBA
	private String refundable ="";
	
	
	private String divCode ="";
	private String divName ="";
	private String deptCode ="";
	private String deptName ="";
	private String branchCode ="";
	private String branchName ="";
	private String designationCode ="";
	private String designationName ="";
	private String empCode =""; 
	private String empName ="";
	private String empToken ="";
	
	private String empNameFlag ="";
	private String divFlag ="";
	private String loanAmountFlag ="";
	private String loanTypeFlag ="";
	private String loanPaymentDateFlag ="";
	private String loanInstallmentFlag ="";
	private String noOfInstallFlag ="";
	private String loanDateSelect ="";
	private String loanFromDate ="";
	private String loanToDate ="";
	
	private String reportTypeStr= "";
	
	private String savedReport = "";
	private String reportTitle = "";
	private String hidReportRadio = "";
	private String reportView = "";
	private String reportType = "";
	private String sortBy = "";
	private String hiddenSortBy = "";
	private String sortByOrder = "";
	private String thenBy1 = "";
	private String hiddenThenBy1 = "";
	private String thenByOrder1 = "";
	private String thenByOrder2 = "";
	private String thenBy2 = "";
	private String hiddenThenBy2 = "";
	private String sortByAsc = "checked";
	private String sortByDsc = "";
	private String thenByOrder1Asc = "checked";
	private String thenByOrder1Dsc = "";
	private String thenByOrder2Asc = "checked";
	private String thenByOrder2Dsc = "";
	private String reqStatus = "";
	private String settingName = "";
	private String hiddenColumns = "";

	private String reportId = "";
	private String hidReportView="checked";
	private String backFlag = "";
	private String exportAll = "";

 
	private String myPage="";
	private String show="";
	private String dataLength="";
	private String noData="false";
	 
	
	private String loanType ="";
	
	private HashMap lstLoanType = null;  
	
 
	private HashMap lstMonthList = null;
	private ArrayList lstYearList = new ArrayList();
	
	public HashMap getLstLoanType() {
		return lstLoanType;
	}
	public void setLstLoanType(HashMap lstLoanType) {
		this.lstLoanType = lstLoanType;
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
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpNameFlag() {
		return empNameFlag;
	}
	public void setEmpNameFlag(String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}
	public String getDivFlag() {
		return divFlag;
	}
	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}
	public String getLoanAmountFlag() {
		return loanAmountFlag;
	}
	public void setLoanAmountFlag(String loanAmountFlag) {
		this.loanAmountFlag = loanAmountFlag;
	}
	public String getLoanTypeFlag() {
		return loanTypeFlag;
	}
	public void setLoanTypeFlag(String loanTypeFlag) {
		this.loanTypeFlag = loanTypeFlag;
	}
	public String getLoanPaymentDateFlag() {
		return loanPaymentDateFlag;
	}
	public void setLoanPaymentDateFlag(String loanPaymentDateFlag) {
		this.loanPaymentDateFlag = loanPaymentDateFlag;
	}
	public String getLoanInstallmentFlag() {
		return loanInstallmentFlag;
	}
	public void setLoanInstallmentFlag(String loanInstallmentFlag) {
		this.loanInstallmentFlag = loanInstallmentFlag;
	}
	public String getNoOfInstallFlag() {
		return noOfInstallFlag;
	}
	public void setNoOfInstallFlag(String noOfInstallFlag) {
		this.noOfInstallFlag = noOfInstallFlag;
	}
	public String getLoanDateSelect() {
		return loanDateSelect;
	}
	public void setLoanDateSelect(String loanDateSelect) {
		this.loanDateSelect = loanDateSelect;
	}
	public String getLoanFromDate() {
		return loanFromDate;
	}
	public void setLoanFromDate(String loanFromDate) {
		this.loanFromDate = loanFromDate;
	}
	public String getLoanToDate() {
		return loanToDate;
	}
	public void setLoanToDate(String loanToDate) {
		this.loanToDate = loanToDate;
	}
	public String getSavedReport() {
		return savedReport;
	}
	public void setSavedReport(String savedReport) {
		this.savedReport = savedReport;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getHidReportRadio() {
		return hidReportRadio;
	}
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	public String getReportView() {
		return reportView;
	}
	public void setReportView(String reportView) {
		this.reportView = reportView;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getHiddenSortBy() {
		return hiddenSortBy;
	}
	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}
	public String getSortByOrder() {
		return sortByOrder;
	}
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}
	public String getThenBy1() {
		return thenBy1;
	}
	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}
	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}
	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}
	public String getThenByOrder1() {
		return thenByOrder1;
	}
	public void setThenByOrder1(String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}
	public String getThenByOrder2() {
		return thenByOrder2;
	}
	public void setThenByOrder2(String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}
	public String getThenBy2() {
		return thenBy2;
	}
	public void setThenBy2(String thenBy2) {
		this.thenBy2 = thenBy2;
	}
	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}
	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}
	public String getSortByAsc() {
		return sortByAsc;
	}
	public void setSortByAsc(String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}
	public String getSortByDsc() {
		return sortByDsc;
	}
	public void setSortByDsc(String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}
	public String getThenByOrder1Asc() {
		return thenByOrder1Asc;
	}
	public void setThenByOrder1Asc(String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}
	public String getThenByOrder1Dsc() {
		return thenByOrder1Dsc;
	}
	public void setThenByOrder1Dsc(String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}
	public String getThenByOrder2Asc() {
		return thenByOrder2Asc;
	}
	public void setThenByOrder2Asc(String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}
	public String getThenByOrder2Dsc() {
		return thenByOrder2Dsc;
	}
	public void setThenByOrder2Dsc(String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getHiddenColumns() {
		return hiddenColumns;
	}
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getHidReportView() {
		return hidReportView;
	}
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getReportTypeStr() {
		return reportTypeStr;
	}
	public void setReportTypeStr(String reportTypeStr) {
		this.reportTypeStr = reportTypeStr;
	}
	public HashMap getLstMonthList() {
		return lstMonthList;
	}
	public void setLstMonthList(HashMap lstMonthList) {
		this.lstMonthList = lstMonthList;
	}
	public ArrayList getLstYearList() {
		return lstYearList;
	}
	public void setLstYearList(ArrayList lstYearList) {
		this.lstYearList = lstYearList;
	}
	public String getRefundable() {
		return refundable;
	}
	public void setRefundable(String refundable) {
		this.refundable = refundable;
	}
}
