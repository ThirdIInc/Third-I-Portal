/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelClaim;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0563
 *
 */
public class AdvCaimDisb extends BeanBase {
	
	private String source="";
	private String bankIFSCCode = "";
	private String bankBranch = "";
	private String bankBSRCode = "";
	private String applApprvdAdvAmtCurrency = "";
	private String totalCurrencyExpense = "";
	
	private boolean showApproverComments =false;
	private String defEmpId = "";
	private String defApplId = "";
	private String defTrvlId = "";
	private String defApplDate = "";
	private String defAdvAmt = "";
	private String settlDate = "";
	private String defEmpName = "";
	private String defEmpGrade = "";
	private String defStatus = "";
	private String divisionCode = "";
	private String divisionName = "";
	private String defDivision = "";
	private String pendingListDivision = "";
	private String pendingClaimListDivision = "";
	private String closedDefDivision = "";
	private String closedAdvDisbDivision = "";
	ArrayList<Object> defaulterList;
	ArrayList<Object> closedDefaulterList;
	
	private String defaulterEmpName = "";
	private String deafulterEmpId = "";
	private String defTravelstartdate = "";
	private String defBranchName = "";
	private String defTravelEnddate = "";
	private String defDepartmentName = "";
	private String defGradeName = "";
	private String defGradeID = "";
	private String defDesignation = "";
	private String defaulterApplDate = "";
	private String defaulterApplId = "";
	private String defaulterTrvlId = "";
	private String defMonth = "";
	private String defYear = "";
	private String debitCode = "";
	private String debitName = "";
	
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";
	private String zeroflag="false";
	// for new paging

	private String claimStatus = "";
	private boolean pageFlag = false;
	private String claimRowId="";

	// control flags
	private boolean noClaimData = false;
	private String pen = "";
	private String clsd = "";
	//private String trvlId="";
	private String claimtrvlId="";

	// for iterator
	ArrayList clmDisbrList = new ArrayList();
	private boolean acceptButton = false;
	private String displayClaimStatus = "";
	private String itEmpName = "";
	private String itclmType = "";
	private String itAppDate = "";
	private String itClmAmt = "";
	private String itClmAppId="";
	private String itClmStatus="";
	private String tranferFlag="false";
	private String chequeFlag="false";
	private String disburseCode="";
	private String paraId="";
	private String trvlAppId="";
	private String trvlAppCode="";
	private String appId="";
	private String appCode="";
	// for navigation purpose

	private String tmsClmAppId = "";
	private String tmsClmStatus = "";
	private String editButton="false";
	private String currentStatus="";
	
	private String applApprvdAdvAmt="";
	
	// for combo box
	TreeMap payModeMap;
	private String gradeID="";
	
	private String month = "";
	private String year = "";
	private String monthAdv = "";
	private String yearAdv = "";
	private String monthName = "";
	
	private String myPage = "";
	private boolean advRecordsAvailable = false;
	private int totalAdvRecords;
	
	private String myClaimPage = "";
	private boolean claimRecordsAvailable = false;
	private int totalClaimRecords;
	
	private String showRevokeStatus="false";
	private String applStatus = "";
	
	
	
	private boolean noData=false;
	ArrayList pendingList;
	private String descCnt1="";
	private String empId="";
	private String empName="";
	private String listType = "";
	private String advanceType="";
	private String applicationDate="";
	private String advReqDate="";
	private String advAmount="";
	private String hidstatus="";
	private String rownum;
	private String settingflag="true";
	private String trvlId;
	private String pendingtrvlId;
	private String guestflag;//for identifing which one guest or  self
	private String hiddengustflag;
	/**
	 * @return the pendingList
	 */
	 private String applId="";
	 private String applCode="";
	//fields for pendingAdvDisb.jsp
	
	
	private String pendingEmpName="";
	private String pendingEmpId="";
	private String trvlStrtDate="";
	private String branchName="";
	private String travelEnddate="";
	private String departmentName="";
	private String gradeName="";
	private String  desig="";
	private String  pendingadvAmount="";
	private String pendingApplicationDate="";
	private String hiddenAdvPaid="";
	private String hiddenBalanceAmt="";
	private String pendingapplId="";
	private String pendingapplCode="";
	private String pendingempcode="";
	private String rowId="";
	private String bankname="";
	//for setting in list
	private String itpaymentdate;
	private String itpaymentmode;
	private String itamount;
	private String hdeleteSkill;
	private String itcomment;
	private String status;
	private String chkno;
	private String checkdate;
	private String accountno;
	ArrayList payList;
	//hidden field
	private String hiddencode;//hidden field for double click store id in that field
	private String hiddenappldate;//hidden field for( double click) application date in advclmdisb.jsp
	
	
	
	
	//for closed advance disbursement list
	
	private String closedempId="";
	private String closedempName="";
	private String closedadvanceType="";
	private String closedapplicationDate="";
	private String closedadvReqDate="";
	private String closedadvAmount="";
	 private String closedapplId="";
	private String closedapplCode="";
	private String closedhidstatus="";
	private String closeditcheckno="";
	private String closeditcheckdate="";
	private String closedaccountnumber="";
	private String closedbankname="";
	private String closedtrvlId="";
	private String TravelcloseId="";
	ArrayList closedList;
	//for closed but in edit to next jsp page
	private String advclosedEmpName="";

	private String advclosedEmpId="";

	private String closedtrvlStrtDate="";

