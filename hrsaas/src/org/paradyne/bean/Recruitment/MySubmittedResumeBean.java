package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Manish Sakpal
 * Created on 22/09/2011
 *
 */
public class MySubmittedResumeBean  extends BeanBase{
	String myPage = "";
	private String  noData ="false";
	String itrRequisitionName = "";
	String itrRequisitionCode = "";
	String itrCandidateCode = "";
	String itrCandidateName = "";
	String itrResumeName = "";
	String itrPosition = "";
	String totalRecods = "";
	ArrayList<MySubmittedResumeBean> submittedResumeList = new ArrayList<MySubmittedResumeBean>();
	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList<MySubmittedResumeBean> getSubmittedResumeList() {
		return submittedResumeList;
	}
	public void setSubmittedResumeList(
			ArrayList<MySubmittedResumeBean> submittedResumeList) {
		this.submittedResumeList = submittedResumeList;
	}
	public String getItrRequisitionName() {
		return itrRequisitionName;
	}
	public void setItrRequisitionName(String itrRequisitionName) {
		this.itrRequisitionName = itrRequisitionName;
	}
	public String getItrRequisitionCode() {
		return itrRequisitionCode;
	}
	public void setItrRequisitionCode(String itrRequisitionCode) {
		this.itrRequisitionCode = itrRequisitionCode;
	}
	public String getItrCandidateCode() {
		return itrCandidateCode;
	}
	public void setItrCandidateCode(String itrCandidateCode) {
		this.itrCandidateCode = itrCandidateCode;
	}
	public String getItrCandidateName() {
		return itrCandidateName;
	}
	public void setItrCandidateName(String itrCandidateName) {
		this.itrCandidateName = itrCandidateName;
	}
	public String getItrResumeName() {
		return itrResumeName;
	}
	public void setItrResumeName(String itrResumeName) {
		this.itrResumeName = itrResumeName;
	}
	public String getItrPosition() {
		return itrPosition;
	}
	public void setItrPosition(String itrPosition) {
		this.itrPosition = itrPosition;
	}
	public String getTotalRecods() {
		return totalRecods;
	}
	public void setTotalRecods(String totalRecods) {
		this.totalRecods = totalRecods;
	}
	
}
