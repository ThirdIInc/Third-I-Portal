package org.paradyne.bean.TravelManagement;
import org.paradyne.lib.BeanBase;

/**
 * @author hemantg
 * date : 14/08/2008
 *
 */
public class TravelMisReport extends BeanBase{
	private String empToken="";
	private String empId="";
	private String empName="";
	private String frmDate="";
	private String toDate="";
	private String status="";
	private String rptType="";
	private String trBranCode="";
	private String trBranch="";
	private String trDept="";
	private String trDeptCode="";
	private String trDesg="";
	private String trDesgCode="";
	private String trDiv="";
	private String trDivCode="";
	private String travelId="";
	private String cancelStatus ="";
	public String getRptType() {
		return rptType;
	}
	public void setRptType(String rptType) {
		this.rptType = rptType;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrBranCode() {
		return trBranCode;
	}
	public void setTrBranCode(String trBranCode) {
		this.trBranCode = trBranCode;
	}
	public String getTrBranch() {
		return trBranch;
	}
	public void setTrBranch(String trBranch) {
		this.trBranch = trBranch;
	}
	public String getTrDept() {
		return trDept;
	}
	public void setTrDept(String trDept) {
		this.trDept = trDept;
	}
	public String getTrDeptCode() {
		return trDeptCode;
	}
	public void setTrDeptCode(String trDeptCode) {
		this.trDeptCode = trDeptCode;
	}
	public String getTrDesg() {
		return trDesg;
	}
	public void setTrDesg(String trDesg) {
		this.trDesg = trDesg;
	}
	public String getTrDesgCode() {
		return trDesgCode;
	}
	public void setTrDesgCode(String trDesgCode) {
		this.trDesgCode = trDesgCode;
	}
	public String getTrDiv() {
		return trDiv;
	}
	public void setTrDiv(String trDiv) {
		this.trDiv = trDiv;
	}
	public String getTrDivCode() {
		return trDivCode;
	}
	public void setTrDivCode(String trDivCode) {
		this.trDivCode = trDivCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getTravelId() {
		return travelId;
	}
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	
	
	
}
