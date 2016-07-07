package org.paradyne.bean.extraWorkBenefits;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ExtraWorkBenifitCalculation extends BeanBase {

	
	private String creditedTo="";
	private String  divisionCode="";
	private String  divisionName="";
	private String month="";
	private String year="";
	private String salarymonth="";
	private String salaryyear="";
	private String salaryCheck="";
	private String empToken="";
	private String empName="";
	private String benifitFor="";
	private String extraWorkDate="";
	private String benifitType="";
	ArrayList list;
	ArrayList creditList;
	private String creditHead="";
	private String creditHeadCode="";
	private String employeeId="";
	private String creditAmount="";
	
	private String hiddenStatus="";
	private String processCode="";
	
	private String checkProcess="";
	private String lockFlag="";
	
	private String totAmount="";
	
	private String  extraworkFromTime ="";
	private String  extraworkToTime ="";
 
	public String getExtraworkFromTime() {
		return extraworkFromTime;
	}
	public void setExtraworkFromTime(String extraworkFromTime) {
		this.extraworkFromTime = extraworkFromTime;
	}
	public String getExtraworkToTime() {
		return extraworkToTime;
	}
	public void setExtraworkToTime(String extraworkToTime) {
		this.extraworkToTime = extraworkToTime;
	}
	public String getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(String totAmount) {
		this.totAmount = totAmount;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public ArrayList getCreditList() {
		return creditList;
	}
	public void setCreditList(ArrayList creditList) {
		this.creditList = creditList;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
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
	public String getBenifitFor() {
		return benifitFor;
	}
	public void setBenifitFor(String benifitFor) {
		this.benifitFor = benifitFor;
	}
	public String getExtraWorkDate() {
		return extraWorkDate;
	}
	public void setExtraWorkDate(String extraWorkDate) {
		this.extraWorkDate = extraWorkDate;
	}
	public String getBenifitType() {
		return benifitType;
	}
	public void setBenifitType(String benifitType) {
		this.benifitType = benifitType;
	}
	public String getSalaryCheck() {
		return salaryCheck;
	}
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSalarymonth() {
		return salarymonth;
	}
	public void setSalarymonth(String salarymonth) {
		this.salarymonth = salarymonth;
	}
	public String getSalaryyear() {
		return salaryyear;
	}
	public void setSalaryyear(String salaryyear) {
		this.salaryyear = salaryyear;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	public String getCreditHeadCode() {
		return creditHeadCode;
	}
	public void setCreditHeadCode(String creditHeadCode) {
		this.creditHeadCode = creditHeadCode;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getCheckProcess() {
		return checkProcess;
	}
	public void setCheckProcess(String checkProcess) {
		this.checkProcess = checkProcess;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getCreditedTo() {
		return creditedTo;
	}
	public void setCreditedTo(String creditedTo) {
		this.creditedTo = creditedTo;
	}
	 
}
