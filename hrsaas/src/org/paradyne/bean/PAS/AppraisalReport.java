/**
 * 
 */
package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class AppraisalReport extends BeanBase {
		private String apprCode;
		private String apprId;
		private String frmDate;
		private String empToken;
		private String empName;
		private String empCode;
		private String branch;
		private String desg;
		private String toDate;
		private String templateCode;
		private String apprPeriod;
		
		public String getApprPeriod() {
			return apprPeriod;
		}
		public void setApprPeriod(String apprPeriod) {
			this.apprPeriod = apprPeriod;
		}
		public String getTemplateCode() {
			return templateCode;
		}
		public void setTemplateCode(String templateCode) {
			this.templateCode = templateCode;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
		public String getApprCode() {
			return apprCode;
		}
		public void setApprCode(String apprCode) {
			this.apprCode = apprCode;
		}
		public String getApprId() {
			return apprId;
		}
		public void setApprId(String apprId) {
			this.apprId = apprId;
		}
		public String getFrmDate() {
			return frmDate;
		}
		public void setFrmDate(String frmDate) {
			this.frmDate = frmDate;
		}
		public String getEmpToken() {
			return empToken;
		}
		public void setEmpToken(String empToken) {
			this.empToken = empToken;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getDesg() {
			return desg;
		}
		public void setDesg(String desg) {
			this.desg = desg;
		}
		
}
