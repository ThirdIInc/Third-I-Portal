/**
 * 
 */
package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
/**
 * @author pradeep kumar sahoo
 *
 */
public class EmployeeRequisition extends BeanBase {
	
	private String sourceComments="";
private String checkFlag="";	
private String source="";	
private String pageNumber="";	
private String apprvStat="";
private String dashletFlag="false";    //this flag will be true when view requisition button click within the dashlet.
private String addNewFlag="false"; //this flag to return back to Interview details
private String commentFlag="false";
private String reqFlag="";
private String apprvComment="";
private String reqsLevel="";
private String totalPage="";
private String pageNoField="";
private String apprvFlag="false";	
private String apprvName="";
private String apprvDate="";
private String apprvRem="";
private String spname="";
private String qname="";	
private String jdEffDate="";	
private String hiddenReqCode="";
private String hiddenCode="";
private String idReq;
private String enableFirst="false";
private String enableSecond="false";
private String viewFirstCancel="false";
private String viewSecondCancel="false";
private String hiddenQualificationName;	
private String reqApprStatusSer;
private String hiddenApproveStatus;	
private String hiddenStatus;	
private String cancel1="false";
private String flagView="false"; 
private String previousFlag="false";
private String nextFlag="false";
private String cancelFrth="false";
private String cancelThrd="false";	
private String inter;
private String exter;
private String quaDetCode;
private String skillDetCode;
private String domDetCode;
private String certDetCode;
private String updateFirstFlag="false";	
private String updateSecondFlag="false";
private String saveFirstFlag="true";
private String saveSecondFlag="false";
private String reqCompensation;
private String minExp;
private String maxExp;
private String reqPartFull;
private String reqConType="";
private String vacType="";
private String hCode="";
private String reqCode;
private String reqPositionCode;
private String reqPositionName;
private String internal;
private String external;

private String listFlag = "false";
private String noData = "";
private String reqName ="";
private String srNo="";
private String myPage;
private String reqApprStatus;
private String reqAppBy;
private String formFlag ="false";
private String formAction="";
private String statusKey;
//Vacancy Flag
private String vacDate;
private String noOfVac;
private String vacDetCode; 
private String chkVacAll;
private String jdDescName;
private String jdDesc;
private String jdRoleDesc;
private String specialReq;
private String persoanlReq;
private String jdCode;

private String minReuiredDays="";
/**
 * Qualification Details
 */
private String qualType;
private String qualCode;
private String qualName;
private String qualLevelCod;
private String qualiLevelName;
private String splCode;
private String splName;
private String cutOff;
private String hqualType;
private String hqualName;
private String hqualiLevelName;
private String hsplName;
private String hsplId;
private String hcutOff;
private String hqsrlNo;
private String sel;
private String hdeleteVac;
private String confChkVac;
private String confChkDel;
private String hdeleteQuali;
private String qualificationName;
private String qualificationId;
private String quaChk;
private String quaFlag="false";
private String rowId;
private String chkEmpAll;
private  ArrayList qualList=null;

//Skill Details
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


//Domain detail
private String domType;
private String domId;
private String domName;
private String hdsrlNo;
private String domExp;
private String domSel;
private String chkDomAll;
private ArrayList domList=null;
private String hdeleteDom;
private String confChkDom;
private String qualFlag="true";
private String domFlag="false";
private String skillFlag="true";
private String domainName="false";
private String domainCode;
private String chkDom;



//Certification Details Variable;

private String confChkCert;
private ArrayList certList=null;
private String certFlag;
private String certType;
private String certName;
private String certIssueBy;
private String certValid;
private String certOption;
private String certChk;
private String hdeleteCert;

private String reqDt;
private String reqDept;
private String reqDivCode;
private String reqDiv;
private String reqImmd;
private String reqBrnCode;
private String reqBrn;
private String reqImmdDt;
private String reqDeptCode;
	//private String reqStatus;
private String reqFtr;
private String appliedFor;
private String appliedBy;
private String reqFtrDt;
private String reqExpFr;
private String reqExpTo;
private String	reqComment;
	
private String appliedCode;
private String hiringcode;
private String	expectedCTC;
private String	hiringManager;
private String hiringtoken;
private String hiddenEmpReqId = "";
private String	checkflab;
	
private String reqSkill;
private String reqJobDesc;
private String reqQual;
private String reqDesQua;
private String reqApprover;
private String reqStatus;
private String reqAprCode;
	
private ArrayList jobList;
private String jobDescId;
	
	
private ArrayList skilllist;
private String hskillCode;
private String hskillName;
private  ArrayList test=null;
	
private  ArrayList quallist=null;
private String qulCode;
private String qulName;
private  ArrayList vacList=null;
private ArrayList loadList=null;
private ArrayList apprvList=null;

/**
 * Created by Varun Khetan on 27/04/2009....
 */
private String createNewFlag = "";
private String offerAppointFlag="false";
private String requisitionName = "";
private String requisitionCode = "";
private String position = "";
private String division = "";
private String branch = "";
private String department = "";
private String viewQua="false";
private String viewSkill="false";
private String viewDom="false";
private String viewCert="false";
private String checkUploadProfile;
private String reqCodeToApply;
private String positionCode;
private String divisionCode;
private String branchCode;
private String deptCode;


private ArrayList list=null;//this is for candidate job board

private HashMap statMap; //this for candidate job board
private String advtName="";
private String applyFlag = "true";

private String leadTimeFlag = "";
private String dummyTokenForReplace = "";
private String replaceEmpId = "";
private String replaceEmpName = "";
private String hiddenEmpId = "";
private String hiddenEmpName ="";

private ArrayList removeEmpDataList = null;
private String removeEmpId = "";
private String removeEmpToken = "";
private String removeEmpName = "";
private String newPostComment = "";

TreeMap tmap =null ;
private String viewforEmployeeRequisition = "true";

//Added by Nilesh 9th August

private String approverName  = "";
private ArrayList approverList;

private ArrayList  keepInformedList;
private String  serialNo  = "";
private String  keepInformedEmpToken  = "";
private String  keepInformedEmpName  = "";
private String  keepInformedEmpId  = "";

private String employeeId = "";
private String  employeeToken = "";
private String  employeeName = "";


private String empid = "";
private String  keepInformedEmpTokenItt = "";
private String  keepInformedEmpNameItt = "";
private String hiddenDeleteId = "";

private String srNoIterator = "";

private String firstApproverCode = "";
private String firstApproverToken = "";


private String checkRemove = "";
private String backFlag ="";
private String commentsFlag = "false";
private String jobBoardNoRecord = "false";
private String calCulayedRequiredByDate = "";

public String getCalCulayedRequiredByDate() {
	return calCulayedRequiredByDate;
}
public void setCalCulayedRequiredByDate(String calCulayedRequiredByDate) {
	this.calCulayedRequiredByDate = calCulayedRequiredByDate;
}
public String getJobBoardNoRecord() {
	return jobBoardNoRecord;
}
public void setJobBoardNoRecord(String jobBoardNoRecord) {
	this.jobBoardNoRecord = jobBoardNoRecord;
}
/**
 * @return the backFlag
 */
public String getBackFlag() {
	return backFlag;
}
/**
 * @param backFlag the backFlag to set
 */
public void setBackFlag(String backFlag) {
	this.backFlag = backFlag;
}
/**
 * @return the checkRemove
 */
public String getCheckRemove() {
	return checkRemove;
}
/**
 * @param checkRemove the checkRemove to set
 */
public void setCheckRemove(String checkRemove) {
	this.checkRemove = checkRemove;
}
/**
 * @return the firstApproverCode
 */
public String getFirstApproverCode() {
	return firstApproverCode;
}
/**
 * @param firstApproverCode the firstApproverCode to set
 */
public void setFirstApproverCode(String firstApproverCode) {
	this.firstApproverCode = firstApproverCode;
}
/**
 * @return the firstApproverToken
 */
public String getFirstApproverToken() {
	return firstApproverToken;
}
/**
 * @param firstApproverToken the firstApproverToken to set
 */
public void setFirstApproverToken(String firstApproverToken) {
	this.firstApproverToken = firstApproverToken;
}
/**
 * @return the srNoIterator
 */
public String getSrNoIterator() {
	return srNoIterator;
}
/**
 * @param srNoIterator the srNoIterator to set
 */
public void setSrNoIterator(String srNoIterator) {
	this.srNoIterator = srNoIterator;
}
public String getViewforEmployeeRequisition() {
	return viewforEmployeeRequisition;
}
public void setViewforEmployeeRequisition(String viewforEmployeeRequisition) {
	this.viewforEmployeeRequisition = viewforEmployeeRequisition;
}
public TreeMap getTmap() {
	return tmap;
}
public void setTmap(TreeMap tmap) {
	this.tmap = tmap;
}
public String getNewPostComment() {
	return newPostComment;
}
public void setNewPostComment(String newPostComment) {
	this.newPostComment = newPostComment;
}
public String getDummyTokenForReplace() {
	return dummyTokenForReplace;
}
public void setDummyTokenForReplace(String dummyTokenForReplace) {
	this.dummyTokenForReplace = dummyTokenForReplace;
}
public String getReplaceEmpId() {
	return replaceEmpId;
}
public void setReplaceEmpId(String replaceEmpId) {
	this.replaceEmpId = replaceEmpId;
}
public String getReplaceEmpName() {
	return replaceEmpName;
}
public void setReplaceEmpName(String replaceEmpName) {
	this.replaceEmpName = replaceEmpName;
}
public String getHiddenEmpId() {
	return hiddenEmpId;
}
public void setHiddenEmpId(String hiddenEmpId) {
	this.hiddenEmpId = hiddenEmpId;
}
public String getHiddenEmpName() {
	return hiddenEmpName;
}
public void setHiddenEmpName(String hiddenEmpName) {
	this.hiddenEmpName = hiddenEmpName;
}
public String getLeadTimeFlag() {
	return leadTimeFlag;
}
public void setLeadTimeFlag(String leadTimeFlag) {
	this.leadTimeFlag = leadTimeFlag;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getDivision() {
	return division;
}
public void setDivision(String division) {
	this.division = division;
}
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getRequisitionCode() {
	return requisitionCode;
}
public void setRequisitionCode(String requisitionCode) {
	this.requisitionCode = requisitionCode;
}
public String getRequisitionName() {
	return requisitionName;
}
public void setRequisitionName(String requisitionName) {
	this.requisitionName = requisitionName;
}
public String getApprvName() {
	return apprvName;
}
public void setApprvName(String apprvName) {
	this.apprvName = apprvName;
}
public String getApprvDate() {
	return apprvDate;
}
public void setApprvDate(String apprvDate) {
	this.apprvDate = apprvDate;
}
public String getApprvRem() {
	return apprvRem;
}
public void setApprvRem(String apprvRem) {
	this.apprvRem = apprvRem;
}
public String getSpname() {
	return spname;
}
public void setSpname(String spname) {
	this.spname = spname;
}
public ArrayList getApprvList() {
	return apprvList;
}
public void setApprvList(ArrayList apprvList) {
	this.apprvList = apprvList;
}
	public ArrayList getLoadList() {
		return loadList;
	}
	public void setLoadList(ArrayList loadList) {
		this.loadList = loadList;
	}
	public ArrayList getTest() {
		return test;
	}
	public void setTest(ArrayList test) {
		this.test = test;
	}
	public ArrayList getJobList() {
		return jobList;
	}
	public void setJobList(ArrayList jobList) {
		this.jobList = jobList;
	}

	public String getReqDiv() {
		return reqDiv;
	}
	public void setReqDiv(String reqDiv) {
		this.reqDiv = reqDiv;
	}
	public String getReqBrn() {
		return reqBrn;
	}
	public void setReqBrn(String reqBrn) {
		this.reqBrn = reqBrn;
	}
	public String getReqDept() {
		return reqDept;
	}
	public void setReqDept(String reqDept) {
		this.reqDept = reqDept;
	}
	
	public String getReqDt() {
		return reqDt;
	}
	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}
	public String getReqImmd() {
		return reqImmd;
	}
	public void setReqImmd(String reqImmd) {
		this.reqImmd = reqImmd;
	}
	public String getReqImmdDt() {
		return reqImmdDt;
	}
	public void setReqImmdDt(String reqImmdDt) {
		this.reqImmdDt = reqImmdDt;
	}
	public String getReqFtr() {
		return reqFtr;
	}
	public void setReqFtr(String reqFtr) {
		this.reqFtr = reqFtr;
	}
	public String getReqFtrDt() {
		return reqFtrDt;
	}
	public void setReqFtrDt(String reqFtrDt) {
		this.reqFtrDt = reqFtrDt;
	}
	public String getReqSkill() {
		return reqSkill;
	}
	public void setReqSkill(String reqSkill) {
		this.reqSkill = reqSkill;
	}
	public String getReqJobDesc() {
		return reqJobDesc;
	}
	public void setReqJobDesc(String reqJobDesc) {
		this.reqJobDesc = reqJobDesc;
	}
	public String getReqQual() {
		return reqQual;
	}
	public void setReqQual(String reqQual) {
		this.reqQual = reqQual;
	}
	public String getReqExpFr() {
		return reqExpFr;
	}
	public void setReqExpFr(String reqExpFr) {
		this.reqExpFr = reqExpFr;
	}
	public String getReqExpTo() {
		return reqExpTo;
	}
	public void setReqExpTo(String reqExpTo) {
		this.reqExpTo = reqExpTo;
	}
	public String getReqDesQua() {
		return reqDesQua;
	}
	public void setReqDesQua(String reqDesQua) {
		this.reqDesQua = reqDesQua;
	}
	
