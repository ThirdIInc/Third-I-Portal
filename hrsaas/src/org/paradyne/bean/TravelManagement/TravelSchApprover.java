package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelSchApprover extends BeanBase {
	
	
	private String expEmpId = "";
	private String expEmpToken = "";	
	private String empName = "";
	private String trvlReqName = "";
	private String trvlDate="";
	private String status="";
	private String comment="";
	
//control flags
	private boolean noData=false;
	private String pen = "";
	private String apprvd = "";
	private String regctd = "";
	
	//for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";
	
	// control flags for iterator

	private boolean penFlag = false;
	private boolean apprvdFlag = false;
	private boolean regctedFlag = false;
	
	
	
	//for iterator
	ArrayList trvlSchList = new ArrayList();
	private String trvlAppId="";
	private String stat="";
	private String travelAppId="";
	
	
	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}
	/**
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}
	/**
	 * @return the trvlAppId
	 */
	public String getTrvlAppId() {
		return trvlAppId;
	}
	/**
	 * @param trvlAppId the trvlAppId to set
	 */
	public void setTrvlAppId(String trvlAppId) {
		this.trvlAppId = trvlAppId;
	}
	/**
	 * @return the trvlSchList
	 */
	public ArrayList getTrvlSchList() {
		return trvlSchList;
	}
	/**
	 * @param trvlSchList the trvlSchList to set
	 */
	public void setTrvlSchList(ArrayList trvlSchList) {
		this.trvlSchList = trvlSchList;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	/**
	 * @return the expEmpId
	 */
	public String getExpEmpId() {
		return expEmpId;
	}
	/**
	 * @param expEmpId the expEmpId to set
	 */
	public void setExpEmpId(String expEmpId) {
		this.expEmpId = expEmpId;
	}
	/**
	 * @return the expEmpToken
	 */
	public String getExpEmpToken() {
		return expEmpToken;
	}
	/**
	 * @param expEmpToken the expEmpToken to set
	 */
	public void setExpEmpToken(String expEmpToken) {
		this.expEmpToken = expEmpToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the trvlReqName
	 */
	public String getTrvlReqName() {
		return trvlReqName;
	}
	/**
	 * @param trvlReqName the trvlReqName to set
	 */
	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}
	/**
	 * @return the trvlDate
	 */
	public String getTrvlDate() {
		return trvlDate;
	}
	/**
	 * @param trvlDate the trvlDate to set
	 */
	public void setTrvlDate(String trvlDate) {
		this.trvlDate = trvlDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the noData
	 */
	public boolean isNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(boolean noData) {
		this.noData = noData;
	}
	/**
	 * @return the pen
	 */
	public String getPen() {
		return pen;
	}
	/**
	 * @param pen the pen to set
	 */
	public void setPen(String pen) {
		this.pen = pen;
	}
	/**
	 * @return the apprvd
	 */
	public String getApprvd() {
		return apprvd;
	}
	/**
	 * @param apprvd the apprvd to set
	 */
	public void setApprvd(String apprvd) {
		this.apprvd = apprvd;
	}
	/**
	 * @return the regctd
	 */
	public String getRegctd() {
		return regctd;
	}
	/**
	 * @param regctd the regctd to set
	 */
	public void setRegctd(String regctd) {
		this.regctd = regctd;
	}
	/**
	 * @return the penFlag
	 */
	public boolean isPenFlag() {
		return penFlag;
	}
	/**
	 * @param penFlag the penFlag to set
	 */
	public void setPenFlag(boolean penFlag) {
		this.penFlag = penFlag;
	}
	/**
	 * @return the apprvdFlag
	 */
	
	/**
	 * @return the regctedFlag
	 */
	public boolean isRegctedFlag() {
		return regctedFlag;
	}
	/**
	 * @param regctedFlag the regctedFlag to set
	 */
	public void setRegctedFlag(boolean regctedFlag) {
		this.regctedFlag = regctedFlag;
	}
	/**
	 * @return the apprvdFlag
	 */
	public boolean isApprvdFlag() {
		return apprvdFlag;
	}
	/**
	 * @param apprvdFlag the apprvdFlag to set
	 */
	public void setApprvdFlag(boolean apprvdFlag) {
		this.apprvdFlag = apprvdFlag;
	}
	/**
	 * @return the travelAppId
	 */
	public String getTravelAppId() {
		return travelAppId;
	}
	/**
	 * @param travelAppId the travelAppId to set
	 */
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}
	
	
	
}