	private String closedbranchName="";

	private String closedtravelEnddate="";

	private String closeddepartmentName="";

	private String closedgradeName="";

	private String closeddesig="";

	private String closedrequireingadvAmount="";

	private String closedApplicationDate="";

	private String closedadvapplId="";

	private String closedadvapplCode="";
	//for iterate in closed advance
	 private String closeditpaymentdate="";
	 private String closeditpaymentmode="";
	 private String closeditamount="";
	 private String closeditcomment="";
	 private String closedhiddenAdvPaid="";
	 private String closedhiddenBalanceAmt="";
	 private String closedstatus="";
	ArrayList closedpayList;
	
	
	
	//for payment block newly developed
	private String paymentDate="";
	private String paymentmode="";
	private String checkNumber;
	private String chkDate="";
	private String account="";
	private String bank="";
	private String amount="";
	private String comment="";
	private String checkEdit="";
	private String SrNo="";
	private String categoryListlength="";
	private String bankid="";
	private String itbankid="";
	
	private String recMonth = "";
	private String recYear = "";
	
	String modeLength  = "" ;
	String totalNoOfRecords  = "" ;
	private ArrayList approverCommentList =null ; 
	
	private String recoveryMonth="";
	private String recoveryYear = "";
	private String recoveryDebit = "";
	private String recoveryDebitCode = "";
	
	private String applicantId = "";
	
