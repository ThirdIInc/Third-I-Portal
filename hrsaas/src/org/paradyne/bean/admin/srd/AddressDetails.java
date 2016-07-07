package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

/**
 * @author prajakta.bhandare
 * @date 21 Jan 2013
 */
public class AddressDetails extends BeanBase implements Serializable {

	private String empId;
	private String type;
	private String address1;
	private String address2;
	private String address3;
	private String cityId;
	private String cityName;
	private String stateName;
	private String country;
	private String phone1;
	private String phone2;
	private String faxNo;
	private String mobNo;
	private String emailId;
	private String paraId;
	private String totAddress;
	private String pinCode;
	private String uploadFileName;
	private String isNotGeneralUser;
	private String profileEmpName;
	private String newFlag = "false";
	private String extension;
	TreeMap assetmap;
	private boolean editFlag = false;
	private String emeAddress = "";
	private String emeCity = "";
	private String emeState = "";
	private String emePinCode = "";
	private String emeCountry = "";
	private String emePhoneNum = "";
	private String emeExtNum = "";
	private String emeFaxNum = "";
	private String emeMobileNum = "";
	private String emeEmail = "";
	private String offiAddress = "";
	private String offiCity = "";
	private String offiState = "";
	private String offiPinCode = "";
	private String offiCountry = "";
	private String offiPhoneNum = "";
	private String offiExtNum = "";
	private String offiFaxNum = "";
	private String offiMobileNum = "";
	private String offiEmail = "";
	private String localAddress = "";
	private String localCity = "";
	private String localState = "";
	private String localPinCode = "";
	private String localCountry = "";
	private String localPhoneNum = "";
	private String localExtNum = "";
	private String localFaxNum = "";
	private String localMobileNum = "";
	private String localEmail = "";
	private String emeType = "";
	private String offiType = "";
	private String localType = "";
	private String noData = "true";
	private ArrayList addressList = null;
	private String abbrEmeAddress = "";
	private String abbrOffiAddress = "";
	private String abbrLocalAddress = "";
	private String abbrEmeEmail = "";
	private String abbrOffiEmail = "";
	private String abbrLocalEmail = "";
	private boolean emeFlag = false;
	private boolean offiFlag = false;
	private boolean localFlag = false;
	private String flag = "";

	public AddressDetails() {

	}

	/**
	 * Constructor for AddressDetails
	 * 
	 * @param empId
	 * @param type
	 * @param address1
	 * @param address2
	 * @param address3
	 * @param cityId
	 * @param stateId
	 * @param country
	 * @param phone1
	 * @param phone2
	 * @param faxNo
	 * @param mobNo
	 * @param emailId
	 * @param stateName
	 * @param cityName
	 * @param paraId
	 * @param totAddress
	 */
	public AddressDetails(String empId, String type, String address1,
			String address2, String address3, String cityId, String stateId,
			String country, String phone1, String phone2, String faxNo,
			String mobNo, String emailId, String stateName, String cityName,
			String paraId, String totAddress, String emeAddress,
			String emeCity, String emeState, String emePinCode,
			String emeCountry, String emePhoneNum, String emeExtNum,
			String emeFaxNum, String emeMobileNum, String emeEmail) {

		this.empId = empId;
		this.type = type;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.cityId = cityId;
		this.stateName = stateName;
		this.country = country;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.faxNo = faxNo;
		this.mobNo = mobNo;
		this.emailId = emailId;
		this.cityName = cityName;
		this.paraId = paraId;
		this.totAddress = totAddress;
		this.emeAddress = emeAddress;
		this.emeCity = emeCity;
		this.emeState = emeState;
		this.emePinCode = emePinCode;
		this.emeCountry = emeCountry;
		this.emePhoneNum = emePhoneNum;
		this.emeExtNum = emeExtNum;
		this.emeFaxNum = emeFaxNum;
		this.emeMobileNum = emeMobileNum;
		this.emeEmail = emeEmail;
	}

	/**
	 * @return flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 * the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return abbrEmeAddress
	 */
	public String getAbbrEmeAddress() {
		return abbrEmeAddress;
	}

	/**
	 * @return emeFlag
	 */
	public boolean isEmeFlag() {
		return emeFlag;
	}

	/**
	 * @param emeFlag
	 *            the emeFlag to set
	 */
	public void setEmeFlag(boolean emeFlag) {
		this.emeFlag = emeFlag;
	}

	/**
	 * @return offiFlag
	 */
	public boolean isOffiFlag() {
		return offiFlag;
	}

