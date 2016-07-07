package org.paradyne.bean.attendance;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;
/**
 * @author saipavan v
 * 29-05-2008
 */

public class OutdoorVisit extends BeanBase{
	
	private String source="";

	private String informCode="";
	private String informToken="";
	private String informName="";
	private String keepInformCode="";
	private ArrayList informList=null;
	private String keepHidden="";
	private String keepInform="";
	
	private String outvCode;
	private String ecode;
	private String empName;
	private String outDate;
	private String frTime;
	private String toTime;
	private String outLoc;
	private String purpose;
	private String status;
	private String hiddenStatus;
	private String eToken;
	private String empAppr;
	private String isApprove;
	private String apprflag ="false";
	
	private String apprName;
	private String apprDate;
	private String apprComments;
	private String applnStatus = "";
	ArrayList approveList;
	
	//List Page Variables Begins
	private String myPage = "";
	private String myPageInProcess = "";
	private String myPageSentBack = "";
	private String myPageApproved = "";
	private String myPageRejected = "";
	private String listType = "";
	
	ArrayList draftIteratorList = null;
	private boolean draftListLength = false;
	ArrayList inProcessIteratorList = null;
	private boolean inProcessListLength = false;
	ArrayList sentBackIteratorList = null;
	private boolean sentBackListLength = false;
	ArrayList approvedIteratorList = null;
	private boolean approvedListLength = false;
	ArrayList rejectedIteratorList = null;
	private boolean rejectedListLength =  false;
	
	private String visitHiddenID = "";
	private String visitHiddenStatus = "";
	private String employeeNumberIterator = "";
	private String employeeNameIterator = "";
	private String applicationDateIterator = "";
	//List Page Variables Begins
	
	boolean approverFlag = false;
	boolean approverCommentsFlag = false;
	private String approverComments = "";
	private String applicationLevel = "";
	private String checkApproveRejectStatus = "";
	boolean approverCommentsListFlag = false;
	
	//Application Approver List begins
	ArrayList applicationApproverIteratorList ;
	private String applicationApproverName = "";
	private String srNoIterator = "";
	//Application Approver List begins
	
	
	// Attendance Related Data Begins
	private String attDate = "";
	private String attShiftName = "";
	private String attInTime = "";
	private String attOutTime = "";
	private String attWorkingHours = "";
	private String attExtraHours = "";
	private String attLateHours = "";
	private String attEarlyHours = "";
	private String attStatusOne = "";
	private String attStatusTwo = "";	
	
	private boolean noDataAvailableAttendanceFlag =  false;
	private boolean dataAvailableAttendanceFlag =  false;
	// Attendance Related Data Ends
	
