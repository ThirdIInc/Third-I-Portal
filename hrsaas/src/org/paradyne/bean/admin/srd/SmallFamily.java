package org.paradyne.bean.admin.srd;
import java.util.*;

import java.io.Serializable;

import org.paradyne.lib.BeanBase;

/*
 * author:Pradeep Ku. Sahoo
 */
public class SmallFamily extends BeanBase implements Serializable{
	
	String empId="";
	String empName="";
	String empDept="";
	String empCent="";
	String empTrade="";
	String normAdptBy="";
	String normAdptDt="";
	String normPayAmt="";
	String auth="";
	String payScale="";
	String ordrDt="";
	String authDt="";
	String normCode="";
	String payCode="";
	String empRank="";
	String employeeToken="";
	String slNo="";
	
	public ArrayList smallFamilyList=null;
	
	public SmallFamily(String empId, String empName, String empDept, String empCent, String empTrade, String normAdptBy, String normAdptDt, String normPayAmt, String auth, String payScale, String ordrDt, String authDt, String normCode, String payCode, String empRank, String employeeToken,String slNo,ArrayList smallFamilyList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empCent = empCent;
		this.empTrade = empTrade;
		this.normAdptBy = normAdptBy;
		this.normAdptDt = normAdptDt;
		this.normPayAmt = normPayAmt;
		this.auth = auth;
		this.payScale = payScale;
		this.ordrDt = ordrDt;
		this.authDt = authDt;
		this.normCode = normCode;
		this.payCode = payCode;
		this.empRank = empRank;
		this.employeeToken = employeeToken;
		this.slNo=slNo;//Added on 26.07.2007
		this.smallFamilyList = smallFamilyList;
	} 

	
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the authDt
	 */
	public String getAuthDt() {
		return authDt;
	}

	/**
	 * @param authDt the authDt to set
	 */
	public void setAuthDt(String authDt) {
		this.authDt = authDt;
	}

	/**
	 * @return the empCent
	 */
	public String getEmpCent() {
		return empCent;
	}

	/**
	 * @param empCent the empCent to set
	 */
	public void setEmpCent(String empCent) {
		this.empCent = empCent;
	}

	/**
	 * @return the empDept
	 */
	public String getEmpDept() {
		return empDept;
	}

	/**
	 * @param empDept the empDept to set
	 */
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empTrade
	 */
	public String getEmpTrade() {
		return empTrade;
	}

	/**
	 * @param empTrade the empTrade to set
	 */
	public void setEmpTrade(String empTrade) {
		this.empTrade = empTrade;
	}

	/**
	 * @return the normAdptBy
	 */
	public String getNormAdptBy() {
		return normAdptBy;
	}

	/**
	 * @param normAdptBy the normAdptBy to set
	 */
	public void setNormAdptBy(String normAdptBy) {
		this.normAdptBy = normAdptBy;
	}

	/**
	 * @return the normAdptDt
	 */
	public String getNormAdptDt() {
		return normAdptDt;
	}

	/**
	 * @param normAdptDt the normAdptDt to set
	 */
	public void setNormAdptDt(String normAdptDt) {
		this.normAdptDt = normAdptDt;
	}

	/**
	 * @return the normCode
	 */
	public String getNormCode() {
		return normCode;
	}

	/**
	 * @param normCode the normCode to set
	 */
	public void setNormCode(String normCode) {
		this.normCode = normCode;
	}

	/**
	 * @return the normPayAmt
	 */
	public String getNormPayAmt() {
		return normPayAmt;
	}

	/**
	 * @param normPayAmt the normPayAmt to set
	 */
	public void setNormPayAmt(String normPayAmt) {
		this.normPayAmt = normPayAmt;
	}

	/**
	 * @return the ordrDt
	 */
	public String getOrdrDt() {
		return ordrDt;
	}

	/**
	 * @param ordrDt the ordrDt to set
	 */
	public void setOrdrDt(String ordrDt) {
		this.ordrDt = ordrDt;
	}

	/**
	 * @return the payScale
	 */
	public String getPayScale() {
		return payScale;
	}

	/**
	 * @param payScale the payScale to set
	 */
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}

	/**
	 * @return the smallFamilyList
	 */
	public ArrayList getSmallFamilyList() {
		return smallFamilyList;
	}

	/**
	 * @param smallFamilyList the smallFamilyList to set
	 */
	public void setSmallFamilyList(ArrayList smallFamilyList) {
		this.smallFamilyList = smallFamilyList;
	}

	public SmallFamily() { }

	/**
	 * @return the payCode
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * @param payCode the payCode to set
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmpRank() {
		return empRank;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	
}
