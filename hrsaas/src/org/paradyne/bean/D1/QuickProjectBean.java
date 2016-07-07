package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class QuickProjectBean extends BeanBase {

	public String quickProjectCode="";
	public String forwardToHidden="";
	public String comments="";
	public String completedBy="";
	public String completedOn="";
	
	public String nextAppToken="";
	public String nextAppName="";
	public String nextAppCode="";	
	
	public String forwardTo="";
	public String managerCode="";
	public String managerToken="";	
	public String standardHour="";
	public String noOfTemps="";
	public String positionTitle="";
	public String brand="";
	public String branch="";
	public String brandCode="";
	public String branchCode="";
	public String departmentCode="";
	public String executive="";
	public String area="";
	public String location="";
	public String customer="";
	public String department="";
	public String managerName="";
	public String phone="";
	public String managerEmail="";
	public String opsDirectorName="";
	public String maxTempAgency="";
	public String perWeek="";
	public String otRequired="";
	public String noOfOTHrs="";
	public String project="";
	public String startDate="";
	public String endDate="";
	public String extension="";
	
	public String sbu="";
	public String supportType="";
	public String hardwareSkill="";
	public String softwareSkill="";
	
	public String networkSkill="";
	public String otherSkill="";
	public String succExp="";
	public String nextExp="";
	public String currExp="";
	public String perCurrentMonth="";
	public String perNextMonth="";
	public String perSuccessiveMonth="";
	public String revCurrentMonth="";
	public String revNextMonth="";
	public String revSuccessiveMonth="";
	public String laborCurrentMonth="";
	public String laborNextMonth="";
	public String laborSuccessiveMonth="";
	

	public String othorCurrentMonth="";
	public String othorNextMonth="";
	public String othorSuccessiveMonth="";
	public String totalCurrentMonth="";
	public String totalNextMonth="";
	public String totalSuccessiveMonth="";
	public String profitCurrentMonth="";
	public String profitNextMonth="";
	public String profitSuccessiveMonth="";
	
	public String grossCurrentMonth="";
	public String grossNextMonth="";
	public String grossSuccessiveMonth="";

	public String meetsCurrentMonth="";
	public String meetsNextMonth="";
	public String meetsSuccessiveMonth="";
	
	
	public String revCurrent="";
	public String revNext="";
	public String revSuccessive="";
	public String laborCurrent="";
	public String laborNext="";
	public String laborSuccessive="";
	

	public String othorCurrent="";
	public String othorNext="";
	public String othorSuccessive="";
	public String opsCurrent="";
	public String opsNext="";
	public String opsSuccessive="";
	
	public String headerName="";
	public String checkData="";
	public String fileheaderName="";
	public String buttonName="";
	private String myPage= "";
	private String myPage1= "";
	private String myPage2= "";
	private String flag="";
	private String status="";
	private String hiddenValue="";
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	
	private String ittEmployeeToken= "";
	private String ittquickProjectCode= "";
	private String ittEmployee= "";
	private String ittApplicationDate="";
	private ArrayList listDraft=null;
	private ArrayList listReject=null;
	private ArrayList listApprove=null;
	private ArrayList listResubmit=null;
	private ArrayList listInProgress=null;
	private ArrayList listSendBack=null;
	private ArrayList approverList=null;
	private ArrayList listComment=null;
	
	private String applicantComments = "";

	/**
	 * @return the quickProjectCode
	 */
	public String getQuickProjectCode() {
		return this.quickProjectCode;
	}

	/**
	 * @param quickProjectCode the quickProjectCode to set
	 */
	public void setQuickProjectCode(String quickProjectCode) {
		this.quickProjectCode = quickProjectCode;
	}

	/**
	 * @return the forwardToHidden
	 */
	public String getForwardToHidden() {
		return this.forwardToHidden;
	}

	/**
	 * @param forwardToHidden the forwardToHidden to set
	 */
	public void setForwardToHidden(String forwardToHidden) {
		this.forwardToHidden = forwardToHidden;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the completedBy
	 */
	public String getCompletedBy() {
		return this.completedBy;
	}

	/**
	 * @param completedBy the completedBy to set
	 */
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	/**
	 * @return the completedOn
	 */
	public String getCompletedOn() {
		return this.completedOn;
	}

	/**
	 * @param completedOn the completedOn to set
	 */
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	/**
	 * @return the nextAppToken
	 */
	public String getNextAppToken() {
		return this.nextAppToken;
	}

	/**
	 * @param nextAppToken the nextAppToken to set
	 */
	public void setNextAppToken(String nextAppToken) {
		this.nextAppToken = nextAppToken;
	}

	/**
	 * @return the nextAppName
	 */
	public String getNextAppName() {
		return this.nextAppName;
	}

	/**
	 * @param nextAppName the nextAppName to set
	 */
	public void setNextAppName(String nextAppName) {
		this.nextAppName = nextAppName;
	}

	/**
	 * @return the nextAppCode
	 */
	public String getNextAppCode() {
		return this.nextAppCode;
	}

	/**
	 * @param nextAppCode the nextAppCode to set
	 */
	public void setNextAppCode(String nextAppCode) {
		this.nextAppCode = nextAppCode;
	}

	/**
	 * @return the forwardTo
	 */
	public String getForwardTo() {
		return this.forwardTo;
	}

	/**
	 * @param forwardTo the forwardTo to set
	 */
	public void setForwardTo(String forwardTo) {
		this.forwardTo = forwardTo;
	}

	/**
	 * @return the managerCode
	 */
	public String getManagerCode() {
		return this.managerCode;
	}

	/**
	 * @param managerCode the managerCode to set
	 */
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	/**
	 * @return the managerToken
	 */
	public String getManagerToken() {
		return this.managerToken;
	}

	/**
	 * @param managerToken the managerToken to set
	 */
	public void setManagerToken(String managerToken) {
		this.managerToken = managerToken;
	}

	/**
	 * @return the standardHour
	 */
	public String getStandardHour() {
		return this.standardHour;
	}

	/**
	 * @param standardHour the standardHour to set
	 */
	public void setStandardHour(String standardHour) {
		this.standardHour = standardHour;
	}

	/**
	 * @return the noOfTemps
	 */
	public String getNoOfTemps() {
		return this.noOfTemps;
	}

	/**
	 * @param noOfTemps the noOfTemps to set
	 */
	public void setNoOfTemps(String noOfTemps) {
		this.noOfTemps = noOfTemps;
	}

	/**
	 * @return the positionTitle
	 */
	public String getPositionTitle() {
		return this.positionTitle;
	}

	/**
	 * @param positionTitle the positionTitle to set
	 */
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return this.brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return this.branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the brandCode
	 */
	public String getBrandCode() {
		return this.brandCode;
	}

	/**
	 * @param brandCode the brandCode to set
	 */
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return this.branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return this.departmentCode;
	}

	/**
	 * @param departmentCode the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the executive
	 */
	public String getExecutive() {
		return this.executive;
	}

	/**
	 * @param executive the executive to set
	 */
	public void setExecutive(String executive) {
		this.executive = executive;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return this.area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return this.customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return this.managerName;
	}

	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the managerEmail
	 */
	public String getManagerEmail() {
		return this.managerEmail;
	}

	/**
	 * @param managerEmail the managerEmail to set
	 */
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	/**
	 * @return the opsDirectorName
	 */
	public String getOpsDirectorName() {
		return this.opsDirectorName;
	}

	/**
	 * @param opsDirectorName the opsDirectorName to set
	 */
	public void setOpsDirectorName(String opsDirectorName) {
		this.opsDirectorName = opsDirectorName;
	}

	/**
	 * @return the maxTempAgency
	 */
	public String getMaxTempAgency() {
		return this.maxTempAgency;
	}

	/**
	 * @param maxTempAgency the maxTempAgency to set
	 */
	public void setMaxTempAgency(String maxTempAgency) {
		this.maxTempAgency = maxTempAgency;
	}

	/**
	 * @return the perWeek
	 */
	public String getPerWeek() {
		return this.perWeek;
	}

	/**
	 * @param perWeek the perWeek to set
	 */
	public void setPerWeek(String perWeek) {
		this.perWeek = perWeek;
	}

	/**
	 * @return the otRequired
	 */
	public String getOtRequired() {
		return this.otRequired;
	}

	/**
	 * @param otRequired the otRequired to set
	 */
	public void setOtRequired(String otRequired) {
		this.otRequired = otRequired;
	}

	/**
	 * @return the noOfOTHrs
	 */
	public String getNoOfOTHrs() {
		return this.noOfOTHrs;
	}

	/**
	 * @param noOfOTHrs the noOfOTHrs to set
	 */
	public void setNoOfOTHrs(String noOfOTHrs) {
		this.noOfOTHrs = noOfOTHrs;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return this.project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the sbu
	 */
	public String getSbu() {
		return this.sbu;
	}

	/**
	 * @param sbu the sbu to set
	 */
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	/**
	 * @return the supportType
	 */
	public String getSupportType() {
		return this.supportType;
	}

	/**
	 * @param supportType the supportType to set
	 */
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}

	/**
	 * @return the hardwareSkill
	 */
	public String getHardwareSkill() {
		return this.hardwareSkill;
	}

	/**
	 * @param hardwareSkill the hardwareSkill to set
	 */
	public void setHardwareSkill(String hardwareSkill) {
		this.hardwareSkill = hardwareSkill;
	}

	/**
	 * @return the softwareSkill
	 */
	public String getSoftwareSkill() {
		return this.softwareSkill;
	}

	/**
	 * @param softwareSkill the softwareSkill to set
	 */
	public void setSoftwareSkill(String softwareSkill) {
		this.softwareSkill = softwareSkill;
	}

	/**
	 * @return the networkSkill
	 */
	public String getNetworkSkill() {
		return this.networkSkill;
	}

	/**
	 * @param networkSkill the networkSkill to set
	 */
	public void setNetworkSkill(String networkSkill) {
		this.networkSkill = networkSkill;
	}

	/**
	 * @return the otherSkill
	 */
	public String getOtherSkill() {
		return this.otherSkill;
	}

	/**
	 * @param otherSkill the otherSkill to set
	 */
	public void setOtherSkill(String otherSkill) {
		this.otherSkill = otherSkill;
	}

	/**
	 * @return the succExp
	 */
	public String getSuccExp() {
		return this.succExp;
	}

	/**
	 * @param succExp the succExp to set
	 */
	public void setSuccExp(String succExp) {
		this.succExp = succExp;
	}

	/**
	 * @return the nextExp
	 */
	public String getNextExp() {
		return this.nextExp;
	}

	/**
	 * @param nextExp the nextExp to set
	 */
	public void setNextExp(String nextExp) {
		this.nextExp = nextExp;
	}

	/**
	 * @return the currExp
	 */
	public String getCurrExp() {
		return this.currExp;
	}

	/**
	 * @param currExp the currExp to set
	 */
	public void setCurrExp(String currExp) {
		this.currExp = currExp;
	}

	/**
	 * @return the perCurrentMonth
	 */
	public String getPerCurrentMonth() {
		return this.perCurrentMonth;
	}

	/**
	 * @param perCurrentMonth the perCurrentMonth to set
	 */
	public void setPerCurrentMonth(String perCurrentMonth) {
		this.perCurrentMonth = perCurrentMonth;
	}

	/**
	 * @return the perNextMonth
	 */
	public String getPerNextMonth() {
		return this.perNextMonth;
	}

	/**
	 * @param perNextMonth the perNextMonth to set
	 */
	public void setPerNextMonth(String perNextMonth) {
		this.perNextMonth = perNextMonth;
	}

	/**
	 * @return the perSuccessiveMonth
	 */
	public String getPerSuccessiveMonth() {
		return this.perSuccessiveMonth;
	}

	/**
	 * @param perSuccessiveMonth the perSuccessiveMonth to set
	 */
	public void setPerSuccessiveMonth(String perSuccessiveMonth) {
		this.perSuccessiveMonth = perSuccessiveMonth;
	}

	/**
	 * @return the revCurrentMonth
	 */
	public String getRevCurrentMonth() {
		return this.revCurrentMonth;
	}

	/**
	 * @param revCurrentMonth the revCurrentMonth to set
	 */
	public void setRevCurrentMonth(String revCurrentMonth) {
		this.revCurrentMonth = revCurrentMonth;
	}

	/**
	 * @return the revNextMonth
	 */
	public String getRevNextMonth() {
		return this.revNextMonth;
	}

	/**
	 * @param revNextMonth the revNextMonth to set
	 */
	public void setRevNextMonth(String revNextMonth) {
		this.revNextMonth = revNextMonth;
	}

	/**
	 * @return the revSuccessiveMonth
	 */
	public String getRevSuccessiveMonth() {
		return this.revSuccessiveMonth;
	}

	/**
	 * @param revSuccessiveMonth the revSuccessiveMonth to set
	 */
	public void setRevSuccessiveMonth(String revSuccessiveMonth) {
		this.revSuccessiveMonth = revSuccessiveMonth;
	}

	/**
	 * @return the laborCurrentMonth
	 */
	public String getLaborCurrentMonth() {
		return this.laborCurrentMonth;
	}

	/**
	 * @param laborCurrentMonth the laborCurrentMonth to set
	 */
	public void setLaborCurrentMonth(String laborCurrentMonth) {
		this.laborCurrentMonth = laborCurrentMonth;
	}

	/**
	 * @return the laborNextMonth
	 */
	public String getLaborNextMonth() {
		return this.laborNextMonth;
	}

	/**
	 * @param laborNextMonth the laborNextMonth to set
	 */
	public void setLaborNextMonth(String laborNextMonth) {
		this.laborNextMonth = laborNextMonth;
	}

	/**
	 * @return the laborSuccessiveMonth
	 */
	public String getLaborSuccessiveMonth() {
		return this.laborSuccessiveMonth;
	}

	/**
	 * @param laborSuccessiveMonth the laborSuccessiveMonth to set
	 */
	public void setLaborSuccessiveMonth(String laborSuccessiveMonth) {
		this.laborSuccessiveMonth = laborSuccessiveMonth;
	}

	/**
	 * @return the othorCurrentMonth
	 */
	public String getOthorCurrentMonth() {
		return this.othorCurrentMonth;
	}

	/**
	 * @param othorCurrentMonth the othorCurrentMonth to set
	 */
	public void setOthorCurrentMonth(String othorCurrentMonth) {
		this.othorCurrentMonth = othorCurrentMonth;
	}

	/**
	 * @return the othorNextMonth
	 */
	public String getOthorNextMonth() {
		return this.othorNextMonth;
	}

	/**
	 * @param othorNextMonth the othorNextMonth to set
	 */
	public void setOthorNextMonth(String othorNextMonth) {
		this.othorNextMonth = othorNextMonth;
	}

	/**
	 * @return the othorSuccessiveMonth
	 */
	public String getOthorSuccessiveMonth() {
		return this.othorSuccessiveMonth;
	}

	/**
	 * @param othorSuccessiveMonth the othorSuccessiveMonth to set
	 */
	public void setOthorSuccessiveMonth(String othorSuccessiveMonth) {
		this.othorSuccessiveMonth = othorSuccessiveMonth;
	}

	/**
	 * @return the totalCurrentMonth
	 */
	public String getTotalCurrentMonth() {
		return this.totalCurrentMonth;
	}

	/**
	 * @param totalCurrentMonth the totalCurrentMonth to set
	 */
	public void setTotalCurrentMonth(String totalCurrentMonth) {
		this.totalCurrentMonth = totalCurrentMonth;
	}

	/**
	 * @return the totalNextMonth
	 */
	public String getTotalNextMonth() {
		return this.totalNextMonth;
	}

	/**
	 * @param totalNextMonth the totalNextMonth to set
	 */
	public void setTotalNextMonth(String totalNextMonth) {
		this.totalNextMonth = totalNextMonth;
	}

	/**
	 * @return the totalSuccessiveMonth
	 */
	public String getTotalSuccessiveMonth() {
		return this.totalSuccessiveMonth;
	}

	/**
	 * @param totalSuccessiveMonth the totalSuccessiveMonth to set
	 */
	public void setTotalSuccessiveMonth(String totalSuccessiveMonth) {
		this.totalSuccessiveMonth = totalSuccessiveMonth;
	}

	/**
	 * @return the profitCurrentMonth
	 */
	public String getProfitCurrentMonth() {
		return this.profitCurrentMonth;
	}

	/**
	 * @param profitCurrentMonth the profitCurrentMonth to set
	 */
	public void setProfitCurrentMonth(String profitCurrentMonth) {
		this.profitCurrentMonth = profitCurrentMonth;
	}

	/**
	 * @return the profitNextMonth
	 */
	public String getProfitNextMonth() {
		return this.profitNextMonth;
	}

	/**
	 * @param profitNextMonth the profitNextMonth to set
	 */
	public void setProfitNextMonth(String profitNextMonth) {
		this.profitNextMonth = profitNextMonth;
	}

	/**
	 * @return the profitSuccessiveMonth
	 */
	public String getProfitSuccessiveMonth() {
		return this.profitSuccessiveMonth;
	}

	/**
	 * @param profitSuccessiveMonth the profitSuccessiveMonth to set
	 */
	public void setProfitSuccessiveMonth(String profitSuccessiveMonth) {
		this.profitSuccessiveMonth = profitSuccessiveMonth;
	}

	/**
	 * @return the grossCurrentMonth
	 */
	public String getGrossCurrentMonth() {
		return this.grossCurrentMonth;
	}

	/**
	 * @param grossCurrentMonth the grossCurrentMonth to set
	 */
	public void setGrossCurrentMonth(String grossCurrentMonth) {
		this.grossCurrentMonth = grossCurrentMonth;
	}

	/**
	 * @return the grossNextMonth
	 */
	public String getGrossNextMonth() {
		return this.grossNextMonth;
	}

	/**
	 * @param grossNextMonth the grossNextMonth to set
	 */
	public void setGrossNextMonth(String grossNextMonth) {
		this.grossNextMonth = grossNextMonth;
	}

	/**
	 * @return the grossSuccessiveMonth
	 */
	public String getGrossSuccessiveMonth() {
		return this.grossSuccessiveMonth;
	}

	/**
	 * @param grossSuccessiveMonth the grossSuccessiveMonth to set
	 */
	public void setGrossSuccessiveMonth(String grossSuccessiveMonth) {
		this.grossSuccessiveMonth = grossSuccessiveMonth;
	}

	/**
	 * @return the meetsCurrentMonth
	 */
	public String getMeetsCurrentMonth() {
		return this.meetsCurrentMonth;
	}

	/**
	 * @param meetsCurrentMonth the meetsCurrentMonth to set
	 */
	public void setMeetsCurrentMonth(String meetsCurrentMonth) {
		this.meetsCurrentMonth = meetsCurrentMonth;
	}

	/**
	 * @return the meetsNextMonth
	 */
	public String getMeetsNextMonth() {
		return this.meetsNextMonth;
	}

	/**
	 * @param meetsNextMonth the meetsNextMonth to set
	 */
	public void setMeetsNextMonth(String meetsNextMonth) {
		this.meetsNextMonth = meetsNextMonth;
	}

	/**
	 * @return the meetsSuccessiveMonth
	 */
	public String getMeetsSuccessiveMonth() {
		return this.meetsSuccessiveMonth;
	}

	/**
	 * @param meetsSuccessiveMonth the meetsSuccessiveMonth to set
	 */
	public void setMeetsSuccessiveMonth(String meetsSuccessiveMonth) {
		this.meetsSuccessiveMonth = meetsSuccessiveMonth;
	}

	/**
	 * @return the revCurrent
	 */
	public String getRevCurrent() {
		return this.revCurrent;
	}

	/**
	 * @param revCurrent the revCurrent to set
	 */
	public void setRevCurrent(String revCurrent) {
		this.revCurrent = revCurrent;
	}

	/**
	 * @return the revNext
	 */
	public String getRevNext() {
		return this.revNext;
	}

	/**
	 * @param revNext the revNext to set
	 */
	public void setRevNext(String revNext) {
		this.revNext = revNext;
	}

	/**
	 * @return the revSuccessive
	 */
	public String getRevSuccessive() {
		return this.revSuccessive;
	}

	/**
	 * @param revSuccessive the revSuccessive to set
	 */
	public void setRevSuccessive(String revSuccessive) {
		this.revSuccessive = revSuccessive;
	}

	/**
	 * @return the laborCurrent
	 */
	public String getLaborCurrent() {
		return this.laborCurrent;
	}

	/**
	 * @param laborCurrent the laborCurrent to set
	 */
	public void setLaborCurrent(String laborCurrent) {
		this.laborCurrent = laborCurrent;
	}

	/**
	 * @return the laborNext
	 */
	public String getLaborNext() {
		return this.laborNext;
	}

	/**
	 * @param laborNext the laborNext to set
	 */
	public void setLaborNext(String laborNext) {
		this.laborNext = laborNext;
	}

	/**
	 * @return the laborSuccessive
	 */
	public String getLaborSuccessive() {
		return this.laborSuccessive;
	}

	/**
	 * @param laborSuccessive the laborSuccessive to set
	 */
	public void setLaborSuccessive(String laborSuccessive) {
		this.laborSuccessive = laborSuccessive;
	}

	/**
	 * @return the othorCurrent
	 */
	public String getOthorCurrent() {
		return this.othorCurrent;
	}

	/**
	 * @param othorCurrent the othorCurrent to set
	 */
	public void setOthorCurrent(String othorCurrent) {
		this.othorCurrent = othorCurrent;
	}

	/**
	 * @return the othorNext
	 */
	public String getOthorNext() {
		return this.othorNext;
	}

	/**
	 * @param othorNext the othorNext to set
	 */
	public void setOthorNext(String othorNext) {
		this.othorNext = othorNext;
	}

	/**
	 * @return the othorSuccessive
	 */
	public String getOthorSuccessive() {
		return this.othorSuccessive;
	}

	/**
	 * @param othorSuccessive the othorSuccessive to set
	 */
	public void setOthorSuccessive(String othorSuccessive) {
		this.othorSuccessive = othorSuccessive;
	}

	/**
	 * @return the opsCurrent
	 */
	public String getOpsCurrent() {
		return this.opsCurrent;
	}

	/**
	 * @param opsCurrent the opsCurrent to set
	 */
	public void setOpsCurrent(String opsCurrent) {
		this.opsCurrent = opsCurrent;
	}

	/**
	 * @return the opsNext
	 */
	public String getOpsNext() {
		return this.opsNext;
	}

	/**
	 * @param opsNext the opsNext to set
	 */
	public void setOpsNext(String opsNext) {
		this.opsNext = opsNext;
	}

	/**
	 * @return the opsSuccessive
	 */
	public String getOpsSuccessive() {
		return this.opsSuccessive;
	}

	/**
	 * @param opsSuccessive the opsSuccessive to set
	 */
	public void setOpsSuccessive(String opsSuccessive) {
		this.opsSuccessive = opsSuccessive;
	}

	/**
	 * @return the headerName
	 */
	public String getHeaderName() {
		return this.headerName;
	}

	/**
	 * @param headerName the headerName to set
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * @return the checkData
	 */
	public String getCheckData() {
		return this.checkData;
	}

	/**
	 * @param checkData the checkData to set
	 */
	public void setCheckData(String checkData) {
		this.checkData = checkData;
	}

	/**
	 * @return the fileheaderName
	 */
	public String getFileheaderName() {
		return this.fileheaderName;
	}

	/**
	 * @param fileheaderName the fileheaderName to set
	 */
	public void setFileheaderName(String fileheaderName) {
		this.fileheaderName = fileheaderName;
	}

	/**
	 * @return the buttonName
	 */
	public String getButtonName() {
		return this.buttonName;
	}

	/**
	 * @param buttonName the buttonName to set
	 */
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the myPage1
	 */
	public String getMyPage1() {
		return this.myPage1;
	}

	/**
	 * @param myPage1 the myPage1 to set
	 */
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}

	/**
	 * @return the myPage2
	 */
	public String getMyPage2() {
		return this.myPage2;
	}

	/**
	 * @param myPage2 the myPage2 to set
	 */
	public void setMyPage2(String myPage2) {
		this.myPage2 = myPage2;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the hiddenValue
	 */
	public String getHiddenValue() {
		return this.hiddenValue;
	}

	/**
	 * @param hiddenValue the hiddenValue to set
	 */
	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	/**
	 * @return the ittApproverName
	 */
	public String getIttApproverName() {
		return this.ittApproverName;
	}

	/**
	 * @param ittApproverName the ittApproverName to set
	 */
	public void setIttApproverName(String ittApproverName) {
		this.ittApproverName = ittApproverName;
	}

	/**
	 * @return the ittComments
	 */
	public String getIttComments() {
		return this.ittComments;
	}

	/**
	 * @param ittComments the ittComments to set
	 */
	public void setIttComments(String ittComments) {
		this.ittComments = ittComments;
	}

	/**
	 * @return the ittStatus
	 */
	public String getIttStatus() {
		return this.ittStatus;
	}

	/**
	 * @param ittStatus the ittStatus to set
	 */
	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}

	/**
	 * @return the ittEmployeeToken
	 */
	public String getIttEmployeeToken() {
		return this.ittEmployeeToken;
	}

	/**
	 * @param ittEmployeeToken the ittEmployeeToken to set
	 */
	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}

	/**
	 * @return the ittquickProjectCode
	 */
	public String getIttquickProjectCode() {
		return this.ittquickProjectCode;
	}

	/**
	 * @param ittquickProjectCode the ittquickProjectCode to set
	 */
	public void setIttquickProjectCode(String ittquickProjectCode) {
		this.ittquickProjectCode = ittquickProjectCode;
	}

	/**
	 * @return the ittEmployee
	 */
	public String getIttEmployee() {
		return this.ittEmployee;
	}

	/**
	 * @param ittEmployee the ittEmployee to set
	 */
	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}

	/**
	 * @return the ittApplicationDate
	 */
	public String getIttApplicationDate() {
		return this.ittApplicationDate;
	}

	/**
	 * @param ittApplicationDate the ittApplicationDate to set
	 */
	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}

	/**
	 * @return the listDraft
	 */
	public ArrayList getListDraft() {
		return this.listDraft;
	}

	/**
	 * @param listDraft the listDraft to set
	 */
	public void setListDraft(ArrayList listDraft) {
		this.listDraft = listDraft;
	}

	/**
	 * @return the listReject
	 */
	public ArrayList getListReject() {
		return this.listReject;
	}

	/**
	 * @param listReject the listReject to set
	 */
	public void setListReject(ArrayList listReject) {
		this.listReject = listReject;
	}

	/**
	 * @return the listApprove
	 */
	public ArrayList getListApprove() {
		return this.listApprove;
	}

	/**
	 * @param listApprove the listApprove to set
	 */
	public void setListApprove(ArrayList listApprove) {
		this.listApprove = listApprove;
	}

	/**
	 * @return the listResubmit
	 */
	public ArrayList getListResubmit() {
		return this.listResubmit;
	}

	/**
	 * @param listResubmit the listResubmit to set
	 */
	public void setListResubmit(ArrayList listResubmit) {
		this.listResubmit = listResubmit;
	}

	/**
	 * @return the listInProgress
	 */
	public ArrayList getListInProgress() {
		return this.listInProgress;
	}

	/**
	 * @param listInProgress the listInProgress to set
	 */
	public void setListInProgress(ArrayList listInProgress) {
		this.listInProgress = listInProgress;
	}

	/**
	 * @return the listSendBack
	 */
	public ArrayList getListSendBack() {
		return this.listSendBack;
	}

	/**
	 * @param listSendBack the listSendBack to set
	 */
	public void setListSendBack(ArrayList listSendBack) {
		this.listSendBack = listSendBack;
	}

	/**
	 * @return the approverList
	 */
	public ArrayList getApproverList() {
		return this.approverList;
	}

	/**
	 * @param approverList the approverList to set
	 */
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	/**
	 * @return the listComment
	 */
	public ArrayList getListComment() {
		return this.listComment;
	}

	/**
	 * @param listComment the listComment to set
	 */
	public void setListComment(ArrayList listComment) {
		this.listComment = listComment;
	}

	/**
	 * @return the applicantComments
	 */
	public String getApplicantComments() {
		return this.applicantComments;
	}

	/**
	 * @param applicantComments the applicantComments to set
	 */
	public void setApplicantComments(String applicantComments) {
		this.applicantComments = applicantComments;
	}	
}
