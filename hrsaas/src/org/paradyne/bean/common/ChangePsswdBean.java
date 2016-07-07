/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.lib.BeanBase;

/**
 * @author MuzaffarS
 *
 */
public class ChangePsswdBean extends BeanBase
{
	private String pssword;
	private String emp_id;
	private String empnm;
	private String oldpsswd;
	private String newpsswd1;
	private String newpsswd2;
	private String mailPassword;
	private String userName;
	private String oldPass;
	private String newPass;
	private String confPass;
	private String linkNameQl;
	private String hiddenCode_Ql;
	private String checkFlag_Ql = "true";
	private String uploadQl;
	private String linkQl;
	private String linkCode_Ql;
	private String linkName_Ql;
	private String linkFile_Ql;
	private ArrayList<Object> list_QlLink;
	private String hiddenDivID;
	private String selHomeTab;
	
	TreeMap tmap;
	TreeMap qmap;
	TreeMap quesmap;
	LinkedHashMap homeTab;
	private String question="";
	private String imgName="";
	private String userText="";
	
	private String secureQue1="";
	private String secureQue2="";
	private String secureQue3="";
	private String secureAns1="";
	private String secureAns2="";
	private String secureAns3="";
	private String isImage="false";
	private String hiddenRadio="";
	
	/**
	 * FOR CONFIGURE EMAIL ACCOUNT
	 * @return
	 */
	private String ittUserName="";
	private String ittServerName="";
	private String ittAccountName="";
	private ArrayList list=null;
	
	
	//FOR VIEW JSP
	private String empToken="";
	private String empName="";
	private String empId="";
	private String accountName="";
	//private String userName="";
	private String userPassword="";
	private String serverList="";
	private String serverName="";
	private String inServerIP="";
	private String inServerPort="";
	private String serverType="";
	private String outServerIP="";
	private String outServerPort="";
	private TreeMap serverMap;
	private String chkFlag="false";
	private String chkDisableFlag="false";
	private String hiddenCode="";	
	private String ittHiddenCode="";
	private String officialMailCheck="";
	private String ittOffcicialCheckBox="";
	private String flagForCongMail="";
	
