package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PensionSlip extends BeanBase {
	
	private String sMonth = null;
	private String sYear = null;
	private String sDivName = null;
	private String sDivId = null;
	private String sBrchName = null;
	private String sBrchId = null;
	private String sDeptName = null;
	private String sDeptId = null;
	private String sEmpName = null;
	private String sEmpId = null;
	private String sEmpCode = null;

	private String sDesignation = null;
	private String sDesignationCode = null;

	private ArrayList lstReportType = new ArrayList();
	private String sSelectedReportType = null;

	public String getSMonth() {
		return sMonth;
	}

	public void setSMonth(String month) {
		sMonth = month;
	}

	public String getSYear() {
		return sYear;
	}

	public void setSYear(String year) {
		sYear = year;
	}

	public String getSDivName() {
		return sDivName;
	}

	public void setSDivName(String divName) {
		sDivName = divName;
	}

	public String getSDivId() {
		return sDivId;
	}

	public void setSDivId(String divId) {
		sDivId = divId;
	}

	public String getSBrchName() {
		return sBrchName;
	}

	public void setSBrchName(String brchName) {
		sBrchName = brchName;
	}

	public String getSBrchId() {
		return sBrchId;
	}

	public void setSBrchId(String brchId) {
		sBrchId = brchId;
	}

	public String getSDeptName() {
		return sDeptName;
	}

	public void setSDeptName(String deptName) {
		sDeptName = deptName;
	}

	public String getSDeptId() {
		return sDeptId;
	}

	public void setSDeptId(String deptId) {
		sDeptId = deptId;
	}

	public String getSEmpName() {
		return sEmpName;
	}

	public void setSEmpName(String empName) {
		sEmpName = empName;
	}

	public String getSEmpId() {
		return sEmpId;
	}

	public void setSEmpId(String empId) {
		sEmpId = empId;
	}

	public String getSEmpCode() {
		return sEmpCode;
	}

	public void setSEmpCode(String empCode) {
		sEmpCode = empCode;
	}

	public String getSDesignation() {
		return sDesignation;
	}

	public void setSDesignation(String designation) {
		sDesignation = designation;
	}

	public String getSDesignationCode() {
		return sDesignationCode;
	}

	public void setSDesignationCode(String designationCode) {
		sDesignationCode = designationCode;
	}

	public ArrayList getLstReportType() {
		return lstReportType;
	}

	public void setLstReportType(ArrayList lstReportType) {
		this.lstReportType = lstReportType;
	}

	public String getSSelectedReportType() {
		return sSelectedReportType;
	}

	public void setSSelectedReportType(String selectedReportType) {
		sSelectedReportType = selectedReportType;
	}
	
}
