package org.paradyne.bean.admin.srd;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author shashikant
 * 
 */
public class SendMail extends BeanBase {
	
	private String ccMailId= "";
	private String subject = "";
	private String message = "";
	private String uploadFileName = "";

	private String divCode = "";
	private String divisionCode = "";
	private String branchCode = "";
	private String deptCode = "";
	private String desgCode = "";
	private String employeeCode = "";
	private String empCode = "";

	private String divisionName;
	private String branchName;
	private String deptName;
	private String desgName;
	private String employeeName;
	private String mailCode;
	private String departmentName;
	private String departmentCode = "";
	private String divName;
	private String branch;
	private String confChk;
	private String anniconfChk;
	private String employee;
	private String empName;
	private String empid;
	private String hdeleteCode;
	private String frmId = "";
	private String hiddenfrmId = "";
	private String tempCode;
	private String tempName;
	private String token;
	private String flag;
	private String attachmentFile="";
	private String dataPath="";
	private boolean viewModeFlag=false;

	ArrayList list;
	ArrayList arrlist;

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getConfChk() {
		return confChk;
	}

	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getTempCode() {
		return tempCode;
	}

	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public ArrayList getArrlist() {
		return arrlist;
	}

	public void setArrlist(ArrayList arrlist) {
		this.arrlist = arrlist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAnniconfChk() {
		return anniconfChk;
	}

	public void setAnniconfChk(String anniconfChk) {
		this.anniconfChk = anniconfChk;
	}

	public String getCcMailId() {
		return ccMailId;
	}

	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}

	/**
	 * @return
	 */
	public String getDataPath() {
		return dataPath;
	}

	/**
	 * @param dataPath
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @return
	 */
	public String getAttachmentFile() {
		return attachmentFile;
	}

	/**
	 * @param attachmentFile
	 */
	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	/**
	 * @return
	 */
	public boolean isViewModeFlag() {
		return viewModeFlag;
	}

	/**
	 * @param viewModeFlag
	 */
	public void setViewModeFlag(boolean viewModeFlag) {
		this.viewModeFlag = viewModeFlag;
	}

}
