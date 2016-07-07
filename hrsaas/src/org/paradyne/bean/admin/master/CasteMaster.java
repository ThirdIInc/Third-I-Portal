 package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

 

public class CasteMaster extends BeanBase
{
	private String modeLength="false";
	private String totalRecords="";
	String casteName;
	String casteCatgName;
	String casteCode;
	String casteCatgCode;
	
	String isActive; // Status of CasteMaster
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	
	ArrayList casteList;
	
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getCasteCatgName() {
		return casteCatgName;
	}
	public void setCasteCatgName(String casteCatgName) {
		this.casteCatgName = casteCatgName;
	}
	public String getCasteCode() {
		return casteCode;
	}
	public void setCasteCode(String casteCode) {
		this.casteCode = casteCode;
	}
	public String getCasteCatgCode() {
		return casteCatgCode;
	}
	public void setCasteCatgCode(String casteCatgCode) {
		this.casteCatgCode = casteCatgCode;
	}
	public ArrayList getCasteList() {
		return casteList;
	}
	public void setCasteList(ArrayList casteList) {
		this.casteList = casteList;
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
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	 
	

}
