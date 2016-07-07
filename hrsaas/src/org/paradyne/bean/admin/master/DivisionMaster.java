package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author pradeep kumar
 * @modified by Reeba 
 *
 */
public class DivisionMaster extends BeanBase{
	private String divisionDisplayName ="";
	private String divCode="";
	private String  noData="false";
	private String modeLength="false";
	private String totalRecords="";
	private String divId="";
	private	String divName="";
	private String divAbbr;
	private String divDesc="";
	private	String locId;
	private String locName;
	private	String city;
	private	String add1;
	private	String add2;
	private	String add3;
	private	String pin;
	private	String tel;
	private	String telStd;
	private	String website;
	private String myPage;
	private String show;
	private String hiddencode;
	private String hdeleteCode;
	ArrayList divList=null;
	private String isActive;
	
	
	private String emailAdress="";
	private String dtCommencementBusiness="";
	private String lglStsEstablishment="";
	private String lglStsEstablishmentId="";
	private String ownership="";
	private String registrationNo="";
	private String expiryDt="";
	private String titleAct="";
	private String ename="";
	private String edesignation="";
	private String etelStd="";
	private String eteleNo="";
	private String emobNo="";
	private String efax="";
	private String eemail="";
	private String mname="";
	private String mdesignation="";
	private String mteleNo="";
	private String mmobNo="";
	private String mfax="";
	private String memail="";
	private String titleActId=""; 
	private String ownershipId="";
	private String logoName="";

	
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivDesc() {
		return divDesc;
	}
	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}
	
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public ArrayList getDivList() {
		return divList;
	}
	public void setDivList(ArrayList divList) {
		this.divList = divList;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
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
	
	public String getDivAbbr() {
		return divAbbr;
	}
	public void setDivAbbr(String divAbbr) {
		this.divAbbr = divAbbr;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
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
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEdesignation() {
		return edesignation;
	}
	public void setEdesignation(String edesignation) {
		this.edesignation = edesignation;
	}
	public String getEteleNo() {
		return eteleNo;
	}
	public void setEteleNo(String eteleNo) {
		this.eteleNo = eteleNo;
	}
	public String getEmobNo() {
		return emobNo;
	}
	public void setEmobNo(String emobNo) {
		this.emobNo = emobNo;
	}
	public String getEfax() {
		return efax;
	}
	public void setEfax(String efax) {
		this.efax = efax;
	}
	public String getEemail() {
		return eemail;
	}
	public void setEemail(String eemail) {
		this.eemail = eemail;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMdesignation() {
		return mdesignation;
	}
	public void setMdesignation(String mdesignation) {
		this.mdesignation = mdesignation;
	}
	public String getMteleNo() {
		return mteleNo;
	}
	public void setMteleNo(String mteleNo) {
		this.mteleNo = mteleNo;
	}
	public String getMmobNo() {
		return mmobNo;
	}
	public void setMmobNo(String mmobNo) {
		this.mmobNo = mmobNo;
	}
	public String getMfax() {
		return mfax;
	}
	public void setMfax(String mfax) {
		this.mfax = mfax;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getDtCommencementBusiness() {
		return dtCommencementBusiness;
	}
	public void setDtCommencementBusiness(String dtCommencementBusiness) {
		this.dtCommencementBusiness = dtCommencementBusiness;
	}
	public String getLglStsEstablishment() {
		return lglStsEstablishment;
	}
	public void setLglStsEstablishment(String lglStsEstablishment) {
		this.lglStsEstablishment = lglStsEstablishment;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public String getTitleAct() {
		return titleAct;
	}
	public void setTitleAct(String titleAct) {
		this.titleAct = titleAct;
	}
	
	public String getLglStsEstablishmentId() {
		return lglStsEstablishmentId;
	}
	public void setLglStsEstablishmentId(String lglStsEstablishmentId) {
		this.lglStsEstablishmentId = lglStsEstablishmentId;
	}
	public String getTitleActId() {
		return titleActId;
	}
	public void setTitleActId(String titleActId) {
		this.titleActId = titleActId;
	}
	public String getOwnershipId() {
		return ownershipId;
	}
	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTelStd() {
		return telStd;
	}
	public void setTelStd(String telStd) {
		this.telStd = telStd;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public String getEtelStd() {
		return etelStd;
	}
	public void setEtelStd(String etelStd) {
		this.etelStd = etelStd;
	}
	public String getDivisionDisplayName() {
		return divisionDisplayName;
	}
	public void setDivisionDisplayName(String divisionDisplayName) {
		this.divisionDisplayName = divisionDisplayName;
	}
	
		
}
