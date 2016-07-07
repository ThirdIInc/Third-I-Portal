package org.paradyne.bean.payroll;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * Author:Pradeep Kumar Sahoo
 * Date:02.08.2007
 */

public class SalaryLedger extends BeanBase {
	String month;
	String year;
	String empType;
	String payGrp;
	String status;
	String payBillNo;
	String paraId;
	String typeCode;
	boolean flag;
	boolean chkStatus;
	boolean chkEmp;
	ArrayList salList;
	public SalaryLedger() {  }
	public SalaryLedger(String paraId,String payBillNo,String month, String year, String empType, String payGrp, String status) {
		super();
		this.month = month;
		this.year = year;
		this.empType = empType;
		this.payGrp = payGrp;
		this.status = status;
		this.payBillNo=payBillNo;
		this.paraId=paraId;
	}
	
	
	public String getEmpType() {
		return empType;
	}
	
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getPayGrp() {
		return payGrp;
	}
	
	public void setPayGrp(String payGrp) {
		this.payGrp = payGrp;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}


	public ArrayList getSalList() {
		return salList;
	}


	public void setSalList(ArrayList salList) {
		this.salList = salList;
	}
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isChkStatus() {
		return chkStatus;
	}
	public void setChkStatus(boolean chkStatus) {
		this.chkStatus = chkStatus;
	}
	public boolean isChkEmp() {
		return chkEmp;
	}
	public void setChkEmp(boolean chkEmp) {
		this.chkEmp = chkEmp;
	}
	
	

}
