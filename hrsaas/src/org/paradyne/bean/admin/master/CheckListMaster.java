package org.paradyne.bean.admin.master;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;
/**
 * @author pranali 
 * Date 26-04-07
 */
public class CheckListMaster extends BeanBase implements Serializable{
	
	private String modeLength="false";
	private String typeCheck;
	private String hidStatus;
	private String checkCode;
	private String checkName;
	private String checkType;
	private String myPage;
	private String show;
	private String  hiddencode;
	ArrayList att=null;
	private String hdeleteCode;
	
	private String  desciption;	
	private String status;
	private String editFlag="false";
	private String cancelFlag;
	private String pageStatus;
	private String pageCheckType;
	private String totalRecords="";
	private String checkAll="";
	 private boolean checkListView=false;
	private HashMap<Object, Object> map = new HashMap<Object, Object>();
	
	/**
	 * @return the map
	 */
	public HashMap<Object, Object> getMap() {
		return map;
	}



	/**
	 * @param map the map to set
	 */
	public void setMap(HashMap<Object, Object> map) {
		this.map = map;
	}



	public CheckListMaster()
	{
		
	}
	
	
	
	public CheckListMaster(String checkCode,String checkName,String checkType)
	{
		this.checkCode=checkCode;
		this.checkName=checkName;
		this.checkType=checkType;
	}
	
	public String getCheckCode()
	{
		return checkCode;
	}
	public void setCheckCode(String checkCode)
	{
		this.checkCode=checkCode;
	}
	
	
	
	public String getCheckName()
	{
		return checkName;
	}
	public void setCheckName(String checkName)
	{
		this.checkName=checkName;
	}
	
	
	public String getCheckType()
	{
		return checkType;
	}
	public void setCheckType(String checkType)
	{
		this.checkType=checkType;
	}
	
	public ArrayList getAtt()
	{
		return att;
	}
	public void  setAtt (ArrayList att)
	{
		this.att=att;
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



	public String getHdeleteCode() {
		return hdeleteCode;
	}



	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getEditFlag() {
		return editFlag;
	}



	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}


	public String getCancelFlag() {
		return cancelFlag;
	}



	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}



	public String getPageStatus() {
		return pageStatus;
	}



	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}



	public String getPageCheckType() {
		return pageCheckType;
	}



	public void setPageCheckType(String pageCheckType) {
		this.pageCheckType = pageCheckType;
	}



	public String getTotalRecords() {
		return totalRecords;
	}



	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}



	public String getCheckAll() {
		return checkAll;
	}



	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}



	public boolean isCheckListView() {
		return checkListView;
	}



	public void setCheckListView(boolean checkListView) {
		this.checkListView = checkListView;
	}



	public String getModeLength() {
		return modeLength;
	}



	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}



	public String getTypeCheck() {
		return typeCheck;
	}



	public void setTypeCheck(String typeCheck) {
		this.typeCheck = typeCheck;
	}



	public String getHidStatus() {
		return hidStatus;
	}



	public void setHidStatus(String hidStatus) {
		this.hidStatus = hidStatus;
	}
	
}
