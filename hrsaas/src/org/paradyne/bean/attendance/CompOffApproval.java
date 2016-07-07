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
public class CompOffApproval extends BeanBase {
	
	private String pen="";
	private String app="";
	private String rej="";
	private String compEmpId="";
	private String empName="";
	private String appDate="";
	private String checkStatus="";
	private String compId="";
	private String level="";
	private String apprflag ="false";
	private String remark;
	private String statusNew;
	private String noData ="false";	
	private String listLength = "";
	ArrayList cmpList = null;
	private String  status;
	
	private String empId = "";
	private String eName = "";
	private String branchName = "";
	private String dept = "";
	private String desg= "";
	private String aDate = "";
	
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
	
	private String compEmpToken;
	 
	
	public String getCompEmpToken() {
		return compEmpToken;
	}
	public void setCompEmpToken(String compEmpToken) {
		this.compEmpToken = compEmpToken;
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
	public ArrayList getCompList() {
		return compList;
	}
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
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
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public ArrayList getCmpList() {
		return cmpList;
	}
	public void setCmpList(ArrayList cmpList) {
		this.cmpList = cmpList;
	}
	public String getCompEmpId() {
		return compEmpId;
	}
	public void setCompEmpId(String compEmpId) {
		this.compEmpId = compEmpId;
	}
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
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
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
	public String getADate() {
		return aDate;
	}
	public void setADate(String date) {
		aDate = date;
	}

}
