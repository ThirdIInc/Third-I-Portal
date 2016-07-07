package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class TdsCalculation extends BeanBase {
	
	private String source ="";
	private String invCode="";
	private String empID="";
	private String empAge="";
	private String empName="";
	private String empGender="";
	private String department="";
	private String center="";
	private String fromYear="";
	private String toYear="";
	private String invAmount="";
	private String invName="";
	private String paraId="";
	private String empTokenNo="";
	private String invId="";
	private String payBillNo="";
	private String creditAmt="";
	private String excemptions="";
	private String rebate="";
	private String otherInc="";
	
	private String taxIncome="";
	private String taxExemptionSection87="";
	private String taxExemptionIncomeLimit="";
	private String maxTaxExemption="";
	
	private String totalTax="";
	private String educCess="";
	private String surcharge="";
	
	private String netTax="";
	private String taxPaid="";
	private String taxPerMon="";
	private String chkFlag="";
	private String salary="";
	private String invChapter="";
	private String chkEdit="";
	private String lop="";
	private String nda="";
	private String daArr="";
	private String chkShwDetail="";
	private String currentMonth="";
	private String currentYear="";
	private String checkCurrentProcess="";
	private String detailCheck="";
	private String debPtaxCode="";
	private String otherFlag="";
	private String otherDecodeFlag="";
	private String employeeId;
	private String leaveEncashAmt;
	private String employeeName;
	private String employeeToken;
	private String leaveEncashAddedIncome;
	
	private String empToken="";
	private String empCenter="";
	private String empRank="";
	private ArrayList chkList=null;
	
	private String isFromEdit="";
	private String isFromCal="";
	private String str="false";
	
	ArrayList empInvList=null;
	ArrayList invList=null;
	
	private String hdChkFlag;
	private String noExemptData="false";
	private String prevEmployerFlag="false";
	private String preNetTaxableIncomeAmt="";
	private String preTaxPaidAmt="";
	private String prePTaxAmt="";
	private String prePFAmt="";
	private  ArrayList<Object> empExemptInvList=null;
	String invExemptId;
	String invExemptName;
	String invExemptLimit;
	String invExemptAmt;
	String totalExemptInvAmt;
	
	private String noOtherData="false";
	private  ArrayList<Object> empOtherInvList=null;
	String invOtherId;
	String invOtherName;
	String invOtherLimit;
	String invOtherAmt;
	String totalOtherInvAmt;
	
	private String noParaData="false";
	private  ArrayList<Object> empParaInvList=null;
	String invParaId;
	String invParaName;
	String invParaLimit;
	String invParaAmt;
	String totalParaInvAmt;
	
	private String noVIAData="false";
	private  ArrayList<Object> empVIAInvList=null;
	String invVIAId;
	String invVIAName;
	String invVIALimit;
	String invVIAAmt;
	String totalVIAInvAmt;
	
	private String noRebateData="false";
	private  ArrayList<Object> empRebateInvList=null;
	String invRebateId;
	String invRebateName;
	String invRebateLimit;
	String invRebateAmt;
	String totalRebateInvAmt;
	String rebateLimit;
	
	private String noSlabData="false";
	private  ArrayList<Object> tdslabList=null;
	String slabFrmAmt;
	String slabToAmt;
	String slabTaxRate;
	String slabAmt;
	String slabTax;
	
	private String branchFlag="";
	private String paybillFlag="";
	private String departmentFlag="";
	private String divisionFlag="";
	private String emptypeFlag="";
	

	
	private String branchCode=""; 
	private String branchName="";
	private String deptCode="";
	private String deptName="";
	private String divisionCode="";
	private String divisionName="";
	private String typeCode="";
	private String typeName="";
	
	private String payBillName="";
	
	private String noGrossData="false";
	private  ArrayList<Object> empGrossSalList=null;
	private String creditId;
	private String creditName;
	private String totalGrossAmt;
	private String processedAmt;
	
	private String noPerqData="false";
	private  ArrayList<Object> empPerqList=null;
	private String perqId;
	private String perqName;
	private String perqAmt;
	private String totalPerqAmt;
	private String totalCreditAmt;
	
	private String totalNetInvAmt;
	private String netTaxableIncome;
	private String taxOnIncome;
	private String eduCess;
	private String surCharge;
	private String taxPerMonth;
	private String pTaxAmt;
	private String centerId;
	private Object[][] ptaxLoc=null;
	private String monthCount;
	private String monthTotAmt;
	private String hraPaidAmtCond;
	private String hraPaidAmtCond1;
	private String hraPaidAmtCond2;
	private String remainMonths;
	private String remainMonthsHidden;
	private String taxParaDebitCode;
	private String empJoinedDate;
	private String ptaxMonthTotGross;
	private String empJoinMon;
	private String empJoinYear;
	
	private String isShowButton = "false";
	private String isShowCalculate = "false";
	private boolean chkCalulateFlag = false;
	private boolean savebutFlag = false;
	private String hdPage="";
	private String hdView="";
	private String taxParaSur="";
	private String taxParaEdu="";
	private String taxParaSurLimit="";
	private String ptaxVarAmt="";
	private String ptaxVarMth="";
	private String joinedMonth="";
	
	private String ledgerCode;
	
	/**
	 * for Search criteria
	 * @return
	 */
	private String empSearchId="";
	private String empSearchToken="";
	private String empSearchName="";
	private boolean searchbutFlag = false;

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getPtaxVarMth() {
		return ptaxVarMth;
	}

	public void setPtaxVarMth(String ptaxVarMth) {
		this.ptaxVarMth = ptaxVarMth;
	}

	public String getTaxParaSurLimit() {
		return taxParaSurLimit;
	}

	public void setTaxParaSurLimit(String taxParaSurLimit) {
		this.taxParaSurLimit = taxParaSurLimit;
	}

	public String getTaxParaSur() {
		return taxParaSur;
	}

	public void setTaxParaSur(String taxParaSur) {
		this.taxParaSur = taxParaSur;
	}

	public String getTaxParaEdu() {
		return taxParaEdu;
	}

	public void setTaxParaEdu(String taxParaEdu) {
		this.taxParaEdu = taxParaEdu;
	}

	public String getHdView() {
		return hdView;
	}

	public void setHdView(String hdView) {
		this.hdView = hdView;
	}

	public String getHdPage() {
		return hdPage;
	}

	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	public boolean isSavebutFlag() {
		return savebutFlag;
	}

	public void setSavebutFlag(boolean savebutFlag) {
		this.savebutFlag = savebutFlag;
	}

	public boolean isChkCalulateFlag() {
		return chkCalulateFlag;
	}

	public void setChkCalulateFlag(boolean chkCalulateFlag) {
		this.chkCalulateFlag = chkCalulateFlag;
	}

	public String getIsShowCalculate() {
		return isShowCalculate;
	}

	public void setIsShowCalculate(String isShowCalculate) {
		this.isShowCalculate = isShowCalculate;
	}

	public String getIsShowButton() {
		return isShowButton;
	}

	public void setIsShowButton(String isShowButton) {
		this.isShowButton = isShowButton;
	}

	public String getNoRebateData() {
		return noRebateData;
	}

	public void setNoRebateData(String noRebateData) {
		this.noRebateData = noRebateData;
	}

	public ArrayList<Object> getEmpRebateInvList() {
		return empRebateInvList;
	}

	public void setEmpRebateInvList(ArrayList<Object> empRebateInvList) {
		this.empRebateInvList = empRebateInvList;
	}

	public String getInvRebateId() {
		return invRebateId;
	}

	public void setInvRebateId(String invRebateId) {
		this.invRebateId = invRebateId;
	}

	public String getInvRebateName() {
		return invRebateName;
	}

	public void setInvRebateName(String invRebateName) {
		this.invRebateName = invRebateName;
	}

	public String getInvRebateLimit() {
		return invRebateLimit;
	}

	public void setInvRebateLimit(String invRebateLimit) {
		this.invRebateLimit = invRebateLimit;
	}

	public String getInvRebateAmt() {
		return invRebateAmt;
	}

	public void setInvRebateAmt(String invRebateAmt) {
		this.invRebateAmt = invRebateAmt;
	}

	public String getTotalRebateInvAmt() {
		return totalRebateInvAmt;
	}

	public void setTotalRebateInvAmt(String totalRebateInvAmt) {
		this.totalRebateInvAmt = totalRebateInvAmt;
	}

	public String getNoVIAData() {
		return noVIAData;
	}

	public void setNoVIAData(String noVIAData) {
		this.noVIAData = noVIAData;
	}

	public ArrayList<Object> getEmpVIAInvList() {
		return empVIAInvList;
	}

	public void setEmpVIAInvList(ArrayList<Object> empVIAInvList) {
		this.empVIAInvList = empVIAInvList;
	}

	public String getInvVIAId() {
		return invVIAId;
	}

	public void setInvVIAId(String invVIAId) {
		this.invVIAId = invVIAId;
	}

	public String getInvVIAName() {
		return invVIAName;
	}

	public void setInvVIAName(String invVIAName) {
		this.invVIAName = invVIAName;
	}

	public String getInvVIALimit() {
		return invVIALimit;
	}

	public void setInvVIALimit(String invVIALimit) {
		this.invVIALimit = invVIALimit;
	}

	public String getInvVIAAmt() {
		return invVIAAmt;
	}

	public void setInvVIAAmt(String invVIAAmt) {
		this.invVIAAmt = invVIAAmt;
	}

	public String getTotalVIAInvAmt() {
		return totalVIAInvAmt;
	}

	public void setTotalVIAInvAmt(String totalVIAInvAmt) {
		this.totalVIAInvAmt = totalVIAInvAmt;
	}

	public String getNoParaData() {
		return noParaData;
	}

	public void setNoParaData(String noParaData) {
		this.noParaData = noParaData;
	}

	public ArrayList<Object> getEmpParaInvList() {
		return empParaInvList;
	}

	public void setEmpParaInvList(ArrayList<Object> empParaInvList) {
		this.empParaInvList = empParaInvList;
	}

	public String getInvParaId() {
		return invParaId;
	}

	public void setInvParaId(String invParaId) {
		this.invParaId = invParaId;
	}

	public String getInvParaName() {
		return invParaName;
	}

	public void setInvParaName(String invParaName) {
		this.invParaName = invParaName;
	}

	public String getInvParaLimit() {
		return invParaLimit;
	}

	public void setInvParaLimit(String invParaLimit) {
		this.invParaLimit = invParaLimit;
	}

	public String getInvParaAmt() {
		return invParaAmt;
	}

	public void setInvParaAmt(String invParaAmt) {
		this.invParaAmt = invParaAmt;
	}

	public String getTotalParaInvAmt() {
		return totalParaInvAmt;
	}

	public void setTotalParaInvAmt(String totalParaInvAmt) {
		this.totalParaInvAmt = totalParaInvAmt;
	}

	public String getNoOtherData() {
		return noOtherData;
	}

	public void setNoOtherData(String noOtherData) {
		this.noOtherData = noOtherData;
	}

	public ArrayList<Object> getEmpOtherInvList() {
		return empOtherInvList;
	}

	public void setEmpOtherInvList(ArrayList<Object> empOtherInvList) {
		this.empOtherInvList = empOtherInvList;
	}

	public String getInvOtherId() {
		return invOtherId;
	}

	public void setInvOtherId(String invOtherId) {
		this.invOtherId = invOtherId;
	}

	public String getInvOtherName() {
		return invOtherName;
	}

	public void setInvOtherName(String invOtherName) {
		this.invOtherName = invOtherName;
	}

	public String getInvOtherLimit() {
		return invOtherLimit;
	}

	public void setInvOtherLimit(String invOtherLimit) {
		this.invOtherLimit = invOtherLimit;
	}

	public String getInvOtherAmt() {
		return invOtherAmt;
	}

	public void setInvOtherAmt(String invOtherAmt) {
		this.invOtherAmt = invOtherAmt;
	}

	public String getTotalOtherInvAmt() {
		return totalOtherInvAmt;
	}

	public void setTotalOtherInvAmt(String totalOtherInvAmt) {
		this.totalOtherInvAmt = totalOtherInvAmt;
	}

	/**
	 * @return the invList
	 */
	public ArrayList getInvList() {
		return invList;
	}

	/**
	 * @param invList the invList to set
	 */
	public void setInvList(ArrayList invList) {
		this.invList = invList;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public ArrayList getEmpInvList() {
		return empInvList;
	}

	public void setEmpInvList(ArrayList empInvList) {
		this.empInvList = empInvList;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(String invAmount) {
		this.invAmount = invAmount;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	

	
	/**
	 * @return the chkEdit
	 */
	public String getChkEdit() {
		return chkEdit;
	}

	/**
	 * @param chkEdit the chkEdit to set
	 */
	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	/**
	 * @return the isFromEdit
	 */
	public String getIsFromEdit() {
		return isFromEdit;
	}

	/**
	 * @param isFromEdit the isFromEdit to set
	 */
	public void setIsFromEdit(String isFromEdit) {
		this.isFromEdit = isFromEdit;
	}

	/**
	 * @return the invId
	 */
	public String getInvId() {
		return invId;
	}

	/**
	 * @param invId the invId to set
	 */
	public void setInvId(String invId) {
		this.invId = invId;
	}

	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}

	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	/**
	 * @return the creditAmt
	 */
	public String getCreditAmt() {
		return creditAmt;
	}

	/**
	 * @param creditAmt the creditAmt to set
	 */
	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	/**
	 * @return the educCess
	 */
	public String getEducCess() {
		return educCess;
	}

	/**
	 * @param educCess the educCess to set
	 */
	public void setEducCess(String educCess) {
		this.educCess = educCess;
	}

	/**
	 * @return the excemptions
	 */
	public String getExcemptions() {
		return excemptions;
	}

	/**
	 * @param excemptions the excemptions to set
	 */
	public void setExcemptions(String excemptions) {
		this.excemptions = excemptions;
	}

	/**
	 * @return the netTax
	 */
	public String getNetTax() {
		return netTax;
	}

	/**
	 * @param netTax the netTax to set
	 */
	public void setNetTax(String netTax) {
		this.netTax = netTax;
	}

	/**
	 * @return the otherInc
	 */
	public String getOtherInc() {
		return otherInc;
	}

	/**
	 * @param otherInc the otherInc to set
	 */
	public void setOtherInc(String otherInc) {
		this.otherInc = otherInc;
	}

	/**
	 * @return the rebate
	 */
	public String getRebate() {
		return rebate;
	}

	/**
	 * @param rebate the rebate to set
	 */
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	/**
	 * @return the surcharge
	 */
	public String getSurcharge() {
		return surcharge;
	}

	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}

	/**
	 * @return the taxIncome
	 */
	public String getTaxIncome() {
		return taxIncome;
	}

	/**
	 * @param taxIncome the taxIncome to set
	 */
	public void setTaxIncome(String taxIncome) {
		this.taxIncome = taxIncome;
	}

	/**
	 * @return the taxPaid
	 */
	public String getTaxPaid() {
		return taxPaid;
	}

	/**
	 * @param taxPaid the taxPaid to set
	 */
	public void setTaxPaid(String taxPaid) {
		this.taxPaid = taxPaid;
	}

	/**
	 * @return the taxPerMon
	 */
	public String getTaxPerMon() {
		return taxPerMon;
	}

	/**
	 * @param taxPerMon the taxPerMon to set
	 */
	public void setTaxPerMon(String taxPerMon) {
		this.taxPerMon = taxPerMon;
	}

	/**
	 * @return the totalTax
	 */
	public String getTotalTax() {
		return totalTax;
	}

	/**
	 * @param totalTax the totalTax to set
	 */
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	/**
	 * @return the isFromCal
	 */
	public String getIsFromCal() {
		return isFromCal;
	}

	/**
	 * @param isFromCal the isFromCal to set
	 */
	public void setIsFromCal(String isFromCal) {
		this.isFromCal = isFromCal;
	}

	/**
	 * @return the chkFlag
	 */
	public String getChkFlag() {
		return chkFlag;
	}

	/**
	 * @param chkFlag the chkFlag to set
	 */
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}

	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the invChapter
	 */
	public String getInvChapter() {
		return invChapter;
	}

	/**
	 * @param invChapter the invChapter to set
	 */
	public void setInvChapter(String invChapter) {
		this.invChapter = invChapter;
	}

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * @return the lop
	 */
	public String getLop() {
		return lop;
	}

	/**
	 * @param lop the lop to set
	 */
	public void setLop(String lop) {
		this.lop = lop;
	}

	/**
	 * @return the nda
	 */
	public String getNda() {
		return nda;
	}

	/**
	 * @param nda the nda to set
	 */
	public void setNda(String nda) {
		this.nda = nda;
	}

	/**
	 * @return the daArr
	 */
	public String getDaArr() {
		return daArr;
	}

	/**
	 * @param daArr the daArr to set
	 */
	public void setDaArr(String daArr) {
		this.daArr = daArr;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getNoExemptData() {
		return noExemptData;
	}

	public void setNoExemptData(String noExemptData) {
		this.noExemptData = noExemptData;
	}

	public ArrayList<Object> getEmpExemptInvList() {
		return empExemptInvList;
	}

	public void setEmpExemptInvList(ArrayList<Object> empExemptInvList) {
		this.empExemptInvList = empExemptInvList;
	}

	public String getInvExemptId() {
		return invExemptId;
	}

	public void setInvExemptId(String invExemptId) {
		this.invExemptId = invExemptId;
	}

	public String getInvExemptName() {
		return invExemptName;
	}

	public void setInvExemptName(String invExemptName) {
		this.invExemptName = invExemptName;
	}

	public String getInvExemptLimit() {
		return invExemptLimit;
	}

	public void setInvExemptLimit(String invExemptLimit) {
		this.invExemptLimit = invExemptLimit;
	}

	public String getInvExemptAmt() {
		return invExemptAmt;
	}

	public void setInvExemptAmt(String invExemptAmt) {
		this.invExemptAmt = invExemptAmt;
	}

	public String getTotalExemptInvAmt() {
		return totalExemptInvAmt;
	}

	public void setTotalExemptInvAmt(String totalExemptInvAmt) {
		this.totalExemptInvAmt = totalExemptInvAmt;
	}

	public String getEmpAge() {
		return empAge;
	}

	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}

	public String getNoSlabData() {
		return noSlabData;
	}

	public void setNoSlabData(String noSlabData) {
		this.noSlabData = noSlabData;
	}

	public ArrayList<Object> getTdslabList() {
		return tdslabList;
	}

	public void setTdslabList(ArrayList<Object> tdslabList) {
		this.tdslabList = tdslabList;
	}

	public String getSlabFrmAmt() {
		return slabFrmAmt;
	}

	public void setSlabFrmAmt(String slabFrmAmt) {
		this.slabFrmAmt = slabFrmAmt;
	}

	public String getSlabToAmt() {
		return slabToAmt;
	}

	public void setSlabToAmt(String slabToAmt) {
		this.slabToAmt = slabToAmt;
	}

	public String getSlabTaxRate() {
		return slabTaxRate;
	}

	public void setSlabTaxRate(String slabTaxRate) {
		this.slabTaxRate = slabTaxRate;
	}

	public String getSlabAmt() {
		return slabAmt;
	}

	public void setSlabAmt(String slabAmt) {
		this.slabAmt = slabAmt;
	}

	public String getSlabTax() {
		return slabTax;
	}

	public void setSlabTax(String slabTax) {
		this.slabTax = slabTax;
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

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPayBillName() {
		return payBillName;
	}

	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	public String getNoGrossData() {
		return noGrossData;
	}

	public void setNoGrossData(String noGrossData) {
		this.noGrossData = noGrossData;
	}

	public ArrayList<Object> getEmpGrossSalList() {
		return empGrossSalList;
	}

	public void setEmpGrossSalList(ArrayList<Object> empGrossSalList) {
		this.empGrossSalList = empGrossSalList;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getTotalGrossAmt() {
		return totalGrossAmt;
	}

	public void setTotalGrossAmt(String totalGrossAmt) {
		this.totalGrossAmt = totalGrossAmt;
	}

	public String getProcessedAmt() {
		return processedAmt;
	}

	public void setProcessedAmt(String processedAmt) {
		this.processedAmt = processedAmt;
	}

	public String getTotalNetInvAmt() {
		return totalNetInvAmt;
	}

	public void setTotalNetInvAmt(String totalNetInvAmt) {
		this.totalNetInvAmt = totalNetInvAmt;
	}

	public String getNetTaxableIncome() {
		return netTaxableIncome;
	}

	public void setNetTaxableIncome(String netTaxableIncome) {
		this.netTaxableIncome = netTaxableIncome;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpCenter() {
		return empCenter;
	}

	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}

	public String getEmpRank() {
		return empRank;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	public String getTaxOnIncome() {
		return taxOnIncome;
	}

	public void setTaxOnIncome(String taxOnIncome) {
		this.taxOnIncome = taxOnIncome;
	}

	public String getEduCess() {
		return eduCess;
	}

	public void setEduCess(String eduCess) {
		this.eduCess = eduCess;
	}

	public String getSurCharge() {
		return surCharge;
	}

	public void setSurCharge(String surCharge) {
		this.surCharge = surCharge;
	}

	public String getTaxPerMonth() {
		return taxPerMonth;
	}

	public void setTaxPerMonth(String taxPerMonth) {
		this.taxPerMonth = taxPerMonth;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

	public String getPaybillFlag() {
		return paybillFlag;
	}

	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}

	public String getDepartmentFlag() {
		return departmentFlag;
	}

	public void setDepartmentFlag(String departmentFlag) {
		this.departmentFlag = departmentFlag;
	}

	public String getDivisionFlag() {
		return divisionFlag;
	}

	public void setDivisionFlag(String divisionFlag) {
		this.divisionFlag = divisionFlag;
	}

	public String getEmptypeFlag() {
		return emptypeFlag;
	}

	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}

	public String getPTaxAmt() {
		return pTaxAmt;
	}

	public void setPTaxAmt(String taxAmt) {
		pTaxAmt = taxAmt;
	}

	public Object[][] getPtaxLoc() {
		return ptaxLoc;
	}

	public void setPtaxLoc(Object[][] ptaxLoc) {
		this.ptaxLoc = ptaxLoc;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getMonthTotAmt() {
		return monthTotAmt;
	}

	public void setMonthTotAmt(String monthTotAmt) {
		this.monthTotAmt = monthTotAmt;
	}

	public String getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(String monthCount) {
		this.monthCount = monthCount;
	}

	public String getHraPaidAmtCond() {
		return hraPaidAmtCond;
	}

	public void setHraPaidAmtCond(String hraPaidAmtCond) {
		this.hraPaidAmtCond = hraPaidAmtCond;
	}

	public String getRemainMonths() {
		return remainMonths;
	}

	public void setRemainMonths(String remainMonths) {
		this.remainMonths = remainMonths;
	}

	public String getHdChkFlag() {
		return hdChkFlag;
	}

	public void setHdChkFlag(String hdChkFlag) {
		this.hdChkFlag = hdChkFlag;
	}

	public String getTaxParaDebitCode() {
		return taxParaDebitCode;
	}

	public void setTaxParaDebitCode(String taxParaDebitCode) {
		this.taxParaDebitCode = taxParaDebitCode;
	}

	public String getRemainMonthsHidden() {
		return remainMonthsHidden;
	}

	public void setRemainMonthsHidden(String remainMonthsHidden) {
		this.remainMonthsHidden = remainMonthsHidden;
	}

	public String getEmpJoinedDate() {
		return empJoinedDate;
	}

	public void setEmpJoinedDate(String empJoinedDate) {
		this.empJoinedDate = empJoinedDate;
	}

	public String getPtaxMonthTotGross() {
		return ptaxMonthTotGross;
	}

	public void setPtaxMonthTotGross(String ptaxMonthTotGross) {
		this.ptaxMonthTotGross = ptaxMonthTotGross;
	}

	public String getEmpJoinMon() {
		return empJoinMon;
	}

	public void setEmpJoinMon(String empJoinMon) {
		this.empJoinMon = empJoinMon;
	}

	public String getEmpJoinYear() {
		return empJoinYear;
	}

	public void setEmpJoinYear(String empJoinYear) {
		this.empJoinYear = empJoinYear;
	}

	public ArrayList getChkList() {
		return chkList;
	}

	public void setChkList(ArrayList chkList) {
		this.chkList = chkList;
	}

	public String getChkShwDetail() {
		return chkShwDetail;
	}

	public void setChkShwDetail(String chkShwDetail) {
		this.chkShwDetail = chkShwDetail;
	}

	public String getPtaxVarAmt() {
		return ptaxVarAmt;
	}

	public void setPtaxVarAmt(String ptaxVarAmt) {
		this.ptaxVarAmt = ptaxVarAmt;
	}

	public String getJoinedMonth() {
		return joinedMonth;
	}

	public void setJoinedMonth(String joinedMonth) {
		this.joinedMonth = joinedMonth;
	}

	public String getHraPaidAmtCond1() {
		return hraPaidAmtCond1;
	}

	public void setHraPaidAmtCond1(String hraPaidAmtCond1) {
		this.hraPaidAmtCond1 = hraPaidAmtCond1;
	}

	public String getHraPaidAmtCond2() {
		return hraPaidAmtCond2;
	}

	public void setHraPaidAmtCond2(String hraPaidAmtCond2) {
		this.hraPaidAmtCond2 = hraPaidAmtCond2;
	}

	public String getRebateLimit() {
		return rebateLimit;
	}

	public void setRebateLimit(String rebateLimit) {
		this.rebateLimit = rebateLimit;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getCheckCurrentProcess() {
		return checkCurrentProcess;
	}

	public void setCheckCurrentProcess(String checkCurrentProcess) {
		this.checkCurrentProcess = checkCurrentProcess;
	}

	public String getDetailCheck() {
		return detailCheck;
	}

	public void setDetailCheck(String detailCheck) {
		this.detailCheck = detailCheck;
	}

	public String getDebPtaxCode() {
		return debPtaxCode;
	}

	public void setDebPtaxCode(String debPtaxCode) {
		this.debPtaxCode = debPtaxCode;
	}

	public String getNoPerqData() {
		return noPerqData;
	}

	public void setNoPerqData(String noPerqData) {
		this.noPerqData = noPerqData;
	}

	public ArrayList<Object> getEmpPerqList() {
		return empPerqList;
	}

	public void setEmpPerqList(ArrayList<Object> empPerqList) {
		this.empPerqList = empPerqList;
	}

	public String getPerqId() {
		return perqId;
	}

	public void setPerqId(String perqId) {
		this.perqId = perqId;
	}

	public String getPerqName() {
		return perqName;
	}

	public void setPerqName(String perqName) {
		this.perqName = perqName;
	}

	public String getPerqAmt() {
		return perqAmt;
	}

	public void setPerqAmt(String perqAmt) {
		this.perqAmt = perqAmt;
	}

	public String getTotalPerqAmt() {
		return totalPerqAmt;
	}

	public void setTotalPerqAmt(String totalPerqAmt) {
		this.totalPerqAmt = totalPerqAmt;
	}

	public String getTotalCreditAmt() {
		return totalCreditAmt;
	}

	public void setTotalCreditAmt(String totalCreditAmt) {
		this.totalCreditAmt = totalCreditAmt;
	}

	public String getOtherFlag() {
		return otherFlag;
	}

	public void setOtherFlag(String otherFlag) {
		this.otherFlag = otherFlag;
	}

	public String getOtherDecodeFlag() {
		return otherDecodeFlag;
	}

	public void setOtherDecodeFlag(String otherDecodeFlag) {
		this.otherDecodeFlag = otherDecodeFlag;
	}

	public String getEmpSearchId() {
		return empSearchId;
	}

	public void setEmpSearchId(String empSearchId) {
		this.empSearchId = empSearchId;
	}

	public String getEmpSearchToken() {
		return empSearchToken;
	}

	public void setEmpSearchToken(String empSearchToken) {
		this.empSearchToken = empSearchToken;
	}

	public String getEmpSearchName() {
		return empSearchName;
	}

	public void setEmpSearchName(String empSearchName) {
		this.empSearchName = empSearchName;
	}

	public boolean isSearchbutFlag() {
		return searchbutFlag;
	}

	public void setSearchbutFlag(boolean searchbutFlag) {
		this.searchbutFlag = searchbutFlag;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLeaveEncashAmt() {
		return leaveEncashAmt;
	}

	public void setLeaveEncashAmt(String leaveEncashAmt) {
		this.leaveEncashAmt = leaveEncashAmt;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getLeaveEncashAddedIncome() {
		return leaveEncashAddedIncome;
	}

	public void setLeaveEncashAddedIncome(String leaveEncashAddedIncome) {
		this.leaveEncashAddedIncome = leaveEncashAddedIncome;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPrevEmployerFlag() {
		return prevEmployerFlag;
	}

	public void setPrevEmployerFlag(String prevEmployerFlag) {
		this.prevEmployerFlag = prevEmployerFlag;
	}

	public String getPreNetTaxableIncomeAmt() {
		return preNetTaxableIncomeAmt;
	}

	public void setPreNetTaxableIncomeAmt(String preNetTaxableIncomeAmt) {
		this.preNetTaxableIncomeAmt = preNetTaxableIncomeAmt;
	}

	public String getPreTaxPaidAmt() {
		return preTaxPaidAmt;
	}

	public void setPreTaxPaidAmt(String preTaxPaidAmt) {
		this.preTaxPaidAmt = preTaxPaidAmt;
	}

	public String getPrePTaxAmt() {
		return prePTaxAmt;
	}

	public void setPrePTaxAmt(String prePTaxAmt) {
		this.prePTaxAmt = prePTaxAmt;
	}

	public String getPrePFAmt() {
		return prePFAmt;
	}

	public void setPrePFAmt(String prePFAmt) {
		this.prePFAmt = prePFAmt;
	}

	public String getTaxExemptionSection87() {
		return taxExemptionSection87;
	}

	public void setTaxExemptionSection87(String taxExemptionSection87) {
		this.taxExemptionSection87 = taxExemptionSection87;
	}

	public String getTaxExemptionIncomeLimit() {
		return taxExemptionIncomeLimit;
	}

	public void setTaxExemptionIncomeLimit(String taxExemptionIncomeLimit) {
		this.taxExemptionIncomeLimit = taxExemptionIncomeLimit;
	}

	public String getMaxTaxExemption() {
		return maxTaxExemption;
	}

	public void setMaxTaxExemption(String maxTaxExemption) {
		this.maxTaxExemption = maxTaxExemption;
	}

	

	/**
	 * @return the str
	 */
	

	
	
	
}