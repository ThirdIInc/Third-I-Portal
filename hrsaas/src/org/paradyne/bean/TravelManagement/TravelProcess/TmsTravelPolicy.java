/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0563
 * 
 */
public class TmsTravelPolicy extends BeanBase {
	private TreeMap<String, String> tarvelCurrencyList;
	private String travelCurrency = "";
	// field names for policy
	private String myPage;
	private String show;
	private String policyId = "";
	private String policyName = "";
	private String desc = "";
	private String status = "";
	private String effectDate = "";
	private String policyAbbr = "";
	private String checkActive = "";
	
	private String policyAdvanceAllowed ="";
	// for check box
	private String hidGradeStatus;
	private String gradeStatus = "false";
	private String onloadstatus = "";

	// field names for grade
	private String empGradeId = "";
	private String empGrade = "";
	// for iterator field
	private String itPolicyName = "";
	private String itPolicyAbbr = "";
	private String itEffectDate = "";
	private String itStatus = "";
	private String itPolicyId = "";

	// field names for expense details
	private String exCategory = "";
	private String exCategoryId = "";
	private String expCatUnit = "";
	private String expCatUnitCode = "";
	private String proofIdtrvl;
	private String proofIdlodge;
	private String proofId;
	
	
	//Added by manish sakpal
	private String expValuewithBill="";
	private String expValuewithoutBill="";
	private String cityId="";
	
	
	private String totExpAmtWithBill = "0";
	private String totExpAmtWithoutBill = "0";
	
	
	private String actualAtt = "";
	private String hidActualAtt;
	// flag usedfor cancel button by setting in hidden field

	private String cancelFlag;
	// for travel mode entitle
	private String expCateTravelId = "";
	private String expCateTravel = "";
	private String expCateTravelUnit = "";
	// for grade page to next page keeping the fields as disable
	private String nextflag = "true";

	// for Lodging Entitle
	private String expCateHotelId = "";
	private String expCateHotelUnit = "";
	private String expCateHotel = "";
	// for policy guidelines
	private String settleDays = "";
	private String textAreaLength = "";
	private String guidLines = "";
	// for iterator in expense used arraylist
	private ArrayList expList = null;

	// flags
	private String editFlag = "false";
	private String activeflag = "false";
	private String activeflag1 = "false";
	// for checkbox
	private String checkboxflag = "true";
	private String multisaveflag;// for identifying differnet page's save
									// button using this flag
	private String lodgemode;
	private String modeEntitle;
	// util components
	private ArrayList policyList = null;// used to set policy list in iterator
	private ArrayList gradeList = null;// ..for coming grade list in grade page
	
	TreeMap tmap =null ;
	private String maxDaysTravelClaim = "";
	
	private String uploadFileName="";
	
	private String dataPath="";
	
	
	private boolean dataCheckFlag=false;
	
	
	private String payModeForAdvanceClaim ="";
	
	private String payModeForAdvanceClaimCheque ="";
	private String payModeForAdvanceClaimCash = "";
	private String payModeForAdvanceClaimTransfer ="";
	private String payModeForAdvanceClaimInSalary = "";
	
	private String minNoOfAdvanceDaysToApplyForTravel ="";
	
	private String travelType = "";
	private String travelTypeSelf = "";
	private String travelTypeTeam = "";
	private String travelTypeGuest = "";

	
	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getTravelTypeSelf() {
		return travelTypeSelf;
	}

	public void setTravelTypeSelf(String travelTypeSelf) {
		this.travelTypeSelf = travelTypeSelf;
	}

	public String getTravelTypeTeam() {
		return travelTypeTeam;
	}

	public void setTravelTypeTeam(String travelTypeTeam) {
		this.travelTypeTeam = travelTypeTeam;
	}

	

	public String getTravelTypeGuest() {
		return travelTypeGuest;
	}

	public void setTravelTypeGuest(String travelTypeGuest) {
		this.travelTypeGuest = travelTypeGuest;
	}

