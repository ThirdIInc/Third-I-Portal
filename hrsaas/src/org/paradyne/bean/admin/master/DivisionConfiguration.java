package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author pradeep kumar
 * @modified by Reeba 
 * @modified by Anantha lakshmi
 */
public class DivisionConfiguration extends BeanBase{
	private String divCode="";
	private String  noData="false";
	private String modeLength="false";
	private String totalRecords="";
	private String divId="";
	private	String divName="";
	private String divAbbr;
	private String divDesc="";
	private	String protaxregNo;
	private	String panNo;
	private	String tanNo;
	private	String esiZone;
	private	String accno;
	private	String bank;
	private	String bankid;
	private String myPage;
	private String show;
	private String hiddencode;
	private String hdeleteCode;
	private String pfNumber;
	
	private String acctGrpNo;
	private String  estbCode="";
	ArrayList divList=null;
	
	// addded 03-09-2010
	
	private String esiNumber;
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
	//updated by  Anantha
	
	private String citAddress="";
	private String citCity="";
	private String citCityId="";
	private String citPinCode="";
	
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
	
	
	
	public String getProtaxregNo() {
		return protaxregNo;
	}
	public void setProtaxregNo(String protaxregNo) {
		this.protaxregNo = protaxregNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getTanNo() {
		return tanNo;
	}
	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}
	public String getEsiZone() {
		return esiZone;
	}
	public void setEsiZone(String esiZone) {
		this.esiZone = esiZone;
	}
	
	public String getAcctGrpNo() {
		return acctGrpNo;
	}
	public void setAcctGrpNo(String acctGrpNo) {
		this.acctGrpNo = acctGrpNo;
	}
	
	public String getEsiNumber() {
		return esiNumber;
	}
	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getCitAddress() {
		return citAddress;
	}
	public void setCitAddress(String citAddress) {
		this.citAddress = citAddress;
	}
	public String getCitCity() {
		return citCity;
	}
	public void setCitCity(String citCity) {
		this.citCity = citCity;
	}
	public String getCitPinCode() {
		return citPinCode;
	}
	public void setCitPinCode(String citPinCode) {
		this.citPinCode = citPinCode;
	}
	public String getCitCityId() {
		return citCityId;
	}
	public void setCitCityId(String citCityId) {
		this.citCityId = citCityId;
	}
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}
	
	
	public ArrayList getDivList() {
		return divList;
	}
	public void setDivList(ArrayList divList) {
		this.divList = divList;
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
	public String getEstbCode() {
		return estbCode;
	}
	public void setEstbCode(String estbCode) {
		this.estbCode = estbCode;
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

		
}
