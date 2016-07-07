package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Prajakta.Bhandare
 * @date 11 Feb 2013
 */
public class MyAppsMaster extends BeanBase {

	private String linkId = "";
	private String linkName = "";
	private String linkUrl = "";
	private String linkImage = "";
	private String linkSeq = "";
	private String linkDiv = "";
	private String linkDivCode="";
	private String hiddenCode = "";
	private String modeLength = "";
	private ArrayList<MyAppsMaster> linkList = null;
	private String isActive = "";
	private String show = "";
	private String myPage="";
	private String totalRecords="";
 
	/**
	 * @return linkId
	 */
	public String getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId
	 *            the linkId to set
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 *            the linkName to set
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl
	 *            the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * @return linkImage
	 */
	public String getLinkImage() {
		return linkImage;
	}

	/**
	 * @param linkImage
	 *            the linkImage to set
	 */
	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	/**
	 * @return linkSeq
	 */
	public String getLinkSeq() {
		return linkSeq;
	}

	/**
	 * @param linkSeq
	 *            the linkSeq to set
	 */
	public void setLinkSeq(String linkSeq) {
		this.linkSeq = linkSeq;
	}

	/**
	 * @return linkDiv
	 */
	public String getLinkDiv() {
		return linkDiv;
	}

	/**
	 * @param linkDiv
	 *            the linkDiv to set
	 */
	public void setLinkDiv(String linkDiv) {
		this.linkDiv = linkDiv;
	}

	/**
	 * @return hiddenCode
	 */
	public String getHiddenCode() {
		return hiddenCode;
	}

	/**
	 * @param hiddenCode
	 *            the hiddenCode to set
	 */
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	/**
	 * @return modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength
	 *            the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return linkDivCode
	 */
	public String getLinkDivCode() {
		return linkDivCode;
	}

	/** 
	 * @param linkDivCode
	 * the linkDivCode to set
	 */
	public void setLinkDivCode(String linkDivCode) {
		this.linkDivCode =linkDivCode; 
	}

	/**
	 * @return linkList
	 */
	public ArrayList<MyAppsMaster> getLinkList() {
		return linkList;
	}

	/**
	 * @param linkList
	 * the linkList to set
	 */
	public void setLinkList(ArrayList<MyAppsMaster> linkList) {
		this.linkList = linkList;
	}

	/**
	 * @return  myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 * the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 * the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

}
