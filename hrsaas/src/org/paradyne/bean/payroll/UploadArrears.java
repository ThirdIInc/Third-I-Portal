/**
 * 
 */
package org.paradyne.bean.payroll;
import org.paradyne.lib.BeanBase;
/**
 * @author AA0554
 *
 */
public class UploadArrears extends BeanBase {
	private String month;
	private String year;
	private String divisionName;
	private String divisionId;
	private String branchId;
	private String branchName;
	private String departmentId;
	private String departmentName;
	private String uploadCreditName;
	private String uploadCreditCode;
	private String uploadFileName;
	private String paybillId;
	private String paybillName;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getUploadCreditName() {
		return uploadCreditName;
	}
	public void setUploadCreditName(String uploadCreditName) {
		this.uploadCreditName = uploadCreditName;
	}
	public String getUploadCreditCode() {
		return uploadCreditCode;
	}
	public void setUploadCreditCode(String uploadCreditCode) {
		this.uploadCreditCode = uploadCreditCode;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the paybillId
	 */
	public String getPaybillId() {
		return paybillId;
	}
	/**
	 * @param paybillId the paybillId to set
	 */
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	/**
	 * @return the paybillName
	 */
	public String getPaybillName() {
		return paybillName;
	}
	/**
	 * @param paybillName the paybillName to set
	 */
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	
}
