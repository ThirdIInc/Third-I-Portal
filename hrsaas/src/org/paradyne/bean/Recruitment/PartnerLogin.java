package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase; 

import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class PartnerLogin extends BeanBase {

	String loginName = null;
	String loginPassword = null;
	String empId = null;
	String loginCode = null;
	String transcPass = null;
	String profileCode = "";
	private String visitorNo = "";
	private String loginDate = "";
	private String invalidUser = "";
	private String invalidFlag = "false";

	private String hostName = "";
	private String portNo = "";
	private String sendHostName = "";
	private String sendPortNo = "";
	private String authUser = "";
	private String authPwd = "";
	private String authChk = "";
	private String infoId = "";

	private String emailId = "";
	private String emailPwd = "";
	private int invalidCount = 1;

	// -------added by shashikant
	// SELECT PWD_EXPIRY_FLAG, PWD_EXPIRY_PERIODICITY, PWD_REUSE_FLAG,
	// PWD_REUSE_PERIODICITY,PWD_LOCK_FLAG,PWD_LOCK_PERIODICITY,PWD_LOCK_ATTEMPTS

	private String passExpireFlg = "";
	private String passExpirePrdcity = "";
	private String passReuseFlg = "";
	private String passReusePrdcity = "";
	private String passLockFlg = "";
	private String passLockPrdcity = "";
	private String passLockAttmts = "";
	private String expPassEmpName = "";
	private String expNewpsswd1 = "";
	private String txt = "";
	private String invalAttmpCount="";

	/**
	 * for forgot password added by Krishna
	 * 
	 */

	public String userNamefg;
	public String emailIdfg;
	public String empIdfg;
	private String partnerUserNameForgot=""; 
	
	private String hidCaptchaID="";
	/**
	 * added by vishwambhar 
	 */
	private String isKeyBoardShow="false";
	private String imgTxtFlag="false";
	private String passFlag="false";
	private String loginFlag="false";
	private String imgName="";
	private String userText="";
	private String userMsg="";
	private String userName="";
	private String oldpssword="";
	private String newpassword="";
	private String secureQue1="";
	private String secureQue2="";
	private String secureQue3="";
	private String secureAns1="";
	private String secureAns2="";
	private String secureAns3="";
	private String confirmpass="";
	private String forgotPassFlag="false";
	TreeMap tmap;
	TreeMap qmap;
	TreeMap quesmap;
	TreeMap forgotQuemap;
	
	private String forgotPassQue="";
	private String forgotPassQueCode="";
	private String forgotPassAns=""; 
	 private String usernameFlag="true";
	 
	 private String settingFlag="true";
	 private String answercnt="0";
	 private String forgotPasswordSubmitFlag = "";

	public String getForgotPasswordSubmitFlag() {
		return forgotPasswordSubmitFlag;
	}

	public void setForgotPasswordSubmitFlag(String forgotPasswordSubmitFlag) {
		this.forgotPasswordSubmitFlag = forgotPasswordSubmitFlag;
	}

	public String getSettingFlag() {
		return settingFlag;
	}

	public void setSettingFlag(String settingFlag) {
		this.settingFlag = settingFlag;
	}

	public String getAnswercnt() {
		return answercnt;
	}

	public void setAnswercnt(String answercnt) {
		this.answercnt = answercnt;
	}

	public String getUsernameFlag() {
		return usernameFlag;
	}

	public void setUsernameFlag(String usernameFlag) {
		this.usernameFlag = usernameFlag;
	}

	public String getForgotPassAns() {
		return forgotPassAns;
	}

	public void setForgotPassAns(String forgotPassAns) {
		this.forgotPassAns = forgotPassAns;
	}

	public String getForgotPassQue() {
		return forgotPassQue;
	}

	public void setForgotPassQue(String forgotPassQue) {
		this.forgotPassQue = forgotPassQue;
	}

	public TreeMap getForgotQuemap() {
		return forgotQuemap;
	}

	public void setForgotQuemap(TreeMap forgotQuemap) {
		this.forgotQuemap = forgotQuemap;
	}

	public String getSecureAns1() {
		return secureAns1;
	}

	public void setSecureAns1(String secureAns1) {
		this.secureAns1 = secureAns1;
	}

	public String getSecureAns2() {
		return secureAns2;
	}

	public void setSecureAns2(String secureAns2) {
		this.secureAns2 = secureAns2;
	}

	public String getSecureAns3() {
		return secureAns3;
	}

	public void setSecureAns3(String secureAns3) {
		this.secureAns3 = secureAns3;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public String getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public String getImgTxtFlag() {
		return imgTxtFlag;
	}

	public void setImgTxtFlag(String imgTxtFlag) {
		this.imgTxtFlag = imgTxtFlag;
	}

	public String getHidCaptchaID() {
		return hidCaptchaID;
	}

	public void setHidCaptchaID(String hidCaptchaID) {
		this.hidCaptchaID = hidCaptchaID;
	}

	public String getUserNamefg() {
		return userNamefg;
	}

	public void setUserNamefg(String userNamefg) {
		this.userNamefg = userNamefg;
	}

	public String getEmailIdfg() {
		return emailIdfg;
	}

	public void setEmailIdfg(String emailIdfg) {
		this.emailIdfg = emailIdfg;
	}

	public String getEmpIdfg() {
		return empIdfg;
	}

	public void setEmpIdfg(String empIdfg) {
		this.empIdfg = empIdfg;
	}

	/**
	 * @return the invalidCount
	 */
	public int getInvalidCount() {
		return invalidCount;
	}

	/**
	 * @param invalidCount
	 *            the invalidCount to set
	 */

	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

	public String getSendHostName() {
		return sendHostName;
	}

	public void setSendHostName(String sendHostName) {
		this.sendHostName = sendHostName;
	}

	public String getSendPortNo() {
		return sendPortNo;
	}

	public void setSendPortNo(String sendPortNo) {
		this.sendPortNo = sendPortNo;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	/**
	 * @return the invalidFlag
	 */
	public String getInvalidFlag() {
		return invalidFlag;
	}

	/**
	 * @param invalidFlag
	 *            the invalidFlag to set
	 */
	public void setInvalidFlag(String invalidFlag) {
		this.invalidFlag = invalidFlag;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getVisitorNo() {
		return visitorNo;
	}

	public void setVisitorNo(String visitorNo) {
		this.visitorNo = visitorNo;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getTranscPass() {
		return transcPass;
	}

	public void setTranscPass(String transcPass) {
		this.transcPass = transcPass;
	}

	/**
	 * @return the invalidUser
	 */
	public String getInvalidUser() {
		return invalidUser;
	}

	/**
	 * @param invalidUser
	 *            the invalidUser to set
	 */
	public void setInvalidUser(String invalidUser) {
		this.invalidUser = invalidUser;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public String getAuthPwd() {
		return authPwd;
	}

	public void setAuthPwd(String authPwd) {
		this.authPwd = authPwd;
	}

	public String getAuthChk() {
		return authChk;
	}

	public void setAuthChk(String authChk) {
		this.authChk = authChk;
	}

	public String getPassExpireFlg() {
		return passExpireFlg;
	}

	public void setPassExpireFlg(String passExpireFlg) {
		this.passExpireFlg = passExpireFlg;
	}

	public String getPassExpirePrdcity() {
		return passExpirePrdcity;
	}

	public void setPassExpirePrdcity(String passExpirePrdcity) {
		this.passExpirePrdcity = passExpirePrdcity;
	}

	public String getPassReuseFlg() {
		return passReuseFlg;
	}

	public void setPassReuseFlg(String passReuseFlg) {
		this.passReuseFlg = passReuseFlg;
	}

	public String getPassReusePrdcity() {
		return passReusePrdcity;
	}

	public void setPassReusePrdcity(String passReusePrdcity) {
		this.passReusePrdcity = passReusePrdcity;
	}

	public String getPassLockFlg() {
		return passLockFlg;
	}

	public void setPassLockFlg(String passLockFlg) {
		this.passLockFlg = passLockFlg;
	}

	public String getPassLockPrdcity() {
		return passLockPrdcity;
	}

	public void setPassLockPrdcity(String passLockPrdcity) {
		this.passLockPrdcity = passLockPrdcity;
	}

	public String getPassLockAttmts() {
		return passLockAttmts;
	}

	public void setPassLockAttmts(String passLockAttmts) {
		this.passLockAttmts = passLockAttmts;
	}

	public String getExpPassEmpName() {
		return expPassEmpName;
	}

	public void setExpPassEmpName(String expPassEmpName) {
		this.expPassEmpName = expPassEmpName;
	}

	public String getExpNewpsswd1() {
		return expNewpsswd1;
	}

	public void setExpNewpsswd1(String expNewpsswd1) {
		this.expNewpsswd1 = expNewpsswd1;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getInvalAttmpCount() {
		return invalAttmpCount;
	}

	public void setInvalAttmpCount(String invalAttmpCount) {
		this.invalAttmpCount = invalAttmpCount;
	}

	public String getIsKeyBoardShow() {
		return isKeyBoardShow;
	}

	public void setIsKeyBoardShow(String isKeyBoardShow) {
		this.isKeyBoardShow = isKeyBoardShow;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}

	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldpssword() {
		return oldpssword;
	}

	public void setOldpssword(String oldpssword) {
		this.oldpssword = oldpssword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public TreeMap getQmap() {
		return qmap;
	}

	public void setQmap(TreeMap qmap) {
		this.qmap = qmap;
	}

	public TreeMap getQuesmap() {
		return quesmap;
	}

	public void setQuesmap(TreeMap quesmap) {
		this.quesmap = quesmap;
	}

	public String getSecureQue1() {
		return secureQue1;
	}

	public void setSecureQue1(String secureQue1) {
		this.secureQue1 = secureQue1;
	}

	public String getSecureQue2() {
		return secureQue2;
	}

	public void setSecureQue2(String secureQue2) {
		this.secureQue2 = secureQue2;
	}

	public String getSecureQue3() {
		return secureQue3;
	}

	public void setSecureQue3(String secureQue3) {
		this.secureQue3 = secureQue3;
	}

	public String getConfirmpass() {
		return confirmpass;
	}

	public void setConfirmpass(String confirmpass) {
		this.confirmpass = confirmpass;
	}

	public String getForgotPassQueCode() {
		return forgotPassQueCode;
	}

	public void setForgotPassQueCode(String forgotPassQueCode) {
		this.forgotPassQueCode = forgotPassQueCode;
	}

	public String getForgotPassFlag() {
		return forgotPassFlag;
	}

	public void setForgotPassFlag(String forgotPassFlag) {
		this.forgotPassFlag = forgotPassFlag;
	}

	public String getPartnerUserNameForgot() {
		return partnerUserNameForgot;
	}

	public void setPartnerUserNameForgot(String partnerUserNameForgot) {
		this.partnerUserNameForgot = partnerUserNameForgot;
	}

 

	

}
