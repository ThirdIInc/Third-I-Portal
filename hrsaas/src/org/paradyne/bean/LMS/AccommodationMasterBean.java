package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AccommodationMasterBean extends BeanBase{

	//For List
	private String AccommodationId="";
	private String ittEmpId="";
	private String ittSrN0="";
	private String ittHouseNo="";
	private String ittColonyName="";
	private String ittEmployeeName="";
	//End List
	
	ArrayList AccommodationMasterList=null ;
	private String empToken="";
	private String empID="";
	private String empName="";
	private String branchName="";
	private String designationName="";
	private String colonyID="";
	private String colonyName="";
	private String houseNo="";
	private String houseAddr="";
	private String houseID="";
	private String accommodationID="";
	private String validityDate="";
	private String amount="";
	private String freeAccommodations="";
	private String hraDeduction="";
	private String rentRec="";
	private String rentAmount="";
	private String individual="";
	private String family="";
	private String myPage="";
	private String radioValue="";
	private String radio1Value="";
	
	
	private String checkOutDate="";
	private String reasonCheckOut="";
	private String remarkCheckOut="";

	/**
	 * @return the checkOutDate
	 */
	public String getCheckOutDate() {
		return checkOutDate;
	}
	/**
	 * @param checkOutDate the checkOutDate to set
	 */
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	/**
	 * @return the reasonCheckOut
	 */
	public String getReasonCheckOut() {
		return reasonCheckOut;
	}
	/**
	 * @param reasonCheckOut the reasonCheckOut to set
	 */
	public void setReasonCheckOut(String reasonCheckOut) {
		this.reasonCheckOut = reasonCheckOut;
	}
	/**
	 * @return the remarkCheckOut
	 */
	public String getRemarkCheckOut() {
		return remarkCheckOut;
	}
	/**
	 * @param remarkCheckOut the remarkCheckOut to set
	 */
	public void setRemarkCheckOut(String remarkCheckOut) {
		this.remarkCheckOut = remarkCheckOut;
	}
	/**
	 * @return the radio1Value
	 */
	public String getRadio1Value() {
		return radio1Value;
	}
	/**
	 * @param radio1Value the radio1Value to set
	 */
	public void setRadio1Value(String radio1Value) {
		this.radio1Value = radio1Value;
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
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the individual
	 */
	public String getIndividual() {
		return individual;
	}
	/**
	 * @param individual the individual to set
	 */
	public void setIndividual(String individual) {
		this.individual = individual;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}
	/**
	 * @return the rentAmount
	 */
	public String getRentAmount() {
		return rentAmount;
	}
	/**
	 * @param rentAmount the rentAmount to set
	 */
	public void setRentAmount(String rentAmount) {
		this.rentAmount = rentAmount;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the designationName
	 */
	public String getDesignationName() {
		return designationName;
	}
	/**
	 * @param designationName the designationName to set
	 */
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	/**
	 * @return the colonyID
	 */
	public String getColonyID() {
		return colonyID;
	}
	/**
	 * @param colonyID the colonyID to set
	 */
	public void setColonyID(String colonyID) {
		this.colonyID = colonyID;
	}
	/**
	 * @return the colonyName
	 */
	public String getColonyName() {
		return colonyName;
	}
	/**
	 * @param colonyName the colonyName to set
	 */
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	/**
	 * @return the houseNo
	 */
	public String getHouseNo() {
		return houseNo;
	}
	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	/**
	 * @return the houseAddr
	 */
	public String getHouseAddr() {
		return houseAddr;
	}
	/**
	 * @param houseAddr the houseAddr to set
	 */
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	/**
	 * @return the houseID
	 */
	public String getHouseID() {
		return houseID;
	}
	/**
	 * @param houseID the houseID to set
	 */
	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}
	/**
	 * @return the accommodationID
	 */
	public String getAccommodationID() {
		return accommodationID;
	}
	/**
	 * @param accommodationID the accommodationID to set
	 */
	public void setAccommodationID(String accommodationID) {
		this.accommodationID = accommodationID;
	}
	/**
	 * @return the validityDate
	 */
	public String getValidityDate() {
		return validityDate;
	}
	/**
	 * @param validityDate the validityDate to set
	 */
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	/**
	 * @return the freeAccommodations
	 */
	public String getFreeAccommodations() {
		return freeAccommodations;
	}
	/**
	 * @param freeAccommodations the freeAccommodations to set
	 */
	public void setFreeAccommodations(String freeAccommodations) {
		this.freeAccommodations = freeAccommodations;
	}
	/**
	 * @return the hraDeduction
	 */
	public String getHraDeduction() {
		return hraDeduction;
	}
	/**
	 * @param hraDeduction the hraDeduction to set
	 */
	public void setHraDeduction(String hraDeduction) {
		this.hraDeduction = hraDeduction;
	}
	/**
	 * @return the rentRec
	 */
	public String getRentRec() {
		return rentRec;
	}
	/**
	 * @param rentRec the rentRec to set
	 */
	public void setRentRec(String rentRec) {
		this.rentRec = rentRec;
	}
	/**
	 * @return the accommodationMasterList
	 */
	public ArrayList getAccommodationMasterList() {
		return AccommodationMasterList;
	}
	/**
	 * @param accommodationMasterList the accommodationMasterList to set
	 */
	public void setAccommodationMasterList(ArrayList accommodationMasterList) {
		AccommodationMasterList = accommodationMasterList;
	}
	/**
	 * @return the ittEmpId
	 */
	public String getIttEmpId() {
		return ittEmpId;
	}
	/**
	 * @param ittEmpId the ittEmpId to set
	 */
	public void setIttEmpId(String ittEmpId) {
		this.ittEmpId = ittEmpId;
	}
	/**
	 * @return the ittSrN0
	 */
	public String getIttSrN0() {
		return ittSrN0;
	}
	/**
	 * @param ittSrN0 the ittSrN0 to set
	 */
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	/**
	 * @return the ittHouseNo
	 */
	public String getIttHouseNo() {
		return ittHouseNo;
	}
	/**
	 * @param ittHouseNo the ittHouseNo to set
	 */
	public void setIttHouseNo(String ittHouseNo) {
		this.ittHouseNo = ittHouseNo;
	}
	/**
	 * @return the ittColonyName
	 */
	public String getIttColonyName() {
		return ittColonyName;
	}
	/**
	 * @param ittColonyName the ittColonyName to set
	 */
	public void setIttColonyName(String ittColonyName) {
		this.ittColonyName = ittColonyName;
	}
	/**
	 * @return the ittEmployeeName
	 */
	public String getIttEmployeeName() {
		return ittEmployeeName;
	}
	/**
	 * @param ittEmployeeName the ittEmployeeName to set
	 */
	public void setIttEmployeeName(String ittEmployeeName) {
		this.ittEmployeeName = ittEmployeeName;
	}
	/**
	 * @return the accommodationId
	 */
	public String getAccommodationId() {
		return AccommodationId;
	}
	/**
	 * @param accommodationId the accommodationId to set
	 */
	public void setAccommodationId(String accommodationId) {
		AccommodationId = accommodationId;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	
	
	
}
