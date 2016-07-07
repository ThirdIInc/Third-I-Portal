/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class Form24 extends BeanBase {
	
	
	private String fromYear="";
	private String toYear="";
	private String quarter="";
	private String divName="";
	private String divCode="";
	private String signAuthName="";
	private String signAuthEmpId="";
	private String signAuthEmpDesg="";
	private String signAuthEmpFather="";
	private String deductorStat="";
	private String signAuthEmpAdd="";
	private String signAuthEmpCity="";
	private String signAuthEmpState="";
	private String signAuthEmpPin="";
	private String empToken="";
	
	private HashMap incomeDataMap = null;
	
	public String getSignAuthEmpAdd() {
		return signAuthEmpAdd;
	}
	public void setSignAuthEmpAdd(String signAuthEmpAdd) {
		this.signAuthEmpAdd = signAuthEmpAdd;
	}
	public String getSignAuthEmpCity() {
		return signAuthEmpCity;
	}
	public void setSignAuthEmpCity(String signAuthEmpCity) {
		this.signAuthEmpCity = signAuthEmpCity;
	}
	public String getSignAuthEmpState() {
		return signAuthEmpState;
	}
	public void setSignAuthEmpState(String signAuthEmpState) {
		this.signAuthEmpState = signAuthEmpState;
	}
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getSignAuthEmpId() {
		return signAuthEmpId;
	}
	public void setSignAuthEmpId(String signAuthEmpId) {
		this.signAuthEmpId = signAuthEmpId;
	}
	public String getSignAuthEmpDesg() {
		return signAuthEmpDesg;
	}
	public void setSignAuthEmpDesg(String signAuthEmpDesg) {
		this.signAuthEmpDesg = signAuthEmpDesg;
	}
	public String getSignAuthEmpFather() {
		return signAuthEmpFather;
	}
	public void setSignAuthEmpFather(String signAuthEmpFather) {
		this.signAuthEmpFather = signAuthEmpFather;
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
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDeductorStat() {
		return deductorStat;
	}
	public void setDeductorStat(String deductorStat) {
		this.deductorStat = deductorStat;
	}
	public String getSignAuthEmpPin() {
		return signAuthEmpPin;
	}
	public void setSignAuthEmpPin(String signAuthEmpPin) {
		this.signAuthEmpPin = signAuthEmpPin;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public HashMap getIncomeDataMap() {
		return incomeDataMap;
	}
	public void setIncomeDataMap(HashMap incomeDataMap) {
		this.incomeDataMap = incomeDataMap;
	}

}
