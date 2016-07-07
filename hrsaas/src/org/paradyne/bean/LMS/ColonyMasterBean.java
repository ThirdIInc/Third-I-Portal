package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ColonyMasterBean extends BeanBase {

	/** 
	 * for Colony list
	 */
	
	private String ittColonyId="";
	private String ittSrN0="";
	private String ittColonyN0="";
	private String ittColonyName="";
	private String ittColonyAddress="";
	ArrayList ColonyMasterItt=null ;
	/*
	 * for Colony Master 
	 * 
	 */
	private String colonyNo="";
	private String colonyName="";
	private String colonyAddress="";

	private String colonyId="";
	private String myPage="";
	public String getColonyNo() {
		return colonyNo;
	}
	public void setColonyNo(String colonyNo) {
		this.colonyNo = colonyNo;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public String getColonyAddress() {
		return colonyAddress;
	}
	public void setColonyAddress(String colonyAddress) {
		this.colonyAddress = colonyAddress;
	}
	public String getColonyId() {
		return colonyId;
	}
	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getIttColonyId() {
		return ittColonyId;
	}
	public void setIttColonyId(String ittColonyId) {
		this.ittColonyId = ittColonyId;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getIttColonyName() {
		return ittColonyName;
	}
	public void setIttColonyName(String ittColonyName) {
		this.ittColonyName = ittColonyName;
	}
	public String getIttColonyAddress() {
		return ittColonyAddress;
	}
	public void setIttColonyAddress(String ittColonyAddress) {
		this.ittColonyAddress = ittColonyAddress;
	}
	public ArrayList getColonyMasterItt() {
		return ColonyMasterItt;
	}
	public void setColonyMasterItt(ArrayList colonyMasterItt) {
		ColonyMasterItt = colonyMasterItt;
	}
	public String getIttColonyN0() {
		return ittColonyN0;
	}
	public void setIttColonyN0(String ittColonyN0) {
		this.ittColonyN0 = ittColonyN0;
	}
	
	
	
	
}
