package org.paradyne.bean.mypage;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @Modified by AA1711 
 * @purpose : To show Record of Team Members and 
 * 				System Login with Night and Flexi Shift 
 * @Date : 29-Oct-2012
 */
public class MypageProcessManagerAlerts extends BeanBase {
	
	
	//for timecard variables
	private String showPageFlag ="false";
	private String  showalltimecard = "";
	private String  presenttimecard = "";
	private String  leavetimecard = "";
	private String  latetimecard = "";
	private String  absenttimecard = "";
	private String  regularizedtimecard = "";
	private String traveltimecard ="";
	private String halfDaytimecard ="";
	//for attendance regularization
	
	private String approvedRegularization = "";
	private String rejectedRegularization = "";
	private String appliedRegularization  = "";
	private String showallRegularization = "";
	private String empCodeAttReg = "";
	private String empTokenAttReg = "";
	private String empNameAttReg = "";
	
	
	//for leaves
	
	private String approvedLeave = "";
	private String appliedLeave = ""; 
	private String rejectedLeave = "";
	private String cancelledLeave = "";
	private String showallLeave  = "";
		
	private String fromLeaveDate = "";
	private String toLeaveDate = "";
	
	private LinkedHashMap yearMap =null;
	private LinkedHashMap holidayMap =null;
	private TreeMap attendanceFromyearMap =null;
	private TreeMap attendanceToyearMap =null;
	private LinkedHashMap leaveYearMap  =null;
	private String hiddenisClickOn = "Input";
	private String hiddensortOption ="";
	private String dynamicStatus ="";
	private String myPage = "";
	private String pollCode = "";
	private String quesName = "";
	private String optionValue = "";
	private String subject = "";
	private String description = "";
	private String hideIdentity = "";
	private String hdMenuCode = "";
	private boolean pageFlag = false;
	private String myPageMenu = "";
	private boolean myMenuPageFlag = false;
	private boolean birthdayPageFlag = false;
	private boolean newJoineePageFlag = false;
	private String myPageNewJoinee = "";
	private boolean ismenuDataAvailable = false;
	private boolean empDataAvailable = false;
	private String empTokenMyPage="";
	private String empNameMyPage = "";
	private String empCodeMyPage = ""; 
	private String fromdate = "";
	private String todate = "";
	 
	private String messageStatus = "";
	private TreeMap moduleMap = null;
	private TreeMap statusMap = null;
	private String moduleName = "";
	private String status = "";
	private String mypageyear = "";
	private String searchMessage = "";
	private String hiddenModuleName = "";	 
	private String hiddenYear = "";
	private String hiddenSearchMessgae = "";		
	private String mypageStatus =""; 
	private String showFlag ="false";
	 

	// Fields for My Attendance --> Annual Attendance -- START
	private String annualAttendanceFromMonth = "";
	private String annualAttendanceFromYear = "";
	private String annualAttendanceToMonth = "";
	private String annualAttendanceToYear = "";
	private String annualAttendanceEmpCode = "";
	private String annualAttendanceEmpToken = "";
	private String annualAttendanceEmpName = "";

	// Fields for My Attendance --> Annual Attendance -- END
	
	//Fields for My Attendance --> holiday -- START
	private String holidayYear = "";
	private String holidayCurrentYear = "";
	LinkedHashMap<String, String> holidayLocationNameList =null ;
	private String holidayLocation = "";
	//Fields for My Attendance --> holiday -- END
	
	private String regularizationYear ="";
	private String leaveEmpCode  = "";
	private String leaveEmpToken  = "";
	private String leaveEmpName  = "";
	private String leaveYear ="";
	private String checkItemCount ="";
	private String myTeamFlag="false";
	private String showMyCardFlag="false";
	private String notLoginEmp="false";
	private String myTeamLeaveFlag="false";
	private String report="";
	private boolean loginAttendanceFlg= false;
	private boolean isAttendance=false;
	
