package org.paradyne.bean.Recruitment;
import java.util.*;
import org.paradyne.lib.BeanBase;
/**
 * author:Gauri 
 * modified by Pradeep on date:15-07-2009
  */
public class CandidateStatusReport extends BeanBase{
	
	private String chkMobileNo ="Y";
	private String chkMobileNo1 ="";
	private String editVal="";
	private String divID="";
	private String aID="";
	private String backFlag="false"; 
	private String viewReqsName="";
	private String viewReqsDate="";
	private String apprvalStatus="";
	private String div="";
	private String status="";
	private String viewReqsCode="";
	private String myReqPage="";
	private String myCandPage="";
	private String totalReqsPage="";
	private String pageNoFieldReq="";
	private String totalCandPage="";
	private String pageNoFieldCand="";
	private String fromTotRec="";
	private String toTotRec="";
	private String hdPage="";
	private String noData="false";
	private String exportAll="";
	private String exportAll1="";
	private String exportAllData="Y";
	private ArrayList candList=null;
	private String noDataReq="false";
	
	private String itrHdrEmail="false";
	private String itrHdrSrn="false";
	private String itrHdrTest="false";
	private String itrHdrInrvRnd="false";
	private String itrHdrOffStat="false";
	private String itrHdrAppntStat="false";
	private String itrHdrCon="false";
	

	private String itrEmailFlag="false";
	private String itrSrnFlag="false";
	private String itrTestFlag="false";
	private String itrInrvRndFlag="false";
	private String itrOffStatFlag="false";
	private String itrAppntStatFlag="false";
	private String itrConFlag="false";
	
	private String cand="";
	private String email="";
	private String scrnStat="";
	private String testRes="";
	private String intrvRnd="";
	private String offStat="";
	private String appntStat="";
	private String convert="";
	
		
	private String noOfCol="";
	private String frmDate;
	private String toDate;
	private String reqname;
	private String reqCode;
	private String candidateName;
	private String candidateCode;
	private String candidate;
	private String experience;
	private String postingDate;
	private String position;
	private String rankId;
	private String rankName;
	private String convertemp;
	private String searchstatus;
	private String testresult;
	private String candscreeningstatus;
	private String intvstatus;
	private String intvround;
	private String makeoffer;
	private String offerstatus;
	private String appointstatus;
	private String reqIndex;
	private String requisitionCode;
	private String title;
	private String type;
	private String flag;
	//Added by Pradeep 
	private String reqsDateCombo="";
	private String genRep="";
	private String onScrn="";
	private String reqsStatus="";
	private String chkCand="Y";
	private String chkCand1="";
	private String chkEmail1="";
	private String chkEmail="Y";
	private String chkPosition="N";
	private String chkPosition1="";
	private String chkSearchStat="Y";
	private String chkCandScrnStat="Y";
	 
	private String chkIntStat="Y";
	 
	private String chkMkOffStat="Y";
	private String chkOffStat="Y";
	private String chkAppntStat="Y";
	private String chkConEmp="Y";
	private String chkSearchStat1="";
	private String chkCandScrnStat1="";
	private String chkTestRes1="";
	private String chkIntStat1="";
	private String chkIntRnd1="";
	private String chkMkOffStat1="";
	private String chkOffStat1="";
	private String chkAppntStat1="";
	private String chkConEmp1="";
	private String sortBy="";
	private String asc="";
	private String desc="";
	private String firstBy="";
	private String secondBy="";
	private String asce="";
	private String desce="";
	private String ascc="";
	private String descc="";
	private String asce1="A";
	private String desce1="";
	private String ascc1="A";
	private String descc1="";
	private String asc1="A";
	private String desc1="";
	private String advScrn="";
	private String advIntRnd="";
	private String advOffStat="";
	private String advAppntStat="";
	private String intRndVal="";
	private String itReqName="";
	private String itPosition="";
	private String itReqCode="";
	private String itReqDate="";
	private String itStatus="";
	private String listChk="";
	private String headerChk="";
	private String selectedReqName="";
	private String selectedReq="";
	private String dataLength="";
	private ArrayList dispList=null;
	private String searchSetting="";
	private String saveSetting="";
	private String appRepCode="";
	HashMap hashMap;
	private String radio1="false";
	private String radioFlag1="false";
	private String radio2="false";
	private String radioFlag2="false";
	private String radio3="false";
	private String radioFlag3="false";
	private String radioFlag="false";
	private String pageFlag="false";
	private String candScrnItrtrFlag="false";
	private String candScrnHdrFlag="false";
	private String intvHdrFlag="false";
	private String intvItrtFlag="false";
	private String mkOffHdrFlag="false";
	private String mkOffItrtrFlag="false";
	private String offrHdrFlag="false";
	private String offrItrtrFlag="false";
	private String appntmtHdrFlag="false";
	private String appntmtItrtrFlag="false";
	private String conEmpHdrFlag="false";
	private String conEMpItrtrFlag="flase";
	private ArrayList summaryList=null;	
	private String sn="";
	private String viewReqsn="";
	private String viewScreening="";
	private String viewInterview="";
	private String viewMakeOffer="";
	private String viewOffer="";
	private String viewAppntmt="";
	private String conEmp="";
	private String reqsnCode="";
	private String editReqFlag="false";
	private String selectedReqFlag=""; 
	private String reportFlag="flase";
	
	
	//Added by tinshuk
	
