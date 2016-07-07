package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class EmpInvstmnt extends BeanBase implements Serializable {		
String frmYear="";
String toYear="";
String typeCode="";
String empType="";
String centerNo="";
String payBill="";
String rptTyp="";
HashMap hmPayBill=new HashMap();
public EmpInvstmnt(){
	
}
public EmpInvstmnt(String frmYear, String toYear, String typeCode, String empType, String centerNo, String payBill, String rptTyp, HashMap hmPayBill) {
	super();
	this.frmYear = frmYear;
	this.toYear = toYear;
	this.typeCode = typeCode;
	this.empType = empType;
	this.centerNo = centerNo;
	this.payBill = payBill;
	this.rptTyp = rptTyp;
	this.hmPayBill = hmPayBill;
}
public String getCenterNo() {
	return centerNo;
}
public void setCenterNo(String centerNo) {
	this.centerNo = centerNo;
}
public String getEmpType() {
	return empType;
}
public void setEmpType(String empType) {
	this.empType = empType;
}
public String getFrmYear() {
	return frmYear;
}
public void setFrmYear(String frmYear) {
	this.frmYear = frmYear;
}
public HashMap getHmPayBill() {
	return hmPayBill;
}
public void setHmPayBill(HashMap hmPayBill) {
	this.hmPayBill = hmPayBill;
}
public String getPayBill() {
	return payBill;
}
public void setPayBill(String payBill) {
	this.payBill = payBill;
}
public String getRptTyp() {
	return rptTyp;
}
public void setRptTyp(String rptTyp) {
	this.rptTyp = rptTyp;
}
public String getToYear() {
	return toYear;
}
public void setToYear(String toYear) {
	this.toYear = toYear;
}
public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}






}
