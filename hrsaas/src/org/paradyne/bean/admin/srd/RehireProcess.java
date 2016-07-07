/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0517
 *
 */
public class RehireProcess extends BeanBase {

	private String applDivisionCode;
	private String applDivisionName;
	private String applBranchCode;
	private String applBranchName;
	private String applDeptCode;
	private String applDeptName;
	private String applDesgCode;
	private String applDesgName;
	private String applEmpCode;
	private String applEmpName;
	private String applEmpTypeCode;
	private String applEmpTypeName;
	private String newEmpTokenHidden="";
	
	
	private ArrayList employeeList = null;
	private String listEmpId= "";
	private String listEmpToken= "";
	private String listEmpName= "";
	private String listDoj= "";
	private String listDol= "";
	private String listEmpType= "";
	private String noData = "false";
	
	private String processEmpId= "";
	private String oldEmpToken= "";
	private String processEmpName= "";
	private String newEmpToken= "";
	private String processDoj= "";
	private String processDol= "";
	private String updateRadio= "";
	private String createRadio= "";
	private ArrayList processList = null;
	private String viewEmpList = "false";
	private String colour = "";
	private String sepPay = "";
	private String updOrInsert = "";
	
	
	public String getUpdOrInsert() {
		return updOrInsert;
	}
	public void setUpdOrInsert(String updOrInsert) {
		this.updOrInsert = updOrInsert;
	}
	public String getSepPay() {
		return sepPay;
	}
	public void setSepPay(String sepPay) {
		this.sepPay = sepPay;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getViewEmpList() {
		return viewEmpList;
	}
	public void setViewEmpList(String viewEmpList) {
		this.viewEmpList = viewEmpList;
	}
	public String getProcessEmpId() {
		return processEmpId;
	}
	public void setProcessEmpId(String processEmpId) {
		this.processEmpId = processEmpId;
	}
	public String getOldEmpToken() {
		return oldEmpToken;
	}
	public void setOldEmpToken(String oldEmpToken) {
		this.oldEmpToken = oldEmpToken;
	}
	public String getProcessEmpName() {
		return processEmpName;
	}
	public void setProcessEmpName(String processEmpName) {
		this.processEmpName = processEmpName;
	}
	public String getNewEmpToken() {
		return newEmpToken;
	}
	public void setNewEmpToken(String newEmpToken) {
		this.newEmpToken = newEmpToken;
	}
	public String getProcessDoj() {
		return processDoj;
	}
	public void setProcessDoj(String processDoj) {
		this.processDoj = processDoj;
	}
	public String getProcessDol() {
		return processDol;
	}
	public void setProcessDol(String processDol) {
		this.processDol = processDol;
	}
	public String getUpdateRadio() {
		return updateRadio;
	}
	public void setUpdateRadio(String updateRadio) {
		this.updateRadio = updateRadio;
	}
	public String getCreateRadio() {
		return createRadio;
	}
	public void setCreateRadio(String createRadio) {
		this.createRadio = createRadio;
	}
	public ArrayList getProcessList() {
		return processList;
	}
	public void setProcessList(ArrayList processList) {
		this.processList = processList;
	}
	public String getApplDivisionCode() {
		return applDivisionCode;
	}
	public void setApplDivisionCode(String applDivisionCode) {
		this.applDivisionCode = applDivisionCode;
	}
	public String getApplDivisionName() {
		return applDivisionName;
	}
	public void setApplDivisionName(String applDivisionName) {
		this.applDivisionName = applDivisionName;
	}
	public String getApplBranchCode() {
		return applBranchCode;
	}
	public void setApplBranchCode(String applBranchCode) {
		this.applBranchCode = applBranchCode;
	}
	public String getApplBranchName() {
		return applBranchName;
	}
	public void setApplBranchName(String applBranchName) {
		this.applBranchName = applBranchName;
	}
	public String getApplDeptCode() {
		return applDeptCode;
	}
	public void setApplDeptCode(String applDeptCode) {
		this.applDeptCode = applDeptCode;
	}
	public String getApplDeptName() {
		return applDeptName;
	}
	public void setApplDeptName(String applDeptName) {
		this.applDeptName = applDeptName;
	}
	public String getApplDesgCode() {
		return applDesgCode;
	}
	public void setApplDesgCode(String applDesgCode) {
		this.applDesgCode = applDesgCode;
	}
	public String getApplDesgName() {
		return applDesgName;
	}
	public void setApplDesgName(String applDesgName) {
		this.applDesgName = applDesgName;
	}
	public String getApplEmpCode() {
		return applEmpCode;
	}
	public void setApplEmpCode(String applEmpCode) {
		this.applEmpCode = applEmpCode;
	}
	public String getApplEmpName() {
		return applEmpName;
	}
	public void setApplEmpName(String applEmpName) {
		this.applEmpName = applEmpName;
	}
	public ArrayList getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}
	public String getListEmpId() {
		return listEmpId;
	}
	public void setListEmpId(String listEmpId) {
		this.listEmpId = listEmpId;
	}
	public String getListEmpToken() {
		return listEmpToken;
	}
	public void setListEmpToken(String listEmpToken) {
		this.listEmpToken = listEmpToken;
	}
	public String getListEmpName() {
		return listEmpName;
	}
	public void setListEmpName(String listEmpName) {
		this.listEmpName = listEmpName;
	}
	public String getListDoj() {
		return listDoj;
	}
	public void setListDoj(String listDoj) {
		this.listDoj = listDoj;
	}
	public String getListDol() {
		return listDol;
	}
	public void setListDol(String listDol) {
		this.listDol = listDol;
	}
	public String getListEmpType() {
		return listEmpType;
	}
	public void setListEmpType(String listEmpType) {
		this.listEmpType = listEmpType;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getApplEmpTypeCode() {
		return applEmpTypeCode;
	}
	public void setApplEmpTypeCode(String applEmpTypeCode) {
		this.applEmpTypeCode = applEmpTypeCode;
	}
	public String getApplEmpTypeName() {
		return applEmpTypeName;
	}
	public void setApplEmpTypeName(String applEmpTypeName) {
		this.applEmpTypeName = applEmpTypeName;
	}
	public String getNewEmpTokenHidden() {
		return newEmpTokenHidden;
	}
	public void setNewEmpTokenHidden(String newEmpTokenHidden) {
		this.newEmpTokenHidden = newEmpTokenHidden;
	}
}
