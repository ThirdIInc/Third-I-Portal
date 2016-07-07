package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Prasad
 */

public class RecruitmentPartners extends BeanBase implements Serializable{
	
	private String recpartnerCode;
	private String recPartnerName;
	private String recContactPerson;
	private String recPartnerType;
	private String recPartnerAddress;
	private String recPartnerCity;
	private String cityCode;
	private String phoneNo;
	private String pinCode;
	private String mobileNo;
	private String email;
	private String faxNo;
	private String charges;
	private String partnerDesc;
	private String status;
	private String hdomainCode;
	private String noData="false";
	private String dataLength="";
	private ArrayList recpartnerList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
	private String pageRecPartnerType;
	private String editViewFlag="";
	private String passMessage="";
	private String chkAll="";
	private String currentView ="";
	//Updated By Anantha lakshmi
	private String termsCond="";
	private String startDate="";
	private String endDate="";
	public String getCurrentView() {
		return currentView;
	}
	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}
	/**
	 * @return the recpartnerCode
	 */
	public String getRecpartnerCode() {
		return recpartnerCode;
	}
	/**
	 * @return the recPartnerName
	 */
	public String getRecPartnerName() {
		return recPartnerName;
	}
	/**
	 * @return the recContactPerson
	 */
	public String getRecContactPerson() {
		return recContactPerson;
	}
	/**
	 * @return the recPartnerType
	 */
	public String getRecPartnerType() {
		return recPartnerType;
	}
	/**
	 * @return the recPartnerAddress
	 */
	public String getRecPartnerAddress() {
		return recPartnerAddress;
	}
	/**
	 * @return the recPartnerCity
	 */
	public String getRecPartnerCity() {
		return recPartnerCity;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}
	/**
	 * @return the charges
	 */
	public String getCharges() {
		return charges;
	}
	/**
	 * @return the partnerDesc
	 */
	public String getPartnerDesc() {
		return partnerDesc;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @return the hdomainCode
	 */
	public String getHdomainCode() {
		return hdomainCode;
	}
	/**
	 * @return the recpartnerList
	 */
	public ArrayList getRecpartnerList() {
		return recpartnerList;
	}
	/**
	 * @return the confChk
	 */
	public String getConfChk() {
		return confChk;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param recpartnerCode the recpartnerCode to set
	 */
	public void setRecpartnerCode(String recpartnerCode) {
		this.recpartnerCode = recpartnerCode;
	}
	/**
	 * @param recPartnerName the recPartnerName to set
	 */
	public void setRecPartnerName(String recPartnerName) {
		this.recPartnerName = recPartnerName;
	}
	/**
	 * @param recContactPerson the recContactPerson to set
	 */
	public void setRecContactPerson(String recContactPerson) {
		this.recContactPerson = recContactPerson;
	}
	/**
	 * @param recPartnerType the recPartnerType to set
	 */
	public void setRecPartnerType(String recPartnerType) {
		this.recPartnerType = recPartnerType;
	}
	/**
	 * @param recPartnerAddress the recPartnerAddress to set
	 */
	public void setRecPartnerAddress(String recPartnerAddress) {
		this.recPartnerAddress = recPartnerAddress;
	}
	/**
	 * @param recPartnerCity the recPartnerCity to set
	 */
	public void setRecPartnerCity(String recPartnerCity) {
		this.recPartnerCity = recPartnerCity;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	/**
	 * @param charges the charges to set
	 */
	public void setCharges(String charges) {
		this.charges = charges;
	}
	/**
	 * @param partnerDesc the partnerDesc to set
	 */
	public void setPartnerDesc(String partnerDesc) {
		this.partnerDesc = partnerDesc;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param hdomainCode the hdomainCode to set
	 */
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	/**
	 * @param recpartnerList the recpartnerList to set
	 */
	public void setRecpartnerList(ArrayList recpartnerList) {
		this.recpartnerList = recpartnerList;
	}
	/**
	 * @param confChk the confChk to set
	 */
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getPageRecPartnerType() {
		return pageRecPartnerType;
	}
	public void setPageRecPartnerType(String pageRecPartnerType) {
		this.pageRecPartnerType = pageRecPartnerType;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getEditViewFlag() {
		return editViewFlag;
	}
	public void setEditViewFlag(String editViewFlag) {
		this.editViewFlag = editViewFlag;
	}
	public String getPassMessage() {
		return passMessage;
	}
	public void setPassMessage(String passMessage) {
		this.passMessage = passMessage;
	}
	public String getChkAll() {
		return chkAll;
	}
	public void setChkAll(String chkAll) {
		this.chkAll = chkAll;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getTermsCond() {
		return termsCond;
	}
	public void setTermsCond(String termsCond) {
		this.termsCond = termsCond;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
