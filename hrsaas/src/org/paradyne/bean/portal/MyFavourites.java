/**
 * 
 */
package org.paradyne.bean.portal;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1439
 *
 */
public class MyFavourites extends BeanBase {
	private String hiddenFavId ="";
	private String hiddenEditCode ="";
	private String linkName="";
	private String linkUrl="";
	private String categoryOther="";
	LinkedHashMap tmap=null;
	private String category="";
	private boolean chkFlag=false;
	/**
	 * @return the hiddenFavId
	 */
	public String getHiddenFavId() {
		return hiddenFavId;
	}
	/**
	 * @param hiddenFavId the hiddenFavId to set
	 */
	public void setHiddenFavId(String hiddenFavId) {
		this.hiddenFavId = hiddenFavId;
	}
	/**
	 * @return the hiddenEditCode
	 */
	public String getHiddenEditCode() {
		return hiddenEditCode;
	}
	/**
	 * @param hiddenEditCode the hiddenEditCode to set
	 */
	public void setHiddenEditCode(String hiddenEditCode) {
		this.hiddenEditCode = hiddenEditCode;
	}
	/**
	 * @return the linkName
	 */
	public String getLinkName() {
		return linkName;
	}
	/**
	 * @param linkName the linkName to set
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * @return the categoryOther
	 */
	public String getCategoryOther() {
		return categoryOther;
	}
	/**
	 * @param categoryOther the categoryOther to set
	 */
	public void setCategoryOther(String categoryOther) {
		this.categoryOther = categoryOther;
	}
	/**
	 * @return the tmap
	 */
	public LinkedHashMap getTmap() {
		return tmap;
	}
	/**
	 * @param tmap the tmap to set
	 */
	public void setTmap(LinkedHashMap tmap) {
		this.tmap = tmap;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the chkFlag
	 */
	public boolean isChkFlag() {
		return chkFlag;
	}
	/**
	 * @param chkFlag the chkFlag to set
	 */
	public void setChkFlag(boolean chkFlag) {
		this.chkFlag = chkFlag;
	}
	



	
}
