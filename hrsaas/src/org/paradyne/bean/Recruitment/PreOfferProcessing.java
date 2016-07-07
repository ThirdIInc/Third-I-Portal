package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.lib.BeanBase;

public class PreOfferProcessing extends BeanBase {
	
	private String bgcheckCode;
	private String candidateName; 
	private String candidateCode;
	private String offerStatus;
	private String reqCode;
	private String reqName;
	private String division;
	private String branch;
	private String department;
	private String position;
	private String bgCheckType="I";
	private String DupbgCheckType;
	private String outsourceAgencyName;
	private String outsourceAgencyCode;
	private String checkListType;
	private String DupcheckListType;
	
	private String checkListCode;
	private String overallComments;
	private boolean checklistTable;
	private String noData;
	
	private String hiddenstatus;
	
	//Check List Details  
	
	private String checkListresponce;
	private String DupcheckListresponce;
	
	private String checkListComments;
	private String checkListitemcode;
	private String checkListName;
	
	
	private String Lcandidate;
	private String Lcancode;
	private String Loffercode;
	private String Lreqcode;
	
	private String LreqName;
	private String Lposition;
	private String Lofferstatus;
	private String LchecklistType;
	
	private String bgStatus;
	private String conduct;
	
	private String resume;
	
	ArrayList BgpendingChkList=null;
	ArrayList BgconductChkList=null;
	
	ArrayList ChkList=null;      
	private String chkLength;
	private String bgpendinglistLength;
	private String bgconductlistLength;
	private String Chkreqcode;
	private String Chkoffercode;  
	private String Chkcandidatecode;
	
	private boolean onLoadFlag=false;
	
	private boolean flag=false;
	
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	private boolean conductflag=true;
	 
	private String candiFlag="false";
	
	private String uploadLocFileName = "";
	private String uploadFlag = "false";
	private String checkRemoveUpload="";
	ArrayList ittUploadList;
	ArrayList proofList;
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String dataPath="";
	
	
	private String srNo = "";
	
