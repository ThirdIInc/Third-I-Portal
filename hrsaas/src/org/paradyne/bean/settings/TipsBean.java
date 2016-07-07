package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TipsBean extends BeanBase {

	private String linkCode_TL;
	private String linkName_TL;
	private String tipsName;
	private String hiddenCode_TS;
	private String uploadTs;
	private String linkTs;
	private String activeTip;
	private String divisionCode;
	private String divisionName;
	private String linkFile_TL;
	private String linkActive_TL;
	private boolean divFlag_TS = false;
	private String checkFlag_TS = "true";
	private ArrayList<Object> list_TipsLink;
	private String hiddenDivId;
	
	String myTextarea ="";
	String subject = "";
	private String htmlcode = "";
	
	/**
	 * @return the myTextarea
	 */
	public String getMyTextarea() {
		return myTextarea;
	}
	/**
	 * @param myTextarea the myTextarea to set
	 */
	public void setMyTextarea(String myTextarea) {
		this.myTextarea = myTextarea;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the hiddenDivId
	 */
	public String getHiddenDivId() {
		return hiddenDivId;
	}
	/**
	 * @param hiddenDivId the hiddenDivId to set
	 */
	public void setHiddenDivId(String hiddenDivId) {
		this.hiddenDivId = hiddenDivId;
	}
	/**
	 * @return the list_TipsLink
	 */
	public ArrayList<Object> getList_TipsLink() {
		return list_TipsLink;
	}
	/**
	 * @param list_TipsLink the list_TipsLink to set
	 */
	public void setList_TipsLink(ArrayList<Object> list_TipsLink) {
		this.list_TipsLink = list_TipsLink;
	}
	/**
	 * @return the linkCode_TL
	 */
	public String getLinkCode_TL() {
		return linkCode_TL;
	}
	/**
	 * @param linkCode_TL the linkCode_TL to set
	 */
	public void setLinkCode_TL(String linkCode_TL) {
		this.linkCode_TL = linkCode_TL;
	}
	/**
	 * @return the linkName_TL
	 */
	public String getLinkName_TL() {
		return linkName_TL;
	}
	/**
	 * @param linkName_TL the linkName_TL to set
	 */
	public void setLinkName_TL(String linkName_TL) {
		this.linkName_TL = linkName_TL;
	}
	/**
	 * @return the tipsName
	 */
	public String getTipsName() {
		return tipsName;
	}
	/**
	 * @param tipsName the tipsName to set
	 */
	public void setTipsName(String tipsName) {
		this.tipsName = tipsName;
	}
	/**
	 * @return the hiddenCode_TS
	 */
	public String getHiddenCode_TS() {
		return hiddenCode_TS;
	}
	/**
	 * @param hiddenCode_TS the hiddenCode_TS to set
	 */
	public void setHiddenCode_TS(String hiddenCode_TS) {
		this.hiddenCode_TS = hiddenCode_TS;
	}
	/**
	 * @return the uploadTs
	 */
	public String getUploadTs() {
		return uploadTs;
	}
	/**
	 * @param uploadTs the uploadTs to set
	 */
	public void setUploadTs(String uploadTs) {
		this.uploadTs = uploadTs;
	}
	/**
	 * @return the linkTs
	 */
	public String getLinkTs() {
		return linkTs;
	}
	/**
	 * @param linkTs the linkTs to set
	 */
	public void setLinkTs(String linkTs) {
		this.linkTs = linkTs;
	}
	/**
	 * @return the activeTip
	 */
	public String getActiveTip() {
		return activeTip;
	}
	/**
	 * @param activeTip the activeTip to set
	 */
	public void setActiveTip(String activeTip) {
		this.activeTip = activeTip;
	}
	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the linkFile_TL
	 */
	public String getLinkFile_TL() {
		return linkFile_TL;
	}
	/**
	 * @param linkFile_TL the linkFile_TL to set
	 */
	public void setLinkFile_TL(String linkFile_TL) {
		this.linkFile_TL = linkFile_TL;
	}
	/**
	 * @return the linkActive_TL
	 */
	public String getLinkActive_TL() {
		return linkActive_TL;
	}
	/**
	 * @param linkActive_TL the linkActive_TL to set
	 */
	public void setLinkActive_TL(String linkActive_TL) {
		this.linkActive_TL = linkActive_TL;
	}
	/**
	 * @return the divFlag_TS
	 */
	public boolean isDivFlag_TS() {
		return divFlag_TS;
	}
	/**
	 * @param divFlag_TS the divFlag_TS to set
	 */
	public void setDivFlag_TS(boolean divFlag_TS) {
		this.divFlag_TS = divFlag_TS;
	}
	/**
	 * @return the checkFlag_TS
	 */
	public String getCheckFlag_TS() {
		return checkFlag_TS;
	}
	/**
	 * @param checkFlag_TS the checkFlag_TS to set
	 */
	public void setCheckFlag_TS(String checkFlag_TS) {
		this.checkFlag_TS = checkFlag_TS;
	}
	/**
	 * @return the htmlcode
	 */
	public String getHtmlcode() {
		return htmlcode;
	}
	/**
	 * @param htmlcode the htmlcode to set
	 */
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}

}
