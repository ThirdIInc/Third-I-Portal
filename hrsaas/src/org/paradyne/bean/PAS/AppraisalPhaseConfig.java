package org.paradyne.bean.PAS;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase; 

/**
 * @author Hemant Nagam
 *  Date: 09-JAN-2009
 */
public class AppraisalPhaseConfig extends BeanBase{
	
	String apprId;
	String hApprId;
	String apprCode;
	String apprName;
	String frmDate;
	String toDate;
	String phaseId;
	
	//MAIN PHASE VARS
	String phase;	
	String pOrder;
	String pRatingCheck;
	String pRating;	
	String pWeightage;
	String pStatus="A";
	String pDescr;
	String pQueWeight;
	///PHASE LIST VARS
	String hPhase;	
	String hOrder;
	String hRating;
	String hWeightage;
	String hStatus;
	String hDescr;
	
	String hQueWeight;
	
	String totalWeightage;	
	String sNo;
	String removePhases;
	
	String viewMode="false";
	String editMode="false";
	String mode;
	String removeFlag="false";
	String phaseRating;
	
	String calId;
	String calCode;
	String startdate;
	String endDate;
	
	String myPage;
	String show;
	String appStarted;
	String newFlag="false";
	
	ArrayList phaseList;//Phase list 
	ArrayList calList;//Calendara list
	
	
	public String getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	public String getAppStarted() {
		return appStarted;
	}

	public void setAppStarted(String appStarted) {
		this.appStarted = appStarted;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getPhaseRating() {
		return phaseRating;
	}

	public void setPhaseRating(String phaseRating) {
		this.phaseRating = phaseRating;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getCalId() {
		return calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	public String getCalCode() {
		return calCode;
	}

	public void setCalCode(String calCode) {
		this.calCode = calCode;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRemovePhases() {
		return removePhases;
	}

	public void setRemovePhases(String removePhases) {
		this.removePhases = removePhases;
	}

	public String getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(String removeFlag) {
		this.removeFlag = removeFlag;
	}

	public String getHApprId() {
		return hApprId;
	}

	public void setHApprId(String apprId) {
		hApprId = apprId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPRatingCheck() {
		return pRatingCheck;
	}

	public void setPRatingCheck(String ratingCheck) {
		pRatingCheck = ratingCheck;
	}

	public String getSNo() {
		return sNo;
	}

	public void setSNo(String no) {
		sNo = no;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	public String getEditMode() {
		return editMode;
	}

	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	public String getApprCode() {
		return apprCode;
	}

	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}

	public String getApprName() {
		return apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPOrder() {
		return pOrder;
	}

	public void setPOrder(String order) {
		pOrder = order;
	}

	public String getPRating() {
		return pRating;
	}

	public void setPRating(String rating) {
		pRating = rating;
	}

	public String getPWeightage() {
		return pWeightage;
	}

	public void setPWeightage(String weightage) {
		pWeightage = weightage;
	}

	public String getPStatus() {
		return pStatus;
	}

	public void setPStatus(String status) {
		System.out.println("STATUS--->"+status);
		pStatus = status;
	}

	public String getPDescr() {
		return pDescr;
	}

	public void setPDescr(String descr) {
		pDescr = descr;
	}
	
	public String getTotalWeightage() {
		return totalWeightage;
	}

	public void setTotalWeightage(String totalWeightage) {
		this.totalWeightage = totalWeightage;
	}

	public ArrayList getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}
	
	public ArrayList getCalList() {
		return calList;
	}

	public void setCalList(ArrayList calList) {
		this.calList = calList;
	}

	public String getHPhase() {
		return hPhase;
	}

	public void setHPhase(String phase) {
		hPhase = phase;
	}

	public String getHOrder() {
		return hOrder;
	}

	public void setHOrder(String order) {
		hOrder = order;
	}

	public String getHRating() {
		return hRating;
	}

	public void setHRating(String rating) {
		hRating = rating;
	}

	public String getHWeightage() {
		return hWeightage;
	}

	public void setHWeightage(String weightage) {
		hWeightage = weightage;
	}

	public String getHStatus() {
		return hStatus;
	}

	public void setHStatus(String status) {
		hStatus = status;
	}

	public String getHDescr() {
		return hDescr;
	}

	public void setHDescr(String descr) {
		hDescr = descr;
	}

	public String getPQueWeight() {
		return pQueWeight;
	}

	public void setPQueWeight(String queWeight) {
		pQueWeight = queWeight;
	}

	public String getHQueWeight() {
		return hQueWeight;
	}

	public void setHQueWeight(String queWeight) {
		hQueWeight = queWeight;
	}

	
}
