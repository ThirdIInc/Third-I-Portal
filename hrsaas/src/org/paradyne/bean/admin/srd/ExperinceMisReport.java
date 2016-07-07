package org.paradyne.bean.admin.srd;

import org.paradyne.lib.BeanBase;

public class ExperinceMisReport extends BeanBase {
	    private String empid= "";
		private String empName= "";
		private String token= "";

		
		private String status= "";

		private String desgCode= "";
		private String desgName= "";

	    private String deptCode = "";
	   private String deptName= "";

		private String centerId = "";
		private String centerName = "";
		private String	divCode="";
		private String divsion="";
		
		private String quaCode="";
		private String quaName="";
		
		private String reportType="";
		
		private String report="";
		
		
		
		//Added by Nilesh 20th June 2011
		private String checkFlag="N";
		//private boolean displayFlag = false;
		
		public String getEmpid() {
			return empid;
		}
		public void setEmpid(String empid) {
			this.empid = empid;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
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
		public String getCenterId() {
			return centerId;
		}
		public void setCenterId(String centerId) {
			this.centerId = centerId;
		}
		public String getCenterName() {
			return centerName;
		}
		public void setCenterName(String centerName) {
			this.centerName = centerName;
		}
		public String getReportType() {
			return reportType;
		}
		public void setReportType(String reportType) {
			this.reportType = reportType;
		}
		public String getDivCode() {
			return divCode;
		}
		public void setDivCode(String divCode) {
			this.divCode = divCode;
		}
		public String getDivsion() {
			return divsion;
		}
		public void setDivsion(String divsion) {
			this.divsion = divsion;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getQuaCode() {
			return quaCode;
		}
		public void setQuaCode(String quaCode) {
			this.quaCode = quaCode;
		}
		public String getQuaName() {
			return quaName;
		}
		public void setQuaName(String quaName) {
			this.quaName = quaName;
		}
		/**
		 * @return the displayFlag
		 */
		
		/**
		 * @return the checkFlag
		 */
		public String getCheckFlag() {
			return checkFlag;
		}
		/**
		 * @param checkFlag the checkFlag to set
		 */
		public void setCheckFlag(String checkFlag) {
			this.checkFlag = checkFlag;
		}
		public String getReport() {
			return report;
		}
		public void setReport(String report) {
			this.report = report;
		}

}

