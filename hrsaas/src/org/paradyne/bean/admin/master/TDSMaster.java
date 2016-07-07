package org.paradyne.bean.admin.master;
import org.paradyne.lib.BeanBase;
import java.util.*;
/*
 * author:Pradeep Kumar Sahoo
 * Date:20-05-2008
 */

public class TDSMaster extends BeanBase {
	
	private String tdsCode="";
	private String tdsDate="";
	private String surcharge="";
	private String eduCess="";
	private String tdsDebitCode="";
	private String tdsDebit="";
	private String salAmt="";
	private String salCode="";
	private String salHead="";
	private String salAbbr="";
	private String frmCalc="";
	private String salCode2="";
	private String salHead2="";
	private String salAbbr2="";
	private String frmCalc2="";
	private String salCode3="";
	private String salHead3="";
	private String salAbbr3="";
	private String frmCalc3="";
	private String salCode4="";
	private String salHead4="";
	private String salAbbr4="";
	private String frmCalc4="";
	private String salCode5="";
	private String salHead5="";
	private String salAbbr5="";
	private String frmCalc5="";
	private String salCode6="";
	private String salHead6="";
	private String salAbbr6="";
	private String frmCalc6="";
	private String citizenAge="";
	private String citizenSurLimit="";
	private String salAmt2="";
	private String salAmt3="";
	private String salAmt4="";
	private String salAmt5="";
	private String salAmt6="";
	private String hraCode="";
	private String hraName="";
	private String daCode="";
	private String daName="";
	private String basicCode="";
	private String basicName="";
	private String hraExCode="";
	private String hraExName="";
	private String rebateLimit="";
	private String para="";
	private String provFundCode="";
	private String provFundName="";
	private String myPage="";
	private String hiddenCode="";
	private String hdeleteCode="";
	private String show="";
	private String selectname="";
	private String confChk="";
	private ArrayList tdsList=null;
	private String signAuthId="";
	private String signAuthName="";
	private String token="";
	private String hraPaidMetro="";
	private String ReimbursebillDate="";
	private String invVerificationDate="";
	private String toYear;
	private String fromYear;
	private String leaveEncashamt="";
	private String leaveEncashMonths="";
	private String leaveEncashFormula="";
	private String reportType="";
	
	// edited by Prashant Shinde
	//private String avgleaveEncashNo="";  
	private String leaveEncInvcode=""; 
	private String leaveEncInvName;
	
	//added by vishwambhar
	
	private String creditCode ="";
	private String creditType ="";
	private String gratuityAmount ="";
	private String taxCode ="";
	private String exemptSectionNo ="";
	
	private String ltaExemptSectionNo ="";
	private String ltaCreditType  ="";
	private String ltaCreditCode ="";
	private String ltaTaxCode ="";
	private String ltaAmount ="";
	//Added Ganesh 4Oct2011
	ArrayList tdsIteratorList = null;
	boolean tdsListLength = false;
	private String traAllowanceLimit="";
	private String traAllowExemptSectionNo = "";
	private String traAllowTaxCode = "";
	private String vehicleCapGreaterthan1600 = "";
	private String vehicleCapLessthan1600 = "";
	private String driverUsedAddExemption = "";
	private String vehicalAllowExemptSectionNo = "";
	private String vehicalAllowTaxCode = "";
	private String donationsExemptSectionNo = "";
	private String donationsTaxCode = "";
	boolean draftVoucherListLength = false ;
	boolean insertButtonFlag = false ;
	boolean updateButtonFlag = false ;
	private String itttdsCode="";
	private String invDeclarationCuttOffDate = "";
	private String monthInvestmentDecPeriodFromDate = "";
	private String monthInvestmentDecPeriodToDate= "";
	
	//ADDED BY VIJAY
	private String hraExUnpaidCode = "";
	private String hraExUnpaidName = "";
	private String mapPerquisiteHeadCode = "";
	private String mapPerquisiteHeadName = "";
	private String accomPerqCentOwnedHigher = "";
	private String accomPerqCentOwnedLess = "";
	private String accomPerqCentRentedHigher = "";
	private String accomPerqCentRentedLess = "";
	private String transAllowanceLimitPermDisabi = "";
	private String traAllowCreditHeadName = "";
	private String traAllowCreditHeadCode = "";
	private String permanentDisabilityDedCode = "";
	private String permanentDisabilityDedName = "";
	private String govLoanRate = "";
	private String perqHeadCompanyLoan = "";
	private String perqHeadCompanyLoanCode = "";
	private String minLoanAmt = "";
	private String lockFlag = "";
	private String donationApplInv="";
	private String donationApplInvCode="";
	private String donationFormPercent="";
	
