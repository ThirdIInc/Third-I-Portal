package org.paradyne.bean.CR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.lib.BeanBase;

/**
 * @author vivek.wadhwani
 *
 */
public class ClientMaster extends BeanBase {
	
	private String fromDate = "";
	private String toDate = "";
	private String report = "";
	private String modeLength="false";
	private String totalRecords="";
	String isActive;
	ArrayList titleList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String address;
	private String hdeleteCode;
	ArrayList iteratorList = null;
	private boolean listLength = false;
	private String hiddenCheckBoxFlag = "";
	private String clientUserNo = "";
	private String firstName = "";
	private String lastName = "";
	private String emailClientAddress = "";
	private String password = "";
	private String cellNumber = "";
	private String isClientActive = "";
	ArrayList clientUserList = null;
	private String recordCreatedBy = ""; 
	private String recordCreatedOn = "";
	private String recordModifiedBy = ""; 
	private String recordModifiedOn = "";
	private String myPageInProcess = "";
	private String ittClientUserNo = "";
	private String ittFirstName = "";
	private String ittLastName = "";
	private String ittEmailId = "";
	private String ittPassword="";
	private String ittCellNumber = "";
	private String ittIsActive = "";
	private String ittCreatedOn = "";
	private String ittModifiedOn = "";
	private String paracode = "";
	private String dataPath = "";
	
	
	/**
	 * @return Client User Number 
	 */
	public String getClientUserNo() {
		return clientUserNo;
	}
	
	/** Set Client User Number
	 * @param clientUserNo
	 */
	public void setClientUserNo(String clientUserNo) {
		this.clientUserNo = clientUserNo;
	}
	
	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** Set firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/** Set lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return emailClientAddress
	 */
	public String getEmailClientAddress() {
		return emailClientAddress;
	}
	
	/** Set emailClientAddress
	 * @param emailClientAddress
	 */
	public void setEmailClientAddress(String emailClientAddress) {
		
		this.emailClientAddress = emailClientAddress;
	}
	
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/** Set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** 
	 * @return cellNumber
	 */
	public String getCellNumber() {
		return cellNumber;
	}
	
	/** Set cellNumber
	 * @param cellNumber
	 */
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	/**
	 * @return isClientActive
	 */
		public String getIsClientActive() {
		return isClientActive;
	}
	
	/** Set isClientActive
	 * @param isClientActive
	 */
	public void setIsClientActive(String isClientActive) {
		this.isClientActive = isClientActive;
	}
	
	/** List that is displayed
	 * @return clientUserList
	 */
	public ArrayList getClientUserList() {
		return clientUserList;
	}
	
	/** Set clientUserList
	 * @param clientUserList
	 */
	public void setClientUserList(ArrayList clientUserList) {
		this.clientUserList = clientUserList;
	}
	
	/**
	 * @return recordCreatedBy
	 */
	public String getRecordCreatedBy() {
		return recordCreatedBy;
	}
	
	/** 
	 * @param recordCreatedBy
	 */
	public void setRecordCreatedBy(String recordCreatedBy) {
		this.recordCreatedBy = recordCreatedBy;
	}
	
	/**
	 * @return recordCreatedOn
	 */
	public String getRecordCreatedOn() {
		return recordCreatedOn;
	}
	
	/**
	 * @param recordCreatedOn
	 */
	public void setRecordCreatedOn(String recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}
	
	/**
	 * @return recordModifiedBy
	 */
	public String getRecordModifiedBy() {
		return recordModifiedBy;
	}
	
	/**
	 * @param recordModifiedBy
	 */
	public void setRecordModifiedBy(String recordModifiedBy) {
		this.recordModifiedBy = recordModifiedBy;
	}
	
	/**
	 * @return recordModifiedOn
	 */
	public String getRecordModifiedOn() {
		return recordModifiedOn;
	}
	
	/**
	 * @param recordModifiedOn
	 */
	public void setRecordModifiedOn(String recordModifiedOn) {
		this.recordModifiedOn = recordModifiedOn;
	}
	
	/**
	 * @return fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	
	/**
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * @return toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/** Get report
	 * @return report
	 */
	public String getReport() {
		return report;
	}
	
