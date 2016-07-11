package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormGeneralInfo extends BeanBase {
	
	private String source="";
	private String sourceFormType="";
	private String apprId;
	private String apprCode;
	private String apprStartDate;
	private String apprEndDate;
	private String apprValidTillDate;
	private String detailFLag="false";//to display for approval check
	private String phaseForwardFlag="false";//phase forwarded so display in readonly
	
	private String phaseCode;
	private String phaseName;
	private String phaseStartDate;
	private String phaseEndDate;
	private String phaseLockFlag;
	private String quesWtDisplayFlag;
	
	private String empId;
	private String empCode;
	private String empName;
	private String empBrnName;
	private String empDeptName;
	private String empDesgName;
	private String empReportingTo;
	private String empDoj;
	private String apprDate;
	
	private String appraiserName;
	private String appraiserDesgName;
	private String appraiserPhaseName;
	private String appraiserLevel;
	private ArrayList appraiserList;
	
	private String instrVisibilty;
	private String templateCode;
	private String ratingDefined="false";
	
	private String phaseOrder="";
	
	
	public void setPhaseOrder(String phaseOrder) {
		this.phaseOrder = phaseOrder;
	}
	public String getPhaseOrder() {
		return phaseOrder;
	}
	
	
	
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public ArrayList getAppraiserList() {
		return appraiserList;
	}
	public void setAppraiserList(ArrayList appraiserList) {
		this.appraiserList = appraiserList;
	}
	public String getAppraiserName() {
		return appraiserName;
	}
	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}
	public String getAppraiserDesgName() {
		return appraiserDesgName;
	}
	public void setAppraiserDesgName(String appraiserDesgName) {
		this.appraiserDesgName = appraiserDesgName;
	}
	public String getAppraiserPhaseName() {
		return appraiserPhaseName;
	}
	public void setAppraiserPhaseName(String appraiserPhaseName) {
		this.appraiserPhaseName = appraiserPhaseName;
	}
	public String getEmpBrnName() {
		return empBrnName;
	}
	public void setEmpBrnName(String empBrnName) {
		this.empBrnName = empBrnName;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getEmpDesgName() {
		return empDesgName;
	}
	public void setEmpDesgName(String empDesgName) {
		this.empDesgName = empDesgName;
	}
	public String getEmpReportingTo() {
		return empReportingTo;
	}
	public void setEmpReportingTo(String empReportingTo) {
		this.empReportingTo = empReportingTo;
	}
	public String getEmpDoj() {
		return empDoj;
	}
	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
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
	public String getApprStartDate() {
		return apprStartDate;
	}
	public void setApprStartDate(String apprStartDate) {
		this.apprStartDate = apprStartDate;
	}
	public String getApprEndDate() {
		return apprEndDate;
	}
	public void setApprEndDate(String apprEndDate) {
		this.apprEndDate = apprEndDate;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getPhaseStartDate() {
		return phaseStartDate;
	}
	public void setPhaseStartDate(String phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}
	public String getPhaseEndDate() {
		return phaseEndDate;
	}
	public void setPhaseEndDate(String phaseEndDate) {
		this.phaseEndDate = phaseEndDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPhaseLockFlag() {
		return phaseLockFlag;
	}
	public void setPhaseLockFlag(String phaseLockFlag) {
		this.phaseLockFlag = phaseLockFlag;
	}
	public String getAppraiserLevel() {
		return appraiserLevel;
	}
	public void setAppraiserLevel(String appraiserLevel) {
		this.appraiserLevel = appraiserLevel;
	}
	/*public String getRetrieveFlag() {
		return retrieveFlag;
	}
	public void setRetrieveFlag(String retrieveFlag) {
		this.retrieveFlag = retrieveFlag;
	}*/
	public String getInstrVisibilty() {
		return instrVisibilty;
	}
	public void setInstrVisibilty(String instrVisibilty) {
		this.instrVisibilty = instrVisibilty;
	}
	public String getDetailFLag() {
		return detailFLag;
	}
	public void setDetailFLag(String detailFLag) {
		this.detailFLag = detailFLag;
	}
	public String getPhaseForwardFlag() {
		return phaseForwardFlag;
	}
	public void setPhaseForwardFlag(String phaseForwardFlag) {
		this.phaseForwardFlag = phaseForwardFlag;
	}
	public String getApprValidTillDate() {
		return apprValidTillDate;
	}
	public void setApprValidTillDate(String apprValidTillDate) {
		this.apprValidTillDate = apprValidTillDate;
	}
	public String getRatingDefined() {
		return ratingDefined;
	}
	public void setRatingDefined(String ratingDefined) {
		this.ratingDefined = ratingDefined;
	}
	public String getQuesWtDisplayFlag() {
		return quesWtDisplayFlag;
	}
	public void setQuesWtDisplayFlag(String quesWtDisplayFlag) {
		this.quesWtDisplayFlag = quesWtDisplayFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceFormType() {
		return sourceFormType;
	}
	public void setSourceFormType(String sourceFormType) {
		this.sourceFormType = sourceFormType;
	}
	
	
	
	
}
