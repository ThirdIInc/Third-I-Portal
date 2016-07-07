/**
 * 
 */
package org.paradyne.bean.admin.srd;

import org.paradyne.lib.BeanBase;

/**
 * @author Mangesh Jadhav
 *
 */
public class ManpowerSnapshot extends BeanBase {

	private String divCode="";
	private String divName="";
	private String fromDate="";
	private String toDate="";
	private String snapShotFor="";
	private String snapShotGroupBy="";
	private String reportType="";
	private String deptName="";
	private String deptCode="";
	private String branchName="";
	private String branchCode="";
	private String eTypeName="";
	private String eTypeCode="";
	private String costCenterName="";
	private String costCenterCode="";
	//private String reportType="";
	
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
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
	public String getSnapShotFor() {
		return snapShotFor;
	}
	public void setSnapShotFor(String snapShotFor) {
		this.snapShotFor = snapShotFor;
	}
	public String getSnapShotGroupBy() {
		return snapShotGroupBy;
	}
	public void setSnapShotGroupBy(String snapShotGroupBy) {
		this.snapShotGroupBy = snapShotGroupBy;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getETypeName() {
		return eTypeName;
	}
	public void setETypeName(String typeName) {
		eTypeName = typeName;
	}
	public String getETypeCode() {
		return eTypeCode;
	}
	public void setETypeCode(String typeCode) {
		eTypeCode = typeCode;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getCostCenterCode() {
		return costCenterCode;
	}
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
}
