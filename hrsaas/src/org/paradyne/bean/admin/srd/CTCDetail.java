package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Prajakta.Bhandare
 * @date 11 Jan 2013
 */
public class CTCDetail extends BeanBase implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<CTCDetail> ctcList = null;

	private String empId = "";
	private String empName = "";
	private String empToken = "";
	private String empCenter = "";
	private String empRank = "";
	private String salGradeId = "";
	private String salGradeName = "";
	private String empGradeId = "";
	private String empGradeName = "";
	private String empDeptName = "";
	private String empDeptId = "";
	private String grsAmt = "";
	private String empAccNo = "";
	private String empPANNo = "";
	private String empJoinDate = "";
	private String pfFlag = "";
	private String totalAmt = "";
	private String annualAmt = "";
	private String ctc = "";
	private String ctcNameItt = "";
	private String ctcPeriodItt = "";
	private String ctcAmountItt = "";
	private String ctcNameIdItt = "";
	private String formula = "";
	private String formulaId = "";
	private String incrementPeriod = "";
	private String uploadFileName = "";
	private String profileEmpName = "";
	private String isNotGeneralUser = "";
	private String empStatus = "";
	private String ctcNameId = "";
	private String ctcName = "";
	private String ctcPeriod = "";
	private String ctcAmount = "";
	private String annualAmtPer = "";
	private String noData = "";
	private boolean editFlag = false;
	private boolean editDetail = false;
	HashMap incrementHistoryMap;
	ArrayList<CTCDetail> salaryHdr;

	/**
	 * @return salaryHdr
	 */
	public ArrayList<CTCDetail> getSalaryHdr() {
		return salaryHdr;
	}

	/**
	 * @param salaryHdr
	 *            the salaryHdr to set
	 */
	public void setSalaryHdr(ArrayList<CTCDetail> salaryHdr) {
		this.salaryHdr = salaryHdr;
	}

	/**
	 * @return ctcList
	 */
	public ArrayList<CTCDetail> getCtcList() {
		return ctcList;
	}

	/**
	 * @param ctcList
	 *            the ctcList to set
	 */
	public void setCtcList(ArrayList<CTCDetail> ctcList) {
		this.ctcList = ctcList;
	}

	/**
	 * @return empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}

	/**
	 * @param profileEmpName
	 *            the profileEmpName to set
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	/**
	 * @return isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	/**
	 * @param isNotGeneralUser
	 *            the isNotGeneralUser to set
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	/**
	 * @return editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 *            the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return incrementHistoryMap
	 */
	public HashMap getIncrementHistoryMap() {
		return incrementHistoryMap;
	}

	/**
	 * @param incrementHistoryMap
	 *            the incrementHistoryMap to set
	 */
	public void setIncrementHistoryMap(HashMap incrementHistoryMap) {
		this.incrementHistoryMap = incrementHistoryMap;
	}

	/**
	 * @return empCenter
	 */
	public String getEmpCenter() {
		return empCenter;
	}

	/**
	 * @param empCenter
	 *            the empCenter to set
	 */
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}

	/**
	 * @return empRank
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
	 * @return salGradeId
	 */
	public String getSalGradeId() {
		return salGradeId;
	}

	/**
	 * @param salGradeId
	 *            the salGradeId to set
	 */
	public void setSalGradeId(String salGradeId) {
		this.salGradeId = salGradeId;
	}

	/**
	 * @return salGradeName
	 */
	public String getSalGradeName() {
		return salGradeName;
	}

	/**
	 * @param salGradeName
	 *            the salGradeName to set
	 */
	public void setSalGradeName(String salGradeName) {
		this.salGradeName = salGradeName;
	}

	/**
	 * @return empGradeId
	 */
	public String getEmpGradeId() {
		return empGradeId;
	}

	/**
	 * @param empGradeId
	 *            the empGradeId to set
	 */
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}

	/**
	 * @return empGradeName
	 */
	public String getEmpGradeName() {
		return empGradeName;
	}

	/**
	 * @param empGradeName
	 *            the empGradeName to set
	 */
	public void setEmpGradeName(String empGradeName) {
		this.empGradeName = empGradeName;
	}

	/**
	 * @return empDeptName
	 */
	public String getEmpDeptName() {
		return empDeptName;
	}

	/**
	 * @param empDeptName
	 *            the empDeptName to set
	 */
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}

	/**
	 * @return empDeptId
	 */
	public String getEmpDeptId() {
		return empDeptId;
	}

	/**
	 * @param empDeptId
	 *            the empDeptId to set
	 */
	public void setEmpDeptId(String empDeptId) {
		this.empDeptId = empDeptId;
	}

	/**
	 * @return grsAmt
	 */
	public String getGrsAmt() {
		return grsAmt;
	}

	/**
	 * @param grsAmt
	 *            the grsAmt to set
	 */
	public void setGrsAmt(String grsAmt) {
		this.grsAmt = grsAmt;
	}

	/**
	 * @return empAccNo
	 */
	public String getEmpAccNo() {
		return empAccNo;
	}

	/**
	 * @param empAccNo
	 *            the empAccNo to set
	 */
	public void setEmpAccNo(String empAccNo) {
		this.empAccNo = empAccNo;
	}

	/**
	 * @return empJoinDate
	 */
	public String getEmpJoinDate() {
		return empJoinDate;
	}

	/**
	 * @param empJoinDate
	 *            the empJoinDate to set
	 */
	public void setEmpJoinDate(String empJoinDate) {
		this.empJoinDate = empJoinDate;
	}

	/**
	 * @return pfFlag
	 */
	public String getPfFlag() {
		return pfFlag;
	}

	/**
	 * @param pfFlag
	 *            the pfFlag to set
	 */
	public void setPfFlag(String pfFlag) {
		this.pfFlag = pfFlag;
	}

	/**
	 * @return empPANNo
	 */
	public String getEmpPANNo() {
		return empPANNo;
	}

	/**
	 * @param empPANNo
	 *            the empPANNo to set
	 */
	public void setEmpPANNo(String empPANNo) {
		this.empPANNo = empPANNo;
	}

	/**
	 * @return empName
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
	 * @return empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return totalAmt
	 */
	public String getTotalAmt() {
		return totalAmt;
	}

	/**
	 * @param totalAmt
	 *            the totalAmt to set
	 */
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * @return annualAmt
	 */
	public String getAnnualAmt() {
		return annualAmt;
	}

	/**
	 * @param annualAmt
	 *            the annualAmt to set
	 */
	public void setAnnualAmt(String annualAmt) {
		this.annualAmt = annualAmt;
	}

	/**
	 * @return ctc
	 */
	public String getCtc() {
		return ctc;
	}

	/**
	 * @param ctc
	 *            the ctc to set
	 */
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	/**
	 * @return ctcNameItt
	 */
	public String getCtcNameItt() {
		return ctcNameItt;
	}

	/**
	 * @param ctcNameItt
	 *            the ctcNameItt to set
	 */
	public void setCtcNameItt(String ctcNameItt) {
		this.ctcNameItt = ctcNameItt;
	}

	/**
	 * @return ctcPeriodItt
	 */
	public String getCtcPeriodItt() {
		return ctcPeriodItt;
	}

	/**
	 * @param ctcPeriodItt
	 *            the ctcPeriodItt to set
	 */
	public void setCtcPeriodItt(String ctcPeriodItt) {
		this.ctcPeriodItt = ctcPeriodItt;
	}

	/**
	 * @return ctcAmountItt
	 */
	public String getCtcAmountItt() {
		return ctcAmountItt;
	}

	/**
	 * @param ctcAmountItt
	 *            the ctcAmountItt to set
	 */
	public void setCtcAmountItt(String ctcAmountItt) {
		this.ctcAmountItt = ctcAmountItt;
	}

	/**
	 * @return ctcNameIdItt
	 */
	public String getCtcNameIdItt() {
		return ctcNameIdItt;
	}

	/**
	 * @param ctcNameIdItt
	 *            the ctcNameIdItt to set
	 */
	public void setCtcNameIdItt(String ctcNameIdItt) {
		this.ctcNameIdItt = ctcNameIdItt;
	}

	/**
	 * @return formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @param formula
	 *            the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * @return empStatus
	 */
	public String getEmpStatus() {
		return empStatus;
	}

	/**
	 * @param empStatus
	 *            the empStatus to set
	 */
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	/**
	 * @return formulaId
	 */
	public String getFormulaId() {
		return formulaId;
	}

	/**
	 * @param formulaId
	 *            THE formulaId TOSET
	 */
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	/**
	 * @return incrementPeriod
	 */
	public String getIncrementPeriod() {
		return incrementPeriod;
	}

	/**
	 * @param incrementPeriod
	 *            the incrementPeriod to set
	 */
	public void setIncrementPeriod(String incrementPeriod) {
		this.incrementPeriod = incrementPeriod;
	}

	/**
	 * @return ctcNameId
	 */
	public String getCtcNameId() {
		return ctcNameId;
	}

	/**
	 * @param ctcNameId
	 *            the ctcNameId to set
	 */
	public void setCtcNameId(String ctcNameId) {
		this.ctcNameId = ctcNameId;
	}

	/**
	 * @return ctcName
	 */
	public String getCtcName() {
		return ctcName;
	}

	/**
	 * @param ctcName
	 *            the ctcName to set
	 */
	public void setCtcName(String ctcName) {
		this.ctcName = ctcName;
	}

	/**
	 * @return ctcPeriod
	 */
	public String getCtcPeriod() {
		return ctcPeriod;
	}

	/**
	 * @param ctcPeriod
	 *            the ctcPeriod to set
	 */
	public void setCtcPeriod(String ctcPeriod) {
		this.ctcPeriod = ctcPeriod;
	}

	/**
	 * @return ctcAmount
	 */
	public String getCtcAmount() {
		return ctcAmount;
	}

	/**
	 * @param ctcAmount
	 *            the ctcAmount to set
	 */
	public void setCtcAmount(String ctcAmount) {
		this.ctcAmount = ctcAmount;
	}

	/**
	 * @return annualAmtPer
	 */
	public String getAnnualAmtPer() {
		return annualAmtPer;
	}

	/**
	 * @param annualAmtPer
	 *            the annualAmtPer to set
	 */
	public void setAnnualAmtPer(String annualAmtPer) {
		this.annualAmtPer = annualAmtPer;
	}

	/**
	 * @return noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData
	 *            the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return editDetail
	 */
	public boolean isEditDetail() {
		return editDetail;
	}

	/**
	 * @param editDetail
	 * the editDetail to set
	 */
	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

}
