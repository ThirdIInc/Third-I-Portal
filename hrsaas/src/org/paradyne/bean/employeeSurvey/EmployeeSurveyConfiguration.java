/**
 * 
 */
package org.paradyne.bean.employeeSurvey;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0491
 *
 */
public class EmployeeSurveyConfiguration extends BeanBase {

	
	private String surveyCode ="";
	private String surveyName ="";
	private String divCode =""; 
	private String divName=""; 
	private String deptCode ="";
	private String deptName =""; 
	private String branchCode =""; 
	private String branchName =""; 
	private String desgCode ="";
	private String desgName ="";
	
	private String employeeToken ="";
	private String employeeName ="";
	private String employeeCode ="";
	private String employeeId ="";
	
	private String srNo  ="";
	private String empToken  ="";
	private String empName  ="";
	
	
	private ArrayList  list = null ;
	private String hiddenEdit="";
	
	private String htmlcode="";
	
	private String  surveyMailSubject="";
	
	private String surveyNameItt  ="";
	private String hiddenAutoCodeItt  ="";
	private ArrayList tableList = null ;
	
	private String listFlag="false";
	private String noData="false";
	private String totalRecords ="";
	
	private String myPage;
	private String modeLength="false";
	private String modeLengthEmpFlag="false";
	private String hiddencode="";
	
	private String  showFlag="false";
	
	private String myPageEmp="";
	
	private String mailText="";
	
	public String getMyPageEmp() {
		return myPageEmp;
	}
	public void setMyPageEmp(String myPageEmp) {
		this.myPageEmp = myPageEmp;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSurveyCode() {
		return surveyCode;
	}
	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	 
	public String getSurveyNameItt() {
		return surveyNameItt;
	}
	public void setSurveyNameItt(String surveyNameItt) {
		this.surveyNameItt = surveyNameItt;
	}
	public String getHiddenAutoCodeItt() {
		return hiddenAutoCodeItt;
	}
	public void setHiddenAutoCodeItt(String hiddenAutoCodeItt) {
		this.hiddenAutoCodeItt = hiddenAutoCodeItt;
	}
	public ArrayList getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getModeLengthEmpFlag() {
		return modeLengthEmpFlag;
	}
	public void setModeLengthEmpFlag(String modeLengthEmpFlag) {
		this.modeLengthEmpFlag = modeLengthEmpFlag;
	}
	public String getMailText() {
		return mailText;
	}
	public void setMailText(String mailText) {
		this.mailText = mailText;
	}
	public String getHtmlcode() {
		return htmlcode;
	}
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	public String getSurveyMailSubject() {
		return surveyMailSubject;
	}
	public void setSurveyMailSubject(String surveyMailSubject) {
		this.surveyMailSubject = surveyMailSubject;
	}
	 
}
