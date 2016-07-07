/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0517
 *
 */
public class ConsultantPortal extends BeanBase {

	private String itReqName="";
	private String itPosition="";
	private String itLocation=""; 
	private String itNoOfOpenVac="";
	private String itReqCode="";
	private String itTotalNoPostVac=""; 
	private String itHiringManager=""; 
	private String itHiringManagerId="";  
	
	
	
	private String reqName="";
	private String reqCode="";
	private String position="";
	private String positionCode="";
	private String branch="";
	private String branchCode="";
	private String noOfVacancies="";
	private String isClose="false";  
	private String myPage="";
	private String show="";
	private String  noData ="false";
	private String  apprvFlag ="false";
	ArrayList  partnerJobList = new ArrayList(); 
	
	private ArrayList list=null;

	public String getItReqName() {
		return itReqName;
	}

	public void setItReqName(String itReqName) {
		this.itReqName = itReqName;
	}

	public String getItPosition() {
		return itPosition;
	}

	public void setItPosition(String itPosition) {
		this.itPosition = itPosition;
	}

	public String getItLocation() {
		return itLocation;
	}

	public void setItLocation(String itLocation) {
		this.itLocation = itLocation;
	}

	public String getItNoOfOpenVac() {
		return itNoOfOpenVac;
	}

	public void setItNoOfOpenVac(String itNoOfOpenVac) {
		this.itNoOfOpenVac = itNoOfOpenVac;
	}

	public String getItReqCode() {
		return itReqCode;
	}

	public void setItReqCode(String itReqCode) {
		this.itReqCode = itReqCode;
	}

	public String getItTotalNoPostVac() {
		return itTotalNoPostVac;
	}

	public void setItTotalNoPostVac(String itTotalNoPostVac) {
		this.itTotalNoPostVac = itTotalNoPostVac;
	}

	public String getItHiringManager() {
		return itHiringManager;
	}

	public void setItHiringManager(String itHiringManager) {
		this.itHiringManager = itHiringManager;
	}

	public String getItHiringManagerId() {
		return itHiringManagerId;
	}

	public void setItHiringManagerId(String itHiringManagerId) {
		this.itHiringManagerId = itHiringManagerId;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
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

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(String noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
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

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getApprvFlag() {
		return apprvFlag;
	}

	public void setApprvFlag(String apprvFlag) {
		this.apprvFlag = apprvFlag;
	}

	public ArrayList getPartnerJobList() {
		return partnerJobList;
	}

	public void setPartnerJobList(ArrayList partnerJobList) {
		this.partnerJobList = partnerJobList;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}
	
	
	
	
}
