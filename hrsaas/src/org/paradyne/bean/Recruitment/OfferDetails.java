/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0540
 *
 */
public class OfferDetails extends BeanBase {
	private boolean addcandFlag = false;
	private boolean viewOfferFlag = false;
	private String buttonName;
	private String doubleClickViewModeFlag="false";
	
	private String offerCode;
	private String requisitionCode;
	private String requisitionName;
	private String position;
	private String positionCode;
	private String hiringManager;
	private String division;
	private String divisionCode;
	private String branch;
	private String branchCode;
	private String department;
	private String deptCode;
	
	private String candidateCode;
	private String candidateName;
	private String joiningDate;
	private String offerDate;
	private String currentCtc;
	private String negotiatedCtc;
	
	private String jobCode;
	private String jobDescription;
	private String rolesResponsibility;
	private String empTypeCode;
	private String empType;
	private String hiringMgrCode;
	private String hiringMgr;
	private String expJoiningDate;
	private String reportingToCode;
	private String reportingTo;
	private String reportingToAdmin;
	private String reportingToAdminCode;
	private String testReqCode;
	private String testRequirements;
	private String gradeCode;
	private String grade;
	private String templateCode;
	private String template;
	private String probation;
	private String months;
	private String authorityCode;
	private String signingAuthority;
	private String offerStatus;
	private String offeredCtc;
	private String designation;
	private String remarks;
	private String candConstraints;
	
	private String backgroundCheck;
	
	private String checklistCode;
	private String checklistName;
	
	private String frmName;
	private String frmId;
	private String salgrdId;
	private String salgrdName;
	private ArrayList salList;
	private String salCode;
	private String salHead;
	private String salPerdiocity;
	private String newAmt;
	private String proSalCode;
	private String grdFlag;
	private String frmFlag;
	private String viewFlagStruc = "true";
	private String grsAmt;
	private String emailtemplate;
	//private String emailtemplateCode;
	private String calflag;
	
	private String recruiterId = "";
	private String recruiterToken = "";
	private String recruiterName = "";
	private String hBranch;
	private String hDesg;
	
	//added 31-08-2010
	
	private ArrayList dbtList;
	private String dbtCode;
	private String dbtHead;
	private String dbtPerdiocity;
	private String newAmtDbt;
	private String proDbtCode;
	
	//Edited by Prashant Shinde
	private ArrayList keepInformedList = null;
	private String serialNo = "";
	private String keepInformedEmpName = "";
	private String keepInformedEmpId = "";
	
	private String employeeName = "";
	private String employeeId = "";
	private String employeeToken = "";
	
	
	private String checkRemove = "";
	private String srNo = "";

	private String dataPath = "";
	private String annextureFileName = "";
	
	private String candidateEmailID = "";
	private String offerLetterRegCode = "";
	private boolean showCandidateCommentsFlag = false;
	private String candidateComments = "";
	
	private ArrayList<Object> testDataList = new ArrayList<Object>();
	private String gradeflag = "";
	private String requiredByDate = "";
	private String hiddenRequisitionCode = "";
	private String reqStatus = "";
	
	
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getHiddenRequisitionCode() {
		return hiddenRequisitionCode;
	}
	public void setHiddenRequisitionCode(String hiddenRequisitionCode) {
		this.hiddenRequisitionCode = hiddenRequisitionCode;
	}
	public String getRequiredByDate() {
		return requiredByDate;
	}
	public void setRequiredByDate(String requiredByDate) {
		this.requiredByDate = requiredByDate;
	}

