package org.paradyne.bean.PAS;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AwardsRecognition extends BeanBase{

	private String apprId;
	private String apprCode;
	private String startDate;
	private String endDate;	
	private String templateCode;
	private String templateName;	
	private String phaseId;
	private String phase;
	
	private String rownum;
	private String hSectionId;	
	
	private String chkAwardAppl;
	private String chkNominate;
	private String chkReason;
	
	private String mode;	
	
	private String nominate;
	private String reason;
	
	private ArrayList phaseList;
	
	
	
	
	
	public String getChkNominate() {
		return chkNominate;
	}
	public void setChkNominate(String chkNominate) {
		this.chkNominate = chkNominate;
	}
	public String getChkReason() {
		return chkReason;
	}
	public void setChkReason(String chkReason) {
		this.chkReason = chkReason;
	}
	public String getNominate() {
		return nominate;
	}
	public void setNominate(String nominate) {
		this.nominate = nominate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public ArrayList getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
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
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getHSectionId() {
		return hSectionId;
	}
	public void setHSectionId(String sectionId) {
		hSectionId = sectionId;
	}
	public String getChkAwardAppl() {
		return chkAwardAppl;
	}
	public void setChkAwardAppl(String chkAwardAppl) {
		this.chkAwardAppl = chkAwardAppl;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
	
}
