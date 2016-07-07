/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelPlan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author balajim
 *
 */
public class TravelApprover extends BeanBase {

	 private String travelEmpId="";
	 private String empName ="";
	 private String appDate ="";
	 private String travelEmpToken="";
	 private String travelAppId="";
	 private String level="";
	 private String checkStatus ="";
	 private String statusNew ="";
	 private String remark ="";
	 private String travelViewNo="";
	 private String apprflag="";
	 private String listLength="";
	 private String status="";
	 private String noData ="";
	 private String pen="";
	 private String app="";
	 private String rej ="";
	 private String appComment="";
	 private String gradeJourneyId="";
	 private String	gradeClassId =""; 
	 private String appJourneyId="";
	 private String	appClassId ="";  
	 private String gradeCode="";
	 private String	gradeName =""; 
	 private String modeName="";
	 private String brName=""; 
	 private String empAge="";
	 private String contactNo="";
	 private String  idProof="";
	 private String  idNumber ="";
	 private String  trRequestName ="";
	 private String  trAppCanStatus ="true";
	 
	 
	 // for single Apporve
	 
	 private String trAppId="";
	 private String  levelApp=""; 
	 private String  approverComments="";

 
	 ArrayList travelList = new ArrayList();
	 ArrayList approveList = new ArrayList(); 
	 ArrayList applicantModeList = new ArrayList();  // for display the applicable mode list
		private String appModeName="";
		private String appClassName="";
		private String appSerial="";
		private String appDispFlag="true";
		private String  alertMode ="";
		private String  alertFlag ="false";
		private String alertShowFlag="false"; 
		private String trOtherExp="";  // for display the gradewise cost
		private String lodgOtherAllow="";   
		private String lodgAllowPerDay=""; 
		private String outPocketAllow="";   
		private String foodAllow="";
		private String cssClassTYpe="";
	 
	 // FOR ITERATOR
	    ArrayList travelDtlList = new ArrayList(); 
		private String itFromPlaceName ="";
		private String itToPlaceName="";
		private String itJourneyName="";
		private String itJourneyClassName="";
		private String itFromPlaceId="";
		private String itToPlaceId="";
		private String itJourneyId="";
		private String itJourneyClassId="";
		private String itJourneyDate="";
		private String itJourneyTime="";
		private String serialNo="";
		
  //for Approver List
		private String apprName = "";
		private String apprDate ="";
		private String apprComments ="";
		
 // for Employee Details
		private String empId = "";
		private String eName = "";
		private String branchName = "";
		private String dept = "";
		private String desg= "";
		private String aDate = "";
		private String empToken; 
		
		// for paging 
		 private String hdPage="";
		 private String fromTotRec="";
		 private String toTotRec="";
		 
		 //for alert in list
		 
		 private String alertTrMode="";
		 private String alertTrFlag="";
		 
		 private String alertHotelType="";
		 private String alertHotelFlag="";
		 private String dispSrNo="";
		 
