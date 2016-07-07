package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PostApplied extends BeanBase {
private String postCode="";
private  String postName="";

private String myPage;
private String show;
private String  hiddencode;
ArrayList postList;

private String hdeleteCode;
public String getPostCode() {
	return postCode;
}
public void setPostCode(String postCode) {
	this.postCode = postCode;
}
public String getPostName() {
	return postName;
}
public void setPostName(String postName) {
	this.postName = postName;
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
public ArrayList getPostList() {
	return postList;
}
public void setPostList(ArrayList postList) {
	this.postList = postList;
}
public String getHdeleteCode() {
	return hdeleteCode;
}
public void setHdeleteCode(String hdeleteCode) {
	this.hdeleteCode = hdeleteCode;
}

}
