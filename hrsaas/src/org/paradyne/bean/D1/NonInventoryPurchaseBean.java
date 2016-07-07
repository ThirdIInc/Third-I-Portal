/**
 * 
 */
package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ganesh
 *
 */
public class NonInventoryPurchaseBean extends BeanBase {
	
	public String shippingPhoneNumber = "";
	public String ppo="";
	public String ppoNo="";
	public String completedBy="";
	public String completedOn="";
	public String asiVendorID="";
	
	public String department="";
	public String departmentCode="";
	public String dataPath="";
	public String uploadFileName="";
	public String checkData="";
	public String FileheaderName="";
	public String status="";
	public String shipTo="";
	public String aSLMPS=null;
	private String  hasWrittenQuote="";
	private String stationary="";
	private String hardwareSoftware="";
	private String  requestType="";
	public String projectDesc="";
	private String  businessObj="";
	private String  recommendations="";
	public String businessBenefits="";	
	private String  resposiblePersonName="";
	private String  resposiblePersonCode="";
	public String assetInformation="";	
	private String  account="";
	private String  manufacture="";
	public String shipVia="";
	public String cear="";
	
	public String stationaryHidden="";
	public String hardwareSoftwareHidden="";
	
	private String  qty="";
	public String unit="";	
	private String  price="";
	private String  comment="";
	public String mrfPart="";
	public String vendorPart="";
	public String desc="";
	private String  ittqty="";
	public String ittunit="";	
	private String  ittprice="";
	private String  ittmanufacture="";
	public String ittmrfPart="";
	public String ittvendorPart="";
	public String ittcomment="";
	public String ittdesc="";
	public ArrayList productList=null;
	
	public String buttonName="";
	public ArrayList listComment=null;
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	public String headerName="";
	
	private String myPage= "";
	private String myPage1= "";
	private String myPage2= "";
	private String flag="";
	private String hiddenValue="";
	//private ArrayList approverList=null;
	private String ittEmployeeToken= "";
	private String ittnonInventoryCode= "";
	private String ittEmployee= "";
	private String ittApplicationDate="";
	//private String ittnonInventoryCode="";
	private String defoultApproverToken="";
	private String changeApproverCode="";
	
	
	private ArrayList selectList=null;
	private String selectTypeCode= "";
	private String selectTypeName="";
	private String ittselectTypeCode= "";
	private String ittselectTypeName="";
	private String ittnoOfLots="";
	private String noOfLots="";
	
	private ArrayList listDraft=null;
	private ArrayList listReject=null;
	private ArrayList listApprove=null;
	private ArrayList listResubmit=null;
	private ArrayList listInProgress=null;
	private ArrayList listSendBack=null;
	
	private String nonInventoryCode= "";
	private String additionalCityCode= "";
	private String compCityNameCode= "";	
	private String employeeToken= ""; 
	private String employeeName= "";
	private String employeeCode= "";
	private String preparerPhone= "";
	private String extension= "";
	private String preparerFax= "";
	private String address= "";
	private String cityName= "";
	private String cityId= "";
	private String stateName= "";
	private String country= "";
	private	String zip= "";
	//private String myPage= "";
	private String attn= "";
	private String imprintType= "";
	
	private String imprintId= "";
	
	private String imprintName= "";
	private String companyName= "";
	private String title= "";
	private String companyAddress= "";
	private String compCityName= "";
	private String companyState= "";
	private String companyZip= "";
	private String compPhoneNumber= "";
	private String compFaxNumber= "";
	private String compOtherNumber= "";
	private String compDescription= "";
	private String internet= "";
	private String businessCardsRegular= "";
	private String envelope10x13= "";
	private String businessCardsCne= "";
	private String letterheadRegular= "";
	private String businessCardsLogo= "";
	private String letterheadManager= "";
	private String envelopeRegular= "";
	private String memoPads= "";
	private String envelopeWindow= "";
	private String memoLoose= "";
	private String envelope12Window= "";
	private String labelFrom= "";
	private String envelope9x12= "";
	private String labelFromTo= "";
	private String letterheadLogo= "";
	private String memoManager= "";
	private String additionalName= "";
	private String additionalCompanyName= "";
	private String additionalTitle= "";
	private String additionalAddress= "";
	private String additionalCity= "";
	private String additionalState= "";
	private String additionalZip= "";
	private String additionalPhoneNumber= "";
	private String additionalFax= "";
	private String additionalOtherNumber= "";
	private String additionalDesc= "";
	private String additionalInternet= "";
	
