package org.paradyne.bean.TravelManagement;
import org.paradyne.lib.BeanBase;

public class TravelScheduleReport extends BeanBase{

	private String empId;
	private String empToken;
	private String empName;
	private String appFromDate;
	private String appToDate;
	private String journeyFromDate;
	private String journeyToDate;
	private String journeymode;	
	private String branch;
	private String branchId;
	private String dept;
	private String deptId;
	private String desg;
	private String desgId;
	private String division;
	private String divisionId;
	private String rptType;
	private String travelReportId="";
	
	
	
	
	public String getTravelReportId() {
		return travelReportId;
	}
	public void setTravelReportId(String travelReportId) {
		this.travelReportId = travelReportId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDesgId() {
		return desgId;
	}
	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getRptType() {
		return rptType;
	}
	public void setRptType(String rptType) {
		this.rptType = rptType;
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
	public String getAppFromDate() {
		return appFromDate;
	}
	public void setAppFromDate(String appFromDate) {
		this.appFromDate = appFromDate;
	}
	public String getAppToDate() {
		return appToDate;
	}
	public void setAppToDate(String appToDate) {
		this.appToDate = appToDate;
	}
	public String getJourneyFromDate() {
		return journeyFromDate;
	}
	public void setJourneyFromDate(String journeyFromDate) {
		this.journeyFromDate = journeyFromDate;
	}
	public String getJourneyToDate() {
		return journeyToDate;
	}
	public void setJourneyToDate(String journeyToDate) {
		this.journeyToDate = journeyToDate;
	}
	public String getJourneymode() {
		return journeymode;
	}
	public void setJourneymode(String journeymode) {
		this.journeymode = journeymode;
	}
 
	
	
	
}
