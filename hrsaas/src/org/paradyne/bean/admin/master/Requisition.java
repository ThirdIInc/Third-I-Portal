package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class Requisition extends BeanBase {
	private String requisitionCode;
	private String reuisitionName;
	
	ArrayList reqTableList = null;
	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;


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
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	
	 
	public ArrayList getReqTableList() {
		return reqTableList;
	}
	public void setReqTableList(ArrayList reqTableList) {
		this.reqTableList = reqTableList;
	}
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	public String getReuisitionName() {
		return reuisitionName;
	}
	public void setReuisitionName(String reuisitionName) {
		this.reuisitionName = reuisitionName;
	}

}
