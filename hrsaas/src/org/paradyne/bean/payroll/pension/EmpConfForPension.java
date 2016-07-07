/**
 * 
 */
package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class EmpConfForPension extends BeanBase {
	
	/*
	 * variables for Filter screen
 	 */
	private String applDivisionCode="";
	private String applDivisionName="";
	private String applBranchCode="";
	private String applBranchName="";
	private String applDeptCode="";
	private String applDeptName="";
	private String applDesgCode="";
	private String applDesgName="";
	private String applETypeCode="";
	private String applETypeName="";
	private String applGradeCode="";
	private String applGradeName="";
	private String applEmpCode="";
	private String applEmpName="";
	
	/*
	 * variables for sorting screen
	 * 
	 */
	private String sortBy1="";
	private String sortBy2="";
	private String sortBy3="";
	private String sortByOrder1="";
	private String sortByOrder2="";
	private String sortByOrder3="";
	
	
	/*
	 * variables for column screen
	 * 
	 */
	
	private String empTokenChk="";
	private String empNameChk="";
	private String divisionChk="";
	private String branchChk="";
	private String desgChk="";
	private String dobChk="";
	private String dojChk="";
	private String ageChk="";
	private String statusChk="";
	private String yearsOfServiceChk="";
	private String checkedCount="";
	
	/*
	 * variables for Advance Filter screen
	 * 
	 */
	private String dobCompare="";
	private String dobFrom="";
	private String dobTo="";
	private String dojCompare="";
	private String dojFrom="";
	private String dojTo="";
	private String dorCompare="";
	private String dorFrom="";
	private String dorTo="";
	private String ageFilter="";
	private String ageOperator="";
	
	ArrayList applEmpList;
	private String empToken="";
	private String empName="";
	private String empCode="";
	private String empDiv="";
	private String divCode="";
	private String empBranch="";
	private String empDesg="";
	private String empDob="";
	private String empDoj="";
	private String empDateOfRet="";
	private String empAge="";
	private String empStatus="";
	private String pensionType="";
	private String qualfYears="";
	private String myPageEmpConf="";
	private String processingChkHd="";
	private String noData="false";
	
	
	private String reportType="";
	
	
	/*
	 * Variables for pension calculation
	 * 
	 */
	
	private String empIdPC="";
	private String empCodePC="";
	private String empCodePCDet="";
	private String empNamePC="";
	private String pensionTypePC="";
	private String pensionAmtPC="";
	private String grossPensionAmt="";
	private String lockFlag="false";
	private String lockFlagItt="false";
	
	private String myPagePC="";
	private String lockChkHd="";
	private String dateOfRetirementPC="";
	private String qualfYearsServicePC="";
	private String avgEmolumentPC="";
	private String pensionAmtPCDet=""; 
	private String commutationAmtPC="";
	private String commEffectFromYear="";
	private String commEffectFromMonth="";
	private String commEffectToYear="";
	private String commEffectToMonth="";
	private String noDataPC="false";
	
	private String recoveryHead="";
	private String recoveryHeadCode="";
	private String recoveryAmt="";
	private String recoveryComments="";
	private String recoveryMonth="";
	private String recoveryYear="";
	private String recoveryFlag="false";
	private String recoveryHeadList="";
	private String recoveryHeadCodeList="";
	private String recoveryAmtList="";
	private String recoveryMonthList="";
	private String recoveryYearList="";
	private String recoveryCommentsList="";
	
	private String creditHead="";
	private String creditHeadCode="";
	private String creditHeadAmt=""; 
	private String avgEmolFormula="";
	private String avgEmolFormulaHidden="";
	private String pensionFormula="";
	private String pensionFormulaHidden="";
	private String totEmolument="";
	private String emolMonths="";
	private String pensionAmtPCList="";
	private String maxYearsServicePC="";
	ArrayList creditList;
	
	
	
	private String paraId="";
	
	private ArrayList recoveryList;
	
	ArrayList empListPC;
	
	//ADDED BY REEBA
	private String onholdFlag="false";
	//Added by Ganesh 
	private String employeeToken="";
	private String employeeName = "";
	private String employeeCode = "";
	private String payBillName = "";
	private String payBillId = "";
	
	private String searchFlag="false";
	private String pageFlag="false";
	private String viewEditFlag="false";
	private String listFlag;
	private String stopPensionFlag="false";
	private String report = "";
	private String hiddenStatus = "";
	
	
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getStopPensionFlag() {
		return stopPensionFlag;
	}
	public void setStopPensionFlag(String stopPensionFlag) {
		this.stopPensionFlag = stopPensionFlag;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getViewEditFlag() {
		return viewEditFlag;
	}
	public void setViewEditFlag(String viewEditFlag) {
		this.viewEditFlag = viewEditFlag;
	}
	public ArrayList getEmpListPC() {
		return empListPC;
	}
	public void setEmpListPC(ArrayList empListPC) {
		this.empListPC = empListPC;
	}
	public String getMyPageEmpConf() {
		return myPageEmpConf;
	}
	public void setMyPageEmpConf(String myPageEmpConf) {
		this.myPageEmpConf = myPageEmpConf;
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpDiv() {
		return empDiv;
	}
	public void setEmpDiv(String empDiv) {
		this.empDiv = empDiv;
	}
	public String getEmpBranch() {
		return empBranch;
	}
	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}
	public String getEmpDesg() {
		return empDesg;
	}
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	public String getEmpDob() {
		return empDob;
	}
	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}
	public String getEmpDoj() {
		return empDoj;
	}
	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}
	public String getEmpDateOfRet() {
		return empDateOfRet;
	}
	public void setEmpDateOfRet(String empDateOfRet) {
		this.empDateOfRet = empDateOfRet;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public String getQualfYears() {
		return qualfYears;
	}
	public void setQualfYears(String qualfYears) {
		this.qualfYears = qualfYears;
	}
	public ArrayList getApplEmpList() {
		return applEmpList;
	}
	public void setApplEmpList(ArrayList applEmpList) {
		this.applEmpList = applEmpList;
	}
	public String getApplDivisionCode() {
		return applDivisionCode;
	}
	public void setApplDivisionCode(String applDivisionCode) {
		this.applDivisionCode = applDivisionCode;
	}
	public String getApplDivisionName() {
		return applDivisionName;
	}
	public void setApplDivisionName(String applDivisionName) {
		this.applDivisionName = applDivisionName;
	}
	public String getApplBranchCode() {
		return applBranchCode;
	}
	public void setApplBranchCode(String applBranchCode) {
		this.applBranchCode = applBranchCode;
	}
	public String getApplBranchName() {
		return applBranchName;
	}
	public void setApplBranchName(String applBranchName) {
		this.applBranchName = applBranchName;
	}
	public String getApplDeptCode() {
		return applDeptCode;
	}
	public void setApplDeptCode(String applDeptCode) {
		this.applDeptCode = applDeptCode;
	}
	public String getApplDeptName() {
		return applDeptName;
	}
	public void setApplDeptName(String applDeptName) {
		this.applDeptName = applDeptName;
	}
	public String getApplDesgCode() {
		return applDesgCode;
	}
	public void setApplDesgCode(String applDesgCode) {
		this.applDesgCode = applDesgCode;
	}
	public String getApplDesgName() {
		return applDesgName;
	}
	public void setApplDesgName(String applDesgName) {
		this.applDesgName = applDesgName;
	}
	public String getApplETypeCode() {
		return applETypeCode;
	}
	public void setApplETypeCode(String applETypeCode) {
		this.applETypeCode = applETypeCode;
	}
	public String getApplETypeName() {
		return applETypeName;
	}
	public void setApplETypeName(String applETypeName) {
		this.applETypeName = applETypeName;
	}
	public String getApplGradeCode() {
		return applGradeCode;
	}
	public void setApplGradeCode(String applGradeCode) {
		this.applGradeCode = applGradeCode;
	}
	public String getApplGradeName() {
		return applGradeName;
	}
	public void setApplGradeName(String applGradeName) {
		this.applGradeName = applGradeName;
	}
	public String getApplEmpCode() {
		return applEmpCode;
	}
	public void setApplEmpCode(String applEmpCode) {
		this.applEmpCode = applEmpCode;
	}
	public String getApplEmpName() {
		return applEmpName;
	}
	public void setApplEmpName(String applEmpName) {
		this.applEmpName = applEmpName;
	}
	public String getEmpTokenChk() {
		return empTokenChk;
	}
	public void setEmpTokenChk(String empTokenChk) {
		this.empTokenChk = empTokenChk;
	}
	public String getEmpNameChk() {
		return empNameChk;
	}
	public void setEmpNameChk(String empNameChk) {
		this.empNameChk = empNameChk;
	}
	public String getDivisionChk() {
		return divisionChk;
	}
	public void setDivisionChk(String divisionChk) {
		this.divisionChk = divisionChk;
	}
	public String getBranchChk() {
		return branchChk;
	}
	public void setBranchChk(String branchChk) {
		this.branchChk = branchChk;
	}
	public String getDesgChk() {
		return desgChk;
	}
	public void setDesgChk(String desgChk) {
		this.desgChk = desgChk;
	}
	public String getDobChk() {
		return dobChk;
	}
	public void setDobChk(String dobChk) {
		this.dobChk = dobChk;
	}
	public String getDojChk() {
		return dojChk;
	}
	public void setDojChk(String dojChk) {
		this.dojChk = dojChk;
	}
	public String getAgeChk() {
		return ageChk;
	}
	public void setAgeChk(String ageChk) {
		this.ageChk = ageChk;
	}
	public String getStatusChk() {
		return statusChk;
	}
	public void setStatusChk(String statusChk) {
		this.statusChk = statusChk;
	}
	public String getYearsOfServiceChk() {
		return yearsOfServiceChk;
	}
	public void setYearsOfServiceChk(String yearsOfServiceChk) {
		this.yearsOfServiceChk = yearsOfServiceChk;
	}
	public String getSortByOrder1() {
		return sortByOrder1;
	}
	public void setSortByOrder1(String sortByOrder1) {
		this.sortByOrder1 = sortByOrder1;
	}
	public String getSortBy1() {
		return sortBy1;
	}
	public void setSortBy1(String sortBy1) {
		this.sortBy1 = sortBy1;
	}
	public String getSortBy2() {
		return sortBy2;
	}
	public void setSortBy2(String sortBy2) {
		this.sortBy2 = sortBy2;
	}
	public String getSortBy3() {
		return sortBy3;
	}
	public void setSortBy3(String sortBy3) {
		this.sortBy3 = sortBy3;
	}
	public String getSortByOrder2() {
		return sortByOrder2;
	}
	public void setSortByOrder2(String sortByOrder2) {
		this.sortByOrder2 = sortByOrder2;
	}
	public String getSortByOrder3() {
		return sortByOrder3;
	}
	public void setSortByOrder3(String sortByOrder3) {
		this.sortByOrder3 = sortByOrder3;
	}
	public String getDobCompare() {
		return dobCompare;
	}
	public void setDobCompare(String dobCompare) {
		this.dobCompare = dobCompare;
	}
	public String getDobFrom() {
		return dobFrom;
	}
	public void setDobFrom(String dobFrom) {
		this.dobFrom = dobFrom;
	}
	public String getDobTo() {
		return dobTo;
	}
	public void setDobTo(String dobTo) {
		this.dobTo = dobTo;
	}
	public String getDojCompare() {
		return dojCompare;
	}
	public void setDojCompare(String dojCompare) {
		this.dojCompare = dojCompare;
	}
	public String getDojFrom() {
		return dojFrom;
	}
	public void setDojFrom(String dojFrom) {
		this.dojFrom = dojFrom;
	}
	public String getDojTo() {
		return dojTo;
	}
	public void setDojTo(String dojTo) {
		this.dojTo = dojTo;
	}
	public String getAgeFilter() {
		return ageFilter;
	}
	public void setAgeFilter(String ageFilter) {
		this.ageFilter = ageFilter;
	}
	public String getAgeOperator() {
		return ageOperator;
	}
	public void setAgeOperator(String ageOperator) {
		this.ageOperator = ageOperator;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getProcessingChkHd() {
		return processingChkHd;
	}
	public void setProcessingChkHd(String processingChkHd) {
		this.processingChkHd = processingChkHd;
	}
	public String getEmpIdPC() {
		return empIdPC;
	}
	public void setEmpIdPC(String empIdPC) {
		this.empIdPC = empIdPC;
	}
	public String getEmpCodePC() {
		return empCodePC;
	}
	public void setEmpCodePC(String empCodePC) {
		this.empCodePC = empCodePC;
	}
	public String getEmpNamePC() {
		return empNamePC;
	}
	public void setEmpNamePC(String empNamePC) {
		this.empNamePC = empNamePC;
	}
	public String getPensionTypePC() {
		return pensionTypePC;
	}
	public void setPensionTypePC(String pensionTypePC) {
		this.pensionTypePC = pensionTypePC;
	}
	public String getPensionAmtPC() {
		return pensionAmtPC;
	}
	public void setPensionAmtPC(String pensionAmtPC) {
		this.pensionAmtPC = pensionAmtPC;
	}
	public String getMyPagePC() {
		return myPagePC;
	}
	public void setMyPagePC(String myPagePC) {
		this.myPagePC = myPagePC;
	}
	public String getLockChkHd() {
		return lockChkHd;
	}
	public void setLockChkHd(String lockChkHd) {
		this.lockChkHd = lockChkHd;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(String checkedCount) {
		this.checkedCount = checkedCount;
	}
	public String getDateOfRetirementPC() {
		return dateOfRetirementPC;
	}
	public void setDateOfRetirementPC(String dateOfRetirementPC) {
		this.dateOfRetirementPC = dateOfRetirementPC;
	}
	public String getQualfYearsServicePC() {
		return qualfYearsServicePC;
	}
	public void setQualfYearsServicePC(String qualfYearsServicePC) {
		this.qualfYearsServicePC = qualfYearsServicePC;
	}
	public String getAvgEmolumentPC() {
		return avgEmolumentPC;
	}
	public void setAvgEmolumentPC(String avgEmolumentPC) {
		this.avgEmolumentPC = avgEmolumentPC;
	}
	public String getPensionAmtPCDet() {
		return pensionAmtPCDet;
	}
	public void setPensionAmtPCDet(String pensionAmtPCDet) {
		this.pensionAmtPCDet = pensionAmtPCDet;
	}
	public String getCommutationAmtPC() {
		return commutationAmtPC;
	}
	public void setCommutationAmtPC(String commutationAmtPC) {
		this.commutationAmtPC = commutationAmtPC;
	}
	public String getEmpCodePCDet() {
		return empCodePCDet;
	}
	public void setEmpCodePCDet(String empCodePCDet) {
		this.empCodePCDet = empCodePCDet;
	}
	public String getCommEffectFromYear() {
		return commEffectFromYear;
	}
	public void setCommEffectFromYear(String commEffectFromYear) {
		this.commEffectFromYear = commEffectFromYear;
	}
	public String getCommEffectFromMonth() {
		return commEffectFromMonth;
	}
	public void setCommEffectFromMonth(String commEffectFromMonth) {
		this.commEffectFromMonth = commEffectFromMonth;
	}
	public String getCommEffectToYear() {
		return commEffectToYear;
	}
	public void setCommEffectToYear(String commEffectToYear) {
		this.commEffectToYear = commEffectToYear;
	}
	public String getCommEffectToMonth() {
		return commEffectToMonth;
	}
	public void setCommEffectToMonth(String commEffectToMonth) {
		this.commEffectToMonth = commEffectToMonth;
	}
	public String getRecoveryHead() {
		return recoveryHead;
	}
	public void setRecoveryHead(String recoveryHead) {
		this.recoveryHead = recoveryHead;
	}
	public String getRecoveryHeadCode() {
		return recoveryHeadCode;
	}
	public void setRecoveryHeadCode(String recoveryHeadCode) {
		this.recoveryHeadCode = recoveryHeadCode;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getRecoveryMonth() {
		return recoveryMonth;
	}
	public void setRecoveryMonth(String recoveryMonth) {
		this.recoveryMonth = recoveryMonth;
	}
	public String getRecoveryYear() {
		return recoveryYear;
	}
	public void setRecoveryYear(String recoveryYear) {
		this.recoveryYear = recoveryYear;
	}
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}
	public String getRecoveryHeadList() {
		return recoveryHeadList;
	}
	public void setRecoveryHeadList(String recoveryHeadList) {
		this.recoveryHeadList = recoveryHeadList;
	}
	public String getRecoveryHeadCodeList() {
		return recoveryHeadCodeList;
	}
	public void setRecoveryHeadCodeList(String recoveryHeadCodeList) {
		this.recoveryHeadCodeList = recoveryHeadCodeList;
	}
	public String getRecoveryAmtList() {
		return recoveryAmtList;
	}
	public void setRecoveryAmtList(String recoveryAmtList) {
		this.recoveryAmtList = recoveryAmtList;
	}
	public String getRecoveryMonthList() {
		return recoveryMonthList;
	}
	public void setRecoveryMonthList(String recoveryMonthList) {
		this.recoveryMonthList = recoveryMonthList;
	}
	public String getRecoveryYearList() {
		return recoveryYearList;
	}
	public void setRecoveryYearList(String recoveryYearList) {
		this.recoveryYearList = recoveryYearList;
	}
	public ArrayList getRecoveryList() {
		return recoveryList;
	}
	public void setRecoveryList(ArrayList recoveryList) {
		this.recoveryList = recoveryList;
	}
	public String getNoDataPC() {
		return noDataPC;
	}
	public void setNoDataPC(String noDataPC) {
		this.noDataPC = noDataPC;
	}
	public String getRecoveryComments() {
		return recoveryComments;
	}
	public void setRecoveryComments(String recoveryComments) {
		this.recoveryComments = recoveryComments;
	}
	public String getRecoveryCommentsList() {
		return recoveryCommentsList;
	}
	public void setRecoveryCommentsList(String recoveryCommentsList) {
		this.recoveryCommentsList = recoveryCommentsList;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	public String getCreditHeadCode() {
		return creditHeadCode;
	}
	public void setCreditHeadCode(String creditHeadCode) {
		this.creditHeadCode = creditHeadCode;
	}
	public String getCreditHeadAmt() {
		return creditHeadAmt;
	}
	public void setCreditHeadAmt(String creditHeadAmt) {
		this.creditHeadAmt = creditHeadAmt;
	}
	public String getAvgEmolFormula() {
		return avgEmolFormula;
	}
	public void setAvgEmolFormula(String avgEmolFormula) {
		this.avgEmolFormula = avgEmolFormula;
	}
	public String getAvgEmolFormulaHidden() {
		return avgEmolFormulaHidden;
	}
	public void setAvgEmolFormulaHidden(String avgEmolFormulaHidden) {
		this.avgEmolFormulaHidden = avgEmolFormulaHidden;
	}
	public String getPensionFormula() {
		return pensionFormula;
	}
	public void setPensionFormula(String pensionFormula) {
		this.pensionFormula = pensionFormula;
	}
	public String getPensionFormulaHidden() {
		return pensionFormulaHidden;
	}
	public void setPensionFormulaHidden(String pensionFormulaHidden) {
		this.pensionFormulaHidden = pensionFormulaHidden;
	}
	public ArrayList getCreditList() {
		return creditList;
	}
	public void setCreditList(ArrayList creditList) {
		this.creditList = creditList;
	}
	public String getMaxYearsServicePC() {
		return maxYearsServicePC;
	}
	public void setMaxYearsServicePC(String maxYearsServicePC) {
		this.maxYearsServicePC = maxYearsServicePC;
	}
	public String getDorCompare() {
		return dorCompare;
	}
	public void setDorCompare(String dorCompare) {
		this.dorCompare = dorCompare;
	}
	public String getDorFrom() {
		return dorFrom;
	}
	public void setDorFrom(String dorFrom) {
		this.dorFrom = dorFrom;
	}
	public String getDorTo() {
		return dorTo;
	}
	public void setDorTo(String dorTo) {
		this.dorTo = dorTo;
	}
	public String getPensionAmtPCList() {
		return pensionAmtPCList;
	}
	public void setPensionAmtPCList(String pensionAmtPCList) {
		this.pensionAmtPCList = pensionAmtPCList;
	}
	public String getTotEmolument() {
		return totEmolument;
	}
	public void setTotEmolument(String totEmolument) {
		this.totEmolument = totEmolument;
	}
	public String getEmolMonths() {
		return emolMonths;
	}
	public void setEmolMonths(String emolMonths) {
		this.emolMonths = emolMonths;
	}
	public String getGrossPensionAmt() {
		return grossPensionAmt;
	}
	public void setGrossPensionAmt(String grossPensionAmt) {
		this.grossPensionAmt = grossPensionAmt;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getLockFlagItt() {
		return lockFlagItt;
	}
	public void setLockFlagItt(String lockFlagItt) {
		this.lockFlagItt = lockFlagItt;
	}
	public String getOnholdFlag() {
		return onholdFlag;
	}
	public void setOnholdFlag(String onholdFlag) {
		this.onholdFlag = onholdFlag;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	
	
}