	/**
	 * @param offiFlag
	 *            the offiFlag to set
	 */
	public void setOffiFlag(boolean offiFlag) {
		this.offiFlag = offiFlag;
	}

	/**
	 * @return localFlag
	 */
	public boolean isLocalFlag() {
		return localFlag;
	}

	/**
	 * @param localFlag
	 *            the localFlag to set
	 */
	public void setLocalFlag(boolean localFlag) {
		this.localFlag = localFlag;
	}

	/**
	 * @param abbrEmeAddress
	 *            the abbrEmeAddress to set
	 */
	public void setAbbrEmeAddress(String abbrEmeAddress) {
		this.abbrEmeAddress = abbrEmeAddress;
	}

	/**
	 * @return abbrOffiAddress
	 */
	public String getAbbrOffiAddress() {
		return abbrOffiAddress;
	}

	/**
	 * @param abbrOffiAddress
	 *            the abbrOffiAddress to set
	 */
	public void setAbbrOffiAddress(String abbrOffiAddress) {
		this.abbrOffiAddress = abbrOffiAddress;
	}

	/**
	 * @return abbrLocalAddress
	 */
	public String getAbbrLocalAddress() {
		return abbrLocalAddress;
	}

	/**
	 * @param abbrLocalAddress
	 *            the abbrLocalAddress to set
	 */
	public void setAbbrLocalAddress(String abbrLocalAddress) {
		this.abbrLocalAddress = abbrLocalAddress;
	}

	/**
	 * @return abbrEmeEmail
	 */
	public String getAbbrEmeEmail() {
		return abbrEmeEmail;
	}

	/**
	 * @param abbrEmeEmail
	 *            the abbrEmeEmail to set
	 */
	public void setAbbrEmeEmail(String abbrEmeEmail) {
		this.abbrEmeEmail = abbrEmeEmail;
	}

	/**
	 * @return abbrOffiEmail
	 */
	public String getAbbrOffiEmail() {
		return abbrOffiEmail;
	}

	/**
	 * @param abbrOffiEmail
	 *            the abbrOffiEmail to set
	 */
	public void setAbbrOffiEmail(String abbrOffiEmail) {
		this.abbrOffiEmail = abbrOffiEmail;
	}

	/**
	 * @return abbrLocalEmail
	 */
	public String getAbbrLocalEmail() {
		return abbrLocalEmail;
	}

	/**
	 * @param abbrLocalEmail
	 *            the abbrLocalEmail to set
	 */
	public void setAbbrLocalEmail(String abbrLocalEmail) {
		this.abbrLocalEmail = abbrLocalEmail;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3
	 *            the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

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
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1
	 *            the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2
	 *            the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * @param faxNo
	 *            the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * @return the mobNo
	 */
	public String getMobNo() {
		return mobNo;
	}

	/**
	 * @param mobNo
	 *            the mobNo to set
	 */
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}

	/**
	 * @param paraId
	 *            the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the totAddress
	 */
	public String getTotAddress() {
		return totAddress;
	}

	/**
	 * @param totAddress
	 *            the totAddress to set
	 */
	public void setTotAddress(String totAddress) {
		this.totAddress = totAddress;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode
	 *            the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the assetmap
	 */
	public TreeMap getAssetmap() {
		return assetmap;
	}

	/**
	 * @param assetmap
	 *            the assetmap to set
	 */
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
	}

	/**
	 * @return the addressList
	 */
	public ArrayList getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList
	 *            the addressList to set
	 */
	public void setAddressList(ArrayList addressList) {
		this.addressList = addressList;
	}

	/**
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * @param newFlag
	 *            the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	/**
	 * @return extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 *            the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return emeAddress
	 */
	public String getEmeAddress() {
		return emeAddress;
	}

	/**
	 * @param emeAddress
	 *            the emeAddress to set
	 */
	public void setEmeAddress(String emeAddress) {
		this.emeAddress = emeAddress;
	}

	/**
	 * @return emeCity
	 */
	public String getEmeCity() {
		return emeCity;
	}

	/**
	 * @param emeCity
	 *            the emeCity to set
	 */
	public void setEmeCity(String emeCity) {
		this.emeCity = emeCity;
	}

	/**
	 * @return emeState
	 */
	public String getEmeState() {
		return emeState;
	}

	/**
	 * @param emeState
	 *            the emeState to set
	 */
	public void setEmeState(String emeState) {
		this.emeState = emeState;
	}

