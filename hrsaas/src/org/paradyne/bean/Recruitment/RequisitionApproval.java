/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author pradeep
 * @date Jan 03, 2009
 * @description RequisitionApproval serves as bean for Manpower Requisition Approval form
 */
public class RequisitionApproval extends BeanBase {
	private String source="";
	private String reqStatus;
	private String apprflag = "false";
	private ArrayList list;
	private String reqNo;
	private String level;
	private String pageNoField="";
	private String totalPage="";
	
	private String reqName;
	private String reqDate;
	private String appliedBy;
	private String empCode;
	private String status;
	private String srNo;
	private String appliedFor;
	private String hrManager;
	private String statusIterator;
	private String comment;
	private String noData="false";
	private String myPage="";
	private String holdFlag="false";
	private String commentHdr="false";
	private String commentItr="false";
	
	//Added by Nilesh 9th August

	private String approverName  = "";
	private ArrayList approverList;

	private ArrayList  keepInformedList;
	private String  serialNo  = "";
	private String  keepInformedEmpToken  = "";
	private String  keepInformedEmpName  = "";
	private String  keepInformedEmpId  = "";

	private String employeeId = "";
	private String  employeeToken = "";
	private String  employeeName = "";


	private String empid = "";
	private String  keepInformedEmpTokenItt = "";
	private String  keepInformedEmpNameItt = "";
	private String hiddenDeleteId = "";

	private String srNoIterator = "";

	private String firstApproverCode = "";
	private String firstApproverToken = "";


	private String checkRemove = "";
	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return reqStatus;
	}
	/**
	 * @param reqStatus the reqStatus to set
	 */
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	/**
	 * @return the apprflag
	 */
	public String getApprflag() {
		return apprflag;
	}
	/**
	 * @param apprflag the apprflag to set
	 */
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	/**
	 * @return the list
	 */
	public ArrayList getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList list) {
		this.list = list;
	}
	/**
	 * @return the reqNo
	 */
	public String getReqNo() {
		return reqNo;
	}
	/**
	 * @param reqNo the reqNo to set
	 */
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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
	 * @return the reqName
	 */
	public String getReqName() {
		return reqName;
	}
	/**
	 * @param reqName the reqName to set
	 */
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	/**
	 * @return the reqDate
	 */
	public String getReqDate() {
		return reqDate;
	}
	/**
	 * @param reqDate the reqDate to set
	 */
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	/**
	 * @return the appliedBy
	 */
	public String getAppliedBy() {
		return appliedBy;
	}
	/**
	 * @param appliedBy the appliedBy to set
	 */
	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}
	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the appliedFor
	 */
	public String getAppliedFor() {
		return appliedFor;
	}
	/**
	 * @param appliedFor the appliedFor to set
	 */
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
	/**
	 * @return the hrManager
	 */
	public String getHrManager() {
		return hrManager;
	}
	/**
	 * @param hrManager the hrManager to set
	 */
	public void setHrManager(String hrManager) {
		this.hrManager = hrManager;
	}
	/**
	 * @return the statusIterator
	 */
	public String getStatusIterator() {
		return statusIterator;
	}
	/**
	 * @param statusIterator the statusIterator to set
	 */
	public void setStatusIterator(String statusIterator) {
		this.statusIterator = statusIterator;
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
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getHoldFlag() {
		return holdFlag;
	}
	public void setHoldFlag(String holdFlag) {
		this.holdFlag = holdFlag;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getCommentHdr() {
		return commentHdr;
	}
	public void setCommentHdr(String commentHdr) {
		this.commentHdr = commentHdr;
	}
	public String getCommentItr() {
		return commentItr;
	}
	public void setCommentItr(String commentItr) {
		this.commentItr = commentItr;
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
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the keepInformedEmpToken
	 */
	public String getKeepInformedEmpToken() {
		return keepInformedEmpToken;
	}
	/**
	 * @param keepInformedEmpToken the keepInformedEmpToken to set
	 */
	public void setKeepInformedEmpToken(String keepInformedEmpToken) {
		this.keepInformedEmpToken = keepInformedEmpToken;
	}
	/**
	 * @return the keepInformedEmpName
	 */
	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}
	/**
	 * @param keepInformedEmpName the keepInformedEmpName to set
	 */
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}
	/**
	 * @return the keepInformedEmpId
	 */
	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}
	/**
	 * @param keepInformedEmpId the keepInformedEmpId to set
	 */
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}
	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	/**
	 * @return the keepInformedEmpTokenItt
	 */
	public String getKeepInformedEmpTokenItt() {
		return keepInformedEmpTokenItt;
	}
	/**
	 * @param keepInformedEmpTokenItt the keepInformedEmpTokenItt to set
	 */
	public void setKeepInformedEmpTokenItt(String keepInformedEmpTokenItt) {
		this.keepInformedEmpTokenItt = keepInformedEmpTokenItt;
	}
	/**
	 * @return the keepInformedEmpNameItt
	 */
	public String getKeepInformedEmpNameItt() {
		return keepInformedEmpNameItt;
	}
	/**
	 * @param keepInformedEmpNameItt the keepInformedEmpNameItt to set
	 */
	public void setKeepInformedEmpNameItt(String keepInformedEmpNameItt) {
		this.keepInformedEmpNameItt = keepInformedEmpNameItt;
	}
	/**
	 * @return the hiddenDeleteId
	 */
	public String getHiddenDeleteId() {
		return hiddenDeleteId;
	}
	/**
	 * @param hiddenDeleteId the hiddenDeleteId to set
	 */
	public void setHiddenDeleteId(String hiddenDeleteId) {
		this.hiddenDeleteId = hiddenDeleteId;
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
	 * @return the checkRemove
	 */
	public String getCheckRemove() {
		return checkRemove;
	}
	/**
	 * @param checkRemove the checkRemove to set
	 */
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	

}
