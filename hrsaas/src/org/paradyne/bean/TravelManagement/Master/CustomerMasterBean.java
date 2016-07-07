package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CustomerMasterBean extends BeanBase
{
	
	private String customercode ="";
	private String customerName ="";
	private String contactPerson ="";
	private String address ="";
	private String city ="";
	private String cityId ="";
	private String phone ="";
	private String emailId ="";
	
	private String myPage="";
	private String modeLength ="";
	private String totalRecords ="";	
	private String customerdeleteCode ="";	
	ArrayList<Object> customerList =null;	
	private String projectId = "";
	private String projectName = "";
	
	public ArrayList<Object> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Object> customerList) {
		this.customerList = customerList;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

	public String getCustomerdeleteCode() {
		return customerdeleteCode;
	}

	public void setCustomerdeleteCode(String customerdeleteCode) {
		this.customerdeleteCode = customerdeleteCode;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
}