	public String getReqComment() {
		return reqComment;
	}
	public void setReqComment(String reqComment) {
		this.reqComment = reqComment;
	}
	public String getReqDivCode() {
		return reqDivCode;
	}
	public void setReqDivCode(String reqDivCode) {
		this.reqDivCode = reqDivCode;
	}
	public String getReqBrnCode() {
		return reqBrnCode;
	}
	public void setReqBrnCode(String reqBrnCode) {
		this.reqBrnCode = reqBrnCode;
	}
	public String getReqDeptCode() {
		return reqDeptCode;
	}
	public void setReqDeptCode(String reqDeptCode) {
		this.reqDeptCode = reqDeptCode;
	}
	
	public String getReqApprover() {
		return reqApprover;
	}
	public void setReqApprover(String reqApprover) {
		this.reqApprover = reqApprover;
	}
	
	
	public String getReqAprCode() {
		return reqAprCode;
	}
	public void setReqAprCode(String reqAprCode) {
		this.reqAprCode = reqAprCode;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getAppliedFor() {
		return appliedFor;
	}
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
	public String getJobDescId() {
		return jobDescId;
	}
	public void setJobDescId(String jobDescId) {
		this.jobDescId = jobDescId;
	}
	public String getAppliedCode() {
		return appliedCode;
	}
	public void setAppliedCode(String appliedCode) {
		this.appliedCode = appliedCode;
	}
	public String getHiringcode() {
		return hiringcode;
	}
	public void setHiringcode(String hiringcode) {
		this.hiringcode = hiringcode;
	}
	public String getExpectedCTC() {
		return expectedCTC;
	}
	public void setExpectedCTC(String expectedCTC) {
		this.expectedCTC = expectedCTC;
	}
	public String getHiringManager() {
		return hiringManager;
	}
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}
	public ArrayList getSkilllist() {
		return skilllist;
	}
	public void setSkilllist(ArrayList skilllist) {
		this.skilllist = skilllist;
	}
	public String getHskillCode() {
		return hskillCode;
	}
	public void setHskillCode(String hskillCode) {
		this.hskillCode = hskillCode;
	}
	public String getHskillName() {
		return hskillName;
	}
	public void setHskillName(String hskillName) {
		this.hskillName = hskillName;
	}
	
