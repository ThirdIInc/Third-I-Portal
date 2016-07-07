package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * Pradeep Kumar Sahoo
 * Date:28.06.2007
 */
public class AwardMaster extends BeanBase{
	private String modeLength="false";
	private String totalRecords="";
	String awardCode="";
	String awardType="";
	String isActive=""; //Award Status
	
	private String myPage;
	private String show;
	private String  hiddencode;
	ArrayList awardList;
	
	private String hdeleteCode;
	public AwardMaster() { }
	public AwardMaster(String awardCode, String awardType) {
		super();
		this.awardCode = awardCode;
		this.awardType = awardType;
	}
	
	
	
	public String getAwardCode() {
		return awardCode;
	}
	
	
	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}
	
	
	public String getAwardType() {
		return awardType;
	}
	
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
	public ArrayList getAwardList() {
		return awardList;
	}
	public void setAwardList(ArrayList awardList) {
		this.awardList = awardList;
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