	//For Tax Rebate
	private String taxRebateEnable="";
	private String taxRebateAmount="";
	private String taxIncomeLimit="";
	
	public String getDonationApplInv() {
		return donationApplInv;
	}
	public void setDonationApplInv(String donationApplInv) {
		this.donationApplInv = donationApplInv;
	}
	public String getDonationApplInvCode() {
		return donationApplInvCode;
	}
	public void setDonationApplInvCode(String donationApplInvCode) {
		this.donationApplInvCode = donationApplInvCode;
	}
	public String getDonationFormPercent() {
		return donationFormPercent;
	}
	public void setDonationFormPercent(String donationFormPercent) {
		this.donationFormPercent = donationFormPercent;
	}
	
	public String getInvDeclarationCuttOffDate() {
		return invDeclarationCuttOffDate;
	}
	public void setInvDeclarationCuttOffDate(String invDeclarationCuttOffDate) {
		this.invDeclarationCuttOffDate = invDeclarationCuttOffDate;
	}
	public String getMonthInvestmentDecPeriodFromDate() {
		return monthInvestmentDecPeriodFromDate;
	}
	public void setMonthInvestmentDecPeriodFromDate(
			String monthInvestmentDecPeriodFromDate) {
		this.monthInvestmentDecPeriodFromDate = monthInvestmentDecPeriodFromDate;
	}
	public String getMonthInvestmentDecPeriodToDate() {
		return monthInvestmentDecPeriodToDate;
	}
	public void setMonthInvestmentDecPeriodToDate(
			String monthInvestmentDecPeriodToDate) {
		this.monthInvestmentDecPeriodToDate = monthInvestmentDecPeriodToDate;
	}
	public String getItttdsCode() {
		return itttdsCode;
	}
	public void setItttdsCode(String itttdsCode) {
		this.itttdsCode = itttdsCode;
	}
	public boolean isInsertButtonFlag() {
		return insertButtonFlag;
	}
	public void setInsertButtonFlag(boolean insertButtonFlag) {
		this.insertButtonFlag = insertButtonFlag;
	}
	public boolean isUpdateButtonFlag() {
		return updateButtonFlag;
	}
	public void setUpdateButtonFlag(boolean updateButtonFlag) {
		this.updateButtonFlag = updateButtonFlag;
	}
	public String getTraAllowanceLimit() {
		return traAllowanceLimit;
	}
	public void setTraAllowanceLimit(String traAllowanceLimit) {
		this.traAllowanceLimit = traAllowanceLimit;
	}
	public String getTraAllowExemptSectionNo() {
		return traAllowExemptSectionNo;
	}
	public void setTraAllowExemptSectionNo(String traAllowExemptSectionNo) {
		this.traAllowExemptSectionNo = traAllowExemptSectionNo;
	}
	public String getTraAllowTaxCode() {
		return traAllowTaxCode;
	}
	public void setTraAllowTaxCode(String traAllowTaxCode) {
		this.traAllowTaxCode = traAllowTaxCode;
	}
	public String getVehicleCapGreaterthan1600() {
		return vehicleCapGreaterthan1600;
	}
	public void setVehicleCapGreaterthan1600(String vehicleCapGreaterthan1600) {
		this.vehicleCapGreaterthan1600 = vehicleCapGreaterthan1600;
	}
	public String getVehicleCapLessthan1600() {
		return vehicleCapLessthan1600;
	}
	public void setVehicleCapLessthan1600(String vehicleCapLessthan1600) {
		this.vehicleCapLessthan1600 = vehicleCapLessthan1600;
	}
	public String getDriverUsedAddExemption() {
		return driverUsedAddExemption;
	}
	public void setDriverUsedAddExemption(String driverUsedAddExemption) {
		this.driverUsedAddExemption = driverUsedAddExemption;
	}
	public String getVehicalAllowExemptSectionNo() {
		return vehicalAllowExemptSectionNo;
	}
	public void setVehicalAllowExemptSectionNo(String vehicalAllowExemptSectionNo) {
		this.vehicalAllowExemptSectionNo = vehicalAllowExemptSectionNo;
	}
	public String getVehicalAllowTaxCode() {
		return vehicalAllowTaxCode;
	}
	public void setVehicalAllowTaxCode(String vehicalAllowTaxCode) {
		this.vehicalAllowTaxCode = vehicalAllowTaxCode;
	}
	public String getDonationsExemptSectionNo() {
		return donationsExemptSectionNo;
	}
	public void setDonationsExemptSectionNo(String donationsExemptSectionNo) {
		this.donationsExemptSectionNo = donationsExemptSectionNo;
	}
	public String getDonationsTaxCode() {
		return donationsTaxCode;
	}
	public void setDonationsTaxCode(String donationsTaxCode) {
		this.donationsTaxCode = donationsTaxCode;
	}
	public String getLtaExemptSectionNo() {
		return ltaExemptSectionNo;
	}
	public void setLtaExemptSectionNo(String ltaExemptSectionNo) {
		this.ltaExemptSectionNo = ltaExemptSectionNo;
	}
	public String getLtaCreditType() {
		return ltaCreditType;
	}
	public void setLtaCreditType(String ltaCreditType) {
		this.ltaCreditType = ltaCreditType;
	}
	public String getLtaCreditCode() {
		return ltaCreditCode;
	}
	public void setLtaCreditCode(String ltaCreditCode) {
		this.ltaCreditCode = ltaCreditCode;
	}
	public String getLtaTaxCode() {
		return ltaTaxCode;
	}
	public void setLtaTaxCode(String ltaTaxCode) {
		this.ltaTaxCode = ltaTaxCode;
	}
	public String getLtaAmount() {
		return ltaAmount;
	}
	public void setLtaAmount(String ltaAmount) {
		this.ltaAmount = ltaAmount;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getExemptSectionNo() {
		return exemptSectionNo;
	}
	public void setExemptSectionNo(String exemptSectionNo) {
		this.exemptSectionNo = exemptSectionNo;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
 
	public String getGratuityAmount() {
		return gratuityAmount;
	}
	public void setGratuityAmount(String gratuityAmount) {
		this.gratuityAmount = gratuityAmount;
	}
	public String getLeaveEncInvName() {
		return leaveEncInvName;
	}
	public void setLeaveEncInvName(String leaveEncInvName) {
		this.leaveEncInvName = leaveEncInvName;
	}
	public String getLeaveEncInvcode() {
		return leaveEncInvcode;
	}
	public void setLeaveEncInvcode(String leaveEncInvcode) {
		this.leaveEncInvcode = leaveEncInvcode;
	}
	public String getLeaveEncashamt() {
		return leaveEncashamt;
	}
	public void setLeaveEncashamt(String leaveEncashamt) {
		this.leaveEncashamt = leaveEncashamt;
	}
	public String getLeaveEncashMonths() {
		return leaveEncashMonths;
	}
	public void setLeaveEncashMonths(String leaveEncashMonths) {
		this.leaveEncashMonths = leaveEncashMonths;
	}
	public String getLeaveEncashFormula() {
		return leaveEncashFormula;
	}
	public void setLeaveEncashFormula(String leaveEncashFormula) {
		this.leaveEncashFormula = leaveEncashFormula;
	}
	/*public String getAvgleaveEncashNo() {
		return avgleaveEncashNo;
	}
	public void setAvgleaveEncashNo(String avgleaveEncashNo) {
		this.avgleaveEncashNo = avgleaveEncashNo;
	}*/
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
	public String getHraPaidMetro() {
		return hraPaidMetro;
	}
	public void setHraPaidMetro(String hraPaidMetro) {
		this.hraPaidMetro = hraPaidMetro;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
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
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public ArrayList getTdsList() {
		return tdsList;
	}
	public void setTdsList(ArrayList tdsList) {
		this.tdsList = tdsList;
	}
	public String getProvFundCode() {
		return provFundCode;
	}
	public void setProvFundCode(String provFundCode) {
		this.provFundCode = provFundCode;
	}
	public String getProvFundName() {
		return provFundName;
	}
	public void setProvFundName(String provFundName) {
		this.provFundName = provFundName;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getSalAmt2() {
		return salAmt2;
	}
	public void setSalAmt2(String salAmt2) {
		this.salAmt2 = salAmt2;
	}
	public String getSalAmt3() {
		return salAmt3;
	}
	public void setSalAmt3(String salAmt3) {
		this.salAmt3 = salAmt3;
	}
	public String getSalAmt4() {
		return salAmt4;
	}
	public void setSalAmt4(String salAmt4) {
		this.salAmt4 = salAmt4;
	}
	public String getSalAmt5() {
		return salAmt5;
	}
	public void setSalAmt5(String salAmt5) {
		this.salAmt5 = salAmt5;
	}
	public String getSalAmt6() {
		return salAmt6;
	}
	public void setSalAmt6(String salAmt6) {
		this.salAmt6 = salAmt6;
	}
	public String getCitizenAge() {
		return citizenAge;
	}
	public void setCitizenAge(String citizenAge) {
		this.citizenAge = citizenAge;
	}
	public String getCitizenSurLimit() {
		return citizenSurLimit;
	}
	public void setCitizenSurLimit(String citizenSurLimit) {
		this.citizenSurLimit = citizenSurLimit;
	}
	public String getSalCode2() {
		return salCode2;
	}
	public void setSalCode2(String salCode2) {
		this.salCode2 = salCode2;
	}
	public String getSalHead2() {
		return salHead2;
	}
	public void setSalHead2(String salHead2) {
		this.salHead2 = salHead2;
	}
	public String getSalAbbr2() {
		return salAbbr2;
	}
	public void setSalAbbr2(String salAbbr2) {
		this.salAbbr2 = salAbbr2;
	}
	public String getFrmCalc2() {
		return frmCalc2;
	}
	public void setFrmCalc2(String frmCalc2) {
		this.frmCalc2 = frmCalc2;
	}
	public String getFrmCalc() {
		return frmCalc;
	}
	public void setFrmCalc(String frmCalc) {
		this.frmCalc = frmCalc;
	}
	public String getSalAmt() {
		return salAmt;
	}
	public void setSalAmt(String salAmt) {
		this.salAmt = salAmt;
	}
	public String getTdsCode() {
		return tdsCode;
	}
	public void setTdsCode(String tdsCode) {
		this.tdsCode = tdsCode;
	}
	public String getTdsDate() {
		return tdsDate;
	}
	public void setTdsDate(String tdsDate) {
		this.tdsDate = tdsDate;
	}
	public String getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	public String getEduCess() {
		return eduCess;
	}
	public void setEduCess(String eduCess) {
		this.eduCess = eduCess;
	}
	public String getTdsDebitCode() {
		return tdsDebitCode;
	}
	public void setTdsDebitCode(String tdsDebitCode) {
		this.tdsDebitCode = tdsDebitCode;
	}
	public String getTdsDebit() {
		return tdsDebit;
	}
	public void setTdsDebit(String tdsDebit) {
		this.tdsDebit = tdsDebit;
	}
	public String getSalCode() {
		return salCode;
	}
	public void setSalCode(String salCode) {
		this.salCode = salCode;
	}
	public String getSalHead() {
		return salHead;
	}
	public void setSalHead(String salHead) {
		this.salHead = salHead;
	}
	public String getSalAbbr() {
		return salAbbr;
	}
	public void setSalAbbr(String salAbbr) {
		this.salAbbr = salAbbr;
	}
	public String getSalCode3() {
		return salCode3;
	}
	public void setSalCode3(String salCode3) {
		this.salCode3 = salCode3;
	}
	public String getSalHead3() {
		return salHead3;
	}
	public void setSalHead3(String salHead3) {
		this.salHead3 = salHead3;
	}
	public String getSalAbbr3() {
		return salAbbr3;
	}
	public void setSalAbbr3(String salAbbr3) {
		this.salAbbr3 = salAbbr3;
	}
	public String getFrmCalc3() {
		return frmCalc3;
	}
	public void setFrmCalc3(String frmCalc3) {
		this.frmCalc3 = frmCalc3;
	}
	public String getSalCode4() {
		return salCode4;
	}
	public void setSalCode4(String salCode4) {
		this.salCode4 = salCode4;
	}
	public String getSalHead4() {
		return salHead4;
	}
	public void setSalHead4(String salHead4) {
		this.salHead4 = salHead4;
	}
	public String getSalAbbr4() {
		return salAbbr4;
	}
	public void setSalAbbr4(String salAbbr4) {
		this.salAbbr4 = salAbbr4;
	}
	public String getFrmCalc4() {
		return frmCalc4;
	}
	public void setFrmCalc4(String frmCalc4) {
		this.frmCalc4 = frmCalc4;
	}
	public String getSalCode5() {
		return salCode5;
	}
	public void setSalCode5(String salCode5) {
		this.salCode5 = salCode5;
	}
	public String getSalHead5() {
		return salHead5;
	}
	public void setSalHead5(String salHead5) {
		this.salHead5 = salHead5;
	}
	public String getSalAbbr5() {
		return salAbbr5;
	}
	public void setSalAbbr5(String salAbbr5) {
		this.salAbbr5 = salAbbr5;
	}
	public String getFrmCalc5() {
		return frmCalc5;
	}
	public void setFrmCalc5(String frmCalc5) {
		this.frmCalc5 = frmCalc5;
	}
	public String getSalCode6() {
		return salCode6;
	}
	public void setSalCode6(String salCode6) {
		this.salCode6 = salCode6;
	}
	public String getSalHead6() {
		return salHead6;
	}
	public void setSalHead6(String salHead6) {
		this.salHead6 = salHead6;
	}
	public String getSalAbbr6() {
		return salAbbr6;
	}
	public void setSalAbbr6(String salAbbr6) {
		this.salAbbr6 = salAbbr6;
	}
	public String getFrmCalc6() {
		return frmCalc6;
	}
	public void setFrmCalc6(String frmCalc6) {
		this.frmCalc6 = frmCalc6;
	}
	public String getHraCode() {
		return hraCode;
	}
	public void setHraCode(String hraCode) {
		this.hraCode = hraCode;
	}
	public String getHraName() {
		return hraName;
	}
	public void setHraName(String hraName) {
		this.hraName = hraName;
	}
	public String getDaCode() {
		return daCode;
	}
	public void setDaCode(String daCode) {
		this.daCode = daCode;
	}
	public String getDaName() {
		return daName;
	}
	public void setDaName(String daName) {
		this.daName = daName;
	}
	public String getBasicCode() {
		return basicCode;
	}
	public void setBasicCode(String basicCode) {
		this.basicCode = basicCode;
	}
	public String getBasicName() {
		return basicName;
	}
	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}
	public String getHraExCode() {
		return hraExCode;
	}
	public void setHraExCode(String hraExCode) {
		this.hraExCode = hraExCode;
	}
	public String getHraExName() {
		return hraExName;
	}
	public void setHraExName(String hraExName) {
		this.hraExName = hraExName;
	}
	public String getRebateLimit() {
		return rebateLimit;
	}
	public void setRebateLimit(String rebateLimit) {
		this.rebateLimit = rebateLimit;
	}
	public String getSignAuthId() {
		return signAuthId;
	}
	public void setSignAuthId(String signAuthId) {
		this.signAuthId = signAuthId;
	}
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getReimbursebillDate() {
		return ReimbursebillDate;
	}
	public void setReimbursebillDate(String reimbursebillDate) {
		ReimbursebillDate = reimbursebillDate;
	}
	public String getInvVerificationDate() {
		return invVerificationDate;
	}
	public void setInvVerificationDate(String invVerificationDate) {
		this.invVerificationDate = invVerificationDate;
	}
	public ArrayList getTdsIteratorList() {
		return tdsIteratorList;
	}
	public void setTdsIteratorList(ArrayList tdsIteratorList) {
		this.tdsIteratorList = tdsIteratorList;
	}
	public boolean isTdsListLength() {
		return tdsListLength;
	}
	public void setTdsListLength(boolean tdsListLength) {
		this.tdsListLength = tdsListLength;
	}
	public boolean isDraftVoucherListLength() {
		return draftVoucherListLength;
	}
	public void setDraftVoucherListLength(boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the hraExUnpaidCode
	 */
	public String getHraExUnpaidCode() {
		return hraExUnpaidCode;
	}
	/**
	 * @param hraExUnpaidCode the hraExUnpaidCode to set
	 */
	public void setHraExUnpaidCode(String hraExUnpaidCode) {
		this.hraExUnpaidCode = hraExUnpaidCode;
	}
	/**
	 * @return the hraExUnpaidName
	 */
	public String getHraExUnpaidName() {
		return hraExUnpaidName;
	}
	/**
	 * @param hraExUnpaidName the hraExUnpaidName to set
	 */
	public void setHraExUnpaidName(String hraExUnpaidName) {
		this.hraExUnpaidName = hraExUnpaidName;
	}
	/**
	 * @return the mapPerquisiteHeadCode
	 */
	public String getMapPerquisiteHeadCode() {
		return mapPerquisiteHeadCode;
	}
	/**
	 * @param mapPerquisiteHeadCode the mapPerquisiteHeadCode to set
	 */
	public void setMapPerquisiteHeadCode(String mapPerquisiteHeadCode) {
		this.mapPerquisiteHeadCode = mapPerquisiteHeadCode;
	}
	/**
	 * @return the mapPerquisiteHeadName
	 */
	public String getMapPerquisiteHeadName() {
		return mapPerquisiteHeadName;
	}
	/**
	 * @param mapPerquisiteHeadName the mapPerquisiteHeadName to set
	 */
	public void setMapPerquisiteHeadName(String mapPerquisiteHeadName) {
		this.mapPerquisiteHeadName = mapPerquisiteHeadName;
	}
	/**
	 * @return the accomPerqCentOwnedHigher
	 */
	public String getAccomPerqCentOwnedHigher() {
		return accomPerqCentOwnedHigher;
	}
	/**
	 * @param accomPerqCentOwnedHigher the accomPerqCentOwnedHigher to set
	 */
	public void setAccomPerqCentOwnedHigher(String accomPerqCentOwnedHigher) {
		this.accomPerqCentOwnedHigher = accomPerqCentOwnedHigher;
	}
	/**
	 * @return the accomPerqCentOwnedLess
	 */
	public String getAccomPerqCentOwnedLess() {
		return accomPerqCentOwnedLess;
	}
	/**
	 * @param accomPerqCentOwnedLess the accomPerqCentOwnedLess to set
	 */
	public void setAccomPerqCentOwnedLess(String accomPerqCentOwnedLess) {
		this.accomPerqCentOwnedLess = accomPerqCentOwnedLess;
	}
	/**
	 * @return the accomPerqCentRentedHigher
	 */
	public String getAccomPerqCentRentedHigher() {
		return accomPerqCentRentedHigher;
	}
	/**
	 * @param accomPerqCentRentedHigher the accomPerqCentRentedHigher to set
	 */
	public void setAccomPerqCentRentedHigher(String accomPerqCentRentedHigher) {
		this.accomPerqCentRentedHigher = accomPerqCentRentedHigher;
	}
	/**
	 * @return the accomPerqCentRentedLess
	 */
	public String getAccomPerqCentRentedLess() {
		return accomPerqCentRentedLess;
	}
	/**
	 * @param accomPerqCentRentedLess the accomPerqCentRentedLess to set
	 */
	public void setAccomPerqCentRentedLess(String accomPerqCentRentedLess) {
		this.accomPerqCentRentedLess = accomPerqCentRentedLess;
	}
	/**
	 * @return the transAllowanceLimitPermDisabi
	 */
	public String getTransAllowanceLimitPermDisabi() {
		return transAllowanceLimitPermDisabi;
	}
	/**
	 * @param transAllowanceLimitPermDisabi the transAllowanceLimitPermDisabi to set
	 */
	public void setTransAllowanceLimitPermDisabi(
			String transAllowanceLimitPermDisabi) {
		this.transAllowanceLimitPermDisabi = transAllowanceLimitPermDisabi;
	}
	/**
	 * @return the traAllowCreditHeadName
	 */
	public String getTraAllowCreditHeadName() {
		return traAllowCreditHeadName;
	}
	/**
	 * @param traAllowCreditHeadName the traAllowCreditHeadName to set
	 */
	public void setTraAllowCreditHeadName(String traAllowCreditHeadName) {
		this.traAllowCreditHeadName = traAllowCreditHeadName;
	}
	/**
	 * @return the traAllowCreditHeadCode
	 */
	public String getTraAllowCreditHeadCode() {
		return traAllowCreditHeadCode;
	}
	/**
	 * @param traAllowCreditHeadCode the traAllowCreditHeadCode to set
	 */
	public void setTraAllowCreditHeadCode(String traAllowCreditHeadCode) {
		this.traAllowCreditHeadCode = traAllowCreditHeadCode;
	}
	/**
	 * @return the permanentDisabilityDedCode
	 */
	public String getPermanentDisabilityDedCode() {
		return permanentDisabilityDedCode;
	}
	/**
	 * @param permanentDisabilityDedCode the permanentDisabilityDedCode to set
	 */
	public void setPermanentDisabilityDedCode(String permanentDisabilityDedCode) {
		this.permanentDisabilityDedCode = permanentDisabilityDedCode;
	}
	/**
	 * @return the permanentDisabilityDedName
	 */
	public String getPermanentDisabilityDedName() {
		return permanentDisabilityDedName;
	}
	/**
	 * @param permanentDisabilityDedName the permanentDisabilityDedName to set
	 */
	public void setPermanentDisabilityDedName(String permanentDisabilityDedName) {
		this.permanentDisabilityDedName = permanentDisabilityDedName;
	}
	/**
	 * @return the govLoanRate
	 */
	public String getGovLoanRate() {
		return govLoanRate;
	}
	/**
	 * @param govLoanRate the govLoanRate to set
	 */
	public void setGovLoanRate(String govLoanRate) {
		this.govLoanRate = govLoanRate;
	}
	/**
	 * @return the perqHeadCompanyLoan
	 */
	public String getPerqHeadCompanyLoan() {
		return perqHeadCompanyLoan;
	}
	/**
	 * @param perqHeadCompanyLoan the perqHeadCompanyLoan to set
	 */
	public void setPerqHeadCompanyLoan(String perqHeadCompanyLoan) {
		this.perqHeadCompanyLoan = perqHeadCompanyLoan;
	}
	/**
	 * @return the perqHeadCompanyLoanCode
	 */
	public String getPerqHeadCompanyLoanCode() {
		return perqHeadCompanyLoanCode;
	}
	/**
	 * @param perqHeadCompanyLoanCode the perqHeadCompanyLoanCode to set
	 */
	public void setPerqHeadCompanyLoanCode(String perqHeadCompanyLoanCode) {
		this.perqHeadCompanyLoanCode = perqHeadCompanyLoanCode;
	}
	/**
	 * @return the minLoanAmt
	 */
	public String getMinLoanAmt() {
		return minLoanAmt;
	}
	/**
	 * @param minLoanAmt the minLoanAmt to set
	 */
	public void setMinLoanAmt(String minLoanAmt) {
		this.minLoanAmt = minLoanAmt;
	}
	/**
	 * @return the lockFlag
	 */
	public String getLockFlag() {
		return lockFlag;
	}
	/**
	 * @param lockFlag the lockFlag to set
	 */
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	/**
	 * @return the taxRebateEnable
	 */
	public String getTaxRebateEnable() {
		return taxRebateEnable;
	}
	/**
	 * @param taxRebateEnable to set taxRebateEnable
	 */
	public void setTaxRebateEnable(String taxRebateEnable) {
		this.taxRebateEnable = taxRebateEnable;
	}
	/**
	 * @return taxRebateAmount
	 */
	public String getTaxRebateAmount() {
		return taxRebateAmount;
	}
	/**
	 * @param taxRebateAmount to set taxRebateAmount
	 */
	public void setTaxRebateAmount(String taxRebateAmount) {
		this.taxRebateAmount = taxRebateAmount;
	}
	/**
	 * @return taxIncomeLimit
	 */
	public String getTaxIncomeLimit() {
		return taxIncomeLimit;
	}
	/**
	 * @param taxIncomeLimit to set taxIncomeLimit
	 */
	public void setTaxIncomeLimit(String taxIncomeLimit) {
		this.taxIncomeLimit = taxIncomeLimit;
	}
		
}
