package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class IPFilter extends BeanBase {
	
	private String ipFilterFlag="";
	private String orgFrom="";
	private String orgTo="";
	private String branchName="";
	private String branchCode="";
	private String branchFrom="";
	private String branchTo="";
	private String empName="";
	private String empId="";
	private String empToken="";
	private String orgWiseFlag="false";
	private String fromIpAdd="";
	private String toIpAdd="";
	private String orgWiseCheck="";
	private String branchWiseCheck="";
	
	private ArrayList  list;
	private ArrayList branchList;
	private ArrayList empList;
	
	private String branchfromIpAdd="";
	private String branchtoIpAdd="";
	
	
	private String brnFlag="true"; 
	
	private String orgFlag="true";
	
	private String brnName="";
	private String brnCode="";
	
	private String eName="";
	private String eToken="";
	private String eId="";
	private String srNoEmp="";
	
	private String leaveChkBrn="";
	
	private String leaveChkEmp="";
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	private boolean onLoadFlag=false;
	
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public boolean isLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	public boolean isAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public boolean isModFlag() {
		return modFlag;
	}
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	public boolean isDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	public String getLeaveChkEmp() {
		return leaveChkEmp;
	}
	public void setLeaveChkEmp(String leaveChkEmp) {
		this.leaveChkEmp = leaveChkEmp;
	}
	public String getLeaveChkBrn() {
		return leaveChkBrn;
	}
	public void setLeaveChkBrn(String leaveChkBrn) {
		this.leaveChkBrn = leaveChkBrn;
	}
	public String getSrNoEmp() {
		return srNoEmp;
	}
	public void setSrNoEmp(String srNoEmp) {
		this.srNoEmp = srNoEmp;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
	}
	public String getEToken() {
		return eToken;
	}
	public void setEToken(String token) {
		eToken = token;
	}
	public String getEId() {
		return eId;
	}
	public void setEId(String id) {
		eId = id;
	}
	public ArrayList getBranchList() {
		return branchList;
	}
	public void setBranchList(ArrayList branchList) { 
		this.branchList = branchList;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getIpFilterFlag() {
		return ipFilterFlag;
	}
	public void setIpFilterFlag(String ipFilterFlag) {
		this.ipFilterFlag = ipFilterFlag;
	}
	public String getOrgFrom() {
		return orgFrom;
	}
	public void setOrgFrom(String orgFrom) {
		this.orgFrom = orgFrom;
	}
	public String getOrgTo() {
		return orgTo;
	}
	public void setOrgTo(String orgTo) {
		this.orgTo = orgTo;
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
	public String getBranchFrom() {
		return branchFrom;
	}
	public void setBranchFrom(String branchFrom) {
		this.branchFrom = branchFrom;
	}
	public String getBranchTo() {
		return branchTo;
	}
	public void setBranchTo(String branchTo) {
		this.branchTo = branchTo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getOrgWiseFlag() {
		return orgWiseFlag;
	}
	public void setOrgWiseFlag(String orgWiseFlag) {
		this.orgWiseFlag = orgWiseFlag;
	}
	public String getFromIpAdd() {
		return fromIpAdd;
	}
	public void setFromIpAdd(String fromIpAdd) {
		this.fromIpAdd = fromIpAdd;
	}
	public String getToIpAdd() {
		return toIpAdd;
	}
	public void setToIpAdd(String toIpAdd) {
		this.toIpAdd = toIpAdd;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getBranchfromIpAdd() {
		return branchfromIpAdd;
	}
	public void setBranchfromIpAdd(String branchfromIpAdd) {
		this.branchfromIpAdd = branchfromIpAdd;
	}
	public String getBranchtoIpAdd() {
		return branchtoIpAdd;
	}
	public void setBranchtoIpAdd(String branchtoIpAdd) {
		this.branchtoIpAdd = branchtoIpAdd;
	}
	public String getBrnFlag() {
		return brnFlag;
	}
	public void setBrnFlag(String brnFlag) {
		this.brnFlag = brnFlag;
	}
	public String getOrgFlag() {
		return orgFlag;
	}
	public void setOrgFlag(String orgFlag) {
		this.orgFlag = orgFlag;
	}
	public String getOrgWiseCheck() {
		return orgWiseCheck;
	}
	public void setOrgWiseCheck(String orgWiseCheck) {
		this.orgWiseCheck = orgWiseCheck;
	}
	public String getBranchWiseCheck() {
		return branchWiseCheck;
	}
	public void setBranchWiseCheck(String branchWiseCheck) {
		this.branchWiseCheck = branchWiseCheck;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}


}
