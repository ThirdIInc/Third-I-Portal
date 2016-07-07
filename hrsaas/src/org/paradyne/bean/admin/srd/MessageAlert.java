package org.paradyne.bean.admin.srd;
import java.io.Serializable;
import java.util.*;

import org.paradyne.lib.BeanBase;

public class MessageAlert extends BeanBase {
	
	String msgId;
	String msgDet;
	String msgDate;
	String msgType;
	String msgSub;
	String msgTo;
	String chkSubmit;
	String chkSubmitTest;
	private String msgCode="";
	private String msg1="";
	private String msg2="";
	private String msg3="";
	private String msg4="";
	private String msg5="";
	private String chkFlag="false";
	private String appCode="";
	private String paraId="";
	
	ArrayList msgList=null;
	public MessageAlert() { }
	public MessageAlert(String chkSubmitTest,String chkSubmit,String msgId, String msgDet, String msgDate, String msgType, String msgSub, String msgTo, ArrayList msgList) {
		super();
		this.msgId = msgId;
		this.msgDet = msgDet;
		this.msgDate = msgDate;
		this.msgType = msgType;
		this.msgSub = msgSub;
		this.msgTo = msgTo;
		this.msgList = msgList;
		this.chkSubmitTest=chkSubmitTest;
		this.chkSubmit=chkSubmit;
	}
	public String getChkSubmit() {
		return chkSubmit;
	}
	public void setChkSubmit(String chkSubmit) {
		this.chkSubmit = chkSubmit;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	public String getMsgDet() {
		return msgDet;
	}
	public void setMsgDet(String msgDet) {
		this.msgDet = msgDet;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public ArrayList getMsgList() {
		return msgList;
	}
	public void setMsgList(ArrayList msgList) {
		this.msgList = msgList;
	}
	public String getMsgSub() {
		return msgSub;
	}
	public void setMsgSub(String msgSub) {
		this.msgSub = msgSub;
	}
	public String getMsgTo() {
		return msgTo;
	}
	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	/**
	 * @return the chkSubmitTest
	 */
	public String getChkSubmitTest() {
		return chkSubmitTest;
	}
	/**
	 * @param chkSubmitTest the chkSubmitTest to set
	 */
	public void setChkSubmitTest(String chkSubmitTest) {
		this.chkSubmitTest = chkSubmitTest;
	}
	/**
	 * @return the msgCode
	 */
	public String getMsgCode() {
		return msgCode;
	}
	/**
	 * @param msgCode the msgCode to set
	 */
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	/**
	 * @return the msg1
	 */
	public String getMsg1() {
		return msg1;
	}
	/**
	 * @param msg1 the msg1 to set
	 */
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	/**
	 * @return the msg2
	 */
	public String getMsg2() {
		return msg2;
	}
	/**
	 * @param msg2 the msg2 to set
	 */
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	/**
	 * @return the msg3
	 */
	public String getMsg3() {
		return msg3;
	}
	/**
	 * @param msg3 the msg3 to set
	 */
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}
	/**
	 * @return the msg4
	 */
	public String getMsg4() {
		return msg4;
	}
	/**
	 * @param msg4 the msg4 to set
	 */
	public void setMsg4(String msg4) {
		this.msg4 = msg4;
	}
	/**
	 * @return the msg5
	 */
	public String getMsg5() {
		return msg5;
	}
	/**
	 * @param msg5 the msg5 to set
	 */
	public void setMsg5(String msg5) {
		this.msg5 = msg5;
	}
	/**
	 * @return the chkFlag
	 */
	public String getChkFlag() {
		return chkFlag;
	}
	/**
	 * @param chkFlag the chkFlag to set
	 */
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	/**
	 * @return the aapCode
	 */
	
	public String getAppCode() {
		return appCode;
	}
	/**
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}
	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

}
