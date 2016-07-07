package org.paradyne.bean.common;
 
import org.paradyne.lib.BeanBase;

public class EmployeePortal extends BeanBase {

	
	private String pollCode ="";
	private String quesName ="";
	private String optionValue="";
	private String subject ="";
	private String description ="";
	private String hideIdentity ="";
	private String hdMenuCode="";
	private String myPage ="";
	private boolean pageFlag=false;
	private String myPageMenu="";
	private boolean myMenuPageFlag =false;
	private boolean birthdayPageFlag =false;
	private boolean newJoineePageFlag =false;
	private String myPageNewJoinee ="";
	private boolean ismenuDataAvailable=false;
	private boolean empDataAvailable=false;
	 private String nextReporting ="";
	 //Added by Janisha for resigned employee list 
 	private boolean resignPageFlag =false;
	private String myPageResignDisplay ="";
	private String monthList="";

	public String getNextReporting() {
		return nextReporting;
	}
	public void setNextReporting(String nextReporting) {
		this.nextReporting = nextReporting;
	}
	public boolean isMyMenuPageFlag() {
		return myMenuPageFlag;
	}
	public void setMyMenuPageFlag(boolean myMenuPageFlag) {
		this.myMenuPageFlag = myMenuPageFlag;
	}
	public String getMyPageMenu() {
		return myPageMenu;
	}
	public void setMyPageMenu(String myPageMenu) {
		this.myPageMenu = myPageMenu;
	}
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHdMenuCode() {
		return hdMenuCode;
	}
	public void setHdMenuCode(String hdMenuCode) {
		this.hdMenuCode = hdMenuCode;
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
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
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
	/**
	 * @return the resignPageFlag
	 */
	public boolean isResignPageFlag() {
		return resignPageFlag;
	}
	/**
	 * @param resignPageFlag the resignPageFlag to set
	 */
	public void setResignPageFlag(boolean resignPageFlag) {
		this.resignPageFlag = resignPageFlag;
	}
	/**
	 * @return the myPageResignDisplay
	 */
	public String getMyPageResignDisplay() {
		return myPageResignDisplay;
	}
	/**
	 * @param myPageResignDisplay the myPageResignDisplay to set
	 */
	public void setMyPageResignDisplay(String myPageResignDisplay) {
		this.myPageResignDisplay = myPageResignDisplay;
	}
	/**
	 * @return the monthList
	 */
	public String getMonthList() {
		return monthList;
	}
	/**
	 * @param monthList the monthList to set
	 */
	public void setMonthList(String monthList) {
		this.monthList = monthList;
	}
 
	
	
}
