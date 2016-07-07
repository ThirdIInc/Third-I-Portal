package org.paradyne.bean.admin.master;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;
/*
 * Anantha lakshmi
 */
public class LangMaster extends BeanBase{
	private String modeLength="false";
	private String totalRecords="";
	String langCode="";
	String langType="";
	String countryType="";
	String isActive=""; 
	private String hiddenIsactive="";
	
	private String myPage;
	private String show;
	private String  hiddencode;
	ArrayList awardList;
	
	private String hdeleteCode;
	public LangMaster() { }
	public LangMaster(String langCode, String awardType) {
		super();
		this.langCode = langCode;
		this.langType = langType;
		this.countryType = countryType;
	}
	
	
	
	public String getLangCode() {
		return langCode;
	}
	
	
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	
	public String getLangType() {
		return langType;
	}
	
	public void setLangType(String langType) {
		this.langType = langType;
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
	public String getCountryType() {
		return countryType;
	}
	public void setCountryType(String countryType) {
		this.countryType = countryType;
	}
	public String getHiddenIsactive() {
		return hiddenIsactive;
	}
	public void setHiddenIsactive(String hiddenIsactive) {
		this.hiddenIsactive = hiddenIsactive;
	}

	

}
