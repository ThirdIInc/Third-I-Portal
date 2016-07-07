package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class HardwareSoftwareRequest extends BeanBase {
	
	boolean approverCommentFlag = false;
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	
	TreeMap map;
	TreeMap specialSoftwareMap;
	private String status = "";
	private String listType = "";
	private String myPage = "";
	private String myPageInProcess = "";
	private String myPageSentBack = "";
	private String myPageApproved = "";
	private String myPageApprovedCancel = "";
	private String myPageCancel = "";
	private String myPageRejected = "";
	private String myPageCancelRejected = "";

	ArrayList draftApplicationIteratorList = null;
	private boolean draftApplicationListLength = false;
	ArrayList inProcessApplicationIteratorList = null;
	private boolean inProcessApplicationListLength = false;
	ArrayList sentBackApplicationIteratorList = null;
	private boolean sentBackApplicationListLength = false;
	ArrayList approvedApplicationIteratorList = null;
	private boolean approvedApplicationListLength = false;
	ArrayList approvedCancellationApplicationIteratorList = null;
	private boolean approvedCancellationApplicationListLength = false;
	ArrayList cancelledApplicationIteratorList = null;
	private boolean cancelledApplicationListLength = false;
	ArrayList rejectedApplicationIteratorList = null;
	private boolean rejectedApplicationListLength = false;
	ArrayList rejectedCancelApplicationIteratorList = null;
	private boolean rejectedCancelApplicationListLength = false;

	private String hwSwHiddenID = "";
	private String trackingNumIterator = "";
	private String requesterNameIterator = "";
	private String applicationDateIterator = "";

	private String requestTrackingNumber = "";
	private String hwSwID = "";
	private String hardWareCheckbox = "";
	private String softWareCheckbox = "";
	private String airCardCheckbox = "";
	private String requesterID = "";
	private String requesterToken = "";
	private String requesterName = "";
	private String requesterPhone = "";
	private String requesterEmail = "";

	//Employee Information
	private String empID = "";
	private String empToken = "";
	private String empName = "";
	private String empDept = "";
	private String empCity = "";
	private String empState = "";
	private String empZip = "";
	private String empAddress = "";
	private String empBussUnit = "";
	private String empEmail = "";
	private String empPhone = "";
	private String empCountry = "";
	private String empAttn = "";

	private String level = "";
	private String userProfile = "";
	private String userProfileRadioOptionValue = "";
	private String otherProfileExplain = "";
	private String bussinessExplain = "";
	private String typeOfHardwareRequest = "";
	private String hardwareItemsRequested = "";
	private String specialHardwareRequestedExplain = "";
	private String softwareItemsRequested = "";
	private String specialSoftwareItemsRequested = "";
	private String descOfSoftwareRequest = "";
	private String manufacturer = "";
	private String currentModel = "";
	private String serial = "";
	private String compName = "";
	private String operatingSystem = "";
	private String doYouHaveOpenTicketRadio = "";
	private String doYouHaveOpenTicketOptionValue = "";
	private String openTicketYES = "";
	private String doYouHaveProjectSrRadio = "";
	private String doYouHaveProjectSrOptionValue = "";
	private String projectSrYES = "";
	private String zenworkRadio = "";
	private String zenWorkOptionValue = "";
	private String antiVirusRadio = "";
	private String antiVirusOptionValue = "";
	private String selectApproverToken = "";
	private String selectapproverName = "";
	private String selectApproverCode = "";

	private String firstApproverCode = "";
	ArrayList approverList = null;
	private String approverName = "";
	private String approverToken = "";
	private String srNoIterator = "";
	private String hardwareHiddenValues = "";
	private String hwSwHiddenStatus = "";
	private String softwareHiddenValues = "";
	private String specialSoftwareHiddenValues = "";
	private String applicationStatus = "";
	private String completedByID = "";
	private String completedByName = "";
	private String completedByToken = "";
	private String completedOn = "";
	private String uploadFileName = "";
	private String dataPath = "";
	
	private String nextApproverCode="";
	private String nextApproverToken="";
	private String nextApproverName="";
	boolean nextApproverFlag = false;
	boolean ppoFlag = false;
	private String ppoNumber = "";
	private String ppoAttachement = "";
	
	private String hwQuantity="";
	private String swQuantity="";
	
	private String businessReasonIterator="";
	
	/**
	 * @return the businessReasonIterator
	 */
	public String getBusinessReasonIterator() {
		return this.businessReasonIterator;
	}

	/**
	 * @param businessReasonIterator the businessReasonIterator to set
	 */
	public void setBusinessReasonIterator(String businessReasonIterator) {
		this.businessReasonIterator = businessReasonIterator;
	}

	public String getHwQuantity() {
		return hwQuantity;
	}

	public void setHwQuantity(String hwQuantity) {
		this.hwQuantity = hwQuantity;
	}

	public String getSwQuantity() {
		return swQuantity;
	}

	public void setSwQuantity(String swQuantity) {
		this.swQuantity = swQuantity;
	}

	public String getPpoAttachement() {
		return ppoAttachement;
	}

	public void setPpoAttachement(String ppoAttachement) {
		this.ppoAttachement = ppoAttachement;
	}

	public String getNextApproverCode() {
		return nextApproverCode;
	}

	public void setNextApproverCode(String nextApproverCode) {
		this.nextApproverCode = nextApproverCode;
	}

	public String getNextApproverToken() {
		return nextApproverToken;
	}

	public void setNextApproverToken(String nextApproverToken) {
		this.nextApproverToken = nextApproverToken;
	}

	public String getNextApproverName() {
		return nextApproverName;
	}

	public void setNextApproverName(String nextApproverName) {
		this.nextApproverName = nextApproverName;
	}

	public boolean isNextApproverFlag() {
		return nextApproverFlag;
	}

	public void setNextApproverFlag(boolean nextApproverFlag) {
		this.nextApproverFlag = nextApproverFlag;
	}

	public boolean isPpoFlag() {
		return ppoFlag;
	}

	public void setPpoFlag(boolean ppoFlag) {
		this.ppoFlag = ppoFlag;
	}

	public String getPpoNumber() {
		return ppoNumber;
	}

	public void setPpoNumber(String ppoNumber) {
		this.ppoNumber = ppoNumber;
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

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getHardwareHiddenValues() {
		return hardwareHiddenValues;
	}

	public void setHardwareHiddenValues(String hardwareHiddenValues) {
		this.hardwareHiddenValues = hardwareHiddenValues;
	}

	public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getFirstApproverCode() {
		return firstApproverCode;
	}

	public void setFirstApproverCode(String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}


	public String getHwSwID() {
		return hwSwID;
	}

	public void setHwSwID(String hwSwID) {
		this.hwSwID = hwSwID;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	public String getMyPageSentBack() {
		return myPageSentBack;
	}

	public void setMyPageSentBack(String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}

	public String getMyPageApproved() {
		return myPageApproved;
	}

	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	public String getMyPageApprovedCancel() {
		return myPageApprovedCancel;
	}

	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}

	public String getMyPageCancel() {
		return myPageCancel;
	}

	public void setMyPageCancel(String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}

	public String getMyPageRejected() {
		return myPageRejected;
	}

	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	public String getMyPageCancelRejected() {
		return myPageCancelRejected;
	}

	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}

	public ArrayList getDraftApplicationIteratorList() {
		return draftApplicationIteratorList;
	}

	public void setDraftApplicationIteratorList(
			ArrayList draftApplicationIteratorList) {
		this.draftApplicationIteratorList = draftApplicationIteratorList;
	}

	public boolean isDraftApplicationListLength() {
		return draftApplicationListLength;
	}

	public void setDraftApplicationListLength(boolean draftApplicationListLength) {
		this.draftApplicationListLength = draftApplicationListLength;
	}

	public ArrayList getInProcessApplicationIteratorList() {
		return inProcessApplicationIteratorList;
	}

	public void setInProcessApplicationIteratorList(
			ArrayList inProcessApplicationIteratorList) {
		this.inProcessApplicationIteratorList = inProcessApplicationIteratorList;
	}

	public boolean isInProcessApplicationListLength() {
		return inProcessApplicationListLength;
	}

	public void setInProcessApplicationListLength(
			boolean inProcessApplicationListLength) {
		this.inProcessApplicationListLength = inProcessApplicationListLength;
	}

	public ArrayList getSentBackApplicationIteratorList() {
		return sentBackApplicationIteratorList;
	}

	public void setSentBackApplicationIteratorList(
			ArrayList sentBackApplicationIteratorList) {
		this.sentBackApplicationIteratorList = sentBackApplicationIteratorList;
	}

	public boolean isSentBackApplicationListLength() {
		return sentBackApplicationListLength;
	}

	public void setSentBackApplicationListLength(
			boolean sentBackApplicationListLength) {
		this.sentBackApplicationListLength = sentBackApplicationListLength;
	}

	public ArrayList getApprovedApplicationIteratorList() {
		return approvedApplicationIteratorList;
	}

	public void setApprovedApplicationIteratorList(
			ArrayList approvedApplicationIteratorList) {
		this.approvedApplicationIteratorList = approvedApplicationIteratorList;
	}

	public boolean isApprovedApplicationListLength() {
		return approvedApplicationListLength;
	}

	public void setApprovedApplicationListLength(
			boolean approvedApplicationListLength) {
		this.approvedApplicationListLength = approvedApplicationListLength;
	}

	public ArrayList getApprovedCancellationApplicationIteratorList() {
		return approvedCancellationApplicationIteratorList;
	}

	public void setApprovedCancellationApplicationIteratorList(
			ArrayList approvedCancellationApplicationIteratorList) {
		this.approvedCancellationApplicationIteratorList = approvedCancellationApplicationIteratorList;
	}

	public boolean isApprovedCancellationApplicationListLength() {
		return approvedCancellationApplicationListLength;
	}

	public void setApprovedCancellationApplicationListLength(
			boolean approvedCancellationApplicationListLength) {
		this.approvedCancellationApplicationListLength = approvedCancellationApplicationListLength;
	}

	public ArrayList getCancelledApplicationIteratorList() {
		return cancelledApplicationIteratorList;
	}

	public void setCancelledApplicationIteratorList(
			ArrayList cancelledApplicationIteratorList) {
		this.cancelledApplicationIteratorList = cancelledApplicationIteratorList;
	}

	public boolean isCancelledApplicationListLength() {
		return cancelledApplicationListLength;
	}

	public void setCancelledApplicationListLength(
			boolean cancelledApplicationListLength) {
		this.cancelledApplicationListLength = cancelledApplicationListLength;
	}

	public ArrayList getRejectedApplicationIteratorList() {
		return rejectedApplicationIteratorList;
	}

	public void setRejectedApplicationIteratorList(
			ArrayList rejectedApplicationIteratorList) {
		this.rejectedApplicationIteratorList = rejectedApplicationIteratorList;
	}

	public boolean isRejectedApplicationListLength() {
		return rejectedApplicationListLength;
	}

	public void setRejectedApplicationListLength(
			boolean rejectedApplicationListLength) {
		this.rejectedApplicationListLength = rejectedApplicationListLength;
	}

	public ArrayList getRejectedCancelApplicationIteratorList() {
		return rejectedCancelApplicationIteratorList;
	}

	public void setRejectedCancelApplicationIteratorList(
			ArrayList rejectedCancelApplicationIteratorList) {
		this.rejectedCancelApplicationIteratorList = rejectedCancelApplicationIteratorList;
	}

	public boolean isRejectedCancelApplicationListLength() {
		return rejectedCancelApplicationListLength;
	}

	public void setRejectedCancelApplicationListLength(
			boolean rejectedCancelApplicationListLength) {
		this.rejectedCancelApplicationListLength = rejectedCancelApplicationListLength;
	}

	public String getHardWareCheckbox() {
		return hardWareCheckbox;
	}

	public void setHardWareCheckbox(String hardWareCheckbox) {
		this.hardWareCheckbox = hardWareCheckbox;
	}

	public String getSoftWareCheckbox() {
		return softWareCheckbox;
	}

	public void setSoftWareCheckbox(String softWareCheckbox) {
		this.softWareCheckbox = softWareCheckbox;
	}

	public String getAirCardCheckbox() {
		return airCardCheckbox;
	}

	public void setAirCardCheckbox(String airCardCheckbox) {
		this.airCardCheckbox = airCardCheckbox;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getRequesterPhone() {
		return requesterPhone;
	}

	public void setRequesterPhone(String requesterPhone) {
		this.requesterPhone = requesterPhone;
	}

	public String getRequesterEmail() {
		return requesterEmail;
	}

	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public String getEmpZip() {
		return empZip;
	}

	public void setEmpZip(String empZip) {
		this.empZip = empZip;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getSelectapproverName() {
		return selectapproverName;
	}

	public void setSelectapproverName(String selectapproverName) {
		this.selectapproverName = selectapproverName;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getUserProfileRadioOptionValue() {
		return userProfileRadioOptionValue;
	}

	public void setUserProfileRadioOptionValue(
			String userProfileRadioOptionValue) {
		this.userProfileRadioOptionValue = userProfileRadioOptionValue;
	}

	public String getOtherProfileExplain() {
		return otherProfileExplain;
	}

	public void setOtherProfileExplain(String otherProfileExplain) {
		this.otherProfileExplain = otherProfileExplain;
	}

	public String getBussinessExplain() {
		return bussinessExplain;
	}

	public void setBussinessExplain(String bussinessExplain) {
		this.bussinessExplain = bussinessExplain;
	}

	public String getTypeOfHardwareRequest() {
		return typeOfHardwareRequest;
	}

	public void setTypeOfHardwareRequest(String typeOfHardwareRequest) {
		this.typeOfHardwareRequest = typeOfHardwareRequest;
	}

	public String getHardwareItemsRequested() {
		return hardwareItemsRequested;
	}

	public void setHardwareItemsRequested(String hardwareItemsRequested) {
		this.hardwareItemsRequested = hardwareItemsRequested;
	}

	public String getSpecialHardwareRequestedExplain() {
		return specialHardwareRequestedExplain;
	}

	public void setSpecialHardwareRequestedExplain(
			String specialHardwareRequestedExplain) {
		this.specialHardwareRequestedExplain = specialHardwareRequestedExplain;
	}

	public String getSoftwareItemsRequested() {
		return softwareItemsRequested;
	}

	public void setSoftwareItemsRequested(String softwareItemsRequested) {
		this.softwareItemsRequested = softwareItemsRequested;
	}

	public String getSpecialSoftwareItemsRequested() {
		return specialSoftwareItemsRequested;
	}

	public void setSpecialSoftwareItemsRequested(
			String specialSoftwareItemsRequested) {
		this.specialSoftwareItemsRequested = specialSoftwareItemsRequested;
	}

	public String getDescOfSoftwareRequest() {
		return descOfSoftwareRequest;
	}

	public void setDescOfSoftwareRequest(String descOfSoftwareRequest) {
		this.descOfSoftwareRequest = descOfSoftwareRequest;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCurrentModel() {
		return currentModel;
	}

	public void setCurrentModel(String currentModel) {
		this.currentModel = currentModel;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getDoYouHaveOpenTicketRadio() {
		return doYouHaveOpenTicketRadio;
	}

	public void setDoYouHaveOpenTicketRadio(String doYouHaveOpenTicketRadio) {
		this.doYouHaveOpenTicketRadio = doYouHaveOpenTicketRadio;
	}

	public String getDoYouHaveOpenTicketOptionValue() {
		return doYouHaveOpenTicketOptionValue;
	}

	public void setDoYouHaveOpenTicketOptionValue(
			String doYouHaveOpenTicketOptionValue) {
		this.doYouHaveOpenTicketOptionValue = doYouHaveOpenTicketOptionValue;
	}

	public String getOpenTicketYES() {
		return openTicketYES;
	}

	public void setOpenTicketYES(String openTicketYES) {
		this.openTicketYES = openTicketYES;
	}

	public String getDoYouHaveProjectSrRadio() {
		return doYouHaveProjectSrRadio;
	}

	public void setDoYouHaveProjectSrRadio(String doYouHaveProjectSrRadio) {
		this.doYouHaveProjectSrRadio = doYouHaveProjectSrRadio;
	}

	public String getDoYouHaveProjectSrOptionValue() {
		return doYouHaveProjectSrOptionValue;
	}

	public void setDoYouHaveProjectSrOptionValue(
			String doYouHaveProjectSrOptionValue) {
		this.doYouHaveProjectSrOptionValue = doYouHaveProjectSrOptionValue;
	}

	public String getProjectSrYES() {
		return projectSrYES;
	}

	public void setProjectSrYES(String projectSrYES) {
		this.projectSrYES = projectSrYES;
	}

	public String getZenworkRadio() {
		return zenworkRadio;
	}

	public void setZenworkRadio(String zenworkRadio) {
		this.zenworkRadio = zenworkRadio;
	}

	public String getZenWorkOptionValue() {
		return zenWorkOptionValue;
	}

	public void setZenWorkOptionValue(String zenWorkOptionValue) {
		this.zenWorkOptionValue = zenWorkOptionValue;
	}

	public String getAntiVirusRadio() {
		return antiVirusRadio;
	}

	public void setAntiVirusRadio(String antiVirusRadio) {
		this.antiVirusRadio = antiVirusRadio;
	}

	public String getAntiVirusOptionValue() {
		return antiVirusOptionValue;
	}

	public void setAntiVirusOptionValue(String antiVirusOptionValue) {
		this.antiVirusOptionValue = antiVirusOptionValue;
	}

	public String getSelectApproverToken() {
		return selectApproverToken;
	}

	public void setSelectApproverToken(String selectApproverToken) {
		this.selectApproverToken = selectApproverToken;
	}

	public String getSelectApproverCode() {
		return selectApproverCode;
	}

	public void setSelectApproverCode(String selectApproverCode) {
		this.selectApproverCode = selectApproverCode;
	}

	public String getRequesterID() {
		return requesterID;
	}

	public void setRequesterID(String requesterID) {
		this.requesterID = requesterID;
	}

	public String getEmpBussUnit() {
		return empBussUnit;
	}

	public void setEmpBussUnit(String empBussUnit) {
		this.empBussUnit = empBussUnit;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getRequesterToken() {
		return requesterToken;
	}

	public void setRequesterToken(String requesterToken) {
		this.requesterToken = requesterToken;
	}

	public String getHwSwHiddenID() {
		return hwSwHiddenID;
	}

	public void setHwSwHiddenID(String hwSwHiddenID) {
		this.hwSwHiddenID = hwSwHiddenID;
	}

	
	public String getTrackingNumIterator() {
		return trackingNumIterator;
	}

	public void setTrackingNumIterator(String trackingNumIterator) {
		this.trackingNumIterator = trackingNumIterator;
	}


	public String getRequesterNameIterator() {
		return requesterNameIterator;
	}

	public void setRequesterNameIterator(String requesterNameIterator) {
		this.requesterNameIterator = requesterNameIterator;
	}


	public String getApplicationDateIterator() {
		return applicationDateIterator;
	}

	public void setApplicationDateIterator(String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}

	public String getRequestTrackingNumber() {
		return requestTrackingNumber;
	}

	public void setRequestTrackingNumber(String requestTrackingNumber) {
		this.requestTrackingNumber = requestTrackingNumber;
	}

	public TreeMap getMap() {
		return map;
	}

	public void setMap(TreeMap map) {
		this.map = map;
	}

	public TreeMap getSpecialSoftwareMap() {
		return specialSoftwareMap;
	}

	public void setSpecialSoftwareMap(TreeMap specialSoftwareMap) {
		this.specialSoftwareMap = specialSoftwareMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getApproverToken() {
		return approverToken;
	}

	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}

	public String getHwSwHiddenStatus() {
		return hwSwHiddenStatus;
	}

	public void setHwSwHiddenStatus(String hwSwHiddenStatus) {
		this.hwSwHiddenStatus = hwSwHiddenStatus;
	}

	public String getSoftwareHiddenValues() {
		return softwareHiddenValues;
	}

	public void setSoftwareHiddenValues(String softwareHiddenValues) {
		this.softwareHiddenValues = softwareHiddenValues;
	}

	public String getSpecialSoftwareHiddenValues() {
		return specialSoftwareHiddenValues;
	}

	public void setSpecialSoftwareHiddenValues(String specialSoftwareHiddenValues) {
		this.specialSoftwareHiddenValues = specialSoftwareHiddenValues;
	}

	public boolean isApproverCommentFlag() {
		return approverCommentFlag;
	}

	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}

	public String getApprName() {
		return apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getApprComments() {
		return apprComments;
	}

	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public String getApprStatus() {
		return apprStatus;
	}

	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	public String getCompletedByID() {
		return completedByID;
	}

	public void setCompletedByID(String completedByID) {
		this.completedByID = completedByID;
	}

	public String getCompletedByName() {
		return completedByName;
	}

	public void setCompletedByName(String completedByName) {
		this.completedByName = completedByName;
	}

	public String getCompletedByToken() {
		return completedByToken;
	}

	public void setCompletedByToken(String completedByToken) {
		this.completedByToken = completedByToken;
	}

	public String getEmpCountry() {
		return empCountry;
	}

	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}

	public String getEmpAttn() {
		return empAttn;
	}

	public void setEmpAttn(String empAttn) {
		this.empAttn = empAttn;
	}
	
}
