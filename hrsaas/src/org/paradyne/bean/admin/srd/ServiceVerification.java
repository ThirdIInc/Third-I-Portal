/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 */
public class ServiceVerification extends BeanBase {
	
	private String paraID="";
	private String serviceID="";
	private String empCode="";
	private String empName="";
	private String empToken="";
	private String department="";
	private String center="";
	private String trade="";
	private String rank="";
	private String fromDate="";
	private String toDate="";
	private String qualSerYear="";
	private String qualSerMonth="";
	private String qualSerDays="";
	private String attestOfficer="";
	private String verifOfficer="";
	private String remark="";
	private String pay="";
	private String payID="";
	private String payScale="";
	
	ArrayList serviceList=null;
	
	/*public ServiceVerification(String serviceID, String empID, String empName, String department, String center, String trade, String rank, String fromDate, String toDate, String qualSerYear, String qualSerMonth, String qualSerDays, String attestOfficer, String verifOfficer, String remark, ArrayList serviceList) {
		super();
		this.serviceID = serviceID;
		this.empID = empID;
		this.empName = empName;
		this.department = department;
		this.center = center;
		this.trade = trade;
		this.rank = rank;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.qualSerYear = qualSerYear;
		this.qualSerMonth = qualSerMonth;
		this.qualSerDays = qualSerDays;
		this.attestOfficer = attestOfficer;
		this.verifOfficer = verifOfficer;
		this.remark = remark;
		this.serviceList = serviceList;
	}

	*//**
	 * 
	 *//*
	public ServiceVerification() {
		// TODO Auto-generated constructor stub
	}
*/
	/**
	 * @return the attestOfficer
	 */
	public String getAttestOfficer() {
		return attestOfficer;
	}

	/**
	 * @param attestOfficer the attestOfficer to set
	 */
	public void setAttestOfficer(String attestOfficer) {
		this.attestOfficer = attestOfficer;
	}

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
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
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
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the qualSerDays
	 */
	public String getQualSerDays() {
		return qualSerDays;
	}

	/**
	 * @param qualSerDays the qualSerDays to set
	 */
	public void setQualSerDays(String qualSerDays) {
		this.qualSerDays = qualSerDays;
	}

	/**
	 * @return the qualSerMonth
	 */
	public String getQualSerMonth() {
		return qualSerMonth;
	}

	/**
	 * @param qualSerMonth the qualSerMonth to set
	 */
	public void setQualSerMonth(String qualSerMonth) {
		this.qualSerMonth = qualSerMonth;
	}

	/**
	 * @return the qualSerYear
	 */
	public String getQualSerYear() {
		return qualSerYear;
	}

	/**
	 * @param qualSerYear the qualSerYear to set
	 */
	public void setQualSerYear(String qualSerYear) {
		this.qualSerYear = qualSerYear;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	/**
	 * @return the serviceList
	 */
	public ArrayList getServiceList() {
		return serviceList;
	}

	/**
	 * @param serviceList the serviceList to set
	 */
	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the trade
	 */
	public String getTrade() {
		return trade;
	}

	/**
	 * @param trade the trade to set
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}

	/**
	 * @return the verifOfficer
	 */
	public String getVerifOfficer() {
		return verifOfficer;
	}

	/**
	 * @param verifOfficer the verifOfficer to set
	 */
	public void setVerifOfficer(String verifOfficer) {
		this.verifOfficer = verifOfficer;
	}

	/**
	 * @return the serviceID
	 */
	public String getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	/**
	 * @return the paraID
	 */
	public String getParaID() {
		return paraID;
	}

	/**
	 * @param paraID the paraID to set
	 */
	public void setParaID(String paraID) {
		this.paraID = paraID;
	}

	/**
	 * @return the pay
	 */
	public String getPay() {
		return pay;
	}

	/**
	 * @param pay the pay to set
	 */
	public void setPay(String pay) {
		this.pay = pay;
	}

	/**
	 * @return the payScale
	 */
	public String getPayScale() {
		return payScale;
	}

	/**
	 * @param payScale the payScale to set
	 */
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}

	/**
	 * @return the payID
	 */
	public String getPayID() {
		return payID;
	}

	/**
	 * @param payID the payID to set
	 */
	public void setPayID(String payID) {
		this.payID = payID;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

}