	public String getHiringtoken() {
		return hiringtoken;
	}
	public void setHiringtoken(String hiringtoken) {
		this.hiringtoken = hiringtoken;
	}
	public ArrayList getQuallist() {
		return quallist;
	}
	public void setQuallist(ArrayList quallist) {
		this.quallist = quallist;
	}
	public String getQulCode() {
		return qulCode;
	}
	public void setQulCode(String qulCode) {
		this.qulCode = qulCode;
	}
	public String getQulName() {
		return qulName;
	}
	public void setQulName(String qulName) {
		this.qulName = qulName;
	}
	
	public ArrayList getVacList() {
		return vacList;
	}
	public void setVacList(ArrayList vacList) {
		this.vacList = vacList;
	}
	public String getJdCode() {
		return jdCode;
	}
	public void setJdCode(String jdCode) {
		this.jdCode = jdCode;
	}

	
	public String getHiddenEmpReqId() {
		return hiddenEmpReqId;
	}
	public void setHiddenEmpReqId(String hiddenEmpReqId) {
		this.hiddenEmpReqId = hiddenEmpReqId;
	}
	public String getCheckflab() {
		return checkflab;
	}
	public void setCheckflab(String checkflab) {
		this.checkflab = checkflab;
	}
	public String getNoOfVac() {
		return noOfVac;
	}
	public void setNoOfVac(String noOfVac) {
		this.noOfVac = noOfVac;
	}
	
