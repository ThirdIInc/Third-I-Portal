package org.paradyne.bean.common;
import org.paradyne.lib.BeanBase;
import java.util.*;
/*
 * author:Pradeep Kumar Sahoo
 * Date:17.01.2008
 */

public class LeaveHome extends BeanBase  {
	
	String empName;
	String appDate;
	String frmDate;
	String tdate;
	String name;
	String fromDt; 
	String toDt;
	String reason;
	String contactinfo;
	
	private String employeeName;
	private String applDate;
	private String LeaveFrmDate;
	private String LeaveToDate;
	ArrayList leaveList;
	
	private String eName;
	private String LeaveFromDate;
	private String LevToDate;
	private String reasoninfo;
	ArrayList empLeaveList;
	
	
	
	
	

	
	private ArrayList list;
	private ArrayList list1;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getContactinfo() {
		return contactinfo;
	}
	public void setContactinfo(String contactinfo) {
		this.contactinfo = contactinfo;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getList1() {
		return list1;
	}
	public void setList1(ArrayList list1) {
		this.list1 = list1;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLeaveFrmDate() {
		return LeaveFrmDate;
	}
	public void setLeaveFrmDate(String leaveFrmDate) {
		LeaveFrmDate = leaveFrmDate;
	}
	public String getLeaveToDate() {
		return LeaveToDate;
	}
	public void setLeaveToDate(String leaveToDate) {
		LeaveToDate = leaveToDate;
	}
	public ArrayList getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
	}
	public String getLeaveFromDate() {
		return LeaveFromDate;
	}
	public void setLeaveFromDate(String leaveFromDate) {
		LeaveFromDate = leaveFromDate;
	}
	public String getLevToDate() {
		return LevToDate;
	}
	public void setLevToDate(String levToDate) {
		LevToDate = levToDate;
	}
	public String getReasoninfo() {
		return reasoninfo;
	}
	public void setReasoninfo(String reasoninfo) {
		this.reasoninfo = reasoninfo;
	}
	public ArrayList getEmpLeaveList() {
		return empLeaveList;
	}
	public void setEmpLeaveList(ArrayList empLeaveList) {
		this.empLeaveList = empLeaveList;
	}
	
	

}
