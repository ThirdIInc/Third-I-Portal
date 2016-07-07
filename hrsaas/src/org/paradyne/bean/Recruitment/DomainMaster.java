package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;
/**
 * 
 * @author Pradeep
 * Date:05-01-2009
 *
 */

public class DomainMaster extends BeanBase {
	private String totalPage="";
	private String pageNoField="";
	private String domainCode;
	private String domainName;
	private String domDesc;
	private String domAbbr;
	private String domainStatus;
	private String hdomainCode;
	private ArrayList domainList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String flagShow="false";
	
	private String onLoadFlag="false";
	private String saveFlag="false";
	private String flag="false";
	private String editFlag="false";
	private String pageFlag="false";
	private String dblFlag="false";
	private String upFlag="false"; 
	private String viewStatus="";
	private String cancelFlg="false";
	private String flagView="flase";
	private String chkDomAll="";
	private String totRecord="";
	
	public String getTotRecord() {
		return totRecord;
	}
	public void setTotRecord(String totRecord) {
		this.totRecord = totRecord;
	}
	public String getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(String onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomDesc() {
		return domDesc;
	}
	public void setDomDesc(String domDesc) {
		this.domDesc = domDesc;
	}
	public String getDomAbbr() {
		return domAbbr;
	}
	public void setDomAbbr(String domAbbr) {
		this.domAbbr = domAbbr;
	}
	public String getDomainStatus() {
		return domainStatus;
	}
	public void setDomainStatus(String domainStatus) {
		this.domainStatus = domainStatus;
	}
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	public ArrayList getDomainList() {
		return domainList;
	}
	public void setDomainList(ArrayList domainList) {
		this.domainList = domainList;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(String dblFlag) {
		this.dblFlag = dblFlag;
	}
	/**
	 * @return the upFlag
	 */
	public String getUpFlag() {
		return upFlag;
	}
	/**
	 * @param upFlag the upFlag to set
	 */
	public void setUpFlag(String upFlag) {
		this.upFlag = upFlag;
	}
	public String getCancelFlg() {
		return cancelFlg;
	}
	public void setCancelFlg(String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}
	public String getChkDomAll() {
		return chkDomAll;
	}
	public void setChkDomAll(String chkDomAll) {
		this.chkDomAll = chkDomAll;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
	

}
