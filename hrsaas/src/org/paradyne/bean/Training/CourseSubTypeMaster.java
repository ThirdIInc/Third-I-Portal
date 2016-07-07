package org.paradyne.bean.Training;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CourseSubTypeMaster extends BeanBase {
private String hiddenSubcode;
private String courseSubCode;
private String courseSubType;
private String courseSubDesc;
private String  subCreatedBy;
private String subCreatedOn;
private String subTypemodeLength;
private String myPage;
private String courseSubTypeIt;
private String courseSubCodeIt;
private String courseSubDescIt;
private String subSrNoIt;
private String subCreatedByIt;
private String subCreatedOnIt;
private ArrayList<CourseSubTypeMaster> courseSubList;


public String getCourseSubTypeIt() {
	return courseSubTypeIt;
}
public void setCourseSubTypeIt(String courseSubTypeIt) {
	this.courseSubTypeIt = courseSubTypeIt;
}
public String getCourseSubCodeIt() {
	return courseSubCodeIt;
}
public void setCourseSubCodeIt(String courseSubCodeIt) {
	this.courseSubCodeIt = courseSubCodeIt;
}
public String getCourseSubDescIt() {
	return courseSubDescIt;
}
public void setCourseSubDescIt(String courseSubDescIt) {
	this.courseSubDescIt = courseSubDescIt;
}
public String getSubSrNoIt() {
	return subSrNoIt;
}
public void setSubSrNoIt(String subSrNoIt) {
	this.subSrNoIt = subSrNoIt;
}
public String getSubCreatedByIt() {
	return subCreatedByIt;
}
public void setSubCreatedByIt(String subCreatedByIt) {
	this.subCreatedByIt = subCreatedByIt;
}
public String getSubCreatedOnIt() {
	return subCreatedOnIt;
}
public void setSubCreatedOnIt(String subCreatedOnIt) {
	this.subCreatedOnIt = subCreatedOnIt;
}
public String getSubTypemodeLength() {
	return subTypemodeLength;
}
public void setSubTypemodeLength(String subTypemodeLength) {
	this.subTypemodeLength = subTypemodeLength;
}

public String getCourseSubCode() {
	return courseSubCode;
}
public void setCourseSubCode(String courseSubCode) {
	this.courseSubCode = courseSubCode;
}
public String getCourseSubType() {
	return courseSubType;
}
public void setCourseSubType(String courseSubType) {
	this.courseSubType = courseSubType;
}
public String getCourseSubDesc() {
	return courseSubDesc;
}
public void setCourseSubDesc(String courseSubDesc) {
	this.courseSubDesc = courseSubDesc;
}
public String getSubCreatedBy() {
	return subCreatedBy;
}
public void setSubCreatedBy(String subCreatedBy) {
	this.subCreatedBy = subCreatedBy;
}
public String getSubCreatedOn() {
	return subCreatedOn;
}
public void setSubCreatedOn(String subCreatedOn) {
	this.subCreatedOn = subCreatedOn;
}
public String getHiddenSubcode() {
	return hiddenSubcode;
}
public void setHiddenSubcode(String hiddenSubcode) {
	this.hiddenSubcode = hiddenSubcode;
}
public String getMyPage() {
	return myPage;
}
public void setMyPage(String myPage) {
	this.myPage = myPage;
}
public ArrayList<CourseSubTypeMaster> getCourseSubList() {
	return courseSubList;
}
public void setCourseSubList(ArrayList<CourseSubTypeMaster> courseSubList) {
	this.courseSubList = courseSubList;
}



}
