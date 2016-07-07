package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveReason extends BeanBase {

	private String reaId;
	private String reaName;
	
	private String isActive; // Ststus is added by Abhijit
	
	ArrayList listIterator;
	private String noData;
	private String hiddenCode;
	private String hdeleteCode;
	private String totalRecords;
	private String myPage;
	public String getReaId() {
		return reaId;
	}
	public void setReaId(String reaId) {
		this.reaId = reaId;
	}
	public String getReaName() {
		return reaName;
	}
	public void setReaName(String reaName) {
		this.reaName = reaName;
	}
	public ArrayList getListIterator() {
		return listIterator;
	}
	public void setListIterator(ArrayList listIterator) {
		this.listIterator = listIterator;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
