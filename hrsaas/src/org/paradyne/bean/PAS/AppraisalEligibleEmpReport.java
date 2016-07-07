package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AppraisalEligibleEmpReport extends BeanBase 
{
	private String sAppCode = null;
	private String sAppStartDate = null;
	private String sAppEndDate = null;
	private String sAppId = null;
	private ArrayList lstEligibleEmp = null;
	
	private String sNo =null;
	private String sEmpCode = null;
	private String sEmpName = null;
	private String sDOJ = null;
	private String sEmpType = null;
	private String sGrade = null;
	private String sDept = null;
	private String sBrh = null;
	private String report = "";
	
	
	public String getSAppCode() {
		return sAppCode;
	}
	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}
	public String getSAppStartDate() {
		return sAppStartDate;
	}
	public void setSAppStartDate(String appStartDate) {
		sAppStartDate = appStartDate;
	}
	public String getSAppEndDate() {
		return sAppEndDate;
	}
	public void setSAppEndDate(String appEndDate) {
		sAppEndDate = appEndDate;
	}
	public String getSAppId() {
		return sAppId;
	}
	public void setSAppId(String appId) {
		sAppId = appId;
	}
	public ArrayList getLstEligibleEmp() {
		return lstEligibleEmp;
	}
	public void setLstEligibleEmp(ArrayList lstEligibleEmp) {
		this.lstEligibleEmp = lstEligibleEmp;
	}
	public String getSNo() {
		return sNo;
	}
	public void setSNo(String no) {
		sNo = no;
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
	public String getSDOJ() {
		return sDOJ;
	}
	public void setSDOJ(String sdoj) {
		sDOJ = sdoj;
	}
	public String getSEmpType() {
		return sEmpType;
	}
	public void setSEmpType(String empType) {
		sEmpType = empType;
	}
	public String getSGrade() {
		return sGrade;
	}
	public void setSGrade(String grade) {
		sGrade = grade;
	}
	public String getSDept() {
		return sDept;
	}
	public void setSDept(String dept) {
		sDept = dept;
	}
	public String getSBrh() {
		return sBrh;
	}
	public void setSBrh(String brh) {
		sBrh = brh;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}

}
