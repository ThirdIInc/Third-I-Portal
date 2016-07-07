package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveEncashment extends BeanBase {
	
	private String empId;
	private String empToken;
	private String empName;
	private String centerName;
	private String  centerID="";
	private String  desgID="";
	private String desgName="";
	private String rankName="";
	private String rankID="";
	private String gender="";
	
	private String leaveName;
	private String leaveCode;
	private String leaveId;
	private String clBal;
	private String enBal;
	private String encashDate;
	private String enComment;
	private String totalAmt;
	private String total;
	private String enCode;
	private String encashLock;
	private Boolean encashFlag; 
	private Boolean applicableFlag; 
	private String approvalFlag ="true";
	private String imageFlag ="true";
	private String  maxEncashAmt ="";
	private String statusFlag="";
	private String approveStatusFlag="";
	ArrayList leaveList = null;
	
	private String 	isFlag ="false";
	
	private String onhold="";
	
	private String isResult="false";
	
	private String isTotal="false";
	
	private String hiddenStatus ="";
	
	private String status ="PD";
	
	private String level ="";
	
	private String amtPerDay ="";
	
	private String showflag="false";
	
	private String apprName="";
	
	private String apprComments="";
	
	private String  apprDate="";
	
	private String approverDetails="false";
	
	private ArrayList approveList =null;
	
	//FIELDS FOR LEAVE ENCASHMENT HISTORY
	
	private String eId="";
	
	private String leaveType="";

	private String levCloseBalance="";
	
	private String leaveOnhold="";
	
	private String leaveEncashed="";
	
	private String encashAmount="";
	
	private ArrayList list=null;
	
	private String encashStatus="";
	
	private String encashSelect="";
	
	private String hiddenEncashDays="";

	private String dept = "";
	private String appcode = "";
	
	// FILDS FOR APPROVER LIST
	private ArrayList approverList;
	private String approverName = "";
	private String srNoIterator = "";
	
	//Keep infor to
	private String informToken = ""; 
	private String informName = ""; 
	private String informCode = ""; 
	private String keepInformToCode = "";
	private String keepInformToName = "";
	private ArrayList keepInformedList;
	
	// fields for payment details
	private String salarymonth = "";
	private String salaryyear = "";
	private String creditCode = "";
	private String creditType = "";
	private String salaryCheck = "";
	
	private String approverComments = "";
	private String approverCode = "";
	
	private String isMaxEncashLimit = "";	
	
	/**
	 * @return the informToken
	 */
	public String getInformToken() {
		return informToken;
	}
	/**
	 * @param informToken the informToken to set
	 */
	public void setInformToken(String informToken) {
		this.informToken = informToken;
	}
	/**
	 * @return the informName
	 */
	public String getInformName() {
		return informName;
	}
	/**
	 * @param informName the informName to set
	 */
	public void setInformName(String informName) {
		this.informName = informName;
	}
	/**
	 * @return the informCode
	 */
	public String getInformCode() {
		return informCode;
	}
	/**
	 * @param informCode the informCode to set
	 */
	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}
	public String getHiddenEncashDays() {
		return hiddenEncashDays;
	}
	public void setHiddenEncashDays(String hiddenEncashDays) {
		this.hiddenEncashDays = hiddenEncashDays;
	}
	public String getEncashSelect() {
		return encashSelect;
	}
	public void setEncashSelect(String encashSelect) {
		this.encashSelect = encashSelect;
	}
	public String getEncashStatus() {
		return encashStatus;
	}
	public void setEncashStatus(String encashStatus) {
		this.encashStatus = encashStatus;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getEId() {
		return eId;
	}
	public void setEId(String id) {
		eId = id;
	}
	public String getShowflag() {
		return showflag;
	}
	public void setShowflag(String showflag) {
		this.showflag = showflag;
	}
	public String getIsTotal() {
		return isTotal;
	}
	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}
	public String getEncashLock() {
		return encashLock;
	}
	public void setEncashLock(String encashLock) {
		this.encashLock = encashLock;
	}
	public ArrayList getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getClBal() {
		return clBal;
	}
	public void setClBal(String clBal) {
		this.clBal = clBal;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	public String getEnBal() {
		return enBal;
	}
	public void setEnBal(String enBal) {
		this.enBal = enBal;
	}
	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getEncashDate() {
		return encashDate;
	}
	public void setEncashDate(String encashDate) {
		this.encashDate = encashDate;
	}
	public String getEnComment() {
		return enComment;
	}
	public void setEnComment(String enComment) {
		this.enComment = enComment;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getEnCode() {
		return enCode;
	}
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}
	public Boolean getEncashFlag() {
		return encashFlag;
	}
	public void setEncashFlag(Boolean encashFlag) {
		this.encashFlag = encashFlag;
	}
	public Boolean getApplicableFlag() {
		return applicableFlag;
	}
	public void setApplicableFlag(Boolean applicableFlag) {
		this.applicableFlag = applicableFlag;
	}
	public String getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	public String getIsResult() {
		return isResult;
	}
	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
	public String getDesgID() {
		return desgID;
	}
	public void setDesgID(String desgID) {
		this.desgID = desgID;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getRankID() {
		return rankID;
	}
	public void setRankID(String rankID) {
		this.rankID = rankID;
	}
	public String getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	public String getImageFlag() {
		return imageFlag;
	}
	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
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
	public String getAmtPerDay() {
		return amtPerDay;
	}
	public void setAmtPerDay(String amtPerDay) {
		this.amtPerDay = amtPerDay;
	}
	public String getMaxEncashAmt() {
		return maxEncashAmt;
	}
	public void setMaxEncashAmt(String maxEncashAmt) {
		this.maxEncashAmt = maxEncashAmt;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getOnhold() {
		return onhold;
	}
	public void setOnhold(String onhold) {
		this.onhold = onhold;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLevCloseBalance() {
		return levCloseBalance;
	}
	public void setLevCloseBalance(String levCloseBalance) {
		this.levCloseBalance = levCloseBalance;
	}
	public String getLeaveOnhold() {
		return leaveOnhold;
	}
	public void setLeaveOnhold(String leaveOnhold) {
		this.leaveOnhold = leaveOnhold;
	}
	public String getLeaveEncashed() {
		return leaveEncashed;
	}
	public void setLeaveEncashed(String leaveEncashed) {
		this.leaveEncashed = leaveEncashed;
	}
	public String getEncashAmount() {
		return encashAmount;
	}
	public void setEncashAmount(String encashAmount) {
		this.encashAmount = encashAmount;
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
	public String getApproverDetails() {
		return approverDetails;
	}
	public void setApproverDetails(String approverDetails) {
		this.approverDetails = approverDetails;
	}
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
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
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the keepInformToCode
	 */
	public String getKeepInformToCode() {
		return keepInformToCode;
	}
	/**
	 * @param keepInformToCode the keepInformToCode to set
	 */
	public void setKeepInformToCode(String keepInformToCode) {
		this.keepInformToCode = keepInformToCode;
	}
	/**
	 * @return the keepInformToName
	 */
	public String getKeepInformToName() {
		return keepInformToName;
	}
	/**
	 * @param keepInformToName the keepInformToName to set
	 */
	public void setKeepInformToName(String keepInformToName) {
		this.keepInformToName = keepInformToName;
	}
	/**
	 * @return the keepInformedList
	 */
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	/**
	 * @param keepInformedList the keepInformedList to set
	 */
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	/**
	 * @return the appcode
	 */
	public String getAppcode() {
		return appcode;
	}
	/**
	 * @param appcode the appcode to set
	 */
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	/**
	 * @return the approveStatusFlag
	 */
	public String getApproveStatusFlag() {
		return approveStatusFlag;
	}
	/**
	 * @param approveStatusFlag the approveStatusFlag to set
	 */
	public void setApproveStatusFlag(String approveStatusFlag) {
		this.approveStatusFlag = approveStatusFlag;
	}
	/**
	 * @return the salarymonth
	 */
	public String getSalarymonth() {
		return salarymonth;
	}
	/**
	 * @param salarymonth the salarymonth to set
	 */
	public void setSalarymonth(String salarymonth) {
		this.salarymonth = salarymonth;
	}
	/**
	 * @return the salaryyear
	 */
	public String getSalaryyear() {
		return salaryyear;
	}
	/**
	 * @param salaryyear the salaryyear to set
	 */
	public void setSalaryyear(String salaryyear) {
		this.salaryyear = salaryyear;
	}
	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the creditType
	 */
	public String getCreditType() {
		return creditType;
	}
	/**
	 * @param creditType the creditType to set
	 */
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	/**
	 * @return the salaryCheck
	 */
	public String getSalaryCheck() {
		return salaryCheck;
	}
	/**
	 * @param salaryCheck the salaryCheck to set
	 */
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the isMaxEncashLimit
	 */
	public String getIsMaxEncashLimit() {
		return isMaxEncashLimit;
	}
	/**
	 * @param isMaxEncashLimit the isMaxEncashLimit to set
	 */
	public void setIsMaxEncashLimit(String isMaxEncashLimit) {
		this.isMaxEncashLimit = isMaxEncashLimit;
	}
	

}
