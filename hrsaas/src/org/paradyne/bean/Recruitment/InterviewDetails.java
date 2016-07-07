/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 *
 */
public class InterviewDetails extends BeanBase {
	private String intCode;
	private String reqCode;
	private String candCode;
	private String candName;
	private String position;
	private String positionId;
	private String intDate;
	private String intTime;
	private String intRoundType;
	private String intCv;
	private String intPrvFdbck;
	private String comments;
	private String reqName;
	private String department;
	private String deptId;
	private String division;
	private String divisionId;
	private String branch;
	private String branchId;
	private String pendingFlag = "true";
	private String canceledFlag = "false";
	private String intDtlCode;
	private String resumeName="";
	private ArrayList intrList;
	private String statusFlag="";
	private String myPage="";
	private String show="";
	private String intRoundTypeCode = "";
	private String candidateEvaluationCode = "";
	private String interviewDetailCode = "";
	private String hiddenCandidateEvaluationCode = "";
	private String hiddenInterviewDetailCode = "";
	
	public String getIntRoundTypeCode() {
		return intRoundTypeCode;
	}
	public void setIntRoundTypeCode(String intRoundTypeCode) {
		this.intRoundTypeCode = intRoundTypeCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public ArrayList getIntrList() {
		return intrList;
	}
	public void setIntrList(ArrayList intrList) {
		this.intrList = intrList;
	}
	public String getIntCode() {
		return intCode;
	}
	public void setIntCode(String intCode) {
		this.intCode = intCode;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getCandCode() {
		return candCode;
	}
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIntDate() {
		return intDate;
	}
	public void setIntDate(String intDate) {
		this.intDate = intDate;
	}
	public String getIntTime() {
		return intTime;
	}
	public void setIntTime(String intTime) {
		this.intTime = intTime;
	}
	public String getIntRoundType() {
		return intRoundType;
	}
	public void setIntRoundType(String intRoundType) {
		this.intRoundType = intRoundType;
	}
	public String getIntCv() {
		return intCv;
	}
	public void setIntCv(String intCv) {
		this.intCv = intCv;
	}
	public String getIntPrvFdbck() {
		return intPrvFdbck;
	}
	public void setIntPrvFdbck(String intPrvFdbck) {
		this.intPrvFdbck = intPrvFdbck;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}
	public String getIntDtlCode() {
		return intDtlCode;
	}
	public void setIntDtlCode(String intDtlCode) {
		this.intDtlCode = intDtlCode;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getCanceledFlag() {
		return canceledFlag;
	}
	public void setCanceledFlag(String canceledFlag) {
		this.canceledFlag = canceledFlag;
	}
	public String getCandidateEvaluationCode() {
		return candidateEvaluationCode;
	}
	public void setCandidateEvaluationCode(String candidateEvaluationCode) {
		this.candidateEvaluationCode = candidateEvaluationCode;
	}
	public String getInterviewDetailCode() {
		return interviewDetailCode;
	}
	public void setInterviewDetailCode(String interviewDetailCode) {
		this.interviewDetailCode = interviewDetailCode;
	}
	public String getHiddenCandidateEvaluationCode() {
		return hiddenCandidateEvaluationCode;
	}
	public void setHiddenCandidateEvaluationCode(
			String hiddenCandidateEvaluationCode) {
		this.hiddenCandidateEvaluationCode = hiddenCandidateEvaluationCode;
	}
	public String getHiddenInterviewDetailCode() {
		return hiddenInterviewDetailCode;
	}
	public void setHiddenInterviewDetailCode(String hiddenInterviewDetailCode) {
		this.hiddenInterviewDetailCode = hiddenInterviewDetailCode;
	}
}
