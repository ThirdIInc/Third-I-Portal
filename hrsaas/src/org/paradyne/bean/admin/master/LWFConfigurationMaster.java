package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class LWFConfigurationMaster extends BeanBase {

	private String myPage = null;
	private String sTabOrder = null;
	private String sMode = null;
	
	/* Tab-1 */
	private String sLwfApplicableOrgChk = null;
	private String sLwfApplicableOrg = null;
	private String sLwfDebitHead = null;
	private String sLwfDebitHeadCode = null;
	private String sLwfCreditHead = null;
	private String sLwfCreditHeadCode = null;
	
	/* Tab-2 */
	private String sEffectiveDate = null;
	private String sState = null;
	private String sStateCode = null;
	private String sDeductionMonth = null;
	private String sDeductionMonthCode = null;

	
	private List lstSlabDefinition = new ArrayList();
	private int iSrNo;
	private String sBasicFrm = null;
	private String sBasicTo = null;
	private String sEmployeeContribution = null;
	private String sEmployerContribution = null;
	private String sSlabChk = null;
	private int count1;
	
	/* Tab-3 */
	private String sApplicableAll = null;
	private String sDivCode = null;
	private String sDivName = null;
	private String sBranch = null;
	private String sBranchName = null;
	private String sDepartment = null;
	private String sDepartmentName = null;
	private String sDesignation = null;
	private String sDesignationName = null;
	private String sEmpType = null;
	private String sEmpTypeId = null;
	private String sEmpName = null;
	private String sEmpCode = null;
	
	private String iSrNo_EmpLst;
	private String sEmployeeId = null;
	private String sEmployeeName = null;
	private List lstEmpList = new ArrayList();
	private String sLwfApplicableChk = null;
	private String isLwfApplicable = null;
	private int count3;
	private String sConfigAllChk = null;

	private String sLwfApplicable = null;
	private String sLwfNotApplicable = null;
	
	private String sLwfCode = null;
	
	private String modeLength="false";
	private String totalRecords="";
	  ArrayList iteratorlist;
	  private String lwfID = "";
	  private String configureState = "";
	  private String effectiveFrom = "";
	  private String configureCode = "";
	  private String hiddencode;
	  private String hiddenChechfrmId = "";
	  private String uploadFileName = "";
	  
	  private String status = "Fail";
		private String note = "";
	  private String hiddenChechAllEmpfrmId = "";
	  private String dataPath;
	  private String grade = "";
	  private String designation = "";
	  boolean viewUploadFileFlag = false;
	  private String totalEmpDiv = "";
	  private String totalEmpLWFDiv = "";
	  
	public String getTotalEmpDiv() {
		return totalEmpDiv;
	}

	public void setTotalEmpDiv(String totalEmpDiv) {
		this.totalEmpDiv = totalEmpDiv;
	}

	public String getTotalEmpLWFDiv() {
		return totalEmpLWFDiv;
	}

	public void setTotalEmpLWFDiv(String totalEmpLWFDiv) {
		this.totalEmpLWFDiv = totalEmpLWFDiv;
	}

	public boolean isViewUploadFileFlag() {
		return viewUploadFileFlag;
	}

	public void setViewUploadFileFlag(boolean viewUploadFileFlag) {
		this.viewUploadFileFlag = viewUploadFileFlag;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getHiddenChechAllEmpfrmId() {
		return hiddenChechAllEmpfrmId;
	}

	public void setHiddenChechAllEmpfrmId(String hiddenChechAllEmpfrmId) {
		this.hiddenChechAllEmpfrmId = hiddenChechAllEmpfrmId;
	}

	public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}

	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}

	public String getSLwfApplicableOrg() {
		return sLwfApplicableOrg;
	}

	public void setSLwfApplicableOrg(String lwfApplicableOrg) {
		sLwfApplicableOrg = lwfApplicableOrg;
	}

	public String getSLwfDebitHead() {
		return sLwfDebitHead;
	}

	public void setSLwfDebitHead(String lwfDebitHead) {
		sLwfDebitHead = lwfDebitHead;
	}

	public String getSEffectiveDate() {
		return sEffectiveDate;
	}

	public void setSEffectiveDate(String effectiveDate) {
		sEffectiveDate = effectiveDate;
	}

	public String getSState() {
		return sState;
	}

	public void setSState(String state) {
		sState = state;
	}

	public String getSDeductionMonth() {
		return sDeductionMonth;
	}

	public void setSDeductionMonth(String deductionMonth) {
		sDeductionMonth = deductionMonth;
	}

	public String getSBasicFrm() {
		return sBasicFrm;
	}

	public void setSBasicFrm(String basicFrm) {
		sBasicFrm = basicFrm;
	}

	public String getSBasicTo() {
		return sBasicTo;
	}

	public void setSBasicTo(String basicTo) {
		sBasicTo = basicTo;
	}

	public String getSEmployeeContribution() {
		return sEmployeeContribution;
	}

	public void setSEmployeeContribution(String employeeContribution) {
		sEmployeeContribution = employeeContribution;
	}

	public String getSEmployerContribution() {
		return sEmployerContribution;
	}

	public void setSEmployerContribution(String employerContribution) {
		sEmployerContribution = employerContribution;
	}

	public String getSApplicableAll() {
		return sApplicableAll;
	}

	public void setSApplicableAll(String applicableAll) {
		sApplicableAll = applicableAll;
	}

	public String getSLwfApplicable() {
		return sLwfApplicable;
	}

	public void setSLwfApplicable(String lwfApplicable) {
		sLwfApplicable = lwfApplicable;
	}

	public String getSLwfNotApplicable() {
		return sLwfNotApplicable;
	}

	public void setSLwfNotApplicable(String lwfNotApplicable) {
		sLwfNotApplicable = lwfNotApplicable;
	}

	public List getLstSlabDefinition() {
		return lstSlabDefinition;
	}

	public void setLstSlabDefinition(List lstSlabDefinition) {
		this.lstSlabDefinition = lstSlabDefinition;
	}

	public int getISrNo() {
		return iSrNo;
	}

	public void setISrNo(int srNo) {
		iSrNo = srNo;
	}

	public String getSSlabChk() {
		return sSlabChk;
	}

	public void setSSlabChk(String slabChk) {
		sSlabChk = slabChk;
	}

	public String getISrNo_EmpLst() {
		return iSrNo_EmpLst;
	}

	public void setISrNo_EmpLst(String srNo_EmpLst) {
		iSrNo_EmpLst = srNo_EmpLst;
	}

	public String getSEmployeeId() {
		return sEmployeeId;
	}

	public void setSEmployeeId(String employeeId) {
		sEmployeeId = employeeId;
	}

	public String getSEmployeeName() {
		return sEmployeeName;
	}

	public void setSEmployeeName(String employeeName) {
		sEmployeeName = employeeName;
	}

	public String getIsLwfApplicable() {
		return isLwfApplicable;
	}

	public void setIsLwfApplicable(String isLwfApplicable) {
		this.isLwfApplicable = isLwfApplicable;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public String getSDivCode() {
		return sDivCode;
	}

	public void setSDivCode(String divCode) {
		sDivCode = divCode;
	}

	public String getSBranch() {
		return sBranch;
	}

	public void setSBranch(String branch) {
		sBranch = branch;
	}

	public String getSDepartment() {
		return sDepartment;
	}

	public void setSDepartment(String department) {
		sDepartment = department;
	}

	public String getSDesignation() {
		return sDesignation;
	}

	public void setSDesignation(String designation) {
		sDesignation = designation;
	}

	public String getSEmpType() {
		return sEmpType;
	}

	public void setSEmpType(String empType) {
		sEmpType = empType;
	}

	public String getSEmpCode() {
		return sEmpCode;
	}

	public void setSEmpCode(String empCode) {
		sEmpCode = empCode;
	}

	public String getSEmpName() {
		return sEmpName;
	}

	public void setSEmpName(String empName) {
		sEmpName = empName;
	}

	public String getSEmpTypeId() {
		return sEmpTypeId;
	}

	public void setSEmpTypeId(String empTypeId) {
		sEmpTypeId = empTypeId;
	}

	public String getSDivName() {
		return sDivName;
	}

	public void setSDivName(String divName) {
		sDivName = divName;
	}

	public String getSBranchName() {
		return sBranchName;
	}

	public void setSBranchName(String branchName) {
		sBranchName = branchName;
	}

	public String getSDepartmentName() {
		return sDepartmentName;
	}

	public void setSDepartmentName(String departmentName) {
		sDepartmentName = departmentName;
	}

	public String getSDesignationName() {
		return sDesignationName;
	}

	public void setSDesignationName(String designationName) {
		sDesignationName = designationName;
	}

	public List getLstEmpList() {
		return lstEmpList;
	}

	public void setLstEmpList(List lstEmpList) {
		this.lstEmpList = lstEmpList;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getSTabOrder() {
		return sTabOrder;
	}

	public void setSTabOrder(String tabOrder) {
		sTabOrder = tabOrder;
	}

	public String getSDeductionMonthCode() {
		return sDeductionMonthCode;
	}

	public void setSDeductionMonthCode(String deductionMonthCode) {
		sDeductionMonthCode = deductionMonthCode;
	}

	public String getSStateCode() {
		return sStateCode;
	}

	public void setSStateCode(String stateCode) {
		sStateCode = stateCode;
	}

	public String getSLwfCreditHead() {
		return sLwfCreditHead;
	}

	public void setSLwfCreditHead(String lwfCreditHead) {
		sLwfCreditHead = lwfCreditHead;
	}

	public String getSLwfDebitHeadCode() {
		return sLwfDebitHeadCode;
	}

	public void setSLwfDebitHeadCode(String lwfDebitHeadCode) {
		sLwfDebitHeadCode = lwfDebitHeadCode;
	}

	public String getSLwfCreditHeadCode() {
		return sLwfCreditHeadCode;
	}

	public void setSLwfCreditHeadCode(String lwfCreditHeadCode) {
		sLwfCreditHeadCode = lwfCreditHeadCode;
	}

	public String getSLwfApplicableOrgChk() {
		return sLwfApplicableOrgChk;
	}

	public void setSLwfApplicableOrgChk(String lwfApplicableOrgChk) {
		sLwfApplicableOrgChk = lwfApplicableOrgChk;
	}

	public String getSLwfCode() {
		return sLwfCode;
	}

	public void setSLwfCode(String lwfCode) {
		sLwfCode = lwfCode;
	}

	public String getSMode() {
		return sMode;
	}

	public void setSMode(String mode) {
		sMode = mode;
	}

	public String getSLwfApplicableChk() {
		return sLwfApplicableChk;
	}

	public void setSLwfApplicableChk(String lwfApplicableChk) {
		sLwfApplicableChk = lwfApplicableChk;
	}

	public String getSConfigAllChk() {
		return sConfigAllChk;
	}

	public void setSConfigAllChk(String configAllChk) {
		sConfigAllChk = configAllChk;
	}

	public String getLwfID() {
		return lwfID;
	}

	public void setLwfID(String lwfID) {
		this.lwfID = lwfID;
	}

	public String getConfigureState() {
		return configureState;
	}

	public void setConfigureState(String configureState) {
		this.configureState = configureState;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getConfigureCode() {
		return configureCode;
	}

	public void setConfigureCode(String configureCode) {
		this.configureCode = configureCode;
	}
}
