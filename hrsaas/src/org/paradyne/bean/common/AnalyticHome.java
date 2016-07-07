package org.paradyne.bean.common;
import org.paradyne.lib.BeanBase;
import java.util.*;
/*
 * author:Pradeep Kumar Sahoo
 * Date:01-02-2008
 */
public class AnalyticHome extends BeanBase {
	
	String leaveName;
	String leaveLink;
	
	String attendanceName;
	String attendanceLink;
	
	String appraisalLink;
	String appraisalName;
	
	String trainingLink;
	String trainingName;
	
	String taxLink;
	String taxName;
	
	String adminLink;
	String adminName;
	
	String hrmLink;
	String hrmName;
	
	String payrollName;
	String payrollLink;
	
	String recruitLink;
	String recruitName;
	
	
	
	
	
	ArrayList leaveList=null;
	ArrayList attendanceList=null;
	ArrayList apprList=null;
	ArrayList trList=null;
	ArrayList taxList=null;
	ArrayList adminList=null;
	ArrayList hrmList=null;
	ArrayList payrollList=null;
	ArrayList recruitList=null;
	
	public String getAttendanceName() {
		return attendanceName;
	}
	public void setAttendanceName(String attendanceName) {
		this.attendanceName = attendanceName;
	}
	public String getAttendanceLink() {
		return attendanceLink;
	}
	public void setAttendanceLink(String attendanceLink) {
		this.attendanceLink = attendanceLink;
	}
	public ArrayList getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(ArrayList attendanceList) {
		this.attendanceList = attendanceList;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeaveLink() {
		return leaveLink;
	}
	public void setLeaveLink(String leaveLink) {
		this.leaveLink = leaveLink;
	}
	public ArrayList getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}
	public String getAppraisalLink() {
		return appraisalLink;
	}
	public void setAppraisalLink(String appraisalLink) {
		this.appraisalLink = appraisalLink;
	}
	public String getAppraisalName() {
		return appraisalName;
	}
	public void setAppraisalName(String appraisalName) {
		this.appraisalName = appraisalName;
	}
	public ArrayList getApprList() {
		return apprList;
	}
	public void setApprList(ArrayList apprList) {
		this.apprList = apprList;
	}
	public String getTrainingLink() {
		return trainingLink;
	}
	public void setTrainingLink(String trainingLink) {
		this.trainingLink = trainingLink;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public ArrayList getTrList() {
		return trList;
	}
	public void setTrList(ArrayList trList) {
		this.trList = trList;
	}
	public String getTaxLink() {
		return taxLink;
	}
	public void setTaxLink(String taxLink) {
		this.taxLink = taxLink;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public String getAdminLink() {
		return adminLink;
	}
	public void setAdminLink(String adminLink) {
		this.adminLink = adminLink;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public ArrayList getTaxList() {
		return taxList;
	}
	public void setTaxList(ArrayList taxList) {
		this.taxList = taxList;
	}
	public ArrayList getAdminList() {
		return adminList;
	}
	public void setAdminList(ArrayList adminList) {
		this.adminList = adminList;
	}
	public String getHrmLink() {
		return hrmLink;
	}
	public void setHrmLink(String hrmLink) {
		this.hrmLink = hrmLink;
	}
	public String getHrmName() {
		return hrmName;
	}
	public void setHrmName(String hrmName) {
		this.hrmName = hrmName;
	}
	public String getPayrollName() {
		return payrollName;
	}
	public void setPayrollName(String payrollName) {
		this.payrollName = payrollName;
	}
	public String getPayrollLink() {
		return payrollLink;
	}
	public void setPayrollLink(String payrollLink) {
		this.payrollLink = payrollLink;
	}
	public String getRecruitLink() {
		return recruitLink;
	}
	public void setRecruitLink(String recruitLink) {
		this.recruitLink = recruitLink;
	}
	public String getRecruitName() {
		return recruitName;
	}
	public void setRecruitName(String recruitName) {
		this.recruitName = recruitName;
	}
	public ArrayList getHrmList() {
		return hrmList;
	}
	public void setHrmList(ArrayList hrmList) {
		this.hrmList = hrmList;
	}
	public ArrayList getPayrollList() {
		return payrollList;
	}
	public void setPayrollList(ArrayList payrollList) {
		this.payrollList = payrollList;
	}
	public ArrayList getRecruitList() {
		return recruitList;
	}
	public void setRecruitList(ArrayList recruitList) {
		this.recruitList = recruitList;
	}

}