	public String getMinNoOfAdvanceDaysToApplyForTravel() {
		return minNoOfAdvanceDaysToApplyForTravel;
	}

	public void setMinNoOfAdvanceDaysToApplyForTravel(
			String minNoOfAdvanceDaysToApplyForTravel) {
		this.minNoOfAdvanceDaysToApplyForTravel = minNoOfAdvanceDaysToApplyForTravel;
	}

	public String getPayModeForAdvanceClaimInSalary() {
		return payModeForAdvanceClaimInSalary;
	}

	public void setPayModeForAdvanceClaimInSalary(
			String payModeForAdvanceClaimInSalary) {
		this.payModeForAdvanceClaimInSalary = payModeForAdvanceClaimInSalary;
	}

	public String getPayModeForAdvanceClaim() {
		return payModeForAdvanceClaim;
	}

	public void setPayModeForAdvanceClaim(String payModeForAdvanceClaim) {
		this.payModeForAdvanceClaim = payModeForAdvanceClaim;
	}

	public String getPayModeForAdvanceClaimCheque() {
		return payModeForAdvanceClaimCheque;
	}

	public void setPayModeForAdvanceClaimCheque(String payModeForAdvanceClaimCheque) {
		this.payModeForAdvanceClaimCheque = payModeForAdvanceClaimCheque;
	}

	public String getPayModeForAdvanceClaimCash() {
		return payModeForAdvanceClaimCash;
	}

	public void setPayModeForAdvanceClaimCash(String payModeForAdvanceClaimCash) {
		this.payModeForAdvanceClaimCash = payModeForAdvanceClaimCash;
	}

	public String getPayModeForAdvanceClaimTransfer() {
		return payModeForAdvanceClaimTransfer;
	}

	public void setPayModeForAdvanceClaimTransfer(
			String payModeForAdvanceClaimTransfer) {
		this.payModeForAdvanceClaimTransfer = payModeForAdvanceClaimTransfer;
	}

	public boolean isDataCheckFlag() {
		return dataCheckFlag;
	}

	public void setDataCheckFlag(boolean dataCheckFlag) {
		this.dataCheckFlag = dataCheckFlag;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	/**
	 * @return the policyId
	 */
	public String getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId
	 *            the policyId to set
	 */
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	/**
	 * @return the policyName
	 */
	public String getPolicyName() {
		return policyName;
	}

	/**
	 * @param policyName
	 *            the policyName to set
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the effectDate
	 */
	public String getEffectDate() {
		return effectDate;
	}

	/**
	 * @param effectDate
	 *            the effectDate to set
	 */
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * @return the policyAbbr
	 */
	public String getPolicyAbbr() {
		return policyAbbr;
	}

	/**
	 * @param policyAbbr
	 *            the policyAbbr to set
	 */
	public void setPolicyAbbr(String policyAbbr) {
		this.policyAbbr = policyAbbr;
	}

	/**
	 * @return the policyList
	 */
	public ArrayList getPolicyList() {
		return policyList;
	}

	/**
	 * @param policyList
	 *            the policyList to set
	 */
	public void setPolicyList(ArrayList policyList) {
		this.policyList = policyList;
	}

	/**
	 * @return the itPolicyName
	 */
	public String getItPolicyName() {
		return itPolicyName;
	}

	/**
	 * @param itPolicyName
	 *            the itPolicyName to set
	 */
	public void setItPolicyName(String itPolicyName) {
		this.itPolicyName = itPolicyName;
	}

	/**
	 * @return the itPolicyAbbr
	 */
	public String getItPolicyAbbr() {
		return itPolicyAbbr;
	}

	/**
	 * @param itPolicyAbbr
	 *            the itPolicyAbbr to set
	 */
	public void setItPolicyAbbr(String itPolicyAbbr) {
		this.itPolicyAbbr = itPolicyAbbr;
	}

	/**
	 * @return the itEffectDate
	 */
	public String getItEffectDate() {
		return itEffectDate;
	}

	/**
	 * @param itEffectDate
	 *            the itEffectDate to set
	 */
	public void setItEffectDate(String itEffectDate) {
		this.itEffectDate = itEffectDate;
	}

	/**
	 * @return the itStatus
	 */
	public String getItStatus() {
		return itStatus;
	}

	/**
	 * @param itStatus
	 *            the itStatus to set
	 */
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}

