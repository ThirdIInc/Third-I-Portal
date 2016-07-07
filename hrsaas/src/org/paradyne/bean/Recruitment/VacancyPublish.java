/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class VacancyPublish extends BeanBase {
	
	
	private String recruiterName;
	private String noOfVacancies;
	private String recruiterId;
	private String rowId;
	private String recruiterListFlag="false";
	private String recruiterChk;
	private String reqDt;
	private String noVac;
	private String cancelFlag="false";
	private String formAction;
	private String statusKey="";
	
	private String reqCode;
	private String position;
	private String appliedBy;
	private String hiringMgr;
	private String reqDate;
	private String totalVacancy;
	private String requiredDate;
	private String vacanDtlCode;
	private String reqName;
	private String appEmpId;
	private String hiringEmpId;
	
	private String intEmployee;
	private String extEmployee;
	private String copWeb;
	private String jobCons;
	private String refProgram;
	private String comments;
	
	private String skillType;
	private String skillId;
	private String skillName;
	private String hssrlNo;
	private String skillExp;
	private String skillSel;
	private String chkSkillAll;
	private ArrayList skillList=null;
	private String hdeleteSkill;
	private String confChkSkill;
	private String skill="false"; 
	private String afterSaveView="false";
	private String myPage="";
	private String flag="false";
	private String jobConsview="";
	private String copWebview="";
	private ArrayList recruiterList=null;
	
	
	private String consultantListFlag="false";
	private String consultantId;
	private String consultantName;
	private String city;
	private String cityName;
	private String phoneNo;
	private String emailAdd;
	private String consultantVacancies;
	private String hdeleteConsultant;
	private String confChkConsultant;
	private String hssrlNoConsultant;
	private String chkConsultantAll;
	private String recruitmentInternal;
	private String recruitmentExternal;
	private ArrayList consultantList=null;
	private String f9Flag="true";
	private String headerView="true";
	private String flagVal;
	private String jobPortal="";
	private String onlinejobPort;
	private String rePublishFlag="false";
	
	private String requisitionCodeItr = "";
	private String requisitionNameItr = "";
	private String vacancyNumberItr = "";
	private String offerGivenItr = "";
	private String appointmentGivenItr = "";
	private String vacancyStatusItr = "";
	private ArrayList<VacancyPublish> cancelVacancyIteratorList = null;
	private String vacancyListAvailable = "false";
	private String isVacancyClose = "false";
	private String totalNumberOfRecords = "";
	private String vacancyPublishCodeItr = "";
	private String vacancyDetailCodeItr = "";
	
	/*For Referral Division*/
	private String divCode = "";
	private String divsion = "";
	 
	
	public String getVacancyDetailCodeItr() {
		return vacancyDetailCodeItr;
	}

	public void setVacancyDetailCodeItr(String vacancyDetailCodeItr) {
		this.vacancyDetailCodeItr = vacancyDetailCodeItr;
	}

	public String getVacancyPublishCodeItr() {
		return vacancyPublishCodeItr;
	}

	public void setVacancyPublishCodeItr(String vacancyPublishCodeItr) {
		this.vacancyPublishCodeItr = vacancyPublishCodeItr;
	}

	public String getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(String totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	public String getIsVacancyClose() {
		return isVacancyClose;
	}

	public void setIsVacancyClose(String isVacancyClose) {
		this.isVacancyClose = isVacancyClose;
	}

	public String getOnlinejobPort() {
		return onlinejobPort;
	}

	public void setOnlinejobPort(String onlinejobPort) {
		this.onlinejobPort = onlinejobPort;
	}

	public String getJobPortal() {
		return jobPortal;
	}

	public void setJobPortal(String jobPortal) {
		this.jobPortal = jobPortal;
	}

	public String getFlagVal() {
		return flagVal;
	}

	public void setFlagVal(String flagVal) {
		this.flagVal = flagVal;
	}

	public String getHeaderView() {
		return headerView;
	}

	public void setHeaderView(String headerView) {
		this.headerView = headerView;
	}

	public String getF9Flag() {
		return f9Flag;
	}

	public void setF9Flag(String flag) {
		f9Flag = flag;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(String noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	public ArrayList getRecruiterList() {
		return recruiterList;
	}

	public void setRecruiterList(ArrayList recruiterList) {
		this.recruiterList = recruiterList;
	}

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getRecruiterListFlag() {
		return recruiterListFlag;
	}

	public void setRecruiterListFlag(String recruiterListFlag) {
		this.recruiterListFlag = recruiterListFlag;
	}

	public String getRecruiterChk() {
		return recruiterChk;
	}

	public void setRecruiterChk(String recruiterChk) {
		this.recruiterChk = recruiterChk;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getHssrlNo() {
		return hssrlNo;
	}

	public void setHssrlNo(String hssrlNo) {
		this.hssrlNo = hssrlNo;
	}

	public String getSkillExp() {
		return skillExp;
	}

	public void setSkillExp(String skillExp) {
		this.skillExp = skillExp;
	}

	public String getSkillSel() {
		return skillSel;
	}

	public void setSkillSel(String skillSel) {
		this.skillSel = skillSel;
	}

	public String getChkSkillAll() {
		return chkSkillAll;
	}

	public void setChkSkillAll(String chkSkillAll) {
		this.chkSkillAll = chkSkillAll;
	}

	public ArrayList getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList skillList) {
		this.skillList = skillList;
	}

	public String getHdeleteSkill() {
		return hdeleteSkill;
	}

	public void setHdeleteSkill(String hdeleteSkill) {
		this.hdeleteSkill = hdeleteSkill;
	}

	public String getConfChkSkill() {
		return confChkSkill;
	}

	public void setConfChkSkill(String confChkSkill) {
		this.confChkSkill = confChkSkill;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getConsultantListFlag() {
		return consultantListFlag;
	}

	public void setConsultantListFlag(String consultantListFlag) {
		this.consultantListFlag = consultantListFlag;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getConsultantVacancies() {
		return consultantVacancies;
	}

	public void setConsultantVacancies(String consultantVacancies) {
		this.consultantVacancies = consultantVacancies;
	}

	public String getHdeleteConsultant() {
		return hdeleteConsultant;
	}

	public void setHdeleteConsultant(String hdeleteConsultant) {
		this.hdeleteConsultant = hdeleteConsultant;
	}

	public String getConfChkConsultant() {
		return confChkConsultant;
	}

	public void setConfChkConsultant(String confChkConsultant) {
		this.confChkConsultant = confChkConsultant;
	}

	public String getHssrlNoConsultant() {
		return hssrlNoConsultant;
	}

	public void setHssrlNoConsultant(String hssrlNoConsultant) {
		this.hssrlNoConsultant = hssrlNoConsultant;
	}

	public String getChkConsultantAll() {
		return chkConsultantAll;
	}

	public void setChkConsultantAll(String chkConsultantAll) {
		this.chkConsultantAll = chkConsultantAll;
	}

	public ArrayList getConsultantList() {
		return consultantList;
	}

	public void setConsultantList(ArrayList consultantList) {
		this.consultantList = consultantList;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	public String getHiringMgr() {
		return hiringMgr;
	}

	public void setHiringMgr(String hiringMgr) {
		this.hiringMgr = hiringMgr;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getTotalVacancy() {
		return totalVacancy;
	}

	public void setTotalVacancy(String totalVacancy) {
		this.totalVacancy = totalVacancy;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getVacanDtlCode() {
		return vacanDtlCode;
	}

	public void setVacanDtlCode(String vacanDtlCode) {
		this.vacanDtlCode = vacanDtlCode;
	}

	public String getIntEmployee() {
		return intEmployee;
	}

	public void setIntEmployee(String intEmployee) {
		this.intEmployee = intEmployee;
	}

	public String getExtEmployee() {
		return extEmployee;
	}

	public void setExtEmployee(String extEmployee) {
		this.extEmployee = extEmployee;
	}

	public String getCopWeb() {
		return copWeb;
	}

	public void setCopWeb(String copWeb) {
		this.copWeb = copWeb;
	}

	public String getJobCons() {
		return jobCons;
	}

	public void setJobCons(String jobCons) {
		this.jobCons = jobCons;
	}

	public String getRefProgram() {
		return refProgram;
	}

	public void setRefProgram(String refProgram) {
		this.refProgram = refProgram;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public String getAppEmpId() {
		return appEmpId;
	}

	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}

	public String getHiringEmpId() {
		return hiringEmpId;
	}

	public void setHiringEmpId(String hiringEmpId) {
		this.hiringEmpId = hiringEmpId;
	}

	public String getReqDt() {
		return reqDt;
	}

	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}

	public String getNoVac() {
		return noVac;
	}

	public void setNoVac(String noVac) {
		this.noVac = noVac;
	}

	public String getAfterSaveView() {
		return afterSaveView;
	}

	public void setAfterSaveView(String afterSaveView) {
		this.afterSaveView = afterSaveView;
	}

	public String getRecruitmentInternal() {
		return recruitmentInternal;
	}

	public void setRecruitmentInternal(String recruitmentInternal) {
		this.recruitmentInternal = recruitmentInternal;
	}

	public String getRecruitmentExternal() {
		return recruitmentExternal;
	}

	public void setRecruitmentExternal(String recruitmentExternal) {
		this.recruitmentExternal = recruitmentExternal;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(String statusKey) {
		this.statusKey = statusKey;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getJobConsview() {
		return jobConsview;
	}

	public void setJobConsview(String jobConsview) {
		this.jobConsview = jobConsview;
	}

	public String getCopWebview() {
		return copWebview;
	}

	public void setCopWebview(String copWebview) {
		this.copWebview = copWebview;
	}

	public String getRePublishFlag() {
		return rePublishFlag;
	}

	public void setRePublishFlag(String rePublishFlag) {
		this.rePublishFlag = rePublishFlag;
	}

	public String getRequisitionCodeItr() {
		return requisitionCodeItr;
	}

	public void setRequisitionCodeItr(String requisitionCodeItr) {
		this.requisitionCodeItr = requisitionCodeItr;
	}

	public String getRequisitionNameItr() {
		return requisitionNameItr;
	}

	public void setRequisitionNameItr(String requisitionNameItr) {
		this.requisitionNameItr = requisitionNameItr;
	}

	public String getVacancyNumberItr() {
		return vacancyNumberItr;
	}

	public void setVacancyNumberItr(String vacancyNumberItr) {
		this.vacancyNumberItr = vacancyNumberItr;
	}

	public String getOfferGivenItr() {
		return offerGivenItr;
	}

	public void setOfferGivenItr(String offerGivenItr) {
		this.offerGivenItr = offerGivenItr;
	}

	public String getAppointmentGivenItr() {
		return appointmentGivenItr;
	}

	public void setAppointmentGivenItr(String appointmentGivenItr) {
		this.appointmentGivenItr = appointmentGivenItr;
	}

	public ArrayList<VacancyPublish> getCancelVacancyIteratorList() {
		return cancelVacancyIteratorList;
	}

	public void setCancelVacancyIteratorList(
			ArrayList<VacancyPublish> cancelVacancyIteratorList) {
		this.cancelVacancyIteratorList = cancelVacancyIteratorList;
	}

	public String getVacancyStatusItr() {
		return vacancyStatusItr;
	}

	public void setVacancyStatusItr(String vacancyStatusItr) {
		this.vacancyStatusItr = vacancyStatusItr;
	}

	public String getVacancyListAvailable() {
		return vacancyListAvailable;
	}

	public void setVacancyListAvailable(String vacancyListAvailable) {
		this.vacancyListAvailable = vacancyListAvailable;
	}
	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivsion() {
		return divsion;
	}

	public void setDivsion(String divsion) {
		this.divsion = divsion;
	}

	

}
