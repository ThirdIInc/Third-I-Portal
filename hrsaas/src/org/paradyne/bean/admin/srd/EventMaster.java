package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class EventMaster extends BeanBase {

	private String eventModule ="";
	private String eventName =""; 
	private String eventDesc ="";
	private String uploadFileName ="";
	private String uploadImageItt= "";
	private String hiddenAutoCode = "";
	private String uploadCategoryItt ="";
	private String dataPath = "";
	private String userUploadFileName = "";
	private String uploadImagePathItt = "";
	private ArrayList <EventMaster> list =null ;
	private String uploadCategory ="";
	private String eventFromYear  = "";
	private String  eventToYear  = "";
	private String  eventDate  = "";
	private String  eventLocation  = "";
	private String eventCategory ="";
	private String eventTypeCode="";
	private String eventType="";
	private String eventDivCode="";
	private String eventDivision="";
	
	
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public String getEventFromYear() {
		return eventFromYear;
	}
	public void setEventFromYear(String eventFromYear) {
		this.eventFromYear = eventFromYear;
	}
	public String getEventToYear() {
		return eventToYear;
	}
	public void setEventToYear(String eventToYear) {
		this.eventToYear = eventToYear;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}	
	public ArrayList<EventMaster> getList() {
		return list;
	}
	public void setList(ArrayList<EventMaster> list) {
		this.list = list;
	}
	public String getUploadImageItt() {
		return uploadImageItt;
	}
	public void setUploadImageItt(String uploadImageItt) {
		this.uploadImageItt = uploadImageItt;
	}
	public String getEventModule() {
		return eventModule;
	}
	public void setEventModule(String eventModule) {
		this.eventModule = eventModule;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getHiddenAutoCode() {
		return hiddenAutoCode;
	}
	public void setHiddenAutoCode(String hiddenAutoCode) {
		this.hiddenAutoCode = hiddenAutoCode;
	}
	public String getUserUploadFileName() {
		return userUploadFileName;
	}
	public void setUserUploadFileName(String userUploadFileName) {
		this.userUploadFileName = userUploadFileName;
	}
	public String getEventTypeCode() {
		return eventTypeCode;
	}
	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}
	public String getUploadImagePathItt() {
		return uploadImagePathItt;
	}
	public void setUploadImagePathItt(String uploadImagePathItt) {
		this.uploadImagePathItt = uploadImagePathItt;
	}
	public String getUploadCategoryItt() {
		return uploadCategoryItt;
	}
	public void setUploadCategoryItt(String uploadCategoryItt) {
		this.uploadCategoryItt = uploadCategoryItt;
	}
	public String getUploadCategory() {
		return uploadCategory;
	}
	public void setUploadCategory(String uploadCategory) {
		this.uploadCategory = uploadCategory;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventDivCode() {
		return eventDivCode;
	}
	public void setEventDivCode(String eventDivCode) {
		this.eventDivCode = eventDivCode;
	}
	public String getEventDivision() {
		return eventDivision;
	}
	public void setEventDivision(String eventDivision) {
		this.eventDivision = eventDivision;
	}
}
