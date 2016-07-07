package org.paradyne.bean.settings;


import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class MailSettings extends BeanBase {
	
	private String authChk;	
	private String pbSmtp;
	private String chkPBSmtp;
	// Fields for MailBox Configuration 
	 
	private String mailServer;
	private String mailProtocol;
	private String mailPort;
	private String mailUsername; 
	private String mailPasswd;
	
	
	private String mailServerOut;
	private String mailProtocolOut;
	private String mailPortOut;
	 
	
	
	//Variables for System Email 
	private String sysEmail;
	private String sysEmailPsswd;
	private String hiddenMailCode;
	private String sysMailCode;
	private String sysEmailId;
	private ArrayList<Object> sysMailList;
	
	
	private String fromMailId=""; 
	private String toMailId="";
	
	private String sendSysMailChk="";
	private String mailServerName="";
	
	private String ittServerName="";
	private String ittUserName="";
	private String ittServerCode="";
	
	private ArrayList list=null;
	
	
	private String hiddenCode="";
	private String serverCode="";
	
	private String inboundServer="";
	private String outboundServer="";
	
	private String sysMailPassword="";
	
	//added by vishwambhar for logon using setting
	
	private String logonusingcheck ="";
	private String logonUserName ="";
	private String logonPassword ="";
	
	private String hiddenRadio="";
	private String useSystemMailIdForAll="";
	private String showTestConnectionFlag="false";

	private String numberOfMailsSend = "";
	
	//added By Anantha Lakshmi
	private String division="";
	private String divisionCode="";
	ArrayList divisionList=null;
	private String checkBoxFlag="";
	
	public String getCheckBoxFlag() {
		return checkBoxFlag;
	}
	public void setCheckBoxFlag(String checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
	}
	public ArrayList getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(ArrayList divisionList) {
		this.divisionList = divisionList;
	}
	public String getNumberOfMailsSend() {
		return numberOfMailsSend;
	}
	public void setNumberOfMailsSend(String numberOfMailsSend) {
		this.numberOfMailsSend = numberOfMailsSend;
	}
	public String getUseSystemMailIdForAll() {
		return useSystemMailIdForAll;
	}
	public void setUseSystemMailIdForAll(String useSystemMailIdForAll) {
		this.useSystemMailIdForAll = useSystemMailIdForAll;
	}
	public String getHiddenRadio() {
		return hiddenRadio;
	}
	public void setHiddenRadio(String hiddenRadio) {
		this.hiddenRadio = hiddenRadio;
	}
	public String getLogonusingcheck() {
		return logonusingcheck;
	}
	public void setLogonusingcheck(String logonusingcheck) {
		this.logonusingcheck = logonusingcheck;
	}
	public String getLogonUserName() {
		return logonUserName;
	}
	public void setLogonUserName(String logonUserName) {
		this.logonUserName = logonUserName;
	}
	public String getLogonPassword() {
		return logonPassword;
	}
	public void setLogonPassword(String logonPassword) {
		this.logonPassword = logonPassword;
	}
	public String getSysMailPassword() {
		return sysMailPassword;
	}
	public void setSysMailPassword(String sysMailPassword) {
		this.sysMailPassword = sysMailPassword;
	}
	public String getInboundServer() {
		return inboundServer;
	}
	public void setInboundServer(String inboundServer) {
		this.inboundServer = inboundServer;
	}
	 
	public String getOutboundServer() {
		return outboundServer;
	}
	public void setOutboundServer(String outboundServer) {
		this.outboundServer = outboundServer;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getIttServerName() {
		return ittServerName;
	}
	public void setIttServerName(String ittServerName) {
		this.ittServerName = ittServerName;
	}
	public String getIttUserName() {
		return ittUserName;
	}
	public void setIttUserName(String ittUserName) {
		this.ittUserName = ittUserName;
	}
	public String getSendSysMailChk() {
		return sendSysMailChk;
	}
	public void setSendSysMailChk(String sendSysMailChk) {
		this.sendSysMailChk = sendSysMailChk;
	}
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
	public ArrayList<Object> getSysMailList() {
		return sysMailList;
	}
	public void setSysMailList(ArrayList<Object> sysMailList) {
		this.sysMailList = sysMailList;
	}
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
	
	public String getAuthChk() {
		return authChk;
	}
	public void setAuthChk(String authChk) {
		this.authChk = authChk;
	}
	public String getPbSmtp() {
		return pbSmtp;
	}
	public void setPbSmtp(String pbSmtp) {
		this.pbSmtp = pbSmtp;
	}
	public String getMailServerName() {
		return mailServerName;
	}
	public void setMailServerName(String mailServerName) {
		this.mailServerName = mailServerName;
	}
	public String getChkPBSmtp() {
		return chkPBSmtp;
	}
	public void setChkPBSmtp(String chkPBSmtp) {
		this.chkPBSmtp = chkPBSmtp;
	}
	public String getIttServerCode() {
		return ittServerCode;
	}
	public void setIttServerCode(String ittServerCode) {
		this.ittServerCode = ittServerCode;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	public String getShowTestConnectionFlag() {
		return showTestConnectionFlag;
	}
	public void setShowTestConnectionFlag(String showTestConnectionFlag) {
		this.showTestConnectionFlag = showTestConnectionFlag;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	


}
