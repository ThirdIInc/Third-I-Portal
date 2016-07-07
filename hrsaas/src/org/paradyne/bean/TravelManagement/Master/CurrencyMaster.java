package org.paradyne.bean.TravelManagement.Master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ayyappa
 *
 */

public class CurrencyMaster extends BeanBase implements Serializable  {
	
	private String curCode="";
	private String curName;
	private String curAbbr;
	private String description;
	private String curStatus;
	
	ArrayList tableList=null;
	private String totalRecords;
	private String listLength="false";
	
	private String show;
	private String hdeleteCode;
	private String myPage;
	private String hiddenCode;
	
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	public String getCurName() {
		return curName;
	}
	public void setCurName(String curName) {
		this.curName = curName;
	}
	public String getCurAbbr() {
		return curAbbr;
	}
	public void setCurAbbr(String curAbbr) {
		this.curAbbr = curAbbr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurStatus() {
		return curStatus;
	}
	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}
	public ArrayList getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

}
