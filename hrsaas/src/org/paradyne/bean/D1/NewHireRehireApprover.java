package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class NewHireRehireApprover extends BeanBase {
	
	private String myPage = "";	
	private String myPagePendingCancel = "";
	private String myPageApproved = "";
	private String myPageRejected = "";
	private String hireRehireApprovalId = "";
	
	ArrayList approverCommentList = null;
	boolean approverCommentsFlag = false;
	
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";

	ArrayList pendingIteratorList = null;
	private boolean pendingListLength = false;
	ArrayList pendingCancellationIteratorList = null;
	private boolean  pendingCancellationListLength = false;
	ArrayList approveredIteratorList = null;
	private boolean approvedListLength = false;
	ArrayList rejectedIteratorList = null;
	private boolean rejectedListLength = false;
	
	private String employeeIDIterator = "";
	private String employeeTokenIterator = "";
	private String employeeNameIterator = "";
	private String applicationDateIterator ="";
	
	private String totalRecords = "";
	private String listType = "";
	private String requestApproverID = "";
	private String employeeID = "";
	private String employeeToken = "";
	private String fromName = "";
	private String toDate ="";
	private String vendorName = "";
	private String vendorNumber = "";
	private String purchaseOrderNumber ="";
	private String lineNumber = "";
	private String quantity = "";
	private String voucherNumber = "";
	private String reasonForRequest = "";
	private String rma = "";
	private String airBillNumber = "";
	private String didVendorIssueCreditMemo = "";
	private String creditMemoRadio = "";
	private String comments = "";
	private String status = "";
	private String listTypeDetailPage = "";
	
	private String approverName = "";
	private String approverCode = "";
	private String approverToken = "";
	private boolean secondAppFlag= false;
	private String employeeCode = "";
	private String empToken = "";
	private String country = "";
	private String stateName = "";
	private String cityId;
	private String cityName;
	private String custCityId= "";
	private String custCityName = "";
	private String custStateName = "";
	private String custShiftCode = "";
	private String custShiftType= "";
	private String decisionCityId= "";
	private String decisionCityName ="";
	private String decisionStateName ="";
	private String decisionShiftCode="";
	private String decisionShiftType ="";
	private String deptCode = "";
	private String deptName = "";
	private String executiveCode = "";
	private String executiveName= "";
	private String officeCityId = "";
	private String officeCityName = "";
	private String OfficeStateName = "";
	private String empFirstName = "";
	private String empMiddleName = "";
	private String empLastName = "";
	private String socialSecurityNumber = "";
	private String socialInsuranceNumber = "";
	private String empHomeAddress = "";
	private String homePhoneNumber = "";
	private String reqNumber = "";
	private String sex = "";
	private String maritalStatus = "";
	private String qualifyName = "";
	private String qualCode = "";
	private String birthDate= "";
	private String mediumName = "";
	private String mediumCode = "";
	private String castName ="";
	private String castCode = "";
	private String hireDate ="";
	private String actionReason = "";
	private String jobCode = "";
	private String jobTitle = "";
	private String acquisitionDate = "";
	private String acquiredCompany ="";
	private String preacqusitionDate = "";
	private String userProfile = "";
	private String shiftType = "";
	private String shiftCode = "";
	private String regTemp= "";
	private String flsaStatus= "";
	private String customerName = "";
	private String physicalAddress = "";
	private String salaryPlan = "";
	private String payGroup = "";
	private String cadreName = "";			////grade
	private String cadreCode = "";
	private String weeklyHours = "";
	private String biweeklySalary = "";
	private String annualSalary ="";
	private String zip = "";
	private String custRegTemp = "";
	private String custflsaStatus = "";
	private String decisionzip = "";
	private String regTempDecision = "";
	private String decisionflsaStatus = "";
	private String hireStatus ="";
	private String custZip = "";
	private String userProfileRadioOptionValue = "";
	
	private String decisionPhysicalAddress = "";
	private String applicationDate = "";
	private String custZipId = "";
	private String custZipCode = "";
	private String approverComments = "";
	public String trackingNo="";
	public String employeeName="";
	
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	
	private String exeEmployeeToken = "";
	private String exeEmployeeName = "";
	private String exeEmployeeCode="";
	
	
	private String divCode = "";
	private String divName = "";
	private String centerCode = "";
	private String centerName = "";
	private String rankCode = "";
	private String rankName = "";
	private String empId = "";
	private String emailId = "";
	private String shiftCodeAppr="";
	private String shiftTypeAppr="";
	boolean empOfficeDtlFlag = false;
	
	private String fulltimeParttime="";
	private String custfulltimeParttime="";
	
	private String empTypeCode="";
	private String empTypeName=""; 
	
	//Added by Nilesh 
	private String actionType= "";
	private String displayFlag = "";
	
	private String newHiredEmployeeToken = "";
	private String newHiredEmployeeName = "";
	private String newHiredEmployeeCode = "";
	private String emailAddress = "";
	private String  labelFlag = "";
	
	private String actionReasonItr = "";
	
	
	/**
	 * @return the actionReasonItr
	 */
	public String getActionReasonItr() {
		return this.actionReasonItr;
	}
	/**
	 * @param actionReasonItr the actionReasonItr to set
	 */
	public void setActionReasonItr(String actionReasonItr) {
		this.actionReasonItr = actionReasonItr;
	}
	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getFulltimeParttime() {
		return fulltimeParttime;
	}
	public void setFulltimeParttime(String fulltimeParttime) {
		this.fulltimeParttime = fulltimeParttime;
	}
	public String getCustfulltimeParttime() {
		return custfulltimeParttime;
	}
	public void setCustfulltimeParttime(String custfulltimeParttime) {
		this.custfulltimeParttime = custfulltimeParttime;
	}
	public String getShiftCodeAppr() {
		return shiftCodeAppr;
	}
	public void setShiftCodeAppr(String shiftCodeAppr) {
		this.shiftCodeAppr = shiftCodeAppr;
	}
	public String getShiftTypeAppr() {
		return shiftTypeAppr;
	}
	public void setShiftTypeAppr(String shiftTypeAppr) {
		this.shiftTypeAppr = shiftTypeAppr;
	}
	public String getExeEmployeeToken() {
		return exeEmployeeToken;
	}
	public void setExeEmployeeToken(String exeEmployeeToken) {
		this.exeEmployeeToken = exeEmployeeToken;
	}
	public String getExeEmployeeName() {
		return exeEmployeeName;
	}
	public void setExeEmployeeName(String exeEmployeeName) {
		this.exeEmployeeName = exeEmployeeName;
	}
	public String getExeEmployeeCode() {
		return exeEmployeeCode;
	}
	public void setExeEmployeeCode(String exeEmployeeCode) {
		this.exeEmployeeCode = exeEmployeeCode;
	}
	public String getInitiatorCode() {
		return initiatorCode;
	}
	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	public String getInitiatorDate() {
		return initiatorDate;
	}
	public void setInitiatorDate(String initiatorDate) {
		this.initiatorDate = initiatorDate;
	}
	public String getInitiatorToken() {
		return initiatorToken;
	}
	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}
	public String getInitiatorName() {
		return initiatorName;
	}
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getMyPagePendingCancel() {
		return myPagePendingCancel;
	}
	public void setMyPagePendingCancel(String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
	}
	public String getMyPageApproved() {
		return myPageApproved;
	}
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getHireRehireApprovalId() {
		return hireRehireApprovalId;
	}
	public void setHireRehireApprovalId(String hireRehireApprovalId) {
		this.hireRehireApprovalId = hireRehireApprovalId;
	}
	public ArrayList getPendingIteratorList() {
		return pendingIteratorList;
	}
	public void setPendingIteratorList(ArrayList pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}
	public boolean isPendingListLength() {
		return pendingListLength;
	}
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	public ArrayList getPendingCancellationIteratorList() {
		return pendingCancellationIteratorList;
	}
	public void setPendingCancellationIteratorList(
			ArrayList pendingCancellationIteratorList) {
		this.pendingCancellationIteratorList = pendingCancellationIteratorList;
	}
	public boolean isPendingCancellationListLength() {
		return pendingCancellationListLength;
	}
	public void setPendingCancellationListLength(
			boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	public ArrayList getApproveredIteratorList() {
		return approveredIteratorList;
	}
	public void setApproveredIteratorList(ArrayList approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}
	public boolean isApprovedListLength() {
		return approvedListLength;
	}
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	public ArrayList getRejectedIteratorList() {
		return rejectedIteratorList;
	}
	public void setRejectedIteratorList(ArrayList rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	public String getEmployeeIDIterator() {
		return employeeIDIterator;
	}
	public void setEmployeeIDIterator(String employeeIDIterator) {
		this.employeeIDIterator = employeeIDIterator;
	}
	public String getEmployeeTokenIterator() {
		return employeeTokenIterator;
	}
	public void setEmployeeTokenIterator(String employeeTokenIterator) {
		this.employeeTokenIterator = employeeTokenIterator;
	}
	public String getEmployeeNameIterator() {
		return employeeNameIterator;
	}
	public void setEmployeeNameIterator(String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}
	public String getApplicationDateIterator() {
		return applicationDateIterator;
	}
	public void setApplicationDateIterator(String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorNumber() {
		return vendorNumber;
	}
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getReasonForRequest() {
		return reasonForRequest;
	}
	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}
	public String getRma() {
		return rma;
	}
	public void setRma(String rma) {
		this.rma = rma;
	}
	public String getAirBillNumber() {
		return airBillNumber;
	}
	public void setAirBillNumber(String airBillNumber) {
		this.airBillNumber = airBillNumber;
	}
	public String getDidVendorIssueCreditMemo() {
		return didVendorIssueCreditMemo;
	}
	public void setDidVendorIssueCreditMemo(String didVendorIssueCreditMemo) {
		this.didVendorIssueCreditMemo = didVendorIssueCreditMemo;
	}
	public String getCreditMemoRadio() {
		return creditMemoRadio;
	}
	public void setCreditMemoRadio(String creditMemoRadio) {
		this.creditMemoRadio = creditMemoRadio;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getListTypeDetailPage() {
		return listTypeDetailPage;
	}
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
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
	public boolean isSecondAppFlag() {
		return secondAppFlag;
	}
	public void setSecondAppFlag(boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCustCityId() {
		return custCityId;
	}
	public void setCustCityId(String custCityId) {
		this.custCityId = custCityId;
	}
	public String getCustCityName() {
		return custCityName;
	}
	public void setCustCityName(String custCityName) {
		this.custCityName = custCityName;
	}
	public String getCustStateName() {
		return custStateName;
	}
	public void setCustStateName(String custStateName) {
		this.custStateName = custStateName;
	}
	public String getCustShiftCode() {
		return custShiftCode;
	}
	public void setCustShiftCode(String custShiftCode) {
		this.custShiftCode = custShiftCode;
	}
	public String getCustShiftType() {
		return custShiftType;
	}
	public void setCustShiftType(String custShiftType) {
		this.custShiftType = custShiftType;
	}
	public String getDecisionCityId() {
		return decisionCityId;
	}
	public void setDecisionCityId(String decisionCityId) {
		this.decisionCityId = decisionCityId;
	}
	public String getDecisionCityName() {
		return decisionCityName;
	}
	public void setDecisionCityName(String decisionCityName) {
		this.decisionCityName = decisionCityName;
	}
	public String getDecisionStateName() {
		return decisionStateName;
	}
	public void setDecisionStateName(String decisionStateName) {
		this.decisionStateName = decisionStateName;
	}
	public String getDecisionShiftCode() {
		return decisionShiftCode;
	}
	public void setDecisionShiftCode(String decisionShiftCode) {
		this.decisionShiftCode = decisionShiftCode;
	}
	public String getDecisionShiftType() {
		return decisionShiftType;
	}
	public void setDecisionShiftType(String decisionShiftType) {
		this.decisionShiftType = decisionShiftType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getExecutiveCode() {
		return executiveCode;
	}
	public void setExecutiveCode(String executiveCode) {
		this.executiveCode = executiveCode;
	}
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	public String getOfficeCityId() {
		return officeCityId;
	}
	public void setOfficeCityId(String officeCityId) {
		this.officeCityId = officeCityId;
	}
	public String getOfficeCityName() {
		return officeCityName;
	}
	public void setOfficeCityName(String officeCityName) {
		this.officeCityName = officeCityName;
	}
	public String getOfficeStateName() {
		return OfficeStateName;
	}
	public void setOfficeStateName(String officeStateName) {
		OfficeStateName = officeStateName;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpMiddleName() {
		return empMiddleName;
	}
	public void setEmpMiddleName(String empMiddleName) {
		this.empMiddleName = empMiddleName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public String getSocialInsuranceNumber() {
		return socialInsuranceNumber;
	}
	public void setSocialInsuranceNumber(String socialInsuranceNumber) {
		this.socialInsuranceNumber = socialInsuranceNumber;
	}
	public String getEmpHomeAddress() {
		return empHomeAddress;
	}
	public void setEmpHomeAddress(String empHomeAddress) {
		this.empHomeAddress = empHomeAddress;
	}
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}
	public String getReqNumber() {
		return reqNumber;
	}
	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getQualifyName() {
		return qualifyName;
	}
	public void setQualifyName(String qualifyName) {
		this.qualifyName = qualifyName;
	}
	public String getQualCode() {
		return qualCode;
	}
	public void setQualCode(String qualCode) {
		this.qualCode = qualCode;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getMediumName() {
		return mediumName;
	}
	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}
	public String getMediumCode() {
		return mediumCode;
	}
	public void setMediumCode(String mediumCode) {
		this.mediumCode = mediumCode;
	}
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}
	public String getCastCode() {
		return castCode;
	}
	public void setCastCode(String castCode) {
		this.castCode = castCode;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getActionReason() {
		return actionReason;
	}
	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	public String getAcquiredCompany() {
		return acquiredCompany;
	}
	public void setAcquiredCompany(String acquiredCompany) {
		this.acquiredCompany = acquiredCompany;
	}
	public String getPreacqusitionDate() {
		return preacqusitionDate;
	}
	public void setPreacqusitionDate(String preacqusitionDate) {
		this.preacqusitionDate = preacqusitionDate;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getRegTemp() {
		return regTemp;
	}
	public void setRegTemp(String regTemp) {
		this.regTemp = regTemp;
	}
	public String getFlsaStatus() {
		return flsaStatus;
	}
	public void setFlsaStatus(String flsaStatus) {
		this.flsaStatus = flsaStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public String getSalaryPlan() {
		return salaryPlan;
	}
	public void setSalaryPlan(String salaryPlan) {
		this.salaryPlan = salaryPlan;
	}
	public String getPayGroup() {
		return payGroup;
	}
	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getCadreCode() {
		return cadreCode;
	}
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	public String getWeeklyHours() {
		return weeklyHours;
	}
	public void setWeeklyHours(String weeklyHours) {
		this.weeklyHours = weeklyHours;
	}
	public String getBiweeklySalary() {
		return biweeklySalary;
	}
	public void setBiweeklySalary(String biweeklySalary) {
		this.biweeklySalary = biweeklySalary;
	}
	public String getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCustRegTemp() {
		return custRegTemp;
	}
	public void setCustRegTemp(String custRegTemp) {
		this.custRegTemp = custRegTemp;
	}
	public String getCustflsaStatus() {
		return custflsaStatus;
	}
	public void setCustflsaStatus(String custflsaStatus) {
		this.custflsaStatus = custflsaStatus;
	}
	public String getDecisionzip() {
		return decisionzip;
	}
	public void setDecisionzip(String decisionzip) {
		this.decisionzip = decisionzip;
	}
	public String getRegTempDecision() {
		return regTempDecision;
	}
	public void setRegTempDecision(String regTempDecision) {
		this.regTempDecision = regTempDecision;
	}
	public String getDecisionflsaStatus() {
		return decisionflsaStatus;
	}
	public void setDecisionflsaStatus(String decisionflsaStatus) {
		this.decisionflsaStatus = decisionflsaStatus;
	}
	public String getHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(String hireStatus) {
		this.hireStatus = hireStatus;
	}
	public String getCustZip() {
		return custZip;
	}
	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}
	public String getUserProfileRadioOptionValue() {
		return userProfileRadioOptionValue;
	}
	public void setUserProfileRadioOptionValue(String userProfileRadioOptionValue) {
		this.userProfileRadioOptionValue = userProfileRadioOptionValue;
	}
	public String getDecisionPhysicalAddress() {
		return decisionPhysicalAddress;
	}
	public void setDecisionPhysicalAddress(String decisionPhysicalAddress) {
		this.decisionPhysicalAddress = decisionPhysicalAddress;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getCustZipId() {
		return custZipId;
	}
	public void setCustZipId(String custZipId) {
		this.custZipId = custZipId;
	}
	public String getCustZipCode() {
		return custZipCode;
	}
	public void setCustZipCode(String custZipCode) {
		this.custZipCode = custZipCode;
	}
	public String getRequestApproverID() {
		return requestApproverID;
	}
	public void setRequestApproverID(String requestApproverID) {
		this.requestApproverID = requestApproverID;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
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
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getRankCode() {
		return rankCode;
	}
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public boolean isEmpOfficeDtlFlag() {
		return empOfficeDtlFlag;
	}
	public void setEmpOfficeDtlFlag(boolean empOfficeDtlFlag) {
		this.empOfficeDtlFlag = empOfficeDtlFlag;
	}
	/**
	 * @return the displayFlag
	 */
	public String getDisplayFlag() {
		return displayFlag;
	}
	/**
	 * @param displayFlag the displayFlag to set
	 */
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	/**
	 * @return the newHiredEmployeeToken
	 */
	public String getNewHiredEmployeeToken() {
		return newHiredEmployeeToken;
	}
	/**
	 * @param newHiredEmployeeToken the newHiredEmployeeToken to set
	 */
	public void setNewHiredEmployeeToken(String newHiredEmployeeToken) {
		this.newHiredEmployeeToken = newHiredEmployeeToken;
	}
	/**
	 * @return the newHiredEmployeeName
	 */
	public String getNewHiredEmployeeName() {
		return newHiredEmployeeName;
	}
	/**
	 * @param newHiredEmployeeName the newHiredEmployeeName to set
	 */
	public void setNewHiredEmployeeName(String newHiredEmployeeName) {
		this.newHiredEmployeeName = newHiredEmployeeName;
	}
	/**
	 * @return the newHiredEmployeeCode
	 */
	public String getNewHiredEmployeeCode() {
		return newHiredEmployeeCode;
	}
	/**
	 * @param newHiredEmployeeCode the newHiredEmployeeCode to set
	 */
	public void setNewHiredEmployeeCode(String newHiredEmployeeCode) {
		this.newHiredEmployeeCode = newHiredEmployeeCode;
	}
	/**
	 * @return the labelFlag
	 */
	public String getLabelFlag() {
		return labelFlag;
	}
	/**
	 * @param labelFlag the labelFlag to set
	 */
	public void setLabelFlag(String labelFlag) {
		this.labelFlag = labelFlag;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	

}
