package org.paradyne.bean.settings;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**@purpose : This form is used to Save or Edit HomePage Dashlet Information
 * 			  HomePage Setting Link 
 * @Modified By AA1711 - TO view that Records According to Applicable 
 * 						Division Wise on Dashlet 
 * @Date 22-Jan-2013
 */
public class SettingMaster extends BeanBase {
	
	
	private String forgotPassQuestion ="";
	private String linkNameHr;
	private String uploadHr;
	private String linkNameCs;
	private String uploadCs;
	private String linkNameKs;
	private String uploadKs;
	private String linkNameCms;
	private String uploadCms;
	private String question;
	private String pollCode;
	private String option;
	private String expiry;
	private ArrayList<Object> list;
	private String optionList;
	private String hiddenOptionCode;
	private String optionCode;
	private String thougth;
	private String hiddenCode_th;
	private String linkNameBbs;
	private String uploadBbs;
	private String linkHr;
	private String linkCs;
	private String linkKs;
	private String linkCms;
	private String linkBbs;
	private String genType;
	private String linkNameGs;
	private String uploadGs;
	private String linkGs;
	private String type_Gs;
	//private String authChk;
	private String optionColor;
	private String color;
	private String hiddenDivId;
	
	private String linkNameQl;
	private String uploadQl;
	private String linkQl;
	
	private String checkFlag_hr = "true";
	private String checkFlag_cs = "true";
	private String checkFlag_ks = "true";
	private String checkFlag_cms = "true";
	private String checkFlag_Bbs = "true";
	private String checkFlag_Ql = "true";
	private String checkFlag_gs = "true";
	
	//Check Box variables
	private String holidayCheck;
	private String birthdayCheck;
	private String newjoineeCheck;
	private String addressCheck;
	private String chkBbs;
	private String link_Bbs;
	private String uploadDoc_Bbs;
	private String checkGeneral;
//	private String pbSmtp;
	
	//Iterator Bean For Thought
	private String thougthCode;
	private String thougthName;
	private String thougthDate;
	
	//Iterator bean for HR Settings
	private String linkCode_Hr;
	private String linkName_Hr;
	private String linkFile_Hr;
	
	//Iterator bean for Corporate Settings
	private String linkCode_Cs;
	private String linkName_Cs;
	private String linkFile_Cs;
	private String linkActive_Cs;
	private String linkDivision_Cs="";
	
	//Iterator bean for Knowledge Settings
	private String linkCode_Ks;
	private String linkName_Ks;
	private String linkFile_Ks;
	private String linkActive_Ks;
	
	//Iterator bean for General Settings
	private String linkCode_Gs;
	private String linkName_Gs;
	private String linkFile_Gs;
	
	//Iterator bean for company Settings
	private String linkCode_Cms;
	private String linkName_Cms;
	private String linkFile_Cms;
	private String linkActive_Cms;
	
	//Iterator bean for Bulletin Board Settings
	private String linkCode_Bbs;
	private String linkName_Bbs;
	private String linkFile_Bbs;
	
	//Array List Objects for Iterators
	private ArrayList<Object> list_hrLink;
	private ArrayList<Object> list_csLink;
	private ArrayList<Object> list_ksLink;
	private ArrayList<Object> list_cmsLink;
	private ArrayList<Object> list_thougth;
	private ArrayList<Object> list_BbsLink;
	private ArrayList<Object> list_QlLink;
	private ArrayList<Object> list_gsLink;
	private ArrayList<Object> list_EM;
	private ArrayList<Object> list_TipsLink;
	
	
	
	//Hidden Fields For Edit and Delete Functions
	private String hiddenCode_Hr;
	private String hiddenCode_Cs;
	private String hiddenCode_Ks;
	private String hiddenCode_Cms;
	private String hiddenCode_Bbs;
	private String hiddenCode_Ql;
	private String hiddenCode_Gs;
	private String hiddenCode_poll;
	
	// Category Fields for Knowledge
	private String knowCatNmSelect="";
	private String knowCatName="";
	private String catName_Ks="";
	private LinkedHashMap<String, String> knowMap =null ;
	
	
	// Fields for MailBox Configuration 
/*	
	private String mailServer;
	private String mailProtocol;
	private String mailPort;
	private String mailUsername;
	private String mailPasswd;
	
	
	private String mailServerOut;
	private String mailProtocolOut;
	private String mailPortOut;
	private String mailUsernameOut;
	private String mailPasswdOut;
*/	
	
	//Iterator bean for Quick Link Settings
	private String linkCode_Ql;
	private String linkName_Ql;
	private String linkFile_Ql;
	
	//Iterator for TIPS Link Settings Added By Nilesh Dhandare on 17th Jan 2012.
	private String linkCode_TL;
	private String linkName_TL;
	private String tipsName;
	private String hiddenCode_TS;
	private String uploadTs;
	private String linkTs;
	private String activeTip;
	private String divisionCode;
	private String divisionName;
	private String linkFile_TL;
	private String linkActive_TL;
	private boolean divFlag_TS = false;
	private String checkFlag_TS = "true";
	

