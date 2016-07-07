/**
 * 
 */
package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 * @since 17/04/2009
 *
 */
public class LeavePolicySetting extends BeanBase {
	//for text fields
	private String settingCode="";
	private String divisionName="";
	private String divisionCode="";
	private String empTypeName="";
	private String empTypeCode="";
	private String tokenNo="";
	private String deptName="";
	private String deptCode="";
	private String branchName="";
	private String branchCode="";
	private String desgName="";
	private String desgCode="";
	private String employeeName="";
	private String employeeCode="";
	private String policyName="";
	private String policyCode="";
	
	//for iterator names
	private String divisionNameItr="";
	private String empTypeNameItr="";
	private String deptNameItr="";
	private String branchNameItr="";
	private String desgNameItr="";
	private String policyNameItr="";
	private String policyNameItt="";
	private String employeeNameItr="";
	private String divisionNameItt="";
	
	//for iterator codes
	private String divisionCodeItr="";
	private String empTypeCodeItr="";
	private String deptCodeItr="";
	private String branchCodeItr="";
	private String desgCodeItr="";
	private String policyCodeItr="";
	private String policyCodeItt="";
	private String employeeCodeItr="";
	private String settingCodeItt="";
	private String divisionCodeItt="";
	
	private String hiddenCode="";
	private String hiddenDivCode="";
	
	//flags for button panel
	private boolean nextFlag=false;
	private boolean onLoadFlag=false;
	private boolean newPolicyFlag=false;
	private boolean newExceptionFlag=false;
	private boolean policyCancel=false;
	private boolean excepCancel=false;
	private boolean editPolicies=false;
	private boolean editExceptions=false;
	
