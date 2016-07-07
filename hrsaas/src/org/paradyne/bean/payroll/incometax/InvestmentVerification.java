package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class InvestmentVerification extends BeanBase {
	
	//Used in settlement process form
	private Boolean paginationFlag=false;
	private boolean settlementFlag = false;
	private String settCode="";
	private String settlementApplnStatus="";
	private String status="";
	
	private String navigateTo="";
	
	private String empId="";
	private String empName="";
	private String empToken="";
	private String divId;
	private String divName="";
	private String branchId;
	private String branchName="";
	private String deptId;
	private String deptName="";
	private String paybillId="";
	private String paybillName="";
	
	private Boolean nodata=false;
	private String toYear;
	private String fromYear;
	private String listType="";
	private String investmentCode="";
	private String investmentName="";
	//private String investmentGroup="";
	
	private Boolean empListFlag=false;
	private Boolean monthlyFlag=false;
	private Boolean empSaveFlag=false;
	private Boolean proofAttachedFlag=false;
	
	ArrayList investverifylist;
	
	// all fields for  List
	private String LempName;
	private String LinvestName;
	private String LinvSectionName;
	private String LchapterName;
	private String LinvAmount;
	private String LviewDoc;
	private String Lverified;
	private String LVerifyAmt;
	private String Lproofatteched;
	private String totalDeclaredInvestments;
	private String totalVerifiedInvestments;
	
	private String hiddenMypage="";
	
	private String myPage="";
	private String show="";
	private String selectname;
	private String hidChkBox="";
	
	private String InvestId;
	private String Estatus;
	private String filterflag;
	
	private String attchViewflag;
	private String invEmpId;
	
	//Added by Prashant
	
	private String dataPath="";
	
	private String monthlyRentCodeItt = "";
	private String monthlyEmployeeIdItt = "";
	private String monthlyEmployeeNameItt = "";
	private String investmentMonthItt = "";
	private String investmentMonthNameItt = "";
	private String monthlyRentPaidDeclaredItt = "";
	private String monthlyRentPaidVerifiedItt = "";
	private String monthlyIsMetroDeclaredItt = "";
	private String monthlyIsMetroVerifiedItt = "";
	private String monthlyCompanyOwnedHouseDeclaredItt = "";
	private String monthlyCompanyOwnedHouseVerifiedItt = "";
	private String monthlyCityPopulationDeclaredItt="";
	private String monthlyCityPopulationVerifiedItt="";
	private String monthlyCarUsedDeclaredItt = "";
	private String monthlyCarUsedVerifiedItt = "";
	private String monthlyCubicCapacityDeclaredItt = "";
	private String monthlyCubicCapacityVerifiedItt = "";
	private String monthlyDriverUsedDeclaredItt = "";
	private String monthlyDriverUsedVerifiedItt = "";
	private String monthlyHouseRentPaidByCompanyDeclaredItt = "";
	private String monthlyHouseRentPaidByCompanyVerifiedItt = "";
	private String monthlyInIndiaDeclaredItt = "";
	private String monthlyInIndiaVerifiedItt = "";
	private String monthlyAttachmentFileItt = "";
	ArrayList monthlyInvestmentList;
	ArrayList monthlyAttachmentList;
	
	
	private String checkOne="";
	private String checkTwo="";
	private String checkThree="";
	private String checkFour="";
	private String checkFive="";
	private String checkSix="";
	private String checkSeven="";
	private String hdeleteCode="";
	
	/*variables for Exemptions under section 10 & 17*/
	private String exemptionsEmpIdItt;
	private String exemptionsEmpNameItt;
	private String exemptionsInvestNameItt;
	private String exemptionsInvSectionNameItt;
	private String exemptionsChapterNameItt;
	private String exemptionsInvAmountItt;
	private String exemptionsViewDocItt;
	private String exemptionsVerifiedItt;
	private String exemptionsVerifiedAmountItt;
	private String exemptionsProofAttachedItt;
	private String exemptionsInvestmentIdItt;
	private Boolean exemptionsAttachViewFlagItt=false;
	ArrayList exemptionsInvestmentList;
	ArrayList exemptionsAttachmentList;
	private Boolean exemptionsListViewFlag=false;
	
	/*variables for Other income*/
	private String otherEmpIdItt;
	private String otherEmpNameItt;
	private String otherInvestNameItt;
	private String otherInvSectionNameItt;
	private String otherChapterNameItt;
	private String otherInvAmountItt;
	private String otherViewDocItt;
	private String otherVerifiedItt;
	private String otherVerifiedAmountItt;
	private String otherProofAttachedItt;
	private String otherInvestmentIdItt;
	private Boolean otherAttachViewFlagItt=false;
	ArrayList otherInvestmentList;
	ArrayList otherAttachmentList;
	private Boolean otherListViewFlag=false;
	
	/*variables for Deductions under Chapter VI-A*/
	private String deductionsVIAEmpIdItt;
	private String deductionsVIAEmpNameItt;
	private String deductionsVIAInvestNameItt;
	private String deductionsVIAInvSectionNameItt;
	private String deductionsVIAChapterNameItt;
	private String deductionsVIAInvAmountItt;
	private String deductionsVIAViewDocItt;
	private String deductionsVIAVerifiedItt;
	private String deductionsVIAVerifiedAmountItt;
	private String deductionsVIAProofAttachedItt;
	private String deductionsVIAInvestmentIdItt;
	private Boolean deductionsVIAAttachViewFlagItt=false;
	ArrayList deductionsVIAInvestmentList;
	ArrayList deductionsVIAAttachmentList;
	private Boolean deductionsVIAListViewFlag=false;
	
	/*variables for Deductions under Chapter VI*/
	private String deductionsVIEmpIdItt;
	private String deductionsVIEmpNameItt;
	private String deductionsVIInvestNameItt;
	private String deductionsVIInvSectionNameItt;
	private String deductionsVIChapterNameItt;
	private String deductionsVIInvAmountItt;
	private String deductionsVIViewDocItt;
	private String deductionsVIVerifiedItt;
	private String deductionsVIVerifiedAmountItt;
	private String deductionsVIProofAttachedItt;
	private String deductionsVIInvestmentIdItt;
	private Boolean deductionsVIAttachViewFlagItt=false;
	ArrayList deductionsVIInvestmentList;
	ArrayList deductionsVIAttachmentList;
	private Boolean deductionsVIListViewFlag=false;
	
	/*variables for Donations*/
	private String donationEmpIdItt;
	private String donationEmpNameItt;
	private String donationNameItt;
	private String donationExemptionPercentageItt;
	private String donationInvAmountItt;
	private String donationViewDocItt;
	private String donationVerifiedItt;
	private String donationVerifiedAmountItt;
	private String donationProofAttachedItt;
	private String donationIdItt;
	private Boolean donationAttachViewFlagItt=false;
	ArrayList donationInvestmentList;
	ArrayList donationAttachmentList;
	private Boolean donationListViewFlag=false;
	
	
	public String getInvEmpId() {
		return invEmpId;
	}

	public void setInvEmpId(String invEmpId) {
		this.invEmpId = invEmpId;
	}

	public String getAttchViewflag() {
		return attchViewflag;
	}

	public void setAttchViewflag(String attchViewflag) {
		this.attchViewflag = attchViewflag;
	}

	public String getFilterflag() {
		return filterflag;
	}

	public void setFilterflag(String filterflag) {
		this.filterflag = filterflag;
	}

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}

	public String getHidChkBox() {
		return hidChkBox;
	}

	public void setHidChkBox(String hidChkBox) {
		this.hidChkBox = hidChkBox;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
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

	public ArrayList getInvestverifylist() {
		return investverifylist;
	}

	public void setInvestverifylist(ArrayList investverifylist) {
		this.investverifylist = investverifylist;
	}

	public String getLempName() {
		return LempName;
	}

	public void setLempName(String lempName) {
		LempName = lempName;
	}

	public String getLinvestName() {
		return LinvestName;
	}

	public void setLinvestName(String linvestName) {
		LinvestName = linvestName;
	}

	public String getLinvSectionName() {
		return LinvSectionName;
	}

	public void setLinvSectionName(String linvSectionName) {
		LinvSectionName = linvSectionName;
	}

	public String getLchapterName() {
		return LchapterName;
	}

	public void setLchapterName(String lchapterName) {
		LchapterName = lchapterName;
	}

	public String getLinvAmount() {
		return LinvAmount;
	}

	public void setLinvAmount(String linvAmount) {
		LinvAmount = linvAmount;
	}

	public String getLviewDoc() {
		return LviewDoc;
	}

	public void setLviewDoc(String lviewDoc) {
		LviewDoc = lviewDoc;
	}

	public String getLverified() {
		return Lverified;
	}

	public void setLverified(String lverified) {
		Lverified = lverified;
	}

	public String getLVerifyAmt() {
		return LVerifyAmt;
	}

	public void setLVerifyAmt(String verifyAmt) {
		LVerifyAmt = verifyAmt;
	}

	public String getInvestId() {
		return InvestId;
	}

	public void setInvestId(String investId) {
		InvestId = investId;
	}

	public Boolean getNodata() {
		return nodata;
	}

	public void setNodata(Boolean nodata) {
		this.nodata = nodata;
	}

	public String getLproofatteched() {
		return Lproofatteched;
	}

	public void setLproofatteched(String lproofatteched) {
		Lproofatteched = lproofatteched;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getHiddenMypage() {
		return hiddenMypage;
	}

	public void setHiddenMypage(String hiddenMypage) {
		this.hiddenMypage = hiddenMypage;
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

	public String getSelectname() {
		return selectname;
	}

	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	public String getInvestmentCode() {
		return investmentCode;
	}

	public void setInvestmentCode(String investmentCode) {
		this.investmentCode = investmentCode;
	}

	public String getInvestmentName() {
		return investmentName;
	}

	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}

	public Boolean getEmpListFlag() {
		return empListFlag;
	}

	public void setEmpListFlag(Boolean empListFlag) {
		this.empListFlag = empListFlag;
	}

	public Boolean getMonthlyFlag() {
		return monthlyFlag;
	}

	public void setMonthlyFlag(Boolean monthlyFlag) {
		this.monthlyFlag = monthlyFlag;
	}

	public ArrayList getMonthlyInvestmentList() {
		return monthlyInvestmentList;
	}

	public void setMonthlyInvestmentList(ArrayList monthlyInvestmentList) {
		this.monthlyInvestmentList = monthlyInvestmentList;
	}

	public String getInvestmentMonthItt() {
		return investmentMonthItt;
	}

	public void setInvestmentMonthItt(String investmentMonthItt) {
		this.investmentMonthItt = investmentMonthItt;
	}

	public String getMonthlyEmployeeIdItt() {
		return monthlyEmployeeIdItt;
	}

	public void setMonthlyEmployeeIdItt(String monthlyEmployeeIdItt) {
		this.monthlyEmployeeIdItt = monthlyEmployeeIdItt;
	}

	public String getMonthlyEmployeeNameItt() {
		return monthlyEmployeeNameItt;
	}

	public void setMonthlyEmployeeNameItt(String monthlyEmployeeNameItt) {
		this.monthlyEmployeeNameItt = monthlyEmployeeNameItt;
	}

	public String getMonthlyRentCodeItt() {
		return monthlyRentCodeItt;
	}

	public void setMonthlyRentCodeItt(String monthlyRentCodeItt) {
		this.monthlyRentCodeItt = monthlyRentCodeItt;
	}

	public String getMonthlyRentPaidDeclaredItt() {
		return monthlyRentPaidDeclaredItt;
	}

	public void setMonthlyRentPaidDeclaredItt(String monthlyRentPaidDeclaredItt) {
		this.monthlyRentPaidDeclaredItt = monthlyRentPaidDeclaredItt;
	}

	public String getMonthlyRentPaidVerifiedItt() {
		return monthlyRentPaidVerifiedItt;
	}

	public void setMonthlyRentPaidVerifiedItt(String monthlyRentPaidVerifiedItt) {
		this.monthlyRentPaidVerifiedItt = monthlyRentPaidVerifiedItt;
	}

	public String getMonthlyIsMetroVerifiedItt() {
		return monthlyIsMetroVerifiedItt;
	}

	public void setMonthlyIsMetroVerifiedItt(String monthlyIsMetroVerifiedItt) {
		this.monthlyIsMetroVerifiedItt = monthlyIsMetroVerifiedItt;
	}

	public String getMonthlyIsMetroDeclaredItt() {
		return monthlyIsMetroDeclaredItt;
	}

	public void setMonthlyIsMetroDeclaredItt(String monthlyIsMetroDeclaredItt) {
		this.monthlyIsMetroDeclaredItt = monthlyIsMetroDeclaredItt;
	}

	public String getMonthlyCompanyOwnedHouseVerifiedItt() {
		return monthlyCompanyOwnedHouseVerifiedItt;
	}

	public void setMonthlyCompanyOwnedHouseVerifiedItt(
			String monthlyCompanyOwnedHouseVerifiedItt) {
		this.monthlyCompanyOwnedHouseVerifiedItt = monthlyCompanyOwnedHouseVerifiedItt;
	}

	public String getMonthlyCompanyOwnedHouseDeclaredItt() {
		return monthlyCompanyOwnedHouseDeclaredItt;
	}

	public void setMonthlyCompanyOwnedHouseDeclaredItt(
			String monthlyCompanyOwnedHouseDeclaredItt) {
		this.monthlyCompanyOwnedHouseDeclaredItt = monthlyCompanyOwnedHouseDeclaredItt;
	}

	public String getMonthlyCarUsedVerifiedItt() {
		return monthlyCarUsedVerifiedItt;
	}

	public void setMonthlyCarUsedVerifiedItt(String monthlyCarUsedVerifiedItt) {
		this.monthlyCarUsedVerifiedItt = monthlyCarUsedVerifiedItt;
	}

	public String getMonthlyCarUsedDeclaredItt() {
		return monthlyCarUsedDeclaredItt;
	}

	public void setMonthlyCarUsedDeclaredItt(String monthlyCarUsedDeclaredItt) {
		this.monthlyCarUsedDeclaredItt = monthlyCarUsedDeclaredItt;
	}

	public String getMonthlyCubicCapacityVerifiedItt() {
		return monthlyCubicCapacityVerifiedItt;
	}

	public void setMonthlyCubicCapacityVerifiedItt(
			String monthlyCubicCapacityVerifiedItt) {
		this.monthlyCubicCapacityVerifiedItt = monthlyCubicCapacityVerifiedItt;
	}

	public String getMonthlyCubicCapacityDeclaredItt() {
		return monthlyCubicCapacityDeclaredItt;
	}

	public void setMonthlyCubicCapacityDeclaredItt(
			String monthlyCubicCapacityDeclaredItt) {
		this.monthlyCubicCapacityDeclaredItt = monthlyCubicCapacityDeclaredItt;
	}

	public String getMonthlyDriverUsedVerifiedItt() {
		return monthlyDriverUsedVerifiedItt;
	}

	public void setMonthlyDriverUsedVerifiedItt(String monthlyDriverUsedVerifiedItt) {
		this.monthlyDriverUsedVerifiedItt = monthlyDriverUsedVerifiedItt;
	}

	public String getMonthlyDriverUsedDeclaredItt() {
		return monthlyDriverUsedDeclaredItt;
	}

	public void setMonthlyDriverUsedDeclaredItt(String monthlyDriverUsedDeclaredItt) {
		this.monthlyDriverUsedDeclaredItt = monthlyDriverUsedDeclaredItt;
	}

	public String getMonthlyHouseRentPaidByCompanyVerifiedItt() {
		return monthlyHouseRentPaidByCompanyVerifiedItt;
	}

	public void setMonthlyHouseRentPaidByCompanyVerifiedItt(
			String monthlyHouseRentPaidByCompanyVerifiedItt) {
		this.monthlyHouseRentPaidByCompanyVerifiedItt = monthlyHouseRentPaidByCompanyVerifiedItt;
	}

	public String getMonthlyHouseRentPaidByCompanyDeclaredItt() {
		return monthlyHouseRentPaidByCompanyDeclaredItt;
	}

	public void setMonthlyHouseRentPaidByCompanyDeclaredItt(
			String monthlyHouseRentPaidByCompanyDeclaredItt) {
		this.monthlyHouseRentPaidByCompanyDeclaredItt = monthlyHouseRentPaidByCompanyDeclaredItt;
	}

	public String getMonthlyInIndiaVerifiedItt() {
		return monthlyInIndiaVerifiedItt;
	}

	public void setMonthlyInIndiaVerifiedItt(String monthlyInIndiaVerifiedItt) {
		this.monthlyInIndiaVerifiedItt = monthlyInIndiaVerifiedItt;
	}

	public String getMonthlyInIndiaDeclaredItt() {
		return monthlyInIndiaDeclaredItt;
	}

	public void setMonthlyInIndiaDeclaredItt(String monthlyInIndiaDeclaredItt) {
		this.monthlyInIndiaDeclaredItt = monthlyInIndiaDeclaredItt;
	}

	public String getInvestmentMonthNameItt() {
		return investmentMonthNameItt;
	}

	public void setInvestmentMonthNameItt(String investmentMonthNameItt) {
		this.investmentMonthNameItt = investmentMonthNameItt;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getMonthlyAttachmentFileItt() {
		return monthlyAttachmentFileItt;
	}

	public void setMonthlyAttachmentFileItt(String monthlyAttachmentFileItt) {
		this.monthlyAttachmentFileItt = monthlyAttachmentFileItt;
	}

	public String getCheckOne() {
		return checkOne;
	}

	public void setCheckOne(String checkOne) {
		this.checkOne = checkOne;
	}

	public String getCheckTwo() {
		return checkTwo;
	}

	public void setCheckTwo(String checkTwo) {
		this.checkTwo = checkTwo;
	}

	public String getCheckThree() {
		return checkThree;
	}

	public void setCheckThree(String checkThree) {
		this.checkThree = checkThree;
	}

	public String getCheckFour() {
		return checkFour;
	}

	public void setCheckFour(String checkFour) {
		this.checkFour = checkFour;
	}

	public String getCheckFive() {
		return checkFive;
	}

	public void setCheckFive(String checkFive) {
		this.checkFive = checkFive;
	}

	public String getCheckSix() {
		return checkSix;
	}

	public void setCheckSix(String checkSix) {
		this.checkSix = checkSix;
	}

	public String getCheckSeven() {
		return checkSeven;
	}

	public void setCheckSeven(String checkSeven) {
		this.checkSeven = checkSeven;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getTotalDeclaredInvestments() {
		return totalDeclaredInvestments;
	}

	public void setTotalDeclaredInvestments(String totalDeclaredInvestments) {
		this.totalDeclaredInvestments = totalDeclaredInvestments;
	}

	public String getTotalVerifiedInvestments() {
		return totalVerifiedInvestments;
	}

	public void setTotalVerifiedInvestments(String totalVerifiedInvestments) {
		this.totalVerifiedInvestments = totalVerifiedInvestments;
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

	public boolean isSettlementFlag() {
		return settlementFlag;
	}
	public void setSettlementFlag(boolean settlementFlag) {
		this.settlementFlag = settlementFlag;
	}

	public String getSettCode() {
		return settCode;
	}

	public void setSettCode(String settCode) {
		this.settCode = settCode;
	}

	public String getExemptionsEmpNameItt() {
		return exemptionsEmpNameItt;
	}

	public void setExemptionsEmpNameItt(String exemptionsEmpNameItt) {
		this.exemptionsEmpNameItt = exemptionsEmpNameItt;
	}

	public String getExemptionsInvestNameItt() {
		return exemptionsInvestNameItt;
	}

	public void setExemptionsInvestNameItt(String exemptionsInvestNameItt) {
		this.exemptionsInvestNameItt = exemptionsInvestNameItt;
	}

	public String getExemptionsInvSectionNameItt() {
		return exemptionsInvSectionNameItt;
	}

	public void setExemptionsInvSectionNameItt(String exemptionsInvSectionNameItt) {
		this.exemptionsInvSectionNameItt = exemptionsInvSectionNameItt;
	}

	public String getExemptionsChapterNameItt() {
		return exemptionsChapterNameItt;
	}

	public void setExemptionsChapterNameItt(String exemptionsChapterNameItt) {
		this.exemptionsChapterNameItt = exemptionsChapterNameItt;
	}

	public String getExemptionsInvAmountItt() {
		return exemptionsInvAmountItt;
	}

	public void setExemptionsInvAmountItt(String exemptionsInvAmountItt) {
		this.exemptionsInvAmountItt = exemptionsInvAmountItt;
	}

	public String getExemptionsViewDocItt() {
		return exemptionsViewDocItt;
	}

	public void setExemptionsViewDocItt(String exemptionsViewDocItt) {
		this.exemptionsViewDocItt = exemptionsViewDocItt;
	}

	public String getExemptionsVerifiedItt() {
		return exemptionsVerifiedItt;
	}

	public void setExemptionsVerifiedItt(String exemptionsVerifiedItt) {
		this.exemptionsVerifiedItt = exemptionsVerifiedItt;
	}

	public String getExemptionsVerifiedAmountItt() {
		return exemptionsVerifiedAmountItt;
	}

	public void setExemptionsVerifiedAmountItt(String exemptionsVerifiedAmountItt) {
		this.exemptionsVerifiedAmountItt = exemptionsVerifiedAmountItt;
	}

	public String getExemptionsProofAttachedItt() {
		return exemptionsProofAttachedItt;
	}

	public void setExemptionsProofAttachedItt(String exemptionsProofAttachedItt) {
		this.exemptionsProofAttachedItt = exemptionsProofAttachedItt;
	}

	public Boolean getExemptionsAttachViewFlagItt() {
		return exemptionsAttachViewFlagItt;
	}

	public void setExemptionsAttachViewFlagItt(Boolean exemptionsAttachViewFlagItt) {
		this.exemptionsAttachViewFlagItt = exemptionsAttachViewFlagItt;
	}

	public ArrayList getExemptionsInvestmentList() {
		return exemptionsInvestmentList;
	}

	public void setExemptionsInvestmentList(ArrayList exemptionsInvestmentList) {
		this.exemptionsInvestmentList = exemptionsInvestmentList;
	}

	public String getOtherEmpNameItt() {
		return otherEmpNameItt;
	}

	public void setOtherEmpNameItt(String otherEmpNameItt) {
		this.otherEmpNameItt = otherEmpNameItt;
	}

	public String getOtherInvestNameItt() {
		return otherInvestNameItt;
	}

	public void setOtherInvestNameItt(String otherInvestNameItt) {
		this.otherInvestNameItt = otherInvestNameItt;
	}

	public String getOtherInvSectionNameItt() {
		return otherInvSectionNameItt;
	}

	public void setOtherInvSectionNameItt(String otherInvSectionNameItt) {
		this.otherInvSectionNameItt = otherInvSectionNameItt;
	}

	public String getOtherChapterNameItt() {
		return otherChapterNameItt;
	}

	public void setOtherChapterNameItt(String otherChapterNameItt) {
		this.otherChapterNameItt = otherChapterNameItt;
	}

	public String getOtherInvAmountItt() {
		return otherInvAmountItt;
	}

	public void setOtherInvAmountItt(String otherInvAmountItt) {
		this.otherInvAmountItt = otherInvAmountItt;
	}

	public String getOtherViewDocItt() {
		return otherViewDocItt;
	}

	public void setOtherViewDocItt(String otherViewDocItt) {
		this.otherViewDocItt = otherViewDocItt;
	}

	public String getOtherVerifiedItt() {
		return otherVerifiedItt;
	}

	public void setOtherVerifiedItt(String otherVerifiedItt) {
		this.otherVerifiedItt = otherVerifiedItt;
	}

	public String getOtherVerifiedAmountItt() {
		return otherVerifiedAmountItt;
	}

	public void setOtherVerifiedAmountItt(String otherVerifiedAmountItt) {
		this.otherVerifiedAmountItt = otherVerifiedAmountItt;
	}

	public String getOtherProofAttachedItt() {
		return otherProofAttachedItt;
	}

	public void setOtherProofAttachedItt(String otherProofAttachedItt) {
		this.otherProofAttachedItt = otherProofAttachedItt;
	}

	public Boolean getOtherAttachViewFlagItt() {
		return otherAttachViewFlagItt;
	}

	public void setOtherAttachViewFlagItt(Boolean otherAttachViewFlagItt) {
		this.otherAttachViewFlagItt = otherAttachViewFlagItt;
	}

	public ArrayList getOtherInvestmentList() {
		return otherInvestmentList;
	}

	public void setOtherInvestmentList(ArrayList otherInvestmentList) {
		this.otherInvestmentList = otherInvestmentList;
	}

	public String getDeductionsVIAEmpNameItt() {
		return deductionsVIAEmpNameItt;
	}

	public void setDeductionsVIAEmpNameItt(String deductionsVIAEmpNameItt) {
		this.deductionsVIAEmpNameItt = deductionsVIAEmpNameItt;
	}

	public String getDeductionsVIAInvestNameItt() {
		return deductionsVIAInvestNameItt;
	}

	public void setDeductionsVIAInvestNameItt(String deductionsVIAInvestNameItt) {
		this.deductionsVIAInvestNameItt = deductionsVIAInvestNameItt;
	}

	public String getDeductionsVIAInvSectionNameItt() {
		return deductionsVIAInvSectionNameItt;
	}

	public void setDeductionsVIAInvSectionNameItt(
			String deductionsVIAInvSectionNameItt) {
		this.deductionsVIAInvSectionNameItt = deductionsVIAInvSectionNameItt;
	}

	public String getDeductionsVIAChapterNameItt() {
		return deductionsVIAChapterNameItt;
	}

	public void setDeductionsVIAChapterNameItt(String deductionsVIAChapterNameItt) {
		this.deductionsVIAChapterNameItt = deductionsVIAChapterNameItt;
	}

	public String getDeductionsVIAInvAmountItt() {
		return deductionsVIAInvAmountItt;
	}

	public void setDeductionsVIAInvAmountItt(String deductionsVIAInvAmountItt) {
		this.deductionsVIAInvAmountItt = deductionsVIAInvAmountItt;
	}

	public String getDeductionsVIAViewDocItt() {
		return deductionsVIAViewDocItt;
	}

	public void setDeductionsVIAViewDocItt(String deductionsVIAViewDocItt) {
		this.deductionsVIAViewDocItt = deductionsVIAViewDocItt;
	}

	public String getDeductionsVIAVerifiedItt() {
		return deductionsVIAVerifiedItt;
	}

	public void setDeductionsVIAVerifiedItt(String deductionsVIAVerifiedItt) {
		this.deductionsVIAVerifiedItt = deductionsVIAVerifiedItt;
	}

	public String getDeductionsVIAVerifiedAmountItt() {
		return deductionsVIAVerifiedAmountItt;
	}

	public void setDeductionsVIAVerifiedAmountItt(
			String deductionsVIAVerifiedAmountItt) {
		this.deductionsVIAVerifiedAmountItt = deductionsVIAVerifiedAmountItt;
	}

	public String getDeductionsVIAProofAttachedItt() {
		return deductionsVIAProofAttachedItt;
	}

	public void setDeductionsVIAProofAttachedItt(
			String deductionsVIAProofAttachedItt) {
		this.deductionsVIAProofAttachedItt = deductionsVIAProofAttachedItt;
	}

	public Boolean getDeductionsVIAAttachViewFlagItt() {
		return deductionsVIAAttachViewFlagItt;
	}

	public void setDeductionsVIAAttachViewFlagItt(
			Boolean deductionsVIAAttachViewFlagItt) {
		this.deductionsVIAAttachViewFlagItt = deductionsVIAAttachViewFlagItt;
	}

	public ArrayList getDeductionsVIAInvestmentList() {
		return deductionsVIAInvestmentList;
	}

	public void setDeductionsVIAInvestmentList(ArrayList deductionsVIAInvestmentList) {
		this.deductionsVIAInvestmentList = deductionsVIAInvestmentList;
	}

	public String getDeductionsVIEmpNameItt() {
		return deductionsVIEmpNameItt;
	}

	public void setDeductionsVIEmpNameItt(String deductionsVIEmpNameItt) {
		this.deductionsVIEmpNameItt = deductionsVIEmpNameItt;
	}

	public String getDeductionsVIInvestNameItt() {
		return deductionsVIInvestNameItt;
	}

	public void setDeductionsVIInvestNameItt(String deductionsVIInvestNameItt) {
		this.deductionsVIInvestNameItt = deductionsVIInvestNameItt;
	}

	public String getDeductionsVIInvSectionNameItt() {
		return deductionsVIInvSectionNameItt;
	}

	public void setDeductionsVIInvSectionNameItt(
			String deductionsVIInvSectionNameItt) {
		this.deductionsVIInvSectionNameItt = deductionsVIInvSectionNameItt;
	}

	public String getDeductionsVIChapterNameItt() {
		return deductionsVIChapterNameItt;
	}

	public void setDeductionsVIChapterNameItt(String deductionsVIChapterNameItt) {
		this.deductionsVIChapterNameItt = deductionsVIChapterNameItt;
	}

	public String getDeductionsVIInvAmountItt() {
		return deductionsVIInvAmountItt;
	}

	public void setDeductionsVIInvAmountItt(String deductionsVIInvAmountItt) {
		this.deductionsVIInvAmountItt = deductionsVIInvAmountItt;
	}

	public String getDeductionsVIViewDocItt() {
		return deductionsVIViewDocItt;
	}

	public void setDeductionsVIViewDocItt(String deductionsVIViewDocItt) {
		this.deductionsVIViewDocItt = deductionsVIViewDocItt;
	}

	public String getDeductionsVIVerifiedItt() {
		return deductionsVIVerifiedItt;
	}

	public void setDeductionsVIVerifiedItt(String deductionsVIVerifiedItt) {
		this.deductionsVIVerifiedItt = deductionsVIVerifiedItt;
	}

	public String getDeductionsVIVerifiedAmountItt() {
		return deductionsVIVerifiedAmountItt;
	}

	public void setDeductionsVIVerifiedAmountItt(
			String deductionsVIVerifiedAmountItt) {
		this.deductionsVIVerifiedAmountItt = deductionsVIVerifiedAmountItt;
	}

	public String getDeductionsVIProofAttachedItt() {
		return deductionsVIProofAttachedItt;
	}

	public void setDeductionsVIProofAttachedItt(String deductionsVIProofAttachedItt) {
		this.deductionsVIProofAttachedItt = deductionsVIProofAttachedItt;
	}

	public Boolean getDeductionsVIAttachViewFlagItt() {
		return deductionsVIAttachViewFlagItt;
	}

	public void setDeductionsVIAttachViewFlagItt(
			Boolean deductionsVIAttachViewFlagItt) {
		this.deductionsVIAttachViewFlagItt = deductionsVIAttachViewFlagItt;
	}

	public ArrayList getDeductionsVIInvestmentList() {
		return deductionsVIInvestmentList;
	}

	public void setDeductionsVIInvestmentList(ArrayList deductionsVIInvestmentList) {
		this.deductionsVIInvestmentList = deductionsVIInvestmentList;
	}

	public Boolean getExemptionsListViewFlag() {
		return exemptionsListViewFlag;
	}

	public void setExemptionsListViewFlag(Boolean exemptionsListViewFlag) {
		this.exemptionsListViewFlag = exemptionsListViewFlag;
	}

	public Boolean getOtherListViewFlag() {
		return otherListViewFlag;
	}

	public void setOtherListViewFlag(Boolean otherListViewFlag) {
		this.otherListViewFlag = otherListViewFlag;
	}

	public Boolean getDeductionsVIAListViewFlag() {
		return deductionsVIAListViewFlag;
	}

	public void setDeductionsVIAListViewFlag(Boolean deductionsVIAListViewFlag) {
		this.deductionsVIAListViewFlag = deductionsVIAListViewFlag;
	}

	public Boolean getDeductionsVIListViewFlag() {
		return deductionsVIListViewFlag;
	}

	public void setDeductionsVIListViewFlag(Boolean deductionsVIListViewFlag) {
		this.deductionsVIListViewFlag = deductionsVIListViewFlag;
	}

	public String getExemptionsEmpIdItt() {
		return exemptionsEmpIdItt;
	}

	public void setExemptionsEmpIdItt(String exemptionsEmpIdItt) {
		this.exemptionsEmpIdItt = exemptionsEmpIdItt;
	}

	public String getExemptionsInvestmentIdItt() {
		return exemptionsInvestmentIdItt;
	}

	public void setExemptionsInvestmentIdItt(String exemptionsInvestmentIdItt) {
		this.exemptionsInvestmentIdItt = exemptionsInvestmentIdItt;
	}

	public String getOtherEmpIdItt() {
		return otherEmpIdItt;
	}

	public void setOtherEmpIdItt(String otherEmpIdItt) {
		this.otherEmpIdItt = otherEmpIdItt;
	}

	public String getOtherInvestmentIdItt() {
		return otherInvestmentIdItt;
	}

	public void setOtherInvestmentIdItt(String otherInvestmentIdItt) {
		this.otherInvestmentIdItt = otherInvestmentIdItt;
	}

	public String getDeductionsVIAEmpIdItt() {
		return deductionsVIAEmpIdItt;
	}

	public void setDeductionsVIAEmpIdItt(String deductionsVIAEmpIdItt) {
		this.deductionsVIAEmpIdItt = deductionsVIAEmpIdItt;
	}

	public String getDeductionsVIAInvestmentIdItt() {
		return deductionsVIAInvestmentIdItt;
	}

	public void setDeductionsVIAInvestmentIdItt(String deductionsVIAInvestmentIdItt) {
		this.deductionsVIAInvestmentIdItt = deductionsVIAInvestmentIdItt;
	}

	public String getDeductionsVIEmpIdItt() {
		return deductionsVIEmpIdItt;
	}

	public void setDeductionsVIEmpIdItt(String deductionsVIEmpIdItt) {
		this.deductionsVIEmpIdItt = deductionsVIEmpIdItt;
	}

	public String getDeductionsVIInvestmentIdItt() {
		return deductionsVIInvestmentIdItt;
	}

	public void setDeductionsVIInvestmentIdItt(String deductionsVIInvestmentIdItt) {
		this.deductionsVIInvestmentIdItt = deductionsVIInvestmentIdItt;
	}

	public String getDonationEmpIdItt() {
		return donationEmpIdItt;
	}

	public void setDonationEmpIdItt(String donationEmpIdItt) {
		this.donationEmpIdItt = donationEmpIdItt;
	}

	public String getDonationEmpNameItt() {
		return donationEmpNameItt;
	}

	public void setDonationEmpNameItt(String donationEmpNameItt) {
		this.donationEmpNameItt = donationEmpNameItt;
	}

	public String getDonationNameItt() {
		return donationNameItt;
	}

	public void setDonationNameItt(String donationNameItt) {
		this.donationNameItt = donationNameItt;
	}

	public String getDonationExemptionPercentageItt() {
		return donationExemptionPercentageItt;
	}

	public void setDonationExemptionPercentageItt(
			String donationExemptionPercentageItt) {
		this.donationExemptionPercentageItt = donationExemptionPercentageItt;
	}

	public String getDonationInvAmountItt() {
		return donationInvAmountItt;
	}

	public void setDonationInvAmountItt(String donationInvAmountItt) {
		this.donationInvAmountItt = donationInvAmountItt;
	}

	public String getDonationViewDocItt() {
		return donationViewDocItt;
	}

	public void setDonationViewDocItt(String donationViewDocItt) {
		this.donationViewDocItt = donationViewDocItt;
	}

	public String getDonationVerifiedItt() {
		return donationVerifiedItt;
	}

	public void setDonationVerifiedItt(String donationVerifiedItt) {
		this.donationVerifiedItt = donationVerifiedItt;
	}

	public String getDonationVerifiedAmountItt() {
		return donationVerifiedAmountItt;
	}

	public void setDonationVerifiedAmountItt(String donationVerifiedAmountItt) {
		this.donationVerifiedAmountItt = donationVerifiedAmountItt;
	}

	public String getDonationProofAttachedItt() {
		return donationProofAttachedItt;
	}

	public void setDonationProofAttachedItt(String donationProofAttachedItt) {
		this.donationProofAttachedItt = donationProofAttachedItt;
	}

	public String getDonationIdItt() {
		return donationIdItt;
	}

	public void setDonationIdItt(String donationIdItt) {
		this.donationIdItt = donationIdItt;
	}

	public Boolean getDonationAttachViewFlagItt() {
		return donationAttachViewFlagItt;
	}

	public void setDonationAttachViewFlagItt(Boolean donationAttachViewFlagItt) {
		this.donationAttachViewFlagItt = donationAttachViewFlagItt;
	}

	public ArrayList getDonationInvestmentList() {
		return donationInvestmentList;
	}

	public void setDonationInvestmentList(ArrayList donationInvestmentList) {
		this.donationInvestmentList = donationInvestmentList;
	}

	public Boolean getDonationListViewFlag() {
		return donationListViewFlag;
	}

	public void setDonationListViewFlag(Boolean donationListViewFlag) {
		this.donationListViewFlag = donationListViewFlag;
	}

	public ArrayList getExemptionsAttachmentList() {
		return exemptionsAttachmentList;
	}

	public void setExemptionsAttachmentList(ArrayList exemptionsAttachmentList) {
		this.exemptionsAttachmentList = exemptionsAttachmentList;
	}

	public ArrayList getOtherAttachmentList() {
		return otherAttachmentList;
	}

	public void setOtherAttachmentList(ArrayList otherAttachmentList) {
		this.otherAttachmentList = otherAttachmentList;
	}

	public ArrayList getDeductionsVIAAttachmentList() {
		return deductionsVIAAttachmentList;
	}

	public void setDeductionsVIAAttachmentList(ArrayList deductionsVIAAttachmentList) {
		this.deductionsVIAAttachmentList = deductionsVIAAttachmentList;
	}

	public ArrayList getDeductionsVIAttachmentList() {
		return deductionsVIAttachmentList;
	}

	public void setDeductionsVIAttachmentList(ArrayList deductionsVIAttachmentList) {
		this.deductionsVIAttachmentList = deductionsVIAttachmentList;
	}

	public ArrayList getDonationAttachmentList() {
		return donationAttachmentList;
	}

	public void setDonationAttachmentList(ArrayList donationAttachmentList) {
		this.donationAttachmentList = donationAttachmentList;
	}

	public ArrayList getMonthlyAttachmentList() {
		return monthlyAttachmentList;
	}

	public void setMonthlyAttachmentList(ArrayList monthlyAttachmentList) {
		this.monthlyAttachmentList = monthlyAttachmentList;
	}

	public Boolean getPaginationFlag() {
		return paginationFlag;
	}

	public void setPaginationFlag(Boolean paginationFlag) {
		this.paginationFlag = paginationFlag;
	}

	public String getNavigateTo() {
		return navigateTo;
	}

	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}

	public Boolean getEmpSaveFlag() {
		return empSaveFlag;
	}

	public void setEmpSaveFlag(Boolean empSaveFlag) {
		this.empSaveFlag = empSaveFlag;
	}

	public Boolean getProofAttachedFlag() {
		return proofAttachedFlag;
	}

	public void setProofAttachedFlag(Boolean proofAttachedFlag) {
		this.proofAttachedFlag = proofAttachedFlag;
	}

	public String getSettlementApplnStatus() {
		return settlementApplnStatus;
	}

	public void setSettlementApplnStatus(String settlementApplnStatus) {
		this.settlementApplnStatus = settlementApplnStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the monthlyCityPopulationDeclaredItt
	 */
	public String getMonthlyCityPopulationDeclaredItt() {
		return this.monthlyCityPopulationDeclaredItt;
	}

	/**
	 * @param monthlyCityPopulationDeclaredItt the monthlyCityPopulationDeclaredItt to set
	 */
	public void setMonthlyCityPopulationDeclaredItt(
			String monthlyCityPopulationDeclaredItt) {
		this.monthlyCityPopulationDeclaredItt = monthlyCityPopulationDeclaredItt;
	}

	/**
	 * @return the monthlyCityPopulationVerifiedItt
	 */
	public String getMonthlyCityPopulationVerifiedItt() {
		return this.monthlyCityPopulationVerifiedItt;
	}

	/**
	 * @param monthlyCityPopulationVerifiedItt the monthlyCityPopulationVerifiedItt to set
	 */
	public void setMonthlyCityPopulationVerifiedItt(
			String monthlyCityPopulationVerifiedItt) {
		this.monthlyCityPopulationVerifiedItt = monthlyCityPopulationVerifiedItt;
	}
}