	// bean fields for Email Info
	private String linkCode_EM;
	private String linkName_EM;
	private String Check_EM;
	private String chkemail;

	
	private String checkHr ;
	private String checkCorp ;
	private String checkKnow;
	private String checkComp;
	private String checkBulletin;
	private String checkQuick;
	
	//Check Box for Quick and General
	private String chkGeneral;
	private String chkQlink;
	private String pmChkFlag;
	private String Check_G;
	private String Check_Q;
	
	//Check Box PasswordSetting
	private String expFlag;
	private String expPeriodicity;
	private String reuseFlag;
	private String reusePassPed;
	private String lockFlag;
	private String lockPrd;
	private String lockAttmpt;
	
	//Variables for System Email 
/*	
	private String sysEmail;
	private String sysEmailPsswd;
	private String hiddenMailCode;
	private String sysMailCode;
	private String sysEmailId;
	private ArrayList<Object> sysMailList;
*/
	
	//VARIABLES FOR PASSWORD SETTING FLAGS
	private String includeAlpha;
	private String includeSpChar;
	private String includeNum;
	private String includeUpCase;
	private String minPwdLen;
	private String maxPwdLen;
	
	//Flags for Showing the division
	private boolean divFlag_CR = false;
	private boolean divFlag_KN = false;
	private boolean divFlag_GE = false;
	private boolean divFlag_CM = false;
	private boolean divFlag_PO = false;
	private boolean divFlag_TH = false;
	private boolean divFlag_ML = false;
	private boolean divFlag_QL = false;
	private boolean divFlag_EM = false;
	private boolean divFlag_PS = false;
	private String virtualKey="";
	private String enforceKey="";
	private String enableTxtImg=""; 
	private String secureQue="";
	
//	private String fromMailId=""; 
//	private String toMailId="";

	

/* Used variables for DivisionWise*/	
	private String applDivisionCode = "";
	private String applDivisionName = "";	
	private String knowDivCode ="";
	private String knowDivName="";		
	private String corpDivCode ="";
	private String corpDivName ="";	
	private String pollDivCode ="";
	private String pollDivName="";
	private String pollDate="";
	
	
	/**
	 * @return String
	 */
	public String getCorpDivCode() {
		return corpDivCode;
	}
	/**
	 * @param corpDivCode
	 */
	public void setCorpDivCode(String corpDivCode) {
		this.corpDivCode = corpDivCode;
	}
	/**
	 * @return String
	 */
	public String getCorpDivName() {
		return corpDivName;
	}
	/**
	 * @param corpDivName
	 */
	public void setCorpDivName(String corpDivName) {
		this.corpDivName = corpDivName;
	}
	/**
	 * @return String 
	 */
	public String getKnowDivCode() {
		return knowDivCode;
	}
	/**
	 * @param knowDivCode
	 */
	public void setKnowDivCode(String knowDivCode) {
		this.knowDivCode = knowDivCode;
	}
	/**
	 * @return String 
	 */
	public String getKnowDivName() {
		return knowDivName;
	}
	/**
	 * @param knowDivName
	 */
	public void setKnowDivName(String knowDivName) {
		this.knowDivName = knowDivName;
	}
	/**
	 * @return String
	 */
	public String getApplDivisionCode() {
		return applDivisionCode;
	}
	/**
	 * @param applDivisionCode
	 */
	public void setApplDivisionCode(String applDivisionCode) {
		this.applDivisionCode = applDivisionCode;
	}
	/**
	 * @return String
	 */
	public String getApplDivisionName() {
		return applDivisionName;
	}
	/**
	 * @param applDivisionName
	 */
	public void setApplDivisionName(String applDivisionName) {
		this.applDivisionName = applDivisionName;
	}
	public String getSecureQue() {
		return secureQue;
	}
	public void setSecureQue(String secureQue) {
		this.secureQue = secureQue;
	}
	public String getEnableTxtImg() {
		return enableTxtImg;
	}
	public void setEnableTxtImg(String enableTxtImg) {
		this.enableTxtImg = enableTxtImg;
	}
	
/*	
	public ArrayList<Object> getSysMailList() {
		return sysMailList;
	}
	public void setSysMailList(ArrayList<Object> sysMailList) {
		this.sysMailList = sysMailList;
	}
	public String getSysEmail() {
		return sysEmail;
	}
	public void setSysEmail(String sysEmail) {
		this.sysEmail = sysEmail;
	}
	public String getSysEmailPsswd() {
		return sysEmailPsswd;
	}
	public void setSysEmailPsswd(String sysEmailPsswd) {
		this.sysEmailPsswd = sysEmailPsswd;
	}
	public String getHiddenMailCode() {
		return hiddenMailCode;
	}
	public void setHiddenMailCode(String hiddenMailCode) {
		this.hiddenMailCode = hiddenMailCode;
	}
	public String getSysMailCode() {
		return sysMailCode;
	}
	public void setSysMailCode(String sysMailCode) {
		this.sysMailCode = sysMailCode;
	}
	public String getSysEmailId() {
		return sysEmailId;
	}
	public void setSysEmailId(String sysEmailId) {
		this.sysEmailId = sysEmailId;
	}
*/
	
