/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0540
 *
 */
public class MyRequisition extends BeanBase {
	private String divName="";
	private String divId="";
	private String branchName="";
	private String branchId="";
	private String deptName="";
	private String deptId="";
	private String noData="false";
	private String reqCode;
	private String position;
	private String appliedBy;
	private String hiringMgr;
	private String publishDate;
	private String noOfVacancies;
	private String requiredDate;
	private String vacanDtlCode;
	private String reqName;
	private String appliedEmpId; 
	private String hiringEmpId;
	private String listLength="0";
	private String myPage="";
	private String fakeRankName = "";
	//private String recruitmentInternal;
	//private String recruitmentExternal;
	
	private ArrayList list=null;
	private String  requisitionId="";
	private String  requisitionName="";
	private String  hrManagerId="";
	private String  managerName="";
	private String  positionId="";
	private String  positionName="";
	private String pubfDate="";
	private String pubtDate="";
	private String totalRecords="";
	private String modeLength="";
	private String myStatus="";
	private String searchFlag="false";
	private String  applyFilterFlag="false";
	private boolean divisionFlag=false;
	private boolean branchFlag=false;
	private boolean departmentFlag=false;
	private boolean hrManagerFlag=false;
	private boolean fromDateFlag=false;
	private boolean toDateFlag=false;
	private boolean clearFlag=false;
	private boolean positionFlag=false;
	private boolean enableFilterValue=false;
	private boolean showFilterValue=false;
	private boolean requstionFlag=false;
	private boolean pubFromDateFlag=false;
	private boolean pubToDateFlag=false;
	private String ChkSerch="";
	public String getChkSerch() {
		return ChkSerch;
	}

	public void setChkSerch(String chkSerch) {
		ChkSerch = chkSerch;
	}

	public boolean isDivisionFlag() {
		return divisionFlag;
	}

	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}

	public boolean isBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}

	public boolean isDepartmentFlag() {
		return departmentFlag;
	}

	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}

	public boolean isHrManagerFlag() {
		return hrManagerFlag;
	}

	public void setHrManagerFlag(boolean hrManagerFlag) {
		this.hrManagerFlag = hrManagerFlag;
	}

	public boolean isFromDateFlag() {
		return fromDateFlag;
	}

	public void setFromDateFlag(boolean fromDateFlag) {
		this.fromDateFlag = fromDateFlag;
	}

	public boolean isToDateFlag() {
		return toDateFlag;
	}

	public void setToDateFlag(boolean toDateFlag) {
		this.toDateFlag = toDateFlag;
	}

	public boolean isClearFlag() {
		return clearFlag;
	}

	public void setClearFlag(boolean clearFlag) {
		this.clearFlag = clearFlag;
	}

	public boolean isPositionFlag() {
		return positionFlag;
	}

	public void setPositionFlag(boolean positionFlag) {
		this.positionFlag = positionFlag;
	}

	public boolean isEnableFilterValue() {
		return enableFilterValue;
	}

	public void setEnableFilterValue(boolean enableFilterValue) {
		this.enableFilterValue = enableFilterValue;
	}

	public boolean isShowFilterValue() {
		return showFilterValue;
	}

	public void setShowFilterValue(boolean showFilterValue) {
		this.showFilterValue = showFilterValue;
	}

	public boolean isRequstionFlag() {
		return requstionFlag;
	}

	public void setRequstionFlag(boolean requstionFlag) {
		this.requstionFlag = requstionFlag;
	}

	public boolean isPubFromDateFlag() {
		return pubFromDateFlag;
	}

	public void setPubFromDateFlag(boolean pubFromDateFlag) {
		this.pubFromDateFlag = pubFromDateFlag;
	}

	public boolean isPubToDateFlag() {
		return pubToDateFlag;
	}

	public void setPubToDateFlag(boolean pubToDateFlag) {
		this.pubToDateFlag = pubToDateFlag;
	}

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
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

	public String getPubfDate() {
		return pubfDate;
	}

	public void setPubfDate(String pubfDate) {
		this.pubfDate = pubfDate;
	}

	public String getPubtDate() {
		return pubtDate;
	}

	public void setPubtDate(String pubtDate) {
		this.pubtDate = pubtDate;
	}

	public String getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getRequisitionName() {
		return requisitionName;
	}

	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}

	public String getHrManagerId() {
		return hrManagerId;
	}

	public void setHrManagerId(String hrManagerId) {
		this.hrManagerId = hrManagerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}

	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}

	/**
	 * @return the divId
	 */
	public String getDivId() {
		return divId;
	}

	/**
	 * @param divId the divId to set
	 */
	public void setDivId(String divId) {
		this.divId = divId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the reqCode
	 */
	public String getReqCode() {
		return reqCode;
	}

	/**
	 * @param reqCode the reqCode to set
	 */
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the appliedBy
	 */
	public String getAppliedBy() {
		return appliedBy;
	}

	/**
	 * @param appliedBy the appliedBy to set
	 */
	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	/**
	 * @return the hiringMgr
	 */
	public String getHiringMgr() {
		return hiringMgr;
	}

	/**
	 * @param hiringMgr the hiringMgr to set
	 */
	public void setHiringMgr(String hiringMgr) {
		this.hiringMgr = hiringMgr;
	}

	/**
	 * @return the noOfVacancies
	 */
	public String getNoOfVacancies() {
		return noOfVacancies;
	}

	/**
	 * @param noOfVacancies the noOfVacancies to set
	 */
	public void setNoOfVacancies(String noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	/**
	 * @return the requiredDate
	 */
	public String getRequiredDate() {
		return requiredDate;
	}

	/**
	 * @param requiredDate the requiredDate to set
	 */
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	/**
	 * @return the vacanDtlCode
	 */
	public String getVacanDtlCode() {
		return vacanDtlCode;
	}

	/**
	 * @param vacanDtlCode the vacanDtlCode to set
	 */
	public void setVacanDtlCode(String vacanDtlCode) {
		this.vacanDtlCode = vacanDtlCode;
	}

	/**
	 * @return the reqName
	 */
	public String getReqName() {
		return reqName;
	}

	/**
	 * @param reqName the reqName to set
	 */
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	/**
	 * @return the appliedEmpId
	 */
	public String getAppliedEmpId() {
		return appliedEmpId;
	}

	/**
	 * @param appliedEmpId the appliedEmpId to set
	 */
	public void setAppliedEmpId(String appliedEmpId) {
		this.appliedEmpId = appliedEmpId;
	}

	/**
	 * @return the hiringEmpId
	 */
	public String getHiringEmpId() {
		return hiringEmpId;
	}

	/**
	 * @param hiringEmpId the hiringEmpId to set
	 */
	public void setHiringEmpId(String hiringEmpId) {
		this.hiringEmpId = hiringEmpId;
	}

	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}

	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	/**
	 * @return the list
	 */
	public ArrayList getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList list) {
		this.list = list;
	}

	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getApplyFilterFlag() {
		return applyFilterFlag;
	}

	public void setApplyFilterFlag(String applyFilterFlag) {
		this.applyFilterFlag = applyFilterFlag;
	}

	public String getFakeRankName() {
		return fakeRankName;
	}

	public void setFakeRankName(String fakeRankName) {
		this.fakeRankName = fakeRankName;
	}
}
