package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class prejoiningActivities extends BeanBase {
	
	
	
	private boolean conductflag=false;    //not using
	
	private String RespCode;
	private String RespName;  
	private String buttonpanelcode;
	/**   
	 * Flags For Cancel Button
	 */
	private String rowId;
	
	
	private String bgStatus; //for getting status
									
	private String prejoinCode;
	private String candidateName; 
	private String candidateCode;
	private String reqCode;
	private String reqName;
	
	private String division;
	private String branch;
	private String department;
	private String position;
	
	private String Appstatus;
	
	private String offerDate;
	private String joinDate;
	
	private String reportingTo; 
	private String reportingName;
	
	
	
	private String checkListType;                       //background or prejoin
	private String DupcheckListType;
	
	private String checkListCode;
	
	private boolean checklistTable;                     // act as flag whether table to be visible or not
	
	
	//prejoin Check List Details  
	
	
	
	private String prejoinListCode;
	private String prejoinListName;
	private String prejoinTargetDate;
	private String ResponsiblePersonName;
	private String ResponsiblePersonCode;
	private String Lstatus;
	private String activityRequired;
	private String activityStatus;
	
	private String DupactivityRequired;
	private String DupactivityStatus;
	
	
	private String Lcandidate;   
	private String Lcancode;      
	private String Loffercode;
	private String Lreqcode;
	
	private String LreqName;      
	
	private String Lposition;     
	
	private String Lofferstatus; 
	
	private String LofferDate;
	private String LjoinDate;
	private String LreportTo;
	private String LreporterName;
	
	
	private String LtargetDate;
	private String Lchecklistdtlcode;
	private String LcheckListname;
	private String LactivityRequired;
	
	private String conduct;
	
	ArrayList MonitorList=null;
	
	ArrayList prejoiningList=null;
	ArrayList LChkList=null;
	
	private String hiddencode;
	
	
	private String prejoiningListLength;
	private String LChkListLength;
	
	private String noData;
	
	
	private String myPage="";
	private String show="";
	private String selectname;
	private String modeLength="";
	private String totalRecords="";
	private String positionId="";
	private String candCode1="";
	private String candidateName1="";
	private String positionName="";
	private String offerFDate="";
	private String offerTDate="";
	private String joinFDate="";
	private String joinTDate="";
	
	private String targetTDate="";
	private String targetFDate="";
	private boolean enableFilterValue=false;
	private boolean showFilterValue=false;
	private String ChkSerch="";
	private String searchFlag="false";
	private boolean clearFlag=false;
	private String applyFilterFlag="false";
	
	
	public String getApplyFilterFlag() {
		return applyFilterFlag;
	}
	public void setApplyFilterFlag(String applyFilterFlag) {
		this.applyFilterFlag = applyFilterFlag;
	}
	public boolean isClearFlag() {
		return clearFlag;
	}
	public void setClearFlag(boolean clearFlag) {
		this.clearFlag = clearFlag;
	}

	public boolean isEnableFilterValue() {
		return enableFilterValue;
	}
	public void setEnableFilterValue(boolean enableFilterValue) {
		this.enableFilterValue = enableFilterValue;
	}
	public boolean isShowFilterValue() {
		return showFilterValue;
	}
	public void setShowFilterValue(boolean showFilterValue) {
		this.showFilterValue = showFilterValue;
	}
	public String getChkSerch() {
		return ChkSerch;
	}
	public void setChkSerch(String chkSerch) {
		ChkSerch = chkSerch;
	}
	public String getTargetTDate() {
		return targetTDate;
	}
	public void setTargetTDate(String targetTDate) {
		this.targetTDate = targetTDate;
	}
	public String getTargetFDate() {
		return targetFDate;
	}
	public void setTargetFDate(String targetFDate) {
		this.targetFDate = targetFDate;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getCandCode1() {
		return candCode1;
	}
	public void setCandCode1(String candCode1) {
		this.candCode1 = candCode1;
	}
	public String getCandidateName1() {
		return candidateName1;
	}
	public void setCandidateName1(String candidateName1) {
		this.candidateName1 = candidateName1;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getOfferFDate() {
		return offerFDate;
	}
	public void setOfferFDate(String offerFDate) {
		this.offerFDate = offerFDate;
	}
	public String getOfferTDate() {
		return offerTDate;
	}
	public void setOfferTDate(String offerTDate) {
		this.offerTDate = offerTDate;
	}
	public String getJoinFDate() {
		return joinFDate;
	}
	public void setJoinFDate(String joinFDate) {
		this.joinFDate = joinFDate;
	}
	public String getJoinTDate() {
		return joinTDate;
	}
	public void setJoinTDate(String joinTDate) {
		this.joinTDate = joinTDate;
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
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	
	public boolean isConductflag() {
		return conductflag;
	}
	public void setConductflag(boolean conductflag) {
		this.conductflag = conductflag;
	}
	
	public String getPrejoinCode() {
		return prejoinCode;
	}
	public void setPrejoinCode(String prejoinCode) {
		this.prejoinCode = prejoinCode;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getReportingTo() {
		return reportingTo;
	}
	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}
	public String getReportingName() {
		return reportingName;
	}
	public void setReportingName(String reportingName) {
		this.reportingName = reportingName;
	}
	public String getCheckListType() {
		return checkListType;
	}
	public void setCheckListType(String checkListType) {
		this.checkListType = checkListType;
	}
	public String getDupcheckListType() {
		return DupcheckListType;
	}
	public void setDupcheckListType(String dupcheckListType) {
		DupcheckListType = dupcheckListType;
	}
	public String getCheckListCode() {
		return checkListCode;
	}
	public void setCheckListCode(String checkListCode) {
		this.checkListCode = checkListCode;
	}
	public boolean isChecklistTable() {
		return checklistTable;
	}
	public void setChecklistTable(boolean checklistTable) {
		this.checklistTable = checklistTable;
	}
	public String getPrejoinListCode() {
		return prejoinListCode;
	}
	public void setPrejoinListCode(String prejoinListCode) {
		this.prejoinListCode = prejoinListCode;
	}
	public String getPrejoinListName() {
		return prejoinListName;
	}
	public void setPrejoinListName(String prejoinListName) {
		this.prejoinListName = prejoinListName;
	}
	public String getPrejoinTargetDate() {
		return prejoinTargetDate;
	}
	public void setPrejoinTargetDate(String prejoinTargetDate) {
		this.prejoinTargetDate = prejoinTargetDate;
	}
	public String getResponsiblePersonName() {
		return ResponsiblePersonName;
	}
	public void setResponsiblePersonName(String responsiblePersonName) {
		ResponsiblePersonName = responsiblePersonName;
	}
	public String getResponsiblePersonCode() {
		return ResponsiblePersonCode;
	}
	public void setResponsiblePersonCode(String responsiblePersonCode) {
		ResponsiblePersonCode = responsiblePersonCode;
	}
	public String getActivityRequired() {
		return activityRequired;
	}
	public void setActivityRequired(String activityRequired) {
		this.activityRequired = activityRequired;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getDupactivityRequired() {
		return DupactivityRequired;
	}
	public void setDupactivityRequired(String dupactivityRequired) {
		DupactivityRequired = dupactivityRequired;
	}
	public String getDupactivityStatus() {
		return DupactivityStatus;
	}
	public void setDupactivityStatus(String dupactivityStatus) {
		DupactivityStatus = dupactivityStatus;
	}
	public String getLcandidate() {
		return Lcandidate;
	}
	public void setLcandidate(String lcandidate) {
		Lcandidate = lcandidate;
	}
	public String getLcancode() {
		return Lcancode;
	}
	public void setLcancode(String lcancode) {
		Lcancode = lcancode;
	}
	public String getLoffercode() {
		return Loffercode;
	}
	public void setLoffercode(String loffercode) {
		Loffercode = loffercode;
	}
	public String getLreqcode() {
		return Lreqcode;
	}
	public void setLreqcode(String lreqcode) {
		Lreqcode = lreqcode;
	}
	public String getLreqName() {
		return LreqName;
	}
	public void setLreqName(String lreqName) {
		LreqName = lreqName;
	}
	public String getLposition() {
		return Lposition;
	}
	public void setLposition(String lposition) {
		Lposition = lposition;
	}
	public String getLofferstatus() {
		return Lofferstatus;
	}
	public void setLofferstatus(String lofferstatus) {
		Lofferstatus = lofferstatus;
	}
	public String getLofferDate() {
		return LofferDate;
	}
	public void setLofferDate(String lofferDate) {
		LofferDate = lofferDate;
	}
	public String getLjoinDate() {
		return LjoinDate;
	}
	public void setLjoinDate(String ljoinDate) {
		LjoinDate = ljoinDate;
	}
	public String getLreportTo() {
		return LreportTo;
	}
	public void setLreportTo(String lreportTo) {
		LreportTo = lreportTo;
	}
	public String getLreporterName() {
		return LreporterName;
	}
	public void setLreporterName(String lreporterName) {
		LreporterName = lreporterName;
	}
	public String getLstatus() {
		return Lstatus;
	}
	public void setLstatus(String lstatus) {
		Lstatus = lstatus;
	}
	public ArrayList getPrejoiningList() {
		return prejoiningList;
	}
	public void setPrejoiningList(ArrayList prejoiningList) {
		this.prejoiningList = prejoiningList;
	}
	public ArrayList getLChkList() {
		return LChkList;
	}
	public void setLChkList(ArrayList chkList) {
		LChkList = chkList;
	}
	
	public String getPrejoiningListLength() {
		return prejoiningListLength;
	}
	public void setPrejoiningListLength(String prejoiningListLength) {
		this.prejoiningListLength = prejoiningListLength;
	}
	public String getLChkListLength() {
		return LChkListLength;
	}
	public void setLChkListLength(String chkListLength) {
		LChkListLength = chkListLength;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getAppstatus() {
		return Appstatus;
	}
	public void setAppstatus(String appstatus) {
		Appstatus = appstatus;
	}
	public String getBgStatus() {
		return bgStatus;
	}
	public void setBgStatus(String bgStatus) {
		this.bgStatus = bgStatus;
	}
	public String getRespCode() {
		return RespCode;
	}
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getRespName() {
		return RespName;
	}
	public void setRespName(String respName) {
		RespName = respName;
	}
	public String getLtargetDate() {
		return LtargetDate;
	}
	public void setLtargetDate(String ltargetDate) {
		LtargetDate = ltargetDate;
	}
	public String getLcheckListname() {
		return LcheckListname;
	}
	public void setLcheckListname(String lcheckListname) {
		LcheckListname = lcheckListname;
	}
	public String getLactivityRequired() {
		return LactivityRequired;
	}
	public void setLactivityRequired(String lactivityRequired) {
		LactivityRequired = lactivityRequired;
	}
	public String getLchecklistdtlcode() {
		return Lchecklistdtlcode;
	}
	public void setLchecklistdtlcode(String lchecklistdtlcode) {
		Lchecklistdtlcode = lchecklistdtlcode;
	}
	public ArrayList getMonitorList() {
		return MonitorList;
	}
	public void setMonitorList(ArrayList monitorList) {
		MonitorList = monitorList;
	}
	public String getConduct() {
		return conduct;
	}
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}
	public String getButtonpanelcode() {
		return buttonpanelcode;
	}
	public void setButtonpanelcode(String buttonpanelcode) {
		this.buttonpanelcode = buttonpanelcode;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	
	
	
	
	
	
	

}
