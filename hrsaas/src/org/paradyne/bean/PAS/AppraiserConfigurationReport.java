package org.paradyne.bean.PAS;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class AppraiserConfigurationReport extends BeanBase {

	private String sAppId = null;
	private String sAppStartDate = null;
	private String sAppEndDate = null;
	private String sAppCode = null;
	private String sGroup = null;
	private String sGroupId = null;
	private String appraiseeCode = null;
	private String appraiseeName = null;
	private String appraiseeId = null;
	private String appraiserCode = null;
	private String appraiserName = null;
	private String appraiserId = null;
	private String report;
	private String reportAction;
	private String PhaseId = null;
	private String phaseName = null;
	private LinkedHashMap phaseList = null;

	public String getSAppId() {
		return sAppId;
	}

	public void setSAppId(String appId) {
		sAppId = appId;
	}

	public String getSAppStartDate() {
		return sAppStartDate;
	}

	public void setSAppStartDate(String appStartDate) {
		sAppStartDate = appStartDate;
	}

	public String getSAppEndDate() {
		return sAppEndDate;
	}

	public void setSAppEndDate(String appEndDate) {
		sAppEndDate = appEndDate;
	}

	public String getSAppCode() {
		return sAppCode;
	}

	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}

	public String getSGroup() {
		return sGroup;
	}

	public void setSGroup(String group) {
		sGroup = group;
	}

	public String getAppraiseeCode() {
		return appraiseeCode;
	}

	public void setAppraiseeCode(String appraiseeCode) {
		this.appraiseeCode = appraiseeCode;
	}

	public String getAppraiseeName() {
		return appraiseeName;
	}

	public void setAppraiseeName(String appraiseeName) {
		this.appraiseeName = appraiseeName;
	}

	public String getAppraiserCode() {
		return appraiserCode;
	}

	public void setAppraiserCode(String appraiserCode) {
		this.appraiserCode = appraiserCode;
	}

	public String getAppraiserName() {
		return appraiserName;
	}

	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public LinkedHashMap getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(LinkedHashMap phaseList) {
		this.phaseList = phaseList;
	}

	public String getSGroupId() {
		return sGroupId;
	}

	public void setSGroupId(String groupId) {
		sGroupId = groupId;
	}

	public String getAppraiseeId() {
		return appraiseeId;
	}

	public void setAppraiseeId(String appraiseeId) {
		this.appraiseeId = appraiseeId;
	}

	public String getAppraiserId() {
		return appraiserId;
	}

	public void setAppraiserId(String appraiserId) {
		this.appraiserId = appraiserId;
	}

	public String getPhaseId() {
		return PhaseId;
	}

	public void setPhaseId(String phaseId) {
		PhaseId = phaseId;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getReportAction() {
		return reportAction;
	}

	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
}
