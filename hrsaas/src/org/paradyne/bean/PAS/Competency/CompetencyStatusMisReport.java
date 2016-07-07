package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class CompetencyStatusMisReport extends BeanBase
{
  private String fromDate;
  private String toDate;
  private String divisionId;
  private String divisionName;
  private String branchId;
  private String branchName;
  private String deptId;
  private String deptName;
  private String desgId;
  private String desgName;
  private String empToken;
  private String empId;
  private String empName;
  private String compStatus;
  private String assesmentDate;
  private String compName;
  private String compId;
  private String report = "";
  private String showCompAssesmentSelfReport;
  private String showCompAssesmentManagerReport;
  private String showCompAssesmentPendingReport;//Added for pending employee list
  private String reportAction;

  public String getShowCompAssesmentSelfReport()
  {
    return this.showCompAssesmentSelfReport;
  }
  public void setShowCompAssesmentSelfReport(String showCompAssesmentSelfReport) {
    this.showCompAssesmentSelfReport = showCompAssesmentSelfReport;
  }
  public String getShowCompAssesmentManagerReport() {
    return this.showCompAssesmentManagerReport;
  }

  public void setShowCompAssesmentManagerReport(String showCompAssesmentManagerReport) {
    this.showCompAssesmentManagerReport = showCompAssesmentManagerReport;
  }
  public String getFromDate() {
    return this.fromDate;
  }
  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }
  public String getToDate() {
    return this.toDate;
  }
  public void setToDate(String toDate) {
    this.toDate = toDate;
  }
  public String getDivisionId() {
    return this.divisionId;
  }
  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }
  public String getDivisionName() {
    return this.divisionName;
  }
  public void setDivisionName(String divisionName) {
    this.divisionName = divisionName;
  }
  public String getBranchId() {
    return this.branchId;
  }
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }
  public String getBranchName() {
    return this.branchName;
  }
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }
  public String getDeptId() {
    return this.deptId;
  }
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  public String getDeptName() {
    return this.deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public String getDesgId() {
    return this.desgId;
  }
  public void setDesgId(String desgId) {
    this.desgId = desgId;
  }
  public String getDesgName() {
    return this.desgName;
  }
  public void setDesgName(String desgName) {
    this.desgName = desgName;
  }
  public String getEmpToken() {
    return this.empToken;
  }
  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }
  public String getEmpId() {
    return this.empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return this.empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getCompStatus() {
    return this.compStatus;
  }
  public void setCompStatus(String compStatus) {
    this.compStatus = compStatus;
  }
  public String getAssesmentDate() {
    return this.assesmentDate;
  }
  public void setAssesmentDate(String assesmentDate) {
    this.assesmentDate = assesmentDate;
  }

  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
  }

  public String getReportAction() {
    return this.reportAction;
  }
  public void setReportAction(String reportAction) {
    this.reportAction = reportAction;
  }
  public String getCompName() {
    return this.compName;
  }
  public void setCompName(String compName) {
    this.compName = compName;
  }
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
  }
public String getShowCompAssesmentPendingReport() {
	return showCompAssesmentPendingReport;
}
public void setShowCompAssesmentPendingReport(
		String showCompAssesmentPendingReport) {
	this.showCompAssesmentPendingReport = showCompAssesmentPendingReport;
}
}