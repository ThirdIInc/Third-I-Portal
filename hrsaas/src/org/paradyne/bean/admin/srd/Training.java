package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts2.components.Bean;
import org.paradyne.lib.BeanBase;

public class Training extends BeanBase implements Serializable {
	
	public String trnId="";
	public String empId="";
	public String empName="";
	public String tokenNo="";
	public String rank="";
	public String center="";
	
	public String trnTyp="";
	public String trnTypId="";
	
	public String startDate="";
	public String endDate="";
	public String institute="";
	public String locationId="";
	public String location="";
	
	public String fees="";
	public String letterNo="";
	public String letterDate="";
	public String paracode="";
	private ArrayList trnList ;
	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the fees
	 */
	public String getFees() {
		return fees;
	}
	/**
	 * @param fees the fees to set
	 */
	public void setFees(String fees) {
		this.fees = fees;
	}
	/**
	 * @return the institute
	 */
	public String getInstitute() {
		return institute;
	}
	/**
	 * @param institute the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	/**
	 * @return the letterDate
	 */
	public String getLetterDate() {
		return letterDate;
	}
	/**
	 * @param letterDate the letterDate to set
	 */
	public void setLetterDate(String letterDate) {
		this.letterDate = letterDate;
	}
	/**
	 * @return the letterNo
	 */
	public String getLetterNo() {
		return letterNo;
	}
	/**
	 * @param letterNo the letterNo to set
	 */
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the trnId
	 */
	public String getTrnId() {
		return trnId;
	}
	/**
	 * @param trnId the trnId to set
	 */
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	/**
	 * @return the trnList
	 */
	public ArrayList getTrnList() {
		return trnList;
	}
	/**
	 * @param trnList the trnList to set
	 */
	public void setTrnList(ArrayList trnList) {
		this.trnList = trnList;
	}
	/**
	 * @return the trnTyp
	 */
	public String getTrnTyp() {
		return trnTyp;
	}
	/**
	 * @param trnTyp the trnTyp to set
	 */
	public void setTrnTyp(String trnTyp) {
		this.trnTyp = trnTyp;
	}
	/**
	 * @return the trnTypId
	 */
	public String getTrnTypId() {
		return trnTypId;
	}
	/**
	 * @param trnTypId the trnTypId to set
	 */
	public void setTrnTypId(String trnTypId) {
		this.trnTypId = trnTypId;
	}

}
