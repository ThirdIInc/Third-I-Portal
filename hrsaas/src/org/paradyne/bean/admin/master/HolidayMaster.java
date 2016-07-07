package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep K. Sahoo
 * Date:17.07.2007
 */

public class HolidayMaster extends BeanBase {
	String holiDate;
	String desc;
	String isActive; // Status is added by Abhijit
	
	private String hidedate="";
	
	String flag="false";
	
	ArrayList holiList ;
	private String modeLength="false";
	private String totalRecords="";
	private String myPage ;
	private String hiddencode;
	private String show;
	
	private String holidayType="";
	ArrayList iteratorlist;
	
	private String hdeleteCode;


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
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
    


	public HolidayMaster() { }
	
	public HolidayMaster(String holiDate, String desc) {
		super();
		this.holiDate = holiDate;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList getHoliList() {
		return holiList;
	}

	public void setHoliList(ArrayList holiList) {
		this.holiList = holiList;
	}

	public String getHoliDate() {
		return holiDate;
	}

	public void setHoliDate(String holiDate) {
		this.holiDate = holiDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHidedate() {
		return hidedate;
	}

	public void setHidedate(String hidedate) {
		this.hidedate = hidedate;
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

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	 
	
	
	
	
	
	

}
