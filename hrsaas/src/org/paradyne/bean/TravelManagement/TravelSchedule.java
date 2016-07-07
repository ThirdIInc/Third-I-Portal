package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelSchedule extends BeanBase {
	 private String travelEmpId="";
	 private String empName ="";
	 private String appDate ="";
	 private String travelEmpToken="";
	 private String traSchAppId="";
	 private String level="";
	 private String checkStatus ="";
	 private String statusNew ="";
	 private String remark ="";
	 private String travelViewNo="";
	 private String apprflag="";
	 private String listLength="";
	 private String status="";
	 private String noData ="";
	 private String pen="";
	 private String app="";
	 ArrayList travelSchList ;
	 private String  scheduled="";
	 private String hdPage="";
	 private String fromTotRec="";
	 private String toTotRec="";
	 
	public ArrayList getTravelSchList() {
		return travelSchList;
	}
	public void setTravelSchList(ArrayList travelSchList) {
		this.travelSchList = travelSchList;
	}
	public String getTravelEmpId() {
		return travelEmpId;
	}
	public void setTravelEmpId(String travelEmpId) {
		this.travelEmpId = travelEmpId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getTravelEmpToken() {
		return travelEmpToken;
	}
	public void setTravelEmpToken(String travelEmpToken) {
		this.travelEmpToken = travelEmpToken;
	}
 
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTravelViewNo() {
		return travelViewNo;
	}
	public void setTravelViewNo(String travelViewNo) {
		this.travelViewNo = travelViewNo;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
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
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getTraSchAppId() {
		return traSchAppId;
	}
	public void setTraSchAppId(String traSchAppId) {
		this.traSchAppId = traSchAppId;
	}
	public String getScheduled() {
		return scheduled;
	}
	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
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
 
}
