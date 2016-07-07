package org.paradyne.bean.Recruitment;
import org.paradyne.lib.*;
import java.util.*;
/**
 * 
 * @author Pradeep
 * Date:12-01-2009
 *
 */

public class CandidateScreeningApproval extends BeanBase {
	
private String saveCandFlag="false";	
private String reqCode;
private String position;
private String candidateName;
private String exp;
private String ctc;
private String gender="";
private String recruiter;
private String status;
private String comment;
private ArrayList list=null;
private String resCode;
private String resume;
private String resumeStatus;
private String screenFlag="false";
private String data="false";
private String count;
private String resCandCode;
private String resReqCode;
private String openFlag="true";
private String myPage="";
private String requisitionId="";
private String requisitionName="";
private String positionId="";
private String positionName="";
private String candCode1="";
private String hrManagerId="";
private String candidateName1="";
private String managerName="";
private String hiddengender="";
private String year="";
private String month="";
private String year1="";
private String candGender="";
private boolean viewFilterFlag =false;
private String  filterFlag="";
private String  show="";
private String  selectname="";
private String  hiddenStatusPage="";
private String appliedFilterFlag ="false";  
private String viewEditFlag ="false";  

private String totalRecords="";
private String modeLength="";
private String itRecrruiterId="";

public String getModeLength() {
	return modeLength;
}
public void setModeLength(String modeLength) {
	this.modeLength = modeLength;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getMyPage() {
	return myPage;
}
public void setMyPage(String myPage) {
	this.myPage = myPage;
}
public String getOpenFlag() {
	return openFlag;
}
public void setOpenFlag(String openFlag) {
	this.openFlag = openFlag;
}
public String getResCode() {
	return resCode;
}
public void setResCode(String resCode) {
	this.resCode = resCode;
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
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getCandidateName() {
	return candidateName;
}
public void setCandidateName(String candidateName) {
	this.candidateName = candidateName;
}
public String getExp() {
	return exp;
}
public void setExp(String exp) {
	this.exp = exp;
}
public String getCtc() {
	return ctc;
}
public void setCtc(String ctc) {
	this.ctc = ctc;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getRecruiter() {
	return recruiter;
}
public void setRecruiter(String recruiter) {
	this.recruiter = recruiter;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public ArrayList getList() {
	return list;
}
public void setList(ArrayList list) {
	this.list = list;
}
public String getResumeStatus() {
	return resumeStatus;
}
public void setResumeStatus(String resumeStatus) {
	this.resumeStatus = resumeStatus;
}
public String getScreenFlag() {
	return screenFlag;
}
public void setScreenFlag(String screenFlag) {
	this.screenFlag = screenFlag;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getResCandCode() {
	return resCandCode;
}
public void setResCandCode(String resCandCode) {
	this.resCandCode = resCandCode;
}
public String getResReqCode() {
	return resReqCode;
}
public void setResReqCode(String resReqCode) {
	this.resReqCode = resReqCode;
}
public String getSaveCandFlag() {
	return saveCandFlag;
}
public void setSaveCandFlag(String saveCandFlag) {
	this.saveCandFlag = saveCandFlag;
}
public String getRequisitionId() {
	return requisitionId;
}
public void setRequisitionId(String requisitionId) {
	this.requisitionId = requisitionId;
}
public String getRequisitionName() {
	return requisitionName;
}
public void setRequisitionName(String requisitionName) {
	this.requisitionName = requisitionName;
}
public String getPositionId() {
	return positionId;
}
public void setPositionId(String positionId) {
	this.positionId = positionId;
}
public String getPositionName() {
	return positionName;
}
public void setPositionName(String positionName) {
	this.positionName = positionName;
}
public String getCandCode1() {
	return candCode1;
}
public void setCandCode1(String candCode1) {
	this.candCode1 = candCode1;
}
public String getHrManagerId() {
	return hrManagerId;
}
public void setHrManagerId(String hrManagerId) {
	this.hrManagerId = hrManagerId;
}
public String getCandidateName1() {
	return candidateName1;
}
public void setCandidateName1(String candidateName1) {
	this.candidateName1 = candidateName1;
}
public String getManagerName() {
	return managerName;
}
public void setManagerName(String managerName) {
	this.managerName = managerName;
}
public String getHiddengender() {
	return hiddengender;
}
public void setHiddengender(String hiddengender) {
	this.hiddengender = hiddengender;
}
public String getYear1() {
	return year1;
}
public void setYear1(String year1) {
	this.year1 = year1;
}
public String getTotalRecords() {
	return totalRecords;
}
public void setTotalRecords(String totalRecords) {
	this.totalRecords = totalRecords;
}
public String getCandGender() {
	return candGender;
}
public void setCandGender(String candGender) {
	this.candGender = candGender;
}
public boolean isViewFilterFlag() {
	return viewFilterFlag;
}
public void setViewFilterFlag(boolean viewFilterFlag) {
	this.viewFilterFlag = viewFilterFlag;
}
 
public String getFilterFlag() {
	return filterFlag;
}
public void setFilterFlag(String filterFlag) {
	this.filterFlag = filterFlag;
}
public String getShow() {
	return show;
}
public void setShow(String show) {
	this.show = show;
}
public String getSelectname() {
	return selectname;
}
public void setSelectname(String selectname) {
	this.selectname = selectname;
}
public String getHiddenStatusPage() {
	return hiddenStatusPage;
}
public void setHiddenStatusPage(String hiddenStatusPage) {
	this.hiddenStatusPage = hiddenStatusPage;
}
public String getAppliedFilterFlag() {
	return appliedFilterFlag;
}
public void setAppliedFilterFlag(String appliedFilterFlag) {
	this.appliedFilterFlag = appliedFilterFlag;
}
public String getItRecrruiterId() {
	return itRecrruiterId;
}
public void setItRecrruiterId(String itRecrruiterId) {
	this.itRecrruiterId = itRecrruiterId;
}
public String getViewEditFlag() {
	return viewEditFlag;
}
public void setViewEditFlag(String viewEditFlag) {
	this.viewEditFlag = viewEditFlag;
}
 
 

}