	private String authorizationPassword="";
	private String confirmAuthorizationPassword="";
	private String employeeName="";
	
	 
	public String getAuthorizationPassword() {
		return authorizationPassword;
	}
	public void setAuthorizationPassword(String authorizationPassword) {
		this.authorizationPassword = authorizationPassword;
	}
	public String getConfirmAuthorizationPassword() {
		return confirmAuthorizationPassword;
	}
	public void setConfirmAuthorizationPassword(String confirmAuthorizationPassword) {
		this.confirmAuthorizationPassword = confirmAuthorizationPassword;
	}
	public String getFlagForCongMail() {
		return flagForCongMail;
	}
	public void setFlagForCongMail(String flagForCongMail) {
		this.flagForCongMail = flagForCongMail;
	}
	public String getHiddenRadio() {
		return hiddenRadio;
	}
	public void setHiddenRadio(String hiddenRadio) {
		this.hiddenRadio = hiddenRadio;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getHiddenDivID() {
		return hiddenDivID;
	}
	public void setHiddenDivID(String hiddenDivID) {
		this.hiddenDivID = hiddenDivID;
	}
	public ArrayList<Object> getList_QlLink() {
		return list_QlLink;
	}
	public void setList_QlLink(ArrayList<Object> list_QlLink) {
		this.list_QlLink = list_QlLink;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmpnm() {
		return empnm;
	}
	public void setEmpnm(String empnm) {
		this.empnm = empnm;
	}
	public String getNewpsswd1() {
		return newpsswd1;
	}
	public void setNewpsswd1(String newpsswd1) {
		this.newpsswd1 = newpsswd1;
	}
	public String getNewpsswd2() {
		return newpsswd2;
	}
	public void setNewpsswd2(String newpsswd2) {
		this.newpsswd2 = newpsswd2;
	}
	public String getOldpsswd() {
		return oldpsswd;
	}
	public void setOldpsswd(String oldpsswd) {
		this.oldpsswd = oldpsswd;
	}
	public String getLinkNameQl() {
		return linkNameQl;
	}
	public void setLinkNameQl(String linkNameQl) {
		this.linkNameQl = linkNameQl;
	}
	public String getHiddenCode_Ql() {
		return hiddenCode_Ql;
	}
	public void setHiddenCode_Ql(String hiddenCode_Ql) {
		this.hiddenCode_Ql = hiddenCode_Ql;
	}
	public String getCheckFlag_Ql() {
		return checkFlag_Ql;
	}
	public void setCheckFlag_Ql(String checkFlag_Ql) {
		this.checkFlag_Ql = checkFlag_Ql;
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
	public String getPssword() {
		return pssword;
	}
	public void setPssword(String pssword) {
		this.pssword = pssword;
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
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getUserText() {
		return userText;
	}
	public void setUserText(String userText) {
		this.userText = userText;
	}
	public String getIsImage() {
		return isImage;
	}
	public void setIsImage(String isImage) {
		this.isImage = isImage;
	}
	public String getSelHomeTab() {
		return selHomeTab;
	}
	public void setSelHomeTab(String selHomeTab) {
		this.selHomeTab = selHomeTab;
	}
	public LinkedHashMap getHomeTab() {
		return homeTab;
	}
	public void setHomeTab(LinkedHashMap homeTab) {
		this.homeTab = homeTab;
	}
	public String getIttUserName() {
		return ittUserName;
	}
	public void setIttUserName(String ittUserName) {
		this.ittUserName = ittUserName;
	}
	public String getIttServerName() {
		return ittServerName;
	}
	public void setIttServerName(String ittServerName) {
		this.ittServerName = ittServerName;
	}
	public String getIttAccountName() {
		return ittAccountName;
	}
	public void setIttAccountName(String ittAccountName) {
		this.ittAccountName = ittAccountName;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getServerList() {
		return serverList;
	}
	public void setServerList(String serverList) {
		this.serverList = serverList;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getInServerIP() {
		return inServerIP;
	}
	public void setInServerIP(String inServerIP) {
		this.inServerIP = inServerIP;
	}
	public String getInServerPort() {
		return inServerPort;
	}
	public void setInServerPort(String inServerPort) {
		this.inServerPort = inServerPort;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getOutServerIP() {
		return outServerIP;
	}
	public void setOutServerIP(String outServerIP) {
		this.outServerIP = outServerIP;
	}
	public String getOutServerPort() {
		return outServerPort;
	}
	public void setOutServerPort(String outServerPort) {
		this.outServerPort = outServerPort;
	}
	public TreeMap getServerMap() {
		return serverMap;
	}
	public void setServerMap(TreeMap serverMap) {
		this.serverMap = serverMap;
	}
	public String getChkFlag() {
		return chkFlag;
	}
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	public String getChkDisableFlag() {
		return chkDisableFlag;
	}
	public void setChkDisableFlag(String chkDisableFlag) {
		this.chkDisableFlag = chkDisableFlag;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getIttHiddenCode() {
		return ittHiddenCode;
	}
	public void setIttHiddenCode(String ittHiddenCode) {
		this.ittHiddenCode = ittHiddenCode;
	}
	public String getOfficialMailCheck() {
		return officialMailCheck;
	}
	public void setOfficialMailCheck(String officialMailCheck) {
		this.officialMailCheck = officialMailCheck;
	}
	public String getIttOffcicialCheckBox() {
		return ittOffcicialCheckBox;
	}
	public void setIttOffcicialCheckBox(String ittOffcicialCheckBox) {
		this.ittOffcicialCheckBox = ittOffcicialCheckBox;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
