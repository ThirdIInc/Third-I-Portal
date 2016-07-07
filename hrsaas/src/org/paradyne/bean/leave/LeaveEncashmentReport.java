package org.paradyne.bean.leave;

import org.paradyne.lib.BeanBase;

public class LeaveEncashmentReport extends BeanBase {
	private String  frmDate="";
	private String  toDate="";
	private String  centerName="";
	private String  deptID="";
	private String  desgID="";
	private String  centerID="";
	private String  deptName="";
	private String  desgName="";
	private String today="";
	private String  divCode="";
	private String  divName="";
	private String status="";
	private String empId="";
	private String token="";
	private String empName="";
	private String typeCode="";
	private String empType="";
	private String reportType="";
	private String reportAction="";
	
	private String costcentername = "";
	private String costcenterid = "";
	private String payBillName = "";
	private String payBillNo = "";
	private String cadreName = "";
	private String cadreCode = "";
	private String leaveTypeCode = "";
	private String leaveTypeName = "";

	private String allCheck = "true";
	private String inclSalCheck = "";
	private String notInclSalCheck = "";
	private String bankCheck = "";
	private String accNoCheck = "";
	private String divCheck = "";
	private String branchCheck = "";
	private String gradeCheck = "";
	private String costCenterCheck = "";	
	
	
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}

	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDesgID() {
		return desgID;
	}
	public void setDesgID(String desgID) {
		this.desgID = desgID;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}
	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
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
	 * @return the costcentername
	 */
	public String getCostcentername() {
		return costcentername;
	}
	/**
	 * @param costcentername the costcentername to set
	 */
	public void setCostcentername(String costcentername) {
		this.costcentername = costcentername;
	}
	/**
	 * @return the costcenterid
	 */
	public String getCostcenterid() {
		return costcenterid;
	}
	/**
	 * @param costcenterid the costcenterid to set
	 */
	public void setCostcenterid(String costcenterid) {
		this.costcenterid = costcenterid;
	}
	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}
	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}
	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}
	/**
	 * @param cadreName the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	/**
	 * @return the cadreCode
	 */
	public String getCadreCode() {
		return cadreCode;
	}
	/**
	 * @param cadreCode the cadreCode to set
	 */
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	/**
	 * @return the leaveTypeCode
	 */
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}
	/**
	 * @param leaveTypeCode the leaveTypeCode to set
	 */
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}
	/**
	 * @return the leaveTypeName
	 */
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	/**
	 * @param leaveTypeName the leaveTypeName to set
	 */
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	/**
	 * @return the allCheck
	 */
	public String getAllCheck() {
		return allCheck;
	}
	/**
	 * @param allCheck the allCheck to set
	 */
	public void setAllCheck(String allCheck) {
		this.allCheck = allCheck;
	}
	/**
	 * @return the inclSalCheck
	 */
	public String getInclSalCheck() {
		return inclSalCheck;
	}
	/**
	 * @param inclSalCheck the inclSalCheck to set
	 */
	public void setInclSalCheck(String inclSalCheck) {
		this.inclSalCheck = inclSalCheck;
	}
	/**
	 * @return the notInclSalCheck
	 */
	public String getNotInclSalCheck() {
		return notInclSalCheck;
	}
	/**
	 * @param notInclSalCheck the notInclSalCheck to set
	 */
	public void setNotInclSalCheck(String notInclSalCheck) {
		this.notInclSalCheck = notInclSalCheck;
	}
	/**
	 * @return the bankCheck
	 */
	public String getBankCheck() {
		return bankCheck;
	}
	/**
	 * @param bankCheck the bankCheck to set
	 */
	public void setBankCheck(String bankCheck) {
		this.bankCheck = bankCheck;
	}
	/**
	 * @return the accNoCheck
	 */
	public String getAccNoCheck() {
		return accNoCheck;
	}
	/**
	 * @param accNoCheck the accNoCheck to set
	 */
	public void setAccNoCheck(String accNoCheck) {
		this.accNoCheck = accNoCheck;
	}
	/**
	 * @return the divCheck
	 */
	public String getDivCheck() {
		return divCheck;
	}
	/**
	 * @param divCheck the divCheck to set
	 */
	public void setDivCheck(String divCheck) {
		this.divCheck = divCheck;
	}
	/**
	 * @return the branchCheck
	 */
	public String getBranchCheck() {
		return branchCheck;
	}
	/**
	 * @param branchCheck the branchCheck to set
	 */
	public void setBranchCheck(String branchCheck) {
		this.branchCheck = branchCheck;
	}
	/**
	 * @return the gradeCheck
	 */
	public String getGradeCheck() {
		return gradeCheck;
	}
	/**
	 * @param gradeCheck the gradeCheck to set
	 */
	public void setGradeCheck(String gradeCheck) {
		this.gradeCheck = gradeCheck;
	}
	/**
	 * @return the costCenterCheck
	 */
	public String getCostCenterCheck() {
		return costCenterCheck;
	}
	/**
	 * @param costCenterCheck the costCenterCheck to set
	 */
	public void setCostCenterCheck(String costCenterCheck) {
		this.costCenterCheck = costCenterCheck;
	}
	
	
}
