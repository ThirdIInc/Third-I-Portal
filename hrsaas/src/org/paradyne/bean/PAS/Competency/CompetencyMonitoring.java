package org.paradyne.bean.PAS.Competency;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CompetencyMonitoring extends BeanBase {
	
	private String compName="";
	private String compFrmDate="";
	private String compToDate="";
	private String compId="";
	private String assessmentDate="";
	private String assessmentId="";
	private String compEmpName="";
	private String compEmpToken="";
	private String compEmpId="";
	private ArrayList list;
	private String sendbackEmpToken="";
	private String sendbackEmpName="";
	private String sendbackEmpId="";
	public String getSendbackEmpToken() {
		return sendbackEmpToken;
	}
	public void setSendbackEmpToken(String sendbackEmpToken) {
		this.sendbackEmpToken = sendbackEmpToken;
	}
	public String getSendbackEmpName() {
		return sendbackEmpName;
	}
	public void setSendbackEmpName(String sendbackEmpName) {
		this.sendbackEmpName = sendbackEmpName;
	}
	public String getSendbackEmpId() {
		return sendbackEmpId;
	}
	public void setSendbackEmpId(String sendbackEmpId) {
		this.sendbackEmpId = sendbackEmpId;
	}
	public String getCompEmpName() {
		return compEmpName;
	}
	public void setCompEmpName(String compEmpName) {
		this.compEmpName = compEmpName;
	}
	public String getCompEmpToken() {
		return compEmpToken;
	}
	public void setCompEmpToken(String compEmpToken) {
		this.compEmpToken = compEmpToken;
	}
	public String getCompEmpId() {
		return compEmpId;
	}
	public void setCompEmpId(String compEmpId) {
		this.compEmpId = compEmpId;
	}
	public String getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(String assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompFrmDate() {
		return compFrmDate;
	}
	public void setCompFrmDate(String compFrmDate) {
		this.compFrmDate = compFrmDate;
	}
	public String getCompToDate() {
		return compToDate;
	}
	public void setCompToDate(String compToDate) {
		this.compToDate = compToDate;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}

}
