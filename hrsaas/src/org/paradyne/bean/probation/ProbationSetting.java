package org.paradyne.bean.probation;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProbationSetting extends BeanBase {

	
	private String empTypeCode="";
	private String empType="";
	private String gradeId="";
	private String gradeName="";
	private String probationDays="";
	private String probationGradeDays="";
	private String probationApp="";
	ArrayList gradeList=null;
	public ArrayList getGradeList() {
		return gradeList;
	}
	public void setGradeList(ArrayList gradeList) {
		this.gradeList = gradeList;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getProbationGradeDays() {
		return probationGradeDays;
	}
	public void setProbationGradeDays(String probationGradeDays) {
		this.probationGradeDays = probationGradeDays;
	}
	public String getProbationDays() {
		return probationDays;
	}
	public void setProbationDays(String probationDays) {
		this.probationDays = probationDays;
	}
	public String getProbationApp() {
		return probationApp;
	}
	public void setProbationApp(String probationApp) {
		this.probationApp = probationApp;
	}
}
