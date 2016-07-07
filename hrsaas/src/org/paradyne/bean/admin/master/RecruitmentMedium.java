package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;;

public class RecruitmentMedium extends BeanBase {
	
	String mediumCode="";
	String mediumName="";
	private String myPage;
	private String show;
	private String  hiddencode;
	

	private ArrayList recrtList = null;
	
	private String hdeleteCode;


	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getMediumCode() {
		return mediumCode;
	}
	public void setMediumCode(String mediumCode) {
		this.mediumCode = mediumCode;
	}
	
	
	public String getMediumName() {
		return mediumName;
	}
	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}
	
	
	
	public ArrayList getRecrtList() {
		return recrtList;
	}
	public void setRecrtList(ArrayList recrtList) {
		this.recrtList = recrtList;
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
	
	
	
	
	

}
