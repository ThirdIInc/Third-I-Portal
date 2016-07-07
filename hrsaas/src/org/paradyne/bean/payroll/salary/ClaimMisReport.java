package org.paradyne.bean.payroll.salary;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep 
 * Date:05-04-2008
 */

public class ClaimMisReport extends BeanBase {

		private String empId="";
		private String empName="";
		private String empToken="";
		private String fromDate="";
		private String toDate="";
		private String claimDate="";
		private String cliamAmt="";
		//private String status="";
	
		private String  status;
		private String branchname;
		private String branchcode;
		private String department;
		private String deptCode;
		
		private String desgCode;
		private String desgname;
		private String divName;
		private String divCode;
		private String reporttype;
		private String creditCode;
		private String creditName;
		
		private String vchCode;
		private String vchName;
		
		public ClaimMisReport() {
			super();
			// TODO Auto-generated constructor stub
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
		public String getEmpToken() {
			return empToken;
		}
		public void setEmpToken(String empToken) {
			this.empToken = empToken;
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
		public String getClaimDate() {
			return claimDate;
		}
		public void setClaimDate(String claimDate) {
			this.claimDate = claimDate;
		}
		public String getCliamAmt() {
			return cliamAmt;
		}
		public void setCliamAmt(String cliamAmt) {
			this.cliamAmt = cliamAmt;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getBranchname() {
			return branchname;
		}
		public void setBranchname(String branchname) {
			this.branchname = branchname;
		}
		public String getBranchcode() {
			return branchcode;
		}
		public void setBranchcode(String branchcode) {
			this.branchcode = branchcode;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDeptCode() {
			return deptCode;
		}
		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
		public String getDesgCode() {
			return desgCode;
		}
		public void setDesgCode(String desgCode) {
			this.desgCode = desgCode;
		}
		public String getDesgname() {
			return desgname;
		}
		public void setDesgname(String desgname) {
			this.desgname = desgname;
		}
		public String getDivName() {
			return divName;
		}
		public void setDivName(String divName) {
			this.divName = divName;
		}
		public String getDivCode() {
			return divCode;
		}
		public void setDivCode(String divCode) {
			this.divCode = divCode;
		}
		public String getReporttype() {
			return reporttype;
		}
		public void setReporttype(String reporttype) {
			this.reporttype = reporttype;
		}
		public String getCreditCode() {
			return creditCode;
		}
		public void setCreditCode(String creditCode) {
			this.creditCode = creditCode;
		}
		public String getCreditName() {
			return creditName;
		}
		public void setCreditName(String creditName) {
			this.creditName = creditName;
		}
		public String getVchCode() {
			return vchCode;
		}
		public void setVchCode(String vchCode) {
			this.vchCode = vchCode;
		}
		public String getVchName() {
			return vchName;
		}
		public void setVchName(String vchName) {
			this.vchName = vchName;
		}
		
		
	
}
