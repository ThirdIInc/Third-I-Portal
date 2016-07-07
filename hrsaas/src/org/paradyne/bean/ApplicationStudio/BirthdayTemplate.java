package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

public class BirthdayTemplate extends BeanBase {
	private String htmlcode = "";
	private String tempCode = "";
	private String tempContent = "";
	private String field = "";
	private String TemplateId = ""; 
	private String tempName = "";
	public String getHtmlcode() {
		return htmlcode;
	}
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	public String getTempCode() {
		return tempCode;
	}
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	public String getTempContent() {
		return tempContent;
	}
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTemplateId() {
		return TemplateId;
	}
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
}
