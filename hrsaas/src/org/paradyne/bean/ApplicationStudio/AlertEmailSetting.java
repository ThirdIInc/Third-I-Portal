package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

public class AlertEmailSetting extends BeanBase{
	 
	private String modName="";
	private String modCode="";
	private String alertType="";
	private String noOfDays="";
	private String applicantToapprover="";
	private String approverToapprover="";
	private String approverToapplicant="";

	 	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getModName() {
		return modName;
	}
	public String getApplicantToapprover() {
		return applicantToapprover;
	}
	public void setApplicantToapprover(String applicantToapprover) {
		this.applicantToapprover = applicantToapprover;
	}
	public String getApproverToapprover() {
		return approverToapprover;
	}
	public void setApproverToapprover(String approverToapprover) {
		this.approverToapprover = approverToapprover;
	}
	
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getModCode() {
		return modCode;
	}
	public void setModCode(String modCode) {
		this.modCode = modCode;
	}
	public String getApproverToapplicant() {
		return approverToapplicant;
	}
	public void setApproverToapplicant(String approverToapplicant) {
		this.approverToapplicant = approverToapplicant;
	}

}
