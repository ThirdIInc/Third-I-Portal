/**
 * 
 */
package org.paradyne.bean.PAS;

import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 *
 */
public class AppraisalStatusReport extends BeanBase {
	
	private String apprCode="";
	private String apprName="";
	private String report="";
	private String divCode="";
	private String divName="";
	private String deptCode="";
	private String deptName="";
	private String desgCode="";
	private String desgName="";
	private String branchCode="";
	private String branchName="";
	private LinkedHashMap phaseList = null;
	private String phaseStatus="";
	private String phaseType="";
	private String startDate = "";
	private String endDate = "";
	private String empCode = "";
	private String empName = "";
	private String empId = "";
	
	public String getPhaseStatus() {
		return phaseStatus;
	}
	public void setPhaseStatus(String phaseStatus) {
		this.phaseStatus = phaseStatus;
	}
	public String getPhaseType() {
		return phaseType;
	}
	public void setPhaseType(String phaseType) {
		this.phaseType = phaseType;
	}
	public LinkedHashMap getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(LinkedHashMap phaseList) {
		this.phaseList = phaseList;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
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
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}