	private String approverCode = "";
	private String approverToken = "";
	private String approverName = "";
	
	private String selectapproverName = "";
	
	private String comments = "";
	// FILDS FOR APPROVER LIST
	private ArrayList approverList;
	private String srNoIterator = "";

	/**
	 * ADDED FOR SUGGESTED VENDOR
	 * @return
	 */
	private String vendorName = "";
	private String vendorPhoneNo = "";
	private String vendorEmailId = "";
	private String vendorShipVia = "";
	private String departmentAbbr = "";
	
	public String getDepartmentAbbr() {
                return departmentAbbr;
        }

        public void setDepartmentAbbr(String departmentAbbr) {
                this.departmentAbbr = departmentAbbr;
        }

        public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getPreparerPhone() {
		return preparerPhone;
	}

	public void setPreparerPhone(String preparerPhone) {
		this.preparerPhone = preparerPhone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPreparerFax() {
		return preparerFax;
	}

	public void setPreparerFax(String preparerFax) {
		this.preparerFax = preparerFax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getAttn() {
		return attn;
	}

	public void setAttn(String attn) {
		this.attn = attn;
	}

	public String getImprintType() {
		return imprintType;
	}

	public void setImprintType(String imprintType) {
		this.imprintType = imprintType;
	}

	public String getImprintId() {
		return imprintId;
	}

	public void setImprintId(String imprintId) {
		this.imprintId = imprintId;
	}

	public String getImprintName() {
		return imprintName;
	}

	public void setImprintName(String imprintName) {
		this.imprintName = imprintName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompCityName() {
		return compCityName;
	}

	public void setCompCityName(String compCityName) {
		this.compCityName = compCityName;
	}

	public String getCompanyState() {
		return companyState;
	}

	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	public String getCompanyZip() {
		return companyZip;
	}

	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}

	public String getCompPhoneNumber() {
		return compPhoneNumber;
	}

	public void setCompPhoneNumber(String compPhoneNumber) {
		this.compPhoneNumber = compPhoneNumber;
	}

	public String getCompFaxNumber() {
		return compFaxNumber;
	}

	public void setCompFaxNumber(String compFaxNumber) {
		this.compFaxNumber = compFaxNumber;
	}

	public String getCompOtherNumber() {
		return compOtherNumber;
	}

	public void setCompOtherNumber(String compOtherNumber) {
		this.compOtherNumber = compOtherNumber;
	}

	public String getCompDescription() {
		return compDescription;
	}

	public void setCompDescription(String compDescription) {
		this.compDescription = compDescription;
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getBusinessCardsRegular() {
		return businessCardsRegular;
	}

	public void setBusinessCardsRegular(String businessCardsRegular) {
		this.businessCardsRegular = businessCardsRegular;
	}

	public String getEnvelope10x13() {
		return envelope10x13;
	}

	public void setEnvelope10x13(String envelope10x13) {
		this.envelope10x13 = envelope10x13;
	}

	public String getBusinessCardsCne() {
		return businessCardsCne;
	}

	public void setBusinessCardsCne(String businessCardsCne) {
		this.businessCardsCne = businessCardsCne;
	}

	public String getLetterheadRegular() {
		return letterheadRegular;
	}

	public void setLetterheadRegular(String letterheadRegular) {
		this.letterheadRegular = letterheadRegular;
	}

	public String getBusinessCardsLogo() {
		return businessCardsLogo;
	}

	public void setBusinessCardsLogo(String businessCardsLogo) {
		this.businessCardsLogo = businessCardsLogo;
	}

	public String getLetterheadManager() {
		return letterheadManager;
	}

	public void setLetterheadManager(String letterheadManager) {
		this.letterheadManager = letterheadManager;
	}

	public String getEnvelopeRegular() {
		return envelopeRegular;
	}

	public void setEnvelopeRegular(String envelopeRegular) {
		this.envelopeRegular = envelopeRegular;
	}

	public String getMemoPads() {
		return memoPads;
	}

	public void setMemoPads(String memoPads) {
		this.memoPads = memoPads;
	}

	public String getEnvelopeWindow() {
		return envelopeWindow;
	}

	public void setEnvelopeWindow(String envelopeWindow) {
		this.envelopeWindow = envelopeWindow;
	}

	public String getMemoLoose() {
		return memoLoose;
	}

	public void setMemoLoose(String memoLoose) {
		this.memoLoose = memoLoose;
	}

	public String getEnvelope12Window() {
		return envelope12Window;
	}

	public void setEnvelope12Window(String envelope12Window) {
		this.envelope12Window = envelope12Window;
	}

	public String getLabelFrom() {
		return labelFrom;
	}

	public void setLabelFrom(String labelFrom) {
		this.labelFrom = labelFrom;
	}

	public String getEnvelope9x12() {
		return envelope9x12;
	}

	public void setEnvelope9x12(String envelope9x12) {
		this.envelope9x12 = envelope9x12;
	}

	public String getLabelFromTo() {
		return labelFromTo;
	}

	public void setLabelFromTo(String labelFromTo) {
		this.labelFromTo = labelFromTo;
	}

	public String getLetterheadLogo() {
		return letterheadLogo;
	}

	public void setLetterheadLogo(String letterheadLogo) {
		this.letterheadLogo = letterheadLogo;
	}

	public String getMemoManager() {
		return memoManager;
	}

	public void setMemoManager(String memoManager) {
		this.memoManager = memoManager;
	}

	public String getAdditionalName() {
		return additionalName;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}

	public String getAdditionalCompanyName() {
		return additionalCompanyName;
	}

	public void setAdditionalCompanyName(String additionalCompanyName) {
		this.additionalCompanyName = additionalCompanyName;
	}

	public String getAdditionalTitle() {
		return additionalTitle;
	}

	public void setAdditionalTitle(String additionalTitle) {
		this.additionalTitle = additionalTitle;
	}

	public String getAdditionalAddress() {
		return additionalAddress;
	}

	public void setAdditionalAddress(String additionalAddress) {
		this.additionalAddress = additionalAddress;
	}

	public String getAdditionalCity() {
		return additionalCity;
	}

	public void setAdditionalCity(String additionalCity) {
		this.additionalCity = additionalCity;
	}

	public String getAdditionalState() {
		return additionalState;
	}

	public void setAdditionalState(String additionalState) {
		this.additionalState = additionalState;
	}

	public String getAdditionalZip() {
		return additionalZip;
	}

	public void setAdditionalZip(String additionalZip) {
		this.additionalZip = additionalZip;
	}

	public String getAdditionalPhoneNumber() {
		return additionalPhoneNumber;
	}

	public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
		this.additionalPhoneNumber = additionalPhoneNumber;
	}

	public String getAdditionalFax() {
		return additionalFax;
	}

	public void setAdditionalFax(String additionalFax) {
		this.additionalFax = additionalFax;
	}

	public String getAdditionalOtherNumber() {
		return additionalOtherNumber;
	}

	public void setAdditionalOtherNumber(String additionalOtherNumber) {
		this.additionalOtherNumber = additionalOtherNumber;
	}

	public String getAdditionalDesc() {
		return additionalDesc;
	}

	public void setAdditionalDesc(String additionalDesc) {
		this.additionalDesc = additionalDesc;
	}

	public String getAdditionalInternet() {
		return additionalInternet;
	}

	public void setAdditionalInternet(String additionalInternet) {
		this.additionalInternet = additionalInternet;
	}

	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public String getApproverToken() {
		return approverToken;
	}

	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getSelectapproverName() {
		return selectapproverName;
	}

	public void setSelectapproverName(String selectapproverName) {
		this.selectapproverName = selectapproverName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAdditionalCityCode() {
		return additionalCityCode;
	}

	public void setAdditionalCityCode(String additionalCityCode) {
		this.additionalCityCode = additionalCityCode;
	}

	public String getCompCityNameCode() {
		return compCityNameCode;
	}

	public void setCompCityNameCode(String compCityNameCode) {
		this.compCityNameCode = compCityNameCode;
	}

	public String getNonInventoryCode() {
		return nonInventoryCode;
	}

	public void setNonInventoryCode(String nonInventoryCode) {
		this.nonInventoryCode = nonInventoryCode;
	}

	public String getMyPage1() {
		return myPage1;
	}

	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}

	public String getMyPage2() {
		return myPage2;
	}

	public void setMyPage2(String myPage2) {
		this.myPage2 = myPage2;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}

	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}

	public String getIttnonInventoryCode() {
		return ittnonInventoryCode;
	}

	public void setIttnonInventoryCode(String ittnonInventoryCode) {
		this.ittnonInventoryCode = ittnonInventoryCode;
	}

	public String getIttEmployee() {
		return ittEmployee;
	}

	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}

