package org.paradyne.bean.PAS;

import java.util.List;

import org.paradyne.lib.BeanBase;

public class TemplateDefination extends BeanBase {
	
	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;
	
	
	private String templateCode;
	private String templateName;
	private String saveFlag="false";
	private String calupdateFlag="false";
	private List temList;
	public List getTemList() {
		return temList;
	}
	public void setTemList(List list) {
		this.temList = list;
	}
	public String getCalupdateFlag() {
		return calupdateFlag;
	}
	public void setCalupdateFlag(String calupdateFlag) {
		this.calupdateFlag = calupdateFlag;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

}
