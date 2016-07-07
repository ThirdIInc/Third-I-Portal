package org.paradyne.bean.lta;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LtaMisReport extends BeanBase {
	/*
	 * variables for Filter screen
 	 */
	private String applDivisionCode;
	private String applDivisionName;
	private String applBranchCode;
	private String applBranchName;
	private String applDeptCode;
	private String applDeptName;
	private String applDesgCode;
	private String applDesgName;
	private String applETypeCode;
	private String applETypeName;
	private String applGradeCode;
	private String applGradeName;
	private String applEmpCode;
	private String applEmpName;
	
	/*
	 * variables for sorting screen
	 * 
	 */
	private String sortBy1;
	private String sortBy2;
	private String sortBy3;
	private String sortByOrder1;
	private String sortByOrder2;
	private String sortByOrder3;
	
	private String hiddenColumns;
	private String backFlag;
	private String hiddenSortBy;
	private String hiddenThenBy1;
	private String hiddenThenBy2;
	
	
	
	/*
	 * variables for column screen
	 * 
	 */
	
	private String empTokenChk;
	private String empNameChk;
	private String divisionChk;
	private String branchChk;
	private String blockYrChk;
	private String visitYearChk;
	private String claimDateChk;
	private String claimTypeChk;
	private String claimAmtChk;
	private String exemptedChk;
	private String exemptedYearChk;
	private String checkedCount;
	
	/*
	 * variables for Advance Filter screen
	 * 
	 */
	private String claimDateCompare;
	private String claimDateFrom;
	private String claimDateTo;
	private String yearOfVisitOperator;
	private String yearOfVisitFilter;
	private String claimAmtOperator;
	private String claimAmtFilter;
	private String exemptionOperator;
	private String exemptionFilter;
	
	
	private String exportAll="";
	

	private String myPage;
	
	private String noData="false";
	
	private String dataLength="";
	
	
	private String reportType;


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


	public String getSortByOrder1() {
		return sortByOrder1;
	}


	public void setSortByOrder1(String sortByOrder1) {
		this.sortByOrder1 = sortByOrder1;
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


	


	public String getCheckedCount() {
		return checkedCount;
	}


	public void setCheckedCount(String checkedCount) {
		this.checkedCount = checkedCount;
	}

	public String getClaimDateCompare() {
		return claimDateCompare;
	}


	public void setClaimDateCompare(String claimDateCompare) {
		this.claimDateCompare = claimDateCompare;
	}


	public String getClaimDateFrom() {
		return claimDateFrom;
	}


	public void setClaimDateFrom(String claimDateFrom) {
		this.claimDateFrom = claimDateFrom;
	}


	public String getClaimDateTo() {
		return claimDateTo;
	}


	public void setClaimDateTo(String claimDateTo) {
		this.claimDateTo = claimDateTo;
	}


	public String getYearOfVisitOperator() {
		return yearOfVisitOperator;
	}


	public void setYearOfVisitOperator(String yearOfVisitOperator) {
		this.yearOfVisitOperator = yearOfVisitOperator;
	}


	public String getYearOfVisitFilter() {
		return yearOfVisitFilter;
	}


	public void setYearOfVisitFilter(String yearOfVisitFilter) {
		this.yearOfVisitFilter = yearOfVisitFilter;
	}


	public String getClaimAmtOperator() {
		return claimAmtOperator;
	}


	public void setClaimAmtOperator(String claimAmtOperator) {
		this.claimAmtOperator = claimAmtOperator;
	}


	public String getClaimAmtFilter() {
		return claimAmtFilter;
	}


	public void setClaimAmtFilter(String claimAmtFilter) {
		this.claimAmtFilter = claimAmtFilter;
	}


	public String getExemptionOperator() {
		return exemptionOperator;
	}


	public void setExemptionOperator(String exemptionOperator) {
		this.exemptionOperator = exemptionOperator;
	}

	public String getExemptionFilter() {
		return exemptionFilter;
	}


	public void setExemptionFilter(String exemptionFilter) {
		this.exemptionFilter = exemptionFilter;
	}


	

	public String getMyPage() {
		return myPage;
	}


	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}


	

	public String getNoData() {
		return noData;
	}


	public void setNoData(String noData) {
		this.noData = noData;
	}


	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getBlockYrChk() {
		return blockYrChk;
	}


	public void setBlockYrChk(String blockYrChk) {
		this.blockYrChk = blockYrChk;
	}


	public String getVisitYearChk() {
		return visitYearChk;
	}


	public void setVisitYearChk(String visitYearChk) {
		this.visitYearChk = visitYearChk;
	}


	public String getClaimDateChk() {
		return claimDateChk;
	}


	public void setClaimDateChk(String claimDateChk) {
		this.claimDateChk = claimDateChk;
	}


	public String getClaimTypeChk() {
		return claimTypeChk;
	}


	public void setClaimTypeChk(String claimTypeChk) {
		this.claimTypeChk = claimTypeChk;
	}


	public String getClaimAmtChk() {
		return claimAmtChk;
	}


	public void setClaimAmtChk(String claimAmtChk) {
		this.claimAmtChk = claimAmtChk;
	}


	public String getExemptedChk() {
		return exemptedChk;
	}


	public void setExemptedChk(String exemptedChk) {
		this.exemptedChk = exemptedChk;
	}


	public String getExemptedYearChk() {
		return exemptedYearChk;
	}


	public void setExemptedYearChk(String exemptedYearChk) {
		this.exemptedYearChk = exemptedYearChk;
	}


	public String getHiddenColumns() {
		return hiddenColumns;
	}


	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}


	public String getBackFlag() {
		return backFlag;
	}


	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}


	public String getHiddenSortBy() {
		return hiddenSortBy;
	}


	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}


	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}


	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}


	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}


	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}


	public String getExportAll() {
		return exportAll;
	}


	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}


	public String getDataLength() {
		return dataLength;
	}


	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

}
