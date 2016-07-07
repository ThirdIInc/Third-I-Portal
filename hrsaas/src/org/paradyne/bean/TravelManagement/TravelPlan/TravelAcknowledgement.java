package org.paradyne.bean.TravelManagement.TravelPlan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

	/**
	 * Dilip
	 */
	
	public class TravelAcknowledgement  extends BeanBase {
		
		// status for navigation
		
	
		 private String buttonFlag="";
		 private String lodgeFlag="";
		 private String convenceFlag="";
		 private String travelFlag="";
		 private String stat="";
		 private String  chkFlag="";
		 private String addAmount="";
		 private String payMode="";
		 private String schComment="";
		 private String applComment="";
		 ArrayList lodgeList;
		 ArrayList conveyList;
		 private String flag="true";
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
		 private String comment="";
		 ArrayList travelDtlList;
		 private String cansApp="";
		 private String cansRej="";
		 private String uploadTravelTicket="";
		 private String lodgeticketDeatails="";
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
			private String grade= "";
			private String aDate = "";
			private String empToken; 
			
			
			// for paging 
			 private String hdPage="";
			 private String fromTotRec="";
			 private String toTotRec="";
			 
		// Tarvel Schedule Iterator Values	 
			 
			 private String itSource="";
			 private String itJournyDate="";
			 private String	itTime="";
			 private String	itMode="";
			 private String itBTFNumber="";
			 private String itTicketNumber="";
			 
			 private String	Details="";
			 private String	city ="";
			 private String	fromDate="";
			 private String	checkIn ="";
			 private String	toDate="";
			 private String checkOut="";
			 private String	hotelType ="";
			 private String	roomType="";
			 private String	address="";
			 private String	ticketDeatails="";
			 
			 private String sourcedt="";
			 private String date="";
			 private String travelMode="";
			 private String modeNumber="";
			 private String cntPerson="";
			 private String cntNumber="";
			 private String pickPerson="";
			 private String pickPlace="";
			 private String pickTime="";
			 
			 private String apprflag="";
			 
			
			 
			 private String testFlag="false";
			 
			 private String backFlag="";
			public String getBackFlag() {
				return backFlag;
			}
			public void setBackFlag(String backFlag) {
				this.backFlag = backFlag;
			}
			public String getCansApp() {
				return cansApp;
			}
			public void setCansApp(String cansApp) {
				this.cansApp = cansApp;
			}
			public String getCansRej() {
				return cansRej;
			}
			public void setCansRej(String cansRej) {
				this.cansRej = cansRej;
			}
			public String getApprflag() {
				return apprflag;
			}
			public void setApprflag(String apprflag) {
				this.apprflag = apprflag;
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
			public String getRemark() {
				return remark;
			}
			public void setRemark(String remark) {
				this.remark = remark;
			}
			public String getTravelViewNo() {
				return travelViewNo;
			}
			public void setTravelViewNo(String travelViewNo) {
				this.travelViewNo = travelViewNo;
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
			public String getAppComment() {
				return appComment;
			}
			public void setAppComment(String appComment) {
				this.appComment = appComment;
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
			public String getTrRequestName() {
				return trRequestName;
			}
			public void setTrRequestName(String trRequestName) {
				this.trRequestName = trRequestName;
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
			public ArrayList getTravelList() {
				return travelList;
			}
			public void setTravelList(ArrayList travelList) {
				this.travelList = travelList;
			}
			public ArrayList getApproveList() {
				return approveList;
			}
			public void setApproveList(ArrayList approveList) {
				this.approveList = approveList;
			}
			public ArrayList getApplicantModeList() {
				return applicantModeList;
			}
			public void setApplicantModeList(ArrayList applicantModeList) {
				this.applicantModeList = applicantModeList;
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
			
			public String getADate() {
				return aDate;
			}
			public void setADate(String date) {
				aDate = date;
			}
			public String getEmpToken() {
				return empToken;
			}
			public void setEmpToken(String empToken) {
				this.empToken = empToken;
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
			public String getComment() {
				return comment;
			}
			public void setComment(String comment) {
				this.comment = comment;
			}
			public String getGrade() {
				return grade;
			}
			public void setGrade(String grade) {
				this.grade = grade;
			}
			public String getItSource() {
				return itSource;
			}
			public void setItSource(String itSource) {
				this.itSource = itSource;
			}
			public String getItJournyDate() {
				return itJournyDate;
			}
			public void setItJournyDate(String itJournyDate) {
				this.itJournyDate = itJournyDate;
			}
			public String getItTime() {
				return itTime;
			}
			public void setItTime(String itTime) {
				this.itTime = itTime;
			}
			public String getItMode() {
				return itMode;
			}
			public void setItMode(String itMode) {
				this.itMode = itMode;
			}
			public String getItBTFNumber() {
				return itBTFNumber;
			}
			public void setItBTFNumber(String itBTFNumber) {
				this.itBTFNumber = itBTFNumber;
			}
			
			public String getDetails() {
				return Details;
			}
			public void setDetails(String details) {
				Details = details;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getFromDate() {
				return fromDate;
			}
			public void setFromDate(String fromDate) {
				this.fromDate = fromDate;
			}
			public String getCheckIn() {
				return checkIn;
			}
			public void setCheckIn(String checkIn) {
				this.checkIn = checkIn;
			}
			public String getToDate() {
				return toDate;
			}
			public void setToDate(String toDate) {
				this.toDate = toDate;
			}
			public String getCheckOut() {
				return checkOut;
			}
			public void setCheckOut(String checkOut) {
				this.checkOut = checkOut;
			}
			public String getHotelType() {
				return hotelType;
			}
			public void setHotelType(String hotelType) {
				this.hotelType = hotelType;
			}
			public String getRoomType() {
				return roomType;
			}
			public void setRoomType(String roomType) {
				this.roomType = roomType;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getTicketDeatails() {
				return ticketDeatails;
			}
			public void setTicketDeatails(String ticketDeatails) {
				this.ticketDeatails = ticketDeatails;
			}
			public String getSourcedt() {
				return sourcedt;
			}
			public void setSourcedt(String sourcedt) {
				this.sourcedt = sourcedt;
			}
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public String getTravelMode() {
				return travelMode;
			}
			public void setTravelMode(String travelMode) {
				this.travelMode = travelMode;
			}
			public String getModeNumber() {
				return modeNumber;
			}
			public void setModeNumber(String modeNumber) {
				this.modeNumber = modeNumber;
			}
			public String getCntPerson() {
				return cntPerson;
			}
			public void setCntPerson(String cntPerson) {
				this.cntPerson = cntPerson;
			}
			public String getCntNumber() {
				return cntNumber;
			}
			public void setCntNumber(String cntNumber) {
				this.cntNumber = cntNumber;
			}
			public String getPickPerson() {
				return pickPerson;
			}
			public void setPickPerson(String pickPerson) {
				this.pickPerson = pickPerson;
			}
			public String getPickTime() {
				return pickTime;
			}
			public void setPickTime(String pickTime) {
				this.pickTime = pickTime;
			}
			public String getFlag() {
				return flag;
			}
			public void setFlag(String flag) {
				this.flag = flag;
			}
			public String getItTicketNumber() {
				return itTicketNumber;
			}
			public void setItTicketNumber(String itTicketNumber) {
				this.itTicketNumber = itTicketNumber;
			}
			public ArrayList getConveyList() {
				return conveyList;
			}
			public void setConveyList(ArrayList conveyList) {
				this.conveyList = conveyList;
			}
			public ArrayList getLodgeList() {
				return lodgeList;
			}
			public void setLodgeList(ArrayList lodgeList) {
				this.lodgeList = lodgeList;
			}
			public String getAddAmount() {
				return addAmount;
			}
			public void setAddAmount(String addAmount) {
				this.addAmount = addAmount;
			}
			public String getPayMode() {
				return payMode;
			}
			public void setPayMode(String payMode) {
				this.payMode = payMode;
			}
			public String getSchComment() {
				return schComment;
			}
			public void setSchComment(String schComment) {
				this.schComment = schComment;
			}
			public String getApplComment() {
				return applComment;
			}
			public void setApplComment(String applComment) {
				this.applComment = applComment;
			}
			public String getStat() {
				return stat;
			}
			public void setStat(String stat) {
				this.stat = stat;
			}
			public String getChkFlag() {
				return chkFlag;
			}
			public void setChkFlag(String chkFlag) {
				this.chkFlag = chkFlag;
			}
			public String getTestFlag() {
				return testFlag;
			}
			public void setTestFlag(String testFlag) {
				this.testFlag = testFlag;
			}
			public String getUploadTravelTicket() {
				return uploadTravelTicket;
			}
			public void setUploadTravelTicket(String uploadTravelTicket) {
				this.uploadTravelTicket = uploadTravelTicket;
			}
			public String getLodgeticketDeatails() {
				return lodgeticketDeatails;
			}
			public void setLodgeticketDeatails(String lodgeticketDeatails) {
				this.lodgeticketDeatails = lodgeticketDeatails;
			}
			public String getLodgeFlag() {
				return lodgeFlag;
			}
			public void setLodgeFlag(String lodgeFlag) {
				this.lodgeFlag = lodgeFlag;
			}
			public String getConvenceFlag() {
				return convenceFlag;
			}
			public void setConvenceFlag(String convenceFlag) {
				this.convenceFlag = convenceFlag;
			}
			public String getTravelFlag() {
				return travelFlag;
			}
			public void setTravelFlag(String travelFlag) {
				this.travelFlag = travelFlag;
			}
			
			public String getButtonFlag() {
				return buttonFlag;
			}
			public void setButtonFlag(String buttonFlag) {
				this.buttonFlag = buttonFlag;
			}
			public String getPickPlace() {
				return pickPlace;
			}
			public void setPickPlace(String pickPlace) {
				this.pickPlace = pickPlace;
			}
			 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

