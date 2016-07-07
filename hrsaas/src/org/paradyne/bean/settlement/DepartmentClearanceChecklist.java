package org.paradyne.bean.settlement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DepartmentClearanceChecklist extends BeanBase {
	
	
	private String checkList ="";
	private String empName ="";
	private String empCode ="";
	private String empToken ="";
	private String deptName ="";
	private String deptCode ="";
	private String checkListItt ="";
	private String srNo ="";
	private ArrayList list=null ;	
	private String checkEdit ="";
	private String checkListId ="";
	
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getCheckListItt() {
		return checkListItt;
	}
	public void setCheckListItt(String checkListItt) {
		this.checkListItt = checkListItt;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getCheckList() {
		return checkList;
	}
	public void setCheckList(String checkList) {
		this.checkList = checkList;
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
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	public String getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
	}
	 
}
