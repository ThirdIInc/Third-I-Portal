
package org.paradyne.bean.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Tarun Chaturvedi
 * 
 * Bean class for Reporting Structure
 *
 */
public class ReportingStructure extends BeanBase {
	
	/**
	 * Declaring bean properties
	 */
	private String deptCode;
	private String reqDept;
	private String brnCode;
	private String reqBrn;
	private String empCode;
	private String empName;
	private String empId;
	private String empNameAdd;
	private ArrayList empList;
	private String desgName;
	private String desgId;
	private String designationCode;
	private String designationName;
	
	public String alternateEmpId;
	public String alternateEmpToken;
	public String alternateEmpName;
	
	private String depbrnCheck;
	private String empCheck;
	private String hdrCode;
	private String reportingType;
	private String checkDeptFlag;
	private String checkEmpFlag;
	private String empTokenNo;
	private String empTokenAdd;
	private String StructureType;
	private String linkType;
	private String structure="";
	private String Structure="";
	private String defaultCheck;
	private String sameAsCheck;
	private String sameStr;
	private String defaultAllCheck;
	private String defaultName;
	private String structureFor;
	private HashMap structureMap;
	private String structureFlag;
	private String structureKey="";
	private String hiddenStrucutre;
	
	private String empIdIterator;
	private String empTokenIterator;
	private String empNameIterator;
	private String desgIdIterator;
	private String desgNameIterator;
	private String srNoIterator;
	
	public String alternateEmpIdIterator;
	public String alternateEmpTokenIterator;
	public String alternateEmpNameIterator;
	
	public String rowNum;
	private String srNo;
	
	//ADDED BY REEBA
	private String managerRecord;
	private String managerLevel="";
	private String fromEmpCode = "";
	private String fromEmpToken = "";
	private String fromEmpName = "";
	private String toEmpCode = "";
	private String toEmpToken = "";
	private String toEmpName = "";
	private String fromEmpIdIt = "";
	private String fromEmpTokenIt = "";
	private String fromEmpNameIt = "";
	private String toEmpIdIt = "";
	private String toEmpTokenIt = "";
	private String toEmpNameIt = "";
	private ArrayList exceptionEmpList;
	private String excepSrNoIterator;
	public String excepRowNum;
	private String excepSrNo;
	private String fromEmpIdValues = "";
	private String toEmpIdValues = "";
	
