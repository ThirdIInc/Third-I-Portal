/**
 * 
 */
package org.paradyne.bean.TravelManagement.Config;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0417
 *
 */
public class TravelPolicy extends BeanBase {
    // FOR GRADE
	private String empGrade="";
	private String empGradeId="";
	private String hidGradeStatus="";
	private String hidFlag="";
	private String expTravelFlag="false";
	private String readOnlyFlag="";
 
	private ArrayList gradeList=null;
	
	//for Expense
	private String exCategory="";
	private String exCategoryId="";
	private String expCatUnit="";
	private String expCatUnitCode="";
	
	
	
	private String expValuewithBill="";
	private String expValuewithoutBill="";
	
	
	
	
	private String totExpAmt="0";
	private String actualAtt="";
	private String hidActualAtt="N";
	private ArrayList expList=null;
	
	//for Travel
	private String travelMode="";
	private String travelModeId="";
	private String travelClass=""; 
	private String travelClassId=""; 
	private String  trModeStatus="";
	private String expCateTravel=""; 
	private String expCateTravelId="";	
	private String expCateTravelUnit=""; 
	private String hidtrModeStatus="";  
	private ArrayList travelModeList=null;   
	
 
 
	//for Hotel type
	private String hotelType="";
	private String hotelTypeId="";
	private String roomType=""; 
	private String roomTypeId=""; 
	private String expCateHotel=""; 
	private String expCateHotelId=""; 
	private String expCateHotelUnit=""; 
	private String hotelStatus="";  
	private String hidHotelStatus="";
	private ArrayList hotelTypeList=null; 
	 
	//for Falg
	private String loadFlag="true";
	private String addNewFlag="false";
	private String editFlag="false";
	private String saveFlag="false";
	
	
	//for POLICY 	
	private String policyId="";
	private String policyName="";	
	private String travelGrade="";	
	private String travelGradeId="";	
	private String effectDate="";	
	private String settleDays="";	
	private String policySysDate="";	
	private String policyAbbr="";	
	private String desc="";	
	private String status="";	
	private String guidLines="";	
	
	private String policyAdvanceAllowed="";
	
	
	
	
	//for Grade 	
	private String gradeStatus="";	 
	
	//for search list
  
	private String itPolicyName="";
	private String itPolicyAbbr="";
	private String itEffectDate=""; 
	private String itStatus="";
	private String itPolicyId=""; 
	private String searchFlag="false"; 
	private String editStatus=""; 
	
 
	ArrayList policyList = new ArrayList();
	
