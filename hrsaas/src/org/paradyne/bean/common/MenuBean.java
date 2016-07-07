/**
 * 
 */
package org.paradyne.bean.common;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author sunil
 *
 */
public class MenuBean extends BeanBase {
	
	private String loginEmpName = "";
	private String hdMenuCode ="";
	private String quickLink="";
	private HashMap quickMap;
	private String thought;
	private String empTokenNo;
	private String imagePath;
	private String fontSize;
	private String fontFamily;
	private String fontColor;
	
	/**
	 * @return the quickLink
	 */
	public String getQuickLink() {
		return quickLink;
	}

	/**
	 * @param quickLink the quickLink to set
	 */
	public void setQuickLink(String quickLink) {
		this.quickLink = quickLink;
	}

	/**
	 * @return the quickMap
	 */
	public HashMap getQuickMap() {
		return quickMap;
	}

	/**
	 * @param quickMap the quickMap to set
	 */
	public void setQuickMap(HashMap quickMap) {
		this.quickMap = quickMap;
	}

	public String getHdMenuCode() {
		return hdMenuCode;
	}

	public void setHdMenuCode(String hdMenuCode) {
		this.hdMenuCode = hdMenuCode;
	}

	public String getLoginEmpName() {
		return loginEmpName;
	}

	public void setLoginEmpName(String loginEmpName) {
		this.loginEmpName = loginEmpName;
	}

	/**
	 * @return the thought
	 */
	public String getThought() {
		return thought;
	}

	/**
	 * @param thought the thought to set
	 */
	public void setThought(String thought) {
		this.thought = thought;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	

}

