package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AppraisalSchedule extends BeanBase {

	private String apprCode;
	private String apprId ;
	private String startDate;
	private String endDate;
	
	private String phaseCode;
	private String phaseName;
	private String phaseStartDate;
	private String phaseEndDate;
	private String phaseLockFlag;
	private String lockAppr;

	private String addFlag="true";
	private String readFlag="true";
	private String dateFlag="false";
	private ArrayList phaseList;
	private String calUpdateFlag="false";
	
	private ArrayList dataList;
	private String myPage;
	private String hiddencode;
	private String show;
	private String hdeleteCode;
	private String appStarted;
	
	
	public String getAppStarted() {
		return appStarted;
	}
	public void setAppStarted(String appStarted) {
		this.appStarted = appStarted;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
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
	public ArrayList getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getPhaseStartDate() {
		return phaseStartDate;
	}
	public void setPhaseStartDate(String phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}
	public String getPhaseEndDate() {
		return phaseEndDate;
	}
	public void setPhaseEndDate(String phaseEndDate) {
		this.phaseEndDate = phaseEndDate;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ArrayList getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	public String getPhaseLockFlag() {
		return phaseLockFlag;
	}
	public void setPhaseLockFlag(String phaseLockFlag) {
		this.phaseLockFlag = phaseLockFlag;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public String getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public String getLockAppr() {
		return lockAppr;
	}
	public void setLockAppr(String lockAppr) {
		this.lockAppr = lockAppr;
	}
	public String getCalUpdateFlag() {
		return calUpdateFlag;
	}
	public void setCalUpdateFlag(String calUpdateFlag) {
		this.calUpdateFlag = calUpdateFlag;
	}

	
	
	
	
	
}