	/**
	 * @return the testDataList
	 */
	public ArrayList<Object> getTestDataList() {
		return testDataList;
	}
	/**
	 * @param testDataList the testDataList to set
	 */
	public void setTestDataList(ArrayList<Object> testDataList) {
		this.testDataList = testDataList;
	}
	/**
	 * @return the checkListCode
	 */
	public String getChecklistCode() {
		return checklistCode;
	}
	/**
	 * @param checkListCode the checkListCode to set
	 */
	public void setChecklistCode(String checklistCode) {
		this.checklistCode = checklistCode;
	}
	/**
	 * @return the checkListName
	 */
	public String getChecklistName() {
		return checklistName;
	}
	/**
	 * @param checkListName the checkListName to set
	 */
	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}
	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the requisitionCode
	 */
	public String getRequisitionCode() {
		return requisitionCode;
	}
	/**
	 * @param requisitionCode the requisitionCode to set
	 */
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	/**
	 * @return the requisitionName
	 */
	public String getRequisitionName() {
		return requisitionName;
	}
	/**
	 * @param requisitionName the requisitionName to set
	 */
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the candidateCode
	 */
	public String getCandidateCode() {
		return candidateCode;
	}
	/**
	 * @param candidateCode the candidateCode to set
	 */
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	/**
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}
	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	/**
	 * @return the joiningDate
	 */
	public String getJoiningDate() {
		return joiningDate;
	}
	/**
	 * @param joiningDate the joiningDate to set
	 */
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	/**
	 * @return the offerDate
	 */
	public String getOfferDate() {
		return offerDate;
	}
	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	/**
	 * @return the currentCtc
	 */
	public String getCurrentCtc() {
		return currentCtc;
	}
	/**
	 * @param currentCtc the currentCtc to set
	 */
	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}
	/**
	 * @return the negotiatedCtc
	 */
	public String getNegotiatedCtc() {
		return negotiatedCtc;
	}
	/**
	 * @param negotiatedCtc the negotiatedCtc to set
	 */
	public void setNegotiatedCtc(String negotiatedCtc) {
		this.negotiatedCtc = negotiatedCtc;
	}
	/**
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}
	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return the rolesResponsibility
	 */
	public String getRolesResponsibility() {
		return rolesResponsibility;
	}
	/**
	 * @param rolesResponsibility the rolesResponsibility to set
	 */
	public void setRolesResponsibility(String rolesResponsibility) {
		this.rolesResponsibility = rolesResponsibility;
	}
	/**
	 * @return the empTypeCode
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	/**
	 * @param empTypeCode the empTypeCode to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	/**
	 * @return the hiringMgrCode
	 */
	public String getHiringMgrCode() {
		return hiringMgrCode;
	}
	/**
	 * @param hiringMgrCode the hiringMgrCode to set
	 */
	public void setHiringMgrCode(String hiringMgrCode) {
		this.hiringMgrCode = hiringMgrCode;
	}
	/**
	 * @return the hiringManager
	 */
	public String getHiringManager() {
		return hiringManager;
	}
	/**
	 * @param hiringManager the hiringManager to set
	 */
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}
	/**
	 * @return the expJoiningDate
	 */
	public String getExpJoiningDate() {
		return expJoiningDate;
	}
	/**
	 * @param expJoiningDate the expJoiningDate to set
	 */
	public void setExpJoiningDate(String expJoiningDate) {
		this.expJoiningDate = expJoiningDate;
	}
	/**
	 * @return the reportingToCode
	 */
	public String getReportingToCode() {
		return reportingToCode;
	}
	/**
	 * @param reportingToCode the reportingToCode to set
	 */
	public void setReportingToCode(String reportingToCode) {
		this.reportingToCode = reportingToCode;
	}
	/**
	 * @return the reportingTo
	 */
	public String getReportingTo() {
		return reportingTo;
	}
	/**
	 * @param reportingTo the reportingTo to set
	 */
	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}
	/**
	 * @return the testReqCode
	 */
	public String getTestReqCode() {
		return testReqCode;
	}
	/**
	 * @param testReqCode the testReqCode to set
	 */
	public void setTestReqCode(String testReqCode) {
		this.testReqCode = testReqCode;
	}
	/**
	 * @return the testRequirements
	 */
	public String getTestRequirements() {
		return testRequirements;
	}
	/**
	 * @param testRequirements the testRequirements to set
	 */
	public void setTestRequirements(String testRequirements) {
		this.testRequirements = testRequirements;
	}
	/**
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}
	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return templateCode;
	}
	/**
	 * @param templateCode the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}
	/**
	 * @param months the months to set
	 */
	public void setMonths(String months) {
		this.months = months;
	}
	/**
	 * @return the authorityCode
	 */
	public String getAuthorityCode() {
		return authorityCode;
	}
	/**
	 * @param authorityCode the authorityCode to set
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	/**
	 * @return the signingAuthority
	 */
	public String getSigningAuthority() {
		return signingAuthority;
	}
	/**
	 * @param signingAuthority the signingAuthority to set
	 */
	public void setSigningAuthority(String signingAuthority) {
		this.signingAuthority = signingAuthority;
	}
	/**
	 * @return the offerStatus
	 */
	public String getOfferStatus() {
		return offerStatus;
	}
	/**
	 * @param offerStatus the offerStatus to set
	 */
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the candConstraints
	 */
	public String getCandConstraints() {
		return candConstraints;
	}
	/**
	 * @param candConstraints the candConstraints to set
	 */
	public void setCandConstraints(String candConstraints) {
		this.candConstraints = candConstraints;
	}
	/**
	 * @return the probation
	 */
	public String getProbation() {
		return probation;
	}
	/**
	 * @param probation the probation to set
	 */
	public void setProbation(String probation) {
		this.probation = probation;
	}
	/**
	 * @return the backgroundCheck
	 */
	public String getBackgroundCheck() {
		return backgroundCheck;
	}
	/**
	 * @param backgroundCheck the backgroundCheck to set
	 */
	public void setBackgroundCheck(String backgroundCheck) {
		this.backgroundCheck = backgroundCheck;
	}
	/**
	 * @return the offeredCtc
	 */
	public String getOfferedCtc() {
		return offeredCtc;
	}
	/**
	 * @param offeredCtc the offeredCtc to set
	 */
	public void setOfferedCtc(String offeredCtc) {
		this.offeredCtc = offeredCtc;
	}
	/**
	 * @return the addcandFlag
	 */
	public boolean isAddcandFlag() {
		return addcandFlag;
	}
	/**
	 * @param addcandFlag the addcandFlag to set
	 */
	public void setAddcandFlag(boolean addcandFlag) {
		this.addcandFlag = addcandFlag;
	}
	
	/**
	 * @return the viewOfferFlag
	 */
	public boolean isViewOfferFlag() {
		return viewOfferFlag;
	}
	/**
	 * @param viewOfferFlag the viewOfferFlag to set
	 */
	public void setViewOfferFlag(boolean viewOfferFlag) {
		this.viewOfferFlag = viewOfferFlag;
	}
	
	/**
	 * @return the hiringMgr
	 */
	public String getHiringMgr() {
		return hiringMgr;
	}
	/**
	 * @param hiringMgr the hiringMgr to set
	 */
	public void setHiringMgr(String hiringMgr) {
		this.hiringMgr = hiringMgr;
	}
	/**
	 * @return the buttonName
	 */
	public String getButtonName() {
		return buttonName;
	}
	/**
	 * @param buttonName the buttonName to set
	 */
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getFrmName() {
		return frmName;
	}
	public void setFrmName(String frmName) {
		this.frmName = frmName;
	}
	public String getFrmId() {
		return frmId;
	}
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	public String getSalgrdId() {
		return salgrdId;
	}
	public void setSalgrdId(String salgrdId) {
		this.salgrdId = salgrdId;
	}
	public String getSalgrdName() {
		return salgrdName;
	}
	public void setSalgrdName(String salgrdName) {
		this.salgrdName = salgrdName;
	}
	public ArrayList getSalList() {
		return salList;
	}
	public void setSalList(ArrayList salList) {
		this.salList = salList;
	}
	public String getSalCode() {
		return salCode;
	}
	public void setSalCode(String salCode) {
		this.salCode = salCode;
	}
	public String getSalHead() {
		return salHead;
	}
	public void setSalHead(String salHead) {
		this.salHead = salHead;
	}
	public String getSalPerdiocity() {
		return salPerdiocity;
	}
	public void setSalPerdiocity(String salPerdiocity) {
		this.salPerdiocity = salPerdiocity;
	}
	public String getNewAmt() {
		return newAmt;
	}
	public void setNewAmt(String newAmt) {
		this.newAmt = newAmt;
	}
	public String getProSalCode() {
		return proSalCode;
	}
	public String getViewFlagStruc() {
		return viewFlagStruc;
	}
	public void setViewFlagStruc(String viewFlagStruc) {
		this.viewFlagStruc = viewFlagStruc;
	}
	public void setProSalCode(String proSalCode) {
		this.proSalCode = proSalCode;
	}
	public String getGrdFlag() {
		return grdFlag;
	}
	public void setGrdFlag(String grdFlag) {
		this.grdFlag = grdFlag;
	}
	public String getFrmFlag() {
		return frmFlag;
	}
	public void setFrmFlag(String frmFlag) {
		this.frmFlag = frmFlag;
	}
	public String getGrsAmt() {
		return grsAmt;
	}
	public void setGrsAmt(String grsAmt) {
		this.grsAmt = grsAmt;
	}
	public String getEmailtemplate() {
		return emailtemplate;
	}
	public void setEmailtemplate(String emailtemplate) {
		this.emailtemplate = emailtemplate;
	}
	
	public String getCalflag() {
		return calflag;
	}
	public void setCalflag(String calflag) {
		this.calflag = calflag;
	}
	public String getDoubleClickViewModeFlag() {
		return doubleClickViewModeFlag;
	}
	public void setDoubleClickViewModeFlag(String doubleClickViewModeFlag) {
		this.doubleClickViewModeFlag = doubleClickViewModeFlag;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getRecruiterToken() {
		return recruiterToken;
	}
	public void setRecruiterToken(String recruiterToken) {
		this.recruiterToken = recruiterToken;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getHBranch() {
		return hBranch;
	}
	public void setHBranch(String branch) {
		hBranch = branch;
	}
	public String getHDesg() {
		return hDesg;
	}
	public void setHDesg(String desg) {
		hDesg = desg;
	}
	public String getReportingToAdmin() {
		return reportingToAdmin;
	}
	public void setReportingToAdmin(String reportingToAdmin) {
		this.reportingToAdmin = reportingToAdmin;
	}
	public String getReportingToAdminCode() {
		return reportingToAdminCode;
	}
	public void setReportingToAdminCode(String reportingToAdminCode) {
		this.reportingToAdminCode = reportingToAdminCode;
	}
	public ArrayList getDbtList() {
		return dbtList;
	}
	public void setDbtList(ArrayList dbtList) {
		this.dbtList = dbtList;
	}
	public String getDbtCode() {
		return dbtCode;
	}
	public void setDbtCode(String dbtCode) {
		this.dbtCode = dbtCode;
	}
	public String getDbtHead() {
		return dbtHead;
	}
	public void setDbtHead(String dbtHead) {
		this.dbtHead = dbtHead;
	}
	public String getDbtPerdiocity() {
		return dbtPerdiocity;
	}
	public void setDbtPerdiocity(String dbtPerdiocity) {
		this.dbtPerdiocity = dbtPerdiocity;
	}
	public String getNewAmtDbt() {
		return newAmtDbt;
	}
	public void setNewAmtDbt(String newAmtDbt) {
		this.newAmtDbt = newAmtDbt;
	}
	public String getProDbtCode() {
		return proDbtCode;
	}
	public void setProDbtCode(String proDbtCode) {
		this.proDbtCode = proDbtCode;
	}
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}
	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getCheckRemove() {
		return checkRemove;
	}
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getAnnextureFileName() {
		return annextureFileName;
	}
	public void setAnnextureFileName(String annextureFileName) {
		this.annextureFileName = annextureFileName;
	}
	public String getCandidateEmailID() {
		return candidateEmailID;
	}
	public void setCandidateEmailID(String candidateEmailID) {
		this.candidateEmailID = candidateEmailID;
	}
	public String getOfferLetterRegCode() {
		return offerLetterRegCode;
	}
	public void setOfferLetterRegCode(String offerLetterRegCode) {
		this.offerLetterRegCode = offerLetterRegCode;
	}
	public boolean isShowCandidateCommentsFlag() {
		return showCandidateCommentsFlag;
	}
	public void setShowCandidateCommentsFlag(boolean showCandidateCommentsFlag) {
		this.showCandidateCommentsFlag = showCandidateCommentsFlag;
	}
	public String getCandidateComments() {
		return candidateComments;
	}
	public void setCandidateComments(String candidateComments) {
		this.candidateComments = candidateComments;
	}
	public String getGradeflag() {
		return gradeflag;
	}
	public void setGradeflag(String gradeflag) {
		this.gradeflag = gradeflag;
	}

	
}
