package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class IndustrialSalaryAudit  extends BeanBase implements Serializable {		
	
	private String typeCode="";
	private String typeName="";
	private String month="";
	private String year="";
	private String payBillNo="";
	private String creditCode="";
	private String creditName="";
	private String creditAmount="";
	private String debitCode="";
	private String debitName="";
	private String debitAmount="";
	private String empId="";
	private String empName="";
	private String empToken="";
	private ArrayList<IndustrialSalaryAudit> creditHeader=null;
	private ArrayList<IndustrialSalaryAudit> debitHeader=null;
	private ArrayList<ArrayList> empInfo=null;
	private Object[][]  credit=null;
	private String creditLength="";
	
	
	
	
	public ArrayList<ArrayList> getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(ArrayList<ArrayList> empInfo) {
		this.empInfo = empInfo;
	}
	public String getCreditLength() {
		return creditLength;
	}
	public void setCreditLength(String creditLength) {
		this.creditLength = creditLength;
	}
	public Object[][] getCredit() {
		return credit;
	}
	public void setCredit(Object[][] credit) {
		this.credit = credit;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public ArrayList<IndustrialSalaryAudit> getDebitHeader() {
		return debitHeader;
	}
	public void setDebitHeader(ArrayList<IndustrialSalaryAudit> debitHeader) {
		this.debitHeader = debitHeader;
	}
	public ArrayList<IndustrialSalaryAudit> getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList<IndustrialSalaryAudit> creditHeader) {
		this.creditHeader = creditHeader;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	

}
