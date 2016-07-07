package org.paradyne.bean.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class CongEmailAccount extends BeanBase {

	private String ittUserName="";
	private String ittServerName="";
	private String ittAccountName="";
	private ArrayList list=null;
	
	
	//FOR VIEW JSP
	private String empToken="";
	private String empName="";
	private String empId="";
	private String accountName="";
	private String userName="";
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
	
	/**
	 * TO SET SERVER DATA FRO LOGIN 
	 * @return
	 */
	private String server="";
	private String port="";
	private String protocol="";
	private String imapuser="";
	private String pass="";
	private String smtphost="";
	private String smtpport="";
	private String new_lang="";
	private String select_view="";
	
	
	private String hServer="";
	private String hPort="";
	private String hProtocol="";
	private String hImapuser="";
	private String hPass="";
	private String hSmtphost="";
	private String hSmtpport="";
	private String hNew_lang="";
	private String hSelect_view="";
	
	public String getIttOffcicialCheckBox() {
		return ittOffcicialCheckBox;
	}
	public void setIttOffcicialCheckBox(String ittOffcicialCheckBox) {
		this.ittOffcicialCheckBox = ittOffcicialCheckBox;
	}
	public String getOfficialMailCheck() {
		return officialMailCheck;
	}
	public void setOfficialMailCheck(String officialMailCheck) {
		this.officialMailCheck = officialMailCheck;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getImapuser() {
		return imapuser;
	}
	public void setImapuser(String imapuser) {
		this.imapuser = imapuser;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSmtphost() {
		return smtphost;
	}
	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}
	public String getSmtpport() {
		return smtpport;
	}
	public void setSmtpport(String smtpport) {
		this.smtpport = smtpport;
	}
	public String getNew_lang() {
		return new_lang;
	}
	public void setNew_lang(String new_lang) {
		this.new_lang = new_lang;
	}
	public String getSelect_view() {
		return select_view;
	}
	public void setSelect_view(String select_view) {
		this.select_view = select_view;
	}
	public String getHServer() {
		return hServer;
	}
	public void setHServer(String server) {
		hServer = server;
	}
	public String getHPort() {
		return hPort;
	}
	public void setHPort(String port) {
		hPort = port;
	}
	public String getHProtocol() {
		return hProtocol;
	}
	public void setHProtocol(String protocol) {
		hProtocol = protocol;
	}
	public String getHImapuser() {
		return hImapuser;
	}
	public void setHImapuser(String imapuser) {
		hImapuser = imapuser;
	}
	public String getHPass() {
		return hPass;
	}
	public void setHPass(String pass) {
		hPass = pass;
	}
	public String getHSmtphost() {
		return hSmtphost;
	}
	public void setHSmtphost(String smtphost) {
		hSmtphost = smtphost;
	}
	public String getHSmtpport() {
		return hSmtpport;
	}
	public void setHSmtpport(String smtpport) {
		hSmtpport = smtpport;
	}
	public String getHNew_lang() {
		return hNew_lang;
	}
	public void setHNew_lang(String new_lang) {
		hNew_lang = new_lang;
	}
	public String getHSelect_view() {
		return hSelect_view;
	}
	public void setHSelect_view(String select_view) {
		hSelect_view = select_view;
	}

	
}
