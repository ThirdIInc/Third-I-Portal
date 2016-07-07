package org.paradyne.bean.conference;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Tarun Chaturvedi
 * Bean class for Conference Booking
 */
public class ConferenceBooking extends BeanBase {
	
	private String source ="";
	/* Keep informed to variables by Prashant*/
	private String informCode="";
	private String informToken="";
	private String informName="";
	private String keepInformCode="";
	private String otherEmail="";
	private ArrayList <ConferenceBooking>informList=null;
	private String keepHidden="";
	private String keepInform="";
	private String keepInformToCode ="";
	private String keepInformToName ="";
	private ArrayList <ConferenceBooking>keepInformedList = null ;
	/**
	 * Variables for Employee Information 
	 */
	private String empId;
	private String empToken;
	private String empName;
	private String branch;
	private String dept;
	private String desig;
	private String confAppDate;
	private String comments;
	/**
	 * Declaring bean properties
	 */
	private String confCode = "";
	private String requireDate = "";
	private String venue = "";
	private String venueCode = "";
	private String fromTime = "";
	private String toTime = "";
	private String bookBy = "";
	private String bookByCode = "";
	private String purpose = "";
	private String approverCode;
	private String accessoryCode = "";
	private String accessoryName = "";
	private String confChkFlag = "";
	private String chkFlag = "";
	private String quantity = "";
	private String bookByToken;
	private String status;
	private String hiddenStatus;
	private String level;
	private String isApprove = "false";
	private String isSentBack = "false";
	private String appCommentFlag = "false";
	private String approverName;   
	private String approvedDate;
	private String approvedStatus;
	private String approverComment;
	private ArrayList <ConferenceBooking>tableDetailList = null;
	private ArrayList <ConferenceBooking>commentList = null;
	private String noParticipant = "";
	private String maxParticipant="";
	private String minParticipant="";
	
	private ArrayList <ConferenceBooking>iteratorAccessorylist;
	private String totalRecords;
	private String listLengthApproved="false";//true if any approved records is there
	private String listLengthRejected="false";//true if any rejected records is there
	private String hiddencode;
	private String hdeleteCode;
	private String myPage;
	
	private String listType;//for activating tab,contains data as pending or submitted or rejected
	private ArrayList <ConferenceBooking>draftList;//to place draft table records
	private ArrayList <ConferenceBooking>submittedList;//to place Application in process records
	private ArrayList <ConferenceBooking>returnedList;// to place returned records
	private ArrayList <ConferenceBooking>approvedList;// to place approved tab records
	private ArrayList <ConferenceBooking>rejectedList;//to place rejected tab records

	/*Variable for Cancel Application*/
	private String cancelFlag ="false";
	private String appCancelFlg="false";
	
	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getListLengthApproved() {
		return listLengthApproved;
	}