	public String getDispSrNo() {
			return dispSrNo;
		}
		public void setDispSrNo(String dispSrNo) {
			this.dispSrNo = dispSrNo;
		}
	public String getEmpToken() {
			return empToken;
		}
		public void setEmpToken(String empToken) {
			this.empToken = empToken;
		}
	public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEName() {
			return eName;
		}
		public void setEName(String name) {
			eName = name;
		}
		public String getBranchName() {
			return branchName;
		}
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public String getDesg() {
			return desg;
		}
		public void setDesg(String desg) {
			this.desg = desg;
		}
		public String getADate() {
			return aDate;
		}
		public void setADate(String date) {
			aDate = date;
		}
	public ArrayList getTravelList() {
		return travelList;
	}
	public void setTravelList(ArrayList travelList) {
		this.travelList = travelList;
	}
	public String getTravelEmpId() {
		return travelEmpId;
	}
	public void setTravelEmpId(String travelEmpId) {
		this.travelEmpId = travelEmpId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getTravelEmpToken() {
		return travelEmpToken;
	}
	public void setTravelEmpToken(String travelEmpToken) {
		this.travelEmpToken = travelEmpToken;
	}
	public String getTravelAppId() {
		return travelAppId;
	}
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getTravelViewNo() {
		return travelViewNo;
	}
	public void setTravelViewNo(String travelViewNo) {
		this.travelViewNo = travelViewNo;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public ArrayList getTravelDtlList() {
		return travelDtlList;
	}
	public void setTravelDtlList(ArrayList travelDtlList) {
		this.travelDtlList = travelDtlList;
	}
	public String getItFromPlaceName() {
		return itFromPlaceName;
	}
	public void setItFromPlaceName(String itFromPlaceName) {
		this.itFromPlaceName = itFromPlaceName;
	}
	public String getItToPlaceName() {
		return itToPlaceName;
	}
	public void setItToPlaceName(String itToPlaceName) {
		this.itToPlaceName = itToPlaceName;
	}
	public String getItJourneyName() {
		return itJourneyName;
	}
	public void setItJourneyName(String itJourneyName) {
		this.itJourneyName = itJourneyName;
	}
	public String getItJourneyClassName() {
		return itJourneyClassName;
	}
	public void setItJourneyClassName(String itJourneyClassName) {
		this.itJourneyClassName = itJourneyClassName;
	}
	public String getItFromPlaceId() {
		return itFromPlaceId;
	}
	public void setItFromPlaceId(String itFromPlaceId) {
		this.itFromPlaceId = itFromPlaceId;
	}
	public String getItToPlaceId() {
		return itToPlaceId;
	}
	public void setItToPlaceId(String itToPlaceId) {
		this.itToPlaceId = itToPlaceId;
	}
	public String getItJourneyId() {
		return itJourneyId;
	}
	public void setItJourneyId(String itJourneyId) {
		this.itJourneyId = itJourneyId;
	}
	public String getItJourneyClassId() {
		return itJourneyClassId;
	}
	public void setItJourneyClassId(String itJourneyClassId) {
		this.itJourneyClassId = itJourneyClassId;
	}
	public String getItJourneyDate() {
		return itJourneyDate;
	}
	public void setItJourneyDate(String itJourneyDate) {
		this.itJourneyDate = itJourneyDate;
	}
	public String getItJourneyTime() {
		return itJourneyTime;
	}
	public void setItJourneyTime(String itJourneyTime) {
		this.itJourneyTime = itJourneyTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getAppComment() {
		return appComment;
	}
	public void setAppComment(String appComment) {
		this.appComment = appComment;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public String getGradeJourneyId() {
		return gradeJourneyId;
	}
	public void setGradeJourneyId(String gradeJourneyId) {
		this.gradeJourneyId = gradeJourneyId;
	}
	public String getGradeClassId() {
		return gradeClassId;
	}
	public void setGradeClassId(String gradeClassId) {
		this.gradeClassId = gradeClassId;
	}
	public String getAppJourneyId() {
		return appJourneyId;
	}
	public void setAppJourneyId(String appJourneyId) {
		this.appJourneyId = appJourneyId;
	}
	public String getAppClassId() {
		return appClassId;
	}
	public void setAppClassId(String appClassId) {
		this.appClassId = appClassId;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
 
	public String getAppModeName() {
		return appModeName;
	}
	public void setAppModeName(String appModeName) {
		this.appModeName = appModeName;
	}
	public String getAppClassName() {
		return appClassName;
	}
	public void setAppClassName(String appClassName) {
		this.appClassName = appClassName;
	}
	public String getAppSerial() {
		return appSerial;
	}
	public void setAppSerial(String appSerial) {
		this.appSerial = appSerial;
	}
	public String getAppDispFlag() {
		return appDispFlag;
	}
	public void setAppDispFlag(String appDispFlag) {
		this.appDispFlag = appDispFlag;
	}
	public ArrayList getApplicantModeList() {
		return applicantModeList;
	}
	public void setApplicantModeList(ArrayList applicantModeList) {
		this.applicantModeList = applicantModeList;
	}
	public String getAlertMode() {
		return alertMode;
	}
	public void setAlertMode(String alertMode) {
		this.alertMode = alertMode;
	}
	public String getAlertFlag() {
		return alertFlag;
	}
	public void setAlertFlag(String alertFlag) {
		this.alertFlag = alertFlag;
	}
	public String getAlertShowFlag() {
		return alertShowFlag;
	}
	public void setAlertShowFlag(String alertShowFlag) {
		this.alertShowFlag = alertShowFlag;
	}
	public String getTrOtherExp() {
		return trOtherExp;
	}
	public void setTrOtherExp(String trOtherExp) {
		this.trOtherExp = trOtherExp;
	}
	public String getLodgOtherAllow() {
		return lodgOtherAllow;
	}
	public void setLodgOtherAllow(String lodgOtherAllow) {
		this.lodgOtherAllow = lodgOtherAllow;
	}
	public String getLodgAllowPerDay() {
		return lodgAllowPerDay;
	}
	public void setLodgAllowPerDay(String lodgAllowPerDay) {
		this.lodgAllowPerDay = lodgAllowPerDay;
	}
	public String getOutPocketAllow() {
		return outPocketAllow;
	}
	public void setOutPocketAllow(String outPocketAllow) {
		this.outPocketAllow = outPocketAllow;
	}
	public String getFoodAllow() {
		return foodAllow;
	}
	public void setFoodAllow(String foodAllow) {
		this.foodAllow = foodAllow;
	}
	public String getCssClassTYpe() {
		return cssClassTYpe;
	}
	public void setCssClassTYpe(String cssClassTYpe) {
		this.cssClassTYpe = cssClassTYpe;
	}
	public String getTrAppId() {
		return trAppId;
	}
	public void setTrAppId(String trAppId) {
		this.trAppId = trAppId;
	}
	public String getLevelApp() {
		return levelApp;
	}
	public void setLevelApp(String levelApp) {
		this.levelApp = levelApp;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getTrRequestName() {
		return trRequestName;
	}
	public void setTrRequestName(String trRequestName) {
		this.trRequestName = trRequestName;
	}
	public String getAlertTrMode() {
		return alertTrMode;
	}
	public void setAlertTrMode(String alertTrMode) {
		this.alertTrMode = alertTrMode;
	}
	public String getAlertTrFlag() {
		return alertTrFlag;
	}
	public void setAlertTrFlag(String alertTrFlag) {
		this.alertTrFlag = alertTrFlag;
	}
	public String getTrAppCanStatus() {
		return trAppCanStatus;
	}
	public void setTrAppCanStatus(String trAppCanStatus) {
		this.trAppCanStatus = trAppCanStatus;
	}
	public String getAlertHotelType() {
		return alertHotelType;
	}
	public void setAlertHotelType(String alertHotelType) {
		this.alertHotelType = alertHotelType;
	}
	public String getAlertHotelFlag() {
		return alertHotelFlag;
	}
	public void setAlertHotelFlag(String alertHotelFlag) {
		this.alertHotelFlag = alertHotelFlag;
	}
	  
}
