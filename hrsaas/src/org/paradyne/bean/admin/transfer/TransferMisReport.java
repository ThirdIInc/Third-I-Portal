package org.paradyne.bean.admin.transfer;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep Kumar Sahoo
 * Date:28-06-2008
 */
public class TransferMisReport extends BeanBase {
	
	private String fromDate="";
	private String toDate="";
	private String empId="";
	private String divId="";
	private String brnId="";
	private String deptId="";
	private String brnName="";
	private String deptName="";
	private String divName="";
	private String status="";
	private String empName="";
	private String repType="";
	private String empToken="";
	
	
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public TransferMisReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransferMisReport(String fromDate, String toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBrnId() {
		return brnId;
	}
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	
	
	
	

}
