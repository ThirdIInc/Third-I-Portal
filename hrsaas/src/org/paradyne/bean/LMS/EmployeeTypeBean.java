package org.paradyne.bean.LMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class EmployeeTypeBean extends BeanBase {

	private String emptype = "";
	private String typeofEmployment = "";

	private String onLoadFlag = "false";
	private String flagShow = "false";

	private String typeId = "";

	private String hiddencode;
	private String empCode = "";
	private String myPage = "";
	private String modeLength = "";
	private String totalNoOfRecords = "";

	ArrayList employmentList;
	LinkedHashMap<String, String> map;

	public LinkedHashMap<String, String> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, String> map) {
		this.map = map;
	}

	public String getOnLoadFlag() {
		return onLoadFlag;
	}
	
	public void setOnLoadFlag(String onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	
	public String getFlagShow() {
		return flagShow;
	}

	
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}

	
	public String getHiddencode() {
		return hiddencode;
	}

	
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	
	public String getMyPage() {
		return myPage;
	}

	
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	
	public String getModeLength() {
		return modeLength;
	}

	
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	
	public String getEmptype() {
		return emptype;
	}

	
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}

	
	public ArrayList getEmploymentList() {
		return employmentList;
	}

	
	public void setEmploymentList(ArrayList employmentList) {
		this.employmentList = employmentList;
	}

	
	public String getTypeofEmployment() {
		return typeofEmployment;
	}

	
	public void setTypeofEmployment(String typeofEmployment) {
		this.typeofEmployment = typeofEmployment;
	}

	
	public String getEmpCode() {
		return empCode;
	}

	
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	
	public String getTypeId() {
		return typeId;
	}

	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

}
