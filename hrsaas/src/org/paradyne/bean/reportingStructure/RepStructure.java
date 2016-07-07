package org.paradyne.bean.reportingStructure;

import org.paradyne.lib.BeanBase;

public class RepStructure extends BeanBase {

	/**
	 * @author KRISHNA
	 * 13-08-2008
	 */
	private String empId;
	private String empTokenNo;
	private String empName;
	private String deptId;
	private String brnchId;
	private String desgnId;
	// for header code
	private String headerCode;
	private String sameAsType;
	// for same Type
	private String sameType;
	// for default case
	private String defaultFlag = "false";

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getBrnchId() {
		return brnchId;
	}

	public void setBrnchId(String brnchId) {
		this.brnchId = brnchId;
	}

	public String getDesgnId() {
		return desgnId;
	}

	public void setDesgnId(String desgnId) {
		this.desgnId = desgnId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getSameAsType() {
		return sameAsType;
	}

	public void setSameAsType(String sameAsType) {
		this.sameAsType = sameAsType;
	}

	public String getSameType() {
		return sameType;
	}

	public void setSameType(String sameType) {
		this.sameType = sameType;
	}

}
