package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**Author Nilesh D**/


public class VenodrMasterBean extends BeanBase {

	public String empToken = "";
	public String empName = "";
	public String vendorAppDate = "";
	public String vendorCode = "";
	public String hdeleteCode = "";

	public String vendorName = "";
	public String einNumber = "";
	public String postboxAddress = "";
	public String city = "";
	public String state = "";
	public String zip = "";
	public String phoneNumber = "";
	public String contactName = "";
	public String remitName = "";
	public String remitAddress = "";
	public String remitCity = "";
	public String remitState = "";
	public String remitZip = "";
	public String discPercent = "";
	public String netDays = "";
	public String comments1 = "";
	public String classCode = "";
	public String minOrder = "";
	public String freightMessage = "";
	public String taxMessage = "";
	public String shipVia = "";
	public String fob = "";
	public String comments2 = "";
	public String formCompletion = "";
	public String date = "";

	public String sendPO = "";

	public String radioValue = "";

	private ArrayList vendorsList = null;
	private String myPage = "";
	private String modeLength = "";
	private String totalNoOfRecords = "";

	public String cityId = "";
	public String remitCityId = "";
	public String vendorIttId = "";

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the remitCityId
	 */
	public String getRemitCityId() {
		return remitCityId;
	}

	/**
	 * @param remitCityId
	 *            the remitCityId to set
	 */
	public void setRemitCityId(String remitCityId) {
		this.remitCityId = remitCityId;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the vendorAppDate
	 */
	public String getVendorAppDate() {
		return vendorAppDate;
	}

	/**
	 * @param vendorAppDate
	 *            the vendorAppDate to set
	 */
	public void setVendorAppDate(String vendorAppDate) {
		this.vendorAppDate = vendorAppDate;
	}

	/**
	 * @return the vendorCode
	 */
	public String getVendorCode() {
		return vendorCode;
	}

	/**
	 * @param vendorCode
	 *            the vendorCode to set
	 */
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	/**
	 * @return the vendorsList
	 */
	public ArrayList getVendorsList() {
		return vendorsList;
	}

	/**
	 * @param vendorsList
	 *            the vendorsList to set
	 */
	public void setVendorsList(ArrayList vendorsList) {
		this.vendorsList = vendorsList;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength
	 *            the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords
	 *            the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @param hdeleteCode
	 *            the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName
	 *            the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the einNumber
	 */
	public String getEinNumber() {
		return einNumber;
	}

	/**
	 * @param einNumber
	 *            the einNumber to set
	 */
	public void setEinNumber(String einNumber) {
		this.einNumber = einNumber;
	}

	/**
	 * @return the postboxAddress
	 */
	public String getPostboxAddress() {
		return postboxAddress;
	}

	/**
	 * @param postboxAddress
	 *            the postboxAddress to set
	 */
	public void setPostboxAddress(String postboxAddress) {
		this.postboxAddress = postboxAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the remitName
	 */
	public String getRemitName() {
		return remitName;
	}

	/**
	 * @param remitName
	 *            the remitName to set
	 */
	public void setRemitName(String remitName) {
		this.remitName = remitName;
	}

	/**
	 * @return the remitAddress
	 */
	public String getRemitAddress() {
		return remitAddress;
	}

	/**
	 * @param remitAddress
	 *            the remitAddress to set
	 */
	public void setRemitAddress(String remitAddress) {
		this.remitAddress = remitAddress;
	}

	/**
	 * @return the discPercent
	 */
	public String getDiscPercent() {
		return discPercent;
	}

	/**
	 * @param discPercent
	 *            the discPercent to set
	 */
	public void setDiscPercent(String discPercent) {
		this.discPercent = discPercent;
	}

	/**
	 * @return the netDays
	 */
	public String getNetDays() {
		return netDays;
	}

	/**
	 * @param netDays
	 *            the netDays to set
	 */
	public void setNetDays(String netDays) {
		this.netDays = netDays;
	}

	/**
	 * @return the comments1
	 */
	public String getComments1() {
		return comments1;
	}

	/**
	 * @param comments1
	 *            the comments1 to set
	 */
	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}

	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}

	/**
	 * @param classCode
	 *            the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
	 * @return the minOrder
	 */
	public String getMinOrder() {
		return minOrder;
	}

	/**
	 * @param minOrder
	 *            the minOrder to set
	 */
	public void setMinOrder(String minOrder) {
		this.minOrder = minOrder;
	}

	/**
	 * @return the freightMessage
	 */
	public String getFreightMessage() {
		return freightMessage;
	}

	/**
	 * @param freightMessage
	 *            the freightMessage to set
	 */
	public void setFreightMessage(String freightMessage) {
		this.freightMessage = freightMessage;
	}

	/**
	 * @return the shipVia
	 */
	public String getShipVia() {
		return shipVia;
	}

	/**
	 * @param shipVia
	 *            the shipVia to set
	 */
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	/**
	 * @return the fob
	 */
	public String getFob() {
		return fob;
	}

	/**
	 * @param fob
	 *            the fob to set
	 */
	public void setFob(String fob) {
		this.fob = fob;
	}

	/**
	 * @return the comments2
	 */
	public String getComments2() {
		return comments2;
	}

	/**
	 * @param comments2
	 *            the comments2 to set
	 */
	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}

	/**
	 * @return the formCompletion
	 */
	public String getFormCompletion() {
		return formCompletion;
	}

	/**
	 * @param formCompletion
	 *            the formCompletion to set
	 */
	public void setFormCompletion(String formCompletion) {
		this.formCompletion = formCompletion;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the sendPO
	 */
	public String getSendPO() {
		return sendPO;
	}

	/**
	 * @param sendPO
	 *            the sendPO to set
	 */
	public void setSendPO(String sendPO) {
		this.sendPO = sendPO;
	}

	/**
	 * @return the remitCity
	 */
	public String getRemitCity() {
		return remitCity;
	}

	/**
	 * @param remitCity
	 *            the remitCity to set
	 */
	public void setRemitCity(String remitCity) {
		this.remitCity = remitCity;
	}

	/**
	 * @return the remitState
	 */
	public String getRemitState() {
		return remitState;
	}

	/**
	 * @param remitState
	 *            the remitState to set
	 */
	public void setRemitState(String remitState) {
		this.remitState = remitState;
	}

	/**
	 * @return the radioValue
	 */
	public String getRadioValue() {
		return radioValue;
	}

	/**
	 * @param radioValue the radioValue to set
	 */
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}

	/**
	 * @return the remitZip
	 */
	public String getRemitZip() {
		return remitZip;
	}

	/**
	 * @param remitZip the remitZip to set
	 */
	public void setRemitZip(String remitZip) {
		this.remitZip = remitZip;
	}

	/**
	 * @return the taxMessage
	 */
	public String getTaxMessage() {
		return taxMessage;
	}

	/**
	 * @param taxMessage the taxMessage to set
	 */
	public void setTaxMessage(String taxMessage) {
		this.taxMessage = taxMessage;
	}

	/**
	 * @return the vendorIttId
	 */
	public String getVendorIttId() {
		return vendorIttId;
	}

	/**
	 * @param vendorIttId the vendorIttId to set
	 */
	public void setVendorIttId(String vendorIttId) {
		this.vendorIttId = vendorIttId;
	}

}
