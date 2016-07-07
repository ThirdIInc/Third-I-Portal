package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class SalaryDetail extends BeanBase implements Serializable {
	String salaryCode = "";
	String empID = "";
	String empName = "";
	String employeeToken = "";
	String empRank = "";
	String deptName = "";
	String centerName = "";
	String payScaleCode = "";
	String payScaleName = "";
	String noGPF = "";
	String noPAN = "";
	String noCGHS = "";
	String regularMICR = "";
	String pensionMICR = "";
	String regularACCNO = "";
	String pensionACCNO = "";
	String savingMICR = "";
	String savingACCNO = "";
	String pensionable = "";
	String payBill = "";
	String payBillName = "";
	String payMode = "";
	String noDCMAF = "";
	String micrReguCode;
	String pensionMicrcode;
	String savingMICRcode;
	String esciNumber;
	String reimbursementACCNO;
	String reimbrBankCode;
	String reimbrBank;
	// for combo box
	TreeMap assetmap;
	private boolean editFlag=false;
	
	String gratuityId="";
	String pensionBank;
	String pensionBankCode="";
	String accountingCategory="";
	String costCenter="";
	String accountingCategoryCode="";
	String costCenterCode="";
	String subCostCenter="";
	String subCostCenterCode="";
	String checkTabFlag="";
	
	String sEPFflag = "";
	String sGPFflag = "";
	String sVPFflag = "";
	String sPFTflag = "";
	
	String oEPFflag = "";
	String oGPFflag = "";
	String oVPFflag = "";
	String oPFTflag = "";
	
	private String customerRefNo="";
	
	// added by ganesh 13/09/2011
	
	private String sOTflag = "";
	private String oOTflag = "";
	private String pfJoinDate = "";
	private String calEmpPfFlag = "";
	private String actualAmtEmpPf = "";
	private String qualAmtEmpPfFlag = "";
	private String calCompPfFlag = "";
	private String actualAmtCompPf = "";
	private String qualAmtCompPfFlag = "";
	private String calPensionPfFlag = "";
	private String actualAmtPensionPf = "";
	private String qualAmtPensionPfFlag = "";
	private String actualAmtEmpPfFlag = "";
	private String actualAmtCompPfFlag = "";
	private String actualAmtPensionPfFlag = "";
	private String overridePfCal = "";
	private String isNotGeneralUser = "false";
	
	private String branchName = "";
	private String ifscCode = "";
	
	private String grade = "";
	private String joiningDate = "";
	private String flag = "";
	private String uploadFileName = "";
	private String profileEmpName = "";
	
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getOverridePfCal() {
		return overridePfCal;
	}

	public void setOverridePfCal(String overridePfCal) {
		this.overridePfCal = overridePfCal;
	}

	public String getActualAmtCompPfFlag() {
		return actualAmtCompPfFlag;
	}

	public void setActualAmtCompPfFlag(String actualAmtCompPfFlag) {
		this.actualAmtCompPfFlag = actualAmtCompPfFlag;
	}

	public String getActualAmtPensionPfFlag() {
		return actualAmtPensionPfFlag;
	}

	public void setActualAmtPensionPfFlag(String actualAmtPensionPfFlag) {
		this.actualAmtPensionPfFlag = actualAmtPensionPfFlag;
	}

	public String getActualAmtEmpPfFlag() {
		return actualAmtEmpPfFlag;
	}

	public void setActualAmtEmpPfFlag(String actualAmtEmpPfFlag) {
		this.actualAmtEmpPfFlag = actualAmtEmpPfFlag;
	}

	public String getCalEmpPfFlag() {
		return calEmpPfFlag;
	}

	public void setCalEmpPfFlag(String calEmpPfFlag) {
		this.calEmpPfFlag = calEmpPfFlag;
	}

	public String getActualAmtEmpPf() {
		return actualAmtEmpPf;
	}

	public void setActualAmtEmpPf(String actualAmtEmpPf) {
		this.actualAmtEmpPf = actualAmtEmpPf;
	}

	public String getQualAmtEmpPfFlag() {
		return qualAmtEmpPfFlag;
	}

	public void setQualAmtEmpPfFlag(String qualAmtEmpPfFlag) {
		this.qualAmtEmpPfFlag = qualAmtEmpPfFlag;
	}

	public String getCalCompPfFlag() {
		return calCompPfFlag;
	}

	public void setCalCompPfFlag(String calCompPfFlag) {
		this.calCompPfFlag = calCompPfFlag;
	}

	public String getActualAmtCompPf() {
		return actualAmtCompPf;
	}

	public void setActualAmtCompPf(String actualAmtCompPf) {
		this.actualAmtCompPf = actualAmtCompPf;
	}

	public String getQualAmtCompPfFlag() {
		return qualAmtCompPfFlag;
	}

	public void setQualAmtCompPfFlag(String qualAmtCompPfFlag) {
		this.qualAmtCompPfFlag = qualAmtCompPfFlag;
	}

	public String getCalPensionPfFlag() {
		return calPensionPfFlag;
	}

	public void setCalPensionPfFlag(String calPensionPfFlag) {
		this.calPensionPfFlag = calPensionPfFlag;
	}

	public String getActualAmtPensionPf() {
		return actualAmtPensionPf;
	}

	public void setActualAmtPensionPf(String actualAmtPensionPf) {
		this.actualAmtPensionPf = actualAmtPensionPf;
	}

	public String getQualAmtPensionPfFlag() {
		return qualAmtPensionPfFlag;
	}

	public void setQualAmtPensionPfFlag(String qualAmtPensionPfFlag) {
		this.qualAmtPensionPfFlag = qualAmtPensionPfFlag;
	}

	public String getPfJoinDate() {
		return pfJoinDate;
	}

	public void setPfJoinDate(String pfJoinDate) {
		this.pfJoinDate = pfJoinDate;
	}

	public String getSOTflag() {
		return sOTflag;
	}

	public void setSOTflag(String tflag) {
		sOTflag = tflag;
	}

	public String getOOTflag() {
		return oOTflag;
	}

	public void setOOTflag(String tflag) {
		oOTflag = tflag;
	}

	public String getCheckTabFlag() {
		return checkTabFlag;
	}

	public void setCheckTabFlag(String checkTabFlag) {
		this.checkTabFlag = checkTabFlag;
	}

	public String getSubCostCenterCode() {
		return subCostCenterCode;
	}

	public void setSubCostCenterCode(String subCostCenterCode) {
		this.subCostCenterCode = subCostCenterCode;
	}

	public SalaryDetail() {

	}

	/**
	 * Constructor for SalaryDetail
	 * 
	 * @param salaryCode
	 * @param empID
	 * @param empName
	 * @param deptName
	 * @param centerName
	 * @param payScaleCode
	 * @param payScaleName
	 * @param noGPF
	 * @param noPAN
	 * @param noCGHS
	 * @param regularMICR
	 * @param pensionMICR
	 * @param regularACCNO
	 * @param pensionACCNO
	 * @param savingMICR
	 * @param savingACCNO
	 * @param pensionable
	 * @param payBill
	 * @param payMode
	 * @param noDCMAF
	 */
	public SalaryDetail(String salaryCode, String empID, String empName,
			String deptName, String centerName, String payScaleCode,
			String payScaleName, String noGPF, String noPAN, String noCGHS,
			String regularMICR, String pensionMICR, String regularACCNO,
			String pensionACCNO, String savingMICR, String savingACCNO,
			String pensionable, String payBill, String payMode, String noDCMAF,
			String payBillName, String employeeToken, String empRank,
			String pensionMicrcode, String savingMICRcode) {
		super();
		this.salaryCode = salaryCode;
		this.empID = empID;
		this.empName = empName;
		this.deptName = deptName;
		this.centerName = centerName;
		this.payScaleCode = payScaleCode;
		this.payScaleName = payScaleName;
		this.noGPF = noGPF;
		this.noPAN = noPAN;
		this.noCGHS = noCGHS;
		this.regularMICR = regularMICR;
		this.pensionMICR = pensionMICR;
		this.regularACCNO = regularACCNO;
		this.pensionACCNO = pensionACCNO;
		this.savingMICR = savingMICR;
		this.savingACCNO = savingACCNO;
		this.pensionable = pensionable;
		this.payBill = payBill;
		this.payMode = payMode;
		this.noDCMAF = noDCMAF;
		this.payBillName = payBillName;
		this.employeeToken = employeeToken;
		this.empRank = empRank;
		this.savingMICRcode = savingMICRcode;
		this.pensionMicrcode = pensionMicrcode;
	}

	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}

	/**
	 * @param payBillName
	 *            the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *            the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *            the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the noCGHS
	 */
	public String getNoCGHS() {
		return noCGHS;
	}

	/**
	 * @param noCGHS
	 *            the noCGHS to set
	 */
	public void setNoCGHS(String noCGHS) {
		this.noCGHS = noCGHS;
	}

	/**
	 * @return the noDCMAF
	 */
	public String getNoDCMAF() {
		return noDCMAF;
	}

	/**
	 * @param noDCMAF
	 *            the noDCMAF to set
	 */
	public void setNoDCMAF(String noDCMAF) {
		this.noDCMAF = noDCMAF;
	}

	/**
	 * @return the noGPF
	 */
	public String getNoGPF() {
		return noGPF;
	}

	/**
	 * @param noGPF
	 *            the noGPF to set
	 */
	public void setNoGPF(String noGPF) {
		this.noGPF = noGPF;
	}

	/**
	 * @return the noPAN
	 */
	public String getNoPAN() {
		return noPAN;
	}

	/**
	 * @param noPAN
	 *            the noPAN to set
	 */
	public void setNoPAN(String noPAN) {
		this.noPAN = noPAN;
	}

	/**
	 * @return the payBill
	 */
	public String getPayBill() {
		return payBill;
	}

	/**
	 * @param payBill
	 *            the payBill to set
	 */
	public void setPayBill(String payBill) {
		this.payBill = payBill;
	}

	/**
	 * @return the payMode
	 */
	public String getPayMode() {
		return payMode;
	}

	/**
	 * @param payMode
	 *            the payMode to set
	 */
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	/**
	 * @return the payScaleCode
	 */
	public String getPayScaleCode() {
		return payScaleCode;
	}

	/**
	 * @param payScaleCode
	 *            the payScaleCode to set
	 */
	public void setPayScaleCode(String payScaleCode) {
		this.payScaleCode = payScaleCode;
	}

	/**
	 * @return the payScaleName
	 */
	public String getPayScaleName() {
		return payScaleName;
	}

	/**
	 * @param payScaleName
	 *            the payScaleName to set
	 */
	public void setPayScaleName(String payScaleName) {
		this.payScaleName = payScaleName;
	}

	/**
	 * @return the pensionable
	 */
	public String getPensionable() {
		return pensionable;
	}

	/**
	 * @param pensionable
	 *            the pensionable to set
	 */
	public void setPensionable(String pensionable) {
		this.pensionable = pensionable;
	}

	/**
	 * @return the pensionACCNO
	 */
	public String getPensionACCNO() {
		return pensionACCNO;
	}

	/**
	 * @param pensionACCNO
	 *            the pensionACCNO to set
	 */
	public void setPensionACCNO(String pensionACCNO) {
		this.pensionACCNO = pensionACCNO;
	}

	/**
	 * @return the pensionMICR
	 */
	public String getPensionMICR() {
		return pensionMICR;
	}

	/**
	 * @param pensionMICR
	 *            the pensionMICR to set
	 */
	public void setPensionMICR(String pensionMICR) {
		this.pensionMICR = pensionMICR;
	}

	/**
	 * @return the regularACCNO
	 */
	public String getRegularACCNO() {
		return regularACCNO;
	}

	/**
	 * @param regularACCNO
	 *            the regularACCNO to set
	 */
	public void setRegularACCNO(String regularACCNO) {
		this.regularACCNO = regularACCNO;
	}

	/**
	 * @return the regularMICR
	 */
	public String getRegularMICR() {
		return regularMICR;
	}

	/**
	 * @param regularMICR
	 *            the regularMICR to set
	 */
	public void setRegularMICR(String regularMICR) {
		this.regularMICR = regularMICR;
	}

	/**
	 * @return the salaryCode
	 */
	public String getSalaryCode() {
		return salaryCode;
	}

	/**
	 * @param salaryCode
	 *            the salaryCode to set
	 */
	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
	}

	/**
	 * @return the savingACCNO
	 */
	public String getSavingACCNO() {
		return savingACCNO;
	}

	/**
	 * @param savingACCNO
	 *            the savingACCNO to set
	 */
	public void setSavingACCNO(String savingACCNO) {
		this.savingACCNO = savingACCNO;
	}

	/**
	 * @return the savingMICR
	 */
	public String getSavingMICR() {
		return savingMICR;
	}

	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}

	/**
	 * @param employeeToken
	 *            the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	/**
	 * @return the empRank
	 */
	public String getEmpRank() {
		return empRank;
	}

	/**
	 * @param empRank
	 *            the empRank to set
	 */
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	/**
	 * @return the micrReguCode
	 */
	public String getMicrReguCode() {
		return micrReguCode;
	}

	/**
	 * @param micrReguCode
	 *            the micrReguCode to set
	 */
	public void setMicrReguCode(String micrReguCode) {
		this.micrReguCode = micrReguCode;
	}

	/**
	 * @return the pensionMicrcode
	 */
	public String getPensionMicrcode() {
		return pensionMicrcode;
	}

	/**
	 * @param pensionMicrcode
	 *            the pensionMicrcode to set
	 */
	public void setPensionMicrcode(String pensionMicrcode) {
		this.pensionMicrcode = pensionMicrcode;
	}

	/**
	 * @return the savingMICRcode
	 */
	public String getSavingMICRcode() {
		return savingMICRcode;
	}

	/**
	 * @param savingMICRcode
	 *            the savingMICRcode to set
	 */
	public void setSavingMICRcode(String savingMICRcode) {
		this.savingMICRcode = savingMICRcode;
	}

	/**
	 * @return the esciNumber
	 */
	public String getEsciNumber() {
		return esciNumber;
	}

	/**
	 * @param esciNumber
	 *            the esciNumber to set
	 */
	public void setEsciNumber(String esciNumber) {
		this.esciNumber = esciNumber;
	}

	/**
	 * @return the reimbursementACCNO
	 */
	public String getReimbursementACCNO() {
		return reimbursementACCNO;
	}

	/**
	 * @param reimbursementACCNO
	 *            the reimbursementACCNO to set
	 */
	public void setReimbursementACCNO(String reimbursementACCNO) {
		this.reimbursementACCNO = reimbursementACCNO;
	}

	/**
	 * @return the reimbrBankCode
	 */
	public String getReimbrBankCode() {
		return reimbrBankCode;
	}

	/**
	 * @param reimbrBankCode
	 *            the reimbrBankCode to set
	 */
	public void setReimbrBankCode(String reimbrBankCode) {
		this.reimbrBankCode = reimbrBankCode;
	}

	/**
	 * @return the reimbrBank
	 */
	public String getReimbrBank() {
		return reimbrBank;
	}

	/**
	 * @param reimbrBank
	 *            the reimbrBank to set
	 */
	public void setReimbrBank(String reimbrBank) {
		this.reimbrBank = reimbrBank;
	}

	/**
	 * @return the assetmap
	 */
	public TreeMap getAssetmap() {
		return assetmap;
	}

	/**
	 * @param assetmap
	 *            the assetmap to set
	 */
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
	}

	/**
	 * @param savingMICR
	 *            the savingMICR to set
	 */
	public void setSavingMICR(String savingMICR) {
		this.savingMICR = savingMICR;
	}

	public String getGratuityId() {
		return gratuityId;
	}

	public void setGratuityId(String gratuityId) {
		this.gratuityId = gratuityId;
	}

	public String getPensionBankCode() {
		return pensionBankCode;
	}

	public void setPensionBankCode(String pensionBankCode) {
		this.pensionBankCode = pensionBankCode;
	}

	public String getAccountingCategory() {
		return accountingCategory;
	}

	public void setAccountingCategory(String accountingCategory) {
		this.accountingCategory = accountingCategory;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getSubCostCenter() {
		return subCostCenter;
	}

	public void setSubCostCenter(String subCostCenter) {
		this.subCostCenter = subCostCenter;
	}

	public String getPensionBank() {
		return pensionBank;
	}

	public void setPensionBank(String pensionBank) {
		this.pensionBank = pensionBank;
	}

	public String getAccountingCategoryCode() {
		return accountingCategoryCode;
	}

	public void setAccountingCategoryCode(String accountingCategoryCode) {
		this.accountingCategoryCode = accountingCategoryCode;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getSEPFflag() {
		return sEPFflag;
	}

	public void setSEPFflag(String fflag) {
		sEPFflag = fflag;
	}

	public String getSGPFflag() {
		return sGPFflag;
	}

	public void setSGPFflag(String fflag) {
		sGPFflag = fflag;
	}

	public String getSVPFflag() {
		return sVPFflag;
	}

	public void setSVPFflag(String fflag) {
		sVPFflag = fflag;
	}

	public String getSPFTflag() {
		return sPFTflag;
	}

	public void setSPFTflag(String tflag) {
		sPFTflag = tflag;
	}

	public String getOEPFflag() {
		return oEPFflag;
	}

	public void setOEPFflag(String fflag) {
		oEPFflag = fflag;
	}

	public String getOGPFflag() {
		return oGPFflag;
	}

	public void setOGPFflag(String fflag) {
		oGPFflag = fflag;
	}

	public String getOVPFflag() {
		return oVPFflag;
	}

	public void setOVPFflag(String fflag) {
		oVPFflag = fflag;
	}

	public String getOPFTflag() {
		return oPFTflag;
	}

	public void setOPFTflag(String tflag) {
		oPFTflag = tflag;
	}

	public String getCustomerRefNo() {
		return customerRefNo;
	}

	public void setCustomerRefNo(String customerRefNo) {
		this.customerRefNo = customerRefNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getProfileEmpName() {
		return profileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	

}