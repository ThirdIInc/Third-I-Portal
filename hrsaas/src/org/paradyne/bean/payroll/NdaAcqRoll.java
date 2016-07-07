package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.paradyne.lib.BeanBase;

public class NdaAcqRoll extends BeanBase implements Serializable {		
	String month1 ="";
	String month2 ="";
	String year ="";
	String empType = "";
	String centerNo = "";
	String typeCode="";
	String payBilGrp="";
	String rptTyp="";
	
	public NdaAcqRoll(){
		
	}
	
	public NdaAcqRoll(String month1, String month2, String year, String empType, String centerNo, String typeCode, String payBilGrp, String rptTyp) {
		
		this.month1 = month1;
		this.month2 = month2;
		this.year = year;
		this.empType = empType;
		this.centerNo = centerNo;
		this.typeCode = typeCode;
		this.payBilGrp = payBilGrp;
		this.rptTyp = rptTyp;
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

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getPayBilGrp() {
		return payBilGrp;
	}

	public void setPayBilGrp(String payBilGrp) {
		this.payBilGrp = payBilGrp;
	}

	public String getRptTyp() {
		return rptTyp;
	}

	public void setRptTyp(String rptTyp) {
		this.rptTyp = rptTyp;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

}
