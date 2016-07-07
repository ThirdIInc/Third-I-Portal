package org.paradyne.bean.probation;

import org.paradyne.lib.BeanBase;
import java.util.*;

public class ProbationEvaluation extends BeanBase {
	private String selectemployeetoken="";
	private String selectemployeeName="";
	private String empToken="";
	private String empName="";
	private String dateOfJoining="";
	private String employeeId="";
	private String branch="";
	private String myPage="";
	private String ittprobationCode="";
	private String ittprobationStatus="";
	private String noData="false";
	private ArrayList list;
	private String show;
	private String dueDate="";
	private String designation="";
	private String department="";
	private String hiddenempcode="";
	private String hiddenprobationcode="";
	private String duedateconf="";
	private String evaluationDate="";
	TreeMap tmap;
	private String displayFlag ="false";
	private String reportFlag="true";
	ArrayList tableList = null;
	private String backBtnFlag="false";
	private String comment;
	private String probationevalCode="";
	private String probationevalName="";
	private String QuesDtlflag = "false";
	private String evalcode="";
	private String rating="";
	private String ittrevalCode="";
	private String extendedProbationDays="";
	private String recommendation="";
	private String evalFlag="false";
	private String evalstatus="";
	private String viewFlag="false";
	private String viewReportFlag="false";
	private String terminationDate="";
	private String confirmDate="";
	
	
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getViewReportFlag() {
		return viewReportFlag;
	}
	public void setViewReportFlag(String viewReportFlag) {
		this.viewReportFlag = viewReportFlag;
	}
	public String getEvalstatus() {
		return evalstatus;
	}
	public void setEvalstatus(String evalstatus) {
		this.evalstatus = evalstatus;
	}
	public String getEvalFlag() {
		return evalFlag;
	}
	public void setEvalFlag(String evalFlag) {
		this.evalFlag = evalFlag;
	}
	public String getExtendedProbationDays() {
		return extendedProbationDays;
	}
	public void setExtendedProbationDays(String extendedProbationDays) {
		this.extendedProbationDays = extendedProbationDays;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getIttrevalCode() {
		return ittrevalCode;
	}
	public void setIttrevalCode(String ittrevalCode) {
		this.ittrevalCode = ittrevalCode;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getEvalcode() {
		return evalcode;
	}
	public void setEvalcode(String evalcode) {
		this.evalcode = evalcode;
	}
	public String getQuesDtlflag() {
		return QuesDtlflag;
	}
	public void setQuesDtlflag(String quesDtlflag) {
		QuesDtlflag = quesDtlflag;
	}
	public String getProbationevalCode() {
		return probationevalCode;
	}
	public void setProbationevalCode(String probationevalCode) {
		this.probationevalCode = probationevalCode;
	}
	public String getProbationevalName() {
		return probationevalName;
	}
	public void setProbationevalName(String probationevalName) {
		this.probationevalName = probationevalName;
	}
	public TreeMap getTmap() {
		return tmap;
	}
	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}
	public ArrayList getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEvaluationDate() {
		return evaluationDate;
	}
	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
	}
	public String getDuedateconf() {
		return duedateconf;
	}
	public void setDuedateconf(String duedateconf) {
		this.duedateconf = duedateconf;
	}
	public String getHiddenprobationcode() {
		return hiddenprobationcode;
	}
	public void setHiddenprobationcode(String hiddenprobationcode) {
		this.hiddenprobationcode = hiddenprobationcode;
	}
	public String getHiddenempcode() {
		return hiddenempcode;
	}
	public void setHiddenempcode(String hiddenempcode) {
		this.hiddenempcode = hiddenempcode;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
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
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getIttprobationCode() {
		return ittprobationCode;
	}
	public void setIttprobationCode(String ittprobationCode) {
		this.ittprobationCode = ittprobationCode;
	}
	public String getIttprobationStatus() {
		return ittprobationStatus;
	}
	public void setIttprobationStatus(String ittprobationStatus) {
		this.ittprobationStatus = ittprobationStatus;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getBackBtnFlag() {
		return backBtnFlag;
	}
	public void setBackBtnFlag(String backBtnFlag) {
		this.backBtnFlag = backBtnFlag;
	}
	public String getReportFlag() {
		return reportFlag;
	}
	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}
	public String getSelectemployeetoken() {
		return selectemployeetoken;
	}
	public void setSelectemployeetoken(String selectemployeetoken) {
		this.selectemployeetoken = selectemployeetoken;
	}
	public String getSelectemployeeName() {
		return selectemployeeName;
	}
	public void setSelectemployeeName(String selectemployeeName) {
		this.selectemployeeName = selectemployeeName;
	}
	 
 

}
