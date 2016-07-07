/**
 * 
 */
package org.paradyne.bean.lta;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class LTACalculation extends BeanBase {
	/*
	 * variables for Filter screen
 	 */
	
	ArrayList applEmpList;
	private String empToken;
	private String empName;
	private String empCode;
	private String empBranch;
	private String empDoj;
	private String myPageEmpConf;
	private String noData="false";
	
	private String claimType;
	private String claimDate;
	private String claimAmt;
	private String exemptedAmt;
	private String blockYearFrom;
	private String blockYearTo;
	private String yearOfVisit;
	private String isTaxExempted;
	private String ltaId;
	private String yearOfExemption;
	private String yearOfExemptionTo;
	private String isOnload="true";
	
	
	private String empTokenList;
	private String empNameList;
	private String empCodeList;
	
	private String claimAmtList;
	private String blockYearFromList;
	private String blockYearToList;
	private String yearOfVisitList;
	private String isTaxExemptedList;
	private String ltaIdList;
	
	
	public ArrayList getApplEmpList() {
		return applEmpList;
	}


	public void setApplEmpList(ArrayList applEmpList) {
		this.applEmpList = applEmpList;
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

	public String getEmpBranch() {
		return empBranch;
	}


	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}



	public String getEmpDoj() {
		return empDoj;
	}


	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}

	public String getMyPageEmpConf() {
		return myPageEmpConf;
	}


	public void setMyPageEmpConf(String myPageEmpConf) {
		this.myPageEmpConf = myPageEmpConf;
	}


	public String getNoData() {
		return noData;
	}


	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getClaimType() {
		return claimType;
	}


	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}


	public String getClaimDate() {
		return claimDate;
	}


	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}


	public String getClaimAmt() {
		return claimAmt;
	}


	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}


	public String getBlockYearFrom() {
		return blockYearFrom;
	}


	public void setBlockYearFrom(String blockYearFrom) {
		this.blockYearFrom = blockYearFrom;
	}


	public String getBlockYearTo() {
		return blockYearTo;
	}


	public void setBlockYearTo(String blockYearTo) {
		this.blockYearTo = blockYearTo;
	}


	public String getYearOfVisit() {
		return yearOfVisit;
	}


	public void setYearOfVisit(String yearOfVisit) {
		this.yearOfVisit = yearOfVisit;
	}


	public String getIsTaxExempted() {
		return isTaxExempted;
	}


	public void setIsTaxExempted(String isTaxExempted) {
		this.isTaxExempted = isTaxExempted;
	}


	public String getYearOfExemption() {
		return yearOfExemption;
	}


	public void setYearOfExemption(String yearOfExemption) {
		this.yearOfExemption = yearOfExemption;
	}


	public String getLtaId() {
		return ltaId;
	}


	public void setLtaId(String ltaId) {
		this.ltaId = ltaId;
	}


	public String getIsOnload() {
		return isOnload;
	}


	public void setIsOnload(String isOnload) {
		this.isOnload = isOnload;
	}


	public String getEmpTokenList() {
		return empTokenList;
	}


	public void setEmpTokenList(String empTokenList) {
		this.empTokenList = empTokenList;
	}


	public String getEmpNameList() {
		return empNameList;
	}


	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}


	public String getEmpCodeList() {
		return empCodeList;
	}


	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}


	public String getClaimAmtList() {
		return claimAmtList;
	}


	public void setClaimAmtList(String claimAmtList) {
		this.claimAmtList = claimAmtList;
	}


	public String getBlockYearFromList() {
		return blockYearFromList;
	}


	public void setBlockYearFromList(String blockYearFromList) {
		this.blockYearFromList = blockYearFromList;
	}


	public String getBlockYearToList() {
		return blockYearToList;
	}


	public void setBlockYearToList(String blockYearToList) {
		this.blockYearToList = blockYearToList;
	}


	public String getYearOfVisitList() {
		return yearOfVisitList;
	}


	public void setYearOfVisitList(String yearOfVisitList) {
		this.yearOfVisitList = yearOfVisitList;
	}


	public String getIsTaxExemptedList() {
		return isTaxExemptedList;
	}


	public void setIsTaxExemptedList(String isTaxExemptedList) {
		this.isTaxExemptedList = isTaxExemptedList;
	}


	public String getLtaIdList() {
		return ltaIdList;
	}


	public void setLtaIdList(String ltaIdList) {
		this.ltaIdList = ltaIdList;
	}


	public String getYearOfExemptionTo() {
		return yearOfExemptionTo;
	}


	public void setYearOfExemptionTo(String yearOfExemptionTo) {
		this.yearOfExemptionTo = yearOfExemptionTo;
	}


	public String getExemptedAmt() {
		return exemptedAmt;
	}


	public void setExemptedAmt(String exemptedAmt) {
		this.exemptedAmt = exemptedAmt;
	}
}