	/**
	 * @return the itPolicyId
	 */
	public String getItPolicyId() {
		return itPolicyId;
	}

	/**
	 * @param itPolicyId
	 *            the itPolicyId to set
	 */
	public void setItPolicyId(String itPolicyId) {
		this.itPolicyId = itPolicyId;
	}

	/**
	 * @return the editFlag
	 */
	public String getEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 *            the editFlag to set
	 */
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return the gradeList
	 */
	public ArrayList getGradeList() {
		return gradeList;
	}

	/**
	 * @param gradeList
	 *            the gradeList to set
	 */
	public void setGradeList(ArrayList gradeList) {
		this.gradeList = gradeList;
	}

	/**
	 * @return the empGradeId
	 */
	public String getEmpGradeId() {
		return empGradeId;
	}

	/**
	 * @param empGradeId
	 *            the empGradeId to set
	 */
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}

	/**
	 * @return the empGrade
	 */
	public String getEmpGrade() {
		return empGrade;
	}

	/**
	 * @param empGrade
	 *            the empGrade to set
	 */
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	/**
	 * @return the multisaveflag
	 */
	public String getMultisaveflag() {
		return multisaveflag;
	}

	/**
	 * @param multisaveflag
	 *            the multisaveflag to set
	 */
	public void setMultisaveflag(String multisaveflag) {
		this.multisaveflag = multisaveflag;
	}

	/**
	 * @return the hidGradeStatus
	 */
	public String getHidGradeStatus() {
		return hidGradeStatus;
	}

	/**
	 * @param hidGradeStatus
	 *            the hidGradeStatus to set
	 */
	public void setHidGradeStatus(String hidGradeStatus) {
		this.hidGradeStatus = hidGradeStatus;
	}

	/**
	 * @return the gradeStatus
	 */
	public String getGradeStatus() {
		return gradeStatus;
	}

	/**
	 * @param gradeStatus
	 *            the gradeStatus to set
	 */
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}

	/**
	 * @return the exCategory
	 */
	public String getExCategory() {
		return exCategory;
	}

	/**
	 * @param exCategory
	 *            the exCategory to set
	 */
	public void setExCategory(String exCategory) {
		this.exCategory = exCategory;
	}

	/**
	 * @return the exCategoryId
	 */
	public String getExCategoryId() {
		return exCategoryId;
	}

	/**
	 * @param exCategoryId
	 *            the exCategoryId to set
	 */
	public void setExCategoryId(String exCategoryId) {
		this.exCategoryId = exCategoryId;
	}

	/**
	 * @return the expCatUnit
	 */
	public String getExpCatUnit() {
		return expCatUnit;
	}

	/**
	 * @param expCatUnit
	 *            the expCatUnit to set
	 */
	public void setExpCatUnit(String expCatUnit) {
		this.expCatUnit = expCatUnit;
	}

	/**
	 * @return the expCatUnitCode
	 */
	public String getExpCatUnitCode() {
		return expCatUnitCode;
	}

	/**
	 * @param expCatUnitCode
	 *            the expCatUnitCode to set
	 */
	public void setExpCatUnitCode(String expCatUnitCode) {
		this.expCatUnitCode = expCatUnitCode;
	}

	/**
	 * @return the expList
	 */
	public ArrayList getExpList() {
		return expList;
	}

	/**
	 * @param expList
	 *            the expList to set
	 */
	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}

	

	
	/**
	 * @return the actualAtt
	 */
	public String getActualAtt() {
		return actualAtt;
	}

	/**
	 * @param actualAtt
	 *            the actualAtt to set
	 */
	public void setActualAtt(String actualAtt) {
		this.actualAtt = actualAtt;
	}

	/**
	 * @return the hidActualAtt
	 */
	public String getHidActualAtt() {
		return hidActualAtt;
	}

	/**
	 * @param hidActualAtt
	 *            the hidActualAtt to set
	 */
	public void setHidActualAtt(String hidActualAtt) {
		this.hidActualAtt = hidActualAtt;
	}

	/**
	 * @return the expCateTravelId
	 */
	public String getExpCateTravelId() {
		return expCateTravelId;
	}

	/**
	 * @param expCateTravelId
	 *            the expCateTravelId to set
	 */
	public void setExpCateTravelId(String expCateTravelId) {
		this.expCateTravelId = expCateTravelId;
	}

	/**
	 * @return the expCateTravel
	 */
	public String getExpCateTravel() {
		return expCateTravel;
	}

	/**
	 * @param expCateTravel
	 *            the expCateTravel to set
	 */
	public void setExpCateTravel(String expCateTravel) {
		this.expCateTravel = expCateTravel;
	}

	/**
	 * @return the expCateTravelUnit
	 */
	public String getExpCateTravelUnit() {
		return expCateTravelUnit;
	}

	/**
	 * @param expCateTravelUnit
	 *            the expCateTravelUnit to set
	 */
	public void setExpCateTravelUnit(String expCateTravelUnit) {
		this.expCateTravelUnit = expCateTravelUnit;
	}

	/**
	 * @return the expCateHotelId
	 */
	public String getExpCateHotelId() {
		return expCateHotelId;
	}

	/**
	 * @param expCateHotelId
	 *            the expCateHotelId to set
	 */
	public void setExpCateHotelId(String expCateHotelId) {
		this.expCateHotelId = expCateHotelId;
	}

	/**
	 * @return the expCateHotelUnit
	 */
	public String getExpCateHotelUnit() {
		return expCateHotelUnit;
	}

	/**
	 * @param expCateHotelUnit
	 *            the expCateHotelUnit to set
	 */
	public void setExpCateHotelUnit(String expCateHotelUnit) {
		this.expCateHotelUnit = expCateHotelUnit;
	}

	/**
	 * @return the expCateHotel
	 */
	public String getExpCateHotel() {
		return expCateHotel;
	}

	/**
	 * @param expCateHotel
	 *            the expCateHotel to set
	 */
	public void setExpCateHotel(String expCateHotel) {
		this.expCateHotel = expCateHotel;
	}

	/**
	 * @return the settleDays
	 */
	public String getSettleDays() {
		return settleDays;
	}

	/**
	 * @param settleDays
	 *            the settleDays to set
	 */
	public void setSettleDays(String settleDays) {
		this.settleDays = settleDays;
	}

	/**
	 * @return the textAreaLength
	 */
	public String getTextAreaLength() {
		return textAreaLength;
	}

	/**
	 * @param textAreaLength
	 *            the textAreaLength to set
	 */
	public void setTextAreaLength(String textAreaLength) {
		this.textAreaLength = textAreaLength;
	}

	/**
	 * @return the guidLines
	 */
	public String getGuidLines() {
		return guidLines;
	}

	/**
	 * @param guidLines
	 *            the guidLines to set
	 */
	public void setGuidLines(String guidLines) {
		this.guidLines = guidLines;
	}

	/**
	 * @return the checkboxflag
	 */
	public String getCheckboxflag() {
		return checkboxflag;
	}

	/**
	 * @param checkboxflag
	 *            the checkboxflag to set
	 */
	public void setCheckboxflag(String checkboxflag) {
		this.checkboxflag = checkboxflag;
	}

	/**
	 * @return the lodgemode
	 */
	public String getLodgemode() {
		return lodgemode;
	}

	/**
	 * @param lodgemode
	 *            the lodgemode to set
	 */
	public void setLodgemode(String lodgemode) {
		this.lodgemode = lodgemode;
	}

	/**
	 * @return the modeEntitle
	 */
	public String getModeEntitle() {
		return modeEntitle;
	}

	/**
	 * @param modeEntitle
	 *            the modeEntitle to set
	 */
	public void setModeEntitle(String modeEntitle) {
		this.modeEntitle = modeEntitle;
	}

	/**
	 * @return the cancelFlag
	 */
	public String getCancelFlag() {
		return cancelFlag;
	}

	/**
	 * @param cancelFlag
	 *            the cancelFlag to set
	 */
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	/**
	 * @return the activeflag
	 */
	/**
	 * @return the activeflag
	 */
	public String getActiveflag() {
		return activeflag;
	}

	/**
	 * @param activeflag
	 *            the activeflag to set
	 */
	public void setActiveflag(String activeflag) {
		this.activeflag = activeflag;
	}

	/**
	 * @return the activeflag1
	 */
	public String getActiveflag1() {
		return activeflag1;
	}

	/**
	 * @param activeflag1
	 *            the activeflag1 to set
	 */
	public void setActiveflag1(String activeflag1) {
		this.activeflag1 = activeflag1;
	}

	/**
	 * @return the nextflag
	 */
	/**
	 * @return the nextflag
	 */
	public String getNextflag() {
		return nextflag;
	}

	/**
	 * @param nextflag
	 *            the nextflag to set
	 */
	public void setNextflag(String nextflag) {
		this.nextflag = nextflag;
	}

	/**
	 * @return the checkActive
	 */
	public String getCheckActive() {
		return checkActive;
	}

	/**
	 * @param checkActive
	 *            the checkActive to set
	 */
	public void setCheckActive(String checkActive) {
		this.checkActive = checkActive;
	}

	public String getOnloadstatus() {
		return onloadstatus;
	}

	public void setOnloadstatus(String onloadstatus) {
		this.onloadstatus = onloadstatus;
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

	public String getProofIdtrvl() {
		return proofIdtrvl;
	}

	public void setProofIdtrvl(String proofIdtrvl) {
		this.proofIdtrvl = proofIdtrvl;
	}

	public String getProofIdlodge() {
		return proofIdlodge;
	}

	public void setProofIdlodge(String proofIdlodge) {
		this.proofIdlodge = proofIdlodge;
	}

	public String getProofId() {
		return proofId;
	}

	public void setProofId(String proofId) {
		this.proofId = proofId;
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getPolicyAdvanceAllowed() {
		return policyAdvanceAllowed;
	}

	public void setPolicyAdvanceAllowed(String policyAdvanceAllowed) {
		this.policyAdvanceAllowed = policyAdvanceAllowed;
	}

	public String getTotExpAmtWithBill() {
		return totExpAmtWithBill;
	}

	public void setTotExpAmtWithBill(String totExpAmtWithBill) {
		this.totExpAmtWithBill = totExpAmtWithBill;
	}

	public String getTotExpAmtWithoutBill() {
		return totExpAmtWithoutBill;
	}

	public void setTotExpAmtWithoutBill(String totExpAmtWithoutBill) {
		this.totExpAmtWithoutBill = totExpAmtWithoutBill;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getMaxDaysTravelClaim() {
		return maxDaysTravelClaim;
	}

	public void setMaxDaysTravelClaim(String maxDaysTravelClaim) {
		this.maxDaysTravelClaim = maxDaysTravelClaim;
	}

	public TreeMap<String, String> getTarvelCurrencyList() {
		return tarvelCurrencyList;
	}

	public void setTarvelCurrencyList(TreeMap<String, String> tarvelCurrencyList) {
		this.tarvelCurrencyList = tarvelCurrencyList;
	}

	public String getTravelCurrency() {
		return travelCurrency;
	}

	public void setTravelCurrency(String travelCurrency) {
		this.travelCurrency = travelCurrency;
	}

}
