package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ClosedRequisitionBean extends BeanBase {
	
	String reqCode;
	String reqName;
	String reqStatus;
	String reqClosedDate;
	
	ArrayList<Object> list = new ArrayList<Object>();

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getReqClosedDate() {
		return reqClosedDate;
	}

	public void setReqClosedDate(String reqClosedDate) {
		this.reqClosedDate = reqClosedDate;
	}

	public ArrayList<Object> getList() {
		return list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

}
