/**
 * 
 */
package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class AppraisalSummaryReport extends BeanBase {
	
		private String apprCode;
		private String apprId;
		
		private String apprFrom;
		private String apprTo; 
		private String division;
		private String divId;
		private String branch;
		private String branchId;
		private String dept;
		private String deptId;		
		private String desg;
		private String desgId;
		private String rptType;
		private String template;
		private String templateId;
		private String report;
		private String reportAction;
		
		
		
		public String getReportAction() {
			return reportAction;
		}
		public void setReportAction(String reportAction) {
			this.reportAction = reportAction;
		}
		public String getTemplate() {
			return template;
		}
		public void setTemplate(String template) {
			this.template = template;
		}
		public String getTemplateId() {
			return templateId;
		}
		public void setTemplateId(String templateId) {
			this.templateId = templateId;
		}
		public String getRptType() {
			return rptType;
		}
		public void setRptType(String rptType) {
			this.rptType = rptType;
		}
		public String getApprFrom() {
			return apprFrom;
		}
		public void setApprFrom(String apprFrom) {
			this.apprFrom = apprFrom;
		}
		public String getApprTo() {
			return apprTo;
		}
		public void setApprTo(String apprTo) {
			this.apprTo = apprTo;
		}
		public String getDivision() {
			return division;
		}
		public void setDivision(String division) {
			this.division = division;
		}
		public String getDivId() {
			return divId;
		}
		public void setDivId(String divId) {
			this.divId = divId;
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
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getBranchId() {
			return branchId;
		}
		public void setBranchId(String branchId) {
			this.branchId = branchId;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public String getDeptId() {
			return deptId;
		}
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}
		public String getDesg() {
			return desg;
		}
		public void setDesg(String desg) {
			this.desg = desg;
		}
		public String getDesgId() {
			return desgId;
		}
		public void setDesgId(String desgId) {
			this.desgId = desgId;
		}
		public String getReport() {
			return report;
		}
		public void setReport(String report) {
			this.report = report;
		}
		
		
}