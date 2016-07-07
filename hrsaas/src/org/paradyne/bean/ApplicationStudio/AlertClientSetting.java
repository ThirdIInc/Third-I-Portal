/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0491
 *
 */
public class AlertClientSetting extends BeanBase {

	private String jobQueryType="";
	private String hiddenJobCode ="";
	private String moduleName ="";
	private ArrayList<Object>list =null;
	private String jobDuration ="";
	private String jobDayOfWeek ="";
	private String jobDayOfMonth ="";
	private String jobStartTime ="";
	private String emailCheck ="";
	private String alertCheck ="";
	private String hiddenEdit ="";
	private String employeeToken ="";
	private String employeeName ="";
	private String employeeCode ="";
	private String addFlag="false";
	private String showFlag="false" ;
	private String srNo ="";
	private String empToken =""; 
	private String employeeId="";
	private String empName ="";
	private String startShedulerFlag ="false" ;
	
	private String stopShedulerFlag ="false" ;
	private String subject="";
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStartShedulerFlag() {
		return startShedulerFlag;
	}
	public void setStartShedulerFlag(String startShedulerFlag) {
		this.startShedulerFlag = startShedulerFlag;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getJobDuration() {
		return jobDuration;
	}
	public void setJobDuration(String jobDuration) {
		this.jobDuration = jobDuration;
	}
	public String getJobDayOfWeek() {
		return jobDayOfWeek;
	}
	public void setJobDayOfWeek(String jobDayOfWeek) {
		this.jobDayOfWeek = jobDayOfWeek;
	}
	public String getJobDayOfMonth() {
		return jobDayOfMonth;
	}
	public void setJobDayOfMonth(String jobDayOfMonth) {
		this.jobDayOfMonth = jobDayOfMonth;
	}
	public String getJobStartTime() {
		return jobStartTime;
	}
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
	public String getEmailCheck() {
		return emailCheck;
	}
	public void setEmailCheck(String emailCheck) {
		this.emailCheck = emailCheck;
	}
	public String getAlertCheck() {
		return alertCheck;
	}
	public void setAlertCheck(String alertCheck) {
		this.alertCheck = alertCheck;
	}
	public String getHiddenJobCode() {
		return hiddenJobCode;
	}
	public void setHiddenJobCode(String hiddenJobCode) {
		this.hiddenJobCode = hiddenJobCode;
	}
	 
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public ArrayList<Object> getList() {
		return list;
	}
	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getJobQueryType() {
		return jobQueryType;
	}
	public void setJobQueryType(String jobQueryType) {
		this.jobQueryType = jobQueryType;
	}
	public String getStopShedulerFlag() {
		return stopShedulerFlag;
	}
	public void setStopShedulerFlag(String stopShedulerFlag) {
		this.stopShedulerFlag = stopShedulerFlag;
	}
	 
	
}
