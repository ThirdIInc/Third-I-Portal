package org.paradyne.bean.Recruitment;
import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:07-04-2009
 *
 */
public class ReqsnMisReport extends BeanBase {
private String pageNoField="";
private String totalPage="";
private String myPage="";
private String viewOnScrn="false";
private String reportFlag="false";
private String dateFlag="false";
private String scrnFlg="false"; 
private String radioFlag="false";	
private HashMap hashMap=null;
private String mraRepCode="";	
private String dataLen="";
private String headerChk="";
private String editVal="";
private String reqCode="";
private String reqName="";
private String fDate="";
private String tDate="";
private String appStatus="";
private String reqStatus="";
private String hiringMgrId="";
private String hiringMgr="";
private String reqDate="";
private String empToken="";
private String repType="";
private String fromTotRec="";
private String toTotRec="";
private String hdPage="";  
private String itReqName1="";
private String itReqName="";
private String itPosition="";
private String itStatus="";  
private String hidReportRadio="";
private String hidReportView="checked";
private String itDiv="";
private String itBranch="";
private String itDept="";
private String itApprStatus="";   
private String exportAll1="";
private String exportAllData="Y";
private String exportAll="";
private String itHirmanger="";
private String itCandiName="";
private String itJobDecName="";
private String itJobDec="";  

private String itRoleperson="";
private String itSpecReq="";
private String itPerReq="";
private String itVacanCompn="";

private String itVacexpMin="";
private String itVacexpMax="";
private String itVacType="";
private String itVacanContract="";
private String editFlag="false";
private String itVacConType="";
private String itReqApprCode="";
private String itReqLevel="";
private String itReqType="";  
private String itReqCode="";
private String itReqCode1="";
private String itReqDate="";
private String itReqDate1="";
private String itReqType1="";
private String noOfVac="";
private String vacDate="";
private String qualType="";
private String qualName="";
private String qualLvl="";
private String spl="";
private String cutOff="";
private String sel="";
private String skillType="";
private String skillName="";
private String skillExp="";
private String skillSel="";
private String domType="";
private String domName="";
private String domExp="";
private String domSel="";
private String certType="";
private String certName="";
private String certIssueBy="";
private String certValid="";
private String certOption="";
private String apprvName="";
private String apprvDate="";
private String apprvRem="";
private ArrayList apprvList=null;
private ArrayList vacList=null;
private ArrayList qualList=null;
private ArrayList skillList=null;
private ArrayList domList=null;
private ArrayList certList=null;
private String apprvFlag="false";
ArrayList  reqList = new ArrayList();
private String divCode="";
private String divName="";
private String brnName="";
private String brnCode="";
private String deptCode="";
private String deptName="";
private String posCode="";
private String posName="";
private String dateFilter="";
private String frmDate="";
private String toDate="";
private String reportView="";
private String settingName="";
private String reportType="";
private String searchSetting="";
private String editReqFlag="false";
private String selectedReqName="";
private String selectedReq="";
ArrayList dispList=null;
private String dataLength="";
private String listChk="";
private String selectedReqFlag="";
private String noDataReq="false";
private String itPostition="";
private String firstSort="";
private String firstAsc="";
private String radio1="";
private String firstDesc="";
private String radioFlag1="false";
private String secondSort="";
private String radio2="";
private String secAsc="";
private String secDesc="";
private String radioFlag2="false";
private String thirdSort="";
private String thirdAsc="";
private String thirdDesc="";
private String radio3="";
private String radioFlag3="false";
private String hidFirstAsc="A";
private String hidFirstDesc="";
private String hidSecAsc="A";
private String hidSecDesc="";
private String hidThirdAsc="A";
private String hidThirdDesc="";
private String firstRadio="false";
private String secondRadio="false";
private String thirdRadio="false";
private String advVacOpt="";
private String advVacVal="";
private String reqsStatus="";
private String qualFlag="false";
private String skillFlag="false";
private String domFlag="false";
private String certFlag="false";
private String divId="";
private String aId="";
private String backFlag="";
 
public String getDivId() {
	return divId;
}
public void setDivId(String divId) {
	this.divId = divId;
}
public String getAId() {
	return aId;
}
public void setAId(String id) {
	aId = id;
}
public String getBackFlag() {
	return backFlag;
}
public void setBackFlag(String backFlag) {
	this.backFlag = backFlag;
}
public String getReqsStatus() {
	return reqsStatus;
}
public void setReqsStatus(String reqsStatus) {
	this.reqsStatus = reqsStatus;
}
public String getAdvVacOpt() {
	return advVacOpt;
}
public void setAdvVacOpt(String advVacOpt) {
	this.advVacOpt = advVacOpt;
}
public String getAdvVacVal() {
	return advVacVal;
}
public void setAdvVacVal(String advVacVal) {
	this.advVacVal = advVacVal;
}
public String getDivCode() {
	return divCode;
}
public String getReportView() {
	return reportView;
}
public void setReportView(String reportView) {
	this.reportView = reportView;
}
public String getSettingName() {
	return settingName;
}
public void setSettingName(String settingName) {
	this.settingName = settingName;
}
public String getReportType() {
	return reportType;
}
public void setReportType(String reportType) {
	this.reportType = reportType;
}
public String getSearchSetting() {
	return searchSetting;
}
public void setSearchSetting(String searchSetting) {
	this.searchSetting = searchSetting;
}
public void setDivCode(String divCode) {
	this.divCode = divCode;
}
public String getDivName() {
	return divName;
}
public void setDivName(String divName) {
	this.divName = divName;
}
public String getBrnName() {
	return brnName;
}
public void setBrnName(String brnName) {
	this.brnName = brnName;
}
public String getBrnCode() {
	return brnCode;
}
public void setBrnCode(String brnCode) {
	this.brnCode = brnCode;
}
public String getDeptCode() {
	return deptCode;
}
public void setDeptCode(String deptCode) {
	this.deptCode = deptCode;
}
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public String getPosCode() {
	return posCode;
}
public void setPosCode(String posCode) {
	this.posCode = posCode;
}
public String getPosName() {
	return posName;
}
public void setPosName(String posName) {
	this.posName = posName;
}
public String getDateFilter() {
	return dateFilter;
}
public void setDateFilter(String dateFilter) {
	this.dateFilter = dateFilter;
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
public ArrayList getReqList() {
	return reqList;
}
public void setReqList(ArrayList reqList) {
	this.reqList = reqList;
}
public String getItReqCode() {
	return itReqCode;
}
public void setItReqCode(String itReqCode) {
	this.itReqCode = itReqCode;
}
public String getItReqDate() {
	return itReqDate;
}
public void setItReqDate(String itReqDate) {
	this.itReqDate = itReqDate;
}
public String getItPostition() {
	return itPostition;
}
public void setItPostition(String itPostition) {
	this.itPostition = itPostition;
}
public String getItStatus() {
	return itStatus;
}
public void setItStatus(String itStatus) {
	this.itStatus = itStatus;
}
public String getHdPage() {
	return hdPage;
}
public void setHdPage(String hdPage) {
	this.hdPage = hdPage;
}
public String getFromTotRec() {
	return fromTotRec;
}
public void setFromTotRec(String fromTotRec) {
	this.fromTotRec = fromTotRec;
}
public String getToTotRec() {
	return toTotRec;
}
public void setToTotRec(String toTotRec) {
	this.toTotRec = toTotRec;
}
public String getReqDate() {
	return reqDate;
}
public void setReqDate(String reqDate) {
	
	this.reqDate = reqDate;
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
public String getFDate() {
	return fDate;
}
public void setFDate(String date) {
	fDate = date;
}
public String getTDate() {
	return tDate;
}
public void setTDate(String date) {
	tDate = date;
}
public String getAppStatus() {
	return appStatus;
}
public void setAppStatus(String appStatus) {
	this.appStatus = appStatus;
}
public String getReqStatus() {
	return reqStatus;
}
public void setReqStatus(String reqStatus) {
	this.reqStatus = reqStatus;
}
public String getHiringMgrId() {
	return hiringMgrId;
}
public void setHiringMgrId(String hiringMgrId) {
	this.hiringMgrId = hiringMgrId;
}
public String getHiringMgr() {
	return hiringMgr;
}
public void setHiringMgr(String hiringMgr) {
	this.hiringMgr = hiringMgr;
}
public String getEmpToken() {
	return empToken;
}
public void setEmpToken(String empToken) {
	this.empToken = empToken;
}
public String getRepType() {
	return repType;
}
public void setRepType(String repType) {
	this.repType = repType;
}
public String getItReqName() {
	return itReqName;
}
public void setItReqName(String itReqName) {
	this.itReqName = itReqName;
}
public String getItDiv() {
	return itDiv;
}
public void setItDiv(String itDiv) {
	this.itDiv = itDiv;
}
public String getItBranch() {
	return itBranch;
}
public void setItBranch(String itBranch) {
	this.itBranch = itBranch;
}
public String getItDept() {
	return itDept;
}
public void setItDept(String itDept) {
	this.itDept = itDept;
}
public String getItApprStatus() {
	return itApprStatus;
}
public void setItApprStatus(String itApprStatus) {
	this.itApprStatus = itApprStatus;
}
public String getItHirmanger() {
	return itHirmanger;
}
public void setItHirmanger(String itHirmanger) {
	this.itHirmanger = itHirmanger;
}
public String getItCandiName() {
	return itCandiName;
}
public void setItCandiName(String itCandiName) {
	this.itCandiName = itCandiName;
}
public String getItJobDecName() {
	return itJobDecName;
}
public void setItJobDecName(String itJobDecName) {
	this.itJobDecName = itJobDecName;
}
public String getItJobDec() {
	return itJobDec;
}
public void setItJobDec(String itJobDec) {
	this.itJobDec = itJobDec;
}
public String getItRoleperson() {
	return itRoleperson;
}
public void setItRoleperson(String itRoleperson) {
	this.itRoleperson = itRoleperson;
}
public String getItSpecReq() {
	return itSpecReq;
}
public void setItSpecReq(String itSpecReq) {
	this.itSpecReq = itSpecReq;
}
public String getItPerReq() {
	return itPerReq;
}
public void setItPerReq(String itPerReq) {
	this.itPerReq = itPerReq;
}
public String getItVacanCompn() {
	return itVacanCompn;
}
public void setItVacanCompn(String itVacanCompn) {
	this.itVacanCompn = itVacanCompn;
}
public String getItVacexpMin() {
	return itVacexpMin;
}
public void setItVacexpMin(String itVacexpMin) {
	this.itVacexpMin = itVacexpMin;
}
public String getItVacexpMax() {
	return itVacexpMax;
}
public void setItVacexpMax(String itVacexpMax) {
	this.itVacexpMax = itVacexpMax;
}
public String getItVacType() {
	return itVacType;
}
public void setItVacType(String itVacType) {
	this.itVacType = itVacType;
}
public String getItVacanContract() {
	return itVacanContract;
}
public void setItVacanContract(String itVacanContract) {
	this.itVacanContract = itVacanContract;
}
public String getItVacConType() {
	return itVacConType;
}
public void setItVacConType(String itVacConType) {
	this.itVacConType = itVacConType;
}
public String getItReqApprCode() {
	return itReqApprCode;
}
public void setItReqApprCode(String itReqApprCode) {
	this.itReqApprCode = itReqApprCode;
}
public String getItReqLevel() {
	return itReqLevel;
}
public void setItReqLevel(String itReqLevel) {
	this.itReqLevel = itReqLevel;
}
public String getItReqType() {
	return itReqType;
}
public void setItReqType(String itReqType) {
	this.itReqType = itReqType;
}
public String getItReqType1() {
	return itReqType1;
}
public void setItReqType1(String itReqType1) {
	this.itReqType1 = itReqType1;
}
public ArrayList getVacList() {
	return vacList;
}
public void setVacList(ArrayList vacList) {
	this.vacList = vacList;
}
public String getNoOfVac() {
	return noOfVac;
}
public void setNoOfVac(String noOfVac) {
	this.noOfVac = noOfVac;
}
public String getVacDate() {
	return vacDate;
}
public void setVacDate(String vacDate) {
	this.vacDate = vacDate;
}
public String getQualType() {
	return qualType;
}
public void setQualType(String qualType) {
	this.qualType = qualType;
}
public String getQualName() {
	return qualName;
}
public void setQualName(String qualName) {
	this.qualName = qualName;
}
public String getQualLvl() {
	return qualLvl;
}
public void setQualLvl(String qualLvl) {
	this.qualLvl = qualLvl;
}
public String getSpl() {
	return spl;
}
public void setSpl(String spl) {
	this.spl = spl;
}
public String getCutOff() {
	return cutOff;
}
public void setCutOff(String cutOff) {
	this.cutOff = cutOff;
}
public String getSel() {
	return sel;
}
public void setSel(String sel) {
	this.sel = sel;
}
public ArrayList getQualList() {
	return qualList;
}
public void setQualList(ArrayList qualList) {
	this.qualList = qualList;
}
public String getSkillType() {
	return skillType;
}
public void setSkillType(String skillType) {
	this.skillType = skillType;
}
public String getSkillName() {
	return skillName;
}
public void setSkillName(String skillName) {
	this.skillName = skillName;
}
public String getSkillExp() {
	return skillExp;
}
public void setSkillExp(String skillExp) {
	this.skillExp = skillExp;
}
public String getSkillSel() {
	return skillSel;
}
public void setSkillSel(String skillSel) {
	this.skillSel = skillSel;
}
public ArrayList getSkillList() {
	return skillList;
}
public void setSkillList(ArrayList skillList) {
	this.skillList = skillList;
}
public String getDomType() {
	return domType;
}
public void setDomType(String domType) {
	this.domType = domType;
}
public String getDomName() {
	return domName;
}
public void setDomName(String domName) {
	this.domName = domName;
}
public String getDomExp() {
	return domExp;
}
public void setDomExp(String domExp) {
	this.domExp = domExp;
}
public String getDomSel() {
	return domSel;
}
public void setDomSel(String domSel) {
	this.domSel = domSel;
}
public ArrayList getDomList() {
	return domList;
}
public void setDomList(ArrayList domList) {
	this.domList = domList;
}
public String getCertType() {
	return certType;
}
public void setCertType(String certType) {
	this.certType = certType;
}
public String getCertName() {
	return certName;
}
public void setCertName(String certName) {
	this.certName = certName;
}
public String getCertIssueBy() {
	return certIssueBy;
}
public void setCertIssueBy(String certIssueBy) {
	this.certIssueBy = certIssueBy;
}
public String getCertValid() {
	return certValid;
}
public void setCertValid(String certValid) {
	this.certValid = certValid;
}
public String getCertOption() {
	return certOption;
}
public void setCertOption(String certOption) {
	this.certOption = certOption;
}
public ArrayList getCertList() {
	return certList;
}
public void setCertList(ArrayList certList) {
	this.certList = certList;
}
public String getApprvName() {
	return apprvName;
}
public void setApprvName(String apprvName) {
	this.apprvName = apprvName;
}
public String getApprvDate() {
	return apprvDate;
}
public void setApprvDate(String apprvDate) {
	this.apprvDate = apprvDate;
}
public String getApprvRem() {
	return apprvRem;
}
public void setApprvRem(String apprvRem) {
	this.apprvRem = apprvRem;
}
public ArrayList getApprvList() {
	return apprvList;
}
public void setApprvList(ArrayList apprvList) {
	this.apprvList = apprvList;
}
public String getApprvFlag() {
	return apprvFlag;
}
public void setApprvFlag(String apprvFlag) {
	this.apprvFlag = apprvFlag;
}
public String getEditReqFlag() {
	return editReqFlag;
}
public void setEditReqFlag(String editReqFlag) {
	this.editReqFlag = editReqFlag;
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
public ArrayList getDispList() {
	return dispList;
}
public void setDispList(ArrayList dispList) {
	this.dispList = dispList;
}
public String getDataLength() {
	return dataLength;
}
public void setDataLength(String dataLength) {
	this.dataLength = dataLength;
}
public String getListChk() {
	return listChk;
}
public void setListChk(String listChk) {
	this.listChk = listChk;
}
public String getSelectedReqFlag() {
	return selectedReqFlag;
}
public void setSelectedReqFlag(String selectedReqFlag) {
	this.selectedReqFlag = selectedReqFlag;
}
public String getNoDataReq() {
	return noDataReq;
}
public void setNoDataReq(String noDataReq) {
	this.noDataReq = noDataReq;
}
public String getItPosition() {
	return itPosition;
}
public void setItPosition(String itPosition) {
	this.itPosition = itPosition;
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
public String getRadio1() {
	return radio1;
}
public void setRadio1(String radio1) {
	this.radio1 = radio1;
}
public String getFirstDesc() {
	return firstDesc;
}
public void setFirstDesc(String firstDesc) {
	this.firstDesc = firstDesc;
}
public String getRadioFlag1() {
	return radioFlag1;
}
public void setRadioFlag1(String radioFlag1) {
	this.radioFlag1 = radioFlag1;
}
public String getSecondSort() {
	return secondSort;
}
public void setSecondSort(String secondSort) {
	this.secondSort = secondSort;
}
public String getRadio2() {
	return radio2;
}
public void setRadio2(String radio2) {
	this.radio2 = radio2;
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
public String getRadioFlag2() {
	return radioFlag2;
}
public void setRadioFlag2(String radioFlag2) {
	this.radioFlag2 = radioFlag2;
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
public String getRadio3() {
	return radio3;
}
public void setRadio3(String radio3) {
	this.radio3 = radio3;
}
public String getRadioFlag3() {
	return radioFlag3;
}
public void setRadioFlag3(String radioFlag3) {
	this.radioFlag3 = radioFlag3;
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
public String getHeaderChk() {
	return headerChk;
}
public void setHeaderChk(String headerChk) {
	this.headerChk = headerChk;
}
public String getEditVal() {
	return editVal;
}
public void setEditVal(String editVal) {
	this.editVal = editVal;
}
public String getDataLen() {
	return dataLen;
}
public void setDataLen(String dataLen) {
	this.dataLen = dataLen;
}
public String getItReqName1() {
	return itReqName1;
}
public void setItReqName1(String itReqName1) {
	this.itReqName1 = itReqName1;
}
public String getItReqCode1() {
	return itReqCode1;
}
public void setItReqCode1(String itReqCode1) {
	this.itReqCode1 = itReqCode1;
}
public String getItReqDate1() {
	return itReqDate1;
}
public void setItReqDate1(String itReqDate1) {
	this.itReqDate1 = itReqDate1;
}
public String getQualFlag() {
	return qualFlag;
}
public void setQualFlag(String qualFlag) {
	this.qualFlag = qualFlag;
}
public String getSkillFlag() {
	return skillFlag;
}
public void setSkillFlag(String skillFlag) {
	this.skillFlag = skillFlag;
}
public String getDomFlag() {
	return domFlag;
}
public void setDomFlag(String domFlag) {
	this.domFlag = domFlag;
}
public String getCertFlag() {
	return certFlag;
}
public void setCertFlag(String certFlag) {
	this.certFlag = certFlag;
}
public String getHidReportRadio() {
	return hidReportRadio;
}
public void setHidReportRadio(String hidReportRadio) {
	this.hidReportRadio = hidReportRadio;
}
public String getHidReportView() {
	return hidReportView;
}
public void setHidReportView(String hidReportView) {
	this.hidReportView = hidReportView;
}
public String getExportAll1() {
	return exportAll1;
}
public void setExportAll1(String exportAll1) {
	this.exportAll1 = exportAll1;
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
public String getRadioFlag() {
	return radioFlag;
}
public void setRadioFlag(String radioFlag) {
	this.radioFlag = radioFlag;
}
public String getScrnFlg() {
	return scrnFlg;
}
public void setScrnFlg(String scrnFlg) {
	this.scrnFlg = scrnFlg;
}
public String getViewOnScrn() {
	return viewOnScrn;
}
public void setViewOnScrn(String viewOnScrn) {
	this.viewOnScrn = viewOnScrn;
}
public String getReportFlag() {
	return reportFlag;
}
public void setReportFlag(String reportFlag) {
	this.reportFlag = reportFlag;
}
public String getDateFlag() {
	return dateFlag;
}
public void setDateFlag(String dateFlag) {
	this.dateFlag = dateFlag;
}
public String getPageNoField() {
	return pageNoField;
}
public void setPageNoField(String pageNoField) {
	this.pageNoField = pageNoField;
}
public String getTotalPage() {
	return totalPage;
}
public void setTotalPage(String totalPage) {
	this.totalPage = totalPage;
}
public String getMyPage() {
	return myPage;
}
public void setMyPage(String myPage) {
	this.myPage = myPage;
}
public String getEditFlag() {
	return editFlag;
}
public void setEditFlag(String editFlag) {
	this.editFlag = editFlag;
}


	
}
