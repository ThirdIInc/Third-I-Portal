/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0491
 * 
 */
public class AlertAdminSetting extends BeanBase {

	private String methodName="";
	private String moduleCode = "";
	private String moduleName = "";
	private String subject = "";

	private String emailCheck = "";
	private String alertCheck = "";
	private String query = "";
	private String querytype = "";
	private String alerttype = "";

	private String jobCode = "";
	
	private String templateName ="";
	private String templateCode ="";
	
	private String link  ="";
	private String linkParameter  ="";
	private String noOflinkParameter  ="";
	private String linkParameterValue="";

	public String getLinkParameterValue() {
		return linkParameterValue;
	}

	public void setLinkParameterValue(String linkParameterValue) {
		this.linkParameterValue = linkParameterValue;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkParameter() {
		return linkParameter;
	}

	public void setLinkParameter(String linkParameter) {
		this.linkParameter = linkParameter;
	}

	public String getNoOflinkParameter() {
		return noOflinkParameter;
	}

	public void setNoOflinkParameter(String noOflinkParameter) {
		this.noOflinkParameter = noOflinkParameter;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getAlerttype() {
		return alerttype;
	}

	public void setAlerttype(String alerttype) {
		this.alerttype = alerttype;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
