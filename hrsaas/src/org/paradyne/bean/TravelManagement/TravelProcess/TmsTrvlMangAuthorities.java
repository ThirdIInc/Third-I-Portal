
package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 * 
 */
public class TmsTrvlMangAuthorities extends BeanBase {
	
	//ADDED BY VISHWAMBHAR
	private String claimwrkflowflag="";
	private String ackwrkflowflag="";
	private String hiddenclaimwrkflowflag="";
	private String hiddenackwrkflowflag="";
	private String itAcknoledge = "";
	private String hiddenitAcknoledge = "";
	private String hiddenittClaim = "";
	private String ittClaim = "";

	private String hiddenitAdvance = "";
	private String itAdvance = "";
	
	private String accountantemployee = "";
	private String accountantemployeeCode = "";
	private String accountantemployeeToken = "";
	
	private ArrayList subAccountantList =null;

	private String srNoAcc = "";
	private String itEmployeeTokenAcc = "";
	private String itEmployeeCodeAcc = "";
	private String itEmployeeAcc = "";
	
	//ADDED BY REEBA BEGINS
	private String alterAccountant="";
	private String alterAccountantCode = "";
	private String alterAccountantToken = "";
	private String itAltAccntCode = "";
	private String escalationEmployee = "";
	private String escalationEmployeeCode = "";
	private String escalationEmployeeToken = "";
	private String itEscalationEmployee = "";
	private String itEscalationEmployeeCode = "";
	//ADDED BY REEBA ENDS
	
	private String dataFlag = "false";
	private String mangAuthCode = "";
	private String appFlag = "";
	private String hidAppFlag = "N";
	private String hidChkFlg = "";
	private String branchCode = "";
	private String branch = "";
	private String mainSchdlr = "";
	private String mainSchdlrCode = "";
	private String altMainSchdlrCode = "";
	private String altMainSchdlr = "";
	private String schdlrApprCode = "";
	private String schdlrAppr = "";
	private String accntCode = "";
	private String accnt = "";
	private String mainSchdlrToken = "";
	private String altMainSchdlrToken = "";
	private String schdlrApprToken = "";
	private String accntToken = "";
	private String description = "";
	private String employeeCode = "";
	private String employee = "";
	private String employeeToken = "";
	private String status = "";
	private String hidStatus = "";
	private String descCnt;
	private String myHidden;
	private String hiddencode = "";
	private String hiddenBranchId = "";
	private String hiddenEmpId = "";
	private String hiddenAppFlag = "";
	private String hidTabEmpId = "";//to retrieved saved empId
	private String hidDeleteEmpId = "";
	
	

	// for iterator
	private String ItAuthCode = "";
	private String itBranch = "";
	private String itBranchCode = "";
	private String hdeleteCode = "";
	private String itSchdlrAppr = "";
	private String itSchdlr = "";
	private String itStatus = "";
	private String itSchdlrCode = "";
	private String itSchdlrApprCode = "";
	private String itAccnt = "";
	private String confChk = "";
	private String itAccntCode = "";
	private String itAltSchdlrCode = "";
	private String itAllBranch = "";
	private String itAllBranchCode = "";

	// for 3rd ietrator
	private String itEmployee = "";
	private String itEmployeeCode = "";
	private String itEmployeeToken = "";
 
	private ArrayList subSchdlrList = null;
	private ArrayList authoritiesList = null;
	private String srNo = "";
	private String hdeleteSub = "";
	private String hdeleteAuth = "";
	private String confChkAuth = "";
	// for paging
	private String myPage;
	private String show;
	private String selectname;
	private String panelFlag = "";
	private String retrnFlag = "";
	private String cancelFlg = "false";
	private boolean onLoadFlag = false;
	private boolean editFlag = false;

	 
	// to view subscheduler list
	private String itViewEmpToken = "";
	private String itViewEmpName = "";
	private String itViewTrvl = "";
	private String itViewConv = "";
	private String itViewLodge = "";
	private ArrayList viewMangAuthList = null;
	private String viewSrNo = "";
	private String subTabLength = "";

	// for note
	private boolean msgFlag = false;
	private boolean showBranchFlag = false;
	private String msg = "";
	private String branchMsg = "";
	private String divBranch="";
	private ArrayList branchList = null;
	private String testBranchCode="";
	private String myHiddenEmpCode="";
	
	
	private String testFlag = "false";
	private boolean noData = false;
	private boolean noDataSch = false;
	