	public String getVacDate() {
		return vacDate;
	}
	public void setVacDate(String vacDate) {
		this.vacDate = vacDate;
	}
	
	public String getReqCompensation() {
		return reqCompensation;
	}
	public void setReqCompensation(String reqCompensation) {
		this.reqCompensation = reqCompensation;
	}
	public String getMinExp() {
		return minExp;
	}
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}
	public String getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}
	
	public String getReqPartFull() {
		return reqPartFull;
	}
	public void setReqPartFull(String reqPartFull) {
		this.reqPartFull = reqPartFull;
	}

	public String getReqConType() {
		return reqConType;
	}
	public void setReqConType(String reqConType) {
		this.reqConType = reqConType;
	}
	public String getVacType() {
		return vacType;
	}
	public void setVacType(String vacType) {
		this.vacType = vacType;
	}
	public String getHCode() {
		return hCode;
	}
	public void setHCode(String code) {
		hCode = code;
	}
	
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	
	public String getReqPositionCode() {
		return reqPositionCode;
	}
	public void setReqPositionCode(String reqPositionCode) {
		this.reqPositionCode = reqPositionCode;
	}
	public String getReqPositionName() {
		return reqPositionName;
	}
	public void setReqPositionName(String reqPositionName) {
		this.reqPositionName = reqPositionName;
	}
	public String getInternal() {
		return internal;
	}
	public void setInternal(String internal) {
		this.internal = internal;
	}
	public String getExternal() {
		return external;
	}
	public void setExternal(String external) {
		this.external = external;
	}
	public String getJdDescName() {
		return jdDescName;
	}
	public void setJdDescName(String jdDescName) {
		this.jdDescName = jdDescName;
	}
	public String getJdDesc() {
		return jdDesc;
	}
	public void setJdDesc(String jdDesc) {
		this.jdDesc = jdDesc;
	}
	public String getJdRoleDesc() {
		return jdRoleDesc;
	}
	public void setJdRoleDesc(String jdRoleDesc) {
		this.jdRoleDesc = jdRoleDesc;
	}
	public String getSpecialReq() {
		return specialReq;
	}
	public void setSpecialReq(String specialReq) {
		this.specialReq = specialReq;
	}
	public String getPersoanlReq() {
		return persoanlReq;
	}
	public void setPersoanlReq(String persoanlReq) {
		this.persoanlReq = persoanlReq;
	}
	public String getQualType() {
		return qualType;
	}
	public void setQualType(String qualType) {
		this.qualType = qualType;
	}
	public String getQualCode() {
		return qualCode;
	}
	public void setQualCode(String qualCode) {
		this.qualCode = qualCode;
	}
	public String getQualName() {
		return qualName;
	}
	public void setQualName(String qualName) {
		this.qualName = qualName;
	}
	public String getQualLevelCod() {
		return qualLevelCod;
	}
	public void setQualLevelCod(String qualLevelCod) {
		this.qualLevelCod = qualLevelCod;
	}
	public String getQualiLevelName() {
		return qualiLevelName;
	}
	public void setQualiLevelName(String qualiLevelName) {
		this.qualiLevelName = qualiLevelName;
	}
	public String getSplCode() {
		return splCode;
	}
	public void setSplCode(String splCode) {
		this.splCode = splCode;
	}
	public String getSplName() {
		return splName;
	}
	public void setSplName(String splName) {
		this.splName = splName;
	}
	public String getCutOff() {
		return cutOff;
	}
	public void setCutOff(String cutOff) {
		this.cutOff = cutOff;
	}
	public String getHqualType() {
		return hqualType;
	}
	public void setHqualType(String hqualType) {
		this.hqualType = hqualType;
	}
	public String getHqualName() {
		return hqualName;
	}
	public void setHqualName(String hqualName) {
		this.hqualName = hqualName;
	}
	public String getHqualiLevelName() {
		return hqualiLevelName;
	}
	public void setHqualiLevelName(String hqualiLevelName) {
		this.hqualiLevelName = hqualiLevelName;
	}
	public String getHsplName() {
		return hsplName;
	}
	public void setHsplName(String hsplName) {
		this.hsplName = hsplName;
	}
	public String getHcutOff() {
		return hcutOff;
	}
	public void setHcutOff(String hcutOff) {
		this.hcutOff = hcutOff;
	}
	public String getHqsrlNo() {
		return hqsrlNo;
	}
	public void setHqsrlNo(String hqsrlNo) {
		this.hqsrlNo = hqsrlNo;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	public ArrayList getQualList() {
		return qualList;
	}
	public void setQualList(ArrayList qualList) {
		this.qualList = qualList;
	}
	
	public String getHdeleteVac() {
		return hdeleteVac;
	}
	public void setHdeleteVac(String hdeleteVac) {
		this.hdeleteVac = hdeleteVac;
	}
	public String getConfChkVac() {
		return confChkVac;
	}
	public void setConfChkVac(String confChkVac) {
		this.confChkVac = confChkVac;
	}
	public String getConfChkDel() {
		return confChkDel;
	}
	public void setConfChkDel(String confChkDel) {
		this.confChkDel = confChkDel;
	}
	public String getHdeleteQuali() {
		return hdeleteQuali;
	}
	public void setHdeleteQuali(String hdeleteQuali) {
		this.hdeleteQuali = hdeleteQuali;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public String getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(String qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getHsplId() {
		return hsplId;
	}
	public void setHsplId(String hsplId) {
		this.hsplId = hsplId;
	}
	public String getChkEmpAll() {
		return chkEmpAll;
	}
	public void setChkEmpAll(String chkEmpAll) {
		this.chkEmpAll = chkEmpAll;
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
	public String getQualFlag() {
		return qualFlag;
	}
	public void setQualFlag(String qualFlag) {
		this.qualFlag = qualFlag;
	}
	public String getSkillFlag() {
		return skillFlag;
	}
	public void setSkillFlag(String skillFlag) {
		this.skillFlag = skillFlag;
	}
	
	public String getDomType() {
		return domType;
	}
	public void setDomType(String domType) {
		this.domType = domType;
	}
	public String getDomId() {
		return domId;
	}
	public void setDomId(String domId) {
		this.domId = domId;
	}
	public String getDomName() {
		return domName;
	}
	public void setDomName(String domName) {
		this.domName = domName;
	}
	public String getHdsrlNo() {
		return hdsrlNo;
	}
	public void setHdsrlNo(String hdsrlNo) {
		this.hdsrlNo = hdsrlNo;
	}
	public String getDomExp() {
		return domExp;
	}
	public void setDomExp(String domExp) {
		this.domExp = domExp;
	}
	public String getDomSel() {
		return domSel;
	}
	public void setDomSel(String domSel) {
		this.domSel = domSel;
	}
	public String getChkDomAll() {
		return chkDomAll;
	}
	public void setChkDomAll(String chkDomAll) {
		this.chkDomAll = chkDomAll;
	}
	public ArrayList getDomList() {
		return domList;
	}
	public void setDomList(ArrayList domList) {
		this.domList = domList;
	}
	public String getHdeleteDom() {
		return hdeleteDom;
	}
	public void setHdeleteDom(String hdeleteDom) {
		this.hdeleteDom = hdeleteDom;
	}
	public String getConfChkDom() {
		return confChkDom;
	}
	public void setConfChkDom(String confChkDom) {
		this.confChkDom = confChkDom;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getConfChkCert() {
		return confChkCert;
	}
	public void setConfChkCert(String confChkCert) {
		this.confChkCert = confChkCert;
	}
	public ArrayList getCertList() {
		return certList;
	}
	public void setCertList(ArrayList certList) {
		this.certList = certList;
	}
	public String getCertFlag() {
		return certFlag;
	}
	public void setCertFlag(String certFlag) {
		this.certFlag = certFlag;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getCertIssueBy() {
		return certIssueBy;
	}
	public void setCertIssueBy(String certIssueBy) {
		this.certIssueBy = certIssueBy;
	}
	public String getCertValid() {
		return certValid;
	}
	public void setCertValid(String certValid) {
		this.certValid = certValid;
	}
	public String getCertOption() {
		return certOption;
	}
	public void setCertOption(String certOption) {
		this.certOption = certOption;
	}
	public String getCertChk() {
		return certChk;
	}
	public void setCertChk(String certChk) {
		this.certChk = certChk;
	}
	public String getHdeleteCert() {
		return hdeleteCert;
	}
	public void setHdeleteCert(String hdeleteCert) {
		this.hdeleteCert = hdeleteCert;
	}
	public String getChkDom() {
		return chkDom;
	}
	public void setChkDom(String chkDom) {
		this.chkDom = chkDom;
	}
	public String getDomFlag() {
		return domFlag;
	}
	public void setDomFlag(String domFlag) {
		this.domFlag = domFlag;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getQuaChk() {
		return quaChk;
	}
	public void setQuaChk(String quaChk) {
		this.quaChk = quaChk;
	}
	public String getQuaFlag() {
		return quaFlag;
	}
	public void setQuaFlag(String quaFlag) {
		this.quaFlag = quaFlag;
	}
	
	public String getChkVacAll() {
		return chkVacAll;
	}
	public void setChkVacAll(String chkVacAll) {
		this.chkVacAll = chkVacAll;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getAppliedBy() {
		return appliedBy;
	}
	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getReqApprStatus() {
		return reqApprStatus;
	}
	public void setReqApprStatus(String reqApprStatus) {
		this.reqApprStatus = reqApprStatus;
	}
	public String getVacDetCode() {
		return vacDetCode;
	}
	public void setVacDetCode(String vacDetCode) {
		this.vacDetCode = vacDetCode;
	}
	public String getSaveFirstFlag() {
		return saveFirstFlag;
	}
	public void setSaveFirstFlag(String saveFirstFlag) {
		this.saveFirstFlag = saveFirstFlag;
	}
	public String getSaveSecondFlag() {
		return saveSecondFlag;
	}
	public void setSaveSecondFlag(String saveSecondFlag) {
		this.saveSecondFlag = saveSecondFlag;
	}

	public String getUpdateFirstFlag() {
		return updateFirstFlag;
	}
	public void setUpdateFirstFlag(String updateFirstFlag) {
		this.updateFirstFlag = updateFirstFlag;
	}
	public String getQuaDetCode() {
		return quaDetCode;
	}
	public void setQuaDetCode(String quaDetCode) {
		this.quaDetCode = quaDetCode;
	}
	public String getSkillDetCode() {
		return skillDetCode;
	}
	public void setSkillDetCode(String skillDetCode) {
		this.skillDetCode = skillDetCode;
	}
	public String getDomDetCode() {
		return domDetCode;
	}
	public void setDomDetCode(String domDetCode) {
		this.domDetCode = domDetCode;
	}
	public String getCertDetCode() {
		return certDetCode;
	}
	public void setCertDetCode(String certDetCode) {
		this.certDetCode = certDetCode;
	}
	public String getUpdateSecondFlag() {
		return updateSecondFlag;
	}
	public void setUpdateSecondFlag(String updateSecondFlag) {
		this.updateSecondFlag = updateSecondFlag;
	}
	public String getInter() {
		return inter;
	}
	public void setInter(String inter) {
		this.inter = inter;
	}
	public String getExter() {
		return exter;
	}
	public void setExter(String exter) {
		this.exter = exter;
	}
	public String getCancelThrd() {
		return cancelThrd;
	}
	public void setCancelThrd(String cancelThrd) {
		this.cancelThrd = cancelThrd;
	}
	public String getCancelFrth() {
		return cancelFrth;
	}
	public void setCancelFrth(String cancelFrth) {
		this.cancelFrth = cancelFrth;
	}
	public String getNextFlag() {
		return nextFlag;
	}
	public void setNextFlag(String nextFlag) {
		this.nextFlag = nextFlag;
	}
	public String getPreviousFlag() {
		return previousFlag;
	}
	public void setPreviousFlag(String previousFlag) {
		this.previousFlag = previousFlag;
	}
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}
	public String getCancel1() {
		return cancel1;
	}
	public void setCancel1(String cancel1) {
		this.cancel1 = cancel1;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getReqAppBy() {
		return reqAppBy;
	}
	public void setReqAppBy(String reqAppBy) {
		this.reqAppBy = reqAppBy;
	}
	public String getHiddenApproveStatus() {
		return hiddenApproveStatus;
	}
	public void setHiddenApproveStatus(String hiddenApproveStatus) {
		this.hiddenApproveStatus = hiddenApproveStatus;
	}
	public String getFormFlag() {
		return formFlag;
	}
	public void setFormFlag(String formFlag) {
		this.formFlag = formFlag;
	}
	
	public String getFormAction() {
		return formAction;
	}
	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}
	public String getReqApprStatusSer() {
		return reqApprStatusSer;
	}
	public void setReqApprStatusSer(String reqApprStatusSer) {
		this.reqApprStatusSer = reqApprStatusSer;
	}
	public String getHiddenQualificationName() {
		return hiddenQualificationName;
	}
	public void setHiddenQualificationName(String hiddenQualificationName) {
		this.hiddenQualificationName = hiddenQualificationName;
	}
	public String getStatusKey() {
		return statusKey;
	}
	public void setStatusKey(String statusKey) {
		this.statusKey = statusKey;
	}
	public String getIdReq() {
		return idReq;
	}
	public void setIdReq(String idReq) {
		this.idReq = idReq;
	}
	public String getViewFirstCancel() {
		return viewFirstCancel;
	}
	public void setViewFirstCancel(String viewFirstCancel) {
		this.viewFirstCancel = viewFirstCancel;
	}
	public String getViewSecondCancel() {
		return viewSecondCancel;
	}
	public void setViewSecondCancel(String viewSecondCancel) {
		this.viewSecondCancel = viewSecondCancel;
	}
	public String getEnableFirst() {
		return enableFirst;
	}
	public void setEnableFirst(String enableFirst) {
		this.enableFirst = enableFirst;
	}
	public String getEnableSecond() {
		return enableSecond;
	}
	public void setEnableSecond(String enableSecond) {
		this.enableSecond = enableSecond;
	}
	public String getHiddenReqCode() {
		return hiddenReqCode;
	}
	public void setHiddenReqCode(String hiddenReqCode) {
		this.hiddenReqCode = hiddenReqCode;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getJdEffDate() {
		return jdEffDate;
	}
	public void setJdEffDate(String jdEffDate) {
		this.jdEffDate = jdEffDate;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getApprvFlag() {
		return apprvFlag;
	}
	public void setApprvFlag(String apprvFlag) {
		this.apprvFlag = apprvFlag;
	}
	public String getCreateNewFlag() {
		return createNewFlag;
	}
	public void setCreateNewFlag(String createNewFlag) {
		this.createNewFlag = createNewFlag;
	}
	public String getOfferAppointFlag() {
		return offerAppointFlag;
	}
	public void setOfferAppointFlag(String offerAppointFlag) {
		this.offerAppointFlag = offerAppointFlag;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDashletFlag() {
		return dashletFlag;
	}
	public void setDashletFlag(String dashletFlag) {
		this.dashletFlag = dashletFlag;
	}
	public String getViewQua() {
		return viewQua;
	}
	public void setViewQua(String viewQua) {
		this.viewQua = viewQua;
	}
	public String getViewSkill() {
		return viewSkill;
	}
	public void setViewSkill(String viewSkill) {
		this.viewSkill = viewSkill;
	}
	public String getViewDom() {
		return viewDom;
	}
	public void setViewDom(String viewDom) {
		this.viewDom = viewDom;
	}
	public String getViewCert() {
		return viewCert;
	}
	public void setViewCert(String viewCert) {
		this.viewCert = viewCert;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public HashMap getStatMap() {
		return statMap;
	}
	public void setStatMap(HashMap statMap) {
		this.statMap = statMap;
	}
	public String getAdvtName() {
		return advtName;
	}
	public void setAdvtName(String advtName) {
		this.advtName = advtName;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public String getApprvStat() {
		return apprvStat;
	}
	public void setApprvStat(String apprvStat) {
		this.apprvStat = apprvStat;
	}
	public String getAddNewFlag() {
		return addNewFlag;
	}
	public void setAddNewFlag(String addNewFlag) {
		this.addNewFlag = addNewFlag;
	}
	public String getReqFlag() {
		return reqFlag;
	}
	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}
	public String getReqsLevel() {
		return reqsLevel;
	}
	public void setReqsLevel(String reqsLevel) {
		this.reqsLevel = reqsLevel;
	}
	public String getApprvComment() {
		return apprvComment;
	}
	public void setApprvComment(String apprvComment) {
		this.apprvComment = apprvComment;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getCheckUploadProfile() {
		return checkUploadProfile;
	}
	public void setCheckUploadProfile(String checkUploadProfile) {
		this.checkUploadProfile = checkUploadProfile;
	}
	public String getReqCodeToApply() {
		return reqCodeToApply;
	}
	public void setReqCodeToApply(String reqCodeToApply) {
		this.reqCodeToApply = reqCodeToApply;
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
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public ArrayList getRemoveEmpDataList() {
		return removeEmpDataList;
	}
	public void setRemoveEmpDataList(ArrayList removeEmpDataList) {
		this.removeEmpDataList = removeEmpDataList;
	}
	public String getRemoveEmpId() {
		return removeEmpId;
	}
	public void setRemoveEmpId(String removeEmpId) {
		this.removeEmpId = removeEmpId;
	}
	public String getRemoveEmpToken() {
		return removeEmpToken;
	}
	public void setRemoveEmpToken(String removeEmpToken) {
		this.removeEmpToken = removeEmpToken;
	}
	public String getRemoveEmpName() {
		return removeEmpName;
	}
	public void setRemoveEmpName(String removeEmpName) {
		this.removeEmpName = removeEmpName;
	}
	public String getMinReuiredDays() {
		return minReuiredDays;
	}
	public void setMinReuiredDays(String minReuiredDays) {
		this.minReuiredDays = minReuiredDays;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the approverList
	 */
	public ArrayList getApproverList() {
		return approverList;
	}
	/**
	 * @param approverList the approverList to set
	 */
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	/**
	 * @return the keepInformedList
	 */
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	/**
	 * @param keepInformedList the keepInformedList to set
	 */
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the keepInformedEmpName
	 */
	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}
	/**
	 * @param keepInformedEmpName the keepInformedEmpName to set
	 */
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}
	/**
	 * @return the keepInformedEmpId
	 */
	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}
	/**
	 * @param keepInformedEmpId the keepInformedEmpId to set
	 */
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}
	/**
	 * @return the keepInformedEmpToken
	 */
	public String getKeepInformedEmpToken() {
		return keepInformedEmpToken;
	}
	/**
	 * @param keepInformedEmpToken the keepInformedEmpToken to set
	 */
	public void setKeepInformedEmpToken(String keepInformedEmpToken) {
		this.keepInformedEmpToken = keepInformedEmpToken;
	}
	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}
	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	/**
	 * @return the keepInformedEmpTokenItt
	 */
	public String getKeepInformedEmpTokenItt() {
		return keepInformedEmpTokenItt;
	}
	/**
	 * @param keepInformedEmpTokenItt the keepInformedEmpTokenItt to set
	 */
	public void setKeepInformedEmpTokenItt(String keepInformedEmpTokenItt) {
		this.keepInformedEmpTokenItt = keepInformedEmpTokenItt;
	}
	/**
	 * @return the keepInformedEmpNameItt
	 */
	public String getKeepInformedEmpNameItt() {
		return keepInformedEmpNameItt;
	}
	/**
	 * @param keepInformedEmpNameItt the keepInformedEmpNameItt to set
	 */
	public void setKeepInformedEmpNameItt(String keepInformedEmpNameItt) {
		this.keepInformedEmpNameItt = keepInformedEmpNameItt;
	}
	/**
	 * @return the hiddenDeleteId
	 */
	public String getHiddenDeleteId() {
		return hiddenDeleteId;
	}
	/**
	 * @param hiddenDeleteId the hiddenDeleteId to set
	 */
	public void setHiddenDeleteId(String hiddenDeleteId) {
		this.hiddenDeleteId = hiddenDeleteId;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the commentsFlag
	 */
	public String getCommentsFlag() {
		return commentsFlag;
	}
	/**
	 * @param commentsFlag the commentsFlag to set
	 */
	public void setCommentsFlag(String commentsFlag) {
		this.commentsFlag = commentsFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getSourceComments() {
		return sourceComments;
	}
	public void setSourceComments(String sourceComments) {
		this.sourceComments = sourceComments;
	}

}
