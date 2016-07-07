package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class DepartmentandLocationChangeBean extends BeanBase {
    private boolean draftFlag;
	private String managerToken="";
    private String initiator="";
	private String completedDate="";
	private String completedBy="";
	private String completedByCode="";
	private String authorizedToken="";	
	private String completedByToken="";
//Second Approver
	private String secondApproverToken="";
	private String secondApproverName="";
	private String secondApproverId="";
	private boolean secondAppFlag;
	
private String level="";	
private boolean forApproval;
private boolean forFlagComment;
private String comment="";
private String applnActualStatus="";
private String persDataChangeId;
private String approverComments;
private boolean readOnlyDetails;
private String radioYNValue;
private String applnStatus = "";
public String empNum="";
public String empName="";	
public String fname="";	
public String mname="";	
public String lname="";	
public String executive="";	
public String region="";	
public String area="";	
public String deptNum="";	
public String effectivedateofChange="";	
public String from_department_Num="";	
public String to_department_Num="";	
public String from_workPhone="";	
public String to_workPhone="";	
public String from_managerName="";	
public String to_managerName="";	
public String depAppDate="";


private String approverCode="";
private String approverToken= "";
private String approverName="";
private String firstApproverCode="";
private String selectapproverName="";
public String deptNum1="";

public String deptCode=""; 
public String to_managerID="";
public String to_departmentID="";
public String cityId="";
public String empId="";
public String appId="";

public String physicaLocation="";
public String address1="";
public String address2="";
public String address3="";

public String city="";
public String state="";
public String country="";
public String phone1="";
public String phone2="";


public String workHome="";
public String radioValue="";	
public String hdeleteCode="";
private String myPage  = "" ;
private String modeLength  = "" ;
private String totalNoOfRecords  = "" ;
private ArrayList deptchangeLocationList=null;



public String draftID="";
public String  pendingCode="";
public String approveCode="";
public String rejectCode="";

public String appToken="";

//For Pagination
private boolean pagingForPendingApp;
private boolean pagingForPendingCancelApp;
private boolean pagingForApprovedApp;
private boolean pagingForRejectedApp;

private String pageForPendingApp;
private String pageForPendingCancelApp;
private String pageForApprovedApp;
private String pageForRejectedApp;

//FILDS FOR APPROVER LIST
private String srNoIterator = "";
private ArrayList  approverList=null;
public String approName ="";
public String firstName ="";

public String listType="";
private ArrayList cancelledList;
private ArrayList draftList=null;
private ArrayList  pedingList=null;
 public String status="";

private String myPageReturn="";
private String myPageApproved="";
private String myPageApprovedList="";
private String myPageApprovedCancelList="";
private String myPageReject="";
private String myPageRejected="";
private String myPageCancelled="";
private String myPageCancelRejected="";
private String myPageCancelApproved="";
private String myPageRejectCancel="";
private String myPageCancel="";
public String hiddenStatus1="";
public String hiddenStatus2="";
public String hiddenStatus3="";
public String hiddenStatus4="";
public String hiddenStatus5="";


private ArrayList submitList=null;
private ArrayList cancelledApprovedList=null;
private ArrayList rejectedList=null;
private ArrayList approveCancelrejectList=null;
private ArrayList returnList=null;
private ArrayList approvedList=null;

private ArrayList approvedCancelList=null;
private ArrayList approvedRejectList=null;
private ArrayList cancelList=null;
public String pendingAppList;

//Added by janisha for Approver comments
private List<Object> approverCommentList;
private String apprName;
private String apprComments;
private String apprDate;
private String apprStatus;

//added ganesh
private String deptCodeSelect = "";

private boolean draftVoucherListLength = false;
private boolean inProcessVoucherListLength = false;
private boolean sentBackVoucherListLength = false;
private boolean approvedVoucherListLength = false;
private boolean rejectedVoucherListLength = false;

public boolean isRejectedVoucherListLength() {
	return rejectedVoucherListLength;
}
public void setRejectedVoucherListLength(boolean rejectedVoucherListLength) {
	this.rejectedVoucherListLength = rejectedVoucherListLength;
}
public boolean isApprovedVoucherListLength() {
	return approvedVoucherListLength;
}
public void setApprovedVoucherListLength(boolean approvedVoucherListLength) {
	this.approvedVoucherListLength = approvedVoucherListLength;
}
public boolean isSentBackVoucherListLength() {
	return sentBackVoucherListLength;
}
public void setSentBackVoucherListLength(boolean sentBackVoucherListLength) {
	this.sentBackVoucherListLength = sentBackVoucherListLength;
}
public boolean isInProcessVoucherListLength() {
	return inProcessVoucherListLength;
}
public void setInProcessVoucherListLength(boolean inProcessVoucherListLength) {
	this.inProcessVoucherListLength = inProcessVoucherListLength;
}
public boolean isDraftVoucherListLength() {
	return draftVoucherListLength;
}
public void setDraftVoucherListLength(boolean draftVoucherListLength) {
	this.draftVoucherListLength = draftVoucherListLength;
}
public String getDeptCodeSelect() {
	return deptCodeSelect;
}
public void setDeptCodeSelect(String deptCodeSelect) {
	this.deptCodeSelect = deptCodeSelect;
}
public List<Object> getApproverCommentList() {
	return approverCommentList;
}
public void setApproverCommentList(List<Object> approverCommentList) {
	this.approverCommentList = approverCommentList;
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
/**
 * @return the pendingAppList
 */
public String getPendingAppList() {
	return pendingAppList;
}
/**
 * @param pendingAppList the pendingAppList to set
 */
public void setPendingAppList(String pendingAppList) {
	this.pendingAppList = pendingAppList;
}
/**
 * @return the hiddenStatus5
 */
public String getHiddenStatus5() {
	return hiddenStatus5;
}
/**
 * @param hiddenStatus5 the hiddenStatus5 to set
 */
public void setHiddenStatus5(String hiddenStatus5) {
	this.hiddenStatus5 = hiddenStatus5;
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
 * @return the myPageCancelled
 */
public String getMyPageCancelled() {
	return myPageCancelled;
}
/**
 * @param myPageCancelled the myPageCancelled to set
 */
public void setMyPageCancelled(String myPageCancelled) {
	this.myPageCancelled = myPageCancelled;
}

/**
 * @return the empNum
 */
public String getEmpNum() {
	return empNum;
}
/**
 * @param empNum the empNum to set
 */
public void setEmpNum(String empNum) {
	this.empNum = empNum;
}
/**
 * @return the fname
 */
public String getFname() {
	return fname;
}
/**
 * @param fname the fname to set
 */
public void setFname(String fname) {
	this.fname = fname;
}
/**
 * @return the mname
 */
public String getMname() {
	return mname;
}
/**
 * @param mname the mname to set
 */
public void setMname(String mname) {
	this.mname = mname;
}
/**
 * @return the lname
 */
public String getLname() {
	return lname;
}
/**
 * @param lname the lname to set
 */
public void setLname(String lname) {
	this.lname = lname;
}
/**
 * @return the executive
 */
public String getExecutive() {
	return executive;
}
/**
 * @param executive the executive to set
 */
public void setExecutive(String executive) {
	this.executive = executive;
}
/**
 * @return the region
 */
public String getRegion() {
	return region;
}
/**
 * @param region the region to set
 */
public void setRegion(String region) {
	this.region = region;
}
/**
 * @return the area
 */
public String getArea() {
	return area;
}
/**
 * @param area the area to set
 */
public void setArea(String area) {
	this.area = area;
}
/**
 * @return the deptNum
 */
public String getDeptNum() {
	return deptNum;
}
/**
 * @param deptNum the deptNum to set
 */
public void setDeptNum(String deptNum) {
	this.deptNum = deptNum;
}
/**
 * @return the effectivedateofChange
 */
public String getEffectivedateofChange() {
	return effectivedateofChange;
}
/**
 * @param effectivedateofChange the effectivedateofChange to set
 */
public void setEffectivedateofChange(String effectivedateofChange) {
	this.effectivedateofChange = effectivedateofChange;
}




/**
 * @return the from_department_Num
 */
public String getFrom_department_Num() {
	return from_department_Num;
}
/**
 * @param from_department_Num the from_department_Num to set
 */
public void setFrom_department_Num(String from_department_Num) {
	this.from_department_Num = from_department_Num;
}
/**
 * @return the to_department_Num
 */
public String getTo_department_Num() {
	return to_department_Num;
}
/**
 * @param to_department_Num the to_department_Num to set
 */
public void setTo_department_Num(String to_department_Num) {
	this.to_department_Num = to_department_Num;
}
/**
 * @return the from_workPhone
 */
public String getFrom_workPhone() {
	return from_workPhone;
}
/**
 * @param from_workPhone the from_workPhone to set
 */
public void setFrom_workPhone(String from_workPhone) {
	this.from_workPhone = from_workPhone;
}
/**
 * @return the to_workPhone
 */
public String getTo_workPhone() {
	return to_workPhone;
}
/**
 * @param to_workPhone the to_workPhone to set
 */
public void setTo_workPhone(String to_workPhone) {
	this.to_workPhone = to_workPhone;
}

/**
 * @return the from_managerName
 */
public String getFrom_managerName() {
	return from_managerName;
}
/**
 * @param from_managerName the from_managerName to set
 */
public void setFrom_managerName(String from_managerName) {
	this.from_managerName = from_managerName;
}
/**
 * @return the to_managerName
 */
public String getTo_managerName() {
	return to_managerName;
}
/**
 * @param to_managerName the to_managerName to set
 */
public void setTo_managerName(String to_managerName) {
	this.to_managerName = to_managerName;
}

/**
 * @return the approName
 */
public String getApproName() {
	return approName;
}
/**
 * @param approName the approName to set
 */
public void setApproName(String approName) {
	this.approName = approName;
}
/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}
/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
/**
 * @return the hdeleteCode
 */
public String getHdeleteCode() {
	return hdeleteCode;
}
/**
 * @param hdeleteCode the hdeleteCode to set
 */
public void setHdeleteCode(String hdeleteCode) {
	this.hdeleteCode = hdeleteCode;
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
 * @return the modeLength
 */
public String getModeLength() {
	return modeLength;
}
/**
 * @param modeLength the modeLength to set
 */
public void setModeLength(String modeLength) {
	this.modeLength = modeLength;
}
/**
 * @return the totalNoOfRecords
 */
public String getTotalNoOfRecords() {
	return totalNoOfRecords;
}
/**
 * @param totalNoOfRecords the totalNoOfRecords to set
 */
public void setTotalNoOfRecords(String totalNoOfRecords) {
	this.totalNoOfRecords = totalNoOfRecords;
}
/**
 * @return the deptchangeLocationList
 */
public ArrayList getDeptchangeLocationList() {
	return deptchangeLocationList;
}
/**
 * @param deptchangeLocationList the deptchangeLocationList to set
 */
public void setDeptchangeLocationList(ArrayList deptchangeLocationList) {
	this.deptchangeLocationList = deptchangeLocationList;
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
 * @return the address1
 */
public String getAddress1() {
	return address1;
}
/**
 * @param address1 the address1 to set
 */
public void setAddress1(String address1) {
	this.address1 = address1;
}
/**
 * @return the address2
 */
public String getAddress2() {
	return address2;
}
/**
 * @param address2 the address2 to set
 */
public void setAddress2(String address2) {
	this.address2 = address2;
}
/**
 * @return the address3
 */
public String getAddress3() {
	return address3;
}
/**
 * @param address3 the address3 to set
 */
public void setAddress3(String address3) {
	this.address3 = address3;
}
/**
 * @return the city
 */
public String getCity() {
	return city;
}
/**
 * @param city the city to set
 */
public void setCity(String city) {
	this.city = city;
}
/**
 * @return the state
 */
public String getState() {
	return state;
}
/**
 * @param state the state to set
 */
public void setState(String state) {
	this.state = state;
}
/**
 * @return the country
 */
public String getCountry() {
	return country;
}
/**
 * @param country the country to set
 */
public void setCountry(String country) {
	this.country = country;
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
 * @return the phone1
 */
public String getPhone1() {
	return phone1;
}
/**
 * @param phone1 the phone1 to set
 */
public void setPhone1(String phone1) {
	this.phone1 = phone1;
}
/**
 * @return the phone2
 */
public String getPhone2() {
	return phone2;
}
/**
 * @param phone2 the phone2 to set
 */
public void setPhone2(String phone2) {
	this.phone2 = phone2;
}
/**
 * @return the deptNum1
 */
public String getDeptNum1() {
	return deptNum1;
}
/**
 * @param deptNum1 the deptNum1 to set
 */
public void setDeptNum1(String deptNum1) {
	this.deptNum1 = deptNum1;
}
/**
 * @return the to_managerID
 */
public String getTo_managerID() {
	return to_managerID;
}
/**
 * @param to_managerID the to_managerID to set
 */
public void setTo_managerID(String to_managerID) {
	this.to_managerID = to_managerID;
}
/**
 * @return the to_departmentID
 */
public String getTo_departmentID() {
	return to_departmentID;
}
/**
 * @param to_departmentID the to_departmentID to set
 */
public void setTo_departmentID(String to_departmentID) {
	this.to_departmentID = to_departmentID;
}
/**
 * @return the physicaLocation
 */
public String getPhysicaLocation() {
	return physicaLocation;
}
/**
 * @param physicaLocation the physicaLocation to set
 */
public void setPhysicaLocation(String physicaLocation) {
	this.physicaLocation = physicaLocation;
}
/**
 * @return the workHome
 */
public String getWorkHome() {
	return workHome;
}
/**
 * @param workHome the workHome to set
 */
public void setWorkHome(String workHome) {
	this.workHome = workHome;
}
/**
 * @return the cityId
 */
public String getCityId() {
	return cityId;
}
/**
 * @param cityId the cityId to set
 */
public void setCityId(String cityId) {
	this.cityId = cityId;
}
/**
 * @return the empId
 */
public String getEmpId() {
	return empId;
}
/**
 * @param empId the empId to set
 */
public void setEmpId(String empId) {
	this.empId = empId;
}
/**
 * @return the appId
 */
public String getAppId() {
	return appId;
}
/**
 * @param appId the appId to set
 */
public void setAppId(String appId) {
	this.appId = appId;
}
/**
 * @return the approverList
 */
public ArrayList getApproverList() {
	return approverList;
}
/**
 * @param approverList the approverList to set
 */
public void setApproverList(ArrayList approverList) {
	this.approverList = approverList;
}
/**
 * @return the srNoIterator
 */
public String getSrNoIterator() {
	return srNoIterator;
}
/**
 * @param srNoIterator the srNoIterator to set
 */
public void setSrNoIterator(String srNoIterator) {
	this.srNoIterator = srNoIterator;
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
 * @return the cancelledList
 */
public ArrayList getCancelledList() {
	return cancelledList;
}
/**
 * @param cancelledList the cancelledList to set
 */
public void setCancelledList(ArrayList cancelledList) {
	this.cancelledList = cancelledList;
}
/**
 * @return the hiddenStatus1
 */
public String getHiddenStatus1() {
	return hiddenStatus1;
}
/**
 * @param hiddenStatus1 the hiddenStatus1 to set
 */
public void setHiddenStatus1(String hiddenStatus1) {
	this.hiddenStatus1 = hiddenStatus1;
}
/**
 * @return the hiddenStatus2
 */
public String getHiddenStatus2() {
	return hiddenStatus2;
}
/**
 * @param hiddenStatus2 the hiddenStatus2 to set
 */
public void setHiddenStatus2(String hiddenStatus2) {
	this.hiddenStatus2 = hiddenStatus2;
}
/**
 * @return the hiddenStatus3
 */
public String getHiddenStatus3() {
	return hiddenStatus3;
}
/**
 * @param hiddenStatus3 the hiddenStatus3 to set
 */
public void setHiddenStatus3(String hiddenStatus3) {
	this.hiddenStatus3 = hiddenStatus3;
}
/**
 * @return the hiddenStatus4
 */
public String getHiddenStatus4() {
	return hiddenStatus4;
}
/**
 * @param hiddenStatus4 the hiddenStatus4 to set
 */
public void setHiddenStatus4(String hiddenStatus4) {
	this.hiddenStatus4 = hiddenStatus4;
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
 * @return the submitList
 */
public ArrayList getSubmitList() {
	return submitList;
}
/**
 * @param submitList the submitList to set
 */
public void setSubmitList(ArrayList submitList) {
	this.submitList = submitList;
}
/**
 * @return the cancelledApprovedList
 */
public ArrayList getCancelledApprovedList() {
	return cancelledApprovedList;
}
/**
 * @param cancelledApprovedList the cancelledApprovedList to set
 */
public void setCancelledApprovedList(ArrayList cancelledApprovedList) {
	this.cancelledApprovedList = cancelledApprovedList;
}
/**
 * @return the rejectedList
 */
public ArrayList getRejectedList() {
	return rejectedList;
}
/**
 * @param rejectedList the rejectedList to set
 */
public void setRejectedList(ArrayList rejectedList) {
	this.rejectedList = rejectedList;
}
/**
 * @return the approveCancelrejectList
 */
public ArrayList getApproveCancelrejectList() {
	return approveCancelrejectList;
}
/**
 * @param approveCancelrejectList the approveCancelrejectList to set
 */
public void setApproveCancelrejectList(ArrayList approveCancelrejectList) {
	this.approveCancelrejectList = approveCancelrejectList;
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
 * @return the myPageCancelApproved
 */
public String getMyPageCancelApproved() {
	return myPageCancelApproved;
}
/**
 * @param myPageCancelApproved the myPageCancelApproved to set
 */
public void setMyPageCancelApproved(String myPageCancelApproved) {
	this.myPageCancelApproved = myPageCancelApproved;
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
 * @return the selectapproverName
 */
public String getSelectapproverName() {
	return selectapproverName;
}
/**
 * @param selectapproverName the selectapproverName to set
 */
public void setSelectapproverName(String selectapproverName) {
	this.selectapproverName = selectapproverName;
}
/**
 * @return the returnList
 */
public ArrayList getReturnList() {
	return returnList;
}
/**
 * @param returnList the returnList to set
 */
public void setReturnList(ArrayList returnList) {
	this.returnList = returnList;
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
 * @return the depAppDate
 */
public String getDepAppDate() {
	return depAppDate;
}
/**
 * @param depAppDate the depAppDate to set
 */
public void setDepAppDate(String depAppDate) {
	this.depAppDate = depAppDate;
}
/**
 * @return the pedingList
 */
public ArrayList getPedingList() {
	return pedingList;
}
/**
 * @param pedingList the pedingList to set
 */
public void setPedingList(ArrayList pedingList) {
	this.pedingList = pedingList;
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
 * @return the draftID
 */
public String getDraftID() {
	return draftID;
}
/**
 * @param draftID the draftID to set
 */
public void setDraftID(String draftID) {
	this.draftID = draftID;
}
/**
 * @return the pendingCode
 */
public String getPendingCode() {
	return pendingCode;
}
/**
 * @param pendingCode the pendingCode to set
 */
public void setPendingCode(String pendingCode) {
	this.pendingCode = pendingCode;
}
/**
 * @return the approveCode
 */
public String getApproveCode() {
	return approveCode;
}
/**
 * @param approveCode the approveCode to set
 */
public void setApproveCode(String approveCode) {
	this.approveCode = approveCode;
}
/**
 * @return the rejectCode
 */
public String getRejectCode() {
	return rejectCode;
}
/**
 * @param rejectCode the rejectCode to set
 */
public void setRejectCode(String rejectCode) {
	this.rejectCode = rejectCode;
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
 * @return the persDataChangeId
 */
public String getPersDataChangeId() {
	return persDataChangeId;
}
/**
 * @param persDataChangeId the persDataChangeId to set
 */
public void setPersDataChangeId(String persDataChangeId) {
	this.persDataChangeId = persDataChangeId;
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
 * @return the radioYNValue
 */
public String getRadioYNValue() {
	return radioYNValue;
}
/**
 * @param radioYNValue the radioYNValue to set
 */
public void setRadioYNValue(String radioYNValue) {
	this.radioYNValue = radioYNValue;
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
 * @return the appToken
 */
public String getAppToken() {
	return appToken;
}
/**
 * @param appToken the appToken to set
 */
public void setAppToken(String appToken) {
	this.appToken = appToken;
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
 * @return the approvedRejectList
 */
public ArrayList getApprovedRejectList() {
	return approvedRejectList;
}
/**
 * @param approvedRejectList the approvedRejectList to set
 */
public void setApprovedRejectList(ArrayList approvedRejectList) {
	this.approvedRejectList = approvedRejectList;
}
/**
 * @return the cancelList
 */
public ArrayList getCancelList() {
	return cancelList;
}
/**
 * @param cancelList the cancelList to set
 */
public void setCancelList(ArrayList cancelList) {
	this.cancelList = cancelList;
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
 * @return the pagingForPendingApp
 */
public boolean isPagingForPendingApp() {
	return pagingForPendingApp;
}
/**
 * @param pagingForPendingApp the pagingForPendingApp to set
 */
public void setPagingForPendingApp(boolean pagingForPendingApp) {
	this.pagingForPendingApp = pagingForPendingApp;
}
/**
 * @return the pagingForPendingCancelApp
 */
public boolean isPagingForPendingCancelApp() {
	return pagingForPendingCancelApp;
}
/**
 * @param pagingForPendingCancelApp the pagingForPendingCancelApp to set
 */
public void setPagingForPendingCancelApp(boolean pagingForPendingCancelApp) {
	this.pagingForPendingCancelApp = pagingForPendingCancelApp;
}
/**
 * @return the pagingForApprovedApp
 */
public boolean isPagingForApprovedApp() {
	return pagingForApprovedApp;
}
/**
 * @param pagingForApprovedApp the pagingForApprovedApp to set
 */
public void setPagingForApprovedApp(boolean pagingForApprovedApp) {
	this.pagingForApprovedApp = pagingForApprovedApp;
}
/**
 * @return the pagingForRejectedApp
 */
public boolean isPagingForRejectedApp() {
	return pagingForRejectedApp;
}
/**
 * @param pagingForRejectedApp the pagingForRejectedApp to set
 */
public void setPagingForRejectedApp(boolean pagingForRejectedApp) {
	this.pagingForRejectedApp = pagingForRejectedApp;
}
/**
 * @return the pageForPendingApp
 */
public String getPageForPendingApp() {
	return pageForPendingApp;
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
	return pageForPendingCancelApp;
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
	return pageForApprovedApp;
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
	return pageForRejectedApp;
}
/**
 * @param pageForRejectedApp the pageForRejectedApp to set
 */
public void setPageForRejectedApp(String pageForRejectedApp) {
	this.pageForRejectedApp = pageForRejectedApp;
}
/**
 * @return the myPageReturn
 */
public String getMyPageReturn() {
	return myPageReturn;
}
/**
 * @param myPageReturn the myPageReturn to set
 */
public void setMyPageReturn(String myPageReturn) {
	this.myPageReturn = myPageReturn;
}
/**
 * @return the myPageApprovedList
 */
public String getMyPageApprovedList() {
	return myPageApprovedList;
}
/**
 * @param myPageApprovedList the myPageApprovedList to set
 */
public void setMyPageApprovedList(String myPageApprovedList) {
	this.myPageApprovedList = myPageApprovedList;
}
/**
 * @return the myPageApprovedCancelList
 */
public String getMyPageApprovedCancelList() {
	return myPageApprovedCancelList;
}
/**
 * @param myPageApprovedCancelList the myPageApprovedCancelList to set
 */
public void setMyPageApprovedCancelList(String myPageApprovedCancelList) {
	this.myPageApprovedCancelList = myPageApprovedCancelList;
}
/**
 * @return the myPageReject
 */
public String getMyPageReject() {
	return myPageReject;
}
/**
 * @param myPageReject the myPageReject to set
 */
public void setMyPageReject(String myPageReject) {
	this.myPageReject = myPageReject;
}
/**
 * @return the myPageRejectCancel
 */
public String getMyPageRejectCancel() {
	return myPageRejectCancel;
}
/**
 * @param myPageRejectCancel the myPageRejectCancel to set
 */
public void setMyPageRejectCancel(String myPageRejectCancel) {
	this.myPageRejectCancel = myPageRejectCancel;
}
/**
 * @return the myPageCancel
 */
public String getMyPageCancel() {
	return myPageCancel;
}
/**
 * @param myPageCancel the myPageCancel to set
 */
public void setMyPageCancel(String myPageCancel) {
	this.myPageCancel = myPageCancel;
}
public String getApplnActualStatus() {
	return applnActualStatus;
}
public void setApplnActualStatus(String applnActualStatus) {
	this.applnActualStatus = applnActualStatus;
}
public boolean isForFlagComment() {
	return forFlagComment;
}
public void setForFlagComment(boolean forFlagComment) {
	this.forFlagComment = forFlagComment;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getSecondApproverToken() {
	return secondApproverToken;
}
public void setSecondApproverToken(String secondApproverToken) {
	this.secondApproverToken = secondApproverToken;
}
public String getSecondApproverName() {
	return secondApproverName;
}
public void setSecondApproverName(String secondApproverName) {
	this.secondApproverName = secondApproverName;
}
public String getSecondApproverId() {
	return secondApproverId;
}
public void setSecondApproverId(String secondApproverId) {
	this.secondApproverId = secondApproverId;
}
public boolean isSecondAppFlag() {
	return secondAppFlag;
}
public void setSecondAppFlag(boolean secondAppFlag) {
	this.secondAppFlag = secondAppFlag;
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
 * @return the completedByToken
 */
public String getCompletedByToken() {
	return completedByToken;
}
/**
 * @param completedByToken the completedByToken to set
 */
public void setCompletedByToken(String completedByToken) {
	this.completedByToken = completedByToken;
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
/**
 * @return the managerToken
 */
public String getManagerToken() {
	return managerToken;
}
/**
 * @param managerToken the managerToken to set
 */
public void setManagerToken(String managerToken) {
	this.managerToken = managerToken;
}



}
