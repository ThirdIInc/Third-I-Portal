package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author Dhanashree
 *
 */
public class ProjectMasterBean extends BeanBase {


	
	/** 
	 * for Project list
	 */
	private String ittProjectName="";
	private String ittProjectOwner="";
	private String ittProjectDescription="";
	private String ittSrN0="";
	
	ArrayList projectMasterItt=null ;
	/*
	 * for Project Master 
	 * 
	 */
	private String projectId="";
	private String projectName="";
	private String projectOwner="";
	private String projectDescription="";
	private String empId="";
	private String myPage="";
	
	private String ittProjectCode="";
	
	public String getIttProjectCode() {
		return ittProjectCode;
	}
	public void setIttProjectCode(String ittProjectCode) {
		this.ittProjectCode = ittProjectCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getIttProjectName() {
		return ittProjectName;
	}
	public void setIttProjectName(String ittProjectName) {
		this.ittProjectName = ittProjectName;
	}
	public String getIttProjectOwner() {
		return ittProjectOwner;
	}
	public void setIttProjectOwner(String ittProjectOwner) {
		this.ittProjectOwner = ittProjectOwner;
	}
	public String getIttProjectDescription() {
		return ittProjectDescription;
	}
	public void setIttProjectDescription(String ittProjectDescription) {
		this.ittProjectDescription = ittProjectDescription;
	}
	public ArrayList getProjectMasterItt() {
		return projectMasterItt;
	}
	public void setProjectMasterItt(ArrayList projectMasterItt) {
		this.projectMasterItt = projectMasterItt;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}	
	
}
