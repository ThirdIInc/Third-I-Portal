package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DaAcquintanceRoll  extends BeanBase implements Serializable {		
	private String empId="";
	private String empName="";
	private String daCode="";
	private String sysDate="";
	private String daDate="";
	private String paidDa="";
	private String dueDa="";
	private String diff="";
	private String effectiveDate="";
	private String daRate="";
	private String payBillNo="";
	private String payBillName="";
	private String typeName="";
	private String typeCode="";
	private ArrayList<DaAcquintanceRoll> daList=null;
	private String rptType="";
	
	
	
	 
	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}

	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	public String getRptType() {
		return rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}

	public ArrayList<DaAcquintanceRoll> getDaList() {
		return daList;
	}

	public void setDaList(ArrayList<DaAcquintanceRoll> daList) {
		this.daList = daList;
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

	public String getDaDate() {
		return daDate;
	}

	public void setDaDate(String daDate) {
		this.daDate = daDate;
	}

	public String getPayBillNo() {
		return payBillNo;
	}

	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
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

	public String getDaCode() {
		return daCode;
	}

	public void setDaCode(String daCode) {
		this.daCode = daCode;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getDueDa() {
		return dueDa;
	}

	public void setDueDa(String dueDa) {
		this.dueDa = dueDa;
	}

	public String getPaidDa() {
		return paidDa;
	}

	public void setPaidDa(String paidDa) {
		this.paidDa = paidDa;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public String getDaRate() {
		return daRate;
	}

	public void setDaRate(String daRate) {
		this.daRate = daRate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	

}