	public ArrayList getPolicyList() {
		return policyList;
	}
	public void setPolicyList(ArrayList policyList) {
		this.policyList = policyList;
	}
	public String getItPolicyName() {
		return itPolicyName;
	}
	public void setItPolicyName(String itPolicyName) {
		this.itPolicyName = itPolicyName;
	}
	public String getItPolicyAbbr() {
		return itPolicyAbbr;
	}
	public void setItPolicyAbbr(String itPolicyAbbr) {
		this.itPolicyAbbr = itPolicyAbbr;
	}
	public String getItEffectDate() {
		return itEffectDate;
	}
	public void setItEffectDate(String itEffectDate) {
		this.itEffectDate = itEffectDate;
	}
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}
	public String getItPolicyId() {
		return itPolicyId;
	}
	public void setItPolicyId(String itPolicyId) {
		this.itPolicyId = itPolicyId;
	}
 
	public String getExCategory() {
		return exCategory;
	}
	public void setExCategory(String exCategory) {
		this.exCategory = exCategory;
	}
	public String getExCategoryId() {
		return exCategoryId;
	}
	public void setExCategoryId(String exCategoryId) {
		this.exCategoryId = exCategoryId;
	}
	public String getExpCatUnit() {
		return expCatUnit;
	}
	public void setExpCatUnit(String expCatUnit) {
		this.expCatUnit = expCatUnit;
	}
	public ArrayList getExpList() {
		return expList;
	}
	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}
	public String getEmpGradeId() {
		return empGradeId;
	}
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}
	public ArrayList getGradeList() {
		return gradeList;
	}
	public void setGradeList(ArrayList gradeList) {
		this.gradeList = gradeList;
	}
	public String getTravelMode() {
		return travelMode;
	}
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}
	public String getTravelModeId() {
		return travelModeId;
	}
	public void setTravelModeId(String travelModeId) {
		this.travelModeId = travelModeId;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public String getTravelClassId() {
		return travelClassId;
	}
	public void setTravelClassId(String travelClassId) {
		this.travelClassId = travelClassId;
	}
	public ArrayList getTravelModeList() {
		return travelModeList;
	}
	public void setTravelModeList(ArrayList travelModeList) {
		this.travelModeList = travelModeList;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public String getHotelTypeId() {
		return hotelTypeId;
	}
	public void setHotelTypeId(String hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public ArrayList getHotelTypeList() {
		return hotelTypeList;
	}
	public void setHotelTypeList(ArrayList hotelTypeList) {
		this.hotelTypeList = hotelTypeList;
	}
	public String getLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(String loadFlag) {
		this.loadFlag = loadFlag;
	}
	public String getAddNewFlag() {
		return addNewFlag;
	}
	public void setAddNewFlag(String addNewFlag) {
		this.addNewFlag = addNewFlag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getTravelGrade() {
		return travelGrade;
	}
	public void setTravelGrade(String travelGrade) {
		this.travelGrade = travelGrade;
	}
	public String getTravelGradeId() {
		return travelGradeId;
	}
	public void setTravelGradeId(String travelGradeId) {
		this.travelGradeId = travelGradeId;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getSettleDays() {
		return settleDays;
	}
	public void setSettleDays(String settleDays) {
		this.settleDays = settleDays;
	}
	public String getPolicySysDate() {
		return policySysDate;
	}
	public void setPolicySysDate(String policySysDate) {
		this.policySysDate = policySysDate;
	}
	public String getPolicyAbbr() {
		return policyAbbr;
	}
	public void setPolicyAbbr(String policyAbbr) {
		this.policyAbbr = policyAbbr;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGuidLines() {
		return guidLines;
	}
	public void setGuidLines(String guidLines) {
		this.guidLines = guidLines;
	}
	public String getGradeStatus() {
		return gradeStatus;
	}
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	
	public String getExpValuewithBill() {
		return expValuewithBill;
	}
	public void setExpValuewithBill(String expValuewithBill) {
		this.expValuewithBill = expValuewithBill;
	}
	public String getExpValuewithoutBill() {
		return expValuewithoutBill;
	}
	public void setExpValuewithoutBill(String expValuewithoutBill) {
		this.expValuewithoutBill = expValuewithoutBill;
	}
	public String getExpCateTravelId() {
		return expCateTravelId;
	}
	public void setExpCateTravelId(String expCateTravelId) {
		this.expCateTravelId = expCateTravelId;
	}
	public String getExpCatUnitCode() {
		return expCatUnitCode;
	}
	public void setExpCatUnitCode(String expCatUnitCode) {
		this.expCatUnitCode = expCatUnitCode;
	}
	public String getTrModeStatus() {
		return trModeStatus;
	}
	public void setTrModeStatus(String trModeStatus) {
		this.trModeStatus = trModeStatus;
	}
	public String getExpCateTravel() {
		return expCateTravel;
	}
	public void setExpCateTravel(String expCateTravel) {
		this.expCateTravel = expCateTravel;
	}
	public String getExpCateTravelUnit() {
		return expCateTravelUnit;
	}
	public void setExpCateTravelUnit(String expCateTravelUnit) {
		this.expCateTravelUnit = expCateTravelUnit;
	}
	public String getExpCateHotel() {
		return expCateHotel;
	}
	public void setExpCateHotel(String expCateHotel) {
		this.expCateHotel = expCateHotel;
	}
	public String getExpCateHotelId() {
		return expCateHotelId;
	}
	public void setExpCateHotelId(String expCateHotelId) {
		this.expCateHotelId = expCateHotelId;
	}
	public String getExpCateHotelUnit() {
		return expCateHotelUnit;
	}
	public void setExpCateHotelUnit(String expCateHotelUnit) {
		this.expCateHotelUnit = expCateHotelUnit;
	}
	public String getHotelStatus() {
		return hotelStatus;
	}
	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getHidGradeStatus() {
		return hidGradeStatus;
	}
	public void setHidGradeStatus(String hidGradeStatus) {
		this.hidGradeStatus = hidGradeStatus;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getHidtrModeStatus() {
		return hidtrModeStatus;
	}
	public void setHidtrModeStatus(String hidtrModeStatus) {
		this.hidtrModeStatus = hidtrModeStatus;
	}
	public String getHidHotelStatus() {
		return hidHotelStatus;
	}
	public void setHidHotelStatus(String hidHotelStatus) {
		this.hidHotelStatus = hidHotelStatus;
	}
	public String getHidFlag() {
		return hidFlag;
	}
	public void setHidFlag(String hidFlag) {
		this.hidFlag = hidFlag;
	}
	public String getTotExpAmt() {
		return totExpAmt;
	}
	public void setTotExpAmt(String totExpAmt) {
		this.totExpAmt = totExpAmt;
	}
	public String getExpTravelFlag() {
		return expTravelFlag;
	}
	public void setExpTravelFlag(String expTravelFlag) {
		this.expTravelFlag = expTravelFlag;
	}
	public String getActualAtt() {
		return actualAtt;
	}
	public void setActualAtt(String actualAtt) {
		this.actualAtt = actualAtt;
	}
	public String getHidActualAtt() {
		return hidActualAtt;
	}
	public void setHidActualAtt(String hidActualAtt) {
		this.hidActualAtt = hidActualAtt;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getReadOnlyFlag() {
		return readOnlyFlag;
	}
	public void setReadOnlyFlag(String readOnlyFlag) {
		this.readOnlyFlag = readOnlyFlag;
	}
	public String getEditStatus() {
		return editStatus;
	}
	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}
	public String getPolicyAdvanceAllowed() {
		return policyAdvanceAllowed;
	}
	public void setPolicyAdvanceAllowed(String policyAdvanceAllowed) {
		this.policyAdvanceAllowed = policyAdvanceAllowed;
	}
 
	 

}
