package org.paradyne.bean.businessCardInfo;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @Modified By AA1711
 * @Date  15-Oct-2012
 * For Vendor
 */
public class BusinessCardInfo extends BeanBase {
	
	private boolean IsAdminApproval;
	private String empId="";
	private String empToken="";
	private String empName="";
	private String empToken1="";
	private String empName1="";
	private String desig="";
	private String dept="";
	private String companyName="";
	private String branch="";
	private String pinCode="";
	private String location="";
	private String officeNum="";
	private String mobile="";
	private String faxNum="";
	private String emailId="";
	private String webSiteAddr="";
	private String source="";
	private String sysInfoFlag="false";	
	private String empTokenp="";
	private String empNamep="";
	private String desigp="";
	private String deptp="";
	private String companyNamep="";
	private String branchp="";
	private String pinCodep="";
	private String locationp="";
	private String officeNump="";
	private String mobilep="";
	private String faxNump="";
	private String emailIdp="";
	private String webSiteAddrp="";	
	private String qtyOfCards="";
	private String reqDate="";
	private String comment="";
	private String status="";
	//Approver bean
	private String managerNameIt="";
	private String srNoIt="";
	private ArrayList <BusinessCardInfo>managerList;
	private String managerIdIt="";
	// keep Inform Employee bean
	private String serialNoIt="";
	private String keepInformedEmpIdIt="";
	private String keepInformedEmpNameIt="";
	private String keepInformedEmpTokenIt="";
	private ArrayList <BusinessCardInfo>keepInformedList;
	private String KeepInformedEmpId="";
	private String KeepInformedEmpToken="";
	private String KeepInformedEmpName="";
	private String SerialNo="";
	private String kiEmployeeId="";
	private String kiEmployeeToken="";
	private String kiEmployeeName="";
	private String checkRemove="";
	private String keepInfoFlag="false";
	private String empFlag="false";
	private String commentFlag="false";
	private String busCardApplCode="";
	private String apprComments="";

	private ArrayList <BusinessCardInfo>apprDtlLst;
	private String apprSrNoIt="";
	private String approvedDateIt="";
	private String apprNameIt="";
	private String apprStatusIt="";
	private String apprCommentsIt="";
	private String approverCode="";
	
	private String tomail="";
	private String subject="";
	
