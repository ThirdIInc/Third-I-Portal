package org.paradyne.bean.admin.master;
 import org.paradyne.lib.BeanBase;
import java.util.*;
/*
 * author:Pradeep Ku. Sahoo 
 */
public class LeaveMaster extends BeanBase {
	
	String leaveCode;
	String leaveName;
	String leaveAbbr;
	private String isActive="";
	private String myPage;
	private String show;
	private String  hiddencode;
	private String modeLength="false";
	private String totalRecords="";
	private String hdeleteCode;
	
	private String isHalfPayApplicable="";

	ArrayList leaveList=null;
	
	public LeaveMaster() { }
	
	public LeaveMaster(String leaveCode, String leaveName,String leaveAbbr) {
		super();
		this.leaveCode = leaveCode;
		this.leaveName = leaveName;
		this.leaveAbbr = leaveAbbr;
	}

	/**
	 * @return the leaveCode
	 */
	public String getLeaveCode() {
		return leaveCode;
	}

	/**
	 * @param leaveCode the leaveCode to set
	 */
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	/**
	 * @return the leaveName
	 */
	public String getLeaveName() {
		return leaveName;
	}

	/**
	 * @param leaveName the leaveName to set
	 */
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveAbbr() {
		return leaveAbbr;
	}

	public void setLeaveAbbr(String leaveAbbr) {
		this.leaveAbbr = leaveAbbr;
	}

	public ArrayList getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
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

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsHalfPayApplicable() {
		return isHalfPayApplicable;
	}

	public void setIsHalfPayApplicable(String isHalfPayApplicable) {
		this.isHalfPayApplicable = isHalfPayApplicable;
	}
	
	}
