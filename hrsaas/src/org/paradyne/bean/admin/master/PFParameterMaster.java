/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 *
 */
public class PFParameterMaster extends BeanBase {
	
	private String pfCode;
	private String effDate;
	private String maxLimit;
	private String govLimit;
	private String deduction;
	private String pfType;
	private String empPF;
	private String empFamilyPF;
	private String pensionFund;
	private String compFund;
	private String pfFormula;
	private String debitCode;
	private String debitName;
	private String percentage;
    //Rules definition for epf
	private String deductCriteria="0";
	private String pfDedEmolument="0";
	private String pfEmoMaxLimit="0";
	private String noMaxLimitChk="N";
	private String totalRecords;
	private String makeList="false";
	
	private String pfadmincharge;
	private String pfedlicharge;
	private String  edlicontribution;

	private String myPage ;
	private String hiddencode;
	private String show;
	private String paraId;
	private String pfMinAmt;
	
	/*
	 * variables for PF Trust
	 * 
	 */
	private String effDatePFT;
	private String pfCodePFT;
	private String percOfDedPFT;
	private String maxLimitOFDed;
	private String debitCodePFT;
	private String debitNamePFT;
	private String interestRate;
	private String minLoanLimit;
	private String maxLoanLimit;
	private String loanTypeName;
	private String loanTypeCode;
	private String loanPurpose;
	private String loanPurposeCode;
	private String loanEligibility;
	ArrayList purposeList = null;
	/*
	 * end for PF trust
	 */
	
	/*
	 * For GPF
	 */
	private String pfCodeGPF;
	private String maxLimitOFDedGPF;
	private String debitCodeGPF;
	private String debitNameGPF;
	
	/*
	 * For VPF
	 */
	private String pfCodeVPF;
	private String maxLimitOFDedVPF;
	private String debitCodeVPF;
	private String debitNameVPF;
	private String vpfDedType;
	private String vpfDedTypeHidden;
	private String deductionFormVPF;
	
	
	/*
	 * Employee configuration for PF
	 * 
	 */
	private String allEmpEPF;
	private String allEmpGPF;
	private String allEmpVPF;
	private String allEmpPFT;
	private String allEmpPFNot;
	private String allEmpAppl;
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
	private String empListFlag="false";
	private String confEmp;
	private String nonConfEmp;
	ArrayList empList =null ;
	private String empId;
	private String empCode;
	private String empName;
	private String epfAppl;
	private String gpfAppl;
	private String vpfAppl;
	private String pftAppl;
	private String pfNotAppl;
	
	private String flag1="false";
	
	private String myPageEmpConf;
	private String noData="false";
	/*
	 * Organization level
	 */
	private String EPFflag;
	private String GPFflag;
	private String VPFflag;
	private String PFTflag;
	
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;
	private String reportType = "pdf";

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
public String getMyPage() {
	
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
    
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
	public String getPfFormula() {
		return pfFormula;
	}
	public void setPfFormula(String pfFormula) {
		this.pfFormula = pfFormula;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}
	public String getGovLimit() {
		return govLimit;
	}
	public void setGovLimit(String govLimit) {
		this.govLimit = govLimit;
	}
	public String getDeduction() {
		return deduction;
	}
	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}
	public String getPfType() {
		return pfType;
	}
	public void setPfType(String pfType) {
		this.pfType = pfType;
	}
	public String getEmpPF() {
		return empPF;
	}
	public void setEmpPF(String empPF) {
		this.empPF = empPF;
	}
	public String getEmpFamilyPF() {
		return empFamilyPF;
	}
	public void setEmpFamilyPF(String empFamilyPF) {
		this.empFamilyPF = empFamilyPF;
	}
	public String getPensionFund() {
		return pensionFund;
	}
	public void setPensionFund(String pensionFund) {
		this.pensionFund = pensionFund;
	}
	public String getCompFund() {
		return compFund;
	}
	public void setCompFund(String compFund) {
		this.compFund = compFund;
	}
	public String getPfCode() {
		return pfCode;
	}
	public void setPfCode(String pfCode) {
		this.pfCode = pfCode;
	}

	public String getPfadmincharge() {
		return pfadmincharge;
	}

	public void setPfadmincharge(String pfadmincharge) {
		this.pfadmincharge = pfadmincharge;
	}

	public String getPfedlicharge() {
		return pfedlicharge;
	}

	public void setPfedlicharge(String pfedlicharge) {
		this.pfedlicharge = pfedlicharge;
	}