	/**
	 * @return emePinCode
	 */
	public String getEmePinCode() {
		return emePinCode;
	}

	/**
	 * @param emePinCode
	 *            the emePinCode to set
	 */
	public void setEmePinCode(String emePinCode) {
		this.emePinCode = emePinCode;
	}

	/**
	 * @return emeCountry
	 */
	public String getEmeCountry() {
		return emeCountry;
	}

	/**
	 * @param emeCountry
	 *            the emeCountry to set
	 */
	public void setEmeCountry(String emeCountry) {
		this.emeCountry = emeCountry;
	}

	/**
	 * @return emePhoneNum
	 */
	public String getEmePhoneNum() {
		return emePhoneNum;
	}

	/**
	 * @param emePhoneNum
	 *            the emePhoneNum to set
	 */
	public void setEmePhoneNum(String emePhoneNum) {
		this.emePhoneNum = emePhoneNum;
	}

	/**
	 * @return emeExtNum
	 */
	public String getEmeExtNum() {
		return emeExtNum;
	}

	/**
	 * @param emeExtNum
	 *            the emeExtNum to set
	 */
	public void setEmeExtNum(String emeExtNum) {
		this.emeExtNum = emeExtNum;
	}

	/**
	 * @return emeFaxNum
	 */
	public String getEmeFaxNum() {
		return emeFaxNum;
	}

	/**
	 * @param emeFaxNum
	 *            the emeFaxNum to set
	 */
	public void setEmeFaxNum(String emeFaxNum) {
		this.emeFaxNum = emeFaxNum;
	}

	/**
	 * @return emeMobileNum
	 */
	public String getEmeMobileNum() {
		return emeMobileNum;
	}

	/**
	 * @param emeMobileNum
	 *            the emeMobileNum to set
	 */
	public void setEmeMobileNum(String emeMobileNum) {
		this.emeMobileNum = emeMobileNum;
	}

	/**
	 * @return emeEmail
	 */
	public String getEmeEmail() {
		return emeEmail;
	}

	/**
	 * @param emeEmail
	 *            the emeEmail to set
	 */
	public void setEmeEmail(String emeEmail) {
		this.emeEmail = emeEmail;
	}

	/**
	 * @return offiAddress
	 */
	public String getOffiAddress() {
		return offiAddress;
	}

	/**
	 * @param offiAddress
	 *            the offiAddress to set
	 */
	public void setOffiAddress(String offiAddress) {
		this.offiAddress = offiAddress;
	}

	/**
	 * @return offiCity
	 */
	public String getOffiCity() {
		return offiCity;
	}

	/**
	 * @param offiCity
	 *            the offiCity to set
	 */
	public void setOffiCity(String offiCity) {
		this.offiCity = offiCity;
	}

	/**
	 * @return offiState
	 */
	public String getOffiState() {
		return offiState;
	}

	/**
	 * @param offiState
	 *            the offiState to set
	 */
	public void setOffiState(String offiState) {
		this.offiState = offiState;
	}

	/**
	 * @return offiPinCode
	 */
	public String getOffiPinCode() {
		return offiPinCode;
	}

	/**
	 * @param offiPinCode
	 *            the offiPinCode to set
	 */
	public void setOffiPinCode(String offiPinCode) {
		this.offiPinCode = offiPinCode;
	}

	/**
	 * @return offiCountry
	 */
	public String getOffiCountry() {
		return offiCountry;
	}

	/**
	 * @param offiCountry
	 *            the offiCountry to set
	 */
	public void setOffiCountry(String offiCountry) {
		this.offiCountry = offiCountry;
	}

	/**
	 * @return offiPhoneNum
	 */
	public String getOffiPhoneNum() {
		return offiPhoneNum;
	}

	/**
	 * @param offiPhoneNum
	 *            the offiPhoneNum to set
	 */
	public void setOffiPhoneNum(String offiPhoneNum) {
		this.offiPhoneNum = offiPhoneNum;
	}

	/**
	 * @return offiExtNum
	 */
	public String getOffiExtNum() {
		return offiExtNum;
	}

	/**
	 * @param offiExtNum
	 *            the offiExtNum to set
	 */
	public void setOffiExtNum(String offiExtNum) {
		this.offiExtNum = offiExtNum;
	}

	/**
	 * @return offiFaxNum
	 */
	public String getOffiFaxNum() {
		return offiFaxNum;
	}

