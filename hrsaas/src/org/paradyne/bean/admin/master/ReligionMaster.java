package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author pranali 
 * Date 24-04-07
 */
public class ReligionMaster extends BeanBase implements Serializable
{
	String religionID="";
	String religionName="";
	String isActive=""; // Status of ReligionMaster
	private String myPage;
	private String show;
	private String  hiddencode;
	private String modeLength="false";
	private String totalRecords="";
	ArrayList att=null; 
	
	private String hdeleteCode;

	
	
	public ReligionMaster()
	{
	
	}
	
	
	public ReligionMaster(String religionID, String religionName)
	{
		this.religionID=religionID;
		this.religionName=religionName;
	
	}
	
	public String getReligionID()
	{
		return religionID;
	}
	
	public void setReligionID(String religionID)
	{
		this.religionID=religionID;
	}
	
	public String getReligionName()
	{
		return religionName;
	}
	
	
	public void setReligionName(String religionName)
	{
		this.religionName=religionName;
	}
	
	public void setAtt(ArrayList att)
	{
		
		this.att	=	att;
	}
	
	public ArrayList getAtt()
	{
		
		return att;
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


	public String getModeLength() {
		return modeLength;
	}


	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}


	public String getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	

}
