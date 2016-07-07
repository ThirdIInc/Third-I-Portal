package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * Pradeep K Sahoo
 * Date:25.06.2007
 */
public class TitleMaster extends BeanBase {
	
	private String modeLength="false";
	private String totalRecords="";
	String titleCode;
	String titleName;
	String isActive;
	ArrayList titleList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String address;
	private String hdeleteCode;

	
	
	public ArrayList getTitleList() {
		return titleList;
	}


	public void setTitleList(ArrayList titleList) {
		this.titleList = titleList;
	}


	public TitleMaster() { }
	
	
	public TitleMaster(String titleCode, String titleName) {
		super();
		this.titleCode = titleCode;
		this.titleName = titleName;
	}
	
	
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	

}
