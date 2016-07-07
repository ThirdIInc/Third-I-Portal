/* Bhushan Dasare Apr 20, 2010*/

package org.paradyne.bean.DataMigration;

import org.paradyne.lib.BeanBase;

public class EmpDetailsUpload extends BeanBase {
	private String dataPath;
	private String status = "Fail";
	private String note = "";
	private String uploadName;
	private String uploadFileName;
	
	private String divisionId;
	private String divisionName;
	private String branchId;
	private String branchName;
	private String departmentId;
	private String departmentName;
	private String designationId;
	private String designationName;
	private String employeeTypeId;
	private String employeeTypeName;
	private String payBillId;
	private String payBillName;
	private String uploadType;
	private String downloadType;
	private String invCode="";
	private boolean dataExists = false;
	private boolean fileUploaded = false;
	
	private String perCode="";
	private String perName="";
	private String invName = "";
	
	/**
	 * @return the invName
	 */
	public String getInvName() {
		return invName;
	}
	/**
	 * @param invName the invName to set
	 */
	public void setInvName(String invName) {
		this.invName = invName;
	}
	/**
	 * @return the perCode
	 */
	public String getPerCode() {
		return perCode;
	}
	/**
	 * @param perCode the perCode to set
	 */
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	/**
	 * @return the perName
	 */
	public String getPerName() {
		return perName;
	}
	/**
	 * @param perName the perName to set
	 */
	public void setPerName(String perName) {
		this.perName = perName;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the uploadName
	 */
	public String getUploadName() {
		return uploadName;
	}
	/**
	 * @param uploadName the uploadName to set
	 */
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the designationId
	 */
	public String getDesignationId() {
		return designationId;
	}
	/**
	 * @param designationId the designationId to set
	 */
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	/**
	 * @return the designationName
	 */
	public String getDesignationName() {
		return designationName;
	}
	/**
	 * @param designationName the designationName to set
	 */
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	/**
	 * @return the employeeTypeId
	 */
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	/**
	 * @param employeeTypeId the employeeTypeId to set
	 */
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	/**
	 * @return the employeeTypeName
	 */
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	/**
	 * @param employeeTypeName the employeeTypeName to set
	 */
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	/**
	 * @return the payBillId
	 */
	public String getPayBillId() {
		return payBillId;
	}
	/**
	 * @param payBillId the payBillId to set
	 */
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}
	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	/**
	 * @return the uploadType
	 */
	public String getUploadType() {
		return uploadType;
	}
	/**
	 * @param uploadType the uploadType to set
	 */
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	/**
	 * @return the downloadType
	 */
	public String getDownloadType() {
		return downloadType;
	}
	/**
	 * @param downloadType the downloadType to set
	 */
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	/**
	 * @return the dataExists
	 */
	public boolean isDataExists() {
		return dataExists;
	}
	/**
	 * @param dataExists the dataExists to set
	 */
	public void setDataExists(boolean dataExists) {
		this.dataExists = dataExists;
	}
	/**
	 * @return the fileUploaded
	 */
	public boolean isFileUploaded() {
		return fileUploaded;
	}
	/**
	 * @param fileUploaded the fileUploaded to set
	 */
	public void setFileUploaded(boolean fileUploaded) {
		this.fileUploaded = fileUploaded;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
}