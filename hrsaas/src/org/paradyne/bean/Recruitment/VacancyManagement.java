/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class VacancyManagement extends BeanBase {
	private String searchFlag="false";
	private String formAction="";
	private String statusKey;
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
	private String reqDate;
	private String noOfVacancies;
	private String requiredDate;
	private String vacanDtlCode;
	private String reqName;
	private String appliedEmpId; 
	private String hiringEmpId;
	private String listLength="0";
	private String publishButtonFlag="false";
	private String closeVacancyFlag="false";
	private String recruitmentInternal;
	private String recruitmentExternal;
	private String myPage="";
	private String vacancy="O";//Added by Pradeep on Date:16-03-2009
	private String show="";
	private String fDate="";
	private String tDate="";
	private String positionId="";
	private String positionName="";
	private String hrManagerId="";
	private String managerName="";
	private String totalRecords="";
	private String modeLength="false";
	private String selectname="";
	private ArrayList list=null;
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
	private boolean  appFilter=false;
	private String applyFilterFlag="false";
	private String rePublishFlag = "false";
	
	private String ChkSerch="";
	
	private String hiddenColumns="";
	private String reportType="";
	private String reportTitle="";
	private String ctcFlag="";
	private String salaryFlag="";
	private String exportAll="";
	private String  dateFormatSelect = "";
	
	private String division = "";
	private String branch = "";
	private String fromDate="";
	private String toDate="";
	private String vacancyPublishCode = "";
	
	public String getVacancyPublishCode() {
		return vacancyPublishCode;
	}
	public void setVacancyPublishCode(String vacancyPublishCode) {
		this.vacancyPublishCode = vacancyPublishCode;
	}
	public String getDateFormatSelect() {
		return dateFormatSelect;
	}
	public void setDateFormatSelect(String dateFormatSelect) {
		this.dateFormatSelect = dateFormatSelect;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getSalaryFlag() {
		return salaryFlag;
	}
	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}
	public String getCtcFlag() {
		return ctcFlag;
	}
	public void setCtcFlag(String ctcFlag) {
		this.ctcFlag = ctcFlag;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getHiddenColumns() {
		return hiddenColumns;
	}
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}
	public String getChkSerch() {
		return ChkSerch;
	}
	public void setChkSerch(String chkSerch) {
		ChkSerch = chkSerch;
	}
	public boolean isAppFilter() {
		return appFilter;
	}
	public void setAppFilter(boolean appFilter) {
		this.appFilter = appFilter;
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
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAppliedBy() {
		return appliedBy;
	}
	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}
	public String getHiringMgr() {
		return hiringMgr;
	}
	public void setHiringMgr(String hiringMgr) {
		this.hiringMgr = hiringMgr;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getNoOfVacancies() {
		return noOfVacancies;
	}
	public void setNoOfVacancies(String noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getVacanDtlCode() {
		return vacanDtlCode;
	}
	public void setVacanDtlCode(String vacanDtlCode) {
		this.vacanDtlCode = vacanDtlCode;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getAppliedEmpId() {
		return appliedEmpId;
	}
	public void setAppliedEmpId(String appliedEmpId) {
		this.appliedEmpId = appliedEmpId;
	}
	public String getHiringEmpId() {
		return hiringEmpId;
	}
	public void setHiringEmpId(String hiringEmpId) {
		this.hiringEmpId = hiringEmpId;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getPublishButtonFlag() {
		return publishButtonFlag;
	}
	public void setPublishButtonFlag(String publishButtonFlag) {
		this.publishButtonFlag = publishButtonFlag;
	}
	public String getRecruitmentInternal() {
		return recruitmentInternal;
	}
	public void setRecruitmentInternal(String recruitmentInternal) {
		this.recruitmentInternal = recruitmentInternal;
	}
	public String getRecruitmentExternal() {
		return recruitmentExternal;
	}
	public void setRecruitmentExternal(String recruitmentExternal) {
		this.recruitmentExternal = recruitmentExternal;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getVacancy() {
		return vacancy;
	}
	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getFormAction() {
		return formAction;
	}
	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}
	public String getStatusKey() {
		return statusKey;
	}
	public void setStatusKey(String statusKey) {
		this.statusKey = statusKey;
	}
	public String getFDate() {
		return fDate;
	}
	public void setFDate(String date) {
		fDate = date;
	}
	public String getTDate() {
		return tDate;
	}
	public void setTDate(String date) {
		tDate = date;
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
	
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
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
	public String getRePublishFlag() {
		return rePublishFlag;
	}
	public void setRePublishFlag(String rePublishFlag) {
		this.rePublishFlag = rePublishFlag;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCloseVacancyFlag() {
		return closeVacancyFlag;
	}
	public void setCloseVacancyFlag(String closeVacancyFlag) {
		this.closeVacancyFlag = closeVacancyFlag;
	}
	 

}
