package org.paradyne.bean.common;

import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class TaskReport extends BeanBase {

	
	private String empCode ="";
	
	private String empToken="";
	private String empName ="";
	
	private String appFromDate ="";
	
	private String appToDate ="";
	
	private String  reportType="";
	
	private String  taskType="";
	private String  taskProject="";
	
	TreeMap tmap;
	TreeMap tmap1;

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public TreeMap getTmap1() {
		return tmap1;
	}

	public void setTmap1(TreeMap tmap1) {
		this.tmap1 = tmap1;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAppFromDate() {
		return appFromDate;
	}

	public void setAppFromDate(String appFromDate) {
		this.appFromDate = appFromDate;
	}

	public String getAppToDate() {
		return appToDate;
	}

	public void setAppToDate(String appToDate) {
		this.appToDate = appToDate;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskProject() {
		return taskProject;
	}

	public void setTaskProject(String taskProject) {
		this.taskProject = taskProject;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
}
