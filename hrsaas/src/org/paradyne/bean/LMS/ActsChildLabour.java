package org.paradyne.bean.LMS;

/**
 * @author Ganesh
 */

public class ActsChildLabour {
	
	private String ReturnID="";
	private String ChildLaboutActID="";
	private String occupation="";
	private String AreHazardousProcessesCarriedOut="";// Are hazardous occupations and 
	// processes carried out in the
	// establishment?
	private String HazardousOccupationDetails="";//indicate the entry number of the occupation  in the schedule to the child labour act 1986
	private String HazardousProcessDetails="";//indicate the entry number of the  process in the schedule to the child labour act 1986


	private String childinRegulatedPart=""; // Child labour in regulated part.
	private String NumberOfChildLaboursEmployed="";
	private String ChildLaboursEmployed=""; //As at the reporting date does your establishment employ any persons under the age of 14 years?
	private String workofChild="";

	private String WorkDetails="";// What work do people under the age of 14 years perform?
	private String WorkHrsPerDay=""; // How many hours does the child work each day? 
	private String WorkHrsWithoutBreak=""; //How many hours does the child work without a break?
	private String BreakDuration=""; // If a child has a work-break, how long is it?
	private String MaxSpreadOverHrs=""; // What is the maximum spread- over hours for your child labours?
	private String NoOfChildLaboursWorkingEarlynLate=""; // How many of your child labour work after 7.00 pm and before 8.00 am?
	private String NumberOfHolidays=""; // How many whole days per week, does a child labour have as a holiday?
	private String childlabourWage=""; // What is the wage of a child labour?
	private String WagesPerHr="";
	private String WagesPerDay="";
	private String WagesPerWeek="";
	private String WagesPerMonth="";
	private String SafetyNHealthInitiativesTaken=""; // Have you taken any particular steps  to protect the safety and health of your child labours?
	
	private String attendTime="";
	private String ChildLaboursAttendSchool=""; // Do you child labours attend school? Do you provide financial or other assistance to the child labour
	private String howmanyHours="";
	private String Date="";
	