	public String getLeaveYear() {
		return leaveYear;
	}
	public void setLeaveYear(String leaveYear) {
		this.leaveYear = leaveYear;
	}
	public String getLeaveEmpCode() {
		return leaveEmpCode;
	}
	public void setLeaveEmpCode(String leaveEmpCode) {
		this.leaveEmpCode = leaveEmpCode;
	}
	public String getLeaveEmpToken() {
		return leaveEmpToken;
	}
	public void setLeaveEmpToken(String leaveEmpToken) {
		this.leaveEmpToken = leaveEmpToken;
	}
	public String getLeaveEmpName() {
		return leaveEmpName;
	}
	public void setLeaveEmpName(String leaveEmpName) {
		this.leaveEmpName = leaveEmpName;
	}
	public String getHolidayLocation() {
		return holidayLocation;
	}
	public void setHolidayLocation(String holidayLocation) {
		this.holidayLocation = holidayLocation;
	}
	public String getHolidayYear() {
		return holidayYear;
	}
	public void setHolidayYear(String holidayYear) {
		this.holidayYear = holidayYear;
	}
	public String getRegularizationYear() {
		return regularizationYear;
	}
	public void setRegularizationYear(String regularizationYear) {
		this.regularizationYear = regularizationYear;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getMypageStatus() {
		return mypageStatus;
	}
	public void setMypageStatus(String mypageStatus) {
		this.mypageStatus = mypageStatus;
	}
	public String getHiddenModuleName() {
		return hiddenModuleName;
	}
	public void setHiddenModuleName(String hiddenModuleName) {
		this.hiddenModuleName = hiddenModuleName;
	}
	public String getHiddenYear() {
		return hiddenYear;
	}
	public void setHiddenYear(String hiddenYear) {
		this.hiddenYear = hiddenYear;
	}
	public String getHiddenSearchMessgae() {
		return hiddenSearchMessgae;
	}
	public void setHiddenSearchMessgae(String hiddenSearchMessgae) {
		this.hiddenSearchMessgae = hiddenSearchMessgae;
	}
	public String getSearchMessage() {
		return searchMessage;
	}
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	public String getMypageyear() {
		return mypageyear;
	}
	public void setMypageyear(String mypageyear) {
		this.mypageyear = mypageyear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public TreeMap getModuleMap() {
		return moduleMap;
	}
	public void setModuleMap(TreeMap moduleMap) {
		this.moduleMap = moduleMap;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getPollCode() {
		return pollCode;
	}
	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}
	public String getQuesName() {
		return quesName;
	}
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHideIdentity() {
		return hideIdentity;
	}
	public void setHideIdentity(String hideIdentity) {
		this.hideIdentity = hideIdentity;
	}
	public String getHdMenuCode() {
		return hdMenuCode;
	}
	public void setHdMenuCode(String hdMenuCode) {
		this.hdMenuCode = hdMenuCode;
	}
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getMyPageMenu() {
		return myPageMenu;
	}
	public void setMyPageMenu(String myPageMenu) {
		this.myPageMenu = myPageMenu;
	}
	public boolean isMyMenuPageFlag() {
		return myMenuPageFlag;
	}
	public void setMyMenuPageFlag(boolean myMenuPageFlag) {
		this.myMenuPageFlag = myMenuPageFlag;
	}
	public boolean isBirthdayPageFlag() {
		return birthdayPageFlag;
	}
	public void setBirthdayPageFlag(boolean birthdayPageFlag) {
		this.birthdayPageFlag = birthdayPageFlag;
	}
	public boolean isNewJoineePageFlag() {
		return newJoineePageFlag;
	}
	public void setNewJoineePageFlag(boolean newJoineePageFlag) {
		this.newJoineePageFlag = newJoineePageFlag;
	}
	public String getMyPageNewJoinee() {
		return myPageNewJoinee;
	}
	public void setMyPageNewJoinee(String myPageNewJoinee) {
		this.myPageNewJoinee = myPageNewJoinee;
	}
	public boolean isIsmenuDataAvailable() {
		return ismenuDataAvailable;
	}
	public void setIsmenuDataAvailable(boolean ismenuDataAvailable) {
		this.ismenuDataAvailable = ismenuDataAvailable;
	}
	public boolean isEmpDataAvailable() {
		return empDataAvailable;
	}
	public void setEmpDataAvailable(boolean empDataAvailable) {
		this.empDataAvailable = empDataAvailable;
	}
	public String getEmpTokenMyPage() {
		return empTokenMyPage;
 
	}
	public void setEmpTokenMyPage(String empTokenMyPage) {
		this.empTokenMyPage = empTokenMyPage;
	}
	public String getEmpNameMyPage() {
		return empNameMyPage;
	}
	public void setEmpNameMyPage(String empNameMyPage) {
		this.empNameMyPage = empNameMyPage;
	}
	public String getEmpCodeMyPage() {
		return empCodeMyPage;
	}
	public void setEmpCodeMyPage(String empCodeMyPage) {
		this.empCodeMyPage = empCodeMyPage;
	}
	public TreeMap getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(TreeMap statusMap) {
		this.statusMap = statusMap;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getAnnualAttendanceFromMonth() {
		return annualAttendanceFromMonth;
	}
	public void setAnnualAttendanceFromMonth(String annualAttendanceFromMonth) {
		this.annualAttendanceFromMonth = annualAttendanceFromMonth;
	}
	public String getAnnualAttendanceFromYear() {
		return annualAttendanceFromYear;
	}
	public void setAnnualAttendanceFromYear(String annualAttendanceFromYear) {
		this.annualAttendanceFromYear = annualAttendanceFromYear;
	}
	public String getAnnualAttendanceToMonth() {
		return annualAttendanceToMonth;
	}
	public void setAnnualAttendanceToMonth(String annualAttendanceToMonth) {
		this.annualAttendanceToMonth = annualAttendanceToMonth;
	}
	public String getAnnualAttendanceToYear() {
		return annualAttendanceToYear;
	}
	public void setAnnualAttendanceToYear(String annualAttendanceToYear) {
		this.annualAttendanceToYear = annualAttendanceToYear;
	}
	public String getAnnualAttendanceEmpCode() {
		return annualAttendanceEmpCode;
	}
	public void setAnnualAttendanceEmpCode(String annualAttendanceEmpCode) {
		this.annualAttendanceEmpCode = annualAttendanceEmpCode;
	}
	public String getAnnualAttendanceEmpToken() {
		return annualAttendanceEmpToken;
	}
	public void setAnnualAttendanceEmpToken(String annualAttendanceEmpToken) {
		this.annualAttendanceEmpToken = annualAttendanceEmpToken;
	}
	public String getAnnualAttendanceEmpName() {
		return annualAttendanceEmpName;
	}
	public void setAnnualAttendanceEmpName(String annualAttendanceEmpName) {
		this.annualAttendanceEmpName = annualAttendanceEmpName;
	}
	public String getHolidayCurrentYear() {
		return holidayCurrentYear;
	}
	public void setHolidayCurrentYear(String holidayCurrentYear) {
		this.holidayCurrentYear = holidayCurrentYear;
	}
	public LinkedHashMap<String, String> getHolidayLocationNameList() {
		return holidayLocationNameList;
	}
	public void setHolidayLocationNameList(
			LinkedHashMap<String, String> holidayLocationNameList) {
		this.holidayLocationNameList = holidayLocationNameList;
	}
	public String getCheckItemCount() {
		return checkItemCount;
	}
	public void setCheckItemCount(String checkItemCount) {
		this.checkItemCount = checkItemCount;
	}
	public String getHiddenisClickOn() {
		return hiddenisClickOn;
	}
	public void setHiddenisClickOn(String hiddenisClickOn) {
		this.hiddenisClickOn = hiddenisClickOn;
	}
	public String getHiddensortOption() {
		return hiddensortOption;
	}
	public void setHiddensortOption(String hiddensortOption) {
		this.hiddensortOption = hiddensortOption;
	}
	public String getDynamicStatus() {
		return dynamicStatus;
	}
	public void setDynamicStatus(String dynamicStatus) {
		this.dynamicStatus = dynamicStatus;
	}
	/**
	 * @return the attendanceFromyearMap
	 */
	public TreeMap getAttendanceFromyearMap() {
		return attendanceFromyearMap;
	}
	public LinkedHashMap getYearMap() {
		return yearMap;
	}
	public void setYearMap(LinkedHashMap yearMap) {
		this.yearMap = yearMap;
	}
	/**
	 * @param attendanceFromyearMap the attendanceFromyearMap to set
	 */
	public void setAttendanceFromyearMap(TreeMap attendanceFromyearMap) {
		this.attendanceFromyearMap = attendanceFromyearMap;
	}
	/**
	 * @return the attendanceToyearMap
	 */
	public TreeMap getAttendanceToyearMap() {
		return attendanceToyearMap;
	}
	/**
	 * @param attendanceToyearMap the attendanceToyearMap to set
	 */
	public void setAttendanceToyearMap(TreeMap attendanceToyearMap) {
		this.attendanceToyearMap = attendanceToyearMap;
	}
	/**
	 * @return the holidayMap
	 */
	public LinkedHashMap getHolidayMap() {
		return holidayMap;
	}
	/**
	 * @param holidayMap the holidayMap to set
	 */
	public void setHolidayMap(LinkedHashMap holidayMap) {
		this.holidayMap = holidayMap;
	}
	/**
	 * @param leaveYearMap the leaveYearMap to set
	 */
	public void setLeaveYearMap(LinkedHashMap leaveYearMap) {
		this.leaveYearMap = leaveYearMap;
	}
	/**
	 * @return the leaveYearMap
	 */
	public LinkedHashMap getLeaveYearMap() {
		return leaveYearMap;
	}
	public String getShowalltimecard() {
		return showalltimecard;
	}
	public void setShowalltimecard(String showalltimecard) {
		this.showalltimecard = showalltimecard;
	}
	public String getPresenttimecard() {
		return presenttimecard;
	}
	public void setPresenttimecard(String presenttimecard) {
		this.presenttimecard = presenttimecard;
	}
	public String getLeavetimecard() {
		return leavetimecard;
	}
	public void setLeavetimecard(String leavetimecard) {
		this.leavetimecard = leavetimecard;
	}
	public String getLatetimecard() {
		return latetimecard;
	}
	public void setLatetimecard(String latetimecard) {
		this.latetimecard = latetimecard;
	}
	public String getAbsenttimecard() {
		return absenttimecard;
	}
	public void setAbsenttimecard(String absenttimecard) {
		this.absenttimecard = absenttimecard;
	}
	public String getRegularizedtimecard() {
		return regularizedtimecard;
	}
	public void setRegularizedtimecard(String regularizedtimecard) {
		this.regularizedtimecard = regularizedtimecard;
	}
	public String getApprovedRegularization() {
		return approvedRegularization;
	}
	public void setApprovedRegularization(String approvedRegularization) {
		this.approvedRegularization = approvedRegularization;
	}
	public String getRejectedRegularization() {
		return rejectedRegularization;
	}
	public void setRejectedRegularization(String rejectedRegularization) {
		this.rejectedRegularization = rejectedRegularization;
	}
	public String getAppliedRegularization() {
		return appliedRegularization;
	}
	public void setAppliedRegularization(String appliedRegularization) {
		this.appliedRegularization = appliedRegularization;
	}
	public String getShowallRegularization() {
		return showallRegularization;
	}
	public void setShowallRegularization(String showallRegularization) {
		this.showallRegularization = showallRegularization;
	}
	public String getApprovedLeave() {
		return approvedLeave;
	}
	public void setApprovedLeave(String approvedLeave) {
		this.approvedLeave = approvedLeave;
	}
	public String getAppliedLeave() {
		return appliedLeave;
	}
	public void setAppliedLeave(String appliedLeave) {
		this.appliedLeave = appliedLeave;
	}
	public String getRejectedLeave() {
		return rejectedLeave;
	}
	public void setRejectedLeave(String rejectedLeave) {
		this.rejectedLeave = rejectedLeave;
	}
	public String getCancelledLeave() {
		return cancelledLeave;
	}
	public void setCancelledLeave(String cancelledLeave) {
		this.cancelledLeave = cancelledLeave;
	}
	public String getShowallLeave() {
		return showallLeave;
	}
	public void setShowallLeave(String showallLeave) {
		this.showallLeave = showallLeave;
	}
	public String getTraveltimecard() {
		return traveltimecard;
	}
	public void setTraveltimecard(String traveltimecard) {
		this.traveltimecard = traveltimecard;
	}
	public String getHalfDaytimecard() {
		return halfDaytimecard;
	}
	public void setHalfDaytimecard(String halfDaytimecard) {
		this.halfDaytimecard = halfDaytimecard;
	}
	public String getEmpCodeAttReg() {
		return empCodeAttReg;
	}
	public void setEmpCodeAttReg(String empCodeAttReg) {
		this.empCodeAttReg = empCodeAttReg;
	}
	public String getEmpTokenAttReg() {
		return empTokenAttReg;
	}
	public void setEmpTokenAttReg(String empTokenAttReg) {
		this.empTokenAttReg = empTokenAttReg;
	}
	public String getEmpNameAttReg() {
		return empNameAttReg;
	}
	public void setEmpNameAttReg(String empNameAttReg) {
		this.empNameAttReg = empNameAttReg;
	}
	public String getShowPageFlag() {
		return showPageFlag;
	}
	public void setShowPageFlag(String showPageFlag) {
		this.showPageFlag = showPageFlag;
	}
	public String getMyTeamFlag() {
		return myTeamFlag;
	}
	public void setMyTeamFlag(String myTeamFlag) {
		this.myTeamFlag = myTeamFlag;
	}
	public String getShowMyCardFlag() {
		return showMyCardFlag;
	}
	public void setShowMyCardFlag(String showMyCardFlag) {
		this.showMyCardFlag = showMyCardFlag;
	}
	public String getNotLoginEmp() {
		return notLoginEmp;
	}
	public void setNotLoginEmp(String notLoginEmp) {
		this.notLoginEmp = notLoginEmp;
	}
	public String getMyTeamLeaveFlag() {
		return myTeamLeaveFlag;
	}
	public void setMyTeamLeaveFlag(String myTeamLeaveFlag) {
		this.myTeamLeaveFlag = myTeamLeaveFlag;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public boolean isLoginAttendanceFlg() {
		return loginAttendanceFlg;
	}
	public void setLoginAttendanceFlg(boolean loginAttendanceFlg) {
		this.loginAttendanceFlg = loginAttendanceFlg;
	}

	public String getFromLeaveDate() {
		return fromLeaveDate;
	}

	public void setFromLeaveDate(String fromLeaveDate) {
		this.fromLeaveDate = fromLeaveDate;
	}

	public String getToLeaveDate() {
		return toLeaveDate;
	}

	public void setToLeaveDate(String toLeaveDate) {
		this.toLeaveDate = toLeaveDate;
	}
	public boolean isAttendance() {
		return isAttendance;
	}
	public void setAttendance(boolean isAttendance) {
		this.isAttendance = isAttendance;
	}
}
