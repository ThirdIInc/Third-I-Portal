package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

public class ApprFormInstuction extends BeanBase {
	
	private String source="";
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
	private String QuesWtDisplayFlag;
	
	private String empId;
	private String empName;
	private String empDesgName;
	private String templateCode;
	
	private String apprInstr;
	
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesgName() {
		return empDesgName;
	}
	public void setEmpDesgName(String empDesgName) {
		this.empDesgName = empDesgName;
	}
	public String getApprInstr() {
		return apprInstr;
	}
	public void setApprInstr(String apprInstr) {
		this.apprInstr = apprInstr;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
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
	public String getQuesWtDisplayFlag() {
		return QuesWtDisplayFlag;
	}
	public void setQuesWtDisplayFlag(String quesWtDisplayFlag) {
		QuesWtDisplayFlag = quesWtDisplayFlag;
	}
	public String getPhaseLockFlag() {
		return phaseLockFlag;
	}
	public void setPhaseLockFlag(String phaseLockFlag) {
		this.phaseLockFlag = phaseLockFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	

}