	private String childlabourSafetyYes="";// 	If yes, what have you done? (Explain) 
	private String SchoolHrsPerDay="";// If yes, how many hours do they attend school?+	Per day
	private String SchoolHrsPerWeek=""; //If yes, how many hours do they attend school?+	Per week
	private String childLabourFinancialOtherAssistance="";//Do you provide financial or other assistance to the child labour? 
	private String NotifiedToLabourDept="";//If you employ child labours, have you notified the Department of Labour indicating the name of your establishment, the name of the responsible manager, the postal address, and the nature of occupations and processes carried out at the establishment? 
	private String NoticeDate="";
	private String ChildLabourRegisterMaintained="";
	
	
	public String getNoticeDate() {
		return NoticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		NoticeDate = noticeDate;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAreHazardousProcessesCarriedOut() {
		return AreHazardousProcessesCarriedOut;
	}
	public void setAreHazardousProcessesCarriedOut(
			String areHazardousProcessesCarriedOut) {
		AreHazardousProcessesCarriedOut = areHazardousProcessesCarriedOut;
	}
	public String getChildinRegulatedPart() {
		return childinRegulatedPart;
	}
	public void setChildinRegulatedPart(String childinRegulatedPart) {
		this.childinRegulatedPart = childinRegulatedPart;
	}
	public String getNumberOfChildLaboursEmployed() {
		return NumberOfChildLaboursEmployed;
	}
	public void setNumberOfChildLaboursEmployed(String numberOfChildLaboursEmployed) {
		NumberOfChildLaboursEmployed = numberOfChildLaboursEmployed;
	}
	public String getChildLaboursEmployed() {
		return ChildLaboursEmployed;
	}
	public void setChildLaboursEmployed(String childLaboursEmployed) {
		ChildLaboursEmployed = childLaboursEmployed;
	}
	public String getWorkofChild() {
		return workofChild;
	}
	public void setWorkofChild(String workofChild) {
		this.workofChild = workofChild;
	}
	public String getWorkDetails() {
		return WorkDetails;
	}
	public void setWorkDetails(String workDetails) {
		WorkDetails = workDetails;
	}
	public String getWorkHrsPerDay() {
		return WorkHrsPerDay;
	}
	public void setWorkHrsPerDay(String workHrsPerDay) {
		WorkHrsPerDay = workHrsPerDay;
	}
	public String getWorkHrsWithoutBreak() {
		return WorkHrsWithoutBreak;
	}
	public void setWorkHrsWithoutBreak(String workHrsWithoutBreak) {
		WorkHrsWithoutBreak = workHrsWithoutBreak;
	}
	public String getBreakDuration() {
		return BreakDuration;
	}
	public void setBreakDuration(String breakDuration) {
		BreakDuration = breakDuration;
	}
	public String getMaxSpreadOverHrs() {
		return MaxSpreadOverHrs;
	}
	public void setMaxSpreadOverHrs(String maxSpreadOverHrs) {
		MaxSpreadOverHrs = maxSpreadOverHrs;
	}
	public String getNoOfChildLaboursWorkingEarlynLate() {
		return NoOfChildLaboursWorkingEarlynLate;
	}
	public void setNoOfChildLaboursWorkingEarlynLate(
			String noOfChildLaboursWorkingEarlynLate) {
		NoOfChildLaboursWorkingEarlynLate = noOfChildLaboursWorkingEarlynLate;
	}
	public String getNumberOfHolidays() {
		return NumberOfHolidays;
	}
	public void setNumberOfHolidays(String numberOfHolidays) {
		NumberOfHolidays = numberOfHolidays;
	}
	public String getChildlabourWage() {
		return childlabourWage;
	}
	public void setChildlabourWage(String childlabourWage) {
		this.childlabourWage = childlabourWage;
	}
	public String getSafetyNHealthInitiativesTaken() {
		return SafetyNHealthInitiativesTaken;
	}
	public void setSafetyNHealthInitiativesTaken(
			String safetyNHealthInitiativesTaken) {
		SafetyNHealthInitiativesTaken = safetyNHealthInitiativesTaken;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getChildLaboursAttendSchool() {
		return ChildLaboursAttendSchool;
	}
	public void setChildLaboursAttendSchool(String childLaboursAttendSchool) {
		ChildLaboursAttendSchool = childLaboursAttendSchool;
	}
	public String getHowmanyHours() {
		return howmanyHours;
	}
	public void setHowmanyHours(String howmanyHours) {
		this.howmanyHours = howmanyHours;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getChildLabourRegisterMaintained() {
		return ChildLabourRegisterMaintained;
	}
	public void setChildLabourRegisterMaintained(
			String childLabourRegisterMaintained) {
		ChildLabourRegisterMaintained = childLabourRegisterMaintained;
	}
	public String getChildlabourSafetyYes() {
		return childlabourSafetyYes;
	}
	public void setChildlabourSafetyYes(String childlabourSafetyYes) {
		this.childlabourSafetyYes = childlabourSafetyYes;
	}
	public String getSchoolHrsPerDay() {
		return SchoolHrsPerDay;
	}
	public void setSchoolHrsPerDay(String schoolHrsPerDay) {
		SchoolHrsPerDay = schoolHrsPerDay;
	}
	public String getSchoolHrsPerWeek() {
		return SchoolHrsPerWeek;
	}
	public void setSchoolHrsPerWeek(String schoolHrsPerWeek) {
		SchoolHrsPerWeek = schoolHrsPerWeek;
	}
	public String getChildLabourFinancialOtherAssistance() {
		return childLabourFinancialOtherAssistance;
	}
	public void setChildLabourFinancialOtherAssistance(
			String childLabourFinancialOtherAssistance) {
		this.childLabourFinancialOtherAssistance = childLabourFinancialOtherAssistance;
	}
	public String getReturnID() {
		return ReturnID;
	}
	public void setReturnID(String returnID) {
		ReturnID = returnID;
	}
	public String getChildLaboutActID() {
		return ChildLaboutActID;
	}
	public void setChildLaboutActID(String childLaboutActID) {
		ChildLaboutActID = childLaboutActID;
	}
	public String getHazardousOccupationDetails() {
		return HazardousOccupationDetails;
	}
	public void setHazardousOccupationDetails(String hazardousOccupationDetails) {
		HazardousOccupationDetails = hazardousOccupationDetails;
	}
	public String getHazardousProcessDetails() {
		return HazardousProcessDetails;
	}
	public void setHazardousProcessDetails(String hazardousProcessDetails) {
		HazardousProcessDetails = hazardousProcessDetails;
	}
	public String getWagesPerHr() {
		return WagesPerHr;
	}
	public void setWagesPerHr(String wagesPerHr) {
		WagesPerHr = wagesPerHr;
	}
	public String getWagesPerDay() {
		return WagesPerDay;
	}
	public void setWagesPerDay(String wagesPerDay) {
		WagesPerDay = wagesPerDay;
	}
	public String getWagesPerWeek() {
		return WagesPerWeek;
	}
	public void setWagesPerWeek(String wagesPerWeek) {
		WagesPerWeek = wagesPerWeek;
	}
	public String getWagesPerMonth() {
		return WagesPerMonth;
	}
	public void setWagesPerMonth(String wagesPerMonth) {
		WagesPerMonth = wagesPerMonth;
	}
	public String getNotifiedToLabourDept() {
		return NotifiedToLabourDept;
	}
	public void setNotifiedToLabourDept(String notifiedToLabourDept) {
		NotifiedToLabourDept = notifiedToLabourDept;
	}
	
	
	
}