	/**
	 * @param report
	 */
	public void setReport(String report) {
		this.report = report;
	}
	/**
	 * @return modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/** Total number of records
	 * @return totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return isActive
	 */
	public String getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return titleList
	 */
	public ArrayList getTitleList() {
		return titleList;
	}
	/**
	 * @param titleList
	 */
	public void setTitleList(ArrayList titleList) {
		this.titleList = titleList;
	}
	/** Get the page
	 * @return myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show
	 */
	public void setShow(String show) {
		this.show = show;
	}
	
	/** Get hidden code for edit
	 * @return hidden code
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	
	/** Returns the list the of records in ArrayList
	 * @return iteratorList
	 */
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	
	/**
	 * @param iteratorList
	 */
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	
	/**
	 * @return listLength
	 */
	public boolean isListLength() {
		return listLength;
	}
	
	/**
	 * @param listLength
	 */
	public void setListLength(boolean listLength) {
		this.listLength = listLength;
	}
	
	/**
	 * @return hiddenCheckBoxFlag
	 */
	public String getHiddenCheckBoxFlag() {
		return hiddenCheckBoxFlag;
	}
	
	/**
	 * @param hiddenCheckBoxFlag
	 */
	public void setHiddenCheckBoxFlag(String hiddenCheckBoxFlag) {
		this.hiddenCheckBoxFlag = hiddenCheckBoxFlag;
	}
	
	/** Returns clientuserNo from iterator
	 * @return ittClientUserNo
	 */
	public String getIttClientUserNo() {
		return ittClientUserNo;
	}
	
	/**
	 * @param ittClientUserNo
	 */
	public void setIttClientUserNo(String ittClientUserNo) {
		this.ittClientUserNo = ittClientUserNo;
	}
	
	/**  Gets the Paracode
	 * @return paracode
	 */ 
	public String getParacode() {
		return paracode;
	}
	
	/**
	 * @param paracode
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	
	/** Returns firstName from iterator
	 * @return ittFirstName
	 */
	public String getIttFirstName() {
		return ittFirstName;
	}
	
	/**
	 * @param ittFirstName
	 */
	public void setIttFirstName(String ittFirstName) {
		this.ittFirstName = ittFirstName;
	}
	
	/** Returns LastName from iterator
	 * @return ittLastName
	 */
	public String getIttLastName() {
		return ittLastName;
	}
	
	/**
	 * @param ittLastName
	 */
	public void setIttLastName(String ittLastName) {
		this.ittLastName = ittLastName;
	}
	/**  Returns Email id from iterator
	 * @return ittEmailId
	 */
	public String getIttEmailId() {
		return ittEmailId;
	}
	/**
	 * @param ittEmailId
	 */
	public void setIttEmailId(String ittEmailId) {
		this.ittEmailId = ittEmailId;
	}
	/** Returns Password from iterator
	 * @return ittPassword
	 */
	public String getIttPassword() {
		return ittPassword;
	}
	/**
	 * @param ittPassword
	 */
	public void setIttPassword(String ittPassword) {
		this.ittPassword = ittPassword;
	}
	/** Returns Cell Number from iterator
	 * @return ittCellNumber
	 */
	public String getIttCellNumber() {
		return ittCellNumber;
	}
	/**
	 * @param ittCellNumber
	 */
	public void setIttCellNumber(String ittCellNumber) {
		this.ittCellNumber = ittCellNumber;
	}
	/** Returns isActive from iterator
	 * @return ittIsActive
	 */
	public String getIttIsActive() {
		return ittIsActive;
	}
	/**
	 * @param ittIsActive
	 */
	public void setIttIsActive(String ittIsActive) {
		this.ittIsActive = ittIsActive;
	}
	/** Returns created on from iterator
	 * @return ittCreatedOn
	 */
	public String getIttCreatedOn() {
		return ittCreatedOn;
	}
	/**
	 * @param ittCreatedOn
	 */
	public void setIttCreatedOn(String ittCreatedOn) {
		this.ittCreatedOn = ittCreatedOn;
	}
	/** Returns Modified On from iterator
	 * @return ittModifiedOn
	 */
	public String getIttModifiedOn() {
		return ittModifiedOn;
	}
	/**
	 * @param ittModifiedOn
	 */
	public void setIttModifiedOn(String ittModifiedOn) {
		this.ittModifiedOn = ittModifiedOn;
	}
	/** Returns the page in process
	 * @return myPageInProcess
	 */
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	/**
	 * @param myPageInProcess
	 */
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	
	

}
