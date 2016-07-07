package org.paradyne.bean.Recruitment;
import org.paradyne.lib.BeanBase;
import java.util.*;
/**
 * @author Pradeep Sahoo
 * Date:07-02-2009
 *
 */

public class OfferApproval extends BeanBase {
	//Offer Details
	private String offerFlag="false";
	private String reqCode="";
	private String reqName="";
	private String candCode="";
	private String candName="";
	private String position="";
	private String days="";
	private String noData="false";
	private String offerStatus="";
	ArrayList list=null;
	private String resume="";
	private String offerCode;
	private String statusFlag="";
	private String saveFlag="false";
	private String pending="false";
	private String approved="false";
	private String rejected="false";
	
	//Appointment Details
	private String apptFlag="false";
	private String aptReqnCode="";
	private String aptReqName="";
	private String aprCandCode="";
	private String aptCandName="";
	private String aptPos="";
	private String aptDays="";
	private String aptId="";
	private String aptSaveFlag="false";
	private String aptStatusFlag="";
	private String aptResume="";
	private String aptStatus="";
	private String counter="";
	private ArrayList aptmtList=null;
	private String noApt="";
	private String appointment="";
	private String approvedFlag="false";
	private String pendingFlag="false";
	private String rejectedFlag="false";
	private String myPage="";
	private String myPage1="";
	
	// added ganesh 
	
	private String templateCode;
	private String template;
	
	
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getMyPage1() {
		return myPage1;
	}
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}
	public String getNoApt() {
		return noApt;
	}
	public void setNoApt(String noApt) {
		this.noApt = noApt;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getAptReqnCode() {
		return aptReqnCode;
	}
	public void setAptReqnCode(String aptReqnCode) {
		this.aptReqnCode = aptReqnCode;
	}
	public String getAptReqName() {
		return aptReqName;
	}
	public void setAptReqName(String aptReqName) {
		this.aptReqName = aptReqName;
	}
	public String getAprCandCode() {
		return aprCandCode;
	}
	public void setAprCandCode(String aprCandCode) {
		this.aprCandCode = aprCandCode;
	}
	public String getAptCandName() {
		return aptCandName;
	}
	public void setAptCandName(String aptCandName) {
		this.aptCandName = aptCandName;
	}
	public String getAptPos() {
		return aptPos;
	}
	public void setAptPos(String aptPos) {
		this.aptPos = aptPos;
	}
	public String getAptDays() {
		return aptDays;
	}
	public void setAptDays(String aptDays) {
		this.aptDays = aptDays;
	}
	public String getAptId() {
		return aptId;
	}
	public void setAptId(String aptId) {
		this.aptId = aptId;
	}
	public String getAptSaveFlag() {
		return aptSaveFlag;
	}
	public void setAptSaveFlag(String aptSaveFlag) {
		this.aptSaveFlag = aptSaveFlag;
	}
	public String getAptStatusFlag() {
		return aptStatusFlag;
	}
	public void setAptStatusFlag(String aptStatusFlag) {
		this.aptStatusFlag = aptStatusFlag;
	}
	public String getAptResume() {
		return aptResume;
	}
	public void setAptResume(String aptResume) {
		this.aptResume = aptResume;
	}
	public String getAptStatus() {
		return aptStatus;
	}
	public void setAptStatus(String aptStatus) {
		this.aptStatus = aptStatus;
	}
	public ArrayList getAptmtList() {
		return aptmtList;
	}
	public void setAptmtList(ArrayList aptmtList) {
		this.aptmtList = aptmtList;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public String getAppointment() {
		return appointment;
	}
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}
	public String getOfferFlag() {
		return offerFlag;
	}
	public void setOfferFlag(String offerFlag) {
		this.offerFlag = offerFlag;
	}
	public String getApptFlag() {
		return apptFlag;
	}
	public void setApptFlag(String apptFlag) {
		this.apptFlag = apptFlag;
	}
	public String getPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}
	public String getRejectedFlag() {
		return rejectedFlag;
	}
	public void setRejectedFlag(String rejectedFlag) {
		this.rejectedFlag = rejectedFlag;
	}
	public String getApprovedFlag() {
		return approvedFlag;
	}
	public void setApprovedFlag(String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	
	
	
	

}
