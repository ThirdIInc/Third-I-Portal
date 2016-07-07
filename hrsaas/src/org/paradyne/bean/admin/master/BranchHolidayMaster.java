/**
 * Bhushan
 * May 7, 2008
**/

package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class BranchHolidayMaster extends BeanBase
{
	private String brnCode;
	private String brnName;
	private String year;
	private ArrayList<Object> holidayList;
	private String hDayDate;
	private String occasion;
	private boolean applHDay = false;
	private boolean listFlag = false;
	private boolean allHolidays = false;; 
	
	public boolean isAllHolidays()
    {
        return allHolidays;
    }

    public void setAllHolidays(boolean allHolidays)
    {
        this.allHolidays = allHolidays;
    }

    public String getBrnCode() {
		return brnCode;
	}
	
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	
	public String getBrnName() {
		return brnName;
	}
	
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public ArrayList<Object> getHolidayList() {
		return holidayList;
	}
	
	public void setHolidayList(ArrayList<Object> holidayList) {
		this.holidayList = holidayList;
	}
	
	public String getHDayDate() {
		return hDayDate;
	}
	
	public void setHDayDate(String dayDate) {
		hDayDate = dayDate;
	}
	
	public String getOccasion() {
		return occasion;
	}
	
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public boolean isApplHDay() {
		return applHDay;
	}

	public void setApplHDay(boolean applHDay) {
		this.applHDay = applHDay;
	}
	
	public boolean isListFlag() {
		return listFlag;
	}

	public void setListFlag(boolean listFlag) {
		this.listFlag = listFlag;
	}

    
	
}