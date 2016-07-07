package org.paradyne.bean.Recruitment;
/**
 * Author:Pradeep Kumar Sahoo
 * Date:31-07-2009
 */
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.BeanBase;

public class ManpwrReqsnAnalysis extends BeanBase {
	/*
	 *for filter option 
	 */
	private String hidSelectedReqName="";
	private String dataLen="";
	private String reqsnCode="";
	private String reqsnName="";
	private String positionId="";
	private String positionName="";
	private String reqsnDate="";
	private String reqsnStatus="";
	private String selectedReqName="";
	private String selectedReq="";
	private String frmDate="";
	private String tDate="";
	private String headerChk="";
	private String listChk="";
	private String selectedReqFlag="";
	private String itStatus="";
	private String dataLength="";
	private String editReqFlag="";
	private String editVal="";
	private ArrayList dispList=null;
	private String repType="";
	private String viewOnScrn="";
	private String genRep="";
	private String mraRepCode=""; 
	private HashMap hashMap=null;
	private String searchSetting="";
	private String reportFlag="false";
	private String radio1="false";
	private String radio2="false";
	private String radio3="false";
	private String radioFlag1="false";
	private String radioFlag2="false";
	private String radioFlag3="false";
	private String radioFlag="false";
	private String checkFlag="false";
	private String totRecord="";
	private String noDataReq="false";
	private String common="";
	private String viewReqsDate="";
	private String showReqsnFlag="false";
	private String backFlag="false";
	private String positionRadio="";
	private String reqsnRadio="";
	private String reqsnFlg="false";
	private String positionFlg="false";
	/*
	 * for column definition
	 */
	private String hidVacStatus="Y";
	private String hidReqsn="Y";
	private String hidReqsnDate="Y";
	private String hidReqByDate="Y";
	private String hidNoOfVac="Y";
	private String hidOverDue="Y";
	private String hidPosition="Y";
	private String hidReqsStatus="Y";
	private String hidApprvStatus="Y";
	private String chkReqsnDate="";
	private String chkReqsn="";
	private String chkNoOfVac="";
	private String chkReqByDate="";
	private String chkOverDue="";
	private String chkVacStat="";
	private String chkOpenVac="";
	private String chkPosition="";
	private String chkReqsStatus="";
	private String chkApprvStatus="";
	private String hidOpenVac="Y";
	private String dateChk="true";
	private String reqChk="true";
	private String closeChk="true";
	private String vacChk="true";
	private String overChk="true";
	private String openChk="true";
	private String positionChk="true";
	private String statusChk="true";
	private String apprvChk="true";
	private String hidTotalCtc="Y";	
	private String chkTotalCtc="";
	
	private String chkHiringMngr="";
	private String hidHiringMngr="Y";
	private String hiringMngrChk="true";
	
	private String chkApprvrName="";
	private String hidApprvrName="Y";
	private String apprvrNameChk="true";
	
	private String chkRecruitName="";
	private String hidRecruitName="Y";
	private String recruitNameChk="true";
	
	private String hdrHrngMngrFlag="";
	private String itrHrngMngrFlag="";
	
	private String hdrRecruitNameFlag="";
	private String itrRecruitNameFlag="";
	
	private String hdrApprvrNameFlag="";
	private String itrApprvrNameFlag="";
	
	private String totalCtcChk="true";
	private String hidClosedDate="Y";
	private String chkClosedDate="";
	private String closedDateChk="true";
	
	/*
	 * for sorting
	 */
	private String firstSort="";
	private String firstAsc="";
	private String firstDesc="";
	private String secSortBy="";
	private String secAsc="";
	private String secDesc="";
	private String thirdSort="";
	private String thirdAsc="";
	private String thirdDesc="";
	private String hidFirstAsc="A";
	private String hidFirstDesc="";
	private String hidSecAsc="A";
	private String hidSecDesc="";
	private String hidThirdAsc="A";
	private String hidThirdDesc="";
	private String firstRadio="false";
	private String secondRadio="false";
	private String thirdRadio="false";
	/*
	 * for advance filters
	 */
	private String advVacVal="";
	private String advOverDueVal="";
	private String advVacOpt="";
	private String advOverDueOpt="";
	private String settingName="";
		
