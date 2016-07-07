package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelConfirmation extends BeanBase {
	
	
	private String empId="";
	private String tokenNo="";
	private String empName="";
	private String travelDate="";
	private String travelID="";
	private String  linkStatus="";	
	private String pend="";
	private String confirm="";
	private String cancel="";
	private String  itPend="";	
	private String hdPage="";
	private String fromTotRec="";
	private String toTotRec="";
	private String status="";
	private String noData="";
	private String cancelStatus="";
	private String confStatus="";
	private String cStatus="";
	private String cancelNoData="";
	 
 
	private ArrayList travelConfList=null;
	
	public ArrayList getTravelConfList() {
		return travelConfList;
	}
	public void setTravelConfList(ArrayList travelConfList) {
		this.travelConfList = travelConfList;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public String getTravelID() {
		return travelID;
	}
	public void setTravelID(String travelID) {
		this.travelID = travelID;
	}
	public String getLinkStatus() {
		return linkStatus;
	}
	public void setLinkStatus(String linkStatus) {
		this.linkStatus = linkStatus;
	}
	public String getPend() {
		return pend;
	}
	public void setPend(String pend) {
		this.pend = pend;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getItPend() {
		return itPend;
	}
	public void setItPend(String itPend) {
		this.itPend = itPend;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getConfStatus() {
		return confStatus;
	}
	public void setConfStatus(String confStatus) {
		this.confStatus = confStatus;
	}
 
	public String getCStatus() {
		return cStatus;
	}
	public void setCStatus(String status) {
		cStatus = status;
	}
	public String getCancelNoData() {
		return cancelNoData;
	}
	public void setCancelNoData(String cancelNoData) {
		this.cancelNoData = cancelNoData;
	}
	

}
