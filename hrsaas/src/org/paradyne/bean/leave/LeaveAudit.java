package org.paradyne.bean.leave;


/**
 * @author Venkatesh

 */
import java.util.ArrayList;


import org.paradyne.lib.BeanBase;

public class LeaveAudit extends BeanBase {
	
	private String empID="";
	private String empName1="";
	private String empTokenNo="";
	private String empName="";
	private String department="";
	private String center="";
	private String leaveDays="";
	private String leaveFromDate="";
	private String leaveToDate="";
	private String leaveAuditFlag="";
	private String leaveAuditDate="";
	private String leaveAuditBy="";
	private String leaveDtlId="";
	private String leaveDtlId1="";
	private boolean flag=false;
	private String chkFlag="";
	
	private ArrayList auditList=null;
	private ArrayList flagList=null;
	
	public ArrayList getFlagList() {
		return flagList;
	}

	public void setFlagList(ArrayList flagList) {
		this.flagList = flagList;
	}

	public LeaveAudit()
	{
		
	}
	
	public LeaveAudit(String empId,String empName,String leaveDays,String leaveFromDate,String leaveDtlId)
	{
		
		this.empName=empName;
		this.leaveDays=leaveDays;
		this.leaveFromDate=leaveFromDate;
		this.leaveDtlId=leaveDtlId;
	}

	public ArrayList getAuditList() {
		return auditList;
	}

	public void setAuditList(ArrayList auditList) {
		this.auditList = auditList;
	}

	

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	

	public String getLeaveAuditFlag() {
		return leaveAuditFlag;
	}

	public void setLeaveAuditFlag(String leaveAuditFlag) {
		this.leaveAuditFlag = leaveAuditFlag;
	}

	public String getLeaveAuditDate() {
		return leaveAuditDate;
	}

	public void setLeaveAuditDate(String leaveAuditDate) {
		this.leaveAuditDate = leaveAuditDate;
	}

	public String getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}

	public String getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public String getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public String getLeaveAuditBy() {
		return leaveAuditBy;
	}

	public void setLeaveAuditBy(String leaveAuditBy) {
		this.leaveAuditBy = leaveAuditBy;
	}

	public String getLeaveDtlId() {
		return leaveDtlId;
	}

	public void setLeaveDtlId(String leaveDtlId) {
		this.leaveDtlId = leaveDtlId;
	}

	public String getEmpName1() {
		return empName1;
	}

	public void setEmpName1(String empName1) {
		this.empName1 = empName1;
	}

	

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLeaveDtlId1() {
		return leaveDtlId1;
	}

	public void setLeaveDtlId1(String leaveDtlId1) {
		this.leaveDtlId1 = leaveDtlId1;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	
	
}

	