package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class BankStatementMis extends BeanBase {

	private String divName ="";
	private String divCode ="";
	private String centerName ="";
	private String centerCode ="";
	private String bankCode ="";
	private String bankName ="";
	private String savedReport ="";
	private String reportId ="";
	private String reportTitle ="";
	private String settingName  ="";
	private String reportType  ="";
	 
	private String accNumberFlag ="";
	private String currencyCodeFlag ="";
	private String solIdFlag ="";
	private String creditdebitFlag ="";
	private String transactiontypeFlag ="";
	private String ifsccodeFlag ="";
	private String empcodeFlag ="";
	private String empnameFlag ="";
	private String transactionDtlFlag ="";
	private String customerrefnoFlag ="";
	private String transactionDateFlag ="";
	private String bankFlag ="";
	private String bankbrnNameFlag ="";
	private String beneficaryEmailidFlag ="";
	
	
	private String sortBy="";
	private String hiddenSortBy="";
	private String  sortByOrder="";
	private String  thenBy1="";
	private String hiddenThenBy1="";
	private String  thenByOrder1="";
	private String  thenByOrder2=""; 
	private String  thenBy2="";
	private String hiddenThenBy2="";
	
	private String  sortByAsc="checked"; 	
	private String  sortByDsc=""; 
	private String  thenByOrder1Asc="checked"; 	
	private String  thenByOrder1Dsc=""; 
	private String  thenByOrder2Asc="checked"; 	
	private String  thenByOrder2Dsc="";
	
	
	private String columnOrdering="";
	private String hiddenColumns="";
	
	private String myPage="";
	private String show="";
	private String dataLength="";
	private String hidReportView="checked"; 	
	private String hidReportRadio="";
	private String exportAll="";
	private String noData="false";
	
	private String backFlag = "";
	private String reqStatus = "";
	
	private String reportTypeStr = "";
	
	private String month ="";
	private String year ="";
	
	private String divisionAccountNoFlag ="";
	
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
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSavedReport() {
		return savedReport;
	}
	public void setSavedReport(String savedReport) {
		this.savedReport = savedReport;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getAccNumberFlag() {
		return accNumberFlag;
	}
	public void setAccNumberFlag(String accNumberFlag) {
		this.accNumberFlag = accNumberFlag;
	}
	public String getCurrencyCodeFlag() {
		return currencyCodeFlag;
	}
	public void setCurrencyCodeFlag(String currencyCodeFlag) {
		this.currencyCodeFlag = currencyCodeFlag;
	}
	public String getSolIdFlag() {
		return solIdFlag;
	}
	public void setSolIdFlag(String solIdFlag) {
		this.solIdFlag = solIdFlag;
	}
	public String getCreditdebitFlag() {
		return creditdebitFlag;
	}
	public void setCreditdebitFlag(String creditdebitFlag) {
		this.creditdebitFlag = creditdebitFlag;
	}
	public String getTransactiontypeFlag() {
		return transactiontypeFlag;
	}
	public void setTransactiontypeFlag(String transactiontypeFlag) {
		this.transactiontypeFlag = transactiontypeFlag;
	}
	public String getIfsccodeFlag() {
		return ifsccodeFlag;
	}
	public void setIfsccodeFlag(String ifsccodeFlag) {
		this.ifsccodeFlag = ifsccodeFlag;
	}
	public String getEmpcodeFlag() {
		return empcodeFlag;
	}
	public void setEmpcodeFlag(String empcodeFlag) {
		this.empcodeFlag = empcodeFlag;
	}
	public String getEmpnameFlag() {
		return empnameFlag;
	}
	public void setEmpnameFlag(String empnameFlag) {
		this.empnameFlag = empnameFlag;
	}
	public String getTransactionDtlFlag() {
		return transactionDtlFlag;
	}
	public void setTransactionDtlFlag(String transactionDtlFlag) {
		this.transactionDtlFlag = transactionDtlFlag;
	}
	public String getCustomerrefnoFlag() {
		return customerrefnoFlag;
	}
	public void setCustomerrefnoFlag(String customerrefnoFlag) {
		this.customerrefnoFlag = customerrefnoFlag;
	}
	public String getTransactionDateFlag() {
		return transactionDateFlag;
	}
	public void setTransactionDateFlag(String transactionDateFlag) {
		this.transactionDateFlag = transactionDateFlag;
	}
	public String getBankFlag() {
		return bankFlag;
	}
	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}
	public String getBankbrnNameFlag() {
		return bankbrnNameFlag;
	}
	public void setBankbrnNameFlag(String bankbrnNameFlag) {
		this.bankbrnNameFlag = bankbrnNameFlag;
	}
	public String getBeneficaryEmailidFlag() {
		return beneficaryEmailidFlag;
	}
	public void setBeneficaryEmailidFlag(String beneficaryEmailidFlag) {
		this.beneficaryEmailidFlag = beneficaryEmailidFlag;
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
	public String getColumnOrdering() {
		return columnOrdering;
	}
	public void setColumnOrdering(String columnOrdering) {
		this.columnOrdering = columnOrdering;
	}
	public String getHiddenColumns() {
		return hiddenColumns;
	}
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
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
	public String getHidReportView() {
		return hidReportView;
	}
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}
	public String getHidReportRadio() {
		return hidReportRadio;
	}
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getReportTypeStr() {
		return reportTypeStr;
	}
	public void setReportTypeStr(String reportTypeStr) {
		this.reportTypeStr = reportTypeStr;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	/**
	 * @return the divisionAccountNoFlag
	 */
	public String getDivisionAccountNoFlag() {
		return divisionAccountNoFlag;
	}
	/**
	 * @param divisionAccountNoFlag the divisionAccountNoFlag to set
	 */
	public void setDivisionAccountNoFlag(String divisionAccountNoFlag) {
		this.divisionAccountNoFlag = divisionAccountNoFlag;
	}
	
}
