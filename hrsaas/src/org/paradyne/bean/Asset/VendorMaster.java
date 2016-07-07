package org.paradyne.bean.Asset;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * 
 * @author Krishna
 * 
 */
public class VendorMaster extends BeanBase {

	private String vendorCode;
	private String vendorName;
	private String vendorAdd;
	private String vendorCon;
	private String vendorEmail;
	private String vendorCty;
	private String vendorState;
	private String ctyId;
	private String stateId;
	private String locParentCode;
	private String pinCode;
	private String myPage;
	private String hiddencode;
	private String show;
	ArrayList iteratorlist;
	private String hdeleteCode;
	private String isActive="";
	 private String totalRecords="";
	 private String recordsLength="false";
	 ArrayList recordList;
	 private String vendortype="";
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	public String getVendortype() {
		return vendortype;
	}

	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
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
	 * @return the vendorAdd
	 */
	public String getVendorAdd() {
		return vendorAdd;
	}

	/**
	 * @param vendorAdd
	 *            the vendorAdd to set
	 */
	public void setVendorAdd(String vendorAdd) {
		this.vendorAdd = vendorAdd;
	}

	/**
	 * @return the vendorCon
	 */
	public String getVendorCon() {
		return vendorCon;
	}

	/**
	 * @param vendorCon
	 *            the vendorCon to set
	 */
	public void setVendorCon(String vendorCon) {
		this.vendorCon = vendorCon;
	}

	/**
	 * @return the vendorEmail
	 */
	public String getVendorEmail() {
		return vendorEmail;
	}

	/**
	 * @param vendorEmail
	 *            the vendorEmail to set
	 */
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	/**
	 * @return the vendorCty
	 */
	public String getVendorCty() {
		return vendorCty;
	}

	/**
	 * @param vendorCty
	 *            the vendorCty to set
	 */
	public void setVendorCty(String vendorCty) {
		this.vendorCty = vendorCty;
	}

	/**
	 * @return the vendorState
	 */
	public String getVendorState() {
		return vendorState;
	}

	/**
	 * @param vendorState
	 *            the vendorState to set
	 */
	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}

	/**
	 * @return the ctyId
	 */
	public String getCtyId() {
		return ctyId;
	}

	/**
	 * @param ctyId
	 *            the ctyId to set
	 */
	public void setCtyId(String ctyId) {
		this.ctyId = ctyId;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the locParentCode
	 */
	public String getLocParentCode() {
		return locParentCode;
	}

	/**
	 * @param locParentCode
	 *            the locParentCode to set
	 */
	public void setLocParentCode(String locParentCode) {
		this.locParentCode = locParentCode;
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
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param hiddencode
	 *            the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	/**
	 * @param iteratorlist
	 *            the iteratorlist to set
	 */
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
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
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the recordsLength
	 */
	public String getRecordsLength() {
		return recordsLength;
	}

	/**
	 * @param recordsLength the recordsLength to set
	 */
	public void setRecordsLength(String recordsLength) {
		this.recordsLength = recordsLength;
	}

	/**
	 * @return the recordList
	 */
	public ArrayList getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(ArrayList recordList) {
		this.recordList = recordList;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
