
package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author saipavan v 
 *
 */
public class SuggestionApproval extends BeanBase {

	private String outAppStatus =""; //for application status.
	private String suggestionDate;
	private String suggestion;
	private String suggcode;
	private String ecode;
	private String comments;  
	private String checkStatus;
	private String status;
	private String apprflag="false";
	private String level;
	private ArrayList list;
	private ArrayList applist;
	private String etoken;
	private String ename;
	private String eApprCode;
	private String noData = "false";
	
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getApplist() {
		return applist;
	}
	public String getOutAppStatus() {
		return outAppStatus;
	}
	public void setOutAppStatus(String outAppStatus) {
		this.outAppStatus = outAppStatus;
	}
	
	
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	
	public void setApplist(ArrayList applist) {
		this.applist = applist;
	}
	
	
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getEtoken() {
		return etoken;
	}
	public void setEtoken(String etoken) {
		this.etoken = etoken;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEApprCode() {
		return eApprCode;
	}
	public void setEApprCode(String apprCode) {
		eApprCode = apprCode;
	}
	public String getSuggestionDate() {
		return suggestionDate;
	}
	public void setSuggestionDate(String suggestionDate) {
		this.suggestionDate = suggestionDate;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getSuggcode() {
		return suggcode;
	}
	public void setSuggcode(String suggcode) {
		this.suggcode = suggcode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