	/**
	 * Flags For Cancel Button
	 */
	
	
	
	
	private String buttonpanelcode;
	
	
	private String myPage;
	private String show;
	private String selectname;
	
	
	
	
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
	public String getButtonpanelcode() {
		return buttonpanelcode;
	}
	public void setButtonpanelcode(String buttonpanelcode) {
		this.buttonpanelcode = buttonpanelcode;
	}
	/* Candidate Name 	
     Requisition Code 	
     Position 	
     Offer Status 	
     Check List Type*/
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBgCheckType() {
		return bgCheckType;
	}
	public void setBgCheckType(String bgCheckType) {
		this.bgCheckType = bgCheckType;
	}
	public String getOutsourceAgencyName() {
		return outsourceAgencyName;
	}
	public void setOutsourceAgencyName(String outsourceAgencyName) {
		this.outsourceAgencyName = outsourceAgencyName;
	}
	public String getOutsourceAgencyCode() {
		return outsourceAgencyCode;
	}
	public void setOutsourceAgencyCode(String outsourceAgencyCode) {
		this.outsourceAgencyCode = outsourceAgencyCode;
	}
	public String getCheckListType() {
		return checkListType;
	}
	public void setCheckListType(String checkListType) {
		this.checkListType = checkListType;
	}
	public String getCheckListCode() {
		return checkListCode;
	}
	public void setCheckListCode(String checkListCode) {
		this.checkListCode = checkListCode;
	}
	public String getOverallComments() {
		return overallComments;
	}
	public void setOverallComments(String overallComments) {
		this.overallComments = overallComments;
	}
	public String getCheckListresponce() {
		return checkListresponce;
	}
	public void setCheckListresponce(String checkListresponce) {
		this.checkListresponce = checkListresponce;
	}
	public String getCheckListComments() {
		return checkListComments;
	}
	public void setCheckListComments(String checkListComments) {
		this.checkListComments = checkListComments;
	}
	public String getCheckListitemcode() {
		return checkListitemcode;
	}
	public void setCheckListitemcode(String checkListitemcode) {
		this.checkListitemcode = checkListitemcode;
	}
	public String getCheckListName() {
		return checkListName;
	}
	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}
	public ArrayList getChkList() {
		return ChkList;
	}
	public void setChkList(ArrayList chkList) {
		ChkList = chkList;
	}
	public String getChkLength() {
		return chkLength;
	}
	public void setChkLength(String chkLength) {
		this.chkLength = chkLength;
	}
	public String getBgcheckCode() {
		return bgcheckCode;
	}
	public void setBgcheckCode(String bgcheckCode) {
		this.bgcheckCode = bgcheckCode;
	}
	public String getLcandidate() {
		return Lcandidate;
	}
	public void setLcandidate(String lcandidate) {
		Lcandidate = lcandidate;
	}
	public String getLreqcode() {
		return Lreqcode;
	}
	public void setLreqcode(String lreqcode) {
		Lreqcode = lreqcode;
	}
	public String getLposition() {
		return Lposition;
	}
	public void setLposition(String lposition) {
		Lposition = lposition;
	}
	public String getLofferstatus() {
		return Lofferstatus;
	}
	public void setLofferstatus(String lofferstatus) {
		Lofferstatus = lofferstatus;
	}
	public String getLchecklistType() {
		return LchecklistType;
	}
	public void setLchecklistType(String lchecklistType) {
		LchecklistType = lchecklistType;
	}
	public ArrayList getBgpendingChkList() {
		return BgpendingChkList;
	}
	public void setBgpendingChkList(ArrayList bgpendingChkList) {
		BgpendingChkList = bgpendingChkList;
	}
	public ArrayList getBgconductChkList() {
		return BgconductChkList;
	}
	public void setBgconductChkList(ArrayList bgconductChkList) {
		BgconductChkList = bgconductChkList;
	}
	public String getBgpendinglistLength() {
		return bgpendinglistLength;
	}
	public void setBgpendinglistLength(String bgpendinglistLength) {
		this.bgpendinglistLength = bgpendinglistLength;
	}
	public String getBgconductlistLength() {
		return bgconductlistLength;
	}
	public void setBgconductlistLength(String bgconductlistLength) {
		this.bgconductlistLength = bgconductlistLength;
	}
	public String getBgStatus() {
		return bgStatus;
	}
	public void setBgStatus(String bgStatus) {
		this.bgStatus = bgStatus;
	}
	public String getLreqName() {
		return LreqName;
	}
	public void setLreqName(String lreqName) {
		LreqName = lreqName;
	}
	public String getLoffercode() {
		return Loffercode;
	}
	public void setLoffercode(String loffercode) {
		Loffercode = loffercode;
	}
	public String getChkreqcode() {
		return Chkreqcode;
	}
	public void setChkreqcode(String chkreqcode) {
		Chkreqcode = chkreqcode;
	}
	public String getChkoffercode() {
		return Chkoffercode;
	}
	public void setChkoffercode(String chkoffercode) {
		Chkoffercode = chkoffercode;
	}
	public String getLcancode() {
		return Lcancode;
	}
	public void setLcancode(String lcancode) {
		Lcancode = lcancode;
	}
	public String getChkcandidatecode() {
		return Chkcandidatecode;
	}
	public void setChkcandidatecode(String chkcandidatecode) {
		Chkcandidatecode = chkcandidatecode;
	}
	public String getConduct() {
		return conduct;
	}
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public boolean isDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	
	public boolean isConductflag() {
		return conductflag;
	}
	public void setConductflag(boolean conductflag) {
		this.conductflag = conductflag;
	}
	public boolean isChecklistTable() {
		return checklistTable;
	}
	public void setChecklistTable(boolean checklistTable) {
		this.checklistTable = checklistTable;
	}
	public String getDupcheckListType() {
		return DupcheckListType;
	}
	public void setDupcheckListType(String dupcheckListType) {
		DupcheckListType = dupcheckListType;
	}
	public String getDupcheckListresponce() {
		return DupcheckListresponce;
	}
	public void setDupcheckListresponce(String dupcheckListresponce) {
		DupcheckListresponce = dupcheckListresponce;
	}
	public String getDupbgCheckType() {
		return DupbgCheckType;
	}
	public void setDupbgCheckType(String dupbgCheckType) {
		DupbgCheckType = dupbgCheckType;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getHiddenstatus() {
		return hiddenstatus;
	}
	public void setHiddenstatus(String hiddenstatus) {
		this.hiddenstatus = hiddenstatus;
	}
	public String getCandiFlag() {
		return candiFlag;
	}
	public void setCandiFlag(String candiFlag) {
		this.candiFlag = candiFlag;
	}
	public String getUploadLocFileName() {
		return uploadLocFileName;
	}
	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}
	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}
	public ArrayList getIttUploadList() {
		return ittUploadList;
	}
	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}
	public ArrayList getProofList() {
		return proofList;
	}
	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}
	public String getProofSrNo() {
		return proofSrNo;
	}
	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}
	public String getProofName() {
		return proofName;
	}
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	
	
}
