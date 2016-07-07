package org.paradyne.bean.Recruitment;
import org.paradyne.lib.BeanBase;
import java.util.*;
/**
 * Date:27-02-2009
 * @author Pradeep Sahoo
 *
 */

public class TestInterviewReport extends BeanBase {
	
	private String requisitionRadioCheck="";
	private String candidateRadioCheck="";
	private String positionRadioCheck="";
	
	
	private String aId="";
	private String divId="";
	private String candCode="";
	private String candName="";
	private String reqCode="";
	private String reqName="";
	private String fromDate="";
	private String toDate="";
	private String type="";
	private String repType="";
	private String reqDate="";
	private String recName="";
	private String recId="";
	private String modeLength="false";
	private String schdTypeVal="";
	private String headerChk="";
	private String listChk="";
	private String viewReqsName="";
	private String reqsId="";
	private String viewReqsDate="";
	private String position="";
	private String apprvalStatus="";
	private String div="";
	private String status="";
	private String brn="";
	private String hrngMgr="";
	private String dept="";
	private String schdType="";
	private String fromTotRec="";
	private String toTotRec="";
	private String hdPage="";  
	//Interview List
	private String candidateName="";
	private String intRound="";
	private String intDate="";
	private String intTime="";
	private String intVenue="";
	private String interviewer="";
	private String recr="";
	private String conduct="";
	private ArrayList intList=null;
	private String testCandName="";
	private String emailId="";
	private String contac="";
	private String testDate="";
	private String testTime="";
	private String testVenue="";
	private String testRecr="";
	private ArrayList testList=null;
	private boolean flag=false;
	private String myPage="";
	private boolean noData=false;
	 public String noDataInt="false";
	private String candLen="";
	private boolean testIntFlag=false;
	private String reqsDateCombo="";
	private String rankId="";
	private String rankName="";
	private String asc="";
	private String desc="";
	private String radAsc="";
	private String sortBy="";
	private String thenBY="";
	private String secondBy="";
	private String RadioOne="";
	private String RadioThree="";
	private String pageNoField="";
	private String totalPage1="";
	
	private String totalPage="";
	private String  pageNoFieldFirst="";
	private String  pageNoFieldReq="";
	private String show="";
	private String showReq="";
	
	private String myPageReq="";
	private String hdPageReq="";;
	private String RadioTwo="";
	private String emailFlag="false";
	private String requisionFlag="false";
	private String positionFlag="false";
	private String candidateFlag="false";
	private String interviewrFlag="false";
	private String dateFlag="false";
	private String hidreqCheck="Y";
	private String hidcandCheck="Y";
	private String hidintCheck="Y";
	private String hidintDateCheck="Y";
	private String hidposCheck="Y";
	private String reqCodeCheck	="";
	private String candCheck="Y";
	private String intCheck	="";
	private String intDateCheck="Y";	
	private String posCheck="";
	private String  itReqName="";
	private String  itPosition="";
	private String  itschdDate="";
	private String  itschType=""; 
	private String  itReqCode="";
	private String dataLength="";
	private String selectedReqName="";
	private String selectedReq="";
	private String intrvCode="";
	private String intStatus="N";
	//flags for headings
	private String candCheckHd="false";
	private String interviewrHd="";
	private String dateHd="false";
	ArrayList dispList = new ArrayList();
	
	private String editReqFlag="false";
	private String selectedReqFlag="false";
	private String settingTitel="";
	private String settingSearch="";
	private String testInterviewRadio="I";
	// Itrator Flag
	
	private String EmailCheckHd="false";
	private String contactCheckHd="false";
	private String testVenueHd="false";
	private String testTimeHd="false";
	private String testTimeFlag="false";
	private String testVenueFlag="false";
	private String contactFlag="false";
	private String EmailFlag="false";
	private String hidrecruitNameCheck="Y";
	private String hidemailCheck="Y";
	private String hidcontactCheck="Y";
	private String hidtestvenueCheck="Y";
	private String EmailCheck="";
	private String contactCheck="";
	private String timeCheck="";
	private String testvenueCheck="";
	private String recruitNameCheck="";
	private String hidtimeCheck="Y"; 
	private String  recruiterFlag="false"; 
	private String  recruiterHd="false";
	private String typeFlag="false";
	//=============This is for the Interview Dat Iterator and check box  hidden field==============
	
