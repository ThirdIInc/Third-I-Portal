package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DaArrearAudit extends BeanBase implements Serializable{
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
	private String typeName="";
	private String typeCode="";
	private String audit="";
	private String chk="";
	private ArrayList<DaArrear> daList=null;
	
	public DaArrearAudit(){
		
	}
	
	public DaArrearAudit(String empId, String empName, String daCode, String sysDate, String daDate, String paidDa, String dueDa, String diff, String effectiveDate, String daRate, String payBillNo, String typeName, String typeCode, String audit, String chk, ArrayList<DaArrear> daList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.daCode = daCode;
		this.sysDate = sysDate;
		this.daDate = daDate;
		this.paidDa = paidDa;
		this.dueDa = dueDa;
		this.diff = diff;
		this.effectiveDate = effectiveDate;
		this.daRate = daRate;
		this.payBillNo = payBillNo;
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.audit = audit;
		this.chk = chk;
		this.daList = daList;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getDaCode() {
		return daCode;
	}

	public void setDaCode(String daCode) {
		this.daCode = daCode;
	}

	public String getDaDate() {
		return daDate;
	}

	public void setDaDate(String daDate) {
		this.daDate = daDate;
	}

	public ArrayList<DaArrear> getDaList() {
		return daList;
	}

	public void setDaList(ArrayList<DaArrear> daList) {
		this.daList = daList;
	}

	public String getDaRate() {
		return daRate;
	}

	public void setDaRate(String daRate) {
		this.daRate = daRate;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public String getDueDa() {
		return dueDa;
	}

	public void setDueDa(String dueDa) {
		this.dueDa = dueDa;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public String getPaidDa() {
		return paidDa;
	}

	public void setPaidDa(String paidDa) {
		this.paidDa = paidDa;
	}

	public String getPayBillNo() {
		return payBillNo;
	}

	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
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
		
	

}
