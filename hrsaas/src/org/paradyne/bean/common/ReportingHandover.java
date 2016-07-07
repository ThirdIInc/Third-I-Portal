
package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author tarunc
 * @date Oct 16, 2008
 * @description ReportingHandover class serves as bean for reporting hand over form
 */
public class ReportingHandover extends BeanBase {
	private String fromEmpCode = "";
	private String fromEmpTokenNo = "";
	private String fromEmpName = "";
	private String toEmpCode = "";
	private String toEmpTokenNo = "";
	private String toEmpName = "";
	
	private String reportingHdrCode = "";
	private String reportingDtlCode = "";
	
	private String structureDefinedFor = "";
	private String reportingType = "";
	private String approverType = "";
	private String level = "";
	
	ArrayList<Object> approverDataList = new ArrayList<Object>();
	
	/**
	 * @return the structureDefinedFor
	 */
	public String getStructureDefinedFor() {
		return structureDefinedFor;
	}
	/**
	 * @param structureDefinedFor the structureDefinedFor to set
	 */
	public void setStructureDefinedFor(String structureDefinedFor) {
		this.structureDefinedFor = structureDefinedFor;
	}
	/**
	 * @return the reportingType
	 */
	public String getReportingType() {
		return reportingType;
	}
	/**
	 * @param reportingType the reportingType to set
	 */
	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}
	/**
	 * @return the approverType
	 */
	public String getApproverType() {
		return approverType;
	}
	/**
	 * @param approverType the approverType to set
	 */
	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the approverDataList
	 */
	public ArrayList<Object> getApproverDataList() {
		return approverDataList;
	}
	/**
	 * @param approverDataList the approverDataList to set
	 */
	public void setApproverDataList(ArrayList<Object> approverDataList) {
		this.approverDataList = approverDataList;
	}
	/**
	 * @return the fromEmpCode
	 */
	public String getFromEmpCode() {
		return fromEmpCode;
	}
	/**
	 * @param fromEmpCode the fromEmpCode to set
	 */
	public void setFromEmpCode(String fromEmpCode) {
		this.fromEmpCode = fromEmpCode;
	}
	/**
	 * @return the fromEmpTokenNo
	 */
	public String getFromEmpTokenNo() {
		return fromEmpTokenNo;
	}
	/**
	 * @param fromEmpTokenNo the fromEmpTokenNo to set
	 */
	public void setFromEmpTokenNo(String fromEmpTokenNo) {
		this.fromEmpTokenNo = fromEmpTokenNo;
	}
	/**
	 * @return the fromEmpName
	 */
	public String getFromEmpName() {
		return fromEmpName;
	}
	/**
	 * @param fromEmpName the fromEmpName to set
	 */
	public void setFromEmpName(String fromEmpName) {
		this.fromEmpName = fromEmpName;
	}
	/**
	 * @return the toEmpCode
	 */
	public String getToEmpCode() {
		return toEmpCode;
	}
	/**
	 * @param toEmpCode the toEmpCode to set
	 */
	public void setToEmpCode(String toEmpCode) {
		this.toEmpCode = toEmpCode;
	}
	/**
	 * @return the toEmpTokenNo
	 */
	public String getToEmpTokenNo() {
		return toEmpTokenNo;
	}
	/**
	 * @param toEmpTokenNo the toEmpTokenNo to set
	 */
	public void setToEmpTokenNo(String toEmpTokenNo) {
		this.toEmpTokenNo = toEmpTokenNo;
	}
	/**
	 * @return the toEmpName
	 */
	public String getToEmpName() {
		return toEmpName;
	}
	/**
	 * @param toEmpName the toEmpName to set
	 */
	public void setToEmpName(String toEmpName) {
		this.toEmpName = toEmpName;
	}
	/**
	 * @return the reportingHdrCode
	 */
	public String getReportingHdrCode() {
		return reportingHdrCode;
	}
	/**
	 * @param reportingHdrCode the reportingHdrCode to set
	 */
	public void setReportingHdrCode(String reportingHdrCode) {
		this.reportingHdrCode = reportingHdrCode;
	}
	/**
	 * @return the reportingDtlCode
	 */
	public String getReportingDtlCode() {
		return reportingDtlCode;
	}
	/**
	 * @param reportingDtlCode the reportingDtlCode to set
	 */
	public void setReportingDtlCode(String reportingDtlCode) {
		this.reportingDtlCode = reportingDtlCode;
	}

}
