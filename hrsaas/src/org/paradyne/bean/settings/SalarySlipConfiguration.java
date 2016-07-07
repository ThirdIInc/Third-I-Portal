package org.paradyne.bean.settings;

import org.paradyne.lib.BeanBase;

public class SalarySlipConfiguration extends BeanBase {
	
	private String departmentChk;
	private String branchChk;
	private String designationChk;
	private String empTypeChk;
	private String salaryGradeChk;
	private String gradeChk;
	private String bankIdChk;
	private String accountNoChk;
	private String attendanceDetailChk;
	private String leaveDetailChk;
	private String showLogoChk;
	private String salHeader;
	private String payBill="";
	private String trade="";
	
	
	private String roleChk; //Added checkbox(Y/N) button by abhijit
	
	//added ganesh 10/01/2012
	private String aprilToDateSalaryChk ="";
	private String recoveryDays = "";
	private String netPayInWords = "";
	private String signature = "";
	
	
	public String getAprilToDateSalaryChk() {
		return aprilToDateSalaryChk;
	}
	public void setAprilToDateSalaryChk(String aprilToDateSalaryChk) {
		this.aprilToDateSalaryChk = aprilToDateSalaryChk;
	}
	public String getRecoveryDays() {
		return recoveryDays;
	}
	public void setRecoveryDays(String recoveryDays) {
		this.recoveryDays = recoveryDays;
	}
	public String getNetPayInWords() {
		return netPayInWords;
	}
	public void setNetPayInWords(String netPayInWords) {
		this.netPayInWords = netPayInWords;
	}
	public String getDepartmentChk() {
		return departmentChk;
	}
	public void setDepartmentChk(String departmentChk) {
		this.departmentChk = departmentChk;
	}
	public String getBranchChk() {
		return branchChk;
	}
	public void setBranchChk(String branchChk) {
		this.branchChk = branchChk;
	}
	public String getDesignationChk() {
		return designationChk;
	}
	public void setDesignationChk(String designationChk) {
		this.designationChk = designationChk;
	}
	public String getEmpTypeChk() {
		return empTypeChk;
	}
	public void setEmpTypeChk(String empTypeChk) {
		this.empTypeChk = empTypeChk;
	}
	public String getSalaryGradeChk() {
		return salaryGradeChk;
	}
	public void setSalaryGradeChk(String salaryGradeChk) {
		this.salaryGradeChk = salaryGradeChk;
	}
	public String getBankIdChk() {
		return bankIdChk;
	}
	public void setBankIdChk(String bankIdChk) {
		this.bankIdChk = bankIdChk;
	}
	public String getAccountNoChk() {
		return accountNoChk;
	}
	public void setAccountNoChk(String accountNoChk) {
		this.accountNoChk = accountNoChk;
	}
	public String getAttendanceDetailChk() {
		return attendanceDetailChk;
	}
	public void setAttendanceDetailChk(String attendanceDetailChk) {
		this.attendanceDetailChk = attendanceDetailChk;
	}
	public String getLeaveDetailChk() {
		return leaveDetailChk;
	}
	public void setLeaveDetailChk(String leaveDetailChk) {
		this.leaveDetailChk = leaveDetailChk;
	}
	public String getShowLogoChk() {
		return showLogoChk;
	}
	public void setShowLogoChk(String showLogoChk) {
		this.showLogoChk = showLogoChk;
	}
	public String getSalHeader() {
		return salHeader;
	}
	public void setSalHeader(String salHeader) {
		this.salHeader = salHeader;
	}
	public String getGradeChk() {
		return gradeChk;
	}
	public void setGradeChk(String gradeChk) {
		this.gradeChk = gradeChk;
	}
	public String getRoleChk() {
		return roleChk;
	}
	public void setRoleChk(String roleChk) {
		this.roleChk = roleChk;
	}
	public String getPayBill() {
		return payBill;
	}
	public void setPayBill(String payBill) {
		this.payBill = payBill;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