	private String  hidintRoundCheck="Y";
	private String  hidintervewDateCheck="Y";
	private String  hidIntervenueCheck="Y";
	private String  hidInterviewerCheck="Y";
	private String  hidIntRecruiterCheck="Y";
	private String  hidconductCheck="Y";
	
	private String  hidinterviewewCheck="Y";
	private String  hidIntrvcandCheck="Y";
	private String  hidIntervewtimeCheck="Y";
	private String  intrvCandidateHd="false";
	private String  intRndTypeHd="false";
	private String  intrvVenueHd="false";
	private String  intrvDateHd="false";
	private String  intrvTimeHd="false";
	private String  recruiterIntHd="false";
	private String  conductHd="false";
	private String  intrviewerHd="false";
	private String  intrvCandidateFlag="false";
	private String  intRndTypeFlag="false";
	private String  intrvDateFlag="false";
	private String  intrvTimeFlag="false";
	private String  intrvVenueFlag="false";
	private String  intrviewerFlag="false";
	private String  recruiterIntFlag="false";
	private String  conductFlag="false";
	private String secondByInt="";
	private String thenBYInt="";
	private String sortByInt="";
	private String sortRadioInt ="";
	private String RadioOneInt="";
	private String thenRadioInt="";
	private String RadioTwoInt="";
	private String thanRadioInt="";
	private String RadioThreeInt="";
	private String backFlag="false";
	HashMap map = new HashMap();
	
	private String noRecords="false";
	private String schdReqCode="";
	
	private String selecteReqCode="";
	private String selecteReqName="";
	private String sortRadio="";
	private String thenRadio="";
	private String thanRadio="";
	private String settingName="";
	private String seltype="";
	private String interviewerName="";
	private String  Intrvcandidate="";
	private String intRoundType="";	
	private String intervewDate	="";
	private String intervewTime	="";
	private String interviewVenue="";	
	//private String interviewer=""; 
	private String interviewRecruietr="";
	private String intConduct="";
	private String testStatusHd="false";
	private String testStatusFlag="false";
	private String testStatus="N";
	private String testStatus1="";
	private String  hidtestStatusCheck="Y";
	private String itreqsiName="";
	private String itconducted="";
	private String itnonconducted="";
	private String itcanceled="";
	ArrayList summaryList=new ArrayList();
	private String summaryflag="false";
	
