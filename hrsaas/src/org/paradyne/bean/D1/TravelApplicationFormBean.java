/**
 * 
 */
package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1439
 *
 */
public class TravelApplicationFormBean extends BeanBase{
	
	private boolean draftFlag;
	private String authorizedToken;
	private String flag="";
	
	private String initiator="";
	private String completedDate="";
	private String completedBy="";
	private String completedByCode="";
	//For Forward
	private String forwardApproverToken;
	private String forwardApproverCode;
	private String forwardApproverName;
//For approval
	
	private String relationAddress;
	private List<Object> approverCommentList;
	private String apprName;
	private String apprComments;
	private String apprDate;
	private String apprStatus;
	private String approverComments;
	private String  emailWorldTravel;
	
	//Hidden filed
	private String travelID;
	private String listType="";
	private String hiddenCode;
	private String level="";	
	private String status="";
	//Employee Details
	private String employeeName;
	private String employeeCode;
	private String employeeToken;
	private String deptCode;
	private String designation;
	
	//Approver Details
	private String approverToken;
	private String approverName;
	private String approverCode;
	private String secondApproverCode;
	private String secondApproverToken;
	private String secondApproverName;
	private String firstApproverCode;
	private String firstApproverToken;
	private String firstApproverName;
	
	//Flag deatls
	private boolean secondAppFlag;
	private boolean forApproval;
	private boolean readOnlyDetails;
	private boolean worldTravelFlag;
	private boolean forwardManager;
	
	//From Fields
	private String radioValue;
	private String otherChk;
	private String dateOfAtr;
	private String fromDate;
	private String toDate;
	private String empTravel;
	private String customer;
	private String managmentTraining;
	private String Other;
	private String training;
	private String acquisition;
	private String comments;
	private String air;
	private String car;
	private String hotel;
	private String lowCost;
	private String connections;
	private String atr;
	private String times;
	private String notBooked;
	private String carrierPreference;
	private String nonRefundable;
	private String airExp;
	private String carExp;
	private String totalAirExp;
	private String totalCarExp;
	private String hotelExp;
	private String totalHotelExp;
	private String mealExp;
	private String totalMealExp;
	private String otherExp;
	private String totalOtherExp;
	private String totalValue;
	
	
	//List Vaaiable
	private String applnStatus="";
	private String empToken;
	private String empName;
	private String applicationDate;
	ArrayList draftList = null;
	private boolean draftListLength = false;
	
	ArrayList applicationList = null;
	private boolean inProcessListLength = false;
	
	ArrayList sendBackList = null;
	private boolean sendBackLength = false;
	
	ArrayList approvedList = null;
	private boolean approvedListLength = false;
	
	ArrayList approvedCancelList = null;
	private boolean approvedCancelListLength = false;
	
	ArrayList rejectList = null;
	private boolean rejectListLength = false;
	
	
	ArrayList rejectCancelList = null;
	private boolean rejectCancelListLength = false;
	//Page Variable
	private String myPage="";
	private String myPageInProcess="";
	private String myPageSentBack="";
	private String myPageApproved="";
	private String myPageApprovedCancel="";
	private String myPageRejected="";
	private String myPageCancelRejected="";
	//added ganesh start
	private String destination = "";
	private String generalComments = "";
	private String deptName= "";
	//added ganesh end 
	
	private String pageForPendingApp;
	private String pageForPendingCancelApp;
	private String pageForApprovedApp;
	private String pageForRejectedApp;
	