	public String getCheck_G() {
		return Check_G;
	}
	public void setCheck_G(String check_G) {
		Check_G = check_G;
	}
	public String getCheck_Q() {
		return Check_Q;
	}
	public void setCheck_Q(String check_Q) {
		Check_Q = check_Q;
	}
	public String getPmChkFlag() {
		return pmChkFlag;
	}
	public void setPmChkFlag(String pmChkFlag) {
		this.pmChkFlag = pmChkFlag;
	}
	public String getCheckHr() {
		return checkHr;
	}
	public void setCheckHr(String checkHr) {
		this.checkHr = checkHr;
	}
	public String getCheckCorp() {
		return checkCorp;
	}
	public void setCheckCorp(String checkCorp) {
		this.checkCorp = checkCorp;
	}
	public String getCheckKnow() {
		return checkKnow;
	}
	public void setCheckKnow(String checkKnow) {
		this.checkKnow = checkKnow;
	}
	public String getCheckComp() {
		return checkComp;
	}
	public void setCheckComp(String checkComp) {
		this.checkComp = checkComp;
	}
	public String getCheckBulletin() {
		return checkBulletin;
	}
	public void setCheckBulletin(String checkBulletin) {
		this.checkBulletin = checkBulletin;
	}
	public String getCheckQuick() {
		return checkQuick;
	}
	public void setCheckQuick(String checkQuick) {
		this.checkQuick = checkQuick;
	}
	public ArrayList<Object> getList_csLink() {
		return list_csLink;
	}
	public void setList_csLink(ArrayList<Object> list_csLink) {
		this.list_csLink = list_csLink;
	}
	public ArrayList<Object> getList_ksLink() {
		return list_ksLink;
	}
	public void setList_ksLink(ArrayList<Object> list_ksLink) {
		this.list_ksLink = list_ksLink;
	}
	public ArrayList<Object> getList_cmsLink() {
		return list_cmsLink;
	}
	public void setList_cmsLink(ArrayList<Object> list_cmsLink) {
		this.list_cmsLink = list_cmsLink;
	}
	public ArrayList<Object> getList_hrLink() {
		return list_hrLink;
	}
	public void setList_hrLink(ArrayList<Object> list_hrLink) {
		this.list_hrLink = list_hrLink;
	}
	public String getLinkCode_Hr() {
		return linkCode_Hr;
	}
	public void setLinkCode_Hr(String linkCode_Hr) {
		this.linkCode_Hr = linkCode_Hr;
	}
	public String getLinkName_Hr() {
		return linkName_Hr;
	}
	public void setLinkName_Hr(String linkName_Hr) {
		this.linkName_Hr = linkName_Hr;
	}
	public String getLinkFile_hr() {
		return linkFile_Hr;
	}
	public void setLinkFile_hr(String linkFile_Hr) {
		this.linkFile_Hr = linkFile_Hr;
	}
	public String getHiddenOptionCode() {
		return hiddenOptionCode;
	}
	public void setHiddenOptionCode(String hiddenOptionCode) {
		this.hiddenOptionCode = hiddenOptionCode;
	}
	public String getOptionList() {
		return optionList;
	}
	public void setOptionList(String optionList) {
		this.optionList = optionList;
	}
	public String getLinkNameHr() {
		return linkNameHr;
	}
	public void setLinkNameHr(String linkNameHr) {
		this.linkNameHr = linkNameHr;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPollCode() {
		return pollCode;
	}
	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public ArrayList<Object> getList() {
		return list;
	}
	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	public String getUploadHr() {
		return uploadHr;
	}
	public void setUploadHr(String uploadHr) {
		this.uploadHr = uploadHr;
	}
	public String getLinkNameCms() {
		return linkNameCms;
	}
	public void setLinkNameCms(String linkNameCms) {
		this.linkNameCms = linkNameCms;
	}
	public String getUploadCms() {
		return uploadCms;
	}
	public void setUploadCms(String uploadCms) {
		this.uploadCms = uploadCms;
	}
	public String getLinkNameCs() {
		return linkNameCs;
	}
	public void setLinkNameCs(String linkNameCs) {
		this.linkNameCs = linkNameCs;
	}
	public String getUploadCs() {
		return uploadCs;
	}
	public void setUploadCs(String uploadCs) {
		this.uploadCs = uploadCs;
	}
	public String getLinkNameKs() {
		return linkNameKs;
	}
	public void setLinkNameKs(String linkNameKs) {
		this.linkNameKs = linkNameKs;
	}
	public String getUploadKs() {
		return uploadKs;
	}
	public void setUploadKs(String uploadKs) {
		this.uploadKs = uploadKs;
	}
	public String getLinkFile_Hr() {
		return linkFile_Hr;
	}
	public void setLinkFile_Hr(String linkFile_Hr) {
		this.linkFile_Hr = linkFile_Hr;
	}
	public String getLinkCode_Cs() {
		return linkCode_Cs;
	}
	public void setLinkCode_Cs(String linkCode_Cs) {
		this.linkCode_Cs = linkCode_Cs;
	}
	public String getLinkName_Cs() {
		return linkName_Cs;
	}
	public void setLinkName_Cs(String linkName_Cs) {
		this.linkName_Cs = linkName_Cs;
	}
	public String getLinkFile_Cs() {
		return linkFile_Cs;
	}
	public void setLinkFile_Cs(String linkFile_Cs) {
		this.linkFile_Cs = linkFile_Cs;
	}
	public String getLinkCode_Ks() {
		return linkCode_Ks;
	}
	public void setLinkCode_Ks(String linkCode_Ks) {
		this.linkCode_Ks = linkCode_Ks;
	}
	public String getLinkName_Ks() {
		return linkName_Ks;
	}
	public void setLinkName_Ks(String linkName_Ks) {
		this.linkName_Ks = linkName_Ks;
	}
	public String getLinkFile_Ks() {
		return linkFile_Ks;
	}
	public void setLinkFile_Ks(String linkFile_Ks) {
		this.linkFile_Ks = linkFile_Ks;
	}
	public String getLinkCode_Cms() {
		return linkCode_Cms;
	}
	public void setLinkCode_Cms(String linkCode_Cms) {
		this.linkCode_Cms = linkCode_Cms;
	}
	public String getLinkName_Cms() {
		return linkName_Cms;
	}
	public void setLinkName_Cms(String linkName_Cms) {
		this.linkName_Cms = linkName_Cms;
	}
	public String getLinkFile_Cms() {
		return linkFile_Cms;
	}
	public void setLinkFile_Cms(String linkFile_Cms) {
		this.linkFile_Cms = linkFile_Cms;
	}
	public String getHiddenCode_Hr() {
		return hiddenCode_Hr;
	}
	public void setHiddenCode_Hr(String hiddenCode_Hr) {
		this.hiddenCode_Hr = hiddenCode_Hr;
	}
	public String getHiddenCode_Cs() {
		return hiddenCode_Cs;
	}
	public void setHiddenCode_Cs(String hiddenCode_Cs) {
		this.hiddenCode_Cs = hiddenCode_Cs;
	}
	public String getHiddenCode_Ks() {
		return hiddenCode_Ks;
	}
	public void setHiddenCode_Ks(String hiddenCode_Ks) {
		this.hiddenCode_Ks = hiddenCode_Ks;
	}
	public String getHiddenCode_Cms() {
		return hiddenCode_Cms;
	}
	public void setHiddenCode_Cms(String hiddenCode_Cms) {
		this.hiddenCode_Cms = hiddenCode_Cms;
	}
	public String getHolidayCheck() {
		return holidayCheck;
	}
	public void setHolidayCheck(String holidayCheck) {
		this.holidayCheck = holidayCheck;
	}
	public String getBirthdayCheck() {
		return birthdayCheck;
	}
	public void setBirthdayCheck(String birthdayCheck) {
		this.birthdayCheck = birthdayCheck;
	}
	public String getNewjoineeCheck() {
		return newjoineeCheck;
	}
	public void setNewjoineeCheck(String newjoineeCheck) {
		this.newjoineeCheck = newjoineeCheck;
	}
	public String getAddressCheck() {
		return addressCheck;
	}
	public void setAddressCheck(String addressCheck) {
		this.addressCheck = addressCheck;
	}
	public String getThougth() {
		return thougth;
	}
	public void setThougth(String thougth) {
		this.thougth = thougth;
	}
	public String getThougthCode() {
		return thougthCode;
	}
	public void setThougthCode(String thougthCode) {
		this.thougthCode = thougthCode;
	}
	public String getThougthName() {
		return thougthName;
	}
	public void setThougthName(String thougthName) {
		this.thougthName = thougthName;
	}
	public String getHiddenCode_th() {
		return hiddenCode_th;
	}
	public void setHiddenCode_th(String hiddenCode_th) {
		this.hiddenCode_th = hiddenCode_th;
	}
	public ArrayList<Object> getList_thougth() {
		return list_thougth;
	}
	public void setList_thougth(ArrayList<Object> list_thougth) {
		this.list_thougth = list_thougth;
	}
	/*
	public String getMailServer() {
		return mailServer;
	}
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	public String getMailProtocol() {
		return mailProtocol;
	}
	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPasswd() {
		return mailPasswd;
	}
	public void setMailPasswd(String mailPasswd) {
		this.mailPasswd = mailPasswd;
	}
	*/
	public String getLinkNameBbs() {
		return linkNameBbs;
	}
	public void setLinkNameBbs(String linkNameBbs) {
		this.linkNameBbs = linkNameBbs;
	}
	public String getUploadBbs() {
		return uploadBbs;
	}
	public void setUploadBbs(String uploadBbs) {
		this.uploadBbs = uploadBbs;
	}
	public String getChkBbs() {
		return chkBbs;
	}
	public void setChkBbs(String chkBbs) {
		this.chkBbs = chkBbs;
	}
	public String getLinkCode_Bbs() {
		return linkCode_Bbs;
	}
	public void setLinkCode_Bbs(String linkCode_Bbs) {
		this.linkCode_Bbs = linkCode_Bbs;
	}
	public String getLinkName_Bbs() {
		return linkName_Bbs;
	}
	public void setLinkName_Bbs(String linkName_Bbs) {
		this.linkName_Bbs = linkName_Bbs;
	}
	public String getLinkFile_Bbs() {
		return linkFile_Bbs;
	}
	public void setLinkFile_Bbs(String linkFile_Bbs) {
		this.linkFile_Bbs = linkFile_Bbs;
	}
	public ArrayList<Object> getList_BbsLink() {
		return list_BbsLink;
	}
	public void setList_BbsLink(ArrayList<Object> list_BbsLink) {
		this.list_BbsLink = list_BbsLink;
	}
	public String getHiddenCode_Bbs() {
		return hiddenCode_Bbs;
	}
	public void setHiddenCode_Bbs(String hiddenCode_Bbs) {
		this.hiddenCode_Bbs = hiddenCode_Bbs;
	}
	public String getLink_Bbs() {
		return link_Bbs;
	}
	public void setLink_Bbs(String link_Bbs) {
		this.link_Bbs = link_Bbs;
	}
	public String getUploadDoc_Bbs() {
		return uploadDoc_Bbs;
	}
	public void setUploadDoc_Bbs(String uploadDoc_Bbs) {
		this.uploadDoc_Bbs = uploadDoc_Bbs;
	}
	public String getLinkHr() {
		return linkHr;
	}
	public void setLinkHr(String linkHr) {
		this.linkHr = linkHr;
	}
	public String getLinkCs() {
		return linkCs;
	}
	public void setLinkCs(String linkCs) {
		this.linkCs = linkCs;
	}
	public String getLinkKs() {
		return linkKs;
	}
	public void setLinkKs(String linkKs) {
		this.linkKs = linkKs;
	}
	public String getLinkCms() {
		return linkCms;
	}
	public void setLinkCms(String linkCms) {
		this.linkCms = linkCms;
	}
	public String getLinkBbs() {
		return linkBbs;
	}
	public void setLinkBbs(String linkBbs) {
		this.linkBbs = linkBbs;
	}
	public String getThougthDate() {
		return thougthDate;
	}
	public void setThougthDate(String thougthDate) {
		this.thougthDate = thougthDate;
	}
	public String getCheckFlag_hr() {
		return checkFlag_hr;
	}
	public void setCheckFlag_hr(String checkFlag_hr) {
		this.checkFlag_hr = checkFlag_hr;
	}
	public String getCheckFlag_cs() {
		return checkFlag_cs;
	}
	public void setCheckFlag_cs(String checkFlag_cs) {
		this.checkFlag_cs = checkFlag_cs;
	}
	public String getCheckFlag_ks() {
		return checkFlag_ks;
	}
	public void setCheckFlag_ks(String checkFlag_ks) {
		this.checkFlag_ks = checkFlag_ks;
	}
	public String getCheckFlag_cms() {
		return checkFlag_cms;
	}
	public void setCheckFlag_cms(String checkFlag_cms) {
		this.checkFlag_cms = checkFlag_cms;
	}
	public String getCheckFlag_Bbs() {
		return checkFlag_Bbs;
	}
	public void setCheckFlag_Bbs(String checkFlag_Bbs) {
		this.checkFlag_Bbs = checkFlag_Bbs;
	}
	/*
	public String getMailServerOut() {
		return mailServerOut;
	}
	public void setMailServerOut(String mailServerOut) {
		this.mailServerOut = mailServerOut;
	}
	public String getMailProtocolOut() {
		return mailProtocolOut;
	}
	public void setMailProtocolOut(String mailProtocolOut) {
		this.mailProtocolOut = mailProtocolOut;
	}
	public String getMailPortOut() {
		return mailPortOut;
	}
	public void setMailPortOut(String mailPortOut) {
		this.mailPortOut = mailPortOut;
	}
	public String getMailUsernameOut() {
		return mailUsernameOut;
	}
	public void setMailUsernameOut(String mailUsernameOut) {
		this.mailUsernameOut = mailUsernameOut;
	}
	public String getMailPasswdOut() {
		return mailPasswdOut;
	}
	public void setMailPasswdOut(String mailPasswdOut) {
		this.mailPasswdOut = mailPasswdOut;
	}
*/	
	public String getLinkNameQl() {
		return linkNameQl;
	}
	public void setLinkNameQl(String linkNameQl) {
		this.linkNameQl = linkNameQl;
	}
	public String getUploadQl() {
		return uploadQl;
	}
	public void setUploadQl(String uploadQl) {
		this.uploadQl = uploadQl;
	}
	public String getLinkQl() {
		return linkQl;
	}
	public void setLinkQl(String linkQl) {
		this.linkQl = linkQl;
	}
	public String getCheckFlag_Ql() {
		return checkFlag_Ql;
	}
	public void setCheckFlag_Ql(String checkFlag_Ql) {
		this.checkFlag_Ql = checkFlag_Ql;
	}
	public ArrayList<Object> getList_QlLink() {
		return list_QlLink;
	}
	public void setList_QlLink(ArrayList<Object> list_QlLink) {
		this.list_QlLink = list_QlLink;
	}
	public String getHiddenCode_Ql() {
		return hiddenCode_Ql;
	}
	public void setHiddenCode_Ql(String hiddenCode_Ql) {
		this.hiddenCode_Ql = hiddenCode_Ql;
	}
	public String getLinkCode_Ql() {
		return linkCode_Ql;
	}
	public void setLinkCode_Ql(String linkCode_Ql) {
		this.linkCode_Ql = linkCode_Ql;
	}
	public String getLinkName_Ql() {
		return linkName_Ql;
	}
	public void setLinkName_Ql(String linkName_Ql) {
		this.linkName_Ql = linkName_Ql;
	}
	public String getLinkFile_Ql() {
		return linkFile_Ql;
	}
	public void setLinkFile_Ql(String linkFile_Ql) {
		this.linkFile_Ql = linkFile_Ql;
	}
	public String getGenType() {
		return genType;
	}
	public void setGenType(String genType) {
		this.genType = genType;
	}
	public String getLinkNameGs() {
		return linkNameGs;
	}
	public void setLinkNameGs(String linkNameGs) {
		this.linkNameGs = linkNameGs;
	}
	public String getUploadGs() {
		return uploadGs;
	}
	public void setUploadGs(String uploadGs) {
		this.uploadGs = uploadGs;
	}
	public String getLinkGs() {
		return linkGs;
	}
	public void setLinkGs(String linkGs) {
		this.linkGs = linkGs;
	}
	public String getCheckFlag_gs() {
		return checkFlag_gs;
	}
	public void setCheckFlag_gs(String checkFlag_gs) {
		this.checkFlag_gs = checkFlag_gs;
	}
	public String getCheckGeneral() {
		return checkGeneral;
	}
	public void setCheckGeneral(String checkGeneral) {
		this.checkGeneral = checkGeneral;
	}
	public String getLinkCode_Gs() {
		return linkCode_Gs;
	}
	public void setLinkCode_Gs(String linkCode_Gs) {
		this.linkCode_Gs = linkCode_Gs;
	}
	public String getLinkName_Gs() {
		return linkName_Gs;
	}
	public void setLinkName_Gs(String linkName_Gs) {
		this.linkName_Gs = linkName_Gs;
	}
	public String getLinkFile_Gs() {
		return linkFile_Gs;
	}
	public void setLinkFile_Gs(String linkFile_Gs) {
		this.linkFile_Gs = linkFile_Gs;
	}
	public ArrayList<Object> getList_gsLink() {
		return list_gsLink;
	}
	public void setList_gsLink(ArrayList<Object> list_gsLink) {
		this.list_gsLink = list_gsLink;
	}
	public String getHiddenCode_Gs() {
		return hiddenCode_Gs;
	}
	public void setHiddenCode_Gs(String hiddenCode_Gs) {
		this.hiddenCode_Gs = hiddenCode_Gs;
	}
	public String getType_Gs() {
		return type_Gs;
	}
	public void setType_Gs(String type_Gs) {
		this.type_Gs = type_Gs;
	}
	/*
	public String getAuthChk() {
		return authChk;
	}
	public void setAuthChk(String authChk) {
		this.authChk = authChk;
	}
	*/
	public String getChkGeneral() {
		return chkGeneral;
	}
	public void setChkGeneral(String chkGeneral) {
		this.chkGeneral = chkGeneral;
	}
	public String getChkQlink() {
		return chkQlink;
	}
	public void setChkQlink(String chkQlink) {
		this.chkQlink = chkQlink;
	}
	public String getLinkActive_Cs() {
		return linkActive_Cs;
	}
	public void setLinkActive_Cs(String linkActive_Cs) {
		this.linkActive_Cs = linkActive_Cs;
	}
	public String getLinkActive_Ks() {
		return linkActive_Ks;
	}
	public void setLinkActive_Ks(String linkActive_Ks) {
		this.linkActive_Ks = linkActive_Ks;
	}
	public String getLinkActive_Cms() {
		return linkActive_Cms;
	}
	public void setLinkActive_Cms(String linkActive_Cms) {
		this.linkActive_Cms = linkActive_Cms;
	}
	public String getLinkCode_EM() {
		return linkCode_EM;
	}
	public void setLinkCode_EM(String linkCode_EM) {
		this.linkCode_EM = linkCode_EM;
	}
	public String getLinkName_EM() {
		return linkName_EM;
	}
	public void setLinkName_EM(String linkName_EM) {
		this.linkName_EM = linkName_EM;
	}
	public String getCheck_EM() {
		return Check_EM;
	}
	public void setCheck_EM(String check_EM) {
		Check_EM = check_EM;
	}
	public String getChkemail() {
		return chkemail;
	}
	public void setChkemail(String chkemail) {
		this.chkemail = chkemail;
	}
	public ArrayList<Object> getList_EM() {
		return list_EM;
	}
	public void setList_EM(ArrayList<Object> list_EM) {
		this.list_EM = list_EM;
	}
	public String getOptionColor() {
		return optionColor;
	}
	public void setOptionColor(String optionColor) {
		this.optionColor = optionColor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getHiddenCode_poll() {
		return hiddenCode_poll;
	}
	public void setHiddenCode_poll(String hiddenCode_poll) {
		this.hiddenCode_poll = hiddenCode_poll;
	}

	public String getExpFlag() {
		return expFlag;
	}
	public void setExpFlag(String expFlag) {
		this.expFlag = expFlag;
	}
	public String getExpPeriodicity() {
		return expPeriodicity;
	}
	public void setExpPeriodicity(String expPeriodicity) {
		this.expPeriodicity = expPeriodicity;
	}
	public String getReuseFlag() {
		return reuseFlag;
	}
	public void setReuseFlag(String reuseFlag) {
		this.reuseFlag = reuseFlag;
	}
	public String getReusePassPed() {
		return reusePassPed;
	}
	public void setReusePassPed(String reusePassPed) {
		this.reusePassPed = reusePassPed;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getLockPrd() {
		return lockPrd;
	}
	public void setLockPrd(String lockPrd) {
		this.lockPrd = lockPrd;
	}
	public String getLockAttmpt() {
		return lockAttmpt;
	}
	public void setLockAttmpt(String lockAttmpt) {
		this.lockAttmpt = lockAttmpt;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getHiddenDivId() {
		return hiddenDivId;
	}
	public void setHiddenDivId(String hiddenDivId) {
		this.hiddenDivId = hiddenDivId;
	}
	public boolean isDivFlag_CR() {
		return divFlag_CR;
	}
	public void setDivFlag_CR(boolean divFlag_CR) {
		this.divFlag_CR = divFlag_CR;
	}
	public boolean isDivFlag_KN() {
		return divFlag_KN;
	}
	public void setDivFlag_KN(boolean divFlag_KN) {
		this.divFlag_KN = divFlag_KN;
	}
	public boolean isDivFlag_GE() {
		return divFlag_GE;
	}
	public void setDivFlag_GE(boolean divFlag_GE) {
		this.divFlag_GE = divFlag_GE;
	}
	public boolean isDivFlag_CM() {
		return divFlag_CM;
	}
	public void setDivFlag_CM(boolean divFlag_CM) {
		this.divFlag_CM = divFlag_CM;
	}
	public boolean isDivFlag_PO() {
		return divFlag_PO;
	}
	public void setDivFlag_PO(boolean divFlag_PO) {
		this.divFlag_PO = divFlag_PO;
	}
	public boolean isDivFlag_TH() {
		return divFlag_TH;
	}
	public void setDivFlag_TH(boolean divFlag_TH) {
		this.divFlag_TH = divFlag_TH;
	}
	public boolean isDivFlag_ML() {
		return divFlag_ML;
	}
	public void setDivFlag_ML(boolean divFlag_ML) {
		this.divFlag_ML = divFlag_ML;
	}
	public boolean isDivFlag_QL() {
		return divFlag_QL;
	}
	public void setDivFlag_QL(boolean divFlag_QL) {
		this.divFlag_QL = divFlag_QL;
	}
	public boolean isDivFlag_EM() {
		return divFlag_EM;
	}
	public void setDivFlag_EM(boolean divFlag_EM) {
		this.divFlag_EM = divFlag_EM;
	}
	public boolean isDivFlag_PS() {
		return divFlag_PS;
	}
	public void setDivFlag_PS(boolean divFlag_PS) {
		this.divFlag_PS = divFlag_PS;
	}
	public String getIncludeAlpha() {
		return includeAlpha;
	}
	public void setIncludeAlpha(String includeAlpha) {
		this.includeAlpha = includeAlpha;
	}
	public String getIncludeSpChar() {
		return includeSpChar;
	}
	public void setIncludeSpChar(String includeSpChar) {
		this.includeSpChar = includeSpChar;
	}
	public String getIncludeNum() {
		return includeNum;
	}
	public void setIncludeNum(String includeNum) {
		this.includeNum = includeNum;
	}
	public String getIncludeUpCase() {
		return includeUpCase;
	}
	public void setIncludeUpCase(String includeUpCase) {
		this.includeUpCase = includeUpCase;
	}
	public String getMinPwdLen() {
		return minPwdLen;
	}
	public void setMinPwdLen(String minPwdLen) {
		this.minPwdLen = minPwdLen;
	}
	public String getMaxPwdLen() {
		return maxPwdLen;
	}
	public void setMaxPwdLen(String maxPwdLen) {
		this.maxPwdLen = maxPwdLen;
	}
	public String getVirtualKey() {
		return virtualKey;
	}
	public void setVirtualKey(String virtualKey) {
		this.virtualKey = virtualKey;
	}
	public String getEnforceKey() {
		return enforceKey;
	}
	public void setEnforceKey(String enforceKey) {
		this.enforceKey = enforceKey;
	}
	/*
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String getToMailId() {
		return toMailId;
	}
	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}

	public String getPbSmtp() {
		return pbSmtp;
	}
	public void setPbSmtp(String pbSmtp) {
		this.pbSmtp = pbSmtp;
	}
*/
	public String getLinkDivision_Cs() {
		return linkDivision_Cs;
	}
	public void setLinkDivision_Cs(String linkDivision_Cs) {
		this.linkDivision_Cs = linkDivision_Cs;
	}
	public String getForgotPassQuestion() {
		return forgotPassQuestion;
	}
	public void setForgotPassQuestion(String forgotPassQuestion) {
		this.forgotPassQuestion = forgotPassQuestion;
	}
	/**
	 * @return the list_TipsLink
	 */
	public ArrayList<Object> getList_TipsLink() {
		return list_TipsLink;
	}
	/**
	 * @param list_TipsLink the list_TipsLink to set
	 */
	public void setList_TipsLink(ArrayList<Object> list_TipsLink) {
		this.list_TipsLink = list_TipsLink;
	}
	/**
	 * @return the linkCode_TL
	 */
	public String getLinkCode_TL() {
		return linkCode_TL;
	}
	/**
	 * @param linkCode_TL the linkCode_TL to set
	 */
	public void setLinkCode_TL(String linkCode_TL) {
		this.linkCode_TL = linkCode_TL;
	}
	/**
	 * @return the linkName_TL
	 */
	public String getLinkName_TL() {
		return linkName_TL;
	}
	/**
	 * @param linkName_TL the linkName_TL to set
	 */
	public void setLinkName_TL(String linkName_TL) {
		this.linkName_TL = linkName_TL;
	}
	
	/**
	 * @return the divFlag_TS
	 */
	public boolean isDivFlag_TS() {
		return divFlag_TS;
	}
	/**
	 * @param divFlag_TS the divFlag_TS to set
	 */
	public void setDivFlag_TS(boolean divFlag_TS) {
		this.divFlag_TS = divFlag_TS;
	}
	/**
	 * @return the checkFlag_TS
	 */
	public String getCheckFlag_TS() {
		return checkFlag_TS;
	}
	/**
	 * @param checkFlag_TS the checkFlag_TS to set
	 */
	public void setCheckFlag_TS(String checkFlag_TS) {
		this.checkFlag_TS = checkFlag_TS;
	}
	/**
	 * @return the tipsName
	 */
	public String getTipsName() {
		return tipsName;
	}
	/**
	 * @param tipsName the tipsName to set
	 */
	public void setTipsName(String tipsName) {
		this.tipsName = tipsName;
	}
	/**
	 * @return the hiddenCode_TS
	 */
	public String getHiddenCode_TS() {
		return hiddenCode_TS;
	}
	/**
	 * @param hiddenCode_TS the hiddenCode_TS to set
	 */
	public void setHiddenCode_TS(String hiddenCode_TS) {
		this.hiddenCode_TS = hiddenCode_TS;
	}
	/**
	 * @return the uploadTs
	 */
	public String getUploadTs() {
		return uploadTs;
	}
	/**
	 * @param uploadTs the uploadTs to set
	 */
	public void setUploadTs(String uploadTs) {
		this.uploadTs = uploadTs;
	}
	/**
	 * @return the linkTs
	 */
	public String getLinkTs() {
		return linkTs;
	}
	/**
	 * @param linkTs the linkTs to set
	 */
	public void setLinkTs(String linkTs) {
		this.linkTs = linkTs;
	}
	/**
	 * @return the activeTip
	 */
	public String getActiveTip() {
		return activeTip;
	}
	/**
	 * @param activeTip the activeTip to set
	 */
	public void setActiveTip(String activeTip) {
		this.activeTip = activeTip;
	}
	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	/**
	 * @return the linkFile_TL
	 */
	public String getLinkFile_TL() {
		return linkFile_TL;
	}
	/**
	 * @param linkFile_TL the linkFile_TL to set
	 */
	public void setLinkFile_TL(String linkFile_TL) {
		this.linkFile_TL = linkFile_TL;
	}
	/**
	 * @return the linkActive_T
	 */
	public String getLinkActive_TL() {
		return linkActive_TL;
	}
	/**
	 * @param linkActive_T the linkActive_T to set
	 */
	public void setLinkActive_TL(String linkActive_TL) {
		this.linkActive_TL = linkActive_TL;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	/**
	 * @param divisionName
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return String 
	 */
	public String getPollDivCode() {
		return pollDivCode;
	}
	/**
	 * @param pollDivCode
	 */
	public void setPollDivCode(String pollDivCode) {
		this.pollDivCode = pollDivCode;
	}
	/**
	 * @return String
	 */
	public String getPollDivName() {
		return pollDivName;
	}
	/**
	 * @param pollDivName
	 */
	public void setPollDivName(String pollDivName) {
		this.pollDivName = pollDivName;
	}
	/**
	 * @return String
	 */
	public String getKnowCatNmSelect() {
		return knowCatNmSelect;
	}
	/**
	 * @param knowCatNmSelect
	 */
	public void setKnowCatNmSelect(String knowCatNmSelect) {
		this.knowCatNmSelect = knowCatNmSelect;
	}
	/**
	 * @return String
	 */
	public String getKnowCatName() {
		return knowCatName;
	}
	/**
	 * @param knowCatName
	 */
	public void setKnowCatName(String knowCatName) {
		this.knowCatName = knowCatName;
	}
	public String getPollDate() {
		return pollDate;
	}
	public void setPollDate(String pollDate) {
		this.pollDate = pollDate;
	}
	public LinkedHashMap<String, String> getKnowMap() {
		return knowMap;
	}
	public void setKnowMap(LinkedHashMap<String, String> knowMap) {
		this.knowMap = knowMap;
	}
	public String getCatName_Ks() {
		return catName_Ks;
	}
	public void setCatName_Ks(String catName_Ks) {
		this.catName_Ks = catName_Ks;
	}
}