	public String getRecoveryMonth() {
		return recoveryMonth;
	}
	public void setRecoveryMonth(String recoveryMonth) {
		this.recoveryMonth = recoveryMonth;
	}
	public String getRecoveryYear() {
		return recoveryYear;
	}
	public void setRecoveryYear(String recoveryYear) {
		this.recoveryYear = recoveryYear;
	}
	public String getRecoveryDebit() {
		return recoveryDebit;
	}
	public void setRecoveryDebit(String recoveryDebit) {
		this.recoveryDebit = recoveryDebit;
	}
	public String getRecoveryDebitCode() {
		return recoveryDebitCode;
	}
	public void setRecoveryDebitCode(String recoveryDebitCode) {
		this.recoveryDebitCode = recoveryDebitCode;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
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
	 * @return the advanceType
	 */
	public String getAdvanceType() {
		return advanceType;
	}
	/**
	 * @param advanceType the advanceType to set
	 */
	public void setAdvanceType(String advanceType) {
		this.advanceType = advanceType;
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
	 * @return the advReqDate
	 */
	public String getAdvReqDate() {
		return advReqDate;
	}
	/**
	 * @param advReqDate the advReqDate to set
	 */
	public void setAdvReqDate(String advReqDate) {
		this.advReqDate = advReqDate;
	}
	/**
	 * @return the advAmount
	 */
	public String getAdvAmount() {
		return advAmount;
	}
	/**
	 * @param advAmount the advAmount to set
	 */
	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return the trvlStrtDate
	 */
	public String getTrvlStrtDate() {
		return trvlStrtDate;
	}
	/**
	 * @param trvlStrtDate the trvlStrtDate to set
	 */
	public void setTrvlStrtDate(String trvlStrtDate) {
		this.trvlStrtDate = trvlStrtDate;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the travelenddate
	 */
	public String getTravelEnddate() {
		return travelEnddate;
	}
	/**
	 * @param travelenddate the travelenddate to set
	 */
	public void setTravelEnddate(String travelEnddate) {
		this.travelEnddate = travelEnddate;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	/**
	 * @return the desig
	 */
	public String getDesig() {
		return desig;
	}
	/**
	 * @param desig the desig to set
	 */
	public void setDesig(String desig) {
		this.desig = desig;
	}
	/**
	 * @return the hiddenappldate
	 */
	public String getHiddenappldate() {
		return hiddenappldate;
	}
	/**
	 * @param hiddenappldate the hiddenappldate to set
	 */
	public void setHiddenappldate(String hiddenappldate) {
		this.hiddenappldate = hiddenappldate;
	}
	/**
	 * @return the pendingEmpName
	 */
	public String getPendingEmpName() {
		return pendingEmpName;
	}
	/**
	 * @param pendingEmpName the pendingEmpName to set
	 */
	public void setPendingEmpName(String pendingEmpName) {
		this.pendingEmpName = pendingEmpName;
	}
	/**
	 * @return the pendingEmpId
	 */
	public String getPendingEmpId() {
		return pendingEmpId;
	}
	/**
	 * @param pendingEmpId the pendingEmpId to set
	 */
	public void setPendingEmpId(String pendingEmpId) {
		this.pendingEmpId = pendingEmpId;
	}
	/**
	 * @return the pendingadvAmount
	 */
	public String getPendingadvAmount() {
		return pendingadvAmount;
	}
	/**
	 * @param pendingadvAmount the pendingadvAmount to set
	 */
	public void setPendingadvAmount(String pendingadvAmount) {
		this.pendingadvAmount = pendingadvAmount;
	}
	/**
	 * @return the pendingApplicationDate
	 */
	public String getPendingApplicationDate() {
		return pendingApplicationDate;
	}
	/**
	 * @param pendingApplicationDate the pendingApplicationDate to set
	 */
	public void setPendingApplicationDate(String pendingApplicationDate) {
		this.pendingApplicationDate = pendingApplicationDate;
	}
	/**
	 * @return the hiddenAdvPaid
	 */
	public String getHiddenAdvPaid() {
		return hiddenAdvPaid;
	}
	/**
	 * @param hiddenAdvPaid the hiddenAdvPaid to set
	 */
	public void setHiddenAdvPaid(String hiddenAdvPaid) {
		this.hiddenAdvPaid = hiddenAdvPaid;
	}
	/**
	 * @return the itpaymentdate
	 */
	public String getItpaymentdate() {
		return itpaymentdate;
	}
	/**
	 * @param itpaymentdate the itpaymentdate to set
	 */
	public void setItpaymentdate(String itpaymentdate) {
		this.itpaymentdate = itpaymentdate;
	}
	/**
	 * @return the itpaymentmode
	 */
	public String getItpaymentmode() {
		return itpaymentmode;
	}
	/**
	 * @param itpaymentmode the itpaymentmode to set
	 */
	public void setItpaymentmode(String itpaymentmode) {
		this.itpaymentmode = itpaymentmode;
	}
	/**
	 * @return the itamount
	 */
	public String getItamount() {
		return itamount;
	}
	/**
	 * @param itamount the itamount to set
	 */
	public void setItamount(String itamount) {
		this.itamount = itamount;
	}
	/**
	 * @return the payList
	 */
	public ArrayList getPayList() {
		return payList;
	}
	/**
	 * @param payList the payList to set
	 */
	public void setPayList(ArrayList payList) {
		this.payList = payList;
	}
	/**
	 * @return the hdeleteSkill
	 */
	public String getHdeleteSkill() {
		return hdeleteSkill;
	}
	/**
	 * @param hdeleteSkill the hdeleteSkill to set
	 */
	public void setHdeleteSkill(String hdeleteSkill) {
		this.hdeleteSkill = hdeleteSkill;
	}
	/**
	 * @return the itcomment
	 */
	public String getItcomment() {
		return itcomment;
	}
	/**
	 * @param itcomment the itcomment to set
	 */
	public void setItcomment(String itcomment) {
		this.itcomment = itcomment;
	}
	/**
	 * @return the applId
	 */
	public String getApplId() {
		return applId;
	}
	/**
	 * @param applId the applId to set
	 */
	public void setApplId(String applId) {
		this.applId = applId;
	}
	/**
	 * @return the applCode
	 */
	public String getApplCode() {
		return applCode;
	}
	/**
	 * @param applCode the applCode to set
	 */
	public void setApplCode(String applCode) {
		this.applCode = applCode;
	}
	/**
	 * @return the pendingapplId
	 */
	public String getPendingapplId() {
		return pendingapplId;
	}
	/**
	 * @param pendingapplId the pendingapplId to set
	 */
	public void setPendingapplId(String pendingapplId) {
		this.pendingapplId = pendingapplId;
	}
	/**
	 * @return the pendingapplCode
	 */
	public String getPendingapplCode() {
		return pendingapplCode;
	}
	/**
	 * @param pendingapplCode the pendingapplCode to set
	 */
	public void setPendingapplCode(String pendingapplCode) {
		this.pendingapplCode = pendingapplCode;
	}
	/**
	 * @return the hidstatus
	 */
	public String getHidstatus() {
		return hidstatus;
	}
	/**
	 * @param hidstatus the hidstatus to set
	 */
	public void setHidstatus(String hidstatus) {
		this.hidstatus = hidstatus;
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
	 * @return the pendingempcode
	 */
	public String getPendingempcode() {
		return pendingempcode;
	}
	/**
	 * @param pendingempcode the pendingempcode to set
	 */
	public void setPendingempcode(String pendingempcode) {
		this.pendingempcode = pendingempcode;
	}
	/**
	 * @return the hiddenBalanceAmt
	 */
	public String getHiddenBalanceAmt() {
		return hiddenBalanceAmt;
	}
	/**
	 * @param hiddenBalanceAmt the hiddenBalanceAmt to set
	 */
	public void setHiddenBalanceAmt(String hiddenBalanceAmt) {
		this.hiddenBalanceAmt = hiddenBalanceAmt;
	}
	/**
	 * @return the closedempId
	 */
	public String getClosedempId() {
		return closedempId;
	}
	/**
	 * @param closedempId the closedempId to set
	 */
	public void setClosedempId(String closedempId) {
		this.closedempId = closedempId;
	}
	/**
	 * @return the closedempName
	 */
	public String getClosedempName() {
		return closedempName;
	}
	/**
	 * @param closedempName the closedempName to set
	 */
	public void setClosedempName(String closedempName) {
		this.closedempName = closedempName;
	}
	/**
	 * @return the closedadvanceType
	 */
	public String getClosedadvanceType() {
		return closedadvanceType;
	}
	/**
	 * @param closedadvanceType the closedadvanceType to set
	 */
	public void setClosedadvanceType(String closedadvanceType) {
		this.closedadvanceType = closedadvanceType;
	}
	/**
	 * @return the closedapplicationDate
	 */
	public String getClosedapplicationDate() {
		return closedapplicationDate;
	}
	/**
	 * @param closedapplicationDate the closedapplicationDate to set
	 */
	public void setClosedapplicationDate(String closedapplicationDate) {
		this.closedapplicationDate = closedapplicationDate;
	}
	/**
	 * @return the closedadvReqDate
	 */
	public String getClosedadvReqDate() {
		return closedadvReqDate;
	}
	/**
	 * @param closedadvReqDate the closedadvReqDate to set
	 */
	public void setClosedadvReqDate(String closedadvReqDate) {
		this.closedadvReqDate = closedadvReqDate;
	}
	/**
	 * @return the closedadvAmount
	 */
	public String getClosedadvAmount() {
		return closedadvAmount;
	}
	/**
	 * @param closedadvAmount the closedadvAmount to set
	 */
	public void setClosedadvAmount(String closedadvAmount) {
		this.closedadvAmount = closedadvAmount;
	}
	/**
	 * @return the closedapplId
	 */
	public String getClosedapplId() {
		return closedapplId;
	}
	/**
	 * @param closedapplId the closedapplId to set
	 */
	public void setClosedapplId(String closedapplId) {
		this.closedapplId = closedapplId;
	}
	/**
	 * @return the closedapplCode
	 */
	public String getClosedapplCode() {
		return closedapplCode;
	}
	/**
	 * @param closedapplCode the closedapplCode to set
	 */
	public void setClosedapplCode(String closedapplCode) {
		this.closedapplCode = closedapplCode;
	}
	/**
	 * @return the closedhidstatus
	 */
	public String getClosedhidstatus() {
		return closedhidstatus;
	}
	/**
	 * @param closedhidstatus the closedhidstatus to set
	 */
	public void setClosedhidstatus(String closedhidstatus) {
		this.closedhidstatus = closedhidstatus;
	}
	/**
	 * @return the closedList
	 */
	public ArrayList getClosedList() {
		return closedList;
	}
	/**
	 * @param closedList the closedList to set
	 */
	public void setClosedList(ArrayList closedList) {
		this.closedList = closedList;
	}
	/**
	 * @return the advclosedEmpName
	 */
	public String getAdvclosedEmpName() {
		return advclosedEmpName;
	}
	/**
	 * @param advclosedEmpName the advclosedEmpName to set
	 */
	public void setAdvclosedEmpName(String advclosedEmpName) {
		this.advclosedEmpName = advclosedEmpName;
	}
	/**
	 * @return the advclosedEmpId
	 */
	public String getAdvclosedEmpId() {
		return advclosedEmpId;
	}
	/**
	 * @param advclosedEmpId the advclosedEmpId to set
	 */
	public void setAdvclosedEmpId(String advclosedEmpId) {
		this.advclosedEmpId = advclosedEmpId;
	}
	/**
	 * @return the closedtrvlStrtDate
	 */
	public String getClosedtrvlStrtDate() {
		return closedtrvlStrtDate;
	}
	/**
	 * @param closedtrvlStrtDate the closedtrvlStrtDate to set
	 */
	public void setClosedtrvlStrtDate(String closedtrvlStrtDate) {
		this.closedtrvlStrtDate = closedtrvlStrtDate;
	}
	/**
	 * @return the closedbranchName
	 */
	public String getClosedbranchName() {
		return closedbranchName;
	}
	/**
	 * @param closedbranchName the closedbranchName to set
	 */
	public void setClosedbranchName(String closedbranchName) {
		this.closedbranchName = closedbranchName;
	}
	/**
	 * @return the closedtravelEnddate
	 */
	public String getClosedtravelEnddate() {
		return closedtravelEnddate;
	}
	/**
	 * @param closedtravelEnddate the closedtravelEnddate to set
	 */
	public void setClosedtravelEnddate(String closedtravelEnddate) {
		this.closedtravelEnddate = closedtravelEnddate;
	}
	/**
	 * @return the closeddepartmentName
	 */
	public String getCloseddepartmentName() {
		return closeddepartmentName;
	}
	/**
	 * @param closeddepartmentName the closeddepartmentName to set
	 */
	public void setCloseddepartmentName(String closeddepartmentName) {
		this.closeddepartmentName = closeddepartmentName;
	}
	/**
	 * @return the closedgradeName
	 */
	public String getClosedgradeName() {
		return closedgradeName;
	}
	/**
	 * @param closedgradeName the closedgradeName to set
	 */
	public void setClosedgradeName(String closedgradeName) {
		this.closedgradeName = closedgradeName;
	}
	/**
	 * @return the closeddesig
	 */
	public String getCloseddesig() {
		return closeddesig;
	}
	/**
	 * @param closeddesig the closeddesig to set
	 */
	public void setCloseddesig(String closeddesig) {
		this.closeddesig = closeddesig;
	}
	/**
	 * @return the closedrequireingadvAmount
	 */
	public String getClosedrequireingadvAmount() {
		return closedrequireingadvAmount;
	}
	/**
	 * @param closedrequireingadvAmount the closedrequireingadvAmount to set
	 */
	public void setClosedrequireingadvAmount(String closedrequireingadvAmount) {
		this.closedrequireingadvAmount = closedrequireingadvAmount;
	}
	/**
	 * @return the closedApplicationDate
	 */
	public String getClosedApplicationDate() {
		return closedApplicationDate;
	}
	/**
	 * @param closedApplicationDate the closedApplicationDate to set
	 */
	public void setClosedApplicationDate(String closedApplicationDate) {
		this.closedApplicationDate = closedApplicationDate;
	}
	/**
	 * @return the closedadvapplId
	 */
	public String getClosedadvapplId() {
		return closedadvapplId;
	}
	/**
	 * @param closedadvapplId the closedadvapplId to set
	 */
	public void setClosedadvapplId(String closedadvapplId) {
		this.closedadvapplId = closedadvapplId;
	}
	/**
	 * @return the closedadvapplCode
	 */
	public String getClosedadvapplCode() {
		return closedadvapplCode;
	}
	/**
	 * @param closedadvapplCode the closedadvapplCode to set
	 */
	public void setClosedadvapplCode(String closedadvapplCode) {
		this.closedadvapplCode = closedadvapplCode;
	}
	/**
	 * @return the closedpayList
	 */
	public ArrayList getClosedpayList() {
		return closedpayList;
	}
	/**
	 * @param closedpayList the closedpayList to set
	 */
	public void setClosedpayList(ArrayList closedpayList) {
		this.closedpayList = closedpayList;
	}
	/**
	 * @return the closeditpaymentdate
	 */
	public String getCloseditpaymentdate() {
		return closeditpaymentdate;
	}
	/**
	 * @param closeditpaymentdate the closeditpaymentdate to set
	 */
	public void setCloseditpaymentdate(String closeditpaymentdate) {
		this.closeditpaymentdate = closeditpaymentdate;
	}
	/**
	 * @return the closeditpaymentmode
	 */
	public String getCloseditpaymentmode() {
		return closeditpaymentmode;
	}
	/**
	 * @param closeditpaymentmode the closeditpaymentmode to set
	 */
	public void setCloseditpaymentmode(String closeditpaymentmode) {
		this.closeditpaymentmode = closeditpaymentmode;
	}
	/**
	 * @return the closeditamount
	 */
	public String getCloseditamount() {
		return closeditamount;
	}
	/**
	 * @param closeditamount the closeditamount to set
	 */
	public void setCloseditamount(String closeditamount) {
		this.closeditamount = closeditamount;
	}
	/**
	 * @return the closeditcomment
	 */
	public String getCloseditcomment() {
		return closeditcomment;
	}
	/**
	 * @param closeditcomment the closeditcomment to set
	 */
	public void setCloseditcomment(String closeditcomment) {
		this.closeditcomment = closeditcomment;
	}
	/**
	 * @return the closedhiddenAdvPaid
	 */
	public String getClosedhiddenAdvPaid() {
		return closedhiddenAdvPaid;
	}
	/**
	 * @param closedhiddenAdvPaid the closedhiddenAdvPaid to set
	 */
	public void setClosedhiddenAdvPaid(String closedhiddenAdvPaid) {
		this.closedhiddenAdvPaid = closedhiddenAdvPaid;
	}
	/**
	 * @return the closedhiddenBalanceAmt
	 */
	public String getClosedhiddenBalanceAmt() {
		return closedhiddenBalanceAmt;
	}
	/**
	 * @param closedhiddenBalanceAmt the closedhiddenBalanceAmt to set
	 */
	public void setClosedhiddenBalanceAmt(String closedhiddenBalanceAmt) {
		this.closedhiddenBalanceAmt = closedhiddenBalanceAmt;
	}
	/**
	 * @return the closedstatus
	 */
	public String getClosedstatus() {
		return closedstatus;
	}
	/**
	 * @param closedstatus the closedstatus to set
	 */
	public void setClosedstatus(String closedstatus) {
		this.closedstatus = closedstatus;
	}
	/**
	 * @return the chkno
	 */
	public String getChkno() {
		return chkno;
	}
	/**
	 * @param chkno the chkno to set
	 */
	public void setChkno(String chkno) {
		this.chkno = chkno;
	}
	/**
	 * @return the checkdate
	 */
	public String getCheckdate() {
		return checkdate;
	}
	/**
	 * @param checkdate the checkdate to set
	 */
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the bankname
	 */
	public String getBankname() {
		return bankname;
	}
	/**
	 * @param bankname the bankname to set
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	/**
	 * @return the accountno
	 */
	public String getAccountno() {
		return accountno;
	}
	/**
	 * @param accountno the accountno to set
	 */
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	/**
	 * @return the closeditcheckno
	 */
	public String getCloseditcheckno() {
		return closeditcheckno;
	}
	/**
	 * @param closeditcheckno the closeditcheckno to set
	 */
	public void setCloseditcheckno(String closeditcheckno) {
		this.closeditcheckno = closeditcheckno;
	}
	/**
	 * @return the closeditcheckdate
	 */
	public String getCloseditcheckdate() {
		return closeditcheckdate;
	}
	/**
	 * @param closeditcheckdate the closeditcheckdate to set
	 */
	public void setCloseditcheckdate(String closeditcheckdate) {
		this.closeditcheckdate = closeditcheckdate;
	}
	/**
	 * @return the closedaccountnumber
	 */
	public String getClosedaccountnumber() {
		return closedaccountnumber;
	}
	/**
	 * @param closedaccountnumber the closedaccountnumber to set
	 */
	public void setClosedaccountnumber(String closedaccountnumber) {
		this.closedaccountnumber = closedaccountnumber;
	}
	/**
	 * @return the closedbankname
	 */
	public String getClosedbankname() {
		return closedbankname;
	}
	/**
	 * @param closedbankname the closedbankname to set
	 */
	public void setClosedbankname(String closedbankname) {
		this.closedbankname = closedbankname;
	}
	/**
	 * @return the rownum
	 */
	public String getRownum() {
		return rownum;
	}
	/**
	 * @param rownum the rownum to set
	 */
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return the paymentmode
	 */
	public String getPaymentmode() {
		return paymentmode;
	}
	/**
	 * @param paymentmode the paymentmode to set
	 */
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}
	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	/**
	 * @return the chkDate
	 */
	public String getChkDate() {
		return chkDate;
	}
	/**
	 * @param chkDate the chkDate to set
	 */
	public void setChkDate(String chkDate) {
		this.chkDate = chkDate;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the checkEdit
	 */
	public String getCheckEdit() {
		return checkEdit;
	}
	/**
	 * @param checkEdit the checkEdit to set
	 */
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return SrNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		SrNo = srNo;
	}
	/**
	 * @return the categoryListlength
	 */
	public String getCategoryListlength() {
		return categoryListlength;
	}
	/**
	 * @param categoryListlength the categoryListlength to set
	 */
	public void setCategoryListlength(String categoryListlength) {
		this.categoryListlength = categoryListlength;
	}
	/**
	 * @return the bankid
	 */
	public String getBankid() {
		return bankid;
	}
	/**
	 * @param bankid the bankid to set
	 */
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	/**
	 * @return the itbankid
	 */
	public String getItbankid() {
		return itbankid;
	}
	/**
	 * @param itbankid the itbankid to set
	 */
	public void setItbankid(String itbankid) {
		this.itbankid = itbankid;
	}
	public String getDescCnt1() {
		return descCnt1;
	}
	public void setDescCnt1(String descCnt1) {
		this.descCnt1 = descCnt1;
	}
	/**
	 * @return the settingflag
	 */
	public String getSettingflag() {
		return settingflag;
	}
	/**
	 * @param settingflag the settingflag to set
	 */
	public void setSettingflag(String settingflag) {
		this.settingflag = settingflag;
	}
	/**
	 * @return the trvlId
	 */
	public String getTrvlId() {
		return trvlId;
	}
	/**
	 * @param trvlId the trvlId to set
	 */
	public void setTrvlId(String trvlId) {
		this.trvlId = trvlId;
	}
	/**
	 * @return the pendingtrvlId
	 */
	public String getPendingtrvlId() {
		return pendingtrvlId;
	}
	/**
	 * @param pendingtrvlId the pendingtrvlId to set
	 */
	public void setPendingtrvlId(String pendingtrvlId) {
		this.pendingtrvlId = pendingtrvlId;
	}
	/**
	 * @return the closedtrvlId
	 */
	public String getClosedtrvlId() {
		return closedtrvlId;
	}
	/**
	 * @param closedtrvlId the closedtrvlId to set
	 */
	public void setClosedtrvlId(String closedtrvlId) {
		this.closedtrvlId = closedtrvlId;
	}
	/**
	 * @return the travelcloseId
	 */
	public String getTravelcloseId() {
		return TravelcloseId;
	}
	/**
	 * @param travelcloseId the travelcloseId to set
	 */
	public void setTravelcloseId(String travelcloseId) {
		TravelcloseId = travelcloseId;
	}
	/**
	 * @return the noData
	 */
	public boolean isNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(boolean noData) {
		this.noData = noData;
	}
	/**
	 * @return the guestflag
	 */
	public String getGuestflag() {
		return guestflag;
	}
	/**
	 * @param guestflag the guestflag to set
	 */
	public void setGuestflag(String guestflag) {
		this.guestflag = guestflag;
	}
	/**
	 * @return the hiddengustflag
	 */
	public String getHiddengustflag() {
		return hiddengustflag;
	}
	/**
	 * @param hiddengustflag the hiddengustflag to set
	 */
	public void setHiddengustflag(String hiddengustflag) {
		this.hiddengustflag = hiddengustflag;
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
	public String getZeroflag() {
		return zeroflag;
	}
	public void setZeroflag(String zeroflag) {
		this.zeroflag = zeroflag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getClaimRowId() {
		return claimRowId;
	}
	public void setClaimRowId(String claimRowId) {
		this.claimRowId = claimRowId;
	}
	public boolean isNoClaimData() {
		return noClaimData;
	}
	public void setNoClaimData(boolean noClaimData) {
		this.noClaimData = noClaimData;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getClsd() {
		return clsd;
	}
	public void setClsd(String clsd) {
		this.clsd = clsd;
	}
	public String getClaimtrvlId() {
		return claimtrvlId;
	}
	public void setClaimtrvlId(String claimtrvlId) {
		this.claimtrvlId = claimtrvlId;
	}
	public ArrayList getClmDisbrList() {
		return clmDisbrList;
	}
	public void setClmDisbrList(ArrayList clmDisbrList) {
		this.clmDisbrList = clmDisbrList;
	}
	public String getItEmpName() {
		return itEmpName;
	}
	public void setItEmpName(String itEmpName) {
		this.itEmpName = itEmpName;
	}
	public String getItclmType() {
		return itclmType;
	}
	public void setItclmType(String itclmType) {
		this.itclmType = itclmType;
	}
	public String getItAppDate() {
		return itAppDate;
	}
	public void setItAppDate(String itAppDate) {
		this.itAppDate = itAppDate;
	}
	public String getItClmAmt() {
		return itClmAmt;
	}
	public void setItClmAmt(String itClmAmt) {
		this.itClmAmt = itClmAmt;
	}
	public String getItClmAppId() {
		return itClmAppId;
	}
	public void setItClmAppId(String itClmAppId) {
		this.itClmAppId = itClmAppId;
	}
	public String getItClmStatus() {
		return itClmStatus;
	}
	public void setItClmStatus(String itClmStatus) {
		this.itClmStatus = itClmStatus;
	}
	public String getTranferFlag() {
		return tranferFlag;
	}
	public void setTranferFlag(String tranferFlag) {
		this.tranferFlag = tranferFlag;
	}
	public String getChequeFlag() {
		return chequeFlag;
	}
	public void setChequeFlag(String chequeFlag) {
		this.chequeFlag = chequeFlag;
	}
	public String getDisburseCode() {
		return disburseCode;
	}
	public void setDisburseCode(String disburseCode) {
		this.disburseCode = disburseCode;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getTrvlAppId() {
		return trvlAppId;
	}
	public void setTrvlAppId(String trvlAppId) {
		this.trvlAppId = trvlAppId;
	}
	public String getTrvlAppCode() {
		return trvlAppCode;
	}
	public void setTrvlAppCode(String trvlAppCode) {
		this.trvlAppCode = trvlAppCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getTmsClmAppId() {
		return tmsClmAppId;
	}
	public void setTmsClmAppId(String tmsClmAppId) {
		this.tmsClmAppId = tmsClmAppId;
	}
	public String getTmsClmStatus() {
		return tmsClmStatus;
	}
	public void setTmsClmStatus(String tmsClmStatus) {
		this.tmsClmStatus = tmsClmStatus;
	}
	public String getEditButton() {
		return editButton;
	}
	public void setEditButton(String editButton) {
		this.editButton = editButton;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public TreeMap getPayModeMap() {
		return payModeMap;
	}
	public void setPayModeMap(TreeMap payModeMap) {
		this.payModeMap = payModeMap;
	}
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonthAdv() {
		return monthAdv;
	}
	public void setMonthAdv(String monthAdv) {
		this.monthAdv = monthAdv;
	}
	public String getYearAdv() {
		return yearAdv;
	}
	public void setYearAdv(String yearAdv) {
		this.yearAdv = yearAdv;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	public boolean isAdvRecordsAvailable() {
		return advRecordsAvailable;
	}
	public void setAdvRecordsAvailable(boolean advRecordsAvailable) {
		this.advRecordsAvailable = advRecordsAvailable;
	}
	public int getTotalAdvRecords() {
		return totalAdvRecords;
	}
	public void setTotalAdvRecords(int totalAdvRecords) {
		this.totalAdvRecords = totalAdvRecords;
	}
	public boolean isClaimRecordsAvailable() {
		return claimRecordsAvailable;
	}
	public void setClaimRecordsAvailable(boolean claimRecordsAvailable) {
		this.claimRecordsAvailable = claimRecordsAvailable;
	}
	public int getTotalClaimRecords() {
		return totalClaimRecords;
	}
	public void setTotalClaimRecords(int totalClaimRecords) {
		this.totalClaimRecords = totalClaimRecords;
	}
	public String getMyClaimPage() {
		return myClaimPage;
	}
	public void setMyClaimPage(String myClaimPage) {
		this.myClaimPage = myClaimPage;
	}
	public boolean isAcceptButton() {
		return acceptButton;
	}
	public void setAcceptButton(boolean acceptButton) {
		this.acceptButton = acceptButton;
	}
	public String getDisplayClaimStatus() {
		return displayClaimStatus;
	}
	public void setDisplayClaimStatus(String displayClaimStatus) {
		this.displayClaimStatus = displayClaimStatus;
	}
	public String getDefEmpId() {
		return defEmpId;
	}
	public void setDefEmpId(String defEmpId) {
		this.defEmpId = defEmpId;
	}
	public String getDefApplId() {
		return defApplId;
	}
	public void setDefApplId(String defApplId) {
		this.defApplId = defApplId;
	}
	public String getDefTrvlId() {
		return defTrvlId;
	}
	public void setDefTrvlId(String defTrvlId) {
		this.defTrvlId = defTrvlId;
	}
	public String getDefApplDate() {
		return defApplDate;
	}
	public void setDefApplDate(String defApplDate) {
		this.defApplDate = defApplDate;
	}
	public String getDefAdvAmt() {
		return defAdvAmt;
	}
	public void setDefAdvAmt(String defAdvAmt) {
		this.defAdvAmt = defAdvAmt;
	}
	public String getSettlDate() {
		return settlDate;
	}
	public void setSettlDate(String settlDate) {
		this.settlDate = settlDate;
	}
	public String getDefEmpName() {
		return defEmpName;
	}
	public void setDefEmpName(String defEmpName) {
		this.defEmpName = defEmpName;
	}
	public String getDefEmpGrade() {
		return defEmpGrade;
	}
	public void setDefEmpGrade(String defEmpGrade) {
		this.defEmpGrade = defEmpGrade;
	}
	public ArrayList<Object> getDefaulterList() {
		return defaulterList;
	}
	public void setDefaulterList(ArrayList<Object> defaulterList) {
		this.defaulterList = defaulterList;
	}
	public String getDefaulterEmpName() {
		return defaulterEmpName;
	}
	public void setDefaulterEmpName(String defaulterEmpName) {
		this.defaulterEmpName = defaulterEmpName;
	}
	public String getDeafulterEmpId() {
		return deafulterEmpId;
	}
	public void setDeafulterEmpId(String deafulterEmpId) {
		this.deafulterEmpId = deafulterEmpId;
	}
	public String getDefTravelstartdate() {
		return defTravelstartdate;
	}
	public void setDefTravelstartdate(String defTravelstartdate) {
		this.defTravelstartdate = defTravelstartdate;
	}
	public String getDefBranchName() {
		return defBranchName;
	}
	public void setDefBranchName(String defBranchName) {
		this.defBranchName = defBranchName;
	}
	public String getDefTravelEnddate() {
		return defTravelEnddate;
	}
	public void setDefTravelEnddate(String defTravelEnddate) {
		this.defTravelEnddate = defTravelEnddate;
	}
	public String getDefDepartmentName() {
		return defDepartmentName;
	}
	public void setDefDepartmentName(String defDepartmentName) {
		this.defDepartmentName = defDepartmentName;
	}
	public String getDefGradeName() {
		return defGradeName;
	}
	public void setDefGradeName(String defGradeName) {
		this.defGradeName = defGradeName;
	}
	public String getDefGradeID() {
		return defGradeID;
	}
	public void setDefGradeID(String defGradeID) {
		this.defGradeID = defGradeID;
	}
	public String getDefDesignation() {
		return defDesignation;
	}
	public void setDefDesignation(String defDesignation) {
		this.defDesignation = defDesignation;
	}
	public String getDefaulterApplDate() {
		return defaulterApplDate;
	}
	public void setDefaulterApplDate(String defaulterApplDate) {
		this.defaulterApplDate = defaulterApplDate;
	}
	public String getDefaulterApplId() {
		return defaulterApplId;
	}
	public void setDefaulterApplId(String defaulterApplId) {
		this.defaulterApplId = defaulterApplId;
	}
	public String getDefaulterTrvlId() {
		return defaulterTrvlId;
	}
	public void setDefaulterTrvlId(String defaulterTrvlId) {
		this.defaulterTrvlId = defaulterTrvlId;
	}
	public String getDefMonth() {
		return defMonth;
	}
	public void setDefMonth(String defMonth) {
		this.defMonth = defMonth;
	}
	public String getDefYear() {
		return defYear;
	}
	public void setDefYear(String defYear) {
		this.defYear = defYear;
	}

	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public ArrayList<Object> getClosedDefaulterList() {
		return closedDefaulterList;
	}
	public void setClosedDefaulterList(ArrayList<Object> closedDefaulterList) {
		this.closedDefaulterList = closedDefaulterList;
	}
	public String getDefStatus() {
		return defStatus;
	}
	public void setDefStatus(String defStatus) {
		this.defStatus = defStatus;
	}
	/**
	 * @return the showRevokeStatus
	 */
	public String getShowRevokeStatus() {
		return showRevokeStatus;
	}
	/**
	 * @param showRevokeStatus the showRevokeStatus to set
	 */
	public void setShowRevokeStatus(String showRevokeStatus) {
		this.showRevokeStatus = showRevokeStatus;
	}
	/**
	 * @return the applStatus
	 */
	public String getApplStatus() {
		return applStatus;
	}
	/**
	 * @param applStatus the applStatus to set
	 */
	public void setApplStatus(String applStatus) {
		this.applStatus = applStatus;
	}
	public String getApplApprvdAdvAmt() {
		return applApprvdAdvAmt;
	}
	public void setApplApprvdAdvAmt(String applApprvdAdvAmt) {
		this.applApprvdAdvAmt = applApprvdAdvAmt;
	}
	public String getRecMonth() {
		return recMonth;
	}
	public void setRecMonth(String recMonth) {
		this.recMonth = recMonth;
	}
	public String getRecYear() {
		return recYear;
	}
	public void setRecYear(String recYear) {
		this.recYear = recYear;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public boolean isShowApproverComments() {
		return showApproverComments;
	}
	public void setShowApproverComments(boolean showApproverComments) {
		this.showApproverComments = showApproverComments;
	}
	public String getDefDivision() {
		return defDivision;
	}
	public void setDefDivision(String defDivision) {
		this.defDivision = defDivision;
	}
	public String getPendingListDivision() {
		return pendingListDivision;
	}
	public void setPendingListDivision(String pendingListDivision) {
		this.pendingListDivision = pendingListDivision;
	}
	public String getPendingClaimListDivision() {
		return pendingClaimListDivision;
	}
	public void setPendingClaimListDivision(String pendingClaimListDivision) {
		this.pendingClaimListDivision = pendingClaimListDivision;
	}
	public String getClosedDefDivision() {
		return closedDefDivision;
	}
	public void setClosedDefDivision(String closedDefDivision) {
		this.closedDefDivision = closedDefDivision;
	}
	public String getClosedAdvDisbDivision() {
		return closedAdvDisbDivision;
	}
	public void setClosedAdvDisbDivision(String closedAdvDisbDivision) {
		this.closedAdvDisbDivision = closedAdvDisbDivision;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getBankIFSCCode() {
		return bankIFSCCode;
	}
	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankBSRCode() {
		return bankBSRCode;
	}
	public void setBankBSRCode(String bankBSRCode) {
		this.bankBSRCode = bankBSRCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getApplApprvdAdvAmtCurrency() {
		return applApprvdAdvAmtCurrency;
	}
	public void setApplApprvdAdvAmtCurrency(String applApprvdAdvAmtCurrency) {
		this.applApprvdAdvAmtCurrency = applApprvdAdvAmtCurrency;
	}
	public String getTotalCurrencyExpense() {
		return totalCurrencyExpense;
	}
	public void setTotalCurrencyExpense(String totalCurrencyExpense) {
		this.totalCurrencyExpense = totalCurrencyExpense;
	}
	
	
}
