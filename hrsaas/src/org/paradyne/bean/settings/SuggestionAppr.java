/**
 * 
 */
package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 *
 */
public class SuggestionAppr extends BeanBase {


	private String suggestionCode;
	private String suggestionSub;
	
	private String checkStatus;
	private String suggStatus;
	private String status;
	private String apprflag;
	private String SrNo;
	private String suggDetail="0";
	private ArrayList list;
	private ArrayList applist;
	public String getSuggestionCode() {
		return suggestionCode;
	}
	public void setSuggestionCode(String suggestionCode) {
		this.suggestionCode = suggestionCode;
	}
	public String getSuggestionSub() {
		return suggestionSub;
	}
	public void setSuggestionSub(String suggestionSub) {
		this.suggestionSub = suggestionSub;
	}
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
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getApplist() {
		return applist;
	}
	public void setApplist(ArrayList applist) {
		this.applist = applist;
	}
	public String getSuggStatus() {
		return suggStatus;
	}
	public void setSuggStatus(String suggStatus) {
		this.suggStatus = suggStatus;
	}
	public String getSrNo() {
		return SrNo;
	}
	public void setSrNo(String srNo) {
		SrNo = srNo;
	}
	public String getSuggDetail() {
		return suggDetail;
	}
	public void setSuggDetail(String suggDetail) {
		this.suggDetail = suggDetail;
	}
	
}