	public String getEdlicontribution() {
		return edlicontribution;
	}

	public void setEdlicontribution(String edlicontribution) {
		this.edlicontribution = edlicontribution;
	}

	public String getDebitCodePFT() {
		return debitCodePFT;
	}

	public void setDebitCodePFT(String debitCodePFT) {
		this.debitCodePFT = debitCodePFT;
	}

	public String getDebitNamePFT() {
		return debitNamePFT;
	}

	public void setDebitNamePFT(String debitNamePFT) {
		this.debitNamePFT = debitNamePFT;
	}

	public String getMaxLimitOFDed() {
		return maxLimitOFDed;
	}

	public void setMaxLimitOFDed(String maxLimitOFDed) {
		this.maxLimitOFDed = maxLimitOFDed;
	}

	public String getMinLoanLimit() {
		return minLoanLimit;
	}

	public void setMinLoanLimit(String minLoanLimit) {
		this.minLoanLimit = minLoanLimit;
	}

	public String getMaxLoanLimit() {
		return maxLoanLimit;
	}

	public void setMaxLoanLimit(String maxLoanLimit) {
		this.maxLoanLimit = maxLoanLimit;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public String getLoanTypeCode() {
		return loanTypeCode;
	}

	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}

	public String getMaxLimitOFDedGPF() {
		return maxLimitOFDedGPF;
	}

	public void setMaxLimitOFDedGPF(String maxLimitOFDedGPF) {
		this.maxLimitOFDedGPF = maxLimitOFDedGPF;
	}

	public String getDebitCodeGPF() {
		return debitCodeGPF;
	}

	public void setDebitCodeGPF(String debitCodeGPF) {
		this.debitCodeGPF = debitCodeGPF;
	}

	public String getDebitNameGPF() {
		return debitNameGPF;
	}

	public void setDebitNameGPF(String debitNameGPF) {
		this.debitNameGPF = debitNameGPF;
	}

	public String getMaxLimitOFDedVPF() {
		return maxLimitOFDedVPF;
	}

	public void setMaxLimitOFDedVPF(String maxLimitOFDedVPF) {
		this.maxLimitOFDedVPF = maxLimitOFDedVPF;
	}

	public String getDebitCodeVPF() {
		return debitCodeVPF;
	}

	public void setDebitCodeVPF(String debitCodeVPF) {
		this.debitCodeVPF = debitCodeVPF;
	}

	public String getDebitNameVPF() {
		return debitNameVPF;
	}

	public void setDebitNameVPF(String debitNameVPF) {
		this.debitNameVPF = debitNameVPF;
	}

	public String getEPFflag() {
		return EPFflag;
	}

	public void setEPFflag(String fflag) {
		EPFflag = fflag;
	}

	public String getGPFflag() {
		return GPFflag;
	}

	public void setGPFflag(String fflag) {
		GPFflag = fflag;
	}

	public String getVPFflag() {
		return VPFflag;
	}

	public void setVPFflag(String fflag) {
		VPFflag = fflag;
	}

	public String getPFTflag() {
		return PFTflag;
	}

	public void setPFTflag(String tflag) {
		PFTflag = tflag;
	}

	public String getPfCodePFT() {
		return pfCodePFT;
	}

	public void setPfCodePFT(String pfCodePFT) {
		this.pfCodePFT = pfCodePFT;
	}

	public String getPercOfDedPFT() {
		return percOfDedPFT;
	}