	private String descriptionNew = "";
	private String accountantEmailID = "";

	

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	public String getDivBranch() {
		return divBranch;
	}

	public void setDivBranch(String divBranch) {
		this.divBranch = divBranch;
	}

	public boolean isMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(boolean msgFlag) {
		this.msgFlag = msgFlag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getViewSrNo() {
		return viewSrNo;
	}

	public void setViewSrNo(String viewSrNo) {
		this.viewSrNo = viewSrNo;
	}

	public String getItViewEmpToken() {
		return itViewEmpToken;
	}

	public void setItViewEmpToken(String itViewEmpToken) {
		this.itViewEmpToken = itViewEmpToken;
	}

	public String getItViewEmpName() {
		return itViewEmpName;
	}

	public void setItViewEmpName(String itViewEmpName) {
		this.itViewEmpName = itViewEmpName;
	}

	public String getItViewTrvl() {
		return itViewTrvl;
	}

	public void setItViewTrvl(String itViewTrvl) {
		this.itViewTrvl = itViewTrvl;
	}

	public String getItViewConv() {
		return itViewConv;
	}

	public void setItViewConv(String itViewConv) {
		this.itViewConv = itViewConv;
	}

	public String getItViewLodge() {
		return itViewLodge;
	}

	public void setItViewLodge(String itViewLodge) {
		this.itViewLodge = itViewLodge;
	}

	public String getPanelFlag() {
		return panelFlag;
	}

	public void setPanelFlag(String panelFlag) {
		this.panelFlag = panelFlag;
	}

	public String getRetrnFlag() {
		return retrnFlag;
	}

	public void setRetrnFlag(String retrnFlag) {
		this.retrnFlag = retrnFlag;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getSelectname() {
		return selectname;
	}

	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	public ArrayList getSubSchdlrList() {
		return subSchdlrList;
	}

	public void setSubSchdlrList(ArrayList subSchdlrList) {
		this.subSchdlrList = subSchdlrList;
	}

	public String getItEmployee() {
		return itEmployee;
	}

	public void setItEmployee(String itEmployee) {
		this.itEmployee = itEmployee;
	}

	public String getItEmployeeCode() {
		return itEmployeeCode;
	}

	public void setItEmployeeCode(String itEmployeeCode) {
		this.itEmployeeCode = itEmployeeCode;
	}

	public String getItEmployeeToken() {
		return itEmployeeToken;
	}

	public void setItEmployeeToken(String itEmployeeToken) {
		this.itEmployeeToken = itEmployeeToken;
	}

	 

	public String getAppFlag() {
		return appFlag;
	}

	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}

	public String getHidAppFlag() {
		return hidAppFlag;
	}

	public void setHidAppFlag(String hidAppFlag) {
		this.hidAppFlag = hidAppFlag;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMainSchdlr() {
		return mainSchdlr;
	}

	public void setMainSchdlr(String mainSchdlr) {
		this.mainSchdlr = mainSchdlr;
	}

	public String getMainSchdlrCode() {
		return mainSchdlrCode;
	}

	public void setMainSchdlrCode(String mainSchdlrCode) {
		this.mainSchdlrCode = mainSchdlrCode;
	}

	public String getAltMainSchdlrCode() {
		return altMainSchdlrCode;
	}

	public void setAltMainSchdlrCode(String altMainSchdlrCode) {
		this.altMainSchdlrCode = altMainSchdlrCode;
	}

	public String getAltMainSchdlr() {
		return altMainSchdlr;
	}

	public void setAltMainSchdlr(String altMainSchdlr) {
		this.altMainSchdlr = altMainSchdlr;
	}

	public String getSchdlrApprCode() {
		return schdlrApprCode;
	}

	public void setSchdlrApprCode(String schdlrApprCode) {
		this.schdlrApprCode = schdlrApprCode;
	}

	public String getSchdlrAppr() {
		return schdlrAppr;
	}

	public void setSchdlrAppr(String schdlrAppr) {
		this.schdlrAppr = schdlrAppr;
	}

	public String getAccntCode() {
		return accntCode;
	}

	public void setAccntCode(String accntCode) {
		this.accntCode = accntCode;
	}

	public String getAccnt() {
		return accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItBranch() {
		return itBranch;
	}

	public void setItBranch(String itBranch) {
		this.itBranch = itBranch;
	}

	public String getItBranchCode() {
		return itBranchCode;
	}

	public void setItBranchCode(String itBranchCode) {
		this.itBranchCode = itBranchCode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getItSchdlrAppr() {
		return itSchdlrAppr;
	}

	public void setItSchdlrAppr(String itSchdlrAppr) {
		this.itSchdlrAppr = itSchdlrAppr;
	}

	public String getItSchdlr() {
		return itSchdlr;
	}

	public void setItSchdlr(String itSchdlr) {
		this.itSchdlr = itSchdlr;
	}

	public String getItStatus() {
		return itStatus;
	}

	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}

	public String getConfChk() {
		return confChk;
	}

	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}

	public String getMainSchdlrToken() {
		return mainSchdlrToken;
	}

	public void setMainSchdlrToken(String mainSchdlrToken) {
		this.mainSchdlrToken = mainSchdlrToken;
	}

	public String getAltMainSchdlrToken() {
		return altMainSchdlrToken;
	}

	public void setAltMainSchdlrToken(String altMainSchdlrToken) {
		this.altMainSchdlrToken = altMainSchdlrToken;
	}

	public String getSchdlrApprToken() {
		return schdlrApprToken;
	}

	public void setSchdlrApprToken(String schdlrApprToken) {
		this.schdlrApprToken = schdlrApprToken;
	}

	public String getAccntToken() {
		return accntToken;
	}

	public void setAccntToken(String accntToken) {
		this.accntToken = accntToken;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getItSchdlrCode() {
		return itSchdlrCode;
	}

	public void setItSchdlrCode(String itSchdlrCode) {
		this.itSchdlrCode = itSchdlrCode;
	}

	public String getItSchdlrApprCode() {
		return itSchdlrApprCode;
	}

	public void setItSchdlrApprCode(String itSchdlrApprCode) {
		this.itSchdlrApprCode = itSchdlrApprCode;
	}

	public String getItAccntCode() {
		return itAccntCode;
	}

	public void setItAccntCode(String itAccntCode) {
		this.itAccntCode = itAccntCode;
	}

	public String getItAltSchdlrCode() {
		return itAltSchdlrCode;
	}

	public void setItAltSchdlrCode(String itAltSchdlrCode) {
		this.itAltSchdlrCode = itAltSchdlrCode;
	}

	public String getDescCnt() {
		return descCnt;
	}

	public void setDescCnt(String descCnt) {
		this.descCnt = descCnt;
	}

	public ArrayList getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(ArrayList authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getHdeleteSub() {
		return hdeleteSub;
	}

	public void setHdeleteSub(String hdeleteSub) {
		this.hdeleteSub = hdeleteSub;
	}

	public String getItAccnt() {
		return itAccnt;
	}

	public void setItAccnt(String itAccnt) {
		this.itAccnt = itAccnt;
	}

	public String getItAuthCode() {
		return ItAuthCode;
	}

	public void setItAuthCode(String itAuthCode) {
		ItAuthCode = itAuthCode;
	}

	public String getHdeleteAuth() {
		return hdeleteAuth;
	}

	public void setHdeleteAuth(String hdeleteAuth) {
		this.hdeleteAuth = hdeleteAuth;
	}

	public String getMyHidden() {
		return myHidden;
	}

	public void setMyHidden(String myHidden) {
		this.myHidden = myHidden;
	}

	public String getConfChkAuth() {
		return confChkAuth;
	}

	public void setConfChkAuth(String confChkAuth) {
		this.confChkAuth = confChkAuth;
	}

	public String getCancelFlg() {
		return cancelFlg;
	}

	public void setCancelFlg(String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	public String getMangAuthCode() {
		return mangAuthCode;
	}

	public void setMangAuthCode(String mangAuthCode) {
		this.mangAuthCode = mangAuthCode;
	}

 

	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}

	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}

	public ArrayList getViewMangAuthList() {
		return viewMangAuthList;
	}

	public void setViewMangAuthList(ArrayList viewMangAuthList) {
		this.viewMangAuthList = viewMangAuthList;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getHidChkFlg() {
		return hidChkFlg;
	}

	public void setHidChkFlg(String hidChkFlg) {
		this.hidChkFlg = hidChkFlg;
	}

	public String getHiddenBranchId() {
		return hiddenBranchId;
	}

	public void setHiddenBranchId(String hiddenBranchId) {
		this.hiddenBranchId = hiddenBranchId;
	}

	public String getHiddenEmpId() {
		return hiddenEmpId;
	}

	public void setHiddenEmpId(String hiddenEmpId) {
		this.hiddenEmpId = hiddenEmpId;
	}

	public String getHiddenAppFlag() {
		return hiddenAppFlag;
	}

	public void setHiddenAppFlag(String hiddenAppFlag) {
		this.hiddenAppFlag = hiddenAppFlag;
	}

	public String getHidStatus() {
		return hidStatus;
	}

	public void setHidStatus(String hidStatus) {
		this.hidStatus = hidStatus;
	}

	public String getItAllBranch() {
		return itAllBranch;
	}

	public void setItAllBranch(String itAllBranch) {
		this.itAllBranch = itAllBranch;
	}

	public String getItAllBranchCode() {
		return itAllBranchCode;
	}

	public void setItAllBranchCode(String itAllBranchCode) {
		this.itAllBranchCode = itAllBranchCode;
	}

	public String getBranchMsg() {
		return branchMsg;
	}

	public void setBranchMsg(String branchMsg) {
		this.branchMsg = branchMsg;
	}

	public String getSubTabLength() {
		return subTabLength;
	}

	public void setSubTabLength(String subTabLength) {
		this.subTabLength = subTabLength;
	}

	public ArrayList getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList branchList) {
		this.branchList = branchList;
	}

	public boolean isShowBranchFlag() {
		return showBranchFlag;
	}

	public void setShowBranchFlag(boolean showBranchFlag) {
		this.showBranchFlag = showBranchFlag;
	}

	public String getHidTabEmpId() {
		return hidTabEmpId;
	}

	public void setHidTabEmpId(String hidTabEmpId) {
		this.hidTabEmpId = hidTabEmpId;
	}

	public String getHidDeleteEmpId() {
		return hidDeleteEmpId;
	}

	public void setHidDeleteEmpId(String hidDeleteEmpId) {
		this.hidDeleteEmpId = hidDeleteEmpId;
	}
	

	public String getTestBranchCode() {
		return testBranchCode;
	}

	public void setTestBranchCode(String testBranchCode) {
		this.testBranchCode = testBranchCode;
	}

	public String getMyHiddenEmpCode() {
		return myHiddenEmpCode;
	}

	public void setMyHiddenEmpCode(String myHiddenEmpCode) {
		this.myHiddenEmpCode = myHiddenEmpCode;
	}

	public boolean isNoDataSch() {
		return noDataSch;
	}

	public void setNoDataSch(boolean noDataSch) {
		this.noDataSch = noDataSch;
	}

	public String getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getAlterAccountant() {
		return alterAccountant;
	}

	public void setAlterAccountant(String alterAccountant) {
		this.alterAccountant = alterAccountant;
	}

	public String getAlterAccountantCode() {
		return alterAccountantCode;
	}

	public void setAlterAccountantCode(String alterAccountantCode) {
		this.alterAccountantCode = alterAccountantCode;
	}

	public String getAlterAccountantToken() {
		return alterAccountantToken;
	}

	public void setAlterAccountantToken(String alterAccountantToken) {
		this.alterAccountantToken = alterAccountantToken;
	}

	public String getItAltAccntCode() {
		return itAltAccntCode;
	}

	public void setItAltAccntCode(String itAltAccntCode) {
		this.itAltAccntCode = itAltAccntCode;
	}

	public String getEscalationEmployee() {
		return escalationEmployee;
	}

	public void setEscalationEmployee(String escalationEmployee) {
		this.escalationEmployee = escalationEmployee;
	}

	public String getEscalationEmployeeCode() {
		return escalationEmployeeCode;
	}

	public void setEscalationEmployeeCode(String escalationEmployeeCode) {
		this.escalationEmployeeCode = escalationEmployeeCode;
	}

	public String getEscalationEmployeeToken() {
		return escalationEmployeeToken;
	}

	public void setEscalationEmployeeToken(String escalationEmployeeToken) {
		this.escalationEmployeeToken = escalationEmployeeToken;
	}

	public String getItEscalationEmployee() {
		return itEscalationEmployee;
	}

	public void setItEscalationEmployee(String itEscalationEmployee) {
		this.itEscalationEmployee = itEscalationEmployee;
	}

	public String getItEscalationEmployeeCode() {
		return itEscalationEmployeeCode;
	}

	public void setItEscalationEmployeeCode(String itEscalationEmployeeCode) {
		this.itEscalationEmployeeCode = itEscalationEmployeeCode;
	}

	public String getDescriptionNew() {
		return descriptionNew;
	}

	public void setDescriptionNew(String descriptionNew) {
		this.descriptionNew = descriptionNew;
	}

	public String getAccountantEmailID() {
		return accountantEmailID;
	}

	public void setAccountantEmailID(String accountantEmailID) {
		this.accountantEmailID = accountantEmailID;
	}

	public String getAccountantemployee() {
		return accountantemployee;
	}

	public void setAccountantemployee(String accountantemployee) {
		this.accountantemployee = accountantemployee;
	}

	public String getAccountantemployeeCode() {
		return accountantemployeeCode;
	}

	public void setAccountantemployeeCode(String accountantemployeeCode) {
		this.accountantemployeeCode = accountantemployeeCode;
	}

	public String getAccountantemployeeToken() {
		return accountantemployeeToken;
	}

	public void setAccountantemployeeToken(String accountantemployeeToken) {
		this.accountantemployeeToken = accountantemployeeToken;
	}

	public ArrayList getSubAccountantList() {
		return subAccountantList;
	}

	public void setSubAccountantList(ArrayList subAccountantList) {
		this.subAccountantList = subAccountantList;
	}

	public String getSrNoAcc() {
		return srNoAcc;
	}

	public void setSrNoAcc(String srNoAcc) {
		this.srNoAcc = srNoAcc;
	}

	public String getItEmployeeTokenAcc() {
		return itEmployeeTokenAcc;
	}

	public void setItEmployeeTokenAcc(String itEmployeeTokenAcc) {
		this.itEmployeeTokenAcc = itEmployeeTokenAcc;
	}

	public String getItEmployeeCodeAcc() {
		return itEmployeeCodeAcc;
	}

	public void setItEmployeeCodeAcc(String itEmployeeCodeAcc) {
		this.itEmployeeCodeAcc = itEmployeeCodeAcc;
	}

	public String getItEmployeeAcc() {
		return itEmployeeAcc;
	}

	public void setItEmployeeAcc(String itEmployeeAcc) {
		this.itEmployeeAcc = itEmployeeAcc;
	}

	public String getItAcknoledge() {
		return itAcknoledge;
	}

	public void setItAcknoledge(String itAcknoledge) {
		this.itAcknoledge = itAcknoledge;
	}

	public String getHiddenitAcknoledge() {
		return hiddenitAcknoledge;
	}

	public void setHiddenitAcknoledge(String hiddenitAcknoledge) {
		this.hiddenitAcknoledge = hiddenitAcknoledge;
	}

	public String getHiddenittClaim() {
		return hiddenittClaim;
	}

	public void setHiddenittClaim(String hiddenittClaim) {
		this.hiddenittClaim = hiddenittClaim;
	}

	public String getIttClaim() {
		return ittClaim;
	}

	public void setIttClaim(String ittClaim) {
		this.ittClaim = ittClaim;
	}

	public String getHiddenitAdvance() {
		return hiddenitAdvance;
	}

	public void setHiddenitAdvance(String hiddenitAdvance) {
		this.hiddenitAdvance = hiddenitAdvance;
	}

	public String getItAdvance() {
		return itAdvance;
	}

	public void setItAdvance(String itAdvance) {
		this.itAdvance = itAdvance;
	}

	public String getClaimwrkflowflag() {
		return claimwrkflowflag;
	}

	public void setClaimwrkflowflag(String claimwrkflowflag) {
		this.claimwrkflowflag = claimwrkflowflag;
	}

	public String getAckwrkflowflag() {
		return ackwrkflowflag;
	}

	public void setAckwrkflowflag(String ackwrkflowflag) {
		this.ackwrkflowflag = ackwrkflowflag;
	}

	public String getHiddenclaimwrkflowflag() {
		return hiddenclaimwrkflowflag;
	}

	public void setHiddenclaimwrkflowflag(String hiddenclaimwrkflowflag) {
		this.hiddenclaimwrkflowflag = hiddenclaimwrkflowflag;
	}

	public String getHiddenackwrkflowflag() {
		return hiddenackwrkflowflag;
	}

	public void setHiddenackwrkflowflag(String hiddenackwrkflowflag) {
		this.hiddenackwrkflowflag = hiddenackwrkflowflag;
	}

}
