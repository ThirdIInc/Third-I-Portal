package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1383
 *
 */
public class EmailPayrollReport extends BeanBase {
	
	/*Mail report variables*/
	
	private String attachmentPath="";
	private String mailToEmployeeId="";
	private String mailToEmployeeToken="";
	private String mailToEmployeeName="";
	private String mailToEmployeeEmail="";
	
	private String ccEmailIds="";
	private String emailReport="";
	private String subject="";
	private String description="";
	private String returnMessage="";
	private String actionBack="";
	private String fileName="";
	private String htmlcode="";
	private String sentFlag="";
	
	
	/* List of mail to employees*/
	private String mailToListEmpId="";
	private String mailToListEmpToken="";
	private String mailToListEmpName="";
	private String mailToListEmpEmail="";
	
	public String getMailToEmployeeId() {
		return mailToEmployeeId;
	}
	public void setMailToEmployeeId(String mailToEmployeeId) {
		this.mailToEmployeeId = mailToEmployeeId;
	}
	public String getMailToEmployeeToken() {
		return mailToEmployeeToken;
	}
	public void setMailToEmployeeToken(String mailToEmployeeToken) {
		this.mailToEmployeeToken = mailToEmployeeToken;
	}
	public String getMailToEmployeeName() {
		return mailToEmployeeName;
	}
	public void setMailToEmployeeName(String mailToEmployeeName) {
		this.mailToEmployeeName = mailToEmployeeName;
	}
	public String getMailToEmployeeEmail() {
		return mailToEmployeeEmail;
	}
	public void setMailToEmployeeEmail(String mailToEmployeeEmail) {
		this.mailToEmployeeEmail = mailToEmployeeEmail;
	}
	public String getCcEmailIds() {
		return ccEmailIds;
	}
	public void setCcEmailIds(String ccEmailIds) {
		this.ccEmailIds = ccEmailIds;
	}
	public String getEmailReport() {
		return emailReport;
	}
	public void setEmailReport(String emailReport) {
		this.emailReport = emailReport;
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
	public String getMailToListEmpId() {
		return mailToListEmpId;
	}
	public void setMailToListEmpId(String mailToListEmpId) {
		this.mailToListEmpId = mailToListEmpId;
	}
	public String getMailToListEmpToken() {
		return mailToListEmpToken;
	}
	public void setMailToListEmpToken(String mailToListEmpToken) {
		this.mailToListEmpToken = mailToListEmpToken;
	}
	public String getMailToListEmpName() {
		return mailToListEmpName;
	}
	public void setMailToListEmpName(String mailToListEmpName) {
		this.mailToListEmpName = mailToListEmpName;
	}
	public String getMailToListEmpEmail() {
		return mailToListEmpEmail;
	}
	public void setMailToListEmpEmail(String mailToListEmpEmail) {
		this.mailToListEmpEmail = mailToListEmpEmail;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getActionBack() {
		return actionBack;
	}
	public void setActionBack(String actionBack) {
		this.actionBack = actionBack;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getHtmlcode() {
		return htmlcode;
	}
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	public String getSentFlag() {
		return sentFlag;
	}
	public void setSentFlag(String sentFlag) {
		this.sentFlag = sentFlag;
	}
	
}
