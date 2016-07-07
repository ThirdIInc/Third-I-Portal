package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DaArrear  extends BeanBase implements Serializable {		
	private String empId="";
	private String empToken="";
	private String empName="";
	private String daMonth="";
	private String daMonthName="";
	private String daYear="";
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
	private String payBillName="";
	private String daFlag="";
	private String saveFlag="false";
	private String lockFlag="false";
	
	
	private ArrayList<DaArrear> daList=null;
	
	
	
	/**
	 * @return the lockFlag
	 */
	public String getLockFlag() {
		return lockFlag;
	}

	/**
	 * @param lockFlag the lockFlag to set
	 */
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
	 * @return the saveFlag
	 */
	public String getSaveFlag() {
		return saveFlag;
	}

	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	/**
	 * @return the daMonthName
	 */
	public String getDaMonthName() {
		return daMonthName;
	}

	/**
	 * @param daMonthName the daMonthName to set
	 */
	public void setDaMonthName(String daMonthName) {
		this.daMonthName = daMonthName;
	}

	/**
	 * @return the daFlag
	 */
	public String getDaFlag() {
		return daFlag;
	}

	/**
	 * @param daFlag the daFlag to set
	 */
	public void setDaFlag(String daFlag) {
		this.daFlag = daFlag;
	}

	/**
	 * @return the daMonth
	 */
	public String getDaMonth() {
		return daMonth;
	}

	/**
	 * @param daMonth the daMonth to set
	 */
	public void setDaMonth(String daMonth) {
		this.daMonth = daMonth;
	}

	/**
	 * @return the daYear
	 */
	public String getDaYear() {
		return daYear;
	}

	/**
	 * @param daYear the daYear to set
	 */
	public void setDaYear(String daYear) {
		this.daYear = daYear;
	}

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

	public ArrayList<DaArrear> getDaList() {
		return daList;
	}

	public void setDaList(ArrayList<DaArrear> daList) {
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

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	
	

}