	private String chkLastComp1="";
	private String chkLastComp="Y";
	
	private String chkcurrCtc1="";
	private String chkcurrCtc="Y";
	
	private String chkCurrLoc1="";
	private String chkCurrLoc="Y";
	
	private String chkQualDtl1="";
	private String chkQualDtl="Y";
	
	private String chkExp1="";
	private String chkExp="Y";
	
	private String report="";


	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getReqsnCode() {
		return reqsnCode;
	}
	public void setReqsnCode(String reqsnCode) {
		this.reqsnCode = reqsnCode;
	}
	public ArrayList getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(ArrayList summaryList) {
		this.summaryList = summaryList;
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
	public String getSearchSetting() {
		return searchSetting;
	}
	public void setSearchSetting(String searchSetting) {
		this.searchSetting = searchSetting;
	}
	public String getSaveSetting() {
		return saveSetting;
	}
	public void setSaveSetting(String saveSetting) {
		this.saveSetting = saveSetting;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getSelectedReq() {
		return selectedReq;
	}
	public void setSelectedReq(String selectedReq) {
		this.selectedReq = selectedReq;
	}
	public String getAdvScrn() {
		return advScrn;
	}
	public void setAdvScrn(String advScrn) {
		this.advScrn = advScrn;
	}
	public String getAdvIntRnd() {
		return advIntRnd;
	}
	public void setAdvIntRnd(String advIntRnd) {
		this.advIntRnd = advIntRnd;
	}
	public String getAdvOffStat() {
		return advOffStat;
	}
	public void setAdvOffStat(String advOffStat) {
		this.advOffStat = advOffStat;
	}
	public String getAdvAppntStat() {
		return advAppntStat;
	}
	public void setAdvAppntStat(String advAppntStat) {
		this.advAppntStat = advAppntStat;
	}
	public String getIntRndVal() {
		return intRndVal;
	}
	public void setIntRndVal(String intRndVal) {
		this.intRndVal = intRndVal;
	}
	public String getChkCand() {
		System.out.println("getchkcand...."+chkCand);
		return chkCand;
	}
	public void setChkCand(String chkCand) {
		System.out.println("setchkcand...."+chkCand);
		this.chkCand = chkCand;
	}
	public String getChkEmail() {
		return chkEmail;
	}
	public void setChkEmail(String chkEmail) {
		this.chkEmail = chkEmail;
	}
	public String getChkPosition() {
		return chkPosition;
	}
	public void setChkPosition(String chkPosition) {
		this.chkPosition = chkPosition;
	}
	public String getChkSearchStat() {
		return chkSearchStat;
	}
	public void setChkSearchStat(String chkSearchStat) {
		this.chkSearchStat = chkSearchStat;
	}
	public String getChkCandScrnStat() {
		return chkCandScrnStat;
	}
	public void setChkCandScrnStat(String chkCandScrnStat) {
		this.chkCandScrnStat = chkCandScrnStat;
	}
	 
	public String getChkIntStat() {
		return chkIntStat;
	}
	public void setChkIntStat(String chkIntStat) {
		this.chkIntStat = chkIntStat;
	}
	 
	public String getChkMkOffStat() {
		return chkMkOffStat;
	}
	public void setChkMkOffStat(String chkMkOffStat) {
		this.chkMkOffStat = chkMkOffStat;
	}
	public String getChkOffStat() {
		return chkOffStat;
	}
	public void setChkOffStat(String chkOffStat) {
		this.chkOffStat = chkOffStat;
	}
	public String getChkAppntStat() {
		return chkAppntStat;
	}
	public void setChkAppntStat(String chkAppntStat) {
		this.chkAppntStat = chkAppntStat;
	}
	public String getChkConEmp() {
		return chkConEmp;
	}
	public void setChkConEmp(String chkConEmp) {
		this.chkConEmp = chkConEmp;
	}
	public String getReqsDateCombo() {
		return reqsDateCombo;
	}
	public void setReqsDateCombo(String reqsDateCombo) {
		this.reqsDateCombo = reqsDateCombo;
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
	public String getReqname() {
		return reqname;
	}
	public void setReqname(String reqname) {
		this.reqname = reqname;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
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
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRankId() {
		return rankId;
	}
	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getConvertemp() {
		return convertemp;
	}
	public void setConvertemp(String convertemp) {
		this.convertemp = convertemp;
	}
	public String getSearchstatus() {
		return searchstatus;
	}
	public void setSearchstatus(String searchstatus) {
		this.searchstatus = searchstatus;
	}
	public String getTestresult() {
		return testresult;
	}
	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}
	public String getCandscreeningstatus() {
		return candscreeningstatus;
	}
	public void setCandscreeningstatus(String candscreeningstatus) {
		this.candscreeningstatus = candscreeningstatus;
	}
	public String getIntvstatus() {
		return intvstatus;
	}
	public void setIntvstatus(String intvstatus) {
		this.intvstatus = intvstatus;
	}
	public String getIntvround() {
		return intvround;
	}
	public void setIntvround(String intvround) {
		this.intvround = intvround;
	}
	public String getMakeoffer() {
		return makeoffer;
	}
	public void setMakeoffer(String makeoffer) {
		this.makeoffer = makeoffer;
	}
	public String getOfferstatus() {
		return offerstatus;
	}
	public void setOfferstatus(String offerstatus) {
		this.offerstatus = offerstatus;
	}
	public String getAppointstatus() {
		return appointstatus;
	}
	public void setAppointstatus(String appointstatus) {
		this.appointstatus = appointstatus;
	}
	public String getReqIndex() {
		return reqIndex;
	}
	public void setReqIndex(String reqIndex) {
		this.reqIndex = reqIndex;
	}
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getGenRep() {
		return genRep;
	}
	public void setGenRep(String genRep) {
		this.genRep = genRep;
	}
	public String getOnScrn() {
		return onScrn;
	}
	public void setOnScrn(String onScrn) {
		this.onScrn = onScrn;
	}
	public String getReqsStatus() {
		return reqsStatus;
	}
	public void setReqsStatus(String reqsStatus) {
		this.reqsStatus = reqsStatus;
	}
	public String getChkCand1() {
		return chkCand1;
	}
	public void setChkCand1(String chkCand1) {
		this.chkCand1 = chkCand1;
	}
	public String getChkEmail1() {
		return chkEmail1;
	}
	public void setChkEmail1(String chkEmail1) {
		this.chkEmail1 = chkEmail1;
	}
	public String getChkPosition1() {
		return chkPosition1;
	}
	public void setChkPosition1(String chkPosition1) {
		this.chkPosition1 = chkPosition1;
	}
	public String getChkSearchStat1() {
		return chkSearchStat1;
	}
	public void setChkSearchStat1(String chkSearchStat1) {
		this.chkSearchStat1 = chkSearchStat1;
	}
	public String getChkCandScrnStat1() {
		return chkCandScrnStat1;
	}
	public void setChkCandScrnStat1(String chkCandScrnStat1) {
		this.chkCandScrnStat1 = chkCandScrnStat1;
	}
	public String getChkTestRes1() {
		return chkTestRes1;
	}
	public void setChkTestRes1(String chkTestRes1) {
		this.chkTestRes1 = chkTestRes1;
	}
	public String getChkIntStat1() {
		return chkIntStat1;
	}
	public void setChkIntStat1(String chkIntStat1) {
		this.chkIntStat1 = chkIntStat1;
	}
	public String getChkIntRnd1() {
		return chkIntRnd1;
	}
	public void setChkIntRnd1(String chkIntRnd1) {
		this.chkIntRnd1 = chkIntRnd1;
	}
	public String getChkMkOffStat1() {
		return chkMkOffStat1;
	}
	public void setChkMkOffStat1(String chkMkOffStat1) {
		this.chkMkOffStat1 = chkMkOffStat1;
	}
	public String getChkOffStat1() {
		return chkOffStat1;
	}
	public void setChkOffStat1(String chkOffStat1) {
		this.chkOffStat1 = chkOffStat1;
	}
	public String getChkAppntStat1() {
		return chkAppntStat1;
	}
	public void setChkAppntStat1(String chkAppntStat1) {
		this.chkAppntStat1 = chkAppntStat1;
	}
	public String getChkConEmp1() {
		return chkConEmp1;
	}
	public void setChkConEmp1(String chkConEmp1) {
		this.chkConEmp1 = chkConEmp1;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getAsc() {
		return asc;
	}
	public void setAsc(String asc) {
		this.asc = asc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFirstBy() {
		return firstBy;
	}
	public void setFirstBy(String firstBy) {
		this.firstBy = firstBy;
	}
	public String getSecondBy() {
		return secondBy;
	}
	public void setSecondBy(String secondBy) {
		this.secondBy = secondBy;
	}
	public String getAsce() {
		return asce;
	}
	public void setAsce(String asce) {
		this.asce = asce;
	}
	public String getDesce() {
		return desce;
	}
	public void setDesce(String desce) {
		this.desce = desce;
	}
	public String getAscc() {
		return ascc;
	}
	public void setAscc(String ascc) {
		this.ascc = ascc;
	}
	public String getDescc() {
		return descc;
	}
	public void setDescc(String descc) {
		this.descc = descc;
	}
	public String getAsce1() {
		return asce1;
	}
	public void setAsce1(String asce1) {
		this.asce1 = asce1;
	}
	public String getDesce1() {
		return desce1;
	}
	public void setDesce1(String desce1) {
		this.desce1 = desce1;
	}
	public String getAscc1() {
		return ascc1;
	}
	public void setAscc1(String ascc1) {
		this.ascc1 = ascc1;
	}
	public String getDescc1() {
		return descc1;
	}
	public void setDescc1(String descc1) {
		this.descc1 = descc1;
	}
	public String getAsc1() {
		return asc1;
	}
	public void setAsc1(String asc1) {
		this.asc1 = asc1;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
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
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}
	public String getListChk() {
		return listChk;
	}
	public void setListChk(String listChk) {
		this.listChk = listChk;
	}
	public String getHeaderChk() {
		return headerChk;
	}
	public void setHeaderChk(String headerChk) {
		this.headerChk = headerChk;
	}
	public String getSelectedReqName() {
		return selectedReqName;
	}
	public void setSelectedReqName(String selectedReqName) {
		this.selectedReqName = selectedReqName;
	}
	public ArrayList getDispList() {
		return dispList;
	}
	public void setDispList(ArrayList dispList) {
		this.dispList = dispList;
	}
	public String getAppRepCode() {
		return appRepCode;
	}
	public void setAppRepCode(String appRepCode) {
		this.appRepCode = appRepCode;
	}
	public HashMap getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
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
	public String getRadioFlag() {
		return radioFlag;
	}
	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getCandScrnItrtrFlag() {
		return candScrnItrtrFlag;
	}
	public void setCandScrnItrtrFlag(String candScrnItrtrFlag) {
		this.candScrnItrtrFlag = candScrnItrtrFlag;
	}
	public String getCandScrnHdrFlag() {
		return candScrnHdrFlag;
	}
	public void setCandScrnHdrFlag(String candScrnHdrFlag) {
		this.candScrnHdrFlag = candScrnHdrFlag;
	}
	public String getIntvHdrFlag() {
		return intvHdrFlag;
	}
	public void setIntvHdrFlag(String intvHdrFlag) {
		this.intvHdrFlag = intvHdrFlag;
	}
	public String getIntvItrtFlag() {
		return intvItrtFlag;
	}
	public void setIntvItrtFlag(String intvItrtFlag) {
		this.intvItrtFlag = intvItrtFlag;
	}
	public String getMkOffHdrFlag() {
		return mkOffHdrFlag;
	}
	public void setMkOffHdrFlag(String mkOffHdrFlag) {
		this.mkOffHdrFlag = mkOffHdrFlag;
	}
	public String getMkOffItrtrFlag() {
		return mkOffItrtrFlag;
	}
	public void setMkOffItrtrFlag(String mkOffItrtrFlag) {
		this.mkOffItrtrFlag = mkOffItrtrFlag;
	}
	public String getOffrHdrFlag() {
		return offrHdrFlag;
	}
	public void setOffrHdrFlag(String offrHdrFlag) {
		this.offrHdrFlag = offrHdrFlag;
	}
	public String getOffrItrtrFlag() {
		return offrItrtrFlag;
	}
	public void setOffrItrtrFlag(String offrItrtrFlag) {
		this.offrItrtrFlag = offrItrtrFlag;
	}
	public String getAppntmtHdrFlag() {
		return appntmtHdrFlag;
	}
	public void setAppntmtHdrFlag(String appntmtHdrFlag) {
		this.appntmtHdrFlag = appntmtHdrFlag;
	}
	public String getAppntmtItrtrFlag() {
		return appntmtItrtrFlag;
	}
	public void setAppntmtItrtrFlag(String appntmtItrtrFlag) {
		this.appntmtItrtrFlag = appntmtItrtrFlag;
	}
	public String getConEmpHdrFlag() {
		return conEmpHdrFlag;
	}
	public void setConEmpHdrFlag(String conEmpHdrFlag) {
		this.conEmpHdrFlag = conEmpHdrFlag;
	}
	public String getConEMpItrtrFlag() {
		return conEMpItrtrFlag;
	}
	public void setConEMpItrtrFlag(String conEMpItrtrFlag) {
		this.conEMpItrtrFlag = conEMpItrtrFlag;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getViewReqsn() {
		return viewReqsn;
	}
	public void setViewReqsn(String viewReqsn) {
		this.viewReqsn = viewReqsn;
	}
	public String getViewScreening() {
		return viewScreening;
	}
	public void setViewScreening(String viewScreening) {
		this.viewScreening = viewScreening;
	}
	public String getViewInterview() {
		return viewInterview;
	}
	public void setViewInterview(String viewInterview) {
		this.viewInterview = viewInterview;
	}
	public String getViewMakeOffer() {
		return viewMakeOffer;
	}
	public void setViewMakeOffer(String viewMakeOffer) {
		this.viewMakeOffer = viewMakeOffer;
	}
	public String getViewOffer() {
		return viewOffer;
	}
	public void setViewOffer(String viewOffer) {
		this.viewOffer = viewOffer;
	}
	public String getViewAppntmt() {
		return viewAppntmt;
	}
	public void setViewAppntmt(String viewAppntmt) {
		this.viewAppntmt = viewAppntmt;
	}
	public String getConEmp() {
		return conEmp;
	}
	public void setConEmp(String conEmp) {
		this.conEmp = conEmp;
	}
	public String getEditReqFlag() {
		return editReqFlag;
	}
	public void setEditReqFlag(String editReqFlag) {
		this.editReqFlag = editReqFlag;
	}
	public String getSelectedReqFlag() {
		return selectedReqFlag;
	}
	public void setSelectedReqFlag(String selectedReqFlag) {
		this.selectedReqFlag = selectedReqFlag;
	}
	public String getReportFlag() {
		return reportFlag;
	}
	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}
	public String getNoOfCol() {
		return noOfCol;
	}
	public void setNoOfCol(String noOfCol) {
		this.noOfCol = noOfCol;
	}
	public String getViewReqsName() {
		return viewReqsName;
	}
	public void setViewReqsName(String viewReqsName) {
		this.viewReqsName = viewReqsName;
	}
	public String getViewReqsDate() {
		return viewReqsDate;
	}
	public void setViewReqsDate(String viewReqsDate) {
		this.viewReqsDate = viewReqsDate;
	}
	public String getApprvalStatus() {
		return apprvalStatus;
	}
	public void setApprvalStatus(String apprvalStatus) {
		this.apprvalStatus = apprvalStatus;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getViewReqsCode() {
		return viewReqsCode;
	}
	public void setViewReqsCode(String viewReqsCode) {
		this.viewReqsCode = viewReqsCode;
	}
	public String getMyReqPage() {
		return myReqPage;
	}
	public void setMyReqPage(String myReqPage) {
		this.myReqPage = myReqPage;
	}
	public String getMyCandPage() {
		return myCandPage;
	}
	public void setMyCandPage(String myCandPage) {
		this.myCandPage = myCandPage;
	}
	public String getTotalReqsPage() {
		return totalReqsPage;
	}
	public void setTotalReqsPage(String totalReqsPage) {
		this.totalReqsPage = totalReqsPage;
	}
	public String getPageNoFieldReq() {
		return pageNoFieldReq;
	}
	public void setPageNoFieldReq(String pageNoFieldReq) {
		this.pageNoFieldReq = pageNoFieldReq;
	}
	public String getPageNoFieldCand() {
		return pageNoFieldCand;
	}
	public void setPageNoFieldCand(String pageNoFieldCand) {
		this.pageNoFieldCand = pageNoFieldCand;
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
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public ArrayList getCandList() {
		return candList;
	}
	public void setCandList(ArrayList candList) {
		this.candList = candList;
	}
	
	public String getItrHdrEmail() {
		return itrHdrEmail;
	}
	public void setItrHdrEmail(String itrHdrEmail) {
		this.itrHdrEmail = itrHdrEmail;
	}
	public String getItrHdrSrn() {
		return itrHdrSrn;
	}
	public void setItrHdrSrn(String itrHdrSrn) {
		this.itrHdrSrn = itrHdrSrn;
	}
	public String getItrHdrTest() {
		return itrHdrTest;
	}
	public void setItrHdrTest(String itrHdrTest) {
		this.itrHdrTest = itrHdrTest;
	}
	public String getItrHdrInrvRnd() {
		return itrHdrInrvRnd;
	}
	public void setItrHdrInrvRnd(String itrHdrInrvRnd) {
		this.itrHdrInrvRnd = itrHdrInrvRnd;
	}
	public String getItrHdrOffStat() {
		return itrHdrOffStat;
	}
	public void setItrHdrOffStat(String itrHdrOffStat) {
		this.itrHdrOffStat = itrHdrOffStat;
	}
	public String getItrHdrAppntStat() {
		return itrHdrAppntStat;
	}
	public void setItrHdrAppntStat(String itrHdrAppntStat) {
		this.itrHdrAppntStat = itrHdrAppntStat;
	}
	public String getItrHdrCon() {
		return itrHdrCon;
	}
	public void setItrHdrCon(String itrHdrCon) {
		this.itrHdrCon = itrHdrCon;
	}

	public String getItrEmailFlag() {
		return itrEmailFlag;
	}
	public void setItrEmailFlag(String itrEmailFlag) {
		this.itrEmailFlag = itrEmailFlag;
	}
	public String getItrSrnFlag() {
		return itrSrnFlag;
	}
	public void setItrSrnFlag(String itrSrnFlag) {
		this.itrSrnFlag = itrSrnFlag;
	}
	public String getItrTestFlag() {
		return itrTestFlag;
	}
	public void setItrTestFlag(String itrTestFlag) {
		this.itrTestFlag = itrTestFlag;
	}
	public String getItrInrvRndFlag() {
		return itrInrvRndFlag;
	}
	public void setItrInrvRndFlag(String itrInrvRndFlag) {
		this.itrInrvRndFlag = itrInrvRndFlag;
	}
	public String getItrOffStatFlag() {
		return itrOffStatFlag;
	}
	public void setItrOffStatFlag(String itrOffStatFlag) {
		this.itrOffStatFlag = itrOffStatFlag;
	}
	public String getItrAppntStatFlag() {
		return itrAppntStatFlag;
	}
	public void setItrAppntStatFlag(String itrAppntStatFlag) {
		this.itrAppntStatFlag = itrAppntStatFlag;
	}
	public String getItrConFlag() {
		return itrConFlag;
	}
	public void setItrConFlag(String itrConFlag) {
		this.itrConFlag = itrConFlag;
	}
	public String getCand() {
		return cand;
	}
	public void setCand(String cand) {
		this.cand = cand;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getScrnStat() {
		return scrnStat;
	}
	public void setScrnStat(String scrnStat) {
		this.scrnStat = scrnStat;
	}
	public String getTestRes() {
		return testRes;
	}
	public void setTestRes(String testRes) {
		this.testRes = testRes;
	}
	public String getIntrvRnd() {
		return intrvRnd;
	}
	public void setIntrvRnd(String intrvRnd) {
		this.intrvRnd = intrvRnd;
	}
	public String getOffStat() {
		return offStat;
	}
	public void setOffStat(String offStat) {
		this.offStat = offStat;
	}
	public String getAppntStat() {
		return appntStat;
	}
	public void setAppntStat(String appntStat) {
		this.appntStat = appntStat;
	}
	public String getConvert() {
		return convert;
	}
	public void setConvert(String convert) {
		this.convert = convert;
	}
	public String getTotalCandPage() {
		return totalCandPage;
	}
	public void setTotalCandPage(String totalCandPage) {
		this.totalCandPage = totalCandPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
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
	public String getExportAllData() {
		return exportAllData;
	}
	public void setExportAllData(String exportAllData) {
		this.exportAllData = exportAllData;
	}
	public String getDivID() {
		return divID;
	}
	public void setDivID(String divID) {
		this.divID = divID;
	}
	public String getAID() {
		return aID;
	}
	public void setAID(String aid) {
		aID = aid;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getEditVal() {
		return editVal;
	}
	public void setEditVal(String editVal) {
		this.editVal = editVal;
	}
	public String getNoDataReq() {
		return noDataReq;
	}
	public void setNoDataReq(String noDataReq) {
		this.noDataReq = noDataReq;
	}
	public String getChkMobileNo() {
		return chkMobileNo;
	}
	public void setChkMobileNo(String chkMobileNo) {
		this.chkMobileNo = chkMobileNo;
	}
	public String getChkMobileNo1() {
		return chkMobileNo1;
	}
	public void setChkMobileNo1(String chkMobileNo1) {
		this.chkMobileNo1 = chkMobileNo1;
	}
	public String getChkLastComp1() {
		return chkLastComp1;
	}
	public void setChkLastComp1(String chkLastComp1) {
		this.chkLastComp1 = chkLastComp1;
	}
	public String getChkLastComp() {
		return chkLastComp;
	}
	public void setChkLastComp(String chkLastComp) {
		this.chkLastComp = chkLastComp;
	}
	public String getChkcurrCtc1() {
		return chkcurrCtc1;
	}
	public void setChkcurrCtc1(String chkcurrCtc1) {
		this.chkcurrCtc1 = chkcurrCtc1;
	}
	public String getChkcurrCtc() {
		return chkcurrCtc;
	}
	public void setChkcurrCtc(String chkcurrCtc) {
		this.chkcurrCtc = chkcurrCtc;
	}
	public String getChkCurrLoc1() {
		return chkCurrLoc1;
	}
	public void setChkCurrLoc1(String chkCurrLoc1) {
		this.chkCurrLoc1 = chkCurrLoc1;
	}
	public String getChkCurrLoc() {
		return chkCurrLoc;
	}
	public void setChkCurrLoc(String chkCurrLoc) {
		this.chkCurrLoc = chkCurrLoc;
	}
	public String getChkQualDtl1() {
		return chkQualDtl1;
	}
	public void setChkQualDtl1(String chkQualDtl1) {
		this.chkQualDtl1 = chkQualDtl1;
	}
	public String getChkQualDtl() {
		return chkQualDtl;
	}
	public void setChkQualDtl(String chkQualDtl) {
		this.chkQualDtl = chkQualDtl;
	}
	public String getChkExp1() {
		return chkExp1;
	}
	public void setChkExp1(String chkExp1) {
		this.chkExp1 = chkExp1;
	}
	public String getChkExp() {
		return chkExp;
	}
	public void setChkExp(String chkExp) {
		this.chkExp = chkExp;
	}
	
	
}