	public boolean isNoDataAvailableAttendanceFlag() {
		return noDataAvailableAttendanceFlag;
	}
	public void setNoDataAvailableAttendanceFlag(
			boolean noDataAvailableAttendanceFlag) {
		this.noDataAvailableAttendanceFlag = noDataAvailableAttendanceFlag;
	}
	public boolean isDataAvailableAttendanceFlag() {
		return dataAvailableAttendanceFlag;
	}
	public void setDataAvailableAttendanceFlag(boolean dataAvailableAttendanceFlag) {
		this.dataAvailableAttendanceFlag = dataAvailableAttendanceFlag;
	}
	public String getAttDate() {
		return attDate;
	}
	public void setAttDate(String attDate) {
		this.attDate = attDate;
	}
	public String getAttShiftName() {
		return attShiftName;
	}
	public void setAttShiftName(String attShiftName) {
		this.attShiftName = attShiftName;
	}
	public String getAttInTime() {
		return attInTime;
	}
	public void setAttInTime(String attInTime) {
		this.attInTime = attInTime;
	}
	public String getAttOutTime() {
		return attOutTime;
	}
	public void setAttOutTime(String attOutTime) {
		this.attOutTime = attOutTime;
	}
	public String getAttWorkingHours() {
		return attWorkingHours;
	}
	public void setAttWorkingHours(String attWorkingHours) {
		this.attWorkingHours = attWorkingHours;
	}
	public String getAttExtraHours() {
		return attExtraHours;
	}
	public void setAttExtraHours(String attExtraHours) {
		this.attExtraHours = attExtraHours;
	}
	public String getAttLateHours() {
		return attLateHours;
	}
	public void setAttLateHours(String attLateHours) {
		this.attLateHours = attLateHours;
	}
	public String getAttEarlyHours() {
		return attEarlyHours;
	}
	public void setAttEarlyHours(String attEarlyHours) {
		this.attEarlyHours = attEarlyHours;
	}
	public String getAttStatusOne() {
		return attStatusOne;
	}
	public void setAttStatusOne(String attStatusOne) {
		this.attStatusOne = attStatusOne;
	}
	public String getAttStatusTwo() {
		return attStatusTwo;
	}
	public void setAttStatusTwo(String attStatusTwo) {
		this.attStatusTwo = attStatusTwo;
	}
	public boolean isApproverCommentsListFlag() {
		return approverCommentsListFlag;
	}
	public void setApproverCommentsListFlag(boolean approverCommentsListFlag) {
		this.approverCommentsListFlag = approverCommentsListFlag;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	 
	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}
	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}
	public boolean isApproverFlag() {
		return approverFlag;
	}
	public void setApproverFlag(boolean approverFlag) {
		this.approverFlag = approverFlag;
	}
	public String getEmpAppr() {
		return empAppr;
	}
	public void setEmpAppr(String empAppr) {
		this.empAppr = empAppr;
	}
	public String getOutvCode() {
		return outvCode;
	}
	public void setOutvCode(String outvCode) {
		this.outvCode = outvCode;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getFrTime() {
		return frTime;
	}
	public void setFrTime(String frTime) {
		this.frTime = frTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getOutLoc() {
		return outLoc;
	}
	public void setOutLoc(String outLoc) {
		this.outLoc = outLoc;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
	public String getEToken() {
		return eToken;
	}
	public void setEToken(String token) {
		eToken = token;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
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
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
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
	public ArrayList getInformList() {
		return informList;
	}
	public void setInformList(ArrayList informList) {
		this.informList = informList;
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
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	
	public ArrayList getDraftIteratorList() {
		return draftIteratorList;
	}
	public void setDraftIteratorList(ArrayList draftIteratorList) {
		this.draftIteratorList = draftIteratorList;
	}
	public boolean isDraftListLength() {
		return draftListLength;
	}
	public void setDraftListLength(boolean draftListLength) {
		this.draftListLength = draftListLength;
	}
	public ArrayList getInProcessIteratorList() {
		return inProcessIteratorList;
	}
	public void setInProcessIteratorList(ArrayList inProcessIteratorList) {
		this.inProcessIteratorList = inProcessIteratorList;
	}
	public boolean isInProcessListLength() {
		return inProcessListLength;
	}
	public void setInProcessListLength(boolean inProcessListLength) {
		this.inProcessListLength = inProcessListLength;
	}
	public ArrayList getSentBackIteratorList() {
		return sentBackIteratorList;
	}
	public void setSentBackIteratorList(ArrayList sentBackIteratorList) {
		this.sentBackIteratorList = sentBackIteratorList;
	}
	public boolean isSentBackListLength() {
		return sentBackListLength;
	}
	public void setSentBackListLength(boolean sentBackListLength) {
		this.sentBackListLength = sentBackListLength;
	}
	public ArrayList getApprovedIteratorList() {
		return approvedIteratorList;
	}
	public void setApprovedIteratorList(ArrayList approvedIteratorList) {
		this.approvedIteratorList = approvedIteratorList;
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
	public String getVisitHiddenID() {
		return visitHiddenID;
	}
	public void setVisitHiddenID(String visitHiddenID) {
		this.visitHiddenID = visitHiddenID;
	}
	public String getVisitHiddenStatus() {
		return visitHiddenStatus;
	}
	public void setVisitHiddenStatus(String visitHiddenStatus) {
		this.visitHiddenStatus = visitHiddenStatus;
	}
	public String getEmployeeNumberIterator() {
		return employeeNumberIterator;
	}
	public void setEmployeeNumberIterator(String employeeNumberIterator) {
		this.employeeNumberIterator = employeeNumberIterator;
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
	public String getApplnStatus() {
		return applnStatus;
	}
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}
	public String getApplicationLevel() {
		return applicationLevel;
	}
	public void setApplicationLevel(String applicationLevel) {
		this.applicationLevel = applicationLevel;
	}
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}
	public ArrayList getApplicationApproverIteratorList() {
		return applicationApproverIteratorList;
	}
	public void setApplicationApproverIteratorList(
			ArrayList applicationApproverIteratorList) {
		this.applicationApproverIteratorList = applicationApproverIteratorList;
	}
	public String getApplicationApproverName() {
		return applicationApproverName;
	}
	public void setApplicationApproverName(String applicationApproverName) {
		this.applicationApproverName = applicationApproverName;
	}
	public String getSrNoIterator() {
		return srNoIterator;
	}
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