	public String getTomail() {
		return tomail;
	}
	public void setTomail(String tomail) {
		this.tomail = tomail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
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
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOfficeNum() {
		return officeNum;
	}
	public void setOfficeNum(String officeNum) {
		this.officeNum = officeNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getQtyOfCards() {
		return qtyOfCards;
	}
	public String getWebSiteAddr() {
		return webSiteAddr;
	}
	public void setWebSiteAddr(String webSiteAddr) {
		this.webSiteAddr = webSiteAddr;
	}
	public void setQtyOfCards(String qtyOfCards) {
		this.qtyOfCards = qtyOfCards;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmpTokenp() {
		return empTokenp;
	}
	public void setEmpTokenp(String empTokenp) {
		this.empTokenp = empTokenp;
	}
	public String getEmpNamep() {
		return empNamep;
	}
	public void setEmpNamep(String empNamep) {
		this.empNamep = empNamep;
	}
	public String getDesigp() {
		return desigp;
	}
	public void setDesigp(String desigp) {
		this.desigp = desigp;
	}
	public String getDeptp() {
		return deptp;
	}
	public void setDeptp(String deptp) {
		this.deptp = deptp;
	}
	public String getCompanyNamep() {
		return companyNamep;
	}
	public void setCompanyNamep(String companyNamep) {
		this.companyNamep = companyNamep;
	}
	public String getBranchp() {
		return branchp;
	}
	public void setBranchp(String branchp) {
		this.branchp = branchp;
	}
	public String getPinCodep() {
		return pinCodep;
	}
	public void setPinCodep(String pinCodep) {
		this.pinCodep = pinCodep;
	}
	public String getLocationp() {
		return locationp;
	}
	public void setLocationp(String locationp) {
		this.locationp = locationp;
	}
	public String getOfficeNump() {
		return officeNump;
	}
	public void setOfficeNump(String officeNump) {
		this.officeNump = officeNump;
	}
	public String getMobilep() {
		return mobilep;
	}
	public void setMobilep(String mobilep) {
		this.mobilep = mobilep;
	}
	public String getFaxNump() {
		return faxNump;
	}
	public void setFaxNump(String faxNump) {
		this.faxNump = faxNump;
	}
	public String getEmailIdp() {
		return emailIdp;
	}
	public void setEmailIdp(String emailIdp) {
		this.emailIdp = emailIdp;
	}
	public String getWebSiteAddrp() {
		return webSiteAddrp;
	}
	public void setWebSiteAddrp(String webSiteAddrp) {
		this.webSiteAddrp = webSiteAddrp;
	}
	public String getEmpToken1() {
		return empToken1;
	}
	public void setEmpToken1(String empToken1) {
		this.empToken1 = empToken1;
	}
	public String getEmpName1() {
		return empName1;
	}
	public void setEmpName1(String empName1) {
		this.empName1 = empName1;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getSysInfoFlag() {
		return sysInfoFlag;
	}
	public void setSysInfoFlag(String sysInfoFlag) {
		this.sysInfoFlag = sysInfoFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManagerNameIt() {
		return managerNameIt;
	}
	public void setManagerNameIt(String managerNameIt) {
		this.managerNameIt = managerNameIt;
	}
	public String getSrNoIt() {
		return srNoIt;
	}
	public void setSrNoIt(String srNoIt) {
		this.srNoIt = srNoIt;
	}
	public String getSerialNoIt() {
		return serialNoIt;
	}
	public void setSerialNoIt(String serialNoIt) {
		this.serialNoIt = serialNoIt;
	}
	public String getKeepInformedEmpNameIt() {
		return keepInformedEmpNameIt;
	}
	public void setKeepInformedEmpNameIt(String keepInformedEmpNameIt) {
		this.keepInformedEmpNameIt = keepInformedEmpNameIt;
	}
	public String getKeepInformedEmpIdIt() {
		return keepInformedEmpIdIt;
	}
	public void setKeepInformedEmpIdIt(String keepInformedEmpIdIt) {
		this.keepInformedEmpIdIt = keepInformedEmpIdIt;
	}
	public String getKeepInformedEmpId() {
		return KeepInformedEmpId;
	}
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		KeepInformedEmpId = keepInformedEmpId;
	}
	public String getKeepInformedEmpToken() {
		return KeepInformedEmpToken;
	}
	public void setKeepInformedEmpToken(String keepInformedEmpToken) {
		KeepInformedEmpToken = keepInformedEmpToken;
	}
	public String getKeepInformedEmpName() {
		return KeepInformedEmpName;
	}
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		KeepInformedEmpName = keepInformedEmpName;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	
	public String getCheckRemove() {
		return checkRemove;
	}
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}
	public String getKeepInfoFlag() {
		return keepInfoFlag;
	}
	public void setKeepInfoFlag(String keepInfoFlag) {
		this.keepInfoFlag = keepInfoFlag;
	}
	public String getKeepInformedEmpTokenIt() {
		return keepInformedEmpTokenIt;
	}
	public void setKeepInformedEmpTokenIt(String keepInformedEmpTokenIt) {
		this.keepInformedEmpTokenIt = keepInformedEmpTokenIt;
	}
	public String getKiEmployeeId() {
		return kiEmployeeId;
	}
	public void setKiEmployeeId(String kiEmployeeId) {
		this.kiEmployeeId = kiEmployeeId;
	}
	public String getKiEmployeeToken() {
		return kiEmployeeToken;
	}
	public void setKiEmployeeToken(String kiEmployeeToken) {
		this.kiEmployeeToken = kiEmployeeToken;
	}
	public String getKiEmployeeName() {
		return kiEmployeeName;
	}
	public void setKiEmployeeName(String kiEmployeeName) {
		this.kiEmployeeName = kiEmployeeName;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getEmpFlag() {
		return empFlag;
	}
	public void setEmpFlag(String empFlag) {
		this.empFlag = empFlag;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getBusCardApplCode() {
		return busCardApplCode;
	}
	public void setBusCardApplCode(String busCardApplCode) {
		this.busCardApplCode = busCardApplCode;
	}
	public String getApprSrNoIt() {
		return apprSrNoIt;
	}
	public void setApprSrNoIt(String apprSrNoIt) {
		this.apprSrNoIt = apprSrNoIt;
	}
	public String getApprovedDateIt() {
		return approvedDateIt;
	}
	public void setApprovedDateIt(String approvedDateIt) {
		this.approvedDateIt = approvedDateIt;
	}
	public String getApprNameIt() {
		return apprNameIt;
	}
	public void setApprNameIt(String apprNameIt) {
		this.apprNameIt = apprNameIt;
	}
	public String getApprStatusIt() {
		return apprStatusIt;
	}
	public void setApprStatusIt(String apprStatusIt) {
		this.apprStatusIt = apprStatusIt;
	}
	public String getApprCommentsIt() {
		return apprCommentsIt;
	}
	public void setApprCommentsIt(String apprCommentsIt) {
		this.apprCommentsIt = apprCommentsIt;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean getIsAdminApproval() {
		return IsAdminApproval;
	}
	public void setIsAdminApproval(boolean isAdminApproval) {
		if(isAdminApproval)
		 this.IsAdminApproval = true;
		else
			this.IsAdminApproval=false;
	}
	public String getManagerIdIt() {
		return managerIdIt;
	}
	public void setManagerIdIt(String managerIdIt) {
		this.managerIdIt = managerIdIt;
	}
	public ArrayList<BusinessCardInfo> getManagerList() {
		return managerList;
	}
	public void setManagerList(ArrayList<BusinessCardInfo> managerList) {
		this.managerList = managerList;
	}
	public ArrayList<BusinessCardInfo> getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList<BusinessCardInfo> keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public ArrayList<BusinessCardInfo> getApprDtlLst() {
		return apprDtlLst;
	}
	public void setApprDtlLst(ArrayList<BusinessCardInfo> apprDtlLst) {
		this.apprDtlLst = apprDtlLst;
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
	 * @param isAdminApproval the isAdminApproval to set
	 */
	public void setAdminApproval(boolean isAdminApproval) {
		IsAdminApproval = isAdminApproval;
	}
	

}
