package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class EmployeePreviousIncome extends BeanBase {
	private String myPage ;
	private String hiddencode;
	private String show;
	private String totalRecords;
	private String listLength="false";
	private String hdeleteCode;
	ArrayList iteratorlist;
	private String prevIncCodeItr;
	private String employeeNameItr;
	private String fromYearItr;
	private String toYearItr;
	private String prevIncCode;
	private String empID="";
	private String empName="";
	private String empToken="";
	private String fromYear="";
	private String toYear="";
	private String empCenter="";
	private String empRank="";
	
	private String basicAmount="0";
	private String hraAmount="0";
	private String daAmount="0";
	private String caAmount="0";
	private String otherAmount="0";
	private String pfAmount="0";
	private String ptAmount="0";
	private String taxAmount="0";
	private String netAmount="0";
	private String panNo="";
	
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getPrevIncCode() {
		return prevIncCode;
	}
	public void setPrevIncCode(String prevIncCode) {
		this.prevIncCode = prevIncCode;
	}
	public String getEmployeeNameItr() {
		return employeeNameItr;
	}
	public void setEmployeeNameItr(String employeeNameItr) {
		this.employeeNameItr = employeeNameItr;
	}
	public String getFromYearItr() {
		return fromYearItr;
	}
	public void setFromYearItr(String fromYearItr) {
		this.fromYearItr = fromYearItr;
	}
	public String getToYearItr() {
		return toYearItr;
	}
	public void setToYearItr(String toYearItr) {
		this.toYearItr = toYearItr;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getEmpCenter() {
		return empCenter;
	}
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}
	public String getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(String basicAmount) {
		this.basicAmount = basicAmount;
	}
	public String getHraAmount() {
		return hraAmount;
	}
	public void setHraAmount(String hraAmount) {
		this.hraAmount = hraAmount;
	}
	public String getDaAmount() {
		return daAmount;
	}
	public void setDaAmount(String daAmount) {
		this.daAmount = daAmount;
	}
	public String getCaAmount() {
		return caAmount;
	}
	public void setCaAmount(String caAmount) {
		this.caAmount = caAmount;
	}
	public String getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}
	public String getPfAmount() {
		return pfAmount;
	}
	public void setPfAmount(String pfAmount) {
		this.pfAmount = pfAmount;
	}
	public String getPtAmount() {
		return ptAmount;
	}
	public void setPtAmount(String ptAmount) {
		this.ptAmount = ptAmount;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	public String getPrevIncCodeItr() {
		return prevIncCodeItr;
	}
	public void setPrevIncCodeItr(String prevIncCodeItr) {
		this.prevIncCodeItr = prevIncCodeItr;
	}
}
