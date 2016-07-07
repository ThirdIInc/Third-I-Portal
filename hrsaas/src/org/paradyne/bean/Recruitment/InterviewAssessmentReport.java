package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;

public class InterviewAssessmentReport extends BeanBase {

	
	private String reqName ="";
	private String reqCode ="";
	private String candCode ="";
	private String candName ="";
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getCandCode() {
		return candCode;
	}
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	}