	private String destinationItr = "";
	private String fromTravelDateItr = "";
	
	
	/**
	 * @return the destinationItr
	 */
	public String getDestinationItr() {
		return this.destinationItr;
	}
	/**
	 * @param destinationItr the destinationItr to set
	 */
	public void setDestinationItr(String destinationItr) {
		this.destinationItr = destinationItr;
	}
	/**
	 * @return the fromTravelDateItr
	 */
	public String getFromTravelDateItr() {
		return this.fromTravelDateItr;
	}
	/**
	 * @param fromTravelDateItr the fromTravelDateItr to set
	 */
	public void setFromTravelDateItr(String fromTravelDateItr) {
		this.fromTravelDateItr = fromTravelDateItr;
	}
	/**
	 * @return the pageForPendingApp
	 */
	public String getPageForPendingApp() {
		return this.pageForPendingApp;
	}
	/**
	 * @param pageForPendingApp the pageForPendingApp to set
	 */
	public void setPageForPendingApp(String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}
	/**
	 * @return the pageForPendingCancelApp
	 */
	public String getPageForPendingCancelApp() {
		return this.pageForPendingCancelApp;
	}
	/**
	 * @param pageForPendingCancelApp the pageForPendingCancelApp to set
	 */
	public void setPageForPendingCancelApp(String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}
	/**
	 * @return the pageForApprovedApp
	 */
	public String getPageForApprovedApp() {
		return this.pageForApprovedApp;
	}
	/**
	 * @param pageForApprovedApp the pageForApprovedApp to set
	 */
	public void setPageForApprovedApp(String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}
	/**
	 * @return the pageForRejectedApp
	 */
	public String getPageForRejectedApp() {
		return this.pageForRejectedApp;
	}
	/**
	 * @param pageForRejectedApp the pageForRejectedApp to set
	 */
	public void setPageForRejectedApp(String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getGeneralComments() {
		return generalComments;
	}
	public void setGeneralComments(String generalComments) {
		this.generalComments = generalComments;
	}
	
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}
	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	/**
	 * @return the draftList
	 */
	public ArrayList getDraftList() {
		return draftList;
	}
	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}
	/**
	 * @return the draftListLength
	 */
	public boolean isDraftListLength() {
		return draftListLength;
	}
	/**
	 * @param draftListLength the draftListLength to set
	 */
	public void setDraftListLength(boolean draftListLength) {
		this.draftListLength = draftListLength;
	}
	/**
	 * @return the applicationList
	 */
	public ArrayList getApplicationList() {
		return applicationList;
	}
	/**
	 * @param applicationList the applicationList to set
	 */
	public void setApplicationList(ArrayList applicationList) {
		this.applicationList = applicationList;
	}
	/**
	 * @return the inProcessListLength
	 */
	public boolean isInProcessListLength() {
		return inProcessListLength;
	}
	/**
	 * @param inProcessListLength the inProcessListLength to set
	 */
	public void setInProcessListLength(boolean inProcessListLength) {
		this.inProcessListLength = inProcessListLength;
	}
	/**
	 * @return the sendBackList
	 */
	public ArrayList getSendBackList() {
		return sendBackList;
	}
	/**
	 * @param sendBackList the sendBackList to set
	 */
	public void setSendBackList(ArrayList sendBackList) {
		this.sendBackList = sendBackList;
	}
	/**
	 * @return the sendBackLength
	 */
	public boolean isSendBackLength() {
		return sendBackLength;
	}
	/**
	 * @param sendBackLength the sendBackLength to set
	 */
	public void setSendBackLength(boolean sendBackLength) {
		this.sendBackLength = sendBackLength;
	}
	/**
	 * @return the approvedList
	 */
	public ArrayList getApprovedList() {
		return approvedList;
	}
	/**
	 * @param approvedList the approvedList to set
	 */
	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}
	/**
	 * @return the approvedListLength
	 */
	public boolean isApprovedListLength() {
		return approvedListLength;
	}
	/**
	 * @param approvedListLength the approvedListLength to set
	 */
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	/**
	 * @return the approvedCancelList
	 */
	public ArrayList getApprovedCancelList() {
		return approvedCancelList;
	}
	/**
	 * @param approvedCancelList the approvedCancelList to set
	 */
	public void setApprovedCancelList(ArrayList approvedCancelList) {
		this.approvedCancelList = approvedCancelList;
	}
	/**
	 * @return the approvedCancelListLength
	 */
	public boolean isApprovedCancelListLength() {
		return approvedCancelListLength;
	}
	/**
	 * @param approvedCancelListLength the approvedCancelListLength to set
	 */
	public void setApprovedCancelListLength(boolean approvedCancelListLength) {
		this.approvedCancelListLength = approvedCancelListLength;
	}
	/**
	 * @return the rejectList
	 */
	public ArrayList getRejectList() {
		return rejectList;
	}
	/**
	 * @param rejectList the rejectList to set
	 */
	public void setRejectList(ArrayList rejectList) {
		this.rejectList = rejectList;
	}
	/**
	 * @return the rejectListLength
	 */
	public boolean isRejectListLength() {
		return rejectListLength;
	}
	/**
	 * @param rejectListLength the rejectListLength to set
	 */
	public void setRejectListLength(boolean rejectListLength) {
		this.rejectListLength = rejectListLength;
	}
	/**
	 * @return the rejectCancelList
	 */
	public ArrayList getRejectCancelList() {
		return rejectCancelList;
	}
	/**
	 * @param rejectCancelList the rejectCancelList to set
	 */
	public void setRejectCancelList(ArrayList rejectCancelList) {
		this.rejectCancelList = rejectCancelList;
	}
	/**
	 * @return the rejectCancelListLength
	 */
	public boolean isRejectCancelListLength() {
		return rejectCancelListLength;
	}
	/**
	 * @param rejectCancelListLength the rejectCancelListLength to set
	 */
	public void setRejectCancelListLength(boolean rejectCancelListLength) {
		this.rejectCancelListLength = rejectCancelListLength;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return the myPageSentBack
	 */
	public String getMyPageSentBack() {
		return myPageSentBack;
	}
	/**
	 * @param myPageSentBack the myPageSentBack to set
	 */
	public void setMyPageSentBack(String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}
	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return myPageApproved;
	}
	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	/**
	 * @return the myPageApprovedCancel
	 */
	public String getMyPageApprovedCancel() {
		return myPageApprovedCancel;
	}
	/**
	 * @param myPageApprovedCancel the myPageApprovedCancel to set
	 */
	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}
	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return myPageRejected;
	}
	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the myPageCancelRejected
	 */
	public String getMyPageCancelRejected() {
		return myPageCancelRejected;
	}
	/**
	 * @param myPageCancelRejected the myPageCancelRejected to set
	 */
	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	/**
	 * @return the approverToken
	 */
	public String getApproverToken() {
		return approverToken;
	}
	/**
	 * @param approverToken the approverToken to set
	 */
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the secondApproverCode
	 */
	public String getSecondApproverCode() {
		return secondApproverCode;
	}
	/**
	 * @param secondApproverCode the secondApproverCode to set
	 */
	public void setSecondApproverCode(String secondApproverCode) {
		this.secondApproverCode = secondApproverCode;
	}
	/**
	 * @return the secondApproverToken
	 */
	public String getSecondApproverToken() {
		return secondApproverToken;
	}
	/**
	 * @param secondApproverToken the secondApproverToken to set
	 */
	public void setSecondApproverToken(String secondApproverToken) {
		this.secondApproverToken = secondApproverToken;
	}
	/**
	 * @return the secondApproverName
	 */
	public String getSecondApproverName() {
		return secondApproverName;
	}
	/**
	 * @param secondApproverName the secondApproverName to set
	 */
	public void setSecondApproverName(String secondApproverName) {
		this.secondApproverName = secondApproverName;
	}
	/**
	 * @return the firstApproverCode
	 */
	public String getFirstApproverCode() {
		return firstApproverCode;
	}
	/**
	 * @param firstApproverCode the firstApproverCode to set
	 */
	public void setFirstApproverCode(String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}
	/**
	 * @return the firstApproverToken
	 */
	public String getFirstApproverToken() {
		return firstApproverToken;
	}
	/**
	 * @param firstApproverToken the firstApproverToken to set
	 */
	public void setFirstApproverToken(String firstApproverToken) {
		this.firstApproverToken = firstApproverToken;
	}
	/**
	 * @return the firstApproverName
	 */
	public String getFirstApproverName() {
		return firstApproverName;
	}
	/**
	 * @param firstApproverName the firstApproverName to set
	 */
	public void setFirstApproverName(String firstApproverName) {
		this.firstApproverName = firstApproverName;
	}
	/**
	 * @return the secondAppFlag
	 */
	public boolean isSecondAppFlag() {
		return secondAppFlag;
	}
	/**
	 * @param secondAppFlag the secondAppFlag to set
	 */
	public void setSecondAppFlag(boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
	}
	/**
	 * @return the forApproval
	 */
	public boolean isForApproval() {
		return forApproval;
	}
	/**
	 * @param forApproval the forApproval to set
	 */
	public void setForApproval(boolean forApproval) {
		this.forApproval = forApproval;
	}
	/**
	 * @return the readOnlyDetails
	 */
	public boolean isReadOnlyDetails() {
		return readOnlyDetails;
	}
	/**
	 * @param readOnlyDetails the readOnlyDetails to set
	 */
	public void setReadOnlyDetails(boolean readOnlyDetails) {
		this.readOnlyDetails = readOnlyDetails;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the relationAddress
	 */
	public String getRelationAddress() {
		return relationAddress;
	}
	/**
	 * @param relationAddress the relationAddress to set
	 */
	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}
	/**
	 * @return the approverCommentList
	 */
	public List<Object> getApproverCommentList() {
		return approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(List<Object> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return apprName;
	}
	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return apprComments;
	}
	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return apprDate;
	}
	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}
	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the travelID
	 */
	public String getTravelID() {
		return travelID;
	}
	/**
	 * @param travelID the travelID to set
	 */
	public void setTravelID(String travelID) {
		this.travelID = travelID;
	}
	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the hiddenCode
	 */
	public String getHiddenCode() {
		return hiddenCode;
	}
	/**
	 * @param hiddenCode the hiddenCode to set
	 */
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the dateOfAtr
	 */
	public String getDateOfAtr() {
		return dateOfAtr;
	}
	/**
	 * @param dateOfAtr the dateOfAtr to set
	 */
	public void setDateOfAtr(String dateOfAtr) {
		this.dateOfAtr = dateOfAtr;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the empTravel
	 */
	public String getEmpTravel() {
		return empTravel;
	}
	/**
	 * @param empTravel the empTravel to set
	 */
	public void setEmpTravel(String empTravel) {
		this.empTravel = empTravel;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the managmentTraining
	 */
	public String getManagmentTraining() {
		return managmentTraining;
	}
	/**
	 * @param managmentTraining the managmentTraining to set
	 */
	public void setManagmentTraining(String managmentTraining) {
		this.managmentTraining = managmentTraining;
	}
	/**
	 * @return the other
	 */
	public String getOther() {
		return Other;
	}
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		Other = other;
	}
	/**
	 * @return the training
	 */
	public String getTraining() {
		return training;
	}
	/**
	 * @param training the training to set
	 */
	public void setTraining(String training) {
		this.training = training;
	}
	/**
	 * @return the acquisition
	 */
	public String getAcquisition() {
		return acquisition;
	}
	/**
	 * @param acquisition the acquisition to set
	 */
	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the air
	 */
	public String getAir() {
		return air;
	}
	/**
	 * @param air the air to set
	 */
	public void setAir(String air) {
		this.air = air;
	}
	/**
	 * @return the car
	 */
	public String getCar() {
		return car;
	}
	/**
	 * @param car the car to set
	 */
	public void setCar(String car) {
		this.car = car;
	}
	/**
	 * @return the hotel
	 */
	public String getHotel() {
		return hotel;
	}
	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	/**
	 * @return the lowCost
	 */
	public String getLowCost() {
		return lowCost;
	}
	/**
	 * @param lowCost the lowCost to set
	 */
	public void setLowCost(String lowCost) {
		this.lowCost = lowCost;
	}
	/**
	 * @return the connections
	 */
	public String getConnections() {
		return connections;
	}
	/**
	 * @param connections the connections to set
	 */
	public void setConnections(String connections) {
		this.connections = connections;
	}
	/**
	 * @return the atr
	 */
	public String getAtr() {
		return atr;
	}
	/**
	 * @param atr the atr to set
	 */
	public void setAtr(String atr) {
		this.atr = atr;
	}
	/**
	 * @return the times
	 */
	public String getTimes() {
		return times;
	}
	/**
	 * @param times the times to set
	 */
	public void setTimes(String times) {
		this.times = times;
	}
	/**
	 * @return the notBooked
	 */
	public String getNotBooked() {
		return notBooked;
	}
	/**
	 * @param notBooked the notBooked to set
	 */
	public void setNotBooked(String notBooked) {
		this.notBooked = notBooked;
	}
	/**
	 * @return the carrierPreference
	 */
	public String getCarrierPreference() {
		return carrierPreference;
	}
	/**
	 * @param carrierPreference the carrierPreference to set
	 */
	public void setCarrierPreference(String carrierPreference) {
		this.carrierPreference = carrierPreference;
	}
	/**
	 * @return the nonRefundable
	 */
	public String getNonRefundable() {
		return nonRefundable;
	}
	/**
	 * @param nonRefundable the nonRefundable to set
	 */
	public void setNonRefundable(String nonRefundable) {
		this.nonRefundable = nonRefundable;
	}
	/**
	 * @return the airExp
	 */
	public String getAirExp() {
		return airExp;
	}
	/**
	 * @param airExp the airExp to set
	 */
	public void setAirExp(String airExp) {
		this.airExp = airExp;
	}
	/**
	 * @return the carExp
	 */
	public String getCarExp() {
		return carExp;
	}
	/**
	 * @param carExp the carExp to set
	 */
	public void setCarExp(String carExp) {
		this.carExp = carExp;
	}
	/**
	 * @return the totalAirExp
	 */
	public String getTotalAirExp() {
		return totalAirExp;
	}
	/**
	 * @param totalAirExp the totalAirExp to set
	 */
	public void setTotalAirExp(String totalAirExp) {
		this.totalAirExp = totalAirExp;
	}
	/**
	 * @return the totalCarExp
	 */
	public String getTotalCarExp() {
		return totalCarExp;
	}
	/**
	 * @param totalCarExp the totalCarExp to set
	 */
	public void setTotalCarExp(String totalCarExp) {
		this.totalCarExp = totalCarExp;
	}
	/**
	 * @return the hotelExp
	 */
	public String getHotelExp() {
		return hotelExp;
	}
	/**
	 * @param hotelExp the hotelExp to set
	 */
	public void setHotelExp(String hotelExp) {
		this.hotelExp = hotelExp;
	}
	/**
	 * @return the totalHotelExp
	 */
	public String getTotalHotelExp() {
		return totalHotelExp;
	}
	/**
	 * @param totalHotelExp the totalHotelExp to set
	 */
	public void setTotalHotelExp(String totalHotelExp) {
		this.totalHotelExp = totalHotelExp;
	}
	/**
	 * @return the mealExp
	 */
	public String getMealExp() {
		return mealExp;
	}
	/**
	 * @param mealExp the mealExp to set
	 */
	public void setMealExp(String mealExp) {
		this.mealExp = mealExp;
	}
	/**
	 * @return the totalMealExp
	 */
	public String getTotalMealExp() {
		return totalMealExp;
	}
	/**
	 * @param totalMealExp the totalMealExp to set
	 */
	public void setTotalMealExp(String totalMealExp) {
		this.totalMealExp = totalMealExp;
	}
	/**
	 * @return the otherExp
	 */
	public String getOtherExp() {
		return otherExp;
	}
	/**
	 * @param otherExp the otherExp to set
	 */
	public void setOtherExp(String otherExp) {
		this.otherExp = otherExp;
	}
	/**
	 * @return the totalOtherExp
	 */
	public String getTotalOtherExp() {
		return totalOtherExp;
	}
	/**
	 * @param totalOtherExp the totalOtherExp to set
	 */
	public void setTotalOtherExp(String totalOtherExp) {
		this.totalOtherExp = totalOtherExp;
	}
	/**
	 * @return the radioValue
	 */
	public String getRadioValue() {
		return radioValue;
	}
	/**
	 * @param radioValue the radioValue to set
	 */
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	/**
	 * @return the otherChk
	 */
	public String getOtherChk() {
		return otherChk;
	}
	/**
	 * @param otherChk the otherChk to set
	 */
	public void setOtherChk(String otherChk) {
		this.otherChk = otherChk;
	}
	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return applnStatus;
	}
	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}
	/**
	 * @return the totalValue
	 */
	public String getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}
	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	/**
	 * @return the worldTravelFlag
	 */
	public boolean isWorldTravelFlag() {
		return worldTravelFlag;
	}
	/**
	 * @param worldTravelFlag the worldTravelFlag to set
	 */
	public void setWorldTravelFlag(boolean worldTravelFlag) {
		this.worldTravelFlag = worldTravelFlag;
	}
	/**
	 * @return the emailWorldTravel
	 */
	public String getEmailWorldTravel() {
		return emailWorldTravel;
	}
	/**
	 * @param emailWorldTravel the emailWorldTravel to set
	 */
	public void setEmailWorldTravel(String emailWorldTravel) {
		this.emailWorldTravel = emailWorldTravel;
	}
	/**
	 * @return the forwardApproverToken
	 */
	public String getForwardApproverToken() {
		return forwardApproverToken;
	}
	/**
	 * @param forwardApproverToken the forwardApproverToken to set
	 */
	public void setForwardApproverToken(String forwardApproverToken) {
		this.forwardApproverToken = forwardApproverToken;
	}
	/**
	 * @return the forwardApproverCode
	 */
	public String getForwardApproverCode() {
		return forwardApproverCode;
	}
	/**
	 * @param forwardApproverCode the forwardApproverCode to set
	 */
	public void setForwardApproverCode(String forwardApproverCode) {
		this.forwardApproverCode = forwardApproverCode;
	}
	/**
	 * @return the forwardApproverName
	 */
	public String getForwardApproverName() {
		return forwardApproverName;
	}
	/**
	 * @param forwardApproverName the forwardApproverName to set
	 */
	public void setForwardApproverName(String forwardApproverName) {
		this.forwardApproverName = forwardApproverName;
	}
	/**
	 * @return the forwardManager
	 */
	public boolean isForwardManager() {
		return forwardManager;
	}
	/**
	 * @param forwardManager the forwardManager to set
	 */
	public void setForwardManager(boolean forwardManager) {
		this.forwardManager = forwardManager;
	}
	/**
	 * @return the authorizedToken
	 */
	public String getAuthorizedToken() {
		return authorizedToken;
	}
	/**
	 * @param authorizedToken the authorizedToken to set
	 */
	public void setAuthorizedToken(String authorizedToken) {
		this.authorizedToken = authorizedToken;
	}
	/**
	 * @return the initiator
	 */
	public String getInitiator() {
		return initiator;
	}
	/**
	 * @param initiator the initiator to set
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	/**
	 * @return the completedDate
	 */
	public String getCompletedDate() {
		return completedDate;
	}
	/**
	 * @param completedDate the completedDate to set
	 */
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	/**
	 * @return the completedBy
	 */
	public String getCompletedBy() {
		return completedBy;
	}
	/**
	 * @param completedBy the completedBy to set
	 */
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	/**
	 * @return the completedByCode
	 */
	public String getCompletedByCode() {
		return completedByCode;
	}
	/**
	 * @param completedByCode the completedByCode to set
	 */
	public void setCompletedByCode(String completedByCode) {
		this.completedByCode = completedByCode;
	}
	/**
	 * @return the draftFlag
	 */
	public boolean isDraftFlag() {
		return draftFlag;
	}
	/**
	 * @param draftFlag the draftFlag to set
	 */
	public void setDraftFlag(boolean draftFlag) {
		this.draftFlag = draftFlag;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
