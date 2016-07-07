package org.paradyne.bean.common;

import org.paradyne.lib.BeanBase;

public class ApplCodeTemplate extends BeanBase{

	private String mainPageFlag="true";
	private String applicationType;
	private String templateName;
	private String hiddenTemplateName;
	private String templateId;
	private String autoIdFlag;
	private String resetType;
	private String autoGenIdType;
	private String textToAppend;
	private String seperatorValue;
	private String deleteTempl;
	private String autoIdDigits;
	private String applCodeType;
	private String templateQuery;
	
	public String getTemplateQuery() {
		return templateQuery;
	}

	public void setTemplateQuery(String templateQuery) {
		this.templateQuery = templateQuery;
	}

	public String getDeleteTempl() {
		return deleteTempl;
	}

	public void setDeleteTempl(String deleteTempl) {
		this.deleteTempl = deleteTempl;
	}

	public String getSeperatorValue() {
		return seperatorValue;
	}

	public void setSeperatorValue(String seperatorValue) {
		this.seperatorValue = seperatorValue;
	}

	

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	public String getAutoGenIdType() {
		return autoGenIdType;
	}

	public void setAutoGenIdType(String autoGenIdType) {
		this.autoGenIdType = autoGenIdType;
	}

	public String getTextToAppend() {
		return textToAppend;
	}

	public void setTextToAppend(String textToAppend) {
		this.textToAppend = textToAppend;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getHiddenTemplateName() {
		return hiddenTemplateName;
	}

	public void setHiddenTemplateName(String hiddenTemplateName) {
		this.hiddenTemplateName = hiddenTemplateName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getAutoIdFlag() {
		return autoIdFlag;
	}

	public void setAutoIdFlag(String autoIdFlag) {
		this.autoIdFlag = autoIdFlag;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getMainPageFlag() {
		return mainPageFlag;
	}

	public void setMainPageFlag(String mainPageFlag) {
		this.mainPageFlag = mainPageFlag;
	}

	public String getAutoIdDigits() {
		return autoIdDigits;
	}

	public void setAutoIdDigits(String autoIdDigits) {
		this.autoIdDigits = autoIdDigits;
	}

	public String getApplCodeType() {
		return applCodeType;
	}

	public void setApplCodeType(String applCodeType) {
		this.applCodeType = applCodeType;
	}
}
