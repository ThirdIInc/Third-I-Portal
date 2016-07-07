package org.paradyne.bean.probation;

import java.util.List;

import org.paradyne.lib.BeanBase;

public class DefineHRBean extends BeanBase {
	
	private String divisionName = "";
	private String divCode = "";
	
	private String employeeName = "";
	private String empToken = "";
	private String empCode = "";
	
	
	//List Functionality.
	private List<Object> divisionList;
	private String srNo = "";
	private String divisionNameItt = "";
	private String divisionItt = "";
	private String employeetokenDivisionItt = "";
	private String employeenameDivisionItt = "";
	
	
	
	
	
	//Branch Fields 
	private String branchCode = "";
	private String branchName = "";
	
	private String employeeBranchName = "";
	private String empBranchToken = "";
	private String empBranchCode = "";
	
	
	//List Fields
	private List<Object> branchList;
	private String serialNo = "";
	private String branchNameItt = "";
	private String employeetokenBranchItt = "";
	private String employeenameBranchItt = "";
	
	
	
	//Saving Data
	
	
	private String divisionId = "";
	private String empid = "";
	
	//for Branch 
	private String branchId = "";
	private String branchEmpId = "";
	
	
	
	private String hiddenDelete = "";
	private String hiddenDeleteDiv = "";
	
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchEmpId
	 */
	public String getBranchEmpId() {
		return branchEmpId;
	}
	/**
	 * @param branchEmpId the branchEmpId to set
	 */
	public void setBranchEmpId(String branchEmpId) {
		this.branchEmpId = branchEmpId;
	}
	/**
	 * @return the divisionId
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}
	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}
	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the divisionItt
	 */
	public String getDivisionItt() {
		return divisionItt;
	}
	/**
	 * @param divisionItt the divisionItt to set
	 */
	public void setDivisionItt(String divisionItt) {
		this.divisionItt = divisionItt;
	}
	
	
	/**
	 * @return the divisionList
	 */
	public List<Object> getDivisionList() {
		return divisionList;
	}
	/**
	 * @param divisionList the divisionList to set
	 */
	public void setDivisionList(List<Object> divisionList) {
		this.divisionList = divisionList;
	}
	/**
	 * @return the employeetokenDivisionItt
	 */
	public String getEmployeetokenDivisionItt() {
		return employeetokenDivisionItt;
	}
	/**
	 * @param employeetokenDivisionItt the employeetokenDivisionItt to set
	 */
	public void setEmployeetokenDivisionItt(String employeetokenDivisionItt) {
		this.employeetokenDivisionItt = employeetokenDivisionItt;
	}
	/**
	 * @return the employeenameDivisionItt
	 */
	public String getEmployeenameDivisionItt() {
		return employeenameDivisionItt;
	}
	/**
	 * @param employeenameDivisionItt the employeenameDivisionItt to set
	 */
	public void setEmployeenameDivisionItt(String employeenameDivisionItt) {
		this.employeenameDivisionItt = employeenameDivisionItt;
	}
	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the employeeBranchName
	 */
	public String getEmployeeBranchName() {
		return employeeBranchName;
	}
	/**
	 * @param employeeBranchName the employeeBranchName to set
	 */
	public void setEmployeeBranchName(String employeeBranchName) {
		this.employeeBranchName = employeeBranchName;
	}
	/**
	 * @return the empBranchToken
	 */
	public String getEmpBranchToken() {
		return empBranchToken;
	}
	/**
	 * @param empBranchToken the empBranchToken to set
	 */
	public void setEmpBranchToken(String empBranchToken) {
		this.empBranchToken = empBranchToken;
	}
	/**
	 * @return the empBranchCode
	 */
	public String getEmpBranchCode() {
		return empBranchCode;
	}
	/**
	 * @param empBranchCode the empBranchCode to set
	 */
	public void setEmpBranchCode(String empBranchCode) {
		this.empBranchCode = empBranchCode;
	}
	/**
	 * @return the branchList
	 */
	public List<Object> getBranchList() {
		return branchList;
	}
	/**
	 * @param branchList the branchList to set
	 */
	public void setBranchList(List<Object> branchList) {
		this.branchList = branchList;
	}
	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the employeetokenBranchItt
	 */
	public String getEmployeetokenBranchItt() {
		return employeetokenBranchItt;
	}
	/**
	 * @param employeetokenBranchItt the employeetokenBranchItt to set
	 */
	public void setEmployeetokenBranchItt(String employeetokenBranchItt) {
		this.employeetokenBranchItt = employeetokenBranchItt;
	}
	/**
	 * @return the employeenameBranchItt
	 */
	public String getEmployeenameBranchItt() {
		return employeenameBranchItt;
	}
	/**
	 * @param employeenameBranchItt the employeenameBranchItt to set
	 */
	public void setEmployeenameBranchItt(String employeenameBranchItt) {
		this.employeenameBranchItt = employeenameBranchItt;
	}
	
	
	/**
	 * @return the divisionNameItt
	 */
	public String getDivisionNameItt() {
		return divisionNameItt;
	}
	/**
	 * @param divisionNameItt the divisionNameItt to set
	 */
	public void setDivisionNameItt(String divisionNameItt) {
		this.divisionNameItt = divisionNameItt;
	}
	/**
	 * @return the branchNameItt
	 */
	public String getBranchNameItt() {
		return branchNameItt;
	}
	/**
	 * @param branchNameItt the branchNameItt to set
	 */
	public void setBranchNameItt(String branchNameItt) {
		this.branchNameItt = branchNameItt;
	}
	/**
	 * @return the hiddenDelete
	 */
	public String getHiddenDelete() {
		return hiddenDelete;
	}
	/**
	 * @param hiddenDelete the hiddenDelete to set
	 */
	public void setHiddenDelete(String hiddenDelete) {
		this.hiddenDelete = hiddenDelete;
	}
	/**
	 * @return the hiddenDeleteDiv
	 */
	public String getHiddenDeleteDiv() {
		return hiddenDeleteDiv;
	}
	/**
	 * @param hiddenDeleteDiv the hiddenDeleteDiv to set
	 */
	public void setHiddenDeleteDiv(String hiddenDeleteDiv) {
		this.hiddenDeleteDiv = hiddenDeleteDiv;
	}
	

}
