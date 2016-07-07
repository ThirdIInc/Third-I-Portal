package org.paradyne.bean.attendance;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AutoPresentAttendance extends BeanBase {

	
	
	private String empId;
	private String empName;
	private String eToken;
	private String waiveOffLate;
	private String waiveOffHalfday;
	private String waiveOffAbsent;
	private String empToken;
	private String empDesg;
	private String LempId;
	private String LwaiveOffLate;
	private String LwaiveOffHalfday;
	private String LwaiveOffAbsent;
	private String autoPresentcode;
	
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	
	private String empListLength;
	private String noData;
	private String totalRecords="";
	ArrayList empList;
	private boolean listLength;
	
	private String isNewrecord="";

	public String getIsNewrecord() {
		return isNewrecord;
	}

	public void setIsNewrecord(String isNewrecord) {
		this.isNewrecord = isNewrecord;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEToken() {
		return eToken;
	}

	public void setEToken(String token) {
		eToken = token;
	}

	public String getWaiveOffLate() {
		return waiveOffLate;
	}

	public void setWaiveOffLate(String waiveOffLate) {
		this.waiveOffLate = waiveOffLate;
	}

	public String getWaiveOffHalfday() {
		return waiveOffHalfday;
	}

	public void setWaiveOffHalfday(String waiveOffHalfday) {
		this.waiveOffHalfday = waiveOffHalfday;
	}

	public String getWaiveOffAbsent() {
		return waiveOffAbsent;
	}

	public void setWaiveOffAbsent(String waiveOffAbsent) {
		this.waiveOffAbsent = waiveOffAbsent;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpDesg() {
		return empDesg;
	}

	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}

	public String getLwaiveOffLate() {
		return LwaiveOffLate;
	}

	public void setLwaiveOffLate(String lwaiveOffLate) {
		LwaiveOffLate = lwaiveOffLate;
	}

	public String getLwaiveOffHalfday() {
		return LwaiveOffHalfday;
	}

	public void setLwaiveOffHalfday(String lwaiveOffHalfday) {
		LwaiveOffHalfday = lwaiveOffHalfday;
	}

	public String getLwaiveOffAbsent() {
		return LwaiveOffAbsent;
	}

	public void setLwaiveOffAbsent(String lwaiveOffAbsent) {
		LwaiveOffAbsent = lwaiveOffAbsent;
	}

	public String getAutoPresentcode() {
		return autoPresentcode;
	}

	public void setAutoPresentcode(String autoPresentcode) {
		this.autoPresentcode = autoPresentcode;
	}

	public ArrayList getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList empList) {
		this.empList = empList;
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

	public String getLempId() {
		return LempId;
	}

	public void setLempId(String lempId) {
		LempId = lempId;
	}

	public String getEmpListLength() {
		return empListLength;
	}

	public void setEmpListLength(String empListLength) {
		this.empListLength = empListLength;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public boolean isListLength() {
		return listLength;
	}

	public void setListLength(boolean listLength) {
		this.listLength = listLength;
	}
}