	private String intStatusReport="Y";
	private boolean myChkFlag=false;
	private boolean buttonFlag=false;
	private boolean radIntFlag=false;
	private boolean intCheckBoxFlag=false;
	HashMap hashMap = new HashMap();
	private String exportAll="";
	private String exportAll_bot="";
	public String getSortRadio() {
		return sortRadio;
	}
	public void setSortRadio(String sortRadio) {
		this.sortRadio = sortRadio;
	}
	public String getThenRadio() {
		return thenRadio;
	}
	public void setThenRadio(String thenRadio) {
		this.thenRadio = thenRadio;
	}
	public String getThanRadio() {
		return thanRadio;
	}
	public void setThanRadio(String thanRadio) {
		this.thanRadio = thanRadio;
	}
	public String getSchdReqCode() {
		return schdReqCode;
	}
	public void setSchdReqCode(String schdReqCode) {
		this.schdReqCode = schdReqCode;
	}
	public HashMap getMap() {
		return map;
	}
	public void setMap(HashMap map) {
		this.map = map;
	}
	public String getSortRadioInt() {
		return sortRadioInt;
	}
	public void setSortRadioInt(String sortRadioInt) {
		this.sortRadioInt = sortRadioInt;
	}
	public String getRadioOneInt() {
		return RadioOneInt;
	}
	public void setRadioOneInt(String radioOneInt) {
		RadioOneInt = radioOneInt;
	}
	public String getThenRadioInt() {
		return thenRadioInt;
	}
	public void setThenRadioInt(String thenRadioInt) {
		this.thenRadioInt = thenRadioInt;
	}
	public String getRadioTwoInt() {
		return RadioTwoInt;
	}
	public void setRadioTwoInt(String radioTwoInt) {
		RadioTwoInt = radioTwoInt;
	}
	public String getThanRadioInt() {
		return thanRadioInt;
	}
	public void setThanRadioInt(String thanRadioInt) {
		this.thanRadioInt = thanRadioInt;
	}
	public String getRadioThreeInt() {
		return RadioThreeInt;
	}
	public void setRadioThreeInt(String radioThreeInt) {
		RadioThreeInt = radioThreeInt;
	}
	public String getSecondByInt() {
		return secondByInt;
	}
	public void setSecondByInt(String secondByInt) {
		this.secondByInt = secondByInt;
	}
	public String getThenBYInt() {
		return thenBYInt;
	}
	public void setThenBYInt(String thenBYInt) {
		this.thenBYInt = thenBYInt;
	}
	public String getSortByInt() {
		return sortByInt;
	}
	public void setSortByInt(String sortByInt) {
		this.sortByInt = sortByInt;
	}
	public String getRecruiterHd() {
		return recruiterHd;
	}
	public void setRecruiterHd(String recruiterHd) {
		this.recruiterHd = recruiterHd;
	}
	public String getHidtimeCheck() {
		return hidtimeCheck;
	}
	public void setHidtimeCheck(String hidtimeCheck) {
		this.hidtimeCheck = hidtimeCheck;
	}
	public String getRecruitNameCheck() {
		return recruitNameCheck;
	}
	public void setRecruitNameCheck(String recruitNameCheck) {
		this.recruitNameCheck = recruitNameCheck;
	}
	public String getHidemailCheck() {
		return hidemailCheck;
	}
	public void setHidemailCheck(String hidemailCheck) {
		this.hidemailCheck = hidemailCheck;
	}
	public String getHidcontactCheck() {
		return hidcontactCheck;
	}
	public void setHidcontactCheck(String hidcontactCheck) {
		this.hidcontactCheck = hidcontactCheck;
	}
	public String getHidtestvenueCheck() {
		return hidtestvenueCheck;
	}
	public void setHidtestvenueCheck(String hidtestvenueCheck) {
		this.hidtestvenueCheck = hidtestvenueCheck;
	}
	public String getSettingSearch() {
		return settingSearch;
	}
	public void setSettingSearch(String settingSearch) {
		this.settingSearch = settingSearch;
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
	public String getReqCodeCheck() {
		return reqCodeCheck;
	}
	public void setReqCodeCheck(String reqCodeCheck) {
		this.reqCodeCheck = reqCodeCheck;
	}
	public String getCandCheck() {
		return candCheck;
	}
	public void setCandCheck(String candCheck) {
		this.candCheck = candCheck;
	}
	public String getIntCheck() {
		return intCheck;
	}
	public void setIntCheck(String intCheck) {
		this.intCheck = intCheck;
	}
	public String getIntDateCheck() {
		return intDateCheck;
	}
	public void setIntDateCheck(String intDateCheck) {
		this.intDateCheck = intDateCheck;
	}
	public String getPosCheck() {
		return posCheck;
	}
	public void setPosCheck(String posCheck) {
		this.posCheck = posCheck;
	}
	public String getRequisionFlag() {
		return requisionFlag;
	}
	public void setRequisionFlag(String requisionFlag) {
		this.requisionFlag = requisionFlag;
	}
	public String getPositionFlag() {
		return positionFlag;
	}
	public void setPositionFlag(String positionFlag) {
		this.positionFlag = positionFlag;
	}
	public String getCandidateFlag() {
		return candidateFlag;
	}
	public void setCandidateFlag(String candidateFlag) {
		this.candidateFlag = candidateFlag;
	}
	public String getInterviewrFlag() {
		return interviewrFlag;
	}
	public void setInterviewrFlag(String interviewrFlag) {
		this.interviewrFlag = interviewrFlag;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public String getRadioOne() {
		return RadioOne;
	}
	public void setRadioOne(String radioOne) {
		RadioOne = radioOne;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getThenBY() {
		return thenBY;
	}
	public void setThenBY(String thenBY) {
		this.thenBY = thenBY;
	}
	public String getSecondBy() {
		return secondBy;
	}
	public void setSecondBy(String secondBy) {
		this.secondBy = secondBy;
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
	public String getRadAsc() {
		return radAsc;
	}
	public void setRadAsc(String radAsc) {
		this.radAsc = radAsc;
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
	public String getReqsDateCombo() {
		return reqsDateCombo;
	}
	public void setReqsDateCombo(String reqsDateCombo) {
		this.reqsDateCombo = reqsDateCombo;
	}
	public boolean isTestIntFlag() {
		return testIntFlag;
	}
	public void setTestIntFlag(boolean testIntFlag) {
		this.testIntFlag = testIntFlag;
	}
	public String getCandLen() {
		return candLen;
	}
	public void setCandLen(String candLen) {
		this.candLen = candLen;
	}
	public boolean isNoData() {
		return noData;
	}
	public void setNoData(boolean noData) {
		this.noData = noData;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getIntRound() {
		return intRound;
	}
	public void setIntRound(String intRound) {
		this.intRound = intRound;
	}
	public String getIntDate() {
		return intDate;
	}
	public void setIntDate(String intDate) {
		this.intDate = intDate;
	}
	public String getIntTime() {
		return intTime;
	}
	public void setIntTime(String intTime) {
		this.intTime = intTime;
	}
	public String getIntVenue() {
		return intVenue;
	}
	public void setIntVenue(String intVenue) {
		this.intVenue = intVenue;
	}
	public String getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	public String getRecr() {
		return recr;
	}
	public void setRecr(String recr) {
		this.recr = recr;
	}
	public String getConduct() {
		return conduct;
	}
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}
	public ArrayList getIntList() {
		return intList;
	}
	public void setIntList(ArrayList intList) {
		this.intList = intList;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getViewReqsName() {
		return viewReqsName;
	}
	public void setViewReqsName(String viewReqsName) {
		this.viewReqsName = viewReqsName;
	}
	public String getReqsId() {
		return reqsId;
	}
	public void setReqsId(String reqsId) {
		this.reqsId = reqsId;
	}
	public String getViewReqsDate() {
		return viewReqsDate;
	}
	public void setViewReqsDate(String viewReqsDate) {
		this.viewReqsDate = viewReqsDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getBrn() {
		return brn;
	}
	public void setBrn(String brn) {
		this.brn = brn;
	}
	public String getHrngMgr() {
		return hrngMgr;
	}
	public void setHrngMgr(String hrngMgr) {
		this.hrngMgr = hrngMgr;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public String getTestCandName() {
		return testCandName;
	}
	public void setTestCandName(String testCandName) {
		this.testCandName = testCandName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContac() {
		return contac;
	}
	public void setContac(String contac) {
		this.contac = contac;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getTestVenue() {
		return testVenue;
	}
	public void setTestVenue(String testVenue) {
		this.testVenue = testVenue;
	}
	public String getTestRecr() {
		return testRecr;
	}
	public void setTestRecr(String testRecr) {
		this.testRecr = testRecr;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setTestList(ArrayList testList) {
		this.testList = testList;
	}
	public ArrayList getTestList() {
		return testList;
	}
	public String getRadioThree() {
		return RadioThree;
	}
	public void setRadioThree(String radioThree) {
		RadioThree = radioThree;
	}
	public String getRadioTwo() {
		return RadioTwo;
	}
	public void setRadioTwo(String radioTwo) {
		RadioTwo = radioTwo;
	}
	public String getHidreqCheck() {
		return hidreqCheck;
	}
	public void setHidreqCheck(String hidreqCheck) {
		this.hidreqCheck = hidreqCheck;
	}
	public String getHidcandCheck() {
		return hidcandCheck;
	}
	public void setHidcandCheck(String hidcandCheck) {
		this.hidcandCheck = hidcandCheck;
	}
	public String getHidintCheck() {
		return hidintCheck;
	}
	public void setHidintCheck(String hidintCheck) {
		this.hidintCheck = hidintCheck;
	}
	public String getHidintDateCheck() {
		return hidintDateCheck;
	}
	public void setHidintDateCheck(String hidintDateCheck) {
		this.hidintDateCheck = hidintDateCheck;
	}
	public String getHidposCheck() {
		return hidposCheck;
	}
	public void setHidposCheck(String hidposCheck) {
		this.hidposCheck = hidposCheck;
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
	public String getItschdDate() {
		return itschdDate;
	}
	public void setItschdDate(String itschdDate) {
		this.itschdDate = itschdDate;
	}
	public String getItschType() {
		return itschType;
	}
	public void setItschType(String itschType) {
		this.itschType = itschType;
	}
	public String getItReqCode() {
		return itReqCode;
	}
	public void setItReqCode(String itReqCode) {
		this.itReqCode = itReqCode;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public ArrayList getDispList() {
		return dispList;
	}
	public void setDispList(ArrayList dispList) {
		this.dispList = dispList;
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
	public String getIntrvCode() {
		return intrvCode;
	}
	public void setIntrvCode(String intrvCode) {
		this.intrvCode = intrvCode;
	}
	public String getIntStatus() {
		return intStatus;
	}
	public void setIntStatus(String intStatus) {
		this.intStatus = intStatus;
	}
	public String getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getCandCheckHd() {
		return candCheckHd;
	}
	public void setCandCheckHd(String candCheckHd) {
		this.candCheckHd = candCheckHd;
	}
	public String getInterviewrHd() {
		return interviewrHd;
	}
	public void setInterviewrHd(String interviewrHd) {
		this.interviewrHd = interviewrHd;
	}
	public String getDateHd() {
		return dateHd;
	}
	public void setDateHd(String dateHd) {
		this.dateHd = dateHd;
	}
	public String getSettingTitel() {
		return settingTitel;
	}
	public void setSettingTitel(String settingTitel) {
		this.settingTitel = settingTitel;
	}
	public String getEmailCheckHd() {
		return EmailCheckHd;
	}
	public void setEmailCheckHd(String emailCheckHd) {
		EmailCheckHd = emailCheckHd;
	}
	public String getContactCheckHd() {
		return contactCheckHd;
	}
	public void setContactCheckHd(String contactCheckHd) {
		this.contactCheckHd = contactCheckHd;
	}
	public String getTestVenueHd() {
		return testVenueHd;
	}
	public void setTestVenueHd(String testVenueHd) {
		this.testVenueHd = testVenueHd;
	}
	public String getTestTimeHd() {
		return testTimeHd;
	}
	public void setTestTimeHd(String testTimeHd) {
		this.testTimeHd = testTimeHd;
	}
	public String getTestTimeFlag() {
		return testTimeFlag;
	}
	public void setTestTimeFlag(String testTimeFlag) {
		this.testTimeFlag = testTimeFlag;
	}
	public String getTestVenueFlag() {
		return testVenueFlag;
	}
	public void setTestVenueFlag(String testVenueFlag) {
		this.testVenueFlag = testVenueFlag;
	}
	public String getContactFlag() {
		return contactFlag;
	}
	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}
	public String getHidrecruitNameCheck() {
		return hidrecruitNameCheck;
	}
	public void setHidrecruitNameCheck(String hidrecruitNameCheck) {
		this.hidrecruitNameCheck = hidrecruitNameCheck;
	}
	public String getEmailCheck() {
		return EmailCheck;
	}
	public void setEmailCheck(String emailCheck) {
		EmailCheck = emailCheck;
	}
	public String getContactCheck() {
		return contactCheck;
	}
	public void setContactCheck(String contactCheck) {
		this.contactCheck = contactCheck;
	}
	public String getTimeCheck() {
		return timeCheck;
	}
	public void setTimeCheck(String timeCheck) {
		this.timeCheck = timeCheck;
	}
	public String getTestvenueCheck() {
		return testvenueCheck;
	}
	public void setTestvenueCheck(String testvenueCheck) {
		this.testvenueCheck = testvenueCheck;
	}
	public String getRecruiterFlag() {
		return recruiterFlag;
	}
	public void setRecruiterFlag(String recruiterFlag) {
		this.recruiterFlag = recruiterFlag;
	}
	public String getHidintRoundCheck() {
		return hidintRoundCheck;
	}
	public void setHidintRoundCheck(String hidintRoundCheck) {
		this.hidintRoundCheck = hidintRoundCheck;
	}
	public String getHidintervewDateCheck() {
		return hidintervewDateCheck;
	}
	public void setHidintervewDateCheck(String hidintervewDateCheck) {
		this.hidintervewDateCheck = hidintervewDateCheck;
	}
	public String getHidIntervenueCheck() {
		return hidIntervenueCheck;
	}
	public void setHidIntervenueCheck(String hidIntervenueCheck) {
		this.hidIntervenueCheck = hidIntervenueCheck;
	}
	public String getHidInterviewerCheck() {
		return hidInterviewerCheck;
	}
	public void setHidInterviewerCheck(String hidInterviewerCheck) {
		this.hidInterviewerCheck = hidInterviewerCheck;
	}
	public String getHidIntRecruiterCheck() {
		return hidIntRecruiterCheck;
	}
	public void setHidIntRecruiterCheck(String hidIntRecruiterCheck) {
		this.hidIntRecruiterCheck = hidIntRecruiterCheck;
	}
	public String getHidconductCheck() {
		return hidconductCheck;
	}
	public void setHidconductCheck(String hidconductCheck) {
		this.hidconductCheck = hidconductCheck;
	}
	public String getHidinterviewewCheck() {
		return hidinterviewewCheck;
	}
	public void setHidinterviewewCheck(String hidinterviewewCheck) {
		this.hidinterviewewCheck = hidinterviewewCheck;
	}
	public String getHidIntrvcandCheck() {
		return hidIntrvcandCheck;
	}
	public void setHidIntrvcandCheck(String hidIntrvcandCheck) {
		this.hidIntrvcandCheck = hidIntrvcandCheck;
	}
	public String getHidIntervewtimeCheck() {
		return hidIntervewtimeCheck;
	}
	public void setHidIntervewtimeCheck(String hidIntervewtimeCheck) {
		this.hidIntervewtimeCheck = hidIntervewtimeCheck;
	}
	public String getIntrvCandidateHd() {
		return intrvCandidateHd;
	}
	public void setIntrvCandidateHd(String intrvCandidateHd) {
		this.intrvCandidateHd = intrvCandidateHd;
	}
	public String getIntRndTypeHd() {
		return intRndTypeHd;
	}
	public void setIntRndTypeHd(String intRndTypeHd) {
		this.intRndTypeHd = intRndTypeHd;
	}
	public String getIntrvVenueHd() {
		return intrvVenueHd;
	}
	public void setIntrvVenueHd(String intrvVenueHd) {
		this.intrvVenueHd = intrvVenueHd;
	}
	public String getIntrvDateHd() {
		return intrvDateHd;
	}
	public void setIntrvDateHd(String intrvDateHd) {
		this.intrvDateHd = intrvDateHd;
	}
	public String getIntrvTimeHd() {
		return intrvTimeHd;
	}
	public void setIntrvTimeHd(String intrvTimeHd) {
		this.intrvTimeHd = intrvTimeHd;
	}
	public String getRecruiterIntHd() {
		return recruiterIntHd;
	}
	public void setRecruiterIntHd(String recruiterIntHd) {
		this.recruiterIntHd = recruiterIntHd;
	}
	public String getConductHd() {
		return conductHd;
	}
	public void setConductHd(String conductHd) {
		this.conductHd = conductHd;
	}
	public String getIntrviewerHd() {
		return intrviewerHd;
	}
	public void setIntrviewerHd(String intrviewerHd) {
		this.intrviewerHd = intrviewerHd;
	}
	public String getIntrvCandidateFlag() {
		return intrvCandidateFlag;
	}
	public void setIntrvCandidateFlag(String intrvCandidateFlag) {
		this.intrvCandidateFlag = intrvCandidateFlag;
	}
	public String getIntRndTypeFlag() {
		return intRndTypeFlag;
	}
	public void setIntRndTypeFlag(String intRndTypeFlag) {
		this.intRndTypeFlag = intRndTypeFlag;
	}
	public String getIntrvDateFlag() {
		return intrvDateFlag;
	}
	public void setIntrvDateFlag(String intrvDateFlag) {
		this.intrvDateFlag = intrvDateFlag;
	}
	public String getIntrvTimeFlag() {
		return intrvTimeFlag;
	}
	public void setIntrvTimeFlag(String intrvTimeFlag) {
		this.intrvTimeFlag = intrvTimeFlag;
	}
	public String getIntrvVenueFlag() {
		return intrvVenueFlag;
	}
	public void setIntrvVenueFlag(String intrvVenueFlag) {
		this.intrvVenueFlag = intrvVenueFlag;
	}
	public String getIntrviewerFlag() {
		return intrviewerFlag;
	}
	public void setIntrviewerFlag(String intrviewerFlag) {
		this.intrviewerFlag = intrviewerFlag;
	}
	public String getRecruiterIntFlag() {
		return recruiterIntFlag;
	}
	public void setRecruiterIntFlag(String recruiterIntFlag) {
		this.recruiterIntFlag = recruiterIntFlag;
	}
	public String getConductFlag() {
		return conductFlag;
	}
	public void setConductFlag(String conductFlag) {
		this.conductFlag = conductFlag;
	}
	public String getSchdTypeVal() {
		return schdTypeVal;
	}
	public void setSchdTypeVal(String schdTypeVal) {
		this.schdTypeVal = schdTypeVal;
	}
	public String getTestInterviewRadio() {
		return testInterviewRadio;
	}
	public void setTestInterviewRadio(String testInterviewRadio) {
		this.testInterviewRadio = testInterviewRadio;
	}
	public String getTypeFlag() {
		return typeFlag;
	}
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}
	public String getSelecteReqCode() {
		return selecteReqCode;
	}
	public void setSelecteReqCode(String selecteReqCode) {
		this.selecteReqCode = selecteReqCode;
	}
	public String getSelecteReqName() {
		return selecteReqName;
	}
	public void setSelecteReqName(String selecteReqName) {
		this.selecteReqName = selecteReqName;
	}
	public HashMap getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}
	public String getSeltype() {
		return seltype;
	}
	public void setSeltype(String seltype) {
		this.seltype = seltype;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getIntrvcandidate() {
		return Intrvcandidate;
	}
	public void setIntrvcandidate(String intrvcandidate) {
		Intrvcandidate = intrvcandidate;
	}
	public String getIntRoundType() {
		return intRoundType;
	}
	public void setIntRoundType(String intRoundType) {
		this.intRoundType = intRoundType;
	}
	public String getIntervewDate() {
		return intervewDate;
	}
	public void setIntervewDate(String intervewDate) {
		this.intervewDate = intervewDate;
	}
	public String getIntervewTime() {
		return intervewTime;
	}
	public void setIntervewTime(String intervewTime) {
		this.intervewTime = intervewTime;
	}
	public String getInterviewVenue() {
		return interviewVenue;
	}
	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
	}
	public String getInterviewRecruietr() {
		return interviewRecruietr;
	}
	public void setInterviewRecruietr(String interviewRecruietr) {
		this.interviewRecruietr = interviewRecruietr;
	}
	public String getIntConduct() {
		return intConduct;
	}
	public void setIntConduct(String intConduct) {
		this.intConduct = intConduct;
	}
	public String getTestStatusHd() {
		return testStatusHd;
	}
	public void setTestStatusHd(String testStatusHd) {
		this.testStatusHd = testStatusHd;
	}
	public String getTestStatusFlag() {
		return testStatusFlag;
	}
	public void setTestStatusFlag(String testStatusFlag) {
		this.testStatusFlag = testStatusFlag;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getHidtestStatusCheck() {
		return hidtestStatusCheck;
	}
	public void setHidtestStatusCheck(String hidtestStatusCheck) {
		this.hidtestStatusCheck = hidtestStatusCheck;
	}
	public String getItreqsiName() {
		return itreqsiName;
	}
	public void setItreqsiName(String itreqsiName) {
		this.itreqsiName = itreqsiName;
	}
	public String getItconducted() {
		return itconducted;
	}
	public void setItconducted(String itconducted) {
		this.itconducted = itconducted;
	}
	public String getItnonconducted() {
		return itnonconducted;
	}
	public void setItnonconducted(String itnonconducted) {
		this.itnonconducted = itnonconducted;
	}
	public String getItcanceled() {
		return itcanceled;
	}
	public void setItcanceled(String itcanceled) {
		this.itcanceled = itcanceled;
	}
	public ArrayList getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(ArrayList summaryList) {
		this.summaryList = summaryList;
	}
	public String getSummaryflag() {
		return summaryflag;
	}
	public void setSummaryflag(String summaryflag) {
		this.summaryflag = summaryflag;
	}
	public String getIntStatusReport() {
		return intStatusReport;
	}
	public void setIntStatusReport(String intStatusReport) {
		this.intStatusReport = intStatusReport;
	}
	public boolean getMyChkFlag() {
		return myChkFlag;
	}
	public void setMyChkFlag(boolean myChkFlag) {
		this.myChkFlag = myChkFlag;
	}
	public boolean getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(boolean buttonFlag) {
		this.buttonFlag = buttonFlag;
	}
	public boolean getRadIntFlag() {
		return radIntFlag;
	}
	public void setRadIntFlag(boolean radIntFlag) {
		this.radIntFlag = radIntFlag;
	}
	public boolean getIntCheckBoxFlag() {
		return intCheckBoxFlag;
	}
	public void setIntCheckBoxFlag(boolean intCheckBoxFlag) {
		this.intCheckBoxFlag = intCheckBoxFlag;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getNoDataInt() {
		return noDataInt;
	}
	public void setNoDataInt(String noDataInt) {
		this.noDataInt = noDataInt;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getTotalPage1() {
		return totalPage1;
	}
	public void setTotalPage1(String totalPage1) {
		this.totalPage1 = totalPage1;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoFieldFirst() {
		return pageNoFieldFirst;
	}
	public void setPageNoFieldFirst(String pageNoFieldFirst) {
		this.pageNoFieldFirst = pageNoFieldFirst;
	}
	public String getHeaderChk() {
		return headerChk;
	}
	public void setHeaderChk(String headerChk) {
		this.headerChk = headerChk;
	}
	public String getListChk() {
		return listChk;
	}
	public void setListChk(String listChk) {
		this.listChk = listChk;
	}
	public String getNoRecords() {
		return noRecords;
	}
	public void setNoRecords(String noRecords) {
		this.noRecords = noRecords;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getAId() {
		return aId;
	}
	public void setAId(String id) {
		aId = id;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getTestStatus1() {
		return testStatus1;
	}
	public void setTestStatus1(String testStatus1) {
		this.testStatus1 = testStatus1;
	}
	public String getRequisitionRadioCheck() {
		return requisitionRadioCheck;
	}
	public void setRequisitionRadioCheck(String requisitionRadioCheck) {
		this.requisitionRadioCheck = requisitionRadioCheck;
	}
	public String getCandidateRadioCheck() {
		return candidateRadioCheck;
	}
	public void setCandidateRadioCheck(String candidateRadioCheck) {
		this.candidateRadioCheck = candidateRadioCheck;
	}
	public String getPositionRadioCheck() {
		return positionRadioCheck;
	}
	public void setPositionRadioCheck(String positionRadioCheck) {
		this.positionRadioCheck = positionRadioCheck;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getMyPageReq() {
		return myPageReq;
	}
	public void setMyPageReq(String myPageReq) {
		this.myPageReq = myPageReq;
	}
	public String getHdPageReq() {
		return hdPageReq;
	}
	public void setHdPageReq(String hdPageReq) {
		this.hdPageReq = hdPageReq;
	}
	public String getPageNoFieldReq() {
		return pageNoFieldReq;
	}
	public void setPageNoFieldReq(String pageNoFieldReq) {
		this.pageNoFieldReq = pageNoFieldReq;
	}
	public String getShowReq() {
		return showReq;
	}
	public void setShowReq(String showReq) {
		this.showReq = showReq;
	}
	public String getExportAll_bot() {
		return exportAll_bot;
	}
	public void setExportAll_bot(String exportAll_bot) {
		this.exportAll_bot = exportAll_bot;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public String getSchdType() {
		return schdType;
	}
	public void setSchdType(String schdType) {
		this.schdType = schdType;
	}
	
	
	
	

}
