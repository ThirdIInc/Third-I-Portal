/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Anantha lakshmi
 *
 */
public class EventMaster extends BeanBase {
	
	private String event ="";;
	private String mailId ="";;
	private ArrayList eventList=null;
	private String modeLength="false";
	private String totalRecords  ="";;
	private String hdeleteCode  ="";;
	private String myPage  ="";;
	private String show  ="";;
	private String  hiddencode  ="";;
	private String hiddenAutoCode ="";
	private String eventCode ="";	
	
	
	public String getHiddenAutoCode() {
		return hiddenAutoCode;
	}
	public void setHiddenAutoCode(String hiddenAutoCode) {
		this.hiddenAutoCode = hiddenAutoCode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public ArrayList getEventList() {
		return eventList;
	}
	public void setEventList(ArrayList eventList) {
		this.eventList = eventList;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
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
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
}//end of class