	TreeMap tmap;
	
	 
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getEmpIdIterator() {
		return empIdIterator;
	}
	public void setEmpIdIterator(String empIdIterator) {
		this.empIdIterator = empIdIterator;
	}
	public String getEmpTokenIterator() {
		return empTokenIterator;
	}
	public void setEmpTokenIterator(String empTokenIterator) {
		this.empTokenIterator = empTokenIterator;
	}
	public String getEmpNameIterator() {
		return empNameIterator;
	}
	public void setEmpNameIterator(String empNameIterator) {
		this.empNameIterator = empNameIterator;
	}
	public String getDesgIdIterator() {
		return desgIdIterator;
	}
	public void setDesgIdIterator(String desgIdIterator) {
		this.desgIdIterator = desgIdIterator;
	}
	public String getDesgNameIterator() {
		return desgNameIterator;
	}
	public void setDesgNameIterator(String desgNameIterator) {
		this.desgNameIterator = desgNameIterator;
	}
	public String getStructureFor() {
		return structureFor;
	}
	public void setStructureFor(String structureFor) {
		this.structureFor = structureFor;
	}
	public String getDefaultCheck() {
		return defaultCheck;
	}
	public void setDefaultCheck(String defaultCheck) {
		this.defaultCheck = defaultCheck;
	}
	public String getSameAsCheck() {
		return sameAsCheck;
	}
	public void setSameAsCheck(String sameAsCheck) {
		this.sameAsCheck = sameAsCheck;
	}
	public String getSameStr() {
		return sameStr;
	}
	public void setSameStr(String sameStr) {
		this.sameStr = sameStr;
	}
	public String getDefaultAllCheck() {
		return defaultAllCheck;
	}
	public void setDefaultAllCheck(String defaultAllCheck) {
		this.defaultAllCheck = defaultAllCheck;
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getEmpTokenAdd() {
		return empTokenAdd;
	}
	public void setEmpTokenAdd(String empTokenAdd) {
		this.empTokenAdd = empTokenAdd;
	}
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	/**
	 * getter/setter methods for all bean properties
	 */
	  
	public String getCheckEmpFlag() {
		return checkEmpFlag;
	}
	public void setCheckEmpFlag(String checkEmpFlag) {
		this.checkEmpFlag = checkEmpFlag;
	}
	public String getCheckDeptFlag() {
		return checkDeptFlag;
	}
	public void setCheckDeptFlag(String checkDeptFlag) {
		this.checkDeptFlag = checkDeptFlag;
	}
	public String getReportingType() {
		return reportingType;
	}
	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}
	public String getHdrCode() {
		return hdrCode;
	}
	public void setHdrCode(String hdrCode) {
		this.hdrCode = hdrCode;
	}
	public String getDepbrnCheck() {
		return depbrnCheck;
	}
	public void setDepbrnCheck(String depbrnCheck) {
		this.depbrnCheck = depbrnCheck;
	}
	public String getEmpCheck() {
		return empCheck;
	}
	public void setEmpCheck(String empCheck) {
		this.empCheck = empCheck;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getDesgId() {
		return desgId;
	}
	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getReqDept() {
		return reqDept;
	}
	public void setReqDept(String reqDept) {
		this.reqDept = reqDept;
	}
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public String getReqBrn() {
		return reqBrn;
	}
	public void setReqBrn(String reqBrn) {
		this.reqBrn = reqBrn;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getEmpNameAdd() {
		return empNameAdd;
	}
	public void setEmpNameAdd(String empNameAdd) {
		this.empNameAdd = empNameAdd;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getStructureType() {
		return StructureType;
	}
	public void setStructureType(String structureType) {
		StructureType = structureType;
	}
	public HashMap getStructureMap() {
		return structureMap;
	}
	public void setStructureMap(HashMap structureMap) {
		this.structureMap = structureMap;
	}
	public String getStructureFlag() {
		return structureFlag;
	}
	public void setStructureFlag(String structureFlag) {
		this.structureFlag = structureFlag;
	}
	public String getSrNoIterator() {
		return srNoIterator;
	}
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getStructureKey() {
		return structureKey;
	}
	public void setStructureKey(String structureKey) {
		this.structureKey = structureKey;
	}
	public String getAlternateEmpId() {
		return alternateEmpId;
	}
	public void setAlternateEmpId(String alternateEmpId) {
		this.alternateEmpId = alternateEmpId;
	}
	public String getAlternateEmpToken() {
		return alternateEmpToken;
	}
	public void setAlternateEmpToken(String alternateEmpToken) {
		this.alternateEmpToken = alternateEmpToken;
	}
	public String getAlternateEmpName() {
		return alternateEmpName;
	}
	public void setAlternateEmpName(String alternateEmpName) {
		this.alternateEmpName = alternateEmpName;
	}
	public String getAlternateEmpIdIterator() {
		return alternateEmpIdIterator;
	}
	public void setAlternateEmpIdIterator(String alternateEmpIdIterator) {
		this.alternateEmpIdIterator = alternateEmpIdIterator;
	}
	public String getAlternateEmpTokenIterator() {
		return alternateEmpTokenIterator;
	}
	public void setAlternateEmpTokenIterator(String alternateEmpTokenIterator) {
		this.alternateEmpTokenIterator = alternateEmpTokenIterator;
	}
	public String getAlternateEmpNameIterator() {
		return alternateEmpNameIterator;
	}
	public void setAlternateEmpNameIterator(String alternateEmpNameIterator) {
		this.alternateEmpNameIterator = alternateEmpNameIterator;
	}
	/**
	 * @return the hiddenStrucutre
	 */
	public String getHiddenStrucutre() {
		return hiddenStrucutre;
	}
	/**
	 * @param hiddenStrucutre the hiddenStrucutre to set
	 */
	public void setHiddenStrucutre(String hiddenStrucutre) {
		this.hiddenStrucutre = hiddenStrucutre;
	}
	public TreeMap getTmap() {
		return tmap;
	}
	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}
	public String getManagerRecord() {
		return managerRecord;
	}
	public void setManagerRecord(String managerRecord) {
		this.managerRecord = managerRecord;
	}
	public String getManagerLevel() {
		return managerLevel;
	}
	public void setManagerLevel(String managerLevel) {
		this.managerLevel = managerLevel;
	}
	public String getFromEmpCode() {
		return fromEmpCode;
	}
	public void setFromEmpCode(String fromEmpCode) {
		this.fromEmpCode = fromEmpCode;
	}
	public String getFromEmpToken() {
		return fromEmpToken;
	}
	public void setFromEmpToken(String fromEmpToken) {
		this.fromEmpToken = fromEmpToken;
	}
	public String getFromEmpName() {
		return fromEmpName;
	}
	public void setFromEmpName(String fromEmpName) {
		this.fromEmpName = fromEmpName;
	}
	public String getToEmpCode() {
		return toEmpCode;
	}
	public void setToEmpCode(String toEmpCode) {
		this.toEmpCode = toEmpCode;
	}
	public String getToEmpToken() {
		return toEmpToken;
	}
	public void setToEmpToken(String toEmpToken) {
		this.toEmpToken = toEmpToken;
	}
	public String getToEmpName() {
		return toEmpName;
	}
	public void setToEmpName(String toEmpName) {
		this.toEmpName = toEmpName;
	}
	public String getFromEmpIdIt() {
		return fromEmpIdIt;
	}
	public void setFromEmpIdIt(String fromEmpIdIt) {
		this.fromEmpIdIt = fromEmpIdIt;
	}
	public String getFromEmpTokenIt() {
		return fromEmpTokenIt;
	}
	public void setFromEmpTokenIt(String fromEmpTokenIt) {
		this.fromEmpTokenIt = fromEmpTokenIt;
	}
	public String getFromEmpNameIt() {
		return fromEmpNameIt;
	}
	public void setFromEmpNameIt(String fromEmpNameIt) {
		this.fromEmpNameIt = fromEmpNameIt;
	}
	public String getToEmpIdIt() {
		return toEmpIdIt;
	}
	public void setToEmpIdIt(String toEmpIdIt) {
		this.toEmpIdIt = toEmpIdIt;
	}
	public String getToEmpTokenIt() {
		return toEmpTokenIt;
	}
	public void setToEmpTokenIt(String toEmpTokenIt) {
		this.toEmpTokenIt = toEmpTokenIt;
	}
	public String getToEmpNameIt() {
		return toEmpNameIt;
	}
	public void setToEmpNameIt(String toEmpNameIt) {
		this.toEmpNameIt = toEmpNameIt;
	}
	public ArrayList getExceptionEmpList() {
		return exceptionEmpList;
	}
	public void setExceptionEmpList(ArrayList exceptionEmpList) {
		this.exceptionEmpList = exceptionEmpList;
	}
	public String getExcepSrNoIterator() {
		return excepSrNoIterator;
	}
	public void setExcepSrNoIterator(String excepSrNoIterator) {
		this.excepSrNoIterator = excepSrNoIterator;
	}
	public String getExcepRowNum() {
		return excepRowNum;
	}
	public void setExcepRowNum(String excepRowNum) {
		this.excepRowNum = excepRowNum;
	}
	public String getExcepSrNo() {
		return excepSrNo;
	}
	public void setExcepSrNo(String excepSrNo) {
		this.excepSrNo = excepSrNo;
	}
	public String getFromEmpIdValues() {
		return fromEmpIdValues;
	}
	public void setFromEmpIdValues(String fromEmpIdValues) {
		this.fromEmpIdValues = fromEmpIdValues;
	}
	public String getToEmpIdValues() {
		return toEmpIdValues;
	}
	public void setToEmpIdValues(String toEmpIdValues) {
		this.toEmpIdValues = toEmpIdValues;
	}

}
