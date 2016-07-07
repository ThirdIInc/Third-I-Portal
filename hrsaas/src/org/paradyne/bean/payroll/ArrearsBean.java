/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author MuzaffarS
 *
 */
public class ArrearsBean extends BeanBase{
	String frmmonth;
	String tomonth;
	String toDt;
	String srtEmp;
	String empType;
	String pbgrp;
	String pbId;
	String empName;
	String basic;
	String cca;
	String month;
	ArrayList att;
	String emp_id;
	String totalCredit;
	private String creditName1="";
	private String EmpNm="";
	private String mon="";
	private String creditCode="";
	private String creditName="";
	private String creditAmount="";
	private String year="";
	
	private String debitCode="";
	private String debitName="";
	private ArrayList debitHeader;
	private ArrayList<ArrayList> empInfo=null;
	private Object[][]  credit=null;
	private String creditLength="";
	private ArrayList creditHeader=null;
	private ArrayList creditHeader1=null;
	private ArrayList creditList=null;
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public String getPbgrp() {
		return pbgrp;
	}
	public void setPbgrp(String pbgrp) {
		this.pbgrp = pbgrp;
	}
	public String getPbId() {
		return pbId;
	}
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}
	public String getSrtEmp() {
		return srtEmp;
	}
	public void setSrtEmp(String srtEmp) {
		this.srtEmp = srtEmp;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getCca() {
		return cca;
	}
	public void setCca(String cca) {
		this.cca = cca;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public ArrayList getAtt() {
		return att;
	}
	public void setAtt(ArrayList att) {
		this.att = att;
	}
	/**
	 * @return the credit
	 */
	public Object[][] getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Object[][] credit) {
		this.credit = credit;
	}
	/**
	 * @return the creditAmount
	 */
	public String getCreditAmount() {
		return creditAmount;
	}
	/**
	 * @param creditAmount the creditAmount to set
	 */
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the creditHeader
	 */
	public ArrayList getCreditHeader() {
		return creditHeader;
	}
	/**
	 * @param creditHeader the creditHeader to set
	 */
	public void setCreditHeader(ArrayList creditHeader) {
		this.creditHeader = creditHeader;
	}
	/**
	 * @return the creditLength
	 */
	public String getCreditLength() {
		return creditLength;
	}
	/**
	 * @param creditLength the creditLength to set
	 */
	public void setCreditLength(String creditLength) {
		this.creditLength = creditLength;
	}
	/**
	 * @return the creditName
	 */
	public String getCreditName() {
		return creditName;
	}
	/**
	 * @param creditName the creditName to set
	 */
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	/**
	 * @return the empInfo
	 */
	public ArrayList<ArrayList> getEmpInfo() {
		return empInfo;
	}
	/**
	 * @param empInfo the empInfo to set
	 */
	public void setEmpInfo(ArrayList<ArrayList> empInfo) {
		this.empInfo = empInfo;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the creditHeader1
	 */
	public ArrayList getCreditHeader1() {
		return creditHeader1;
	}
	/**
	 * @param creditHeader1 the creditHeader1 to set
	 */
	public void setCreditHeader1(ArrayList creditHeader1) {
		this.creditHeader1 = creditHeader1;
	}
	/**
	 * @return the creditName1
	 */
	public String getCreditName1() {
		return creditName1;
	}
	/**
	 * @param creditName1 the creditName1 to set
	 */
	public void setCreditName1(String creditName1) {
		this.creditName1 = creditName1;
	}
	/**
	 * @return the empNm
	 */
	public String getEmpNm() {
		return EmpNm;
	}
	/**
	 * @param empNm the empNm to set
	 */
	public void setEmpNm(String empNm) {
		EmpNm = empNm;
	}
	/**
	 * @return the mon
	 */
	public String getMon() {
		return mon;
	}
	/**
	 * @param mon the mon to set
	 */
	public void setMon(String mon) {
		this.mon = mon;
	}
	/**
	 * @return the emp_id
	 */
	public String getEmp_id() {
		return emp_id;
	}
	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	/**
	 * @return the totalCredit
	 */
	public String getTotalCredit() {
		return totalCredit;
	}
	/**
	 * @param totalCredit the totalCredit to set
	 */
	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}
	/**
	 * @return the creditList
	 */
	public ArrayList getCreditList() {
		return creditList;
	}
	/**
	 * @param creditList the creditList to set
	 */
	public void setCreditList(ArrayList creditList) {
		this.creditList = creditList;
	}
	public String getFrmmonth() {
		return frmmonth;
	}
	public void setFrmmonth(String frmmonth) {
		this.frmmonth = frmmonth;
	}
	public String getTomonth() {
		return tomonth;
	}
	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}
	/**
	 * @return the debitCode
	 */
	public String getDebitCode() {
		return debitCode;
	}
	/**
	 * @param debitCode the debitCode to set
	 */
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	/**
	 * @return the debitName
	 */
	public String getDebitName() {
		return debitName;
	}
	/**
	 * @param debitName the debitName to set
	 */
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	/**
	 * @return the debitHeader
	 */
	public ArrayList getDebitHeader() {
		return debitHeader;
	}
	/**
	 * @param debitHeader the debitHeader to set
	 */
	public void setDebitHeader(ArrayList debitHeader) {
		this.debitHeader = debitHeader;
	}

}
