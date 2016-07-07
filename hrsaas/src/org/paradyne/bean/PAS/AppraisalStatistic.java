package org.paradyne.bean.PAS;
/*
 * @saipavan voleti
 * Date:20-06-2008
 */

import org.paradyne.lib.BeanBase;

public class AppraisalStatistic extends BeanBase 
{
 	
 private String apprCode;
 private String template;
 private String apprFrom;
 private String apprTo;
 private String apprId;
 private String templateId;
 private String appstat;
 private String report="";
 

 


public String getApprCode() {
	Object obj="34234234324";
	return apprCode;
}
public void setApprCode(String apprCode) {
	this.apprCode = apprCode;
}
public String getTemplate() {
	return template;
}
public void setTemplate(String template) {
	this.template = template;
}


public String getApprFrom() {
	return apprFrom;
}
public void setApprFrom(String apprFrom) {
	this.apprFrom = apprFrom;
}
public String getApprTo() {
	return apprTo;
}
public void setApprTo(String apprTo) {
	this.apprTo = apprTo;
}
public String getApprId() {
	return apprId;
}
public void setApprId(String apprId) {
	this.apprId = apprId;
}
public String getTemplateId() {
	return templateId;
}
public void setTemplateId(String templateId) {
	this.templateId = templateId;
}
public String getAppstat() {
	return appstat;
}
public void setAppstat(String appstat) {
	this.appstat = appstat;
}
public String getReport() {
	return report;
}
public void setReport(String report) {
	this.report = report;
}
 
 
}
