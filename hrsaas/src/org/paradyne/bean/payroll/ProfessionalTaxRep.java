package org.paradyne.bean.payroll;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep Kumar Sahoo
 * Date:11-04-2008
 */

public class ProfessionalTaxRep extends BeanBase {
	
	private String reportType="";
	private String month="";
	private String year="";
	private String divId="";
	private String divName="";
	private String brnId="";
	private String brnName="";
	private String onHold="";
	private String checkFlag="N";
	
	/*
	 * modified on 25/11/2009
	 */
	private String stateCheck;
	private String branchCheck;
	private String slabCheck;
	private String incomeCheck;
	private String dojCheck;
	private String toMonth="";
	private String toYear="";
	private String stateId;
	private String stateName;
	
	//ADDED BY REEBA
	private String reportOption;
	
	/*
	 * end modification
	 */
	
//  added on 06/08/2010	
	private String divAdd;
	
	
	public String getSlabCheck() {
		return slabCheck;
	}
	public void setSlabCheck(String slabCheck) {
		this.slabCheck = slabCheck;
	}
	public String getIncomeCheck() {
		return incomeCheck;
	}
	public void setIncomeCheck(String incomeCheck) {
		this.incomeCheck = incomeCheck;
	}
	public String getDojCheck() {
		return dojCheck;
	}
	public void setDojCheck(String dojCheck) {
		this.dojCheck = dojCheck;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public ProfessionalTaxRep() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBrnId() {
		return brnId;
	}
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getStateCheck() {
		return stateCheck;
	}
	public void setStateCheck(String stateCheck) {
		this.stateCheck = stateCheck;
	}
	public String getBranchCheck() {
		return branchCheck;
	}
	public void setBranchCheck(String branchCheck) {
		this.branchCheck = branchCheck;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDivAdd() {
		return divAdd;
	}
	public void setDivAdd(String divAdd) {
		this.divAdd = divAdd;
	}
	public String getReportOption() {
		return reportOption;
	}
	public void setReportOption(String reportOption) {
		this.reportOption = reportOption;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	

}
