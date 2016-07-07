/**
 * 
 */
package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class AppraisalCalendar extends BeanBase {
	HashMap hashmapType;
	HashMap hashmapTypeSel;
	HashMap hashmapGrade;
	HashMap hashmapGradeSel;
	HashMap hashmapDept;
	HashMap hashmapDeptSel;
	HashMap hashmapDiv;
	HashMap hashmapDivSel;
	private String isScoreShow="";
	private String hiddenisScoreShow="";
	private String importPhaseIdFlag="";
	private String importAppriaserFlag="";
	private String importTemplateFlag="";
	private String hiddenimportPhaseIdFlag="";
	private String hiddenimportAppriaserFlag="";
	private String hiddenimportTemplateFlag="";
	private String hiddenmultipleGoal="";
	private String compWeightage="";
	private String multipleGoal="";
	private String ittrGoalName="";
	private String ittrGoalFromDate="";
	private String ittrGoalToDate="";
	private String ittrGoalWeightage="";
	private String ittrGoalId="";
	ArrayList list;
	private String srNo="";
	private String goalName="";
	private String hidengoalStartDate="";
	private String hidengoalEndDate="";
	private String goalId="";
	private String compName="";
	private String compId="";
	private String goalWeightage="";
	private String goalMapCheck="";
	private String hiddengoalMap="";
	private String competencyMapCheck="";
	private String hiddencompetencyMap="";
	private String appraisalIdPhase;
	private String isStarted;
	private String isStartedList;
	private String availType;
	private String selType;
	private String availDiv;
	private String selDiv;
	private String availGrade;
	private String selGrade;
	private String availDept;
	private String selDept;
	private String view="false";
	private String edit;
	private String addNew;
	
	private String importStartDate;
	private String importEndDate;
	private String appraisalCode;
	private String appraisalId;
	private String validTill;
	private String startDate;
	private String endDate;
	private String repeatFreq;
	private String autoStart;
	private String hideAutoStart;
	private String importConfig;
	private String hideImportConfig;
	private String importAppraisalCode;
	private String importAppraisalID;
	private String importContentConfig;
	private String hideImportContentConfig;
	private String appraisalCodePhase;
	private String appraisalIdPhases;
	private String appraisalCodeParameters;
	private String appraisalIdParameters;
	private String appraisalCodeRating;
	private String appraisalIdRating;
	private String appraisalCodeAppraisers;
	private String appraisalIdAppraisers;
	private String appraisalCodeTemplate;
	private String appraisalIdTemplate;
	private String appraisalCodeMapping;
	private String appraisalIdMapping;
	
	private String joinDateCheck;
	private String hideJoinDateCheck;
	private String joinDateCondition;
	private String hideJoinDateCondition;
	private String joinDate;
	private String joinFromDate;
	private String joinToDate;
	private String empTypeCheck;
	private String hideEmpTypeCheck;
	private String empGradeCheck;
	private String hideEmpGradeCheck;
	private String empDeptCheck;
	private String hideEmpDeptCheck;
	private String empDivCheck;
	private String hideEmpDivCheck;
	private String comments;
	private String paraId;
	private String onloadMode="true";
	private String hdeleteCode;
	/*
	 * for Eligible Employee List
	 * 
	 */
	private String myPage;
	private String show;
	private String empIdAdd;
	private String empNameAdd;
	private String empCodeAdd;
	private String empJoinDateAdd;
	private String empTypeAdd;
	private String empGradeAdd;
	private String empDeptAdd;
	private String empIdSearch;
	private String empNameSearch;
	private String empCodeSearch;
	ArrayList empList;
	private String empCodeList;
	private String empNameList;
	private String empJoinDateList;
	private String empTypeList;
	private String empGradeList;
	private String empDeptList;
	private String saveEligibleFlag="true";
	private String criteriaType;
	
	/*
	 * for Calendar List
	 * 
	 */
	ArrayList calendarList;
	private String appraisalCodeList;
	private String appraisalIdList;
	private String validTillList;
	private String startDateList;
	private String endDateList;
	private String repeatFreqList;
	private String autoStartList;
	private String noData ="false";
	private String totalRecords="";
	
	
	//Appraisal Phase Config start
	String apprId;
	String hApprId;
	String apprCode;
	String apprName;
	String frmDate;
	String toDate;
	String phaseId;
	//MAIN PHASE VARS
	String phase;	
	String pOrder;
	String pRatingCheck;
	String pRating;	
	String pWeightage;
	String pStatus="A";
	String pDescr;
	String pQueWeight;
	///PHASE LIST VARS
	String hPhase;	
	String hOrder;
	String hRating;
	String hWeightage;
	String hStatus;
	String hDescr;
	String hQueWeight;
	String totalWeightage;	
	String sNo;
	String removePhases;
	String viewMode="false";
	String editMode="false";
	String mode;
	String removeFlag="false";
	String phaseRating;
	String calId;
	String calCode;
	String startdate;
	String appStarted;
	String newFlag="false";
	ArrayList phaseList;//Phase list 
	ArrayList calList;//Calendara list
	
	//Appraisal Phase Config end
	
	// rating scale definition start
	
	private ArrayList lstAppraisal = null;
	private boolean flglstAppraisal = false;
	
	private String sAppId = null;
	private String sAppCode = null;
	private String sAppStartDate;
	private String sAppEndDate;
		
	/* Question Rating Parameter */
	private int iSrNo = 0;
	private String sAppRatingId = null;
	private String sAppMinRating = null;
	private String sAppMaxRating = null;
	private String sAllowFraction = null;
	private String sAllowFractionFlg = null;
	private String sRatingType=null;
	private ArrayList lstQuestionRatingList = null;
	private boolean flgQuestionRatingList = false;
	
	private String addFlg = null;
	
	/* Question Rating  List */
	private String sDtlId = null;
	private String sAppQuestionRatingName = null;
	private String sAppQuestionRatingValue = null;
	
	private String sAppScoreFlg = null;
	private int iflg;
	
	/* Overall Score Description */
	private int iSrNoOverall = 0;
	private String sAppScoreId = null;
	private String sAppOverAllScoreFrom = null;
	private String sAppOverAllScoreTo = null;
	private String sAppOverAllScoreValue = null;
	private String sAppOverAllScoreDesc = null;
	private String sAppOverAllBellCurve = null;
	private ArrayList lstOverAllScoreList = null;
	private boolean flgOverAllScoreList = false;
	private String sTotalBullCurve = null;
	
	/* Navigation and Mode Parameter */
	private String sMode = null;
	
	private String hiddencode;
	private String readFlag="true";
	// rating scale definition end
	
	private String addFlag = "false";
	private String phaseCode;
	private String phaseName;
	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getOnloadMode() {
		return onloadMode;
	}

	public void setOnloadMode(String onloadMode) {
		this.onloadMode = onloadMode;
	}

	public String getEmpCodeList() {
		return empCodeList;
	}

	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}

	public String getEmpNameList() {
		return empNameList;
	}

	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}

	public String getEmpJoinDateList() {
		return empJoinDateList;
	}

	public void setEmpJoinDateList(String empJoinDateList) {
		this.empJoinDateList = empJoinDateList;
	}

	public String getEmpTypeList() {
		return empTypeList;
	}

	public void setEmpTypeList(String empTypeList) {
		this.empTypeList = empTypeList;
	}

	public String getEmpGradeList() {
		return empGradeList;
	}

	public void setEmpGradeList(String empGradeList) {
		this.empGradeList = empGradeList;
	}

	public String getEmpDeptList() {
		return empDeptList;
	}

	public void setEmpDeptList(String empDeptList) {
		this.empDeptList = empDeptList;
	}

	public ArrayList getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}

	public String getEmpIdAdd() {
		return empIdAdd;
	}

	public void setEmpIdAdd(String empIdAdd) {
		this.empIdAdd = empIdAdd;
	}

	public String getEmpNameAdd() {
		return empNameAdd;
	}

	public void setEmpNameAdd(String empNameAdd) {
		this.empNameAdd = empNameAdd;
	}

	public String getEmpCodeAdd() {
		return empCodeAdd;
	}

	public void setEmpCodeAdd(String empCodeAdd) {
		this.empCodeAdd = empCodeAdd;
	}

	public String getEmpIdSearch() {
		return empIdSearch;
	}

	public void setEmpIdSearch(String empIdSearch) {
		this.empIdSearch = empIdSearch;
	}

	public String getEmpNameSearch() {
		return empNameSearch;
	}

	public void setEmpNameSearch(String empNameSearch) {
		this.empNameSearch = empNameSearch;
	}

	public String getEmpCodeSearch() {
		return empCodeSearch;
	}

	public void setEmpCodeSearch(String empCodeSearch) {
		this.empCodeSearch = empCodeSearch;
	}

	public HashMap getHashmapDept() {
		return hashmapDept;
	}

	public void setHashmapDept(HashMap hashmapDept) {
		this.hashmapDept = hashmapDept;
	}

	public String getAvailDept() {
		return availDept;
	}

	public void setAvailDept(String availDept) {
		this.availDept = availDept;
	}

	

	public String getSelDept() {
		return selDept;
	}

	public void setSelDept(String selDept) {
		this.selDept = selDept;
	}

	public String getAvailType() {
		return availType;
	}

	public void setAvailType(String availType) {
		this.availType = availType;
	}

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}

	
	public String getAvailGrade() {
		return availGrade;
	}

	public void setAvailGrade(String availGrade) {
		this.availGrade = availGrade;
	}

	public String getSelGrade() {
		return selGrade;
	}

	public void setSelGrade(String selGrade) {
		this.selGrade = selGrade;
	}

	public HashMap getHashmapType() {
		return hashmapType;
	}

	public void setHashmapType(HashMap hashmapType) {
		this.hashmapType = hashmapType;
	}

	public HashMap getHashmapGrade() {
		return hashmapGrade;
	}

	public void setHashmapGrade(HashMap hashmapGrade) {
		this.hashmapGrade = hashmapGrade;
	}

	public String getImportStartDate() {
		return importStartDate;
	}

	public void setImportStartDate(String importStartDate) {
		this.importStartDate = importStartDate;
	}

	public String getImportEndDate() {
		return importEndDate;
	}

	public void setImportEndDate(String importEndDate) {
		this.importEndDate = importEndDate;
	}

	public String getAppraisalCode() {
		return appraisalCode;
	}

	public void setAppraisalCode(String appraisalCode) {
		this.appraisalCode = appraisalCode;
	}

	public String getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRepeatFreq() {
		return repeatFreq;
	}

	public void setRepeatFreq(String repeatFreq) {
		this.repeatFreq = repeatFreq;
	}

	public String getAutoStart() {
		return autoStart;
	}

	public void setAutoStart(String autoStart) {
		this.autoStart = autoStart;
	}

	public String getImportConfig() {
		return importConfig;
	}

	public void setImportConfig(String importConfig) {
		this.importConfig = importConfig;
	}

	public String getImportAppraisalCode() {
		return importAppraisalCode;
	}

	public void setImportAppraisalCode(String importAppraisalCode) {
		this.importAppraisalCode = importAppraisalCode;
	}

	public String getImportAppraisalID() {
		return importAppraisalID;
	}

	public void setImportAppraisalID(String importAppraisalID) {
		this.importAppraisalID = importAppraisalID;
	}

	public String getImportContentConfig() {
		return importContentConfig;
	}

	public void setImportContentConfig(String importContentConfig) {
		this.importContentConfig = importContentConfig;
	}

	public String getAppraisalCodePhase() {
		return appraisalCodePhase;
	}

	public void setAppraisalCodePhase(String appraisalCodePhase) {
		this.appraisalCodePhase = appraisalCodePhase;
	}

	public String getAppraisalIdPhases() {
		return appraisalIdPhases;
	}

	public void setAppraisalIdPhases(String appraisalIdPhases) {
		this.appraisalIdPhases = appraisalIdPhases;
	}

	public String getAppraisalCodeParameters() {
		return appraisalCodeParameters;
	}

	public void setAppraisalCodeParameters(String appraisalCodeParameters) {
		this.appraisalCodeParameters = appraisalCodeParameters;
	}

	public String getAppraisalIdParameters() {
		return appraisalIdParameters;
	}

	public void setAppraisalIdParameters(String appraisalIdParameters) {
		this.appraisalIdParameters = appraisalIdParameters;
	}

	public String getAppraisalCodeRating() {
		return appraisalCodeRating;
	}

	public void setAppraisalCodeRating(String appraisalCodeRating) {
		this.appraisalCodeRating = appraisalCodeRating;
	}

	public String getAppraisalIdRating() {
		return appraisalIdRating;
	}

	public void setAppraisalIdRating(String appraisalIdRating) {
		this.appraisalIdRating = appraisalIdRating;
	}

	public String getAppraisalCodeAppraisers() {
		return appraisalCodeAppraisers;
	}

	public void setAppraisalCodeAppraisers(String appraisalCodeAppraisers) {
		this.appraisalCodeAppraisers = appraisalCodeAppraisers;
	}

	public String getAppraisalIdAppraisers() {
		return appraisalIdAppraisers;
	}

	public void setAppraisalIdAppraisers(String appraisalIdAppraisers) {
		this.appraisalIdAppraisers = appraisalIdAppraisers;
	}

	public String getAppraisalCodeTemplate() {
		return appraisalCodeTemplate;
	}

	public void setAppraisalCodeTemplate(String appraisalCodeTemplate) {
		this.appraisalCodeTemplate = appraisalCodeTemplate;
	}

	public String getAppraisalIdTemplate() {
		return appraisalIdTemplate;
	}

	public void setAppraisalIdTemplate(String appraisalIdTemplate) {
		this.appraisalIdTemplate = appraisalIdTemplate;
	}

	public String getAppraisalCodeMapping() {
		return appraisalCodeMapping;
	}

	public void setAppraisalCodeMapping(String appraisalCodeMapping) {
		this.appraisalCodeMapping = appraisalCodeMapping;
	}

	public String getAppraisalIdMapping() {
		return appraisalIdMapping;
	}

	public void setAppraisalIdMapping(String appraisalIdMapping) {
		this.appraisalIdMapping = appraisalIdMapping;
	}

	public String getJoinDateCheck() {
		return joinDateCheck;
	}

	public void setJoinDateCheck(String joinDateCheck) {
		this.joinDateCheck = joinDateCheck;
	}

	public String getJoinDateCondition() {
		return joinDateCondition;
	}

	public void setJoinDateCondition(String joinDateCondition) {
		this.joinDateCondition = joinDateCondition;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getJoinFromDate() {
		return joinFromDate;
	}

	public void setJoinFromDate(String joinFromDate) {
		this.joinFromDate = joinFromDate;
	}

	public String getJoinToDate() {
		return joinToDate;
	}

	public void setJoinToDate(String joinToDate) {
		this.joinToDate = joinToDate;
	}

	public String getEmpTypeCheck() {
		return empTypeCheck;
	}

	public void setEmpTypeCheck(String empTypeCheck) {
		this.empTypeCheck = empTypeCheck;
	}

	public String getEmpGradeCheck() {
		return empGradeCheck;
	}

	public void setEmpGradeCheck(String empGradeCheck) {
		this.empGradeCheck = empGradeCheck;
	}

	public String getEmpDeptCheck() {
		return empDeptCheck;
	}

	public void setEmpDeptCheck(String empDeptCheck) {
		this.empDeptCheck = empDeptCheck;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHideAutoStart() {
		return hideAutoStart;
	}

	public void setHideAutoStart(String hideAutoStart) {
		this.hideAutoStart = hideAutoStart;
	}

	public String getHideImportConfig() {
		return hideImportConfig;
	}

	public void setHideImportConfig(String hideImportConfig) {
		this.hideImportConfig = hideImportConfig;
	}

	public String getHideImportContentConfig() {
		return hideImportContentConfig;
	}

	public void setHideImportContentConfig(String hideImportContentConfig) {
		this.hideImportContentConfig = hideImportContentConfig;
	}

	public String getHideJoinDateCheck() {
		return hideJoinDateCheck;
	}

	public void setHideJoinDateCheck(String hideJoinDateCheck) {
		this.hideJoinDateCheck = hideJoinDateCheck;
	}

	public String getHideEmpTypeCheck() {
		return hideEmpTypeCheck;
	}

	public void setHideEmpTypeCheck(String hideEmpTypeCheck) {
		this.hideEmpTypeCheck = hideEmpTypeCheck;
	}

	public String getHideEmpGradeCheck() {
		return hideEmpGradeCheck;
	}

	public void setHideEmpGradeCheck(String hideEmpGradeCheck) {
		this.hideEmpGradeCheck = hideEmpGradeCheck;
	}

	public String getHideEmpDeptCheck() {
		return hideEmpDeptCheck;
	}

	public void setHideEmpDeptCheck(String hideEmpDeptCheck) {
		this.hideEmpDeptCheck = hideEmpDeptCheck;
	}

	public HashMap getHashmapTypeSel() {
		return hashmapTypeSel;
	}

	public void setHashmapTypeSel(HashMap hashmapTypeSel) {
		this.hashmapTypeSel = hashmapTypeSel;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public ArrayList getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(ArrayList calendarList) {
		this.calendarList = calendarList;
	}

	public String getAppraisalCodeList() {
		return appraisalCodeList;
	}

	public void setAppraisalCodeList(String appraisalCodeList) {
		this.appraisalCodeList = appraisalCodeList;
	}

	public String getAppraisalIdList() {
		return appraisalIdList;
	}

	public void setAppraisalIdList(String appraisalIdList) {
		this.appraisalIdList = appraisalIdList;
	}

	public String getValidTillList() {
		return validTillList;
	}

	public void setValidTillList(String validTillList) {
		this.validTillList = validTillList;
	}

	public String getStartDateList() {
		return startDateList;
	}

	public void setStartDateList(String startDateList) {
		this.startDateList = startDateList;
	}

	public String getEndDateList() {
		return endDateList;
	}

	public void setEndDateList(String endDateList) {
		this.endDateList = endDateList;
	}

	public String getRepeatFreqList() {
		return repeatFreqList;
	}

	public void setRepeatFreqList(String repeatFreqList) {
		this.repeatFreqList = repeatFreqList;
	}

	public String getAutoStartList() {
		return autoStartList;
	}

	public void setAutoStartList(String autoStartList) {
		this.autoStartList = autoStartList;
	}

	public HashMap getHashmapGradeSel() {
		return hashmapGradeSel;
	}

	public void setHashmapGradeSel(HashMap hashmapGradeSel) {
		this.hashmapGradeSel = hashmapGradeSel;
	}

	public HashMap getHashmapDeptSel() {
		return hashmapDeptSel;
	}

	public void setHashmapDeptSel(HashMap hashmapDeptSel) {
		this.hashmapDeptSel = hashmapDeptSel;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getAddNew() {
		return addNew;
	}

	public void setAddNew(String addNew) {
		this.addNew = addNew;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getHideJoinDateCondition() {
		return hideJoinDateCondition;
	}

	public void setHideJoinDateCondition(String hideJoinDateCondition) {
		this.hideJoinDateCondition = hideJoinDateCondition;
	}


	public String getEmpJoinDateAdd() {
		return empJoinDateAdd;
	}

	public void setEmpJoinDateAdd(String empJoinDateAdd) {
		this.empJoinDateAdd = empJoinDateAdd;
	}

	public String getEmpTypeAdd() {
		return empTypeAdd;
	}

	public void setEmpTypeAdd(String empTypeAdd) {
		this.empTypeAdd = empTypeAdd;
	}

	public String getEmpGradeAdd() {
		return empGradeAdd;
	}

	public void setEmpGradeAdd(String empGradeAdd) {
		this.empGradeAdd = empGradeAdd;
	}

	public String getEmpDeptAdd() {
		return empDeptAdd;
	}

	public void setEmpDeptAdd(String empDeptAdd) {
		this.empDeptAdd = empDeptAdd;
	}

	public String getSaveEligibleFlag() {
		return saveEligibleFlag;
	}

	public void setSaveEligibleFlag(String saveEligibleFlag) {
		this.saveEligibleFlag = saveEligibleFlag;
	}

	public String getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getIsStarted() {
		return isStarted;
	}

	public void setIsStarted(String isStarted) {
		this.isStarted = isStarted;
	}

	public String getIsStartedList() {
		return isStartedList;
	}

	public void setIsStartedList(String isStartedList) {
		this.isStartedList = isStartedList;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getAvailDiv() {
		return availDiv;
	}

	public void setAvailDiv(String availDiv) {
		this.availDiv = availDiv;
	}

	public String getSelDiv() {
		return selDiv;
	}

	public void setSelDiv(String selDiv) {
		this.selDiv = selDiv;
	}

	public String getEmpDivCheck() {
		return empDivCheck;
	}

	public void setEmpDivCheck(String empDivCheck) {
		this.empDivCheck = empDivCheck;
	}

	public String getHideEmpDivCheck() {
		return hideEmpDivCheck;
	}

	public void setHideEmpDivCheck(String hideEmpDivCheck) {
		this.hideEmpDivCheck = hideEmpDivCheck;
	}

	public HashMap getHashmapDiv() {
		return hashmapDiv;
	}

	public void setHashmapDiv(HashMap hashmapDiv) {
		this.hashmapDiv = hashmapDiv;
	}

	public HashMap getHashmapDivSel() {
		return hashmapDivSel;
	}

	public void setHashmapDivSel(HashMap hashmapDivSel) {
		this.hashmapDivSel = hashmapDivSel;
	}

	public String getAppraisalIdPhase() {
		return appraisalIdPhase;
	}

	public void setAppraisalIdPhase(String appraisalIdPhase) {
		this.appraisalIdPhase = appraisalIdPhase;
	}

	public String getGoalMapCheck() {
		return goalMapCheck;
	}

	public void setGoalMapCheck(String goalMapCheck) {
		this.goalMapCheck = goalMapCheck;
	}

	public String getHiddengoalMap() {
		return hiddengoalMap;
	}

	public void setHiddengoalMap(String hiddengoalMap) {
		this.hiddengoalMap = hiddengoalMap;
	}

	public String getCompetencyMapCheck() {
		return competencyMapCheck;
	}

	public void setCompetencyMapCheck(String competencyMapCheck) {
		this.competencyMapCheck = competencyMapCheck;
	}

	public String getHiddencompetencyMap() {
		return hiddencompetencyMap;
	}

	public void setHiddencompetencyMap(String hiddencompetencyMap) {
		this.hiddencompetencyMap = hiddencompetencyMap;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public String getHidengoalStartDate() {
		return hidengoalStartDate;
	}

	public void setHidengoalStartDate(String hidengoalStartDate) {
		this.hidengoalStartDate = hidengoalStartDate;
	}

	public String getHidengoalEndDate() {
		return hidengoalEndDate;
	}

	public void setHidengoalEndDate(String hidengoalEndDate) {
		this.hidengoalEndDate = hidengoalEndDate;
	}

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getGoalWeightage() {
		return goalWeightage;
	}

	public void setGoalWeightage(String goalWeightage) {
		this.goalWeightage = goalWeightage;
	}

	public String getIttrGoalName() {
		return ittrGoalName;
	}

	public void setIttrGoalName(String ittrGoalName) {
		this.ittrGoalName = ittrGoalName;
	}

	public String getIttrGoalFromDate() {
		return ittrGoalFromDate;
	}

	public void setIttrGoalFromDate(String ittrGoalFromDate) {
		this.ittrGoalFromDate = ittrGoalFromDate;
	}

	public String getIttrGoalToDate() {
		return ittrGoalToDate;
	}

	public void setIttrGoalToDate(String ittrGoalToDate) {
		this.ittrGoalToDate = ittrGoalToDate;
	}

	public String getIttrGoalWeightage() {
		return ittrGoalWeightage;
	}

	public void setIttrGoalWeightage(String ittrGoalWeightage) {
		this.ittrGoalWeightage = ittrGoalWeightage;
	}

	public String getIttrGoalId() {
		return ittrGoalId;
	}

	public void setIttrGoalId(String ittrGoalId) {
		this.ittrGoalId = ittrGoalId;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getMultipleGoal() {
		return multipleGoal;
	}

	public void setMultipleGoal(String multipleGoal) {
		this.multipleGoal = multipleGoal;
	}

	public String getCompWeightage() {
		return compWeightage;
	}

	public void setCompWeightage(String compWeightage) {
		this.compWeightage = compWeightage;
	}

	public String getHiddenmultipleGoal() {
		return hiddenmultipleGoal;
	}

	public void setHiddenmultipleGoal(String hiddenmultipleGoal) {
		this.hiddenmultipleGoal = hiddenmultipleGoal;
	}

	public String getImportPhaseIdFlag() {
		return importPhaseIdFlag;
	}

	public void setImportPhaseIdFlag(String importPhaseIdFlag) {
		this.importPhaseIdFlag = importPhaseIdFlag;
	}

	public String getImportAppriaserFlag() {
		return importAppriaserFlag;
	}

	public void setImportAppriaserFlag(String importAppriaserFlag) {
		this.importAppriaserFlag = importAppriaserFlag;
	}

	public String getImportTemplateFlag() {
		return importTemplateFlag;
	}

	public void setImportTemplateFlag(String importTemplateFlag) {
		this.importTemplateFlag = importTemplateFlag;
	}

	public String getHiddenimportPhaseIdFlag() {
		return hiddenimportPhaseIdFlag;
	}

	public void setHiddenimportPhaseIdFlag(String hiddenimportPhaseIdFlag) {
		this.hiddenimportPhaseIdFlag = hiddenimportPhaseIdFlag;
	}

	public String getHiddenimportAppriaserFlag() {
		return hiddenimportAppriaserFlag;
	}

	public void setHiddenimportAppriaserFlag(String hiddenimportAppriaserFlag) {
		this.hiddenimportAppriaserFlag = hiddenimportAppriaserFlag;
	}

	public String getHiddenimportTemplateFlag() {
		return hiddenimportTemplateFlag;
	}

	public void setHiddenimportTemplateFlag(String hiddenimportTemplateFlag) {
		this.hiddenimportTemplateFlag = hiddenimportTemplateFlag;
	}

	public String getIsScoreShow() {
		return isScoreShow;
	}

	public void setIsScoreShow(String isScoreShow) {
		this.isScoreShow = isScoreShow;
	}

	public String getHiddenisScoreShow() {
		return hiddenisScoreShow;
	}

	public void setHiddenisScoreShow(String hiddenisScoreShow) {
		this.hiddenisScoreShow = hiddenisScoreShow;
	}

	/**
	 * @return the apprId
	 */
	public String getApprId() {
		return apprId;
	}

	/**
	 * @param apprId the apprId to set
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	/**
	 * @return the hApprId
	 */
	public String getHApprId() {
		return hApprId;
	}

	/**
	 * @param apprId the hApprId to set
	 */
	public void setHApprId(String apprId) {
		hApprId = apprId;
	}

	/**
	 * @return the apprCode
	 */
	public String getApprCode() {
		return apprCode;
	}

	/**
	 * @param apprCode the apprCode to set
	 */
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}

	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @return the frmDate
	 */
	public String getFrmDate() {
		return frmDate;
	}

	/**
	 * @param frmDate the frmDate to set
	 */
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the phaseId
	 */
	public String getPhaseId() {
		return phaseId;
	}

	/**
	 * @param phaseId the phaseId to set
	 */
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * @return the pOrder
	 */
	public String getPOrder() {
		return pOrder;
	}

	/**
	 * @param order the pOrder to set
	 */
	public void setPOrder(String order) {
		pOrder = order;
	}

	/**
	 * @return the pRatingCheck
	 */
	public String getPRatingCheck() {
		return pRatingCheck;
	}

	/**
	 * @param ratingCheck the pRatingCheck to set
	 */
	public void setPRatingCheck(String ratingCheck) {
		pRatingCheck = ratingCheck;
	}

	/**
	 * @return the pRating
	 */
	public String getPRating() {
		return pRating;
	}

	/**
	 * @param rating the pRating to set
	 */
	public void setPRating(String rating) {
		pRating = rating;
	}

	/**
	 * @return the pWeightage
	 */
	public String getPWeightage() {
		return pWeightage;
	}

	/**
	 * @param weightage the pWeightage to set
	 */
	public void setPWeightage(String weightage) {
		pWeightage = weightage;
	}

	/**
	 * @return the pStatus
	 */
	public String getPStatus() {
		return pStatus;
	}

	/**
	 * @param status the pStatus to set
	 */
	public void setPStatus(String status) {
		pStatus = status;
	}

	/**
	 * @return the pDescr
	 */
	public String getPDescr() {
		return pDescr;
	}

	/**
	 * @param descr the pDescr to set
	 */
	public void setPDescr(String descr) {
		pDescr = descr;
	}

	/**
	 * @return the pQueWeight
	 */
	public String getPQueWeight() {
		return pQueWeight;
	}

	/**
	 * @param queWeight the pQueWeight to set
	 */
	public void setPQueWeight(String queWeight) {
		pQueWeight = queWeight;
	}

	/**
	 * @return the hPhase
	 */
	public String getHPhase() {
		return hPhase;
	}

	/**
	 * @param phase the hPhase to set
	 */
	public void setHPhase(String phase) {
		hPhase = phase;
	}

	/**
	 * @return the hOrder
	 */
	public String getHOrder() {
		return hOrder;
	}

	/**
	 * @param order the hOrder to set
	 */
	public void setHOrder(String order) {
		hOrder = order;
	}

	/**
	 * @return the hRating
	 */
	public String getHRating() {
		return hRating;
	}

	/**
	 * @param rating the hRating to set
	 */
	public void setHRating(String rating) {
		hRating = rating;
	}

	/**
	 * @return the hWeightage
	 */
	public String getHWeightage() {
		return hWeightage;
	}

	/**
	 * @param weightage the hWeightage to set
	 */
	public void setHWeightage(String weightage) {
		hWeightage = weightage;
	}

	/**
	 * @return the hStatus
	 */
	public String getHStatus() {
		return hStatus;
	}

	/**
	 * @param status the hStatus to set
	 */
	public void setHStatus(String status) {
		hStatus = status;
	}

	/**
	 * @return the hDescr
	 */
	public String getHDescr() {
		return hDescr;
	}

	/**
	 * @param descr the hDescr to set
	 */
	public void setHDescr(String descr) {
		hDescr = descr;
	}

	/**
	 * @return the hQueWeight
	 */
	public String getHQueWeight() {
		return hQueWeight;
	}

	/**
	 * @param queWeight the hQueWeight to set
	 */
	public void setHQueWeight(String queWeight) {
		hQueWeight = queWeight;
	}

	/**
	 * @return the totalWeightage
	 */
	public String getTotalWeightage() {
		return totalWeightage;
	}

	/**
	 * @param totalWeightage the totalWeightage to set
	 */
	public void setTotalWeightage(String totalWeightage) {
		this.totalWeightage = totalWeightage;
	}

	/**
	 * @return the sNo
	 */
	public String getSNo() {
		return sNo;
	}

	/**
	 * @param no the sNo to set
	 */
	public void setSNo(String no) {
		sNo = no;
	}

	/**
	 * @return the removePhases
	 */
	public String getRemovePhases() {
		return removePhases;
	}

	/**
	 * @param removePhases the removePhases to set
	 */
	public void setRemovePhases(String removePhases) {
		this.removePhases = removePhases;
	}

	/**
	 * @return the viewMode
	 */
	public String getViewMode() {
		return viewMode;
	}

	/**
	 * @param viewMode the viewMode to set
	 */
	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	/**
	 * @return the editMode
	 */
	public String getEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the removeFlag
	 */
	public String getRemoveFlag() {
		return removeFlag;
	}

	/**
	 * @param removeFlag the removeFlag to set
	 */
	public void setRemoveFlag(String removeFlag) {
		this.removeFlag = removeFlag;
	}

	/**
	 * @return the phaseRating
	 */
	public String getPhaseRating() {
		return phaseRating;
	}

	/**
	 * @param phaseRating the phaseRating to set
	 */
	public void setPhaseRating(String phaseRating) {
		this.phaseRating = phaseRating;
	}

	/**
	 * @return the calId
	 */
	public String getCalId() {
		return calId;
	}

	/**
	 * @param calId the calId to set
	 */
	public void setCalId(String calId) {
		this.calId = calId;
	}

	/**
	 * @return the calCode
	 */
	public String getCalCode() {
		return calCode;
	}

	/**
	 * @param calCode the calCode to set
	 */
	public void setCalCode(String calCode) {
		this.calCode = calCode;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the appStarted
	 */
	public String getAppStarted() {
		return appStarted;
	}

	/**
	 * @param appStarted the appStarted to set
	 */
	public void setAppStarted(String appStarted) {
		this.appStarted = appStarted;
	}

	/**
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * @param newFlag the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	/**
	 * @return the phaseList
	 */
	public ArrayList getPhaseList() {
		return phaseList;
	}

	/**
	 * @param phaseList the phaseList to set
	 */
	public void setPhaseList(ArrayList phaseList) {
		this.phaseList = phaseList;
	}

	/**
	 * @return the calList
	 */
	public ArrayList getCalList() {
		return calList;
	}

	/**
	 * @param calList the calList to set
	 */
	public void setCalList(ArrayList calList) {
		this.calList = calList;
	}

	/**
	 * @return the lstAppraisal
	 */
	public ArrayList getLstAppraisal() {
		return lstAppraisal;
	}

	/**
	 * @param lstAppraisal the lstAppraisal to set
	 */
	public void setLstAppraisal(ArrayList lstAppraisal) {
		this.lstAppraisal = lstAppraisal;
	}

	/**
	 * @return the flglstAppraisal
	 */
	public boolean isFlglstAppraisal() {
		return flglstAppraisal;
	}

	/**
	 * @param flglstAppraisal the flglstAppraisal to set
	 */
	public void setFlglstAppraisal(boolean flglstAppraisal) {
		this.flglstAppraisal = flglstAppraisal;
	}

	/**
	 * @return the sAppId
	 */
	public String getSAppId() {
		return sAppId;
	}

	/**
	 * @param appId the sAppId to set
	 */
	public void setSAppId(String appId) {
		sAppId = appId;
	}

	/**
	 * @return the sAppCode
	 */
	public String getSAppCode() {
		return sAppCode;
	}

	/**
	 * @param appCode the sAppCode to set
	 */
	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}

	/**
	 * @return the sAppStartDate
	 */
	public String getSAppStartDate() {
		return sAppStartDate;
	}

	/**
	 * @param appStartDate the sAppStartDate to set
	 */
	public void setSAppStartDate(String appStartDate) {
		sAppStartDate = appStartDate;
	}

	/**
	 * @return the sAppEndDate
	 */
	public String getSAppEndDate() {
		return sAppEndDate;
	}

	/**
	 * @param appEndDate the sAppEndDate to set
	 */
	public void setSAppEndDate(String appEndDate) {
		sAppEndDate = appEndDate;
	}

	/**
	 * @return the iSrNo
	 */
	public int getISrNo() {
		return iSrNo;
	}

	/**
	 * @param srNo the iSrNo to set
	 */
	public void setISrNo(int srNo) {
		iSrNo = srNo;
	}

	/**
	 * @return the sAppRatingId
	 */
	public String getSAppRatingId() {
		return sAppRatingId;
	}

	/**
	 * @param appRatingId the sAppRatingId to set
	 */
	public void setSAppRatingId(String appRatingId) {
		sAppRatingId = appRatingId;
	}

	/**
	 * @return the sAppMinRating
	 */
	public String getSAppMinRating() {
		return sAppMinRating;
	}

	/**
	 * @param appMinRating the sAppMinRating to set
	 */
	public void setSAppMinRating(String appMinRating) {
		sAppMinRating = appMinRating;
	}

	/**
	 * @return the sAppMaxRating
	 */
	public String getSAppMaxRating() {
		return sAppMaxRating;
	}

	/**
	 * @param appMaxRating the sAppMaxRating to set
	 */
	public void setSAppMaxRating(String appMaxRating) {
		sAppMaxRating = appMaxRating;
	}

	/**
	 * @return the sAllowFraction
	 */
	public String getSAllowFraction() {
		return sAllowFraction;
	}

	/**
	 * @param allowFraction the sAllowFraction to set
	 */
	public void setSAllowFraction(String allowFraction) {
		sAllowFraction = allowFraction;
	}

	/**
	 * @return the sAllowFractionFlg
	 */
	public String getSAllowFractionFlg() {
		return sAllowFractionFlg;
	}

	/**
	 * @param allowFractionFlg the sAllowFractionFlg to set
	 */
	public void setSAllowFractionFlg(String allowFractionFlg) {
		sAllowFractionFlg = allowFractionFlg;
	}

	/**
	 * @return the sRatingType
	 */
	public String getSRatingType() {
		return sRatingType;
	}

	/**
	 * @param ratingType the sRatingType to set
	 */
	public void setSRatingType(String ratingType) {
		sRatingType = ratingType;
	}

	/**
	 * @return the lstQuestionRatingList
	 */
	public ArrayList getLstQuestionRatingList() {
		return lstQuestionRatingList;
	}

	/**
	 * @param lstQuestionRatingList the lstQuestionRatingList to set
	 */
	public void setLstQuestionRatingList(ArrayList lstQuestionRatingList) {
		this.lstQuestionRatingList = lstQuestionRatingList;
	}

	/**
	 * @return the flgQuestionRatingList
	 */
	public boolean isFlgQuestionRatingList() {
		return flgQuestionRatingList;
	}

	/**
	 * @param flgQuestionRatingList the flgQuestionRatingList to set
	 */
	public void setFlgQuestionRatingList(boolean flgQuestionRatingList) {
		this.flgQuestionRatingList = flgQuestionRatingList;
	}

	/**
	 * @return the addFlg
	 */
	public String getAddFlg() {
		return addFlg;
	}

	/**
	 * @param addFlg the addFlg to set
	 */
	public void setAddFlg(String addFlg) {
		this.addFlg = addFlg;
	}

	/**
	 * @return the sDtlId
	 */
	public String getSDtlId() {
		return sDtlId;
	}

	/**
	 * @param dtlId the sDtlId to set
	 */
	public void setSDtlId(String dtlId) {
		sDtlId = dtlId;
	}

	/**
	 * @return the sAppQuestionRatingName
	 */
	public String getSAppQuestionRatingName() {
		return sAppQuestionRatingName;
	}

	/**
	 * @param appQuestionRatingName the sAppQuestionRatingName to set
	 */
	public void setSAppQuestionRatingName(String appQuestionRatingName) {
		sAppQuestionRatingName = appQuestionRatingName;
	}

	/**
	 * @return the sAppQuestionRatingValue
	 */
	public String getSAppQuestionRatingValue() {
		return sAppQuestionRatingValue;
	}

	/**
	 * @param appQuestionRatingValue the sAppQuestionRatingValue to set
	 */
	public void setSAppQuestionRatingValue(String appQuestionRatingValue) {
		sAppQuestionRatingValue = appQuestionRatingValue;
	}

	/**
	 * @return the sAppScoreFlg
	 */
	public String getSAppScoreFlg() {
		return sAppScoreFlg;
	}

	/**
	 * @param appScoreFlg the sAppScoreFlg to set
	 */
	public void setSAppScoreFlg(String appScoreFlg) {
		sAppScoreFlg = appScoreFlg;
	}

	/**
	 * @return the iflg
	 */
	public int getIflg() {
		return iflg;
	}

	/**
	 * @param iflg the iflg to set
	 */
	public void setIflg(int iflg) {
		this.iflg = iflg;
	}

	/**
	 * @return the iSrNoOverall
	 */
	public int getISrNoOverall() {
		return iSrNoOverall;
	}

	/**
	 * @param srNoOverall the iSrNoOverall to set
	 */
	public void setISrNoOverall(int srNoOverall) {
		iSrNoOverall = srNoOverall;
	}

	/**
	 * @return the sAppScoreId
	 */
	public String getSAppScoreId() {
		return sAppScoreId;
	}

	/**
	 * @param appScoreId the sAppScoreId to set
	 */
	public void setSAppScoreId(String appScoreId) {
		sAppScoreId = appScoreId;
	}

	/**
	 * @return the sAppOverAllScoreFrom
	 */
	public String getSAppOverAllScoreFrom() {
		return sAppOverAllScoreFrom;
	}

	/**
	 * @param appOverAllScoreFrom the sAppOverAllScoreFrom to set
	 */
	public void setSAppOverAllScoreFrom(String appOverAllScoreFrom) {
		sAppOverAllScoreFrom = appOverAllScoreFrom;
	}

	/**
	 * @return the sAppOverAllScoreTo
	 */
	public String getSAppOverAllScoreTo() {
		return sAppOverAllScoreTo;
	}

	/**
	 * @param appOverAllScoreTo the sAppOverAllScoreTo to set
	 */
	public void setSAppOverAllScoreTo(String appOverAllScoreTo) {
		sAppOverAllScoreTo = appOverAllScoreTo;
	}

	/**
	 * @return the sAppOverAllScoreValue
	 */
	public String getSAppOverAllScoreValue() {
		return sAppOverAllScoreValue;
	}

	/**
	 * @param appOverAllScoreValue the sAppOverAllScoreValue to set
	 */
	public void setSAppOverAllScoreValue(String appOverAllScoreValue) {
		sAppOverAllScoreValue = appOverAllScoreValue;
	}

	/**
	 * @return the sAppOverAllScoreDesc
	 */
	public String getSAppOverAllScoreDesc() {
		return sAppOverAllScoreDesc;
	}

	/**
	 * @param appOverAllScoreDesc the sAppOverAllScoreDesc to set
	 */
	public void setSAppOverAllScoreDesc(String appOverAllScoreDesc) {
		sAppOverAllScoreDesc = appOverAllScoreDesc;
	}

	/**
	 * @return the sAppOverAllBellCurve
	 */
	public String getSAppOverAllBellCurve() {
		return sAppOverAllBellCurve;
	}

	/**
	 * @param appOverAllBellCurve the sAppOverAllBellCurve to set
	 */
	public void setSAppOverAllBellCurve(String appOverAllBellCurve) {
		sAppOverAllBellCurve = appOverAllBellCurve;
	}

	/**
	 * @return the lstOverAllScoreList
	 */
	public ArrayList getLstOverAllScoreList() {
		return lstOverAllScoreList;
	}

	/**
	 * @param lstOverAllScoreList the lstOverAllScoreList to set
	 */
	public void setLstOverAllScoreList(ArrayList lstOverAllScoreList) {
		this.lstOverAllScoreList = lstOverAllScoreList;
	}

	/**
	 * @return the flgOverAllScoreList
	 */
	public boolean isFlgOverAllScoreList() {
		return flgOverAllScoreList;
	}

	/**
	 * @param flgOverAllScoreList the flgOverAllScoreList to set
	 */
	public void setFlgOverAllScoreList(boolean flgOverAllScoreList) {
		this.flgOverAllScoreList = flgOverAllScoreList;
	}

	/**
	 * @return the sTotalBullCurve
	 */
	public String getSTotalBullCurve() {
		return sTotalBullCurve;
	}

	/**
	 * @param totalBullCurve the sTotalBullCurve to set
	 */
	public void setSTotalBullCurve(String totalBullCurve) {
		sTotalBullCurve = totalBullCurve;
	}

	/**
	 * @return the sMode
	 */
	public String getSMode() {
		return sMode;
	}

	/**
	 * @param mode the sMode to set
	 */
	public void setSMode(String mode) {
		sMode = mode;
	}

	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the readFlag
	 */
	public String getReadFlag() {
		return readFlag;
	}

	/**
	 * @param readFlag the readFlag to set
	 */
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	
}
