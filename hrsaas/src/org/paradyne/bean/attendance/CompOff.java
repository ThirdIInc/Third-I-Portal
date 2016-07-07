 /**
 * 
 */
package org.paradyne.bean.attendance;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author balajim
 *
 */
public class CompOff extends BeanBase {
	
	private String compId= "";
	private String status = "";
	private String empId = "";
	private String empName = "";
	private String branchName = "";
	private String dept = "";
	private String desg= "";
	private String appDate = "";
	private String prName = "";
	private String prDate = "";
	private String startTime = "";
	private String endTime = "";
	private String cmonth = "";
	private String cyear = "";
	private String hprojName = "";
	private String hsTime = ""; 
	private String heTime = "";
	private String hmonth = "";
	private String hyear = "";
	private String hDate = "";
	private String serialNo ="";
	private String hidMon;
	private String empToken;
	ArrayList compList = null;
	private String  checkEdit;
	private String apprName = "";
	private String apprDate ="";
	private String apprComments ="";
	ArrayList approveList = null;
	private String iteratorFlag="false";
	private String comments;
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public ArrayList getCompList() {
		return compList;
	}
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public String getPrDate() {
		return prDate;
	}
	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	 
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCmonth() {
		return cmonth;
	}
	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}
	public String getCyear() {
		return cyear;
	}
	public void setCyear(String cyear) {
		this.cyear = cyear;
	}
	public String getHprojName() {
		return hprojName;
	}
	public void setHprojName(String hprojName) {
		this.hprojName = hprojName;
	}
	public String getHsTime() {
		return hsTime;
	}
	public void setHsTime(String hsTime) {
		this.hsTime = hsTime;
	}
	public String getHeTime() {
		return heTime;
	}
	public void setHeTime(String heTime) {
		this.heTime = heTime;
	}
	public String getHmonth() {
		return hmonth;
	}
	public void setHmonth(String hmonth) {
		this.hmonth = hmonth;
	}
	public String getHyear() {
		return hyear;
	}
	public void setHyear(String hyear) {
		this.hyear = hyear;
	}
	public String getHDate() {
		return hDate;
	}
	public void setHDate(String date) {
		hDate = date;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getHidMon() {
		return hidMon;
	}
	public void setHidMon(String hidMon) {
		this.hidMon = hidMon;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIteratorFlag() {
		return iteratorFlag;
	}
	public void setIteratorFlag(String iteratorFlag) {
		this.iteratorFlag = iteratorFlag;
	}
	

}