	/**
 	 * for displaying in the screen
	 */
	private String itReqCode="";
	private String itReqName="";
	private String itReqDate="";
	private String itPosition="";
	private String itrReqsStatus="";
	private String irtApprvStatus="";
	private String itNoOfVac="";
	private String itReqByDate="";
	private String itOvrDue="";
	private String itVacStatus="";
	private String itOpenVac="";
	private String totalCtc="";
	private String closedDate="";
	private ArrayList list=null;
	private String hdrReqFlg="false";
	private String itrReqFlag="false";
	private String hdrNoOfVacFlg="false"; 
	private String itrNoOfVacFlg="false";
	private String hdrReqByDt="false";
	private String itrReqByFlgDt="false";
	private String hdrOvrFlg="false"; 
	private String itrOvrcFlg="false";
	private String hdrVacStatFlg="false";
	private String itrVacStatFlg="false";
	private String myPage="";
	private String scrnReqCode="";
	private String hdrOpenVac="false";
	private String itrOpenVac="false";
	private String itrCloseVacFlag="false";
	private String exportAllData="Y";
	private String exportAll="";
	private String exportAll1="";
	private String divId="";
	private String totalPage="";
	private String pageNoField="";
	private String aId="";
	private String noData="false";
	private String hdrPosFlag="false";
	private String itrPosFlag="false";
	private String hdrReqsStatFlag="false";
	private String itrReqsStatFlag="false";
	private String hdrApprvStatFlag="false";
	private String itrApprvStatFlag="false";
	private String advApprvStat="";
	private String hdrTotalCtcFlag="false";
	private String itrTotalCtcFlag="false";
	private String hdrClosedDateFlag="false";
	private String itrClosedDateFlag="false";
	private String itHrngMngr="";
	private String itApprvrName="";
	private String itRecruitName="";
	
	
	public String getScrnReqCode() {
		return scrnReqCode;
	}
	public void setScrnReqCode(String scrnReqCode) {
		this.scrnReqCode = scrnReqCode;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getAdvVacOpt() {
		return advVacOpt;
	}
	public void setAdvVacOpt(String advVacOpt) {
		this.advVacOpt = advVacOpt;
	}
	public String getAdvOverDueOpt() {
		return advOverDueOpt;
	}
	public void setAdvOverDueOpt(String advOverDueOpt) {
		this.advOverDueOpt = advOverDueOpt;
	}
	public String getFirstSort() {
		return firstSort;
	}
	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}
	public String getFirstAsc() {
		return firstAsc;
	}
	public void setFirstAsc(String firstAsc) {
		this.firstAsc = firstAsc;
	}
	public String getFirstDesc() {
		return firstDesc;
	}
	public void setFirstDesc(String firstDesc) {
		this.firstDesc = firstDesc;
	}
	public String getSecSortBy() {
		return secSortBy;
	}
	public void setSecSortBy(String secSortBy) {
		this.secSortBy = secSortBy;
	}
	public String getSecAsc() {
		return secAsc;
	}
	public void setSecAsc(String secAsc) {
		this.secAsc = secAsc;
	}
	public String getSecDesc() {
		return secDesc;
	}
	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}
	public String getThirdSort() {
		return thirdSort;
	}
	public void setThirdSort(String thirdSort) {
		this.thirdSort = thirdSort;
	}
	public String getThirdAsc() {
		return thirdAsc;
	}
	public void setThirdAsc(String thirdAsc) {
		this.thirdAsc = thirdAsc;
	}
	public String getThirdDesc() {
		return thirdDesc;
	}
	public void setThirdDesc(String thirdDesc) {
		this.thirdDesc = thirdDesc;
	}
	public String getAdvVacVal() {
		return advVacVal;
	}
	public void setAdvVacVal(String advVacVal) {
		this.advVacVal = advVacVal;
	}
	public String getAdvOverDueVal() {
		return advOverDueVal;
	}
	public void setAdvOverDueVal(String advOverDueVal) {
		this.advOverDueVal = advOverDueVal;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getEditReqFlag() {
		return editReqFlag;
	}
	public void setEditReqFlag(String editReqFlag) {
		this.editReqFlag = editReqFlag;
	}
	public ArrayList getDispList() {
		return dispList;
	}
	public void setDispList(ArrayList dispList) {
		this.dispList = dispList;
	}
	public String getReqsnCode() {
		return reqsnCode;
	}
	public void setReqsnCode(String reqsnCode) {
		this.reqsnCode = reqsnCode;
	}
	public String getReqsnName() {
		return reqsnName;
	}
	public void setReqsnName(String reqsnName) {
		this.reqsnName = reqsnName;
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
	public String getReqsnDate() {
		return reqsnDate;
	}
	public void setReqsnDate(String reqsnDate) {
		this.reqsnDate = reqsnDate;
	}
	public String getReqsnStatus() {
		return reqsnStatus;
	}
	public void setReqsnStatus(String reqsnStatus) {
		this.reqsnStatus = reqsnStatus;
	}
	public String getSelectedReqName() {
		return selectedReqName;
	}
	public void setSelectedReqName(String selectedReqName) {
		this.selectedReqName = selectedReqName;
	}
	public String getSelectedReq() {
		return selectedReq;
	}
	public void setSelectedReq(String selectedReq) {
		this.selectedReq = selectedReq;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getTDate() {
		return tDate;
	}
	public void setTDate(String date) {
		tDate = date;
	}
	public String getHeaderChk() {
		return headerChk;
	}
	public void setHeaderChk(String headerChk) {
		this.headerChk = headerChk;
	}
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
	public String getListChk() {
		return listChk;
	}
	public void setListChk(String listChk) {
		this.listChk = listChk;
	}
	public String getItReqDate() {
		return itReqDate;
	}
	public void setItReqDate(String itReqDate) {
		this.itReqDate = itReqDate;
	}
	public String getSelectedReqFlag() {
		return selectedReqFlag;
	}
	public void setSelectedReqFlag(String selectedReqFlag) {
		this.selectedReqFlag = selectedReqFlag;
	}
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}
	public String getItReqCode() {
		return itReqCode;
	}
	public void setItReqCode(String itReqCode) {
		this.itReqCode = itReqCode;
	}
	public String getHidVacStatus() {
		return hidVacStatus;
	}
	public void setHidVacStatus(String hidVacStatus) {
		this.hidVacStatus = hidVacStatus;
	}
	public String getHidReqsn() {
		return hidReqsn;
	}
	public void setHidReqsn(String hidReqsn) {
		this.hidReqsn = hidReqsn;
	}
	public String getHidReqsnDate() {
		return hidReqsnDate;
	}
	public void setHidReqsnDate(String hidReqsnDate) {
		this.hidReqsnDate = hidReqsnDate;
	}
	public String getHidReqByDate() {
		return hidReqByDate;
	}
	public void setHidReqByDate(String hidReqByDate) {
		this.hidReqByDate = hidReqByDate;
	}
	public String getHidNoOfVac() {
		return hidNoOfVac;
	}
	public void setHidNoOfVac(String hidNoOfVac) {
		this.hidNoOfVac = hidNoOfVac;
	}
	public String getHidOverDue() {
		return hidOverDue;
	}
	public void setHidOverDue(String hidOverDue) {
		this.hidOverDue = hidOverDue;
	}
	public String getChkReqsnDate() {
		return chkReqsnDate;
	}
	public void setChkReqsnDate(String chkReqsnDate) {
		this.chkReqsnDate = chkReqsnDate;
	}
	public String getChkReqsn() {
		return chkReqsn;
	}
	public void setChkReqsn(String chkReqsn) {
		this.chkReqsn = chkReqsn;
	}
	public String getChkNoOfVac() {
		return chkNoOfVac;
	}
	public void setChkNoOfVac(String chkNoOfVac) {
		this.chkNoOfVac = chkNoOfVac;
	}
	public String getChkReqByDate() {
		return chkReqByDate;
	}
	public void setChkReqByDate(String chkReqByDate) {
		this.chkReqByDate = chkReqByDate;
	}
	public String getChkOverDue() {
		return chkOverDue;
	}
	public void setChkOverDue(String chkOverDue) {
		this.chkOverDue = chkOverDue;
	}
	public String getChkVacStat() {
		return chkVacStat;
	}
	public void setChkVacStat(String chkVacStat) {
		this.chkVacStat = chkVacStat;
	}
	
	public String getViewOnScrn() {
		return viewOnScrn;
	}
	public void setViewOnScrn(String viewOnScrn) {
		this.viewOnScrn = viewOnScrn;
	}
	public String getGenRep() {
		return genRep;
	}
	public void setGenRep(String genRep) {
		this.genRep = genRep;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getHidFirstAsc() {
		return hidFirstAsc;
	}
	public void setHidFirstAsc(String hidFirstAsc) {
		this.hidFirstAsc = hidFirstAsc;
	}
	public String getHidFirstDesc() {
		return hidFirstDesc;
	}
	public void setHidFirstDesc(String hidFirstDesc) {
		this.hidFirstDesc = hidFirstDesc;
	}
	public String getHidSecAsc() {
		return hidSecAsc;
	}
	public void setHidSecAsc(String hidSecAsc) {
		this.hidSecAsc = hidSecAsc;
	}
	public String getHidSecDesc() {
		return hidSecDesc;
	}
	public void setHidSecDesc(String hidSecDesc) {
		this.hidSecDesc = hidSecDesc;
	}
	public String getHidThirdAsc() {
		return hidThirdAsc;
	}
	public void setHidThirdAsc(String hidThirdAsc) {
		this.hidThirdAsc = hidThirdAsc;
	}
	public String getHidThirdDesc() {
		return hidThirdDesc;
	}
	public void setHidThirdDesc(String hidThirdDesc) {
		this.hidThirdDesc = hidThirdDesc;
	}
	public String getMraRepCode() {
		return mraRepCode;
	}
	public void setMraRepCode(String mraRepCode) {
		this.mraRepCode = mraRepCode;
	}
	public HashMap getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}
	public String getSearchSetting() {
		return searchSetting;
	}
	public void setSearchSetting(String searchSetting) {
		this.searchSetting = searchSetting;
	}
	public String getReportFlag() {
		return reportFlag;
	}
	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}
	public String getRadio1() {
		return radio1;
	}
	public void setRadio1(String radio1) {
		this.radio1 = radio1;
	}
	public String getRadio2() {
		return radio2;
	}
	public void setRadio2(String radio2) {
		this.radio2 = radio2;
	}
	public String getRadio3() {
		return radio3;
	}
	public void setRadio3(String radio3) {
		this.radio3 = radio3;
	}
	public String getRadioFlag1() {
		return radioFlag1;
	}
	public void setRadioFlag1(String radioFlag1) {
		this.radioFlag1 = radioFlag1;
	}
	public String getRadioFlag2() {
		return radioFlag2;
	}
	public void setRadioFlag2(String radioFlag2) {
		this.radioFlag2 = radioFlag2;
	}
	public String getRadioFlag3() {
		return radioFlag3;
	}
	public void setRadioFlag3(String radioFlag3) {
		this.radioFlag3 = radioFlag3;
	}
	public String getRadioFlag() {
		return radioFlag;
	}
	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getItNoOfVac() {
		return itNoOfVac;
	}
	public void setItNoOfVac(String itNoOfVac) {
		this.itNoOfVac = itNoOfVac;
	}
	public String getItReqByDate() {
		return itReqByDate;
	}
	public void setItReqByDate(String itReqByDate) {
		this.itReqByDate = itReqByDate;
	}
	public String getItOvrDue() {
		return itOvrDue;
	}
	public void setItOvrDue(String itOvrDue) {
		this.itOvrDue = itOvrDue;
	}
	public String getItVacStatus() {
		return itVacStatus;
	}
	public void setItVacStatus(String itVacStatus) {
		this.itVacStatus = itVacStatus;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getHdrReqFlg() {
		return hdrReqFlg;
	}
	public void setHdrReqFlg(String hdrReqFlg) {
		this.hdrReqFlg = hdrReqFlg;
	}
	public String getItrReqFlag() {
		return itrReqFlag;
	}
	public void setItrReqFlag(String itrReqFlag) {
		this.itrReqFlag = itrReqFlag;
	}
	public String getHdrNoOfVacFlg() {
		return hdrNoOfVacFlg;
	}
	public void setHdrNoOfVacFlg(String hdrNoOfVacFlg) {
		this.hdrNoOfVacFlg = hdrNoOfVacFlg;
	}
	public String getItrNoOfVacFlg() {
		return itrNoOfVacFlg;
	}
	public void setItrNoOfVacFlg(String itrNoOfVacFlg) {
		this.itrNoOfVacFlg = itrNoOfVacFlg;
	}
	public String getHdrReqByDt() {
		return hdrReqByDt;
	}
	public void setHdrReqByDt(String hdrReqByDt) {
		this.hdrReqByDt = hdrReqByDt;
	}
	public String getItrReqByFlgDt() {
		return itrReqByFlgDt;
	}
	public void setItrReqByFlgDt(String itrReqByFlgDt) {
		this.itrReqByFlgDt = itrReqByFlgDt;
	}
	public String getHdrOvrFlg() {
		return hdrOvrFlg;
	}
	public void setHdrOvrFlg(String hdrOvrFlg) {
		this.hdrOvrFlg = hdrOvrFlg;
	}
	public String getItrOvrcFlg() {
		return itrOvrcFlg;
	}
	public void setItrOvrcFlg(String itrOvrcFlg) {
		this.itrOvrcFlg = itrOvrcFlg;
	}
	public String getHdrVacStatFlg() {
		return hdrVacStatFlg;
	}
	public void setHdrVacStatFlg(String hdrVacStatFlg) {
		this.hdrVacStatFlg = hdrVacStatFlg;
	}
	public String getItrVacStatFlg() {
		return itrVacStatFlg;
	}
	public void setItrVacStatFlg(String itrVacStatFlg) {
		this.itrVacStatFlg = itrVacStatFlg;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getItOpenVac() {
		return itOpenVac;
	}
	public void setItOpenVac(String itOpenVac) {
		this.itOpenVac = itOpenVac;
	}
	public String getEditVal() {
		return editVal;
	}
	public void setEditVal(String editVal) {
		this.editVal = editVal;
	}
	public String getChkOpenVac() {
		return chkOpenVac;
	}
	public void setChkOpenVac(String chkOpenVac) {
		this.chkOpenVac = chkOpenVac;
	}
	public String getHidOpenVac() {
		return hidOpenVac;
	}
	public void setHidOpenVac(String hidOpenVac) {
		this.hidOpenVac = hidOpenVac;
	}
	public String getHdrOpenVac() {
		return hdrOpenVac;
	}
	public void setHdrOpenVac(String hdrOpenVac) {
		this.hdrOpenVac = hdrOpenVac;
	}
	public String getItrOpenVac() {
		return itrOpenVac;
	}
	public void setItrOpenVac(String itrOpenVac) {
		this.itrOpenVac = itrOpenVac;
	}
	
	public String getItrCloseVacFlag() {
		return itrCloseVacFlag;
	}
	public void setItrCloseVacFlag(String itrCloseVacFlag) {
		this.itrCloseVacFlag = itrCloseVacFlag;
	}
	public String getExportAllData() {
		return exportAllData;
	}
	public void setExportAllData(String exportAllData) {
		this.exportAllData = exportAllData;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getExportAll1() {
		return exportAll1;
	}
	public void setExportAll1(String exportAll1) {
		this.exportAll1 = exportAll1;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getFirstRadio() {
		return firstRadio;
	}
	public void setFirstRadio(String firstRadio) {
		this.firstRadio = firstRadio;
	}
	public String getSecondRadio() {
		return secondRadio;
	}
	public void setSecondRadio(String secondRadio) {
		this.secondRadio = secondRadio;
	}
	public String getThirdRadio() {
		return thirdRadio;
	}
	public void setThirdRadio(String thirdRadio) {
		this.thirdRadio = thirdRadio;
	}
	public String getDateChk() {
		return dateChk;
	}
	public void setDateChk(String dateChk) {
		this.dateChk = dateChk;
	}
	
	public String getCloseChk() {
		return closeChk;
	}
	public void setCloseChk(String closeChk) {
		this.closeChk = closeChk;
	}
	public String getVacChk() {
		return vacChk;
	}
	public void setVacChk(String vacChk) {
		this.vacChk = vacChk;
	}
	public String getOverChk() {
		return overChk;
	}
	public void setOverChk(String overChk) {
		this.overChk = overChk;
	}
	public String getOpenChk() {
		return openChk;
	}
	public void setOpenChk(String openChk) {
		this.openChk = openChk;
	}
	public String getReqChk() {
		return reqChk;
	}
	public void setReqChk(String reqChk) {
		this.reqChk = reqChk;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getAId() {
		return aId;
	}
	public void setAId(String id) {
		aId = id;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getTotRecord() {
		return totRecord;
	}
	public void setTotRecord(String totRecord) {
		this.totRecord = totRecord;
	}
	public String getHidPosition() {
		return hidPosition;
	}
	public void setHidPosition(String hidPosition) {
		this.hidPosition = hidPosition;
	}
	public String getHidReqsStatus() {
		return hidReqsStatus;
	}
	public void setHidReqsStatus(String hidReqsStatus) {
		this.hidReqsStatus = hidReqsStatus;
	}
	public String getHidApprvStatus() {
		return hidApprvStatus;
	}
	public void setHidApprvStatus(String hidApprvStatus) {
		this.hidApprvStatus = hidApprvStatus;
	}
	public String getChkPosition() {
		return chkPosition;
	}
	public void setChkPosition(String chkPosition) {
		this.chkPosition = chkPosition;
	}
	public String getChkReqsStatus() {
		return chkReqsStatus;
	}
	public void setChkReqsStatus(String chkReqsStatus) {
		this.chkReqsStatus = chkReqsStatus;
	}
	public String getChkApprvStatus() {
		return chkApprvStatus;
	}
	public void setChkApprvStatus(String chkApprvStatus) {
		this.chkApprvStatus = chkApprvStatus;
	}
	public String getPositionChk() {
		return positionChk;
	}
	public void setPositionChk(String positionChk) {
		this.positionChk = positionChk;
	}
	public String getStatusChk() {
		return statusChk;
	}
	public void setStatusChk(String statusChk) {
		this.statusChk = statusChk;
	}
	public String getApprvChk() {
		return apprvChk;
	}
	public void setApprvChk(String apprvChk) {
		this.apprvChk = apprvChk;
	}
	public String getItrReqsStatus() {
		return itrReqsStatus;
	}
	public void setItrReqsStatus(String itrReqsStatus) {
		this.itrReqsStatus = itrReqsStatus;
	}
	public String getIrtApprvStatus() {
		return irtApprvStatus;
	}
	public void setIrtApprvStatus(String irtApprvStatus) {
		this.irtApprvStatus = irtApprvStatus;
	}
	public String getHdrPosFlag() {
		return hdrPosFlag;
	}
	public void setHdrPosFlag(String hdrPosFlag) {
		this.hdrPosFlag = hdrPosFlag;
	}
	public String getItrPosFlag() {
		return itrPosFlag;
	}
	public void setItrPosFlag(String itrPosFlag) {
		this.itrPosFlag = itrPosFlag;
	}
	public String getHdrReqsStatFlag() {
		return hdrReqsStatFlag;
	}
	public void setHdrReqsStatFlag(String hdrReqsStatFlag) {
		this.hdrReqsStatFlag = hdrReqsStatFlag;
	}
	public String getItrReqsStatFlag() {
		return itrReqsStatFlag;
	}
	public void setItrReqsStatFlag(String itrReqsStatFlag) {
		this.itrReqsStatFlag = itrReqsStatFlag;
	}
	public String getHdrApprvStatFlag() {
		return hdrApprvStatFlag;
	}
	public void setHdrApprvStatFlag(String hdrApprvStatFlag) {
		this.hdrApprvStatFlag = hdrApprvStatFlag;
	}
	public String getItrApprvStatFlag() {
		return itrApprvStatFlag;
	}
	public void setItrApprvStatFlag(String itrApprvStatFlag) {
		this.itrApprvStatFlag = itrApprvStatFlag;
	}
	public String getAdvApprvStat() {
		return advApprvStat;
	}
	public void setAdvApprvStat(String advApprvStat) {
		this.advApprvStat = advApprvStat;
	}
	public String getNoDataReq() {
		return noDataReq;
	}
	public void setNoDataReq(String noDataReq) {
		this.noDataReq = noDataReq;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getViewReqsDate() {
		return viewReqsDate;
	}
	public void setViewReqsDate(String viewReqsDate) {
		this.viewReqsDate = viewReqsDate;
	}
	public String getDataLen() {
		return dataLen;
	}
	public void setDataLen(String dataLen) {
		this.dataLen = dataLen;
	}
	public String getHidTotalCtc() {
		return hidTotalCtc;
	}
	public void setHidTotalCtc(String hidTotalCtc) {
		this.hidTotalCtc = hidTotalCtc;
	}
	public String getChkTotalCtc() {
		return chkTotalCtc;
	}
	public void setChkTotalCtc(String chkTotalCtc) {
		this.chkTotalCtc = chkTotalCtc;
	}
	public String getTotalCtcChk() {
		return totalCtcChk;
	}
	public void setTotalCtcChk(String totalCtcChk) {
		this.totalCtcChk = totalCtcChk;
	}
	public String getHidClosedDate() {
		return hidClosedDate;
	}
	public void setHidClosedDate(String hidClosedDate) {
		this.hidClosedDate = hidClosedDate;
	}
	public String getChkClosedDate() {
		return chkClosedDate;
	}
	public void setChkClosedDate(String chkClosedDate) {
		this.chkClosedDate = chkClosedDate;
	}
	public String getClosedDateChk() {
		return closedDateChk;
	}
	public void setClosedDateChk(String closedDateChk) {
		this.closedDateChk = closedDateChk;
	}
	public String getTotalCtc() {
		return totalCtc;
	}
	public void setTotalCtc(String totalCtc) {
		this.totalCtc = totalCtc;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getHdrTotalCtcFlag() {
		return hdrTotalCtcFlag;
	}
	public void setHdrTotalCtcFlag(String hdrTotalCtcFlag) {
		this.hdrTotalCtcFlag = hdrTotalCtcFlag;
	}
	public String getItrTotalCtcFlag() {
		return itrTotalCtcFlag;
	}
	public void setItrTotalCtcFlag(String itrTotalCtcFlag) {
		this.itrTotalCtcFlag = itrTotalCtcFlag;
	}
	public String getHdrClosedDateFlag() {
		return hdrClosedDateFlag;
	}
	public void setHdrClosedDateFlag(String hdrClosedDateFlag) {
		this.hdrClosedDateFlag = hdrClosedDateFlag;
	}
	public String getItrClosedDateFlag() {
		return itrClosedDateFlag;
	}
	public void setItrClosedDateFlag(String itrClosedDateFlag) {
		this.itrClosedDateFlag = itrClosedDateFlag;
	}
	public String getShowReqsnFlag() {
		return showReqsnFlag;
	}
	public void setShowReqsnFlag(String showReqsnFlag) {
		this.showReqsnFlag = showReqsnFlag;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getPositionRadio() {
		return positionRadio;
	}
	public void setPositionRadio(String positionRadio) {
		this.positionRadio = positionRadio;
	}
	public String getReqsnRadio() {
		return reqsnRadio;
	}
	public void setReqsnRadio(String reqsnRadio) {
		this.reqsnRadio = reqsnRadio;
	}
	public String getReqsnFlg() {
		return reqsnFlg;
	}
	public void setReqsnFlg(String reqsnFlg) {
		this.reqsnFlg = reqsnFlg;
	}
	public String getPositionFlg() {
		return positionFlg;
	}
	public void setPositionFlg(String positionFlg) {
		this.positionFlg = positionFlg;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	}
	public String getChkHiringMngr() {
		return chkHiringMngr;
	}
	public void setChkHiringMngr(String chkHiringMngr) {
		this.chkHiringMngr = chkHiringMngr;
	}
	public String getHidHiringMngr() {
		return hidHiringMngr;
	}
	public void setHidHiringMngr(String hidHiringMngr) {
		this.hidHiringMngr = hidHiringMngr;
	}
	public String getHiringMngrChk() {
		return hiringMngrChk;
	}
	public void setHiringMngrChk(String hiringMngrChk) {
		this.hiringMngrChk = hiringMngrChk;
	}
	public String getChkApprvrName() {
		return chkApprvrName;
	}
	public void setChkApprvrName(String chkApprvrName) {
		this.chkApprvrName = chkApprvrName;
	}
	public String getHidApprvrName() {
		return hidApprvrName;
	}
	public void setHidApprvrName(String hidApprvrName) {
		this.hidApprvrName = hidApprvrName;
	}
	public String getApprvrNameChk() {
		return apprvrNameChk;
	}
	public void setApprvrNameChk(String apprvrNameChk) {
		this.apprvrNameChk = apprvrNameChk;
	}
	public String getChkRecruitName() {
		return chkRecruitName;
	}
	public void setChkRecruitName(String chkRecruitName) {
		this.chkRecruitName = chkRecruitName;
	}
	public String getHidRecruitName() {
		return hidRecruitName;
	}
	public void setHidRecruitName(String hidRecruitName) {
		this.hidRecruitName = hidRecruitName;
	}
	public String getRecruitNameChk() {
		return recruitNameChk;
	}
	public void setRecruitNameChk(String recruitNameChk) {
		this.recruitNameChk = recruitNameChk;
	}
	public String getHdrHrngMngrFlag() {
		return hdrHrngMngrFlag;
	}
	public void setHdrHrngMngrFlag(String hdrHrngMngrFlag) {
		this.hdrHrngMngrFlag = hdrHrngMngrFlag;
	}
	public String getItrHrngMngrFlag() {
		return itrHrngMngrFlag;
	}
	public void setItrHrngMngrFlag(String itrHrngMngrFlag) {
		this.itrHrngMngrFlag = itrHrngMngrFlag;
	}
	public String getHdrRecruitNameFlag() {
		return hdrRecruitNameFlag;
	}
	public void setHdrRecruitNameFlag(String hdrRecruitNameFlag) {
		this.hdrRecruitNameFlag = hdrRecruitNameFlag;
	}
	public String getItrRecruitNameFlag() {
		return itrRecruitNameFlag;
	}
	public void setItrRecruitNameFlag(String itrRecruitNameFlag) {
		this.itrRecruitNameFlag = itrRecruitNameFlag;
	}
	public String getHdrApprvrNameFlag() {
		return hdrApprvrNameFlag;
	}
	public void setHdrApprvrNameFlag(String hdrApprvrNameFlag) {
		this.hdrApprvrNameFlag = hdrApprvrNameFlag;
	}
	public String getItrApprvrNameFlag() {
		return itrApprvrNameFlag;
	}
	public void setItrApprvrNameFlag(String itrApprvrNameFlag) {
		this.itrApprvrNameFlag = itrApprvrNameFlag;
	}
	public String getItHrngMngr() {
		return itHrngMngr;
	}
	public void setItHrngMngr(String itHrngMngr) {
		this.itHrngMngr = itHrngMngr;
	}
	public String getItRecruitName() {
		return itRecruitName;
	}
	public void setItRecruitName(String itRecruitName) {
		this.itRecruitName = itRecruitName;
	}
	public String getItApprvrName() {
		return itApprvrName;
	}
	public void setItApprvrName(String itApprvrName) {
		this.itApprvrName = itApprvrName;
	}
	
}
