package org.paradyne.bean.admin.transfer;

import java.io.Serializable;
import java.util.*;

import org.paradyne.lib.BeanBase;

/***
 *  
 * @author pradeep kumar
 *Date:26-06-2008
 */

public class TransferApproval extends BeanBase implements Serializable  {
	
	ArrayList trfList=null;
	String loginEmpId = "";
	String loginEmpName = "";
	String empId="";
	String empName="";
	String appDt="";
	String curCent="";
	String newCent="";
	String hiddenTrfCode="";
	String trfId="";
	String status="";
	String comments="";
	String recforwardName="";
	String recforwardId="";
	String forstatus="";
	String status1="";
	String empToken="";
	String newCentId="";
	String newDeptId="";
	String newDivId="";
	String remark="";
	private String app="false";
	private String pen="false";
	private String rej="false";
	private String hol="false";
	private String noData="false";
	private String apprflag="false";
	private String listLength="0";
	private String level;
	private String statusList;
	private String commentFlag="false";
	private String statusNew;
	private String statusFlag="false";

	public String getStatusFlag() {
		return statusFlag;
	}


	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}


	public String getStatusNew() {
		return statusNew;
	}


	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public String getListLength() {
		return listLength;
	}


	public void setListLength(String listLength) {
		this.listLength = listLength;
	}


	public String getApp() {
		return app;
	}


	public void setApp(String app) {
		this.app = app;
	}


	public String getPen() {
		return pen;
	}


	public void setPen(String pen) {
		this.pen = pen;
	}


	public String getRej() {
		return rej;
	}


	public void setRej(String rej) {
		this.rej = rej;
	}


	public String getHol() {
		return hol;
	}


	public void setHol(String hol) {
		this.hol = hol;
	}


	public String getNoData() {
		return noData;
	}


	public void setNoData(String noData) {
		this.noData = noData;
	}


	public String getApprflag() {
		return apprflag;
	}


	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}


	public TransferApproval() {
		
		
	}


	public TransferApproval(String empToken,String comments,String status,String trfId,ArrayList trfList, String hiddenTrfCode,String loginEmpId, String loginEmpName, String empId, String empName, String appDt, String curCent, String newCent) {
		super();
		this.trfList = trfList;
		this.loginEmpId = loginEmpId;
		this.loginEmpName = loginEmpName;
		this.empId = empId;
		this.empName = empName;
		this.appDt = appDt;
		this.curCent = curCent;
		this.newCent = newCent;
		this.hiddenTrfCode=hiddenTrfCode;
		this.trfId=trfId;
		this.status=status;
		this.comments=comments;
		this.empToken=empToken;
		
	}


	/**
	 * @return the appDt
	 */
	public String getAppDt() {
		return appDt;
	}


	/**
	 * @param appDt the appDt to set
	 */
	public void setAppDt(String appDt) {
		this.appDt = appDt;
	}


	/**
	 * @return the curCent
	 */
	public String getCurCent() {
		return curCent;
	}


	/**
	 * @param curCent the curCent to set
	 */
	public void setCurCent(String curCent) {
		this.curCent = curCent;
	}


	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}


	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	 * @return the loginEmpId
	 */
	public String getLoginEmpId() {
		return loginEmpId;
	}


	/**
	 * @param loginEmpId the loginEmpId to set
	 */
	public void setLoginEmpId(String loginEmpId) {
		this.loginEmpId = loginEmpId;
	}


	/**
	 * @return the loginEmpName
	 */
	public String getLoginEmpName() {
		return loginEmpName;
	}


	/**
	 * @param loginEmpName the loginEmpName to set
	 */
	public void setLoginEmpName(String loginEmpName) {
		this.loginEmpName = loginEmpName;
	}


	/**
	 * @return the newCent
	 */
	public String getNewCent() {
		return newCent;
	}


	/**
	 * @param newCent the newCent to set
	 */
	public void setNewCent(String newCent) {
		this.newCent = newCent;
	}


	/**
	 * @return the trfList
	 */
	public ArrayList getTrfList() {
		return trfList;
	}


	/**
	 * @param trfList the trfList to set
	 */
	public void setTrfList(ArrayList trfList) {
		this.trfList = trfList;
	}


	/**
	 * @return the hiddenTrfCode
	 */
	public String getHiddenTrfCode() {
		return hiddenTrfCode;
	}


	/**
	 * @param hiddenTrfCode the hiddenTrfCode to set
	 */
	public void setHiddenTrfCode(String hiddenTrfCode) {
		this.hiddenTrfCode = hiddenTrfCode;
	}


	/**
	 * @return the trfId
	 */
	public String getTrfId() {
		return trfId;
	}


	/**
	 * @param trfId the trfId to set
	 */
	public void setTrfId(String trfId) {
		this.trfId = trfId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getRecforwardId() {
		return recforwardId;
	}


	public void setRecforwardId(String recforwardId) {
		this.recforwardId = recforwardId;
	}


	public String getRecforwardName() {
		return recforwardName;
	}


	public void setRecforwardName(String recforwardName) {
		this.recforwardName = recforwardName;
	}


	public String getForstatus() {
		return forstatus;
	}


	public void setForstatus(String forstatus) {
		this.forstatus = forstatus;
	}


	public String getStatus1() {
		return status1;
	}


	public void setStatus1(String status1) {
		this.status1 = status1;
	}


	public String getEmpToken() {
		return empToken;
	}


	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}


	public String getStatusList() {
		return statusList;
	}


	public void setStatusList(String statusList) {
		this.statusList = statusList;
	}


	public String getNewCentId() {
		return newCentId;
	}


	public void setNewCentId(String newCentId) {
		this.newCentId = newCentId;
	}


	public String getNewDeptId() {
		return newDeptId;
	}


	public void setNewDeptId(String newDeptId) {
		this.newDeptId = newDeptId;
	}


	public String getNewDivId() {
		return newDivId;
	}


	public void setNewDivId(String newDivId) {
		this.newDivId = newDivId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getCommentFlag() {
		return commentFlag;
	}


	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	
	

}
