package org.paradyne.bean.payroll.salary;
import org.paradyne.lib.BeanBase;
import java.util.ArrayList; 

/**
 * @author hemant
 *
 */
public class PAProcess extends BeanBase{
 
	//FILTER MEMBERS
	
	String empId="";
	String empName="";
	String empToken="";
	String branchId="";
	String branchName="";	
	String divisionId="";
	String divisionName="";
	String componentId="";
	String componentName="";
	String ratingFlag="";
	String period="";
	String deptId ="";
	String deptName ="";
	String EmpTypeId ="";
	String desgnId ="";
	String desgnName ="";
	String payBillId ="";
	String EmpTypeName ="";
	String payBillName="";
	String salFrmMonth ="";
	String salFrmYear="";
	String disbType ="";
	String hidDisbType = "";
	String hiddenChk ="";
	String paFromDate ="";
	String paToDate ="";
	
	//PA SPECIFIC MEMBERS
	String rating="";
	String score="";
	String entitledAmt="";
	String itEntitledAmtActual="";
	String entitledPercent="";
	String finalAmt="";
	String compAbbr="";
	String compAbbrPer="";
	String compAbbrAmtRs="";
	
	
	//OTHER MEMBERS
	String paProcessId ="";
	String crId="";
	String crName="";
	String payMode="";
	String frmMonth="";
	String toMonth="";
	String frmYear="";
	String toYear="";
	String finaliseFlag="";	
	String processDate="";
	String salaryFlag ="";
	String apprFlag ="";
	
	// iterator variable
	String itEmpId ="";
	String itEmpName ="";
	String itRating="";
	String itRatingScore="";
	String itEntitledAmt="";	
	String itEntitledAmtScore="";
	String itPIAmt=""; 
	String itRemark ="";
	String lockFlag ="false";
	String disbursFlag ="false";
	String lockActivateFlag ="true";
	String radioFlag ="";
	String processStatus="";
	private String itTotalAmt="0";
	private String itTaxAmt="0"; 
	//for Paging 
	private String hdPage ="";
	private String fromTotRec ="";
	private String toTotRec ="" ;
	
	//for Monthly Details of Allwoence 
    String mMonth ="";
    String mMonthDays ="";
    String mYear ="";
    String mAmount ="";
    String flagButton ="";
	
	 
	ArrayList paList;

 
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getRatingFlag() {
		return ratingFlag;
	}