	public String getIttApplicationDate() {
		return ittApplicationDate;
	}

	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}

	public ArrayList getListDraft() {
		return listDraft;
	}

	public void setListDraft(ArrayList listDraft) {
		this.listDraft = listDraft;
	}

	public ArrayList getListReject() {
		return listReject;
	}

	public void setListReject(ArrayList listReject) {
		this.listReject = listReject;
	}

	public ArrayList getListApprove() {
		return listApprove;
	}

	public void setListApprove(ArrayList listApprove) {
		this.listApprove = listApprove;
	}

	public ArrayList getListResubmit() {
		return listResubmit;
	}

	public void setListResubmit(ArrayList listResubmit) {
		this.listResubmit = listResubmit;
	}

	public ArrayList getListInProgress() {
		return listInProgress;
	}

	public void setListInProgress(ArrayList listInProgress) {
		this.listInProgress = listInProgress;
	}

	public ArrayList getListSendBack() {
		return listSendBack;
	}

	public void setListSendBack(ArrayList listSendBack) {
		this.listSendBack = listSendBack;
	}

	public String getDefoultApproverToken() {
		return defoultApproverToken;
	}

	public void setDefoultApproverToken(String defoultApproverToken) {
		this.defoultApproverToken = defoultApproverToken;
	}

	public String getChangeApproverCode() {
		return changeApproverCode;
	}

	public void setChangeApproverCode(String changeApproverCode) {
		this.changeApproverCode = changeApproverCode;
	}

	public ArrayList getSelectList() {
		return selectList;
	}

	public void setSelectList(ArrayList selectList) {
		this.selectList = selectList;
	}

	public String getSelectTypeCode() {
		return selectTypeCode;
	}

	public void setSelectTypeCode(String selectTypeCode) {
		this.selectTypeCode = selectTypeCode;
	}

	public String getSelectTypeName() {
		return selectTypeName;
	}

	public void setSelectTypeName(String selectTypeName) {
		this.selectTypeName = selectTypeName;
	}

	public String getIttselectTypeCode() {
		return ittselectTypeCode;
	}

	public void setIttselectTypeCode(String ittselectTypeCode) {
		this.ittselectTypeCode = ittselectTypeCode;
	}

	public String getIttselectTypeName() {
		return ittselectTypeName;
	}

	public void setIttselectTypeName(String ittselectTypeName) {
		this.ittselectTypeName = ittselectTypeName;
	}

	public String getIttnoOfLots() {
		return ittnoOfLots;
	}

	public void setIttnoOfLots(String ittnoOfLots) {
		this.ittnoOfLots = ittnoOfLots;
	}

	public String getNoOfLots() {
		return noOfLots;
	}

	public void setNoOfLots(String noOfLots) {
		this.noOfLots = noOfLots;
	}

	public String getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	public ArrayList getListComment() {
		return listComment;
	}

	public void setListComment(ArrayList listComment) {
		this.listComment = listComment;
	}

	public String getIttApproverName() {
		return ittApproverName;
	}

	public void setIttApproverName(String ittApproverName) {
		this.ittApproverName = ittApproverName;
	}

	public String getIttComments() {
		return ittComments;
	}

	public void setIttComments(String ittComments) {
		this.ittComments = ittComments;
	}

	public String getIttStatus() {
		return ittStatus;
	}

	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getASLMPS() {
		return aSLMPS;
	}

	public void setASLMPS(String aslmps) {
		aSLMPS = aslmps;
	}

	public String getHasWrittenQuote() {
		return hasWrittenQuote;
	}

	public void setHasWrittenQuote(String hasWrittenQuote) {
		this.hasWrittenQuote = hasWrittenQuote;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getBusinessObj() {
		return businessObj;
	}

	public void setBusinessObj(String businessObj) {
		this.businessObj = businessObj;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getBusinessBenefits() {
		return businessBenefits;
	}

	public void setBusinessBenefits(String businessBenefits) {
		this.businessBenefits = businessBenefits;
	}

	public String getResposiblePersonName() {
		return resposiblePersonName;
	}

	public void setResposiblePersonName(String resposiblePersonName) {
		this.resposiblePersonName = resposiblePersonName;
	}

	public String getResposiblePersonCode() {
		return resposiblePersonCode;
	}

	public void setResposiblePersonCode(String resposiblePersonCode) {
		this.resposiblePersonCode = resposiblePersonCode;
	}

	public String getAssetInformation() {
		return assetInformation;
	}

	public void setAssetInformation(String assetInformation) {
		this.assetInformation = assetInformation;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getShipVia() {
		return shipVia;
	}

	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	public String getCear() {
		return cear;
	}

	public void setCear(String cear) {
		this.cear = cear;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMrfPart() {
		return mrfPart;
	}

	public void setMrfPart(String mrfPart) {
		this.mrfPart = mrfPart;
	}

	public String getVendorPart() {
		return vendorPart;
	}

	public void setVendorPart(String vendorPart) {
		this.vendorPart = vendorPart;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIttqty() {
		return ittqty;
	}

	public void setIttqty(String ittqty) {
		this.ittqty = ittqty;
	}

	public String getIttunit() {
		return ittunit;
	}

	public void setIttunit(String ittunit) {
		this.ittunit = ittunit;
	}

	public String getIttprice() {
		return ittprice;
	}

	public void setIttprice(String ittprice) {
		this.ittprice = ittprice;
	}

	public String getIttmanufacture() {
		return ittmanufacture;
	}

	public void setIttmanufacture(String ittmanufacture) {
		this.ittmanufacture = ittmanufacture;
	}

	public String getIttmrfPart() {
		return ittmrfPart;
	}

	public void setIttmrfPart(String ittmrfPart) {
		this.ittmrfPart = ittmrfPart;
	}

	public String getIttvendorPart() {
		return ittvendorPart;
	}

	public void setIttvendorPart(String ittvendorPart) {
		this.ittvendorPart = ittvendorPart;
	}

	public String getIttcomment() {
		return ittcomment;
	}

	public void setIttcomment(String ittcomment) {
		this.ittcomment = ittcomment;
	}

	public String getIttdesc() {
		return ittdesc;
	}

	public void setIttdesc(String ittdesc) {
		this.ittdesc = ittdesc;
	}

	public ArrayList getProductList() {
		return productList;
	}

	public void setProductList(ArrayList productList) {
		this.productList = productList;
	}

	public String getStationary() {
		return stationary;
	}

	public void setStationary(String stationary) {
		this.stationary = stationary;
	}

	public String getHardwareSoftware() {
		return hardwareSoftware;
	}

	public void setHardwareSoftware(String hardwareSoftware) {
		this.hardwareSoftware = hardwareSoftware;
	}

	public String getStationaryHidden() {
		return stationaryHidden;
	}

	public void setStationaryHidden(String stationaryHidden) {
		this.stationaryHidden = stationaryHidden;
	}

	public String getHardwareSoftwareHidden() {
		return hardwareSoftwareHidden;
	}

	public void setHardwareSoftwareHidden(String hardwareSoftwareHidden) {
		this.hardwareSoftwareHidden = hardwareSoftwareHidden;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileheaderName() {
		return FileheaderName;
	}

	public void setFileheaderName(String fileheaderName) {
		FileheaderName = fileheaderName;
	}

	public String getCheckData() {
		return checkData;
	}

	public void setCheckData(String checkData) {
		this.checkData = checkData;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public String getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorPhoneNo() {
		return vendorPhoneNo;
	}

	public void setVendorPhoneNo(String vendorPhoneNo) {
		this.vendorPhoneNo = vendorPhoneNo;
	}

	public String getVendorEmailId() {
		return vendorEmailId;
	}

	public void setVendorEmailId(String vendorEmailId) {
		this.vendorEmailId = vendorEmailId;
	}

	public String getVendorShipVia() {
		return vendorShipVia;
	}

	public void setVendorShipVia(String vendorShipVia) {
		this.vendorShipVia = vendorShipVia;
	}

	public String getAsiVendorID() {
		return asiVendorID;
	}

	public void setAsiVendorID(String asiVendorID) {
		this.asiVendorID = asiVendorID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getPpo() {
		return ppo;
	}

	public void setPpo(String ppo) {
		this.ppo = ppo;
	}

	public String getPpoNo() {
		return ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	/**
	 * @return the shippingPhoneNumber
	 */
	public String getShippingPhoneNumber() {
		return this.shippingPhoneNumber;
	}

	/**
	 * @param shippingPhoneNumber the shippingPhoneNumber to set
	 */
	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}
	
	
	
	
	
	

}
