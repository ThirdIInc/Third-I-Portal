package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


import org.paradyne.lib.BeanBase;

public class NdaHours extends BeanBase implements Serializable {		
	String frmDate ="";
	String toDate ="";
	String empType = "";
	String centerNo = "";
	String typeCode="";
	String rptTyp="";
	String payBill="";
	HashMap payBilGrp=null;
	public NdaHours(){
		
	}
	public NdaHours(String frmDate, String toDate, String empType, String centerNo, String typeCode, String rptTyp, String payBill, HashMap payBilGrp) {		
		this.frmDate = frmDate;
		this.toDate = toDate;
		this.empType = empType;
		this.centerNo = centerNo;
		this.typeCode = typeCode;
		this.rptTyp = rptTyp;
		this.payBill = payBill;
		this.payBilGrp = payBilGrp;
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
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public HashMap getPayBilGrp() {
		return payBilGrp;
	}
	public void setPayBilGrp(HashMap payBilGrp) {
		this.payBilGrp = payBilGrp;
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
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
		
	
}
