package org.paradyne.bean.ApplicationStudio.configAuth;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class ConfigAuthorization extends BeanBase {
	
	private String moduleId ="";
	private String moduleName ="";
	private String authType="";
	private String rowId =""; 
	private String dispEmpId =""; 
	private String dispEmpName ="";
	private String rowNum ="0";
	private String cellNum ="0"; 
	private String hiddenAuthType ="";
	
	
	
	ArrayList moduleList = new ArrayList();
	ArrayList employeeList = new ArrayList();
	
	//added by ganesh 18/10/2011
	private String configAuth = "";
	private String divCode = "";
	private String divName = "";
	private String deptCode = "";
	private String deptName = "";
	private String payBillId = "";
	private String payBillName = "";
	private String managerToken = "";
	private String managerName = "";
	private String managerCode = "";
	private String debitCode = "";
	private String debitName = "";
	private String debitAbbr = "";
	ArrayList arr=null;
	private String frmId="";
	private String hiddenfrmId="";
	private String hiddenCode="";
	boolean miscSalaryUploadListPage = false ;
	private List miscSalaryUploadList;
	private String miscSalaryUploadHiddenId = "";
	private String managerEmpAuthId = "";
	private String managerEmpToken = "";
	private String managerEmpName = "";
	private String miscSalDivId = "";
	private String miscSalDivName = "";
	private String miscSalDeptId = "";
	private String miscSalDeptName = "";
	private String miscSalPayBillId = "";
	private String miscSalPayBillName = "";
	private String miscSalComponentCode = "";
	private String miscSalComponentHead = "";
	private String miscSalaryAuthId = "";
	private String miscSalConfigAuth = "";
	private String paracode = "";
	boolean  insertRecordFlag  = false ;
	boolean  updateRecordFlag  = false ;
	private String hiddenCreditCodefrmId = "";
	private String creditCode = "";
	private String creditName = "";
	private String miscSalCreditCode = "";
	private String miscSalCreditHead = "";
	
	
	private String centerCode = "";
	private String centerName = "";
	private String miscSalCenterCode = "";
	private String miscSalCenterName = "";
	
	
	public String getMiscSalCenterCode() {
		return miscSalCenterCode;
	}
	public void setMiscSalCenterCode(String miscSalCenterCode) {
		this.miscSalCenterCode = miscSalCenterCode;
	}
	public String getMiscSalCenterName() {
		return miscSalCenterName;
	}
	public void setMiscSalCenterName(String miscSalCenterName) {
		this.miscSalCenterName = miscSalCenterName;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getHiddenCreditCodefrmId() {
		return hiddenCreditCodefrmId;
	}
	public void setHiddenCreditCodefrmId(String hiddenCreditCodefrmId) {
		this.hiddenCreditCodefrmId = hiddenCreditCodefrmId;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getMiscSalCreditCode() {
		return miscSalCreditCode;
	}
	public void setMiscSalCreditCode(String miscSalCreditCode) {
		this.miscSalCreditCode = miscSalCreditCode;
	}
	public String getMiscSalCreditHead() {
		return miscSalCreditHead;
	}
	public void setMiscSalCreditHead(String miscSalCreditHead) {
		this.miscSalCreditHead = miscSalCreditHead;
	}
	public boolean isInsertRecordFlag() {
		return insertRecordFlag;
	}
	public void setInsertRecordFlag(boolean insertRecordFlag) {
		this.insertRecordFlag = insertRecordFlag;
	}
	public boolean isUpdateRecordFlag() {
		return updateRecordFlag;
	}
	public void setUpdateRecordFlag(boolean updateRecordFlag) {
		this.updateRecordFlag = updateRecordFlag;
	}
	public String getMiscSalConfigAuth() {
		return miscSalConfigAuth;
	}
	public void setMiscSalConfigAuth(String miscSalConfigAuth) {
		this.miscSalConfigAuth = miscSalConfigAuth;
	}
	public boolean isMiscSalaryUploadListPage() {
		return miscSalaryUploadListPage;
	}
	public void setMiscSalaryUploadListPage(boolean miscSalaryUploadListPage) {
		this.miscSalaryUploadListPage = miscSalaryUploadListPage;
	}
	public List getMiscSalaryUploadList() {
		return miscSalaryUploadList;
	}
	public void setMiscSalaryUploadList(List miscSalaryUploadList) {
		this.miscSalaryUploadList = miscSalaryUploadList;
	}
	public String getMiscSalaryUploadHiddenId() {
		return miscSalaryUploadHiddenId;
	}
	public void setMiscSalaryUploadHiddenId(String miscSalaryUploadHiddenId) {
		this.miscSalaryUploadHiddenId = miscSalaryUploadHiddenId;
	}
	public String getManagerEmpAuthId() {
		return managerEmpAuthId;
	}
	public void setManagerEmpAuthId(String managerEmpAuthId) {
		this.managerEmpAuthId = managerEmpAuthId;
	}
	public String getManagerEmpToken() {
		return managerEmpToken;
	}
	public void setManagerEmpToken(String managerEmpToken) {
		this.managerEmpToken = managerEmpToken;
	}
	public String getManagerEmpName() {
		return managerEmpName;
	}
	public void setManagerEmpName(String managerEmpName) {
		this.managerEmpName = managerEmpName;
	}
	public String getMiscSalDivId() {
		return miscSalDivId;
	}
	public void setMiscSalDivId(String miscSalDivId) {
		this.miscSalDivId = miscSalDivId;
	}
	public String getMiscSalDivName() {
		return miscSalDivName;
	}
	public void setMiscSalDivName(String miscSalDivName) {
		this.miscSalDivName = miscSalDivName;
	}
	public String getMiscSalDeptId() {
		return miscSalDeptId;
	}
	public void setMiscSalDeptId(String miscSalDeptId) {
		this.miscSalDeptId = miscSalDeptId;
	}
	public String getMiscSalDeptName() {
		return miscSalDeptName;
	}
	public void setMiscSalDeptName(String miscSalDeptName) {
		this.miscSalDeptName = miscSalDeptName;
	}
	public String getMiscSalPayBillId() {
		return miscSalPayBillId;
	}
	public void setMiscSalPayBillId(String miscSalPayBillId) {
		this.miscSalPayBillId = miscSalPayBillId;
	}
	public String getMiscSalPayBillName() {
		return miscSalPayBillName;
	}
	public void setMiscSalPayBillName(String miscSalPayBillName) {
		this.miscSalPayBillName = miscSalPayBillName;
	}
	public String getMiscSalComponentCode() {
		return miscSalComponentCode;
	}
	public void setMiscSalComponentCode(String miscSalComponentCode) {
		this.miscSalComponentCode = miscSalComponentCode;
	}
	public String getMiscSalComponentHead() {
		return miscSalComponentHead;
	}
	public void setMiscSalComponentHead(String miscSalComponentHead) {
		this.miscSalComponentHead = miscSalComponentHead;
	}
	public String getMiscSalaryAuthId() {
		return miscSalaryAuthId;
	}
	public void setMiscSalaryAuthId(String miscSalaryAuthId) {
		this.miscSalaryAuthId = miscSalaryAuthId;
	}
	public String getConfigAuth() {
		return configAuth;
	}
	public void setConfigAuth(String configAuth) {
		this.configAuth = configAuth;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getManagerToken() {
		return managerToken;
	}
	public void setManagerToken(String managerToken) {
		this.managerToken = managerToken;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getDebitAbbr() {
		return debitAbbr;
	}
	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}
	public ArrayList getArr() {
		return arr;
	}
	public void setArr(ArrayList arr) {
		this.arr = arr;
	}
	public String getFrmId() {
		return frmId;
	}
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	public String getHiddenfrmId() {
		return hiddenfrmId;
	}
	public void setHiddenfrmId(String hiddenfrmId) {
		this.hiddenfrmId = hiddenfrmId;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public ArrayList getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public ArrayList getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}
	public String getDispEmpId() {
		return dispEmpId;
	}
	public void setDispEmpId(String dispEmpId) {
		this.dispEmpId = dispEmpId;
	}
	public String getDispEmpName() {
		return dispEmpName;
	}
	public void setDispEmpName(String dispEmpName) {
		this.dispEmpName = dispEmpName;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getCellNum() {
		return cellNum;
	}
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	public String getHiddenAuthType() {
		return hiddenAuthType;
	}
	public void setHiddenAuthType(String hiddenAuthType) {
		this.hiddenAuthType = hiddenAuthType;
	}
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	 
 

}