	ArrayList tableList;
	private String listLength="0";
	ArrayList employeeList;
	private String empListLength="0";
	private String noData="false";
	private String noEmpData="false";
	
	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	/**
	 * @return the empListLength
	 */
	public String getEmpListLength() {
		return empListLength;
	}
	/**
	 * @param empListLength the empListLength to set
	 */
	public void setEmpListLength(String empListLength) {
		this.empListLength = empListLength;
	}
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	/**
	 * @return the employeeList
	 */
	public ArrayList getEmployeeList() {
		return employeeList;
	}
	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}
	/**
	 * @return the tableList
	 */
	public ArrayList getTableList() {
		return tableList;
	}
	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
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
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	/**
	 * @return the empTypeName
	 */
	public String getEmpTypeName() {
		return empTypeName;
	}
	/**
	 * @param empTypeName the empTypeName to set
	 */
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	/**
	 * @return the empTypeCode
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	/**
	 * @param empTypeCode the empTypeCode to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}
	/**
	 * @param desgName the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	/**
	 * @return the desgCode
	 */
	public String getDesgCode() {
		return desgCode;
	}
	/**
	 * @param desgCode the desgCode to set
	 */
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
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
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the policyName
	 */
	public String getPolicyName() {
		return policyName;
	}
	/**
	 * @param policyName the policyName to set
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	/**
	 * @return the policyCode
	 */
	public String getPolicyCode() {
		return policyCode;
	}
	/**
	 * @param policyCode the policyCode to set
	 */
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	/**
	 * @return the empTypeNameItr
	 */
	public String getEmpTypeNameItr() {
		return empTypeNameItr;
	}
	/**
	 * @param empTypeNameItr the empTypeNameItr to set
	 */
	public void setEmpTypeNameItr(String empTypeNameItr) {
		this.empTypeNameItr = empTypeNameItr;
	}
	/**
	 * @return the deptNameItr
	 */
	public String getDeptNameItr() {
		return deptNameItr;
	}
	/**
	 * @param deptNameItr the deptNameItr to set
	 */
	public void setDeptNameItr(String deptNameItr) {
		this.deptNameItr = deptNameItr;
	}
	/**
	 * @return the branchNameItr
	 */
	public String getBranchNameItr() {
		return branchNameItr;
	}
	/**
	 * @param branchNameItr the branchNameItr to set
	 */
	public void setBranchNameItr(String branchNameItr) {
		this.branchNameItr = branchNameItr;
	}
	/**
	 * @return the desgNameItr
	 */
	public String getDesgNameItr() {
		return desgNameItr;
	}
	/**
	 * @param desgNameItr the desgNameItr to set
	 */
	public void setDesgNameItr(String desgNameItr) {
		this.desgNameItr = desgNameItr;
	}
	/**
	 * @return the policyNameItr
	 */
	public String getPolicyNameItr() {
		return policyNameItr;
	}
	/**
	 * @param policyNameItr the policyNameItr to set
	 */
	public void setPolicyNameItr(String policyNameItr) {
		this.policyNameItr = policyNameItr;
	}
	/**
	 * @return the employeeNameItr
	 */
	public String getEmployeeNameItr() {
		return employeeNameItr;
	}
	/**
	 * @param employeeNameItr the employeeNameItr to set
	 */
	public void setEmployeeNameItr(String employeeNameItr) {
		this.employeeNameItr = employeeNameItr;
	}
	/**
	 * @return the settingCode
	 */
	public String getSettingCode() {
		return settingCode;
	}
	/**
	 * @param settingCode the settingCode to set
	 */
	public void setSettingCode(String settingCode) {
		this.settingCode = settingCode;
	}
	/**
	 * @return the empTypeCodeItr
	 */
	public String getEmpTypeCodeItr() {
		return empTypeCodeItr;
	}
	/**
	 * @param empTypeCodeItr the empTypeCodeItr to set
	 */
	public void setEmpTypeCodeItr(String empTypeCodeItr) {
		this.empTypeCodeItr = empTypeCodeItr;
	}
	/**
	 * @return the deptCodeItr
	 */
	public String getDeptCodeItr() {
		return deptCodeItr;
	}
	/**
	 * @param deptCodeItr the deptCodeItr to set
	 */
	public void setDeptCodeItr(String deptCodeItr) {
		this.deptCodeItr = deptCodeItr;
	}
	/**
	 * @return the branchCodeItr
	 */
	public String getBranchCodeItr() {
		return branchCodeItr;
	}
	/**
	 * @param branchCodeItr the branchCodeItr to set
	 */
	public void setBranchCodeItr(String branchCodeItr) {
		this.branchCodeItr = branchCodeItr;
	}
	/**
	 * @return the desgCodeItr
	 */
	public String getDesgCodeItr() {
		return desgCodeItr;
	}
	/**
	 * @param desgCodeItr the desgCodeItr to set
	 */
	public void setDesgCodeItr(String desgCodeItr) {
		this.desgCodeItr = desgCodeItr;
	}
	/**
	 * @return the policyCodeItr
	 */
	public String getPolicyCodeItr() {
		return policyCodeItr;
	}
	/**
	 * @param policyCodeItr the policyCodeItr to set
	 */
	public void setPolicyCodeItr(String policyCodeItr) {
		this.policyCodeItr = policyCodeItr;
	}
	/**
	 * @return the employeeCodeItr
	 */
	public String getEmployeeCodeItr() {
		return employeeCodeItr;
	}
	/**
	 * @param employeeCodeItr the employeeCodeItr to set
	 */
	public void setEmployeeCodeItr(String employeeCodeItr) {
		this.employeeCodeItr = employeeCodeItr;
	}
	/**
	 * @return the divisionNameItr
	 */
	public String getDivisionNameItr() {
		return divisionNameItr;
	}
	/**
	 * @param divisionNameItr the divisionNameItr to set
	 */
	public void setDivisionNameItr(String divisionNameItr) {
		this.divisionNameItr = divisionNameItr;
	}
	/**
	 * @return the divisionCodeItr
	 */
	public String getDivisionCodeItr() {
		return divisionCodeItr;
	}
	/**
	 * @param divisionCodeItr the divisionCodeItr to set
	 */
	public void setDivisionCodeItr(String divisionCodeItr) {
		this.divisionCodeItr = divisionCodeItr;
	}
	/**
	 * @return the hiddenCode
	 */
	public String getHiddenCode() {
		return hiddenCode;
	}
	/**
	 * @param hiddenCode the hiddenCode to set
	 */
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	/**
	 * @return the policyNameItt
	 */
	public String getPolicyNameItt() {
		return policyNameItt;
	}
	/**
	 * @param policyNameItt the policyNameItt to set
	 */
	public void setPolicyNameItt(String policyNameItt) {
		this.policyNameItt = policyNameItt;
	}
	/**
	 * @return the policyCodeItt
	 */
	public String getPolicyCodeItt() {
		return policyCodeItt;
	}
	/**
	 * @param policyCodeItt the policyCodeItt to set
	 */
	public void setPolicyCodeItt(String policyCodeItt) {
		this.policyCodeItt = policyCodeItt;
	}
	/**
	 * @return the settingCodeItt
	 */
	public String getSettingCodeItt() {
		return settingCodeItt;
	}
	/**
	 * @param settingCodeItt the settingCodeItt to set
	 */
	public void setSettingCodeItt(String settingCodeItt) {
		this.settingCodeItt = settingCodeItt;
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
	 * @return the divisionCodeItt
	 */
	public String getDivisionCodeItt() {
		return divisionCodeItt;
	}
	/**
	 * @param divisionCodeItt the divisionCodeItt to set
	 */
	public void setDivisionCodeItt(String divisionCodeItt) {
		this.divisionCodeItt = divisionCodeItt;
	}
	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the noEmpData
	 */
	public String getNoEmpData() {
		return noEmpData;
	}
	/**
	 * @param noEmpData the noEmpData to set
	 */
	public void setNoEmpData(String noEmpData) {
		this.noEmpData = noEmpData;
	}
	/**
	 * @return the nextFlag
	 */
	public boolean isNextFlag() {
		return nextFlag;
	}
	/**
	 * @param nextFlag the nextFlag to set
	 */
	public void setNextFlag(boolean nextFlag) {
		this.nextFlag = nextFlag;
	}
	/**
	 * @return the onLoadFlag
	 */
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	/**
	 * @param onLoadFlag the onLoadFlag to set
	 */
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	/**
	 * @return the newPolicyFlag
	 */
	public boolean isNewPolicyFlag() {
		return newPolicyFlag;
	}
	/**
	 * @param newPolicyFlag the newPolicyFlag to set
	 */
	public void setNewPolicyFlag(boolean newPolicyFlag) {
		this.newPolicyFlag = newPolicyFlag;
	}
	/**
	 * @return the newExceptionFlag
	 */
	public boolean isNewExceptionFlag() {
		return newExceptionFlag;
	}
	/**
	 * @param newExceptionFlag the newExceptionFlag to set
	 */
	public void setNewExceptionFlag(boolean newExceptionFlag) {
		this.newExceptionFlag = newExceptionFlag;
	}
	/**
	 * @return the policyCancel
	 */
	public boolean isPolicyCancel() {
		return policyCancel;
	}
	/**
	 * @param policyCancel the policyCancel to set
	 */
	public void setPolicyCancel(boolean policyCancel) {
		this.policyCancel = policyCancel;
	}
	/**
	 * @return the excepCancel
	 */
	public boolean isExcepCancel() {
		return excepCancel;
	}
	/**
	 * @param excepCancel the excepCancel to set
	 */
	public void setExcepCancel(boolean excepCancel) {
		this.excepCancel = excepCancel;
	}
	/**
	 * @return the hiddenDivCode
	 */
	public String getHiddenDivCode() {
		return hiddenDivCode;
	}
	/**
	 * @param hiddenDivCode the hiddenDivCode to set
	 */
	public void setHiddenDivCode(String hiddenDivCode) {
		this.hiddenDivCode = hiddenDivCode;
	}
	/**
	 * @return the editPolicies
	 */
	public boolean isEditPolicies() {
		return editPolicies;
	}
	/**
	 * @param editPolicies the editPolicies to set
	 */
	public void setEditPolicies(boolean editPolicies) {
		this.editPolicies = editPolicies;
	}
	/**
	 * @return the editExceptions
	 */
	public boolean isEditExceptions() {
		return editExceptions;
	}
	/**
	 * @param editExceptions the editExceptions to set
	 */
	public void setEditExceptions(boolean editExceptions) {
		this.editExceptions = editExceptions;
	}
}//end of class