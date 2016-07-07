/**
 * 
 */
package org.paradyne.bean.admin.master;
import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author Lakkichand
 * @date 20 APR 2007
 */
public class LocationMaster extends BeanBase implements Serializable{
	private String modeLength="false";
	private String totalRecorde="";
	private String locationCode="";
	private String locationName="";
	private String  locclass="";
	private String locationType="";
	private String locationType1="";
	private String countryCode="";
	private String countryName="";
	private String stateCode="";
	private String stateName="";
	//private String cityCode="";
	//private String cityName="";
	
	private String isActive=""; // Status of LocationMaster
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	
	
	private String parentCode="";
	private ArrayList locationList;
	private ArrayList typeList;
	
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/*public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}*/
	public ArrayList getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList locationList) {
		this.locationList = locationList;
	}
	public String getLocationType1() {
		return locationType1;
	}
	public void setLocationType1(String locationType1) {
		this.locationType1 = locationType1;
	}
	public ArrayList getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String pageNo) {
		this.myPage = pageNo;
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
	public String getLocclass() {
		return locclass;
	}
	public void setLocclass(String locclass) {
		this.locclass = locclass;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecorde() {
		return totalRecorde;
	}
	public void setTotalRecorde(String totalRecorde) {
		this.totalRecorde = totalRecorde;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
