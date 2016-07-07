/**
 * 
 */
package org.paradyne.bean.openCloseOffice;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseReport extends BeanBase {
	
	private String branchId = "";
	private String branchName = "";
	private String reportType = "";
	private String fromDate = "";
	private String toDate = "";
	private String openCloseFilter = "";
	private String beforeAfterFilter = "";
	private String exceptionTime = "";
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public String getOpenCloseFilter() {
		return openCloseFilter;
	}
	public void setOpenCloseFilter(String openCloseFilter) {
		this.openCloseFilter = openCloseFilter;
	}
	public String getBeforeAfterFilter() {
		return beforeAfterFilter;
	}
	public void setBeforeAfterFilter(String beforeAfterFilter) {
		this.beforeAfterFilter = beforeAfterFilter;
	}
	public String getExceptionTime() {
		return exceptionTime;
	}
	public void setExceptionTime(String exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

}
