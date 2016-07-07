package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;



public class DonationMaster extends BeanBase implements Serializable{
	private String modeLength="false";
	private String totalRecords="";
	private String donationCode;
	private String donationName;
	private String percentage;
	
	private String isActive;
	private String hdomainCode;
	
	private ArrayList donationList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
	
	private String checkAll="";
	private boolean donationView=false;
	private String ittdonationCode = "";
	private String ittdonationName = "";
	private String ittpercentage = "";
	private String ittactive = "";
	private String active = "";
	private String donationHiddenCode = "";
	String paracode = "";
	
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	
	public String getDonationHiddenCode() {
		return donationHiddenCode;
	}
	public void setDonationHiddenCode(String donationHiddenCode) {
		this.donationHiddenCode = donationHiddenCode;
	}
	public String getIttactive() {
		return ittactive;
	}
	public void setIttactive(String ittactive) {
		this.ittactive = ittactive;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getIttdonationName() {
		return ittdonationName;
	}
	public void setIttdonationName(String ittdonationName) {
		this.ittdonationName = ittdonationName;
	}
	public String getIttpercentage() {
		return ittpercentage;
	}
	public void setIttpercentage(String ittpercentage) {
		this.ittpercentage = ittpercentage;
	}
	
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getDonationCode() {
		return donationCode;
	}
	public void setDonationCode(String donationCode) {
		this.donationCode = donationCode;
	}
	public String getDonationName() {
		return donationName;
	}
	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	public ArrayList getDonationList() {
		return donationList;
	}
	public void setDonationList(ArrayList donationList) {
		this.donationList = donationList;
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
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getCheckAll() {
		return checkAll;
	}
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}
	public boolean isDonationView() {
		return donationView;
	}
	public void setDonationView(boolean donationView) {
		this.donationView = donationView;
	}
	public String getIttdonationCode() {
		return ittdonationCode;
	}
	public void setIttdonationCode(String ittdonationCode) {
		this.ittdonationCode = ittdonationCode;
	}
	
	
	
}
