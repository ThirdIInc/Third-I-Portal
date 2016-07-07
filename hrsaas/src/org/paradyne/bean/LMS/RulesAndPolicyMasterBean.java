package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RulesAndPolicyMasterBean extends BeanBase {

	private String rulesAndPolicyID="";
	private String committeeType="";
	private String categoryType = "";
	private String details= "";
	private String engFlag = "";
	private String hindiFlag = "";
	private String marathiFlag="";
	private String othersFlag= "";
	private String othersLanguage="";
	private String noticeBoardFlag="";
	private String circularFlag= "";
	private String othersMediumFlag = "";
	private String othersMedium = "";
	private String uploadLocFileName = "";
	
	ArrayList proofList;
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String checkRemoveUpload="";
	private String dataPath="";
	
	private String ittproofName="";
	
	private String name="";
	
	private String myPage="";
	private String modeLength ="";
	private String totalRecords ="";
	private String hiddenCode="";
	ArrayList<Object>  rulesAndPolicyList=null ;
	
	String eng="N"; //english
	String hin="N";//hindi
	String mar="N";//marathi
	String oth="N";//others
	
	String notice="N";//marathi
	String circular="N";//others
	String other="N";//others medium
	
	private String hidCommCode="";
	ArrayList ittUploadList;
	
	public ArrayList getIttUploadList() {
		return ittUploadList;
	}

	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getHin() {
		return hin;
	}

	public void setHin(String hin) {
		this.hin = hin;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getHidCommCode() {
		return hidCommCode;
	}

	public void setHidCommCode(String hidCommCode) {
		this.hidCommCode = hidCommCode;
	}

	public ArrayList<Object> getRulesAndPolicyList() {
		return rulesAndPolicyList;
	}

	public void setRulesAndPolicyList(ArrayList<Object> rulesAndPolicyList) {
		this.rulesAndPolicyList = rulesAndPolicyList;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getHiddenCode() {
		return hiddenCode;
	}

	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRulesAndPolicyID() {
		return rulesAndPolicyID;
	}

	public void setRulesAndPolicyID(String rulesAndPolicyID) {
		this.rulesAndPolicyID = rulesAndPolicyID;
	}

	public String getCommitteeType() {
		return committeeType;
	}

	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEngFlag() {
		return engFlag;
	}

	public void setEngFlag(String engFlag) {
		this.engFlag = engFlag;
	}

	public String getHindiFlag() {
		return hindiFlag;
	}

	public void setHindiFlag(String hindiFlag) {
		this.hindiFlag = hindiFlag;
	}

	public String getMarathiFlag() {
		return marathiFlag;
	}

	public void setMarathiFlag(String marathiFlag) {
		this.marathiFlag = marathiFlag;
	}

	public String getOthersFlag() {
		return othersFlag;
	}

	public void setOthersFlag(String othersFlag) {
		this.othersFlag = othersFlag;
	}

	public String getOthersLanguage() {
		return othersLanguage;
	}

	public void setOthersLanguage(String othersLanguage) {
		this.othersLanguage = othersLanguage;
	}

	public String getNoticeBoardFlag() {
		return noticeBoardFlag;
	}

	public void setNoticeBoardFlag(String noticeBoardFlag) {
		this.noticeBoardFlag = noticeBoardFlag;
	}

	public String getCircularFlag() {
		return circularFlag;
	}

	public void setCircularFlag(String circularFlag) {
		this.circularFlag = circularFlag;
	}

	public String getOthersMediumFlag() {
		return othersMediumFlag;
	}

	public void setOthersMediumFlag(String othersMediumFlag) {
		this.othersMediumFlag = othersMediumFlag;
	}

	public String getOthersMedium() {
		return othersMedium;
	}

	public void setOthersMedium(String othersMedium) {
		this.othersMedium = othersMedium;
	}

	public String getUploadLocFileName() {
		return uploadLocFileName;
	}

	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
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

	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}

	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getIttproofName() {
		return ittproofName;
	}

	public void setIttproofName(String ittproofName) {
		this.ittproofName = ittproofName;
	}

	public String getOth() {
		return oth;
	}

	public void setOth(String oth) {
		this.oth = oth;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getCircular() {
		return circular;
	}

	public void setCircular(String circular) {
		this.circular = circular;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	
	
	
	
	
}
