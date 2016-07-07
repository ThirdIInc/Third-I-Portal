package org.paradyne.bean.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class MealVoucherBean extends BeanBase {
	
	
	private String empCode ="";
	private String empToken ="";
	private String empName =""; 
	private String branchName =""; 
	private String designationName ="";
	private String applicationDate ="";
	private String mealVoucherCoupenAmt =""; 
	private String mealVoucherAmount ="0";
	private String mealVoucherSplAllowanceAmount ="0";
	private String mealVoucherDeclaredAmt ="";
	private String checkhiddenCode = "";
	private String checkValidation ="";
	private String changePeriodicity="";
	private String saveFlag ="false";
	private String previousDate="";
	private String fromYear ="";
	private String toYear ="";
	
	
	
	/**
	 * Map for Doc Type.
	 */
	
	LinkedList  amtValue = null;
	
	private String amountValue = "";
	private String amtVal = "";
	private String fromPeriod = "";
	private String toPeriod = "";
	
	public String getAmtVal() {
		return amtVal;
	}
	public void setAmtVal(String amtVal) {
		this.amtVal = amtVal;
	}
	public String getAmountValue() {
		return amountValue;
	}
	public void setAmountValue(String amountValue) {
		this.amountValue = amountValue;
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
	public String getCheckValidation() {
		return checkValidation;
	}
	public void setCheckValidation(String checkValidation) {
		this.checkValidation = checkValidation;
	}
	public String getCheckhiddenCode() {
		return checkhiddenCode;
	}
	public void setCheckhiddenCode(String checkhiddenCode) {
		this.checkhiddenCode = checkhiddenCode;
	}
	public String getMealVoucherDeclaredAmt() {
		return mealVoucherDeclaredAmt;
	}
	public void setMealVoucherDeclaredAmt(String mealVoucherDeclaredAmt) {
		this.mealVoucherDeclaredAmt = mealVoucherDeclaredAmt;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	 
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	 
	public String getMealVoucherCoupenAmt() {
		return mealVoucherCoupenAmt;
	}
	public void setMealVoucherCoupenAmt(String mealVoucherCoupenAmt) {
		this.mealVoucherCoupenAmt = mealVoucherCoupenAmt;
	}
	public String getMealVoucherAmount() {
		return mealVoucherAmount;
	}
	public void setMealVoucherAmount(String mealVoucherAmount) {
		this.mealVoucherAmount = mealVoucherAmount;
	}
	public String getMealVoucherSplAllowanceAmount() {
		return mealVoucherSplAllowanceAmount;
	}
	public void setMealVoucherSplAllowanceAmount(
			String mealVoucherSplAllowanceAmount) {
		this.mealVoucherSplAllowanceAmount = mealVoucherSplAllowanceAmount;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getChangePeriodicity() {
		return changePeriodicity;
	}
	public void setChangePeriodicity(String changePeriodicity) {
		this.changePeriodicity = changePeriodicity;
	}
	public String getPreviousDate() {
		return previousDate;
	}
	public void setPreviousDate(String previousDate) {
		this.previousDate = previousDate;
	}
	public LinkedList getAmtValue() {
		return amtValue;
	}
	public void setAmtValue(LinkedList amtValue) {
		this.amtValue = amtValue;
	}
	public String getFromPeriod() {
		return fromPeriod;
	}
	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}
	public String getToPeriod() {
		return toPeriod;
	}
	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}

}
