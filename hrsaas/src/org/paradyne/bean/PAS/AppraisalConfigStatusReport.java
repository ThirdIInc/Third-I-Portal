package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

public class AppraisalConfigStatusReport extends BeanBase {
	
	private String apprCode;
	private String appStartDate;
	private String appEndDate;
	private String apprId;
	private String report;
	private String reportAction;
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getAppStartDate() {
		return appStartDate;
	}
	public void setAppStartDate(String appStartDate) {
		this.appStartDate = appStartDate;
	}
	public String getAppEndDate() {
		return appEndDate;
	}
	public void setAppEndDate(String appEndDate) {
		this.appEndDate = appEndDate;
	}
	public String getApprId() {
		return apprId;
	}
	public void setAppId(String apprId) {
		this.apprId = apprId;
	}
	

}