	/**
	 * @param offiFaxNum
	 *            the offiFaxNum to set
	 */
	public void setOffiFaxNum(String offiFaxNum) {
		this.offiFaxNum = offiFaxNum;
	}

	/**
	 * @return offiMobileNum
	 */
	public String getOffiMobileNum() {
		return offiMobileNum;
	}

	/**
	 * @param offiMobileNum
	 *            the offiMobileNum to set
	 */
	public void setOffiMobileNum(String offiMobileNum) {
		this.offiMobileNum = offiMobileNum;
	}

	/**
	 * @return offiEmail
	 */
	public String getOffiEmail() {
		return offiEmail;
	}

	/**
	 * @param offiEmail
	 *            the offiEmail to set
	 */
	public void setOffiEmail(String offiEmail) {
		this.offiEmail = offiEmail;
	}

	/**
	 * @return localAddress
	 */
	public String getLocalAddress() {
		return localAddress;
	}

	/**
	 * @param localAddress
	 *            the localAddress to set
	 */
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	/**
	 * @return localCity
	 */
	public String getLocalCity() {
		return localCity;
	}

	/**
	 * @param localCity
	 *            the localCity to set
	 */
	public void setLocalCity(String localCity) {
		this.localCity = localCity;
	}

	/**
	 * @return localState
	 */
	public String getLocalState() {
		return localState;
	}

	/**
	 * @param localState
	 *            the localState to set
	 */
	public void setLocalState(String localState) {
		this.localState = localState;
	}

	/**
	 * @return localPinCode
	 */
	public String getLocalPinCode() {
		return localPinCode;
	}

	/**
	 * @param localPinCode
	 *            the localPinCode to set
	 */
	public void setLocalPinCode(String localPinCode) {
		this.localPinCode = localPinCode;
	}

	/**
	 * @return localCountry
	 */
	public String getLocalCountry() {
		return localCountry;
	}

	/**
	 * @param localCountry
	 *            the localCountry to set
	 */
	public void setLocalCountry(String localCountry) {
		this.localCountry = localCountry;
	}

	/**
	 * @return localPhoneNum
	 */
	public String getLocalPhoneNum() {
		return localPhoneNum;
	}

	/**
	 * @param localPhoneNum
	 *            the localPhoneNum to set
	 */
	public void setLocalPhoneNum(String localPhoneNum) {
		this.localPhoneNum = localPhoneNum;
	}

	/**
	 * @return localExtNum
	 */
	public String getLocalExtNum() {
		return localExtNum;
	}

	/**
	 * @param localExtNum
	 *            the localExtNum to set
	 */
	public void setLocalExtNum(String localExtNum) {
		this.localExtNum = localExtNum;
	}

	/**
	 * @return localFaxNum
	 */
	public String getLocalFaxNum() {
		return localFaxNum;
	}

	/**
	 * @param localFaxNum
	 *            the localFaxNum to set
	 */
	public void setLocalFaxNum(String localFaxNum) {
		this.localFaxNum = localFaxNum;
	}

	/**
	 * @return localMobileNum
	 */
	public String getLocalMobileNum() {
		return localMobileNum;
	}

	/**
	 * @param localMobileNum
	 *            the localMobileNum to set
	 */
	public void setLocalMobileNum(String localMobileNum) {
		this.localMobileNum = localMobileNum;
	}

	/**
	 * @return localEmail
	 */
	public String getLocalEmail() {
		return localEmail;
	}

	/**
	 * @param localEmail
	 *            the localEmail to set
	 */
	public void setLocalEmail(String localEmail) {
		this.localEmail = localEmail;
	}

	/**
	 * @return emeType
	 */
	public String getEmeType() {
		return emeType;
	}

	/**
	 * @param emeType
	 *            the emeType to set
	 */
	public void setEmeType(String emeType) {
		this.emeType = emeType;
	}

	/**
	 * @return offiType
	 */
	public String getOffiType() {
		return offiType;
	}

	/**
	 * @param offiType
	 *            the offiType to set
	 */
	public void setOffiType(String offiType) {
		this.offiType = offiType;
	}

	/**
	 * @return localType
	 */
	public String getLocalType() {
		return localType;
	}

	/**
	 * @param localType
	 *            the localType to set
	 */
	public void setLocalType(String localType) {
		this.localType = localType;
	}

	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	/**
	 * @param isNotGeneralUser
	 *            the isNotGeneralUser to set
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	/**
	 * @return profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}

	/**
	 * @param profileEmpName
	 *            the profileEmpName to set
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	/**
	 * @return noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData
	 *            the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

}