	public void setRatingFlag(String ratingFlag) {
		this.ratingFlag = ratingFlag;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getEntitledAmt() {
		return entitledAmt;
	}

	public void setEntitledAmt(String entitledAmt) {
		this.entitledAmt = entitledAmt;
	}

	public String getEntitledPercent() {
		return entitledPercent;
	}

	public void setEntitledPercent(String entitledPercent) {
		this.entitledPercent = entitledPercent;
	}

	public String getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(String finalAmt) {
		this.finalAmt = finalAmt;
	}

	public String getCrId() {
		return crId;
	}

	public void setCrId(String crId) {
		this.crId = crId;
	}

	public String getCrName() {
		return crName;
	}

	public void setCrName(String crName) {
		this.crName = crName;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getFrmMonth() {
		return frmMonth;
	}

	public void setFrmMonth(String frmMonth) {
		this.frmMonth = frmMonth;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getFrmYear() {
		return frmYear;
	}

	public void setFrmYear(String frmYear) {
		this.frmYear = frmYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

 

	public String getFinaliseFlag() {
		return finaliseFlag;
	}

	public void setFinaliseFlag(String finaliseFlag) {
		this.finaliseFlag = finaliseFlag;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public ArrayList getPaList() {
		return paList;
	}

	public void setPaList(ArrayList paList) {
		this.paList = paList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

 

	public String getEmpTypeId() {
		return EmpTypeId;
	}

	public void setEmpTypeId(String empTypeId) {
		EmpTypeId = empTypeId;
	}

	public String getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}

	public String getItEmpId() {
		return itEmpId;
	}

	public void setItEmpId(String itEmpId) {
		this.itEmpId = itEmpId;
	}

	public String getItEmpName() {
		return itEmpName;
	}

	public void setItEmpName(String itEmpName) {
		this.itEmpName = itEmpName;
	}

	public String getItRating() {
		return itRating;
	}

	public void setItRating(String itRating) {
		this.itRating = itRating;
	}

	public String getItRatingScore() {
		return itRatingScore;
	}

	public void setItRatingScore(String itRatingScore) {
		this.itRatingScore = itRatingScore;
	}

	public String getItEntitledAmt() {
		return itEntitledAmt;
	}

	public void setItEntitledAmt(String itEntitledAmt) {
		this.itEntitledAmt = itEntitledAmt;
	}

	public String getItEntitledAmtScore() {
		return itEntitledAmtScore;
	}

	public void setItEntitledAmtScore(String itEntitledAmtScore) {
		this.itEntitledAmtScore = itEntitledAmtScore;
	}

	public String getItPIAmt() {
		return itPIAmt;
	}

	public void setItPIAmt(String itPIAmt) {
		this.itPIAmt = itPIAmt;
	}

 

	public String getEmpTypeName() {
		return EmpTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		EmpTypeName = empTypeName;
	}

	public String getPayBillName() {
		return payBillName;
	}

	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	public String getSalFrmMonth() {
		return salFrmMonth;
	}

	public void setSalFrmMonth(String salFrmMonth) {
		this.salFrmMonth = salFrmMonth;
	}

	public String getSalFrmYear() {
		return salFrmYear;
	}

	public void setSalFrmYear(String salFrmYear) {
		this.salFrmYear = salFrmYear;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDisbType() {
		return disbType;
	}

	public void setDisbType(String disbType) {
		this.disbType = disbType;
	}

	public String getDesgnId() {
		return desgnId;
	}

	public void setDesgnId(String desgnId) {
		this.desgnId = desgnId;
	}

	public String getDesgnName() {
		return desgnName;
	}

	public void setDesgnName(String desgnName) {
		this.desgnName = desgnName;
	}

	public String getHidDisbType() {
		return hidDisbType;
	}

	public void setHidDisbType(String hidDisbType) {
		this.hidDisbType = hidDisbType;
	}

	public String getItRemark() {
		return itRemark;
	}

	public void setItRemark(String itRemark) {
		this.itRemark = itRemark;
	}

	public String getSalaryFlag() {
		return salaryFlag;
	}

	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}

	public String getPaProcessId() {
		return paProcessId;
	}

	public void setPaProcessId(String paProcessId) {
		this.paProcessId = paProcessId;
	}

	public String getCompAbbr() {
		return compAbbr;
	}

	public void setCompAbbr(String compAbbr) {
		this.compAbbr = compAbbr;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getLockActivateFlag() {
		return lockActivateFlag;
	}

	public void setLockActivateFlag(String lockActivateFlag) {
		this.lockActivateFlag = lockActivateFlag;
	}

	public String getApprFlag() {
		return apprFlag;
	}

	public void setApprFlag(String apprFlag) {
		this.apprFlag = apprFlag;
	}

	public String getRadioFlag() {
		return radioFlag;
	}

	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}

	public String getHiddenChk() {
		return hiddenChk;
	}

	public void setHiddenChk(String hiddenChk) {
		this.hiddenChk = hiddenChk;
	}

	public String getHdPage() {
		return hdPage;
	}

	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	public String getFromTotRec() {
		return fromTotRec;
	}

	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}

	public String getToTotRec() {
		return toTotRec;
	}

	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}

	public String getDisbursFlag() {
		return disbursFlag;
	}

	public void setDisbursFlag(String disbursFlag) {
		this.disbursFlag = disbursFlag;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getItEntitledAmtActual() {
		return itEntitledAmtActual;
	}

	public void setItEntitledAmtActual(String itEntitledAmtActual) {
		this.itEntitledAmtActual = itEntitledAmtActual;
	}

	public String getPaFromDate() {
		return paFromDate;
	}

	public void setPaFromDate(String paFromDate) {
		this.paFromDate = paFromDate;
	}

	public String getPaToDate() {
		return paToDate;
	}

	public void setPaToDate(String paToDate) {
		this.paToDate = paToDate;
	}
 
	public String getMMonth() {
		return mMonth;
	}

	public void setMMonth(String month) {
		mMonth = month;
	}

	public String getMMonthDays() {
		return mMonthDays;
	}

	public void setMMonthDays(String monthDays) {
		mMonthDays = monthDays;
	}

	public String getMYear() {
		return mYear;
	}

	public void setMYear(String year) {
		mYear = year;
	}

	public String getMAmount() {
		return mAmount;
	}

	public void setMAmount(String amount) {
		mAmount = amount;
	}

	public String getFlagButton() {
		return flagButton;
	}

	public void setFlagButton(String flagButton) {
		this.flagButton = flagButton;
	}

	public String getCompAbbrPer() {
		return compAbbrPer;
	}

	public void setCompAbbrPer(String compAbbrPer) {
		this.compAbbrPer = compAbbrPer;
	}

	public String getCompAbbrAmtRs() {
		return compAbbrAmtRs;
	}

	public void setCompAbbrAmtRs(String compAbbrAmtRs) {
		this.compAbbrAmtRs = compAbbrAmtRs;
	}

	public String getItTotalAmt() {
		return itTotalAmt;
	}

	public void setItTotalAmt(String itTotalAmt) {
		this.itTotalAmt = itTotalAmt;
	}

	public String getItTaxAmt() {
		return itTaxAmt;
	}

	public void setItTaxAmt(String itTaxAmt) {
		this.itTaxAmt = itTaxAmt;
	}
	
	
}
