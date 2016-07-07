package org.paradyne.bean.attendance;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;
/**
 * @author  saipavan v 
 * 04-06-2008
 */
public class OutdoorApproval extends BeanBase {

	private String outAppStatus ="";
	private String outDate;
	private String outLoc;
	private String outVisitcode;
	private String ecode;
	private String purpose;
	private String checkStatus;
	private String status;
	private String apprflag="false";
	private String level;
	private ArrayList list;
	private ArrayList applist;
	private String etoken;
	private String ename;
	private String eApprCode;
	private String noData = "false";
	
	private String outvCode;
	private String checkApproveRejectStatus;
	private String approverComments;
	private String applicationLevel;
	
	
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getApplist() {
		return applist;
	}
	public String getOutAppStatus() {
		return outAppStatus;
	}
	public void setOutAppStatus(String outAppStatus) {
		this.outAppStatus = outAppStatus;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getOutLoc() {
		return outLoc;
	}
	public void setOutLoc(String outLoc) {
		this.outLoc = outLoc;
	}
	public String getOutVisitcode() {
		return outVisitcode;
	}
	public void setOutVisitcode(String outVisitcode) {
		this.outVisitcode = outVisitcode;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setApplist(ArrayList applist) {
		this.applist = applist;
	}
	
	
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getEtoken() {
		return etoken;
	}
	public void setEtoken(String etoken) {
		this.etoken = etoken;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEApprCode() {
		return eApprCode;
	}
	public void setEApprCode(String apprCode) {
		eApprCode = apprCode;
	}
	public String getOutvCode() {
		return outvCode;
	}
	public void setOutvCode(String outvCode) {
		this.outvCode = outvCode;
	}
	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}
	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getApplicationLevel() {
		return applicationLevel;
	}
	public void setApplicationLevel(String applicationLevel) {
		this.applicationLevel = applicationLevel;
	}
}
