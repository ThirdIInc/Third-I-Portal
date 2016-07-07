package org.paradyne.bean.admin.srd;

import java.util.ArrayList;
import java.io.Serializable;
import org.paradyne.lib.BeanBase;

public class EmployeePosting extends BeanBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String fromDateCode="";
	private String toDateCode="";
	private String paraId = "";
	private String empID = "";
	private String empName = "";
	private String deptName = "";
	private String centerName = "";
	private String rankId= "";
	private String branchCode= "";
	private String empPostingDesc="";
	private String frmDate = "";
	private String toDate = "";
	private String paracode = "";
	private String empToken="";
	private String rankName="";
	ArrayList <EmployeePosting> postingList = null;
	private String generalFlag="false";
	
	
	private String siteCode = "";
	private String siteName = "";
	private String locationCode= "";
	private String siteCodeItt="";
	private String locCodeItt="";
	private String locationName = "";
	private String frmDateItt = "";
	private String toDateItt = "";
	private String empPostingDescItt="";
	private String locationNameItt="";
	private String siteNameItt = "";
	
	private String postingId="";
	private String empPostingId="";
	private String uploadFileName="";
	private String flag="";
	private String profileEmpName="";
	private String noData="";
	private String empPostingIdItt="";
	
	String isNotGeneralUser="false";

	private boolean editDetail=false;
	//Added By Anantha Lakshmi
	
	private boolean editFlag=false;
	
	//ended 
	
	
	
	
	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}
	
	
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}
	
	
	
	public String getEmpPostingId() {
		return empPostingId;
	}
	public void setEmpPostingId(String empPostingId) {
		this.empPostingId = empPostingId;
	}
	public String getPostingId() {
		return postingId;
	}
	public void setPostingId(String postingId) {
		this.postingId = postingId;
	}
	
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getEmpPostingDesc() {
		return empPostingDesc;
	}
	public void setEmpPostingDesc(String empPostingDesc) {
		this.empPostingDesc = empPostingDesc;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
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
	public String getGeneralFlag() {
		return generalFlag;
	}
	public ArrayList<EmployeePosting> getPostingList() {
		return postingList;
	}
	public void setPostingList(ArrayList<EmployeePosting> postingList) {
		this.postingList = postingList;
	}


	public void setGeneralFlag(String generalFlag) {
		this.generalFlag = generalFlag;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getRankId() {
		return rankId;
	}
	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getFromDateCode() {
		return fromDateCode;
	}
	public void setFromDateCode(String fromDateCode) {
		this.fromDateCode = fromDateCode;
	}
	public String getToDateCode() {
		return toDateCode;
	}
	public void setToDateCode(String toDateCode) {
		this.toDateCode = toDateCode;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProfileEmpName() {
		return profileEmpName;
	}
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}


	public boolean isEditDetail() {
		return editDetail;
	}


	public String getSiteCodeItt() {
		return siteCodeItt;
	}


	public void setSiteCodeItt(String siteCodeItt) {
		this.siteCodeItt = siteCodeItt;
	}


	public String getLocCodeItt() {
		return locCodeItt;
	}


	public void setLocCodeItt(String locCodeItt) {
		this.locCodeItt = locCodeItt;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getFrmDateItt() {
		return frmDateItt;
	}


	public void setFrmDateItt(String frmDateItt) {
		this.frmDateItt = frmDateItt;
	}


	public String getToDateItt() {
		return toDateItt;
	}


	public void setToDateItt(String toDateItt) {
		this.toDateItt = toDateItt;
	}


	public String getEmpPostingDescItt() {
		return empPostingDescItt;
	}


	public void setEmpPostingDescItt(String empPostingDescItt) {
		this.empPostingDescItt = empPostingDescItt;
	}


	public String getLocationNameItt() {
		return locationNameItt;
	}


	public void setLocationNameItt(String locationNameItt) {
		this.locationNameItt = locationNameItt;
	}


	public String getSiteNameItt() {
		return siteNameItt;
	}


	public void setSiteNameItt(String siteNameItt) {
		this.siteNameItt = siteNameItt;
	}


	public String getEmpPostingIdItt() {
		return empPostingIdItt;
	}


	public void setEmpPostingIdItt(String empPostingIdItt) {
		this.empPostingIdItt = empPostingIdItt;
	}
	

}
