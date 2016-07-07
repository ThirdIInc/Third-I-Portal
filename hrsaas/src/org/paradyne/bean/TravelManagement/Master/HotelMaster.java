package org.paradyne.bean.TravelManagement.Master;
/**  @author Nilesh Dhandare*/
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class HotelMaster extends BeanBase {

private String hotelName="";
private String hotelType="";
private String roomtypeName="";
private String roomLevel="";
private String actualRate="";
private String corporateRate="";

private String contactPerson="";
private String address="";
private String city="";
private String phone1="";
private String phone2="";
private String emailId="";
private String hotelId="";
private String hotelMasterRemark="";

private String onLoadFlag="false";
private String flagShow="false";

String hiddencode  = "" ;
String typeId  = "" ;
String cityId  = "" ;
String roomtypeId  = "" ;
String roomlevelId  = "" ;

private String isbreakFast ="";



String hdeleteCode = "";
String myPage  = "" ;
String modeLength  = "" ;
String totalNoOfRecords  = "" ;
ArrayList hoteldataList = null ;

ArrayList roomList=null;
private String distanceFromAirport = "";
private String averageRating = "";
TreeMap zonemap = null;
private String zoneName = "";
private String dinnerPackage ="";
private String dinnerPackageCost ="";


public String getZoneName() {
	return zoneName;
}
public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
}
public String getDinnerPackage() {
	return dinnerPackage;
}
public void setDinnerPackage(String dinnerPackage) {
	this.dinnerPackage = dinnerPackage;
}
public String getDinnerPackageCost() {
	return dinnerPackageCost;
}
public void setDinnerPackageCost(String dinnerPackageCost) {
	this.dinnerPackageCost = dinnerPackageCost;
}
public String getAverageRating() {
	return averageRating;
}
public void setAverageRating(String averageRating) {
	this.averageRating = averageRating;
}
public String getDistanceFromAirport() {
	return distanceFromAirport;
}
public void setDistanceFromAirport(String distanceFromAirport) {
	this.distanceFromAirport = distanceFromAirport;
}
public String getModeLength() {
	return modeLength;
}
public void setModeLength(String modeLength) {
	this.modeLength = modeLength;
}
public String getTotalNoOfRecords() {
	return totalNoOfRecords;
}
public void setTotalNoOfRecords(String totalNoOfRecords) {
	this.totalNoOfRecords = totalNoOfRecords;
}
public String getMyPage() {
	return myPage;
}
public void setMyPage(String myPage) {
	this.myPage = myPage;
}
public String getHdeleteCode() {
	return hdeleteCode;
}
public void setHdeleteCode(String hdeleteCode) {
	this.hdeleteCode = hdeleteCode;
}
public String getHotelName() {
	return hotelName;
}
public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
}
public String getHotelType() {
	return hotelType;
}
public void setHotelType(String hotelType) {
	this.hotelType = hotelType;
}
public String getContactPerson() {
	return contactPerson;
}
public void setContactPerson(String contactPerson) {
	this.contactPerson = contactPerson;
}

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getPhone1() {
	return phone1;
}
public void setPhone1(String phone1) {
	this.phone1 = phone1;
}
public String getPhone2() {
	return phone2;
}
public void setPhone2(String phone2) {
	this.phone2 = phone2;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getOnLoadFlag() {
	return onLoadFlag;
}
public void setOnLoadFlag(String onLoadFlag) {
	this.onLoadFlag = onLoadFlag;
}
public String getFlagShow() {
	return flagShow;
}
public void setFlagShow(String flagShow) {
	this.flagShow = flagShow;
}
public String getHiddencode() {
	return hiddencode;
}
public String getTypeId() {
	return typeId;
}
public void setTypeId(String typeId) {
	this.typeId = typeId;
}
public String getCityId() {
	return cityId;
}
public void setCityId(String cityId) {
	this.cityId = cityId;
}
public void setHiddencode(String hiddencode) {
	this.hiddencode = hiddencode;
}
public String getHotelId() {
	return hotelId;
}
public void setHotelId(String hotelId) {
	this.hotelId = hotelId;
}
public ArrayList getHoteldataList() {
	return hoteldataList;
}
public void setHoteldataList(ArrayList hoteldataList) {
	this.hoteldataList = hoteldataList;
}

public String getRoomtypeName() {
	return roomtypeName;
}
public void setRoomtypeName(String roomtypeName) {
	this.roomtypeName = roomtypeName;
}
public String getRoomtypeId() {
	return roomtypeId;
}
public void setRoomtypeId(String roomtypeId) {
	this.roomtypeId = roomtypeId;
}
public String getRoomLevel() {
	return roomLevel;
}
public void setRoomLevel(String roomLevel) {
	this.roomLevel = roomLevel;
}
public String getRoomlevelId() {
	return roomlevelId;
}
public void setRoomlevelId(String roomlevelId) {
	this.roomlevelId = roomlevelId;
}
public String getActualRate() {
	return actualRate;
}
public void setActualRate(String actualRate) {
	this.actualRate = actualRate;
}
public String getCorporateRate() {
	return corporateRate;
}
public void setCorporateRate(String corporateRate) {
	this.corporateRate = corporateRate;
}
public ArrayList getRoomList() {
	return roomList;
}
public void setRoomList(ArrayList roomList) {
	this.roomList = roomList;
}
public String getHotelMasterRemark() {
	return hotelMasterRemark;
}
public void setHotelMasterRemark(String hotelMasterRemark) {
	this.hotelMasterRemark = hotelMasterRemark;
}
public String getIsbreakFast() {
	return isbreakFast;
}
public void setIsbreakFast(String isbreakFast) {
	this.isbreakFast = isbreakFast;
}
public TreeMap getZonemap() {
	return zonemap;
}
public void setZonemap(TreeMap zonemap) {
	this.zonemap = zonemap;
}



}
