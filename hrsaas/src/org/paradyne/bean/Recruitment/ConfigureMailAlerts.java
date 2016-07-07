package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class ConfigureMailAlerts extends BeanBase {

	private String reqAppr;
	private String reqACOstatus;
	private String reqSend2Appr;
	private String reqSend2Empr;
	private String reqHead;
	
	private String hidReqAppr;
	private String hidACOstatus;
	private String hidSend2Appr;
	private String hidSend2Empr;
	private String hidreqHead;
	
	
	private String hidConf;  // for checkbox
    private String eventName;
    private String eventCode;
    private String rowId;
    private String moduleName;
    private String templateCode;
    private String templateName;
    private Boolean eventFlag=false;
    private Boolean noofRecordsFlag=false;
    private String dupModulecode;
    
    private String hidRadioConf;   //for  Radio button
	
	ArrayList eventList;
	
	TreeMap map;
    
	public ArrayList getEventList() {
		return eventList;
	}
	public void setEventList(ArrayList eventList) {
		this.eventList = eventList;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getReqAppr() {
		return reqAppr;
	}
	public void setReqAppr(String reqAppr) {
		this.reqAppr = reqAppr;
	}
	public String getReqACOstatus() {
		return reqACOstatus;
	}
	public void setReqACOstatus(String reqACOstatus) {
		this.reqACOstatus = reqACOstatus;
	}
	public String getReqSend2Appr() {
		return reqSend2Appr;
	}
	public void setReqSend2Appr(String reqSend2Appr) {
		this.reqSend2Appr = reqSend2Appr;
	}
	public String getReqSend2Empr() {
		return reqSend2Empr;
	}
	public void setReqSend2Empr(String reqSend2Empr) {
		this.reqSend2Empr = reqSend2Empr;
	}
	public String getReqHead() {
		return reqHead;
	}
	public void setReqHead(String reqHead) {
		this.reqHead = reqHead;
	}
	public String getHidReqAppr() {
		return hidReqAppr;
	}
	public void setHidReqAppr(String hidReqAppr) {
		this.hidReqAppr = hidReqAppr;
	}
	public String getHidACOstatus() {
		return hidACOstatus;
	}
	public void setHidACOstatus(String hidACOstatus) {
		this.hidACOstatus = hidACOstatus;
	}
	public String getHidSend2Appr() {
		return hidSend2Appr;
	}
	public void setHidSend2Appr(String hidSend2Appr) {
		this.hidSend2Appr = hidSend2Appr;
	}
	public String getHidSend2Empr() {
		return hidSend2Empr;
	}
	public void setHidSend2Empr(String hidSend2Empr) {
		this.hidSend2Empr = hidSend2Empr;
	}
	public String getHidreqHead() {
		return hidreqHead;
	}
	public void setHidreqHead(String hidreqHead) {
		this.hidreqHead = hidreqHead;
	}
	
	public String getHidConf() {
		return hidConf;
	}
	public void setHidConf(String hidConf) {
		this.hidConf = hidConf;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Boolean getEventFlag() {
		return eventFlag;
	}
	public void setEventFlag(Boolean eventFlag) {
		this.eventFlag = eventFlag;
	}
	public Boolean getNoofRecordsFlag() {
		return noofRecordsFlag;
	}
	public String getDupModulecode() {
		return dupModulecode;
	}
	public void setDupModulecode(String dupModulecode) {
		this.dupModulecode = dupModulecode;
	}
	public void setNoofRecordsFlag(Boolean noofRecordsFlag) {
		this.noofRecordsFlag = noofRecordsFlag;
	}
	public String getHidRadioConf() {
		return hidRadioConf;
	}
	public void setHidRadioConf(String hidRadioConf) {
		this.hidRadioConf = hidRadioConf;
	}
	
	
	
}