	public void setListLengthApproved(String listLengthApproved) {
		this.listLengthApproved = listLengthApproved;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getNoParticipant() {
		return noParticipant;
	}

	public void setNoParticipant(String noParticipant) {
		this.noParticipant = noParticipant;
	}
	/**
	 * getter/setter methods for all bean properties
	 */
	public String getConfCode() {
		return confCode;
	}
	
	public void setConfCode(String confCode) {
		this.confCode = confCode;
	}
	
	public String getRequireDate() {
		return requireDate;
	}
	
	public void setRequireDate(String requireDate) {
		this.requireDate = requireDate;
	}
	
	public String getVenue() {
		return venue;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getFromTime() {
		return fromTime;
	}
	
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	
	public String getBookBy() {
		return bookBy;
	}
	
	public void setBookBy(String bookBy) {
		this.bookBy = bookBy;
	}
	
	public String getPurpose() {
		return purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getAccessoryCode() {
		return accessoryCode;
	}
	
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}
	
	public String getAccessoryName() {
		return accessoryName;
	}
	
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getBookByCode() {
		return bookByCode;
	}
	
	public void setBookByCode(String bookByCode) {
		this.bookByCode = bookByCode;
	}
	
	public String getVenueCode() {
		return venueCode;
	}
	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}
	
	public String getConfChkFlag() {
		return confChkFlag;
	}
	
	public void setConfChkFlag(String confChkFlag) {
		this.confChkFlag = confChkFlag;
	}
	
	public String getChkFlag() {
		return chkFlag;
	}
	
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	/**
	 * @return the bookByToken
	 */
	public String getBookByToken() {
		return bookByToken;
	}
	/**
	 * @param bookByToken the bookByToken to set
	 */
	public void setBookByToken(String bookByToken) {
		this.bookByToken = bookByToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getAppCommentFlag() {
		return appCommentFlag;
	}

	public void setAppCommentFlag(String appCommentFlag) {
		this.appCommentFlag = appCommentFlag;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getApproverComment() {
		return approverComment;
	}

	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
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

	public String getMaxParticipant() {
		return maxParticipant;
	}

	public void setMaxParticipant(String maxParticipant) {
		this.maxParticipant = maxParticipant;
	}

	public String getMinParticipant() {
		return minParticipant;
	}

	public void setMinParticipant(String minParticipant) {
		this.minParticipant = minParticipant;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getConfAppDate() {
		return confAppDate;
	}

	public void setConfAppDate(String confAppDate) {
		this.confAppDate = confAppDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getListLengthRejected() {
		return listLengthRejected;
	}

	public void setListLengthRejected(String listLengthRejected) {
		this.listLengthRejected = listLengthRejected;
	}

	public String getIsSentBack() {
		return isSentBack;
	}

	public void setIsSentBack(String isSentBack) {
		this.isSentBack = isSentBack;
	}

	public String getInformCode() {
		return informCode;
	}

	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}

	public String getInformToken() {
		return informToken;
	}

	public void setInformToken(String informToken) {
		this.informToken = informToken;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}

	public String getKeepInformCode() {
		return keepInformCode;
	}

	public void setKeepInformCode(String keepInformCode) {
		this.keepInformCode = keepInformCode;
	}
	public String getKeepHidden() {
		return keepHidden;
	}

	public void setKeepHidden(String keepHidden) {
		this.keepHidden = keepHidden;
	}

	public String getKeepInform() {
		return keepInform;
	}

	public void setKeepInform(String keepInform) {
		this.keepInform = keepInform;
	}
	public String getKeepInformToCode() {
		return keepInformToCode;
	}

	public void setKeepInformToCode(String keepInformToCode) {
		this.keepInformToCode = keepInformToCode;
	}

	public String getKeepInformToName() {
		return keepInformToName;
	}

	public void setKeepInformToName(String keepInformToName) {
		this.keepInformToName = keepInformToName;
	}

	public String getOtherEmail() {
		return otherEmail;
	}

	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList<ConferenceBooking> getInformList() {
		return informList;
	}

	public void setInformList(ArrayList<ConferenceBooking> informList) {
		this.informList = informList;
	}

	public ArrayList<ConferenceBooking> getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList<ConferenceBooking> keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public ArrayList<ConferenceBooking> getTableDetailList() {
		return tableDetailList;
	}

	public void setTableDetailList(ArrayList<ConferenceBooking> tableDetailList) {
		this.tableDetailList = tableDetailList;
	}

	public ArrayList<ConferenceBooking> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<ConferenceBooking> commentList) {
		this.commentList = commentList;
	}

	public ArrayList<ConferenceBooking> getIteratorAccessorylist() {
		return iteratorAccessorylist;
	}

	public void setIteratorAccessorylist(
			ArrayList<ConferenceBooking> iteratorAccessorylist) {
		this.iteratorAccessorylist = iteratorAccessorylist;
	}

	public ArrayList<ConferenceBooking> getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList<ConferenceBooking> draftList) {
		this.draftList = draftList;
	}

	public ArrayList<ConferenceBooking> getSubmittedList() {
		return submittedList;
	}

	public void setSubmittedList(ArrayList<ConferenceBooking> submittedList) {
		this.submittedList = submittedList;
	}

	public ArrayList<ConferenceBooking> getReturnedList() {
		return returnedList;
	}

	public void setReturnedList(ArrayList<ConferenceBooking> returnedList) {
		this.returnedList = returnedList;
	}

	public ArrayList<ConferenceBooking> getApprovedList() {
		return approvedList;
	}

	public void setApprovedList(ArrayList<ConferenceBooking> approvedList) {
		this.approvedList = approvedList;
	}

	public ArrayList<ConferenceBooking> getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(ArrayList<ConferenceBooking> rejectedList) {
		this.rejectedList = rejectedList;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
	public String getAppCancelFlg() {
		return appCancelFlg;
	}

	public void setAppCancelFlg(String appCancelFlg) {
		this.appCancelFlg = appCancelFlg;
	}

}