	public void setPercOfDedPFT(String percOfDedPFT) {
		this.percOfDedPFT = percOfDedPFT;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanEligibility() {
		return loanEligibility;
	}

	public void setLoanEligibility(String loanEligibility) {
		this.loanEligibility = loanEligibility;
	}

	public String getLoanPurposeCode() {
		return loanPurposeCode;
	}

	public void setLoanPurposeCode(String loanPurposeCode) {
		this.loanPurposeCode = loanPurposeCode;
	}

	public ArrayList getPurposeList() {
		return purposeList;
	}

	public void setPurposeList(ArrayList purposeList) {
		this.purposeList = purposeList;
	}

	public String getPfCodeGPF() {
		return pfCodeGPF;
	}

	public void setPfCodeGPF(String pfCodeGPF) {
		this.pfCodeGPF = pfCodeGPF;
	}

	public String getPfCodeVPF() {
		return pfCodeVPF;
	}

	public void setPfCodeVPF(String pfCodeVPF) {
		this.pfCodeVPF = pfCodeVPF;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getAllEmpAppl() {
		return allEmpAppl;
	}

	public void setAllEmpAppl(String allEmpAppl) {
		this.allEmpAppl = allEmpAppl;
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

	public ArrayList getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getEpfAppl() {
		return epfAppl;
	}

	public void setEpfAppl(String epfAppl) {
		this.epfAppl = epfAppl;
	}

	public String getGpfAppl() {
		return gpfAppl;
	}

	public void setGpfAppl(String gpfAppl) {
		this.gpfAppl = gpfAppl;
	}

	public String getVpfAppl() {
		return vpfAppl;
	}

	public void setVpfAppl(String vpfAppl) {
		this.vpfAppl = vpfAppl;
	}

	public String getPftAppl() {
		return pftAppl;
	}

	public void setPftAppl(String pftAppl) {
		this.pftAppl = pftAppl;
	}

	public String getPfNotAppl() {
		return pfNotAppl;
	}

	public void setPfNotAppl(String pfNotAppl) {
		this.pfNotAppl = pfNotAppl;
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

	public String getEffDatePFT() {
		return effDatePFT;
	}

	public void setEffDatePFT(String effDatePFT) {
		this.effDatePFT = effDatePFT;
	}

	public String getEmpListFlag() {
		return empListFlag;
	}

	public void setEmpListFlag(String empListFlag) {
		this.empListFlag = empListFlag;
	}

	public String getConfEmp() {
		return confEmp;
	}

	public void setConfEmp(String confEmp) {
		this.confEmp = confEmp;
	}

	public String getNonConfEmp() {
		return nonConfEmp;
	}

	public void setNonConfEmp(String nonConfEmp) {
		this.nonConfEmp = nonConfEmp;
	}

	public String getPfMinAmt() {
		return pfMinAmt;
	}

	public void setPfMinAmt(String pfMinAmt) {
		this.pfMinAmt = pfMinAmt;
	}

	public String getAllEmpEPF() {
		return allEmpEPF;
	}

	public void setAllEmpEPF(String allEmpEPF) {
		this.allEmpEPF = allEmpEPF;
	}

	public String getAllEmpGPF() {
		return allEmpGPF;
	}

	public void setAllEmpGPF(String allEmpGPF) {
		this.allEmpGPF = allEmpGPF;
	}

	public String getAllEmpVPF() {
		return allEmpVPF;
	}

	public void setAllEmpVPF(String allEmpVPF) {
		this.allEmpVPF = allEmpVPF;
	}

	public String getAllEmpPFT() {
		return allEmpPFT;
	}

	public void setAllEmpPFT(String allEmpPFT) {
		this.allEmpPFT = allEmpPFT;
	}

	public String getAllEmpPFNot() {
		return allEmpPFNot;
	}

	public void setAllEmpPFNot(String allEmpPFNot) {
		this.allEmpPFNot = allEmpPFNot;
	}

	public String getDeductCriteria() {
		return deductCriteria;
	}

	public void setDeductCriteria(String deductCriteria) {
		this.deductCriteria = deductCriteria;
	}

	public String getPfDedEmolument() {
		return pfDedEmolument;
	}

	public void setPfDedEmolument(String pfDedEmolument) {
		this.pfDedEmolument = pfDedEmolument;
	}

	public String getPfEmoMaxLimit() {
		return pfEmoMaxLimit;
	}

	public void setPfEmoMaxLimit(String pfEmoMaxLimit) {
		this.pfEmoMaxLimit = pfEmoMaxLimit;
	}

	public String getNoMaxLimitChk() {
		return noMaxLimitChk;
	}

	public void setNoMaxLimitChk(String noMaxLimitChk) {
		this.noMaxLimitChk = noMaxLimitChk;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMakeList() {
		return makeList;
	}

	public void setMakeList(String makeList) {
		this.makeList = makeList;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getVpfDedType() {
		return vpfDedType;
	}

	public void setVpfDedType(String vpfDedType) {
		this.vpfDedType = vpfDedType;
	}

	public String getDeductionFormVPF() {
		return deductionFormVPF;
	}

	public void setDeductionFormVPF(String deductionFormVPF) {
		this.deductionFormVPF = deductionFormVPF;
	}

	public String getVpfDedTypeHidden() {
		return vpfDedTypeHidden;
	}

	public void setVpfDedTypeHidden(String vpfDedTypeHidden) {
		this.vpfDedTypeHidden = vpfDedTypeHidden;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	

}
